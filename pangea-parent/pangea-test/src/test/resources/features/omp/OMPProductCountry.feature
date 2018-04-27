@pangea_test @AEAZ-1615
Feature: OMPProductCountry AEAZ-1615

  Scenario: Full Load curation

    Given I import "/plan/cns_prod_cty_affl" by keyFields "sourceSystem,dpParentCode,country"
      | sourceSystem | dpParentCode           | country | prodClassification | prodStatus | ovrPrdClass | ovrPrdStat | dpSegmentation | dpPlannerId | rootSize | countryGrp |
      | CONS_LATAM   |                        | BR      | SAMPLE             |            |             | INACTIVE   |                | DP004       |          | SUNFRESH   |
      | CONS_LATAM   | 0000000000000517180240 |         | SAMPLE             |            |             | INACTIVE   |                | DP004       |          | SUNFRESH   |
      | CONS_LATAM   | 0000000000000517180241 | BR      | SAMPLE             |            | INACTIVE    | INACTIVE   |                | DP004       |          | SUNFRESH   |
      | CONS_LATAM   | 0000000000000517180242 | BR      | SAMPLE             |            |             | INACTIVE   |                | DP004       |          | SUNFRESH   |
      | CONS_LATAM   | 0000000000000517180243 | BR      |                    |            |             | INACTIVE   |                | DP004       |          | SUNFRESH   |
      | CONS_LATAM   | 0000000000000517180244 | BR      | SAMPLE             | SAMPLE     | INACTIVE    | INACTIVE   |                | DP004       |          | SUNFRESH   |
      | CONS_LATAM   | 0000000000000517180245 | BR      | SAMPLE             | SAMPLE     | INACTIVE    |            |                | DP004       |          | SUNFRESH   |
      | CONS_LATAM   | 0000000000000517180246 | BR      | SAMPLE             |            | INACTIVE    |            |                | DP004       |          | SUNFRESH   |

    And I wait "/plan/cns_prod_cty_affl" Async Queue complete

    When I submit task with xml file "xml/omp/OMPProductCountry.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "OMPProductCountry.tsv"

    Then I check file data for filename "OMPProductCountry.tsv" by keyFields "uniqueId"
      | uniqueId                    | activeFCTERP | countryGroup | countryId | dpPlannerId | dpSegmentation | productClassification | productId              | productStatus | rootSize | segmentation |
      | LA_0000000000000517180241BR | YES          | SUNFRESH     | BR        | DP004       |                | INACTIVE              | 0000000000000517180241 | INACTIVE      |          |              |
      | LA_0000000000000517180242BR | YES          | SUNFRESH     | BR        | DP004       |                | SAMPLE                | 0000000000000517180242 | INACTIVE      |          |              |
      | LA_0000000000000517180244BR | YES          | SUNFRESH     | BR        | DP004       |                | INACTIVE              | 0000000000000517180244 | INACTIVE      |          |              |
      | LA_0000000000000517180245BR | YES          | SUNFRESH     | BR        | DP004       |                | INACTIVE              | 0000000000000517180245 | SAMPLE        |          |              |

#    Then I check region data "/omp/product_country" by keyFields "uniqueId"
#      | uniqueId                    | activeFCTERP | countryGroup | countryId | dpPlannerId | dpSegmentation | productClassification | productId              | productStatus | rootSize | segmentation |
#      | LA_0000000000000517180241BR | YES          | SUNFRESH     | BR        | DP004       |                | INACTIVE              | 0000000000000517180241 | INACTIVE      |          |              |
#      | LA_0000000000000517180242BR | YES          | SUNFRESH     | BR        | DP004       |                | SAMPLE                | 0000000000000517180242 | INACTIVE      |          |              |
#      | LA_0000000000000517180244BR | YES          | SUNFRESH     | BR        | DP004       |                | INACTIVE              | 0000000000000517180244 | INACTIVE      |          |              |
#      | LA_0000000000000517180245BR | YES          | SUNFRESH     | BR        | DP004       |                | INACTIVE              | 0000000000000517180245 | SAMPLE        |          |              |


    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID       | errorCode | sourceSystem | businessArea | key1       | key2                   | key3 | key4 | key5 | errorValue               |
      | DP             | GDMProductCountry | C1        |              |              | CONS_LATAM | 0000000000000517180240 |      |      |      | All Key fields not Exist |
      | DP             | GDMProductCountry | C1        |              |              | CONS_LATAM |                        | BR   |      |      | All Key fields not Exist |
      | DP             | GDMProductCountry | T1        |              |              | CONS_LATAM | 0000000000000517180243 | BR   |      |      | All Key fields not Exist |
      | DP             | GDMProductCountry | T2        |              |              | CONS_LATAM | 0000000000000517180246 | BR   |      |      | All Key fields not Exist |

    And I compare the number of records between "/plan/cns_prod_cty_affl" and "/omp/product_country,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/omp/product_country"

    And I will remove all data with region "/plan/edm_failed_data"

    And I will remove the test file on sink application "OMPProductCountry.tsv"

