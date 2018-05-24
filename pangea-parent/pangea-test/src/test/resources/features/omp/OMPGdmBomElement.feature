@pangea_test @AEAZ-4250
Feature: OMPGdmBomElement AEAZ-4250

  Scenario: check rule  J1

    Given I import "/edm/matl_bom" by keyFields "srcSysCd,matlNum,plntCd,bomUsgCd,bomNum,altBomNum"
      | srcSysCd | matlNum            | plntCd | altBomNum | sourceSystem | bomNum | bomUsgCd | attribute |
      | 0001     | 000000000000203700 | M001   | A001      | CONS_LATAM   | B001   | 0001     | att01     |
      | 0002     | 000000000000203701 | M002   | A002      | CONS_LATAM   | B002   | 0002     | att02     |
      #cns_plan_parameter-sourceSystem = MATL_BOM-sourceSystem
      | 0003     | 000000000000203702 | M003   | A003      | NAN_HUANG    | B003   | 0003     | att03     |
      #MATL_BOM-srcSysCd= MATL_PROD_VERSN-srcSysCd
      | 0004     | 000000000000203703 | M004   | A004      | CONS_LATAM   | B004   | 0004     | att04     |
      # MATL_BOM-matlNum   = MATL_PROD_VERSN-matlNum
      | 0005     | 000000000000203704 | M005   | A005      | CONS_LATAM   | B005   | 0005     | att05     |
      #  MATL_BOM-plntCd= MATL_PROD_VERSN-plntCd
      | 0006     | 000000000000203705 | M006   | A006      | CONS_LATAM   | B006   | 0006     | att06     |
      #MATL_BOM-altBomNum   = MATL_PROD_VERSN-altBomNum
      | 0007     | 000000000000203706 | M007   | A007      | CONS_LATAM   | B007   | 0007     | att07     |
      | 0008     | 000000000000203707 | M008   | A008      | CONS_LATAM   | B008   | 0008     | att08     |
      | 0009     | 000000000000203708 | M009   | A009      | CONS_LATAM   | B009   | 0009     | att09     |
      | 0010     | 000000000000203709 | M010   | A010      | CONS_LATAM   | B010   | 0010     | att10     |
      | 0011     | 000000000000203710 | M011   | A011      | CONS_LATAM   | B011   | 0011     | att11     |
      | 0012     | 000000000000203711 | M012   | A012      | CONS_LATAM   | B012   | 0012     | att12     |
      | 0013     | 000000000000203712 | M013   | A013      | CONS_LATAM   | B013   | 0013     | att13     |
      | 0014     | 000000000000203713 | M014   | A014      | CONS_LATAM   | B014   | 0014     | att14     |
      | 0015     | 000000000000203714 | M015   | A015      | CONS_LATAM   | B015   | 0015     | att15     |
      | 0016     | 000000000000203715 | M016   | A016      | CONS_LATAM   | B016   | 0016     | att16     |
      #  BOM_HDR-srcSysCd  = MATL_BOM-srcSysCd
      | 0017     | 000000000000203716 | M017   | A017      | CONS_LATAM   | B017   | 0017     | att17     |
      #  BOM_HDR-bomNum   = MATL_BOM-bomNum
      | 0018     | 000000000000203717 | M018   | A018      | CONS_LATAM   | B018   | 0018     | att18     |
      #  BOM_HDR-altBomNum  = MATL_BOM-altBomNum
      | 0019     | 000000000000203718 | M019   | A019      | CONS_LATAM   | B019   | 0019     | att19     |
    And I wait "/edm/matl_bom" Async Queue complete

    Given I import "/edm/material_plant_v1" by keyFields "localMaterialNumber,localPlant"
      | localMaterialNumber | localPlant | sourceSystem  | localGoodsReceiptProcessingTimeInDays |
      | 000000000000203700  | M001       | sourceSystem1 | 1                                     |
      | 000000000000203701  | M002       | sourceSystem2 | 2                                     |
      | 000000000000203702  | M003       | sourceSystem3 | 3                                     |
    And I wait "/edm/material_plant_v1" Async Queue complete

    Given I import "/edm/bom_item" by keyFields "srcSysCd,bomCatCd,bomNum"
      | bomNum | srcSysCd | bomCatCd | cmpntNum | bomItmNum | bomItmVldFromDt     | dstrbtnKeyCd | fxQtyInd | bomItmVldToDt       | leadTimeOffst | cmpntQty | cmpntScrap_Pct | cmpntUomCd |
      | B001   | 0001     | M        | 001      | 001       | 2018/05/15 00:00:00 | 001          | 001      | 2018/05/16 00:00:00 | 001           | 001      | 001            | 001        |
      | B002   | 0002     | M        | 002      | 002       | 2018/05/16 00:00:00 | 002          | 002      | 2018/05/17 00:00:00 | 002           | 002      | 002            | 002        |
      | B003   | 0003     | M        | 003      | 003       | 2018/05/17 00:00:00 | 003          | 003      | 2018/05/18 00:00:00 | 003           | 003      | 003            | 003        |
       #BOM_HDR-bomCatCd  = BOM_ITEM-bomCatCd
      | B004   | 0004     | N        | 004      | 004       | 2018/05/18 00:00:00 | 004          | 004      | 2018/05/19 00:00:00 | 004           | 004      | 004            | 004        |

    And I wait "/edm/bom_item" Async Queue complete

    Given I import "/edm/bom_hdr" by keyFields "srcSysCd,bomCatCd,bomNum,altBomNum"
      | srcSysCd | altBomNum | bomNum | bomCatCd | bomVldFromDt        | bomBaseQty | bomUomCd |
      | 0001     | A001      | B001   | M        | 2018/05/15 00:00:00 | 0001       | 0001     |
      | 0002     | A002      | B002   | M        | 2018/05/16 00:00:00 | 0002       | 0002     |
      | 0003     | A003      | B003   | M        | 2018/05/17 00:00:00 | 0003       | 0003     |
      | 0004     | A004      | B004   | M        | 2018/05/18 00:00:00 | 0003       | 0003     |
      | 0005     | A005      | B005   | M        | 2018/05/19 00:00:00 | 0003       | 0003     |
      | 0006     | A006      | B006   | M        | 2018/05/20 00:00:00 | 0003       | 0003     |
      | 0007     | A007      | B007   | M        | 2018/05/21 00:00:00 | 0003       | 0003     |
      | 0008     | A008      | B008   | M        | 2018/05/22 00:00:00 | 0003       | 0003     |
      | 0009     | A009      | B009   | M        | 2018/05/23 00:00:00 | 0003       | 0003     |
      | 0010     | A010      | B010   | M        | 2018/05/24 00:00:00 | 0003       | 0003     |
      | 0011     | A011      | B011   | M        | 2018/05/25 00:00:00 | 0003       | 0003     |
      | 0012     | A012      | B012   | M        | 2018/05/26 00:00:00 | 0003       | 0003     |
      | 0013     | A013      | B013   | M        | 2018/05/27 00:00:00 | 0003       | 0003     |
      | 0014     | A014      | B014   | M        | 2018/05/28 00:00:00 | 0003       | 0003     |
      | 0015     | A015      | B015   | M        | 2018/05/29 00:00:00 | 0003       | 0003     |
      | 0016     | A016      | B016   | M        | 2018/05/30 00:00:00 | 0003       | 0003     |
    And I wait "/edm/bom_hdr" Async Queue complete

    Given I import "/plan/cns_plan_parameter" by keyFields "sourceSystem,dataObject,attribute"
      | sourceSystem | attribute | dataObject               | parameterValue |
      | CONS_LATAM   | att01     | cns_material_plan_status | para01         |
