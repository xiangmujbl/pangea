@pangea_test
Feature:  OMPProductDetail-Curation

  Scenario: Full Load curation

  #1.test get attributes from cns_material_plan_status and source_system_v1(rule J1,T1,T2,T3,T4,T5)

    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber,sourceSystem"
      | localMaterialNumber | primaryPlanningCode | localDpParentCode | sourceSystem | localManufacturingTechnology |
      | 97568               | 1233                | 111               | CONS_LATAM   | WWWW                         |
      | 97569               | 1233                | 111               | CONS_LATAM   | WWWW                         |
      | 97570               | 1233                | 111               | CONS_LATAM   | WWWW                         |
      | 97571               | 1233                | 111               | CONS_LATAM   | WWWW                         |
      | 97572               | 1233                | 111               | CONS_LATAM   | WWWW                         |

    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/plan/cns_material_plan_status" by keyFields "localMaterialNumber"
      | localMaterialNumber | spRelevant | dpRelevant | noPlanRelevant |sourceSystem|
      | 97568               | X          | X          | X              |CONS_LATAM  |

    And I wait "/plan/cns_material_plan_status" Async Queue complete

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | sourceSystem |
      | CONS_LATAM        | project_one  |
    And I wait "/edm/source_system_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPProductDetail.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/omp/product_detail" by keyFields "productDetailId"
      | productDetailId               | activeFCTERP | activeOPRERP | activeSOPERP | CLASS | comments | description | name       | productId        | unit | value           |
      | 1233/PGA/LATAM_ROOT           | NO           | YES          |              |       |          |             | LATAM_ROOT | 1233             |      | project_one_111 |
      | 1233/PGA/LATAM_SKU            | NO           | YES          |              |       |          |             | LATAM_SKU  | 1233             |      | 97568           |
      | 1233/PGA/LATAM_TECH           | NO           | YES          |              |       |          |             | LATAM_TECH | 1233             |      | WWWW            |
      | CONS_LATAM_111/PGA/LATAM_ROOT | NO           | YES          |              |       |          |             | LATAM_ROOT | project_one_111  |      | project_one_111 |
      | CONS_LATAM_111/PGA/LATAM_SKU  | NO           | YES          |              |       |          |             | LATAM_SKU  | project_one_1233 |      | 97568           |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/edm/material_global_v1" and "/omp/product_detail,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/omp/product_detail"

    And I will remove all data with region "/plan/edm_failed_data"

