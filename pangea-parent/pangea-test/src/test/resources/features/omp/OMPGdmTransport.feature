@pangea_test @AEAZ-2778
Feature:  OMPGdmTransport
	Scenario: Full Load consumption

		Given I import "/plan/cns_tlane_item_exception" by keyFields "sequenceNumber,tlaneName,materialNumber"
#			| validTo	| originLocation	  	| materialNumber	| processTypeId	      | validFrom | destinationLocation	  | leadTime  | mode	| sequenceNumber  | tlaneName   | deletionIndicator   | refSeqNumTlaneItem |
#			| 20181231  | CONS_LATAM_V_BR07   	| 59816           	| INTERNALTRANSPORT   | 20180101  | CONS_LATAM_V_BR12     | 5         | Ship    | 2               | BR07toBR12  |                     |                    |
#			| 20181231  | CONS_LATAM_V_BR08   	| 59816           	| INTERNALTRANSPORT   | 20180101  | CONS_LATAM_V_BR13     | 5         | Ship    | 3               | BR08toBR13  | X                   |                    |
#			| 20181231  | CONS_LATAM_V_BR11   	| 59816           	| INTERNALTRANSPORT   | 20180101  | 				      | 5         | Ship    | 4               | BR11toBR13  |                     |                    |
#			| 20181231  | 					   	| 59816           	| INTERNALTRANSPORT   | 20180101  | CONS_LATAM_V_BR16     | 5         | Ship    | 5               | BR08toBR16  |                     |                    |
#			| 20181231  | CONS_LATAM_V_BR09   	| 		           	| INTERNALTRANSPORT   | 20180101  | CONS_LATAM_V_BR14     | 5         | Ship    | 6               | BR09toBR14  |                     |                    |
#			| 20181231  | CONS_LATAM_V_BR10   	| 59111		        | INTERNALTRANSPORT   | 20180101  | CONS_LATAM_V_BR15     | 5         | Ship    | 7               | BR10toBR15  |                     |                    |
#			| 20181231  | CONS_LATAM_V_BR12   	| 59817		        | INTERNALTRANSPORT   | 20180101  | CONS_LATAM_V_BR17     | 5         | Ship    | 8               | BR12toBR17  |                     |                    |
#			| 20181231  | CONS_LATAM_V_BR13   	| 59818		        | INTERNALTRANSPORT   | 20180101  | CONS_LATAM_V_BR18     | 5         | Ship    | 9               | BR13toBR18  |                     |                    |

#			| sequenceNumber	| tlaneName		| materialNumber	| validFrom	| validTo	| originLocation	| destinationLocation	| mode	| leadTime	| deletionIndicator	| processTypeId		| refSeqNumTlaneItem	|
#			| 1					| CO01toBR12	| 59816				| 20180101	| 20181231	| CONS_LATAM_CO01	| CONS_LATAM_BR12		| Ship	| 77		| 					| InternalTransport	| 						|
#			| 2					| BR12toBR07	| 60109				| 20180101	| 20181231	| CONS_LATAM_BR12	| CONS_LATAM_BR07		| Ship	| 7			| X					| InternalTransport	| 1						|
#			| 3					| BR12toBR07	| 60109				| 20180101	| 20181231	| CONS_LATAM_BR12	| CONS_LATAM_BR07		| Air	| 2			|  					| InternalTransport	|  						|
#			| 4					| BR12toBR25	| 441526			| 20180101	| 20181231	| CONS_LATAM_BR12	| CONS_LATAM_BR25		| Ship	| 4			| 					| InternalTransport	|                       |

			| sequenceNumber	| tlaneName		| materialNumber	| validFrom	| validTo	| originLocation	| destinationLocation	| mode	| leadTime	| deletionIndicator	| processTypeId		| refSeqNumTlaneItem	|
			| 100				| CO01toBR12	| 59816				| 20180101	| 20181231	| CONS_LATAM_CO01	| CONS_LATAM_BR12		| Ship	| 77		| 					| InternalTransport	| 						|
			| 1					| BR12toBR07	| 60109				| 20180101	| 20181231	| CONS_LATAM_BR12	| CONS_LATAM_BR07		| Ship	| 7			| X					| InternalTransport	| 1						|
			| 1					| BR12toBR07	| 60109				| 20180101	| 20181231	| CONS_LATAM_BR12	| CONS_LATAM_BR07		| Air	| 2			|  					| InternalTransport	|  						|
			| 400				| BR12toBR25	| 441526			| 20180101	| 20181231	| CONS_LATAM_BR12	| CONS_LATAM_BR25		| Ship	| 4			| 					| InternalTransport	| 						|

		And I wait "/plan/cns_tlane_item_exception" Async Queue complete

		Given I import "/plan/cns_tlane_item" by keyFields "sequenceNumber,tlaneName,materialNumber"
#			| sequenceNumber	| tlaneName		| validFrom	| validTo	| originLocation	| destinationLocation	| materialNumber	| mode	| leadTime	| processTypeId 	|
#			| 1					| BR07toBR12	| 20180101	| 20181231	| CONS_LATAM_V_BR07	| CONS_LATAM_V_BR12		| 59816				| Ship	| 7			| INTERNALTRANSPORT |

#			| sequenceNumber	| tlaneName		| materialNumber	| validFrom	| validTo	| originLocation		| destinationLocation	| mode	| leadTime	| processTypeId				|
#			| 1					| BR12toBR07	| 60109				| 20180101	| 20181231	| CONS_LATAM_BR12		| CONS_LATAM_BR07		| Ship	| 7			| InternalTransport			|
#			| 2					| BR12toBR26	| 441526			| 20180101	| 20181231	| CONS_LATAM_BR12		| CONS_LATAM_BR25		| Ship	| 4			| InternalTransport			|
#			| 3					| BR07toBR12	| 59816				| 20180101	| 20181231	| CONS_LATAM_BR07		| CONS_LATAM_BR12		| Ship	| 55		| InternalTransport			|
#			| 4					| CO01toPE01	| 88382				| 20180101	| 99991231	| CONS_LATAM_CO01		| CONS_LATAM_PE01		| Ship	| 28		| InternalTransport			|
#			| 5					| AR01toAR02	| 69056				| 20180101	| 20181231	| CONS_LATAM_AR01		| CONS_LATAM_AR02		| Ship	| 1			| InternalTransport			|
#			| 26				| 15574toCO01	| 93937				| 20180101	| 99991231	| CONS_LATAM_V_15574	| CONS_LATAM_CO01		| Ship	| 28		| SubcontractingTransport	|
#			| 27				| 50161toCO01	| 93616				| 20180101	| 99991231	| CONS_LATAM_V_50161	| CONS_LATAM_CO01		| Ship	| 35		| VendorTransport			|

			| sequenceNumber	| tlaneName		| materialNumber	| validFrom	| validTo	| originLocation		| destinationLocation	| mode	| leadTime	| processTypeId				|
			|  1				| BR12toBR07	| 60109				| 20180101	| 20181231	| CONS_LATAM_BR12		| CONS_LATAM_BR07		| Ship	| 7			| InternalTransport			|
			|  2				| BR12toBR26	| 441526			| 20180101	| 20181231	| CONS_LATAM_BR12		| CONS_LATAM_BR25		| Ship	| 4			| InternalTransport			|
			|  3				| BR07toBR12	| 59816				| 20180101	| 20181231	| CONS_LATAM_BR07		| CONS_LATAM_BR12		| Ship	| 77		| InternalTransport			|
			|  4				| CO01toPE01	| 88382				| 20180101	| 99991231	| CONS_LATAM_CO01		| CONS_LATAM_PE01		| Ship	| 28		| InternalTransport			|
			|  5				| AR01toAR02	| 69056				| 20180101	| 99991231	| CONS_LATAM_AR01		| CONS_LATAM_AR02		| Ship	| 1			| InternalTransport			|
			|  26				| 15574toCO01	| 93937				| 20180101	| 99991231	| CONS_LATAM_V_15574	| CONS_LATAM_CO01		| Ship	| 28		| SubcontractingTransport	|
			|  27				| 50161toCO01	| 93616				| 20180101	| 99991231	| CONS_LATAM_V_50161	| CONS_LATAM_CO01		| Ship	| 35		| VendorTransport			|

		And I wait "/plan/cns_tlane_item" Async Queue complete

		Given I import "/plan/cns_process_type" by keyFields "processTypeId"
