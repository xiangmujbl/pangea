@pangea_test
Feature:  PlanCnsProdCtyAffl-Curation

  Scenario: Full Load curation
    #1.get sourceSystem from source_system_v1 (rule T1)
    #2.get dpParentCode from material_global_v1 (rule C1)
    #3.get country from plant_V1 (rule T2)
    #4.get prodClassification from material_global_v1 (rule T3)
    #5.get ovrProdClass by rule C2
    #6.get prodStatus by rule T4
    #7.get ovrProdStat by rule C3

    Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | sourceSystem     | localMaterialNumber | localMaterialType |
      | Project_One      | AR01                | FERT              |
      | [Consumer LATAM] | AR02                | FERT              |

    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/plan/cns_material_plan_status" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | dpRelevant |
      | CONS_LATAM   | BR01                | BR01       | X          |
      | CONS_LATAM   | BR02                | BR01       |            |
      | CONS_LATAM   | NP                  | BR03       |            |
      | CONS_LATAM   | NQ                  | BR04       |            |

    And I wait "/plan/cns_material_plan_status" Async Queue complete

    And I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | localSourceSystemName | sourceSystem | sourceSystemName   |
      | Project_One       | Project One           | CON_LATAM    | Consumer Latam Ent |
      | [Consumer LATAM]  | Consumer Latam        | CON_LATAM    | Consumer Latam Ent |
      | [EMS]             | EMS                   | EMS          | EMS Ent            |
    And I wait "/edm/source_system_v1" Async Queue complete

    Given I import "/edm/plant_v1" by keyFields "sourceSystem,localPlant"
      | sourceSystem | localPlant | country |
      | CONS_LATAM   | AR01       | C1      |
      | CONS_LATAM   | AR02       | C2      |

    And I wait "/edm/plant_v1" Async Queue complete

    When I submit task with xml file "xml/plan/PlanCnsProdCtyAffl.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/plan/cns_prod_cty_affl" by keyFields "sourceSystem,dpParentCode,country"
      | sourceSystem | dpParentCode | country | prodClassification | ovrProdClass | prodStatus | ovrProdStat | dpSegmentation | dpPlannerId | rootSize | countryGrp |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/edm/material_global_v1" and "/plan/cns_prod_cty_affl,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/plan/cns_prod_cty_affl"

    And I will remove all data with region "/edm/material_global_v1"
    And I will remove all data with region "/plan/edm_failed_data"

