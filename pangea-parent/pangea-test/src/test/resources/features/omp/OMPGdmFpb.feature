@pangea_test @
Feature:  OMPGdmFpb-Curation

  Scenario: Full Load curation

    Given I import "/edm/material_global_v1" by keyFields ""
      | sourceSystem | localMaterialNumber | localDPParentCode | localBaseUom |
    And I wait "/edm/material_global_v1" Async Queue complete
    Given I import "/plan/cns_fin_plan_qty" by keyFields ""
      | localMaterialNumber | country | currency | unitId | quantity |
    And I wait "/plan/cns_fin_plan_qty" Async Queue complete
    Given I import "/edm/source_system_v1" by keyFields ""
      | localSourceSystem | localSourceSystemName | sourceSystem | sourceSystemName |
    And I wait "/edm/source_system_v1" Async Queue complete
    Given I import "/plan/cns_fin_plan_val" by keyFields ""
      | localMaterialNumber | value |
    And I wait "/plan/cns_fin_plan_val" Async Queue complete
    Given I import "/edm/material_auom_v1" by keyFields ""
      | localMaterialNumber | localAuom | localNumerator | localDenominator |
    And I wait "/edm/material_auom_v1" Async Queue complete
    Given I import "/edm/country_v1" by keyFields ""
      | sourceSystem | localCountry | countryCode | countryName | consumerPlanningRegion |
    And I wait "/edm/country_v1" Async Queue complete
    Given I import "/edm/currency_v1" by keyFields ""
      | sourceSystem | localCurrency | currencyCode | currencyName | isoNumeric |
    And I wait "/edm/currency_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmFpb.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/omp/gdm_fpb" by keyFields ""
      | fpbId | countryId | currencyId | dueDate | fromDueDate | productId | value | volume |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/edm/material_global_v1" and "/omp/gdm_fpb,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/omp/gdm_fpb"

    And I will remove all data with region "/edm/material_global_v1"

