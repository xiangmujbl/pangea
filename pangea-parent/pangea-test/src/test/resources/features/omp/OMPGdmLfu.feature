@pangea_test @AEAZ-3215
Feature:  E.2.1.6 GDMLFU - Consumptionn

  As a Data user,
  I want EDG to create GDM file for GDMLFU and send to OMP
  so that data can be consumed by OMP for planning

  Background:delete all test data

    Then I delete the test data

    And I will remove all data with region "/omp/gdm_lfu"

    And I will remove all data with region "/edm/material_global_v1"

    And I will remove all data with region "/plan/cns_fin_plan_qty"

    And I will remove all data with region "/plan/cns_fin_plan_val"

    And I will remove all data with region "/edm/material_auom_v1"

    And I will remove all data with region "/edm/country_v1"

    And I will remove all data with region "/edm/currency_v1"

    And I will remove all data with region "/plan/cns_plan_parameter"

    And I will remove all data with region "/edm/jnj_calendar_v1"

    And I will remove the test file on sink application "GDMLFU.tsv"

    And I will remove all data with region "/plan/edm_failed_data"


  @Scenario1
  Scenario: only found record in cns_fin_plan_qty skip this record

    Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | sourceSystem | localMaterialNumber | localDpParentCode | localBaseUom |
      | CONS_LATAM   | 000000000000000001  | LDPC01            | LBU01        |
      | CONS_LATAM   | 000000000000000003  | LDPC03            | LBU03        |
      | CONS_LATAM   | 000000000000000004  |                   | LBU04        |
      | CONS_LATAM   | 000000000000000006  | LDPC06            | LBU06        |
    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/plan/cns_fin_plan_qty" by keyFields "localMaterialNumber,identifier"
      | localMaterialNumber | identifier | country | currency | unitId | quantity | yearMonth | sourceSystem |
      | 000000000000000001  | LFU        | AT01    | ADA      | LBU01  | 100      | 201901    | CONS_LATAM   |
      | 000000000000000003  | LFU        | AT03    | AEB      | LBU03  | 300      | 201801    | CONS_LATAM   |
      | 000000000000000004  | LFU        | AT04    | AEC      | LBU03  | 300      | 201801    | CONS_LATAM   |
      | 000000000000000005  | LFU        | AT05    | ADG      | LBU05  | 100      | 201901    | CONS_LATAM   |
      | 000000000000000006  | LFU        | AT06    | ADH      | LBU06  | 500      | 201901    | CONS_LATAM   |

    And I wait "/plan/cns_fin_plan_qty" Async Queue complete

    Given I import "/edm/material_auom_v1" by keyFields "localMaterialNumber,localAuom,sourceSystem"
      | localMaterialNumber | localAuom | sourceSystem | localNumerator | localDenominator |
      | 000000000000000001  | LBU01     | CONS_LATAM   | 100            | 10               |
      | 000000000000000002  | LBU02     | CONS_LATAM   | 100            | 10               |
      | 000000000000000003  | LBU03     | CONS_LATAM   | 100            | 10               |
      | 000000000000000005  | LBU05     | CONS_LATAM   | 10             | 10               |

    And I wait "/edm/material_auom_v1" Async Queue complete

    Given I import "/plan/cns_fin_plan_val" by keyFields "localMaterialNumber,identifier"
      | localMaterialNumber | identifier | value | yearMonth | country | currency |
      | 000000000000000002  | LFU        | 100   | 201901    | AT02    | ADD      |
      | 000000000000000004  | LFU        | 1000  | 201801    | AT03    | ADE      |
    And I wait "/plan/cns_fin_plan_val" Async Queue complete

    Given I import "/edm/country_v1" by keyFields "sourceSystem,localCountry"
      | sourceSystem | localCountry | countryCode | countryName |
      | CONS_LATAM   | AT01         | AT011       | AT01-NAME   |
      | CONS_LATAM   | AT02         | AT022       | AT02-NAME   |
      | CONS_LATAM   | AT03         | AT033       | AT03-NAME   |
      | CONS_LATAM   | AT04         | AT044       | AT04-NAME   |
      | CONS_LATAM   | AT05         | AT055       | AT05-NAME   |
      | CONS_LATAM   | AT06         | AT066       | AT06-NAME   |

    And I wait "/edm/country_v1" Async Queue complete

    Given I import "/edm/currency_v1" by keyFields "sourceSystem,localCurrency"
      | sourceSystem | localCurrency | currencyCode | currencyName |
      | CONS_LATAM   | ADA           | ADAA         | CADP-NAME    |
      | CONS_LATAM   | AEB           | AEBB         | UAE -NAME    |
      | CONS_LATAM   | AEC           | AECC         | CADP-NAME    |
      | CONS_LATAM   | ADD           | ADDD         | CADP-NAME    |
      | CONS_LATAM   | ADE           | ADEE         | CADP-NAME    |
      | CONS_LATAM   | ADF           | ADFF         | CADP-NAME    |
      | CONS_LATAM   | ADG           | ADGG         | CADP-NAME    |
      | CONS_LATAM   | ADH           | ADHH         | CADP-NAME    |

    And I wait "/edm/currency_v1" Async Queue complete

    Given I import "/plan/cns_plan_parameter" by keyFields "attribute,dataObject,parameter,parameterValue,sourceSystem"
      | attribute  | dataObject  | parameter | parameterValue | sourceSystem |
      | CONS_LATAM | SEND_TO_OMP | LA        | LA             | CONS_LATAM   |
      | CONS_LATAM | SEND_TO_OMP | LA        | LC             | CONS_LATAM   |

    And I wait "/plan/cns_plan_parameter" Async Queue complete

    Given I import "/edm/jnj_calendar_v1" by keyFields "calWeek,fiscalPeriod,noOfWeek"
      | calWeek | fiscalPeriod | noOfWeek | weekToDate | weekFromDate |
      | 001     | 201901       | 1        | 2019-01-08 | 2019-01-01   |
      | 002     | 201801       | 2        | 2018-01-08 | 2018-01-01   |
      | 003     | 201801       | 2        | 2018-01-15 | 2018-01-08   |

    And I wait "/edm/jnj_calendar_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmLfu.xml" and execute file "jar/pangea-view.jar"

