@pangea_test @AEAZ-498
Feature: EDMCurrency-Curation

  Scenario: Full Load curation
    #  1. test filter zSourceSystem <> '[EMS]'
    #  2. test get sourceSystem from source_system_v1 (rule T1)
    #  3. test get z_name from ems_f_z_currencies and if initial, leave blank ( rule T2 )

#    Given I import "/ems/ems_f_z_currencies" by keyFields "zSourceSystem,zCode"
#      | zSourceSystem               | zCode | zEntCodeIso4217Alpha | zName            | zIso4217Numeric |
#      | [MD Synthes SAP Anspach]   |ADP    | -                      | Andoran peseta   | -                |
#      | [MDD FASE]                  | AED    | AED                    | UAE Dirham      | -                |
#      | [EMS]                        | AED   | AED                    | UAE Dirham       | 784              |
#    And I wait "/ems/ems_f_z_currencies" Async Queue complete
#
#    And I import "/pangea/edm/source_system_v1" by keyFields "localSourceSystem"
#      | localSourceSystem     | localSourceSystemName       | sourceSystem | sourceSystemName   |
#      | [MDD FASE]              | [MDD FASE]                  | CONS_LATAM   | Consumer Latam Ent |
#      | [EMS]                   | EMS                           | EMS          | EMS Ent            |
#      | [MD Synthes SAP Anspach]| [MD Synthes SAP Anspach] | CONS_LATAM   | Consumer Latam Ent |
#    And I wait "/pangea/edm/source_system_v1" Async Queue complete

    When I submit task with xml file "xml/edm/currency.xml" and execute file "jar/pangea-view.jar"

#    Then I check region data "/pangea/edm/currency_v1" by keyFields "sourceSystem,localCurrency"
#      | sourceSystem | localCurrency | currencyCode | currencyName | isoNumeric |
#      | CONS_LATAM   | ADP            |  -             |                | -           |
#      | CONS_LATAM   | AED            | AED            | UAE Dirham     | -        |
#
#    And I delete the test data
#
#    And I will remove all data with region "/pangea/edm/currency_v1"
#    And I compare the number of records between "/ems/ems_f_z_currencies" and "/pangea/edm/currency_v1,/pangeas/edm/currency_v1_failed"