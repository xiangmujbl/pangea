@pangea_test @AEAZ-4251
Feature: E.2.1.11 GDMBOMElement - Process - Consumption AEAZ-4251

  As a Data user,
  I want EDG to create GDM file for GDMBOMElement and send to OMP
  so that data can be consumed by OMP for planning

  Background:delete all test data

    Then I delete the test data

    And I will remove all data with region "/edm/mfg_order"

    And I will remove all data with region "/edm/mfg_order_itm"

    And I will remove all data with region "/edm/reserv_itm"

    And I will remove all data with region "/edm/bom_item"

    And I will remove all data with region "/edm/mfg_order_rtng"

    And I will remove all data with region "/edm/mfg_order_seq"

    And I will remove all data with region "/edm/matl_prod_versn"

    And I will remove all data with region "/plan/cns_plan_parameter"

    And I will remove all data with region "/plan/edm_failed_data"

    And I will remove all data with region "/omp/gdm_bom_element"

    And I will remove all data with region "/edm/material_global_v1"

    And I will remove all data with region "/plan/cns_material_plan_status"


  @Scenario1
  Scenario:  JOIN MFG_ORDER, MFG_ORDER_ITM, MFG_ORDER_RTNG, MFG_ORDER_SEQ  If no records found, skip ,if multiple records found, use all of them for output  --(J1)

    Given I import "/edm/mfg_order" by keyFields "sourceSysCd,mfgOrdrNum"
      | sourceSysCd   | mfgOrdrNum   | rsrvtnNum  | ordrRtngNum | bomCatCd | bomNum     | actRlseDt |
      # actRlseDt Between current date and current date - 1
      | CONS_LATAM    | 000001000320 | 0000000570 | 0000000340  | stlty    | 0000000540 | 20180520  |
      | CONS_LATAM    | 000001000321 | 0000000571 | 0000000341  | stlty    | 0000000541 | 20180612  |
      | CONS_LATAM    | 000001000322 | 0000000572 | 0000000342  | stlty    | 0000000542 | 20180612  |
      | CONS_LATAM    | 000001000323 | 0000000573 | 0000000343  | stlty    | 0000000543 | 20180612  |
      | CONS_LATAM    | 000001000324 | 0000000574 | 0000000344  | stlty    | 0000000544 | 20180612  |
      | CONS_LATAM    | 000001000325 | 0000000575 | 0000000345  | stlty    | 0000000545 | 20180612  |
      | CONS_LATAM    | 000001000326 | 0000000576 | 0000000346  | stlty    | 0000000546 | 20180612  |
      #sourceSysCd not equals /plan/cns_plan_parameter.attribute(CONS_LATAM-J1!=CONS_LATAM)
      | CONS_LATAM-J1 | 000001000327 | 0000000577 | 0000000347  | stlty    | 0000000547 | 20180612  |
      #MFG_ORDER.srcSysCd not equalse MFG_ORDER_ITM.srcSysCd
      | CONS_LATAM    | 000001000421 | 0000000471 | 0000000441  | stlty    | 0000000441 | 20180612  |
       #MFG_ORDER_ITM.srcSysCd not equalse MATL_PROD_VERSN.srcSysCd
      | CONS_LATAM    | 000001000422 | 0000000472 | 0000000442  | stlty    | 0000000442 | 20180612  |
       #MFG_ORDER.srcSysCd not equalse RESERV_ITM.srcSysCd
      | CONS_LATAM    | 000001000423 | 0000000473 | 0000000443  | stlty    | 0000000443 | 20180612  |
      #MFG_ORDER.srcSysCd not equalse MFG_ORDER_RTNG.srcSysCd
      | CONS_LATAM    | 000001000424 | 0000000474 | 0000000444  | stlty    | 0000000444 | 20180612  |
      #MFG_ORDER_RTNG.srcSysCd not equalse MFG_ORDER_SEQ.srcSysCd
      | CONS_LATAM    | 000001000425 | 0000000475 | 0000000445  | stlty    | 0000000445 | 20180612  |
      | CONS_LATAM    | 000001000426 | 0000000476 | 0000000446  | stlty    | 0000000446 | 20180611  |


    And I wait "/edm/mfg_order" Async Queue complete

    Given I import "/plan/cns_plan_parameter" by keyFields "attribute,dataObject,inclExcl,parameter,parameterValue,sourceSystem"
      | attribute  | dataObject  | inclExcl | parameter | parameterValue | sourceSystem |
      | CONS_LATAM | SEND_TO_OMP | E        | MRPType   | LA             | CONS_LATAM   |
    And I wait "/plan/cns_plan_parameter" Async Queue complete


    Given I import "/edm/mfg_order_itm" by keyFields "srcSysCd,mfgOrdrNum,lnItmNbr"

      | srcSysCd      | mfgOrdrNum   | lnItmNbr | matlNum            | plntCd | prdntVrsnNum | btchNum | rcvdQty  | goodRcptLdDaysQty | itmQty  |
      | CONS_LATAM    | 000001000320 | 0000     | 000000000000004770 | BR00   | V000         | 0000    | 5214.000 | 0                 | 521.000 |
      | CONS_LATAM    | 000001000321 | 0001     | 000000000000004771 | BR01   | V001         | 0001    | 5214.000 | 0                 | 521.000 |
         #mfgOrdrNum not equals  not found /edm/mfg_order.mfgOrdrNum(100001000322!=000001000322)
      | CONS_LATAM    | 100001000322 | 0001     | 000000000000004772 | BR02   | V002         | 0002    | 5214.000 | 0                 | 521.000 |
      | CONS_LATAM    | 000001000323 | 0001     | 000000000000004773 | BR03   | V003         | 0003    | 5214.000 | 0                 | 521.000 |
      | CONS_LATAM    | 000001000324 | 0001     | 000000000000004774 | BR04   | V004         | 0004    | 5214.000 | 0                 | 521.000 |
      | CONS_LATAM    | 000001000325 | 0001     | 000000000000004775 | BR05   | V005         | 0005    | 5214.000 | 0                 | 521.000 |
      | CONS_LATAM    | 000001000326 | 0001     | 000000000000004776 | BR06   | V006         | 0006    | 5214.000 | 1                 | 521.000 |
      | CONS_LATAM-J1 | 000001000327 | 0001     | 000000000000004777 | BR07   | V007         | 0007    | 5214.000 | 1                 | 521.000 |
      | CONS_LATAM-J1 | 000001000421 | 0001     | 000000000000004471 | BR41   | V041         | 0011    | 5214.000 | 1                 | 521.000 |
      | CONS_LATAM    | 000001000422 | 0001     | 000000000000004472 | BR42   | V042         | 0012    | 5214.000 | 1                 | 521.000 |
      | CONS_LATAM    | 000001000423 | 0001     | 000000000000004473 | BR43   | V043         | 0013    | 5214.000 | 1                 | 521.000 |
      | CONS_LATAM    | 000001000424 | 0001     | 000000000000004474 | BR44   | V045         | 0014    | 5214.000 | 1                 | 521.000 |
      | CONS_LATAM    | 000001000425 | 0001     | 000000000000004475 | BR45   | V045         | 0015    | 5214.000 | 1                 | 521.000 |
      | CONS_LATAM    | 000001000426 | 0001     | 000000000000004476 | BR46   | V046         | 0016    | 5214.000 | 1                 | 521.000 |


    And I wait "/edm/mfg_order_itm" Async Queue complete

    Given I import "/edm/matl_prod_versn" by keyFields "srcSysCd,matlNum,plntCd,prdntVrsnNum"
      | srcSysCd      | matlNum            | plntCd | prdntVrsnNum | dstrbtnKeyCd |
      | CONS_LATAM    | 000000000000004770 | BR00   | V000         |              |
      | CONS_LATAM    | 000000000000004771 | BR01   | V001         |              |
      | CONS_LATAM    | 000000000000004772 | BR02   | V002         |              |
      #matlNum not equals  not found /edm/mfg_order.matlNum (100000000000004772!=000000000000004772)
      | CONS_LATAM    | 100000000000004773 | BR03   | V003         |              |
      | CONS_LATAM    | 000000000000004774 | BR04   | V004         |              |
      | CONS_LATAM    | 000000000000004775 | BR05   | V005         |              |
      | CONS_LATAM    | 000000000000004776 | BR06   | V006         |              |
      | CONS_LATAM-J1 | 000000000000004777 | BR07   | V007         |              |
      | CONS_LATAM    | 000000000000004471 | BR41   | V041         |              |
      | CONS_LATAM-J1 | 000000000000004472 | BR42   | V042         |              |
      | CONS_LATAM    | 000000000000004473 | BR43   | V043         |              |
      | CONS_LATAM    | 000000000000004474 | BR44   | V044         |              |
      | CONS_LATAM    | 000000000000004475 | BR45   | V045         |              |
      | CONS_LATAM    | 000000000000004476 | BR46   | V046         |              |

    And I wait "/edm/matl_prod_versn" Async Queue complete


    Given I import "/edm/reserv_itm" by keyFields "sourceSysCd,rsrvtnNum,rsrvtnItmNum,rsrvtnRcrdTypCd"
      | sourceSysCd   | rsrvtnNum  | rsrvtnItmNum | rsrvtnRcrdTypCd | delInd | qtyFxInd | bomItmNum | btchNum | matlNum            | leadTimeOffset | rqmtQty | wthdrnQty |
       #delInd Not equal to X
      | CONS_LATAM    | 0000000570 | 0000         |                 |        |          | 0020      | 0020    | 000000000000004770 | 0              | 15.000  | 15.000    |
      | CONS_LATAM    | 0000000571 | 0001         |                 | X      |          | 0020      | 0021    | 000000000000004771 | 0              | 15.000  | 15.000    |
      | CONS_LATAM    | 0000000572 | 0002         |                 |        |          | 0020      | 0022    | 000000000000004772 | 0              | 15.000  | 15.000    |
      | CONS_LATAM    | 0000000573 | 0003         |                 |        |          | 0020      | 0023    | 000000000000004773 | 0              | 15.000  | 15.000    |
      | CONS_LATAM    | 0000000574 | 0004         |                 |        |          | 0020      | 0024    | 000000000000004774 | 0              | 15.000  | 15.000    |
      | CONS_LATAM    | 0000000575 | 0005         |                 |        |          | 0020      | 0025    | 000000000000004775 | 0              | 15.000  | 15.000    |
      | CONS_LATAM    | 0000000576 | 0006         |                 |        |          | 0020      | 0026    | 000000000000004776 | 1              | 15.000  | 15.000    |
      | CONS_LATAM-J1 | 0000000577 | 0007         |                 |        |          | 0020      | 0027    | 000000000000004777 | 1              | 15.000  | 15.000    |
      | CONS_LATAM    | 0000000471 | 0041         |                 |        |          | 0020      | 0021    | 000000000000004471 | 1              | 15.000  | 15.000    |
      | CONS_LATAM    | 0000000472 | 0042         |                 |        |          | 0020      | 0022    | 000000000000004472 | 1              | 15.000  | 15.000    |
      | CONS_LATAM-J1 | 0000000473 | 0043         |                 |        |          | 0020      | 0023    | 000000000000004473 | 1              | 15.000  | 15.000    |
      | CONS_LATAM    | 0000000474 | 0044         |                 |        |          | 0020      | 0024    | 000000000000004474 | 1              | 15.000  | 15.000    |
      | CONS_LATAM    | 0000000475 | 0045         |                 |        |          | 0020      | 0025    | 000000000000004475 | 1              | 15.000  | 15.000    |
      | CONS_LATAM    | 0000000476 | 0046         |                 |        |          | 0020      | 0026    | 000000000000004476 | 1              | 15.000  | 15.000    |


    And I wait "/edm/reserv_itm" Async Queue complete


    Given I import "/edm/mfg_order_rtng" by keyFields "srcSysCd,ordrRtngNum,ordrRtngCtrNum"
      | srcSysCd      | ordrRtngNum | ordrRtngCtrNum | operNum |
      | CONS_LATAM    | 0000000340  | 00000000       | 0010    |
      | CONS_LATAM    | 0000000341  | 00000001       | 0010    |
      | CONS_LATAM    | 0000000342  | 00000002       | 0010    |
      | CONS_LATAM    | 0000000343  | 00000003       | 0010    |
      #ordrRtngNum   not equal mfg_order ordrRtngNum
      | CONS_LATAM    | 1000000344  | 00000004       | 0010    |
      | CONS_LATAM    | 0000000345  | 00000005       | 0010    |
      | CONS_LATAM    | 0000000346  | 00000006       | 0010    |
      | CONS_LATAM-J1 | 0000000347  | 00000007       | 0010    |
      | CONS_LATAM    | 0000000441  | 00000041       | 0010    |
      | CONS_LATAM    | 0000000442  | 00000042       | 0010    |
      | CONS_LATAM    | 0000000443  | 00000043       | 0010    |
      | CONS_LATAM-J1 | 0000000444  | 00000044       | 0010    |
      | CONS_LATAM    | 0000000445  | 00000045       | 0010    |
      | CONS_LATAM    | 0000000446  | 00000046       | 0010    |

    And I wait "/edm/mfg_order_rtng" Async Queue complete

    Given I import "/edm/mfg_order_seq" by keyFields "srcSysCd,ordrRtngNum,ordrRtngCtrNum"
      | srcSysCd      | ordrRtngNum | ordrRtngCtrNum | rtngSqncNum |
      | CONS_LATAM    | 0000000340  | 0000000000     | 444         |
      | CONS_LATAM    | 0000000341  | 0000000001     | 444         |
      | CONS_LATAM    | 0000000342  | 0000000002     | 444         |
      | CONS_LATAM    | 0000000343  | 0000000003     | 444         |
      | CONS_LATAM    | 0000000344  | 0000000004     | 444         |
       #/edm/mfg_order_seq ordrRtngNum not equal /edm/mfg_order_rtng ordrRtngNum(0000000345!=1000000345)
      | CONS_LATAM    | 1000000345  | 0000000005     | 444         |
      | CONS_LATAM    | 0000000346  | 0000000006     | 444         |
      | CONS_LATAM-J1 | 0000000347  | 0000000007     | 444         |
      | CONS_LATAM    | 0000000441  | 0000000041     | 444         |
      | CONS_LATAM    | 0000000442  | 0000000042     | 444         |
      | CONS_LATAM    | 0000000443  | 0000000043     | 444         |
      | CONS_LATAM    | 0000000444  | 0000000044     | 444         |
      | CONS_LATAM-J1 | 0000000445  | 0000000045     | 444         |
      | CONS_LATAM    | 0000000446  | 0000000046     | 444         |

    And I wait "/edm/mfg_order_seq" Async Queue complete

    Given I import "/edm/bom_item" by keyFields "srcSysCd,bomCatCd,bomNum,bomItmNdeNum,bomItmCntrNbr"
      | srcSysCd   | bomCatCd | bomNum     | bomItmNdeNum | bomItmCntrNbr | dstrbtnKeyCd |
      | CONS_LATAM | stlty    | 0000000546 | 1            | 2             |              |
      | CONS_LATAM | stlty    | 0000000446 | 1            | 2             |              |

    And I wait "/edm/bom_item" Async Queue complete



    Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | sourceSystem | localMaterialNumber | localDpParentCode | primaryPlanningCode |
      | CONS_LATAM   | 000000000000004476  | LDPC01            | LBU01               |
      | CONS_LATAM   | 000000000000004776  | LDPC01            | LBU01               |
      | CONS_LATAM   | 000000000000004770  | LDPC01            | LBU01               |

    And I wait "/edm/material_global_v1" Async Queue complete


    Given I import "/plan/cns_material_plan_status" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | spRelevant | noPlanRelevant | dpRelevant |
      | CONS_LATAM   | 000000000000004476  | BR46       | X          | X              | X          |
      | CONS_LATAM   | 000000000000004776  | BR06       | X          | X              | X          |
      | CONS_LATAM   | 000000000000004770  | BR00       | X          | X              | X          |

    And I wait "/plan/cns_material_plan_status" Async Queue complete

    When I submit task with xml file "xml/OMP/OMPGdmbomelementProcess.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMBOMElement_process.tsv"

    Then I check file data for filename "GDMBOMElement_process.tsv" by keyFields "bomElementId"

