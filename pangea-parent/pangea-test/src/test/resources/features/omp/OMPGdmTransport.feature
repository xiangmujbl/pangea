@pangea_test @AEAZ-2778
Feature:  OMPGdmTransport

  Scenario: Full Load consumption


    Given I import "/plan/cns_tlane_item" by keyFields "sequenceNumber,tlaneName"
	| sequenceNumber	| tlaneName		| validFrom	| validTo	| originLocation	| destinatonLocation	| materialNumber	| mode	| leadTime	|
	| 1					| BR12toBR07	| 1012018	| 12312018	| CONS_LATAM_BR12	| CONS_LATAM_BR07		| 59816				| Ship	| 7			|
	| 2					| BR12toBR25	| 1012018	| 12312018	| CONS_LATAM_BR12	| CONS_LATAM_BR25		| 59816				| Ship	| 4			|
	| 3					| CO01toBR12	| 1012018	| 12312018	| CONS_LATAM_CO01	| CONS_LATAM_BR12		| 59816				| Ship	| 77		|
    And I wait "/plan/cns_tlane_item" Async Queue complete


	Given I import "/plan/cns_tlane_item_exception" by keyFields "sequenceNumber,tlaneName"
	| validTo	| originLocation	  	| materialNumber	| processTypeId	      | validFrom | destinationLocation	| leadTime	| mode	  | sequenceNumber	| tlaneName   | deletionIndicator   | refSeqNumTlaneItem |
	| 12312018  | CONS_LATAM_CO01   	| 59816           	| INTERNALTRANSPORT   | 1012018   | CONS_LATAM_BR12     | 7         | Ship    | 1               | CO01toBR12  |                     |                    |
	| 12312018  | CONS_LATAM_BR12   	| 59816           	| INTERNALTRANSPORT   | 1012018   | CONS_LATAM_BR12     | 5         | Ship    | 2               | CO01toBR12  | X                   | 1                  |
	| 12312018  | CONS_LATAM_BR07   	| 59816           	| INTERNALTRANSPORT   | 1012018   | CONS_LATAM_BR12     | 5         | Ship    | 2               | BR07toBR12  |                     |                    |
	And I wait "/plan/cns_tlane_item_exception" Async Queue complete


	Given I import "/plan/cns_process_type" by keyFields "processTypeId"
	| processTypeId 	| processTypeDesc 	|
	| INTERNALTRANSPORT | INTERNALTRANSPORT	|
	And I wait "/plan/cns_process_type" Async Queue complete


	Given I import "/edm/material_plant_v1" by keyFields "localMaterialNumber,localPlant,sourceSystem"
	| sourceSystem | localMaterialNumber | localPlant | materialNumber | plant | localPlantStatus | plantStatus | localProcurementType | localDeletionFlagPlant | localFixedlotsize | localMaximumLotSize | localMinimumLotSize | localRoundingvalueForPoq | localLotsize | localMrpType | localMrpController | localInHouseProcessingTime | localSafetyStock | localMinimumSafetyStock | localProductionSupervisor | localProductionUnit | localPosttoInspectionStock | localComponentScrapInPercent | localCriticalpart | localAbcIndicator | localMaximumStocklevel | localCheckingGroupforAvailabilityCheck | localPlannedDeliveryTimeInDays | localDependentRequirements | localSafetytimeIndicator | localSafetyTime | localSpecialProcurementType | localPlanningStrategyGroup | localConsumptionPeriodBackward | localConsumptionPeriodForward | localConsumptionMode | localGoodsReceiptProcessingTimeInDays | localBatchManagementRequirmentIndicator | localPlanningTimeFence | localPosttoinspstk | localComponentScrap | localBaseQuantity |
	| CONS_LATAM   | 59816          | BR01       | 59816  | 00001 | 04               | 100         | F                    | BR01                   | 0.000             | 0.000               | 0.000               | 0.000                    | EX0          | ND           | 151                | 0                          | 0.000            | 0.000                   | 001                       | 0.000               | 0.000                      | 0.000                        | 0.000             | 0.000             | 0.000                  | 01                                     | 0                              | 0                          | 0                        | 0               | 0                           | 0                          | 0                              | 0                             | 0                    | 0                                     | 0                                       | 0                      | 0.000              | 0.000               | BA0               |
	| CONS_LATAM   | 59816          | BR02       | 59816  | 00002 | 05               | 101         | F                    | BR02                   | 0.001             | 0.001               | 0.001               | 0.001                    | EX1          | X4           | 152                | 2                          | 0.001            | 0.001                   | 002                       | 0.001               | 0.001                      | 0.001                        | 0.001             | 0.001             | 0.001                  | 02                                     | 1                              | 1                          | 1                        | 1               | 1                           | 1                          | 1                              | 1                             | 1                    | 1                                     | 1                                       | 1                      | 0.001              | 0.001               | BA1               |
	| CONS_LATAM   | 59816          | BR03       | 59816  |       | 06               | 102         | X                    | BR03                   | 0.002             | 0.002               | 0.002               | 0.002                    | EX2          | ND           | 153                | 1                          | 0.002            | 0.002                   | 003                       | 0.002               | 0.002                      | 0.002                        | 0.002             | 0.002             | 0.002                  | 01                                     | 2                              | 2                          | 2                        | 2               | 2                           | 2                          | 2                              | 2                             | 2                    | 2                                     | 2                                       | 2                      | 0.002              | 0.002               | BA2               |
	| CONS_LATAM   | 59816          | BR04       | 59816  |       | 07               |             | E                    | BR04                   | 0.003             | 0.003               | 0.003               | 0.003                    | EX3          | X4           | 154                | 0                          | 0.003            | 0.003                   | 004                       | 0.003               | 0.003                      | 0.003                        | 0.003             | 0.003             | 0.003                  | 01                                     | 3                              | 3                          | 3                        | 3               | 3                           | 3                          | 3                              | 3                             | 3                    | 3                                     | 3                                       | 3                      | 0.003              | 0.003               | BA3               |
	| CONS_LATAM   | 59816          | BR05       |                | 00005 | 08               | 104         | E                    | BR05                   | 0.004             | 0.004               | 0.004               | 0.004                    | EX4          | ND           | 155                | 2                          | 0.004            | 0.004                   | 005                       | 0.004               | 0.004                      | 0.004                        | 0.004             | 0.004             | 0.004                  | 01                                     | 4                              | 4                          | 4                        | 4               | 4                           | 4                          | 4                              | 4                             | 4                    | 4                                     | 4                                       | 4                      | 0.004              | 0.004               | BA4               |
	| CONS_LATAM   | 59816          | BR06       |                |       | 09               | 105         | F                    | BR06                   | 0.005             | 0.005               | 0.005               | 0.005                    | EX5          | X4           | 156                | 3                          | 0.005            | 0.005                   | 006                       | 0.005               | 0.005                      | 0.005                        | 0.005             | 0.005             | 0.005                  | 01                                     | 5                              | 5                          | 5                        | 5               | 5                           | 5                          | 5                              | 5                             | 5                    | 5                                     | 5                                       | 5                      | 0.005              | 0.005               | BA5               |
	| CONS_LATAM   | 59816          | BR07       |                |       | 10               |             | X                    | BR07                   | 0.006             | 0.006               | 0.006               | 0.006                    | EX6          | ND           | 157                | 0                          | 0.006            | 0.006                   | 007                       | 0.006               | 0.006                      | 0.006                        | 0.006             | 0.006             | 0.006                  | 01                                     | 6                              | 6                          | 6                        | 6               | 6                           | 6                          | 6                              | 6                             | 6                    | 6                                     | 6                                       | 6                      | 0.006              | 0.006               | BA6               |
	And I wait "/edm/material_plant_v1" Async Queue complete


	Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
	| sourceSystem | localMaterialNumber | localRefDescription                  | localMaterialType | localBaseUom | materialNumber | refDescription                           | materialType | baseUom | localDpParentCod | parentCode    | globalDpParentCode | form   | category | subBrand | brand  | franchise | globalBusinessUnit | productFamily | localManufacturingTechnology | manufacturingTechnology | localMaterialGroup | materialGroup | flagForDeletion | materialStatus | division | batchManageIndicator | minRemShelfLife | totalShelfLife | primaryPlanningCode |
	| CONS_LATAM   | 59816               | JS COTTON BALLS 50 GRX20 T50P35      | FERT              | KI           | 59816  		| JS SOFT DEO HIDR MAC PROL 12XL400P320ML | FERT         | EA      |                  | 7891010931582 |                    | 116151 | 6        | 1V       | BRD001 | FCH001    | GFO001             | Not Assigned  |                              | Wipes                   | 01                 |               |                 | 08             | 10       | X                    | 180             | 9999           | 59816               |
	| CONS_LATAM   | 59817               | BOLAS DE ALGODAO J 20X50 LV50 PG35 | FERT              | KI           | 59816  		| JS BODY DE HI 24H MA PR 12xL400 P320ML N | FERT         | EA      |                  | 7891010931642 |                    | 116151 | 0006     | 500003   | BRD001 | FCH001    | GFO001             | Not Assigned  |                              | Toothbrushes            | 01                 |               |                 | 08             | 10       | X                    | 180             | 1095           | 59816               |
	| CONS_LATAM   | 59818               | REACH TOOTHBRUSH COMPACT SOFT T2P1   | FERT              | KI           |                |                                          |              |         |                  |               |                    |        |          |          |        |           |                    |               |                              |                         | 01                 |               |                 | 08             | 10       | X                    | 180             | 730            | 59816               |
	And I wait "/edm/material_global_v1" Async Queue complete


	Given I import "/edm/source_list_v1" by keyFields "sourceSystem,localMaterialNumber"
	| sourceSystem | localMaterialNumber | localPlant | localNumberofSourceListRecord | materialNumber	| plant | localCreatedOn | localCreatedBy | localSourceListRecordValidFrom | localSourceListRecordValidTo | localVendorAccountNumber | localFixedvendor | localAgreementNumber | localAgreementItem | localFixedOutlinePurchaseAgreementItem | localPlantfromWhichMaterialisProcured | localMatForManufPartNumber | localBlockedSourceofSupply | localPurchasingOrganization | localPurchasingDocumentCategory | localCategoryofSourceListRecord | localSourceListUsageinMaterialsPlanning |
	| CONS_LATAM   | 59816               | BR06       | 4                             | 59816           | AR01  | 20030306       | CCASTRO1       | 20030206                       | 29991231                     | 8917                     |                  |                      | 0                  |                                        | BR12                                  |                            |                            | BR00                        |                                 | 7                               |                                         |
	| CONS_LATAM   | 59817               | BR06       | 2                             | 59816     		| AR01  | 20020522       | CARGA03        | 20020301                       | 99991231                     | 8917                     |                  |                      | 0                  |                                        | BR12                                  |                            |                            | BR00                        |                                 | 7                               |                                         |
	| CONS_LATAM   | 59818               | BR07       | 2                             | 59816     		|       | 20020522       | JPINTO4        | 20020422                       | 29991231                     | 8917                     |                  |                      | 0                  |                                        | BR12                                  |                            |                            | BR00                        |                                 | 7                               |                                         |
	And I wait "/edm/source_list_v1" Async Queue complete


	Given I import "/plan/cns_spl_pln_loc" by keyFields "sourceSystem,localNumber"
	| sourceSystem	| localNumber	|
	| CONS_LATAM	| BR01			|
	| CONS_LATAM	| BR03			|
	| CONS_LATAM	| BR04			|
	| CONS_LATAM	| BR12			|
	| CONS_LATAM	| CO01			|
	| CONS_LATAM	| BR06			|
	| CONS_LATAM	| BR05			|
	| CONS_LATAM	| BR02			|
	| CONS_LATAM	| BR08			|
	| CONS_LATAM	| BR25			|
	And I wait "/plan/cns_spl_pln_loc" Async Queue complete


	Given I import "/plan/cns_material_plan_status" by keyFields "sourceSystem,localMaterialNumber,localPlant"
	| sourceSystem | localMaterialNumber | localPlant | materialNumber | localParentCode | ppc | active | dpRelevant | spRelevant | parentActive | noPlanRelevant |
	| CONS_LATAM   | 59816               | BR01       | 59816          | G3a             | G4a | X      | X          | X          | X            | X              |
	| CONS_LATAM   | 59816               | BR02       | 59816          | G3b             | G4b | X      | X          | X          | X            | X              |
	| CONS_LATAM   | 59816               | BR03       | 59816          |                 |     |        |            | X          |              | X              |
	| CONS_LATAM   | 59816               | BR04       | 59816          |                 |     |        |            |            |              | X              |
	And I wait "/plan/cns_material_plan_status" Async Queue complete


	Given I import "/edm/plant_v1" by keyFields "sourceSystem,localPlant"
	| sourceSystem | localPlant | plant |
	| CONS_LATAM   | BR01       | BR01 |
	| CONS_LATAM   | BR02       | BR02 |
	| cons         | BR03       | BR03 |
	| EMS          | BR04       | BR04 |
	| CONS_LATAM   | BR05       | BR05 |
	| CONS         | BR06       | BR06 |
	| EMS          | BR07       | BR07 |
	| Project_one  | BR08       | BR08 |
	And I wait "/edm/plant_v1" Async Queue complete


	When I submit task with xml file "xml/omp/OMPGdmTransportException.xml" and execute file "jar/pangea-view.jar"

	Then A file is found on sink application with name "GDMTransportEx.tsv"

