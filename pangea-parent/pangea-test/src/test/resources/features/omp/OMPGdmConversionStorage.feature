@pangea_test @AEAZ-3688
Feature: OMPGdmConversionStorage AEAZ-3688

  @Scenario1
  Scenario: J1
    And I will remove the test file on sink application "GDMConversionStorage.tsv"
    Given I import "/plan/cns_dp_price" by keyFields "sourceSystem,localMaterialNumber,currency,country,fromDate"
      | localMaterialNumber | currency | country | fromDate | salesPrice  | sourceSystem  |
      | 000000000000002885  | BRL      | AT      | 201801   | 1286.604828 | sourceSystem1 |
      | 000000000000002886  | BRL      | AU      | 201802   | 1265.14621  | sourceSystem2 |
      #  cns_dp_price-localMaterialNumber = material_global_v1-localMaterialNumber
      | 000000000000002887  | BRL      | AT      | 201801   | 1265.14621  | sourceSystem2 |
      #  cns_dp_price-sourceSystem = material_global_v1-sourceSystem
      | 000000000000002888  | BRL      | AU      | 201802   | 1265.14621  | sourceSystem2 |
      #  cn_dp_price-country =  country_v1-localCountry
      | 000000000000002889  | BRL      | AK      | 201801   | 1265.14621  | sourceSystem2 |
      #country_v1-countryCode =  1st two chars. of cns_cust_channel-salesOrg
      | 000000000000002890  | BRL      | AZ      | 201802   | 1265.14621  | sourceSystem1 |

    And I wait "/plan/cns_dp_price" Async Queue complete

    Given I import "/edm/country_v1" by keyFields "localCountry"
      | countryCode | localCountry |
      | AT          | AT           |
      | AU          | AU           |
      #country_v1-countryCode =  1st two chars. of cns_cust_channel-salesOrg
      | AZ          | AZ           |
    And I wait "/edm/country_v1" Async Queue complete

    Given I import "/edm/jnj_calendar_v1" by keyFields "fiscalPeriod,calWeek,noOfWeek"
      | fiscalPeriod | weekFromDate | weekToDate | calWeek | noOfWeek |
      | 201801       | 2018-01-01   | 2018-01-08 | 001     | 4        |
      | 201802       | 2018-01-29   | 2018-02-05 | 005     | 4        |
    And I wait "/edm/jnj_calendar_v1" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | localMaterialNumber | sourceSystem  | localDpParentCode | localBaseUom |
      | 000000000000002885  | sourceSystem1 | 123ABC            | BaseUom1     |
      | 000000000000002886  | sourceSystem2 | 123ABC            | BaseUom1     |
      | 000000000000002888  | sourceSystem3 | 234BCD            | BaseUom1     |
      | 000000000000002889  | sourceSystem2 | 234BCD            | BaseUom2     |
      | 000000000000002890  | sourceSystem1 | 123ABC            | BaseUom2     |
    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/plan/cns_cust_channel" by keyFields "salesOrg"
      | channel | salesOrg |
      | AT001   | AT001    |
      | AU001   | AU001    |
    And I wait "/plan/cns_cust_channel" Async Queue complete

    Given  I import "/plan/cns_plan_unit" by keyFields "localUom"
      | localUom | unit  |
      | BaseUom1 | unit1 |
      | BaseUom2 | unit2 |
    And I wait "/plan/cns_plan_unit" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmConversionStorage.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMConversionStorage.tsv"

    Then I check file data for filename "GDMConversionStorage.tsv" by keyFields "sourceSystem,aggregationId,currencyId,dueDate,forecastUploadId,fromDueDate"
      | aggregationId      | conversionFactor | currencyId | fromDueDate         | dueDate             | value    | unitId |
      | LA_123ABC-AT001-AT | SALESPRICE       | BRL        | 2018-01-01 00:00:00 | 2018-01-08 00:00:00 | 1272.299 | unit1  |
      | LA_123ABC-AU001-AU | SALESPRICE       | BRL        | 2018-01-29 00:00:00 | 2018-02-05 00:00:00 | 1272.299 | unit1  |
    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID             | errorCode | sourceSystem | businessArea | key1               | key2          | key3 | key4 | key5   | errorValue                                                        |
      | DP             | OMPGdmConversionStorage | J1        | omp          |              | 000000000000002887 | sourceSystem2 | BRL  | AT   | 201801 | sourceSystem / dpParent code / channel / countryCode do not exist |
      | DP             | OMPGdmConversionStorage | J1        | omp          |              | 000000000000002888 | sourceSystem2 | BRL  | AU   | 201802 | sourceSystem / dpParent code / channel / countryCode do not exist |
      | DP             | OMPGdmConversionStorage | J1        | omp          |              | 000000000000002889 | sourceSystem2 | BRL  | AK   | 201801 | sourceSystem / dpParent code / channel / countryCode do not exist |
      | DP             | OMPGdmConversionStorage | J1        | omp          |              | 000000000000002890 | sourceSystem1 | BRL  | AZ   | 201802 | sourceSystem / dpParent code / channel / countryCode do not exist |
    And I delete the test data
    And I will remove all data with region "/omp/gdm_conversion_storage"
    And I will remove all data with region "/plan/edm_failed_data"

  @Scenario2
  Scenario: C2 & C3
    And I will remove the test file on sink application "GDMConversionStorage.tsv"
    Given I import "/plan/cns_dp_price" by keyFields "sourceSystem,localMaterialNumber,currency,country,fromDate"
      | localMaterialNumber | currency | country | fromDate | salesPrice  | sourceSystem  |
      | 000000000000002885  | BRL      | AT      | 201801   | 1286.604828 | sourceSystem1 |
      | 000000000000002886  | BRL      | AU      | 201802   | 1265.14621  | sourceSystem2 |
      #  cns_dp_price-fromDate = jnj_calendar_v1-fiscalPeriod
      | 000000000000002887  | BRL      | AW      | 201803   | 1265.14621  | sourceSystem2 |

    And I wait "/plan/cns_dp_price" Async Queue complete

    Given I import "/edm/country_v1" by keyFields "localCountry"
      | countryCode | localCountry |
      | AT          | AT           |
      | AU          | AU           |
      | AW          | AW           |
    And I wait "/edm/country_v1" Async Queue complete

    Given I import "/edm/jnj_calendar_v1" by keyFields "fiscalPeriod,calWeek,noOfWeek"
      | fiscalPeriod | weekFromDate | weekToDate | calWeek | noOfWeek |
      | 201801       | 2018-01-01   | 2018-01-08 | 001     | 4        |
      | 201801       | 2018-01-08   | 2018-01-15 | 002     | 4        |
      | 201801       | 2018-01-15   | 2018-01-22 | 003     | 4        |
      | 201801       | 2018-01-22   | 2018-01-29 | 004     | 4        |
      | 201802       | 2018-01-29   | 2018-02-05 | 005     | 4        |
      | 201802       | 2018-02-05   | 2018-02-12 | 006     | 4        |
      | 201802       | 2018-02-12   | 2018-02-19 | 007     | 4        |
      | 201802       | 2018-02-19   | 2018-02-26 | 008     | 4        |
    And I wait "/edm/jnj_calendar_v1" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | localMaterialNumber | sourceSystem  | localDpParentCode | localBaseUom |
      | 000000000000002885  | sourceSystem1 | 123ABC            | BaseUom1     |
      | 000000000000002886  | sourceSystem2 | 123ABC            | BaseUom1     |
      | 000000000000002887  | sourceSystem2 | 123ABC            | BaseUom1     |
    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/plan/cns_cust_channel" by keyFields "salesOrg"
      | channel | salesOrg |
      | AT001   | AT001    |
      | AU001   | AU001    |
    And I wait "/plan/cns_cust_channel" Async Queue complete

    Given  I import "/plan/cns_plan_unit" by keyFields "localUom"
      | localUom | unit  |
      | BaseUom1 | unit1 |
      | BaseUom2 | unit2 |
    And I wait "/plan/cns_plan_unit" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmConversionStorage.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMConversionStorage.tsv"

    Then I check file data for filename "GDMConversionStorage.tsv" by keyFields "sourceSystem,aggregationId,currencyId,dueDate,forecastUploadId,fromDueDate"
      | aggregationId      | conversionFactor | currencyId | fromDueDate         | dueDate             | value    | unitId |
      | LA_123ABC-AT001-AT | SALESPRICE       | BRL        | 2018-01-01 00:00:00 | 2018-01-08 00:00:00 | 1272.299 | unit1  |
      | LA_123ABC-AT001-AT | SALESPRICE       | BRL        | 2018-01-08 00:00:00 | 2018-01-15 00:00:00 | 1272.299 | unit1  |
      | LA_123ABC-AT001-AT | SALESPRICE       | BRL        | 2018-01-15 00:00:00 | 2018-01-22 00:00:00 | 1272.299 | unit1  |
      | LA_123ABC-AT001-AT | SALESPRICE       | BRL        | 2018-01-22 00:00:00 | 2018-01-29 00:00:00 | 1272.299 | unit1  |
      | LA_123ABC-AU001-AU | SALESPRICE       | BRL        | 2018-01-29 00:00:00 | 2018-02-05 00:00:00 | 1272.299 | unit1  |
      | LA_123ABC-AU001-AU | SALESPRICE       | BRL        | 2018-02-05 00:00:00 | 2018-02-12 00:00:00 | 1272.299 | unit1  |
      | LA_123ABC-AU001-AU | SALESPRICE       | BRL        | 2018-02-12 00:00:00 | 2018-02-19 00:00:00 | 1272.299 | unit1  |
      | LA_123ABC-AU001-AU | SALESPRICE       | BRL        | 2018-02-19 00:00:00 | 2018-02-26 00:00:00 | 1272.299 | unit1  |


    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |
    And I delete the test data
    And I will remove all data with region "/omp/gdm_conversion_storage"
    And I will remove all data with region "/plan/edm_failed_data"

