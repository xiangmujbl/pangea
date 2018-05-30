@pangea_test @AEAZ-4480
Feature: OMPGdmBOM AEAZ-4480

  @Scenario1
  Scenario: T1,J1
#  cns_plan_parameter-sourceSystem =MFG_ORDER-srcSysCd and cns_plan_parameter-dataObject = 'SEND_TO_OMP'
#  MFG_ORDER-actRlseDt Between current date and current date - 1
#  MFG_ORDER-ordrRtngNum = MFG_ORDER_RTNG-ordrRtngNum
#  MFG_ORDER_RTNG-ordrRtngNum = MFG_ORDER_SEQ-ordrRtngNum
    And I will remove the test file on sink application "GDMBOM.tsv"

    Given I import "/edm/mfg_order" by keyFields "mfgOrdrNum"
      | sourceSysCd   | actRlseDt | ordrRtngNum | mfgOrdrNum   | plntCd |
      #    MFG_ORDER-actRlseDt Between current date and current date - 1
      | CONS_LATAM | 201805229  | 0000000613  | 000001000612 | BR02   |
      #    MFG_ORDER-actRlseDt out of current date and current date - 1
      | CONS_LATAM | 20180523  | 0000000614  | 000001000613 | BR02   |
    And I wait "/edm/mfg_order" Async Queue complete

    Given I import "/edm/mfg_order_rtng" by keyFields "ordrRtngCtrNum,ordrRtngNum,srcSysCd"
#    MFG_ORDER-ordrRtngNum = MFG_ORDER_RTNG-ordrRtngNum
      | ordrRtngCtrNum | ordrRtngNum | srcSysCd   | operNum |
      | 18041801       | 0000000613  | CONS_LATAM | 0010    |
      | 18041801       | 0000000614  | CONS_LATAM | 0010    |
#    MFG_ORDER-ordrRtngNum != MFG_ORDER_RTNG-ordrRtngNu && MFG_ORDER_RTNG-ordrRtngNum != MFG_ORDER_SEQ-ordrRtngNum
      | 18041801       | 0000000615  | CONS_LATAM | 0010    |
    And I wait "/edm/mfg_order_rtng" Async Queue complete

    Given I import "/edm/mfg_order_seq" by keyFields "ordrRtngCtrNum,ordrRtngNum"
#   MFG_ORDER_RTNG-ordrRtngNum = MFG_ORDER_SEQ-ordrRtngNum
      | ordrRtngCtrNum | ordrRtngNum |
      | 00000001       | 0000000613  |
      | 00000001       | 0000000614  |
    And I wait "/edm/mfg_order_seq" Async Queue complete

    Given I import "/plan/cns_plan_parameter" by keyFields "attribute,dataObject,parameterValue,sourceSystem"
      | attribute  | sourceSystem | dataObject               | parameterValue |
      # cns_plan_parameter-sourceSystem =MFG_ORDER-srcSysCd and cns_plan_parameter-dataObject = 'SEND_TO_OMP'
      | DPRelevant | CONS_LATAM   | cns_material_plan_status | ND             |
      | CONS_LATAM | CONS_LATAM   | SEND_TO_OMP              | LA             |
      | CONS_LATAM | CONS         | SEND_TO_OMP              | ND             |
    And I wait "/plan/cns_plan_parameter" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmBom2.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMBOM.tsv"

    Then I check file data for filename "GDMBOM.tsv" by keyFields "bomId"
      | bomId                               | active | activeFCTERP | activeOPRERP | activeSOPERP | comments | endEff               | locationId      | startEff             |
      | PRO/LA_000001000612/0000000613/0010 | YES    | YES          | YES          | NO           |          | 31/12/2298  23:59:59 | CONS_LATAM_BR02 | 01/01/1980  00:00:00 |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |
#    And I compare the number of records between "/edm/mfg_order" and "/omp/gdm_bom,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/omp/gdm_bom"

    And I will remove all data with region "/plan/edm_failed_data"

  @Scenario2
  Scenario: Full Load curation

    And I will remove the test file on sink application "GDMBOM.tsv"

    Given I import "/edm/mfg_order" by keyFields "mfgOrdrNum"
      | srcSysCd   | actRlseDt | ordrRtngNum | mfgOrdrNum   | plntCd |
      | CONS_LATAM | 20180528  | 0000000613  | 000001000612 | BR02   |
      | CONS_LATAM | 20180528  | 0000000653  | 000001000652 | BR02   |
      | CONS_LATAM | 20180528  | 0000000781  | 000001000780 | BR02   |
    And I wait "/edm/mfg_order" Async Queue complete

    Given I import "/edm/mfg_order_rtng" by keyFields "ordrRtngCtrNum,ordrRtngNum,srcSysCd"
      | ordrRtngCtrNum | ordrRtngNum | srcSysCd   | operNum |
      | 18041801       | 0000000613  | CONS_LATAM | 0010    |
      | 18041802       | 0000000653  | CONS_LATAM | 0010    |
      | 18041803       | 0000000781  | CONS_LATAM | 0010    |
    And I wait "/edm/mfg_order_rtng" Async Queue complete

    Given I import "/edm/mfg_order_seq" by keyFields "ordrRtngCtrNum,ordrRtngNum"
      | ordrRtngCtrNum | ordrRtngNum |
      | 00000001       | 0000000613  |
      | 00000004       | 0000000781  |
      | 00000005       | 0000000653  |
    And I wait "/edm/mfg_order_seq" Async Queue complete

    Given I import "/plan/cns_plan_parameter" by keyFields "attribute,dataObject,parameterValue,sourceSystem"
      | attribute  | sourceSystem | dataObject               | parameterValue |
      | CONS_LATAM | CONS_LATAM   | SEND_TO_OMP              | LA             |
    And I wait "/plan/cns_plan_parameter" Async Queue complete


    When I submit task with xml file "xml/omp/OMPGdmBom2.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMBOM.tsv"

    Then I check file data for filename "GDMBOM.tsv" by keyFields "bomId"
      | bomId                               | active | activeFCTERP | activeOPRERP | activeSOPERP | comments | endEff               | locationId      | startEff             |
      | PRO/LA_000001000612/0000000613/0010 | YES    | YES          | YES          | NO           |          | 31/12/2298  23:59:59 | CONS_LATAM_BR02 | 01/01/1980  00:00:00 |
      | PRO/LA_000001000652/0000000653/0010 | YES    | YES          | YES          | NO           |          | 31/12/2298  23:59:59 | CONS_LATAM_BR02 | 01/01/1980  00:00:00 |
      | PRO/LA_000001000780/0000000781/0010 | YES    | YES          | YES          | NO           |          | 31/12/2298  23:59:59 | CONS_LATAM_BR02 | 01/01/1980  00:00:00 |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |
#    And I compare the number of records between "/edm/mfg_order" and "/omp/gdm_bom,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/omp/gdm_bom"

    And I will remove all data with region "/plan/edm_failed_data"



