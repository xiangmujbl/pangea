@pangea_test @AEAZ-3689
Feature:  OMPGdmProduct AEAZ-3689

  Scenario: Full Load curation

    And I will remove the test file on sink application "GDMProduct.tsv"

    Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | sourceSystem | localMaterialNumber   | localRefDescription             | localMaterialType | localBaseUom | materialNumber | refDescription                           | materialType | localDpParentCode | parentCode    | globalDpParentCode | form | category | subBrand | brand | franchise | globalBusinessUnit | productFamily | localManufacturingTechnology | manufacturingTechnology | localMaterialGroup | materialGroup | flagForDeletion | materialStatus | division | batchManageIndicator | minRemShelfLife | totalShelfLife | primaryPlanningCode |
      | CONS_LATAM   | 000000000000000016    | JS COTTON BALLS 50 GRX20 T50P35 | FERT              | CRT          | 7891010014803  | J'S SOFT DEO HIDR MAC PROL 12XL400P320ML | FERT         | LDPC01            | 7891010931582 | GDPC01             | 101  | 1001     | 101      | TD001 | FCH001    | GFO001             | AB101         | LMT01                        | Wipes                   | 01                 | MG01          |                 | 08             | 10       | X                    | 180             | 9999           | 1233                |
      | CONS_LATAM   | 000000000000000046    | JS COTTON BALLS 50 GRX20 T50P35 | FERT              | EA           | 7891010014804  | J'S SOFT DEO HIDR MAC PROL 12XL400P320ML | FERT         | LDPC02            | 7891010931582 | GDPC02             | 102  | 1002     | 102      | TD002 | FCH002    | GFO002             | AC102         | LMT02                        | Wipes                   | 02                 | MG02          |                 | 08             | 10       | X                    | 180             | 9999           | 1234                |
      | CONS_LATAM   | 000000000000000076    | JS COTTON BALLS 50 GRX20 T50P35 | FERT              | EA           | 7891010014804  | J'S SOFT DEO HIDR MAC PROL 12XL400P320ML | FERT         | LDPC02            | 7891010931582 | GDPC02             | 102  | 1002     | 102      | TD002 | FCH002    | GFO002             | AC102         | LMT02                        | Wipes                   | 02                 | MG02          |                 | 08             | 10       | X                    | 180             | 9999           | 2563                |

      | CONS_LATAM   | 000000000000000108_J1 | JS COTTON BALLS 50 GRX20 T50P35 | FERT              | EA           | 7891010014804  | J'S SOFT DEO HIDR MAC PROL 12XL400P320ML | FERT         | LDPC02            | 7891010931582 | GDPC02             | 102  | 1002     | 102      | TD002 | FCH002    | GFO002             | AC102         | LMT02                        | Wipes                   | 02                 | MG02          |                 | 08             | 10       | X                    | 180             | 9999           | 1234                |
      | CONS_LATAM   | 000000000000000109_J1 | JS COTTON BALLS 50 GRX20 T50P35 | FERT              | EA           | 7891010014804  | J'S SOFT DEO HIDR MAC PROL 12XL400P320ML | FERT         | LDPC02            | 7891010931582 | GDPC02             | 102  | 1002     | 102      | TD002 | FCH002    | GFO002             | AC102         | LMT02                        | Wipes                   | 02                 | MG02          |                 | 08             | 10       | X                    | 180             | 9999           | 1234                |
      | CONS_LATAM   | 000000000000000110_J1 | JS COTTON BALLS 50 GRX20 T50P35 | FERT              | EA           | 7891010014804  | J'S SOFT DEO HIDR MAC PROL 12XL400P320ML | FERT         |                   | 7891010931582 | GDPC02             | 102  | 1002     | 102      | TD002 | FCH002    | GFO002             | AC102         | LMT02                        | Wipes                   | 02                 | MG02          |                 | 08             | 10       | X                    | 180             | 9999           |                     |
      | CONS_LATAM   | 000000000000000111_J1 | JS COTTON BALLS 50 GRX20 T50P35 | FERT              | EA           | 7891010014804  | J'S SOFT DEO HIDR MAC PROL 12XL400P320ML | FERT         | LDPC02            | 7891010931582 | GDPC02             | 102  | 1002     | 102      | TD002 | FCH002    | GFO002             | AC102         | LMT02                        | Wipes                   | 02                 | MG02          |                 | 08             | 10       | X                    | 180             | 9999           |                     |
      | CONS_LATAM   | 000000000000000112_J1 | JS COTTON BALLS 50 GRX20 T50P35 | FERT              | EA           | 7891010014804  | J'S SOFT DEO HIDR MAC PROL 12XL400P320ML | FERT         |                   | 7891010931582 | GDPC02             | 102  | 1002     | 102      | TD002 | FCH002    | GFO002             | AC102         | LMT02                        | Wipes                   | 02                 | MG02          |                 | 08             | 10       | X                    | 180             | 9999           | 1234                |

      | CONS_LATAM   | 000000000000000113_E3 | JS COTTON BALLS 50 GRX20 T50P35 | FERT              | EA           | 7891010014804  | J'S SOFT DEO HIDR MAC PROL 12XL400P320ML | FERT         | LDPC02            | 7891010931582 | GDPC02             |      | 1002     | 102      | TD002 | FCH002    | GFO002             | AC102         | LMT02                        | Wipes                   | 02                 | MG02          |                 | 08             | 10       | X                    | 180             | 9999           | 1234                |
      | CONS_LATAM   | 000000000000000114_E4 | JS COTTON BALLS 50 GRX20 T50P35 | FERT              | EA           | 7891010014804  | J'S SOFT DEO HIDR MAC PROL 12XL400P320ML | FERT         | LDPC02            | 7891010931582 | GDPC02             | 102  |          | 102      | TD002 | FCH002    | GFO002             | AC102         | LMT02                        | Wipes                   | 02                 | MG02          |                 | 08             | 10       | X                    | 180             | 9999           | 1234                |
      | CONS_LATAM   | 000000000000000115_E5 | JS COTTON BALLS 50 GRX20 T50P35 | FERT              | EA           | 7891010014804  | J'S SOFT DEO HIDR MAC PROL 12XL400P320ML | FERT         | LDPC02            | 7891010931582 | GDPC02             | 102  | 1002     |          | TD002 | FCH002    | GFO002             | AC102         | LMT02                        | Wipes                   | 02                 | MG02          |                 | 08             | 10       | X                    | 180             | 9999           | 1234                |
      | CONS_LATAM   | 000000000000000116_E6 | JS COTTON BALLS 50 GRX20 T50P35 | FERT              | EA           | 7891010014804  | J'S SOFT DEO HIDR MAC PROL 12XL400P320ML | FERT         | LDPC02            | 7891010931582 | GDPC02             | 102  | 1002     | 102      |       | FCH002    | GFO002             | AC102         | LMT02                        | Wipes                   | 02                 | MG02          |                 | 08             | 10       | X                    | 180             | 9999           | 1234                |
      | CONS_LATAM   | 000000000000000117_E7 | JS COTTON BALLS 50 GRX20 T50P35 | FERT              | EA           | 7891010014804  | J'S SOFT DEO HIDR MAC PROL 12XL400P320ML | FERT         | LDPC02            | 7891010931582 | GDPC02             | 102  | 1002     | 102      | TD002 |           | GFO002             | AC102         | LMT02                        | Wipes                   | 02                 | MG02          |                 | 08             | 10       | X                    | 180             | 9999           | 1234                |
      | CONS_LATAM   | 000000000000000118_E8 | JS COTTON BALLS 50 GRX20 T50P35 | FERT              | EA           | 7891010014804  | J'S SOFT DEO HIDR MAC PROL 12XL400P320ML | FERT         | LDPC02            | 7891010931582 | GDPC02             | 102  | 1002     | 102      | TD002 | FCH002    |                    | AC102         | LMT02                        | Wipes                   | 02                 | MG02          |                 | 08             | 10       | X                    | 180             | 9999           | 1234                |
      | CONS_LATAM   | 000000000000000119_E9 | JS COTTON BALLS 50 GRX20 T50P35 | FERT              |              | 7891010014804  | J'S SOFT DEO HIDR MAC PROL 12XL400P320ML | FERT         | LDPC02            | 7891010931582 | GDPC02             | 102  | 1002     | 102      | TD002 | FCH002    | GFO002             | AC102         | LMT02                        | Wipes                   | 02                 | MG02          |                 | 08             | 10       | X                    | 180             | 9999           | 1234                |
    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/plan/cns_material_plan_status" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber   | localPlant | materialNumber | localParentCode | ppc | active | dpRelevant | spRelevant | parentActive | noPlanRelevant |
      | CONS_LATAM   | 000000000000000016    | BR011      | 9862           | G3a             | G4a | X      | X          |            | X            |                |
      | CONS_LATAM   | 000000000000000046    | BR021      | 9864           | G3b             | G4b | X      | X          | X          | X            |                |
      | CONS_LATAM   | 000000000000000076    | BR021      | 9864           | G3b             | G4b | X      | X          |            | X            | X              |

      | CONS_LATAM   | 000000000000000109_J1 | BR031      |                |                 |     | X      |            |            | X            |                |
      | CONS_LATAM   | 000000000000000110_J1 | BR031      |                |                 |     | X      | X          |            | X            | X              |
      | CONS_LATAM   | 000000000000000111_J1 | BR031      |                |                 |     | X      |            | X          | X            |                |
      | CONS_LATAM   | 000000000000000112_J1 | BR031      |                |                 |     | X      | X          |            |              |                |

      | CONS_LATAM   | 000000000000000113_E3 | BR031      |                |                 |     | X      | X          |            |              |                |
      | CONS_LATAM   | 000000000000000114_E4 | BR031      |                |                 |     | X      | X          |            |              |                |
      | CONS_LATAM   | 000000000000000115_E5 | BR031      |                |                 |     | X      | X          |            |              |                |
      | CONS_LATAM   | 000000000000000116_E6 | BR031      |                |                 |     | X      | X          |            |              |                |
      | CONS_LATAM   | 000000000000000117_E7 | BR031      |                |                 |     | X      | X          |            |              |                |
      | CONS_LATAM   | 000000000000000118_E8 | BR031      |                |                 |     | X      | X          |            |              |                |
      | CONS_LATAM   | 000000000000000119_E9 | BR031      |                |                 |     | X      | X          |            |              |                |
    And I wait "/plan/cns_material_plan_status" Async Queue complete

    And I import "/plan/cns_plan_parameter" by keyFields "sourceSystem,dataObject,attribute,parameter"
      | sourceSystem | dataObject  | attribute  | parameter | inclExcl | parameterValue |
      | CONS_LATAM   | SEND_TO_OMP | CONS_LATAM |           |          | LA             |

    And I wait "/plan/cns_plan_parameter" Async Queue complete

    Given I import "/edm/product_family_v1" by keyFields "productFamily"
      | productFamily | productFamilyName |
      | AB101         | Acuvue Bifocal    |
      | AC102         | Acuvue 2          |
      | AF103         | Affinitive        |
      | AM104         | Ambi              |
    And I wait "/edm/product_family_v1" Async Queue complete

    Given I import "/edm/form_v1" by keyFields "formName"
      | formName | formDescription         |
      | 101      | C&C ACNE                |
      | 102      | C&C CLEANSERS           |
      | 103      | C&C FACIAL MOISTURIZERS |
      | 104      | SUNDOWN SUNCARE REGULAR |
    And I wait "/edm/form_v1" Async Queue complete

    Given I import "/edm/category_v1" by keyFields "category"
      | category | categoryName            |
      | 1001     | Body Care Wash          |
      | 1002     | Body Cleansing Bar Soap |
      | 1003     | Body Care Moisturizing  |
      | 1004     | Ultra Thin Pads         |
    And I wait "/edm/category_v1" Async Queue complete

    Given I import "/edm/sub_brand_v1" by keyFields "subBrand"
      | subBrand | subBrandDescription          |
      | 101      | OGX                          |
      | 102      | Neostrata                    |
      | 103      | Internal Sanitary Protection |
      | 104      | Baby Bar Soaps               |
    And I wait "/edm/sub_brand_v1" Async Queue complete

    Given I import "/edm/brand_v1" by keyFields "brand"
      | brand | brandDescription |
      | TD001 | JOHNSONS ADULT   |
      | TD002 | JOHNSONS BABY    |
      | TD003 | RoC              |
    And I wait "/edm/brand_v1" Async Queue complete

    Given I import "/edm/franchise_v1" by keyFields "franchise"
      | franchise | franchiseDescription |
      | FCH001    | BABY CARE            |
      | FCH002    | BEAUTY               |
      | FCH003    | ORAL & TOPICAL       |
    And I wait "/edm/franchise_v1" Async Queue complete

    Given I import "/edm/global_business_unit_v1" by keyFields "gbu"
      | gbu    | gbuName  |
      | GFO001 | SKINCARE |
      | GFO002 | CHC      |
      | GFO003 | OTC      |
    And I wait "/edm/global_business_unit_v1" Async Queue complete

    Given I import "/plan/cns_plan_unit" by keyFields "sourceSystem,localUom"
      | sourceSystem | localUom | localUomName | planFlag | unit |
      | CONS_LATAM   | CRT      | Crate        | DPSP     | CRTU |
      | CONS_LATAM   | EA       | Each         | DPSP     | EAU  |
      | CONS_LATAM   | KG       | KiloGram     | SP       | KGU  |
    And I wait "/plan/cns_plan_unit" Async Queue complete

    Given I import "/plan/cns_root_description" by keyFields "sourceSystem,localDpParentCode"
      | sourceSystem | localDpParentCode | rootDesc                                 | ovrRootDesc                              |
      | CONS_LATAM   | LDPC01            | J'S SOFT DEO HIDR MAC PROL 12XL400P320ML | J'S SOFT DEO                             |
      | CONS_LATAM   | LDPC02            | J'S SOFT DEO HIDR MAC PROL 12XL400P320ML | J'S SOFT DEO HIDR MAC PROL 12XL400P320ML |
    And I wait "/plan/cns_root_description" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmProduct.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMProduct.tsv"

    Then I check file data for filename "GDMProduct.tsv" by keyFields "productId"
