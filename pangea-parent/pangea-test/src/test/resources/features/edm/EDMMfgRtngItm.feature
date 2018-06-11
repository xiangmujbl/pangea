@pangea_test @3276
Feature: E.2.1.9 EDMRouting-MFG_RTNG_ITEM - Curation 3276

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
      | plnty | plnnr | plnkn | zaehl | datuv      | aennr        | loekz | andat | aedat | sumnr | vornr | steus | arbid | werks | ltxa1 | meinh | bmsch | lar01 | vge01 | vgw01 | lar02 | vge02 | vgw02 | lar03 | vge03 | vgw03 | lar04 | vge04 | vgw04 | lar05 | vge05 | vgw05 | lar06 | vge06 | vgw06 | dauno | daune | daumi | daume | phflg |
      | 2     | 998   | 1     | 1     | 2018/04/24 |              | loekz | andat | aedat | sumnr | 0010  | steus | arbid | werks | ltxa1 | meinh | bmsch | lar01 | vge01 | vgw01 | lar02 | vge02 | vgw02 | lar03 | vge03 | vgw03 | lar04 | vge04 | vgw04 | lar05 | vge05 | vgw05 | lar06 | vge06 | vgw06 | dauno | daune | daumi | daume | phflg |
      | 2     | 998   | 2     | 2     | 2018/04/24 |              | loekz | andat | aedat | sumnr | 0020  | steus | arbid | werks | ltxa1 | meinh | bmsch | lar01 | vge01 | vgw01 | lar02 | vge02 | vgw02 | lar03 | vge03 | vgw03 | lar04 | vge04 | vgw04 | lar05 | vge05 | vgw05 | lar06 | vge06 | vgw06 | dauno | daune | daumi | daume | phflg |
      | 2     | 998   | 3     | 3     | 2018/04/24 |              | loekz | andat | aedat | sumnr | 0015  | steus | arbid | werks | ltxa1 | meinh | bmsch | lar01 | vge01 | vgw01 | lar02 | vge02 | vgw02 | lar03 | vge03 | vgw03 | lar04 | vge04 | vgw04 | lar05 | vge05 | vgw05 | lar06 | vge06 | vgw06 | dauno | daune | daumi | daume | phflg |
      | 2     | 998   | 3     | 6     | 2018/08/25 | 500000000003 | loekz | andat | aedat | sumnr | 0015  | steus | arbid | werks | ltxa1 | meinh | bmsch | lar01 | vge01 | vgw01 | lar02 | vge02 | vgw02 | lar03 | vge03 | vgw03 | lar04 | vge04 | vgw04 | lar05 | vge05 | vgw05 | lar06 | vge06 | vgw06 | dauno | daune | daumi | daume | phflg |
      | 2     | 998   | 4     | 4     | 2018/04/24 |              | loekz | andat | aedat | sumnr | 0025  | steus | arbid | werks | ltxa1 | meinh | bmsch | lar01 | vge01 | vgw01 | lar02 | vge02 | vgw02 | lar03 | vge03 | vgw03 | lar04 | vge04 | vgw04 | lar05 | vge05 | vgw05 | lar06 | vge06 | vgw06 | dauno | daune | daumi | daume | phflg |
      | 2     | 998   | 5     | 5     | 2018/08/25 | 500000000003 | loekz | andat | aedat | sumnr | 0026  | steus | arbid | werks | ltxa1 | meinh | bmsch | lar01 | vge01 | vgw01 | lar02 | vge02 | vgw02 | lar03 | vge03 | vgw03 | lar04 | vge04 | vgw04 | lar05 | vge05 | vgw05 | lar06 | vge06 | vgw06 | dauno | daune | daumi | daume | phflg |
      | N     | 998   | 6     | 7     | 2018/08/25 | 500000000003 | loekz | andat | aedat | sumnr | 0026  | steus | arbid | werks | ltxa1 | meinh | bmsch | lar01 | vge01 | vgw01 | lar02 | vge02 | vgw02 | lar03 | vge03 | vgw03 | lar04 | vge04 | vgw04 | lar05 | vge05 | vgw05 | lar06 | vge06 | vgw06 | dauno | daune | daumi | daume | phflg |
      # plpo-plnty != plas-plnty ,plpo-plnnr == plas-plnnr ,plpo-plnkn == plas-plnkn
      | N     | 998   | 7     | 8     | 2018/08/25 | 500000000003 | loekz | andat | aedat | sumnr | 0026  | steus | arbid | werks | ltxa1 | meinh | bmsch | lar01 | vge01 | vgw01 | lar02 | vge02 | vgw02 | lar03 | vge03 | vgw03 | lar04 | vge04 | vgw04 | lar05 | vge05 | vgw05 | lar06 | vge06 | vgw06 | dauno | daune | daumi | daume | phflg |
      #      plpo-plnty == plas-plnty ,plpo-plnnr != plas-plnnr ,plpo-plnkn == plas-plnkn
      | 2     | 997   | 8     | 9     | 2018/08/25 | 500000000003 | loekz | andat | aedat | sumnr | 0026  | steus | arbid | werks | ltxa1 | meinh | bmsch | lar01 | vge01 | vgw01 | lar02 | vge02 | vgw02 | lar03 | vge03 | vgw03 | lar04 | vge04 | vgw04 | lar05 | vge05 | vgw05 | lar06 | vge06 | vgw06 | dauno | daune | daumi | daume | phflg |
      #      plpo-plnty == plas-plnty ,plpo-plnnr == plas-plnnr ,plpo-plnkn != plas-plnkn
      | 2     | 998   | 9     | 10    | 2018/08/25 | 500000000003 | loekz | andat | aedat | sumnr | 0026  | steus | arbid | werks | ltxa1 | meinh | bmsch | lar01 | vge01 | vgw01 | lar02 | vge02 | vgw02 | lar03 | vge03 | vgw03 | lar04 | vge04 | vgw04 | lar05 | vge05 | vgw05 | lar06 | vge06 | vgw06 | dauno | daune | daumi | daume | phflg |
      #      plpo-plnty != plas-plnty ,plpo-plnnr != plas-plnnr ,plpo-plnkn != plas-plnkn
      | 2     | 999   | 11    | 11    | 2018/08/25 | 500000000003 | loekz | andat | aedat | sumnr | 0026  | steus | arbid | werks | ltxa1 | meinh | bmsch | lar01 | vge01 | vgw01 | lar02 | vge02 | vgw02 | lar03 | vge03 | vgw03 | lar04 | vge04 | vgw04 | lar05 | vge05 | vgw05 | lar06 | vge06 | vgw06 | dauno | daune | daumi | daume | phflg |

    And I wait "/project_one/plpo" Async Queue complete


    Given I import "/project_one/plas" by keyFields "plnty,plnnr,plnal,plnfl,plnkn,zaehl"
      | plnty | plnnr | plnal | plnfl | plnkn | zaehl | datuv      | loekz | arnnr        |
      | 2     | 998   | 1     | 0     | 1     | 1     | 2018/04/24 |       |              |
      | 2     | 998   | 1     | 0     | 2     | 2     | 2018/04/24 |       |              |
      | 2     | 998   | 1     | 0     | 3     | 3     | 2018/04/24 |       |              |
      | 2     | 998   | 1     | 0     | 4     | 4     | 2018/04/24 |       |              |
      | 2     | 998   | 1     | 0     | 4     | 6     | 2018/08/31 | X     | 500000000004 |
      | 2     | 998   | 1     | 0     | 5     | 5     | 2018/08/25 |       | 500000000003 |
      | N     | 998   | 1     | 0     | 6     | 7     | 2018/08/25 |       | 500000000005 |
      | 2     | 998   | 1     | 0     | 7     | 8     | 2018/08/25 |       | 500000000006 |
      | 2     | 998   | 1     | 0     | 8     | 9     | 2018/08/25 |       | 500000000007 |
      | 2     | 998   | 1     | 0     | 10    | 10    | 2018/08/25 |       | 500000000008 |
      | N     | 996   | 1     | 0     | 12    | 11    | 2018/08/25 |       | 500000000009 |
    And I wait "/project_one/plas" Async Queue complete

    When I submit task with xml file "xml/edm/EDMMfgRtngItm.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/mfg_rtng_itm" by keyFields "srcSysCd,rtngTypCd,rtngGrpCd,rtngItmNum,rtngItmVersnCntrNbr"
      | srcSysCd   | rtngTypCd | rtngGrpCd | rtngItmNum | rtngItmVersnCntrNbr | valFromDt  | chgNum       | delInd | crtDttm | chgDttm | rtngSupNdeNum | operNum | operCd | wrkCntrCd | plntCd | operDesc | operUom | bsQty | act1Cd | act1UomCd | act1Qty | act2Cd | act2UomCd | act2Qty | act3Cd | act3UomCd | act3Qty | act4Cd | act4UomCd | act4Qty | act5Cd | act5UomCd | act5Qty | act6Cd | act6UomCd | act6Qty | operDurtnQty | operDurtnUomCd | minOperDurtnQty | minOperDurtnUomCd | phsInd | rtgItemEndDate |
      | CONS_LATAM | 2         | 998       | 1          | 1                   | 2018/04/24 |              | loekz  | andat   | aedat   | sumnr         | 0010    | steus  | arbid     | werks  | ltxa1    | meinh   | bmsch | lar01  | vge01     | vgw01   | lar02  | vge02     | vgw02   | lar03  | vge03     | vgw03   | lar04  | vge04     | vgw04   | lar05  | vge05     | vgw05   | lar06  | vge06     | vgw06   | dauno        | daune          | daumi           | daume             | phflg  | 9999/12/31     |
      | CONS_LATAM | 2         | 998       | 2          | 2                   | 2018/04/24 |              | loekz  | andat   | aedat   | sumnr         | 0020    | steus  | arbid     | werks  | ltxa1    | meinh   | bmsch | lar01  | vge01     | vgw01   | lar02  | vge02     | vgw02   | lar03  | vge03     | vgw03   | lar04  | vge04     | vgw04   | lar05  | vge05     | vgw05   | lar06  | vge06     | vgw06   | dauno        | daune          | daumi           | daume             | phflg  | 9999/12/31     |
      | CONS_LATAM | 2         | 998       | 3          | 3                   | 2018/04/24 |              | loekz  | andat   | aedat   | sumnr         | 0015    | steus  | arbid     | werks  | ltxa1    | meinh   | bmsch | lar01  | vge01     | vgw01   | lar02  | vge02     | vgw02   | lar03  | vge03     | vgw03   | lar04  | vge04     | vgw04   | lar05  | vge05     | vgw05   | lar06  | vge06     | vgw06   | dauno        | daune          | daumi           | daume             | phflg  | 2018/08/25     |
      | CONS_LATAM | 2         | 998       | 3          | 6                   | 2018/08/25 | 500000000003 | loekz  | andat   | aedat   | sumnr         | 0015    | steus  | arbid     | werks  | ltxa1    | meinh   | bmsch | lar01  | vge01     | vgw01   | lar02  | vge02     | vgw02   | lar03  | vge03     | vgw03   | lar04  | vge04     | vgw04   | lar05  | vge05     | vgw05   | lar06  | vge06     | vgw06   | dauno        | daune          | daumi           | daume             | phflg  | 9999/12/31     |
      | CONS_LATAM | 2         | 998       | 4          | 4                   | 2018/04/24 |              | loekz  | andat   | aedat   | sumnr         | 0025    | steus  | arbid     | werks  | ltxa1    | meinh   | bmsch | lar01  | vge01     | vgw01   | lar02  | vge02     | vgw02   | lar03  | vge03     | vgw03   | lar04  | vge04     | vgw04   | lar05  | vge05     | vgw05   | lar06  | vge06     | vgw06   | dauno        | daune          | daumi           | daume             | phflg  | 2018/08/31     |
      | CONS_LATAM | 2         | 998       | 5          | 5                   | 2018/08/25 | 500000000003 | loekz  | andat   | aedat   | sumnr         | 0026    | steus  | arbid     | werks  | ltxa1    | meinh   | bmsch | lar01  | vge01     | vgw01   | lar02  | vge02     | vgw02   | lar03  | vge03     | vgw03   | lar04  | vge04     | vgw04   | lar05  | vge05     | vgw05   | lar06  | vge06     | vgw06   | dauno        | daune          | daumi           | daume             | phflg  | 9999/12/31     |
      | CONS_LATAM | N         | 998       | 6          | 7                   | 2018/08/25 | 500000000003 | loekz  | andat   | aedat   | sumnr         | 0026    | steus  | arbid     | werks  | ltxa1    | meinh   | bmsch | lar01  | vge01     | vgw01   | lar02  | vge02     | vgw02   | lar03  | vge03     | vgw03   | lar04  | vge04     | vgw04   | lar05  | vge05     | vgw05   | lar06  | vge06     | vgw06   | dauno        | daune          | daumi           | daume             | phflg  | 9999/12/31     |

  Scenario: delete all test data
    And I will remove all data with region "/edm/source_system_v1"
    And I will remove all data with region "/project_one/plpo"
    And I will remove all data with region "/project_one/plas"
    And I will remove all data with region "/edm/mfg_rtng_itm"


