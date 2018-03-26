@pangea_test @AEAZ-1480
Feature: PlanCnsPlantAttr-Curation

  Scenario: Full Load curation
    # 1. test the filter localPlanningRelevant='Y'

    Given I import "/edm/plant_v1" by keyFields "sourceSystem,localPlant"
      | sourceSystem     | localPlant | localPlantName | localPlantType | plant | plantType | localPlanningRelevant |
      | [Consumer LATAM] | BR02       | A              | 00             |       | 00        | Y                     |
      | [Consumer LATAM] | BR06       | B              | 01             |       | 01        | N                     |

    And I wait "/edm/plant_v1" Async Queue complete

    When I submit task with xml file "xml/plan/PlanCnsPlantAttr.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/plan/cns_plant_attr" by keyFields "sourceSystem,localPlant"
      | sourceSystem     | localPlant | localPlantName | localPlantType | plant | plantType | localPlanningRelevant |
      | [Consumer LATAM] | BR02       | A              | 00             |       | 00        | Y                     |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID      | errorCode | sourceSystem | businessArea | key1             | key2 | key3 | key4 | key5 | errorValue |
      | SP             | PlanCnsPlantAttr | FILTER    | edm          |              | [Consumer LATAM] | BR06 |      |      |      |            |
    And I compare the number of records between "/edm/plant_v1" and "/plan/cns_plant_attr,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/plan/cns_plant_attr"
    And I will remove all data with region "/plan/edm_failed_data"

