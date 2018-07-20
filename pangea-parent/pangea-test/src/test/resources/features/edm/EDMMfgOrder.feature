@pangea_test @AEAZ-8798
Feature: EDMMfgOrder AEAZ-8798

  Scenario: Full Load curation

    Given I import "/project_one/afko" by keyFields "aufnr,mandt"
      | aufnr   | mandt | gstrp    | gsuzp  | gltrp    | gluzp  | gstrs    | gsuzs  | gltrs    | gluzs  | ftrms    | gstri       | gsuzi  | gltri    | getri | geuzi  | ftrmp | ftrmi    | rsnum    | plnty | plnnr    | plnal | stlty | stlnr | stlal | dispo | aufpl  | fevor | igmng | iasmg |
      | 1699274 | 120   | 20180704 | 000001 | 20180704 | 000001 | 20180704 | 000001 | 20180704 | 000001 | 20180704 | 20180702 | 000001 | 20180702 |       | 000001 |       | 20180702 | 88920537 | N     | 50020243 | 1     | M     | 35498 | 1     | 52    | 699255 |       | 630   | 0     |
      | 1699530 | 120   | 20181126 | 000001 | 20181126 | 000001 | 20181126 | 000001 | 20181126 | 000001 | 20181126 | 20180702 | 000001 | 20180702 |       | 000001 |       | 20180702 | 88549826 | N     | 50005368 | 1     | M     | 5909  | 1     | 55    | 699511 |       | 648   | 0     |
      | 1699531 | 120   | 20180702 | 000001 | 20180702 | 000001 | 20180702 | 000001 | 20180702 | 000001 | 20180702 | 20180702 | 000001 | 20180702 |       | 000001 |       | 20180702 | 88963996 | N     | 50019132 | 1     | M     | 33874 | 1     | I00   | 699512 | 9     | 774   | 0     |

    And I wait "/project_one/afko" Async Queue complete

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | localSourceSystemName | sourceSystem | sourceSystemName   |
      | project_one       | Project One           | CONS_LATAM   | Consumer Latam Ent |
      | ems               | EMS                   | EMS          | ems                |
    And I wait "/edm/source_system_v1" Async Queue complete

    Given I import "/project_one/aufk" by keyFields "aufnr,mandt"
      | aufnr   | mandt | auart | werks | erdat    | aedat    | loekz | objnr          |
      | 1699274 | 120   | PP01  | BR12  | 20180702 | 20180702 |       | OR000001699274 |
      | 1699530 | 120   | PP01  | BR12  | 20180702 | 20180702 |       | OR000001699530 |
      | 1699531 | 120   | PP01  | BR12  | 20180702 | 20180702 |       | OR000001699531 |

    And I wait "/project_one/aufk" Async Queue complete

    Given I import "/project_one/jest" by keyFields "objnr,stat"
      | objnr          | stat  | inact |
      | OR000001699274 | I0001 |       |
      | OR000001699274 | I0016 |       |
      | OR000001699274 | I0028 |       |
      | OR000001699274 | I0115 |       |
      | OR000001699530 | I0001 |       |
      | OR000001699531 | I0340 | X     |

    And I wait "/project_one/jest" Async Queue complete

    Given I import "/project_one/tj02t" by keyFields "istat,spras"
      | istat | txt04 | spras |
      | I0001 | CRTD  | E     |
      | I0016 | PRC   | E     |
      | I0028 | SETC  | E     |
      | I0115 | CSER  | E     |
      | I0340 | MACM  | E     |
    And I wait "/project_one/tj02t" Async Queue complete



    When I submit task with xml file "xml/edm/EDMMfgOrder.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/mfg_order" by keyFields "sourceSysCd,mfgOrdrNum"
      | sourceSysCd | mfgOrdrNum | mfgOrdrTypCd | plntCd | crtdDttm | chgDttm  | delInd | strtDt   | strtTm | endDt    | endTm  | schdStrtDt | schdStrtTm | schdEndDt | schdEndTm | schRelDt | actStrtDt | actStrtTm | prdtnEndDt | cnfrmdEndDt | cnfrmdEndTm | planRlseDt | actRlseDt | rsrvtnNum | rtngTypCd | rtngGrpCd | rtngGrpCntrNum | bomCatCd | bomNum | bomAltNum | mrpCntrllrCd | ordrRtngNum | prdSpvsrCd | cnfrmdYldQty | cnfrmdScrpQty | objectNumber   | mfgOrdrSttsCd           | localSystemStatus       | localStatus1       |
      | CONS_LATAM  | 1699274    | PP01         | BR12   | 20180702 | 20180702 |        | 20180704 | 000001 | 20180704 | 000001 | 20180704   | 000001     | 20180704  | 000001    | 20180704 | 20180702  | 000001    | 20180702   |             | 000001      |                  | 20180702  | 88920537  | N         | 50020243  | 1              | M        | 35498  | 1         | 52           | 699255      |            | 630          | 0             | OR000001699274 | I0001,I0016,I0028,I0115 | I0001,I0016,I0028,I0115 | CRTD,PRC,SETC,CSER |
      | CONS_LATAM  | 1699530    | PP01         | BR12   | 20180702 | 20180702 |        | 20181126 | 000001 | 20181126 | 000001 | 20181126   | 000001     | 20181126  | 000001    | 20181126 | 20180702  | 000001    | 20180702   |             | 000001      |                  | 20180702  | 88549826  | N         | 50005368  | 1              | M        | 5909   | 1         | 55           | 699511      |            | 648          | 0             | OR000001699530 | I0001                   | I0001                   | CRTD               |
      | CONS_LATAM  | 1699531    | PP01         | BR12   | 20180702 | 20180702 |        | 20180702 | 000001 | 20180702 | 000001 | 20180702   | 000001     | 20180702  | 000001    | 20180702 | 20180702  | 000001    | 20180702   |             | 000001      |                  | 20180702  | 88963996  | N         | 50019132  | 1              | M        | 33874  | 1         | I00          | 699512      | 9          | 774          | 0             | OR000001699531 |                         |                         |                    |

  Scenario:delete test data
    And I delete the test data

    #And I will remove all data with region "/edm/mfg_order"

    #And I will remove all data with region "/plan/edm_failed_data"

