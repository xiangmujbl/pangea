@pangea_test @AEAZ-1614
Feature: OMPGdmCountry AEAZ-1614

  Scenario: Full Load curation

    Given I import "/edm/country_v1" by keyFields "localCountry,sourceSystem"
      | countryName | countryCode | localCountry | sourceSystem |
      | BR          | BARAZIL     |              | CONS_LATAM   |
      | BL          | BL          |              | EMS          |
    And I wait "/edm/country_v1" Async Queue complete

    Given I import "/plan/cns_plan_parameter" by keyFields "sourceSystem"
      | sourceSystem |
      | CONS_LATAM   |
    And I wait "/edm/country_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmCountry.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/omp/gdm_country" by keyFields "countryId"
      | countryId | activeFCTERP | activeOPRERP | activeSOPERP | countryDescription | mrc |
      | BARAZIL   | YES          | YES          | NO           | BR                 |     |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

#    And I compare the number of records between "/edm/country_v1" and "/omp/gdm_country,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/omp/gdm_country"
    And I will remove all data with region "/plan/edm_failed_data"

