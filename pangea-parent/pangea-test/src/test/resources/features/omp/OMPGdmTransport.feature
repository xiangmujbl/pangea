@pangea_test @AEAZ-2778
Feature:  OMPGdmTransport
	Scenario: Full Load consumption

		Given I import "/plan/cns_tlane_item_exception" by keyFields "sequenceNumber,tlaneName"
			| validTo	| originLocation	  	| materialNumber	| processTypeId	      | validFrom | destinationLocation	  | leadTime  | mode	| sequenceNumber  | tlaneName   | deletionIndicator   | refSeqNumTlaneItem |
			| 20181231  | CONS_LATAM_V_BR07   	| 59816           	| INTERNALTRANSPORT   | 20180101  | CONS_LATAM_V_BR12     | 5         | Ship    | 2               | BR07toBR12  |                     |                    |
#			| 20181231  | CONS_LATAM_V_BR08   	| 59816           	| INTERNALTRANSPORT   | 20180101  | CONS_LATAM_V_BR13     | 5         | Ship    | 3               | BR08toBR13  | X                   |                    |
#			| 20181231  | CONS_LATAM_V_BR11   	| 59816           	| INTERNALTRANSPORT   | 20180101  | 				      | 5         | Ship    | 4               | BR11toBR13  |                     |                    |
#			| 20181231  | 					   	| 59816           	| INTERNALTRANSPORT   | 20180101  | CONS_LATAM_V_BR16     | 5         | Ship    | 5               | BR08toBR16  |                     |                    |
#			| 20181231  | CONS_LATAM_V_BR09   	| 		           	| INTERNALTRANSPORT   | 20180101  | CONS_LATAM_V_BR14     | 5         | Ship    | 6               | BR09toBR14  |                     |                    |
#			| 20181231  | CONS_LATAM_V_BR10   	| 59111		        | INTERNALTRANSPORT   | 20180101  | CONS_LATAM_V_BR15     | 5         | Ship    | 7               | BR10toBR15  |                     |                    |
#			| 20181231  | CONS_LATAM_V_BR12   	| 59817		        | INTERNALTRANSPORT   | 20180101  | CONS_LATAM_V_BR17     | 5         | Ship    | 8               | BR12toBR17  |                     |                    |
#			| 20181231  | CONS_LATAM_V_BR13   	| 59818		        | INTERNALTRANSPORT   | 20180101  | CONS_LATAM_V_BR18     | 5         | Ship    | 9               | BR13toBR18  |                     |                    |

		And I wait "/plan/cns_tlane_item_exception" Async Queue complete


		Given I import "/plan/cns_tlane_item" by keyFields "sequenceNumber,tlaneName"
			| sequenceNumber	| tlaneName		| validFrom	| validTo	| originLocation	| destinationLocation	| materialNumber	| mode	| leadTime	| processTypeId 	|
			| 1					| BR07toBR12	| 20180101	| 20181231	| CONS_LATAM_V_BR07	| CONS_LATAM_V_BR12		| 59816				| Ship	| 7			| INTERNALTRANSPORT |

		And I wait "/plan/cns_tlane_item" Async Queue complete

		Given I import "/plan/cns_process_type" by keyFields "processTypeId"
			| processTypeId 	| processTypeDesc 	|
			| INTERNALTRANSPORT | INTERNALTRANSPORT	|

		And I wait "/plan/cns_process_type" Async Queue complete

		Given I import "/edm/material_plant_v1" by keyFields "localMaterialNumber,localPlant,sourceSystem"
			| sourceSystem | localMaterialNumber  | localPlant | materialNumber | plant | localPlantStatus | plantStatus | localProcurementType | localDeletionFlagPlant | localFixedlotsize | localMaximumLotSize | localMinimumLotSize | localRoundingvalueForPoq | localLotsize | localMrpType | localMrpController | localInHouseProcessingTime | localSafetyStock | localMinimumSafetyStock | localProductionSupervisor | localProductionUnit | localPosttoInspectionStock | localComponentScrapInPercent | localCriticalpart | localAbcIndicator | localMaximumStocklevel | localCheckingGroupforAvailabilityCheck | localPlannedDeliveryTimeInDays | localDependentRequirements | localSafetytimeIndicator | localSafetyTime | localSpecialProcurementType | localPlanningStrategyGroup | localConsumptionPeriodBackward | localConsumptionPeriodForward | localConsumptionMode | localGoodsReceiptProcessingTimeInDays | localBatchManagementRequirmentIndicator | localPlanningTimeFence | localPosttoinspstk | localComponentScrap | localBaseQuantity | localPurchasingGroup	|
			| CONS_LATAM   | 59816                | BR12       | 59816          | BR12  | 07               |             | E                    | BR12                   | 0.003             | 0.003               | 0.003               | 0.003                    | EX3          | X4           | 154                | 0                          | 0.003            | 0.003                   | 004                       | 0.003               | 0.003                      | 0.003                        | 0.003             | 0.003             | 0.003                  | 01                                     | 3                              | 3                          | 3                        | 3               | 3                           | 3                          | 3                              | 3                             | 3                    | 3                                     | 3                                       | 3                      | 0.003              | 0.003               | BA3               | BA0               	|
