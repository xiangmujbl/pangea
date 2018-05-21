@pangea_test @AEAZ-1815
Feature: OMPGdmProductUnitConversion AEAZ-1815

  @Scenario1
  Scenario: J1

    And I will remove the test file on sink application "GDMProductUnitConversion.tsv"

    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber"
      | localMaterialNumber | materialNumber | localDpParentCode  | localBaseUom | primaryPlanningCode |
      | 000000000000000016  | 16             |                    | KI           | 16                  |
      | 000000000000000945  | 945            | 178962124094540036 | KI           | 945                 |

    And I wait "/edm/material_global_v1" Async Queue complete

    #material_global_V1-materialNumber = cns_material_plan_status-materialNumber
    Given I import "/plan/cns_material_plan_status" by keyFields "localMaterialNumber,localPlant,sourceSystem"
      | localMaterialNumber | localPlant | sourceSystem | materialNumber | active | dpRelevant | spRelevant |
      | 000000000000000945  | BR07       | CONS_LATAM   | 945            | X      | X          | X          |
      | 000000000000000945  | BR16       | CONS_LATAM   | 945            | X      | X          | X          |
      | 000000000000000945  | BR19       | CONS_LATAM   | 945            | X      | X          | X          |
      | 000000000000000945  | BR25       | CONS_LATAM   | 945            | X      | X          | X          |

    And I wait "/plan/cns_material_plan_status" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmProductUnitConversion.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMProductUnitConversion.tsv"

    Then I check file data for filename "GDMProductUnitConversion.tsv" by keyFields "gdmProductUnitConversionId"
#    Then I check region data "/omp/gdm_product_unit_conversion" by keyFields "gdmProductUnitConversionId"
      | gdmProductUnitConversionId | productId          | unitId | active | activeFCTERP | activeOPRERP | activeSOPERP | factor | comments |
      | 945                        | 945                |        |        |              |              | NO           |        |          |
      | 178962124094540036         | 178962124094540036 |        |        |              |              | NO           |        |          |

    And I delete the test data
    And I will remove all data with region "/omp/gdm_product_unit_conversion"
    And I will remove all data with region "/plan/edm_failed_data"

  @Scenario2
  Scenario: J2

    And I will remove the test file on sink application "GDMProductUnitConversion.tsv"

    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber"
      | localMaterialNumber | materialNumber | localDpParentCode | localBaseUom | primaryPlanningCode |
      | 000000000000055735  | 55735          |                   | EA           | 55735               |
      | 000000000000058723  | 58723          |                   | EA           | 58723               |
      | 000000000000058725  | 58725          |                   | EA           | 58725               |
    And I wait "/edm/material_global_v1" Async Queue complete

    #material_global_V1-materialNumber = cns_material_plan_status-materialNumber
    Given I import "/plan/cns_material_plan_status" by keyFields "localMaterialNumber,localPlant,sourceSystem"
      | localMaterialNumber | localPlant | sourceSystem | materialNumber | active | dpRelevant | spRelevant |
      | 000000000000055735  | PE01       | CONS_LATAM   | 55735          | X      | X          | X          |
      | 000000000000058723  | CR01       | CONS_LATAM   | 58723          | X      | X          | X          |
      | 000000000000058725  | CR01       | CONS_LATAM   | 58725          | X      | X          | X          |

    And I wait "/plan/cns_material_plan_status" Async Queue complete

    ##material_global_V1-localMaterialNumber = material_auom_v1-localMaterialNumber
    Given I import "/edm/material_auom_v1" by keyFields "localMaterialNumber,localAuom"
      | localMaterialNumber | localDenominator | localAuom          | localNumerator | materialNumber |
      | 000000000000055735  | 10               | 000000000000055735 | 1              | 55735          |
      | 000000000000058723  | 1                | 1                  | 12             | 58723          |
      | 000000000000058724  | 1                | 1                  | 12             | 58724          |

    And I wait "/edm/material_auom_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmProductUnitConversion.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMProductUnitConversion.tsv"

    Then I check file data for filename "GDMProductUnitConversion.tsv" by keyFields "gdmProductUnitConversionId"