#    Then I check region data "/omp/gdm_bom_element" by keyFields "bomElementId"

      | bomElementId                                   | Active | ActiveFCTERP | ActiveOPRERP | ActiveSOPERP | BatchId | BOMId                           | BOMType    | BOMUsage     | Comments | EndEff              | ERPFeedbackQuantity | LocationId      | Offset     | OffsetCalendarId | OffsetPercentage | OffsetPercType | PlanLevelId | ProductId | Quantity | StartEff            |
      | PRO/CONS_LATAM/1000326/444/0010/4776/0020/0026 | YES    | YES          | YES          | NO           | 0026    | PRO/CONS_LATAM/1000326/346/0010 | batchstart | proportional |          | 31/12/2998 23:59:59 | 5214.000            | CONS_LATAM_BR06 | 864000.000 |                  |                  |                | *           | LBU01     | 0.000    | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/1000326/444/0010/4776/0001/0006 | YES    | YES          | YES          | NO           | 0006    | PRO/CONS_LATAM/1000326/346/0010 | batchend   | proportional |          | 31/12/2998 23:59:59 | 15.000              | CONS_LATAM_BR06 | 864000.000 |                  |                  |                | *           | LBU01     | -521.000 | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/1000426/444/0010/4476/0020/0026 | YES    | YES          | YES          | NO           | 0026    | PRO/CONS_LATAM/1000426/446/0010 | batchstart | proportional |          | 31/12/2998 23:59:59 | 5214.000            | CONS_LATAM_BR46 | 864000.000 |                  |                  |                | *           | LBU01     | 0.000    | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/1000426/444/0010/4476/0001/0016 | YES    | YES          | YES          | NO           | 0016    | PRO/CONS_LATAM/1000426/446/0010 | batchend   | proportional |          | 31/12/2998 23:59:59 | 15.000              | CONS_LATAM_BR46 | 864000.000 |                  |                  |                | *           | LBU01     | -521.000 | 01/01/1980 00:00:00 |


  @Scenario2
  Scenario:  set the BOMType value  according to  BOM_ITEM-dstrbtnKeyCd or MATL_PRD_VERSN-dstrbtnKeyCd   --(T6)
  AND  set  Offset RESERV_ITM- leadTimeOffset * 864000 or "FOR PRODUCED MAT" MFG_ORDER_ITM -goodRcptLdDaysQty  *864000 --(T11)

    Given I import "/edm/mfg_order" by keyFields "sourceSysCd,mfgOrdrNum"
      | sourceSysCd | mfgOrdrNum   | rsrvtnNum  | ordrRtngNum | bomCatCd | bomNum     | actRlseDt |
      | CONS_LATAM  | 000001000321 | 0000000571 | 0000000341  | stlty    | 0000000541 | 20180612  |
      | CONS_LATAM  | 000001000322 | 0000000572 | 0000000342  | stlty    | 0000000542 | 20180612  |
      | CONS_LATAM  | 000001000326 | 0000000576 | 0000000346  | stlty    | 0000000546 | 20180612  |

    And I wait "/edm/mfg_order" Async Queue complete

    Given I import "/plan/cns_plan_parameter" by keyFields "attribute,dataObject,inclExcl,parameter,parameterValue,sourceSystem"
      | attribute  | dataObject  | inclExcl | parameter | parameterValue | sourceSystem |
      | CONS_LATAM | SEND_TO_OMP | E        | MRPType   | LA             | CONS_LATAM   |

    And I wait "/plan/cns_plan_parameter" Async Queue complete


    Given I import "/edm/mfg_order_itm" by keyFields "srcSysCd,mfgOrdrNum,lnItmNbr"

      | srcSysCd   | mfgOrdrNum   | lnItmNbr | matlNum            | plntCd | prdntVrsnNum | btchNum | rcvdQty  | goodRcptLdDaysQty | itmQty  |
      | CONS_LATAM | 000001000321 | 0001     | 000000000000004771 | BR01   | V001         | 0001    | 5214.000 | 0                 | 521.000 |
      | CONS_LATAM | 000001000322 | 0001     | 000000000000004772 | BR02   | V002         | 0002    | 5214.000 | 5                 | 521.000 |
      | CONS_LATAM | 000001000326 | 0001     | 000000000000004776 | BR06   | V006         | 0006    | 5214.000 | 1                 | 521.000 |

    And I wait "/edm/mfg_order_itm" Async Queue complete


    Given I import "/edm/matl_prod_versn" by keyFields "srcSysCd,matlNum,plntCd,prdntVrsnNum"
      | srcSysCd   | matlNum            | plntCd | prdntVrsnNum | dstrbtnKeyCd |
      | CONS_LATAM | 000000000000004771 | BR01   | V001         |              |
      | CONS_LATAM | 000000000000004772 | BR02   | V002         | GLEI         |
      | CONS_LATAM | 000000000000004776 | BR06   | V006         | GL           |

    And I wait "/edm/matl_prod_versn" Async Queue complete

    Given I import "/edm/reserv_itm" by keyFields "sourceSysCd,rsrvtnNum,rsrvtnItmNum,rsrvtnRcrdTypCd"
      | sourceSysCd | rsrvtnNum  | rsrvtnItmNum | rsrvtnRcrdTypCd | delInd | qtyFxInd | bomItmNum | btchNum | matlNum            | leadTimeOffset | rqmtQty | wthdrnQty |
      | CONS_LATAM  | 0000000571 | 0001         |                 |        |          | 0020      | 0021    | 000000000000004771 | 0              | 15.000  | 15.000    |
      | CONS_LATAM  | 0000000572 | 0002         |                 |        |          | 0020      | 0022    | 000000000000004772 | 10             | 15.000  | 15.000    |
      | CONS_LATAM  | 0000000576 | 0006         |                 |        |          | 0020      | 0026    | 000000000000004776 | 1              | 15.000  | 15.000    |

    And I wait "/edm/reserv_itm" Async Queue complete

    Given I import "/edm/mfg_order_rtng" by keyFields "srcSysCd,ordrRtngNum,ordrRtngCtrNum"
      | srcSysCd   | ordrRtngNum | ordrRtngCtrNum | operNum |
      | CONS_LATAM | 0000000341  | 00000001       | 0010    |
      | CONS_LATAM | 0000000342  | 00000002       | 0010    |
      | CONS_LATAM | 0000000346  | 00000006       | 0010    |

    And I wait "/edm/mfg_order_rtng" Async Queue complete

    Given I import "/edm/mfg_order_seq" by keyFields "srcSysCd,ordrRtngNum,ordrRtngCtrNum"
      | srcSysCd   | ordrRtngNum | ordrRtngCtrNum | rtngSqncNum |
      | CONS_LATAM | 0000000341  | 0000000001     | 444         |
      | CONS_LATAM | 0000000342  | 0000000002     | 444         |
      | CONS_LATAM | 0000000346  | 0000000006     | 444         |

    And I wait "/edm/mfg_order_seq" Async Queue complete

    Given I import "/edm/bom_item" by keyFields "srcSysCd,bomCatCd,bomNum,bomItmNdeNum,bomItmCntrNbr"
      | srcSysCd   | bomCatCd | bomNum     | bomItmNdeNum | bomItmCntrNbr | dstrbtnKeyCd |
      | CONS_LATAM | stlty    | 0000000541 | 1            | 2             |              |
      | CONS_LATAM | stlty    | 0000000542 | 1            | 2             | GLEI         |
      | CONS_LATAM | stlty    | 0000000546 | 1            | 2             | GL           |

    Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | sourceSystem | localMaterialNumber | localDpParentCode | primaryPlanningCode |
      | CONS_LATAM   | 000000000000004771  | LDPC01            | LBU01               |
      | CONS_LATAM   | 000000000000004772  | LDPC02            | LBU02               |
      | CONS_LATAM   | 000000000000004776  | LDPC06            | LBU06               |

    And I wait "/edm/material_global_v1" Async Queue complete


    Given I import "/plan/cns_material_plan_status" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | spRelevant | noPlanRelevant | dpRelevant |
      | CONS_LATAM   | 000000000000004771  | BR01       | X          | X              | X          |
      | CONS_LATAM   | 000000000000004772  | BR02       | X          | X              | X          |
      | CONS_LATAM   | 000000000000004776  | BR06       | X          | X              | X          |

    And I wait "/plan/cns_material_plan_status" Async Queue complete



    And I wait "/edm/bom_item" Async Queue complete

    When I submit task with xml file "xml/OMP/OMPGdmbomelementProcess.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMBOMElement_process.tsv"
    Then I check file data for filename "GDMBOMElement_process.tsv" by keyFields "bomElementId"
