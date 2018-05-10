@pangea_test
Feature:  OMPGdmFpb-Curation

  Scenario: Full Load curation

    #1.JOIN( cns_fin_plan_qty-localMaterialNumber = material_global_v1-localMaterialNumber AND cns_fin_plan_qty-identifier = 'FPB' ) AND
    #material_global_v1-sourceSystem = cns_plan_parameter-attribute ( where cns_plan_parameter-sourceSystem = material_global_v1-sourceSystem and cns_plan_parameter-dataObject = 'SEND_TO_OMP' )
    # JOIN( cns_fin_plan_val-localMaterialNumber = material_global_v1-localMaterialNumber AND cns_fin_plan_val-identifier = 'FPB' ) AND
    #material_global_v1-sourceSystem = cns_plan_parameter-attribute ( where cns_plan_parameter-sourceSystem = material_global_v1-sourceSystem and cns_plan_parameter-dataObject = 'SEND_TO_OMP'  )
    #2.Rejct the record if material_global_v1-localDpParentCode is empty
    #3.get parameterValue where sourceSystem = material_global_v1-source_system AND cns_plan_parameter-dataObject = 'SEND_TO_OMP'
    #4.get FPBId by T4(Concatenate GDMFPB-ProductID, "-" .  GDMFPB-FromDueDate to create a unique ID.)
    #5.get country_v1-country from country_v1 where country_v1-localCountry =   cns_fin_plan_qty-country(T2)
    #6.get currency_v1-localCurrency from currency_v1 where currency_v1-localCurrency = cns_fin_plan_qty-currency(T3)
    #7.get noOfWeek, weekToDate and weekFromDate where cns_fin_plan_val/cns_fin_plan_qty-yearMonth = jnj_calendar_v1-fiscalPeriod(T5)
    #8.Distribute cns_fin_plan_val-value equally into noOfWeeks(T7)
    #9.Distribute cns_fin_plan_qty-Quantity equally into noOfWeeks(T8)

    Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | sourceSystem     | localMaterialNumber | localDpParentCode  | localBaseUom |
      | CONS_LATAM       | 000000000000000945  | 178962124094540036 | KI            |
      | CONS_LATAM       | 000000000000002886  | 178962124094540045 | KI            |
      | CONS_LATAM       | 000000000000002886  | 178962124094540045 | KI            |
      | CONS_LATAM       | 000000000000002886  | 178962124094540045 | KI            |
      | CONS_LATAM       | 000000000000002886  |                      | KI            |
    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/plan/cns_fin_plan_qty" by keyFields "localMaterialNumber,identifier,yearMonth"
      | localMaterialNumber | identifier | country |  unitId | quantity | yearMonth |
      | 000000000000000945  | FPB         | BR       |  ZUM    | -         | 201805    |
      | 000000000000002886  | FPB         | BR       |  ZUM    | -         | 201806    |
      | 000000000000002886  | FPB         | BR       |  ZUM    | -         | 201806    |
      | 000000000000002886  | FPB         | BR       |  ZUM    | -         | 201807    |
      | 000000000000002886  | FPB         | BR       |  ZUM    | -         | 201807    |
    And I wait "/plan/cns_fin_plan_qty" Async Queue complete

    Given I import "/plan/cns_fin_plan_val" by keyFields "localMaterialNumber,identifier,yearMonth"
      | localMaterialNumber | identifier | value        | yearMonth |currency|
      | 000000000000000945  | FPB         | 745400.866   | 201805    | BRL     |
      | 000000000000002886  | FPB         | 4658755.412	 | 201806    | BRL     |
      | 000000000000002886  | FPB         | 4658755.412	 | 201806    | BRL     |
      | 000000000000002886  | FPB         | 4658755.412	 | 201807    | BRL     |
      | 000000000000002886  | FPB         | 4658755.412	 | 201807    | BRL     |
    And I wait "/plan/cns_fin_plan_val" Async Queue complete

    Given I import "/edm/country_v1" by keyFields "sourceSystem,localCountry"
      | sourceSystem | localCountry | countryCode |
      | CONS_LATAM   | BR             | BR           |
      | CONS_LATAM   | BR             | BR           |
      | CONS_LATAM   | BR             | BR           |
      | CONS_LATAM   | BR             | BR           |
      | CONS_LATAM   | BR             | BR           |
    And I wait "/edm/country_v1" Async Queue complete

    Given I import "/edm/currency_v1" by keyFields "sourceSystem,localCurrency"
      | sourceSystem | localCurrency | currencyCode | currencyName   | isoNumeric |
      | CONS_LATAM   | BRL            | CADP          | Brazilian Real | -           |
      | CONS_LATAM   | BRL            | CAED          | Brazilian Real | -           |
      | CONS_LATAM   | BRL            | CAED          | Brazilian Real | -           |
      | CONS_LATAM   | BRL            | CAED          | Brazilian Real | -           |
      | CONS_LATAM   | BRL            | CAED          | Brazilian Real | -           |
    And I wait "/edm/currency_v1" Async Queue complete

    Given I import "/plan/cns_plan_parameter" by keyFields "attribute,dataObject,parameter,parameterValue,sourceSystem"
      | attribute        | dataObject  | parameter | parameterValue | sourceSystem     |
      | CONS_LATAM       | SEND_TO_OMP | Division  | 10               | CONS_LATAM       |
      | CONS_LATAM       | SEND_TO_OMP | MRPType   | ND               | CONS_LATAM       |
      | CONS_LATAM       | SEND_TO_OMP | MRPType   | ND               | CONS_LATAM       |
      | CONS_LATAM       | SEND_TO_OMP | MRPType   | ND               | CONS_LATAM       |
      | CONS_LATAM       | SEND_TO_OMP | MRPType   | ND               | CONS_LATAM       |
    And I wait "/plan/cns_plan_parameter" Async Queue complete

    Given I import "/edm/jnj_calender_v1" by keyFields "calWeek,fiscalPeriod,noOfweek"
      | calWeek | fiscalPeriod | noOfweek | weekFromDate | weekToDate |
      | 201914  | 201805        | 4         | 2019-04-01   | 2019-04-08 |
      | 201915  | 201806        | 4         | 2019-04-08   | 2019-04-15 |
      | 201916  | 201806        | 4         | 2019-04-15   | 2019-04-27 |
      | 201917  | 201807        | 4         | 2019-04-22   | 2019-04-22 |
      | 201918  | 201807        | 4         | 2019-04-29   | 2019-04-22 |


    And I wait "/edm/jnj_calender_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmFpb.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMFpb.tsv"

    Then I check file data for filename "GDMFpb.tsv" by keyFields "fpbId"

      | fpbId                                          | countryId   | currencyId | dueDate              | fromDueDate           | productId               | value | volume |
      | 10_178962124094540036-2019/04/01 00:00:00  |      BR     | BRL         | 2019/04/08 00:00:00 | 2019/04/01 00:00:00  | 10_178962124094540036 | 4     | 4      |
      | ND_178962124094540045-2019/04/08 00:00:00  |      BR     | BRL         | 2019/04/15 00:00:00 | 2019/04/08 00:00:00  | ND_178962124094540045 | 4     | 4      |
      | ND_178962124094540045-2019/04/15 00:00:00  |      BR     | BRL         | 2019/04/27 00:00:00 | 2019/04/15 00:00:00  | ND_178962124094540045 | 4     | 4      |
      | ND_178962124094540045-2019/04/22 00:00:00  |      BR     | BRL         | 2019/04/22 00:00:00 | 2019/04/22 00:00:00  | ND_178962124094540045 | 4     | 4      |
      |                                                 |      BR     | BRL         | 2019/04/22 00:00:00 | 2019/04/29 00:00:00  | ND_                     | 4     | 4      |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    #And I compare the number of records between "/plan/cns_fin_plan_qty" and "/omp/gdm_fpb,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/omp/gdm_fpb"

    And I will remove all data with region "/plan/edm_failed_data"

    And I will remove the test file on sink application "GDMFpb.tsv"
