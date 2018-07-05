@pangea_test @AEAZ-7229
Feature: OMPGDMProductLocation AEAZ-7229

  Scenario: Full Load curation
  1. get atrributes from material_global_v1|source_system_v1|cns_material_plan_status (rule J1)
  2. get atrributes from material_plant_fin_v1 (rule J2)
  3. get atrributes from cns_material_plan_status If cns_material_plan_status (rule D1|T1|T2|)
  4. get atrributes from cns_proc_type-procurementType If cns_proc_type-procurementType (rule E1)
  5. get atrributes from cns_prod_loc_attrib If cns_prod_loc_attrib (rule E5)
  	
  And I will remove the test file on sink application "GDMProductLocation.tsv"

    Given I import "/edm/material_plant_v1" by keyFields "localPlant,localMaterialNumber,sourceSystem"
|_test_reservation_|localAbcIndicator|localBaseQuantity|localBatchManagementRequirementIndicator|localCheckingGroupForAvailabilityCheck|localComponentScrap|localComponentScrapInPercent|localConsumptionMode|localConsumptionPeriodBackward|localConsumptionPeriodForward|localCriticalPart|localDeletionFlagPlant|localDependentRequirements|localFixedLotSize|localGoodsReceiptProcessingTimeInDays|localInHouseProcessingTime|localInstalledReplenishmentLotSize|localLotSize|localMaterialNumber|localMaximumLotSize|localMaximumStockLevel|localMinimumLotSize|localMinimumSafetyStock|localMrpController|localMrpType|localPlannedDeliveryTimeInDays|localPlanningStrategyGroup|localPlanningTimeFence|localPlant|localPlantStatus|localPostToInspStk|localPostToInspectionStock|localProcurementType|localProductionSupervisor|localProductionUnit|localRoundingValueForPoq|localSafetyStock|localSafetyTime|localSafetyTimeIndicator|localSpecialProcurementType|materialNumber|plant|plantStatus|purchsngGrpCd|sourceSystem|
||A|0|X|2|0|0|2|45|7| |MX02|1|0|2|0||28|89390|0|0|5616|0|999|X0|1|40|10|MX02|8| | |F| | |5616|0|49|2|90|89390|MX32||M39|CONS_LATAM|
||A|0|X|2|0|0|2|99|45| |PE01|2|0|1|0||28|89390|0|0|5616|0|14R|X0|48|40|10|PE01|8| | |F| | |5616|0|42|2| |89390|PE03||R10|CONS_LATAM|
||A|0|X|2|0|0|2|99|7| |CR01|1|0|0|0||28|529523|0|0|6|0|CPS|X0|14|40|0|CR01|8| | |F| | |6|0|28|2|30|529523|CR01||P06|CONS_LATAM|
|| |0|X|2|0|0| |0|0| |SV01| |0|0|0|| |93489|0|0|0|0| | |0| |0|SV01|8| | | | | |0|0|0| | |93489|||P06|CONS_LATAM|
|| |0|X|2|0|0|2|99|7| |GT01|1|0|1|0||WB|93489|0|0|6|0|18I|X0|0|40|0|GT01|8| | |F| | |6|0|35|2|90|93489|GT01||P06|CONS_LATAM|
||A|0|X|2|0|0|2|45|7| |BR12|2|0|0|0||28|530217|0|0|6|0|13R|X0|0|40|0|BR12|8| | |F| |KI|6|0|14|2|30|530217|BR59||B02|CONS_LATAM|
||C|0|X|2|0|0|2|45|7| |BR12|2|0|4|0||28|93489|0|0|29400|0|18I|X0|3|40|0|BR12|8| | |F| |KI|29400|0|28|2|30|93489|BR59||B23|CONS_LATAM|
||A|0|X|2|0|0|2|45|60| |CO01|1|0|2|0||WB|70538|0|0|17544|0|C81|Z2|0|40|10|CO01|8| | |E|C11|KI|12|0|7|2| |70538|CO07|| |CONS_LATAM|
||C|0|X|2|0|0|2|99|7| |CO02| |0|3|0||WB|93489|0|0|0|0|18I|ND|0|40|0|CO02|8| | |F| | |1|0|0| |90|93489|CO08|| |CONS_LATAM|
|| |0|X|2|0|0|2|45|7| |PY04|1|0|0|0||WB|93489|0|0|6|0|Y10|M0|0|11|0|PY04|8| | |F|J99| |6|0|0| | |93489|||Y10|CONS_LATAM|
||A|0|X|2|0|0|2|99|7| |GT01|1|0|0|0||28|529523|0|0|6|0|CPS|X0|0|40|0|GT01|8| | |F| | |6|0|28|2|30|529523|GT01||P06|CONS_LATAM|
||C|0|X|2|0|0|2|45|7| |BR14|2|0|0|0||WB|93489|0|0|120|0|18D|X0|2|40|0|BR14|8| | |F| | |120|0|17|2|40|93489|||B86|CONS_LATAM|
||A|0|X|2|0|0|2|99|7| |PA04|1|0|0|0||WB|529523|0|0|6|0|CPS|X0|14|40|0|PA04|4| | |F| | |6|0|28|2|30|529523|||P06|CONS_LATAM|
|| |0|X|2|0|0|2|99|45| |CR02|1|0|1|0||WB|441441|0|0|12|0|14R|X0|20|40|0|CR02|8| | |F| | |12|0|0| |39|441441|CR02||P06|CONS_LATAM|
||A|0|X|2|0|0|2|45|7| |CO02| |0|0|0||WB|70538|0|0|12|0|07R|X0|0|40|10|CO02|8| | |F| | |12|0|28|2|90|70538|CO08|| |CONS_LATAM|
|| |0|X|2|0|0|2|45|7| |AR01|1|0|3|0||14|93489|0|0|504|0|18D|X0|14|40|0|AR01|8| | |F|J99| |6|0|0| |40|93489|AR06||A14|CONS_LATAM|
|| |0|X|2|0|0|2|99|7| |GT01|1|0|0|0||WB|441441|0|0|12|0|14R|X0|0|40|0|GT01|8| | |F| | |12|0|0| |90|441441|GT01||P06|CONS_LATAM|
|| |0|X|2|0|0|2|99|45| |CL01|1|0|1|0||28|93489|0|0|1764|0|18D|X0|12|40|0|CL01|8| | |F| | |6|0|42|2|40|93489|CL03||I06|CONS_LATAM|
||A|0|X|2|0|0|2|99|7| |PA03|1|0|0|0||WB|529523|0|0|6|0|CPS|X0|14|40|0|PA03|8| | |F| | |6|0|28|2|30|529523|PA06||P06|CONS_LATAM|
|| |0|X|2|0|0|2|99|7| |CR01|1|0|0|0||WB|441441|0|0|12|0|14R|X0|0|40|0|CR01|8| | |F| | |12|0|0| |90|441441|CR01||P06|CONS_LATAM|
||A|0|X|2|0|0|2|45|7| |MX01|1|0|0|0||28|89390|0|0|5616|0|999|X0|19|40|10|MX01|8| | |F| | |5616|0|0| | |89390|MX31||M16|CONS_LATAM|
||C|0|X|2|0|0|2|7|7| |CO01|1|0|3|0||28|93489|0|0|9000|0|18I|X0|46|40|0|CO01|8| | |F|C52| |6|0|14|2|40|93489|CO07||C22|CONS_LATAM|
|| |0|X|2|0|0|2|45|7| |PA03|1|0|1|0||28|93489|0|0|6|0|18D|X0|33|40|0|PA03|8| | |F| | |6|0|63|2|40|93489|PA06||P06|CONS_LATAM|
||C|0|X|2|0|0|2|99|45| |PE01|2|0|1|0||28|93489|0|0|6|0|18D|X0|34|40|0|PE01|8| | |F| |KI|6|0|42|2|40|93489|PE03||R01|CONS_LATAM|
||A|0|X|2|0|0|2|45|7| |BR07|2|0|0|0||WB|530217|0|0|6|0|13R|X0|7|40|0|BR07|13| | |F| |KI|6|0|14|2|40|530217|BR54||B02|CONS_LATAM|
||C|0|X|2|0|0|2|45|7| |BR07|2|0|0|1||WB|93489|0|0|120|0|18D|X0|7|40|0|BR07|8| | |F| |KI|120|0|21|2|40|93489|BR54||B86|CONS_LATAM|
|| |0|X|2|0|0|2|45|7| |CR02|1|0|1|0||28|93489|0|0|6|0|18I|X0|41|40|0|CR02|8| | |F| | |6|0|0| |39|93489|CR02||P06|CONS_LATAM|
|| |0|X|2|0|0|2|99|7| |PA04|1|0|0|0||MB|93489|0|0|6|0|CPB|M0|0|40|0|PA04|8| | |F| | |6|0|0| | |93489|||P06|CONS_LATAM|
||C|0|X|2|0|0|2|45|7| |VE02|2|0|0|0||MB|93489|0|0|504|0|999|ND|0| |133|VE02|5| | |F| | |504|0|56|2|90|93489|VE07||V01|CONS_LATAM|
||A|0|X|2|0|0|2|99|35| |PA01|1|0|0|0||28|529523|0|0|6|0|CPS|X0|0|40|0|PA01|8| | |F| | |6|0|0| |90|529523|PA05||P06|CONS_LATAM|
|| |0|X|2|0|0|2|45|20| |MX02|1|0|2|0||90|93489|0|0|504|0|999|ND|1|40|0|MX02|8| | |F| | |504|0|33|2|90|93489|MX32||M39|CONS_LATAM|
|| |0|X|2|0|0|2|45|7| |CR01|1|0|1|0||28|93489|0|0|6|0|18I|X0|0|40|0|CR01|8| | |F| | |6|0|49|2|90|93489|CR01||P06|CONS_LATAM|
|| |0|X|2|0|0|2|45|7| |DO01|2|0|1|0||WB|93489|0|0|6|0|18I|X0|27|40|0|DO01|8| | |F| | |6|0|49|2|39|93489|DO02||D12|CONS_LATAM|
|| |0|X|2|0|0|2|45|7| |AR02|1|0|0|0||WB|93489|0|0|6|0|18D|X0|0|40|0|AR02|8| | |F| | |6|0|35|2|90|93489|AR07||A16|CONS_LATAM|
||A|0|X|2|0|0|2|45|7| |PE01|2|0|4|0||WB|70538|0|0|12|0|07R|X0|24|40|10|PE01|8| | |F| |KI|12|0|28|2|44|70538|PE03||R02|CONS_LATAM|
||C|0|X|2|0|0|2|99|7| |EC01|1|0|1|0||60|93489|0|0|6|0|18I|X0|48|40|0|EC01|8| | |F| |KI|504|0|42|2|40|93489|EC03||E11|CONS_LATAM|
||A|0|X|2|0|0|2|99|7| |DO01|1|0|0|0||28|529523|0|0|6|0|CPS|X0|14|40|0|DO01|8| | |F| | |6|0|28|2|30|529523|DO02||P06|CONS_LATAM|
|| |0|X|2|0|0|2|99|7| |PA04|1|0|1|0||WB|441441|0|0|12|0|CPC|X0|34|40|0|PA04|4| | |F| | |12|0|56|2| |441441|||P06|CONS_LATAM|
|| |0|X|2|0|0|2|45|7| |PY01|1|0|7|0||WB|93489|0|0|6|0|CPB|M0|14|40|0|PY01|8| | |F|J99| |6|0|0| | |93489|PY01|| |CONS_LATAM|
||C|0|X|2|0|0|2|45|7| |BR25|2|0|0|0||WB|93489|0|0|120|0|18D|X0|5|40|0|BR25|8| | |F| |KI|120|0|14|2|40|93489|BR69||B86|CONS_LATAM|
|| |0|X|2|0|0| |0|0| |HN01| |0|0|0|| |93489|0|0|0|0| | |0| |0|HN01|8| | | | | |0|0|0| | |93489|||P06|CONS_LATAM|
||C|0|X|2|0|0|2|99|7| |VE01|1|0|1|0||MB|93489|0|0|504|0|999|ND|41|40|133|VE01|5| | |F| | |504|0|0| |40|93489|VE06||V01|CONS_LATAM|
|| |0|X|2|0|0|2|45|20| |MX01|1|0|0|0||28|93489|0|0|504|0|999|ND|39|40|0|MX01|8| | |F| | |6|0|0| |40|93489|MX31||M16|CONS_LATAM|
||A|0|X|2|0|0|2|99|7| |CR02|1|0|0|0||28|529523|0|0|6|0|CPS|X0|0|40|0|CR02|8| | |F| | |6|0|0| | |529523|CR02||P06|CONS_LATAM|
|| |0|X|2|0|0|2|99|7| |GT02|1|0|1|0||WB|93489|0|0|6|0|18I|X0|34|40|0|GT02|8| | |F| | |6|0|0| |39|93489|GT02||P06|CONS_LATAM|
|| |0|X|2|0|0|2|99|45| |DO01|1|0|1|0||WB|441441|0|0|12|0|14R|X0|27|40|0|DO01|8| | |F| | |12|0|0| |39|441441|DO02||P06|CONS_LATAM|
|| |0|X|2|0|0|2|99|35| |PA01|1|0|0|0||WB|441441|0|0|12|0|14R|X0|0|40|0|PA01|8| | |F| | |12|0|0| |90|441441|PA05||P06|CONS_LATAM|
||C|0|X|2|0|0|2|45|7| |BR16|2|0|0|0||WB|93489|0|0|120|0|18D|X0|2|40|0|BR16|8| | |F| |KI|120|0|21|2|40|93489|BR61||B86|CONS_LATAM|
||C|0|X|2|0|0|2|45|7| |BR19|2|0|0|0||WB|93489|0|0|120|0|18D|X0|2|40|0|BR19|8| | |F| |KI|120|0|21|2|40|93489|BR63||B86|CONS_LATAM|
||A|0|X|2|0|0|2|99|7| |GT02|1|0|0|0||28|529523|0|0|6|0|CPS|X0|27|40|0|GT02|8| | |F| | |6|0|0| | |529523|GT02||P06|CONS_LATAM|
||C|0|X|2|0|0|2|45|7| |UY01|1|0|6|0||28|93489|0|0|6|0|14D|X0|8|40|0|UY01|8| | |F|J99|KI|6|0|42|2|40|93489|UY07||U10|CONS_LATAM|
||A|0|X|2|0|0|2|45|7| |BR16|2|0|0|0||WB|530217|0|0|6|0|13R|X0|2|40|0|BR16|13| | |F| |KI|6|0|21|2|40|530217|BR61||B02|CONS_LATAM|
||A|0|X|2|0|0|2|45|7| |BR19|2|0|0|0||WB|530217|0|0|6|0|13R|X0|2|40|0|BR19|13| | |F| |KI|6|0|21|2|40|530217|BR63||B02|CONS_LATAM|
|| |0|X|2|0|0|2|45|7| |PA01|1|0|1|0||28|93489|0|0|6|0|18I|X0|41|40|0|PA01|8| | |F| | |6|0|0| |90|93489|PA05||P06|CONS_LATAM|
||A|0|X|2|0|0|2|45|7| |BR25|2|0|0|0||WB|530217|0|0|6|0|13R|X0|4|40|0|BR25|13| | |F| |KI|6|0|14|2|40|530217|BR69||B02|CONS_LATAM|
|| |0|X|2|0|0|2|99|7| |GT02|1|0|1|0||WB|441441|0|0|12|0|14R|X0|34|40|0|GT02|8| | |F| | |12|0|0| |39|441441|GT02||P06|CONS_LATAM|
|| |0|X|2|0|0|2|99|7| |PA03|1|0|1|0||WB|441441|0|0|12|0|14R|X0|34|40|0|PA03|8| | |F| | |12|0|56|2| |441441|PA06||P06|CONS_LATAM|



      
    And I wait "/edm/material_plant_v1" Async Queue complete

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | sourceSystem | localSourceSystem |
      | CONS_LATAM    | [Consumer LATAM]  |
    And I wait "/edm/source_system_v1" Async Queue complete

    Given I import "/edm/source_list_v1" by keyFields "localMaterialNumber,localPlant"
        |localAgreementItem|localAgreementNumber|localBlockedSourceofSupply|localCategoryofSourceListRecord|localDateonWhichRecordWasCreated|localFixedOutlinePurchaseAgreementItem|localFixedvendor|localMaterialNumber|localMaterialNumberCorrespondingtoManufacturerPartNumber|localNameofPersonwhoCreatedtheObject|localNumberofSourceListRecord|localPlant|localPlantfromWhichMaterialisProcured|localPurchasingDocumentCategory|localPurchasingOrganization|localSourceListRecordValidFrom|localSourceListRecordValidTo|localSourceListUsageinMaterialsPlanning|localVendorAccountNumber|sourceSystem|
