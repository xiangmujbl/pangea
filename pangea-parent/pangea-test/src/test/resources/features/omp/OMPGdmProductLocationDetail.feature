@pangea_test @AEAZ-4070

Feature:  OMPGdmProductLocationDetail-Curation AEAZ-4070

  # 1. Reject record when cns_material_plan_status where spRelevant <> X and npPlanRelevant <> X (N2)
  # 2. Reject record when cns_prod_loc_attrib-sourceSystem <> cns_material_plan_status-sourceSystem (N2)
  # 3. Reject record when cns_prod_loc_attrib-localMaterialNumber <> cns_material_plan_status-localMaterialNumber (N2)
  # 4. Reject record when cns_prod_loc_attrib-localPlant <> cns_material_plan_status-localPlant (N2)
  # 5. Create multiple records based on each combination of Attribute Description and Attribute Value pair (N4)
  # 6. Send "PGA" to CLASS description if material_global_v1-sourceSystem = 'CONS_LATAM' (C3)
  # 7. Send "Pangea" to description if material_global_v1-sourceSystem = 'CONS_LATAM' (C4)
  # 8. Get productLocationId by primaryPlanningCode + "_" + cns_prod_loc_attrib-sourceSystem + "_" + cns_prod_loc_attrib-localPlant (C1)
  # 9. Send blank to unit (N1)
  # 10.Send "NO" to activeSOPERP (N3)
  # 11.Both material_global_v1-primaryPlanningCode and material_global_v1-materialNumber are not available then reject the record (E2)

  Scenario:  Full Load consumption

    And I will remove the test file on sink application "GDMProductLocationDetail.tsv"

    Given I import "/plan/cns_material_plan_status" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | materialNumber | localParentCode    | ppc  | active | dpRelevant | spRelevant | parentActive | noPlanRelevant |
      | CONS_LATAM   | 000000000000000945  | BR19       | 9862           | G3a                | G4a  | X      | X          | X          | X            |                |
      | CONS_LATAM   | 000000000007709000  | EC01       | 9862           | G3a                | G4a  | X      | X          | X          | X            |                |

      # when cns_materilal_plan_status-spRelevant <> X and cns_material_plan_status_v1-noPlanRelevant <> X, then reject the record.
      | CONS_LATAM   | 000000000000000999  | BR19       | 9862           | G3a                | G4a  | X      | X          |            | X            |                |

      # when ns_materilal_plan_status-sourceSystem <> cns_prod_loc_attrib-sourceSystem, then reject the record.
      | TOSC_ADCGA   | 000000000000000945  | BR19       | 9862           | G3a                | G4a  | X      | X          | X          | X            |                |

      # when ns_materilal_plan_status-localMaterialNumber <> cns_prod_loc_attrib-localMaterialNumber, then reject the record.
      | CONS_LATAM   | 000000000000000731  | BR19       | 9862           | G3a                | G4a  | X      | X          | X          | X            |                |

      # when ns_materilal_plan_status-localPlant <> cns_prod_loc_attrib-localPlant, then reject the record.
      | CONS_LATAM   | 000000000007709000  | BR79       | 9862           | G3a                | G4a  | X      | X          | X          | X            |                |

      # when material_global_v1-primaryPlanningCode and material_global_v1-materialNumber are not available, then reject the record
      | CONS_LATAM   | 000000000000004000  | BR07       | 4000           | 178910100400070000 | 4000 | X      | X          | X          | X            |                |

    And I wait "/plan/cns_material_plan_status" Async Queue complete

    Given I import "/plan/cns_prod_loc_attrib" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | schdAttrbName1 | schAttrbDesc1 | schdAttrbName2 | schAttrbDesc2 | schdAttrbName3 | schAttrbDesc3 |
      | CONS_LATAM   | 000000000000000945  | BR19       | ATTRB1         | VALUE1        | ATTRB2         | VALUE2        |                |               |
      | CONS_LATAM   | 000000000007709000  | EC01       | ATTRB1         | VALUE1        | ATTRB2         | VALUE2        |                |               |
      | CONS_LATAM   | 000000000000004000  | BR07       | ATTRB1         | VALUE1        | ATTRB2         | VALUE2        |                |               |

    And I wait "/plan/cns_prod_loc_attrib" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | sourceSystem | localMaterialNumber | materialNumber | primaryPlanningCode |
      | CONS_LATAM   | 000000000000000945  | 9862           | EM9999              |
      | CONS_LATAM   | 000000000007709000  | 9862           |                     |
      | CONS_LATAM   | 000000000000004000  |                |                     |

    And I wait "/edm/material_global_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmProductLocationDetail.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMProductLocationDetail.tsv"

    And I check file data for filename "GDMProductLocationDetail.tsv" by keyFields "productLocationDetailId"
      | unit | comments | productLocationDetailId           | productLocationId      | name   | description | CLASS | value  | activeOPRERP | activeSOPERP |
      |      |          | EM9999/CONS_LATAM_BR19/PGA/ATTRB1 | EM9999/CONS_LATAM_BR19 | ATTRB1 | Pangea      | PGA   | VALUE1 | YES          | NO           |
      |      |          | EM9999/CONS_LATAM_BR19/PGA/ATTRB2 | EM9999/CONS_LATAM_BR19 | ATTRB2 | Pangea      | PGA   | VALUE2 | YES          | NO           |
      |      |          | 9862/CONS_LATAM_EC01/PGA/ATTRB1   | 9862/CONS_LATAM_EC01   | ATTRB1 | Pangea      | PGA   | VALUE1 | YES          | NO           |
      |      |          | 9862/CONS_LATAM_EC01/PGA/ATTRB2   | 9862/CONS_LATAM_EC01   | ATTRB2 | Pangea      | PGA   | VALUE2 | YES          | NO           |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID                 | errorCode | sourceSystem | businessArea | key1       | key2               | key3 | key4 | key5 | errorValue                                                  |
      | SP             | OMPGdmProductLocationDetail | N2        | omp          |              | CONS_LATAM | 000000000000000999 | BR19 |      |      | Not Planning Relevant                                       |
      | SP             | OMPGdmProductLocationDetail | N2        | omp          |              | TOSC_ADCGA | 000000000000000945 | BR19 |      |      | Not Planning Relevant                                       |
      | SP             | OMPGdmProductLocationDetail | N2        | omp          |              | CONS_LATAM | 000000000000000731 | BR19 |      |      | Not Planning Relevant                                       |
      | SP             | OMPGdmProductLocationDetail | N2        | omp          |              | CONS_LATAM | 000000000007709000 | BR79 |      |      | Not Planning Relevant                                       |
      | SP             | OMPGdmProductLocationDetail | E2        | omp          |              | CONS_LATAM | 000000000000004000 | BR07 |      |      | Missing Primary Plannig Code and Enterprise Material Number |

    Then I delete the test data

    And I will remove all data with region "/omp/gdm_product_location_detail"

    And I will remove all data with region "/plan/edm_failed_data"
