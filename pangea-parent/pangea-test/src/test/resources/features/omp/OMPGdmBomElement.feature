@pangea_test @AEAZ-4250
Feature: OMPGdmBomElement AEAZ-4250

  Background:delete all test data

    Then I delete the test data

    And I will remove all data with region "/edm/matl_bom"

    And I will remove all data with region "/edm/matl_prod_versn"

    And I will remove all data with region "/edm/material_plant_v1"

    And I will remove all data with region "/edm/bom_item"

    And I will remove all data with region "/plan/cns_plan_parameter"

    And I will remove all data with region "/edm/matl_mfg_rtng"

    And I will remove all data with region "/edm/mfg_rtng_itm_nde"

    And I will remove all data with region "/omp/gdm_bom_element"

    And I will remove all data with region "/edm/bom_hdr"

    And I will remove all data with region "/edm/mfg_rtng_itm"


  @Scenario1
  Scenario: check rule  J1 & T1


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
      | localMaterialNumber | localPlant | sourceSystem | localGoodsReceiptProcessingTimeInDays |
      | 000000000000203700  | M001       | 0001         | 1                                     |
      | 000000000000203701  | M002       | 0002         | 2                                     |
      | 000000000000203702  | M003       | 0003         | 1                                     |
      | 000000000000203703  | M004       | 0004         | 1                                     |
      | 000000000000203704  | M005       | 0005         | 1                                     |
      | 000000000000203705  | M006       | 0006         | 1                                     |
      | 000000000000203706  | M007       | 0007         | 1                                     |
      | 000000000000203707  | M008       | 0008         | 1                                     |
      | 000000000000203708  | M009       | 0009         | 1                                     |
      | 000000000000203709  | M010       | 0010         | 1                                     |
      | 000000000000203710  | M011       | 0011         | 1                                     |
      | 000000000000203711  | M012       | 0012         | 1                                     |
      | 000000000000203712  | M013       | 0013         | 1                                     |
      | 000000000000203713  | M014       | 0014         | 1                                     |
      | 000000000000203714  | M015       | 0015         | 1                                     |
      | 000000000000203715  | M016       | 0016         | 1                                     |
      | 000000000000203716  | M017       | 0017         | 1                                     |
      | 000000000000203717  | M018       | 0018         | 1                                     |
      | 000000000000203718  | M019       | 0019         | 1                                     |
    And I wait "/edm/material_plant_v1" Async Queue complete

    Given I import "/edm/bom_item" by keyFields "srcSysCd,bomCatCd,bomNum"
      | bomNum | srcSysCd | bomCatCd | cmpntNum | bomItmNum | bomItmVldFromDt | dstrbtnKeyCd | fxQtyInd | bomItmVldToDt | leadTimeOffst | cmpntQty | cmpntScrap_Pct | cmpntUomCd |
      | B001   | 0001     | M        | 001      | 001       | 20180515        |              | X        | 20180516      | 0             | 100.000  | 1.00           | EH         |
      | B002   | 0002     | M        | 002      | 002       | 20180516        | GLEI         |          | 20180517      | 0             | 200.000  | 1.00           | EH         |
      | B003   | 0003     | M        | 003      | 003       | 20180517        |              | X        | 20180518      | 0             | 300.000  | 1.00           | EH         |
       #BOM_HDR-bomCatCd  = BOM_ITEM-bomCatCd
      | B004   | 0004     | N        | 004      | 004       | 20180518        | GLEI         |          | 20180519      | 0             | 400.000  | 1.00           | EH         |
    And I wait "/edm/bom_item" Async Queue complete

    Given I import "/edm/bom_hdr" by keyFields "srcSysCd,bomCatCd,bomNum,altBomNum"
      | srcSysCd | altBomNum | bomNum | bomCatCd | bomVldFromDt | bomBaseQty | bomUomCd |
      | 0001     | A001      | B001   | M        | 20180515     | 0001       | 0001     |
      | 0002     | A002      | B002   | M        | 20180516     | 0002       | 0002     |
      | 0003     | A003      | B003   | M        | 20180517     | 0003       | 0003     |
      | 0004     | A004      | B004   | M        | 20180518     | 0003       | 0003     |
      | 0005     | A005      | B005   | M        | 20180519     | 0003       | 0003     |
      | 0006     | A006      | B006   | M        | 20180520     | 0003       | 0003     |
      | 0007     | A007      | B007   | M        | 20180521     | 0003       | 0003     |
      | 0008     | A008      | B008   | M        | 20180522     | 0003       | 0003     |
      | 0009     | A009      | B009   | M        | 20180523     | 0003       | 0003     |
      | 0010     | A010      | B010   | M        | 20180524     | 0003       | 0003     |
      | 0011     | A011      | B011   | M        | 20180525     | 0003       | 0003     |
      | 0012     | A012      | B012   | M        | 20180526     | 0003       | 0003     |
      | 0013     | A013      | B013   | M        | 20180527     | 0003       | 0003     |
      | 0014     | A014      | B014   | M        | 20180528     | 0003       | 0003     |
      | 0015     | A015      | B015   | M        | 20180529     | 0003       | 0003     |
      | 0016     | A016      | B016   | M        | 20180530     | 0003       | 0003     |
    And I wait "/edm/bom_hdr" Async Queue complete

    Given I import "/plan/cns_plan_parameter" by keyFields "sourceSystem,dataObject,attribute"
      | sourceSystem | attribute | dataObject  | parameterValue |
      | 0001         | 0001      | SEND_TO_OMP | para01         |
