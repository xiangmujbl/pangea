@pangea_test
Feature: OMPProductCountry

  Scenario: Full Load curation

    Given I import "/plan/cns_prod_country_aff" by keyFields "country,dpPlanner,localMaterialNumber"
      | sourceSystem | localMaterialNumber | country | productClassification | productStatus | dpSegmentation | dpPlanner | rootSize | countryGroup |
      | CONS_LATAM   | 78910105796160024   | BR      | REGULAR               | Active        |                | DP003     |          | NTG LIMPEGA  |
      | CONS_LATAM   | 78910100373520048   | BR      | REGULAR               | Active        |                | DP003     |          | NTG LIMPEGA  |
      | CONS_LATAM   | 35746611035250006   | BR      | REGULAR               | Active        |                | DP003     |          | NTG LIMPEGA  |

    And I wait "/plan/cns_prod_country_aff" Async Queue complete

    Given I import "/edm/country_v1" by keyFields "localCountry"
      | localCountry | countryCode |
      | BR           | BR          |
      | BR           | BR          |

    And I wait "/edm/country_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPProductCountry.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/omp/product_country" by keyFields "uniqueId"
      | uniqueId            | activeFcterp | countryGroup | countryId | dpPlannerId | dpSegmentation | productClassification | productId         | productStatus | rootSize | segmentation |
      | 78910105796160024BR | YES          | NTG LIMPEGA  | BR        | DP003       |                | REGULAR               | 78910105796160024 | Active        |          |              |
      | 78910100373520048BR | YES          | NTG LIMPEGA  | BR        | DP003       |                | REGULAR               | 78910100373520048 | Active        |          |              |
      | 35746611035250006BR | YES          | NTG LIMPEGA  | BR        | DP003       |                | REGULAR               | 35746611035250006 | Active        |          |              |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/plan/cns_prod_country_aff" and "/omp/product_country,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/omp/product_country"
    And I will remove all data with region "/plan/edm_failed_data"
