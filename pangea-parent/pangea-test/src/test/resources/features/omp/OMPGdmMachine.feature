@pangea_test @AEAZ-6662
Feature: OMPGdmMachine AEAZ-6662

  @Scenario1
  Scenario: Check Full Rule

    Given I import "/edm/wrk_ctr" by keyFields "srcSysCd,wrkCtrNum,wrkCtrTypeCd"
      | srcSysCd   | wrkCtrTypeCd | wrkCtrNum     | vldFromDt | vldToDt    | wrkCtrCd   | plntCd | wrkCtrCatCd | delInd | wrkCtrUsgCd | wrkCtrLocCd | respPrsnNum | wrkCtrActvCd | lockInd | schdlngInd | setupTypeCd | oprCd | setupFrmlCd | runFrmlCd | teardownFrmlCd | capyNum    | locGrpCd | machTypeCd | plnrGrpCd | othFrmlCd | suplAreaCd | slocCd | mixingInd | wrkCtrDesc                     |
      | CONS_LATAM | A            | 10000237      | 20000823  | 23.08.2000 | TOU-PESA   | BR02   | 0001        | X      | 009         | CONS_LATAM  |             |              |         |            |             |       |             |           |                | 10000253   |          |            |           |           |            |        |           | Sala de Pesagem Toucador       |
      | CONS_LATAM | A            | 10000238      | 20000823  | 23.08.2000 | TOU-R01    | BR02   | 0001        | X      | 009         | CONS_LATAM  |             |              |         |            |             |       |             |           |                | 10000254   |          |            |           |           |            |        |           | Reator R01 Toucador            |
      | CONS_LATAM | A            | 10000239      | 20000823  | 23.08.2000 | TOU-R02    | BR02   | 0001        | X      | 009         | CONS_LATAM  |             |              |         |            |             |       |             |           |                | 10000255   |          |            |           |           |            |        |           | Reator R02 Toucador            |
      | CONS_LATAM | A            | 10000240      | 20000823  | 23.08.2000 | TOU-R03    | BR02   | 0001        | X      | 009         | CONS_LATAM  |             |              |         |            |             |       |             |           |                | 10000256   |          |            |           |           |            |        |           | Reator R03 Toucador            |
      | CONS_LATAM | A            | 10000241      | 20000823  | 23.08.2000 | TOU-R04    | BR02   | 0001        | X      | 009         | CONS_LATAM  |             |              |         |            |             |       |             |           |                | 10000257   |          |            |           |           |            |        |           | Reator R04 Toucador            |
      | CONS_LATAM | A            | 10000242      | 20000823  | 23.08.2000 | TOU-R05    | BR02   | 0001        | X      | 009         | CONS_LATAM  |             |              |         |            |             |       |             |           |                | 10000258   |          |            |           |           |            |        |           | Reator R05 Toucador            |
      | CONS_LATAM | A            | 10000243      | 20000823  | 23.08.2000 | TOU-R06    | BR02   | 0001        |        | 009         | CONS_LATAM  |             |              |         |            |             |       |             |           |                | 10000259   |          |            |           |           |            |        |           | Reator R06 Toucador            |
      | CONS_LATAM | A            | 10000244      | 20000823  | 23.08.2000 | TOU-R07    | BR02   | 0001        |        | 009         | CONS_LATAM  |             |              |         |            |             |       |             |           |                | 10000260   |          |            |           |           |            |        |           | Reator R07 Toucador            |
      | CONS_LATAM | A            | 10000245      | 20000823  | 23.08.2000 | TOU-R08    | BR02   | 0001        |        | 009         | CONS_LATAM  |             |              |         |            |             |       |             |           |                | 10000261   |          |            |           |           |            |        |           | Reator R08 Toucador            |
      | CONS_LATAM | A            | 10000246      | 20000823  | 23.08.2000 | TOU-R11    | BR02   | 0001        |        | 009         | CONS_LATAM  |             |              |         |            |             |       |             |           |                | 10000262   |          |            |           |           |            |        |           | Reator R11 Toucador            |
      | CONS_LATAM | A            | 10000247      | 20000823  | 23.08.2000 | TOU-R10    | BR02   | 0001        |        | 009         | CONS_LATAM  |             |              |         |            |             |       |             |           |                | 10000263   |          |            |           |           |            |        |           | Reator R10 Toucador            |
      | CONS_LATAM | A            | 10003230      | 20030319  | 19.03.2003 | CTTOU      | BR12   | 0005        |        | 009         | CONS_LATAM  |             |              |         |            |             |       |             |           |                | 10003151   |          |            |           |           |            |        |           | Centro trabalho toucador       |
      | CONS_LATAM | A            | 10003232      | 20030319  | 19.03.2003 | TOU-PREP   | BR12   | 0005        |        | 009         | CONS_LATAM  |             |              |         |            |             |       |             |           |                | 10003153   |          |            |           |           |            |        |           | Liquidos e Cremes - PREPARAÇÃO |
      | CONS_LATAM | A            | 10003233_T4_0 | 20030319  | 19.03.2003 | CTTOUMCA_0 | BR12   | 0005        |        | 009         | CONS_LATAM  |             |              |         |            |             |       |             |           |                | 10003154_0 |          |            |           |           |            |        |           | Centro/trabalho/toucador       |
      | CONS_LATAM | A            | 10003233_T4_1 | 20030319  | 19.03.2003 | CTTOUMCA_1 | BR12   | 0005        |        | 009         | CONS_LATAM  |             |              |         |            |             |       |             |           |                | 10003154_1 |          |            |           |           |            |        |           | /trabalho/toucador             |
      | CONS_LATAM | A            | 10003233_T4_2 | 20030319  | 19.03.2003 | CTTOUMCA_2 | BR12   | 0005        |        | 009         | CONS_LATAM  |             |              |         |            |             |       |             |           |                | 10003154_2 |          |            |           |           |            |        |           | //toucador                     |
      | CONS_LATAM | A            | 10003233_T4_3 | 20030319  | 19.03.2003 | CTTOUMCA_3 | BR12   | 0005        |        | 009         | CONS_LATAM  |             |              |         |            |             |       |             |           |                | 10003154_3 |          |            |           |           |            |        |           | //                             |
      | CONS_LATAM | A            | 10003234      | 20030319  | 19.03.2003 | CTTOUMCB   | BR12   | 0005        |        | 009         | CONS_LATAM  |             |              |         |            |             |       |             |           |                | 10003155   |          |            |           |           |            |        |           | Centro trabalho toucador       |
      | CONS_LATAM | A            | 10003235      | 20030319  | 19.03.2003 | CTTOUMCC   | BR12   | 0005        |        | 001         | CONS_LATAM  |             |              |         |            |             |       |             |           |                | 10003156   |          |            |           |           |            |        |           | Centro trabalho toucador       |
      | CONS_LATAM | A            | 10003238      | 20030320  | 20.03.2003 | CTMICA     | BR12   | 0005        |        | 001         | CONS_LATAM  |             |              |         |            |             |       |             |           |                | 10003159   |          |            |           |           |            |        |           | Centro trabalho toucador       |
      | CONS_LATAM | A            | 10003239      | 20030320  | 20.03.2003 | CT325172   | BR12   | 0005        | X      | 009         | CONS_LATAM  |             |              |         |            |             |       |             |           |                | 10003160   |          |            |           |           |            |        |           | Centro trabalho toucador       |
    And I wait "/edm/wrk_ctr" Async Queue complete

    Given I import "/edm/wrk_ctr_hier" by keyFields "srcSysCd,parntWrkCtrNum"
      | srcSysCd   | topWrkCtrTypeCd | topWrkCtrNum | parntWrkCtrTypeCd | parntWrkCtrNum | childWrkCtrTypeCd | childWrkCtrNum | wrkCtrTypeCd | wrkCtrNum | stopExplsInd |
      | CONS_LATAM | H               | 10000021     | A                 | 10003234       | A                 | 10003232       | A            | 10003233  |              |
      | CONS_LATAM | H               | 10000021     | A                 | 10003235       | A                 | 10003232       | A            | 10003234  |              |
      | CONS_LATAM | H               | 10000021     | A                 | 10003236       | A                 | 10003232       | A            | 10003235  |              |
      | CONS_LATAM | H               | 10000021     | A                 | 10003241       | A                 | 10003237       | A            | 10003239  |              |
    And I wait "/edm/wrk_ctr_hier" Async Queue complete

    Given I import "/edm/capy_hdr" by keyFields "srcSysCd,capyNum"
      | srcSysCd   | capyNum    | capyNbr | strtTm | endTm | fctryCalCd | capyCatCd | wrkCtrTypeCd | wrkCtrNum | plntCd | finiteSchdlngInd |
      | CONS_LATAM | 10000253   | 1       |        |       | 0          | 001       | A            | 10003233  | BR12   | X                |
      | CONS_LATAM | 10000254   | 2       |        |       | 0          | 001       | A            | 10003234  | BR12   | X                |
      | CONS_LATAM | 10000255   | 2       |        |       | 0          | 001       | A            | 10003235  | BR12   | X                |
      | CONS_LATAM | 10000256   | 2       |        |       | 0          | 001       | A            | 10003239  | BR12   | X                |
      | CONS_LATAM | 10000257   | 2       |        |       | 0          | 001       | A            | 10003239  | BR12   | X                |
      | CONS_LATAM | 10000258   | 2       |        |       | 0          | 001       | A            | 10003239  | BR12   | X                |
      | CONS_LATAM | 10000259   | 2       |        |       | 0          | 001       | A            | 10003239  | BR12   | X                |
      | CONS_LATAM | 10000260   | 2       |        |       | 0          | 001       | A            | 10003239  | BR12   | X                |
      | CONS_LATAM | 10000261   | 2       |        |       | 0          | 001       | A            | 10003239  | BR12   | X                |
      | CONS_LATAM | 10000262   | 2       |        |       | 0          | 001       | A            | 10003239  | BR12   | X                |
      | CONS_LATAM | 10000263   | 2       |        |       | 0          | 001       | A            | 10003239  | BR12   | X                |
      | CONS_LATAM | 10003151   | 1       |        |       | 0          | 002       | A            | 10003239  | BR12   |                  |
      | CONS_LATAM | 10003153   | 1       |        |       | 0          | 002       | A            | 10003239  | BR12   |                  |
      | CONS_LATAM | 10003154_0 | 1       |        |       | 0          | 001       | A            | 10003239  | BR12   |                  |
      | CONS_LATAM | 10003154_1 | 1       |        |       | 0          | 001       | A            | 10003239  | BR12   |                  |
      | CONS_LATAM | 10003154_2 | 1       |        |       | 0          | 001       | A            | 10003239  | BR12   |                  |
      | CONS_LATAM | 10003154_3 | 1       |        |       | 0          | 001       | A            | 10003239  | BR12   |                  |
      | CONS_LATAM | 10003155   | 1       |        |       | 0          | 001       | A            | 10003239  | BR12   |                  |
      | CONS_LATAM | 10003156   | 1       |        |       | 0          | 002       | A            | 10003239  | BR12   |                  |
      | CONS_LATAM | 10003159   | 1       |        |       | 0          | 002       | A            | 10003239  | BR12   |                  |
      | CONS_LATAM | 10003160   | 1       |        |       | 0          | 002       | A            | 10003239  | BR12   |                  |
    And I wait "/edm/capy_hdr" Async Queue complete

    Given I import "/plan/cns_plan_parameter" by keyFields "attribute,dataObject,sourceSystem,parameter"
      | attribute  | dataObject  | sourceSystem | parameter | parameterValue |
      | CONS_LATAM | SEND_TO_OMP | CONS_LATAM   | Division  | LA             |
      | CONS_LATAM | SEND_TO_GDM | CONS_LATAM   | Division  | LA             |
    And I wait "/plan/cns_plan_parameter" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmMachine.xml" and execute file "jar/pangea-view.jar"