#    When I submit task with xml file "xml/omp/OMPGdmTransport.xml" and execute file "jar/pangea-view.jar"

#    Then A file is found on sink application with name "GDMTransport.tsv"

#	todo: dont have validated data to test against
#    And I check file data for filename "GDMTransportEx.tsv" by keyFields "sequenceNumber,tlaneName"
#      | sequenceNumber	| tlaneName		| validFrom	| validTo	| originLocation	| destinatonLocation	| mode	| leadTime	| triangulationDetail	| processTypeId	| sourceSystem	| criticalParameter1	| criticalParameter1Low	| criticalParameter1High	| criticalParameter1Operator	| criticalParameter1IE	| sourceSystem	| criticalParameter2	| criticalParameter2Low	| criticalParameter2High	| criticalParameterOperator	| criticalParameter2IE	| sourceSystem	| criticalParameter3	| criticalParameter3Low	| criticalParameter3High	| criticalParameter3Operator	| criticalParameter3IE	| sourceSystem	| criticalParameter4	| criticalParameter4Low	| criticalParameter4High	| criticalParameter4Operator	| criticalParameter4IE	| sourceSystem	| criticalParameter5Value	| criticalParameter5Low	| criticalParameter5High	| criticalParameter5Opeartor	| criticalParameter5IE	| sourceSystem	| criticalParameter6Value	| criticalParameter6Low	| criticalParameter6High	| criticalParameter6Operator	| criticalParameter6IE	| sourceSystem	| criticalParameter7Value	| criticalParameter7Low	| criticalParameter7High	| criticalParameter7Opearator	| criticalParameter7IE	| sourceSystem	| criticalParameter8Value	| criticalParameter8Low	| criticalParameter8High	| criticalParameter8Operator	| criticalParameter8IE	| sourceSystem	| criticalParameter9Value	| criticalParameter9Low	| criticalParameter9High	| criticalParameter9Operator	| criticalParameter9IE	| sourceSystem	| criticalParameter10Value	| criticalParameter10Low	| criticalParameter10High	| criticalParameter10Operator	| criticalParameter10IE	|
#      | 1				| CO01toMX02	| 1012018	| 12312018	| CONS_LATAM_CO01	| CONS_LATAM_MX02		| Ship	| 15		| Yes					| CONS_LATAM	| P grp			| 1						| 100					| BT						| I								| CONS_LATAM			| P grp			| 1						| 100					| BT						| I							| 						| 				| 						| 						| 							| 								| 						| 				| 						| 						| 							| 								| 						| 				| 							| 						| 							| 								| 						| 				| 							| 						| 							| 								| 						| 				| 							| 						| 							| 								| 						| 				| 							| 						| 							| 								| 						| 				| 							| 						| 							| 								| 						| 				| 							| 							| 							| 								| 						|
#      | 2				| AR01 to AR02	| 1012018	| 12312018	| CONS_LATAM_AR01	| CONS_LATAM_AR02		| Air	| 1			| 						| CONS_LATAM	| P grp			| 2						| 						| >							| I								| CONS_LATAM			| P grp			| 2						| 						| >							| I							| 						| 				| 						| 						| 							| 								| 						| 				| 						| 						| 							| 								| 						| 				| 							| 						| 							| 								| 						| 				| 							| 						| 							| 								| 						| 				| 							| 						| 							| 								| 						| 				| 							| 						| 							| 								| 						| 				| 							| 						| 							| 								| 						| 				| 							| 							| 							| 								| 						|
#
#	todo: dont have validated data to test against
#    And I check file data for filename "GDMTransport.tsv" by keyFields "sequenceNumber,tlaneName"
#      | sequenceNumber	| tlaneName	| validFrom	| validTo	| originLocation	| destinatonLocation	| materialNumber	| mode	| leadTime in days	| Deletion Indicator	| Process Type Id	| Ref seqence number from cns_tlane_item	|
#      | 1				| CO to MX	| 1012018	| 12319999	| CONS-LATAM_CO01	| CONS-LATAM_MX02		| 12345				| Ship	| 15				| 						| 1					| 435										|
#      | 2				| CO to AR	| 1012018	| 12319999	| CONS-LATAM_CO01	| CONS-LATAM_AR02		| 12345				| Ship	| 3					| X						| 2					| 2008										|
#
#
#	Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
#	  | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |
#
#	And I compare the number of records between "/omp/gdm_transport" and "/plan/cns_tlane_item_exception,/plan/edm_failed_data"


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


#    And I will remove the test file on sink application "GDMTransportEx.tsv"
#    And I will remove the test file on sink application "GDMTransport.tsv"

