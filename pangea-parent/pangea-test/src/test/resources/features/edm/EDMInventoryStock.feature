@pangea_test @AEAZ-3678
Feature: EDMInventoryStock AEAZ-3678
    #1.get sourceSystem from source_system_v1 (rule T1)
    #2.Join (MARC-MATNR) = (MCHB-MATNR) and (MARC-WERKS) = (MCHB-WERKS) (rule N1)
    #3.Join (MARC-MATNR) = (MSLB-MATNR) and (MARC-WERKS) = (MSLB-WERKS) (rule N2)
    #4.Join (MARC-MATNR) = (MKOL-MATNR) and (MARC-WERKS) = (MKOL-WERKS) (rule N3)
    #5.Join (MARC-MATNR) = (MARD-MATNR) and (MARC-WERKS) = (MARD-WERKS) (rule N4)
    #6.If no record is found in MCHB or MSLB or MKOL or MARD, still there needs to be an output record with blank values for attributes from them;

  @Scenario1
  Scenario: test N1,N2,N3,N4
    Given I import "/project_one/marc" by keyFields "mandt,matnr,werks"
      | mandt | matnr              | werks | trame |
      | 120   | 000000000000132264 | CO01  | 0     |
      | 120   | 000000000000132288 | CO01  | 0     |
      | 120   | 000000000000132264 | CO02  | 0     |

    And I wait "/project_one/marc" Async Queue complete

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | sourceSystem |
      | Project_One       | CONS_LATAM   |
      | [Consumer LATAM]  | CONS_LATAM   |
    And I wait "/edm/source_system_v1" Async Queue complete

    Given I import "/project_one/mchb" by keyFields "charg,lgort,matnr,werks,mandt"
      | charg      | lgort | matnr              | werks | mandt | clabs | cinsm | ceinm | cspem | cretm | cumlm |
      | 0027LKE30  | CO01  | 000000000000132264 | CO01  | 120   | 1000  | 1     | 0     | 650   | 0     | 0     |
      | 10017KAE38 | CO01  | 000000000000132295 | CO01  | 120   | 6     | 50    | 0     | 0     | 0     | 0     |
      | 10017KAE39 | CO03  | 000000000000132264 | CO03  | 120   | 6     | 50    | 0     | 0     | 0     | 0     |
    And I wait "/project_one/mchb" Async Queue complete

    Given I import "/project_one/mkol" by keyFields "charg,lifnr,logrt,mandt,matnr,sobkz,werks"
      | mandt | matnr              | werks | logrt | charg      | sobkz | lifnr | slabs     | sinsm | seinm | sspem |
      | 120   | 000000000000132264 | CO01  | CO02  | 4000290992 | K     | 16137 | 25200.000 | 0.000 | 0.000 | 0.000 |
      | 120   | 000000000000132289 | CO01  | CO02  | 4000290989 | K     | 16137 | 600.000   | 0.000 | 0.000 | 0.000 |
      | 120   | 000000000000132264 | CO04  | CO02 | 4000290992 | K     | 16137 | 25200.000 | 0.000 | 0.000 | 0.000 |

    And I wait "/project_one/mkol" Async Queue complete

    Given I import "/project_one/mard" by keyFields "lgort,mandt,matnr,werks"
      | mandt | matnr              | werks | lgort | labst | insme | einme | speme | retme | umlme | klabs | kinsm | keinm | kspem | umlmc |
      | 120   | 000000000000132264 | CO01  | CO07  | 1807  | 213   | 0     | 1.972 | 0     | 280   | 0     | 0     | 0     | 0     | 0     |
      | 120   | 000000000000132265 | CO01  | CO08  | 1808  | 214   | 1     | 1     | 0     | 281   | 0     | 0     | 0     | 0     | 0     |
      | 120   | 000000000000132264 | CO05  | CO07  | 1807  | 213   | 0     | 1.972 | 0     | 280   | 0     | 0     | 0     | 0     | 0     |
    And I wait "/project_one/mard" Async Queue complete

    Given I import "/project_one/mslb" by keyFields "charg,lifnr,mandt,matnr,sobkz,werks"
      | mandt | matnr              | werks | charg      | sobkz | lifnr | lblab   | lbins | lbein | lbuml |
      | 120   | 000000000000132264 | CO01  | 4000263377 | O     | 15574 | 108.000 | 0.000 | 0.000 | 0.000 |
      | 120   | 000000000000132266 | CO01  | 4000279495 | O     | 15575 | 103.000 | 0.000 | 0.000 | 0.000 |
      | 120   | 000000000000132264 | CO06  | 4000263377 | O     | 15574 | 108.000 | 0.000 | 0.000 | 0.000 |
    And I wait "/project_one/mslb" Async Queue complete

    When I submit task with xml file "xml/edm/EDMInventoryStock.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/inventory_stock" by keyFields "localBatchId,localConsignmentSpecialStockIndicator,localPlant,localSpecialStockIndicator,sourceSystem,localStorageLocation,localMaterial,localVendorNumber"
      | sourceSystem | localPlant | localMaterial      | localStorageLocation | localBatchId | localVendorNumber | localSpecialStockIndicator | localConsignmentSpecialStockIndicator | localUnrestrictedStock | localUnrestrictedSpecialStock | localUnrestrictedBatchStock | localUnrestrictedConsignmentStock | localQualityInspectionStock | localQualityInspectionSpecialStock | localQualityInspectionBatchStock | localQualityInspectionConsignmentStock | localRestrictedStock | localRestrictedSpecialStock | localRestrictedBatchStock | localRestrictedConsignmentStock | localBlockedStock | localBlockedBatchStock | localBlockedConsignmentStock | localReturnsStock | localReturnsBatchStock | localStockInTransitStorageLocation | localStockInTransitPlantToPlant | localStockInTransitPlant | localStockInTransitSpecial | localStockInTransitBatch | localRestrictedUseConsignment | localConsignmentStockInQualityInspection | localUnrestrictedUseConsignment | standLocalBlockedConsignmentStock | localTotalStockAllRestrictedBatches |
      | CONS_LATAM   | CO01       | 000000000000132264 | CO07                 | 0027LKE30    | 15574             | O                          | K                                     | 1807                   | 108.000                       | 1000                        | 25200.000                         | 213                         | 0.000                              | 1                                | 0.000                                  | 0                    | 0.000                       | 0                         | 0.000                           | 1.972             | 650                    | 0.000                        | 0                 | 0                      | 0                                  | 0                               | 280                      | 0.000                      | 0                        | 0                             | 0                                        | 0                               | 0                                 | 0                                   |
      | CONS_LATAM   |            |                    |                      |              |                   |                            |                                       |                        |                               |                             |                                   |                             |                                    |                                  |                                        |                      |                             |                           |                                 |                   |                        |                              |                   |                        | 0                                  |                                 |                          |                            |                          |                               |                                          |                                 |                                   |                                     |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

