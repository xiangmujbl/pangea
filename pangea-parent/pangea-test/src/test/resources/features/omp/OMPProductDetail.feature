@pangea_test @AEAZ-7162
Feature:  OMPProductDetail AEAZ-7162

  @Scenario1
  Scenario: Adding 1-3 rows for each records(rule J1,T1)

    And I will remove the test file on sink application "GDMProductDetail.tsv"

    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber,sourceSystem"
      | localMaterialNumber | primaryPlanningCode | localDpParentCode | sourceSystem | localManufacturingTechnology | materialNumber |
      # T1 - 3 Records
      | 000000000000098825  | 98825               | 77020319882500012 | CONS_LATAM   | Listerne                     | 98825          |
      # T1 - 3 Records, Remove Leading zeroes
      | 000000000000441547  | 6007700             | 78910102446440006 | CONS_LATAM   |                              | 6007700        |
      # J1 Skip
      | 000000000000000016  | 16                  |                   | CONS_LATAM   |                              | 16             |
      # T1 - 1 Record
      | 000000000000036001  | 36001               |                   | CONS_LATAM   |                              | 36001          |
      # T1 - 3 Records, Alpha Numeric
      | 0000000000-8441547  | 6007700             | 78910102446440006 | CONS_LATAM   |                              | 6007700        |
      # T1 - 3 Records,  Alpha Numeric
      | A98825              | 98825               | 77020319882500012 | CONS_LATAM   | Listerne                     | 98825          |
      # T1 - 3 Records
      | 000000000000198825  | 98825               | 77020319882500012 | CONS_LATAM   | Listerne                     | 98825          |
      # J1 - Reject
      | 000000000000294629  |                     | 77020319882500012 | CONS_LATAM   | Listerne                     | 94629          |

    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/plan/cns_material_plan_status" by keyFields "localMaterialNumber,localPlant,sourceSystem"
      | active | dpRelevant | localMaterialNumber | localParentCode   | localPlant | materialNumber | noPlanRelevant | parentActive | ppc     | sourceSystem | spRelevant |
      # T1 - 3 Records
      | X      | X          | 000000000000098825  | 77020319882500012 | BR16       | 98825          |                | X            | 98825   | CONS_LATAM   | X          |
      | X      | X          | 000000000000098825  | 77020319882500012 | BR19       | 98825          |                | X            | 98825   | CONS_LATAM   | X          |
      | X      | X          | 000000000000098825  | 77020319882500012 | BR25       | 98825          |                | X            | 98825   | CONS_LATAM   | X          |
      | X      |            | 000000000000098825  | 77020319882500012 | BR12       | 98825          |                | X            | 98825   | CONS_LATAM   | X          |
      | X      | X          | 000000000000098825  | 77020319882500012 | BR07       | 98825          |                | X            | 98825   | CONS_LATAM   | X          |
      # T1 - 3 Records, Remove Leading zeroes
      | X      | X          | 000000000000441547  | 78910102446440006 | BR19       | 6007700        |                | X            | 6007700 | CONS_LATAM   | X          |
      | X      | X          | 000000000000441547  | 78910102446440006 | BR25       | 6007700        |                | X            | 6007700 | CONS_LATAM   | X          |
      | X      | X          | 000000000000441547  | 78910102446440006 | BR07       | 6007700        |                | X            | 6007700 | CONS_LATAM   | X          |
      | X      | X          | 000000000000441547  | 78910102446440006 | BR16       | 6007700        |                | X            | 6007700 | CONS_LATAM   | X          |
      | X      |            | 000000000000441547  | 78910102446440006 | BR12       | 6007700        |                | X            | 6007700 | CONS_LATAM   | X          |
      # J1 Skip
      |        |            | 000000000000000016  |                   | BR07       | 16             |                |              | 16      | CONS_LATAM   |            |
      # T1 - 1 Record
      | X      |            | 000000000000036001  |                   | BR12       | 36001          |                |              | 36001   | CONS_LATAM   | X          |
      # T1 - 3 Records, Alpha Numeric
      | X      | X          | 0000000000-8441547  | 78910102446440006 | BR19       | 6007700        |                | X            | 6007700 | CONS_LATAM   | X          |
      | X      | X          | 0000000000-8441547  | 78910102446440006 | BR25       | 6007700        |                | X            | 6007700 | CONS_LATAM   | X          |
      | X      | X          | 0000000000-8441547  | 78910102446440006 | BR07       | 6007700        |                | X            | 6007700 | CONS_LATAM   | X          |
      | X      | X          | 0000000000-8441547  | 78910102446440006 | BR16       | 6007700        |                | X            | 6007700 | CONS_LATAM   | X          |
      | X      |            | 0000000000-8441547  | 78910102446440006 | BR12       | 6007700        |                | X            | 6007700 | CONS_LATAM   | X          |
      # T1 - 3 Records,  Alpha Numeric
      | X      | X          | A98825              | 77020319882500012 | BR16       | 98825          |                | X            | 98825   | CONS_LATAM   | X          |
      | X      | X          | A98825              | 77020319882500012 | BR19       | 98825          |                | X            | 98825   | CONS_LATAM   | X          |
      | X      | X          | A98825              | 77020319882500012 | BR25       | 98825          |                | X            | 98825   | CONS_LATAM   | X          |
      | X      |            | A98825              | 77020319882500012 | BR12       | 98825          |                | X            | 98825   | CONS_LATAM   | X          |
      | X      | X          | A98825              | 77020319882500012 | BR07       | 98825          |                | X            | 98825   | CONS_LATAM   | X          |
      # T1 - 3 Records
      | X      | X          | 000000000000198825  | 77020319882500012 | BR16       | 98825          |                | X            | 98825   | CONS_LATAM   |            |
      | X      | X          | 000000000000198825  | 77020319882500012 | BR19       | 98825          |                | X            | 98825   | CONS_LATAM   |            |
      | X      | X          | 000000000000198825  | 77020319882500012 | BR25       | 98825          |                | X            | 98825   | CONS_LATAM   |            |
      | X      |            | 000000000000198825  | 77020319882500012 | BR12       | 98825          |                | X            | 98825   | CONS_LATAM   |            |
      | X      | X          | 000000000000198825  | 77020319882500012 | BR07       | 98825          |                | X            | 98825   | CONS_LATAM   |            |
      # J1 - Reject
      | X      | X          | 000000000000294629  | 77020319882500012 | BR16       | 94629          |                | X            |         | CONS_LATAM   |            |

    And I wait "/plan/cns_material_plan_status" Async Queue complete

    Given I import "/plan/cns_plan_parameter" by keyFields "parameterValue,attribute,dataObject"
      | parameterValue | attribute  | dataObject  |
      | LA             | CONS_LATAM | SEND_TO_OMP |

    And I wait "/plan/cns_plan_parameter" Async Queue complete

    When I submit task with xml file "xml/omp/OMPProductDetail.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMProductDetail.tsv"

    Then I check file data for filename "GDMProductDetail.tsv" by keyFields "productDetailId"
      | productDetailId                                       | activeFCTERP | activeOPRERP | activeSOPERP | CLASS | comments | description | name       | productId            | unit | value                |
      # T1 - 3 Records, Alpha Numeric
      | 6007700/PGA/LATAM_ROOT/LA_78910102446440006           | YES          | NO           | NO           | PGA   |          | Pangea      | LATAM_ROOT | 6007700              |      | LA_78910102446440006 |
      | 6007700/PGA/LATAM_SKU/0000000000-8441547              | YES          | YES          | NO           | PGA   |          | Pangea      | LATAM_SKU  | 6007700              |      | 0000000000-8441547   |
      | LA_78910102446440006/PGA/LATAM_SKU/0000000000-8441547 | YES          | NO           | NO           | PGA   |          | Pangea      | LATAM_SKU  | LA_78910102446440006 |      | 0000000000-8441547   |
      # J1 Skip
      | 36001/PGA/LATAM_SKU/36001                             | NO           | YES          | NO           | PGA   |          | Pangea      | LATAM_SKU  | 36001                |      | 36001                |
      # T1 - 3 Records
      | 98825/PGA/LATAM_SKU/198825                            | YES          | NO           | NO           | PGA   |          | Pangea      | LATAM_SKU  | 98825                |      | 198825               |
      | 98825/PGA/LATAM_ROOT/LA_77020319882500012             | YES          | NO           | NO           | PGA   |          | Pangea      | LATAM_ROOT | 98825                |      | LA_77020319882500012 |
      | LA_77020319882500012/PGA/LATAM_SKU/198825             | YES          | NO           | NO           | PGA   |          | Pangea      | LATAM_SKU  | LA_77020319882500012 |      | 198825               |
      # T1 - 3 Records
      | 98825/PGA/LATAM_SKU/98825                             | YES          | YES          | NO           | PGA   |          | Pangea      | LATAM_SKU  | 98825                |      | 98825                |
      | 98825/PGA/LATAM_ROOT/LA_77020319882500012             | YES          | NO           | NO           | PGA   |          | Pangea      | LATAM_ROOT | 98825                |      | LA_77020319882500012 |
      | LA_77020319882500012/PGA/LATAM_SKU/98825              | YES          | NO           | NO           | PGA   |          | Pangea      | LATAM_SKU  | LA_77020319882500012 |      | 98825                |
      # T1 - 3 Records,  Alpha Numeric
      | 98825/PGA/LATAM_SKU/A98825                            | YES          | YES          | NO           | PGA   |          | Pangea      | LATAM_SKU  | 98825                |      | A98825               |
      | 98825/PGA/LATAM_ROOT/LA_77020319882500012             | YES          | NO           | NO           | PGA   |          | Pangea      | LATAM_ROOT | 98825                |      | LA_77020319882500012 |
      | LA_77020319882500012/PGA/LATAM_SKU/A98825             | YES          | NO           | NO           | PGA   |          | Pangea      | LATAM_SKU  | LA_77020319882500012 |      | A98825               |
      # T1 - 3 Records, Remove Leading zeroes
      | 6007700/PGA/LATAM_ROOT/LA_78910102446440006           | YES          | NO           | NO           | PGA   |          | Pangea      | LATAM_ROOT | 6007700              |      | LA_78910102446440006 |
      | 6007700/PGA/LATAM_SKU/441547                          | YES          | YES          | NO           | PGA   |          | Pangea      | LATAM_SKU  | 6007700              |      | 441547               |
      | LA_78910102446440006/PGA/LATAM_SKU/441547             | YES          | NO           | NO           | PGA   |          | Pangea      | LATAM_SKU  | LA_78910102446440006 |      | 441547               |

    Then I check region data "/dev/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID         | errorCode | sourceSystem | businessArea | key1       | key2               | key3 | key4 | key5 | errorValue                                                 |
      | DP             | OMPGdmProductDetail | J1        | CONS_LATAM   |              | CONS_LATAM | 000000000000294629 |      |      |      | PPC does not exist for localMaterialNumber in edm material |

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/omp/product_detail"

    And I will remove all data with region "/dev/plan/edm_failed_data"

