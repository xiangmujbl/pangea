@pangea_test @AEAZ-3215
Feature:  E.2.1.6 GDMLFU - Consumptionn

  As a Data user,
  I want EDG to create GDM file for GDMLFU and send to OMP
  so that data can be consumed by OMP for planning

  Scenario: Full Load curation

#     1.  only qty
#     2   only  val
#     3  one  qty  two  val   one same  one different
#     4  localDpParentCode is null

    Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | sourceSystem | localMaterialNumber | localDpParentCode | localBaseUom |
      | CONS_LATAM   | 000000000000000001  | LDPC01            | LBU01        |
      | CONS_LATAM   | 000000000000000002  | LDPC02            | LBU02        |
      | CONS_LATAM   | 000000000000000003  | LDPC03            | LBU03        |
      | CONS_LATAM   | 000000000000000004  |                   | LBU04        |
      | CONS_LATAM   | 000000000000000005  | LDPC05            | LBU05        |
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
      | 000000000000000001  | LBU01     | CONS_LATAM   | 1              | 1                |
      | 000000000000000002  | LBU02     | CONS_LATAM   | 100            | 10               |
      | 000000000000000003  | LBU03     | CONS_LATAM   | 100            | 10               |
      | 000000000000000004  | LBU04     | CONS_LATAM   | 10             | 100              |
      | 000000000000000005  | LBU05     | CONS_LATAM   | 10             | 10               |


    And I wait "/edm/material_auom_v1" Async Queue complete

    Given I import "/plan/cns_fin_plan_val" by keyFields "localMaterialNumber,identifier"
      | localMaterialNumber | identifier | value | yearMonth | country | currency |
      | 000000000000000002  | LFU        | VAL02 | 201901    | AT02    | ADD      |
      | 000000000000000003  | LFU        | VAL03 | 201801    | AT03    | ADE      |
      | 000000000000000003  | LFE        | VAL05 | 201801    | AT03    | ADF      |
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
#
#    Then I check file data for filename "GDMLFU.tsv" by keyFields "LFUId"
    Then I check region data "/omp/gdm_lfu" by keyFields "LFUId"
      | LFUId                         | CountryId | CurrencyId | DueDate             | FromDueDate         | ProductId | Value | Volume |
      | LA-LDPC01-2019/01/01 00:00:00 | AT011     | ADAA       | 2019/01/08 00:00:00 | 2019/01/01 00:00:00 | LA-LDPC01 |       | 100    |
      | LC-LDPC01-2019/01/01 00:00:00 | AT011     | ADAA       | 2019/01/08 00:00:00 | 2019/01/01 00:00:00 | LC-LDPC01 |       | 100    |
      | LA-LDPC02-2019/01/01 00:00:00 | AT022     | ADDD       | 2019/01/08 00:00:00 | 2019/01/01 00:00:00 | LA-LDPC02 | VAL02 |        |
      | LC-LDPC02-2019/01/01 00:00:00 | AT022     | ADDD       | 2019/01/08 00:00:00 | 2019/01/01 00:00:00 | LC-LDPC02 | VAL02 |        |
      | LA-LDPC03-2018/01/01 00:00:00 | AT033     | AEBB       | 2018/01/08 00:00:00 | 2018/01/01 00:00:00 | LA-LDPC03 | VAL03 | 3000   |
      | LC-LDPC03-2018/01/01 00:00:00 | AT033     | AEBB       | 2018/01/08 00:00:00 | 2018/01/01 00:00:00 | LC-LDPC03 | VAL03 | 3000   |
      | LA-LDPC03-2018/01/08 00:00:00 | AT033     | AEBB       | 2018/01/15 00:00:00 | 2018/01/08 00:00:00 | LA-LDPC03 | VAL03 | 3000   |
      | LC-LDPC03-2018/01/08 00:00:00 | AT033     | AEBB       | 2018/01/15 00:00:00 | 2018/01/08 00:00:00 | LC-LDPC03 | VAL03 | 3000   |
      | LA-LDPC05-2019/01/01 00:00:00 | AT055     | ADGG       | 2019/01/08 00:00:00 | 2019/01/01 00:00:00 | LA-LDPC05 |       | 100    |
      | LC-LDPC05-2019/01/01 00:00:00 | AT055     | ADGG       | 2019/01/08 00:00:00 | 2019/01/01 00:00:00 | LC-LDPC05 |       | 100    |
      | LA-LDPC06-2019/01/01 00:00:00 | AT066     | ADHH       | 2019/01/08 00:00:00 | 2019/01/01 00:00:00 | LA-LDPC06 |       |        |
      | LC-LDPC06-2019/01/01 00:00:00 | AT066     | ADHH       | 2019/01/08 00:00:00 | 2019/01/01 00:00:00 | LC-LDPC06 |       |        |

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

