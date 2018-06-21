@pangea_test @AEAZ-5866
Feature:  OMPGdmStockASN-Curation

  Scenario: Full Load consumption

    Given I import "/edm/advanced_ship_notification_v1" by keyFields "srcSysCd,delvDocID,localRefDocNum,locRefDocLineNum"
      | srcSysCd   | delvDocID  | receivingPtID | localdeliveryType | localdeliveryCatg | localdeliveryDate | localcreatedDate | localbillOfLading | localExternalId | actGRDt | vendorID | localShippingPlant | delvLineNbr | matlNum | localbatchNo | localvendorBatchNo | localReceivingPlant | baseUnitOfMeasureCd | actlSkuDelvQty | localRefDocNum | locRefDocLineNum |
      | CONS_LATAM | 180005489  |               | EL                | 7                 | 20160421          | 20160421         | 001041960-1       | 001041960-1     |         | 8917     |                    | 10          | 58722   | 2436B04      |                    | BR19                | EA                  | 2592.000       | 3000753622     | 190              |
      | CONS_LATAM | 180005490  |               | EL                | 7                 | 20160421          | 20160421         | 001041960-1       | 001041960-1     |         | 9859     |                    | 900001      | 68874   | 0086B02      |                    | BR19                | EA                  | 23456.000      | 3000789748     | 190              |
      | CONS_LATAM | 180005491  |               | EL                | 7                 | 20160421          | 20160421         | 001041960-1       | 001041960-1     |         | 89123    |                    | 900002      | 55735   | 0216B03      |                    | BR19                | EA                  | 25921.000      | 3000753612     | 190              |
      | CONS_LATAM | 180021108  |               | EL                | 7                 | 20161128          | 20161128         | 001137503-1       | 001137503-1     |         | 8899     |                    | 10          | 57039   | 0345A12      |                    | BR19                | EA                  | 2340.000       | 3000789748     | 220              |
      | CONS_LATAM | 180021109  |               | XY                | 7                 | 20161128          | 20161128         | 001137503-1       | 001137503-1     |         | 9816     |                    | 10          | 97360   | 0345A121     |                    | BR01                | EA                  | 1253.000       | 3000789749     | 120              |
      | CONS_LATAM | 18000599   |               | EL                | 7                 | 20160421          | 20160421         | 001041960-1       | 001041960-1     |         | 98765    |                    | 10          | 96161   | 2436B078     |                    | BR19                | EA                  | 18746.000      | 3000753873     | 20               |
      | CONS_LATAM | 180005495  |               | XY                | 7                 | 20160421          | 20160421         | 001041960-1       | 001041960-1     |         | 9859     |                    | 900001      | 68874   | 0086B02      |                    | BR19                | EA                  | 23456.000      | 3000789742     | 190              |
      | CONS_LATAM | 180005499  |               | EL                | 7                 | 20160421          | 20160421         | 001041960-1       | 001041960-1     |         | 6754     |                    | 900001      | 59573   | 0086B        |                    | BR19                | EA                  | 1975.000       | 3000789744     | 10               |
      | CONS_LATAM | 1800054578 |               | EL                | 7                 | 20160421          | 20160421         | 001041960-1       | 001041960-1     |         | 8917     |                    | 10          | 91732   | 2436B041     |                    | BR21                | EA                  | 2593.000       | 3000753623     | 10               |
      | CONS_LATAM | 1800054579 |               | EL                | 7                 | 20160421          | 20160421         | 001041960-1       | 001041960-1     |         | 8918     |                    | 10          | 441424  | 2436B042     |                    | BR20                | EA                  | 2593.000       | 3000753629     | 30               |
      | CONS_LATAM | 1800054580 |               | XY                | 7                 | 20160421          | 20160421         | 001041960-1       | 001041960-1     |         | 8920     |                    | 10          | 441425  | 2436B042     |                    | BR20                | EA                  | 2593.000       | 3000753630     | 30               |
    And I wait "edm/advanced_ship_notification_v1" Async Queue complete

    Given I import "/edm/purchase_order_oa_v1" by keyFields "delvSchedCntNbr,evTypeCd,localSeqNumActAsgn,localSeqNumVendConf,matlMvmtNum,matlMvmtSeqNbr,matlMvmttYr,poLineNbr,poNum,sourceSystem"
      | sourceSystem | poNum      | poLineNbr | evTypeCd | matlMvmttYr | matlMvmtNum | matlMvmtSeqNbr | delvSchedCntNbr | poCatTypeCd | poTypeCd | crtOnDt  | supNum | prchsngOrgNum | prchsngGrpNum | prchsngCoCd | crncyCd | poDt     | suplPlntCd | matlNum | plntCd | slocCd | poLineQty | lineItemTypeCd | localPostingDate | recvEaQty | localdelvDt | grLeadTimeDays | custNum | localSeqNumActAsgn | localSeqNumVendConf |
      | CONS_LATAM   | 3000753622 | 190       | 1        | 2017        | 5006382844  | 1              |                 | F           | ZLA      | 20170102 | 6109   | MX01          | M39           | 8302        | MXN     | 20170102 | MX01       | 58722   | MX02   | MX01   | 1968      | 2              | 20170103         | 2592.000  |             |                |         |                    |                     |
      | CONS_LATAM   | 3000789748 | 190       | 2        | 2017        | 5115285205  | 1              |                 | F           | NB       | 20170102 | 9106   | MX01          | M39           | 8302        | MXN     | 20170102 | MX02       | 68874   | MX02   | MX01   | 1968      |                | 20170116         | 23456.000 |             |                |         |                    |                     |
      | CONS_LATAM   | 3000753612 | 190       | 2        | 2017        | 5115285205  | 1              |                 | F           | UB       | 20170102 | 9111   | MX01          | M39           | 8302        | MXN     | 20170102 | MX02       | 55735   | MX02   | MX01   | 1968      | 2              | 20170116         | 23456.000 |             |                |         |                    |                     |
      | CONS_LATAM   | 3000789748 | 220       | 2        | 2017        | 5115285205  | 1              |                 | F           | ZNB      | 20170102 | 91123  | MX01          | M39           | 8302        | MXN     | 20170102 | MX02       | 57039   | MX02   | MX01   | 1968      | 2              | 20170116         | 23456.000 |             |                |         |                    |                     |
      | CONS_LATAM   | 3000789749 | 120       | 2        | 2017        | 5115285205  | 1              |                 | F           | ZNB      | 20170102 | 9112   | MX01          | M39           | 8302        | MXN     | 20170102 | MX02       | 97360   | MX02   | MX01   | 1968      | 2              | 20170116         | 23456.000 |             |                |         |                    |                     |
      | CONS_LATAM   | 3000753873 | 20        | 2        | 2017        | 5115285205  | 1              |                 | F           | ZNB      | 20170102 | 8976   | MX01          | M39           | 8302        | MXN     | 20170102 | MX02       | 96161   | MX02   | MX01   | 1968      | 2              | 20170116         | 45681.000 |             |                |         |                    |                     |
      | CONS_LATAM   | 3000789744 | 10        | 2        | 2017        | 5115285205  | 1              |                 | F           | ZNB      | 20170102 | 7654   | MX01          | M39           | 8302        | MXN     | 20170102 | MX02       | 59573   | MX02   | MX01   | 1968      | 2              | 20170116         | 45681.000 |             |                |         |                    |                     |
      | CONS_LATAM   | 3000753623 | 10        | 1        | 2017        | 5006382844  | 1              |                 | F           | ZLA      | 20170102 | 6109   | MX01          | M39           | 8302        | MXN     | 20170102 | MX01       | 91732   | MX02   | MX01   | 1968      | 2              | 20170103         | 2592.000  |             |                |         |                    |                     |
      | CONS_LATAM   | 3000753629 | 30        | 1        | 2017        | 5006382844  | 1              |                 | F           | ZLA      | 20170102 | 6109   | MX01          | M39           | 8302        | MXN     | 20170102 | MX01       | 441424  | MX02   | MX01   | 1968      | 2              | 20170103         | 2593.000  |             |                |         |                    |                     |
      | CONS_LATAM   | 3000753630 | 30        | 1        | 2017        | 5006382844  | 1              |                 | F           | ABC      | 20170102 | 6109   | MX01          | M39           | 8302        | MXN     | 20170102 | MX01       | 441425  | MX02   | MX01   | 1968      | 2              | 20170103         | 2593.000  |             |                |         |                    |                     |
      | CONS_LATAM   | 3000789742 | 30        | 1        | 2017        | 5006382844  | 1              |                 | F           | ABC      | 20170102 | 6109   | MX01          | M39           | 8302        | MXN     | 20170102 | MX01       | 68874   | MX02   | MX01   | 1968      | 2              | 20170103         | 2593.000  |             |                |         |                    |                     |
    And I wait "/edm/purchase_order_oa_v1" Async Queue complete


    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber,sourceSystem"
      | localMaterialGroup | localMaterialNumber | localMaterialType | materialNumber | materialStatus | materialType | minRemShelfLife | parentCode | primaryPlanningCode | sourceSystem | totalShelfLife |
      | 80141605           | 58722               | PROM              |                | 8              |              | 0               |            | 58722               | CONS_LATAM   | 0              |
      | 53130000           | 68874               | SAPR              |                | 8              |              | 365             |            | 68874               | CONS_LATAM   | 1825           |
      | 53130000           | 55735               | SAPR              | 55735          | 8              |              | 120             |            | 55735               | CONS_LATAM   | 11             |
      | 53130000           | 57039               | SAPR              | 57039          | 8              |              | 134             |            | 57039               | CONS_LATAM   | 15             |
      | 53130000           | 97360               | SAPR              |                | 9              |              | 11              |            |                     | CONS_LATAM   | 1              |
      | 53130000           | 96161               | SAPR              | 96161          | 8              |              | 8               |            |                     | CONS_LATAM   | 2              |
      | 53130000           | 80990               | SAPR              | 80990          | 9              |              | 9               |            | 80990               | CONS_LATAM   | 6              |
      | 53130000           | 59573               | SAPR              |                | 9              |              | 9               |            | 8641                | CONS_LATAM   | 9              |
      | 53130000           | 91732               | SAPR              | 91732          | 9              |              | 9               |            | 91732               | CONS_LATAM   | 9              |
      | 53130000           | 441424              | SAPR              | 441424         | 9              |              | 9               |            | 441424              | CONS_LATAM   | 9              |
      | 5313000            | 441425              | SAPR              | 441425         | 9              |              | 9               |            | 441425              | CONS_LATAM   | 9              |
    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/edm/plant_v1" by keyFields "localPlant,sourceSystem"
      | country | localCountry | localCurrency | localPlanningRelevant | localPlant | localPlantName                 | localPlantType | plant | plantType               | region            | site           | sourceSystem |
      | BR      | BR           | BRL           | X                     | BR19       | J&J BR-Nova Odessa - Com&Distr | DC             | BR63  | DC, Distribution Center | BR, BRAZIL BRAZIL | Nova Odessa DC | CONS_LATAM   |
      | BR      | BR           | BRL           | X                     | BR01       | J&J BR-Nova Odessa - Com&Distr | DC             | BR61  | DC, Distribution Center | BR, BRAZIL BRAZIL | Nova Odessa DC | CONS_LATAM   |
      | BR      | BR           | BRL           | X                     | BR20       | J&J BR-Nova Odessa - Com&Distr | DC             | BR60  | DC, Distribution Center | BR, BRAZIL BRAZIL | Nova Odessa DC | CONS_LATAM   |
      | BR      | BR           | BRL           | X                     | BR21       | J&J BR-Nova Odessa - Com&Distr | DC             | BR62  | DC, Distribution Center | BR, BRAZIL BRAZIL | Nova Odessa DC | CONS_LATAM   |
    And I wait "/edm/plant_v1" Async Queue complete

    Given I import "/plan/cns_material_plan_status" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | active | dpRelevant | localMaterialNumber | localParentCode | localPlant | materialNumber | noPlanRelevant | parentActive | ppc   | sourceSystem | spRelevant |
      | X      |            | 58722               | 7.89101E+16     | BR19       |                |                | X            | 58722 | CONS_LATAM   | X          |
      | X      |            | 68874               | 7.89101E+16     | BR19       |                |                | X            | 68874 | CONS_LATAM   | X          |
      | X      |            | 55735               | 7.89101E+16     | BR19       |                |                | X            | 55735 | CONS_LATAM   | X          |
      | X      |            | 57039               | 7.89101E+16     | BR19       |                |                | X            | 57039 | CONS_LATAM   | X          |
      | X      |            | 97360               | 7.89101E+16     | BR01       | 97360          |                | X            | 97360 | CONS_LATAM   | X          |
      | X      |            | 96161               | 7.89101E+16     | BR19       | 96161          |                | X            |       | CONS_LATAM   | X          |
      | X      |            | 80990               | 7.89101E+16     | BR19       |                |                | X            |       | CONS_LATAM   | X          |
      | X      |            | 59573               | 7.89101E+16     | BR19       | 8640           |                | X            | 8641  | CONS_LATAM   | X          |
      | X      |            | 91732               | 7.89101E+16     | BR21       |                | X              | X            | 91732 | CONS_LATAM   |            |
      | X      |            | 441424              | 7.89101E+16     | BR20       | 441424         | X              |              |       | CONS_LATAM   |            |
      | X      |            | 441425              | 7.89101E+16     | BR20       | 441425         | X              |              |       | CONS_LATAM   |            |
    And I wait "/plan/cns_material_plan_status" Async Queue complete

    Given I import "/plan/cns_plan_object_filter" by keyFields "sourceObjectAttribute1,sourceObjectAttribute1Value,sourceObjectAttribute2,sourceObjectAttribute2Value,sourceObjectTechName,sourceSystem"
      | comments | inclusionExclusion | sourceObjectAttribute1 | sourceObjectAttribute1Value | sourceObjectAttribute2 | sourceObjectAttribute2Value | sourceObjectTechName       | sourceSystem |
      |          | I                  | localReceivingPlant    | BR19                        | localdeliveryType      | EL                          | advance_ship_notifications | CONS_LATAM   |
      |          | I                  | localReceivingPlant    | BR01                        | localdeliveryType      | AB                          | advance_ship_notifications | CONS_LATAM   |
      |          | I                  | localReceivingPlant    | BR20                        | localdeliveryType      | EL                          | advance_ship_notifications | CONS_LATAM   |
      |          | I                  | localReceivingPlant    | BR21                        | localdeliveryType      | EL                          | advance_ship_notifications | CONS_LATAM   |
      |          | E                  | localReceivingPlant    | BR21                        | localdeliveryType      | XY                          | advance_ship_notifications | CONS_LATAM   |
    And I wait "/plan/cns_plan_object_filter" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmStockASN.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMStock.tsv"

    And I check file data for filename "GDMStock.tsv" by keyFields "stockId"
      | stockId                                | active | activeOPRERP | activeSOPERP | batchId                         | blockedQuantity | consignment | certaintyId | erpOrderId | inventoryLinkGroupId | vendorId | locationId      | processId                             | processTypeId     | productId | qualityQuantity | quantity | receiptDate         | restrictedQuantity | returnsQuantity | startDate           | stockType | transferQuantity | transitDate         | unrestrictedQuantity |
      | 58722/CONS_LATAM_BR19/180005489/10     | YES    | YES          | NO           | 58722/CONS_LATAM_BR19/2436B04   | 0.0             | YES         | LA          | 180005489  |                      | 8917     | CONS_LATAM_BR19 | TR/58722/CONS_LATAM_BR19/MX01/6109    | ExternalTransport | 58722     | 0.0             | 2592.00  | 2016/04/21 00:00:00 | 0.0                | 0.0             | 2016/04/21 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
      | 68874/CONS_LATAM_BR19/180005490/900001 | YES    | YES          | NO           | 68874/CONS_LATAM_BR19/0086B02   | 0.0             |             | LA          | 180005490  |                      | 9859     | CONS_LATAM_BR19 | SU/68874/CONS_LATAM_BR19/9106/Default | VendorTransport   | 68874     | 0.0             | 23456.00 | 2016/04/21 00:00:00 | 0.0                | 0.0             | 2016/04/21 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
      | 55735/CONS_LATAM_BR19/180005491/900002 | YES    | YES          | NO           | 55735/CONS_LATAM_BR19/0216B03   | 0.0             | YES         | LA          | 180005491  |                      | 89123    | CONS_LATAM_BR19 | TR/55735/CONS_LATAM_BR19/MX02/9111    | InternalTransport | 55735     | 0.0             | 25921.00 | 2016/04/21 00:00:00 | 0.0                | 0.0             | 2016/04/21 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
      | 57039/CONS_LATAM_BR19/180021108/10     | YES    | YES          | NO           | 57039/CONS_LATAM_BR19/0345A12   | 0.0             | YES         | LA          | 180021108  |                      | 8899     | CONS_LATAM_BR19 | TR/57039/CONS_LATAM_BR19/MX02/91123   | ExternalTransport | 57039     | 0.0             | 2340.00  | 2016/11/28 00:00:00 | 0.0                | 0.0             | 2016/11/28 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
      | 96161/CONS_LATAM_BR19/18000599/10      | YES    | YES          | NO           | 96161/CONS_LATAM_BR19/2436B078  | 0.0             | YES         | LA          | 18000599   |                      | 98765    | CONS_LATAM_BR19 | TR/96161/CONS_LATAM_BR19/MX02/8976    | ExternalTransport | 96161     | 0.0             | 18746.00 | 2016/04/21 00:00:00 | 0.0                | 0.0             | 2016/04/21 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
      | 91732/CONS_LATAM_BR21/1800054578/10    | YES    | YES          | NO           | 91732/CONS_LATAM_BR21/2436B041  | 0.0             | YES         | LA          | 1800054578 |                      | 8917     | CONS_LATAM_BR21 | TR/91732/CONS_LATAM_BR21/MX01/6109    | ExternalTransport | 91732     | 0.0             | 2593.00  | 2016/04/21 00:00:00 | 0.0                | 0.0             | 2016/04/21 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
      | 441424/CONS_LATAM_BR20/1800054579/10   | YES    | YES          | NO           | 441424/CONS_LATAM_BR20/2436B042 | 0.0             | YES         | LA          | 1800054579 |                      | 8918     | CONS_LATAM_BR20 | TR/441424/CONS_LATAM_BR20/MX01/6109   | ExternalTransport | 441424    | 0.0             | 2593.00  | 2016/04/21 00:00:00 | 0.0                | 0.0             | 2016/04/21 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
      | 8641/CONS_LATAM_BR19/180005499/900001  | YES    | YES          | NO           | 8641/CONS_LATAM_BR19/0086B      | 0.0             | YES         | LA          | 180005499  |                      | 6754     | CONS_LATAM_BR19 | TR/8641/CONS_LATAM_BR19/MX02/7654     | ExternalTransport | 8641      | 0.0             | 1975.00  | 2016/04/21 00:00:00 | 0.0                | 0.0             | 2016/04/21 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |


    Then I check region data "/dev/plan/edm_failed_data" by keyFields "errorCode,functionalArea,interfaceID,key1,key2,key3,key4,key5,sourceSystem"
      | errorCode | errorValue                                                 | functionalArea | interfaceID    | key1  | key2       | key3 | key4 | key5 | sourceSystem |
      | ASN9      | Both Primary Planning Code and Material Number are missing | DP             | OMPGdmStockASN | 97360 | CONS_LATAM |      |      |      | CONS_LATAM   |

    And I will remove all data with region "/plan/advanced_ship_notification_v1"

    And I will remove all data with region "/edm/purchase_order_oa_v1"

    And I will remove all data with region "/edm/material_global_v1"

    And I will remove all data with region "/edm/plant_v1"

    And I will remove all data with region "/plan/cns_material_plan_status"

    And I will remove all data with region "/plan/cns_plan_object_filter"

    And I will remove all data with region "/omp/gdm_stock_asn"

    And I will remove all data with region "/dev/plan/edm_failed_data"

    And I will remove the test file on sink application "GDMStock.tsv"