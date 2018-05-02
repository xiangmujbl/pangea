@pangea_test @AEAZ-2377
Feature:  OMPGdmProductLocationDetail-Curation AEAZ-2377

  Scenario: Full Load curation

    Given I import "/plan/cns_material_plan_status" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | materialNumber | localParentCode | ppc | active | dpRelevant | spRelevant | parentActive | noPlanRelevant |
      | CONS_LATAM   | LT9999              | BR19       | 9862           | G3a             | G4a | X      | X          |            | X            | X              |

    And I wait "/plan/cns_material_plan_status" Async Queue complete
    Given I import "/plan/cns_prod_loc_attrib" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | schdAttrbName1 | schAttrbDesc1 | schdAttrbName2 | schAttrbDesc2 | schdAttrbName3 | schAttrbDesc3 | supplyGroup | minShelfLife | minMinShelfLife |
      | CONS_LATAM   | LT9999              | BR19       | ATTRB1         | VALUE1        | ATTRB2         | VALUE2        |                |               |             |              |                 |

    And I wait "/plan/cns_prod_loc_attrib" Async Queue complete
    Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | sourceSystem | localMaterialNumber | localRefDescription | localMaterialType | localBaseUom | materialNumber | refDescription | materialType | baseUom | localDpParentCode | parentCode | globalDpParentCode | form | category | subBrand | brand | franchise | globalBusinessUnit | productFamily | localManufacturingTechnology | manufacturingTechnology | localMaterialGroup | materialGroup | flagForDeletion | materialStatus | division | batchManageIndicator | minRemShelfLife | totalShelfLife | primaryPlanningCode |
      | CONS_LATAM   | LT9999              |                     |                   |              | EM9999         |                |              |         |                   |            |                    |      |          |          |       |           |                    |               |                              |                         |                    |               |                 |                |          |                      |                 |                | EM9999              |

    And I wait "/edm/material_global_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmProductLocationDetail.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMProductLocationDetail.tsv"

    Then I check file data for filename "GDMProductLocationDetail.tsv" by keyFields "productLocationDetailId"
      | productLocationDetailId           | activeOPRERP | activeSOPERP | CLASS | comments | description | name   | productLocationId      | unit | value  |
      | EM9999-CONS_LATAM_BR19/PGA/ATTRB1 | YES          | NO           | PGA   |          | Pangea      | ATTRB1 | EM9999-CONS_LATAM_BR19 |      | VALUE1 |
      | EM9999-CONS_LATAM_BR19/PGA/ATTRB2 | YES          | NO           | PGA   |          | Pangea      | ATTRB2 | EM9999-CONS_LATAM_BR19 |      | VALUE2 |

    Then I check region data "/omp/gdm_product_location_detail" by keyFields "productLocationDetailId"
      | productLocationDetailId           | activeOPRERP | activeSOPERP | CLASS | comments | description | name   | productLocationId      | unit | value  |
      | EM9999-CONS_LATAM_BR19/PGA/ATTRB1 | YES          | NO           | PGA   |          | Pangea      | ATTRB1 | EM9999-CONS_LATAM_BR19 |      | VALUE1 |
      | EM9999-CONS_LATAM_BR19/PGA/ATTRB2 | YES          | NO           | PGA   |          | Pangea      | ATTRB2 | EM9999-CONS_LATAM_BR19 |      | VALUE2 |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

#    And I compare the number of records between "/plan/cns_material_plan_status" and "/omp/gdm_product_location_detail,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/omp/gdm_product_location_detail"

    And I will remove all data with region "/plan/edm_failed_data"

    And I will remove the test file on sink application "GDMProductLocationDetail.tsv"

