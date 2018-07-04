@pangea @AEAZ-6187
Feature: OMPGdmStockInventoryStocks AEAZ-6187

  @Scenario1
  Scenario: test scenario 1

    #T1-Scenario1:Full of consumption

    And I will remove the test file on sink application "GDMStock_InventoryStocks.tsv"

    Given I import "/edm/material_plant_v1" by keyFields "localMaterialNumber,localPlant,sourceSystem"
      | localBatchManagementRequirementIndicator | localMaterialNumber | localPlant | sourceSystem |
      | X                                        | 132264              | CO01       | CONS_LATAM   |
      |                                          | 60414               | EC01       | CONS_LATAM   |
      |                                          | 57039               | CR01       | CONS_LATAM   |
      |                                          | 174461              | CO01       | CONS_LATAM   |
      | X                                        | 215161              | CO01       | CONS_LATAM   |

    And I wait "/edm/material_plant_v1" Async Queue complete

    Given I import "/edm/inventory_stock_v1" by keyFields "sourceSystem,localPlant,localMaterial,localStorageLocation,localBatchId,localVendorNumber,localSpecialStockIndicator,localConsignmentSpecialStockIndicator"
      | sourceSystem | localPlant | localMaterial | localStorageLocation | localBatchId | localVendorNumber | localSpecialStockIndicator | localConsignmentSpecialStockIndicator | localUnrestrictedStock | localUnrestrictedSpecialStock | localUnrestrictedBatchStock | localUnrestrictedConsignmentStock | localQualityInspectionStock | localQualityInspectionSpecialStock | localQualityInspectionBatchStock | localQualityInspectionConsignmentStock | localRestrictedStock | localRestrictedSpecialStock | localRestrictedBatchStock | localRestrictedConsignmentStock | localBlockedStock | localBlockedBatchStock | localBlockedConsignmentStock | localReturnsStock | localReturnsBatchStock | localStockInTransitStorageLocation | localStockInTransitPlantToPlant | localStockInTransitPlant | localStockInTransitSpecial | localStockInTransitBatch | localRestrictedUseConsignment | localConsignmentStockInQualityInspection | localUnrestrictedUseConsignment | localBlkdConstStkNonBm | localTotalStockAllRestrictedBatches |
      | CONS_LATAM   | CO01       | 132264        | CO01                 | 0027LKE30    | 15574             |                            |                                       | 280                    | 108                           | 1000                        | 25200                             | 160                         | 0                                  | 1                                | 0                                      | 0                    | 0                           | 0                         | 0                               | 2                 | 650                    | 0                            | 0                 | 0                      | 0                                  | 0                               | 0                        | 0                          | 0                        | 0                             | 0                                        | 0                               | 0                      | 0                                   |
      | CONS_LATAM   | CO01       | 132264        | CO02                 | 0027LKE30    | 15574             |                            |                                       | 280                    | 108                           | 1000                        | 25200                             | 160                         | 0                                  | 1                                | 0                                      | 0                    | 0                           | 0                         | 0                               | 2                 | 650                    | 0                            | 0                 | 0                      | 0                                  | 0                               | 0                        | 0                          | 0                        | 0                             | 0                                        | 0                               | 0                      | 0                                   |
      | CONS_LATAM   | EC01       | 60414         | EC01                 | 0027LKH24    | 19574             | O                          |                                       | 280                    | 103.000                       | 458                         | 71400.000                         | 160                         | 0.000                              | 1                                | 0.000                                  | 0                    | 0.000                       | 0                         | 0.000                           | 1                 | 416                    | 0.000                        | 0                 | 0                      | 0                                  | 0                               | 0                        | 50.000                     | 100                      | 0                             | 0                                        | 0                               | 0                      | 0                                   |
      | CONS_LATAM   | CR01       | 57039         | CR01                 | 0027LKH27    | 36328             |                            | K                                     | 1785                   | 103.000                       | 300                         | 307098.000                        | 175                         | 0.000                              | 1                                | 10.000                                 | 0                    | 0.000                       | 0                         | 5.000                           | 0.986             | 108                    | 20.000                       | 0                 | 0                      | 0                                  | 0                               | 0                        | 0.000                      | 0                        | 0                             | 0                                        | 0                               | 0                      | 0                                   |
      | CONS_LATAM   | CO01       | 174461        | CO07                 | 0027LKH28    | 15574             |                            |                                       | 1788                   | 104.900                       | 200                         | 440456.000                        | 175                         | 0.000                              | 1                                | 0.000                                  | 0                    | 0.000                       | 0                         | 0.000                           | 1                 | 120                    | 0.000                        | 0                 | 0                      | 0                                  | 0                               | 0                        | 0.000                      | 0                        | 0                             | 0                                        | 0                               | 0                      | 0                                   |
      | CONS_LATAM   | CO01       | 215161        | CO08                 | 0027LKH29    | 15574             | O                          |                                       | 279                    | 107.000                       | 200                         | 60.000                            | 0                           | 0.000                              | 1                                | 0.000                                  | 0                    | 0.000                       | 0                         | 0.000                           | 3                 | 108                    | 0.000                        | 0                 | 0                      | 0                                  | 0                               | 0                        | 0.000                      | 0                        | 0                             | 0                                        | 32                              | 0                      | 0                                   |

    And I wait "/edm/inventory_stock_v1" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber,sourceSystem"
      | sourceSystem | localMaterialNumber | materialNumber | primaryPlanningCode |
      | CONS_LATAM   | 132264              | 132264         |                     |
      | CONS_LATAM   | 57039               | 57039          |                     |
      | CONS_LATAM   | 60414               | 60414          |                     |
      | CONS_LATAM   | 215161              | 215161         |                     |
      | CONS_LATAM   | 174461              | 174461         |                     |

    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/plan/cns_spl_pln_loc" by keyFields "localName,localNumber,sourceSystem,vendorOrCustomer"
      | localName                       | localNumber | sourceSystem | vendorOrCustomer | localPlant |
      | ASPRILLA ORTIZ FABIO            | 15574       | CONS_LATAM   | V                | CO01       |
      | LODISAL S.A.                    | 19574       | CONS_LATAM   | V                | EC01       |
      | D.H.L. COSTA RICA (CORMAR) S.A. | 36328       | CONS_LATAM   | V                | CR01       |

    And I wait "/plan/cns_spl_pln_loc" Async Queue complete

    Given I import "/plan/cns_material_plan_status" by keyFields "localMaterialNumber,localPlant,sourceSystem"
      | localMaterialNumber | localPlant | noPlanRelevant | sourceSystem | spRelevant |
      | 132264              | CO01       |                | CONS_LATAM   | X          |
      | 60414               | EC01       |                | CONS_LATAM   | X          |
      | 57039               | CR01       |                | CONS_LATAM   | X          |
      | 174461              | CO01       | X              | CONS_LATAM   |            |
      | 215161              | CO01       | X              | CONS_LATAM   |            |

    And I wait "/plan/cns_material_plan_status" Async Queue complete

    Given I import "/edm/plant_v1" by keyFields "localPlant,sourceSystem"
      | localPlanningRelevant | localPlant | sourceSystem |
      | X                     | CO01       | CONS_LATAM   |
      | X                     | EC01       | CONS_LATAM   |
      | X                     | CR01       | CONS_LATAM   |

    And I wait "/edm/plant_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmStockInventoryStocks.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMStock_InventoryStocks.tsv"

    Then I check file data for filename "GDMStock_InventoryStocks.tsv" by keyFields "stockId,consignment"
      | transitDate         | processTypeId | returnsQuantity | inventoryLinkGroupId | batchId   | activeOPRERP | activeSOPERP | transferQuantity | qualityQuantity | processId | locationId            | consignment | certaintyID | quantity | productId | stockType | receiptDate         | active | eRPOrderId | lifnr | blockedQuantity | unrestrictedQuantity | stockId                                | restrictedQuantity | startDate           |
      | 2018/06/25 00:00:00 |               | 0.0             |                      | 0027LKH28 | YES          | NO           | 0.0              | 175.0           |           | CONS_LATAM_CO01       |             |             | 1788.0   | 174461    | level     | 2018/06/25 00:00:00 | YES    |            |       | 1.0             | 1788.0               | 174461/CONS_LATAM_CO01/0027LKH28       | 0.0                | 2018/06/25 00:00:00 |
      | 2018/06/25 00:00:00 |               | 0.0             |                      | 0027LKH29 | YES          | NO           | 0.0              | 0.0             |           | CONS_LATAM_CO01$15574 |             |             | 107.0    | 215161    | level     | 2018/06/25 00:00:00 | YES    |            | O     | 0.0             | 107.0                | 215161/CONS_LATAM_CO01$15574/0027LKH29 | 0.0                | 2018/06/25 00:00:00 |
      | 2018/06/25 00:00:00 |               | 0.0             |                      | 0027LKH24 | YES          | NO           | 50.0             | 0.0             |           | CONS_LATAM_EC01$19574 |             |             | 103.0    | 60414     | level     | 2018/06/25 00:00:00 | YES    |            | O     | 0.0             | 103.0                | 60414/CONS_LATAM_EC01$19574/0027LKH24  | 0.0                | 2018/06/25 00:00:00 |
      | 2018/06/25 00:00:00 |               | 0.0             |                      | 0027LKE30 | YES          | NO           | 0.0              | 2.0             |           | CONS_LATAM_CO01       |             |             | 2000.0   | 132264    | level     | 2018/06/25 00:00:00 | YES    |            |       | 1300.0          | 2000.0               | 132264/CONS_LATAM_CO01/0027LKE30       | 0.0                | 2018/06/25 00:00:00 |
      | 2018/06/25 00:00:00 |               | 0.0             |                      | 0027LKE30 | YES          | NO           | 0.0              | 2.0             |           | CONS_LATAM_CO01       |             |             | 2000.0   | 132264    | level     | 2018/06/25 00:00:00 | YES    |            |       | 1300.0          | 2000.0               | 132264/CONS_LATAM_CO01/0027LKE30       | 0.0                | 2018/06/25 00:00:00 |

    And I delete the test data

    And I will remove all data with region "/omp/gdm_stock"


    
        