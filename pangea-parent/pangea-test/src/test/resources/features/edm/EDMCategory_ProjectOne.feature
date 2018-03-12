@pangea_test @AEAZ-868
Feature: EDMCategory
  # test copy attributes from source system to target system

  Scenario: Full Load curation

    Given I import "/ngems/category_v1" by keyFields "category"
      |category|	categoryName|
      |0003	|Body Care Wash|
      |0004	|Body Cleansing Bar Soap|
      |0006	|Body Care Moisturizing |
      |0021 |Ultra Thin Pads        |
      |0034	|Nursing Pads           |
      |0035	|Other Misc. Baby Care Products|
      |0037	|Baby Hair Care                |

    And I wait "/ngems/category_v1" Async Queue complete

    When I submit task with xml file "xml/edm/EDMCategory_ProjectOne.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/category_v1" by keyFields "category"
      |category|	categoryName|
      |0003	|Body Care Wash|
      |0004	|Body Cleansing Bar Soap|
      |0006	|Body Care Moisturizing |
      |0021 |Ultra Thin Pads        |
      |0034	|Nursing Pads           |
      |0035	|Other Misc. Baby Care Products|
      |0037	|Baby Hair Care                |

    #And I compare the number of records between "/ngems/category_v1" and "/edm/category_v1,/edm/category_v1_failed"

    And I delete the test data

    And I will remove all data with region "/edm/category_v1"