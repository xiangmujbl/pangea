@pangea_test
Feature:  OMPGdmProduct-Curation

  Scenario: Full Load curation

    And I will remove the test file on sink application "GDMProduct.tsv"

    Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | sourceSystem | localMaterialNumber | localRefDescription             | localMaterialType | localBaseUom | materialNumber | refDescription                           | materialType | localDpParentCode | parentCode    | globalDpParentCode | form | category | subBrand | brand | franchise | globalBusinessUnit | productFamily | localManufacturingTechnology | manufacturingTechnology | localMaterialGroup | materialGroup | flagForDeletion | materialStatus | division | batchManageIndicator | minRemShelfLife | totalShelfLife | primaryPlanningCode |
      | CONS_LATAM   | BR01                | JS COTTON BALLS 50 GRX20 T50P35 | FERT              | CRT          | 7891010014803  | J'S SOFT DEO HIDR MAC PROL 12XL400P320ML | FERT         | LDPC01            | 7891010931582 | GDPC01             | 101  | 1001     | 101      | TD001 | FCH001    | GFO001             | AB101         | LMT01                        | Wipes                   | 01                 | MG01          |                 | 08             | 10       | X                    | 180             | 9999           | 1233                |
      | CONS_LATAM   | BR02                | JS COTTON BALLS 50 GRX20 T50P35 | FERT              | EA           | 7891010014804  | J'S SOFT DEO HIDR MAC PROL 12XL400P320ML | FERT         | LDPC02            | 7891010931582 | GDPC02             | 102  | 1002     | 102      | TD002 | FCH002    | GFO002             | AC102         | LMT02                        | Wipes                   | 02                 | MG02          |                 | 08             | 10       | X                    | 180             | 9999           | 1234                |
    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/plan/cns_material_plan_status" by keyFields "sourceSystem,localMaterialNumber,localPlant"
      | sourceSystem | localMaterialNumber | localPlant | materialNumber | localParentCode | ppc | active | dpRelevant | spRelevant | parentActive | noPlanRelevant |
      | CONS_LATAM   | BR01                | BR011      | 9862           | G3a             | G4a | X      | X          |            | X            |                |
      | CONS_LATAM   | BR02                | BR021      | 9864           | G3b             | G4b | X      | X          | X          | X            | X              |
      | CONS_LATAM   | BR03                | BR031      |                |                 |     |        |            |            |              | X              |
    And I wait "/plan/cns_material_plan_status" Async Queue complete

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

    When I submit task with xml file "xml/omp/OMPGdmProduct.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMProduct.tsv"

    Then I check file data for filename "GDMProduct.tsv" by keyFields "productId"
      | productId         | active | activeFCTERP | activeOPRERP | activeSOPERP | color | description                              | groes | isRoot | isSku | label                                    | matkl | planningHierarchy1 | planningHierarchy1Desc | planningHierarchy2 | planningHierarchy2Desc | planningHierarchy3 | planningHierarchy3Desc  | planningHierarchy4 | planningHierarchy4Desc | planningHierarchy5 | planningHierarchy5Desc | planningHierarchy6 | planningHierarchy6Desc | planningHierarchy7 | planningHierarchy7Desc | prdha | shortDescription                         | sourceLocationId | subFranchise | technology | unitId |
      | 1234              | YES    | YES          | YES          | NO           |       | J'S SOFT DEO HIDR MAC PROL 12XL400P320ML |       |        |       | J'S SOFT DEO HIDR MAC PROL 12XL400P320ML | MG02  | AC102              | Acuvue 2               | 102                | C&C CLEANSERS          | 1002               | Body Cleansing Bar Soap | 102                | Neostrata              | TD002              | JOHNSONS BABY          | FCH002             | BEAUTY                 | GFO002             | CHC                    |       | J'S SOFT DEO HIDR MAC PROL 12XL400P320ML |                  |              | LMT02      | EAU    |
      | CONS_LATAM_LDPC02 | YES    | YES          | YES          | NO           |       | J'S SOFT DEO HIDR MAC PROL 12XL400P320ML |       |        |       | J'S SOFT DEO HIDR MAC PROL 12XL400P320ML | MG02  | AC102              | Acuvue 2               | 102                | C&C CLEANSERS          | 1002               | Body Cleansing Bar Soap | 102                | Neostrata              | TD002              | JOHNSONS BABY          | FCH002             | BEAUTY                 | GFO002             | CHC                    |       | J'S SOFT DEO HIDR MAC PROL 12XL400P320ML |                  |              | LMT02      | EAU    |
      | CONS_LATAM_LDPC01 | YES    | YES          | NO           | NO           |       | J'S SOFT DEO HIDR MAC PROL 12XL400P320ML |       |        |       | J'S SOFT DEO HIDR MAC PROL 12XL400P320ML | MG01  | AB101              | Acuvue Bifocal         | 101                | C&C ACNE               | 1001               | Body Care Wash          | 101                | OGX                    | TD001              | JOHNSONS ADULT         | FCH001             | BABY CARE              | GFO001             | SKINCARE               |       | J'S SOFT DEO HIDR MAC PROL 12XL400P320ML |                  |              | LMT01      | CRTU   |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

#    And I compare the number of records between "/edm/material_global_v1" and "/omp/gdm_product,/plan/edm_failed_data"

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/omp/gdm_product"

    And I will remove all data with region "/plan/edm_failed_data"
