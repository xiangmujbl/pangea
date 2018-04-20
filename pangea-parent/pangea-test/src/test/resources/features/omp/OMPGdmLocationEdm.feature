@pangea_test @AEAZ-1764
Feature: OMPGdmLocationEdm AEAZ-1764

  Scenario: Full Load curation

    Given I import "/edm/plant_v1" by keyFields "sourceSystem,localPlant"
      | sourceSystem | localPlant | localPlanningRelevant | country | localCurrency | localPlantName | plantType | region     | localCountry  |
      | CONS_LATAM   | AR01       | X                     | 00      | AR            | Pilar Plant    | AH        | edmPlant   | nanjing       |
      | CONS_LATAM02 | AR02       | X                     | 002     | AR2           | Pilar Plant2   | AH2       | edmPlant2  | nanjing2      |
      | CONS_LATAM03 | AR03       | X                     |         | AR3           | Pilar Plant3   | AH3       | edmPlant3  | nanjing3      |
    And I wait "/edm/plant_v1" Async Queue complete

    Given I import "/plan/cns_plan_parameter" by keyFields "sourceSystem"
      | sourceSystem   | dataObject               | attribute  | parameter | parameterValue |
      | CONS_LATAM     | cns_material_plan_status | DPRelevant | Plant     | AR01           |
      | CONS_LATAM     | cns_material_plan_status | DPRelevant | Plant     | AR02           |
      | CONS_LATAM     | cns_material_plan_status | DPRelevant | Plant     | AR03           |

    And I wait "/plan/cns_plan_parameter" Async Queue complete

    Given I import "/edm/country_v1" by keyFields "sourceSystem"
      | sourceSystem   | localCountry      | consumerPlanningRegion |
      | CONS_LATAM     | nanjing           | regin001               |
      | CONS_LATAM02   | nanjing2          | regin002               |
      | CONS_LATAM03   | nanjing3          | regin003               |

    And I wait "/edm/country_v1" Async Queue complete

    Given I import "/plan/cns_plant_attr" by keyFields "sourceSystem,localPlant"
      | sourceSystem | localPlant      | planLocTypeId |
      | CONS_LATAM   | AR01            | typeid001     |
      | CONS_LATAM02 | AR002           | typeid002     |
      | CONS_LATAM03 | AR03            | typeid003     |

    And I wait "/plan/cns_plant_attr" Async Queue complete

    Given I import "/edm/currency_v1" by keyFields "sourceSystem"
      | sourceSystem   | localCurrency     | currencyCode  |
      | CONS_LATAM     | AR                | code001       |
      | CONS_LATAM02   | AR2               | code002       |
      | CONS_LATAM03   | AR3               | code003       |

    And I wait "/edm/currency_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmLocationEdm.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/omp/gdm_location" by keyFields "locationId"
      | locationId      | active | activeFCTERP | activeOPRERP | activeSOPERP | countryId | currencyId | customerId | label       | locationTypeId | regionId | vendorId |
      | CONS_LATAM_AR01 | YES    | YES          | YES          |    NO        | 00        | code001    |            | Pilar Plant | typeid001      | regin001 |          |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID       | errorCode | sourceSystem | businessArea | key1         | key2  | key3 | key4 | key5 | errorValue               |
      | SP             | OMPGdmLocationEdm | T8        |              |              | CONS_LATAM02 | AR02  | key3 | key4 | key5 | Missing Location Type Id |
      | SP             | OMPGdmLocationEdm | T10       |              |              | CONS_LATAM03 | AR03  | key3 | key4 | key5 | Missing Country          |

    And I compare the number of records between "/edm/plant_v1" and "/omp/gdm_location,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/omp/gdm_location"

    And I will remove all data with region "/edm/edm_failed_data"

