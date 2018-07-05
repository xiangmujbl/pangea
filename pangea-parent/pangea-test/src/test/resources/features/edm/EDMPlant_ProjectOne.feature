@pangea_test @AEAZ-495
Feature: EDMPlant AEAZ-495


  Background:delete all test data

    And I will remove all data with region "/ems/ems_f_z_enterprise_plants"

    And I will remove all data with region "/edm/source_system_v1"

    And I will remove all data with region "/project_one/t001"

    And I will remove all data with region "/project_one/t001k"

    And I will remove all data with region "/project_one/t001w"

    And I will remove all data with region "/edm/edm_plant_input"

    And I will remove all data with region "/edm/country_v1"

    And I will remove all data with region "/edm/plant_v1"

    And I will remove all data with region "/plan/edm_failed_data"

  @Scenario1
  Scenario:  check rule  T1, J2, T4, T5
  Rule:
  >> T1: Get sourceSystem from source_system_v1 where localSourceSystem = ems_f_z_enterprise_plants -z_plant_source_system
  >> J2: Join plant_V1 and edm_plant_input, where -edm_plant_input-sourceSystem = plant_v1-sourceSystem AND edm_plant-input-localPlant = plant_v1-localPlant if Record is missing, set as blank
  >> T4: Get countryCode from country_v1 where localCountry = T001W-LAND1
  >> T5: parse zPlant with comma as a seperator and take take the second word.
  ems_f_z_enterprise_plants:
  >> T1:  line5.zPlantSourceSystem equals EMS not found in source_system_v1 skip this record
  >> J2:  line1.edm_plant_input-sourceSystem not equals  plant_v1-sourceSystem record is missing, set as blank
  >> J2:  line4.edm_plant-input-localPlant  not equals plant_v1-localPlant record is missing, set as blank
  >> T4： line2.Get countryCode from country_v1 where localCountry not equals T001W-LAND1
  >> T5:  line3.exception with  parse zPlant with comma as a seperator and take take the second word. skip this record
  edm_plant_input:
  >> J2:  line1.edm_plant_input-sourceSystem not equals  plant_v1-sourceSystem record is missing, set as blank
  >> J2:  line2.edm_plant-input-localPlant  not equals plant_v1-localPlant record is missing, set as blank
  t001w:
  >> T4:line3.Get countryCode from country_v1 where localCountry not equals T001W-LAND1

    Given I import "/ems/ems_f_z_enterprise_plants" by keyFields "zPlantSourceSystem,zPlant"
  #  ems_f_z_enterprise_plants:
  #  >> T1:  line5.zPlantSourceSystem equals EMS not found in source_system_v1 skip this record
  #  >> J2:  line1.edm_plant_input-sourceSystem not equals  plant_v1-sourceSystem record is missing, set as blank
  #  >> J2:  line4.edm_plant-input-localPlant  not equals plant_v1-localPlant record is missing, set as blank
  #  >> T4： line2.Get countryCode from country_v1 where localCountry not equals T001W-LAND1
  #  >> T5:  line3.exception with  parse zPlant with comma as a seperator and take take the second word. skip this record
      | zEntPlantNumber | zEntPlantType             | zFranchise                  | zPlant                                                 | zPlantSourceSystem | zRegion           | zSector  | zSite               |
      | BR54            | DC, Distribution Center   | [Consumer LATAM], LA, LATAM | [Consumer LATAM], BR07, J&J BR-Alhandra PB-Com&Distrib | [Consumer LATAM]   | BR, BRAZIL BRAZIL | Consumer | Pessoa DC           |
      | BR69            | DC, Distribution Center   | [Consumer LATAM], LA, LATAM | [Consumer LATAM], BR25, J&J BR-GoiaÌ‚nia - Com&Distrib | [Consumer LATAM]   | BR, BRAZIL BRAZIL | Consumer | Goiania DC          |
      | BR59            | MP, Manufacturing Plant   | [Consumer LATAM], LA, LATAM | BR12                                                   | [Consumer LATAM]   | BR, BRAZIL BRAZIL | Consumer | Sao Jose dos Campos |
      | BR63            | DC, Distribution Center   | [Consumer LATAM], LA, LATAM | [Consumer LATAM], BR19, J&J BR-Nova Odessa - Com&Distr | [Consumer LATAM]   | BR, BRAZIL BRAZIL | Consumer | Nova Odessa DC      |
      | ZZZZ            | DC, Distribution Center_1 | [Consumer LATAM], LA, LATAM | EMS, ZZZZ, J&J BR-Nova Odessa - Com&Distr              | EMS                | BR, BRAZIL BRAZIL | Consumer | Nova Odessa DC      |

    And I wait "/ems/ems_f_z_enterprise_plants" Async Queue complete


    And I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | localSourceSystemName | sourceSystem | sourceSystemName   |
      | [Consumer LATAM]  | Project One           | CONS_LATAM   | Consumer Latam Ent |

    And I wait "/edm/source_system_v1" Async Queue complete



    And I import "/project_one/t001" by keyFields "mandt,bukrs"
      | adrnr | bukrs | waers | assetid | bapovar | bukrsGlob | butxt                     | buvar | casedatetime | caseid | comments | deliverdatetime | deliveryby | deliverymethod | departmentattention | destinationucn | dkweg | dtamtc | dtaxr | dtprov | dttaxc | dttdsp | ebukr | fdbuk | fikrs | fmDeriveAcc | fmhrdate | fslid | fstva | fstvare | impda | infmt | kkber | kokfi | kopim | ktop2 | ktopl | land1 | mandt | mregl | mwska | mwskv | nameofpod | numberoftotes | offsacct | opvar | ordernumber | ordertype | ort01                 | periv | ppPdate | pstPerVar | rcomp | recordid | reservationid | salesrepwwid | sourceucn | specialinstructions | spras | status | stceg | surccm | surgeonname | trackinginfo | txjcd      | txkrs | umkrs | waabw | wfvar | wtNewwt | xbbba | xbbbe | xbbbf | xbbko | xcession | xcos | xcovr | xeink | xextb | xfdis | xfdmm | xfdsd | xfmca | xfmcb | xfmco | xgjrv | xgsbe | xjvaa | xkdft | xkkbi | xmwsn | xnegp | xprod | xskfn | xslta | xsplt | xstdt | xvalv | xvatdate | xvvwa |
      | 58894 | 7680  | BRL   |         |         | 7680      | JJ - INDUSTRIAL LTDA      | 2     |              |        |          |                 |            |                |                     |                |       |        |       |        |        |        |       |       | 1     |             | 0        |       | JJLA  |         |       |       | CCJJ  | 2     |       |       | PCJJ  | BR    | 120   |       | A0    | I0    |           |               | 0        | JJCO  |             |           | SÃ£o JosÃ© dos Campos | J1    |         |           | 3170  |          |               |              |           |                     | P     |        |       |        |             |              | XX 0000000 |       |       | 10    |       | X       |       |       |       |       |          | 2    |       |       |       | X     |       |       |       |       |       | X     |       |       | X     |       |       |       |       |       |       |       |       | X     |          |       |
      | 710   | 7610  | BRL   |         |         | 7610      | JJ BR IND COM PR SAUDE LT | 2     |              |        |          |                 |            |                |                     |                |       |        |       |        |        |        |       |       | 1     |             | 0        |       | JJLA  |         |       |       | CCJJ  | 2     |       |       | PCJJ  | BR    | 120   |       | A0    | I0    |           |               | 0        | JJCO  |             |           | SÃƒO PAULO            | J1    |         |           | 3170  |          |               |              |           |                     | P     |        |       |        |             |              | XX 0000000 |       |       | 10    |       | X       |       |       |       |       |          | 2    |       |       |       | X     |       |       |       |       |       | X     |       |       | X     |       |       |       |       |       |       |       |       | X     |          |       |

    And I wait "/project_one/t001" Async Queue complete

    And I import "/project_one/t001k" by keyFields "bwkey,mandt"
      | bukrs | bwkey | mandt |
      | 7610  | BR25  | 120   |
      | 7680  | BR12  | 120   |
      | 7610  | BR19  | 120   |
      | 7610  | BR07  | 120   |

    And I wait "/project_one/t001k" Async Queue complete

    And I import "/project_one/t001w" by keyFields "mandt,werks"
  #  t001w:
  #  >> T4:line3.Get countryCode from country_v1 where localCountry not equals T001W-LAND1

      | bwkey | land1 | mandt | name1                            | nodetype | werks |
      | BR07  | BR    | 120   | J&J BR-JoÃ£o Pessoa-COM&Distrib  | DC       | BR07  |
      | BR19  | BR    | 120   | J&J BR-Nova Odessa - Com&Distr   | DC       | BR19  |
      | BR25  | BC    | 120   | J&J BR-GoiÃ¢nia - Com&Distrib    | DC       | BR25  |
      | BR12  | BR    | 120   | J&J BR SÃ£o JosÃ© Campos - Indus |          | BR12  |

    And I wait "/project_one/t001w" Async Queue complete
