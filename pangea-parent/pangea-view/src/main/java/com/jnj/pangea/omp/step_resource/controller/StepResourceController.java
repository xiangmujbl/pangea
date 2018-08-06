package com.jnj.pangea.omp.step_resource.controller;

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
public class StepResourceController implements IEventProcessor {

	private final String FAILREGION = "/plan/fail_data";

	@Override
	public List<ViewResultItem> process(List<RawDataEvent> list) {

		List<ViewResultItem> resultList = new ArrayList<>();

		list.stream()
				.filter(e -> !(StepResourceHook.filterraw(e.getValue())))
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
											.append("stepResourceId",
													data.get("stepResourceId"))
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
										.info("StepResourceController Exception occured. key = {}.",
												e.getKey());
								LogUtil.getCoreLog().info(
										"StepResourceController Exception:",
										exception);
							}

						});

		return resultList;
	}

	public boolean buildView(RawDataValue raw,
							 List<RawDataBuilder> rawDataBuilderList,
							 Map<String, RawDataBuilder> failMap) {

		Map map = raw.toMap();

		String srcSysCd = StringInner.getString(map, "srcSysCd");

		String matlNum = StringInner.getString(map, "matlNum");

		String plntCd = StringInner.getString(map, "plntCd");

		String rtngTypCd = StringInner.getString(map, "rtngTypCd");

		String rntgGrpCd = StringInner.getString(map, "rntgGrpCd");

		String rntgGrpCntrNbr = StringInner.getString(map, "rntgGrpCntrNbr");

		String rntgAddtnlCntrNbr = StringInner.getString(map,
				"rntgAddtnlCntrNbr");

		String matlRtngVrsnCntrNbr = StringInner.getString(map,
				"matlRtngVrsnCntrNbr");

		String valFromDt = StringInner.getString(map, "valFromDt");

		String chgNum = StringInner.getString(map, "chgNum");

		String delInd = StringInner.getString(map, "delInd");

		String crtDttm = StringInner.getString(map, "crtDttm");

		String chgDttm = StringInner.getString(map, "chgDttm");

		String matlRtngValid_To = StringInner
				.getString(map, "matlRtngValid_To");

		String steus = "";

		String charVal = "";

		String rtngGrpCntrNum = "";

		String prdntVrsnNum = StringInner.getString(map, "prdntVrsnNum");

		String wrkCtrNum = null;

		String rtngGrpCd = "";

		String rtngNdeNum = "";

		String wrkCntrCd = "";

		String operCd = "";

		List<Map.Entry<String, String>> retList1 = null;
		if (StringInner.isStringNotEmpty(srcSysCd)
				&& StringInner.isStringNotEmpty(matlNum)
				&& StringInner.isStringNotEmpty(plntCd)) {
			retList1 = X1(srcSysCd, matlNum, plntCd);
			if (retList1 != null && retList1.size() > 0) {
				for (Map.Entry<String, String> retList1Entry : retList1) {
					Map<String, Object> retList1Map = JsonObject.append(
							retList1Entry.getValue()).toMap();
					srcSysCd = StringInner.getString(retList1Map, "srcSysCd");
					rtngTypCd = StringInner.getString(retList1Map, "rtngTypCd");
					rtngGrpCd = StringInner.getString(retList1Map, "rtngGrpCd");
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
								rtngGrpCd = StringInner.getString(retList2Map,
										"rtngGrpCd");
								rtngTypCd = StringInner.getString(retList2Map,
										"rtngTypCd");
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
											operCd = StringInner.getString(
													retList3Map, "operCd");
											steus = StringInner.getString(
													retList3Map, "steus");

											RawDataBuilder builder = new RawDataBuilder();

											builder.put("active", "YES");
											builder.put("activeOPRERP", "YES");
											builder.put("activeSOPERP", "NO");
											builder.put("quantity", "0");
											builder.put("stepResourceType",
													"production");

											String minQuantity = null;

											if (String.valueOf(retList2Map
													.get("rtngNdeNum")) != null
													&& String
													.valueOf(retList1Map
															.get("rtngTypCd")) != null
													&& String.valueOf(map
													.get("srcSysCd")) != null
													&& String
													.valueOf(retList1Map
															.get("rtngGrpCd")) != null) {

												Map mapT7 = T7(
														String.valueOf(map
																.get("srcSysCd")),
														String.valueOf(retList1Map
																.get("rtngTypCd")),
														String.valueOf(retList1Map
																.get("rtngGrpCd")),
														String.valueOf(retList2Map
																.get("rtngNdeNum")));
												if (mapT7 != null) {
													charVal = StringInner
															.getString(mapT7,
																	"charVal");
													delInd = StringInner
															.getString(mapT7,
																	"delInd");
													if (mapT7 == null) {
														charVal = "";
													} else if (delInd
															.equals("X")) {
														charVal = "";
													}
												}
											}

											minQuantity = charVal;
											builder.put("minQuantity", charVal);

											String resourceId = null;

											Map X4Map = X4(srcSysCd, wrkCntrCd);
											if (X4Map != null) {
												srcSysCd = StringInner
														.getString(X4Map,
																"srcSysCd");
												wrkCtrNum = StringInner
														.getString(X4Map,
																"wrkCtrNum");
											}
											resourceId = StepResourceHook
													.setResourceIdT5(X4Map);
											if ("".equals(resourceId)
													|| resourceId == null
													|| "null"
													.equals(resourceId)
													|| "-".equals(resourceId)) {
												continue;
											}

											builder.put("resourceId",
													resourceId);

											String stepResourceId = null;

											stepResourceId = StepResourceHook
													.StepResourceId(map,
															retList1Map,
															retList3Map, X4Map,
															resourceId);

											if (resourceId == null) {

												continue;
											}

											builder.put("stepResourceId",
													stepResourceId);

											String operationId = null;

											operationId = StepResourceHook.C1(
													retList1Map, map,
													retList3Map);

											builder.put("operationId",
													operationId);

											String machineId = null;

											machineId = StepResourceHook
													.setT3MachineId(X4Map);

											if ("".equals(machineId)) {

												continue;
											}

											builder.put("machineId", machineId);

											rawDataBuilderList.add(builder);
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

	public List X1(String srcSysCd, String matlNum, String plntCd) {

		ADFCriteria adfCriteria1 = QueryHelper.buildCriteria("srcSysCd").is(
				srcSysCd);
		ADFCriteria adfCriteria2 = QueryHelper.buildCriteria("matlNum").is(
				matlNum);
		ADFCriteria adfCriteria3 = QueryHelper.buildCriteria("plntCd").is(
				plntCd);
		ADFCriteria groupCriteria19 = adfCriteria3.and(adfCriteria2).and(
				adfCriteria1);

		ADFCriteria adfCriteria = groupCriteria19;
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

		ADFCriteria adfCriteria4 = QueryHelper.buildCriteria("srcSysCd").is(
				srcSysCd);
		ADFCriteria adfCriteria5 = QueryHelper.buildCriteria("rtngGrpCd").is(
				rtngGrpCd);
		ADFCriteria adfCriteria6 = QueryHelper.buildCriteria("rtngTypCd").is(
				rtngTypCd);
		ADFCriteria adfCriteria7 = QueryHelper.buildCriteria("rtngGrpCntrNbr")
				.is(rtngGrpCntrNum);
		ADFCriteria groupCriteria20 = adfCriteria7.and(adfCriteria6)
				.and(adfCriteria5).and(adfCriteria4);

		ADFCriteria adfCriteria = groupCriteria20;
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

		ADFCriteria adfCriteria8 = QueryHelper.buildCriteria("srcSysCd").is(
				srcSysCd);
		ADFCriteria adfCriteria9 = QueryHelper.buildCriteria("rtngTypCd").is(
				rtngTypCd);
		ADFCriteria adfCriteria10 = QueryHelper.buildCriteria("rtngGrpCd").is(
				rtngGrpCd);
		ADFCriteria adfCriteria11 = QueryHelper.buildCriteria("rtngItmNum").is(
				rtngItmNum);
		ADFCriteria groupCriteria21 = adfCriteria11.and(adfCriteria10)
				.and(adfCriteria9).and(adfCriteria8);

		ADFCriteria adfCriteria = groupCriteria21;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/edm/mfg_rtng_itm", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			return retList;
		}

		return new ArrayList<>();

	}

	public Map X4(String srcSysCd, String wrkCtrCd) {

		ADFCriteria adfCriteria12 = QueryHelper.buildCriteria("srcSysCd").is(
				srcSysCd);
		ADFCriteria adfCriteria13 = QueryHelper.buildCriteria("wrkCtrNum").is(
				wrkCtrCd);
		ADFCriteria groupCriteria22 = adfCriteria13.and(adfCriteria12);

		ADFCriteria adfCriteria = groupCriteria22;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/edm/wrk_ctr", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			Map.Entry<String, String> entry = retList.get(0);
			Map<String, Object> map = JsonObject.append(entry.getValue())
					.toMap();
			return map;
		}

		return null;

	}

	public Map T7(String srcSysCd, String rtgTypeCd, String rtgGrpCd,
				  String rtgNodeNum) {

		ADFCriteria adfCriteria14 = QueryHelper.buildCriteria("srcSysCd").is(
				srcSysCd);
		ADFCriteria adfCriteria15 = QueryHelper.buildCriteria("rtgTypeCd").is(
				rtgTypeCd);
		ADFCriteria adfCriteria16 = QueryHelper.buildCriteria("rtgGrpCd").is(
				rtgGrpCd);
		ADFCriteria adfCriteria17 = QueryHelper.buildCriteria("rtgNodeNum").is(
				rtgNodeNum);
		ADFCriteria adfCriteria18 = QueryHelper.buildCriteria("charCd").is(
				"EFICIENCIA");
		ADFCriteria groupCriteria23 = adfCriteria18.and(adfCriteria17)
				.and(adfCriteria16).and(adfCriteria15).and(adfCriteria14);

		ADFCriteria adfCriteria = groupCriteria23;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/edm/mfg_rtg_parm", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			Map.Entry<String, String> entry = retList.get(0);
			Map<String, Object> map = JsonObject.append(entry.getValue())
					.toMap();
			return map;
		}

		return null;

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
