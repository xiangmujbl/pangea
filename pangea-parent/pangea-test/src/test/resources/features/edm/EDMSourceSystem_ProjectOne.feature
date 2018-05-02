@pangea_test @AEAZ-868
Feature: EDMSourceSystem AEAZ-868
  # test copy attributes from source system to target system

  Scenario: Full Load curation

    Given I import "/ngems/source_system" by keyFields "localSourceSystem"
      | localSourceSystem       | localSourceSystemName | sourceSystem | sourceSystemName   |
      | Project_One             | Project One           | CON_LATAM    | Consumer Latam Ent |
      | [EMS]                   | EMS                   | EMS          | EMS Ent            |
      | [MD DePuy Spine JDE XE] | Spine                 | MDDePuy      | MD DePuy Ent       |
      | [Consumer LATAM]        | Consumer Latam        | CON_LATAM    | Consumer Latam Ent |
    And I wait "/ngems/source_system" Async Queue complete

    When I submit task with xml file "xml/edm/EDMSourceSystem_ProjectOne.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem       | localSourceSystemName | sourceSystem | sourceSystemName   |
      | Project_One             | Project One           | CON_LATAM    | Consumer Latam Ent |
      | [EMS]                   | EMS                   | EMS          | EMS Ent            |
      | [MD DePuy Spine JDE XE] | Spine                 | MDDePuy      | MD DePuy Ent       |
      | [Consumer LATAM]        | Consumer Latam        | CON_LATAM    | Consumer Latam Ent |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/ngems/source_system" and "/edm/source_system_v1,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/edm/source_system_v1"
    And I will remove all data with region "/plan/edm_failed_data"