@pangea_test @AEAZ-3685
Feature: OMPGdmLocationEdm AEAZ-3685

  Scenario: Full Load curation

    Given I import "/edm/plant_v1" by keyFields "sourceSystem,localPlant"
      | sourceSystem | localPlant | localPlanningRelevant | country | localCurrency | localPlantName | plantType | region   | localCountry |
      | CONS_LATAM   | AR_C1      | X                     | 00      | AR            | Pilar Plant    | AH        | edmPlant | AR           |
      | CONS_LATAM   | AR_T2      | X                     | 00      | AR            | Pilar Plant    | AH        | edmPlant | AR           |
      | CONS_LATAM   | AR1_T2     | X                     | 00      | AR            | Pilar Plant    | AH        | edmPlant | AR           |
      | CONS_LATAM   | AR1_T5     | X                     | 00      | AR            | Pilar Plant    | AH        | edmPlant | AR           |
      | CONS_LATAM   | AR1_T6     |                       | 00      | AR            | Pilar Plant    | AH        | edmPlant | AR           |
      | CONS_LATAM   | AR1_T7     | X                     | 00      | AR            | Pilar Plant    | AH        | edmPlant | AR           |
      | CONS_LATAM   | AR1_T9     | X                     | 00      | AR            | Pilar Plant    | AH        | edmPlant | AR           |
      | CONS_LATAM   | AR1_T8     | X                     | 00      | AR            | Pilar Plant    | AH        | edmPlant | AR           |
      | CONS_LATAM   | AR1_T10    | X                     |         | AR            | Pilar Plant    | AH        | edmPlant | AR           |

    And I wait "/edm/plant_v1" Async Queue complete

    Given I import "/plan/cns_plan_parameter" by keyFields "sourceSystem,parameterValue,parameter"
      | sourceSystem | dataObject               | attribute  | parameter | parameterValue |
      | CONS_LATAM   | cns_material_plan_status | DPRelevant | Plant     | AR_C1          |
      | CONS_LATAM   | cns_material_plan_status | DPRelevant | Plant     | AR_T2          |
      | CONS_LATAM   | cns_material_plan_status | SPRelevant | Plant     | AR1_T2         |
      | CONS_LATAM   | cns_material_plan_status | DPRelevant | Plant     | AR1_T5         |
      | CONS_LATAM   | cns_material_plan_status | DPRelevant | Plant     | AR1_T6         |
      | CONS_LATAM   | cns_material_plan_status | DPRelevant | Plant     | AR1_T7         |
      | CONS_LATAM   | cns_material_plan_status | DPRelevant | Plant     | AR1_T9         |
      | CONS_LATAM   | cns_material_plan_status | DPRelevant | Plant     | AR1_T8         |
      | CONS_LATAM   | cns_material_plan_status | DPRelevant | Plant     | AR1_T10        |

    And I wait "/plan/cns_plan_parameter" Async Queue complete

    Given I import "/edm/country_v1" by keyFields "sourceSystem"
      | sourceSystem | localCountry | consumerPlanningRegion |
      | CONS_LATAM   | AR           | regin001               |

    And I wait "/edm/country_v1" Async Queue complete

    Given I import "/plan/cns_plant_attr" by keyFields "sourceSystem,localPlant"
      | sourceSystem | localPlant | planLocTypeId |
      | CONS_LATAM   | AR_C1      | typeid001     |
      | CONS_LATAM   | AR_T2      | typeid001     |
      | CONS_LATAM   | AR1_T2     | typeid001     |
      | CONS_LATAM   | AR1_T5     | typeid001     |
      | CONS_LATAM   | AR1_T6     | typeid001     |
      | CONS_LATAM   | AR1_T7     | typeid001     |
      | CONS_LATAM   | AR1_T9     | typeid001     |
      | CONS_LATAM   | AR1111     | typeid001     |
      | CONS_LATAM   | AR1_T10    | typeid001     |

    And I wait "/plan/cns_plant_attr" Async Queue complete

    Given I import "/edm/currency_v1" by keyFields "sourceSystem"
      | sourceSystem | localCurrency | currencyCode |
      | CONS_LATAM   | AR            | AFA          |

    And I wait "/edm/currency_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmLocationEdm.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMLocation.tsv"

    Then I check file data for filename "GDMLocation.tsv" by keyFields "locationId"
      | locationId        | active | activeFCTERP | activeOPRERP | activeSOPERP | countryId | currencyId | customerId | label       | locationTypeId | regionId | vendorId |
      | CONS_LATAM_AR_C1  | YES    | YES          | YES          | NO           | 00        | AFA        |            | Pilar Plant | typeid001      | regin001 |          |
      | CONS_LATAM_AR_T2  | YES    | YES          | YES          | NO           | 00        | AFA        |            | Pilar Plant | typeid001      | regin001 |          |
      | CONS_LATAM_AR1_T2 | YES    | NO           | YES          | NO           | 00        | AFA        |            | Pilar Plant | typeid001      | regin001 |          |
      | CONS_LATAM_AR1_T5 | YES    | YES          | YES          | NO           | 00        | AFA        |            | Pilar Plant | typeid001      | regin001 |          |
      | CONS_LATAM_AR1_T6 | YES    | YES          | NO           | NO           | 00        | AFA        |            | Pilar Plant | typeid001      | regin001 |          |
      | CONS_LATAM_AR1_T7 | YES    | YES          | YES          | NO           | 00        | AFA        |            | Pilar Plant | typeid001      | regin001 |          |
      | CONS_LATAM_AR1_T9 | YES    | YES          | YES          | NO           | 00        | AFA        |            | Pilar Plant | typeid001      | regin001 |          |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID       | errorCode | sourceSystem | businessArea | key1    | key2       | key3 | key4 | key5 | errorValue               |
      | SP             | OMPGdmLocationEdm | T8        | omp          |              | AR1_T8  | CONS_LATAM |      |      |      | Missing Location Type Id |
      | SP             | OMPGdmLocationEdm | T10       | omp          |              | AR1_T10 | CONS_LATAM |      |      |      | Missing Country          |

    And I compare the number of records between "/edm/plant_v1" and "/omp/gdm_location,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/omp/gdm_location"

    And I will remove all data with region "/plan/edm_failed_data"

    And I will remove the test file on sink application "GDMLocation.tsv"

