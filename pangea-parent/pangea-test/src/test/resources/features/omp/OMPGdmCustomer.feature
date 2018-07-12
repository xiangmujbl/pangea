@pangea_test @AEAZ-5744
Feature: OMPGdmCustomer AEAZ-5744

  # 1. When cns_dem_grp_asgn when cns_dem_grp_asgn-sourceSystem <> cns_plan_dem_grp-sourceSystem or cns_dem_grp_asgn-demandGroup <> cns_plan_dem_grp-demandGroupId, then skip record. (J4,J6,J7)
  # 2. Get first record from cns_dem_grp_asgn when cns_dem_grp_asgn-sourceSystem = cns_plan_dem_grp-sourceSystem and cns_dem_grp_asgn-demandGroup = cns_plan_dem_grp-demandGroupId. (J4,J6,J7)
  # 3. When cns_dem_grp_asgn-affiliateCountry <> cns_clusters-countryID or cns_dem_grp_asgn-sourceSystem <> cns_clusters-sourceSystem, then skip the record. (J1,J3)
  # 4. When cns_dem_grp_asgn-affiliateCountry <> country_V1-localCountry or cns_dem_grp_asgn-sourceSystem <> country_V1-sourceSystem, then skip the record. (J2)
  # 5. When cns_cust_channel-sourceSystem = cns_dem_grp_asgn-sourceSystem and cns_cust_channel-salesOrg = cns_dem_grp_asgn-salesOrganization and cns_cust_channel-channel = cns_dem_grp_asgn-channel, then get channelDesc value. (J5)
  # 6. Set 'YES' to active, activeFCTERP. (D1)
  # 7. Set 'NO' to activeOPRERP, activeSOPERP, distributor, e-Commerce. (D2)
  # 8. Set '' to distributionChannel, forecastSource, globalCustomerId, partner, partnerCountry, partnerName, partnerRegion, partnerRole, sourceLocationId. (D3)
  # 9. CustomerId (column 1) = cns_productcustomer-demandGroup, set 'YES' to distributor (T1)
  # 10.CustomerId (column 1) <> cns_productcustomer-demandGroup, set 'NO' to distributor (T1)
  # 11.If cns_plan_dem_grp-sourceSystem <> currency_v1-sourceSystem, leave currencyId blank (J8)
  # 12.If cns_plan_dem_grp-localCurrency <> currency_v1-localCurrency, leave currencyId blank (J8)

  Scenario: Full Load consumption

    And I will remove the test file on sink application "GDMCustomer.tsv"

    Given I import "/plan/cns_plan_dem_grp" by keyFields "demandGroupId,sourceSystem"
      | demandGroupId | sourceSystem | demandGroupDesc               | localCurrency |
      | 76100003      | CONS_LATAM   | WM VAREJO                     | ARS           |
      | 76100004      | CONS_LATAM   | WM SAMS                       | ARS           |
      | 76100005      | CONS_LATAM   | Pharmacy Wholesa ler OTHER    | ARS           |
      | 76100007      | CONS_LATAM   | OTHER MASS_OTHER              | ARS           |

      | 76100006      | CONS_LATAM   | Clubs/Cash & Carry OTHER      | ARS           |
      | 76100009      | CONS_LATAM   | Wholesaler Traditional_OTHER  | CLP           |
      | 76100010      | CONS_LATAM   | Distributor Traditional_OTHER | BRL           |

    And I wait "/plan/cns_plan_dem_grp" Async Queue complete

    Given I import "/plan/cns_cust_channel" by keyFields "sourceSystem,salesOrg,channel"
      | sourceSystem | salesOrg | channel | channelDesc             |
      | CONS_LATAM   | BR01     | CH003   | Pharmacy Wholesaler     |
      | CONS_LATAM   | BR01     | CH010   | Other Mass              |
      | CONS_LATAM   | BR01     | CH008   | Clubs/Cash & Carry      |
      | CONS_LATAM   | BR01     | CH007   | Wholesaler Traditional  |
      | CONS_LATAM   | BR01     | CH006   | Distributor Traditional |

    And I wait "/plan/cns_cust_channel" Async Queue complete

    Given I import "/plan/cns_dem_grp_asgn" by keyFields "countryAffiliate,customerId"
      | sourceSystem | countryAffiliate | customerId | demandGroup | channel | channelDescription      | customerName                      | salesOrganization |
      | BTC          | BR               | 5          | 76100003    | CH005   | Modern Trade            | GRUPO WM VAREJO                   | BR01              |
      | CONS_LATAM   | BR1              | 9290       | 76100004    | CH008   | Clubs/Cash & Carry      | GRUPO WMSE ATACADO SAMS           | BR01              |
      | CONS_LATAM   | BR               | 10498      | 76100005    | CH003   | Pharmacy Wholesaler     | GRUPO MAXIFARMA DIST DE MED LTDA  | BR01              |
      | CONS_LATAM   | BR               | 10881      | 76100005    | CH003   | Pharmacy Wholesaler     | GRUPO NEWPHAR IMPORT (ATACADO)    | BR01              |
      | CONS_LATAM   | BR               | 11292      | 76100005    | CH003   | Pharmacy Wholesaler     | GRUPO RSA DROGARIAS               | BR01              |
      | CONS_LATAM   | BR               | 11343      | 76100005    | CH003   | Pharmacy Wholesaler     | GRUPO NAZARIA PB                  | BR01              |
      | CONS_LATAM   | BR               | 10029      | 76100007    | CH010   | Other Mass              | GRUPO FIALHO E LIMA               | BR01              |
      | CONS_LATAM   | BR               | 1346       | 76100006    | CH008   | Clubs/Cash & Carry      | GRUPO MAKRO ATAC SP               | BR01              |
      | CONS_LATAM   | BR               | 866        | 76100009    | CH007   | Wholesaler Traditional  | GRUPO BECHARA SULEIMAN & CIA      | BR01              |
      | CONS_LATAM   | BR               | 10         | 76100010    | CH006   | Distributor Traditional | GRUPO DISTRIBUIDORA GOLFINHO LTDA | BR01              |

    And I wait "/plan/cns_dem_grp_asgn" Async Queue complete

    Given I import "/plan/cns_clusters" by keyFields "countryId,sourceSystem,cluster,subCluster"
      | countryId | sourceSystem | cluster | subCluster |
      | BR        | CONS_LATAM   | BRAZIL  | Brazil     |

    And I wait "/plan/cns_clusters" Async Queue complete

    Given I import "/edm/country_v1" by keyFields "localCountry,sourceSystem"
      | localCountry | sourceSystem | countryCode | countryName | consumerPlanningRegion |
      | BR           | CONS_LATAM   | BR          | Brazil      | 103                    |

    And I wait "/edm/country_v1" Async Queue complete

    Given I import "/plan/cns_productcustomer" by keyFields "sourceSystem,customerId,productId"
      | sourceSystem | customerId | productId |
      | CONS_LATAM   | 76100006    | 87818     |
      | CONS_LATAM   | 76100009    | 87818     |

    And I wait "/plan/cns_productcustomer" Async Queue complete

    Given I import "/edm/currency_v1" by keyFields "sourceSystem,localCurrency"
      | sourceSystem | localCurrency | currencyCode |
      | CONS_LATAM   | ARS           | ARS          |
      | BTC          | CLP           | CLP          |

    And I wait "/edm/currency_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmCustomer.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMCustomer.tsv"

    Then I check file data for filename "GDMCustomer.tsv" by keyFields "customerId"
      | customerId | active | activeFCTERP | activeOPRERP | activeSOPERP | channel | channelDescription      | countryId | CUST_Cluster | distributionChannel | distributor | eCommerce | forecastSource | globalCustomerId | name                          | partner | partnerCountry | partnerName | partnerRegion | partnerRole | planningCustomerGroupId | regionId | salesOrganization | soldTo | sourceLocationId | subClusterId | currencyId |
      | 76100005   | YES    | YES          | NO           | NO           | CH003   | Pharmacy Wholesaler     | BR        | BRAZIL       |                     | NO          | NO        |                |                  | Pharmacy Wholesa ler OTHER    |         |                |             |               |             | 76100005                | 103      | BR01              |        |                  | Brazil       | ARS        |
      | 76100007   | YES    | YES          | NO           | NO           | CH010   | Other Mass              | BR        | BRAZIL       |                     | NO          | NO        |                |                  | OTHER MASS_OTHER              |         |                |             |               |             | 76100007                | 103      | BR01              |        |                  | Brazil       | ARS        |
      | 76100006   | YES    | YES          | NO           | NO           | CH008   | Clubs/Cash & Carry      | BR        | BRAZIL       |                     | YES         | NO        |                |                  | Clubs/Cash & Carry OTHER      |         |                |             |               |             | 76100006                | 103      | BR01              |        |                  | Brazil       | ARS        |
      | 76100009   | YES    | YES          | NO           | NO           | CH007   | Wholesaler Traditional  | BR        | BRAZIL       |                     | YES         | NO        |                |                  | Wholesaler Traditional_OTHER  |         |                |             |               |             | 76100009                | 103      | BR01              |        |                  | Brazil       |            |
      | 76100010   | YES    | YES          | NO           | NO           | CH006   | Distributor Traditional | BR        | BRAZIL       |                     | NO          | NO        |                |                  | Distributor Traditional_OTHER |         |                |             |               |             | 76100010                | 103      | BR01              |        |                  | Brazil       |            |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/plan/edm_failed_data"
