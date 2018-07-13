@pangea_test @AEAZ-7487
Feature: EDMBomItem AEAZ-7487

  @Scenario1
  Scenario: Sheet1

    When I import "/project_one/stpo" by keyFields "mandt,stlkn,stlnr,stlty,stpoz"
      | mandt | stlty | stlnr    | stlkn    | stpoz    | datuv    | aennr        | lkenz | vgknt | vgpzl | andat    | aedat    | idnrk              | postp | posnr | meins | menge  | fmeng | ausch | schgt | nlfzt | verti | potx1 | potx2 | kzkup | alpgr | ewahr | alpos | alpst | alprf |
      | 120   | M     | 00015485 | 00000001 | 00000002 | 20061027 |              |       |       |       | 20061027 |          | 000000000008201178 | N     | 0010  | KG    | 1200   |       |       |       |       |       |       |       |       |       |       |       |       |       |
      | 120   | M     | 00015485 | 00000010 | 00000032 | 20061121 | 500000000588 |       |       |       | 20061122 |          | 000000000008201178 | L     | 0010  | KG    | 1200   |       |       |       |       |       |       |       |       |       |       |       |       |       |
      | 120   | M     | 00015485 | 00000011 | 00000034 | 20061121 | 500000000588 |       |       |       | 20061122 | 20070430 | 000000000008220032 | L     | 0020  | M     | 137600 |       |       |       |       |       | text1 | text2 |       |       |       |       |       |       |

    And I wait "/project_one/stpo" Async Queue complete

    When I import "/project_one/stas" by keyFields "stasz,stlal,stlkn,stlnr,stlty"
      | stlty | stlnr    | stlal | stlkn    | stasz    | datuv    | techv | aennr        | lkenz | andat    | annam    | stvkn    |
      | M     | 00015485 | 01    | 00000001 | 00000003 | 20061027 |       |              |       | 20061027 | COLIVEI7 | 00000001 |
      | M     | 00015485 | 01    | 00000001 | 00000022 | 20061121 |       | 500000000588 | X     | 20061122 | JMATERAZ | 00000001 |
      | M     | 00015485 | 02    | 00000010 | 00000033 | 20061121 |       | 500000000588 |       | 20061122 | JMATERAZ | 00000010 |
      | M     | 00015485 | 02    | 00000011 | 00000035 | 20061121 |       | 500000000588 |       | 20061122 | JMATERAZ | 00000011 |
      | M     | 00015485 | 02    | 00000011 | 00000054 | 20080201 |       | 500000000693 | X     | 20070308 | JMATERAZ | 00000011 |
      | M     | 00015485 | 02    | 00000011 | 00000057 | 20070611 |       | 500000000771 | X     | 20070430 | JNAVARRA | 00000011 |
    And I wait "/project_one/stas" Async Queue complete

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | sourceSystem |
      | project_one       | CONS_LATAM   |
      | EMS               | EMS          |

    And I wait "/edm/source_system_v1" Async Queue complete
    When I submit task with xml file "xml/edm/EDMBomItem.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/bom_item" by keyFields "srcSysCd,bomCatCd,bomNum,bomItmNdeNum,bomItmCntrNbr"
      | srcSysCd   | bomCatCd | bomNum   | bomItmNdeNum | bomItmCntrNbr | bomItmVldFromDt | bomChgNum    | bomDelInd | bomPreItmNdeNum | bomPreItmCntrNbr | crtDttm  | chgDttm  | cmpntNum           | bomItmCatCd | bomItmNum | cmpntUomCd | cmpntQty | fxQtyInd | cmpntScrapPct | bomItmBulkInd | leadTimeOffst | dstrbtnKeyCd | bomItmTxt   | coProdInd | bomItmVldToDt | altItmGrpCd | altItmPct | altItmInd | altItmStratCd | altItmRankNbr |
      | CONS_LATAM | M        | 00015485 | 00000001     | 00000002      | 20061027        |              |           |                 |                  | 20061027 |          | 000000000008201178 | N           | 0010      | KG         | 1200     |          |               |               |               |              |             |           | 20061120      |             |           |           |               |               |
      | CONS_LATAM | M        | 00015485 | 00000010     | 00000032      | 20061121        | 500000000588 |           |                 |                  | 20061122 |          | 000000000008201178 | L           | 0010      | KG         | 1200     |          |               |               |               |              |             |           | 99991231      |             |           |           |               |               |
      | CONS_LATAM | M        | 00015485 | 00000011     | 00000034      | 20061121        | 500000000588 |           |                 |                  | 20061122 | 20070430 | 000000000008220032 | L           | 0020      | M          | 137600   |          |               |               |               |              | text1 text2 |           | 20070610      |             |           |           |               |               |


    And I delete the test data

    And I will remove all data with region "/edm/bom_item"

        