|140|5100005966| |0|20160114|X| |93489| |MDELOSA|16|DO01|PA03|K|DO00|20151214|99991230|1|78772|CONS_LATAM|
|0| | |7|20121228| | |93489| |JCABRAL2|2|BR19|BR12| |BR00|20121228|99991230| |8917|CONS_LATAM|
|0| | |0|20171130| | |530217| |WREIS|4|BR12| | |BR00|20171130|99991230| |1189|CONS_LATAM|
|10|5100006734| |0|20180413| | |93489| |RPULCINO|7|UY01| |K|UY00|20180413|99991230| |94073|CONS_LATAM|
|0| | |0|20180507| | |93489| |RPULCINO|26|PA03| | |PA00|20180507|99991230| |94073|CONS_LATAM|
|0| | |7|20160114| | |93489| |MDELOSA|20|DO01|PA03| |DO00|20151214|99991230| |78772|CONS_LATAM|
|520|5100004765| |0|20160114| | |93489| |MDELOSA|15|DO01| |K|DO00|20151214|99991230| |36220|CONS_LATAM|
|7370|5100002372| |3|20171130|X| |530217| |WREIS|3|BR12| |K|BR00|20171130|99991230|1|1189|CONS_LATAM|
|1470|5100005187| |3|20170811|X| |529523| |LCASTI16|1|DO01| |K|DO00|20170811|99991230|1|34427|CONS_LATAM|
|0| | |7|20171218| | |530217| |KFREIRE|2|BR16|BR12| |BR00|20171218|99991230| |8917|CONS_LATAM|
|0| | |0|20130626| | |93489| |JNUNEZ10|5|PY01| | |PY00|20130526|99991230| |17627|CONS_LATAM|
|2200|5100004002| |0|20131014|X| |93489| |CBRAVO4|14|PA03|BR12|K|PA00|20130914|99991230|1|8917|CONS_LATAM|
|1170|5100005813| |3|20170811|X| |529523| |LCASTI16|1|PA03| |K|PA00|20170811|99991230|1|36124|CONS_LATAM|
|49340|5100001520| |0|20171218|X| |530217| |KFREIRE|1|BR07|BR12|K|BR00|20171218|99991230|1|8917|CONS_LATAM|
|3720|5100002675| |0|20100618|X| |93489| |RPULCINO|1|VE01|BR12|K|VE00|20100518|99991230|1|8917|CONS_LATAM|
|0| | |0|20180413| | |93489| |RPULCINO|10|UY01| | |UY00|20180413|99991230| |94073|CONS_LATAM|
|0| | |0|20151210| | |89390| |JLUISMOR|2|MX01| | |MX00|20151110|99991230| |50161|CONS_LATAM|
|0| | |7|20100618| | |93489| |RPULCINO|2|VE01|BR12| |VE00|20100518|99991230| |8917|CONS_LATAM|
|0| | |0|20160114| | |93489| |MDELOSA|18|DO01| | |DO00|20151214|99991230| |26593|CONS_LATAM|
|46300|5100001777| |0|20171218|X| |530217| |KFREIRE|1|BR16|BR12|K|BR00|20171218|99991230|1|8917|CONS_LATAM|
|5160|5100004226| |0|20130626|X| |93489| |JNUNEZ10|4|PY01| |K|PY00|20130526|99991230|1|17627|CONS_LATAM|
|0| | |7|20160129| | |89390| |MDELOSA|2|MX02|MX01| |MX01|20151229|99991230| |6109|CONS_LATAM|
|0| | |7|20100828| | |93489| |RPULCINO|4|CO01|BR12| |CO00|20100101|99991230| |8917|CONS_LATAM|
|0| | |0|20100828| | |93489| |BARRUDA|5|EC01| | |EC00|20100101|99991230| |17627|CONS_LATAM|
|0| | |0|20111118| | |93489| |JMOREIR1|6|MX01| | |MX00|20110919|99991230| |17627|CONS_LATAM|
|34970|5100001520| |0|20121227|X| |93489| |JCABRAL2|1|BR07|BR12|K|BR00|20121227|99991230|1|8917|CONS_LATAM|
|0| | |0|20170222| | |441441| |GCORRE2|2|DO01| | |DO00|20170222|99991230| |50161|CONS_LATAM|
|0| | |0|20160527| | |93489| |LCASTI16|31|GT02| | |GT00|20160527|99991230| |36220|CONS_LATAM|
|0| | |7|20101022| | |93489| |JRASQUIN|2|MX02|MX01| |MX01|20101022|99991230| |6109|CONS_LATAM|
|0| | |7|20171218| | |530217| |KFREIRE|2|BR07|BR12| |BR00|20171218|99991230| |8917|CONS_LATAM|
|650|5100002859| |0|20130626| | |93489| |JNUNEZ10|3|PY01| |K|PY00|20130526|99991230| |6048|CONS_LATAM|
|4340|5100006222| |0|20150131| | |93489| |DNASCIM|4|SV01|PA03|K|SV00|20150131|99991230| |78772|CONS_LATAM|
|4340|5100006221| |0|20150131|X| |93489| |DNASCIM|4|HN01|PA03|K|HN00|20150131|99991230|1|78772|CONS_LATAM|
|0| | |0|20130626| | |93489| |JNUNEZ10|6|PY01| | |PY00|20130526|99991230| |6048|CONS_LATAM|
|260|5100005999| |0|20131011|X| |93489| |PLA_FCUSER1|1|CL01|BR12|K|CL00|20130904|99991231|1|8917|CONS_LATAM|
|0| | |0|20111112| | |93489| |LFERRE46|2|PY04| | |PY00|20111112|29991230| |17627|CONS_LATAM|
|3650|5100004222| |0|20111112|X| |93489| |LFERRE46|1|PY04| |K|PY00|20111112|29991230|1|17627|CONS_LATAM|
|0| | |7|20140218| | |93489| |FGUTIER6|14|PE01|CO01| |PE00|20140118|99991230| |4672|CONS_LATAM|
|31380|5100001777| |0|20121228|X| |93489| |JCABRAL2|1|BR16|BR12|K|BR00|20121228|99991230|1|8917|CONS_LATAM|
|9650|5100004172| |0|20150131| | |93489| |DNASCIM|3|SV01| |K|SV00|20141231|99991230| |26593|CONS_LATAM|
|10170|5100004174| |0|20120613| | |93489| |JNUNEZ10|1|HN01| |K|HN00|20120613|99991230| |26593|CONS_LATAM|
|0| | |7|20171218| | |530217| |KFREIRE|2|BR25|BR12| |BR00|20171218|99991230| |8917|CONS_LATAM|
|0| | |7|20140218| | |93489| |FGUTIER6|13|PE01|BR12| |PE00|20140118|99991230| |8917|CONS_LATAM|
|0| | |0|20150131| | |93489| |DNASCIM|5|SV01| | |SV00|20141231|99991230| |26593|CONS_LATAM|
|1750|5100006315| |0|20151210|X| |89390| |JLUISMOR|1|MX01| |K|MX00|20151110|99991230|1|50161|CONS_LATAM|
|380|5100003857| |0|20160114| | |93489| |MDELOSA|13|DO01|BR12|K|DO00|20151214|99991230| |8917|CONS_LATAM|
|0| | |0|20120613| | |93489| |JNUNEZ10|2|HN01| | |HN00|20120613|99991230| |26593|CONS_LATAM|
|11380|5100006425| |0|20171218|X| |530217| |KFREIRE|1|BR25|BR12|K|BR00|20171218|99991230|1|8917|CONS_LATAM|
|0| | |7|20121228| | |93489| |JCABRAL2|2|BR16|BR12| |BR00|20121228|99991230| |8917|CONS_LATAM|
|0| | |0|20170222| | |441441| |GCORRE2|3|CR02| | |CR00|20170222|99991230| |50161|CONS_LATAM|
|0| | |7|20100828| | |93489| |BARRUDA|4|EC01|BR12| |EC00|20100101|99991230| |8917|CONS_LATAM|
|890|5100004942| |0|20160322|X| |89390| |LCASTI16|1|PE01| |K|PE00|20160322|99991230|1|50161|CONS_LATAM|
|870|5100005474| |0|20140430| | |93489| |JALARCON|20|GT02|BR12|K|GT00|20140330|99991230| |8917|CONS_LATAM|
|20670|5100004195| |0|20121228|X| |93489| |JCABRAL2|1|BR19|BR12|K|BR00|20121228|99991230|1|8917|CONS_LATAM|
|0| | |7|20171218| | |530217| |KFREIRE|2|BR19|BR12| |BR00|20171218|99991230| |8917|CONS_LATAM|
|0| | |7|20140430| | |93489| |JALARCON|22|GT02|BR12| |GT00|20140330|99991230| |8917|CONS_LATAM|
|5750|5100003994| |0|20140430| | |93489| |JALARCON|19|GT02| |K|GT00|20140330|99991230| |26593|CONS_LATAM|
|0| | |0|20180504| | |70538| |LCASTI16|6|PE01| | |PE00|20180504|99991230| |94072|CONS_LATAM|
|4310|5100002674| |0|20140218|X| |93489| |FGUTIER6|11|PE01|BR12|K|PE00|20140118|99991230|1|8917|CONS_LATAM|
|0| | |0|20140430| | |93489| |JALARCON|23|GT02| | |GT00|20140330|99991230| |26593|CONS_LATAM|
|1510|5100004272| |0|20160527| | |93489| |LCASTI16|26|GT02| |K|GT00|20160527|99991230| |36220|CONS_LATAM|
|0| | |7|20160425| | |93489| |RFELIC13|19|CR02|PA03| |CR00|20160425|99991230| |78772|CONS_LATAM|
|1570|5100004997| |3|20180122|X| |529523| |LCASTI16|3|GT01| |K|GT00|20180122|99991230|1|36191|CONS_LATAM|
|1980|5100003970| |3|20180122|X| |529523| |LCASTI16|3|CR01| |K|CR00|20180122|99991230|1|36328|CONS_LATAM|
|0| | |0|20121228| | |93489| |JCABRAL2|4|BR12| | |BR00|20121228|99991230| |13074|CONS_LATAM|
|0| | |7|20160204| | |93489| |AHERMINI|12|CR02|BR12| |CR00|20160104|99991230| |8917|CONS_LATAM|
|5670|5100006425| |0|20160624|X| |93489| |WREIS|1|BR25|BR12|K|BR00|20160624|99991230|1|8917|CONS_LATAM|
|610|5100003983| |0|20131014| | |93489| |CBRAVO4|13|PA03| |K|PA00|20130914|99991230| |32368|CONS_LATAM|
|0| | |0|20131014| | |93489| |CBRAVO4|18|PA03| | |PA00|20130914|99991230| |36220|CONS_LATAM|
|20|5100006759| |0|20180507| | |93489| |RPULCINO|22|PA03| |K|PA00|20180507|99991230| |94073|CONS_LATAM|
|6800|5100002684| |0|20140218| | |93489| |FGUTIER6|12|PE01|CO01|K|PE00|20140118|99991230| |4672|CONS_LATAM|
|0| | |0|20170222| | |441441| |GCORRE2|2|PA03| | |PA00|20170222|99991230| |50161|CONS_LATAM|
|0| | |7|20150131| |X|93489| |DNASCIM|6|SV01|PA03| |SV00|20141231|99991230|1|78772|CONS_LATAM|
|820|5100006217| |0|20160425|X| |93489| |RFELIC13|16|CR02|PA03|K|CR00|20160425|99991230|1|78772|CONS_LATAM|
|4300|5100002156| |0|20100828|X| |93489| |RPULCINO|3|CO01|BR12|K|CO00|20100101|99991230|1|8917|CONS_LATAM|
|930|5100005469| |0|20111123|X| |93489| |JMOREIR1|7|MX01|BR12|K|MX00|20111118|99991230|1|8917|CONS_LATAM|
|0| | |0|20160322| | |89390| |LCASTI16|2|PE01| | |PE00|20160322|99991230| |50161|CONS_LATAM|
|4370|5100003715| |0|20160129|X| |89390| |MDELOSA|1|MX02|MX01|K|MX01|20151229|99991230|1|6109|CONS_LATAM|
|0| | |7|20111123| | |93489| |JMOREIR1|8|MX01|BR12| |MX00|20110919|99991230| |8917|CONS_LATAM|
|4150|5100002500| |0|20100828|X| |93489| |BARRUDA|3|EC01|BR12|K|EC00|20100101|99991230|1|8917|CONS_LATAM|
|30|5100006733| |0|20180411| | |93489| |RPULCINO|17|PE01| |K|PE00|20180411|99991230| |94073|CONS_LATAM|
|0| | |7|20100828| | |93489| |RSOUZA14|4|UY01|BR12| |UY00|20100101|99991230| |8917|CONS_LATAM|
|0| | |0|20170811| | |529523| |LCASTI16|2|PA03| | |PA00|20170811|99991230| |36124|CONS_LATAM|
|0| | |7|20160114| | |93489| |MDELOSA|17|DO01|BR12| |DO00|20151214|99991230| |8917|CONS_LATAM|
|0| | |7|20131014| | |93489| |CBRAVO4|16|PA03|BR12| |PA00|20130914|99991230| |8917|CONS_LATAM|
|0| | |0|20100828| | |93489| |RPULCINO|5|CO01| | |CO00|20100101|99991230| |17627|CONS_LATAM|
|930|5100005119| |0|20131014| | |93489| |CBRAVO4|15|PA03| |K|PA00|20130914|99991230| |36220|CONS_LATAM|
|0| | |0|20160204| | |93489| |AHERMINI|13|CR02| | |CR00|20160104|99991230| |26593|CONS_LATAM|
|3440|5100003114| |0|20100828|X| |93489| |RSOUZA14|3|UY01|BR12|K|UY00|20100101|29991231|1|8917|CONS_LATAM|
|180|5100006308| |0|20170222|X| |441441| |GCORRE2|1|GT02| |K|GT00|20170222|99991230|1|50161|CONS_LATAM|
|150|5100006013| |0|20140430|X| |93489| |JALARCON|21|GT02|PA03|K|GT00|20140330|99991230|1|78772|CONS_LATAM|
|0| | |7|20121227| | |93489| |JCABRAL2|2|BR07|BR12| |BR00|20121227|99991230| |8917|CONS_LATAM|
|0| | |0|20160114| | |93489| |MDELOSA|19|DO01| | |DO00|20151214|99991230| |36220|CONS_LATAM|
|34810|5100004195| |0|20171218|X| |530217| |KFREIRE|1|BR19|BR12|K|BR00|20171218|99991230|1|8917|CONS_LATAM|
|0| | |0|20160624| | |93489| |WREIS|2|BR25| | |BR00|20160624|99991230| |13074|CONS_LATAM|
|870|5100005473| |0|20160204| | |93489| |AHERMINI|11|CR02|BR12|K|CR00|20160104|99991230| |8917|CONS_LATAM|
|1980|5100003728| |0|20101022|X| |93489| |JRASQUIN|1|MX02|MX01|K|MX01|20101022|99991230|1|6109|CONS_LATAM|
|190|5100006306| |0|20170222|X| |441441| |GCORRE2|2|CR02| |K|CR00|20170222|99991230|1|50161|CONS_LATAM|
|0| | |0|20170811| | |529523| |LCASTI16|2|DO01| | |DO00|20170811|99991230| |34427|CONS_LATAM|
|0| | |0|20180122| | |529523| |LCASTI16|4|GT01| | |GT00|20180122|99991230| |36191|CONS_LATAM|
|1150|5100006758| |0|20180504| | |70538| |LCASTI16|4|PE01| |K|PE00|20180504|99991230| |94072|CONS_LATAM|
|1870|5100001886| |3|20121228|X| |93489| |JCABRAL2|3|BR12| |K|BR00|20121228|99991230|1|13074|CONS_LATAM|
|0| | |7|20101213| | |93489| |RSOUZA14|2|AR01|BR12| |AR00|20101213|29991230| |8917|CONS_LATAM|
|1690|5100004685| |0|20160114| | |93489| |MDELOSA|14|DO01| |K|DO00|20151214|99991230| |26593|CONS_LATAM|
|0| | |0|20180122| | |529523| |LCASTI16|4|CR01| | |CR00|20180122|99991230| |36328|CONS_LATAM|
|0| | |7|20160624| | |93489| |WREIS|3|BR25|BR12| |BR00|20160624|99991230| |8917|CONS_LATAM|
|6370|5100003997| |0|20160204| | |93489| |AHERMINI|10|CR02| |K|CR00|20160104|99991230| |26593|CONS_LATAM|
|4160|5100003020| |0|20101213|X| |93489| |RSOUZA14|1|AR01|BR12|K|AR00|20101213|29991230|1|8917|CONS_LATAM|
|0| | |0|20170222| | |441441| |GCORRE2|2|GT02| | |GT00|20170222|99991230| |50161|CONS_LATAM|
|430|5100006309| |0|20170222|X| |441441| |GCORRE2|1|PA03| |K|PA00|20170222|99991230|1|50161|CONS_LATAM|
|7910|5100002684| |0|20151112|X| |70538| |MDELOSA|1|PE01|CO01|K|PE00|20151012|99991230|1|4672|CONS_LATAM|
|0| | |0|20131014| | |93489| |CBRAVO4|17|PA03| | |PA00|20130914|99991230| |32368|CONS_LATAM|
|0| | |0|20180411| | |93489| |RPULCINO|20|PE01| | |PE00|20180411|99991230| |94073|CONS_LATAM|
|200|5100006307| |0|20170222|X| |441441| |GCORRE2|1|DO01| |K|DO00|20170222|99991230|1|50161|CONS_LATAM|
|0| | |7|20140430| | |93489| |JALARCON|24|GT02|PA03| |GT00|20140330|99991230| |78772|CONS_LATAM|
|0| | |7|20151112| | |70538| |MDELOSA|2|PE01|CO01| |PE00|20151012|99991230| |4672|CONS_LATAM|
|0| | |7|20150131| | |93489| |DNASCIM|6|HN01|PA03| |HN00|20141231|99991230| |78772|CONS_LATAM|
        

    And I wait "/edm/source_list_v1" Async Queue complete

    Given I import "/plan/cns_spl_pln_loc" by keyFields "sourceSystem,localNumber,vendorOrCustomer"
      |_1specLocAttDesc|_2specLocAtt|_2specLocAttDesc|_2specLocAttdEsc|_3specLocAtt|_3specLocAttDesc|_test_reservation_|localCountry|localCurrency|localName|localNumber|localPlant|localRegion|pLanlocTypeDesc|pLanlocTypeId|planLocTypeDesc|planLocTypeId|sourceSystem|specLocAtt1|specLocAtt2|specLocAtt3|specLocAttDesc1|specLocAttDesc2|specLocAttDesc3|vendorOrCustomer|