#    Then A file is found on sink application with name "GDMLFU.tsv"

#    Then I check file data for filename "GDMLFU.tsv" by keyFields "lfuId"
    Then I check region data "/omp/gdm_lfu" by keyFields "lfuId"
      | lfuId | countryId | currencyId | dueDate | fromDueDate | productId | value | volume |

    Then I check region data "/plan/edm_failed_data" by keyFields "errorCode,functionalArea,interfaceID,key1,key2,key3,key4,key5,sourceSystem"
      | errorCode | functionalArea | interfaceID | key1               | key2       | key3 | key4 | key5 | sourceSystem | errorValue                                       |
      | J1        | DP             | OMPGdmLfu   | 000000000000000004 | CONS_LATAM |      |      |      | CONS_LATAM   | localDpParentCode does not exist in edm Material |


  @Scenario2
  Scenario: only found record in cns_fin_plan_val skip this record

    Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | sourceSystem | localMaterialNumber | localDpParentCode | localBaseUom |
      | CONS_LATAM   | 000000000000000001  | LDPC01            | LBU01        |
      | CONS_LATAM   | 000000000000000003  | LDPC03            | LBU03        |
      | CONS_LATAM   | 000000000000000002  | LDPC02            | LBU02        |
      | CONS_LATAM   | 000000000000000004  |                   | LBU04        |

    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/plan/cns_fin_plan_qty" by keyFields "localMaterialNumber,identifier"
      | localMaterialNumber | identifier | country | currency | unitId | quantity | yearMonth | sourceSystem |
      | 000000000000000004  | LFU        | AT04    | AEC      | LBU03  | 300      | 201801    | CONS_LATAM   |
      | 000000000000000005  | LFU        | AT05    | ADG      | LBU05  | 100      | 201901    | CONS_LATAM   |
      | 000000000000000006  | LFU        | AT06    | ADH      | LBU06  | 500      | 201901    | CONS_LATAM   |

    And I wait "/plan/cns_fin_plan_qty" Async Queue complete

    Given I import "/edm/material_auom_v1" by keyFields "localMaterialNumber,localAuom,sourceSystem"
      | localMaterialNumber | localAuom | sourceSystem | localNumerator | localDenominator |
      | 000000000000000001  | LBU01     | CONS_LATAM   | 100            | 10               |
      | 000000000000000002  | LBU02     | CONS_LATAM   | 100            | 10               |
      | 000000000000000003  | LBU03     | CONS_LATAM   | 100            | 10               |

    And I wait "/edm/material_auom_v1" Async Queue complete

    Given I import "/plan/cns_fin_plan_val" by keyFields "localMaterialNumber,identifier"
      | localMaterialNumber | identifier | value | yearMonth | country | currency |
      | 000000000000000001  | LFU        | 1000  | 201901    | AT01    | ADA      |
      | 000000000000000002  | LFU        | 10    | 201901    | AT02    | ADD      |
      | 000000000000000003  | LFU        | 10    | 201801    | AT03    | ADE      |
      | 000000000000000003  | LFE        | 2     | 201801    | AT03    | ADE      |

    And I wait "/plan/cns_fin_plan_val" Async Queue complete

    Given I import "/edm/country_v1" by keyFields "sourceSystem,localCountry"
      | sourceSystem | localCountry | countryCode | countryName |
      | CONS_LATAM   | AT01         | AT011       | AT01-NAME   |
      | CONS_LATAM   | AT02         | AT022       | AT02-NAME   |
      | CONS_LATAM   | AT03         | AT033       | AT03-NAME   |
      | CONS_LATAM   | AT04         | AT044       | AT04-NAME   |
      | CONS_LATAM   | AT05         | AT055       | AT05-NAME   |
      | CONS_LATAM   | AT06         | AT066       | AT06-NAME   |

    And I wait "/edm/country_v1" Async Queue complete

    Given I import "/edm/currency_v1" by keyFields "sourceSystem,localCurrency"
      | sourceSystem | localCurrency | currencyCode | currencyName |
      | CONS_LATAM   | ADA           | ADAA         | CADP-NAME    |
      | CONS_LATAM   | AEB           | AEBB         | UAE -NAME    |
      | CONS_LATAM   | AEC           | AECC         | CADP-NAME    |
      | CONS_LATAM   | ADD           | ADDD         | CADP-NAME    |
      | CONS_LATAM   | ADE           | ADEE         | CADP-NAME    |
      | CONS_LATAM   | ADF           | ADFF         | CADP-NAME    |
      | CONS_LATAM   | ADG           | ADGG         | CADP-NAME    |
      | CONS_LATAM   | ADH           | ADHH         | CADP-NAME    |

    And I wait "/edm/currency_v1" Async Queue complete

    Given I import "/plan/cns_plan_parameter" by keyFields "attribute,dataObject,parameter,parameterValue,sourceSystem"
      | attribute  | dataObject  | parameter | parameterValue | sourceSystem |
      | CONS_LATAM | SEND_TO_OMP | LA        | LA             | CONS_LATAM   |
      | CONS_LATAM | SEND_TO_OMP | LA        | LC             | CONS_LATAM   |

    And I wait "/plan/cns_plan_parameter" Async Queue complete

    Given I import "/edm/jnj_calendar_v1" by keyFields "calWeek,fiscalPeriod,noOfWeek"
      | calWeek | fiscalPeriod | noOfWeek | weekToDate | weekFromDate |
      | 001     | 201901       | 1        | 2019-01-08 | 2019-01-01   |
      | 002     | 201801       | 2        | 2018-01-08 | 2018-01-01   |
      | 003     | 201801       | 2        | 2018-01-15 | 2018-01-08   |

    And I wait "/edm/jnj_calendar_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmLfu.xml" and execute file "jar/pangea-view.jar"

