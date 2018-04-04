@pangea_test @
Feature: OMPGdmConversionStorage

  Scenario: Full Load curation

    Given I import "/plan/cns_dp_price" by keyFields "sourceSystem,localMaterialNumber,"
      | localMaterialNumber | country | currency | fromDate | toDate | salesPrice |
      | 57928               | BR      | BRL      | 201701   | 201701 | 56.84      |
      | 57928               | BR      | BRL      | 201701   | 201701 | 56.84      |
    And I wait "/plan/cns_dp_price" Async Queue complete

    Given I import "/edm/source_system_v1" by keyFields ""
      | localSourceSystem | localSourceSystemName | sourceSystem | sourceSystemName   |
      | project_one       | Project One           | CONS_LATAM   | Consumer Latam Ent |
      | [EMS]             | EMS                   | EMS          | EMS Ent            |
      | project_two       | Project Two           | CONS_LATAM   | Consumer Latam Ent |
    And I wait "/edm/source_system_v1" Async Queue complete

    Given I import "/edm/currency_v1" by keyFields ""
      | localCurrency | currencyCode |
      | USD           | USD          |
      | BRL           | BRL          |
    And I wait "/edm/currency_v1" Async Queue complete

    Given I import "/edm/country_v1" by keyFields ""
      | localCountry | countryCode |
      | AD           | AD          |
      | AE           | AE          |
    And I wait "/edm/country_v1" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields ""
      | localDpParentCode |
    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/plan/cns_cust_channel" by keyFields ""
      | channel |
    And I wait "/plan/cns_cust_channel" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmConversionStorage.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/omp/gdm_conversion_storage" by keyFields ""
      | sourceSystem | aggregationId | currencyId | dueDate | forecastUploadId | fromDueDate | salesPrice | unitId |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/plan/cns_dp_price" and "/omp/gdm_conversion_storage,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/omp/gdm_conversion_storage"

    And I will remove all data with region "/plan/edm_failed_data"

