@pangea_test @AEAZ-4690
Feature: EDMOutboundDeliveryLineV1 AEAZ-4690

  @Scenario1
  Scenario: get sourceSystem from source_system_v1 where source_system_v1-localSourceSystem = "project_one" (T1)

    Given I import "/project_one/lips" by keyFields "mandt,posnr,vbeln"
      | mandt | vbeln      | posnr  | matnr              | charg   | lichn  | werks | meins | lfimg | lgmng  | vgbel      | vgpos  |
      | 120   | 0084526844 | 000010 | 000000000000059764 | 0AF4406 | AF4406 | BR16  | EA    | 4.000 | 96.000 | 0061608006 | 000070 |
      | 120   | 0084526845 | 000010 | 000000000000059767 | 0AE7350 | AE7350 | BR16  | EA    | 5.000 | 15.000 | 0061608007 | 000140 |

    And I wait "/project_one/lips" Async Queue complete

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | sourceSystem |
      | project_one       | CONS_LATAM   |
      | [Btb]             | MDD_Btb      |

    And I wait "/edm/source_system_v1" Async Queue complete

    When I submit task with xml file "xml/edm/EDMOutboundDeliveryLine.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/outbound_delivery_line_v1" by keyFields "srcSysCd,delvDocId,delvLineNbr"
      | srcSysCd   | delvDocId  | delvLineNbr | matlNum            | btchNum | vendBtchNum | shippingPlntCd | baseUnitOfMeasureCd | shippedQty | actlSkuDelvQty | slsOrdrNum | slsOrdrLineNbr |
      | CONS_LATAM | 0084526844 | 000010      | 000000000000059764 | 0AF4406 | AF4406      | BR16           | EA                  | 4.000      | 96.000         | 0061608006 | 000070         |
      | CONS_LATAM | 0084526845 | 000010      | 000000000000059767 | 0AE7350 | AE7350      | BR16           | EA                  | 5.000      | 15.000         | 0061608007 | 000140         |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/project_one/lips" and "/edm/outbound_delivery_line_v1,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/edm/outbound_delivery_line_v1"

    And I will remove all data with region "/plan/edm_failed_data"

