@pangea_test @AEAZ-868
Feature: EDMGlobalBaseUnit
  # test copy attributes from source system to target system

  Scenario: Full Load curation

    Given I import "/ngems/global_base_unit_v1" by keyFields "gbu"
      |gbu	|gbuName|
      |GFO001	|SKINCARE|
      | GFO002	|CHC|
      |GFO003	|OTC|



    And I wait "/ngems/global_base_unit_v1" Async Queue complete

    When I submit task with xml file "xml/edm/EDMGlobalBaseUnit_ProjectOne.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/global_base_unit_v1" by keyFields "gbu"
      |gbu	|gbuName|
      |GFO001	|SKINCARE|
      | GFO002	|CHC|
      |GFO003	|OTC|

    #And I compare the number of records between "/ngems/global_base_unit_v1" and "/edm/global_base_unit_v1,/edm/global_base_unit_v1_failed"

    And I delete the test data

    And I will remove all data with region "/edm/global_base_unit_v1"