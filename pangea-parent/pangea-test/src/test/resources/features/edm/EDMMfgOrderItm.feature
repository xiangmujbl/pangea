@pangea_test @AEAZ-7851
Feature: EDMMfgOrderItm AEAZ-7851

  Scenario: Full Load curation

    Given I import "/project_one/afpo" by keyFields "aufnr,posnr,mandt"
      | mandt | aufnr        | posnr | plnum      | matnr              | psamg | psmng | wemng | amein | pwerk | umrez | umren | webaz | elikz | verid | charg | xloek | meins |
      | 120   | 000001647950 | 0001  | 0109281711 | 000000000000069340 |       | 2500  | 2500  | PC    | BR12 | 1     | 1     |       |       | V012  |       |       | PC    |
      | 120   | 000001647951 | 0001  | 0109281712 | 000000000000069341 |       | 2500  | 2500  | PC    | BR12 | 1     | 1     |       |       | V012  |       |       | PC    |
      | 120   | 000001647952 | 0001  | 0109281713 | 000000000000069342 |       | 2500  | 2500  | PC    | BR12 | 1     | 1     |       |       | V012  |       |       | PC    |
      | 120   | 000001647953 | 0001  | 0109281714 | 000000000000069343 |       | 2500  | 2500  | PC    | BR12 | 1     | 1     |       |       | V012  |       |       | PC    |
    And I wait "/project_one/afpo" Async Queue complete

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | localSourceSystemName | sourceSystem | sourceSystemName   |
      | project_one       | Project One           | CONS_LATAM   | Consumer Latam Ent |
      | ems                | EMS                    | EMS           | ems                 |
    And I wait "/edm/source_system_v1" Async Queue complete


    When I submit task with xml file "xml/edm/EDMMfgOrderItm.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/mfg_order_itm" by keyFields "srcSysCd,mfgOrdrNum,lnItmNbr"
      | srcSysCd   | mfgOrdrNum   | lnItmNbr | mfgPlnndOrdrNum | matlNum            | scrpQty | itmQty | rcvdQty | prdtnUomCd | plntCd | fctrNmrtrMeas | fctrDnmntrMeas | goodRcptLdDaysQty | dlvCmpltInd | prdntVrsnNum | btchNum | delInd | baseUomCd |
      | CONS_LATAM | 000001647950 | 0001     | 0109281711      | 000000000000069340 |         | 2500   | 2500    | PC         | BR12   | 1             | 1              |                   |             | V012         |         |        | PC        |
      | CONS_LATAM | 000001647951 | 0001     | 0109281712      | 000000000000069341 |         | 2500   | 2500    | PC         | BR12   | 1             | 1              |                   |             | V012         |         |        | PC        |
      | CONS_LATAM | 000001647952 | 0001     | 0109281713      | 000000000000069342 |         | 2500   | 2500    | PC         | BR12   | 1             | 1              |                   |             | V012         |         |        | PC        |
      | CONS_LATAM | 000001647953 | 0001     | 0109281714      | 000000000000069343 |         | 2500   | 2500    | PC         | BR12   | 1             | 1              |                   |             | V012         |         |        | PC        |

#    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
#      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |
#
#    And I compare the number of records between "/project_one/afpo" and "/edm/mfg_order_itm,/plan/edm_failed_data"

    And I delete the test data

    #And I will remove all data with region "/edm/mfg_order_itm"

    #And I will remove all data with region "/plan/edm_failed_data"

