@pangea_test @AEAZ-2776
Feature:  OMPGdmSupply-Curation

  Scenario: Full Load consumption

    Given I import "/edm/source_list_v1" by keyFields "sourceSystem,localMaterialNumber,localPlant,localNumberofSourceListRecord"
      | sourceSystem | localMaterialNumber | localPlant | localNumberofSourceListRecord | materialNumber | plant | localCreatedOn | localCreatedBy | localSourceListRecordValidFrom | localSourceListRecordValidTo | localVendorAccountNumber | localFixedvendor | localAgreementNumber | localAgreementItem | localFixedOutlinePurchaseAgreementItem | localPlantfromWhichMaterialisProcured | localMatForManufPartNumber | localBlockedSourceofSupply | localPurchasingOrganization | localPurchasingDocumentCategory | localCategoryofSourceListRecord | localSourceListUsageinMaterialsPlanning |
      | CONS_LATAM   | 3                   | MX02       | 4                             | 51178          | AR01  | 20030306       | CCASTRO1       | 20120718                       | 99991230                     | 6109                     |                  |                      | 0                  |                                        | MX01                                  |                            |                            | MX01                        |                                 | 7                               |                                         |
      | CONS_LATAM   | 4                   | BR16       | 5                             | 945            | AR01  | 20030306       | CCASTRO1       | 20150731                       | 99991230                     | 2519                     |                  |                      | 0                  |                                        | BR00                                  |                            |                            | BR00                        |                                 | 8                               |                                         |
      | CONS_LATAM   | 5                   | BR06       | 6                             | 71325          | AR01  | 20030306       | CCASTRO1       | 20070108                       | 29991231                     | 8917                     |                  |                      | 0                  |                                        | BR00                                  |                            |                            | BR00                        |                                 | 9                               |                                         |
      | CONS_LATAM   | 6                   | VE01       | 3                             | 1331           | AR01  | 20030306       | CCASTRO1       | 20060910                       | 29991231                     | 8917                     |                  |                      | 0                  |                                        | VE00                                  |                            |                            | VE00                        |                                 | 4                               |                                         |
      | CONS_LATAM   | 7                   | MX02       | 4                             | 92129          | AR01  | 20030306       | CCASTRO1       | 20090508                       | 99991230                     | 6359                     |                  |                      | 0                  |                                        | MX01                                  |                            |                            | MX01                        |                                 | 5                               |                                         |

