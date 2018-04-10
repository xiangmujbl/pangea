@pangea_test
Feature:  OMPGdmProduct-Curation

  Scenario: Full Load curation

    Given I import "/edm/material_global_v1" by keyFields ""
      | localDPParentCode | primaryPlanningCode | refDescription | refDescription | materialGroup | productFamily | form  | category | subBrand | brand | franchise | globalBusinessUnit | refDescription | localManufacturingTechnology |
    And I wait "/edm/material_global_v1" Async Queue complete
    Given I import "/plan/cns_material_plan_status" by keyFields ""
      | sourceSystem | localMaterialNumber | localPlant | materialNumber | localParentCode | ppc | active | dpRelevant | spRelevant | parentActive | noPlanRelevant |
    And I wait "/plan/cns_material_plan_status" Async Queue complete
    Given I import "/edm/product_family_v1" by keyFields ""
      | productFamily | productFamilyName |
    And I wait "/edm/product_family_v1" Async Queue complete
    Given I import "/edm/form_v1" by keyFields ""
      | formName | formDescription |
    And I wait "/edm/form_v1" Async Queue complete
    Given I import "/edm/category_v1" by keyFields ""
      | category | categoryName |
    And I wait "/edm/category_v1" Async Queue complete
    Given I import "/edm/sub_brand_v1" by keyFields ""
      | subBrand | subBrandDescription |
    And I wait "/edm/sub_brand_v1" Async Queue complete
    Given I import "/edm/brand_v1" by keyFields ""
      | brand | brandDescription |
    And I wait "/edm/brand_v1" Async Queue complete
    Given I import "/edm/franchise_v1" by keyFields ""
      | franchise | franchiseDescription |
    And I wait "/edm/franchise_v1" Async Queue complete
    Given I import "/edm/global_base_unit_v1" by keyFields ""
      | gbu | gbuName |
    And I wait "/edm/global_base_unit_v1" Async Queue complete
    Given I import "/plan/cns_plan_unit" by keyFields ""
      | sourceSystem | localUom | localUomName | plantFlag | unit |
    And I wait "/plan/cns_plan_unit" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmProduct.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "gdmProduct.tsv"

    Then I check data for filename "gdmProduct.tsv" by keyFields ""
      | productId | active | activeFcterp | activeOprerp | activeSoperp | color | description | groes | isroot | issku | label | matkl | planningHierarchy1 | planningHierarchy1Desc | planningHierarchy2 | planningHierarchy2Desc | planningHierarchy3 | planningHierarchy3Desc | planningHierarchy4 | planningHierarchy4Desc | planningHierarchy5 | planningHierarchy5Desc | planningHierarchy6 | planningHierarchy6Desc | planningHierarchy7 | planningHierarchy7Desc | prdha | shortDescription | sourceLocationId | subFranchise | technology | unitId |

    Then I check region data "/omp/product" by keyFields ""
      | productId | active | activeFcterp | activeOprerp | activeSoperp | color | description | groes | isroot | issku | label | matkl | planningHierarchy1 | planningHierarchy1Desc | planningHierarchy2 | planningHierarchy2Desc | planningHierarchy3 | planningHierarchy3Desc | planningHierarchy4 | planningHierarchy4Desc | planningHierarchy5 | planningHierarchy5Desc | planningHierarchy6 | planningHierarchy6Desc | planningHierarchy7 | planningHierarchy7Desc | prdha | shortDescription | sourceLocationId | subFranchise | technology | unitId |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/edm/material_global_v1" and "/omp/product,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/omp/product"

    And I will remove all data with region "/edm/material_global_v1"

    And I will remove the test file on sink application "gdmProduct.tsv"