#      cns_plan_parameter-dataObject = 'SEND_TO_OMP'
      | 0002         | 0002      | SEND_TO_OMP | para02         |
    And I wait "/plan/cns_plan_parameter" Async Queue complete

    Given I import "/edm/matl_prod_versn" by keyFields "srcSysCd,matlNum,plntCd,prdntVrsnNum"
      | srcSysCd | plntCd | altBomNum | matlNum            | rtngGrpCntrNum | rtngGrpCd | prdntVrsnNum | dstrbtnKeyCd | valFromDt | valToDt  |
      | 0001     | M001   | A001      | 000000000000203700 | cntr001        | cd001     | vrsn001      | key001       | 20180516  | 20180517 |
      | 0002     | M002   | A002      | 000000000000203701 | cntr002        | cd002     | vrsn002      | key002       | 20180514  | 20180515 |
      #cns_plan_parameter-sourceSystem = MATL_BOM-sourceSystem
      | 0003     | M003   | A003      | 000000000000203702 | cntr003        | cd003     | vrsn003      | key003       | 20180517  | 20180518 |
      #MATL_BOM-srcSysCd= MATL_PROD_VERSN-srcSysCd
      | 0005     | M004   | A004      | 000000000000203703 | cntr004        | cd004     | vrsn004      | key004       | 20180518  | 20180519 |
      # MATL_BOM-matlNum   = MATL_PROD_VERSN-matlNum
      | 0005     | M005   | A005      | 000000000000203704 | cntr005        | cd005     | vrsn005      | key005       | 20180519  | 20180520 |
      #  MATL_BOM-plntCd= MATL_PROD_VERSN-plntCd
      | 0006     | M005   | A006      | 000000000000203705 | cntr006        | cd006     | vrsn006      | key006       | 20180520  | 20180521 |
       #MATL_BOM-altBomNum   = MATL_PROD_VERSN-altBomNum
      | 0007     | M007   | A006      | 000000000000203706 | cntr007        | cd007     | vrsn007      | key007       | 20180521  | 20180522 |
      #  MATL_PROD_VERSN-srcSysCd= MATL_MFG_RTNG-srcSysCd
      | 0008     | M008   | A008      | 000000000000203707 | cntr008        | cd008     | vrsn008      | key008       | 20180522  | 20180523 |
      # MATL_PROD_VERSN-matlNum  = MATL_MFG_RTNG-matlNum
      | 0009     | M009   | A009      | 000000000000203708 | cntr009        | cd009     | vrsn009      | key009       | 20180523  | 20180524 |
      #MATL_PROD_VERSN-plntCd  = MATL_MFG_RTNG-plntCd
      | 0010     | M010   | A010      | 000000000000203709 | cntr010        | cd010     | vrsn010      | key010       | 20180524  | 20180525 |
      #MATL_PROD_VERSN-rtngGrpCd   = MATL_MFG_RTNG-rtngGrpCd
      | 0011     | M011   | A011      | 000000000000203710 | cntr011        | cd011     | vrsn011      | key011       | 20180525  | 20180526 |
      #MATL_PROD_VERSN-rtngGrpcntrNum    = MATL_MFG_RTNG-rtngGrpcntrNum
      | 0012     | M012   | A012      | 000000000000203711 | cntr012        | cd012     | vrsn012      | key012       | 20180526  | 20180527 |
      | 0013     | M013   | A013      | 000000000000203712 | cntr013        | cd013     | vrsn013      | key013       | 20180527  | 20180528 |
      | 0014     | M014   | A014      | 000000000000203713 | cntr014        | cd014     | vrsn014      | key014       | 20180528  | 20180529 |
      | 0015     | M015   | A015      | 000000000000203714 | cntr015        | cd015     | vrsn015      | key015       | 20180529  | 20180530 |
      | 0016     | M016   | A016      | 000000000000203715 | cntr016        | cd016     | vrsn016      | key016       | 20180530  | 20180531 |
    And I wait "/edm/matl_prod_versn" Async Queue complete

    Given I import "/edm/matl_mfg_rtng" by keyFields "srcSysCd,matlNum,plntCd,rtngTypCd,rntgGrpCntrNbr"
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


    Given I import "/edm/mfg_rtng_itm_nde" by keyFields "srcSysCd,rtngTypCd,rtngGrpCd,rtngGrpCntrNbr,rtngNdeNum"
      | srcSysCd | rtngTypCd | rtngGrpCntrNbr | rtngGrpCd | rtngNdeNum |
      | 0001     | typ001    | nbr001         | cd001     | nde001     |
      | 0002     | typ002    | nbr002         | cd002     | nde002     |
      | 0003     | typ003    | nbr003         | cd003     | nde003     |
      | 0004     | typ004    | nbr004         | cd004     | nde004     |
      | 0005     | typ005    | nbr005         | cd005     | nde005     |
      | 0006     | typ006    | nbr006         | cd006     | nde006     |
      | 0007     | typ007    | nbr007         | cd007     | nde007     |
      | 0007     | typ008    | nbr008         | cd008     | nde008     |
      | 0009     | typ009    | nbr009         | cd009     | nde009     |
      | 0010     | typ010    | nbr010         | cd010     | nde010     |
      | 0011     | typ011    | nbr011         | cd010     | nde011     |
      | 0012     | typ012    | nbr011         | cd012     | nde012     |
      #MFG_RTNG_ITM_NDE-srcSysCd  =  MFG_RTNG_ITM-srcSysCd
      | 0013     | typ013    | nbr013         | cd013     | nde013     |
      #MFG_RTNG_ITM_NDE-rtngTypCd     = MFG_RTNG_ITM-rtngTypCd
      | 0014     | typ014    | nbr014         | cd014     | nde014     |
      #MFG_RTNG_ITM_NDE-rtngGrpCd  = MFG_RTNG_ITM-rtngGrpCd
      | 0015     | typ015    | nbr015         | cd015     | nde015     |
      #MFG_RTNG_ITM_NDE-rtngNdeNum = MFG_RTNG_ITM-rtngItmNum
      | 0016     | typ016    | nbr016         | cd016     | nde016     |

    And I wait "/edm/mfg_rtng_itm_nde" Async Queue complete

    Given I import "/edm/mfg_rtng_itm" by keyFields "srcSysCd,rtngTypCd,rtngGrpCd,rtngItmNum"
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

    When I submit task with xml file "xml/omp/OMPGdmBomElement.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMBOMElement.tsv"

#    Then I check region data "/omp/gdm_bom_element" by keyFields "bomElementId"
    Then I check file data for filename "GDMBOMElement.tsv" by keyFields "bomElementId"
      | bomElementId                                                                         | active | activeFCTERP | activeOPRERP | activeSOPERP | batchId | bomId                                                         | bomType    | bomUsage     | comment | endEff              | erpFeedbackQuantity | locationId | offset | offsetCalendarId | offsetPercentage | offsetPercType | planLevelId        | productId  | quantity | startEff            |
      | vrsn002/000000000000203701/M002/B002/A002/0002/oper002/002/002/20180516              | YES    | YES          | YES          | NO           |         | vrsn002/para02_000000000000203701/M002/B002/A002/0002/oper002 | continu    | proportional |         | 2018/05/15 00:00:00 |                     | 0002_M002  | 0      |                  |                  |                | *                  | para02_002 | 102.000  | 2018/05/16 00:00:00 |
      | vrsn002/000000000000203701/M002/B002/A002/0002/oper002/20180516                      | YES    | YES          | YES          | NO           |         | vrsn002/para02_000000000000203701/M002/B002/A002/0002/oper002 | continu    | proportional |         | 2018/05/17 00:00:00 |                     | 0002_M002  | 172800 |                  |                  |                | *                  | para02_002 |          | 2018/05/14 00:00:00 |
      | vrsn001/000000000000203700/M001/B001/A001/0001/oper001/001/001/20180515              | YES    | YES          | YES          | NO           |         | vrsn001/para01_000000000000203700/M001/B001/A001/0001/oper001 | batchstart | fixed        |         | 2018/05/16 00:00:00 |                     | 0001_M001  | 0      |                  |                  |                | DetailedScheduling | para01_001 | 101.000  | 2018/05/16 00:00:00 |
      | vrsn001/000000000000203700/M001/B001/A001/0001/oper001/20180515                      | YES    | YES          | YES          | NO           |         | vrsn001/para01_000000000000203700/M001/B001/A001/0001/oper001 | batchend   | proportional |         | 2018/05/17 00:00:00 |                     | 0001_M001  | 86400  |                  |                  |                | *                  | para01_001 |          | 2018/05/15 00:00:00 |
      | vrsn001/000000000000203700/M001/B001/A001/0001/oper001/001/001/20180515/proportional | YES    | YES          | YES          | NO           |         | vrsn001/para01_000000000000203700/M001/B001/A001/0001/oper001 | batchstart | fixed        |         | 2018/05/16 00:00:00 |                     | 0001_M001  | 0      |                  |                  |                | VolumePlanning     | para01_001 | 100.000  | 2018/05/16 00:00:00 |

    And I delete the test data

    And I will remove all data with region "/omp/gdm_bom_element"

    And I will remove all data with region "/plan/edm_failed_data"

