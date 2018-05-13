@pangea_test @AEAZ-3216
Feature: CnsMaterialPlanStatus AEAZ-3216

  Scenario: Full Load curation - 1

    Given I import "/edm/material_plant_v1" by keyFields "localMaterialNumber,localPlant"
      | localMaterialNumber   | localPlant | materialNumber | localMrpType | localPlantStatus | localMrpController |
      | 000000000000203700_F1 | BR12       | -              | PD           |                  | 999                |
      | 000000000000203701_F1 | BR12       | -              | PD           |                  | 999                |
      | 000000000000203702_F1 | BR12       | -              | PD           |                  | 999                |
      | 000000000000203703_F1 | BR12       | -              | PD           |                  | 999                |
      | 000000000000203704_F2 | BR08       | -              | PD           | 9                | 999                |
      | 000000000000203705_F2 | BR12       | -              | ND           | 9                | 999                |
      | 000000000000203706_F2 | BR12       | -              | PD           | 8                | 999                |
      | 000000000000203707_F2 | BR12       | -              | PD           | 9                | 222                |
      | 000000000000203708_F3 | BR08       | -              | PD           | 11               | 111                |
      | 000000000000203709_F3 | BR12       | -              | ND           | 11               | 111                |
      | 000000000000203710_F3 | BR12       | -              | PD           | 8                | 111                |
      | 000000000000203711_F3 | BR12       | -              | PD           | 11               | 222                |
      | 000000000000203712_F3 | BR12       | -              | PD           | 11               | 111                |
      | 000000000000214001_T1 | BR12       | -              | PD           | 9                | 999                |
      | 000000000000214002_T2 | BR12       | -              | PD           | 9                | 999                |
      | 000000000000214003_T3 | BR12       | -              | PD           | 9                | 999                |
      | 000000000000214004_T4 | BR12       | -              | PD           | 9                | 999                |
      | 000000000000214005_T5 | BR12       | -              | PD           | 9                | 999                |
      | 000000000000214006_T6 | BR12       | -              | PD           | 9                | 999                |

    And I wait "/edm/material_plant_v1" Async Queue complete|

    And I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | localSourceSystemName | sourceSystem | sourceSystemName   |
      | Project_One       | Project One           | CONS_LATAM   | Consumer Latam Ent |
      | project_two       | Project Two           | CONS_LATAM   | Consumer Latam Ent |
    And I wait "/edm/source_system_v1" Async Queue complete

    And I import "/edm/material_global_v1" by keyFields "localMaterialNumber"
      | localMaterialType | localMaterialNumber   | localDpParentCode  | primaryPlanningCode | division | flagForDeletion |
      | HAWA              | 000000000000203700_F1 | 178962124094540036 | 4007                | 10       |                 |
      | FERT              | 000000000000203701_F1 | 178962124094540036 | 4007                | 10       |                 |
      | SAPR              | 000000000000203702_F1 | 178962124094540036 | 4007                | 5        |                 |
      | SAPR              | 000000000000203703_F1 | 178962124094540036 | 4007                | 20       |                 |
      | SAPR              | 000000000000203704_F2 | 178962124094540036 | 4008                | 10       |                 |
      | SAPR              | 000000000000203705_F2 | 178962124094540036 | 4008                | 10       |                 |
      | SAPR              | 000000000000203706_F2 | 178962124094540036 | 4008                | 10       |                 |
      | SAPR              | 000000000000203707_F2 | 178962124094540036 | 4008                | 10       |                 |
      | SAPR              | 000000000000203708_F3 | 178962124094540036 | 4000                | 10       |                 |
      | SAPR              | 000000000000203709_F3 | 178962124094540036 | 4000                | 10       |                 |
      | SAPR              | 000000000000203710_F3 | 178962124094540036 | 4000                | 10       |                 |
      | SAPR              | 000000000000203711_F3 | 178962124094540036 | 4000                | 10       |                 |
      | SAPR              | 000000000000203712_F3 | 178962124094540036 | 4000                | 10       |                 |
      | SAPR              | 000000000000214001_T1 | 178910100400070072 | 4001                | 10       |                 |
      | SAPR              | 000000000000214002_T2 | 178910100400070072 | 4002                | 10       |                 |
      | SAPR              | 000000000000214003_T3 | 178910100400070072 | 4003                | 10       |                 |
      | SAPR              | 000000000000214004_T4 | 178910100400070072 | 4004                | 10       |                 |
      | SAPR              | 000000000000214005_T5 | 178910100400070072 | 4005                | 10       |                 |
      | SAPR              | 000000000000214006_T6 | 178910100400070072 | 4006                | 10       |                 |
    And I wait "/edm/material_global_v1" Async Queue complete

    And I import "/plan/cns_plan_parameter" by keyFields "sourceSystem,dataObject,attribute,parameter"
      | sourceSystem | dataObject               | attribute  | parameter     | inclExcl | parameterValue |
      | CONS_LATAM   | cns_material_plan_status | DPRelevant | MaterialType  | I        | SAPR           |
      | CONS_LATAM   | cns_material_plan_status | SPRelevant | MaterialType  | E        | FERT           |
      | CONS_LATAM   | cns_material_plan_status | DPRelevant | Division      | I        | 10             |
      | CONS_LATAM   | cns_material_plan_status | SPRelevant | Division      | E        | 20             |
      | CONS_LATAM   | cns_material_plan_status | DPRelevant | Plant         | I        | BR12           |
      | CONS_LATAM   | cns_material_plan_status | SPRelevant | Plant         | I        | BR12           |
      | CONS_LATAM   | cns_material_plan_status | DPRelevant | MRPType       | E        | ND             |
      | CONS_LATAM   | cns_material_plan_status | SPRelevant | MRPType       | I        | PD             |
      | CONS_LATAM   | cns_material_plan_status | DPRelevant | PSMSStatus    | I        | 9              |
      | CONS_LATAM   | cns_material_plan_status | SPRelevant | PSMSStatus    | I        | 11             |
      | CONS_LATAM   | cns_material_plan_status | DPRelevant | MrpController | I        | 999            |
      | CONS_LATAM   | cns_material_plan_status | SPRelevant | MrpController | I        | 111            |

    And I wait "/plan/cns_plan_parameter" Async Queue complete

    And I import "/plan/cns_material_incl" by keyFields "localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber   | planningType | localPlant | inclusionType |
      | CONS_LATAM   | 000000000000203700_F1 | SP           | BR12       | Critical-ROH  |
      | CONS_LATAM   | 000000000000203701_F1 | SP           | BR12       | Critical-ROH  |
      | CONS_LATAM   | 000000000000203702_F1 | SP           | BR12       | Critical-ROH  |
      | CONS_LATAM   | 000000000000203703_F1 | SP           | BR12       | Critical-ROH  |
      | CONS_LATAM   | 000000000000203704_F2 | SP           | BR12       | Critical-ROH  |
      | CONS_LATAM   | 000000000000203705_F2 | SP           | BR12       | Critical-ROH  |
      | CONS_LATAM   | 000000000000203706_F2 | SP           | BR12       | Critical-ROH  |
      | CONS_LATAM   | 000000000000203707_F2 | SP           | BR12       | Critical-ROH  |
      | CONS_LATAM   | 000000000000203708_F3 | SP           | BR08       | Critical-ROH  |
      | CONS_LATAM   | 000000000000203709_F3 | SP           | BR08       | Critical-ROH  |
      | CONS_LATAM   | 000000000000203710_F3 | SP           | BR08       | Critical-ROH  |
      | CONS_LATAM   | 000000000000203711_F3 | SP           | BR08       | Critical-ROH  |
      | CONS_LATAM   | 000000000000203712_F3 | SP           | BR08       | Critical-ROH  |
      | CONS_LATAM   | 000000000000214001_T1 | SP           | BR12       | Critical-ROH  |
      | CONS_LATAM   | 000000000000214002_T2 | SP           | BR12       | Critical-ROH  |
      | CONS_LATAM   | 000000000000214003_T3 | SP           | BR12       | Critical-ROH  |
      | CONS_LATAM   | 000000000000214004_T4 | SP           | BR12       | Critical-ROH  |
      | CONS_LATAM   | 000000000000214005_T5 | SP           | BR12       | Critical-ROH  |
      | CONS_LATAM   | 000000000000214006_T6 | SP           | BR12       | Critical-ROH  |

    And I wait "/plan/cns_material_incl" Async Queue complete

    When I submit task with xml file "xml/plan/PlanCnsMaterialPlanStatus.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/plan/cns_material_plan_status" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber   | localPlant | materialNumber | localParentCode    | ppc  | active | dpRelevant | spRelevant | parentActive | noPlanRelevant |
      | CONS_LATAM   | 000000000000214001_T1 | BR12       | -              | 178910100400070072 | 4001 | X      | X          |            | X            | X              |
      | CONS_LATAM   | 000000000000214002_T2 | BR12       | -              | 178910100400070072 | 4002 | X      | X          |            | X            | X              |
      | CONS_LATAM   | 000000000000214003_T3 | BR12       | -              | 178910100400070072 | 4003 | X      | X          |            | X            | X              |
      | CONS_LATAM   | 000000000000214004_T4 | BR12       | -              | 178910100400070072 | 4004 | X      | X          |            | X            | X              |
      | CONS_LATAM   | 000000000000214005_T5 | BR12       | -              | 178910100400070072 | 4005 | X      | X          |            | X            | X              |
      | CONS_LATAM   | 000000000000214006_T6 | BR12       | -              | 178910100400070072 | 4006 | X      | X          |            | X            | X              |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

