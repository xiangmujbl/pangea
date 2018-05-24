@pangea_test @AEAZ-4252
Feature: EDMMfgOrderItm AEAZ-4252

  Scenario: Full Load curation

    Given I import "/project_one/afpo" by keyFields "aufnr,posnr,mandt"
      | mandt | aufnr        | posnr | plnum      | matnr              | psamg | psmng     | wemng    | amein | pwerk | umrez | umren | webaz | elikz | verid | charg | xloek |
      | 120   | 000001000323 | 0001  | 0000327666 | 000000000000004771 | 0.000 | 10000.000 | 5214.000 | TH    | BR02  | 1     | 1     | 0     |       | V001  |       |       |
      | 120   | 000001000326 | 0001  | 0000327678 | 000000000000004775 | 0.000 | 5216.000  | 9993.000 | KI    | BR02  | 1     | 1     | 0     | X     | V001  |       |       |
    And I wait "/project_one/afpo" Async Queue complete

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | localSourceSystemName | sourceSystem | sourceSystemName   |
      | project_one        | Project One            | CONS_LATAM   | Consumer Latam Ent |
      | ems                 | EMS                     | EMS           | ems                  |
    And I wait "/edm/source_system_v1" Async Queue complete


    When I submit task with xml file "xml/edm/EDMMfgOrderItm.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/mfg_order_itm" by keyFields "srcSysCd,mfgOrdrNum,lnItmNbr"
      | srcSysCd   | mfgOrdrNum   | lnItmNbr | mfgPlnndOrdrNum | matlNum            | scrpQty | itmQty    | rcvdQty  | prdtnUomCd | plntCd | fctrNmrtrMeas | fctrDnmntrMeas | goodRcptLdDaysQty | dlvCmpltInd | prdntVrsnNum | btchNum | delInd |
      | CONS_LATAM | 000001000323 | 0001     | 0000327666      | 000000000000004771 | 0.000   | 10000.000 | 5214.000 | TH         | BR02   | 1             | 1              | 0                 |             | V001         |         |        |
      | CONS_LATAM | 000001000326 | 0001     | 0000327678      | 000000000000004775 | 0.000   | 5216.000  | 9993.000 | KI         | BR02   | 1             | 1              | 0                 | X           | V001         |         |        |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/project_one/afpo" and "/edm/mfg_order_itm,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/edm/mfg_order_itm"

    And I will remove all data with region "/plan/edm_failed_data"

