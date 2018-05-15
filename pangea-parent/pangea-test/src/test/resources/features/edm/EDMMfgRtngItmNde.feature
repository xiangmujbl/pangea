@pangea_test @AEAZ-3273
Feature: EDMMfgRtngItmNde AEAZ-3273

  Scenario: Full Load curation

    Given I import "/project_one/plas" by keyFields "plnty,plnnr,plnal,plnfl,plnkn,zaehl"
      | plnty | plnnr       | plnal | plnfl  | plnkn    | zaehl    | datuv    | aennr | loekz | andat    | aedat    |
      | Q     | 00000001_T1 | 01    | 000000 | 00000001 | 00000001 | 20170504 | 111   | 111   | 20180504 | 20090911 |
      | 2     | 00000002_F1 | 01    | 000000 | 00000002 | 00000002 | 20170504 | 111   | 111   | 20180504 | 20090911 |
      | N     | 00000003_F1 | 01    | 000000 | 00000002 | 00000002 | 20170504 | 111   | 111   | 20180504 | 20090911 |

    And I wait "/project_one/plas" Async Queue complete

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | localSourceSystemName | sourceSystem | sourceSystemName   |
      | project_one       | Project One           | CONS_LATAM   | Consumer Latam Ent |
      | ems               | EMS                   | EMS          | ems                |

    And I wait "/edm/source_system_v1" Async Queue complete

    When I submit task with xml file "xml/edm/EDMMfgRtngItmNde.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/mfg_rtng_itm_nde" by keyFields "srcSysCd,rtngTypCd,rtngGrpCd,rtngGrpCntrNbr,rtngSqncNum,rtngNdeNum,rtngNdeVrsnCntrNbr"
      | srcSysCd   | rtngTypCd | rtngGrpCd   | rtngGrpCntrNbr | rtngSqncNum | rtngNdeNum | rtngNdeVrsnCntrNbr | ValidFromDate | chgNum | delInd | CreateDttm | chgDttm  |
      | CONS_LATAM |           | 00000001_T1 | 01             | 000000      | 00000001   | 00000001           | 20170504      | 111    | 111    | 20180504   | 20090911 |
      | CONS_LATAM | 2         | 00000002_F1 | 01             | 000000      | 00000002   | 00000002           | 20170504      | 111    | 111    | 20180504   | 20090911 |
      | CONS_LATAM | N         | 00000003_F1 | 01             | 000000      | 00000002   | 00000002           | 20170504      | 111    | 111    | 20180504   | 20090911 |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/project_one/plas" and "/edm/mfg_rtng_itm_nde,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/edm/mfg_rtng_itm_nde"

    And I will remove all data with region "/plan/edm_failed_data"