#			| processTypeId 			| processTypeDesc 			|
#			| InternalTransport 		| InternalTransport			|
#			| SubcontractingTransport 	| SubcontractingTransport	|
#			| VendorTransport 			| VendorTransport			|

			| processTypeId				| processTypeDesc																																	|
			| InternalTransport			| Internal Transport (Transport from 1 plant to another plant in the same ERP system, modeled through transport lanes in SAP)						|
			| VendorTransport			| Vendor Transport (specific for the Pangea Vendor modeling: transport from a Vendor location to a plant)											|
			| SubcontractingTransport	| Subcontracting Transport (Transport to ship components to a subcontractor)																		|
			| Subcontracting			| Subcontracting																																	|
			| ExternalPurchase			| External Purchase																																	|
			| VendorPurchase			| Vendor Purchase																																	|
			| InterERPTransport			| InterERP Transport (Transport between 2 different ERP systems, modeled through sales order/purchase order)										|
			| Production				| Production																																		|
			| ExternalTransport			| External Transport (Transport from 1 fiscal entity to another fiscal entity in the same ERP system, modeled through sales order/purchase order)	|

		And I wait "/plan/cns_process_type" Async Queue complete

		Given I import "/edm/material_plant_v1" by keyFields "localMaterialNumber,localPlant,sourceSystem"
#			| sourceSystem | localMaterialNumber  | localPlant | materialNumber | plant | localPlantStatus | plantStatus | localProcurementType | localDeletionFlagPlant | localFixedlotsize | localMaximumLotSize | localMinimumLotSize | localRoundingvalueForPoq | localLotsize | localMrpType | localMrpController | localInHouseProcessingTime | localSafetyStock | localMinimumSafetyStock | localProductionSupervisor | localProductionUnit | localPosttoInspectionStock | localComponentScrapInPercent | localCriticalpart | localAbcIndicator | localMaximumStocklevel | localCheckingGroupforAvailabilityCheck | localPlannedDeliveryTimeInDays | localDependentRequirements | localSafetytimeIndicator | localSafetyTime | localSpecialProcurementType | localPlanningStrategyGroup | localConsumptionPeriodBackward | localConsumptionPeriodForward | localConsumptionMode | localGoodsReceiptProcessingTimeInDays | localBatchManagementRequirmentIndicator | localPlanningTimeFence | localPosttoinspstk | localComponentScrap | localBaseQuantity | localPurchasingGroup	|
#			| CONS_LATAM   | 59816                | BR12       | 59816          | BR12  | 07               |             | E                    | BR12                   | 0.003             | 0.003               | 0.003               | 0.003                    | EX3          | X4           | 154                | 0                          | 0.003            | 0.003                   | 004                       | 0.003               | 0.003                      | 0.003                        | 0.003             | 0.003             | 0.003                  | 01                                     | 3                              | 3                          | 3                        | 3               | 3                           | 3                          | 3                              | 3                             | 3                    | 3                                     | 3                                       | 3                      | 0.003              | 0.003               | BA3               | BA0               	|
#			| CONS_LATAM   | 59818                | BR18       | 59818          | BR18  | 07               |             | E                    | BR18                   | 0.003             | 0.003               | 0.003               | 0.003                    | EX3          | X4           | 154                | 0                          | 0.003            | 0.003                   | 004                       | 0.003               | 0.003                      | 0.003                        | 0.003             | 0.003             | 0.003                  | 01                                     | 3                              | 3                          | 3                        | 3               | 3                           | 3                          | 3                              | 3                             | 3                    | 3                                     | 3                                       | 3                      | 0.003              | 0.003               | BA3               | BA0               	|

