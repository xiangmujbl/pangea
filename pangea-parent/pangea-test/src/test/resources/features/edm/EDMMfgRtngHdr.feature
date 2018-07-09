@pangea_test @AEAZ-7852
Feature: EDMMfgRtngHdr AEAZ-7852

  @Scenario1
  Scenario: full load data

    When I import "/project_one/plko" by keyFields "mandt,plnal,plnnr,plnty,zaehl"
      | mandt | plnty | plnnr    | plnal | zaehl    | datuv    | aennr | loekz | andat    | aedat    | werks | verwe | statu | plnme | losvn | losbs | vagrp | ktext                                   | profidnetz |
      | 120   | N     | 50000010 | 01    | 00000001 | 20000906 |       |       | 20000906 | 20000906 | BR02  | 1     | 4     | CRT   | 0     | 1000  |       | B'AID*EXTRA TRANSP.A GRANEL CX 10675UN  |            |
      | 120   | N     | 50000011 | 01    | 00000001 | 20000906 |       |       | 20000906 | 20000906 | BR02  | 1     | 4     | CRT   | 0     | 1000  |       | BAND-AID*COR PELE A GRANEL CX C/10675UN |            |
      | 120   | N     | 50000011 | 01    | 00000002 | 20120906 |       |       | 20120906 | 20120906 | BR02  | 1     | 4     | CRT   | 0     | 1000  |       | BAND-AID*DUPLA PROTECAO GRANEL CX 10675 |            |
      | 120   | N     | 50000011 | 01    | 00000003 | 20161231 |       |       | 20161231 | 20161231 | BR02  | 1     | 4     | CRT   | 0     | 1000  |       | CURAT.BAND-AID*DISNEY 10675UN A GRANEL  |            |
      | 120   | Q     | 50000012 | 01    | 00000001 | 20000906 |       | X     | 20000906 | 20000906 | BR02  | 1     | 4     | CRT   | 0     | 1000  |       | CURAT.BAND-AID*TARZAN A GRANEL 10675UN  |            |
    And I wait "/project_one/plko" Async Queue complete

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | sourceSystem |
      | project_one       | CONS_LATAM   |
      | EMS               | EMS          |

    And I wait "/edm/source_system_v1" Async Queue complete

    When I submit task with xml file "xml/edm/EDMMfgRtngHdr.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/mfg_rtng_hdr" by keyFields "rtngGrpCd,rtngGrpCntrNbr,rtngTypCd,rtngVrsnCntrNbr,srcSysCd"
      | srcSysCd   | rtngTypCd | rtngGrpCd | rtngGrpCntrNbr | rtngVrsnCntrNbr | valFromDt | chgNum | delInd | crtDttm  | chgDttm  | plntCd | rtngUsgCd | rtngSttsCd | rtngUomCd | fromLtSzQty | toLtSzQty | rtngPlnnrGrpCd | rtngDesc                                | rtngPrflCd | rtgVld_ToDt |
      | CONS_LATAM | N         | 50000010  | 01             | 00000001        | 20000906  |        |        | 20000906 | 20000906 | BR02   | 1         | 4          | CRT       | 0           | 1000      |                | B'AID*EXTRA TRANSP.A GRANEL CX 10675UN  |            | 99991231    |
      | CONS_LATAM | N         | 50000011  | 01             | 00000001        | 20000906  |        |        | 20000906 | 20000906 | BR02   | 1         | 4          | CRT       | 0           | 1000      |                | BAND-AID*COR PELE A GRANEL CX C/10675UN |            | 20120905    |
      | CONS_LATAM | N         | 50000011  | 01             | 00000002        | 20120906  |        |        | 20120906 | 20120906 | BR02   | 1         | 4          | CRT       | 0           | 1000      |                | BAND-AID*DUPLA PROTECAO GRANEL CX 10675 |            | 20161230    |
      | CONS_LATAM | N         | 50000011  | 01             | 00000003        | 20161231  |        |        | 20161231 | 20161231 | BR02   | 1         | 4          | CRT       | 0           | 1000      |                | CURAT.BAND-AID*DISNEY 10675UN A GRANEL  |            | 99991231    |


    And I delete the test data

    And I will remove all data with region "/edm/mfg_rtng_hdr"

