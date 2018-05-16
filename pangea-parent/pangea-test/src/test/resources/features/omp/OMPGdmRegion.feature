@pangea_test @AEAZ-2711
Feature: OMPGdmRegion AEAZ-2711

  Scenario: Full Load curation
    # 1. get attributes from cns_plan_region

    And I will remove the test file on sink application "GDMRegion.tsv"

    Given I import "/plan/cns_plan_region" by keyFields "planningRegionId"
      | planningRegionId | planningRegionDesc         |
      | NORTHREG         | South America North Region |
      | SOUTHREG         | South America South Region |

    And I wait "/plan/cns_plan_region" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmRegion.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMRegion.tsv"

    Then I check file data for filename "GDMRegion.tsv" by keyFields "regionId"
      | regionId | activeFCTERP | activeOPRERP | activeSOPERP | regionDescription          |
      | NORTHREG | YES          | YES          | YES          | South America North Region |
      | SOUTHREG | YES          | YES          | YES          | South America South Region |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/plan/cns_plan_region" and "/omp/gdm_region,/plan/edm_failed_data"

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/omp/gdm_region"

    And I will remove all data with region "/plan/edm_failed_data"


