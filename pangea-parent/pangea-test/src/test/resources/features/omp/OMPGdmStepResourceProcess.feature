@pangea_test @AEAZ-9092
Feature: OMPGdmStepResourceProcess AEAZ-9092

  @Scenario1
  Scenario: GdmStepResourceProcess

    When I import "/edm/mfg_order" by keyFields "sourceSysCd,mfgOrdrNum"
      | sourceSysCd | mfgOrdrNum   | mfgOrdrTypCd | plntCd | crtdDttm | chgDttm  | delInd | strtDt   | actRlseDt | rsrvtnNum  | rtngTypCd | rtngGrpCd | rtngGrpCntrNum | bomCatCd | bomNum   | bomAltNum | mrpCntrllrCd | ordrRtngNum | mfgOrdrSttsCd           |
      | CONS_LATAM  | 000001647950 | PP01         | BR11   | 20180613 | 20180613 |        | 20180613 | 20180613  | 0078293328 | N         | 50019350  | 01             | M        | 00034151 | 01        |              | 0000647950  | I0002                   |
      | CONS_LATAM  | 000001647951 | PP01         | BR12   | 20180613 | 20180613 |        | 20180613 | 20180613  | 0078293328 | N         | 50019351  | 01             | M        | 00034151 | 01        |              | 0000647951  | I0002                   |
      | CONS_LATAM  | 000001647952 | PP01         | BR12   | 20150613 | 20150613 |        | 20150613 | 20150613  | 0078293328 | N         | 50019352  | 01             | M        | 00034151 | 01        |              | 0000647952  | I0002                   |
      | CONS_LATAM  | 000001647953 | PP01         | BR12   | 20180613 | 20180613 |        | 20180613 | 20180613  | 0078293328 | N         | 50019353  | 01             | M        | 00034151 | 01        |              | 0000647953  | I0002,I0012,I0045,I0046 |
      | CONS_LATAM  | 000001647954 | PP01         | BR11   | 20180613 | 20180613 |        | 20180613 | 20180613  | 0078293328 | N         | 50019354  | 01             | M        | 00034151 | 01        |              | 0000647954  | I0002                   |
      | CONS_LATAM  | 000001647955 | PP01         | BR12   | 20180613 | 20180613 |        | 20180613 | 20180613  | 0078293328 | N         | 50019355  | 01             | M        | 00034151 | 01        |              | 0000647955  | I0002                   |
      | CONS_LATAM  | 000001647956 | PP01         | BR12   | 20180613 | 20180613 |        | 20180613 | 20180613  | 0078293328 | N         | 50019356  | 01             | M        | 00034151 | 01        |              | 0000647956  | I0002                   |
      | CONS_LATAM  | 000001647957 | PP01         | BR12   | 20180613 | 20180613 |        | 20180613 | 20180613  | 0078293328 | N         | 50019357  | 01             | M        | 00034151 | 01        |              | 0000647957  | I0002,I0011,I0044       |
      | CONS_LATAM  | 000001647958 | PP01         | BR12   | 20180613 | 20180613 | X      | 20180613 | 20180613  | 0078293328 | N         | 50019358  | 01             | M        | 00034151 | 01        |              | 0000647958  | I0002,I0011,I0044       |
    And I wait "/edm/mfg_order" Async Queue complete

    When I import "/edm/mfg_order_itm" by keyFields "srcSysCd,mfgOrdrNum,lnItmNbr"
      | srcSysCd   | mfgOrdrNum   | lnItmNbr | mfgPlnndOrdrNum | matlNum            | scrpQty | itmQty | rcvdQty | prdtnUomCd | plntCd | prdntVrsnNum |
      | CONS_LATAM | 000001647950 | 0001     |                 | 000000000000069350 |         | 2527   | 2523    | KI         | BR11   | V001         |
      | CONS_LATAM | 000001647951 | 0001     |                 | 000000000000069351 |         | 2527   | 2523    | KI         | BR12   | V001         |
      | CONS_LATAM | 000001647952 | 0001     |                 | 000000000000069352 |         | 2527   | 2523    | KI         | BR12   | V001         |
      | CONS_LATAM | 000001647953 | 0001     |                 | 000000000000069353 |         | 2527   | 2523    | KI         | BR12   | V001         |
      | CONS_LATAM | 000001647954 | 0001     |                 | 000000000000069354 |         | 2527   | 2523    | KI         | BR12   | V001         |
      | CONS_LATAM | 000001647955 | 0001     |                 | 000000000000069355 |         | 2527   | 2523    | KI         | BR12   | V001         |
      | CONS_LATAM | 000001647956 | 0001     |                 | 000000000000069356 |         | 2527   | 2523    | KI         | BR12   | V001         |
      | CONS_LATAM | 000001647957 | 0001     |                 | 000000000000069357 |         | 2527   | 2523    | KI         | BR12   | V001         |
      | CONS_LATAM | 000001647958 | 0001     |                 | 000000000000069358 |         | 2527   | 2523    | KI         | BR12   | V001         |
    And I wait "/edm/mfg_order_itm" Async Queue complete

    When I import "/edm/mfg_order_rtng" by keyFields "srcSysCd,ordrRtngNum,ordrRtngCtrNum"
      | srcSysCd   | ordrRtngNum | ordrRtngCtrNum | operNum | operCd | wrkCntrId | operDesc |
      | CONS_LATAM | 0000647950  | 00000001       | 0010    | PP01   | 10001460  | Produzir |
      | CONS_LATAM | 0000647951  | 00000001       | 0010    | PP07   | 10001461  | Produzir |
      | CONS_LATAM | 0000647952  | 00000001       | 0010    | PP01   | 10001462  | Produzir |
      | CONS_LATAM | 0000647953  | 00000001       | 0010    | PP01   | 10001463  | Produzir |
      | CONS_LATAM | 0000647954  | 00000001       | 0010    | PP01   | 10001464  | Produzir |
      | CONS_LATAM | 0000647955  | 00000001       | 0010    | PP01   | 10001465  | Produzir |
      | CONS_LATAM | 0000647956  | 00000001       | 0010    | PP01   | 10001466  | Produzir |
      | CONS_LATAM | 0000647957  | 00000001       | 0010    | PP01   | 10001467  | Produzir |
      | CONS_LATAM | 0000647958  | 00000001       | 0010    | PP01   | 10001468  | Produzir |
    And I wait "/edm/mfg_order_rtng" Async Queue complete

    When I import "/edm/matl_prod_versn" by keyFields "srcSysCd,matlNum,plntCd,prdntVrsnNum"
      | srcSysCd   | matlNum            | plntCd | prdntVrsnNum | valFromDt | valToDt  | altBomNum | bomUsgCd | rtngTypCd | rtngGrpCd | rtngGrpCntrNum |
      | CONS_LATAM | 000000000000069350 | BR11   | V001         | 20150519  | 99991231 | 01        | 1        | N         | 50019350  | 01             |
      | CONS_LATAM | 000000000000069351 | BR12   | V001         | 20150519  | 99991231 | 01        | 1        | N         | 50019351  | 01             |
      | CONS_LATAM | 000000000000069352 | BR12   | V001         | 20150519  | 99991231 | 01        | 1        | N         | 50019352  | 01             |
      | CONS_LATAM | 000000000000069353 | BR12   | V001         | 20150519  | 99991231 | 01        | 1        | N         | 50019353  | 01             |
      | CONS_LATAM | 000000000000069354 | BR12   | V001         | 20150519  | 99991231 | 01        | 1        | N         | 50019354  | 01             |
      | CONS_LATAM | 000000000000069355 | BR12   | V001         | 20150519  | 99991231 | 01        | 1        | N         | 50019355  | 01             |
      | CONS_LATAM | 000000000000069356 | BR12   | V001         | 20150519  | 99991231 | 01        | 1        | N         | 50019356  | 01             |
      | CONS_LATAM | 000000000000069357 | BR12   | V001         | 20150519  | 99991231 | 01        | 1        | N         | 50019357  | 01             |
      | CONS_LATAM | 000000000000069358 | BR12   | V001         | 20150519  | 99991231 | 01        | 1        | N         | 50019358  | 01             |
    And I wait "/edm/matl_prod_versn" Async Queue complete

    When I import "/edm/mfg_rtg_parm" by keyFields "srcSysCd,rtgTypeCd,rtgGrpCd,rtgNodeNum,intrnlSubCalcNum,intrnlPrcsInstrNum,intrnlPrcsInstrCharValNum,mfgParmVersCntrNbr"
      | srcSysCd   | rtgTypeCd | rtgGrpCd | rtgNodeNum | intrnlSubCalcNum | intrnlPrcsInstrNum | intrnlPrcsInstrCharValNum | mfgParmVersCntrNbr | vldFromDt | delInd | chgNum | crtDttm  | chgDttm  | charCd     | charVal |
      | CONS_LATAM | N         | 50019350 | 00000001   | 00000001         |                    | 00000001                  | 00000001           | 20150519  |        |        | 20071226 | 20170630 | EFICIENCIA | 80.1    |
      | CONS_LATAM | N         | 50019351 | 00000001   | 00000001         |                    | 00000001                  | 00000001           | 20150713  |        |        | 20130412 | 20171004 | EFICIENCIA | 78.0    |
      | CONS_LATAM | N         | 50019352 | 00000001   | 00000001         |                    | 00000001                  | 00000001           | 20150713  |        |        | 20130412 | 20170622 | EFICIENCIA | 76.1    |
      | CONS_LATAM | N         | 50019353 | 00000001   | 00000001         |                    | 00000001                  | 00000001           | 20150713  |        |        | 20130412 | 20170622 | EFICIENCIA | 76.1    |
      | CONS_LATAM | N         | 50019354 | 00000001   | 00000001         |                    | 00000001                  | 00000001           | 20150519  |        |        | 20071226 | 20170630 | EFICIENCIA | 80.1    |
      | CONS_LATAM | N         | 50019355 | 00000001   | 00000001         |                    | 00000001                  | 00000001           | 20150713  |        |        | 20130412 | 20171004 | EFICIENCIA | 78.0    |
      | CONS_LATAM | N         | 50019356 | 00000001   | 00000001         |                    | 00000001                  | 00000001           | 20150713  |        |        | 20130412 | 20170622 | EFICIENCIA |         |
      | CONS_LATAM | N         | 50019357 | 00000001   | 00000001         |                    | 00000001                  | 00000001           | 20150713  |        |        | 20130412 | 20170622 | EFICIENCIA | 76.1    |
      | CONS_LATAM | N         | 50019358 | 00000001   | 00000001         |                    | 00000001                  | 00000001           | 20150713  |        |        | 20130412 | 20170622 | EFICIENCIA | 76.1    |
    And I wait "/edm/mfg_rtg_parm" Async Queue complete

    When I import "/edm/wrk_ctr" by keyFields "srcSysCd,wrkCtrTypeCd,wrkCtrNum"
      | srcSysCd   | wrkCtrTypeCd | wrkCtrNum | vldFromDt | vldToDt  | wrkCtrCd | plntCd | wrkCtrCatCd | delInd | wrkCtrUsgCd | wrkCtrLocCd | oprCd | capyNum  | wrkCtrDesc     |
      | CONS_LATAM | A            | 10001460  | 20020520  | 99991231 | SAN-W+D0 | BR11   | 0001        |        | 009         | 300         | PP07  | 10001450 | Description D0 |
      | CONS_LATAM | A            | 10001461  | 20020520  | 99991231 | SAN-W+D1 | BR12   | 0001        |        | 009         | 300         | PP01  | 10001451 | Description D1 |
      | CONS_LATAM | A            | 10001462  | 20020520  | 99991231 | SAN-W+D2 | BR12   | 0001        |        | 009         | 300         | PP01  | 10001452 | Description D2 |
      | CONS_LATAM | A            | 10001463  | 20020520  | 99991231 | SAN-W+D3 | BR12   | 0001        |        | 009         | 300         | PP01  | 10001453 | Description D3 |
      | CONS_LATAM | A            | 10001464  | 20020520  | 99991231 | SAN-W+D4 | BR12   | 0001        |        | 007         | 300         | PP01  | 10001454 | Description D4 |
      | CONS_LATAM | A            | 10001465  | 20020520  | 99991231 | SAN-W+D5 | BR12   | 0001        |        | 009         | 300         | PP01  | 10001455 | Description D5 |
      | CONS_LATAM | A            | 10001466  | 20020520  | 99991231 | SAN-W+D6 | BR12   | 0001        |        | 009         | 300         | PP01  | 10001456 | Description D6 |
      | CONS_LATAM | A            | 10001467  | 20020520  | 99991231 | SAN-W+D7 | BR12   | 0001        |        | 009         | 300         | PP01  | 10001457 | Description D7 |
      | CONS_LATAM | A            | 10001468  | 20020520  | 99991231 | SAN-W+D7 | BR12   | 0001        |        | 009         | 300         | PP01  | 10001457 | Description D7 |
    And I wait "/edm/wrk_ctr" Async Queue complete

    When I import "/edm/wrk_ctr_capy" by keyFields "srcSysCd,wrkCtrTypeCd,wrkCtrNum,capyAllcNbr"
      | srcSysCd   | wrkCtrTypeCd | wrkCtrNum | capyAllcNbr | capyNum  |
      | CONS_LATAM | A            | 10001460  | 1000        | 10001450 |
      | CONS_LATAM | A            | 10001460  | 2000        | 10002450 |
      | CONS_LATAM | A            | 10001461  | 1001        | 10001451 |
      | CONS_LATAM | A            | 10001461  | 2001        | 10002451 |
      | CONS_LATAM | A            | 10001462  | 1002        | 10001452 |
      | CONS_LATAM | A            | 10001462  | 2002        | 10002452 |
      | CONS_LATAM | A            | 10001463  | 1003        | 10001453 |
      | CONS_LATAM | A            | 10001463  | 2003        | 10002453 |
      | CONS_LATAM | A            | 10001464  | 1004        | 10001454 |
      | CONS_LATAM | A            | 10001464  | 2004        | 10002454 |
      | CONS_LATAM | A            | 10001465  | 1005        | 10001455 |
      | CONS_LATAM | A            | 10001465  | 2005        | 10002455 |
      | CONS_LATAM | A            | 10001466  | 1006        | 10001456 |
      | CONS_LATAM | A            | 10001466  | 2006        | 10002456 |
      | CONS_LATAM | A            | 10001467  | 1007        | 10001457 |
      | CONS_LATAM | A            | 10001467  | 2007        | 10002457 |
      | CONS_LATAM | A            | 10001468  | 1008        | 10001458 |
      | CONS_LATAM | A            | 10001468  | 2008        | 10002458 |
    And I wait "/edm/wrk_ctr_capy" Async Queue complete

    When I import "/edm/capy_hdr" by keyFields "srcSysCd,capyNum"
      | srcSysCd   | capyNum  | capyNbr | strtTm | endTm | fctryCalCd | capyCatCd | capyNm   | plntCd |
      | CONS_LATAM | 10001450 | 1       |        |       | BR         | 001       | SAN-W+D0 | BR11   |
      | CONS_LATAM | 10002450 | 1       |        |       | BR         | 002       | SAN-R+D0 | BR11   |
      | CONS_LATAM | 10001451 | 1       |        |       | BR         | 001       | SAN-W+D1 | BR12   |
      | CONS_LATAM | 10002451 | 1       |        |       | BR         | 002       | SAN-R+D1 | BR12   |
      | CONS_LATAM | 10001452 | 1       |        |       | BR         | 001       | SAN-W+D2 | BR12   |
      | CONS_LATAM | 10002452 | 1       |        |       | BR         | 002       | SAN-R+D2 | BR12   |
      | CONS_LATAM | 10001453 | 1       |        |       | BR         | 001       | SAN-W+D3 | BR12   |
      | CONS_LATAM | 10002453 | 1       |        |       | BR         | 002       | SAN-R+D3 | BR12   |
      | CONS_LATAM | 10001454 | 1       |        |       | BR         | 001       | SAN-W+D4 | BR12   |
      | CONS_LATAM | 10002454 | 1       |        |       | BR         | 002       | SAN-R+D4 | BR12   |
      | CONS_LATAM | 10001455 | 1       |        |       | BR         | 001       | SAN-W+D5 | BR12   |
      | CONS_LATAM | 10002455 | 1       |        |       | BR         | 005       | SAN-R+D5 | BR12   |
      | CONS_LATAM | 10001456 | 1       |        |       | BR         | 001       | SAN-W+D6 | BR12   |
      | CONS_LATAM | 10002456 | 1       |        |       | BR         | 002       | SAN-R+D6 | BR12   |
      | CONS_LATAM | 10001457 | 1       |        |       | BR         | 001       | SAN-W+D7 | BR12   |
      | CONS_LATAM | 10002457 | 1       |        |       | BR         | 002       | SAN-R+D7 | BR12   |
      | CONS_LATAM | 10001458 | 1       |        |       | BR         | 001       | SAN-W+D8 | BR12   |
      | CONS_LATAM | 10002458 | 1       |        |       | BR         | 002       | SAN-R+D8 | BR12   |
    And I wait "/edm/capy_hdr" Async Queue complete

