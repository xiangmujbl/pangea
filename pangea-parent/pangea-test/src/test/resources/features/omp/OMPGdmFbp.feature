@pangea_test @AEAZ-4062
Feature:  OMPGdmfbp-Curation AEAZ-4062
  @Scenario1
  Scenario: Pick unique FPB data from cns_fin_plan_qty and cns_fin_plan_val 

    And I will remove the test file on sink application "GDMFbp.tsv"

    Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | sourceSystem | localMaterialNumber | localDpParentCode  | localBaseUom |
      | CONS_LATAM   | 000000000000000945  | 178962124094540036 | KI           |
      | CONS_LATAM   | 000000000000002886  | 178962124094540045 | KI           |
    And I wait "/edm/material_global_v1" Async Queue complete

    #cns_fin_plan_qty-localMaterialNumber=material_global_v1-localMaterialNumber
    Given I import "/plan/cns_fin_plan_qty" by keyFields "localMaterialNumber,identifier,yearMonth"
      | localMaterialNumber | identifier | country | unitId | quantity    | yearMonth | sourceSystem |
      | 000000000000000945  | FBP        | BR      | ZUM    | 154.6566667 | 201805    | CONS_LATAM   |
      | 000000000000002886  | FBP        | BR      | ZUM    | 417.573     | 201806    | CONS_LATAM   |
    And I wait "/plan/cns_fin_plan_qty" Async Queue complete

   #cns_fin_plan_val-localMaterialNumber=material_global_v1-localMaterialNumber
    Given I import "/plan/cns_fin_plan_val" by keyFields "localMaterialNumber,identifier,yearMonth"
      | localMaterialNumber | identifier | value       | yearMonth | currency |
      | 000000000000000945  | FBP        | 745400.866  | 201805    | BRL      |
      | 000000000000002886  | FBP        | 4658755.412 | 201806    | BRL      |
    And I wait "/plan/cns_fin_plan_val" Async Queue complete

    #country_v1-localCountry=cns_fin_plan_qty-country
    Given I import "/edm/country_v1" by keyFields "sourceSystem,localCountry"
      | sourceSystem | localCountry | countryCode |
      | CONS_LATAM   | BR           | BR          |
      | CONS_LATAM   | BR           | BR          |
    And I wait "/edm/country_v1" Async Queue complete

    #currency_v1-localCurrency=cns_fin_plan_val-currency
    Given I import "/edm/currency_v1" by keyFields "sourceSystem,localCurrency"
      | sourceSystem | localCurrency | currencyCode | currencyName   | isoNumeric |
      | CONS_LATAM   | BRL           | CADP         | Brazilian Real | -          |
      | CONS_LATAM   | BRL           | CAED         | Brazilian Real | -          |

    #cns_plan_parameter-attribute=material_global_v1-sourceSystem
    #cns_plan_parameter-sourceSystem=material_global_v1-sourceSystem
    Given I import "/plan/cns_plan_parameter" by keyFields "attribute,dataObject,parameter,parameterValue,sourceSystem"
      | attribute  | dataObject  | parameter | parameterValue | sourceSystem |
      | CONS_LATAM | SEND_TO_OMP | Division  | 10             | CONS_LATAM   |


    And I wait "/plan/cns_plan_parameter" Async Queue complete

    #jnj_calendar_v1-fiscalPeriod=cns_fin_plan_qty-yearMonth
    #jnj_calendar_v1-fiscalPeriod=cns_fin_plan_val-yearMonth
    Given I import "/edm/jnj_calendar_v1" by keyFields "calWeek,fiscalPeriod,noOfWeek"
      | calWeek | fiscalPeriod | noOfWeek | weekFromDate | weekToDate |
      | 201914  | 201805       | 4        | 2019-04-01   | 2019-04-08 |
      | 201915  | 201806       | 4        | 2019-04-08   | 2019-04-15 |

    And I wait "/edm/jnj_calendar_v1" Async Queue complete

    #material_auom_v1-localMaterialNumber=cns_fin_plan_qty-localMaterialNumber
    #material_auom_v1-localAuom=cns_fin_plan_qty-unitId
    #material_auom_v1-sourceSystem=cns_fin_plan_qty-sourceSystem
    Given I import "/edm/material_auom_v1" by keyFields "localMaterialNumber,localAuom,sourceSystem"
      | localMaterialNumber | localAuom | sourceSystem | localNumerator | localDenominator |
      | 000000000000000945  | ZUM       | CONS_LATAM   | 1              | 12               |
      | 000000000000002886  | ZUM       | CONS_LATAM   | 1              | 12               |

    And I wait "/edm/material_auom_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmFbp.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMFbp.tsv"

    Then I check file data for filename "GDMFbp.tsv" by keyFields "fbpId"
      | fbpId                                          | countryId | currencyId | dueDate             | fromDueDate         | productId             | value | volume |
      | 10_178962124094540036-2019/04/01 00:00:00 | BR        | BRL        | 2019/04/08 00:00:00 | 2019/04/01 00:00:00 | 10_178962124094540036 |   4  |   12.888    |
      | 10_178962124094540045-2019/04/08 00:00:00 | BR        | BRL        | 2019/04/15 00:00:00 | 2019/04/08 00:00:00 | 10_178962124094540045 |   4   |   34.798     |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    #And I compare the number of records between "/plan/cns_fin_plan_qty" and "/omp/gdm_fbp,/plan/edm_failed_data"

   Then I delete the test data

    And I will remove all data with region "/omp/gdm_fbp"

    And I will remove all data with region "/plan/edm_failed_data"

  @Scenario2
  Scenario: Reject the FPB record if localDpParentCode is empty  for the associated material 

    And I will remove the test file on sink application "GDMFbp.tsv"

    Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | sourceSystem | localMaterialNumber | localDpParentCode  | localBaseUom |
      | CONS_LATAM   | 000000000000000945  | 178962124094540036 | KI           |
      | CONS_LATAM   | 000000000000002886  | 178962124094540045 | KI           |
      | CONS_LATAM   | 000000000000002887  |                       | KI           |
    And I wait "/edm/material_global_v1" Async Queue complete

    #cns_fin_plan_qty-localMaterialNumber=material_global_v1-localMaterialNumber
    Given I import "/plan/cns_fin_plan_qty" by keyFields "localMaterialNumber,identifier,yearMonth"
      | localMaterialNumber | identifier | country | unitId | quantity    | yearMonth | sourceSystem |
      | 000000000000000945  | FBP        | BR      | ZUM    | 154.6566667 | 201805    | CONS_LATAM   |
      | 000000000000002886  | FBP        | BR      | ZUM    | 417.573     | 201806    | CONS_LATAM   |
      | 000000000000002887  | FBP        | BR      | ZUM    | 417.573     | 201807    | CONS_LATAM   |
    And I wait "/plan/cns_fin_plan_qty" Async Queue complete

   #cns_fin_plan_val-localMaterialNumber=material_global_v1-localMaterialNumber
    Given I import "/plan/cns_fin_plan_val" by keyFields "localMaterialNumber,identifier,yearMonth"
      | localMaterialNumber | identifier | value       | yearMonth | currency |
      | 000000000000000945  | FBP        | 745400.866  | 201805    | BRL      |
      | 000000000000002886  | FBP        | 4658755.412 | 201806    | BRL      |
      | 000000000000002887  | FBP        | 4658755.412 | 201807    | BRL      |
    And I wait "/plan/cns_fin_plan_val" Async Queue complete

    #country_v1-localCountry=cns_fin_plan_qty-country
    Given I import "/edm/country_v1" by keyFields "sourceSystem,localCountry"
      | sourceSystem | localCountry | countryCode |
      | CONS_LATAM   | BR           | BR          |
      | CONS_LATAM   | BR           | BR          |
      | CONS_LATAM   | BR           | BR          |
    And I wait "/edm/country_v1" Async Queue complete

    #currency_v1-localCurrency=cns_fin_plan_val-currency
    Given I import "/edm/currency_v1" by keyFields "sourceSystem,localCurrency"
      | sourceSystem | localCurrency | currencyCode | currencyName   | isoNumeric |
      | CONS_LATAM   | BRL           | CADP         | Brazilian Real | -          |
      | CONS_LATAM   | BRL           | CAED         | Brazilian Real | -          |
      | CONS_LATAM   | BRL           | CAED         | Brazilian Real | -          |

    #cns_plan_parameter-attribute=material_global_v1-sourceSystem
    #cns_plan_parameter-sourceSystem=material_global_v1-sourceSystem
    Given I import "/plan/cns_plan_parameter" by keyFields "attribute,dataObject,parameter,parameterValue,sourceSystem"
      | attribute  | dataObject  | parameter | parameterValue | sourceSystem |
      | CONS_LATAM | SEND_TO_OMP | Division  | 10             | CONS_LATAM   |


    And I wait "/plan/cns_plan_parameter" Async Queue complete

    #jnj_calendar_v1-fiscalPeriod=cns_fin_plan_qty-yearMonth
    #jnj_calendar_v1-fiscalPeriod=cns_fin_plan_val-yearMonth
    Given I import "/edm/jnj_calendar_v1" by keyFields "calWeek,fiscalPeriod,noOfWeek"
      | calWeek | fiscalPeriod | noOfWeek | weekFromDate | weekToDate |
      | 201914  | 201805       | 4        | 2019-04-01   | 2019-04-08 |
      | 201915  | 201806       | 4        | 2019-04-08   | 2019-04-15 |
      | 201915  | 201807       | 4        | 2019-04-08   | 2019-04-15 |

    And I wait "/edm/jnj_calendar_v1" Async Queue complete

    #material_auom_v1-localMaterialNumber=cns_fin_plan_qty-localMaterialNumber
    #material_auom_v1-localAuom=cns_fin_plan_qty-unitId
    #material_auom_v1-sourceSystem=cns_fin_plan_qty-sourceSystem
    Given I import "/edm/material_auom_v1" by keyFields "localMaterialNumber,localAuom,sourceSystem"
      | localMaterialNumber | localAuom | sourceSystem | localNumerator | localDenominator |
      | 000000000000000945  | ZUM       | CONS_LATAM   | 1              | 12               |
      | 000000000000002886  | ZUM       | CONS_LATAM   | 1              | 12               |
      | 000000000000002887  | ZUM       | CONS_LATAM   | 1              | 12               |

    And I wait "/edm/material_auom_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmFbp.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMFbp.tsv"

    Then I check file data for filename "GDMFbp.tsv" by keyFields "fbpId"
      | fbpId                                          | countryId | currencyId | dueDate             | fromDueDate         | productId             | value | volume |
      | 10_178962124094540036-2019/04/01 00:00:00 | BR        | BRL        | 2019/04/08 00:00:00 | 2019/04/01 00:00:00 | 10_178962124094540036 |   4  |   12.888    |
      | 10_178962124094540045-2019/04/08 00:00:00 | BR        | BRL        | 2019/04/15 00:00:00 | 2019/04/08 00:00:00 | 10_178962124094540045 |   4   |   34.798     |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    #And I compare the number of records between "/plan/cns_fin_plan_qty" and "/omp/gdm_fbp,/plan/edm_failed_data"

    Then I delete the test data

    And I will remove all data with region "/omp/gdm_fbp"

    And I will remove all data with region "/plan/edm_failed_data"


  @Scenario3
  Scenario: No of output records for each localDpParentCode per month should be equal to the jnj calendar's no of weeks per month and qunatity has to be distributed equally between the weeks/month

    And I will remove the test file on sink application "GDMFbp.tsv"

    Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | sourceSystem | localMaterialNumber | localDpParentCode  | localBaseUom |
      | CONS_LATAM   | 000000000000000945  | 178962124094540036 | KI           |
      | CONS_LATAM   | 000000000000002886  | 178962124094540045 | KI           |
      | CONS_LATAM   | 000000000000002887  | 178962124094540045 | KI           |
      | CONS_LATAM   | 000000000000002888  | 178962124094540045 | KI           |
    And I wait "/edm/material_global_v1" Async Queue complete

    #cns_fin_plan_qty-localMaterialNumber=material_global_v1-localMaterialNumber
    Given I import "/plan/cns_fin_plan_qty" by keyFields "localMaterialNumber,identifier,yearMonth"
      | localMaterialNumber | identifier | country | unitId | quantity    | yearMonth | sourceSystem |
      | 000000000000000945  | FBP        | BR      | ZUM    | 154.6566667 | 201805    | CONS_LATAM   |
      | 000000000000002886  | FBP        | BR      | ZUM    | 417.573     | 201806    | CONS_LATAM   |
      | 000000000000002887  | FBP        | BR      | ZUM    | 417.573     | 201807    | CONS_LATAM   |
      | 000000000000002888  | FBP        | BR      | ZUM    | 417.573     | 201808    | CONS_LATAM   |
    And I wait "/plan/cns_fin_plan_qty" Async Queue complete

   #cns_fin_plan_val-localMaterialNumber=material_global_v1-localMaterialNumber
    Given I import "/plan/cns_fin_plan_val" by keyFields "localMaterialNumber,identifier,yearMonth"
      | localMaterialNumber | identifier | value       | yearMonth | currency |
      | 000000000000000945  | FBP        | 745400.866  | 201805    | BRL      |
      | 000000000000002886  | FBP        | 4658755.412 | 201806    | BRL      |
      | 000000000000002887  | FBP        | 4658755.412 | 201807    | BRL      |
      | 000000000000002888  | FBP        | 4658755.412 | 201808    | BRL      |
    And I wait "/plan/cns_fin_plan_val" Async Queue complete

    #country_v1-localCountry=cns_fin_plan_qty-country
    Given I import "/edm/country_v1" by keyFields "sourceSystem,localCountry"
      | sourceSystem | localCountry | countryCode |
      | CONS_LATAM   | BR           | BR          |
      | CONS_LATAM   | BR           | BR          |
      | CONS_LATAM   | BR           | BR          |
      | CONS_LATAM   | BR           | BR          |
    And I wait "/edm/country_v1" Async Queue complete

    #currency_v1-localCurrency=cns_fin_plan_val-currency
    Given I import "/edm/currency_v1" by keyFields "sourceSystem,localCurrency"
      | sourceSystem | localCurrency | currencyCode | currencyName   | isoNumeric |
      | CONS_LATAM   | BRL           | CADP         | Brazilian Real | -          |
      | CONS_LATAM   | BRL           | CAED         | Brazilian Real | -          |
      | CONS_LATAM   | BRL           | CADP         | Brazilian Real | -          |
      | CONS_LATAM   | BRL           | CAED         | Brazilian Real | -          |

    #cns_plan_parameter-attribute=material_global_v1-sourceSystem
    #cns_plan_parameter-sourceSystem=material_global_v1-sourceSystem
    Given I import "/plan/cns_plan_parameter" by keyFields "attribute,dataObject,parameter,parameterValue,sourceSystem"
      | attribute  | dataObject  | parameter | parameterValue | sourceSystem |
      | CONS_LATAM | SEND_TO_OMP | Division  | 10             | CONS_LATAM   |


    And I wait "/plan/cns_plan_parameter" Async Queue complete

    #jnj_calendar_v1-fiscalPeriod=cns_fin_plan_qty-yearMonth
    #jnj_calendar_v1-fiscalPeriod=cns_fin_plan_val-yearMonth
    Given I import "/edm/jnj_calendar_v1" by keyFields "calWeek,fiscalPeriod,noOfWeek"
      | calWeek | fiscalPeriod | noOfWeek | weekFromDate | weekToDate |
      | 201914  | 201805       | 4        | 2019-04-01   | 2019-04-08 |
      | 201915  | 201806       | 4        | 2019-04-08   | 2019-04-15 |
      | 201915  | 201807       | 4        | 2019-04-08   | 2019-04-15 |
      | 201915  | 201808       | 4        | 2019-04-08   | 2019-04-15 |
      | 201915  | 201809       | 4        | 2019-04-08   | 2019-04-15 |

    And I wait "/edm/jnj_calendar_v1" Async Queue complete

    #material_auom_v1-localMaterialNumber=cns_fin_plan_qty-localMaterialNumber
    #material_auom_v1-localAuom=cns_fin_plan_qty-unitId
    #material_auom_v1-sourceSystem=cns_fin_plan_qty-sourceSystem
    Given I import "/edm/material_auom_v1" by keyFields "localMaterialNumber,localAuom,sourceSystem"
      | localMaterialNumber | localAuom | sourceSystem | localNumerator | localDenominator |
      | 000000000000000945  | ZUM       | CONS_LATAM   | 1              | 12               |
      | 000000000000002886  | ZUM       | CONS_LATAM   | 1              | 12               |
      | 000000000000002887  | ZUM       | CONS_LATAM   | 1              | 12               |
      | 000000000000002888  | ZUM       | CONS_LATAM   | 1              | 12               |

    And I wait "/edm/material_auom_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmFbp.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMFbp.tsv"

    Then I check file data for filename "GDMFbp.tsv" by keyFields "fbpId"
      | fbpId                                          | countryId | currencyId | dueDate             | fromDueDate         | productId             | value | volume |
      | 10_178962124094540036-2019/04/01 00:00:00 | BR        | BRL        | 2019/04/08 00:00:00 | 2019/04/01 00:00:00 | 10_178962124094540036 |   4  |   12.888    |
      | 10_178962124094540045-2019/04/08 00:00:00 | BR        | BRL        | 2019/04/15 00:00:00 | 2019/04/08 00:00:00 | 10_178962124094540045 |   4   |   34.798     |
      | 10_178962124094540045-2019/04/08 00:00:00 | BR        | BRL        | 2019/04/15 00:00:00 | 2019/04/08 00:00:00 | 10_178962124094540045 |   4   |   34.798     |
      | 10_178962124094540045-2019/04/08 00:00:00 | BR        | BRL        | 2019/04/15 00:00:00 | 2019/04/08 00:00:00 | 10_178962124094540045 |   4   |   34.798     |
    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    #And I compare the number of records between "/plan/cns_fin_plan_qty" and "/omp/gdm_fbp,/plan/edm_failed_data"

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/omp/gdm_fbp"

    And I will remove all data with region "/plan/edm_failed_data"
