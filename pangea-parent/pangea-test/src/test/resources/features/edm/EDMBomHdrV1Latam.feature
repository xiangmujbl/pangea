@pangea_test @AEAZ-3269
Feature: EDMBomHdr AEAZ-3269

  Scenario: Full Load curation

    Given I import "/project_one/stko" by keyFields "mandt,stlty,stlnr,stlal,stkoz"
      | mandt  | stlty  | stlnr  | stlal  | stkoz | datuv     | aennr  | loekz  | vgkzl  | andat  | aedat  | bmein  | bmeng  | stktx  | stlst  |
      | mandt1 | stlty1 | stlnr1 | stlal1 | 1     | 2017/1/1  | aennr1 | loekz1 | vgkzl1 | andat1 | aedat1 | bmein1 | bmeng1 | stktx1 | stlst1 |
      | mandt2 | stlty2 | stlnr1 | stlal1 | 2     | 2017/8/25 | aennr2 | loekz2 | vgkzl2 | andat2 | aedat2 | bmein2 | bmeng2 | stktx2 | stlst2 |
      | mandt3 | stlty3 | stlnr1 | stlal1 | 3     | 2017/9/24 | aennr3 | loekz3 | vgkzl3 | andat3 | aedat3 | bmein3 | bmeng3 | stktx3 | stlst3 |
      | mandt4 | stlty4 | stlnr1 | stlal2 | 4     | 2017/9/35 | aennr4 | loekz4 | vgkzl4 | andat4 | aedat4 | bmein4 | bmeng4 | stktx4 | stlst4 |
    And I wait "/project_one/stko" Async Queue complete

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | localSourceSystemName | sourceSystem | sourceSystemName   |
      | project_one       | Project One           | CONS_LATAM   | Consumer Latam Ent |
      | ems               | EMS                   | EMS          | ems                |

    And I wait "/edm/source_system_v1" Async Queue complete

    When I submit task with xml file "xml/edm/EDMBomHdrV1Latam.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/bom_hdr" by keyFields "srcSysCd,bomCatCd,bomNum,altBomNum,bomCntrNbr"
      | srcSysCd   | bomCatCd | bomNum | altBomNum | bomCntrNbr | bomVldFromDt | chgNum | delInd | prvCntrNbr | crtDttm | chgDttm | bomUomCd | bomBaseQty | bomTxt | bomStsCd | bomVld_ToDt |
      | CONS_LATAM | stlty1   | stlnr1 | stlal1    | 1          | 2017/1/1     | aennr1 | loekz1 | vgkzl1     | andat1  | aedat1  | bmein1   | bmeng1     | stktx1 | stlst1   | 2017/8/25   |
      | CONS_LATAM | stlty2   | stlnr1 | stlal1    | 2          | 2017/8/25    | aennr2 | loekz2 | vgkzl2     | andat2  | aedat2  | bmein2   | bmeng2     | stktx2 | stlst2   | 2017/9/24   |
      | CONS_LATAM | stlty3   | stlnr1 | stlal1    | 3          | 2017/9/24    | aennr3 | loekz3 | vgkzl3     | andat3  | aedat3  | bmein3   | bmeng3     | stktx3 | stlst3   | 9999/12/31  |
      | CONS_LATAM | stlty4   | stlnr1 | stlal2    | 4          | 2017/9/35    | aennr4 | loekz4 | vgkzl4     | andat4  | aedat4  | bmein4   | bmeng4     | stktx4 | stlst4   | 9999/12/31  |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/project_one/stko" and "/edm/bom_hdr,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/edm/bom_hdr"

    And I will remove all data with region "/plan/edm_failed_data"

