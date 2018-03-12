@pangea_test @AEAZ-868
Feature: EDMSubBrand
  # test copy attributes from source system to target system

  Scenario: Full Load curation

    Given I import "/ngems/sub_brand_v1" by keyFields "subBrand"
      |subBrand	|subBrandDescription|
      |101	|OGX|
      |102	|Neostrata|
      |103	|Internal Sanitary Protection|
      |104	|Baby Bar Soaps|



    And I wait "/ngems/sub_brand_v1" Async Queue complete

    When I submit task with xml file "xml/edm/EDMSubBrand_ProjectOne.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/sub_brand_v1" by keyFields "subBrand"
      |subBrand	|subBrandDescription|
      |101	|OGX|
      |102	|Neostrata|
      |103	|Internal Sanitary Protection|
      |104	|Baby Bar Soaps|

    Then I check region data "/pangea/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/ngems/sub_brand_v1" and "/edm/sub_brand_v1,/pangea/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/edm/sub_brand_v1"