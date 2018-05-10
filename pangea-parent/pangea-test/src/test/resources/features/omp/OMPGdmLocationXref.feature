@pangea_test @AEAZ-1764
Feature:  OMPGdmLocationXref AEAZ-1764

  Scenario: Full Load curation
    # 1. get atrributes from cns_plan_parameter(rules C2)

    Given I import "/plan/cns_spl_pln_loc" by keyFields "sourceSystem,vendorOrCustomer,localNumber,localName"
      | sourceSystem | vendorOrCustomer | localNumber | localCountry | localCurrency | localName | planLocTypeId | localRegion | localPlant |
      | BtB          | V                | 234500      | US           | USD           | Silgan    | Silgan234500  | NJ          |            |
      | BtB          | V                | 234555      | US           | USD           | Silgan    | Silgan234500  | NJ          | BR12       |
    And I wait "/plan/cns_spl_pln_loc" Async Queue complete

    Given I import "/plan/cns_plan_parameter" by keyFields "sourceSystem"
      | parameterValue | sourceSystem | dataObject               | attribute  | parameter |
      | 234500         | CONS_LATAM   | cns_material_plan_status | DPRelevant | Plant     |
      | 234555         | CONS_LATAM   | cns_material_plan_status | DPRelevant | Plant     |
    And I wait "/plan/cns_plan_parameter" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmLocationXref.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMLocation_xref.tsv"

    Then I check file data for filename "GDMLocation_xref.tsv" by keyFields "locationId"
      | locationId      | active | activeFCTERP | activeOPRERP | activeSOPERP | countryId | currencyId | customerId | label  | locationTypeId | regionId | vendorId |
      | BtB_V_234500    | YES    | NO           | YES          | NO           | US        | USD        |            | Silgan | Silgan234500   | NJ       | 234500   |
      | BtB_BR12$234555 | YES    | NO           | YES          | NO           | US        | USD        |            | Silgan | Silgan234500   | NJ       | 234555   |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/plan/cns_spl_pln_loc" and "/omp/gdm_location,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/omp/gdm_location"

    And I will remove all data with region "/plan/edm_failed_data"

    And I will remove the test file on sink application "GDMLocation_xref.tsv"

