@pangea_test
Feature: CnsMaterialPlanStatus

  Scenario: Full Load curation

    Given I import "/edm/material_plant_v1" by keyFields "localMaterialNumber,localPlant"
      | localMaterialNumber | localPlant | materialNumber |localDeletionFlagPlant|localMrpType|localMaterialType|
      | 000000000000000001  | BR02       | 9862           |  PV                  | PV         |  F1             |
      | 000000000000000002  | BR02       | 9864           |  PV                  | PV         |  F2             |
    And I wait "/edm/material_plant_v1" Async Queue complete

    And I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | localSourceSystemName | sourceSystem | sourceSystemName   |
      | project_one       | Project One           | CONS_LATAM   | Consumer Latam Ent |
      | project_two       | Project Two           | CONS_LATAM   | Consumer Latam Ent |
    And I wait "/edm/source_system_v1" Async Queue complete

    And I import "/edm/material_global_v1" by keyFields "localMaterialNumber"
      | localMaterialType | localMaterialNumber | localDpParentCode|primaryPlanningCode|
      |         PV        | 000000000000000001  |    G3a           |       G4a         |
      |         PV        | 000000000000000002  |    G3b           |       G4b         |
    And I wait "/edm/material_global_v1" Async Queue complete

    And I import "/plan/cns_plan_parameter" by keyFields "attribute,dataObject,parameter,sourceSystem"
      | sourceSystem | dataObject | attribute | parameter | inclExcl |parameterValue|
      | CONS_LATAM   |cns_material_plan_status| DPRelevant|Plant| I | PV     |
      | CONS_LATAM   |cns_material_plan_status| SPRelevant|Plant| I | PV     |
      | CONS_LATAM   |cns_material_plan_status| DPRelevant|MRPType| I | PV     |
      | CONS_LATAM   |cns_material_plan_status| DPRelevant|MRPType| I | PV     |

    And I wait "/plan/cns_plan_parameter" Async Queue complete

    And I import "/plan/cns_material_incl" by keyFields "localMaterialNumber"
      | localMaterialNumber | planningType |
      | NP                  |NP            |
      | NQ                  |NP            |

    And I wait "/plan/cns_material_incl" Async Queue complete

    When I submit task with xml file "xml/pangea/CnsMaterialPlanStatus.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/pangea/cns_material_plan_status" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | materialNumber | localParentCode | ppc | active | dpRelevant | spRelevant | parentActive | noPlanRelevant |
      |  CONS_LATAM  |000000000000000001   |     BR02   |   9862         |   G3a           | G4a |  X     |   X        |            |              |        X       |
      |  CONS_LATAM  |000000000000000002   | BR02       |  9864          |   G3b           |  G4a|  X     |   X        |            |              |        X       |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/edm/material_plant_v1" and "/pangea/cns_material_plan_status,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/pangea/cns_material_plan_status"
    And I will remove all data with region "/plan/edm_failed_data"