#    Then I check region data "/omp/gdm_product_unit_conversion" by keyFields "gdmProductUnitConversionId"
      | gdmProductUnitConversionId | productId | unitId | active | activeFCTERP | activeOPRERP | activeSOPERP | factor | comments |
      | 55735                      | 55735     |        |        |              |              | NO           | 0.10   |          |
      | 58723                      | 58723     |        |        |              |              | NO           |        |          |
      | 58725                      | 58725     |        |        |              |              | NO           |        |          |

    And I delete the test data
    And I will remove all data with region "/omp/gdm_product_unit_conversion"
    And I will remove all data with region "/plan/edm_failed_data"

  @Scenario3
  Scenario: T1

    And I will remove the test file on sink application "GDMProductUnitConversion.tsv"

    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber"
      | localMaterialNumber | materialNumber | localDpParentCode  | localBaseUom | primaryPlanningCode |
      | 000000000000004000  | 4000           | 178962124094540036 | KI           | 4000                |
      | 000000000000004001  | 4001           | 178962124094540037 | KI           | 4001                |
      | 000000000000004002  | 4002           |                    | KI           | 4002                |
      | 000000000000004003  | 4003           |                    | KI           | 4003                |
      | 000000000000058722  | 58722          | 178962124094540036 | EA           | 58722               |

    And I wait "/edm/material_global_v1" Async Queue complete

    #material_global_V1-materialNumber = cns_material_plan_status-materialNumber
    Given I import "/plan/cns_material_plan_status" by keyFields "localMaterialNumber,localPlant,sourceSystem"
      | localMaterialNumber | localPlant | sourceSystem | materialNumber | active | dpRelevant | spRelevant |
      | 000000000000004000  | BR07       | CONS_LATAM   | 4000           | X      | X          | X          |
      | 000000000000004001  | BR07       | CONS_LATAM   | 4001           |        | X          | X          |
      | 000000000000004002  | BR07       | CONS_LATAM   | 4002           | X      | X          | X          |
      | 000000000000004003  | BR07       | CONS_LATAM   | 4003           |        | X          | X          |
      | 000000000000058722  | BR07       | CONS_LATAM   | 58722          |        |            |            |
      | 000000000000058722  | CO01       | CONS_LATAM   | 58722          | X      |            |            |

    And I wait "/plan/cns_material_plan_status" Async Queue complete


    When I submit task with xml file "xml/omp/OMPGdmProductUnitConversion.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMProductUnitConversion.tsv"

    Then I check file data for filename "GDMProductUnitConversion.tsv" by keyFields "gdmProductUnitConversionId"