#    And I compare the number of records between "/edm/material_plant_v1" and "/plan/cns_material_plan_status,/plan/edm_failed_data"

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/plan/cns_material_plan_status"

    And I will remove all data with region "/plan/edm_failed_data"

  Scenario: Full Load curation - 2
    #1.get sourceSystem from cns_material_incl (rule J1)
    #2.get localMaterialNumber and localPlant from cns_material_incl (rule T1)
    #3.get materialNumber from material_global_v1 (rule J2)
    #4.get localParentCode from material_global_v1 (rule T2)
    #5.get ppc from material_global_v1 (rule T3)
    #6.get active by rule T6
    #7.get dpRelevant and spRelevant by rule D1
    #8.get parentActive by rule T4
    #9.get noPlanRelevant by rule T5

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

    Then I check region data "/plan/cns_material_plan_status" by keyFields "sourceSystem,localMaterialNumber,localPlant"
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

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/plan/cns_material_plan_status"

    And I will remove all data with region "/plan/edm_failed_data"

  Scenario: Full Load curation - 3

    Given I import "/edm/sales_order_v1" by keyFields "salesOrderItem,salesOrderNo,scheduleLineItem,sourceSystem"
      | salesOrderItem | salesOrderNo | scheduleLineItem | sourceSystem | localPlant | localMaterialNumber       | localSalesOrg | localOrderType | localShipToParty | localOrderCreateDt |
      | 000001         | 000001       | 0001             | CONS_LATAM   | VE06       | 000000000000056504        | VE03          | ZLSR           | 123451223        | 20150408           |
      | 000002         | 000002       | 0002             | CONS_LATAM   | VE07       | 111111111111122222        | BR01          | ZBEF           | 78623213111      | 20150408           |
      | 000003         | 000003       | 0003             | CONS_LATAM   | VE05_F1    | 000000000000213998        | VE03          | ZLSR           | 0000174002       | 20150408           |
      | 000004         | 000004       | 0004             | CONS_LATAM   | VE08       | 0000000000002139_F1       | VE03          | ZLSR           | 0000174002       | 20150408           |
      | 000005         | 000005       | 0005             | CONS_LATAM   | VE06       | 000000000000056504        | BR01_F2       | ZLSR           | 0000174002       | 20150408           |
      | 000006         | 000006       | 0006             | CONS_LATAM   | VE08       | 000000000000213998        | VE03_J1       | ZLSR           | 123451223        | 20150408           |
      | 000007         | 000007       | 0007             | CONS_LATAM   | VE08       | 162312313231112312        | ED01          | EDCD           | 123451223        | 20150408           |
      | 000008         | 000008       | 0008             | CONS_LATAM   | VE09       | 162312313231112312_J2     | ED01          | EDCD           | 123451223        | 20150408           |
      | 000009         | 000009       | 0009             | CONS_LATAM   | VE10       | 162312313231112312_T2&&T3 | ED01          | EDCD           | 123451223        | 20150408           |
    And I wait "/edm/sales_order_v1" Async Queue complete

    And I import "/plan/cns_plan_parameter" by keyFields "attribute,dataObject,parameter,parameterValue,sourceSystem"
      | attribute           | dataObject               | inclExcl | parameter | parameterValue | sourceSystem |
      | DPRelevant          | cns_material_plan_status | I        | Plant     | VE10           | CONS_LATAM   |
      | DPRelevant          | cns_material_plan_status | I        | Plant     | VE07           | CONS_LATAM   |
      | DPRelevant          | cns_material_plan_status | I        | Plant     | VE08           | CONS_LATAM   |
      | DPRelevant          | cns_material_plan_status | I        | Plant     | VE09           | CONS_LATAM   |
      | DPRelevant          | cns_material_plan_status | I        | Plant     | VE06           | CONS_LATAM   |
      | DPRelevant_F1       | cns_material_plan_status | I        | Plant     | VE06           | CONS_LATAM   |
      | DPRelevant          | cns_material_plan_F1     | I        | Plant     | VE06           | CONS_LATAM   |
      | DPRelevant          | cns_material_plan_F1     | E        | Plant     | VE05_F1        | CONS_LATAM   |
      | DPRelevant          | cns_material_plan_status | E_F1     | Plant     | VE05           | CONS_LATAM   |
      | LocalMaterialNumber | cns_product_inclusion    | I        | MRPType   | PD             | CONS_LATAM   |
      | LocalMaterialNumber | cns_product_inclusion    | E_F2     | MRPType   | PRD            | CONS_LATAM   |
      | LocalMaterialNumber | cns_product_inclusion    | I        | MRPType   | PR             | CONS_LATAM   |
      | LessDays            | cns_product_inclusion    | I        | LessDays  | 1200           | CONS_LATAM   |
      | LessDays            | cns_product_inclusion    | E_J1     | LessDays  | 1300           | CONS_LATAM   |
    And I wait "/plan/cns_plan_parameter" Async Queue complete

    And I import "/edm/material_plant_v1" by keyFields "localMaterialNumber,localPlant"
      | localMaterialNumber | localPlant | materialNumber | localMrpType |
      | 000000000000056504  | ND         | -              | PD           |
      | 111111111111122222  | BR12       | -              | PR           |
      | 0000000000002139_F1 | PD23       | -              | PD23         |
      | 000000000000213998  | PD23       | -              | PD           |
      | 162312313231112312  | PD23       | -              | PD           |
    And I wait "/edm/material_plant_v1" Async Queue complete

    And I import "/plan/cns_cust_excl" by keyFields "country,customerShipTo,salesOrg,sourceSystem"
      | country | customerShipTo | salesOrg | sourceSystem |
      | BR      | 116538         | VE03     | CON_LATAM    |
      | BR      | 0000174002     | BR01_F2  | CON_LATAM    |
      | BR      | 178991         | BR01     | CON_LATAM    |
    And I wait "/plan/cns_cust_excl" Async Queue complete

    And I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | localSourceSystemName | sourceSystem | sourceSystemName   |
      | Project_One       | Project One           | CONS_LATAM   | Consumer Latam Ent |
      | project_two       | Project Two           | CONS_LATAM   | Consumer Latam Ent |
    And I wait "/edm/source_system_v1" Async Queue complete

    And I import "/edm/material_global_v1" by keyFields "localMaterialNumber"
      | localMaterialType | localMaterialNumber       | materialNumber | localDpParentCode  | primaryPlanningCode |
      | ROH               | 000000000000056504        | 11111          | 178962124094540036 | 945                 |
      | HALB              | 111111111111122222        | 22222          | 27896212           | 978                 |
      | HALB              | 000000000000213998        | 66666          | 37896212           | 988                 |
      | FBLB              | 162312313231112312        | 44444          | 37896212           | 990                 |
      | CBBBB             | 162312313231112312_T2&&T3 | 5555           | 37896212           | 1090                |
    And I wait "/edm/material_global_v1" Async Queue complete

    And I import "/plan/cns_so_type_incl" by keyFields "country,orderType,salesOrg,sourceSystem"
      | country | orderType | salesOrg | sourceSystem |
      | BR      | ZLSR      | VE03     | CONS_LATAM   |
      | CN      | ZBEF      | BR01     | CONS_LATAM   |
      | BC      | ZBEF      | BR01_J1  | CONS_LATAM   |
      | JN      | EDCD      | ED01     | CONS_LATAM   |
    And I wait "/plan/cns_so_type_incl" Async Queue complete

    And I import "/edm/plant_v1" by keyFields "localPlant,sourceSystem"
      | localPlant | sourceSystem | country |
      | VE06       | ZLSR         | BR      |
      | VE07       | ZBEF         | CN      |
      | VE08       | ZBEF         | JN      |
      | VE09       | ZBEF         | JN      |
      | VE10       | ZBEF         | JN      |
    And I wait "/edm/plant_v1" Async Queue complete

    And I import "/plan/cns_material_plan_status" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber       | localPlant | dpRelevant | parentActive |
      | PLAN_DD      | 000000000000056504        | VE06       | X          | X            |
      | TTTT_DA      | 111111111111122222        | VE07       | X          | X            |
      | BBBB_GD      | 162312313231112312        | VE08       | X          | X            |
      | CCCC_AD      | 162312313231112312_J2     | VE09       | X          | X            |
      | CCCC_AD      | 162312313231112312_T2&&T3 | VE10       | X          | X            |
    And I wait "/plan/cns_material_plan_status" Async Queue complete

    When I submit task with xml file "xml/plan/PlanCnsMaterialPlanStatus_3.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/plan/cns_material_plan_status" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber       | localPlant | materialNumber | localParentCode    | ppc | active | dpRelevant | spRelevant | parentActive | noPlanRelevant |
      | CONS_LATAM   | 000000000000056504        | VE06       | 11111          | 178962124094540036 | 945 | X      | X          |            | X            |                |
      | CONS_LATAM   | 111111111111122222        | VE07       | 22222          | 27896212           | 978 | X      | X          |            | X            |                |
      | CONS_LATAM   | 162312313231112312        | VE08       | 44444          | 37896212           | 990 | X      | X          |            | X            |                |
      | CONS_LATAM   | 162312313231112312_J2     | VE09       |                |                    |     | X      | X          |            |              |                |
      | CONS_LATAM   | 162312313231112312_T2&&T3 | VE10       | 5555           |                    |     | X      | X          |            |              |                |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

#    And I compare the number of records between "/edm/plant_v1" and "/plan/cns_plant_attr,/plan/edm_failed_data"

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/plan/cns_material_plan_status"

    And I will remove all data with region "/plan/edm_failed_data"