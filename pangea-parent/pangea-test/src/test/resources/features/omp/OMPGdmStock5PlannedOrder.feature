@pangea_test @AEAZ-5951
Feature: OMPGdmStock AEAZ-5951
  As a Data user, I want EDG to create GDM file for GDMStock 5 and send to OMP so that data can be consumed by OMP for planning

  Scenario: check rule PLO9

    And I will remove the test file on sink application "GDMStock_plannedOrder.tsv"

    Given I import "/edm/planned_order_v1" by keyFields "mfgPlanOrdrDocId,srcSysCd"
      | matlNum | srcSysCd   | mfgPlanOrdrDocId | plntCd | prdtnVersNum | planOrdrEndDt | grDaysLeadQty | plngScnroCd | planOrdrTypeCd | planOrdrQty |
      | 138821  | CONS_LATAM | 49748397         | BR12   | 0            | 20180610      | 0             | 000         | LA             | 36300       |
      | 441423  | CONS_LATAM | 117229550        | BR12   | 0            | 20180611      | 2             | 000         | LA             | 12132       |
      | 189915  | CONS_LATAM | 95941803         | BR12   | 0            | 20180612      | 4             | 000         | LA             | 86544       |
      | 213481  | CONS_LATAM | 112368557        | BR12   | 0            | 20180613      | 4             | 000         | LA             | 5600        |
      | 213482  | CONS_LATAM | 49748312         | BR12   | 0            | 20180613      | 4             | 000         | LA             | 5600        |
    And I wait "/edm/planned_order_v1" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber,sourceSystem"
      | localMaterialNumber | sourceSystem | primaryPlanningCode | materialNumber |
#     PLO9(if primaryPlanningCode and materialNumber are not empty and not same)
      | 138821              | CONS_LATAM   | 138821              | 234312         |
#     PLO9(if primaryPlanningCode and materialNumber are not empty and same)
      | 441423              | CONS_LATAM   | 441423              | 441423         |
#     PLO9(if primaryPlanningCode is not empty and and materialNumber is empty)
      | 189915              | CONS_LATAM   | 189915              |                |
#     PLO9(if primaryPlanningCode is empty and and materialNumber is not empty)
      | 213481              | CONS_LATAM   |                     | 213481         |
    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/edm/plant_v1" by keyFields "localPlant,sourceSystem"
      | localPlant | sourceSystem | localPlanningRelevant |
      | BR12       | CONS_LATAM   | X                     |
    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/plan/cns_material_plan_status" by keyFields "localMaterialNumber,localPlant,sourceSystem"
      | localMaterialNumber | localPlant | sourceSystem | noPlanRelevant |
      | 138821              | BR12       | CONS_LATAM   | X              |
      | 441423              | BR12       | CONS_LATAM   | X              |
      | 189915              | BR12       | CONS_LATAM   | X              |
      | 213481              | BR12       | CONS_LATAM   | X              |
#     PLO9(if noPlanRelevant != X, skip the record)
      | 213482              | BR12       | CONS_LATAM   |                |
    And I wait "/plan/cns_material_plan_status" Async Queue complete

    Given I import "/plan/cns_plan_object_filter" by keyFields "sourceObjectAttribute1,sourceObjectAttribute2,sourceObjectAttribute2Value,sourceObjectTechName,sourceSystem"
      | sourceObjectAttribute1 | sourceObjectAttribute2 | sourceObjectAttribute2Value | sourceObjectTechName | sourceSystem |
      | plntCd                 | planOrdrTypeCd         | LA                          | planned_order        | CONS_LATAM   |
    And I wait "/plan/cns_plan_object_filter" Async Queue complete

    When I submit task with xml file "xml/gdm_stock_5_planned_order.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMStock_plannedOrder.tsv"

    Then I check file data for filename "GDMStock_plannedOrder.tsv" by keyFields "stockId"
