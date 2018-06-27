@pangea @AEAZ-6186
Feature: OMPGdmForecast AEAZ-6186

  @Scenario1
  Scenario: test scenario 1

    #OOR Forceast with Period W
    #OOR Forceast with Period M
    #OOR Forcest with UOM different than Base UOM
    #OOR record with Blank Values

#    And I will remove the test file on sink application "GDMForecast.tsv"
#
#    When I import "/plan/cns_oor_forecast" by keyFields "mthWeekInd,demandGrp,ppc,loadDate,localUom,oorPeriod"
#      | sourceSystem | demandGrp | ppc    | localPlant | oorPeriod | localUom | loadDate | quantity | mthWeekInd |
#      | CONS_LATAM   | 9999001   | 68229  | BR12       | 201810    | EA       | 20180524 | 100      | W          |
#      | CONS_LATAM   | 9999001   | 68229  | BR12       | 201811    | EA       | 20180524 | 120      | W          |
#      | CONS_LATAM   | 9999001   | 68229  | BR12       | 201812    | EA       | 20180524 | 120      | W          |
#      | CONS_LATAM   | 9999001   | 68229  | BR12       | 201813    | EA       | 20180524 | 100      | W          |
#      | CONS_LATAM   | 9999001   | 69349  | BR12       | 201813    | EA       | 20180524 | 200      | W          |
#      | CONS_LATAM   | 9999001   | 69349  | BR12       | 201814    | EA       | 20180524 | 200      | W          |
#      | CONS_LATAM   | 9999005   | 99100  | BR12       | 201803    | EA       | 20180523 | 100      | M          |
#      | CONS_LATAM   | 9999005   | 99100  | BR12       | 201804    | EA       | 20180523 | 120      | M          |
#      | CONS_LATAM   | 9999005   | 441124 | BR12       | 201803    | KI       | 20180523 | 150      | M          |
#      | CONS_LATAM   | 9999005   | 441124 | BR12       | 201804    | KI       | 20180523 | 200      | M          |
#      | CONS_LATAM   | 9999005   | 441124 | BR12       | 201805    | KI       | 20180523 | 200      | M          |
#    And I wait "/plan/cns_oor_forecast" Async Queue complete
#
#    When I import "/edm/jnj_calendar_v1" by keyFields "weekFromDate,weekToDate,calWeek,fiscalPeriod"
#      | weekFromDate | weekToDate | fiscalPeriod | calWeek |
#      | 2018-03-05   | 2018-03-12 | 201810       | 201810  |
#      | 2018-03-12   | 2018-03-12 | 201803       | 201811  |
#      | 2018-03-19   | 2018-03-26 | 201803       | 201812  |
#      | 2018-03-26   | 2018-03-26 | 201803       | 201813  |
#      | 2018-03-26   | 2018-03-26 | 201802       | 201813  |
#      | 2018-04-02   | 2018-04-02 | 201801       | 201814  |
#      | 2018-02-26   | 2018-03-05 | 201803       | 201809  |
#      | 2018-04-02   | 2018-04-02 | 201804       | 201804  |
#      | 2018-02-26   | 2018-03-05 | 201803       | 201809  |
#      | 2018-04-02   | 2018-04-02 | 201804       | 201804  |
#      | 2018-04-30   | 2018-04-30 | 201805       | 201805  |
#    And I wait "/edm/jnj_calendar_v1" Async Queue complete
#
#    When I import "/edm/material_global_v1" by keyFields "localBaseUom,localMaterialNumber,primaryPlanningCode"
#      | sourceSystem | localMaterialNumber | materialNumber | primaryPlanningCode | localBaseUom |
#      | CONS_LATAM   | 68229               | 68229          | 68229               | EA           |
#      | CONS_LATAM   | 68229               | 68229          | 68229               | EA           |
#      | CONS_LATAM   | 68229               | 68229          | 68229               | EA           |
#      | CONS_LATAM   | 68229               | 68229          | 68229               | EA           |
#      | CONS_LATAM   | 69349               | 69349          | 69349               | EA           |
#      | CONS_LATAM   | 69349               | 69349          | 69349               | EA           |
#      | CONS_LATAM   | 99100               | 99100          | 99100               | EA           |
#      | CONS_LATAM   | 99100               | 99100          | 99100               | KI           |
#      | CONS_LATAM   | 441124              | 441124         | 441124              | EA           |
#      | CONS_LATAM   | 441124              | 441124         | 441124              | EA           |
#      | CONS_LATAM   | 441124              | 441124         | 441124              | EA           |
#    And I wait "/edm/material_global_v1" Async Queue complete
#
#    When I import "/plan/cns_material_plan_status" by keyFields "sourceSystem,localMaterialNumber,spRelevant,localPlant"
#      | sourceSystem | localMaterialNumber | spRelevant | localPlant |
#      | CONS_LATAM   | 68229               | X          | BR12       |
#      | CONS_LATAM   | 68229               | X          | BR12       |
#      | CONS_LATAM   | 68229               | X          | BR12       |
#      | CONS_LATAM   | 68229               | X          | BR12       |
#      | CONS_LATAM   | 69349               | X          | BR12       |
#      | CONS_LATAM   | 69349               | X          | BR12       |
#      | CONS_LATAM   | 99100               | X          | BR12       |
#      | CONS_LATAM   | 99100               | X          | BR12       |
#      | CONS_LATAM   | 441124              | X          | BR12       |
#      | CONS_LATAM   | 441124              | X          | BR12       |
#      | CONS_LATAM   | 441124              | X          | BR12       |
#    And I wait "/plan/cns_material_plan_status" Async Queue complete
#
#    When I import "/edm/material_auom_v1" by keyFields "localAuom,localNumerator,localMaterialNumber"
#      | sourceSystem | localNumerator | localMaterialNumber | localAuom | localDenominator |
#      | CONS_LATAM   | 1000           | 68229               | EA        | 1000             |
#      | CONS_LATAM   | 1000           | 68229               | EA        | 1000             |
#      | CONS_LATAM   | 1000           | 68229               | EA        | 1000             |
#      | CONS_LATAM   | 1000           | 68229               | EA        | 1000             |
#      | CONS_LATAM   | 1000           | 69349               | EA        | 1000             |
#      | CONS_LATAM   | 1000           | 69349               | EA        | 1000             |
#      | CONS_LATAM   | 1000           | 99100               | EA        | 1000             |
#      | CONS_LATAM   | 1              | 99100               | EA        | 1                |
#      | CONS_LATAM   | 36             | 441124              | KI        | 1                |
#      | CONS_LATAM   | 36             | 441124              | KI        | 1                |
#      | CONS_LATAM   | 36             | 441124              | KI        | 1                |
#    And I wait "/edm/material_auom_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmForecast.xml" and execute file "jar/pangea-view.jar"