#    Then I check region data "/omp/gdm_product_unit_conversion" by keyFields "gdmProductUnitConversionId"
      | gdmProductUnitConversionId | productId          | unitId | active | activeFCTERP | activeOPRERP | activeSOPERP | factor | comments |
      | 4000                       | 4000               |        |        |              |              | NO           |        |          |
      | 178962124094540036         | 178962124094540036 |        |        |              |              | NO           |        |          |
      | 4002                       | 4002               |        |        |              |              | NO           |        |          |
      | 58722                      | 58722              |        |        |              |              | NO           |        |          |

    And I delete the test data
    And I will remove all data with region "/omp/gdm_product_unit_conversion"
    And I will remove all data with region "/plan/edm_failed_data"

  @Scenario4
  Scenario: T2

    And I will remove the test file on sink application "GDMProductUnitConversion.tsv"

    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber"
      | localMaterialNumber | materialNumber | localDpParentCode  | localBaseUom | primaryPlanningCode |
      | 000000000000002885  | 2885           | 178962124288680036 | EA           | 2885                |
      | 000000000000002886  | 2886           | 178962124288680037 | EA           | 2886                |
      | 000000000000002887  | 2887           | 178962124288680038 | EA           | 2887                |
      | 000000000000002888  | 2888           | 178962124288680039 | EA           | 2888                |
      | 000000000000002889  | 2889           | 178962124288680040 | EA           | 2889                |
      | 000000000000002890  | 2890           |                    | EA           | 2890                |
      | 000000000000002891  | 2891           |                    | EA           | 2891                |
      | 000000000000002892  | 2892           |                    | EA           | 2892                |
      | 000000000000002893  | 2893           |                    | EA           | 2893                |
      | 000000000000002894  | 2894           |                    | EA           | 2894                |

    And I wait "/edm/material_global_v1" Async Queue complete

    #material_global_V1-materialNumber = cns_material_plan_status-materialNumber
    Given I import "/plan/cns_material_plan_status" by keyFields "localMaterialNumber,localPlant,sourceSystem"
      | localMaterialNumber | localPlant | sourceSystem | materialNumber | active | dpRelevant | spRelevant |
      | 000000000000002885  | CR01       | CONS_LATAM   | 2885           | X      | X          | X          |
      | 000000000000002886  | CR01       | CONS_LATAM   | 2886           | X      | X          | X          |
      | 000000000000002887  | CR01       | CONS_LATAM   | 2887           | X      | X          | X          |
      | 000000000000002888  | CR01       | CONS_LATAM   | 2888           | X      | X          | X          |
      | 000000000000002889  | CR01       | CONS_LATAM   | 2889           | X      | X          | X          |
      | 000000000000002890  | CR01       | CONS_LATAM   | 2890           | X      | X          | X          |
      | 000000000000002891  | CR01       | CONS_LATAM   | 2891           | X      | X          | X          |
      | 000000000000002892  | CR01       | CONS_LATAM   | 2892           | X      | X          | X          |
      | 000000000000002893  | CR01       | CONS_LATAM   | 2893           | X      | X          | X          |
      | 000000000000002894  | CR01       | CONS_LATAM   | 2894           | X      |            | X          |


    And I wait "/plan/cns_material_plan_status" Async Queue complete

    #material_global_V1-materialNumber = material_auom_v1-materialNumber
    Given I import "/edm/material_auom_v1" by keyFields "localMaterialNumber,localAuom"
      | localMaterialNumber | localDenominator | localAuom | localNumerator | materialNumber |
      | 000000000000002886  | 7                | 1         | 14             | 2886           |
      | 000000000000002887  | 5                | 2         | 1              | 2887           |
      | 000000000000002888  | 5                | 3         | 1              | 2888           |
      | 000000000000002889  | 5                | 4         | 1              | 2889           |
      | 000000000000002890  | 5                | 1         | 1              | 2890           |
      | 000000000000002891  | 5                | 2         | 1              | 2891           |
      | 000000000000002892  | 5                | 3         | 1              | 2892           |
      | 000000000000002893  | 5                | 4         | 1              | 2893           |
      | 000000000000002894  | 5                | 1         | 1              | 2894           |

    And I wait "/edm/material_auom_v1" Async Queue complete

    Given I import "/plan/cns_plan_unit" by keyFields "planFlag,unit,localUom,sourceSystem,localUomName"
      | planFlag | unit  | localUom | sourceSystem | localUomName |
      | DPSP     | DPSP  | 1        | CRT          | CONS_LATAM   |
      | DP       | DPSP1 | 2        | EA           | CONS_LATAM   |
      | SP       | DPSP2 | 4        | EA           | CONS_LATAM   |

    And I wait "/plan/cns_plan_unit" Async Queue complete


    When I submit task with xml file "xml/omp/OMPGdmProductUnitConversion.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMProductUnitConversion.tsv"

    Then I check file data for filename "GDMProductUnitConversion.tsv" by keyFields "gdmProductUnitConversionId"
