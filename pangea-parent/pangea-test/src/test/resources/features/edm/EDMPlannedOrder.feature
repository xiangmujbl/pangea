@pangea_test @AEAZ-2370
Feature: EDMPlannedOrder AEAZ-2370

  Scenario: Full Load curation
  # 1. Get plscn from plaf where plscn = 000 (N3)
  # 2. If plaf-plscn <> 000, reject record (N3)

    Given I import "/project_one/plaf" by keyFields "plnum"
      | plnum      | plwrk | pwwrk | matnr              | meins | beskz | sobes | numvr | paart | gsmng    | avmng | bdmng | psttr    | pstti  | pedtr    | pedti  | webaz | auffx | lgort | verid | terst    | tered    | dispo | plscn |
      | 0000267326 | BR02  | BR02  | 000000000000002021 | KI    | E     | E     | 00    | LA    | 2016.000 | 0.000 | 0.000 | 20000918 | 000000 | 20000919 | 000000 | 0     |       | BR02  | V001  | 00000000 | 00000000 | 251   | 000   |
      | 0000267327 | BR03  | BR02  | 000000000000002022 | KI    | E     | E     | 00    | LA    | 2016.000 | 0.000 | 0.000 | 20000918 | 000000 | 20000919 | 000000 | 0     |       | BR02  | V001  | 00000000 | 00000000 | 251   | 009   |
      | 0000273372 | BR02  | BR02  | 000000000000000767 | KI    | F     | 3     | 00    | NB    | 2018.000 | 0.000 | 0.000 | 20001023 | 000000 | 20001023 | 000000 | 0     |       | BR02  |       | 00000000 | 00000000 | 301   | 000   |
    And I wait "/project_one/plaf" Async Queue complete

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | sourceSystem   |
      | project_one       | Consumer LATAM |

    And I wait "/edm/source_system_v1" Async Queue complete

    When I submit task with xml file "xml/edm/EDMPlannedOrder.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/planned_order_v1" by keyFields "srcSysCd,mfgPlanOrdrDocId"
      | srcSysCd       | mfgPlanOrdrDocId | planPlntCd | plntCd | matlNum            | uomCd | prcmtTypeCd | splPrcmtTypeCd | prdtnVersNum | planOrdrTypeCd | planOrdrQty | fxScrapQty | reqQty | planOrdrStrtDt | prdtnStrtTm | planOrdrEndDt | planOrdrEndTm | grDaysLeadQty | firmingInd | sLocCd | prdtnVers | prdtnStrtDt | prdtnFnshdDt | mrpCtlId | plngScnroCd |
      | Consumer LATAM | 0000267326       | BR02       | BR02   | 000000000000002021 | KI    | E           | E              | 00           | LA             | 2016.000    | 0.000      | 0.000  | 20000918       | 000000      | 20000919      | 000000        | 0             |            | BR02   | V001      | 00000000    | 00000000     | 251      | 000         |
      | Consumer LATAM | 0000273372       | BR02       | BR02   | 000000000000000767 | KI    | F           | 3              | 00           | NB             | 2018.000    | 0.000      | 0.000  | 20001023       | 000000      | 20001023      | 000000        | 0             |            | BR02   |           | 00000000    | 00000000     | 301      | 000         |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | key1 | key2 | key3 | key4 | key5 | errorValue |

#    And I compare the number of records between "/project_one/plaf" and "/edm/planned_order_v1,/plan/edm_failed_data"

#  Scenario: delete all test data
#
#    Then I delete the test data
#
#    And I will remove all data with region "/edm/planned_order_v1"
#
#    And I will remove all data with region "/plan/edm_failed_data"