#    And I compare the number of records between "/project_one/marc" and "/edm/inventory_stock,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/edm/inventory_stock"

    And I will remove all data with region "/plan/edm_failed_data"

  @Scenario2
  Scenario: test one to many.
    Given I import "/project_one/marc" by keyFields "mandt,matnr,werks"
      | mandt | matnr              | werks | trame |
      | 120   | 000000000000132264 | CO01  | 0     |

    And I wait "/project_one/marc" Async Queue complete

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | sourceSystem |
      | Project_One       | CONS_LATAM   |
    And I wait "/edm/source_system_v1" Async Queue complete

    Given I import "/project_one/mchb" by keyFields "charg,lgort,matnr,werks,mandt"
      | charg      | lgort | matnr              | werks | mandt | clabs | cinsm | ceinm | cspem | cretm | cumlm |
      | 0027LKE30  | CO01  | 000000000000132264 | CO01  | 120   | 1000  | 1     | 0     | 650   | 0     | 0     |
      | 10017KAE39 | CO01  | 000000000000132264 | CO01  | 120   | 6     | 50    | 0     | 0     | 0     | 0     |
    And I wait "/project_one/mchb" Async Queue complete

    Given I import "/project_one/mkol" by keyFields "charg,lifnr,logrt,mandt,matnr,sobkz,werks"
      | mandt | matnr              | werks | logrt | charg      | sobkz | lifnr | slabs     | sinsm | seinm | sspem |
      | 120   | 000000000000132264 | CO01  | CO02  | 4000290992 | K     | 16137 | 25200.000 | 0.000 | 0.000 | 0.000 |
      | 120   | 000000000000132264 | CO01  | CO02 | 4000290993 | K1     | 16137 | 25200.000 | 0.000 | 0.000 | 0.000 |

    And I wait "/project_one/mkol" Async Queue complete

    Given I import "/project_one/mard" by keyFields "lgort,mandt,matnr,werks"
      | mandt | matnr              | werks | lgort | labst | insme | einme | speme | retme | umlme | klabs | kinsm | keinm | kspem | umlmc |
      | 120   | 000000000000132264 | CO01  | CO07  | 1807  | 213   | 0     | 1.972 | 0     | 280   | 0     | 0     | 0     | 0     | 0     |
      | 120   | 000000000000132264 | CO01  | CO08  | 1807  | 213   | 0     | 1.972 | 0     | 280   | 0     | 0     | 0     | 0     | 0     |
    And I wait "/project_one/mard" Async Queue complete

    Given I import "/project_one/mslb" by keyFields "charg,lifnr,mandt,matnr,sobkz,werks"
      | mandt | matnr              | werks | charg      | sobkz | lifnr | lblab   | lbins | lbein | lbuml |
      | 120   | 000000000000132264 | CO01  | 4000263377 | O     | 15574 | 108.000 | 0.000 | 0.000 | 0.000 |
      | 120   | 000000000000132264 | CO01  | 4000263378 | 1     | 15575 | 109.000 | 0.000 | 0.000 | 0.000 |
    And I wait "/project_one/mslb" Async Queue complete

    When I submit task with xml file "xml/edm/EDMInventoryStock.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/inventory_stock" by keyFields "localBatchId,localConsignmentSpecialStockIndicator,localPlant,localSpecialStockIndicator,sourceSystem,localStorageLocation,localMaterial,localVendorNumber"
      | sourceSystem | localPlant | localMaterial      | localStorageLocation | localBatchId | localVendorNumber | localSpecialStockIndicator | localConsignmentSpecialStockIndicator | localUnrestrictedStock | localUnrestrictedSpecialStock | localUnrestrictedBatchStock | localUnrestrictedConsignmentStock | localQualityInspectionStock | localQualityInspectionSpecialStock | localQualityInspectionBatchStock | localQualityInspectionConsignmentStock | localRestrictedStock | localRestrictedSpecialStock | localRestrictedBatchStock | localRestrictedConsignmentStock | localBlockedStock | localBlockedBatchStock | localBlockedConsignmentStock | localReturnsStock | localReturnsBatchStock | localStockInTransitStorageLocation | localStockInTransitPlantToPlant | localStockInTransitPlant | localStockInTransitSpecial | localStockInTransitBatch | localRestrictedUseConsignment | localConsignmentStockInQualityInspection | localUnrestrictedUseConsignment | standLocalBlockedConsignmentStock | localTotalStockAllRestrictedBatches |
      | CONS_LATAM   | CO01       | 000000000000132264 | CO07                 | 0027LKE30    | 15575             | 1                          | K1                                     | 1807                   | 109.000                       | 1000                        | 25200.000                         | 213                         | 0.000                              | 1                                | 0.000                                  | 0                    | 0.000                       | 0                         | 0.000                           | 1.972             | 650                    | 0.000                        | 0                 | 0                      | 0                                  | 0                               | 280                      | 0.000                      | 0                        | 0                             | 0                                        | 0                               | 0                                 | 0                                   |
      | CONS_LATAM   | CO01       | 000000000000132264 | CO08                 | 0027LKE30    | 15575             | 1                          | K1                                     | 1807                   | 109.000                       | 1000                        | 25200.000                         | 213                         | 0.000                              | 1                                | 0.000                                  | 0                    | 0.000                       | 0                         | 0.000                           | 1.972             | 650                    | 0.000                        | 0                 | 0                      | 0                                  | 0                               | 280                      | 0.000                      | 0                        | 0                             | 0                                        | 0                               | 0                                 | 0                                   |
      | CONS_LATAM   | CO01       | 000000000000132264 | CO07                 | 0027LKE30    | 15574             | O                          | K1                                     | 1807                   | 108.000                       | 1000                        | 25200.000                         | 213                         | 0.000                              | 1                                | 0.000                                  | 0                    | 0.000                       | 0                         | 0.000                           | 1.972             | 650                    | 0.000                        | 0                 | 0                      | 0                                  | 0                               | 280                      | 0.000                      | 0                        | 0                             | 0                                        | 0                               | 0                                 | 0                                   |
      | CONS_LATAM   | CO01       | 000000000000132264 | CO08                 | 0027LKE30    | 15574             | O                          | K1                                     | 1807                   | 108.000                       | 1000                        | 25200.000                         | 213                         | 0.000                              | 1                                | 0.000                                  | 0                    | 0.000                       | 0                         | 0.000                           | 1.972             | 650                    | 0.000                        | 0                 | 0                      | 0                                  | 0                               | 280                      | 0.000                      | 0                        | 0                             | 0                                        | 0                               | 0                                 | 0                                   |
      | CONS_LATAM   | CO01       | 000000000000132264 | CO07                 | 0027LKE30    | 15575             | 1                          | K                                      | 1807                   | 109.000                       | 1000                        | 25200.000                         | 213                         | 0.000                              | 1                                | 0.000                                  | 0                    | 0.000                       | 0                         | 0.000                           | 1.972             | 650                    | 0.000                        | 0                 | 0                      | 0                                  | 0                               | 280                      | 0.000                      | 0                        | 0                             | 0                                        | 0                               | 0                                 | 0                                   |
      | CONS_LATAM   | CO01       | 000000000000132264 | CO08                 | 0027LKE30    | 15575             | 1                          | K                                      | 1807                   | 109.000                       | 1000                        | 25200.000                         | 213                         | 0.000                              | 1                                | 0.000                                  | 0                    | 0.000                       | 0                         | 0.000                           | 1.972             | 650                    | 0.000                        | 0                 | 0                      | 0                                  | 0                               | 280                      | 0.000                      | 0                        | 0                             | 0                                        | 0                               | 0                                 | 0                                   |
      | CONS_LATAM   | CO01       | 000000000000132264 | CO07                 | 0027LKE30    | 15574             | O                          | K                                      | 1807                   | 108.000                       | 1000                        | 25200.000                         | 213                         | 0.000                              | 1                                | 0.000                                  | 0                    | 0.000                       | 0                         | 0.000                           | 1.972             | 650                    | 0.000                        | 0                 | 0                      | 0                                  | 0                               | 280                      | 0.000                      | 0                        | 0                             | 0                                        | 0                               | 0                                 | 0                                   |
      | CONS_LATAM   | CO01       | 000000000000132264 | CO08                 | 0027LKE30    | 15574             | O                          | K                                      | 1807                   | 108.000                       | 1000                        | 25200.000                         | 213                         | 0.000                              | 1                                | 0.000                                  | 0                    | 0.000                       | 0                         | 0.000                           | 1.972             | 650                    | 0.000                        | 0                 | 0                      | 0                                  | 0                               | 280                      | 0.000                      | 0                        | 0                             | 0                                        | 0                               | 0                                 | 0                                   |
      | CONS_LATAM   | CO01       | 000000000000132264 | CO07                 | 10017KAE39   | 15575             | 1                          | K1                                     | 1807                   | 109.000                       | 6                           | 25200.000                         | 213                         | 0.000                              | 50                               | 0.000                                  | 0                    | 0.000                       | 0                         | 0.000                           | 1.972             | 0                      | 0.000                        | 0                 | 0                      | 0                                  | 0                               | 280                      | 0.000                      | 0                        | 0                             | 0                                        | 0                               | 0                                 | 0                                   |
      | CONS_LATAM   | CO01       | 000000000000132264 | CO08                 | 10017KAE39   | 15575             | 1                          | K1                                     | 1807                   | 109.000                       | 6                           | 25200.000                         | 213                         | 0.000                              | 50                               | 0.000                                  | 0                    | 0.000                       | 0                         | 0.000                           | 1.972             | 0                      | 0.000                        | 0                 | 0                      | 0                                  | 0                               | 280                      | 0.000                      | 0                        | 0                             | 0                                        | 0                               | 0                                 | 0                                   |
      | CONS_LATAM   | CO01       | 000000000000132264 | CO07                 | 10017KAE39   | 15574             | O                          | K1                                     | 1807                   | 108.000                       | 6                           | 25200.000                         | 213                         | 0.000                              | 50                               | 0.000                                  | 0                    | 0.000                       | 0                         | 0.000                           | 1.972             | 0                      | 0.000                        | 0                 | 0                      | 0                                  | 0                               | 280                      | 0.000                      | 0                        | 0                             | 0                                        | 0                               | 0                                 | 0                                   |
      | CONS_LATAM   | CO01       | 000000000000132264 | CO08                 | 10017KAE39   | 15574             | O                          | K1                                     | 1807                   | 108.000                       | 6                           | 25200.000                         | 213                         | 0.000                              | 50                               | 0.000                                  | 0                    | 0.000                       | 0                         | 0.000                           | 1.972             | 0                      | 0.000                        | 0                 | 0                      | 0                                  | 0                               | 280                      | 0.000                      | 0                        | 0                             | 0                                        | 0                               | 0                                 | 0                                   |
      | CONS_LATAM   | CO01       | 000000000000132264 | CO07                 | 10017KAE39   | 15575             | 1                          | K                                      | 1807                   | 109.000                       | 6                           | 25200.000                         | 213                         | 0.000                              | 50                               | 0.000                                  | 0                    | 0.000                       | 0                         | 0.000                           | 1.972             | 0                      | 0.000                        | 0                 | 0                      | 0                                  | 0                               | 280                      | 0.000                      | 0                        | 0                             | 0                                        | 0                               | 0                                 | 0                                   |
      | CONS_LATAM   | CO01       | 000000000000132264 | CO08                 | 10017KAE39   | 15575             | 1                          | K                                      | 1807                   | 109.000                       | 6                           | 25200.000                         | 213                         | 0.000                              | 50                               | 0.000                                  | 0                    | 0.000                       | 0                         | 0.000                           | 1.972             | 0                      | 0.000                        | 0                 | 0                      | 0                                  | 0                               | 280                      | 0.000                      | 0                        | 0                             | 0                                        | 0                               | 0                                 | 0                                   |
      | CONS_LATAM   | CO01       | 000000000000132264 | CO07                 | 10017KAE39   | 15574             | O                          | K                                      | 1807                   | 108.000                       | 6                           | 25200.000                         | 213                         | 0.000                              | 50                               | 0.000                                  | 0                    | 0.000                       | 0                         | 0.000                           | 1.972             | 0                      | 0.000                        | 0                 | 0                      | 0                                  | 0                               | 280                      | 0.000                      | 0                        | 0                             | 0                                        | 0                               | 0                                 | 0                                   |
      | CONS_LATAM   | CO01       | 000000000000132264 | CO08                 | 10017KAE39   | 15574             | O                          | K                                      | 1807                   | 108.000                       | 6                           | 25200.000                         | 213                         | 0.000                              | 50                               | 0.000                                  | 0                    | 0.000                       | 0                         | 0.000                           | 1.972             | 0                      | 0.000                        | 0                 | 0                      | 0                                  | 0                               | 280                      | 0.000                      | 0                        | 0                             | 0                                        | 0                               | 0                                 | 0                                   |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

