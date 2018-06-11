@pangea_test @AEAZ-4254
Feature: OMPGdmBom AEAZ-4254

  Background:delete all test data

    Then I delete the test data

    And I will remove all data with region "/edm/matl_bom"

    And I will remove all data with region "/plan/cns_plan_parameter"

    And I will remove all data with region "/edm/matl_prod_versn"

    And I will remove all data with region "/edm/matl_mfg_rtng"

    And I will remove all data with region "/edm/mfg_rtng_itm_nde"

    And I will remove all data with region "/edm/mfg_rtng_itm"

    And I will remove all data with region "/edm/bom_hdr"

    And I will remove all data with region "/plan/edm_failed_data"

    And I will remove all data with region "/omp/gdm_bom"

    And I will remove the test file on sink application "GDMBOM_MASTER.tsv"

  @Scenario1
  Scenario: check rule J1 & check rule T1
    Given I import "/edm/matl_bom" by keyFields "srcSysCd,matlNum,plntCd,altBomNum,bomNum,bomUsgCd"
      | srcSysCd   | matlNum            | plntCd | altBomNum | bomNum   | bomUsgCd |
      | CONS_LATAM | 000000000000000023 | BR12   | 01        | 00000001 | 1        |
      | CONS_LATAM | 000000000000000024 | BR12   | 01        | 00000002 | 1        |
      #cns_plan_parameter-sourceSystem =MATL_BOM-srcSysCd
      | CONS_LATAM | 000000000000000025 | BR12   | 01        | 00000003 | 1        |
      #MATL_BOM-srcSysCd = MATL_PROD_VERSN-srcSysCd
      | CONS_LATAM | 000000000000000026 | BR12   | 01        | 00000005 | 1        |
      #MATL_BOM-matlNum MATL_PROD_VERSN-matlNum
      | CONS_LATAM | 000000000000000027 | BR12   | 01        | 00000006 | 1        |
      #MATL_BOM-plntCd= MATL_PROD_VERSN-plntCd
      | CONS_LATAM | 000000000000000029 | BR11   | 01        | 00000007 | 1        |
      #MATL_BOM-altBomNum= MATL_PROD_VERSN-altBomNum
      | CONS_LATAM | 000000000000000030 | BR12   | 01        | 00000008 | 1        |
      | CONS_LATAM | 000000000000000031 | BR12   | 01        | 00000009 | 1        |
      | CONS_LATAM | 000000000000000032 | BR12   | 01        | 00000010 | 1        |
      | CONS_LATAM | 000000000000000034 | BR12   | 01        | 00000011 | 1        |
      | CONS_LATAM | 000000000000000035 | BR12   | 01        | 00000012 | 1        |
      | CONS_LATAM | 000000000000000036 | BR12   | 01        | 00000013 | 1        |
      | CONS_LATAM | 000000000000000037 | BR12   | 01        | 00000014 | 1        |
      | CONS_LATAM | 000000000000000038 | BR12   | 01        | 00000015 | 1        |
      | CONS_LATAM | 000000000000000039 | BR12   | 01        | 00000016 | 1        |
      | CONS_LATAM | 000000000000000040 | BR12   | 01        | 00000017 | 1        |
      | CONS_LATAM | 000000000000000041 | BR12   | 01        | 00000018 | 1        |
      | CONS_LATAM | 000000000000000042 | BR12   | 01        | 00000019 | 1        |
    And I wait "/edm/matl_bom" Async Queue complete

    Given I import "/plan/cns_plan_parameter" by keyFields "sourceSystem,dataObject,attribute"
      | sourceSystem | dataObject  | attribute  | parameterValue |
      | CONS_LATAM   | SEND_TO_OMP | CONS_LATAM | 11             |
      # ns_plan_parameter-dataObject = 'SEND_TO_OMP'
      | CONS_LATAM   | SEND_TO_GDM | DPRelevant | 12             |
      | CONS_RTGBN   | SEND_TO_GDM | DPRelevant | 12             |
    And I wait "/plan/cns_plan_parameter" Async Queue complete

    Given I import "/edm/matl_prod_versn" by keyFields "srcSysCd,matlNum,plntCd,prdntVrsnNum"
      | srcSysCd   | matlNum            | plntCd | altBomNum | prdntVrsnNum | valToDt  | valFromDt |
      | CONS_LATAM | 000000000000000023 | BR12   | 01        | V01          | 99991231 | 20001019  |
      | CONS_LATAM | 000000000000000024 | BR12   | 01        | V01          | 99991231 | 20001019  |
      | CONS_LATAM | 000000000000000025 | BR12   | 01        | V01          | 99991231 | 20001019  |
      | HNKS_THGN  | 000000000000000026 | BR12   | 01        | V01          | 99991231 | 20001019  |
      | CONS_LATAM | 000000000000000028 | BR12   | 01        | V01          | 99991231 | 20001019  |
      | CONS_LATAM | 000000000000000029 | BR12   | 01        | V01          | 99991231 | 20001019  |
      | CONS_LATAM | 000000000000000030 | BR12   | 02        | V01          | 99991231 | 20001019  |
      #MATL_PROD_VERSN-srcSysCd = MATL_MFG_RTNG-srcSysCd            |
      | CONS_LATAM | 000000000000000031 | BR12   | 01        | V01          | 99991231 | 20001019  |
      #MATL_PROD_VERSN-matlNum= MATL_MFG_RTNG-matlNum                   |
      | CONS_LATAM | 000000000000000032 | BR12   | 01        | V01          | 99991231 | 20001019  |
      #MATL_PROD_VERSN-plntCd= MATL_MFG_RTNG-plntCd       |
      | CONS_LATAM | 000000000000000034 | BR12   | 01        | V01          | 99991231 | 20001019  |
      | CONS_LATAM | 000000000000000035 | BR12   | 01        | V01          | 99991231 | 20001019  |
      | CONS_LATAM | 000000000000000036 | BR12   | 01        | V01          | 99991231 | 20001019  |
      | CONS_LATAM | 000000000000000037 | BR12   | 01        | V01          | 99991231 | 20001019  |
      | CONS_LATAM | 000000000000000038 | BR12   | 01        | V01          | 99991231 | 20001019  |
      | CONS_LATAM | 000000000000000039 | BR12   | 01        | V01          | 99991231 | 20001019  |
      | CONS_LATAM | 000000000000000040 | BR12   | 01        | V01          | 99991231 | 20001019  |
      | CONS_LATAM | 000000000000000041 | BR12   | 01        | V01          | 99991231 | 20001019  |
      | CONS_LATAM | 000000000000000042 | BR12   | 01        | V01          | 99991231 | 20001019  |
    And I wait "/edm/matl_prod_versn" Async Queue complete

    Given I import "/edm/matl_mfg_rtng" by keyFields "srcSysCd,matlNum,plntCd,rtngTypCd,rntgGrpCd,rntgGrpCntrNbr"
      | srcSysCd   | matlNum            | plntCd | rtngTypCd | rntgGrpCd | rntgGrpCntrNbr |
      | CONS_LATAM | 000000000000000023 | BR12   | N         | 50001700  | 01             |
      | CONS_LATAM | 000000000000000024 | BR12   | N         | 50001701  | 01             |
      | CONS_LATAM | 000000000000000025 | BR12   | N         | 50001702  | 01             |
      | HNKS_THGN  | 000000000000000026 | BR12   | N         | 50001703  | 01             |
      | CONS_LATAM | 000000000000000028 | BR12   | N         | 50001704  | 01             |
      | CONS_LATAM | 000000000000000029 | BR12   | N         | 50001705  | 01             |
      | CONS_LATAM | 000000000000000030 | BR12   | N         | 50001706  | 01             |
      | HNKS_THGNR | 000000000000000031 | BR12   | N         | 50001707  | 01             |
      | CONS_LATAM | 000000000000000033 | BR12   | N         | 50001708  | 01             |
      | CONS_LATAM | 000000000000000034 | BR11   | N         | 50001709  | 01             |
      #MATL_MFG_RTNG-srcSysCd = MFG_RTNG_ITM_NDE-srcSysCd
      | CONS_LATAM | 000000000000000035 | BR12   | N         | 50001710  | 01             |
      #MATL_MFG_RTNG-rtngTypCd= MFG_RTNG_ITM_NDE-rtngTypCd
      | CONS_LATAM | 000000000000000036 | BR12   | N         | 50001711  | 01             |
      #MATL_MFG_RTNG-rntgGrpCd  = MFG_RTNG_ITM_NDE-rtngGrpCd
      | CONS_LATAM | 000000000000000037 | BR12   | N         | 50001712  | 01             |
      #MATL_MFG_RTNG-rntgGrpCntrNbr= MFG_RTNG_ITM_NDE-rtngGrpCntrNbr
      | CONS_LATAM | 000000000000000038 | BR12   | N         | 50001714  | 01             |
      | CONS_LATAM | 000000000000000039 | BR12   | N         | 50001715  | 01             |
      | CONS_LATAM | 000000000000000040 | BR12   | N         | 50001716  | 01             |
      | CONS_LATAM | 000000000000000041 | BR12   | N         | 50001717  | 01             |
      | CONS_LATAM | 000000000000000042 | BR12   | N         | 50001718  | 01             |

    And I wait "/edm/matl_mfg_rtng" Async Queue complete

    Given I import "/edm/mfg_rtng_itm_nde" by keyFields "srcSysCd,rtngTypCd,rtngGrpCd,rtngGrpCntrNbr"
      | srcSysCd   | rtngTypCd | rtngGrpCd | rtngGrpCntrNbr | rtngNdeNum |
      | CONS_LATAM | N         | 50001700  | 01             | 00000001   |
      | CONS_LATAM | N         | 50001701  | 01             | 00000002   |
      | CONS_LATAM | N         | 50001702  | 01             | 00000003   |
      | HNKS_THGN  | N         | 50001703  | 01             | 00000004   |
      | CONS_LATAM | N         | 50001704  | 01             | 00000005   |
      | CONS_LATAM | N         | 50001705  | 01             | 00000006   |
      | CONS_LATAM | N         | 50001706  | 01             | 00000007   |
      | HNKS_THGNR | N         | 50001707  | 01             | 00000008   |
      | CONS_LATAM | N         | 50001708  | 01             | 00000009   |
      | CONS_LATAM | N         | 50001709  | 01             | 00000010   |
      | HNKS_THGNR | N         | 50001710  | 01             | 00000011   |
      | CONS_LATAM | M         | 50001711  | 01             | 00000012   |
      | CONS_LATAM | M         | 50001713  | 01             | 00000013   |
      | CONS_LATAM | N         | 50001714  | 02             | 00000014   |
      #MFG_RTNG_ITM_NDE-srcSysCd  =   MFG_RTNG_ITM-srcSysCd
      | CONS_LATAM | N         | 50001715  | 01             | 00000015   |
      #MFG_RTNG_ITM_NDE-rtngTypCd= MFG_RTNG_ITM-rtngTypCd
      | CONS_LATAM | N         | 50001716  | 01             | 00000016   |
      #MFG_RTNG_ITM_NDE-rtngGrpCd = MFG_RTNG_ITM-rtngGrpCd AND
      | CONS_LATAM | N         | 50001717  | 01             | 00000017   |
      #MFG_RTNG_ITM_NDE-rtngNdeNum  = MFG_RTNG_ITM-rtngItmNum
      | CONS_LATAM | N         | 50001718  | 01             | 00000018   |
    And I wait "/edm/mfg_rtng_itm_nde" Async Queue complete

    Given I import "/edm/mfg_rtng_itm" by keyFields "srcSysCd,rtngTypCd,rtngGrpCd,rtngItmNum"
      | srcSysCd   | rtngTypCd | rtngGrpCd | rtngItmNum | operNum |
      | CONS_LATAM | N         | 50001700  | 00000001   | 0010    |
      | CONS_LATAM | N         | 50001701  | 00000002   | 0010    |
      | CONS_LATAM | N         | 50001702  | 00000003   | 0010    |
      | HNKS_THGN  | N         | 50001703  | 00000004   | 0010    |
      | CONS_LATAM | N         | 50001704  | 00000005   | 0010    |
      | CONS_LATAM | N         | 50001705  | 00000006   | 0010    |
      | CONS_LATAM | N         | 50001706  | 00000007   | 0010    |
      | HNKS_THGNR | N         | 50001707  | 00000008   | 0010    |
      | CONS_LATAM | N         | 50001708  | 00000009   | 0010    |
      | CONS_LATAM | N         | 50001709  | 00000010   | 0010    |
      | HNKS_THGNR | N         | 50001710  | 00000011   | 0010    |
      | CONS_LATAM | M         | 50001711  | 00000012   | 0010    |
      | CONS_LATAM | M         | 50001713  | 00000013   | 0010    |
      | CONS_LATAM | N         | 50001714  | 00000014   | 0010    |
      | HNKS_THGNR | N         | 50001715  | 00000015   | 0010    |
      | CONS_LATAM | M         | 50001716  | 00000016   | 0010    |
      | CONS_LATAM | N         | 50001718  | 00000018   | 0010    |
    And I wait "/edm/mfg_rtng_itm" Async Queue complete

    Given I import "/edm/bom_hdr" by keyFields "srcSysCd,bomNum,altBomNum,bomCatCd"
      | srcSysCd   | bomNum   | altBomNum | bomCatCd | bomVldFromDt | bomVld_ToDt |
      | CONS_LATAM | 00000001 | 01        | M        | 1998/01/01   | 9999/12/31  |
      | CONS_LATAM | 00000002 | 01        | M        | 1998/01/01   | 9999/12/31  |
      | CONS_LATAM | 00000003 | 01        | M        | 1998/01/01   | 9999/12/31  |
      | CONS_LATAM | 00000005 | 01        | M        | 1998/01/01   | 9999/12/31  |
      | CONS_LATAM | 00000006 | 01        | M        | 1998/01/01   | 9999/12/31  |
      | CONS_LATAM | 00000007 | 01        | M        | 1998/01/01   | 9999/12/31  |
      | CONS_LATAM | 00000008 | 01        | M        | 1998/01/01   | 9999/12/31  |
      | CONS_LATAM | 00000009 | 01        | M        | 1998/01/01   | 9999/12/31  |
      | CONS_LATAM | 00000010 | 01        | M        | 1998/01/01   | 9999/12/31  |
      | CONS_LATAM | 00000011 | 01        | M        | 1998/01/01   | 9999/12/31  |
      | CONS_LATAM | 00000012 | 01        | M        | 1998/01/01   | 9999/12/31  |
      | CONS_LATAM | 00000013 | 01        | M        | 1998/01/01   | 9999/12/31  |
      | CONS_LATAM | 00000014 | 01        | M        | 1998/01/01   | 9999/12/31  |
      | CONS_LATAM | 00000015 | 01        | M        | 1998/01/01   | 9999/12/31  |
      | CONS_LATAM | 00000016 | 01        | M        | 1998/01/01   | 9999/12/31  |
      | CONS_LATAM | 00000017 | 01        | M        | 1998/01/01   | 9999/12/31  |
      | CONS_LATAM | 00000018 | 01        | M        | 1998/01/01   | 9999/12/31  |
      | CONS_LATAM | 00000019 | 01        | M        | 1998/01/01   | 9999/12/31  |
    And I wait "/edm/bom_hdr" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmBom.xml" and execute file "jar/pangea-view.jar"
    Then A file is found on sink application with name "GDMBOM_MASTER.tsv"
    Then I check file data for filename "GDMBOM_MASTER.tsv" by keyFields "bomId"