#    Then I check region data "/omp/gdm_stock" by keyFields "stockId"
      | stockId                          | active | activeOPRERP | activeSOPERP | batchId | blockedQuantity | consignment | certaintyId | erpOrderId | inventoryLinkGroupId | vendorId | locationId      | processId                | processTypeId | productId | qualityQuantity | quantity | receiptDate         | restrictedQuantity | returnsQuantity | startDate           | stockType | transferQuantity | transitDate         | unrestrictedQuantity |
      | 138821/CONS_LATAM_BR12/49748397  | YES    | YES          | NO           |         | 0.0             | NO          | PA          | 49748397   |                      |          | CONS_LATAM_BR12 | 0/138821/CONS_LATAM_BR12 | Production    | 138821    | 0.0             | 36300    | 2018/06/10 00:00:00 | 0.0                | 0.0             | 2018/06/11 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
      | 441423/CONS_LATAM_BR12/117229550 | YES    | YES          | NO           |         | 0.0             | NO          | PA          | 117229550  |                      |          | CONS_LATAM_BR12 | 0/441423/CONS_LATAM_BR12 | Production    | 441423    | 0.0             | 12132    | 2018/06/11 00:00:00 | 0.0                | 0.0             | 2018/06/13 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
      | 189915/CONS_LATAM_BR12/95941803  | YES    | YES          | NO           |         | 0.0             | NO          | PA          | 95941803   |                      |          | CONS_LATAM_BR12 | 0/189915/CONS_LATAM_BR12 | Production    | 189915    | 0.0             | 86544    | 2018/06/12 00:00:00 | 0.0                | 0.0             | 2018/06/18 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
      | 213481/CONS_LATAM_BR12/112368557 | YES    | YES          | NO           |         | 0.0             | NO          | PA          | 112368557  |                      |          | CONS_LATAM_BR12 | 0/213481/CONS_LATAM_BR12 | Production    | 213481    | 0.0             | 5600     | 2018/06/13 00:00:00 | 0.0                | 0.0             | 2018/06/18 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

  Scenario: delete all test data

    Then I delete the test data

#    And I will remove all data with region "/omp/gdm_stock"

    And I will remove all data with region "/plan/edm_failed_data"

  Scenario: check rule PLO6

    And I will remove the test file on sink application "GDMStock_plannedOrder.tsv"

    Given I import "/edm/planned_order_v1" by keyFields "mfgPlanOrdrDocId,srcSysCd"
      | matlNum | srcSysCd   | mfgPlanOrdrDocId | plntCd | prdtnVersNum | planOrdrEndDt | grDaysLeadQty | plngScnroCd | planOrdrTypeCd | planOrdrQty |
#      PLO6(if plngScnroCd = 000)
      | 138821  | CONS_LATAM | 49748397         | BR12   | 0            | 20180610      | 0             | 000         | LA             | 36300       |
      | 441423  | CONS_LATAM | 117229550        | BR12   | 0            | 20180611      | 2             | 000         | EL             | 12132       |
      | 189915  | CONS_LATAM | 95941803         | BR12   | 0            | 20180612      | 4             | 000         | NB             | 86544       |
#      PLO6(if planOrdrTypeCd does not match sourceObjectAttribute2Value in cns_plan_object_filter, skip the record)
      | 189916  | CONS_LATAM | 959412343        | BR12   | 0            | 20180612      | 4             | 000         | AA             | 86544       |
#      PLO6(if plngScnroCd != 000, skip the record)
      | 213481  | CONS_LATAM | 112368557        | BR12   | 0            | 20180613      | 4             |             | LA             | 5600        |
    And I wait "/edm/planned_order_v1" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber,sourceSystem"
      | localMaterialNumber | sourceSystem | primaryPlanningCode | materialNumber |
      | 138821              | CONS_LATAM   | 138821              | 234312         |
      | 441423              | CONS_LATAM   | 441423              | 441423         |
      | 189915              | CONS_LATAM   | 189915              |                |
      | 213481              | CONS_LATAM   |                     | 213481         |
    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/edm/plant_v1" by keyFields "localPlant,sourceSystem"
      | localPlant | sourceSystem | localPlanningRelevant |
      | BR12       | CONS_LATAM   | X                     |
    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/plan/cns_material_plan_status" by keyFields "localMaterialNumber,localPlant,sourceSystem"
      | localMaterialNumber | localPlant | sourceSystem | noPlanRelevant |
      | 138821              | BR12       | CONS_LATAM   | X              |
      | 441423              | BR12       | CONS_LATAM   | X              |
      | 189915              | BR12       | CONS_LATAM   | X              |
      | 213481              | BR12       | CONS_LATAM   | X              |
    And I wait "/plan/cns_material_plan_status" Async Queue complete

    Given I import "/plan/cns_plan_object_filter" by keyFields "sourceObjectAttribute1,sourceObjectAttribute2,sourceObjectAttribute2Value,sourceObjectTechName,sourceSystem"
      | sourceObjectAttribute1 | sourceObjectAttribute2 | sourceObjectAttribute2Value | sourceObjectTechName | sourceSystem |
