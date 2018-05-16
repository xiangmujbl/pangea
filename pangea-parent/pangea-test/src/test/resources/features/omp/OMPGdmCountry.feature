@pangea_test @AEAZ-3690
Feature: OMPGdmCountry AEAZ-3690

 Scenario: Full Load curation

   And I will remove the test file on sink application "GDMCountry.tsv"

   Given I import "/edm/country_v1" by keyFields "localCountry,sourceSystem"
      | countryName | countryCode | localCountry | sourceSystem |
      | Zimbabwe1   | ZW           | ZW             | CONS_LATAM   |
      | Zimbabwe2   | ZR           | ZR             | CONS_LATAM   |
      | Zimbabwe2   |              | ZR             | MDDePuy      |
      |              | ZR           | ZR             | MDDePuy      |

    And I wait "/edm/country_v1" Async Queue complete

    Given I import "/plan/cns_plan_parameter" by keyFields "sourceSystem,dataObject,attribute,parameter"
      | sourceSystem | dataObject               | attribute  | parameter | inclExcl | parameterValue |
      | CONS_LATAM   | cns_material_plan_status | DPRelevant | Plant     | I        | BR12           |
    And I wait "/plan/cns_plan_parameter" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmCountry.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMCountry.tsv"

    Then I check file data for filename "GDMCountry.tsv" by keyFields "countryId"
      | countryId | activeFCTERP | activeOPRERP | activeSOPERP | countryDescription |
      | ZW         | YES           | YES           | NO            | Zimbabwe1           |
      | ZR         | YES           | YES           | NO            | Zimbabwe2           |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

#    And I compare the number of records between "/edm/country_v1" and "/omp/gdm_country,/plan/edm_failed_data"

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/omp/gdm_country"

    And I will remove all data with region "/plan/edm_failed_data"


