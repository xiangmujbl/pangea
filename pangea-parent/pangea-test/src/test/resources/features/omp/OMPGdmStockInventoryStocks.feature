@pangea_test @AEAZ-8896
Feature: OMPGdmStockInventoryStocks AEAZ-8896

  Scenario: Full Load consumption

    And I will remove the test file on sink application "GDMStock_InventoryStocks.tsv"

    Given I import "/edm/material_plant_v1" by keyFields "localMaterialNumber,localPlant,sourceSystem"
      | localBatchManagementRequirementIndicator | localMaterialNumber | localPlant | sourceSystem |
      |                                          | 55735               | CO01       | CONS_LATAM   |
      |                                          | 88360               | CO01       | CONS_LATAM   |
      | X                                        | 174461              | BR12       | CONS_LATAM   |
      | X                                        | 189915              | BR12       | CONS_LATAM   |
      | X                                        | 189915              | BR06       | CONS_LATAM   |
      | X                                        | 60414               | CO01       | CONS_LATAM   |
      | X                                        | 94723               | CO01       | CONS_LATAM   |
      |                                          | 215161              | CO01       | CONS_LATAM   |
      |                                          | 132264              | CO01       | CONS_LATAM   |
      |                                          | 132266              | CO01       | CONS_LATAM   |
      | X                                        | 57039               | BR12       | CONS_LATAM   |
      | X                                        | 132277              | MX01       | CONS_LATAM   |
      | X                                        | 191383              | BR12       | CONS_LATAM   |

    And I wait "/edm/material_plant_v1" Async Queue complete

    Given I import "/edm/inventory_stock_v1" by keyFields "sourceSystem,localPlant,localMaterial,localStorageLocation,localBatchId,localVendorNumber,localSpecialStockIndicator,localConsignmentSpecialStockIndicator"
      | sourceSystem | localPlant | localMaterial | localStorageLocation | localBatchId | localVendorNumber | localConsignmentSpecialStockIndicator | localSpecialStockIndicator | localUnrestrictedStock | localQualityInspectionStock | localRestrictedStock | localBlockedStock | localBlkdConstStkNonBm | localReturnsStock | localStockInTransitPlantToPlant | localRestrictedUseConsignment | localConsignmentStockInQualityInspection | localUnrestrictedUseConsignment | localTotalStockAllRestrictedBatches | localStockInTransitPlant | localUnrestrictedBatchStock | localQualityInspectionBatchStock | localRestrictedBatchStock | localBlockedBatchStock | localReturnsBatchStock | localStockInTransitBatch | localUnrestrictedConsignmentStock | localQualityInspectionConsignmentStock | localRestrictedConsignmentStock | localBlockedConsignmentStock | localUnrestrictedSpecialStock | localQualityInspectionSpecialStock | localRestrictedSpecialStock | localStockInTransitSpecial | localStockInTransitStorageLocation |
      | CONS_LATAM   | CO01       | 55735         | CO01                 |              |                   |                                       |                            | 100                    | 100                         | 100                  | 100               | 100                    | 100               | 100                             | 100                           | 100                                      |                                 | 100                                 | 100                      |                             |                                  |                           |                        |                        |                          |                                   |                                        |                                 |                              |                               |                                    |                             |                            |                                    |
      | CONS_LATAM   | CO01       | 55735         | CO02                 |              |                   |                                       |                            | 60                     | 40                          | 70                   | 50                | 50                     | 80                | 90                              | 50                            | 1                                        |                                 | 50                                  | 50                       |                             |                                  |                           |                        |                        |                          |                                   |                                        |                                 |                              |                               |                                    |                             |                            |                                    |
      | CONS_LATAM   | CO01       | 88360         | CO08                 |              |                   |                                       |                            | 55                     | 55                          | 55                   | 55                | 55                     | 55                | 55                              | 55                            | 55                                       |                                 | 55                                  | 55                       |                             |                                  |                           |                        |                        |                          |                                   |                                        |                                 |                              |                               |                                    |                             |                            |                                    |
      | CONS_LATAM   | BR12       | 174461        | BR01                 | 020405B      |                   |                                       |                            |                        |                             |                      |                   |                        |                   |                                 |                               |                                          |                                 |                                     |                          | 200                         | 200                              | 200                       | 200                    | 1                      | 5                        |                                   |                                        |                                 |                              |                               |                                    |                             |                            |                                    |
      | CONS_LATAM   | BR12       | 174461        | BR02                 | 020405B      |                   |                                       |                            |                        |                             |                      |                   |                        |                   |                                 |                               |                                          |                                 |                                     |                          | 40                          | 50                               | 80                        | 40                     | 1                      | 5                        |                                   |                                        |                                 |                              |                               |                                    |                             |                            |                                    |
      | CONS_LATAM   | BR12       | 174461        | BR02                 | 020405C      |                   |                                       |                            |                        |                             |                      |                   |                        |                   |                                 |                               |                                          |                                 |                                     |                          | 20                          | 20                               | 20                        | 20                     | 3                      | 6                        |                                   |                                        |                                 |                              |                               |                                    |                             |                            |                                    |
      | CONS_LATAM   | BR12       | 189915        | BR02                 | 020405C      |                   |                                       |                            |                        |                             |                      |                   |                        |                   |                                 |                               |                                          |                                 |                                     |                          | 60                          | 60                               | 60                        | 60                     | 4                      | 7                        |                                   |                                        |                                 |                              |                               |                                    |                             |                            |                                    |
      | CONS_LATAM   | BR06       | 189915        | BR02                 | 020405C      |                   |                                       |                            |                        |                             |                      |                   |                        |                   |                                 |                               |                                          |                                 |                                     |                          | 10                          | 10                               | 10                        | 10                     | 0                      | 8                        |                                   |                                        |                                 |                              |                               |                                    |                             |                            |                                    |
      | CONS_LATAM   | CO01       | 60414         | CO01                 | 4000176631   | 16060             | K                                     |                            |                        |                             |                      |                   |                        |                   |                                 |                               |                                          |                                 |                                     |                          |                             |                                  |                           |                        |                        |                          | 300                               | 300                                    | 300                             | 300                          |                               |                                    |                             |                            |                                    |
      | CONS_LATAM   | CO01       | 60414         | CO02                 | 4000176631   | 16060             | K                                     |                            |                        |                             |                      |                   |                        |                   |                                 |                               |                                          |                                 |                                     |                          |                             |                                  |                           |                        |                        |                          | 0                                 | 10                                     | 20                              | 30                           |                               |                                    |                             |                            |                                    |
      | CONS_LATAM   | CO01       | 60414         | CO03                 | 4000176631   | 16061             | K                                     |                            |                        |                             |                      |                   |                        |                   |                                 |                               |                                          |                                 |                                     |                          |                             |                                  |                           |                        |                        |                          | 3                                 | 3                                      | 3                               | 3                            |                               |                                    |                             |                            |                                    |
      | CONS_LATAM   | MX01       | 132277        | MX01                 | 4000176631   | 16061             | K                                     |                            |                        |                             |                      |                   |                        |                   |                                 |                               |                                          |                                 |                                     |                          |                             |                                  |                           |                        |                        |                          | 1                                 | 1                                      | 1                               | 1                            |                               |                                    |                             |                            |                                    |
      | CONS_LATAM   | CO01       | 215161        |                      | B004JH503X   | 15574             |                                       | O                          |                        |                             |                      |                   |                        |                   |                                 |                               |                                          |                                 |                                     |                          |                             |                                  |                           |                        |                        |                          |                                   |                                        |                                 |                              | 600                           | 500                                | 70                          | 60                         |                                    |
      | CONS_LATAM   | CO01       | 215161        |                      | B004JH503X   | 20668             |                                       | O                          |                        |                             |                      |                   |                        |                   |                                 |                               |                                          |                                 |                                     |                          |                             |                                  |                           |                        |                        |                          |                                   |                                        |                                 |                              | 550                           | 550                                | 33                          | 40                         |                                    |
      | CONS_LATAM   | CO01       | 191383        | CO08                 | 10017KAH37   | 10914             |                                       | O                          | 0                      | 0                           | 0                    | 0                 | 0                      | 0                 | 0                               | 0                             | 0                                        | 0                               | 0                                   | 0                        | 0                           | 0                                | 0                         | 0                      | 0                      | 0                        | 0                                 | 0                                      | 0                               | 0                            | 0                             | 0                                  | 0                           | 0                          | 0                                  |


    And I wait "/edm/inventory_stock_v1" Async Queue complete

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | sourceSystem | localSourceSystem |
      | CONS_LATAM   | Project_One       |

    And I wait "/edm/source_system_v1" Async Queue complete


    Given I import "/plan/cns_tlane_control" by keyFields "sequenceNumber,tlaneName"
      | sourceSystemCriticalParameters | sequenceNumber | tlaneName  | trigSysTransaction | trigSysPlant | triangulationDetail |
      | CONS_LATAM                     | 1              | CO01toMX02 | Purchase Order     | MX01         | Yes                 |
      | CONS_LATAM                     | 10             | CO01toCR01 | Purchase Order     | CR02         | Yes                 |
      | CONS_LATAM                     | 11             | CO01toGT01 | Purchase Order     | GT02         | Yes                 |
      | CONS_LATAM                     | 20             | BR12toMX02 | Purchase Order     | MX01         | Yes                 |
      | CONS_LATAM                     | 28             | PA03toGT01 | Purchase Order     | GT02         | Yes                 |
      | CONS_LATAM                     | 29             | PA03toCR01 | Purchase Order     | CR02         | Yes                 |
      |                                | 2              | CO01toEC01 |                    |              | CONS_LATAM          |
      |                                | 3              | CO01toPE01 |                    |              | CONS_LATAM          |
      |                                | 4              | CO01toCO02 |                    |              | CONS_LATAM          |
      |                                | 5              | CO01toCL01 |                    |              | CONS_LATAM          |

    And I wait "/plan/cns_tlane_control" Async Queue complete

    Given I import "/plan/cns_tlane_control_triangulation" by keyFields "sequenceNumber,stepNumber,tlaneName"
      | sequenceNumber | stepNumber | tlaneName  | originLocation  | destinationLocation |
      | 1              | 1          | CO01toMX02 | CONS_LATAM_CO01 | CONS_LATAM_MX01     |
      | 1              | 2          | CO01toMX02 | CONS_LATAM_MX01 | CONS_LATAM_MX02     |
      | 10             | 1          | CO01toCR01 | CONS_LATAM_CO01 | CONS_LATAM_CR02     |
      | 10             | 2          | CO01toCR01 | CONS_LATAM_CR02 | CONS_LATAM_CR01     |
      | 11             | 1          | CO01toGT01 | CONS_LATAM_CO01 | CONS_LATAM_GT02     |
      | 11             | 2          | CO01toGT01 | CONS_LATAM_GT02 | CONS_LATAM_GT01     |
      | 20             | 1          | BR12toMX03 | CONS_LATAM_BR12 | CONS_LATAM_MX01     |
      | 20             | 2          | BR12toMX03 | CONS_LATAM_MX01 | CONS_LATAM_MX02     |
      | 28             | 1          | PA03toGT01 | CONS_LATAM_PA03 | CONS_LATAM_GT02     |
      | 28             | 2          | PA03toGT01 | CONS_LATAM_GT02 | CONS_LATAM_GT01     |
      | 29             | 1          | PA03toCR01 | CONS_LATAM_PA03 | CONS_LATAM_CR02     |
      | 29             | 2          | PA03toCR01 | CONS_LATAM_CR02 | CONS_LATAM_CR01     |

    And I wait "/plan/cns_tlane_control_triangulation" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber,sourceSystem"
      | sourceSystem | localMaterialNumber | materialNumber | primaryPlanningCode |
      | CONS_LATAM   | 55735               | 55735          |                     |
      | CONS_LATAM   | 88360               | 88360          |                     |
      | CONS_LATAM   | 174461              |                | 174461              |
      | CONS_LATAM   | 189915              |                | 189915              |
      | CONS_LATAM   | 189915              | ABC            | 189915              |
      | CONS_LATAM   | 60414               | 60414          |                     |
      | CONS_LATAM   | 94723               | 94723          |                     |
      | CONS_LATAM   | 215161              |                | 215161              |
      | CONS_LATAM   | 132264              | 132264         | 132264              |
      | CONS_LATAM   | 132266              | 132266         | 132266              |
      | CONS_LATAM   | 57039               | 57039          | 57039               |
      | CONS_LATAM   | 132277              | 132277         |                     |
      | CONS_LATAM   | 191383              | 191383         | 191383              |

    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/plan/cns_spl_pln_loc" by keyFields "localName,localNumber,sourceSystem,vendorOrCustomer"
      | localName                           | localNumber | sourceSystem | vendorOrCustomer | localPlant |
      | LODISAL S.A.                        | 19574       | CONS_LATAM   | V                | EC01       |
      | D.H.L. COSTA RICA (CORMAR) S.A.     | 20668       | CONS_LATAM   | V                | CO01       |
      | CILAG AG INTERNATIONAL              | 16060       | CONS_LATAM   | V                | CO01       |
      | J CAIN & CO                         | 16061       | CONS_LATAM   | V                | CO01       |
      | YOBEL SUPPLY CHAIN MANAGEMENT S.A.  | 20667       | CONS_LATAM   | V                | PE01       |
      | AVP MAQUILA DE MEXICO S DE RL DE CV | 86017       | CONS_LATAM   | V                | MX02       |
      | ASPRILLA ORTIZ FABIO                | 15574       | CONS_LATAM   | V                | CO01       |
      | JOHNSON & JOHNSON CONSUMER INC.     | 50161       | CONS_LATAM   | V                |            |
      | MODYLER SA                          | 65659       | CONS_LATAM   | V                | UY01       |
      | DHL GLOBAL FORWARDING (GUATEMALA)   | 36191       | CONS_LATAM   | V                | GT01       |
      | SUPPLA SA                           | 44776       | CONS_LATAM   | V                | CO01       |
      | Apl Logistics Chile S.A.            | 79232       | CONS_LATAM   | V                | CL01       |
      | YOBEL SRL? ? ? ? ? ? ? ?            | 34427       | CONS_LATAM   | V                | DO01       |
      | SUPPORT PACK IND E COM LTDA         | 1189        | CONS_LATAM   | V                | BR12       |
      | JOHNSON & JOHNSON CONSUMER INC.     | 132264      | CONS_LATAM   | V                | CO01       |
      | MODYLER SA                          | 604141      | CONS_LATAM   | V                | EC01       |
      | DHL GLOBAL FORWARDING (GUATEMALA)   | 57039       | CONS_LATAM   | V                | CR01       |
      | SUPPLA SA                           | 174461      | CONS_LATAM   | V                | CO01       |
      | Apl Logistics Chile S.A.            | 215161      | CONS_LATAM   | V                | CO01       |
      | YOBEL SRL? ? ? ? ? ? ? ?            | 189915      | CONS_LATAM   | V                | BR12       |
      | SUPPORT PACK IND E COM LTDA         | 191383      | CONS_LATAM   | V                | CO01       |

    And I wait "/plan/cns_spl_pln_loc" Async Queue complete

    Given I import "/plan/cns_material_plan_status" by keyFields "localMaterialNumber,localPlant,sourceSystem"
      | localMaterialNumber | localPlant | noPlanRelevant | sourceSystem | spRelevant |
      | 55735               | CO01       |                | CONS_LATAM   | X          |
      | 88360               | CO01       |                | CONS_LATAM   | X          |
      | 174461              | BR12       | X              | CONS_LATAM   |            |
      | 189915              | BR12       | X              | CONS_LATAM   |            |
      | 189915              | BR06       | X              | CONS_LATAM   |            |
      | 60414               | CO01       |                | CONS_LATAM   | X          |
      | 94723               | CO01       |                | CONS_LATAM   | X          |
      | 215161              | CO01       | X              | CONS_LATAM   |            |
      | 132264              | CO01       | X              | CONS_LATAM   |            |
      | 132266              | CO01       | X              | CONS_LATAM   |            |
      | 57039               | BR12       |                | CONS_LATAM   | X          |
      | 132277              | MX01       | X              | CONS_LATAM   |            |
      | 191383              | CO01       |                | CONS_LATAM   | X          |

    And I wait "/plan/cns_material_plan_status" Async Queue complete

    Given I import "/edm/plant_v1" by keyFields "localPlant,sourceSystem"
      | localPlanningRelevant | localPlant | sourceSystem |
      | X                     | BR06       | CONS_LATAM   |
      | X                     | GT01       | CONS_LATAM   |
      | X                     | MX02       | CONS_LATAM   |
      | X                     | PY01       | CONS_LATAM   |
      | X                     | PA03       | CONS_LATAM   |
      | X                     | BR07       | CONS_LATAM   |
      |                       | VE01       | CONS_LATAM   |
      |                       | VE02       | CONS_LATAM   |
      | X                     | BR16       | CONS_LATAM   |
      | X                     | BR12       | CONS_LATAM   |
      | X                     | CR01       | CONS_LATAM   |
      | X                     | PE01       | CONS_LATAM   |
      | X                     | BR25       | CONS_LATAM   |
      | X                     | BR19       | CONS_LATAM   |
      | X                     | CR02       | CONS_LATAM   |
      | X                     | CO02       | CONS_LATAM   |
      | X                     | EC01       | CONS_LATAM   |
      | X                     | DO01       | CONS_LATAM   |
      | X                     | GT02       | CONS_LATAM   |
      | X                     | CO01       | CONS_LATAM   |
      |                       | VE05       | CONS_LATAM   |
      | X                     | AR02       | CONS_LATAM   |
      | X                     | UY05       | CONS_LATAM   |
      | X                     | MX01       | CONS_LATAM   |
      | X                     | CL01       | CONS_LATAM   |
      | X                     | AR01       | CONS_LATAM   |
      | X                     | UY01       | CONS_LATAM   |

    And I wait "/edm/plant_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmStockInventoryStocks.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMStock_InventoryStocks.tsv"

    Then I check file data for filename "GDMStock_InventoryStocks.tsv" by keyFields "stockId,consignment"
      | transitDate         | processTypeId | returnsQuantity | inventoryLinkGroupId | vendorId | batchId                                 | activeOPRERP | activeSOPERP | transferQuantity | qualityQuantity | processId | locationId          | consignment | quantity | productId | stockType | receiptDate         | active | eRPOrderId | blockedQuantity | unrestrictedQuantity | certaintyId | stockId                                 | restrictedQuantity | startDate           |
      | 2017/07/20 00:00:00 |               | 4               |                      |          | 189915/CONS_LATAM_BR12/020405C          | YES          | NO           | 7                | 60              |           | CONS_LATAM_BR12     | NO          | 60       | 189915    | level     | 2017/07/20 00:00:00 | YES    |            | 60              | 60                   |             | 189915/CONS_LATAM_BR12/020405C          | 60                 | 2017/07/20 00:00:00 |
      | 2017/07/20 00:00:00 |               | 0               |                      | 16061    | 132277/CONS_LATAM_MX01/4000176631/K     | YES          | NO           | 0                | 1               |           | CONS_LATAM_MX01     | YES         | 1        | 132277    | level     | 2017/07/20 00:00:00 | YES    |            | 1               | 1                    |             | 132277/CONS_LATAM_MX01/4000176631/K     | 1                  | 2017/07/20 00:00:00 |
      | 2017/07/20 00:00:00 |               | 180             |                      |          | 55735/CONS_LATAM_CO01                   | YES          | NO           | 190              | 140             |           | CONS_LATAM_CO01     | NO          | 160      | 55735     | level     | 2017/07/20 00:00:00 | YES    |            | 150             | 160                  |             | 55735/CONS_LATAM_CO01                   | 170                | 2017/07/20 00:00:00 |
      | 2017/07/20 00:00:00 |               | 0               |                      | 15574    | 215161/CONS_LATAM_CO01$15574/B004JH503X | YES          | NO           | 60               | 500             |           | CONS_LATAMCO0115574 | NO          | 600      | 215161    | level     | 2017/07/20 00:00:00 | YES    |            | 0               | 600                  |             | 215161/CONS_LATAM_CO01$15574/B004JH503X | 70                 | 2017/07/20 00:00:00 |
      | 2017/07/20 00:00:00 |               | 0               |                      | 20668    | 215161/CONS_LATAM_CO01$20668/B004JH503X | YES          | NO           | 40               | 550             |           | CONS_LATAMCO0120668 | NO          | 550      | 215161    | level     | 2017/07/20 00:00:00 | YES    |            | 0               | 550                  |             | 215161/CONS_LATAM_CO01$20668/B004JH503X | 33                 | 2017/07/20 00:00:00 |
      | 2017/07/20 00:00:00 |               | 0               |                      | 16060    | 60414/CONS_LATAM_CO01/4000176631/K      | YES          | NO           | 0                | 310             |           | CONS_LATAM_CO01     | YES         | 300      | 60414     | level     | 2017/07/20 00:00:00 | YES    |            | 330             | 300                  |             | 60414/CONS_LATAM_CO01/4000176631/K      | 320                | 2017/07/20 00:00:00 |
      | 2017/07/20 00:00:00 |               | 0               |                      | 16061    | 60414/CONS_LATAM_CO01/4000176631/K      | YES          | NO           | 0                | 3               |           | CONS_LATAM_CO01     | YES         | 3        | 60414     | level     | 2017/07/20 00:00:00 | YES    |            | 3               | 3                    |             | 60414/CONS_LATAM_CO01/4000176631/K      | 3                  | 2017/07/20 00:00:00 |
      | 2017/07/20 00:00:00 |               | 0               |                      |          | 189915/CONS_LATAM_BR06/020405C          | YES          | NO           | 8                | 10              |           | CONS_LATAM_BR06     | NO          | 10       | 189915    | level     | 2017/07/20 00:00:00 | YES    |            | 10              | 10                   |             | 189915/CONS_LATAM_BR06/020405C          | 10                 | 2017/07/20 00:00:00 |
      | 2017/07/20 00:00:00 |               | 2               |                      |          | 174461/CONS_LATAM_BR12/020405B          | YES          | NO           | 10               | 250             |           | CONS_LATAM_BR12     | NO          | 240      | 174461    | level     | 2017/07/20 00:00:00 | YES    |            | 240             | 240                  |             | 174461/CONS_LATAM_BR12/020405B          | 280                | 2017/07/20 00:00:00 |
      | 2017/07/20 00:00:00 |               | 3               |                      |          | 174461/CONS_LATAM_BR12/020405C          | YES          | NO           | 6                | 20              |           | CONS_LATAM_BR12     | NO          | 20       | 174461    | level     | 2017/07/20 00:00:00 | YES    |            | 20              | 20                   |             | 174461/CONS_LATAM_BR12/020405C          | 20                 | 2017/07/20 00:00:00 |
      | 2017/07/20 00:00:00 |               | 55              |                      |          | 88360/CONS_LATAM_CO01                   | YES          | NO           | 55               | 55              |           | CONS_LATAM_CO01     | NO          | 55       | 88360     | level     | 2017/07/20 00:00:00 | YES    |            | 55              | 55                   |             | 88360/CONS_LATAM_CO01                   | 55                 | 2017/07/20 00:00:00 |

  Scenario: delete all test data

    And I delete the test data

    And I will remove all data with region "/omp/gdm_stock"
