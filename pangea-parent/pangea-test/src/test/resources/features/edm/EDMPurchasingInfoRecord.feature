@pangea_test @AEAZ-2371
Feature: EDMPurchasingInfoRecord AEAZ-2371

  Scenario: Full Load curation
    # 1. Get sourceSystem from source_system_v1 where localSourceSystem="project_one"(T1)
    # 2. Get record from EINE where EINA-INFNR = EINE-INFNR (N1)
    # 3. Get sourceSystem from source_system_v1 where localSourceSystem<>"project_one"(T1)
    # 4. Get record from EINE where EINA-INFNR <> EINE-INFNR (N1)

    Given I import "/project_one/eina" by keyFields "infnr"
      | infnr      | lifnr      | matnr              | lmein | matkl | loekz | erdat    | ernam    | txz01 | idnlf  | umrez | umren | meins | mfrnr |
      | 7100002730 | 0000000028 | 000000000000001905 | KI    |       |       | 20000830 | BASISBTC |       | 1905-1 | 1     | 1     | KI    |       |

    And I wait "/project_one/eina" Async Queue complete

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | sourceSystem   |
      | project_one       | Consumer LATAM |
      | project_two       | BTB            |

    And I wait "/edm/source_system_v1" Async Queue complete

    Given I import "/project_one/eine" by keyFields "infnr,ekorg,esokz,werks"
      | infnr      | ekorg | esokz | loekz | werks | erdat    | ernam    | ekgrp | waers | minbm | norbm | aplfz | mwskz | bstae | meprf | inco1 | inco2      | verid | bstma | rdprf | j1bnbm |
      | 7100002730 | AR00  | 0     |       | AR01  | 20150323 | JLISBETH | A16   | USD   | 0.100 | 0.100 | 30    | GA    |       |       | EXW   | EX-FABRICA |       | 0.000 |       |        |
      | 7100002730 | AR00  | 0     |       | AR03  | 20070329 | RLEITAO  | A17   | USD   | 0.000 | 1.000 | 16    | I0    |       |       |       |            |       | 0.000 |       |        |
      | 7100002731 | AR00  | 0     |       | AR04  | 20070329 | RLEITAO  | A17   | USD   | 0.000 | 1.000 | 16    | I0    |       |       |       |            |       | 0.000 |       |        |

    And I wait "/project_one/eine" Async Queue complete

    When I submit task with xml file "xml/edm/EDMPurchasingInfoRecord.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/purchasing_info_record_v1" by keyFields "sourceSystem,localPurchasingInfoRec,localvendor,localPurchasingOrg,infotype,localPlanPlant"
      | sourceSystem   | localPurchasingInfoRec | localvendor | localPurchasingOrg | infotype | localPlanPlant | localMaterialNumber | localBaseUnit | localMaterialGroup | localPurchOrgDataFlagDeletion | localGeneralDataFlagDeletion | localCreatedOnEine | localCreatedOnEina | localCreatedByEine | localCreatedByEina | localPurchasingGroup | localInfoShortText | localCurrencyKey | localMinimumQty | localStandardQty | localPlDelivTime | localVendorMatNo | localNumerator | localDenominator | localOrderUnit | localManufacturer | localTaxCode | localConfirmationControlKey | localPrDateCat | localIncoterms | localIncoterms2 | localProductionVersion | localMaxQuantity | localRndingProfile | localNCMCode |
      | Consumer LATAM | 7100002730             | 0000000028  | AR00               | 0        | AR01           | 000000000000001905  | KI            |                    |                               |                              | 20150323           | 20000830           | JLISBETH           | BASISBTC           | A16                  |                    | USD              | 0.100           | 0.100            | 30               | 1905-1           | 1              | 1                | KI             |                   | GA           |                             |                | EXW            | EX-FABRICA      |                        | 0.000            |                    |              |
      | Consumer LATAM | 7100002730             | 0000000028  | AR00               | 0        | AR03           | 000000000000001905  | KI            |                    |                               |                              | 20070329           | 20000830           | RLEITAO            | BASISBTC           | A17                  |                    | USD              | 0.000           | 1.000            | 16               | 1905-1           | 1              | 1                | KI             |                   | I0           |                             |                |                |                 |                        | 0.000            |                    |              |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

#    And I compare the number of records between "/project_one/eina" and "/edm/purchasing_info_record_v1,/plan/edm_failed_data"

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/edm/purchasing_info_record_v1"

    And I will remove all data with region "/plan/edm_failed_data"

