@pangea_test @AEAZ-123
Feature: AEAZ-123 EDMBatchMaster-Curation

  Scenario: Full Load curation

    Given I import "/project_one/mch1" by keyFields "matnr,charg"
      | matnr              | charg      | vfdat    | hsdat    |
      | 000000000007703910 | 0 190GB 01 | 00000000 | 00000000 |
      | 000000000007047792 | 0 603B7 A  | 00000000 | 00000000 |
      | 000000000007502834 | 0.10307    | 00000000 | 00000000 |
    And I wait "/project_one/mch1" Async Queue complete

    And I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | localSourceSystemName    | sourceSystem | sourceSystemName   |
      | [MDD FASE]        | [MDD FASE]               | CONS_LATAM   | Consumer Latam Ent |
      | [EMS]             | EMS                      | EMS          | EMS Ent            |
      | project_one       | [MD Synthes SAP Anspach] | CONS_LATAM   | Consumer Latam Ent |
    And I wait "/edm/source_system_v1" Async Queue complete

    And I import "/edm/material_global_v1" by keyFields "localMaterialNumber,sourceSystem"
      | localMaterialNumber | sourceSystem | materialNumber |
      | [EMS]               | EMS          | EMS            |
      | 000000000007703910  | CONS_LATAM   | CONS_LATAM     |
      | 000000000007703910  | TQA          | TQA            |
    And I wait "/edm/material_global_v1" Async Queue complete

    When I submit task with xml file "xml/edm/EDMBatchMaster.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/batch_master_v1" by keyFields "sourceSystem,localMaterialNumber,materialNumber,localBatchNumber"
      | sourceSystem | localMaterialNumber | materialNumber | localBatchNumber | localShelfLifeExpiration | localDateofManufacture |
      | CONS_LATAM   | 000000000007703910  | CONS_LATAM     | 0 190GB 01       | 00000000                 | 00000000               |
      | CONS_LATAM   | 000000000007047792  |                 | 0 603B7 A        | 00000000                 | 00000000               |
      | CONS_LATAM   | 000000000007502834  |                 | 0.10307          | 00000000                 | 00000000               |


    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |



    And I compare the number of records between "/project_one/mch1" and "/edm/batch_master_v1,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/edm/batch_master_v1"

    And I will remove all data with region "/plan/edm_failed_data"


