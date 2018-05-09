@pangea_test @AEAZ-2777
Feature:  OMPGdmReqFromErp-Consumption

  Scenario: Full Load Consumption

    Given I import "/edm/purchase_requisition_v1" by keyFields "prNum"
      | sourceSystem | prNum      | prLineNbr | plntCd | matlNum | prLineUomCd | prTypeCd | prCatCd | localControlInd | delInd | prStsCd | recCrtInd | prchsngGrpNum | crtByNm  | chngOnDt | prchInfoDesc      | slocCd | intrnlRefNum | locaalMaterialGroup | suplPlntCd  | prLineQty | prRqstDt | needByDt   | apprByDt | localPrGRLeadTimeDays | prLineCatCd | acctAsgnmtCatCd | localGRInd | localSupNum | localFixedVendor | prchsngOrgNum | poTypeCd | localAgreement | localAgreementItem | localInfoRecord | asgnSuplSrcInd | localQuotaArr | localQuotaArrItem | prMrpHrzn | bomNum | localPurchaseOrder | localItem | localPODate | localPOQuantity | prClseInd | localReservation | splStkInd | fxInd | localOrderUnit | localSubjToRelease | localBatch | localSpIndStckTfr | localProdVersion | localdelvAddrADRNR | localdelvAddrADRN2 | localCustomer | supNum | localSCVendor | localCurrency | localOverallReqRel | mfrPartNum | localManufacturer | localExternalManuf | localPDT | localIncomplete | lineStsCd | blokInd | localBlockingText | localProcuringPlant | localIssStorLoc | localXSysPReqNo | localXSysPReqItem | localXSysItemCat |   prDocId   |
      | CONS_LATAM   | 1734545711 |    10     | MX02   | 7047792 | EA          | NB       | B       |                 | Blank  | N       | R         | C17           | CSALGUER | 20050902 | FUENTE DE VOLTAJE |        |              | 95160000            |     MX01    | 4692      | 20050901 | 04/3/2018  | 20050901 | 0                     | 0           | K               | X          |             |   6109           |               |          |                | 00000              |                 |                |               | 000               | 111       |        | 3000153227         | 00010     | 20050916    | 4.000           |           | 0000000000       |           |       |                |                    |            |                   |                  |                    |                    |               |        |               | COP           |                    | 222        |                   |                    | 0        |                 | 05        |         |                   |                     |                 |                 | 00000             |                  |  1734545711 |
      | CONS_LATAM   | 1734545719 |    10     | MX02   | 7047793 | EA          | NB       | B       |                 | Blank  | N       | R         | C17           | CSALGUER | 20050902 | FUENTE DE VOLTAJE |        |              | 95160000            |     MX01    | 5352      | 20050901 | 09/18/2018 | 20050901 | 0                     | 0           | K               | X          |             |   6109           |               |          |                | 00000              |                 |                |               | 000               | 111       |        | 3000153227         | 00010     | 20050916    | 4.000           |           | 0000000000       |           |       |                |                    |            |                   |                  |                    |                    |               |        |               | COP           |                    | 222        |                   |                    | 0        |                 | 05        |         |                   |                     |                 |                 | 00000             |                  |  1734545719 |
      | CONS_LATAM   | 1734545852 |    10     | MX02   | 7047794 | EA          | NB       | B       |                 | Blank  | N       | R         | C17           | CSALGUER | 20050902 | FUENTE DE VOLTAJE |        |              | 95160000            |     MX01    | 4890      | 20050901 | 05/25/2019 | 20050901 | 0                     | 0           | K               | X          |             |   6109           |               |          |                | 00000              |                 |                |               | 000               | 111       |        | 3000153227         | 00010     | 20050916    | 4.000           |           | 0000000000       |           |       |                |                    |            |                   |                  |                    |                    |               |        |               | COP           |                    | 222        |                   |                    | 0        |                 | 05        |         |                   |                     |                 |                 | 00000             |                  |  1734545852 |
      | CONS_LATAM   | 1734545796 |    10     | MX02   | 7047795 | EA          | NB       | B       |                 | Blank  | N       | R         | C17           | CSALGUER | 20050902 | FUENTE DE VOLTAJE |        |              | 95160000            |     MX01    | 4728      | 20050901 | 01/8/2019  | 20050901 | 0                     | 0           | K               | X          |             |   6109           |               |          |                | 00000              |                 |                |               | 000               | 111       |        | 3000153227         | 00010     | 20050916    | 4.000           |           | 0000000000       |           |       |                |                    |            |                   |                  |                    |                    |               |        |               | COP           |                    | 222        |                   |                    | 0        |                 | 05        |         |                   |                     |                 |                 | 00000             |                  |  1734545796 |
      | CONS_LATAM   | 1734545850 |    10     | MX02   | 7047796 | EA          | NB       | B       |                 | Blank  | N       | R         | C17           | CSALGUER | 20050902 | FUENTE DE VOLTAJE |        |              | 95160000            |     MX01    | 4878      | 20050901 | 04/2/2019  | 20050901 | 0                     | 0           | K               | X          |             |   6109           |               |          |                | 00000              |                 |                |               | 000               | 111       |        | 3000153227         | 00010     | 20050916    | 4.000           |           | 0000000000       |           |       |                |                    |            |                   |                  |                    |                    |               |        |               | COP           |                    | 222        |                   |                    | 0        |                 | 05        |         |                   |                     |                 |                 | 00000             |                  |  1734545850 |

    And I wait "/edm/purchase_requisition_v1" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber,sourceSystem"
      | localMaterialNumber | materialNumber | sourceSystem | primaryPlanningCode | localBaseUom |
      | 000000000007047792  | 7047792        | CONS_LATAM   | 99059               | CS           |
      | 000000000007047793  | 7047793        | CONS_LATAM   | 99059               | EA           |
      | 000000000007047794  | 7047794        | CONS_LATAM   | 99059               | GS           |
      | 000000000007047795  | 7047795        | CONS_LATAM   | 99059               | ED           |
      | 000000000007047796  | 7047796        | CONS_LATAM   | 99059               | AS           |

    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/plan/cns_plan_unit" by keyFields "plantFlag,unit,localUom,sourceSystem,localUomName"
      | plantFlag | unit | localUom | sourceSystem | localUomName |
      | CS        | DPSP | CS       | CONS_LATAM   |   CRT        |
      | EA        | DPSR | EA       | CONS_LATAM   |   CRT        |
      | EA        | DPST | GS       | CONS_LATAM   |   CRT        |
      | EA        | DPSY | ED       | CONS_LATAM   |   CRT        |
      | EA        | DPSU | AS       | CONS_LATAM   |   CRT        |

    And I wait "/plan/cns_plan_unit" Async Queue complete

    Given I import "/plan/cns_material_plan_status" by keyFields "sourceSystem,localMaterialNumber,localPlant,spRelevant"
      | sourceSystem | localPlant   |   localMaterialNumber | spRelevant |
      | CONS_LATAM   |   MX02       |   000000000007047792  |      X     |
      | CONS_LATAM   |   MX02       |   000000000007047793  |      X     |
      | CONS_LATAM   |   MX02       |   000000000007047794  |      X     |
      | CONS_LATAM   |   MX02       |   000000000007047795  |      X     |
      | CONS_LATAM   |   MX02       |   000000000007047796  |      X     |

    And I wait "/plan/cns_material_plan_status" Async Queue complete

    Given I import "/plan/cns_plan_object_filter" by keyFields "sourceObjectTechName,sourceSystem"
      | sourceObjectTechName | sourceSystem | inclusion_Exclusion | sourceObjectAttribute1Value | sourceObjectAttribute1 | sourceObjectAttribute2Value | sourceObjectAttribute2 |
      |         PM           |   CONS_LATAM |          I          |          MX02               |          plntCd        |            PM               |          prTypeCd      |

    And I wait "/plan/cns_plan_object_filter" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmReqFromErp.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "REQFROMERP.tsv"

    And I check file data for filename "REQFROMERP.tsv" by keyFields "REQFromERPId"
      | ManualOffset | REQType |	VERID |	WRK02  |	DELKZ  | BLCKT | PRIO_URG | LocationId      | ProductId | DeliveryDate | PLIFZ | DELETED | ERPId      | DELPS | DELNR      | TotalQuantity | UnitId | BLCKD| FLIEF   |  REQFromERPId         |
      |              | NB      |	      |	MX01   |	BA     |       |          | CONS_LATAM_MX02 | 99059     | 2018/04/03   | 0     |  FALSE  | 1734545711 |  10   | 1734545711 |	 4692       |	 EA   |      | 6109    | CONS_LATAM/1734545711 |
      |              | NB      |	      |	MX01   |	BA     |       |          | CONS_LATAM_MX02 | 99059     | 2019/01/08   | 0     |  FALSE	  | 1734545796 |  10   | 1734545796 |	 4728       |	 EA   |      | 6109    | CONS_LATAM/1734545796 |
      |              | NB      |	      |	MX01   |	BA     |       |          | CONS_LATAM_MX02 | 99059     | 2019/05/27   | 0     |  FALSE  | 1734545852 |  10   | 1734545852 |	 4890       |	 EA   |      | 6109    | CONS_LATAM/1734545852 |
      |              | NB      |	      |	MX01   |	BA     |       |          | CONS_LATAM_MX02 | 99059     | 2019/04/02   | 0     |  FALSE  | 1734545850 |  10   | 1734545850 |	 4878       |	 EA   |      | 6109    | CONS_LATAM/1734545850 |
      |              | NB      |	      |	MX01   |	BA     |       |          | CONS_LATAM_MX02 | 99059     | 2018/09/18   | 0     |  FALSE  | 1734545719 |  10   | 1734545719 |	 5352       |	 EA   |      | 6109    | CONS_LATAM/1734545719 |

    And I delete the test data

    And I will remove all data with region "/edm/purchase_requisition_v1"

    And I will remove all data with region "/edm/material_global_v1"

    And I will remove all data with region "/plan/cns_material_plan_status"

    And I will remove all data with region "/plan/cns_plan_unit"

    And I will remove all data with region "/plan/cns_plan_object_filter"

    And I will remove the test file on sink application "REQFROMERP.tsv"

