@pangea_test
Feature:  OMPGdmLotSizeKey-Curation

  Scenario: Full Load curation

    Given I import "/plan/cns_lot_size_key" by keyFields "lotSizeKey"
      | lotSizeKey | comments | lotSizeKeyDescription | period | quantity |sourceSystem|localLotSizeKey|
      |     WB       |  BtB Standard, used by all      |    Weekly lot size                   |   W     |       1   |     CONS_LATAM       |   AN            |

    And I wait "/plan/cns_lot_size_key" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmLotSizeKey.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "omp_gdm_lot_size_key.tsv"

    And I check file data for filename "omp_gdm_lot_size_key.tsv" by keyFields "lotSizeKey"
      | lotSizeKey |  activeOprerp |  activeSoperp |  comments                 |  description    |  period  |  quantity  |
      |     WB     |        YES    |               | BtB Standard, used by all | Weekly lot size | W        | 1          |

    And I delete the test data

    And I will remove all data with region "/omp/gdm_lot_size_key"

    And I will remove all data with region "/plan/cns_lot_size_key"

    And I will remove the test file on sink application "omp_gdm_lot_size_key.tsv"

