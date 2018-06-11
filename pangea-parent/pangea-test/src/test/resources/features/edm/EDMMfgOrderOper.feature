@pangea_test @AEAZ-4253
Feature: EDMMfgOrderOper AEAZ-4253

  Scenario: Full Load curation

    Given I import "/project_one/afvv" by keyFields "aplzl,aufpl"
      | aufpl      | aplzl    | meinh | umren  | umrez   | bmsch | vge01 | vgw01 | vge02 | vgw02 | vge03 | vgw03 | vge04 | vgw04 | vge05 | vgw05 | vge06 | vgw06 | zeimu | zminu | minwe | zeimb | zminb | zeilm | zlmax | zeilp | zlpro | zeiwn | zwnor | zeiwm | zwmin  | zeitn  | ztnor  | zeitm  | ztmin  |
      | 0000000001 | 00000001 | PC    | 000000 | 0.00000 | 1.000 | A     | B     | C     | D     | H     | H     | H     | H     | H     | H     | H     | H    | 0.000 | 0.000 | 0.000 | 1.000 | 2.000 | 3.000 | 4.000 | 5.000 | 6.000 | 7.000 | 8.000 | 9.000 | 10.000 | 11.000 | 12.000 | 13.000 | 14.000 |
      | 0000000002 | 00000002 | PC    | 000000 | 0.00000 | 1.000 | A     | B     | C     | D     | H     | H     | H     | H     | H     | H     | H     | H    | 0.000 | 0.000 | 0.000 | 1.000 | 2.000 | 3.000 | 4.000 | 5.000 | 6.000 | 7.000 | 8.000 | 9.000 | 10.000 | 11.000 | 12.000 | 13.000 | 14.000 |
    And I wait "/project_one/afvv" Async Queue complete

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | sourceSystem |
      | project_one       | CONS_LATAM   |
      | project_two       | CONS_LATAM   |
    And I wait "/edm/source_system_v1" Async Queue complete

    When I submit task with xml file "xml/edm/EDMMfgOrderOper.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/mfg_order_oper" by keyFields "srcSysCd,ordrRtngNum,ordrRtngCtrNum"
      | srcSysCd   | ordrRtngNum | ordrRtngCtrNum | oprUomCd | factDenomMeas | factNumrtrMeas | baseQty | actv1UomCd | actv1Qty | actv2UomCd | actv2Qty | actv3UomCd | actv3Qty | actv4UomCd | actv4Qty | actv5UomCd | actv5Qty | actv6UomCd | actv6Qty | minOvrlapTimeUomCd | minOvrlapTimeInDaysQty | minSendAhdQty | minPrcsgTimeUomCd | minPrcsgTimeInDaysQty | maxWaitTimeUomCd | maxWaitTimeInDaysQty | minWaitTimeUomCd | minWaitTimeInDaysQty | stdQueTimeUomCd | stdQueTimeInDaysQty | minQueTimeUomCd | minQueTimeInDaysQty | stdMoveTimeUomCd | stdMoveTimeInDaysQty | minMoveTimeUomCd | minMoveTimeInDaysQty|
      | CONS_LATAM | 0000000001  | 00000001       | PC       | 000000        | 0.00000        | 1.000   | A          | B        | C          | D        | H          | H        | H          | H        | H          | H        | H          | H        | 0.000              | 0.000                  | 0.000         | 1.000             | 2.000                 | 3.000            | 4.000                | 5.000            | 6.000                | 7.000           | 8.000               | 9.000           | 10.000              | 11.000           | 12.000               | 13.000           | 14.000               |
      | CONS_LATAM | 0000000002  | 00000002       | PC       | 000000        | 0.00000        | 1.000   | A          | B        | C          | D        | H          | H        | H          | H        | H          | H        | H          | H        | 0.000              | 0.000                  | 0.000         | 1.000             | 2.000                 | 3.000            | 4.000                | 5.000            | 6.000                | 7.000           | 8.000               | 9.000           | 10.000              | 11.000           | 12.000               | 13.000           | 14.000               |

    And I delete the test data

    And I will remove all data with region "/edm/mfg_order_oper"


