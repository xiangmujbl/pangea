@pangea_test @AEAZ-2370
Feature: EDMPlannedOrder

  Scenario: Full Load curation AEAZ-2370
  # 1. Get Plant from Plant_V1 (N1)(plnum:0000267326)
  # 2. Get localMaterialNumber from material_global_v1 (N2)(plnum:0000267326)
  # 3. Get plscn from plaf where plscn = 000 (N3)(plnum:0000267327)

    Given I import "/project_one/plaf" by keyFields "plnum"
      | plnum      | plwrk | pwwrk | matnr              | meins | beskz | sobes | numvr | paart | gsmng | avmng | bdmng | psttr    | pstti  | pedtr    | pedti  | webaz | auffx | lgort | verid | terst    | tered    | dispo | plscn |
      | 0000267326 | BR02  | BR02  | 000000000000002021 | KI    | E     | E     | 00    | LA    | 2016  | 0.000 | 0.000 | 20000918 | 000000 | 20000919 | 000000 | 0     |       | BR02  | V001  | 00000000 | 00000000 | 251   | 000   |
      | 0000267327 | BR03  | BR02  | 000000000000002022 | KI    | E     | E     | 00    | LA    | 2016  | 0.000 | 0.000 | 20000918 | 000000 | 20000919 | 000000 | 0     |       | BR02  | V001  | 00000000 | 00000000 | 251   | 009   |

    And I wait "/project_one/plaf" Async Queue complete

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | sourceSystem   |
      | project_one       | Consumer LATAM |

    And I wait "/edm/source_system_v1" Async Queue complete

    Given I import "/edm/plant_v1" by keyFields "localPlant"
      | localPlant | plant |
      | BR02       | BR02  |

    And I wait "/edm/plant_v1" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber"
      | localMaterialNumber | materialNumber |
      | 000000000000002021  | 9862           |

    And I wait "/edm/material_global_v1" Async Queue complete

    When I submit task with xml file "xml/edm/EDMPlannedOrder.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/planned_order_v1" by keyFields "sourceSystem,mfgPlannedOrderId"
      | sourceSystem   | mfgPlannedOrderId | localPlant | plant | localproductionPlant | localMaterialNumber | materialNumber | localUom | localProcurementType | localSplProcType | localPrdVersion | localPrdOrdType | plannedOrdQty | localScrapQty | requirementQty | orderstrtDate | ordStrtTime | ordFinishDate | ordEndTime | grProcessDays | ordFirmInd | localStorageLoc | localDocVersion | prdStartDate | prdFinishDate | mrpController | localPlanningScenario |
      | Consumer LATAM | 0000267326        | BR02       | BR02  | BR02                 | 000000000000002021  | 9862           | KI       | E                    | E                | 00              | LA              | 2016          | 0.000         | 0.000          |20000918       | 000000      | 20000919      | 000000     | 0             |            | BR02            | V001            | 00000000     | 00000000      | 251           | 000                   |
      | Consumer LATAM | 0000267327        | BR03       |       | BR02                 | 000000000000002022  |                | KI       | E                    | E                | 00              | LA              | 2016          | 0.000         | 0.000          |20000918       | 000000      | 20000919      | 000000     | 0             |            | BR02            | V001            | 00000000     | 00000000      | 251           |                       |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/project_one/plaf" and "/edm/planned_order_v1,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/edm/planned_order_v1"

    And I will remove all data with region "/plan/edm_failed_data"