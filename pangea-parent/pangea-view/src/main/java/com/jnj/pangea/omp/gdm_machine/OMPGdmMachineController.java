package com.jnj.pangea.omp.gdm_machine;

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
public class OMPGdmMachineController implements IEventProcessor {

	private final String FAILREGION = "";

	@Override
	public List<ViewResultItem> process(List<RawDataEvent> list) {

		List<ViewResultItem> resultList = new ArrayList<>();

		list.stream()
				.filter(e -> !(OMPGdmMachineHook.check(e.getValue())))
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
											.append("machineId",
													data.get("machineId"))
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
										.info("OMPGdmMachineController Exception occured. key = {}.",
												e.getKey());
								LogUtil.getCoreLog().info(
										"OMPGdmMachineController Exception:",
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

		String plntCd = StringInner.getString(map, "plntCd");

		String wrkCtrCd = StringInner.getString(map, "wrkCtrCd");

		String wrkCtrDesc = StringInner.getString(map, "wrkCtrDesc");

		String capyCatCd = "001";

		String capyNum = StringInner.getString(map, "capyNum");

		String finiteSchdlngInd = StringInner
				.getString(map, "finiteSchdlngInd");

		String wrkCtrNum = StringInner.getString(map, "wrkCtrNum");

		String wrkCtrTypeCd = StringInner.getString(map, "wrkCtrTypeCd");

		String srcSysCd_ctr = "";

		String plntCd_ctr = "";

		String parntWrkCtrNum = "";

		String parntWrkCtrTypeCd = "";

		srcSysCd_ctr = srcSysCd;
		plntCd_ctr = plntCd;

		List<Map.Entry<String, String>> wrkCtrCapyList = null;
		if (StringInner.isStringNotEmpty(srcSysCd)
				&& StringInner.isStringNotEmpty(wrkCtrTypeCd)
				&& StringInner.isStringNotEmpty(wrkCtrNum)) {
			wrkCtrCapyList = ruleJ1_3(srcSysCd, wrkCtrTypeCd, wrkCtrNum);
			if (wrkCtrCapyList != null && wrkCtrCapyList.size() > 0) {
				for (Map.Entry<String, String> wrkCtrCapyListEntry : wrkCtrCapyList) {
					Map<String, Object> wrkCtrCapyListMap = JsonObject.append(
							wrkCtrCapyListEntry.getValue()).toMap();
					srcSysCd = StringInner.getString(wrkCtrCapyListMap,
							"srcSysCd");
					capyNum = StringInner.getString(wrkCtrCapyListMap,
							"capyNum");

					List<Map.Entry<String, String>> capyHdrList = null;
					if (StringInner.isStringNotEmpty(srcSysCd)
							&& StringInner.isStringNotEmpty(capyNum)
							&& StringInner.isStringNotEmpty(capyCatCd)) {
						capyHdrList = ruleJ1_2(srcSysCd, capyNum, capyCatCd);
						if (capyHdrList != null && capyHdrList.size() > 0) {
							for (Map.Entry<String, String> capyHdrListEntry : capyHdrList) {
								Map<String, Object> capyHdrListMap = JsonObject
										.append(capyHdrListEntry.getValue())
										.toMap();
								srcSysCd = StringInner.getString(
										capyHdrListMap, "srcSysCd");
								finiteSchdlngInd = StringInner.getString(
										capyHdrListMap, "finiteSchdlngInd");

								RawDataBuilder builder = new RawDataBuilder();

								builder.put("active", "YES");
								builder.put("activeOPRERP", "YES");
								builder.put("activeSOPERP", "NO");
								//building
								String building = StringInner.getString(map,
										"wrkCtrLocCd");
								builder.put("building", building);
								builder.put("machineTypeId", "Production");

								String machineId = null;

								if (StringInner.isStringNotEmpty(srcSysCd_ctr)
										&& StringInner
												.isStringNotEmpty(plntCd_ctr)
										&& StringInner
												.isStringNotEmpty(wrkCtrCd)) {

									machineId = StringInner.join(srcSysCd_ctr,
											"_", plntCd_ctr, "/", wrkCtrCd);
								}

								builder.put("machineId", machineId);

								String description = null;

								if (StringInner.isStringNotEmpty(wrkCtrDesc)) {

									description = OMPGdmMachineHook
											.getDescription(wrkCtrDesc);
								}

								builder.put("description", description);

								String locationId = null;

								if (StringInner.isStringNotEmpty(srcSysCd_ctr)
										&& StringInner
												.isStringNotEmpty(plntCd_ctr)) {

									locationId = StringInner.join(srcSysCd_ctr,
											"_", plntCd_ctr);
								}

								builder.put("locationId", locationId);

								String machineCapacity = null;

								if (finiteSchdlngInd.equals("X")) {

									machineCapacity = "finite";
								}

								else {

									machineCapacity = "infinite";
								}
								builder.put("machineCapacity", machineCapacity);

								String parentMachineId = null;

								Map map1 = ruleT6_1(srcSysCd, wrkCtrNum,
										wrkCtrTypeCd);
								if (map1 != null) {
									parntWrkCtrNum = StringInner.getString(
											map1, "parntWrkCtrNum");
									parntWrkCtrTypeCd = StringInner.getString(
											map1, "parntWrkCtrTypeCd");
									srcSysCd = StringInner.getString(map1,
											"srcSysCd");
								}
								if (StringInner
										.isStringNotEmpty(parntWrkCtrNum)
										&& StringInner
												.isStringNotEmpty(parntWrkCtrTypeCd)) {
									Map map2 = ruleT6_3(srcSysCd,
											parntWrkCtrNum, parntWrkCtrTypeCd);
									if (map2 != null) {
										wrkCtrCd = StringInner.getString(map2,
												"wrkCtrCd");
										if (StringInner
												.isStringNotEmpty(wrkCtrCd)) {
											parentMachineId = StringInner.join(
													srcSysCd, "_", plntCd_ctr,
													"/", wrkCtrCd);
										} else {
											parentMachineId = "";
										}
									}
								} else {
									parentMachineId = "";
								}

								builder.put("parentMachineId", parentMachineId);

								rawDataBuilderList.add(builder);
							}
						}
					}

				}
			}
		}

		return true;
	}

	public Map ruleJ1_1(String srcSysCd) {

		ADFCriteria adfCriteria1 = QueryHelper.buildCriteria("sourceSystem")
				.is(srcSysCd);
		ADFCriteria adfCriteria2 = QueryHelper.buildCriteria("dataObject").is(
				"SEND_TO_OMP");
		ADFCriteria groupCriteria17 = adfCriteria2.and(adfCriteria1);

		ADFCriteria adfCriteria = groupCriteria17;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/plan/cns_plan_parameter", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			Map.Entry<String, String> entry = retList.get(0);
			Map<String, Object> map = JsonObject.append(entry.getValue())
					.toMap();
			return map;
		}

		return null;

	}

