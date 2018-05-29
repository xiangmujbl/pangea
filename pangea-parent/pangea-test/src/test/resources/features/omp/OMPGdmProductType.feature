@pangea_test @AEAZ-4485
Feature: OMPProductType AEAZ-4485

  Scenario: Full Load consumption
  # Gets the data that the primary key is not empty.
  #  And I will remove the test file on sink application "ProductType.tsv"

    Given I import "/edm/material_type_v1" by keyFields "materialType"
      | materialType | materialTypeName |
      | DIEN         | Service          |
      | FERT         | Finished Product |
      |              | Authumber        |
    And I wait "/edm/material_type_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmProductType.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "ProductType.tsv"

    Then I check file data for filename "ProductType.tsv" by keyFields "productTypeId"
      | productTypeId | activeFCTERP | activeOPRERP | activeSOPERP | label            |
      | DIEN          | YES          | YES          | NO           | Service          |
      | FERT          | YES          | YES          | NO           | Finished Product |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |


  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/omp/gdm_product_type"

    And I will remove all data with region "/plan/edm_failed_data"

