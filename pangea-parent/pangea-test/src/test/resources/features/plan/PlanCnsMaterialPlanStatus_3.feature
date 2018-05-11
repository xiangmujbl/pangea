@pangea_test @AEAZ-3216
Feature: CnsMaterialPlanStatus AEAZ-2716


  Scenario: Full Load curation
      And I will remove the test file on sink application "PlanCnsMaterialPlanStatus_3.tsv"

    Given I import "/edm/sales_order_v1" by keyFields "salesOrderItem,salesOrderNo,scheduleLineItem,sourceSystem"
    |salesOrderItem|salesOrderNo|scheduleLineItem|sourceSystem|localPlant|localMaterialNumber|localSalesOrg |localOrderType|localShipToParty|localOrderCreateDt|
    |000001        |000001       |0001           |CONS_LATAM  |VE06      |000000000000056504 |VE03           |  ZLSR       |    123451223   |20150408           |
    |000002        |000002       |0002           |CONS_LATAM  |VE07      |111111111111122222 |BR01           |  ZBEF       |    78623213111 |20150408           |
    |000003        |000003       |0003           |CONS_LATAM  |VE05_F1   |000000000000213998 |VE03           |  ZLSR       |    0000174002  |20150408           |
    |000004        |000004       |0004           |CONS_LATAM  |VE08      |0000000000002139_F1|VE03           |  ZLSR       |    0000174002  |20150408           |
    |000005        |000005       |0005           |CONS_LATAM  |VE06      |000000000000056504 |BR01_F2        |  ZLSR       |    0000174002  |20150408           |
    |000006        |000006       |0006           |CONS_LATAM  |VE08      |000000000000213998 |VE03_J1        |  ZLSR       |    123451223   |20150408           |
    |000007        |000007       |0007           |CONS_LATAM  |VE08      |162312313231112312 |ED01           |  EDCD       |    123451223   |20150408           |
    |000008        |000008       |0008           |CONS_LATAM  |VE09      |162312313231112312_J2 |ED01           |  EDCD       |    123451223   |20150408           |
    |000009        |000009       |0009           |CONS_LATAM  |VE10      |162312313231112312_T2&&T3 |ED01           |  EDCD       |    123451223   |20150408           |

    And I import "/plan/cns_plan_parameter" by keyFields "attribute,dataObject,parameter,parameterValue,sourceSystem"
        | attribute       |	dataObject            |	inclExcl|	parameter|	parameterValue|	sourceSystem|
         | DPRelevant      |cns_material_plan_status  | I       |   Plant    |   VE10         | CONS_LATAM  |
        | DPRelevant      |cns_material_plan_status  | I       |   Plant    |   VE07         | CONS_LATAM  |
        | DPRelevant      |cns_material_plan_status  | I       |   Plant    |   VE08         | CONS_LATAM  |
       | DPRelevant      |cns_material_plan_status  | I       |   Plant    |   VE09         | CONS_LATAM  |
        | DPRelevant      |cns_material_plan_status  | I       |   Plant    |   VE06         | CONS_LATAM  |
        | DPRelevant_F1   |cns_material_plan_status  | I       |   Plant    |   VE06         | CONS_LATAM  |
        | DPRelevant      |cns_material_plan_F1      | I       |   Plant    |   VE06         | CONS_LATAM  |
        | DPRelevant      |cns_material_plan_F1      | E       |   Plant    |   VE05_F1         | CONS_LATAM  |
        | DPRelevant      |cns_material_plan_status  |E_F1     |   Plant    |   VE05         | CONS_LATAM  |
        | LocalMaterialNumber|cns_product_inclusion  |  I      |  MRPType   |   PD           | CONS_LATAM  |
        | LocalMaterialNumber|cns_product_inclusion  | E_F2    |  MRPType   |   PRD          | CONS_LATAM  |
        | LocalMaterialNumber|cns_product_inclusion  |  I      |  MRPType   |   PR         | CONS_LATAM  |
        | LessDays            |cns_product_inclusion |  I       |  LessDays |   1200         | CONS_LATAM  |
        | LessDays            |cns_product_inclusion |  E_J1    |  LessDays |   1300         | CONS_LATAM  |

   And I import "/edm/material_plant_v1" by keyFields "localMaterialNumber,localPlant"
              | localMaterialNumber | localPlant | materialNumber | localMrpType |
              | 000000000000056504  | ND	     | -              | PD           |
              | 111111111111122222  | BR12       | -              | PR           |
              | 0000000000002139_F1 | PD23       | -              | PD23         |
              | 000000000000213998  | PD23        | -              | PD           |
              | 162312313231112312  | PD23        | -              | PD           |


   And I import "/plan/cns_cust_excl" by keyFields "country,customerShipTo,salesOrg,sourceSystem"
            |country|customerShipTo|salesOrg|sourceSystem|
            | BR	 |116538	    |VE03	 |CON_LATAM|
            | BR	 |0000174002    |BR01_F2 |CON_LATAM|
            | BR	 |178991	    |BR01	 |CON_LATAM|

   And I import "/edm/source_system_v1" by keyFields "localSourceSystem"
          | localSourceSystem | localSourceSystemName | sourceSystem | sourceSystemName   |
          | Project_One       | Project One           | CONS_LATAM   | Consumer Latam Ent |
          | project_two       | Project Two           | CONS_LATAM   | Consumer Latam Ent |
   And I wait "/edm/source_system_v1" Async Queue complete


   And I import "/edm/material_global_v1" by keyFields "localMaterialNumber"
      | localMaterialType | localMaterialNumber | materialNumber |localDpParentCode | primaryPlanningCode |
      | ROH               | 000000000000056504  |   11111        | 178962124094540036|       945          |
      | HALB              | 111111111111122222  |   22222        |   27896212        |      978           |
      | HALB              | 000000000000213998  |   66666        |  37896212         |     988             |
      | FBLB              | 162312313231112312  |   44444        |  37896212         |     990             |
      | CBBBB             |  162312313231112312_T2&&T3  |   5555        |  37896212         |     1090|



      And I wait "/edm/material_global_v1" Async Queue complete

    And I import "/plan/cns_so_type_incl" by keyFields "country,orderType,salesOrg,sourceSystem"
     |country|orderType|salesOrg|sourceSystem|
     |BR	 |ZLSR     |VE03    | CONS_LATAM   |
     |CN	 |ZBEF     |BR01    | CONS_LATAM   |
     |BC	 |ZBEF     |BR01_J1  | CONS_LATAM   |
     |JN	 |EDCD     |ED01  | CONS_LATAM   |


    And I wait "/plan/cns_so_type_incl" Async Queue complete

    And I import "/edm/plant_v1" by keyFields "localPlant,sourceSystem"
        |localPlant|sourceSystem|country |
        |VE06	   |ZLSR        |BR      |
        |VE07	   |ZBEF        |CN      |
        |VE08	   |ZBEF        |JN      |
        |VE09	   |ZBEF        |JN      |
        |VE10	   |ZBEF        |JN      |



     And I wait "/edm/plant_v1" Async Queue complete



      And I import "/plan/cns_material_plan_status" by keyFields "sourceSystem,localMaterialNumber,localPlant"
     | sourceSystem | localMaterialNumber | localPlant  | dpRelevant |  parentActive |
     |PLAN_DD       | 000000000000056504  | VE06        |X          |       X        |
     |TTTT_DA       | 111111111111122222  | VE07        |X          |       X        |
     |BBBB_GD       | 162312313231112312  | VE08        |X          |       X        |
     |CCCC_AD       | 162312313231112312_J2| VE09        |X          |       X        |
     |CCCC_AD       | 162312313231112312_T2&&T3| VE10        |X          |       X        |


      And I wait "/plan/cns_material_plan_status" Async Queue complete



     When I submit task with xml file "xml/plan/PlanCnsMaterialPlanStatus_3.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "PlanCnsMaterialPlanStatus_3.tsv"



    Then I check file data for filename "PlanCnsMaterialPlanStatus_3.tsv" by keyFields "sourceSystem,localMaterialNumber,localPlant"
       |sourceSystem |localMaterialNumber  |localPlant  |materialNumber|localParentCode   |ppc|active|dpRelevant|spRelevant|parentActive|noPlanRelevant|
       |CONS_LATAM   |000000000000056504   |VE06        |11111         |178962124094540036|945|X     | X        |          |   X        |               |
       |CONS_LATAM   |111111111111122222   |VE07        |22222         |27896212          |978|X     | X        |          |   X        |               |
       |CONS_LATAM   |162312313231112312   |VE08        |  44444       |37896212          |990|X     | X        |          |   X        |               |
       |CONS_LATAM   |162312313231112312_J2 |VE09       |              |                  |   |X     | X        |          |           |               |
       |CONS_LATAM   |162312313231112312_T2&&T3 |VE10   |  5555        |                  |   |X     | X        |          |            |               |


 Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

#    And I compare the number of records between "/edm/plant_v1" and "/plan/cns_plant_attr,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/plan/cns_material_plan_status"

    And I will remove all data with region "/plan/edm_failed_data"