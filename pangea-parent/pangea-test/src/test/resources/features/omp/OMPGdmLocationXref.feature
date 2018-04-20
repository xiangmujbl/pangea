@pangea_test
Feature:  OMPGdmLocationXref-Curation

  Scenario: Full Load curation

    Given I import "/plan/cns_pln_spl_loc" by keyFields "sourceSystem,vendorCustomer,localNumber,localName"
      | sourceSystem | vendorCustomer | localNumber | localCountry | localCurrency | localName | planLocTypeId | localRegion |
      |BtB           |V          |   234500    |US            |USD            |Silgan     |Silgan234500   |NJ           |

    And I wait "/plan/cns_pln_spl_loc" Async Queue complete
    Given I import "/plan/cns_plan_parameter" by keyFields "sourceSystem"
      | parameterValue | sourceSystem | dataObject | attribute | parameter |
      |234500          |CONS_LATAM    |cns_material_plan_status|DPRelevant|Plant|

    And I wait "/plan/cns_plan_parameter" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmLocationXref.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "gdm_omp_location_xref.tsv"

    And I check file data for filename "gdm_omp_location_xref.tsv" by keyFields "locationId"
      | locationId |  active |  activeFCTERP |  activeOPRERP |  activeSOPERP |  countryId |  currencyId |  customerid |  label |  locationTypeId |  regionId |  vendorid |
      |BtB_V_234500|YES      |YES            |YES            |No             |US          |USD          |             |   Silgan   |Silgan234500 |NJ         |234500     |

    #Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
    #  | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    #And I compare the number of records between "/plan/cns_pln_spl_loc" and "/omp/gdm_location,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/omp/gdm_location"

    And I will remove all data with region "/plan/cns_pln_spl_loc"

    #And I will remove the test file on sink application "gdm_omp_location_xref.tsv"

