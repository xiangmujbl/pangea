@pangea_test @AEAZ-4483
Feature: OMPGdmCertainity AEAZ-4483

  Scenario: Full Load curation
    #test rule N1

    And I will remove the test file on sink application "OMPCertainity.tsv"

    Given I import "/plan/cns_cert_key" by keyFields "certainityKey"
      | certainityKey | certainityKeyDesc |
      | 0001          | whl001            |
    And I wait "/plan/cns_cert_key" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmCertainity.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "OMPCertainity.tsv"

    Then I check file data for filename "OMPCertainity.tsv" by keyFields "certaintyId"
      | certaintyId | activeFCTERP | activeOPRERP | activeSOPERP | label  |
      | 0001        | YES          | YES          | NO           | whl001 |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/plan/cns_cert_key" and "/omp/GDMCertainity,/plan/edm_failed_data"

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/omp/GDMCertainity"

    And I will remove all data with region "/plan/edm_failed_data"


