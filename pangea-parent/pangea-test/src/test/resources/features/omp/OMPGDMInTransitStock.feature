@pangea @AEAZ-5745
Feature:  AEAZ-5745

    @Scenario1
    Scenario: Sheet1

        And I will remove the test file on sink application "GDMInTransitStock.tsv"
        When I import "/plan/cns_intransitstock" by keyFields "sourceSystem,demandGroup,productId,date,quantity"
            | sourceSystem | demandGroup | productId   | date       | quantity | uom |
            | CONS_LATAM   | 76100009    | 87818       | 17/01/2018 | 10300    | EA  |
            | CONS_LATAM   | 76100009    | 91838       | 17/01/2018 | 760      | CS  |
            | CONS_LATAM   | 76100009    | 91838       | 17/01/2018 | 10000    | EA  |
            | CONS_LATAM   | 76100009    | 7550641     | 17/01/2018 | 420      | CS  |
            | CONS_LATAM   | 76100006    | 87818       | 17/01/2018 | 10500    | EA  |
            | CONS_LATAM   | 76100006    | 91838       | 17/01/2018 | 8900     | EA  |
            | CONS_LATAM   | 76100006    | 6000700     | 17/01/2018 | 10050    | EA  |
            | CONS_LATAM   | 76100010    | 7550641     | 17/01/2018 | 225      | PL  |
            | CONS_LATAM   | 76100010    | 87818       | 17/01/2018 | 7645     | EA  |
            | CONS_LATAM   | 76100010    | 95299       | 17/01/2018 | 750      | CS  |
            | ATLM_LATAM   | 76100009    | 878188888   | 17/01/2018 | 10300    | EA  |
            | CONS_LATAM   | 76100010    | 999999999   | 17/01/2018 | 750      | CS  |
            | CONS_LATAM   | 76100010    | 99998881111 | 17/01/2018 | 750      | ZZ  |
            | CONS_LATAM   | 76100010    | 777777777   | 17/01/2018 | 750      | CS  |

        And I wait "/plan/cns_intransitstock" Async Queue complete

        When I import "/plan/cns_dem_grp_asgn" by keyFields "sourceSystem,customerId"
            | customerId | sourceSystem | demandGroup |
            | 12192      | CONS_LATAM   | 76100006    |
            | 12190      | CONS_LATAM   | 76100006    |
            | 81         | CONS_LATAM   | 76100006    |
            | 9423       | CONS_LATAM   | 76100006    |
            | 9460       | CONS_LATAM   | 76100009    |
            | 9783       | CONS_LATAM   | 76100009    |
            | 866        | CONS_LATAM   | 76100009    |
            | 9659       | CONS_LATAM   | 76100009    |
            | 9730       | CONS_LATAM   | 76100010    |
            | 12773      | CONS_LATAM   | 76100010    |
            | 10673      | CONS_LATAM   | 76100010    |
            | 11777      | CONS_LATAM   | 76100010    |
        And I wait "/plan/cns_dem_grp_asgn" Async Queue complete

        When I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
            | localMaterialNumber | localDpParentCode | sourceSystem |
            | 777777777           | 1234567890        | CONS_LATAM   |
            | 7550641             | 1234567890        | CONS_LATAM   |
            | 91838               | 1234567890        | CONS_LATAM   |
            | 68781               | 1234567890        | CONS_LATAM   |
            | 6000700             | 9876543210        | CONS_LATAM   |
            | 95299               | 9876543210        | CONS_LATAM   |
            | 87818               | 4567890123        | CONS_LATAM   |
        And I wait "/edm/material_global_v1" Async Queue complete

        When I import "/edm/material_auom_v1" by keyFields "sourceSystem,localMaterialNumber,localAuom"
            | sourceSystem | localMaterialNumber | localNumerator | localDenominator | localAuom |
            | CONS_LATAM   | 95299               | 1              | 1                | CS        |
            | CONS_LATAM   | 91838               | 1020           | 1                | PAL       |
            | CONS_LATAM   | 7550641             | 180            | 1                | PL        |
            | CONS_LATAM   | 7550641             | 180            | 1                | CS        |
            | CONS_LATAM   | 6000700             | 1              | 1                | EA        |
            | CONS_LATAM   | 91838               | 1020           | 1                | EA        |
            | CONS_LATAM   | 91838               | 1020           | 1                | CS        |
            | CONS_LATAM   | 6000700             | 4452           | 1                | PAL       |
            | CONS_LATAM   | 87818               | 1              | 1                | EA        |
            | CONS_LATAM   | 87818               | 500            | 1                | PAL       |
            | CONS_LATAM   | 87818               | 24             | 1                | CA        |
        And I wait "/edm/material_auom_v1" Async Queue complete

        When I submit task with xml file "xml/omp/OMPGDMInTransitStock.xml" and execute file "jar/pangea-view.jar"

        Then A file is found on sink application with name "GDMInTransitStock.tsv"

         Then I check file data for filename "GDMInTransitStock" by keyFields "customerId,productId,quantity"
            | customerId | productId     | dueDate             | quantity |
            | 76100006   | LA_4567890123 | 17/01/2018 00:00:00 | 10500    |
            | 76100006   | LA_9876543210 | 17/01/2018 00:00:00 | 10050    |
            | 76100006   | LA_1234567890 | 17/01/2018 00:00:00 | 9078000  |
            | 76100009   | LA_4567890123 | 17/01/2018 00:00:00 | 10300    |
            | 76100009   | LA_1234567890 | 17/01/2018 00:00:00 | 10975200 |
            | 76100009   | LA_1234567890 | 17/01/2018 00:00:00 | 75600    |
            | 76100010   | LA_1234567890 | 17/01/2018 00:00:00 | 40500    |
            | 76100010   | LA_4567890123 | 17/01/2018 00:00:00 | 7645     |
            | 76100010   | LA_9876543210 | 17/01/2018 00:00:00 | 750      |
        Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3"
            | errorCode | functionalArea | interfaceID          | sourceSystem | key1       | key2     | key3        | errorValue                                     |
            | J1        | DP             | OMPGDMInTransitStock | sourceSystem | ATLM_LATAM | 76100009 | 878188888   | productId do not exist in edm_material         |
            | J1        | DP             | OMPGDMInTransitStock | sourceSystem | CONS_LATAM | 76100010 | 777777777   | unit do not exist in edm_Auom                  |
            | J1        | DP             | OMPGDMInTransitStock | sourceSystem | CONS_LATAM | 76100010 | 99998881111 | localDpParentCode do not exist in edm material |
            | J1        | DP             | OMPGDMInTransitStock | sourceSystem | CONS_LATAM | 76100010 | 999999999   | localDpParentCode do not exist in edm material |
        And I delete the test data

