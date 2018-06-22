@pangea_test @AEAZ-5865
Feature: OMPGdmBatch AEAZ-5865

  Scenario: Full Load consumption
    # 1. Send 'YES' to Active,ActiveOPRERP (rule N1)
    # 2. Send 'NO' to ActiveSOPERP (rule N4)
    # 3. Date format needs to be YYYY/MM/DD HH:NN:SS (where HH:NN:SS = 00:00:00) for expirationDate,manufacturingDate (rule N5)
    # 4. if (cns_material_plan_status_v1-sourceSystem) <> (batch_master_v1-srcSysCd), skip the record (rule N2)
    # 5. if (cns_material_plan_status_v1_localMaterialNumber) <> (batch_master_v1-matlNum), skip the record (rule N2)
    # 6. if (cns_material_plan_status_v1_localPlant) <> (batch_master_v1-localPlant), skip the record (rule N2)
    # 7. if (cns_material_plan_status_v1-spRelevant) <> X and (cns_material_plan_status_v1-noPlanRelevant) <> X, skip the record (rule N2)
    # 8. If (material_global_v1-primaryPlanningCode) is Blank then send (material_global_v1-MaterialNumber) to productId (rule N2)
    # 9. If (material_global_v1-primaryPlanningCode) =  (material_global_v1-MaterialNumber) send (material_global_v1-primaryPlanningCode) to productId (rule N2)
    # 10.If (material_global_v1-primaryPlanningCode) not equal to  (material_global_v1-MaterialNumber) then skip the record (rule N2)
    # 11.If (material_global_v1-MaterialNumber) not available, reject the record (rule N2)
    # 12.If (material_global_v1-PrimaryPlanningCode) is not available, reject the record (rule N2)
    # 13.If ((material_global_v1-Material Number) AND (material_global_v1-PrimaryPlanningCode) is Blank, reject the record (rule N2)
    # 14.If value of all fields with Data Type as "Float"  <= 0, skip the record (N3)

    And I will remove the test file on sink application "GDMBatch.tsv"

    Given I import "/edm/batch_master_v1" by keyFields "btchNum"
      | btchNum | btchExpDt           | btchMfgDt           | materialNumber | localPlant | matlNum            | srcSysCd   |
      | 920B5   | 2008/04/07 00:00:00 | 2006/04/08 00:00:00 | 7047647        | EC01       | 000000000007047647 | CONS_LATAM |

      # skip record
      | 920K5 B | 0000/00/00          | 0000/00/00          | 7090325        | PE01       | 000000000007090325 | CONS_LATAM |
      | 924C6 B | 2008/04/07 00:00:00 | 2006/04/08 00:00:00 | 7047789        | PE01       | 000000000007047789 | CONS_LATAM |
      | 926B5 B | 0000/00/00          | 0000/00/00          | 7045631        | PE01       | 000000000007045631 | CONS_LATAM |
      | 926F6   | 2008/07/08 00:00:00 | 2006/07/08 00:00:00 | 7047632        | EC01       | 000000000007047632 | CONS_LATAM |
      | 930F6 A | 2008/07/06 00:00:00 | 2006/07/07 00:00:00 | 7043867        | PE01       | 000000000007043867 | CONS_LATAM |
      # value of any fields with data type as "Float"  <= 0
      | 90904   | 2008/04/15 00:00:00 | 2006/04/16 00:00:00 | 70365          | EC01       | 000000000000070365 | CONS_LATAM |

      # reject record
      | 927C6 D | 2008/04/28 00:00:00 | 2006/04/29 00:00:00 | 7044811        | PE01       | 000000000007044811 | CONS_LATAM |
      | 90407   | 2034/08/05 00:00:00 | 2007/03/21 00:00:00 | 7502768        | VE02       | 000000000007502768 | CONS_LATAM |

    And I wait "/edm/batch_master_v1" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber"
      | localMaterialNumber | materialNumber | primaryPlanningCode |
      | 000000000007047647  | 7047647        | 7047647             |
      | 000000000000070365  | 70365          | 70365               |

      # (material_global_v1-PrimaryPlanningCode) is not available, reject the record
      | 000000000007044811  | 7044811        |                     |

      # (material_global_v1-primaryPlanningCode) not equal to  (material_global_v1-MaterialNumber) then skip the record
      | 000000000007043867  | 7043867        | 7043866             |

      # (material_global_v1-MaterialNumber) not available, reject the record
      | 000000000007502768  |                | 7502768             |

    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/plan/cns_material_plan_status" by keyFields "localMaterialNumber,localPlant,sourceSystem"
      | sourceSystem | localMaterialNumber | localPlant | spRelevant | noPlanRelevant |
      | CONS_LATAM   | 000000000007047647  | EC01       | X          | X              |
      | CONS_LATAM   | 000000000000070365  | EC01       | X          | X              |
      | CONS_LATAM   | 000000000007044811  | PE01       | X          | X              |
      | CONS_LATAM   | 000000000007043867  | PE01       | X          | X              |
      | CONS_LATAM   | 000000000007502768  | VE02       | X          | X              |

      # (cns_material_plan_status_v1-sourceSystem) <> (batch_master_v1-srcSysCd), skip the record
      | BTC          | 000000000007090325  | PE01       | X          |                |

      # (cns_material_plan_status_v1_localMaterialNumber) <> (batch_master_v1-matlNum), skip the record
      | CONS_LATAM   | 000000000007047788  | PE01       |            | X              |

      # (cns_material_plan_status_v1_localPlant) <> (batch_master_v1-localPlant), skip the record
      | CONS_LATAM   | 000000000007045631  | EC01       | X          | X              |

      # (cns_material_plan_status_v1-spRelevant) <> X and (cns_material_plan_status_v1-noPlanRelevant) <> X, skip the record
      | CONS_LATAM   | 000000000007047632  | EC01       |            |                |

    And I wait "/plan/cns_material_plan_status" Async Queue complete

    Given I import "/edm/inventory_stock" by keyFields "sourceSystem,localBatchId,localMaterial"
      | sourceSystem | localBatchId | localMaterial      | localUnrestrictedStock | localUnrestrictedSpecialStock | localUnrestrictedBatchStock | localUnrestrictedConsignmentStock | localQualityInspectionStock | localQualityInspectionSpecialStock | localQualityInspectionBatchStock | localQualityInspectionConsignmentStock | localRestrictedStock | localRestrictedSpecialStock | localRestrictedBatchStock | localRestrictedConsignmentStock | localBlockedStock | localBlockedBatchStock | localBlockedConsignmentStock | localReturnsStock | localReturnsBatchStock | localStockInTransitStorageLocation | localStockInTransitPlantToPlant | localStockInTransitPlant | localStockInTransitSpecial | localStockInTransitBatch | localRestrictedUseConsignment | localConsignmentStockInQualityInspection | localUnrestrictedUseConsignment | localBlkdConstStkNonBm | localTotalStockAllRestrictedBatches |
      | CONS_LATAM   | 920B5        | 000000000007047647 | 4285.672               | 0.000                         | 0.000                       | 0.000                             | 0.000                       | 0.000                              | 0.000                            | 0.000                                  | 0.000                | 0.000                       | 0.000                     | 0.000                           | 0.000             | 0.000                  | 0.000                        | 0.000             | 0.000                  | 0.000                              | 0.000                           | 0.000                    | 0.000                      | 0.000                    | 0.000                         | 0.000                                    | 0.000                           | 0.000                  | 0.000                               |

      # If value of all fields with Data Type as "Float"  <= 0, skip the record
      | CONS_LATAM   | 90904        | 000000000000070365 | 0.000                  | 0.000                         | 0.000                       | 0.000                             | 0.000                       | 0.000                              | 0.000                            | 0.000                                  | 0.000                | 0.000                       | 0.000                     | 0.000                           | 0.000             | 0.000                  | 0.000                        | 0.000             | 0.000                  | 0.000                              | 0.000                           | 0.000                    | 0.000                      | 0.000                    | 0.000                         | 0.000                                    | 0.000                           | 0.000                  | 0.000                               |

    And I wait "/edm/inventory_stock" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmBatch.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMBatch.tsv"

    Then I check file data for filename "GDMBatch.tsv" by keyFields "batchId,productId"
      | batchId                       | active | activeOPRERP | activeSOPERP | expirationDate      | manufacturingDate   | productId |
      | 7047647/CONS_LATAM_EC01/920B5 | YES    | YES          | NO           | 2008/04/07 00:00:00 | 2006/04/08 00:00:00 | 7047647   |

    Then I check region data "/dev/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1    | key2 | key3 | key4 | key5 | errorValue |
      | SP             | OMPGdmBatch | N2        |              |              | 927C6 D |      |      |      |      |            |
      | SP             | OMPGdmBatch | N2        |              |              | 90407   |      |      |      |      |            |

#    And I compare the number of records between "/edm/batch_master_v1" and "/omp/gdm_batch,/dev/plan/edm_failed_data"

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/omp/gdm_batch"

    And I will remove all data with region "/dev/plan/edm_failed_data"
