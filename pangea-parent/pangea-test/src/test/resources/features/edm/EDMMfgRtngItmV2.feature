@pangea_test @6663
Feature: E.2.1.9 EDMRouting-MFG_RTNG_ITEM_V2
  As a Data user,
  I want EDG to curate Routing item node raw data from ECC LATAM
  so that there is an Enterprise Data Model that contains Routing-level supply chain data consistent with Enterprise standards, and ready for consumption by reports and/or cross-functional applications.

  Scenario: Full Load curation

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | sourceSystem |
      | project_one       | CONS_LATAM   |
      | EMS               | EMS          |

    And I wait "/edm/source_system_v1" Async Queue complete

    Given I import "/project_one/plpo" by keyFields "plnty,plnkn,plnnr,zaehl"
      | mandt | plnty | plnnr    | plnkn    | zaehl    | datuv    | aennr | loekz | andat    | aedat    | sumnr | vornr | steus | arbid    | werks | ltxa1    | meinh | bmsch | lar01 | vge01 | vgw01 | lar02  | vge02 | vgw02 | lar03  | vge03 | vgw03 | lar04  | vge04 | vgw04 | lar05 | vge05 | vgw05 | lar06 | vge06 | vgw06 | usr00 | usr01             | usr02 | usr03 | usr04 | usr10     |
      | 120   | N     | 50019343 | 00000001 | 00000001 | 20150519 |       |       | 20150519 | 20170630 |       | 0010  | PP01  | 10001460 | BR12  | Produzir | EA    | 1000  | MOBRA | H     | 0.640 | HORHOM | H     | 0.640 | HORMAQ | H     | 0.213 | DEPR01 | H     | 0.213 | OHIND | TS    | 8     |       |       |       | Drum  | ListerinePackCrew | 85%   | 97%   | 5     | Bulk Tank |
      | 120   | N     | 50019343 | 00000002 | 00000002 | 20150713 |       |       | 20150713 | 20171004 |       | 0010  | PP01  | 10001458 | BR12  | PRODUÇÃO | EA    | 1000  | MOBRA | H     | 0.657 | HORHOM | H     | 0.657 | HORMAQ | H     | 0.219 | DEPR01 | H     | 0.219 | OHIND | TS    | 8     |       |       |       | Drum  | ListerinePackCrew | 85%   | 98%   | 5     | Bulk Tank |
      | 120   | N     | 50019343 | 00000003 | 00000003 | 20150713 |       |       | 20150713 | 20170622 |       | 0010  | PP01  | 10001457 | BR12  | PRODUÇÃO | EA    | 1000  | MOBRA | H     | 0.674 | HORHOM | H     | 0.674 | HORMAQ | H     | 0.225 | DEPR01 | H     | 0.225 | OHIND | TS    | 8     |       |       |       | Drum  | ListerinePackCrew | 85%   | 97%   | 5     | Bulk Tank |
      | 120   | N     | 50019343 | 00000004 | 00000004 | 20150713 |       |       | 20150713 | 20170622 |       | 0010  | PP01  | 10007203 | BR12  | PRODUÇÃO | EA    | 1000  | MOBRA | H     | 0.916 | HORHOM | H     | 0.916 | HORMAQ | H     | 0.212 | DEPR01 | H     | 0.212 | OHIND | TS    | 8     |       |       |       | Drum  | ListerinePackCrew | 85%   | 98%   | 5     | Bulk Tank |
      | 120   | N     | 50019343 | 00000005 | 00000005 | 20150713 |       |       | 20150713 | 20170710 |       | 0010  | PP01  | 10006094 | BR12  | PRODUÇÃO | EA    | 1000  | MOBRA | H     | 0.733 | HORHOM | H     | 0.733 | HORMAQ | H     | 0.244 | DEPR01 | H     | 0.244 | OHIND | TS    | 8     |       |       |       | Drum  | ListerinePackCrew | 85%   | 97%   | 5     | Bulk Tank |
      | 120   | N     | 50019343 | 00000006 | 00000006 | 20150724 |       |       | 20150724 | 20151126 |       | 0010  | PP01  | 10001460 | BR12  | Produzir | EA    | 1000  | MOBRA | H     | 0.625 | HORHOM | H     | 0.625 | HORMAQ | H     | 0.208 | DEPR01 | H     | 0.208 | OHIND | TS    | 8     |       |       |       | Drum  | ListerinePackCrew | 85%   | 98%   | 5     | Bulk Tank |
      | 120   | N     | 50019343 | 00000007 | 00000007 | 20170622 |       |       | 20170622 | 20170622 |       | 0010  | PP01  | 10001459 | BR12  | PRODUÇÃO | EA    | 1000  | MOBRA | H     | 0.701 | HORHOM | H     | 0.701 | HORMAQ | H     | 0.234 | DEPR01 | H     | 0.234 | OHIND | TS    | 8     |       |       |       | Drum  | ListerinePackCrew | 85%   | 97%   | 5     | Bulk Tank |
      | 120   | N     | 50019343 | 00000008 | 00000008 | 20170725 |       |       | 20170725 | 20170725 |       | 0010  | PP01  | 10001468 | BR12  | PRODUÇÃO | EA    | 1000  | MOBRA | H     | 0.855 | HORHOM | H     | 0.855 | HORMAQ | H     | 0.285 | DEPR01 | H     | 0.285 | OHIND | TS    | 8     |       |       |       | Drum  | ListerinePackCrew | 85%   | 98%   | 5     | Bulk Tank |

    And I wait "/project_one/plpo" Async Queue complete


    Given I import "/project_one/plas" by keyFields "plnty,plnnr,plnal,plnfl,plnkn,zaehl"
      | mandt | plnty | plnnr    | plnal | plnfl | plnkn    | zaehl    | datuv    | aennr | loekz | parkz | andat    | aedat    |
      | 120   | N     | 50019343 | 01    | 0     | 00000001 | 00000001 | 20150519 |       |       |       | 20150519 | 20150519 |
      | 120   | N     | 50019343 | 02    | 0     | 00000002 | 00000002 | 20150713 |       |       |       | 20150713 | 20150713 |
      | 120   | N     | 50019343 | 03    | 0     | 00000003 | 00000003 | 20150713 |       |       |       | 20150713 | 20150713 |
      | 120   | N     | 50019343 | 04    | 0     | 00000004 | 00000004 | 20150713 |       |       |       | 20150713 | 20150713 |
      | 120   | N     | 50019343 | 05    | 0     | 00000005 | 00000005 | 20150713 |       |       |       | 20150713 | 20150713 |
      | 120   | N     | 50019343 | 06    | 0     | 00000006 | 00000006 | 20150724 |       |       |       | 20150724 | 20150724 |
      | 120   | N     | 50019343 | 07    | 0     | 00000007 | 00000007 | 20170622 |       |       |       | 20170622 | 20170622 |
      | 120   | N     | 50019343 | 08    | 0     | 00000008 | 00000008 | 20170725 |       |       |       | 20170725 | 20170725 |

    And I wait "/project_one/plas" Async Queue complete

    When I submit task with xml file "xml/edm/EDMMfgRtngItmV2.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/mfg_rtng_itm" by keyFields "srcSysCd,rtngTypCd,rtngGrpCd,rtngItmNum,rtngItmVersnCntrNbr"
      | srcSysCd   | rtngTypCd | rtngGrpCd | rtngItmNum | rtngItmVersnCntrNbr | valFromDt | chgNum | delInd | crtDttm  | chgDttm  | rtngSupNdeNum | operNum | operCd | wrkCntrCd | plntCd | operDesc | operUom | bsQty | act1Cd | act1UomCd | act1Qty | act2Cd | act2UomCd | act2Qty | act3Cd | act3UomCd | act3Qty | act4Cd | act4UomCd | act4Qty | act5Cd | act5UomCd | act5Qty | act6Cd | act6UomCd | act6Qty | operDurtnQty | operDurtnUomCd | minOperDurtnQty | minOperDurtnUomCd | PhsInd | rtgItemEndDate | attr1Desc | attr2Desc         | attr3Desc | attr4Desc | attr5Qty | attr11Ind |
      | CONS_LATAM | N         | 50019343  | 00000001   | 00000001            | 20150519  |        |        | 20150519 | 20170630 |               | 0010    | PP01   | 10001460  | BR12   | Produzir | EA      | 1000  | MOBRA  | H         | 0.640   | HORHOM | H         | 0.640   | HORMAQ | H         | 0.213   | DEPR01 | H         | 0.213   | OHIND  | TS        | 8       |        |           |         |              |                |                 |                   |        | 9999/12/31       | Drum      | ListerinePackCrew | 85%       | 97%       | 5        | Bulk Tank |
      | CONS_LATAM | N         | 50019343  | 00000002   | 00000002            | 20150713  |        |        | 20150713 | 20171004 |               | 0010    | PP01   | 10001458  | BR12   | PRODUÇÃO | EA      | 1000  | MOBRA  | H         | 0.657   | HORHOM | H         | 0.657   | HORMAQ | H         | 0.219   | DEPR01 | H         | 0.219   | OHIND  | TS        | 8       |        |           |         |              |                |                 |                   |        | 9999/12/31       | Drum      | ListerinePackCrew | 85%       | 98%       | 5        | Bulk Tank |
      | CONS_LATAM | N         | 50019343  | 00000003   | 00000003            | 20150713  |        |        | 20150713 | 20170622 |               | 0010    | PP01   | 10001457  | BR12   | PRODUÇÃO | EA      | 1000  | MOBRA  | H         | 0.674   | HORHOM | H         | 0.674   | HORMAQ | H         | 0.225   | DEPR01 | H         | 0.225   | OHIND  | TS        | 8       |        |           |         |              |                |                 |                   |        | 9999/12/31       | Drum      | ListerinePackCrew | 85%       | 97%       | 5        | Bulk Tank |
      | CONS_LATAM | N         | 50019343  | 00000004   | 00000004            | 20150713  |        |        | 20150713 | 20170622 |               | 0010    | PP01   | 10007203  | BR12   | PRODUÇÃO | EA      | 1000  | MOBRA  | H         | 0.916   | HORHOM | H         | 0.916   | HORMAQ | H         | 0.212   | DEPR01 | H         | 0.212   | OHIND  | TS        | 8       |        |           |         |              |                |                 |                   |        | 9999/12/31       | Drum      | ListerinePackCrew | 85%       | 98%       | 5        | Bulk Tank |
      | CONS_LATAM | N         | 50019343  | 00000005   | 00000005            | 20150713  |        |        | 20150713 | 20170710 |               | 0010    | PP01   | 10006094  | BR12   | PRODUÇÃO | EA      | 1000  | MOBRA  | H         | 0.733   | HORHOM | H         | 0.733   | HORMAQ | H         | 0.244   | DEPR01 | H         | 0.244   | OHIND  | TS        | 8       |        |           |         |              |                |                 |                   |        | 9999/12/31       | Drum      | ListerinePackCrew | 85%       | 97%       | 5        | Bulk Tank |
      | CONS_LATAM | N         | 50019343  | 00000006   | 00000006            | 20150724  |        |        | 20150724 | 20151126 |               | 0010    | PP01   | 10001460  | BR12   | Produzir | EA      | 1000  | MOBRA  | H         | 0.625   | HORHOM | H         | 0.625   | HORMAQ | H         | 0.208   | DEPR01 | H         | 0.208   | OHIND  | TS        | 8       |        |           |         |              |                |                 |                   |        | 9999/12/31       | Drum      | ListerinePackCrew | 85%       | 98%       | 5        | Bulk Tank |
      | CONS_LATAM | N         | 50019343  | 00000007   | 00000007            | 20170622  |        |        | 20170622 | 20170622 |               | 0010    | PP01   | 10001459  | BR12   | PRODUÇÃO | EA      | 1000  | MOBRA  | H         | 0.701   | HORHOM | H         | 0.701   | HORMAQ | H         | 0.234   | DEPR01 | H         | 0.234   | OHIND  | TS        | 8       |        |           |         |              |                |                 |                   |        | 9999/12/31       | Drum      | ListerinePackCrew | 85%       | 97%       | 5        | Bulk Tank |
      | CONS_LATAM | N         | 50019343  | 00000008   | 00000008            | 20170725  |        |        | 20170725 | 20170725 |               | 0010    | PP01   | 10001468  | BR12   | PRODUÇÃO | EA      | 1000  | MOBRA  | H         | 0.855   | HORHOM | H         | 0.855   | HORMAQ | H         | 0.285   | DEPR01 | H         | 0.285   | OHIND  | TS        | 8       |        |           |         |              |                |                 |                   |        | 9999/12/31       | Drum      | ListerinePackCrew | 85%       | 98%       | 5        | Bulk Tank |

#  Scenario: delete all test data
#    And I will remove all data with region "/edm/source_system_v1"
#    And I will remove all data with region "/project_one/plpo"
#    And I will remove all data with region "/project_one/plas"
#    And I will remove all data with region "/edm/mfg_rtng_itm"


