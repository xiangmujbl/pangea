@pangea_test @AEAZ-7229
Feature: OMPGDMProductLocation AEAZ-7229

  Scenario: Full Load curation
  1. get atrributes from material_global_v1,source_system_v1,cns_material_plan_status (rule J1)
  2. get atrributes from material_plant_fin_v1 (rule J2)
  3. get atrributes from cns_material_plan_status If cns_material_plan_status (rule D1,T1,T2,)
  4. get atrributes from cns_proc_type-procurementType If cns_proc_type-procurementType (rule E1)
  5. get atrributes from cns_prod_loc_attrib If cns_prod_loc_attrib (rule E5)
  
  And I will remove the test file on sink application "GDMProductLocation.tsv"

    Given I import "/edm/material_plant_v1" by keyFields "localPlant,localMaterialNumber,sourceSystem"
      | localMaterialNumber | localPlant | sourceSystem | localProcurementType | localAbcIndicator | localSpecialProcurementType | localPlanningStrategyGroup | localConsumptionMode | localLotSize | localBaseQuantity | localFixedLotSize | localMaximumLotSize | localMinimumLotSize | localRoundingValueForPoq | localMrpType | localMrpController | localInHouseProcessingTime | localSafetyStock | localMinimumSafetyStock | localProductionSupervisor | localPlanningTimeFence | localProductionUnit | localPostToInspectionStock | localComponentScrapInPercent | localCriticalPart | localPlannedDeliveryTimeInDays | localMaximumStockLevel | localPlantStatus | localCheckingGroupForAvailabilityCheck | localInstalledReplenishmentLotSize | localDependentRequirements | localSafetyTimeIndicator | localSafetyTime | localConsumptionPeriodBackward | localConsumptionPeriodForward | localGoodsReceiptProcessingTimeInDays | localBatchManagementRequirementIndicator |
      | 000000000000000001  | BR01       | CON_LATAM    | M                    | C                 | 30                          |                            |                      |              | 0.000             | 0.000             | 0.000               | 0.000               | 1                        | PD           | 606                | 1                          | 0.000            | 0.000                   | 007                       | 000                    | 1                   |                            | 0.00                         | 1                 | 0                              | 0.000                  | 05               | 02                                     |                                    | 2                          |                          | 00              | 045                            | 007                           | 0                                     | X                                        |
      | 000000000000000002  | BR02       | CON_LATAM    | F                    | C                 | 30                          | 40                         | 2                    | EX           | 0.000             | 0.000             | 0.000               | 0.000               | 2                        | PD           | 606                | 1                          | 0.000            | 0.000                   | 007                       | 000                    | 2                   |                            | 0.00                         | 2                 | 0                              | 0.000                  | 05               | 02                                     |                                    | 2                          |                          | 00              | 045                            | 007                           | 0                                     | X                                        |
      | 000000000000000003  | BR03       | CON_LATAM    | F                    |                   | 30                          | 40                         | 2                    | EX           | 0.000             | 0.000             | 0.000               | 0.000               | 3                        | PD           | 606                | 1                          | 0.000            | 0.000                   | 007                       | 000                    | 3                   |                            | 0.00                         | 3                 | 0                              | 0.000                  | 05               | 02                                     |                                    | 2                          |                          | 00              | 045                            | 007                           | 0                                     | X                                        |
      | 000000000000000004  | BR04       | CON_LATAM    | F                    | C                 |                             | 40                         | 2                    | EX           | 0.000             | 0.000             | 0.000               | 0.000               | 4                        | PD           | 606                | 1                          | 0.000            | 0.000                   | 007                       | 000                    | 4                   |                            | 0.00                         | 4                 | 0                              | 0.000                  | 05               | 02                                     |                                    | 2                          |                          | 00              | 045                            | 007                           | 0                                     | Y                                        |
      | 000000000000000005  |            | CON_LATAM    | F                    | C                 | 30                          | 40                         | 2                    | EX           | 0.000             | 0.000             | 0.000               | 0.000               | 5                        | PD           | 606                | 1                          | 0.000            | 0.000                   | 007                       | 000                    | 5                   |                            | 0.00                         | 5                 | 0                              | 0.000                  | 05               | 02                                     |                                    | 2                          |                          | 00              | 045                            | 007                           | 0                                     | Y                                        |
      | 000000000000000006  | BR05       | CON_LATAM    | F                    | C                 | 30                          | 40                         | 2                    | EX           | 0.000             | 0.000             | 0.000               | 0.000               | 5                        | PD           | 606                | 1                          | 0.000            | 0.000                   | 007                       | 000                    | 5                   |                            | 0.00                         | 5                 | 0                              | 0.000                  | 05               | 02                                     |                                    | 2                          |                          | 00              | 045                            | 007                           | 0                                     | N                                        |
      | 000000000000000007  | BR04       | CON_LATAM    | F                    | C                 | 30                          | 40                         | 2                    | EX           | 0.000             | 0.000             | 0.000               | 0.000               | 4                        | PD           | 606                | 1                          | 0.000            | 0.000                   | 007                       | 000                    | 4                   |                            | 0.00                         | 4                 | 0                              | 0.000                  | 05               | 02                                     |                                    | 2                          |                          | 00              | 045                            | 007                           | 0                                     |                                          |
      | 000000000000000008  | BR06       | CON_LATAM    | F                    | C                 | 30                          | 40                         | 2                    | EX           | 0.000             | 0.000             | 0.000               | 0.000               | 4                        | PD           | 606                | 1                          | 0.000            | 0.000                   | 007                       | 000                    | 4                   |                            | 0.00                         | 4                 | 0                              | 0.000                  | 05               | 02                                     |                                    | 2                          |                          | 00              | 045                            | 007                           | 0                                     |                                          |
      | 000000000000000009  | BR07       | CON_LATAM    | F                    | C                 | 30                          | 40                         | 2                    | EX           | 0.000             | 0.000             | 0.000               | 0.000               | 4                        | PD           | 606                | 1                          | 0.000            | 0.000                   | 007                       | 000                    | 4                   |                            | 0.00                         | 4                 | 0                              | 0.000                  | 05               | 02                                     |                                    | 2                          |                          | 00              | 045                            | 007                           | 0                                     |                                          |
      | 000000000000000010  | BR07       | CON_LATAM    | F                    | C                 | 30                          | 40                         | 2                    | EX           | 0.000             | 0.000             | 0.000               | 0.000               | 4                        | PD           | 606                | 1                          | 0.000            | 0.000                   | 007                       | 000                    | 4                   |                            | 0.00                         | 4                 | 0                              | 0.000                  | 05               | 02                                     |                                    | 2                          |                          | 00              | 045                            | 007                           | 0                                     |                                          |
      | 000000000000000011  | BR07       | CON_LATAM    | F                    | C                 | 30                          | 40                         | 2                    | EX           | 0.000             | 0.000             | 0.000               | 0.000               | 4                        | PD           | 606                | 1                          | 0.000            | 0.000                   | 007                       | 000                    | 4                   |                            | 0.00                         | 4                 | 0                              | 0.000                  | 05               | 02                                     |                                    | 2                          |                          | 00              | 045                            | 007                           | 0                                     |                                          |
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
      | CON_LATAM    | 000000000000000008  | BR06       | LN04                     | 20030206                       | 20200206                     | X                          |
      | CON_LATAM    | 000000000000000009  | BR07       | LN04                     | 20030206                       | 20200206                     |                            |
      | CON_LATAM    | 000000000000000010  | BR07       | LN05                     | 20030206                       | 20200206                     |                            |
      | CON_LATAM    | 000000000000000011  | BR07       | LN05                     | 20880206                       | 20120101                     |                            |
    And I wait "/edm/source_system_v1" Async Queue complete

    Given I import "/plan/cns_spl_pln_loc" by keyFields "sourceSystem,localNumber,vendorOrCustomer"
      | sourceSystem | localNumber | vendorOrCustomer | localPlant |
      | CON_LATAM    | LN01        |                  |            |
      | CON_LATAM    | LN03        | V                |            |
      | CON_LATAM    | LN04        | V                | CO01       |
      | CON_LATAM    | LN04        |                  |            |

    And I wait "/plan/cns_spl_pln_loc" Async Queue complete

    Given I import "/plan/cons_time_dep_xchange" by keyFields "effectiveEndDate,effectiveStartDate,fromCurrency"
      | sourceSystem | fromCurrency | toCurrency | effectiveEndDate | effectiveStartDate | exchangeRate     | preference |
      | CON_LATAM    | BRL          | USD        | 31/12/2019       | 2018/1/1           |                  |            |
      | CON_LATAM    | CLP          | USD        | 31/12/2019       | 2018/1/1           | 3.25000325000325 |            |
      | CON_LATAM    | COP          | USD        | 31/12/2019       | 2018/1/1           |                  |            |
      | CON_LATAM    | USD          | USD        | 31/12/2019       | 2018/1/1           |                  |            |
      | CON_LATAM    | ARS          | USD        | 31/12/2019       | 2018/1/1           | 3.00000000000000 |            |
      | CON_LATAM    | MXN          | USD        | 31/12/2019       | 2019/1/1           |                  |            |

    And I wait "/plan/cons_time_dep_xchange" Async Queue complete

    Given I import "/edm/plant_v1" by keyFields "sourceSystem,localPlant"
      | localPlant | localCurrency | sourceSystem |
      | BR01       | BRL           | CON_LATAM    |
      | BR02       | CLP           | CON_LATAM    |
      | BR03       | CLP           | CON_LATAM    |
      | BR04       | USD           | CON_LATAM    |
      | BR06       | ARS           | CON_LATAM    |
      | BR07       | MXN           | CON_LATAM    |

    And I wait "/edm/plant_v1" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | sourceSystem     | localMaterialNumber | minRemShelfLife | localRefDescription             | materialType | totalShelfLife | primaryPlanningCode | localDPParentCode | materialNumber |
      | [Consumer LATAM] | 000000000000000001  | 180             | JS COTTON BALLS 50 GRX20 T50P35 | FERT         | 1825           |                     |                   | 111            |
      | [Consumer LATAM] | 000000000000000002  | 180             | JS COTTON BALLS 50 GRX20 T50P35 | FERT         | 1825           |                     |                   |                |
      | [Consumer LATAM] | 000000000000000003  | 180             | JS COTTON BALLS 50 GRX20 T50P35 | FERT         | 1825           |                     |                   | 113            |
      | [Consumer LATAM] | 000000000000000004  | 180             | JS COTTON BALLS 50 GRX20 T50P35 | FERT         | 1825           |                     |                   | 114            |
      | [Consumer LATAM] | 000000000000000006  | 180             | JS COTTON BALLS 50 GRX20 T50P35 | FERT         | 1825           |                     |                   | 115            |
      | [Consumer LATAM] | 000000000000000007  | 180             | JS COTTON BALLS 50 GRX20 T50P35 | FERT         | 1825           |                     |                   | 117            |
      | [Consumer LATAM] | 000000000000000008  | 180             | JS COTTON BALLS 50 GRX20 T50P35 | FERT         | 1825           |                     |                   | 118            |
      | [Consumer LATAM] | 000000000000000009  |                 | JS COTTON BALLS 50 GRX20 T50P35 | FERT         | 1825           | 130                 |                   | 119            |
      | [Consumer LATAM] | 000000000000000010  |                 | JS COTTON BALLS 50 GRX20 T50P35 | FERT         | 1825           | 120                 |                   | 120            |
      | [Consumer LATAM] | 000000000000000011  |                 | JS COTTON BALLS 50 GRX20 T50P35 | FERT         | 1825           |                     |                   | 121            |

    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/edm/mat_plant_fi_v1" by keyFields "localMaterialNumber"
      | localMaterialNumber | localPlant | priceControl | localStandardPrice | localPriceUnit | localMvp |
      | 000000000000000001  | BR01       | V            | 600                | 1              | 300      |
      | 000000000000000002  | BR02       | V            | 600                | 1              | 300      |
      | 000000000000000003  | BR03       | V            | 600                | 1              | 300      |
      | 000000000000000004  | BR04       | V            | 600                | 1              | 300      |
      | 000000000000000007  | BR04       | S            | 600                | 1              | 300      |
      | 000000000000000008  | BR06       | S            | 600                | 1              | 300      |
      | 000000000000000009  | BR07       | S            | 600                | 1              | 300      |
      | 000000000000000010  | BR07       | S            | 600                | 1              | 300      |
      | 000000000000000011  | BR07       | S            | 600                | 1              | 300      |
    And I wait "/edm/mat_plant_fi_v1" Async Queue complete

    Given I import "/plan/cns_spl_proc_typ" by keyFields "sourceSystem"
      | specialProcurementType | splProcType | localSplProcType | sourceSystem |
      |                        | 10          | 30               | CON_LATAM    |
    And I wait "/plan/cns_spl_proc_typ" Async Queue complete

    Given I import "/plan/cns_prod_loc_attrib" by keyFields "localMaterialNumber,localPlant,sourceSystem"
      | localMaterialNumber | localPlant | supplyGroup | minMinshelfLife | minShelfLife | sourceSystem |
      | 000000000000000001  | BR01       | SG1         |                 |              | CON_LATAM    |
      | 000000000000000002  | BR02       | SG1         |                 |              | CON_LATAM    |
      | 000000000000000003  | BR03       | SG1         |                 |              | CON_LATAM    |
      | 000000000000000004  | BR04       | SG1         |                 | 150          | CON_LATAM    |
      | 000000000000000007  | BR04       | SG1         |                 | 150          | CON_LATAM    |
      | 000000000000000008  | BR06       | SG1         |                 | 150          | CON_LATAM    |
      | 000000000000000009  | BR07       | SG2         |                 | 150          | CON_LATAM    |
      | 000000000000000010  | BR07       | SG2         |                 | 150          | CON_LATAM    |
      | 000000000000000011  | BR07       | SG2         |                 | 150          | CON_LATAM    |
    And I wait "/plan/cns_prod_loc_attrib" Async Queue complete

    Given I import "/plan/prod_loc_min_shelf" by keyFields "localMaterialNumber,localPlant,sourceSystem"
      | localMaterialNumber | localPlant | sourceSystem | minMinShelfLife | minShelfLife |
      | 000000000000000001  | BR01       | CONS_LATAM   | 111             | 180          |
      | 000000000000000002  | BR02       | CONS_LATAM   |                 |              |
      | *                   | BR03       | CONS_LATAM   | 222             | 180          |
      |                     | BR04       | CONS_LATAM   | 333             | 150          |
    And I wait "/plan/prod_loc_min_shelf" Async Queue complete
