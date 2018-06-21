@pangea_test @AEAZ-3685
Feature: OMPGdmLocation AEAZ-3685

  Scenario: Full Load curation - edm

    And I will remove the test file on sink application "GDMLocation_edm.tsv"

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

    Then A file is found on sink application with name "GDMLocation_edm.tsv"

    Then I check file data for filename "GDMLocation_edm.tsv" by keyFields "locationId"
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
      | SP             | OMPGdmLocationEdm | T8        | project_one  |              | AR1_T8  | CONS_LATAM |      |      |      | Missing Location Type Id |
      | SP             | OMPGdmLocationEdm | T10       | project_one  |              | AR1_T10 | CONS_LATAM |      |      |      | Missing Country          |
      | SP             | OMPGdmLocationEdm | T8        | project_one  |              | BR07    | CONS_LATAM |      |      |      | Missing Location Type Id |
      | SP             | OMPGdmLocationEdm | T8        | project_one  |              | VE06    | ZLSR       |      |      |      | Missing Location Type Id |
      | SP             | OMPGdmLocationEdm | T8        | project_one  |              | VE07    | ZBEF       |      |      |      | Missing Location Type Id |
      | SP             | OMPGdmLocationEdm | T8        | project_one  |              | VE08    | ZBEF       |      |      |      | Missing Location Type Id |
      | SP             | OMPGdmLocationEdm | T8        | project_one  |              | VE09    | ZBEF       |      |      |      | Missing Location Type Id |
      | SP             | OMPGdmLocationEdm | T8        | project_one  |              | VE10    | ZBEF       |      |      |      | Missing Location Type Id |


  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/omp/gdm_location"

    And I will remove all data with region "/plan/edm_failed_data"

  Scenario: Full Load curation - xref
  # 1. get atrributes from cns_plan_parameter(rules C2)

    And I will remove the test file on sink application "GDMLocation_xref.tsv"

    Given I import "/plan/cns_spl_pln_loc" by keyFields "sourceSystem,vendorOrCustomer,localNumber,localName"
      | sourceSystem | vendorOrCustomer | localNumber | localCountry | localCurrency | localName | planLocTypeId | localRegion | localPlant |
      | BtB          | V                | 234500_C1   | US           |               | Silgan    | Silgan234500  | NJ          |            |
      | BtB          | V                | 234555_C1   | US           | USD           | Silgan    | Silgan234500  | NJ          | BR12       |
      | BtB          | C                | 234560_C3   | US           | USD           | Silgan    | Silgan234500  | NJ          | BR12       |
      | BtB          | V                | 234565_C4   | US           | USD           | Silgan    | Silgan234500  | NJ          | BR12       |
      | BtB          | V                | 234570_D1   | US           | USD           | Silgan    | Silgan234500  | NJ          | BR12       |
      | BtB          | V                | 234575_D2   | US           | USD           | Silgan    | Silgan234500  | NJ          | BR12       |
      | BtB          | V                | 234585_D4   | US           | USD           | Silgan    | Silgan234500  | NJ          | BR12       |
    And I wait "/plan/cns_spl_pln_loc" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmLocationXref.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMLocation_xref.tsv"

    Then I check file data for filename "GDMLocation_xref.tsv" by keyFields "locationId"
      | locationId         | active | activeFCTERP | activeOPRERP | activeSOPERP | countryId | currencyId | customerId | label  | locationTypeId | regionId | vendorId  |
      | BtB_V_234500_C1    | YES    | NO           | YES          | NO           | US        | USD        |            | Silgan | Silgan234500   | NJ       | 234500_C1 |
      | BtB_BR12$234555_C1 | YES    | NO           | YES          | NO           | US        | USD        |            | Silgan | Silgan234500   | NJ       | 234555_C1 |
      | BtB_BR12$234560_C3 | YES    | NO           | YES          | NO           | US        | USD        | 234560_C3  | Silgan | Silgan234500   | NJ       |           |
      | BtB_BR12$234565_C4 | YES    | NO           | YES          | NO           | US        | USD        |            | Silgan | Silgan234500   | NJ       | 234565_C4 |
      | BtB_BR12$234570_D1 | YES    | NO           | YES          | NO           | US        | USD        |            | Silgan | Silgan234500   | NJ       | 234570_D1 |
      | BtB_BR12$234575_D2 | YES    | NO           | YES          | NO           | US        | USD        |            | Silgan | Silgan234500   | NJ       | 234575_D2 |
      | BtB_BR12$234585_D4 | YES    | NO           | YES          | NO           | US        | USD        |            | Silgan | Silgan234500   | NJ       | 234585_D4 |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/plan/cns_spl_pln_loc" and "/omp/gdm_location,/plan/edm_failed_data"

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/omp/gdm_location"

    And I will remove all data with region "/plan/edm_failed_data"

  Scenario: merge file

    When I execute xd job to merge file "GDMLocation_*" to "GDMLocation.tsv" by keyFields "locationId"

    Then I check file data for filename "GDMLocation.tsv" by keyFields "locationId"
      | locationTypeId | regionId | activeFCTERP | locationId         | customerId | active | vendorId  | label       | currencyId | activeOPRERP | countryId | activeSOPERP |
      | typeid001      | regin001 | YES          | CONS_LATAM_AR_C1   |            | YES    |           | Pilar Plant | AFA        | YES          | 00        | NO           |
      | typeid001      | regin001 | YES          | CONS_LATAM_AR1_T5  |            | YES    |           | Pilar Plant | AFA        | YES          | 00        | NO           |
      | typeid001      | regin001 | YES          | CONS_LATAM_AR1_T6  |            | YES    |           | Pilar Plant | AFA        | NO           | 00        | NO           |
      | typeid001      | regin001 | NO           | CONS_LATAM_AR1_T2  |            | YES    |           | Pilar Plant | AFA        | YES          | 00        | NO           |
      | typeid001      | regin001 | YES          | CONS_LATAM_AR1_T9  |            | YES    |           | Pilar Plant | AFA        | YES          | 00        | NO           |
      | typeid001      | regin001 | YES          | CONS_LATAM_AR1_T7  |            | YES    |           | Pilar Plant | AFA        | YES          | 00        | NO           |
      | typeid001      | regin001 | YES          | CONS_LATAM_AR_T2   |            | YES    |           | Pilar Plant | AFA        | YES          | 00        | NO           |
      | Silgan234500   | NJ       | NO           | BtB_BR12$234560_C3 | 234560_C3  | YES    |           | Silgan      | USD        | YES          | US        | NO           |
      | Silgan234500   | NJ       | NO           | BtB_V_234500_C1    |            | YES    | 234500_C1 | Silgan      | USD        | YES          | US        | NO           |
      | Silgan234500   | NJ       | NO           | BtB_BR12$234555_C1 |            | YES    | 234555_C1 | Silgan      | USD        | YES          | US        | NO           |
      | Silgan234500   | NJ       | NO           | BtB_BR12$234575_D2 |            | YES    | 234575_D2 | Silgan      | USD        | YES          | US        | NO           |
      | Silgan234500   | NJ       | NO           | BtB_BR12$234565_C4 |            | YES    | 234565_C4 | Silgan      | USD        | YES          | US        | NO           |
      | Silgan234500   | NJ       | NO           | BtB_BR12$234585_D4 |            | YES    | 234585_D4 | Silgan      | USD        | YES          | US        | NO           |
      | Silgan234500   | NJ       | NO           | BtB_BR12$234570_D1 |            | YES    | 234570_D1 | Silgan      | USD        | YES          | US        | NO           |