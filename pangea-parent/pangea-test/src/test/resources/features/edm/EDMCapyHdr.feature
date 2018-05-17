@pangea_test @AEAZ-4241
Feature: EDMCapyHdr AEAZ-4241

  Scenario: Full Load curation

    Given I import "/project_one/kako" by keyFields "kapid"
      | kapid    | aznor | begzt | endzt | kalid | kapar | meins | name     | ngrad | planr | poolk | werks | kapter | kapavo | ueberlast | kaplpl | kapeh | mehr | ang_unit | is_bottleneck |
      | 10000001 | 0     | 21601 | 21601 | BR    | 002   | A     | nhuang12 | 003   | 004   | 005   | H     | 006    | 007    | 008       | 009    | 010   | 011  | 012      | 013           |
      | 10000002 | 0     | 21602 | 21602 | BR    | 002   | A     | nhuang12 | 003   | 004   | 005   | H     | 006    | 007    | 008       | 009    | 010   | 011  | 012      | 013           |
      | 10000003 | 0     | 21603 | 21603 | BR    | 002   | A     | nhuang12 | 003   | 004   | 005   | H     | 006    | 007    | 008       | 009    | 010   | 011  | 012      | 013           |
      | 10000004 | 0     | 21604 | 21604 | BR    | 002   | A     | nhuang12 | 003   | 004   | 005   | H     | 006    | 007    | 008       | 009    | 010   | 011  | 012      | 013           |
      | 10000005 | 0     | 21605 | 21605 | BR    | 002   | A     | nhuang12 | 003   | 004   | 005   | H     | 006    | 007    | 008       | 009    | 010   | 011  | 012      | 013           |

    And I wait "/project_one/kako" Async Queue complete

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | sourceSystem |
      | project_one       | CONS_LATAM   |
      | project_two       | NHUANG_NAN   |
    And I wait "/edm/source_system_v1" Async Queue complete

    Given I import "/project_one/kakt" by keyFields "kapid,spras"
      | kapid    | spras | ktext     |
      | 10000001 | EN    | Machine 1 |
      | 10000001 | ES    | Maquina 1 |
      | 10000001 | PT    | M1        |
      | 10000002 | EN    | Machine 2 |
      | 10000002 | ES    | Maquina 2 |
      | 10000003 | PT    | M3        |
      | 10000004 | ES    | Maquina 4 |
      | 10000004 | PT    | M4        |
      | 10000005 | ES    |           |

    And I wait "/project_one/kakt" Async Queue complete


    When I submit task with xml file "xml/edm/EDMCapyHdr.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/capy_hdr" by keyFields "srcSysCd,capyNum"
      | srcSysCd   | capyNum  | capyNbr | strtTm | endTm | fctryCalCd | capyCatCd | capyBaseUomCd | capyNm   | capyUtlzRtPct | plnrGrpCd | poolCapyInd | plntCd | finiteSchdlngInd | mltOpsInd | ovldPct | ltpExclnInd | capyUomCd | indivCapyInd | availCapyUomCd | btneckInd | capyDesc               |
      | CONS_LATAM | 10000001 | 0       | 21601  | 21601 | BR         | 002       | A             | nhuang12 | 003           | 004       | 005         | H      | 006              | 007       | 008     | 009         | 010       | 011          | 012            | 013       | Machine 1/Maquina 1/M1 |
      | CONS_LATAM | 10000002 | 0       | 21602  | 21602 | BR         | 002       | A             | nhuang12 | 003           | 004       | 005         | H      | 006              | 007       | 008     | 009         | 010       | 011          | 012            | 013       | Machine 2/Maquina 2/   |
      | CONS_LATAM | 10000003 | 0       | 21603  | 21603 | BR         | 002       | A             | nhuang12 | 003           | 004       | 005         | H      | 006              | 007       | 008     | 009         | 010       | 011          | 012            | 013       | //M3                   |
      | CONS_LATAM | 10000004 | 0       | 21604  | 21604 | BR         | 002       | A             | nhuang12 | 003           | 004       | 005         | H      | 006              | 007       | 008     | 009         | 010       | 011          | 012            | 013       | /Maquina 4/M4          |
      | CONS_LATAM | 10000005 | 0       | 21605  | 21605 | BR         | 002       | A             | nhuang12 | 003           | 004       | 005         | H      | 006              | 007       | 008     | 009         | 010       | 011          | 012            | 013       | //                     |

#    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
#      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

#    And I compare the number of records between "/project_one/kako" and "/edm/capy_hdr,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/edm/capy_hdr"

    And I will remove all data with region "/plan/edm_failed_data"