#    Then I check region data "/omp/gdm_product" by keyFields "productId"
      | planningHierarchy4Desc | color | description                              | planningHierarchy3Desc  | activeOPRERP | activeSOPERP | planningHierarchy5Desc | unitId | planningHierarchy6 | planningHierarchy7 | planningHierarchy4 | planningHierarchy5 | planningHierarchy2 | planningHierarchy6Desc | productId | planningHierarchy3 | planningHierarchy1 | active | planningHierarchy7Desc | shortDescription                         | technology | sourceLocationId | planningHierarchy1Desc | activeFCTERP | planningHierarchy2Desc | subFranchise |
      | Neostrata              |       | J'S SOFT DEO HIDR MAC PROL 12XL400P320ML | Body Cleansing Bar Soap | YES          | NO           | JOHNSONS BABY          | EAU    | FCH002             | GFO002             | 102                | TD002              | 102                | BEAUTY                 | 2563      | 1002               | AC102              | YES    | CHC                    | J'S SOFT DEO HIDR MAC PROL 12XL400P320ML | LMT02      |                  | Acuvue 2               | YES          | C&C CLEANSERS          |              |
      | Neostrata              |       | J'S SOFT DEO HIDR MAC PROL 12XL400P320ML | Body Cleansing Bar Soap | NO           | NO           | JOHNSONS BABY          | EAU    | FCH002             | GFO002             | 102                | TD002              | 102                | BEAUTY                 | LA_LDPC02 | 1002               | AC102              | YES    | CHC                    | J'S SOFT DEO HIDR MAC PROL 12XL400P320ML | LMT02      |                  | Acuvue 2               | YES          | C&C CLEANSERS          |              |
      |                        |       | J'S SOFT DEO HIDR MAC PROL 12XL400P320ML |                         | NO           | NO           |                        | EAU    | FCH002             | GFO002             | 102                | TD002              | 102                |                        | LA_LDPC02 | 1002               | AC102              | YES    |                        | J'S SOFT DEO HIDR MAC PROL 12XL400P320ML | LMT02      |                  |                        | YES          |                        |              |
      | Neostrata              |       | J'S SOFT DEO HIDR MAC PROL 12XL400P320ML | Body Cleansing Bar Soap | NO           | NO           | JOHNSONS BABY          | EAU    | FCH002             | GFO002             | 102                | TD002              | 102                | BEAUTY                 | 1234      | 1002               | AC102              | YES    | CHC                    | J'S SOFT DEO HIDR MAC PROL 12XL400P320ML | LMT02      |                  | Acuvue 2               | YES          | C&C CLEANSERS          |              |
      | OGX                    |       | J'S SOFT DEO HIDR MAC PROL 12XL400P320ML | Body Care Wash          | NO           | NO           | JOHNSONS ADULT         | CRTU   | FCH001             | GFO001             | 101                | TD001              | 101                | BABY CARE              | 1233      | 1001               | AB101              | YES    | SKINCARE               | J'S SOFT DEO HIDR MAC PROL 12XL400P320ML | LMT01      |                  | Acuvue Bifocal         | YES          | C&C ACNE               |              |
      | OGX                    |       | J'S SOFT DEO                             | Body Care Wash          | NO           | NO           | JOHNSONS ADULT         | CRTU   | FCH001             | GFO001             | 101                | TD001              | 101                | BABY CARE              | LA_LDPC01 | 1001               | AB101              | YES    | SKINCARE               | J'S SOFT DEO                             | LMT01      |                  | Acuvue Bifocal         | YES          | C&C ACNE               |              |
      | Neostrata              |       | J'S SOFT DEO HIDR MAC PROL 12XL400P320ML | Body Cleansing Bar Soap | YES          | NO           | JOHNSONS BABY          | EAU    | FCH002             | GFO002             | 102                | TD002              | 102                | BEAUTY                 | 1234      | 1002               | AC102              | YES    | CHC                    | J'S SOFT DEO HIDR MAC PROL 12XL400P320ML | LMT02      |                  | Acuvue 2               | YES          | C&C CLEANSERS          |              |
      | Neostrata              |       | J'S SOFT DEO HIDR MAC PROL 12XL400P320ML | Body Cleansing Bar Soap | NO           | NO           | JOHNSONS BABY          | EAU    | FCH002             | GFO002             | 102                | TD002              | 102                | BEAUTY                 | LA_LDPC02 | 1002               | AC102              | YES    | CHC                    | J'S SOFT DEO HIDR MAC PROL 12XL400P320ML | LMT02      |                  | Acuvue 2               | YES          | C&C CLEANSERS          |              |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID   | errorCode | sourceSystem | businessArea | key1                  | key2 | key3 | key4 | key5 | errorValue                                                      |
      | SP             | OMPGdmProduct | J1        | CONS_LATAM   |              | 000000000000000108_J1 |      |      |      |      | Unable to find DPParentCode                                     |
      | SP             | OMPGdmProduct | J1        | CONS_LATAM   |              | 000000000000000109_J1 |      |      |      |      | Unable to find DPParentCode                                     |

      | SP             | OMPGdmProduct | J1        | CONS_LATAM   |              | 000000000000000110_J1 |      |      |      |      | Unable to find DPParentCode                                     |
