@pangea_test @AEAZ-7478
Feature: EDMBomHdr AEAZ-7478

  Scenario: Full Load curation

#    Given I import "/project_one/stko" by keyFields "mandt,stlty,stlnr,stlal,stkoz"
#      | mandt | stlty | stlnr    | stlal | stkoz    | datuv    | aennr        | loekz | vgkzl | andat    | aedat    | bmein | bmeng | stktx         | stlst |
#      | 120   | M     | 00015485 | 01    | 00000001 | 20061027 |              |       | 0     | 20061027 | 20061101 | EN    | 1000  | ALTERNATIVA 1 | 01    |
#      | 120   | M     | 00015485 | 01    | 00000020 | 20061101 | 500000000562 |       | 1     | 20061101 | 20061122 | EN    | 40000 | ALTERNATIVA 1 | 01    |
#      | 120   | M     | 00015485 | 01    | 00000021 | 20061121 | 500000000588 |       | 20    | 20061122 | 0        | EN    | 40000 | ALTERNATIVA 1 | 01    |
#      | 120   | M     | 00015485 | 02    | 00000031 | 20061121 | 500000000588 |       | 0     | 20061122 | 0        | EN    | 40000 |               | 01    |
#      | 120   | M     | 00015485 | 03    | 00000060 | 20070719 |              |       | 0     | 20070719 | 0        | EN    | 40000 | ALTERNATIVA 3 | 01    |
#      | 120   | M     | 00015485 | 04    | 00000070 | 20070823 |              |       | 0     | 20070823 | 0        | EN    | 1000  |               | 01    |
#    And I wait "/project_one/stko" Async Queue complete
#
#    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
#      | localSourceSystem | localSourceSystemName | sourceSystem | sourceSystemName   |
#      | project_one       | Project One           | CONS_LATAM   | Consumer Latam Ent |
#      | ems               | EMS                   | EMS          | ems                |
#
#    And I wait "/edm/source_system_v1" Async Queue complete

    When I submit task with xml file "xml/edm/EDMBomHdr.xml" and execute file "jar/pangea-view.jar"

#    Then I check region data "/edm/bom_hdr" by keyFields "srcSysCd,bomCatCd,bomNum,altBomNum,bomCntrNbr"
#      | srcSysCd   | bomCatCd | bomNum   | altBomNum | bomCntrNbr | bomVldFromDt | chgNum       | delInd | prvCntrNbr | crtDttm  | chgDttm  | bomUomCd | bomBaseQty | bomTxt        | bomStsCd | bomVld_ToDt |
#      | CONS_LATAM | M        | 00015485 | 01        | 00000001   | 20061027     |              |        | 0          | 20061027 | 20061101 | EN       | 1000       | ALTERNATIVA 1 | 01       | 20061031    |
#      | CONS_LATAM | M        | 00015485 | 01        | 00000020   | 20061101     | 500000000562 |        | 1          | 20061101 | 20061122 | EN       | 40000      | ALTERNATIVA 1 | 01       | 20061120    |
#      | CONS_LATAM | M        | 00015485 | 01        | 00000021   | 20061121     | 500000000588 |        | 20         | 20061122 | 0        | EN       | 40000      | ALTERNATIVA 1 | 01       | 99991231    |
#      | CONS_LATAM | M        | 00015485 | 02        | 00000031   | 20061121     | 500000000588 |        | 0          | 20061122 | 0        | EN       | 40000      |               | 01       | 99991231    |
#      | CONS_LATAM | M        | 00015485 | 03        | 00000060   | 20070719     |              |        | 0          | 20070719 | 0        | EN       | 40000      | ALTERNATIVA 3 | 01       | 99991231    |
#      | CONS_LATAM | M        | 00015485 | 04        | 00000070   | 20070823     |              |        | 0          | 20070823 | 0        | EN       | 1000       |               | 01       | 99991231    |
#    And I delete the test data
#
#    And I will remove all data with region "/edm/bom_hdr"
#
#    And I will remove all data with region "/plan/edm_failed_data"

