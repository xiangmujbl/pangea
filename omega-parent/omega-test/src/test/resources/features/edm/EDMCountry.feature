@omega_test @AEAZ-492
Feature: EDMCountry AEAZ-492

  Scenario: Full Load curation
    #  1. test get sourceSystem from source_system_v1 and overwrite the value in this field (rule T1)
    #  2. test get mdm_name as countryName from ems_f_mdm_countries and if initial, leave blank ( rule T2 )

    Given I import "/ems/ems_f_mdm_countries" by keyFields "zSourceSystem,mdmCode"
      | zSourceSystem | mdmCode | zEntCodeIso3166Alpha2 | mdmName       |
      | project_one   | *       | AD                    | All Countries |
      | project_two   | 00      | AE                    | Miscellaneous |
      | [EMS]         | AD      |                       | All Countries |
      | [EMS]         | AE      |                       | Miscellaneous |
    And I wait "/ems/ems_f_mdm_countries" Async Queue complete

    And I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | localSourceSystemName | sourceSystem | sourceSystemName   |
      | project_one       | Project One           | CONS_LATAM   | Consumer Latam Ent |
      | [EMS]             | EMS                   | EMS          | EMS Ent            |
      | project_two       | Project Two           | CONS_LATAM   | Consumer Latam Ent |
    And I wait "/edm/source_system_v1" Async Queue complete

    When I submit task with xml file "xml/edm/EDMCountry.xml" and execute file "jar/omega-view.jar"

    Then I check region data "/edm/country_v1" by keyFields "sourceSystem,localCountry"
      | sourceSystem | localCountry | countryCode | countryName   | consumerPlanningRegion | consumerPlannRegDesc |
      | CONS_LATAM   | *            | AD          | All Countries |                        |                      |
      | CONS_LATAM   | 00           | AE          | Miscellaneous |                        |                      |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

#    And I compare the number of records between "/ems/ems_f_mdm_countries" and "/edm/country_v1,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/edm/country_v1"

    And I will remove all data with region "/plan/edm_failed_data"




