@pangea_test @AEAZ-4242
Feature: EDMWrkCtrCc AEAZ-4242

  Scenario: Full Load curation

    Given I import "/project_one/crco" by keyFields "objid,endda,lanum,laset,objty,mandt"
      | mandt | objty | objid    | laset | endda    | lanum | begda    | aedatKost | kokrs | kostl      | lstar |
      | 120   | A     | 10003041 | 1     | 20001231 | 0001  | 20000101 | 20050107  | ACJJ  | 0000001019 | MANUT |
      | 120   | A     | 10001525 | 1     | 20001231 | 0002  | 20000101 | 20020520  | ACJJ  | 0000008018 | MOBRA |
    And I wait "/project_one/crco" Async Queue complete

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | localSourceSystemName | sourceSystem | sourceSystemName   |
      | project_one       | Project One           | CONS_LATAM   | Consumer Latam Ent |
      | ems               | EMS                   | EMS          | ems                |
    And I wait "/edm/source_system_v1" Async Queue complete


    When I submit task with xml file "xml/edm/EDMWrkCtrCc.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/wrk_ctr_cc" by keyFields "srcSysCd,wrkCtrTypeCd,wrkCtrNum,actvTypeSetCd,vldToDt,actvTypeNbr"
      | srcSysCd   | wrkCtrTypeCd | wrkCtrNum | actvTypeSetCd | vldToDt  | actvTypeNbr | vldFromDt | chgDttm  | cntlAreaCd | ccCd       | actvCd |
      | CONS_LATAM | A            | 10003041  | 1             | 20001231 | 0001        | 20000101  | 20050107 | ACJJ       | 0000001019 | MANUT  |
      | CONS_LATAM | A            | 10001525  | 1             | 20001231 | 0002        | 20000101  | 20020520 | ACJJ       | 0000008018 | MOBRA  |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/project_one/crco" and "/edm/wrk_ctr_cc,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/edm/wrk_ctr_cc"

    And I will remove all data with region "/plan/edm_failed_data"

