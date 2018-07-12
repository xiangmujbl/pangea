@pangea_test @AEAZ-7469
Feature: E.2.1.1 EDMRouting-MATL_MFG_RTNG - Curation

  As a Data user,
  I want EDG to curate Routing raw data from ECC LATAM
  so that there is an Enterprise Data Model that contains Routing-level supply chain data consistent with Enterprise standards, and ready for consumption by reports and/or cross-functional applications.
  Scenario1

  Scenario: check rule F1

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | sourceSystem |
      | project_one       | CONS_LATAM   |
      | EMS               | EMS          |
      | project_two       | project_two  |

    And I wait "/edm/source_system_v1" Async Queue complete
#
#      #PLNNR/PLNTY/PLNAL/ZKRIZ
    And I import "/project_one/mapl" by keyFields "mandt,matnr,werks,plnty,plnnr,plnal,zkriz,zaehl,datuv,aennr,loekz,andat,aedat"
      | mandt | matnr              | werks | plnty | plnnr    | plnal | zkriz   | zaehl    | datuv    | aennr        | loekz | andat    | aedat    |
      | 120   | 000000000009300140 | BR12  | 2     | 50006440 | 01    | 0000001 | 00000001 | 20120125 |              |       | 20120125 | 20120125 |
      | 120   | 000000000009300140 | BR12  | 5     | 50006440 | 01    | 0000001 | 00000001 | 20120125 |              |       | 20120125 | 20120125 |
      | 120   | 000000000009300140 | BR12  | N     | 50006440 | 01    | 0000001 | 00000001 | 20120125 |              |       | 20120125 | 20120125 |
      | 120   | 000000000009300140 | BR12  | N     | 50006440 | 02    | 0000002 | 00000002 | 20150930 |              |       | 20150930 | 20150930 |
      | 120   | 000000000009300140 | BR12  | N     | 50006440 | 02    | 0000002 | 00000002 | 20180705 | 500000009479 | X     | 20180705 | 20180705 |
      | 120   | 000000000009300140 | BR12  | N     | 50006440 | 03    | 0000003 | 00000003 | 20120125 |              | X     | 20120125 | 20120125 |

    And I wait "/project_one/mapl" Async Queue complete

    When I submit task with xml file "xml/edm/EDMMatlMfgRtng.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/matl_mfg_rtng" by keyFields "srcSysCd,matlNum,plntCd,rtngTypCd,rntgGrpCd,rntgGrpCntrNbr,rntgAddtnlCntrNbr,matlRtngVrsnCntrNbr"
      | srcSysCd   | matlNum            | plntCd | rtngTypCd | rntgGrpCd | rntgGrpCntrNbr | rntgAddtnlCntrNbr | matlRtngVrsnCntrNbr | valFromDt | chgNum       | delInd | crtDttm  | chgDttm  | matlRtngValid_To |
      | CONS_LATAM | 000000000009300140 | BR12   | 2         | 50006440  | 01             | 0000001           | 00000001            | 20120125  |              |        | 20120125 | 20120125 | 99991231         |
      | CONS_LATAM | 000000000009300140 | BR12   | N         | 50006440  | 01             | 0000001           | 00000001            | 20120125  |              |        | 20120125 | 20120125 | 99991231         |
      | CONS_LATAM | 000000000009300140 | BR12   | N         | 50006440  | 02             | 0000002           | 00000002            | 20150930  | 500000009479 |        | 20150930 | 20150930 | 20180704         |

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/edm/source_system_v1"

    And I will remove all data with region "/project_one/mapl"

    And I will remove all data with region "/edm/matl_mfg_rtng"

  Scenario: Full Load curation

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | sourceSystem |
      | project_one       | CONS_LATAM   |
      | EMS               | EMS          |
      | project_two       | project_two  |

    And I wait "/edm/source_system_v1" Async Queue complete
#
#      #PLNNR/PLNTY/PLNAL/ZKRIZ
    And I import "/project_one/mapl" by keyFields "mandt,matnr,werks,plnty,plnnr,plnal,zkriz,zaehl,datuv,aennr,loekz,andat,aedat"
      | mandt | matnr              | werks | plnty | plnnr    | plnal | zkriz   | zaehl    | datuv    | aennr        | loekz | andat    | aedat    |
      | 120   | 000000000009300140 | BR12  | N     | 50006440 | 01    | 0000001 | 00000001 | 20120125 |              |       | 20120125 | 20120125 |
      | 120   | 000000000009300140 | BR12  | 5     | 50006440 | 01    | 0000001 | 00000001 | 20120125 |              |       | 20120125 | 20120125 |
      | 120   | 000000000009300140 | BR12  | N     | 50006440 | 02    | 0000002 | 00000002 | 20150930 |              |       | 20150930 | 20150930 |
      | 120   | 000000000009300140 | BR12  | N     | 50006440 | 02    | 0000002 | 00000002 | 20180705 | 500000009479 | X     | 20180705 | 20180705 |
      | 120   | 000000000009300140 | BR12  | N     | 50006440 | 03    | 0000003 | 00000003 | 20120125 |              | X     | 20120125 | 20120125 |

    And I wait "/project_one/mapl" Async Queue complete

    When I submit task with xml file "xml/edm/EDMMatlMfgRtng.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/matl_mfg_rtng" by keyFields "srcSysCd,matlNum,plntCd,rtngTypCd,rntgGrpCd,rntgGrpCntrNbr,rntgAddtnlCntrNbr,matlRtngVrsnCntrNbr"
      | srcSysCd   | matlNum            | plntCd | rtngTypCd | rntgGrpCd | rntgGrpCntrNbr | rntgAddtnlCntrNbr | matlRtngVrsnCntrNbr | valFromDt | chgNum       | delInd | crtDttm  | chgDttm  | matlRtngValid_To |
      | CONS_LATAM | 000000000009300140 | BR12   | N         | 50006440  | 01             | 0000001           | 00000001            | 20120125  |              |        | 20120125 | 20120125 | 99991231         |
      | CONS_LATAM | 000000000009300140 | BR12   | N         | 50006440  | 02             | 0000002           | 00000002            | 20150930  | 500000009479 |        | 20150930 | 20150930 | 20180704         |

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/edm/source_system_v1"

    And I will remove all data with region "/project_one/mapl"

    #And I will remove all data with region "/edm/matl_mfg_rtng"
