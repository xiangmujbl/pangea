@pangea_test @AEAZ-4069
Feature: OMPGdmCustomer AEAZ-4069

  Scenario: Full Load curation
    # 1. When cns_dem_grp_asgn-affiliateCountry <> cns_clusters-countryID then skip that record (J1,J3)
    # 2. When cns_dem_grp_asgn-sourceSystem <> cns_clusters-sourceSystem then skip that record (J1,J3)
    # 3. When cns_dem_grp_asgn-affiliateCountry <> country_V1-localCountry then skip that record (J2)
    # 4. When cns_dem_grp_asgn-sourceSystem <> country_V1-sourceSystem then skip that record (J2)

    And I will remove the test file on sink application "GDMCustomer.tsv"

    Given I import "/plan/cns_dem_grp_asgn" by keyFields "countryAffiliate,customerId"
      | sourceSystem | countryAffiliate | customerId | demandGroup | channel | channelDescription      | customerName                        | salesOrganization |
      | CONS_LATAM   | BR1J1J3          | 323        | 76100001    | CH006   | Distributor Traditional | Distributor Traditional             | BR01              |
      | CONS_LATAM   | BR2J1J3          | 8289       | 76100002    | CH008   | Modern Trade            | Clubs/Cash & Carry                  | BR01              |
      | CONS_LATAM   | BR3J2            | 5          | 76100003    | CH005   | Clubs/Cash & Carry      | Modern Trade                        | BR01              |
      | CONS_LATAM   | BR4J2            | 9290       | 76100004    | CH008   | Clubs/Cash & Carry      | Clubs/Cash & Carry                  | BR01              |
      | CONS_LATAM   | BR               | 82         | 76100005    | CH003   | Pharmacy Wholesaler     | GRUPO DISTRIB.BRASIL MEDICAMENTOS L | BR01              |

    And I wait "/plan/cns_dem_grp_asgn" Async Queue complete

    Given I import "/plan/cns_clusters" by keyFields "countryId,sourceSystem,cluster,subCluster"
      | countryId | sourceSystem | cluster | subCluster |
      | BR        | CONS_LATAM   | SOUTH   | South      |
      | BR2J1J3   | CONS_LATAM2  | BRAZIL  | Brazil     |
      | BR3J2     | CONS_LATAM   | MEXCAM  | MEX        |
      | BR4J2     | CONS_LATAM   | ANDEAN  | Andean     |

    And I wait "/plan/cns_clusters" Async Queue complete

    Given I import "/edm/country_v1" by keyFields "localCountry,sourceSystem"
      | localCountry | sourceSystem | countryCode | countryName   | consumerPlanningRegion |
      | BR4J2        | CONS_LATAM4  | BR4         | All Countries | 104                    |
      | BR           | CONS_LATAM   | BR          | Brazil        | 103                    |

    And I wait "/edm/country_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmCustomer.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMCustomer.tsv"

    Then I check file data for filename "GDMCustomer.tsv" by keyFields "customerId"
      | customerId | active | activeFCTERP | activeOPRERP | activeSOPERP | channel | channelDescription  | countryId | custCluster | distributionChannel | distributor | eCommerce | forecastSource | globalCustomerId | name                                | partner | partnerCountry | partnerName | partnerRegion | partnerRole | planningCustomerGroupID | regionId | salesOrganization | soldTo | sourceLocationId | subCluster |
      | 76100005   | YES    | YES          | NO           | NO           | CH003   | Pharmacy Wholesaler | BR        | SOUTH       |                     | NO          | NO        |                |                  | GRUPO DISTRIB.BRASIL MEDICAMENTOS L |         |                |             |               |             |                         | 103      | BR01              |        |                  | South      |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

#    And I compare the number of records between "/plan/cns_dem_grp_asgn" and "/omp/gdm_customer,/plan/edm_failed_data"

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/plan/edm_failed_data"