#    When I import "/plan/cns_plant_attr" by keyFields "sourceSystem,localPlant"
#      | sourceSystem | localPlant | localPlantName      | localPlanningRelevant | locationAttribute1Desc | locationAttribute1Value | locationAttribute2Desc | locationAttribute2Value | planLocTypeDesc              | planLocTypeId | plant | plantType     |
#      | CONS_LATAM   | BR11       | Campos - Indus      |                       | Country                | Brazil                  | Volume                 | High                    | Internal Manufacturing Plant | IM            | BR59  | Manufacturing |
#      | CONS_LATAM   | BR12       | Campos - Indus plan | X                     | Country                | Brazil                  | Volume                 | High                    | Internal Manufacturing Plant | IM            | BR59  | Manufacturing |
#    And I wait "/plan/cns_plant_attr" Async Queue complete

    When I import "/project_one/t430" by keyFields "mandt,steus"
      | mandt | steus | term |
      | 120   | PP01  | X    |
      | 120   | PP07  |      |
    And I wait "/project_one/t430" Async Queue complete

    When I import "/plan/cns_plan_parameter" by keyFields "attribute,dataObject,parameter,parameterValue,sourceSystem"
      | sourceSystem | dataObject | attribute   | parameter  | parameterValue | inclExcl | comments |
      | CONS_LATAM   | ALL        | SEND_TO_OMP | SYSTEMNAME | CONS_LATAM     | I        |          |
      | CONS_LATAM   | PP         | SEND_TO_OMP | PLANT      | BR12           | I        |          |
      | CONS_LATAM   | PP         | SEND_TO_OMP | PLANT      | CO01           | I        |          |
      | CONS_LATAM   | PP         | SEND_TO_OMP | PLANT      | AR01           | I        |          |
      | CONS_LATAM   | PP         | SEND_TO_OMP | ORDERTYPE  | PP01           | I        |          |
      | CONS_LATAM   | PP         | SEND_TO_OMP | ORDERTYPE  | PP02           | I        |          |
      | CONS_LATAM   | PP         | SEND_TO_OMP | ORDERTYPE  | PP03           | I        |          |
      | CONS_LATAM   | PP         | SEND_TO_OMP | RELDAYS    | 365            | I        |          |
      | CONS_LATAM   | PP         | SEND_TO_OMP | ORDERSTAT  | I0001          | I        |          |
      | CONS_LATAM   | PP         | SEND_TO_OMP | ORDERSTAT  | I0002          | I        |          |
      | CONS_LATAM   | PP         | SEND_TO_OMP | ORDERSTAT  | I0009          | E        |          |
      | CONS_LATAM   | PP         | SEND_TO_OMP | ORDERSTAT  | I0012          | E        |          |
      | CONS_LATAM   | PP         | SEND_TO_OMP | ORDERSTAT  | I0045          | E        |          |
      | CONS_LATAM   | PP         | SEND_TO_OMP | ORDERSTAT  | I0046          | E        |          |
    And I wait "/plan/cns_plan_parameter" Async Queue complete

    When I import "/edm/mfg_rtng_itm" by keyFields "srcSysCd,rtngTypCd,rtngGrpCd,rtngItmNum,rtngItmVersnCntrNbr"
      | srcSysCd   | rtngTypCd | rtngGrpCd | rtngItmNum | rtngItmVersnCntrNbr | valFromDt | chgNum | delInd | crtDttm  | chgDttm | rtngSupNdeNum | operNum | operCd | wrkCntrCd | plntCd | operDesc | operUom | bsQty | rtgItemEndDate |
      | CONS_LATAM | N         | 50019350  | 00000001   | 00000001            | 20150519  |        |        | 20150519 |         |               | 0010    | PP07   | 10001460  | BR11   | Produzir | EA      | 1000  | 99991231       |
      | CONS_LATAM | N         | 50019351  | 00000001   | 00000001            | 20150713  |        |        | 20150713 |         |               | 0010    | PP01   | 10001461  | BR12   | Produzir | EA      | 1000  | 99991231       |
      | CONS_LATAM | N         | 50019352  | 00000001   | 00000001            | 20150724  |        |        | 20150724 |         |               | 0010    | PP01   | 10001462  | BR12   | Produzir | EA      | 1000  | 99991231       |
      | CONS_LATAM | N         | 50019353  | 00000001   | 00000001            | 20170622  |        |        | 20170622 |         |               | 0010    | PP01   | 10001463  | BR12   | Produzir | EA      | 1000  | 99991231       |
      | CONS_LATAM | N         | 50019354  | 00000001   | 00000001            | 20150519  |        |        | 20150519 |         |               | 0010    | PP01   | 10001464  | BR12   | Produzir | EA      | 1000  | 99991231       |
      | CONS_LATAM | N         | 50019355  | 00000001   | 00000001            | 20150713  |        |        | 20150713 |         |               | 0010    | PP01   | 10001465  | BR12   | Produzir | EA      | 1000  | 99991231       |
      | CONS_LATAM | N         | 50019356  | 00000001   | 00000001            | 20150724  |        |        | 20150724 |         |               | 0010    | PP01   | 10001466  | BR12   | Produzir | EA      | 1000  | 99991231       |
      | CONS_LATAM | N         | 50019357  | 00000001   | 00000001            | 20170622  |        |        | 20170622 |         |               | 0010    | PP01   | 10001467  | BR12   | Produzir | EA      | 1000  | 99991231       |
      | CONS_LATAM | N         | 50019358  | 00000001   | 00000001            | 20170622  |        |        | 20170622 |         |               | 0010    | PP01   | 10001467  | BR12   | Produzir | EA      | 1000  | 99991231       |
    And I wait "/edm/mfg_rtng_itm" Async Queue complete

    When I import "/edm/mfg_rtng_itm_nde" by keyFields "srcSysCd,rtngTypCd,rtngGrpCd,rtngGrpCntrNbr,rtngSqncNum,rtngNdeNum,rtngNdeVrsnCntrNbr"
      | srcSysCd   | rtngTypCd | rtngGrpCd | rtngGrpCntrNbr | rtngSqncNum | rtngNdeNum | rtngNdeVrsnCntrNbr | ValidFromDate | chgNum | delInd | CreateDttm | chgDttm |
      | CONS_LATAM | N         | 50019350  | 01             | 000000      | 00000001   | 00000001           | 20150519      |        |        | 20150519   |         |
      | CONS_LATAM | N         | 50019351  | 01             | 000000      | 00000001   | 00000001           | 20150713      |        |        | 20150713   |         |
      | CONS_LATAM | N         | 50019352  | 01             | 000000      | 00000001   | 00000001           | 20150724      |        |        | 20150724   |         |
      | CONS_LATAM | N         | 50019353  | 01             | 000000      | 00000001   | 00000001           | 20170622      |        |        | 20170622   |         |
      | CONS_LATAM | N         | 50019354  | 01             | 000000      | 00000001   | 00000001           | 20150519      |        |        | 20150519   |         |
      | CONS_LATAM | N         | 50019355  | 01             | 000000      | 00000001   | 00000001           | 20150713      |        |        | 20150713   |         |
      | CONS_LATAM | N         | 50019356  | 01             | 000000      | 00000001   | 00000001           | 20150724      |        |        | 20150724   |         |
      | CONS_LATAM | N         | 50019357  | 01             | 000000      | 00000001   | 00000001           | 20170622      |        |        | 20170622   |         |
      | CONS_LATAM | N         | 50019358  | 01             | 000000      | 00000001   | 00000001           | 20170622      |        |        | 20170622   |         |
    And I wait "/edm/mfg_rtng_itm_nde" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmStepResourceProcess.xml" and execute file "jar/pangea-view.jar"
    And wait 5000 millisecond

    Then A file is found on sink application with name "GDMStepResource_process.tsv"
    Then I check file data for filename "GDMStepResource_process.tsv" by keyFields "stepResourceId"
