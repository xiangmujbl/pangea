@pangea_test @AEAZ-2759
Feature: OMPGdmConversionStorage AEAZ-2759

  Scenario: Full Load curation

    Given I import "/plan/cns_dp_price" by keyFields "sourceSystem,localMaterialNumber,currency,country,fromDate"
      | localMaterialNumber | currency | country  | fromDate | salesPrice | sourceSystem |
      | 57928               | BRL1     | jiangsu  | 201801   | 56.70      | nihao        |
      | 57929               | BRL2     | nnajing  | 201804   | 57.50      | wohao        |
      | 57930               | BRL3     | anhuiaa  | 201804   | 57.50      | wobuhao      |
      | 57939               | BRL3     | realdate | 201804   | 0          | wobuhao      |
    And I wait "/plan/cns_dp_price" Async Queue complete

    Given I import "/edm/country_v1" by keyFields "localCountry"
      | countryCode | localCountry |
      | code01      | jiangsu      |
      | error01     | nnajing      |
      | edoc08      | realdate     |
    And I wait "/edm/country_v1" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | localMaterialNumber | sourceSystem | localDpParentCode |
      | 57928               | nihao        | 58.90             |
      | 57929               | wohao        | 59.70             |
      | 57932               | wohao        | 59.70             |
      | 57939               | wobuhao      | 60.66             |

    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/plan/cns_cust_channel" by keyFields "channel"
      | channel | salesOrg |
      | code01  | code01   |
      | error01 | error01  |
      | edoc08  | edoc08   |
    And I wait "/plan/cns_cust_channel" Async Queue complete

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | sourceSystem |
      | nihao             | nihao        |
      | wohao             | wohao        |
      | wobuhao           | wobuhao      |
    And I wait "/edm/source_system_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmConversionStorage.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMConversionStorage.tsv"

    Then I check region data "/omp/gdm_conversion_storage" by keyFields "sourceSystem,aggregationId,currencyId,dueDate,forecastUploadId,fromDueDate"
      | aggregationId               | currencyId | dueDate          | forecastUploadId                             | fromDueDate      | salesPrice | sourceSystem |
      | nihao-58.90-code01-code01   | BRL1       | 2018013123:59:59 | nihao-58.90-code01-code01-2018013123:59:59   | 2018010100:00:00 | 56.7       | nihao        |
      | wobuhao-60.66-edoc08-edoc08 | BRL3       | 2018043023:59:59 | wobuhao-60.66-edoc08-edoc08-2018043023:59:59 | 2018040100:00:00 | 0.0        | wobuhao      |
      | wohao-59.70-error01-error01 | BRL2       | 2018043023:59:59 | wohao-59.70-error01-error01-2018043023:59:59 | 2018040100:00:00 | 57.5       | wohao        |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID             | errorCode | sourceSystem | businessArea | key1  | key2    | key3 | key4    | key5   | errorValue                       |
      | DP             | OMPGdmConversionStorage | J1        | omp          |              | 57930 | wobuhao | BRL3 | anhuiaa | 201804 | localMaterialNumber do not exist |

    And I compare the number of records between "/plan/cns_dp_price" and "/omp/gdm_conversion_storage,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/omp/gdm_conversion_storage"

    And I will remove all data with region "/plan/edm_failed_data"

    And I will remove the test file on sink application "GDMConversionStorage.tsv"