#    Then I check region data "/omp/gdm_bom_element" by keyFields "bomElementId"

      | bomElementId                                   | Active | ActiveFCTERP | ActiveOPRERP | ActiveSOPERP | BatchId | BOMId                           | BOMType    | BOMUsage     | Comments | EndEff              | ERPFeedbackQuantity | LocationId      | Offset      | OffsetCalendarId | OffsetPercentage | OffsetPercType | PlanLevelId | ProductId | Quantity | StartEff            |
      | PRO/CONS_LATAM/1000321/444/0010/4771/0020/0021 | YES    | YES          | YES          | NO           | 0021    | PRO/CONS_LATAM/1000321/341/0010 | batchstart | proportional |          | 31/12/2998 23:59:59 | 5214.000            | CONS_LATAM_BR01 | 0.000       |                  |                  |                | *           | LBU01     | 0.000    | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/1000321/444/0010/4771/0001/0001 | YES    | YES          | YES          | NO           | 0001    | PRO/CONS_LATAM/1000321/341/0010 | batchend   | proportional |          | 31/12/2998 23:59:59 | 15.000              | CONS_LATAM_BR01 | 0.000       |                  |                  |                | *           | LBU01     | -521.000 | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/1000322/444/0010/4772/0020/0022 | YES    | YES          | YES          | NO           | 0022    | PRO/CONS_LATAM/1000322/342/0010 | continu    | proportional |          | 31/12/2998 23:59:59 | 5214.000            | CONS_LATAM_BR02 | 8640000.000 |                  |                  |                | *           | LBU02     | 0.000    | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/1000322/444/0010/4772/0001/0002 | YES    | YES          | YES          | NO           | 0002    | PRO/CONS_LATAM/1000322/342/0010 | continu    | proportional |          | 31/12/2998 23:59:59 | 15.000              | CONS_LATAM_BR02 | 4320000.000 |                  |                  |                | *           | LBU02     | -521.000 | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/1000326/444/0010/4776/0020/0026 | YES    | YES          | YES          | NO           | 0026    | PRO/CONS_LATAM/1000326/346/0010 |            | proportional |          | 31/12/2998 23:59:59 | 5214.000            | CONS_LATAM_BR06 | 864000.000  |                  |                  |                | *           | LBU06     | 0.000    | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/1000326/444/0010/4776/0001/0006 | YES    | YES          | YES          | NO           | 0006    | PRO/CONS_LATAM/1000326/346/0010 |            | proportional |          | 31/12/2998 23:59:59 | 15.000              | CONS_LATAM_BR06 | 864000.000  |                  |                  |                | *           | LBU06     | -521.000 | 01/01/1980 00:00:00 |

  @Scenario3
  Scenario:set the BOMUsage value  according to  RESERV_ITM-qtyFxInd  or "FOR PRODUCED MAT" default to "proportional" --(T7)
  AND set the PlanLevelId value  according to  RESERV_ITM-qtyFxInd  or  "FOR PRODUCED MAT" default to "*"  --(T12)
  AND set the Quantity =  RESERV_ITM-rqmtQty   - RESERV_ITM-wthdrnQty  or  "FOR PRODUCED MAT" Quantity =  MFG_ORDER_ITM- itmQty*-1 --(T15)

    Given I import "/edm/mfg_order" by keyFields "sourceSysCd,mfgOrdrNum"
      | sourceSysCd | mfgOrdrNum   | rsrvtnNum  | ordrRtngNum | bomCatCd | bomNum     | actRlseDt |
      | CONS_LATAM  | 000001000321 | 0000000571 | 0000000341  | stlty    | 0000000541 | 20180612  |
      | CONS_LATAM  | 000001000322 | 0000000572 | 0000000342  | stlty    | 0000000542 | 20180612  |
      | CONS_LATAM  | 000001000326 | 0000000576 | 0000000346  | stlty    | 0000000546 | 20180612  |

    And I wait "/edm/mfg_order" Async Queue complete

    Given I import "/plan/cns_plan_parameter" by keyFields "attribute,dataObject,inclExcl,parameter,parameterValue,sourceSystem"
      | attribute  | dataObject  | inclExcl | parameter | parameterValue | sourceSystem |
      | CONS_LATAM | SEND_TO_OMP | E        | MRPType   | LA             | CONS_LATAM   |

    And I wait "/plan/cns_plan_parameter" Async Queue complete


    Given I import "/edm/mfg_order_itm" by keyFields "srcSysCd,mfgOrdrNum,lnItmNbr"

      | srcSysCd   | mfgOrdrNum   | lnItmNbr | matlNum            | plntCd | prdntVrsnNum | btchNum | rcvdQty  | goodRcptLdDaysQty | itmQty  |
      | CONS_LATAM | 000001000321 | 0001     | 000000000000004771 | BR01   | V001         | 0001    | 5214.000 | 0                 | 521.000 |
      | CONS_LATAM | 000001000322 | 0001     | 000000000000004772 | BR02   | V002         | 0002    | 5214.000 | 0                 | 521.000 |
      | CONS_LATAM | 000001000326 | 0001     | 000000000000004776 | BR06   | V006         | 0006    | 5214.000 | 1                 | 521.000 |

    And I wait "/edm/mfg_order_itm" Async Queue complete


    Given I import "/edm/matl_prod_versn" by keyFields "srcSysCd,matlNum,plntCd,prdntVrsnNum"
      | srcSysCd   | matlNum            | plntCd | prdntVrsnNum | dstrbtnKeyCd |
      | CONS_LATAM | 000000000000004771 | BR01   | V001         |              |
      | CONS_LATAM | 000000000000004772 | BR02   | V002         |              |
      | CONS_LATAM | 000000000000004776 | BR06   | V006         |              |

    And I wait "/edm/matl_prod_versn" Async Queue complete


    Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | sourceSystem | localMaterialNumber | localDpParentCode | primaryPlanningCode |
      | CONS_LATAM   | 000000000000004771  | LDPC01            | LBU01               |
      | CONS_LATAM   | 000000000000004772  | LDPC02            | LBU02               |
      | CONS_LATAM   | 000000000000004776  | LDPC06            | LBU06               |

    And I wait "/edm/material_global_v1" Async Queue complete


    Given I import "/plan/cns_material_plan_status" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | spRelevant | noPlanRelevant | dpRelevant |
      | CONS_LATAM   | 000000000000004771  | BR01       | X          | X              | X          |
      | CONS_LATAM   | 000000000000004772  | BR02       | X          | X              | X          |
      | CONS_LATAM   | 000000000000004776  | BR06       | X          | X              | X          |

    And I wait "/plan/cns_material_plan_status" Async Queue complete


  # qtyFxInd is blank is X is not X
    Given I import "/edm/reserv_itm" by keyFields "sourceSysCd,rsrvtnNum,rsrvtnItmNum,rsrvtnRcrdTypCd"
      | sourceSysCd | rsrvtnNum  | rsrvtnItmNum | rsrvtnRcrdTypCd | delInd | qtyFxInd | bomItmNum | btchNum | matlNum            | leadTimeOffset | rqmtQty | wthdrnQty |
      | CONS_LATAM  | 0000000571 | 0001         |                 |        |          | 0020      | 0021    | 000000000000004771 | 0              | 15.000  | 16.000    |
      | CONS_LATAM  | 0000000572 | 0002         |                 |        | X        | 0020      | 0022    | 000000000000004772 | 0              | 15.000  | 14.000    |
      | CONS_LATAM  | 0000000576 | 0006         |                 |        | A        | 0020      | 0026    | 000000000000004776 | 1              | 15.000  | 15.000    |

    And I wait "/edm/reserv_itm" Async Queue complete


    Given I import "/edm/mfg_order_rtng" by keyFields "srcSysCd,ordrRtngNum,ordrRtngCtrNum"
      | srcSysCd   | ordrRtngNum | ordrRtngCtrNum | operNum |
      | CONS_LATAM | 0000000341  | 00000001       | 0010    |
      | CONS_LATAM | 0000000342  | 00000002       | 0010    |
      | CONS_LATAM | 0000000346  | 00000006       | 0010    |

    And I wait "/edm/mfg_order_rtng" Async Queue complete

    Given I import "/edm/mfg_order_seq" by keyFields "srcSysCd,ordrRtngNum,ordrRtngCtrNum"
      | srcSysCd   | ordrRtngNum | ordrRtngCtrNum | rtngSqncNum |
      | CONS_LATAM | 0000000341  | 0000000001     | 444         |
      | CONS_LATAM | 0000000342  | 0000000002     | 444         |
      | CONS_LATAM | 0000000346  | 0000000006     | 444         |

    And I wait "/edm/mfg_order_seq" Async Queue complete

    Given I import "/edm/bom_item" by keyFields "srcSysCd,bomCatCd,bomNum,bomItmNdeNum,bomItmCntrNbr"
      | srcSysCd   | bomCatCd | bomNum     | bomItmNdeNum | bomItmCntrNbr | dstrbtnKeyCd |
      | CONS_LATAM | stlty    | 0000000541 | 1            | 2             |              |
      | CONS_LATAM | stlty    | 0000000542 | 1            | 2             |              |
      | CONS_LATAM | stlty    | 0000000546 | 1            | 2             |              |

    And I wait "/edm/bom_item" Async Queue complete

    When I submit task with xml file "xml/OMP/OMPGdmbomelementProcess.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMBOMElement_process.tsv"

    Then I check file data for filename "GDMBOMElement_process.tsv" by keyFields "bomElementId"
