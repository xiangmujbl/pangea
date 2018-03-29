@pangea_test @AEAZ-1243
Feature: AEAZ-1243 OMPGdmProductUnitConversion-Curation

  Scenario: Full Load curation

    Given I import "/plan/cns_plan_unit" by keyFields "plantFlag,unit,localUom,sourceSystem,localUomName"
      | plantFlag | unit | localUom | sourceSystem | localUomName |
      | CS        | DPSP | CS       | CRT          | CONS_LATAM   |
      | EA        | DPSP | EA       | EA           | CONS_LATAM   |
    And I wait "/plan/cns_plan_unit" Async Queue complete

    Given I import "/edm/material_auom_v1" by keyFields "localMaterialNumber,localAuom"
      | localMaterialNumber | localDenominator | localAuom          | localNumerator |
      | 000000000000000257  | 10               | 000000000000000257 | 1              |
      | 000000000000000258  | 13               | 1                  | 1              |
    And I wait "/edm/material_auom_v1" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber"
      | localDpParentCode | localBaseUom | primaryPlanningCode | localMaterialNumber |
      | -                 | CS           | 1                   | 000000000000000257  |
      | -                 | EA           | 1                   | 1                   |
    And I wait "/edm/material_global_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmProductUnitConversion.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/omp/gdm_product_unit_conversion" by keyFields "gdmProductUnitConversionId"
      | gdmProductUnitConversionId | active | activeFCTERP | activeOPRERP | activeSOPERP | factor | productId | unitId |
      | 1DPSP                      | YES    | YES          | YES          | YES          | 0.10    | -         | DPSP   |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |


    And I delete the test data

    And I will remove all data with region "/omp/gdm_product_unit_conversion"

    And I will remove all data with region "/plan/edm_failed_data"

