@pangea_test @AEAZ-1763
Feature: OMPLocationType AEAZ-1763

  Scenario: Full Load curation

    And I will remove the test file on sink application "GDMLocationType.tsv"

    Given I import "/plan/cns_loc_type" by keyFields "planLocTypeId"
      | planLocTypeId | planLocTypeDesc              |
      | CO01          | Internal Manufacturing Plant |
    And I wait "/plan/cns_loc_type" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmLocationType.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMLocationType.tsv"

    Then I check file data for filename "GDMLocationType.tsv" by keyFields "locationTypeId"
#    Then I check region data "/omp/location_type" by keyFields "locationTypeId"
      | locationTypeId | activeFCTERP | activeOPRERP | activeSOPERP | label                        |
      | CO01           | YES          | YES          | NO           | Internal Manufacturing Plant |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/omp/location_type"

    And I will remove all data with region "/plan/edm_failed_data"