#    Then A file is found on sink application with name "GDMLFU.tsv"

#    Then I check file data for filename "GDMLFU.tsv" by keyFields "lfuId"
    Then I check region data "/omp/gdm_lfu" by keyFields "lfuId"
      | lfuId | countryId | currencyId | dueDate | fromDueDate | productId | value | volume |

    Then I check region data "/plan/edm_failed_data" by keyFields "errorCode,functionalArea,interfaceID,key1,key2,key3,key4,key5,sourceSystem"

      | errorCode | functionalArea | interfaceID | key1               | key2       | key3 | key4 | key5 | sourceSystem | errorValue                                       |
      | J1        | DP             | OMPGdmLfu   | 000000000000000004 | CONS_LATAM |      |      |      | CONS_LATAM   | localDpParentCode does not exist in edm Material |


  @Scenario3
  Scenario:  both  found record in cns_fin_plan_qty and  cns_fin_plan_val  ,same localMaterialNumber output once

    Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | sourceSystem | localMaterialNumber | localDpParentCode | localBaseUom |
      | CONS_LATAM   | 000000000000000001  | LDPC01            | LBU01        |
      | CONS_LATAM   | 000000000000000002  | LDPC02            | LBU02        |
      | CONS_LATAM   | 000000000000000003  | LDPC03            | LBU03        |
      | CONS_LATAM   | 000000000000000004  |                   | LBU04        |
      | CONS_LATAM   | 000000000000000005  | LDPC05            | LBU05        |
      | CONS_LATAM   | 000000000000000006  | LDPC06            | LBU06        |
    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/plan/cns_fin_plan_qty" by keyFields "localMaterialNumber,identifier,yearMonth"
      | localMaterialNumber | identifier | country | currency | unitId | quantity | yearMonth | sourceSystem |
      | 000000000000000003  | LFU        | AT03    | AEB      | LBU03  | 300      | 201801    | CONS_LATAM   |
      | 000000000000000004  | LFU        | AT04    | AEC      | LBU03  | 300      | 201801    | CONS_LATAM   |
      | 000000000000000005  | LFU        | AT05    | ADG      | LBU05  | 100      | 201901    | CONS_LATAM   |
      | 000000000000000005  | LFU        | AT05    | ADG      | LBU05  | 100      | 201902    | CONS_LATAM   |
      | 000000000000000005  | LFU        | AT05    | ADG      | LBU05  | 100      | 201903    | CONS_LATAM   |

    And I wait "/plan/cns_fin_plan_qty" Async Queue complete

    Given I import "/edm/material_auom_v1" by keyFields "localMaterialNumber,localAuom,sourceSystem"
      | localMaterialNumber | localAuom | sourceSystem | localNumerator | localDenominator |
      | 000000000000000003  | LBU03     | CONS_LATAM   | 100            | 10               |
      | 000000000000000004  | LBU04     | CONS_LATAM   | 10             | 100              |
      | 000000000000000005  | LBU05     | CONS_LATAM   | 10             | 10               |

    And I wait "/edm/material_auom_v1" Async Queue complete

    Given I import "/plan/cns_fin_plan_val" by keyFields "localMaterialNumber,identifier,yearMonth"
      | localMaterialNumber | identifier | value | yearMonth | country | currency |
      | 000000000000000003  | LFU        | 100   | 201801    | AT03    | ADE      |
      | 000000000000000005  | LFU        | 100   | 201901    | AT05    | ADG      |
      | 000000000000000005  | LFU        | 100   | 201902    | AT05    | ADG      |
    And I wait "/plan/cns_fin_plan_val" Async Queue complete

    Given I import "/edm/country_v1" by keyFields "sourceSystem,localCountry"
      | sourceSystem | localCountry | countryCode | countryName |
      | CONS_LATAM   | AT01         | AT011       | AT01-NAME   |
      | CONS_LATAM   | AT02         | AT022       | AT02-NAME   |
      | CONS_LATAM   | AT03         | AT033       | AT03-NAME   |
      | CONS_LATAM   | AT04         | AT044       | AT04-NAME   |
      | CONS_LATAM   | AT05         | AT055       | AT05-NAME   |
      | CONS_LATAM   | AT06         | AT066       | AT06-NAME   |

    And I wait "/edm/country_v1" Async Queue complete

    Given I import "/edm/currency_v1" by keyFields "sourceSystem,localCurrency"
      | sourceSystem | localCurrency | currencyCode | currencyName |
      | CONS_LATAM   | ADA           | ADAA         | CADP-NAME    |
      | CONS_LATAM   | AEB           | AEBB         | UAE -NAME    |
      | CONS_LATAM   | AEC           | AECC         | CADP-NAME    |
      | CONS_LATAM   | ADD           | ADDD         | CADP-NAME    |
      | CONS_LATAM   | ADE           | ADEE         | CADP-NAME    |
      | CONS_LATAM   | ADF           | ADFF         | CADP-NAME    |
      | CONS_LATAM   | ADG           | ADGG         | CADP-NAME    |
      | CONS_LATAM   | ADH           | ADHH         | CADP-NAME    |

    And I wait "/edm/currency_v1" Async Queue complete

    Given I import "/plan/cns_plan_parameter" by keyFields "attribute,dataObject,parameter,parameterValue,sourceSystem"
      | attribute  | dataObject  | parameter | parameterValue | sourceSystem |
      | CONS_LATAM | SEND_TO_OMP | LA        | LA             | CONS_LATAM   |
      | CONS_LATAM | SEND_TO_OMP | LA        | LC             | CONS_LATAM   |

    And I wait "/plan/cns_plan_parameter" Async Queue complete

    Given I import "/edm/jnj_calendar_v1" by keyFields "calWeek,fiscalPeriod,noOfWeek"
      | calWeek | fiscalPeriod | noOfWeek | weekToDate | weekFromDate |
      | 001     | 201901       | 1        | 2019-01-08 | 2019-01-01   |
      | 001     | 201902       | 1        | 2019-02-08 | 2019-02-01   |
      | 001     | 201903       | 1        | 2019-03-08 | 2019-03-01   |
      | 002     | 201801       | 2        | 2018-01-08 | 2018-01-01   |
      | 003     | 201801       | 2        | 2018-01-15 | 2018-01-08   |

    And I wait "/edm/jnj_calendar_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmLfu.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMLFU.tsv"

    Then I check file data for filename "GDMLFU.tsv" by keyFields "lfuId"
