@pangea_test @AEAZ-3685
Feature:  OMPGdmLocationXref AEAZ-3685

  Scenario: Full Load curation
    # 1. get atrributes from cns_plan_parameter(rules C2)

    Given I import "/plan/cns_spl_pln_loc" by keyFields "sourceSystem,vendororCustomer,localNumber,localName"
      | sourceSystem | vendororCustomer | localNumber | localCountry | localCurrency | localName | planLocTypeId | localRegion | localPlant |
      | BtB          | V                | 234500_C1   | US           |               | Silgan    | Silgan234500  | NJ          |            |
      | BtB          | V                | 234555_C1   | US           | USD           | Silgan    | Silgan234500  | NJ          | BR12       |
      | BtB          | C                | 234560_C3   | US           | USD           | Silgan    | Silgan234500  | NJ          | BR12       |
      | BtB          | V                | 234565_C4   | US           | USD           | Silgan    | Silgan234500  | NJ          | BR12       |
      | BtB          | V                | 234570_D1   | US           | USD           | Silgan    | Silgan234500  | NJ          | BR12       |
      | BtB          | V                | 234575_D2   | US           | USD           | Silgan    | Silgan234500  | NJ          | BR12       |
      | BtB          | V                | 234585_D4   | US           | USD           | Silgan    | Silgan234500  | NJ          | BR12       |
    And I wait "/plan/cns_spl_pln_loc" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmLocationXref.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMLocation.tsv"

    Then I check file data for filename "GDMLocation.tsv" by keyFields "locationId"
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

    And I delete the test data

    And I will remove all data with region "/omp/gdm_location"

    And I will remove all data with region "/plan/edm_failed_data"

    And I will remove the test file on sink application "GDMLocation.tsv"