#			| CONS_LATAM   | 59818                | BR18       | 59818          | BR18  | 07               |             | E                    | BR18                   | 0.003             | 0.003               | 0.003               | 0.003                    | EX3          | X4           | 154                | 0                          | 0.003            | 0.003                   | 004                       | 0.003               | 0.003                      | 0.003                        | 0.003             | 0.003             | 0.003                  | 01                                     | 3                              | 3                          | 3                        | 3               | 3                           | 3                          | 3                              | 3                             | 3                    | 3                                     | 3                                       | 3                      | 0.003              | 0.003               | BA3               | BA0               	|

		And I wait "/edm/material_plant_v1" Async Queue complete

		Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
			| sourceSystem | localMaterialNumber | localRefDescription                  | localMaterialType | localBaseUom | materialNumber | refDescription                           | materialType | baseUom | localDpParentCod | parentCode    | globalDpParentCode | form   | category | subBrand | brand  | franchise | globalBusinessUnit | productFamily | localManufacturingTechnology | manufacturingTechnology | localMaterialGroup | materialGroup | flagForDeletion | materialStatus | division | batchManageIndicator | minRemShelfLife | totalShelfLife | primaryPlanningCode |
			| CONS_LATAM   | 59816               | JS COTTON BALLS 50 GRX20 T50P35      | FERT              | KI           | 59816  		| JS SOFT DEO HIDR MAC PROL 12XL400P320ML  | FERT         | EA      |                  | 7891010931582 |                    | 116151 | 6        | 1V       | BRD001 | FCH001    | GFO001             | Not Assigned  |                              | Wipes                   | 01                 |               |                 | 08             | 10       | X                    | 180             | 9999           | 59816               |
