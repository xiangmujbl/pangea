@pangea_test
Feature: CnsMaterialPlanStatus

  Scenario: Full Load curation

    Given I import "/edm/material_plant_v1" by keyFields "localMaterialNumber,localPlant"
      | localMaterialNumber | localPlant | materialNumber | localDeletionFlagPlant | localMrpType | localMaterialType |
      | BR01                | BR01       | 9862           | PV                     | PV           | F1                |
      | BR02                | BR01       | 9864           | PV                     | PV           | F2                |
    And I wait "/edm/material_plant_v1" Async Queue complete

    And I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | localSourceSystemName | sourceSystem | sourceSystemName   |
      | project_one       | Project One           | CONS_LATAM   | Consumer Latam Ent |
      | project_two       | Project Two           | CONS_LATAM   | Consumer Latam Ent |
    And I wait "/edm/source_system_v1" Async Queue complete

    And I import "/edm/material_global_v1" by keyFields "localMaterialNumber"
      | localMaterialType | localMaterialNumber | localDpParentCode | primaryPlanningCode |
      | PV5               | BR01                | G3a               | G4a                 |
      | PV6               | BR02                | G3b               | G4b                 |
    And I wait "/edm/material_global_v1" Async Queue complete

    And I import "/plan/cns_plan_parameter" by keyFields "sourceSystem,dataObject,attribute,parameter"
      | sourceSystem | dataObject               | attribute  | parameter    | inclExcl | parameterValue |
      | CONS_LATAM   | cns_material_plan_status | DPRelevant | Plant        | I        | BR01           |
      | CONS_LATAM   | cns_material_plan_status | SPRelevant | Plant        | I        | BR01           |
      | CONS_LATAM   | cns_material_plan_status | DPRelevant | MRPType      | I        | PV             |
      | CONS_LATAM   | cns_material_plan_status | SPRelevant | MRPType      | I        | PV             |
      | CONS_LATAM   | cns_material_plan_status | DPRelevant | MaterialType | I        | PV5            |
      | CONS_LATAM   | cns_material_plan_status | SPRelevant | MaterialType | I        | PV6            |

    And I wait "/plan/cns_plan_parameter" Async Queue complete

    And I import "/plan/cns_material_incl" by keyFields "localMaterialNumber"
      | sourceSystem | localMaterialNumber | planningType | localPlant |
      | CONS_LATAM   | NP                  | NP           | BR03       |
      | CONS_LATAM   | NQ                  | NP           | BR04       |

    And I wait "/plan/cns_material_incl" Async Queue complete

    When I submit task with xml file "xml/pangea/CnsMaterialPlanStatus.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/pangea/cns_material_plan_status" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | materialNumber | localParentCode | ppc | active | dpRelevant | spRelevant | parentActive | noPlanRelevant |
      | CONS_LATAM   | BR01                | BR01       | 9862           | G3a             | G4a | X      | X          |            | X            | X              |
      | CONS_LATAM   | BR02                | BR01       | 9864           | G3b             | G4b | X      | X          |            | X            | X              |
      | CONS_LATAM   | NP                  | BR03       |                |                 |     |        |            |            |              |                |
      | CONS_LATAM   | NQ                  | BR04       |                |                 |     |        |            |            |              |                |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/edm/material_plant_v1,/plan/cns_material_incl" and "/pangea/cns_material_plan_status,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/pangea/cns_material_plan_status"
    And I will remove all data with region "/plan/edm_failed_data"