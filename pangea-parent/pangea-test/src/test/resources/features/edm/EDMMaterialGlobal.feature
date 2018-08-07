@pangea_test @AEAZ-8301
Feature: EDMMaterialGlobal AEAZ-8301

 

  Scenario: Full Load curation

    Given I import "/project_one/mara" by keyFields "matnr,mandt"
      | matnr     | mtart | meins | zzplnrootid       | matkl  | lvorm | mstae | spart | xchpf | mhdrz | mhdhb | mandt |
      | 414.740V  | FERT  | ST    | 8105080005140012  | 999999 | X     | L     | 10    | X     | 18    |       | 100   |
      | 456.770S  | FERT  | ST    | 8105080005070012  | 999999 | X     | I     | 10    | X     | 36    |       | 100   |
      | 456.775S  | FERT  | ST    | 78962124225170036 | 999999 |       | I     | 10    | X     | 36    |       | 100   |
      | 456.776S  | FERT  | ST    | 78962124225170036 | 999999 | X     | L     | 10    | X     | 18    |       | 100   |
      | 456.778S  | FERT  | ST    | 78962124135600024 | 999999 |       | L     | 10    | X     | 180   | 0     | 100   |
      | 498.015VS | FERT  | ST    | 78912684040290024 | 999999 |       | L     | 10    | X     | 18    |       | 100   |
      | 498.016VS | FERT  | ST    | 78912684040290024 | 999999 |       | L     | 10    | X     | 18    |       | 100   |
      | 498.018VS | FERT  | ST    | 78912684020250024 | 999999 |       | L     | 10    | X     | 18    |       | 100   |
      | 498.023VS | FERT  | ST    | 78912684020180024 | 999999 |       | L     | 10    | X     | 1     | 0     | 100   |
      | 498.024VS | FERT  | ST    | 8105080004150024  | 999999 |       | L     | 10    | X     | 18    |       | 100   |
      | 498.028VS | FERT  | ST    | 8105080004150024  | 999999 |       | L     | 10    | X     | 180   |       | 100   |
      | 498.030VS | FERT  | ST    | 8105080004150024  | 999999 |       | L     | 10    | X     | 18    | 0     | 100   |
      | 498.031VS | FERT  | ST    | 78962124060430006 | 999999 | X     | L     | 10    | X     | 180   |       | 100   |
      | 498.160VS | FERT  | ST    | 78962124225240003 | 999999 | X     | L     | 10    | X     | 36    | 0     | 100   |
      | 498.215VS | FERT  | ST    | 78962124225240003 | 999999 | X     | L     | 10    | X     | 18    |       | 100   |
      | 498.220VS | FERT  | ST    | 8105080004150024  | 999999 | X     | L     | 10    | X     | 18    |       | 100   |
      | 498.225VS | FERT  | ST    | 8105080004150024  | 999999 | X     | L     | 10    | X     | 180   | 0     | 100   |
      | 498.250VS | FERT  | ST    | 8105080004150024  | 999999 |       | L     | 10    | X     | 18    |       | 100   |
      | 498.025VS | FERT  | ST    | 8105080004150024  | 999999 |       | L     | 10    | X     | 36    |       | 100   |
      | 498.251VS | FERT  | ST    | 8105080004150024  | 999999 |       | L     | 10    | X     | 36    | 0     | 100   |
      | 456.777S  | FERT  | ST    | 				  | 999999 |       | L     | 10    | X     | 18    |       | 100   |
      | 498.027VS | FERT  | ST    | 8105080004150024  | 999999 |       | L     | 10    | X     | 18    | 0     | 100   |
      | 456.772S  | FERT  | ST    | 8105080004150024  | 999999 |       | L     | 10    | X     | 180   | 0     | 100   |
      | 456.773S  | FERT  | ST    | 78962124225240003 | 999999 |       | L     | 10    | X     | 18    |       | 100   |
      | 456.774S  | FERT  | ST    | 78962124225240003 | 999999 | X     | L     | 10    | X     | 180   | 0     | 100   |
      
    And I wait "/project_one/mara" Async Queue complete

    Given I import "/project_one/makt" by keyFields "mandt,matnr,spras"
      | matnr     | maktx                                    		| spras | mandt |
      | 414.740V  | SUNDOWN REGULAR FPS 60 STARCK            		| E     | 100   |
      | 456.770S  | J'S BABY WIPES COOLING TOUCH BIL         		| E     | 100   |
      | 456.772S  | LISTERINE ZERO PORT 6X1,5L               		| E     | 100   |
      | 456.772S  | LISTERINE ZERO PORT 6X1,5L-PORTUGESE     		| P     | 100   |
      | 456.772S  | LISTERINE ZERO PORT 6X1,5L - SPANISH     		| S     | 100   |
      | 456.773S  | RoC Clarifiant Eyes 6X15G - PORTUGESE    		| P     | 100   |
      | 456.773S  | RoC Clarifiant Eyes 6X15G - SPANISH      		| S     | 100   |
      | 456.774S  | SUNDOWN REGULAR FPS50 STARCK 12X120ML - SPANISH | S     | 100   |
      | 456.775S  | NTG ACNE PROOFING TONER 6X 200 ML BIL    		| E     | 100   |
      | 456.776S  | SActive Triple Firming Neck Cream 2g     		| E     | 100   |
      | 456.777S  | ROC RETIN-OX CORREXI SERUM A-RUGA 6X30ML 		| E     | 100   |
      | 456.778S  | ROC C-SUPÃ‰RIEUR CONCENTRADO 6% 6X15G    		| E     | 100   |
      | 498.015VS | J'S COTTON SWABS COTONETES - TRILINGUAL  		| E     | 100   |
      | 498.016VS | NAPKIN SL UF COTTON WIN 48x8             		| E     | 100   |
      | 498.018VS | P LINER CF EVERY DAY FLEXI UNSC 64X15    		| E     | 100   |
      | 498.023VS | PR OB MEDIO L20P16 20X20UN               		| E     | 100   |
      | 498.024VS | COTONETES CARTRIDGE 30X150               		| E     | 100   |
      | 498.025VS | PR SHAMP 200ML + SAB LIQ HTT 200ML       		| E     | 100   |
      | 498.027VS | PR CURAT B-AID TRANSP L10P8 144X10UN     		| E     | 100   |
      | 498.028VS | COND OGX BRAZIL KERAT 6X385ML BIL        		| E     | 100   |
      | 498.030VS | J'S SOFT PELE DOS SONHOS 12X200ML        		| E     | 100   |
      | 498.031VS | NEUT DEEP CLEAN ADSTR FACIAL 6X200ML BIL 		| E     | 100   |
      | 498.160VS | PROT DIARIO CAREFREE PROT S/PERF 48X15UN 		| E     | 100   |
      | 498.215VS | AVEENO SKIN RELIEF COCO BODY LOT 354mL   		| E     | 100   |
      | 498.220VS | PR ROC MI OIL CONT FP30 GR ROC C-SUP 6CJ 		| E     | 100   |
      | 498.225VS | ABS HIG SL TRI-PR MAX SV S/ABA 4X12X8UN  		| E     | 100   |
      | 498.250VS | ROC MINESOL ACT UNIFY FPS60 AG 240X5G    		| E     | 100   |
      | 498.251VS | NTG SUNFRESH RADIANCE SPF60 200ML        		| E     | 100   |
    And I wait "/project_one/makt" Async Queue complete

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem    	| localSourceSystemName | sourceSystem | sourceSystemName  						  | sourceSystemType |
      | Project_One       		| Project One           | CONS_LATAM   | Consumer Latam Ent 					  | SAP    		     |
      | [EMS]             		| EMS                   | EMS          | Enterprise Master Data Management System | NGEMS            |
      | [Consumer LATAM]		| Consumer Latam        | CONS_LATAM   | Consumer Latam Ent 					  | NG_EMS           |
      | [MD DePuy Spine JDE XE] | Spine                 | MDDePuy      | MD DePuy Ent							  | JDE              |
      | ems             		| EMS                   | EMS          | Enterprise Master Data Management System | EMS              |
    And I wait "/edm/source_system_v1" Async Queue complete

    Given I import "/p360/material_cross_reference" by keyFields "sourceSystemName,enterpriseMaterialNumber"
      | sourceSystemName   | sourceSystemMaterialNumber | enterpriseMaterialNumber |
      | [Consumer LATAM]   | 414.740V            		| EMN_414.740V   		   |
      | [Consumer LATAM]   | 456.770S            		| EMN_456.770S      	   |
      | [Consumer LATAM]   | 456.775S            		| EMN_456.775S      	   |
      | [Consumer LATAM]   | 456.776S            		| EMN_456.776S      	   |
      | [Consumer LATAM]   | 498.015VS           		| EMN_498.015VS     	   |
      | [Consumer LATAM]   | 456.778S            		| EMN_456.778S      	   |
      | [Consumer LATAM]   | 498.016VS           		| EMN_498.016VS    		   |
      | [Consumer LATAM]   | 498.018VS           		| EMN_498.018VS    		   |
      | [Consumer LATAM]   | 498.023VS           		| EMN_498.023VS     	   |
      | [Consumer LATAM]   | 498.024VS           		| EMN_498.024VS     	   |
      | [Consumer LATAM]   | 498.028VS           		| EMN_498.028VS     	   |
      | [Consumer LATAM]   | 498.030VS           		| EMN_498.030VS     	   |
      | [Consumer LATAM]   | 498.031VS           		| EMN_498.031VS     	   |
      | [Consumer LATAM]   | 498.160VS           		| EMN_498.160VS     	   |
      | [Consumer LATAM]   | 498.215VS           		| EMN_498.215VS     	   |
      | [Consumer LATAM]   | 498.220VS           		| EMN_498.220VS     	   |
      | [Consumer LATAM]   | 498.225VS           		| EMN_498.225VS     	   |
      | [Consumer LATAM]   | 498.250VS           		| EMN_498.250VS     	   |
      | [Consumer LATAM]   | 498.025VS           		| EMN_498.025VS     	   |	  
      | [Consumer LATAM]   | 498.251VS           		| 				    	   |
      | [Consumer LATAM]   | 456.777S           		| EMN_456.777S      	   |	  
      | [Consumer LATAM]   | 498.027VS           		| EMN_498.027VS     	   |	  
      | [Consumer LATAM]   | 456.772S           		| EMN_456.772S      	   |
      | [Consumer LATAM]   | 456.773S           		| EMN_456.773S     		   |
      | [Consumer LATAM]   | 456.774S           		| EMN_456.774S      	   |	  
    And I wait "/p360/material_cross_reference" Async Queue complete

    Given I import "/p360/material" by keyFields "enterpriseMaterialNumber"
      | enterpriseMaterialNumber   | materialDescription								 | materialType	  |	baseUnitOfMeasure |	parentCode | globalDpParentCode	| form	      |	category	| subBrand	  |	brand	    | franchise | globalBusinessUnitGbu | productFamily | manufacturingTechnology |
	  | EMN_414.740V			   | EMN_SUNDOWN REGULAR FPS 60 STARCK					 | FINISHED GOODS |	EA				  |	987654321  | G_987654321		| F_987654321 |	C_987654321	| S_987654321 |	B_987654321	| ET		| CNS				 	| P_987654321	 | T_987654321			   |
	  | EMN_456.770S			   | EMN_J'S BABY WIPES COOLING TOUCH BIL				 | FINISHED GOODS |	EA				  |	987654321  | G_987654321		| F_987654321 |	C_987654321	| S_987654321 |	B_987654321	| ET		| CNS				 	| P_987654321	 | T_987654321			   |
	  | EMN_456.775S			   | EMN_NTG ACNE PROOFING TONER 6X 200 ML BIL			 | FINISHED GOODS |	EA				  |	987654321  | G_987654321		| F_987654321 |	C_987654321	| S_987654321 |	B_987654321	| ET		| CNS				 	| P_987654321	 | T_987654321			   |
	  | EMN_456.776S			   | EMN_SActive Triple Firming Neck Cream 2g			 | FINISHED GOODS |	EA				  |	987654321  | G_987654321		| F_987654321 |	C_987654321	| S_987654321 |	B_987654321	| ET		| CNS					| P_987654321	 | T_987654321			   |
	  | EMN_456.778S			   | EMN_ROC C-SUPÃ‰RIEUR CONCENTRADO 6% 6X15G			 | FINISHED GOODS |	EA				  |	987654321  | G_987654321		| F_987654321 |	C_987654321	| S_987654321 |	B_987654321	| ET		| CNS					| P_987654321	 | T_987654321			   |
	  | EMN_498.015VS			   | EMN_J'S COTTON SWABS COTONETES - TRILINGUAL		 | FINISHED GOODS |	EA				  |	123456789  | G_123456789		| F_123456789 |	C_123456789	| S_123456789 |	B_123456789	| ET		| CNS				 	| P_123456789	 | T_123456789			   |
	  | EMN_498.016VS			   | EMN_NAPKIN SL UF COTTON WIN 48x8					 | 			 	  |	EA				  |	123456789  | G_123456789		| F_123456789 |	C_123456789	| S_123456789 |	B_123456789	| ET		| CNS				 	| P_123456789	 | T_123456789			   |
	  | EMN_498.018VS			   | EMN_P LINER CF EVERY DAY FLEXI UNSC 64X15			 | FINISHED GOODS |					  |	123456789  | G_123456789		| F_123456789 |	C_123456789	| S_123456789 |	B_123456789	| ET		| CNS				 	| P_123456789	 | T_123456789			   |
	  | EMN_498.023VS			   | EMN_PR OB MEDIO L20P16 20X20UN						 | FINISHED GOODS |	EA				  |		       | G_123456789		| F_123456789 |	C_123456789	| S_123456789 |	B_123456789	| ET		| CNS				 	| P_123456789	 | T_123456789			   |
	  | EMN_498.024VS			   | EMN_COTONETES CARTRIDGE 30X150						 | FINISHED GOODS |	EA				  |	897654321  | 			    	| F_897654321 |	C_897654321	| S_897654321 |	B_897654321	| ET		| CNS				 	| P_897654321	 | T_897654321			   |
	  | EMN_498.028VS			   | EMN_COND OGX BRAZIL KERAT 6X385ML BIL				 | FINISHED GOODS |	EA				  |	897654321  | G_897654321		| 			  |	C_897654321	| S_897654321 |	B_897654321	| EE		| CNS				 	| P_897654321	 | T_897654321			   |
	  | EMN_498.030VS			   | EMN_J'S SOFT PELE DOS SONHOS 12X200ML				 | FINISHED GOODS |	EA				  |	897654321  | G_897654321		| F_897654321 |				| S_897654321 |	B_897654321	| EE		| CNS				 	| P_897654321	 | T_897654321			   |
	  | EMN_498.031VS			   | EMN_NEUT DEEP CLEAN ADSTR FACIAL 6X200ML BIL 		 | FINISHED GOODS |	EA				  |	897654321  | G_897654321		| F_897654321 |	C_897654321	| 			  |	B_897654321	| EE		| CNS				 	| P_897654321	 | T_897654321			   |
	  | EMN_498.160VS			   | EMN_PROT DIARIO CAREFREE PROT S/PERF 48X15UN 		 | FINISHED GOODS |	EA				  |	213456789  | G_213456789		| F_213456789 |	C_213456789	| S_213456789 |				| EE		| CNS				 	| P_213456789	 | T_213456789			   |
	  | EMN_498.215VS			   | EMN_AVEENO SKIN RELIEF COCO BODY LOT 354mL	  		 | FINISHED GOODS |	EA				  |	213456789  | G_213456789		| F_213456789 |	C_213456789	| S_213456789 |	B_213456789	| 			| CNS				 	| P_213456789	 | T_213456789			   |
	  | EMN_498.220VS			   | EMN_PR ROC MI OIL CONT FP30 GR ROC C-SUP 6CJ 		 | FINISHED GOODS |	EA				  |	213456789  | G_213456789		| F_213456789 |	C_213456789	| S_213456789 |	B_213456789	| LS		| 					 	| P_213456789	 | T_213456789			   |
	  | EMN_498.225VS			   | EMN_ABS HIG SL TRI-PR MAX SV S/ABA 4X12X8UN  		 | FINISHED GOODS |	EA				  |	213456789  | G_213456789		| F_213456789 |	C_213456789	| S_213456789 |	B_213456789	| LS		| CNS				 	| 				 | T_213456789			   |
	  | EMN_498.250VS			   | EMN_ROC MINESOL ACT UNIFY FPS60 AG 240X5G	  		 | FINISHED GOODS |	EA				  |	213456789  | G_213456789		| F_213456789 |	C_213456789	| S_213456789 |	B_213456789	| LS		| CNS				 	| P_213456789	 | 						   |
	  | EMN_498.025VS			   | 											  		 | FINISHED GOODS |	EA				  |	789654321  | G_789654321		| F_789654321 |	C_789654321	| S_789654321 |	B_789654321	| LS		| CNS				 	| P_789654321	 | T_789654321			   |
	  | EMN_456.777S			   | EMN_ROC RETIN-OX CORREXI SERUM A-RUGA 6X30ML 		 | FINISHED GOODS |	EA				  |	789654321  | G_789654321		| F_789654321 |	C_789654321	| S_789654321 |	B_789654321	| AS		| CNS				 	| P_789654321	 | T_789654321			   |
	  | EMN_498.027VS			   | EMN_PR CURAT B-AID TRANSP L10P8 144X10UN	  		 | FINISHED GOODS |	EA				  |	789654321  | G_789654321		| F_789654321 |	C_789654321	| S_789654321 |	B_789654321	| AS		| CNS				 	| P_789654321	 | T_789654321			   |
	  | EMN_456.772S			   | EMN_LISTERINE ZERO PORT 6X1,5L				  		 | FINISHED GOODS |	EA				  |	312456789  | G_312456789		| F_312456789 |	C_312456789	| S_312456789 |	B_312456789	| AS		| CNS				 	| P_312456789	 | T_312456789			   |
	  | EMN_456.773S			   | EMN_RoC Clarifiant Eyes 6X15G - PORTUGESE	  		 | FINISHED GOODS |	EA				  |	312456789  | G_312456789		| F_312456789 |	C_312456789	| S_312456789 |	B_312456789	| AS		| CNS				 	| P_312456789	 | T_312456789			   |
	  | EMN_456.774S			   | EMN_SUNDOWN REGULAR FPS50 STARCK 12X120ML - SPANISH | FINISHED GOODS |	EA				  |	312456789  | G_312456789		| F_312456789 |	C_312456789	| S_312456789 |	B_312456789	| AS		| CNS				 	| P_312456789	 | T_312456789			   |
    And I wait "/p360/material" Async Queue complete

    Given I import "/plan/edm_mat_input" by keyFields "sourceSystem,localMaterialNumber"
      | sourceSystem | localMaterialNumber | localTechnology  |
	  | CONS_LATAM   | 414.740V			   | Toothbrushes	  |
	  | CONS_LATAM   | 456.770S			   | Toothbrushes	  |
	  | CONS_LATAM   | 456.775S			   | Toothbrushes	  |
	  | CONS_LATAM   | 456.776S			   | ImpoC		      |
	  | CONS_LATAM   | 456.778S			   | ImpoC		      |
	  | CONS_LATAM   | 498.015VS		   | Soaps		      |
	  | CONS_LATAM   | 498.016VS		   | Soaps		      |
	  | CONS_LATAM   | 498.018VS		   | Soaps		      |
	  | CONS_LATAM   | 498.023VS		   | Cologne/Oil	  |
	  | CONS_LATAM   | 498.024VS		   | Cologne/Oil	  |
	  | CONS_LATAM   | 498.028VS		   | ImpoC	          |
	  | CONS_LATAM   | 498.030VS		   | ImpoC	          |
	  | CONS_LATAM   | 498.031VS		   | Toiletries BRA   |
	  | CONS_LATAM   | 498.160VS		   | ImpoC	          |
	  | CONS_LATAM   | 498.215VS		   | Toiletries BRA   |
	  | CONS_LATAM   | 498.220VS		   | Toiletries BRA   |
	  | CONS_LATAM   | 498.225VS		   | Listerne	      |
	  | CONS_LATAM   | 498.250VS		   | ImpoC	          |
	  | CONS_LATAM   | 498.025VS		   | Hair Care /Baths |
	  | CONS_LATAM   | 498.251VS		   | Hair Care /Baths |
	  | CONS_LATAM   | 456.777S			   | ImpoC	          |
	  | CONS_LATAM   | 498.027VS		   |		          |
	  | CONS_LATAM   | 456.772S			   | ImpoC	          |
	  | CONS_LATAM   | 456.773S			   | Hair Care /Baths |
	  | CONS_LATAM   | 456.774S			   | Cologne/Oil	  |
    And I wait "/plan/edm_mat_input" Async Queue complete

    When I submit task with xml file "xml/edm/EDMMaterialGlobal.xml" and execute file "jar/pangea-view.jar"

    And wait 8000 millisecond

    Then I check region data "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      |	sourceSystem | localMaterialNumber | localRefDescription							 | localMaterialType | localBaseUom | materialNumber | refDescription									   | materialType	| baseUom |	localDpParentCode |	parentCode | globalDpParentCode | form		  |	category	| subBrand	  |	brand		| franchise | globalBusinessUnit | productFamily | localManufacturingTechnology	| manufacturingTechnology |	localMaterialGroup | materialGroup | flagForDeletion | materialStatus |	division | batchManageIndicator	| minRemShelfLife |	totalShelfLife | primaryPlanningCode |
	  |	CONS_LATAM	 | 414.740V			   | SUNDOWN REGULAR FPS 60 STARCK					 | FERT				 | ST			| EMN_414.740V	 | EMN_SUNDOWN REGULAR FPS 60 STARCK				   | FINISHED GOODS	| EA	  |	8105080005140012  |	987654321  | G_987654321		| F_987654321 |	C_987654321	| S_987654321 |	B_987654321	| ET		| CNS				 | P_987654321	 | Toothbrushes				    | T_987654321			  |	999999			   |			   | X				 | L			  |	10		 | X					| 18			  |				   | EMN_414.740V		 |
	  |	CONS_LATAM	 | 456.770S			   | J'S BABY WIPES COOLING TOUCH BIL				 | FERT				 | ST			| EMN_456.770S	 | EMN_J'S BABY WIPES COOLING TOUCH BIL			   	   | FINISHED GOODS	| EA	  |	8105080005070012  |	987654321  | G_987654321		| F_987654321 |	C_987654321	| S_987654321 |	B_987654321	| ET		| CNS				 | P_987654321	 | Toothbrushes				    | T_987654321			  |	999999			   |			   | X				 | I			  |	10		 | X					| 36			  |				   | EMN_456.770S		 |
	  |	CONS_LATAM	 | 456.775S			   | NTG ACNE PROOFING TONER 6X 200 ML BIL			 | FERT				 | ST			| EMN_456.775S	 | EMN_NTG ACNE PROOFING TONER 6X 200 ML BIL		   | FINISHED GOODS	| EA	  |	78962124225170036 |	987654321  | G_987654321		| F_987654321 |	C_987654321	| S_987654321 |	B_987654321	| ET		| CNS				 | P_987654321	 | Toothbrushes				    | T_987654321			  |	999999			   |			   | 				 | I			  |	10		 | X					| 36			  |				   | EMN_456.775S		 |
	  |	CONS_LATAM	 | 456.776S			   | SActive Triple Firming Neck Cream 2g			 | FERT				 | ST			| EMN_456.776S	 | EMN_SActive Triple Firming Neck Cream 2g			   | FINISHED GOODS	| EA	  |	78962124225170036 |	987654321  | G_987654321		| F_987654321 |	C_987654321	| S_987654321 |	B_987654321	| ET		| CNS				 | P_987654321	 | ImpoC					    | T_987654321			  |	999999			   |			   | X				 | L			  |	10		 | X					| 18			  |				   | EMN_456.776S		 |
	  |	CONS_LATAM	 | 456.778S			   | ROC C-SUPÃ‰RIEUR CONCENTRADO 6% 6X15G			 | FERT				 | ST			| EMN_456.778S	 | EMN_ROC C-SUPÃ‰RIEUR CONCENTRADO 6% 6X15G		   | FINISHED GOODS	| EA	  |	78962124135600024 |	987654321  | G_987654321		| F_987654321 |	C_987654321	| S_987654321 |	B_987654321	| ET		| CNS				 | P_987654321	 | ImpoC					    | T_987654321			  |	999999			   |			   | 				 | L			  |	10		 | X					| 180			  |	0			   | EMN_456.778S		 |
	  |	CONS_LATAM	 | 498.015VS		   | J'S COTTON SWABS COTONETES - TRILINGUAL		 | FERT				 | ST			| EMN_498.015VS	 | EMN_J'S COTTON SWABS COTONETES - TRILINGUAL		   | FINISHED GOODS	| EA	  |	78912684040290024 |	123456789  | G_123456789		| F_123456789 |	C_123456789	| S_123456789 |	B_123456789	| ET		| CNS				 | P_123456789	 | Soaps					    | T_123456789			  |	999999			   |			   | 				 | L			  |	10		 | X					| 18			  |				   | EMN_498.015VS		 |
	  |	CONS_LATAM	 | 498.016VS		   | NAPKIN SL UF COTTON WIN 48x8					 | FERT				 | ST			| EMN_498.016VS	 | EMN_NAPKIN SL UF COTTON WIN 48x8					   | 				| EA	  |	78912684040290024 |	123456789  | G_123456789		| F_123456789 |	C_123456789	| S_123456789 |	B_123456789	| ET		| CNS				 | P_123456789	 | Soaps					    | T_123456789			  |	999999			   |			   | 				 | L			  |	10		 | X					| 18			  |				   | EMN_498.016VS		 |
	  |	CONS_LATAM	 | 498.018VS		   | P LINER CF EVERY DAY FLEXI UNSC 64X15			 | FERT				 | ST			| EMN_498.018VS	 | EMN_P LINER CF EVERY DAY FLEXI UNSC 64X15		   | FINISHED GOODS	| 		  |	78912684020250024 |	123456789  | G_123456789		| F_123456789 |	C_123456789	| S_123456789 |	B_123456789	| ET		| CNS				 | P_123456789	 | Soaps					    | T_123456789			  |	999999			   |			   | 				 | L			  |	10		 | X					| 18			  |				   | EMN_498.018VS		 |
	  |	CONS_LATAM	 | 498.023VS		   | PR OB MEDIO L20P16 20X20UN						 | FERT				 | ST			| EMN_498.023VS	 | EMN_PR OB MEDIO L20P16 20X20UN					   | FINISHED GOODS	| EA	  |	78912684020180024 |			   | G_123456789		| F_123456789 |	C_123456789	| S_123456789 |	B_123456789	| ET		| CNS				 | P_123456789	 | Cologne/Oil					| T_123456789			  |	999999			   |			   | 				 | L			  |	10		 | X					| 1				  |	0			   | EMN_498.023VS		 |
	  |	CONS_LATAM	 | 498.024VS		   | COTONETES CARTRIDGE 30X150						 | FERT				 | ST			| EMN_498.024VS	 | EMN_COTONETES CARTRIDGE 30X150					   | FINISHED GOODS	| EA	  |	8105080004150024  |	897654321  | 					| F_897654321 |	C_897654321	| S_897654321 |	B_897654321	| ET		| CNS				 | P_897654321	 | Cologne/Oil					| T_897654321			  |	999999			   |			   | 				 | L			  |	10		 | X					| 18			  |				   | EMN_498.024VS		 |
	  |	CONS_LATAM	 | 498.028VS		   | COND OGX BRAZIL KERAT 6X385ML BIL				 | FERT				 | ST			| EMN_498.028VS	 | EMN_COND OGX BRAZIL KERAT 6X385ML BIL			   | FINISHED GOODS	| EA	  |	8105080004150024  |	897654321  | G_897654321		| 			  |	C_897654321	| S_897654321 |	B_897654321	| EE		| CNS				 | P_897654321	 | ImpoC						| T_897654321			  |	999999			   |			   | 				 | L			  |	10		 | X					| 180			  |				   | EMN_498.028VS		 |
	  |	CONS_LATAM	 | 498.030VS		   | J'S SOFT PELE DOS SONHOS 12X200ML				 | FERT				 | ST			| EMN_498.030VS	 | EMN_J'S SOFT PELE DOS SONHOS 12X200ML			   | FINISHED GOODS	| EA	  |	8105080004150024  |	897654321  | G_897654321		| F_897654321 |				| S_897654321 |	B_897654321	| EE		| CNS				 | P_897654321	 | ImpoC						| T_897654321			  |	999999			   |			   | 				 | L			  |	10		 | X					| 18			  |	0			   | EMN_498.030VS		 |
	  |	CONS_LATAM	 | 498.031VS		   | NEUT DEEP CLEAN ADSTR FACIAL 6X200ML BIL		 | FERT				 | ST			| EMN_498.031VS	 | EMN_NEUT DEEP CLEAN ADSTR FACIAL 6X200ML BIL		   | FINISHED GOODS	| EA	  |	78962124060430006 |	897654321  | G_897654321		| F_897654321 |	C_897654321	| 			  |	B_897654321	| EE		| CNS				 | P_897654321	 | Toiletries BRA				| T_897654321			  |	999999			   |			   | X				 | L			  |	10		 | X					| 180			  |				   | EMN_498.031VS		 |
	  |	CONS_LATAM	 | 498.160VS		   | PROT DIARIO CAREFREE PROT S/PERF 48X15UN		 | FERT				 | ST			| EMN_498.160VS	 | EMN_PROT DIARIO CAREFREE PROT S/PERF 48X15UN		   | FINISHED GOODS	| EA	  |	78962124225240003 |	213456789  | G_213456789		| F_213456789 |	C_213456789	| S_213456789 |				| EE		| CNS				 | P_213456789	 | ImpoC						| T_213456789			  |	999999			   |			   | X				 | L			  |	10		 | X					| 36			  |	0			   | EMN_498.160VS		 |
	  |	CONS_LATAM	 | 498.215VS		   | AVEENO SKIN RELIEF COCO BODY LOT 354mL			 | FERT				 | ST			| EMN_498.215VS	 | EMN_AVEENO SKIN RELIEF COCO BODY LOT 354mL		   | FINISHED GOODS	| EA	  |	78962124225240003 |	213456789  | G_213456789		| F_213456789 |	C_213456789	| S_213456789 |	B_213456789	| 			| CNS				 | P_213456789	 | Toiletries BRA				| T_213456789			  |	999999			   |			   | X				 | L			  |	10		 | X					| 18			  |				   | EMN_498.215VS		 |
	  |	CONS_LATAM	 | 498.220VS		   | PR ROC MI OIL CONT FP30 GR ROC C-SUP 6CJ		 | FERT				 | ST			| EMN_498.220VS	 | EMN_PR ROC MI OIL CONT FP30 GR ROC C-SUP 6CJ		   | FINISHED GOODS	| EA	  |	8105080004150024  |	213456789  | G_213456789		| F_213456789 |	C_213456789	| S_213456789 |	B_213456789	| LS		| 					 | P_213456789	 | Toiletries BRA				| T_213456789			  |	999999			   |			   | X				 | L			  |	10		 | X					| 18			  |				   | EMN_498.220VS		 |
	  |	CONS_LATAM	 | 498.225VS		   | ABS HIG SL TRI-PR MAX SV S/ABA 4X12X8UN		 | FERT				 | ST			| EMN_498.225VS	 | EMN_ABS HIG SL TRI-PR MAX SV S/ABA 4X12X8UN		   | FINISHED GOODS	| EA	  |	8105080004150024  |	213456789  | G_213456789		| F_213456789 |	C_213456789	| S_213456789 |	B_213456789	| LS		| CNS				 | 				 | Listerne						| T_213456789			  |	999999			   |			   | X				 | L			  |	10		 | X					| 180			  |	0			   | EMN_498.225VS		 |
	  |	CONS_LATAM	 | 498.250VS		   | ROC MINESOL ACT UNIFY FPS60 AG 240X5G			 | FERT				 | ST			| EMN_498.250VS	 | EMN_ROC MINESOL ACT UNIFY FPS60 AG 240X5G		   | FINISHED GOODS	| EA	  |	8105080004150024  |	213456789  | G_213456789		| F_213456789 |	C_213456789	| S_213456789 |	B_213456789	| LS		| CNS				 | P_213456789	 | ImpoC						| 						  |	999999			   |			   | 				 | L			  |	10		 | X					| 18			  |				   | EMN_498.250VS		 |
	  |	CONS_LATAM 	 | 498.025VS		   | PR SHAMP 200ML + SAB LIQ HTT 200ML				 | FERT				 | ST			| EMN_498.025VS	 | 													   | FINISHED GOODS	| EA	  |	8105080004150024  |	789654321  | G_789654321		| F_789654321 |	C_789654321	| S_789654321 |	B_789654321	| LS		| CNS				 | P_789654321	 | Hair Care /Baths				| T_789654321			  |	999999			   |			   | 				 | L			  |	10		 | X					| 36			  |				   | EMN_498.025VS		 |
	  |	CONS_LATAM	 | 498.251VS		   | NTG SUNFRESH RADIANCE SPF60 200ML				 | FERT				 | ST			| 				 | 													   | 				| 		  |	8105080004150024  |			   |   					| 			  |				| 			  |				| 			| 					 | 				 | Hair Care /Baths				| 						  |	999999			   |			   | 				 | L			  |	10		 | X					| 36			  |	0			   | 					 |
	  |	CONS_LATAM	 | 456.777S			   | ROC RETIN-OX CORREXI SERUM A-RUGA 6X30ML		 | FERT				 | ST			| EMN_456.777S	 | EMN_ROC RETIN-OX CORREXI SERUM A-RUGA 6X30ML		   | FINISHED GOODS	| EA	  |					  |	789654321  | G_789654321		| F_789654321 |	C_789654321	| S_789654321 |	B_789654321	| AS		| CNS				 | P_789654321	 | ImpoC						| T_789654321			  |	999999			   |			   | 				 | L			  |	10		 | X					| 18			  |				   | EMN_456.777S		 |
	  |	CONS_LATAM	 | 498.027VS		   | PR CURAT B-AID TRANSP L10P8 144X10UN			 | FERT				 | ST			| EMN_498.027VS	 | EMN_PR CURAT B-AID TRANSP L10P8 144X10UN			   | FINISHED GOODS	| EA	  |	8105080004150024  |	789654321  | G_789654321		| F_789654321 |	C_789654321	| S_789654321 |	B_789654321	| AS		| CNS				 | P_789654321	 |								| T_789654321			  |	999999			   |			   | 				 | L			  |	10		 | X					| 18			  |	0			   | EMN_498.027VS		 |
	  |	CONS_LATAM	 | 456.772S			   | LISTERINE ZERO PORT 6X1,5L						 | FERT				 | ST			| EMN_456.772S	 | EMN_LISTERINE ZERO PORT 6X1,5L					   | FINISHED GOODS	| EA	  |	8105080004150024  |	312456789  | G_312456789		| F_312456789 |	C_312456789	| S_312456789 |	B_312456789	| AS		| CNS				 | P_312456789	 | ImpoC						| T_312456789			  |	999999			   |			   | 				 | L			  |	10		 | X					| 180			  |	0			   | EMN_456.772S		 |
	  |	CONS_LATAM	 | 456.773S			   | RoC Clarifiant Eyes 6X15G - PORTUGESE		     | FERT				 | ST			| EMN_456.773S	 | EMN_RoC Clarifiant Eyes 6X15G - PORTUGESE		   | FINISHED GOODS	| EA	  |	78962124225240003 |	312456789  | G_312456789		| F_312456789 |	C_312456789	| S_312456789 |	B_312456789	| AS		| CNS				 | P_312456789	 | Hair Care /Baths				| T_312456789			  |	999999			   |			   | 				 | L			  |	10		 | X					| 18			  |				   | EMN_456.773S		 |
	  |	CONS_LATAM	 | 456.774S			   | SUNDOWN REGULAR FPS50 STARCK 12X120ML - SPANISH | FERT				 | ST			| EMN_456.774S	 | EMN_SUNDOWN REGULAR FPS50 STARCK 12X120ML - SPANISH | FINISHED GOODS	| EA	  |	78962124225240003 |	312456789  | G_312456789		| F_312456789 |	C_312456789	| S_312456789 |	B_312456789	| AS		| CNS				 | P_312456789	 | Cologne/Oil					| T_312456789			  |	999999			   |			   | X				 | L			  |	10		 | X					| 180			  |	0			   | EMN_456.774S		 |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/project_one/mara" and "/edm/material_global_v1,/plan/edm_failed_data"

#  Scenario: delete all test data

#    Then I delete the test data

#    And I will remove all data with region "/edm/material_global_v1"

#    And I will remove all data with region "/plan/edm_failed_data"
