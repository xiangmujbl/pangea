@pangea_test @AEAZ-1981
Feature: OMPProductType AEAZ-1981

  Scenario: Full Load curation

    Given I import "/edm/material_type_v1" by keyFields "materialType"
      | materialType | materialTypeName |
      | DIEN         | Service          |
      | FERT         | Finished Product |
    And I wait "/edm/material_type_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPProductType.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/omp/product_type" by keyFields "productTypeId"
      | productTypeId | activeFCTERP | activeOPRERP | activeSOPERP | label            |
      | DIEN          | YES          | YES          | NO           | Service          |
      | FERT          | YES          | YES          | NO           | Finished Product |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/edm/material_type_v1" and "/omp/product_type,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/omp/product_type"

    And I will remove all data with region "/plan/edm_failed_data"