#    And I will remove the test file on sink application "GDMBOMElement.tsv"




  @Scenario2
  Scenario: check rule  T2 default "YES" & T3 default "NO" & T4 Leave Blank
    Given I import "/edm/matl_bom" by keyFields "srcSysCd,matlNum,plntCd,bomUsgCd,bomNum,altBomNum"
      | srcSysCd | matlNum            | plntCd | altBomNum | sourceSystem | bomNum | bomUsgCd | attribute |
      | 0001     | 000000000000203700 | M001   | A001      | CONS_LATAM   | B001   | 0001     | att01     |
      | 0002     | 000000000000203701 | M002   | A002      | CONS_LATAM   | B002   | 0002     | att02     |
    And I wait "/edm/matl_bom" Async Queue complete

    Given I import "/edm/material_plant_v1" by keyFields "localMaterialNumber,localPlant"
      | localMaterialNumber | localPlant | sourceSystem | localGoodsReceiptProcessingTimeInDays |
      | 000000000000203700  | M001       | 0001         | 1                                     |
      | 000000000000203701  | M002       | 0002         | 2                                     |
    And I wait "/edm/material_plant_v1" Async Queue complete

    Given I import "/edm/bom_item" by keyFields "srcSysCd,bomCatCd,bomNum"
      | bomNum | srcSysCd | bomCatCd | cmpntNum | bomItmNum | bomItmVldFromDt | dstrbtnKeyCd | fxQtyInd | bomItmVldToDt | leadTimeOffst | cmpntQty | cmpntScrap_Pct | cmpntUomCd |
      | B001   | 0001     | M        | 001      | 001       | 20180515        |              | X        | 20180516      | 0             | 100.000  | 1.00           | TH         |
      | B002   | 0002     | M        | 002      | 002       | 20180516        | GLEI         |          | 20180517      | 0             | 200.000  | 1.00           | TH         |
    And I wait "/edm/bom_item" Async Queue complete

    Given I import "/edm/bom_hdr" by keyFields "srcSysCd,bomCatCd,bomNum,altBomNum"
      | srcSysCd | altBomNum | bomNum | bomCatCd | bomVldFromDt | bomBaseQty | bomUomCd |
      | 0001     | A001      | B001   | M        | 20180515     | 0001       | 0001     |
      | 0002     | A002      | B002   | M        | 20180516     | 0002       | 0002     |
    And I wait "/edm/bom_hdr" Async Queue complete

    Given I import "/plan/cns_plan_parameter" by keyFields "sourceSystem,dataObject,attribute"
      | sourceSystem | attribute | dataObject  | parameterValue |
      | 0001         | 0001      | SEND_TO_OMP | para01         |
      | 0002         | 0002      | SEND_TO_OMP | para02         |
    And I wait "/plan/cns_plan_parameter" Async Queue complete

    Given I import "/edm/matl_prod_versn" by keyFields "srcSysCd,matlNum,plntCd,prdntVrsnNum"
      | srcSysCd | plntCd | altBomNum | matlNum            | rtngGrpCntrNum | rtngGrpCd | prdntVrsnNum | dstrbtnKeyCd | valFromDt | valToDt  |
      | 0001     | M001   | A001      | 000000000000203700 | cntr001        | cd001     | vrsn001      | key001       | 20180516  | 20180517 |
      | 0002     | M002   | A002      | 000000000000203701 | cntr002        | cd002     | vrsn002      | key002       | 20180514  | 20180515 |
    And I wait "/edm/matl_prod_versn" Async Queue complete

    Given I import "/edm/matl_mfg_rtng" by keyFields "srcSysCd,matlNum,plntCd,rtngTypCd,rntgGrpCntrNbr"
      | srcSysCd | rntgGrpCd | rtngGrpcntrNum | plntCd | matlNum            | rntgGrpCntrNbr | rtngTypCd |
      | 0001     | cd001     | cntr001        | M001   | 000000000000203700 | nbr001         | typ001    |
      | 0002     | cd002     | cntr002        | M002   | 000000000000203701 | nbr002         | typ002    |
    And I wait "/edm/matl_mfg_rtng" Async Queue complete

    Given I import "/edm/mfg_rtng_itm_nde" by keyFields "srcSysCd,rtngTypCd,rtngGrpCd,rtngGrpCntrNbr,rtngNdeNum"
      | srcSysCd | rtngTypCd | rtngGrpCntrNbr | rtngGrpCd | rtngNdeNum |
      | 0001     | typ001    | nbr001         | cd001     | nde001     |
      | 0002     | typ002    | nbr002         | cd002     | nde002     |

    And I wait "/edm/mfg_rtng_itm_nde" Async Queue complete

    Given I import "/edm/mfg_rtng_itm" by keyFields "srcSysCd,rtngTypCd,rtngGrpCd,rtngItmNum"
      | srcSysCd | rtngTypCd | rtngItmNum | rtngGrpCd | operNum | bsQty |
      | 0001     | typ001    | nde001     | cd001     | oper001 | 1.000 |
      | 0002     | typ002    | nde002     | cd002     | oper002 | 1.000 |
    And I wait "/edm/mfg_rtng_itm" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmBomElement.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMBOMElement.tsv"

    Then I check file data for filename "GDMBOMElement.tsv" by keyFields "bomElementId"
#    Then I check region data "/omp/gdm_bom_element" by keyFields "bomElementId"
      | bomElementId                                                                         | active | activeFCTERP | activeOPRERP | activeSOPERP | batchId | bomId                                                         | bomType    | bomUsage     | comment | endEff              | erpFeedbackQuantity | locationId | offset | offsetCalendarId | offsetPercentage | offsetPercType | planLevelId        | productId  | quantity | startEff            |
      | vrsn001/000000000000203700/M001/B001/A001/0001/oper001/001/001/20180515              | YES    | YES          | YES          | NO           |         | vrsn001/para01_000000000000203700/M001/B001/A001/0001/oper001 | batchstart | fixed        |         | 2018/05/16 00:00:00 |                     | 0001_M001  | 0      |                  |                  |                | DetailedScheduling | para01_001 | 101.000  | 2018/05/16 00:00:00 |
      | vrsn001/000000000000203700/M001/B001/A001/0001/oper001/20180515                      | YES    | YES          | YES          | NO           |         | vrsn001/para01_000000000000203700/M001/B001/A001/0001/oper001 | batchend   | proportional |         | 2018/05/17 00:00:00 |                     | 0001_M001  | 86400  |                  |                  |                | *                  | para01_001 |          | 2018/05/15 00:00:00 |
      | vrsn001/000000000000203700/M001/B001/A001/0001/oper001/001/001/20180515/proportional | YES    | YES          | YES          | NO           |         | vrsn001/para01_000000000000203700/M001/B001/A001/0001/oper001 | batchstart | fixed        |         | 2018/05/16 00:00:00 |                     | 0001_M001  | 0      |                  |                  |                | VolumePlanning     | para01_001 | 100.000  | 2018/05/16 00:00:00 |
      | vrsn002/000000000000203701/M002/B002/A002/0002/oper002/002/002/20180516              | YES    | YES          | YES          | NO           |         | vrsn002/para02_000000000000203701/M002/B002/A002/0002/oper002 | continu    | proportional |         | 2018/05/15 00:00:00 |                     | 0002_M002  | 0      |                  |                  |                | *                  | para02_002 | 102.000  | 2018/05/16 00:00:00 |
      | vrsn002/000000000000203701/M002/B002/A002/0002/oper002/20180516                      | YES    | YES          | YES          | NO           |         | vrsn002/para02_000000000000203701/M002/B002/A002/0002/oper002 | continu    | proportional |         | 2018/05/17 00:00:00 |                     | 0002_M002  | 172800 |                  |                  |                | *                  | para02_002 |          | 2018/05/14 00:00:00 |

    And I delete the test data

    And I will remove all data with region "/omp/gdm_bom_element"

    And I will remove all data with region "/plan/edm_failed_data"

#    And I will remove the test file on sink application "GDMBOMElement.tsv"



  @Scenario3
  Scenario: check rule  T5  & T6  & T7 & T13
    Given I import "/edm/matl_bom" by keyFields "srcSysCd,matlNum,plntCd,bomUsgCd,bomNum,altBomNum"
      | srcSysCd | matlNum            | plntCd | altBomNum | sourceSystem | bomNum | bomUsgCd | attribute |
      | 0001     | 000000000000203700 | M001   | A001      | CONS_LATAM   | B001   | 0001     | att01     |
      | 0002     | 000000000000203701 | M002   | A002      | CONS_LATAM   | B002   | 0002     | att02     |
    And I wait "/edm/matl_bom" Async Queue complete

    Given I import "/edm/material_plant_v1" by keyFields "localMaterialNumber,localPlant"
      | localMaterialNumber | localPlant | sourceSystem | localGoodsReceiptProcessingTimeInDays |
      | 000000000000203700  | M001       | 0001         | 1                                     |
      | 000000000000203701  | M002       | 0002         | 2                                     |
    And I wait "/edm/material_plant_v1" Async Queue complete

    Given I import "/edm/bom_item" by keyFields "srcSysCd,bomCatCd,bomNum"
      | bomNum | srcSysCd | bomCatCd | cmpntNum | bomItmNum | bomItmVldFromDt | dstrbtnKeyCd | fxQtyInd | bomItmVldToDt | leadTimeOffst | cmpntQty | cmpntScrap_Pct | cmpntUomCd |
      # If BOM_ITEM-dstrbtnKeyCd=blank, use "batchstart"  AND  If BOM_ITEM-fxQtyInd = X, use "fixed"
      | B001   | 0001     | M        | 001      | 001       | 20180515        |              | X        | 20180516      | 0             | 100.000  | 1.00           | HN         |
      # If BOM_ITEM-dstrbtnKeyCd=GLEI, use "continu"   AND     If BOM_ITEM-fxQtyInd = blank value , use "proportional"
      | B002   | 0002     | M        | 002      | 002       | 20180516        | GLEI         |          | 20180517      | 0             | 200.000  | 1.00           | HN         |
    And I wait "/edm/bom_item" Async Queue complete

    Given I import "/edm/bom_hdr" by keyFields "srcSysCd,bomCatCd,bomNum,altBomNum"
      | srcSysCd | altBomNum | bomNum | bomCatCd | bomVldFromDt | bomBaseQty | bomUomCd |
      | 0001     | A001      | B001   | M        | 20180515     | 0001       | 0001     |
      | 0002     | A002      | B002   | M        | 20180516     | 0002       | 0002     |
    And I wait "/edm/bom_hdr" Async Queue complete

    Given I import "/plan/cns_plan_parameter" by keyFields "sourceSystem,dataObject,attribute"
      | sourceSystem | attribute | dataObject  | parameterValue |
      | 0001         | 0001      | SEND_TO_OMP | para01         |
      | 0002         | 0002      | SEND_TO_OMP | para02         |
    And I wait "/plan/cns_plan_parameter" Async Queue complete

    Given I import "/edm/matl_prod_versn" by keyFields "srcSysCd,matlNum,plntCd,prdntVrsnNum"
      | srcSysCd | plntCd | altBomNum | matlNum            | rtngGrpCntrNum | rtngGrpCd | prdntVrsnNum | dstrbtnKeyCd | valFromDt | valToDt  |
      | 0001     | M001   | A001      | 000000000000203700 | cntr001        | cd001     | vrsn001      | key001       | 20180516  | 20180517 |
      | 0002     | M002   | A002      | 000000000000203701 | cntr002        | cd002     | vrsn002      | key002       | 20180514  | 20180515 |
    And I wait "/edm/matl_prod_versn" Async Queue complete

    Given I import "/edm/matl_mfg_rtng" by keyFields "srcSysCd,matlNum,plntCd,rtngTypCd,rntgGrpCntrNbr"
      | srcSysCd | rntgGrpCd | rtngGrpcntrNum | plntCd | matlNum            | rntgGrpCntrNbr | rtngTypCd |
      | 0001     | cd001     | cntr001        | M001   | 000000000000203700 | nbr001         | typ001    |
      | 0002     | cd002     | cntr002        | M002   | 000000000000203701 | nbr002         | typ002    |
    And I wait "/edm/matl_mfg_rtng" Async Queue complete

    Given I import "/edm/mfg_rtng_itm_nde" by keyFields "srcSysCd,rtngTypCd,rtngGrpCd,rtngGrpCntrNbr,rtngNdeNum"
      | srcSysCd | rtngTypCd | rtngGrpCntrNbr | rtngGrpCd | rtngNdeNum |
      | 0001     | typ001    | nbr001         | cd001     | nde001     |
      | 0002     | typ002    | nbr002         | cd002     | nde002     |

    And I wait "/edm/mfg_rtng_itm_nde" Async Queue complete

    Given I import "/edm/mfg_rtng_itm" by keyFields "srcSysCd,rtngTypCd,rtngGrpCd,rtngItmNum"
      | srcSysCd | rtngTypCd | rtngItmNum | rtngGrpCd | operNum | bsQty |
      | 0001     | typ001    | nde001     | cd001     | oper001 | 1.000 |
      | 0002     | typ002    | nde002     | cd002     | oper002 | 1.000 |
    And I wait "/edm/mfg_rtng_itm" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmBomElement.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMBOMElement.tsv"

