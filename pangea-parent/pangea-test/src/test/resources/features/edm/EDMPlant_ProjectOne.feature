@pangea_test @AEAZ-495
Feature: EDMPlant-Curation

  Scenario: Full Load curation
    #  1. test get sourceSystem from source_system_v1 (rule T1)
    #  2. test get NAME1 from T001W ( rule T2 )
    #  3. test get LAND1 from T001W (rule T3)
    #  4. test get countryCode from country_v1 where localCountry = T001W-LAND1 ( rule T4 )
    #  5. test get WAERS form T001 by joining T001K-BWKEY = T001W-BWKEY and T001K-BUKRS = t001-BUKRS ( rule J1 )

    Given I import "/ems/ems_f_z_enterprise_plants" by keyFields "zPlantSourceSystem,zPlant"
      | zPlantSourceSystem | zPlant | zEntPlantNumber | zSite | zEntPlantType | zRegion  |
      | project_one        | AR01   | -               | -     | All Countries | edmPlant |
      | project_two        | AR02   | 00              | *     | Miscellaneous | gdmPlant |
      | [EMS]              | AR06   | 00              | ET    | All Countries | fase     |
    And I wait "/ems/ems_f_z_enterprise_plants" Async Queue complete


    And I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | localSourceSystemName | sourceSystem | sourceSystemName   |
      | project_one       | Project One           | CONS_LATAM   | Consumer Latam Ent |
      | [EMS]             | EMS                   | EMS          | EMS Ent            |
      | project_two       | Project Two           | CONS_LATAM   | Consumer Latam Ent |
    And I wait "/edm/source_system_v1" Async Queue complete

    And I import "/project_one/t001" by keyFields "mandt,bukrs"
      | mandt | bukrs | waers |
      | 110   | 0001  | BRL   |
      | 120   | AU01  | ARS   |
      | 122   | 7450  | AUD   |
    And I wait "/project_one/t001" Async Queue complete

    And I import "/project_one/t001k" by keyFields "bwkey,mandt"
      | mandt | bwkey | bukrs |
      | 110   | AR01  | 0001  |
      | 120   | AR02  | AU01  |
      | 122   | AR06  | 0002  |
    And I wait "/project_one/t001k" Async Queue complete

    And I import "/project_one/t001w" by keyFields "mandt,werks"
      | mandt | werks | name1       | land1 | nodetype | bwkey |
      | 110   | AR01  | Pilar Plant | AR    | AH       | AR01  |
      | 120   | AR02  | S & M Pilar | AR    | DC       | AR02  |
      | 122   | BR04  | João Pessoa | BR    |          | AR06  |
    And I wait "/project_one/t001w" Async Queue complete

    And I import "/edm/country_v1" by keyFields "localCountry,sourceSystem"
      | localCountry | sourceSystem | countryCode | countryName |
      | AR           | CONS_LATAM   | 00          |             |
      | BR           | EMS          | --          |             |
    And I wait "/edm/country_v1" Async Queue complete

    When I submit task with xml file "xml/edm/EDMPlant_ProjectOne.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/plant_v1" by keyFields "sourceSystem,localPlant"
      | sourceSystem | localPlant | localPlantName | plant | localPlanningRelevant | localCountry | country | site | localPlantType | plantType     | localCurrency | region   |
      | CONS_LATAM   | AR01       | Pilar Plant    | -     |                       | AR           | 00      | -    | AH             | All Countries | BRL           | edmPlant |
      | CONS_LATAM   | AR02       | S & M Pilar    | 00    |                       | AR           | 00      | *    | DC             | Miscellaneous | ARS           | gdmPlant |

    Then I check region data "/plan/plan_v1_failed" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1  | key2 | key3 | key4 | key5 | errorValue |
      | DP             | EDMPlant    | T1        | project_one  |              | [EMS] | AR06 |      |      |      |            |

    And I compare the number of records between "/ems/ems_f_z_enterprise_plants" and "/edm/plant_v1,/plan/plan_v1_failed"

    And I delete the test data

    And I will remove all data with region "/edm/plant_v1"
    And I will remove all data with region "/plan/plan_v1_failed"