@pangea_test @AEAZ-6098
Feature:  OMPGdmProduct AEAZ-6098

  Background:delete all test data

    And I will remove all data with region "/edm/material_global_v1"
    And I will remove all data with region "/plan/cns_productcustomer"
    And I will remove all data with region "/plan/cns_plan_dem_grp"
    And I will remove all data with region "/edm/material_auom_v1"
    And I will remove all data with region "/plan/edm_failed_data"
    And I will remove all data with region "/omp/gdm_productcustomer"
    And I will remove the test file on sink application "GDMProductCustomer.tsv"

  @Scenario1
  Scenario: check rule J1 , T1 and J2
  J2:
  >> line1:productId is empty add fail data
  T1:
  >> line2:localDpParentCode is empty add fail data
  J1:
  >> line3:material_global_v1.localMaterialNumber not equal cns_productcustomer.productId add fail data

    Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber,localDpParentCode"
      | sourceSystem | localMaterialNumber | localDpParentCode  | baseUom |
      | CONS_LATAM   | 6000700             | 178962124094540500 | EA      |
      | CONS_LATAM   | 95299               |                    | EA      |
    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/plan/cns_productcustomer" by keyFields "sourceSystem,customerId,productId,locationId"
      | sourceSystem | customerId | productId   | productStatus | moq | ioq   | stockLevel | uom | locationId | norm | leadTime | roundingThreshold |
      | CONS_LATAM   | 76100006   | 6000700     | ACTIVE        | 950 | 10050 | 5975       | ZZ  | BR16       | 7    | 10       | 95                |
      | CONS_LATAM   | 76100010   | 95299       | ACTIVE        | 500 | 750   | 875        | CS  | BR08       | 9    | 8        | 90                |
      | CONS_LATAM   | 76100010   | 95999999999 | ACTIVE        | 500 | 750   | 875        | CS  | BR08       | 9    | 8        | 35                |
    And I wait "/plan/cns_productcustomer" Async Queue complete

    Given I import "/edm/material_auom_v1" by keyFields "localAuom,sourceSystem,localMaterialNumber"
      | localAuom | localDenominator | localMaterialNumber | localNumerator | materialNumber | numerator | sourceSystem |
      | EA        | 40               | 6000700             | 1              | 6000700        |           | CONS_LATAM   |
      | CS        | 42               | 95299               | 1              | 95299          |           | CONS_LATAM   |
    And I wait "/edm/material_auom_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmProductCustomer.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/plan/edm_failed_data" by keyFields "errorCode,functionalArea,interfaceID,key1,key2,key3,key4,key5,sourceSystem"
      | errorCode | functionalArea | interfaceID        | key1        | key2 | key3 | key4 | key5 | errorValue                                     | sourceSystem |
      | J2        | DP             | GDMProductCustomer | 6000700     | ZZ   |      |      |      | uom do not exist in edm auom                   | CONS_LATAM   |
      | T1        | DP             | GDMProductCustomer | 95299       |      |      |      |      | localDpParentCode do not exist in edm material | CONS_LATAM   |
      | J1        | DP             | GDMProductCustomer | 95999999999 |      |      |      |      | ProductId do not exist in edm material         | CONS_LATAM   |

  @Scenario2
  Scenario: Scenario_Input_Output
  the data come from DOMD_OMP_GDMProductCustomer.xlsx sheet Scenario_Input_Output

    Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber,localDpParentCode"
      | sourceSystem | localMaterialNumber | localDpParentCode  | baseUom |
      | CONS_LATAM   | 87818               | 178962124094540036 | EA      |
      | CONS_LATAM   | 91838               | 178962124094540036 | CS      |
      | CONS_LATAM   | 7550641             | 178962124094540036 | PL      |
      | CONS_LATAM   | 87819               | 178962124094540099 | EA      |
      | CONS_LATAM   | 91839               | 178962124094540099 | EA      |
      | CONS_LATAM   | 7550642             | 178962124094540099 | CS      |
      | CONS_LATAM   | 6000701             | 178962124094540500 | EA      |
    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/plan/cns_productcustomer" by keyFields "sourceSystem,customerId,productId,locationId"
      | sourceSystem | customerId | productId | productStatus | moq   | ioq   | stockLevel | uom | locationId | norm | leadTime | roundingThreshold |
      | CONS_LATAM   | 76100009   | 87818     | ACTIVE        | 10200 | 10300 | 15350      | EA  | BR08       | 7    | 7        | 50                |
      | CONS_LATAM   | 76100009   | 91838     | INACTIVE      | 700   | 760   | 1080       | CS  | BR08       | 8    | 21       | 25                |
      | CONS_LATAM   | 76100010   | 7550641   | ACTIVE        | 200   | 225   | 313        | PL  | BR08       | 9    | 9        | 67                |
      | CONS_LATAM   | 76100006   | 87818     | ACTIVE        | 10000 | 10500 | 15250      | EA  | BR08       | 19   | 19       | 89                |
      | CONS_LATAM   | 76100006   | 91838     | ACTIVE        | 8500  | 8900  | 12950      | CS  | BR12       | 6    | 6        | 50                |
      | CONS_LATAM   | 76100010   | 87819     | INACTIVE      | 7300  | 7645  | 11123      | EA  | BR12       | 11   | 250      | 75                |
      | CONS_LATAM   | 76100009   | 91839     | INACTIVE      | 9000  | 10000 | 14000      | EA  | BR19       | 51   | 51       | 82                |
      | CONS_LATAM   | 76100009   | 7550642   | INACTIVE      | 300   | 420   | 510        | CS  | BR19       | 115  | 115      | 88                |
      | CONS_LATAM   | 76100006   | 6000701   | ACTIVE        | 950   | 10050 | 5975       | EA  | BR16       | 7    | 10       | 95                |
    And I wait "/plan/cns_productcustomer" Async Queue complete

    Given I import "/edm/material_auom_v1" by keyFields "localAuom,sourceSystem,localMaterialNumber"
      | localAuom | localMaterialNumber | localNumerator | localDenominator | materialNumber | sourceSystem |
      | EA        | 87818               | 1              | 24               | 87818          | CONS_LATAM   |
      | CS        | 91838               | 2              | 26               | 91838          | CONS_LATAM   |
      | PL        | 7550641             | 1              | 28               | 7550641        | CONS_LATAM   |
      | EA        | 87819               | 5              | 30               | 87818          | CONS_LATAM   |
      | EA        | 91839               | 6              | 32               | 91838          | CONS_LATAM   |
      | CS        | 7550642             | 2              | 34               | 87818          | CONS_LATAM   |
      | EA        | 6000701             | 8              | 36               | 91838          | CONS_LATAM   |
    And I wait "/edm/material_auom_v1" Async Queue complete

    Given I import "/plan/cns_plan_dem_grp" by keyFields "sourceSystem,demandGroupId,locationId"
      | sourceSystem | demandGroupId | demandGroupDesc | localCurrency | locationId |
      | CONS_LATAM   | 76100009      | demandGroup_09  | BRL           | BR16       |
      | CONS_LATAM   | 76100010      | demandGroup_10  | BRL           | BR07       |
      | CONS_LATAM   | 76100006      | demandGroup_06  | BRL           | BR12       |
    And I wait "/plan/cns_plan_dem_grp" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmProductCustomer.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMProductCustomer.tsv"
    Then I check file data for filename "GDMProductCustomer.tsv" by keyFields "customerId,productId"
