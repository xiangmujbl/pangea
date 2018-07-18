@pangea_test @AEAZ-8299
Feature: EDMFranchise AEAZ-8299
  # test copy attributes from source system to target system

  Scenario: Full Load curation

    Given I import "/infa_mdm/c_lkup_fran" by keyFields "franCd"
      | franCd  | franNm            |
      | FCH001 | BABY CARE |
      | FCH002 | BEAUTY |
      | FCH003 | ORAL & TOPICAL |
      | FCH004 | OTC |
      | FCH005 | WOMENS HEALTH |

    And I wait "/infa_mdm/c_lkup_fran" Async Queue complete

    When I submit task with xml file "xml/edm/EDMFranchiseV1.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/franchise_v1" by keyFields "franchise"
      | franchise  | franchiseDescription            |
      | FCH001 | BABY CARE |
      | FCH002 | BEAUTY |
      | FCH003 | ORAL & TOPICAL |
      | FCH004 | OTC |
      | FCH005 | WOMENS HEALTH |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/infa_mdm/c_lkup_fran" and "/edm/franchise_v1,/plan/edm_failed_data"

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/edm/franchise_v1"

    And I will remove all data with region "/plan/edm_failed_data"
