@pangea_test @AEAZ-6828
Feature: OMPGdmDemand AEAZ-6828

  Scenario: Full Load Consumption

    Given I import "/edm/outbound_delivery_header_v1" by keyFields "srcSysCd,delvDocId,delvTypeCd"
      | srcSysCd   | delvDocId | shippingPtNum | delvTypeCd | slsOrdrCarCd | delvDt   | crtDttm  | soldToCustNum | shipToCustNum | billOfLdngNum | delvNum | planGiDt | actlGiDt | shippingCondCd | supNum | plntCd | localSalesOrg |
      | CONS_LATAM | 82793652  | BR12          | ZDLI       | J            | 20090723 | 20090723 | 164115        |               |               | 0,00    | 20090723 |          | 01             |        |        | BR01          |
      | CONS_LATAM | 81123645  | CO01          | ZDLI       | J            | 20060517 | 20060517 | 109527        |               |               | 0,00    | 20060517 |          | 01             |        |        | CO01          |
      | CONS_LATAM | 82794385  | BR12          | ZDLI       | J            | 20090723 | 20090723 | 789456        | 789456        |               | 0,00    | 20090723 |          | 01             |        |        | BR01          |
      | CONS_LATAM | 82812920  | BR12          | ZDLI       | J            | 20090108 | 20090108 | 897001        | 897001        |               | 0,00    | 20090108 |          | 01             |        |        | BR01          |
      | CONS_LATAM | 81156058  | BR12          | ZITL       | J            | 20060628 | 20060628 | 109524        |               |               | 0,00    | 20060628 |          | 01             |        |        | BR01          |
      | CONS_LATAM | 81156060  | CO01          | ZDLI       | J            | 20060628 | 20060628 | 109555        |               |               | 0,00    | 20060628 |          | 01             |        |        | CO01          |
      | CONS_LATAM | 81123738  | UY01          | ZITL       | J            | 20170111 | 20170102 | 149680        |               |               | 0,00    | 20170109 | 20170103 | 01             |        |        |               |
      | CONS_LATAM | 80808080  | UY01          | ZITL       | J            | 20170111 | 20170102 |               |               |               | 0,00    | 20170109 | 20170103 | 01             |        |        | BR01          |
      | CONS_LATAM | 82812921  | AR01          | ZVCI       | J            | 20090108 | 20090108 | 164115        | 164115        |               | 0,00    | 20090108 | 20090108 | 01             |        |        |               |
      | CONS_LATAM | 82812345  | CO01          | ZVIC       | J            | 20060517 | 20060517 | 109527        | 109527        |               | 0,00    | 20060517 | 20060517 | 01             |        |        |               |
      | CONS_LATAM | 81156059  | BR12          | ZITL       | J            | 20060628 | 20060628 | 109524        | 109524        |               | 0,00    | 20060628 |          | 01             |        |        |               |
    And I wait "/edm/outbound_delivery_header_v1" Async Queue complete

    Given I import "/edm/outbound_delivery_line_v1" by keyFields "delvDocId,delvLineNbr,btchNum,slsOrdrNum,slsOrdrLineNbr,baseUnitOfMeasureCd"
      | actlSkuDelvQty | baseUnitOfMeasureCd | btchNum    | delvDocId | delvLineNbr | matlNum | shippedQty | shippingPlntCd | slsOrdrLineNbr | slsOrdrNum | srcSysCd   | vendBtchNum |
      | 820.0          | TS                  | 4000613556 | 82793652  | 10          | 135768  | 0          | BR12           | 10             | 4000007574 | CONS_LATAM | 13680       |
      | 225.0          | KG                  |            | 81123645  | 10          | 7058161 | 27         | CO01           | 20             | 3000187317 | CONS_LATAM | 183229      |
      | 3504.0         | TS                  | 1336COA    | 82794385  | 10          | 189915  | 27         | BR12           | 10             | 3000187319 | CONS_LATAM |             |
      | 5276.0         | TS                  | 4000616293 | 82812920  | 10          | 174461  | 0          | BR12           | 50             | 4000007779 | CONS_LATAM | 323337      |
      | 120.0          | EA                  | 110506     | 81156058  | 10          | 70391   | 10         | BR12           | 10             | 3000192787 | CONS_LATAM |             |
      | 110.0          | EA                  | 110ABC     | 81156060  | 10          | 65759   | 10         | CO01           | 10             | 3000192789 | CONS_LATAM |             |
      | 10.0           | EA                  | 110ABC     | 81156060  | 20          | 65759   | 10         | CO01           | 20             | 3000192789 | CONS_LATAM |             |
      | 10.0           | EA                  |            | 81123738  | 30          | 60414   | 0          | UY01           | 30             | 118234172  | CONS_LATAM |             |
      | 1.0            | KI                  | B00LLC6    | 81123547  | 100         | 84033   | 1          | UY01           | 150            | 117915919  | CONS_LATAM | B00LLC6     |
      | 8.0            | TS                  | 4000616A   | 80808080  | 10          | 170196  | 1          | AR01           | 10             | 400000001  | CONS_LATAM | 88812       |
      | 1.0            | TS                  |            | 82812345  | 10          | 123456  | 1          | CO01           | 10             | 4000007771 | CONS_LATAM | 323339      |
    And I wait "/edm/outbound_delivery_line_v1" Async Queue complete

    Given I import "/edm/sales_order_v1" by keyFields "salesOrderNo,localMaterialNumber"
      | localChangeDt | localCustomerGroup | localCustomerPO      | localDeliveryBlock | localDelvStat | localDentoBase | localDistrChannel | localDivision | localDocumentCateg | localIncoTerms1 | localIncoTerms2       | localItemBillRlvnt | localItemCategory | localItemDlvRlvnt | localMaterialNumber | localNumtoBase | localOrderCreateDt | localOrderDate | localOrderReason | localOrderType | localPlant | localPricingProcedure | localRejReason | localRejStat | localRequestedDate | localRoute | localSDItemCurrency | localSDItemValue | localSalesGroup | localSalesOffice | localSalesOrg | localSalesUnit | localSchLineConfimQty | localSchLineQty | localScheduleLineDate | localShipToParty | localShippingPoint | localSoldToParty | localStorageLocation | localValidFromDt | localValidToDt | salesOrderItem | salesOrderNo | salesOrderQty | scheduleLineItem | sourceSystem |
      | 0             | RH                 | 50716649             |                    |               | 1              | 10                | 10            | C                  | CIF             | COSTO,SEGURO,FLETE    | A                  | ZLBN              |                   | 65759               | 1              | 20180808           | 20170216       |                  | ZLVE           | CO01       | ZCOL01                |                |              | 20170218           | C02008     | COP                 | 275712           | C03             | CO10             | CO01          | EA             | 24                    | 24              | 20170218              | 109555           | CO02               | 109555           |                      | 0                | 0              | 10             | 3000192789   | 24            | 1                | CONS_LATAM   |
      | 20150514      | WP                 | 479928               |                    |               | 1              | 12                | 10            | C                  | CIF             | COSTO,SEGURO,FLETE    | A                  | ZLBN              |                   | 70391               | 12             | 20180808           | 20150514       |                  | ZVLO           | BR12       | ZECU01                | 99             |              | 20150514           | ECGUAY     | USE                 | 2066.4           | E01             | E100             | BR01          | KI             | 0                     | 41              | 20150514              | 109524           | BR12               | 109524           |                      | 0                | 0              | 10             | 3000192787   | 41            | 1                | CONS_LATAM   |
      | 0             | RS                 | 0085205965-YA        |                    |               | 1              | 10                | 10            | C                  | CIF             | Custo, Seguro & Frete | A                  | ZLBN              |                   | 174461              | 1              | 20180808           | 20160603       |                  | ZVLO           | BR12       | ZCOL01                | 95             |              | 20160607           | C02004     | COP                 | 52404            | C03             | CO10             | BR01          | EA             | 16                    | 16              | 20160321              |                  | CO02               |                  |                      | 0                | 0              | 10             |              | 12            | 1                | CONS_LATAM   |
      | 0             | CC                 | 958071               |                    |               | 1              | 16                | 10            | C                  | CIF             | Custo, Seguro & Frete | A                  | ZVLO              |                   | 189915              | 1              | 20180808           | 20150915       |                  | ZVLO           | BR12       | ZBRA14                |                |              | 20150915           |            | BRL                 | 10.05            | 45              | BR30             | BR01          | EA             | 2                     | 2               | 20150915              | 789456           | BR12               | 789456           |                      | 0                | 0              | 10             | 3000187319   | 2             | 1                | CONS_LATAM   |
      | 0             | WG                 | 196484970            |                    |               | 1              | 11                | 11            | H                  | FOB             | PLANTA CLIENTE        | B                  | ZLBE              |                   | 7058161             | 1              | 20180808           | 20161220       | 343              | ZLVE           | CO01       | ZMEX04                |                |              | 20161220           |            | BRL                 | 0                | M59             | CO01             | CO01          | KI             | 1                     | 1               | 20161220              | 109527           | CO01               | 109527           |                      | 0                | 0              | 10             | 3000187317   | 1             | 1                | CONS_LATAM   |
      | 0             |                    | 46044079050000000000 |                    |               | 1              | 10                | 10            | C                  |                 |                       | A                  | ZLBN              |                   | 135768              | 24             | 20180808           | 20180305       |                  | ZVLO           | BR12       | ZECW01                |                |              | 20180305           | ECGUAY     | USE                 | 219.9            | E01             | E100             | BR01          | KI             | 3                     | 0               | 20180306              | 164115           | BR12               | 164115           |                      | 0                | 0              | 10             | 4000007574   | 3             | 2                | CONS_LATAM   |
      | 0             |                    | VL-REP-DX 604 REP #1 |                    |               | 1              | 12                | 11            | I                  |                 |                       | A                  | ZLSR              |                   | 123456              | 1              | 20180808           | 20180220       | 337              | ZVLO           | BR12       | ZCOL07                |                |              | 20180220           | C02005     | COP                 | 1216.64          | C45             | CO50             | BR01          | EA             | 1                     | 0               | 20180222              | 123456           | BR01               | 123456           |                      | 0                | 0              | 178            | 4000000001   | 200           | 2                | CONS_LATAM   |
      | 20170316      | RZ                 | U023781              |                    |               | 1              | 11                | 10            | C                  | FOB             | PLANTA CLIENTE        | A                  | ZLBN              |                   | 94065               | 12             | 20180808           | 20170315       |                  | ZLVE           | MX02       | ZMEX01                |                |              | 20170323           | MP0600     | MXN                 | 2881.7           | M93             | MX91             | BR01          | KI             | 10                    | 10              | 20170323              |                  | MX02               |                  | MX01                 | 0                | 0              | 130            | 117493379    | 10            | 1                | CONS_LATAM   |
      | 20170316      | RZ                 | U023781              |                    |               | 1              | 11                | 10            | C                  | FOB             | PLANTA CLIENTE        | A                  | ZLBN              |                   | 174461              | 12             | 20180808           | 20170315       |                  | ZLVE           | BR12       | ZMEX01                |                |              | 20170323           | MP0600     | MXN                 | 2881.7           | M93             | MX91             | BR01          | KI             | 10                    | 10              | 20170323              | 897001           | MX02               | 897001           | MX01                 | 0                | 0              | 130            | 4000007779   | 10            | 1                | CONS_LATAM   |
    And I wait "/edm/sales_order_v1" Async Queue complete

    Given I import "/edm/sales_history_v1" by keyFields "localSubsDocNo,localSubsDocLnNo,localSubDocCatg,sourceSystem"
      | localBaseQuantity | localBaseUom | localCrtDt | localPrecDocNo | localPrecItemCatg | localSPrecDocLnNo | localSalesQuantity | localSalesUom | localSubDocCatg | localSubsDocLnNo | localSubsDocNo | sourceSystem |
      | 42672             | EA           | 20090406   | 3000192789     | J                 | 10                | 42700              | EA            | J               | 10               | 81156060       | CONS_LATAM   |
      | 42672             | EA           | 20090406   | 3000192789     | J                 | 10                | 42700              | EA            | J               | 20               | 81156060       | CONS_LATAM   |
      | 2                 | EA           | 20090422   | 3000192787     | J                 | 10                | 2                  | EA            | J               | 10               | 81156058       | CONS_LATAM   |
      | 552               | EA           | 20090221   |                | J                 | 10                | 552                | EA            | J               | 10               | 82812920       | CONS_LATAM   |
      | 0                 | EA           | 20180104   | 3000187319     | J                 | 10                | 0                  | CRT           | J               | 10               | 82794385       | CONS_LATAM   |
      | 60                | EA           | 20140214   | 3000187317     | J                 | 10                | 60                 |               | J               | 10               | 81123645       | CONS_LATAM   |
      | 90                | EA           | 20140306   | 4000007574     | J                 | 10                | 90                 |               | J               | 10               | 82793652       | CONS_LATAM   |
      | 0                 |              | 20090221   | 92746504       | O                 | 130               | 0                  |               | S               | 130              | 92760572       | CONS_LATAM   |
      | 2                 | EA           | 20090526   | 82671347       | J                 | 660               | 2                  | EA            | M               | 810              | 92969929       | CONS_LATAM   |
      | 12                | EA           | 20090206   | 400000001      | M                 | 7                 | 12                 | EA            | S               | 27               | 80808080       | CONS_LATAM   |
      | 360               | EA           | 20140625   | 87948879       | J                 | 310               | 360                |               | Q               | 32               | 3661885        | CONS_LATAM   |
      | 1                 | EA           | 20061023   | 60142343       | H                 | 8                 | 1                  | EA            | O               | 8                | 91365306       | CONS_LATAM   |
    And I wait "/edm/sales_history_v1" Async Queue complete

    Given I import "/edm/purchase_order_oa_v1" by keyFields "matlNum,poNum,poLineNbr,plntCd,sourceSystem"
      | custNum | delInd | delvCmpltInd | matlNum | outbDelvCmpltInd | plntCd | poCatTypeCd | poLineNbr | poLineQty | poNum      | poTypeCd | slsOrdrLineNbr | slsOrdrNum | sourceSystem | stkTfrRecvQty | subcntrcInd | supNum | suplPlntCd |
      |         |        | TRUE         | 174461  | TRUE             | BR12   | F           | 50        | 5         | 4000007779 | ZLA      |                |            | CONS_LATAM   | 5             | FALSE       | 79895  | BR12       |
      |         |        | TRUE         | 69348   | FALSE            | BR25   | F           | 10        | 66        | 3000810567 | ZNB      |                |            | CONS_LATAM   | 66            | FALSE       | 8917   | BR12       |
      |         | L      | FALSE        | 60458   | FALSE            | BR14   | F           | 320       | 2         | 4000074790 | UB       |                |            | CONS_LATAM   | 0             | FALSE       |        | BR12       |
      |         |        | TRUE         | 56951   | FALSE            | BR07   | F           | 110       | 68        | 3000831994 | ZNB      |                |            | CONS_LATAM   | 68            | FALSE       | 8917   | BR12       |
    And I wait "/edm/purchase_order_oa_v1" Async Queue complete

    Given I import "/plan/cns_plan_object_filter" by keyFields "sourceObjectAttribute1,sourceObjectAttribute1Value,sourceObjectAttribute2,sourceObjectAttribute2Value,sourceObjectTechName,sourceSystem"
      | inclusionExclusion | sourceObjectAttribute1 | sourceObjectAttribute1Value | sourceObjectAttribute2 | sourceObjectAttribute2Value | sourceObjectTechName       | sourceSystem |
      | I                  | plntCd                 | CO01                        | poTypeCd               | ZNB                         | purchase_order_oa          | CONS_LATAM   |
      | I                  | plntCd                 | DO01                        | poTypeCd               | ZNB                         | purchase_order_oa          | CONS_LATAM   |
      | I                  | plntCd                 | MX02                        | poTypeCd               | UB                          | purchase_order_oa          | CONS_LATAM   |
      | I                  | plntCd                 | BR19                        | poTypeCd               | ZLA                         | purchase_order_oa          | CONS_LATAM   |
      | I                  | plntCd                 | PE01                        | poTypeCd               | UB                          | purchase_order_oa          | CONS_LATAM   |
      | I                  | shippingPtNum          | CO01                        | delvDocId              | ZVIC                        | outbound_delivery_header   | CONS_LATAM   |
      | I                  | localReceivingPlant    | CR02                        | localdeliveryType      | EL                          | advance_ship_notifications | CONS_LATAM   |
      | I                  | shippingPtNum          | CO01                        | delvDocId              | ZITL                        | outbound_delivery_header   | CONS_LATAM   |
      | I                  | shippingPtNum          | BR12                        | delvDocId              | ZITL                        | outbound_delivery_header   | CONS_LATAM   |
      | I                  | shippingPtNum          | CO01                        | delvDocId              | ZDLI                        | outbound_delivery_header   | CONS_LATAM   |
      | I                  | shippingPtNum          | BR12                        | delvDocId              | ZDLI                        | outbound_delivery_header   | CONS_LATAM   |
      | I                  | shippingPtNum          | CO01                        | delvDocId              | ZDLI                        | outbound_delivery_header   | CONS_LATAM   |
      | I                  | shippingPtNum          | BR12                        | delvDocId              | ZVCI                        | outbound_delivery_header   | CONS_LATAM   |
      | I                  | localPlant             | CO01                        | ordrType               | ZLVE                        | sales_order                | CONS_LATAM   |
      | I                  | localPlant             | BR12                        | ordrType               | ZLVO                        | sales_order                | CONS_LATAM   |
      | I                  | localPlant             | CO01                        | ordrType               | ZLVE                        | sales_order                | CONS_LATAM   |
      | I                  | localPlant             | BR12                        | ordrType               | ZLVO                        | sales_order                | CONS_LATAM   |
      | I                  | localSalesOrg          | BR01                        | shipToCustNum          | 164115                      | outbound_delivery_header   | CONS_LATAM   |
      | I                  | localSalesOrg          | CO01                        | shipToCustNum          | 109527                      | outbound_delivery_header   | CONS_LATAM   |
      | I                  | localSalesOrg          | BR01                        | shipToCustNum          |                             | outbound_delivery_header   | CONS_LATAM   |
      | I                  | localSalesOrg          | BR01                        | shipToCustNum          | 897001                      | outbound_delivery_header   | CONS_LATAM   |
      | I                  | localSalesOrg          | BR01                        | shipToCustNum          |                             | outbound_delivery_header   | CONS_LATAM   |
      | I                  | localSalesOrg          | CO01                        | shipToCustNum          | ALL                         | outbound_delivery_header   | CONS_LATAM   |
    And I wait "/plan/cns_plan_object_filter" Async Queue complete
    
    Given  I import "/plan/prod_loc_min_shelf" by keyFields "localMaterialNumber,localPlant,sourceSystem"
      | localMaterialNumber | localPlant | minMinShelfLife | minShelfLife | sourceSystem |
      | *                   | BR16       |                 | 270          | CONS_LATAM   |
      | 4002                | BR12       |                 | 150          | CONS_LATAM   |
      | *                   | BR12       |                 | 180          | CONS_LATAM   |
    And I wait "/plan/prod_loc_min_shelf" Async Queue complete

    Given I import "/plan/cns_material_plan_status" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | active | dpRelevant | localMaterialNumber | localParentCode   | localPlant | materialNumber | noPlanRelevant | parentActive | ppc     | sourceSystem | spRelevant |
      | X      | X          | 135768              | 78910105667390000 | BR12       |                |                | X            | 135768  | CONS_LATAM   | X          |
      | X      |            | 7058161             |                   | CO01       | 7058161        |                |              | 7058161 | CONS_LATAM   | X          |
      | X      |            |                     | 78910105896390000 | UY01       | 71503          |                | X            | 71503   | CONS_LATAM   | X          |
      | X      |            | 84033               |                   | UY01       | 84300          |                |              | 84303   | CONS_LATAM   | X          |
      | X      |            | 189915              |                   | BR12       | 189915         | X              |              | 189915  | CONS_LATAM   |            |
      | X      |            | 174461              |                   | BR12       | 174461         | X              |              | 174461  | CONS_LATAM   |            |
      | X      | X          | 170196              | 78910108851820000 | BR16       | 171960         | X              | X            | 170196  | CONS_LATAM   |            |
      | X      |            | 123456              |                   | CO01       | 123456         |                |              | 123456A | CONS_LATAM   | X          |
      | X      | X          | 65759               |                   | CO01       | 65758          |                | X            | 112233  | CONS_LATAM   | X          |
      | X      |            | 70391               |                   | BR12       | 70391          |                |              | 70391   | CONS_LATAM   | X          |
    And I wait "/plan/cns_material_plan_status" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber,materialNumber,sourceSystem"
      | localMaterialGroup | localMaterialNumber | localMaterialType | materialNumber | materialStatus | materialType | minRemShelfLife | parentCode | primaryPlanningCode | sourceSystem | totalShelfLife |
      | 1                  | 135768              | FERT              |                | 8              | FERT         | 180             |            | 135768              | CONS_LATAM   | 9999           |
      | 1                  | 7058161             | FERT              | 7058161        | 8              | FERT         | 220             |            |                     | CONS_LATAM   | 730            |
      | 1                  |                     | FERT              | 71503          | 8              | FERT         | 180             |            | 71503               | CONS_LATAM   | 730            |
      | 1                  | 84033               | FERT              | 84300          | 8              | FERT         | 180             |            | 84303               | CONS_LATAM   | 730            |
      | 1                  | 189915              | SAPR              |                | 8              | FERT         | 183             |            | 189915              | CONS_LATAM   | 730            |
      | 1                  | 174461              | FERT              | 174461         | 8              | FERT         | 0               |            |                     | CONS_LATAM   | 0              |
      | 3                  | 170196              | FERT              | 171960         | 8              | FERT         | 183             |            | 170196              | CONS_LATAM   | 730            |
      | 1                  | 123456              | FERT              | 123456         | 8              | FERT         | 146             |            | 123456A             | CONS_LATAM   | 730            |
      | 1                  | 70391               | FERT              | 70391          | 8              | FERT         | 180             |            | 70391               | CONS_LATAM   | 730            |
      |                    | 65759               | FERT              | 65758          | 8              | FERT         | 200             |            | 65759               | CONS_LATAM   | 730            |
    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/plan/cns_plan_unit" by keyFields "plantFlag,unit,localUom,sourceSystem,localUomName"
      | factor | localUom | localUomName        | planFlag | plantFlag | roundingDecimal | sourceSystem | unit   |
      | 1      | KG       | KiloGram            | SP       |           | 6               | CONS_LATAM   | KG     |
      | 1      | KI       | Crate               | DPSP     |           | 0               | CONS_LATAM   | CA     |
      | 1      | PAL      | Pallet              | SP       |           | 0               | CONS_LATAM   | PAL    |
      | 1      | CS       | Case                | DPSP     |           | 0               | CONS_LATAM   | CA     |
      | 1      | L        | Liter               | SP       |           | 6               | CONS_LATAM   | L      |
      | 1      | DZ       | Dozen               | SP       |           | 3               | CONS_LATAM   | DZ     |
      | 1      | M2       | Square Meter        | SP       |           | 6               | CONS_LATAM   | M2     |
      | 1      | EA       | Each                | DPSP     |           | 0               | CONS_LATAM   | EA     |
      | 1      | TS       | Market Control Unit | DPÂ      |           | 6               | CONS_LATAM   | LA_ZUM |
      | 1      | KM       | Kilometer           | SP       |           | 3               | CONS_LATAM   | KM     |
      | 1      | TH       | Thousand            | SP       |           | 6               | CONS_LATAM   | TS     |
    And I wait "/plan/cns_plan_unit" Async Queue complete
    
    Given I import "/plan/cns_dem_grp_asgn" by keyFields "customerId,demandGroup,salesOrganization,sourceSystem"
      | channel | channelDescription      | comments | companyCode | countryAffiliate | customerId | customerName                   | customerShipTo | demandGroup | demandGroupDescription        | flatFile | fromDate | group | modifiedBy | region | salesOrg | salesOrganization | sourceSystem | status | subFranchise | timeStamp | toDate | valid |
      | CH002   | Pharmacy Distributor    |          |             | CO               | 109555P3   | CAFAM                          |                | 77550013    | Other Pharmacy Distributor    |          |          |       |            |        |          | CO01              | CONS_LATAM   |        |              |           |        |       |
      | CH003   | Pharmacy Wholesaler     |          |             | BR               | 109524     | GRUPO NAZARIA RN               |                | 76100015    | NAZARIA                       |          |          |       |            |        |          | BR01              | CONS_LATAM   |        |              |           |        |       |
      | CH005   | Modern Trade            |          |             | CO               | 789456     | ALMACENES EXITO S A            |                | 77550025    | ALMACEN EXITO                 |          |          |       |            |        |          | BR01              | CONS_LATAM   |        |              |           |        |       |
      | CH010   | Other Mass              |          |             | DO               | 109527     | YESER CALDERON     (MARKETING) |                | 77800015    | OTHERS DO                     |          |          |       |            |        |          | CO01              | CONS_LATAM   |        |              |           |        |       |
      | CH006   | Distributor Traditional |          |             | PE               | 164115     | TRANSPORTES PAREDES S.R.L.     |                | 77550022    | Other Distributor Traditional |          |          |       |            |        |          | BR01              | CONS_LATAM   |        |              |           |        |       |
      | CH004   | Other Pharma            |          |             | AR               | 12814      | FARMACIA OJEDA                 |                | 74500037    | OTHERS DRUGS                  |          |          |       |            |        |          | BR01              | CONS_LATAM   |        |              |           |        |       |
      | CH005   | Modern Trade            |          |             | CO               | 192215     | COMFAMILIAR ANDI               |                | 77550031    | Other Modern Trade            |          |          |       |            |        |          | BR01              | CONS_LATAM   |        |              |           |        |       |
      | CH006   |                         |          |             |                  | 123456     | Prashant                       |                |             |                               |          |          |       |            |        |          | BR01              |              |        |              |           |        |       |
    And I wait "/plan/cns_dem_grp_asgn" Async Queue complete

    Given I import "/plan/cns_so_type_incl_excl" by keyFields "country,orderType,plant,salesOrg,sourceSystem"
      | country | inclExcl | orderType | plant | salesOrg | sourceSystem |
      | CO      | I        | ZLVE      | CO01  | CO01     | CONS_LATAM   |
      | BR      | I        | ZVLO      | BR12  | BR01     | CONS_LATAM   |
      | PE      | I        | ZLPE      | PE01  | PE01     | CONS_LATAM   |
      | CL      | I        | ZLGR      | CL01  | CL01     | CONS_LATAM   |
      | AR      | I        | ZLSA      | AR02  | AR02     | CONS_LATAM   |
      | BR      | I        | ZORB      | BR25  | BR01     | CONS_LATAM   |
      | BR      | I        | ZSRT      | BR16  | BR01     | CONS_LATAM   |
      | EC      | I        | ZLVE      | EC01  | EC01     | CONS_LATAM   |
      | PA      | I        | ZLGR      | PA03  | PA01     | CONS_LATAM   |
      | MX      | I        | ZLSR      | MX02  | MX02     | CONS_LATAM   |
      | AR      | I        | ZLDP      | AR01  | AR01     | CONS_LATAM   |
      | BR      | I        | ZSRT      | BR07  | BR01     | CONS_LATAM   |
      | AR      | I        | ZLGR      | AR02  | AR02     | CONS_LATAM   |
      | CR      | I        | ZLVE      | CR01  | CR01     | CONS_LATAM   |
    And I wait "/plan/cns_so_type_incl_excl" Async Queue complete
    
    Given I import "/plan/cns_cust_excl_incl" by keyFields "country,customerShipTo,salesOrg,sourceSystem"
      | country | customerShipTo | inclExcl | salesOrg | sourceSystem  |
      | BR      | 164115         | I        | BR01     | CONS_LATAM    |
      | GT      | ALL            | I        | CO01     | CONS_LATAM    |
      | CR      | 789456         | I        | BR01     | CONS_LATAM    |
      | PA      | 897001         | I        | BR01     | CONS_LATAM    |
      | AR      | 109524         | I        | BR01     | CONS_LATAM    |
      | CR      | 109555         | I        | CO01     | CONS_LATAM    |
      | UY      | 191049         | E        | UY01     | CONS_LATAM    |
      | PA      | 155266         | E        | PA01     | CONS_LATAM    |
      | CL      | 179304         | E        | CL01     | CONS_LATAM    |
      | PA      | 187011         | E        | PA01     | CONS_LATAM    |
      | GT      | 161892         | E        | GT01     | CONS_LATAM    |
      | GT      | 189403         | E        | GT01     | CONS_LATAM    |
      | PA      | 162763         | E        | PA01     | CONS_LATAM    |
      | GT      | 157381         | E        | GT01     | CONS_LATAM    |
      | AR      | 125097         | E        | AR02     | CONS_LATAM    |
    And I wait "/plan/cns_cust_excl_incl" Async Queue complete
    
    Given I import "/project_one/knvh" by keyFields "kunnr,vkorg,hkunnr,hityp"
      | hityp | kunnr    | vkorg | vtweg | spart | datab    | datbi    | hkunnr   | hvkorg | hvtweg | hspart | grpno | bokre | prfre | hzuor |
      | A     | 109555   | CO01  | 10    | 10    | 20180809 | 20180809 | 109555P1 |        |        |        | 0     | true  | true  | 0     |
      | A     | 109555P1 | CO01  | 10    | 10    | 20180809 | 20180809 | 109555P2 |        |        |        | 0     | true  | true  | 0     |
      | A     | 109555P2 | CO01  | 10    | 10    | 20180809 | 20180809 | 109555P3 |        |        |        | 0     | true  | true  | 0     |
      |       | 123456   | CO01  |       |       | 20180809 | 20180809 |          |        |        |        |       |       |       |       |
    And I wait "/project_one/knvh" Async Queue complete


    When I submit task with xml file "xml/omp/OMPGdmDemandOBD.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMDemand.tsv"

    And I check file data for filename "GDMDemand.tsv" by keyFields "demandId"
      | active | activeFCTERP | activeOPRERP | activeOBDPERP | batchId                           | certaintyId | customerId | demandId                            | dueDate             | fromDueDate         | inventoryLinkGroupId                | locationId      | minRemainingShelfLife | planningStrategy        | productId | quantity   | unitId | WRK02 |
      | YES    | NO           | YES          | NO            | 135768/CONS_LATAM_BR12/4000613556 | VJ          | 77550022   | 135768/CONS_LATAM_BR12/82793652/10  | 2009/07/23 00:00:01 | 2009/07/23 00:00:00 | 135768/CONS_LATAM_BR12/82793652/10  | CONS_LATAM_BR12 | 180                   | ProductLocationBalanced | 135768    | 820.0      | TS     |       |
      | YES    | NO           | YES          | NO            |                                   | VJ          | 77800015   | 7058161/CONS_LATAM_CO01/81123645/10 | 2006/05/17 00:00:01 | 2006/05/17 00:00:00 | 7058161/CONS_LATAM_CO01/81123645/10 | CONS_LATAM_CO01 | 220                   | ProductLocationBalanced | 7058161   | 225.0      | KG     |       |
      | YES    | NO           | YES          | NO            | 189915/CONS_LATAM_BR12/1336COA    | VJ          | 77550025   | 189915/CONS_LATAM_BR12/82794385/10  | 2009/07/23 00:00:01 | 2009/07/23 00:00:00 | 189915/CONS_LATAM_BR12/82794385/10  | CONS_LATAM_BR12 | 180                   | ProductLocationBalanced | 189915    | 3504.0     | TS     |       |
      | YES    | NO           | YES          | NO            | 174461/CONS_LATAM_BR12/4000616293 | VJ          | 897001     | 174461/CONS_LATAM_BR12/82812920/10  | 2009/01/08 00:00:01 | 2009/01/08 00:00:00 | 174461/CONS_LATAM_BR12/82812920/10  | CONS_LATAM_BR12 | 180                   | ProductLocationBalanced | 174461    | 5276.0     | TS     |       |
      | YES    | NO           | YES          | NO            | 70391/CONS_LATAM_BR12/110506      | VJ          | 76100015   | 70391/CONS_LATAM_BR12/81156058/10   | 2006/06/28 00:00:01 | 2006/06/28 00:00:00 | 70391/CONS_LATAM_BR12/81156058/10   | CONS_LATAM_BR12 | 180                   | ProductLocationBalanced | 70391     | 120.0      | EA     |       |
      | YES    | NO           | YES          | NO            | 65759/CONS_LATAM_CO01/110ABC      | VJ          | 77550013   | 65759/CONS_LATAM_CO01/81156060/10   | 2006/06/28 00:00:01 | 2006/06/28 00:00:00 | 65759/CONS_LATAM_CO01/81156060/10   | CONS_LATAM_CO01 | 200                   | ProductLocationBalanced | 65759     | 110.0      | EA     |       |
      | YES    | NO           | YES          | NO            | 65759/CONS_LATAM_CO01/110ABC      | VJ          | 77550013   | 65759/CONS_LATAM_CO01/81156060/20   | 2006/06/28 00:00:01 | 2006/06/28 00:00:00 | 65759/CONS_LATAM_CO01/81156060/20   | CONS_LATAM_CO01 | 200                   | ProductLocationBalanced | 65759     | 10.0       | EA     |       |

    And I will remove all data with region "/edm/outbound_delivery_header_v1"

    And I will remove all data with region "/edm/outbound_delivery_line_v1"

    And I will remove all data with region "/edm/purchase_order_oa_v1"

    And I will remove all data with region "/edm/sales_order_v1"

    And I will remove all data with region "/edm/sales_history_v1"

    And I will remove all data with region "/plan/cns_material_plan_status"

    And I will remove all data with region "/plan/cns_plan_unit"

    And I will remove all data with region "/plan/cns_plan_object_filter"

    And I will remove all data with region "/plan/cns_plan_so_type_incl_excl"

    And I will remove all data with region "/plan/prod_loc_min_shelf"

    And I will remove all data with region "/plan/cns_cust_excl_incl"

    And I will remove all data with region "/plan/cns_dem_grp_asgn"

    And I will remove all data with region "/project_one/knvh"

    #And I will remove all data with region "/dev/plan/edm_failed_data"

    And I will remove the test file on sink application "GDMDemand.tsv"