@pangea_test @AEAZ-6664
Feature: EDMWrkCtrHier AEAZ-6664

  Scenario: Full Load curation

    Given I import "/project_one/crhs" by keyFields "mandt,objidHo,objidHy,objidUp,objtyHo,objtyHy,objtyUp"
      | mandt | objtyHy    | objidHy | objtyHo    | objidHo    | objtyUp | objidUp    | objtyLe | objidLe | vcxpos | vcypos  | flgav | 
      | 20     | H | 10000021  | A | 10003234 | A  | 10003232 | A     | 10003233     |      |   |      | 
      | 20     | H | 10000021  | A | 10003235 | A  | 10003232 | A     | 10003234     |      |   |      |
      | 20     | H | 10000021  | A | 10003236 | A  | 10003232 | A     | 10003235     |      |   |      | 
      | 20     | H | 10000021_F1  | A | 10003241 | A  | 10003237_F1 | A     | 10003239     |      |   |   X   |  
    And I wait "/project_one/crhs" Async Queue complete

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | sourceSystem |
      | project_one       | CONS_LATAM   |
      | EMS               | EMS          |
    And I wait "/edm/source_system_v1" Async Queue complete

    When I submit task with xml file "xml/edm/EDMWrkCtrHier.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/wrk_ctr_hier" by keyFields "srcSysCd,topWrkCtrTypeCd,topWrkCtrNum,parntWrkCtrTypeCd,parntWrkCtrNum,childWrkCtrTypeCd,childWrkCtrNum"
      | srcSysCd   | topWrkCtrTypeCd | topWrkCtrNum | parntWrkCtrTypeCd | parntWrkCtrNum | childWrkCtrTypeCd  | childWrkCtrNum  | wrkCtrTypeCd  | wrkCtrNum | stopExplsInd |
      | CONS_LATAM | H            | 10000021  | A        | 10003232  | A | 10003234 | A | 10003233           |          |
      | CONS_LATAM | H            | 10000021  | A        | 10003232  | A | 10003235 | A | 10003234           |          |
      | CONS_LATAM | H            | 10000021  | A        | 10003232  | A | 10003236 | A | 10003235           |          |

    And I delete the test data

    And I will remove all data with region "/edm/wrk_ctr_hier"