#
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
      | 000000000000000007  | BR04       |            |                | X          |        |
      | 000000000000000008  | BR06       |            | X              |            |        |
      | 000000000000000009  | BR07       |            | X              |            |        |
      | 000000000000000010  | BR07       |            | X              |            |        |
      | 000000000000000011  | BR07       |            | X              |            |        |
    And I wait "/plan/cns_material_plan_status" Async Queue complete

    Given I import "/plan/cns_plng_strat_grp" by keyFields "sourceSystem"
      | sourceSystem | localPlanStratGrp | planStratGrp |
      | CON_LATAM    | 40                | 40           |
    And I wait "/plan/cns_plng_strat_grp" Async Queue complete

    Given I import "/plan/cns_lot_size_key_trans" by keyFields "sourceSystem"
      | lotSizeKey | localLotSizeKey | sourceSystem |
      | EX         | EX              | CON_LATAM    |
    And I wait "/plan/cns_lot_size_key_trans" Async Queue complete

    Given I import "/plan/cns_con_mode" by keyFields "sourceSystem"
      | localConsumptionMode | sourceSystem |
      | 2                    | CON_LATAM    |
    And I wait "/plan/cns_con_mode" Async Queue complete

    Given I import "/plan/cns_abc_ind" by keyFields "sourceSystem"
      | indicator | localIndicator | sourceSystem |
      | A         | C              | CON_LATAM    |
    And I wait "/plan/cns_abc_ind" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmProductLocation.xml" and execute file "jar/pangea-view.jar"
    Then A file is found on sink application with name "GDMProductLocation.tsv"

    Then I check file data for filename "GDMProductLocation.tsv" by keyFields "productId,locationId"
      | productId | locationId          | active | activeFCTERP | activeOPRERP | activeSOPERP | beskz | bstfe | bstma | bstmi | bstrf | disls | dismm | dispo | dzeit | ProductTypeId | fevor | fixedHorizon | frtme | insmk | itemPlanningCategory | kausf | kzkri | label                           | leadTime | maabc | minmrsl | minRemainingShelfLife | mmsta | mtvfp | replenishmentLotSize | shflg | sobsl | strgr | supplySource | totalShelfLife | vint1 | vint2 | vrmod | webaz | BatchManaged | MinInventoryQuantity | MaxInventoryQuantity | supplyGroup | productValue |
      | 111       | CON_LATAM_BR01      | YES    | YES          | YES          | NO           |       | 0.000 | 0.000 | 0.000 | 1     |       | PD    | 606   | 1     | FERT          | 007   | 000          | 1     |       |                      | 0.00  | 1     | JS COTTON BALLS 50 GRX20 T50P35 | 0        | A     | 111     | 180                   | 05    | 02    |                      |       | 10    |       |              | 1825           | 045   | 007   |       | 0     | YES          | 0.000                | 0.000                | SG1         | 300.0 |
      | 113       | CON_LATAM_BR03      | YES    | NO           | YES          | NO           | PE    | 0.000 | 0.000 | 0.000 | 3     | EX    | PD    | 606   | 1     | FERT          | 007   | 000          | 3     |       |                      | 0.00  | 3     | JS COTTON BALLS 50 GRX20 T50P35 | 0        |       | 222     | 180                   | 05    | 02    |                      |       | 10    | 40    |              | 1825           | 045   | 007   | 2     | 0     | YES          | 0.000                | 0.000                | SG1         | 300.0 |
      | 113       | CON_LATAM_V_LN03    | YES    | NO           | YES          | NO           | PE    | 0.000 | 0.000 | 0.000 | 3     | EX    | PD    | 606   | 1     | FERT          | 007   | 000          | 3     |       |                      | 0.00  | 3     | JS COTTON BALLS 50 GRX20 T50P35 | 0        |       | 222     | 180                   | 05    | 02    |                      |       | 10    | 40    |              | 1825           | 045   | 007   | 2     | 0     | YES          | 0.000                | 0.000                | SG1         | 300.0 |
      | 114       | CON_LATAM_BR04      | YES    | NO           | YES          | NO           | PE    | 0.000 | 0.000 | 0.000 | 4     | EX    | PD    | 606   | 1     | FERT          | 007   | 000          | 4     |       |                      | 0.00  | 4     | JS COTTON BALLS 50 GRX20 T50P35 | 0        | A     |         | 180                   | 05    | 02    |                      |       |       | 40    |              | 1825           | 045   | 007   | 2     | 0     | NO           | 0.000                | 0.000                | SG1         | 300.0 |
      | 117       | CON_LATAM_BR04      | YES    | NO           | YES          | NO           | PE    | 0.000 | 0.000 | 0.000 | 4     | EX    | PD    | 606   | 1     | FERT          | 007   | 000          | 4     |       |                      | 0.00  | 4     | JS COTTON BALLS 50 GRX20 T50P35 | 0        | A     |         | 180                   | 05    | 02    |                      |       | 10    | 40    |              | 1825           | 045   | 007   | 2     | 0     | NO           | 0.000                | 0.000                | SG1         | 600.0 |
      | 118       | CON_LATAM_BR06      | YES    | NO           | YES          | NO           | PE    | 0.000 | 0.000 | 0.000 | 4     | EX    | PD    | 606   | 1     | FERT          | 007   | 000          | 4     |       |                      | 0.00  | 4     | JS COTTON BALLS 50 GRX20 T50P35 | 0        | A     |         | 180                   | 05    | 02    |                      |       | 10    | 40    |              | 1825           | 045   | 007   | 2     | 0     | NO           | 0.000                | 0.000                | SG1         | 600.0 |
      | 120       | CON_LATAM_BR07      | YES    | NO           | YES          | NO           | PE    | 0.000 | 0.000 | 0.000 | 4     | EX    | PD    | 606   | 1     | FERT          | 007   | 000          | 4     |       |                      | 0.00  | 4     | JS COTTON BALLS 50 GRX20 T50P35 | 0        | A     |         |                       | 05    | 02    |                      |       | 10    | 40    |              | 1825           | 045   | 007   | 2     | 0     | NO           | 0.000                | 0.000                | SG2         | 600.0 |
      | 121       | CON_LATAM_BR07      | YES    | NO           | YES          | NO           | PE    | 0.000 | 0.000 | 0.000 | 4     | EX    | PD    | 606   | 1     | FERT          | 007   | 000          | 4     |       |                      | 0.00  | 4     | JS COTTON BALLS 50 GRX20 T50P35 | 0        | A     |         |                       | 05    | 02    |                      |       | 10    | 40    |              | 1825           | 045   | 007   | 2     | 0     | NO           | 0.000                | 0.000                | SG2         | 600.0 |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID           | errorCode | sourceSystem | businessArea | key1 | key2               | key3      | key4 | key5 | errorValue                    |
      | SP             | OMPGdmProductLocation | J1        | omp          |              | BR02 | 000000000000000002 | CON_LATAM |      |      | Unable to find DPParentCode   |
      | SP             | OMPGdmProductLocation | J1        | omp          |              | BR05 | 000000000000000006 | CON_LATAM |      |      | Unable to find DPParentCode   |
      | SP             | OMPGdmProductLocation | C1        | omp          |              |      | 000000000000000005 | CON_LATAM |      |      | Unable to find assigned plant |
      | SP             | OMPGdmProductLocation | J1        | omp          |              | BR07 | 000000000000000009 | CON_LATAM |      |      | Unable to find DPParentCode   |
    And I delete the test data

    And I will remove all data with region "/omp/gdm_product_location"

    And I will remove all data with region "/plan/edm_failed_data"