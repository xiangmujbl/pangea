@pangea_test @AEAZ-2712
Feature: OMPGdmUnitEvol-Curation AEAZ-2712

  Scenario: Full Load curation
  #  1. make sure of data construction (Rule:C1)
  #  2. if values is not exist, filed data record (Rule:C1)

    Given I import "/plan/cons_time_dep_xchange" by keyFields "uniqueId"
      | uniqueId | sourceSystem | fromCurrency | effectiveStartDate | effectiveEndDate | exchangeRate | preference |
      | 1        | CONS_LATAM   | VEF          | 2019/1/1           | 2020/1/1         | 700          | 100        |
      | 2        | CONS_LATAM   | BRL          | 2017/12/31         | 2018/12/31       | 300          | 100        |
      | 3        | CONS_LATAM   | BRL          | 2016/1/1           | 2016/12/31       | 290          | 100        |
      | 4        | CONS_LATAM   | VEF          | 2018/1/1           | 2019/1/1         | 700          | 100        |
      | 5        | CONS_LATAM   | VEF          | 2020/1/1           | 2021/1/1         | 700          | 100        |
      | 6        | CONS_LATAM   | VEF          | 2021/1/1           | 2022/1/1         | 700          | 100        |
      | 7        | CONS_LATAM   | UYU          | 2018/1/1           | 2019/1/1         | 28.90172958  | 100        |
      | 8        | CONS_LATAM   | UYU          | 2020/1/1           | 2021/1/1         | 28.90172958  | 100        |
      | 9        | CONS_LATAM   |              | 2019/1/1           | 2020/1/1         | 28.90172958  | 100        |
    And I wait "/plan/cons_time_dep_xchange" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmUnitEvol.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMUnitEvol.tsv"

#    Then I check region data "/omp/gdm_unit_evol" by keyFields "uniqueId"
#      | uniqueId         | activeFCTERP | unitId | startEff   | endEff     | factor      | preference |
#      | CONS_LATAMVEF001 | YES          | VEF    | 2019/1/1   | 2020/1/1   | 700         | 100        |
#      | CONS_LATAMBRL001 | YES          | BRL    | 2017/12/31 | 2018/12/31 | 300         | 100        |
#      | CONS_LATAMBRL002 | YES          | BRL    | 2016/1/1   | 2016/12/31 | 290         | 100        |
#      | CONS_LATAMVEF002 | YES          | VEF    | 2018/1/1   | 2019/1/1   | 700         | 100        |
#      | CONS_LATAMVEF003 | YES          | VEF    | 2020/1/1   | 2021/1/1   | 700         | 100        |
#      | CONS_LATAMVEF004 | YES          | VEF    | 2021/1/1   | 2022/1/1   | 700         | 100        |
#      | CONS_LATAMVYU001 | YES          | UYU    | 2018/1/1   | 2019/1/1   | 28.90172958 | 100        |
#      | CONS_LATAMVYU002 | YES          | UYU    | 2020/1/1   | 2021/1/1   | 28.90172958 | 100        |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | errorCode | functionalArea | interfaceID | key1 | key2 | key3 | key4 | key5 | errorValue               | sourceSystem |
      | C1        | DP             | GDMUnitEvol | 9    |      |      |      |      | All Key fields not Exist |              |

    And I compare the number of records between "/plan/cons_time_dep_xchange" and "/omp/gdm_unit_evol,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/omp/gdm_unit_evol"
    And I will remove all data with region "/plan/edm_failed_data"
    And I will remove the test file on sink application "GDMUnitEvol.tsv"


