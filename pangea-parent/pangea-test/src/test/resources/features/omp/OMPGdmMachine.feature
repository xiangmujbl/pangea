@pangea_test @AEAZ-8799
Feature: GDMMachine AEAZ-8799

  @Scenario1
  Scenario: test scenario 1
#    Given I import "/edm/wrk_ctr" by keyFields "srcSysCd,wrkCtrNum,wrkCtrTypeCd"
#      | srcSysCd   | wrkCtrTypeCd | wrkCtrNum | vldFromDt | vldToDt  | wrkCtrCd | plntCd | wrkCtrCatCd | delInd | wrkCtrUsgCd | wrkCtrLocCd | capyNum  | wrkCtrDesc                                             |
#      | CONS_LATAM | A            | 10000200  | 20000823  | 99991231 | TOU-R00  | BR12   | 0001        | X      | 009         | 400         | 90000200 | Reator R00 Toucador//                                  |
#      | CONS_LATAM | A            | 10000201  | 20000823  | 20100801 | TOU-R01  | BR12   | 0001        |        | 009         | 400         | 90000202 | Reator R01 Toucador//                                  |
#      | CONS_LATAM | A            | 10000202  | 20000823  | 99991231 | TOU-R02  | BR12   | 0001        |        | 009         | 400         | 90000204 | Reator R02 Toucador//                                  |
#      | CONS_LATAM | A            | 10000203  | 20000823  | 99991231 | TOU-R03  | BR12   | 0001        |        | 008         | 400         | 90000206 | Reator R03 Toucador//                                  |
#      | CONS_LATAM | A            | 10000204  | 20000823  | 99991231 | TOU-R04  | BR11   | 0001        |        | 009         | 400         | 90000208 | Reator R04 Toucador//                                  |
#      | CONS_LATAM | A            | 10000205  | 20000823  | 99991231 | TOU-R05  | BR12   | 0001        |        | 009         | 400         | 90000210 | Reator R05 Toucador//                                  |
#      | CONS_LATAM | A            | 10000206  | 20000823  | 99991231 | TOU-R06  | BR12   | 0001        |        | 009         | 400         | 90000212 | EN R06 Toucador/ES R06 Toucador/PT Reator R06 Toucador |
#      | CONS_LATAM | A            | 10000207  | 20000823  | 99991231 | TOU-R07  | CO01   | 0001        |        | 009         | 400         | 90000214 | /ES R07 Toucador/                                      |
#      | CONS_LATAM | A            | 10000208  | 20000823  | 99991231 | TOU-R08  | BR12   | 0001        |        | 009         | 400         | 90000216 | //PT Reator R08 Toucador                               |
#      | CONS_LATAM | A            | 10000209  | 20000823  | 99991231 | TOU-R09  | BR12   | 0001        |        | 009         | 400         | 90000218 | //                                                     |
#      | CONS_LATAM | A            | 10000210  | 20000823  | 99991231 | TOU-R10  | BR12   | 0001        |        | 009         | 400         | 90000220 | Reator R10 Toucador//                                  |
#      | CONS_LATAM | A            | 10000405  | 20000823  | 99991231 | TOU-P05  | BR12   | 0001        |        | 009         | 400         | 90000210 | Reator P05 Toucador//                                  |
#      | CONS_LATAM | A            | 10000406  | 20000823  | 99991231 | TOU-P06  | BR12   | 0001        |        | 009         | 400         | 90000212 | Reator P06 Toucador//                                  |
#      | CONS_LATAM | A            | 10000407  | 20000823  | 99991231 | TOU-P07  | BR12   | 0001        |        | 009         | 400         | 90000214 | Reator P07 Toucador//                                  |
#      | CONS_LATAM | A            | 10000408  | 20000823  | 99991231 | TOU-P08  | BR12   | 0001        |        | 009         | 400         | 90000216 | Reator P08 Toucador//                                  |
#      | CONS_LATAM | A            | 10000409  | 20000823  | 99991231 | TOU-P09  | BR12   | 0001        |        | 009         | 400         | 90000218 | Reator P09 Toucador//                                  |
#      | CONS_LATAM | A            | 10000410  | 20000823  | 99991231 | TOU-P10  | BR12   | 0001        |        | 009         | 400         | 90000220 | Reator P10 Toucador//                                  |
#    And I wait "/edm/wrk_ctr" Async Queue complete
#
#    Given I import "/edm/wrk_ctr_capy" by keyFields "capyAllcNbr,srcSysCd,wrkCtrNum,wrkCtrTypeCd"
#      | srcSysCd   | wrkCtrTypeCd | wrkCtrNum | capyAllcNbr | capyNum  |
#      | CONS_LATAM | A            | 10000200  | 1000        | 90000200 |
#      | CONS_LATAM | A            | 10000200  | 2000        | 90000201 |
#      | CONS_LATAM | A            | 10000201  | 1001        | 90000202 |
#      | CONS_LATAM | A            | 10000201  | 2001        | 90000203 |
#      | CONS_LATAM | A            | 10000202  | 1002        | 90000204 |
#      | CONS_LATAM | A            | 10000202  | 2002        | 90000205 |
#      | CONS_LATAM | A            | 10000203  | 1003        | 90000206 |
#      | CONS_LATAM | A            | 10000203  | 2003        | 90000207 |
#      | CONS_LATAM | A            | 10000204  | 1004        | 90000208 |
#      | CONS_LATAM | A            | 10000204  | 2004        | 90000209 |
#      | CONS_LATAM | A            | 10000205  | 1005        | 90000210 |
#      | CONS_LATAM | A            | 10000205  | 2005        | 90000211 |
#      | CONS_LATAM | A            | 10000206  | 1006        | 90000212 |
#      | CONS_LATAM | A            | 10000206  | 2006        | 90000213 |
#      | CONS_LATAM | A            | 10000207  | 1007        | 90000214 |
#      | CONS_LATAM | A            | 10000207  | 2007        | 90000215 |
#      | CONS_LATAM | A            | 10000208  | 1008        | 90000216 |
#      | CONS_LATAM | A            | 10000208  | 2008        | 90000217 |
#      | CONS_LATAM | A            | 10000209  | 1009        | 90000218 |
#      | CONS_LATAM | A            | 10000209  | 2009        | 90000219 |
#      | CONS_LATAM | A            | 10000210  | 1010        | 90000220 |
#      | CONS_LATAM | A            | 10000210  | 2010        | 90000221 |
#    And I wait "/edm/wrk_ctr_capy" Async Queue complete
#
#    Given I import "/edm/wrk_ctr_hier" by keyFields "srcSysCd,parntWrkCtrNum"
#      | srcSysCd   | topWrkCtrTypeCd | topWrkCtrNum | parntWrkCtrTypeCd | parntWrkCtrNum | wrkCtrTypeCd | wrkCtrNum |
#      | CONS_LATAM | H               | 10000605     | A                 | 10000405       | A            | 10000205  |
#      | CONS_LATAM | H               | 10000606     | A                 | 10000406       | A            | 10000206  |
#      | CONS_LATAM | H               | 10000607     | A                 | 10000407       | A            | 10000207  |
#      | CONS_LATAM | H               | 10000608     | A                 | 10000408       | A            | 10000208  |
#      | CONS_LATAM | H               | 10000609     | A                 | 10000409       | A            | 10000209  |
#      | CONS_LATAM | H               | 10000610     | A                 | 10000410       | A            | 10000210  |
#    And I wait "/edm/wrk_ctr_hier" Async Queue complete
#
#    Given I import "/edm/capy_hdr" by keyFields "srcSysCd,capyNum"
#      | srcSysCd   | capyNum  | capyCatCd | plntCd | finiteSchdlngInd |
#      | CONS_LATAM | 90000200 | 001       | BR12   |                  |
#      | CONS_LATAM | 90000201 | 002       | BR12   |                  |
#      | CONS_LATAM | 90000202 | 001       | BR12   |                  |
#      | CONS_LATAM | 90000203 | 002       | BR12   |                  |
#      | CONS_LATAM | 90000204 | 008       | BR12   |                  |
#      | CONS_LATAM | 90000205 | 002       | BR12   |                  |
#      | CONS_LATAM | 90000206 | 001       | BR12   |                  |
#      | CONS_LATAM | 90000207 | 002       | BR12   |                  |
#      | CONS_LATAM | 90000208 | 001       | BR11   |                  |
#      | CONS_LATAM | 90000209 | 002       | BR11   |                  |
#      | CONS_LATAM | 90000210 | 001       | BR12   |                  |
#      | CONS_LATAM | 90000211 | 002       | BR12   |                  |
#      | CONS_LATAM | 90000212 | 001       | BR12   |                  |
#      | CONS_LATAM | 90000213 | 002       | BR12   |                  |
#      | CONS_LATAM | 90000214 | 001       | CO01   |                  |
#      | CONS_LATAM | 90000215 | 002       | CO01   |                  |
#      | CONS_LATAM | 90000216 | 001       | BR12   |                  |
#      | CONS_LATAM | 90000217 | 002       | BR12   |                  |
#      | CONS_LATAM | 90000218 | 001       | BR12   |                  |
#      | CONS_LATAM | 90000219 | 002       | BR12   |                  |
#      | CONS_LATAM | 90000220 | 001       | BR12   | X                |
#      | CONS_LATAM | 90000221 | 002       | BR12   |                  |
#    And I wait "/edm/capy_hdr" Async Queue complete
#
#    Given I import "/plan/cns_plan_parameter" by keyFields "attribute,dataObject,sourceSystem,parameter"
#      | sourceSystem | dataObject  | attribute  | parameter  | parameterValue | inclExcl | comments |
#      | CONS_LATAM   | SEND_TO_OMP | CONS_LATAM | SYSTEMNAME | CONS_LATAM     | I        |          |
#    And I wait "/plan/cns_plan_parameter" Async Queue complete
#
#    Given I import "/plan/cns_plant_attr" by keyFields "sourceSystem,localPlant"
#      | sourceSystem | localPlant | localPlantName                     | localPlanningRelevant | locationAttribute1Desc | locationAttribute1Value | locationAttribute2Desc | locationAttribute2Value | planLocTypeDesc              | planLocTypeId | plant | plantType                   |
#      | CONS_LATAM   | BR11       | J&J BR Sï¿½o Josï¿½ Campos - Indus |                       | Country                | Brazil                  | Volume                 | High                    | Internal Manufacturing Plant | IM            | BR59  | MP,ï¿½Manufacturingï¿½Plant |
#      | CONS_LATAM   | BR12       | J&J BR Sï¿½o Josï¿½ Campos - Indus | X                     | Country                | Brazil                  | Volume                 | High                    | Internal Manufacturing Plant | IM            | BR59  | MP,ï¿½Manufacturingï¿½Plant |
#      | CONS_LATAM   | CO01       | J&J BR Sï¿½o Josï¿½ Campos - Indus |  X                     | Country                | Brazil                  | Volume                 | High                    | Internal Manufacturing Plant | IM            | BR59  | MP,ï¿½Manufacturingï¿½Plant |
#    And I wait "/plan/cns_plant_attr" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmMachine.xml" and execute file "jar/pangea-view.jar"

