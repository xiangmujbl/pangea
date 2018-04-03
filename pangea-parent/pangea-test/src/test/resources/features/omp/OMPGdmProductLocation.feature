@pangea_test @AEAZ-1979
Feature: OMPGdmProductLocation-Curation AEAZ-1979

  Scenario: Full Load curation

    Given I import "/edm/material_plant_v1" by keyFields "localPlant,localMaterialNumber,sourceSystem"
      | localMaterialNumber | localPlant | sourceSystem | localProcurementType | localAbcIndicator | localSpecialProcurementType | localPlanningStrategyGroup | localConsumptionMode | localLotSize | localBaseQuantity | localFixedlotsize | localMaximumLotSize | localMinimumLotSize | localRoundingvalueforpurchaseorderquantity | localMRPType | localMRPController | localInHouseProcessingTime | localSafetyStock | localMinimumSafetyStock | localProductionSupervisor | localPlanningTimeFence | localProductionUnit | localPosttoInspectionStock | localComponentscrapinpercent | localCriticalpart | localPlannedDeliveryTimeinDays | localMaximumstocklevel | localPlantStatus | localCheckingGroupforAvailabilityCheck | localInstalledReplenishmentLotSize | localDependentrequirements | localSafetytimeindicator | localSafetytime | localConsumptionperiodBackward | localConsumptionperiodForward | localGoodsReceiptProcessingTimeinDays | localBatchmanagementrequirmentindicator |
      |000000000000000001   |BR02        |[Consumer LATAM]|F                   |C                  |30                           |40                          |2                     |EX            |0.000              |0.000              |0.000                |0.000                |                                            |PD            |606                 |1                           |0.000             |0.000                    |007                        |000                     |                     |                            |0.00                          |                   |0                               |0.000                   |05                |02                                      |                                    |2                           |                          |00               |045                             |007                            |0                                      |X                                        |
    And I wait "/edm/material_plant_v1" Async Queue complete

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      |sourceSystem|localSourceSystem|
      |CON_LATAM   |[Consumer LATAM] |
    And I wait "/edm/source_system_v1" Async Queue complete

    Given I import "/plan/cons_time_dep_xchange" by keyFields "uniqueId"
      |preference|unitId|endEff|factor|uniqueId|startEff|
      |100       |BRL   |2018/12/31|300|1      |2017/12/31|
      |100       |VEF   |2019/1/1|700|2      |2017/12/31|
      |100       |UYU   |2019/1/1|28.90172958|2      |2017/12/31|
    And I wait "/plan/cons_time_dep_xchange" Async Queue complete


    Given I import "/edm/plant_v1" by keyFields "sourceSystem,localPlant"
    |localPlant|localCurrency|sourceSystem|
    |BR02      | BRL           |[Consumer LATAM]|
    And I wait "/edm/plant_v1" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | sourceSystem | localMaterialNumber | minRemShelfLife | localRefDescription | localMaterialType | totalShelfLife |primaryPlanningCode|localDPParentCode|
      |[Consumer LATAM]|000000000000000001 |180              |JS COTTON BALLS 50 GRX20 T50P35|FERT     |1825            |-                  |                 |
    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/edm/material_plant_fin_v1" by keyFields "localMaterialNumber"
      | localMaterialNumber | localPlant | priceControl | localStandardPrice | localPriceUnit | localMovingAverage | localPriceControlIndicator |localMvp|
      |	000000000000000001  |BR02        |    v          |       300             |                |                    |                            |   300     |
    And I wait "/edm/material_plant_fin_v1" Async Queue complete

    Given I import "/plan/cns_spl_proc_typ" by keyFields "sourceSystem"
      | specialProcurementType | splProcType | localSplProcType | sourceSystem |
      |                        |             | 30               |[Consumer LATAM]|
    And I wait "/plan/cns_spl_proc_typ" Async Queue complete

    Given I import "/plan/cns_prod_loc_attrib" by keyFields "localMaterialNumber,localPlant"
      | localMaterialNumber | localPlant | supplyGroup | minMinshelfLife | minShelfLife |
      |000000000000000001   |BR02        |SG1          |                 |              |
    And I wait "/plan/cns_prod_loc_attrib" Async Queue complete

    Given I import "/plan/cns_proc_type" by keyFields "localProcurementType,sourceSystem,procurementType"
      | procurementType | localProcurementType | sourceSystem |
      |PE               |F                    |[Consumer LATAM]|
    And I wait "/plan/cns_proc_type" Async Queue complete

    Given I import "/plan/cns_material_plan_status" by keyFields "localMaterialNumber,localPlant"
      | localMaterialNumber | localPlant | dpRelevant | noPlanRelevant | spRelevant | activeStat |
      |000000000000000001   |BR02        |-           |X               |-           |-           |
    And I wait "/plan/cns_material_plan_status" Async Queue complete

    Given I import "/plan/cns_plng_strat_grp" by keyFields "sourceSystem"
      | sourceSystem | localPlanStratGrp | planStratGrp |
      |[Consumer LATAM]|40              |              |
    And I wait "/plan/cns_plng_strat_grp" Async Queue complete

    Given I import "/plan/cns_lot_size_key_trans" by keyFields "sourceSystem"
      | lotSizeKey | localLotSizeKey | sourceSystem |
      |            |    EX             |[Consumer LATAM]|
    And I wait "/plan/cns_lot_size_key_trans" Async Queue complete

    Given I import "/plan/cns_con_mode" by keyFields "sourceSystem"
      | localConsumptionMode | sourceSystem |
      |  2                    |[Consumer LATAM]|
    And I wait "/plan/cns_con_mode" Async Queue complete

    Given I import "/plan/cns_abc_ind" by keyFields "sourceSystem"
      | indicator | localIndicator | sourceSystem |
      |           |     C          |[Consumer LATAM]|
    And I wait "/plan/cns_abc_ind" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmProductLocation.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/omp/gdm_product_location" by keyFields "productId"
     | productId | active | activeFCTERP | activeOPRERP | activeSOPERP | basmg | beskz | bstfe | bstma | bstmi | bstrf | cost | disls | dismm | dispo | dzeit | eisbe | eislo | fevor | fixedhorizon | frtme | insmk | ItemPlanningCategory | kausf | kzkri | lABEL | leadTime | locationId | maabc | mabst | minmrsl | minremainingshelflife | mmsta | mtvfp | peinh | plifz | productTypeId | replenishmentLotsize | sbdkz | shelflifeproducttypeid | shflg | shzet | sobsl | stprs | strgr | supplyGroup | supplySource | totalshelflife | verpr | vint1 | vint2 | vprsv | vrmod | webaz | xchpf |
     |FERT       |YES     |-             |-             |-             |0.000  |PE     |0.000  |0.000  |0.000  |       |-     |       |-      |606    |1      |0.000  |	0.000 |007    |000           |       |       |- |0.00   |   |JS COTTON BALLS 50 GRX20 T50P35|0|[Consumer LATAM]_BR02|-      |0.000  |         |180                    |05     |	02    |-      |0      |-              |                      |	2     |-                      |        |00     |       |300    |       |-            |-             |1825            |       |045    |007    |v      |2      |0      |X      |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
       | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/edm/material_plant_v1" and "/omp/gdm_product_location,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/omp/gdm_product_location"

    And I will remove all data with region "/edm/material_plant_v1"