#      | SP             | OMPGdmProduct | J1        | CONS_LATAM   |              | 000000000000000111_J1 |      |      |      |      | Unable to find DPParentCode                                     |
#      | SP             | OMPGdmProduct | J1        | CONS_LATAM   |              | 000000000000000112_J1 |      |      |      |      | Unable to find DPParentCode                                     |

      | SP             | OMPGdmProduct | E3        | CONS_LATAM   |              | 000000000000000113_E3 |      |      |      |      | There is no Form assigned for product                           |
      | SP             | OMPGdmProduct | E4        | CONS_LATAM   |              | 000000000000000114_E4 |      |      |      |      | There is no Category assigned for product                       |
      | SP             | OMPGdmProduct | E5        | CONS_LATAM   |              | 000000000000000115_E5 |      |      |      |      | There is no subBrand assigned for product                       |
      | SP             | OMPGdmProduct | E6        | CONS_LATAM   |              | 000000000000000116_E6 |      |      |      |      | There is no brand assigned for product                          |
      | SP             | OMPGdmProduct | E7        | CONS_LATAM   |              | 000000000000000117_E7 |      |      |      |      | There is no franchise assigned for product                      |
      | SP             | OMPGdmProduct | E8        | CONS_LATAM   |              | 000000000000000118_E8 |      |      |      |      | There is no globalBusinessUnit assigned for product             |
      | SP             | OMPGdmProduct | E9        | CONS_LATAM   |              | 000000000000000119_E9 |      |      |      |      | No Plannable Enterprise UOM has been assigned to the local Unit |

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/omp/gdm_product"

    And I will remove all data with region "/plan/edm_failed_data"
