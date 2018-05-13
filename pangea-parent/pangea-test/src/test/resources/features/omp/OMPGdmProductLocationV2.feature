@pangea_test @AEAZ-2827
Feature: OMPGdmProductLocationV2-Curation AEAZ-2827

  Scenario: Full Load curation
   # 1. get atrributes from material_global_v1,source_system_v1,cns_material_plan_status (rule J1)
   # 2. get atrributes from material_plant_fin_v1 (rule J2)
   # 3. get atrributes from cns_material_plan_status If cns_material_plan_status (rule D1,T1,T2,)
   # 4. get atrributes from cns_proc_type-procurementType If cns_proc_type-procurementType (rule E1)
   # 5. get atrributes from cns_prod_loc_attrib If cns_prod_loc_attrib (rule E5)

    And I will remove the test file on sink application "GDMProductLocation.tsv"

    Given I import "/edm/material_plant_v1" by keyFields "localPlant,localMaterialNumber,sourceSystem"
      | localMaterialNumber | localPlant | sourceSystem | localProcurementType | localAbcIndicator | localSpecialProcurementType | localPlanningStrategyGroup | localConsumptionMode | localLotSize | localBaseQuantity | localFixedLotSize | localMaximumLotSize | localMinimumLotSize | localRoundingValueForPoq | localMrpType | localMrpController | localInHouseProcessingTime | localSafetyStock | localMinimumSafetyStock | localProductionSupervisor | localPlanningTimeFence | localProductionUnit | localPostToInspectionStock | localComponentScrapInPercent | localCriticalPart | localPlannedDeliveryTimeInDays | localMaximumStockLevel | localPlantStatus | localCheckingGroupForAvailabilityCheck | localInstalledReplenishmentLotSize | localDependentRequirements | localSafetyTimeIndicator | localSafetyTime | localConsumptionPeriodBackward | localConsumptionPeriodForward | localGoodsReceiptProcessingTimeInDays | localBatchManagementRequirementIndicator |
      | 000000000000000001  | BR01       | CON_LATAM    | F                    | C                 | 30                          | 40                         | 2                    | EX           | 0.000             | 0.000             | 0.000               | 0.000               | 1                        | PD           | 606                | 1                          | 0.000            | 0.000                   | 007                       | 000                    | 1                   |                            | 0.00                         | 1                 | 0                              | 0.000                  | 05               | 02                                     |                                    | 2                          |                          | 00              | 045                            | 007                           | 0                                     | X                                        |
      | 000000000000000002  | BR02       | CON_LATAM    | F                    | C                 | 30                          | 40                         | 2                    | EX           | 0.000             | 0.000             | 0.000               | 0.000               | 2                        | PD           | 606                | 1                          | 0.000            | 0.000                   | 007                       | 000                    | 2                   |                            | 0.00                         | 2                 | 0                              | 0.000                  | 05               | 02                                     |                                    | 2                          |                          | 00              | 045                            | 007                           | 0                                     | X                                        |
      | 000000000000000003  | BR03       | CON_LATAM    | F                    | C                 | 30                          | 40                         | 2                    | EX           | 0.000             | 0.000             | 0.000               | 0.000               | 3                        | PD           | 606                | 1                          | 0.000            | 0.000                   | 007                       | 000                    | 3                   |                            | 0.00                         | 3                 | 0                              | 0.000                  | 05               | 02                                     |                                    | 2                          |                          | 00              | 045                            | 007                           | 0                                     | X                                        |
      | 000000000000000004  | BR04       | CON_LATAM    | F                    | C                 | 30                          | 40                         | 2                    | EX           | 0.000             | 0.000             | 0.000               | 0.000               | 4                        | PD           | 606                | 1                          | 0.000            | 0.000                   | 007                       | 000                    | 4                   |                            | 0.00                         | 4                 | 0                              | 0.000                  | 05               | 02                                     |                                    | 2                          |                          | 00              | 045                            | 007                           | 0                                     | X                                        |
      | 000000000000000005  |            | CON_LATAM    | F                    | C                 | 30                          | 40                         | 2                    | EX           | 0.000             | 0.000             | 0.000               | 0.000               | 5                        | PD           | 606                | 1                          | 0.000            | 0.000                   | 007                       | 000                    | 5                   |                            | 0.00                         | 5                 | 0                              | 0.000                  | 05               | 02                                     |                                    | 2                          |                          | 00              | 045                            | 007                           | 0                                     | X                                        |
      | 000000000000000006  | BR05       | CON_LATAM    | F                    | C                 | 30                          | 40                         | 2                    | EX           | 0.000             | 0.000             | 0.000               | 0.000               | 5                        | PD           | 606                | 1                          | 0.000            | 0.000                   | 007                       | 000                    | 5                   |                            | 0.00                         | 5                 | 0                              | 0.000                  | 05               | 02                                     |                                    | 2                          |                          | 00              | 045                            | 007                           | 0                                     | X                                        |
      | 000000000000000007  | BR04       | CON_LATAM    | F                    | C                 | 30                          | 40                         | 2                    | EX           | 0.000             | 0.000             | 0.000               | 0.000               | 4                        | PD           | 606                | 1                          | 0.000            | 0.000                   | 007                       | 000                    | 4                   |                            | 0.00                         | 4                 | 0                              | 0.000                  | 05               | 02                                     |                                    | 2                          |                          | 00              | 045                            | 007                           | 0                                     | X                                        |
    And I wait "/edm/material_plant_v1" Async Queue complete

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | sourceSystem | localSourceSystem |
      | CON_LATAM    | [Consumer LATAM]  |
    And I wait "/edm/source_system_v1" Async Queue complete

    Given I import "/edm/source_list_v1" by keyFields "localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | localVendorAccountNumber | localSourceListRecordValidFrom | localSourceListRecordValidTo | localBlockedSourceofSupply |
      | CON_LATAM    | 000000000000000001  | BR01       | LN01                     | 20030206                       | 20190206                     |                            |
      | CON_LATAM    | 000000000000000002  | BR02       | LN02                     | 20030206                       | 20190206                     |                            |
      | CON_LATAM    | 000000000000000003  | BR03       | LN03                     | 20030206                       | 20190206                     |                            |
      | CON_LATAM    | 000000000000000004  | BR04       | LN04                     | 20030206                       | 20190206                     |                            |
      | CON_LATAM    | 000000000000000006  | BR05       | LN05                     | 20030206                       | 20190206                     |                            |
      | CON_LATAM    | 000000000000000007  | BR04       | LN06                     | 20030206                       | 20200206                     |                            |
    And I wait "/edm/source_system_v1" Async Queue complete

    Given I import "/plan/cns_spl_pln_loc" by keyFields "sourceSystem,localNumber"
      | sourceSystem | localNumber | vendororCustomer |
      | CON_LATAM    | LN06        | V                |
    And I wait "/plan/cns_spl_pln_loc" Async Queue complete

    Given I import "/plan/cons_time_dep_xchange" by keyFields "uniqueId"
      | uniqueId | sourceSystem | fromCurrency | effectiveEndDate | effectiveStartDate | exchangeRate | preference |
      | BRL      | CON_LATAM    |              |                  |                    |              |            |
    And I wait "/plan/cons_time_dep_xchange" Async Queue complete

    Given I import "/edm/plant_v1" by keyFields "sourceSystem,localPlant"
      | localPlant | localCurrency | sourceSystem |
      | BR01       | BRL           | CON_LATAM    |
      | BR02       | BRL           | CON_LATAM    |
      | BR03       | BRL           | CON_LATAM    |
      | BR04       | BRL           | CON_LATAM    |
      | BR04       | BRL           | CON_LATAM    |
    And I wait "/edm/plant_v1" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | sourceSystem     | localMaterialNumber | minRemShelfLife | localRefDescription             | localMaterialType | totalShelfLife | primaryPlanningCode | localDPParentCode | materialNumber |
      | [Consumer LATAM] | 000000000000000001  | 180             | JS COTTON BALLS 50 GRX20 T50P35 | FERT              | 1825           |                     |                   | 111            |
      | [Consumer LATAM] |                     | 180             | JS COTTON BALLS 50 GRX20 T50P35 | FERT              | 1825           |                     |                   | 112            |
      | [Consumer LATAM] | 000000000000000003  | 180             | JS COTTON BALLS 50 GRX20 T50P35 | FERT              | 1825           |                     |                   | 113            |
      | [Consumer LATAM] | 000000000000000004  | 180             | JS COTTON BALLS 50 GRX20 T50P35 | FERT              | 1825           |                     |                   | 114            |
      | [Consumer LATAM] | 000000000000000006  | 180             | JS COTTON BALLS 50 GRX20 T50P35 | FERT              | 1825           |                     |                   | 115            |
      | [Consumer LATAM] | 000000000000000007  | 180             | JS COTTON BALLS 50 GRX20 T50P35 | FERT              | 1825           |                     |                   | 117            |
    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/edm/mat_plant_fi_v1" by keyFields "localMaterialNumber"
      | localMaterialNumber | localPlant | priceControl | localStandardPrice | localPriceUnit | localMovingAverage | localPriceControlIndicator | localMvp |
      | 000000000000000001  | BR01       | v            | 300                | 1              | 1                  |                            | 300      |
      | 000000000000000002  | BR02       | v            | 300                | 1              | 2                  |                            | 300      |
      | 000000000000000003  | BR03       | v            | 300                | 1              | 3                  |                            | 300      |
      | 000000000000000004  | BR04       | v            | 300                | 1              | 4                  |                            | 300      |
      | 000000000000000007  | BR04       | v            | 300                | 1              | 4                  |                            | 300      |
    And I wait "/edm/mat_plant_fi_v1" Async Queue complete

    Given I import "/plan/cns_spl_proc_typ" by keyFields "sourceSystem"
      | specialProcurementType | splProcType | localSplProcType | sourceSystem |
      |                        |             | 30               | CON_LATAM    |
    And I wait "/plan/cns_spl_proc_typ" Async Queue complete

    Given I import "/plan/cns_prod_loc_attrib" by keyFields "localMaterialNumber,localPlant"
      | localMaterialNumber | localPlant | supplyGroup | minMinshelfLife | minShelfLife |
      | 000000000000000001  | BR01       | SG1         |                 |              |
      | 000000000000000002  | BR02       | SG1         |                 |              |
      | 000000000000000003  | BR03       | SG1         |                 |              |
      | 000000000000000004  | BR04       | SG1         |                 | 150          |
      | 000000000000000007  | BR04       | SG1         |                 | 150          |
    And I wait "/plan/cns_prod_loc_attrib" Async Queue complete

    Given I import "/plan/cns_proc_type" by keyFields "localProcurementType,sourceSystem,procurementType"
      | procurementType | localProcurementType | sourceSystem |
      | PE              | F                    | CON_LATAM    |
    And I wait "/plan/cns_proc_type" Async Queue complete

    Given I import "/plan/cns_material_plan_status" by keyFields "localMaterialNumber,localPlant"
      | localMaterialNumber | localPlant | dpRelevant | noPlanRelevant | spRelevant | active |
      | 000000000000000001  | BR01       | X          | X              |            |        |
      | 000000000000000002  | BR02       |            | X              |            |        |
      | 000000000000000003  | BR03       |            | X              | X          |        |
      | 000000000000000004  | BR04       |            | X              |            |        |
      | 000000000000000005  |            |            | X              | X          |        |
      | 000000000000000006  | BR05       |            |                |            |        |
      | 000000000000000007  | BR04       |            | X              |            |        |
    And I wait "/plan/cns_material_plan_status" Async Queue complete

    Given I import "/plan/cns_plng_strat_grp" by keyFields "sourceSystem"
      | sourceSystem | localPlanStratGrp | planStratGrp |
      | CON_LATAM    | 40                | 40           |
      | CON_LATAM    | 40                | 40           |
      | CON_LATAM    | 40                | 40           |
      | CON_LATAM    | 40                | 40           |
    And I wait "/plan/cns_plng_strat_grp" Async Queue complete

    Given I import "/plan/cns_lot_size_key_trans" by keyFields "sourceSystem"
      | lotSizeKey | localLotSizeKey | sourceSystem |
      | EX         | EX              | CON_LATAM    |
    And I wait "/plan/cns_lot_size_key_trans" Async Queue complete

    Given I import "/plan/cns_con_mode" by keyFields "sourceSystem"
      | localConsumptionMode | sourceSystem |
      | 2                    | CON_LATAM    |
      | 2                    | CON_LATAM    |
      | 2                    | CON_LATAM    |
      | 2                    | CON_LATAM    |
    And I wait "/plan/cns_con_mode" Async Queue complete

    Given I import "/plan/cns_abc_ind" by keyFields "sourceSystem"
      | indicator | localIndicator | sourceSystem |
      |           | C              | CON_LATAM    |
      |           | C              | CON_LATAM    |
      |           | C              | CON_LATAM    |
      |           | C              | CON_LATAM    |
    And I wait "/plan/cns_abc_ind" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmProductLocationV2.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMProductLocation.tsv"

    Then I check file data for filename "GDMProductLocation.tsv" by keyFields "productId,locationId"
      | productId     | active | activeFCTERP | activeOPRERP | activeSOPERP | basmg | beskz | bstfe | bstma | bstmi | bstrf | cost | disls | dismm | dispo | dzeit | eisbe | eislo | fevor | fixedHorizon | frtme | insmk | itemPlanningCategory | kausf | kzkri | label                           | leadTime | locationId     | maabc | mabst | minmrsl | minRemainingShelfLife | mmsta | mtvfp | peinh | plifz | productTypeId | replenishmentLotSize | sbdkz | shelfLifeProductTypeId | shflg | shzet | sobsl | stprs | strgr | supplyGroup | supplySource | totalShelfLife | verpr | vint1 | vint2 | vprsv | vrmod | webaz | xchpf | productValue |
      | CON_LATAM_111 | YES    | YES          | YES          | NO           | 0.000 | PE    | 0.000 | 0.000 | 0.000 | 1     | 300  | EX    | PD    | 606   | 1     | 0.000 | 0.000 | 007   | 000          | 1     |       |                      | 0.00  | 1     | JS COTTON BALLS 50 GRX20 T50P35 | 0        | CON_LATAM_BR01 |       | 0.000 |         | 180                   | 05    | 02    | 1     | 0     | FERT          |                      | 2     |                        |       | 00    |       | 300   | 40    | SG1         |              | 1825           | 1     | 045   | 007   |       | 2     | 0     | X     | 300          |
      | CON_LATAM_113 | YES    | NO           | YES          | NO           | 0.000 | PE    | 0.000 | 0.000 | 0.000 | 3     | 300  | EX    | PD    | 606   | 1     | 0.000 | 0.000 | 007   | 000          | 3     |       |                      | 0.00  | 3     | JS COTTON BALLS 50 GRX20 T50P35 | 0        | CON_LATAM_BR03 |       | 0.000 |         | 180                   | 05    | 02    | 1     | 0     | FERT          |                      | 2     |                        |       | 00    |       | 300   | 40    | SG1         |              | 1825           | 3     | 045   | 007   |       | 2     | 0     | X     | 300          |
      | CON_LATAM_114 | YES    | NO           | YES          | NO           | 0.000 | PE    | 0.000 | 0.000 | 0.000 | 4     | 300  | EX    | PD    | 606   | 1     | 0.000 | 0.000 | 007   | 000          | 4     |       |                      | 0.00  | 4     | JS COTTON BALLS 50 GRX20 T50P35 | 0        | CON_LATAM_BR04 |       | 0.000 |         | 150                   | 05    | 02    | 1     | 0     | FERT          |                      | 2     |                        |       | 00    |       | 300   | 40    | SG1         |              | 1825           | 4     | 045   | 007   |       | 2     | 0     | X     | 300          |
      | CON_LATAM_117 | YES    | NO           | YES          | NO           | 0.000 | PE    | 0.000 | 0.000 | 0.000 | 4     | 300  | EX    | PD    | 606   | 1     | 0.000 | 0.000 | 007   | 000          | 4     |       |                      | 0.00  | 4     | JS COTTON BALLS 50 GRX20 T50P35 | 0        | CON_LATAM_BR04 |       | 0.000 |         | 150                   | 05    | 02    | 1     | 0     | FERT          |                      | 2     |                        |       | 00    |       | 300   | 40    | SG1         |              | 1825           | 4     | 045   | 007   |       | 2     | 0     | X     | 300          |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID           | errorCode | sourceSystem | businessArea | key1 | key2               | key3      | key4 | key5 | errorValue                    |
      | SP             | OMPGdmProductLocation | J1        | omp          |              | BR02 | 000000000000000002 | CON_LATAM |      |      | Unable to find DPParentCode   |
      | SP             | OMPGdmProductLocation | C1        | omp          |              |      | 000000000000000005 | CON_LATAM |      |      | Unable to find assigned plant |

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/omp/gdm_product_location_v2"

    And I will remove all data with region "/plan/edm_failed_data"


