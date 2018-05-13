@pangea_test @AEAZ-2369
Feature: EDMSourceList-Curation AEAZ-2369

  Scenario: Full Load curation
    # 1. Get sourceSystem from sourceSystem (T1)

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | localSourceSystemName    | sourceSystem | sourceSystemName   |
      | [EMS]             | EMS                      | EMS          | EMS Ent            |
      | project_one       | [MD Synthes SAP Anspach] | CONS_LATAM   | Consumer Latam Ent |
    And I wait "/edm/source_system_v1" Async Queue complete

    And I import "/project_one/eord" by keyFields "matnr,werks,zeord"
      | notkz | reswk | eortp | ernam    | matnr | erdat    | ebelp | ebeln | vdatu    | autet | febel | lifnr | werks | zeord | ematn | bdatu    | flifn | vrtyp | ekorg |
      |       | BR12  | 7     | CCASTRO1 | 1     | 20030306 | 0     |       | 20030206 |       |       | 8917  | BR06  | 4     |       | 29991231 |       |       | BR00  |
    And I wait "/project_one/eord" Async Queue complete

    When I submit task with xml file "xml/edm/EDMSourceList.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/source_list_v1" by keyFields "sourceSystem,localMaterialNumber,localPlant,localNumberofSourceListRecord"
      | sourceSystem | localMaterialNumber | localPlant | localNumberofSourceListRecord | localDateonWhichRecordWasCreated | localNameofPersonwhoCreatedtheObject | localSourceListRecordValidFrom | localSourceListRecordValidTo | localVendorAccountNumber | localFixedvendor | localAgreementNumber | localAgreementItem | localFixedOutlinePurchaseAgreementItem | localPlantfromWhichMaterialisProcured | localMaterialNumberCorrespondingtoManufacturerPartNumber | localBlockedSourceofSupply | localPurchasingOrganization | localPurchasingDocumentCategory | localCategoryofSourceListRecord | localSourceListUsageinMaterialsPlanning |
      | CONS_LATAM   | 1                   | BR06       | 4                             | 20030306                         | CCASTRO1                             | 20030206                       | 29991231                     | 8917                     |                  |                      | 0                  |                                        | BR12                                  |                                                          |                            | BR00                        |                                 | 7                               |                                         |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/project_one/eord" and "/edm/source_list_v1,/plan/edm_failed_data"

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/edm/source_list_v1"

    And I will remove all data with region "/plan/edm_failed_data"