#    Then I check region data "/omp/gdm_bom_element" by keyFields "bomElementId"
    Then I check file data for filename "GDMBOMElement.tsv" by keyFields "bomElementId"
      | bomElementId                                                                         | active | activeFCTERP | activeOPRERP | activeSOPERP | batchId | bomId                                                         | bomType    | bomUsage     | comment | endEff              | erpFeedbackQuantity | locationId | offset | offsetCalendarId | offsetPercentage | offsetPercType | planLevelId        | productId  | quantity | startEff            |
      | vrsn001/000000000000203700/M001/B001/A001/0001/oper001/001/001/20180515              | YES    | YES          | YES          | NO           |         | vrsn001/para01_000000000000203700/M001/B001/A001/0001/oper001 | batchstart | fixed        |         | 2018/05/16 00:00:00 |                     | 0001_M001  | 0      |                  |                  |                | DetailedScheduling | para01_001 | 101.000  | 2018/05/16 00:00:00 |
      | vrsn001/000000000000203700/M001/B001/A001/0001/oper001/20180515                      | YES    | YES          | YES          | NO           |         | vrsn001/para01_000000000000203700/M001/B001/A001/0001/oper001 | batchend   | proportional |         | 2018/05/17 00:00:00 |                     | 0001_M001  | 86400  |                  |                  |                | *                  | para01_001 |          | 2018/05/15 00:00:00 |
      | vrsn001/000000000000203700/M001/B001/A001/0001/oper001/001/001/20180515/proportional | YES    | YES          | YES          | NO           |         | vrsn001/para01_000000000000203700/M001/B001/A001/0001/oper001 | batchstart | fixed        |         | 2018/05/16 00:00:00 |                     | 0001_M001  | 0      |                  |                  |                | VolumePlanning     | para01_001 | 100.000  | 2018/05/16 00:00:00 |
      | vrsn002/000000000000203701/M002/B002/A002/0002/oper002/002/002/20180516              | YES    | YES          | YES          | NO           |         | vrsn002/para02_000000000000203701/M002/B002/A002/0002/oper002 | continu    | proportional |         | 2018/05/15 00:00:00 |                     | 0002_M002  | 0      |                  |                  |                | *                  | para02_002 | 102.000  | 2018/05/16 00:00:00 |
      | vrsn002/000000000000203701/M002/B002/A002/0002/oper002/20180516                      | YES    | YES          | YES          | NO           |         | vrsn002/para02_000000000000203701/M002/B002/A002/0002/oper002 | continu    | proportional |         | 2018/05/17 00:00:00 |                     | 0002_M002  | 172800 |                  |                  |                | *                  | para02_002 |          | 2018/05/14 00:00:00 |

    And I delete the test data

    And I will remove all data with region "/omp/gdm_bom_element"

    And I will remove all data with region "/plan/edm_failed_data"


  @Scenario4
  Scenario: check rule  T8 & T14
    Given I import "/edm/matl_bom" by keyFields "srcSysCd,matlNum,plntCd,bomUsgCd,bomNum,altBomNum"
      | srcSysCd | matlNum            | plntCd | altBomNum | sourceSystem | bomNum | bomUsgCd | attribute |
      | 0001     | 000000000000203700 | M001   | A001      | CONS_LATAM   | B001   | 0001     | att01     |
      | 0002     | 000000000000203701 | M002   | A002      | CONS_LATAM   | B002   | 0002     | att02     |
    And I wait "/edm/matl_bom" Async Queue complete

    Given I import "/edm/material_plant_v1" by keyFields "localMaterialNumber,localPlant"
      | localMaterialNumber | localPlant | sourceSystem | localGoodsReceiptProcessingTimeInDays |
      | 000000000000203700  | M001       | 0001         | 1                                     |
      | 000000000000203701  | M002       | 0002         | 2                                     |
    And I wait "/edm/material_plant_v1" Async Queue complete

    Given I import "/edm/bom_item" by keyFields "srcSysCd,bomCatCd,bomNum"
      | bomNum | srcSysCd | bomCatCd | cmpntNum | bomItmNum | bomItmVldFromDt | dstrbtnKeyCd | fxQtyInd | bomItmVldToDt | leadTimeOffst | cmpntQty | cmpntScrap_Pct | cmpntUomCd |
       # use lowest among BOM_ITEM-bomItmVldToDt and MATL_PROD_VERSN-valToDt
      | B001   | 0001     | M        | 001      | 001       | 20180515        |              | X        | 20180516      | 0             | 100.000  | 1.00           | YH         |
      #  Greatest of BOM_ITEM-bomItmVldFromDt and MATL_PROD_VERSN-valFromDt
      | B002   | 0002     | M        | 002      | 002       | 20180516        | GLEI         |          | 20180517      | 0             | 200.000  | 1.00           | YH         |
    And I wait "/edm/bom_item" Async Queue complete

    Given I import "/edm/bom_hdr" by keyFields "srcSysCd,bomCatCd,bomNum,altBomNum"
      | srcSysCd | altBomNum | bomNum | bomCatCd | bomVldFromDt | bomBaseQty | bomUomCd |
      | 0001     | A001      | B001   | M        | 20180515     | 0001       | 0001     |
      | 0002     | A002      | B002   | M        | 20180516     | 0002       | 0002     |
    And I wait "/edm/bom_hdr" Async Queue complete

    Given I import "/plan/cns_plan_parameter" by keyFields "sourceSystem,dataObject,attribute"
      | sourceSystem | attribute | dataObject  | parameterValue |
      | 0001         | 0001      | SEND_TO_OMP | para01         |
      | 0002         | 0002      | SEND_TO_OMP | para02         |
    And I wait "/plan/cns_plan_parameter" Async Queue complete

    Given I import "/edm/matl_prod_versn" by keyFields "srcSysCd,matlNum,plntCd,prdntVrsnNum"
      | srcSysCd | plntCd | altBomNum | matlNum            | rtngGrpCntrNum | rtngGrpCd | prdntVrsnNum | dstrbtnKeyCd | valFromDt | valToDt  |
        # use lowest among BOM_ITEM-bomItmVldToDt and MATL_PROD_VERSN-valToDt
      | 0001     | M001   | A001      | 000000000000203700 | cntr001        | cd001     | vrsn001      | key001       | 20180516  | 20180517 |
      #  Greatest of BOM_ITEM-bomItmVldFromDt and MATL_PROD_VERSN-valFromDt
      | 0002     | M002   | A002      | 000000000000203701 | cntr002        | cd002     | vrsn002      | key002       | 20180514  | 20180515 |
    And I wait "/edm/matl_prod_versn" Async Queue complete

    Given I import "/edm/matl_mfg_rtng" by keyFields "srcSysCd,matlNum,plntCd,rtngTypCd,rntgGrpCntrNbr"
      | srcSysCd | rntgGrpCd | rtngGrpcntrNum | plntCd | matlNum            | rntgGrpCntrNbr | rtngTypCd |
      | 0001     | cd001     | cntr001        | M001   | 000000000000203700 | nbr001         | typ001    |
      | 0002     | cd002     | cntr002        | M002   | 000000000000203701 | nbr002         | typ002    |
    And I wait "/edm/matl_mfg_rtng" Async Queue complete

    Given I import "/edm/mfg_rtng_itm_nde" by keyFields "srcSysCd,rtngTypCd,rtngGrpCd,rtngGrpCntrNbr,rtngNdeNum"
      | srcSysCd | rtngTypCd | rtngGrpCntrNbr | rtngGrpCd | rtngNdeNum |
      | 0001     | typ001    | nbr001         | cd001     | nde001     |
      | 0002     | typ002    | nbr002         | cd002     | nde002     |

    And I wait "/edm/mfg_rtng_itm_nde" Async Queue complete

    Given I import "/edm/mfg_rtng_itm" by keyFields "srcSysCd,rtngTypCd,rtngGrpCd,rtngItmNum"
      | srcSysCd | rtngTypCd | rtngItmNum | rtngGrpCd | operNum | bsQty |
      | 0001     | typ001    | nde001     | cd001     | oper001 | 1.000 |
      | 0002     | typ002    | nde002     | cd002     | oper002 | 1.000 |
    And I wait "/edm/mfg_rtng_itm" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmBomElement.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMBOMElement.tsv"

