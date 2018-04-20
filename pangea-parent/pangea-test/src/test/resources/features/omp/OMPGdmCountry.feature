@pangea_test @AEAZ-1614
Feature: OMPGdmCountry AEAZ-1614

  Scenario: Full Load curation

    Given I import "/edm/country_v1" by keyFields "localCountry,sourceSystem"
      | countryName | countryCode | localCountry | sourceSystem |
      | Zimbabwe1   | ZW          | ZW           | CONS_LATAM   |
      | Zimbabwe2   | ZR          | ZR           | MDDePuy      |

    And I wait "/edm/country_v1" Async Queue complete

    Given I import "/plan/cns_plan_parameter" by keyFields "sourceSystem,dataObject,attribute,parameter"
      | sourceSystem | dataObject               | attribute  | parameter | inclExcl | parameterValue |
      | CONS_LATAM   | cns_material_plan_status | DPRelevant | Plant     | I        | BR12           |

    And I wait "/plan/cns_plan_parameter" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmCountry.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/omp/gdm_country" by keyFields "countryId"
      | countryId | activeFCTERP | activeOPRERP | activeSOPERP | countryDescription | mrc |
      | ZW        | YES          | YES          | NO           | Zimbabwe1          |     |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/edm/country_v1" and "/omp/gdm_country,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/omp/gdm_country"

    And I will remove all data with region "/plan/edm_failed_data"

