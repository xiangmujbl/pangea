@pangea_test @AEAZ-5951
Feature: OMPGdmStock AEAZ-5951
  As a Data user, I want EDG to create GDM file for GDMStock 5 and send to OMP so that data can be consumed by OMP for planning

  Scenario: full load

    And I will remove the test file on sink application "GDMStock_plannedOrder.tsv"

    Given I import "/edm/planned_order_v1" by keyFields "mfgPlanOrdrDocId,srcSysCd"
      | firmingInd | fxScrapQty | grDaysLeadQty | matlNum | mfgPlanOrdrDocId | mrpCtlId | planOrdrEndDt | planOrdrEndTm | planOrdrQty | planOrdrStrtDt | planOrdrTypeCd | plntCd | plngScnroCd | prcmtTypeCd | prdtnFnshdDt | prdtnStrtDt | prdtnStrtTm | prdtnVers | prdtnVersNum | reqQty | sLocCd | splPrcmtTypeCd | srcSysCd   | uomCd |
      | X          | 0          | 1             | 138821  | 49748397         | H00      | 20180904      | 0             | 36300       | 20180903       | LA             | BR12   | 000         | E           | 0            | 0           | 0           | V001      | 0            | 0      | BR28   | E              | CONS_LATAM | EA    |
      | X          | 0          | 1             | 138821  | 49748398         | H00      | 20180508      | 0             | 38016       | 20180507       | LA             | BR12   | 000         | E           | 0            | 0           | 0           | V001      | 0            | 0      | BR28   | E              | CONS_LATAM | EA    |
      | X          | 0          | 1             | 138821  | 49748399         | H00      | 20190319      | 0             | 72000       | 20190318       | LA             | BR12   | 000         | E           | 0            | 0           | 0           | V001      | 0            | 0      | BR28   | E              | CONS_LATAM | EA    |
      | X          | 0          | 2             | 189915  | 95941803         | C11      | 20181203      | 0             | 86544       | 20181203       | LA             | BR12   | 000         | E           | 0            | 0           | 0           | V001      | 0            | 0      | BR28   | E              | CONS_LATAM | EA    |
      |            | 0          | 0             | 213481  | 112368557        | C61      | 20180808      | 240000        | 5600        | 20180808       | LA             | BR12   | 000         | E           | 0            | 0           | 0           | V001      | 0            | 0      | CO05   | E              | CONS_LATAM | L     |
      | X          | 0          | 6             | 441423  | 117229550        | A04      | 20180621      | 0             | 12132       | 20180620       | LA             | BR12   | 000         | E           | 0            | 0           | 0           | V001      | 0            | 0      | BR23   | E              | CONS_LATAM | EA    |
      | X          | 0          | 6             | 654321  | 117229550        | A04      | 20180621      | 0             | 12132       | 20180620       | LA             | BR12   | 000         | E           | 0            | 0           | 0           | V001      | 0            | 0      | BR23   | E              | CONS_LATAM | EA    |
      | X          | 0          | 2             | 138821  | 112370817        | 51       | 20190318      | 0             | 31200       | 20190318       | LA             | BR12   | 000         | E           | 0            | 0           | 0           | V001      | 0            | 0      | BR26   | E              | CONS_LATAM | EA    |
      | X          | 0          | 7             | 441346  | 117076013        | C61      | 20190423      | 0             | 26400       | 20190422       | LA             | AR01   | 000         | E           | 0            | 0           | 0           | V001      | 0            | 0      | ARO1   | E              | CONS_LATAM | EA    |
      | X          | 0          | 2             | 174461  | 112484003        | 51       | 20190318      | 0             | 31200       | 20190318       | LA             | CO01   | 000         | E           | 0            | 0           | 0           | V001      | 0            | 0      | CO01   | E              | CONS_LATAM | EA    |
      | X          | 0          | 7             | 441346  | 117076013        | C61      | 20190423      | 0             | 26400       | 20190422       | LA             | AR01   | 000         | E           | 0            | 0           | 0           | V001      | 0            | 0      | AR01   | E              | CONS_LATAM | EA    |
      | X          | 0          | 7             | 441349  | 117076014        | C61      | 20190423      | 0             | 26400       | 20190422       | LA             | AR01   | 001         | E           | 0            | 0           | 0           | V001      | 0            | 0      | AR01   | E              | CONS_LATAM | EA    |
      | X          | 0          | 7             | 441363  | 117076014        | C61      | 20190423      | 0             | 26400       | 20190422       | LA             | AR01   | 000         | E           | 0            | 0           | 0           | V001      | 0            | 0      | AR01   | E              | CONS_LATAM | EA    |
    And I wait "/edm/planned_order_v1" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber,sourceSystem"
      | baseUom | batchManageIndicator | brand  | category | division | form   | franchise | getPrimaryPlanningCode | globalBusinessUnit | globalDpParentCode | localBaseUom | localDpParentCode | localManufacturingTechnology | localMaterialGroup | localMaterialNumber | localMaterialType | localRefDescription                      | materialNumber | materialStatus | materialType | minRemShelfLife | parentCode | primaryPlanningCode | productFamily | refDescription                           | refdescription | sourceSystem | subBrand | totalShelfLife |
      | EA      | X                    | BRD006 | 280      | 10       | 112684 | FCH002    |                        | GFO001             |                    | EA           | 78910109421200000 | Toiletries BRA               | 1                  | 138821              | FERT              | NTG SUNFRESH FPS60 FACIAL OC COR 50ML    | 138821         | 8              | FERT         | 180             |            | 138821              |               | NTG SUNFRESH FPS60 FACIAL OC COR 50ML    |                | CONS_LATAM   | 3G       | 730            |
      | EA      | X                    |        |          | 10       |        |           |                        |                    |                    | EA           |                   |                              | 1                  | 138821              | FERT              | STAY ULTRADELGADA DP AZUL 6X60 UN        | 138821         | 8              | FERT         | 180             |            | 138821              |               | STAY ULTRADELGADA DP AZUL 6X60 UN        |                | CONS_LATAM   |          | 730            |
      | EA      | X                    | BRD014 | 135      | 10       | 100367 | FCH003    |                        | GFO002             |                    | EA           | 78910109255430000 | Toothbrushes                 | 1                  | 138821              | FERT              | ESC J&J REACH ECO ESSEN MC 72UN          | 138821         | 8              | FERT         | 180             |            | 138821              |               | ESC J&J REACH ECO ESSEN MC 72UN          |                | CONS_LATAM   | 5H       | 9999           |
      | EA      | X                    |        |          | 10       |        |           |                        |                    |                    | EA           |                   |                              | 1                  | 189915              | FERT              | LISTERINE NAT. CITRUS 12/500ML           | 189915         | 8              | FERT         | 180             |            | 189915              |               | LISTERINE NAT. CITRUS 12/500ML           |                | CONS_LATAM   |          | 730            |
      | EA      | X                    |        |          | 10       |        |           |                        |                    |                    | EA           |                   |                              | 1                  | 213481              | FERT              | NEU RAPD CLEAR MASK GEL 6X50GR           |                | 8              | FERT         | 180             |            | 213481              |               | NEU RAPD CLEAR MASK GEL 6X50GR           |                | CONS_LATAM   |          | 730            |
      | EA      | X                    |        |          | 10       |        |           |                        |                    |                    | EA           |                   |                              | 1                  | 441423              | FERT              | LISTERINE CUIDADO TOTAL 12X250ML         | 441423         | 8              | FERT         | 180             |            | 441423              |               | LISTERINE CUIDADO TOTAL 12X250ML         |                | CONS_LATAM   |          | 730            |
      | EA      | X                    |        |          | 10       |        |           |                        |                    |                    | EA           |                   |                              | 1                  | 654321              | SAPR              | FS SER CLARIFY ROC CLARIFIANT 1,5ML BIL  | 654321         | 8              | FERT         | 180             |            | 654321              |               | FS SER CLARIFY ROC CLARIFIANT 1,5ML BIL  |                | CONS_LATAM   |          | 730            |
      | L       | X                    |        |          | 10       |        |           |                        |                    |                    | L            |                   |                              | 2                  | 138821              | HALB              | Shampoo Cabellos Oscuros LATC 1459 - 024 | 138821         | 8              | HALB         | 1               |            | 138821              |               | Shampoo Cabellos Oscuros LATC 1459 - 024 |                | CONS_LATAM   |          | 730            |
      | EA      | X                    |        |          | 10       |        |           |                        |                    |                    | EA           |                   |                              | 3                  | 441346              | FERT              | JB JABON NEUTRO 75G 2 PACK C/24          | 441346         | 8              | FERT         | 365             |            | 441346              |               | JB JABON NEUTRO 75G 2 PACK C/24          |                | CONS_LATAM   |          | 730            |
      | L       | X                    |        |          | 10       |        |           |                        |                    |                    | L            |                   |                              | 2                  | 174461              | HALB              | Shampoo Cabellos Oscuros LATC 1459 - 024 | 174461         | 8              | HALB         | 1               |            | 174461              |               | Shampoo Cabellos Oscuros LATC 1459 - 024 |                | CONS_LATAM   |          | 730            |
      | EA      | X                    |        |          | 10       |        |           |                        |                    |                    | EA           |                   |                              | 3                  | 441346              | FERT              | JB JABON NEUTRO 75G 2 PACK C/24          | 441346         | 8              | FERT         | 365             |            | 441346              |               | JB JABON NEUTRO 75G 2 PACK C/24          |                | CONS_LATAM   |          | 730            |
      | EA      | X                    |        |          | 10       |        |           |                        |                    |                    | EA           |                   |                              | 3                  | 441349              | FERT              | JB JABON NEUTRO 75G 2 PACK C/24          | 441350         | 8              | FERT         | 365             |            | 441351              |               | JB JABON NEUTRO 75G 2 PACK C/24          |                | CONS_LATAM   |          | 730            |
      | EA      | X                    |        |          | 10       |        |           |                        |                    |                    | EA           |                   |                              | 3                  | 441363              | FERT              | JB JABON NEUTRO 75G 2 PACK C/24          | 441364         | 8              | FERT         | 365             |            | 441365              |               | JB JABON NEUTRO 75G 2 PACK C/24          |                | CONS_LATAM   |          | 730            |

    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/edm/plant_v1" by keyFields "localPlant,sourceSystem"
      | country | localCountry | localCurrency | localPlanningRelevant | localPlant | localPlantName                   | localPlantType | plant | plantType               | region                                    | site                 | sourceSystem |
      | MX      | MX           | MXN           | X                     | MX02       | Sales & Marketing                | DC             | MX32  | DC, Distribution Center | MX, MEXICO MEXICO                         | Importadora DC       | CONS_LATAM   |
      | VE      | VE           | VEF           | X                     | VE02       | J&J VE-Valencia-DistribuiciÃ³n   | DC             | VE07  | DC, Distribution Center | VE, VENEZUELA VENEZUELA                   | Valencia DC          | CONS_LATAM   |
      | GT      | GT           | GTQ           | X                     | GT02       | J&J Guatemala - Bond Fiscal      | DC             | GT02  | DC, Distribution Center | GT, GUATEMALA GUATEMALA                   | Guatemala BF DC      | CONS_LATAM   |
      | PA      | PA           | PAB           | X                     | PA01       | J&J Panama - S&M Consumer        | DC             | PA05  | DC, Distribution Center | PA, PANAMA PANAMA                         | Panama DC            | CONS_LATAM   |
      | GT      | GT           | GTQ           | X                     | GT01       | J&J Guatemala - S&M Consumer     | DC             | GT01  | DC, Distribution Center | GT, GUATEMALA GUATEMALA                   | Guatemala DC         | CONS_LATAM   |
      | MX      | MX           | MXN           | X                     | MX01       | PT Importado                     | DC             | MX31  | DC, Distribution Center | MX, MEXICO MEXICO                         | PT Importadora DC    | CONS_LATAM   |
      | CL      | CL           | CLP           | X                     | CL01       | J&J CL-Santiago-Chile Consumer   | DC             | CL03  | DC, Distribution Center | CL, CHILE CHILE                           | Chile DC             | CONS_LATAM   |
      | PY      | PY           | PYG           | X                     | PY01       | S & M Consumer                   | DC             | PY01  | DC, Distribution Center | PY, PARAGUAY PARAGUAY                     | Paraguay             | CONS_LATAM   |
      | PA      | PA           | PAB           | X                     | PA03       | J&J Panama - Zona Libre          | DC             | PA06  | DC, Distribution Center | PA, PANAMA PANAMA                         | Panama Zona Libre DC | CONS_LATAM   |
      | BR      | BR           | BRL           | X                     | BR07       | J&J BR-JoÃ£o Pessoa-COM&Distrib  | DC             | BR54  | DC, Distribution Center | BR, BRAZIL BRAZIL                         | Pessoa DC            | CONS_LATAM   |
      | VE      | VE           | VEF           | X                     | VE01       | J&J VE-Valencia-Planta           | DC             | VE06  | MP, Manufacturing Plant | VE, VENEZUELA VENEZUELA                   | Valencia             | CONS_LATAM   |
      | UY      | UY           | UYU           | X                     | UY05       | S & M Consumer RecintoAduanero   | DC             | UY05  | DC, Distribution Center | UY, URUGUAY URUGUAY                       | Recinto Aduanero     | CONS_LATAM   |
      | CR      | CR           | CRC           | X                     | CR01       | J&J Costa Rica - S&M Consumer    | DC             | CR01  | DC, Distribution Center | CR, COSTA RICA COSTA RICA                 | Costa Rica DC        | CONS_LATAM   |
      | UY      | UY           | UYU           | X                     | UY01       | S & M Consumer                   | DC             | UY07  | DC, Distribution Center | UY, URUGUAY URUGUAY                       | Uruguay              | CONS_LATAM   |
      | AR      | AR           | ARS           | X                     | AR01       | Pilar Plant                      | DC             | AR06  | MP, Manufacturing Plant | AR, ARGENTINA ARGENTINA                   | Pilar                | CONS_LATAM   |
      | DO      | DO           | DOP           | X                     | DO01       | J&J DO-S.Domingos 1-R.Dominica   | DC             | DO02  | DC, Distribution Center | DO, DOMINICAN REPUBLIC DOMINICAL REPUBLIC | Dominica             | CONS_LATAM   |
      | CO      | CO           | BRL           | X                     | CO01       | J&J Colombia - LADS (CO01)       | DC             | CO07  | MP, Manufacturing Plant | CO, COLOMBIA COLOMBIA                     | Cali                 | CONS_LATAM   |
      | VE      | VE           | VEF           | X                     | VE05       | J&J VE-Valencia-ETHNOR           |                | VE05  | DC, Distribution Center | VE, VENEZUELA VENEZUELA                   | Valencia Ethnor DC   | CONS_LATAM   |
      | AR      | AR           | ARS           | X                     | AR02       | S & M Pilar                      | DC             | AR07  | DC, Distribution Center | AR, ARGENTINA ARGENTINA                   | Pilar DC             | CONS_LATAM   |
      | BR      | BR           | BRL           | X                     | BR12       | J&J BR SÃ£o JosÃ© Campos - Indus | DC             | BR59  | MP, Manufacturing Plant | BR, BRAZIL BRAZIL                         | Sao Jose dos Campos  | CONS_LATAM   |
      | PE      | PE           | PEN           | X                     | PE01       | J&J PE-Lima-S&M                  | DC             | PE03  | DC, Distribution Center | PE, PERU PERU                             | Peru DC              | CONS_LATAM   |
      | BR      | BR           | BRL           | X                     | BR25       | J&J BR-GoiÃ¢nia - Com&Distrib    | DC             | BR69  | DC, Distribution Center | BR, BRAZIL BRAZIL                         | Goiania DC           | CONS_LATAM   |
      | BR      | BR           | BRL           | X                     | BR19       | J&J BR-Nova Odessa - Com&Distr   | DC             | BR63  | DC, Distribution Center | BR, BRAZIL BRAZIL                         | Nova Odessa DC       | CONS_LATAM   |
      | CR      | CR           | CRC           | X                     | CR02       | J&J Costa Rica - Bond Fiscal     | DC             | CR02  | DC, Distribution Center | CR, COSTA RICA COSTA RICA                 | Costa Rica BF DC     | CONS_LATAM   |
      | CO      | CO           | BRL           | X                     | CO02       | J&J Colombia - S&M (CO02)        | DC             | CO08  | DC, Distribution Center | CO, COLOMBIA COLOMBIA                     | Cali DC              | CONS_LATAM   |
      | BR      | BR           | BRL           | X                     | BR16       | J&J BR-Extrema - Com&Distrib     | DC             | BR61  | DC, Distribution Center | BR, BRAZIL BRAZIL                         | Extrema DC           | CONS_LATAM   |
      | EC      | EC           | BRL           | X                     | EC01       | Johnson & Johnson del Ecuador    | DC             | EC03  | DC, Distribution Center | EC, ECUADOR ECUADOR                       | Ecuador DC           | CONS_LATAM   |
    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/plan/cns_material_plan_status" by keyFields "localMaterialNumber,localPlant,sourceSystem"
      | active | dpRelevant | localMaterialNumber | localParentCode   | localPlant | materialNumber | noPlanRelevant | parentActive | ppc    | sourceSystem | spRelevant |
      | X      |            | 138821              |                   | BR12       | 138821         | X              |              | 138821 | CONS_LATAM   |            |
      | X      | X          | 138821              | 78912700000000000 | BR12       | 138821         | X              | X            | 138821 | CONS_LATAM   |            |
      | X      | X          | 138821              | 78910100000000000 | BR12       | 138821         | X              | X            | 138821 | CONS_LATAM   |            |
      | X      | X          | 189915              | 580100240         | BR12       | 189915         | X              | X            | 189915 | CONS_LATAM   |            |
      | X      | X          | 213481              | 78910100000000000 | BR12       | 213481         | X              | X            | 213481 | CONS_LATAM   |            |
      | X      | X          | 441423              | 975930150         | BR12       | 441423         | X              | X            | 441423 | CONS_LATAM   | X          |
      | X      |            | 654321              |                   | BR12       | 654321         | X              |              | 654321 | CONS_LATAM   | X          |
      | X      |            | 138821              |                   | BR12       | 138821         | X              |              | 138821 | CONS_LATAM   |            |
      | X      |            | 441346              |                   | AR01       | 441346         | X              |              | 441346 | CONS_LATAM   | X          |
      | X      | X          | 174461              |                   | CO01       | 138821         | X              |              | 138821 | CONS_LATAM   |            |
      | X      | X          | 441346              |                   | AR01       | 441346         | X              |              | 441346 | CONS_LATAM   | X          |
      | X      | X          | 441349              |                   | AR01       | 441350         | X              |              | 441351 | CONS_LATAM   | X          |
      | X      | X          | 441363              |                   | AR01       | 441364         | X              |              | 441365 | CONS_LATAM   | X          |

    And I wait "/plan/cns_material_plan_status" Async Queue complete

    Given I import "/plan/cns_plan_object_filter" by keyFields "sourceObjectAttribute1,sourceObjectAttribute2,sourceObjectAttribute2Value,sourceObjectTechName,sourceSystem"
      | inclusionExclusion | sourceObjectAttribute1 | sourceObjectAttribute1Value | sourceObjectAttribute2 | sourceObjectAttribute2Value | sourceObjectTechName | sourceSystem |
      | I                  | plntCd                 | BR12                        | planOrdrTypeCd         | NB                          | planned_order        | CONS_LATAM   |
      | I                  | plntCd                 | CO01                        | planOrdrTypeCd         | NB                          | planned_order        | CONS_LATAM   |
      | I                  | plntCd                 | AR01                        | planOrdrTypeCd         | NB                          | planned_order        | CONS_LATAM   |
      | I                  | plntCd                 | BR12                        | planOrdrTypeCd         | LA                          | planned_order        | CONS_LATAM   |
      | I                  | plntCd                 | CO01                        | planOrdrTypeCd         | LA                          | planned_order        | CONS_LATAM   |
      | I                  | plntCd                 | AR01                        | planOrdrTypeCd         | LA                          | planned_order        | CONS_LATAM   |
      | E                  | plntCd                 | AE01                        | planOrdrTypeCd         | LA                          | planned_order        | CONS_LATAM   |

    And I wait "/plan/cns_plan_object_filter" Async Queue complete

    When I submit task with xml file "xml/gdm_stock_5_planned_order.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMStock_plannedOrder.tsv"
    Then I check file data for filename "GDMStock_plannedOrder.tsv" by keyFields "stockId"