#			| localMaterialNumber	| localPlant	| sourceSystem	| materialNumber	| localPurchasingGroup	| localAbcIndicator	| localBaseQuantity	| localBatchManagementRequirementIndicator	| localCheckingGroupForAvailabilityCheck	| localComponentScrap	| localComponentScrapInPercent	| localConsumptionMode	| localConsumptionPeriodBackward	| localConsumptionPeriodForward	| localCriticalPart	| localDeletionFlagPlant	| localDependentRequirements	| localFixedLotSize	| localGoodsReceiptProcessingTimeInDays	| localInHouseProcessingTime	| localLotSize	| localMaximumLotSize	| localMaximumStockLevel	| localMinimumLotSize	| localMinimumSafetyStock	| localMrpController	| localMrpType	| localPlannedDeliveryTimeInDays	| localPlanningStrategyGroup	| localPlanningTimeFence	| localPlantStatus	| localPostToInspStk	| localPostToInspectionStock	| localProcurementType	| localProductionSupervisor	| localProductionUnit	| localRoundingValueForPoq	| localSafetyStock	| localSafetyTime	| localSafetyTimeIndicator	| localSpecialProcurementType	| plant	| plantStatus	|
#			| 88382					| PE01			| CONS_LATAM	| 88382				| BA0					| 					| 0					| X											| 2											| 0						| 0								| 2						| 45								| 7								|  					| 							| 1								| 0					| 15									| 0								| MB			| 0						| 0							| 1						| 0							| U01					| X0			| 0									| 40							| 0							| 11				|  						|  								| F						|  							|  						| 1							| 0					| 0					|  							| 44							| UY07	| 				|
#			| 69056					| AR02			| CONS_LATAM	| 69056				| BA0					| 					| 0					| X											| 2											| 0						| 0								|  						| 0									| 0								|  					| 							|  								| 0					| 0										| 0								|  				| 0						| 0							| 0						| 0							| U01					| X0			| 0									|  								| 0							| 13				|  						|  								| F						|  							|  						| 0							| 0					| 0					|  							| 90							| 		| 				|
#			| 93937					| CO01			| CONS_LATAM	| 93937				| BA0					| 					| 0					| X											| 2											| 0						| 0								|  						| 0									| 0								|  					| 							|  								| 0					| 0										| 0								|  				| 0						| 0							| 0						| 0							| U01					| X0			| 0									|  								| 0							| 13				|  						|  								| F						|  							|  						| 0							| 0					| 0					|  							| 30							| 		| 				|
#			| 93616					| CO01			| CONS_LATAM	| 93937				| BA0					| 					| 0					| X											| 2											| 0						| 0								|  						| 0									| 0								|  					| 							|  								| 0					| 0										| 0								|  				| 0						| 0							| 0						| 0							| U01					| X0			| 0									|  								| 0							| 13				|  						|  								| F						|  							|  						| 0							| 0					| 0					|  							| 								| 		| 				|
#			| 60109					| BR07			| CONS_LATAM	| 60109				| BA0					| 					| 0					| X											| 2											| 0						| 0								| 2						| 45								| 7								|  					| 							| 1								| 0					| 15									| 0								| MB			| 0						| 0							| 1						| 0							| U01					| X0			| 0									| 40							| 0							| 11				|  						|  								| F						|  							|  						| 1							| 0					| 0					|  							| 44							| 		| 				|
#			| 441526				| BR25			| CONS_LATAM	| 441526			| BA0					| 					| 0					| X											| 2											| 0						| 0								|  						| 0									| 0								|  					| 							|  								| 0					| 0										| 0								|  				| 0						| 0							| 0						| 0							| U01					| X0			| 0									|  								| 0							| 13				|  						|  								| F						|  							|  						| 0							| 0					| 0					|  							| 90							| 		| 				|
#			| 59816					| BR12			| CONS_LATAM	| 59816				| BA0					| 					| 0					| X											| 2											| 0						| 0								|  						| 0									| 0								|  					| 							|  								| 0					| 0										| 0								|  				| 0						| 0							| 0						| 0							| U01					| X0			| 0									|  								| 0							| 13				|  						|  								| F						|  							|  						| 0							| 0					| 0					|  							| 30							| 		| 				|
#			| 93937					| 15574			| CONS_LATAM	| 93937				| BA0					| 					| 0					| X											| 2											| 0						| 0								|  						| 0									| 0								|  					| 							|  								| 0					| 0										| 0								|  				| 0						| 0							| 0						| 0							| U01					| X0			| 0									|  								| 0							| 13				|  						|  								| F						|  							|  						| 0							| 0					| 0					|  							| 30							| 		| 				|

			| localMaterialNumber	| localPlant	| sourceSystem	| materialNumber	| localPurchasingGroup	| purchasingGrpCd	| plant	| localAbcIndicator	| localBaseQuantity	| localBatchManagementRequirementIndicator	| localCheckingGroupForAvailabilityCheck	| localComponentScrap	| localComponentScrapInPercent	| localConsumptionMode	| localConsumptionPeriodBackward	| localConsumptionPeriodForward	| localCriticalPart	| localDeletionFlagPlant	| localDependentRequirements	| localFixedLotSize	| localGoodsReceiptProcessingTimeInDays	| localInHouseProcessingTime	| localLotSize	| localMaximumLotSize	| localMaximumStockLevel	| localMinimumLotSize	| localMinimumSafetyStock	| localMrpController	| localMrpType	| localPlannedDeliveryTimeInDays	| localPlanningStrategyGroup	| localPlanningTimeFence	| localPlantStatus	| localPostToInspStk	| localPostToInspectionStock	| localProcurementType	| localProductionSupervisor	| localProductionUnit	| localRoundingValueForPoq	| localSafetyStock	| localSafetyTime	| localSafetyTimeIndicator	| localSpecialProcurementType	| plantStatus	|
			| 88382					| PE01			| CONS_LATAM	| 88382				| BA0					| BA0				| UY07	|  					| 0					| X											| 2											| 0						| 0								| 2						| 45								| 7								|  					| 							| 1								| 0					| 15									| 0								| MB			| 0						| 0							| 1						| 0							| U01					| X0			| 0									| 40							| 0							| 11				|  						|  								| F						|  							|  						| 1							| 0					| 0					|  							| 44							| 				|
			| 69056					| AR02			| CONS_LATAM	| 69056				| BA0					| BA0				| 		|  					| 0					| X											| 2											| 0						| 0								|  						| 0									| 0								|  					| 							|  								| 0					| 0										| 0								|  				| 0						| 0							| 0						| 0							| U01					| X0			| 0									|  								| 0							| 13				|  						|  								| F						|  							|  						| 0							| 0					| 0					|  							| 90							| 				|
			| 93937					| CO01			| CONS_LATAM	| 93937				| BA0					| BA0				| 		|  					| 0					| X											| 2											| 0						| 0								|  						| 0									| 0								|  					| 							|  								| 0					| 0										| 0								|  				| 0						| 0							| 0						| 0							| U01					| X0			| 0									|  								| 0							| 13				|  						|  								| F						|  							|  						| 0							| 0					| 0					|  							| 30							| 				|
			| 93616					| CO01			| CONS_LATAM	| 93937				| BA0					| BA0				| 		|  					| 0					| X											| 2											| 0						| 0								|  						| 0									| 0								|  					| 							|  								| 0					| 0										| 0								|  				| 0						| 0							| 0						| 0							| U01					| X0			| 0									|  								| 0							| 13				|  						|  								| F						|  							|  						| 0							| 0					| 0					|  							| 								| 				|
			| 60109					| BR07			| CONS_LATAM	| 60109				| BA0					| BA0				| 		|  					| 0					| X											| 2											| 0						| 0								| 2						| 45								| 7								|  					| 							| 1								| 0					| 15									| 0								| MB			| 0						| 0							| 1						| 0							| U01					| X0			| 0									| 40							| 0							| 11				|  						|  								| F						|  							|  						| 1							| 0					| 0					|  							| 44							| 				|
			| 441526				| BR26			| CONS_LATAM	| 441526			| BA0					| BA0				| 		|  					| 0					| X											| 2											| 0						| 0								|  						| 0									| 0								|  					| 							|  								| 0					| 0										| 0								|  				| 0						| 0							| 0						| 0							| U01					| X0			| 0									|  								| 0							| 13				|  						|  								| F						|  							|  						| 0							| 0					| 0					|  							| 90							| 				|
			| 59816					| BR12			| CONS_LATAM	| 59816				| BA0					| BA0				| 		|  					| 0					| X											| 2											| 0						| 0								|  						| 0									| 0								|  					| 							|  								| 0					| 0										| 0								|  				| 0						| 0							| 0						| 0							| U01					| X0			| 0									|  								| 0							| 13				|  						|  								| F						|  							|  						| 0							| 0					| 0					|  							| 30							| 				|
			| 441526				| BR25			| CONS_LATAM	| 441526			| BA0					| BA0				| 		|  					| 0					| X											| 2											| 0						| 0								|  						| 0									| 0								|  					| 							|  								| 0					| 0										| 0								|  				| 0						| 0							| 0						| 0							| U01					| X0			| 0									|  								| 0							| 13				|  						|  								| F						|  							|  						| 0							| 0					| 0					|  							| 90							| 				|
			| 88382					| CO01			| CONS_LATAM	| 88382				| BA0					| BA0				| UY07	|  					| 0					| X											| 2											| 0						| 0								| 2						| 45								| 7								|  					| 							| 1								| 0					| 15									| 0								| MB			| 0						| 0							| 1						| 0							| U01					| X0			| 0									| 40							| 0							| 11				|  						|  								| F						|  							|  						| 1							| 0					| 0					|  							| 44							| 				|
			| 69056					| AR01			| CONS_LATAM	| 69056				| BA0					| BA0				| 		|  					| 0					| X											| 2											| 0						| 0								|  						| 0									| 0								|  					| 							|  								| 0					| 0										| 0								|  				| 0						| 0							| 0						| 0							| U01					| X0			| 0									|  								| 0							| 13				|  						|  								| F						|  							|  						| 0							| 0					| 0					|  							| 90							| 				|
			| 60109					| BR12			| CONS_LATAM	| 60109				| BA0					| BA0				| 		|  					| 0					| X											| 2											| 0						| 0								| 2						| 45								| 7								|  					| 							| 1								| 0					| 15									| 0								| MB			| 0						| 0							| 1						| 0							| U01					| X0			| 0									| 40							| 0							| 11				|  						|  								| F						|  							|  						| 1							| 0					| 0					|  							| 44							| 				|
			| 441526				| BR12			| CONS_LATAM	| 441526			| BA0					| BA0				| 		|  					| 0					| X											| 2											| 0						| 0								|  						| 0									| 0								|  					| 							|  								| 0					| 0										| 0								|  				| 0						| 0							| 0						| 0							| U01					| X0			| 0									|  								| 0							| 13				|  						|  								| F						|  							|  						| 0							| 0					| 0					|  							| 90							| 				|
			| 59816					| BR07			| CONS_LATAM	| 59816				| BA0					| BA0				| 		|  					| 0					| X											| 2											| 0						| 0								|  						| 0									| 0								|  					| 							|  								| 0					| 0										| 0								|  				| 0						| 0							| 0						| 0							| U01					| X0			| 0									|  								| 0							| 13				|  						|  								| F						|  							|  						| 0							| 0					| 0					|  							| 30							| 				|
			| 441526				| BR12			| CONS_LATAM	| 441526			| BA0					| BA0				| 		|  					| 0					| X											| 2											| 0						| 0								|  						| 0									| 0								|  					| 							|  								| 0					| 0										| 0								|  				| 0						| 0							| 0						| 0							| U01					| X0			| 0									|  								| 0							| 13				|  						|  								| F						|  							|  						| 0							| 0					| 0					|  							| 90							| 				|

		And I wait "/edm/material_plant_v1" Async Queue complete

		Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
#			| sourceSystem | localMaterialNumber | localRefDescription                  | localMaterialType | localBaseUom | materialNumber | refDescription                           | materialType | baseUom | localDpParentCod | parentCode    | globalDpParentCode | form   | category | subBrand | brand  | franchise | globalBusinessUnit | productFamily | localManufacturingTechnology | manufacturingTechnology | localMaterialGroup | materialGroup | flagForDeletion | materialStatus | division | batchManageIndicator | minRemShelfLife | totalShelfLife | primaryPlanningCode |
#			| CONS_LATAM   | 59816               | JS COTTON BALLS 50 GRX20 T50P35      | FERT              | KI           | 59816  		| JS SOFT DEO HIDR MAC PROL 12XL400P320ML  | FERT         | EA      |                  | 7891010931582 |                    | 116151 | 6        | 1V       | BRD001 | FCH001    | GFO001             | Not Assigned  |                              | Wipes                   | 01                 |               |                 | 08             | 10       | X                    | 180             | 9999           | 59816               |
#			| CONS_LATAM   | 59817               | JS COTTON BALLS 50 GRX20 T50P35      | FERT              | KI           | 59817  		| JS SOFT DEO HIDR MAC PROL 12XL400P320ML  | FERT         | EA      |                  | 7891010931582 |                    | 116151 | 6        | 1V       | BRD001 | FCH001    | GFO001             | Not Assigned  |                              | Wipes                   | 01                 |               |                 | 08             | 10       | X                    | 180             | 9999           | 59817               |
#			| CONS_LATAM   | 59818               | JS COTTON BALLS 50 GRX20 T50P35      | FERT              | KI           | 59818  		| JS SOFT DEO HIDR MAC PROL 12XL400P320ML  | FERT         | EA      |                  | 7891010931582 |                    | 116151 | 6        | 1V       | BRD001 | FCH001    | GFO001             | Not Assigned  |                              | Wipes                   | 01                 |               |                 | 08             | 10       | X                    | 180             | 9999           | 59818               |