#    Then I check region data "/omp/gdm_lfu" by keyFields "lfuId"
      | lfuId                         | countryId | currencyId | dueDate             | fromDueDate         | productId | value   | volume |
      | LA_LDPC03-2018/01/01 00:00:00 | AT033     | ADEE       | 2018/01/08 00:00:00 | 2018/01/01 00:00:00 | LA_LDPC03 | 50.000  | 1500   |
      | LC_LDPC03-2018/01/01 00:00:00 | AT033     | ADEE       | 2018/01/08 00:00:00 | 2018/01/01 00:00:00 | LC_LDPC03 | 50.000  | 1500   |
      | LA_LDPC03-2018/01/08 00:00:00 | AT033     | ADEE       | 2018/01/15 00:00:00 | 2018/01/08 00:00:00 | LA_LDPC03 | 50.000  | 1500   |
      | LC_LDPC03-2018/01/08 00:00:00 | AT033     | ADEE       | 2018/01/15 00:00:00 | 2018/01/08 00:00:00 | LC_LDPC03 | 50.000  | 1500   |
      | LA_LDPC05-2019/01/01 00:00:00 | AT055     | ADGG       | 2019/01/08 00:00:00 | 2019/01/01 00:00:00 | LA_LDPC05 | 100.000 | 100    |
      | LC_LDPC05-2019/01/01 00:00:00 | AT055     | ADGG       | 2019/01/08 00:00:00 | 2019/01/01 00:00:00 | LC_LDPC05 | 100.000 | 100    |
      | LA_LDPC05-2019/02/01 00:00:00 | AT055     | ADGG       | 2019/02/08 00:00:00 | 2019/02/01 00:00:00 | LA_LDPC05 | 100.000 | 100    |
      | LC_LDPC05-2019/02/01 00:00:00 | AT055     | ADGG       | 2019/02/08 00:00:00 | 2019/02/01 00:00:00 | LC_LDPC05 | 100.000 | 100    |

    Then I check region data "/plan/edm_failed_data" by keyFields "errorCode,functionalArea,interfaceID,key1,key2,key3,key4,key5,sourceSystem"
      | errorCode | functionalArea | interfaceID | key1               | key2       | key3 | key4 | key5 | sourceSystem | errorValue                                       |
      | J1        | DP             | OMPGdmLfu   | 000000000000000004 | CONS_LATAM |      |      |      | CONS_LATAM   | localDpParentCode does not exist in edm Material |

  @Scenario4
  Scenario:  Aggrgeate Value and Quantity

    Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | sourceSystem | localMaterialNumber | localDpParentCode | localBaseUom |
      | CONS_LATAM   | 000000000000000031  | LDPC03            | LBU03        |
      | CONS_LATAM   | 000000000000000032  | LDPC03            | LBU03        |
      | CONS_LATAM   | 000000000000000033  | LDPC03            | LBU03        |
      | CONS_LATAM   | 000000000000000034  | LDPC03            | LBU03        |
      | CONS_LATAM   | 000000000000000035  | LDPC03            | LBU03        |
      | CONS_LATAM   | 000000000000000036  | LDPC03            | LBU03        |
      | CONS_LATAM   | 000000000000000037  | LDPC03            | LBU03        |
      | CONS_LATAM   | 000000000000000038  | LDPC03            | LBU03        |
      | CONS_LATAM   | 000000000000000039  | LDPC03            | LBU03        |

    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/plan/cns_fin_plan_qty" by keyFields "localMaterialNumber,identifier,yearMonth"
      | localMaterialNumber | identifier | country | currency | unitId | quantity | yearMonth | sourceSystem |
      | 000000000000000031  | LFU        | AT03    | AEB      | LBU03  | 100      | 201801    | CONS_LATAM   |
      | 000000000000000032  | LFU        | AT03    | AEC      | LBU03  | 200      | 201801    | CONS_LATAM   |
      | 000000000000000033  | LFU        | AT03    | ADG      | LBU03  | 300      | 201801    | CONS_LATAM   |
      | 000000000000000034  | LFU        | AT03    | ADG      | LBU03  | 400      | 201801    | CONS_LATAM   |
      | 000000000000000036  | LFU        | AT03    | ADG      | LBU03  | 600      | 201802    | CONS_LATAM   |
      | 000000000000000037  | LFU        | AT03    | ADG      | LBU03  | 700      | 201802    | CONS_LATAM   |
      | 000000000000000038  | LFU        | AT03    | ADG      | LBU03  | 800      | 201802    | CONS_LATAM   |
      | 000000000000000039  | LFU        | AT03    | ADG      | LBU03  | 900      | 201802    | CONS_LATAM   |

    And I wait "/plan/cns_fin_plan_qty" Async Queue complete

    Given I import "/edm/material_auom_v1" by keyFields "localMaterialNumber,localAuom,sourceSystem"
      | localMaterialNumber | localAuom | sourceSystem | localNumerator | localDenominator |
      | 000000000000000031  | LBU03     | CONS_LATAM   | 100            | 100              |
      | 000000000000000032  | LBU03     | CONS_LATAM   | 200            | 100              |
      | 000000000000000033  | LBU03     | CONS_LATAM   | 300            | 100              |
      | 000000000000000034  | LBU03     | CONS_LATAM   | 400            | 100              |
      | 000000000000000035  | LBU03     | CONS_LATAM   | 500            | 100              |
      | 000000000000000036  | LBU03     | CONS_LATAM   | 600            | 100              |
      | 000000000000000037  | LBU03     | CONS_LATAM   | 700            | 100              |
      | 000000000000000038  | LBU03     | CONS_LATAM   | 800            | 100              |

    And I wait "/edm/material_auom_v1" Async Queue complete

    Given I import "/plan/cns_fin_plan_val" by keyFields "localMaterialNumber,identifier,yearMonth"
      | localMaterialNumber | identifier | value | yearMonth | country | currency |
      | 000000000000000032  | LFU        | 200   | 201801    | AT05    | ADE      |
      | 000000000000000033  | LFU        | 300   | 201801    | AT05    | ADE      |
      | 000000000000000034  | LFU        | 400   | 201801    | AT05    | ADE      |
      | 000000000000000035  | LFU        | 500   | 201801    | AT05    | ADE      |
      | 000000000000000036  | LFU        | 600   | 201802    | AT05    | ADE      |
      | 000000000000000037  | LFU        | 700   | 201802    | AT05    | ADE      |
      | 000000000000000038  | LFU        | 800   | 201802    | AT05    | ADE      |
      | 000000000000000039  | LFU        | 900   | 201802    | AT05    | ADE      |

    And I wait "/plan/cns_fin_plan_val" Async Queue complete

    Given I import "/edm/country_v1" by keyFields "sourceSystem,localCountry"
      | sourceSystem | localCountry | countryCode | countryName |
      | CONS_LATAM   | AT03         | AT033       | AT03-NAME   |


    And I wait "/edm/country_v1" Async Queue complete

    Given I import "/edm/currency_v1" by keyFields "sourceSystem,localCurrency"
      | sourceSystem | localCurrency | currencyCode | currencyName |
      | CONS_LATAM   | ADE           | ADEE         | CADP-NAME    |

    And I wait "/edm/currency_v1" Async Queue complete

    Given I import "/plan/cns_plan_parameter" by keyFields "attribute,dataObject,parameter,parameterValue,sourceSystem"
      | attribute  | dataObject  | parameter | parameterValue | sourceSystem |
      | CONS_LATAM | SEND_TO_OMP | LA        | LA             | CONS_LATAM   |

    And I wait "/plan/cns_plan_parameter" Async Queue complete

    Given I import "/edm/jnj_calendar_v1" by keyFields "calWeek,fiscalPeriod,noOfWeek"
      | calWeek | fiscalPeriod | noOfWeek | weekToDate | weekFromDate |
      | 001     | 201801       | 2        | 2018-01-08 | 2018-01-01   |
      | 002     | 201801       | 2        | 2018-01-15 | 2018-01-09   |
      | 003     | 201802       | 1        | 2018-02-08 | 2018-02-01   |

    And I wait "/edm/jnj_calendar_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmLfu.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMLFU.tsv"

    Then I check file data for filename "GDMLFU.tsv" by keyFields "lfuId"
