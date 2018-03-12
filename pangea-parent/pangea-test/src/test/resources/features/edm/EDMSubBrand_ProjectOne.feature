@pangea_test @AEAZ-868
Feature: EDMSubBrand
  # test copy attributes from source system to target system

  Scenario: Full Load curation

    Given I import "/ngems/sub_brand_v1" by keyFields "subBrand"
      |subBrand	|subBrandDescription|
      |1A	|OGX|
      |1A	|Neostrata|
      |1H	|Internal Sanitary Protection|
      |1K	|Baby Bar Soaps|
      |1L	|Baby Cologne|
      |1N	|Baby Hair Care
      |1P	|Baby Lotion/Cream ex Soft|



    And I wait "/ngems/sub_brand_v1" Async Queue complete

    When I submit task with xml file "xml/edm/EDMSubBrand_ProjectOne.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/pangea/edm/sub_brand_v1" by keyFields "subBrand"
      |subBrand	|subBrandDescription|
      |1A	|OGX|
      |1A	|Neostrata|
      |1H	|Internal Sanitary Protection|
      |1K	|Baby Bar Soaps|
      |1L	|Baby Cologne|
      |1N	|Baby Hair Care
      |1P	|Baby Lotion/Cream ex Soft|

    #And I compare the number of records between "/ngems/sub_brand_v1" and "/pangea/edm/sub_brand_v1,/pangea/edm/sub_brand_v1_failed"

    And I delete the test data

    And I will remove all data with region "/pangea/edm/sub_brand_v1"