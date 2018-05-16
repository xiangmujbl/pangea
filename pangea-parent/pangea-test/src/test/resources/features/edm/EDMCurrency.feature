@pangea_test @AEAZ-498
Feature: EDMCurrency AEAZ-498

  Scenario: Full Load curation
    #  1. test filter zSourceSystem <> '[EMS]'
    #  2. test get sourceSystem from source_system_v1 (rule T1)
    #  3. test get z_name from ems_f_z_currencies and if initial, leave blank ( rule T2 )

    Given I import "/ems/ems_f_z_currencies" by keyFields "zSourceSystem,zCode"
      | zSourceSystem            | zCode | zEntCodeIso4217Alpha | zName          | zIso4217Numeric |
      | [MD Synthes SAP Anspach] | ADP   | -                    | Andoran peseta | -               |
      | [MDD FASE]               | AED   | AED                  | UAE Dirham     | -               |
      | [EMS]                    | AED   | AED                  | UAE Dirham     | 784             |
    And I wait "/ems/ems_f_z_currencies" Async Queue complete

    And I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem        | localSourceSystemName    | sourceSystem | sourceSystemName   |
      | [MDD FASE]               | [MDD FASE]               | CONS_LATAM   | Consumer Latam Ent |
      | [EMS]                    | EMS                      | EMS          | EMS Ent            |
      | [MD Synthes SAP Anspach] | [MD Synthes SAP Anspach] | CONS_LATAM   | Consumer Latam Ent |
    And I wait "/edm/source_system_v1" Async Queue complete

    When I submit task with xml file "xml/edm/currency.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/currency_v1" by keyFields "sourceSystem,localCurrency"
      | sourceSystem | localCurrency | currencyCode | currencyName | isoNumeric |
      | CONS_LATAM   | ADP           | -            |              | -          |
      | CONS_LATAM   | AED           | AED          | UAE Dirham   | -          |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1  | key2 | key3 | key4 | key5 | errorValue |

    Then I check region data "/plan/currency_v1_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |
#      |                |   | z_source_system value is not [EMS] and rule T1   |       |               |  [EMS] |  AED|    |      |      |           |

#    And I compare the number of records between "/ems/ems_f_z_currencies" and "/edm/currency_v1,/plan/edm_failed_data"

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/edm/currency_v1"

    And I will remove all data with region "/plan/edm_failed_data"


