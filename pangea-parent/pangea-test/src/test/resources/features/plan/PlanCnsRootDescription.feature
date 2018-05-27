@pangea_test @AEAZ-3675
Feature: PlanCnsRootDescription AEAZ-3675

  @Scenario1
  Scenario: Only create records in cns_root_description if material_global_v1-localDpParentCode is not available in cns_root_description-localDpParentCode(R1)

    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber"
      | localMaterialNumber | localDpParentCode  | refDescription                        |
      | 000000000000000945  | 178962124094540036 | TYLENOL*CRIANCA LIQ SUSPENSAO 36X60ML |

    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | sourceSystem |
      | project_one       | CONS_LATAM   |

    And I wait "/edm/source_system_v1" Async Queue complete

    Given I import "/plan/cns_material_plan_status" by keyFields "localMaterialNumber"
      | localMaterialNumber | localPlant | dpRelevant | localParentCode    |
      | 000000000000000945  | BR25       | X          | 178962124094540036 |

    And I wait "/plan/cns_material_plan_status" Async Queue complete

    Given I import "/plan/cns_root_description_user_override" by keyFields "sourceSystem,localDpParentCode"
      | sourceSystem | localDpParentCode  | rootDesc                              | ovrRootDesc                        |
      | CONS_LATAM   | 178962124094540036 | TYLENOL*CRIANCA LIQ SUSPENSAO 36X60ML | TYLENOL* BEBE GOTAS SUSP 36FRX15ML |

    And I wait "/plan/user_override" Async Queue complete

    When I submit task with xml file "xml/plan/PlanCnsRootDescription.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/plan/cns_root_description" by keyFields "sourceSystem,localDpParentCode"
      | sourceSystem | localDpParentCode  | rootDesc                              | ovrRootDesc                        |
      | CONS_LATAM   | 178962124094540036 | TYLENOL*CRIANCA LIQ SUSPENSAO 36X60ML | TYLENOL* BEBE GOTAS SUSP 36FRX15ML |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/edm/material_global_v1" and "/plan/cns_root_description,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/plan/cns_root_description"

    And I will remove all data with region "/plan/edm_failed_data"


  @Scenario2
  Scenario: If an entry exists, then don’t overwrite the old record(R1)

    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber"
      | localMaterialNumber | localDpParentCode  | refDescription                      |
      | 000000000000002886  | 178962124288680036 | TYLENOL* BEBE GOTAS SUSP 36FRX15ML  |
      | 000000000000004000  | 178962124288680036 | J'S BABY SHAMPOO 12X750ML - ARG 733 |

    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | sourceSystem |
      | project_one       | CONS_LATAM   |

    And I wait "/edm/source_system_v1" Async Queue complete

    Given I import "/plan/cns_material_plan_status" by keyFields "localMaterialNumber"
      | localMaterialNumber | localPlant | dpRelevant | localParentCode    |
      | 000000000000002886  | BR07       | X          | 178962124288680036 |
      | 000000000000004000  | BR07       | X          | 178962124288680036 |

    And I wait "/plan/cns_material_plan_status" Async Queue complete

    Given I import "/plan/cns_root_description_user_override" by keyFields "sourceSystem,localDpParentCode"
      | sourceSystem | localDpParentCode  | rootDesc                           | ovrRootDesc                         |
      | CONS_LATAM   | 178962124288680036 | TYLENOL* BEBE GOTAS SUSP 36FRX15ML | J'S BABY SHAMPOO 12X750ML - ARG 733 |

    And I wait "/plan/cns_material_plan_status" Async Queue complete

    When I submit task with xml file "xml/plan/PlanCnsRootDescription.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/plan/cns_root_description" by keyFields "sourceSystem,localDpParentCode"
      | sourceSystem | localDpParentCode  | rootDesc                           | ovrRootDesc                         |
      | CONS_LATAM   | 178962124288680036 | TYLENOL* BEBE GOTAS SUSP 36FRX15ML | J'S BABY SHAMPOO 12X750ML - ARG 733 |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

