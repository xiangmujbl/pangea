@pangea_test @AEAZ-1763
Feature:  OMPGdmLocationType-Curation

  Scenario: Full Load consumption

  #todo: this test isnt finished. need to figure out how to merge these files..

    #dont know what the source data is, or the region
#    Given I import "/plan/cns_loc_type" by keyFields "planLocTypeId"
    Given I import "/plan/cns_tlane_item" by keyFields "sequenceNumber,tlaneName"
      | validTo	| originLocation	| materialNumber	| processTypeId	| validFrom	| destinationLocation	| leadTime	| mode	| sequenceNumber	| tlaneName |
      | test    | test              | test              | test          | test      | test                  | test      | test  | test              | test      |
      | test2   | test2             | test2             | test2         | test2     | test2                 | test2     | test2 | test2             | test2     |

    And I wait "/plan/cns_tlane_item" Async Queue complete

    Given I import "/plan/cns_tlane_item_exception" by keyFields "sequenceNumber,tlaneName"
      | validTo	| originLocation	| materialNumber	| processTypeId	| validFrom	| destinationLocation	| leadTime	| mode	  | sequenceNumber	| tlaneName |
      | newTest | newTest           | newTest           | newTest       | newTest   | newTest               | newTest   | newTest | newTest         | newTest   |

    And I wait "/plan/cns_tlane_item" Async Queue complete

    Given I import "/plan/cns_process_type" by keyFields "processTypeId"
      | processTypeId | processTypeDesc |
      | 1             | test            |

    And I wait "/plan/cns_process_type" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmTransport.xml" and execute file "jar/pangea-view.jar"

#    Then A file is found on sink application with name "GDMTransport.tsv"
#
#    And I check file data for filename "GDMTransport.tsv" by keyFields "sequenceNumber,tlaneName"
#      | sequenceNumber	| tlaneName		| validFrom	| validTo	| originLocation	| destinatonLocation	| mode	| leadTime	| triangulationDetail	| processTypeId	| sourceSystem	| criticalParameter1	| criticalParameter1Low	| criticalParameter1High	| criticalParameter1Operator	| criticalParameter1IE	| sourceSystem	| criticalParameter2	| criticalParameter2Low	| criticalParameter2High	| criticalParameterOperator	| criticalParameter2IE	| sourceSystem	| criticalParameter3	| criticalParameter3Low	| criticalParameter3High	| criticalParameter3Operator	| criticalParameter3IE	| sourceSystem	| criticalParameter4	| criticalParameter4Low	| criticalParameter4High	| criticalParameter4Operator	| criticalParameter4IE	| sourceSystem	| criticalParameter5Value	| criticalParameter5Low	| criticalParameter5High	| criticalParameter5Opeartor	| criticalParameter5IE	| sourceSystem	| criticalParameter6Value	| criticalParameter6Low	| criticalParameter6High	| criticalParameter6Operator	| criticalParameter6IE	| sourceSystem	| criticalParameter7Value	| criticalParameter7Low	| criticalParameter7High	| criticalParameter7Opearator	| criticalParameter7IE	| sourceSystem	| criticalParameter8Value	| criticalParameter8Low	| criticalParameter8High	| criticalParameter8Operator	| criticalParameter8IE	| sourceSystem	| criticalParameter9Value	| criticalParameter9Low	| criticalParameter9High	| criticalParameter9Operator	| criticalParameter9IE	| sourceSystem	| criticalParameter10Value	| criticalParameter10Low	| criticalParameter10High	| criticalParameter10Operator	| criticalParameter10IE	|
#      | 1				| CO01toMX02	| 1012018	| 12312018	| CONS_LATAM_CO01	| CONS_LATAM_MX02		| Ship	| 15		| Yes					| CONS_LATAM	| P grp			| 1						| 100					| BT						| I								| CONS_LATAM			| P grp			| 1						| 100					| BT						| I							| 						| 				| 						| 						| 							| 								| 						| 				| 						| 						| 							| 								| 						| 				| 							| 						| 							| 								| 						| 				| 							| 						| 							| 								| 						| 				| 							| 						| 							| 								| 						| 				| 							| 						| 							| 								| 						| 				| 							| 						| 							| 								| 						| 				| 							| 							| 							| 								| 						|
#      | 2				| AR01 to AR02	| 1012018	| 12312018	| CONS_LATAM_AR01	| CONS_LATAM_AR02		| Air	| 1			| 						| CONS_LATAM	| P grp			| 2						| 						| >							| I								| CONS_LATAM			| P grp			| 2						| 						| >							| I							| 						| 				| 						| 						| 							| 								| 						| 				| 						| 						| 							| 								| 						| 				| 							| 						| 							| 								| 						| 				| 							| 						| 							| 								| 						| 				| 							| 						| 							| 								| 						| 				| 							| 						| 							| 								| 						| 				| 							| 						| 							| 								| 						| 				| 							| 							| 							| 								| 						|
#
#    And I check file data for filename "GDMTransport.tsv" by keyFields "sequenceNumber,tlaneName"
#      | sequenceNumber	| tlaneName	| validFrom	| validTo	| originLocation	| destinatonLocation	| materialNumber	| mode	| leadTime in days	| Deletion Indicator	| Process Type Id	| Ref seqence number from cns_tlane_item	|
#      | 1				| CO to MX	| 1012018	| 12319999	| CONS-LATAM_CO01	| CONS-LATAM_MX02		| 12345				| Ship	| 15				| 						| 1					| 435										|
#      | 2				| CO to AR	| 1012018	| 12319999	| CONS-LATAM_CO01	| CONS-LATAM_AR02		| 12345				| Ship	| 3					| X						| 2					| 2008										|
#
#    #not sure if this is needed (removing data from regions?)
#    And I delete the test data
#
#    #not sure what regions to clean
#    And I will remove all data with region "/omp/gdm_transport"
#    And I will remove all data with region "/plan/cns_loc_type"
#
#    And I will remove the test file on sink application "GDMTransport.tsv"
#
