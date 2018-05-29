@pangea_test @AEAZ-4484
Feature: OMPProcessType AEAZ-4484

  Scenario: Full Load curation
    # 1. get record from cns_process_type

    And I will remove the test file on sink application "ProcessType.tsv"

    Given I import "/plan/cns_process_type" by keyFields "processTypeId"
      | processTypeId | processTypeDesc |
      | 1             | Inter Plant     |
      | 2             | subcontracting  |

    And I wait "/plan/cns_process_type" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmProcessType.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "ProcessType.tsv"

    Then I check file data for filename "ProcessType.tsv" by keyFields "processTypeId"
      | processTypeId | activeOPRERP | activeSOPERP | label         |
      | 1             | YES          | NO           | Inter Plant   |
      | 2             | YES          | NO           | subcontracting|

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/plan/cns_process_type" and "/omp/gdm_process_type,/plan/edm_failed_data"

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/omp/gdm_process_type"

    And I will remove all data with region "/plan/edm_failed_data"


