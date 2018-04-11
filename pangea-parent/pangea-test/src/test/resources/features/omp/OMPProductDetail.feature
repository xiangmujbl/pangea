@pangea_test @
Feature:  OMPProductDetail-Curation

  Scenario: Full Load curation

    Given I import "/edm/material_global_v1" by keyFields ""
      | localMaterialNumber | primaryPlanningCode | localDpParentCode | sourceSystem | localManufacturingTechnology |

    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/plan/cns_material_plan_status" by keyFields ""
      | localMaterialNumber | spRelevant | dpRelevant | npRelevant |
    And I wait "/plan/cns_material_plan_status" Async Queue complete

    Given I import "/edm/source_system_v1" by keyFields ""
      | localSourceSystem | sourceSystem |
    And I wait "/edm/source_system_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPProductDetail.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/omp/product_detail" by keyFields ""
      | productDetailId | activeFCTERP | activeOPRERP | activeSOPERP | CLASS | comments | description | name | productId | unit | value |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/edm/material_global_v1" and "/omp/product_detail,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/omp/product_detail"

    And I will remove all data with region "/edm/material_global_v1"