#			| CONS_LATAM   | 59817               | JS COTTON BALLS 50 GRX20 T50P35      | FERT              | KI           | 59817  		| JS SOFT DEO HIDR MAC PROL 12XL400P320ML  | FERT         | EA      |                  | 7891010931582 |                    | 116151 | 6        | 1V       | BRD001 | FCH001    | GFO001             | Not Assigned  |                              | Wipes                   | 01                 |               |                 | 08             | 10       | X                    | 180             | 9999           | 59817               |
#			| CONS_LATAM   | 59818               | JS COTTON BALLS 50 GRX20 T50P35      | FERT              | KI           | 59818  		| JS SOFT DEO HIDR MAC PROL 12XL400P320ML  | FERT         | EA      |                  | 7891010931582 |                    | 116151 | 6        | 1V       | BRD001 | FCH001    | GFO001             | Not Assigned  |                              | Wipes                   | 01                 |               |                 | 08             | 10       | X                    | 180             | 9999           | 59818               |

		And I wait "/edm/material_global_v1" Async Queue complete

		Given I import "/edm/source_list_v1" by keyFields "sourceSystem,localMaterialNumber"
			| sourceSystem | localMaterialNumber | localPlant | localNumberofSourceListRecord | materialNumber	| plant | localCreatedOn | localCreatedBy | localSourceListRecordValidFrom | localSourceListRecordValidTo | localVendorAccountNumber | localFixedvendor | localAgreementNumber | localAgreementItem | localFixedOutlinePurchaseAgreementItem | localPlantfromWhichMaterialisProcured | localMatForManufPartNumber | localBlockedSourceofSupply | localPurchasingOrganization | localPurchasingDocumentCategory | localCategoryofSourceListRecord | localSourceListUsageinMaterialsPlanning |
			| CONS_LATAM   | 59816               | BR12       | 4                             | 59816           | AR01  | 20030306       | CCASTRO1       | 20030206                       | 29991231                     | 8917                     |                  |                      | 0                  |                                        | BR12                                  |                            |                            | BR00                        |                                 | 7                               |                                         |

		And I wait "/edm/source_list_v1" Async Queue complete

		Given I import "/plan/cns_spl_pln_loc" by keyFields "sourceSystem,localNumber"
			| sourceSystem	| localNumber	|
			| CONS_LATAM	| BR07			|

		And I wait "/plan/cns_spl_pln_loc" Async Queue complete

		Given I import "/plan/cns_material_plan_status" by keyFields "sourceSystem,localMaterialNumber,localPlant"
			| sourceSystem | localMaterialNumber | localPlant | materialNumber | localParentCode | ppc | active | dpRelevant | spRelevant | parentActive | noPlanRelevant |
			| CONS_LATAM   | 59816               | BR07       | 59816          | G3b             | G4b | X      | X          | X          | X            | X              |

		And I wait "/plan/cns_material_plan_status" Async Queue complete

		Given I import "/edm/plant_v1" by keyFields "sourceSystem,localPlant"
			| sourceSystem | localPlant | plant | localPlanningRelevant |
			| CONS_LATAM   | BR12       | BR12  | X						|

		And I wait "/edm/plant_v1" Async Queue complete

		And I will remove the test file on sink application "GDMTransportMergeException.tsv"

		When I submit task with xml file "xml/omp/OMPGdmTransportException.xml" and execute file "jar/pangea-view.jar"

		Then A file is found on sink application with name "GDMTransportMergeException.tsv"

		And I check file data for filename "GDMTransportMergeException.tsv" by keyFields "machineTypeId, processTypeId, transportId, requirementOffset, purchasingOrganization, active, vendorId, purchasingGroup, label, toLocationId, fromLocationId, planLevelId, activeOPRERP, activeSOPERP, minQuantity, transportOffset, toProductId, transportType, endEff, fromProductId, startEff"
			| machineTypeId	| processTypeId		| transportId								| requirementOffset	| purchasingOrganization	| active	| vendorId	| purchasingGroup	| label				| toLocationId		| fromLocationId	| planLevelId	| activeOPRERP	| activeSOPERP	| minQuantity	| transportOffset	| toProductId	| transportType	| endEff	| fromProductId	| startEff	|
			| TRANSPORT		| INTERNALTRANSPORT	| 59816/CONS_LATAM_V_BR07/CONS_LATAM_V_BR12	| 					| BR00						| YES		| 8917		| BA0				| INTERNALTRANSPORT	| CONS_LATAM_V_BR12	| CONS_LATAM_V_BR07	| *				| YES			| NO			| 				| 5					| 59816			| Ship			| 20181231	| 59816			| 20180101	|

		Then I check region data "/dev/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
			| key1			| key2	| businessArea	| key5	| functionalArea	| key3	| sourceSystem	| errorValue															| key4	| errorCode	| interfaceID		|
