@pangea_test @AEAZ-868
Feature: EDMFranchise
  # test copy attributes from source system to target system

  Scenario: Full Load curation

    Given I import "/ngems/franchise_v1" by keyFields "franchise"
      |franchise	|franchiseDescription|
      |FCH001	|BABY CARE|
      |FCH002	|BEAUTY|
      |FCH003	|ORAL & TOPICAL|
      |FCH004	|OTC|
      |FCH005	|WOMENS HEALTH|


    And I wait "/ngems/franchise_v1" Async Queue complete

    When I submit task with xml file "xml/edm/EDMFranchise_ProjectOne.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/pangea/edm/franchise_v1" by keyFields "franchise"
      |franchise	|franchiseDescription|
      |FCH001	|BABY CARE|
      |FCH002	|BEAUTY|
      |FCH003	|ORAL & TOPICAL|
      |FCH004	|OTC|
      |FCH005	|WOMENS HEALTH|

    #And I compare the number of records between "/ngems/franchise_v1" and "/pangea/edm/franchise_v1,/pangea/edm/franchise_v1_failed"

    And I delete the test data

    And I will remove all data with region "/pangea/edm/franchise_v1"