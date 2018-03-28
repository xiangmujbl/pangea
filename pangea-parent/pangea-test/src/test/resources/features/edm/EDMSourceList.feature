@pangea_test
Feature: EDMSourceList-Curation

  Scenario: Full Load curation

    Given I import "/edm/plant_v1" by keyFields "localPlant,sourceSystem"
      | sourceSystem | localPlant | plant |
      | CONS_LATAM   | BR06       | AR01  |
      | TQA          | BR07       | AR02  |
      | TQA          | BR08       | AR03  |
    And I wait "/edm/plant_v1" Async Queue complete

    And I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | localSourceSystemName    | sourceSystem | sourceSystemName   |
      | [MDD FASE]        | [MDD FASE]               | CONS_LATAM   | Consumer Latam Ent |
      | [EMS]             | EMS                      | EMS          | EMS Ent            |
      | project_one       | [MD Synthes SAP Anspach] | CONS_LATAM   | Consumer Latam Ent |
    And I wait "/edm/source_system_v1" Async Queue complete

    And I import "/edm/material_global_v1" by keyFields "localMaterialNumber,sourceSystem"
      |  localMaterialNumber| sourceSystem | materialNumber  |
      | [EMS]               | EMS          | EMS            |
      | 3                   | CONS_LATAM   | CONS_LATAM     |
      | 1                   | TQA          | TQA            |
    And I wait "/edm/material_global_v1" Async Queue complete

    And I import "/project_one/eord" by keyFields "matnr,werks,zeord"
      | notkz | reswk | eortp | ernam    | matnr | erdat    | ebelp | ebeln | vdatu    | autet | febel | lifnr | werks | zeord | ematn | bdatu    | flifn | vrtyp | ekorg |
      |       | BR12  | 7     | CCASTRO1 | 1     | 20030306 | 0     |       | 20030206 |       |       | 8917  | BR06  | 4     |       | 29991231 |       |       | BR00  |
      |       | BR12  | 7     | CARGA03  | 3     | 20020522 | 0     |       | 20020301 |       |       | 8917  | BR06  | 2     |       | 99991231 |       |       | BR00  |
      |       | BR12  | 7     | JPINTO4  | 3     | 20020522 | 0     |       | 20020422 |       |       | 8917  | BR07  | 2     |       | 29991231 |       |       | BR00  |

    And I wait "/project_one/eord" Async Queue complete

    When I submit task with xml file "xml/edm/EDMSourceList.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/source_list_v1" by keyFields "sourceSystem,localMaterialNumber,localPlant,localNumberofSourceListRecord"
      | sourceSystem | localMaterialNumber | localPlant | localNumberofSourceListRecord | materialNumber | plant | localCreatedOn | localCreatedBy | localSourceListRecordValidFrom | localSourceListRecordValidTo | localVendorAccountNumber | localFixedvendor | localAgreementNumber | localAgreementItem | localFixedOutlinePurchaseAgreementItem | localPlantfromWhichMaterialisProcured | localMatForManufPartNumber | localBlockedSourceofSupply | localPurchasingOrganization | localPurchasingDocumentCategory | localCategoryofSourceListRecord | localSourceListUsageinMaterialsPlanning |
      | CONS_LATAM   | 1                   | BR06       | 4                             |                | AR01  | 20030306       | CCASTRO1       | 20030206                       | 29991231                     | 8917                     |                  |                      | 0                  |                                        | BR12                                  |                            |                            | BR00                        |                                 | 7                               |                                         |
      | CONS_LATAM   | 3                   | BR06       | 2                             | CONS_LATAM     | AR01  | 20020522       | CARGA03        | 20020301                       | 99991231                     | 8917                     |                  |                      | 0                  |                                        | BR12                                  |                            |                            | BR00                        |                                 | 7                               |                                         |
      | CONS_LATAM   | 3                   | BR07       | 2                             | CONS_LATAM     |       | 20020522       | JPINTO4        | 20020422                       | 29991231                     | 8917                     |                  |                      | 0                  |                                        | BR12                                  |                            |                            | BR00                        |                                 | 7                               |                                         |
    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |



    And I compare the number of records between "/project_one/eord" and "/edm/source_list_v1,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/edm/source_list_v1"

    And I will remove all data with region "/plan/edm_failed_data"

#        And I will remove all data with region "/edm/plant_v1"
#
#            And I will remove all data with region "/edm/source_system_v1"
#
#                And I will remove all data with region "/edm/material_global_v1"
#





