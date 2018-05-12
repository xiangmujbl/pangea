@pangea_test @AEAZ-3679
Feature: OMPGdmCluster AEAZ-3679

  Scenario: Full Load curation
    #1. Send one record for each unique combination of cns_clusters-sourceSystem AND cns_clusters-cluster (rule C1)
    #2. Skip if cns_clusters-sourceSystem AND cns_clusters-cluster sent once (rule C1)
    #3. Skip if  cns_clusters-sourceSystem <> cns_plan_parameter-sourceSystem (rule C1)

    Given I import "/plan/cns_clusters" by keyFields "sourceSystem,countryId,cluster,subCluster,clusterDesc,subClusterDesc"
      | sourceSystem | countryId | cluster | subCluster | clusterDesc                | subClusterDesc     |
      | CONS_LATAM   | CO        | ANDEAN  | Andean     | Andean Cluster             | Andean Sub Cluster |
      | CONS_LATAM   | EC        | ANDEAN  | Andean     | Andean Cluster             | Andean Sub Cluster |
      | CONS_LATAM   | PE        | ANDEAN  | Andean     | Andean Cluster             | Andean Sub Cluster |
      | CONS_LATAM   | VE        | ANDEAN  | Andean     | Andean Cluster             | Andean Sub Cluster |
      | CONS_LATAM   | BR        | BRAZIL  | Brazil     | Brazil                     | Brazil             |
      | BTC          | CR        | MEXCAM  | CAM        | Mexico and Central America | Central America    |

    And I wait "/plan/cns_clusters" Async Queue complete

    Given I import "/plan/cns_plan_parameter" by keyFields "sourceSystem"
      | sourceSystem |
      | CONS_LATAM   |

    And I wait "/plan/cns_clusters" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmCluster.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMCluster.tsv"

    Then I check file data for filename "GDMCluster.tsv" by keyFields "clusterId"
      | clusterId        | activeFCTERP | clusterDescription |
      | CONS_LATAMANDEAN | YES          | Andean Cluster     |
      | CONS_LATAMBRAZIL | YES          | Brazil             |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I delete the test data

    And I will remove all data with region "/plan/edm_failed_data"

    And I will remove the test file on sink application "GDMCluster.tsv"

