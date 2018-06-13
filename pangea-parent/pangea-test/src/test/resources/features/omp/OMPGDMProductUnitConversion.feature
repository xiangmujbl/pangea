@pangea_test @AEAZ-1815 @AEAZ-4071
Feature: OMPGdmProductUnitConversion AEAZ-4071

  @Scenario1
  Scenario: full load consumption

    And I will remove the test file on sink application "GDMProductUnitConversion.tsv"

    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber,sourceSystem"
      | localMaterialNumber | materialNumber | localDpParentCode  | localBaseUom | primaryPlanningCode | sourceSystem |
      | 000000000000000016  | 16             |                    | KI           | 16                  | CONS_LATAM   |
      | 000000000000000017  | 16             |                    | KI           | 17                  | CONS_LATAM1  |
      | 000000000000000945  | 945            | 178962124094540035 | EA           | 945                 | CONS_LATAM   |
      | 000000000000055735  | 55735          |                    | BX           | 55735               | CONS_LATAM   |
      | 000000000000058723  | 58723          |                    | EA           | 58723               | CONS_LATAM   |
      | 000000000000058725  | 58725          |                    | EA           | 58725               | CONS_LATAM   |
      | 000000000000004000  | 4000           | 178962124094540036 | EA           | 4000                | CONS_LATAM   |
      | 000000000000004001  | 4001           | 178962124094540037 | EA           | 4001                | CONS_LATAM   |
      | 000000000000004002  | 4002           |                    | EA           | 4002                | CONS_LATAM   |
      | 000000000000004003  | 4003           |                    | EA           | 4003                | CONS_LATAM   |
      | 000000000000058722  | 58722          | 178962124094540038 | EA           | 58722               | CONS_LATAM   |
      | 000000000000002885  | 2885           | 178962124288680036 | EA           | 2885                | CONS_LATAM   |
      | 000000000000002886  | 2886           | 178962124288680037 | EA           | 2886                | CONS_LATAM   |
      | 000000000000002887  | 2887           | 178962124288680038 | CA           | 2887                | CONS_LATAM   |
      | 000000000000002888  | 2888           | 178962124288680039 | BI           | 2888                | CONS_LATAM   |
      | 000000000000002889  | 2889           | 178962124288680040 | KI           | 2889                | CONS_LATAM   |
      | 000000000000002890  | 2890           |                    | EA           | 2890                | CONS_LATAM   |
      | 000000000000002891  | 2891           |                    | CA           | 2891                | CONS_LATAM   |
      | 000000000000002892  | 2892           |                    | BI           | 2892                | CONS_LATAM   |
      | 000000000000002893  | 2893           |                    | KI           | 2893                | CONS_LATAM   |
      | 000000000000002894  | 2894           |                    | EA           | 2894                | CONS_LATAM   |
      | 000000000000003880  | 3880           |                    | EA           | 3880                | CONS_LATAM   |
      | 000000000000003881  | 3881           |                    | EA           | 3881                | CONS_LATAM   |
      | 000000000000004880  | 4880           |                    | EA           | 4880                | CONS_LATAM   |
      | 000000000000004881  | 4881           |                    | EA           | 4881                | CONS_LATAM1  |
      | 000000000000004882  | 4882           |                    | EA           | 4882                | CONS_LATAM   |
      | 000000000000004883  | 4883           |                    | CA           | 4883                | CONS_LATAM   |
      | 000000000000006881  | 6881           |                    | KK           | 6881                | CONS_LATAM   |
    And I wait "/edm/material_global_v1" Async Queue complete

    #material_global_V1-materialNumber = cns_material_plan_status-materialNumber
    And I import "/plan/cns_material_plan_status" by keyFields "localMaterialNumber,localPlant,sourceSystem"
      | localMaterialNumber | localPlant | sourceSystem | materialNumber | active | dpRelevant | spRelevant | noPlanRelevant |
      | 000000000000000017  | BR07       | CONS_LATAM   | 17             | X      | X          | X          | X              |
      | 000000000000000945  | BR07       | CONS_LATAM   | 945            | X      | X          | X          | X              |
      | 000000000000000945  | BR16       | CONS_LATAM   | 945            | X      | X          | X          | X              |
      | 000000000000000945  | BR19       | CONS_LATAM   | 945            | X      | X          | X          | X              |
      | 000000000000000945  | BR25       | CONS_LATAM   | 945            | X      | X          | X          | X              |
      | 000000000000055735  | PE01       | CONS_LATAM   | 55735          | X      | X          | X          | X              |
      | 000000000000058723  | CR01       | CONS_LATAM   | 58723          | X      | X          | X          | X              |
      | 000000000000058725  | CR01       | CONS_LATAM   | 58725          | X      | X          | X          | X              |
      | 000000000000004000  | BR07       | CONS_LATAM   | 4000           | X      | X          | X          | X              |
      | 000000000000004001  | BR07       | CONS_LATAM   | 4001           |        | X          | X          | X              |
      | 000000000000004002  | BR07       | CONS_LATAM   | 4002           | X      | X          | X          | X              |
      | 000000000000004003  | BR07       | CONS_LATAM   | 4003           |        | X          | X          | X              |
      | 000000000000058722  | BR07       | CONS_LATAM   | 58722          |        |            |            | X              |
      | 000000000000058722  | CO01       | CONS_LATAM   | 58722          | X      |            |            | X              |
      | 000000000000002885  | CR01       | CONS_LATAM   | 2885           | X      | X          | X          | X              |
      | 000000000000002886  | CR01       | CONS_LATAM   | 2886           | X      | X          | X          | X              |
      | 000000000000002887  | CR01       | CONS_LATAM   | 2887           | X      | X          | X          | X              |
      | 000000000000002888  | CR01       | CONS_LATAM   | 2888           | X      | X          | X          | X              |
      | 000000000000002889  | CR01       | CONS_LATAM   | 2889           | X      | X          | X          | X              |
      | 000000000000002890  | CR01       | CONS_LATAM   | 2890           | X      | X          | X          | X              |
      | 000000000000002891  | CR01       | CONS_LATAM   | 2891           | X      | X          | X          | X              |
      | 000000000000002892  | CR01       | CONS_LATAM   | 2892           | X      | X          | X          | X              |
      | 000000000000002893  | CR01       | CONS_LATAM   | 2893           | X      | X          | X          | X              |
      | 000000000000002894  | CR01       | CONS_LATAM   | 2894           | X      |            | X          | X              |
      | 000000000000003880  | CR01       | CONS_LATAM   | 3880           | X      | X          | X          | X              |
      | 000000000000003881  | CR01       | CONS_LATAM   | 3881           | X      | X          |            | X              |
      | 000000000000004880  | CR01       | CONS_LATAM   | 4880           | X      | X          | X          | X              |
      | 000000000000004881  | CR01       | CONS_LATAM   | 4881           | X      | X          | X          | X              |
      | 000000000000004882  | CR01       | CONS_LATAM   | 4882           | X      | X          | X          | X              |
      | 000000000000004883  | CR01       | CONS_LATAM   | 4883           | X      | X          | X          | X              |
      | 000000000000006881  | CR01       | CONS_LATAM   | 6881           | X      | X          | X          | X              |

    And I wait "/plan/cns_material_plan_status" Async Queue complete

    And I import "/edm/material_auom_v1" by keyFields "localMaterialNumber,localAuom,sourceSystem"
      | localMaterialNumber | localDenominator | localAuom | localNumerator | materialNumber | sourceSystem |
      | 000000000000000945  | 7                | EA        | 14             | 945            | CONS_LATAM   |
      | 000000000000055735  | 10               | BX        | 1              | 55735          | CONS_LATAM   |
      | 000000000000058723  | 1                | EA        | 12             | 58723          | CONS_LATAM   |
      | 000000000000058724  | 1                | EA        | 12             | 58724          | CONS_LATAM   |
      | 000000000000004000  | 7                | EA        | 14             | 4000           | CONS_LATAM   |
      | 000000000000004001  | 5                | EA        | 1              | 4001           | CONS_LATAM   |
      | 000000000000004002  | 5                | EA        | 1              | 4002           | CONS_LATAM   |
      | 000000000000004003  | 5                | EA        | 1              | 4003           | CONS_LATAM   |
      | 000000000000058722  | 5                | EA        | 1              | 58722          | CONS_LATAM   |
      | 000000000000002886  | 7                | EA        | 14             | 2886           | CONS_LATAM   |
      | 000000000000002887  | 5                | CA        | 1              | 2887           | CONS_LATAM   |
      | 000000000000002888  | 5                | BI        | 1              | 2888           | CONS_LATAM   |
      | 000000000000002889  | 5                | KI        | 1              | 2889           | CONS_LATAM   |
      | 000000000000002890  | 5                | EA        | 1              | 2890           | CONS_LATAM   |
      | 000000000000002891  | 5                | CA        | 1              | 2891           | CONS_LATAM   |
      | 000000000000002892  | 5                | BI        | 1              | 2892           | CONS_LATAM   |
      | 000000000000002893  | 5                | KI        | 1              | 2893           | CONS_LATAM   |
      | 000000000000002894  | 5                | EA        | 1              | 2894           | CONS_LATAM   |
      | 000000000000003880  | 5                | EA        | 1              | 3880           | CONS_LATAM   |
      | 000000000000003881  | 5                | EA        | 1              | 3881           | CONS_LATAM   |
      | 000000000000004880  | 5                | EA        | 1              | 4880           | CONS_LATAM   |
      | 000000000000004880  | 5                | CA        | 1              | 4880           | CONS_LATAM   |
      | 000000000000004880  | 5                | BI        | 1              | 4880           | CONS_LATAM   |
      | 000000000000004881  | 5                | EA        | 1              | 4881           | CONS_LATAM   |
      | 000000000000004883  | 5                | CA        | 1              | 4883           | CONS_LATAM   |
      | 000000000000006881  | 5                | KK        | 1              | 6881           | CONS_LATAM   |

    And I wait "/edm/material_auom_v1" Async Queue complete

    And I import "/plan/cns_plan_unit" by keyFields "planFlag,unit,localUom,sourceSystem,localUomName"
      | planFlag | unit  | localUom | sourceSystem | localUomName |
      | DPSP     | DPSP  | EA       | CONS_LATAM   | CONS_LATAM   |
      | DP       | DP    | BX       | CONS_LATAM   | CONS_LATAM   |
      | DP       | DPSP1 | CA       | CONS_LATAM   | CONS_LATAM   |
      | SP       | SP    | KI       | CONS_LATAM   | CONS_LATAM   |
      | DPSP     | DPSP2 | BI       | CONS_LATAM   | CONS_LATAM   |

    And I wait "/plan/cns_plan_unit" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmProductUnitConversion.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMProductUnitConversion.tsv"

    Then I check file data for filename "GDMProductUnitConversion.tsv" by keyFields "gdmProductUnitConversionId"
