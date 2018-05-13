@pangea_test @AEAZ-1979
Feature: OMPGdmProductLocation-Curation AEAZ-1979

  Scenario: Full Load curation
   # 1. get atrributes from material_global_v1,source_system_v1,cns_material_plan_status (rule J1)
   # 2. get atrributes from material_plant_fin_v1 (rule J2)
   # 3. get atrributes from cns_material_plan_status If cns_material_plan_status (rule D1,T1,T2,)
   # 4. get atrributes from cns_proc_type-procurementType If cns_proc_type-procurementType (rule E1)
   # 5. get atrributes from cns_prod_loc_attrib If cns_prod_loc_attrib (rule E5)

    And I will remove the test file on sink application "GDMProductLocation.tsv"

    Given I import "/edm/material_plant_v1" by keyFields "localPlant,localMaterialNumber,sourceSystem"
      | localMaterialNumber | localPlant | sourceSystem     | localProcurementType | localAbcIndicator | localSpecialProcurementType | localPlanningStrategyGroup | localConsumptionMode | localLotSize | localBaseQuantity | localFixedLotSize | localMaximumLotSize | localMinimumLotSize | localRoundingValueForPoq | localMrpType | localMrpController | localInHouseProcessingTime | localSafetyStock | localMinimumSafetyStock | localProductionSupervisor | localPlanningTimeFence | localProductionUnit | localPostToInspectionStock | localComponentScrapInPercent | localCriticalPart | localPlannedDeliveryTimeInDays | localMaximumStockLevel | localPlantStatus | localCheckingGroupForAvailabilityCheck | localInstalledReplenishmentLotSize | localDependentRequirements | localSafetyTimeIndicator | localSafetyTime | localConsumptionPeriodBackward | localConsumptionPeriodForward | localGoodsReceiptProcessingTimeInDays | localBatchManagementRequirementIndicator |
      | 000000000000000001  | BR02       | [Consumer LATAM] | F                    | C                 | 30                          | 40                         | 2                    | EX           | 0.000             | 0.000             | 0.000               | 0.000               |                          | PD           | 606                | 1                          | 0.000            | 0.000                   | 007                       | 000                    |                     |                            | 0.00                         |                   | 0                              | 0.000                  | 05               | 02                                     |                                    | 2                          |                          | 00              | 045                            | 007                           | 0                                     | X                                        |
      | 000000000000000002  | BR02       | [Consumer LATAM] | F                    | C                 | 30                          | 40                         | 2                    | EX           | 0.000             | 0.000             | 0.000               | 0.000               |                          | PD           | 606                | 1                          | 0.000            | 0.000                   | 007                       | 000                    |                     |                            | 0.00                         |                   | 0                              | 0.000                  | 05               | 02                                     |                                    | 2                          |                          | 00              | 045                            | 007                           | 0                                     | X                                        |
      | 000000000000000003  | BR02       | [Consumer LATAM] | F                    | C                 | 30                          | 40                         | 2                    | EX           | 0.000             | 0.000             | 0.000               | 0.000               |                          | PD           | 606                | 1                          | 0.000            | 0.000                   | 007                       | 000                    |                     |                            | 0.00                         |                   | 0                              | 0.000                  | 05               | 02                                     |                                    | 2                          |                          | 00              | 045                            | 007                           | 0                                     | X                                        |
      | 000000000000000004  | BR02       | [Consumer LATAM] | F                    | C                 | 30                          | 40                         | 2                    | EX           | 0.000             | 0.000             | 0.000               | 0.000               |                          | PD           | 606                | 1                          | 0.000            | 0.000                   | 007                       | 000                    |                     |                            | 0.00                         |                   | 0                              | 0.000                  | 05               | 02                                     |                                    | 2                          |                          | 00              | 045                            | 007                           | 0                                     | X                                        |
    And I wait "/edm/material_plant_v1" Async Queue complete

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | sourceSystem | localSourceSystem |
      | CON_LATAM    | [Consumer LATAM]  |
      | CON_LATAM    | [Consumer LATAM]  |
      | CON_LATAM    | [Consumer LATAM]  |
      | CON_LATAM    | [Consumer LATAM]  |
    And I wait "/edm/source_system_v1" Async Queue complete

    Given I import "/plan/cons_time_dep_xchange" by keyFields "uniqueId"
      | preference | unitId | endEff     | factor      | uniqueId | startEff   | exchangeRate |
      | 100        | BRL    | 2018/12/31 | 300         | 1        | 2017/12/31 | 3            |
      | 100        | VEF    | 2019/1/1   | 700         | 2        | 2017/12/31 | 7            |
      | 100        | UYU    | 2019/1/1   | 28.90172958 | 2        | 2017/12/31 | 0.28         |
    And I wait "/plan/cons_time_dep_xchange" Async Queue complete


    Given I import "/edm/plant_v1" by keyFields "sourceSystem,localPlant"
      | localPlant | localCurrency | sourceSystem     |
      | BR02       | BRL           | [Consumer LATAM] |
      | BR02       | BRL           | [Consumer LATAM] |
      | BR02       | BRL           | [Consumer LATAM] |
      | BR02       | BRL           | [Consumer LATAM] |
    And I wait "/edm/plant_v1" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | sourceSystem     | localMaterialNumber | minRemShelfLife | localRefDescription             | localMaterialType | totalShelfLife | primaryPlanningCode | localDPParentCode |
      | [Consumer LATAM] | 000000000000000001  | 180             | JS COTTON BALLS 50 GRX20 T50P35 | FERT              | 1825           | w                   |                   |
      | [Consumer LATAM] | 000000000000000002  | 180             | JS COTTON BALLS 50 GRX20 T50P35 | FERT              | 1825           |                     |                   |
      | [Consumer LATAM] | 000000000000000003  | 180             | JS COTTON BALLS 50 GRX20 T50P35 | FERT              | 1825           | x                   |                   |
      | [Consumer LATAM] | 000000000000000004  | 180             | JS COTTON BALLS 50 GRX20 T50P35 | FERT              | 1825           | z                   |                   |
    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/edm/mat_plant_fi_v1" by keyFields "localMaterialNumber"
      | localMaterialNumber | localPlant | priceControl | localStandardPrice | localPriceUnit | localMovingAverage | localPriceControlIndicator | localMvp |
      | 000000000000000001  | BR02       | v            | 300                |                |                    |                            | 300      |
      | 000000000000000002  | BR02       | v            | 300                |                |                    |                            | 300      |
      | 000000000000000003  | BR02       | v            | 300                |                |                    |                            | 300      |
      | 000000000000000004  | BR02       | v            | 300                |                |                    |                            | 300      |
    And I wait "/edm/mat_plant_fi_v1" Async Queue complete

    Given I import "/plan/cns_spl_proc_typ" by keyFields "sourceSystem"
      | specialProcurementType | splProcType | localSplProcType | sourceSystem     |
      |                        |             | 30               | [Consumer LATAM] |
      |                        |             | 30               | [Consumer LATAM] |
      |                        |             | 30               | [Consumer LATAM] |
      |                        |             | 30               | [Consumer LATAM] |
    And I wait "/plan/cns_spl_proc_typ" Async Queue complete

    Given I import "/plan/cns_prod_loc_attrib" by keyFields "localMaterialNumber,localPlant"
      | localMaterialNumber | localPlant | supplyGroup | minMinShelfLife | minShelfLife |
      | 000000000000000001  | BR02       | SG1         |                 |              |
      | 000000000000000002  | BR02       | SG1         |                 |              |
      | 000000000000000003  | BR02       | SG1         |                 |              |
      | 000000000000000004  | BR02       | SG1         |                 | 150          |
    And I wait "/plan/cns_prod_loc_attrib" Async Queue complete

    Given I import "/plan/cns_proc_type" by keyFields "localProcurementType,sourceSystem,procurementType"
      | procurementType | localProcurementType | sourceSystem     |
      | PE              | F                    | [Consumer LATAM] |
      | PE              | F                    | [Consumer LATAM] |
      | PE              | F                    | [Consumer LATAM] |
      | PE              | F                    | [Consumer LATAM] |
    And I wait "/plan/cns_proc_type" Async Queue complete

    Given I import "/plan/cns_material_plan_status" by keyFields "localMaterialNumber,localPlant"
      | localMaterialNumber | localPlant | dpRelevant | noPlanRelevant | spRelevant | activeStat |
      | 000000000000000001  | BR02       |            | X              |            |            |
      | 000000000000000002  | BR02       |            | X              |            |            |
      | 000000000000000003  | BR02       |            | X              |            |            |
      | 000000000000000004  | BR02       |            | X              |            |            |
    And I wait "/plan/cns_material_plan_status" Async Queue complete

    Given I import "/plan/cns_plng_strat_grp" by keyFields "sourceSystem"
      | sourceSystem     | localPlanStratGrp | planStratGrp |
      | [Consumer LATAM] | 40                |              |
      | [Consumer LATAM] | 40                |              |
      | [Consumer LATAM] | 40                |              |
      | [Consumer LATAM] | 40                |              |
    And I wait "/plan/cns_plng_strat_grp" Async Queue complete

    Given I import "/plan/cns_lot_size_key_trans" by keyFields "sourceSystem"
      | lotSizeKey | localLotSizeKey | sourceSystem     |
      |            | EX              | [Consumer LATAM] |
      |            | EX              | [Consumer LATAM] |
      |            | EX              | [Consumer LATAM] |
      |            | EX              | [Consumer LATAM] |
    And I wait "/plan/cns_lot_size_key_trans" Async Queue complete

    Given I import "/plan/cns_con_mode" by keyFields "sourceSystem"
      | localConsumptionMode | sourceSystem     |
      | 2                    | [Consumer LATAM] |
      | 2                    | [Consumer LATAM] |
      | 2                    | [Consumer LATAM] |
      | 2                    | [Consumer LATAM] |
    And I wait "/plan/cns_con_mode" Async Queue complete

    Given I import "/plan/cns_abc_ind" by keyFields "sourceSystem"
      | indicator | localIndicator | sourceSystem     |
      |           | C              | [Consumer LATAM] |
      |           | C              | [Consumer LATAM] |
      |           | C              | [Consumer LATAM] |
      |           | C              | [Consumer LATAM] |
    And I wait "/plan/cns_abc_ind" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmProductLocation.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMProductLocation.tsv"

    Then I check file data for filename "GDMProductLocation.tsv" by keyFields "productId,locationId"
      | productId | active | activeFCTERP | activeOPRERP | activeSOPERP | basmg | beskz | bstfe | bstma | bstmi | bstrf | cost | disls | dismm | dispo | dzeit | eisbe | eislo | fevor | fixedHorizon | frtme | insmk | itemPlanningCategory | kausf | kzkri | label                           | leadTime | locationId            | maabc | mabst | minmrsl | minRemainingShelfLife | mmsta | mtvfp | peinh | plifz | productTypeId | replenishmentLotSize | sbdkz | shelfLifeProductTypeId | shflg | shzet | sobsl | stprs | strgr | supplyGroup | supplySource | totalShelfLife | verpr | vint1 | vint2 | vprsv | vrmod | webaz | xchpf | productValue |
      | w         | YES    |              | YES          |              | 0.000 | PE    | 0.000 | 0.000 | 0.000 |       | 300  |       | PD    | 606   | 1     | 0.000 | 0.000 | 007   | 000          |       |       |                      | 0.00  |       | JS COTTON BALLS 50 GRX20 T50P35 | 0        | [Consumer LATAM]_BR02 |       | 0.000 |         | 180                   | 05    | 02    |       | 0     | FERT          |                      | 2     |                        |       | 00    |       | 300   |       | SG1         |              | 1825           |       | 045   | 007   |       | 2     | 0     | X     | 300          |
      | x         | YES    |              | YES          |              | 0.000 | PE    | 0.000 | 0.000 | 0.000 |       | 300  |       | PD    | 606   | 1     | 0.000 | 0.000 | 007   | 000          |       |       |                      | 0.00  |       | JS COTTON BALLS 50 GRX20 T50P35 | 0        | [Consumer LATAM]_BR02 |       | 0.000 |         | 180                   | 05    | 02    |       | 0     | FERT          |                      | 2     |                        |       | 00    |       | 300   |       | SG1         |              | 1825           |       | 045   | 007   |       | 2     | 0     | X     | 300          |
      | z         | YES    |              | YES          |              | 0.000 | PE    | 0.000 | 0.000 | 0.000 |       | 300  |       | PD    | 606   | 1     | 0.000 | 0.000 | 007   | 000          |       |       |                      | 0.00  |       | JS COTTON BALLS 50 GRX20 T50P35 | 0        | [Consumer LATAM]_BR02 |       | 0.000 |         | 150                   | 05    | 02    |       | 0     | FERT          |                      | 2     |                        |       | 00    |       | 300   |       | SG1         |              | 1825           |       | 045   | 007   |       | 2     | 0     | X     | 300          |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID           | errorCode | sourceSystem | businessArea | key1 | key2               | key3             | key4 | key5 | errorValue                  |
      | SP             | OMPGdmProductLocation | J1        | omp          |              | BR02 | 000000000000000002 | [Consumer LATAM] |      |      | Unable to find DPParentCode |

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/omp/gdm_product_location"

    And I will remove all data with region "/plan/edm_failed_data"


