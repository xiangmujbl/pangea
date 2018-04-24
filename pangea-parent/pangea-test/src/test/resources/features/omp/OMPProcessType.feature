@pangea_test @AEAZ-2375
Feature: OMPProcessType AEAZ-2375

  Scenario: Full Load curation
    # 1. get record from cns_process_type

    Given I import "/plan/cns_process_type" by keyFields "processTypeId"
      | processTypeId | processTypeDescription |
      | 1             | Inter Plant            |

    And I wait "/plan/cns_process_type" Async Queue complete

    When I submit task with xml file "xml/omp/OMPProcessType.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "PANGEA_V1_gdm_process_type.tsv"

    Then I check file data for filename "PANGEA_V1_gdm_process_type.tsv" by keyFields "processTypeId"
      | processTypeId | activeOPRERP | activeSOPERP | label       |
      | 1             | YES          | NO           | Inter Plant |

    Then I check region data "/omp/process_type" by keyFields "processTypeId"
      | processTypeId | activeOPRERP | activeSOPERP | label       |
      | 1             | YES          | NO           | Inter Plant |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/plan/cns_process_type" and "/omp/process_type,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/omp/process_type"

    And I will remove all data with region "/plan/edm_failed_data"

    And I will remove the test file on sink application "PANGEA_V1_gdm_process_type.tsv"