#    Then I check region data "/omp/gdm_bom_element" by keyFields "bomElementId"
    Then I check file data for filename "GDMBOMElement.tsv" by keyFields "bomElementId"
      | bomElementId                                                                         | active | activeFCTERP | activeOPRERP | activeSOPERP | batchId | bomId                                                         | bomType    | bomUsage     | comment | endEff              | erpFeedbackQuantity | locationId | offset | offsetCalendarId | offsetPercentage | offsetPercType | planLevelId        | productId  | quantity | startEff            |
      | vrsn001/000000000000203700/M001/B001/A001/0001/oper001/001/001/20180515              | YES    | YES          | YES          | NO           |         | vrsn001/para01_000000000000203700/M001/B001/A001/0001/oper001 | batchstart | fixed        |         | 2018/05/16 00:00:00 |                     | 0001_M001  | 0      |                  |                  |                | DetailedScheduling | para01_001 | 101.000  | 2018/05/16 00:00:00 |
      | vrsn001/000000000000203700/M001/B001/A001/0001/oper001/20180515                      | YES    | YES          | YES          | NO           |         | vrsn001/para01_000000000000203700/M001/B001/A001/0001/oper001 | batchend   | proportional |         | 2018/05/17 00:00:00 |                     | 0001_M001  | 86400  |                  |                  |                | *                  | para01_001 |          | 2018/05/15 00:00:00 |
      | vrsn001/000000000000203700/M001/B001/A001/0001/oper001/001/001/20180515/proportional | YES    | YES          | YES          | NO           |         | vrsn001/para01_000000000000203700/M001/B001/A001/0001/oper001 | batchstart | fixed        |         | 2018/05/16 00:00:00 |                     | 0001_M001  | 0      |                  |                  |                | VolumePlanning     | para01_001 | 100.000  | 2018/05/16 00:00:00 |
      | vrsn002/000000000000203701/M002/B002/A002/0002/oper002/002/002/20180516              | YES    | YES          | YES          | NO           |         | vrsn002/para02_000000000000203701/M002/B002/A002/0002/oper002 | continu    | proportional |         | 2018/05/15 00:00:00 |                     | 0002_M002  | 0      |                  |                  |                | *                  | para02_002 | 102.000  | 2018/05/16 00:00:00 |
      | vrsn002/000000000000203701/M002/B002/A002/0002/oper002/20180516                      | YES    | YES          | YES          | NO           |         | vrsn002/para02_000000000000203701/M002/B002/A002/0002/oper002 | continu    | proportional |         | 2018/05/17 00:00:00 |                     | 0002_M002  | 172800 |                  |                  |                | *                  | para02_002 |          | 2018/05/14 00:00:00 |

    And I delete the test data

    And I will remove all data with region "/omp/gdm_bom_element"

    And I will remove all data with region "/plan/edm_failed_data"

#    And I will remove the test file on sink application "GDMBOMElement.tsv"


  @Scenario5
  Scenario: check rule  T12
#  SELECT cns_plan_parameter-parameterValue where cns_plan_parameter-sourceSystem =MATL_BOM-srcSysCd AND cns_plan_parameter-dataObject = 'SEND_TO_OMP'
#  concatenate cns_plan_parameter-parameterValue, '_'  and  BOM_ITEM-cmpntNum
    Given I import "/edm/matl_bom" by keyFields "srcSysCd,matlNum,plntCd,bomUsgCd,bomNum,altBomNum"
      | srcSysCd | matlNum            | plntCd | altBomNum | sourceSystem | bomNum | bomUsgCd | attribute |
      | 0001     | 000000000000203700 | M001   | A001      | CONS_LATAM   | B001   | 0001_T12 | att01     |
      | 0002     | 000000000000203701 | M002   | A002      | CONS_LATAM   | B002   | 0002_T12 | att02     |
      | 0003     | 000000000000203702 | M003   | A003      | CONS_LATAM   | B003   | 0003_T12 | att03     |
    And I wait "/edm/matl_bom" Async Queue complete

    Given I import "/edm/material_plant_v1" by keyFields "localMaterialNumber,localPlant"
      | localMaterialNumber | localPlant | sourceSystem | localGoodsReceiptProcessingTimeInDays |
      | 000000000000203700  | M001       | 0001         | 1                                     |
      | 000000000000203701  | M002       | 0002         | 2                                     |
      | 000000000000203702  | M003       | 0003         | 3                                     |
    And I wait "/edm/material_plant_v1" Async Queue complete

    Given I import "/edm/bom_item" by keyFields "srcSysCd,bomCatCd,bomNum"
      | bomNum | srcSysCd | bomCatCd | cmpntNum | bomItmNum | bomItmVldFromDt | dstrbtnKeyCd | fxQtyInd | bomItmVldToDt | leadTimeOffst | cmpntQty | cmpntScrap_Pct | cmpntUomCd |
      | B001   | 0001     | M        | 001      | 001       | 20180515        |              | X        | 20180516      | 0             | 100.000  | 1.00           | YU         |
      | B002   | 0002     | M        | 002      | 002       | 20180516        | GLEI         |          | 20180517      | 0             | 200.000  | 1.00           | YU         |
      | B003   | 0003     | M        | 003      | 003       | 20180517        | GLEI         |          | 20180518      | 0             | 200.000  | 1.00           | YU         |
    And I wait "/edm/bom_item" Async Queue complete

    Given I import "/edm/bom_hdr" by keyFields "srcSysCd,bomCatCd,bomNum,altBomNum"
      | srcSysCd | altBomNum | bomNum | bomCatCd | bomVldFromDt | bomBaseQty | bomUomCd |
      | 0001     | A001      | B001   | M        | 20180515     | 0001       | 0001     |
      | 0002     | A002      | B002   | M        | 20180516     | 0002       | 0002     |
      | 0003     | A003      | B003   | M        | 20180517     | 0002       | 0002     |
    And I wait "/edm/bom_hdr" Async Queue complete

    Given I import "/plan/cns_plan_parameter" by keyFields "sourceSystem,dataObject,attribute"
      | sourceSystem | attribute | dataObject  | parameterValue |
      | 0001         | 0001      | SEND_TO_OMP | para01         |
      | 0002         | 0002      | SEND_TO_OMP | para02         |
      | 0003         | 0003      | SEND_TO_OMP | para03         |
    And I wait "/plan/cns_plan_parameter" Async Queue complete

    Given I import "/edm/matl_prod_versn" by keyFields "srcSysCd,matlNum,plntCd,prdntVrsnNum"
      | srcSysCd | plntCd | altBomNum | matlNum            | rtngGrpCntrNum | rtngGrpCd | prdntVrsnNum | dstrbtnKeyCd | valFromDt | valToDt  |
      | 0001     | M001   | A001      | 000000000000203700 | cntr001        | cd001     | vrsn001      | key001       | 20180516  | 20180517 |
      | 0002     | M002   | A002      | 000000000000203701 | cntr002        | cd002     | vrsn002      | key002       | 20180514  | 20180515 |
      | 0003     | M003   | A003      | 000000000000203702 | cntr003        | cd003     | vrsn003      | key003       | 20180515  | 20180516 |
    And I wait "/edm/matl_prod_versn" Async Queue complete

    Given I import "/edm/matl_mfg_rtng" by keyFields "srcSysCd,matlNum,plntCd,rtngTypCd,rntgGrpCntrNbr"
      | srcSysCd | rntgGrpCd | rtngGrpcntrNum | plntCd | matlNum            | rntgGrpCntrNbr | rtngTypCd |
      | 0001     | cd001     | cntr001        | M001   | 000000000000203700 | nbr001         | typ001    |
      | 0002     | cd002     | cntr002        | M002   | 000000000000203701 | nbr002         | typ002    |
      | 0003     | cd003     | cntr003        | M003   | 000000000000203702 | nbr003         | typ003    |
    And I wait "/edm/matl_mfg_rtng" Async Queue complete

    Given I import "/edm/mfg_rtng_itm_nde" by keyFields "srcSysCd,rtngTypCd,rtngGrpCd,rtngGrpCntrNbr,rtngNdeNum"
      | srcSysCd | rtngTypCd | rtngGrpCntrNbr | rtngGrpCd | rtngNdeNum |
      | 0001     | typ001    | nbr001         | cd001     | nde001     |
      | 0002     | typ002    | nbr001         | cd002     | nde002     |
      | 0003     | typ003    | nbr001         | cd003     | nde003     |

    And I wait "/edm/mfg_rtng_itm_nde" Async Queue complete

    Given I import "/edm/mfg_rtng_itm" by keyFields "srcSysCd,rtngTypCd,rtngGrpCd,rtngItmNum"
      | srcSysCd | rtngTypCd | rtngItmNum | rtngGrpCd | operNum | bsQty |
      | 0001     | typ001    | nde001     | cd001     | oper001 | 1.000 |
      | 0002     | typ002    | nde002     | cd002     | oper002 | 1.000 |
      | 0003     | typ003    | nde003     | cd003     | oper003 | 1.000 |
    And I wait "/edm/mfg_rtng_itm" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmBomElement.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMBOMElement.tsv"

