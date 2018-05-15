@pangea_test @AEAZ-3675
Feature: PlanCnsRootDescription AEAZ-3675

  Scenario: Full Load curation
  #  1. Only create records in cns_root_description if material_global_v1-localDpParentCode is not available in cns_root_description-localDpParentCode (Rule:R1)
  #  2. If an entry exists, then don’t overwrite the old record (Rule:R1)
  #  3. When cns_material_plan_status-dpRelevant = X, skip the record (Rule:R1)
  #  4. Get  the first material_global_v1-refDescription using material_global_v1-localDpParentCode (Rule:R2)

    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber"
      | localMaterialNumber | localDpParentCode  | refDescription                        |
      | 000000000000000945  | 178962124094540036 | TYLENOL*CRIANCA LIQ SUSPENSAO 36X60ML |
      | 000000000000002886  | 178962124288680036 | TYLENOL* BEBE GOTAS SUSP 36FRX15ML    |
      | 000000000000004000  | 178962124288680036 | J'S BABY SHAMPOO 12X750ML - ARG 733   |
      | 000000000000000095  | 178962124288680037 | J'S BABY SHAMPOO 12X400ML - ARG 3093  |

    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | sourceSystem |
      | project_one       | CONS_LATAM   |
      | [EMS]             | EMS          |

    And I wait "/edm/source_system_v1" Async Queue complete

    Given I import "/plan/cns_material_plan_status" by keyFields "localMaterialNumber"
      | localMaterialNumber | localPlant | dpRelevant | localParentCode    |
      | 000000000000000945  | BR25       | X          | 178962124094540036 |
      | 000000000000001308  | BR12       | -          |                    |
      | 000000000000002886  | BR07       | X          | 178962124288680036 |
      | 000000000000004000  | BR07       | X          | 178962124288680036 |

    And I wait "/plan/cns_material_plan_status" Async Queue complete

    When I submit task with xml file "xml/plan/PlanCnsRootDescription.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/plan/cns_root_description" by keyFields "sourceSystem,localDpParentCode,ovrRootDesc"
      | sourceSystem | localDpParentCode  | rootDesc | ovrRootDesc                           |
      | CONS_LATAM   | 178962124094540036 |          | TYLENOL*CRIANCA LIQ SUSPENSAO 36X60ML |
      | CONS_LATAM   | 178962124288680036 |          | TYLENOL* BEBE GOTAS SUSP 36FRX15ML    |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

#    And I compare the number of records between "/edm/material_global_v1" and "/plan/cns_root_description,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/plan/cns_root_description"

    And I will remove all data with region "/plan/edm_failed_data"

