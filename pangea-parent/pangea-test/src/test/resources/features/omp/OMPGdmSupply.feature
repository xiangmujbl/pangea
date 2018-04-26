@pangea_test @AEAZ-2776
Feature:  OMPGdmSupply-Curation

  Scenario: Full Load consumption

    Given I import "/edm/source_list_v1" by keyFields "sourceSystem,localMaterialNumber,localPlant,localNumberofSourceListRecord"
      | sourceSystem | localMaterialNumber | localPlant | localNumberofSourceListRecord | materialNumber | plant | localCreatedOn | localCreatedBy | localSourceListRecordValidFrom | localSourceListRecordValidTo | localVendorAccountNumber | localFixedvendor | localAgreementNumber | localAgreementItem | localFixedOutlinePurchaseAgreementItem | localPlantfromWhichMaterialisProcured | localMatForManufPartNumber | localBlockedSourceofSupply | localPurchasingOrganization | localPurchasingDocumentCategory | localCategoryofSourceListRecord | localSourceListUsageinMaterialsPlanning |
      | CONS_LATAM   | 1                   | BR06       | 4                             |                | AR01  | 20030306       | CCASTRO1       | 20030206                       | 29991231                     | 8917                     |                  |                      | 0                  |                                        | BR12                                  |                            |                            | BR00                        |                                 | 7                               |                                         |
      | CONS_LATAM   | 3                   | BR06       | 2                             | CONS_LATAM     | AR01  | 20020522       | CARGA03        | 20020301                       | 99991231                     | 8917                     |                  |                      | 0                  |                                        | BR12                                  |                            |                            | BR00                        |                                 | 7                               |                                         |
      | CONS_LATAM   | 3                   | BR07       | 2                             | CONS_LATAM     |       | 20020522       | JPINTO4        | 20020422                       | 29991231                     | 8917                     |                  |                      | 0                  |                                        | BR12                                  |                            |                            | BR00                        |                                 | 7                               |                                         |

    And I wait "/edm/source_list_v1" Async Queue complete

    Given I import "/edm/plant_v1" by keyFields "localPlant,sourceSystem"
      | sourceSystem | localPlant | plant |
      | CONS_LATAM   | BR06       | AR01  |
      | TQA          | BR07       | AR02  |
      | TQA          | BR08       | AR03  |

    And I wait "/edm/plant_v1" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber,sourceSystem"
      |  localMaterialNumber| sourceSystem | materialNumber |
      | [EMS]               | EMS          | EMS            |
      | 3                   | CONS_LATAM   | CONS_LATAM     |
      | 1                   | TQA          | TQA            |

    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/edm/material_plant_v1" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | materialNumber | plant | localPlantStatus | plantStatus | localProcurementType | localDeletionFlagPlant | localFixedlotsize | localMaximumLotSize | localMinimumLotSize | localRoundingvalueForPoq | localLotsize | localMrpType | localMrpController | localInHouseProcessingTime | localSafetyStock | localMinimumSafetyStock | localProductionSupervisor | localProductionUnit | localPosttoInspectionStock | localComponentScrapInPercent | localCriticalpart | localAbcIndicator | localMaximumStocklevel | localCheckingGroupforAvailabilityCheck | localPlannedDeliveryTimeInDays | localDependentRequirements | localSafetytimeIndicator | localSafetyTime | localSpecialProcurementType | localPlanningStrategyGroup | localConsumptionPeriodBackward | localConsumptionPeriodForward | localConsumptionMode | localGoodsReceiptProcessingTimeInDays | localBatchManagementRequirmentIndicator | localPlanningTimeFence | localPosttoinspstk | localComponentScrap | localBaseQuantity |
      | CONS_LATAM   | 0000000001          | BR01       | 7891010010001  | 00001 | 04               | 100         | F                    | BR01                   | 0.000             | 0.000               | 0.000               | 0.000                    | EX0          | ND           | 151                | 0                          | 0.000            | 0.000                   | 001                       | 0.000               | 0.000                      | 0.000                        | 0.000             | 0.000             | 0.000                  | 01                                     | 0                              | 0                          | 0                        | 0               | 0                           | 0                          | 0                              | 0                             | 0                    | 0                                     | 0                                       | 0                      | 0.000              | 0.000               | BA0               |
      | CONS_LATAM   | 0000000001          | BR02       | 7891010010001  | 00002 | 05               | 101         | F                    | BR02                   | 0.001             | 0.001               | 0.001               | 0.001                    | EX1          | X4           | 152                | 2                          | 0.001            | 0.001                   | 002                       | 0.001               | 0.001                      | 0.001                        | 0.001             | 0.001             | 0.001                  | 02                                     | 1                              | 1                          | 1                        | 1               | 1                           | 1                          | 1                              | 1                             | 1                    | 1                                     | 1                                       | 1                      | 0.001              | 0.001               | BA1               |
      | CONS_LATAM   | 0000000001          | BR03       | 7891010010001  |       | 06               | 102         | X                    | BR03                   | 0.002             | 0.002               | 0.002               | 0.002                    | EX2          | ND           | 153                | 1                          | 0.002            | 0.002                   | 003                       | 0.002               | 0.002                      | 0.002                        | 0.002             | 0.002             | 0.002                  | 01                                     | 2                              | 2                          | 2                        | 2               | 2                           | 2                          | 2                              | 2                             | 2                    | 2                                     | 2                                       | 2                      | 0.002              | 0.002               | BA2               |
      | CONS_LATAM   | 0000000002          | BR04       | 7891010010002  |       | 07               |             | E                    | BR04                   | 0.003             | 0.003               | 0.003               | 0.003                    | EX3          | X4           | 154                | 0                          | 0.003            | 0.003                   | 004                       | 0.003               | 0.003                      | 0.003                        | 0.003             | 0.003             | 0.003                  | 01                                     | 3                              | 3                          | 3                        | 3               | 3                           | 3                          | 3                              | 3                             | 3                    | 3                                     | 3                                       | 3                      | 0.003              | 0.003               | BA3               |
      | CONS_LATAM   | 0000000003          | BR05       |                | 00005 | 08               | 104         | E                    | BR05                   | 0.004             | 0.004               | 0.004               | 0.004                    | EX4          | ND           | 155                | 2                          | 0.004            | 0.004                   | 005                       | 0.004               | 0.004                      | 0.004                        | 0.004             | 0.004             | 0.004                  | 01                                     | 4                              | 4                          | 4                        | 4               | 4                           | 4                          | 4                              | 4                             | 4                    | 4                                     | 4                                       | 4                      | 0.004              | 0.004               | BA4               |
      | CONS_LATAM   | 0000000004          | BR06       |                |       | 09               | 105         | F                    | BR06                   | 0.005             | 0.005               | 0.005               | 0.005                    | EX5          | X4           | 156                | 3                          | 0.005            | 0.005                   | 006                       | 0.005               | 0.005                      | 0.005                        | 0.005             | 0.005             | 0.005                  | 01                                     | 5                              | 5                          | 5                        | 5               | 5                           | 5                          | 5                              | 5                             | 5                    | 5                                     | 5                                       | 5                      | 0.005              | 0.005               | BA5               |
      | CONS_LATAM   | 0000000004          | BR07       |                |       | 10               |             | X                    | BR07                   | 0.006             | 0.006               | 0.006               | 0.006                    | EX6          | ND           | 157                | 0                          | 0.006            | 0.006                   | 007                       | 0.006               | 0.006                      | 0.006                        | 0.006             | 0.006             | 0.006                  | 01                                     | 6                              | 6                          | 6                        | 6               | 6                           | 6                          | 6                              | 6                             | 6                    | 6                                     | 6                                       | 6                      | 0.006              | 0.006               | BA6               |

    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/plan/cns_material_plan_status" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | materialNumber | localParentCode | ppc | active | dpRelevant | spRelevant | parentActive | noPlanRelevant |
      | CONS_LATAM   | LT9999              | BR19       | 9862           | G3a             | G4a | X      | X          |            | X            | X              |

    And I wait "/plan/cns_material_plan_status" Async Queue complete

    Given I import "/plan/cns_process_type" by keyFields "processTypeId,processTypeDescription"
      | processTypeId | processTypeDescription |
      | 1             | Inter Plant            |

    And I wait "/plan/cns_process_type" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmSupply.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMSupply.tsv"

    And I check file data for filename "GdmSupply.tsv" by keyFields "locationTypeId,activeFctErp,activeOprErp,activeSopErp,label"
      | SupplyId              | Active | ActiveOPRERP | ActiveSOPERP | FromDate           | LABEL          | LocationId | MACHINECAPACITY | MACHINETYPEID | MaxQuantity | MaxQuantityType | MinQuantity | MinQuantityType | PLANLEVELID | Preference | PROCESSTYPEID | ProductId | PURCHASINGGROUP | PURCHASINGORGANIZATION | SupplierId | ToDate   | VENDOR | TransportType |
      | 123Cons_latamMX01V789 | Yes    | Yes          | 101217       | INTERNAL TRANSPORT | CONS_LATAMCO01 | INFINITE   | SUPPLY          | 100           |             | 20              |             |                 | *           | x          | 1             | PPC001    | U96             | NA00                   | CO01       | 12312020 | VCO01  | DEFAULT       |

    And I delete the test data

    And I will remove all data with region "/omp/gdm_location_type"

    And I will remove the test file on sink application "GDMSupply.tsv"

