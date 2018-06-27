@pangea_test @AEAZ-7319
Feature: EDMUnitOfMeasure AEAZ-7319

  Scenario: Full Load curation
    #  1. test filter zSourceSystem <> '[EMS]'
    #  2. test get sourceSystem from source_system_v1 (rule T1)
    #  3. test get mdm_name as uomName from ems_f_mdm_units and if initial, leave blank  rule T2 )



    Given I import "/ems/ems_f_mdm_units" by keyFields "zSourceSystem,mdmSapCode"
      | zSourceSystem    | mdmSapCode | mdmName         | zEnterpriseCode | mdmIsoCode | zUnitsDimension |
      | [EMS]            |            | ZUM, Mkt Ctr Gr |                 |            |                 |
      | [EMS]            | KG         | KG, kg          | KG              | KGM        | MASS            |
      | [EMS]            | PAL        | PAL, Pallet     | PAL             | PL         | AAAADL          |
      | [EMS]            | EA         | EA, Each        | EA              | EA         | AAAADL          |
      | [Consumer LATAM] | ZUM        | ZUM, Mkt Ctr Gr |                 |            |                 |
      | [Consumer LATAM] | KG         | KG, kg          | KG              | KGM        |                 |
      | [Consumer LATAM] | PAL        | PAL, Pallet     | PAL             | PL         |                 |
      | [Consumer LATAM] | EA         | EA, Each        | EA              | EA         |                 |
    And I wait "/ems/ems_f_mdm_units" Async Queue complete

    And I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | localSourceSystemName | sourceSystem | sourceSystemName   |
      | project_one       | Project One           | CONS_LATAM   | Consumer Latam Ent |
      | [EMS]             | EMS                   | EMS          | EMS Ent            |
      | [Consumer LATAM]  | Project Two           | CONS_LATAM   | Consumer Latam Ent |
    And I wait "/edm/source_system_v1" Async Queue complete

    And I import "/ems/edm_unit_input" by keyFields "sourceSystem,localUom"
      | sourceSystem       | localUom | roundingDecimal | factor |
      | [Consumer LATAM]   | CRT      | 0               | 1      |
      | [Consumer LATAM]   | EA       | 0               | 1      |
      | [Consumer LATAM]   | KG       | 0               | 1      |
      | [Consumer LATAM]   | ZUM      | 0               | 2      |
      | APAC_ECC           | PC       | 0               | 1      |

    And I wait "/ems/edm_unit_input" Async Queue complete

    When I submit task with xml file "xml/edm/unit_of_measure.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/unit_of_measure_v1" by keyFields "sourceSystem,localUom"
      | sourceSystem | localUom | localUomName             | uom | uomName         | isoCode | measure | factor | roundingDecimal  |
      | CONS_LATAM   | ZUM      | ZUM, Mkt Ctr Gr          |     | ZUM, Mkt Ctr Gr |         |         |   2    |      0           |
      | CONS_LATAM   | KG       | KG, kg                   |KG   | KG, kg          |KGM      | MASS    |   1    |      0           |
      | CONS_LATAM   | PAL      | PAL, Pallet              |PAL  | PAL, Pallet     |PL       | AAAADL  |        |                  |
      | CONS_LATAM   | EA       | EA, Each                 |EA   | EA, Each        |EA       | AAAADL  |   1    |      0           |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

#    And I compare the number of records between "/ems/ems_f_mdm_units" and "/edm/unit_of_measure_v1,/plan/edm_failed_data"

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/edm/unit_of_measure_v1"

    And I will remove all data with region "/plan/edm_failed_data"

