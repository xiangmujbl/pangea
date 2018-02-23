@pangea_test
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

    And I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | localSourceSystemName | sourceSystem | sourceSystemName   |
      | project_one       | Project One           | CONS_LATAM   | Consumer Latam Ent |
      | [EMS]             | EMS                   | EMS          | EMS Ent            |
      | project_two       | Project Two           | CONS_LATAM   | Consumer Latam Ent |

    When I submit task with xml file "xml/material_country.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/country_v1" by keyFields "sourceSystem,localMaterialNumber,localAuom"
      | sourceSystem | localCountry | countryCode | countryName   | consumerPlanningRegion |
      | CONS_LATAM   | *            | -           |               |                        |
      | CONS_LATAM   | 00           | -           | All Countries |                        |

    And I compare the number of records between "/ems/ems_f_mdm_countries" and "/edm/country_v1,/edm/country_v1_failed"