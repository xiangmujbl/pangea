@pangea_test @AEAZ-511
Feature: EDMMaterialAUOM AEAZ-511

  Scenario: Full Load curation
    #  1. test get sourceSystem from source_system_v1 (rule T1)
    #  2. test join to materialGlobal to get materialNumber (rule J1)

    Given I import "/project_one/marm" by keyFields "matnr,meinh"
      | matnr | meinh | umrez | umren |
      | 97568 | KG   | 1     | 20    |
      | 97570 | EA    | 1000  | 1500  |
      | 97571 | KI    | 2000  | 500   |
    And I wait "/project_one/marm" Async Queue complete

    And I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | localSourceSystemName | sourceSystem | sourceSystemName   |
      | project_one       | Project One           | CONS_LATAM   | Consumer Latam Ent |
      | \[EMS\]           | EMS                   | EMS          | EMS Ent            |
    And I wait "/edm/source_system_v1" Async Queue complete

    And I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | sourceSystem | localMaterialNumber | materialNumber |
      | CONS_LATAM   | 97568               | 7891010014803  |
      | CONS_LATAM   | 97570               | 7891010931582  |
      | EMS1         | 97570               | 7891010931582  |
      | EMS2         | 97570               | 7891010931582  |
    And I wait "/edm/material_global_v1" Async Queue complete

    When I submit task with xml file "xml/edm/material/auom/EDMMaterialAUOM_Projectone.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/material_auom_v1" by keyFields "sourceSystem,localMaterialNumber,localAuom"
      |sourceSystem|localMaterialNumber|localAuom|materialNumber|localNumerator|localDenominator|Auom|numerator|denominator|
      |CONS_LATAM  |97568              |KG         |7891010014803 |1          |20              |    |         |           |
      |CONS_LATAM  |97570              |EA         |7891010931582|1000       |1500            |    |         |           |
      |CONS_LATAM  |97571              |KI          |              |2000       |500             |    |         |           |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/project_one/marm" and "/edm/material_auom_v1,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/edm/material_auom_v1"
    And I will remove all data with region "/pangea/edm_failed_data"