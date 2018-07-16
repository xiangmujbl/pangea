package com.jnj.pangea.omp.gdm_process_master.controller;

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
public class OMPGdmProcessMaster implements IEventProcessor {

	@Override
	public List<ViewResultItem> process(List<RawDataEvent> list) {

		List<ViewResultItem> resultList = new ArrayList<>();

		list.stream()
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
										.info("OMPGdmProcessMaster Exception occured. key = {}.",
												e.getKey());
								LogUtil.getCoreLog().info(
										"OMPGdmProcessMaster Exception:",
										exception);
							}
						});

		return resultList;
	}

	public boolean buildView(RawDataValue raw, RawDataBuilder builder,
			Map<String, RawDataBuilder> failMap, List<ViewResultItem> itemList) {

		Map map = raw.toMap();

		String srcSysCd = StringInner.getString(map, "srcSysCd");
		String matlNum = StringInner.getString(map, "matlNum");
		String plntCd = StringInner.getString(map, "plntCd");
		String rtngTypCd = StringInner.getString(map, "rtngTypCd");
		String rntgGrpCd = StringInner.getString(map, "rntgGrpCd");
		String rtngGrpCd = "";
		String valToDt = "";
		String valFromDt_hdr = "";
		String valFromDt_versn = "";
		String rntgGrpCntrNbr = StringInner.getString(map, "rntgGrpCntrNbr");
		String valFromDt = null;
		String prdVrsnDesc = null;
		String prdntVrsnNum = null;
		String rtgVld_ToDt = null;
		String infotype = null;
		String localvendor = null;
		String primaryPlanningCode = null;
		String sourceSystem = null;
		String localMaterialNumber = null;
		String spRelevant = null;
		String noPlanRelevant = null;

		builder.put("active", "YES");
		builder.put("activeOPRERP", "YES");
		builder.put("activeSOPERP", "NO");
		builder.put("ERPOrderId", "");
		builder.put("label", "");

		String processId = null;

		Map map1 = ruleC1AndT1(srcSysCd, matlNum, plntCd, rtngTypCd, rntgGrpCd,
				rntgGrpCntrNbr);
		if (map1 != null) {
			valToDt = StringInner.getString(map1, "valToDt");
			valFromDt = StringInner.getString(map1, "valFromDt");
			prdVrsnDesc = StringInner.getString(map1, "prdVrsnDesc");
		}
		if (StringInner.isStringNotEmpty(valFromDt)) {
			valFromDt_versn = valFromDt;
		}

		Map retMap = ruleC1AndT1(srcSysCd, matlNum, plntCd, rtngTypCd,
				rntgGrpCd, rntgGrpCntrNbr);
		if (retMap != null) {
			srcSysCd = StringInner.getString(retMap, "srcSysCd");
			plntCd = StringInner.getString(retMap, "plntCd");
			matlNum = StringInner.getString(retMap, "matlNum");
			prdntVrsnNum = StringInner.getString(retMap, "prdntVrsnNum");
		}
		if (StringInner.isStringNotEmpty(prdntVrsnNum)
				&& StringInner.isStringNotEmpty(matlNum)
				&& StringInner.isStringNotEmpty(srcSysCd)
				&& StringInner.isStringNotEmpty(plntCd)) {
			processId = OMPGdmProcessMasterHook.getProcessId(prdntVrsnNum,
					matlNum, srcSysCd, plntCd);
		}

		if (retMap == null) {

			return false;
		}

		builder.put("processId", processId);

		String description = null;

		description = prdVrsnDesc;
		builder.put("description", description);

		String endEff = null;

		Map map2 = ruleT5(srcSysCd, rtngTypCd, rntgGrpCd, rntgGrpCntrNbr);
		if (map2 != null) {
			rtgVld_ToDt = StringInner.getString(map2, "rtgVld_ToDt");
			valFromDt = StringInner.getString(map2, "valFromDt");
		}
		if (StringInner.isStringNotEmpty(valFromDt)) {
			valFromDt_hdr = valFromDt;
		}

		if (StringInner.isStringNotEmpty(rtgVld_ToDt)
				&& StringInner.isStringNotEmpty(valToDt)) {

			endEff = OMPGdmProcessMasterHook.getEndEff_T5(rtgVld_ToDt, valToDt);
		}

		builder.put("endEff", endEff);

		String locationId = null;

		if (StringInner.isStringNotEmpty(srcSysCd)
				&& StringInner.isStringNotEmpty(plntCd)) {

			locationId = StringInner.join(srcSysCd, "_", plntCd);
		}

		builder.put("locationId", locationId);

		String processTypeId = null;

		Map map3 = ruleT6(plntCd, prdntVrsnNum, matlNum);
		if (map3 != null) {
			infotype = StringInner.getString(map3, "infotype");
			localvendor = StringInner.getString(map3, "localvendor");
			prdntVrsnNum = StringInner.getString(map3, "prdntVrsnNum");
		}
		if (StringInner.isStringNotEmpty(infotype) && infotype.equals("3")) {
			processTypeId = "Subcontracting";
		} else {
			processTypeId = "Production";
		}

		builder.put("processTypeId", processTypeId);

		String productId = null;

		Map map4 = rule_productId(srcSysCd, matlNum);
		if (map4 != null) {
			primaryPlanningCode = StringInner.getString(map4,
					"primaryPlanningCode");
			sourceSystem = StringInner.getString(map4, "sourceSystem");
			localMaterialNumber = StringInner.getString(map4,
					"localMaterialNumber");
		}

		if (StringInner.isStringNotEmpty(sourceSystem)
				&& StringInner.isStringNotEmpty(localMaterialNumber)
				&& StringInner.isStringNotEmpty(plntCd)) {

			Map map5 = rule_productId_1(sourceSystem, localMaterialNumber,
					plntCd);
			if (map5 != null) {
				spRelevant = StringInner.getString(map5, "spRelevant");
				noPlanRelevant = StringInner.getString(map5, "noPlanRelevant");
			}
			if ("X".equals(spRelevant) || "X".equals(noPlanRelevant)) {
				productId = primaryPlanningCode;
			} else {
				return false;
			}
		}

		builder.put("productId", productId);

		String startEff = null;

		startEff = OMPGdmProcessMasterHook.getStartEff_T7(valFromDt_hdr,
				valFromDt_versn);

		builder.put("startEff", startEff);

		String vendorId = null;

		if (StringInner.isStringNotEmpty(infotype) && infotype.equals("3")) {

			vendorId = OMPGdmProcessMasterHook.getVendorId(localvendor);
		}

		else {

			vendorId = "";
		}
		builder.put("vendorId", vendorId);

		return true;
	}

	public Map ruleC1AndT1(String srcSysCd, String matlNum, String plntCd,
			String rtngTypCd, String rntgGrpCd, String rntgGrpCntrNbr) {

		ADFCriteria adfCriteria1 = QueryHelper.buildCriteria("srcSysCd").is(
				srcSysCd);
		ADFCriteria adfCriteria2 = QueryHelper.buildCriteria("matlNum").is(
				matlNum);
		ADFCriteria adfCriteria3 = QueryHelper.buildCriteria("plntCd").is(
				plntCd);
		ADFCriteria adfCriteria4 = QueryHelper.buildCriteria("rtngTypCd").is(
				rtngTypCd);
		ADFCriteria adfCriteria5 = QueryHelper.buildCriteria("rtngGrpCd").is(
				rntgGrpCd);
		ADFCriteria adfCriteria6 = QueryHelper.buildCriteria("rtngGrpCntrNum")
				.is(rntgGrpCntrNbr);
		ADFCriteria groupCriteria19 = adfCriteria6.and(adfCriteria5)
				.and(adfCriteria4).and(adfCriteria3).and(adfCriteria2)
				.and(adfCriteria1);

		ADFCriteria adfCriteria = groupCriteria19;
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

	public Map ruleT5(String srcSysCd, String rtngTypCd, String rntgGrpCd,
			String rntgGrpCntrNbr) {

		ADFCriteria adfCriteria7 = QueryHelper.buildCriteria("srcSysCd").is(
				srcSysCd);
		ADFCriteria adfCriteria8 = QueryHelper.buildCriteria("rtngTypCd").is(
				rtngTypCd);
		ADFCriteria adfCriteria9 = QueryHelper.buildCriteria("rtngGrpCd").is(
				rntgGrpCd);
		ADFCriteria adfCriteria10 = QueryHelper.buildCriteria("rtngGrpCntrNbr")
				.is(rntgGrpCntrNbr);
		ADFCriteria groupCriteria20 = adfCriteria10.and(adfCriteria9)
				.and(adfCriteria8).and(adfCriteria7);

		ADFCriteria adfCriteria = groupCriteria20;
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

	public Map ruleT6(String plntCd, String prdntVrsnNum, String matlNum) {

		ADFCriteria adfCriteria11 = QueryHelper.buildCriteria("localPlanPlant")
				.is(plntCd);
		ADFCriteria adfCriteria12 = QueryHelper.buildCriteria(
				"localProductionVersion").is(prdntVrsnNum);
		ADFCriteria adfCriteria13 = QueryHelper.buildCriteria(
				"localMaterialNumber").is(matlNum);
		ADFCriteria groupCriteria21 = adfCriteria13.and(adfCriteria12).and(
				adfCriteria11);

		ADFCriteria adfCriteria = groupCriteria21;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/edm/purchasing_info_record_v1", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			Map.Entry<String, String> entry = retList.get(0);
			Map<String, Object> map = JsonObject.append(entry.getValue())
					.toMap();
			return map;
		}

		return null;

	}

	public Map rule_productId(String srcSysCd, String matlNum) {

		ADFCriteria adfCriteria14 = QueryHelper.buildCriteria("sourceSystem")
				.is(srcSysCd);
		ADFCriteria adfCriteria15 = QueryHelper.buildCriteria(
				"localMaterialNumber").is(matlNum);
		ADFCriteria groupCriteria22 = adfCriteria15.and(adfCriteria14);

		ADFCriteria adfCriteria = groupCriteria22;
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

		ADFCriteria adfCriteria16 = QueryHelper.buildCriteria("sourceSystem")
				.is(srcSysCd);
		ADFCriteria adfCriteria17 = QueryHelper.buildCriteria(
				"localMaterialNumber").is(matlNum);
		ADFCriteria adfCriteria18 = QueryHelper.buildCriteria("localPlant").is(
				plntCd);
		ADFCriteria groupCriteria23 = adfCriteria18.and(adfCriteria17).and(
				adfCriteria16);

		ADFCriteria adfCriteria = groupCriteria23;
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