#			| sourceSystem	| localMaterialNumber	| materialNumber	| primaryPlanningCode	| baseUom	| batchManageIndicator	| brand	| category	| division	| flagForDeletion	| form	| franchise	| globalBusinessUnit	| globalDpParentCode	| localBaseUom	| localDpParentCode	| localManufacturingTechnology	| localMaterialGroup	| localMaterialType	| localRefDescription			| manufacturingTechnology	| materialGroup	| materialStatus	| materialType	| minRemShelfLife	| parentCode	| productFamily	| refDescription	| subBrand	| totalShelfLife	|
#			| CONS_LATAM	| 88382					| 88382				| 88382					| 			|  						| 		| 			|  			|  					| 		| 			| 						| 						| EA			| 					| 								|  						| CBAU				| EMS - Indisponivel para LATAM	| 							| 				| 11				| 				| 0					| 				| 				| 					| 			| 0					|
#			| CONS_LATAM	| 69056					| 69056				| 69056					| 			|  						| 		| 			|  			|  					| 		| 			| 						| 						| EA			| 					| 								|  						| CBAU				| EMS - Indisponivel para LATAM	| 							| 				| 13				| 				| 0					| 				| 				| 					| 			| 0					|
#			| CONS_LATAM	| 93937					| 93937				| 93937					| 			|  						| 		| 			|  			|  					| 		| 			| 						| 						| EA			| 					| 								|  						| CBAU				| EMS - Indisponivel para LATAM	| 							| 				| 11				| 				| 0					| 				| 				| 					| 			| 0					|
#			| CONS_LATAM	| 93616					| 93616				| 						| 			|  						| 		| 			|  			|  					| 		| 			| 						| 						| EA			| 					| 								|  						| CBAU				| EMS - Indisponivel para LATAM	| 							| 				| 13				| 				| 0					| 				| 				| 					| 			| 0					|
#			| CONS_LATAM	| 60109					| 60109				| 60109					| 			|  						| 		| 			|  			|  					| 		| 			| 						| 						| EA			| 					| 								|  						| CBAU				| EMS - Indisponivel para LATAM	| 							| 				| 11				| 				| 0					| 				| 				| 					| 			| 0					|
#			| CONS_LATAM	| 441526				| 441526			| 441526				| 			|  						| 		| 			|  			|  					| 		| 			| 						| 						| EA			| 					| 								|  						| CBAU				| EMS - Indisponivel para LATAM	| 							| 				| 13				| 				| 0					| 				| 				| 					| 			| 0					|
#			| CONS_LATAM	| 59816					| 59816				| 59816					| 			|  						| 		| 			|  			|  					| 		| 			| 						| 						| EA			| 					| 								|  						| CBAU				| EMS - Indisponivel para LATAM	| 							| 				| 11				| 				| 0					| 				| 				| 					| 			| 0					|

			| sourceSystem	| localMaterialNumber	| materialNumber	| primaryPlanningCode	| baseUom	| batchManageIndicator	| brand	| category	| division	| flagForDeletion	| form	| franchise	| globalBusinessUnit	| globalDpParentCode	| localBaseUom	| localDpParentCode	| localManufacturingTechnology	| localMaterialGroup	| localMaterialType	| localRefDescription			| manufacturingTechnology	| materialGroup	| materialStatus	| materialType	| minRemShelfLife	| parentCode	| productFamily	| refDescription	| subBrand	| totalShelfLife	|
			| CONS_LATAM	| 88382					| 88382				| 88382					| 			|  						| 		| 			|  			|  					| 		| 			| 						| 						| EA			| 					| 								|  						| CBAU				| EMS - Indisponivel para LATAM	| 							| 				| 11				| 				| 0					| 				| 				| 					| 			| 0					|
			| CONS_LATAM	| 69056					| 69056				| 69056					| 			|  						| 		| 			|  			|  					| 		| 			| 						| 						| EA			| 					| 								|  						| CBAU				| EMS - Indisponivel para LATAM	| 							| 				| 13				| 				| 0					| 				| 				| 					| 			| 0					|
			| CONS_LATAM	| 93937					| 93937				| 93937					| 			|  						| 		| 			|  			|  					| 		| 			| 						| 						| EA			| 					| 								|  						| CBAU				| EMS - Indisponivel para LATAM	| 							| 				| 11				| 				| 0					| 				| 				| 					| 			| 0					|
			| CONS_LATAM	| 93616					| 93616				| 						| 			|  						| 		| 			|  			|  					| 		| 			| 						| 						| EA			| 					| 								|  						| CBAU				| EMS - Indisponivel para LATAM	| 							| 				| 13				| 				| 0					| 				| 				| 					| 			| 0					|
			| CONS_LATAM	| 60109					| 60109				| 60109					| 			|  						| 		| 			|  			|  					| 		| 			| 						| 						| EA			| 					| 								|  						| CBAU				| EMS - Indisponivel para LATAM	| 							| 				| 11				| 				| 0					| 				| 				| 					| 			| 0					|
			| CONS_LATAM	| 441526				| 441526			| 441526				| 			|  						| 		| 			|  			|  					| 		| 			| 						| 						| EA			| 					| 								|  						| CBAU				| EMS - Indisponivel para LATAM	| 							| 				| 13				| 				| 0					| 				| 				| 					| 			| 0					|
			| CONS_LATAM	| 59816					| 59816				| 59816					| 			|  						| 		| 			|  			|  					| 		| 			| 						| 						| EA			| 					| 								|  						| CBAU				| EMS - Indisponivel para LATAM	| 							| 				| 11				| 				| 0					| 				| 				| 					| 			| 0					|

		And I wait "/edm/material_global_v1" Async Queue complete

		Given I import "/edm/source_list_v1" by keyFields "sourceSystem,localMaterialNumber,localPlant,localNumberofSourceListRecord"
#			| sourceSystem | localMaterialNumber | localPlant | localNumberofSourceListRecord | materialNumber	| plant | localCreatedOn | localCreatedBy | localSourceListRecordValidFrom | localSourceListRecordValidTo | localVendorAccountNumber | localFixedvendor | localAgreementNumber | localAgreementItem | localFixedOutlinePurchaseAgreementItem | localPlantfromWhichMaterialisProcured | localMatForManufPartNumber | localBlockedSourceofSupply | localPurchasingOrganization | localPurchasingDocumentCategory | localCategoryofSourceListRecord | localSourceListUsageinMaterialsPlanning |
#			| CONS_LATAM   | 59816               | BR12       | 4                             | 59816           | AR01  | 20030306       | CCASTRO1       | 20030206                       | 29991231                     | 8917                     |                  |                      | 0                  |                                        | BR12                                  |                            |                            | BR00                        |                                 | 7                               |                                         |