#    Then A file is found on sink application with name "GDMForecast.tsv"
#
#    Then I check file data for filename "GDMForecast.tsv" by keyFields "forecastId,productId,dueDate"
#      | active | activeFctErp | activeOprErp | activeSopErp | certaintyId | customerId | cycleStartDate      | dueDate             | forecastId                                         | fromDueDate         | locationId      | planningStrategy        | productId | publishDate         | quantity |
#      | YES    | NO           | YES          | NO           | PP          | 9999001    | 2018/05/01 00:00:00 | 2018/03/05 00:00:01 | 68229-CONS_LATAM_BR12-9999001-2018/03/05 00:00:00  | 2018/03/05 00:00:00 | CONS_LATAM_BR12 | ProductLocationBalanced | 68229     | 2018/06/26 00:00:00 | 100      |
#      | YES    | NO           | YES          | NO           | PP          | 9999001    | 2018/05/01 00:00:00 | 2018/03/12 00:00:01 | 68229-CONS_LATAM_BR12-9999001-2018/03/12 00:00:00  | 2018/03/12 00:00:00 | CONS_LATAM_BR12 | ProductLocationBalanced | 68229     | 2018/06/26 00:00:00 | 120      |
#      | YES    | NO           | YES          | NO           | PP          | 9999001    | 2018/05/01 00:00:00 | 2018/03/19 00:00:01 | 68229-CONS_LATAM_BR12-9999001-2018/03/19 00:00:00  | 2018/03/19 00:00:00 | CONS_LATAM_BR12 | ProductLocationBalanced | 68229     | 2018/06/26 00:00:00 | 120      |
#      | YES    | NO           | YES          | NO           | PP          | 9999001    | 2018/05/01 00:00:00 | 2018/03/26 00:00:01 | 68229-CONS_LATAM_BR12-9999001-2018/03/26 00:00:00  | 2018/03/26 00:00:00 | CONS_LATAM_BR12 | ProductLocationBalanced | 68229     | 2018/06/26 00:00:00 | 100      |
#      | YES    | NO           | YES          | NO           | PP          | 9999001    | 2018/05/01 00:00:00 | 2018/03/26 00:00:01 | 69349-CONS_LATAM_BR12-9999001-2018/03/26 00:00:00  | 2018/03/26 00:00:00 | CONS_LATAM_BR12 | ProductLocationBalanced | 69349     | 2018/06/26 00:00:00 | 200      |
#      | YES    | NO           | YES          | NO           | PP          | 9999001    | 2018/05/01 00:00:00 | 2018/04/02 00:00:01 | 69349-CONS_LATAM_BR12-9999001-2018/04/02 00:00:00  | 2018/04/02 00:00:00 | CONS_LATAM_BR12 | ProductLocationBalanced | 69349     | 2018/06/26 00:00:00 | 200      |
#      | YES    | NO           | YES          | NO           | PP          | 9999005    | 2018/05/01 00:00:00 | 2018/02/26 00:00:01 | 99100-CONS_LATAM_BR12-9999005-2018/02/26 00:00:00  | 2018/02/26 00:00:00 | CONS_LATAM_BR12 | ProductLocationBalanced | 99100     | 2018/06/26 00:00:00 | 100      |
#      | YES    | NO           | YES          | NO           | PP          | 9999005    | 2018/05/01 00:00:00 | 2018/04/02 00:00:01 | 99100-CONS_LATAM_BR12-9999005-2018/04/02 00:00:00  | 2018/04/02 00:00:00 | CONS_LATAM_BR12 | ProductLocationBalanced | 99100     | 2018/06/26 00:00:00 | 120      |
#      | YES    | NO           | YES          | NO           | PP          | 9999005    | 2018/05/01 00:00:00 | 2018/02/26 00:00:01 | 441124-CONS_LATAM_BR12-9999005-2018/02/26 00:00:00 | 2018/02/26 00:00:00 | CONS_LATAM_BR12 | ProductLocationBalanced | 441124    | 2018/06/26 00:00:00 | 5400     |
#      | YES    | NO           | YES          | NO           | PP          | 9999005    | 2018/05/01 00:00:00 | 2018/04/02 00:00:01 | 441124-CONS_LATAM_BR12-9999005-2018/04/02 00:00:00 | 2018/04/02 00:00:00 | CONS_LATAM_BR12 | ProductLocationBalanced | 441124    | 2018/06/26 00:00:00 | 7200     |
#      | YES    | NO           | YES          | NO           | PP          | 9999005    | 2018/05/01 00:00:00 | 2018/04/30 00:00:01 | 441124-CONS_LATAM_BR12-9999005-2018/04/30 00:00:00 | 2018/04/30 00:00:00 | CONS_LATAM_BR12 | ProductLocationBalanced | 441124    | 2018/06/26 00:00:00 | 7200     |
#
#    Then I check region data "/edm/failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
#      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |
#
#    And I delete the test data
#
#    And I will remove all data with region "/omp/gdm_forecast"
#
#    And I will remove all data with region "/edm/failed_data"



        