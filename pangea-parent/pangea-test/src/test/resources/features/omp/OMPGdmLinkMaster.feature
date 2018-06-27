@pangea_test @AEAZ-5835
Feature: OMPGdmLinkMaster AEAZ-5835

  @Scenario1
  Scenario: Check Full Rule
#    Given I import "/edm/matl_prod_versn" by keyFields "matlNum,plntCd,srcSysCd,prdntVrsnNum"
#      | srcSysCd | matlNum | plntCd | prdntVrsnNum | valFromDt | valToDt  | altBomNum | bomUsgCd | rtngTypCd | rtngGrpCd | rtngGrpCntrNum | prcrmntTypCd | spPrcrmntTypCd | cstLtSzQty | mfgLine Cd | prdVrsnDesc                        | dstrbtnKeyCd | frmLtSzQty | toLtSzQty | sLocCd | chckInd | chckDt | lckInd | orgBtchInd | crtDttm | chgDttm |
#      | LA_CONS  | 127993  | BR12   | V001         | 20060123  | 99991231 | 1         | 1        | N         | 50007785  | 1              |              |                |            |            | Grupo Roteiro                      |              | 0          | 0         |        | 1       | 38740  |        |            |         |         |
#      | LA_CONS  | 69342   | BR12   | V001         | 20170817  | 99991231 | 1         | 1        | N         | 50019394  | 1              |              |                |            | SAN-SL03   | Grupo de Roteiro 01 - SACO + FLEX1 |              | 0          | 0         |        | 1       | 42964  |        |            |         |         |
#      | LA_CONS  | 209303  | BR12   | V001         | 20160413  | 99991231 | 1         | 1        | N         | 50020243  | 1              |              |                |            |            | Grupo de Roteiro 01                |              | 0          | 0         |        | 1       | 42473  |        |            |         |         |
#    And I wait "/edm/matl_prod_versn" Async Queue complete
#
#    Given I import "/edm/matl_mfg_rtng" by keyFields "matlNum,matlRtngVrsnCntrNbr,plntCd,rntgAddtnlCntrNbr,rntgGrpCd,rntgGrpCntrNbr,rtngTypCd,srcSysCd"
#      | srcSysCd | matlNum | plntCd | rtngTypCd | rntgGrpCd | rntgGrpCntrNbr | rntgAddtnlCntrNbr | matlRtngVrsnCntrNbr | valFromDt  | chgNum | delInd | crtDttm    | chgDttm | matlRtngValid_To |
#      | LA_CONS  | 127993  | BR12   | N         | 50007785  | 1              | 1                 | 1                   | 2006/01/23 |        |        | 2006/01/23 |         | 9999/12/31       |
#      | LA_CONS  | 69342   | BR12   | N         | 50019394  | 1              | 1                 | 1                   | 2015/06/10 |        |        | 2015/06/10 |         | 9999/12/31       |
#      | LA_CONS  | 209303  | BR12   | N         | 50020243  | 1              | 1                 | 1                   | 2016/04/13 |        |        | 2016/04/13 |         | 9999/12/31       |
#    And I wait "/edm/matl_mfg_rtng" Async Queue complete
#
#    Given I import "/edm/mfg_rtng_seq" by keyFields "rtngGrpCd,rtngGrpCntrNbr,rtngSqncNum,rtngSqncVrsnCntrNbr,rtngTypCd,srcSysCd"
#      | srcSysCd | rtngTypCd | rtngGrpCd | rtngGrpCntrNbr | rtngSqncNum | rtngSqncVrsnCntrNbr | valFromDt  | chgNum | delInd | crtDttm    | chgDttm | seqCategory | seqDesc                       | fromLtSzQty | toLtSzQty | seqRelKeyBranch | seqRelKeyReturn | seqStartNode | seqEndNode | seqValidToDate | rtngSqncCtgryCd |
#      | LA_CONS  | N         | 50007785  | 1              | 0           | 1                   | 2015/06/10 |        |        | 2015/06/10 |         | 0           | TOU-PESA / TOU-R05            | 0           | 99999999  |                 |                 |              |            | 9999/12/31     | 0               |
#      | LA_CONS  | N         | 50019394  | 1              | 0           | 1                   | 2006/01/23 |        |        | 2006/01/23 |         | 0           | SAN-SL03                      | 0           | 99999999  |                 |                 |              |            | 9999/12/31     | 0               |
#      | LA_CONS  | N         | 50020243  | 1              | 0           | 1                   | 2016/04/13 |        |        | 2016/04/13 |         | 0           | BA-UN04 ou BA-EDM3 ou BA-EDM2 | 0           | 99999999  |                 |                 |              |            | 9999/12/31     | 0               |
#      | LA_CONS  | N         | 50020243  | 1              | 1           | 2                   | 2016/04/13 |        |        | 2016/04/13 |         | 2           | alternativa EDM3              | 0           | 99999999  | 1               | 2               | 1            | 1          | 9999/12/31     | 2               |
#      | LA_CONS  | N         | 50020243  | 1              | 2           | 3                   | 2016/04/13 |        |        | 2016/04/13 |         | 2           | BA-EDM2                       | 0           | 99999999  | 1               | 2               | 1            | 1          | 9999/12/31     | 2               |
#    And I wait "/edm/mfg_rtng_seq" Async Queue complete
#
#    Given I import "/edm/mfg_rtng_itm_nde" by keyFields "srcSysCd,rtngTypCd,rtngGrpCd,rtngGrpCntrNbr,rtngSqncNum,rtngNdeNum,rtngNdeVrsnCntrNbr"
#      | srcSysCd | rtngTypCd | rtngGrpCd | rtngGrpCntrNbr | rtngSqncNum | rtngNdeNum | rtngNdeVrsnCntrNbr | Valid-From Date | chgNum | delInd | Create Dttm | chgDttm    |
#      | LA_CONS  | N         | 50007785  | 1              | 0           | 1          | 1                  | 2006/01/23      |        |        | 2006/01/23  | 2006/01/23 |
#      | LA_CONS  | N         | 50007785  | 1              | 0           | 2          | 2                  | 2006/01/23      |        |        | 2006/01/23  | 2006/01/23 |
#      | LA_CONS  | N         | 50019394  | 1              | 0           | 1          | 1                  | 2015/06/10      |        |        | 2015/06/10  | 2015/06/10 |
#      | LA_CONS  | N         | 50020243  | 1              | 0           | 1          | 1                  | 2016/04/13      |        |        | 2016/04/13  | 2016/04/13 |
#      | LA_CONS  | N         | 50020243  | 1              | 1           | 2          | 2                  | 2016/04/13      |        |        | 2016/04/13  | 2016/04/13 |
#      | LA_CONS  | N         | 50020243  | 1              | 2           | 3          | 3                  | 2016/04/13      |        |        | 2016/04/13  | 2016/04/13 |
#    And I wait "/edm/mfg_rtng_itm_nde" Async Queue complete
#
#    Given I import "/edm/mfg_rtng_itm" by keyFields "rtngGrpCd,rtngItmNum,rtngItmVersnCntrNbr,rtngTypCd,srcSysCd"
#      | srcSysCd | rtngTypCd | rtngGrpCd | rtngItmNum | rtngItmVersnCntrNbr | valFromDt | chgNum | delInd | crtDttm    | chgDttm    | rtngSupNdeNum | operNum | operCd | wrkCntrCd | plntCd | operDesc              | operUom | bsQty | act1Cd | act1UomCd | act1Qty | act2Cd | act2UomCd | act2Qty | act3Cd | act3UomCd | act3Qty | act4Cd | act4UomCd | act4Qty | act5Cd | act5UomCd | act5Qty | act6Cd | act6UomCd | act6Qty | operDurtnQty | operDurtnUomCd | minOperDurtnQty | minOperDurtnUomCd | PhsInd | rtgItemEndDate | rtngSqncNum |
#      | LA_CONS  | N         | 50007785  | 1          | 1                   | 20060123  |        |        | 2006/01/23 | 2011/04/14 |               | 0010    | PP01   | 10001390  | BR12   | PESAR                 | L       | 1000  | MOBRA  | H         | 1       | HORHOM | H         | 1       | HORMAQ | H         | 1       | DEPR01 | H         | 1       | OHIND  | TS        | 0       |        |           | 0       | 0            |                | 0               |                   |        | 9999/12/31     | 0           |
#      | LA_CONS  | N         | 50007785  | 2          | 2                   | 20060123  |        |        | 2006/01/23 | 2011/06/28 |               | 0020    | PP01   | 10001395  | BR12   | FABRICAR              | L       | 1000  | MOBRA  | H         | 2       | HORHOM | H         | 2       | HORMAQ | H         | 2       | DEPR01 | H         | 2       | OHIND  | TS        | 0       |        |           | 0       | 0            |                | 0               |                   |        | 9999/12/31     | 0           |
#      | LA_CONS  | N         | 50019394  | 1          | 1                   | 20150610  |        |        | 2015/06/10 | 2017/10/04 |               | 0010    | PP01   | 10001565  | BR12   | Produzir              | EA      | 1000  | MOBRA  | H         | 1       | HORHOM | H         | 1       | HORMAQ | H         | 0       | DEPR01 | H         | 0       | OHIND  | TS        | 8       |        |           | 0       | 0            |                | 0               |                   |        | 9999/12/31     | 0           |
#      | LA_CONS  | N         | 50019394  | 2          | 2                   | 20150713  |        |        | 2015/07/13 | 2017/10/04 |               | 0010    | PP01   | 10001565  | BR12   | Produzir              | EA      | 1000  | MOBRA  | H         | 1       | HORHOM | H         | 1       | HORMAQ | H         | 0       | DEPR01 | H         | 0       | OHIND  | TS        | 8       |        |           | 0       | 0            |                | 0               |                   |        | 9999/12/31     | 0           |
#      | LA_CONS  | N         | 50019394  | 3          | 3                   | 20150713  |        |        | 2015/07/13 | 2015/07/13 |               | 0010    | PP01   | 10001460  | BR12   | Produzir              | EA      | 1000  | MOBRA  | H         | 1       | HORHOM | H         | 1       | HORMAQ | H         | 0       | DEPR01 | H         | 0       | OHIND  | TS        | 8       |        |           | 0       | 0            |                | 0               |                   |        | 9999/12/31     | 0           |
#      | LA_CONS  | N         | 50019394  | 4          | 4                   | 20150814  |        |        | 2015/08/14 | 2017/06/22 |               | 0010    | PP01   | 10006094  | BR12   | Produzir              | EA      | 1000  | MOBRA  | H         | 1       | HORHOM | H         | 1       | HORMAQ | H         | 0       | DEPR01 | H         | 0       | OHIND  | TS        | 8       |        |           | 0       | 0            |                | 0               |                   |        | 9999/12/31     | 0           |
#      | LA_CONS  | N         | 50019394  | 5          | 5                   | 20150814  |        |        | 2015/08/14 | 2015/08/14 |               | 0010    | PP01   | 10006094  | BR12   | Produzir              | EA      | 1000  | MOBRA  | H         | 1       | HORHOM | H         | 1       | HORMAQ | H         | 0       | DEPR01 | H         | 0       | OHIND  | TS        | 8       |        |           | 0       | 0            |                | 0               |                   |        | 9999/12/31     | 0           |
#      | LA_CONS  | N         | 50020243  | 1          | 1                   | 20160413  |        |        | 2016/04/13 | 2017/06/30 |               | 0010    | PP01   | 10001430  | BR12   | PRODUZIR              | TS      | 1000  | MOBRA  | H         | 8       | HORHOM | H         | 8       | HORMAQ | H         | 8       | DEPR01 | H         | 8       | OHIND  | TS        | 8       |        | MIN       | 0       | 0            |                | 0               |                   |        | 9999/12/31     | 0           |
#      | LA_CONS  | N         | 50020243  | 2          | 2                   | 20160413  |        |        | 2016/04/13 | 2016/04/13 |               | 0010    | PP01   | 10003708  | BR12   | alternativa EDM3      | TS      | 1000  | MOBRA  | H         | 9       | HORHOM | H         | 9       | HORMAQ | H         | 9       | DEPR01 | H         | 10      | OHIND  | TS        | 1000    |        | MIN       | 1       | 0            |                | 0               |                   |        | 9999/12/31     | 1           |
#      | LA_CONS  | N         | 50020243  | 3          | 3                   | 20160413  |        |        | 2016/04/13 | 2016/04/13 |               | 0010    | PP01   | 10003822  | BR12   | ALTERNATIVA PARA EDM2 | TS      | 1000  | MOBRA  | H         | 10      | HORHOM | H         | 10      | HORMAQ | H         | 10      | DEPR01 | H         | 10      | OHIND  | TS        | 0       |        | MIN       | 0       | 0            |                | 0               |                   |        | 9999/12/31     | 2           |
#    And I wait "/edm/mfg_rtng_itm" Async Queue complete
#
#    Given I import "/plan/cns_plan_parameter" by keyFields "attribute,dataObject,sourceSystem,parameter"
#      | attribute | dataObject  | sourceSystem | parameter | parameterValue |
#      | LA_CONS   | SEND_TO_OMP | LA_CONS      | Division  | LA             |
#      | LA_CONS   | SEND_TO_GDM | LA_CONS      | Division  | LA             |
#    And I wait "/plan/cns_plan_parameter" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmLinkMaster.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/omp/gdm_link" by keyFields "linkId"
      | linkId                       | active | activeOPRERP | activeOPROMP | activeSOPERP | activeSOPOMP | comments | endEff              | firstOccupied | operationId           | planLevelId | prevOperationId       | processId        | secondOccupied | startEff            |
      | V001/127993/BR12//0010/0     | YES    | YES          | YES          | YES          | NO           |          | 2998/12/31 23:59:59 |               | V001/127993/BR12/0010 | *           |                       | V001/127993/BR12 |                | 2006/01/23 00:00:00 |
      | V001/127993/BR12/0010/0020/0 | YES    | YES          | YES          | YES          | NO           |          | 2998/12/31 23:59:59 |               | V001/127993/BR12/0020 | *           | V001/127993/BR12/0010 | V001/127993/BR12 |                | 2006/01/23 00:00:00 |
      | V001/69342/BR12//0010/0      | YES    | YES          | YES          | YES          | NO           |          | 2998/12/31 23:59:59 |               | V001/69342/BR12/0010  | *           |                       | V001/69342/BR12  |                | 2017/08/17 00:00:00 |
      | V001/209303/BR12//0010/0     | YES    | YES          | YES          | YES          | NO           |          | 2998/12/31 23:59:59 |               | V001/209303/BR12/0010 | *           |                       | V001/209303/BR12 |                | 2016/04/13 00:00:00 |


    And I delete the test data

    And I will remove all data with region "/omp/gdm_link"
    
        