#    edm_plant_input:
#    >> J2:  line1.edm_plant_input-sourceSystem not equals  plant_v1-sourceSystem record is missing, set as blank
#    >> J2:  line2.edm_plant-input-localPlant  not equals plant_v1-localPlant record is missing, set as blank

    And I import "/edm/edm_plant_input" by keyFields "sourceSystem,localPlant"

      | sourceSystem | localPlant | localPlanningRelevant |
      | EMS          | BR07       | X                     |
      | CONS_LATAM   | BR15       | X                     |
      | CONS_LATAM   | BR19       | X                     |
      | CONS_LATAM   | BR25       | X                     |

    And I wait "/edm/edm_plant_input" Async Queue complete

    And I import "/edm/country_v1" by keyFields "localCountry,sourceSystem"
      | consumerPlannRegDesc | consumerPlanningRegion | countryCode | countryName | localCountry | localCurrency | planningRegionId | sourceSystem |
      | LATAM                | 103                    | BR          | Brazil      | BR           |               |                  | CONS_LATAM   |

    And I wait "/edm/country_v1" Async Queue complete

    When I submit task with xml file "xml/edm/EDMPlant_ProjectOne.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/plant_v1" by keyFields "sourceSystem,localPlant"
      | sourceSystem | localPlant | localPlantName                  | plant | localPlanningRelevant | localCountry | country | site           | localPlantType | plantType               | localCurrency | region            |
      | CONS_LATAM   | BR07       | J&J BR-JoÃ£o Pessoa-COM&Distrib | BR54  |                       | BR           | BR      | Pessoa DC      | DC             | DC, Distribution Center | BRL           | BR, BRAZIL BRAZIL |
      | CONS_LATAM   | BR19       | J&J BR-Nova Odessa - Com&Distr  | BR63  | X                     | BR           | BR      | Nova Odessa DC | DC             | DC, Distribution Center | BRL           | BR, BRAZIL BRAZIL |
      | CONS_LATAM   | BR25       | J&J BR-GoiÃ¢nia - Com&Distrib   | BR69  | X                     | BC           |         | Goiania DC     | DC             | DC, Distribution Center | BRL           | BR, BRAZIL BRAZIL |


  @Scenario2
  Scenario: check rule  T2,T3,J1
  Rule:
  >> J1: Get WAERS form T001 by joining T001K-BWKEY = T001W-BWKEY and T001K-BUKRS = t001-BUKRS  where t001W-WERKS = plant_v1-localplant
  >> T2: Get NAME1 from T001W where WERKS = ems_f_z_enterprise_plants -z_plant WERKS = plant_v1-localplant
  >> T3: Get LAND11 from T001W where WERKS = plant_v1-localplant
  If J1 meet  T2,T3  also meet
  J1:
  >>line1->all pass
  >>line2->t001W-WERKS not equals plant_v1-localplant skip the record
  >>line3->T001K-BWKEY not equals  T001W-BWKEY skip the record
  >>line4->T001K-BUKRS not equals t001-BUKRS  skip the record

    Given I import "/ems/ems_f_z_enterprise_plants" by keyFields "zPlantSourceSystem,zPlant"

      | zEntPlantNumber | zEntPlantType             | zFranchise                  | zPlant                                                    | zPlantSourceSystem | zRegion           | zSector  | zSite               |
      | BR54            | DC, Distribution Center   | [Consumer LATAM], LA, LATAM | [Consumer LATAM], BR07, J&J BR-Alhandra PB-Com&Distrib    | [Consumer LATAM]   | BR, BRAZIL BRAZIL | Consumer | Pessoa DC           |
      | BR69            | DC, Distribution Center   | [Consumer LATAM], LA, LATAM | [Consumer LATAM], BR25, J&J BR-GoiaÌ‚nia - Com&Distrib    | [Consumer LATAM]   | BR, BRAZIL BRAZIL | Consumer | Goiania DC          |
      | BR59            | MP, Manufacturing Plant   | [Consumer LATAM], LA, LATAM | [Consumer LATAM], BR12, J&J BR SaÌƒo JoseÌ Campos - Indus | [Consumer LATAM]   | BR, BRAZIL BRAZIL | Consumer | Sao Jose dos Campos |
      | BR63            | DC, Distribution Center   | [Consumer LATAM], LA, LATAM | [Consumer LATAM], BR19, J&J BR-Nova Odessa - Com&Distr    | [Consumer LATAM]   | BR, BRAZIL BRAZIL | Consumer | Nova Odessa DC      |

    And I wait "/ems/ems_f_z_enterprise_plants" Async Queue complete


    And I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | localSourceSystemName | sourceSystem | sourceSystemName   |
      | [Consumer LATAM]  | Project One           | CONS_LATAM   | Consumer Latam Ent |

    And I wait "/edm/source_system_v1" Async Queue complete


  #  t001k.bukrs=7620 not found t001 where t001.bukrs=7620 and t001k.bukrs=BR19--J1
    And I import "/project_one/t001" by keyFields "mandt,bukrs"
      | adrnr | bukrs | waers | assetid | bapovar | bukrsGlob | butxt                     | buvar | casedatetime | caseid | comments | deliverdatetime | deliveryby | deliverymethod | departmentattention | destinationucn | dkweg | dtamtc | dtaxr | dtprov | dttaxc | dttdsp | ebukr | fdbuk | fikrs | fmDeriveAcc | fmhrdate | fslid | fstva | fstvare | impda | infmt | kkber | kokfi | kopim | ktop2 | ktopl | land1 | mandt | mregl | mwska | mwskv | nameofpod | numberoftotes | offsacct | opvar | ordernumber | ordertype | ort01                 | periv | ppPdate | pstPerVar | rcomp | recordid | reservationid | salesrepwwid | sourceucn | specialinstructions | spras | status | stceg | surccm | surgeonname | trackinginfo | txjcd      | txkrs | umkrs | waabw | wfvar | wtNewwt | xbbba | xbbbe | xbbbf | xbbko | xcession | xcos | xcovr | xeink | xextb | xfdis | xfdmm | xfdsd | xfmca | xfmcb | xfmco | xgjrv | xgsbe | xjvaa | xkdft | xkkbi | xmwsn | xnegp | xprod | xskfn | xslta | xsplt | xstdt | xvalv | xvatdate | xvvwa |
      | 58894 | 7680  | BRL   |         |         | 7680      | JJ - INDUSTRIAL LTDA      | 2     |              |        |          |                 |            |                |                     |                |       |        |       |        |        |        |       |       | 1     |             | 0        |       | JJLA  |         |       |       | CCJJ  | 2     |       |       | PCJJ  | BR    | 120   |       | A0    | I0    |           |               | 0        | JJCO  |             |           | SÃ£o JosÃ© dos Campos | J1    |         |           | 3170  |          |               |              |           |                     | P     |        |       |        |             |              | XX 0000000 |       |       | 10    |       | X       |       |       |       |       |          | 2    |       |       |       | X     |       |       |       |       |       | X     |       |       | X     |       |       |       |       |       |       |       |       | X     |          |       |
      | 710   | 7610  | BRL   |         |         | 7610      | JJ BR IND COM PR SAUDE LT | 2     |              |        |          |                 |            |                |                     |                |       |        |       |        |        |        |       |       | 1     |             | 0        |       | JJLA  |         |       |       | CCJJ  | 2     |       |       | PCJJ  | BR    | 120   |       | A0    | I0    |           |               | 0        | JJCO  |             |           | SÃƒO PAULO            | J1    |         |           | 3170  |          |               |              |           |                     | P     |        |       |        |             |              | XX 0000000 |       |       | 10    |       | X       |       |       |       |       |          | 2    |       |       |       | X     |       |       |       |       |       | X     |       |       | X     |       |       |       |       |       |       |       |       | X     |          |       |

    And I wait "/project_one/t001" Async Queue complete

    # bwkey=BR12 not found  --J1
    And I import "/project_one/t001k" by keyFields "bwkey,mandt"
      | bukrs | bwkey | mandt |
      | 7610  | BR25  | 120   |
      | 7620  | BR19  | 120   |
      | 7610  | BR07  | 120   |

    And I wait "/project_one/t001k" Async Queue complete

    # werks=BR25 not found  --J1
    And I import "/project_one/t001w" by keyFields "mandt,werks"
      | bwkey | land1 | mandt | name1                            | nodetype | werks |
      | BR07  | BR    | 120   | J&J BR-JoÃ£o Pessoa-COM&Distrib  | DC       | BR07  |
      | BR19  | BR    | 120   | J&J BR-Nova Odessa - Com&Distr   | DC       | BR19  |
      | BR12  | BR    | 120   | J&J BR SÃ£o JosÃ© Campos - Indus |          | BR12  |

    And I wait "/project_one/t001w" Async Queue complete

    And I import "/edm/edm_plant_input" by keyFields "sourceSystem,localPlant"
      | sourceSystem | localPlant | localPlanningRelevant |
      | CONS_LATAM   | BR07       | X                     |
      | CONS_LATAM   | BR12       | X                     |
      | CONS_LATAM   | BR19       | X                     |
      | CONS_LATAM   | BR25       | X                     |

    And I wait "/edm/edm_plant_input" Async Queue complete

    And I import "/edm/country_v1" by keyFields "localCountry,sourceSystem"
      | consumerPlannRegDesc | consumerPlanningRegion | countryCode | countryName | localCountry | localCurrency | planningRegionId | sourceSystem |
      | LATAM                | 103                    | BR          | Brazil      | BR           |               |                  | CONS_LATAM   |

    And I wait "/edm/country_v1" Async Queue complete

    When I submit task with xml file "xml/edm/EDMPlant_ProjectOne.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/plant_v1" by keyFields "sourceSystem,localPlant"
      | sourceSystem | localPlant | localPlantName                   | plant | localPlanningRelevant | localCountry | country | site                | localPlantType | plantType               | localCurrency | region            |
      | CONS_LATAM   | BR07       | J&J BR-JoÃ£o Pessoa-COM&Distrib  | BR54  | X                     | BR           | BR      | Pessoa DC           | DC             | DC, Distribution Center | BRL           | BR, BRAZIL BRAZIL |



  @Scenario3
  Scenario: Full Load curation

    Given I import "/ems/ems_f_z_enterprise_plants" by keyFields "zPlantSourceSystem,zPlant"

      | zEntPlantNumber | zEntPlantType             | zFranchise                  | zPlant                                                    | zPlantSourceSystem | zRegion           | zSector  | zSite               |
      | BR54            | DC, Distribution Center   | [Consumer LATAM], LA, LATAM | [Consumer LATAM], BR07, J&J BR-Alhandra PB-Com&Distrib    | [Consumer LATAM]   | BR, BRAZIL BRAZIL | Consumer | Pessoa DC           |
      | BR70            | DC, Distribution Center   | [Consumer LATAM], LA, LATAM | [Consumer LATAM], BR16, J&J BR-Alhandra PB-Com&Distrib    | [Consumer LATAM]   | BR, BRAZIL BRAZIL | Consumer | Pessoa DC           |
      | BR69            | DC, Distribution Center   | [Consumer LATAM], LA, LATAM | [Consumer LATAM], BR25, J&J BR-GoiaÌ‚nia - Com&Distrib    | [Consumer LATAM]   | BR, BRAZIL BRAZIL | Consumer | Goiania DC          |
      | BR59            | MP, Manufacturing Plant   | [Consumer LATAM], LA, LATAM | [Consumer LATAM], BR12, J&J BR SaÌƒo JoseÌ Campos - Indus | [Consumer LATAM]   | BR, BRAZIL BRAZIL | Consumer | Sao Jose dos Campos |
      | BR63            | DC, Distribution Center   | [Consumer LATAM], LA, LATAM | [Consumer LATAM], BR19, J&J BR-Nova Odessa - Com&Distr    | [Consumer LATAM]   | BR, BRAZIL BRAZIL | Consumer | Nova Odessa DC      |
      | ZZZZ            | DC, Distribution Center_1 | [Consumer LATAM], LA, LATAM | EMS, ZZZZ, J&J BR-Nova Odessa - Com&Distr                 | EMS                | BR, BRAZIL BRAZIL | Consumer | Nova Odessa DC      |

    And I wait "/ems/ems_f_z_enterprise_plants" Async Queue complete


    And I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | localSourceSystemName | sourceSystem | sourceSystemName   |
      | [Consumer LATAM]  | Project One           | CONS_LATAM   | Consumer Latam Ent |

    And I wait "/edm/source_system_v1" Async Queue complete



    And I import "/project_one/t001" by keyFields "mandt,bukrs"
      | adrnr | bukrs | waers | assetid | bapovar | bukrsGlob | butxt                     | buvar | casedatetime | caseid | comments | deliverdatetime | deliveryby | deliverymethod | departmentattention | destinationucn | dkweg | dtamtc | dtaxr | dtprov | dttaxc | dttdsp | ebukr | fdbuk | fikrs | fmDeriveAcc | fmhrdate | fslid | fstva | fstvare | impda | infmt | kkber | kokfi | kopim | ktop2 | ktopl | land1 | mandt | mregl | mwska | mwskv | nameofpod | numberoftotes | offsacct | opvar | ordernumber | ordertype | ort01                 | periv | ppPdate | pstPerVar | rcomp | recordid | reservationid | salesrepwwid | sourceucn | specialinstructions | spras | status | stceg | surccm | surgeonname | trackinginfo | txjcd      | txkrs | umkrs | waabw | wfvar | wtNewwt | xbbba | xbbbe | xbbbf | xbbko | xcession | xcos | xcovr | xeink | xextb | xfdis | xfdmm | xfdsd | xfmca | xfmcb | xfmco | xgjrv | xgsbe | xjvaa | xkdft | xkkbi | xmwsn | xnegp | xprod | xskfn | xslta | xsplt | xstdt | xvalv | xvatdate | xvvwa |
      | 58894 | 7680  | BRL   |         |         | 7680      | JJ - INDUSTRIAL LTDA      | 2     |              |        |          |                 |            |                |                     |                |       |        |       |        |        |        |       |       | 1     |             | 0        |       | JJLA  |         |       |       | CCJJ  | 2     |       |       | PCJJ  | BR    | 120   |       | A0    | I0    |           |               | 0        | JJCO  |             |           | SÃ£o JosÃ© dos Campos | J1    |         |           | 3170  |          |               |              |           |                     | P     |        |       |        |             |              | XX 0000000 |       |       | 10    |       | X       |       |       |       |       |          | 2    |       |       |       | X     |       |       |       |       |       | X     |       |       | X     |       |       |       |       |       |       |       |       | X     |          |       |
      | 710   | 7610  | BRL   |         |         | 7610      | JJ BR IND COM PR SAUDE LT | 2     |              |        |          |                 |            |                |                     |                |       |        |       |        |        |        |       |       | 1     |             | 0        |       | JJLA  |         |       |       | CCJJ  | 2     |       |       | PCJJ  | BR    | 120   |       | A0    | I0    |           |               | 0        | JJCO  |             |           | SÃƒO PAULO            | J1    |         |           | 3170  |          |               |              |           |                     | P     |        |       |        |             |              | XX 0000000 |       |       | 10    |       | X       |       |       |       |       |          | 2    |       |       |       | X     |       |       |       |       |       | X     |       |       | X     |       |       |       |       |       |       |       |       | X     |          |       |

    And I wait "/project_one/t001" Async Queue complete

    And I import "/project_one/t001k" by keyFields "bwkey,mandt"
      | bukrs | bwkey | mandt |
      | 7610  | BR25  | 120   |
      | 7680  | BR12  | 120   |
      | 7610  | BR19  | 120   |
      | 7610  | BR07  | 120   |

    And I wait "/project_one/t001k" Async Queue complete

    And I import "/project_one/t001w" by keyFields "mandt,werks"
      | bwkey | land1 | mandt | name1                            | nodetype | werks |
      | BR07  | BR    | 120   | J&J BR-JoÃ£o Pessoa-COM&Distrib  | DC       | BR07  |
      | BR19  | BR    | 120   | J&J BR-Nova Odessa - Com&Distr   | DC       | BR19  |
      | BR25  | BR    | 120   | J&J BR-GoiÃ¢nia - Com&Distrib    | DC       | BR25  |
      | BR12  | BR    | 120   | J&J BR SÃ£o JosÃ© Campos - Indus |          | BR12  |

    And I wait "/project_one/t001w" Async Queue complete

    And I import "/edm/edm_plant_input" by keyFields "sourceSystem,localPlant"
      | sourceSystem | localPlant | localPlanningRelevant |
      | CONS_LATAM   | BR07       | X                     |
      | CONS_LATAM   | BR12       | X                     |
      | CONS_LATAM   | BR19       | X                     |
      | CONS_LATAM   | BR25       | X                     |

    And I wait "/edm/edm_plant_input" Async Queue complete

    And I import "/edm/country_v1" by keyFields "localCountry,sourceSystem"
      | consumerPlannRegDesc | consumerPlanningRegion | countryCode | countryName | localCountry | localCurrency | planningRegionId | sourceSystem |
      | LATAM                | 103                    | BR          | Brazil      | BR           |               |                  | CONS_LATAM   |

    And I wait "/edm/country_v1" Async Queue complete

    When I submit task with xml file "xml/edm/EDMPlant_ProjectOne.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/plant_v1" by keyFields "sourceSystem,localPlant"
      | sourceSystem | localPlant | localPlantName                   | plant | localPlanningRelevant | localCountry | country | site                | localPlantType | plantType               | localCurrency | region            |
      | CONS_LATAM   | BR07       | J&J BR-JoÃ£o Pessoa-COM&Distrib  | BR54  | X                     | BR           | BR      | Pessoa DC           | DC             | DC, Distribution Center | BRL           | BR, BRAZIL BRAZIL |
      | CONS_LATAM   | BR19       | J&J BR-Nova Odessa - Com&Distr   | BR63  | X                     | BR           | BR      | Nova Odessa DC      | DC             | DC, Distribution Center | BRL           | BR, BRAZIL BRAZIL |
      | CONS_LATAM   | BR25       | J&J BR-GoiÃ¢nia - Com&Distrib    | BR69  | X                     | BR           | BR      | Goiania DC          | DC             | DC, Distribution Center | BRL           | BR, BRAZIL BRAZIL |
      | CONS_LATAM   | BR12       | J&J BR SÃ£o JosÃ© Campos - Indus | BR59  | X                     | BR           | BR      | Sao Jose dos Campos |                | MP, Manufacturing Plant | BRL           | BR, BRAZIL BRAZIL |