#
#  Yes	51178CONS_LATAM_MX02_6109	Yes	Yes	Yes	20120718	VENDORTRANSPORT	CONS_LATAM_MX02	INFINITE	SUPPLY	0		0		*	X	VendorTransport	51178	 	MX01	CONS_LATAM_MX02	          99991230	6109	DEFAULT
#  Yes	945CONS_LATAM_BR16_2519	    Yes	Yes	Yes	20150731	VENDORTRANSPORT	CONS_LATAM_BR16	INFINITE	SUPPLY	0		0		*	X	VendorTransport	945		    BR00	CONS_LATAM_V_0000002519	  99991230	2519	DEFAULT
#  No	71326CONS_LATAM_BR06_8917	Yes	Yes	Yes	20070108	VENDORTRANSPORT	CONS_LATAM_BR06	INFINITE	SUPPLY	0		0		*	X	VendorTransport	71325		BR00	CONS_LATAM_BR06	          29991231	8917	DEFAULT
#  No	1331CONS_LATAM_VE01_8917	Yes	Yes	Yes	20060910	VENDORTRANSPORT	CONS_LATAM_VE01	INFINITE	SUPPLY	0		0		*	X	VendorTransport	1331		VE00	CONS_LATAM_VE01	          29991231	8917	DEFAULT
#  No	92129CONS_LATAM_MX02_6359	Yes	Yes	Yes	20090508	VENDORTRANSPORT	CONS_LATAM_MX02	INFINITE	SUPPLY	0		0		*	X	VendorTransport	92129	 	MX01	CONS_LATAM_MX02	          99991230	6359	DEFAULT

    And I wait "/edm/source_list_v1" Async Queue complete

    Given I import "/edm/plant_v1" by keyFields "localPlant,sourceSystem"
      | sourceSystem | localPlant | plant | localPlanningRelevant |
      | CONS_LATAM   | MX02       | AR01  |          X            |
      | CONS_LATAM   | BR16       | AR01  |          X            |
      | CONS_LATAM   | BR06       | AR01  |          X            |
      | CONS_LATAM   | VE01       | AR01  |          X            |
      | CONS_LATAM   | MX02       | AR01  |          X            |

    And I wait "/edm/plant_v1" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber,sourceSystem"
      | localMaterialNumber | materialNumber | sourceSystem | primaryPlanningCode |
      | 3                   | 51178          | CONS_LATAM   | 51178               |
      | 4                   | 945            | CONS_LATAM   | 945                 |
      | 5                   | 71325          | CONS_LATAM   | 71325               |
      | 6                   | 1331           | CONS_LATAM   | 1331                |
      | 7                   | 92129          | CONS_LATAM   | 92129               |

    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/edm/material_plant_v1" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | materialNumber | plant  | localPlantStatus | plantStatus | localProcurementType | localDeletionFlagPlant | localFixedlotsize | localMaximumLotSize | localMinimumLotSize | localRoundingvalueForPoq | localLotsize | localMrpType | localMrpController | localInHouseProcessingTime | localSafetyStock | localMinimumSafetyStock | localProductionSupervisor | localProductionUnit | localPosttoInspectionStock | localComponentScrapInPercent | localCriticalpart | localAbcIndicator | localMaximumStocklevel | localCheckingGroupforAvailabilityCheck | localPlannedDeliveryTimeInDays | localDependentRequirements | localSafetytimeIndicator | localSafetyTime | localSpecialProcurementType | localPlanningStrategyGroup | localConsumptionPeriodBackward | localConsumptionPeriodForward | localConsumptionMode | localGoodsReceiptProcessingTimeInDays | localBatchManagementRequirmentIndicator | localPlanningTimeFence | localPosttoinspstk | localComponentScrap | localBaseQuantity |localPurchasingGroup |
      | CONS_LATAM   | 3                   | MX02       | 7703910        | AR01   | 05               | 101         | F                    | BR02                   | 0.001             | 0                   | 0                   | 0.001                    | EX1          | X4           | 152                | 2                          | 0.001            | 0.001                   | 002                       | 0.001               | 0.001                      | 0.001                        | 0.001             | 0.001             | 0.001                  | 02                                     | 1                              | 1                          | 1                        | 1               | 1                           | 1                          | 1                              | 1                             | 1                    | 1                                     | 1                                       | 1                      | 0.001              | 0.001               | BA1               | MX01                |
      | CONS_LATAM   | 4                   | BR16       | 7703911        | AR01   | 05               | 101         | F                    | BR02                   | 0.001             | 0                   | 0                   | 0.001                    | EX1          | X4           | 152                | 2                          | 0.001            | 0.001                   | 002                       | 0.001               | 0.001                      | 0.001                        | 0.001             | 0.001             | 0.001                  | 02                                     | 1                              | 1                          | 1                        | 1               | 1                           | 1                          | 1                              | 1                             | 1                    | 1                                     | 1                                       | 1                      | 0.001              | 0.001               | BA1               | BR00                |
      | CONS_LATAM   | 5                   | BR06       | 7703912        | AR01   | 05               | 101         | F                    | BR02                   | 0.001             | 0                   | 0                   | 0.001                    | EX1          | X4           | 152                | 2                          | 0.001            | 0.001                   | 002                       | 0.001               | 0.001                      | 0.001                        | 0.001             | 0.001             | 0.001                  | 02                                     | 1                              | 1                          | 1                        | 1               | 1                           | 1                          | 1                              | 1                             | 1                    | 1                                     | 1                                       | 1                      | 0.001              | 0.001               | BA1               | BR00                |
      | CONS_LATAM   | 6                   | VE01       | 7703913        | AR01   | 05               | 101         | F                    | BR02                   | 0.001             | 0                   | 0                   | 0.001                    | EX1          | X4           | 152                | 2                          | 0.001            | 0.001                   | 002                       | 0.001               | 0.001                      | 0.001                        | 0.001             | 0.001             | 0.001                  | 02                                     | 1                              | 1                          | 1                        | 1               | 1                           | 1                          | 1                              | 1                             | 1                    | 1                                     | 1                                       | 1                      | 0.001              | 0.001               | BA1               | VE00                |
      | CONS_LATAM   | 7                   | MX02       | 7703914        | AR01   | 05               | 101         | F                    | BR02                   | 0.001             | 0                   | 0                   | 0.001                    | EX1          | X4           | 152                | 2                          | 0.001            | 0.001                   | 002                       | 0.001               | 0.001                      | 0.001                        | 0.001             | 0.001             | 0.001                  | 02                                     | 1                              | 1                          | 1                        | 1               | 1                           | 1                          | 1                              | 1                             | 1                    | 1                                     | 1                                       | 1                      | 0.001              | 0.001               | BA1               | MX01                |

    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/plan/cns_material_plan_status" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | materialNumber    | localParentCode | ppc | active | dpRelevant | spRelevant | parentActive | noPlanRelevant |
      | CONS_LATAM   | 3                   | MX02       | 7703911           | G3a             | G4a | X      | X          |  X         | X            | X              |
      | CONS_LATAM   | 4                   | BR16       | 7703912           | G3a             | G4a | X      | X          |  X         | X            | X              |
      | CONS_LATAM   | 5                   | BR06       | 7703913           | G3a             | G4a | X      | X          |  X         | X            | X              |
      | CONS_LATAM   | 6                   | VE01       | 7703914           | G3a             | G4a | X      | X          |  X         | X            | X              |
      | CONS_LATAM   | 7                   | MX02       | 7703915           | G3a             | G4a | X      | X          |  X         | X            | X              |

    And I wait "/plan/cns_material_plan_status" Async Queue complete

    Given I import "/plan/cns_process_type" by keyFields "processTypeId,processTypeDesc"
      | processTypeId | processTypeDesc       |
      | 1             | VENDORTRANSPORT       |

    And I wait "/plan/cns_process_type" Async Queue complete

    Given I import "/plan/cns_spl_pln_loc" by keyFields "localNumber"
      | localNumber | vendorOrCustomer | sourceSystem |
      | 6109        | V                | CONS_LATAM   |
      | 2519        | V                | CONS_LATAM   |
      | 8917        | V                | CONS_LATAM   |
      | 8917        | V                | CONS_LATAM   |
      | 6359        | V                | CONS_LATAM   |

    And I wait "/plan/cns_spl_pln_loc" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmSupply.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMSupply.tsv"

    And I check file data for filename "GDMSupply.tsv" by keyFields "locationTypeId,activeFctErp,activeOprErp,activeSopErp,label"
      | PLANLEVELID | MinQuantityType | PROCESSTYPEID   | PURCHASINGORGANIZATION | PURCHASINGGROUP | LocationId      | MACHINETYPEID | MaxQuantityType | ProductId | MinQuantity | ActiveSOPERP | SupplierId                | Active | MACHINECAPACITY | Preference	  | ActiveOPRERP | LABEL                 | VENDOR	| FromDate      | ToDate     | MaxQuantity | TransportType | SupplyId                   |
      | *           |                 | VendorTransport | MX01                   | MX01            | CONS_LATAM_MX02 | SUPPLY        |                 |  92129    |     0       | NO           | CONS_LATAM_V_6359         |   YES  |  INFINITE       | 1            | YES          | VENDORTRANSPORT       | 6359   | 2009/05/08    | 9999/12/30 |    0        | DEFAULT       | 92129CONS_LATAM_MX02_6359  |
      | *           |                 | VendorTransport | BR00                   | BR00            | CONS_LATAM_BR06 | SUPPLY        |                 |  71325    |     0       | NO           | CONS_LATAM_V_8917         |   YES  |  INFINITE       | 1            | YES          | VENDORTRANSPORT       | 8917   | 2007/01/08    | 2999/12/31 |    0        | DEFAULT       | 71325CONS_LATAM_BR06_8917    |
      | *           |                 | VendorTransport | BR00                   | BR00            | CONS_LATAM_BR16 | SUPPLY        |                 |  945      |     0       | NO           | CONS_LATAM_V_2519         |   YES  |  INFINITE       | 1            | YES          | VENDORTRANSPORT       | 2519   | 2015/07/31    | 9999/12/30 |    0        | DEFAULT       | 945CONS_LATAM_BR16_2519  |
      | *           |                 | VendorTransport | MX01                   | MX01            | CONS_LATAM_MX02 | SUPPLY        |                 |  51178    |     0       | NO           | CONS_LATAM_V_6109         |   YES  |  INFINITE       | 1            | YES          | VENDORTRANSPORT       | 6109   | 2012/07/18    | 9999/12/30 |    0        | DEFAULT       | 51178CONS_LATAM_MX02_6109   |
      | *           |                 | VendorTransport | VE00                   | VE00            | CONS_LATAM_VE01 | SUPPLY        |                 |  1331     |     0       | NO           | CONS_LATAM_V_8917         |   YES  |  INFINITE       | 1            | YES          | VENDORTRANSPORT       | 8917   | 2006/09/10    | 2999/12/31 |    0        | DEFAULT       | 1331CONS_LATAM_VE01_8917  |


    And I will remove all data with region "/edm/source_list_v1"

    And I will remove all data with region "/edm/plant_v1"

    And I will remove all data with region "/edm/material_global_v1"

    And I will remove all data with region "/edm/material_plant_v1"

    And I will remove all data with region "/plan/cns_material_plan_status"

    And I will remove all data with region "/plan/cns_process_type"

    And I will remove all data with region "/plan/cns_spl_pln_loc"

    And I will remove the test file on sink application "GDMSupply.tsv"

