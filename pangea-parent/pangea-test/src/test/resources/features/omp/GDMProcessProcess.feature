@pangea_test @AEAZ-6661
Feature: GDMProcessProcess AEAZ-6661

  @Scenario1
  Scenario: Sheet1

    When I import "/edm/mfg_order" by keyFields "mfgOrdrNum,sourceSysCd"
      | sourceSysCd | mfgOrdrNum   | mfgOrdrTypCd | plntCd | crtdDttm | chgDttm  | delInd | strtDt   | actRlseDt | rsrvtnNum  | rtngTypCd | rtngGrpCd | rtngGrpCntrNum | bomCatCd | bomNum   | bomAltNum | mrpCntrllrCd | ordrRtngNum | mfgOrdrSttsCd |
      | CONS_LATAM  | 000001647950 | PP01         | BR12   | 20171231 | 20171231 |        | 20180131 | 20180624  | 0078293320 | N         | 50019340  | 01             | M        | 00034150 | 01        |              | 0000647900  |               |
      | CONS_LATAM  | 000001647951 | PP01         | BR12   | 20171231 | 20171231 |        | 20180131 | 20180624  | 0078293321 | N         | 50019341  | 01             | M        | 00034151 | 01        |              | 0000647901  |               |
      | CONS_LATAM  | 000001647952 | PP01         | BR12   | 20171231 | 20171231 |        | 20180131 | 20180624  | 0078293322 | N         | 50019342  | 01             | M        | 00034152 | 01        |              | 0000647902  |               |
      | CONS_LATAM  | 000001647953 | PP01         | BR12   | 20171231 | 20171231 |        | 20180131 | 20180624  | 0078293323 | N         | 50019343  | 01             | M        | 00034153 | 01        |              | 0000647903  |               |
      | CONS_LATAM  | 000001647954 | PP01         | BR12   | 20171231 | 20171231 |        | 20180131 | 20180624  | 0078293324 | N         | 50019344  | 01             | M        | 00034154 | 01        |              | 0000647904  |               |
      | CONS_LATAM  | 000001647955 | PP01         | BR12   | 20171231 | 20171231 |        | 20180131 | 20180624  | 0078293325 | N         | 50019345  | 01             | M        | 00034155 | 01        |              | 0000647905  |               |
      | CONS_LATAM  | 000001647956 | PP01         | BR12   | 20171231 | 20171231 |        | 20180131 | 20180624  | 0078293326 | N         | 50019346  | 01             | M        | 00034156 | 01        |              | 0000647906  |               |
      | CONS_LATAM  | 000001647957 | PP01         | BR12   | 20171231 | 20171231 |        | 20180131 | 20180624  | 0078293327 | N         | 50019347  | 01             | M        | 00034157 | 01        |              | 0000647907  |               |
      | CONS_LATAM  | 000001647958 | PP01         | BR12   | 20171231 | 20171231 |        | 20180131 | 20180624  | 0078293328 | N         | 50019348  | 01             | M        | 00034158 | 01        |              | 0000647908  |               |
      | CONS_LATAM  | 000001647959 | PP01         | BR12   | 20171231 | 20171231 |        | 20180131 | 20180624  | 0078293329 | N         | 50019349  | 01             | M        | 00034159 | 01        |              | 0000647909  |               |
      | CONS_LATAM  | 000001647960 | PP01         | BR12   | 20171231 | 20171231 |        | 20180131 | 20180624  | 0078293360 | N         | 50019360  | 01             | M        | 00034160 | 01        |              | 0000647960  |               |
    And I wait "/edm/mfg_order" Async Queue complete

    When I import "/edm/material_global_v1" by keyFields "localMaterialNumber,sourceSystem"
      | sourceSystem | localMaterialNumber | localRefDescription                  | localMaterialType | localBaseUom | materialNumber | primaryPlanningCode |
      | CONS_LATAM   | 000000000000069340  | PR T8P7 NAPK SF ADAPT COT WINGS 48x8 | FERT              | EA           | 69340          | 69340               |
      | CONS_LATAM   | 000000000000069341  | Test material 1                      | FERT              | EA           | 69341          | 69341               |
      | CONS_LATAM   | 000000000000069342  | Test material 2                      | FERT              | EA           | 69342          | 69342               |
      | CONS_LATAM   | 000000000000069343  | Test material 3                      | FERT              | EA           | 69343          | 69343               |
      | CONS_LATAM   | 000000000000069344  | PR T8P7 NAPK SF ADAPT COT WINGS 48x8 | FERT              | EA           | 69344          | 69344               |
      | CONS_LATAM   | 000000000000069345  | PR T8P7 NAPK SF ADAPT COT WINGS 48x8 | FERT              | EA           | 69345          | 69345               |
      | CONS_LATAM   | 000000000000069346  | Test material 4                      | FERT              | EA           | 69346          | 69346               |
      | CONS_LATAM   | 000000000000069347  | Test material 6                      | FERT              | EA           | 69347          | 69347               |
      | CONS_LATAM   | 000000000000069348  | Test Material1                       | FERT              | EA           | 69348          | 69348               |
      | CONS_LATAM   | 000000000000069349  | Test Material2                       | FERT              | EA           | 69349          | 69349               |
      | CONS_LATAM   | 000000000000069360  | PR T8P7 NAPK SF ADAPT COT WINGS 48x8 | FERT              | EA           | 69360          | 69360               |
    And I wait "/edm/material_global_v1" Async Queue complete

    When I import "/plan/cns_plan_parameter" by keyFields "attribute,dataObject,inclExcl,parameter,parameterValue,sourceSystem"
      | sourceSystem | dataObject  | attribute  | parameter | parameterValue | inclExcl | comments |
      | CONS_LATAM   | SEND_TO_OMP | CONS_LATAM |           | LA             |          |          |
      | CONS_LATAM   | SEND_TO_OMP | GDMPROCESS |           | 360            | I        |          |
    And I wait "/plan/cns_plan_parameter" Async Queue complete

    When I import "/edm/mfg_rtng_hdr" by keyFields "rtngGrpCd,rtngGrpCntrNbr,rtngTypCd,rtngVrsnCntrNbr,srcSysCd"
      | srcSysCd   | rtngTypCd | rtngGrpCd | rtngGrpCntrNbr | rtngVrsnCntrNbr | valFromDt | chgNum | delInd | crtDttm  | chgDttm  | plntCd | rtngUsgCd | rtngSttsCd | rtngUomCd | fromLtSzQty | toLtSzQty      | rtngPlnnrGrpCd | rtngDesc | rtngPrflCd | rtgVld_ToDt |
      | CONS_LATAM | N         | 50019340  | 01             | 00000001        | 20150519  |        |        | 20150519 | 20150519 | BR12   | 1         | 4          | CRT       | 0.000       | 99.999.999,000 |                |          |            | 99991231    |
      | CONS_LATAM | N         | 50019341  | 01             | 00000002        | 20150710  |        |        | 20150713 | 20150713 | BR12   | 1         | 4          | CRT       | 0.000       | 99.999.999,000 |                |          |            | 99991231    |
      | CONS_LATAM | N         | 50019342  | 01             | 00000006        | 20150724  |        |        | 20150724 | 20150724 | BR12   | 1         | 4          | CRT       | 0.000       | 99.999.999,000 |                |          |            | 99991231    |
      | CONS_LATAM | N         | 50019343  | 01             | 00000007        | 20170621  |        |        | 20170622 | 20170622 | BR12   | 1         | 4          | CRT       | 0.000       | 99.999.999,000 |                |          |            | 99991231    |
      | CONS_LATAM | N         | 50019344  | 01             | 00000001        | 20170524  |        |        | 20170524 | 20170524 | BR12   | 1         | 4          | CRT       | 0.000       | 99.999.999,000 |                |          |            | 99991231    |
      | CONS_LATAM | N         | 50019345  | 01             | 00000001        | 20170621  |        |        | 20170621 | 20170621 | BR12   | 1         | 4          | CRT       | 0.000       | 99.999.999,000 |                |          |            | 99991231    |
      | CONS_LATAM | N         | 50019346  | 01             | 00000003        | 20170415  |        |        | 20170415 | 20170415 | BR12   | 1         | 4          | CRT       | 0.000       | 99.999.999,000 |                |          |            | 99991231    |
      | CONS_LATAM | N         | 50019347  | 01             | 00000003        | 20170629  |        |        | 20170629 | 20170629 | BR12   | 1         | 4          | CRT       | 0.000       | 99.999.999,000 |                |          |            | 99991231    |
      | CONS_LATAM | N         | 50019348  | 01             | 00000001        | 20170115  |        |        | 20170415 | 20170415 | BR12   | 1         | 4          | CRT       | 0.000       | 99.999.999,000 |                |          |            | 20241130    |
      | CONS_LATAM | N         | 50019349  | 01             | 00000001        | 20170617  |        |        | 20170629 | 20170629 | BR12   | 1         | 4          | CRT       | 0.000       | 99.999.999,000 |                |          |            | 20261231    |
      | CONS_LATAM | N         | 50019360  | 01             | 00000001        | 20170617  |        |        | 20170629 | 20170629 | BR12   | 1         | 4          | CRT       | 0.000       | 99.999.999,000 |                |          |            | 20261231    |
    And I wait "/edm/mfg_rtng_hdr" Async Queue complete

    When I import "/edm/matl_prod_versn" by keyFields "matlNum,plntCd,prdntVrsnNum,srcSysCd"
      | srcSysCd   | matlNum            | plntCd | prdntVrsnNum | valFromDt | valToDt  | altBomNum | bomUsgCd | rtngTypCd | rtngGrpCd | rtngGrpCntrNum | prdVrsnDesc        |
      | CONS_LATAM | 000000000000069340 | BR12   | V001         | 20150520  | 99991231 | 01        | 1        | N         | 50019340  | 01             | TestProdVersion1   |
      | CONS_LATAM | 000000000000069341 | BR12   | V002         | 20150713  | 99991231 | 02        | 1        | N         | 50019341  | 01             | TestProdVersion6   |
      | CONS_LATAM | 000000000000069342 | BR12   | V003         | 20150908  | 99991231 | 03        | 1        | N         | 50019342  | 01             | TestProdVersion4   |
      | CONS_LATAM | 000000000000069343 | BR12   | V004         | 20170622  | 99991231 | 02        | 1        | N         | 50019343  | 01             | TestProdVersion01  |
      | CONS_LATAM | 000000000000069344 | BR12   | V005         | 20170524  | 99991231 | 02        | 1        | N         | 50019344  | 01             | TestProdVersion02  |
      | CONS_LATAM | 000000000000069345 | BR12   | V006         | 20170625  | 99991231 | 03        | 1        | N         | 50019345  | 01             | TestProdVersion03  |
      | CONS_LATAM | 000000000000069346 | BR12   | V007         | 20170416  | 99991231 | 01        | 1        | N         | 50019346  | 01             | TestProdVersion05  |
      | CONS_LATAM | 000000000000069347 | BR12   | V008         | 20170630  | 99991231 | 02        | 1        | N         | 50019347  | 01             | TestProdVersion01  |
      | CONS_LATAM | 000000000000069348 | BR12   | V009         | 20160610  | 20250610 | 01        | 1        | N         | 50019348  | 01             | TestProdVersion008 |
      | CONS_LATAM | 000000000000069349 | BR12   | V010         | 20170220  | 20250120 | 01        | 1        | N         | 50019349  | 01             | TestProdVersion009 |
      | CONS_LATAM | 000000000000069360 | BR12   | V001         | 20150520  | 99991231 | 01        | 1        | N         | 50019360  | 01             | TestProdVersion1   |
    And I wait "/edm/matl_prod_versn" Async Queue complete

    When I import "/edm/mfg_order_itm" by keyFields "lnItmNbr,mfgOrdrNum,srcSysCd"
      | srcSysCd   | mfgOrdrNum   | lnItmNbr | mfgPlnndOrdrNum | matlNum            | scrpQty | itmQty | rcvdQty | prdtnUomCd | plntCd | prdntVrsnNum |
      | CONS_LATAM | 000001647950 | 0001     | 0109281711      | 000000000000069340 |         | 2527   |         | KI         | BR12   | V001         |
      | CONS_LATAM | 000001647951 | 0001     | 0109281964      | 000000000000069341 |         | 1993   |         | EA         | BR12   | V002         |
      | CONS_LATAM | 000001647952 | 0001     | 0109281965      | 000000000000069342 |         | 24656  |         | TS         | BR12   | V003         |
      | CONS_LATAM | 000001647953 | 0001     | 0109281966      | 000000000000069343 |         | 98345  |         | EA         | BR12   | V004         |
      | CONS_LATAM | 000001647954 | 0001     | 0109281967      | 000000000000069344 |         | 2527   |         | KI         | BR12   | V005         |
      | CONS_LATAM | 000001647955 | 0001     | 0109281968      | 000000000000069345 |         | 2527   |         | KI         | BR12   | V006         |
      | CONS_LATAM | 000001647956 | 0001     | 0109281969      | 000000000000069346 |         | 3456   |         | KG         | BR12   | V007         |
      | CONS_LATAM | 000001647957 | 0001     | 0109281970      | 000000000000069347 |         | 2874   |         | KG         | BR12   | V008         |
      | CONS_LATAM | 000001647958 | 0001     | 0109281981      | 000000000000069348 |         | 3456   |         | KG         | BR12   | V009         |
      | CONS_LATAM | 000001647959 | 0001     | 0109281983      | 000000000000069349 |         | 2874   |         | KG         | BR12   | V010         |
      | CONS_LATAM | 000001647960 | 0001     | 0109281711      | 000000000000069360 |         | 2527   |         | KI         | BR12   | V001         |
    And I wait "/edm/mfg_order_itm" Async Queue complete

    When I import "/plan/cns_material_plan_status" by keyFields "localMaterialNumber,localPlant,sourceSystem"
      | sourceSystem | localMaterialNumber | localPlant | materialNumber | localParentCode | ppc   | active | dpRelevant | spRelevant | parentActive | noPlanRelevant |
      | CONS_LATAM   | 000000000000069340  | BR12       | 69340          |                 | 69340 |        |            | X          |              |                |
      | CONS_LATAM   | 000000000000069341  | BR12       | 69341          |                 | 69341 |        |            | X          |              |                |
      | CONS_LATAM   | 000000000000069342  | BR12       | 69342          |                 | 69342 |        |            | X          |              |                |
      | CONS_LATAM   | 000000000000069343  | BR12       | 69343          |                 | 69343 |        |            | X          |              |                |
      | CONS_LATAM   | 000000000000069344  | BR12       | 69344          |                 | 69344 |        |            | X          |              |                |
      | CONS_LATAM   | 000000000000069345  | BR12       | 69345          |                 | 69345 |        |            | X          |              |                |
      | CONS_LATAM   | 000000000000069346  | BR12       | 69346          |                 | 69346 |        |            | X          |              |                |
      | CONS_LATAM   | 000000000000069347  | BR12       | 69347          |                 | 69347 |        |            | X          |              |                |
      | CONS_LATAM   | 000000000000069348  | BR12       | 69348          |                 | 69348 |        |            | X          |              |                |
      | CONS_LATAM   | 000000000000069349  | BR12       | 69349          |                 | 69349 |        |            | X          |              |                |
      | CONS_LATAM   | 000000000000069360  | BR12       | 69360          | 69360           |       |        | X          |            |              |                |
    And I wait "/plan/cns_material_plan_status" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmProcessProcess.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/omp/gdm_process" by keyFields "processId"
      | processId              | active | activeOPRERP | activeSOPERP | description        | endEff              | ERPOrderId             | label | locationId      | processTypeId | productId | startEff            | vendorId |
      | PRO/CONS_LATAM/1647950 | YES    | YES          | NO           | TestProdVersion1   | 2998/12/31 23:59:59 | PRO/CONS_LATAM/1647950 |       | CONS_LATAM_BR12 | Production    | 69340     | 2015/05/20 00:00:00 |          |
      | PRO/CONS_LATAM/1647951 | YES    | YES          | NO           | TestProdVersion6   | 2998/12/31 23:59:59 | PRO/CONS_LATAM/1647951 |       | CONS_LATAM_BR12 | Production    | 69341     | 2015/07/13 00:00:00 |          |
      | PRO/CONS_LATAM/1647952 | YES    | YES          | NO           | TestProdVersion4   | 2998/12/31 23:59:59 | PRO/CONS_LATAM/1647952 |       | CONS_LATAM_BR12 | Production    | 69342     | 2015/09/08 00:00:00 |          |
      | PRO/CONS_LATAM/1647953 | YES    | YES          | NO           | TestProdVersion01  | 2998/12/31 23:59:59 | PRO/CONS_LATAM/1647953 |       | CONS_LATAM_BR12 | Production    | 69343     | 2017/06/22 00:00:00 |          |
      | PRO/CONS_LATAM/1647954 | YES    | YES          | NO           | TestProdVersion02  | 2998/12/31 23:59:59 | PRO/CONS_LATAM/1647954 |       | CONS_LATAM_BR12 | Production    | 69344     | 2017/05/24 00:00:00 |          |
      | PRO/CONS_LATAM/1647955 | YES    | YES          | NO           | TestProdVersion03  | 2998/12/31 23:59:59 | PRO/CONS_LATAM/1647955 |       | CONS_LATAM_BR12 | Production    | 69345     | 2017/06/25 00:00:00 |          |
      | PRO/CONS_LATAM/1647956 | YES    | YES          | NO           | TestProdVersion05  | 2998/12/31 23:59:59 | PRO/CONS_LATAM/1647956 |       | CONS_LATAM_BR12 | Production    | 69346     | 2017/04/16 00:00:00 |          |
      | PRO/CONS_LATAM/1647957 | YES    | YES          | NO           | TestProdVersion01  | 2998/12/31 23:59:59 | PRO/CONS_LATAM/1647957 |       | CONS_LATAM_BR12 | Production    | 69347     | 2017/06/30 00:00:00 |          |
      | PRO/CONS_LATAM/1647958 | YES    | YES          | NO           | TestProdVersion008 | 2024/11/30 23:59:59 | PRO/CONS_LATAM/1647958 |       | CONS_LATAM_BR12 | Production    | 69348     | 2017/01/15 00:00:00 |          |
      | PRO/CONS_LATAM/1647959 | YES    | YES          | NO           | TestProdVersion009 | 2025/01/20 23:59:59 | PRO/CONS_LATAM/1647959 |       | CONS_LATAM_BR12 | Production    | 69349     | 2017/06/17 00:00:00 |          |


    And I delete the test data

    And I will remove all data with region "/omp/gdm_process"
