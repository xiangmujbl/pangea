@pangea_test @AEAZ-7853
Feature:
  As a Data user,
  I want EDG to curate Routing relationship raw data from ECC LATAM
  so that there is an Enterprise Data Model that contains Routing-level supply chain data consistent with Enterprise standards, and ready for consumption by reports and/or cross-functional applications.

  Scenario: Full Load curation

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | sourceSystem |
      | project_one       | CONS_LATAM   |
      | EMS               | EMS          |

    And I wait "/edm/source_system_v1" Async Queue complete

    And I import "/project_one/plab" by keyFields "mandt,plnty,plnnr,plnal,plnkn,plnrn,alnrn,knnrn,aobar,zaehl"
      | mandt | plnty | plnnr    | plnal | plnkn    | plnrn | alnrn | knnrn | aobar | mimax | zaehl    | datuv    | aennr | loekz | zeinh | dauer | daukz | kalid | werks | andat | aedat |
      | 120   | 2     | 50000001 | 01    | 00000001 |       |       | 5     | FS    |       | 00000001 | 20101230 |       |       |       |       |       |       |       |       |       |
      | 120   | 2     | 50000002 | 01    | 00000001 |       |       | 5     | SS    |       | 00000001 | 20100101 |       |       |       |       |       |       |       |       |       |
      | 120   | 2     | 50000002 | 01    | 00000001 |       |       | 5     | SS    |       | 00000003 | 20121231 |       |       |       |       |       |       |       |       |       |
      | 120   | 2     | 50000002 | 01    | 00000001 |       |       | 5     | SS    |       | 00000004 | 21060721 |       |       |       |       |       |       |       |       |       |
      | 120   | N     | 50000002 | 01    | 00000001 |       |       | 5     | SS    |       | 00000005 | 21060620 |       |       |       |       |       |       |       |       |       |
      | 120   | 3     | 50000002 | 01    | 00000001 |       |       | 5     | SS    |       | 00000004 | 20101230 |       |       |       |       |       |       |       |       |       |

    And I wait "/project_one/plab" Async Queue complete

    When I submit task with xml file "xml/edm/EDMMfgRtngRltnshp.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/mfg_rtng_rltnshp" by keyFields "srcSysCd,rtngTypCd,rtngGrpCd,rtngGrpCntrNbr,rtngNdeNum,rtngScndGrpCd,rtngScndGrpCntrNbr,rtngScndNdeNum,rltnshpTypCd,rltnshpMxIntrvlCd,rltnshpVrsnCntrNbr"
      | srcSysCd   | rtngTypCd | rtngGrpCd | rtngGrpCntrNbr | rtngNdeNum | rtngScndGrpCd | rtngScndGrpCntrNbr | rtngScndNdeNum | rltnshpTypCd | rltnshpMxIntrvlCd | rltnshpVrsnCntrNbr | valFromDt | chgNum | rltnshpIntrvlUomCd | rltnshpIntrvl | rltsnhpDrtnInd | fctryClndrCd | plntCd | crtDttm | chgDttm | rltnshpValidEnd |
      | CONS_LATAM | 2         | 50000001  | 01             | 00000001   |               |                    | 5              | FS           |                   | 00000001           | 20101230  |        |                    |               |                |              |        |         |         | 99991231        |
      | CONS_LATAM | 2         | 50000002  | 01             | 00000001   |               |                    | 5              | SS           |                   | 00000001           | 20100101  |        |                    |               |                |              |        |         |         | 20121230        |
      | CONS_LATAM | 2         | 50000002  | 01             | 00000001   |               |                    | 5              | SS           |                   | 00000003           | 20121231  |        |                    |               |                |              |        |         |         | 21060720        |
      | CONS_LATAM | 2         | 50000002  | 01             | 00000001   |               |                    | 5              | SS           |                   | 00000004           | 21060721  |        |                    |               |                |              |        |         |         | 99991231        |
      | CONS_LATAM | N         | 50000002  | 01             | 00000001   |               |                    | 5              | SS           |                   | 00000005           | 21060620  |        |                    |               |                |              |        |         |         | 99991231        |


  Scenario: delete all test data

    And I will remove all data with region "/edm/source_system_v1"
    And I will remove all data with region "/project_one/plab"
    And I will remove all data with region "/edm/mfg_rtng_rltnshp"



