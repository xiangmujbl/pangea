@pangea_test @AEAZ-8897
Feature:  OMPGdmStockASN-Curation

  Scenario: Full Load consumption

    Given I import "/edm/advanced_ship_notification_v1" by keyFields "srcSysCd,delvDocId,localRefDocNum,locRefDocLineNum"
      | srcSysCd   | delvDocId  | receivingPtId | localDeliveryType | localDeliveryCatg | localDeliveryDate | localCreatedDate | localBillOfLading | localExternalId | actGRDt | vendorId   | localShippingPlant | delvLineNbr | matlNum | localBatchNo | localVendorBatchNo | localReceivingPlant | baseUnitOfMeasureCd | actlSkuDelvQty | localRefDocNum | locRefDocLineNum |
      | CONS_LATAM | 180005489  |               |  EL               |  7                | 2016-05-10        | 20160421         | 001041960-1       | 001041960-1     |         | 0000008917 |                    | 10          | 58722   | 2436B04      |                    | CR02                | EA                  | 2592.000       | 3000753622     | 190              |
      | CONS_LATAM | 180005490  |               |  EL               |  7                | 2016-04-21        | 20160421         | 001041960-1       | 001041960-1     |         | 0000009859 |                    | 900001      | 68874   | 0086B02      |                    | MX01                | EA                  | 23456.000      | 3000789748     | 190              |
      | CONS_LATAM | 180005491  |               |  EL               |  7                | 2016-04-21        | 20160421         | 001041960-1       | 001041960-1     |         | 0000089123 |                    | 900002      | 55735   | 0216B03      |                    | GT02                | EA                  | 25921.000      | 3000753612     | 190              |
      | CONS_LATAM | 180021108  |               |  EL               |  7                | 2016-11-28        | 20161128         | 001137503-1       | 001137503-1     |         | 0000008899 |                    | 10          | 57039   | 0345A12      |                    | CO01                | EA                  | 2340.000       | 3000789748     | 220              |
      | CONS_LATAM | 180021109  |               |  XY               |  7                | 2016-11-28        | 20161128         | 001137503-1       | 001137503-1     |         | 0000009816 |                    | 10          | 97360   | 0345A121     |                    | BR01                | EA                  | 1253.000       | 3000789749     | 120              |
      | CONS_LATAM | 18000599   |               |  EL               |  7                | 2016-04-21        | 20160421         | 001041960-1       | 001041960-1     |         | 0000098765 |                    | 10          | 96161   | 2436B078     |                    | BR19                | EA                  | 18746.000      | 3000753873     | 20               |
      | CONS_LATAM | 180005495  |               |  XY               |  7                | 2016-04-21        | 20160421         | 001041960-1       | 001041960-1     |         | 0000009859 |                    | 900001      | 68874   | 0086B02      |                    | BR19                | EA                  | 23456.000      | 3000789742     | 190              |
      | CONS_LATAM | 180005499  |               |  EL               |  7                | 2016-04-21        | 20160421         | 001041960-1       | 001041960-1     |         | 0000006754 |                    | 900001      | 59573   | 0086B        |                    | PE01                | EA                  | 1975.000       | 3000789744     | 10               |
      | CONS_LATAM | 1800054578 |               |  EL               |  7                | 2016-04-21        | 20160421         | 001041960-1       | 001041960-1     |         | 0000008917 |                    | 10          | 91732   | 2436B041     |                    | MX02                | EA                  | 2593.000       | 3000753623     | 10               |
      | CONS_LATAM | 1800054579 |               |  EL               |  7                | 2016-04-21        | 20160421         | 001041960-1       | 001041960-1     |         | 0000008918 |                    | 10          | 441424  | 2436B042     |                    | BR20                | EA                  | 2593.000       | 3000753629     | 30               |
      | CONS_LATAM | 1800054580 |               |  XY               |  7                | 2016-04-21        | 20160421         | 001041960-1       | 001041960-1     |         | 0000008920 |                    | 10          | 441425  | 2436B042     |                    | BR20                | EA                  | 2593.000       | 3000753630     | 30               |
    And I wait "edm/advanced_ship_notification_v1" Async Queue complete

    Given I import "/edm/purchase_order_oa_v1" by keyFields "delvSchedCntNbr,evTypeCd,localSeqNumActAsgn,localSeqNumVendConf,matlMvmtNum,matlMvmtSeqNbr,matlMvmttYr,poLineNbr,poNum,sourceSystem"
      | sourceSystem | poNum      | poLineNbr | evTypeCd | matlMvmttYr | matlMvmtNum | matlMvmtSeqNbr | delvSchedCntNbr | poCatTypeCd | poTypeCd | crtOnDt  | supNum | prchsngOrgNum | prchsngGrpNum | prchsngCoCd | crncyCd | poDt     | suplPlntCd | matlNum | plntCd | slocCd | poLineQty | poUomCd | stkTypeCd | delvCmpltInd | lineItemTypeCd | acctAsgnmtCatCd | prinPrchAgmtNum | prinPrchAgmtLineNbr | localBaseUOM | delvAddrNum | localPDT | prDocId    | prLineNbr | poHistCatCd | localMovementType | localPostingDate | recvEaQty | grLeadTimeDays | custNum | localSeqNumActAsgn | localSeqNumVendConf |
      | CONS_LATAM   | 3000753622 | 190       | 1        | 2017        | 5006382844  | 1              |                 | F           | ZLA      | 20170102 | 6109   | MX01          | M39           | 8302        | MXN     | 20170102 | MX01       | 58722   | MX02   | MX01   | 1968      | EA      | X         | X            | 2              |                 | 5100003715      | 4950                | EA           |             | 0        | 1603571115 | 10        | E           | 101               | 20170103         | 2592.000  | 1              |         |                    |                     |
      | CONS_LATAM   | 3000789748 | 190       | 2        | 2017        | 5115285205  | 1              |                 | F           | NB       | 20170102 | 9106   | MX01          | M39           | 8302        | MXN     | 20170102 | MX02       | 68874   | MX02   | MX01   | 1968      | EA      | X         | X            |                |                 | 5100003715      | 4950                | EA           |             | 0        | 1603571115 | 10        | Q           |                   | 20170116         | 23456.000 | 1              |         |                    |                     |
      | CONS_LATAM   | 3000753612 | 190       | 2        | 2017        | 5115285205  | 1              |                 | F           | UB       | 20170102 | 9111   | MX01          | M39           | 8302        | MXN     | 20170102 | MX02       | 55735   | MX02   | MX01   | 1968      | EA      | X         | X            | 2              |                 | 5100003715      | 4950                | EA           |             | 0        | 1603571115 | 10        | Q           |                   | 20170116         | 23456.000 | 1              |         |                    |                     |
      | CONS_LATAM   | 3000789748 | 220       | 2        | 2017        | 5115285205  | 1              |                 | F           | ZNB      | 20170102 | 91123  | MX01          | M39           | 8302        | MXN     | 20170102 | MX02       | 57039   | MX02   | MX01   | 1968      | EA      | X         | X            | 2              |                 | 5100003715      | 4950                | EA           |             | 0        | 1603571115 | 10        | Q           |                   | 20170116         | 23456.000 | 1              |         |                    |                     |
      | CONS_LATAM   | 3000789749 | 120       | 2        | 2017        | 5115285205  | 1              |                 | F           | ZNB      | 20170102 | 9112   | MX01          | M39           | 8302        | MXN     | 20170102 | MX02       | 97360   | MX02   | MX01   | 1968      | EA      | X         | X            | 2              |                 | 5100003715      | 4950                | EA           |             | 0        | 1603571115 | 10        | Q           |                   | 20170116         | 23456.000 | 1              |         |                    |                     |
      | CONS_LATAM   | 3000753873 | 20        | 2        | 2017        | 5115285205  | 1              |                 | F           | ZNB      | 20170102 | 8976   | MX01          | M39           | 8302        | MXN     | 20170102 | MX02       | 96161   | MX02   | MX01   | 1968      | EA      | X         | X            | 2              |                 | 5100003715      | 4950                | EA           |             | 0        | 1603571115 | 10        | Q           |                   | 20170116         | 45681.000 | 1              |         |                    |                     |
      | CONS_LATAM   | 3000789744 | 10        | 2        | 2017        | 5115285205  | 1              |                 | F           | ZNB      | 20170102 | 7654   | MX01          | M39           | 8302        | MXN     | 20170102 | MX02       | 59573   | MX02   | MX01   | 1968      | EA      | X         | X            | 2              |                 | 5100003715      | 4950                | EA           |             | 0        | 1603571115 | 10        | Q           |                   | 20170116         | 45681.000 | 1              |         |                    |                     |
      | CONS_LATAM   | 3000753623 | 10        | 1        | 2017        | 5006382844  | 1              |                 | F           | ZLA      | 20170102 | 6109   | MX01          | M39           | 8302        | MXN     | 20170102 | MX01       | 91732   | MX02   | MX01   | 1968      | EA      | X         | X            | 2              |                 | 5100003715      | 4950                | EA           |             | 0        | 1603571115 | 10        | E           | 101               | 20170103         | 2592.000  | 1              |         |                    |                     |
      | CONS_LATAM   | 3000753629 | 30        | 1        | 2017        | 5006382844  | 1              |                 | F           | ZLA      | 20170102 | 6109   | MX01          | M39           | 8302        | MXN     | 20170102 | MX01       | 441424  | MX02   | MX01   | 1968      | EA      | X         | X            | 2              |                 | 5100003715      | 4950                | EA           |             | 0        | 1603571115 | 10        | E           | 101               | 20170103         | 2593.000  | 1              |         |                    |                     |
      | CONS_LATAM   | 3000753630 | 30        | 1        | 2017        | 5006382844  | 1              |                 | F           | ABC      | 20170102 | 6109   | MX01          | M39           | 8302        | MXN     | 20170102 | MX01       | 441425  | MX02   | MX01   | 1968      | EA      | X         | X            | 2              |                 | 5100003715      | 4950                | EA           |             | 0        | 1603571115 | 10        | E           | 101               | 20170103         | 2593.000  | 1              |         |                    |                     |
      | CONS_LATAM   | 3000789742 | 30        | 1        | 2017        | 5006382844  | 1              |                 | F           | ABC      | 20170102 | 6109   | MX01          | M39           | 8302        | MXN     | 20170102 | MX01       | 68874   | MX02   | MX01   | 1968      | EA      | X         | X            | 2              |                 | 5100003715      | 4950                | EA           |             | 0        | 1603571115 | 10        | E           | 101               | 20170103         | 2593.000  | 1              |         |                    |                     |
    And I wait "/edm/purchase_order_oa_v1" Async Queue complete|

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
      | country | localCountry | localCurrency | localPlanningRelevant | localPlant | localPlantName                 | localPlantType | plant | plantType               | region            |  | site           | sourceSystem |
      | BR      | BR           | BRL           | X                     | BR19       | J&J BR-Nova Odessa - Com&Distr | DC             | BR63  | DC, Distribution Center | BR, BRAZIL BRAZIL |  | Nova Odessa DC | CONS_LATAM   |
      | BR      | BR           | BRL           | X                     | BR01       | J&J BR-Nova Odessa - Com&Distr | DC             | BR61  | DC, Distribution Center | BR, BRAZIL BRAZIL |  | Nova Odessa DC | CONS_LATAM   |
      | BR      | BR           | BRL           | X                     | BR20       | J&J BR-Nova Odessa - Com&Distr | DC             | BR60  | DC, Distribution Center | BR, BRAZIL BRAZIL |  | Nova Odessa DC | CONS_LATAM   |
      | BR      | BR           | BRL           | X                     | BR21       | J&J BR-Nova Odessa - Com&Distr | DC             | BR62  | DC, Distribution Center | BR, BRAZIL BRAZIL |  | Nova Odessa DC | CONS_LATAM   |
      | BR      | BR           | BRL           | X                     | CR02       | J&J BR-Nova Odessa - Com&Distr | DC             | BR62  | DC, Distribution Center | BR, BRAZIL BRAZIL |  | Nova Odessa DC | CONS_LATAM   |
      | BR      | BR           | BRL           | X                     | CR01       | J&J BR-Nova Odessa - Com&Distr | DC             | BR62  | DC, Distribution Center | BR, BRAZIL BRAZIL |  | Nova Odessa DC | CONS_LATAM   |
      | BR      | BR           | BRL           | X                     | MX01       | J&J BR-Nova Odessa - Com&Distr | DC             | BR62  | DC, Distribution Center | BR, BRAZIL BRAZIL |  | Nova Odessa DC | CONS_LATAM   |
      | BR      | BR           | BRL           | X                     | MX02       | J&J BR-Nova Odessa - Com&Distr | DC             | BR62  | DC, Distribution Center | BR, BRAZIL BRAZIL |  | Nova Odessa DC | CONS_LATAM   |
      | BR      | BR           | BRL           | X                     | GT02       | J&J BR-Nova Odessa - Com&Distr | DC             | BR62  | DC, Distribution Center | BR, BRAZIL BRAZIL |  | Nova Odessa DC | CONS_LATAM   |
      | BR      | BR           | BRL           | X                     | GT01       | J&J BR-Nova Odessa - Com&Distr | DC             | BR62  | DC, Distribution Center | BR, BRAZIL BRAZIL |  | Nova Odessa DC | CONS_LATAM   |
      | BR      | BR           | BRL           | X                     | CO01       | J&J BR-Nova Odessa - Com&Distr | DC             | BR62  | DC, Distribution Center | BR, BRAZIL BRAZIL |  | Nova Odessa DC | CONS_LATAM   |
      | BR      | BR           | BRL           | X                     | PE01       | J&J BR-Nova Odessa - Com&Distr | DC             | BR62  | DC, Distribution Center | BR, BRAZIL BRAZIL |  | Nova Odessa DC | CONS_LATAM   |
    And I wait "/edm/plant_v1" Async Queue complete

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem,sourceSystem"
      | localSourceSystem       | localSourceSystemName                               | modifiedBy | sourceSystem | sourceSystemName                         | sourceSystemType | status | timeStamp | valid |
      | [MDD FASE]              | JDE EDO - FASE - JDE ENTERPRISEONE -PROD (JDE 8.12) |            | MDD_FASE     |                                          |                  |        |           |       |
      | hcs                     | USROTC                                              |            | MDD_USROTC   |                                          |                  |        |           |       |
      | btbjapan                | BTB JAPAN                                           |            | MDD_BTBJAPAN |                                          |                  |        |           |       |
      | [MDD USROTC]            | HCS SAP-ECC-PROD-MP2 (USROTC)                       |            | MDD_USROTC   |                                          |                  |        |           |       |
      | fase                    | JDE GMED 8.12                                       |            | MDD_FASE     |                                          |                  |        |           |       |
      | [EMS]                   | EMS                                                 |            | EMS          | Enterprise Master Data Management System | NGEMS            |        |           |       |
      | [MDD ENDO]              | JDE ETHICON ENDO - JDE ENTERPRISEONE-PROD           |            | MDD_ENDO     |                                          |                  |        |           |       |
      | [MD DePuy Spine JDE XE] | Spine                                               |            | MDDePuy      | MD DePuy Ent                             | JDE              |        |           |       |
      | btb                     | BTB NA                                              |            | MDD_BtB      |                                          |                  |        |           |       |
      | ems                     | EMS                                                 |            | EMS          | Enterprise Master Data Management System | EMS              |        |           |       |
      | mars                    | MARS                                                |            | MDD_MARS     |                                          |                  |        |           |       |
      | Project_One             | Project One                                         |            | CONS_LATAM   | Consumer Latam Ent                       | SAP              |        |           |       |
      | endo                    | JDE ENDO E1                                         |            | MDD_ENDO     |                                          |                  |        |           |       |
      | [Consumer LATAM]        | Consumer Latam                                      |            | CONS_LATAM   | Consumer Latam Ent                       | NGEMS            |        |           |       |
      | [BtB]                   | Back to Basics - MDG                                |            | MDD_BtB      |                                          |                  |        |           |       |
      | [MDD MARS]              | MARS-SAP                                            |            | MDD_MARS     |                                          |                  |        |           |       |
    And I wait "/edm/source_system_v1" Async Queue complete


    Given I import "/plan/cns_material_plan_status" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | active | dpRelevant | localMaterialNumber | localParentCode   | localPlant | materialNumber | noPlanRelevant | parentActive | ppc   | sourceSystem | spRelevant |
      | X      |            | 58722               | 78910109411230000 | CR01       |                |                | X            | 58722 | CONS_LATAM   | X          |
      | X      |            | 68874               | 78910100877220000 | MX02       |                |                | X            | 68874 | CONS_LATAM   | X          |
      | X      |            | 55735               | 78910100877220000 | GT01       |                |                | X            | 55735 | CONS_LATAM   | X          |
      | X      |            | 57039               | 78910100877220000 | CO01       |                |                | X            | 57039 | CONS_LATAM   | X          |
      | X      |            | 97360               | 78910100877220000 | BR01       | 97360          |                | X            | 97360 | CONS_LATAM   | X          |
      | X      |            | 96161               | 78910100877220000 | BR19       | 96161          |                | X            |       | CONS_LATAM   | X          |
      | X      |            | 80990               | 78910100877220000 | BR19       |                |                | X            |       | CONS_LATAM   | X          |
      | X      |            | 59573               | 78910100877220000 | PE01       | 8640           |                | X            | 8641  | CONS_LATAM   | X          |
      | X      |            | 91732               | 78910100877220000 | MX02       |                | X              | X            | 91732 | CONS_LATAM   |            |
      | X      |            | 441424              | 78910100877220000 | BR20       | 441424         | X              |              |       | CONS_LATAM   |            |
      | X      |            | 441425              | 78910100877220000 | BR20       | 441425         | X              |              |       | CONS_LATAM   |            |
      | X      |            | 68874               | 78910109411230000 | BR19       | 68874          |                | X            | 68874 | CONS_LATAM   | X          |
    And I wait "/plan/cns_material_plan_status" Async Queue complete

    Given I import "/plan/cns_plan_object_filter" by keyFields "sourceObjectAttribute1,sourceObjectAttribute1Value,sourceObjectAttribute2,sourceObjectAttribute2Value,sourceObjectTechName,sourceSystem"
      | inclusionExclusion | sourceObjectAttribute1 | sourceObjectAttribute1Value | sourceObjectAttribute2 | sourceObjectAttribute2Value | sourceObjectTechName       | sourceSystem |
      | I                  | localReceivingPlant    | BR19                        | localdeliveryType      | EL                          | advance_ship_notifications | CONS_LATAM   |
      | I                  | localReceivingPlant    | BR01                        | localdeliveryType      | AB                          | advance_ship_notifications | CONS_LATAM   |
      | I                  | localReceivingPlant    | BR20                        | localdeliveryType      | EL                          | advance_ship_notifications | CONS_LATAM   |
      | I                  | localReceivingPlant    | BR21                        | localdeliveryType      | EL                          | advance_ship_notifications | CONS_LATAM   |
      | E                  | localReceivingPlant    | BR21                        | localdeliveryType      | XY                          | advance_ship_notifications | CONS_LATAM   |
      | I                  | localReceivingPlant    | CR02                        | localdeliveryType      | EL                          | advance_ship_notifications | CONS_LATAM   |
      | I                  | localReceivingPlant    | CR01                        | localdeliveryType      | EL                          | advance_ship_notifications | CONS_LATAM   |
      | I                  | localReceivingPlant    | MX01                        | localdeliveryType      | EL                          | advance_ship_notifications | CONS_LATAM   |
      | I                  | localReceivingPlant    | MX02                        | localdeliveryType      | EL                          | advance_ship_notifications | CONS_LATAM   |
      | I                  | localReceivingPlant    | GT02                        | localdeliveryType      | EL                          | advance_ship_notifications | CONS_LATAM   |
      | I                  | localReceivingPlant    | GT01                        | localdeliveryType      | EL                          | advance_ship_notifications | CONS_LATAM   |
      | I                  | localReceivingPlant    | CO01                        | localdeliveryType      | EL                          | advance_ship_notifications | CONS_LATAM   |
      | I                  | localReceivingPlant    | PE01                        | localdeliveryType      | EL                          | advance_ship_notifications | CONS_LATAM   |
    And I wait "/plan/cns_plan_object_filter" Async Queue complete
    
    Given I import "/plan/cns_tlane_control" by keyFields "tlaneName,sequenceNumber,originLocation,destinationLocation"
          | sequenceNumber | tlaneName  | validFrom | validTo    | originLocation  | supSys | supSysVendor | supSysCustomer | destinationLocation | mode | leadTime | triangulationDetail | processTypeId | sourceSystemCriticalParameters | trigSysTransaction | trigSysPlant | trigSysVendor | trigSysCustomer | trigSysShipTo |
          | 1              | CO01toMX02 | 1012018   | 31/12/2998 | CONS_LATAM_CO01 |        |              |                | CONS_LATAM_MX02     | Ship | 35       | YES                 | 1             | CONS_LATAM                     | purchase_order     | MX01         |               |                 |               |
          | 2              | CO01toEC01 | 1012018   | 31/12/2998 | CONS_LATAM_CO01 |        |              |                | CONS_LATAM_EC01     | Ship | 28       | NO                  | 1             | CONS_LATAM                     |                    |              |               |                 |               |
          | 3              | CO01toPE01 | 1012018   | 31/12/2998 | CONS_LATAM_CO01 |        |              |                | CONS_LATAM_PE01     | Ship | 28       | NO                  | 1             | CONS_LATAM                     |                    |              |               |                 |               |
          | 4              | CO01toCO02 | 1012018   | 31/12/2998 | CONS_LATAM_CO01 |        |              |                | CONS_LATAM_CO02     | Ship | 1        | NO                  | 1             | CONS_LATAM                     |                    |              |               |                 |               |
          | 5              | CO01toCL01 | 1012018   | 31/12/2998 | CONS_LATAM_CO01 |        |              |                | CONS_LATAM_CL01     | Ship | 21       | NO                  | 1             | CONS_LATAM                     |                    |              |               |                 |               |
          | 6              | CO01toUY01 | 1012018   | 31/12/2998 | CONS_LATAM_CO01 |        |              |                | CONS_LATAM_UY01     | Ship | 51       | NO                  | 1             | CONS_LATAM                     |                    |              |               |                 |               |
          | 10             | CO01toCR01 | 1012018   | 31/12/2998 | CONS_LATAM_CO01 |        |              |                | CONS_LATAM_CR01     | Ship | 40       | YES                 | 1             | CONS_LATAM                     | purchase_order     | CR02         |               |                 |               |
          | 11             | CO01toGT01 | 1012018   | 31/12/2998 | CONS_LATAM_CO01 |        |              |                | CONS_LATAM_GT01     | Ship | 40       | YES                 | 1             | CONS_LATAM                     | purchase_order     | GT02         |               |                 |               |
          | 20             | BR12toMX02 | 1012018   | 31/12/2998 | CONS_LATAM_BR12 |        |              |                | CONS_LATAM_MX02     | Ship | 37       | YES                 | 1             | CONS_LATAM                     | purchase_order     | MX01         |               |                 |               |
          | 28             | PA03toGT01 | 1012018   | 31/12/2998 | CONS_LATAM_PA03 |        |              |                | CONS_LATAM_GT01     | Ship | 27       | YES                 | 1             | CONS_LATAM                     | purchase_order     | GT02         |               |                 |               |
          | 29             | PA03toCR01 | 1012018   | 31/12/2998 | CONS_LATAM_PA03 |        |              |                | CONS_LATAM_CR01     | Ship | 20       | YES                 | 1             | CONS_LATAM                     | purchase_order     | CR02         |               |                 |               |
    And I wait "/plan/cns_tlane_control" Async Queue complete

    Given I import "/plan/cns_tlane_control_triangulation" by keyFields "sequenceNumber,stepNumber,tlaneName,originLocation,destinationLocation"
      | sequenceNumber | stepNumber | tlaneName  | originLocation  | destinationLocation |
      | 1              | 1          | CO01toMX02 | CONS_LATAM_CO01 | CONS_LATAM_MX01     |
      | 1              | 2          | CO01toMX02 | CONS_LATAM_MX01 | CONS_LATAM_MX02     |
      | 10             | 1          | CO01toCR01 | CONS_LATAM_CO01 | CONS_LATAM_CR02     |
      | 10             | 2          | CO01toCR01 | CONS_LATAM_CR02 | CONS_LATAM_CR01     |
      | 11             | 1          | CO01toGT01 | CONS_LATAM_CO01 | CONS_LATAM_GT02     |
      | 11             | 2          | CO01toGT01 | CONS_LATAM_GT02 | CONS_LATAM_GT01     |
      | 20             | 1          | BR12toMX02 | CONS_LATAM_BR12 | CONS_LATAM_MX01     |
      | 20             | 2          | BR12toMX02 | CONS_LATAM_MX01 | CONS_LATAM_MX02     |
      | 28             | 1          | PA03toGT01 | CONS_LATAM_PA03 | CONS_LATAM_GT02     |
      | 28             | 2          | PA03toGT01 | CONS_LATAM_GT02 | CONS_LATAM_GT01     |
      | 29             | 1          | PA03toCR01 | CONS_LATAM_PA03 | CONS_LATAM_CR02     |
      | 29             | 2          | PA03toCR01 | CONS_LATAM_CR02 | CONS_LATAM_CR01     |
    And I wait "/plan/cns_tlane_control_triangulation" Async Queue complete


    When I submit task with xml file "xml/omp/OMPGdmStockASN.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMStock.tsv"

    And I check file data for filename "GDMStock.tsv" by keyFields "stockId"
      | stockId                                | active | activeOPRERP | activeSOPERP | batchId                         | blockedQuantity | consignment | certaintyId | erpOrderId | inventoryLinkGroupId | vendorId | locationId      | processId                             | processTypeId     | productId | qualityQuantity | quantity     | receiptDate         | restrictedQuantity | returnsQuantity | startDate           | stockType | transferQuantity | transitDate         | unrestrictedQuantity |
      | 58722/CONS_LATAM_CR01/180005489/10     | YES    | YES          | NO           | 58722/CONS_LATAM_CR01/2436B04   | 0.0             | YES         | LA          | 180005489  |                      | 8917     | CONS_LATAM_CR01 | TR/58722/CONS_LATAM_CR01/MX01/6109    | ExternalTransport | 58722     | 0.0             | 2592.000     | 2016/05/10 00:00:00 | 0.0                | 0.0             | 2016/05/11 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
      | 68874/CONS_LATAM_MX02/180005490/900001 | YES    | YES          | NO           | 68874/CONS_LATAM_MX02/0086B02   | 0.0             | NO          | LA          | 180005490  |                      | 9859     | CONS_LATAM_MX02 | SU/68874/CONS_LATAM_MX02/9106/Default | VendorTransport   | 68874     | 0.0             | 23456.000    | 2016/04/21 00:00:00 | 0.0                | 0.0             | 2016/04/22 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
      | 55735/CONS_LATAM_GT01/180005491/900002 | YES    | YES          | NO           | 55735/CONS_LATAM_GT01/0216B03   | 0.0             | YES         | LA          | 180005491  |                      | 89123    | CONS_LATAM_GT01 | TR/55735/CONS_LATAM_GT01/MX02/9111    | InternalTransport | 55735     | 0.0             | 25921.000    | 2016/04/21 00:00:00 | 0.0                | 0.0             | 2016/04/22 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
      | 57039/CONS_LATAM_CO01/180021108/10     | YES    | YES          | NO           | 57039/CONS_LATAM_CO01/0345A12   | 0.0             | YES         | LA          | 180021108  |                      | 8899     | CONS_LATAM_CO01 | TR/57039/CONS_LATAM_CO01/MX02/91123   | ExternalTransport | 57039     | 0.0             | 2340.000     | 2016/11/28 00:00:00 | 0.0                | 0.0             | 2016/11/29 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
      | 96161/CONS_LATAM_BR19/18000599/10      | YES    | YES          | NO           | 96161/CONS_LATAM_BR19/2436B078  | 0.0             | YES         | LA          | 18000599   |                      | 98765    | CONS_LATAM_BR19 | TR/96161/CONS_LATAM_BR19/MX02/8976    | ExternalTransport | 96161     | 0.0             | 18746.000    | 2016/04/21 00:00:00 | 0.0                | 0.0             | 2016/04/22 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
      | 91732/CONS_LATAM_MX02/1800054578/10    | YES    | YES          | NO           | 91732/CONS_LATAM_MX02/2436B041  | 0.0             | YES         | LA          | 1800054578 |                      | 8917     | CONS_LATAM_MX02 | TR/91732/CONS_LATAM_MX02/MX01/6109    | ExternalTransport | 91732     | 0.0             | 2593.000     | 2016/04/21 00:00:00 | 0.0                | 0.0             | 2016/04/22 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
      | 441424/CONS_LATAM_BR20/1800054579/10   | YES    | YES          | NO           | 441424/CONS_LATAM_BR20/2436B042 | 0.0             | YES         | LA          | 1800054579 |                      | 8918     | CONS_LATAM_BR20 | TR/441424/CONS_LATAM_BR20/MX01/6109   | ExternalTransport | 441424    | 0.0             | 2593.000     | 2016/04/21 00:00:00 | 0.0                | 0.0             | 2016/04/22 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
      | 8641/CONS_LATAM_PE01/180005499/900001  | YES    | YES          | NO           | 8641/CONS_LATAM_PE01/0086B      | 0.0             | YES         | LA          | 180005499  |                      | 6754     | CONS_LATAM_PE01 | TR/8641/CONS_LATAM_PE01/MX02/7654     | ExternalTransport | 8641      | 0.0             | 1975.000     | 2016/04/21 00:00:00 | 0.0                | 0.0             | 2016/04/22 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |


    Then I check region data "/plan/edm_failed_data" by keyFields "errorCode,functionalArea,interfaceID,key1,key2,key3,key4,key5,sourceSystem"
      | errorCode | errorValue                                                 | functionalArea | interfaceID    | key1  | key2       | key3 | key4 | key5 | sourceSystem |
#      | ASN9      | Both Primary Planning Code and Material Number are missing | DP             | OMPGdmStockASN | 97360 | CONS_LATAM |      |      |      | CONS_LATAM   |

    And I will remove all data with region "/plan/advanced_ship_notification_v1"

    And I will remove all data with region "/edm/purchase_order_oa_v1"

    And I will remove all data with region "/edm/material_global_v1"

    And I will remove all data with region "/edm/plant_v1"

    And I will remove all data with region "/edm/source_system_v1"

    And I will remove all data with region "/plan/cns_material_plan_status"

    And I will remove all data with region "/plan/cns_plan_object_filter"

    And I will remove all data with region "/plan/cns_tlane_control"

    And I will remove all data with region "/plan/cns_tlane_control_triangulation"

    And I will remove all data with region "/omp/gdm_stock_asn"

    And I will remove all data with region "/plan/edm_failed_data"

    And I will remove the test file on sink application "GDMStock.tsv"