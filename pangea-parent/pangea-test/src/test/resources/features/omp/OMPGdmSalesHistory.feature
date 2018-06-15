@pangea_test @AEAZ-6924
Feature: OMPGdmSalesHistory AEAZ-6924

  Scenario: Full Load curation

    And I will remove the test file on sink application "GDMSalesHistory.tsv"

    Given I import "/edm/sales_order_v1" by keyFields "sourceSystem,salesOrderNo,salesOrderItem"
      | sourceSystem | salesOrderNo | salesOrderItem | scheduleLineItem | localSalesOrg | localShipToParty | localOrderCreateDt | localOrderType | localPlant | localMaterialNumber | localItemCategory | localSDItemCurrency | localRequestedDate | localRejReason | salesOrderQty | localNumtoBase | localDentoBase | localRoute |
      | CONS_LATAM   | 0008288858   | 000003_D1      | 0001             | BR01          | 0000177376       | 20190402           | ZSRT           | BR08       | 000000000000085891  | ZSRT              | BR08                | 20190402           |                | 1.000         | 1              | 1              | B08002     |
      | CONS_LATAM   | 0008288859   | 000003_T3      | 0002             | BR01          | 0000118476       | 20190402           | ZSRT           | BR08       | 000000000000081097  | ZSRT              | BR08                | 20190402           |                | 2.000         | 1              | 1              | B08004     |
      | CONS_LATAM   | 0008288860   | 000001_T4      | 0003             | BR01          | 0000185314       | 20190402           | ZSRT           | BR08       | 000000000000081222  | ZSRT              | BR08                | 20190402           |                | 2.000         | 1              | 1              | B08010     |
      | CONS_LATAM   | 0008288862   | 000002_T7      | 0004             | BR01          | 0000185315       | 20190402           | ZSRT           | BR08       | 000000000000081222  | ZSRT              | BR08                | 20190402           |                | 2.000         | 1              | 1              | B08010     |
      | CONS_LATAM   | 0008288863   | 000003_T8      | 0005             | BR01          | 0000185316       | 20190402           | ZSRT           | BR08       | 000000000000081222  | ZSRT              | BR08                | 20190402           |                | 2.000         | 1              | 1              | B08010     |
      | CONS_LATAM   | 0008288864   | 000004_J2      | 0006             | BR01          | 0000185314       | 20190402           | LOT4           | BR08       | 000000000000081222  | LIC4              | BR08                | 20190402           |                | 2.000         | 1              | 1              | B08010     |

      | CONS_LATAM   | 0008288874   | 000014_T9      | 0005             | BR01          | 0000185316       | 20190402           | ZSRT           | BR08       | 000000000000081222  | ZSRT              | BR08                | 20190402           |                | 2.000         | 1              | 1              | B08010     |
      | CONS_LATAM   | 0008288875   | 000015_T10     | 0006             | BR01          | 0000185314       | 20190402           | LOT4           | BR08       | 000000000000081222  | LIC4              | BR08                | 20190402           |                | 2.000         | 1              | 1              | B08010     |

      | CONS_LATAM   | 0008288865   | 000005_T2      | 0007             | BR01          | 0000185314       | 20190402           | LOT5           | BR08       | 000000000000081222  | LIC5              | BR08                | 20190402           |                | 2.000         | 1              | 1              | B08010     |
      | CONS_LATAM   | 0008288866   | 000006_T2      | 0008             | BR01          | 0000185314       | 20190402           | LOT6           | BR08       | 000000000000081222  | LIC6              | BR08                | 20190402           |                | 2.000         | 1              | 1              | B08010     |

      | CONS_LATAM   | 0008288867   | 000007_T5      | 0009             | BR01          | 0000185314       | 20190402           | ZSRT           | BR08       | 000000000000081222  | ZSRT              | BR08                | 20190402           | LRR01          | 2.000         | 1              | 1              | B08010     |
      | CONS_LATAM   | 0008288868   | 000008_T5      | 0010             | BR01          | 0000185314       | 20190402           | ZSRT           | BR08       | 000000000000081222  | ZSRT              | BR08                | 20190402           | LRR02          | 2.000         | 1              | 1              | B08010     |

      | CONS_LATAM   | 0008288869   | 000009_J1      | 0011             | BR01          | 0000185314       | 20190402           | LOT9           | BR08       | 000000000000081222  | ZSRT              | BR08                | 20190402           | LRR02          | 2.000         | 1              | 1              | B08010     |
      | CONS_LATAM   | 0008288870   | 000010_J1      | 0012             | BR01          | 0000185314       | 20190402           | ZSRT           | BR10       | 000000000000081222  | ZSRT              | BR08                | 20190402           | LRR02          | 2.000         | 1              | 1              | B08010     |

      | CONS_LATAM   | 0008288871   | 000011_T4      | 0013             | BR01          | 0000185314       | 20190402           | ZSRT           | BR08       | 000000000000081222  | ZSRT              | BR11                | 20190402           | LRR02          | 2.000         | 1              | 1              | B08010     |
      | CONS_LATAM   | 0008288872   | 000012_T5      | 0014             | BR01          | 0000185312       | 20190402           | ZSRT           | BR08       | 000000000000081222  | ZSRT              | BR08                | 20190402           | LRR02          | 2.000         | 1              | 1              | B08010     |
      | CONS_LATAM   | 0008288873   | 000013_T8      | 0015             | BR01          | 0000185314       | 20190402           | ZSRT           | BR08       | 000000000000081223  | ZSRT              | BR08                | 20190402           |                | 2.000         | 1              | 1              | B08010     |
    And I wait "/edm/sales_order_v1" Async Queue complete

    Given I import "/plan/cns_cust_excl" by keyFields "salesOrg,customerShipTo"
      | salesOrg | customerShipTo | inclExcl |
      | BR01     | 0000185314     | I        |
      | BR01     | 116538         | E        |
      | BR01     | 124092         | I        |
      | BR01     | 124094         | I        |
    And I wait "/plan/cns_cust_excl" Async Queue complete

    Given I import "/plan/cns_plan_parameter" by keyFields "sourceSystem,dataObject,attribute,parameter"
      | sourceSystem | dataObject       | attribute      | parameter | inclExcl | parameterValue |
      | CONS_LATAM   | CNS_SalesHistory | restrictSelect | lessMonth | I        | 1095           |
      | CONS_LATAM   | CNS_SalesHistory | initialSelect  | lessMonth | I        | 1095           |
    And I wait "/plan/cns_plan_parameter" Async Queue complete

    Given I import "/plan/cns_so_type_incl" by keyFields "salesOrg,orderType,country"
      | salesOrg | orderType | country | inclExcl |
      | BR01     | ZSRT      | BR      | I        |
      | BR01     | LOT4      | BR      | I        |
      | BR01     | LOT5      | BR      | I        |
      | BR01     | LOT6      | BR      | I        |
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
      | BR08       | 000000000000081223  | X          |
    And I wait "/plan/cns_material_plan_status" Async Queue complete

    Given I import "/plan/cns_cert_deter" by keyFields "salesOrg"
      | certaintyKey | salesOrg | orderType | itemCategory |
      | FOC          | BR01     | ZSRT      | ZSRT         |
      | FOC          | BR01     | ZSRT      | LIC5         |
    And I wait "/plan/cns_cert_deter" Async Queue complete

    Given I import "/edm/currency_v1" by keyFields "localCurrency"
      | currencyCode | localCurrency |
      | ADP          | BR08          |
      | AED          | AED           |
      | AFA          | AFA           |
    And I wait "/edm/currency_v1" Async Queue complete

    Given I import "/plan/cns_dem_grp_asgn" by keyFields "customerId"
      | demandGroup | customerId | salesOrg |
      | 76100007    | 0000177376 | BR01     |
      | 76100008    | 0000118476 | BR01     |
      | 76100009    | 0000185314 | BR01     |
      | 76100010    | HK0001     | BR01     |
      | 76100011    | 0000185316 | BR01     |
    And I wait "/plan/cns_dem_grp_asgn" Async Queue complete

    Given I import "/project_one/knvh" by keyFields "kunnr"
      | kunnr      | vkorg | datbi    | hkunnr   |
      | 0000185315 | BR01  | 20190512 | HK0001   |
      | 0000185316 | BR01  | 20190512 | HK0002   |
      | 0000185312 | BR01  | 20190512 | HK0003   |
      | KOO2       | HK002 | VK       | 20170512 |
      | K003       | HK003 | VK       | 20170512 |
    And I wait "/project_one/knvh" Async Queue complete

    Given I import "/project_one/tvro" by keyFields "route"
      | traztd | route  |
      | 240000 | B08002 |
      | 480000 | B08004 |
      | 720000 | B08010 |
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

    Then A file is found on sink application with name "GDMSalesHistory.tsv"

    Then I check file data for filename "GDMSalesHistory.tsv" by keyFields "salesHistoryId,activeFCTERP"
      | salesHistoryId       | activeFCTERP | certaintyId | currencyId | customerId | dueDate             | fromDueDate         | locationId      | productId | quantity | salesUnit |
      | 0008288860000001_T4  | YES          | FOC         | ADP        | 76100009   | 2019/03/30 00:00:00 | 2019/03/30 00:00:00 | CONS_LATAM_BR08 | PPC03     | 2.0      | CA        |
      | 0008288859000003_T3  | YES          | FOC         | ADP        | 76100008   | 2019/03/31 00:00:00 | 2019/03/31 00:00:00 | CONS_LATAM_BR08 | PPC02     | 2.0      | CA        |
      | 0008288858000003_D1  | YES          | FOC         | ADP        | 76100007   | 2019/04/01 00:00:00 | 2019/04/01 00:00:00 | CONS_LATAM_BR08 | PPC01     | 1.0      | CA        |
      | 0008288862000002_T7  | YES          | FOC         | ADP        | 76100010   | 2019/03/30 00:00:00 | 2019/03/30 00:00:00 | CONS_LATAM_BR08 | PPC03     | 2.0      | CA        |
      | 0008288863000003_T8  | YES          | FOC         | ADP        | 76100011   | 2019/03/30 00:00:00 | 2019/03/30 00:00:00 | CONS_LATAM_BR08 | PPC03     | 2.0      | CA        |
      | 0008288864000004_J2  | YES          | BASE        | ADP        | 76100009   | 2019/03/30 00:00:00 | 2019/03/30 00:00:00 | CONS_LATAM_BR08 | PPC03     | 2.0      | CA        |

      | 0008288865000005_T2  | YES          | FOC         | ADP        | 76100009   | 2019/03/30 00:00:00 | 2019/03/30 00:00:00 | CONS_LATAM_BR08 | PPC03     | 2.0      | CA        |
      | 0008288866000006_T2  | YES          | BASE        | ADP        | 76100009   | 2019/03/30 00:00:00 | 2019/03/30 00:00:00 | CONS_LATAM_BR08 | PPC03     | 2.0      | CA        |

      | 0008288867000007_T5  | YES          | FOC         | ADP        | 76100009   | 2019/03/30 00:00:00 | 2019/03/30 00:00:00 | CONS_LATAM_BR08 | PPC03     | 0        | CA        |
      | 0008288868000008_T5  | YES          | FOC         | ADP        | 76100009   | 2019/03/30 00:00:00 | 2019/03/30 00:00:00 | CONS_LATAM_BR08 | PPC03     | 0        | CA        |
      | 0008288874000014_T9  | YES          | FOC         | ADP        | 76100011   | 2019/03/30 00:00:00 | 2019/03/30 00:00:00 | CONS_LATAM_BR08 | PPC03     | 2.0      | CA        |
      | 0008288875000015_T10 | YES          | BASE        | ADP        | 76100009   | 2019/03/30 00:00:00 | 2019/03/30 00:00:00 | CONS_LATAM_BR08 | PPC03     | 2.0      | CA        |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID        | errorCode | sourceSystem | businessArea | key1       | key2      | key3 | key4       | key5 | errorValue                                  |
      | DP             | OMPGdmSalesHistory | T4        | CONS_LATAM   |              | 0008288871 | 000011_T4 | 0013 | 0000185314 | BR01 | Unable to find the Enterprise currency code |
      | DP             | OMPGdmSalesHistory | T5        | CONS_LATAM   |              | 0008288872 | 000012_T5 | 0014 | 0000185312 | BR01 | Demand group can not be determined          |
      | DP             | OMPGdmSalesHistory | T8        | CONS_LATAM   |              | 0008288873 | 000013_T8 | 0015 | 0000185314 | BR01 | Material not found in material global       |

    And I compare the number of records between "/edm/sales_order_v1" and "/omp/gdm_sales_history,/plan/edm_failed_data"

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/omp/gdm_sales_history"

    And I will remove all data with region "/plan/edm_failed_data"


