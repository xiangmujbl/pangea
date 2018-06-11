@pangea_test @AEAZ-4069
Feature: OMPGdmCustomer AEAZ-4069

  # 1. When cns_dem_grp_asgn when cns_dem_grp_asgn-sourceSystem <> cns_plan_dem_grp-sourceSystem or cns_dem_grp_asgn-demandGroup <> cns_plan_dem_grp-demandGroupId, then skip record. (J4,J6,J7)
  # 2. Get first record from cns_dem_grp_asgn when cns_dem_grp_asgn-sourceSystem = cns_plan_dem_grp-sourceSystem and cns_dem_grp_asgn-demandGroup = cns_plan_dem_grp-demandGroupId. (J4,J6,J7)
  # 3. When cns_dem_grp_asgn-affiliateCountry <> cns_clusters-countryID or cns_dem_grp_asgn-sourceSystem <> cns_clusters-sourceSystem, then skip the record. (J1,J3)
  # 4. When cns_dem_grp_asgn-affiliateCountry <> country_V1-localCountry or cns_dem_grp_asgn-sourceSystem <> country_V1-sourceSystem, then skip the record. (J2)
  # 5. When cns_cust_channel-sourceSystem = cns_dem_grp_asgn-sourceSystem and cns_cust_channel-salesOrg = cns_dem_grp_asgn-salesOrganization and cns_cust_channel-channel = cns_dem_grp_asgn-channel, then get channelDesc value. (J5)
  # 6. Set 'YES' to active, activeFCTERP. (D1)
  # 7. Set 'NO' to activeOPRERP, activeSOPERP, distributor, e-Commerce. (D2)
  # 8. Set '' to distributionChannel, forecastSource, globalCustomerId, partner, partnerCountry, partnerName, partnerRegion, partnerRole, sourceLocationId. (D3)

  Scenario: Full Load consumption

    And I will remove the test file on sink application "GDMCustomer.tsv"

    Given I import "/plan/cns_plan_dem_grp" by keyFields "demandGroupId,sourceSystem"
      | demandGroupId | sourceSystem | demandGroupDesc           |
      | 76100003      | CONS_LATAM   | WM VAREJO                 |
      | 76100004      | CONS_LATAM   | WM SAMS                   |
      | 76100005      | CONS_LATAM   | Pharmacy Wholesaler OTHER |
      | 76100007      | CONS_LATAM   | OTHER MASS_OTHER          |

    And I wait "/plan/cns_plan_dem_grp" Async Queue complete

    Given I import "/plan/cns_cust_channel" by keyFields "sourceSystem,salesOrg,channel"
      | sourceSystem | salesOrg | channel | channelDesc         |
      | CONS_LATAM   | BR01     | CH003   | Pharmacy Wholesaler |
      | CONS_LATAM   | BR01     | CH010   | Other Mass          |

    And I wait "/plan/cns_cust_channel" Async Queue complete

    Given I import "/plan/cns_dem_grp_asgn" by keyFields "countryAffiliate,customerId"
      | sourceSystem | countryAffiliate | customerId | demandGroup | channel | channelDescription  | customerName                     | salesOrganization |
      | BTC          | BR               | 5          | 76100003    | CH005   | Modern Trade        | GRUPO WM VAREJO                  | BR01              |
      | CONS_LATAM   | BR1              | 9290       | 76100004    | CH008   | Clubs/Cash & Carry  | GRUPO WMSE ATACADO SAMS          | BR01              |
      | CONS_LATAM   | BR               | 10498      | 76100005    | CH003   | Pharmacy Wholesaler | GRUPO MAXIFARMA DIST DE MED LTDA | BR01              |
      | CONS_LATAM   | BR               | 10881      | 76100005    | CH003   | Pharmacy Wholesaler | GRUPO NEWPHAR IMPORT (ATACADO)   | BR01              |
      | CONS_LATAM   | BR               | 11292      | 76100005    | CH003   | Pharmacy Wholesaler | GRUPO RSA DROGARIAS              | BR01              |
      | CONS_LATAM   | BR               | 11343      | 76100005    | CH003   | Pharmacy Wholesaler | GRUPO NAZARIA PB                 | BR01              |
      | CONS_LATAM   | BR               | 10029      | 76100007    | CH010   | Other Mass          | GRUPO FIALHO E LIMA              | BR01              |

    And I wait "/plan/cns_dem_grp_asgn" Async Queue complete

    Given I import "/plan/cns_clusters" by keyFields "countryId,sourceSystem,cluster,subCluster"
      | countryId | sourceSystem | cluster | subCluster |
      | BR        | CONS_LATAM   | SOUTH   | South      |

    And I wait "/plan/cns_clusters" Async Queue complete

    Given I import "/edm/country_v1" by keyFields "localCountry,sourceSystem"
      | localCountry | sourceSystem | countryCode | countryName | consumerPlanningRegion |
      | BR           | CONS_LATAM   | BR          | Brazil      | 103                    |

    And I wait "/edm/country_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmCustomer.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMCustomer.tsv"

    Then I check file data for filename "GDMCustomer.tsv" by keyFields "customerId"
      | customerId | active | activeFCTERP | activeOPRERP | activeSOPERP | channel | channelDescription  | countryId | CUST_Cluster | distributionChannel | distributor | eCommerce | forecastSource | globalCustomerId | name                      | partner | partnerCountry | partnerName | partnerRegion | partnerRole | planningCustomerGroupID | regionId | salesOrganization | soldTo | sourceLocationId | subCluster |
      | 76100005   | YES    | YES          | NO           | NO           | CH003   | Pharmacy Wholesaler | BR        | SOUTH        |                     | NO          | NO        |                |                  | Pharmacy Wholesaler OTHER |         |                |             |               |             | 76100005                | 103      | BR01              |        |                  | South      |
      | 76100007   | YES    | YES          | NO           | NO           | CH010   | Other Mass          | BR        | SOUTH        |                     | NO          | NO        |                |                  | OTHER MASS_OTHER          |         |                |             |               |             | 76100007                | 103      | BR01              |        |                  | South      |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/plan/edm_failed_data"
