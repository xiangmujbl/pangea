package com.jnj.pangea.omp.plo_operation.controller;

import com.jnj.adf.client.api.JsonObject;
import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.curation.actors.remote.CurationRawDataHelper;
import com.jnj.adf.curation.logic.IEventProcessor;
import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.adf.grid.data.raw.RawDataBuilder;
import com.jnj.adf.grid.utils.JsonUtils;
import com.jnj.adf.grid.view.common.AdfViewHelper;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.hook.PloOperationFromErpHook;
import org.apache.commons.lang3.StringUtils;
import com.jnj.adf.client.api.ADFCriteria;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.inner.DateInner;
import com.jnj.inner.StringInner;

import java.math.*;
import java.text.*;
import java.util.*;
import java.time.*;
import java.io.*;
import java.nio.*;

@SuppressWarnings("unchecked")
public class OMPPloOperationFromERPController implements IEventProcessor {

	private final String FAILREGION = "/plan/fail_data";

	@Override
	public List<ViewResultItem> process(List<RawDataEvent> list) {

		List<ViewResultItem> resultList = new ArrayList<>();

		list.stream()
				.filter(e -> !(PloOperationFromErpHook.filterraw(e.getValue())))
				.forEach(
						e -> {
							try {
								List<RawDataBuilder> rawDataBuilderList = new ArrayList<>();
								Map<String, RawDataBuilder> failMap = new HashMap<>();
								boolean isOk = buildView(e.getValue(),
										rawDataBuilderList, failMap);

								rawDataBuilderList.forEach(rawDataBuilder -> {
									Map<String, Object> data = (Map<String, Object>) rawDataBuilder
											.toRawData();
									String viewKey = JsonObject
											.create()
											.append("ploOperationFromErpId",
													data.get("ploOperationFromErpId"))
											.toJson();

									ViewResultItem viewRaw = ViewResultBuilder
											.newResultItem(viewKey, data);
									resultList.add(viewRaw);
								});

								failMap.forEach((key, value) -> {
									ViewResultItem viewRawFail = ViewResultBuilder
											.newResultItem(FAILREGION, key,
													(Map<String, Object>) value
															.toRawData());
									resultList.add(viewRawFail);
								});

							} catch (Exception exception) {
								LogUtil.getCoreLog()
										.info("OMPPloOperationFromERPController Exception occured. key = {}.",
												e.getKey());
								LogUtil.getCoreLog()
										.info("OMPPloOperationFromERPController Exception:",
												exception);
							}

						});

		return resultList;
	}

