@pangea_test @3274
Feature: E.2.1.7 EDMRouting-MFG_RTNG_RLTNSHP - Curation 3274
  As a Data user,
  I want EDG to curate Routing relationship raw data from ECC LATAM
  so that there is an Enterprise Data Model that contains Routing-level supply chain data consistent with Enterprise standards, and ready for consumption by reports and/or cross-functional applications.

  Scenario: Full Load curation


    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | sourceSystem |
      | project_one       | CONS_LATAM   |
      | EMS               | EMS          |

    And I wait "/edm/source_system_v1" Async Queue complete



    And I import "/project_one/plab" by keyFields "plnty,plnnr,plnal,plnkn,plnrn,alnrn,knnrn,aobar,zaehl"

      | plnty | plnnr | plnal | plnkn | plnrn | alnrn | knnrn | aobar | mimax       | zaehl | datuv      | aennr        | loekz | zeinh       | dauer       | daukz       | kalid       | werks | andat      | aedat      |
      | 2     | 999   | 1     | 2     | 999   | 1     | 5     | FS    | mimax-line1 | 1     | 2018/08/25 | 500000000003 |       | zeinh-line1 | dauer-line1 | dauer-line1 | kalid-line1 | 0001  | 2018/05/15 | 2018/05/15 |
      | 2     | 999   | 1     | 2     | 999   | 1     | 5     | FS    | mimax-line2 | 2     | 2018/08/31 | 500000000004 | X     | zeinh-line2 | dauer-line2 | dauer-line2 | kalid-line2 | 0001  | 2018/05/15 | 2018/05/15 |
      | 2     | 999   | 1     | 2     | 999   | 1     | 5     | FS    | mimax-line3 | 3     | 2018/08/31 | 500000000005 |       | zeinh-line3 | dauer-line3 | dauer-line3 | kalid-line3 | 0001  | 2018/05/15 | 2018/05/15 |
      | 3-F1  | 999   | 1     | 2     | 999   | 1     | 5     | FS    | mimax-line1 | 1     | 2018/08/25 | 500000000003 |       | zeinh-line1 | dauer-line1 | dauer-line1 | kalid-line1 | 0001  | 2018/05/15 | 2018/05/15 |
      | N     | 999   | 1     | 2     | 999   | 1     | 5     | FS    | mimax-line1 | 1     | 2018/08/25 | 500000000003 |       | zeinh-line1 | dauer-line1 | dauer-line1 | kalid-line1 | 0001  | 2018/05/15 | 2018/05/15 |

    And I wait "/project_one/plab" Async Queue complete



    When I submit task with xml file "xml/edm/EDMMfgRtngRltnshp.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/mfg_rtng_rltnshp" by keyFields "srcSysCd,rtngTypCd,rtngGrpCd,rtngGrpCntrNbr,rtngNdeNum,rtngScndGrpCd,rtngScndGrpCntrNbr,rtngScndNdeNum,rltnshpTypCd,rltnshpMxIntrvlCd,rltnshpVrsnCntrNbr"
      | srcSysCd | rtngTypCd | rtngGrpCd | rtngGrpCntrNbr | rtngNdeNum | rtngScndGrpCd | rtngScndGrpCntrNbr | rtngScndNdeNum | rltnshpTypCd | rltnshpMxIntrvlCd | rltnshpVrsnCntrNbr | valFromDt | chgNum      | rltnshpIntrvlUomCd | rltnshpIntrvl | rltsnhpDrtnInd | fctryClndrCd | plntCd | crtDttm | chgDttm   | rltnshpValidEnd |
      |CONS_LATAM|2          |999        |1               |2           |999            |1                   |5               |FS            | mimax-line1       |1                   |2018/08/25 |500000000003 |zeinh-line1         |dauer-line1    |dauer-line1     |kalid-line1   |0001    |2018/05/15|2018/05/15|2018/08/31      |
      |CONS_LATAM|N          |999        |1               |2           |999            |1                   |5               |FS            | mimax-line1       |1                   |2018/08/25 |500000000003 |zeinh-line1         |dauer-line1    |dauer-line1     |kalid-line1   |0001    |2018/05/15|2018/05/15|9999/12/31       |
      |CONS_LATAM|2          |999        |1               |2           |999            |1                   |5               |FS            | mimax-line3       |3                   |2018/08/31 |500000000005 |zeinh-line3         |dauer-line3    |dauer-line3     |kalid-line3   |0001    |2018/05/15|2018/05/15|9999/12/31      |

  Scenario: delete all test data


    And I will remove all data with region "/edm/source_system_v1"

    And I will remove all data with region "/project_one/plab"
    And I will remove all data with region "/edm/mfg_rtng_rltnshp"