#    Then I check region data "/omp/gdm_bom_element" by keyFields "bomElementId"
    Then I check file data for filename "GDMBOMElement.tsv" by keyFields "bomElementId"
      | bomElementId                                                                             | active | activeFCTERP | activeOPRERP | activeSOPERP | batchId | bomId                                                             | bomType    | bomUsage     | comment | endEff              | erpFeedbackQuantity | locationId | offset | offsetCalendarId | offsetPercentage | offsetPercType | planLevelId        | productId  | quantity | startEff            |
      | vrsn001/000000000000203700/M001/B001/A001/0001_T12/oper001/001/001/20180515              | YES    | YES          | YES          | NO           |         | vrsn001/para01_000000000000203700/M001/B001/A001/0001_T12/oper001 | batchstart | fixed        |         | 2018/05/16 00:00:00 |                     | 0001_M001  | 0      |                  |                  |                | DetailedScheduling | para01_001 | 101.000  | 2018/05/16 00:00:00 |
      | vrsn001/000000000000203700/M001/B001/A001/0001_T12/oper001/20180515                      | YES    | YES          | YES          | NO           |         | vrsn001/para01_000000000000203700/M001/B001/A001/0001_T12/oper001 | batchend   | proportional |         | 2018/05/17 00:00:00 |                     | 0001_M001  | 86400  |                  |                  |                | *                  | para01_001 |          | 2018/05/15 00:00:00 |
      | vrsn001/000000000000203700/M001/B001/A001/0001_T12/oper001/001/001/20180515/proportional | YES    | YES          | YES          | NO           |         | vrsn001/para01_000000000000203700/M001/B001/A001/0001_T12/oper001 | batchstart | fixed        |         | 2018/05/16 00:00:00 |                     | 0001_M001  | 0      |                  |                  |                | VolumePlanning     | para01_001 | 100.000  | 2018/05/16 00:00:00 |

    And I delete the test data

    And I will remove all data with region "/omp/gdm_bom_element"

    And I will remove all data with region "/plan/edm_failed_data"