#    And I compare the number of records between "/project_one/marc" and "/edm/inventory_stock,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/edm/inventory_stock"

    And I will remove all data with region "/plan/edm_failed_data"

  @Scenario3
  Scenario: Full Load curation

    Given I import "/project_one/marc" by keyFields "mandt,matnr,werks"
      | mandt | matnr              | werks | trame |
      | 120   | 000000000000132264 | CO01  | 0     |
      | 120   | 000000000000132294 | CO01  | 0     |

    And I wait "/project_one/marc" Async Queue complete

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | sourceSystem |
      | Project_One       | CONS_LATAM   |
      | [Consumer LATAM]  | CONS_LATAM   |
    And I wait "/edm/source_system_v1" Async Queue complete

    Given I import "/project_one/mchb" by keyFields "charg,lgort,matnr,werks,mandt"
      | charg      | lgort | matnr              | werks | mandt | clabs | cinsm | ceinm | cspem | cretm | cumlm |
      | 0027LKE30  | CO01  | 000000000000132264 | CO01  | 120   | 1000  | 1     | 0     | 650   | 0     | 0     |
      | 10017KAE38 | CO01  | 000000000000132294 | CO01  | 120   | 6     | 50    | 0     | 0     | 0     | 0     |
    And I wait "/project_one/mchb" Async Queue complete

    Given I import "/project_one/mkol" by keyFields "charg,lifnr,logrt,mandt,matnr,sobkz,werks"
      | mandt | matnr              | werks | logrt | charg      | sobkz | lifnr | slabs     | sinsm | seinm | sspem |
      | 120   | 000000000000132264 | CO01  | CO02  | 4000290992 | K     | 16137 | 25200.000 | 0.000 | 0.000 | 0.000 |
      | 120   | 000000000000132294 | CO01  | CO02  | 4000290989 | K     | 16137 | 600.000   | 0.000 | 0.000 | 0.000 |

    And I wait "/project_one/mkol" Async Queue complete

    Given I import "/project_one/mard" by keyFields "lgort,mandt,matnr,werks"
      | mandt | matnr              | werks | lgort | labst | insme | einme | speme | retme | umlme | klabs | kinsm | keinm | kspem | umlmc |
      | 120   | 000000000000132264 | CO01  | CO07  | 1807  | 213   | 0     | 1.972 | 0     | 280   | 0     | 0     | 0     | 0     | 0     |
      | 120   | 000000000000132294 | CO01  | CO08  | 1808  | 214   | 1     | 1     | 0     | 281   | 0     | 0     | 0     | 0     | 0     |
    And I wait "/project_one/mard" Async Queue complete

    Given I import "/project_one/mslb" by keyFields "charg,lifnr,mandt,matnr,sobkz,werks"
      | mandt | matnr              | werks | charg      | sobkz | lifnr | lblab   | lbins | lbein | lbuml |
      | 120   | 000000000000132264 | CO01  | 4000263377 | O     | 15574 | 108.000 | 0.000 | 0.000 | 0.000 |
      | 120   | 000000000000132294 | CO01  | 4000279495 | O     | 15575 | 103.000 | 0.000 | 0.000 | 0.000 |
    And I wait "/project_one/mslb" Async Queue complete


    When I submit task with xml file "xml/edm/EDMInventoryStock.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/inventory_stock" by keyFields "localBatchId,localConsignmentSpecialStockIndicator,localPlant,localSpecialStockIndicator,sourceSystem,localStorageLocation,localMaterial,localVendorNumber"
      | sourceSystem | localPlant | localMaterial      | localStorageLocation | localBatchId | localVendorNumber | localSpecialStockIndicator | localConsignmentSpecialStockIndicator | localUnrestrictedStock | localUnrestrictedSpecialStock | localUnrestrictedBatchStock | localUnrestrictedConsignmentStock | localQualityInspectionStock | localQualityInspectionSpecialStock | localQualityInspectionBatchStock | localQualityInspectionConsignmentStock | localRestrictedStock | localRestrictedSpecialStock | localRestrictedBatchStock | localRestrictedConsignmentStock | localBlockedStock | localBlockedBatchStock | localBlockedConsignmentStock | localReturnsStock | localReturnsBatchStock | localStockInTransitStorageLocation | localStockInTransitPlantToPlant | localStockInTransitPlant | localStockInTransitSpecial | localStockInTransitBatch | localRestrictedUseConsignment | localConsignmentStockInQualityInspection | localUnrestrictedUseConsignment | standLocalBlockedConsignmentStock | localTotalStockAllRestrictedBatches |
      | CONS_LATAM   | CO01       | 000000000000132264 | CO07                 | 0027LKE30    | 15574             | O                          | K                                     | 1807                   | 108.000                       | 1000                        | 25200.000                         | 213                         | 0.000                              | 1                                | 0.000                                  | 0                    | 0.000                       | 0                         | 0.000                           | 1.972             | 650                    | 0.000                        | 0                 | 0                      | 0                                  | 0                               | 280                      | 0.000                      | 0                        | 0                             | 0                                        | 0                               | 0                                 | 0                                   |
      | CONS_LATAM   | CO01       | 000000000000132294 | CO08                 | 10017KAE38   | 15575             | O                          | K                                     | 1808                   | 103.000                       | 6                           | 600.000                           | 214                         | 0.000                              | 50                               | 0.000                                  | 1                    | 0.000                       | 0                         | 0.000                           | 1                 | 0                      | 0.000                        | 0                 | 0                      | 0                                  | 0                               | 281                      | 0.000                      | 0                        | 0                             | 0                                        | 0                               | 0                                 | 1                                   |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

#    And I compare the number of records between "/project_one/marc" and "/edm/inventory_stock,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/edm/inventory_stock"

    And I will remove all data with region "/plan/edm_failed_data"

