@pangea_test @AEAZ-510
Feature: EDMMaterialPlant

  Scenario: Full Load curation

    Given I import "/project_one/marc" by keyFields "matnr,werks"
      | matnr      | werks | mmsta | beskz | bstfe | bstma | bstmi | bstrf | disls | dismm | dispo | dzeit | eisbe | eislo | fevor | frtme | insmk | kausf | kzkri | maabc | mabst | mtvfp | plifz | sbdkz | shflg | shzet | sobsl | strgr | vint1 | vint2 | vrmod | webaz | xchpf | fxhor | basmg |
      | 0000000001 | BR01  | 04    | F     | 0.000 | 0.000 | 0.000 | 0.000 | EX0   | ND    | 151   | 0     | 0.000 | 0.000 | 001   | 0.000 | 0.000 | 0.000 | 0.000 | 0.000 | 0.000 | 01    | 0     | 0     | 0     | 0     | 0     | 0     | 0     | 0     | 0     | 0     | 0     | 0     | BA0   |
      | 0000000001 | BR02  | 05    | F     | 0.001 | 0.001 | 0.001 | 0.001 | EX1   | X4    | 152   | 2     | 0.001 | 0.001 | 002   | 0.001 | 0.001 | 0.001 | 0.001 | 0.001 | 0.001 | 02    | 1     | 1     | 1     | 1     | 1     | 1     | 1     | 1     | 1     | 1     | 1     | 1     | BA1   |
      | 0000000001 | BR03  | 06    | X     | 0.002 | 0.002 | 0.002 | 0.002 | EX2   | ND    | 153   | 1     | 0.002 | 0.002 | 003   | 0.002 | 0.002 | 0.002 | 0.002 | 0.002 | 0.002 | 01    | 2     | 2     | 2     | 2     | 2     | 2     | 2     | 2     | 2     | 2     | 2     | 2     | BA2   |
      | 0000000002 | BR04  | 07    | E     | 0.003 | 0.003 | 0.003 | 0.003 | EX3   | X4    | 154   | 0     | 0.003 | 0.003 | 004   | 0.003 | 0.003 | 0.003 | 0.003 | 0.003 | 0.003 | 01    | 3     | 3     | 3     | 3     | 3     | 3     | 3     | 3     | 3     | 3     | 3     | 3     | BA3   |
      | 0000000003 | BR05  | 08    | E     | 0.004 | 0.004 | 0.004 | 0.004 | EX4   | ND    | 155   | 2     | 0.004 | 0.004 | 005   | 0.004 | 0.004 | 0.004 | 0.004 | 0.004 | 0.004 | 01    | 4     | 4     | 4     | 4     | 4     | 4     | 4     | 4     | 4     | 4     | 4     | 4     | BA4   |
      | 0000000004 | BR06  | 09    | F     | 0.005 | 0.005 | 0.005 | 0.005 | EX5   | X4    | 156   | 3     | 0.005 | 0.005 | 006   | 0.005 | 0.005 | 0.005 | 0.005 | 0.005 | 0.005 | 01    | 5     | 5     | 5     | 5     | 5     | 5     | 5     | 5     | 5     | 5     | 5     | 5     | BA5   |
      | 0000000004 | BR07  | 10    | X     | 0.006 | 0.006 | 0.006 | 0.006 | EX6   | ND    | 157   | 0     | 0.006 | 0.006 | 007   | 0.006 | 0.006 | 0.006 | 0.006 | 0.006 | 0.006 | 01    | 6     | 6     | 6     | 6     | 6     | 6     | 6     | 6     | 6     | 6     | 6     | 6     | BA6   |
    And I wait "/project_one/marc" Async Queue complete

    And I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | sourceSystem |
      | project_one       | CONS_LATAM   |
      | Project_one       | EMS          |
    And I wait "/edm/source_system_v1" Async Queue complete

    And I import "/edm/material_global_v1" by keyFields "localMaterialNumber,sourceSystem"
      | localMaterialNumber | sourceSystem | materialNumber |
      | 0000000001          | CONS_LATAM   | 7891010010001  |
      | 0000000002          | CONS_LATAM   | 7891010010002  |
      | 0000000003          | latam1       | 7891010010003  |
      | 0000000004          | EMS          | 7891010010004  |
      | 0000000005          | CONS_LATAM   | 7891010010005  |
    And I wait "/edm/material_global_v1" Async Queue complete

    And I import "/edm/plant_v1" by keyFields "sourceSystem,localPlant"
      | sourceSystem | localPlant | plant |
      | CONS_LATAM   | BR01       | 00001 |
      | CONS_LATAM   | BR02       | 00002 |
      | cons         | BR03       | 00003 |
      | EMS          | BR04       | 00004 |
      | CONS_LATAM   | BR05       | 00005 |
      | CONS         | BR06       | 00006 |
      | EMS          | BR07       | 00007 |
      | Project_one  | BR08       | 00008 |
    And I wait "/edm/plant_v1" Async Queue complete

    And I import "/edm/mat_plant_stat_v1" by keyFields "sourceSystem,localPlantStatus"
      | sourceSystem | localPlantStatus | plantStatus |
      | CONS_LATAM   | 04               | 100         |
      | CONS_LATAM   | 05               | 101         |
      | CONS_LATAM   | 06               | 102         |
      | Project_one  | 07               | 103         |
      | CONS_LATAM   | 08               | 104         |
      | CONS_LATAM   | 09               | 105         |
      | EMS          | 10               | 106         |
    And I wait "/edm/mat_plant_stat_v1" Async Queue complete

    When I submit task with xml file "xml/edm/material/plant/EDMMaterialPlant_Projectone.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/material_plant_v1" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | materialNumber | plant | localPlantStatus | plantStatus | localProcurementType | localDeletionFlagPlant | localFixedlotsize | localMaximumLotSize | localMinimumLotSize | localRoundingvalueForPoq | localLotsize | localMrpType | localMrpController | localInHouseProcessingTime | localSafetyStock | localMinimumSafetyStock | localProductionSupervisor | localProductionUnit | localPosttoInspectionStock | localComponentScrapInPercent | localCriticalpart | localAbcIndicator | localMaximumStocklevel | localCheckingGroupforAvailabilityCheck | localPlannedDeliveryTimeInDays | localDependentRequirements | localSafetytimeIndicator | localSafetyTime | localSpecialProcurementType | localPlanningStrategyGroup | localConsumptionPeriodBackward | localConsumptionPeriodForward | localConsumptionMode | localGoodsReceiptProcessingTimeInDays | localBatchManagementRequirmentIndicator | localPlanningTimeFence | localPosttoinspstk | localComponentScrap | localBaseQuantity |
      | CONS_LATAM   | 0000000001          | BR01       | 7891010010001  | 00001 | 04               | 100         | F                    | BR01                   | 0.000             | 0.000               | 0.000               | 0.000                    | EX0          | ND           | 151                | 0                          | 0.000            | 0.000                   | 001                       | 0.000               | 0.000                      | 0.000                        | 0.000             | 0.000             | 0.000                  | 01                                     | 0                              | 0                          | 0                        | 0               | 0                           | 0                          | 0                              | 0                             | 0                    | 0                                     | 0                                       | 0                      | 0.000              | 0.000               | BA0               |
      | CONS_LATAM   | 0000000001          | BR02       | 7891010010001  | 00002 | 05               | 101         | F                    | BR02                   | 0.001             | 0.001               | 0.001               | 0.001                    | EX1          | X4           | 152                | 2                          | 0.001            | 0.001                   | 002                       | 0.001               | 0.001                      | 0.001                        | 0.001             | 0.001             | 0.001                  | 02                                     | 1                              | 1                          | 1                        | 1               | 1                           | 1                          | 1                              | 1                             | 1                    | 1                                     | 1                                       | 1                      | 0.001              | 0.001               | BA1               |
      | CONS_LATAM   | 0000000001          | BR03       | 7891010010001  |       | 06               | 102         | X                    | BR03                   | 0.002             | 0.002               | 0.002               | 0.002                    | EX2          | ND           | 153                | 1                          | 0.002            | 0.002                   | 003                       | 0.002               | 0.002                      | 0.002                        | 0.002             | 0.002             | 0.002                  | 01                                     | 2                              | 2                          | 2                        | 2               | 2                           | 2                          | 2                              | 2                             | 2                    | 2                                     | 2                                       | 2                      | 0.002              | 0.002               | BA2               |
      | CONS_LATAM   | 0000000002          | BR04       | 7891010010002  |       | 07               |             | E                    | BR04                   | 0.003             | 0.003               | 0.003               | 0.003                    | EX3          | X4           | 154                | 0                          | 0.003            | 0.003                   | 004                       | 0.003               | 0.003                      | 0.003                        | 0.003             | 0.003             | 0.003                  | 01                                     | 3                              | 3                          | 3                        | 3               | 3                           | 3                          | 3                              | 3                             | 3                    | 3                                     | 3                                       | 3                      | 0.003              | 0.003               | BA3               |
      | CONS_LATAM   | 0000000003          | BR05       |                | 00005 | 08               | 104         | E                    | BR05                   | 0.004             | 0.004               | 0.004               | 0.004                    | EX4          | ND           | 155                | 2                          | 0.004            | 0.004                   | 005                       | 0.004               | 0.004                      | 0.004                        | 0.004             | 0.004             | 0.004                  | 01                                     | 4                              | 4                          | 4                        | 4               | 4                           | 4                          | 4                              | 4                             | 4                    | 4                                     | 4                                       | 4                      | 0.004              | 0.004               | BA4               |
      | CONS_LATAM   | 0000000004          | BR06       |                |       | 09               | 105         | F                    | BR06                   | 0.005             | 0.005               | 0.005               | 0.005                    | EX5          | X4           | 156                | 3                          | 0.005            | 0.005                   | 006                       | 0.005               | 0.005                      | 0.005                        | 0.005             | 0.005             | 0.005                  | 01                                     | 5                              | 5                          | 5                        | 5               | 5                           | 5                          | 5                              | 5                             | 5                    | 5                                     | 5                                       | 5                      | 0.005              | 0.005               | BA5               |
      | CONS_LATAM   | 0000000004          | BR07       |                |       | 10               |             | X                    | BR07                   | 0.006             | 0.006               | 0.006               | 0.006                    | EX6          | ND           | 157                | 0                          | 0.006            | 0.006                   | 007                       | 0.006               | 0.006                      | 0.006                        | 0.006             | 0.006             | 0.006                  | 01                                     | 6                              | 6                          | 6                        | 6               | 6                           | 6                          | 6                              | 6                             | 6                    | 6                                     | 6                                       | 6                      | 0.006              | 0.006               | BA6               |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/project_one/marc" and "/edm/material_plant_v1,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/edm/material_plant_v1"
    And I will remove all data with region "/plan/edm_failed_data"





