@pangea_test @AEAZ-4243
Feature: EDMWrkCtr AEAZ-4243

  Scenario: Full Load curation

    Given I import "/project_one/crhd" by keyFields "objid,objty"
      | objty | objid    | begda | endda | arbpl | werks | verwe | lvorm | planv | stand | veran | vgwts | xsprr | xterm | rasch | steus | fort1 | fort2 | fort3 | kapid | ortgr | matyp | cplgr | fortn | prvbe | lgort_res | mixmat |
      | A     | 10000001 | begda | endda | arbpl | werks | verwe | lvorm | planv | stand | veran | vgwts | xsprr | xterm | rasch | steus | fort1 | fort2 | fort3 | kapid | ortgr | matyp | cplgr | fortn | prvbe | lgort_res | mixmat |
      | B     | 10000002 | begda | endda | arbpl | werks | verwe | lvorm | planv | stand | veran | vgwts | xsprr | xterm | rasch | steus | fort1 | fort2 | fort3 | kapid | ortgr | matyp | cplgr | fortn | prvbe | lgort_res | mixmat |
      | C     | 10000003 | begda | endda | arbpl | werks | verwe | lvorm | planv | stand | veran | vgwts | xsprr | xterm | rasch | steus | fort1 | fort2 | fort3 | kapid | ortgr | matyp | cplgr | fortn | prvbe | lgort_res | mixmat |
      | D     | 10000004 | begda | endda | arbpl | werks | verwe | lvorm | planv | stand | veran | vgwts | xsprr | xterm | rasch | steus | fort1 | fort2 | fort3 | kapid | ortgr | matyp | cplgr | fortn | prvbe | lgort_res | mixmat |
      | E     | 10000005 | begda | endda | arbpl | werks | verwe | lvorm | planv | stand | veran | vgwts | xsprr | xterm | rasch | steus | fort1 | fort2 | fort3 | kapid | ortgr | matyp | cplgr | fortn | prvbe | lgort_res | mixmat |
    And I wait "/project_one/crhd" Async Queue complete

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | sourceSystem |
      | project_one       | CONS_LATAM   |
      | [EMS]             | EMS          |
      | project_two       | CONS_LATAM   |
    And I wait "/edm/source_system_v1" Async Queue complete

    Given I import "/project_one/crtx" by keyFields "objid,spras,ktext"
      | objty | objid    | spras | ktext     |
      | A     | 10000001 | E     | Machine 1 |
      | A     | 10000001 | S     | Maquina 1 |
      | A     | 10000001 | P     | M1        |
      | B     | 10000002 | E     | Machine 2 |
      | B     | 10000002 | S     | Maquina 2 |
      | C     | 10000003 | P     | M3        |
      | D     | 10000004 | S     | Maquina 4 |
      | D     | 10000004 | P     | M4        |
      | E     | 10000005 | S     |           |
    And I wait "/project_one/crtx" Async Queue complete


    When I submit task with xml file "xml/edm/EDMWrkCtr.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/wrk_ctr" by keyFields "srcSysCd,wrkCtrTypeCd,wrkCtrNum"
      | srcSysCd   | wrkCtrTypeCd | wrkCtrNum | vldFromDt | vldToDt | wrkCtrCd | plntCd | wrkCtrCatCd | delInd | wrkCtrUsgCd | wrkCtrLocCd | respPrsnNum | wrkCtrActvCd | lockInd | schdlngInd | setupTypeCd | oprCd | setupFrmlCd | runFrmlCd | teardownFrmlCd | capyNum | locGrpCd | machTypeCd | plnrGrpCd | othFrmlCd | suplAreaCd | slocCd    | mixingInd | wrkCtrDesc             |
      | CONS_LATAM | A            | 10000001  | begda     | endda   | arbpl    | werks  | verwe       | lvorm  | planv       | stand       | veran       | vgwts        | xsprr   | xterm      | rasch       | steus | fort1       | fort2     | fort3          | kapid   | ortgr    | matyp      | cplgr     | fortn     | prvbe      | lgort_res | mixmat    | Machine 1/Maquina 1/M1 |
      | CONS_LATAM | B            | 10000002  | begda     | endda   | arbpl    | werks  | verwe       | lvorm  | planv       | stand       | veran       | vgwts        | xsprr   | xterm      | rasch       | steus | fort1       | fort2     | fort3          | kapid   | ortgr    | matyp      | cplgr     | fortn     | prvbe      | lgort_res | mixmat    | Machine 2/Maquina 2/   |
      | CONS_LATAM | C            | 10000003  | begda     | endda   | arbpl    | werks  | verwe       | lvorm  | planv       | stand       | veran       | vgwts        | xsprr   | xterm      | rasch       | steus | fort1       | fort2     | fort3          | kapid   | ortgr    | matyp      | cplgr     | fortn     | prvbe      | lgort_res | mixmat    | //M3                   |
      | CONS_LATAM | D            | 10000004  | begda     | endda   | arbpl    | werks  | verwe       | lvorm  | planv       | stand       | veran       | vgwts        | xsprr   | xterm      | rasch       | steus | fort1       | fort2     | fort3          | kapid   | ortgr    | matyp      | cplgr     | fortn     | prvbe      | lgort_res | mixmat    | /Maquina 4/M4          |
      | CONS_LATAM | E            | 10000005  | begda     | endda   | arbpl    | werks  | verwe       | lvorm  | planv       | stand       | veran       | vgwts        | xsprr   | xterm      | rasch       | steus | fort1       | fort2     | fort3          | kapid   | ortgr    | matyp      | cplgr     | fortn     | prvbe      | lgort_res | mixmat    | //                     |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/project_one/crhd" and "/edm/wrk_ctr,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/edm/wrk_ctr"

    And I will remove all data with region "/plan/edm_failed_data"

