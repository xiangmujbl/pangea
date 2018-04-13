@pangea_test @AEAZ-2372
Feature: EDMPurchaseRequisitionV1 AEAZ-2372

  Scenario: Full Load curation
    #1. get atrributes from source_system_v1(rules T1)

    Given I import "/project_one/eban" by keyFields "banfn,bnfpo,werks"
      | banfn      | bnfpo | werks | matnr | meins | bsart | bstyp | bsakz | loekz | statu | estkz | ekgrp | ernam    | erdat    | txz01             | ematn | lgort | bednr | matkl    | reswk | menge | badat    | lfdat    | frgdt    | webaz | pstyp | knttp | wepos | lifnr | flief | ekorg | vrtyp | konnr | ktpnr | infnr | zugba | qunum | qupos | dispo | sernr | ebeln      | ebelp | bedat    | bsmng | ebakz | rsnum      | sobkz | fixkz | bmein | frgrl | charg | umsok | verid | adrnr | adrn2 | kunnr | emlif | lblkz | waers | gsfrg | mfrpn | mfrnr | emnfr | plifz | berid | memory | banpr | blckd | blckt | beswk | reslo | banfnCs | bnfpoCs | itemCs |
      | 0010000230 | 00010 | CO01  |       | EA    | PM    | B     |       | N     | Blank | R     | C17   | CSALGUER | 20050902 | FUENTE DE VOLTAJE |       |       |       | 95160000 |       | 4.000 | 20050901 | 20050915 | 20050901 | 0     | 0     | K     | X     |       |       |       |       |       | 00000 |       |       |       | 000   | 111   |       | 3000153227 | 00010 | 20050916 | 4.000 |       | 0000000000 |       |       |       |       |       |       |       |       |       |       |       |       | COP   |       | 222   |       |       | 0     |       |        | 05    |       |       |       |       |         | 00000   |        |
    And I wait "/project_one/eban" Async Queue complete

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem,sourceSystem"
      | sourceSystem | localSourceSystem |
      | CONS_LATAM   | [MDD FASE]        |
      | CONS_LATAM   | project_one       |
    And I wait "/edm/source_system_v1" Async Queue complete

    When I submit task with xml file "xml/edm/EDMPurchaseRequisitionV1.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/purchase_requisition_v1" by keyFields "sourceSystem,prNum,prLineNbr"
      | sourceSystem | prNum      | prLineNbr | plntCd | matlNum | prLineUomCd | prTypeCd | prCatCd | localControlInd | delInd | prStsCd | recCrtInd | prchsngGrpNum | crtByNm  | chngOnDt | prchInfoDesc      | slocCd | intrnlRefNum | locaalMaterialGroup | suplPlntCd | prLineQty | prRqstDt | needByDt | apprByDt | localPrGRLeadTimeDays | prLineCatCd | acctAsgnmtCatCd | localGRInd | localSupNum | localFixedVendor | prchsngOrgNum | poTypeCd | localAgreement | localAgreementItem | localInfoRecord | asgnSuplSrcInd | localQuotaArr | localQuotaArrItem | prMrpHrzn | bomNum | localPurchaseOrder | localItem | localPODate | localPOQuantity | prClseInd | localReservation | splStkInd | fxInd | localOrderUnit | localSubjToRelease | localBatch | localSpIndStckTfr | localProdVersion | localdelvAddrADRNR | localdelvAddrADRN2 | localCustomer | supNum | localSCVendor | localCurrency | localOverallReqRel | mfrPartNum | localManufacturer | localExternalManuf | localPDT | localIncomplete | lineStsCd | blokInd | localBlockingText | localProcuringPlant | localIssStorLoc | localXSysPReqNo | localXSysPReqItem | localXSysItemCat |
      | CONS_LATAM   | 0010000230 | 00010     | CO01   |         | EA          | PM       | B       |                 | N      | Blank   | R         | C17           | CSALGUER | 20050902 | FUENTE DE VOLTAJE |        |              | 95160000            |            | 4.000     | 20050901 | 20050915 | 20050901 | 0                     | 0           | K               | X          |             |                  |               |          |                | 00000              |                 |                |               | 000               | 111       |        | 3000153227         | 00010     | 20050916    | 4.000           |           | 0000000000       |           |       |                |                    |            |                   |                  |                    |                    |               |        |               | COP           |                    | 222        |                   |                    | 0        |                 | 05        |         |                   |                     |                 |                 | 00000             |                  |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/project_one/eban" and "/edm/purchase_requisition_v1,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/edm/purchase_requisition_v1"

    And I will remove all data with region "/plan/edm_failed_data"

