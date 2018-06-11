@pangea_test @AEAZ-4244
Feature: E.2.1.5 EDMProductionVersion - Curation AEAZ-4244

  As a Data user,
  I want EDG to curate ProductionVersion raw data from ECC LATAM
  so that there is an Enterprise Data Model that contains ProductionVersion supply chain data consistent with Enterprise standards, and ready for consumption by reports and/or cross-functional applications.

  Scenario: Full Load curation


    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | sourceSystem |
      | project_one       | CONS_LATAM   |
      | EMS               | EMS          |
    And I wait "/edm/source_system_v1" Async Queue complete

    Given I import "/project_one/mkal" by keyFields "mandt,matnr,verid,werks"
      | mandt | matnr              | werks | verid | adatu  | bdatu  | stlal | stlan | plnty | plnnr   | alnal | beskz | sobsl | losgr | mdv01 | text1                     | verto | bstmi | bstma | alort | prfgF | prdat   | mksp | ucmat |
      | 120   | 000000000000000001 | BR02  | V01   |20181001|99991231|01     |  1     |N     |50001721|01      |       |       |0.000  |       |Grupo de Roteiro * 50005955|       |0.000  |0.000  |       |1      |20190521 |      |       |
      | 120   | 000000000000000002 | BR02  | V01   |20181002|99991231|01     |  1     |N     |50001721|01      |       |       |0.000  |       |Grupo de Roteiro * 50005955|       |0.000  |0.000  |       |1      |20190521 |      |       |
      | 120   | 000000000000000003 | BR02  | V01   |20181003|99991231|01     |  1     |N     |50001721|01      |       |       |0.000  |       |Grupo de Roteiro * 50005955|       |0.000  |0.000  |       |1      |20190521 |      |       |
      | 120   | 000000000000000004 | BR02  | V01   |20181004|99991231|01     |  1     |N     |50001721|01      |       |       |0.000  |       |Grupo de Roteiro * 50005955|       |0.000  |0.000  |       |1      |20190521 |      |       |
      | 120   | 000000000000000005 | BR02  | V01   |20181005|99991231|01     |  1     |N     |50001721|01      |       |       |0.000  |       |Grupo de Roteiro * 50005955|       |0.000  |0.000  |       |1      |20190521 |      |       |
      | 120   | 000000000000000006 | BR02  | V01   |20181006|99991231|01     |  1     |N     |50001721|01      |       |       |0.000  |       |Grupo de Roteiro * 50005955|       |0.000  |0.000  |       |1      |20190521 |      |       |

    And I wait "/project_one/mkal" Async Queue complete

    Given I import "/project_one/mkal_aend" by keyFields "mandt,matnr,verid,werks"
      | mandt | matnr              | werks | verid    | andat    | aedat |
      | 120   | 000000000000000001 | BR02  | V01      |20181001 | 20181002 |
      | 120   | 000000000000000009 | BR02  | V01      |20181002 | 20181003 |
      | 120   | 000000000000000003 | BR03  | V01      |20181003 | 20181004 |
      | 120   | 000000000000000004 | BR02  | V04      |20181004 | 20181005 |
      | 120   | 000000000000000005 | BR02  | V01      |20181005 | 20181006 |
      | 120   | 000000000000000006 | BR02  | V01      |20181006 | 20181007 |

    And I wait "/project_one/mkal_aend" Async Queue complete


    When I submit task with xml file "xml/edm/EDMMatlProdVersn.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/matl_prod_versn" by keyFields "srcSysCd,matlNum,plntCd,prdntVrsnNum"
      | srcSysCd   | matlNum            | plntCd | prdntVrsnNum | valFromDt | valToDt  | altBomNum | bomUsgCd | rtngTypCd | rtngGrpCd | rtngGrpCntrNum | prcrmntTypCd | spPrcrmntTypCd | cstLtSzQty | mfgLineCd | prdVrsnDesc                 | dstrbtnKeyCd | frmLtSzQty | toLtSzQty | sLocCd | chckInd | chckDt   | lckInd | orgBtchInd | crtDttm  | chgDttm  |
      | CONS_LATAM | 000000000000000001 | BR02   | V01          | 20181001  | 99991231 | 01        | 1        | N         | 50001721  | 01             |              |                | 0.000      |           | Grupo de Roteiro * 50005955 |              | 0.000      | 0.000     |        | 1       | 20190521 |        |            | 20181001 | 20181002 |
      | CONS_LATAM | 000000000000000005 | BR02   | V01          | 20181005  | 99991231 | 01        | 1        | N         | 50001721  | 01             |              |                | 0.000      |           | Grupo de Roteiro * 50005955 |              | 0.000      | 0.000     |        | 1       | 20190521 |        |            | 20181005 | 20181006 |
      | CONS_LATAM | 000000000000000006 | BR02   | V01          | 20181006  | 99991231 | 01        | 1        | N         | 50001721  | 01             |              |                | 0.000      |           | Grupo de Roteiro * 50005955 |              | 0.000      | 0.000     |        | 1       | 20190521 |        |            | 20181006 | 20181007 |

  Scenario: delete all test data

    And I delete the test data

    And I will remove all data with region "/edm/matl_prod_versn"

    And I will remove all data with region "/project_one/mkal"

    And I will remove all data with region "/project_one/mkal_aehd"

    And I will remove all data with region "/edm/source_system_v1"

