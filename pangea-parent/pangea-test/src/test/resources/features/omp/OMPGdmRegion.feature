@pangea_test
Feature: OMPGdmRegion

  Scenario: Full Load curation
    # 1. get attributes from cns_plan_region

    Given I import "/plan/cns_plan_region" by keyFields "planningRegionId"
      | planningRegionId | planningRegionDesc         |
      | NORTHREG         | South America North Region |
      | SOUTHREG         | South America South Region |

    And I wait "/plan/cns_plan_region" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmRegion.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/omp/gdm_region" by keyFields "regionId"
      | regionId | activeFCTERP | activeOPRERP | activeSOPERP | regionDescription          |
      | NORTHREG | YES          | YES          | YES          | South America North Region |
      | SOUTHREG | YES          | YES          | YES          | South America South Region |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/plan/cns_plan_region" and "/omp/gdm_region,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/omp/gdm_region"

    And I will remove all data with region "/plan/edm_failed_data"

