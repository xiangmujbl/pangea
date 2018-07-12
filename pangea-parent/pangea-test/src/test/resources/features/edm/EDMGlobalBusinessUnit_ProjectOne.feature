@pangea_test @AEAZ-8298
Feature: EDMGlobalBaseUnit AEAZ-8298
  # test copy attributes from source system to target system

  Scenario: Full Load curation

    Given I import "/infa_mdm/c_lkup_gbu" by keyFields "gbuCd"
      | gbuCd    | gbuDescnTxt  |
      | GFO001   | SKINCARE     |  
      | GFO002   | CHC          |
      | GFO003   | OTC          |
    And I wait "/infa_mdm/c_lkup_gbu" Async Queue complete

    When I submit task with xml file "xml/edm/EDMGlobalBusinessUnit_ProjectOne.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/global_business_unit_v1" by keyFields "gbu"
      | gbu      | gbuName  |
      | GFO001   | SKINCARE |
      | GFO002   | CHC      |
      | GFO003   | OTC      |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/infa_mdm/c_lkup_gbu" and "/edm/global_business_unit_v1,/plan/edm_failed_data"

   Scenario: delete all test data

     Then I delete the test data

     And I will remove all data with region "/edm/global_business_unit_v1"

     And I will remove all data with region "/plan/edm_failed_data"