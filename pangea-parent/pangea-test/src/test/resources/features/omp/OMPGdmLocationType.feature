@pangea_test
Feature:  OMPGdmLocationType-Curation

  Scenario: Full Load curation
    
    Given I import "/plan/cns_loc_type" by keyFields "planLocTypeId"
      | planLocTypeId | planLocTypeDesc              | planLocTypeComments |
      | 10            | Copacker                     |                     |
      | 20            | Subcon                       | Consumer Specific   |
      | 30            | Internal Manufacturing Plant |                     |
    And I wait "/plan/cns_loc_type" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmLocationType.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/omp/gdm_location_type" by keyFields "locationTypeId"
      | locationTypeId | activeFprerp | activeOprerp | activeSoperp | label                        |
      | 10             | YES          | YES          | YES          | Copacker                     |
      | 20             | YES          | YES          | YES          | Subcon                       |
      | 30             | YES          | YES          | YES          | Internal Manufacturing Plant |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/plan/cns_loc_type" and "/omp/gdm_location_type,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/omp/gdm_location_type"

    And I will remove all data with region "/plan/edm_failed_data"

