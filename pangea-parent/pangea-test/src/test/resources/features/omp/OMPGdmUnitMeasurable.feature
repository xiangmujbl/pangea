@pangea_test @AEAZ-1980
Feature: OMPGdmUnitMeasurable AEAZ-1980

  Scenario: Full Load curation
    #  1. test get unit from cns_plan_unit (rule T1)
    #  2. test failed data cns_plan_unit-localUom <> unit_of_measure_v1-localUom

    Given I import "/edm/unit_of_measure_v1" by keyFields "localUom,sourceSystem"
      | uom | factor | isoCode | uomName         | measure | roundingDecimal | localUom | sourceSystem |
      | EA  |        | EA      | -               | -       |                 | EA       | CON_LATAM    |
      | %O2 |        | EA      | %LC, %label clm | -       |                 | %0       | CON_LATAM    |

    And I wait "/edm/unit_of_measure_v1" Async Queue complete

    Given I import "/plan/cns_plan_unit" by keyFields "localUom,localUomName,plantFlag,sourceSystem,unit"
      | localUom | localUomName | plantFlag | sourceSystem | unit |
      | EA       | Each         | DPSP      | CONS_LATAM   | EA   |

    And I wait "/plan/cns_plan_unit" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmUnitMeasurable.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/omp/gdm_unit" by keyFields "unitId"
      | unitId | active | activeFCTERP | activeOPRERP | activeSOPERP | factor | isoCode | longDescription | measure | precision | shortDescription |
      | EA     | YES    | YES          | YES          | YES          |        | EA      | -               | -       |           | -                |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | errorCode | functionalArea | interfaceID       | key1 | key2      | key3 | key4 | key5 | errorValue                              | sourceSystem |
      | T1        | SP             | GdmUnitMeasurable | %0   | CON_LATAM |      |      |      | Enterprise UOM is missing for local UOM |              |

    And I compare the number of records between "/edm/unit_of_measure_v1" and "/omp/gdm_unit,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/omp/gdm_unit"

    And I will remove all data with region "/plan/edm_failed_data"

