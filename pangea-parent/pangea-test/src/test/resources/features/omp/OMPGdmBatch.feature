@pangea_test @AEAZ-2374
Feature: OMPGdmBatch AEAZ-2374

  Scenario: Full Load curation
    #1. get atrributes from material_global_v1(rule N2)

    Given I import "/edm/batch_master_v1" by keyFields "localBatchNumber"
      | localBatchNumber   | localBatchExpDate | localBatchMfgDate | srcSysCd  | matlId     | locPrtyId        |
      | BA001              | 20150708          | 20180708          | 18041801  | 22041801   | BA001_22041801   |
      | BA002              | 20160708          | 20180708          | 18041802  | 22041802   | BA002_22041802   |
      | BA003              | 20160808          | 20180708          | 18041803  | 22041803   | BA003_22041803   |
      | BA004              | 20160908          | 20180708          | 18041804  | 22041804   | BA004_22041804   |

    And I wait "/edm/batch_master_v1" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber"
      | localMaterialNumber | materialNumber | primaryPlanningCode |
      | 22041801            | ZL041801       |                     |
      | 22041802            |                |                     |
      | 22041803            |                |                     |
      | 22041804            | ZL041804       | ZL041804            |
    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/plan/cns_material_plan_status" by keyFields "localMaterialNumber,localPlant,sourceSystem"
      | sourceSystem | localMaterialNumber | localPlant        | spRelevant | noPlanRelevant |
      | 18041801     | 22041801            | BA001_22041801    | X          | X              |
      | 18041802     | 22041802            | BA002_22041802    | X          | Y              |
      | 18041803     | 22041803            | BA003_22041803    | Y          | X              |
      | 18041804     | 22041804            | BA004_22041804    | X          | X              |
    And I wait "/plan/cns_material_plan_status" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmBatch.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/omp/gdm_batch" by keyFields "batchId,productId"
      | batchId                                   | active | activeOPRERP | activeSOPERP | expirationDate | manufacturingDate | productId |
      | ZL041801/BA001_22041801_18041801/BA001    | YES    | YES          | NO           | 20150708       | 20180708          | ZL041801  |
      | ZL041804/BA004_22041804_18041804/BA004    | YES    | YES          | NO           | 20160908       | 20180708          | ZL041804  |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1  | key2  | key3 | key4 | key5 | errorValue |
      | SP             | OMPGdmBatch | N2        |              |              | BA002 |       |      |      |      |            |
      | SP             | OMPGdmBatch | N2        |              |              | BA003 |       |      |      |      |            |

    And I compare the number of records between "/edm/batch_master_v1" and "/omp/gdm_batch,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/omp/gdm_batch"

    And I will remove all data with region "/plan/edm_failed_data"

