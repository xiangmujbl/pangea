@pangea_test @AEAZ-3216  @AEAZ-7320
Feature: CnsMaterialPlanStatus AEAZ-3216 AEAZ-7320

  @Scenario1
  Scenario: Full Load curation

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
      | CONS_LATAM   | 000000000000203700_F1 | NP           | BR12       | Critical-ROH  |
      | CONS_LATAM   | 000000000000203701_F1 | NP           | BR12       | Critical-ROH  |
      | CONS_LATAM   | 000000000000203702_F1 | NP           | BR12       | Critical-ROH  |
      | CONS_LATAM   | 000000000000203703_F1 | NP           | BR12       | Critical-ROH  |
      | CONS_LATAM   | 000000000000203704_F2 | NP           | BR12       | Critical-ROH  |
      | CONS_LATAM   | 000000000000203705_F2 | NP           | BR12       | Critical-ROH  |
      | CONS_LATAM   | 000000000000203706_F2 | NP           | BR12       | Critical-ROH  |
      | CONS_LATAM   | 000000000000203707_F2 | NP           | BR12       | Critical-ROH  |
      | CONS_LATAM   | 000000000000203708_F3 | NP           | BR08       | Critical-ROH  |
      | CONS_LATAM   | 000000000000203709_F3 | NP           | BR08       | Critical-ROH  |
      | CONS_LATAM   | 000000000000203710_F3 | NP           | BR08       | Critical-ROH  |
      | CONS_LATAM   | 000000000000203711_F3 | NP           | BR08       | Critical-ROH  |
      | CONS_LATAM   | 000000000000203712_F3 | NP           | BR08       | Critical-ROH  |
      | CONS_LATAM   | 000000000000214001_T1 | NP           | BR12       | Critical-ROH  |
      | CONS_LATAM   | 000000000000214002_T2 | NP           | BR12       | Critical-ROH  |
      | CONS_LATAM   | 000000000000214003_T3 | NP           | BR12       | Critical-ROH  |
      | CONS_LATAM   | 000000000000214004_T4 | NP           | BR12       | Critical-ROH  |
      | CONS_LATAM   | 000000000000214005_T5 | NP           | BR12       | Critical-ROH  |
      | CONS_LATAM   | 000000000000214006_T6 | NP           | BR12       | Critical-ROH  |

    And I wait "/plan/cns_material_incl" Async Queue complete

    When I submit task with xml file "xml/plan/PlanCnsMaterialPlanStatus_1.xml" and execute file "jar/pangea-view.jar"

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

    And I delete the test data

    And I will remove all data with region "/plan/cns_material_plan_status"
    And I will remove all data with region "/plan/cns_material_incl"
    And I will remove all data with region "/edm/material_global_v1"
    And I will remove all data with region "/plan/edm_failed_data"

  @Scenario2
  Scenario: Full Load curation

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
      | localSourceSystem | sourceSystem |
      | project_one       | CONS_LATAM   |
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

    And I delete the test data

    And I will remove all data with region "/plan/cns_material_plan_status"
    And I will remove all data with region "/plan/edm_failed_data"

  @Scenario3
  Scenario: Full Load curation

    Given I import "/edm/sales_order_v1" by keyFields "salesOrderItem,salesOrderNo,scheduleLineItem,sourceSystem"
      | salesOrderItem | salesOrderNo | scheduleLineItem | sourceSystem | localPlant | localMaterialNumber | localSalesOrg | localOrderType | localShipToParty | localOrderCreateDt |
      | 000001         | 000001       | 0001             | CONS_LATAM   | BR19       | 93                  | BR01          | ZORB           | 116538           | 20171223           |
      | 000002         | 000002       | 0002             | CONS_LATAM   | BR19       | 94                  | BR01          | ZSRT           | 124092           | 20161221           |
      | 000003         | 000003       | 0003             | CONS_LATAM   | BR19       | 136                 | BR01          | ZGRA           | 116538           | 20151222           |
      | 000004         | 000004       | 0004             | CONS_LATAM   | BR19       | 240                 | BR01          | ZZZZ           | 124092           | 20180519           |
      | 000005         | 000005       | 0005             | CONS_LATAM   | BR19       | 502                 | BR01          | ZDIV           | 116538           | 20150718           |
      | 000006         | 000006       | 0006             | CONS_LATAM   | BR16       | 504                 | BR01          | ZRLO           | 124092           | 20150721           |
      | 000007         | 000007       | 0007             | CONS_LATAM   | BR19       | 578                 | BR01          | ZRLO           | 124090           | 20150805           |
      | 000008         | 000008       | 0008             | CONS_LATAM   | BR16       | 604                 | BR01          | ZRLO           | 124092           | 20150805           |
      | 000009         | 000009       | 0009             | CONS_LATAM   | BR16       | 738                 | BR01          | ZRLO           | 116538           | 20150805           |
      | 000010         | 000010       | 0010             | CONS_LATAM   | BR16       | 746                 | BR01          | ZRLO           | 124092           | 20150805           |
      | 000011         | 000011       | 0011             | CONS_LATAM   | CL19       | 93                  | CL01          | ZGRA           | 116538           | 20171223           |
      | 000012         | 000012       | 0012             | CONS_LATAM   | CL19       | 92                  | CL01          | ZORB           | 124092           | 20161221           |
      | 000013         | 000013       | 0013             | CONS_LATAM   | CL19       | 136                 | CL01          | ZORB           | 116538           | 20150510           |
      | 000014         | 000014       | 0014             | CONS_LATAM   | CL19       | 240                 | CL01          | ZZZZ           | 124092           | 20180519           |
      | 000015         | 000015       | 0015             | CONS_LATAM   | CL19       | 502                 | CL01          | ZDIV           | 116538           | 20150718           |
      | 000016         | 000016       | 0016             | CONS_LATAM   | CL16       | 504                 | CL01          | ZRLO           | 124092           | 20150721           |
      | 000017         | 000017       | 0017             | CONS_LATAM   | CL19       | 578                 | CL01          | ZRLO           | 124090           | 20150805           |
      | 000018         | 000018       | 0018             | CONS_LATAM   | CL19       | 578                 | CL01          | ZRLO           | 179311           | 20150805           |
      | 000019         | 000019       | 0019             | CONS_LATAM   | CL16       | 604                 | CL01          | ZRLO           | 124092           | 20150805           |
      | 000020         | 000020       | 0020             | CONS_LATAM   | CL16       | 738                 | CL01          | ZRLO           | 116538           | 20150805           |
      | 000021         | 000021       | 0021             | CONS_LATAM   | CL16       | 746                 | CL01          | ZRLO           | 124092           | 20150805           |

    And I wait "/edm/sales_order_v1" Async Queue complete

    And I import "/plan/cns_plan_parameter" by keyFields "attribute,dataObject,parameter,parameterValue,sourceSystem"
      | attribute           | comments                       | dataObject               | inclExcl | inclusionExclusion | parameter | parameterValue | sourceSystem |
      | DPRelevant          | MARC-WERKS                     | cns_material_plan_status | E        |                    | Plant     | BR16           | CONS_LATAM   |
      | DPRelevant          | MARC-WERKS                     | cns_material_plan_status | I        |                    | Plant     | BR19           | CONS_LATAM   |
      | DPRelevant          | MARC-WERKS                     | cns_material_plan_status | E        |                    | Plant     | CL16           | CONS_LATAM   |
      | DPRelevant          | MARC-WERKS                     | cns_material_plan_status | I        |                    | Plant     | CL19           | CONS_LATAM   |
      | LocalMaterialNumber | MARC-DISMM                     | cns_product_inclusion    | I        |                    | MRPType   | ND             | CONS_LATAM   |
      | LessDays            | Less than 3 year Sales History | cns_product_inclusion    | I        |                    | LessDays  | 1095           | CONS_LATAM   |

    And I wait "/plan/cns_plan_parameter" Async Queue complete

    And I import "/edm/material_plant_v1" by keyFields "localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | materialNumber | localMrpType |
      | CONS_LATAM   | 93                  | BR16       | -              | ND           |
      | CONS_LATAM   | 94                  | BR16       | -              | ND           |
      | CONS_LATAM   | 136                 | BR19       | -              | ND           |
      | CONS_LATAM   | 240                 | BR19       | -              | ND           |
      | CONS_LATAM   | 502                 | BR19       | -              | PD           |
      | CONS_LATAM   | 504                 | BR19       | -              | ND           |
      | CONS_LATAM   | 578                 | BR19       | -              | M0           |
      | CONS_LATAM   | 604                 | BR19       | -              | ND           |
      | CONS_LATAM   | 738                 | BR19       | -              | ND           |
      | CONS_LATAM   | 746                 | BR19       | -              | ND           |
      | CONS_LATAM   | 93                  | CL16       | -              | ND           |
      | CONS_LATAM   | 92                  | CL16       | -              | ND           |
      | CONS_LATAM   | 136                 | CL19       | -              | ND           |
      | CONS_LATAM   | 240                 | CL19       | -              | ND           |
      | CONS_LATAM   | 502                 | CL19       | -              | PD           |
      | CONS_LATAM   | 504                 | CL19       | -              | ND           |
      | CONS_LATAM   | 578                 | CL19       | -              | M0           |
      | CONS_LATAM   | 604                 | CL19       | -              | ND           |
      | CONS_LATAM   | 738                 | CL19       | -              | ND           |
      | CONS_LATAM   | 746                 | CL19       | -              | ND           |


    And I wait "/edm/material_plant_v1" Async Queue complete

    And I import "/plan/cns_cust_excl_incl" by keyFields "country,customerShipTo,salesOrg,sourceSystem"
      | country | customerShipTo | salesOrg | sourceSystem | inclExcl |
      | BR      | 116538         | BR01     | CON_LATAM    | I        |
      | BR      | 124092         | BR01     | CON_LATAM    | I        |
      | CL      | 179311         | CL01     | CON_LATAM    | E        |


    And I wait "/plan/cns_cust_excl_incl" Async Queue complete

    And I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | localSourceSystemName | sourceSystem | sourceSystemName   |
      | Project_One       | Project One           | CONS_LATAM   | Consumer Latam Ent |

    And I wait "/edm/source_system_v1" Async Queue complete


    And I import "/edm/material_global_v1" by keyFields "localMaterialNumber"
      | localMaterialNumber | materialNumber | localDpParentCode | primaryPlanningCode |
      | 93                  | 9300           | 17896212409454    | 51746               |
      | 94                  | 9400           |                   | 6011409             |
      | 136                 | 13600          |                   | 69177               |
      | 240                 | 24000          |                   | 61740               |
      | 502                 | 50200          |                   | 88320               |
      | 504                 | 50400          |                   | 7622195             |
      | 578                 | 57800          |                   | 441484              |
      | 604                 | 60400          |                   | 70327               |
      | 738                 | 73800          |                   | 441133              |
      | 746                 | 74600          |                   | 441133              |

    And I wait "/edm/material_global_v1" Async Queue complete

    And I import "/plan/cns_so_type_incl_excl" by keyFields "country,orderType,salesOrg,sourceSystem"
      | country | orderType | salesOrg | sourceSystem | inclExcl | plant |
