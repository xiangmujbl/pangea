@pangea_test @AEAZ-1481
Feature: PlanCnsAbcInd AEAZ-1481

  Scenario: Full Load curation

    Given I import "/project_one/tmabc" by keyFields "maabc"
      | maabc |
      | 10001 |
      | 10002 |
      | 10003 |
      | 10004 |

    And I wait "/project_one/tmabc" Async Queue complete

    And I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | sourceSystem |
      | project_one       | CONS_LATAM   |
      | Project_one       | EMS          |
    And I wait "/edm/source_system_v1" Async Queue complete

    And I import "/project_one/tmabct" by keyFields "tmabc"
      | tmabc | maabc | spars |
      | 2001  | 10001 | E     |
      | 2002  | 10002 | E     |
      | 2003  | 10003 | S     |
    And I wait "/project_one/tmabct" Async Queue complete

    When I submit task with xml file "xml/plan/PlanCnsAbcInd_ProjectOne.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/plan/cns_abc_ind" by keyFields "localIndicator,localIndicatorDescription,indicator,indicatorDescription,sourceSystem"
      | sourceSystem | localIndicator | localIndicatorDescription | indicator | indicatorDescription |
      | CONS_LATAM   | 10001          | 2001                      |           |                      |
      | CONS_LATAM   | 10002          | 2002                      |           |                      |
      | CONS_LATAM   | 10003          |                           |           |                      |
      | CONS_LATAM   | 10004          |                           |           |                      |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/project_one/tmabc" and "/plan/cns_abc_ind,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/plan/cns_abc_ind"
    And I will remove all data with region "/plan/edm_failed_data"