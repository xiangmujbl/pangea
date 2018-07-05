@pangea_test @AEAZ-6097
Feature: OMPGdmCountry AEAZ-6097

  Background:delete all test data

    Then I delete the test data

    And I will remove all data with region "/edm/country_v1"

    And I will remove all data with region "/plan/cns_plan_parameter"

    And I will remove all data with region "/edm/edm_country_input"

    And I will remove all data with region "/edm/currency_v1"

    And I will remove all data with region "/omp/gdm_country"

    And I will remove all data with region "/plan/edm_failed_data"

  Scenario: Full Load curation

    And I will remove the test file on sink application "GDMCountry.tsv"

    # and test incidence relation and Output to the error table
    Given I import "/edm/country_v1" by keyFields "localCountry,sourceSystem"
      | countryName | countryCode | localCountry | sourceSystem |
      | Zimbabwe1   | ZW          | ZW           | CONS_LATAM   |
      | Zimbabwe2   | ZR          | ZR           | CONS_LATAM   |
      | Zimbabwe2   |             | ZR           | MDDePuy      |
      |             | ZR          | ZB           | MDDePuy      |

      |             | ZR          | ZB           | MDDePuy2     |

      |             | ZR          | WB           | MDDePuy2     |
      | Zimbabwe2   | ZR          | DR           | CONS_LATAM   |
      | Zimbabwe2   | ZR          | CR           | CONS_LATAM   |
      | Zimbabwe2   | ZR          | BR           | CONS_LATAM   |

    And I wait "/edm/country_v1" Async Queue complete

    Given I import "/plan/cns_plan_parameter" by keyFields "sourceSystem,dataObject,attribute,parameter"
      | sourceSystem | dataObject               | attribute  | parameter | inclExcl | parameterValue |
      | CONS_LATAM   | cns_material_plan_status | DPRelevant | Plant     | I        | BR12           |
    And I wait "/plan/cns_plan_parameter" Async Queue complete

    Given I import "/edm/edm_country_input" by keyFields "sourceSystem,localCountry,localCurrency"
      | sourceSystem | localCountry             | localCurrency |
      | CONS_LATAM   | ZW                       | DDDDD         |

      | CONS_LATAM   | ZR                       | AAAAA         |

      | CONS_LATAM   | cns_material_plan_status | BBBBB         |

      | CONS_LATAM   | CR                       | CCCCC         |

      | MDDePuy2     | WB                       | EEEEE         |

    And I wait "/edm/edm_country_input" Async Queue complete

    Given I import "/edm/currency_v1" by keyFields "sourceSystem,localCurrency"
      | sourceSystem | localCurrency | currencyCode |
      | CONS_LATAM   | DDDDD         | FFFF         |
      | CONS_LATAM   | AAAAA         | GGGG         |
      | CONS_LATAM   | BBBBB         | HHHH         |

    And I wait "/edm/currency_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmCountry.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMCountry.tsv"

    Then I check file data for filename "GDMCountry.tsv" by keyFields "countryId"
#    Then I check region data "/omp/gdm_country" by keyFields "countryId"
      | countryId | activeFCTERP | activeOPRERP | activeSOPERP | countryDescription | mrc | currencyId |
      | ZW        | YES          | YES          | NO           | Zimbabwe1          |     | FFFF         |
      | ZR        | YES          | YES          | NO           | Zimbabwe2          |     | GGGG         |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID   | errorCode | sourceSystem | businessArea | key1       | key2 | key3 | key4 | key5 | errorValue                                 |
      | DP             | OMPGdmCountry | J1        |              |              | CONS_LATAM | DR   |      |      |      | localcountry do not exist in edm country   |
      | DP             | OMPGdmCountry | J1        |              |              | CONS_LATAM | CR   |      |      |      | localCurrecny do not exist in edm currency |
      | DP             | OMPGdmCountry | J1        |              |              | CONS_LATAM | BR   |      |      |      | localcountry do not exist in edm country   |

      | DP             | OMPGdmCountry | J1        |              |              | MDDePuy2   | ZB   |      |      |      | localcountry do not exist in edm country   |
      | DP             | OMPGdmCountry | J1        |              |              | MDDePuy2   | WB   |      |      |      | localCurrecny do not exist in edm currency |
      | DP             | OMPGdmCountry | J1        |              |              | MDDePuy    | ZB   |      |      |      | localcountry do not exist in edm country   |
      | DP             | OMPGdmCountry | J1        |              |              | MDDePuy    | ZR   |      |      |      | localcountry do not exist in edm country   |


  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/omp/gdm_country"

    And I will remove all data with region "/plan/edm_failed_data"
