@pangea_test @AEAZ-4480 @AEAZ-9085
Feature: OMPGdmBOM AEAZ-4480 AEAZ-9085

  @Scenario1
  Scenario: T1,J1
    And I will remove the test file on sink application "GDMBOMElement_process.tsv"

    Given I import "/edm/mfg_order" by keyFields "mfgOrdrNum"
      | sourceSysCd     | actRlseDt | ordrRtngNum | mfgOrdrNum   | plntCd | mfgOrdrSttsCd | rtngSqncNum |
#      Conditional "mfgOrdrSttsCd" does not conform to
      | PPTRANSACTIONAL | 20180703  | 0000000612  | 000001000612 | BR02   | I0012         | 0000000619  |
      | PPTRANSACTIONAL | 20180704  | 0000000613  | 000001000613 | BR02   | I0045         | 0000000619  |
      | PPTRANSACTIONAL | 20180705  | 0000000614  | 000001000614 | BR02   | I0046         | 0000000619  |
#     Conditional "actRlseDtc" does not conform to
      | PPTRANSACTIONAL | 20180711  | 0000000615  | 000001000615 | BR02   | 111           | 0000000619  |
      | PPTRANSACTIONAL | 20180702  | 0000000616  | 000001000616 | BR02   | 111           | 0000000619  |
#     MFG_ORDER-srcSysCd != MFG_ORDER_RTNG-srcSysCd
      | PPTRANSACTIONAL | 20180708  | 0000000617  | 000001000617 | BR02   | 111           | 0000000619  |
#      MFG_ORDER-ordrRtngNum != MFG_ORDER_RTNG-ordrRtngNum
      | PPTRANSACTIONAL | 20180709  | 0000000618  | 000001000618 | BR02   | 111           | 0000000619  |
      | LATAM           | 20180709  | 0000000619  | 000001000619 | BR02   | 111           | 0000000619  |
      | PPTRANSACTIONAL | 20180709  | 0000000620  | 000001000620 | BR02   | 111           | 0000000619  |

      | PPTRANSACTIONAL | 20180703  | 0000000621  | 000001000621 | BR02   | 111           | 0000000619  |
      | PPTRANSACTIONAL | 20180704  | 0000000622  | 000001000622 | BR02   | 111           | 0000000619  |
      | PPTRANSACTIONAL | 20180705  | 0000000623  | 000001000623 | BR02   | 111           | 0000000619  |
      | PPTRANSACTIONAL | 20180706  | 0000000624  | 000001000624 | BR02   | 111           | 0000000619  |
      | PPTRANSACTIONAL | 20180707  | 0000000625  | 000001000625 | BR02   | 111           | 0000000619  |
      | PPTRANSACTIONAL | 20180708  | 0000000626  | 000001000626 | BR02   | 111           | 0000000619  |
      | PPTRANSACTIONAL | 20180709  | 0000000627  | 000001000627 | BR02   | 111           | 0000000619  |
      | PPTRANSACTIONAL | 20180710 | 0000000628  | 000001000628 | BR02   | 111           | 0000000619  |
    And I wait "/edm/mfg_order" Async Queue complete

    Given I import "/edm/mfg_order_rtng" by keyFields "ordrRtngCtrNum,ordrRtngNum,srcSysCd"
      | ordrRtngCtrNum | ordrRtngNum | srcSysCd        | operNum |
      | 18041801       | 0000000612  | PPTRANSACTIONAL | 0010    |
      | 18041802       | 0000000613  | PPTRANSACTIONAL | 0010    |
      | 18041803       | 0000000614  | PPTRANSACTIONAL | 0010    |
      | 18041804       | 0000000615  | PPTRANSACTIONAL | 0010    |
      | 18041805       | 0000000616  | PPTRANSACTIONAL | 0010    |
      | 18041807       | 0000000617  | CONS            | 0010    |
#     MFG_ORDER_RTNG-srcSysCd != MFG_ORDER_SEQ-srcSysCd
      | 18041808       | 0000000619  | LATAM           | 0010    |
