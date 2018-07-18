@pangea_test @AEAZ-6661
Feature: GDMProcessMaster AEAZ-6661

  @Scenario1
  Scenario: Sheet1

    When I import "/edm/material_global_v1" by keyFields "localMaterialNumber,sourceSystem"
      | sourceSystem | localMaterialNumber | localRefDescription                  | localMaterialType | localBaseUom | materialNumber | refDescription | primaryPlanningCode |
      | CONS_LATAM   | 000000000000069349  | PR T8P7 NAPK SF ADAPT COT WINGS 48x8 | FERT              | EA           | 69349          |                | 69349               |
      | CONS_LATAM   | 000000000000069350  | Test Material                        | FERT              | EA           | 69350          |                | 69350               |
      | CONS_LATAM   | 000000000000069381  | Test Material1                       | FERT              | EA           | 69351          |                | 69381               |
      | CONS_LATAM   | 000000000000069382  | Test Material2                       | FERT              | EA           | 69352          |                | 69382               |
      | CONS_LATAM   | 000000000000069360  | PR T8P7 NAPK SF ADAPT COT WINGS 48x8 | FERT              | EA           | 69360          |                |                     |
    And I wait "/edm/material_global_v1" Async Queue complete

    When I import "/plan/cns_plan_parameter" by keyFields "attribute,dataObject,parameter,parameterValue,sourceSystem"
      | sourceSystem | dataObject  | attribute  | parameter | parameterValue | inclExcl | comments |
      | CONS_LATAM   | SEND_TO_OMP | CONS_LATAM |           | LA             |          |          |
    And I wait "/plan/cns_plan_parameter" Async Queue complete

    When I import "/edm/matl_mfg_rtng" by keyFields "matlNum,matlRtngVrsnCntrNbr,plntCd,rntgAddtnlCntrNbr,rntgGrpCd"
      | srcSysCd   | matlNum            | plntCd | rtngTypCd | rntgGrpCd | rntgGrpCntrNbr | rntgAddtnlCntrNbr | matlRtngVrsnCntrNbr | valFromDt | chgNum | delInd | crtDttm  | chgDttm  | matlRtngValid_To |
      | CONS_LATAM | 000000000000069349 | BR12   | N         | 50019343  | 01             | 0000001           | 00000001            | 20150519  |        |        | 20150519 | 20150519 | 99991231         |
      | CONS_LATAM | 000000000000069349 | BR12   | N         | 50019343  | 02             | 0000002           | 00000002            | 20150713  |        |        | 20150713 | 20150713 | 99991231         |
      | CONS_LATAM | 000000000000069349 | BR12   | N         | 50019343  | 06             | 0000006           | 00000006            | 20150724  |        |        | 20150724 | 20150724 | 99991231         |
      | CONS_LATAM | 000000000000069349 | BR12   | N         | 50019343  | 07             | 0000007           | 00000007            | 20170622  |        |        | 20170622 | 20170622 | 99991231         |
      | CONS_LATAM | 000000000000069350 | BR12   | N         | 50019344  | 01             | 0000001           | 00000001            | 20170524  |        |        | 20170524 | 20170524 | 99991231         |
      | CONS_LATAM | 000000000000069350 | BR12   | N         | 50019344  | 02             | 0000002           | 00000002            | 20170621  |        |        | 20170621 | 20170621 | 99991231         |
      | CONS_LATAM | 000000000000069350 | BR12   | N         | 50019344  | 03             | 0000003           | 00000003            | 20170621  |        |        | 20170621 | 20170621 | 99991231         |
      | CONS_LATAM | 000000000000069350 | BR12   | N         | 50019344  | 06             | 0000006           | 00000006            | 20170415  |        |        | 20170415 | 20170415 | 99991231         |
      | CONS_LATAM | 000000000000069381 | BR01   | N         | 50019364  | 01             | 0000001           | 00000001            | 20170524  |        |        | 20170524 | 20170524 | 20241231         |
      | CONS_LATAM | 000000000000069382 | BR02   | N         | 50019365  | 01             | 0000001           | 00000001            | 20170524  |        |        | 20170524 | 20170524 | 20261231         |
      | CONS_LATAM | 000000000000069360 | BR12   | N         | 50019360  | 01             | 0000001           | 00000001            | 20150519  |        |        | 20150519 | 20150519 | 99991231         |
    And I wait "/edm/matl_mfg_rtng" Async Queue complete

    When I import "/edm/mfg_rtng_hdr" by keyFields "rtngGrpCd,rtngGrpCntrNbr,rtngTypCd,srcSysCd"
      | srcSysCd   | rtngTypCd | rtngGrpCd | rtngGrpCntrNbr | rtngVrsnCntrNbr | valFromDt | chgNum | delInd | crtDttm  | chgDttm  | plntCd | rtngUsgCd | rtngSttsCd | rtngUomCd | fromLtSzQty | toLtSzQty      | rtngPlnnrGrpCd | rtngDesc | rtngPrflCd | rtgVld_ToDt |
      | CONS_LATAM | N         | 50019343  | 01             | 00000001        | 20150519  |        |        | 20150519 | 20150519 | BR12   | 1         | 4          | CRT       | 0.000       | 99.999.999,000 |                |          |            | 99991231    |
      | CONS_LATAM | N         | 50019343  | 02             | 00000002        | 20150710  |        |        | 20150713 | 20150713 | BR12   | 1         | 4          | CRT       | 0.000       | 99.999.999,000 |                |          |            | 99991231    |
      | CONS_LATAM | N         | 50019343  | 06             | 00000006        | 20150724  |        |        | 20150724 | 20150724 | BR12   | 1         | 4          | CRT       | 0.000       | 99.999.999,000 |                |          |            | 99991231    |
      | CONS_LATAM | N         | 50019343  | 07             | 00000007        | 20170621  |        |        | 20170622 | 20170622 | BR12   | 1         | 4          | CRT       | 0.000       | 99.999.999,000 |                |          |            | 99991231    |
      | CONS_LATAM | N         | 50019344  | 01             | 00000001        | 20170524  |        |        | 20170524 | 20170524 | BR12   | 1         | 4          | CRT       | 0.000       | 99.999.999,000 |                |          |            | 99991231    |
      | CONS_LATAM | N         | 50019344  | 02             | 00000002        | 20170621  |        |        | 20170621 | 20170621 | BR12   | 1         | 4          | CRT       | 0.000       | 99.999.999,000 |                |          |            | 99991231    |
      | CONS_LATAM | N         | 50019344  | 03             | 00000003        | 20170415  |        |        | 20170415 | 20170415 | BR12   | 1         | 4          | CRT       | 0.000       | 99.999.999,000 |                |          |            | 99991231    |
      | CONS_LATAM | N         | 50019344  | 06             | 00000006        | 20170629  |        |        | 20170629 | 20170629 | BR12   | 1         | 4          | CRT       | 0.000       | 99.999.999,000 |                |          |            | 99991231    |
      | CONS_LATAM | N         | 50019364  | 01             | 00000001        | 20170115  |        |        | 20170415 | 20170415 | BR12   | 1         | 4          | CRT       | 0.000       | 99.999.999,000 |                |          |            | 20241231    |
      | CONS_LATAM | N         | 50019365  | 01             | 00000001        | 20170617  |        |        | 20170629 | 20170629 | BR12   | 1         | 4          | CRT       | 0.000       | 99.999.999,000 |                |          |            | 20261231    |
      | CONS_LATAM | N         | 50019360  | 01             | 00000001        | 20150519  |        |        | 20150519 | 20150519 | BR12   | 1         | 4          | CRT       | 0.000       | 99.999.999,000 |                |          |            | 99991231    |
    And I wait "/edm/mfg_rtng_hdr" Async Queue complete

    When I import "/edm/matl_prod_versn" by keyFields "srcSysCd,matlNum,plntCd,prdntVrsnNum"
      | srcSysCd   | matlNum            | plntCd | prdntVrsnNum | valFromDt | valToDt  | altBomNum | bomUsgCd | rtngTypCd | rtngGrpCd | rtngGrpCntrNum | prcrmntTypCd | spPrcrmntTypCd | cstLtSzQty | mfgLine Cd | prdVrsnDesc        |
      | CONS_LATAM | 000000000000069349 | BR12   | V001         | 20150520  | 99991231 | 01        | 1        | N         | 50019343  | 01             |              |                |            |            | TestProdVersion1   |
      | CONS_LATAM | 000000000000069349 | BR12   | V002         | 20150713  | 99991231 | 01        | 1        | N         | 50019343  | 02             |              |                |            |            | TestProdVersion2   |
      | CONS_LATAM | 000000000000069349 | BR12   | V012         | 20150908  | 99991231 | 01        | 1        | N         | 50019343  | 06             |              |                |            |            | TestProdVersion12  |
      | CONS_LATAM | 000000000000069349 | BR12   | V013         | 20170622  | 99991231 | 02        | 1        | N         | 50019343  | 07             |              |                |            |            | TestProdVersion13  |
      | CONS_LATAM | 000000000000069350 | BR12   | V001         | 20170524  | 99991231 | 01        | 1        | N         | 50019344  | 01             |              |                |            |            | TestProdVersion001 |
      | CONS_LATAM | 000000000000069350 | BR12   | V002         | 20170625  | 99991231 | 02        | 1        | N         | 50019344  | 02             |              |                |            |            | TestProdVersion002 |
      | CONS_LATAM | 000000000000069350 | BR12   | V003         | 20170416  | 99991231 | 01        | 1        | N         | 50019344  | 03             |              |                |            |            | TestProdVersion003 |
      | CONS_LATAM | 000000000000069350 | BR12   | V006         | 20170630  | 99991231 | 02        | 1        | N         | 50019344  | 06             |              |                |            |            | TestProdVersion006 |
      | CONS_LATAM | 000000000000069381 | BR01   | V011         | 20160610  | 20250610 | 01        | 1        | N         | 50019364  | 01             |              |                |            |            | TestProdVersion008 |
      | CONS_LATAM | 000000000000069382 | BR02   | V012         | 20170220  | 20250220 | 01        | 1        | N         | 50019365  | 01             |              |                |            |            | TestProdVersion009 |
      | CONS_LATAM | 000000000000069360 | BR12   | V001         | 20150520  | 99991231 | 01        | 1        | N         | 50019360  | 01             |              |                |            |            | TestProdVersion1   |
    And I wait "/edm/matl_prod_versn" Async Queue complete

    When I import "/edm/purchasing_info_record_v1" by keyFields "infotype,localPlanPlant,localPurchasingInfoRec,localPurchasingOrg,localvendor"
      | sourceSystem | localPurchasingInfoRec | localvendor | localPurchasingOrg | infotype | localPlanPlant | localMaterialNumber | localProductionVersion |
      | CONS_LATAM   |                        | 0000064543  |                    | 1        | BR12           | 000000000000069349  | V001                   |
      | CONS_LATAM   |                        | 0000063543  |                    | 1        | BR12           | 000000000000069349  | V002                   |
      | CONS_LATAM   |                        | 0000162543  |                    | 1        | BR12           | 000000000000069349  | V012                   |
      | CONS_LATAM   |                        | 0000272543  |                    | 1        | BR12           | 000000000000069349  | V013                   |
      | CONS_LATAM   |                        | 0000202543  |                    | 2        | BR12           | 000000000000069350  | V001                   |
      | CONS_LATAM   |                        | 0000272543  |                    | 2        | BR12           | 000000000000069350  | V002                   |
      | CONS_LATAM   |                        | 0000242543  |                    | 2        | BR12           | 000000000000069350  | V003                   |
      | CONS_LATAM   |                        | 0007862543  |                    | 3        | BR12           | 000000000000069350  | V006                   |
      | CONS_LATAM   |                        | 0000202543  |                    | 2        | BR01           | 000000000000069381  | V011                   |
      | CONS_LATAM   |                        | 0000202543  |                    | 2        | BR02           | 000000000000069382  | V012                   |
      | CONS_LATAM   |                        | 0000064543  |                    | 1        | BR12           | 000000000000069360  | V001                   |
    And I wait "/edm/purchasing_info_record_v1" Async Queue complete

    When I import "/plan/cns_material_plan_status" by keyFields "localMaterialNumber,localPlant,sourceSystem"
      | sourceSystem | localMaterialNumber | localPlant | materialNumber | localParentCode | ppc   | active | dpRelevant | spRelevant | parentActive | noPlanRelevant |
      | CONS_LATAM   | 000000000000069349  | BR12       | 69349          |                 | 69349 | X      |            | X          |              |                |
      | CONS_LATAM   | 000000000000069350  | BR12       | 69350          |                 | 69350 | X      |            | X          |              |                |
      | CONS_LATAM   | 000000000000069381  | BR01       | 69381          |                 | 69381 | X      |            | X          |              |                |
      | CONS_LATAM   | 000000000000069382  | Br02       | 69382          |                 | 69382 | X      |            | X          |              |                |
      | CONS_LATAM   | 000000000000069360  | BR12       | 69360          |                 | 69360 | X      | X          |            |              |                |
    And I wait "/plan/cns_material_plan_status" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmProcessMaster.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/omp/gdm_process" by keyFields "processId"
      | processId                  | active | activeOPRERP | activeSOPERP | description        | endEff              | ERPOrderId | label | locationId      | processTypeId  | productId | startEff            | vendorId |
      | V001/69349/CONS_LATAM/BR12 | YES    | YES          | NO           | TestProdVersion1   | 2998/12/31 23:59:59 |            |       | CONS_LATAM_BR12 | Production     | 69349     | 2015/05/20 00:00:00 |          |
      | V002/69349/CONS_LATAM/BR12 | YES    | YES          | NO           | TestProdVersion2   | 2998/12/31 23:59:59 |            |       | CONS_LATAM_BR12 | Production     | 69349     | 2015/07/13 00:00:00 |          |
      | V012/69349/CONS_LATAM/BR12 | YES    | YES          | NO           | TestProdVersion12  | 2998/12/31 23:59:59 |            |       | CONS_LATAM_BR12 | Production     | 69349     | 2015/09/08 00:00:00 |          |
      | V013/69349/CONS_LATAM/BR12 | YES    | YES          | NO           | TestProdVersion13  | 2998/12/31 23:59:59 |            |       | CONS_LATAM_BR12 | Production     | 69349     | 2017/06/22 00:00:00 |          |
      | V001/69350/CONS_LATAM/BR12 | YES    | YES          | NO           | TestProdVersion001 | 2998/12/31 23:59:59 |            |       | CONS_LATAM_BR12 | Production     | 69350     | 2017/05/24 00:00:00 |          |
      | V002/69350/CONS_LATAM/BR12 | YES    | YES          | NO           | TestProdVersion002 | 2998/12/31 23:59:59 |            |       | CONS_LATAM_BR12 | Production     | 69350     | 2017/06/25 00:00:00 |          |
      | V003/69350/CONS_LATAM/BR12 | YES    | YES          | NO           | TestProdVersion003 | 2998/12/31 23:59:59 |            |       | CONS_LATAM_BR12 | Production     | 69350     | 2017/04/16 00:00:00 |          |
      | V006/69350/CONS_LATAM/BR12 | YES    | YES          | NO           | TestProdVersion006 | 2998/12/31 23:59:59 |            |       | CONS_LATAM_BR12 | Subcontracting | 69350     | 2017/06/30 00:00:00 | 7862543  |
      | V011/69381/CONS_LATAM/BR01 | YES    | YES          | NO           | TestProdVersion008 | 2024/12/31 23:59:59 |            |       | CONS_LATAM_BR01 | Production     | 69381     | 2017/01/15 00:00:00 |          |
      | V012/69382/CONS_LATAM/BR02 | YES    | YES          | NO           | TestProdVersion009 | 2025/02/20 23:59:59 |            |       | CONS_LATAM_BR02 | Production     | 69382     | 2017/06/17 00:00:00 |          |


    And I delete the test data

    And I will remove all data with region "/omp/gdm_process"

