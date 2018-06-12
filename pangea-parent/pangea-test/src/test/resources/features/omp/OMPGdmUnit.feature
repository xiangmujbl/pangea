@pangea_test @AEAZ-3683
Feature: OMPGdmUnit AEAZ-3683

  Scenario: Full Load consumption for currency
    #  1. If no records found from currency_v1, skip insertion (rule E1)
    #  2. Send as "YES" when sourced from edm currency_v1 (rule D1)
    #  3. when sourced from edm currency_v1 populate 1.0 (rule D2)

    And I will remove the test file on sink application "GDMUnit_currency.tsv"

    Given I import "/edm/currency_v1" by keyFields "localCurrency,sourceSystem"
      | localCurrency | sourceSystem | isoNumeric | currencyName | currencyCode |
      | USD           | MDDePuy      | -          | US Dollar    | USD          |
      | AFA           | CONS_LATAM   | -          | -            |              |

    And I wait "/edm/currency_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmUnitCurrency.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMUnit_currency.tsv"

    Then I check file data for filename "GDMUnit_currency.tsv" by keyFields "unitId"
      | unitId | active | activeFCTERP | activeOPRERP | activeSOPERP | factor | isoCode | longDescription | measure  | precision | shortDescription |
      | USD    | YES    | YES          | YES          | NO           | 1.0    |         | US Dollar       | CURRENCY | 0         | US Dollar        |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | errorCode | functionalArea | interfaceID | key1 | key2 | key3 | key4 | key5 | errorValue | sourceSystem |

#    And I compare the number of records between "/edm/currency_v1" and "/omp/gdm_unit,/plan/edm_failed_data"

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/omp/gdm_unit"

    And I will remove all data with region "/plan/edm_failed_data"

  Scenario: Full Load consumption for measure
    # 1. Get atrribute from cns_plan_unit-factor when cns_plan_unit-unit in (unit_of_measure_V1-uom) (rule F1)
    # 2. Populate '1' when cns_plan_unit-unit not exist in unit_of_measure_v1-uom (rule F1)
    # 3. Populate 'YES' when cns_plan_unit-planFlag = DP or DPSP (rule D3,D4)
    # 4. Populate 'NO' when cns_plan_unit-planFlag <> DP or DPSP (rule D3,D4)
    # 5. Get unit_of_measure_v1-isocode when cns_plan_unit-unit = (unit_of_measure_V1-uom) (rule F2)
    # 6. Send as “ “ when cns_plan_unit-unit <> (unit_of_measure_V1-uom) (rule F2)
    # 7. Get unit_of_measure_v1-uomName when cns_plan_unit-unit = unit_of_measure_V1-uom (rule T2)
    # 8. Get cns_plan_unit-localUomName when unit_of_measure_v1-uomName is bank and cns_plan_unit-unit = unit_of_measure_V1-uom (rule T2)
    # 9. Get unit_of_measure_v1-measure when cns_plan_unit-unit = unit_of_measure_V1-uom (rule T3)
    # 10.Send as 'AAAADL' when unit_of_measure_v1-measure is bank and cns_plan_unit-unit = unit_of_measure_V1-uom (rule T3)
    # 11.Get unit_of_measure_v1-roundingDecimal when cns_plan_unit-unit = unit_of_measure_V1-uom (rule T4)
    # 12.Send as cns_plan_unit-rounding decimal when cns_plan_unit-unit <> unit_of_measure_V1-uom (rule T4)
    # 13.Send as cns_plan_unit-rounding decimal when unit of measure_V1-roundingDecimal is blank and cns_plan_unit-unit = unit_of_measure_V1-uom (rule T4)
    # 14.Get unit_of_measure_v1-uomName when cns_plan_unit-unit = unit_of_measure_V1-uom (rule T5)
    # 15.Get cns_plan_unit-localUomName when unit_of_measure_v1-uomName is blank and cns_plan_unit-unit = unit_of_measure_V1-uom (rule T5)

    And I will remove the test file on sink application "GDMUnit_measure.tsv"

    Given I import "/plan/cns_plan_unit" by keyFields "localUom,localUomName,planFlag,sourceSystem,unit"
      | localUom | localUomName        | planFlag | sourceSystem | unit   | factor | roundingDecimal |
      | KI       | Crate               | DPSP     | CONS_LATAM   | CA     | 2      | 0               |
      | CS       | Case                | DPSP     | CONS_LATAM   | CA     | 2      | 0               |
      | EA       | Each                | DPSP     | CONS_LATAM   | EA     | 2      | 0               |
      | KG       | KiloGram            | SP       | CONS_LATAM   | KG     | 2      | 6               |
      | ZUM      | Market Control Unit | DP       | CONS_LATAM   | LA_ZUM | 2      | 6               |
      | DZ       | Dozen               | SP       | CONS_LATAM   | DZ     | 2      | 3               |
      | KM       | Kilometer           | SP       | CONS_LATAM   | KM     | 2      | 3               |
      | L        | Liter               | SP       | CONS_LATAM   | L      | 2      | 6               |
      | M2       | Square Meter        | SP       | CONS_LATAM   | M2     | 2      | 6               |
      | TH       | Thousand            | SP       | CONS_LATAM   | TS     | 2      | 6               |
      | PAL      | Pallet              | SP       | CONS_LATAM   | PAL    | 2      | 0               |

    And I wait "/plan/cns_plan_unit" Async Queue complete

    Given I import "/edm/unit_of_measure_v1" by keyFields "localUom,sourceSystem"
      | uom | factor | isoCode | uomName      | measure | roundingDecimal | localUom | sourceSystem |
      | CA  | 1      | CS      | Case         | 0       | 0               | CS       | CONS_LATAM   |
      | EA  | 1      | EA      | Each         |         | 0               | EA       | CONS_LATAM   |
      | KG  | 1      | KGM     |              |         | 3               | KG       | CONS_LATAM   |
      | DZ  | 1      | DZ      | Dozen        |         |                 | DZ       | CONS_LATAM   |
      | KM  | 1      | KMT     | Kilometer    |         | 3               | KM       | CONS_LATAM   |
      | L   | 1      | LTR     | Liter        |         | 3               | L        | CONS_LATAM   |
      | M2  | 1      | MTK     | Square meter |         | 3               | M2       | CONS_LATAM   |
      | TS  | 1      | MIL     | Thousands    |         | 3               | TH       | BTC          |
      | PAL | 1      | PL      |              |         | 0               | PAL      | CONS_LATAM   |

    And I wait "/edm/unit_of_measure_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmUnitMeasurable.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMUnit_measure.tsv"

    Then I check file data for filename "GDMUnit_measure.tsv" by keyFields "unitId"
      | unitId | active | activeFCTERP | activeOPRERP | activeSOPERP | factor | isoCode | longDescription     | measure | precision | shortDescription    |
      | CA     | YES    | YES          | YES          | NO           | 2      | CS      | Case                | 0       | 0         | Case                |
      | EA     | YES    | YES          | YES          | NO           | 2      | EA      | Each                | AAAADL  | 0         | Each                |
      | KG     | YES    | NO           | YES          | NO           | 2      | KGM     | KiloGram            | AAAADL  | 3         | KiloGram            |
      | LA_ZUM | YES    | YES          | NO           | NO           | 1      |         | Market Control Unit | AAAADL  | 6         | Market Control Unit |
      | DZ     | YES    | NO           | YES          | NO           | 2      | DZ      | Dozen               | AAAADL  | 3         | Dozen               |
      | KM     | YES    | NO           | YES          | NO           | 2      | KMT     | Kilometer           | AAAADL  | 3         | Kilometer           |
      | L      | YES    | NO           | YES          | NO           | 2      | LTR     | Liter               | AAAADL  | 3         | Liter               |
      | M2     | YES    | NO           | YES          | NO           | 2      | MTK     | Square meter        | AAAADL  | 3         | Square meter        |
      | TS     | YES    | NO           | YES          | NO           | 2      |         | Thousand            | AAAADL  | 6         | Thousand            |
      | PAL    | YES    | NO           | YES          | NO           | 2      | PL      | Pallet              | AAAADL  | 0         | Pallet              |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | errorCode | functionalArea | interfaceID | key1 | key2 | key3 | key4 | key5 | errorValue | sourceSystem |

