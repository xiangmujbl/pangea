@pangea_test @AEAZ-4070

Feature:  OMPGdmProductLocationDetail-Curation AEAZ-4070

  @Scenario1
  Scenario:  Filter data and get productLocationDetailId (Rule F1,E1,C2)

    And I will remove the test file on sink application "ProductLocationDetail.tsv"

    Given I import "/plan/cns_material_plan_status" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | materialNumber | localParentCode | ppc | active | dpRelevant | spRelevant | parentActive | noPlanRelevant |
      | CONS_LATAM   | 000000000000000945  | BR19       | 9862           | G3a             | G4a | X      | X          | X          | X            |                |
      | CONS_LATAM   | 000000000000000999  | CQ10       | 9862           | G3a             | G4a | X      | X          |            | X            | X              |

      # when spRelevant <> X and noPlanRelevant <> X, then filter the record.
      | CONS_LATAM   | 000000000000000999  | BR19       | 9862           | G3a             | G4a | X      | X          |            | X            |                |

      # when cns_prod_loc_attrib-sourceSystem <> cns_material_plan_status-sourceSystem, then skip the record.
      | TOSC_ADCGA   | 000000000000000945  | BR19       | 9862           | G3a             | G4a | X      | X          | X          | X            |                |

      # when cns_prod_loc_attrib-localMaterialNumber <> cns_material_plan_status-localMaterialNumber, then skip the record.
      | CONS_LATAM   | 000000000000000731  | BR19       | 9862           | G3a             | G4a | X      | X          | X          | X            |                |

      # when cns_prod_loc_attrib-localPlant <> cns_material_plan_status-localPlant, then skip the record.
      | CONS_LATAM   | 000000000000000999  | BR79       | 9862           | G3a             | G4a | X      | X          | X          | X            |                |

    And I wait "/plan/cns_material_plan_status" Async Queue complete

    Given I import "/plan/cns_prod_loc_attrib" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | schdAttrbName1 | schAttrbDesc1 | schdAttrbName2 | schAttrbDesc2 | schdAttrbName3 | schAttrbDesc3 |
      | CONS_LATAM   | 000000000000000945  | BR19       | ATTRB1         | VALUE1        | ATTRB2         | VALUE2        |                |               |
      | CONS_LATAM   | 000000000000000999  | CQ10       | ATTRB1         | VALUE1        | ATTRB2         | VALUE2        |                |               |

    And I wait "/plan/cns_prod_loc_attrib" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | sourceSystem | localMaterialNumber | materialNumber | primaryPlanningCode |
      | CONS_LATAM   | 000000000000000945  | 9862           | EM9999              |
      | CONS_LATAM   | 000000000000000999  | 9862           | EM9999              |

    And I wait "/edm/material_global_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmProductLocationDetail.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "ProductLocationDetail.tsv"

    And I check file data for filename "ProductLocationDetail.tsv" by keyFields "productLocationDetailId"
      | unit | comments | productLocationDetailId           | productLocationId      | name   | description | CLASS | value  | activeOPRERP | activeSOPERP |
      |      |          | EM9999-CONS_LATAM_BR19/PGA/ATTRB1 | EM9999-CONS_LATAM_BR19 | ATTRB1 | Pangea      | PGA   | VALUE1 | YES          | NO           |
      |      |          | EM9999-CONS_LATAM_BR19/PGA/ATTRB2 | EM9999-CONS_LATAM_BR19 | ATTRB2 | Pangea      | PGA   | VALUE2 | YES          | NO           |
      |      |          | EM9999-CONS_LATAM_CQ10/PGA/ATTRB1 | EM9999-CONS_LATAM_CQ10 | ATTRB1 | Pangea      | PGA   | VALUE1 | YES          | NO           |
      |      |          | EM9999-CONS_LATAM_CQ10/PGA/ATTRB2 | EM9999-CONS_LATAM_CQ10 | ATTRB2 | Pangea      | PGA   | VALUE2 | YES          | NO           |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |


    Then I delete the test data

    And I will remove all data with region "/omp/gdm_product_location_detail"

    And I will remove all data with region "/plan/edm_failed_data"


  @Scenario2
  Scenario: Reject with Error "Not Planning Relevant"(Rule N2)

    And I will remove the test file on sink application "ProductLocationDetail.tsv"

    Given I import "/plan/cns_material_plan_status" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | materialNumber | localParentCode | ppc | active | dpRelevant | spRelevant | parentActive | noPlanRelevant |
      | CONS_LATAM   | 000000000000000945  | BR19       | 9862           | G3a             | G4a | X      | X          | X          | X            |                |
      | CONS_LATAM   | 000000000007709000  | EC01       | 9862           | G3a             | G4a | X      | X          | X          | X            |                |

      # when cns_materilal_plan_status-spRelevant <> X and cns_material_plan_status_v1-noPlanRelevant <> X, then reject the record.
      | CONS_LATAM   | 000000000000000945  | BR19       | 9862           | G3a             | G4a | X      | X          |            | X            |                |

      # when ns_materilal_plan_status-sourceSystem <> cns_prod_loc_attrib-sourceSystem, then reject the record.
      | TOSC_ADCGA   | 000000000000000945  | BR19       | 9862           | G3a             | G4a | X      | X          | X          | X            |                |

      # when ns_materilal_plan_status-localMaterialNumber <> cns_prod_loc_attrib-localMaterialNumber, then reject the record.
      | CONS_LATAM   | 000000000000000731  | BR19       | 9862           | G3a             | G4a | X      | X          | X          | X            |                |

      # when ns_materilal_plan_status-localPlant <> cns_prod_loc_attrib-localPlant, then reject the record.
      | CONS_LATAM   | 000000000007709000  | BR79       | 9862           | G3a             | G4a | X      | X          | X          | X            |                |

    And I wait "/plan/cns_material_plan_status" Async Queue complete

    Given I import "/plan/cns_prod_loc_attrib" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | schdAttrbName1 | schAttrbDesc1 | schdAttrbName2 | schAttrbDesc2 | schdAttrbName3 | schAttrbDesc3 |
      | CONS_LATAM   | 000000000000000945  | BR19       | ATTRB1         | VALUE1        | ATTRB2         | VALUE2        |                |               |
      | CONS_LATAM   | 000000000007709000  | EC01       | ATTRB1         | VALUE1        | ATTRB2         | VALUE2        |                |               |

    And I wait "/plan/cns_prod_loc_attrib" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | sourceSystem | localMaterialNumber | materialNumber | primaryPlanningCode |
      | CONS_LATAM   | 000000000000000945  | 9862           | EM9999              |
      | CONS_LATAM   | 000000000007709000  | 9862           | EM9999              |

    And I wait "/edm/material_global_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmProductLocationDetail.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "ProductLocationDetail.tsv"

    And I check file data for filename "ProductLocationDetail.tsv" by keyFields "productLocationDetailId"
      | unit | comments | productLocationDetailId           | productLocationId      | name   | description | CLASS | value  | activeOPRERP | activeSOPERP |
      |      |          | EM9999-CONS_LATAM_BR19/PGA/ATTRB1 | EM9999-CONS_LATAM_BR19 | ATTRB1 | Pangea      | PGA   | VALUE1 | YES          | NO           |
      |      |          | EM9999-CONS_LATAM_BR19/PGA/ATTRB2 | EM9999-CONS_LATAM_BR19 | ATTRB2 | Pangea      | PGA   | VALUE2 | YES          | NO           |
      |      |          | EM9999-CONS_LATAM_EC01/PGA/ATTRB1 | EM9999-CONS_LATAM_EC01 | ATTRB1 | Pangea      | PGA   | VALUE1 | YES          | NO           |
      |      |          | EM9999-CONS_LATAM_EC01/PGA/ATTRB2 | EM9999-CONS_LATAM_EC01 | ATTRB2 | Pangea      | PGA   | VALUE2 | YES          | NO           |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID                 | errorCode | sourceSystem | businessArea | key1       | key2               | key3 | key4 | key5 | errorValue            |
      | SP             | OMPGdmProductLocationDetail | N2        | omp          |              | CONS_LATAM | 000000000000000999 | BR19 |      |      | Not Planning Relevant |
      | SP             | OMPGdmProductLocationDetail | N2        | omp          |              | TOSC_ADCGA | 000000000000000945 | BR19 |      |      | Not Planning Relevant |
      | SP             | OMPGdmProductLocationDetail | N2        | omp          |              | CONS_LATAM | 000000000000000731 | BR19 |      |      | Not Planning Relevant |
      | SP             | OMPGdmProductLocationDetail | N2        | omp          |              | CONS_LATAM | 000000000007709000 | BR79 |      |      | Not Planning Relevant |

    Then I delete the test data

    And I will remove all data with region "/omp/gdm_product_location_detail"

    And I will remove all data with region "/plan/edm_failed_data"


  @Scenario3
  Scenario:   (Rule C3,C4)

    #send "PGA" to CLASS description if material_global_v1-sourceSystem = 'CONS_LATAM'
    #send "Pangea" to description if material_global_v1-sourceSystem = 'CONS_LATAM'
    And I will remove the test file on sink application "ProductLocationDetail.tsv"

    Given I import "/plan/cns_material_plan_status" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | materialNumber | localParentCode | ppc | active | dpRelevant | spRelevant | parentActive | noPlanRelevant |
      | CONS_LATAM   | 000000000000000945  | BR19       | 9862           | G3a             | G4a | X      | X          | X          | X            |                |
      | TGCP_LATAM   | 000000000007709000  | EC01       | 9862           | G3a             | G4a | X      | X          | X          | X            |                |

    And I wait "/plan/cns_material_plan_status" Async Queue complete

    Given I import "/plan/cns_prod_loc_attrib" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | schdAttrbName1 | schAttrbDesc1 | schdAttrbName2 | schAttrbDesc2 | schdAttrbName3 | schAttrbDesc3 |
      | CONS_LATAM   | 000000000000000945  | BR19       | ATTRB1         | VALUE1        | ATTRB2         | VALUE2        |                |               |
      | TGCP_LATAM   | 000000000007709000  | EC01       | ATTRB1         | VALUE1        | ATTRB2         | VALUE2        |                |               |

    And I wait "/plan/cns_prod_loc_attrib" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | sourceSystem | localMaterialNumber | materialNumber | primaryPlanningCode |
      | CONS_LATAM   | 000000000000000945  | 9862           | EM9999              |
      | TGCP_LATAM   | 000000000007709000  | 9862           | EM9999              |

    And I wait "/edm/material_global_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmProductLocationDetail.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "ProductLocationDetail.tsv"

    And I check file data for filename "ProductLocationDetail.tsv" by keyFields "productLocationDetailId"
      | unit | comments | productLocationDetailId           | productLocationId      | name   | description | CLASS | value  | activeOPRERP | activeSOPERP |
      |      |          | EM9999-CONS_LATAM_BR19/PGA/ATTRB1 | EM9999-CONS_LATAM_BR19 | ATTRB1 | Pangea      | PGA   | VALUE1 | YES          | NO           |
      |      |          | EM9999-CONS_LATAM_BR19/PGA/ATTRB2 | EM9999-CONS_LATAM_BR19 | ATTRB2 | Pangea      | PGA   | VALUE2 | YES          | NO           |
      |      |          | EM9999-TGCP_LATAM_EC01/PGA/ATTRB1 | EM9999-TGCP_LATAM_EC01 | ATTRB1 |             |       | VALUE1 | YES          | NO           |
      |      |          | EM9999-TGCP_LATAM_EC01/PGA/ATTRB2 | EM9999-TGCP_LATAM_EC01 | ATTRB2 |             |       | VALUE2 | YES          | NO           |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    Then I delete the test data

    And I will remove all data with region "/omp/gdm_product_location_detail"

    And I will remove all data with region "/plan/edm_failed_data"


  @Scenario4
  Scenario:  (Rule E2,C1)

    And I will remove the test file on sink application "ProductLocationDetail.tsv"

    Given I import "/plan/cns_material_plan_status" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | materialNumber | localParentCode | ppc | active | dpRelevant | spRelevant | parentActive | noPlanRelevant |
      | CONS_LATAM   | 000000000000000945  | BR19       | 9862           | G3a             | G4a | X      | X          | X          | X            |                |
      | CONS_LATAM   | 000000000007706120  | EC01       | 9862           | G3a             | G4a | X      | X          | X          | X            |                |
      | TCPD_AUCEE   | 000000000007706190  | EC01       | 9862           | G3a             | G4a | X      | X          | X          | X            |                |

    And I wait "/plan/cns_material_plan_status" Async Queue complete

    Given I import "/plan/cns_prod_loc_attrib" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | schdAttrbName1 | schAttrbDesc1 | schdAttrbName2 | schAttrbDesc2 | schdAttrbName3 | schAttrbDesc3 |

      # material_global_v1-localMaterialNumber = cns_prod_loc_attrib-localMaterialNumber
      # material_global_v1-sourceSystem = cns_prod_loc_attrib-sourceSystem
      | CONS_LATAM   | 000000000000000945  | BR19       | ATTRB1         | VALUE1        | ATTRB2         | VALUE2        |                |               |

      # when material_global_v1-localMaterialNumber <> cns_prod_loc_attrib-localMaterialNumber, then skip the record.
      | CONS_LATAM   | 000000000007706120  | EC01       | ATTRB1         | VALUE1        | ATTRB2         | VALUE2        |                |               |

      # when material_global_v1-sourceSystem <> cns_prod_loc_attrib-sourceSystem, then skip the record.
      | TCPD_AUCEE   | 000000000007706190  | EC01       | ATTRB1         | VALUE1        | ATTRB2         | VALUE2        |                |               |

    And I wait "/plan/cns_prod_loc_attrib" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | sourceSystem | localMaterialNumber | materialNumber | primaryPlanningCode |
      | CONS_LATAM   | 000000000000000945  | 9862           | EM9999              |
      | CONS_LATAM   | 000000000007709000  | 9862           | EM9999              |
      | CONS_LATAM   | 000000000007706190  | 9862           | EM9999              |

    And I wait "/edm/material_global_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmProductLocationDetail.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "ProductLocationDetail.tsv"

    # get productLocationId by primaryPlanningCode + "_" + cns_prod_loc_attrib-sourceSystem + "_" + cns_prod_loc_attrib-localPlant (C1)
    And I check file data for filename "ProductLocationDetail.tsv" by keyFields "productLocationDetailId"
      | unit | comments | productLocationDetailId           | productLocationId      | name   | description | CLASS | value  | activeOPRERP | activeSOPERP |
      |      |          | EM9999-CONS_LATAM_BR19/PGA/ATTRB1 | EM9999-CONS_LATAM_BR19 | ATTRB1 | Pangea      | PGA   | VALUE1 | YES          | NO           |
      |      |          | EM9999-CONS_LATAM_BR19/PGA/ATTRB2 | EM9999-CONS_LATAM_BR19 | ATTRB2 | Pangea      | PGA   | VALUE2 | YES          | NO           |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |


    Then I delete the test data

    And I will remove all data with region "/omp/gdm_product_location_detail"

    And I will remove all data with region "/plan/edm_failed_data"

  @Scenario5
  Scenario:  (Rule N1,N3)

    # send blank to unit (N1)
    # send "NO" to activeSOPERP (N3)
    And I will remove the test file on sink application "ProductLocationDetail.tsv"

    Given I import "/plan/cns_material_plan_status" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | materialNumber | localParentCode | ppc | active | dpRelevant | spRelevant | parentActive | noPlanRelevant |
      | CONS_LATAM   | 000000000000000945  | BR19       | 9862           | G3a             | G4a | X      | X          | X          | X            |                |

    And I wait "/plan/cns_material_plan_status" Async Queue complete

    Given I import "/plan/cns_prod_loc_attrib" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | schdAttrbName1 | schAttrbDesc1 | schdAttrbName2 | schAttrbDesc2 | schdAttrbName3 | schAttrbDesc3 |
      | CONS_LATAM   | 000000000000000945  | BR19       | ATTRB1         | VALUE1        | ATTRB2         | VALUE2        |                |               |

    And I wait "/plan/cns_prod_loc_attrib" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | sourceSystem | localMaterialNumber | materialNumber | primaryPlanningCode |
      | CONS_LATAM   | 000000000000000945  | 9862           | EM9999              |

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


  @Scenario6
  Scenario: Create multiple records based on each combination of Attribute Description and Attribute Value pair (Rule N4)

    And I will remove the test file on sink application "ProductLocationDetail.tsv"

    Given I import "/plan/cns_material_plan_status" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | materialNumber | localParentCode | ppc | active | dpRelevant | spRelevant | parentActive | noPlanRelevant |
      | CONS_LATAM   | 000000000000000945  | BR19       | 9862           | G3a             | G4a | X      | X          | X          | X            |                |

    And I wait "/plan/cns_material_plan_status" Async Queue complete

    Given I import "/plan/cns_prod_loc_attrib" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | schdAttrbName1 | schAttrbDesc1 | schdAttrbName2 | schAttrbDesc2 | schdAttrbName3 | schAttrbDesc3 |
      | CONS_LATAM   | 000000000000000945  | BR19       | ATTRB1         | VALUE1        | ATTRB2         | VALUE2        | ATTRB3         | VALUE3        |

    And I wait "/plan/cns_prod_loc_attrib" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | sourceSystem | localMaterialNumber | materialNumber | primaryPlanningCode |
      | CONS_LATAM   | 000000000000000945  | 9862           | EM9999              |

    And I wait "/edm/material_global_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmProductLocationDetail.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "ProductLocationDetail.tsv"

    And I check file data for filename "ProductLocationDetail.tsv" by keyFields "productLocationDetailId"
      | unit | comments | productLocationDetailId           | productLocationId      | name   | description | CLASS | value  | activeOPRERP | activeSOPERP |
      |      |          | EM9999-CONS_LATAM_BR19/PGA/ATTRB1 | EM9999-CONS_LATAM_BR19 | ATTRB1 | Pangea      | PGA   | VALUE1 | YES          | NO           |
      |      |          | EM9999-CONS_LATAM_BR19/PGA/ATTRB2 | EM9999-CONS_LATAM_BR19 | ATTRB2 | Pangea      | PGA   | VALUE2 | YES          | NO           |
      |      |          | EM9999-CONS_LATAM_BR19/PGA/ATTRB3 | EM9999-CONS_LATAM_BR19 | ATTRB3 | Pangea      | PGA   | VALUE3 | YES          | NO           |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    Then I delete the test data

    And I will remove all data with region "/omp/gdm_product_location_detail"

    And I will remove all data with region "/plan/edm_failed_data"

