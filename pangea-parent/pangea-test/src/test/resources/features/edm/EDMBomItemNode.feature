@pangea_test @AEAZ-3270
Feature: EDMBomItemNode AEAZ-3270

  Scenario: Full Load curation

    Given I import "/project_one/stas" by keyFields "stlty,stlnr,stlkn,stlal,stasz,mandt"
      | stlty  | stlnr      | stlal | stlkn    | stasz       | datuv     | aennr | lkenz | andat      | aedat     |mandt|
      | S      | 00000001   | 01    | 00000001 | 00000003   | 20000825  |        |        | 20000825  | 00000000 | 120   |
      | S      | 00000002   | 01    | 00000001 | 00000003   | 20000826  |        |        | 20000825  | 00000000 | 120  |
    And I wait "/project_one/stas" Async Queue complete

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | localSourceSystemName | sourceSystem | sourceSystemName   |
      | project_one        | Project One            | CONS_LATAM   | Consumer Latam Ent |
      | ems                 | EMS                     | EMS           | ems                 |

    And I wait "/edm/source_system_v1" Async Queue complete

    When I submit task with xml file "xml/edm/EDMBomItemNode.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/bom_item_node" by keyFields "srcSysCd,bomCatCd,bomNum,altBomNum,bomItmNdeNum,bomItmNdeCntrNbr"
      | srcSysCd   | bomCatCd | bomNum      | altBomNum | bomItmNdeNum | bomItmNdeCntrNbr | bomItmNdeVldFromDt | chgNum | delInd | crtDttm     | chgDttm     |
      |CONS_LATAM  | S         | 00000001   | 01         | 00000001      | 00000003          | 20000825            |         |         | 20000825    | 00000000   |
      |CONS_LATAM  | S         | 00000002   | 01         | 00000001      | 00000003          | 20000826            |         |         | 20000825    | 00000000   |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/project_one/stas" and "/edm/bom_item_node,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/edm/bom_item_node"

    And I will remove all data with region "/plan/edm_failed_data"



