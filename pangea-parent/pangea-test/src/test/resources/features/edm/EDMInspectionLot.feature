@pangea_test @AEAZ-3677
Feature: EDMInspectionLotV1  AEAZ-3677
   #1.get sourceSystem from source_system_v1 (rule T1)
    #2.get stsPrfl from project_one_qals (rule N1)
    #3.get actlLotQty from project_one_qals (rule N2)
    #4.get qcStsCd from /project_one/jest (rule N3)
    #5.get usgDcsnCd and qcDcsnDttm  (rule N4)


  Scenario: Full Load curation
    Given I import "/project_one/qals" by keyFields "prueflos,werk"
      | mandt | prueflos   | werk | matnr | art | herkunft | objnr         | stsma   | stat35     | enstehdat | entstezeit | ERSTELDATERSTELZEIT|AENDERDATAENDERZEIT | pastrterm | pastrzeit | paendterm | paendzeit | aufnr | kunnr | lifnr | charg    | lagortchrg | ebeln    | ebelp | mjahr | mblnr    | werkvorg | lagortvorg | losmenge | mengeneinh | lmengezub | lmengeist |
      |    120|010000001138|BR02  |32239  |01   |01        |QL010000001138 |STALOTE  |            |20001024   |8:26:42 AM  |20001024 08:26:42   |19000101 00:00:00   |20001024   |12:00:00 AM|20001024   |12:00:00 AM|       |       |6245   |4000008964|BR01        |3000000022|70     |2000   |5000018782|BR02      |BR01        |3195.000  |KG          |0.000      |  3195.000 |
      |    120|010000001153|BR02  |12937  |01   |01        |QL010000001153 |STALOTE  |            |20001023   |11:39:39 AM |20001023 11:39:39   |19000101 00:00:00   |20001023   |12:00:00 AM|20001023   |12:00:00 AM|       |       |2893   |4000008934|BR01        |3000001553|10     |2000   |5000017720|BR02      |BR01        |25.920    |TS          |0.000      |25.920     |
      |    120|010000001614|BR02  |100060 |01   |01        |QL010000001614 |STALOTE  |            |20001030   |10:05:15 AM |20001030 10:05:15   |19000101 00:00:00   |20001030   |12:00:00 AM|20001030   |12:00:00 AM|       |       |1858   |4000009567|BR01        |3000000645|30     |2000   |5000025593|BR02      |BR01        |500.000   |KG          |0.000      |500.000    |
      |    120|010000001620|BR02  |100060 |01   |01        |QL010000001620 |STALOTE  |  X         |20001030   |10:05:15 AM |20001030 10:05:15   |19000101 00:00:00   |20001030   |12:00:00 AM|20001030   |12:00:00 AM|       |       |1858   |4000009567|BR01        |3000000645|30     |2000   |5000025593|BR02      |BR01        |500.000   |KG          |0.000      |500.000    |
      |    120|010000001631|BR02  |100060 |01   |01        |QL010000001631 |STALOTE  |            |20001030   |10:05:15 AM |20001030 10:05:15   |19000101 00:00:00   |20001030   |12:00:00 AM|20001030   |12:00:00 AM|       |       |1858   |4000009567|BR01        |3000000645|30     |2000   |5000025593|BR02      |BR01        |500.000   |KG          |           |500.000    |
      |    120|010000001691|BR02  |100060 |01   |01        |QL010000001691 |STALOTE  |            |20001030   |10:05:15 AM |20001030 10:05:15   |19000101 00:00:00   |20001030   |12:00:00 AM|20001030   |12:00:00 AM|       |       |1858   |4000009567|BR01        |3000000645|30     |2000   |5000025593|BR02      |BR01        |500.000   |KG          |0.000      |500.000    |

    And I wait "/project_one/qals" Async Queue complete

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | localSourceSystemName | sourceSystem | sourceSystemName   |
      | Project_One       | Project One           | CONS_LATAM   | Consumer Latam Ent |
      | project_two       | Project Two           | BTCT_LATAM   | Consumer Latam Ent |
    And I wait "/edm/source_system_v1" Async Queue complete

    Given I import "/project_one/qave" by keyFields "kzart,mandant,prueflos,zaehler"
      |dbewertung|kzart|ltextkz|mandant|prueflos   |qkennzahl|stafo|teillos|vaedatum|vaename |vauswahlmg|vbewertung|vcode|vcodegrp|vdatum  |versionam|versioncd|vezeitaen|vezeiterf|vfolgeakti|vkatart|vname   |vorglfnr |vwerks|zaehler|
      |          |L    |X      |120    |010000001138|100      |22   |0      |20001024|AFREIS  |DECIUSO   |A         |APR  |APROVADO|20001024|1        |1        |133802   |133802   |          |3      |AFREIS  |         |BR02  |       |
      |          |L    |X      |120    |010000001153|100      |22   |0      |20001024|MROMER1 |DECIUSO   |A         |APR  |APROVADO|20001024|1        |1        |184834   |184834   |          |3      |MROMER1 |         |BR02  |       |
      |          |L    |       |120    |010000001614|100      |22   |0      |20001108|YTAKAHAS|DECIUSO   |A         |APR  |APROVADO|20001108|1        |1        |191030   |191030   |          |3      |YTAKAHAS|         |BR02  |       |
      |          |L    |       |120    |010000001620|100      |22   |0      |20001108|YTAKAHAS|DECIUSO   |A         |APR  |APROVADO|20001108|1        |1        |191030   |191030   |          |3      |YTAKAHAS|         |BR02  |       |
      |          |L    |       |120    |010000001631|100      |22   |0      |20001108|YTAKAHAS|DECIUSO   |A         |APR  |APROVADO|20001108|1        |1        |191030   |191030   |          |3      |YTAKAHAS|         |BR02  |       |

    And I wait "/project_one/qave" Async Queue complete

    Given I import "/project_one/jest" by keyFields "objnr,stat"
      | mandt| objnr     | stat| inact|
      |120|QL010000001138|E0001|      |
      |120|QL010000001138|I0001|X     |
      |120|QL010000001138|I0002|X     |
      |120|QL010000001138|I0201|X     |
      |120|QL010000001138|I0202|X     |
      |120|QL010000001138|I0203|      |
      |120|QL010000001138|I0205|      |
      |120|QL010000001138|I0206|      |
      |120|QL010000001138|I0207|      |
      |120|QL010000001138|I0212|X     |
      |120|QL010000001138|I0213|      |
      |120|QL010000001138|I0216|      |
      |120|QL010000001138|I0217|      |
      |120|QL010000001138|I0218|      |
      |120|QL010000001138|I0219|X     |
      |120|QL010000001138|I0220|      |
      |120|QL010000001138|I0221|      |
      |120|QL010000001138|I0366|      |
      |120|QL010000001153|E0001|      |
      |120|QL010000001153|I0001|X     |
      |120|QL010000001153|I0002|X     |
      |120|QL010000001153|I0201|X     |
      |120|QL010000001153|I0202|X     |
      |120|QL010000001153|I0203|      |
      |120|QL010000001153|I0205|      |
      |120|QL010000001153|I0206|      |
      |120|QL010000001153|I0207|      |
      |120|QL010000001153|I0212|X     |
      |120|QL010000001153|I0213|      |
      |120|QL010000001153|I0216|      |
      |120|QL010000001153|I0217|      |
      |120|QL010000001153|I0218|      |
      |120|QL010000001153|I0219|X     |
      |120|QL010000001153|I0220|      |
      |120|QL010000001153|I0221|      |
      |120|QL010000001614|E0001|      |
      |120|QL010000001614|I0001|X     |
      |120|QL010000001614|I0002|X     |
      |120|QL010000001614|I0201|X     |
      |120|QL010000001614|I0202|X     |
      |120|QL010000001614|I0203|      |
      |120|QL010000001614|I0205|      |
      |120|QL010000001614|I0206|      |
      |120|QL010000001614|I0207|      |
      |120|QL010000001614|I0212|X     |
      |120|QL010000001614|I0213|      |
      |120|QL010000001614|I0216|      |
      |120|QL010000001614|I0217|      |
      |120|QL010000001614|I0218|      |
      |120|QL010000001614|I0219|X     |
      |120|QL010000001614|I0220|      |
      |120|QL010000001614|I0221|      |
      |120|QL010000001614|I0366|      |
      |120|QL010000001620|E0001|      |
      |120|QL010000001620|I0001|X     |
      |120|QL010000001620|I0002|X     |
      |120|QL010000001620|I0201|X     |
      |120|QL010000001620|I0202|X     |
      |120|QL010000001620|I0203|      |
      |120|QL010000001620|I0205|      |
      |120|QL010000001631|E0001|      |
      |120|QL010000001631|I0001|X     |
      |120|QL010000001631|I0002|X     |
      |120|QL010000001631|I0201|X     |
      |120|QL010000001631|I0202|X     |
      |120|QL010000001631|I0203|      |
      |120|QL010000001631|I0205|      |
      |120|QL010000001631|I0206|      |

    And I wait "/project_one/jest" Async Queue complete

    Given I import "/project_one/tj02t" by keyFields "istat"
      |istat|spras|txt04|txt30                        |
      |I0203|TS   |SPRQ |Quantity posting required    |
      |I0205|EN   |PASG |Plan/specification assigned  |
      |I0206|EN   |CCTD |Insp. characteristics created|
      |I0207|EN   |CALC |Sample calculated            |
      |I0213|EN   |RREC |Results confirmed            |
      |I0216|EN   |STIC |Short-term insp. completed   |
      |I0217|EN   |ICCO |All inspections completed    |
      |I0218|EN   |UD   |Usage decision has been made |
      |I0220|EN   |SPCO |Stock posting completed      |
      |I0221|EN   |STUP |Statistics updated           |
      |I0366|EN   |CROK |Certificate receipt confirmed|
    And I wait "/project_one/tj02t" Async Queue complete

    When I submit task with xml file "xml/edm/EDMInspectionLot.xml" and execute file "jar/pangea-view.jar"


    Then I check region data "/edm/inspection_lot_v1" by keyFields "srcSysCd,lotNum"
      | srcSysCd | lotNum    | PlntCd | matlId | baseUom | lotVerifTypeCd | lotOrigCd | localObjectNumber | stsPrfl | usgDecInd | localDateOfLotCreation   | localTimeOfLotCreation   | crtDttm               | chgDttm               | inspStrtDt   | inspStrtTm   | inspEndDt   | inspEndTm | cstmNum | vndrNum | btchNum | stgLocCd | mfgOrdrDoc | poDocNum | poDocLineNbr | matlMvmtDocYr | matlMvmtDocNum | stckPlntCd | stckSLocCd | inspLotQty | actlLotQty | toBePostedQty | usgDcsnCd  | qcDcsnDttm|qcStsCd|
      |CONS_LATAM|010000001138|BR02    |32239   |KG       |01              |01         |QL010000001138     |STALOTE  |           |2000-10-24                |8:26:42 AM                |2000-10-24T08:26:42.000|1900-01-01T00:00:00.000|2000-10-24    |12:00:00 AM   |2000-10-24   |12:00:00 AM|         |6245     |4000008964|BR01      |            |3000000022|70            |2000           |5000018782      |BR02        |BR01        |3195.000    |3195.000    |0.000          |APR        |2000-10-24 |PASG CCTD CALC RREC STIC ICCO UD SPCO STUP CROK|
      |CONS_LATAM|010000001153|BR02    |12937   |TS       |01              |01         |QL010000001153     |STALOTE  |           |2000-10-23                |11:39:39 AM               |2000-10-23T11:39:39.000|1900-01-01T00:00:00.000|2000-10-23    |12:00:00 AM   |2000-10-23   |12:00:00 AM|         |2893     |4000008934|BR01      |            |3000001553|10            |2000           |5000017720      |BR02        |BR01        |25.920      |25.920      |0.000          |APR        |2000-10-24 |PASG CCTD CALC RREC STIC ICCO UD SPCO STUP|
      |CONS_LATAM|010000001614|BR02    |100060  |KG       |01              |01         |QL010000001614     |STALOTE  |           |2000-10-30                |10:05:15 AM               |2000-10-30T10:05:15.000|1900-01-01T00:00:00.000|2000-10-30    |12:00:00 AM   |2000-10-30   |12:00:00 AM|         |1858     |4000009567|BR01      |            |3000000645|30            |2000           |5000025593      |BR02        |BR01        |500.000     |500.000     |0.000          |APR        |2000-11-08 |PASG CCTD CALC RREC STIC ICCO UD SPCO STUP CROK|
      |CONS_LATAM|010000001691|BR02    |100060  |KG       |01              |01         |QL010000001691     | STALOTE |           |2000-10-30                |10:05:15 AM               |2000-10-30T10:05:15.000|1900-01-01T00:00:00.000|2000-10-30    |12:00:00 AM   |2000-10-30   |12:00:00 AM|         |1858     |4000009567|BR01      |            |3000000645|30            |2000           |5000025593      |BR02        |BR01        |500.000     |500.000     |0.000          |           |           |                 |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |


    And I delete the test data

    And I will remove all data with region "/edm/inspection_lot_v1"

    And I will remove all data with region "/plan/edm_failed_data"