#			| localMaterialNumber	| sourceSystem	| localPlant	| localPurchasingOrganization	| localSourceListRecordValidFrom	| localSourceListRecordValidTo	| localVendorAccountNumber	| localAgreementItem	| localAgreementNumber	| localBlockedSourceofSupply	| localCategoryofSourceListRecord	| localDateonWhichRecordWasCreated	| localFixedOutlinePurchaseAgreementItem	| localFixedvendor	| localMaterialNumberCorrespondingtoManufacturerPartNumber	| localNameofPersonwhoCreatedtheObject	| localNumberofSourceListRecord	| localPlantfromWhichMaterialisProcured	| localPurchasingDocumentCategory	| localSourceListUsageinMaterialsPlanning	|
#			| 93937					| CONS_LATAM	| CO01			| CO00							| 20030206							| 29991231						| 15574						| 0						|  						|  								| 7									| 20030306							|  											|  					|  															| CCASTRO1								| 4								| CO01									|  									|  											|
#			| 93616					| CONS_LATAM	| CO01			| CO00							| 20020301							| 99991231						| 50161						| 0						|  						|  								| 7									| 20020522							|  											|  					|  															| CARGA03								| 2								| CO01									|  									|  											|
#			| 59816					| CONS_LATAM	| BR12			| CO00							| 20020301							| 99991231						| 50161						| 0						|  						|  								| 7									| 20020522							|  											|  					|  															| CARGA03								| 2								| CO01									|  									|  											|

			| localMaterialNumber	| sourceSystem	| localPlant	| localPurchasingOrganization	| localSourceListRecordValidFrom	| localSourceListRecordValidTo	| localBlockedSourceofSupply	| localVendorAccountNumber	| localAgreementItem	| localAgreementNumber	| localCategoryofSourceListRecord	| localDateonWhichRecordWasCreated	| localFixedOutlinePurchaseAgreementItem	| localFixedvendor	| localMaterialNumberCorrespondingtoManufacturerPartNumber	| localNameofPersonwhoCreatedtheObject	| localNumberofSourceListRecord	| localPlantfromWhichMaterialisProcured	| localPurchasingDocumentCategory	| localSourceListUsageinMaterialsPlanning	|
			| 93937					| CONS_LATAM	| CO01			| CO00							| 20030206							| 29991231						|  								| 15574						| 0						|  						| 7									| 20030306							|  											|  					|  															| CCASTRO1								| 4								| CO01									|  									|  											|
			| 93616					| CONS_LATAM	| CO01			| CO00							| 20020301							| 99991231						|  								| 50161						| 0						|  						| 7									| 20020522							|  											|  					|  															| CARGA03								| 2								| CO01									|  									|  											|
			| 59816					| CONS_LATAM	| BR12			| CO00							| 20020301							| 99991231						|  								| 12345						| 0						|  						| 7									| 20020522							|  											|  					|  															| CARGA03								| 2								| BR12									|  									|  											|
			| 60109					| CONS_LATAM	| BR07			| CO00							| 20020301							| 99991231						|  								| 23456						| 0						|  						| 7									| 20020522							|  											|  					|  															| CARGA03								| 2								| BR07									|  									|  											|
			| 441526				| CONS_LATAM	| BR25			| CO00							| 20020301							| 99991231						|  								| 34567						| 0						|  						| 7									| 20020522							|  											|  					|  															| CARGA03								| 2								| BR25									|  									|  											|
			| 69056					| CONS_LATAM	| AR02			| CO00							| 20020301							| 99991231						|  								| 45678						| 0						|  						| 7									| 20020522							|  											|  					|  															| CARGA03								| 2								| AR01									|  									|  											|
			| 88382					| CONS_LATAM	| PE01			| CO00							| 20020301							| 99991231						|  								| 45678						| 0						|  						| 7									| 20020522							|  											|  					|  															| CARGA03								| 2								| CO01									|  									|  											|

		And I wait "/edm/source_list_v1" Async Queue complete

		Given I import "/plan/cns_spl_pln_loc" by keyFields "sourceSystem,localNumber,localName,vendorOrCustomer"
#			| sourceSystem	| localNumber	|
#			| CONS_LATAM	| BR07			|

#			| sourceSystem	| localPlant	| localCountry	| localCurrency	| localName								| localNumber	| localRegion	| pLanlocTypeDesc		| pLanlocTypeId	| specLocAtt1	| specLocAtt2	| specLocAtt3	| specLocAttDesc1	| specLocAttDesc2	| specLocAttDesc3	| vendorOrCustomer	|
#			| CONS_LATAM	| CO01			| CO			| COP			| ASPRILLA ORTIZ FABIO					| 15574			| 103			| Mfg Plant Copacker	| SUBCON		| 				| 				| 				| 					| 					| 					| V					|
#			| CONS_LATAM	| MX02			| MX			| MXN			| AVP MAQUILA DE MEXICO S DE RL DE CV	| 50161			| 103			| VENDOR				| SUBCON		| 				| 				| 				| 					| 					| 					| V					|

			| sourceSystem	| localNumber	| localName								| vendorOrCustomer	| localPlant	| localCountry	| localCurrency	| localRegion	| pLanlocTypeDesc		| pLanlocTypeId	| specLocAtt1	| specLocAtt2	| specLocAtt3	| specLocAttDesc1	| specLocAttDesc2	| specLocAttDesc3	|
			| CONS_LATAM	| 15574			| ASPRILLA ORTIZ FABIO					| V					| CO01			| CO			| COP			| 103			| Mfg Plant Copacker	| SUBCON		| 				| 				| 				| 					| 					| 					|
			| CONS_LATAM	| 50161			| AVP MAQUILA DE MEXICO S DE RL DE CV	| V					| CO01			| MX			| MXN			| 103			| VENDOR				| SUBCON		| 				| 				| 				| 					| 					| 					|

		And I wait "/plan/cns_spl_pln_loc" Async Queue complete

		Given I import "/plan/cns_material_plan_status" by keyFields "sourceSystem,localMaterialNumber,localPlant"
#			| sourceSystem | localMaterialNumber | localPlant | materialNumber | localParentCode | ppc | active | dpRelevant | spRelevant | parentActive | noPlanRelevant |
#			| CONS_LATAM   | 59816               | BR07       | 59816          | G3b             | G4b | X      | X          | X          | X            | X              |

#			| localMaterialNumber	| sourceSystem	| localPlant	| active	| dpRelevant	| localParentCode		| materialNumber	| noPlanRelevant	| parentActive	| ppc		| spRelevant	|
#			| 88382					| CONS_LATAM	| PE01			| X			| X				| 178962124094540000	| 88382				| 					| X				| 88382		| X	 			|
#			| 69056					| CONS_LATAM	| AR02			| X			| X				| 178962124094540000	| 69056				| 					| X				| 69056		| X	 			|
#			| 93937					| CONS_LATAM	| BR19			| X			| X				| 178962124094540000	| 945				| 					| X				| 93937		| X	 			|
#			| 93616					| CONS_LATAM	| BR19			| X			| X				| 178962124094540000	| 945				| 					| X				| 			| X	 			|
#			| 60109					| CONS_LATAM	| BR07			| X			| X				| 178962124094540000	| 60109				| 					| X				| 60109		| X	 			|
#			| 441526				| CONS_LATAM	| BR26			| X			| X				| 178962124094540000	| 441526			| 					| X				| 441526	| X	 			|
#			| 59816					| CONS_LATAM	| CO01			| X			| X				| 178962124094540000	| 59816				| 					| X				| 59816		| X				|

			| localMaterialNumber	| sourceSystem	| localPlant	| active	| dpRelevant	| localParentCode		| materialNumber	| noPlanRelevant	| parentActive	| ppc		| spRelevant	|
			| 88382					| CONS_LATAM	| PE01			| X			| X				| 178962124094540000	| 88382				| 					| X				| 88382		| X				|
			| 69056					| CONS_LATAM	| AR02			| X			| X				| 178962124094540000	| 69056				| 					| X				| 69056		| X				|
			| 93937					| CONS_LATAM	| CO01			| X			| X				| 178962124094540000	| 945				| 					| X				| 93937		| X				|
			| 93616					| CONS_LATAM	| CO01			| X			| X				| 178962124094540000	| 945				| 					| X				| 			| X				|
			| 60109					| CONS_LATAM	| BR07			| X			| X				| 178962124094540000	| 60109				| 					| X				| 60109		| X				|
			| 441526				| CONS_LATAM	| BR26			| X			| X				| 178962124094540000	| 441526			| 					| X				| 441526	| X				|
			| 59816					| CONS_LATAM	| BR12			| X			| X				| 178962124094540000	| 59816				| 					| X				| 59816		| X				|
			| 441526				| CONS_LATAM	| BR25			| X			| X				| 178962124094540000	| 441526			| 					| X				| 441526	| X				|
			| 88382					| CONS_LATAM	| CO01			| X			| X				| 178962124094540000	| 88382				| 					| X				| 88382		| X				|
			| 69056					| CONS_LATAM	| AR01			| X			| X				| 178962124094540000	| 69056				| 					| X				| 69056		| X				|
			| 60109					| CONS_LATAM	| BR12			| X			| X				| 178962124094540000	| 60109				| 					| X				| 60109		| X				|
			| 441526				| CONS_LATAM	| BR12			| X			| X				| 178962124094540000	| 441526			| 					| X				| 441526	| X				|
			| 59816					| CONS_LATAM	| BR07			| X			| X				| 178962124094540000	| 59816				| 					| X				| 59816		| X				|
			| 59816					| CONS_LATAM	| CO01			| X			| X				| 178962124094540000	| 59816				| 					| X				| 59816		| X				|
			| 441526				| CONS_LATAM	| BR12			| X			| X				| 178962124094540000	| 441526			| 					| X				| 441526	| X				|

		And I wait "/plan/cns_material_plan_status" Async Queue complete

		Given I import "/edm/plant_v1" by keyFields "sourceSystem,localPlant"