	public boolean buildView(RawDataValue raw,
			List<RawDataBuilder> rawDataBuilderList,
			Map<String, RawDataBuilder> failMap) {

		Map map = raw.toMap();

		String plntCd = StringInner.getString(map, "plntCd");

		String srcSysCd = StringInner.getString(map, "srcSysCd");

		String firmingInd = StringInner.getString(map, "firmingInd");

		String fxScrapQty = StringInner.getString(map, "fxScrapQty");

		String grDaysLeadQty = StringInner.getString(map, "grDaysLeadQty");

		String matlNum = StringInner.getString(map, "matlNum");

		String mfgPlanOrdrDocId = StringInner
				.getString(map, "mfgPlanOrdrDocId");

		String mrpCtlId = StringInner.getString(map, "mrpCtlId");

		String planOrdrEndDt = StringInner.getString(map, "planOrdrEndDt");

		String planOrdrEndTm = StringInner.getString(map, "planOrdrEndTm");

		String planOrdrQty = StringInner.getString(map, "planOrdrQty");

		String planOrdrStrtDt = StringInner.getString(map, "planOrdrStrtDt");

		String planOrdrTypeCd = StringInner.getString(map, "planOrdrTypeCd");

		String planPlntCd = StringInner.getString(map, "planPlntCd");

		String plngScnroCd = StringInner.getString(map, "plngScnroCd");

		String prcmtTypeCd = StringInner.getString(map, "prcmtTypeCd");

		String prdtnFnshdDt = StringInner.getString(map, "prdtnFnshdDt");

		String prdtnStrtDt = StringInner.getString(map, "prdtnStrtDt");

		String prdtnStrtTm = StringInner.getString(map, "prdtnStrtTm");

		String prdtnVers = StringInner.getString(map, "prdtnVers");

		String prdtnVersNum = StringInner.getString(map, "prdtnVersNum");

		String reqQty = StringInner.getString(map, "reqQty");

		String sLocCd = StringInner.getString(map, "sLocCd");

		String splPrcmtTypeCd = StringInner.getString(map, "splPrcmtTypeCd");

		String uomCd = StringInner.getString(map, "uomCd");

		String rtngGrpCd = "";

		String rtngTypCd = "";

		String rtngGrpCntrNum = "";

		String rtngNdeNum = "";

		String wrkCntrCd = "";

		String act1UomCd = "";

		String act2UomCd = "";

		String act3UomCd = "";

		String act1Qty = "";

		String act2Qty = "";

		String act3Qty = "";

		String operNum = "";

		List<Map.Entry<String, String>> retList1 = null;
		if (StringInner.isStringNotEmpty(srcSysCd)
				&& StringInner.isStringNotEmpty(matlNum)
				&& StringInner.isStringNotEmpty(plntCd)
				&& StringInner.isStringNotEmpty(prdtnVers)) {
			retList1 = X1(srcSysCd, matlNum, plntCd, prdtnVers);
			if (retList1 != null && retList1.size() > 0) {
				for (Map.Entry<String, String> retList1Entry : retList1) {
					Map<String, Object> retList1Map = JsonObject.append(
							retList1Entry.getValue()).toMap();
					srcSysCd = StringInner.getString(retList1Map, "srcSysCd");
					rtngGrpCd = StringInner.getString(retList1Map, "rtngGrpCd");
					rtngTypCd = StringInner.getString(retList1Map, "rtngTypCd");
					rtngGrpCntrNum = StringInner.getString(retList1Map,
							"rtngGrpCntrNum");

					List<Map.Entry<String, String>> retList2 = null;
					if (StringInner.isStringNotEmpty(srcSysCd)
							&& StringInner.isStringNotEmpty(rtngGrpCd)
							&& StringInner.isStringNotEmpty(rtngTypCd)
							&& StringInner.isStringNotEmpty(rtngGrpCntrNum)) {
						retList2 = X2(srcSysCd, rtngGrpCd, rtngTypCd,
								rtngGrpCntrNum);
						if (retList2 != null && retList2.size() > 0) {
							for (Map.Entry<String, String> retList2Entry : retList2) {
								Map<String, Object> retList2Map = JsonObject
										.append(retList2Entry.getValue())
										.toMap();
								srcSysCd = StringInner.getString(retList2Map,
										"srcSysCd");
								rtngTypCd = StringInner.getString(retList2Map,
										"rtngTypCd");
								rtngGrpCd = StringInner.getString(retList2Map,
										"rtngGrpCd");
								rtngNdeNum = StringInner.getString(retList2Map,
										"rtngNdeNum");

								List<Map.Entry<String, String>> retList3 = null;
								if (StringInner.isStringNotEmpty(srcSysCd)
										&& StringInner
												.isStringNotEmpty(rtngTypCd)
										&& StringInner
												.isStringNotEmpty(rtngGrpCd)
										&& StringInner
												.isStringNotEmpty(rtngNdeNum)) {
									retList3 = X3(srcSysCd, rtngTypCd,
											rtngGrpCd, rtngNdeNum);
									if (retList3 != null && retList3.size() > 0) {
										for (Map.Entry<String, String> retList3Entry : retList3) {
											Map<String, Object> retList3Map = JsonObject
													.append(retList3Entry
															.getValue())
													.toMap();
											srcSysCd = StringInner.getString(
													retList3Map, "srcSysCd");
											wrkCntrCd = StringInner.getString(
													retList3Map, "wrkCntrCd");
											act1UomCd = StringInner.getString(
													retList3Map, "act1UomCd");
											act2UomCd = StringInner.getString(
													retList3Map, "act2UomCd");
											act3UomCd = StringInner.getString(
													retList3Map, "act3UomCd");
											act1Qty = StringInner.getString(
													retList3Map, "act1Qty");
											act2Qty = StringInner.getString(
													retList3Map, "act2Qty");
											act3Qty = StringInner.getString(
													retList3Map, "act3Qty");
											operNum = StringInner.getString(
													retList3Map, "operNum");

											List<Map.Entry<String, String>> retList4 = null;
											if (StringInner
													.isStringNotEmpty(srcSysCd)
													&& StringInner
															.isStringNotEmpty(wrkCntrCd)) {
												retList4 = X4(srcSysCd,
														wrkCntrCd);
												if (retList4 != null
														&& retList4.size() > 0) {
													for (Map.Entry<String, String> retList4Entry : retList4) {
														Map<String, Object> retList4Map = JsonObject
																.append(retList4Entry
																		.getValue())
																.toMap();

														RawDataBuilder builder = new RawDataBuilder();

														builder.put("vge04", "");
														builder.put("vge05", "");
														builder.put("vge06", "");
														builder.put("vgw04", "");
														builder.put("vgw05", "");
														builder.put("vgw06", "");

														String vge01 = null;

														vge01 = act1UomCd;
														builder.put("vge01",
																vge01);

														String vge02 = null;

														vge02 = act2UomCd;
														builder.put("vge02",
																vge02);

														String vge03 = null;

														vge03 = act3UomCd;
														builder.put("vge03",
																vge03);

														String vgw01 = null;

														vgw01 = act1Qty;
														builder.put("vgw01",
																vgw01);

														String vgw02 = null;

														vgw02 = act2Qty;
														builder.put("vgw02",
																vgw02);

														String vgw03 = null;

														vgw03 = act3Qty;
														builder.put("vgw03",
																vgw03);

														String ploOperationFromErpId = null;

														ploOperationFromErpId = StringInner
																.join(srcSysCd,
																		"/",
																		String.valueOf(Long
																				.valueOf(mfgPlanOrdrDocId)),
																		"/",
																		operNum);
														builder.put(
																"ploOperationFromErpId",
																ploOperationFromErpId);

														String erpPloid = null;

														erpPloid = StringInner
																.join(srcSysCd,
																		"/",
																		String.valueOf(Long
																				.valueOf(mfgPlanOrdrDocId)));
														builder.put("erpPloid",
																erpPloid);

														String arbid = null;

														arbid = StringInner
																.join(srcSysCd,
																		"/",
																		wrkCntrCd);
														builder.put("arbid",
																arbid);

														String vornr = null;

														vornr = operNum;
														builder.put("vornr",
																vornr);

														rawDataBuilderList
																.add(builder);
													}
												}
											}

										}
									}
								}

							}
						}
					}

				}
			}
		}

		return true;
	}

