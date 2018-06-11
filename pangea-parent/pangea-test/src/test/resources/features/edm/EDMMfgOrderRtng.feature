@pangea_test @AEAZ-4248
Feature: EDMMfgOrderRtng AEAZ-4248

  Scenario: Full Load curation

    Given I import "/project_one/afvc" by keyFields "aplzl,aufpl"
      | aufpl      | aplzl    | vornr | steus | arbid    | ltxa1    |
      | 0000000324 | 00000001 | 0010  | PP01  | 10000456 | Produzir |
      | 0000000325 | 00000002 | 0020  | PP02  | 10000457 | Produzir |
      | 0000000326 | 00000003 | 0030  | PP03  | 10000458 | Produzir |

    And I wait "/project_one/afvc" Async Queue complete

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | sourceSystem |
      | project_one       | CONS_LATAM   |
      | EMS               | EMS          |

    And I wait "/edm/source_system_v1" Async Queue complete


    When I submit task with xml file "xml/edm/EDMMfgOrderRtng.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/mfg_order_rtng" by keyFields "srcSysCd,ordrRtngNum,ordrRtngCtrNum"
      | srcSysCd   | ordrRtngNum | ordrRtngCtrNum | operNum | operCd | wrkCntrId | operDesc |
      | CONS_LATAM | 0000000324  | 00000001       | 0010    | PP01   | 10000456  | Produzir |
      | CONS_LATAM | 0000000325  | 00000002       | 0020    | PP02   | 10000457  | Produzir |
      | CONS_LATAM | 0000000326  | 00000003       | 0030    | PP03   | 10000458  | Produzir |

  Scenario: delete all test data

    And I delete the test data

    And I will remove all data with region "/edm/mfg_order_rtng"

    And I will remove all data with region "/edm/source_system_v1"

    And I will remove all data with region "/project_one/afvc"