#			| sourceSystem | localPlant 	| plant 	| localPlanningRelevant |
#			| CONS_LATAM   | BR12       	| BR12  	| X						|
#			| CONS_LATAM   | BR07       	| BR07  	| X						|
#			| CONS_LATAM   | BR25       	| BR25  	| X						|
#			| CONS_LATAM   | CO01       	| CO01  	| X						|
#			| CONS_LATAM   | PE01       	| PE01  	| X						|
#			| CONS_LATAM   | AR01       	| AR01  	| X						|
#			| CONS_LATAM   | 15574       	| 15574  	| X						|
#			| CONS_LATAM   | 50161       	| 50161  	| X						|

			| sourceSystem	| localPlant	| localPlanningRelevant	| country	| localCurrency	| plantType					| localPlantType	| site					| localCountry	| plant	| localPlantName					| region									|
			| CONS_LATAM	| AR01			| 						| AR		| ARS			| MP, Manufacturing Plant	| 					| Pilar					| AR			| AR06	| Pilar Plant						| AR, ARGENTINA ARGENTINA					|
			| CONS_LATAM	| AR02			| 						| AR		| ARS			| DC, Distribution Center	| DC				| Pilar DC				| AR			| AR07	| S & M Pilar						| AR, ARGENTINA ARGENTINA					|
			| CONS_LATAM	| BR07			| X						| -			| BRL			| DC, Distribution Center	| DC				| Pessoa DC				| BR			| BR54	| J&J BR-João Pessoa-COM&Distrib	| BR, BRAZIL BRAZIL							|
			| CONS_LATAM	| BR12			| X						| -			| BRL			| MP, Manufacturing Plant	| 					| Sao Jose dos Campos	| BR			| BR59	| J&J BR São José Campos - Indus	| BR, BRAZIL BRAZIL							|
			| CONS_LATAM	| BR16			| 						| -			| BRL			| DC, Distribution Center	| DC				| Extrema DC			| BR			| BR61	| J&J BR-Extrema - Com&Distrib		| BR, BRAZIL BRAZIL							|
			| CONS_LATAM	| BR19			| 						| -			| BRL			| DC, Distribution Center	| DC				| Nova Odessa DC		| BR			| BR63	| J&J BR-Nova Odessa - Com&Distr	| BR, BRAZIL BRAZIL							|
			| CONS_LATAM	| BR25			| X						| -			| BRL			| DC, Distribution Center	| DC				| Goiania DC			| BR			| BR69	| J&J BR-Goiânia - Com&Distrib		| BR, BRAZIL BRAZIL							|
			| CONS_LATAM	| CL01			| 						| CL		| CLP			| DC, Distribution Center	| DC				| Chile DC				| CL			| CL03	| J&J CL-Santiago-Chile Consumer	| CL, CHILE CHILE							|
			| CONS_LATAM	| CO01			| X						| 			| 				| MP, Manufacturing Plant	| 					| Cali					| 				| CO07	| J&J Colombia - LADS (CO01)		| CO, COLOMBIA COLOMBIA						|
			| CONS_LATAM	| CO02			| 						| 			| 				| DC, Distribution Center	| 					| Cali DC				| 				| CO08	| J&J Colombia - S&M (CO02)			| CO, COLOMBIA COLOMBIA						|
			| CONS_LATAM	| CR01			| 						| -			| CRC			| DC, Distribution Center	| DC				| Costa Rica DC			| CR			| CR01	| J&J Costa Rica - S&M Consumer		| CR, COSTA RICA COSTA RICA					|
			| CONS_LATAM	| CR02			| 						| -			| CRC			| DC, Distribution Center	| DC				| Costa Rica BF DC		| CR			| CR02	| J&J Costa Rica - Bond Fiscal		| CR, COSTA RICA COSTA RICA					|
			| CONS_LATAM	| DO01			| 						| DO		| DOP			| DC, Distribution Center	| DC				| Dominica				| DO			| DO02	| J&J DO-S.Domingos 1-R.Dominica	| DO, DOMINICAN REPUBLIC DOMINICAL REPUBLIC	|
			| CONS_LATAM	| EC01			| 						| 			| 				| DC, Distribution Center	| DC				| Ecuador DC			| 				| EC03	| Johnson & Johnson del Ecuador		| EC, ECUADOR ECUADOR						|
			| CONS_LATAM	| GT01			| 						| -			| GTQ			| DC, Distribution Center	| DC				| Guatemala DC			| GT			| GT01	| J&J Guatemala - S&M Consumer		| GT, GUATEMALA GUATEMALA					|
			| CONS_LATAM	| GT02			| 						| -			| GTQ			| DC, Distribution Center	| DC				| Guatemala BF DC		| GT			| GT02	| J&J Guatemala - Bond Fiscal		| GT, GUATEMALA GUATEMALA					|
			| CONS_LATAM	| MX01			| 						| MX		| MXN			| DC, Distribution Center	| DC				| PT Importadora DC		| MX			| MX31	| PT Importado						| MX, MEXICO MEXICO							|
			| CONS_LATAM	| MX02			| 						| MX		| MXN			| DC, Distribution Center	| DC				| Importadora DC		| MX			| MX32	| Sales & Marketing					| MX, MEXICO MEXICO							|
			| CONS_LATAM	| PA01			| 						| PA		| PAB			| DC, Distribution Center	| DC				| Panama DC				| PA			| PA05	| J&J Panama - S&M Consumer			| PA, PANAMA PANAMA							|
			| CONS_LATAM	| PA03			| 						| PA		| PAB			| DC, Distribution Center	| DC				| Panama Zona Libre DC	| PA			| PA06	| J&J Panama - Zona Libre			| PA, PANAMA PANAMA							|
			| CONS_LATAM	| PE01			| 						| PE		| PEN			| DC, Distribution Center	| DC				| Peru DC				| PE			| PE03	| J&J PE-Lima-S&M					| PE, PERU PERU								|
			| CONS_LATAM	| PY01			| 						| PY		| PYG			| DC, Distribution Center	| DC				| Paraguay				| PY			| PY01	| S & M Consumer					| PY, PARAGUAY PARAGUAY						|
			| CONS_LATAM	| UY01			| 						| UY		| UYU			| DC, Distribution Center	| DC				| Uruguay				| UY			| UY07	| S & M Consumer					| UY, URUGUAY URUGUAY						|
			| CONS_LATAM	| UY05			| 						| UY		| UYU			| DC, Distribution Center	| DC				| Recinto Aduanero		| UY			| UY05	| S & M Consumer RecintoAduanero	| UY, URUGUAY URUGUAY						|
			| CONS_LATAM	| VE01			| 						| VE		| VEF			| MP, Manufacturing Plant	| 					| Valencia				| VE			| VE06	| J&J VE-Valencia-Planta			| VE, VENEZUELA VENEZUELA					|
			| CONS_LATAM	| VE02			| 						| VE		| VEF			| DC, Distribution Center	| DC				| Valencia DC			| VE			| VE07	| J&J VE-Valencia-Distribuición		| VE, VENEZUELA VENEZUELA					|
			| CONS_LATAM	| VE05			| 						| VE		| VEF			| DC, Distribution Center	| 					| Valencia Ethnor DC	| VE			| VE05	| J&J VE-Valencia-ETHNOR			| VE, VENEZUELA VENEZUELA					|


		And I wait "/edm/plant_v1" Async Queue complete

		And I will remove the test file on sink application "GDMTransportMergeException.tsv"

		When I submit task with xml file "xml/omp/OMPGdmTransportException.xml" and execute file "jar/pangea-view.jar"

		Then A file is found on sink application with name "GDMTransportMergeException.tsv"

		And I check file data for filename "GDMTransportMergeException.tsv" by keyFields "machineTypeId, processTypeId, transportId, requirementOffset, purchasingOrganization, active, vendorId, purchasingGroup, label, toLocationId, fromLocationId, planLevelId, activeOPRERP, activeSOPERP, minQuantity, transportOffset, toProductId, transportType, endEff, fromProductId, startEff"
