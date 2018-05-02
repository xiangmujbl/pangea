@pangea_test
Feature:  OMPGdmFpb-Curation

  Scenario: Full Load curation

    #1.Join ( cns_fin_plan_qty-localMaterialNumber = material_global_v1-localMaterialNumber AND cns_fin_plan_qty-identifier = 'FPB' ) AND
    #(cns_fin_plan_val-localMaterialNumber = material_global_v1-localMaterialNumber AND cns_fin_plan_val-identifier = 'FPB' ) AND
    #source_system_v1-localSourceSystem = material_global_v1-sourceSystem
    #2.get fpbId by ProductID+UniqueID
    #3.get countryId from /edm/country_v1-countryCode(J2 get country_v1 with country_v1-localCountry=cns_fin_plan_qty-country)
    #4.get currencyId from /edm/currency_v1-currencyCode(J3 get currency_v1 with currency_v1-localCurrency=cns_fin_plan_qty-currency)
    #5.get productID by T1(CONCATENATE: source_system_v1-sourceSystem,'_' and material_global_v1-localDPParentCode)
    #6.get value from cns_fin_plan_val-Value
    #7.get volume by J4(If cns_fin_plan_qty-UnitID=material_global_v1-localBaseUom JOIN material_auom_v1-localMaterialNumber=material_global_v1-localMaterialNumber AND
    #material_auom_v1-localAuom = cns_fin_plan_qty-UnitID,IF value found,cns_fin_plan_qty-quantity = cns_fin_plan_qty-quantity * material_auom_v1-localNumerator / localDenominator ))

    And I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | sourceSystem     | localMaterialNumber | localDpParentCode | localBaseUom |
      | Project_One      | AR01                | LDPC01            | LBU01        |
      | [Consumer LATAM] | AR02                | LDPC02            | LBU02        |
    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/plan/cns_fin_plan_qty" by keyFields "localMaterialNumber,identifier"
      | localMaterialNumber | identifier | country | currency | unitId | quantity |
      | AR01                | FPB        | AT01    | ADP      | LBU01  | 100      |
      | AR02                | FPB        | AT02    | AED      | LBU02  | 200      |
    And I wait "/plan/cns_fin_plan_qty" Async Queue complete

    And I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | localSourceSystemName | sourceSystem | sourceSystemName   |
      | Project_One       | Project One           | CON_LATAM    | Consumer Latam Ent |
      | [Consumer LATAM]  | Consumer Latam        | CON_LATAM    | Consumer Latam Ent |
      | [EMS]             | EMS                   | EMS          | EMS Ent            |
    And I wait "/edm/source_system_v1" Async Queue complete

    Given I import "/edm/country_v1" by keyFields "sourceSystem,localCountry"
      | sourceSystem | localCountry | countryCode | countryName | consumerPlanningRegion |
      | CONS_LATAM   | AT01         | CAT01       |             |                        |
      | CONS_LATAM   | AT02         | CAT02       |             |                        |
    And I wait "/edm/country_v1" Async Queue complete

    Given I import "/edm/currency_v1" by keyFields "sourceSystem,localCurrency"
      | sourceSystem | localCurrency | currencyCode | currencyName | isoNumeric |
      | CONS_LATAM   | ADP           | CADP         |              | -          |
      | CONS_LATAM   | AED           | CAED         | UAE Dirham   | -          |
    And I wait "/edm/currency_v1" Async Queue complete

    Given I import "/plan/cns_fin_plan_val" by keyFields "localMaterialNumber,identifier"
      | localMaterialNumber | identifier | value |
      | AR01                | FPB        | VAL01 |
      | AR02                | FPB        | VAL02 |
    And I wait "/plan/cns_fin_plan_val" Async Queue complete

    Given I import "/edm/material_auom_v1" by keyFields "localMaterialNumber,localAuom"
      | localMaterialNumber | localAuom | localNumerator | localDenominator |
      | AR01                | LBU01     | 100            | 5                |
      | AR02                | LBU02     | 200            | 20               |
    And I wait "/edm/material_auom_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmFpb.xml" and execute file "jar/pangea-view.jar"

#    Then I check region data "/omp/gdm_fpb" by keyFields "fpbId"
#      | fpbId | countryId | currencyId | dueDate | fromDueDate | productId | value | volume |
#
#    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
#      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |
#
#    And I compare the number of records between "/plan/cns_fin_plan_qty" and "/omp/gdm_fpb,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/omp/gdm_fpb"

    And I will remove all data with region "/plan/cns_fin_plan_qty"