#    And I will remove the test file on sink application "GDMBOMElement.tsv"



  @Scenario6
  Scenario: check rule  T1 and  Satisfy all the rules
    Given I import "/edm/matl_bom" by keyFields "srcSysCd,matlNum,plntCd,bomUsgCd,bomNum,altBomNum"
      | srcSysCd | matlNum            | plntCd | altBomNum | sourceSystem | bomNum | bomUsgCd | attribute |
      | 0001     | 000000000000203700 | M001   | A001      | CONS_LATAM   | B001   | 0001_T11 | att01     |
      | 0002     | 000000000000203701 | M002   | A002      | CONS_LATAM   | B002   | 0002_T11 | att02     |
    And I wait "/edm/matl_bom" Async Queue complete

    Given I import "/edm/material_plant_v1" by keyFields "localMaterialNumber,localPlant"
      | localMaterialNumber | localPlant | sourceSystem | localGoodsReceiptProcessingTimeInDays |
      | 000000000000203700  | M001       | 0001         | 1                                     |
      | 000000000000203701  | M002       | 0002         | 2                                     |
    And I wait "/edm/material_plant_v1" Async Queue complete

    Given I import "/edm/bom_item" by keyFields "srcSysCd,bomCatCd,bomNum"
      | bomNum | srcSysCd | bomCatCd | cmpntNum | bomItmNum | bomItmVldFromDt | dstrbtnKeyCd | fxQtyInd | bomItmVldToDt | leadTimeOffst | cmpntQty | cmpntScrap_Pct | cmpntUomCd |
      | B001   | 0001     | M        | 001      | 001       | 20180515        |              | X        | 20180516      | 0             | 100.000  | 1.00           | TH         |
      | B002   | 0002     | M        | 002      | 002       | 20180516        | GLEI         |          | 20180517      | 0             | 200.000  | 2.00           | EA         |
    And I wait "/edm/bom_item" Async Queue complete

    Given I import "/edm/bom_hdr" by keyFields "srcSysCd,bomCatCd,bomNum,altBomNum"
      | srcSysCd | altBomNum | bomNum | bomCatCd | bomVldFromDt | bomBaseQty | bomUomCd |
      | 0001     | A001      | B001   | M        | 20180515     | 0001       | 0001     |
      | 0002     | A002      | B002   | M        | 20180516     | 0002       | 0002     |
    And I wait "/edm/bom_hdr" Async Queue complete

    Given I import "/plan/cns_plan_parameter" by keyFields "sourceSystem,dataObject,attribute"
      | sourceSystem | attribute | dataObject  | parameterValue |
      | 0001         | 0001      | SEND_TO_OMP | para01         |
      | 0002         | 0002      | SEND_TO_OMP | para02         |
    And I wait "/plan/cns_plan_parameter" Async Queue complete

    Given I import "/edm/matl_prod_versn" by keyFields "srcSysCd,matlNum,plntCd,prdntVrsnNum"
      | srcSysCd | plntCd | altBomNum | matlNum            | rtngGrpCntrNum | rtngGrpCd | prdntVrsnNum | dstrbtnKeyCd | valFromDt | valToDt  |
      | 0001     | M001   | A001      | 000000000000203700 | cntr001        | cd001     | vrsn001      | key001       | 20180516  | 20180517 |
      | 0001     | M001   | A001      | 000000000000203700 | cntr002        | cd002     | vrsn002      | key002       | 20180516  | 20180517 |
      | 0002     | M002   | A002      | 000000000000203701 | cntr003        | cd003     | vrsn003      | key003       | 20180514  | 20180515 |
      | 0002     | M002   | A002      | 000000000000203701 | cntr004        | cd004     | vrsn004      | key004       | 20180514  | 20180515 |
    And I wait "/edm/matl_prod_versn" Async Queue complete

    Given I import "/edm/matl_mfg_rtng" by keyFields "srcSysCd,matlNum,plntCd,rtngTypCd,rntgGrpCntrNbr"
      | srcSysCd | rntgGrpCd | rtngGrpcntrNum | plntCd | matlNum            | rntgGrpCntrNbr | rtngTypCd |
      | 0001     | cd001     | cntr001        | M001   | 000000000000203700 | nbr001         | typ001    |
      | 0001     | cd001     | cntr001        | M001   | 000000000000203700 | nbr002         | typ002    |
      | 0001     | cd002     | cntr002        | M001   | 000000000000203700 | nbr003         | typ003    |
      | 0001     | cd002     | cntr002        | M001   | 000000000000203700 | nbr004         | typ004    |
      | 0002     | cd003     | cntr003        | M002   | 000000000000203701 | nbr005         | typ005    |
      | 0002     | cd003     | cntr003        | M002   | 000000000000203701 | nbr006         | typ006    |
      | 0002     | cd004     | cntr004        | M002   | 000000000000203701 | nbr007         | typ007    |
      | 0002     | cd004     | cntr004        | M002   | 000000000000203701 | nbr008         | typ008    |
    And I wait "/edm/matl_mfg_rtng" Async Queue complete

    Given I import "/edm/mfg_rtng_itm_nde" by keyFields "srcSysCd,rtngTypCd,rtngGrpCd,rtngGrpCntrNbr,rtngNdeNum"
      | srcSysCd | rtngTypCd | rtngGrpCntrNbr | rtngGrpCd | rtngNdeNum |
      | 0001     | typ001    | nbr001         | cd001     | nde001     |
      | 0001     | typ001    | nbr001         | cd001     | nde002     |
      | 0001     | typ002    | nbr002         | cd001     | nde003     |
      | 0001     | typ002    | nbr002         | cd001     | nde004     |
      | 0001     | typ003    | nbr003         | cd002     | nde005     |
      | 0001     | typ003    | nbr003         | cd002     | nde006     |
      | 0001     | typ004    | nbr004         | cd002     | nde007     |
      | 0001     | typ004    | nbr004         | cd002     | nde008     |
      | 0002     | typ005    | nbr005         | cd003     | nde009     |
      | 0002     | typ005    | nbr005         | cd003     | nde010     |
      | 0002     | typ006    | nbr006         | cd003     | nde011     |
      | 0002     | typ006    | nbr006         | cd003     | nde012     |
      | 0002     | typ007    | nbr007         | cd004     | nde013     |
      | 0002     | typ007    | nbr007         | cd004     | nde014     |
      | 0002     | typ008    | nbr008         | cd004     | nde015     |
      | 0002     | typ008    | nbr008         | cd004     | nde016     |
    And I wait "/edm/mfg_rtng_itm_nde" Async Queue complete

    Given I import "/edm/mfg_rtng_itm" by keyFields "srcSysCd,rtngTypCd,rtngGrpCd,rtngItmNum"
      | srcSysCd | rtngTypCd | rtngItmNum | rtngGrpCd | operNum | bsQty |
      | 0001     | typ001    | nde001     | cd001     | oper001 | 1.000 |
      | 0001     | typ001    | nde002     | cd001     | oper002 | 1.000 |
      | 0001     | typ002    | nde003     | cd001     | oper003 | 1.000 |
      | 0001     | typ002    | nde004     | cd001     | oper004 | 1.000 |
      | 0001     | typ003    | nde005     | cd002     | oper005 | 1.000 |
      | 0001     | typ003    | nde006     | cd002     | oper006 | 1.000 |
      | 0001     | typ004    | nde007     | cd002     | oper007 | 1.000 |
      | 0001     | typ004    | nde008     | cd002     | oper008 | 1.000 |
      | 0002     | typ005    | nde009     | cd003     | oper009 | 1.000 |
      | 0002     | typ005    | nde010     | cd003     | oper010 | 1.000 |
      | 0002     | typ006    | nde011     | cd003     | oper011 | 1.000 |
      | 0002     | typ006    | nde012     | cd003     | oper012 | 1.000 |
      | 0002     | typ007    | nde013     | cd004     | oper013 | 1.000 |
      | 0002     | typ007    | nde014     | cd004     | oper014 | 1.000 |
      | 0002     | typ008    | nde015     | cd004     | oper015 | 1.000 |
      | 0002     | typ008    | nde016     | cd004     | oper016 | 1.000 |
    And I wait "/edm/mfg_rtng_itm" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmBomElement.xml" and execute file "jar/pangea-view.jar"
    Then A file is found on sink application with name "GDMBOMElement.tsv"