#      cns_plan_parameter-dataObject = 'SEND_TO_OMP'
      | CONS_LATAM   | att02     | SEND_TO_OMP              | para02         |
      | CONS_LATAM   | att03     | cns_material_plan_status | para03         |
    And I wait "/plan/cns_plan_parameter" Async Queue complete

    Given I import "/edm/matl_prod_versn" by keyFields "srcSysCd,matlNum,plntCd,prdntVrsnNum"
      | srcSysCd | plntCd | altBomNum | matlNum            | rtngGrpCntrNum | rtngGrpCd | prdntVrsnNum | dstrbtnKeyCd | valFromDt           | valToDt             |
      | 0001     | M001   | A001      | 000000000000203700 | cntr001        | cd001     | vrsn001      | key001       | 2018/05/15 00:00:00 | 2018/05/16 00:00:00 |
      | 0002     | M002   | A002      | 000000000000203701 | cntr002        | cd002     | vrsn002      | key002       | 2018/05/16 00:00:00 | 2018/05/17 00:00:00 |
      #cns_plan_parameter-sourceSystem = MATL_BOM-sourceSystem
      | 0003     | M003   | A003      | 000000000000203702 | cntr003        | cd003     | vrsn003      | key003       | 2018/05/17 00:00:00 | 2018/05/18 00:00:00 |
      #MATL_BOM-srcSysCd= MATL_PROD_VERSN-srcSysCd
      | 0005     | M004   | A004      | 000000000000203703 | cntr004        | cd004     | vrsn004      | key004       | 2018/05/18 00:00:00 | 2018/05/19 00:00:00 |
      # MATL_BOM-matlNum   = MATL_PROD_VERSN-matlNum
      | 0005     | M005   | A005      | 000000000000203704 | cntr005        | cd005     | vrsn005      | key005       | 2018/05/19 00:00:00 | 2018/05/20 00:00:00 |
      #  MATL_BOM-plntCd= MATL_PROD_VERSN-plntCd
      | 0006     | M005   | A006      | 000000000000203705 | cntr006        | cd006     | vrsn006      | key006       | 2018/05/20 00:00:00 | 2018/05/21 00:00:00 |
       #MATL_BOM-altBomNum   = MATL_PROD_VERSN-altBomNum
      | 0007     | M007   | A006      | 000000000000203706 | cntr007        | cd007     | vrsn007      | key007       | 2018/05/21 00:00:00 | 2018/05/22 00:00:00 |
      #  MATL_PROD_VERSN-srcSysCd= MATL_MFG_RTNG-srcSysCd
      | 0008     | M008   | A008      | 000000000000203707 | cntr008        | cd008     | vrsn008      | key008       | 2018/05/22 00:00:00 | 2018/05/23 00:00:00 |
      # MATL_PROD_VERSN-matlNum  = MATL_MFG_RTNG-matlNum
      | 0009     | M009   | A009      | 000000000000203708 | cntr009        | cd009     | vrsn009      | key009       | 2018/05/23 00:00:00 | 2018/05/24 00:00:00 |
      #MATL_PROD_VERSN-plntCd  = MATL_MFG_RTNG-plntCd
      | 0010     | M010   | A010      | 000000000000203709 | cntr010        | cd010     | vrsn010      | key010       | 2018/05/24 00:00:00 | 2018/05/25 00:00:00 |
      #MATL_PROD_VERSN-rtngGrpCd   = MATL_MFG_RTNG-rtngGrpCd
      | 0011     | M011   | A011      | 000000000000203710 | cntr011        | cd011     | vrsn011      | key011       | 2018/05/25 00:00:00 | 2018/05/26 00:00:00 |
      #MATL_PROD_VERSN-rtngGrpcntrNum    = MATL_MFG_RTNG-rtngGrpcntrNum
      | 0012     | M012   | A012      | 000000000000203711 | cntr012        | cd012     | vrsn012      | key012       | 2018/05/26 00:00:00 | 2018/05/27 00:00:00 |
      | 0013     | M013   | A013      | 000000000000203712 | cntr013        | cd013     | vrsn013      | key013       | 2018/05/27 00:00:00 | 2018/05/28 00:00:00 |
      | 0014     | M014   | A014      | 000000000000203713 | cntr014        | cd014     | vrsn014      | key014       | 2018/05/28 00:00:00 | 2018/05/29 00:00:00 |
      | 0015     | M015   | A015      | 000000000000203714 | cntr015        | cd015     | vrsn015      | key015       | 2018/05/29 00:00:00 | 2018/05/30 00:00:00 |
      | 0016     | M016   | A016      | 000000000000203715 | cntr016        | cd016     | vrsn016      | key016       | 2018/05/30 00:00:00 | 2018/05/31 00:00:00 |
    And I wait "/edm/matl_prod_versn" Async Queue complete

    Given I import "/edm/matl_mfg_rtng" by keyFields "srcSysCd,matlNum,plntCd,rtngTypCd,rntgGrpCd,rntgGrpCntrNbr,rntgAddtnlCntrNbr,matlRtngVrsnCntrNbr"
      | srcSysCd | rtngGrpCd | rtngGrpcntrNum | plntCd | matlNum            | rntgGrpCntrNbr | rtngTypCd |
      | 0001     | cd001     | cntr001        | M001   | 000000000000203700 | nbr001         | typ001    |
      | 0002     | cd002     | cntr002        | M002   | 000000000000203701 | nbr002         | typ002    |
      | 0003     | cd003     | cntr003        | M003   | 000000000000203702 | nbr003         | typ003    |
      | 0004     | cd004     | cntr004        | M004   | 000000000000203703 | nbr004         | typ004    |
      | 0005     | cd005     | cntr005        | M005   | 000000000000203704 | nbr005         | typ005    |
      | 0006     | cd006     | cntr006        | M006   | 000000000000203705 | nbr006         | typ006    |
      | 0007     | cd007     | cntr007        | M007   | 000000000000203706 | nbr007         | typ007    |
      #  MATL_PROD_VERSN-srcSysCd= MATL_MFG_RTNG-srcSysCd
      | 0007     | cd008     | cntr008        | M008   | 000000000000203707 | nbr008         | typ008    |
      #  MATL_PROD_VERSN-matlNum  = MATL_MFG_RTNG-matlNum
      | 0009     | cd009     | cntr009        | M009   | 000000000000203707 | nbr009         | typ009    |
      #MATL_PROD_VERSN-plntCd  = MATL_MFG_RTNG-plntCd
      | 0010     | cd010     | cntr010        | M009   | 000000000000203709 | nbr010         | typ010    |
      #MATL_PROD_VERSN-rtngGrpCd   = MATL_MFG_RTNG-rtngGrpCd
      | 0011     | cd010     | cntr011        | M011   | 000000000000203710 | nbr011         | typ011    |
      #MATL_PROD_VERSN-rtngGrpcntrNum    = MATL_MFG_RTNG-rtngGrpcntrNum
      | 0012     | cd012     | cntr011        | M012   | 000000000000203711 | nbr012         | typ012    |
      | 0013     | cd013     | cntr013        | M013   | 000000000000203712 | nbr013         | typ013    |
      | 0014     | cd014     | cntr014        | M014   | 000000000000203713 | nbr014         | typ014    |
      | 0015     | cd015     | cntr015        | M015   | 000000000000203714 | nbr015         | typ015    |
      | 0016     | cd016     | cntr016        | M016   | 000000000000203715 | nbr016         | typ016    |
    And I wait "/edm/matl_mfg_rtng" Async Queue complete

    Given I import "/edm/mfg_rtng_itm_nde" by keyFields "srcSysCd,rtngTypCd,rtngGrpCd,rtngGrpCntrNbr,rtngSqncNum,rtngNdeNum,rtngNdeVrsnCntrNbr"
      | srcSysCd | rtngTypCd | rtngGrpCntrNbr | rtngGrpCd | rtngNdeNum |
      | 0001     | typ001    | cntr001        | cd001     | nde001     |
      | 0002     | typ002    | cntr002        | cd002     | nde002     |
      | 0003     | typ003    | cntr003        | cd003     | nde003     |
      | 0004     | typ004    | cntr004        | cd004     | nde004     |
      | 0005     | typ005    | cntr005        | cd005     | nde005     |
      | 0006     | typ006    | cntr006        | cd006     | nde006     |
      | 0007     | typ007    | cntr007        | cd007     | nde007     |
      | 0007     | typ008    | cntr008        | cd008     | nde008     |
      | 0009     | typ009    | cntr009        | cd009     | nde009     |
      | 0010     | typ010    | cntr010        | cd010     | nde010     |
      | 0011     | typ011    | cntr011        | cd010     | nde011     |
      | 0012     | typ012    | cntr011        | cd012     | nde012     |
      #MFG_RTNG_ITM_NDE-srcSysCd  =  MFG_RTNG_ITM-srcSysCd
      | 0013     | typ013    | cntr013        | cd013     | nde013     |
      #MFG_RTNG_ITM_NDE-rtngTypCd     = MFG_RTNG_ITM-rtngTypCd
      | 0014     | typ014    | cntr014        | cd014     | nde014     |
      #MFG_RTNG_ITM_NDE-rtngGrpCd  = MFG_RTNG_ITM-rtngGrpCd
      | 0015     | typ015    | cntr015        | cd015     | nde015     |
      #MFG_RTNG_ITM_NDE-rtngNdeNum = MFG_RTNG_ITM-rtngItmNum
      | 0016     | typ016    | cntr016        | cd016     | nde016     |

    And I wait "/edm/mfg_rtng_itm_nde" Async Queue complete

    Given I import "/edm/mfg_rtng_itm" by keyFields "srcSysCd,rtngTypCd,rtngGrpCd,rtngItmNum,rtngItmVersnCntrNbr"
      | srcSysCd | rtngTypCd | rtngItmNum | rtngGrpCd | operNum | bsQty |
      | 0001     | typ001    | nde001     | cd001     | oper001 | 1.000 |
      | 0002     | typ002    | nde002     | cd002     | oper002 | 1.000 |
      | 0003     | typ003    | nde003     | cd003     | oper003 | 1.000 |
      | 0004     | typ004    | nde004     | cd004     | oper004 | 1.000 |
      | 0005     | typ005    | nde005     | cd005     | oper005 | 1.000 |
      | 0006     | typ006    | nde006     | cd006     | oper006 | 1.000 |
      | 0007     | typ007    | nde007     | cd007     | oper007 | 1.000 |
      | 0007     | typ008    | nde008     | cd008     | oper008 | 1.000 |
      | 0009     | typ009    | nde009     | cd009     | oper009 | 1.000 |
      | 0010     | typ010    | nde010     | cd010     | oper010 | 1.000 |
      | 0011     | typ011    | nde011     | cd010     | oper011 | 1.000 |
      | 0012     | typ012    | nde012     | cd012     | oper012 | 1.000 |
      #MFG_RTNG_ITM_NDE-srcSysCd  =  MFG_RTNG_ITM-srcSysCd
      | 0012     | typ013    | nde013     | cd013     | oper013 | 1.000 |
      #MFG_RTNG_ITM_NDE-rtngTypCd     = MFG_RTNG_ITM-rtngTypCd
      | 0014     | typ013    | nde014     | cd014     | oper014 | 1.000 |
      #MFG_RTNG_ITM_NDE-rtngGrpCd  = MFG_RTNG_ITM-rtngGrpCd
      | 0015     | typ015    | nde015     | cd014     | oper015 | 1.000 |
      #MFG_RTNG_ITM_NDE-rtngNdeNum = MFG_RTNG_ITM-rtngItmNum
      | 0016     | typ016    | nde015     | cd016     | oper016 | 1.000 |
    And I wait "/edm/mfg_rtng_itm" Async Queue complete

    Given I import "/edm/mfg_rtng_hdr" by keyFields "srcSysCd,rtngTypCd,rtngGrpCd,rtngGrpCntrNbr,rtngVrsnCntrNbr"
      | srcSysCd | rtngTypCd | rtngGrpCd | rtngGrpCntrNb | toLtSzQty |
      | 0001     | typ001    | cd001     | nbr001        | toLtSzQty |
      | 0002     | typ002    | cd002     | nbr002        | toLtSzQty |
      | 0003     | typ003    | cd003     | nbr003        | toLtSzQty |
      | 0004     | typ004    | cd004     | nbr004        | toLtSzQty |
      | 0005     | typ005    | cd005     | nbr005        | toLtSzQty |
      | 0006     | typ006    | cd006     | nbr006        | toLtSzQty |
      | 0007     | typ007    | cd007     | nbr007        | toLtSzQty |
      | 0007     | typ008    | cd008     | nbr008        | toLtSzQty |
      | 0009     | typ009    | cd009     | nbr009        | toLtSzQty |
      | 0010     | typ010    | cd010     | nbr010        | toLtSzQty |
      | 0011     | typ011    | cd010     | nbr011        | toLtSzQty |
      | 0012     | typ012    | cd012     | nbr012        | toLtSzQty |
      | 0013     | typ013    | cd013     | nbr013        | toLtSzQty |
      | 0014     | typ013    | cd014     | nbr014        | toLtSzQty |
      | 0015     | typ015    | cd014     | nbr015        | toLtSzQty |
      | 0016     | typ016    | cd016     | nbr016        | toLtSzQty |
    And I wait "/edm/mfg_rtng_hdr" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmBomElement.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMBOMElement.tsv"

    Then I check  file data for filename "/omp/gdm_bom_element" by keyFields "bomElementId"
      | bomElementId | active | activeFCTERP | activeOPRERP | activeSOPERP | batchId | bomId | bomType | bomUsage | comment | endEff | erpFeedbackQuantity | locationId | offset | offsetCalendarId | offsetPercentage | offsetPercType | planLevelId | productId | quantity | startEff |


    And I delete the test data

    And I will remove all data with region "/omp/gdm_bom_element"

    And I will remove all data with region "/plan/edm_failed_data"

