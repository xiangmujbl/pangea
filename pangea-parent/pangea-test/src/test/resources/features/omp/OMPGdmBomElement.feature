@pangea_test @AEAZ-4250
Feature: OMPGdmBomElement AEAZ-4250

  Scenario: Full Load curation

    Given I import "/edm/matl_bom" by keyFields "srcSysCd,matlNum,plntCd,bomUsgCd,bomNum,altBomNum"
      | srcSysCd | matlNum            | plntCd | altBomNum | sourceSystem  | bomNum | bomUsgCd | attribute |
      | 0001     | 000000000000203700 | M001   | A001      | sourceSystem1 | B001   | 0001     | att01     |
      | 0002     | 000000000000203701 | M002   | A002      | sourceSystem2 | B002   | 0002     | att02     |
      | 0003     | 000000000000203702 | M003   | A003      | sourceSystem3 | B003   | 0003     | att03     |
    And I wait "/edm/matl_bom" Async Queue complete

    Given I import "/edm/material_plant_v1" by keyFields "localMaterialNumber,localPlant"
      | localMaterialNumber | localPlant | sourceSystem  | localGoodsReceiptProcessingTimeInDays |
      | 000000000000203700  | M001       | sourceSystem1 | 1                                     |
      | 000000000000203701  | M002       | sourceSystem2 | 2                                     |
      | 000000000000203702  | M003       | sourceSystem3 | 3                                     |
    And I wait "/edm/material_plant_v1" Async Queue complete

    Given I import "/edm/bom_item" by keyFields "srcSysCd,bomCatCd,bomNum"
      | bomNum | srcSysCd | bomCatCd | cmpntNum | bomItmNum | bomItmVldFromDt     | dstrbtnKeyCd | fxQtyInd | bomItmVldToDt       | leadTimeOffst | cmpntQty | cmpntScrap_Pct | cmpntUomCd |
      | B001   | 0001     | bom001   | 001      | 001       | 2018/05/15 00:00:00 | 001          | 001      | 2018/05/16 00:00:00 | 001           | 001      | 001            | 001        |
      | B002   | 0002     | bom002   | 002      | 002       | 2018/05/16 00:00:00 | 002          | 002      | 2018/05/17 00:00:00 | 002           | 002      | 002            | 002        |
      | B003   | 0003     | bom003   | 003      | 003       | 2018/05/17 00:00:00 | 003          | 003      | 2018/05/18 00:00:00 | 003           | 003      | 003            | 003        |

    And I wait "/edm/bom_item" Async Queue complete

    Given I import "/edm/bom_hdr" by keyFields "srcSysCd,bomCatCd,bomNum,altBomNum"
      | srcSysCd | altBomNum | bomNum | bomCatCd | bomVldFromDt        | bomBaseQty | bomUomCd |
      | 0001     | A001      | B001   | bom001   | 2018/05/15 00:00:00 | 0001       | 0001     |
      | 0002     | A002      | B002   | bom002   | 2018/05/16 00:00:00 | 0002       | 0002     |
      | 0003     | A003      | B003   | bom003   | 2018/05/17 00:00:00 | 0003       | 0003     |
    And I wait "/edm/bom_hdr" Async Queue complete

    Given I import "/plan/cns_plan_parameter" by keyFields "sourceSystem,dataObject,attribute"
      | sourceSystem  | attribute | dataObject | parameterValue |
      | sourceSystem1 | att01     | Obj01      | para01         |
      | sourceSystem2 | att02     | Obj02      | para02         |
      | sourceSystem3 | att03     | Obj03      | para03         |
    And I wait "/plan/cns_plan_parameter" Async Queue complete

    Given I import "/edm/matl_prod_versn" by keyFields "srcSysCd,matlNum,plntCd,prdntVrsnNum"
      | srcSysCd | plntCd | altBomNum | matlNum            | rtngGrpCntrNum | rtngGrpCd | prdntVrsnNum | dstrbtnKeyCd | valFromDt           | valToDt             |
      | 0001     | M001   | A001      | 000000000000203700 | cntr001        | cd001     | vrsn001      | key001       | 2018/05/15 00:00:00 | 2018/05/16 00:00:00 |
      | 0002     | M002   | A002      | 000000000000203701 | cntr002        | cd002     | vrsn002      | key002       | 2018/05/16 00:00:00 | 2018/05/17 00:00:00 |
      | 0003     | M003   | A003      | 000000000000203702 | cntr003        | cd003     | vrsn003      | key003       | 2018/05/17 00:00:00 | 2018/05/18 00:00:00 |
    And I wait "/edm/matl_prod_versn" Async Queue complete

    Given I import "/edm/matl_mfg_rtng" by keyFields "srcSysCd,matlNum,plntCd,rtngTypCd,rntgGrpCd,rntgGrpCntrNbr,rntgAddtnlCntrNbr,matlRtngVrsnCntrNbr"
      | srcSysCd | rtngGrpCd | rtngGrpcntrNum | plntCd | matlNum            | rntgGrpCntrNbr | rtngTypCd |
      | 0001     | cd001     | cntr001        | M001   | 000000000000203700 | nbr001         | typ001    |
      | 0002     | cd002     | cntr002        | M002   | 000000000000203701 | nbr002         | typ002    |
      | 0003     | cd003     | cntr003        | M003   | 000000000000203702 | nbr003         | typ003    |
    And I wait "/edm/matl_mfg_rtng" Async Queue complete

    Given I import "/edm/mfg_rtng_itm_nde" by keyFields "srcSysCd,rtngTypCd,rtngGrpCd,rtngGrpCntrNbr,rtngSqncNum,rtngNdeNum,rtngNdeVrsnCntrNbr"
      | srcSysCd | rtngTypCd | rtngGrpCntrNbr | rtngGrpCd | rtngNdeNum |
      | 0001     | typ001    | cntr001        | cd001     | nde001     |
      | 0002     | typ002    | cntr002        | cd002     | nde002     |
      | 0003     | typ003    | cntr003        | cd003     | nde003     |

    And I wait "/edm/mfg_rtng_itm_nde" Async Queue complete

    Given I import "/edm/mfg_rtng_itm" by keyFields "srcSysCd,rtngTypCd,rtngGrpCd,rtngItmNum,rtngItmVersnCntrNbr"
      | srcSysCd | rtngTypCd | rtngItmNum | rtngGrpCd | operNum |
      | 0001     | typ001    | nde001     | cd001     | oper001 |
      | 0002     | typ002    | nde002     | cd002     | oper002 |
      | 0003     | typ003    | nde003     | cd003     | oper003 |
    And I wait "/edm/mfg_rtng_itm" Async Queue complete


    When I submit task with xml file "xml/omp/OMPGdmBomElement.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMBOMElement.tsv"

    Then I check  file data for filename "/omp/gdm_bom_element" by keyFields "bomElementId"
      | bomElementId | active | activeFCTERP | activeOPRERP | activeSOPERP | batchId | bomId | bomType | bomUsage | comment | endEff | erpFeedbackQuantity | locationId | offset | offsetCalendarId | offsetPercentage |offsetPercType | planLevelId | productId | quantity | startEff |

    And I delete the test data

    And I will remove all data with region "/omp/gdm_bom_element"

    And I will remove all data with region "/plan/edm_failed_data"

