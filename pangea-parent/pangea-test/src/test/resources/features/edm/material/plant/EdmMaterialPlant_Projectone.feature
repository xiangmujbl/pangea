@pangea_test
Feature: EDMMaterialPlant

  Scenario: Full Load curation

    Given I import "/project_one/marc" by keyFields "matnr,werks"
      | matnr      | werks | mmsta |
      | 0000000001 | BR01  | 04    |
      | 0000000001 | BR02  | 05    |
      | 0000000001 | BR03  | 06    |
      | 0000000002 | BR04  | 07    |
      | 0000000003 | BR05  | 08    |
      | 0000000004 | BR06  | 09    |
      | 0000000004 | BR07  | 10    |
    And I wait "/project_one/marc" Async Queue complete

    And I import "/pangea/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | sourceSystem |
      | project_one       | CONS_LATAM   |
      | Project_one       | EMS          |
    And I wait "/pangea/edm/source_system_v1" Async Queue complete

    And I import "/pangea/edm/material_global_v1" by keyFields "localMaterialNumber,sourceSystem"
      | localMaterialNumber | sourceSystem | materialNumber |
      | 0000000001          | CONS_LATAM   | 7891010010001  |
      | 0000000002          | CONS_LATAM   | 7891010010002  |
      | 0000000003          | latam1       | 7891010010003  |
      | 0000000004          | EMS          | 7891010010004  |
      | 0000000005          | CONS_LATAM   | 7891010010005  |
    And I wait "/pangea/edm/material_global_v1" Async Queue complete

    And I import "/pangea/edm/plant_v1" by keyFields "sourceSystem,localPlant"
      | sourceSystem | localPlant | plant |
      | CONS_LATAM   | BR01       | 00001 |
      | CONS_LATAM   | BR02       | 00002 |
      | cons         | BR03       | 00003 |
      | EMS          | BR04       | 00004 |
      | CONS_LATAM   | BR05       | 00005 |
      | CONS         | BR06       | 00006 |
      | EMS          | BR07       | 00007 |
      | Project_one  | BR08       | 00008 |
    And I wait "/pangea/edm/plant_v1" Async Queue complete

    And I import "/pangea/edm/mat_plant_stat_v1" by keyFields "sourceSystem,localPlantStatus"
      | sourceSystem | localPlantStatus | plantStatus |
      | CONS_LATAM   | 04               | 100         |
      | CONS_LATAM   | 05               | 101         |
      | CONS_LATAM   | 06               | 102         |
      | Project_one  | 07               | 103         |
      | CONS_LATAM   | 08               | 104         |
      | CONS_LATAM   | 09               | 105         |
      | EMS          | 10               | 106         |
    And I wait "/pangea/edm/mat_plant_stat_v1" Async Queue complete

    When I submit task with xml file "xml/edm/material/plant/EDMMaterialPlant_Projectone.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/pangea/edm/material_plant_v1" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | materialNumber | plant | localPlantStatus | plantStatus |
      | CONS_LATAM   | 0000000001          | BR01       | 7891010010001  | 00001 | 04               | 100         |
      | CONS_LATAM   | 0000000001          | BR02       | 7891010010001  | 00002 | 05               | 101         |
      | CONS_LATAM   | 0000000001          | BR03       | 7891010010001  |       | 06               | 102         |
      | CONS_LATAM   | 0000000002          | BR04       | 7891010010002  |       | 07               |             |
      | CONS_LATAM   | 0000000003          | BR05       |                | 00005 | 08               | 104         |
      | CONS_LATAM   | 0000000004          | BR06       |                |       | 09               | 105         |
      | CONS_LATAM   | 0000000004          | BR07       |                |       | 10               |             |

    And I delete the test data

    And I will remove all data with region "/pangea/edm/material_plant_v1"




