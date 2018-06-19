@pangea_test @AEAZ-5951
Feature: OMPGdmStock AEAZ-5951
  As a Data user, I want EDG to create GDM file for GDMStock 5 and send to OMP so that data can be consumed by OMP for planning

#  Scenario: check rule PLO9
#
#    And I will remove the test file on sink application "GDMStock_plannedOrder.tsv"
#
#    Given I import "/edm/planned_order_v1" by keyFields "mfgPlanOrdrDocId,srcSysCd"
#      | matlNum | srcSysCd   | mfgPlanOrdrDocId | plntCd | prdtnVersNum | planOrdrEndDt | grDaysLeadQty | plngScnroCd | planOrdrTypeCd | planOrdrQty |
#      | 138821  | CONS_LATAM | 49748397         | BR12   | 0            | 20180610      | 0             | 000         | LA             | 36300       |
#      | 441423  | CONS_LATAM | 117229550        | BR12   | 0            | 20180611      | 2             | 000         | LA             | 12132       |
#      | 189915  | CONS_LATAM | 95941803         | BR12   | 0            | 20180612      | 4             | 000         | LA             | 86544       |
#      | 213481  | CONS_LATAM | 112368557        | BR12   | 0            | 20180613      | 4             | 000         | LA             | 5600        |
#      | 213482  | CONS_LATAM | 49748312         | BR12   | 0            | 20180613      | 4             | 000         | LA             | 5600        |
#    And I wait "/edm/planned_order_v1" Async Queue complete
#
#    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber,sourceSystem"
#      | localMaterialNumber | sourceSystem | primaryPlanningCode | materialNumber |
##     PLO9(if primaryPlanningCode and materialNumber are not empty and not same)
#      | 138821              | CONS_LATAM   | 138821              | 234312         |
##     PLO9(if primaryPlanningCode and materialNumber are not empty and same)
#      | 441423              | CONS_LATAM   | 441423              | 441423         |
##     PLO9(if primaryPlanningCode is not empty and and materialNumber is empty)
#      | 189915              | CONS_LATAM   | 189915              |                |
##     PLO9(if primaryPlanningCode is empty and and materialNumber is not empty)
#      | 213481              | CONS_LATAM   |                     | 213481         |
#    And I wait "/edm/material_global_v1" Async Queue complete
#
#    Given I import "/edm/plant_v1" by keyFields "localPlant,sourceSystem"
#      | localPlant | sourceSystem | localPlanningRelevant |
#      | BR12       | CONS_LATAM   | X                     |
#    And I wait "/edm/material_global_v1" Async Queue complete
#
#    Given I import "/plan/cns_material_plan_status" by keyFields "localMaterialNumber,localPlant,sourceSystem"
#      | localMaterialNumber | localPlant | sourceSystem | noPlanRelevant |
#      | 138821              | BR12       | CONS_LATAM   | X              |
#      | 441423              | BR12       | CONS_LATAM   | X              |
#      | 189915              | BR12       | CONS_LATAM   | X              |
#      | 213481              | BR12       | CONS_LATAM   | X              |
##     PLO9(if noPlanRelevant != X, skip the record)
#      | 213482              | BR12       | CONS_LATAM   |                |
#    And I wait "/plan/cns_material_plan_status" Async Queue complete
#
#    Given I import "/plan/cns_plan_object_filter" by keyFields "sourceObjectAttribute1,sourceObjectAttribute2,sourceObjectAttribute2Value,sourceObjectTechName,sourceSystem"
#      | sourceObjectAttribute1 | sourceObjectAttribute2 | sourceObjectAttribute2Value | sourceObjectTechName | sourceSystem |
#      | plntCd                 | planOrdrTypeCd         | LA                          | planned_order        | CONS_LATAM   |
#    And I wait "/plan/cns_plan_object_filter" Async Queue complete
#
#    When I submit task with xml file "xml/gdm_stock_5_planned_order.xml" and execute file "jar/pangea-view.jar"
#
#    Then A file is found on sink application with name "GDMStock_plannedOrder.tsv"
#
#    Then I check file data for filename "GDMStock_plannedOrder.tsv" by keyFields "stockId"
##    Then I check region data "/omp/gdm_stock" by keyFields "stockId"
#      | stockId                          | active | activeOPRERP | activeSOPERP | batchId | blockedQuantity | consignment | certaintyId | erpOrderId | inventoryLinkGroupId | vendorId | locationId      | processId                | processTypeId | productId | qualityQuantity | quantity | receiptDate         | restrictedQuantity | returnsQuantity | startDate           | stockType | transferQuantity | transitDate         | unrestrictedQuantity |
#      | 138821/CONS_LATAM_BR12/49748397  | YES    | YES          | NO           |         | 0.0             | NO          | PA          | 49748397   |                      |          | CONS_LATAM_BR12 | 0/138821/CONS_LATAM_BR12 | Production    | 138821    | 0.0             | 36300    | 2018/06/10 00:00:00 | 0.0                | 0.0             | 2018/06/11 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
#      | 441423/CONS_LATAM_BR12/117229550 | YES    | YES          | NO           |         | 0.0             | NO          | PA          | 117229550  |                      |          | CONS_LATAM_BR12 | 0/441423/CONS_LATAM_BR12 | Production    | 441423    | 0.0             | 12132    | 2018/06/11 00:00:00 | 0.0                | 0.0             | 2018/06/13 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
#      | 189915/CONS_LATAM_BR12/95941803  | YES    | YES          | NO           |         | 0.0             | NO          | PA          | 95941803   |                      |          | CONS_LATAM_BR12 | 0/189915/CONS_LATAM_BR12 | Production    | 189915    | 0.0             | 86544    | 2018/06/12 00:00:00 | 0.0                | 0.0             | 2018/06/18 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
#      | 213481/CONS_LATAM_BR12/112368557 | YES    | YES          | NO           |         | 0.0             | NO          | PA          | 112368557  |                      |          | CONS_LATAM_BR12 | 0/213481/CONS_LATAM_BR12 | Production    | 213481    | 0.0             | 5600     | 2018/06/13 00:00:00 | 0.0                | 0.0             | 2018/06/18 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
#
#    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
#      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |
#
#  Scenario: delete all test data
#
#    Then I delete the test data
#
##    And I will remove all data with region "/omp/gdm_stock"
#
#    And I will remove all data with region "/plan/edm_failed_data"
#
#  Scenario: check rule PLO6
#
#    And I will remove the test file on sink application "GDMStock_plannedOrder.tsv"
#
#    Given I import "/edm/planned_order_v1" by keyFields "mfgPlanOrdrDocId,srcSysCd"
#      | matlNum | srcSysCd   | mfgPlanOrdrDocId | plntCd | prdtnVersNum | planOrdrEndDt | grDaysLeadQty | plngScnroCd | planOrdrTypeCd | planOrdrQty |
##      PLO6(if plngScnroCd = 000)
#      | 138821  | CONS_LATAM | 49748397         | BR12   | 0            | 20180610      | 0             | 000         | LA             | 36300       |
#      | 441423  | CONS_LATAM | 117229550        | BR12   | 0            | 20180611      | 2             | 000         | EL             | 12132       |
#      | 189915  | CONS_LATAM | 95941803         | BR12   | 0            | 20180612      | 4             | 000         | NB             | 86544       |
##      PLO6(if planOrdrTypeCd does not match sourceObjectAttribute2Value in cns_plan_object_filter, skip the record)
#      | 189916  | CONS_LATAM | 959412343        | BR12   | 0            | 20180612      | 4             | 000         | AA             | 86544       |
##      PLO6(if plngScnroCd != 000, skip the record)
#      | 213481  | CONS_LATAM | 112368557        | BR12   | 0            | 20180613      | 4             |             | LA             | 5600        |
#    And I wait "/edm/planned_order_v1" Async Queue complete
#
#    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber,sourceSystem"
#      | localMaterialNumber | sourceSystem | primaryPlanningCode | materialNumber |
#      | 138821              | CONS_LATAM   | 138821              | 234312         |
#      | 441423              | CONS_LATAM   | 441423              | 441423         |
#      | 189915              | CONS_LATAM   | 189915              |                |
#      | 213481              | CONS_LATAM   |                     | 213481         |
#    And I wait "/edm/material_global_v1" Async Queue complete
#
#    Given I import "/edm/plant_v1" by keyFields "localPlant,sourceSystem"
#      | localPlant | sourceSystem | localPlanningRelevant |
#      | BR12       | CONS_LATAM   | X                     |
#    And I wait "/edm/material_global_v1" Async Queue complete
#
#    Given I import "/plan/cns_material_plan_status" by keyFields "localMaterialNumber,localPlant,sourceSystem"
#      | localMaterialNumber | localPlant | sourceSystem | noPlanRelevant |
#      | 138821              | BR12       | CONS_LATAM   | X              |
#      | 441423              | BR12       | CONS_LATAM   | X              |
#      | 189915              | BR12       | CONS_LATAM   | X              |
#      | 213481              | BR12       | CONS_LATAM   | X              |
#    And I wait "/plan/cns_material_plan_status" Async Queue complete
#
#    Given I import "/plan/cns_plan_object_filter" by keyFields "sourceObjectAttribute1,sourceObjectAttribute2,sourceObjectAttribute2Value,sourceObjectTechName,sourceSystem"
#      | sourceObjectAttribute1 | sourceObjectAttribute2 | sourceObjectAttribute2Value | sourceObjectTechName | sourceSystem |
##      PLO6(satisfied)
#      | plntCd                 | planOrdrTypeCd         | LA                          | planned_order        | CONS_LATAM   |
##      PLO6(if sourceObjectTechName != planned_order_v1, skip the record)
#      | plntCd                 | planOrdrTypeCd         | EL                          | planning             | CONS_LATAM   |
##      PLO6(if sourceSystem != srcSysCd, skip the record)
#      | plntCd                 | planOrdrTypeCd         | NB                          | planned_order        | EMS          |
##      PLO6(if sourceObjectAttribute1 != plntCd, skip the record)
#      | plnt                   | planOrdrTypeCd         | LA                          | planned_order        | CONS_LATAM   |
#    And I wait "/plan/cns_plan_object_filter" Async Queue complete
#
#    When I submit task with xml file "xml/gdm_stock_5_planned_order.xml" and execute file "jar/pangea-view.jar"
#
#    Then A file is found on sink application with name "GDMStock_plannedOrder.tsv"
#
#    Then I check file data for filename "GDMStock_plannedOrder.tsv" by keyFields "stockId"
##    Then I check region data "/omp/gdm_stock" by keyFields "stockId"
#      | stockId                         | active | activeOPRERP | activeSOPERP | batchId | blockedQuantity | consignment | certaintyId | erpOrderId | inventoryLinkGroupId | vendorId | locationId      | processId                | processTypeId | productId | qualityQuantity | quantity | receiptDate         | restrictedQuantity | returnsQuantity | startDate           | stockType | transferQuantity | transitDate         | unrestrictedQuantity |
#      | 138821/CONS_LATAM_BR12/49748397 | YES    | YES          | NO           |         | 0.0             | NO          | PA          | 49748397   |                      |          | CONS_LATAM_BR12 | 0/138821/CONS_LATAM_BR12 | Production    | 138821    | 0.0             | 36300    | 2018/06/10 00:00:00 | 0.0                | 0.0             | 2018/06/11 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
#
#    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
#      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |
#
#  Scenario: delete all test data
#
#    Then I delete the test data
#
#    And I will remove all data with region "/omp/gdm_stock"
#
#    And I will remove all data with region "/plan/edm_failed_data"
#
#  Scenario: check rule PLO7 and rule PLO11
#
#    And I will remove the test file on sink application "GDMStock_plannedOrder.tsv"
#
#    Given I import "/edm/planned_order_v1" by keyFields "mfgPlanOrdrDocId,srcSysCd"
#      | matlNum | srcSysCd   | mfgPlanOrdrDocId | plntCd | prdtnVersNum | planOrdrEndDt | grDaysLeadQty | plngScnroCd | planOrdrTypeCd | planOrdrQty |
##     PLO11(if planOrdrEndDt is the weekend and grDaysLeadQty = 0)
#      | 138821  | CONS_LATAM | 49748397         | BR12   | 0            | 20180610      | 0             | 000         | LA             | 36300       |
##     PLO11(if planOrdrEndDt + grDaysLeadQty is the weekday)
#      | 441423  | CONS_LATAM | 117229550        | BR12   | 0            | 20180611      | 2             | 000         | LA             | 12132       |
##     PLO11(if planOrdrEndDt + grDaysLeadQty is saturday)
#      | 189915  | CONS_LATAM | 95941803         | BR12   | 0            | 20180612      | 4             | 000         | LA             | 86544       |
##     PLO11(if planOrdrEndDt + grDaysLeadQty is sunday)
#      | 213481  | CONS_LATAM | 112368557        | BR12   | 0            | 20180613      | 4             | 000         | LA             | 5600        |
##      PLO7(if localPlanningRelevant != X, skip the record)
#      | 138822  | CONS_LATAM | 49748345         | AR01   | 0            | 20180610      | 0             | 000         | LA             | 36300       |
#      |         | CONS_LATAM | 65864523         |        |              |               |               |             |                |             |
#    And I wait "/edm/planned_order_v1" Async Queue complete
#
#    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber,sourceSystem"
#      | localMaterialNumber | sourceSystem | primaryPlanningCode | materialNumber |
#      | 138821              | CONS_LATAM   | 138821              | 234312         |
#      | 441423              | CONS_LATAM   | 441423              | 441423         |
#      | 189915              | CONS_LATAM   | 189915              |                |
#      | 213481              | CONS_LATAM   |                     | 213481         |
#    And I wait "/edm/material_global_v1" Async Queue complete
#
#    Given I import "/edm/plant_v1" by keyFields "localPlant,sourceSystem"
#      | localPlant | sourceSystem | localPlanningRelevant |
##      PLO7(if localPlanningRelevant = X)
#      | BR12       | CONS_LATAM   | X                     |
##      PLO7(if localPlanningRelevant != X, skip the record)
#      | AR01       | CONS_LATAM   |                       |
#    And I wait "/edm/material_global_v1" Async Queue complete
#
#    Given I import "/plan/cns_material_plan_status" by keyFields "localMaterialNumber,localPlant,sourceSystem"
#      | localMaterialNumber | localPlant | sourceSystem | noPlanRelevant |
#      | 138821              | BR12       | CONS_LATAM   | X              |
#      | 441423              | BR12       | CONS_LATAM   | X              |
#      | 189915              | BR12       | CONS_LATAM   | X              |
#      | 213481              | BR12       | CONS_LATAM   | X              |
##     PLO9(if noPlanRelevant != X, skip the record)
#      | 213482              | BR12       | CONS_LATAM   |                |
#    And I wait "/plan/cns_material_plan_status" Async Queue complete
#
#    Given I import "/plan/cns_plan_object_filter" by keyFields "sourceObjectAttribute1,sourceObjectAttribute2,sourceObjectAttribute2Value,sourceObjectTechName,sourceSystem"
#      | sourceObjectAttribute1 | sourceObjectAttribute2 | sourceObjectAttribute2Value | sourceObjectTechName | sourceSystem |
#      | plntCd                 | planOrdrTypeCd         | LA                          | planned_order        | CONS_LATAM   |
#    And I wait "/plan/cns_plan_object_filter" Async Queue complete
#
#    When I submit task with xml file "xml/gdm_stock_5_planned_order.xml" and execute file "jar/pangea-view.jar"
#
#    Then A file is found on sink application with name "GDMStock_plannedOrder.tsv"
#
#    Then I check file data for filename "GDMStock_plannedOrder.tsv" by keyFields "stockId"
##    Then I check region data "/omp/gdm_stock" by keyFields "stockId"
#      | stockId                          | active | activeOPRERP | activeSOPERP | batchId | blockedQuantity | consignment | certaintyId | erpOrderId | inventoryLinkGroupId | vendorId | locationId      | processId                | processTypeId | productId | qualityQuantity | quantity | receiptDate         | restrictedQuantity | returnsQuantity | startDate           | stockType | transferQuantity | transitDate         | unrestrictedQuantity |
#      | 138821/CONS_LATAM_BR12/49748397  | YES    | YES          | NO           |         | 0.0             | NO          | PA          | 49748397   |                      |          | CONS_LATAM_BR12 | 0/138821/CONS_LATAM_BR12 | Production    | 138821    | 0.0             | 36300    | 2018/06/10 00:00:00 | 0.0                | 0.0             | 2018/06/11 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
#      | 441423/CONS_LATAM_BR12/117229550 | YES    | YES          | NO           |         | 0.0             | NO          | PA          | 117229550  |                      |          | CONS_LATAM_BR12 | 0/441423/CONS_LATAM_BR12 | Production    | 441423    | 0.0             | 12132    | 2018/06/11 00:00:00 | 0.0                | 0.0             | 2018/06/13 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
#      | 189915/CONS_LATAM_BR12/95941803  | YES    | YES          | NO           |         | 0.0             | NO          | PA          | 95941803   |                      |          | CONS_LATAM_BR12 | 0/189915/CONS_LATAM_BR12 | Production    | 189915    | 0.0             | 86544    | 2018/06/12 00:00:00 | 0.0                | 0.0             | 2018/06/18 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
#      | 213481/CONS_LATAM_BR12/112368557 | YES    | YES          | NO           |         | 0.0             | NO          | PA          | 112368557  |                      |          | CONS_LATAM_BR12 | 0/213481/CONS_LATAM_BR12 | Production    | 213481    | 0.0             | 5600     | 2018/06/13 00:00:00 | 0.0                | 0.0             | 2018/06/18 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
#
#    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
#      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |
#
#  Scenario: delete all test data
#
#    Then I delete the test data
#
#    And I will remove all data with region "/omp/gdm_stock"
#
#    And I will remove all data with region "/plan/edm_failed_data"

  Scenario: full load

    And I will remove the test file on sink application "GDMStock_plannedOrder.tsv"

    Given I import "/edm/planned_order_v1" by keyFields "mfgPlanOrdrDocId,srcSysCd"
      | firmingInd | fxScrapQty | grDaysLeadQty | matlNum | mfgPlanOrdrDocId | mrpCtlId | planOrdrEndDt | planOrdrEndTm | planOrdrQty | planOrdrStrtDt | planOrdrTypeCd | plntCd | plngScnroCd | prcmtTypeCd | prdtnFnshdDt | prdtnStrtDt | prdtnStrtTm | prdtnVers | prdtnVersNum | reqQty | sLocCd | splPrcmtTypeCd | srcSysCd   | uomCd |
      | X          | 0          | 1             | 138821  | 49748397         | H00      | 20180904      | 0             | 36300       | 20180903       | LA             | BR12   | 000         | E           | 0            | 0           | 0           | V001      | 0            | 0      | BR28   | E              | CONS_LATAM | EA    |
      | X          | 0          | 1             | 138821  | 49748398         | H00      | 20180508      | 0             | 38016       | 20180507       | LA             | BR12   | 000         | E           | 0            | 0           | 0           | V001      | 0            | 0      | BR28   | E              | CONS_LATAM | EA    |
      | X          | 0          | 1             | 138821  | 49748399         | H00      | 20190319      | 0             | 72000       | 20190318       | LA             | BR12   | 000         | E           | 0            | 0           | 0           | V001      | 0            | 0      | BR28   | E              | CONS_LATAM | EA    |
      | X          | 0          | 2             | 189915  | 95941803         | C11      | 20181203      | 0             | 86544       | 20181203       | LA             | BR12   | 000         | E           | 0            | 0           | 0           | V001      | 0            | 0      | BR28   | E              | CONS_LATAM | EA    |
      |            | 0          | 0             | 213481  | 112368557        | C61      | 20180808      | 240000        | 5600        | 20180808       | LA             | BR12   | 000         | E           | 0            | 0           | 0           | V001      | 0            | 0      | CO05   | E              | CONS_LATAM | L     |
      | X          | 0          | 6             | 441423  | 117229550        | A04      | 20180621      | 0             | 12132       | 20180620       | LA             | BR12   | 000         | E           | 0            | 0           | 0           | V001      | 0            | 0      | BR23   | E              | CONS_LATAM | EA    |
      | X          | 0          | 6             | 654321  | 117229550        | A04      | 20180621      | 0             | 12132       | 20180620       | LA             | BR12   | 000         | E           | 0            | 0           | 0           | V001      | 0            | 0      | BR23   | E              | CONS_LATAM | EA    |
      | X          | 0          | 2             | 138821  | 112370817        | 51       | 20190318      | 0             | 31200       | 20190318       | LA             | BR12   | 000         | E           | 0            | 0           | 0           | V001      | 0            | 0      | BR26   | E              | CONS_LATAM | EA    |
      | X          | 0          | 7             | 441346  | 117076013        | C61      | 20190423      | 0             | 26400       | 20190422       | LA             | AR01   | 000         | E           | 0            | 0           | 0           | V001      | 0            | 0      | ARO1   | E              | CONS_LATAM | EA    |
      | X          | 0          | 2             | 174461  | 112484003        | 51       | 20190318      | 0             | 31200       | 20190318       | LA             | CO01   | 000         | E           | 0            | 0           | 0           | V001      | 0            | 0      | CO01   | E              | CONS_LATAM | EA    |
      | X          | 0          | 7             | 441346  | 117076013        | C61      | 20190423      | 0             | 26400       | 20190422       | LA             | AR01   | 000         | E           | 0            | 0           | 0           | V001      | 0            | 0      | AR01   | E              | CONS_LATAM | EA    |
      | X          | 0          | 7             | 441349  | 117076014        | C61      | 20190423      | 0             | 26400       | 20190422       | LA             | AR01   | 001         | E           | 0            | 0           | 0           | V001      | 0            | 0      | AR01   | E              | CONS_LATAM | EA    |
      | X          | 0          | 7             | 441363  | 117076014        | C61      | 20190423      | 0             | 26400       | 20190422       | LA             | AR01   | 000         | E           | 0            | 0           | 0           | V001      | 0            | 0      | AR01   | E              | CONS_LATAM | EA    |
    And I wait "/edm/planned_order_v1" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber,sourceSystem"
      | baseUom | batchManageIndicator | brand  | category | division | form   | franchise | getPrimaryPlanningCode | globalBusinessUnit | globalDpParentCode | localBaseUom | localDpParentCode | localManufacturingTechnology | localMaterialGroup | localMaterialNumber | localMaterialType | localRefDescription                      | materialNumber | materialStatus | materialType | minRemShelfLife | parentCode | primaryPlanningCode | productFamily | refDescription                           | refdescription | sourceSystem | subBrand | totalShelfLife |
      | EA      | X                    | BRD006 | 280      | 10       | 112684 | FCH002    |                        | GFO001             |                    | EA           | 78910109421200000 | Toiletries BRA               | 1                  | 138821              | FERT              | NTG SUNFRESH FPS60 FACIAL OC COR 50ML    | 138821         | 8              | FERT         | 180             |            | 138821              |               | NTG SUNFRESH FPS60 FACIAL OC COR 50ML    |                | CONS_LATAM   | 3G       | 730            |
      | EA      | X                    |        |          | 10       |        |           |                        |                    |                    | EA           |                   |                              | 1                  | 138821              | FERT              | STAY ULTRADELGADA DP AZUL 6X60 UN        | 138821         | 8              | FERT         | 180             |            | 138821              |               | STAY ULTRADELGADA DP AZUL 6X60 UN        |                | CONS_LATAM   |          | 730            |
      | EA      | X                    | BRD014 | 135      | 10       | 100367 | FCH003    |                        | GFO002             |                    | EA           | 78910109255430000 | Toothbrushes                 | 1                  | 138821              | FERT              | ESC J&J REACH ECO ESSEN MC 72UN          | 138821         | 8              | FERT         | 180             |            | 138821              |               | ESC J&J REACH ECO ESSEN MC 72UN          |                | CONS_LATAM   | 5H       | 9999           |
      | EA      | X                    |        |          | 10       |        |           |                        |                    |                    | EA           |                   |                              | 1                  | 189915              | FERT              | LISTERINE NAT. CITRUS 12/500ML           | 189915         | 8              | FERT         | 180             |            | 189915              |               | LISTERINE NAT. CITRUS 12/500ML           |                | CONS_LATAM   |          | 730            |
      | EA      | X                    |        |          | 10       |        |           |                        |                    |                    | EA           |                   |                              | 1                  | 213481              | FERT              | NEU RAPD CLEAR MASK GEL 6X50GR           |                | 8              | FERT         | 180             |            | 213481              |               | NEU RAPD CLEAR MASK GEL 6X50GR           |                | CONS_LATAM   |          | 730            |
      | EA      | X                    |        |          | 10       |        |           |                        |                    |                    | EA           |                   |                              | 1                  | 441423              | FERT              | LISTERINE CUIDADO TOTAL 12X250ML         | 441423         | 8              | FERT         | 180             |            | 441423              |               | LISTERINE CUIDADO TOTAL 12X250ML         |                | CONS_LATAM   |          | 730            |
      | EA      | X                    |        |          | 10       |        |           |                        |                    |                    | EA           |                   |                              | 1                  | 654321              | SAPR              | FS SER CLARIFY ROC CLARIFIANT 1,5ML BIL  | 654321         | 8              | FERT         | 180             |            | 654321              |               | FS SER CLARIFY ROC CLARIFIANT 1,5ML BIL  |                | CONS_LATAM   |          | 730            |
      | L       | X                    |        |          | 10       |        |           |                        |                    |                    | L            |                   |                              | 2                  | 138821              | HALB              | Shampoo Cabellos Oscuros LATC 1459 - 024 | 138821         | 8              | HALB         | 1               |            | 138821              |               | Shampoo Cabellos Oscuros LATC 1459 - 024 |                | CONS_LATAM   |          | 730            |
      | EA      | X                    |        |          | 10       |        |           |                        |                    |                    | EA           |                   |                              | 3                  | 441346              | FERT              | JB JABON NEUTRO 75G 2 PACK C/24          | 441346         | 8              | FERT         | 365             |            | 441346              |               | JB JABON NEUTRO 75G 2 PACK C/24          |                | CONS_LATAM   |          | 730            |
      | L       | X                    |        |          | 10       |        |           |                        |                    |                    | L            |                   |                              | 2                  | 174461              | HALB              | Shampoo Cabellos Oscuros LATC 1459 - 024 | 174461         | 8              | HALB         | 1               |            | 174461              |               | Shampoo Cabellos Oscuros LATC 1459 - 024 |                | CONS_LATAM   |          | 730            |
      | EA      | X                    |        |          | 10       |        |           |                        |                    |                    | EA           |                   |                              | 3                  | 441346              | FERT              | JB JABON NEUTRO 75G 2 PACK C/24          | 441346         | 8              | FERT         | 365             |            | 441346              |               | JB JABON NEUTRO 75G 2 PACK C/24          |                | CONS_LATAM   |          | 730            |
      | EA      | X                    |        |          | 10       |        |           |                        |                    |                    | EA           |                   |                              | 3                  | 441349              | FERT              | JB JABON NEUTRO 75G 2 PACK C/24          | 441350         | 8              | FERT         | 365             |            | 441351              |               | JB JABON NEUTRO 75G 2 PACK C/24          |                | CONS_LATAM   |          | 730            |
      | EA      | X                    |        |          | 10       |        |           |                        |                    |                    | EA           |                   |                              | 3                  | 441363              | FERT              | JB JABON NEUTRO 75G 2 PACK C/24          | 441364         | 8              | FERT         | 365             |            | 441365              |               | JB JABON NEUTRO 75G 2 PACK C/24          |                | CONS_LATAM   |          | 730            |

    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/edm/plant_v1" by keyFields "localPlant,sourceSystem"
      | localPlant | sourceSystem | localPlanningRelevant |
      | BR12       | CONS_LATAM   | X                     |
      | AR01       | CONS_LATAM   |                       |
      | CO01       | CONS_LATAM   | X                     |
    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/plan/cns_material_plan_status" by keyFields "localMaterialNumber,localPlant,sourceSystem"
      | active | dpRelevant | localMaterialNumber | localParentCode   | localPlant | materialNumber | noPlanRelevant | parentActive | ppc    | sourceSystem | spRelevant |
      | X      |            | 138821              |                   | BR12       | 138821         | X              |              | 138821 | CONS_LATAM   |            |
      | X      | X          | 138821              | 78912700000000000 | BR12       | 138821         | X              | X            | 138821 | CONS_LATAM   |            |
      | X      | X          | 138821              | 78910100000000000 | BR12       | 138821         | X              | X            | 138821 | CONS_LATAM   |            |
      | X      | X          | 189915              | 580100240         | BR12       | 189915         | X              | X            | 189915 | CONS_LATAM   |            |
      | X      | X          | 213481              | 78910100000000000 | BR12       | 213481         | X              | X            | 213481 | CONS_LATAM   |            |
      | X      | X          | 441423              | 975930150         | BR12       | 441423         | X              | X            | 441423 | CONS_LATAM   | X          |
      | X      |            | 654321              |                   | BR12       | 654321         | X              |              | 654321 | CONS_LATAM   | X          |
      | X      |            | 138821              |                   | BR12       | 138821         | X              |              | 138821 | CONS_LATAM   |            |
      | X      |            | 441346              |                   | AR01       | 441346         | X              |              | 441346 | CONS_LATAM   | X          |
      | X      | X          | 174461              |                   | CO01       | 138821         | X              |              | 138821 | CONS_LATAM   |            |
      | X      | X          | 441346              |                   | AR01       | 441346         | X              |              | 441346 | CONS_LATAM   | X          |
      | X      | X          | 441349              |                   | AR01       | 441350         | X              |              | 441351 | CONS_LATAM   | X          |
      | X      | X          | 441363              |                   | AR01       | 441364         | X              |              | 441365 | CONS_LATAM   | X          |

    And I wait "/plan/cns_material_plan_status" Async Queue complete

    Given I import "/plan/cns_plan_object_filter" by keyFields "sourceObjectAttribute1,sourceObjectAttribute2,sourceObjectAttribute2Value,sourceObjectTechName,sourceSystem"
      | inclusionExclusion | sourceObjectAttribute1 | sourceObjectAttribute1Value | sourceObjectAttribute2 | sourceObjectAttribute2Value | sourceObjectTechName | sourceSystem |
      | I                  | plntCd                 | BR12                        | planOrdrTypeCd         | NB                          | planned_order        | CONS_LATAM   |
      | I                  | plntCd                 | CO01                        | planOrdrTypeCd         | NB                          | planned_order        | CONS_LATAM   |
      | I                  | plntCd                 | AR01                        | planOrdrTypeCd         | NB                          | planned_order        | CONS_LATAM   |
      | I                  | plntCd                 | BR12                        | planOrdrTypeCd         | LA                          | planned_order        | CONS_LATAM   |
      | I                  | plntCd                 | CO01                        | planOrdrTypeCd         | LA                          | planned_order        | CONS_LATAM   |
      | I                  | plntCd                 | AR01                        | planOrdrTypeCd         | LA                          | planned_order        | CONS_LATAM   |
      | E                  | plntCd                 | AE01                        | planOrdrTypeCd         | LA                          | planned_order        | CONS_LATAM   |

    And I wait "/plan/cns_plan_object_filter" Async Queue complete

    When I submit task with xml file "xml/gdm_stock_5_planned_order.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMStock_plannedOrder.tsv"

    Then I check file data for filename "GDMStock_plannedOrder.tsv" by keyFields "stockId"
