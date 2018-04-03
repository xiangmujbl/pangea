@pangea_test @AEAZ-1279
Feature: EDMMatPlantStat AEAZ-1279

  Scenario: Full Load curation

    Given I import "/project_one/t141" by keyFields "mmsta"
      | mmsta |
      | M001  |
      | M002  |
      | M003  |

    And I wait "/project_one/t141" Async Queue complete

    And I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | localSourceSystemName | sourceSystem | sourceSystemName   |
      | project_one       | Project One           | CONS_LATAM   | Consumer Latam Ent |
      | project_two       | Project Two           | CONS_LATAM   | Consumer Latam Ent |
    And I wait "/edm/source_system_v1" Async Queue complete

    When I submit task with xml file "xml/edm/EDMMatPlantStat_ProjectOne.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/mat_plant_stat_v1" by keyFields "sourceSystem,localPlantStatus"
      | sourceSystem | localPlantStatus | plantStatus |
      | CONS_LATAM   | M001             |             |
      | CONS_LATAM   | M002             |             |
      | CONS_LATAM   | M003             |             |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/project_one/t141" and "/edm/mat_plant_stat_v1,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/edm/mat_plant_stat_v1"
    And I will remove all data with region "/plan/edm_failed_data"