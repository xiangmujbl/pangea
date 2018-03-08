@pangea_test @AEAZ-511
Feature: EDMMaterialAUOM-Curation

  Scenario: Full Load curation
    #  1. test get sourceSystem from source_system_v1 (rule T1)
    #  2. test join to materialGlobal to get materialNumber (rule J1)

    Given I import "/project_one/marm" by keyFields "matnr,meinh"
      | matnr | meinh | umrez | umren |
      | 97568 | BAG   | 1     | 20    |
      | 97570 | EA    | 1000  | 1500  |
    And I wait "/project_one/marm" Async Queue complete

    And I import "/pangea/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | localSourceSystemName | sourceSystem | sourceSystemName   |
      | project_one       | Project One           | CONS_LATAM   | Consumer Latam Ent |
      | \[EMS\]           | EMS                   | EMS          | EMS Ent            |
    And I wait "/pangea/edm/source_system_v1" Async Queue complete

    And I import "/pangea/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | sourceSystem | localMaterialNumber | materialNumber |
      | CONS_LATAM   | 97568               | 7891010014803  |
      | EMS          | 97570               | 7891010931582  |
      | EMS1         | 97570               | 7891010931582  |
      | EMS2         | 97570               | 7891010931582  |
      | EMS3         | 97570               | 7891010931582  |
      | EMS4         | 97570               | 7891010931582  |
      | EMS5         | 97570               | 7891010931582  |
      | EMS6         | 97570               | 7891010931582  |
      | EMS7         | 97570               | 7891010931582  |
      | EMS8         | 97570               | 7891010931582  |
      | EMS9         | 97570               | 7891010931582  |
      | EMS90        | 97570               | 7891010931582  |
      | EMS91        | 97570               | 7891010931582  |
      | EMS92        | 97570               | 7891010931582  |
    And I wait "/pangea/edm/material_global_v1" Async Queue complete

    When I submit task with xml file "xml/edm/material/auom/EDMMaterialAUOM_Projectone.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/pangea/edm/material_auom_v1" by keyFields "sourceSystem,localMaterialNumber,materialNumber"
      | sourceSystem | localMaterialNumber | localAuom | materialNumber | localNumerator | localDenominator | Auom | numerator | denominator |
      | CONS_LATAM   | 97568               | BAG       | 7891010014803  | 1              | 20               |      |           |             |

    #And I compare the number of records between "/project_one/marm" and "/edm/material_auom_v1,/edm/material_auom_v1_failed"

    And I delete the test data

    And I will remove all data with region "/pangea/edm/material_auom_v1"