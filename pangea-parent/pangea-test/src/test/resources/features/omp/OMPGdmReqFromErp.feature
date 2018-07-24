@pangea_test @AEAZ-8895
Feature: OMPGdmReqFromErp-Consumption

  Scenario: Full Load Consumption

    Given I import "/edm/purchase_requisition_v1" by keyFields "prNum,prLineNbr,sourceSystem"
    | prNum | matlNum | needByDt | plntCd | lineStsCd | localCurrency | localFixedVendor | localPDT | localPODate | localPOQuantity | localPrGRLeadTimeDays | prLineCatCd | prLineNbr | prLineQty | prLineUomCd | prMrpHrzn | prRqstDt | poTypeCd | prCatCd | prClseInd | prStsCd | prTypeCd | locaalMaterialGroup | intrnlRefNum | localProcuringPlant | localProdVersion | mfrPartNum | localQuotaArr | localQuotaArrItem | localReservation | localSCVendor | localSpIndStckTfr | localSubjToRelease | localSupNum | localXSysItemCat | localXSysPReqItem | localXSysPReqNo | localdelvAddrADRN2 | localdelvAddrADRNR | prchInfoDesc | prchsngGrpNum | prchsngOrgNum | recCrtInd | slocCd | sourceSystem | splStkInd | supNum | suplPlntCd | blokInd | bomNum | chngOnDt | crtByNm | localAgreement | localAgreementItem | localBatch | localBlockingText | localCustomer | localExternalManuf | localControlInd | localGRInd | localIncomplete | localInfoRecord | localIssStorLoc | localItem | localManufacturer | localOrderUnit | localOverallReqRel | delInd | fxInd | acctAsgnmtCatCd | apprByDt | asgnSuplSrcInd  |
    | 1773590231 | 61021 | 20180719 | MX01 | 2 | MXN | 0000008917 | 2 |  | 0 | 3 | 7 | 10 | 4476 | EA | 19R | 20180709 | K | B |  | N | NB | 1 |  |  |  |  |  | 0 | 0 | FALSE |  | FALSE |  |  | 0 |  |  |  | J'S BODY LOT DEO H ROMA UVA 12X400ML NAC |  | MX00 | A | MX02 | CONS_LATAM |  |  | MX02 |  |  | 20180709 | ALEREMOTE | 5100001777 | 38490 |  |  |  |  |  | TRUE | FALSE |  |  | 0 |  |  | FALSE |  | FALSE |  | 20180717 | TRUE |
    | 1773590132 | 55755 | 20191104 | CR02 | 2 | USS | 8917 | 2 |  | 0 | 0 | 7 | 10 | 3792 | EA | 19R | 20180709 | K | B |  | N | NB | 1 |  |  |  |  |  | 0 | 0 | FALSE |  | FALSE |  |  | 0 |  |  |  | J'S BODY LOT DEO H ROMA UVA 12X400ML NAC |  | CR00 | A | CR01 | CONS_LATAM |  |  | CR01 |  |  | 20180709 | ALEREMOTE | 5100004195 | 27540 |  |  |  |  |  | TRUE | FALSE |  |  | 0 |  |  | FALSE |  | FALSE |  | 20191101 | TRUE       |
    | 1505694358 | 60831 | 20151022 | GT02 | 2 | GTQ | 26593 | 1 |  | 0 | 1 | 0 | 10 | 72 | EA | CPV | 20151022 | K | B |  | N | NB | 1 |  |  |  |  |  | 0 | 0 | FALSE |  | FALSE |  |  | 0 |  |  |  | J'S B TOAL HUMEDAS DULCES SUENOS 24X48UN | P06 | GT00 | A | GT01 | CONS_LATAM |  |  | GT01 |  |  | 20151022 | JZAPATA5 | 5100004172 | 13180 |  |  |  |  |  | TRUE | FALSE |  |  | 0 |  |  | FALSE |  | FALSE |  | 20151022 | TRUE      |
    | 1505694359 | 96877 | 20191104 | MX01 | 2 | MXN | 0000001111 | 40 |  | 0 | 0 | 0 | 10 | 504 | EA | CPC | 20151022 | K | B |  | N | NB | 1 |  |  |  |  |  | 0 | 0 | FALSE |  | FALSE |  |  | 0 |  |  |  | LUBRIDERM UV15 36 X 200 ML | P06 | MX00 | A | MX02 | CONS_LATAM |  |  | MX02 |  |  | 20151022 | JZAPATA5 | 5100004172 | 9410 |  |  |  |  |  | TRUE | FALSE |  |  | 0 |  |  | FALSE |  | FALSE |  | 20151022 | TRUE              |
    | 1773765928 | 66845 | 20190101 | CO01 | 2 | GTQ | 4672 | 40 |  | 0 | 0 | 7 | 10 | 120 | EA | 08D | 20180709 | K | B |  | N | NB | 1 |  |  |  |  |  | 0 | 0 | FALSE |  | FALSE |  |  | 0 |  |  |  | JB BABY GEL CRE HID TOQUE FRESCO 12X200 | P06 | CO00 | A | CO01 | CONS_LATAM |  |  | CO01 |  |  | 20180709 | ALEREMOTE | 5100004012 | 8170 |  |  |  |  |  | TRUE | FALSE |  |  | 0 |  |  | FALSE |  | FALSE |  | 20181122 | TRUE      |
    | 1402042616 | 56424 | 20190101 | AR01 | 2 | USS | 8917 | 0 |  | 0 | 0 | 3 | 10 | 1992 | EA | M59 | 20121001 | K | B |  | N | NB | 3 |  |  |  |  |  | 0 | 47920410 | FALSE |  | FALSE |  |  | 0 |  |  |  | MOTRIN INFANTIL 120ML 2 PACK C/12 | M11 | AR00 | A | AR01 | CONS_LATAM |  |  | AR01 |  |  | 20130110 | Motrin PROD |  | 0 |  |  |  |  |  | TRUE | FALSE |  |  | 0 |  |  | FALSE |  | TRUE |  | 20121001 | FALSE                |
    | 1402042617 | 56425 | 20190101 | CR01 | 2 | USS | 8917 | 0 |  | 0 | 0 | 3 | 10 | 90 | EA | M59 | 20121001 | K | B |  | N | NB | 3 |  |  |  |  |  | 0 | 47920410 | FALSE |  | FALSE |  |  | 0 |  |  |  | MOTRIN INFANTIL 120ML 2 PACK C/12 | M11 | CR00 | A | CR01 | CONS_LATAM |  |  |  |  |  | 20130110 | Motrin PROD |  | 0 |  |  |  |  |  | TRUE | FALSE |  |  | 0 |  |  | FALSE |  | TRUE |  | 20121001 | FALSE                  |
    | 1402042901 | 98011 | 20180727 | PE01 | 2 | USS | 0000008898 | 10 |  | 0 | 1 | 3 | 20 | 110 | EA | M59 | 20180727 | K | B |  | N | NB | 3 |  |  |  |  |  | 0 | 47920410 | FALSE |  | FALSE |  |  | 0 |  |  |  | MOTRIN INFANTIL 120ML 2 PACK C/12 | PE01 | PE00 | A | PE01 | CONS_LATAM |  |  |  |  |  | 20130110 | Motrin PROD |  | 0 |  |  |  |  |  | TRUE | FALSE |  |  | 0 |  |  | FALSE |  | TRUE |  | 20121001 | FALSE |
    | 1402042902 | 98012 | 20180727 | MX02 | 2 | USS | 0000008898 | 10 |  | 0 | 1 | 3 | 20 | 120 | EA | M59 | 20180727 | K | B |  | N | NB | 3 |  |  |  |  |  | 0 | 47920410 | FALSE |  | FALSE |  |  | 0 |  |  |  | MOTRIN INFANTIL 120ML 2 PACK C/12 | PE01 | PE00 | A | PE01 | CONS_LATAM |  |  |  |  |  | 20130110 | Motrin PROD |  | 0 |  |  |  |  |  | TRUE | FALSE |  |  | 0 |  |  | FALSE |  | TRUE |  | 20180723 | FALSE |
    | 1402042903 | 98013 | 20180727 | PE01 | 2 | USS | 0000008898 | 10 |  | 0 | 1 | 3 | 20 | 110 | EA | M59 | 20180727 | K | B |  | N | NB | 3 |  |  |  |  |  | 0 | 47920410 | FALSE |  | FALSE |  |  | 0 |  |  |  | MOTRIN INFANTIL 120ML 2 PACK C/12 | PE01 | PE00 | A | PE01 | MDD_BtB |  |  |  |  |  | 20130110 | Motrin PROD |  | 0 |  |  |  |  |  | TRUE | FALSE |  |  | 0 |  |  | FALSE |  | TRUE |  | 20121001 | FALSE |
    | 1402042904 | 98014 | 20180727 | PE01 | 2 | USS | 0000008898 | 10 |  | 0 | 1 | 3 | 20 | 110 | EA | M59 | 20180727 | K | B | FALSE | N | NB | 3 |  |  |  |  |  | 0 | 47920410 | FALSE |  | FALSE |  |  | 0 |  |  |  | MOTRIN INFANTIL 120ML 2 PACK C/12 | PE01 | PE00 | A | PE01 | CONS_LATAM |  |  |  |  |  | 20130110 | Motrin PROD |  | 0 |  |  |  |  |  | TRUE | FALSE |  |  | 0 |  |  | FALSE |  | TRUE |  | 20121001 | FALSE |
    | 1402042905 | 98015 | 20180727 | UY09 | 2 | USS | 0000001234 | 10 |  | 0 | 1 | 3 | 20 | 110 | EA | M59 | 20180727 | K | B |  | N | NB | 3 |  |  |  |  |  | 0 | 47920410 | FALSE |  | FALSE |  |  | 0 |  |  |  | MOTRIN INFANTIL 120ML 2 PACK C/12 | UY09 | UY00 | A | UY09 | CONS_LATAM |  |  |  |  |  | 20130110 | Motrin PROD |  | 0 |  |  |  |  |  | TRUE | FALSE |  |  | 0 |  |  | FALSE |  | TRUE |  | 20121001 | FALSE |
    | 1402042906 | 98016 | 20180727 | PE01 | 2 | USS | 0000008898 | 10 |  | 0 | 1 | 3 | 20 | 110 | EA | M59 | 20180727 | K | B |  | N | NB | 3 |  |  |  |  |  | 0 | 47920410 | FALSE |  | FALSE |  |  | 0 |  |  |  | MOTRIN INFANTIL 120ML 2 PACK C/12 | PE01 | PE00 | A | PE01 | CONS_LATAM |  |  |  |  |  | 20130110 | Motrin PROD |  | 0 |  |  |  |  |  | TRUE | FALSE |  |  | 0 |  |  | FALSE |  | TRUE |  | 20121001 | FALSE |
    | 1402042907 | 98017 | 20180727 | VE01 | 2 | USS | 0000004321 | 10 |  | 0 | 1 | 3 | 20 | 110 | EA | M59 | 20180727 | K | B |  | N | NB | 3 |  |  |  |  |  | 0 | 47920410 | FALSE |  | FALSE |  |  | 0 |  |  |  | MOTRIN INFANTIL 120ML 2 PACK C/12 | VE01 | VE00 | A | VE01 | CONS_LATAM |  |  |  |  |  | 20130110 | Motrin PROD |  | 0 |  |  |  |  |  | TRUE | FALSE |  |  | 0 |  |  | FALSE |  | TRUE |  | 20121001 | FALSE |
    | 1402042908 | 98018 | 20180727 | PE01 | 2 | USS | 0000008898 | 10 |  | 0 | 1 | 3 | 20 | 110 | EA | M59 | 20180727 | K | B | FALSE | M | NB | 3 |  |  |  |  |  | 0 | 47920410 | FALSE |  | FALSE |  |  | 0 |  |  |  | MOTRIN INFANTIL 120ML 2 PACK C/12 | PE01 | PE00 | A | PE01 | CONS_LATAM |  |  |  |  |  | 20130110 | Motrin PROD |  | 0 |  |  |  |  |  | TRUE | FALSE |  |  | 0 |  |  | FALSE |  | TRUE |  | 20121001 | FALSE |
    | 1402042909 | 98019 | 20180727 | PE01 | 2 | USS | 0000008898 | 10 |  | 0 | 1 | 3 | 20 | 110 | EA | M59 | 20180727 | K | B | FALSE | M | NB | 3 |  |  |  |  |  | 0 | 47920410 | FALSE |  | FALSE |  |  | 0 |  |  |  | MOTRIN INFANTIL 120ML 2 PACK C/12 | PE01 | PE00 | A | PE01 | CONS_LATAM |  |  |  |  |  | 20130110 | Motrin PROD |  | 0 |  |  |  |  |  | TRUE | FALSE |  |  | 0 |  |  | FALSE |  | TRUE |  | 20121001 | FALSE |

    And I wait "/edm/purchase_requisition_v1" Async Queue complete

    Given I import "/plan/cns_tlane_control" by keyFields "sequenceNumber,tlaneName"
    | sequenceNumber | tlaneName | validFrom | validTo | originLocation | supSys | supSysVendor | supSysCustomer | destinationLocation | trigSysTransaction | trigSysPlant | recSys | recSysVendor | recSysCustomer | mode | leadTime | triangulationDetail | processTypeId | sourceSystemCriticalParameters | trigSysVendor | trigSysCustomer | trigSysShipTo | criticalParameter1Table | criticalParameter1Field | criticalParameter1Low | criticalParameter1High | criticalParameter1Operator | criticalParameter1IE | criticalParameter2Table | criticalParameter2Field | criticalParameter2Low | criticalParameter2High | criticalParameter2Operator | criticalParameter2IE | criticalParameter3Table | criticalParameter3Field | criticalParameter3Low | criticalParameter3High | criticalParameter3Operator | criticalParameter3IE | criticalParameter4Table | criticalParameter4Field | criticalParameter4Low | criticalParameter4High | criticalParameter4Operator | criticalParameter4IE | criticalParameter5Table | criticalParameter5Field | criticalParameter5Low | criticalParameter5High | criticalParameter5Operator | criticalParameter5IE | criticalParameter6Table | criticalParameter6Field | criticalParameter6Low | criticalParameter6High | criticalParameter6Operator | criticalParameter6IE | criticalParameter7Table | criticalParameter7Field | criticalParameter7Low | criticalParameter7High | criticalParameter7Operator | criticalParameter7IE | criticalParameter8Table | criticalParameter8Field | criticalParameter8Low | criticalParameter8High | criticalParameter8Operator | criticalParameter8IE | criticalParameter9Table | criticalParameter9Field | criticalParameter9Low | criticalParameter9High | criticalParameter9Operator | criticalParameter9IE | criticalParameter10Table | criticalParameter10Field | criticalParameter10Low | criticalParameter10High | criticalParameter10Operator | criticalParameter10IE |
    | 1 | CO01toMX02 | 1012018 | 31/12/2998 | CONS_LATAM_CO01 |  |  |  | CONS_LATAM_MX02 | purchase_order | MX01 |  |  |  | Ship | 35 | Yes | 1 | CONS_LATAM |  |  |  | cns_prod_loc_attrib | primarySupplierPlantCode | CO01 |  | = | I | material_plant | localPlant | MX02 |  | = | I | material_plant | localProcurementType | F |  | = | I | material_plant | localMrpType | X0 |  | = | I | material_plant | localMrpController | 999 |  | = | E | material_plant | localSpecialProcurementType | 44 |  | = | I | material_plant | localPlantStatus | 08,11,13 |  | = | I |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |
    | 10 | CO01toCR01 | 1012018 | 31/12/2998 | CONS_LATAM_CO01 |  |  |  | CONS_LATAM_CR01 | purchase_order | CR02 |  |  |  | Ship | 40 | Yes | 1 | CONS_LATAM |  |  |  | cns_prod_loc_attrib | primarySupplierPlantCode | CO01 |  | = | I | material_plant | localPlant | CR01 |  | = | I | material_plant | localProcurementType | F |  | = | I | material_plant | localMrpType | X0 |  | = | I | material_plant | localMrpController | 999 |  | = | E | material_plant | localSpecialProcurementType | 44 |  | = | I | material_plant | localPlantStatus | 08,11,13 |  | = | I |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |
    | 11 | CO01toGT01 | 1012018 | 31/12/2998 | CONS_LATAM_CO01 |  |  |  | CONS_LATAM_GT01 | purchase_order | GT02 |  |  |  | Ship | 40 | Yes | 1 | CONS_LATAM |  |  |  | cns_prod_loc_attrib | primarySupplierPlantCode | CO01 |  | = | I | material_plant | localPlant | GT01 |  | = | I | material_plant | localProcurementType | F |  | = | I | material_plant | localMrpType | X0 |  | = | I | material_plant | localMrpController | 999 |  | = | E | material_plant | localSpecialProcurementType | 44 |  | = | I | material_plant | localPlantStatus | 08,11,13 |  | = | I |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |
    | 20 | BR12toMX02 | 1012018 | 31/12/2998 | CONS_LATAM_BR12 |  |  |  | CONS_LATAM_MX02 | purchase_order | MX01 |  |  |  | Ship | 37 | Yes | 1 | CONS_LATAM |  |  |  | cns_prod_loc_attrib | primarySupplierPlantCode | BR12 |  | = | I | material_plant | localPlant | MX02 |  | = | I | material_plant | localProcurementType | F |  | = | I | material_plant | localMrpType | X0 |  | = | I | material_plant | localMrpController | 999 |  | = | E | material_plant | localSpecialProcurementType | 40 |  | = | I | material_plant | localPlantStatus | 08,11,13 |  | = | I |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |
    | 28 | PA03toGT01 | 1012018 | 31/12/2998 | CONS_LATAM_PA03 |  |  |  | CONS_LATAM_GT01 | purchase_order | GT02 |  |  |  | Ship | 27 | Yes | 1 | CONS_LATAM |  |  |  | cns_prod_loc_attrib | primarySupplierPlantCode | PA03 |  | = | I | material_plant | localPlant | GT01 |  | = | I | material_plant | localProcurementType | F |  | = | I | material_plant | localMrpType | X0 |  | = | I | material_plant | localMrpController | 999 |  | = | E | material_plant | localSpecialProcurementType | 39 |  | = | I | material_plant | localPlantStatus | 08,11,13 |  | = | I |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |
    | 29 | PA03toCR01 | 1012018 | 31/12/2998 | CONS_LATAM_PA03 |  |  |  | CONS_LATAM_CR01 | purchase_order | CR02 |  |  |  | Ship | 20 | Yes | 1 | CONS_LATAM |  |  |  | cns_prod_loc_attrib | primarySupplierPlantCode | PA03 |  | = | I | material_plant | localPlant | CR01 |  | = | I | material_plant | localProcurementType | F |  | = | I | material_plant | localMrpType | X0 |  | = | I | material_plant | localMrpController | 999 |  | = | E | material_plant | localSpecialProcurementType | 39 |  | = | I | material_plant | localPlantStatus | 08,11,13 |  | = | I |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |

    And I wait "/plan/cns_tlane_control" Async Queue complete

    Given I import "/plan/cns_tlane_control_triangulation" by keyFields "sequenceNumber,stepNumber,tlaneName"
    | sequenceNumber | stepNumber | tlaneName | originLocation | destinatonLocation  |
    | 1 | 1 | CO01toMX02 | CONS_LATAM_CO01 | CONS_LATAM_MX01   |
    | 1 | 2 | CO01toMX02 | CONS_LATAM_MX01 | CONS_LATAM_MX02   |
    | 10 | 1 | CO01toCR01 | CONS_LATAM_CO01 | CONS_LATAM_CR02  |
    | 10 | 2 | CO01toCR01 | CONS_LATAM_CR02 | CONS_LATAM_CR01  |
    | 11 | 1 | CO01toGT01 | CONS_LATAM_CO01 | CONS_LATAM_GT02  |
    | 11 | 2 | CO01toGT01 | CONS_LATAM_GT02 | CONS_LATAM_GT01  |
    | 20 | 1 | BR12toMX03 | CONS_LATAM_BR12 | CONS_LATAM_MX01  |
    | 20 | 2 | BR12toMX03 | CONS_LATAM_MX01 | CONS_LATAM_MX02  |
    | 28 | 1 | PA03toGT01 | CONS_LATAM_PA03 | CONS_LATAM_GT02  |
    | 28 | 2 | PA03toGT01 | CONS_LATAM_GT02 | CONS_LATAM_GT01  |
    | 29 | 1 | PA03toCR01 | CONS_LATAM_PA03 | CONS_LATAM_CR02  |
    | 29 | 2 | PA03toCR01 | CONS_LATAM_CR02 | CONS_LATAM_CR01  |

    And I wait "/plan/cns_tlane_control_triangulation" Async Queue complete

    Given I import "/plan/cns_plan_object_filter" by keyFields "sourceObjectAttribute1,sourceObjectAttribute1Value,sourceObjectAttribute2,sourceObjectAttribute2Value,sourceObjectTechName,sourceSystem"
    | comments | inclusionExclusion | sourceObjectAttribute1 | sourceObjectAttribute1Value | sourceObjectAttribute2 | sourceObjectAttribute2Value | sourceObjectTechName | sourceSystem |
    | | I | plntCd | MX02 | prTypeCd | NB | purchase_requisition | CONS_LATAM  |
    | | I | plntCd | CR01 | prTypeCd | NB | purchase_requisition | CONS_LATAM  |
    | | I | plntCd | GT01 | prTypeCd | NB | purchase_requisition | CONS_LATAM  |
    | | I | plntCd | CO01 | prTypeCd | NB | purchase_requisition | CONS_LATAM  |
    | | I | plntCd | AR01 | prTypeCd | NB | purchase_requisition | CONS_LATAM |
    | | I | plntCd | DO01 | prTypeCd | NB | purchase_requisition | CONS_LATAM |
    | | I | plntCd | CO02 | prTypeCd | EL | purchase_requisition | CONS_LATAM |
    | | I | plntCd | CR02 | prTypeCd | NB | purchase_requisition | CONS_LATAM |
    | | I | plntCd | MX02 | prTypeCd | NB | purchase_requisition | CONS_LATAM |
    | | I | plntCd | CO01 | prTypeCd | NB | purchase_requisition | CONS_LATAM |
    | | I | plntCd | CL01 | prTypeCd | NB | purchase_requisition | CONS_LATAM |
    | | I | plntCd | CR02 | prTypeCd | NB | purchase_requisition | CONS_LATAM |
    | | I | plntCd | PE01 | prTypeCd | NB | purchase_requisition | CONS_LATAM |
    | | I | plntCd | UY09 | prTypeCd | NB | purchase_requisition | CONS_LATAM |
    | | I | plntCd | VE02 | prTypeCd | NB | purchase_requisition | CONS_LATAM |

    And I wait "/plan/cns_plan_object_filter" Async Queue complete

    Given I import "/plan/cns_plan_unit" by keyFields "localUom,localUomName,planFlag,sourceSystem,unit"
    | localUom | localUomName | modifiedBy | planFlag | roundingDecimal | sourceSystem | status | timeStamp | unit |
    | EA | EA |  | SP | 3 | CONS_LATAM |  |  | EA |
    | TH | TH | mmarik | SP | 0 | CONS_LATAM | N | 10/07/2018 17:17 | TH |
    | BOT | EA | mmarik | SP | 0 | CONS_LATAM | N | 10/07/2018 17:17 | EA |
    | TO | Tonnes |  | SP | 3 | CONS_LATAM |  |  | TO |
    | BOT | Bottle |  | SP | 0 | CONS_LATAM |  |  | BO |
    | KI | KI, Crate | mmarik | DPSP | 0 | CONS_LATAM | N | 10/07/2018 17:17 | CA |
    | CD | CD, Cd | mmarik | SP | 0 | CONS_LATAM | N | 10/07/2018 17:17 | CD |
    | YD | Yard |  | SP | 3 | CONS_LATAM |  |  | YD |
    | TS | TS |  | SP | 3 | CONS_LATAM |  |  | LA_ZUM |

    And I wait "/plan/cns_plan_unit" Async Queue complete

    Given I import "/edm/plant_v1" by keyFields "localPlant,sourceSystem"
    | country | localCountry | localCurrency | localPlanningRelevant | localPlant | localPlantName | localPlantType | plant | region | site | sourceSystem  |
    | CL | CL | CLP | X | MX02 | J&J CL-Santiago-Chile Consumer | DC | MX02 | CL, CHILE CHILE | Chile DC | CONS_LATAM |
    | CR | AR | ARS | X | CR01 | Pilar Plant |  | CR01 | AR, ARGENTINA ARGENTINA | Pilar | CONS_LATAM |
    | DO | DO | DOP | X | GT01 | J&J DO-S.Domingos 1-R.Dominica | DC | GT01 | DO, DOMINICAN REPUBLIC DOMINICAL REPUBLIC | Dominica | CONS_LATAM |
    | |   |  | X | CO01 | J&J Colombia - LADS (CO01) |   | CO07 | CO, COLOMBIA COLOMBIA | Cali | CONS_LATAM |
    | | VE | VEF | X | AR01 | J&J VE-Valencia-ETHNOR |  | VE05 | VE, VENEZUELA VENEZUELA | Valencia Ethnor DC | CONS_LATAM |
    | BR | BR | BRL | X | BR19 | J&J BR-Nova Odessa - Com&Distr | DC | BR63 | BR, BRAZIL BRAZIL | Nova Odessa DC | CONS_LATAM |
    | AR | AR | ARS | X | AR02 | S & M Pilar | DC | AR07 | AR, ARGENTINA ARGENTINA | Pilar DC | CONS_LATAM |
    | PA | PA | PAB | X | PA01 | J&J Panama - S&M Consumer | DC | PA05 | PA, PANAMA PANAMA | Panama DC | CONS_LATAM |
    | | GT | GTQ | X | GT01 | J&J Guatemala - S&M Consumer | DC | GT01 | GT, GUATEMALA GUATEMALA | Guatemala DC | CONS_LATAM |
    |  | GT | USD | X | PE01 | J&J Guatemala - S&M Consumer | DC | PE01 | GT, GUATEMALA GUATEMALA | Guatemala DC | CONS_LATAM |
    | UY | UY | UYL |  | UY09 | J&J Guatemala - S&M Consumer | DC | UY09 | GT, GUATEMALA GUATEMALA | Guatemala DC | CONS_LATAM |
    | VE | VT | USD | X | VE01 | J&J Guatemala - S&M Consumer | DC | VE01 | GT, GUATEMALA GUATEMALA | Guatemala DC | CONS_LATAM |


    And I wait "/edm/plant_v1" Async Queue complete

    Given I import "/plan/cns_material_plan_status" by keyFields "localMaterialNumber,localPlant,sourceSystem"
    | active | dpRelevant | localMaterialNumber | localParentCode | localPlant | materialNumber | noPlanRelevant | parentActive | ppc | sourceSystem | spRelevant  |
    | X |  | 61021 |  | MX02 | 61021 |  |  | 91808 | CONS_LATAM | X |
    | X |  | 55755 | 7.89101E+16 | CR01 | 55755 |  | X | 55755 | CONS_LATAM | X |
    | X |  | 60831 |  | GT01 | 60831 |  |  | 60831 | CONS_LATAM | X |
    | X |  | 96877 |  | MX02 | 96877 |  |  | 96877 | CONS_LATAM | X |
    | X |  | 66845 | 7.50101E+16 | CO01 | 66845 |  | X | 66845 | CONS_LATAM | X |
    | X |  | 56424 |  | AR01 | 56424 |  |  | 56424 | CONS_LATAM | X |
    | X |  | 56425 |  | CR01 | 56425 |  |  | 56425 | CONS_LATAM | X |
    | X |  | 98011 |  | PE01 | 98011 |  |  | 98011 | CONS_LATAM | X |
    | X |  | 98012 |  | MX02 | 98012 |  |  | 98012 | CONS_LATAM | X |
    | X |  | 98013 |  | PE01 | 98013 |  |  | 98013 | CONS_LATAM | X |
    | X |  | 98014S|  | PE01 | 98014 |  |  | 98014 | CONS_LATAM | X |
    | X |  | 98015 |  | UY09 | 98015 |  |  | 98015 | CONS_LATAM | X |
    | X |  | 98016 |  | PE01 | 98016 |  |  | 98016 | CONS_LATAM | X |
    | X |  | 98017 |  | VE01 | 98017 |  |  | 98017 | CONS_LATAM | S |
    | X |  | 98018 |  | PE01 | 98018 |  |  | 98018 | CONS_LATAM | X |

    And I wait "/plan/cns_material_plan_status" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber,sourceSystem"
    | baseUom | batchManageIndicator | localBaseUom | localMaterialGroup | localMaterialNumber | materialNumber | materialStatus | materialType | minRemShelfLife | parentCode | primaryPlanningCode | productFamily | refDescription | sourceSystem | subBrand | totalShelfLife | brand | category | division | flagForDeletion | form | franchise | globalBusinessUnit | globalDpParentCode |  | localDpParentCode | localManufacturingTechnology |  |  | localMaterialType | localRefDescription |
    | |   | EA | 80141605 | 61021 |  | 8 |  | 0 |  | 61021 |  |  | CONS_LATAM |  | 0 |  |  | 10 |   |  |  |  |  |  |  |  |  |  | PROM | TM-DVD LG TAT |
    | | X | TH | PKG009 | 55755 | 55755 | 8 |  | 1 |  |  |  |  | CONS_LATAM |  | 550 |  |  | 10 |   |  |  |  |  |  |  |  |  |  | PACK | C-ROT PARA FR 200ML JS OLEO AMENDOAS |
    | |   | EA | 1 | 60831 | 60831 | 8 |  | 0 |  | 60831 |  |  | CONS_LATAM |  | 0 |  |  | 10 |   |  |  |  |  |  |  |  |  |  | NLAG | SUNDOWN GEL APOS-SOL 12X130 GR(55698)  |
    | |   | EA | 0 | 96877 | 96877 | 0 |  | 1 |  | 96877 |  |  | CONS_LATAM |  | 730 |  |  | 10 |   |  |  |  |  |  |  |  |  |  | PACK | CX 6 KIT PROM SDW FPS50 200 ML GR SOFT |
    | EA | X | EA | 1 | 66845 | 66845 | 8 | FERT | 180 |  | 66845 |  | KIT RUTINA DL BAÃ‘O:HTT SHAMP CREMA 200ML | CONS_LATAM |  | 730 |  |  | 10 |   |  |  |  |  |  |  |  |  |  | FERT | Kit Rutina dl baÃ±o:HTT Shamp Crema 200ml |
    | | X | EA | FNW011 | 56424 | 56424 | 5 |  | 180 |  | 56424 |  |  | CONS_LATAM |  | 730 |  |  | 10 |   |  |  |  |  |  |  |  |  |  | ROH | PAPEL SILICONADO TEKKOTE 35 LB - 96MM |
    | |   | EA |   | 56425 | 56425 | 1 |  | 0 |  | 56425 |  |  | CONS_LATAM |  | 0 |  |  |   |   |  |  |  |  |  |  |  |  |  | CBAU | EMS - Indisponivel para LATAM |
    | TS | X | TS |   | 98011 | 98011 | 1 |  | 0 |  | 98011 |  |  | CONS_LATAM |  | 0 |  |  |   |   |  |  |  |  |  |  |  |  |  | CBAU | EMS - Indisponivel para LATAM |
    | TS | X | TS |   | 98012 | 98012 | 1 |  | 0 |  | 98012 |  |  | CONS_LATAM |  | 0 |  |  |   |   |  |  |  |  |  |  |  |  |  | CBAU | EMS - Indisponivel para LATAM |
    | TS | X | TS |   | 98013 | 98013 | 1 |  | 0 |  | 98013 |  |  | CONS_LATAM |  | 0 |  |  |   |   |  |  |  |  |  |  |  |  |  | CBAU | EMS - Indisponivel para LATAM |
    | TS | X | TS |   | 98014 | 98014 | 1 |  | 0 |  | 98014 |  |  | CONS_LATAM |  | 0 |  |  |   |   |  |  |  |  |  |  |  |  |  | CBAU | EMS - Indisponivel para LATAM |
    | TS | X | TS |   | 98015 | 98015 | 1 |  | 0 |  | 98015 |  |  | CONS_LATAM |  | 0 |  |  |   |   |  |  |  |  |  |  |  |  |  | CBAU | EMS - Indisponivel para LATAM |
    | TS | X | TX |   | 98016 | 98016 | 1 |  | 0 |  | 98016 |  |  | CONS_LATAM |  | 0 |  |  |   |   |  |  |  |  |  |  |  |  |  | CBAU | EMS - Indisponivel para LATAM |
    | TS | X | TS |   | 98017 | 98017 | 1 |  | 0 |  | 98017 |  |  | CONS_LATAM |  | 0 |  |  |   |   |  |  |  |  |  |  |  |  |  | CBAU | EMS - Indisponivel para LATAM |
    | TS | X | TS |   | 98018 | 98018 | 1 |  | 0 |  | 98018 |  |  | CONS_LATAM |  | 0 |  |  |   |   |  |  |  |  |  |  |  |  |  | CBAU | EMS - Indisponivel para LATAM |


    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
    | localSourceSystem | localSourceSystemName | modifiedBy | sourceSystem | sourceSystemName | sourceSystemType | status | timeStamp | valid | LOCAL_SOURCE_SYSTEM | LOCAL_SOURCE_SYSTEM_NAME | SOURCE_SYSTEM | SOURCE_SYSTEM_NAME | comments | flatFile |
    | [MDD FASE] | JDE EDO - FASE - JDE ENTERPRISEONE -PROD (JDE 8.12) |  | MDD_FASE |  |  |  |  |  |  |  |  |  |  |  |
    | hcs | USROTC |  | MDD_USROTC |  |  |  |  |  |  |  |  |  |  |  |
    | btbjapan | BTB JAPAN |  | MDD_BTBJAPAN |  |  |  |  |  |  |  |  |  |  |  |
    | [MDD USROTC] | HCS SAP-ECC-PROD-MP2 (USROTC) |  | MDD_USROTC |  |  |  |  |  |  |  |  |  |  |  |
    | fase | JDE GMED 8.12 |  | MDD_FASE |  |  |  |  |  |  |  |  |  |  |  |
    | [EMS] | EMS |  | EMS | Enterprise Master Data Management System | NGEMS |  |  |  |  |  |  |  |  |  |
    | [MDD ENDO] | JDE ETHICON ENDO - JDE ENTERPRISEONE-PROD |  | MDD_ENDO |  |  |  |  |  |  |  |  |  |  |  |
    | [MD DePuy Spine JDE XE] | Spine |  | MDDePuy | MD DePuy Ent | JDE |  |  |  |  |  |  |  |  |  |
    | btb | BTB NA |  | MDD_BtB |  |  |  |  |  |  |  |  |  |  |  |
    | ems | EMS |  | EMS | Enterprise Master Data Management System | EMS |  |  |  |  |  |  |  |  |  |
    | mars | MARS |  | MDD_MARS |  |  |  |  |  |  |  |  |  |  |  |
    | Project_One | Project One |  | CONS_LATAM | Consumer Latam Ent | SAP |  |  |  |  |  |  |  |  |  |
    | endo | JDE ENDO E1 |  | MDD_ENDO |  |  |  |  |  |  |  |  |  |  |  |
    | [Consumer LATAM] | Consumer Latam |  | CONS_LATAM | Consumer Latam Ent | NGEMS |  |  |  |  |  |  |  |  |  |
    | [BtB] | Back to Basics - MDG |  | MDD_BtB |  |  |  |  |  |  |  |  |  |  |  |
    | [MDD MARS] | MARS-SAP |  | MDD_MARS |  |  |  |  |  |  |  |  |  |  |  |

    And I wait "/edm/source_system_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmReqFromErp.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "REQFROMERP.tsv"

    And I check file data for filename "REQFROMERP.tsv" by keyFields "REQFromERPId"
    | REQFromERPId | BLCKD | BLCKT | DELETED | DeliveryDate | DELKZ | DELNR | DELPS | ERPId | FLIEF | LocationId | ManualOffset | PLIFZ | PRIO_URG | ProductId | REQType | TotalQuantity | UnitId | VERID | WRK02 |
    | CONS_LATAM/1773590132/10 |  |  | NO | 2019/11/04 00:00:00 | BA | CONS_LATAM/1773590132 | 10 | CONS_LATAM/1773590132/10 | 8917 | CONS_LATAM_CR01 | 0 | 2 | 0 | 55755 | NB | 3792 | TH |  | CONS_LATAM_CR01 |
    | CONS_LATAM/1402042616/10 |  |  | NO | 2019/01/01 00:00:00 | BA | CONS_LATAM/1402042616 | 10 | CONS_LATAM/1402042616/10 | 8917 | CONS_LATAM_AR01 | 0 | 0 | 0 | 56424 | NB | 1992 | EA |  | CONS_LATAM_AR01 |
    | CONS_LATAM/1505694358/10 |  |  | NO | 2015/10/23 00:00:00 | BA | CONS_LATAM/1505694358 | 10 | CONS_LATAM/1505694358/10 | 26593| CONS_LATAM_GT01 | 0 | 1 | 0 | 60831 | NB | 72   | EA |  | CONS_LATAM_GT01 |
    | CONS_LATAM/1773765928/10 |  |  | NO | 2019/01/01 00:00:00 | BA | CONS_LATAM/1773765928 | 10 | CONS_LATAM/1773765928/10 | 4672 | CONS_LATAM_CO01 | 0 | 40| 0 | 66845 | NB | 120  | EA |  | CONS_LATAM_CO01 |
    | CONS_LATAM/1402042617/10 |  |  | NO | 2019/01/01 00:00:00 | BA | CONS_LATAM/1402042617 | 10 | CONS_LATAM/1402042617/10 | 8917 | CONS_LATAM_CR01 | 0 | 0 | 0 | 56425 | NB | 90   | EA |  |  |
    | CONS_LATAM/1773590231/10 |  |  | NO | 2018/07/22 00:00:00 | BA | CONS_LATAM/1773590231 | 10 | CONS_LATAM/1773590231/10 | 8917 | CONS_LATAM_MX02 | 0 | 2 | 0 | 61021 | NB | 4476 | EA |  | CONS_LATAM_MX02 |
    | CONS_LATAM/1505694359/10 |  |  | NO | 2019/11/04 00:00:00 | BA | CONS_LATAM/1505694359 | 10 | CONS_LATAM/1505694359/10 | 1111 | CONS_LATAM_MX02 | 0 | 40| 0 | 96877 | NB | 504  | EA |  | CONS_LATAM_MX02 |
    | CONS_LATAM/1402042901/20 |  |  | NO | 2018/07/28 00:00:00 | BA | CONS_LATAM/1402042901 | 20 | CONS_LATAM/1402042901/20 | 8898 | CONS_LATAM_PE01 | 0 | 10 | 0 | 98011 | NB | 110 | LA_ZUM |  |             |
    | CONS_LATAM/1402042902/20 |  |  | NO | 2018/07/28 00:00:00 | BA | CONS_LATAM/1402042902 | 20 | CONS_LATAM/1402042902/20 | 8898 | CONS_LATAM_MX02 | 0 | 10 | 0 | 98012 | NB | 120 | LA_ZUM |  |             |


    Then I check region data "/plan/edm_failed_data" by keyFields "errorCode,functionalArea,interfaceID,key1,key2,key3,key4,key5,sourceSystem"
      | errorCode |              errorValue                                                                    | functionalArea |  interfaceID     | key1       | key2  | key3 | key4 | key5 | sourceSystem |
      |    N6     |  Critical error - material_global_v1 null or localMaterialNumber not found                 |      SP        | OMPGdmReqFromErp | CONS_LATAM | 98019 |      |      |      | CONS_LATAM   |
      |    N7     |  Critical error - Cns Plan Unit - unit not found                                           |      SP        | OMPGdmReqFromErp | CONS_LATAM | 98016 |      |      |      | CONS_LATAM   |


    And I will remove all data with region "/edm/purchase_requisition_v1"

    And I will remove all data with region "/edm/material_global_v1"

    And I will remove all data with region "/plan/cns_material_plan_status"

    And I will remove all data with region "/plan/cns_plan_unit"

    And I will remove all data with region "/plan/cns_plan_object_filter"

    And I will remove all data with region "/edm/source_system_v1"

    And I will remove all data with region "/edm/plant_v1"

    And I will remove all data with region "/plan/cns_tlane_control"

    And I will remove all data with region "/plan/cns_tlane_control_triangulation"

    And I will remove all data with region "/omp/gdm_req_from_erp"

    And I will remove all data with region "/plan/edm_failed_data"

    And I will remove the test file on sink application "REQFROMERP.tsv"