#    Then I check region data "/omp/gdm_bom" by keyFields "bomId"
      | bomId                               | active | activeFCTERP | activeOPRERP | activeSOPERP | comments | endEff              | locationId      | startEff            |
      | V01/42/CONS_LATAM/BR12/19/01/1/0010 | YES    | NO           | YES          | NO           |          | 2998/12/31 23:59:59 | CONS_LATAM_BR12 | 2000/10/19 00:00:00 |
      | V01/24/CONS_LATAM/BR12/2/01/1/0010  | YES    | NO           | YES          | NO           |          | 2998/12/31 23:59:59 | CONS_LATAM_BR12 | 2000/10/19 00:00:00 |
      | V01/25/CONS_LATAM/BR12/3/01/1/0010  | YES    | NO           | YES          | NO           |          | 2998/12/31 23:59:59 | CONS_LATAM_BR12 | 2000/10/19 00:00:00 |
      | V01/23/CONS_LATAM/BR12/1/01/1/0010  | YES    | NO           | YES          | NO           |          | 2998/12/31 23:59:59 | CONS_LATAM_BR12 | 2000/10/19 00:00:00 |


  @Scenario2
  Scenario: check rule T3 & check rule T4  & check rule T5 & check rule C1
    Given I import "/edm/matl_bom" by keyFields "srcSysCd,matlNum,plntCd,altBomNum,bomNum,bomUsgCd"
      | srcSysCd   | matlNum            | plntCd | altBomNum | bomNum   | bomUsgCd |
      | CONS_LATAM | 000000000000000023 | BR12   | 01        | 00000001 | 1        |
      | CONS_LATAM | 000000000000000024 | BR12   | 01        | 00000002 | 1        |
    And I wait "/edm/matl_bom" Async Queue complete

    Given I import "/plan/cns_plan_parameter" by keyFields "sourceSystem,dataObject,attribute"
      | sourceSystem | dataObject  | attribute  | parameterValue |
      | CONS_LATAM   | SEND_TO_OMP | CONS_LATAM | 11             |
    And I wait "/plan/cns_plan_parameter" Async Queue complete

    Given I import "/edm/matl_prod_versn" by keyFields "srcSysCd,matlNum,plntCd,prdntVrsnNum"
      | srcSysCd   | matlNum            | plntCd | altBomNum | prdntVrsnNum | valToDt  | valFromDt |
      | CONS_LATAM | 000000000000000023 | BR12   | 01        | V01          | 99991231 | 20001019  |
      | CONS_LATAM | 000000000000000024 | BR12   | 01        | V01          | 99991231 | 20001019  |
    And I wait "/edm/matl_prod_versn" Async Queue complete

    Given I import "/edm/matl_mfg_rtng" by keyFields "srcSysCd,matlNum,plntCd,rtngTypCd,rntgGrpCd,rntgGrpCntrNbr"
      | srcSysCd   | matlNum            | plntCd | rtngTypCd | rntgGrpCd | rntgGrpCntrNbr |
      | CONS_LATAM | 000000000000000023 | BR12   | N         | 50001700  | 01             |
      | CONS_LATAM | 000000000000000024 | BR12   | N         | 50001701  | 01             |
    And I wait "/edm/matl_mfg_rtng" Async Queue complete

    Given I import "/edm/mfg_rtng_itm_nde" by keyFields "srcSysCd,rtngTypCd,rtngGrpCd,rtngGrpCntrNbr"
      | srcSysCd   | rtngTypCd | rtngGrpCd | rtngGrpCntrNbr | rtngNdeNum |
      | CONS_LATAM | N         | 50001700  | 01             | 00000001   |
      | CONS_LATAM | N         | 50001701  | 01             | 00000002   |
    And I wait "/edm/mfg_rtng_itm_nde" Async Queue complete

    Given I import "/edm/mfg_rtng_itm" by keyFields "srcSysCd,rtngTypCd,rtngGrpCd,rtngItmNum"
      | srcSysCd   | rtngTypCd | rtngGrpCd | rtngItmNum | operNum |
      | CONS_LATAM | N         | 50001700  | 00000001   | 0010    |
      | CONS_LATAM | N         | 50001701  | 00000002   | 0010    |
    And I wait "/edm/mfg_rtng_itm" Async Queue complete

    Given I import "/edm/bom_hdr" by keyFields "srcSysCd,bomNum,altBomNum,bomCatCd"
      | srcSysCd   | bomNum   | altBomNum | bomCatCd | bomVldFromDt | bomVld_ToDt |
      | CONS_LATAM | 00000001 | 01        | M        | 1998/01/01   | 9999/12/31  |
      | CONS_LATAM | 00000002 | 01        | M        | 1998/01/02   | 9999/12/31  |
    And I wait "/edm/bom_hdr" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmBom.xml" and execute file "jar/pangea-view.jar"
    Then A file is found on sink application with name "GDMBOM_MASTER.tsv"
    Then I check file data for filename "GDMBOM_MASTER.tsv" by keyFields "bomId"
