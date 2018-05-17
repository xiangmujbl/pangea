@pangea_test @AEAZ-1485
Feature: CnsLotSizeKey AEAZ-1485

  Scenario: Full Load curation
    # 1. get sourceSystem from source_system_v1 with rule T1
    # 2. get loslt from t439t where spras = 'E' (T2)

    And I import "/project_one/t439t" by keyFields "disls,spras"
      | loslt         | disls | spras |
      | Description_E | AN    | E     |
      | Description_P | AN    | P     |
      | Description_E | AM    | E     |
      | Description_S | AM    | S     |
      | Description_E | AP    | E     |
    And I wait "/project_one/t439t" Async Queue complete

    And I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | localSourceSystemName | sourceSystem | sourceSystemName   |
      | project_one       | Project One           | CONS_LATAM   | Consumer Latam Ent |
      | project_two       | Project Two           | CONS_LATAM   | Consumer Latam Ent |
    And I wait "/edm/source_system_v1" Async Queue complete

    When I submit task with xml file "xml/plan/PlanCnsLotSizeKeyTrans.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/plan/cns_lot_size_key_trans" by keyFields "sourceSystem,localLotSizeKey"
      | sourceSystem | localLotSizeKey | localLotSizeKeyDescription | lotSizeKey | lotSizeKeyDescription |
      | CONS_LATAM   | AN              | Description_E              |            |                       |
      | CONS_LATAM   | AM              | Description_E              |            |                       |
      | CONS_LATAM   | AP              | Description_E              |            |                       |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

#    And I compare the number of records between "/project_one/t439t" and "/plan/cns_lot_size_key_trans,/plan/edm_failed_data"

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/plan/cns_lot_size_key_trans"

    And I will remove all data with region "/plan/edm_failed_data"