#    And I compare the number of records between "/edm/material_global_v1" and "/plan/cns_root_description,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/plan/cns_root_description"

    And I will remove all data with region "/plan/edm_failed_data"


  @Scenario3
  Scenario: When cns_material_plan_status-dpRelevant <> X, skip the record(R1)

    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber"
      | localMaterialNumber | localDpParentCode  | refDescription                        |
      | 000000000000000945  | 178962124094540036 | TYLENOL*CRIANCA LIQ SUSPENSAO 36X60ML |

    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | sourceSystem |
      | project_one       | CONS_LATAM   |

    And I wait "/edm/source_system_v1" Async Queue complete

    Given I import "/plan/cns_material_plan_status" by keyFields "localMaterialNumber"
      | localMaterialNumber | localPlant | dpRelevant | localParentCode    |
      | 000000000000000945  | BR25       | -          | 178962124094540036 |

    And I wait "/plan/cns_material_plan_status" Async Queue complete

    Given I import "/plan/cns_root_description_user_override" by keyFields "sourceSystem,localDpParentCode"
      | sourceSystem | localDpParentCode | rootDesc | ovrRootDesc |

    And I wait "/plan/cns_material_plan_status" Async Queue complete

    When I submit task with xml file "xml/plan/PlanCnsRootDescription.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/plan/cns_root_description" by keyFields "sourceSystem,localDpParentCode"
      | sourceSystem | localDpParentCode | rootDesc | ovrRootDesc |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

#    And I compare the number of records between "/edm/material_global_v1" and "/plan/cns_root_description,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/plan/cns_root_description"

    And I will remove all data with region "/plan/edm_failed_data"


  @Scenario4
  Scenario: Get the first material_global_v1-refDescription using material_global_v1-localDpParentCode(R2)

    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber"
      | localMaterialNumber | localDpParentCode  | refDescription                      |
      | 000000000000002886  | 178962124288680036 | TYLENOL* BEBE GOTAS SUSP 36FRX15ML  |
      | 000000000000004000  | 178962124288680036 | J'S BABY SHAMPOO 12X750ML - ARG 733 |

    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | sourceSystem |
      | project_one       | CONS_LATAM   |

    And I wait "/edm/source_system_v1" Async Queue complete

    Given I import "/plan/cns_material_plan_status" by keyFields "localMaterialNumber"
      | localMaterialNumber | localPlant | dpRelevant | localParentCode    |
      | 000000000000002886  | BR07       | X          | 178962124288680036 |
      | 000000000000004000  | BR07       | X          | 178962124288680036 |

    And I wait "/plan/cns_material_plan_status" Async Queue complete

    Given I import "/plan/cns_root_description_user_override" by keyFields "sourceSystem,localDpParentCode"
      | sourceSystem | localDpParentCode  | rootDesc                           | ovrRootDesc                         |
      | CONS_LATAM   | 178962124288680036 | TYLENOL* BEBE GOTAS SUSP 36FRX15ML | J'S BABY SHAMPOO 12X750ML - ARG 744 |

    And I wait "/plan/cns_material_plan_status" Async Queue complete

    When I submit task with xml file "xml/plan/PlanCnsRootDescription.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/plan/cns_root_description" by keyFields "sourceSystem,localDpParentCode"
      | sourceSystem | localDpParentCode  | rootDesc                           | ovrRootDesc                         |
      | CONS_LATAM   | 178962124288680036 | TYLENOL* BEBE GOTAS SUSP 36FRX15ML | J'S BABY SHAMPOO 12X750ML - ARG 744 |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

#    And I compare the number of records between "/edm/material_global_v1" and "/plan/cns_root_description,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/plan/cns_root_description"

    And I will remove all data with region "/plan/edm_failed_data"