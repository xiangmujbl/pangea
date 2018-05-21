@pangea_test @AEAZ-3174
Feature: EDMAdvancedShippingNotification AEAZ-3174

  Scenario: Full Load curation
    #1.test get localSourceSystem from source_system_v1 and overwrite the value in this field (rule T1)
    #2.test get attributes from VBAP and overwrite the value in this field (rule J1)
    #3.test get attributes from VBEP and overwrite the value in this field (rule J2)
    #4.test get kunnr from VBPA and overwrite the value in this field (rule J3)
    #5.test get attributes from VBKD and overwrite the value in this field (rule J4)

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | localSourceSystemName | sourceSystem | sourceSystemName   |
      | Project_One       | Project One           | CONS_LATAM   | Consumer Latam Ent |
      | Project Two       | Project Two           | CONS_LATAM   | Consumer Latam Ent |

    And I wait "/edm/source_system_v1" Async Queue complete

    And I import "/project_one/likp" by keyFields "vbeln"
      | vbeln     | erdat    |  bolnr       | lfdat    | lfart | vstel | lifex       | wadatist  | lifnr | werks | vbtyp |
      | 180005489 | 20161128 | 001041960-1  | 20160421 | EL    |       | 001041960-1 | 20160422  | 8917  |       | 7     |
      | 180005489 | 20161128 | 001041960-1  | 20160421 | EL    |       | 001041960-1 | 20160422  | 8917  |       | 7     |
      | 180005489 | 20161128 | 001041960-1  | 20160421 | EL    |       | 001041960-1 | 20160422  | 8917  |       | 7     |
      | 180021108 | 20160420 | 001137503-1  | 20161129 | EL    | BR07  | 001137503-1 | 20161205  | 8917  |       | 7     |
      | 180021108 | 20160420 | 001137503-1  | 20161129 | EL    | BR07  | 001137503-1 | 20161205  | 8917  |       | 7     |
      | 180021108 | 20160420 | 001137503-1  | 20161129 | EL    | BR07  | 001137503-1 | 20161205  | 8917  |       | 7     |

    And I wait "/project_one/likp" Async Queue complete

    And I import "/project_one/lips" by keyFields "vbeln"
      | vbeln     | posnr  | matnr              | werks | charg   | lichn | meins | lgmng    | vgbel      | vgpos |
      | 180005489 |        | 000000000000068874 | BR19  |         |       |  EA   | 0.000    | 3000753622 | 190   |
      | 180005489 | 000001 | 000000000000068874 | BR19  | 0086B02 |       |  EA   | 2592.000 | 3000753622 | 190   |
      | 180005489 | 900002 | 000000000000068874 | BR19  | 0216B03 |       |  EA   | 2592.000 | 3000753622 | 190   |
      | 180021108 |        | 000000000000087046 | BR07  |         |       |  EA   | 0.000    | 3000789748 | 220   |
      | 180021108 | 900001 | 000000000000087046 | BR07  | 2286B02 |       |  EA   | 2124.000 | 3000789748 | 220   |
      | 180021108 | 900002 | 000000000000087046 | BR07  | 2436B04 |       |  EA   | 2484.000 | 3000789748 | 220   |

    And I wait "/project_one/lips" Async Queue complete

    When I submit task with xml file "xml/edm/EDMAdvancedShipNotification.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/advanced_ship_notification_v1" by keyFields "delvDocID,srcSysCd"
      | localdeliveryType | localdeliveryCatg | localExternalId | delvLineNbr | actlSkuDelvQty | localcreatedDate | locRefDocLineNum | delvDocID    | localShippingPlant | vendorID     | localvendorBatchNo | srcSysCd    |  baseUnitOfMeasureCd | receivingPtID | localReceivingPlant |  localbillOfLading |   localbatchNo     | matlNum   			| localdeliveryDate |  actGRDt   | localRefDocNum |
      | 	EL	          |			7	      |	001041960-1     | 900002      |	2592.000       |  2016-11-28      | 190              |	180005489	|			         |	8917		|   	             | CONS_LATAM  |        EA  		  |		 	      | BR19     			|  001041960-1       |   0216B03     	  | 000000000000068874  |  2016-04-21       | 2016-04-22 | 3000753622 	  |
      |     EL            |         7         | 001137503-1     | 900002      | 2484.000       |  2016-04-20      | 220              |  180021108   |                    |  8917        |                    | CONS_LATAM  |        EA            |   BR07        | BR07                |  001137503-1       |   2436B04          | 000000000000087046  |  2016-11-29       | 2016-12-05 | 3000789748     |

    Then I check region data "/dev/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I will remove all data with region "/edm/source_system_v1"

    And I will remove all data with region "/project_one/likp"

    And I will remove all data with region "/project_one/lips"

    And I will remove all data with region "/plan/edm_failed_data"

