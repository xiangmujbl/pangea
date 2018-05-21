@pangea_test @AEAZ-4240
Feature: EDMWrkCtrCapy AEAZ-4240

  Scenario: Full Load curation

    Given I import "/project_one/crca" by keyFields "objty,objid,canum"
      | objty | objid    | canum | begda    | endda    | aedatKapa | kapid    | fork1 | fork2 | fork3 | forkn  | vert1 | vert2 | vert3 | vertn |
      | A     | 10000180 | 0512  | 20000823 | 99991231 | 20000823  | 10000196 | 1     | 2     | 3     | SAP008 | 1     | 2     | 3     | n     |
      | A     | 20000180 | 0512  | 20000823 | 99991231 | 20000823  | 10000196 | 1     | 2     | 3     | SAP008 | 1     | 2     | 3     | n     |
    And I wait "/project_one/crca" Async Queue complete

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | sourceSystem |
      | project_one       | CONS_LATAM   |
      | EMS               | EMS          |
    And I wait "/edm/source_system_v1" Async Queue complete

    When I submit task with xml file "xml/edm/EDMWrkCtrCapy.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/wrk_ctr_capy" by keyFields "srcSysCd,wrkCtrTypeCd,wrkCtrNum,capyAllcNbr"
      | srcSysCd   | wrkCtrTypeCd | wrkCtrNum | capyAllcNbr | vldFromDt | vldToDt  | chgDttm  | capyNum  | setupFrmlCd | runFrmlCd | teardownFrmlCd | othFrmlCd | setupDstnCd | runDstnCd | teardownDstnCd | othDstnCd |
      | CONS_LATAM | A            | 10000180  | 0512        | 20000823  | 99991231 | 20000823 | 10000196 | 1           | 2         | 3              | SAP008    | 1           | 2         | 3              | n         |
      | CONS_LATAM | A            | 20000180  | 0512        | 20000823  | 99991231 | 20000823 | 10000196 | 1           | 2         | 3              | SAP008    | 1           | 2         | 3              | n         |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/project_one/crca" and "/edm/wrk_ctr_capy,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/edm/wrk_ctr_capy"

    And I will remove all data with region "/plan/edm_failed_data"

