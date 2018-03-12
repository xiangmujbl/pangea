@pangea_test @AEAZ-868
Feature: EDMForm
  # test copy attributes from source system to target system

  Scenario: Full Load curation

    Given I import "/ngems/form_v1" by keyFields "formName"
      |formName	|formDescription|
      |000225	|C&C ACNE|
      |000227	|C&C CLEANSERS|
      |000229	|C&C FACIAL MOISTURIZERS|
      |000250	|SUNDOWN SUNCARE REGULAR|
      |000252	|SUNDOWN SUNCARE FACIAL|
      |000253	|SUNDOWN TANNER|
      |000269	|AVEENO BODY WASH|



    And I wait "/ngems/form_v1" Async Queue complete

    When I submit task with xml file "xml/edm/EDMForm_ProjectOne.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/form_v1" by keyFields "formName"
      |formName	|formDescription|
      |000225	|C&C ACNE|
      |000227	|C&C CLEANSERS|
      |000229	|C&C FACIAL MOISTURIZERS|
      |000250	|SUNDOWN SUNCARE REGULAR|
      |000252	|SUNDOWN SUNCARE FACIAL|
      |000253	|SUNDOWN TANNER|
      |000269	|AVEENO BODY WASH|


    #And I compare the number of records between "/ngems/form_v1" and "/edm/form_v1,/edm/form_v1_failed"

    And I delete the test data

    And I will remove all data with region "/edm/form_v1"