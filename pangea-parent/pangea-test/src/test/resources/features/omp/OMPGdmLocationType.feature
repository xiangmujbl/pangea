@pangea_test @AEAZ-1763
Feature:  OMPGdmLocationType-Curation

  Scenario: Full Load consumption

    Given I import "/plan/cns_loc_type" by keyFields "planLocTypeId"
      | planLocTypeId | planLocTypeDesc              | planLocTypeComments |
      | CO01          | Internal Manufacturing Plant |                     |
    And I wait "/plan/cns_loc_type" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmLocationType.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "LocationType.tsv"

    And I check file data for filename "LocationType.tsv" by keyFields "locationTypeId,ActiveFCTERP,ActiveOPRERP,ActiveSOPERP,planLocTypeDesc"
      | ActiveOPRERP | LocationTypeId | ActiveSOPERP | LABEL                        | ActiveFCTERP |
      | YES          | CO01           | NO           | Internal Manufacturing Plant | YES          |

    And I delete the test data

    And I will remove all data with region "/plan/cns_loc_type"

    And I will remove the test file on sink application "LocationType.tsv"

