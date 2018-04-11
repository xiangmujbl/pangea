@pangea_test @AEAZ-1764
Feature: OMPGdmLocationEdm AEAZ-1764

  Scenario: Full Load curation

    Given I import "/edm/plant_v1" by keyFields "sourceSystem,localPlant"
      | sourceSystem | localPlant | localPlanningRelevant | country | localCurrency | localPlantName | plantType | region   |
      | CONS_LATAM   | AR01       | X                     | 00      | AR            | Pilar Plant    | AH        | edmPlant |
    And I wait "/edm/plant_v1" Async Queue complete

    Given I import "/plan/cns_plan_parameter" by keyFields "sourceSystem"
      | sourceSystem | dataObject               | attribute  | parameter | parameterValue |
      | CONS_LATAM   | cns_material_plan_status | DPRelevant | Plant     | AR01           |

    And I wait "/plan/cns_plan_parameter" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmLocationEdm.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/omp/gdm_location" by keyFields "locationId"
      | locationId      | active | activeFCTERP | activeOPRERP | activeSOPERP | countryId | currencyId | customerId | label       | locationTypeId | regionId | vendorId |
      | CONS_LATAM_AR01 | YES    | YES          | YES          |              | 00        | AR         |            | Pilar Plant | AH             | edmPlant |          |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/edm/plant_v1" and "/omp/gdm_location,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/omp/gdm_location"

    And I will remove all data with region "/edm/edm_failed_data"

