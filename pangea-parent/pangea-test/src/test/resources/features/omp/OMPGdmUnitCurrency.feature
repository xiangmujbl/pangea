@pangea_test @AEAZ-1980
Feature: OMPGdmUnitCurrency AEAZ-1980

  Scenario: Full Load curation
    #  1. get atrributes from currency_v1 (rule E1)
    #  2. If no records found, Skip insertion (rule E1)

    Given I import "/edm/currency_v1" by keyFields "localCountry,sourceSystem"
      | localCountry | sourceSystem | isoNumeric | currencyName | currencyCode |
      | USD          | MDDePuy      | -          | US Dollar    | USD          |
      |              |              |            |              |              |

    And I wait "/edm/currency_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmUnitCurrency.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/omp/gdm_unit" by keyFields "unitId"
      | unitId | active | activeFCTERP | activeOPRERP | activeSOPERP | factor | isoCode | longDescription | measure | precision | shortDescription |
      | USD    | YES    | YES          | YES          | YES          |        | -       | US Dollar       |         |           | US Dollar        |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/edm/currency_v1" and "/omp/gdm_unit,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/omp/gdm_unit"

    And I will remove all data with region "/plan/edm_failed_data"

