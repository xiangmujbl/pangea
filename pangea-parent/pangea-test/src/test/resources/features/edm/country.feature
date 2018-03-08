@pangea_test @AEAZ-492
Feature: EDMCountry-Curation

  Scenario: Full Load curation
    #  1. test get sourceSystem from source_system_v1 and overwrite the value in this field (rule T1)
    #  2. test get mdm_name as countryName from ems_f_mdm_countries and if initial, leave blank ( rule T2 )

    Given I import "/ems/ems_f_mdm_countries" by keyFields "zSourceSystem,mdmCode"
      | zSourceSystem | mdmCode | zEntCodeIso3166Alpha2 | mdmName       |
      | project_one   | *       | -                     | All Countries |
      | project_two   | 00      | -                     | Miscellaneous |
      | [EMS]         | *       | 00                    | All Countries |
      | [EMS]         | 00      | -                     | All Countries |
    And I wait "/ems/ems_f_mdm_countries" Async Queue complete

    And I import "/pangea/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | localSourceSystemName | sourceSystem | sourceSystemName   |
      | project_one       | Project One           | CONS_LATAM   | Consumer Latam Ent |
      | [EMS]             | EMS                   | EMS          | EMS Ent            |
      | project_two       | Project Two           | CONS_LATAM   | Consumer Latam Ent |
    And I wait "/pangea/edm/source_system_v1" Async Queue complete

    When I submit task with xml file "xml/edm/country.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/pangea/edm/country_v1" by keyFields "sourceSystem,localCountry"
      | sourceSystem | localCountry | countryCode | countryName   | consumerPlanningRegion |
      | CONS_LATAM   | *            | -           |               |                        |
      | CONS_LATAM   | 00           | -           |               |                        |

    And I delete the test data

    And I will remove all data with region "/pangea/edm/country_v1"
#    And I compare the number of records between "/ems/ems_f_mdm_countries" and "/pangea/edm/country_v1,/pangea/edm/country_v1_failed"