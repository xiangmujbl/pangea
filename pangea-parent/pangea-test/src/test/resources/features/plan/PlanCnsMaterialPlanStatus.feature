@pangea_test @AEAZ-1692
Feature: CnsMaterialPlanStatus AEAZ-1692

  Scenario: Full Load curation

    Given I import "/edm/material_plant_v1" by keyFields "localMaterialNumber,localPlant"
      | localMaterialNumber | localPlant | materialNumber | localMrpType |
      | 000000000000203700  | BR12       | -              | PD           |
      | 000000000000213997  | BR12       | -              | PD           |
      | 000000000000213998  | BR12       | -              | PD           |

    And I wait "/edm/material_plant_v1" Async Queue complete|

    And I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | localSourceSystemName | sourceSystem | sourceSystemName   |
      | Project_One       | Project One           | CONS_LATAM   | Consumer Latam Ent |
      | project_two       | Project Two           | CONS_LATAM   | Consumer Latam Ent |
    And I wait "/edm/source_system_v1" Async Queue complete

    And I import "/edm/material_global_v1" by keyFields "localMaterialNumber"
      | localMaterialType | localMaterialNumber | localDpParentCode | primaryPlanningCode |
      | ROH               | 000000000000203700  |                   | -                   |
      | HALB              | 000000000000213997  |                   | -                   |
      | HALB              | 000000000000213998  |                   |                     |
    And I wait "/edm/material_global_v1" Async Queue complete

    And I import "/plan/cns_plan_parameter" by keyFields "sourceSystem,dataObject,attribute,parameter"
      | sourceSystem | dataObject               | attribute  | parameter    | inclExcl | parameterValue |
      | CONS_LATAM   | cns_material_plan_status | DPRelevant | Plant        | I        | BR12           |
      | CONS_LATAM   | cns_material_plan_status | SPRelevant | Plant        | I        | BR12           |
      | CONS_LATAM   | cns_material_plan_status | DPRelevant | MRPType      | I        | PD             |
      | CONS_LATAM   | cns_material_plan_status | SPRelevant | MRPType      | I        | PD             |
      | CONS_LATAM   | cns_material_plan_status | DPRelevant | MaterialType | I        | ROH            |
      | CONS_LATAM   | cns_material_plan_status | SPRelevant | MaterialType | I        | HALB           |

    And I wait "/plan/cns_plan_parameter" Async Queue complete

    And I import "/plan/cns_material_incl" by keyFields "localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | planningType | localPlant |
      | CONS_LATAM   | 000000000000087759  | SP           | BR12       |
      | CONS_LATAM   | 000000000000087764  | NP           | BR12       |
      | CONS_LATAM   | 000000000000087765  | NP           | BR12       |

    And I wait "/plan/cns_material_incl" Async Queue complete

    When I submit task with xml file "xml/plan/PlanCnsMaterialPlanStatus.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/plan/cns_material_plan_status" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | materialNumber | localParentCode | ppc | active | dpRelevant | spRelevant | parentActive | noPlanRelevant |
      | CONS_LATAM   | 000000000000203700  | BR12       | -              |                 |     | X      |            | X          |              |                |
      | CONS_LATAM   | 000000000000213997  | BR12       | -              |                 |     | X      |            | X          |              |                |
      | CONS_LATAM   | 000000000000213998  | BR12       | -              |                 |     | X      |            | X          |              |                |
      | CONS_LATAM   | 000000000000087759  | BR12       |                |                 |     |        |            |            |              |                |
      | CONS_LATAM   | 000000000000087764  | BR12       |                |                 |     | X      |            |            |              | X              |
      | CONS_LATAM   | 000000000000087765  | BR12       |                |                 |     | X      |            |            |              | X              |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/edm/material_plant_v1,/plan/cns_material_incl" and "/plan/cns_material_plan_status,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/plan/cns_material_plan_status"
    And I will remove all data with region "/plan/edm_failed_data"