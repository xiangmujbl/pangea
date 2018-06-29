@pangea_test @AEAZ-7320
Feature: CnsMaterialPlanStatus AEAZ-7320

  @Scenario3
  Scenario: Full Load curation
#
#    Given I import "/edm/sales_order_v1" by keyFields "salesOrderItem,salesOrderNo,scheduleLineItem,sourceSystem"
#      | salesOrderItem | salesOrderNo | scheduleLineItem | sourceSystem | localPlant | localMaterialNumber       | localSalesOrg | localOrderType | localShipToParty | localOrderCreateDt |
#      | 000001         | 000001       | 0001             | CONS_LATAM   | BR19       | 93                        | BR01          | ZORB           | 116538           | 20171223           |
#      | 000002         | 000002       | 0002             | CONS_LATAM   | BR19       | 94                        | BR01          | ZSRT           | 124092           | 20161221           |
#      | 000003         | 000003       | 0003             | CONS_LATAM   | BR19       | 136                       | BR01          | ZGRA           | 116538           | 20151222           |
#      | 000004         | 000004       | 0004             | CONS_LATAM   | BR19       | 240                       | BR01          | ZZZZ           | 124092           | 20180519           |
#      | 000005         | 000005       | 0005             | CONS_LATAM   | BR19       | 502                       | BR01          | ZDIV           | 116538           | 20150718           |
#      | 000006         | 000006       | 0006             | CONS_LATAM   | BR16       | 504                       | BR01          | ZRLO           | 124092           | 20150721           |
#      | 000007         | 000007       | 0007             | CONS_LATAM   | BR19       | 578                       | BR01          | ZRLO           | 124090           | 20150805           |
#      | 000008         | 000008       | 0008             | CONS_LATAM   | BR16       | 604                       | BR01          | ZRLO           | 124092           | 20150805           |
#      | 000009         | 000009       | 0009             | CONS_LATAM   | BR16       | 738                       | BR01          | ZRLO           | 116538           | 20150805           |
#      | 000010         | 000010       | 0010             | CONS_LATAM   | BR16       | 746                       | BR01          | ZRLO           | 124092           | 20150805           |
#      | 000011         | 000011       | 0011             | CONS_LATAM   | CL19       | 93                        | CL01          | ZGRA           | 116538           | 20171223           |
#      | 000012         | 000012       | 0012             | CONS_LATAM   | CL19       | 92                        | CL01          | ZORB           | 124092           | 20161221           |
#      | 000013         | 000013       | 0013             | CONS_LATAM   | CL19       | 136                       | CL01          | ZORB           | 116538           | 20150510           |
#      | 000014         | 000014       | 0014             | CONS_LATAM   | CL19       | 240                       | CL01          | ZZZZ           | 124092           | 20180519           |
#      | 000015         | 000015       | 0015             | CONS_LATAM   | CL19       | 502                       | CL01          | ZDIV           | 116538           | 20150718           |
#      | 000016         | 000016       | 0016             | CONS_LATAM   | CL16       | 504                       | CL01          | ZRLO           | 124092           | 20150721           |
#      | 000017         | 000017       | 0017             | CONS_LATAM   | CL19       | 578                       | CL01          | ZRLO           | 124090           | 20150805           |
#      | 000018         | 000018       | 0018             | CONS_LATAM   | CL19       | 578                       | CL01          | ZRLO           | 179311           | 20150805           |
#      | 000019         | 000019       | 0019             | CONS_LATAM   | CL16       | 604                       | CL01          | ZRLO           | 124092           | 20150805           |
#      | 000020         | 000020       | 0020             | CONS_LATAM   | CL16       | 738                       | CL01          | ZRLO           | 116538           | 20150805           |
#      | 000021         | 000021       | 0021             | CONS_LATAM   | CL16       | 746                       | CL01          | ZRLO           | 124092           | 20150805           |
#
#
#    And I wait "/edm/sales_order_v1" Async Queue complete
#
#    And I import "/plan/cns_plan_parameter" by keyFields "attribute,dataObject,parameter,parameterValue,sourceSystem"
#      | attribute           | comments                       | dataObject               | inclExcl | inclusionExclusion | parameter | parameterValue | sourceSystem |
#      | DPRelevant          | MARC-WERKS                     |cns_material_plan_status  | E        |                    | Plant     | BR16           | CONS_LATAM   |
#      | DPRelevant          | MARC-WERKS                     |cns_material_plan_status  | I        |                    | Plant     | BR19           | CONS_LATAM   |
#      | DPRelevant          | MARC-WERKS                     |cns_material_plan_status  | E        |                    | Plant     | CL16           | CONS_LATAM   |
#      | DPRelevant          | MARC-WERKS                     |cns_material_plan_status  | I        |                    | Plant     | CL19           | CONS_LATAM   |
#      | LocalMaterialNumber | MARC-DISMM                     |cns_product_inclusion     | I        |                    | MRPType   | ND             | CONS_LATAM   |
#      | LessDays            | Less than 3 year Sales History |cns_product_inclusion     | I        |                    | LessDays  | 1095           | CONS_LATAM   |
#
#
#    And I wait "/plan/cns_plan_parameter" Async Queue complete
#
#    And I import "/edm/material_plant_v1" by keyFields "localMaterialNumber,localPlant"
#      | sourceSystem | localMaterialNumber       | localPlant | materialNumber | localMrpType |
#      | CONS_LATAM   | 93                        | BR16       | -              | ND           |
#      | CONS_LATAM   | 94                        | BR16       | -              | ND           |
#      | CONS_LATAM   | 136                       | BR19       | -              | ND           |
#      | CONS_LATAM   | 240                       | BR19       | -              | ND           |
#      | CONS_LATAM   | 502                       | BR19       | -              | PD           |
#      | CONS_LATAM   | 504                       | BR19       | -              | ND           |
#      | CONS_LATAM   | 578                       | BR19       | -              | M0           |
#      | CONS_LATAM   | 604                       | BR19       | -              | ND           |
#      | CONS_LATAM   | 738                       | BR19       | -              | ND           |
#      | CONS_LATAM   | 746                       | BR19       | -              | ND           |
#      | CONS_LATAM   | 93                        | CL16       | -              | ND           |
#      | CONS_LATAM   | 92                        | CL16       | -              | ND           |
#      | CONS_LATAM   | 136                       | CL19       | -              | ND           |
#      | CONS_LATAM   | 240                       | CL19       | -              | ND           |
#      | CONS_LATAM   | 502                       | CL19       | -              | PD           |
#      | CONS_LATAM   | 504                       | CL19       | -              | ND           |
#      | CONS_LATAM   | 578                       | CL19       | -              | M0           |
#      | CONS_LATAM   | 604                       | CL19       | -              | ND           |
#      | CONS_LATAM   | 738                       | CL19       | -              | ND           |
#      | CONS_LATAM   | 746                       | CL19       | -              | ND           |
#
#
#    And I wait "/edm/material_plant_v1" Async Queue complete
#
#    And I import "/plan/cns_cust_excl_incl" by keyFields "country,customerShipTo,salesOrg,sourceSystem"
#      | country | customerShipTo | salesOrg | sourceSystem | inclExcl  |
#      | BR      | 116538         | BR01     | CON_LATAM    | I         |
#      | BR      | 124092         | BR01     | CON_LATAM    | I         |
#      | CL      | 179311         | CL01     | CON_LATAM    | E         |
#
#
#    And I wait "/plan/cns_cust_excl_incl" Async Queue complete
#
#    And I import "/edm/source_system_v1" by keyFields "localSourceSystem"
#      | localSourceSystem | localSourceSystemName | sourceSystem | sourceSystemName   |
#      | Project_One       | Project One           | CONS_LATAM   | Consumer Latam Ent |
#
#    And I wait "/edm/source_system_v1" Async Queue complete
#
#
#    And I import "/edm/material_global_v1" by keyFields "localMaterialNumber"
#      | localMaterialNumber | materialNumber | localDpParentCode  | primaryPlanningCode |
#      | 93                  | 9300           | 17896212409454     | 51746               |
#      | 94                  | 9400           |                    | 6011409             |
#      | 136                 | 13600          |                    | 69177               |
#      | 240                 | 24000          |                    | 61740               |
#      | 502                 | 50200          |                    | 88320               |
#      | 504                 | 50400          |                    | 7622195             |
#      | 578                 | 57800          |                    | 441484              |
#      | 604                 | 60400          |                    | 70327               |
#      | 738                 | 73800          |                    | 441133              |
#      | 746                 | 74600          |                    | 441133              |
#
#    And I wait "/edm/material_global_v1" Async Queue complete
#
#    And I import "/plan/cns_so_type_incl_excl" by keyFields "country,orderType,salesOrg,sourceSystem"
#      | country | orderType | salesOrg | sourceSystem | inclExcl | plant |
##      | BR      | ZGRA      | BR01     | CONS_LATAM   | E        | BR19  |
#      | BR      | ZORB      | BR01     | CONS_LATAM   | I        | BR19  |
#      | BR      | ZSRT      | BR01     | CONS_LATAM   | I        | BR19  |
#      | CL      | ZGRA      | CL01     | CONS_LATAM   | I        | CL19  |
#      | CL      | ZORB      | CL01     | CONS_LATAM   | I        | CL19  |
#      | CL      | ZZZZ      | CL01     | CONS_LATAM   | E        | CL19  |
#
#    And I wait "/plan/cns_so_type_incl_excl" Async Queue complete
#
#    And I import "/edm/plant_v1" by keyFields "localPlant,sourceSystem"
#      | localPlant | sourceSystem | country | localCountry | localCurrency | localPlanningRelevant | plant |
#      | BR19       | ZLSR         | BR      | BR           | BRL           | X                     | BR63  |
#      | CL19       | ZLSR         | CL      | CL           | CLP           | X                     | CL03  |
#
#
#    And I wait "/edm/plant_v1" Async Queue complete

    When I submit task with xml file "xml/plan/PlanCnsMaterialPlanStatus_3.xml" and execute file "jar/pangea-view.jar"

