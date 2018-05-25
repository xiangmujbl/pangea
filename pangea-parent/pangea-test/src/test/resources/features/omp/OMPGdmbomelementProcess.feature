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

    And I will remove all data with region "/omp/gdm_bom_element"

  @Scenario1
  Scenario:  JOIN MFG_ORDER, MFG_ORDER_ITM, MFG_ORDER_RTNG, MFG_ORDER_SEQ  If no records found, skip ,if multiple records found, use all of them for output  --(J1)


    Given I import "/edm/mfg_order" by keyFields "sourceSysCd,mfgOrdrNum"
      | sourceSysCd   | mfgOrdrNum   | rsrvtnNum  | ordrRtngNum | bomCatCd | bomNum     | actRlseDt |
      # actRlseDt Between current date and current date - 1
      | CONS_LATAM    | 000001000320 | 0000000570 | 0000000340  | stlty    | 0000000540 | 20180520  |
      | CONS_LATAM    | 000001000321 | 0000000571 | 0000000341  | stlty    | 0000000541 | 20180524  |
      | CONS_LATAM    | 000001000322 | 0000000572 | 0000000342  | stlty    | 0000000542 | 20180524  |
      | CONS_LATAM    | 000001000323 | 0000000573 | 0000000343  | stlty    | 0000000543 | 20180524  |
      | CONS_LATAM    | 000001000324 | 0000000574 | 0000000344  | stlty    | 0000000544 | 20180524  |
      | CONS_LATAM    | 000001000325 | 0000000575 | 0000000345  | stlty    | 0000000545 | 20180524  |
      | CONS_LATAM    | 000001000326 | 0000000576 | 0000000346  | stlty    | 0000000546 | 20180524  |
      #sourceSysCd not equals /plan/cns_plan_parameter.attribute(CONS_LATAM-J1!=CONS_LATAM)
      | CONS_LATAM-J1 | 000001000327 | 0000000577 | 0000000347  | stlty    | 0000000547 | 20180524  |

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

    And I wait "/edm/mfg_order_seq" Async Queue complete



    Given I import "/edm/bom_item" by keyFields "srcSysCd,bomCatCd,bomNum,bomItmNdeNum,bomItmCntrNbr"
      | srcSysCd   | bomCatCd | bomNum     | bomItmNdeNum | bomItmCntrNbr | dstrbtnKeyCd |
      | CONS_LATAM | stlty    | 0000000546 | 1            | 2             |              |

    And I wait "/edm/bom_item" Async Queue complete


    When I submit task with xml file "xml/OMP/OMPGdmbomelementProcess.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/omp/gdm_bom_element" by keyFields "BOMElementId"
      | BOMElementId                                                      | Active | ActiveFCTERP | ActiveOPRERP | ActiveSOPERP | BatchId | BOMId                               | BOMType    | BOMUsage     | Comments | EndEff              | ERPFeedbackQuantity | LocationId      | Offset     | OffsetCalendarId | OffsetPercentage | OffsetPercType | PlanLevelId | ProductId             | Quantity | StartEff            |
      | PRO/CONS_LATAM/000001000326/444/0010/000000000000004776/0020/0026 | YES    | YES          | YES          | NO           | 0026    | PRO/LA_000001000326/0000000346/0010 | batchstart | proportional |          | 31/12/2998 23:59:59 | 5214.000            | CONS_LATAM_BR06 | 864000.000 |                  |                  |                | *           | LA_000000000000004776 | 0.000    | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/000001000326/444/0010/000000000000004776/0001/0006 | YES    | YES          | YES          | NO           | 0006    | PRO/LA_000001000326/0000000346/0010 | batchend   | proportional |          | 31/12/2998 23:59:59 | 15.000              | CONS_LATAM_BR06 | 864000.000 |                  |                  |                | *           | LA_000000000000004776 | 521.000  | 01/01/1980 00:00:00 |


  @Scenario2
  Scenario:  set the BOMType value  according to  BOM_ITEM-dstrbtnKeyCd or MATL_PRD_VERSN-dstrbtnKeyCd   --(T6)
  AND  set  Offset RESERV_ITM- leadTimeOffset * 864000 or "FOR PRODUCED MAT" MFG_ORDER_ITM -goodRcptLdDaysQty  *864000 --(T11)

    Given I import "/edm/mfg_order" by keyFields "sourceSysCd,mfgOrdrNum"
      | sourceSysCd | mfgOrdrNum   | rsrvtnNum  | ordrRtngNum | bomCatCd | bomNum     | actRlseDt |
      | CONS_LATAM  | 000001000321 | 0000000571 | 0000000341  | stlty    | 0000000541 | 20180524  |
      | CONS_LATAM  | 000001000322 | 0000000572 | 0000000342  | stlty    | 0000000542 | 20180524  |
      | CONS_LATAM  | 000001000326 | 0000000576 | 0000000346  | stlty    | 0000000546 | 20180524  |

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

    And I wait "/edm/bom_item" Async Queue complete


    When I submit task with xml file "xml/OMP/OMPGdmbomelementProcess.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/omp/gdm_bom_element" by keyFields "BOMElementId"
      | BOMElementId                                                      | Active | ActiveFCTERP | ActiveOPRERP | ActiveSOPERP | BatchId | BOMId                               | BOMType    | BOMUsage     | Comments | EndEff              | ERPFeedbackQuantity | LocationId      | Offset      | OffsetCalendarId | OffsetPercentage | OffsetPercType | PlanLevelId | ProductId             | Quantity | StartEff            |
      | PRO/CONS_LATAM/000001000321/444/0010/000000000000004771/0020/0021 | YES    | YES          | YES          | NO           | 0021    | PRO/LA_000001000321/0000000341/0010 | batchstart | proportional |          | 31/12/2998 23:59:59 | 5214.000            | CONS_LATAM_BR01 | 0.000       |                  |                  |                | *           | LA_000000000000004771 | 0.000    | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/000001000321/444/0010/000000000000004771/0001/0001 | YES    | YES          | YES          | NO           | 0001    | PRO/LA_000001000321/0000000341/0010 | batchend   | proportional |          | 31/12/2998 23:59:59 | 15.000              | CONS_LATAM_BR01 | 0.000       |                  |                  |                | *           | LA_000000000000004771 | 521.000  | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/000001000322/444/0010/000000000000004772/0020/0022 | YES    | YES          | YES          | NO           | 0022    | PRO/LA_000001000322/0000000342/0010 | continu    | proportional |          | 31/12/2998 23:59:59 | 5214.000            | CONS_LATAM_BR02 | 8640000.000 |                  |                  |                | *           | LA_000000000000004772 | 0.000    | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/000001000322/444/0010/000000000000004772/0001/0002 | YES    | YES          | YES          | NO           | 0002    | PRO/LA_000001000322/0000000342/0010 | continu    | proportional |          | 31/12/2998 23:59:59 | 15.000              | CONS_LATAM_BR02 | 4320000.000 |                  |                  |                | *           | LA_000000000000004772 | 521.000  | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/000001000326/444/0010/000000000000004776/0020/0026 | YES    | YES          | YES          | NO           | 0026    | PRO/LA_000001000326/0000000346/0010 |            | proportional |          | 31/12/2998 23:59:59 | 5214.000            | CONS_LATAM_BR06 | 864000.000  |                  |                  |                | *           | LA_000000000000004776 | 0.000    | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/000001000326/444/0010/000000000000004776/0001/0006 | YES    | YES          | YES          | NO           | 0006    | PRO/LA_000001000326/0000000346/0010 |            | proportional |          | 31/12/2998 23:59:59 | 15.000              | CONS_LATAM_BR06 | 864000.000  |                  |                  |                | *           | LA_000000000000004776 | 521.000  | 01/01/1980 00:00:00 |

  @Scenario3
  Scenario:set the BOMUsage value  according to  RESERV_ITM-qtyFxInd  or "FOR PRODUCED MAT" default to "proportional" --(T7)
  AND set the PlanLevelId value  according to  RESERV_ITM-qtyFxInd  or  "FOR PRODUCED MAT" default to "*"  --(T12)
  AND set the Quantity =  RESERV_ITM-rqmtQty   - RESERV_ITM-wthdrnQty  or  "FOR PRODUCED MAT" Quantity =  MFG_ORDER_ITM- itmQty --(T15)

    Given I import "/edm/mfg_order" by keyFields "sourceSysCd,mfgOrdrNum"
      | sourceSysCd | mfgOrdrNum   | rsrvtnNum  | ordrRtngNum | bomCatCd | bomNum     | actRlseDt |
      | CONS_LATAM  | 000001000321 | 0000000571 | 0000000341  | stlty    | 0000000541 | 20180524  |
      | CONS_LATAM  | 000001000322 | 0000000572 | 0000000342  | stlty    | 0000000542 | 20180524  |
      | CONS_LATAM  | 000001000326 | 0000000576 | 0000000346  | stlty    | 0000000546 | 20180524  |

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

    Then I check region data "/omp/gdm_bom_element" by keyFields "BOMElementId"
      | BOMElementId                                                                   | Active | ActiveFCTERP | ActiveOPRERP | ActiveSOPERP | BatchId | BOMId                               | BOMType    | BOMUsage     | Comments | EndEff              | ERPFeedbackQuantity | LocationId      | Offset     | OffsetCalendarId | OffsetPercentage | OffsetPercType | PlanLevelId        | ProductId             | Quantity | StartEff            |
      | PRO/CONS_LATAM/000001000321/444/0010/000000000000004771/0020/0021              | YES    | YES          | YES          | NO           | 0021    | PRO/LA_000001000321/0000000341/0010 | batchstart | proportional |          | 31/12/2998 23:59:59 | 5214.000            | CONS_LATAM_BR01 | 0.000      |                  |                  |                | *                  | LA_000000000000004771 | -1.000   | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/000001000321/444/0010/000000000000004771/0001/0001              | YES    | YES          | YES          | NO           | 0001    | PRO/LA_000001000321/0000000341/0010 | batchend   | proportional |          | 31/12/2998 23:59:59 | 16.000              | CONS_LATAM_BR01 | 0.000      |                  |                  |                | *                  | LA_000000000000004771 | 521.000  | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/000001000322/444/0010/000000000000004772/0020/0022              | YES    | YES          | YES          | NO           | 0022    | PRO/LA_000001000322/0000000342/0010 | batchstart | fixed        |          | 31/12/2998 23:59:59 | 5214.000            | CONS_LATAM_BR02 | 0.000      |                  |                  |                | DetailedScheduling | LA_000000000000004772 | 1.000    | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/000001000322/444/0010/000000000000004772/0020/0022/proportional | YES    | YES          | YES          | NO           | 0022    | PRO/LA_000001000322/0000000342/0010 | batchstart | fixed        |          | 31/12/2998 23:59:59 | 5214.000            | CONS_LATAM_BR02 | 0.000      |                  |                  |                | VolumePlanning     | LA_000000000000004772 | 1.000    | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/000001000322/444/0010/000000000000004772/0001/0002              | YES    | YES          | YES          | NO           | 0002    | PRO/LA_000001000322/0000000342/0010 | batchend   | proportional |          | 31/12/2998 23:59:59 | 14.000              | CONS_LATAM_BR02 | 0.000      |                  |                  |                | *                  | LA_000000000000004772 | 521.000  | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/000001000326/444/0010/000000000000004776/0020/0026              | YES    | YES          | YES          | NO           | 0026    | PRO/LA_000001000326/0000000346/0010 | batchstart |              |          | 31/12/2998 23:59:59 | 5214.000            | CONS_LATAM_BR06 | 864000.000 |                  |                  |                |                    | LA_000000000000004776 | 0.000    | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/000001000326/444/0010/000000000000004776/0001/0006              | YES    | YES          | YES          | NO           | 0006    | PRO/LA_000001000326/0000000346/0010 | batchend   | proportional |          | 31/12/2998 23:59:59 | 15.000              | CONS_LATAM_BR06 | 864000.000 |                  |                  |                | *                  | LA_000000000000004776 | 521.000  | 01/01/1980 00:00:00 |


  @Scenario4
  Scenario:  set ERPFeedbackQuantity by Suming  the MFG_ORDER_ITM-rcvdQty  for each mfgOrdrNum and matlNum  or "FOR PRODUCED MAT" RESERV_ITM – wthdrnQty --(T9)

    Given I import "/edm/mfg_order" by keyFields "sourceSysCd,mfgOrdrNum"
      | sourceSysCd | mfgOrdrNum   | rsrvtnNum  | ordrRtngNum | bomCatCd | bomNum     | actRlseDt |
      | CONS_LATAM  | 000001000321 | 0000000571 | 0000000341  | stlty    | 0000000541 | 20180524  |

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

    Then I check region data "/omp/gdm_bom_element" by keyFields "BOMElementId"
      | BOMElementId                                                      | Active | ActiveFCTERP | ActiveOPRERP | ActiveSOPERP | BatchId | BOMId                               | BOMType    | BOMUsage     | Comments | EndEff              | ERPFeedbackQuantity | LocationId      | Offset | OffsetCalendarId | OffsetPercentage | OffsetPercType | PlanLevelId | ProductId             | Quantity | StartEff            |
      | PRO/CONS_LATAM/000001000321/444/0010/000000000000004771/0020/0021 | YES    | YES          | YES          | NO           | 0021    | PRO/LA_000001000321/0000000341/0010 | batchstart | proportional |          | 31/12/2998 23:59:59 | 20849.000           | CONS_LATAM_BR01 | 0.000  |                  |                  |                | *           | LA_000000000000004771 | -1.000   | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/000001000321/444/0010/000000000000004771/0001/0001 | YES    | YES          | YES          | NO           | 0001    | PRO/LA_000001000321/0000000341/0010 | batchend   | proportional |          | 31/12/2998 23:59:59 | 16.000              | CONS_LATAM_BR01 | 0.000  |                  |                  |                | *           | LA_000000000000004771 | 521.000  | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/000001000321/444/0010/000000000000004772/0002/0001 | YES    | YES          | YES          | NO           | 0001    | PRO/LA_000001000321/0000000341/0010 | batchend   | proportional |          | 31/12/2998 23:59:59 | 16.000              | CONS_LATAM_BR01 | 0.000  |                  |                  |                | *           | LA_000000000000004772 | 522.000  | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/000001000321/444/0010/000000000000004773/0003/0001 | YES    | YES          | YES          | NO           | 0001    | PRO/LA_000001000321/0000000341/0010 | batchend   | proportional |          | 31/12/2998 23:59:59 | 16.000              | CONS_LATAM_BR01 | 0.000  |                  |                  |                | *           | LA_000000000000004773 | 523.000  | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/000001000321/444/0010/000000000000004773/0004/0001 | YES    | YES          | YES          | NO           | 0001    | PRO/LA_000001000321/0000000341/0010 | batchend   | proportional |          | 31/12/2998 23:59:59 | 16.000              | CONS_LATAM_BR01 | 0.000  |                  |                  |                | *           | LA_000000000000004773 | 523.000  | 01/01/1980 00:00:00 |


  @Scenario5
  Scenario:  full load curation

    Given I import "/edm/mfg_order" by keyFields "sourceSysCd,mfgOrdrNum"
      | sourceSysCd | mfgOrdrNum   | rsrvtnNum  | ordrRtngNum | bomCatCd | bomNum     | actRlseDt |
      | CONS_LATAM  | 000001000321 | 0000000571 | 0000000341  | stlty    | 0000000541 | 20180524  |
      | CONS_LATAM  | 000001000322 | 0000000572 | 0000000342  | stlty    | 0000000542 | 20180524  |
      | CONS_LATAM  | 000001000326 | 0000000576 | 0000000346  | stlty    | 0000000546 | 20180524  |

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

    Then I check region data "/omp/gdm_bom_element" by keyFields "BOMElementId"
      | BOMElementId                                                                   | Active | ActiveFCTERP | ActiveOPRERP | ActiveSOPERP | BatchId | BOMId                               | BOMType | BOMUsage     | Comments | EndEff              | ERPFeedbackQuantity | LocationId      | Offset      | OffsetCalendarId | OffsetPercentage | OffsetPercType | PlanLevelId        | ProductId             | Quantity | StartEff            |
      | PRO/CONS_LATAM/000001000321/444/0010/000000000000004771/0020/0021              | YES    | YES          | YES          | NO           | 0021    | PRO/LA_000001000321/0000000341/0010 | batchstart | proportional |          | 31/12/2998 23:59:59 | 20849.000           | CONS_LATAM_BR01 | 0.000       |                  |                  |                | *                  | LA_000000000000004771 | -1.000   | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/000001000321/444/0010/000000000000004771/0001/0001              | YES    | YES          | YES          | NO           | 0001    | PRO/LA_000001000321/0000000341/0010 | batchend   | proportional |          | 31/12/2998 23:59:59 | 16.000              | CONS_LATAM_BR01 | 0.000       |                  |                  |                | *                  | LA_000000000000004771 | 521.000  | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/000001000321/444/0010/000000000000003772/0002/0001              | YES    | YES          | YES          | NO           | 0001    | PRO/LA_000001000321/0000000341/0010 | batchend   | proportional |          | 31/12/2998 23:59:59 | 16.000              | CONS_LATAM_BR01 | 0.000       |                  |                  |                | *                  | LA_000000000000003772 | 522.000  | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/000001000321/444/0010/000000000000003773/0003/0001              | YES    | YES          | YES          | NO           | 0001    | PRO/LA_000001000321/0000000341/0010 | batchend   | proportional |          | 31/12/2998 23:59:59 | 16.000              | CONS_LATAM_BR01 | 0.000       |                  |                  |                | *                  | LA_000000000000003773 | 523.000  | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/000001000321/444/0010/000000000000003773/0004/0001              | YES    | YES          | YES          | NO           | 0001    | PRO/LA_000001000321/0000000341/0010 | batchend   | proportional |          | 31/12/2998 23:59:59 | 16.000              | CONS_LATAM_BR01 | 0.000       |                  |                  |                | *                  | LA_000000000000003773 | 523.000  | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/000001000322/444/0010/000000000000004772/0020/0022              | YES    | YES          | YES          | NO           | 0022    | PRO/LA_000001000322/0000000342/0010 | continu | fixed        |          | 31/12/2998 23:59:59 | 5214.000            | CONS_LATAM_BR02 | 8640000.000 |                  |                  |                | DetailedScheduling | LA_000000000000004772 | 1.000    | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/000001000322/444/0010/000000000000004772/0020/0022/proportional | YES    | YES          | YES          | NO           | 0022    | PRO/LA_000001000322/0000000342/0010 | continu | fixed        |          | 31/12/2998 23:59:59 | 5214.000            | CONS_LATAM_BR02 | 8640000.000 |                  |                  |                | VolumePlanning     | LA_000000000000004772 | 1.000    | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/000001000322/444/0010/000000000000004772/0001/0002              | YES    | YES          | YES          | NO           | 0002    | PRO/LA_000001000322/0000000342/0010 | continu | proportional |          | 31/12/2998 23:59:59 | 14.000              | CONS_LATAM_BR02 | 4320000.000 |                  |                  |                | *                  | LA_000000000000004772 | 521.000  | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/000001000326/444/0010/000000000000004776/0020/0026              | YES    | YES          | YES          | NO           | 0026    | PRO/LA_000001000326/0000000346/0010 |         |              |          | 31/12/2998 23:59:59 | 5214.000            | CONS_LATAM_BR06 | 864000.000  |                  |                  |                |                    | LA_000000000000004776 | 0.000    | 01/01/1980 00:00:00 |
      | PRO/CONS_LATAM/000001000326/444/0010/000000000000004776/0001/0006              | YES    | YES          | YES          | NO           | 0006    | PRO/LA_000001000326/0000000346/0010 |         | proportional |          | 31/12/2998 23:59:59 | 15.000              | CONS_LATAM_BR06 | 864000.000  |                  |                  |                | *                  | LA_000000000000004776 | 521.000  | 01/01/1980 00:00:00 |

