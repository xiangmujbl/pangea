@pangea_test @AEAZ-1483
Feature: CnsPlngStratGrp AEAZ-1483

  Scenario: Full Load curation

    Given I import "/project_one/t461p" by keyFields "strgr"
      | strgr |
      | EH    |
      | EC    |
      | EA    |

    And I wait "/project_one/t461p" Async Queue complete

    And I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | localSourceSystemName | sourceSystem | sourceSystemName   |
      | project_one       | Project One           | CONS_LATAM   | Consumer Latam Ent |
      | project_two       | Project Two           | CONS_LATAM   | Consumer Latam Ent |
    And I wait "/edm/source_system_v1" Async Queue complete

    And I import "/project_one/t461x" by keyFields "strgr"
      | strgr | text40 | spras |
      | EH    | 04     | E     |
      | EC    | 05     | E     |
      | EA    | 06     | E     |
    And I wait "/project_one/t461x" Async Queue complete

    When I submit task with xml file "xml/plan/PlanCnsPlngStratGrp.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/plan/cns_plng_strat_grp" by keyFields "sourceSystem,localPlanStratGrp,localPlanStratGrpDesc"
      | sourceSystem | localPlanStratGrp | localPlanStratGrpDesc | planStratGrp | planStratGrpDesc |
      | CONS_LATAM   | EH                | 04                    |              |                  |
      | CONS_LATAM   | EC                | 05                    |              |                  |
      | CONS_LATAM   | EA                | 06                    |              |                  |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/project_one/t461p" and "/plan/cns_plng_strat_grp,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/plan/cns_plng_strat_grp"
    And I will remove all data with region "/plan/edm_failed_data"