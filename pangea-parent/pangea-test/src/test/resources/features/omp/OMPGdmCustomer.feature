@pangea_test @AEAZ-4069
Feature: OMPGdmCustomer AEAZ-4069

  @Scenario1
  Scenario: Get first record from cns_dem_grp_asgn (J4,J6,J7)

    And I will remove the test file on sink application "GDMCustomer.tsv"

    # when cns_dem_grp_asgn when cns_dem_grp_asgn-sourceSystem <> cns_plan_dem_grp-sourceSystem
    # or cns_dem_grp_asgn-demandGroup <> cns_plan_dem_grp-demandGroupId, then skip record.
    Given I import "/plan/cns_plan_dem_grp" by keyFields "demandGroupId,sourceSystem"
      | demandGroupId | sourceSystem | demandGroupDesc           |
      | 76100001      | CONS_LATAM   | SCAPOL                    |
      | 76100005      | CONS_LATAM   | Pharmacy Wholesaler OTHER |

    And I wait "/plan/cns_plan_dem_grp" Async Queue complete

    Given I import "/plan/cns_cust_channel" by keyFields "sourceSystem,salesOrg,channel"
      | sourceSystem | salesOrg | channel | channelDesc         |
      | CONS_LATAM   | BR01     | CH003   | Pharmacy Wholesaler |

    And I wait "/plan/cns_cust_channel" Async Queue complete

    # get first record from cns_dem_grp_asgn when cns_dem_grp_asgn-sourceSystem = cns_plan_dem_grp-sourceSystem
    # and cns_dem_grp_asgn-demandGroup = cns_plan_dem_grp-demandGroupId
    Given I import "/plan/cns_dem_grp_asgn" by keyFields "countryAffiliate,customerId"
      | sourceSystem | countryAffiliate | customerId | demandGroup | channel | channelDescription      | customerName                        | salesOrg |
      | BTC          | BR               | 0000000323 | 76100001    | CH006   | Distributor Traditional | Distributor Traditional             | BR01     |
      | CONS_LATAM   | BR               | 0000000082 | 76100005    | CH003   | Pharmacy Wholesaler     | GRUPO DISTRIB.BRASIL MEDICAMENTOS L | BR01     |
      | CONS_LATAM   | BR               | 0000000095 | 76100005    | CH003   | Pharmacy Wholesaler     | GRUPO TAKEDA COMERCIO LTDA          | BR01     |
      | CONS_LATAM   | BR               | 0000000268 | 76100005    | CH003   | Pharmacy Wholesaler     | GRUPO MERCANTIL FARMED              | BR01     |

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
      | 76100005   | YES    | YES          | NO           | NO           | CH003   | Pharmacy Wholesaler | BR        | SOUTH       |                     | NO          | NO        |                |                  | Pharmacy Wholesaler OTHER |         |                |             |               |             | 76100005                | 103      | BR01              |        |                  | South      |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/plan/edm_failed_data"

    And I will remove the test file on sink application "GDMCustomer.tsv"


  @Scenario2
  Scenario: Get attributes from cns_clusters, otherwise skip that record (J1,J3)

    And I will remove the test file on sink application "GDMCustomer.tsv"

    Given I import "/plan/cns_plan_dem_grp" by keyFields "demandGroupId,sourceSystem"
      | demandGroupId | sourceSystem | demandGroupDesc           |
      | 76100001      | CONS_LATAM   | SCAPOL                    |
      | 76100005      | CONS_LATAM   | Pharmacy Wholesaler OTHER |

    And I wait "/plan/cns_plan_dem_grp" Async Queue complete

    Given I import "/plan/cns_cust_channel" by keyFields "sourceSystem,salesOrg,channel"
      | sourceSystem | salesOrg | channel | channelDesc         |
      | CONS_LATAM   | BR01     | CH003   | Pharmacy Wholesaler |

    And I wait "/plan/cns_cust_channel" Async Queue complete

    # when cns_dem_grp_asgn-affiliateCountry <> cns_clusters-countryID
    # or cns_dem_grp_asgn-sourceSystem <> cns_clusters-sourceSystem, then skip the record.
    Given I import "/plan/cns_dem_grp_asgn" by keyFields "countryAffiliate,customerId"
      | sourceSystem | countryAffiliate | customerId | demandGroup | channel | channelDescription      | customerName                        | salesOrg |
      | CONS_LATAM   | BR1              | 0000000323 | 76100001    | CH006   | Distributor Traditional | Distributor Traditional             | BR01     |
      | CONS_LATAM   | BR               | 0000000082 | 76100005    | CH003   | Pharmacy Wholesaler     | GRUPO DISTRIB.BRASIL MEDICAMENTOS L | BR01     |

    And I wait "/plan/cns_dem_grp_asgn" Async Queue complete

    Given I import "/plan/cns_clusters" by keyFields "countryId,sourceSystem,cluster,subCluster"
      | countryId | sourceSystem | cluster | subCluster         |
      | BR        | CONS_LATAM   | SOUTH   | South              |
      | BR1       | CONS_LATAM   | ANDEAN  | Andean Sub Cluster |

    And I wait "/plan/cns_clusters" Async Queue complete

    Given I import "/edm/country_v1" by keyFields "localCountry,sourceSystem"
      | localCountry | sourceSystem | countryCode | countryName | consumerPlanningRegion |
      | BR           | CONS_LATAM   | BR          | Brazil      | 103                    |

    And I wait "/edm/country_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmCustomer.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMCustomer.tsv"

    Then I check file data for filename "GDMCustomer.tsv" by keyFields "customerId"
      | customerId | active | activeFCTERP | activeOPRERP | activeSOPERP | channel | channelDescription  | countryId | CUST_Cluster | distributionChannel | distributor | eCommerce | forecastSource | globalCustomerId | name                      | partner | partnerCountry | partnerName | partnerRegion | partnerRole | planningCustomerGroupID | regionId | salesOrganization | soldTo | sourceLocationId | subCluster |
      | 76100005   | YES    | YES          | NO           | NO           | CH003   | Pharmacy Wholesaler | BR        | SOUTH       |                     | NO          | NO        |                |                  | Pharmacy Wholesaler OTHER |         |                |             |               |             | 76100005                | 103      | BR01              |        |                  | South      |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

