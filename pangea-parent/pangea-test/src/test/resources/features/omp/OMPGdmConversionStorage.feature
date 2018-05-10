@pangea_test @AEAZ-2759
Feature: OMPGdmConversionStorage AEAZ-2759

  Scenario: Full Load curation

    Given I import "/plan/cns_dp_price" by keyFields "sourceSystem,localMaterialNumber,currency,country,fromDate"
      | localMaterialNumber | currency | country | fromDate | salesPrice  | sourceSystem |
      | 000000000000002885  | BRL      | AT      | 201801   | 1286.604828 | CONS_LATAM   |
      | 000000000000002886  | BRL      | AU      | 201802   | 1265.14621  | CONS_LATAM   |
      | 000000000000002887  | BRL      | AW      | 201803   |             | CONS_LATAM   |
      | 000000000000002888  | BRL      | AX      | 201804   | 1265.2345   | CONS_LATAM   |
      | 000000000000002889  | BRL      | AZ      | 201805   | 1265.234b   | CONS_LATAM   |
    And I wait "/plan/cns_dp_price" Async Queue complete

    Given I import "/edm/country_v1" by keyFields "localCountry"
      | countryCode | localCountry |
      | AT          | AT           |
      | AU          | AU           |
      | AW          | AW           |
      | AX          | AX           |
    And I wait "/edm/country_v1" Async Queue complete

    Given I import "/edm/jnj_calendar_v1" by keyFields "fiscalPeriod"
      | fiscalPeriod | weekFromDate | weekToDate |
      | 201801       | 2018-01-01   | 2018-01-08 |
      | 201801       | 2018-01-08   | 2018-01-15 |
      | 201801       | 2018-01-15   | 2018-01-22 |
      | 201801       | 2018-01-22   | 2018-01-29 |
      | 201802       | 2018-01-29   | 2018-02-05 |
      | 201802       | 2018-02-05   | 2018-02-12 |
      | 201802       | 2018-01-12   | 2018-02-19 |
      | 201802       | 2018-01-19   | 2018-02-26 |
      | 201803       | 2018-02-26   | 2018-03-05 |
      | 201803       | 2018-03-05   | 2018-03-12 |
      | 201803       | 2018-03-12   | 2018-03-19 |
      | 201803       | 2018-03-19   | 2018-03-26 |
      | 201803       | 2018-03-26   | 2018-04-02 |
      | 201804       | 2018-04-02   | 2018-04-09 |
      | 201804       | 2018-04-09   | 2018-04-16 |
      | 201804       | 2018-04-16   | 2018-04-23 |
      | 201804       | 2018-04-23   | 2018-04-30 |
    And I wait "/edm/jnj_calendar_v1" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | localMaterialNumber | sourceSystem | localDpParentCode |
      | 000000000000002885  | CONS_LATAM   | 123ABC            |
      | 000000000000002886  | CONS_LATAM   | 123ABC            |
      | 000000000000002887  | CONS_LATAM   | 456DEF            |
      | 000000000000002888  | CONS_LATAM   | 456DEF            |

    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/plan/cns_cust_channel" by keyFields "salesOrg"
      | channel | salesOrg |
      | AT001   | AT       |
      | AU002   | AU       |
      | AW003   | AW       |
      | AX004   | AX       |
    And I wait "/plan/cns_cust_channel" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmConversionStorage.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMConversionStorage.tsv"

    Then I check file data for filename "GDMConversionStorage.tsv" by keyFields "sourceSystem,aggregationId,currencyId,dueDate,forecastUploadId,fromDueDate"
      | aggregationId      | conversionFactor | currencyId | dueDate             | fromDueDate         | value     | unitId |
      | LA_123ABC-AT001-AT | SALESPRICE       | BRL        | 2018/01/01 00:00:00 | 2018/01/08 00:00:00 | 1275.876  |        |
      | LA_123ABC-AT001-AT | SALESPRICE       | BRL        | 2018/01/08 00:00:00 | 2018/01/15 00:00:00 | 1275.876  |        |
      | LA_123ABC-AT001-AT | SALESPRICE       | BRL        | 2018/01/15 00:00:00 | 2018/01/22 00:00:00 | 1275.876  |        |
      | LA_123ABC-AT001-AT | SALESPRICE       | BRL        | 2018/01/22 00:00:00 | 2018/01/29 00:00:00 | 1275.876  |        |
      | LA_123ABC-AU002-AU | SALESPRICE       | BRL        | 2018/01/29 00:00:00 | 2018/02/05 00:00:00 | 1275.876  |        |
      | LA_123ABC-AU002-AU | SALESPRICE       | BRL        | 2018/02/05 00:00:00 | 2018/02/12 00:00:00 | 1275.876  |        |
      | LA_123ABC-AU002-AU | SALESPRICE       | BRL        | 2018/02/12 00:00:00 | 2018/02/19 00:00:00 | 1275.876  |        |
      | LA_123ABC-AU002-AU | SALESPRICE       | BRL        | 2018/02/19 00:00:00 | 2018/02/26 00:00:00 | 1275.876  |        |
      | LA_456DEF-AW003-AW | SALESPRICE       | BRL        | 2018/02/26 00:00:00 | 2018/03/05 00:00:00 | 1265.2345 |        |
      | LA_456DEF-AW003-AW | SALESPRICE       | BRL        | 2018/03/05 00:00:00 | 2018/03/12 00:00:00 | 1265.2345 |        |
      | LA_456DEF-AW003-AW | SALESPRICE       | BRL        | 2018/03/12 00:00:00 | 2018/03/19 00:00:00 | 1265.2345 |        |
      | LA_456DEF-AW003-AW | SALESPRICE       | BRL        | 2018/03/19 00:00:00 | 2018/03/26 00:00:00 | 1265.2345 |        |
      | LA_456DEF-AX003-AX | SALESPRICE       | BRL        | 2018/03/26 00:00:00 | 2018/04/02 00:00:00 | 1265.2345 |        |
      | LA_456DEF-AX003-AX | SALESPRICE       | BRL        | 2018/04/02 00:00:00 | 2018/04/09 00:00:00 | 1265.2345 |        |
      | LA_456DEF-AX003-AX | SALESPRICE       | BRL        | 2018/04/09 00:00:00 | 2018/04/16 00:00:00 | 1265.2345 |        |
      | LA_456DEF-AX003-AX | SALESPRICE       | BRL        | 2018/04/16 00:00:00 | 2018/04/23 00:00:00 | 1265.2345 |        |
      | LA_456DEF-AX003-AX | SALESPRICE       | BRL        | 2018/04/23 00:00:00 | 2018/04/30 00:00:00 | 1265.2345 |        |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID             | errorCode | sourceSystem | businessArea | key1               | key2       | key3 | key4 | key5   | errorValue                 |
      | DP             | OMPGdmConversionStorage | C5        | omp          |              | 000000000000002889 | CONS_LATAM | BRL  | AZ   | 201805 | Sales price is non-numeric |

    And I compare the number of records between "/plan/cns_dp_price" and "/omp/gdm_conversion_storage,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/omp/gdm_conversion_storage"

    And I will remove all data with region "/plan/edm_failed_data"

    And I will remove the test file on sink application "GDMConversionStorage.tsv"

