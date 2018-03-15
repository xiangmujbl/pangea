@pangea_test @AEAZ-1276
Feature: EDMForm
  # test copy attributes from source system to target system

  Scenario: Full Load curation

    Given I import "/ngems/form_v1" by keyFields "formName"
      |formName	|formDescription|
      |101	|C&C ACNE|
      |102	|C&C CLEANSERS|
      |103	|C&C FACIAL MOISTURIZERS|
      |104	|SUNDOWN SUNCARE REGULAR|



    And I wait "/ngems/form_v1" Async Queue complete

    When I submit task with xml file "xml/edm/EDMForm_ProjectOne.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/form_v1" by keyFields "formName"
      |formName	|formDescription|
      |101	|C&C ACNE|
      |102	|C&C CLEANSERS|
      |103	|C&C FACIAL MOISTURIZERS|
      |104	|SUNDOWN SUNCARE REGULAR|

    Then I check region data "/pangea/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/ngems/form_v1" and "/edm/form_v1,/pangea/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/edm/form_v1"
    And I will remove all data with region "/pangea/edm_failed_data"