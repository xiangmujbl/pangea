@pangea_test @AEAZ-3272
Feature: EDMBomItemNode AEAZ-3272

  Scenario: Full Load curation

    Given I import "/project_one/mast" by keyFields "mandt,matnr,stlal,stlan,stlnr,werks"
      | mandt | matnr                 | stlal | stlan | stlnr    | werks | losvn | losbs | andat    | aedat    |
      | 120   | 000000000000000001_T1 | 01    | 1     | 00003097 | BR12  | 0.000 | 0.000 | 20020521 | 00000000 |
      | 120   | 000000000000000002_T1 | 01    | 1     | 00003097 | BR12  | 0.000 | 0.000 | 20020521 | 00000000 |

    And I wait "/project_one/mast" Async Queue complete

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | localSourceSystemName | sourceSystem | sourceSystemName   |
      | project_one       | Project One           | CONS_LATAM   | Consumer Latam Ent |
      | ems               | EMS                   | EMS          | ems                |

    And I wait "/edm/source_system_v1" Async Queue complete

    When I submit task with xml file "xml/edm/EDMMatlBom.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/matl_bom" by keyFields "srcSysCd,matlNum,plntCd,bomUsgCd,bomNum,altBomNum"
      | srcSysCd   | matlNum               | plntCd | bomUsgCd | bomNum   | altBomNum | fromLotSizeQty | toLotSizeQty | crtDttm  | chgDttm  |
      | CONS_LATAM | 000000000000000001_T1 | BR12   | 1        | 00003097 | 01        | 0.000          | 0.000        | 20020521 | 00000000 |
      | CONS_LATAM | 000000000000000002_T1 | BR12   | 1        | 00003097 | 01        | 0.000          | 0.000        | 20020521 | 00000000 |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/project_one/mast" and "/edm/matl_bom,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/edm/matl_bom"

    And I will remove all data with region "/plan/edm_failed_data"