#    And I compare the number of records between "/plan/cns_dem_grp_asgn" and "/omp/gdm_customer,/plan/edm_failed_data"

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/plan/edm_failed_data"

    And I will remove the test file on sink application "GDMCustomer.tsv"


  @Scenario3
  Scenario:  Get attributes from country_v1, otherwise skip that record (J2)

    And I will remove the test file on sink application "GDMCustomer.tsv"

    Given I import "/plan/cns_plan_dem_grp" by keyFields "demandGroupId,sourceSystem"
      | demandGroupId | sourceSystem | demandGroupDesc           |
      | 76100001      | CONS_LATAM   | SCAPOL                    |
      | 76100005      | CONS_LATAM   | Pharmacy Wholesaler OTHER |

    And I wait "/plan/cns_plan_dem_grp" Async Queue complete

    Given I import "/plan/cns_cust_channel" by keyFields "sourceSystem,salesOrg,channel"
      | sourceSystem | salesOrg | channel | channelDesc         |
      | CONS_LATAM   | BR01     | CH003   | Pharmacy Wholesaler |

    And I wait "/plan/cns_cust_channel" Async Queue complete

    Given I import "/plan/cns_dem_grp_asgn" by keyFields "countryAffiliate,customerId"
      | sourceSystem | countryAffiliate | customerId | demandGroup | channel | channelDescription  | customerName                        | salesOrg |
      | CONS_LATAM   | VE               | 0000008289 | 76100002    | CH008   | Modern Trade        | Clubs/Cash & Carry                  | BR01     |
      | CONS_LATAM   | BR               | 0000000082 | 76100005    | CH003   | Pharmacy Wholesaler | GRUPO DISTRIB.BRASIL MEDICAMENTOS L | BR01     |

    And I wait "/plan/cns_dem_grp_asgn" Async Queue complete

    Given I import "/plan/cns_clusters" by keyFields "countryId,sourceSystem,cluster,subCluster"
      | countryId | sourceSystem | cluster | subCluster         |
      | BR        | CONS_LATAM   | SOUTH   | South              |
      | VE        | CONS_LATAM   | ANDEAN  | Andean Sub Cluster |

    And I wait "/plan/cns_clusters" Async Queue complete

    # when cns_dem_grp_asgn-affiliateCountry <> country_V1-localCountry
    # or cns_dem_grp_asgn-sourceSystem <> country_V1-sourceSystem, then skip the record.
    Given I import "/edm/country_v1" by keyFields "localCountry,sourceSystem"
      | localCountry | sourceSystem | countryCode | countryName   | consumerPlanningRegion |
      | BR           | CONS_LATAM2  | BR4         | All Countries | 104                    |
      | BR           | CONS_LATAM   | BR          | Brazil        | 103                    |

    And I wait "/edm/country_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmCustomer.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMCustomer.tsv"

    Then I check file data for filename "GDMCustomer.tsv" by keyFields "customerId"
      | customerId | active | activeFCTERP | activeOPRERP | activeSOPERP | channel | channelDescription  | countryId | CUST_Cluster | distributionChannel | distributor | eCommerce | forecastSource | globalCustomerId | name                      | partner | partnerCountry | partnerName | partnerRegion | partnerRole | planningCustomerGroupID | regionId | salesOrganization | soldTo | sourceLocationId | subCluster |
      | 76100005   | YES    | YES          | NO           | NO           | CH003   | Pharmacy Wholesaler | BR        | SOUTH       |                     | NO          | NO        |                |                  | Pharmacy Wholesaler OTHER |         |                |             |               |             | 76100005                | 103      | BR01              |        |                  | South      |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

