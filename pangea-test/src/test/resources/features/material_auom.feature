@pangea_test
Feature: EDMMaterialAUOM-Curation

  Scenario: Full Load curation
    #  1. test join to materialGlobal to get materialNumber (rule J1)

    #  Given: The data is available in the source region(s) as defined in the mapping document ""DOMD_EDM_materialAUOM_CON_LATAM""
    Given I import "/project_one/marm" by keyFields "matnr,meinh"
      |matnr|meinh|umrez|umren|
      |97568|BAG  |1    |20   |
      |97570|EA   |1000 |1500 |

    And I import "/ngems/source_system" by keyFields "localSourceSystem"
      |localSourceSystem|localSourceSystemName|sourceSystem|sourceSystemName  |
      |project_one      |Project One          |CONS_LATAM  |Consumer Latam Ent|
      |[EMS]            |EMS                  |EMS         |EMS Ent           |

    And I import "/edm/material_global" by keyFields "sourceSystem,localMaterialNumber"
      |sourceSystem|localMaterialNumber|materialNumber|
      |CONS_LATAM  |97568              |7891010014803 |
      |EMS         |97570              |7891010931582 |

    #  When: The full load curation job is triggered.
    When I submit task with xml file "xml/material_auom.xml" and execute file "jar/pangea-view.jar"

    #  Then:
    #  1. EDG has all the curated MaterialAUOM records generated in the target view with all the fields as defined in the mapping document ""DOMD_EDM_materialAUOM_CON_LATAM""
    #  2. For each curated record, all the field mappings and transformation rules are applied as defined in the mapping document.
    Then I check region data "/edm/material_auom" by keyFields "sourceSystem,localMaterialNumber,localAuom"
      |sourceSystem|localMaterialNumber|localAuom|materialNumber|localNumerator|localDenominator|Auom|numerator|denominator|
      |CONS_LATAM  |97568              |BAG      |7891010014803 |1             |20              |    |         |           |

    #  4. EDG should capture the rejected records that do not match the mapping rules.
    #  5. The sum of curated records and rejected records should match the number of records in the MARM table.
    And I compare the number of records between "/project_one/marm" and "/edm/material_auom,/edm/material_auom_failed"

