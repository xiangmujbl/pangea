@pangea_test @AEAZ-3277
Feature: EDMMfgRtngSeq AEAZ-3277

  Scenario: Full Load curation

    Given I import "/project_one/plfl" by keyFields "plnty,plnnr,plnal,plnfl,zaehl"
      | plnty | plnnr | plnal | plnfl | zaehl | datuv     | aennr       | loekz | andat | aedat | flgat | ltxa1 | losvn | losbs | bschl1 | bschl2 | bknt1 | bknt2 |
      | N     | 997   | 1     | 1     | 1     | 2018/4/30 | 50000000003 |       | andat | aedat | 0     | ltxa1 | losvn | losbs | bschl1 | bschl2 |  1     |   1    |
      | N     | 997   | 1     | 2     | 2     | 2018/8/25 | 50000000004 |       | andat | aedat | 2     | ltxa1 | losvn | losbs | bschl1 | bschl2 | 1     | 1     |
      | N     | 997   | 1     | 2     | 3     | 2018/8/31 | 50000000005 | X     | andat | aedat | 2     | ltxa1 | losvn | losbs | bschl1 | bschl2 | 1     | 1     |
      | N     | 997   | 1     | 3     | 4     | 2018/8/31 | 50000000006 |       | andat | aedat | 2     | ltxa1 | losvn | losbs | bschl1 | bschl2 | 1     | 1     |
    And I wait "/project_one/plfl" Async Queue complete

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | localSourceSystemName | sourceSystem | sourceSystemName   |
      | project_one        | Project One            | CONS_LATAM   | Consumer Latam Ent |
      | ems                | EMS                      | EMS           | ems                |
    And I wait "/edm/source_system_v1" Async Queue complete


    When I submit task with xml file "xml/edm/EDMMfgRtngSeq.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/mfg_rtng_seq" by keyFields "srcSysCd,rtngTypCd,rtngGrpCd,rtngGrpCntrNbr,rtngSqncNum,rtngSqncVrsnCntrNbr"
      | srcSysCd   | rtngTypCd | rtngGrpCd | rtngGrpCntrNbr | rtngSqncNum | rtngSqncVrsnCntrNbr | valFromDt | chgNum      | delInd | crtDttm | chgDttm | seqCategory | seqDesc | fromLtSzQty | toLtSzQty | seqRelKeyBranch | seqRelKeyReturn | seqStartNode | seqEndNode | seqValidToDate |
      | CONS_LATAM | N         | 997        | 1               | 1             | 1                     | 2018/4/30 | 50000000003 |        | andat   | aedat    | 0            | ltxa1    | losvn       | losbs     | bschl1          | bschl2            | 1               | 1            | 9999/12/31 |
      | CONS_LATAM | N         | 997        | 1               | 2             | 2                     | 2018/8/25 | 50000000005 |        | andat   | aedat    | 2            | ltxa1    | losvn       | losbs     | bschl1          | bschl2            | 1              | 1           | 2018/8/31 |
      | CONS_LATAM | N         | 997        | 1               | 3             | 4                     | 2018/8/31 | 50000000006 |        | andat   | aedat    | 2            | ltxa1    | losvn       | losbs     | bschl1          | bschl2            | 1              | 1           | 9999/12/31 |
#    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
#      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    #And I compare the number of records between "/project_one/plfl" and "/edm/mfg_rtng_seq,/plan/edm_failed_data"

    And I delete the test data

   # And I will remove all data with region "/edm/mfg_rtng_seq"

    And I will remove all data with region "/plan/edm_failed_data"

