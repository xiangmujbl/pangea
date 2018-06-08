@pangea_test @AEAZ-2378
Feature: OMPLotSizeKey AEAZ-2378

  Scenario: Full Load curation
  # 1. get attributes from cns_lot_size_key

    Given I import "/plan/cns_lot_size_key" by keyFields "lotSizeKey"
      | lotSizeKey | comments   | lotSizeKeyDescription | period | quantity |
      | WB         | Weekly Lot | Weekly lot size       | W      | 1        |
    And I wait "/plan/cns_lot_size_key" Async Queue complete

    When I submit task with xml file "xml/omp/OMPLotSizeKey.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/omp/lot_size_key" by keyFields "lotSizeKey"
      | lotSizeKey | activeOPRERP | activeSOPERP | comments   | description     | period | quantity |
      | WB         | YES          | NO           | Weekly Lot | Weekly lot size | W      | 1        |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/plan/cns_lot_size_key" and "/omp/lot_size_key,/plan/edm_failed_data"

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/omp/lot_size_key"

    And I will remove all data with region "/plan/edm_failed_data"