#			| machineTypeId	| processTypeId		| transportId								| requirementOffset	| purchasingOrganization	| active	| vendorId	| purchasingGroup	| label				| toLocationId		| fromLocationId	| planLevelId	| activeOPRERP	| activeSOPERP	| minQuantity	| transportOffset	| toProductId	| transportType	| endEff	| fromProductId	| startEff	|
##			| TRANSPORT		| INTERNALTRANSPORT	| 59816/CONS_LATAM_V_BR07/CONS_LATAM_V_BR12	| 					| BR00						| YES		| 8917		| BA0				| INTERNALTRANSPORT	| CONS_LATAM_V_BR12	| CONS_LATAM_V_BR07	| *				| YES			| NO			| 				| 5					| 59816			| Ship			| 20181231	| 59816			| 20180101	|
#			| TRANSPORT		| InternalTransport	| 59816/CONS_LATAM_CO01/CONS_LATAM_BR12		| 					| CO00						| YES		| 50161		| BA0				| InternalTransport	| CONS_LATAM_BR12	| CONS_LATAM_CO01	| *				| YES			| NO			| 				| 77				| 59816			| Ship			| 20181231	| 59816			| 20180101	|

			| machineTypeId	| processTypeId		| transportId								| requirementOffset	| purchasingOrganization	| active	| vendorId	| purchasingGroup	| label																															| toLocationId		| fromLocationId	| planLevelId	| activeOPRERP	| activeSOPERP	| minQuantity	| transportOffset	| toProductId	| transportType	| endEff	| fromProductId	| startEff	|
			| TRANSPORT		| InternalTransport	| 60109/CONS_LATAM_BR12/CONS_LATAM_BR07		| 					| CO00						| YES		| 23456		| BA0				| Internal Transport (Transport from 1 plant to another plant in the same ERP system, modeled through transport lanes in SAP)	| CONS_LATAM_BR07	| CONS_LATAM_BR12	| *				| YES			| NO			| 				| 2					| 60109			| Air			| 20181231	| 60109			| 20180101	|
			| TRANSPORT		| InternalTransport	| 441526/CONS_LATAM_BR12/CONS_LATAM_BR25	| 					| CO00						| YES		| 34567		| BA0				| Internal Transport (Transport from 1 plant to another plant in the same ERP system, modeled through transport lanes in SAP)	| CONS_LATAM_BR25	| CONS_LATAM_BR12	| *				| YES			| NO			| 				| 4					| 441526		| Ship			| 20181231	| 441526		| 20180101	|
			| TRANSPORT		| InternalTransport	| 59816/CONS_LATAM_CO01/CONS_LATAM_BR12		| 					| CO00						| YES		| 12345		| BA0				| Internal Transport (Transport from 1 plant to another plant in the same ERP system, modeled through transport lanes in SAP)	| CONS_LATAM_BR12	| CONS_LATAM_CO01	| *				| YES			| NO			| 				| 77				| 59816			| Ship			| 20181231	| 59816			| 20180101	|

#		Then I check region data "/dev/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
#			| key1			| key2	| businessArea	| key5	| functionalArea	| key3	| sourceSystem	| errorValue															| key4	| errorCode	| interfaceID		|
##			| CONS_LATAM	| BR08	| 				| 		| SP				| 59816	| CONS_LATAM	| Deletion Indicator not Blank											| 		| N1		| OMPGdmTransport	|
#			| CONS_LATAM	| BR11	| 				| 		| SP				| 59816	| CONS_LATAM	| Destination Location is Empty. Can't generate source system or plant. | 		| setup		| OMPGdmTransport	|
#			| 				| 		| 				| 		| SP				| 59816	| 				| Origin Location is Empty. Can't generate source system or plant. 		| 		| setup		| OMPGdmTransport	|
#			| CONS_LATAM	| BR09	| 				| 		| SP				| 		| CONS_LATAM	| Material Number null in CNS Tlane Item Exception 						| 		| setup		| OMPGdmTransport	|
##			| CONS_LATAM	| BR10	| 				| 		| SP				| 59111	| CONS_LATAM	| Material number not found in MaterialGlobalV1				 			| 		| N8		| OMPGdmTransport	|
##			| CONS_LATAM	| BR12	| 				| 		| SP				| 59817	| CONS_LATAM	| No Matching Record found in EDMMaterialPlantV1Entity	 				| 		| N8		| OMPGdmTransport	|
##			| CONS_LATAM	| BR13	| 				| 		| SP				| 59818	| CONS_LATAM	| No Matching Record found in EDMSourceListV1Entity		 				| 		| N9_N10	| OMPGdmTransport	|
#
#		And I will remove all data with region "/dev/plan/edm_failed_data"

		And I will remove the test file on sink application "GDMTransportMergeMain.tsv"

		When I submit task with xml file "xml/omp/OMPGdmTransport.xml" and execute file "jar/pangea-view.jar"

		Then A file is found on sink application with name "GDMTransportMergeMain.tsv"

		And I check file data for filename "GDMTransportMergeMain.tsv" by keyFields "machineTypeId, processTypeId, transportId, requirementOffset, purchasingOrganization, active, vendorId, purchasingGroup, label, toLocationId, fromLocationId, planLevelId, activeOPRERP, activeSOPERP, minQuantity, transportOffset, toProductId, transportType, endEff, fromProductId, startEff"
#			| machineTypeId	| processTypeId				| transportId								| requirementOffset	| purchasingOrganization	| active	| vendorId			| purchasingGroup	| label						| toLocationId		| fromLocationId		| planLevelId	| activeOPRERP	| activeSOPERP	| minQuantity	| transportOffset	| toProductId	| transportType	| endEff	| fromProductId	| startEff	|
##			| TRANSPORT		| INTERNALTRANSPORT			| 59816/CONS_LATAM_V_BR07/CONS_LATAM_V_BR12	| 					| BR00						| YES		| 8917				| BA0				| INTERNALTRANSPORT			| CONS_LATAM_V_BR12	| CONS_LATAM_V_BR07		| *				| YES			| NO			| 				| 7					| 59816			| Ship			| 20181231	| 59816			| 20180101	|
#			| TRANSPORT		| InternalTransport			| 59816/CONS_LATAM_BR07/CONS_LATAM_BR12		| 					| CO00						| YES		| 50161				| BA0				| InternalTransport			| CONS_LATAM_BR12	| CONS_LATAM_BR07		| *				| YES			| NO			| 				| 55				| 59816			| Ship			| 20181231	| 59816			| 20180101	|
#			| TRANSPORT		| SubcontractingTransport	| 93937/CONS_LATAM_V_15574/CONS_LATAM_CO01	| 					| CO00						| YES		| 15574				| BA0				| SubcontractingTransport	| CONS_LATAM_CO01	| CONS_LATAM_V_15574	| *				| YES			| NO			| 				| 28				| 93937			| Ship			| 99991231	| 93937			| 20180101	|

			| machineTypeId	| processTypeId				| transportId								| requirementOffset	| purchasingOrganization	| active	| vendorId	| purchasingGroup	| label																															| toLocationId		| fromLocationId		| planLevelId	| activeOPRERP	| activeSOPERP	| minQuantity	| transportOffset	| toProductId	| transportType	| endEff	| fromProductId	| startEff	|
			| TRANSPORT		| InternalTransport			| 441526/CONS_LATAM_BR12/CONS_LATAM_BR25	| 					| CO00						| YES		| 34567		| BA0				| Internal Transport (Transport from 1 plant to another plant in the same ERP system, modeled through transport lanes in SAP)	| CONS_LATAM_BR25	| CONS_LATAM_BR12		| *				| YES			| NO			| 				| 4					| 441526		| Ship			| 20181231	| 441526		| 20180101	|
			| TRANSPORT		| InternalTransport			| 69056/CONS_LATAM_AR01/CONS_LATAM_AR02		| 					| CO00						| YES		| 45678		| BA0				| Internal Transport (Transport from 1 plant to another plant in the same ERP system, modeled through transport lanes in SAP)	| CONS_LATAM_AR02	| CONS_LATAM_AR01		| *				| YES			| NO			| 				| 1					| 69056			| Ship			| 99991231	| 69056			| 20180101	|
			| TRANSPORT		| SubcontractingTransport	| 93937/CONS_LATAM_V_15574/CONS_LATAM_CO01	| 					| CO00						| YES		| 15574		| BA0				| Subcontracting Transport (Transport to ship components to a subcontractor)													| CONS_LATAM_CO01	| CONS_LATAM_V_15574	| *				| YES			| NO			| 				| 28				| 93937			| Ship			| 99991231	| 93937			| 20180101	|
			| TRANSPORT		| InternalTransport			| 88382/CONS_LATAM_CO01/CONS_LATAM_PE01		| 					| CO00						| YES		| 45678		| BA0				| Internal Transport (Transport from 1 plant to another plant in the same ERP system, modeled through transport lanes in SAP)	| CONS_LATAM_PE01	| CONS_LATAM_CO01		| *				| YES			| NO			| 				| 28				| 88382			| Ship			| 99991231	| 88382			| 20180101	|
			| TRANSPORT		| InternalTransport			| 59816/CONS_LATAM_BR07/CONS_LATAM_BR12		| 					| CO00						| YES		| 12345		| BA0				| Internal Transport (Transport from 1 plant to another plant in the same ERP system, modeled through transport lanes in SAP)	| CONS_LATAM_BR12	| CONS_LATAM_BR07		| *				| YES			| NO			| 				| 77				| 59816			| Ship			| 20181231	| 59816			| 20180101	|
			| TRANSPORT		| InternalTransport			| 60109/CONS_LATAM_BR12/CONS_LATAM_BR07		| 					| CO00						| YES		| 23456		| BA0				| Internal Transport (Transport from 1 plant to another plant in the same ERP system, modeled through transport lanes in SAP)	| CONS_LATAM_BR07	| CONS_LATAM_BR12		| *				| YES			| NO			| 				| 7					| 60109			| Ship			| 20181231	| 60109			| 20180101	|

	#		Then I check region data "/dev/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
