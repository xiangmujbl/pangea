@pangea_test @AEAZ-6272
Feature: PlanCnsProdCtyAffl-Curation AEAZ-6272

  Scenario: Full Load curation
    #1.get sourceSystem from source_system_v1 (rule T1)
    #2.get dpParentCode from material_global_v1 (rule C1)
    #3.get country from plant_V1 (rule T2)
    #4.get prodClassification from material_global_v1 (rule T3)
    #5.get ovrProdClass by rule C2
    #6.get prodStatus by rule T4
    #7.get ovrProdStat by rule C3

    Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | sourceSystem | localMaterialNumber | localMaterialType | localDpParentCode |
      | project_one  | BR01                | FERT              | 7320133000740000  |
      | project_one  | BR02                | SAPR              | 975760150         |
      | project_one  | NP                  | FERT              | 78910106115210000 |
    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/plan/cns_material_plan_status" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | dpRelevant | localParentCode  |
      | project_one  | BR01                | BR12       | X          | 7320133000740000 |
      | project_one  | BR02                | BR12       | X          | 975760150        |
      | project_one  | NP                  | BR12       |            | 7891010611521000 |
    And I wait "/plan/cns_material_plan_status" Async Queue complete

    And I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | localSourceSystemName | sourceSystem | sourceSystemName   |
      | project_one       | Project One           | CONS_LATAM   | Consumer Latam Ent |
      | project_two       | Project Two           | CONS_LATAM   | Consumer Latam Ent |
    And I wait "/edm/source_system_v1" Async Queue complete

    Given I import "/edm/plant_v1" by keyFields "sourceSystem,localPlant"
      | sourceSystem | localPlant | country |
      | project_one  | BR12       | BR      |
      | project_two  | BR12       | BR      |
    And I wait "/edm/plant_v1" Async Queue complete

    Given I import "/plan/cns_prod_cty_affl_input" by keyFields "sourceSystem,dpParentCode,country"
      | sourceSystem | dpParentCode     | country | prodClassification | ovrProdClass | ovrProdStat | dpSegmentation | dpPlannerId | rootSize | countryGrp |
      | CONS_LATAM   | 7320133000740000 | BR      | REGULAR            | DSP          | CE          | DL,CP          | DR19        | 18       | PAL        |
    And I wait "/plan/cns_prod_cty_affl_input" Async Queue complete

    When I submit task with xml file "xml/plan/PlanCnsProdCtyAffl.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/plan/cns_prod_cty_affl" by keyFields "sourceSystem,dpParentCode,country"
      | sourceSystem | dpParentCode     | country | prodClassification | ovrProdClass | prodStatus | ovrProdStat | dpSegmentation | dpPlannerId | rootSize | countryGrp |
      | CONS_LATAM   | 7320133000740000 | BR      | REGULAR            | DSP          | ACTIVE     | CE          | DL,CP          | DR19        | 18       | PAL        |
      | CONS_LATAM   | 975760150        | BR      | SAMPLE             |              | ACTIVE     |             |                |             |          |            |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/plan/cns_prod_cty_affl"

    And I will remove all data with region "/plan/edm_failed_data"