#    And wait 3000 millisecond
#
#    Then I check region data "/plan/cns_material_plan_status" by keyFields "sourceSystem,localMaterialNumber,localPlant"
#      | sourceSystem | localMaterialNumber       | localPlant | materialNumber | localParentCode    | ppc      | active | dpRelevant | spRelevant | parentActive | noPlanRelevant |
#      | CONS_LATAM   | 93                        | BR19       | 9300           | 17896212409454     | 51746    | X      | X          |            | X            |                |
#      | CONS_LATAM   | 94                        | BR19       | 9400           |                    | 6011409  | X      | X          |            |              |                |
#      | CONS_LATAM   | 93                        | CL19       | 9300           | 17896212409454     | 51746    | X      | X          |            | X            |                |
##      | CONS_LATAM   | 240                       | BR19       | 24000           |                    | 61740    | X      | X          |            |              |                |
##      | CONS_LATAM   | 240                       | CL19       | 24000           |                    | 61740    | X      | X          |            |              |                |
#
#    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
#      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |
#
#
##    And I compare the number of records between "/edm/plant_v1" and "/plan/cns_plant_attr,/plan/edm_failed_data"
#
#  Scenario: delete all test data
#
#    Then I delete the test data
#
#    And I will remove all data with region "/plan/cns_material_plan_status"
#
#    And I will remove all data with region "/plan/edm_failed_data"