#    Then I check region data "/omp/gdm_stock" by keyFields "stockId"
      | stockId                          | active | activeOPRERP | activeSOPERP | batchId | blockedQuantity | consignment | certaintyId | erpOrderId | inventoryLinkGroupId | vendorId | locationId      | processId               | processTypeId | productId | qualityQuantity | quantity | receiptDate         | restrictedQuantity | returnsQuantity | startDate           | stockType | transferQuantity | transitDate         | unrestrictedQuantity |
      | 138821/CONS_LATAM_BR12/49748397  | YES    | YES          | NO           |         | 0.0             | NO          | PA          | 49748397   |                      |          | CONS_LATAM_BR12 | /138821/CONS_LATAM_BR12 | Production    | 138821    | 0.0             | 36300    | 2018/09/04 00:00:00 | 0.0                | 0.0             | 2018/09/05 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
      | 189915/CONS_LATAM_BR12/95941803  | YES    | YES          | NO           |         | 0.0             | NO          | PA          | 95941803   |                      |          | CONS_LATAM_BR12 | /189915/CONS_LATAM_BR12 | Production    | 189915    | 0.0             | 86544    | 2018/12/03 00:00:00 | 0.0                | 0.0             | 2018/12/05 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
      | 138821/CONS_LATAM_BR12/112370817 | YES    | YES          | NO           |         | 0.0             | NO          | PA          | 112370817  |                      |          | CONS_LATAM_BR12 | /138821/CONS_LATAM_BR12 | Production    | 138821    | 0.0             | 86544    | 2019/03/18 00:00:00 | 0.0                | 0.0             | 2019/03/20 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
      | 174461/CONS_LATAM_CO01/112484003 | YES    | YES          | NO           |         | 0.0             | NO          | PA          | 112484003  |                      |          | CONS_LATAM_CO01 | /174461/CONS_LATAM_CO01 | Production    | 174461    | 0.0             | 86544    | 2019/03/18 00:00:00 | 0.0                | 0.0             | 2019/03/20 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

  Scenario: delete all test data

    Then I delete the test data

#    And I will remove all data with region "/omp/gdm_stock"

    And I will remove all data with region "/plan/edm_failed_data"