	public List ruleJ1_2(String srcSysCd, String capyNum, String capyCatCd) {

		ADFCriteria adfCriteria3 = QueryHelper.buildCriteria("srcSysCd").is(
				srcSysCd);
		ADFCriteria adfCriteria4 = QueryHelper.buildCriteria("capyNum").is(
				capyNum);
		ADFCriteria adfCriteria5 = QueryHelper.buildCriteria("capyCatCd").is(
				capyCatCd);
		ADFCriteria groupCriteria18 = adfCriteria5.and(adfCriteria4).and(
				adfCriteria3);

		ADFCriteria adfCriteria = groupCriteria18;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/edm/capy_hdr", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			return retList;
		}

		return new ArrayList<>();

	}

	public List ruleJ1_3(String srcSysCd, String wrkCtrTypeCd, String wrkCtrNum) {

		ADFCriteria adfCriteria6 = QueryHelper.buildCriteria("srcSysCd").is(
				srcSysCd);
		ADFCriteria adfCriteria7 = QueryHelper.buildCriteria("wrkCtrTypeCd")
				.is(wrkCtrTypeCd);
		ADFCriteria adfCriteria8 = QueryHelper.buildCriteria("wrkCtrNum").is(
				wrkCtrNum);
		ADFCriteria groupCriteria19 = adfCriteria8.and(adfCriteria7).and(
				adfCriteria6);

		ADFCriteria adfCriteria = groupCriteria19;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/edm/wrk_ctr_capy", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			return retList;
		}

		return new ArrayList<>();

	}

	public Map ruleT6_1(String srcSysCd, String wrkCtrNum, String wrkCtrTypeCd) {

		ADFCriteria adfCriteria9 = QueryHelper.buildCriteria("srcSysCd").is(
				srcSysCd);
		ADFCriteria adfCriteria10 = QueryHelper.buildCriteria("wrkCtrNum").is(
				wrkCtrNum);
		ADFCriteria adfCriteria11 = QueryHelper.buildCriteria("wrkCtrTypeCd")
				.is(wrkCtrTypeCd);
		ADFCriteria groupCriteria20 = adfCriteria11.and(adfCriteria10).and(
				adfCriteria9);

		ADFCriteria adfCriteria = groupCriteria20;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/edm/wrk_ctr_hier", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			Map.Entry<String, String> entry = retList.get(0);
			Map<String, Object> map = JsonObject.append(entry.getValue())
					.toMap();
			return map;
		}

		return null;

	}

	public Map ruleT6_2(String wrkCtrCd, String wrkCtrTypeCd) {

		ADFCriteria adfCriteria12 = QueryHelper.buildCriteria("parntWrkCtrNum")
				.is(wrkCtrCd);
		ADFCriteria adfCriteria13 = QueryHelper.buildCriteria(
				"parntWrkCtrTypeCd").is(wrkCtrTypeCd);
		ADFCriteria groupCriteria21 = adfCriteria13.and(adfCriteria12);

		ADFCriteria adfCriteria = groupCriteria21;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/edm/wrk_ctr_hier", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			Map.Entry<String, String> entry = retList.get(0);
			Map<String, Object> map = JsonObject.append(entry.getValue())
					.toMap();
			return map;
		}

		return null;

	}

	public Map ruleT6_3(String srcSysCd, String parntWrkCtrNum,
			String parntWrkCtrTypeCd) {

		ADFCriteria adfCriteria14 = QueryHelper.buildCriteria("srcSysCd").is(
				srcSysCd);
		ADFCriteria adfCriteria15 = QueryHelper.buildCriteria("wrkCtrNum").is(
				parntWrkCtrNum);
		ADFCriteria adfCriteria16 = QueryHelper.buildCriteria("wrkCtrTypeCd")
				.is(parntWrkCtrTypeCd);
		ADFCriteria groupCriteria22 = adfCriteria16.and(adfCriteria15).and(
				adfCriteria14);

		ADFCriteria adfCriteria = groupCriteria22;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/edm/wrk_ctr_clone", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			Map.Entry<String, String> entry = retList.get(0);
			Map<String, Object> map = JsonObject.append(entry.getValue())
					.toMap();
			return map;
		}

		return null;

	}

}
