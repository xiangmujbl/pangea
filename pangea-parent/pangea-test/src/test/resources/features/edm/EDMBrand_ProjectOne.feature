@pangea_test @AEAZ-868
Feature: EDMBrand
  # test copy attributes from source system to target system

  Scenario: Full Load curation

    Given I import "/ngems/brand_v1" by keyFields "brand"
    |brand	|brandDescription|
    |BRD001	|JOHNSONS ADULT|
    |BRD002	|JOHNSONS BABY|
    |BRD003	|RoC          |
    |BRD004	|STAYFREE     |
    |BRD005	|CLEAN CLEAR  |
    |BRD006	|NEUTROGENA   |


    And I wait "/ngems/brand_v1" Async Queue complete

    When I submit task with xml file "xml/edm/EDMBrand_ProjectOne.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/brand_v1" by keyFields "brand"
      |brand	|brandDescription|
      |BRD001	|JOHNSONS ADULT|
      |BRD002	|JOHNSONS BABY|
      |BRD003	|RoC          |
      |BRD004	|STAYFREE     |
      |BRD005	|CLEAN CLEAR  |
      |BRD006	|NEUTROGENA   |

    #And I compare the number of records between "/ngems/brand_v1" and "/edm/brand_v1,/edm/brand_v1_failed"

    And I delete the test data

    And I will remove all data with region "/edm/brand_v1"