@pangea_test @AEAZ-1613
Feature: OMPGdmCluster AEAZ-1613

  Scenario: Full Load curation

    Given I import "/plan/cns_clusters" by keyFields "cluster,countryId,subCluster"
      | cluster | countryId | subCluster |
      |SOUTH    |     AR      |      South      |
      |         |      AR       |        South         |
    And I wait "/plan/cns_clusters" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmCluster.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/omp/gdm_cluster" by keyFields "clusterId"
      | clusterId |  activeFCTERP |  clusterDescription |  clusterNr |  countryId |  subFranchise |
      |ARSOUTHSouth|YES           |SOUTH                |SOUTH       |      AR      |South        |
    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |
      |         DP       |OMPGdmCluster|     C1    |omp         |              |      |AR    |South |      |      |    All Key fields not Exist        |
    And I compare the number of records between "/plan/cns_clusters" and "/omp/gdm_cluster,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/omp/gdm_cluster"

    And I will remove all data with region "/plan/edm_failed_data"