#      PLO6(satisfied)
      | plntCd                 | planOrdrTypeCd         | LA                          | planned_order        | CONS_LATAM   |
#      PLO6(if sourceObjectTechName != planned_order_v1, skip the record)
      | plntCd                 | planOrdrTypeCd         | EL                          | planning             | CONS_LATAM   |
#      PLO6(if sourceSystem != srcSysCd, skip the record)
      | plntCd                 | planOrdrTypeCd         | NB                          | planned_order        | EMS          |
#      PLO6(if sourceObjectAttribute1 != plntCd, skip the record)
      | plnt                   | planOrdrTypeCd         | LA                          | planned_order        | CONS_LATAM   |
    And I wait "/plan/cns_plan_object_filter" Async Queue complete

    When I submit task with xml file "xml/gdm_stock_5_planned_order.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMStock_plannedOrder.tsv"

    Then I check file data for filename "GDMStock_plannedOrder.tsv" by keyFields "stockId"
#    Then I check region data "/omp/gdm_stock" by keyFields "stockId"
      | stockId                         | active | activeOPRERP | activeSOPERP | batchId | blockedQuantity | consignment | certaintyId | erpOrderId | inventoryLinkGroupId | vendorId | locationId      | processId                | processTypeId | productId | qualityQuantity | quantity | receiptDate         | restrictedQuantity | returnsQuantity | startDate           | stockType | transferQuantity | transitDate         | unrestrictedQuantity |
      | 138821/CONS_LATAM_BR12/49748397 | YES    | YES          | NO           |         | 0.0             | NO          | PA          | 49748397   |                      |          | CONS_LATAM_BR12 | 0/138821/CONS_LATAM_BR12 | Production    | 138821    | 0.0             | 36300    | 2018/06/10 00:00:00 | 0.0                | 0.0             | 2018/06/11 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/omp/gdm_stock"

    And I will remove all data with region "/plan/edm_failed_data"

  Scenario: check rule PLO7 and rule PLO11

    And I will remove the test file on sink application "GDMStock_plannedOrder.tsv"

    Given I import "/edm/planned_order_v1" by keyFields "mfgPlanOrdrDocId,srcSysCd"
      | matlNum | srcSysCd   | mfgPlanOrdrDocId | plntCd | prdtnVersNum | planOrdrEndDt | grDaysLeadQty | plngScnroCd | planOrdrTypeCd | planOrdrQty |
#     PLO11(if planOrdrEndDt is the weekend and grDaysLeadQty = 0)
      | 138821  | CONS_LATAM | 49748397         | BR12   | 0            | 20180610      | 0             | 000         | LA             | 36300       |
#     PLO11(if planOrdrEndDt + grDaysLeadQty is the weekday)
      | 441423  | CONS_LATAM | 117229550        | BR12   | 0            | 20180611      | 2             | 000         | LA             | 12132       |
#     PLO11(if planOrdrEndDt + grDaysLeadQty is saturday)
      | 189915  | CONS_LATAM | 95941803         | BR12   | 0            | 20180612      | 4             | 000         | LA             | 86544       |
#     PLO11(if planOrdrEndDt + grDaysLeadQty is sunday)
      | 213481  | CONS_LATAM | 112368557        | BR12   | 0            | 20180613      | 4             | 000         | LA             | 5600        |
