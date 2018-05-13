@pangea_test @AEAZ-3216
Feature: CnsMaterialPlanStatus AEAZ-3216

  Scenario: Full Load curation
    And I will remove the test file on sink application "PlanCnsMaterialPlanStatus_1.tsv"
#    Given I import "/edm/material_plant_v1" by keyFields "localMaterialNumber,localPlant"
#      | localMaterialNumber   | localPlant | materialNumber | localMrpType | localPlantStatus | localMrpController |
#      | 000000000000203700_F1 | BR12       | -              | PD           |                  | 999                |
#      | 000000000000203701_F1 | BR12       | -              | PD           |                  | 999                |
#      | 000000000000203702_F1 | BR12       | -              | PD           |                  | 999                |
#      | 000000000000203703_F1 | BR12       | -              | PD           |                  | 999                |
#      | 000000000000203704_F2 | BR08       | -              | PD           | 9                | 999                |
#      | 000000000000203705_F2 | BR12       | -              | ND           | 9                | 999                |
#      | 000000000000203706_F2 | BR12       | -              | PD           | 8                | 999                |
#      | 000000000000203707_F2 | BR12       | -              | PD           | 9                | 222                |
#      | 000000000000203708_F3 | BR08       | -              | PD           | 11               | 111                |
#      | 000000000000203709_F3 | BR12       | -              | ND           | 11               | 111                |
#      | 000000000000203710_F3 | BR12       | -              | PD           | 8                | 111                |
#      | 000000000000203711_F3 | BR12       | -              | PD           | 11               | 222                |
#      | 000000000000203712_F3 | BR12       | -              | PD           | 11               | 111                |
#      | 000000000000214001_T1 | BR12       | -              | PD           | 9                | 999                |
#      | 000000000000214002_T2 | BR12       | -              | PD           | 9                | 999                |
#      | 000000000000214003_T3 | BR12       | -              | PD           | 9                | 999                |
#      | 000000000000214004_T4 | BR12       | -              | PD           | 9                | 999                |
#      | 000000000000214005_T5 | BR12       | -              | PD           | 9                | 999                |
#      | 000000000000214006_T6 | BR12       | -              | PD           | 9                | 999                |
#
#    And I wait "/edm/material_plant_v1" Async Queue complete|
#
#    And I import "/edm/source_system_v1" by keyFields "localSourceSystem"
#      | localSourceSystem | localSourceSystemName | sourceSystem | sourceSystemName   |
#      | Project_One       | Project One           | CONS_LATAM   | Consumer Latam Ent |
#      | project_two       | Project Two           | CONS_LATAM   | Consumer Latam Ent |
#    And I wait "/edm/source_system_v1" Async Queue complete
#
#    And I import "/edm/material_global_v1" by keyFields "localMaterialNumber"
#      | localMaterialType | localMaterialNumber   | localDpParentCode  | primaryPlanningCode | division | flagForDeletion |
#      | HAWA              | 000000000000203700_F1 | 178962124094540036 | 4007                | 10       |                 |
#      | FERT              | 000000000000203701_F1 | 178962124094540036 | 4007                | 10       |                 |
#      | SAPR              | 000000000000203702_F1 | 178962124094540036 | 4007                | 5        |                 |
#      | SAPR              | 000000000000203703_F1 | 178962124094540036 | 4007                | 20       |                 |
#      | SAPR              | 000000000000203704_F2 | 178962124094540036 | 4008                | 10       |                 |
#      | SAPR              | 000000000000203705_F2 | 178962124094540036 | 4008                | 10       |                 |
#      | SAPR              | 000000000000203706_F2 | 178962124094540036 | 4008                | 10       |                 |
#      | SAPR              | 000000000000203707_F2 | 178962124094540036 | 4008                | 10       |                 |
#      | SAPR              | 000000000000203708_F3 | 178962124094540036 | 4000                | 10       |                 |
#      | SAPR              | 000000000000203709_F3 | 178962124094540036 | 4000                | 10       |                 |
#      | SAPR              | 000000000000203710_F3 | 178962124094540036 | 4000                | 10       |                 |
#      | SAPR              | 000000000000203711_F3 | 178962124094540036 | 4000                | 10       |                 |
#      | SAPR              | 000000000000203712_F3 | 178962124094540036 | 4000                | 10       |                 |
#      | SAPR              | 000000000000214001_T1 | 178910100400070072 | 4001                | 10       |                 |
#      | SAPR              | 000000000000214002_T2 | 178910100400070072 | 4002                | 10       |                 |
#      | SAPR              | 000000000000214003_T3 | 178910100400070072 | 4003                | 10       |                 |
#      | SAPR              | 000000000000214004_T4 | 178910100400070072 | 4004                | 10       |                 |
#      | SAPR              | 000000000000214005_T5 | 178910100400070072 | 4005                | 10       |                 |
#      | SAPR              | 000000000000214006_T6 | 178910100400070072 | 4006                | 10       |                 |
#    And I wait "/edm/material_global_v1" Async Queue complete
#
#    And I import "/plan/cns_plan_parameter" by keyFields "sourceSystem,dataObject,attribute,parameter"
#      | sourceSystem | dataObject               | attribute  | parameter     | inclExcl | parameterValue |
#      | CONS_LATAM   | cns_material_plan_status | DPRelevant | MaterialType  | I        | SAPR           |
#      | CONS_LATAM   | cns_material_plan_status | SPRelevant | MaterialType  | E        | FERT           |
#      | CONS_LATAM   | cns_material_plan_status | DPRelevant | Division      | I        | 10             |
#      | CONS_LATAM   | cns_material_plan_status | SPRelevant | Division      | E        | 20             |
#      | CONS_LATAM   | cns_material_plan_status | DPRelevant | Plant         | I        | BR12           |
#      | CONS_LATAM   | cns_material_plan_status | SPRelevant | Plant         | I        | BR12           |
#      | CONS_LATAM   | cns_material_plan_status | DPRelevant | MRPType       | E        | ND             |
#      | CONS_LATAM   | cns_material_plan_status | SPRelevant | MRPType       | I        | PD             |
#      | CONS_LATAM   | cns_material_plan_status | DPRelevant | PSMSStatus    | I        | 9              |
#      | CONS_LATAM   | cns_material_plan_status | SPRelevant | PSMSStatus    | I        | 11             |
#      | CONS_LATAM   | cns_material_plan_status | DPRelevant | MrpController | I        | 999            |
#      | CONS_LATAM   | cns_material_plan_status | SPRelevant | MrpController | I        | 111            |
#
#    And I wait "/plan/cns_plan_parameter" Async Queue complete
#
#    And I import "/plan/cns_material_incl" by keyFields "localMaterialNumber,localPlant"
#      | sourceSystem | localMaterialNumber   | planningType | localPlant | inclusionType |
#      | CONS_LATAM   | 000000000000203700_F1 | SP           | BR12       | Critical-ROH  |
#      | CONS_LATAM   | 000000000000203701_F1 | SP           | BR12       | Critical-ROH  |
#      | CONS_LATAM   | 000000000000203702_F1 | SP           | BR12       | Critical-ROH  |
#      | CONS_LATAM   | 000000000000203703_F1 | SP           | BR12       | Critical-ROH  |
#      | CONS_LATAM   | 000000000000203704_F2 | SP           | BR12       | Critical-ROH  |
#      | CONS_LATAM   | 000000000000203705_F2 | SP           | BR12       | Critical-ROH  |
#      | CONS_LATAM   | 000000000000203706_F2 | SP           | BR12       | Critical-ROH  |
#      | CONS_LATAM   | 000000000000203707_F2 | SP           | BR12       | Critical-ROH  |
#      | CONS_LATAM   | 000000000000203708_F3 | SP           | BR08       | Critical-ROH  |
#      | CONS_LATAM   | 000000000000203709_F3 | SP           | BR08       | Critical-ROH  |
#      | CONS_LATAM   | 000000000000203710_F3 | SP           | BR08       | Critical-ROH  |
#      | CONS_LATAM   | 000000000000203711_F3 | SP           | BR08       | Critical-ROH  |
#      | CONS_LATAM   | 000000000000203712_F3 | SP           | BR08       | Critical-ROH  |
#      | CONS_LATAM   | 000000000000214001_T1 | SP           | BR12       | Critical-ROH  |
#      | CONS_LATAM   | 000000000000214002_T2 | SP           | BR12       | Critical-ROH  |
#      | CONS_LATAM   | 000000000000214003_T3 | SP           | BR12       | Critical-ROH  |
#      | CONS_LATAM   | 000000000000214004_T4 | SP           | BR12       | Critical-ROH  |
#      | CONS_LATAM   | 000000000000214005_T5 | SP           | BR12       | Critical-ROH  |
#      | CONS_LATAM   | 000000000000214006_T6 | SP           | BR12       | Critical-ROH  |
#
#    And I wait "/plan/cns_material_incl" Async Queue complete
#
    When I submit task with xml file "xml/plan/PlanCnsMaterialPlanStatus_1.xml" and execute file "jar/pangea-view.jar"
