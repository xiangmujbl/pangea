@pangea_test @AEAZ-2530
Feature: OMPGdmSalesHistory AEAZ-2530

  Scenario: Full Load curation

    And I will remove all data with region "/plan/edm_failed_data"

    Given I import "/edm/sales_order_v1" by keyFields "sourceSystem,salesOrderNo,salesOrderItem"
      | sourceSystem | salesOrderNo | salesOrderItem | scheduleLineItem | localSalesOrg | localShipToParty | localOrderCreateDt | localOrderType | localPlant | localMaterialNumber | localItemCategory | localSDItemCurrency | localRequestedDate | localRejReason | salesOrderQty | localNumtoBase | localDentoBase | localRoute |
      | CONS_LATAM   | 0008288858   | 000003         |                  | BR01          | 0000177376       | 20190402           | ZSRT           | BR08       | 000000000000085891  | ZSRT              | BR08                | 20190402           |                | 1.000         | 1              | 1              | B08002     |
      | CONS_LATAM   | 0008288859   | 000003         |                  | BR01          | 0000118476       | 20190402           | ZSRT           | BR08       | 000000000000081097  | ZSRT              | BR08                | 20190402           |                | 2.000         | 1              | 1              | B08004     |
      | CONS_LATAM   | 0008288860   | 000001         |                  | BR01          | 0000185314       | 20190402           | ZSRT           | BR08       | 000000000000081222  | ZSRT              | BR08                | 20190402           |                | 2.000         | 1              | 1              | B08010     |
      | CONS_LATAM   | 0008288860   | 000001         |                  | BR01          | 0000185315       | 20190402           | ZSRT           | BR08       | 000000000000081222  | ZSRT              | BR08                | 20190402           |                | 2.000         | 1              | 1              | B08010     |
      | CONS_LATAM   | 0008288860   | 000002         |                  | BR01          | 0000185315       | 20190402           | ZSRT           | BR08       | 000000000000081222  | ZSRT              | BR08                | 20190402           |                | 2.000         | 1              | 1              | B08010     |
      | CONS_LATAM   | 0008288860   | 000003         |                  | BR01          | 0000185316       | 20190402           | ZSRT           | BR08       | 000000000000081222  | ZSRT              | BR08                | 20190402           |                | 2.000         | 1              | 1              | B08010     |
      | CONS_LATAM   | 0008288860   | 000004         |                  | BR01          | 0000185314       | 20190402           | LOT4           | BR08       | 000000000000081222  | LIC4              | BR08                | 20190402           |                | 2.000         | 1              | 1              | B08010     |
      | CONS_LATAM   | 0008288860   | 000007         |                  | BR01          | 0000185314       | 20190402           | ZSRT           | BR08       | 000000000000081222  | ZSRT              | BR08                | 20190402           | LRR01          | 2.000         | 1              | 1              | B08010     |
      | CONS_LATAM   | 0008288860   | 000008         |                  | BR01          | 0000185314       | 20190402           | ZSRT           | BR08       | 000000000000081222  | ZSRT              | BR08                | 20190402           | LRR02          | 2.000         | 1              | 1              | B08010     |

    And I wait "/edm/sales_order_v1" Async Queue complete
    Given I import "/plan/cns_cust_excl" by keyFields "salesOrg,customerShipTo"
      | salesOrg | customerShipTo |
      | BR01     | 116538         |
      | BR01     | 124092         |
      | BR01     | 124094         |
    And I wait "/plan/cns_cust_excl" Async Queue complete
    Given I import "/plan/cns_plan_parameter" by keyFields "sourceSystem,dataObject,attribute,parameter"
      | sourceSystem | dataObject       | attribute      | parameter | inclExcl | parameterValue |
      | CONS_LATAM   | CNS_SalesHistory | restrictSelect | lessMonth | I        | 1              |
      | CONS_LATAM   | CNS_SalesHistory | initialSelect  | lessMonth | I        | 2              |
    And I wait "/plan/cns_plan_parameter" Async Queue complete

    Given I import "/plan/cns_so_type_incl" by keyFields "salesOrg,orderType,country"
      | salesOrg | orderType | country |
      | BR01     | ZSRT      | BR      |
      | BR01     | LOT4      | BR      |
    And I wait "/plan/cns_so_type_incl" Async Queue complete

    Given I import "/edm/plant_v1" by keyFields "localPlant"
      | localPlant | country |
      | BR08       | BR      |
    And I wait "/edm/plant_v1" Async Queue complete
    Given I import "/plan/cns_material_plan_status" by keyFields "localMaterialNumber,localPlant"
      | localPlant | localMaterialNumber | dpRelevant |
      | BR08       | 000000000000085891  | X          |
      | BR08       | 000000000000081097  | X          |
      | BR08       | 000000000000081222  | X          |
    And I wait "/plan/cns_material_plan_status" Async Queue complete
    Given I import "/plan/cns_cert_deter" by keyFields "salesOrg"
      | certaintyKey | salesOrg | orderType | itemCategory |
      | FOC          | BR01     | ZSRT      | ZSRT         |
      | FOC          | BR01     | ZSRT      | LIC1         |
      | FOC          | BR01     | LOT2      | ZSRT         |
    And I wait "/plan/cns_cert_deter" Async Queue complete
    Given I import "/edm/currency_v1" by keyFields "localCurrency"
      | currencyCode | localCurrency |
      | ADP          | BR08          |
      | AED          | AED           |
      | AFA          | AFA           |
    And I wait "/edm/currency_v1" Async Queue complete
    Given I import "/plan/cns_dem_grp_asgn" by keyFields "customerId"
      | demandGroup | customerId | salesOrganization |
      | 76100007    | 0000177376 | BR01              |
      | 76100008    | 0000118476 | BR01              |
      | 76100009    | 0000185314 | BR01              |
      | 76100010    | HK0001     | BR01              |
      | 76100011    | 0000185316 | BR01              |
    And I wait "/plan/cns_dem_grp_asgn" Async Queue complete
    Given I import "/project_one/knvh" by keyFields "kunnr"
      | kunnr      | vkorg | datbi    | hkunnr   |
      | 0000185315 | BR01  | 20190512 | HK0001   |
      | 0000185316 | BR01  | 20190512 | HK0002   |
      | KOO2       | HK002 | VK       | 20170512 |
      | K003       | HK003 | VK       | 20170512 |
    And I wait "/project_one/knvh" Async Queue complete
    Given I import "/project_one/tvro" by keyFields "route"
      | trazt | route  |
      | 30    | B08002 |
      | 60    | B08004 |
      | 90    | B08010 |
    And I wait "/project_one/tvro" Async Queue complete
    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber"
      | localMaterialNumber | primaryPlanningCode | localBaseUom |
      | 000000000000085891  | PPC01               | KI           |
      | 000000000000081097  | PPC02               | KI           |
      | 000000000000081222  | PPC03               | KI           |
    And I wait "/edm/material_global_v1" Async Queue complete
    Given I import "/plan/cns_ord_rej" by keyFields "salesOrg"
      | salesOrg | rejCd |
      | BR01     | LRR01 |
      | BR01     | 92    |
      | BR01     | 93    |
    And I wait "/plan/cns_ord_rej" Async Queue complete
    Given I import "/plan/cns_plan_unit" by keyFields "unit"
      | unit | localUom |
      | CA   | KI       |
    And I wait "/plan/cns_plan_unit" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmSalesHistory.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMSaleshistory.tsv"

    Then I check file data for filename "GDMSaleshistory.tsv" by keyFields "salesHistoryId,activeFCTERP"
      | salesHistoryId   | activeFCTERP | certaintyId | conversionFactorXx | currencyId | customerId | demandStreamId | dueDate           | forecastItemId | fromDueDate       | locationId      | orderReason | orderStatus | orderSubType | orderType | productId | quantity | salesUnit | unitId | validValueXx |
      | 0008288860000001 | YES          | FOC         |                    | ADP        | 76100009   |                | 20190102 00:00:00 |                | 20190102 00:00:00 | CONS_LATAM_BR08 |             |             |              | ZSRT      | PPC03     | 2.0      | CA        |        |              |
      | 0008288859000003 | YES          | FOC         |                    | ADP        | 76100008   |                | 20190201 00:00:00 |                | 20190201 00:00:00 | CONS_LATAM_BR08 |             |             |              | ZSRT      | PPC02     | 2.0      | CA        |        |              |
      | 0008288858000003 | YES          | FOC         |                    | ADP        | 76100007   |                | 20190303 00:00:00 |                | 20190303 00:00:00 | CONS_LATAM_BR08 |             |             |              | ZSRT      | PPC01     | 1.0      | CA        |        |              |
      | 0008288860000002 | YES          | FOC         |                    | ADP        | 76100010   |                | 20190102 00:00:00 |                | 20190102 00:00:00 | CONS_LATAM_BR08 |             |             |              | ZSRT      | PPC03     | 2.0      | CA        |        |              |
      | 0008288860000003 | YES          | FOC         |                    | ADP        | 76100011   |                | 20190102 00:00:00 |                | 20190102 00:00:00 | CONS_LATAM_BR08 |             |             |              | ZSRT      | PPC03     | 2.0      | CA        |        |              |
      | 0008288860000004 | YES          | BASE        |                    | ADP        | 76100009   |                | 20190102 00:00:00 |                | 20190102 00:00:00 | CONS_LATAM_BR08 |             |             |              | LOT4      | PPC03     | 2.0      | CA        |        |              |
      | 0008288860000007 | YES          | FOC         |                    | ADP        | 76100009   |                | 20190102 00:00:00 |                | 20190102 00:00:00 | CONS_LATAM_BR08 |             |             |              | ZSRT      | PPC03     | 0        | CA        |        |              |
      | 0008288860000008 | YES          | FOC         |                    | ADP        | 76100009   |                | 20190102 00:00:00 |                | 20190102 00:00:00 | CONS_LATAM_BR08 |             |             |              | ZSRT      | PPC03     | 0        | CA        |        |              |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/edm/sales_order_v1" and "/omp/gdm_sales_history,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/omp/gdm_sales_history"

    And I will remove all data with region "/plan/edm_failed_data"

    And I will remove the test file on sink application "GDMSaleshistory.tsv"

