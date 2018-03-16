@pangea_test @AEAZ-1274
Feature: EDMFranchise
  # test copy attributes from source system to target system

  Scenario: Full Load curation

    Given I import "/ngems/franchise_v1" by keyFields "franchise"
      | franchise | franchiseDescription |
      | 1001      | BABY CARE            |
      | 1002      | BEAUTY               |
      | 1003      | ORAL & TOPICAL       |
      | 1004      | OTC                  |

    And I wait "/ngems/franchise_v1" Async Queue complete

    When I submit task with xml file "xml/edm/EDMFranchise_ProjectOne.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/franchise_v1" by keyFields "franchise"
      | franchise | franchiseDescription |
      | 1001      | BABY CARE            |
      | 1002      | BEAUTY               |
      | 1003      | ORAL & TOPICAL       |
      | 1004      | OTC                  |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/ngems/franchise_v1" and "/edm/franchise_v1,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/edm/franchise_v1"
    And I will remove all data with region "/plan/edm_failed_data"