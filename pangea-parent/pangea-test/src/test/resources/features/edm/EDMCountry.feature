@pangea_test @AEAZ-5739
Feature: EDMCountry AEAZ-5739

  Scenario: Full Load curation
    #  1. test get sourceSystem from source_system_v1 and overwrite the value in this field (rule T1)
    #  2. test get mdm_name as countryName from ems_f_mdm_countries and if initial, leave blank ( rule T2 )

    Given I import "/ems/ems_f_mdm_countries" by keyFields "zSourceSystem,mdmCode"
      | zSourceSystem    | mdmCode | zEntCodeIso3166Alpha2 | mdmName         |
      | project_one      | *       | AD                    | All Countries   |
      | project_two      | 00      | AE                    | Miscellaneous   |
      | project_three    | 01      | AD                    | EN Countries    |
      | [EMS]            | AD      | CT                    | All Countries   |
      | [EMS]            | AE      | CG                    | Miscellaneous   |
      | [EMS]            | BS      | GE                    | Bahamas         |
      | [EMS]            | BT      | CS                    | Bhutan          |
      | [EMS]            | BR      | AE                    | Brazil          |
      | [Consumer LATAM] | BS      | BS                    | z All Countries |
      | [Consumer LATAM] | BT      | BT                    | z Miscellaneous |
      | [Consumer LATAM] | BR      | BR                    | z EN Countries  |

    And I wait "/ems/ems_f_mdm_countries" Async Queue complete

    And I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | localSourceSystemName | sourceSystem | sourceSystemName   |
      | project_one       | Project One           | CONS_LATAM   | Consumer Latam Ent |
      | [EMS]             | EMS                   | EMS          | EMS Ent            |
      | project_two       | Project Two           | CONS_LATAM   | Consumer Latam Ent |
      | project_three     | project Three         |              | Consumer Latam Ent |
      | [Consumer LATAM]  | Project One           | CONS_LATAM   | Consumer Latam Ent |

    And I wait "/edm/source_system_v1" Async Queue complete


    And I import "/plan/edm_country_input" by keyFields "localCountry,sourceSystem"
      |sourceSystem  |planningRegionId  |localCountry|
      | CONS_LATAM   | CDEF123          | *            |
      | AECF_JDAMS   | CGUF123          | *            |
      | CONS_LATAM   | AGCF189          | 00           |
      | DPCG_QASLT   | EECT178          | 00           |
      | CONS_LATAM   | 103              | BR           |
      | CONS_LATAM   | 104              | BS           |
    And I wait "/plan/edm_country_input" Async Queue complete

    And I import "/plan/cns_plan_region" by keyFields "planningRegionId"
      |planningRegionDesc  |planningRegionId  |
      | CDEF190            | CDEF123          |
      | AGEC019            | AGCF189          |
      | LATAM              | 103              |
    When I submit task with xml file "xml/edm/EDMCountry.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/country_v1" by keyFields "sourceSystem,localCountry"
      | sourceSystem | localCountry | countryCode | countryName   | consumerPlanningRegion | consumerPlannRegDesc |
      | CONS_LATAM   | *            | AD          | All Countries | CDEF123                | CDEF190              |
      | CONS_LATAM   | 00           | AE          | Miscellaneous | AGCF189                | AGEC019              |
      | CONS_LATAM   | BR           | BR          | Brazil        | 103                    | LATAM                |
      | CONS_LATAM   | BS           | BS          | Bahamas       | 104                    |                      |
      | CONS_LATAM   | BT           | BT          | Bhutan        |                        |                      |
    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |


  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/edm/country_v1"

    And I will remove all data with region "/plan/edm_failed_data"
