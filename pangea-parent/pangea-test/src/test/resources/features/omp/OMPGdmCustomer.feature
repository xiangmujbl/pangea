@pangea_test @AEAZ-2710
Feature: OMPGdmCustomer AEAZ-2710

  Scenario: Full Load curation
    # 1. get attributes from cns_clusters if no record found then skip that record (J1)
    # 2. get attributes from country_v1 if no record found then skip that record (J2)

    Given I import "/plan/cns_dem_grp_asgn" by keyFields "countryAffiliate,customerId"
      | countryAffiliate | customerId | demandGroup | channel | channelDescription      | customerName                        |
      | BR               | 323        | 76100001    | CH006   | Distributor Traditional | GRUPO SCAPOL COM DIST.PRODS.HIG.LTD |
      | CH               | 5          | 76100003    | CH005   | Modern Trade            | GRUPO WM VAREJO                     |
      | CO               | 9290       | 76100004    | CH008   | Clubs/Cash & Carry      | GRUPO WMSE ATACADO SAMS             |

    And I wait "/plan/cns_dem_grp_asgn" Async Queue complete

    Given I import "/plan/cns_clusters" by keyFields "countryId,cluster,subCluster"
      | countryId | cluster | subCluster |
      | BR        | BRAZIL  | Brazil     |
      | CO        | ANDEAN  | Andean     |

    And I wait "/plan/cns_clusters" Async Queue complete

    Given I import "/edm/country_v1" by keyFields "localCountry,sourceSystem"
      | localCountry | sourceSystem | countryCode | countryName   | consumerPlanningRegion |
      | BR           | project_one  | -           | All Countries | BR1                    |
      | CH           | project_one  | -           | All Countries | CH1                    |

    And I wait "/edm/country_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmCustomer.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMCustomer.tsv"

    Then I check file data for filename "GDMCustomer.tsv" by keyFields "customerId"
      | customerId | active | activeFCTERP | activeOPRERP | activeSOPERP | aggrSoldTo | channel | channelDescription      | countryId | custCluster | distributionChannel | distributor | division | eCommerce | globalCustomerId | name                                | partner | partnerCountry | partnerName | partnerRegion | partnerRole | planningCustomerGroupId | regionId | salesOrganization | soldTo | sourceLocationId | subCluster | ucn |
      | 76100001   | YES    | YES          | NO           | NO           |            | CH006   | Distributor Traditional | BR        | BR          |                     | NO          |          | NO        |                  | GRUPO SCAPOL COM DIST.PRODS.HIG.LTD |         |                |             |               |             |                         | BR1      | BRAZIL            |        |                  | Brazil     | 0   |

    Then I check region data "/omp/gdm_customer" by keyFields "customerId"
      | customerId | active | activeFCTERP | activeOPRERP | activeSOPERP | aggrSoldTo | channel | channelDescription      | countryId | custCluster | distributionChannel | distributor | division | eCommerce | globalCustomerId | name                                | partner | partnerCountry | partnerName | partnerRegion | partnerRole | planningCustomerGroupId | regionId | salesOrganization | soldTo | sourceLocationId | subCluster | ucn |
      | 76100001   | YES    | YES          | NO           | NO           |            | CH006   | Distributor Traditional | BR        | BR          |                     | NO          |          | NO        |                  | GRUPO SCAPOL COM DIST.PRODS.HIG.LTD |         |                |             |               |             |                         | BR1      | BRAZIL            |        | 	                | Brazil     | 0   |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

#    And I compare the number of records between "/plan/cns_dem_grp_asgn" and "/omp/gdm_customer,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/omp/gdm_customer"

    And I will remove all data with region "/plan/edm_failed_data"

    And I will remove the test file on sink application "GDMCustomer.tsv"

