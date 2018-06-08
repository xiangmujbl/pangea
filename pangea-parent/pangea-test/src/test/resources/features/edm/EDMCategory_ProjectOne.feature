@pangea_test @AEAZ-1271
Feature: EDMCategory AEAZ-1271
  # test copy attributes from source system to target system

  Scenario: Full Load curation

    Given I import "/ngems/category_v1" by keyFields "category"
      | category | categoryName            |
      | 1001     | Body Care Wash          |
      | 1002     | Body Cleansing Bar Soap |
      | 1003     | Body Care Moisturizing  |
      | 1004     | Ultra Thin Pads         |
    And I wait "/ngems/category_v1" Async Queue complete

    When I submit task with xml file "xml/edm/EDMCategory_ProjectOne.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/category_v1" by keyFields "category"
      | category | categoryName            |
      | 1001     | Body Care Wash          |
      | 1002     | Body Cleansing Bar Soap |
      | 1003     | Body Care Moisturizing  |
      | 1004     | Ultra Thin Pads         |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/ngems/category_v1" and "/edm/category_v1,/plan/edm_failed_data"

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/edm/category_v1"

    And I will remove all data with region "/plan/edm_failed_data"
