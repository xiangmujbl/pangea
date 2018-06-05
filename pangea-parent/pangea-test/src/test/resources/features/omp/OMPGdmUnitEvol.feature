@pangea_test @AEAZ-3686
Feature: OMPGdmUnitEvol-Curation AEAZ-3686

  Scenario: Full Load curation
  #  1. Get uniqueId from cons_time_dep_xchange-fromCurrency pluse next serial number in 000 format (Rule:C1)
  #  2. if values is not exist, filed data record (Rule:C1)
  #  3. Get StartEff in YYYY/DD/MM HH:NN:SS format (Rule:T1)
  #  4. Get EndEff from 'cons_time_dep_xchange-effectiveEndDate' + 1 in YYYY/DD/MM HH:NN:SS format (Rule:T2)
  #  5. Insert missing records for currency to ensure current year + 4 years data is available for the currency (Rule:T3)

    And I will remove the test file on sink application "GDMUnitEvol.tsv"

    Given I import "/plan/cons_time_dep_xchange" by keyFields "sourceSystem,fromCurrency,effectiveStartDate,effectiveEndDate"
      | uniqueId | sourceSystem | fromCurrency | effectiveStartDate | effectiveEndDate | exchangeRate       | preference |
      | -        | CONS_LATAM   | ARS          | 1/1/2018           | 31/12/2018       | 17.27115716753020  | 100        |
      | -        | CONS_LATAM   | ARS          | 1/1/2019           | 31/12/2019       | 17.27115716753020  | 100        |
      | -        | CONS_LATAM   | ARS          | 1/1/2020           | 31/12/2020       | 17.27115716753020  | 100        |
      | -        | CONS_LATAM   | ARS          | 1/1/2021           | 31/12/2021       | 17.27115716753020  | 100        |
      | -        | CONS_LATAM   | ARS          | 1/1/2022           | 31/12/2022       | 17.27115716753020  | 100        |
      | -        | CONS_LATAM   | CLP          | 1/1/2018           | 31/12/2018       | 624.53160129902600 | 100        |
      | -        | CONS_LATAM   | CLP          | 1/1/2019           | 30/12/2019       | 624.53160129902600 | 100        |
      | -        | CONS_LATAM   | CLP          | 1/1/2020           | 31/12/2020       | 624.53160129902600 | 100        |
      | -        | CONS_LATAM   |              | 1/1/2018           | 31/12/2018       | 28.90172958        | 100        |
    And I wait "/plan/cons_time_dep_xchange" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmUnitEvol.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMUnitEvol.tsv"

    Then I check file data for filename "GDMUnitEvol.tsv" by keyFields "uniqueId"
      | uniqueId | activeFCTERP | unitId | startEff            | endEff              | factor             | preference |
      | ARS001   | YES          | ARS    | 2021/01/01 00:00:00 | 2022/01/01 00:00:00 | 17.27115716753020  | 100        |
      | ARS002   | YES          | ARS    | 2020/01/01 00:00:00 | 2021/01/01 00:00:00 | 17.27115716753020  | 100        |
      | ARS003   | YES          | ARS    | 2022/01/01 00:00:00 | 2023/01/01 00:00:00 | 17.27115716753020  | 100        |
      | ARS004   | YES          | ARS    | 2018/01/01 00:00:00 | 2019/01/01 00:00:00 | 17.27115716753020  | 100        |
      | ARS005   | YES          | ARS    | 2019/01/01 00:00:00 | 2020/01/01 00:00:00 | 17.27115716753020  | 100        |
      | CLP001   | YES          | CLP    | 2018/01/01 00:00:00 | 2019/01/01 00:00:00 | 624.53160129902600 | 100        |
      | CLP002   | YES          | CLP    | 2019/01/01 00:00:00 | 2019/31/12 00:00:00 | 624.53160129902600 | 100        |
      | CLP003   | YES          | CLP    | 2020/01/01 00:00:00 | 2021/01/01 00:00:00 | 624.53160129902600 | 100        |
      | CLP004   | YES          | CLP    | 2021/01/01 00:00:00 | 2022/01/01 00:00:00 | 624.53160129902600 | 100        |
      | CLP005   | YES          | CLP    | 2022/01/01 00:00:00 | 2023/01/01 00:00:00 | 624.53160129902600 | 100        |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | errorCode | functionalArea | interfaceID | key1       | key2 | key3     | key4       | key5 | errorValue               | sourceSystem |
      | C1        | DP             | GDMUnitEvol | CONS_LATAM |      | 1/1/2018 | 31/12/2018 |      | All Key fields not Exist |              |

#    And I compare the number of records between "/plan/cons_time_dep_xchange" and "/omp/gdm_unit_evol,/plan/edm_failed_data"

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/omp/gdm_unit_evol"

    And I will remove all data with region "/plan/edm_failed_data"