#    Then I check region data "/omp/gdm_bom" by keyFields "bomId"
      | bomId                              | active | activeFCTERP | activeOPRERP | activeSOPERP | comments | endEff              | locationId      | startEff            |
      | V01/24/CONS_LATAM/BR12/2/01/1/0010 | YES    | NO           | YES          | NO           |          | 2998/12/31 23:59:59 | CONS_LATAM_BR12 | 2000/10/19 00:00:00 |
      | V01/23/CONS_LATAM/BR12/1/01/1/0010 | YES    | NO           | YES          | NO           |          | 2998/12/31 23:59:59 | CONS_LATAM_BR12 | 2000/10/19 00:00:00 |


  @Scenario3
  Scenario: check rule T2
    Given I import "/edm/matl_bom" by keyFields "srcSysCd,matlNum,plntCd,altBomNum,bomNum,bomUsgCd"
      | srcSysCd   | matlNum            | plntCd | altBomNum | bomNum   | bomUsgCd |
      | CONS_LATAM | 000000000000000023 | BR12   | 01        | 00000001 | 1        |
      | CONS_LATAM | 000000000000000024 | BR12   | 01        | 00000002 | 1        |
    And I wait "/edm/matl_bom" Async Queue complete

    Given I import "/plan/cns_plan_parameter" by keyFields "sourceSystem,dataObject,attribute"
      | sourceSystem | dataObject  | attribute  | parameterValue |
      | CONS_LATAM   | SEND_TO_OMP | CONS_LATAM | 11             |
    And I wait "/plan/cns_plan_parameter" Async Queue complete

    Given I import "/edm/matl_prod_versn" by keyFields "srcSysCd,matlNum,plntCd,prdntVrsnNum"
      | srcSysCd   | matlNum            | plntCd | altBomNum | prdntVrsnNum | valToDt  | valFromDt |
      | CONS_LATAM | 000000000000000023 | BR12   | 01        | V01          | 20231112 | 20001019  |
      | CONS_LATAM | 000000000000000024 | BR12   | 01        | V01          | 20451118 | 20001019  |
    And I wait "/edm/matl_prod_versn" Async Queue complete

    Given I import "/edm/matl_mfg_rtng" by keyFields "srcSysCd,matlNum,plntCd,rtngTypCd,rntgGrpCd,rntgGrpCntrNbr"
      | srcSysCd   | matlNum            | plntCd | rtngTypCd | rntgGrpCd | rntgGrpCntrNbr |
      | CONS_LATAM | 000000000000000023 | BR12   | N         | 50001700  | 01             |
      | CONS_LATAM | 000000000000000024 | BR12   | N         | 50001701  | 01             |
    And I wait "/edm/matl_mfg_rtng" Async Queue complete

    Given I import "/edm/mfg_rtng_itm_nde" by keyFields "srcSysCd,rtngTypCd,rtngGrpCd,rtngGrpCntrNbr"
      | srcSysCd   | rtngTypCd | rtngGrpCd | rtngGrpCntrNbr | rtngNdeNum |
      | CONS_LATAM | N         | 50001700  | 01             | 00000001   |
      | CONS_LATAM | N         | 50001701  | 01             | 00000002   |
    And I wait "/edm/mfg_rtng_itm_nde" Async Queue complete

    Given I import "/edm/mfg_rtng_itm" by keyFields "srcSysCd,rtngTypCd,rtngGrpCd,rtngItmNum"
      | srcSysCd   | rtngTypCd | rtngGrpCd | rtngItmNum | operNum |
      | CONS_LATAM | N         | 50001700  | 00000001   | 0010    |
      | CONS_LATAM | N         | 50001701  | 00000002   | 0010    |
    And I wait "/edm/mfg_rtng_itm" Async Queue complete

    Given I import "/edm/bom_hdr" by keyFields "srcSysCd,bomNum,altBomNum,bomCatCd"
      | srcSysCd   | bomNum   | altBomNum | bomCatCd | bomVldFromDt | bomVld_ToDt |
      | CONS_LATAM | 00000001 | 01        | M        | 2023/12/15   | 2023/10/13  |
      | CONS_LATAM | 00000002 | 01        | M        | 2045/12/19   | 2045/07/12  |
    And I wait "/edm/bom_hdr" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmBom.xml" and execute file "jar/pangea-view.jar"
    Then A file is found on sink application with name "GDMBOM_MASTER.tsv"
    Then I check file data for filename "GDMBOM_MASTER.tsv" by keyFields "bomId"
