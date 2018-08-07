@pangea_test @AEAZ-6665
Feature:EDMMfgRtgParm AEAZ-6665

  @Scenario1
  Scenario:Full Load curation
    Given I import "/edm/source_system_v1" by keyFields "sourceSystem,localSourceSystem"
      | sourceSystem  | localSourceSystem  |
      | sourceSystem1 | localSourceSystem1 |
      | sourceSystem2 | localSourceSystem2 |
      | CONS_LATAM    | project_one        |

    And I wait "/edm/source_system_v1" Async Queue complete
    Given I import "/project_one/plfv" by keyFields  "plnty,plnnr,plnkn,plnph"
      | plnty | plnnr    | plnkn    | plnph    | plnft | mkmzl    | zaehl    | datuv    | loekz | aennr | andat    | aedat    | atinn      | atwrt |
      | N     | 50019343 | 00000001 | 00000001 |       | 00000002 | 00000002 | 20150519 |       |       | 20071226 | 20170630 | EFICIENCIA | 80.1  |
      | N     | 50019343 | 00000001 | 00000002 |       | 00000008 | 00000008 | 20150519 |       |       | 20071226 | 20170630 | EFICIENCIA | 80.1  |
      | N     | 50019343 | 00000001 | 00000003 |       | 00000014 | 00000014 | 20150519 |       |       | 20071226 | 20170630 | EFICIENCIA | 80.1  |
      | N     | 50019343 | 00000001 | 00000004 |       | 00000017 | 00000017 | 20150519 |       |       | 20071226 | 20170630 | EFICIENCIA | 80.1  |
      | N     | 50019343 | 00000002 | 00000006 |       | 00000023 | 00000023 | 20150713 |       |       | 20130412 | 20171004 | EFICIENCIA | 78.0  |
      | N     | 50019343 | 00000002 | 00000007 |       | 00000029 | 00000029 | 20150713 |       |       | 20130412 | 20171004 | EFICIENCIA | 78.0  |
      | N     | 50019343 | 00000002 | 00000008 |       | 00000035 | 00000035 | 20150713 |       |       | 20130412 | 20171004 | EFICIENCIA | 78.0  |
      | N     | 50019343 | 00000002 | 00000009 |       | 00000038 | 00000038 | 20150713 |       |       | 20130412 | 20171004 | EFICIENCIA | 78.0  |
      | N     | 50019343 | 00000003 | 00000011 |       | 00000044 | 00000044 | 20150713 |       |       | 20130412 | 20170622 | EFICIENCIA | 76.1  |
      | N     | 50019343 | 00000003 | 00000012 |       | 00000050 | 00000050 | 20150713 |       |       | 20130412 | 20170622 | EFICIENCIA | 76.1  |
      | N     | 50019343 | 00000003 | 00000013 |       | 00000056 | 00000056 | 20150713 |       |       | 20130412 | 20170622 | EFICIENCIA | 76.1  |
      | N     | 50019343 | 00000003 | 00000014 |       | 00000059 | 00000059 | 20150713 |       |       | 20130412 | 20170622 | EFICIENCIA | 76.1  |
      | N     | 50019343 | 00000004 | 00000016 |       | 00000065 | 00000065 | 20150713 | X     |       | 20150527 | 20170622 | EFICIENCIA | 70.0  |
      | N     | 50019343 | 00000004 | 00000017 |       | 00000071 | 00000071 | 20150713 | X     |       | 20150527 | 20170622 | EFICIENCIA | 70.0  |
      | N     | 50019343 | 00000004 | 00000018 |       | 00000077 | 00000077 | 20150713 | X     |       | 20150527 | 20170622 | EFICIENCIA | 70.0  |
      | N     | 50019343 | 00000004 | 00000019 |       | 00000080 | 00000080 | 20150713 | X     |       | 20150527 | 20170622 | EFICIENCIA | 70.0  |

    And I wait "/project_one/plfv" Async Queue complete

    Given I import "/project_one/plph" by keyFields "plnty,plnnr,plnkn,plnph"
      | mandt | plnty | plnnr    | plnkn    | plnph    | zaehl    | datuv    | topnr |
      | 120   | N     | 50019343 | 00000001 | 00000001 | 00000001 | 20150519 | 10    |
      | 120   | N     | 50019343 | 00000001 | 00000002 | 00000002 | 20150519 | 20    |
      | 120   | N     | 50019343 | 00000001 | 00000003 | 00000003 | 20150519 | 30    |
      | 120   | N     | 50019343 | 00000001 | 00000004 | 00000004 | 20150519 | 40    |
      | 120   | N     | 50019343 | 00000001 | 00000005 | 00000005 | 20150519 | 50    |
      | 120   | N     | 50019343 | 00000002 | 00000006 | 00000006 | 20150713 | 10    |
      | 120   | N     | 50019343 | 00000002 | 00000007 | 00000007 | 20150713 | 20    |
      | 120   | N     | 50019343 | 00000002 | 00000008 | 00000008 | 20150713 | 30    |
      | 120   | N     | 50019343 | 00000002 | 00000009 | 00000009 | 20150713 | 40    |
      | 120   | N     | 50019343 | 00000002 | 00000010 | 00000010 | 20150713 | 50    |
      | 120   | N     | 50019343 | 00000003 | 00000011 | 00000011 | 20150713 | 10    |
      | 120   | N     | 50019343 | 00000003 | 00000012 | 00000012 | 20150713 | 20    |
      | 120   | N     | 50019343 | 00000003 | 00000013 | 00000013 | 20150713 | 30    |
      | 120   | N     | 50019343 | 00000003 | 00000014 | 00000014 | 20150713 | 40    |
      | 120   | N     | 50019343 | 00000003 | 00000015 | 00000015 | 20150713 | 50    |
      | 120   | N     | 50019343 | 00000004 | 00000016 | 00000016 | 20150713 | 10    |
      | 120   | N     | 50019343 | 00000004 | 00000017 | 00000017 | 20150713 | 20    |
      | 120   | N     | 50019343 | 00000004 | 00000018 | 00000018 | 20150713 | 30    |
      | 120   | N     | 50019343 | 00000004 | 00000019 | 00000019 | 20150713 | 40    |
      | 120   | N     | 50019343 | 00000004 | 00000020 | 00000020 | 20150713 | 50    |

    And I wait "/project_one/plph" Async Queue complete

    When I submit task with xml file "xml/edm/EDMMfgRtgParm.xml" and execute file "jar/pangea-view.jar"
    Then I check region data "/edm/mfg_rtg_parm" by keyFields "srcSysCd,rtgTypeCd,rtgGrpCd,rtgNodeNum,intrnlSubCalcNum,intrnlPrcsInstrNum,intrnlPrcsInstrCharValNum,mfgParmVersCntrNbr"
      | srcSysCd   | rtgTypeCd | rtgGrpCd | rtgNodeNum | intrnlSubCalcNum | intrnlPrcsInstrNum | intrnlPrcsInstrCharValNum | mfgParmVersCntrNbr | vldFromDt | delInd | chgNum | crtDttm  | chgDttm  | charCd     | charVal |
      | CONS_LATAM | N         | 50019343 | 00000001   | 00000001         |                    | 00000002                  | 00000002           | 20150519  |        |        | 20071226 | 20170630 | EFICIENCIA | 80.1    |
      | CONS_LATAM | N         | 50019343 | 00000002   | 00000006         |                    | 00000023                  | 00000023           | 20150713  |        |        | 20130412 | 20171004 | EFICIENCIA | 78.0    |
      | CONS_LATAM | N         | 50019343 | 00000003   | 00000011         |                    | 00000044                  | 00000044           | 20150713  |        |        | 20130412 | 20170622 | EFICIENCIA | 76.1    |