#			| CONS_LATAM	| BR08	| 				| 		| SP				| 59816	| CONS_LATAM	| Deletion Indicator not Blank											| 		| N1		| OMPGdmTransport	|
#			| CONS_LATAM	| BR09	| 				| 		| SP				| 		| CONS_LATAM	| Material Number null in CNS Tlane Item Exception 						| 		| setup		| OMPGdmTransport	|
#			| CONS_LATAM	| BR10	| 				| 		| SP				| 59111	| CONS_LATAM	| Material number not found in MaterialGlobalV1				 			| 		| N8		| OMPGdmTransport	|
##			| 				| 		| 				| 		| SP				| 59816	| 				| Origin Location null in CNS Tlane Item Exception 						| 		| N/A		| OMPGdmTransport	|
#			| CONS_LATAM	| BR11	| 				| 		| SP				| 59816	| CONS_LATAM	| Destination Location is Empty. Can't generate source system or plant. | 		| N8		| OMPGdmTransport	|
##			| CONS_LATAM	| BR12	| 				| 		| SP				| 59817	| CONS_LATAM	| No Matching Record found in EDMMaterialPlantV1Entity	 				| 		| N8		| OMPGdmTransport	|
#			| CONS_LATAM	| BR13	| 				| 		| SP				| 59818	| CONS_LATAM	| No Matching Record found in EDMSourceListV1Entity		 				| 		| N9_N10	| OMPGdmTransport	|

		And I will remove all data with region "/dev/plan/edm_failed_data"

		And I will remove the test file on sink application "GDMTransportMergeMain.tsv"

		When I submit task with xml file "xml/omp/OMPGdmTransport.xml" and execute file "jar/pangea-view.jar"

		Then A file is found on sink application with name "GDMTransportMergeMain.tsv"

		And I check file data for filename "GDMTransportMergeMain.tsv" by keyFields "machineTypeId, processTypeId, transportId, requirementOffset, purchasingOrganization, active, vendorId, purchasingGroup, label, toLocationId, fromLocationId, planLevelId, activeOPRERP, activeSOPERP, minQuantity, transportOffset, toProductId, transportType, endEff, fromProductId, startEff"
			| machineTypeId	| processTypeId		| transportId								| requirementOffset	| purchasingOrganization	| active	| vendorId			| purchasingGroup	| label				| toLocationId		| fromLocationId	| planLevelId	| activeOPRERP	| activeSOPERP	| minQuantity	| transportOffset	| toProductId	| transportType	| endEff	| fromProductId	| startEff	|
			| TRANSPORT		| INTERNALTRANSPORT	| 59816/CONS_LATAM_V_BR07/CONS_LATAM_V_BR12	| 					| BR00						| YES		| 8917				| BA0				| INTERNALTRANSPORT	| CONS_LATAM_V_BR12	| CONS_LATAM_V_BR07	| *				| YES			| NO			| 				| 7					| 59816			| Ship			| 20181231	| 59816			| 20180101	|

		Then I check region data "/dev/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
			| key1			| key2	| businessArea	| key5	| functionalArea	| key3	| sourceSystem	| errorValue				| key4	| errorCode	| interfaceID		|

		And I will remove all data with region "/dev/plan/edm_failed_data"

		And I execute xd job to merge file "GDMTransportMerge*" to "GDMTransport.tsv" by keyFields "machineTypeId, processTypeId, transportId, requirementOffset, purchasingOrganization, active, vendorId, purchasingGroup, label, toLocationId, fromLocationId, planLevelId, activeOPRERP, activeSOPERP, minQuantity, transportOffset, toProductId, transportType, endEff, fromProductId, startEff"

		And I check file data for filename "GDMTransport.tsv" by keyFields "machineTypeId, processTypeId, transportId, requirementOffset, purchasingOrganization, active, vendorId, purchasingGroup, label, toLocationId, fromLocationId, planLevelId, activeOPRERP, activeSOPERP, minQuantity, transportOffset, toProductId, transportType, endEff, fromProductId, startEff"
			| machineTypeId	| processTypeId		| transportId								| requirementOffset	| purchasingOrganization	| active	| vendorId			| purchasingGroup	| label				| toLocationId		| fromLocationId	| planLevelId	| activeOPRERP	| activeSOPERP	| minQuantity	| transportOffset	| toProductId	| transportType	| endEff	| fromProductId	| startEff	|
			| TRANSPORT		| INTERNALTRANSPORT	| 59816/CONS_LATAM_V_BR07/CONS_LATAM_V_BR12	| 					| BR00						| YES		| 8917				| BA0				| INTERNALTRANSPORT	| CONS_LATAM_V_BR12	| CONS_LATAM_V_BR07	| *				| YES			| NO			| 				| 5					| 59816			| Ship			| 20181231	| 59816			| 20180101	|
			| TRANSPORT		| INTERNALTRANSPORT	| 59816/CONS_LATAM_V_BR07/CONS_LATAM_V_BR12	| 					| BR00						| YES		| 8917				| BA0				| INTERNALTRANSPORT	| CONS_LATAM_V_BR12	| CONS_LATAM_V_BR07	| *				| YES			| NO			| 				| 7					| 59816			| Ship			| 20181231	| 59816			| 20180101	|

		And I delete the test data

		And I will remove all data with region "/omp/gdm_transport"
		And I will remove all data with region "/plan/cns_tlane_item"
		And I will remove all data with region "/plan/cns_tlane_item_exception"
		And I will remove all data with region "/plan/cns_process_type"
		And I will remove all data with region "/plan/cns_material_plan_status"
		And I will remove all data with region "/plan/cns_spl_pln_loc"
		And I will remove all data with region "/edm/plant_v1"
		And I will remove all data with region "/edm/source_list_v1"
		And I will remove all data with region "/edm/material_global_v1"
		And I will remove all data with region "/edm/material_plant_v1"

		And I will remove the test file on sink application "GDMTransportMergeException.tsv"
		And I will remove the test file on sink application "GDMTransportMergeMain.tsv"

