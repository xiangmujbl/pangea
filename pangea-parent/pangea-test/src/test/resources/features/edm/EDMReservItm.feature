@pangea_test @AEAZ-4246
Feature: EDMReservItm AEAZ-4246

  Scenario: Full Load curation

    Given I import "/project_one/resb" by keyFields "rsnum,rsart,rspos,mandt"
      | mandt | rsnum      | rspos | rsart | bdart | aufnr        | posnr | xloek | kzear | matnr | werks | lgort | charg | bdter    | bdmng  | shkzg | fmeng | enmng  | erfmg  | erfme | plnum | banfn | bnfpo | kdauf | kdpos  | bwart | umwrk | umlgo | postp | ausch | nlfzt | umrez | umren | aufps | ebeln | ebelp | rgekz | kzkup | webaz | lifnr |
      | 120   | 0000000063 | 0001  |       | AR    | 000004019212 | 0020  | X     | X     |       | BR02  |       |       | 20010209 | 15.000 | H     |       | 15.000 | 15.000 | ST    |       |       | 00000 |       | 000000 | 261   |       |       | N     | 0.00  | 0     | 2     | 2     |       |       | 00000 |       |       | 0     |       |
      | 120   | 0000000063 | 0002  |       | AR    | 000004030439 | 0010  | X     |       |       | BR02  |       |       | 20010522 | 1.000  | H     |       | 0.000  | 1.000  | EA    |       |       | 00000 |       | 000000 | 261   |       |       | N     | 0.00  | 0     | 2     | 2     |       |       | 00000 |       |       | 0     |       |
    And I wait "/project_one/resb" Async Queue complete

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | localSourceSystemName | sourceSystem | sourceSystemName   |
      | project_one       | Project One           | CONS_LATAM   | Consumer Latam Ent |
      | ems               | EMS                   | EMS          | ems                |
    And I wait "/edm/source_system_v1" Async Queue complete


    When I submit task with xml file "xml/edm/EDMReservItm.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/reserv_itm" by keyFields "sourceSysCd,rsrvtnNum,rsrvtnItmNum,rsrvtnRcrdTypCd"
      | sourceSysCd | rsrvtnNum  | rsrvtnItmNum | rsrvtnRcrdTypCd | rsrvtnRqrmntTypCd | mfgOrdrNum   | bomItmNum | delInd | finIssind | matlNum | plntCd | sLocCd | btchNum | rqmtDt                  | rqmtQty | debCrdInd | qtyFxInd | wthdrnQty | entryQty | entryUomCd | mfgPlanOrdrDocNum | prNum | prLineNbr | slsOrdrDocNum | slsOrdrLineNbr | mvmtTyp | rcvngIssngPlntCd | rcvngIssngSLocCd | itmCatCd | cmpntScrapPct | leadTimeOffset | fctrNmrtrMeas | fctrDnmntrMeas | lnItmNbr | purchsOrdrNum | poLineNbr | bckflushInd | coProdInd | grProcTime | vndNum |
      | CONS_LATAM  | 0000000063 | 0001         |                 | AR                | 000004019212 | 0020      | X      | X         |         | BR02   |        |         | 2001-02-09T00:00:00.000 | 15.000  | H         |          | 15.000    | 15.000   | ST         |                   |       | 00000     |               | 000000         | 261     |                  |                  | N        | 0.00          | 0              | 2             | 2              |          |               | 00000     |             |           | 0          |        |
      | CONS_LATAM  | 0000000063 | 0002         |                 | AR                | 000004030439 | 0010      | X      |           |         | BR02   |        |         | 2001-05-22T00:00:00.000 | 1.000   | H         |          | 0.000     | 1.000    | EA         |                   |       | 00000     |               | 000000         | 261     |                  |                  | N        | 0.00          | 0              | 2             | 2              |          |               | 00000     |             |           | 0          |        |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/project_one/resb" and "/edm/reserv_itm,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/edm/reserv_itm"

    And I will remove all data with region "/plan/edm_failed_data"

