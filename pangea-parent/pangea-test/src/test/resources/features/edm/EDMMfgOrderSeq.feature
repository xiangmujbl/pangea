@pangea_test @AEAZ-4247
Feature: EDMMfgOrderSeq AEAZ-4247

  Scenario: Full Load curation

    Given I import "/project_one/affl" by keyFields "aufpl,aplzl"
      | aufpl      | aplzl      | plnty | plnnr | plnal | plnfl | zaehl | aennr | flgat | bknt1 | bknt2 | losvn | losbs | bschl1 | bschl2 |
      | 0000000001 | 0000000001 | 2     | 0078  | 44456 | 444   | 657   | 2345  | 8978  | 453   | 4523  | 764   | 24324 | 4262   | 4277   |
      | 0000000002 | 0000000002 | 2     | 0078  | 44456 | 444   | 657   | 2345  | 8978  | 453   | 4523  | 764   | 24324 | 4262   | 4277   |
      | 0000000003 | 0000000003 | 2     | 0078  | 44456 | 444   | 657   | 2345  | 8978  | 453   | 4523  | 764   | 24324 | 4262   | 4277   |

    And I wait "/project_one/affl" Async Queue complete

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | sourceSystem |
      | project_one       | CONS_LATAM   |
      | EMS               | EMS          |
    And I wait "/edm/source_system_v1" Async Queue complete


    When I submit task with xml file "xml/edm/EDMMfgOrderSeq.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/mfg_order_seq" by keyFields "srcSysCd,ordrRtngNum,ordrRtngCtrNum"
      | srcSysCd   | ordrRtngNum | ordrRtngCtrNum | rtngTypCd | rntgGrpCd | rntgGrpCntrNbr | rtngSqncNum | rtngSqncVrsnCntrNbr | chgNum | rtngSqncCtgryCd | strtNdeNum | endNdeNum | fromLtSzQty | toLtSzQty | brnchOperNum | retrnOperNum |
      | CONS_LATAM | 0000000001  | 0000000001     | 2         | 0078      | 44456          | 444         | 657                 | 2345   | 8978            | 453        | 4523      | 764         | 24324     | 4262         | 4277         |
      | CONS_LATAM | 0000000002  | 0000000002     | 2         | 0078      | 44456          | 444         | 657                 | 2345   | 8978            | 453        | 4523      | 764         | 24324     | 4262         | 4277         |
      | CONS_LATAM | 0000000003  | 0000000003     | 2         | 0078      | 44456          | 444         | 657                 | 2345   | 8978            | 453        | 4523      | 764         | 24324     | 4262         | 4277         |


#    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
#      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |
#
#    And I compare the number of records between "/project_one/affl" and "/edm/mfg_order_seq,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/edm/mfg_order_seq"

    And I will remove all data with region "/plan/edm_failed_data"

