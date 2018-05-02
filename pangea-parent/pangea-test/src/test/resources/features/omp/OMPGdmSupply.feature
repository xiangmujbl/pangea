@pangea_test @AEAZ-2776
Feature:  OMPGdmSupply-Curation

  Scenario: Full Load consumption

    Given I import "/edm/source_list_v1" by keyFields "sourceSystem,localMaterialNumber,localPlant,localNumberofSourceListRecord"
      | sourceSystem | localMaterialNumber | localPlant | localNumberofSourceListRecord | materialNumber | plant | localCreatedOn | localCreatedBy | localSourceListRecordValidFrom | localSourceListRecordValidTo | localVendorAccountNumber | localFixedvendor | localAgreementNumber | localAgreementItem | localFixedOutlinePurchaseAgreementItem | localPlantfromWhichMaterialisProcured | localMatForManufPartNumber | localBlockedSourceofSupply | localPurchasingOrganization | localPurchasingDocumentCategory | localCategoryofSourceListRecord | localSourceListUsageinMaterialsPlanning |
      | CONS_LATAM   | 3                   | BR06       | 4                             | 7703910        | AR01  | 20030306       | CCASTRO1       | 20030206                       | 29991231                     | 8917                     |       1          |                      | 0                  |                                        | BR12                                  |                            |                            | BR00                        |                                 | 7                               |                                         |

    And I wait "/edm/source_list_v1" Async Queue complete

    Given I import "/edm/plant_v1" by keyFields "localPlant,sourceSystem"
      | sourceSystem | localPlant | plant | localPlanningRelevant |
      | CONS_LATAM   | BR06       | AR01  |          X            |
      | TQA          | BR06       | AR02  |          X            |
      | TQA          | BR06       | AR03  |          X            |

    And I wait "/edm/plant_v1" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber,sourceSystem"
      | localMaterialNumber | materialNumber | sourceSystem | primaryPlanningCode |
      | 3                   | 7703910        | CONS_LATAM   | 7703910             |
      | 2                   | 7707777        | CONS_LATAM   | 7707777             |
      | 1                   | 7047792        | CONS_LATAM   | 7708888             |

    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/edm/material_plant_v1" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | materialNumber | plant | localPlantStatus | plantStatus | localProcurementType | localDeletionFlagPlant | localFixedlotsize | localMaximumLotSize | localMinimumLotSize | localRoundingvalueForPoq | localLotsize | localMrpType | localMrpController | localInHouseProcessingTime | localSafetyStock | localMinimumSafetyStock | localProductionSupervisor | localProductionUnit | localPosttoInspectionStock | localComponentScrapInPercent | localCriticalpart | localAbcIndicator | localMaximumStocklevel | localCheckingGroupforAvailabilityCheck | localPlannedDeliveryTimeInDays | localDependentRequirements | localSafetytimeIndicator | localSafetyTime | localSpecialProcurementType | localPlanningStrategyGroup | localConsumptionPeriodBackward | localConsumptionPeriodForward | localConsumptionMode | localGoodsReceiptProcessingTimeInDays | localBatchManagementRequirmentIndicator | localPlanningTimeFence | localPosttoinspstk | localComponentScrap | localBaseQuantity |localPurchasingGroup |
      | CONS_LATAM   | 2          | BR05       | 7703910        | AR01   | 04    | 100         | F                    | BR01                   | 0.000             | 0.000               | 0.000               | 0.000                    | EX0          | ND           | 151                | 0                          | 0.000            | 0.000                   | 001                       | 0.000               | 0.000                      | 0.000                        | 0.000             | 0.000             | 0.000                  | 01                                     | 0                              | 0                          | 0                        | 0               | 0                           | 0                          | 0                              | 0                             | 0                    | 0                                     | 0                                       | 0                      | 0.000              | 0.000               | BA0               | BR00                |
      | CONS_LATAM   | 3          | BR06       | 7703910        | AR01   | 05    | 101         | F                    | BR02                   | 0.001             | 0.001               | 0.001               | 0.001                    | EX1          | X4           | 152                | 2                          | 0.001            | 0.001                   | 002                       | 0.001               | 0.001                      | 0.001                        | 0.001             | 0.001             | 0.001                  | 02                                     | 1                              | 1                          | 1                        | 1               | 1                           | 1                          | 1                              | 1                             | 1                    | 1                                     | 1                                       | 1                      | 0.001              | 0.001               | BA1               | BR00                |
      | CONS_LATAM   | 3          | BR07       | 7703910        | AR01   | 06    | 102         | X                    | BR03                   | 0.002             | 0.002               | 0.002               | 0.002                    | EX2          | ND           | 153                | 1                          | 0.002            | 0.002                   | 003                       | 0.002               | 0.002                      | 0.002                        | 0.002             | 0.002             | 0.002                  | 01                                     | 2                              | 2                          | 2                        | 2               | 2                           | 2                          | 2                              | 2                             | 2                    | 2                                     | 2                                       | 2                      | 0.002              | 0.002               | BA2               | BR00                |
      | CONS_LATAM   | 2          | BR06       | 7703910        | AR01   | 07    |             | E                    | BR04                   | 0.003             | 0.003               | 0.003               | 0.003                    | EX3          | X4           | 154                | 0                          | 0.003            | 0.003                   | 004                       | 0.003               | 0.003                      | 0.003                        | 0.003             | 0.003             | 0.003                  | 01                                     | 3                              | 3                          | 3                        | 3               | 3                           | 3                          | 3                              | 3                             | 3                    | 3                                     | 3                                       | 3                      | 0.003              | 0.003               | BA3               | BR00                |
      | CONS_LATAM   | 1          | BR06       |                | AR01   | 08    | 104         | E                    | BR05                   | 0.004             | 0.004               | 0.004               | 0.004                    | EX4          | ND           | 155                | 2                          | 0.004            | 0.004                   | 005                       | 0.004               | 0.004                      | 0.004                        | 0.004             | 0.004             | 0.004                  | 01                                     | 4                              | 4                          | 4                        | 4               | 4                           | 4                          | 4                              | 4                             | 4                    | 4                                     | 4                                       | 4                      | 0.004              | 0.004               | BA4               | BR00                |
      | CONS_LATAM   | 4          | BR06       |                | AR01   | 09    | 105         | F                    | BR06                   | 0.005             | 0.005               | 0.005               | 0.005                    | EX5          | X4           | 156                | 3                          | 0.005            | 0.005                   | 006                       | 0.005               | 0.005                      | 0.005                        | 0.005             | 0.005             | 0.005                  | 01                                     | 5                              | 5                          | 5                        | 5               | 5                           | 5                          | 5                              | 5                             | 5                    | 5                                     | 5                                       | 5                      | 0.005              | 0.005               | BA5               | BR00                |
      | CONS_LATAM   | 4          | BR06       |                | AR01   | 10    |             | X                    | BR07                   | 0.006             | 0.006               | 0.006               | 0.006                    | EX6          | ND           | 157                | 0                          | 0.006            | 0.006                   | 007                       | 0.006               | 0.006                      | 0.006                        | 0.006             | 0.006             | 0.006                  | 01                                     | 6                              | 6                          | 6                        | 6               | 6                           | 6                          | 6                              | 6                             | 6                    | 6                                     | 6                                       | 6                      | 0.006              | 0.006               | BA6               | BR00                |

    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/plan/cns_material_plan_status" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | materialNumber    | localParentCode | ppc | active | dpRelevant | spRelevant | parentActive | noPlanRelevant |
      | CONS_LATAM   | 3                   | BR06       | 7703910           | G3a             | G4a | X      | X          |  X         | X            | X              |

    And I wait "/plan/cns_material_plan_status" Async Queue complete

    Given I import "/plan/cns_process_type" by keyFields "processTypeId,processTypeDesc"
      | processTypeId | processTypeDesc        |
      | 1             | VENDOR Transport       |
      | 1             | INTERNAL Transport     |

    And I wait "/plan/cns_process_type" Async Queue complete

    Given I import "/plan/cns_spl_pln_loc" by keyFields "localNumber"
      | localNumber | vendorOrCustomer |
      | 8917        | V                |

    And I wait "/plan/cns_spl_pln_loc" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmSupply.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMSupply.tsv"

    And I check file data for filename "GDMSupply.tsv" by keyFields "locationTypeId,activeFctErp,activeOprErp,activeSopErp,label"
      | PLANLEVELID | MinQuantityType | PROCESSTYPEID | PURCHASINGORGANIZATION | PURCHASINGGROUP | LocationId      | MACHINETYPEID | MaxQuantityType | ProductId | MinQuantity | ActiveSOPERP | SupplierId | Active | MACHINECAPACITY | Preference	| ActiveOPRERP | LABEL                 | VENDOR	| FromDate    | ToDate | MaxQuantity | TransportType | SupplyId                   |
      | *           |                 | 1             | BR00                   | BR00            | CONS_LATAM_BR06 | SUPPLY        |                 | 7703910   |    0.001    | NO           |  BR12      |   YES  | infinite        | 1           | YES          | INTERNAL Transport    | 8917   | 1970/01/01  |        | 0.0001      | Default       | 7703910CONS_LATAM_BR068917 |

    And I delete the test data

    And I will remove all data with region "/omp/gdm_supply"

    And I will remove the test file on sink application "GDMSupply.tsv"