#    Then I check region data "/omp/gdm_stock" by keyFields "stockId"
#      | stockId                          | active | activeOPRERP | activeSOPERP | batchId | blockedQuantity | consignment | certaintyId | erpOrderId | inventoryLinkGroupId | vendorId | locationId      | processId               | processTypeId | productId | qualityQuantity | quantity | receiptDate         | restrictedQuantity | returnsQuantity | startDate           | stockType | transferQuantity | transitDate         | unrestrictedQuantity |
#      | 138821/CONS_LATAM_BR12/49748397  | YES    | YES          | NO           |         | 0.0             | NO          | PA          | 49748397   |                      |          | CONS_LATAM_BR12 | /138821/CONS_LATAM_BR12 | Production    | 138821    | 0.0             | 36300    | 2018/09/04 00:00:00 | 0.0                | 0.0             | 2018/09/05 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
#      | 189915/CONS_LATAM_BR12/95941803  | YES    | YES          | NO           |         | 0.0             | NO          | PA          | 95941803   |                      |          | CONS_LATAM_BR12 | /189915/CONS_LATAM_BR12 | Production    | 189915    | 0.0             | 86544    | 2018/12/03 00:00:00 | 0.0                | 0.0             | 2018/12/05 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
#      | 138821/CONS_LATAM_BR12/112370817 | YES    | YES          | NO           |         | 0.0             | NO          | PA          | 112370817  |                      |          | CONS_LATAM_BR12 | /138821/CONS_LATAM_BR12 | Production    | 138821    | 0.0             | 86544    | 2019/03/18 00:00:00 | 0.0                | 0.0             | 2019/03/20 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
#      | 174461/CONS_LATAM_CO01/112484003 | YES    | YES          | NO           |         | 0.0             | NO          | PA          | 112484003  |                      |          | CONS_LATAM_CO01 | /174461/CONS_LATAM_CO01 | Production    | 174461    | 0.0             | 86544    | 2019/03/18 00:00:00 | 0.0                | 0.0             | 2019/03/20 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
      | stockId                          | transitDate         | processTypeId | returnsQuantity | inventoryLinkGroupId | vendorId | batchId | activeOPRERP | activeSOPERP | transferQuantity | qualityQuantity | processId                | locationId      | consignment | erpOrderId | quantity | productId | stockType | receiptDate         | active | blockedQuantity | unrestrictedQuantity | certaintyId | restrictedQuantity | startDate           |
      | 138821/CONS_LATAM_BR12/112370817 | 1980/01/01 00:00:00 | Production    | 0.0             |                      |          |         | YES          | NO           | 0.0              | 0.0             | 0/138821/CONS_LATAM_BR12 | CONS_LATAM_BR12 | NO          | 112370817  | 31200    | 138821    | movement  | 2019/03/18 00:00:00 | YES    | 0.0             | 0.0                  | PA          | 0.0                | 2019/03/20 00:00:00 |
      | 138821/CONS_LATAM_BR12/49748397  | 1980/01/01 00:00:00 | Production    | 0.0             |                      |          |         | YES          | NO           | 0.0              | 0.0             | 0/138821/CONS_LATAM_BR12 | CONS_LATAM_BR12 | NO          | 49748397   | 36300    | 138821    | movement  | 2018/09/04 00:00:00 | YES    | 0.0             | 0.0                  | PA          | 0.0                | 2018/09/05 00:00:00 |
      | 138821/CONS_LATAM_BR12/49748398  | 1980/01/01 00:00:00 | Production    | 0.0             |                      |          |         | YES          | NO           | 0.0              | 0.0             | 0/138821/CONS_LATAM_BR12 | CONS_LATAM_BR12 | NO          | 49748398   | 38016    | 138821    | movement  | 2018/05/08 00:00:00 | YES    | 0.0             | 0.0                  | PA          | 0.0                | 2018/05/09 00:00:00 |
      | 138821/CONS_LATAM_BR12/49748399  | 1980/01/01 00:00:00 | Production    | 0.0             |                      |          |         | YES          | NO           | 0.0              | 0.0             | 0/138821/CONS_LATAM_BR12 | CONS_LATAM_BR12 | NO          | 49748399   | 72000    | 138821    | movement  | 2019/03/19 00:00:00 | YES    | 0.0             | 0.0                  | PA          | 0.0                | 2019/03/20 00:00:00 |
      | 174461/CONS_LATAM_CO01/112484003 | 1980/01/01 00:00:00 | Production    | 0.0             |                      |          |         | YES          | NO           | 0.0              | 0.0             | 0/174461/CONS_LATAM_CO01 | CONS_LATAM_CO01 | NO          | 112484003  | 31200    | 174461    | movement  | 2019/03/18 00:00:00 | YES    | 0.0             | 0.0                  | PA          | 0.0                | 2019/03/20 00:00:00 |
      | 189915/CONS_LATAM_BR12/95941803  | 1980/01/01 00:00:00 | Production    | 0.0             |                      |          |         | YES          | NO           | 0.0              | 0.0             | 0/189915/CONS_LATAM_BR12 | CONS_LATAM_BR12 | NO          | 95941803   | 86544    | 189915    | movement  | 2018/12/03 00:00:00 | YES    | 0.0             | 0.0                  | PA          | 0.0                | 2018/12/05 00:00:00 |
      | 213481/CONS_LATAM_BR12/112368557 | 1980/01/01 00:00:00 | Production    | 0.0             |                      |          |         | YES          | NO           | 0.0              | 0.0             | 0/213481/CONS_LATAM_BR12 | CONS_LATAM_BR12 | NO          | 112368557  | 5600     | 213481    | movement  | 2018/08/08 00:00:00 | YES    | 0.0             | 0.0                  | PA          | 0.0                | 2018/08/08 00:00:00 |
      | 441346/CONS_LATAM_AR01/117076013 | 1980/01/01 00:00:00 | Production    | 0.0             |                      |          |         | YES          | NO           | 0.0              | 0.0             | 0/441346/CONS_LATAM_AR01 | CONS_LATAM_AR01 | NO          | 117076013  | 26400    | 441346    | movement  | 2019/04/23 00:00:00 | YES    | 0.0             | 0.0                  | PA          | 0.0                | 2019/04/30 00:00:00 |
      | 441365/CONS_LATAM_AR01/117076014 | 1980/01/01 00:00:00 | Production    | 0.0             |                      |          |         | YES          | NO           | 0.0              | 0.0             | 0/441365/CONS_LATAM_AR01 | CONS_LATAM_AR01 | NO          | 117076014  | 26400    | 441365    | movement  | 2019/04/23 00:00:00 | YES    | 0.0             | 0.0                  | PA          | 0.0                | 2019/04/30 00:00:00 |
      | 654321/CONS_LATAM_BR12/117229550 | 1980/01/01 00:00:00 | Production    | 0.0             |                      |          |         | YES          | NO           | 0.0              | 0.0             | 0/654321/CONS_LATAM_BR12 | CONS_LATAM_BR12 | NO          | 117229550  | 12132    | 654321    | movement  | 2018/06/21 00:00:00 | YES    | 0.0             | 0.0                  | PA          | 0.0                | 2018/06/27 00:00:00 |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/omp/gdm_stock"

    And I will remove all data with region "/plan/edm_failed_data"
