package com.jnj.pangea.omp.gdm_process_process.controller;

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
public class OMPGdmProcessProcess implements IEventProcessor {

	@Override
	public List<ViewResultItem> process(List<RawDataEvent> list) {

		List<ViewResultItem> resultList = new ArrayList<>();

		list.stream()
				.filter(e -> !(OMPGdmProcessProcessHook.check(e.getValue())))
				.forEach(
						e -> {
							try {
								RawDataBuilder builder = new RawDataBuilder();

								Map<String, RawDataBuilder> failMap = new HashMap<>();

								List<ViewResultItem> itemList = new ArrayList<>();

								boolean isOk = buildView(e.getValue(), builder,
										failMap, itemList);
								if (itemList != null && itemList.size() > 0) {
									resultList.addAll(itemList);
								}

								if (isOk) {
									Map<String, Object> data = (Map<String, Object>) builder
											.toRawData();
									String viewKey = JsonObject
											.create()
											.append("processId",
													data.get("processId"))
											.toJson();

									ViewResultItem viewRaw = ViewResultBuilder
											.newResultItem(viewKey, data);
									resultList.add(viewRaw);
								}
							} catch (Exception exception) {
								LogUtil.getCoreLog()
										.info("OMPGdmProcessProcess Exception occured. key = {}.",
												e.getKey());
								LogUtil.getCoreLog().info(
										"OMPGdmProcessProcess Exception:",
										exception);
							}
						});

		return resultList;
	}

	public boolean buildView(RawDataValue raw, RawDataBuilder builder,
			Map<String, RawDataBuilder> failMap, List<ViewResultItem> itemList) {

		Map map = raw.toMap();

		String sourceSysCd = StringInner.getString(map, "sourceSysCd");
		String mfgOrdrNum = StringInner.getString(map, "mfgOrdrNum");
		String rtngTypCd = StringInner.getString(map, "rtngTypCd");
		String rtngGrpCd = StringInner.getString(map, "rtngGrpCd");
		String rtngGrpCntrNum = StringInner.getString(map, "rtngGrpCntrNum");
		String matlNum = "";
		String prdntVrsnNum = "";
		String valFromDt_VERSN = "";
		String valFromDt_HDR = "";
		String srcSysCd = null;
		String plntCd = null;
		String valFromDt = null;
		String rtgVld_ToDt = null;
		String valToDt = null;
		String prdVrsnDesc = null;
		String primaryPlanningCode = null;
		String spRelevant = null;
		String noPlanRelevant = null;

		builder.put("active", "YES");
		builder.put("activeOPRERP", "YES");
		builder.put("activeSOPERP", "NO");
		builder.put("label", "");

		String processId = null;

		Map retMap = ruleJ1_1(sourceSysCd, mfgOrdrNum);
		if (retMap != null) {
			srcSysCd = StringInner.getString(retMap, "srcSysCd");
			matlNum = StringInner.getString(retMap, "matlNum");
			plntCd = StringInner.getString(retMap, "plntCd");
			prdntVrsnNum = StringInner.getString(retMap, "prdntVrsnNum");
		}
		if (StringInner.isStringNotEmpty(srcSysCd)
				&& StringInner.isStringNotEmpty(mfgOrdrNum)) {
			processId = OMPGdmProcessProcessHook.getProcessId(srcSysCd,
					mfgOrdrNum);
		}

		if (retMap == null) {

			return false;
		}

		builder.put("processId", processId);

		String endEff = null;

		Map map1 = ruleT4_1(sourceSysCd, rtngTypCd, rtngGrpCd, rtngGrpCntrNum);
		if (map1 != null) {
			valFromDt = StringInner.getString(map1, "valFromDt");
			rtgVld_ToDt = StringInner.getString(map1, "rtgVld_ToDt");
		}
		if (StringInner.isStringNotEmpty(valFromDt)) {
			valFromDt_HDR = valFromDt;
		}

		Map map2 = ruleC2(srcSysCd, matlNum, plntCd, prdntVrsnNum);
		if (map2 != null) {
			valFromDt = StringInner.getString(map2, "valFromDt");
			valToDt = StringInner.getString(map2, "valToDt");
			prdVrsnDesc = StringInner.getString(map2, "prdVrsnDesc");
		}
		if (StringInner.isStringNotEmpty(valFromDt)) {
			valFromDt_VERSN = valFromDt;
		}

		if (StringInner.isStringNotEmpty(rtgVld_ToDt)
				&& StringInner.isStringNotEmpty(valToDt)) {

			endEff = OMPGdmProcessProcessHook.getEndEff_T4_1(rtgVld_ToDt,
					valToDt);
		}

		builder.put("endEff", endEff);

		String description = null;

		description = prdVrsnDesc;
		builder.put("description", description);

		String ERPOrderId = null;

		if (StringInner.isStringNotEmpty(srcSysCd)
				&& StringInner.isStringNotEmpty(mfgOrdrNum)) {

			ERPOrderId = OMPGdmProcessProcessHook.getProcessId(srcSysCd,
					mfgOrdrNum);
		}

		builder.put("ERPOrderId", ERPOrderId);

		String locationId = null;

		if (StringInner.isStringNotEmpty(srcSysCd)
				&& StringInner.isStringNotEmpty(plntCd)) {

			locationId = StringInner.join(srcSysCd, "_", plntCd);
		}

		builder.put("locationId", locationId);

		String processTypeId = null;

		processTypeId = "Production";
		builder.put("processTypeId", processTypeId);

		String productId = null;

		Map map3 = rule_productId(srcSysCd, matlNum);
		if (map3 != null) {
			primaryPlanningCode = StringInner.getString(map3,
					"primaryPlanningCode");
		}

		Map map4 = rule_productId_1(srcSysCd, matlNum, plntCd);
		if (map4 != null) {
			spRelevant = StringInner.getString(map4, "spRelevant");
			noPlanRelevant = StringInner.getString(map4, "noPlanRelevant");
		}
		if (spRelevant.equals("X") || noPlanRelevant.equals("X")) {
			productId = primaryPlanningCode;
		} else {
			return false;
		}

		builder.put("productId", productId);

		String startEff = null;

		if (StringInner.isStringNotEmpty(valFromDt_HDR)
				&& StringInner.isStringNotEmpty(valFromDt_VERSN)) {

			startEff = OMPGdmProcessProcessHook.getEndEff_T4_2(valFromDt_HDR,
					valFromDt_VERSN);
		}

		builder.put("startEff", startEff);

		String vendorId = null;

		vendorId = "";
		builder.put("vendorId", vendorId);

		return true;
	}