#    Then I check region data "/omp/gdm_productcustomer" by keyFields "customerId,productId"

      | customerId | productId             | productStatus | ioq         | moq         | locationId      | norm | leadTime | roundingThreshold | stockLevel  |
      | 76100006   | LA_178962124094540036 | ACTIVE        | 1122.115    | 1070.513    | CONS_LATAM_BR12 | 19   | 19       | 69.500            | 1631.571    |
      | 76100006   | LA_178962124094540500 | ACTIVE        | 2233.333    | 211.111     | CONS_LATAM_BR16 | 7    | 10       | 95.000            | 1327.778    |
      | 76100009   | LA_178962124094540036 | ACTIVE        | 487.628     | 478.846     | CONS_LATAM_BR08 | 8    | 21       | 37.500            | 722.660     |
      | 76100009   | LA_178962124094540099 | INACTIVE      | 1899.706    | 1705.147    | CONS_LATAM_BR19 | 115  | 115      | 85.000            | 2655.000    |
      | 76100010   | LA_178962124094540036 | ACTIVE        | 8.036       | 7.143       | CONS_LATAM_BR08 | 9    | 9        | 67.000            | 11.179      |
      | 76100010   | LA_178962124094540099 | INACTIVE      | 1274.167    | 1216.667    | CONS_LATAM_BR12 | 11   | 250      | 75.000            | 1853.833    |
