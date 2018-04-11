@pangea_test @AEAZ-1764
Feature:  OMPGdmLocationXref AEAZ-1764

  Scenario: Full Load curation
    # 1. get atrributes from cns_plan_parameter(rules C2)

    Given I import "/plan/cns_pln_spl_loc" by keyFields "sourceSystem,vendorCustomer,localNumber,localName"
      | sourceSystem | vendorCustomer | localNumber | localCountry | localCurrency | localName | planLocTypeId | localRegion |
      | BtB          | V              | 234500      | US           | USD           | Silgan    | Silgan234500  | NJ          |

    And I wait "/plan/cns_pln_spl_loc" Async Queue complete
    Given I import "/plan/cns_plan_parameter" by keyFields "sourceSystem"
      | parameterValue | sourceSystem | dataObject               | attribute  | parameter |
      | 234500         | CONS_LATAM   | cns_material_plan_status | DPRelevant | Plant     |

    And I wait "/plan/cns_plan_parameter" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmLocationXref.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/omp/gdm_location" by keyFields "locationId"
      | locationId   | active | activeFCTERP | activeOPRERP | activeSOPERP | countryId | currencyId | customerId | label  | locationTypeId | regionId | vendorId |
      | BtB_V_234500 | YES    | YES          | YES          |              | US        | USD        |            | Silgan | Silgan234500   | NJ       | 234500   |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/plan/cns_pln_spl_loc" and "/omp/gdm_location,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/omp/gdm_location"

    And I will remove all data with region "/plan/edm_failed_data"

