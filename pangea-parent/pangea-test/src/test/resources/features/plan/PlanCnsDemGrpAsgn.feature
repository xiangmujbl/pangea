@pangea_test
Feature: PlanCnsDemGrpAsgn-Curation

  Scenario: Full Load curation

    Given I import "/ems/customer_group" by keyFields "sourceSystem,customerShipTo"
      | sourceSystem | customerShipTo | subFranchise | planningCustomerGroup | fromDate | toDate |
      | CONS_LATAM   | ST1            | SF1          | CG1                   | DF1      | TD1    |
      | CONS_LATAM   | ST2            | SF2          | CG2                   | DF2      | TD2    |
      | CONS_LATAM   | ST3            | SF3          | CG3                   | DF3      | TD3    |
    And I wait "/ems/customer_group" Async Queue complete

    When I submit task with xml file "xml/plan/PlanCnsDemGrpAsgn.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/plan/cns_dem_grp_asgn" by keyFields "sourceSystem,customerShipTo,subFranchise"
      | sourceSystem | customerShipTo | subFranchise | group | fromDate | toDate |
      | CONS_LATAM   | ST1            | SF1          | CG1   | DF1      | TD1    |
      | CONS_LATAM   | ST2            | SF2          | CG2   | DF2      | TD2    |
      | CONS_LATAM   | ST3            | SF3          | CG3   | DF3      | TD3    |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/ems/customer_group" and "/plan/cns_dem_grp_asgn,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/plan/cns_dem_grp_asgn"

    And I will remove all data with region "/plan/edm_failed_data"