#
#    Then A file is found on sink application with name "PlanCnsMaterialPlanStatus_1.tsv"
#
#    Then I check file data for filename "PlanCnsMaterialPlanStatus_1.tsv" by keyFields "sourceSystem,localMaterialNumber,localPlant"
#      | sourceSystem | localMaterialNumber   | localPlant | materialNumber | localParentCode    | ppc  | active | dpRelevant | spRelevant | parentActive | noPlanRelevant |
#      | CONS_LATAM   | 000000000000214001_T1 | BR12       | -              | 178910100400070072 | 4001 | X      | X          |            | X            | X              |
#      | CONS_LATAM   | 000000000000214002_T2 | BR12       | -              | 178910100400070072 | 4002 | X      | X          |            | X            | X              |
#      | CONS_LATAM   | 000000000000214003_T3 | BR12       | -              | 178910100400070072 | 4003 | X      | X          |            | X            | X              |
#      | CONS_LATAM   | 000000000000214004_T4 | BR12       | -              | 178910100400070072 | 4004 | X      | X          |            | X            | X              |
#      | CONS_LATAM   | 000000000000214005_T5 | BR12       | -              | 178910100400070072 | 4005 | X      | X          |            | X            | X              |
#      | CONS_LATAM   | 000000000000214006_T6 | BR12       | -              | 178910100400070072 | 4006 | X      | X          |            | X            | X              |
#
#    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
#      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |
#
##    And I compare the number of records between "/edm/material_plant_v1" and "/plan/cns_material_plan_status,/plan/edm_failed_data"
#
#    And I delete the test data
#
#    And I will remove all data with region "/plan/cns_material_plan_status"
#    And I will remove all data with region "/plan/edm_failed_data"
#    And I will remove the test file on sink application "PlanCnsMaterialPlanStatus_1.tsv"