#    Then I check region data "/omp/gdm_bom_element" by keyFields "bomElementId"

      | bomElementId                                                | Active | ActiveFCTERP | ActiveOPRERP | ActiveSOPERP | BatchId | BOMId                           | BOMType    | BOMUsage     | Comments | EndEff              | ERPFeedbackQuantity | LocationId      | Offset     | OffsetCalendarId | OffsetPercentage | OffsetPercType | PlanLevelId        | ProductId | Quantity | StartEff            |
      | PRO/CONS_LATAM/1000321/444/0010/4771/0020/0021              | YES    | YES          | YES          | NO           | 0021    | PRO/CONS_LATAM/1000321/341/0010 | batchstart | proportional |          | 31/12/2998 23:59:59 | 5214.000            | CONS_LATAM_BR01 | 0.000      |                  |                  |                | *                  | LBU01     | -1.000   | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/1000321/444/0010/4771/0001/0001              | YES    | YES          | YES          | NO           | 0001    | PRO/CONS_LATAM/1000321/341/0010 | batchend   | proportional |          | 31/12/2998 23:59:59 | 16.000              | CONS_LATAM_BR01 | 0.000      |                  |                  |                | *                  | LBU01     | -521.000 | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/1000322/444/0010/4772/0020/0022              | YES    | YES          | YES          | NO           | 0022    | PRO/CONS_LATAM/1000322/342/0010 | batchstart | fixed        |          | 31/12/2998 23:59:59 | 5214.000            | CONS_LATAM_BR02 | 0.000      |                  |                  |                | DetailedScheduling | LBU02     | 1.000    | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/1000322/444/0010/4772/0020/0022/proportional | YES    | YES          | YES          | NO           | 0022    | PRO/CONS_LATAM/1000322/342/0010 | batchstart | fixed        |          | 31/12/2998 23:59:59 | 5214.000            | CONS_LATAM_BR02 | 0.000      |                  |                  |                | VolumePlanning     | LBU02     | 1.000    | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/1000322/444/0010/4772/0001/0002              | YES    | YES          | YES          | NO           | 0002    | PRO/CONS_LATAM/1000322/342/0010 | batchend   | proportional |          | 31/12/2998 23:59:59 | 14.000              | CONS_LATAM_BR02 | 0.000      |                  |                  |                | *                  | LBU02     | -521.000 | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/1000326/444/0010/4776/0020/0026              | YES    | YES          | YES          | NO           | 0026    | PRO/CONS_LATAM/1000326/346/0010 | batchstart |              |          | 31/12/2998 23:59:59 | 5214.000            | CONS_LATAM_BR06 | 864000.000 |                  |                  |                |                    | LBU06     | 0.000    | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/1000326/444/0010/4776/0001/0006              | YES    | YES          | YES          | NO           | 0006    | PRO/CONS_LATAM/1000326/346/0010 | batchend   | proportional |          | 31/12/2998 23:59:59 | 15.000              | CONS_LATAM_BR06 | 864000.000 |                  |                  |                | *                  | LBU06     | -521.000 | 01/01/1980 00:00:00 |


  @Scenario4
  Scenario:  set ERPFeedbackQuantity by Suming  the MFG_ORDER_ITM-rcvdQty  for each mfgOrdrNum and matlNum  or "FOR PRODUCED MAT" RESERV_ITM – wthdrnQty --(T9)

    Given I import "/edm/mfg_order" by keyFields "sourceSysCd,mfgOrdrNum"
      | sourceSysCd | mfgOrdrNum   | rsrvtnNum  | ordrRtngNum | bomCatCd | bomNum     | actRlseDt |
      | CONS_LATAM  | 000001000321 | 0000000571 | 0000000341  | stlty    | 0000000541 | 20180612  |

    And I wait "/edm/mfg_order" Async Queue complete

    Given I import "/plan/cns_plan_parameter" by keyFields "attribute,dataObject,inclExcl,parameter,parameterValue,sourceSystem"
      | attribute  | dataObject  | inclExcl | parameter | parameterValue | sourceSystem |
      | CONS_LATAM | SEND_TO_OMP | E        | MRPType   | LA             | CONS_LATAM   |

    And I wait "/plan/cns_plan_parameter" Async Queue complete


    Given I import "/edm/mfg_order_itm" by keyFields "srcSysCd,mfgOrdrNum,lnItmNbr"

      | srcSysCd   | mfgOrdrNum   | lnItmNbr | matlNum            | plntCd | prdntVrsnNum | btchNum | rcvdQty  | goodRcptLdDaysQty | itmQty  |
      | CONS_LATAM | 000001000321 | 0001     | 000000000000004771 | BR01   | V001         | 0001    | 5211.000 | 0                 | 521.000 |
      | CONS_LATAM | 000001000321 | 0002     | 000000000000004772 | BR01   | V001         | 0001    | 5212.000 | 0                 | 522.000 |
      | CONS_LATAM | 000001000321 | 0003     | 000000000000004773 | BR01   | V001         | 0001    | 5213.000 | 0                 | 523.000 |
      | CONS_LATAM | 000001000321 | 0004     | 000000000000004773 | BR01   | V001         | 0001    | 5213.000 | 0                 | 523.000 |
      | CONS_LATAM | 000001000321 | 0005     | 000000000000004775 | BR01   | V001         | 0001    | 5215.000 | 0                 | 525.000 |

    And I wait "/edm/mfg_order_itm" Async Queue complete


    Given I import "/edm/matl_prod_versn" by keyFields "srcSysCd,matlNum,plntCd,prdntVrsnNum"
      | srcSysCd   | matlNum            | plntCd | prdntVrsnNum | dstrbtnKeyCd |
      | CONS_LATAM | 000000000000004771 | BR01   | V001         |              |
      | CONS_LATAM | 000000000000004772 | BR01   | V001         |              |
      | CONS_LATAM | 000000000000004773 | BR01   | V001         |              |

    And I wait "/edm/matl_prod_versn" Async Queue complete





    Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | sourceSystem | localMaterialNumber | localDpParentCode | primaryPlanningCode |
      | CONS_LATAM   | 000000000000004771  | LDPC01            | LBU01               |
      | CONS_LATAM   | 000000000000004772  | LDPC02            | LBU02               |
      | CONS_LATAM   | 000000000000004773  | LDPC03            | LBU03               |

    And I wait "/edm/material_global_v1" Async Queue complete


    Given I import "/plan/cns_material_plan_status" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | spRelevant | noPlanRelevant | dpRelevant |
      | CONS_LATAM   | 000000000000004771  | BR01       | X          | X              | X          |
      | CONS_LATAM   | 000000000000004772  | BR01       | X          | X              | X          |
      | CONS_LATAM   | 000000000000004773  | BR01       | X          | X              | X          |

    And I wait "/plan/cns_material_plan_status" Async Queue complete


    Given I import "/edm/reserv_itm" by keyFields "sourceSysCd,rsrvtnNum,rsrvtnItmNum,rsrvtnRcrdTypCd"
      | sourceSysCd | rsrvtnNum  | rsrvtnItmNum | rsrvtnRcrdTypCd | delInd | qtyFxInd | bomItmNum | btchNum | matlNum            | leadTimeOffset | rqmtQty | wthdrnQty |
      | CONS_LATAM  | 0000000571 | 0001         |                 |        |          | 0020      | 0021    | 000000000000004771 | 0              | 15.000  | 16.000    |

    And I wait "/edm/reserv_itm" Async Queue complete


    Given I import "/edm/mfg_order_rtng" by keyFields "srcSysCd,ordrRtngNum,ordrRtngCtrNum"
      | srcSysCd   | ordrRtngNum | ordrRtngCtrNum | operNum |
      | CONS_LATAM | 0000000341  | 00000001       | 0010    |

    And I wait "/edm/mfg_order_rtng" Async Queue complete

    Given I import "/edm/mfg_order_seq" by keyFields "srcSysCd,ordrRtngNum,ordrRtngCtrNum"
      | srcSysCd   | ordrRtngNum | ordrRtngCtrNum | rtngSqncNum |
      | CONS_LATAM | 0000000341  | 0000000001     | 444         |

    And I wait "/edm/mfg_order_seq" Async Queue complete

    Given I import "/edm/bom_item" by keyFields "srcSysCd,bomCatCd,bomNum,bomItmNdeNum,bomItmCntrNbr"
      | srcSysCd   | bomCatCd | bomNum     | bomItmNdeNum | bomItmCntrNbr | dstrbtnKeyCd |
      | CONS_LATAM | stlty    | 0000000541 | 1            | 2             |              |

    And I wait "/edm/bom_item" Async Queue complete

    When I submit task with xml file "xml/OMP/OMPGdmbomelementProcess.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMBOMElement_process.tsv"

    Then I check file data for filename "GDMBOMElement_process.tsv" by keyFields "bomElementId"