#    And I compare the number of records between "/edm/unit_of_measure_v1" and "/omp/gdm_unit,/plan/edm_failed_data"

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/omp/gdm_unit"

    And I will remove all data with region "/plan/edm_failed_data"

  Scenario: merge file

    When I execute xd job to merge file "GDMUnit_*" to "GDMUnit.tsv" by keyFields "unitId"

    Then I check file data for filename "GDMUnit.tsv" by keyFields "unitId"
      | unitId | active | activeFCTERP | activeOPRERP | activeSOPERP | factor | isoCode | longDescription     | measure  | precision | shortDescription    |
      | USD    | YES    | YES          | YES          | NO           | 1.0    |         | US Dollar           | CURRENCY | 0         | US Dollar           |
      | CA     | YES    | YES          | YES          | NO           | 2      | CS      | Case                | 0        | 0         | Case                |
      | EA     | YES    | YES          | YES          | NO           | 2      | EA      | Each                | AAAADL   | 0         | Each                |
      | KG     | YES    | NO           | YES          | NO           | 2      | KGM     | KiloGram            | AAAADL   | 3         | KiloGram            |
      | LA_ZUM | YES    | YES          | NO           | NO           | 1      |         | Market Control Unit | AAAADL   | 6         | Market Control Unit |
      | DZ     | YES    | NO           | YES          | NO           | 2      | DZ      | Dozen               | AAAADL   | 3         | Dozen               |
      | KM     | YES    | NO           | YES          | NO           | 2      | KMT     | Kilometer           | AAAADL   | 3         | Kilometer           |
      | L      | YES    | NO           | YES          | NO           | 2      | LTR     | Liter               | AAAADL   | 3         | Liter               |
      | M2     | YES    | NO           | YES          | NO           | 2      | MTK     | Square meter        | AAAADL   | 3         | Square meter        |
      | TS     | YES    | NO           | YES          | NO           | 2      |         | Thousand            | AAAADL   | 6         | Thousand            |
      | PAL    | YES    | NO           | YES          | NO           | 2      | PL      | Pallet              | AAAADL   | 0         | Pallet              |
