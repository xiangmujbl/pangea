@pangea_test @AEAZ-3682
Feature: OMPGdmSubCluster-Curation
  # Enter feature description here

  Scenario: Full Load curation AEAZ-3682

    Given I import "/plan/cns_clusters" by keyFields "sourceSystem,countryId,cluster,subCluster,clusterDesc,subClusterDesc"
      | sourceSystem | countryId | cluster | subCluster | clusterDesc                | subClusterDesc     |
      | CONS_LATAM   | CO        | ANDEAN  | Andean     | Andean Cluster             | Andean Sub Cluster |
      | CONS_LATAM   | EC        | ANDEAN  | Andean     | Andean Cluster             | Andean Sub Cluster |
      | CONS_LATAM   | PE        | ANDEAN  | Andean     | Andean Cluster             | Andean Sub Cluster |
      | CONS_LATAM   | VE        | ANDEAN  | Andean     | Andean Cluster             | Andean Sub Cluster |
      | CONS_LATAM   | BR        | BRAZIL  | Brazil     | Brazil                     | Brazil             |
      | CONS_LATAM   | CR        | MEXCAM  | CAM        | Mexico and Central America | Central America    |
      | CONS_LATAM   | ME        | MEXCAM  | MEX        | Mexico and Central America | Mexico             |
      | CONS_LATAM   | PA        | MEXCAM  | CAM        | Mexico and Central America | Central America    |
    And I wait "/plan/cns_clusters" Async Queue complete

    Given I import "/plan/cns_plan_parameter" by keyFields "sourceSystem"
      | sourceSystem |
      | CONS_LATAM   |
    And I wait "/plan/cns_clusters" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmSubCluster.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMSubCluster.tsv"

    Then I check file data for filename "GDMSubCluster.tsv" by keyFields "subClusterId,clusterId"
      | subClusterId        | description        | clusterId |
      | Andean              | Andean Sub Cluster | ANDEAN    |
      | Brazil              | Brazil             | BRAZIL    |
      | CAM                 | Central America    | MEXCAM    |
      | MEX                 | Mexico             | MEXCAM    |

#    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
#      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I delete the test data

    And I will remove all data with region "/omp/gdm_subcluster"

    And I will remove all data with region "/plan/edm_failed_data"

    And I will remove the test file on sink application "GDMSubCluster.tsv"