	public Map ruleJ1_1(String sourceSysCd, String mfgOrdrNum) {

		ADFCriteria adfCriteria1 = QueryHelper.buildCriteria("srcSysCd").is(
				sourceSysCd);
		ADFCriteria adfCriteria2 = QueryHelper.buildCriteria("mfgOrdrNum").is(
				mfgOrdrNum);
		ADFCriteria groupCriteria16 = adfCriteria2.and(adfCriteria1);

		ADFCriteria adfCriteria = groupCriteria16;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/edm/mfg_order_itm", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			Map.Entry<String, String> entry = retList.get(0);
			Map<String, Object> map = JsonObject.append(entry.getValue())
					.toMap();
			return map;
		}

		return null;

	}

	public Map ruleC2(String srcSysCd, String matlNum, String plntCd,
			String prdntVrsnNum) {

		ADFCriteria adfCriteria3 = QueryHelper.buildCriteria("srcSysCd").is(
				srcSysCd);
		ADFCriteria adfCriteria4 = QueryHelper.buildCriteria("matlNum").is(
				matlNum);
		ADFCriteria adfCriteria5 = QueryHelper.buildCriteria("plntCd").is(
				plntCd);
		ADFCriteria adfCriteria6 = QueryHelper.buildCriteria("prdntVrsnNum")
				.is(prdntVrsnNum);
		ADFCriteria groupCriteria17 = adfCriteria6.and(adfCriteria5)
				.and(adfCriteria4).and(adfCriteria3);

		ADFCriteria adfCriteria = groupCriteria17;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/edm/matl_prod_versn", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			Map.Entry<String, String> entry = retList.get(0);
			Map<String, Object> map = JsonObject.append(entry.getValue())
					.toMap();
			return map;
		}

		return null;

	}

	public Map ruleT4_1(String srcSysCd, String rtngTypCd, String rtngGrpCd,
			String rtngGrpCntrNum) {

		ADFCriteria adfCriteria7 = QueryHelper.buildCriteria("srcSysCd").is(
				srcSysCd);
		ADFCriteria adfCriteria8 = QueryHelper.buildCriteria("rtngTypCd").is(
				rtngTypCd);
		ADFCriteria adfCriteria9 = QueryHelper.buildCriteria("rtngGrpCd").is(
				rtngGrpCd);
		ADFCriteria adfCriteria10 = QueryHelper.buildCriteria("rtngGrpCntrNbr")
				.is(rtngGrpCntrNum);
		ADFCriteria groupCriteria18 = adfCriteria10.and(adfCriteria9)
				.and(adfCriteria8).and(adfCriteria7);

		ADFCriteria adfCriteria = groupCriteria18;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/edm/mfg_rtng_hdr", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			Map.Entry<String, String> entry = retList.get(0);
			Map<String, Object> map = JsonObject.append(entry.getValue())
					.toMap();
			return map;
		}

		return null;

	}

	public Map rule_productId(String srcSysCd, String matlNum) {

		ADFCriteria adfCriteria11 = QueryHelper.buildCriteria("sourceSystem")
				.is(srcSysCd);
		ADFCriteria adfCriteria12 = QueryHelper.buildCriteria(
				"localMaterialNumber").is(matlNum);
		ADFCriteria groupCriteria19 = adfCriteria12.and(adfCriteria11);

		ADFCriteria adfCriteria = groupCriteria19;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/edm/material_global_v1", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			Map.Entry<String, String> entry = retList.get(0);
			Map<String, Object> map = JsonObject.append(entry.getValue())
					.toMap();
			return map;
		}

		return null;

	}

	public Map rule_productId_1(String srcSysCd, String matlNum, String plntCd) {

		ADFCriteria adfCriteria13 = QueryHelper.buildCriteria("sourceSystem")
				.is(srcSysCd);
		ADFCriteria adfCriteria14 = QueryHelper.buildCriteria(
				"localMaterialNumber").is(matlNum);
		ADFCriteria adfCriteria15 = QueryHelper.buildCriteria("localPlant").is(
				plntCd);
		ADFCriteria groupCriteria20 = adfCriteria15.and(adfCriteria14).and(
				adfCriteria13);

		ADFCriteria adfCriteria = groupCriteria20;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/plan/cns_material_plan_status", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			Map.Entry<String, String> entry = retList.get(0);
			Map<String, Object> map = JsonObject.append(entry.getValue())
					.toMap();
			return map;
		}

		return null;

	}

}
