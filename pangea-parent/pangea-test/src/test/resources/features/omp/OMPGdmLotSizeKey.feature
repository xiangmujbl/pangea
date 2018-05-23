@pangea_test @AEAZ-4482
Feature:  OMPGdmLotSizeKey-Curation
#
  Scenario: Full Load curation

    And I will remove the test file on sink application "LotSizeKey.tsv"

    Given I import "/plan/cns_lot_size_key" by keyFields "lotSizeKey"
      | lotSizeKey |          comments           | lotSizeKeyDescription           | period | quantity | sourceSystem | localLotSizeKey |
      |     WB     |  Weekly lot                 | Weekly lot size                 |   W    |    1     | CONS_LATAM   |   AN            |
      |     EX     |  Lot for Lot                | Lot-for-lot order quantity (JJ) |   E    |    0     | CONS_LATAM   |   AN            |
      |     FX     |  Fixed Lot                  | Fixed order quantity (JJ)       |   F    |    0     | CONS_LATAM   |   AN            |
      |     MB     |  Monthly Lot                | Monthly lot size (JJ)           |   M    |    1     | CONS_LATAM   |   AN            |
      |     Z1     |  10 Day Lot                 | 10 Days                         |   T    |    10    | CONS_LATAM   |   AN            |


    And I wait "/plan/cns_lot_size_key" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmLotSizeKey.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "LotSizeKey.tsv"

    And I check file data for filename "LotSizeKey.tsv" by keyFields "lotSizeKey"
      |  activeOPRERP |  period  |  comments   |  quantity  | lotSizeKey |  activeSOPERP |   description                    |
      |        YES    | F        | Fixed Lot   | 0          |     FX     |       NO      |  Fixed order quantity (JJ)       |
      |        YES    | T        | 10 Day Lot  | 10         |     Z1     |       NO      |  10 Days                         |
      |        YES    | M        | Monthly Lot | 1          |     MB     |       NO      |  Monthly lot size (JJ)           |
      |        YES    | W        | Weekly lot  | 1          |     WB     |       NO      |  Weekly lot size                 |
      |        YES    | E        | Lot for Lot | 0          |     EX     |       NO      |  Lot-for-lot order quantity (JJ) |

    And I delete the test data

    And I will remove all data with region "/omp/gdm_lot_size_key"

    And I will remove all data with region "/plan/cns_lot_size_key"
