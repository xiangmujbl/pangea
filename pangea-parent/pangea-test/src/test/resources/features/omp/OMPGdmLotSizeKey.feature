@pangea_test
Feature:  OMPGdmLotSizeKey-Curation

  Scenario: Full Load curation

    Given I import "/plan/cns_lot_size_key" by keyFields "sourceSystem,sourceSystem"
      | lotSizeKey | comments | lotSizeKeyDescription | period | quantity |sourceSystem|localLotSizeKey|
      |     WB       |  BtB Standard, used by all      |    Weekly lot size                   |   W     |       1   |     CONS_LATAM       |   AN            |

    And I wait "/plan/cns_lot_size_key" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmLotSizeKey.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "gdmLotSizeKey.tsv"

    And I check file data for filename "gdmLotSizeKey.tsv" by keyFields "lotSizeKey"
      | lotSizeKey |  activeOprerp |  activeSoperp |  comments                 |  description    |  period  |  quantity  |
      |     WB     |        YES    |               | BtB Standard, used by all | Weekly lot size | W        | 1          |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

#    And I compare the number of records between "/plan/cns_lot_size_key" and "/omp/lot_size_key,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/omp/gdm_lot_size_key"

    And I will remove all data with region "/plan/cns_lot_size_key"

    And I will remove the test file on sink application "gdmLotSizeKey.tsv"