	public List X1(String srcSysCd, String matlNum, String plntCd,
			String prdtnVersnNum) {

		ADFCriteria adfCriteria1 = QueryHelper.buildCriteria("srcSysCd").is(
				srcSysCd);
		ADFCriteria adfCriteria2 = QueryHelper.buildCriteria("matlNum").is(
				matlNum);
		ADFCriteria adfCriteria3 = QueryHelper.buildCriteria("plntCd").is(
				plntCd);
		ADFCriteria adfCriteria4 = QueryHelper.buildCriteria("prdntVrsnNum")
				.is(prdtnVersnNum);
		ADFCriteria groupCriteria15 = adfCriteria4.and(adfCriteria3)
				.and(adfCriteria2).and(adfCriteria1);

		ADFCriteria adfCriteria = groupCriteria15;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/edm/matl_prod_versn", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			return retList;
		}

		return new ArrayList<>();

	}

	public List X2(String srcSysCd, String rtngGrpCd, String rtngTypCd,
			String rtngGrpCntrNum) {

		ADFCriteria adfCriteria5 = QueryHelper.buildCriteria("srcSysCd").is(
				srcSysCd);
		ADFCriteria adfCriteria6 = QueryHelper.buildCriteria("rtngGrpCd").is(
				rtngGrpCd);
		ADFCriteria adfCriteria7 = QueryHelper.buildCriteria("rtngTypCd").is(
				rtngTypCd);
		ADFCriteria adfCriteria8 = QueryHelper.buildCriteria("rtngGrpCntrNbr")
				.is(rtngGrpCntrNum);
		ADFCriteria groupCriteria16 = adfCriteria8.and(adfCriteria7)
				.and(adfCriteria6).and(adfCriteria5);

		ADFCriteria adfCriteria = groupCriteria16;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/edm/mfg_rtng_itm_nde", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			return retList;
		}

		return new ArrayList<>();

	}

	public List X3(String srcSysCd, String rtngTypCd, String rtngGrpCd,
			String rtngItmNum) {

		ADFCriteria adfCriteria9 = QueryHelper.buildCriteria("srcSysCd").is(
				srcSysCd);
		ADFCriteria adfCriteria10 = QueryHelper.buildCriteria("rtngTypCd").is(
				rtngTypCd);
		ADFCriteria adfCriteria11 = QueryHelper.buildCriteria("rtngGrpCd").is(
				rtngGrpCd);
		ADFCriteria adfCriteria12 = QueryHelper.buildCriteria("rtngItmNum").is(
				rtngItmNum);
		ADFCriteria groupCriteria17 = adfCriteria12.and(adfCriteria11)
				.and(adfCriteria10).and(adfCriteria9);

		ADFCriteria adfCriteria = groupCriteria17;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/edm/mfg_rtng_itm", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			return retList;
		}

		return new ArrayList<>();

	}

	public List X4(String srcSysCd, String wrkCtrCd) {

		ADFCriteria adfCriteria13 = QueryHelper.buildCriteria("srcSysCd").is(
				srcSysCd);
		ADFCriteria adfCriteria14 = QueryHelper.buildCriteria("wrkCtrNum").is(
				wrkCtrCd);
		ADFCriteria groupCriteria18 = adfCriteria14.and(adfCriteria13);

		ADFCriteria adfCriteria = groupCriteria18;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/edm/wrk_ctr", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			return retList;
		}

		return new ArrayList<>();

	}

	private void writeFailDataToRegion(Map<String, RawDataBuilder> failMap,
			String functionalArea, String interfaceID, String errorCode,
			String errorValue, String sourceSystem, String key1, String key2,
			String key3, String key4, String key5, String businessArea) {
		String keyJson = JsonObject.create()
				.append("functionalArea", functionalArea)
				.append("interfaceID", interfaceID)
				.append("errorCode", errorCode)
				.append("sourceSystem", sourceSystem).append("key1", key1)
				.append("key2", key2).append("key3", key3).append("key4", key4)
				.append("key5", key5).toJson();

		RawDataBuilder failBuilder = new RawDataBuilder();
		failBuilder.put("functionalArea", functionalArea);
		failBuilder.put("interfaceID", interfaceID);
		failBuilder.put("errorCode", errorCode);
		failBuilder.put("sourceSystem", sourceSystem);
		failBuilder.put("key1", key1);
		failBuilder.put("key2", key2);
		failBuilder.put("key3", key3);
		failBuilder.put("key4", key4);
		failBuilder.put("key5", key5);
		failBuilder.put("businessArea", businessArea);
		failBuilder.put("errorValue", errorValue);

		failMap.put(keyJson, failBuilder);
	}

}
