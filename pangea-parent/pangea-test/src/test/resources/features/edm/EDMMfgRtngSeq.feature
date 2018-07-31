@pangea_test @AEAZ-7854
Feature: EDMMfgRtngSeq AEAZ-7854

  Scenario: Full Load curation

    Given I import "/project_one/plfl" by keyFields "plnty,plnnr,plnal,plnfl,zaehl"
      | plnty | plnnr    | plnal | plnfl  | zaehl    | datuv    | aennr      | loekz | andat      | aedat      | flgat | ltxa1    | losvn | losbs | bschl1 | bschl2 | bknt1    | bknt2    |
      | N     | 50000006 | 01    | 000000 | 00000001 | 20000906 |            |       | 06.09.2000 | 06.09.2000 | 0     | AD-GANZ  | 0     | 1000  |        |        |          |          |
      | N     | 50000006 | 01    | 000001 | 00000002 | 20000906 |            |       | 06.09.2000 | 06.09.2000 | 2     | AD-GANZ2 | 0     | 1000  | 1      | 2      | 00000001 | 00000001 |
      | N     | 50000006 | 01    | 000001 | 00000003 | 20120906 | 5000000999 | X     | 06.09.2000 | 06.09.2000 | 2     | AD-GANZ3 | 0     | 1000  | 1      | 2      | 00000001 | 00000001 |
      | 3     | 50000006 | 01    | 000002 | 00000005 | 20000906 |            |       | 06.09.2000 | 01.10.2000 | 2     | AD-GANZ5 | 0     | 1000  | 1      | 2      | 00000001 | 00000001 |

    And I wait "/project_one/plfl" Async Queue complete

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | localSourceSystemName | sourceSystem | sourceSystemName   |
      | project_one       | Project One           | CONS_LATAM   | Consumer Latam Ent |
      | ems               | EMS                   | EMS          | ems                |
    And I wait "/edm/source_system_v1" Async Queue complete


    When I submit task with xml file "xml/edm/EDMMfgRtngSeq.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/mfg_rtng_seq" by keyFields "srcSysCd,rtngTypCd,rtngGrpCd,rtngGrpCntrNbr,rtngSqncNum,rtngSqncVrsnCntrNbr"
      | srcSysCd   | rtngTypCd | rtngGrpCd | rtngGrpCntrNbr | rtngSqncNum | rtngSqncVrsnCntrNbr | valFromDt | chgNum     | delInd | crtDttm  | chgDttm  | seqCategory | seqDesc  | fromLtSzQty | toLtSzQty | seqRelKeyBranch | seqRelKeyReturn | seqStartNode | seqEndNode | seqValidToDate |
      | CONS_LATAM | N         | 50000006  | 01             | 000000      | 00000001               | 20000906  |            |        | 06.09.2000 | 06.09.2000 | 0           | AD-GANZ  | 0           | 1000      |                 |                 |              |            |  99991231            |
      | CONS_LATAM | N         | 50000006  | 01             | 000001      | 00000002               | 20000906  | 5000000999 |        | 06.09.2000| 06.09.2000 | 2           | AD-GANZ2 | 0           | 1000      | 1               | 2               | 00000001     | 00000001   |  20120905            |

#    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
#      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |
#
#    And I compare the number of records between "/project_one/plfl" and "/edm/mfg_rtng_seq,/plan/edm_failed_data"

    And I delete the test data

    #And I will remove all data with region "/edm/mfg_rtng_seq"

    #And I will remove all data with region "/plan/edm_failed_data"

