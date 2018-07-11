@pangea_test @AEAZ-7849
Feature:EDMCategory AEAZ-7849

  @Scenario1
  Scenario:Full Load curation
#    Given I import "/edm/planned_order_v1" by keyFields "mfgPlanOrdrDocId,srcSysCd"
#      | plntCd | srcSysCd   | firmingInd | fxScrapQty | grDaysLeadQty | matlNum            | mfgPlanOrdrDocId | mrpCtlId | planOrdrEndDt | planOrdrEndTm | planOrdrQty | planOrdrStrtDt | planOrdrTypeCd | planPlntCd | plngScnroCd | prcmtTypeCd | prdtnFnshdDt | prdtnStrtDt | prdtnStrtTm | prdtnVersNum | prdtnVers | reqQty | sLocCd | splPrcmtTypeCd | uomCd |
#      | BR12   | CONS_LATAM |            |            |               | 000000000000069349 | 0109281712       |          |               |               |             |                |                |            |             |             |              |             |             |              | V001      |        |        |                |       |
#      | BR12   | CONS_LATAM |            |            |               | 000000000000069349 | 0109281913       |          |               |               |             |                |                |            |             |             |              |             |             |              | V002      |        |        |                |       |
#      | BR12   | CONS_LATAM |            |            |               | 000000000000069349 | 0109281914       |          |               |               |             |                |                |            |             |             |              |             |             |              | V003      |        |        |                |       |
#      | BR12   | CONS_LATAM |            |            |               | 000000000000051324 | 0109281915       |          |               |               |             |                |                |            |             |             |              |             |             |              | V006      |        |        |                |       |
#      | BR12   | CONS_LATAM |            |            |               | 000000000000295676 | 0109281916       |          |               |               |             |                |                |            |             |             |              |             |             |              | V005      |        |        |                |       |
#
#    And I wait "/edm/planned_order_v1" Async Queue complete
#    Given I import "/edm/matl_prod_versn" by keyFields "matlNum,plntCd,prdntVrsnNum,srcSysCd"
#      | srcSysCd   | matlNum            | plntCd | prdntVrsnNum | valFromDt | valToDt  | altBomNum | bomUsgCd | rtngTypCd | rtngGrpCd | rtngGrpCntrNum | prcrmntTypCd | spPrcrmntTypCd | cstLtSzQty | mfgLine Cd | prdVrsnDesc        | dstrbtnKeyCd | frmLtSzQty | toLtSzQty | sLocCd | chckInd | chckDt | lckInd | orgBtchInd | crtDttm | chgDttm |
#      | CONS_LATAM | 000000000000069349 | BR12   | V001         | 20150520  | 99991231 | 01        | 1        | N         | 50019343  | 01             |              |                |            |            | TestProdVersion1   |              |            |           |        |         |        |        |            |         |         |
#      | CONS_LATAM | 000000000000051324 | BR12   | V006         | 20150713  | 99991231 | 02        | 1        | N         | 50017283  | 02             |              |                |            |            | TestProdVersion6   |              |            |           |        |         |        |        |            |         |         |
#      | CONS_LATAM | 000000000000019456 | BR12   | V004         | 20150908  | 99991231 | 03        | 1        | N         | 50017629  | 06             |              |                |            |            | TestProdVersion4   |              |            |           |        |         |        |        |            |         |         |
#      | CONS_LATAM | 000000000000278456 | BR12   | V001         | 20170622  | 99991231 | 02        | 1        | N         | 50017283  | 07             |              |                |            |            | TestProdVersion01  |              |            |           |        |         |        |        |            |         |         |
#      | CONS_LATAM | 000000000000069349 | BR12   | V002         | 20170524  | 99991231 | 02        | 1        | N         | 50019343  | 01             |              |                |            |            | TestProdVersion02  |              |            |           |        |         |        |        |            |         |         |
#      | CONS_LATAM | 000000000000069349 | BR12   | V003         | 20170625  | 99991231 | 03        | 1        | N         | 50019343  | 01             |              |                |            |            | TestProdVersion03  |              |            |           |        |         |        |        |            |         |         |
#      | CONS_LATAM | 000000000000295676 | BR12   | V005         | 20170416  | 99991231 | 01        | 1        | N         | 50019349  | 03             |              |                |            |            | TestProdVersion05  |              |            |           |        |         |        |        |            |         |         |
#      | CONS_LATAM | 000000000000083945 | BR12   | V001         | 20170630  | 99991231 | 02        | 1        | N         | 50019349  | 03             |              |                |            |            | TestProdVersion01  |              |            |           |        |         |        |        |            |         |         |
#      | CONS_LATAM | 000000000000069381 | BR12   | V001         | 20160610  | 20250610 | 01        | 1        | N         | 50019364  | 01             |              |                |            |            | TestProdVersion008 |              |            |           |        |         |        |        |            |         |         |
#      | CONS_LATAM | 000000000000069382 | BR12   | V001         | 20170220  | 20250120 | 01        | 1        | N         | 50019365  | 01             |              |                |            |            | TestProdVersion009 |              |            |           |        |         |        |        |            |         |         |
#    And I wait "/edm/matl_prod_versn" Async Queue complete
#    Given I import "/edm/mfg_rtng_itm_nde" by keyFields "rtngGrpCd,rtngGrpCntrNbr,rtngNdeNum,rtngNdeVrsnCntrNbr,rtngSqncNum,rtngTypCd,srcSysCd"
#      | srcSysCd   | rtngTypCd | rtngGrpCd | rtngGrpCntrNbr | rtngSqncNum | rtngNdeNum | rtngNdeVrsnCntrNbr | Valid-From Date | chgNum | delInd | Create Dttm | chgDttm |
#      | CONS_LATAM | N         | 50019343  | 01             | 000000      | 00000001   | 00000001           | 20150519        |        |        | 20150519    |         |
#      | CONS_LATAM | N         | 50019343  | 01             | 000000      | 00000002   | 00000002           | 20150519        |        |        |             |         |
#      | CONS_LATAM | N         | 50017283  | 02             | 000000      | 00000001   | 00000001           | 20150713        |        |        | 20150713    |         |
#      | CONS_LATAM | N         | 50017283  | 02             | 000000      | 00000002   | 00000002           | 20150519        |        |        |             |         |
#      | CONS_LATAM | N         | 50017283  | 02             | 000000      | 00000003   | 00000003           | 20150519        |        |        |             |         |
#      | CONS_LATAM | N         | 50019349  | 03             | 000000      | 00000001   | 00000001           | 20150724        |        |        | 20150724    |         |
#      | CONS_LATAM | N         | 50019349  | 03             | 000000      | 00000002   | 00000002           | 20150519        |        |        |             |         |
#    And I wait "/edm/mfg_rtng_itm_nde" Async Queue complete
#    Given I import "/edm/mfg_rtng_itm" by keyFields "rtngGrpCd,rtngItmNum,rtngItmVersnCntrNbr,rtngTypCd,srcSysCd"
#      | srcSysCd   | rtngTypCd | rtngGrpCd | rtngItmNum | rtngItmVersnCntrNbr | valFromDt | chgNum | delInd | crtDttm | chgDttm | rtngSupNdeNum | operNum | operCd | wrkCntrCd | plntCd | operDesc | operUom | bsQty | act1Cd | act1UomCd | act1Qty | act2Cd | act2UomCd | act2Qty | act3Cd | act3UomCd | act3Qty | act4Cd | act4UomCd | act4Qty | act5Cd | act5UomCd |
#      | CONS_LATAM | N         | 50019343  | 00000001   |                     | 20150519  |        |        |         |         |               | 0010    | PP01   | 10000089  | BR12   |          |         |       |        | H         | 1.023   |        | H         | 4.023   |        | H         | 2.9     |        |           |         |        |           |
#      | CONS_LATAM | N         | 50019343  | 00000002   |                     | 20150519  |        |        |         |         |               | 0020    | PP01   | 10022089  | BR12   |          |         |       |        | H         | 5.023   |        | H         | 3.023   |        | H         | 9.03    |        |           |         |        |           |
#      | CONS_LATAM | N         | 50017283  | 00000001   |                     | 20150713  |        |        |         |         |               | 0010    | PP01   | 10122089  | BR12   |          |         |       |        | H         | 2.01    |        | H         | 2.023   |        | H         | 4.32    |        |           |         |        |           |
#      | CONS_LATAM | N         | 50017283  | 00000002   |                     | 20150519  |        |        |         |         |               | 0020    | PP01   | 10024089  | BR12   |          |         |       |        | H         | 1.398   |        | H         | 1.023   |        | H         | 6.35    |        |           |         |        |           |
#      | CONS_LATAM | N         | 50017283  | 00000003   |                     | 20150519  |        |        |         |         |               | 0030    | PP01   | 10028089  | BR12   |          |         |       |        | H         | 2.576   |        | H         | 0.023   |        | H         | 8.38    |        |           |         |        |           |
#      | CONS_LATAM | N         | 50019349  | 00000001   |                     | 20150724  |        |        |         |         |               | 0010    | PP01   | 10026089  | BR12   |          |         |       |        | H         | 3.9     |        | H         | 4.908   |        | H         | 10.41   |        |           |         |        |           |
#      | CONS_LATAM | N         | 50019349  | 00000002   |                     | 20150519  |        |        |         |         |               | 0020    | PP01   | 10922089  | BR12   |          |         |       |        | H         | 4.456   |        | H         | 9.793   |        | H         | 12.44   |        |           |         |        |           |
#    And I wait "/edm/mfg_rtng_itm" Async Queue complete
#    Given I import "/edm/wrk_ctr" by keyFields "srcSysCd,wrkCtrNum,wrkCtrTypeCd"
#      | srcSysCd   | delInd | plntCd | capyNum | locGrpCd | lockInd | machTypeCd | mixingInd | oprCd | othFrmlCd | plnrGrpCd | respPrsnNum | runFrmlCd | schdlngInd | setupFrmlCd | setupTypeCd | slocCd | suplAreaCd | teardownFrmlCd | vldFromDt | vldToDt | wrkCtrActvCd | wrkCtrCatCd | wrkCtrCd | wrkCtrDesc | wrkCtrLocCd | wrkCtrNum | wrkCtrTypeCd | wrkCtrUsgCd |
#      | CONS_LATAM |        | BR12   |         |          |         |            |           |       |           |           |             |           |            |             |             |        |            |                |           |         |              |             |          |            |             | 10000089  |              |             |
#      | CONS_LATAM |        | BR12   |         |          |         |            |           |       |           |           |             |           |            |             |             |        |            |                |           |         |              |             |          |            |             | 10022089  |              |             |
#      | CONS_LATAM |        | BR12   |         |          |         |            |           |       |           |           |             |           |            |             |             |        |            |                |           |         |              |             |          |            |             | 10122089  |              |             |
#      | CONS_LATAM |        | BR12   |         |          |         |            |           |       |           |           |             |           |            |             |             |        |            |                |           |         |              |             |          |            |             | 10024089  |              |             |
#      | CONS_LATAM |        | BR12   |         |          |         |            |           |       |           |           |             |           |            |             |             |        |            |                |           |         |              |             |          |            |             | 10028089  |              |             |
#      | CONS_LATAM |        | BR12   |         |          |         |            |           |       |           |           |             |           |            |             |             |        |            |                |           |         |              |             |          |            |             | 10026089  |              |             |
#      | CONS_LATAM |        | BR12   |         |          |         |            |           |       |           |           |             |           |            |             |             |        |            |                |           |         |              |             |          |            |             | 10922089  |              |             |
#    And I wait "/edm/wrk_ctr" Async Queue complete
#    Given I import "/plan/cns_plan_parameter" by keyFields "attribute,dataObject,inclExcl,parameter,parameterValue,sourceSystem"
#      | sourceSystem | dataObject | attribute   | parameter  | parameterValue | inclExcl | comments |
#      | CONS_LATAM   | ALL        | SEND_TO_OMP | SYSTEMNAME | CONS_LATAM     | I        |          |
#    And I wait "/plan/cns_plan_parameter" Async Queue complete
    When I submit task with xml file "xml/omp/OMPPloOperationFromERP.xml" and execute file "jar/pangea-view.jar"