#    Then I check region data "/omp/gdm_lfu" by keyFields "lfuId"
      | lfuId                         | countryId | currencyId | dueDate             | fromDueDate         | productId | value    | volume |
      | LA_LDPC03-2018/01/01 00:00:00 | AT033     | ADEE       | 2018/01/08 00:00:00 | 2018/01/01 00:00:00 | LA_LDPC03 | 450.000  | 1450   |
      | LA_LDPC03-2018/01/09 00:00:00 | AT033     | ADEE       | 2018/01/15 00:00:00 | 2018/01/09 00:00:00 | LA_LDPC03 | 450.000  | 1450   |
      | LA_LDPC03-2018/02/01 00:00:00 | AT033     | ADEE       | 2018/02/08 00:00:00 | 2018/02/01 00:00:00 | LA_LDPC03 | 2100.000 | 14900  |


  @Scenario5
  Scenario:  full load curation

    Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | sourceSystem | localMaterialNumber | localDpParentCode | localBaseUom |
      | CONS_LATAM   | 000000000000000003  | LDPC03            | LBU03        |
      | CONS_LATAM   | 000000000000000005  | LDPC05            | LBU05        |
    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/plan/cns_fin_plan_qty" by keyFields "localMaterialNumber,identifier,yearMonth"
      | localMaterialNumber | identifier | country | currency | unitId | quantity | yearMonth | sourceSystem |
      | 000000000000000003  | LFU        | AT03    | AEB      | LBU03  | 300      | 201801    | CONS_LATAM   |
      | 000000000000000004  | LFU        | AT04    | AEC      | LBU03  | 300      | 201801    | CONS_LATAM   |
      | 000000000000000005  | LFU        | AT05    | ADG      | LBU05  | 100      | 201901    | CONS_LATAM   |
      | 000000000000000005  | LFU        | AT05    | ADG      | LBU05  | 100      | 201902    | CONS_LATAM   |
      | 000000000000000005  | LFU        | AT05    | ADG      | LBU05  | 100      | 201903    | CONS_LATAM   |

    And I wait "/plan/cns_fin_plan_qty" Async Queue complete

    Given I import "/edm/material_auom_v1" by keyFields "localMaterialNumber,localAuom,sourceSystem"
      | localMaterialNumber | localAuom | sourceSystem | localNumerator | localDenominator |
      | 000000000000000003  | LBU03     | CONS_LATAM   | 100            | 10               |
      | 000000000000000005  | LBU05     | CONS_LATAM   | 10             | 10               |

    And I wait "/edm/material_auom_v1" Async Queue complete

    Given I import "/plan/cns_fin_plan_val" by keyFields "localMaterialNumber,identifier,yearMonth"
      | localMaterialNumber | identifier | value | yearMonth | country | currency |
      | 000000000000000003  | LFU        | 100   | 201801    | AT03    | ADE      |
      | 000000000000000005  | LFU        | 100   | 201901    | AT05    | ADG      |
      | 000000000000000005  | LFU        | 100   | 201902    | AT05    | ADG      |
    And I wait "/plan/cns_fin_plan_val" Async Queue complete

    Given I import "/edm/country_v1" by keyFields "sourceSystem,localCountry"
      | sourceSystem | localCountry | countryCode | countryName |
      | CONS_LATAM   | AT01         | AT011       | AT01-NAME   |
      | CONS_LATAM   | AT02         | AT022       | AT02-NAME   |
      | CONS_LATAM   | AT03         | AT033       | AT03-NAME   |
      | CONS_LATAM   | AT04         | AT044       | AT04-NAME   |
      | CONS_LATAM   | AT05         | AT055       | AT05-NAME   |
      | CONS_LATAM   | AT06         | AT066       | AT06-NAME   |

    And I wait "/edm/country_v1" Async Queue complete

    Given I import "/edm/currency_v1" by keyFields "sourceSystem,localCurrency"
      | sourceSystem | localCurrency | currencyCode | currencyName |
      | CONS_LATAM   | ADA           | ADAA         | CADP-NAME    |
      | CONS_LATAM   | AEB           | AEBB         | UAE -NAME    |
      | CONS_LATAM   | AEC           | AECC         | CADP-NAME    |
      | CONS_LATAM   | ADD           | ADDD         | CADP-NAME    |
      | CONS_LATAM   | ADE           | ADEE         | CADP-NAME    |
      | CONS_LATAM   | ADF           | ADFF         | CADP-NAME    |
      | CONS_LATAM   | ADG           | ADGG         | CADP-NAME    |
      | CONS_LATAM   | ADH           | ADHH         | CADP-NAME    |

    And I wait "/edm/currency_v1" Async Queue complete

    Given I import "/plan/cns_plan_parameter" by keyFields "attribute,dataObject,parameter,parameterValue,sourceSystem"
      | attribute  | dataObject  | parameter | parameterValue | sourceSystem |
      | CONS_LATAM | SEND_TO_OMP | LA        | LA             | CONS_LATAM   |
      | CONS_LATAM | SEND_TO_OMP | LA        | LC             | CONS_LATAM   |

    And I wait "/plan/cns_plan_parameter" Async Queue complete

    Given I import "/edm/jnj_calendar_v1" by keyFields "calWeek,fiscalPeriod,noOfWeek"
      | calWeek | fiscalPeriod | noOfWeek | weekToDate | weekFromDate |
      | 001     | 201901       | 1        | 2019-01-08 | 2019-01-01   |
      | 001     | 201902       | 1        | 2019-02-08 | 2019-02-01   |
      | 001     | 201903       | 1        | 2019-03-08 | 2019-03-01   |
      | 002     | 201801       | 2        | 2018-01-08 | 2018-01-01   |
      | 003     | 201801       | 2        | 2018-01-15 | 2018-01-08   |

    And I wait "/edm/jnj_calendar_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmLfu.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMLFU.tsv"

    Then I check file data for filename "GDMLFU.tsv" by keyFields "lfuId"
