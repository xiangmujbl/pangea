@pangea_test @AEAZ-1980
Feature: OMPGdmUnitMeasurable AEAZ-1980

  Scenario: Full Load curation
    # 1. test get uom from unit_of_measure_v1 if it exist in cns_plan_unit-localUom (rule F1)
    # 2. test skip record if unit_of_measure_v1-uom not exist in cns_plan_unit-localUom (rule F1)
    # 3. test if unit_of_measure_v1-uomName is not available, reject record (E1)

    Given I import "/edm/unit_of_measure_v1" by keyFields "localUom,sourceSystem"
      | uom | factor | isoCode | uomName | measure | roundingDecimal | localUom | sourceSystem |
      | EA  | 1      | EA      | Each    | -       | 0               | EA       | CONS_LATAM   |
      | PAL | 1      | PAL     | Pallet  | -       | 0               | PAL      | CONS_LATAM   |
      | %O2 |        | EA      |         | -       |                 | %O2      | CONS_LATAM   |

    And I wait "/edm/unit_of_measure_v1" Async Queue complete

    Given I import "/plan/cns_plan_unit" by keyFields "localUom,localUomName,plantFlag,sourceSystem,unit"
      | localUom | localUomName    | plantFlag | sourceSystem | unit |
      | EA       | Each            | DPSP      | CONS_LATAM   | EA   |
      | %O2      | %LC, %label clm | DPSP      | CONS_LATAM   | %O2  |

    And I wait "/plan/cns_plan_unit" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmUnitMeasurable.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/omp/gdm_unit" by keyFields "unitId"
      | unitId | active | activeFCTERP | activeOPRERP | activeSOPERP | factor | isoCode | longDescription | measure | precision | shortDescription |
      | EA     | YES    | YES          | YES          | NO           | 1      | EA      | Each            | -       | 0         | Each             |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | errorCode | functionalArea | interfaceID       | key1 | key2       | key3 | key4 | key5 | errorValue | sourceSystem |
      | E1        | SP             | GdmUnitMeasurable | %O2  | CONS_LATAM |      |      |      |            | omp          |

#    And I compare the number of records between "/edm/unit_of_measure_v1" and "/omp/gdm_unit,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/omp/gdm_unit"

    And I will remove all data with region "/plan/edm_failed_data"

