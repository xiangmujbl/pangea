@pangea_test @AEAZ-6830
Feature:  OMPGdmDemandSalesOrder-Curation

  Scenario: Full Load consumption

    Given I import "/edm/sales_order_v1" by keyFields "salesOrderItem,salesOrderNo,scheduleLineItem,sourceSystem"
      | localBillingBlock | localBillingBlockItem | localChangeDt | localCustomerGroup | localCustomerPO | localDeliveryBlock | localDentoBase | localDistrChannel | localDivision | localDocumentCateg | localIncoTerms1 | localIncoTerms2 | localItemBillRlvnt | localItemCategory | localItemDlvRlvnt | localMaterialNumber | localNumtoBase | localOrderCreateDt | localOrderDate | localOrderReason | localOrderType | localPlant | localPricingProcedure | localRejReason | localRequestedDate | localRoute | localSDItemCurrency | localSDItemValue | localSalesGroup | localSalesOffice | localSalesOrg | localSalesUnit | localSchLineConfimQty | localSchLineQty | localScheduleLineDate | localShipToParty | localShippingPoint | localSoldToParty | localStorageLocation | localValidFromDt | localValidToDt | salesOrderItem | salesOrderNo | salesOrderQty | scheduleLineItem | sourceSystem | localRejStat | localDelvStat |
      |                   |                       | 20160701      |                    | 4506056414      |                    | 1              | 10                | 10            | C                  |                 |                 | A                  | ZTBN              |                   | 58752               | 10             | 20160525           | 20160525       |                  | ZORB           | BR16       | ZBRA01                |                | 20160607           | BR4006     | BRL                 | 1543.23          | NE4             | BR60             | BR01          | KI             | 34                    | 0               | 20160603              | 109038           | BR01               | 174574           |                      | 0                | 0              | 490            | 116708516    | 34            | 1                | CONS_LATAM   | N            | N             |
      |                   |                       | 20171020      |                    | 1322163         |                    | 1              | 10                | 10            | C                  |                 |                 | A                  | ZTBN              |                   | 57039               | 1              | 20171011           | 20171011       |                  | ZORB           | BR14       | ZBRA01                |                | 20171019           | B19029     | BRL                 | 1626.9           | SP4             | BR62             | BR01          | KI             | 0                     | 15              | 20171019              | 182493           | BR19               | 182493           |                      | 0                | 0              | 20             | 8306862      | 38            | 1                | CONS_LATAM   | N            | N             |
      |                   |                       | 20171020      |                    | 8306863         |                    | 1              | 10                | 10            | C                  |                 |                 | A                  | ZLBN              |                   | 60520               | 1              | 20161121           | 20161121       |                  | ZLVE           | BR13       | ZECU01                |                | 20161121           | ECGUAY     | USE                 | 23.68            | E01             | E100             | EC01          | KI             | 1                     | 0               | 20161122              | 139938           | EC01               | 139938           |                      | 0                | 0              | 150            | 8306863      | 2020          | 1                | CONS_LATAM   | N            | N             |
      |                   |                       | 20150904      |                    | 3092015         |                    | 1              | 12                | 10            | C                  |                 |                 | A                  | ZLTQ              |                   | 68062               | 1              | 20150903           | 20150903       |                  | ZLVE           | CO02       | ZCOL01                | 121            | 20150907           | C02022     | COP                 | 545.03           | C27             | CO60             | BR01          | EA             | 80                    | 80              | 20150907              | 188002           | CO02               | 188002           |                      | 0                | 0              | 180            | 116043204    | 80            | 2                | CONS_LATAM   | C            | C             |
      |                   |                       | 0             |                    | 115689885       |                    | 1              | 10                | 11            | I                  |                 |                 | A                  | ZSRT              |                   | 91732               | 1              | 20150414           | 20150414       | 337              | ZSRT           | BR14       | ZBRA04                |                | 20171019           | B08004     | BRL                 | 2.11             | B63             | BR29             | BR01          | EA             | 1                     | 0               | 20150416              | 109038           | BR08               | 108733           |                      | 0                | 0              | 60             | 115689885    | 38            | 1                | CONS_LATAM   | N            | N             |
      |                   |                       | 20171212      |                    | 412699          |                    | 1              | 11                | 10            | C                  |                 |                 | A                  | ZLBN              |                   | 68052               | 1              | 20171211           | 20171211       |                  | ZLVE           | AR02       | ZARG01                | 55             | 20171211           | AR4000     | ARS                 | 545.03           | A03             | AR12             | AR02          | KI             | 0                     | 1               | 20171211              | 147885           | AR02               | 147885           | AR02                 | 0                | 0              | 220            | 118191240    | 1             | 2                | CONS_LATAM   | C            | C             |
    And I wait "/edm/sales_order_v1" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber,sourceSystem"
      | baseUom | batchManageIndicator | brand | category | division | flagForDeletion | form | franchise | globalBusinessUnit | globalDpParentCode | localBaseUom | localDpParentCode | localManufacturingTechnology | localMaterialGroup | localMaterialNumber | localMaterialType | localRefDescription                      | manufacturingTechnology | materialGroup | materialNumber | materialStatus | materialType | minRemShelfLife | parentCode | primaryPlanningCode | productFamily | refDescription | sourceSystem | subBrand | totalShelfLife |
      |         |                      |       |          | 10       |                 |      |           |                    |                    | EA           |                   |                              | 80141605           | 58752               | PROM              | TM-OLLA MULTIU. 7 TAZAS OSTER PAÃ‘ALERAS |                         |               | 58752          | 8              |              | 100             |            | 58752               |               |                | CONS_LATAM   |          | 0              |
      |         |                      |       |          | 11       |                 |      |           |                    |                    | EA           |                   |                              | 80141605           | 80990               | PROM              | TM-OLLA MULTIU. 7 TAZAS OSTER PAÃ‘ALERAS |                         |               | 80990          | 8              |              | 365             |            | 80990               |               |                | CONS_LATAM   |          | 1825           |
      |         |                      |       |          | 10       |                 |      |           |                    |                    | EA           |                   |                              | 80141605           | 57039               | PROM              | TM-OLLA MULTIU. 7 TAZAS OSTER PAÃ‘ALERAS |                         |               | 57039          | 8              |              | 120             |            | 57039               |               |                | CONS_LATAM   |          | 11             |
      |         |                      |       |          | 13       |                 |      |           |                    |                    | EA           |                   |                              | 80141605           | 96161               | PROM              | TM-OLLA MULTIU. 7 TAZAS OSTER PAÃ‘ALERAS |                         |               | 96161          | 9              |              | 11              |            | 96161               |               |                | CONS_LATAM   |          | 1              |
      |         |                      |       |          | 13       |                 |      |           |                    |                    | EA           |                   |                              | 80141605           | 91732               | PROM              | TM-OLLA MULTIU. 7 TAZAS OSTER PAÃ‘ALERAS |                         |               | 91732          | 8              |              | 8               |            | 91732               |               |                | CONS_LATAM   |          | 2              |
      |         |                      |       |          | 13       |                 |      |           |                    |                    | EA           |                   |                              | 80141605           | 60520               | PROM              | TM-OLLA MULTIU. 7 TAZAS OSTER PAÃ‘ALERAS |                         |               | 60520          | 9              |              | 9               |            | 60520               |               |                | CONS_LATAM   |          | 6              |
      |         |                      |       |          | 13       |                 |      |           |                    |                    | EA           |                   |                              | 80141605           | 68062               | PROM              | TM-OLLA MULTIU. 7 TAZAS OSTER PAÃ‘ALERAS |                         |               | 68062          | 9              |              | 9               |            |                     |               |                | CONS_LATAM   |          | 7              |
      |         |                      |       |          | 13       |                 |      |           |                    |                    | BTL          |                   |                              | 8011605            | 68052               | PROM              | TM-OLLA MULTIU. 7 TAZAS OSTER PAÃ‘ALERAS |                         |               | 68052          | 8              |              | 6               |            | 68052               |               |                | CONS_LATAM   |          | 7              |
    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/plan/cns_material_plan_status" by keyFields "localMaterialNumber,localPlant,sourceSystem"
      | active | dpRelevant | localMaterialNumber | localParentCode   | localPlant | materialNumber | noPlanRelevant | parentActive | ppc   | sourceSystem | spRelevant |
      | X      |            | 58752               | 78910109411230000 | BR16       | 58722          |                | X            | 58722 | CONS_LATAM   | X          |
      | X      |            | 97360               | 78910100877220000 | CO13       | 97360          |                | X            | 97360 | CONS_LATAM   | X          |
      | X      |            | 57039               | 78910100877220000 | BR14       | 57039          |                | X            | 57039 | CONS_LATAM   | X          |
      | X      |            | 80990               | 78910100877220000 | BR12       | 80990          | X              | X            | 80990 | CONS_LATAM   |            |
      | X      |            | 96161               | 78910100877220000 | BR12       | 1234           | X              | X            | 7867  | CONS_LATAM   |            |
      | X      |            | 91732               | 78910100877220000 | BR14       | 91732          | X              | X            | 91732 | CONS_LATAM   |            |
      | X      |            | 60520               | 78910100877220000 | BR13       | 60520          |                | X            | 60520 | CONS_LATAM   | X          |
      | X      |            | 68062               | 78910100877220000 | BR12       | 68062          |                | X            | 68062 | CONS_LATAM   | x          |
    And I wait "/plan/cns_material_plan_status" Async Queue complete

    Given I import "/plan/cns_spl_pln_loc" by keyFields "localName,localNumber,sourceSystem,vendorOrCustomer"
      | localCountry | localCurrency | localName                           | localNumber | localPlant | localRegion | pLanlocTypeDesc | pLanlocTypeId | planLocTypeDesc           | planLocTypeId | sourceSystem | specLocAtt1 | specLocAtt2 | specLocAtt3 | specLocAttDesc1 | specLocAttDesc2 | specLocAttDesc3 | vendorOrCustomer |
      | BR           | BRL           | SUPPORT PACK IND E COM LTDA         | 1189        | BR12       | 103         |                 |               | Mfg Plant Copacker        | SUBCON        | CONS_LATAM   |             |             |             |                 |                 |                 | V                |
      | EC           | USE           | LODISAL S.A.                        | 528395      | BR12       | 103         |                 |               | Affiliate/Market Copacker | SUBCON        | CONS_LATAM   |             |             |             |                 |                 |                 | V                |
      | US           |               | JOHNSON & JOHNSON CONSUMER INC.     | 50161       | BR01       | 101         |                 |               | Ext Manufacturer FG       | VENDOR        | CONS_LATAM   |             |             |             |                 |                 |                 | V                |
      | UY           | UYU           | MODYLER SA                          | 65659       | UY01       | 103         |                 |               | Affiliate/Market Copacker | SUBCON        | CONS_LATAM   |             |             |             |                 |                 |                 | V                |
      | GT           | GTQ           | DHL GLOBAL FORWARDING (GUATEMALA)   | 36191       | GT01       | 103         |                 |               | Affiliate/Market Copacker | SUBCON        | CONS_LATAM   |             |             |             |                 |                 |                 | V                |
      | CO           | COP           | SUPPLA SA                           | 44776       | CO01       | 103         |                 |               | Mfg Plant Copacker        | SUBCON        | CONS_LATAM   |             |             |             |                 |                 |                 | V                |
      | CL           | CLP           | Apl Logistics Chile S.A.            | 79232       | BR14       | 103         |                 |               | Affiliate/Market Copacker | SUBCON        | CONS_LATAM   |             |             |             |                 |                 |                 | V                |
      | DO           | DOP           | YOBEL SRL? ? ? ? ? ? ? ?            | 34427       | DO01       | 103         |                 |               | Affiliate/Market Copacker | SUBCON        | CONS_LATAM   |             |             |             |                 |                 |                 | V                |
      | MX           | MXN           | AVP MAQUILA DE MEXICO S DE RL DE CV | 86017       | MX02       | 103         |                 |               | Affiliate/Market Copacker | SUBCON        | CONS_LATAM   |             |             |             |                 |                 |                 | V                |
      | CO           | COP           | ASPRILLA ORTIZ FABIO                | 15574       | CO01       | 103         |                 |               | Mfg Plant Copacker        | SUBCON        | CONS_LATAM   |             |             |             |                 |                 |                 | V                |
      | CR           | CRC           | D.H.L. COSTA RICA (CORMAR) S.A.     | 36328       | CR01       | 103         |                 |               | Affiliate/Market Copacker | SUBCON        | CONS_LATAM   |             |             |             |                 |                 |                 | V                |
      | MX           | MXN           | CILAG AG INTERNATIONAL              | 6109        | MX01       | 104         |                 |               | Ext Manufacturer FG       | VENDOR        | CONS_LATAM   |             |             |             |                 |                 |                 | V                |
      | PA           | USD           | J CAIN & CO                         | 36124       | PA03       | 103         |                 |               | Affiliate/Market Copacker | SUBCON        | CONS_LATAM   |             |             |             |                 |                 |                 | V                |
      | PE           | USD           | YOBEL SUPPLY CHAIN MANAGEMENT S.A.  | 20667       | PE01       | 103         |                 |               | Affiliate/Market Copacker | SUBCON        | CONS_LATAM   |             |             |             |                 |                 |                 | V                |
    And I wait "/plan/cns_spl_pln_loc" Async Queue complete

    Given I import "/plan/prod_loc_min_shelf" by keyFields "localMaterialNumber,localPlant,sourceSystem"
      | localMaterialNumber | localPlant | minMinShelfLife | minShelfLife | sourceSystem |
      | 58752               | BR16       |                 | 150          | CONS_LATAM   |
      | *                   | BR01       |                 | 180          | CONS_LATAM   |
      | *                   | BR8        |                 | 270          | CONS_LATAM   |
    And I wait "/plan/prod_loc_min_shelf" Async Queue complete

    Given I import "/plan/cns_plan_unit" by keyFields "localUom,sourceSystem"
      | factor | localUom | localUomName        | planFlag | plantFlag | roundingDecimal | sourceSystem | unit   |
      | 1      | PAL      | Pallet              | SP       |           | 0               | CONS_LATAM   | PAL    |
      | 1      | KI       | Crate               | DPSP     |           | 0               | CONS_LATAM   | CA     |
      | 1      | KG       | KiloGram            | SP       |           | 6               | CONS_LATAM   | KG     |
      | 1      | CS       | Case                | DPSP     |           | 0               | CONS_LATAM   | CA     |
      | 1      | L        | `                   | SP       |           | 6               | CONS_LATAM   | L      |
      | 1      | EA       | Each                | DPSP     |           | 0               | CONS_LATAM   | EA     |
      | 1      | M2       | Square Meter        | SP       |           | 6               | CONS_LATAM   | M2     |
      | 1      | DZ       | Dozen               | SP       |           | 3               | CONS_LATAM   | DZ     |
      | 1      | TH       | Thousand            | SP       |           | 6               | CONS_LATAM   | TS     |
      | 1      | KM       | Kilometer           | SP       |           | 3               | CONS_LATAM   | KM     |
      | 1      | ZUM      | Market Control Unit | DPÂ      |           | 6               | CONS_LATAM   | LA_ZUM |
    And I wait "/plan/cns_plan_unit" Async Queue complete

    Given I import "/edm/sales_history_v1" by keyFields "localPrecDocNo,localSPrecDocLnNo,localSubDocCatg,localSubsDocLnNo,localSubsDocNo,sourceSystem"
      | sourceSystem | localPrecDocNo | localSPrecDocLnNo | localSubsDocNo | localSubsDocLnNo | localSubDocCatg | localBaseQuantity | localBaseUom | localSalesQuantity | localSalesUom | localPrecItemCatg | localCrtDt |
      | CONS_LATAM   | 116708516      | 490               | 195007613      | 10               | J               | 10.000            | CRT          | 1                  | CRT           | J                 | 20150624 |
      | CONS_LATAM   | 8306862        | 20                | 195007613      | 20               | J               | 5.000             | CRT          | 1                  | CRT           | J                 | 20150624 |
      | CONS_LATAM   | 8306862        | 20                | 195007613      | 20               | J               | 5.000             | CRT          | 1                  | CRT           | J                 | 20150624 |
      | CONS_LATAM   | 8306863        | 150               | 195007613      | 30               | J               | 3.000             | CRT          | 3                  | CRT           | J                 | 20150624 |
      | CONS_LATAM   | 116043204      | 180               | 195007613      | 40               | J               | 90.000            | CRT          | 5                  | CRT           | J                 | 20150624 |
      | CONS_LATAM   | 115689885      | 60                | 195007613      | 50               | J               | 1.000             | CRT          | 1                  | CRT           | J                 | 20150624 |
      | CONS_LATAM   | 40075674       | 60                | 195007613      | 60               | J               | 5.000             | CRT          | 5                  | CRT           | J                 | 20150624 |
      | CONS_LATAM   | 40075674       | 70                | 195007613      | 70               | J               | 3.000             | CRT          | 3                  | CRT           | J                 | 20150624 |
      | CONS_LATAM   | 40075674       | 80                | 195007613      | 80               | J               | 3.000             | CRT          | 3                  | CRT           | J                 | 20150624 |
      | CONS_LATAM   | 40075674       | 90                | 195007613      | 90               | J               | 3.000             | CRT          | 3                  | CRT           | J                 | 20150624 |
      | CONS_LATAM   | 40075674       | 100               | 195007613      | 100              | J               | 3.000             | CRT          | 3                  | CRT           | J                 | 20150624 |
      | CONS_LATAM   | 40075674       | 110               | 195007613      | 110              | J               | 3.000             | CRT          | 3                  | CRT           | J                 | 20150624 |
      | CONS_LATAM   | 40075674       | 120               | 195007613      | 120              | J               | 1.000             | CRT          | 1                  | CRT           | J                 | 20150624 |
    And I wait "/edm/sales_history_v1" Async Queue complete

    Given I import "/project_one/knvh" by keyFields "kunnr"
      | bokre | datab    | datbi    | grpno | hityp | hkunnr | hspart | hvkorg | hvtweg | hzuor | kunnr  | mandt | prfre | spart | vkorg | vtweg |
      | X     | 20171006 | 99991231 | 0     | A     | 13231  | 11     | BR01   | 10     | 0     | 192514 | 120   | X     | 11    | BR01  | 10    |
      | X     | 20180130 | 99991231 | 0     | A     | 361    | 10     | BR01   | 10     | 0     | 182493 | 120   | X     | 10    | BR01  | 10    |
      | X     | 20070901 | 99991231 | 0     | A     | 7386   | 11     | BR01   | 10     | 0     | 105032 | 120   | X     | 11    | BR01  | 10    |
      | X     | 20100629 | 99991231 | 0     | A     | 1185   | 10     | CO01   | 10     | 0     | 167049 | 120   | X     | 10    | CO01  | 10    |
      | X     | 20090504 | 99991231 | 0     | A     | 977    | 10     | CO01   | 10     | 0     | 163574 | 120   | X     | 10    | CO01  | 10    |
      | X     | 20170222 | 99991231 | 0     | A     | 13016  | 10     | MX02   | 11     | 0     | 193706 | 120   | X     | 10    | MX02  | 11    |
      | X     | 20070416 | 99991231 | 0     | A     |        |        |        |        | 1     | 4110   | 120   | X     | 10    | VE03  | 10    |
      | X     | 20170802 | 99991231 | 0     | A     | 11560  | 11     | MX02   | 10     | 0     | 198360 | 120   | X     | 11    | MX02  | 10    |
      | X     | 20020822 | 99991231 | 0     | B     | 726    | 10     | BR01   | 10     | 0     | 107724 | 120   | X     | 10    | BR01  | 10    |
    And I wait "/project_one/knvh" Async Queue complete

    Given I import "/plan/cns_dem_grp_asgn" by keyFields "companyCode,countryAffiliate,customerId,region,sourceSystem"
      | channel | channelDescription       | companyCode | countryAffiliate | customerId | customerName                        | customerShipTo | demandGroup | demandGroupDescription | demandGrpDescription           | fromDate | group | region | salesOrg | salesOrganization | sourceSystem | subFranchise | toDate |
      | CH010   | Other Mass               |             | BR               | 109038     | GRUPO COMERCIAL 15 DE NOVEMBRO LTDA |                | 76100007    |                        | OTHER MASS OTHER               |          |       |        | BR01     | BR01              | CONS_LATAM   |              |        |
      | CH010   | Other Mass               |             | BR               | 182493     | GRUPO PALHETA REFEICOES COLETIVAS L |                | 76100014    |                        | OTHER MASS OTHER               |          |       |        | BR01     |                   | CONS_LATAM   |              |        |
      | CH010   | Other Mass               |             | BR               | 283        | GRUPO MICROLITE S/A                 |                | 76100007    |                        | OTHER MASS OTHER               |          |       |        | BR01     |                   | CONS_LATAM   |              |        |
      | CH011   | Door to Doore/E-commerce |             | BR               | 12046      | DENTAL CRUZEIRO LTDA                |                | 76100014    |                        | Door to Doore/E-commerce OTHER |          |       |        | BR01     |                   | CONS_LATAM   |              |        |
      | CH006   | Distributor Traditional  |             | BR               | 96         | GRUPO RECOL REPR.E COMERCIO LTDA    |                | 76100031    |                        | RECOL                          |          |       |        | BR01     |                   | CONS_LATAM   |              |        |
      | CH011   | Door to Doore/E-commerce |             | BR               | 12518      | GRUPO BEBEDAMOUR                    |                | 76100014    |                        | Door to Doore/E-commerce OTHER |          |       |        | BR01     |                   | CONS_LATAM   |              |        |
      | CH011   | Door to Doore/E-commerce |             | BR               | 9385       | GRUPO PLUS COM DE PROD E EQUIP ODON |                | 76100014    |                        | Door to Doore/E-commerce OTHER |          |       |        | BR01     |                   | CONS_LATAM   |              |        |
      | CH007   | Wholesaler Traditional   |             | BR               | 11192      | GRUPO SARDAGNA                      |                | 76100009    |                        | Wholesaler Traditional OTHER   |          |       |        | BR01     |                   | CONS_LATAM   |              |        |
      | CH004   | Other Pharma             |             | BR               | 11992      | MEGA RIO LOGISTICA E DISTRIBUICAO D |                | 76100011    |                        | Other Pharmacy/Drugstore       |          |       |        | BR01     |                   | CONS_LATAM   |              |        |
      | CH010   | Other Mass               |             | BR               | 10643      | GRUPO FROHLICH S A IND E COM DE CER |                | 76100007    |                        | OTHER MASS OTHER               |          |       |        | BR01     |                   | CONS_LATAM   |              |        |
      | CH010   | Other Mass               |             | BR               | 469        | GRUPO SUPERMERCADO REAL DE EDEN LTD |                | 76100007    |                        | OTHER MASS OTHER               |          |       |        | BR01     |                   | CONS_LATAM   |              |        |
      | CH010   | Other Mass               |             | BR               | 510        | GRUPO NITER COMERCIO DE DROGAS LTDA |                | 76100007    |                        | OTHER MASS OTHER               |          |       |        | BR01     |                   | CONS_LATAM   |              |        |
      | CH007   | Wholesaler Traditional   |             | BR               | 9414       | GRUPO CASA NORTE LTDA               |                | 76100009    |                        | Wholesaler Traditional OTHER   |          |       |        | BR01     |                   | CONS_LATAM   |              |        |
      | CH010   | Other Mass               |             | BR               | 195        | GRUPO LOJAS BRASILEIRAS S/A         |                | 76100007    |                        | OTHER MASS OTHER               |          |       |        | BR01     |                   | CONS_LATAM   |              |        |
      | CH010   | Other Mass               |             | BR               | 382        | GRUPO FUCHS GEWURZE DO BRASIL LTDA. |                | 76100007    |                        | OTHER MASS OTHER               |          |       |        | BR01     |                   | CONS_LATAM   |              |        |
      | CH011   | Door to Doore/E-commerce |             | BR               | 12631      | DENTAL CURITIBANA COM PROD OD LTDA  |                | 76100014    |                        | Door to Doore/E-commerce OTHER |          |       |        | BR01     |                   | CONS_LATAM   |              |        |
      | CH010   | Other Mass               |             | BR               | 622        | GRUPO SUPERMERCADOS FEBERNATI SA    |                | 76100007    |                        | OTHER MASS OTHER               |          |       |        | BR01     |                   | CONS_LATAM   |              |        |
      | CH010   | Other Mass               |             | BR               | 7485       | GRUPO SUPERM BARONESA               |                | 76100007    |                        | OTHER MASS OTHER               |          |       |        | BR01     |                   | CONS_LATAM   |              |        |
      | CH010   | Other Mass               |             | BR               | 494        | GRUPO S.M.RASTELAO LTDA.            |                | 76100007    |                        | OTHER MASS OTHER               |          |       |        | BR01     |                   | CONS_LATAM   |              |        |
      | CH001   | Pharmacy/Drugstore       |             | BR               | 681        | GRUPO DIMED SA DISTRIB DE MEDICAMEN |                | 76100013    |                        | Pharmacy/Drugstore OTHER       |          |       |        | BR01     |                   | CONS_LATAM   |              |        |
      | CH001   | Pharmacy/Drugstore       |             | BR               | 9389       | GRUPO SANTA MARTA DISTRIB DE DROGAS |                | 76100013    |                        | Pharmacy/Drugstore OTHER       |          |       |        | BR01     |                   | CONS_LATAM   |              |        |
      | CH008   | Clubs/Cash & Carry       |             | BR               | 1348       | GRUPO MAKRO ATAC SUL                |                | 76100006    |                        | Clubs/Cash & Carry OTHER       |          |       |        | BR01     |                   | CONS_LATAM   |              |        |
      | CH010   | Other Mass               |             | BR               | 9667       | GRUPO FORTE MIX                     |                | 76100007    |                        | OTHER MASS OTHER               |          |       |        | BR01     |                   | CONS_LATAM   |              |        |
      | CH010   | Other Mass               |             | BR               | 207        | GRUPO HOTEIS OTHON S/A LEME PALACE  |                | 76100007    |                        | OTHER MASS OTHER               |          |       |        | BR01     |                   | CONS_LATAM   |              |        |
      | CH011   | Door to Doore/E-commerce |             | BR               | 12029      | GRUPO TONET COM DE MAT ODONTO       |                | 76100014    |                        | Door to Doore/E-commerce OTHER |          |       |        | BR01     |                   | CONS_LATAM   |              |        |
      | CH006   | Distributor Traditional  |             | BR               | 108657     | J. B. FILHO TRANSPORTES L           |                | 76100010    |                        | Distributor Traditional OTHER  |          |       |        | BR01     |                   | CONS_LATAM   |              |        |
      | CH005   | Modern Trade             |             | BR               | 7488       | GRUPO SAITO                         |                | 76100008    |                        | Modern Trade OTHER             |          |       |        | BR01     |                   | CONS_LATAM   |              |        |
      | CH007   | Wholesaler Traditional   |             | BR               | 491        | GRUPO COMERCIAL IKEDA LTDA          |                | 76100009    |                        | Wholesaler Traditional OTHER   |          |       |        | BR01     |                   | CONS_LATAM   |              |        |
      | CH001   | Pharmacy/Drugstore       |             | BR               | 8451       | GRUPO VANDROGAS                     |                | 76100013    |                        | Pharmacy/Drugstore OTHER       |          |       |        | BR01     |                   | CONS_LATAM   |              |        |
      | CH006   | Distributor Traditional  |             | BR               | 145698     | LSLOG ARMAZENAGEM E LOGIS           |                | 76100010    |                        | Distributor Traditional OTHER  |          |       |        | BR01     |                   | CONS_LATAM   |              |        |
      | CH010   | Other Mass               |             | BR               | 336        | GRUPO NESTLE BRASIL LTDA.           |                | 76100007    |                        | OTHER MASS OTHER               |          |       |        | BR01     |                   | CONS_LATAM   |              |        |
      | CH010   | Other Mass               |             | BR               | 149        | GRUPO CARRARO & ROCHA LTDA.         |                | 76100007    |                        | OTHER MASS OTHER               |          |       |        | BR01     |                   | CONS_LATAM   |              |        |
      | CH006   | Distributor Traditional  |             | BR               | 9538       | GRUPO ARMARINHOS  PARANÃ           |                | 76100010    |                        | Distributor Traditional OTHER  |          |       |        | BR01     |                   | CONS_LATAM   |              |        |
      | CH007   | Wholesaler Traditional   |             | BR               | 11626      | GRUPO COMPRE FACIL                  |                | 76100009    |                        | Wholesaler Traditional OTHER   |          |       |        | BR01     |                   | CONS_LATAM   |              |        |
      | CH005   | Modern Trade             |             | BR               | 9344       | GRUPO CASAS GUANABARA COMESTIVEIS L |                | 76100008    |                        | Modern Trade OTHER             |          |       |        | BR01     |                   | CONS_LATAM   |              |        |
      | CH007   | Wholesaler Traditional   |             | BR               | 12461      | GRUPO JOTA FOTE COM DE ALIMENTOS LT |                | 76100009    |                        | Wholesaler Traditional OTHER   |          |       |        | BR01     |                   | CONS_LATAM   |              |        |
      | CH011   | Door to Doore/E-commerce |             | BR               | 9347       | GRUPO D F COMERCIAL ODONTOLOGICA LT |                | 76100014    |                        | Door to Doore/E-commerce OTHER |          |       |        | BR01     |                   | CONS_LATAM   |              |        |
      | CH007   | Wholesaler Traditional   |             | BR               | 9460       | GRUPO PEROLA DISTRIBUICAO E LOGISTI |                | 76100009    |                        | Wholesaler Traditional OTHER   |          |       |        | BR01     |                   | CONS_LATAM   |              |        |
      | CH005   | Modern Trade             |             | BR               | 7771       | GRUPO CABRINI BERETTA CIA           |                | 76100008    |                        | Modern Trade OTHER             |          |       |        | BR01     |                   | CONS_LATAM   |              |        |
      | CH010   | Other Mass               |             | BR               | 395        | GRUPO G D L A GRAPHICS DESIGN AND A |                | 76100007    |                        | OTHER MASS OTHER               |          |       |        | BR01     |                   | CONS_LATAM   |              |        |
      | CH010   | Other Mass               |             | BR               | 456        | GRUPO SESC SERVICO SOCIAL DO COMERC |                | 76100007    |                        | OTHER MASS OTHER               |          |       |        | BR01     |                   | CONS_LATAM   |              |        |
      | CH011   | Door to Doore/E-commerce |             | BR               | 10656      | GRUPO DENTAL S SEBASTIAO LTDA       |                | 76100014    |                        | Door to Doore/E-commerce OTHER |          |       |        | BR01     |                   | CONS_LATAM   |              |        |
      | CH011   | Door to Doore/E-commerce |             | BR               | 12635      | ABCL DACACHE PROD ODONTOL LTDA      |                | 76100014    |                        | Door to Doore/E-commerce OTHER |          |       |        | BR01     |                   | CONS_LATAM   |              |        |
      | CH010   | Other Mass               |             | BR               | 166        | GRUPO NASSER S PRESENTES LTDA.      |                | 76100007    |                        | OTHER MASS OTHER               |          |       |        | BR01     |                   | CONS_LATAM   |              |        |
      | CH005   | Modern Trade             |             | BR               | 9911       | GRUPO ELSON                         |                | 76100008    |                        | Modern Trade OTHER             |          |       |        | BR01     |                   | CONS_LATAM   |              |        |
      | CH010   | Other Mass               |             | BR               | 461        | GRUPO LUCIO SILVEIRA & FILHOS       |                | 76100007    |                        | OTHER MASS OTHER               |          |       |        | BR01     |                   | CONS_LATAM   |              |        |
      | CH011   | Door to Doore/E-commerce |             | BR               | 9356       | GRUPO DENTAL SAO SEBASTIAO LTDA     |                | 76100014    |                        | Door to Doore/E-commerce OTHER |          |       |        | BR01     |                   | CONS_LATAM   |              |        |
      | CH011   | Door to Doore/E-commerce |             | BR               | 9335       | GRUPO ASSOCIACAO DOS LOJISTAS DO SH |                | 76100014    |                        | Door to Doore/E-commerce OTHER |          |       |        | BR01     |                   | CONS_LATAM   |              |        |
      | CH011   | Door to Doore/E-commerce |             | BR               | 11022      | GRUPO DENTAL SORRISO                |                | 76100014    |                        | Door to Doore/E-commerce OTHER |          |       |        | BR01     |                   | CONS_LATAM   |              |        |
    And I wait "/plan/cns_dem_grp_asgn" Async Queue complete

    Given I import "/plan/cns_cust_excl_incl" by keyFields "country,customerShipTo,inclExcl,salesOrg,sourceSystem"
      | country | customerShipTo | inclExcl | salesOrg | sourceSystem |
      | CL      | 179318         | E        | CL01     | CON_LATAM    |
      | GT      | 163106         | E        | GT01     | CON_LATAM    |
      | DO      | 170131         | E        | DO01     | CON_LATAM    |
      | EC      | ALL            | I        | EC01     | CON_LATAM    |
      | CR      | 155266         | E        | CR01     | CON_LATAM    |
      | MX      | 155266         | E        | MX02     | CON_LATAM    |
      | PA      | 194044         | E        | PA01     | CON_LATAM    |
      | CO      | ALL            | I        | CO01     | CON_LATAM    |
      | PA      | 160654         | I        | PA01     | CON_LATAM    |
      | MX      | 163105         | E        | MX02     | CON_LATAM    |
      | PA      | 161325         | E        | PA01     | CON_LATAM    |
      | PA      | 160663         | I        | PA01     | CON_LATAM    |
      | DO      | 187013         | E        | DO01     | CON_LATAM    |
      | GT      | 194044         | E        | GT01     | CON_LATAM    |
      | PA      | 155266         | E        | PA01     | CON_LATAM    |
      | MX      | 194044         | E        | MX02     | CON_LATAM    |
      | GT      | 170155         | E        | GT01     | CON_LATAM    |
      | PA      | 160665         | I        | PA01     | CON_LATAM    |
      | CL      | 179302         | E        | CL01     | CON_LATAM    |
      | GT      | 187013         | E        | GT01     | CON_LATAM    |
      | DO      | 194044         | E        | DO01     | CON_LATAM    |
      | PA      | 187013         | E        | PA01     | CON_LATAM    |
      | CR      | 157293         | E        | CR01     | CON_LATAM    |
      | GT      | 157293         | E        | GT01     | CON_LATAM    |
      | PA      | 162763         | E        | PA01     | CON_LATAM    |
      | CR      | 187013         | E        | CR01     | CON_LATAM    |
      | PA      | 157293         | E        | PA01     | CON_LATAM    |
      | DO      | 162763         | E        | DO01     | CON_LATAM    |
      | PA      | 160664         | I        | PA01     | CON_LATAM    |
      | GT      | 157381         | E        | GT01     | CON_LATAM    |
      | MX      | 162763         | E        | MX02     | CON_LATAM    |
      | DO      | 155266         | E        | DO01     | CON_LATAM    |
      | PA      | 157294         | E        | PA01     | CON_LATAM    |
      | CR      | 187012         | E        | CR01     | CON_LATAM    |
      | UY      | 164133         | E        | UY01     | CON_LATAM    |
      | BR      | 109038         | I        | BR01     | CON_LATAM    |
      | CR      | 162763         | E        | CR01     | CON_LATAM    |
      | MX      | 165139         | E        | MX02     | CON_LATAM    |
      | GT      | 165139         | E        | GT01     | CON_LATAM    |
      | CR      | 170155         | E        | CR01     | CON_LATAM    |
      | AR      | 172904         | E        | AR02     | CON_LATAM    |
      | CL      | 189954         | E        | CL01     | CON_LATAM    |
      | MX      | 160548         | E        | MX02     | CON_LATAM    |
      | PE      | ALL            | I        | PE01     | CON_LATAM    |
      | DO      | 170155         | E        | DO01     | CON_LATAM    |
      | MX      | 187013         | E        | MX02     | CON_LATAM    |
      | CL      | 179321         | E        | CL01     | CON_LATAM    |
      | GT      | 189403         | E        | GT01     | CON_LATAM    |
      | AR      | 171110         | E        | AR02     | CON_LATAM    |
      | MX      | 161325         | E        | MX02     | CON_LATAM    |
      | CR      | 163106         | E        | CR01     | CON_LATAM    |
      | PA      | 176913         | I        | PA01     | CON_LATAM    |
      | DO      | 161325         | E        | DO01     | CON_LATAM    |
      | MX      | 157381         | E        | MX02     | CON_LATAM    |
      | CR      | 161325         | E        | CR01     | CON_LATAM    |
      | PA      | 170131         | E        | PA01     | CON_LATAM    |
      | PA      | 171845         | I        | PA01     | CON_LATAM    |
      | AR      | 149410         | I        | AR01     | CON_LATAM    |
      | CR      | 194044         | E        | CR01     | CON_LATAM    |
      | PA      | 163106         | E        | PA01     | CON_LATAM    |
      | MX      | 157295         | E        | MX02     | CON_LATAM    |
      | PA      | 170155         | E        | PA01     | CON_LATAM    |
      | PA      | 172249         | I        | PA01     | CON_LATAM    |
      | AR      | 170961         | E        | AR02     | CON_LATAM    |
      | CR      | 160548         | E        | CR01     | CON_LATAM    |
      | MX      | 187012         | E        | MX02     | CON_LATAM    |
      | PA      | 160548         | E        | PA01     | CON_LATAM    |
      | CR      | 157381         | E        | CR01     | CON_LATAM    |
      | CR      | 170131         | E        | CR01     | CON_LATAM    |
      | GT      | 155266         | E        | GT01     | CON_LATAM    |
      | GT      | 162763         | E        | GT01     | CON_LATAM    |
      | AR      | 125097         | E        | AR02     | CON_LATAM    |
      | MX      | 187011         | E        | MX02     | CON_LATAM    |
      | DO      | 157381         | E        | DO01     | CON_LATAM    |
      | PA      | 160652         | I        | PA01     | CON_LATAM    |
      | DO      | 189403         | E        | DO01     | CON_LATAM    |
      | CR      | 157295         | E        | CR01     | CON_LATAM    |
      | UY      | 149649         | E        | UY01     | CON_LATAM    |
      | GT      | 157294         | E        | GT01     | CON_LATAM    |
      | MX      | 161892         | E        | MX02     | CON_LATAM    |
      | GT      | 187012         | E        | GT01     | CON_LATAM    |
      | DO      | 187012         | E        | DO01     | CON_LATAM    |
      | DO      | 157293         | E        | DO01     | CON_LATAM    |
      | PY      | 146111         | E        | PY01     | CON_LATAM    |
      | GT      | 161325         | E        | GT01     | CON_LATAM    |
      | DO      | 163105         | E        | DO01     | CON_LATAM    |
      | CR      | 161892         | E        | CR01     | CON_LATAM    |
      | PA      | 161892         | E        | PA01     | CON_LATAM    |
      | DO      | 157294         | E        | DO01     | CON_LATAM    |
      | PA      | 189403         | E        | PA01     | CON_LATAM    |
      | DO      | 187011         | E        | DO01     | CON_LATAM    |
      | PA      | 157381         | E        | PA01     | CON_LATAM    |
      | MX      | 170131         | E        | MX02     | CON_LATAM    |
      | PA      | 165139         | E        | PA01     | CON_LATAM    |
      | AR      | 163990         | E        | AR02     | CON_LATAM    |
      | MX      | 163106         | E        | MX02     | CON_LATAM    |
      | CL      | 179304         | E        | CL01     | CON_LATAM    |
      | MX      | 170155         | E        | MX02     | CON_LATAM    |
      | CR      | 189403         | E        | CR01     | CON_LATAM    |
      | DO      | 163106         | E        | DO01     | CON_LATAM    |
      | CL      | 179311         | E        | CL01     | CON_LATAM    |
      | GT      | 187011         | E        | GT01     | CON_LATAM    |
      | PA      | 163105         | E        | PA01     | CON_LATAM    |
      | MX      | 189403         | E        | MX02     | CON_LATAM    |
      | PA      | 160644         | I        | PA01     | CON_LATAM    |
      | UY      | 161713         | E        | UY01     | CON_LATAM    |
      | PA      | 160658         | I        | PA01     | CON_LATAM    |
      | UY      | 149875         | E        | UY01     | CON_LATAM    |
      | DO      | 160548         | E        | DO01     | CON_LATAM    |
      | GT      | 157295         | E        | GT01     | CON_LATAM    |
      | GT      | 163105         | E        | GT01     | CON_LATAM    |
      | UY      | 191049         | E        | UY01     | CON_LATAM    |
      | CR      | 163105         | E        | CR01     | CON_LATAM    |
      | DO      | 161892         | E        | DO01     | CON_LATAM    |
      | DO      | 157295         | E        | DO01     | CON_LATAM    |
      | DO      | 165139         | E        | DO01     | CON_LATAM    |
      | AR      | 182664         | E        | AR02     | CON_LATAM    |
      | GT      | 182661         | E        | GT01     | CON_LATAM    |
      | GT      | 170131         | E        | GT01     | CON_LATAM    |
      | CR      | 157294         | E        | CR01     | CON_LATAM    |
      | MX      | 157293         | E        | MX02     | CON_LATAM    |
      | CR      | 165139         | E        | CR01     | CON_LATAM    |
      | PA      | 187011         | E        | PA01     | CON_LATAM    |
      | PA      | 187012         | E        | PA01     | CON_LATAM    |
      | BR      | 182493         | I        | BR01     | CON_LATAM    |
      | CL      | 193714         | E        | CL01     | CON_LATAM    |
      | PA      | 157295         | E        | PA01     | CON_LATAM    |
      | CR      | 187011         | E        | CR01     | CON_LATAM    |
      | MX      | 157294         | E        | MX02     | CON_LATAM    |
      | GT      | 161892         | E        | GT01     | CON_LATAM    |
      | GT      | 160548         | E        | GT01     | CON_LATAM    |
    And I wait "/plan/cns_cust_excl_incl" Async Queue complete

    Given I import "/plan/cns_so_type_incl_excl" by keyFields "country,orderType,plant,salesOrg,sourceSystem"
      | country | inclExcl | orderType | plant | salesOrg | sourceSystem |
      | GT      | I        | ZLVE      | GT01  | GT01     | CONS_LATAM   |
      | CR      | I        | ZLVE      | CR01  | CR01     | CONS_LATAM   |
      | BR      | I        | ZGRA      | BR16  | BR01     | CONS_LATAM   |
      | BR      | I        | ZSRT      | BR19  | BR01     | CONS_LATAM   |
      | DO      | I        | ZLSR      | DO01  | DO01     | CONS_LATAM   |
      | PA      | I        | ZLGR      | PA03  | PA01     | CONS_LATAM   |
      | MX      | I        | ZLSR      | MX02  | MX02     | CONS_LATAM   |
      | AR      | I        | ZLDP      | AR01  | AR01     | CONS_LATAM   |
      | BR      | I        | ZSRT      | BR07  | BR01     | CONS_LATAM   |
      | AR      | I        | ZLSA      | AR02  | AR02     | CONS_LATAM   |
      | BR      | I        | ZORB      | BR14  | BR01     | CONS_LATAM   |
      | BR      | I        | ZSRT      | BR16  | BR01     | CONS_LATAM   |
      | EC      | I        | ZLVE      | BR13  | EC01     | CONS_LATAM   |
      | BR      | I        | ZGRA      | BR25  | BR01     | CONS_LATAM   |
      | PY      | I        | ZLSR      | PY01  | PY01     | CONS_LATAM   |
      | CO      | I        | ZLGR      | CO02  | CO01     | CONS_LATAM   |
      | CR      | I        | ZLGR      | CR01  | CR01     | CONS_LATAM   |
      | PY      | I        | ZLGR      | PY01  | PY01     | CONS_LATAM   |
      | PA      | I        | ZLEX      | PA03  | PA01     | CONS_LATAM   |
      | EC      | I        | ZLGR      | EC01  | EC01     | CONS_LATAM   |
      | CO      | I        | ZLSR      | CO02  | CO01     | CONS_LATAM   |
      | BR      | I        | ZGRA      | BR19  | BR01     | CONS_LATAM   |
      | PE      | I        | ZLPE      | PE01  | PE01     | CONS_LATAM   |
      | CL      | I        | ZLGR      | CL01  | CL01     | CONS_LATAM   |
      | CL      | I        | ZLSR      | CL01  | CL01     | CONS_LATAM   |
      | UY      | I        | ZLSR      | UY01  | UY01     | CONS_LATAM   |
      | UY      | I        | ZLGR      | UY01  | UY01     | CONS_LATAM   |
      | UY      | I        | ZLVE      | UY01  | UY01     | CONS_LATAM   |
      | CO      | I        | ZLVE      | CO02  | CO01     | CONS_LATAM   |
      | CR      | I        | ZLSR      | CR01  | CR01     | CONS_LATAM   |
      | PY      | I        | ZLEX      | PY04  | PY01     | CONS_LATAM   |
      | PA      | I        | ZLGR      | PA01  | PA01     | CONS_LATAM   |
      | PY      | I        | ZLVE      | PY01  | PY01     | CONS_LATAM   |
      | MX      | I        | ZLVE      | MX02  | MX02     | CONS_LATAM   |
      | DO      | I        | ZLVE      | DO01  | DO01     | CONS_LATAM   |
      | DO      | I        | ZLGR      | DO01  | DO01     | CONS_LATAM   |
      | GT      | I        | ZLGR      | GT01  | GT01     | CONS_LATAM   |
      | BR      | I        | ZGRA      | CO02  | BR01     | CONS_LATAM   |
      | AR      | I        | ZLVE      | AR02  | AR02     | CONS_LATAM   |
      | PE      | I        | ZLGR      | PE01  | PE01     | CONS_LATAM   |
      | BR      | I        | ZORB      | BR16  | BR01     | CONS_LATAM   |
      | BR      | I        | ZSRT      | BR25  | BR01     | CONS_LATAM   |
      | PA      | I        | ZLSR      | PA03  | PA01     | CONS_LATAM   |
      | CL      | I        | ZLVE      | CL01  | CL01     | CONS_LATAM   |
      | PA      | I        | ZLSR      | PA01  | PA01     | CONS_LATAM   |
      | BR      | I        | ZORB      | BR07  | BR01     | CONS_LATAM   |
      | AR      | I        | ZLSR      | AR02  | AR02     | CONS_LATAM   |
      | PE      | I        | ZLVE      | PE01  | PE01     | CONS_LATAM   |
      | GT      | I        | ZLSR      | GT01  | GT01     | CONS_LATAM   |
      | AR      | I        | ZLGR      | AR02  | AR02     | CONS_LATAM   |
      | BR      | I        | ZORB      | BR19  | BR01     | CONS_LATAM   |
      | PA      | I        | ZLVE      | PA01  | PA01     | CONS_LATAM   |
      | BR      | I        | ZSRT      | BR14  | BR01     | CONS_LATAM   |
    And I wait "/plan/cns_so_type_incl_excl" Async Queue complete

    When I submit aggregation task with xml file "xml/edm/EDMSalesHistoryAggregation.xml"

    When I submit task with xml file "xml/gdm_demand_sales_order.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMDemandSalesOrder.tsv"

    And I check file data for filename "GDMDemandSalesOrder.tsv" by keyFields "demandId"
      | active | activeFCTERP | activeOPRERP | activeSOPERP | batchId | certaintyId | customerId | demandId                            | dueDate             | fromDueDate         | inventoryLinkGroupId | locationId      | minRemainingShelfLife | planningStrategy        | productId | quantity | unitId | wRK02 |
      | YES    | NO           | YES          | NO           |         | VC          | 76100007   | 58752/CONS_LATAM_BR16/116708516/490 | 2016/06/03 00:00:01 | 2016/06/03 00:00:00 |                      | CONS_LATAM_BR16 | 150                   | ProductLocationBalanced | 58752     | 330.00   | EA     |       |
      | YES    | NO           | YES          | NO           |         | VC          | 76100014   | 57039/CONS_LATAM_BR14/8306862/20    | 2018/01/16 00:00:01 | 2018/01/16 00:00:00 |                      | CONS_LATAM_BR14 | 120                   | ProductLocationBalanced | 57039     | 28.00    | EA     |       |
      | YES    | NO           | YES          | NO           |         | VC          |            | 60520/CONS_LATAM_br13/8306863/150   | 2018/02/24 00:00:01 | 2018/02/24 00:00:00 |                      | CONS_LATAM_BR13 | 9                     | ProductLocationBalanced | 60520     | 2017.00  | EA     |       |
      | YES    | NO           | YES          | NO           |         | VC          | 76100014   | 91732/CONS_LATAM_BR14/115689885/60  | 2018/04/02 00:00:01 | 2018/04/02 00:00:00 |                      | CONS_LATAM_BR14 | 120                   | ProductLocationBalanced | 91732     | 37.00    | EA     |       |

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/omp/gdm_demand_sales_order"

    And I will remove the test file on sink application "GDMDemandSalesOrder.tsv"