#    Then I check region data "/omp/gdm_bom" by keyFields "bomId"
      | bomId                              | active | activeFCTERP | activeOPRERP | activeSOPERP | comments | endEff              | locationId      | startEff            |
      | V01/24/CONS_LATAM/BR12/2/01/1/0010 | YES    | NO           | YES          | NO           |          | 2045/07/12 00:00:00 | CONS_LATAM_BR12 | 2045/12/19 00:00:00 |
      | V01/23/CONS_LATAM/BR12/1/01/1/0010 | YES    | NO           | YES          | NO           |          | 2023/10/13 00:00:00 | CONS_LATAM_BR12 | 2023/12/15 00:00:00 |

#
  @Scenario4
  Scenario: check full rule
    Given I import "/edm/matl_bom" by keyFields "srcSysCd,matlNum,plntCd,altBomNum,bomNum,bomUsgCd"
      | srcSysCd   | matlNum            | plntCd | altBomNum | bomNum   | bomUsgCd |
      | CONS_LATAM | 000000000000000023 | BR12   | 01        | 00000001 | 1        |
      | CONS_LATAM | 000000000000000024 | BR12   | 01        | 00000002 | 1        |
      | CONS_LATAM | 000000000000000025 | BR12   | 01        | 00000003 | 1        |
      | CONS_LATAM | 000000000000000026 | BR12   | 01        | 00000004 | 1        |
    And I wait "/edm/matl_bom" Async Queue complete

    Given I import "/plan/cns_plan_parameter" by keyFields "sourceSystem,dataObject,attribute"
      | sourceSystem | dataObject  | attribute  | parameterValue |
      | CONS_LATAM   | SEND_TO_OMP | CONS_LATAM | 11             |
      | CONS_LATAM   | SEND_TO_GDM | CONS_LATAM | 11             |
    And I wait "/plan/cns_plan_parameter" Async Queue complete

    Given I import "/edm/matl_prod_versn" by keyFields "srcSysCd,matlNum,plntCd,prdntVrsnNum"
      | srcSysCd   | matlNum            | plntCd | altBomNum | prdntVrsnNum | valToDt  | valFromDt |
      | CONS_LATAM | 000000000000000023 | BR12   | 01        | V01          | 20231112 | 20001019  |
      | CONS_LATAM | 000000000000000024 | BR12   | 01        | V01          | 20451118 | 20001019  |
      | CONS_LATAM | 000000000000000025 | BR12   | 01        | V01          | 20231112 | 20001019  |
      | CONS_LATAM | 000000000000000026 | BR12   | 01        | V01          | 20451118 | 20001019  |
    And I wait "/edm/matl_prod_versn" Async Queue complete

    Given I import "/edm/matl_mfg_rtng" by keyFields "srcSysCd,matlNum,plntCd,rtngTypCd,rntgGrpCd,rntgGrpCntrNbr"
      | srcSysCd   | matlNum            | plntCd | rtngTypCd | rntgGrpCd | rntgGrpCntrNbr |
      | CONS_LATAM | 000000000000000023 | BR12   | N         | 50001700  | 01             |
      | CONS_LATAM | 000000000000000024 | BR12   | N         | 50001701  | 01             |
      | CONS_LATAM | 000000000000000025 | BR12   | N         | 50001702  | 01             |
      | CONS_LATAM | 000000000000000026 | BR12   | N         | 50001703  | 01             |
    And I wait "/edm/matl_mfg_rtng" Async Queue complete

    Given I import "/edm/mfg_rtng_itm_nde" by keyFields "srcSysCd,rtngTypCd,rtngGrpCd,rtngGrpCntrNbr"
      | srcSysCd   | rtngTypCd | rtngGrpCd | rtngGrpCntrNbr | rtngNdeNum |
      | CONS_LATAM | N         | 50001700  | 01             | 00000001   |
      | CONS_LATAM | N         | 50001701  | 01             | 00000002   |
      | CONS_LATAM | N         | 50001702  | 01             | 00000003   |
      | CONS_LATAM | N         | 50001703  | 01             | 00000004   |
    And I wait "/edm/mfg_rtng_itm_nde" Async Queue complete

    Given I import "/edm/mfg_rtng_itm" by keyFields "srcSysCd,rtngTypCd,rtngGrpCd,rtngItmNum"
      | srcSysCd   | rtngTypCd | rtngGrpCd | rtngItmNum | operNum |
      | CONS_LATAM | N         | 50001700  | 00000001   | 0010    |
      | CONS_LATAM | N         | 50001701  | 00000002   | 0010    |
      | CONS_LATAM | N         | 50001702  | 00000003   | 0010    |
      | CONS_LATAM | N         | 50001703  | 00000004   | 0010    |
    And I wait "/edm/mfg_rtng_itm" Async Queue complete

    Given I import "/edm/bom_hdr" by keyFields "srcSysCd,bomNum,altBomNum,bomCatCd"
      | srcSysCd   | bomNum   | altBomNum | bomCatCd | bomVldFromDt | bomVld_ToDt |
      | CONS_LATAM | 00000001 | 01        | M        | 2023/12/15   | 2023/10/13  |
      | CONS_LATAM | 00000002 | 01        | M        | 2045/12/19   | 2045/07/12  |
      | CONS_LATAM | 00000003 | 01        | M        | 2023/12/15   | 2023/10/13  |
      | CONS_LATAM | 00000004 | 01        | M        | 2045/12/19   | 2045/07/12  |

    And I wait "/edm/bom_hdr" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmBom.xml" and execute file "jar/pangea-view.jar"
    Then A file is found on sink application with name "GDMBOM_MASTER.tsv"
    Then I check file data for filename "GDMBOM_MASTER.tsv" by keyFields "bomId"