#			| key1			| key2	| businessArea	| key5	| functionalArea	| key3	| sourceSystem	| errorValue				| key4	| errorCode	| interfaceID		|
#
#		And I will remove all data with region "/dev/plan/edm_failed_data"

		And I execute xd job to merge file "GDMTransportMerge*" to "GDMTransport.tsv" by keyFields "machineTypeId, processTypeId, transportId, requirementOffset, purchasingOrganization, active, vendorId, purchasingGroup, label, toLocationId, fromLocationId, planLevelId, activeOPRERP, activeSOPERP, minQuantity, transportOffset, toProductId, transportType, endEff, fromProductId, startEff"

		And I check file data for filename "GDMTransport.tsv" by keyFields "machineTypeId, processTypeId, transportId, requirementOffset, purchasingOrganization, active, vendorId, purchasingGroup, label, toLocationId, fromLocationId, planLevelId, activeOPRERP, activeSOPERP, minQuantity, transportOffset, toProductId, transportType, endEff, fromProductId, startEff"
#			| machineTypeId	| processTypeId				| transportId								| requirementOffset	| purchasingOrganization	| active	| vendorId			| purchasingGroup	| label						| toLocationId		| fromLocationId		| planLevelId	| activeOPRERP	| activeSOPERP	| minQuantity	| transportOffset	| toProductId	| transportType	| endEff	| fromProductId	| startEff	|
#			| TRANSPORT		| InternalTransport			| 59816/CONS_LATAM_CO01/CONS_LATAM_BR12		| 					| CO00						| YES		| 50161				| BA0				| InternalTransport			| CONS_LATAM_BR12	| CONS_LATAM_CO01		| *				| YES			| NO			| 				| 77				| 59816			| Ship			| 20181231	| 59816			| 20180101	|
#			| TRANSPORT		| InternalTransport			| 59816/CONS_LATAM_BR07/CONS_LATAM_BR12		| 					| CO00						| YES		| 50161				| BA0				| InternalTransport			| CONS_LATAM_BR12	| CONS_LATAM_BR07		| *				| YES			| NO			| 				| 55				| 59816			| Ship			| 20181231	| 59816			| 20180101	|
#			| TRANSPORT		| SubcontractingTransport	| 93937/CONS_LATAM_V_15574/CONS_LATAM_CO01	| 					| CO00						| YES		| 15574				| BA0				| SubcontractingTransport	| CONS_LATAM_CO01	| CONS_LATAM_V_15574	| *				| YES			| NO			| 				| 28				| 93937			| Ship			| 99991231	| 93937			| 20180101	|

			| machineTypeId	| processTypeId				| transportId								| requirementOffset	| purchasingOrganization	| active	| vendorId	| purchasingGroup	| label																															| toLocationId		| fromLocationId		| planLevelId	| activeOPRERP	| activeSOPERP	| minQuantity	| transportOffset	| toProductId	| transportType	| endEff	| fromProductId	| startEff	|
			| TRANSPORT		| InternalTransport			| 441526/CONS_LATAM_BR12/CONS_LATAM_BR25	| 					| CO00						| YES		| 34567		| BA0				| Internal Transport (Transport from 1 plant to another plant in the same ERP system, modeled through transport lanes in SAP)	| CONS_LATAM_BR25	| CONS_LATAM_BR12		| *				| YES			| NO			| 				| 4					| 441526		| Ship			| 20181231	| 441526		| 20180101	|
			| TRANSPORT		| InternalTransport			| 441526/CONS_LATAM_BR12/CONS_LATAM_BR25	| 					| CO00						| YES		| 34567		| BA0				| Internal Transport (Transport from 1 plant to another plant in the same ERP system, modeled through transport lanes in SAP)	| CONS_LATAM_BR25	| CONS_LATAM_BR12		| *				| YES			| NO			| 				| 4					| 441526		| Ship			| 20181231	| 441526		| 20180101	|
			| TRANSPORT		| InternalTransport			| 69056/CONS_LATAM_AR01/CONS_LATAM_AR02		| 					| CO00						| YES		| 45678		| BA0				| Internal Transport (Transport from 1 plant to another plant in the same ERP system, modeled through transport lanes in SAP)	| CONS_LATAM_AR02	| CONS_LATAM_AR01		| *				| YES			| NO			| 				| 1					| 69056			| Ship			| 99991231	| 69056			| 20180101	|
			| TRANSPORT		| SubcontractingTransport	| 93937/CONS_LATAM_V_15574/CONS_LATAM_CO01	| 					| CO00						| YES		| 15574		| BA0				| Subcontracting Transport (Transport to ship components to a subcontractor)													| CONS_LATAM_CO01	| CONS_LATAM_V_15574	| *				| YES			| NO			| 				| 28				| 93937			| Ship			| 99991231	| 93937			| 20180101	|
			| TRANSPORT		| InternalTransport			| 88382/CONS_LATAM_CO01/CONS_LATAM_PE01		| 					| CO00						| YES		| 45678		| BA0				| Internal Transport (Transport from 1 plant to another plant in the same ERP system, modeled through transport lanes in SAP)	| CONS_LATAM_PE01	| CONS_LATAM_CO01		| *				| YES			| NO			| 				| 28				| 88382			| Ship			| 99991231	| 88382			| 20180101	|
			| TRANSPORT		| InternalTransport			| 59816/CONS_LATAM_BR07/CONS_LATAM_BR12		| 					| CO00						| YES		| 12345		| BA0				| Internal Transport (Transport from 1 plant to another plant in the same ERP system, modeled through transport lanes in SAP)	| CONS_LATAM_BR12	| CONS_LATAM_BR07		| *				| YES			| NO			| 				| 77				| 59816			| Ship			| 20181231	| 59816			| 20180101	|
			| TRANSPORT		| InternalTransport			| 59816/CONS_LATAM_CO01/CONS_LATAM_BR12		| 					| CO00						| YES		| 12345		| BA0				| Internal Transport (Transport from 1 plant to another plant in the same ERP system, modeled through transport lanes in SAP)	| CONS_LATAM_BR12	| CONS_LATAM_CO01		| *				| YES			| NO			| 				| 77				| 59816			| Ship			| 20181231	| 59816			| 20180101	|
			| TRANSPORT		| InternalTransport			| 60109/CONS_LATAM_BR12/CONS_LATAM_BR07		| 					| CO00						| YES		| 23456		| BA0				| Internal Transport (Transport from 1 plant to another plant in the same ERP system, modeled through transport lanes in SAP)	| CONS_LATAM_BR07	| CONS_LATAM_BR12		| *				| YES			| NO			| 				| 7					| 60109			| Ship			| 20181231	| 60109			| 20180101	|
			| TRANSPORT		| InternalTransport			| 60109/CONS_LATAM_BR12/CONS_LATAM_BR07		| 					| CO00						| YES		| 23456		| BA0				| Internal Transport (Transport from 1 plant to another plant in the same ERP system, modeled through transport lanes in SAP)	| CONS_LATAM_BR07	| CONS_LATAM_BR12		| *				| YES			| NO			| 				| 2					| 60109			| Air			| 20181231	| 60109			| 20180101	|


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