#    Then I check region data "/omp/gdm_product_unit_conversion" by keyFields "gdmProductUnitConversionId"
      | gdmProductUnitConversionId | productId             | unitId | active | activeFCTERP | activeOPRERP | activeSOPERP | factor | comments |
      | 945DPSP                    | 945                   | DPSP   | YES    | YES          | YES          | NO           | 2.00   |          |
      | LA_178962124094540035DPSP  | LA_178962124094540035 | DPSP   | YES    | YES          | YES          | NO           | 2.00   |          |
      | 55735DP                    | 55735                 | DP     | YES    | YES          | NO           | NO           | 0.10   |          |
      | 58723DPSP                  | 58723                 | DPSP   | YES    | YES          | YES          | NO           | 12.00  |          |
      | 4000DPSP                   | 4000                  | DPSP   | YES    | YES          | YES          | NO           | 2.00   |          |
      | LA_178962124094540036DPSP  | LA_178962124094540036 | DPSP   | YES    | YES          | YES          | NO           | 2.00   |          |
      | 4002DPSP                   | 4002                  | DPSP   | YES    | YES          | YES          | NO           | 0.20   |          |
      | 58722DPSP                  | 58722                 | DPSP   | YES    | YES          | YES          | NO           | 0.20   |          |
      | LA_178962124094540038DPSP  | LA_178962124094540038 | DPSP   | YES    | YES          | YES          | NO           | 0.20   |          |
      | 2886DPSP                   | 2886                  | DPSP   | YES    | YES          | YES          | NO           | 2.00   |          |
      | LA_178962124288680037DPSP  | LA_178962124288680037 | DPSP   | YES    | YES          | YES          | NO           | 2.00   |          |
      | 2887DPSP1                  | 2887                  | DPSP1  | YES    | YES          | NO           | NO           | 0.20   |          |
      | LA_178962124288680038DPSP1 | LA_178962124288680038 | DPSP1  | YES    | YES          | NO           | NO           | 0.20   |          |
      | 2890DPSP                   | 2890                  | DPSP   | YES    | YES          | YES          | NO           | 0.20   |          |
      | 2891DPSP1                  | 2891                  | DPSP1  | YES    | YES          | NO           | NO           | 0.20   |          |
      | 2894DPSP                   | 2894                  | DPSP   | YES    | NO           | YES          | NO           | 0.20   |          |
      | 3880DPSP                   | 3880                  | DPSP   | YES    | YES          | YES          | NO           | 0.20   |          |
      | 3881DPSP                   | 3881                  | DPSP   | YES    | YES          | YES          | NO           | 0.20   |          |
      | 4880DPSP                   | 4880                  | DPSP   | YES    | YES          | YES          | NO           | 0.20   |          |
      | 4880DPSP1                  | 4880                  | DPSP1  | YES    | YES          | NO           | NO           | 0.20   |          |
      | 4880DPSP2                  | 4880                  | DPSP2  | YES    | YES          | YES          | NO           | 0.20   |          |
      | 2888DPSP2                  | 2888                  | DPSP2  | YES    | YES          | YES          | NO           | 0.20   |          |
      | LA_178962124288680039DPSP2 | LA_178962124288680039 | DPSP2  | YES    | YES          | YES          | NO           | 0.20   |          |
      | 2889SP                     | 2889                  | SP     | YES    | NO           | YES          | NO           | 0.20   |          |
      | LA_178962124288680040SP    | LA_178962124288680040 | SP     | YES    | NO           | YES          | NO           | 0.20   |          |
      | 2892DPSP2                  | 2892                  | DPSP2  | YES    | YES          | YES          | NO           | 0.20   |          |
      | 4883DPSP1                  | 4883                  | DPSP1  | YES    | YES          | NO           | NO           | 0.20   |          |
      | 2893SP                     | 2893                  | SP     | YES    | NO           | YES          | NO           | 0.20   |          |

  Scenario:delete all the test data
    And I delete the test data
    And I will remove all data with region "/omp/gdm_product_unit_conversion"
    And I will remove all data with region "/plan/edm_failed_data"
