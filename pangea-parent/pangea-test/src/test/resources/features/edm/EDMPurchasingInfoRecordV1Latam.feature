@pangea_test @AEAZ-2371
Feature: EDMPurchasingInfoRecordV1Latam 

  Scenario: Full Load curation AEAZ-2371
    # 1. Get sourceSystem from source_system_v1 (T1)
    # 2. Get record from EINE where EINA-INFNR = EINE-INFNR (N1)

    Given I import "/project_one/eina" by keyFields "infnr"
      | infnr      | lifnr      | matnr              | lmein | matkl | loekz | erdat    | ernam    | txz01 | idnlf  | umrez | umren | meins | mfrnr |
      | 7100002730 | 0000000028 | 000000000000001905 | KI    |       |       | 20000830 | BASISBTC |       | 1905-1 | 1     | 1     | KI    |       |

    And I wait "/project_one/eina" Async Queue complete

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | sourceSystem   |
      | project_one       | Consumer LATAM |
      | project_two       | BTB            |

    And I wait "/edm/source_system_v1" Async Queue complete

    Given I import "/project_one/eine" by keyFields "infnr"
      | infnr      | ekorg | esokz | loekz | werks | erdat    | ernam    | ekgrp | waers | minbm | norbm | aplfz | mwskz | bstae | meprf | inco1 | inco2      | verid | bstma | rdprf | j1bnbm |
      | 7100002730 | AR00  | 0     |       | AR01  | 20150323 | JLISBETH | A16   | USD   | 0.100 | 0.100 | 30    | GA    |       |       | EXW   | EX-FABRICA |       | 0.000 |       |        |

    And I wait "/project_one/eine" Async Queue complete

    When I submit task with xml file "xml/edm/EDMPurchasingInfoRecordV1Latam.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/purchasing_info_record_v1_latam" by keyFields "sourceSystem,localPurchasingInfoRec,localvendor,localPurchasingOrg,infotype,localPlanPlant"
      | sourceSystem   | localPurchasingInfoRec | localvendor | localPurchasingOrg | infotype | localPlanPlant | localMaterialNumber | localBaseUnit | localMaterialGroup | localPurchOrgDataFlagDeletion | localGeneralDataFlagDeletion | localCreatedOnEine | localCreatedOnEina | localCreatedByEine | localCreatedByEina | localPurchasingGroup | localInfoShortText | localCurrencyKey | localMinimumQty | localStandardQty | localPlDelivTime | localVendorMatNo | localNumerator | localDenominator | localOrderUnit | localManufacturer | localTaxCode | localConfirmationControlKey | localPrDateCat | localIncoterms | localIncoterms2 | localProductionVersion | localMaxQuantity | localRndingProfile | localNCMCode |
      | Consumer LATAM | 7100002730             | 0000000028  | AR00               | 0        | AR01           | 000000000000001905  | KI            |                    |                               |                              | 20150323           | 20000830           | JLISBETH           | BASISBTC           | A16                  |                    | USD              | 0.100           | 0.100            | 30               | 1905-1           | 1              | 1                | KI             |                   | GA           |                             |                | EXW            | EX-FABRICA      |                        | 0.000            |                    |              |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/project_one/eina" and "/edm/purchasing_info_record_v1_latam,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/edm/purchasing_info_record_v1_latam"

    And I will remove all data with region "/plan/edm_failed_data"

