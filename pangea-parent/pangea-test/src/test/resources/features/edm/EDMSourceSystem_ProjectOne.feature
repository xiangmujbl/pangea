@pangea_test
Feature: EDMSourceSystem
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

    Then I check region data "/pangea/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem       | localSourceSystemName | sourceSystem | sourceSystemName   |
      | Project_One             | Project One           | CON_LATAM    | Consumer Latam Ent |
      | [EMS]                   | EMS                   | EMS          | EMS Ent            |
      | [MD DePuy Spine JDE XE] | Spine                 | MDDePuy      | MD DePuy Ent       |
      | [Consumer LATAM]        | Consumer Latam        | CON_LATAM    | Consumer Latam Ent |

    And I delete the test data

    And I will remove all data with region "/pangea/edm/source_system_v1"