@pangea_test @AEAZ-6098
Feature:  OMPGdmProduct AEAZ-6098

  Background:delete all test data

    And I will remove all data with region "/edm/material_global_v1"
    And I will remove all data with region "/plan/cns_productcustomer"
    And I will remove all data with region "/edm/material_auom_v1"
    And I will remove all data with region "/plan/edm_failed_data"
    And I will remove all data with region "/omp/gdm_productcustomer"

  @Scenario1
  Scenario: check rule J1 , T1 and J2
  T1:
  >> line1: localDpParentCode is empty  add fail data
  J1:
  >> line2:material_global_v1.sourceSystem        not equal cns_productcustomer.sourceSystem add fail data
  >> line3:material_global_v1.localMaterialNumber not equal cns_productcustomer.productId  add fail data
  J2:
  >>line4:/edm/material_auom_v1.sourceSystem not equals  cns_productcustomer.sourceSystem add fail data
  >>line5:/edm/material_auom_v1.localMaterialNumber not equals  cns_productcustomer.productId add fail data
  >>line6:/edm/material_auom_v1.localAuom not equals  cns_productcustomer.uom add fail data
    Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | sourceSystem | localMaterialNumber | localDpParentCode |
      | CONS_LATAM   | 000000000000000001  |                   |
      | CONS_LATAM   | 000000000000000002  | LDPC02            |
      | CONS_LATAM   | 000000000000000003  | LDPC03            |
      | CONS_LATAM   | 000000000000000004  | LDPC04            |
      | CONS_LATAM   | 000000000000000005  | LDPC05            |
      | CONS_LATAM   | 000000000000000006  | LDPC06            |

    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/plan/cns_productcustomer" by keyFields "demandGroup,productId,sourceSystem"

      | sourceSystem  | productId             | demandGroup | productStatus | minStock | maxStock | stockLevel | uom | preferredDC | leadTime | revenueRecognitionOffset |
      | CONS_LATAM    | 000000000000000001    | 76100009    | ACTIVE        | 10200    | 10300    | 15350      | EA  | BR08        | 7        | 7                        |
      | CONS_LATAM_J1 | 000000000000000002    | 76100009    | INACTIVE      | 700      | 760      | 1080       | CS  | BR08        | 8        | 21                       |
      | CONS_LATAM    | 000000000000000003_J1 | 76100010    | ACTIVE        | 200      | 225      | 313        | PL  | BR08        | 9        | 9                        |
      | CONS_LATAM    | 000000000000000004    | 76100009    | INACTIVE      | 700      | 760      | 1080       | CS  | BR08        | 8        | 21                       |
      | CONS_LATAM    | 000000000000000005    | 76100009    | INACTIVE      | 700      | 760      | 1080       | CS  | BR08        | 8        | 21                       |
      | CONS_LATAM    | 000000000000000006    | 76100009    | INACTIVE      | 700      | 760      | 1080       | CS  | BR08        | 8        | 21                       |

    And I wait "/plan/cns_productcustomer" Async Queue complete

    Given I import "/edm/material_auom_v1" by keyFields "localAuom,sourceSystem,localMaterialNumber"

      | localAuom | localDenominator | localMaterialNumber   | localNumerator | materialNumber        | numerator | sourceSystem  |
      | EA        | 24               | 000000000000000001    | 1              | 000000000000000001    |           | CONS_LATAM    |
      | CS        | 26               | 000000000000000002    | 2              | 000000000000000002    |           | CONS_LATAM    |
      | PL        | 28               | 000000000000000003    | 1              | 000000000000000003    |           | CONS_LATAM    |
      | CS        | 26               | 000000000000000004    | 2              | 000000000000000004    |           | CONS_LATAM_J2 |
      | CS        | 26               | 000000000000000005_J2 | 2              | 000000000000000005_J2 |           | CONS_LATAM    |
      | CS_J2     | 26               | 000000000000000006    | 2              | 000000000000000006    |           | CONS_LATAM    |

    And I wait "/edm/material_auom_v1" Async Queue complete


    When I submit task with xml file "xml/omp/OMPGdmProductCustomer.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/plan/edm_failed_data" by keyFields "errorCode,functionalArea,interfaceID,key1,key2,key3,key4,key5,sourceSystem"
      | errorCode | functionalArea | interfaceID        | key1               | key2       | key3 | key4 | key5 | sourceSystem | errorValue                                   |
      | J1        | DP             | GDMProductCustomer | 000000000000000002 |  |      |      |      | CONS_LATAM   | ProductId do not exist in edm material       |
      | J1        | DP             | GDMProductCustomer | 000000000000000003 |  |      |      |      | CONS_LATAM   | ProductId do not exist in edm material       |
      | T1        | DP             | GDMProductCustomer | 000000000000000001 |  |      |      |      | CONS_LATAM   | localDpParentCode is missing in edm material |
      | J2        | DP             | GDMProductCustomer | 000000000000000004 | |      |      |      | CONS_LATAM   | uom do not exist in edm auom                 |
      | J2        | DP             | GDMProductCustomer | 000000000000000005 |  |      |      |      | CONS_LATAM   | uom do not exist in edm auom                 |
      | J2        | DP             | GDMProductCustomer | 000000000000000006 |  |      |      |      | CONS_LATAM   | uom do not exist in edm auom                 |

  @Scenario2
  Scenario: check rule T3,T4,T5,T6,T7,T8
  T4,T5,T6:
  >>Aggregation by localDpparentCode  to calculated
  T7,T8:
  >>Aggregation by localDpparentCode  get largest value
  T3:
  >> mapping  already  check in J1 (@Scenario1)
  if productStatus is  ACTIVE set  ProductStatus value is ACTIVE else INACTIVE
    Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | sourceSystem | localMaterialNumber | localDpParentCode |
      | CONS_LATAM   | 000000000000000001  | LDPC01            |
      | CONS_LATAM   | 000000000000000002  | LDPC01            |
      | CONS_LATAM   | 000000000000000003  | LDPC01            |

    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/plan/cns_productcustomer" by keyFields "demandGroup,productId,sourceSystem"

      | sourceSystem | productId          | demandGroup | productStatus | minStock | maxStock | stockLevel | uom | preferredDC | leadTime | revenueRecognitionOffset |
      | CONS_LATAM   | 000000000000000001 | 76100001    | ACTIVE        | 1        | 1        | 5          | CS  | BR01        | 1        | 1                        |
      | CONS_LATAM   | 000000000000000001 | 76100002    | INACTIVE      | 2        | 2        | 4          | CS  | BR02        | 2        | 2                        |
      | CONS_LATAM   | 000000000000000001 | 76100003    | ACTIVE        | 3        | 3        | 3          | CS  | BR03        | 3        | 3                        |
      | CONS_LATAM   | 000000000000000002 | 76100004    | INACTIVE      | 4        | 4        | 2          | CS  | BR04        | 4        | 4                        |
      | CONS_LATAM   | 000000000000000003 | 76100005    | INACTIVE      | 5        | 5        | 1          | CS  | BR05        | 5        | 5                        |
    And I wait "/plan/cns_productcustomer" Async Queue complete

    Given I import "/edm/material_auom_v1" by keyFields "localAuom,sourceSystem,localMaterialNumber"

      | localAuom | localDenominator | localMaterialNumber | localNumerator | materialNumber     | numerator | sourceSystem |
      | CS        | 1                | 000000000000000001  | 1              | 000000000000000001 |           | CONS_LATAM   |
      | CS        | 10               | 000000000000000002  | 20             | 000000000000000002 |           | CONS_LATAM   |
      | CS        | 100              | 000000000000000003  | 30             | 000000000000000003 |           | CONS_LATAM   |

    And I wait "/edm/material_auom_v1" Async Queue complete
    When I submit task with xml file "xml/omp/OMPGdmProductCustomer.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMProductCustomer.tsv"

    Then I check file data for filename "GDMProductCustomer.tsv" by keyFields "productCustomerId"