||||||||CO|COP|SUPPLA SA|44776|CO01|103|||Mfg Plant Copacker|SUBCON|CONS_LATAM|||||||V|
||||||||BR|BRL|SUPPORT PACK IND E COM LTDA|1189|BR12|103|||Mfg Plant Copacker|SUBCON|CONS_LATAM|||||||V|
||||||||MX|MXN|AVP MAQUILA DE MEXICO S DE RL DE CV|86017|MX02|103|||Affiliate/Market Copacker|SUBCON|CONS_LATAM|||||||V|
||||||||PA|USD|J CAIN & CO|36124|PA03|103|||Affiliate/Market Copacker|SUBCON|CONS_LATAM|||||||V|
||||||||EC|USE|LODISAL S.A.|19574|EC01|103|||Affiliate/Market Copacker|SUBCON|CONS_LATAM|||||||V|
||||||||DO|DOP|YOBEL SRLÂ  Â  Â  Â  Â  Â  Â  Â |34427|DO01|103|||Affiliate/Market Copacker|SUBCON|CONS_LATAM|||||||V|
||||||||PE|USD|YOBEL SUPPLY CHAIN MANAGEMENT S.A.|20667|PE01|103|||Affiliate/Market Copacker|SUBCON|CONS_LATAM|||||||V|
||||||||CR|CRC|D.H.L. COSTA RICA (CORMAR), S.A.|36328|CR01|103|||Affiliate/Market Copacker|SUBCON|CONS_LATAM|||||||V|
||||||||BE||CILAG AG INTERNATIONAL|7303||104|||Ext Manufacturer FG|VENDOR|CONS_LATAM|||||||V|
||||||||CL|CLP|Apl Logistics Chile S.A.|79232|CL01|103|||Affiliate/Market Copacker|SUBCON|CONS_LATAM|||||||V|
||||||||UY|UYU|MODYLER SA|65659|UY01|103|||Affiliate/Market Copacker|SUBCON|CONS_LATAM|||||||V|
||||||||GT|GTQ|DHL GLOBAL FORWARDING (GUATEMALA)|36191|GT01|103|||Affiliate/Market Copacker|SUBCON|CONS_LATAM|||||||V|
||||||||BR|BRL|SUPPORT PACK IND E COM LTDA|1189|BR12|103|||Mfg Plant Copacker|SUBCON|CONS_LATAM||||||||
||||||||CO|COP|ASPRILLA ORTIZ FABIO|15574|CO01|103|||Mfg Plant Copacker|SUBCON|CONS_LATAM||||||||
||||||||US||JOHNSON & JOHNSON CONSUMER INC.|50161||101|||Ext Manufacturer FG|VENDOR|CONS_LATAM||||||||

    And I wait "/plan/cns_spl_pln_loc" Async Queue complete

    Given I import "/plan/cons_time_dep_xchange" by keyFields "effectiveEndDate,effectiveStartDate,fromCurrency"
      | sourceSystem | fromCurrency | toCurrency | effectiveEndDate | effectiveStartDate | exchangeRate     | preference |
      | CON_LATAM    | BRL          | USD        | 31/12/2019       | 2018/1/1           |                  |            |
      | CON_LATAM    | CLP          | USD        | 31/12/2019       | 2018/1/1           | 3.25000325000325 |            |
      | CON_LATAM    | COP          | USD        | 31/12/2019       | 2018/1/1           |                  |            |
      | CON_LATAM    | USD          | USD        | 31/12/2019       | 2018/1/1           |                  |            |
      | CON_LATAM    | ARS          | USD        | 31/12/2019       | 2018/1/1           | 3.00000000000000 |            |
      | CON_LATAM    | MXN          | USD        | 31/12/2019       | 2019/1/1           |                  |            |

    And I wait "/plan/cons_time_dep_xchange" Async Queue complete

    Given I import "/edm/plant_v1" by keyFields "sourceSystem,localPlant"
      | localPlant | localCurrency | sourceSystem |
      | BR01       | BRL           | CON_LATAM    |
      | BR02       | CLP           | CON_LATAM    |
      | BR03       | CLP           | CON_LATAM    |
      | BR04       | USD           | CON_LATAM    |
      | BR06       | ARS           | CON_LATAM    |
      | BR07       | MXN           | CON_LATAM    |

    And I wait "/edm/plant_v1" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      |baseUom|batchManageIndicator|brand|category|division|flagForDeletion|form|franchise|getPrimaryPlanningCode|globalBusinessUnit|globalDpParentCode|localBaseUom|localDpParentCode|localManufacturingTechnology|localMaterialGroup|localMaterialNumber|localMaterialType|localRefDescription|manufacturingTechnology|materialGroup|materialNumber|materialNumber16|materialStatus|materialType|minRemShelfLife|minremshelflife21|parentCode|primaryPlanningCode|productFamily|refDescription|sourceSystem|subBrand|testReservation|totalShelfLife|
			|EA|X|BRD006|239|10| |100212|FCH002||GFO001||EA|7.89E+16||1|93489|FERT|NTG U-LIGHT FACIAL MOIST COMB/OIL 6X55G|||93489|93489|8|FERT|450||7.89E+16|93489||NTG U-LIGHT FACIAL MOIST COMB/OIL 6X55G|CONS_LATAM|3F||1095|
			|EA|X|||10| ||||||EA|||1|530217|FERT|TARGETED BIONIC EYE CREAM PLUS 15G|||530217|530217|8|FERT|180|||530217||TARGETED BIONIC EYE CREAM PLUS 15G|CONS_LATAM|||1095|
			|EA|X|||10| ||||||EA|||1|529523|FERT|PACK GOTAS DE BRILLO SH400+SPRAY200|||529523|529523|8|FERT|180|||529523||PACK GOTAS DE BRILLO SH400+SPRAY200|CONS_LATAM|||730|
			|EA|X|||10| ||||||EA|||3|89390|FERT|NEUT JABON ACNE 24X99g|||89390|89390|8|FERT|365|||89390||NEUT JABON ACNE 24X99g|CONS_LATAM|||730|
			|EA|X|||10| ||||||EA|||1|441441|FERT|DEEP CLEAN SCRUB 4.2OZ 124ML|||441441|441441|8|FERT|180|||441441||DEEP CLEAN SCRUB 4.2OZ 124ML|CONS_LATAM|||730|
			|EA|X|||10| ||||||EA||Stayfree COL|1|70538|FERT|ST ESPECIAL ALAS AN WW 12X42|||70538|70538|8|FERT|180|||70538||ST ESPECIAL ALAS AN WW 12X42|CONS_LATAM|||730|

    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/edm/mat_plant_fi_v1" by keyFields "localMaterialNumber,localPlant,sourceSystem"
      |localDeleIndicator|localMaterialNumber|localMvp|localPlant|localPriceUnit|localStandardPrice|plant|priceControl|sourceSystem|
		 ||70538|2839.45|CO02|1|2740.54|CO08|S|CONS_LATAM|
		 ||93489|21279.35|VE02|1000|21279.35|VE07|S|CONS_LATAM|
		 ||529523|2.62|PA01|1|2.62|PA05|S|CONS_LATAM|
		 ||93489|2.2|PA03|1|1.66|PA06|S|CONS_LATAM|
		 ||93489|4.84|PE01|1|6.41|PE03|S|CONS_LATAM|
		 ||93489|11.38|BR19|1|12.21|BR63|S|CONS_LATAM|
		 ||89390|3.93|PE01|1|3.36|PE03|S|CONS_LATAM|
		 ||529523|1534.3|CR01|1|1534.3|CR01|S|CONS_LATAM|
		 ||93489|2.34|EC01|1|2.34|EC03|S|CONS_LATAM|
		 ||530217|0|BR25|1|20.73|BR69|S|CONS_LATAM|
		 ||93489|868.62|CR02|1|999.47|CR02|S|CONS_LATAM|
		 ||89390|22.76|MX02|1|29.52|MX32|S|CONS_LATAM|
		 ||93489|10090.84|PY01|1|11691.39|PY01|S|CONS_LATAM|
		 ||93489|2.53|SV01|1|2.38||S|CONS_LATAM|
		 ||93489|5.83|BR25|1|6.17|BR69|S|CONS_LATAM|
		 ||529523|0|PA04|1|0||S|CONS_LATAM|
		 ||441441|0|CR02|1|741.98|CR02|S|CONS_LATAM|
		 ||93489|32.08|AR01|1|44.85|AR06|S|CONS_LATAM|
		 ||530217|0|BR12|1|16.39|BR59|S|CONS_LATAM|
		 ||93489|0|PA04|1|1.88||S|CONS_LATAM|
		 ||93489|1096.92|CR01|1|1150|CR01|S|CONS_LATAM|
		 ||529523|2.04|PA03|1|2.48|PA06|S|CONS_LATAM|
		 ||89390|23.27|MX01|1|27.59|MX31|S|CONS_LATAM|
		 ||529523|0|CR02|1|1426|CR02|S|CONS_LATAM|
		 ||93489|15.41|GT02|1|13.16|GT02|S|CONS_LATAM|
		 ||441441|0|DO01|1|77.91|DO02|S|CONS_LATAM|
		 ||93489|0|CO02|1|5562.41|CO08|S|CONS_LATAM|
		 ||441441|0|GT02|1|9.93|GT02|S|CONS_LATAM|
		 ||70538|3.8|PE01|1|3.81|PE03|S|CONS_LATAM|
		 ||530217|0|BR16|1|20.73|BR61|S|CONS_LATAM|
		 ||93489|35.03|MX01|1|30.28|MX31|S|CONS_LATAM|
		 ||93489|98.93|DO01|1|102.9|DO02|S|CONS_LATAM|
		 ||441441|0|CR01|1|856.75|CR01|S|CONS_LATAM|
		 ||70538|2798.2|CO01|1|2740.54|CO07|S|CONS_LATAM|
		 ||93489|9520.99|PY04|1|8352.1||S|CONS_LATAM|
		 ||93489|21279.35|VE01|1000|21279.35|VE06|S|CONS_LATAM|
		 ||441441|0|GT01|1|11.47|GT01|S|CONS_LATAM|
		 ||93489|10.02|CL01|1|10.7|CL03|S|CONS_LATAM|
		 ||93489|2.76|PA01|1|1.84|PA05|S|CONS_LATAM|
		 ||441441|1.32|PA01|1|1.32|PA05|S|CONS_LATAM|
		 ||93489|5.87|BR16|1|6.23|BR61|S|CONS_LATAM|
		 ||93489|51.82|UY01|1|51.82|UY07|S|CONS_LATAM|
		 ||93489|4.66|BR12|1|4.86|BR59|S|CONS_LATAM|
		 ||529523|116.96|DO01|1|116.96|DO02|S|CONS_LATAM|
		 ||530217|200|BR19|1|0|BR63|V|CONS_LATAM|
		 ||441441|0|PA04|1|0||S|CONS_LATAM|
		 ||93489|42.33|AR02|1|44.85|AR07|S|CONS_LATAM|
		 ||93489|41.19|HN01|1|57.31||S|CONS_LATAM|
		 ||441441|1.14|PA03|1|1.17|PA06|S|CONS_LATAM|
		 ||530217|0|BR07|1|20.73|BR54|S|CONS_LATAM|
		 ||529523|0|GT02|1|18.82|GT02|S|CONS_LATAM|
		 ||93489|4.66|BR14|1|19.66||S|CONS_LATAM|
		 ||93489|5.86|BR07|1|6.17|BR54|S|CONS_LATAM|
		 ||93489|16.8|GT01|1|15.12|GT01|S|CONS_LATAM|
		 ||93489|5824.83|CO01|1|5562.41|CO07|S|CONS_LATAM|
		 ||93489|31.71|MX02|1|31.19|MX32|S|CONS_LATAM|
		 ||529523|17.15|GT01|1|18.66|GT01|S|CONS_LATAM|


    And I wait "/edm/mat_plant_fi_v1" Async Queue complete

    Given I import "/plan/cns_spl_proc_typ" by keyFields "localSplProcType,localSplProcTypeDesc,sourceSystem,splProcType,splProcTypeDesc"
      |localSplProcType|localSplProcTypeDesc|sourceSystem|splProcType|splProcTypeDesc|
			|60|Phantom in planning|CONS_LATAM|60|Phantom in planning|
			|21|SNC External Procurement|CONS_LATAM|21|SNC External Procurement|
			|50|Phantom assembly|CONS_LATAM|50|Phantom assembly|
			|80|Production in alternative|CONS_LATAM|80|Production in alternative plant|
			|42|Stock transfer (proc.from|CONS_LATAM|42|Stock transfer (proc.from|
			|90|Consignment|CONS_LATAM|10|Consignment|
			|31|SNC Subcontracting|CONS_LATAM|31|SNC Subcontracting|
			|41|Stock transfer (proc.from|CONS_LATAM|41|Stock transfer (proc.from|
			|52|Direct production / colle|CONS_LATAM|52|Direct production / collective order|
			|40|InterCompany BR12 -> BR07|CONS_LATAM|40|Stock transfer (proc.from alter CA04)|
			|30|Subcontracting|CONS_LATAM|30|Subcontracting|
			|20|External procurement|CONS_LATAM|20|External procurement|
			|70|Withdrawal from alternati|CONS_LATAM|70|Withdrawal from alternative plant|

    And I wait "/plan/cns_spl_proc_typ" Async Queue complete

    Given I import "/plan/cns_prod_loc_attrib" by keyFields "localMaterialNumber,localPlant,sourceSystem"
      |SchdAttrbDesc2|SchdAttrbDesc3|SchdAttrbName1|SchedAttrbName2|SchedAttrbName3|SourceSystem|_test_reservation_|localMaterialNumber|localPlant|localmaterialnumber|localplant|minMinShelfLife|minMinshelfLife|minShelfLife|minminshelflife|minshelflife|primarySupplierPlantCode|schAttrbDesc1|schAttrbDesc2|schAttrbDesc3|schAttrbdesc1|schattrbdesc1|schattrbdesc2|schattrbdesc3|schdAttrbName1|schdAttrbName2|schdAttrbName3|schdattrbname1|schdattrbname2|schdattrbname3|sourceSystem|sourcesystem|supplyGroup|supplygroup|
			||||||||000000000000527887|CO01||||||||CO01||||||||||||||CONS_LATAM||LA_SG_17||
			||||||||000000000000094719|BR12||||||||CO01|Size/Count|Formula/Shape|Formula/Shape 2|||||8|Adapt|Wings||||CONS_LATAM||LA_SG_7||
			||||||||000000000000527708|CO01||||||||CO01|Size/Count|Formula/Shape|Formula/Shape 2|||||8X6X125|Baby|GermFree||||CONS_LATAM||LA_SG_13||
			||||||||000000000000085235|CO01||||||||CO01||||||||||||||CONS_LATAM||LA_SG_17||
			||||||||000000000000068073|CO01||||||||CO01||||||||||||||CONS_LATAM||LA_SG_17||
			||||||||000000000000217503|50161||||||||CO01||||||||||||||CONS_LATAM||LA_SG_34||
			||||||||000000000000527853|CO01||||||||CO01|Size/Count|Formula/Shape|Formula/Shape 2|||||125Pack|Adult|Aloe||||CONS_LATAM||LA_SG_13||

    And I wait "/plan/cns_prod_loc_attrib" Async Queue complete

    Given I import "/plan/prod_loc_min_shelf" by keyFields "localMaterialNumber,localPlant,sourceSystem"
      |localMaterialNumber|localPlant|minMinShelfLife|minShelfLife|sourceSystem|
			| * |BR12||180|CONS_LATAM|
			| * |BR16||270|CONS_LATAM|
			|000000000000004002|BR12||150|CONS_LATAM|

    And I wait "/plan/prod_loc_min_shelf" Async Queue complete
#
    Given I import "/plan/cns_proc_type" by keyFields "localProcurementType,localProcurementTypeDescription,procurementType,procurementTypeDescription,sourceSystem"
      |localProcurementType|localProcurementTypeDescription|localProcurementTypedescription|procurementType|procurementTypeDescription|procurementTypedescription|sourceSystem|
			|F|External procurement||F|External procurement||CONS_LATAM|
			|X|Both procurement types||X|Both procurement types||CONS_LATAM|
			| |No procurement|||No procurement||CONS_LATAM|
			|E|In-house production||E|In-house production||CONS_LATAM|

    And I wait "/plan/cns_proc_type" Async Queue complete

    Given I import "/plan/cns_material_plan_status" by keyFields "localMaterialNumber,localPlant"
      |active|dpRelevant|localMaterialNumber|localParentCode|localPlant|materialNumber|noPlanRelevant|parentActive|ppc|sourceSystem|spRelevant|
			|X||93489|7.89101E+16|EC01|93489||X|93489|CONS_LATAM|X|
			|X||93489|7.89101E+16|CR02|93489||X|93489|CONS_LATAM|X|
			|X||93489|7.89101E+16|CR01|93489||X|93489|CONS_LATAM|X|
			|X||93489|7.89101E+16|PA03|93489||X|93489|CONS_LATAM|X|
			|X|X|93489|7.89101E+16|BR16|93489||X|93489|CONS_LATAM|X|
			|X|X|93489|7.89101E+16|BR07|93489||X|93489|CONS_LATAM|X|
			|X||93489|7.89101E+16|UY01|93489||X|93489|CONS_LATAM|X|
			|X||93489|7.89101E+16|AR01|93489||X|93489|CONS_LATAM|X|
			|X||93489|7.89101E+16|CL01|93489||X|93489|CONS_LATAM|X|
			|X||93489|7.89101E+16|BR12|93489||X|93489|CONS_LATAM|X|
			|X|X|93489|7.89101E+16|BR25|93489||X|93489|CONS_LATAM|X|
			|X||93489|7.89101E+16|GT01|93489||X|93489|CONS_LATAM|X|
			|X||93489|7.89101E+16|CO01|93489||X|93489|CONS_LATAM|X|
			|X||93489|7.89101E+16|PA01|93489||X|93489|CONS_LATAM|X|
			|X||93489|7.89101E+16|GT02|93489||X|93489|CONS_LATAM|X|
			|X|X|93489|7.89101E+16|BR19|93489||X|93489|CONS_LATAM|X|
			|X||93489|7.89101E+16|AR02|93489||X|93489|CONS_LATAM|X|
			|X||93489|7.89101E+16|PE01|93489||X|93489|CONS_LATAM|X|
			|X||93489|7.89101E+16|DO01|93489||X|93489|CONS_LATAM|X|
			|X||530217||BR12|530217|||530217|CONS_LATAM|X|
			|X|X|530217||BR25|530217|||530217|CONS_LATAM|X|
			|X|X|530217||BR07|530217|||530217|CONS_LATAM|X|
			|X|X|530217||BR19|530217|||530217|CONS_LATAM|X|
			|X|X|530217||BR16|530217|||530217|CONS_LATAM|X|
			|X||529523||DO01|529523|||529523|CONS_LATAM|X|
			|X||529523||GT02|529523|||529523|CONS_LATAM|X|
			|X||529523||CR02|529523|||529523|CONS_LATAM|X|
			|X||529523||GT01|529523|||529523|CONS_LATAM|X|
			|X||529523||PA01|529523|||529523|CONS_LATAM|X|
			|X||529523||CR01|529523|||529523|CONS_LATAM|X|
			|X||529523||PA03|529523|||529523|CONS_LATAM|X|
			|X||89390||PE01|89390|||89390|CONS_LATAM|X|
			|X||441441||DO01|441441|||441441|CONS_LATAM|X|
			|X||441441||PA03|441441|||441441|CONS_LATAM|X|
			|X||441441||CR01|441441|||441441|CONS_LATAM|X|
			|X||441441||PA01|441441|||441441|CONS_LATAM|X|
			|X||441441||GT01|441441|||441441|CONS_LATAM|X|
			|X||441441||CR02|441441|||441441|CONS_LATAM|X|
			|X||441441||GT02|441441|||441441|CONS_LATAM|X|
			|X||70538||CO02|70538|||70538|CONS_LATAM|X|
			|X||70538||PE01|70538|||70538|CONS_LATAM|X|

    And I wait "/plan/cns_material_plan_status" Async Queue complete

    Given I import "/plan/cns_plng_strat_grp" by keyFields "localPlanStratGrp,localPlanStratGrpDesc,sourceSystem"
      |localPlanStratGrp|localPlanStratGrpDesc|planStratGrp|planStratGrpDesc|sourceSystem|
			|86|Assembly processing with process orders|86|Assembly processing with process orders|CONS_LATAM|
			|60|Planning with planning material|60|Planning with planning material|CONS_LATAM|
			|32|Make-to-order prod. with prod. by lot|32|Make-to-order prod. with prod. by lot|CONS_LATAM|
			|33|Prod.by lot, with plng with final assem.|33|Prod.by lot, with plng with final assem.|CONS_LATAM|
			|74|Plng at assembly lvl w/o final assembly|74|Plng at assembly lvl w/o final assembly|CONS_LATAM|
			|0|No planning / no requirements transfer|0|No planning / no requirements transfer|CONS_LATAM|
			|30|Production by lot size|30|Production by lot size|CONS_LATAM|
			|65|Planning variants with planning material|65|Planning variants with planning material|CONS_LATAM|
			|51|Plng w/o final assembly / project settl.|51|Plng w/o final assembly / project settl.|CONS_LATAM|
			|52|Plnng w/o final assem. w/o make-to.stock|52|Plnng w/o final assem. w/o make-to.stock|CONS_LATAM|
			|31|Prod. by lot, with make-to-order prod.|31|Prod. by lot, with make-to-order prod.|CONS_LATAM|
			|70|Planning at assembly level|70|Planning at assembly level|CONS_LATAM|
			|83|Assembly processing with networks|83|Assembly processing with networks|CONS_LATAM|
			|80|Project settlement for non-stock items|80|Project settlement for non-stock items|CONS_LATAM|
			|89|Assembly proc. w. characteristics plng|89|Assembly proc. w. characteristics plng|CONS_LATAM|
			|61|Plng with plng material / project settl.|61|Plng with plng material / project settl.|CONS_LATAM|
			|56|Characteristics planning|56|Characteristics planning|CONS_LATAM|
			|59|Planning at phantom assembly level|59|Planning at phantom assembly level|CONS_LATAM|
			|11|Make-to-stock prod./gross reqmts plng|11|Make-to-stock prod./gross reqmts plng|CONS_LATAM|
			|82|Assembly processing w. production orders|82|Assembly processing w. production orders|CONS_LATAM|
			|50|Planning without final assembly|50|Planning without final assembly|CONS_LATAM|
			|55|Planning variants w/o final assembly|55|Planning variants w/o final assembly|CONS_LATAM|
			|63|Planning w.plng material w/o mke-to-ord.|63|Planning w.plng material w/o mke-to-ord.|CONS_LATAM|
			|26|Make-to-order for material variant|26|Make-to-order for material variant|CONS_LATAM|
			|25|Make-to-order for configurable material|25|Make-to-order for configurable material|CONS_LATAM|
			|85|Assembly processing with network/project|85|Assembly processing with network/project|CONS_LATAM|
			|10|Make-to-stock production|10|Make-to-stock production|CONS_LATAM|
			|84|Service orders|84|Service orders|CONS_LATAM|
			|81|Assembly processing with planned orders|81|Assembly processing with planned orders|CONS_LATAM|
			|21|Make-to-order prod./ project settlement|21|Make-to-order prod./ project settlement|CONS_LATAM|
			|40|Planning with final assembly|40|Planning with final assembly|CONS_LATAM|
			|20|Make-to-order production|20|Make-to-order production|CONS_LATAM|
			|54|Planning variants|54|Planning variants|CONS_LATAM|
			|41|Plng with final assem, with make-to-ord.|41|Plng with final assem, with make-to-ord.|CONS_LATAM|


    And I wait "/plan/cns_plng_strat_grp" Async Queue complete

    Given I import "/plan/cns_lot_size_key_trans" by keyFields "localLotSizeKey,sourceSystem"
      |localLotSizeKey|localLotSizeKeyDescription|lotSizeKey|lotSizeKeyDescription|sourceSystem|
			|EX|Lot-for-lot order quantity (JJ)|EX|Lot-for-lot order quantity (JJ)|CONS_LATAM|
			|15|Daily lot size 15 days  (JJ)|15|Daily lot size 15 days  (JJ)|CONS_LATAM|
			|PK|Period lot size acc. to plng calendar|PK|Period lot size acc. to plng calendar|CONS_LATAM|
			|WB|Weekly lot size|WB|Weekly lot size|CONS_LATAM|
			|SP|Part period balancing|SP|Part period balancing|CONS_LATAM|
			|MB|Monthly lot size (JJ)|MB|Monthly lot size (JJ)|CONS_LATAM|
			|45|Daily lot size 30 dias  (JJ)|45|Daily lot size 30 dias  (JJ)|CONS_LATAM|
			|HB|Replenish to maximum stock level (JJ)|HB|Replenish to maximum stock level (JJ)|CONS_LATAM|
			|W2|Week - 2|W2|Week - 2|CONS_LATAM|
			|7|Daily lot size 15 days  (JJ)|7|Daily lot size 15 days  (JJ)|CONS_LATAM|
			|28|Daily lot size 30 dias  (JJ)|28|Daily lot size 30 dias  (JJ)|CONS_LATAM|
			|60|Daily lot size 60 days  (JJ)|60|Daily lot size 60 days  (JJ)|CONS_LATAM|
			|14|Daily lot size 15 days  (JJ)|14|Daily lot size 15 days  (JJ)|CONS_LATAM|
			|DY|Dynamic lot size creation|DY|Dynamic lot size creation|CONS_LATAM|
			|WI|Least unit cost procedure|WI|Least unit cost procedure|CONS_LATAM|
			|30|Daily lot size 30 dias  (JJ)|30|Daily lot size 30 dias  (JJ)|CONS_LATAM|
			|GR|Groff reorder procedure|GR|Groff reorder procedure|CONS_LATAM|
			|90|Daily lot size 90 days  (JJ)|90|Daily lot size 90 days  (JJ)|CONS_LATAM|
			|TB|Daily lot size|TB|Daily lot size|CONS_LATAM|

    And I wait "/plan/cns_lot_size_key_trans" Async Queue complete

    Given I import "/plan/cns_con_mode" by keyFields "consumptionMode,sourceSystem"
      |consumptionMode|consumptionModeDescription|localConsumptionMode|localConsumptionModeDescription|localConsumptionModedEscription|sourceSystem|
			|1|Backward consumption only|1|Backward consumption only||CONS_LATAM|
			|4|Forward/backward consumption|4|Forward/backward consumption||CONS_LATAM|
			|2|Backward/forward consumption|2|Backward/forward consumption||CONS_LATAM|
			|3|Forward consumption only|3|Forward consumption only||CONS_LATAM|

    And I wait "/plan/cns_con_mode" Async Queue complete

    Given I import "/plan/cns_abc_ind" by keyFields "indicator,sourceSystem"
      |indicator|indicatorDescription|localIndicator|localIndicatorDescription|sourceSystem|
			|A|Significant Material|A|Significant Material|CONS_LATAM|
			|B|Material - Medium Significance|B|Material - Medium Significance|CONS_LATAM|
			|C|Material - Low Significance|C|Material - Low Significance|CONS_LATAM|

    And I wait "/plan/cns_abc_ind" Async Queue complete
    
    When I submit task with xml file "xml/omp/OMPGdmProductLocation.xml" and execute file "jar/pangea-view.jar"
    Then A file is found on sink application with name "GDMProductLocation.tsv"

    Then I check file data for filename "GDMProductLocation.tsv" by keyFields "productId|locationId"
    #Then I check region data "/omp/gdm_product_location" by keyFields "productId,locationId"
      |productId|active|activeFCTERP|activeOPRERP|activeSOPERP|BatchManaged|beskz|bstfe|bstma|bstmi|bstrf|disls|dismm|dispo|dzeit|MinInventoryQuantity|fevor|fixedHorizon|insmk|itemPlanningCategory|kausf|kzkri|label|leadTime|locationId|maabc|MaxInventoryQuantity|minmrsl|minRemainingShelfLife|mmsta|mtvfp|ProductTypeId|shflg|sobsl|strgr|supplyGroup|supplySource|totalShelfLife|vint1|vint2|vrmod|webaz|productValue|
			|93489|YES|NO|YES|NO|YES|F|0|0|6|6|WB|X0|18I|0|0| |0| ||0| |NTG U-LIGHT FACIAL MOIST COMB/OIL 6X55G|0|CONS_LATAM_GT01||0||38880000| |2|FERT|2|10|40|||94608000|99|7|2|1|15.12|
			|93489|YES|NO|YES|NO|YES|F|0|0|29400|29400|28|X0|18I|0|0| |0| ||0| |NTG U-LIGHT FACIAL MOIST COMB/OIL 6X55G|259200|CONS_LATAM_BR12|C|0||15552000| |2|FERT|2|30|40|||94608000|45|7|2|4|4.86|
			|93489|YES|NO|YES|NO|YES|F|0|0|9000|6|28|X0|18I|0|0|C52|0| ||0| |NTG U-LIGHT FACIAL MOIST COMB/OIL 6X55G|3974400|CONS_LATAM_CO01|C|0||38880000| |2|FERT|2|40|40|||94608000|7|7|2|3|5562.41|
			|93489|YES|NO|YES|NO|YES|F|0|0|504|6|14|X0|18D|0|0|J99|0| ||0| |NTG U-LIGHT FACIAL MOIST COMB/OIL 6X55G|1209600|CONS_LATAM_AR01||0||38880000| |2|FERT| |40|40|||94608000|45|7|2|3|44.85|
			|93489|YES|NO|YES|NO|YES|F|0|0|6|504|60|X0|18I|0|0| |0| ||0| |NTG U-LIGHT FACIAL MOIST COMB/OIL 6X55G|4147200|CONS_LATAM_EC01|C|0||38880000| |2|FERT|2|40|40|||94608000|99|7|2|1|2.34|
			|93489|YES|NO|YES|NO|YES|F|0|0|6|6|28|X0|18D|0|0| |0| ||0| |NTG U-LIGHT FACIAL MOIST COMB/OIL 6X55G|2851200|CONS_LATAM_PA03||0||38880000| |2|FERT|2|40|40|||94608000|45|7|2|1|1.66|
			|93489|YES|NO|YES|NO|YES|F|0|0|6|6|28|X0|18D|0|0| |0| ||0| |NTG U-LIGHT FACIAL MOIST COMB/OIL 6X55G|2937600|CONS_LATAM_PE01|C|0||38880000| |2|FERT|2|40|40|||94608000|99|45|2|1|6.41|
			|93489|YES|NO|YES|NO|YES|F|0|0|1764|6|28|X0|18D|0|0| |0| ||0| |NTG U-LIGHT FACIAL MOIST COMB/OIL 6X55G|1036800|CONS_LATAM_CL01||0||38880000| |2|FERT|2|40|40|||94608000|99|45|2|1|10.7|
			|93489|YES|NO|YES|NO|YES|F|0|0|6|6|28|X0|18I|0|0| |0| ||0| |NTG U-LIGHT FACIAL MOIST COMB/OIL 6X55G|0|CONS_LATAM_CR01||0||38880000| |2|FERT|2|10|40|||94608000|45|7|2|1|1150.0|
			|93489|YES|NO|YES|NO|YES|F|0|0|6|6|WB|X0|18I|0|0| |0| ||0| |NTG U-LIGHT FACIAL MOIST COMB/OIL 6X55G|2332800|CONS_LATAM_DO01||0||38880000| |2|FERT|2||40|||94608000|45|7|2|1|102.9|
			|93489|YES|NO|YES|NO|YES|F|0|0|6|6|WB|X0|18D|0|0| |0| ||0| |NTG U-LIGHT FACIAL MOIST COMB/OIL 6X55G|0|CONS_LATAM_AR02||0||38880000| |2|FERT|2|10|40|||94608000|45|7|2|0|44.85|
			|93489|YES|NO|YES|NO|YES|F|0|0|6|6|WB|X0|18I|0|0| |0| ||0| |NTG U-LIGHT FACIAL MOIST COMB/OIL 6X55G|2937600|CONS_LATAM_GT02||0||38880000| |2|FERT| ||40|||94608000|99|7|2|1|13.16|
			|93489|YES|NO|YES|NO|YES|F|0|0|120|120|WB|X0|18D|0|0| |0| ||0| |NTG U-LIGHT FACIAL MOIST COMB/OIL 6X55G|432000|CONS_LATAM_BR25|C|0||38880000| |2|FERT|2|40|40|||94608000|45|7|2|0|6.17|
			|93489|YES|NO|YES|NO|YES|F|0|0|120|120|WB|X0|18D|0|0| |0| ||0| |NTG U-LIGHT FACIAL MOIST COMB/OIL 6X55G|172800|CONS_LATAM_BR16|C|0||23328000| |2|FERT|2|40|40|||94608000|45|7|2|0|6.23|
			|93489|YES|NO|YES|NO|YES|F|0|0|6|6|28|X0|18I|0|0| |0| ||0| |NTG U-LIGHT FACIAL MOIST COMB/OIL 6X55G|3542400|CONS_LATAM_PA01||0||38880000| |2|FERT| |10|40|||94608000|45|7|2|1|1.84|
			|93489|YES|NO|YES|NO|YES|F|0|0|6|6|28|X0|18I|0|0| |0| ||0| |NTG U-LIGHT FACIAL MOIST COMB/OIL 6X55G|3542400|CONS_LATAM_CR02||0||38880000| |2|FERT| ||40|||94608000|45|7|2|1|999.47|
			|93489|YES|NO|YES|NO|YES|F|0|0|120|120|WB|X0|18D|1|0| |0| ||0| |NTG U-LIGHT FACIAL MOIST COMB/OIL 6X55G|604800|CONS_LATAM_BR07|C|0||38880000| |2|FERT|2|40|40|||94608000|45|7|2|0|6.17|
			|93489|YES|NO|YES|NO|YES|F|0|0|6|6|28|X0|14D|0|0|J99|0| ||0| |NTG U-LIGHT FACIAL MOIST COMB/OIL 6X55G|691200|CONS_LATAM_UY01|C|0||38880000| |2|FERT|2|40|40|||94608000|45|7|2|6|51.82|
			|93489|YES|NO|YES|NO|YES|F|0|0|120|120|WB|X0|18D|0|0| |0| ||0| |NTG U-LIGHT FACIAL MOIST COMB/OIL 6X55G|172800|CONS_LATAM_BR19|C|0||38880000| |2|FERT|2|40|40|||94608000|45|7|2|0|12.21|
			|530217|YES|NO|YES|NO|YES|F|0|0|6|6|WB|X0|13R|0|0| |0| ||0| |TARGETED BIONIC EYE CREAM PLUS 15G|172800|CONS_LATAM_BR19|A|0||15552000| |2|FERT|2|40|40|||94608000|45|7|2|0|200.0|
			|530217|YES|NO|YES|NO|YES|F|0|0|6|6|WB|X0|13R|0|0| |0| ||0| |TARGETED BIONIC EYE CREAM PLUS 15G|604800|CONS_LATAM_BR07|A|0||15552000| |2|FERT|2|40|40|||94608000|45|7|2|0|20.73|
			|530217|YES|NO|YES|NO|YES|F|0|0|6|6|WB|X0|13R|0|0| |0| ||0| |TARGETED BIONIC EYE CREAM PLUS 15G|345600|CONS_LATAM_BR25|A|0||15552000| |2|FERT|2|40|40|||94608000|45|7|2|0|20.73|
			|530217|YES|NO|YES|NO|YES|F|0|0|6|6|WB|X0|13R|0|0| |0| ||0| |TARGETED BIONIC EYE CREAM PLUS 15G|172800|CONS_LATAM_BR16|A|0||23328000| |2|FERT|2|40|40|||94608000|45|7|2|0|20.73|
			|529523|YES|NO|YES|NO|YES|F|0|0|6|6|28|X0|CPS|0|0| |0| ||0| |PACK GOTAS DE BRILLO SH400+SPRAY200|0|CONS_LATAM_PA01|A|0||15552000| |2|FERT| |10|40|||63072000|99|35|2|0|2.62|
			|529523|YES|NO|YES|NO|YES|F|0|0|6|6|28|X0|CPS|0|0| |0| ||0| |PACK GOTAS DE BRILLO SH400+SPRAY200|0|CONS_LATAM_CR02|A|0||15552000| |2|FERT| ||40|||63072000|99|7|2|0|1426.0|
			|529523|YES|NO|YES|NO|YES|F|0|0|6|6|28|X0|CPS|0|0| |0| ||0| |PACK GOTAS DE BRILLO SH400+SPRAY200|2332800|CONS_LATAM_GT02|A|0||15552000| |2|FERT| ||40|||63072000|99|7|2|0|18.82|
			#|89390|YES|NO|YES|NO|YES|F|0|0|5616|5616|28|X0|14R|0|0| |864000| ||0| |NEUT JABON ACNE 24X99g|4147200|CONS_LATAM_V_0000050161|A|0||31536000| |2|FERT|2||40|||63072000|99|45|2|1|3.36|
			|89390|YES|NO|YES|NO|YES|F|0|0|5616|5616|28|X0|14R|0|0| |864000| ||0| |NEUT JABON ACNE 24X99g|4147200|CONS_LATAM_PE01|A|0||31536000| |2|FERT|2||40|||63072000|99|45|2|1|3.36|
			#|441441|YES|NO|YES|NO|YES|F|0|0|12|12|WB|X0|14R|0|0| |0| ||0| |DEEP CLEAN SCRUB 4.2OZ 124ML|1728000|CONS_LATAM_V_0000050161||0||15552000| |2|FERT| ||40|||63072000|99|45|2|1|741.98|
			|441441|YES|NO|YES|NO|YES|F|0|0|12|12|WB|X0|14R|0|0| |0| ||0| |DEEP CLEAN SCRUB 4.2OZ 124ML|1728000|CONS_LATAM_CR02||0||15552000| |2|FERT| ||40|||63072000|99|45|2|1|741.98|
			|441441|YES|NO|YES|NO|YES|F|0|0|12|12|WB|X0|14R|0|0| |0| ||0| |DEEP CLEAN SCRUB 4.2OZ 124ML|0|CONS_LATAM_GT01||0||15552000| |2|FERT| |10|40|||63072000|99|7|2|0|11.47|
			|441441|YES|NO|YES|NO|YES|F|0|0|12|12|WB|X0|14R|0|0| |0| ||0| |DEEP CLEAN SCRUB 4.2OZ 124ML|0|CONS_LATAM_CR01||0||15552000| |2|FERT| |10|40|||63072000|99|7|2|0|856.75|
			|441441|YES|NO|YES|NO|YES|F|0|0|12|12|WB|X0|14R|0|0| |0| ||0| |DEEP CLEAN SCRUB 4.2OZ 124ML|2332800|CONS_LATAM_DO01||0||15552000| |2|FERT| ||40|||63072000|99|45|2|1|77.91|
			#|441441|YES|NO|YES|NO|YES|F|0|0|12|12|WB|X0|14R|0|0| |0| ||0| |DEEP CLEAN SCRUB 4.2OZ 124ML|2332800|CONS_LATAM_V_0000050161||0||15552000| |2|FERT| ||40|||63072000|99|45|2|1|77.91|
			|441441|YES|NO|YES|NO|YES|F|0|0|12|12|WB|X0|14R|0|0| |0| ||0| |DEEP CLEAN SCRUB 4.2OZ 124ML|0|CONS_LATAM_PA01||0||15552000| |2|FERT| |10|40|||63072000|99|35|2|0|1.32|
			|441441|YES|NO|YES|NO|YES|F|0|0|12|12|WB|X0|14R|0|0| |0| ||0| |DEEP CLEAN SCRUB 4.2OZ 124ML|2937600|CONS_LATAM_GT02||0||15552000| |2|FERT| ||40|||63072000|99|7|2|1|9.93|
			#|441441|YES|NO|YES|NO|YES|F|0|0|12|12|WB|X0|14R|0|0| |0| ||0| |DEEP CLEAN SCRUB 4.2OZ 124ML|2937600|CONS_LATAM_V_0000050161||0||15552000| |2|FERT| ||40|||63072000|99|7|2|1|9.93|
			#|441441|YES|NO|YES|NO|YES|F|0|0|12|12|WB|X0|14R|0|0| |0| ||0| |DEEP CLEAN SCRUB 4.2OZ 124ML|2937600|CONS_LATAM_V_0000050161||0||15552000| |2|FERT|2||40|||63072000|99|7|2|1|1.17|
			|441441|YES|NO|YES|NO|YES|F|0|0|12|12|WB|X0|14R|0|0| |0| ||0| |DEEP CLEAN SCRUB 4.2OZ 124ML|2937600|CONS_LATAM_PA03||0||15552000| |2|FERT|2||40|||63072000|99|7|2|1|1.17|
			|70538|YES|NO|YES|NO|YES|F|0|0|12|12|WB|X0|07R|0|0| |864000| ||0| |ST ESPECIAL ALAS AN WW 12X42|0|CONS_LATAM_CO02|A|0||15552000| |2|FERT|2|10|40|||63072000|45|7|2|0|2740.54|
			|70538|YES|NO|YES|NO|YES|F|0|0|12|12|WB|X0|07R|0|0| |864000| ||0| |ST ESPECIAL ALAS AN WW 12X42|2073600|CONS_LATAM_PE01|A|0||15552000| |2|FERT|2||40|||63072000|45|7|2|4|3.81|
			|529523|YES|NO|YES|NO|YES|F|0|0|6 |6 |28|X0|CPS|0|0| |0     | ||0| |PACK GOTAS DE BRILLO SH400+SPRAY200|1209600|CONS_LATAM_CR01|A|0||15552000| |2|FERT|2|30|40|||63072000|99|7|2|0|1534.3|
			|530217|YES|NO|YES|NO|YES|F|0|0|6 |6 |28|X0|13R|0|0| |0     | ||0| |TARGETED BIONIC EYE CREAM PLUS 15G |0      |CONS_LATAM_BR12|A|0||15552000| |2|FERT|2|30|40|||94608000|45|7|2|0|16.39|
			|529523|YES|NO|YES|NO|YES|F|0|0|6|6|28|X0|CPS|0|0| |0| ||0| |PACK GOTAS DE BRILLO SH400+SPRAY200|1209600|CONS_LATAM_DO01|A|0||15552000| |2|FERT|2|30|40|||63072000|99|7|2|0|116.96|
			|529523|YES|NO|YES|NO|YES|F|0|0|6|6|28|X0|CPS|0|0| |0| ||0| |PACK GOTAS DE BRILLO SH400+SPRAY200|0      |CONS_LATAM_GT01|A|0||15552000| |2|FERT|2|30|40|||63072000|99|7|2|0|18.66|
			|529523|YES|NO|YES|NO|YES|F|0|0|6|6|WB|X0|CPS|0|0| |0| ||0| |PACK GOTAS DE BRILLO SH400+SPRAY200|1209600|CONS_LATAM_PA03|A|0||15552000| |2|FERT|2|30|40|||63072000|99|7|2|0|2.48|
			#|89390|YES|NO|YES|NO|YES|F|0|0|5616|5616|28|X0|14R|0|0| |864000| ||0| |NEUT JABON ACNE 24X99g|4147200|CONS_LATAM_V_50161|A|0||31536000| |2|FERT|2||40|||63072000|99|45|2|1|3.36|
			#|441441|YES|NO|YES|NO|YES|F|0|0|12|12|WB|X0|14R|0|0| |0| ||0| |DEEP CLEAN SCRUB 4.2OZ 124ML|2332800|CONS_LATAM_V_50161||0||15552000| |2|FERT| ||40|||63072000|99|45|2|1|741.98|
			
      
    And I delete the test data

    And I will remove all data with region "/omp/gdm_product_location"

