@pangea_test @AEAZ-8292
Feature: OMPGdmProductUnitConversion AEAZ-8292

  @Scenario1
  Scenario: full load consumption

    And I will remove the test file on sink application "GDMProductUnitConversion.tsv"

    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber,sourceSystem"
      | localMaterialNumber | materialNumber | localDpParentCode  | localBaseUom | primaryPlanningCode | sourceSystem |
      | 000000000000050715  | 50715          | 78910100139980000  | EA           | 69515                | CONS_LATAM   |
      | 000000000000069515  | 69515          | 78910105071520000  | EA           | 50715                | CONS_LATAM   |
      | 000000000000092077  | 92077          |                    | EA           | 92077                | CONS_LATAM   |
      | 000000000000000016  | 16             |                    | DZ           | 16                  | CONS_LATAM   |
      | 000000000000000017  | 16             |                    | DZ           | 17                  | CONS_LATAM1  |
      | 000000000000000945  | 945            | 178962124094540035 | EA           | 945                 | CONS_LATAM   |
      | 000000000000055735  | 55735          |                    | ZUM          | 55735               | CONS_LATAM   |
      | 000000000000058723  | 58723          |                    | EA           | 58723               | CONS_LATAM   |
      | 000000000000058725  | 58725          |                    | EA           | 58725               | CONS_LATAM   |
      | 000000000000004000  | 4000           | 178962124094540036 | EA           | 4000                | CONS_LATAM   |
      | 000000000000004001  | 4001           | 178962124094540037 | EA           | 4001                | CONS_LATAM   |
      | 000000000000004002  | 4002           |                    | EA           | 4002                | CONS_LATAM   |
      | 000000000000004003  | 4003           |                    | EA           | 4003                | CONS_LATAM   |
      | 000000000000058722  | 58722          | 178962124094540038 | EA           | 58722               | CONS_LATAM   |
      | 000000000000002885  | 2885           | 178962124288680036 | EA           | 2885                | CONS_LATAM   |
      | 000000000000002886  | 2886           | 178962124288680037 | EA           | 2886                | CONS_LATAM   |
      | 000000000000002887  | 2887           | 178962124288680038 | LB           | 2887                | CONS_LATAM   |
      | 000000000000002888  | 2888           | 178962124288680039 | CS           | 2888                | CONS_LATAM   |
      | 000000000000002889  | 2889           | 178962124288680040 | DZ           | 2889                | CONS_LATAM   |
      | 000000000000002890  | 2890           |                    | EA           | 2890                | CONS_LATAM   |
      | 000000000000002891  | 2891           |                    | LB           | 2891                | CONS_LATAM   |
      | 000000000000002892  | 2892           |                    | CS           | 2892                | CONS_LATAM   |
      | 000000000000002893  | 2893           |                    | DZ           | 2893                | CONS_LATAM   |
      | 000000000000002894  | 2894           |                    | EA           | 2894                | CONS_LATAM   |
      | 000000000000003880  | 3880           |                    | EA           | 3880                | CONS_LATAM   |
      | 000000000000003881  | 3881           |                    | EA           | 3881                | CONS_LATAM   |
      | 000000000000004880  | 4880           |                    | EA           | 4880                | CONS_LATAM   |
      | 000000000000004881  | 4881           |                    | EA           | 4881                | CONS_LATAM1  |
      | 000000000000004882  | 4882           |                    | EA           | 4882                | CONS_LATAM   |
      | 000000000000004883  | 4883           |                    | LB           | 4883                | CONS_LATAM   |
      | 000000000000006881  | 6881           |                    | KK           | 6881                | CONS_LATAM   |
      | 000000000000007881  | 7881           |                    | KK           | 7881                | CONS_LATAM   |
      | 000000000000008881  | 8881           | 178962124288680041 | KK           | 8881                | CONS_LATAM   |
      | 000000000000008882  | 8882           | 178962124288680041 | KK           | 8882                | CONS_LATAM   |



    And I wait "/edm/material_global_v1" Async Queue complete

    #material_global_V1-materialNumber = cns_material_plan_status-materialNumber
    And I import "/plan/cns_material_plan_status" by keyFields "localMaterialNumber,localPlant,sourceSystem"
      | localMaterialNumber | localPlant | sourceSystem | materialNumber   | active | dpRelevant  | spRelevant | noPlanRelevant |
      | 000000000000092077  | CO02       | CONS_LATAM   | 92077            | X      |            | X           |                |
      | 000000000000069515  | BR07       | CONS_LATAM   | 69515            | X      | X          |             |                |
      | 000000000000069515  | BR16       | CONS_LATAM   | 69515            | X      | X          |             |                |
      | 000000000000069515  | BR19       | CONS_LATAM   | 69515            | X      | X          |             |                |
      | 000000000000069515  | BR25       | CONS_LATAM   | 69515            | X      | X          |             |                |
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
      | 000000000000008881  | CR01       | CONS_LATAM   | 8881           | X      | X          | X          | X              |
      | 000000000000008882  | CR01       | CONS_LATAM   | 8882           | X      | X          | X          | X              |
      | 000000000000007881  | CR01       | CONS_LATAM   | 7881           | X      | X          |            |                |

    And I wait "/plan/cns_material_plan_status" Async Queue complete

    And I import "/edm/material_auom_v1" by keyFields "localMaterialNumber,localAuom,sourceSystem"
      | localMaterialNumber | localDenominator | localAuom | localNumerator | materialNumber | sourceSystem |
      | 000000000000069515  | 8                | ZUM       | 1000           | 69515          | CONS_LATAM   |
      | 000000000000069515  | 48               | 1         | 48             | 69515          | CONS_LATAM   |
      | 000000000000069515  | 1                | PAL       | 3168           | 69515          | CONS_LATAM   |
      | 000000000000069515  | 1                | EA        | 1              | 69515          | CONS_LATAM   |
      | 000000000000069515  | 8                | ZUN       | 1000           | 69515          | CONS_LATAM   |
      | 000000000000069515  | 1                | KI        | 48             | 69515          | CONS_LATAM   |
      | 000000000000069515  | 48               | UM        | 48             | 69515          | CONS_LATAM   |
      | 000000000000069515  | 1000             | ZUV       | 48000          | 69515          | CONS_LATAM   |
      | 000000000000069515  | 48               | ZUA       | 48             | 69515          | CONS_LATAM   |
      | 000000000000050715  | 1                | EA        | 1              | 50715          | CONS_LATAM   |
      | 000000000000050715  | 1                | PAL       | 2124           | 50715          | CONS_LATAM   |
      | 000000000000050715  | 6                | 1         | 6              | 50715          | CONS_LATAM   |
      | 000000000000050715  | 209              | KG        | 1000           | 50715          | CONS_LATAM   |
      | 000000000000050715  | 6                | UM        | 6              | 50715          | CONS_LATAM   |
      | 000000000000050715  | 1000             | ZUN       | 1000           | 50715          | CONS_LATAM   |
      | 000000000000050715  | 1667             | ZUV       | 10000          | 50715          | CONS_LATAM   |
      | 000000000000050715  | 6                | ZUA       | 6              | 50715          | CONS_LATAM   |
      | 000000000000050715  | 1000             | ZUM       | 1000           | 50715          | CONS_LATAM   |
      | 000000000000050715  | 1                | KI        | 6              | 50715          | CONS_LATAM   |
      | 000000000000092077  | 6                | UM        | 6              | 50715          | CONS_LATAM   |
      | 000000000000092077  | 1                | KI        | 6              | 50715          | CONS_LATAM   |
      | 000000000000092077  | 60               | ZUN       | 1000           | 50715          | CONS_LATAM   |
      | 000000000000092077  | 1                | EA        | 1              | 50715          | CONS_LATAM   |
      | 000000000000092077  | 6                | 1         | 6              | 50715          | CONS_LATAM   |
      | 000000000000092077  | 1                | PAL       | 462            | 50715          | CONS_LATAM   |
      | 000000000000092077  | 1000             | ZUV       | 6000           | 50715          | CONS_LATAM   |
      | 000000000000092077  | 60               | ZUM       | 1000           | 50715          | CONS_LATAM   |
      | 000000000000092077  | 6                | ZUA       | 6              | 50715          | CONS_LATAM   |
      | 000000000000000945  | 7                | EA        | 14             | 945            | CONS_LATAM   |
      | 000000000000055735  | 10               | ZUM       | 1              | 55735          | CONS_LATAM   |
      | 000000000000058723  | 1                | EA        | 12             | 58723          | CONS_LATAM   |
      | 000000000000058724  | 1                | EA        | 12             | 58724          | CONS_LATAM   |
      | 000000000000004000  | 7                | EA        | 14             | 4000           | CONS_LATAM   |
      | 000000000000004001  | 5                | EA        | 1              | 4001           | CONS_LATAM   |
      | 000000000000004002  | 5                | EA        | 1              | 4002           | CONS_LATAM   |
      | 000000000000004003  | 5                | EA        | 1              | 4003           | CONS_LATAM   |
      | 000000000000058722  | 5                | EA        | 1              | 58722          | CONS_LATAM   |
      | 000000000000002886  | 7                | EA        | 14             | 2886           | CONS_LATAM   |
      | 000000000000002887  | 5                | LB        | 1              | 2887           | CONS_LATAM   |
      | 000000000000002888  | 5                | CS        | 1              | 2888           | CONS_LATAM   |
      | 000000000000002889  | 5                | DZ        | 1              | 2889           | CONS_LATAM   |
      | 000000000000002890  | 5                | EA        | 1              | 2890           | CONS_LATAM   |
      | 000000000000002891  | 5                | LB        | 1              | 2891           | CONS_LATAM   |
      | 000000000000002892  | 5                | CS        | 1              | 2892           | CONS_LATAM   |
      | 000000000000002893  | 5                | DZ        | 1              | 2893           | CONS_LATAM   |
      | 000000000000002894  | 5                | EA        | 1              | 2894           | CONS_LATAM   |
      | 000000000000003880  | 5                | EA        | 1              | 3880           | CONS_LATAM   |
      | 000000000000003881  | 5                | EA        | 1              | 3881           | CONS_LATAM   |
      | 000000000000004880  | 5                | EA        | 1              | 4880           | CONS_LATAM   |
      | 000000000000004880  | 5                | LB        | 1              | 4880           | CONS_LATAM   |
      | 000000000000004880  | 5                | CS        | 1              | 4880           | CONS_LATAM   |
      | 000000000000004881  | 5                | EA        | 1              | 4881           | CONS_LATAM   |
      | 000000000000004883  | 5                | LB        | 1              | 4883           | CONS_LATAM   |
      | 000000000000006881  | 5                | KK        | 1              | 6881           | CONS_LATAM   |
      | 000000000000007881  | 5                | EA        | 1              | 7881           | CONS_LATAM   |
      | 000000000000008881  | 5                | EA        | 1              | 8881           | CONS_LATAM   |
      | 000000000000008882  | 5                | EA        | 1              | 8882           | CONS_LATAM   |

    And I wait "/edm/material_auom_v1" Async Queue complete

    And I import "/plan/cns_plan_unit" by keyFields "planFlag,unit,localUom,sourceSystem,localUomName"
      | planFlag | unit   | localUom | sourceSystem | localUomName        |
      | DP       | LA_ZUM | ZUM      | CONS_LATAM   | Market Control Unit |
      | DPSP     | EA     | EA       | CONS_LATAM   | CONS_LATAM          |
      | DP       | LA_ZUM | ZUM      | CONS_LATAM   | CONS_LATAM          |
      | DP       | LB     | LB       | CONS_LATAM   | CONS_LATAM          |
      | SP       | DZ     | DZ       | CONS_LATAM   | CONS_LATAM          |
      | DPSP     | CA     | CS       | CONS_LATAM   | CONS_LATAM          |

    And I wait "/plan/cns_plan_unit" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmProductUnitConversion.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMProductUnitConversion.tsv"

    Then I check file data for filename "GDMProductUnitConversion.tsv" by keyFields "gdmProductUnitConversionId"
 #   Then I check region data "/omp/gdm_product_unit_conversion" by keyFields "gdmProductUnitConversionId"
      | gdmProductUnitConversionId | comments | productId             | activeFCTERP | active | unitId | factor | activeOPRERP | activeSOPERP|
      | 3881EA                     |          | 3881                  | YES          | YES    | EA     | 5.00   | YES          | NO           |
      | 7881EA                     |          | 7881                  | YES          | YES    | EA     | 5.00   | NO           | NO           |
      | 50715EA                    |          | 50715                 | YES          | YES    | EA     | 1.00   | NO           | NO           |
      | LA_78910105071520000EA     |          | LA_78910105071520000  | YES          | YES    | EA     | 1.00   | NO           | NO           |
      | 50715LA_ZUM                |          | 50715                 | YES          | YES    | LA_ZUM | 0.01   | NO           | NO           |
      | LA_78910105071520000LA_ZUM |          | LA_78910105071520000  | YES          | YES    | LA_ZUM | 0.01   | NO           | NO           |
      | 2887LB                     |          | 2887                  | YES          | YES    | LB     | 5.00   | NO           | NO           |
      | LA_178962124288680038LB    |          | LA_178962124288680038 | YES          | YES    | LB     | 5.00   | NO           | NO           |
      | 58722EA                    |          | 58722                 | NO           | YES    | EA     | 5.00   | YES          | NO           |
      | LA_178962124094540038EA    |          | LA_178962124094540038 | YES          | YES    | EA     | 5.00   | NO           | NO           |
      | 2892CA                     |          | 2892                  | YES          | YES    | CA     | 5.00   | YES          | NO           |
      | 8881EA                     |          | 8881                  | YES          | YES    | EA     | 5.00   | YES          | NO           |
      | LA_178962124288680041EA    |          | LA_178962124288680041 | YES          | YES    | EA     | 5.00   | NO           | NO           |
      | 8882EA                     |          | 8882                  | YES          | YES    | EA     | 5.00   | YES          | NO           |
      | 2888CA                     |          | 2888                  | YES          | YES    | CA     | 5.00   | YES          | NO           |
      | LA_178962124288680039CA    |          | LA_178962124288680039 | YES          | YES    | CA     | 5.00   | NO           | NO           |
      | 2891LB                     |          | 2891                  | YES          | YES    | LB     | 5.00   | NO           | NO           |
      | 58723EA                    |          | 58723                 | YES          | YES    | EA     | 0.08   | YES          | NO           |
      | 2894EA                     |          | 2894                  | NO           | YES    | EA     | 5.00   | YES          | NO           |
      | 3880EA                     |          | 3880                  | YES          | YES    | EA     | 5.00   | YES          | NO           |
      | 4000EA                     |          | 4000                  | YES          | YES    | EA     | 0.50   | YES          | NO           |
      | LA_178962124094540036EA    |          | LA_178962124094540036 | YES          | YES    | EA     | 0.50   | NO           | NO           |
      | 2889DZ                     |          | 2889                  | NO           | YES    | DZ     | 5.00   | YES          | NO           |
      | 92077EA                    |          | 92077                 | NO           | YES    | EA     | 1.00   | YES          | NO           |
      | 55735LA_ZUM                |          | 55735                 | YES          | YES    | LA_ZUM | 10.00  | NO           | NO           |
      | 4002EA                     |          | 4002                  | YES          | YES    | EA     | 5.00   | YES          | NO           |
      | 2893DZ                     |          | 2893                  | NO           | YES    | DZ     | 5.00   | YES          | NO           |
      | 2890EA                     |          | 2890                  | YES          | YES    | EA     | 5.00   | YES          | NO           |
      | 945EA                      |          | 945                   | YES          | YES    | EA     | 0.50   | YES          | NO           |
      | LA_178962124094540035EA    |          | LA_178962124094540035 | YES          | YES    | EA     | 0.50   | NO           | NO           |
      | 4883LB                     |          | 4883                  | YES          | YES    | LB     | 5.00   | NO           | NO           |
      | 2886EA                     |          | 2886                  | YES          | YES    | EA     | 0.50   | YES          | NO           |
      | LA_178962124288680037EA    |          | LA_178962124288680037 | YES          | YES    | EA     | 0.50   | NO           | NO           |
      | 4880CA                     |          | 4880                  | YES          | YES    | CA     | 5.00   | YES          | NO           |
      | 4880EA                     |          | 4880                  | YES          | YES    | EA     | 5.00   | YES          | NO           |
      | 4880LB                     |          | 4880                  | YES          | YES    | LB     | 5.00   | NO           | NO           |


  Scenario:delete all the test data
    And I delete the test data
    And I will remove all data with region "/omp/gdm_product_unit_conversion"
    And I will remove all data with region "/plan/edm_failed_data"
