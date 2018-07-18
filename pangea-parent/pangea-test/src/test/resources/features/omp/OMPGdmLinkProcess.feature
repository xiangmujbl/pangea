@pangea_test @AEAZ-5835
Feature: OMPGdmLinkProcess AEAZ-5835

  @Scenario1
  Scenario: Check Full Rule

    Given I import "/edm/mfg_order" by keyFields "mfgOrdrNum,sourceSysCd"
      | sourceSysCd | mfgOrdrNum | mfgOrdrTypCd | plntCd | crtdDttm   | chgDttm    | delInd | strtDt     | strtTm   | endDt      | endTm    | schdStrtDt | schdStrtTm | schdEndDt  | schdEndTm | schRelDt   | actStrtDt  | actStrtTm | prdtnEndDt | cnfrmdEndDt | cnfrmdEndTm | planRlseDt | actRlseDt  | rsrvtnNum | rtngTypCd | rtngGrpCd | rtngGrpCntrNum | bomCatCd | bomNum | bomAltNum | mrpCntrllrCd | ordrRtngNum | prdSpvsrCd | cnfrmdYldQty | cnfrmdScrpQty | Object number | mfgOrdrSttsCd                                      |
      | CONS_LA     | 1683737    | PP04         | BR12   | 2018/01/31 | 2018/06/06 |        | 2018/06/13 | 1:38:06  | 2018/06/13 | 1:52:06  | 2018/06/13 | 0:00:00    | 2018/06/13 | 0:00:00   | 2018/06/13 |            | 0:00:00   |            |             | 0:00:00     |            | 2018/01/31 | 85872615  | N         | 50007785  | 1              | M        | 11644  | 1         | A30          | 683718      |            | 0            | 0             |               | I0002  I0004 I0007  I0016  I0369 I0249 I0420 I0028 |
      | CONS_LA     | 1691244    | PP04         | BR12   | 2018/04/11 | 2018/04/28 |        | 2018/04/13 | 16:53:16 | 2018/04/13 | 17:00:00 | 2018/04/13 | 0:00:00    | 2018/04/13 | 0:00:00   | 2018/04/13 |            | 0:00:00   |            |             | 0:00:00     |            | 2018/04/11 | 87178590  | N         | 50020191  | 1              | M        | 35440  | 1         | 71           | 691225      |            | 0            | 0             |               | I0045 I0004 I0016  I0369 I0420 I0028               |
      | CONS_LA     | 1692121    | PP04         | BR12   | 2018/04/16 | 2018/04/28 |        | 2018/04/16 | 8:34:26  | 2018/04/16 | 8:34:33  | 2018/04/16 | 0:00:00    | 2018/04/16 | 0:00:00   | 2018/04/16 |            | 0:00:00   |            |             | 0:00:00     |            | 2018/04/16 | 87261669  | N         | 50020403  | 1              | M        | 35721  | 1         | 72           | 692102      |            | 0            | 0             |               | I0002 I0016  I0369 I0012 I0420 I0028               |
      | CONS_LA     | 1694282    | PP04         | BR12   | 2018/05/08 | 2018/05/12 |        | 2018/05/08 | 9:00:17  | 2018/05/08 | 9:01:20  | 2018/05/08 | 0:00:00    | 2018/05/08 | 0:00:00   | 2018/05/08 |            | 0:00:00   |            |             | 0:00:00     |            | 2018/05/08 | 87693675  | N         | 50020243  | 1              | M        | 35498  | 1         | 52           | 694263      |            | 0            | 0             |               | I0002 I0016  I0369 I0420 I0028                     |
      | CONS_LA     | 1694798    | PP04         | BR12   | 2018/05/11 | 2018/05/19 |        | 2018/05/19 | 8:52:52  | 2018/05/19 | 8:52:52  | 2018/05/19 | 0:00:00    | 2018/05/19 | 0:00:00   | 2018/05/19 | 19.05.2018 | 8:52:52   |            |             | 0:00:00     |            | 2018/05/11 | 87760536  | N         | 50020181  | 1              | M        | 35430  | 1         | 51           | 694779      |            | 1            | 0             |               | I0045 I0010 I0016  I0369 I0321 I0420 I0028         |
      | CONS_LA     | 1695037    | PP04         | BR12   | 2018/05/16 | 2018/05/19 |        | 2018/05/17 | 23:59:21 | 2018/05/17 | 23:59:59 | 2018/05/17 | 0:00:00    | 2018/05/17 | 0:00:00   | 2018/05/17 |            | 0:00:00   |            |             | 0:00:00     |            | 2018/05/16 | 87856170  | N         | 50019343  | 2              | M        | 34151  | 1         | C00          | 695018      |            | 0            | 0             |               | I0045 I0004 I0016  I0369 I0028                     |
      | CONS_LA     | 1695038    | PP04         | BR12   | 2018/05/16 | 2018/05/17 |        | 2018/05/16 | 13:40:21 | 2018/05/16 | 13:40:59 | 2018/05/16 | 0:00:00    | 2018/05/16 | 0:00:00   | 2018/05/16 | 17.05.2018 | 9:56:12   |            |             | 0:00:00     |            | 2018/05/16 | 87856171  | N         | 50019343  | 2              | M        | 34151  | 1         | C00          | 695019      |            | 1            | 0             |               | I0045 I0004 I0010 I0016  I0369 I0321 I0420 I0028   |
      | CONS_LA     | 1695039    | PP04         | BR12   | 2018/05/16 | 2018/05/17 |        | 2018/05/16 | 13:46:58 | 2018/05/16 | 13:47:41 | 2018/05/16 | 0:00:00    | 2018/05/16 | 0:00:00   | 2018/05/16 | 17.05.2018 | 9:57:15   |            |             | 0:00:00     |            | 2018/05/16 | 87856172  | N         | 50019394  | 1              | M        | 34212  | 1         | C00          | 695020      |            | 1            | 0             |               | I0001 I0004 I0010 I0016  I0369 I0321 I0420 I0028   |
    And I wait "/edm/mfg_order" Async Queue complete

    Given I import "/edm/mfg_order_seq" by keyFields "ordrRtngCtrNum,ordrRtngNum,srcSysCd"
      | srcSysCd | ordrRtngNum | ordrRtngCtrNum | rtngTypCd | rntgGrpCd | rntgGrpCntrNbr | rtngSqncNum | rtngSqncVrsnCntrNbr | chgNum | rtngSqncCtgryCd | strtNdeNum | endNdeNum | fromLtSzQty | toLtSzQty | brnchOperNum | retrnOperNum |
      | CONS_LA  | 683718      | 1              | N         | 50007785  | 1              | 0           | 1                   |        | 0               |            |           | 0,000       | 99999999  |              |              |
      | CONS_LA  | 691225      | 1              | N         | 50020191  | 1              | 0           | 1                   |        | 0               |            |           | 0,000       | 99999999  |              |              |
      | CONS_LA  | 692102      | 1              | N         | 50020403  | 1              | 0           | 1                   |        | 0               |            |           | 0,000       | 99999999  |              |              |
      | CONS_LA  | 694263      | 1              | N         | 50020243  | 1              | 0           | 1                   |        | 0               |            |           | 0,000       | 99999999  |              |              |
      | CONS_LA  | 694263      | 2              | N         | 50020243  | 1              | 1           | 2                   |        | 2               | 2          | 2         | 0,000       | 99999999  | 1            | 2            |
      | CONS_LA  | 694263      | 3              | N         | 50020243  | 1              | 2           | 3                   |        | 2               | 2          | 2         | 0,000       | 99999999  | 1            | 2            |
      | CONS_LA  | 694779      | 1              | N         | 50020181  | 1              | 0           | 1                   |        | 0               |            |           | 0,000       | 99999999  |              |              |
      | CONS_LA  | 695018      | 3              | N         | 50019343  | 2              | 0           | 2                   |        | 0               |            |           | 0,000       | 99999999  |              |              |
      | CONS_LA  | 695019      | 2              | N         | 50019343  | 2              | 0           | 2                   |        | 0               |            |           | 0,000       | 99999999  |              |              |
      | CONS_LA  | 695020      | 1              | N         | 50019394  | 1              | 0           | 1                   |        | 0               |            |           | 0,000       | 99999999  |              |              |
    And I wait "/edm/mfg_order_seq" Async Queue complete

    Given I import "/edm/mfg_order_rtng" by keyFields "ordrRtngCtrNum,ordrRtngNum,srcSysCd"
      | srcSysCd | ordrRtngNum | ordrRtngCtrNum | rtngSqncNum | operNum | operCd | wrkCntrId | operDesc                   |
      | CONS_LA  | 683718      | 1              | 0           | 0010    | PP01   | 10001390  | PESAR                      |
      | CONS_LA  | 683718      | 2              | 0           | 0020    | PP01   | 10001395  | FABRICAR                   |
      | CONS_LA  | 691225      | 1              | 0           | 0010    | PP01   | 10003573  | Cortar/Encartuchar/Embalar |
      | CONS_LA  | 692102      | 1              | 0           | 0010    | PP01   | 10003524  | PRODUÇÃO                   |
      | CONS_LA  | 694263      | 1              | 0           | 0010    | PP01   | 10001430  | PRODUZIR                   |
      | CONS_LA  | 694263      | 2              | 1           | 0010    | PP01   | 10003708  | alternativa EDM3           |
      | CONS_LA  | 694263      | 3              | 2           | 0010    | PP01   | 10003822  | ALTERNATIVA PARA EDM2      |
      | CONS_LA  | 694779      | 1              | 0           | 0010    | PP01   | 10003573  | PRODUZIR                   |
      | CONS_LA  | 695018      | 3              | 0           | 0010    | PP01   | 10001458  | PRODUÇÃO                   |
      | CONS_LA  | 695019      | 2              | 0           | 0010    | PP01   | 10001458  | PRODUÇÃO                   |
      | CONS_LA  | 695020      | 1              | 0           | 0010    | PP01   | 10001565  | PRODUÇÃO                   |
    And I wait "/edm/mfg_order_rtng" Async Queue complete

    Given I import "/plan/cns_plan_parameter" by keyFields "attribute,dataObject,sourceSystem,parameter"
      | attribute | dataObject  | sourceSystem | parameter | parameterValue |
      | CONS_LA   | SEND_TO_OMP | CONS_LA      | Division  | LA             |
      | CONS_LA   | SEND_TO_GDM | CONS_LA      | Division  | LA             |
    And I wait "/plan/cns_plan_parameter" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmLinkProcess.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "OMPGdmLinkProcess.tsv"
    Then I check file data for filename "OMPGdmLinkProcess.tsv" by keyFields "linkId"
#    Then I check region data "/omp/gdm_link" by keyFields "linkId"
      | linkId                  | active | activeOPRERP | activeOPROMP | activeSOPERP | activeSOPOMP | comments | endEff              | firstOccupied | operationId      | planLevelId | prevOperationId  | processId   | secondOccupied | startEff            |
      | PRO/1695039//0010/0     | YES    | YES          | YES          | YES          | NO           |          | 2998/12/31 23:59:59 |               | PRO/1695039/0010 | *           |                  | PRO/1695039 |                | 1980/01/01 00:00:00 |
      | PRO/1683737//0010/0     | YES    | YES          | YES          | YES          | NO           |          | 2998/12/31 23:59:59 |               | PRO/1683737/0010 | *           |                  | PRO/1683737 |                | 1980/01/01 00:00:00 |
      | PRO/1694282//0010/0     | YES    | YES          | YES          | YES          | NO           |          | 2998/12/31 23:59:59 |               | PRO/1694282/0010 | *           |                  | PRO/1694282 |                | 1980/01/01 00:00:00 |

    And I delete the test data

    And I will remove all data with region "/edm/gdm_link"
#
#