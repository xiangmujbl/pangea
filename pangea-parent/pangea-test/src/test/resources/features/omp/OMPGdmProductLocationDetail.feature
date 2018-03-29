@pangea_test
Feature:  OMPGdmProductLocationDetail-Curation

  Scenario: Full Load curation

    Given I import "/plan/cns_material_plan_status" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | materialNumber | localParentCode | ppc | active | dpRelevant | spRelevant | parentActive | noPlanRelevant |
      | CONS_LATAM   | LT9999              | BR19       | 9862           | G3a             | G4a | X      | X          |            | X            | X              |

    And I wait "/plan/cns_material_plan_status" Async Queue complete

    Given I import "/plan/cns_prod_loc_attrib" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | SourceSystem | localMaterialNumber | localPlant | SchdAttrbName1 | SchdAttrbDesc1 | SchedAttrbName2 | SchdAttrbDesc2 | SchedAttrbName3 | SchdAttrbDesc3 |
      | CONS_LATAM   | LT9999              | BR19       | ATTRB1         | VALUE1         | ATTRB2          | VALUE2         |                 |                |

    And I wait "/plan/cns_prod_loc_attrib" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | SourceSystem | localMaterialNumber | MaterialNumber | getPrimaryPlanningCode |
      | CONS_LATAM   | LT9999              | EM9999         | EM9999                 |

    And I wait "/edm/material_global_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmProductLocationDetail.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/omp/gdm_product_location_detail" by keyFields ""
      | productLocationDetailId           | activeOprerp | activeSoperp | CLASS | description | name   | productLocationId      | unit | value  |
      | EM9999-CONS_LATAM_BR19/PGA/ATTRB1 | YES          | NO           | PGA   | Pangea      | ATTRB1 | EM9999-CONS_LATAM_BR19 |      | VALUE1 |
      | EM9999-CONS_LATAM_BR19/PGA/ATTRB2 | YES          | NO           | PGA   | Pangea      | ATTRB2 | EM9999-CONS_LATAM_BR19 |      | VALUE2 |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    #And I compare the number of records between "/plan/cns_material_plan_status" and "/omp/gdm_product_location_detail,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/omp/gdm_product_location_detail"

    And I will remove all data with region "/plan/cns_material_plan_status"

