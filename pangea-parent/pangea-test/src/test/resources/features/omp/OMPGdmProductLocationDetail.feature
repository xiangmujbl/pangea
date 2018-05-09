@pangea_test @AEAZ-2377

Feature:  OMPGdmProductLocationDetail-Curation

  Scenario: Full Load curation

    Given I import "/plan/cns_material_plan_status" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | materialNumber | localParentCode | ppc | active | dpRelevant | spRelevant | parentActive | noPlanRelevant |
      | CONS_LATAM   | LT9999              | BR19       | 9862           | G3a             | G4a | X      | X          | X          | X            | X              |

    And I wait "/plan/cns_material_plan_status" Async Queue complete

    Given I import "/plan/cns_prod_loc_attrib" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | schdAttrbName1 | schAttrbDesc1 | schdAttrbName2 | schAttrbDesc2 | schdAttrbName3 | schAttrbDesc3 |
      | CONS_LATAM   | LT9999              | BR19       | ATTRB1         | VALUE1        | ATTRB2         | VALUE2        |                |               |

    And I wait "/plan/cns_prod_loc_attrib" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | sourceSystem | localMaterialNumber | materialNumber | primaryPlanningCode |
      | CONS_LATAM   | LT9999              | 9862           | EM9999              |

    And I wait "/edm/material_global_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmProductLocationDetail.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "ProductLocationDetail.tsv"

    And I check file data for filename "ProductLocationDetail.tsv" by keyFields "productLocationDetailId"
      | unit | comments | productLocationDetailId           |	productLocationId    |	name   | description | CLASS | value  | activeOPRERP | activeSOPERP |
      |      |          | EM9999-CONS_LATAM_BR19/PGA/ATTRB1 | EM9999-CONS_LATAM_BR19 | ATTRB1  | Pangea      |  PGA  | VALUE1 |     YES      |     NO       |
      |      |          | EM9999-CONS_LATAM_BR19/PGA/ATTRB2 | EM9999-CONS_LATAM_BR19 | ATTRB2  | Pangea      |  PGA  | VALUE2 |     YES      |     NO       |

    And I will remove all data with region "/plan/cns_material_plan_status"

    And I will remove all data with region "/plan/cns_prod_loc_attrib"

    And I will remove all data with region "/edm/material_global_v1"

    And I will remove all data with region "/omp/gdm_product_location_detail"

  #  And I will remove the test file on sink application "ProductLocationDetail.tsv"