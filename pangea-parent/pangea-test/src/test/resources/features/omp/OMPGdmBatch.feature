@pangea_test @AEAZ-2374
Feature: OMPGdmBatch AEAZ-2374

  Scenario: Full Load curation
    #1. get atrributes from material_global_v1(rule N2)

    Given I import "/edm/batch_master_v1" by keyFields "localMaterialNumber,localBatchNumber"
      | localMaterialNumber | localBatchNumber | localBatchExpDate | localBatchMfgDate | materialNumber |
      | 001                 | 002              | 20150708          | 20150708          | 003            |
      | 005                 | 003              | 20150708          | 20150708          | 003            |
      | 006                 | 004              | 20150708          | 20150708          | 003            |
      | 007                 | 005              | 20150708          | 20150708          | 003            |

    And I wait "/edm/batch_master_v1" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber"
      | localMaterialNumber | materialNumber | primaryPlanningCode |
      | 001                 | 004            |                     |
      | 005                 | 004            | 004                 |
      | 006                 | 004            | 005                 |
      | 007                 |                |                     |
    And I wait "/edm/material_global_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmBatch.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/omp/gdm_batch" by keyFields "batchId,productId"
      | batchId | active | activeOPRERP | activeSOPERP | expirationDate | manufacturingDate | productId |
      | 002     | YES    | YES          | YES          | 20150708       | 20150708          | 004       |
      | 003     | YES    | YES          | YES          | 20150708       | 20150708          | 004       |
      | 004     | YES    | YES          | YES          | 20150708       | 20150708          | 005       |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |
      | SP             | OMPGdmBatch | N2        |              |              | 007  | 005  |      |      |      |            |

#    And I compare the number of records between "/edm/batch_master_v1" and "/omp/gdm_batch,/plan/edm_failed_data"
#
#    And I delete the test data
#
#    And I will remove all data with region "/omp/gdm_batch"
#
#    And I will remove all data with region "/plan/edm_failed_data"

