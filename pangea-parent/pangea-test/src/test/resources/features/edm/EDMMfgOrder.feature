@pangea_test @AEAZ-4249
Feature: EDMMfgOrder AEAZ-4249

  Scenario: Full Load curation

    Given I import "/project_one/afko" by keyFields "aufnr,mandt"
      | aufnr        | mandt | gstrp    | gsuzp    | gltrp    | gluzp    | gstrs    | gsups    | gltrs    | glups    | ftrms    | gstri    | gsuzi    | gltri    | getri    | geuzi    | ftrmp    | ftrmi    | rsnum      | plnty | plnnr    | plnal | stlty | stlnr | stlal | dispo | aufpl | fevor | igmng    | iasmg |
      | 000001000323 | 120   | 20001016 | 20001016 | 20001018 | 20001018 | 20001018 | 20001018 | 20001012 | 20001012 | 20001005 | 20001005 | 20001005 | 20001019 | 20001019 | 20001019 | 00000000 | 20001002 | 0000000570 | N     | 50000739 | 01    | stlty | stlnr | stlal | 101   | aufpl | 005   | 5214.000 | iasmg |
      | 000001000327 | 120   | 20001011 | 20001016 | 20001018 | 20001018 | 20001018 | 20001018 | 20001012 | 20001012 | 20001005 | 20001005 | 20001005 | 20001019 | 20001019 | 20001019 | 00000000 | 20001002 | 0000000570 | N     | 50000739 | 01    | stlty | stlnr | stlal | 101   | aufpl | 005   | 9993.000 | iasmg |
      | 000001000328 | 120   | 20001011 | 20001016 | 20001018 | 20001018 | 20001018 | 20001018 | 20001012 | 20001012 | 20001005 | 20001005 | 20001005 | 20001019 | 20001019 | 20001019 | 00000000 | 20001002 | 0000000570 | N     | 50000739 | 01    | stlty | stlnr | stlal | 101   | aufpl | 005   | 9993.000 | iasmg |

    And I wait "/project_one/afko" Async Queue complete

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | localSourceSystemName | sourceSystem | sourceSystemName   |
      | project_one       | Project One           | CONS_LATAM   | Consumer Latam Ent |
      | ems               | EMS                   | EMS          | ems                |
    And I wait "/edm/source_system_v1" Async Queue complete

    Given I import "/project_one/aufk" by keyFields "aufnr,mandt"
      | aufnr        | mandt | auart | werks | erdat    | aedat    | loekz | objnr          |
      | 000001000323 | 120   | RA    | PE02  | 20170619 | 20170801 | loekz | OR000001690465 |
      | 000001000327 | 120   | RA    | PE01  | 20170619 | 20171227 | loekz | OR000001690465 |
      | 000001000328 | 120   | RA    | PE01  | 20170619 | 20171227 | loekz | OR000001690466 |

    And I wait "/project_one/aufk" Async Queue complete

    Given I import "/project_one/jest" by keyFields "objnr,stat"
      | objnr          | stat  | inact |
      | OR000001690465 | I0001 | I0366 |
      | OR000001690465 | I0006 | I0366 |
      | OR000001690466 | I0016 | X     |

    And I wait "/project_one/jest" Async Queue complete

    Given I import "/project_one/tj02t" by keyFields "istat,spras"
      | istat | txt04 | spras |
      | I0001 | TECO  | EN    |
      | I0006 | RDIS  | EN    |
      | I0016 | RDIS  | EN    |
    And I wait "/project_one/tj02t" Async Queue complete



    When I submit task with xml file "xml/edm/EDMMfgOrder.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/mfg_order" by keyFields "sourceSysCd,mfgOrdrNum"
      | sourceSysCd | mfgOrdrNum   | mfgOrdrTypCd | plntCd | crtdDttm | chgDttm  | delInd | strtDt   | strtTm   | endDt    | endTm    | schdStrtDt | schdStrtTm | schdEndDt | schdEndTm | schRelDt | actStrtDt | actStrtTm | prdtnEndDt | cnfrmdEndDt | cnfrmdEndTm | planRlseDt | actRlseDt | rsrvtnNum  | rtngTypCd | rtngGrpCd | rtngGrpCntrNum | bomCatCd | bomNum | bomAltNum | mrpCntrllrCd | ordrRtngNum | prdSpvsrCd | cnfrmdYldQty | cnfrmdScrpQty | objectNumber   | mfgOrdrSttsCd | localSystemStatus | localStatus1 |
      | CONS_LATAM  | 000001000323 | RA           | PE02   | 20170619 | 20170801 | loekz  | 20001016 | 20001016 | 20001018 | 20001018 | 20001018   | 20001018   | 20001012  | 20001012  | 20001005 | 20001005  | 20001005  | 20001019   | 20001019    | 20001019    | 00000000   | 20001002  | 0000000570 | N         | 50000739  | 01             | stlty    | stlnr  | stlal     | 101          | aufpl       | 005        | 5214.000     | iasmg         | OR000001690465 | I0001 I0006   | I0001 I0006       | TECO RDIS    |
      | CONS_LATAM  | 000001000327 | RA           | PE01   | 20170619 | 20171227 | loekz  | 20001011 | 20001016 | 20001018 | 20001018 | 20001018   | 20001018   | 20001012  | 20001012  | 20001005 | 20001005  | 20001005  | 20001019   | 20001019    | 20001019    | 00000000   | 20001002  | 0000000570 | N         | 50000739  | 01             | stlty    | stlnr  | stlal     | 101          | aufpl       | 005        | 9993.000     | iasmg         | OR000001690465 | I0001 I0006   | I0001 I0006       | TECO RDIS    |
      | CONS_LATAM  | 000001000328 | RA           | PE01   | 20170619 | 20171227 | loekz  | 20001011 | 20001016 | 20001018 | 20001018 | 20001018   | 20001018   | 20001012  | 20001012  | 20001005 | 20001005  | 20001005  | 20001019   | 20001019    | 20001019    | 00000000   | 20001002  | 0000000570 | N         | 50000739  | 01             | stlty    | stlnr  | stlal     | 101          | aufpl       | 005        | 9993.000     | iasmg         | OR000001690466 |               |                   |              |


    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/project_one/afko" and "/edm/mfg_order,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/edm/mfg_order"

    And I will remove all data with region "/plan/edm_failed_data"