#    Then I check region data "/omp/gdm_productcustomer" by keyFields "productCustomerId"

      | productCustomerId     | productStatus | minStock  | maxStock  | preferredDC | leadTime | revenueRecognitionOffset | stockLevel |
      | LA_LDPC0176100001BR01 | ACTIVE        | 15.500000 | 15.500000 | BR01        | 5        | 5                        | 16.300000  |
      | LA_LDPC0176100002BR02 | INACTIVE      | 15.500000 | 15.500000 | BR02        | 5        | 5                        | 16.300000  |
      | LA_LDPC0176100003BR03 | ACTIVE        | 15.500000 | 15.500000 | BR03        | 5        | 5                        | 16.300000  |
      | LA_LDPC0176100004BR04 | INACTIVE      | 15.500000 | 15.500000 | BR04        | 5        | 5                        | 16.300000  |
      | LA_LDPC0176100005BR05 | INACTIVE      | 15.500000 | 15.500000 | BR05        | 5        | 5                        | 16.300000  |

  @Scenario3
  Scenario: Scenario_Input_Output
  the data come from DOMD_OMP_GDMProductCustomer.xlsx sheet Scenario_Input_Output
    Given I import "/plan/cns_productcustomer" by keyFields "demandGroup,productId,sourceSystem"

      | sourceSystem | productId | demandGroup | productStatus | minStock | maxStock | stockLevel | uom | preferredDC | leadTime | revenueRecognitionOffset |
      | CONS_LATAM   | 87818     | 76100009    | INACTIVE        | 10200    | 10300    | 15350      | EA  | BR08        | 7        | 7                        |
      | CONS_LATAM   | 91838     | 76100009    | INACTIVE      | 700      | 760      | 1080       | CS  | BR08        | 8        | 21                       |
      | CONS_LATAM   | 7550641   | 76100010    | ACTIVE        | 200      | 225      | 313        | PL  | BR08        | 9        | 9                        |
      | CONS_LATAM   | 87818     | 76100006    | ACTIVE        | 10000    | 10500    | 15250      | EB  | BR08        | 19       | 19                       |
      | CONS_LATAM   | 91838     | 76100006    | ACTIVE        | 8500     | 8900     | 12950      | EA  | BR08        | 6        | 6                        |
      | CONS_LATAM   | 87818     | 76100010    | INACTIVE      | 7300     | 7645     | 11123      | EC  | BR12        | 11       | 250                      |
      | CONS_LATAM   | 91838     | 76100010    | INACTIVE      | 9000     | 10000    | 14000      | EB  | BR12        | 51       | 51                       |
      | CONS_LATAM   | 7550641   | 76100009    | INACTIVE      | 300      | 420      | 510        | CS  | BR12        | 115      | 115                      |
      | CONS_LATAM   | 6000700   | 76100006    | ACTIVE        | 950      | 10050    | 5975       | EA  | BR16        | 7        | 10                       |
      | CONS_LATAM   | 95299     | 76100010    | ACTIVE        | 500      | 750      | 875        | CS  | BR08        | 9        | 8                        |
    And I wait "/plan/cns_productcustomer" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | sourceSystem | localMaterialNumber | localDpParentCode  |
      | CONS_LATAM   | 87818               | 178962124094540036 |
      | CONS_LATAM   | 91838               | 178962124094540036 |
      | CONS_LATAM   | 7550641             | 178962124094540037 |
      | CONS_LATAM   | 6000700             | 178962124094540038 |

    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/edm/material_auom_v1" by keyFields "localAuom,sourceSystem,localMaterialNumber"

      | localAuom | localDenominator | localMaterialNumber | localNumerator | materialNumber | numerator | sourceSystem |
      | EA        | 24               | 87818               | 1              | 87818          |           | CONS_LATAM   |
      | CS        | 26               | 91838               | 2              | 91838          |           | CONS_LATAM   |
      | PL        | 28               | 7550641             | 1              | 7550641        |           | CONS_LATAM   |
      | EB        | 30               | 87818               | 5              | 87818          |           | CONS_LATAM   |
      | EA        | 32               | 91838               | 6              | 91838          |           | CONS_LATAM   |
      | EC        | 34               | 87818               | 2              | 87818          |           | CONS_LATAM   |
      | EB        | 36               | 91838               | 8              | 91838          |           | CONS_LATAM   |
      | CS        | 38               | 7550641             | 1              | 7550641        |           | CONS_LATAM   |
      | EA        | 40               | 6000700             | 1              | 6000700        |           | CONS_LATAM   |
      | CS        | 42               | 95299               | 1              | 95299          |           | CONS_LATAM   |

    And I wait "/edm/material_auom_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmProductCustomer.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMProductCustomer.tsv"

    Then I check file data for filename "GDMProductCustomer.tsv" by keyFields "productCustomerId"

#    Then I check region data "/omp/gdm_productcustomer" by keyFields "productCustomerId"

      | productCustomerId                 | productStatus | minStock    | maxStock    | preferredDC | leadTime | revenueRecognitionOffset | stockLevel  |
      | LA_17896212409454003676100006BR08 | ACTIVE        | 6168.674585 | 6578.306310 | BR08        | 51       | 250                      | 9457.857152 |
      | LA_17896212409454003676100009BR08 | INACTIVE      | 6168.674585 | 6578.306310 | BR08        | 51       | 250                      | 9457.857152 |
      | LA_17896212409454003676100010BR12 | INACTIVE      | 6168.674585 | 6578.306310 | BR12        | 51       | 250                      | 9457.857152 |
      | LA_17896212409454003776100009BR12 | INACTIVE      | 15.037594   | 19.088346   | BR12        | 115      | 115                      | 24.599624   |
      | LA_17896212409454003776100010BR08 | ACTIVE        | 15.037594   | 19.088346   | BR08        | 115      | 115                      | 24.599624   |
      | LA_17896212409454003876100006BR16 | ACTIVE        | 23.750000   | 251.250000  | BR16        | 7        | 10                       | 149.375000  |