#    Then I check region data "/omp/gdm_step_resource" by keyFields "stepResourceId"
      | stepResourceId                                     | active | activeOPRERP | activeSOPERP | machineId                | minQuantity | operationId      | quantity | resourceId               | stepResourceType |
      | CONS_LATAM_BR12/SAN-R+D6/SAN-W+D6/PRO/1647956/0010 | YES    | YES          | NO           | CONS_LATAM_BR12/SAN-W+D6 |             | PRO/1647956/0010 | 0        | CONS_LATAM_BR12/SAN-R+D6 | production       |
      | CONS_LATAM_BR12/SAN-R+D7/SAN-W+D7/PRO/1647957/0010 | YES    | YES          | NO           | CONS_LATAM_BR12/SAN-W+D7 | 76.1        | PRO/1647957/0010 | 0        | CONS_LATAM_BR12/SAN-R+D7 | production       |
      | /SAN-W+D5/PRO/1647955/0010                         | YES    | YES          | NO           | CONS_LATAM_BR12/SAN-W+D5 | 78.0        | PRO/1647955/0010 | 0        |                          | production       |

#    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
#      | functionalArea | interfaceID        | errorCode | sourceSystem | businessArea | key1       | key2         | key3 | key4 | key5 | errorValue                         |
#      | PP             | OMPGdmStepResource | T7        | omp          |              | CONS_LATAM | 000001647955 |      |      |      | Unable find the Secondary Resource |

  @Scenario2
  Scenario:delete Test Data
##    And I will remove the test file on sink application "GDMStepResource_process.tsv"
    And I delete the test data
    And I will remove all data with region "/omp/gdm_step_resource"
#    And I will remove all data with region "/plan/edm_failed_data"

        