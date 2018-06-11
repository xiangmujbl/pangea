@pangea_test @AEAZ-2713 @AEAZ-4064
Feature:  OMPProductDetail AEAZ-2713 AEAZ-4064

  @Scenario1
  Scenario: Adding 1-5 rows for each records(rule J1,T1)

    And I will remove the test file on sink application "GDMProductDetail.tsv"

    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber,sourceSystem"
      | localMaterialNumber | primaryPlanningCode | localDpParentCode | sourceSystem | localManufacturingTechnology |

      # In rule T1, because 1-5 are satisfied, so five rows will be generated.
      | 000000000000056685  | 56685               | 78910105668520000 | CONS_LATAM   | Lubriderm                    |

      # In rule T1, because 1 and 5 are satisfied, so two rows will be generated.
      | 000000000000097041  | 97041               | 78910108045890000 | CONS_LATAM   | Carefree COL                 |

      # In rule T1, because 1-4 are satisfied, so four rows will be generated.
      | 000000000000098957  | 98957               | 77020315981450000 | CONS_LATAM   |                              |

      # In rule T1, because 1 are satisfied, so one rows will be generated.
      | 000000000005300198  | 5300198             | 78910108779340000 | CONS_LATAM   |                              |

    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/plan/cns_material_plan_status" by keyFields "localMaterialNumber"
      | localMaterialNumber | spRelevant | dpRelevant | noPlanRelevant | sourceSystem |
      | 000000000000056685  | X          | X          |                | CONS_LATAM   |
      | 000000000000097041  | X          |            | X              | CONS_LATAM   |
      | 000000000000098957  | X          | X          |                | CONS_LATAM   |
      | 000000000005300198  | X          |            |                | CONS_LATAM   |

    And I wait "/plan/cns_material_plan_status" Async Queue complete

    Given I import "/plan/cns_plan_parameter" by keyFields "parameterValue,attribute,dataObject"
      | parameterValue | attribute  | dataObject  |
      | LA             | CONS_LATAM | SEND_TO_OMP |

    And I wait "/plan/cns_plan_parameter" Async Queue complete

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | sourceSystem |
      | Project_One       | CONS_LATAM   |

    And I wait "/edm/source_system_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPProductDetail.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMProductDetail.tsv"

    Then I check file data for filename "GDMProductDetail.tsv" by keyFields "productDetailId"
      | productDetailId                             | activeFCTERP | activeOPRERP | activeSOPERP | CLASS | comments | description | name       | productId            | unit | value                |
      | 56685/PGA/LATAM_ROOT                        | YES          | YES          | NO           | PGA   |          | Pangea      | LATAM_ROOT | 56685                |      | LA_78910105668520000 |
      | 56685/PGA/LATAM_SKU                         | NO           | YES          | NO           | PGA   |          | Pangea      | LATAM_SKU  | 56685                |      | 000000000000056685   |
      | 56685/PGA/LATAM_TECH                        | NO           | YES          | NO           | PGA   |          | Pangea      | LATAM_TECH | 56685                |      | Lubriderm            |
      | CONS_LATAM_78910105668520000/PGA/LATAM_ROOT | YES          | YES          | NO           | PGA   |          | Pangea      | LATAM_ROOT | LA_78910105668520000 |      | LA_78910105668520000 |
      | CONS_LATAM_78910105668520000/PGA/LATAM_SKU  | YES          | YES          | NO           | PGA   |          | Pangea      | LATAM_SKU  | LA_78910105668520000 |      | 000000000000056685   |
      | 97041/PGA/LATAM_SKU                         | NO           | YES          | NO           | PGA   |          | Pangea      | LATAM_SKU  | 97041                |      | 000000000000097041   |
      | 97041/PGA/LATAM_TECH                        | NO           | YES          | NO           | PGA   |          | Pangea      | LATAM_TECH | 97041                |      | Carefree COL         |
      | 98957/PGA/LATAM_ROOT                        | YES          | YES          | NO           | PGA   |          | Pangea      | LATAM_ROOT | 98957                |      | LA_77020315981450000 |
      | 98957/PGA/LATAM_SKU                         | NO           | YES          | NO           | PGA   |          | Pangea      | LATAM_SKU  | 98957                |      | 000000000000098957   |
      | CONS_LATAM_77020315981450000/PGA/LATAM_ROOT | YES          | YES          | NO           | PGA   |          | Pangea      | LATAM_ROOT | LA_77020315981450000 |      | LA_77020315981450000 |
      | CONS_LATAM_77020315981450000/PGA/LATAM_SKU  | YES          | YES          | NO           | PGA   |          | Pangea      | LATAM_SKU  | LA_77020315981450000 |      | 000000000000098957   |
      | 5300198/PGA/LATAM_SKU                       | NO           | YES          | NO           | PGA   |          | Pangea      | LATAM_SKU  | 5300198              |      | 000000000005300198   |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/omp/product_detail"

    And I will remove all data with region "/plan/edm_failed_data"

