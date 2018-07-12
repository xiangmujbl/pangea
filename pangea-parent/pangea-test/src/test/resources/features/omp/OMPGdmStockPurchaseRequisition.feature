@pangea_test @AEAZ-5952
Feature: OMPGdmStockPurchaseRequisition AEAZ-5952

  Scenario: Full Load curation
    Given I import "/edm/purchase_requisition_v1" by keyFields "prLineNbr,prNum,sourceSystem"
      | apprByDt | chngOnDt | crtByNm   | lineStsCd | localAgreementItem | localCurrency | localFixedVendor | localPDT | localPOQuantity | localPrGRLeadTimeDays | matlNum | needByDt | plntCd | poTypeCd | prLineNbr | prLineQty | prLineUomCd | prMrpHrzn | prNum      | prRqstDt | prStsCd | prTypeCd | prchInfoDesc                     | prchsngGrpNum | prchsngOrgNum | slocCd | sourceSystem | suplPlntCd | prLineCatCd | prCatCd | localSupNum |
      | 20190322 | 20180410 | ALEREMOTE | 2         | 5100006662         | BRL           | 93808            | 74       | 0               | 5                     | 170196  | 20190604 | BR12   | K        | 20        | 28836     | EA          | 20R       | 1173484245 | 20180410 | N       | NB       | COND OGX ARGAN OIL 6X250ML BIL   | B02           | BR00          | BR02   | CONS_LATAM   |            | 3           | B       |             |
      | 20190801 | 20180418 | ALEREMOTE | 2         |                    | PAB           | 13873            | 34       | 0               | 1                     | 174461  | 20190904 | CO01   |          | 10        | 4320      | EA          | 11R       | 1190921893 | 20180418 | N       | NB       | LUBRIDERM REGULAR 946 ML THERAPY | P06           | CO02          |        | CONS_LATAM   |            | 7           | B       |             |
      | 20140113 | 20131028 | ALEREMOTE | 2         | 5100003715         | MXN           | 6109             | 0        | 0               | 2                     | 181980  | 20140114 | CL01   | K        | 10        | 1212      | EA          | CPC       | 1414320436 | 20131017 | N       | NB       | JB SHAMP ROMERO PROT UV 12X400   | M39           | MX01          | MX01   | CONS_LATAM   | MX01       | 0           | B       |             |
      | 20140414 | 20131028 | ALEREMOTE | 2         | 5100003715         | MXN           | 6109             | 0        | 0               | 2                     | 55735   | 20140415 | MX02   | K        | 10        | 960       | EA          | CPC       | 1420948162 | 20131017 | N       | NB       | JB SHAMP ROMERO PROT UV 12X400   | M39           | MX01          | MX01   | CONS_LATAM   | MX01       | 0           | B       |             |
      | 20140310 | 20131221 | ALEREMOTE | 2         | 5100003715         | MXN           | 6109             | 0        | 0               | 2                     | 654321  | 20140311 | MX02   | K        | 10        | 696       | EA          | CPC       | 1230123123 | 20131017 | N       | NB       | JB SHAMP ROMERO PROT UV 12X400   | M39           | MX01          | MX01   | CONS_LATAM   | MX01       | 0           | B       |             |
      | 20140501 | 20131028 | ALEREMOTE | 2         | 5100005543         | MXN           | 4672             | 35       | 0               | 0                     | 189915  | 20140605 | BR12   | K        | 10        | 1104      | EA          | CPC       | 1420964649 | 20131017 | N       | NB       | J BABY SHAMPOO MANZAN   24X200   | M16           | MX00          | BR02   | CONS_LATAM   | BR12       | 0           | B       |             |
      | 20190125 | 20180410 | ALEREMOTE | 2         | 5100006662         | BRL           | 93808            | 74       | 0               | 2                     | 88360   | 20190409 | CO02   | K        | 10        | 28836     | EA          | 20R       | 1739829814 | 20180410 | N       | NB       | COND OGX ARGAN OIL 6X250ML BIL   | B02           | BR00          | BR02   | CONS_LATAM   |            | 0           | B       |             |
      | 20140501 | 20131028 | ALEREMOTE | 2         | 5100005543         | MXN           | 2345             | 35       | 0               | 0                     | 55755   | 20140605 | BR12   | K        | 10        | 1104      | EA          | CPC       | 1420964650 | 20131017 | N       | NB       | J BABY SHAMPOO MANZAN   24X200   | M16           | MX00          | BR02   | CONS_LATAM   | BR12       | 2           | B       |             |
      | 20140501 | 20131028 | ALEREMOTE | 2         | 5100005543         | MXN           | 6109             | 35       | 0               | 0                     | 88732   | 20140605 | BR12   | K        | 10        | 1104      | EA          | CPC       | 1420964651 | 20131017 | N       | NB       | J BABY SHAMPOO MANZAN   24X200   | M16           | MX00          | BR02   | CONS_LATAM   | BR12       | K           | B       |             |
    And I wait "/edm/purchase_requisition_v1" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber,sourceSystem"
      | localManufacturingTechnology | localMaterialGroup | localMaterialNumber | localMaterialType | localRefDescription                      | localbaseuom | localdpparentcode | localmanufacturingtechnology | localmaterialgroup | localmaterialnumber | localmaterialtype | localrefdescription | materialNumber | materialStatus | materialType | materialgroup | materialnumber | materialstatus | materialtype | minRemShelfLife | minremshelflife | parentCode | parentcode | primaryPlanningCode | primaryplanningcode | productFamily | productfamily | refDescription                           | refdescription | sourceSystem | sourceSystem25 | sourcesystem |
      |                              | 1                  | 170196              | FERT              | JB BANO LIQUIDO ANTES DE DORMIR 12X200   |              |                   |                              |                    |                     |                   |                     | 170196         | 8              | FERT         |               |                |                |              | 180             |                 |            |            | 170196              |                     |               |               | JB BANO LIQUIDO ANTES DE DORMIR 12X200   |                | CONS_LATAM   |                |              |
      |                              | 1                  | 174461              | FERT              | NEU.DEEP CLEAN ASTRING.6X200             |              |                   |                              |                    |                     |                   |                     | 174461         | 8              | FERT         |               |                |                |              | 180             |                 |            |            | 174461              |                     |               |               | NEU.DEEP CLEAN ASTRING.6X200             |                | CONS_LATAM   |                |              |
      |                              | 1                  | 181980              | FERT              | LUBRIDERM NUTRITIVA X 400 ML             |              |                   |                              |                    |                     |                   |                     | 181980         | 8              | FERT         |               |                |                |              | 180             |                 |            |            | 60408               |                     |               |               | LUBRIDERM NUTRITIVA X 400 ML             |                | CONS_LATAM   |                |              |
      |                              | 1                  | 55735               | FERT              | ENXAG R FLUORDENT MENTA 12X250ML EXP     |              |                   |                              |                    |                     |                   |                     | 55735          | 8              | FERT         |               |                |                |              | 274             |                 |            |            | 55735               |                     |               |               | ENXAG R FLUORDENT MENTA 12X250ML EXP     |                | CONS_LATAM   |                |              |
      |                              | 1                  | 654321              | FERT              | BIPACK CREMA LIQUIDA ORIGINAL 2X400 ML   |              |                   |                              |                    |                     |                   |                     | 69056          | 8              | FERT         |               |                |                |              | 180             |                 |            |            | 69056               |                     |               |               | BIPACK CREMA LIQUIDA ORIGINAL 2X400 ML   |                | CONS_LATAM   |                |              |
      |                              | 1                  | 55755               | FERT              | SHAMPOO MANZANILLA 12x200 (MOLLUSK)      |              |                   |                              |                    |                     |                   |                     | 55755          | 8              | FERT         |               |                |                |              | 146             |                 |            |            | 55755               |                     |               |               | SHAMPOO MANZANILLA 12x200 (MOLLUSK)      |                | CONS_LATAM   |                |              |
      |                              | 1                  | 88732               | FERT              | SHAMPOO MANZANILLA 12x200 (MOLLUSK)      |              |                   |                              |                    |                     |                   |                     | 88732          | 8              | FERT         |               |                |                |              | 146             |                 |            |            | 88732               |                     |               |               | SHAMPOO MANZANILLA 12x200 (MOLLUSK)      |                | CONS_LATAM   |                |              |
      |                              | 1                  | 189915              | FERT              | SHAMPOO MANZANILLA 12x200 (MOLLUSK)      |              |                   |                              |                    |                     |                   |                     | 189915         | 8              | FERT         |               |                |                |              | 146             |                 |            |            | 189915              |                     |               |               | SHAMPOO MANZANILLA 12x200 (MOLLUSK)      |                | CONS_LATAM   |                |              |
      |                              | 1                  | 88360               | FERT              | LIST TOTALCARE LLEVE 500 PAGUE 360, CX24 |              |                   |                              |                    |                     |                   |                     | 88360          | 8              | FERT         |               |                |                |              | 330             |                 |            |            | 88360               |                     |               |               | LIST TOTALCARE LLEVE 500 PAGUE 360, CX24 |                | CONS_LATAM   |                |              |
    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/plan/cns_material_plan_status" by keyFields "localMaterialNumber,localPlant,sourceSystem"
      | active | dpRelevant | localMaterialNumber | localParentCode | localPlant | materialNumber | noPlanRelevant | parentActive | ppc    | sourceSystem | spRelevant |
      | X      |            | 170196              |                 | BR12       | 170196         | X              |              | 170196 | CONS_LATAM   |            |
      | X      |            | 174461              |                 | CO01       | 174461         | X              |              | 174461 | CONS_LATAM   |            |
      | X      |            | 181990              |                 | CL01       | 181990         |                |              | 60408  | CONS_LATAM   | X          |
      | X      |            | 55735               |                 | MX02       | 55735          |                |              | 55735  | CONS_LATAM   | X          |
      | X      |            | 189915              |                 | BR12       | 189915         | X              |              | 189915 | CONS_LATAM   |            |
      | X      |            | 88360               |                 | CO02       | 88360          |                |              | 88360  | CONS_LATAM   | X          |
      |        |            | 88732               |                 | BR12       | 88732          | X              |              | 88732  | CONS_LATAM   |            |
      |        |            | 55755               |                 | BR12       | 55755          | X              |              | 55755  | CONS_LATAM   |            |
    And I wait "/plan/cns_material_plan_status" Async Queue complete

    Given I import "/plan/cns_plan_object_filter" by keyFields "sourceObjectAttribute1,sourceObjectAttribute1Value,sourceObjectAttribute2,sourceObjectAttribute2Value,sourceObjectTechName,sourceSystem"
      | comments | inclusionExclusion | sourceObjectAttribute1 | sourceObjectAttribute1Value | sourceObjectAttribute2 | sourceObjectAttribute2Value | sourceObjectTechName | sourceSystem |
      |          | I                  | plntCd                 | PA03                        | prTypeCd               | NB                          | purchase_requisition | CONS_LATAM   |
      |          | I                  | plntCd                 | UY01                        | prTypeCd               | NB                          | purchase_requisition | CONS_LATAM   |
      |          | I                  | plntCd                 | GT01                        | prTypeCd               | NB                          | purchase_requisition | CONS_LATAM   |
      |          | I                  | plntCd                 | BR12                        | prTypeCd               | NB                          | purchase_requisition | CONS_LATAM   |
      |          | I                  | plntCd                 | AR01                        | prTypeCd               | NB                          | purchase_requisition | CONS_LATAM   |
      |          | I                  | plntCd                 | EC01                        | prTypeCd               | NB                          | purchase_requisition | CONS_LATAM   |
      |          | I                  | plntCd                 | DO01                        | prTypeCd               | NB                          | purchase_requisition | CONS_LATAM   |
      |          | I                  | plntCd                 | CO02                        | prTypeCd               | EL                          | purchase_requisition | CONS_LATAM   |
      |          | I                  | plntCd                 | CR02                        | prTypeCd               | NB                          | purchase_requisition | CONS_LATAM   |
      |          | I                  | plntCd                 | MX02                        | prTypeCd               | NB                          | purchase_requisition | CONS_LATAM   |
      |          | I                  | plntCd                 | CO01                        | prTypeCd               | NB                          | purchase_requisition | CONS_LATAM   |
      |          | I                  | plntCd                 | CL01                        | prTypeCd               | NB                          | purchase_requisition | CONS_LATAM   |
      |          | I                  | plntCd                 | CR02                        | prTypeCd               | NB                          | purchase_requisition | CONS_LATAM   |
      |          | I                  | plntCd                 | CR01                        | prTypeCd               | NB                          | purchase_requisition | CONS_LATAM   |
      |          | I                  | plntCd                 | PE01                        | sourceObjectAttribute2 | NB                          | purchase_requisition | CONS_LATAM   |
      |          | I                  | plntCd                 | PA01                        | prTypeCd               | NB                          | purchase_requisition | CONS_LATAM   |
      |          | I                  | plntCd                 | CL01                        | prTypeCd               | NB                          | purchase_requisition | CONS_LATAM   |
      |          | I                  | plntCd                 | CO01                        | prTypeCd               | NB                          | purchase_requisition | CONS_LATAM   |
      |          | I                  | plntCd                 | BR12                        | prTypeCd               | NB                          | purchase_requisition | CONS_LATAM   |
      |          | I                  | plntCd                 | BR07                        | prTypeCd               | NB                          | purchase_requisition | CONS_LATAM   |
      |          | I                  | plntCd                 | MX01                        | prTypeCd               | NB                          | purchase_requisition | CONS_LATAM   |
      |          | I                  | plntCd                 | AR02                        | prTypeCd               | NB                          | purchase_requisition | CONS_LATAM   |
    And I wait "/plan/cns_plan_object_filter" Async Queue complete

    Given I import "/edm/plant_v1" by keyFields "localPlant,sourceSystem"
      | country | localCountry | localCurrency | localPlanningRelevant | localPlant | localPlantName                  | localPlantType | plant | plantType               | region                                    | site                 | sourceSystem |
      | MX      | MX           | MXN           | X                     | BR12       | Sales & Marketing               | DC             | MX32  | DC, Distribution Center | MX, MEXICO MEXICO                         | Importadora DC       | CONS_LATAM   |
      | VE      | VE           | VEF           | X                     | CO01       | J&J VE-Valencia-DistribuiciÃ³n  | DC             | VE07  | DC, Distribution Center | VE, VENEZUELA VENEZUELA                   | Valencia DC          | CONS_LATAM   |
      | GT      | GT           | GTQ           | X                     | CL01       | J&J Guatemala - Bond Fiscal     | DC             | GT02  | DC, Distribution Center | GT, GUATEMALA GUATEMALA                   | Guatemala BF DC      | CONS_LATAM   |
      | PA      | PA           | PAB           | X                     | MX02       | J&J Panama - S&M Consumer       | DC             | PA05  | DC, Distribution Center | PA, PANAMA PANAMA                         | Panama DC            | CONS_LATAM   |
      | GT      | GT           | GTQ           | X                     | MX01       | J&J Guatemala - S&M Consumer    | DC             | GT01  | DC, Distribution Center | GT, GUATEMALA GUATEMALA                   | Guatemala DC         | CONS_LATAM   |
      | UY      | UY           | UYU           | X                     | CR02       | S & M Consumer RecintoAduanero  | DC             | UY05  | DC, Distribution Center | UY, URUGUAY URUGUAY                       | Recinto Aduanero     | CONS_LATAM   |
      | CR      | CR           | CRC           | X                     | CO02       | J&J Costa Rica - S&M Consumer   | DC             | CR01  | DC, Distribution Center | CR, COSTA RICA COSTA RICA                 | Costa Rica DC        | CONS_LATAM   |
      | UY      | UY           | UYU           | X                     | UY01       | S & M Consumer                  | DC             | UY07  | DC, Distribution Center | UY, URUGUAY URUGUAY                       | Uruguay              | CONS_LATAM   |
      | MX      | MX           | MXN           | X                     | MX01       | PT Importado                    | DC             | MX31  | DC, Distribution Center | MX, MEXICO MEXICO                         | PT Importadora DC    | CONS_LATAM   |
      | CL      | CL           | CLP           | X                     | CL01       | J&J CL-Santiago-Chile Consumer  | DC             | CL03  | DC, Distribution Center | CL, CHILE CHILE                           | Chile DC             | CONS_LATAM   |
      | PY      | PY           | PYG           | X                     | PY01       | S & M Consumer                  | DC             | PY01  | DC, Distribution Center | PY, PARAGUAY PARAGUAY                     | Paraguay             | CONS_LATAM   |
      | PA      | PA           | PAB           | X                     | PA03       | J&J Panama - Zona Libre         | DC             | PA06  | DC, Distribution Center | PA, PANAMA PANAMA                         | Panama Zona Libre DC | CONS_LATAM   |
      | BR      | BR           | BRL           | X                     | BR07       | J&J BR-JoÃ£o Pessoa-COM&Distrib | DC             | BR54  | DC, Distribution Center | BR, BRAZIL BRAZIL                         | Pessoa DC            | CONS_LATAM   |
      | VE      | VE           | VEF           |                       | VE01       | J&J VE-Valencia-Planta          |                | VE06  | MP, Manufacturing Plant | VE, VENEZUELA VENEZUELA                   | Valencia             | CONS_LATAM   |
      | AR      | AR           | ARS           | X                     | AR01       | Pilar Plant                     |                | AR06  | MP, Manufacturing Plant | AR, ARGENTINA ARGENTINA                   | Pilar                | CONS_LATAM   |
      | DO      | DO           | DOP           | X                     | DO01       | J&J DO-S.Domingos 1-R.Dominica  | DC             | DO02  | DC, Distribution Center | DO, DOMINICAN REPUBLIC DOMINICAL REPUBLIC | Dominica             | CONS_LATAM   |
      |         |              |               | X                     | CO01       | J&J Colombia - LADS (CO01)      |                | CO07  | MP, Manufacturing Plant | CO, COLOMBIA COLOMBIA                     | Cali                 | CONS_LATAM   |
      | VE      | VE           | VEF           |                       | VE05       | J&J VE-Valencia-ETHNOR          |                | VE05  | DC, Distribution Center | VE, VENEZUELA VENEZUELA                   | Valencia Ethnor DC   | CONS_LATAM   |
      | AR      | AR           | ARS           | X                     | AR02       | S & M Pilar                     | DC             | AR07  | DC, Distribution Center | AR, ARGENTINA ARGENTINA                   | Pilar DC             | CONS_LATAM   |
      | BR      | BR           | BRL           | X                     | BR12       | J&J BR SÃ£o JosÃ© Campos - Indu |                | BR59  | MP, Manufacturing Plant | BR, BRAZIL BRAZIL                         | Sao Jose dos Campos  | CONS_LATAM   |
      | PE      | PE           | PEN           | X                     | PE01       | J&J PE-Lima-S&M                 | DC             | PE03  | DC, Distribution Center | PE, PERU PERU                             | Peru DC              | CONS_LATAM   |
      | BR      | BR           | BRL           | X                     | BR25       | J&J BR-GoiÃ¢nia - Com&Distrib   | DC             | BR69  | DC, Distribution Center | BR, BRAZIL BRAZIL                         | Goiania DC           | CONS_LATAM   |
      | BR      | BR           | BRL           | X                     | BR19       | J&J BR-Nova Odessa - Com&Distr  | DC             | BR63  | DC, Distribution Center | BR, BRAZIL BRAZIL                         | Nova Odessa DC       | CONS_LATAM   |
      | CR      | CR           | CRC           | X                     | CR02       | J&J Costa Rica - Bond Fiscal    | DC             | CR02  | DC, Distribution Center | CR, COSTA RICA COSTA RICA                 | Costa Rica BF DC     | CONS_LATAM   |
      |         |              |               | X                     | CO02       | J&J Colombia - S&M (CO02)       |                | CO08  | DC, Distribution Center | CO, COLOMBIA COLOMBIA                     | Cali DC              | CONS_LATAM   |
      | BR      | BR           | BRL           | X                     | BR16       | J&J BR-Extrema - Com&Distrib    | DC             | BR61  | DC, Distribution Center | BR, BRAZIL BRAZIL                         | Extrema  DC          | CONS_LATAM   |
      |         |              |               | X                     | EC01       | Johnson & Johnson del Ecuador   | DC             | EC03  | DC, Distribution Center | EC, ECUADOR ECUADOR                       | Ecuador DC           | CONS_LATAM   |
    And I wait "/edm/plant_v1" Async Queue complete

    Given I import "/plan/cns_spl_pln_loc" by keyFields "localName,localNumber,sourceSystem,vendorOrCustomer"
      | localCountry | localCurrency | localName                          | localNumber | localPlant | localRegion | planLocTypeDesc           | planLocTypeId | sourceSystem | specLocAtt1 | specLocAtt2 | specLocAtt3 | specLocAttDesc1 | specLocAttDesc3 | specLocAttdEsc2 | vendorCustomer | vendorOrCustomer | vendorOrCustomer |
      | GT           | GTQ           | DHL GLOBAL FORWARDING (GUATEMALA)  | 36191       | GT01       | 1           | Affiliate/Market Copacker | SUBCON        | CONS_LATAM   |             |             |             |                 |                 |                 |                |                  | V                |
      | US           |               | JOHNSON & JOHNSON CONSUMER INC.    | 50161       |            |             | Ext Manufacturer FG       | VENDOR        | CONS_LATAM   |             |             |             |                 |                 |                 |                |                  | V                |
      | CO           | COP           | SUPPLA SA                          | 13873       | CO01       | 11          | Mfg Plant Copacker        | SUBCON        | CONS_LATAM   |             |             |             |                 |                 |                 |                |                  | V                |
      | CO           | COP           | ASPRILLA ORTIZ FABIO               | 15574       | CO01       | 76          | Mfg Plant Copacker        | SUBCON        | CONS_LATAM   |             |             |             |                 |                 |                 |                |                  | V                |
      | BR           | USD           | YOBEL SUPPLY CHAIN MANAGEMENT S.A. | 9999        | BR12       | 15          | Affiliate/Market Copacker | SUBCON        | CONS_LATAM   |             |             |             |                 |                 |                 |                |                  | V                |
      | PA           | USD           | J CAIN & CO                        | 36124       | PA03       | 1           | Affiliate/Market Copacker | SUBCON        | CONS_LATAM   |             |             |             |                 |                 |                 |                |                  | V                |
      | PA           | USD           | J CAIN & CO                        | 36124       | BR12       | 1           | Affiliate/Market Copacker | SUBCON        | CONS_LATAM   |             |             |             |                 |                 |                 |                |                  | V                |
      | BR           | USD           | J CAIN & CO                        | 6109        | BR12       | 1           | Affiliate/Market Copacker | SUBCON        | CONS_LATAM   |             |             |             |                 |                 |                 |                |                  | V                |
      | BR           | USD           | J CAIN & CO                        | 2345        | BR12       | 1           | Affiliate/Market Copacker | SUBCON        | CONS_LATAM   |             |             |             |                 |                 |                 |                |                  | V                |
    And I wait "/plan/cns_spl_pln_loc" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmStockPurchaseRequisition.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMStock_PurchaseRequisition.tsv"

    And I check file data for filename "GDMStock.tsv" by keyFields "stockId"
      | stockId                              | active | activeOPRERP | activeSOPERP | batchId | blockedQuantity | consignment | certaintyId | erpOrderId | inventoryLinkGroupId                 | vendorId | locationId      | processId                               | processTypeId     | productId | qualityQuantity | quantity | receiptDate         | restrictedQuantity | returnsQuantity | startDate           | stockType | transferQuantity | transitDate         | unrestrictedQuantity |
      | 170196/CONS_LATAM_BR12/1173484245/20 | YES    | YES          | NO           |         | 0.0             | NO          | BA          | 1173484245 |                                      | 93808    | CONS_LATAM_BR12 | SU/170196/CONS_LATAM_BR12/93808/DEFAULT | Subcontracting    | 170196    | 0.0             | 28836    | 2019/06/04 00:00:00 | 0.0                | 0.0             | 2019/06/10 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
      | 174461/CONS_LATAM_CO01/1190921893/10 | YES    | YES          | NO           |         | 0.0             | NO          | BA          | 1190921893 |                                      | 13873    | CONS_LATAM_CO01 | TR/174461/CONS_LATAM_CO01/DEFAULT       | InternalTransport | 174461    | 0.0             | 4320     | 2019/09/04 00:00:00 | 0.0                | 0.0             | 2019/09/05 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
      | 189915/CONS_LATAM_BR12/1420964649/10 | YES    | YES          | NO           |         | 0.0             | NO          | BA          | 1420964649 | 189915/CONS_LATAM_BR12/1420964649/10 | 4672     | CONS_LATAM_BR12 | SU/189915/CONS_LATAM_BR12/4672/DEFAULT  | ExternalPurchase  | 189915    | 0.0             | 1104     | 2014/06/05 00:00:00 | 0.0                | 0.0             | 2014/06/05 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
      | 55755/CONS_LATAM_BR12/1420964650/10  | YES    | YES          | NO           |         | 0.0             | YES         | BA          | 1420964650 | 55755/CONS_LATAM_BR12/1420964650/10  | 2345     | CONS_LATAM_BR12 | SU/55755/CONS_LATAM_BR12/2345/DEFAULT   | VendorTransport   | 55755     | 0.0             | 1104     | 2014/06/05 00:00:00 | 0.0                | 0.0             | 2014/06/05 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
      | 88732/CONS_LATAM_BR12/1420964651/10  | YES    | YES          | NO           |         | 0.0             | YES         | BA          | 1420964651 | 88732/CONS_LATAM_BR12/1420964651/10  | 6109     | CONS_LATAM_BR12 | SU/88732/CONS_LATAM_BR12/6109/DEFAULT   | VendorTransport   | 88732     | 0.0             | 1104     | 2014/06/05 00:00:00 | 0.0                | 0.0             | 2014/06/05 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/omp/gdm_stock_purchase_requisition_v1"

    And I will remove the test file on sink application "GDMStock_PurchaseRequisition.tsv"