#    Then I check region data "/omp/gdm_product_unit_conversion" by keyFields "gdmProductUnitConversionId"
      | gdmProductUnitConversionId | productId          | unitId | active | activeFCTERP | activeOPRERP | activeSOPERP | factor | comments |
      | 2885                       | 2885               |        |        |              |              | NO           |        |          |
      | 178962124288680036         | 178962124288680036 |        |        |              |              | NO           |        |          |
      | 2886DPSP                   | 2886               | DPSP   | YES    | YES          | YES          | NO           |        |          |
      | 178962124288680037DPSP     | 178962124288680037 | DPSP   | YES    | YES          | YES          | NO           |        |          |
      | 2887DPSP1                  | 2887               | DPSP1  | YES    | YES          | YES          | NO           |        |          |
      | 178962124288680038DPSP1    | 178962124288680038 | DPSP1  | YES    | YES          | YES          | NO           |        |          |
      | 2888                       | 2888               |        |        |              |              | NO           |        |          |
      | 178962124288680039         | 178962124288680039 |        |        |              |              | NO           |        |          |
      | 2889DPSP2                  | 2889               | DPSP2  |        |              |              | NO           |        |          |
      | 178962124288680040DPSP2    | 178962124288680040 | DPSP2  |        |              |              | NO           |        |          |
      | 2890DPSP                   | 2890               | DPSP   | YES    | YES          | YES          | NO           |        |          |
      | 2891DPSP1                  | 2891               | DPSP1  | YES    | YES          | YES          | NO           |        |          |
      | 2892                       | 2892               |        |        |              |              | NO           |        |          |
      | 2893DPSP2                  | 2893               | DPSP2  |        |              |              | NO           |        |          |
      | 2894                       | 2894               |        | YES    |              | YES          | NO           |        |          |

    Then I check region data "/plan/edm_failed_data" by keyFields "errorCode,functionalArea,interfaceID,key1,key2,key3,key4,key5,sourceSystem"
      | errorCode | errorValue                      | functionalArea | interfaceID                 | key1               | key2 | key3 | key4 | key5 | sourceSystem |
      | E1        | No Enterprise UOM is maintained | SP             | OMPGdmProductUnitConversion | 000000000000002888 |      |      |      |      | omp          |
      | E1        | No Enterprise UOM is maintained | SP             | OMPGdmProductUnitConversion | 000000000000002892 |      |      |      |      | omp          |

    And I delete the test data
    And I will remove all data with region "/omp/gdm_product_unit_conversion"
    And I will remove all data with region "/plan/edm_failed_data"

  @Scenario5
  Scenario: T3

    And I will remove the test file on sink application "GDMProductUnitConversion.tsv"

    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber"
      | localMaterialNumber | materialNumber | localDpParentCode | localBaseUom | primaryPlanningCode |
      | 000000000000003880  | 3880           |                   | EA           | 3880                |
      | 000000000000003881  | 3881           |                   | EA           | 3881                |

    And I wait "/edm/material_global_v1" Async Queue complete

    #material_global_V1-materialNumber = cns_material_plan_status-materialNumber
    Given I import "/plan/cns_material_plan_status" by keyFields "localMaterialNumber,localPlant,sourceSystem"
      | localMaterialNumber | localPlant | sourceSystem | materialNumber | active | dpRelevant | spRelevant |
      | 000000000000003880  | CR01       | CONS_LATAM   | 3880           | X      | X          | X          |
      | 000000000000003881  | CR01       | CONS_LATAM   | 3881           | X      | X          |            |


    And I wait "/plan/cns_material_plan_status" Async Queue complete
    #material_global_V1-materialNumber = material_auom_v1-materialNumber
    Given I import "/edm/material_auom_v1" by keyFields "localMaterialNumber,localAuom"
      | localMaterialNumber | localDenominator | localAuom | localNumerator | materialNumber |
      | 000000000000003880  | 5                | 1         | 1              | 3880           |
      | 000000000000003881  | 5                | 1         | 1              | 3881           |

    And I wait "/edm/material_auom_v1" Async Queue complete

    Given I import "/plan/cns_plan_unit" by keyFields "planFlag,unit,localUom,sourceSystem,localUomName"
      | planFlag | unit | localUom | sourceSystem | localUomName |
      | DPSP     | DPSP | 1        | CRT          | CONS_LATAM   |

    And I wait "/plan/cns_plan_unit" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmProductUnitConversion.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMProductUnitConversion.tsv"

    Then I check file data for filename "GDMProductUnitConversion.tsv" by keyFields "gdmProductUnitConversionId"
#    Then I check region data "/omp/gdm_product_unit_conversion" by keyFields "gdmProductUnitConversionId"
      | gdmProductUnitConversionId | productId | unitId | active | activeFCTERP | activeOPRERP | activeSOPERP | factor | comments |
      | 3880DPSP                   | 3880      | DPSP   | YES    | YES          | YES          | NO           |        |          |
      | 3881DPSP                   | 3881      | DPSP   | YES    | YES          |              | NO           |        |          |

    And I delete the test data
    And I will remove all data with region "/omp/gdm_product_unit_conversion"
    And I will remove all data with region "/plan/edm_failed_data"