#    Then I check region data "/omp/gdm_bom_element" by keyFields "bomElementId"
    Then I check file data for filename "GDMBOMElement.tsv" by keyFields "bomElementId"
      | bomElementId                                                                             | active | activeFCTERP | activeOPRERP | activeSOPERP | batchId | bomId                                                             | bomType    | bomUsage     | comment | endEff              | erpFeedbackQuantity | locationId | offset | offsetCalendarId | offsetPercentage | offsetPercType | planLevelId        | productId  | quantity | startEff            |
      | vrsn001/000000000000203700/M001/B001/A001/0001_T11/oper001/001/001/20180515              | YES    | YES          | YES          | NO           |         | vrsn001/para01_000000000000203700/M001/B001/A001/0001_T11/oper001 | batchstart | fixed        |         | 2018/05/16 00:00:00 |                     | 0001_M001  | 0      |                  |                  |                | DetailedScheduling | para01_001 | 101.000  | 2018/05/16 00:00:00 |
      | vrsn001/000000000000203700/M001/B001/A001/0001_T11/oper001/20180515                      | YES    | YES          | YES          | NO           |         | vrsn001/para01_000000000000203700/M001/B001/A001/0001_T11/oper001 | batchend   | proportional |         | 2018/05/17 00:00:00 |                     | 0001_M001  | 86400  |                  |                  |                | *                  | para01_001 |          | 2018/05/15 00:00:00 |
      | vrsn001/000000000000203700/M001/B001/A001/0001_T11/oper002/001/001/20180515              | YES    | YES          | YES          | NO           |         | vrsn001/para01_000000000000203700/M001/B001/A001/0001_T11/oper002 | batchstart | fixed        |         | 2018/05/16 00:00:00 |                     | 0001_M001  | 0      |                  |                  |                | DetailedScheduling | para01_001 | 101.000  | 2018/05/16 00:00:00 |
      | vrsn001/000000000000203700/M001/B001/A001/0001_T11/oper003/001/001/20180515              | YES    | YES          | YES          | NO           |         | vrsn001/para01_000000000000203700/M001/B001/A001/0001_T11/oper003 | batchstart | fixed        |         | 2018/05/16 00:00:00 |                     | 0001_M001  | 0      |                  |                  |                | DetailedScheduling | para01_001 | 101.000  | 2018/05/16 00:00:00 |
      | vrsn001/000000000000203700/M001/B001/A001/0001_T11/oper004/001/001/20180515              | YES    | YES          | YES          | NO           |         | vrsn001/para01_000000000000203700/M001/B001/A001/0001_T11/oper004 | batchstart | fixed        |         | 2018/05/16 00:00:00 |                     | 0001_M001  | 0      |                  |                  |                | DetailedScheduling | para01_001 | 101.000  | 2018/05/16 00:00:00 |
      | vrsn002/000000000000203700/M001/B001/A001/0001_T11/oper005/001/001/20180515              | YES    | YES          | YES          | NO           |         | vrsn002/para01_000000000000203700/M001/B001/A001/0001_T11/oper005 | batchstart | fixed        |         | 2018/05/16 00:00:00 |                     | 0001_M001  | 0      |                  |                  |                | DetailedScheduling | para01_001 | 101.000  | 2018/05/16 00:00:00 |
      | vrsn002/000000000000203700/M001/B001/A001/0001_T11/oper006/001/001/20180515              | YES    | YES          | YES          | NO           |         | vrsn002/para01_000000000000203700/M001/B001/A001/0001_T11/oper006 | batchstart | fixed        |         | 2018/05/16 00:00:00 |                     | 0001_M001  | 0      |                  |                  |                | DetailedScheduling | para01_001 | 101.000  | 2018/05/16 00:00:00 |
      | vrsn002/000000000000203700/M001/B001/A001/0001_T11/oper007/001/001/20180515              | YES    | YES          | YES          | NO           |         | vrsn002/para01_000000000000203700/M001/B001/A001/0001_T11/oper007 | batchstart | fixed        |         | 2018/05/16 00:00:00 |                     | 0001_M001  | 0      |                  |                  |                | DetailedScheduling | para01_001 | 101.000  | 2018/05/16 00:00:00 |
      | vrsn002/000000000000203700/M001/B001/A001/0001_T11/oper008/001/001/20180515              | YES    | YES          | YES          | NO           |         | vrsn002/para01_000000000000203700/M001/B001/A001/0001_T11/oper008 | batchstart | fixed        |         | 2018/05/16 00:00:00 |                     | 0001_M001  | 0      |                  |                  |                | DetailedScheduling | para01_001 | 101.000  | 2018/05/16 00:00:00 |
      | vrsn003/000000000000203701/M002/B002/A002/0002_T11/oper009/002/002/20180516              | YES    | YES          | YES          | NO           |         | vrsn003/para02_000000000000203701/M002/B002/A002/0002_T11/oper009 | continu    | proportional |         | 2018/05/15 00:00:00 |                     | 0002_M002  | 0      |                  |                  |                | *                  | para02_002 | 104.000  | 2018/05/16 00:00:00 |
      | vrsn003/000000000000203701/M002/B002/A002/0002_T11/oper009/20180516                      | YES    | YES          | YES          | NO           |         | vrsn003/para02_000000000000203701/M002/B002/A002/0002_T11/oper009 | continu    | proportional |         | 2018/05/17 00:00:00 |                     | 0002_M002  | 172800 |                  |                  |                | *                  | para02_002 |          | 2018/05/14 00:00:00 |
      | vrsn003/000000000000203701/M002/B002/A002/0002_T11/oper010/002/002/20180516              | YES    | YES          | YES          | NO           |         | vrsn003/para02_000000000000203701/M002/B002/A002/0002_T11/oper010 | continu    | proportional |         | 2018/05/15 00:00:00 |                     | 0002_M002  | 0      |                  |                  |                | *                  | para02_002 | 104.000  | 2018/05/16 00:00:00 |
      | vrsn003/000000000000203701/M002/B002/A002/0002_T11/oper011/002/002/20180516              | YES    | YES          | YES          | NO           |         | vrsn003/para02_000000000000203701/M002/B002/A002/0002_T11/oper011 | continu    | proportional |         | 2018/05/15 00:00:00 |                     | 0002_M002  | 0      |                  |                  |                | *                  | para02_002 | 104.000  | 2018/05/16 00:00:00 |
      | vrsn003/000000000000203701/M002/B002/A002/0002_T11/oper012/002/002/20180516              | YES    | YES          | YES          | NO           |         | vrsn003/para02_000000000000203701/M002/B002/A002/0002_T11/oper012 | continu    | proportional |         | 2018/05/15 00:00:00 |                     | 0002_M002  | 0      |                  |                  |                | *                  | para02_002 | 104.000  | 2018/05/16 00:00:00 |
      | vrsn004/000000000000203701/M002/B002/A002/0002_T11/oper013/002/002/20180516              | YES    | YES          | YES          | NO           |         | vrsn004/para02_000000000000203701/M002/B002/A002/0002_T11/oper013 | continu    | proportional |         | 2018/05/15 00:00:00 |                     | 0002_M002  | 0      |                  |                  |                | *                  | para02_002 | 104.000  | 2018/05/16 00:00:00 |
      | vrsn004/000000000000203701/M002/B002/A002/0002_T11/oper014/002/002/20180516              | YES    | YES          | YES          | NO           |         | vrsn004/para02_000000000000203701/M002/B002/A002/0002_T11/oper014 | continu    | proportional |         | 2018/05/15 00:00:00 |                     | 0002_M002  | 0      |                  |                  |                | *                  | para02_002 | 104.000  | 2018/05/16 00:00:00 |
      | vrsn004/000000000000203701/M002/B002/A002/0002_T11/oper015/002/002/20180516              | YES    | YES          | YES          | NO           |         | vrsn004/para02_000000000000203701/M002/B002/A002/0002_T11/oper015 | continu    | proportional |         | 2018/05/15 00:00:00 |                     | 0002_M002  | 0      |                  |                  |                | *                  | para02_002 | 104.000  | 2018/05/16 00:00:00 |
      | vrsn004/000000000000203701/M002/B002/A002/0002_T11/oper016/002/002/20180516              | YES    | YES          | YES          | NO           |         | vrsn004/para02_000000000000203701/M002/B002/A002/0002_T11/oper016 | continu    | proportional |         | 2018/05/15 00:00:00 |                     | 0002_M002  | 0      |                  |                  |                | *                  | para02_002 | 104.000  | 2018/05/16 00:00:00 |
      | vrsn001/000000000000203700/M001/B001/A001/0001_T11/oper001/001/001/20180515/proportional | YES    | YES          | YES          | NO           |         | vrsn001/para01_000000000000203700/M001/B001/A001/0001_T11/oper001 | batchstart | fixed        |         | 2018/05/16 00:00:00 |                     | 0001_M001  | 0      |                  |                  |                | VolumePlanning     | para01_001 | 100.000  | 2018/05/16 00:00:00 |
      | vrsn001/000000000000203700/M001/B001/A001/0001_T11/oper002/001/001/20180515/proportional | YES    | YES          | YES          | NO           |         | vrsn001/para01_000000000000203700/M001/B001/A001/0001_T11/oper002 | batchstart | fixed        |         | 2018/05/16 00:00:00 |                     | 0001_M001  | 0      |                  |                  |                | VolumePlanning     | para01_001 | 100.000  | 2018/05/16 00:00:00 |
      | vrsn001/000000000000203700/M001/B001/A001/0001_T11/oper003/001/001/20180515/proportional | YES    | YES          | YES          | NO           |         | vrsn001/para01_000000000000203700/M001/B001/A001/0001_T11/oper003 | batchstart | fixed        |         | 2018/05/16 00:00:00 |                     | 0001_M001  | 0      |                  |                  |                | VolumePlanning     | para01_001 | 100.000  | 2018/05/16 00:00:00 |
      | vrsn001/000000000000203700/M001/B001/A001/0001_T11/oper004/001/001/20180515/proportional | YES    | YES          | YES          | NO           |         | vrsn001/para01_000000000000203700/M001/B001/A001/0001_T11/oper004 | batchstart | fixed        |         | 2018/05/16 00:00:00 |                     | 0001_M001  | 0      |                  |                  |                | VolumePlanning     | para01_001 | 100.000  | 2018/05/16 00:00:00 |
      | vrsn002/000000000000203700/M001/B001/A001/0001_T11/oper005/001/001/20180515/proportional | YES    | YES          | YES          | NO           |         | vrsn002/para01_000000000000203700/M001/B001/A001/0001_T11/oper005 | batchstart | fixed        |         | 2018/05/16 00:00:00 |                     | 0001_M001  | 0      |                  |                  |                | VolumePlanning     | para01_001 | 100.000  | 2018/05/16 00:00:00 |
      | vrsn002/000000000000203700/M001/B001/A001/0001_T11/oper006/001/001/20180515/proportional | YES    | YES          | YES          | NO           |         | vrsn002/para01_000000000000203700/M001/B001/A001/0001_T11/oper006 | batchstart | fixed        |         | 2018/05/16 00:00:00 |                     | 0001_M001  | 0      |                  |                  |                | VolumePlanning     | para01_001 | 100.000  | 2018/05/16 00:00:00 |
      | vrsn002/000000000000203700/M001/B001/A001/0001_T11/oper007/001/001/20180515/proportional | YES    | YES          | YES          | NO           |         | vrsn002/para01_000000000000203700/M001/B001/A001/0001_T11/oper007 | batchstart | fixed        |         | 2018/05/16 00:00:00 |                     | 0001_M001  | 0      |                  |                  |                | VolumePlanning     | para01_001 | 100.000  | 2018/05/16 00:00:00 |
      | vrsn002/000000000000203700/M001/B001/A001/0001_T11/oper008/001/001/20180515/proportional | YES    | YES          | YES          | NO           |         | vrsn002/para01_000000000000203700/M001/B001/A001/0001_T11/oper008 | batchstart | fixed        |         | 2018/05/16 00:00:00 |                     | 0001_M001  | 0      |                  |                  |                | VolumePlanning     | para01_001 | 100.000  | 2018/05/16 00:00:00 |

    And I delete the test data

    And I will remove all data with region "/omp/gdm_bom_element"

    And I will remove all data with region "/plan/edm_failed_data"
