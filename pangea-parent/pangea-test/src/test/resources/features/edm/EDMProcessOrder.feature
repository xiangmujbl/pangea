@pangea_test @AEAZ-2373
Feature: EDMProcessOrder AEAZ-2373

  Scenario: Full Load curation
    # 1. Get sourceSystem from source_system_v1 where localSourceSystem = "project_one" (T1)
    # 2. Get attributes from aufk where aufk.aufnr = afko.aufnr (N1)
    # 3. Get attributes from afpo  where afpo.aufnr = afko.aufnr (N2)
    # 4. Get tj02t-txt04 from tj02t with a space in between each text (N3)

    Given I import "/project_one/afko" by keyFields "aufnr"
      | aufnr        | gltrp    | gstrp    | gltrs    | gstrs    | gstri    | gltri    | ftrmi    | ftrmp    | dispo | fevor | rsnum      | gasmg | gamng     | gmein | plnbez             | plnty | plnnr    | plnaw | plnal | pverw | stlbez             | terkz | rueck      | rmzhl    | igmng    |
      | 000001000323 | 20001018 | 20001016 | 20001018 | 20001018 | 20001018 | 20001019 | 20001002 | 00000000 | 101   | 005   | 0000000570 | 0.000 | 5216.000  | KI    | 000000000000004771 | N     | 50000739 | P     | 01    | 1     | 000000000000004771 | 6     | 0000002356 | 00000178 | 5214.000 |
      | 000001690465 | 20001016 | 20001011 | 20001012 | 20001011 | 20001005 | 20001028 | 20001002 | 00000000 | 101   | 005   | 0000000576 | 0.000 | 10000.000 | KI    | 000000000000004257 | N     | 50000718 | P     | 01    | 1     | 000000000000004257 | 6     | 0000002429 | 00000105 | 9993.000 |
    And I wait "/project_one/afko" Async Queue complete

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | localSourceSystemName | sourceSystem | sourceSystemName   |
      | project_one       | Project One           | CONS_LATAM   | Consumer Latam Ent |
      | project_two       | Project Two           | CONS_LATAM   | Consumer Latam Ent |
    And I wait "/edm/source_system_v1" Async Queue complete

    Given I import "/project_one/aufk" by keyFields "aufnr"
      | aufnr        | auart | autyp | erdat    | aedat    | werks | loekz | idat2    | procnr       | logsystem |
      | 000001000323 | FJM2  | 30    | 20170103 | 20170131 | 8118  |       | 20170127 | 000000000000 |           |
      | 000001000327 | FJM2  | 30    | 20170103 | 20030307 | 8118  |       | 20170127 | 000000000000 |           |
      | 000001690465 | $$    | 03    | 20030307 | 00000000 |       | X     | 00000000 | 000000000000 |           |
    And I wait "/project_one/aufk" Async Queue complete

    Given I import "/project_one/jest" by keyFields "objnr,stat"
      | objnr          | stat  | inact |
      | OR000001690465 | I0001 |       |
      | OR000001690465 | I0028 |       |
      | OR000001690465 | I0043 |       |
      | OR000001690465 | I0118 |       |
      | OR000001690465 | I0215 |       |
      | OR000001690465 | I0340 |       |
      | OR000001690465 | I0361 |       |
      | OR000001690465 | I0369 | X     |
      | OR000001690465 | J0001 |       |
      | OR000001690465 | I0002 | X     |
    And I wait "/project_one/jest" Async Queue complete

    Given I import "/project_one/tj02t" by keyFields "istat,spras"
      | istat | spras | txt04 | txt30                          |
      | I0001 | EN    | REL   | Released                       |
      | I0028 | EN    | SETC  | Settlement rule created        |
      | I0043 | EN    | LKD   | Locked                         |
      | I0118 | EN    | NTUP  | Dates are not updated          |
      | I0215 | EN    | SSAR  | Object created                 |
      | I0340 | EN    | MACM  | Material committed             |
      | I0361 | EN    | NEWQ  | New quantity calculation       |
      | I0369 | EN    | BCRQ  | Order to be handled in batches |

    And I wait "/project_one/tj02t" Async Queue complete

    Given I import "/project_one/afpo" by keyFields "aufnr,posnr"
      | aufnr        | posnr | plnum      | psamg | psmng     | wemng    | amein | matnr              | elikz | verid | charg | umrez | umren | webaz |
      | 000001000323 | 0001  | 0000327666 | 0.000 | 5216.000  | 5214.000 | KI    | 000000000000004771 |       |       |       | 1     | 1     | 0     |
      | 000001000327 | 0001  |            | 0.000 | 10000.000 | 9993.000 | KI    | 000000000000004257 |       |       |       | 1     | 1     | 0     |
      | 000001690465 | 0001  |            | 0.000 | 9000.000  | 9009.000 | KI    | 000000000000004257 | X     |       |       | 1     | 1     | 0     |

    And I wait "/project_one/afpo" Async Queue complete

    When I submit task with xml file "xml/edm/EDMProcessOrder.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/process_order_v1" by keyFields "sourceSystem,mfgOrdrNum,mfgOrdrItemNum"
      | sourceSystem | mfgOrdrNum   | mfgOrdrItemNum | ordrType | localOrderCat | crtdDttm | chgDttm  | plntCd | delInd | localTechnicalCompletion | localProductionProcessNo | localLogicalSystem | plnEndDt | plnStrtDt | prdtnSchdEndDt | prdtnSchdStrtDt | prdntStrtDt | prdtnEndDt | prdtnRlseDt | prdtnPlanRlseDt | mfgPlnnr | mfgSprvsr | localReservation | localTotalScrap | localTargetQty | localBaseUOM | localMaterialAFKOPLNBEZ | localTaskListType | localGroup | localApplication | localGroupCounter | localUsage | localMaterialAFKOSTLBEZ | localSchedulingType | localConfirmation | localConfirmationCounter | localConfirmedQtyIGMNG | plnndOrdr  | scrpQty | ordrQty  | rcvdQty  | prdtnUom | matlNum            | fctrNmrtrMeas | fctrDnmntrMeas | goodRcptLdDaysQty | dlvCmpltInd | prdntVrsnNum | btchNum | ordrStts                         |
      | CONS_LATAM   | 000001000323 | 0001           | FJM2     | 30            | 20170103 | 20170131 | 8118   |        | 20170127                 | 000000000000             |                    | 20001018 | 20001016  | 20001018       | 20001018        | 20001018    | 20001019   | 20001002    | 00000000        | 101      | 005       | 0000000570       | 0.000           | 5216.000       | KI           | 000000000000004771      | N                 | 50000739   | P                | 01                | 1          | 000000000000004771      | 6                   | 0000002356        | 00000178                 | 5214.000               | 0000327666 | 0.000   | 5216.000 | 5214.000 | KI       | 000000000000004771 | 1             | 1              | 0                 |             |              |         |                                  |
      | CONS_LATAM   | 000001690465 | 0001           | $$       | 03            | 20030307 | 00000000 |        | X      | 00000000                 | 000000000000             |                    | 20001016 | 20001011  | 20001012       | 20001011        | 20001005    | 20001028   | 20001002    | 00000000        | 101      | 005       | 0000000576       | 0.000           | 10000.000      | KI           | 000000000000004257      | N                 | 50000718   | P                | 01                | 1          | 000000000000004257      | 6                   | 0000002429        | 00000105                 | 9993.000               |            | 0.000   | 9000.000 | 9009.000 | KI       | 000000000000004257 | 1             | 1              | 0                 | X           |              |         | REL SETC LKD NTUP SSAR MACM NEWQ |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/project_one/afko" and "/edm/process_order_v1,/plan/edm_failed_data"

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/edm/process_order_v1"

    And I will remove all data with region "/plan/edm_failed_data"