#      PLO7(if localPlanningRelevant != X, skip the record)
      | 138822  | CONS_LATAM | 49748345         | AR01   | 0            | 20180610      | 0             | 000         | LA             | 36300       |
      |         | CONS_LATAM | 65864523         |        |              |               |               |             |                |             |
    And I wait "/edm/planned_order_v1" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber,sourceSystem"
      | localMaterialNumber | sourceSystem | primaryPlanningCode | materialNumber |
      | 138821              | CONS_LATAM   | 138821              | 234312         |
      | 441423              | CONS_LATAM   | 441423              | 441423         |
      | 189915              | CONS_LATAM   | 189915              |                |
      | 213481              | CONS_LATAM   |                     | 213481         |
    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/edm/plant_v1" by keyFields "localPlant,sourceSystem"
      | localPlant | sourceSystem | localPlanningRelevant |
#      PLO7(if localPlanningRelevant = X)
      | BR12       | CONS_LATAM   | X                     |
#      PLO7(if localPlanningRelevant != X, skip the record)
      | AR01       | CONS_LATAM   |                       |
    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/plan/cns_material_plan_status" by keyFields "localMaterialNumber,localPlant,sourceSystem"
      | localMaterialNumber | localPlant | sourceSystem | noPlanRelevant |
      | 138821              | BR12       | CONS_LATAM   | X              |
      | 441423              | BR12       | CONS_LATAM   | X              |
      | 189915              | BR12       | CONS_LATAM   | X              |
      | 213481              | BR12       | CONS_LATAM   | X              |
#     PLO9(if noPlanRelevant != X, skip the record)
      | 213482              | BR12       | CONS_LATAM   |                |
    And I wait "/plan/cns_material_plan_status" Async Queue complete

    Given I import "/plan/cns_plan_object_filter" by keyFields "sourceObjectAttribute1,sourceObjectAttribute2,sourceObjectAttribute2Value,sourceObjectTechName,sourceSystem"
      | sourceObjectAttribute1 | sourceObjectAttribute2 | sourceObjectAttribute2Value | sourceObjectTechName | sourceSystem |
      | plntCd                 | planOrdrTypeCd         | LA                          | planned_order        | CONS_LATAM   |
    And I wait "/plan/cns_plan_object_filter" Async Queue complete

    When I submit task with xml file "xml/gdm_stock_5_planned_order.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMStock_plannedOrder.tsv"

    Then I check file data for filename "GDMStock_plannedOrder.tsv" by keyFields "stockId"
#    Then I check region data "/omp/gdm_stock" by keyFields "stockId"
      | stockId                          | active | activeOPRERP | activeSOPERP | batchId | blockedQuantity | consignment | certaintyId | erpOrderId | inventoryLinkGroupId | vendorId | locationId      | processId                | processTypeId | productId | qualityQuantity | quantity | receiptDate         | restrictedQuantity | returnsQuantity | startDate           | stockType | transferQuantity | transitDate         | unrestrictedQuantity |
      | 138821/CONS_LATAM_BR12/49748397  | YES    | YES          | NO           |         | 0.0             | NO          | PA          | 49748397   |                      |          | CONS_LATAM_BR12 | 0/138821/CONS_LATAM_BR12 | Production    | 138821    | 0.0             | 36300    | 2018/06/10 00:00:00 | 0.0                | 0.0             | 2018/06/11 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
      | 441423/CONS_LATAM_BR12/117229550 | YES    | YES          | NO           |         | 0.0             | NO          | PA          | 117229550  |                      |          | CONS_LATAM_BR12 | 0/441423/CONS_LATAM_BR12 | Production    | 441423    | 0.0             | 12132    | 2018/06/11 00:00:00 | 0.0                | 0.0             | 2018/06/13 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
      | 189915/CONS_LATAM_BR12/95941803  | YES    | YES          | NO           |         | 0.0             | NO          | PA          | 95941803   |                      |          | CONS_LATAM_BR12 | 0/189915/CONS_LATAM_BR12 | Production    | 189915    | 0.0             | 86544    | 2018/06/12 00:00:00 | 0.0                | 0.0             | 2018/06/18 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
      | 213481/CONS_LATAM_BR12/112368557 | YES    | YES          | NO           |         | 0.0             | NO          | PA          | 112368557  |                      |          | CONS_LATAM_BR12 | 0/213481/CONS_LATAM_BR12 | Production    | 213481    | 0.0             | 5600     | 2018/06/13 00:00:00 | 0.0                | 0.0             | 2018/06/18 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/omp/gdm_stock"

    And I will remove all data with region "/plan/edm_failed_data"
