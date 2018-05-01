@pangea_test @AEAZ-2777
Feature:  OMPGdmReqFromErp-Consumption

  Scenario: Full Load Consumption

    Given I import "/edm/purchase_requisition_v1" by keyFields "prNum"
      | sourceSystem | prNum      | prLineNbr | plntCd | matlNum | prLineUomCd | prTypeCd | prCatCd | localControlInd | delInd | prStsCd | recCrtInd | prchsngGrpNum | crtByNm  | chngOnDt | prchInfoDesc      | slocCd | intrnlRefNum | locaalMaterialGroup | suplPlntCd | prLineQty | prRqstDt | needByDt       | apprByDt | localPrGRLeadTimeDays | prLineCatCd | acctAsgnmtCatCd | localGRInd | localSupNum | localFixedVendor | prchsngOrgNum | poTypeCd | localAgreement | localAgreementItem | localInfoRecord | asgnSuplSrcInd | localQuotaArr | localQuotaArrItem | prMrpHrzn | bomNum | localPurchaseOrder | localItem | localPODate | localPOQuantity | prClseInd | localReservation | splStkInd | fxInd | localOrderUnit | localSubjToRelease | localBatch | localSpIndStckTfr | localProdVersion | localdelvAddrADRNR | localdelvAddrADRN2 | localCustomer | supNum | localSCVendor | localCurrency | localOverallReqRel | mfrPartNum | localManufacturer | localExternalManuf | localPDT | localIncomplete | lineStsCd | blokInd | localBlockingText | localProcuringPlant | localIssStorLoc | localXSysPReqNo | localXSysPReqItem | localXSysItemCat |
      | CONS_LATAM   | 0010000230 | 00010     | CS     | 7047792 | EA          | PM       | B       |                 | Blank  | N       | R         | C17           | CSALGUER | 20050902 | FUENTE DE VOLTAJE |        |              | 95160000            |     spd    | 4.000     | 20050901 | 20050915121030 | 20050901 | 0                     | 0           | K               | X          |             |   FxVend         |               |          |                | 00000              |                 |                |               | 000               | 111       |        | 3000153227         | 00010     | 20050916    | 4.000           |           | 0000000000       |           |       |                |                    |            |                   |   1              |                    |                    |               |        |               | COP           |                    | 222        |                   |                    | 0        |                 | 05        |    1    |     Blocktext     |                     |                 |                 | 00000             |                  |

    And I wait "/edm/purchase_requisition_v1" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber,sourceSystem"
      | localMaterialNumber | materialNumber | sourceSystem | primaryPlanningCode | localBaseUom |
      | 000000000007703910  | 7703910        | CONS_LATAM   | 7703910             | CS           |
      | 000000000007047792  | 7047792        | CONS_LATAM   | 7047792             | EA           |

    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/plan/cns_plan_unit" by keyFields "plantFlag,unit,localUom,sourceSystem,localUomName"
      | plantFlag | unit | localUom | sourceSystem | localUomName |
      | CS        | DPSP | CS       | CONS_LATAM   |   CRT        |
      | EA        | DPSP | EA       | CONS_LATAM   |   EA         |

    And I wait "/plan/cns_plan_unit" Async Queue complete

    Given I import "/plan/cns_material_plan_status" by keyFields "sourceSystem,localMaterialNumber,localPlant,spRelevant"
      | sourceSystem | localPlant   |   localMaterialNumber | spRelevant |
      | CONS_LATAM   |   CS         |   000000000007047792  |      X     |
      | CONS_LATAM   |   EA         |   000000000007703910  |      X     |

    And I wait "/plan/cns_material_plan_status" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmReqFromErp.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "REQFROMERP.tsv"

    And I check file data for filename "REQFROMERP.tsv" by keyFields "REQFromERPId"
    | ManualOffset | REQType |	VERID |	WRK02  |	DELKZ  |	BLCKT  | PRIO_URG  | LocationId    | ProductId | DeliveryDate        |	PLIFZ |	DELETED | ERPId | DELPS | DELNR      | TotalQuantity |	UnitId | BLCKD | FLIEF  | REQFromERPId |
    |              | PM      |	1     |	spd    |	BA     | Blocktext |           | CONS_LATAM_CS | 7047792   | 2005/09/15 12:10:30 |	0     |	FALSE	| 1111  | 00010	| 0010000230 |	4.000        |	DPSP   |   1   | FxVend |	1111       |

    And I delete the test data

    And I will remove all data with region "/edm/purchase_requisition_v1"

    And I will remove all data with region "/edm/material_global_v1"

    And I will remove all data with region "/plan/cns_material_plan_status"

    And I will remove all data with region "/plan/cns_plan_unit"

    And I will remove the test file on sink application "REQFROMERP.tsv"

