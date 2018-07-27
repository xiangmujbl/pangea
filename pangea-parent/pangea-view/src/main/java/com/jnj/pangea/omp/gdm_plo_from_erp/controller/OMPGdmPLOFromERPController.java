package com.jnj.pangea.omp.gdm_plo_from_erp.controller;

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
import com.jnj.pangea.hook.OMPGdmPLOFromErpV1Hook;
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
public class OMPGdmPLOFromERPController implements IEventProcessor {

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
											.append("PLOFromERPId",
													data.get("PLOFromERPId"))
											.toJson();

									ViewResultItem viewRaw = ViewResultBuilder
											.newResultItem(viewKey, data);
									resultList.add(viewRaw);
								}
							} catch (Exception exception) {
								LogUtil.getCoreLog()
										.info("OMPGdmPLOFromERPController Exception occured. key = {}.",
												e.getKey());
								LogUtil.getCoreLog()
										.info("OMPGdmPLOFromERPController Exception:",
												exception);
							}
						});

		return resultList;
	}

	public boolean buildView(RawDataValue raw, RawDataBuilder builder,
			Map<String, RawDataBuilder> failMap, List<ViewResultItem> itemList) {

		Map map = raw.toMap();

		String prdtnVers = StringInner.getString(map, "prdtnVers");
		String srcSysCd = StringInner.getString(map, "srcSysCd");
		String plntCd = StringInner.getString(map, "plntCd");
		String matlNum = StringInner.getString(map, "matlNum");
		String planOrdrQty = StringInner.getString(map, "planOrdrQty");
		String mfgPlanOrdrDocId = StringInner
				.getString(map, "mfgPlanOrdrDocId");
		String prdtnFnshdDt = StringInner.getString(map, "prdtnFnshdDt");
		String planOrdrEndDt = StringInner.getString(map, "planOrdrEndDt");
		String grDaysLeadQty = StringInner.getString(map, "grDaysLeadQty");
		String prdtnStrtDt = StringInner.getString(map, "prdtnStrtDt");
		String planOrdrStrtDt = StringInner.getString(map, "planOrdrStrtDt");
		String planOrdrTypeCd = StringInner.getString(map, "planOrdrTypeCd");
		String internalTimeStamp = StringInner.getString(map,
				"internalTimeStamp");
		String totalQuantityOut = "";
		String unitIdOut = "";
		String deleted = "NO";
		String primaryPlanningCode = null;
		String localBaseUom = null;
		String unit = null;

		String PLOFromERPId = null;

		if (StringInner.isStringNotEmpty(srcSysCd)
				&& StringInner.isStringNotEmpty(mfgPlanOrdrDocId)) {

			PLOFromERPId = StringInner.join(srcSysCd, "/", mfgPlanOrdrDocId);
		}

		else {

			return false;
		}
		builder.put("PLOFromERPId", PLOFromERPId);

		builder.put("deleted", "NO");

		String endDate = null;

		if (StringInner.isStringNotEmpty(prdtnFnshdDt)
				&& StringInner.isStringNotEmpty(grDaysLeadQty)) {

			endDate = OMPGdmPLOFromErpV1Hook.addDateByWorksday(prdtnFnshdDt,
					grDaysLeadQty);
		}

		else {

			if (StringInner.isStringNotEmpty(planOrdrEndDt)
					&& StringInner.isStringNotEmpty(grDaysLeadQty)) {
				endDate = OMPGdmPLOFromErpV1Hook.addDateByWorksday(
						planOrdrEndDt, grDaysLeadQty);
			} else {
				endDate = OMPGdmPLOFromErpV1Hook.addDateByWorksday(
						planOrdrEndDt, "0");
			}
		}
		builder.put("endDate", endDate);

		String ERPId = null;

		if (StringInner.isStringNotEmpty(srcSysCd)
				&& StringInner.isStringNotEmpty(mfgPlanOrdrDocId)) {

			ERPId = StringInner.join(srcSysCd, "/", mfgPlanOrdrDocId);
		}

		else {

			return false;
		}
		builder.put("ERPId", ERPId);

		if (StringInner.isStringNotEmpty(internalTimeStamp)) {

			internalTimeStamp = internalTimeStamp;
		}

		else {

			internalTimeStamp = "";
		}
		builder.put("internalTimeStamp", internalTimeStamp);

		String kdauf = null;

		builder.put("kdauf", "");

		String kdpos = null;

		builder.put("kdpos", "");

		String locationId = null;

		if (StringInner.isStringNotEmpty(srcSysCd)
				&& StringInner.isStringNotEmpty(plntCd)) {

			Map cnsPlantAttrMap = joinCnsPlantAttr(srcSysCd, plntCd);
			if (cnsPlantAttrMap != null) {
				if (StringInner.isStringNotEmpty(matlNum)) {
					Map cnsMaterialPlanStatusMap = joinCnsMaterialPlanStatus(
							srcSysCd, plntCd, matlNum);
					if (cnsMaterialPlanStatusMap != null) {
						locationId = StringInner.join(srcSysCd, "_", plntCd);
					}
					if (cnsMaterialPlanStatusMap == null) {
						locationId = "";
					}
				} else {
					locationId = "";
				}
			}
			if (cnsPlantAttrMap == null) {
				locationId = "";
			}
		}

		else {

			locationId = "";
		}
		builder.put("locationId", locationId);

		String manualOffset = null;

		builder.put("manualOffset", "0");

		String PLOType = null;

		if (StringInner.isStringNotEmpty(planOrdrTypeCd)) {

			PLOType = planOrdrTypeCd;
		}

		else {

			PLOType = "";
		}
		builder.put("PLOType", PLOType);

		String productId = null;

		if (StringInner.isStringNotEmpty(srcSysCd)
				&& StringInner.isStringNotEmpty(plntCd)) {

			Map cnsPlantAttrMap = joinCnsPlantAttr(srcSysCd, plntCd);
			if (cnsPlantAttrMap != null) {
				if (StringInner.isStringNotEmpty(matlNum)) {
					Map cnsMaterialPlanStatusMap = joinCnsMaterialPlanStatus(
							srcSysCd, plntCd, matlNum);
					if (cnsMaterialPlanStatusMap != null) {
						if (true) {
							Map materialGlobalV1Map = joinMaterialGlobalV1(
									srcSysCd, matlNum);
							if (materialGlobalV1Map != null) {
								primaryPlanningCode = StringInner.getString(
										materialGlobalV1Map,
										"primaryPlanningCode");
								if (StringInner
										.isStringNotEmpty(primaryPlanningCode)) {
									productId = primaryPlanningCode;
								} else {
									productId = "";
								}
							}
							if (materialGlobalV1Map == null) {
								productId = "";
							}
						}
					}
					if (cnsMaterialPlanStatusMap == null) {
						productId = "";
					}
				} else {
					productId = "";
				}
			}
			if (cnsPlantAttrMap == null) {
				productId = "";
			}
		}

		else {

			productId = "";
		}
		builder.put("productId", productId);

		String startDate = null;

		if (StringInner.isStringNotEmpty(prdtnStrtDt)) {

			startDate = DateInner.dateToString(
					DateInner.stringToDate(prdtnStrtDt, DateInner.F_yyyyMMdd),
					DateInner.yyyy_MM_dd_HHmmss_2);
		}

		else {

			if (StringInner.isStringNotEmpty(planOrdrStrtDt)) {
				startDate = DateInner.dateToString(DateInner.stringToDate(
						planOrdrStrtDt, DateInner.F_yyyyMMdd),
						DateInner.yyyy_MM_dd_HHmmss_2);
			} else {
				startDate = "";
			}
		}
		builder.put("startDate", startDate);

		String totalQuantity = null;

		if (StringInner.isStringNotEmpty(planOrdrQty)) {

			totalQuantity = planOrdrQty;
		}

		else {

			totalQuantity = "";
		}
		builder.put("totalQuantity", totalQuantity);

		String unitId = null;

		Map materialGlobalV1Map = joinMaterialGlobalV1(srcSysCd, matlNum);
		if (materialGlobalV1Map != null) {
			localBaseUom = StringInner.getString(materialGlobalV1Map,
					"localBaseUom");
		}
		if (StringInner.isStringNotEmpty(localBaseUom)) {
			Map cnsPlantUnitMap = joinCnsPlanUnit(localBaseUom);
			if (cnsPlantUnitMap != null) {
				unit = StringInner.getString(cnsPlantUnitMap, "unit");
				if (StringInner.isStringNotEmpty(unit)) {
					unitIdOut = unit;
				} else {
					unitIdOut = "";
				}
			}
			if (cnsPlantUnitMap == null) {
				unitIdOut = "";
			}
		} else {
			unitIdOut = "";
		}

		if (materialGlobalV1Map == null) {

			unitIdOut = "";
		}

		unitId = unitIdOut;
		builder.put("unitId", unitIdOut);

		String verid = null;

		if (StringInner.isStringEmpty(prdtnVers)) {

			verid = "";
		}

		else {

			verid = prdtnVers;
		}
		builder.put("verid", verid);

		return true;
	}

	public Map joinCnsMaterialPlanStatus(String srcSysCd, String plntCd,
			String matlNum) {

		ADFCriteria adfCriteria1 = QueryHelper.buildCriteria("sourceSystem")
				.is(srcSysCd);
		ADFCriteria adfCriteria2 = QueryHelper.buildCriteria("localPlant").is(
				plntCd);
		ADFCriteria adfCriteria3 = QueryHelper.buildCriteria("materialNumber")
				.is(matlNum);
		ADFCriteria adfCriteria4 = QueryHelper.buildCriteria("spRelevant").is(
				"X");
		ADFCriteria groupCriteria11 = adfCriteria4.and(adfCriteria3)
				.and(adfCriteria2).and(adfCriteria1);

		ADFCriteria adfCriteria = groupCriteria11;
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

	public Map joinCnsPlantAttr(String srcSysCd, String plntCd) {

		ADFCriteria adfCriteria5 = QueryHelper.buildCriteria("sourceSystem")
				.is(srcSysCd);
		ADFCriteria adfCriteria6 = QueryHelper.buildCriteria("localPlant").is(
				plntCd);
		ADFCriteria adfCriteria7 = QueryHelper.buildCriteria(
				"localPlanningRelevant").is("X");
		ADFCriteria groupCriteria12 = adfCriteria7.and(adfCriteria6).and(
				adfCriteria5);

		ADFCriteria adfCriteria = groupCriteria12;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/plan/cns_plant_attr", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			Map.Entry<String, String> entry = retList.get(0);
			Map<String, Object> map = JsonObject.append(entry.getValue())
					.toMap();
			return map;
		}

		return null;

	}

	public Map joinMaterialGlobalV1(String srcSysCd, String matlNum) {

		ADFCriteria adfCriteria8 = QueryHelper.buildCriteria("sourceSystem")
				.is(srcSysCd);
		ADFCriteria adfCriteria9 = QueryHelper.buildCriteria(
				"localMaterialNumber").is(matlNum);
		ADFCriteria groupCriteria13 = adfCriteria9.and(adfCriteria8);

		ADFCriteria adfCriteria = groupCriteria13;
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

	public Map joinCnsPlanUnit(String localBaseUom) {

		ADFCriteria adfCriteria10 = QueryHelper.buildCriteria("localUom").is(
				localBaseUom);
		ADFCriteria groupCriteria14 = adfCriteria10;

		ADFCriteria adfCriteria = groupCriteria14;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/plan/cns_plan_unit", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			Map.Entry<String, String> entry = retList.get(0);
			Map<String, Object> map = JsonObject.append(entry.getValue())
					.toMap();
			return map;
		}

		return null;

	}

}
