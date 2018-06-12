@pangea_test @AEAZ-493
Feature: EDMSalesOrder AEAZ-493

  Scenario: Full Load curation
    #1.test get localSourceSystem from source_system_v1 and overwrite the value in this field (rule T1)
    #2.test get attributes from VBAP and overwrite the value in this field (rule J1)
    #3.test get attributes from VBEP and overwrite the value in this field (rule J2)
    #4.test get kunnr from VBPA and overwrite the value in this field (rule J3)
    #5.test get attributes from VBKD and overwrite the value in this field (rule J4)

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | sourceSystem |
      | project_one       | CONS_LATAM   |
      | [EMS]             | EMS          |
      | project_two       | CONS_LATAM   |
    And I wait "/edm/source_system_v1" Async Queue complete

    And I import "/project_one/vbak" by keyFields "vbeln"
      | vbeln      | erdat    | audat    | guebg    | gueen    | vbtyp | auart | augru | lifsk | faksk | vkorg  | vtweg | spart | vkgrp | vkbur | vdatu    | bstnk | kunnr      | aedat    | kalsm  |
      | 0000001250 | 20001005 | 20001005 | 00000000 | 00000000 | I     | ZSR   | 311   |       |       | 000000 | 10    | 10    | B99   | BR90  | 20001010 | 1250  | 0000113212 | 20001005 | ZSREMT |
      | 0000001979 | 20001005 | 20001005 | 00000000 | 00000000 | C     | ZSR   | 311   |       |       | 000000 | 10    | 10    | B99   | BR90  | 20001010 | 1250  | 0000113212 | 20001005 | ZSREMT |
      | 0000001980 | 20001005 | 20001005 | 00000000 | 00000000 | C     | ZSR   | 311   |       |       | 000000 | 10    | 10    | B99   | BR90  | 20001010 | 1250  | 0000113212 | 20001005 | ZSREMT |
      | 0000001981 | 20001005 | 20001005 | 00000000 | 00000000 | C     | ZSR   | 311   |       |       | 000000 | 10    | 10    | B99   | BR90  | 20001010 | 1250  | 0000113212 | 20001005 | ZSREMT |
      | 0000001982 | 20001005 | 20001005 | 00000000 | 00000000 | C     | ZSR   | 311   |       |       | 000000 | 10    | 10    | B99   | BR90  | 20001010 | 1250  | 0000113212 | 20001005 | ZSREMT |
      | 0000001989 | 20001005 | 20001005 | 00000000 | 00000000 | C     | ZSR   | 311   |       |       | 000000 | 10    | 10    | B99   | BR90  | 20001010 | 1250  | 0000113212 | 20001005 | ZSREMT |
    And I wait "/project_one/vbak" Async Queue complete

    And I import "/project_one/vbap" by keyFields "posnr,vbeln"
      | vbeln      | posnr  | matnr              | werks | pstyv | lfrel | fkrel | abgru | kwmeng | vrkme | umvkz | umvkn | faksp | netwr | waerk | lgort | vstel | route  |
      | 0000001250 |        | 000000000000005911 | 0.0   | 0.00  |       |       |       | 2.000  | 0.00  |       | 1     |       |       |       | KI    | 2.000 | BR2042 |
      | 0000001946 | 000001 | 000000000000005912 | 0.0   | 0.00  |       |       |       | 2.000  | 0.00  |       | 1     |       |       |       | KI    | 2.000 | BR2042 |
      | 0000001980 |        | 000000000000005912 | 0.0   | 0.00  |       |       |       | 2.000  | 0.00  |       | 1     |       |       |       | KI    | 2.000 | BR2042 |
      | 0000001981 |        | 000000000000005912 | 0.0   | 0.00  |       |       |       | 2.000  | 0.00  |       | 1     |       |       |       | KI    | 2.000 | BR2042 |
      | 0000001982 |        | 000000000000005912 | 0.0   | 0.00  |       |       |       | 2.000  | 0.00  |       | 1     |       |       |       | KI    | 2.000 | BR2042 |
      | 0000001989 |        | 000000000000005912 | 0.0   | 0.00  |       |       |       | 2.000  | 0.00  |       | 1     |       |       |       | KI    | 2.000 | BR2042 |
    And I wait "/project_one/lips" Async Queue complete

    And I import "/project_one/vbep" by keyFields "posnr,vbeln,etenr"
      | vbeln      | posnr  | etenr | edatu    | wmeng   | bmeng |
      | 0000001250 |        | 0001  | 20001005 | 110.000 | 0.000 |
      | 0000001980 | 000001 | 0001  | 20001005 | 110.000 | 0.000 |
      | 0000001983 |        | 0001  | 20001005 | 110.000 | 0.000 |
      | 0000001979 | 000001 | 0001  | 20001005 | 110.000 | 0.000 |
      | 0000001988 |        | 0001  | 20001005 | 110.000 | 0.000 |
    And I wait "/project_one/vbep" Async Queue complete

    And I import "/project_one/vbpa" by keyFields "posnr,vbeln,parvw"
      | vbeln      | posnr  | parvw | kunnr      |
      | 0000001250 |        | WE    | 0000003883 |
      | 0000001946 | 000002 | 1B    | 0000003883 |
    And I wait "/project_one/vbpa" Async Queue complete

    And I import "/project_one/vbkd" by keyFields "posnr,vbeln"
      | vbeln      | posnr  | inco1 | inco2 | kdgrp |
      | 0000001250 |        | CIF   | CIF   |       |
      | 0000001947 | 000002 | CIF   | CIF   |       |
    And I wait "/project_one/vbkd" Async Queue complete

    When I submit task with xml file "xml/edm/EDMSalesOrder.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/sales_order_v1" by keyFields "sourceSystem,salesOrderNo,salesOrderItem,scheduleLineItem"
      | sourceSystem | salesOrderNo | salesOrderItem | scheduleLineItem | localOrderCreateDt | localOrderDate | localValidFromDt | localValidToDt | localDocumentCateg | localOrderType | localOrderReason | localDeliveryBlock | localBillingBlock | localSalesOrg | localDistrChannel | localDivision | localSalesGroup | localSalesOffice | localRequestedDate | localCustomerPO | localSoldToParty | localShipToParty | localChangeDt | localMaterialNumber | localPlant | localItemCategory | localItemDlvRlvnt | localItemBillRlvnt | localRejReason | salesOrderQty | localSalesUnit | localNumtoBase | localDentoBase | localBillingBlockItem | localSDItemValue | localSDItemCurrency | localStorageLocation | localShippingPoint | localRoute | localScheduleLineDate | localSchLineQty | localSchLineConfimQty | localCustomerGroup | localPricingProcedure | localIncoTerms1 | localIncoTerms2 |
      | CONS_LATAM   | 0000001250   |                | 0001             | 20001005           | 20001005       | 00000000         | 00000000       | I                  | ZSR            | 311              |                    |                   | 000000        | 10                | 10            | B99             | BR90             | 20001010           | 1250            | 0000113212       | 0000003883       | 20001005      | 000000000000005911  | 0.0        | 0.00              |                   |                    |                | 2.000         | 0.00           |                | 1              |                       |                  |                     | KI                   | 2.000              | BR2042     | 20001005              | 110.000         | 0.000                 |                    | ZSREMT                | CIF             | CIF             |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID   | errorCode | sourceSystem | businessArea | key1       | key2 | key3 | key4 | key5 | errorValue              |
      | DP             | EDMSalesOrder | J1        |              |              | 0000001979 |      |      |      |      | No Sales Item Found     |
      | DP             | EDMSalesOrder | J2        |              |              | 0000001980 |      |      |      |      | No Schedule lines Found |
      | DP             | EDMSalesOrder | J2        |              |              | 0000001981 |      |      |      |      | No Schedule lines Found |
      | DP             | EDMSalesOrder | J2        |              |              | 0000001982 |      |      |      |      | No Schedule lines Found |
      | DP             | EDMSalesOrder | J2        |              |              | 0000001989 |      |      |      |      | No Schedule lines Found |

    And I compare the number of records between "/project_one/vbak" and "/edm/sales_order_v1,/plan/edm_failed_data"

    Then I delete the test data

    And I will remove all data with region "/edm/sales_order_v1"

    And I will remove all data with region "/plan/edm_failed_data"