#    Then I check region data "/omp/gdm_bom_element" by keyFields "bomElementId"

      | bomElementId                                   | Active | ActiveFCTERP | ActiveOPRERP | ActiveSOPERP | BatchId | BOMId                           | BOMType    | BOMUsage     | Comments | EndEff              | ERPFeedbackQuantity | LocationId      | Offset | OffsetCalendarId | OffsetPercentage | OffsetPercType | PlanLevelId | ProductId | Quantity | StartEff            |
      | PRO/CONS_LATAM/1000321/444/0010/4771/0020/0021 | YES    | YES          | YES          | NO           | 0021    | PRO/CONS_LATAM/1000321/341/0010 | batchstart | proportional |          | 31/12/2998 23:59:59 | 20849.000           | CONS_LATAM_BR01 | 0.000  |                  |                  |                | *           | LBU01     | -1.000   | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/1000321/444/0010/4771/0001/0001 | YES    | YES          | YES          | NO           | 0001    | PRO/CONS_LATAM/1000321/341/0010 | batchend   | proportional |          | 31/12/2998 23:59:59 | 16.000              | CONS_LATAM_BR01 | 0.000  |                  |                  |                | *           | LBU01     | -521.000 | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/1000321/444/0010/4772/0002/0001 | YES    | YES          | YES          | NO           | 0001    | PRO/CONS_LATAM/1000321/341/0010 | batchend   | proportional |          | 31/12/2998 23:59:59 | 16.000              | CONS_LATAM_BR01 | 0.000  |                  |                  |                | *           | LBU02     | -522.000 | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/1000321/444/0010/4773/0003/0001 | YES    | YES          | YES          | NO           | 0001    | PRO/CONS_LATAM/1000321/341/0010 | batchend   | proportional |          | 31/12/2998 23:59:59 | 16.000              | CONS_LATAM_BR01 | 0.000  |                  |                  |                | *           | LBU03     | -523.000 | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/1000321/444/0010/4773/0004/0001 | YES    | YES          | YES          | NO           | 0001    | PRO/CONS_LATAM/1000321/341/0010 | batchend   | proportional |          | 31/12/2998 23:59:59 | 16.000              | CONS_LATAM_BR01 | 0.000  |                  |                  |                | *           | LBU03     | -523.000 | 01/01/1980 00:00:00 |

  @Scenario5
  Scenario:  set productId   --(T9)


    Given I import "/edm/mfg_order" by keyFields "sourceSysCd,mfgOrdrNum"
      | sourceSysCd | mfgOrdrNum   | rsrvtnNum  | ordrRtngNum | bomCatCd | bomNum     | actRlseDt |
      | CONS_LATAM  | 000001000321 | 0000000571 | 0000000341  | stlty    | 0000000541 | 20180612  |
      | CONS_LATAM  | 000001000322 | 0000000572 | 0000000342  | stlty    | 0000000542 | 20180612  |
      | CONS_LATAM  | 000001000326 | 0000000576 | 0000000346  | stlty    | 0000000546 | 20180612  |

    And I wait "/edm/mfg_order" Async Queue complete

    Given I import "/plan/cns_plan_parameter" by keyFields "attribute,dataObject,inclExcl,parameter,parameterValue,sourceSystem"
      | attribute  | dataObject  | inclExcl | parameter | parameterValue | sourceSystem |
      | CONS_LATAM | SEND_TO_OMP | E        | MRPType   | LA             | CONS_LATAM   |

    And I wait "/plan/cns_plan_parameter" Async Queue complete


    Given I import "/edm/mfg_order_itm" by keyFields "srcSysCd,mfgOrdrNum,lnItmNbr"

      | srcSysCd   | mfgOrdrNum   | lnItmNbr | matlNum            | plntCd | prdntVrsnNum | btchNum | rcvdQty  | goodRcptLdDaysQty | itmQty  |
      | CONS_LATAM | 000001000321 | 0001     | 000000000000004771 | BR01   | V001         | 0001    | 5211.000 | 0                 | 521.000 |
      | CONS_LATAM | 000001000321 | 0002     | 000000000000003772 | BR01   | V001         | 0001    | 5212.000 | 0                 | 522.000 |
      | CONS_LATAM | 000001000321 | 0003     | 000000000000003773 | BR01   | V001         | 0001    | 5213.000 | 0                 | 523.000 |
      | CONS_LATAM | 000001000321 | 0004     | 000000000000003773 | BR01   | V001         | 0001    | 5213.000 | 0                 | 523.000 |
      | CONS_LATAM | 000001000321 | 0005     | 000000000000003775 | BR01   | V001         | 0001    | 5215.000 | 0                 | 525.000 |
      | CONS_LATAM | 000001000322 | 0001     | 000000000000004772 | BR02   | V002         | 0002    | 5214.000 | 5                 | 521.000 |
      | CONS_LATAM | 000001000326 | 0001     | 000000000000004776 | BR06   | V006         | 0006    | 5214.000 | 1                 | 521.000 |

    And I wait "/edm/mfg_order_itm" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | sourceSystem | localMaterialNumber | localDpParentCode | primaryPlanningCode |
      | CONS_LATAM   | 000000000000004771  | LDPC01            | LBU01               |
      | CONS_LATAM   | 000000000000003772  | LDPC02            | LBU02               |
      | CONS_LATAM   | 000000000000003773  | LDPC03            | LBU03               |
      | CONS_LATAM   | 000000000000004772  | LDPC02            | LBU02               |
      | CONS_LATAM   | 000000000000004776  | LDPC06            | LBU06               |


    Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | sourceSystem | localMaterialNumber | localDpParentCode | primaryPlanningCode |
      | CONS_LATAM   | 000000000000004771  | LDPC01            | LBU01               |
      | CONS_LATAM   | 000000000000003772  | LDPC02            | LBU02               |
      | CONS_LATAM   | 000000000000003773  | LDPC03            | LBU03               |
      | CONS_LATAM   | 000000000000004772  | LDPC02            | LBU02               |
      | CONS_LATAM   | 000000000000004776  | LDPC06            | LBU06               |

    And I wait "/edm/material_global_v1" Async Queue complete


    Given I import "/plan/cns_material_plan_status" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | spRelevant | noPlanRelevant | dpRelevant |
      | CONS_LATAM   | 000000000000004771  | BR01       |            |                |            |
      | CONS_LATAM   | 000000000000003772  | BR01       | X          | X              | X          |
      | CONS_LATAM   | 000000000000004772  | BR02       |            | X              |            |
      | CONS_LATAM   | 000000000000004776  | BR06       |            |                | X          |
      | CONS_LATAM   | 000000000000003773  | BR01       | X          |                |            |

    And I wait "/plan/cns_material_plan_status" Async Queue complete


    Given I import "/edm/matl_prod_versn" by keyFields "srcSysCd,matlNum,plntCd,prdntVrsnNum"
      | srcSysCd   | matlNum            | plntCd | prdntVrsnNum | dstrbtnKeyCd |
      | CONS_LATAM | 000000000000004771 | BR01   | V001         |              |
      | CONS_LATAM | 000000000000003772 | BR01   | V001         |              |
      | CONS_LATAM | 000000000000003773 | BR01   | V001         |              |
      | CONS_LATAM | 000000000000004772 | BR02   | V002         | GLEI         |
      | CONS_LATAM | 000000000000004776 | BR06   | V006         | GL           |

    And I wait "/edm/matl_prod_versn" Async Queue complete


    Given I import "/edm/reserv_itm" by keyFields "sourceSysCd,rsrvtnNum,rsrvtnItmNum,rsrvtnRcrdTypCd"
      | sourceSysCd | rsrvtnNum  | rsrvtnItmNum | rsrvtnRcrdTypCd | delInd | qtyFxInd | bomItmNum | btchNum | matlNum            | leadTimeOffset | rqmtQty | wthdrnQty |
      | CONS_LATAM  | 0000000571 | 0001         |                 |        |          | 0020      | 0021    | 000000000000004771 | 0              | 15.000  | 16.000    |
      | CONS_LATAM  | 0000000572 | 0002         |                 |        | X        | 0020      | 0022    | 000000000000004772 | 10             | 15.000  | 14.000    |
      | CONS_LATAM  | 0000000576 | 0006         |                 |        | A        | 0020      | 0026    | 000000000000004776 | 1              | 15.000  | 15.000    |
      | CONS_LATAM  | 0000000576 | 0031         |                 |        | A        | 0020      | 0031    | 000000000000004776 | 1              | 15.000  | 15.000    |

    And I wait "/edm/reserv_itm" Async Queue complete


    Given I import "/edm/mfg_order_rtng" by keyFields "srcSysCd,ordrRtngNum,ordrRtngCtrNum"
      | srcSysCd   | ordrRtngNum | ordrRtngCtrNum | operNum |
      | CONS_LATAM | 0000000341  | 00000001       | 0010    |
      | CONS_LATAM | 0000000342  | 00000002       | 0010    |
      | CONS_LATAM | 0000000346  | 00000006       | 0010    |

    And I wait "/edm/mfg_order_rtng" Async Queue complete

    Given I import "/edm/mfg_order_seq" by keyFields "srcSysCd,ordrRtngNum,ordrRtngCtrNum"
      | srcSysCd   | ordrRtngNum | ordrRtngCtrNum | rtngSqncNum |
      | CONS_LATAM | 0000000341  | 0000000001     | 444         |
      | CONS_LATAM | 0000000342  | 0000000002     | 444         |
      | CONS_LATAM | 0000000346  | 0000000006     | 444         |

    And I wait "/edm/mfg_order_seq" Async Queue complete

    Given I import "/edm/bom_item" by keyFields "srcSysCd,bomCatCd,bomNum,bomItmNdeNum,bomItmCntrNbr"
      | srcSysCd   | bomCatCd | bomNum     | bomItmNdeNum | bomItmCntrNbr | dstrbtnKeyCd |
      | CONS_LATAM | stlty    | 0000000541 | 1            | 2             |              |
      | CONS_LATAM | stlty    | 0000000542 | 1            | 2             | GLEI         |
      | CONS_LATAM | stlty    | 0000000546 | 1            | 2             | GL           |

    And I wait "/edm/bom_item" Async Queue complete
   # When I submit test local with xml file "xml/OMP/OMPGdmbomelementProcess.xml"
    When I submit task with xml file "xml/OMP/OMPGdmbomelementProcess.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMBOMElement_process.tsv"

    Then I check file data for filename "GDMBOMElement_process.tsv" by keyFields "bomElementId"