#    Then A file is found on sink application with name "GDMMachine.tsv"

#    Then I check file data for filename "GDMMachine.tsv" by keyFields "machineId"
    Then I check region data "/omp/gdm_machine" by keyFields "machineId"
      | machineId                  | active | activeOPRERP | activeSOPERP | building   | description              | locationId      | machineCapacity | machineTypeId | parentMachineId          |
      | CONS_LATAM/BR02/TOU-R06    | YES    | YES          | NO           | CONS_LATAM | Reator R06 Toucador      | CONS_LATAM_BR02 | finite          | Production    |                          |
      | CONS_LATAM/BR02/TOU-R07    | YES    | YES          | NO           | CONS_LATAM | Reator R07 Toucador      | CONS_LATAM_BR02 | finite          | Production    |                          |
      | CONS_LATAM/BR02/TOU-R08    | YES    | YES          | NO           | CONS_LATAM | Reator R08 Toucador      | CONS_LATAM_BR02 | finite          | Production    |                          |
      | CONS_LATAM/BR02/TOU-R11    | YES    | YES          | NO           | CONS_LATAM | Reator R11 Toucador      | CONS_LATAM_BR02 | finite          | Production    |                          |
      | CONS_LATAM/BR02/TOU-R10    | YES    | YES          | NO           | CONS_LATAM | Reator R10 Toucador      | CONS_LATAM_BR02 | finite          | Production    |                          |
      | CONS_LATAM/BR12/CTTOUMCA_0 | YES    | YES          | NO           | CONS_LATAM | Centro                   | CONS_LATAM_BR12 | infinite        | Production    |                          |
      | CONS_LATAM/BR12/CTTOUMCA_1 | YES    | YES          | NO           | CONS_LATAM | trabalho                 | CONS_LATAM_BR12 | infinite        | Production    |                          |
      | CONS_LATAM/BR12/CTTOUMCA_2 | YES    | YES          | NO           | CONS_LATAM | toucador                 | CONS_LATAM_BR12 | infinite        | Production    |                          |
      | CONS_LATAM/BR12/CTTOUMCA_3 | YES    | YES          | NO           | CONS_LATAM |                          | CONS_LATAM_BR12 | infinite        | Production    |                          |
      | CONS_LATAM/BR12/CTTOUMCB   | YES    | YES          | NO           | CONS_LATAM | Centro trabalho toucador | CONS_LATAM_BR12 | infinite        | Production    | CONS_LATAM/BR12/CTTOUMCC |


#    And I delete the test data

#    And I will remove all data with region "/omp/gdm_machine"
    
        