#--------------------------------------------------------------------------------------------------------------------------
  @Scenario3
  Scenario: C5 & C6
    And I will remove the test file on sink application "GDMConversionStorage.tsv"
    Given I import "/plan/cns_dp_price" by keyFields "sourceSystem,localMaterialNumber,currency,country,fromDate"
      | localMaterialNumber | currency | country | fromDate | salesPrice  | sourceSystem  |
      | 000000000000002885  | BRL      | AT      | 201801   | 1286.604828 | sourceSystem1 |
      | 000000000000002886  | BRL      | AU      | 201802   | 1265.14621  | sourceSystem2 |
      #  cns_dp_price-salesPrice = '0' or emply
      | 000000000000002888  | BRL      | AZ      | 201803   | 0           | sourceSystem2 |
      #  cns_dp_price-salesPrice = '0' or emply
      | 000000000000002889  | BRL      | AK      | 201803   |             | sourceSystem2 |
      | 000000000000002890  | BRL      | AY      | 201802   | 4563.234    | sourceSystem3 |
      | 000000000000002891  | BRL      | AR      | 201801   | 4563.234    | sourceSystem1 |

    And I wait "/plan/cns_dp_price" Async Queue complete

    Given I import "/edm/country_v1" by keyFields "localCountry"
      | countryCode | localCountry |
      | AT          | AT           |
      | AU          | AU           |
      | AZ          | AZ           |
      | AK          | AK           |
      | AY          | AY           |
      | AR          | AR           |
    And I wait "/edm/country_v1" Async Queue complete

    Given I import "/edm/jnj_calendar_v1" by keyFields "fiscalPeriod,calWeek,noOfWeek"
      | fiscalPeriod | weekFromDate | weekToDate | calWeek | noOfWeek |
      | 201801       | 2018-01-01   | 2018-01-08 | 001     | 4        |
      | 201801       | 2018-01-08   | 2018-01-15 | 002     | 4        |
      | 201801       | 2018-01-15   | 2018-01-22 | 003     | 4        |
      | 201801       | 2018-01-22   | 2018-01-29 | 004     | 4        |
      | 201802       | 2018-01-29   | 2018-02-05 | 005     | 4        |
      | 201802       | 2018-02-05   | 2018-02-12 | 006     | 4        |
      | 201802       | 2018-02-12   | 2018-02-19 | 007     | 4        |
      | 201802       | 2018-02-19   | 2018-02-26 | 008     | 4        |
      | 201803       | 2018-02-21   | 2018-03-01 | 008     | 4        |
      | 201803       | 2018-03-01   | 2018-03-08 | 008     | 4        |
      | 201803       | 2018-03-08   | 2018-03-15 | 008     | 4        |
      | 201803       | 2018-03-15   | 2018-03-22 | 008     | 4        |
      | 201803       | 2018-03-22   | 2018-03-29 | 008     | 4        |
    And I wait "/edm/jnj_calendar_v1" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | localMaterialNumber | sourceSystem  | localDpParentCode | localBaseUom |
      | 000000000000002885  | sourceSystem1 | 123ABC            | BaseUom1     |
      | 000000000000002886  | sourceSystem2 | 123ABC            | BaseUom1     |
      | 000000000000002888  | sourceSystem2 | 123ABC            | BaseUom1     |
      | 000000000000002889  | sourceSystem2 | 123ABC            | BaseUom1     |
      | 000000000000002890  | sourceSystem3 | 234DEF            | BaseUom2     |
      | 000000000000002891  | sourceSystem1 | 234DEF            | BaseUom2     |
      #  cns_plan_unit-localUOM = material_global_v1-localBaseUOM
      | 000000000000002892  | sourceSystem1 | 234DEF            | BaseUom3     |
    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/plan/cns_cust_channel" by keyFields "salesOrg"
      | channel | salesOrg |
      | AT001   | AT001    |
      | AU001   | AU001    |
      | AZ001   | AZ001    |
      | AK001   | AK001    |
      | AY001   | AY001    |
      | AR001   | AR001    |
    And I wait "/plan/cns_cust_channel" Async Queue complete

    Given  I import "/plan/cns_plan_unit" by keyFields "localUom"
      | localUom | unit  |
      | BaseUom1 | unit1 |
      | BaseUom2 | unit2 |
      | BaseUom4 | unit4 |
    And I wait "/plan/cns_plan_unit" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmConversionStorage.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMConversionStorage.tsv"

    Then I check file data for filename "GDMConversionStorage.tsv" by keyFields "sourceSystem,aggregationId,currencyId,dueDate,forecastUploadId,fromDueDate"
      | aggregationId      | conversionFactor | currencyId | fromDueDate         | dueDate             | value    | unitId |
      | LA_123ABC-AT001-AT | SALESPRICE       | BRL        | 2018-01-01 00:00:00 | 2018-01-08 00:00:00 | 1275.876 | unit1 |
      | LA_123ABC-AT001-AT | SALESPRICE       | BRL        | 2018-01-08 00:00:00 | 2018-01-15 00:00:00 | 1275.876 | unit1 |
      | LA_123ABC-AT001-AT | SALESPRICE       | BRL        | 2018-01-15 00:00:00 | 2018-01-22 00:00:00 | 1275.876 | unit1 |
      | LA_123ABC-AT001-AT | SALESPRICE       | BRL        | 2018-01-22 00:00:00 | 2018-01-29 00:00:00 | 1275.876 | unit1 |
      | LA_234DEF-AY001-AY | SALESPRICE       | BRL        | 2018-01-29 00:00:00 | 2018-02-05 00:00:00 | 4563.234 | unit2 |
      | LA_234DEF-AY001-AY | SALESPRICE       | BRL        | 2018-02-05 00:00:00 | 2018-02-12 00:00:00 | 4563.234 | unit2 |
      | LA_234DEF-AY001-AY | SALESPRICE       | BRL        | 2018-02-12 00:00:00 | 2018-02-19 00:00:00 | 4563.234 | unit2 |
      | LA_234DEF-AY001-AY | SALESPRICE       | BRL        | 2018-02-19 00:00:00 | 2018-02-26 00:00:00 | 4563.234 | unit2 |
      | LA_123ABC-AU001-AU | SALESPRICE       | BRL        | 2018-01-29 00:00:00 | 2018-02-05 00:00:00 | 1275.876 | unit1 |
      | LA_123ABC-AU001-AU | SALESPRICE       | BRL        | 2018-02-05 00:00:00 | 2018-02-12 00:00:00 | 1275.876 | unit1 |
      | LA_123ABC-AU001-AU | SALESPRICE       | BRL        | 2018-02-12 00:00:00 | 2018-02-19 00:00:00 | 1275.876 | unit1 |
      | LA_123ABC-AU001-AU | SALESPRICE       | BRL        | 2018-02-19 00:00:00 | 2018-02-26 00:00:00 | 1275.876 | unit1 |
      | LA_234DEF-AR001-AR | SALESPRICE       | BRL        | 2018-01-01 00:00:00 | 2018-01-08 00:00:00 | 4563.234 | unit2 |
      | LA_234DEF-AR001-AR | SALESPRICE       | BRL        | 2018-01-08 00:00:00 | 2018-01-15 00:00:00 | 4563.234 | unit2 |
      | LA_234DEF-AR001-AR | SALESPRICE       | BRL        | 2018-01-15 00:00:00 | 2018-01-22 00:00:00 | 4563.234 | unit2 |
      | LA_234DEF-AR001-AR | SALESPRICE       | BRL        | 2018-01-22 00:00:00 | 2018-01-29 00:00:00 | 4563.234 | unit2 |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID             | errorCode | sourceSystem | businessArea | key1               | key2          | key3 | key4 | key5   | errorValue                 |
#      | DP             | OMPGdmConversionStorage | C5        | omp          |              | 000000000000002888 | sourceSystem2 | BRL  | AZ   | 201803 | Sales price is non-numeric |
#      | DP             | OMPGdmConversionStorage | C5        | omp          |              | 000000000000002889 | sourceSystem2 | BRL  | AZ   | 201803 | Sales price is non-numeric |

    And I delete the test data
    And I will remove all data with region "/omp/gdm_conversion_storage"
    And I will remove all data with region "/plan/edm_failed_data"