#    Then I check region data "/omp/gdm_bom_element" by keyFields "bomElementId"

      | bomElementId                                                | Active | ActiveFCTERP | ActiveOPRERP | ActiveSOPERP | BatchId | BOMId                           | BOMType  | BOMUsage     | Comments | EndEff              | ERPFeedbackQuantity | LocationId      | Offset      | OffsetCalendarId | OffsetPercentage | OffsetPercType | PlanLevelId        | ProductId | Quantity | StartEff            |
      | PRO/CONS_LATAM/1000321/444/0010/3772/0002/0001              | YES    | YES          | YES          | NO           | 0001    | PRO/CONS_LATAM/1000321/341/0010 | batchend | proportional |          | 31/12/2998 23:59:59 | 16.000              | CONS_LATAM_BR01 | 0.000       |                  |                  |                | *                  | LBU02     | -522.000 | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/1000326/444/0010/4776/0020/0026              | YES    | YES          | YES          | NO           | 0026    | PRO/CONS_LATAM/1000326/346/0010 |          |              |          | 31/12/2998 23:59:59 | 5214.000            | CONS_LATAM_BR06 | 864000.000  |                  |                  |                |                    | LA_LDPC06 | 0.000    | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/1000326/444/0010/4776/0020/0031              | YES    | YES          | YES          | NO           | 0031    | PRO/CONS_LATAM/1000326/346/0010 |          |              |          | 31/12/2998 23:59:59 | 5214.000            | CONS_LATAM_BR06 | 864000.000  |                  |                  |                |                    | LA_LDPC06 | 0.000    | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/1000326/444/0010/4776/0001/0006              | YES    | YES          | YES          | NO           | 0006    | PRO/CONS_LATAM/1000326/346/0010 |          | proportional |          | 31/12/2998 23:59:59 | 15.000              | CONS_LATAM_BR06 | 864000.000  |                  |                  |                | *                  | LA_LDPC06 | -521.000 | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/1000321/444/0010/3773/0003/0001              | YES    | YES          | YES          | NO           | 0001    | PRO/CONS_LATAM/1000321/341/0010 | batchend | proportional |          | 31/12/2998 23:59:59 | 16.000              | CONS_LATAM_BR01 | 0.000       |                  |                  |                | *                  | LBU03     | -523.000 | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/1000321/444/0010/3773/0004/0001              | YES    | YES          | YES          | NO           | 0001    | PRO/CONS_LATAM/1000321/341/0010 | batchend | proportional |          | 31/12/2998 23:59:59 | 16.000              | CONS_LATAM_BR01 | 0.000       |                  |                  |                | *                  | LBU03     | -523.000 | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/1000322/444/0010/4772/0020/0022              | YES    | YES          | YES          | NO           | 0022    | PRO/CONS_LATAM/1000322/342/0010 | continu  | fixed        |          | 31/12/2998 23:59:59 | 5214.000            | CONS_LATAM_BR02 | 8640000.000 |                  |                  |                | DetailedScheduling | LBU02     | 1.000    | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/1000322/444/0010/4772/0020/0022/proportional | YES    | YES          | YES          | NO           | 0022    | PRO/CONS_LATAM/1000322/342/0010 | continu  | fixed        |          | 31/12/2998 23:59:59 | 5214.000            | CONS_LATAM_BR02 | 8640000.000 |                  |                  |                | VolumePlanning     | LBU02     | 1.000    | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/1000322/444/0010/4772/0001/0002              | YES    | YES          | YES          | NO           | 0002    | PRO/CONS_LATAM/1000322/342/0010 | continu  | proportional |          | 31/12/2998 23:59:59 | 14.000              | CONS_LATAM_BR02 | 4320000.000 |                  |                  |                | *                  | LBU02     | -521.000 | 01/01/1980 00:00:00 |

    Then I check region data "/plan/edm_failed_data" by keyFields "errorCode,functionalArea,interfaceID,key1,key2,key3,key4,key5,sourceSystem"

      | errorCode | functionalArea | interfaceID             | key1         | key2               | key3 | key4 | key5 | sourceSystem | errorValue                    |
      | J1        | PP             | OMPGdmbomelementProcess | 000001000321 | 000000000000004771 |      |      |      | CONS_LATAM   | Unable to construct ProductId |

  @Scenario6
  Scenario:  full load curation

    Given I import "/edm/mfg_order" by keyFields "sourceSysCd,mfgOrdrNum"
      | sourceSysCd | mfgOrdrNum   | rsrvtnNum  | ordrRtngNum | bomCatCd | bomNum     | actRlseDt |
      | CONS_LATAM  | 000001000321 | 0000000571 | 0000000341  | stlty    | 0000000541 | 20180612  |
      | CONS_LATAM  | 000001000322 | 0000000572 | 0000000342  | stlty    | 0000000542 | 20180612  |
      | CONS_LATAM  | 000001000326 | 0000000576 | 0000000346  | stlty    | 0000000546 | 20180612  |

    And I wait "/edm/mfg_order" Async Queue complete

    Given I import "/plan/cns_plan_parameter" by keyFields "attribute,dataObject,inclExcl,parameter,parameterValue,sourceSystem"
      | attribute  | dataObject  | inclExcl | parameter | parameterValue | sourceSystem |
      | CONS_LATAM | SEND_TO_OMP | E        | MRPType   | LA             | CONS_LATAM   |

    And I wait "/plan/cns_plan_parameter" Async Queue complete


    Given I import "/edm/mfg_order_itm" by keyFields "srcSysCd,mfgOrdrNum,lnItmNbr"

      | srcSysCd   | mfgOrdrNum   | lnItmNbr | matlNum            | plntCd | prdntVrsnNum | btchNum | rcvdQty  | goodRcptLdDaysQty | itmQty  |
      | CONS_LATAM | 000001000321 | 0001     | 000000000000004771 | BR01   | V001         | 0001    | 5211.000 | 0                 | 521.000 |
      | CONS_LATAM | 000001000321 | 0002     | 000000000000003772 | BR01   | V001         | 0001    | 5212.000 | 0                 | 522.000 |
      | CONS_LATAM | 000001000321 | 0003     | 000000000000003773 | BR01   | V001         | 0001    | 5213.000 | 0                 | 523.000 |
      | CONS_LATAM | 000001000321 | 0004     | 000000000000003773 | BR01   | V001         | 0001    | 5213.000 | 0                 | 523.000 |
      | CONS_LATAM | 000001000321 | 0005     | 000000000000003775 | BR01   | V001         | 0001    | 5215.000 | 0                 | 525.000 |
      | CONS_LATAM | 000001000322 | 0001     | 000000000000004772 | BR02   | V002         | 0002    | 5214.000 | 5                 | 521.000 |
      | CONS_LATAM | 000001000326 | 0001     | 000000000000004776 | BR06   | V006         | 0006    | 5214.000 | 1                 | 521.000 |

    And I wait "/edm/mfg_order_itm" Async Queue complete


    Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | sourceSystem | localMaterialNumber | localDpParentCode | primaryPlanningCode |
      | CONS_LATAM   | 000000000000004771  | LDPC01            | LBU01               |
      | CONS_LATAM   | 000000000000003772  | LDPC02            | LBU02               |
      | CONS_LATAM   | 000000000000003773  | LDPC03            | LBU03               |
      | CONS_LATAM   | 000000000000004772  | LDPC02            | LBU02               |
      | CONS_LATAM   | 000000000000004776  | LDPC06            | LBU06               |

    And I wait "/edm/material_global_v1" Async Queue complete


    Given I import "/plan/cns_material_plan_status" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | spRelevant | noPlanRelevant | dpRelevant |
      | CONS_LATAM   | 000000000000004771  | BR01       | X          | X              | X          |
      | CONS_LATAM   | 000000000000003772  | BR01       | X          | X              | X          |
      | CONS_LATAM   | 000000000000003773  | BR01       | X          | X              | X          |
      | CONS_LATAM   | 000000000000004772  | BR02       | X          | X              | X          |
      | CONS_LATAM   | 000000000000004776  | BR06       | X          | X              | X          |

    And I wait "/plan/cns_material_plan_status" Async Queue complete

    Given I import "/edm/matl_prod_versn" by keyFields "srcSysCd,matlNum,plntCd,prdntVrsnNum"
      | srcSysCd   | matlNum            | plntCd | prdntVrsnNum | dstrbtnKeyCd |
      | CONS_LATAM | 000000000000004771 | BR01   | V001         |              |
      | CONS_LATAM | 000000000000003772 | BR01   | V001         |              |
      | CONS_LATAM | 000000000000003773 | BR01   | V001         |              |
      | CONS_LATAM | 000000000000004772 | BR02   | V002         | GLEI         |
      | CONS_LATAM | 000000000000004776 | BR06   | V006         | GL           |

    And I wait "/edm/matl_prod_versn" Async Queue complete


    Given I import "/edm/reserv_itm" by keyFields "sourceSysCd,rsrvtnNum,rsrvtnItmNum,rsrvtnRcrdTypCd"
      | sourceSysCd | rsrvtnNum  | rsrvtnItmNum | rsrvtnRcrdTypCd | delInd | qtyFxInd | bomItmNum | btchNum | matlNum            | leadTimeOffset | rqmtQty | wthdrnQty |
      | CONS_LATAM  | 0000000571 | 0001         |                 |        |          | 0020      | 0021    | 000000000000004771 | 0              | 15.000  | 16.000    |
      | CONS_LATAM  | 0000000572 | 0002         |                 |        | X        | 0020      | 0022    | 000000000000004772 | 10             | 15.000  | 14.000    |
      | CONS_LATAM  | 0000000576 | 0006         |                 |        | A        | 0020      | 0026    | 000000000000004776 | 1              | 15.000  | 15.000    |
      | CONS_LATAM  | 0000000576 | 0031         |                 |        | A        | 0020      | 0031    | 000000000000004776 | 1              | 15.000  | 15.000    |

    And I wait "/edm/reserv_itm" Async Queue complete


    Given I import "/edm/mfg_order_rtng" by keyFields "srcSysCd,ordrRtngNum,ordrRtngCtrNum"
      | srcSysCd   | ordrRtngNum | ordrRtngCtrNum | operNum |
      | CONS_LATAM | 0000000341  | 00000001       | 0010    |
      | CONS_LATAM | 0000000342  | 00000002       | 0010    |
      | CONS_LATAM | 0000000346  | 00000006       | 0010    |

    And I wait "/edm/mfg_order_rtng" Async Queue complete

    Given I import "/edm/mfg_order_seq" by keyFields "srcSysCd,ordrRtngNum,ordrRtngCtrNum"
      | srcSysCd   | ordrRtngNum | ordrRtngCtrNum | rtngSqncNum |
      | CONS_LATAM | 0000000341  | 0000000001     | 444         |
      | CONS_LATAM | 0000000342  | 0000000002     | 444         |
      | CONS_LATAM | 0000000346  | 0000000006     | 444         |

    And I wait "/edm/mfg_order_seq" Async Queue complete

    Given I import "/edm/bom_item" by keyFields "srcSysCd,bomCatCd,bomNum,bomItmNdeNum,bomItmCntrNbr"
      | srcSysCd   | bomCatCd | bomNum     | bomItmNdeNum | bomItmCntrNbr | dstrbtnKeyCd |
      | CONS_LATAM | stlty    | 0000000541 | 1            | 2             |              |
      | CONS_LATAM | stlty    | 0000000542 | 1            | 2             | GLEI         |
      | CONS_LATAM | stlty    | 0000000546 | 1            | 2             | GL           |

    And I wait "/edm/bom_item" Async Queue complete

    When I submit task with xml file "xml/OMP/OMPGdmbomelementProcess.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMBOMElement_process.tsv"

    Then I check file data for filename "GDMBOMElement_process.tsv" by keyFields "bomElementId"
