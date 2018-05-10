@pangea_test @AEAZ-3174
Feature: EDMSAdvancedShippingNotification AEAZ-3174

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
      | vbeln     | erdat    |  bolnr       | lfdat    | lfart | vstel | lifex       | wadatist  | lifnr | werks |
      | 180005489 | 20161128 | 001041960-1  | 20160421 | EL    |       | 001041960-1 | 20160422  | 8917  |       |
      | 180005489 | 20161128 | 001041960-1  | 20160421 | EL    |       | 001041960-1 | 20160422  | 8917  |       |
      | 180005489 | 20161128 | 001041960-1  | 20160421 | EL    |       | 001041960-1 | 20160422  | 8917  |       |
      | 180021108 | 20160421 | 001137503-1  | 20161128 | EL    | BR07  | 001137503-1 | 20161205  | 8917  |       |
      | 180021108 | 20160421 | 001137503-1  | 20161128 | EL    | BR07  | 001137503-1 | 20161205  | 8917  |       |
      | 180021108 | 20160421 | 001137503-1  | 20161128 | EL    | BR07  | 001137503-1 | 20161205  | 8917  |       |

    And I wait "/project_one/likp" Async Queue complete

    And I import "/project_one/lips" by keyFields "vbeln,posnr"
      | vbeln     | posnr  | matnr              | werks | charg   | lichn | meins | lgmng    | vgbel      | vgpos |
      | 180005489 |        | 000000000000068874 | BR19  |         |       |  EA   | 0.000    | 3000753622 | 190   |
      | 180005489 | 000001 | 000000000000068874 | BR19  | 0086B02 |       |  EA   | 2592.000 | 3000753622 | 190   |
      | 180005489 | 900002 | 000000000000068874 | BR19  | 0216B03 |       |  EA   | 2592.000 | 3000753622 | 190   |
      | 180021108 |        | 000000000000087046 | BR07  |         |       |  EA   | 0.000    | 3000789748 | 220   |
      | 180021108 | 900001 | 000000000000087046 | BR07  | 2286B02 |       |  EA   | 2124.000 | 3000789748 | 220   |
      | 180021108 | 900002 | 000000000000087046 | BR07  | 2436B04 |       |  EA   | 2484.000 | 3000789748 | 220   |

    And I wait "/project_one/lips" Async Queue complete

    When I submit task with xml file "xml/edm/EDMAdvancedShipNotification.xml" and execute file "jar/pangea-view.jar"

    And I check file data for filename "EDMAdvancedShipNotification.tsv" by keyFields "productLocationDetailId"

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I will remove all data with region "/project_one/likp"

    And I will remove all data with region "/project_one/lips"

    And I will remove all data with region "/plan/edm_failed_data"

    And I will remove the test file on sink application "EDMAdvancedShipNotification.tsv"
