@pangea_test @AEAZ-1815
Feature: OMPGdmProductUnitConversion AEAZ-1815

  Scenario: Full Load curation

    Given I import "/plan/cns_plan_unit" by keyFields "plantFlag,unit,localUom,sourceSystem,localUomName"
      | plantFlag | unit | localUom | sourceSystem | localUomName |
      | CS        | DPSP | CS       | CRT          | CONS_LATAM   |
      | EA        |      | EA       | EA           | CONS_LATAM   |
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

    Then A file is found on sink application with name "GDMProductUnitConversion.tsv"

    Then I check file data for filename "GDMProductUnitConversion.tsv" by keyFields "gdmProductUnitConversionId"
      | gdmProductUnitConversionId | active | activeFCTERP | activeOPRERP | activeSOPERP | factor | productId | unitId |
      | 1DPSP                      | YES    | YES          | YES          | NO           | 0.10   | -         | DPSP   |

    Then I check region data "/plan/edm_failed_data" by keyFields "errorCode,functionalArea,interfaceID,key1,key2,key3,key4,key5,sourceSystem"
#      | functionalArea | interfaceID                            | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue | timeStamp |
#      | SP             | OMPGdmProductUnitConversion            | E1        | omp          |              | 1    |      |      |      |      | No Enterprise UOM is maintained           | |
       |  errorCode |           errorValue            | functionalArea |         interfaceID         | key1 | key2 | key3 | key4 | key5 | sourceSystem |
       |  E1        | No Enterprise UOM is maintained | SP             | OMPGdmProductUnitConversion | 1    |      |      |      |      | omp          |

    And I delete the test data

    And I will remove all data with region "/omp/gdm_product_unit_conversion"

    And I will remove all data with region "/plan/edm_failed_data"

#    And I will remove the test file on sink application "GDMProductUnitConversion.tsv"