#    Then I check region data "/omp/gdm_bom_element" by keyFields "bomElementId"

      | bomElementId                                                | Active | ActiveFCTERP | ActiveOPRERP | ActiveSOPERP | BatchId | BOMId                           | BOMType    | BOMUsage     | Comments | EndEff              | ERPFeedbackQuantity | LocationId      | Offset      | OffsetCalendarId | OffsetPercentage | OffsetPercType | PlanLevelId        | ProductId | Quantity | StartEff            |
      | PRO/CONS_LATAM/1000321/444/0010/4771/0020/0021              | YES    | YES          | YES          | NO           | 0021    | PRO/CONS_LATAM/1000321/341/0010 | batchstart | proportional |          | 31/12/2998 23:59:59 | 20849.000           | CONS_LATAM_BR01 | 0.000       |                  |                  |                | *                  | LBU01     | -1.000   | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/1000321/444/0010/4771/0001/0001              | YES    | YES          | YES          | NO           | 0001    | PRO/CONS_LATAM/1000321/341/0010 | batchend   | proportional |          | 31/12/2998 23:59:59 | 16.000              | CONS_LATAM_BR01 | 0.000       |                  |                  |                | *                  | LBU01     | -521.000 | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/1000321/444/0010/3772/0002/0001              | YES    | YES          | YES          | NO           | 0001    | PRO/CONS_LATAM/1000321/341/0010 | batchend   | proportional |          | 31/12/2998 23:59:59 | 16.000              | CONS_LATAM_BR01 | 0.000       |                  |                  |                | *                  | LBU02     | -522.000 | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/1000321/444/0010/3773/0003/0001              | YES    | YES          | YES          | NO           | 0001    | PRO/CONS_LATAM/1000321/341/0010 | batchend   | proportional |          | 31/12/2998 23:59:59 | 16.000              | CONS_LATAM_BR01 | 0.000       |                  |                  |                | *                  | LBU03     | -523.000 | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/1000321/444/0010/3773/0004/0001              | YES    | YES          | YES          | NO           | 0001    | PRO/CONS_LATAM/1000321/341/0010 | batchend   | proportional |          | 31/12/2998 23:59:59 | 16.000              | CONS_LATAM_BR01 | 0.000       |                  |                  |                | *                  | LBU03     | -523.000 | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/1000322/444/0010/4772/0020/0022              | YES    | YES          | YES          | NO           | 0022    | PRO/CONS_LATAM/1000322/342/0010 | continu    | fixed        |          | 31/12/2998 23:59:59 | 5214.000            | CONS_LATAM_BR02 | 8640000.000 |                  |                  |                | DetailedScheduling | LBU02     | 1.000    | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/1000322/444/0010/4772/0020/0022/proportional | YES    | YES          | YES          | NO           | 0022    | PRO/CONS_LATAM/1000322/342/0010 | continu    | fixed        |          | 31/12/2998 23:59:59 | 5214.000            | CONS_LATAM_BR02 | 8640000.000 |                  |                  |                | VolumePlanning     | LBU02     | 1.000    | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/1000322/444/0010/4772/0001/0002              | YES    | YES          | YES          | NO           | 0002    | PRO/CONS_LATAM/1000322/342/0010 | continu    | proportional |          | 31/12/2998 23:59:59 | 14.000              | CONS_LATAM_BR02 | 4320000.000 |                  |                  |                | *                  | LBU02     | -521.000 | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/1000326/444/0010/4776/0020/0026              | YES    | YES          | YES          | NO           | 0026    | PRO/CONS_LATAM/1000326/346/0010 |            |              |          | 31/12/2998 23:59:59 | 5214.000            | CONS_LATAM_BR06 | 864000.000  |                  |                  |                |                    | LBU06     | 0.000    | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/1000326/444/0010/4776/0020/0031              | YES    | YES          | YES          | NO           | 0031    | PRO/CONS_LATAM/1000326/346/0010 |            |              |          | 31/12/2998 23:59:59 | 5214.000            | CONS_LATAM_BR06 | 864000.000  |                  |                  |                |                    | LBU06     | 0.000    | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/1000326/444/0010/4776/0001/0006              | YES    | YES          | YES          | NO           | 0006    | PRO/CONS_LATAM/1000326/346/0010 |            | proportional |          | 31/12/2998 23:59:59 | 15.000              | CONS_LATAM_BR06 | 864000.000  |                  |                  |                | *                  | LBU06     | -521.000 | 01/01/1980 00:00:00 |

