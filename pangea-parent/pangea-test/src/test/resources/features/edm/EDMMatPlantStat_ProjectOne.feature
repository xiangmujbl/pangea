@pangea_test @AEAZ-868
Feature: EDMBrand
  # test copy attributes from source system to target system

  Scenario: Full Load curation

    Given I import "/project_one/t141" by keyFields "mmsta"
    |mmsta	|
    |M001	|
    |M002	|
    |M003	|


    And I wait "/project_one/t141" Async Queue complete

    And I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | localSourceSystemName | sourceSystem | sourceSystemName   |
      | project_one       | Project One           | CONS_LATAM   | Consumer Latam Ent |
      | project_one       | EMS                   | EMS          | EMS Ent            |
      | project_two       | Project Two           | CONS_LATAM   | Consumer Latam Ent |
    And I wait "/edm/source_system_v1" Async Queue complete

    When I submit task with xml file "xml/edm/EDMMatPlantStat_ProjectOne.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/mat_plant_stat_v1" by keyFields "sourceSystem,localPlantStatus"
      |sourceSystem	|localPlantStatus|plantStatus|
      |CONS_LATAM	|M001|                         |
      |EMS	|M002|                                |
      |CONS_LATAM	|M003  |                       |


    #And I compare the number of records between "/ngems/mat_plant_stat_v1" and "/edm/mat_plant_stat_v1,/edm/mat_plant_stat_v1_failed"

    And I delete the test data

    And I will remove all data with region "/edm/mat_plant_stat_v1"