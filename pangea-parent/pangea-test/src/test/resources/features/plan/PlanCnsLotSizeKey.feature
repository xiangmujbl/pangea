@pangea_test @AEAZ-1907
Feature: PlanCnsLotSizeKey AEAZ-1907

  Scenario: Full Load curation

    Given I import "/project_one/t439a" by keyFields "disls"
      | loskz | peraz | disls |
      | W     | 200   | EX    |

    And I wait "/project_one/t439a" Async Queue complete

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | sourceSystem |
      | project_one       | BTB          |

    And I wait "/edm/source_system_v1" Async Queue complete

    Given I import "/project_one/t439t" by keyFields "disls"
      | loslt       | disls | spras |
      | Lot for Lot | EX    | EN    |

    And I wait "/project_one/t439t" Async Queue complete

    When I submit task with xml file "xml/plan/PlanCnsLotSizeKey.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/plan/cns_lot_size_key" by keyFields "sourceSystem,localLotSizeKey"
      | sourceSystem | localLotSizeKey | localLotSizeKeyDescription | period | quantity | lotSizeKey | lotSizeKeyDescription | comments |
      | BTB          | EX              | Lot for Lot                | W      | 200      |            |                       |          |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/project_one/t439a" and "/plan/cns_lot_size_key,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/plan/cns_lot_size_key"

    And I will remove all data with region "/plan/edm_failed_data"

