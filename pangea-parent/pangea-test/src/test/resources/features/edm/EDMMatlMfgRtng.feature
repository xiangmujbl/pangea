@pangea_test @AEAZ-3268
Feature: E.2.1.1 EDMRouting-MATL_MFG_RTNG - Curation

  As a Data user,
  I want EDG to curate Routing raw data from ECC LATAM
  so that there is an Enterprise Data Model that contains Routing-level supply chain data consistent with Enterprise standards, and ready for consumption by reports and/or cross-functional applications.

  Scenario: Full Load curation

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | sourceSystem |
      | project_one       | project_one  |
      | EMS               | EMS          |
      | project_two       | project_two  |

    And I wait "/edm/source_system_v1" Async Queue complete
#
#      #PLNNR/PLNTY/PLNAL/ZKRIZ
    And I import "/project_one/mapl" by keyFields "matnr,werks,plnty,plnnr,plnal,zkriz,zaehl,datuv,aennr,loekz,andat,aedat"

      | matnr       | werks | plnty | plnnr | plnal | zkriz | zaehl | datuv    | aennr        | loekz | andat    | aedat    |
      | DARRENTEST  | 0001  | 2     | 999   | 1     | 1     | 1     | 20180419 |              |       | 20180514 | 20180514 |
      | DARRENTEST  | 0001  | 2     | 999   | 2     | 2     | 2     | 20180420 |              |       | 20180514 | 20180514 |
      | DARRENTEST  | 0001  | N     | 999   | 1     | 1     | 1     | 20180420 |              |       | 20180514 | 20180514 |
      | DARRENTEST2 | 0001  | 2     | 999   | 1     | 3     | 3     | 20180825 | 500000000003 |       | 20180514 | 20180514 |
      | DARRENTEST2 | 0001  | 2     | 999   | 1     | 3     | 4     | 20180831 | 500000000004 | X     | 20180514 | 20180514 |
      | DARRENTEST2 | 0001  | 2     | 999   | 1     | 4     | 5     | 20180925 | 500000000005 |       | 20180514 | 20180514 |
      | DARRENTEST2 | 0001  | 3-F1  | 999   | 1     | 4     | 5     | 20180925 | 500000000005 |       | 20180514 | 20180514 |

    And I wait "/project_one/mapl" Async Queue complete


    When I submit task with xml file "xml/edm/EDMMatlMfgRtng.xml" and execute file "jar/pangea-view.jar"


    Then I check region data "/edm/matl_mfg_rtng" by keyFields "srcSysCd,matlNum,plntCd,rtngTypCd,rntgGrpCd,rntgGrpCntrNbr,rntgAddtnlCntrNbr,matlRtngVrsnCntrNbr"
      | srcSysCd    | matlNum     | plntCd | rtngTypCd | rntgGrpCd | rntgGrpCntrNbr | rntgAddtnlCntrNbr | matlRtngVrsnCntrNbr | valFromDt  | chgNum       | delInd | crtDttm    | chgDttm    | matlRtngValid_To |
      | project_one | DARRENTEST  | 0001   | 2         | 999       | 1              | 1                 | 1                   | 20180419 |              |        | 20180514 | 20180514 | 9999/12/31       |
      | project_one | DARRENTEST  | 0001   | 2         | 999       | 2              | 2                 | 2                   | 20180420 |              |        | 20180514 | 20180514 | 9999/12/31       |
      | project_one | DARRENTEST  | 0001   | N         | 999       | 1              | 1                 | 1                   | 20180420 |              |        | 20180514 | 20180514 | 9999/12/31       |
      | project_one | DARRENTEST2 | 0001   | 2         | 999       | 1              | 3                 | 3                   | 20180825 | 500000000003 |        | 20180514 | 20180514 | 2018/08/31       |
      | project_one | DARRENTEST2 | 0001   | 2         | 999       | 1              | 4                 | 5                   | 20180925 | 500000000005 |        | 20180514 | 20180514 | 9999/12/31       |

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/edm/source_system_v1"

    And I will remove all data with region "/project_one/mapl"

    And I will remove all data with region "/edm/matl_mfg_rtng"