#      MFG_ORDER_RTNG-ordrRtngNum != MFG_ORDER_SEQ-ordrRtngNum
      | 18041809       | 0000000620  | PPTRANSACTIONAL | 0010    |
      | 180418010      | 0000000621  | PPTRANSACTIONAL | 0010    |
      | 180418011      | 0000000622  | PPTRANSACTIONAL | 0010    |
      | 180418012      | 0000000623  | PPTRANSACTIONAL | 0010    |
      | 180418013      | 0000000624  | PPTRANSACTIONAL | 0010    |
      | 180418014      | 0000000625  | PPTRANSACTIONAL | 0010    |
      | 180418015      | 0000000626  | PPTRANSACTIONAL | 0010    |
      | 180418016      | 0000000627  | PPTRANSACTIONAL | 0010    |
      | 180418017      | 0000000628  | PPTRANSACTIONAL | 0010    |

    And I wait "/edm/mfg_order_rtng" Async Queue complete

    Given I import "/edm/mfg_order_seq" by keyFields "ordrRtngCtrNum,ordrRtngNum"
      | ordrRtngCtrNum | ordrRtngNum | srcSysCd        |
      | 00000001       | 0000000612  | PPTRANSACTIONAL |
      | 00000002       | 0000000613  | PPTRANSACTIONAL |
      | 00000003       | 0000000614  | PPTRANSACTIONAL |
      | 00000004       | 0000000615  | PPTRANSACTIONAL |
      | 00000005       | 0000000616  | PPTRANSACTIONAL |
      | 00000007       | 0000000617  | PPTRANSACTIONAL |
      | 00000008       | 0000000619  | PPTRANSACTIONAL |
      | 00000009       | 0000000621  | PPTRANSACTIONAL |
      | 00000010       | 0000000622  | PPTRANSACTIONAL |
      | 00000011       | 0000000623  | PPTRANSACTIONAL |
      | 00000012       | 0000000624  | PPTRANSACTIONAL |
      | 00000013       | 0000000625  | PPTRANSACTIONAL |
      | 00000014       | 0000000626  | PPTRANSACTIONAL |
      | 00000015       | 0000000627  | PPTRANSACTIONAL |
      | 00000016       | 0000000628  | PPTRANSACTIONAL |

    And I wait "/edm/mfg_order_seq" Async Queue complete

    Given I import "/plan/cns_plan_parameter" by keyFields "attribute,dataObject,parameterValue,sourceSystem"
      | attribute       | sourceSystem    | dataObject  | parameterValue |
#     Conditional "attribute" does not conform to
      | DPRelevant      | PPTRANSACTIONAL | SEND_TO_OMP | 1              |
#     Conditional "sourceSystem" does not conform to
      | PPTRANSACTIONAL | CONS            | SEND_TO_OMP | 2              |
#      Conditional "dataObject" does not conform to
      | PPTRANSACTIONAL | PPTRANSACTIONAL | SEND_TO_EDG | 3              |

      | PPTRANSACTIONAL | PPTRANSACTIONAL | SEND_TO_OMP | 4              |
      | PPTRANSACTIONAL | PPTRANSACTIONAL | SEND_TO_OMP | 5              |
      | PPTRANSACTIONAL | PPTRANSACTIONAL | SEND_TO_OMP | 6              |
      | PPTRANSACTIONAL | PPTRANSACTIONAL | SEND_TO_OMP | 7              |
    And I wait "/plan/cns_plan_parameter" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmBom2.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMBOMElement_process.tsv"

    Then I check file data for filename "GDMBOMElement_process.tsv" by keyFields "bomId"
      | bomId                   | active | activeFCTERP | activeOPRERP | activeSOPERP | comments | endEff               | locationId      | startEff             |
      | PRO/PPTRANSACTIONAL/1000621/619/10 | YES    | YES          | YES          | NO           |          | 31/12/2298  23:59:59 | PPTRANSACTIONAL_BR02 | 01/01/1980  00:00:00 |
      | PRO/PPTRANSACTIONAL/1000622/619/10 | YES    | YES          | YES          | NO           |          | 31/12/2298  23:59:59 | PPTRANSACTIONAL_BR02 | 01/01/1980  00:00:00 |
      | PRO/PPTRANSACTIONAL/1000623/619/10 | YES    | YES          | YES          | NO           |          | 31/12/2298  23:59:59 | PPTRANSACTIONAL_BR02 | 01/01/1980  00:00:00 |
      | PRO/PPTRANSACTIONAL/1000624/619/10 | YES    | YES          | YES          | NO           |          | 31/12/2298  23:59:59 | PPTRANSACTIONAL_BR02 | 01/01/1980  00:00:00 |
      | PRO/PPTRANSACTIONAL/1000625/619/10 | YES    | YES          | YES          | NO           |          | 31/12/2298  23:59:59 | PPTRANSACTIONAL_BR02 | 01/01/1980  00:00:00 |
      | PRO/PPTRANSACTIONAL/1000626/619/10 | YES    | YES          | YES          | NO           |          | 31/12/2298  23:59:59 | PPTRANSACTIONAL_BR02 | 01/01/1980  00:00:00 |
      | PRO/PPTRANSACTIONAL/1000627/619/10 | YES    | YES          | YES          | NO           |          | 31/12/2298  23:59:59 | PPTRANSACTIONAL_BR02 | 01/01/1980  00:00:00 |
      | PRO/PPTRANSACTIONAL/1000628/619/10 | YES    | YES          | YES          | NO           |          | 31/12/2298  23:59:59 | PPTRANSACTIONAL_BR02 | 01/01/1980  00:00:00 |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |
#    And I compare the number of records between "/edm/mfg_order" and "/omp/gdm_bom,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/omp/gdm_bom"

    And I will remove all data with region "/plan/edm_failed_data"

