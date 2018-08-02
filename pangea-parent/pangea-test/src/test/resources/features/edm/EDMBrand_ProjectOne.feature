@pangea_test @AEAZ-8296
Feature: EDMBrand AEAZ-8296
  # test copy attributes from source system to target system

  Scenario: Full Load curation

    Given I import "/infa_mdm/c_lkup_brnd" by keyFields "brand"
	  | brand  | brandDescription |
	  | BRD001 | JOHNSONS ADULT   |
	  | BRD002 | JOHNSONS BABY    |
	  | BRD003 | RoC              |
	  | BRD004 | STAYFREE 		  |
	  | BRD005 | CLEAN CLEAR 	  |
	  | BRD006 | NEUTROGENA 	  |
   	  | BRD007 | CAREFREE 		  |
	  | BRD008 | LISTERINE 		  |
	  | BRD009 | Neostrata 		  |
	  | BRD010 | SUNDOWN 		  |
	  | BRD011 | TYLENOL 		  |
	  | BRD012 | AVEENO 		  |
	  | BRD014 | REACH 			  |
	  | BRD015 | OB 			  |
	  | BRD016 | Band Aid  		  |
	  | BRD021 | NICORETTE 		  |
	  | BRD022 | BENALET 		  |
	  | BRD023 | MYLANTA 		  |
	  | BRD032 | COTONETE 		  |
	  | BRD033 | HIPOGLOS 		  |
	  | BRD034 | OGX 			  |
    And I wait "/infa_mdm/c_lkup_brnd" Async Queue complete

    When I submit task with xml file "xml/edm/EDMBrand_ProjectOne.xml" and execute file "jar/pangea-view.jar"
    
    And wait 5000 millisecond

    Then I check region data "/edm/brand_v1" by keyFields "brand"
	  | brand  | brandDescription |
	  | BRD001 | JOHNSONS ADULT   |
	  | BRD002 | JOHNSONS BABY    |
	  | BRD003 | RoC              |
	  | BRD004 | STAYFREE 		  |
	  | BRD005 | CLEAN CLEAR 	  |
	  | BRD006 | NEUTROGENA 	  |
   	  | BRD007 | CAREFREE 		  |
	  | BRD008 | LISTERINE 		  |
	  | BRD009 | Neostrata 		  |
	  | BRD010 | SUNDOWN 		  |
	  | BRD011 | TYLENOL 		  |
	  | BRD012 | AVEENO 		  |
	  | BRD014 | REACH 			  |
	  | BRD015 | OB 			  |
	  | BRD016 | Band Aid  		  |
	  | BRD021 | NICORETTE 		  |
	  | BRD022 | BENALET 		  |
	  | BRD023 | MYLANTA 		  |
	  | BRD032 | COTONETE 		  |
	  | BRD033 | HIPOGLOS 		  |
	  | BRD034 | OGX 			  |
    
    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

  Scenario: delete all test data

    Then I delete the test data
    
    And I will remove all data with region "/edm/brand_v1"
    And I will remove all data with region "/plan/edm_failed_data"