#    Then A file is found on sink application with name "PLOOperationFromERP.tsv"
#
#    Then I check file data for filename "PLOOperationFromERP.tsv" by keyFields "ploOperationFromErpId"
##    Then I check region data "/omp/plo_operation_from_erp" by keyFields "ploOperationFromErpId"
#      | arbid               | vgw06 | vgw05 | vgw04 | vgw03 | vgw02 | vgw01 | vornr | erpPloid             | vge06 | vge05 | vge04 | vge03 | ploOperationFromErpId     | vge02 | vge01 |
#      | CONS_LATAM/10000089 |       |       |       | 2.9   | 4.023 | 1.023 | 0010  | CONS_LATAM/109281712 |       |       |       | H     | CONS_LATAM/109281712/0010 | H     | H     |
#      | CONS_LATAM/10022089 |       |       |       | 9.03  | 3.023 | 5.023 | 0020  | CONS_LATAM/109281712 |       |       |       | H     | CONS_LATAM/109281712/0020 | H     | H     |
#      | CONS_LATAM/10000089 |       |       |       | 2.9   | 4.023 | 1.023 | 0010  | CONS_LATAM/109281913 |       |       |       | H     | CONS_LATAM/109281913/0010 | H     | H     |
#      | CONS_LATAM/10022089 |       |       |       | 9.03  | 3.023 | 5.023 | 0020  | CONS_LATAM/109281913 |       |       |       | H     | CONS_LATAM/109281913/0020 | H     | H     |
#      | CONS_LATAM/10000089 |       |       |       | 2.9   | 4.023 | 1.023 | 0010  | CONS_LATAM/109281914 |       |       |       | H     | CONS_LATAM/109281914/0010 | H     | H     |
#      | CONS_LATAM/10022089 |       |       |       | 9.03  | 3.023 | 5.023 | 0020  | CONS_LATAM/109281914 |       |       |       | H     | CONS_LATAM/109281914/0020 | H     | H     |
#      | CONS_LATAM/10122089 |       |       |       | 4.32  | 2.023 | 2.01  | 0010  | CONS_LATAM/109281915 |       |       |       | H     | CONS_LATAM/109281915/0010 | H     | H     |
#      | CONS_LATAM/10024089 |       |       |       | 6.35  | 1.023 | 1.398 | 0020  | CONS_LATAM/109281915 |       |       |       | H     | CONS_LATAM/109281915/0020 | H     | H     |
#      | CONS_LATAM/10028089 |       |       |       | 8.38  | 0.023 | 2.576 | 0030  | CONS_LATAM/109281915 |       |       |       | H     | CONS_LATAM/109281915/0030 | H     | H     |
#      | CONS_LATAM/10026089 |       |       |       | 10.41 | 4.908 | 3.9   | 0010  | CONS_LATAM/109281916 |       |       |       | H     | CONS_LATAM/109281916/0010 | H     | H     |
#      | CONS_LATAM/10922089 |       |       |       | 12.44 | 9.793 | 4.456 | 0020  | CONS_LATAM/109281916 |       |       |       | H     | CONS_LATAM/109281916/0020 | H     | H     |
#