#    Then I check region data "/omp/gdm_bom" by keyFields "bomId"
      | bomId                              | active | activeFCTERP | activeOPRERP | activeSOPERP | comments | endEff              | locationId      | startEff            |
      | V01/24/CONS_LATAM/BR12/2/01/1/0010 | YES    | NO           | YES          | NO           |          | 2045/07/12 00:00:00 | CONS_LATAM_BR12 | 2045/12/19 00:00:00 |
      | V01/26/CONS_LATAM/BR12/4/01/1/0010 | YES    | NO           | YES          | NO           |          | 2045/07/12 00:00:00 | CONS_LATAM_BR12 | 2045/12/19 00:00:00 |
      | V01/25/CONS_LATAM/BR12/3/01/1/0010 | YES    | NO           | YES          | NO           |          | 2023/10/13 00:00:00 | CONS_LATAM_BR12 | 2023/12/15 00:00:00 |
      | V01/23/CONS_LATAM/BR12/1/01/1/0010 | YES    | NO           | YES          | NO           |          | 2023/10/13 00:00:00 | CONS_LATAM_BR12 | 2023/12/15 00:00:00 |

    And I delete the test data

    And I will remove all data with region "/omp/gdm_bom"

    And I will remove all data with region "/plan/edm_failed_data"

  @Scenario5
  Scenario: merge file

    And I execute xd job to merge file "GDMBOM_*" to "GDMBOM.tsv" by keyFields "bomId"