@pangea_test @AEAZ-8894
Feature: OMPGdmLocation AEAZ-8894

  Scenario: Full Load curation - edm

    And I will remove the test file on sink application "GDMLocation_edm.tsv"

    Given I import "/edm/plant_v1" by keyFields "sourceSystem,localPlant"
      | sourceSystem | localPlant | localPlanningRelevant | country | localCurrency | localPlantName | plantType | region   | localCountry |
      | CONS_LATAM   | AR_C1      | X                     | 00      | AR            | Pilar Plant    | AH        | edmPlant | AR           |
      | CONS_LATAM   | AR_T2      | X                     | 00      | AR            | Pilar Plant    | AH        | edmPlant | AR           |
      | CONS_LATAM   | AR1_T2     | X                     | 00      | AR            | Pilar Plant    | AH        | edmPlant | AR           |
      | CONS_LATAM   | AR1_T5     | X                     | 00      | AR            | Pilar Plant    | AH        | edmPlant | AR           |
      | CONS_LATAM   | AR1_T6     |                       | 00      | AR            | Pilar Plant    | AH        | edmPlant | AR           |
      | CONS_LATAM   | AR1_T7     | X                     | 00      | AR            | Pilar Plant    | AH        | edmPlant | AR           |
      | CONS_LATAM   | AR1_T9     | X                     | 00      | AR            | Pilar Plant    | AH        | edmPlant | AR           |
      | CONS_LATAM   | AR1_T8     | X                     | 00      | AR            | Pilar Plant    | AH        | edmPlant | AR           |
      | CONS_LATAM   | AR1_T10    | X                     |         | AR            | Pilar Plant    | AH        | edmPlant | AR           |
    And I wait "/edm/plant_v1" Async Queue complete

    Given I import "/plan/cns_plan_parameter" by keyFields "sourceSystem,parameterValue,parameter"
      | sourceSystem | dataObject               | attribute  | parameter | parameterValue |
      | CONS_LATAM   | cns_material_plan_status | DPRelevant | Plant     | AR_C1          |
      | CONS_LATAM   | cns_material_plan_status | DPRelevant | Plant     | AR_T2          |
      | CONS_LATAM   | cns_material_plan_status | SPRelevant | Plant     | AR1_T2         |
      | CONS_LATAM   | cns_material_plan_status | DPRelevant | Plant     | AR1_T5         |
      | CONS_LATAM   | cns_material_plan_status | DPRelevant | Plant     | AR1_T6         |
      | CONS_LATAM   | cns_material_plan_status | DPRelevant | Plant     | AR1_T7         |
      | CONS_LATAM   | cns_material_plan_status | DPRelevant | Plant     | AR1_T9         |
      | CONS_LATAM   | cns_material_plan_status | DPRelevant | Plant     | AR1_T8         |
      | CONS_LATAM   | cns_material_plan_status | DPRelevant | Plant     | AR1_T10        |

    And I wait "/plan/cns_plan_parameter" Async Queue complete

    Given I import "/edm/country_v1" by keyFields "sourceSystem"
      | sourceSystem | localCountry | consumerPlanningRegion |
      | CONS_LATAM   | AR           | regin001               |

    And I wait "/edm/country_v1" Async Queue complete

    Given I import "/plan/cns_plant_attr" by keyFields "sourceSystem,localPlant"
      | sourceSystem | localPlant | planLocTypeId |
      | CONS_LATAM   | AR_C1      | typeid001     |
      | CONS_LATAM   | AR_T2      | typeid001     |
      | CONS_LATAM   | AR1_T2     | typeid001     |
      | CONS_LATAM   | AR1_T5     | typeid001     |
      | CONS_LATAM   | AR1_T6     | typeid001     |
      | CONS_LATAM   | AR1_T7     | typeid001     |
      | CONS_LATAM   | AR1_T9     | typeid001     |
      | CONS_LATAM   | AR1111     | typeid001     |
      | CONS_LATAM   | AR1_T10    | typeid001     |
    And I wait "/plan/cns_plant_attr" Async Queue complete

    Given I import "/edm/currency_v1" by keyFields "sourceSystem"
      | sourceSystem | localCurrency | currencyCode |
      | CONS_LATAM   | AR            | AFA          |
    And I wait "/edm/currency_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmLocationEdm.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMLocation_edm.tsv"

    Then I check file data for filename "GDMLocation_edm.tsv" by keyFields "locationId"
      | locationId        | active | activeFCTERP | activeOPRERP | activeSOPERP | countryId | currencyId | customerId | label       | locationTypeId | regionId | vendorId |
      | CONS_LATAM_AR_C1  | YES    | YES          | YES          | NO           | 00        | AFA        |            | Pilar Plant | typeid001      | regin001 |          |
      | CONS_LATAM_AR_T2  | YES    | YES          | YES          | NO           | 00        | AFA        |            | Pilar Plant | typeid001      | regin001 |          |
      | CONS_LATAM_AR1_T2 | YES    | NO           | YES          | NO           | 00        | AFA        |            | Pilar Plant | typeid001      | regin001 |          |
      | CONS_LATAM_AR1_T5 | YES    | YES          | YES          | NO           | 00        | AFA        |            | Pilar Plant | typeid001      | regin001 |          |
      | CONS_LATAM_AR1_T6 | YES    | YES          | NO           | NO           | 00        | AFA        |            | Pilar Plant | typeid001      | regin001 |          |
      | CONS_LATAM_AR1_T7 | YES    | YES          | YES          | NO           | 00        | AFA        |            | Pilar Plant | typeid001      | regin001 |          |
      | CONS_LATAM_AR1_T9 | YES    | YES          | YES          | NO           | 00        | AFA        |            | Pilar Plant | typeid001      | regin001 |          |

    Then I check region data "/dev/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID       | errorCode | sourceSystem | businessArea | key1    | key2       | key3 | key4 | key5 | errorValue               |
      | SP             | OMPGdmLocationEdm | T8        | project_one  |              | AR1_T8  | CONS_LATAM |      |      |      | Missing Location Type Id |
      | SP             | OMPGdmLocationEdm | T10       | project_one  |              | AR1_T10 | CONS_LATAM |      |      |      | Missing Country          |

    And I compare the number of records between "/edm/plant_v1" and "/omp/gdm_location,/dev/plan/edm_failed_data"

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/omp/gdm_location"

    And I will remove all data with region "/dev/plan/edm_failed_data"

  Scenario: Full Load curation - xref
  # 1. get atrributes from cns_plan_parameter(rules C2)

    And I will remove the test file on sink application "GDMLocation_xref.tsv"

    Given I import "/plan/cns_spl_pln_loc" by keyFields "sourceSystem,localNumber,localName"
      | sourceSystem | vendorOrCustomer | localNumber  | localCountry | localCurrency | localName                           | planLocTypeId | localRegion | localPlant |
      | CONS_LATAM   | V                | 49139        | EC           |               | CORPORACION DE PROYECTOS EQUINOCCIA | SUBCON        | 103         | EC01       |
      | CONS_LATAM   | V                | 65659        | UY           | UYU           | MODYLER SA                          | SUBCON        | 103         | UY01       |
      | CONS_LATAM   | V                | 86017        | MX           | MXN           | AVP MAQUILA DE MEXICO S DE RL DE CV | SUBCON        | 103         | MX02       |
      | CONS_LATAM   | V                | 93808        | CH           |               | CILAG GMBH INTERNATIONAL - LF BEAUT | VENDOR        | 104         |            |
      | CONS_LATAM   | V                | 1189         |              |               | SUPPORT PACK IND E COM LTDA         | SUBCON        |             |            |
      | CONS_LATAM   | V                | 34427        | DO           | DOP           | YOBEL SRL                           | SUBCON        | 103         | DO01       |
      | CONS_LATAM   | V                | 87047        | BR           |               | ARCO IRIS MONT DE KITS E SHRINK PAC | SUBCON        | 103         | BR12       |
      | CONS_LATAM   | V                | 19574        |              |               | LODISAL S.A.                        |               |             |            |
      | CONS_LATAM   | V                | 79232        |              |               | Apl Logistics Chile S.A.            |               |             |            |
      | CONS_LATAM   | V                | 79300        | CL           | CLP           | Envases Visopack Ltda.              | SUBCON        | 103         | CL01       |
      | CONS_LATAM   | V                | 15574        | CO           | COP           | ASPRILLA ORTIZ FABIO                | SUBCON        | 103         | CO01       |
      | CONS_LATAM   | V                | 7303         |              |               | CILAG AG INTERNATIONAL              |               |             |            |
      | CONS_LATAM   | V                | 50161        |              |               | JOHNSON & JOHNSON CONSUMER INC.     |               |             |            |
      | CONS_LATAM   | V                | 44776        |              |               | SUPPLA SA                           |               |             |            |
      | CONS_LATAM   | V                | 20754        | PE           |               | GMPACK SERVICE SA                   | SUBCON        | 103         | PE01       |
      | CONS_LATAM   | V                | 36124        |              |               | J CAIN & CO                         |               |             |            |
      | CONS_LATAM   | V                | 53032        | BR           |               | JBS S/A                             | VENDOR        | 103         |            |
      | CONS_LATAM   | V                | 20667        | PE           | USD           | YOBEL SUPPLY CHAIN MANAGEMENT S.A.  | SUBCON        | 103         | PE01       |
      | CONS_LATAM   | V                | 70409        | BR           |               | JBS S A                             | VENDOR        | 103         |            |
      | CONS_LATAM   | V                | 36191        | GT           | GTQ           | DHL GLOBAL FORWARDING (GUATEMALA)   | SUBCON        | 103         | GT01       |
      | CONS_LATAM   | V                | 50448        | EC           |               | UBA SOLUCIONES INDUSTRIALES S.A.    | SUBCON        | 103         | EC01       |
      | CONS_LATAM   | V                | 36328        |              |               | D.H.L. COSTA RICA (CORMAR) S.A.     |               |             |            |
      | CONS_LATAM   | V                | 28           | BR           |               | FARMAQUI IND E COM LTDA EPP         | SUBCON        | 103         | BR12       |
      | CONS_LATAM   | V                | 12398        | BR           |               | DAVISO IND COM PROD HIGIENICOS SA   | VENDOR        | 103         |            |
      | CONS_LATAM   | C                | 000000012399 | BR           |               | DAVISO IND COM PROD HIGIENICOS SA   | VENDOR        | 103         |            |
      | CONS_LATAM   | V                | 000000012400 | BR           |               | DAVISO IND COM PROD HIGIENICOS SA   | VENDOR        | 103         |            |

    And I wait "/plan/cns_spl_pln_loc" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmLocationXref.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMLocation_xref.tsv"

    Then I check file data for filename "GDMLocation_xref.tsv" by keyFields "locationId"
      | locationTypeId | regionId | activeFCTERP | locationId            | customerId   | active | vendorId     | label                               | currencyId | activeOPRERP | countryId | activeSOPERP |
      | VENDOR         | 103      | NO           | CONS_LATAM_V_12398    |              | YES    | 12398        | DAVISO IND COM PROD HIGIENICOS SA   | USD        | YES          | BR        | NO           |
      | SUBCON         | 103      | NO           | CONS_LATAM_BR12$87047 |              | YES    | 87047        | ARCO IRIS MONT DE KITS E SHRINK PAC | USD        | YES          | BR        | NO           |
      | SUBCON         | 103      | NO           | CONS_LATAM_EC01$49139 |              | YES    | 49139        | CORPORACION DE PROYECTOS EQUINOCCIA | USD        | YES          | EC        | NO           |
      | SUBCON         | 103      | NO           | CONS_LATAM_CO01$15574 |              | YES    | 15574        | ASPRILLA ORTIZ FABIO                | USD        | YES          | CO        | NO           |
      | SUBCON         | 103      | NO           | CONS_LATAM_PE01$20754 |              | YES    | 20754        | GMPACK SERVICE SA                   | USD        | YES          | PE        | NO           |
      | VENDOR         | 103      | NO           | CONS_LATAM_V_70409    |              | YES    | 70409        | JBS S A                             | USD        | YES          | BR        | NO           |
      |                |          | NO           | CONS_LATAM_V_19574    |              | YES    | 19574        | LODISAL S.A.                        | USD        | YES          |           | NO           |
      | VENDOR         | 103      | NO           | CONS_LATAM_V_53032    |              | YES    | 53032        | JBS S/A                             | USD        | YES          | BR        | NO           |
      |                |          | NO           | CONS_LATAM_V_50161    |              | YES    | 50161        | JOHNSON & JOHNSON CONSUMER INC.     | USD        | YES          |           | NO           |
      | SUBCON         | 103      | NO           | CONS_LATAM_UY01$65659 |              | YES    | 65659        | MODYLER SA                          | USD        | YES          | UY        | NO           |
      | VENDOR         | 104      | NO           | CONS_LATAM_V_93808    |              | YES    | 93808        | CILAG GMBH INTERNATIONAL - LF BEAUT | USD        | YES          | CH        | NO           |
      | SUBCON         |          | NO           | CONS_LATAM_V_1189     |              | YES    | 1189         | SUPPORT PACK IND E COM LTDA         | USD        | YES          |           | NO           |
      |                |          | NO           | CONS_LATAM_V_36328    |              | YES    | 36328        | D.H.L. COSTA RICA (CORMAR) S.A.     | USD        | YES          |           | NO           |
      | VENDOR         | 103      | NO           | CONS_LATAM_C_12399    | 000000012399 | YES    |              | DAVISO IND COM PROD HIGIENICOS SA   | USD        | YES          | BR        | NO           |
      | SUBCON         | 103      | NO           | CONS_LATAM_GT01$36191 |              | YES    | 36191        | DHL GLOBAL FORWARDING (GUATEMALA)   | USD        | YES          | GT        | NO           |
      | SUBCON         | 103      | NO           | CONS_LATAM_BR12$28    |              | YES    | 28           | FARMAQUI IND E COM LTDA EPP         | USD        | YES          | BR        | NO           |
      |                |          | NO           | CONS_LATAM_V_36124    |              | YES    | 36124        | J CAIN & CO                         | USD        | YES          |           | NO           |
      | SUBCON         | 103      | NO           | CONS_LATAM_MX02$86017 |              | YES    | 86017        | AVP MAQUILA DE MEXICO S DE RL DE CV | USD        | YES          | MX        | NO           |
      | SUBCON         | 103      | NO           | CONS_LATAM_PE01$20667 |              | YES    | 20667        | YOBEL SUPPLY CHAIN MANAGEMENT S.A.  | USD        | YES          | PE        | NO           |
      |                |          | NO           | CONS_LATAM_V_7303     |              | YES    | 7303         | CILAG AG INTERNATIONAL              | USD        | YES          |           | NO           |
      | SUBCON         | 103      | NO           | CONS_LATAM_DO01$34427 |              | YES    | 34427        | YOBEL SRL                           | USD        | YES          | DO        | NO           |
      | SUBCON         | 103      | NO           | CONS_LATAM_CL01$79300 |              | YES    | 79300        | Envases Visopack Ltda.              | USD        | YES          | CL        | NO           |
      |                |          | NO           | CONS_LATAM_V_44776    |              | YES    | 44776        | SUPPLA SA                           | USD        | YES          |           | NO           |
      |                |          | NO           | CONS_LATAM_V_79232    |              | YES    | 79232        | Apl Logistics Chile S.A.            | USD        | YES          |           | NO           |
      | VENDOR         | 103      | NO           | CONS_LATAM_V_12400    |              | YES    | 000000012400 | DAVISO IND COM PROD HIGIENICOS SA   | USD        | YES          | BR        | NO           |
      | SUBCON         | 103      | NO           | CONS_LATAM_EC01$50448 |              | YES    | 50448        | UBA SOLUCIONES INDUSTRIALES S.A.    | USD        | YES          | EC        | NO           |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/plan/cns_spl_pln_loc" and "/omp/gdm_location,/plan/edm_failed_data"

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/omp/gdm_location"

    And I will remove all data with region "/plan/edm_failed_data"

  Scenario: merge file

    When I execute xd job to merge file "GDMLocation_*" to "GDMLocation.tsv" by keyFields "locationId"

    Then I check file data for filename "GDMLocation.tsv" by keyFields "locationId"
      | locationTypeId | regionId | activeFCTERP | locationId            | customerId   | active | vendorId     | label                               | currencyId | activeOPRERP | countryId | activeSOPERP |
      | typeid001      | regin001 | YES          | CONS_LATAM_AR_C1      |              | YES    |              | Pilar Plant                         | AFA        | YES          | 00        | NO           |
      | typeid001      | regin001 | YES          | CONS_LATAM_AR1_T5     |              | YES    |              | Pilar Plant                         | AFA        | YES          | 00        | NO           |
      | typeid001      | regin001 | YES          | CONS_LATAM_AR1_T6     |              | YES    |              | Pilar Plant                         | AFA        | NO           | 00        | NO           |
      | typeid001      | regin001 | NO           | CONS_LATAM_AR1_T2     |              | YES    |              | Pilar Plant                         | AFA        | YES          | 00        | NO           |
      | typeid001      | regin001 | YES          | CONS_LATAM_AR1_T9     |              | YES    |              | Pilar Plant                         | AFA        | YES          | 00        | NO           |
      | typeid001      | regin001 | YES          | CONS_LATAM_AR1_T7     |              | YES    |              | Pilar Plant                         | AFA        | YES          | 00        | NO           |
      | typeid001      | regin001 | YES          | CONS_LATAM_AR_T2      |              | YES    |              | Pilar Plant                         | AFA        | YES          | 00        | NO           |
      | VENDOR         | 103      | NO           | CONS_LATAM_V_12398    |              | YES    | 12398        | DAVISO IND COM PROD HIGIENICOS SA   | USD        | YES          | BR        | NO           |
      | SUBCON         | 103      | NO           | CONS_LATAM_BR12$87047 |              | YES    | 87047        | ARCO IRIS MONT DE KITS E SHRINK PAC | USD        | YES          | BR        | NO           |
      | SUBCON         | 103      | NO           | CONS_LATAM_EC01$49139 |              | YES    | 49139        | CORPORACION DE PROYECTOS EQUINOCCIA | USD        | YES          | EC        | NO           |
      | SUBCON         | 103      | NO           | CONS_LATAM_CO01$15574 |              | YES    | 15574        | ASPRILLA ORTIZ FABIO                | USD        | YES          | CO        | NO           |
      | SUBCON         | 103      | NO           | CONS_LATAM_PE01$20754 |              | YES    | 20754        | GMPACK SERVICE SA                   | USD        | YES          | PE        | NO           |
      | VENDOR         | 103      | NO           | CONS_LATAM_V_70409    |              | YES    | 70409        | JBS S A                             | USD        | YES          | BR        | NO           |
      |                |          | NO           | CONS_LATAM_V_19574    |              | YES    | 19574        | LODISAL S.A.                        | USD        | YES          |           | NO           |
      | VENDOR         | 103      | NO           | CONS_LATAM_V_53032    |              | YES    | 53032        | JBS S/A                             | USD        | YES          | BR        | NO           |
      |                |          | NO           | CONS_LATAM_V_50161    |              | YES    | 50161        | JOHNSON & JOHNSON CONSUMER INC.     | USD        | YES          |           | NO           |
      | SUBCON         | 103      | NO           | CONS_LATAM_UY01$65659 |              | YES    | 65659        | MODYLER SA                          | USD        | YES          | UY        | NO           |
      | VENDOR         | 104      | NO           | CONS_LATAM_V_93808    |              | YES    | 93808        | CILAG GMBH INTERNATIONAL - LF BEAUT | USD        | YES          | CH        | NO           |
      | SUBCON         |          | NO           | CONS_LATAM_V_1189     |              | YES    | 1189         | SUPPORT PACK IND E COM LTDA         | USD        | YES          |           | NO           |
      |                |          | NO           | CONS_LATAM_V_36328    |              | YES    | 36328        | D.H.L. COSTA RICA (CORMAR) S.A.     | USD        | YES          |           | NO           |
      | VENDOR         | 103      | NO           | CONS_LATAM_C_12399    | 000000012399 | YES    |              | DAVISO IND COM PROD HIGIENICOS SA   | USD        | YES          | BR        | NO           |
      | SUBCON         | 103      | NO           | CONS_LATAM_GT01$36191 |              | YES    | 36191        | DHL GLOBAL FORWARDING (GUATEMALA)   | USD        | YES          | GT        | NO           |
      | SUBCON         | 103      | NO           | CONS_LATAM_BR12$28    |              | YES    | 28           | FARMAQUI IND E COM LTDA EPP         | USD        | YES          | BR        | NO           |
      |                |          | NO           | CONS_LATAM_V_36124    |              | YES    | 36124        | J CAIN & CO                         | USD        | YES          |           | NO           |
      | SUBCON         | 103      | NO           | CONS_LATAM_MX02$86017 |              | YES    | 86017        | AVP MAQUILA DE MEXICO S DE RL DE CV | USD        | YES          | MX        | NO           |
      | SUBCON         | 103      | NO           | CONS_LATAM_PE01$20667 |              | YES    | 20667        | YOBEL SUPPLY CHAIN MANAGEMENT S.A.  | USD        | YES          | PE        | NO           |
      |                |          | NO           | CONS_LATAM_V_7303     |              | YES    | 7303         | CILAG AG INTERNATIONAL              | USD        | YES          |           | NO           |
      | SUBCON         | 103      | NO           | CONS_LATAM_DO01$34427 |              | YES    | 34427        | YOBEL SRL                           | USD        | YES          | DO        | NO           |
      | SUBCON         | 103      | NO           | CONS_LATAM_CL01$79300 |              | YES    | 79300        | Envases Visopack Ltda.              | USD        | YES          | CL        | NO           |
      |                |          | NO           | CONS_LATAM_V_44776    |              | YES    | 44776        | SUPPLA SA                           | USD        | YES          |           | NO           |
      |                |          | NO           | CONS_LATAM_V_79232    |              | YES    | 79232        | Apl Logistics Chile S.A.            | USD        | YES          |           | NO           |
      | VENDOR         | 103      | NO           | CONS_LATAM_V_12400    |              | YES    | 000000012400 | DAVISO IND COM PROD HIGIENICOS SA   | USD        | YES          | BR        | NO           |
      | SUBCON         | 103      | NO           | CONS_LATAM_EC01$50448 |              | YES    | 50448        | UBA SOLUCIONES INDUSTRIALES S.A.    | USD        | YES          | EC        | NO           |
