@pangea_test @AEAZ-1273
Feature: EDMBrand
  # test copy attributes from source system to target system

  Scenario: Full Load curation

    Given I import "/ngems/brand_v1" by keyFields "brand"
    |brand	|brandDescription|
    |TD001	|JOHNSONS ADULT|
    |TD002	|JOHNSONS BABY|
    |TD003	|RoC          |


    And I wait "/ngems/brand_v1" Async Queue complete

    When I submit task with xml file "xml/edm/EDMBrand_ProjectOne.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/brand_v1" by keyFields "brand"
      |brand	|brandDescription|
      |TD001	|JOHNSONS ADULT|
      |TD002	|JOHNSONS BABY|
      |TD003	|RoC          |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |


    And I compare the number of records between "/ngems/brand_v1" and "/edm/brand_v1,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/edm/brand_v1"
    And I will remove all data with region "/plan/edm_failed_data"
