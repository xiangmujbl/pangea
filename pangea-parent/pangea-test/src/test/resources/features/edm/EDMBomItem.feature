@pangea_test @AEAZ-3271
Feature: E.2.1.4 EDMBOM-BOM_ITEM - curation

  As a Data user,
  I want EDG to curate BOM Item raw data from ECC LATAM as well as Enterprise standard values from EDM cross reference tables
  so that there is an Enterprise Data Model that contains BOM-level supply chain data consistent with Enterprise standards, and ready for consumption by reports and/or cross-functional applications.

  Scenario: Full Load curation

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | sourceSystem |
      | project_one       | CONS_LATAM   |
      | EMS               | EMS          |

    And I wait "/edm/source_system_v1" Async Queue complete

    And I import "/project_one/stpo" by keyFields "stlty,stlnr,stlkn,stpoz,datuv,aennr,posnr,idnrk,menge"
      | stlty | stlnr    | stlkn | stpoz | datuv      | aennr        | lkenz | vgknt | vgpzl | andat      | aedat      | idnrk   | postp       | posnr | meins | menge | fmeng       | ausch | schgt       | nlfzt | verti       | potx1       | potx2       | kzkup       | alpgr       | ewahr | alpos       | alpst       | alprf |
      | M     | 00020130 | 1     | 2     | 2018/04/19 |              |       | 1     | 1     | 2018/05/15 | 2018/05/15 | 203331  | postp-line1 | 0010  | 1     | 1.000 | fmeng-line1 | 1     | schgt-line1 | 1     | verti-line1 | potx1-line1 | potx2-line1 | kzkup-line1 | alpgr-line1 | 1     | alpos-line1 | alpst-line1 | 1     |
      | M     | 00020130 | 3     | 6     | 2018/08/25 | 500000000003 |       | 2     | 2     | 2018/05/15 | 2018/05/15 | 203331  | postp-line2 | 0010  | 2     | 2.000 | fmeng-line2 | 2     | schgt-line2 | 2     | verti-line2 | potx1-line2 | potx2-line2 | kzkup-line2 | alpgr-line2 | 2     | alpos-line2 | alpst-line2 | 2     |
      | M     | 00020130 | 4     | 11    | 2018/04/20 |              |       | 3     | 3     | 2018/05/15 | 2018/05/15 | 203331  | postp-line3 | 0010  | 3     | 1.000 | fmeng-line3 | 3     | schgt-line3 | 3     | verti-line3 | potx1-line3 | potx2-line3 | kzkup-line3 | alpgr-line3 | 3     | alpos-line3 | alpst-line3 | 3     |
      | M     | 00020130 | 5     | 13    | 2018/04/20 |              |       | 4     | 4     | 2018/05/15 | 2018/05/15 | 354267A | postp-line4 | 0020  | 4     | 1.000 | fmeng-line4 | 4     | schgt-line4 | 4     | verti-line4 | potx1-line4 | potx2-line4 | kzkup-line4 | alpgr-line4 | 4     | alpos-line4 | alpst-line4 | 4     |
      | M     | 00020130 | 6     | 15    | 2018/08/25 | 500000000003 |       | 5     | 5     | 2018/05/15 | 2018/05/15 | 354267A | postp-line5 | 0020  | 5     | 2.000 | fmeng-line5 | 5     | schgt-line5 | 5     | verti-line5 | potx1-line5 | potx2-line5 | kzkup-line5 | alpgr-line5 | 5     | alpos-line5 | alpst-line5 | 5     |
      | M     | 00020130 | 7     | 18    | 2018/08/25 | 500000000003 |       | 6     | 6     | 2018/05/15 | 2018/05/15 | 375600A | postp-line6 | 0030  | 6     | 1.000 | fmeng-line6 | 6     | schgt-line6 | 6     | verti-line6 | potx1-line6 | potx2-line6 | kzkup-line6 | alpgr-line6 | 6     | alpos-line6 | alpst-line6 | 6     |
      | M     | 00020130 | 8     | 21    | 2018/04/20 |              |       | 7     | 7     | 2018/05/15 | 2018/05/15 | 203331  | postp-line7 | 0010  | 7     | 1.000 | fmeng-line7 | 7     | schgt-line7 | 7     | verti-line7 | potx1-line7 | potx2-line7 | kzkup-line7 | alpgr-line7 | 7     | alpos-line7 | alpst-line7 | 7     |
      | M     | 00020130 | 9     | 23    | 2018/04/20 |              |       | 8     | 8     | 2018/05/15 | 2018/05/15 | 354267A | postp-line8 | 0020  | 8     | 1.000 | fmeng-line8 | 8     | schgt-line8 | 8     | verti-line8 | potx1-line8 | potx2-line8 | kzkup-line8 | alpgr-line8 | 8     | alpos-line8 | alpst-line8 | 8     |
      | M     | 00020130 | 10    | 25    | 2018/08/25 | 500000000003 |       | 9     | 9     | 2018/05/15 | 2018/05/15 | 375600A | postp-line9 | 0020  | 9     | 1.000 | fmeng-line9 | 9     | schgt-line9 | 9     | verti-line9 | potx1-line9 | potx2-line9 | kzkup-line9 | alpgr-line9 | 9     | alpos-line9 | alpst-line9 | 9     |

    And I wait "/project_one/stas" Async Queue complete

    And I import "/project_one/stas" by keyFields "stlty,stlnr,stlal,stlkn,stasz,datuv,aennr,leknz"
      | stlty | stlnr    | stlal | stlkn | stasz | datuv      | aennr        | leknz |
      | M     | 00020130 | 1     | 1     | 3     | 2018/04/19 |              |       |
      | M     | 00020130 | 1     | 1     | 7     | 2018/08/25 | 500000000003 | X     |
      | M     | 00020130 | 1     | 3     | 8     | 2018/08/25 | 500000000003 |       |
      | M     | 00020130 | 2     | 4     | 12    | 2018/04/20 |              |       |
      | M     | 00020130 | 2     | 5     | 14    | 2018/04/20 |              |       |
      | M     | 00020130 | 2     | 5     | 16    | 2018/08/25 | 500000000003 | X     |
      | M     | 00020130 | 2     | 6     | 17    | 2018/08/25 | 500000000003 |       |
      | M     | 00020130 | 2     | 7     | 19    | 2018/08/25 | 500000000003 |       |
      | M     | 00020130 | 3     | 8     | 22    | 2018/04/20 |              |       |
      | M     | 00020130 | 3     | 9     | 24    | 2018/04/20 |              |       |
      | M     | 00020130 | 3     | 9     | 26    | 2018/08/25 | 500000000003 | X     |
      | M     | 00020130 | 3     | 10    | 27    | 2018/08/25 | 500000000003 |       |

    And I wait "/project_one/stpo" Async Queue complete

    When I submit task with xml file "xml/edm/EDMBomItem.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/bom_item" by keyFields "srcSysCd,bomCatCd,bomNum,bomItmNdeNum,bomItmCntrNbr"
      | srcSysCd   | bomCatCd | bomNum   | bomItmNdeNum | bomItmCntrNbr | bomItmVldFromDt | bomChgNum    | bomDelInd | bomPreItmNdeNum | bomPreItmCntrNbr | crtDttm    | chgDttm    | cmpntNum | bomItmCatCd | bomItmNum | cmpntUomCd | cmpntQty | fxQtyInd    | cmpntScrapPct | bomItmBulkInd | leadTimeOffst | dstrbtnKeyCd | bomItmTxt               | coProdInd   | bomItmVldToDt | altItmGrpCd | altItmPct | altItmInd   | altItmStratCd | altItmRankNbr |
      | CONS_LATAM | M        | 00020130 | 1            | 2             | 2018/04/19      |              |           | 1               | 1                | 2018/05/15 | 2018/05/15 | 203331   | postp-line1 | 0010      | 1          | 1.000    | fmeng-line1 | 1             | schgt-line1   | 1             | verti-line1  | potx1-line1 potx2-line1 | kzkup-line1 | 2018/08/25    | alpgr-line1 | 1         | alpos-line1 | alpst-line1   | 1             |
      | CONS_LATAM | M        | 00020130 | 3            | 6             | 2018/08/25      | 500000000003 |           | 2               | 2                | 2018/05/15 | 2018/05/15 | 203331   | postp-line2 | 0010      | 2          | 2.000    | fmeng-line2 | 2             | schgt-line2   | 2             | verti-line2  | potx1-line2 potx2-line2 | kzkup-line2 | 9999/12/31    | alpgr-line2 | 2         | alpos-line2 | alpst-line2   | 2             |
      | CONS_LATAM | M        | 00020130 | 4            | 11            | 2018/04/20      |              |           | 3               | 3                | 2018/05/15 | 2018/05/15 | 203331   | postp-line3 | 0010      | 3          | 1.000    | fmeng-line3 | 3             | schgt-line3   | 3             | verti-line3  | potx1-line3 potx2-line3 | kzkup-line3 | 9999/12/31    | alpgr-line3 | 3         | alpos-line3 | alpst-line3   | 3             |
      | CONS_LATAM | M        | 00020130 | 5            | 13            | 2018/04/20      |              |           | 4               | 4                | 2018/05/15 | 2018/05/15 | 354267A  | postp-line4 | 0020      | 4          | 1.000    | fmeng-line4 | 4             | schgt-line4   | 4             | verti-line4  | potx1-line4 potx2-line4 | kzkup-line4 | 2018/08/25    | alpgr-line4 | 4         | alpos-line4 | alpst-line4   | 4             |
      | CONS_LATAM | M        | 00020130 | 6            | 15            | 2018/08/25      | 500000000003 |           | 5               | 5                | 2018/05/15 | 2018/05/15 | 354267A  | postp-line5 | 0020      | 5          | 2.000    | fmeng-line5 | 5             | schgt-line5   | 5             | verti-line5  | potx1-line5 potx2-line5 | kzkup-line5 | 9999/12/31    | alpgr-line5 | 5         | alpos-line5 | alpst-line5   | 5             |
      | CONS_LATAM | M        | 00020130 | 7            | 18            | 2018/08/25      | 500000000003 |           | 6               | 6                | 2018/05/15 | 2018/05/15 | 375600A  | postp-line6 | 0030      | 6          | 1.000    | fmeng-line6 | 6             | schgt-line6   | 6             | verti-line6  | potx1-line6 potx2-line6 | kzkup-line6 | 9999/12/31    | alpgr-line6 | 6         | alpos-line6 | alpst-line6   | 6             |
      | CONS_LATAM | M        | 00020130 | 8            | 21            | 2018/04/20      |              |           | 7               | 7                | 2018/05/15 | 2018/05/15 | 203331   | postp-line7 | 0010      | 7          | 1.000    | fmeng-line7 | 7             | schgt-line7   | 7             | verti-line7  | potx1-line7 potx2-line7 | kzkup-line7 | 9999/12/31    | alpgr-line7 | 7         | alpos-line7 | alpst-line7   | 7             |
      | CONS_LATAM | M        | 00020130 | 9            | 23            | 2018/04/20      |              |           | 8               | 8                | 2018/05/15 | 2018/05/15 | 354267A  | postp-line8 | 0020      | 8          | 1.000    | fmeng-line8 | 8             | schgt-line8   | 8             | verti-line8  | potx1-line8 potx2-line8 | kzkup-line8 | 2018/08/25    | alpgr-line8 | 8         | alpos-line8 | alpst-line8   | 8             |
      | CONS_LATAM | M        | 00020130 | 10           | 25            | 2018/08/25      | 500000000003 |           | 9               | 9                | 2018/05/15 | 2018/05/15 | 375600A  | postp-line9 | 0020      | 9          | 1.000    | fmeng-line9 | 9             | schgt-line9   | 9             | verti-line9  | potx1-line9 potx2-line9 | kzkup-line9 | 9999/12/31    | alpgr-line9 | 9         | alpos-line9 | alpst-line9   | 9             |

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/edm/source_system_v1"

    And I will remove all data with region "/project_one/stpo"

    And I will remove all data with region "/project_one/stas"

    And I will remove all data with region "/edm"

