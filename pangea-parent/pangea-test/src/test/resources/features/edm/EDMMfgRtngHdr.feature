@pangea_test @3275
Feature: E.2.1.8 EDMRouting-MFG_RTNG_HDR- Curation 3275
  As a Data user,
  I want EDG to curate Routing Header raw data from ECC LATAM
  so that there is an Enterprise Data Model that contains Routing-level supply chain data consistent with Enterprise standards, and ready for consumption by reports and/or cross-functional applications.

  Scenario: Full Load curation

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | sourceSystem |
      | project_one       | CONS_LATAM   |
      | EMS               | EMS          |

    And I wait "/edm/source_system_v1" Async Queue complete


    Given I import "/project_one/plko" by keyFields "plnty,plnnr,plnal,zaehl"
      | plnty | plnnr | plnal | zaehl | datuv      | aennr        | loekz | andat      | aedat      | werks | verwe       | statu | plnme       | losvn       | losbs       | vagrp       | ktext       | profidnetz       |
      | 2     | 999   | 1     | 1     | 2018/04/19 |              |       | 2018/05/15 | 2018/05/15 | 0001  | verwe-line1 | 1     | plnme-line1 | losvn-line1 | losbs-line1 | vagrp-line1 | ktext-line1 | profidnetz-line1 |
      | 2     | 999   | 1     | 2     | 2018/08/25 | 500000000003 |       | 2018/05/15 | 2018/05/15 | 0001  | verwe-line2 | 1     | plnme-line2 | losvn-line2 | losbs-line2 | vagrp-line2 | ktext-line2 | profidnetz-line2 |
      | 2     | 999   | 2     | 3     | 2018/04/20 |              |       | 2018/05/15 | 2018/05/15 | 0001  | verwe-line3 | 4     | plnme-line3 | losvn-line3 | losbs-line3 | vagrp-line3 | ktext-line3 | profidnetz-line3 |
      | N     | 999   | 2     | 3     | 2018/04/20 |              |       | 2018/05/15 | 2018/05/15 | 0001  | verwe-line4 | 4     | plnme-line4 | losvn-line4 | losbs-line4 | vagrp-line4 | ktext-line4 | profidnetz-line4 |
      | 3-F1  | 999   | 2     | 3     | 2018/04/20 |              |       | 2018/05/15 | 2018/05/15 | 0001  | verwe-line5 | 4     | plnme-line5 | losvn-line5 | losbs-line5 | vagrp-line5 | ktext-line5 | profidnetz-line5 |

    And I wait "/project_one/plko" Async Queue complete


    When I submit task with xml file "xml/edm/EDMMfgRtngHdr.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/mfg_rtng_hdr" by keyFields "srcSysCd,rtngTypCd,rtngGrpCd,rtngGrpCntrNbr,rtngVrsnCntrNbr"
      | srcSysCd   | rtngTypCd | rtngGrpCd | rtngGrpCntrNbr | rtngVrsnCntrNbr | valFromDt  | chgNum       | delInd | crtDttm    | chgDttm    | plntCd | rtngUsgCd   | rtngSttsCd | rtngUomCd   | fromLtSzQty | toLtSzQty   | rtngPlnnrGrpCd | rtngDesc    | rtngPrflCd       | rtgVld_ToDt |
      | CONS_LATAM | 2         | 999       | 1              | 1               | 2018/04/19 |              |        | 2018/05/15 | 2018/05/15 | 0001   | verwe-line1 | 1          | plnme-line1 | losvn-line1 | losbs-line1 | vagrp-line1    | ktext-line1 | profidnetz-line1 | 2018/08/25   |
      | CONS_LATAM | 2         | 999       | 1              | 2               | 2018/08/25 | 500000000003 |        | 2018/05/15 | 2018/05/15 | 0001   | verwe-line2 | 1          | plnme-line2 | losvn-line2 | losbs-line2 | vagrp-line2    | ktext-line2 | profidnetz-line2 | 9999/12/31   |
      | CONS_LATAM | 2         | 999       | 2              | 3               | 2018/04/20 |              |        | 2018/05/15 | 2018/05/15 | 0001   | verwe-line3 | 4          | plnme-line3 | losvn-line3 | losbs-line3 | vagrp-line3    | ktext-line3 | profidnetz-line3 | 9999/12/31   |
      | CONS_LATAM | N         | 999       | 2              | 3               | 2018/04/20 |              |        | 2018/05/15 | 2018/05/15 | 0001   | verwe-line4 | 4          | plnme-line4 | losvn-line4 | losbs-line4 | vagrp-line4    | ktext-line4 | profidnetz-line4 | 9999/12/31   |

  Scenario: delete all test data


    And I will remove all data with region "/edm/source_system_v1"

    And I will remove all data with region "/project_one/plko"
    And I will remove all data with region "/edm/mfg_rtng_hdr"

