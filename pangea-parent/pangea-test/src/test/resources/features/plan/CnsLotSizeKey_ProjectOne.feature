@pangea_test @AEAZ-1279
Feature: CnsLotSizeKey

    Scenario: Full Load curation

        Given I import "/project_one/t439" by keyFields ""
            |  disls             |
            |000000000000000016  |
            |000000000000000029  |

        And I wait "/project_one/t439" Async Queue complete

        And I import "/edm/source_system_v1" by keyFields "localSourceSystem"
            | localSourceSystem | localSourceSystemName | sourceSystem | sourceSystemName   |
            | project_one       | Project One           | CONS_LATAM   | Consumer Latam Ent |
            | project_two       | Project Two           | CONS_LATAM   | Consumer Latam Ent |
        And I wait "/edm/source_system_v1" Async Queue complete

        And I import "/project_one/t439t" by keyFields ""
            | loslt|disls|spras |
            |      |     |      |
            |      |     |      |
        And I wait "/project_one/t439t" Async Queue complete

        When I submit task with xml file "xml/plan/CnsLotSizeKey_ProjectOne.xml" and execute file "jar/pangea-view.jar"

        Then I check region data "/plan/cns_lot_size_key" by keyFields "sourceSystem,localLotSizeKey,localLotSizeKeyDescription"
            |sourceSystem|localLotSizeKey|localLotSizeKeyDescription|lotSizeKey|lotSizeKeyDescription|
            |            |               |                          |          |                     |
            |            |               |                          |          |                     |

        Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
            | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

        And I compare the number of records between "/project_one/t439" and "/plan/cns_lot_size_key,/plan/edm_failed_data"

        And I delete the test data

        And I will remove all data with region "/plan/cns_lot_size_key"
        And I will remove all data with region "/plan/edm_failed_data"