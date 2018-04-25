@pangea_test @AEAZ-2777
Feature:  OMPGdmReqFromErp-Consumption

  Scenario: Full Load Consumption

    Given I import "/edm/purchase_requisition_v1" by keyFields "sourceSystem, prNum"
    | sourceSystem | prNum | prLineNbr | plntCd | matlNum | prLineUomCd | prTypeCd | prCatCd | localControlInd | delInd | prStsCd | recCrtInd | prchsngGrpNum | crtByNm | chngOnDt | prchInfoDesc | mfrPartNum | slocCd | intrnlRefNum | locaalMaterialGroup | suplPlntCd | prLineQty | prRqstDt | needByDt | apprByDt | localPrGRLeadTimeDays | prLineCatCd | acctAsgnmtCatCd | localGRInd | localSupNum | localFixedVendor | prchsngOrgNum | poTypeCd | localAgreement | localAgreementItem | localInfoRecord | asgnSuplSrcInd | localQuotaArr | localQuotaArrItem | bomNum | localPurchaseOrder | localItem | localPODate | localPOQuantity | prClseInd | localReservation | splStkInd | fxInd | localOrderUnit | localSubjToRelease | localBatch | localSpIndStckTfr | localProdVersion | localdelvAddrADRNR | localdelvAddrADRN2 | localCustomer | supNum | localSCVendor | localCurrency | localOverallReqRel | localManufacturer | localExternalManuf | localPDT | prMrpHrzn | localIncomplete | lineStsCd | blokInd | localBlockingText | localProcuringPlant | localIssStorLoc | localXSysPReqNo | localXSysPReqItem | localXSysItemCat |

    Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | sourceSystem | localMaterialNumber | localRefDescription             | localMaterialType | localBaseUom | materialNumber | refDescription                           | materialType | localDpParentCode | parentCode    | globalDpParentCode | form | category | subBrand | brand | franchise | globalBusinessUnit | productFamily | localManufacturingTechnology | manufacturingTechnology | localMaterialGroup | materialGroup | flagForDeletion | materialStatus | division | batchManageIndicator | minRemShelfLife | totalShelfLife | primaryPlanningCode |
      | CONS_LATAM   | BR01                | JS COTTON BALLS 50 GRX20 T50P35 | FERT              | CRT          | 7891010014803  | J'S SOFT DEO HIDR MAC PROL 12XL400P320ML | FERT         | LDPC01            | 7891010931582 | GDPC01             | 101  | 1001     | 101      | TD001 | FCH001    | GFO001             | AB101         | LMT01                        | Wipes                   | 01                 | MG01          |                 | 08             | 10       | X                    | 180             | 9999           | 1233                |
      | CONS_LATAM   | BR02                | JS COTTON BALLS 50 GRX20 T50P35 | FERT              | EA           | 7891010014804  | J'S SOFT DEO HIDR MAC PROL 12XL400P320ML | FERT         | LDPC02            | 7891010931582 | GDPC02             | 102  | 1002     | 102      | TD002 | FCH002    | GFO002             | AC102         | LMT02                        | Wipes                   | 02                 | MG02          |                 | 08             | 10       | X                    | 180             | 9999           | 1234                |

    Given I import "/plan/cns_plan_unit" by keyFields "plantFlag,unit,localUom,sourceSystem,localUomName"
      | plantFlag | unit | localUom | sourceSystem | localUomName |
      | CS        | DPSP | CS       | CRT          | CONS_LATAM   |
      | EA        | DPSP | EA       | EA           | CONS_LATAM   |

    And I wait "/plan/cns_plan_unit" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmReqFromErp.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "REQFROMERP.tsv"

    And I check file data for filename "REQFROMERP.tsv" by keyFields "SupplyId"
    | SupplyId	            | Active |	ActiveOPRERP |	ActiveSOPERP |	FromDate |	LABEL                |	LocationId       | MACHINECAPACITY |	MACHINETYPEID |	MaxQuantity |	MaxQuantityType |	MinQuantity |	MinQuantityType |	PLANLEVELID |	Preference |	PROCESSTYPEID |	ProductId |	PURCHASINGGROUP |	PURCHASINGORGANIZATION | SupplierId |	ToDate |	VENDOR  | 	TransportType |
    | 123Cons_latamMX01V789 | Yes	  | Yes	         | Yes           |	1012017  |	INTERNAL TRANSPORT   | CONS_LATAMCO01	 |  INFINITE	   | SUPPLY           |	100         |		            |		20      |                   |   *           |	X          |	1             |	PPC001    |	U96	            |           NA00           |  	CO01    | 12312020 |	VCO01   |	DEFAULT       |


    And I delete the test data

    And I will remove all data with region "/edm/purchase_requisition_v1"

    And I will remove all data with region "/edm/material_global_v1"

    And I will remove all data with region "/plan/cns_plan_unit"

    And I will remove the test file on sink application "REQFROMERP.tsv"