#    And I compare the number of records between "/plan/cns_dem_grp_asgn" and "/omp/gdm_customer,/plan/edm_failed_data"

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/plan/edm_failed_data"

    And I will remove the test file on sink application "GDMCustomer.tsv"


  @Scenario4
  Scenario: Get attributes from cns_cust_channel (J5)

    And I will remove the test file on sink application "GDMCustomer.tsv"

    Given I import "/plan/cns_plan_dem_grp" by keyFields "demandGroupId,sourceSystem"
      | demandGroupId | sourceSystem | demandGroupDesc           |
      | 76100001      | CONS_LATAM   | SCAPOL                    |
      | 76100005      | CONS_LATAM   | Pharmacy Wholesaler OTHER |

    And I wait "/plan/cns_plan_dem_grp" Async Queue complete

    # when cns_cust_channel-sourceSystem = cns_dem_grp_asgn-sourceSystem and
    # cns_cust_channel-salesOrg = cns_dem_grp_asgn-salesOrganization and
    # cns_cust_channel-channel = cns_dem_grp_asgn-channel, then get channelDesc value.
    Given I import "/plan/cns_cust_channel" by keyFields "sourceSystem,salesOrg,channel"
      | sourceSystem | salesOrg | channel | channelDesc          |
      | CONS_LATAM   | BR01     | CH003   | Pharmacy Wholesaler  |
      | CONS_LATAM   | BR01     | CH002   | Pharmacy Distributor |
      | CONS_LATAM   | BR02     | CH003   | Pharmacy/Drugstore   |
      | BTC          | BR01     | CH003   | Other Pharma         |

    And I wait "/plan/cns_cust_channel" Async Queue complete

    Given I import "/plan/cns_dem_grp_asgn" by keyFields "countryAffiliate,customerId"
      | sourceSystem | countryAffiliate | customerId | demandGroup | channel | channelDescription      | customerName                        | salesOrg |
      | CONS_LATAM   | BR               | 0000000323 | 76100001    | CH006   | Distributor Traditional | Distributor Traditional             | BR01     |
      | CONS_LATAM   | BR               | 0000000082 | 76100005    | CH003   | Pharmacy Wholesaler     | GRUPO DISTRIB.BRASIL MEDICAMENTOS L | BR01     |

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
      | 76100001   | YES    | YES          | NO           | NO           | CH006   |                     | BR        | SOUTH       |                     | NO          | NO        |                |                  | SCAPOL                    |         |                |             |               |             | 76100001                | 103      | BR01              |        |                  | South      |
      | 76100005   | YES    | YES          | NO           | NO           | CH003   | Pharmacy Wholesaler | BR        | SOUTH       |                     | NO          | NO        |                |                  | Pharmacy Wholesaler OTHER |         |                |             |               |             | 76100005                | 103      | BR01              |        |                  | South      |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

#    And I compare the number of records between "/plan/cns_dem_grp_asgn" and "/omp/gdm_customer,/plan/edm_failed_data"

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/plan/edm_failed_data"

    And I will remove the test file on sink application "GDMCustomer.tsv"


  @Scenario5
  Scenario: set constant value (D1,D2,D3)

    # D1: set 'YES' to active, activeFCTERP
    # D2: set 'NO' to activeOPRERP, activeSOPERP, distributor, e-Commerce
    # D3: set '' to distributionChannel, forecastSource, globalCustomerId, partner, partnerCountry, partnerName, partnerRegion, partnerRole, sourceLocationId
    And I will remove the test file on sink application "GDMCustomer.tsv"

    Given I import "/plan/cns_plan_dem_grp" by keyFields "demandGroupId,sourceSystem"
      | demandGroupId | sourceSystem | demandGroupDesc           |
      | 76100005      | CONS_LATAM   | Pharmacy Wholesaler OTHER |

    And I wait "/plan/cns_plan_dem_grp" Async Queue complete

    Given I import "/plan/cns_cust_channel" by keyFields "sourceSystem,salesOrg,channel"
      | sourceSystem | salesOrg | channel | channelDesc         |
      | CONS_LATAM   | BR01     | CH003   | Pharmacy Wholesaler |

    And I wait "/plan/cns_cust_channel" Async Queue complete

    Given I import "/plan/cns_dem_grp_asgn" by keyFields "countryAffiliate,customerId"
      | sourceSystem | countryAffiliate | customerId | demandGroup | channel | channelDescription  | customerName                        | salesOrg |
      | CONS_LATAM   | BR               | 0000000082 | 76100005    | CH003   | Pharmacy Wholesaler | GRUPO DISTRIB.BRASIL MEDICAMENTOS L | BR01     |

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
      | 76100005   | YES    | YES          | NO           | NO           | CH003   | Pharmacy Wholesaler | BR        | SOUTH       |                     | NO          | NO        |                |                  | Pharmacy Wholesaler OTHER |         |                |             |               |             | 76100005                | 103      | BR01              |        |                  | South      |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

#    And I compare the number of records between "/plan/cns_dem_grp_asgn" and "/omp/gdm_customer,/plan/edm_failed_data"

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/plan/edm_failed_data"

    And I will remove the test file on sink application "GDMCustomer.tsv"