#    Then I check region data "/omp/gdm_lfu" by keyFields "lfuId"
      | lfuId                         | countryId | currencyId | dueDate             | fromDueDate         | productId | value   | volume |
      | LA_LDPC03-2018/01/01 00:00:00 | AT033     | ADEE       | 2018/01/08 00:00:00 | 2018/01/01 00:00:00 | LA_LDPC03 | 50.000  | 1500   |
      | LC_LDPC03-2018/01/01 00:00:00 | AT033     | ADEE       | 2018/01/08 00:00:00 | 2018/01/01 00:00:00 | LC_LDPC03 | 50.000  | 1500   |
      | LA_LDPC03-2018/01/08 00:00:00 | AT033     | ADEE       | 2018/01/15 00:00:00 | 2018/01/08 00:00:00 | LA_LDPC03 | 50.000  | 1500   |
      | LC_LDPC03-2018/01/08 00:00:00 | AT033     | ADEE       | 2018/01/15 00:00:00 | 2018/01/08 00:00:00 | LC_LDPC03 | 50.000  | 1500   |
      | LA_LDPC05-2019/01/01 00:00:00 | AT055     | ADGG       | 2019/01/08 00:00:00 | 2019/01/01 00:00:00 | LA_LDPC05 | 100.000 | 100    |
      | LC_LDPC05-2019/01/01 00:00:00 | AT055     | ADGG       | 2019/01/08 00:00:00 | 2019/01/01 00:00:00 | LC_LDPC05 | 100.000 | 100    |
      | LA_LDPC05-2019/02/01 00:00:00 | AT055     | ADGG       | 2019/02/08 00:00:00 | 2019/02/01 00:00:00 | LA_LDPC05 | 100.000 | 100    |
      | LC_LDPC05-2019/02/01 00:00:00 | AT055     | ADGG       | 2019/02/08 00:00:00 | 2019/02/01 00:00:00 | LC_LDPC05 | 100.000 | 100    |

    Then I check region data "/plan/edm_failed_data" by keyFields "errorCode,functionalArea,interfaceID,key1,key2,key3,key4,key5,sourceSystem"
      | errorCode | functionalArea | interfaceID | key1               | key2       | key3 | key4 | key5 | sourceSystem | errorValue                                       |

    Then I delete the test data

    And I will remove all data with region "/omp/gdm_lfu"

    And I will remove all data with region "/plan/edm_failed_data"

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/omp/gdm_lfu"

    And I will remove all data with region "/edm/material_global_v1"

    And I will remove all data with region "/omp/plan/cns_fin_plan_qty"

    And I will remove all data with region "/omp/cns_fin_plan_val"

    And I will remove all data with region "/edm/material_auom_v1"

    And I will remove all data with region "/edm/country_v1"

    And I will remove all data with region "/edm/currency_v1"

    And I will remove all data with region "/plan/cns_plan_parameter"

    And I will remove all data with region "/edm/jnj_calendar_v1"

    And I will remove the test file on sink application "GDMLFU.tsv"
