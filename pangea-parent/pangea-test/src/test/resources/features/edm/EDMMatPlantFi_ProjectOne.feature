@pangea_test @AEAZ-1431
Feature: EDMMatPlantFi AEAZ-1431

  Scenario: Full Load curation

    Given I import "/project_one/mbew" by keyFields "matnr,bwkey"
      | matnr              | bwkey | lvorm | vprsv | stprs | peinh | verpr |
      | 000000000000000016 | AR01  |       | S     | 16.62 | 1     | 13.51 |
      | 000000000000000021 | AR01  |       | S     | 55.09 | 1     | 55.09 |
      | 000000000000000029 | AR01  |       | S     | 1.23  | 1     | 1.23  |
    And I wait "/project_one/mbew" Async Queue complete

    And I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | localSourceSystemName | sourceSystem | sourceSystemName   |
      | project_one       | Project One           | CONS_LATAM   | Consumer Latam Ent |
      | project_two       | Project Two           | CONS_LATAM   | Consumer Latam Ent |
    And I wait "/edm/source_system_v1" Async Queue complete

    And I import "/edm/plant_v1" by keyFields "sourceSystem,localPlant"
      | sourceSystem | localPlant | localPlantName | plant | localPlanningRelevant | localCountry | country | site | localPlantType | plantType     | localCurrency | region   |
      | CONS_LATAM   | AR01       | Pilar Plant    | 00    |                       | AR           | 00      | -    | AH             | All Countries | BRL           | edmPlant |
      | CONS_LATAM   | AR02       | S & M Pilar    | 00    |                       | AR           | 00      | *    | DC             | Miscellaneous | ARS           | gdmPlant |
    And I wait "/edm/plant_v1" Async Queue complete

    When I submit task with xml file "xml/edm/EDMMatPlantFi_ProjectOne.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/mat_plant_fi_v1" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | localDeleIndicator | plant | priceControl | localStandardPrice | localPriceUnit | localMvp |
      | CONS_LATAM   | 000000000000000016  | AR01       |                    | 00    | S            | 16.62              | 1              | 13.51    |
      | CONS_LATAM   | 000000000000000021  | AR01       |                    | 00    | S            | 55.09              | 1              | 55.09    |
      | CONS_LATAM   | 000000000000000029  | AR01       |                    | 00    | S            | 1.23               | 1              | 1.23     |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/project_one/mbew" and "/edm/mat_plant_fi_v1,/plan/edm_failed_data"

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/edm/mat_plant_fi_v1"

    And I will remove all data with region "/plan/edm_failed_data"