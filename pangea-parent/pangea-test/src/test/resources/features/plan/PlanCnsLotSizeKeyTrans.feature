@pangea_test @AEAZ-1485
Feature: CnsLotSizeKey AEAZ-1485

    Scenario: Full Load curation

        Given I import "/project_one/t439" by keyFields "disls"
            |disls|
            | AN  |
            | AM  |
            | AP  |

        And I wait "/project_one/t439" Async Queue complete

        And I import "/edm/source_system_v1" by keyFields "localSourceSystem"
            | localSourceSystem | localSourceSystemName | sourceSystem | sourceSystemName   |
            | project_one       | Project One           | CONS_LATAM   | Consumer Latam Ent |
            | project_two       | Project Two           | CONS_LATAM   | Consumer Latam Ent |
        And I wait "/edm/source_system_v1" Async Queue complete

        And I import "/project_one/t439t" by keyFields "disls"
            | loslt|disls|spras |
            |  00  | AN  |  E   |
            |  01  | AM  |  E   |
            |  02  | AP  |  E   |
        And I wait "/project_one/t439t" Async Queue complete

        When I submit task with xml file "xml/plan/PlanCnsLotSizeKeyTrans.xml" and execute file "jar/pangea-view.jar"

        Then I check region data "/plan/cns_lot_size_key_trans" by keyFields "sourceSystem,localLotSizeKey"
            |sourceSystem|localLotSizeKey|localLotSizeKeyDescription|lotSizeKey|lotSizeKeyDescription|
            | CONS_LATAM |  AN           | 00                       |          |                     |
            | CONS_LATAM |  AM           | 01                       |          |                     |
            | CONS_LATAM |  AP           | 02                       |          |                     |

        Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
            | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

        And I compare the number of records between "/project_one/t439" and "/plan/cns_lot_size_key_trans,/plan/edm_failed_data"

        And I delete the test data

        And I will remove all data with region "/plan/cns_lot_size_key_trans"
        And I will remove all data with region "/plan/edm_failed_data"