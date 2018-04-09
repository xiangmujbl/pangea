@pangea_test
Feature: OMPCertainity 

  Scenario: Full Load curation
    #test rule N1
    Given I import "/plan/cns_cert_key" by keyFields "certainityKey"
      | certainityKey | certainityKeyDesc |
      |    0001       |     whl     |
    And I wait "/plan/cns_cert_key" Async Queue complete

    When I submit task with xml file "xml/omp/OMPCertainity.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/omp/certainity" by keyFields "certaintyId"
      | certaintyId | activeFCTERP | activeOPRERP | activeSOPERP | label |
      |0001         |YES           |YES           |YES           |whl    |
    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/plan/cns_cert_key" and "/omp/certainity,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/omp/certainity"

    And I will remove all data with region "/plan/edm_failed_data"

