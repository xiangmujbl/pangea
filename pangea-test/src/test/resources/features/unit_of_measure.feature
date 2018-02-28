@pangea_test
Feature: EDMUnitOfMeasure-Curation

  Scenario: Full Load curation
    #  1. test filter zSourceSystem <> '[EMS]'
    #  2. test get sourceSystem from source_system_v1 (rule T1)
    #  3. test get mdm_name as uomName from ems_f_mdm_units and if initial, leave blank ( rule T2 )

    Given I import "/ems/ems_f_mdm_units" by keyFields "zSourceSystem,mdmSapCode"
      | zSourceSystem | mdmSapCode | mdmName                  | zEnterpriseCode | mdmIsoCode | zUnitsDimension |
      | project_one   | %0         | Per 1000                 | %O2             | -          | -               |
      | project_two   | %LC        | %LC, Percent label claim | N/A             | -          | -               |
      | [EMS]         | %O2        | %LC, %label clm          | %LC             | -          | -               |
    And I wait "/ems/ems_f_mdm_units" Async Queue complete

    And I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | localSourceSystemName | sourceSystem | sourceSystemName   |
      | project_one       | Project One           | CONS_LATAM   | Consumer Latam Ent |
      | [EMS]             | EMS                   | EMS          | EMS Ent            |
      | project_two       | Project Two           | CONS_LATAM   | Consumer Latam Ent |
    And I wait "/edm/source_system_v1" Async Queue complete

    When I submit task with xml file "xml/unit_of_measure.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/unit_of_measure_v1" by keyFields "sourceSystem,localUom"
      | sourceSystem | localUom | localUomName             | uom | uomName         | isocode | measure | factor | roundingDecimal |
      | CONS_LATAM   | %0       | Per 1000                 | %O2   |  %LC, %label clm | -       | -       |        |                 |
      | CONS_LATAM   | %LC      | %LC, Percent label claim | N/A |                  | -       | -       |        |                 |


#    And I compare the number of records between "/ems/ems_f_mdm_units" and "/edm/unit_of_measure_v1,/edm/unit_of_measure_v1_failed"