#      | BR      | ZGRA      | BR01     | CONS_LATAM   | E        | BR19  |
      | BR      | ZORB      | BR01     | CONS_LATAM   | I        | BR19  |
      | BR      | ZSRT      | BR01     | CONS_LATAM   | I        | BR19  |
      | CL      | ZGRA      | CL01     | CONS_LATAM   | I        | CL19  |
      | CL      | ZORB      | CL01     | CONS_LATAM   | I        | CL19  |
      | CL      | ZZZZ      | CL01     | CONS_LATAM   | E        | CL19  |

    And I wait "/plan/cns_so_type_incl_excl" Async Queue complete

    And I import "/edm/plant_v1" by keyFields "localPlant,sourceSystem"
      | localPlant | sourceSystem | country | localCountry | localCurrency | localPlanningRelevant | plant |
      | BR19       | ZLSR         | BR      | BR           | BRL           | X                     | BR63  |
      | CL19       | ZLSR         | CL      | CL           | CLP           | X                     | CL03  |

    And I wait "/edm/plant_v1" Async Queue complete

    When I submit task with xml file "xml/plan/PlanCnsMaterialPlanStatus_3.xml" and execute file "jar/pangea-view.jar"

    And wait 3000 millisecond

    Then I check region data "/plan/cns_material_plan_status" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | materialNumber | localParentCode | ppc     | active | dpRelevant | spRelevant | parentActive | noPlanRelevant |
      | CONS_LATAM   | 93                  | BR19       | 9300           | 17896212409454  | 51746   | X      | X          |            | X            |                |
      | CONS_LATAM   | 94                  | BR19       | 9400           |                 | 6011409 | X      | X          |            |              |                |
      | CONS_LATAM   | 93                  | CL19       | 9300           | 17896212409454  | 51746   | X      | X          |            | X            |                |
#      | CONS_LATAM   | 240                       | BR19       | 24000           |                    | 61740    | X      | X          |            |              |                |
#      | CONS_LATAM   | 240                       | CL19       | 24000           |                    | 61740    | X      | X          |            |              |                |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

#    And I compare the number of records between "/edm/plant_v1" and "/plan/cns_plant_attr,/plan/edm_failed_data"

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/plan/cns_material_plan_status"

    And I will remove all data with region "/plan/edm_failed_data"
