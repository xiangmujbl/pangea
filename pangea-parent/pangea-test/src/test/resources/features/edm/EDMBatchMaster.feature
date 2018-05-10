@pangea_test @AEAZ-2368
Feature: EDMBatchMaster AEAZ-2368

  Scenario: Full Load curation
    # 1. get record from mcha,mchb,source_system_v1 (J1)
    # 2. get Plant_V1-Plant where Plant_V1-localPlant = mcha-WERKS (N2)
    # 3. Use Enterprise Date Format (YYYY/MM/DD) (N3)
    # 4. get material_global_v1-PrimaryPlanningCode where batch_master_localMaterialNumber = MCH1-MATNR (N4)
    # 5. material_global_v1-MaterialNumber is blank (N4)
    # 6. get material_global_v1-PrimaryPlanningCode where batch_master_localMaterialNumber <> MCH1-MATNR (N4)

    Given I import "/project_one/mch1" by keyFields "matnr,charg"
      | matnr              | charg      | vfdat    | hsdat    | zustd |
      | 000000000007703910 | 0 190GB 01 | 19900713 | 19920813 |       |
      | 000000000007703911 | 0 190GB 01 | 00000000 | 00000000 |       |
      | 000000000007047792 | 0 603B7 A  | 19991011 | 20000101 |       |
      | 000000000007047793 | 0 603B7 B  | 19991011 | 20000101 |       |

    And I wait "/project_one/mch1" Async Queue complete

    Given I import "/project_one/mcha" by keyFields "werks,matnr,charg,mandt"
      | werks | matnr              | charg      | mandt       |
      | PE01  | 000000000007703910 | 0 190GB 01 | project_one |
      | PE01  | 000000000007703911 | 0 190GB 01 | project_one |
      | CO02  | 000000000007047792 | 0 603B7 A  | project_one |
    And I wait "/project_one/mcha" Async Queue complete

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | localSourceSystemName    | sourceSystem | sourceSystemName   |
      | project_one       | [MD Synthes SAP Anspach] | CONS_LATAM   | Consumer Latam Ent |
    And I wait "/edm/source_system_v1" Async Queue complete

    Given I import "/project_one/mchb" by keyFields "charg,lgort"
      | charg      | lgort |
      | 0 190GB 01 | PE01  |
      | 0 603B7 A  | PE02  |
    And I wait "/project_one/mchb" Async Queue complete

    Given I import "/edm/plant_v1" by keyFields "localPlant"
      | localPlant | plant |
      | PE01       | PE02  |
      | CO03       | CO03  |
    And I wait "/edm/plant_v1" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber,sourceSystem"
      | localMaterialNumber | materialNumber | sourceSystem | primaryPlanningCode |
      | 000000000007703910  | 7703910        | CONS_LATAM   | 7703910             |
      | 000000000007703911  | 7703910        | CONS_LATAM   | 7703910             |
      | 000000000007047792  |                | CONS_LATAM   | 7047792             |
    And I wait "/edm/material_global_v1" Async Queue complete

    When I submit task with xml file "xml/edm/EDMBatchMaster.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/batch_master_v1" by keyFields "srcSysCd,matlNum,btchNum,localPlant,localStorLocation"
      | srcSysCd   | matlNum            | btchNum    | localPlant | localStorLocation | btchExpDt  | btchMfgDt  | plant | materialNumber | btchStsCd |
      | CONS_LATAM | 000000000007703910 | 0 190GB 01 | PE01       | PE01              | 1990/07/13 | 1992/08/13 | PE02  | 7703910        |           |
      | CONS_LATAM | 000000000007703911 | 0 190GB 01 | PE01       | PE01              | 0000/00/00 | 0000/00/00 | PE02  | 7703910        |           |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID    | errorCode | sourceSystem | key1               | key2      | key3 | key4 | key5 | errorValue |
      | SP             | EdmBatchMaster | N4        | edm          | 000000000007047792 | 0 603B7 A |      |      |      |            |
      | SP             | EdmBatchMaster | N4        | edm          | 000000000007047793 | 0 603B7 B |      |      |      |            |


    And I compare the number of records between "/project_one/mch1" and "/edm/batch_master_v1,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/edm/batch_master_v1"

    And I will remove all data with region "/plan/edm_failed_data"

