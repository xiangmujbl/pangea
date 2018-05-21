@pangea_test @AEAZ-4070

Feature:  OMPGdmProductLocationDetail-Curation AEAZ-4070

  @Scenario1
  Scenario:  set value "YES" to ActiveOPRERP

    And I will remove the test file on sink application "ProductLocationDetail.tsv"

  # cns_materilal_plan_status_v1_sourceSystem = cns_prod_loc_attrib-sourceSystem
  # cns_materilal_planstatus_v1_localMaterialNumber = cns_prod_loc_attrib-localMaterialNumber
  # cns_materilal_plan_status_v1_localPlant = cns_prod_loc_attrib-localPlant
  # cns_materilal_plan_status_v1-spRelevant = X
  # cns_material_plan_status_v1-noPlanRelevant <> X
    Given I import "/plan/cns_material_plan_status" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | materialNumber | localParentCode | ppc | active | dpRelevant | spRelevant | parentActive | noPlanRelevant |
      | CONS_LATAM   | LT9999              | BR19       | 9862           | G3a             | G4a | X      | X          | X          | X            |                |

    And I wait "/plan/cns_material_plan_status" Async Queue complete

    Given I import "/plan/cns_prod_loc_attrib" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | schdAttrbName1 | schAttrbDesc1 | schdAttrbName2 | schAttrbDesc2 | schdAttrbName3 | schAttrbDesc3 |
      | CONS_LATAM   | LT9999              | BR19       | ATTRB1         | VALUE1        | ATTRB2         | VALUE2        |                |               |

    And I wait "/plan/cns_prod_loc_attrib" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | sourceSystem | localMaterialNumber | materialNumber | primaryPlanningCode |
      | CONS_LATAM   | LT9999              | 9862           | EM9999              |

    And I wait "/edm/material_global_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmProductLocationDetail.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "ProductLocationDetail.tsv"

    And I check file data for filename "ProductLocationDetail.tsv" by keyFields "productLocationDetailId"
      | unit | comments | productLocationDetailId           | productLocationId      | name   | description | CLASS | value  | activeOPRERP | activeSOPERP |
      |      |          | EM9999-CONS_LATAM_BR19/PGA/ATTRB1 | EM9999-CONS_LATAM_BR19 | ATTRB1 | Pangea      | PGA   | VALUE1 | YES          | NO           |
      |      |          | EM9999-CONS_LATAM_BR19/PGA/ATTRB2 | EM9999-CONS_LATAM_BR19 | ATTRB2 | Pangea      | PGA   | VALUE2 | YES          | NO           |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    Then I delete the test data

    And I will remove all data with region "/omp/gdm_product_location_detail"

    And I will remove all data with region "/plan/edm_failed_data"


  @Scenario2
  Scenario:  set value "YES" to ActiveOPRERP

    And I will remove the test file on sink application "ProductLocationDetail.tsv"

  # cns_materilal_plan_status_v1_sourceSystem = cns_prod_loc_attrib-sourceSystem
  # cns_materilal_planstatus_v1_localMaterialNumber = cns_prod_loc_attrib-localMaterialNumber
  # cns_materilal_plan_status_v1_localPlant = cns_prod_loc_attrib-localPlant
  # cns_materilal_plan_status_v1-spRelevant <> X
  # cns_material_plan_status_v1-noPlanRelevant = X
    Given I import "/plan/cns_material_plan_status" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | materialNumber | localParentCode | ppc | active | dpRelevant | spRelevant | parentActive | noPlanRelevant |
      | CONS_LATAM   | LT9999              | BR19       | 9862           | G3a             | G4a | X      | X          |            | X            | X              |

    And I wait "/plan/cns_material_plan_status" Async Queue complete

    Given I import "/plan/cns_prod_loc_attrib" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | schdAttrbName1 | schAttrbDesc1 | schdAttrbName2 | schAttrbDesc2 | schdAttrbName3 | schAttrbDesc3 |
      | CONS_LATAM   | LT9999              | BR19       | ATTRB1         | VALUE1        | ATTRB2         | VALUE2        |                |               |

    And I wait "/plan/cns_prod_loc_attrib" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | sourceSystem | localMaterialNumber | materialNumber | primaryPlanningCode |
      | CONS_LATAM   | LT9999              | 9862           | EM9999              |

    And I wait "/edm/material_global_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmProductLocationDetail.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "ProductLocationDetail.tsv"

    And I check file data for filename "ProductLocationDetail.tsv" by keyFields "productLocationDetailId"
      | unit | comments | productLocationDetailId           | productLocationId      | name   | description | CLASS | value  | activeOPRERP | activeSOPERP |
      |      |          | EM9999-CONS_LATAM_BR19/PGA/ATTRB1 | EM9999-CONS_LATAM_BR19 | ATTRB1 | Pangea      | PGA   | VALUE1 | YES          | NO           |
      |      |          | EM9999-CONS_LATAM_BR19/PGA/ATTRB2 | EM9999-CONS_LATAM_BR19 | ATTRB2 | Pangea      | PGA   | VALUE2 | YES          | NO           |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    Then I delete the test data

    And I will remove all data with region "/omp/gdm_product_location_detail"

    And I will remove all data with region "/plan/edm_failed_data"


  @Scenario3
  Scenario:  Reject with Error "Not Planning Relevant"

    And I will remove the test file on sink application "ProductLocationDetail.tsv"

  # cns_materilal_plan_status_v1_sourceSystem <> cns_prod_loc_attrib-sourceSystem
  # cns_materilal_planstatus_v1_localMaterialNumber = cns_prod_loc_attrib-localMaterialNumber
  # cns_materilal_plan_status_v1_localPlant = cns_prod_loc_attrib-localPlant
  # cns_materilal_plan_status_v1-spRelevant = X or cns_material_plan_status_v1-noPlanRelevant = X
    Given I import "/plan/cns_material_plan_status" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | materialNumber | localParentCode | ppc | active | dpRelevant | spRelevant | parentActive | noPlanRelevant |
      | BTC          | LT9999              | BR19       | 9862           | G3a             | G4a | X      | X          |            | X            | X              |

    And I wait "/plan/cns_material_plan_status" Async Queue complete

    Given I import "/plan/cns_prod_loc_attrib" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | schdAttrbName1 | schAttrbDesc1 | schdAttrbName2 | schAttrbDesc2 | schdAttrbName3 | schAttrbDesc3 |
      | CONS_LATAM   | LT9999              | BR19       | ATTRB1         | VALUE1        | ATTRB2         | VALUE2        |                |               |

    And I wait "/plan/cns_prod_loc_attrib" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | sourceSystem | localMaterialNumber | materialNumber | primaryPlanningCode |
      | CONS_LATAM   | LT9999              | 9862           | EM9999              |

    And I wait "/edm/material_global_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmProductLocationDetail.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "ProductLocationDetail.tsv"

    And I check file data for filename "ProductLocationDetail.tsv" by keyFields "productLocationDetailId"
      | unit | comments | productLocationDetailId | productLocationId | name | description | CLASS | value | activeOPRERP | activeSOPERP |

    Then I check region data "/dev/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID                 | errorCode | sourceSystem | businessArea | key1 | key2   | key3 | key4 | key5 | errorValue            |
      | SP             | OMPGdmProductLocationDetail | N2        | omp          |              | BTC  | LT9999 | BR19 |      |      | Not Planning Relevant |

    Then I delete the test data

    And I will remove all data with region "/omp/gdm_product_location_detail"

    And I will remove all data with region "/plan/edm_failed_data"


  @Scenario4
  Scenario:  Reject with Error "Not Planning Relevant"

    And I will remove the test file on sink application "ProductLocationDetail.tsv"

  # cns_materilal_plan_status_v1_sourceSystem = cns_prod_loc_attrib-sourceSystem
  # cns_materilal_planstatus_v1_localMaterialNumber <> cns_prod_loc_attrib-localMaterialNumber
  # cns_materilal_plan_status_v1_localPlant = cns_prod_loc_attrib-localPlant
  # cns_materilal_plan_status_v1-spRelevant = X or cns_material_plan_status_v1-noPlanRelevant = X
    Given I import "/plan/cns_material_plan_status" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | materialNumber | localParentCode | ppc | active | dpRelevant | spRelevant | parentActive | noPlanRelevant |
      | CONS_LATAM   | LT8888              | BR19       | 9862           | G3a             | G4a | X      | X          |            | X            | X              |

    And I wait "/plan/cns_material_plan_status" Async Queue complete

    Given I import "/plan/cns_prod_loc_attrib" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | schdAttrbName1 | schAttrbDesc1 | schdAttrbName2 | schAttrbDesc2 | schdAttrbName3 | schAttrbDesc3 |
      | CONS_LATAM   | LT9999              | BR19       | ATTRB1         | VALUE1        | ATTRB2         | VALUE2        |                |               |

    And I wait "/plan/cns_prod_loc_attrib" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | sourceSystem | localMaterialNumber | materialNumber | primaryPlanningCode |
      | CONS_LATAM   | LT9999              | 9862           | EM9999              |

    And I wait "/edm/material_global_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmProductLocationDetail.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "ProductLocationDetail.tsv"

    And I check file data for filename "ProductLocationDetail.tsv" by keyFields "productLocationDetailId"
      | unit | comments | productLocationDetailId | productLocationId | name | description | CLASS | value | activeOPRERP | activeSOPERP |

    Then I check region data "/dev/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID                 | errorCode | sourceSystem | businessArea | key1       | key2   | key3 | key4 | key5 | errorValue            |
      | SP             | OMPGdmProductLocationDetail | N2        | omp          |              | CONS_LATAM | LT8888 | BR19 |      |      | Not Planning Relevant |

    Then I delete the test data

    And I will remove all data with region "/omp/gdm_product_location_detail"

    And I will remove all data with region "/plan/edm_failed_data"


  @Scenario5
  Scenario:  Reject with Error "Not Planning Relevant"

    And I will remove the test file on sink application "ProductLocationDetail.tsv"

  # cns_materilal_plan_status_v1_sourceSystem = cns_prod_loc_attrib-sourceSystem
  # cns_materilal_planstatus_v1_localMaterialNumber = cns_prod_loc_attrib-localMaterialNumber
  # cns_materilal_plan_status_v1_localPlant <> cns_prod_loc_attrib-localPlant
  # cns_materilal_plan_status_v1-spRelevant = X or cns_material_plan_status_v1-noPlanRelevant = X
    Given I import "/plan/cns_material_plan_status" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | materialNumber | localParentCode | ppc | active | dpRelevant | spRelevant | parentActive | noPlanRelevant |
      | CONS_LATAM   | LT9999              | BR18       | 9862           | G3a             | G4a | X      | X          |            | X            | X              |

    And I wait "/plan/cns_material_plan_status" Async Queue complete

    Given I import "/plan/cns_prod_loc_attrib" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | schdAttrbName1 | schAttrbDesc1 | schdAttrbName2 | schAttrbDesc2 | schdAttrbName3 | schAttrbDesc3 |
      | CONS_LATAM   | LT9999              | BR19       | ATTRB1         | VALUE1        | ATTRB2         | VALUE2        |                |               |

    And I wait "/plan/cns_prod_loc_attrib" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | sourceSystem | localMaterialNumber | materialNumber | primaryPlanningCode |
      | CONS_LATAM   | LT9999              | 9862           | EM9999              |

    And I wait "/edm/material_global_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmProductLocationDetail.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "ProductLocationDetail.tsv"

    And I check file data for filename "ProductLocationDetail.tsv" by keyFields "productLocationDetailId"
      | unit | comments | productLocationDetailId | productLocationId | name | description | CLASS | value | activeOPRERP | activeSOPERP |

    Then I check region data "/dev/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID                 | errorCode | sourceSystem | businessArea | key1       | key2   | key3 | key4 | key5 | errorValue            |
      | SP             | OMPGdmProductLocationDetail | N2        | omp          |              | CONS_LATAM | LT9999 | BR18 |      |      | Not Planning Relevant |

    Then I delete the test data

    And I will remove all data with region "/omp/gdm_product_location_detail"

    And I will remove all data with region "/plan/edm_failed_data"


  @Scenario6
  Scenario:  Reject with Error "Not Planning Relevant"

    And I will remove the test file on sink application "ProductLocationDetail.tsv"

  # cns_materilal_plan_status_v1_sourceSystem = cns_prod_loc_attrib-sourceSystem
  # cns_materilal_planstatus_v1_localMaterialNumber = cns_prod_loc_attrib-localMaterialNumber
  # cns_materilal_plan_status_v1_localPlant = cns_prod_loc_attrib-localPlant
  # cns_materilal_plan_status_v1-spRelevant <> X and cns_material_plan_status_v1-noPlanRelevant <> X
    Given I import "/plan/cns_material_plan_status" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | materialNumber | localParentCode | ppc | active | dpRelevant | spRelevant | parentActive | noPlanRelevant |
      | CONS_LATAM   | LT9999              | BR19       | 9862           | G3a             | G4a | X      | X          |            | X            |                |

    And I wait "/plan/cns_material_plan_status" Async Queue complete

    Given I import "/plan/cns_prod_loc_attrib" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | schdAttrbName1 | schAttrbDesc1 | schdAttrbName2 | schAttrbDesc2 | schdAttrbName3 | schAttrbDesc3 |
      | CONS_LATAM   | LT9999              | BR19       | ATTRB1         | VALUE1        | ATTRB2         | VALUE2        |                |               |

    And I wait "/plan/cns_prod_loc_attrib" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | sourceSystem | localMaterialNumber | materialNumber | primaryPlanningCode |
      | CONS_LATAM   | LT9999              | 9862           | EM9999              |

    And I wait "/edm/material_global_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmProductLocationDetail.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "ProductLocationDetail.tsv"

    And I check file data for filename "ProductLocationDetail.tsv" by keyFields "productLocationDetailId"
      | unit | comments | productLocationDetailId | productLocationId | name | description | CLASS | value | activeOPRERP | activeSOPERP |

    Then I check region data "/dev/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID                 | errorCode | sourceSystem | businessArea | key1       | key2   | key3 | key4 | key5 | errorValue            |
      | SP             | OMPGdmProductLocationDetail | N2        | omp          |              | CONS_LATAM | LT9999 | BR19 |      |      | Not Planning Relevant |

    Then I delete the test data

    And I will remove all data with region "/omp/gdm_product_location_detail"

    And I will remove all data with region "/plan/edm_failed_data"