#    Then A file is found on sink application with name "GDMMachine.tsv"
#
#    Then I check file data for filename "GDMMachine.tsv" by keyFields "machineId"
##    Then I check region data "/omp/gdm_machine" by keyFields "machineId"
#      | machineId               | active | activeOPRERP | activeSOPERP | building | description            | locationId      | machineCapacity | machineTypeId | parentMachineId         |
#      | CONS_LATAM_BR12/TOU-R05 | YES    | YES          | NO           | 400      | Reator R05 Toucador    | CONS_LATAM_BR12 | infinite        | Production    | CONS_LATAM_BR12/TOU-P05 |
#      | CONS_LATAM_BR12/TOU-R06 | YES    | YES          | NO           | 400      | EN R06 Toucador        | CONS_LATAM_BR12 | infinite        | Production    | CONS_LATAM_BR12/TOU-P06 |
#      | CONS_LATAM_CO01/TOU-R07 | YES    | YES          | NO           | 400      | ES R07 Toucador        | CONS_LATAM_CO01 | infinite        | Production    | CONS_LATAM_CO01/TOU-P07 |
#      | CONS_LATAM_BR12/TOU-R08 | YES    | YES          | NO           | 400      | PT Reator R08 Toucador | CONS_LATAM_BR12 | infinite        | Production    | CONS_LATAM_BR12/TOU-P08 |
#      | CONS_LATAM_BR12/TOU-R09 | YES    | YES          | NO           | 400      |                        | CONS_LATAM_BR12 | infinite        | Production    | CONS_LATAM_BR12/TOU-P09 |
#      | CONS_LATAM_BR12/TOU-R10 | YES    | YES          | NO           | 400      | Reator R10 Toucador    | CONS_LATAM_BR12 | finite          | Production    | CONS_LATAM_BR12/TOU-P10 |
#
#
#    And I delete the test data
#
#    And I will remove all data with region "/omp/gdm_machine"
#
#