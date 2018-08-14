Feature:GDMStepResourceMaster AEAZ-9092

  @Scenario1
  Scenario: GdmStepResourceMaster
    Given I import "/plan/cns_plan_parameter" by keyFields "sourceSystem,dataObject,attribute,parameter,parameterValue,inclExcl"
      | sourceSystem | dataObject | attribute   | parameter  | parameterValue | inclExcl | comments |
      | CONS_LATAM   | ALL        | SEND_TO_OMP | SYSTEMNAME | CONS_LATAM     | I        |          |
      | CONS_LATAM   | PP         | SEND_TO_OMP | PLANT      | BR12           | I        | IM       |
      | CONS_LATAM   | PP         | SEND_TO_OMP | PLANT      | CO01           | I        | IM       |
      | CONS_LATAM   | PP         | SEND_TO_OMP | PLANT      | AR01           | I        | IM       |
    And I wait "/plan/cns_plan_parameter" Async Queue complete
    Given I import "/edm/matl_mfg_rtng" by keyFields "srcSysCd,matlNum,plntCd,rtngTypCd,rntgGrpCd,rntgGrpCntrNbr,rntgAddtnlCntrNbr,matlRtngVrsnCntrNbr"
      | srcSysCd   | matlNum            | plntCd | rtngTypCd | rntgGrpCd | rntgGrpCntrNbr | rntgAddtnlCntrNbr | matlRtngVrsnCntrNbr | valFromDt | chgNum | delInd | crtDttm  | chgDttm  | matlRtngValid_To |
      | CONS_LATAM | 000000000000069349 | BR11   | N         | 50019342  | 01             | 00000001          | 00000001            | 20150519  |        |        | 20150519 | 20150519 | 99991231         |
      | CONS_LATAM | 000000000000069349 | BR12   | N         | 50019343  | 02             | 00000002          | 00000002            | 20150713  |        |        | 20150713 | 20150713 | 99991231         |
      | CONS_LATAM | 000000000000069349 | BR12   | N         | 50019343  | 03             | 00000003          | 00000003            | 20150724  |        |        | 20150724 | 20150724 | 99991231         |
      | CONS_LATAM | 000000000000069349 | BR12   | N         | 50019343  | 04             | 00000004          | 00000004            | 20170622  |        |        | 20170622 | 20170622 | 99991231         |
      | CONS_LATAM | 000000000000069999 | BR12   | N         | 50019343  | 05             | 00000005          | 00000005            | 20170622  |        |        | 20170622 | 20170622 | 99991231         |
      | CONS_LATAM | 000000000000069999 | BR12   | N         | 50019343  | 06             | 00000006          | 00000006            | 20170622  |        |        | 20170622 | 20170622 | 99991231         |
    And I wait "/edm/matl_mfg_rtng" Async Queue complete
    Given I import "/edm/mfg_rtng_itm" by keyFields "srcSysCd,rtngTypCd,rtngGrpCd,rtngItmNum,rtngItmVersnCntrNbr"
      | srcSysCd   | rtngTypCd | rtngGrpCd | rtngItmNum | rtngItmVersnCntrNbr | valFromDt | chgNum | delInd | crtDttm  | chgDttm | rtngSupNdeNum | operNum | operCd | wrkCntrCd | plntCd | operDesc | operUom | bsQty | rtgItemEndDate | attr2Desc         |
      | CONS_LATAM | N         | 50019343  | 00000001   | 00000001            | 20150519  |        |        | 20150519 |         |               | 0010    | PP01   | 10001460  | BR12   | Produzir | EA      | 1000  | 99991231       | ListerinePackCrew |
      | CONS_LATAM | N         | 50019343  | 00000002   | 00000002            | 20150713  |        |        | 20150713 |         |               | 0010    | PP01   | 10001458  | BR12   | Produzir | EA      | 1000  | 99991231       | ListerinePackCrew |
      | CONS_LATAM | N         | 50019343  | 00000006   | 00000006            | 20150724  |        |        | 20150724 |         |               | 0010    | PP01   | 10001460  | BR12   | Produzir | EA      | 1000  | 99991231       |                   |
      | CONS_LATAM | N         | 50019343  | 00000007   | 00000007            | 20170622  |        |        | 20170622 |         |               | 0010    | PP01   | 10001459  | BR12   | Produzir | EA      | 1000  | 99991231       | ListerinePackCrew |
      | CONS_LATAM | N         | 59999999  | 00000001   | 00000001            | 20170622  |        |        | 20170622 |         |               | 0020    | PP07   | 19999999  | BR12   | Produzir | EA      | 1000  | 99991231       | ListerinePackCrew |
    And I wait "/edm/mfg_rtng_itm" Async Queue complete
    Given I import "/edm/mfg_rtng_itm_nde" by keyFields "srcSysCd,rtngTypCd,rtngGrpCd,rtngGrpCntrNbr,rtngSqncNum,rtngNdeNum,rtngNdeVrsnCntrNbr"
      | srcSysCd   | rtngTypCd | rtngGrpCd | rtngGrpCntrNbr | rtngSqncNum | rtngNdeNum | rtngNdeVrsnCntrNbr | valFromDt | chgNum | delInd | CreateDttm | chgDttm |
      | CONS_LATAM | N         | 50019343  | 01             | 000000      | 00000001   | 00000001           | 20150519  |        |        | 20150519   |         |
      | CONS_LATAM | N         | 50019343  | 02             | 000000      | 00000002   | 00000002           | 20150713  |        |        | 20150713   |         |
      | CONS_LATAM | N         | 50019343  | 06             | 000000      | 00000006   | 00000006           | 20150724  |        |        | 20150724   |         |
      | CONS_LATAM | N         | 50019343  | 07             | 000000      | 00000007   | 00000007           | 20170622  |        |        | 20170622   |         |
      | CONS_LATAM | N         | 59999999  | 01             | 000000      | 00000001   | 00000001           | 20170622  |        |        | 20170622   |         |
    And I wait "/edm/mfg_rtng_itm_nde" Async Queue complete
    Given I import "/edm/matl_prod_versn" by keyFields "srcSysCd,matlNum,plntCd,prdntVrsnNum"
      | srcSysCd   | matlNum            | plntCd | prdntVrsnNum | valFromDt | valToDt  | altBomNum | bomUsgCd | rtngTypCd | rtngGrpCd | rtngGrpCntrNum |
      | CONS_LATAM | 000000000000069349 | BR11   | V001         | 20150519  | 99991231 | 01        | 1        | N         | 50019342  | 01             |
      | CONS_LATAM | 000000000000069349 | BR12   | V002         | 20150713  | 99991231 | 01        | 1        | N         | 50019343  | 02             |
      | CONS_LATAM | 000000000000069349 | BR12   | V003         | 20150908  | 99991231 | 01        | 1        | N         | 50019343  | 03             |
      | CONS_LATAM | 000000000000069349 | BR12   | V004         | 20170622  | 99991231 | 02        | 1        | N         | 50019343  | 04             |
      | CONS_LATAM | 000000000000069349 | BR12   | V005         | 20170622  | 99991231 | 01        | 1        | N         | 50019343  | 05             |
      | CONS_LATAM | 000000000000069349 | BR12   | V006         | 20170622  | 99991231 | 01        | 1        | N         | 50019343  | 06             |
    And I wait "/edm/matl_prod_versn" Async Queue complete
    Given I import "/edm/mfg_rtg_parm" by keyFields "srcSysCd,rtgTypeCd,rtgGrpCd,rtgNodeNum,intrnlSubCalcNum,intrnlPrcsInstrNum,intrnlPrcsInstrCharValNum,mfgParmVersCntrNbr"
      | srcSysCd   | rtgTypeCd | rtgGrpCd | rtgNodeNum | intrnlSubCalcNum | intrnlPrcsInstrNum | intrnlPrcsInstrCharValNum | mfgParmVersCntrNbr | vldFromDt | delInd | chgNum | crtDttm  | chgDttm  | charCd | charVal |
      | CONS_LATAM | N         | 50019342 | 00000001   | 00000001         |                    | 00000002                  | 00000002           | 20150519  |        |        | 20071226 | 20170630 | EQUIPE | 2.0     |
      | CONS_LATAM | N         | 50019343 | 00000002   | 00000002         |                    | 00000023                  | 00000023           | 20150713  |        |        | 20130412 | 20171004 | EQUIPE | 2.0     |
      | CONS_LATAM | N         | 50019343 | 00000003   | 00000003         |                    | 00000044                  | 00000044           | 20150713  |        |        | 20130412 | 20170622 | EQUIPE | 2.0     |
      | CONS_LATAM | N         | 50019343 | 00000004   | 00000004         |                    | 00000002                  | 00000002           | 20150713  |        |        | 20130412 | 20170622 | EQUIPE |         |
      | CONS_LATAM | N         | 50019343 | 00000005   | 00000005         |                    | 00000002                  | 00000002           | 20150713  |        |        | 20130412 | 20170622 | EQUIPE | 2.0     |
      | CONS_LATAM | N         | 50019343 | 00000006   | 00000006         |                    | 00000002                  | 00000002           | 20150713  |        |        | 20130412 | 20170622 | EQUIPE | 2,0     |
    And I wait "/edm/mfg_rtg_parm" Async Queue complete

    Given I import "/edm/wrk_ctr" by keyFields "srcSysCd,wrkCtrTypeCd,wrkCtrNum,vldFromDt,vldToDt,wrkCtrCd,plntCd,wrkCtrCatCd,delInd,wrkCtrUsgCd,wrkCtrLocCd,respPrsnNum,wrkCtrActvCd,lockInd,schdlngInd,setupTypeCd,oprCd,setupFrmlCd,runFrmlCd,teardownFrmlCd,capyNum,locGrpCd,machTypeCd,plnrGrpCd,othFrmlCd,suplAreaCd,slocCd,mixingInd,wrkCtrDesc"

      | srcSysCd   | wrkCtrTypeCd | wrkCtrNum | vldFromDt | vldToDt  | wrkCtrCd  | plntCd | wrkCtrCatCd | delInd | wrkCtrUsgCd | wrkCtrLocCd | respPrsnNum | wrkCtrActvCd | lockInd | schdlngInd | setupTypeCd | oprCd | setupFrmlCd | runFrmlCd | teardownFrmlCd | capyNum  | locGrpCd | machTypeCd | plnrGrpCd | othFrmlCd | suplAreaCd | slocCd | mixingInd | wrkCtrDesc  |
      | CONS_LATAM | A            | 10001458  | 20020520  | 99991231 | SAN-W+D3  | BR12   | 0001        |        | 007         | 300         |             |              |         |            | PP01        |       |             |           |                | 10001450 |          |            |           |           |            |        |           | MáquinaW+D3 |
      | CONS_LATAM | A            | 10001459  | 20020520  | 99991231 | SAN-W+D2  | BR12   | 0001        |        | 009         | 300         |             |              |         |            | PP01        |       |             |           |                | 10001451 |          |            |           |           |            |        |           | MáquinaW+D2 |
      | CONS_LATAM | A            | 10001460  | 20020520  | 99991231 | SAN-W+D1  | BR12   | 0001        |        | 009         | 300         |             |              |         |            | PP01        |       |             |           |                | 10001452 |          |            |           |           |            |        |           | MáquinaW+D1 |
      | CONS_LATAM | A            | 19999999  | 20020520  | 99991231 | SAN-R+D1  | BR12   | 0001        |        | 009         | 300         |             |              |         |            | PP07        |       |             |           |                | 19999999 |          |            |           |           |            |        |           | MáquinaR+D1 |
      | CONS_LATAM | A            | 10001460  | 20020520  | 99991231 | SAN-W+D60 | BR11   | 0001        |        | 009         | 300         |             |              |         |            | PP07        |       |             |           |                | 10001460 |          |            |           |           |            |        |           | MáquinaW+60 |
      | CONS_LATAM | A            | 10001461  | 20020520  | 99991231 | SAN-W+D61 | BR12   | 0001        |        | 009         | 300         |             |              |         |            | PP01        |       |             |           |                | 10001461 |          |            |           |           |            |        |           | MáquinaW+61 |
      | CONS_LATAM | A            | 10001462  | 20020520  | 99991231 | SAN-W+D62 | BR12   | 0001        | X      | 009         | 300         |             |              |         |            | PP01        |       |             |           |                | 10001462 |          |            |           |           |            |        |           | MáquinaW+62 |
      | CONS_LATAM | A            | 10001463  | 20020520  | 99991231 | SAN-W+D63 | BR12   | 0001        |        | 009         | 300         |             |              |         |            | PP01        |       |             |           |                | 10001463 |          |            |           |           |            |        |           | MáquinaW+63 |
      | CONS_LATAM | A            | 10001464  | 20020520  | 20180501 | SAN-W+D64 | BR12   | 0001        |        | 009         | 300         |             |              |         |            | PP01        |       |             |           |                | 10001464 |          |            |           |           |            |        |           | MáquinaW+64 |
      | CONS_LATAM | A            | 10001465  | 20020520  | 20180501 | SAN-W+D65 | BR12   | 0001        |        | 009         | 300         |             |              |         |            | PP01        |       |             |           |                | 10001465 |          |            |           |           |            |        |           | MáquinaW+65 |
    And I wait "/edm/wrk_ctr" Async Queue complete
    Given I import "/edm/wrk_ctr_capy" by keyFields "srcSysCd,wrkCtrTypeCd,wrkCtrNum,capyAllcNbr,capyNum"
      | srcSysCd   | wrkCtrTypeCd | wrkCtrNum | capyAllcNbr | capyNum  |
      | CONS_LATAM | A            | 10001460  | 1000        | 40001460 |
      | CONS_LATAM | A            | 10001460  | 2000        | 40002460 |
      | CONS_LATAM | A            | 10001461  | 1001        | 40001461 |
      | CONS_LATAM | A            | 10001461  | 2001        | 40002461 |
      | CONS_LATAM | A            | 10001462  | 1002        | 40001462 |
      | CONS_LATAM | A            | 10001462  | 2002        | 40002462 |
      | CONS_LATAM | A            | 10001463  | 1003        | 40001463 |
      | CONS_LATAM | A            | 10001463  | 2003        | 40002463 |
      | CONS_LATAM | A            | 10001464  | 1004        | 40001464 |
      | CONS_LATAM | A            | 10001464  | 2004        | 40002464 |
      | CONS_LATAM | A            | 10001464  | 1004        | 40001465 |
      | CONS_LATAM | A            | 10001464  | 2004        | 40002465 |
      | CONS_LATAM | A            | 10001465  | 2004        | 40001465 |
    And I wait "/edm/wrk_ctr_capy" Async Queue complete
    Given I import "/edm/capy_hdr" by keyFields "srcSysCd,capyNum,capyNbr,strtTm,endTm,fctryCalCd,capyCatCd"
      | srcSysCd   | capyNum  | capyNbr | strtTm | endTm | fctryCalCd | capyCatCd | capyNm    | plntCd |
      | CONS_LATAM | 40001460 | 1       |        |       | BR         | 001       | SAN-W+D60 | BR11   |
      | CONS_LATAM | 40001460 | 1       |        |       | BR         | 002       | SAN-W+D60 | BR11   |
      | CONS_LATAM | 40002460 | 1       |        |       | BR         | 002       | SAN-R+D60 | BR11   |
      | CONS_LATAM | 40001461 | 1       |        |       | BR         | 001       | SAN-W+D61 | BR12   |
      | CONS_LATAM | 40002461 | 1       |        |       | BR         | 002       | SAN-R+D61 | BR12   |
      | CONS_LATAM | 40001462 | 1       |        |       | BR         | 001       | SAN-W+D62 | BR12   |
      | CONS_LATAM | 40002462 | 1       |        |       | BR         | 002       | SAN-R+D62 | BR12   |
      | CONS_LATAM | 40001463 | 1       |        |       | BR         | 001       | SAN-W+D63 | BR12   |
      | CONS_LATAM | 40002463 | 1       |        |       | BR         | 007       | SAN-R+D63 | BR12   |
      | CONS_LATAM | 40001464 | 1       |        |       | BR         | 001       | SAN-W+D64 | BR12   |
      | CONS_LATAM | 40002464 | 1       |        |       | BR         | 002       | SAN-R+D64 | BR12   |
      | CONS_LATAM | 40002465 | 1       |        |       | BR         | 001       | SAN-W+D65 | BR12   |
      | CONS_LATAM | 40001465 | 1       |        |       | BR         | 002       | SAN-R+D65 | BR12   |
      | CONS_LATAM | 40002465 | 1       |        |       | BR         | 002       | SAN-W+D65 | BR12   |
      | CONS_LATAM | 40001465 | 1       |        |       | BR         | 001       | SAN-R+D65 | BR12   |
      | CONS_LATAM | 10001450 | 1       |        |       | BR         | 001       | SAN-W+D60 | BR11   |
      | CONS_LATAM | 10001451 | 1       |        |       | BR         | 002       | SAN-W+D60 | BR11   |
      | CONS_LATAM | 10001452 | 1       |        |       | BR         | 001       | SAN-W+D60 | BR11   |
      | CONS_LATAM | 19999999 | 1       |        |       | BR         | 002       | SAN-W+D60 | BR11   |
    And I wait "/edm/capy_hdr" Async Queue complete
    When I submit task with xml file "xml/omp/GDMStepResourceMaster.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMStepResource_master.tsv"
    Then I check file data for filename "GDMStepResource_master.tsv" by keyFields "stepResourceId"
#    Then I check region data "/omp/gdm_step_resource" by keyFields "stepResourceId"
      | resourceId                | machineId                | quantity | minQuantity | stepResourceId                                     | active | operationId                     | stepResourceType | activeOPRERP | activeSOPERP |
      | CONS_LATAM_BR11/SAN-R+D60 | CONS_LATAM_BR12/SAN-W+D1 | 0.000    | 2.000       | CONS_LATAM_BR11/SAN-R+D60/SAN-W+D1/V006/69349/0010 | YES    | V006/69349/CONS_LATAM_BR12/0010 | production       | YES          | NO           |
