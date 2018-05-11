@pangea_test @AEAZ-3216
Feature: CnsMaterialPlanStatus_2 AEAZ-3216

  Scenario: Full Load curation
    #1.get sourceSystem from cns_material_incl (rule J1)
    #2.get localMaterialNumber and localPlant from cns_material_incl (rule T1)
    #3.get materialNumber from material_global_v1 (rule J2)
    #4.get localParentCode from material_global_v1 (rule T2)
    #5.get ppc from material_global_v1 (rule T3)
    #6.get active by rule T6
    #7.get dpRelevant and spRelevant by rule D1
    #8.get parentActive by rule T4
    #9.get noPlanRelevant by rule T5

    And I will remove the test file on sink application "PlanCnsMaterialPlanStatus_2.tsv"

    Given I import "/plan/cns_material_incl" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber   | localPlant | planningType |
      | CONS_LATAM   | 000000000000087901_J1 | BR12       | NP           |
      | EMS          | 000000000000087902_J1 | BR12       | NP           |
      | CONS_LATAM   | 000000000000087903_T1 | BR12       | NP           |
      | CONS_LATAM   | 000000000000087904_T4 | BR12       | NP           |
      | CONS_LATAM   | 000000000000087905_J2 | BR12       | NP           |
      | CONS_LATAM   | 000000000000087906_J2 | BR12       | NP           |
      | CONS_LATAM   | 000000000000087906_T2 | BR12       | SP           |
      | CONS_LATAM   | 000000000000087907_T3 | BR12       | SP           |
      | CONS_LATAM   | 000000000000087908_T5 | BR12       | NP           |
      | CONS_LATAM   | 000000000000087909_T6 | BR12       | NP           |
      | CONS_LATAM   | 000000000000087910_D1 | BR12       | SP           |
    And I wait "/plan/cns_material_incl" Async Queue complete


    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem |
      | CONS_LATAM        |
    And I wait "/edm/source_system_v1" Async Queue complete


    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber,sourceSystem"
      | sourceSystem | localMaterialNumber   | materialNumber | localDpParentCode  | primaryPlanningCode |
#      | CONS_LATAM   | 000000000000087901_J1 | 56901          |                    | -                   |
      | CONS_LATAM   | 000000000000087903_T1 | 56902          |                    | -                   |
      | CONS_LATAM   | 000000000000087904_T4 | 56903          | 178962124094540035 | -                   |
      | CONS_LATAM   | 000000000000087906_T2 | 56904          | 178962124094540036 | -                   |
      | CONS_LATAM   | 000000000000087907_T3 | 56905          |                    | T3                  |
      | CONS_LATAM   | 000000000000087908_T5 | 56906          |                    | -                   |
      | CONS_LATAM   | 000000000000087909_T6 | 56907          |                    | -                   |
      | CONS_LATAM   | 000000000000087910_D1 | 56908          |                    | -                   |
#      | CONS_LATAM   | 000000000000087906_J2 | 56909          |                    | -                   |
      | CONS_LATAM   | 000000000000087920    | 56910          |                    | -                   |

    And I wait "/edm/material_global_v1" Async Queue complete


    When I submit task with xml file "xml/plan/PlanCnsMaterialPlanStatus_2.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "PlanCnsMaterialPlanStatus_2.tsv"

    Then I check file data for filename "PlanCnsMaterialPlanStatus_2.tsv" by keyFields "sourceSystem,localMaterialNumber,localPlant"

      | sourceSystem | localMaterialNumber   | localPlant | materialNumber | localParentCode    | ppc | active | dpRelevant | spRelevant | parentActive | noPlanRelevant |
#      | CONS_LATAM   | 000000000000087901_J1 | BR12       | 56901          |                    | -   | X      |            |            |              | X              |
      | CONS_LATAM   | 000000000000087903_T1 | BR12       | 56902          |                    | -   | X      |            |            |              | X              |
      | CONS_LATAM   | 000000000000087904_T4 | BR12       | 56903          | 178962124094540035 | -   | X      |            |            | X            | X              |
      | CONS_LATAM   | 000000000000087906_T2 | BR12       | 56904          | 178962124094540036 | -   |        |            |            | X            |                |
      | CONS_LATAM   | 000000000000087907_T3 | BR12       | 56905          |                    | T3  |        |            |            |              |                |
      | CONS_LATAM   | 000000000000087908_T5 | BR12       | 56906          |                    | -   | X      |            |            |              | X              |
      | CONS_LATAM   | 000000000000087909_T6 | BR12       | 56907          |                    | -   | X      |            |            |              | X              |
      | CONS_LATAM   | 000000000000087910_D1 | BR12       | 56908          |                    | -   |        |            |            |              |                |
#      | CONS_LATAM   | 000000000000087906_J2 | BR12       | 56909          |                    | -   | X      |            |            |              | X              |


    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

#    And I compare the number of records between "/plan/cns_material_incl" and "/plan/cns_material_plan_status,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/plan/cns_material_plan_status"
    And I will remove all data with region "/plan/edm_failed_data"

#    And I will remove the test file on sink application "PlanCnsMaterialPlanStatus_2.tsv"
