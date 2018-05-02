@pangea_test
Feature: OMPGdmPos

  Scenario: Full Load curation

    Given I import "/plan/cns_dp_pos" by keyFields "localMaterial,customer,yearMonth"
      | localMaterial      | customer | yearMonth | quantity |
      | 000000000000000010 | 104076   | 201801    | 1426     |
      | 000000000000000011 | 104076   | 201802    | 860      |

    And I wait "/plan/cns_dp_pos" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber"
      | localMaterialNumber | localBaseUom | localDpParentCode |
      | 000000000000000010  | KI           | 10                |
      | 000000000000000011  | KI           |                   |

    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/plan/cns_plan_unit" by keyFields "localUom,unit"
      | localUom | unit |
      | KI       | KI   |
      | KG       | KG   |

    And I wait "/plan/cns_plan_unit" Async Queue complete

    Given I import "/plan/cns_dem_grp_asgn" by keyFields "customerId"
      | customerId | customerShipTo | group |
      | 10206      | 104076         | 1     |
      | 10236      | 104076         | 2     |

    And I wait "/plan/cns_dem_grp_asgn" Async Queue complete

    Given I import "/project_one/knvh" by keyFields "kunnr"
      | kunnr  | hkunnr | datbi    |
      | 104076 | 104076 | 99991231 |

    And I wait "/project_one/knvh" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmPos.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/omp/gdm_pos" by keyFields "aggregationId,unitId"
      | aggregationId | currencyId | dueDate | forecastUploadId | fromDueDate | unitId | quantity |
      | 10-1          |            | 201801  | 10-1-201801      | 201801      | KI     | 1426     |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | errorCode | functionalArea | interfaceID | key1               | key2   | key3   | key4 | key5 | errorValue          | sourceSystem |
      | T1        | DP             | GDMPOS      | 000000000000000010 | 104076 | 201801 |      |      | Unable to find Root | omp          |

    And I compare the number of records between "/plan/cns_dp_pos" and "/omp/gdm_pos,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/omp/gdm_pos"

    And I will remove all data with region "/plan/edm_failed_data"

