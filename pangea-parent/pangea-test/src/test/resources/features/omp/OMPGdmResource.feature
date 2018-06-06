@pangea_test @AEAZ-4481
Feature: OMPGdmResource AEAZ-4481
   #Create view region /omp/gdm_resource/ when the raw main region is /edm/capy_hdr/
  @Scenario1
 Scenario: Test Rule J1,C1 is used correctly
    Given I import "/edm/capy_hdr" by keyFields "srcSysCd,capyNum"
      | capyDesc               | plntCd | srcSysCd    | capyNum  | capyCatCd |
      | Machine 1/Maquina 1/M1 | BR01   | CONS_LATAM  | 10000001 | 002       |
      | Machine 2/Maquina 2/M2 | BR02   | CONS_LATAM  | 10000002 | 002       |
      | Machine 3/Maquina 3/M3 | BR03   | PROTECT_ONE | 10000003 | 002       |
      # CAP_HDR-capCatCd = 002
      | Machine 4/Maquina 4/M4 | BR04   | CONS_LATAM  | 10000004 | 003       |
      | Machine 5/Maquina 5/M5 | BR04   | CONS_LATAM  | 10000004 |            |
      | Machine 5/Maquina 5/M5 | BR04   | PROTECT_TWO | 10000005 | 002       |
      | Machine 5/Maquina 5/M5 | BR04   | CONS_LATAM | 10000008  | 002       |


    And I wait "/edm/capy_hdr" Async Queue complete
    Given I import "/edm/wrk_ctr" by keyFields "srcSysCd,wrkCtrNum,wrkCtrTypeCd"
      | srcSysCd    | capyNum  | wrkCtrUsgCd | wrkCtrCd | wrkCtrTypeCd | wrkCtrNum |
      | CONS_LATAM  | 10000001 | 001         |           | A            | 10000178  |
      | PROTECT_ONE | 10000003 | 009         | CTLC1OXB | C            | 10000178  |
      | CONS_LATAM  | 10000004 | 009         | CTLC1OXB | D            | 10000178  |
      # WRK_CTR-wrkCtrUsgCd = "001" or "009"
      | CONS_LATAM  | 10000002 | 002         | CTLC1OXB | B            | 10000178  |
      # WRK_CTR-capyNum  =  CAPY_HDR-capyNum(rule J1)
      | CONS_LATAM  | 10000006 | 001         | CTLC1OXB | B            | 10000178  |
      # WRK_CTR-srcSysCd  =  CAPY_HDR-srcSysCd
      | CONS_LATAM  | 10000005 | 001         | CTLC1OXB | B            | 10000178  |

    And I wait "/edm/wrk_ctr" Async Queue completeB

    Given I import "/plan/cns_plan_parameter" by keyFields "attribute,dataObject,inclExcl,parameter,parameterValue,sourceSystem"
      | attribute   | sourceSystem | dataObject  | parameter    | parameterValue | inclExcl |
      | CONS_LATAM  | CONS_LATAM   | SEND_TO_OMP | MaterialType | ND             | E        |
      | PROTECT_ONE | PROTECT_ONE  | SEND_TO_OMP | MaterialType | ND             | E        |
      # cns_plan_parameter-dataObject = 'SEND_TO_OMP'
      | CONS_LATAM  | CONS_LATAM   | SEND_TO_EDM | MaterialType | ND             | E        |
      # WRK_CTR-srcSysCd = cns_plan_parameter-attribute
      | CONS_FATE   | CONS_LATAM   | SEND_TO_OMP | MaterialType | ND             | E        |
      # cns_plan_parameter-sourceSystem =WRK_CTR-srcSysCd
      | CONS_LATAM  | CONS_FATE    | SEND_TO_OMP | MaterialType | ND             | E        |
    And I wait "/plan/cns_plan_parameter" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmResource.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMResource.tsv"

    Then I check file data for filename "GDMResource.tsv" by keyFields "resourceId"
      | resourceId        | active | activeOPRERP | activeSOPERP | description | locationId       |
     # Concatenation CAPY_HDR-plntCd, "/", WRK_CTR-wrkCtrCd,"/",CAPY_HDR-capyCatCd(rule C1)
      | CONS_LATAM/BR01//002          | YES    | YES          | NO           | Machine 1   | CONS_LATAM_BR01  |
      | PROTECT_ONE/BR03/CTLC1OXB/002 | YES    | YES          | NO           | Machine 3   | PROTECT_ONE_BR03 |


    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    #And I compare the number of records between "/edm/capy_hdr" and "/omp/gdm_resource,/plan/edm_failed_data"
  Scenario: delete all test data
    And I delete the test data

    And I will remove all data with region "/omp/gdm_resource"

    And I will remove all data with region "/plan/edm_failed_data"

    And I will remove the test file on sink application "GDMResource.tsv"


  @Scenario2
  Scenario: Test Rule T4 is used correctly

    And I will remove the test file on sink application "GDMResource.tsv"
    Given I import "/edm/capy_hdr" by keyFields "srcSysCd,capyNum"
      | capyDesc               | plntCd | srcSysCd   | capyNum  | capyCatCd |
      | Machine 1/Maquina 1/M1 | BR01   | CONS_LATAM | 10000001 | 002       |
      | /Maquina 2/M2          | BR02   | CONS_LATAM | 10000002 | 002       |
      | /Maquina 3/M3          | LK03   | CONS_LATAM | 10000003 | 002       |
      |                          | BR04   | CONS_FATE  | 10000004 | 002       |

    And I wait "/edm/capy_hdr" Async Queue complete

    Given I import "/edm/wrk_ctr" by keyFields "srcSysCd,wrkCtrNum,wrkCtrTypeCd"
      | srcSysCd   | capyNum  | wrkCtrUsgCd | wrkCtrCd | wrkCtrTypeCd | wrkCtrNum |
      | CONS_LATAM | 10000001 | 001         | CTLC1OXA | A            | 10000178  |
      | CONS_LATAM | 10000002 | 001         | CTLC1OXB | B            | 10000178  |
      | CONS_LATAM | 10000003 | 009         | CTLC1OXC | C            | 10000178  |
      | CONS_FATE  | 10000004 | 009         | CTLC1OXD | D            | 10000178  |

    And I wait "/edm/wrk_ctr" Async Queue completeB

    Given I import "/plan/cns_plan_parameter" by keyFields "attribute,dataObject,inclExcl,parameter,parameterValue,sourceSystem"
      | attribute  | sourceSystem | dataObject  | parameter    | parameterValue | inclExcl |
      | CONS_LATAM | CONS_LATAM   | SEND_TO_OMP | MaterialType | ND             | E        |
      | CONS_FATE  | CONS_FATE    | SEND_TO_OMP | MaterialType | ND             | E        |
       # cns_plan_parameter-dataObject = 'SEND_TO_OMP'
      | CONS_LATAM | CONS_LATAM   | SEND_TO_EDM | MaterialType | ND             | E        |
      # WRK_CTR-srcSysCd = cns_plan_parameter-attribute
      | CONS_FATE  | CONS_LATAM   | SEND_TO_OMP | MaterialType | ND             | E        |
      # cns_plan_parameter-sourceSystem =WRK_CTR-srcSysCd
      | CONS_LATAM | CONS_FATE    | SEND_TO_OMP | MaterialType | ND             | E        |
    And I wait "/plan/cns_plan_parameter" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmResource.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMResource.tsv"

    Then I check file data for filename "GDMResource.tsv" by keyFields "resourceId"
    #  CAPY_HDR-capyDesc will have 3 values separated by "/"
      | resourceId        | active | activeOPRERP | activeSOPERP | description | locationId      |
    #  1. By default populate here first value
      | CONS_LATAM/BR01/CTLC1OXA/002 | YES    | YES          | NO           |Machine 1   | CONS_LATAM_BR01 |
    #  2. If first value blank check LocationId ( CAPY_HDR-plntCd )
      #  If LocationId is NOT starting with BR then populate here the 2nd value
      #  If LocationId is starting with BR then populate here the 3rd value
      | CONS_LATAM/BR02/CTLC1OXB/002 | YES    | YES          | NO           | M2          | CONS_LATAM_BR02 |
      | CONS_LATAM/LK03/CTLC1OXC/002 | YES    | YES          | NO           | Maquina 3   | CONS_LATAM_LK03 |
    #  3. If not found at all then send Blank.
      | CONS_FATE/BR04/CTLC1OXD/002 | YES    | YES          | NO           |             | CONS_FATE_BR04  |


    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    #And I compare the number of records between "/edm/capy_hdr" and "/omp/gdm_resource,/plan/edm_failed_data"
  Scenario: delete all test data
    And I delete the test data

    And I will remove all data with region "/omp/gdm_resource"

    And I will remove all data with region "/plan/edm_failed_data"

    And I will remove the test file on sink application "GDMResource.tsv"

  @Scenario3
  Scenario: Test Rule C2 is used correctly
    # If CAPY_HDR-plntCd is empty, reject the record.Critical Error: Plant Id not found
    And I will remove the test file on sink application "GDMResource.tsv"

    Given I import "/edm/capy_hdr" by keyFields "srcSysCd,capyNum"
      | capyDesc               | plntCd | srcSysCd   | capyNum  | capyCatCd |
      | //                     | BR01   | CONS_LATAM | 10000001 | 002       |
      | Machine 2/Maquina 2/M2 | BR02   | CONS_LATAM | 10000002 | 002       |
      | Machine 3/Maquina 3/M3 | BR03   | CONS_LATAM | 10000003 | 002       |
      | Machine 4/Maquina 4/M4 |        | CONS_LATAM | 10000004 | 002       |
    And I wait "/edm/capy_hdr" Async Queue complete

    Given I import "/edm/wrk_ctr" by keyFields "srcSysCd,wrkCtrNum,wrkCtrTypeCd"
      | srcSysCd   | capyNum  | wrkCtrUsgCd | wrkCtrCd | wrkCtrTypeCd | wrkCtrNum |
      | CONS_LATAM | 10000001 | 001         | CTLC1OXA | A            | 10000178  |
      | CONS_LATAM | 10000002 | 001         | CTLC1OXB | B            | 10000178  |
      | CONS_LATAM | 10000003 | 009         | CTLC1OXC | C            | 10000178  |
      | CONS_LATAM | 10000004 | 009         | CTLC1OXD | D            | 10000178  |

    And I wait "/edm/wrk_ctr" Async Queue completeB
    Given I import "/plan/cns_plan_parameter" by keyFields "attribute,dataObject,inclExcl,parameter,parameterValue,sourceSystem"
      | attribute  | sourceSystem | dataObject  | parameter    | parameterValue | inclExcl |
      | CONS_LATAM | CONS_LATAM   | SEND_TO_OMP | MaterialType | ND             | E        |
      # cns_plan_parameter-dataObject = 'SEND_TO_OMP'
      | CONS_LATAM | CONS_LATAM   | SEND_TO_EDM | MaterialType | ND             | E        |
      # WRK_CTR-srcSysCd = cns_plan_parameter-attribute
      | CONS_FATE  | CONS_LATAM   | SEND_TO_OMP | MaterialType | ND             | E        |
      # cns_plan_parameter-sourceSystem =WRK_CTR-srcSysCd
      | CONS_LATAM | CONS_FATE    | SEND_TO_OMP | MaterialType | ND             | E        |
    And I wait "/plan/cns_plan_parameter" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmResource.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMResource.tsv"
      # Concatenate CAPY_HDR-srcSysCd, "_", CAPY_HDR-plntCd.
    Then I check file data for filename "GDMResource.tsv" by keyFields "resourceId"
      | resourceId        | active | activeOPRERP | activeSOPERP | description | locationId      |
      | CONS_LATAM/BR01/CTLC1OXA/002 | YES    | YES          | NO             |              | CONS_LATAM_BR01 |
      | CONS_LATAM/BR02/CTLC1OXB/002 | YES    | YES          | NO             |  Machine 2   | CONS_LATAM_BR02 |
      | CONS_LATAM/BR03/CTLC1OXC/002 | YES    | YES          | NO             |  Machine 3   | CONS_LATAM_BR03 |


    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1       | key2     | key3 | key4 | key5 | errorValue         |
      | PP             | GDMResource | C2        | omp          |              | CONS_LATAM | 10000004 |      |      |      | Plant Id not found |

    And I compare the number of records between "/edm/capy_hdr" and "/omp/gdm_resource,/plan/edm_failed_data"

  Scenario: delete all test data
    And I delete the test data

    And I will remove all data with region "/omp/gdm_resource"

    And I will remove all data with region "/plan/edm_failed_data"

    And I will remove the test file on sink application "GDMResource.tsv"

  @Scenario4
  Scenario:  Test all rules
    And I will remove the test file on sink application "GDMResource.tsv"

    Given I import "/edm/capy_hdr" by keyFields "srcSysCd,capyNum"
      | capyDesc               | plntCd | srcSysCd     | capyNum  | capyCatCd |
      | Machine 1/Maquina 1/M1 | BR01   | CONS_LATAM   | 10000001 | 002       |
      |                        | BR02   | CONS_LATAM   | 10000002 | 002       |
      | /Maquina 7/M7          | BR07   | CONS_BBA     | 10000007 | 002       |
      | /Maquina 4/M4          | BR04   | CONS_LATAM   | 10000004 | 002       |
      | /Maquina 5/M5          | LK05   | CONS_LATAM   | 10000005 | 002       |

    And I wait "/edm/capy_hdr" Async Queue complete

    Given I import "/edm/wrk_ctr" by keyFields "srcSysCd,wrkCtrNum,wrkCtrTypeCd"
      | srcSysCd     | capyNum  | wrkCtrUsgCd | wrkCtrCd | wrkCtrTypeCd | wrkCtrNum |
      | CONS_LATAM   | 10000001 | 001         | CTLC1OXA | A            | 10000178  |
      | CONS_LATAM   | 10000002 | 001         | CTLC1OXB | B            | 10000178  |
      | CONS_BBA     | 10000007 | 001         | CTLC1OXC | C            | 10000178  |
      | CONS_LATAM   | 10000004 | 009         | CTLC1OXD | D            | 10000178  |
      | CONS_LATAM   | 10000005 | 009         | CTLC1OXE | E            | 10000178  |
      | CONS_LATAM   | 10000006 | 009         | CTLC1OXF | F            | 10000178  |

    And I wait "/edm/wrk_ctr" Async Queue completeB

    Given I import "/plan/cns_plan_parameter" by keyFields "attribute,dataObject,inclExcl,parameter,parameterValue,sourceSystem"
      | attribute  | sourceSystem | dataObject  | parameter    | parameterValue | inclExcl |
      | CONS_LATAM | CONS_LATAM   | SEND_TO_OMP | MaterialType | ND             | E        |
      | CONS_BBA   | CONS_BBA     | SEND_TO_OMP | MaterialType | ND             | E        |
    And I wait "/plan/cns_plan_parameter" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmResource.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMResource.tsv"

    Then I check file data for filename "GDMResource.tsv" by keyFields "resourceId"
      | resourceId        | active | activeOPRERP | activeSOPERP | description | locationId      |
      | CONS_LATAM/BR01/CTLC1OXA/002 | YES    | YES          | NO           | Machine 1   | CONS_LATAM_BR01 |
      | CONS_LATAM/BR02/CTLC1OXB/002 | YES    | YES          | NO           |             | CONS_LATAM_BR02 |
      | CONS_BBA/BR07/CTLC1OXC/002 | YES    | YES          | NO           | M7          | CONS_BBA_BR07   |
      | CONS_LATAM/BR04/CTLC1OXD/002 | YES    | YES          | NO           | M4          | CONS_LATAM_BR04 |
      | CONS_LATAM/LK05/CTLC1OXE/002 | YES    | YES          | NO           | Maquina 5   | CONS_LATAM_LK05 |


    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1       | key2     | key3 | key4 | key5 | errorValue         |
      #| PP             | GDMResource | C2        | omp          |              | CONS_LATAM | 10000006 |      |      |      | Plant Id not found |

    #And I compare the number of records between "/edm/capy_hdr" and "/omp/gdm_resource,/plan/edm_failed_data"

  Scenario: delete all test data

    And I delete the test data

    And I will remove all data with region "/omp/gdm_resource"

    And I will remove all data with region "/plan/edm_failed_data"
