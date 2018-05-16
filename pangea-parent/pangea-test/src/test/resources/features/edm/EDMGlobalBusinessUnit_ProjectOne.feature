@pangea_test @AEAZ-1277
Feature: EDMGlobalBaseUnit AEAZ-1277
  # test copy attributes from source system to target system

  Scenario: Full Load curation

    Given I import "/ngems/global_business_unit_v1" by keyFields "gbu"
      | gbu  | gbuName  |
      | 1001 | SKINCARE |
      | 1002 | CHC      |
      | 1003 | OTC      |
    And I wait "/ngems/global_business_unit_v1" Async Queue complete

    When I submit task with xml file "xml/edm/EDMGlobalBusinessUnit_ProjectOne.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/global_business_unit_v1" by keyFields "gbu"
      | gbu  | gbuName  |
      | 1001 | SKINCARE |
      | 1002 | CHC      |
      | 1003 | OTC      |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/ngems/global_business_unit_v1" and "/edm/global_business_unit_v1,/plan/edm_failed_data"

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/edm/global_business_unit_v1"

    And I will remove all data with region "/plan/edm_failed_data"