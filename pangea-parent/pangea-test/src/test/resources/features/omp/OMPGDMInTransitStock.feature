@pangea_test
Feature:  AEAZ-8303

    @Scenario1
    Scenario: Full Load consumption
        When I import "/plan/cns_intransitstock" by keyFields "sourceSystem,customerId,productId,date,quantity"
            | sourceSystem | customerId | productId   | date       | quantity | uom | sourceCountry |
            | CONS_LATAM   | 12192      | 87818       | 17/01/2018 | 10300    | EA  | BR            |
            | CONS_LATAM   | 12190      | 91838       | 17/01/2018 | 760      | CS  | BR            |
            | CONS_LATAM   | 81         | 7550642     | 17/01/2018 | 225      | PL  | BR            |
            | CONS_LATAM   | 9423       | 87817       | 20/01/2018 | 10500    | EA  | BR            |
            | CONS_LATAM   | 9460       | 91839       | 17/01/2018 | 8900     | EA  | BR            |
            | CONS_LATAM   | 9783       | 87816       | 17/01/2018 | 7645     | EA  | BR            |
            | CONS_LATAM   | 174005     | 91837       | 19/01/2018 | 10000    | EA  | BR            |
            | CONS_LATAM   | 174004     | 7550641     | 17/01/2018 | 420      | CS  | BR            |
            | CONS_LATAM   | 9730       | 6000700     | 17/01/2018 | 10050    | EA  | BR            |
            | CONS_LATAM   | 12773      | 95299       | 17/01/2018 | 750      | CS  | BR            |
            | CONS_LATAM   | 11777      | 99998881111 | 17/01/2018 | 750      | ZZ  | BR            |
            | CONS_LATAM   | 11778      | 777777777   | 17/01/2018 | 750      | CS  | BR            |
            | CONS_LATAM   | 10673      | 999999999   | 17/01/2018 | 750      | CS  | BR            |


        And I wait "/plan/cns_intransitstock" Async Queue complete

        When I import "/plan/cns_dem_grp_asgn" by keyFields "sourceSystem,customerId"
            | sourceSystem | customerId | countryAffiliate | demandGroup | salesOrganization | channel |
            | CONS_LATAM   | 12192      | BR               | 76100006    | BR01              | CH008   |
            | CONS_LATAM   | 106827     | BR               | 76100009    | BR01              | CH008   |
            | CONS_LATAM   | 12190      | BR               | 76100006    | BR01              | CH008   |
            | CONS_LATAM   | 81         | BR               | 76100006    | BR01              | CH008   |
            | CONS_LATAM   | 9423       | BR               | 76100006    | BR01              | CH008   |
            | CONS_LATAM   | 9460       | BR               | 76100009    | BR01              | CH007   |
            | CONS_LATAM   | 9783       | BR               | 76100009    | BR01              | CH007   |
            | CONS_LATAM   | 106826     | BR               | 76100009    | BR01              | CH007   |
            | CONS_LATAM   | 106828     | BR               | 76100009    | BR01              | CH007   |
            | CONS_LATAM   | 11777      | BR               | 76100009    | BR01              | CH007   |
            | CONS_LATAM   | 11778      | BR               | 76100009    | BR01              | CH007   |
            | CONS_LATAM   | 10673      | BR               | 76100009    | BR01              | CH007   |


        And I wait "/plan/cns_dem_grp_asgn" Async Queue complete

        When I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
            | sourceSystem | localMaterialNumber | localBaseUom | localDpParentCode |
            | CONS_LATAM   | 87817               | CS           | 123456789         |
            | CONS_LATAM   | 87816               | EA           | 987654321         |
            | CONS_LATAM   | 91837               | CS           | 987654321         |
            | CONS_LATAM   | 87818               | EA           | 123456789         |
            | CONS_LATAM   | 91838               | CS           | 123456789         |
            | CONS_LATAM   | 91839               | CS           | 987654321         |
            | CONS_LATAM   | 7550642             | PL           | 123456789         |
            | CONS_LATAM   | 7550641             | PL           | 213456789	        |
            | CONS_LATAM   | 87819               | EA           | 987654321         |
            | CONS_LATAM   | 6000700             | EA           | 213456789         |
            | CONS_LATAM   | 95299               | CS           | 897654321         |
            | CONS_LATAM   | 99998881111         | CA           | 132456789         |
            | CONS_LATAM   | 777777777           | CS           |                   |

        And I wait "/edm/material_global_v1" Async Queue complete

        When I import "/edm/material_auom_v1" by keyFields "sourceSystem,localMaterialNumber,localAuom"
            | sourceSystem | localMaterialNumber | localAuom | localNumerator | localDenominator |
            | CONS_LATAM   | 87817               | EA        | 4              | 9                |
            | CONS_LATAM   | 91839               | EA        | 5              | 8                |
            | CONS_LATAM   | 87816               | EA        | 6              | 7                |
            | CONS_LATAM   | 91837               | EA        | 7              | 6                |
            | CONS_LATAM   | 87818               | EA        | 1              | 12               |
            | CONS_LATAM   | 91838               | CS        | 2              | 11               |
            | CONS_LATAM   | 7550642             | PL        | 3              | 10               |
            | CONS_LATAM   | 7550641             | CS        | 8              | 5                |
            | CONS_LATAM   | 6000700             | EA        | 9              | 4                |
            | CONS_LATAM   | 95299               | CS        | 10             | 3                |
            | CONS_LATAM   | 99998881111         | CA        | 11             | 2                |
            | CONS_LATAM   | 777777777           | CS        | 12             | 1                |

        And I wait "/edm/material_auom_v1" Async Queue complete

        When I import "/project_one/knvh" by keyFields "datab,hiytp,kunnr,spart,vkorg,vtweg"
            | datab    | datbi    | kunnr  | hkunnr | hvkorg | hvtweg | hiytp | spart | vkorg | vtweg |
            | 20260802 | 99991231 | 174005 | 106826 | BR01   | 10     | A     | 10    | BR01  | 10    |
            | 20270420 | 99991231 | 174004 | 106827 | BR01   | 10     | A     | 10    | BR01  | 10    |
            | 20280328 | 99991231 | 106827 | 106828 | BR01   | 10     | A     | 10    | BR01  | 10    |
            | 20280328 | 99991231 | 12773  |        | BR01   | 10     | A     | 10    | BR01  | 10    |
        And I wait "/project_one/knvh" Async Queue complete

        When I submit task with xml file "xml/omp/OMPGDMInTransitStock.xml" and execute file "jar/pangea-view.jar"

        When I submit aggregation task with xml file "xml/omp/OMPGDMInTransitStock_aggregation.xml"

        #Then A file is found on sink application with name "GDMInTransitStock.tsv"

       #Then I check file data for filename "GDMInTransitStock" by keyFields "customerId,productId,dueDate"
        Then I check region data "/omp/gdm_in_transit_stock" by keyFields "customerId,productId,dueDate"
            | customerId | productId    | dueDate             | quantity      |
            | 76100006   | LA_123456789 | 17/01/2018 00:00:00 | 1064.016      |
            | 76100006   | LA_123456789 | 20/01/2018 00:00:00 | 4666.6666667  |
            | 76100009   | LA_213456789 | 17/01/2018 00:00:00 | 672.0000000   |
            | 76100009   | LA_987654321 | 17/01/2018 00:00:00 | 12115.358     |
            | 76100009   | LA_987654321 | 19/01/2018 00:00:00 | 11666.6666667 |

        Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3"
            | errorCode | functionalArea | interfaceID          | sourceSystem | key1       | key2  | key3        | errorValue                                     |
            | J1        | DP             | OMPGDMInTransitStock | sourceSystem | CONS_LATAM | 10673 | 999999999   | productID do not exit in edm_material          |
            | J1        | DP             | OMPGDMInTransitStock | sourceSystem | CONS_LATAM | 11778 | 777777777   | localDpParentCode do not exist in edm material |
            | J2        | DP             | OMPGDMInTransitStock | sourceSystem | CONS_LATAM | 11777 | 99998881111 | unit do not exist in edm_Auom                  |
            | T4        | DP             | OMPGDMInTransitStock | sourceSystem | CONS_LATAM | 12773 | 95299       | demandGroup is not defined for customerId      |
            | T4        | DP             | OMPGDMInTransitStock | sourceSystem | CONS_LATAM | 9730  | 6000700     | demandGroup is not defined for customerId      |

        And I delete the test data




