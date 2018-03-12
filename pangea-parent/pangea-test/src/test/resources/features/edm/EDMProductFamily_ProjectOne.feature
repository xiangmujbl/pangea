@pangea_test @AEAZ-868
Feature: EDMProductFamily
  # test copy attributes from source system to target system

  Scenario: Full Load curation

    Given I import "/ngems/product_family_v1" by keyFields "productFamily"
      |productFamily	|productFamilyName|
      |AB	|Acuvue Bifocal|
      |AC	|Acuvue 2|
      |AF	|Affinitive|
      |AM	|Ambi
      |AN	|Aveeno|
      |AV	|Acuvue|
      |BA	|BAND-AID|



    And I wait "/ngems/product_family_v1" Async Queue complete

    When I submit task with xml file "xml/edm/EDMProductFamily_ProjectOne.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/product_family_v1" by keyFields "productFamily"
      |productFamily	|productFamilyName|
      |AB	|Acuvue Bifocal|
      |AC	|Acuvue 2|
      |AF	|Affinitive|
      |AM	|Ambi
      |AN	|Aveeno|
      |AV	|Acuvue|
      |BA	|BAND-AID|

    #And I compare the number of records between "/ngems/product_family_v1" and "/edm/product_family_v1,/edm/product_family_v1_failed"

    And I delete the test data

    And I will remove all data with region "/edm/product_family_v1"