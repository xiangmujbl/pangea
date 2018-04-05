@pangea_test
Feature: OMPLocationType-Curation

  Scenario: Full Load curation

    Given I import "/plan/cns_loc_type" by keyFields "planLocTypeId"
      | planLocTypeId | planLocTypeDesc              |
      | CO01          | Internal Manufacturing Plant |

    And I wait "/plan/cns_loc_type" Async Queue complete

    When I submit task with xml file "xml/omp/OMPLocationType.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "gdmLocationType.tsv"

    And I check file data for filename "gdmLocationType.tsv" by keyFields "locationTypeId"
      | locationTypeId | activeFctErp | activeOprErp | activeSopErp | label                        |
      | CO01           | YES          | YES          | YES          | Internal Manufacturing Plant |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I delete the test data

    And I will remove all data with region "/plan/cns_location_type"

    And I will remove the test file on sink application "gdmLocationType.tsv"

