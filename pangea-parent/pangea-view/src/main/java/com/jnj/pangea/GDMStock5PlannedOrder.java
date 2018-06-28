package com.jnj.pangea;

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
public class GDMStock5PlannedOrder implements IEventProcessor {

	private final String FAILREGION = "/plan/edm_failed_data";

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
											.append("stockId",
													data.get("stockId"))
											.toJson();

									ViewResultItem viewRaw = ViewResultBuilder
											.newResultItem(viewKey, data);
									resultList.add(viewRaw);
								} else {
									failMap.forEach((key, value) -> {
										ViewResultItem viewRaw = ViewResultBuilder
												.newResultItem(
														FAILREGION,
														key,
														(Map<String, Object>) value
																.toRawData());
										resultList.add(viewRaw);
									});
								}
							} catch (Exception exception) {
								LogUtil.getCoreLog()
										.info("GDMStock5PlannedOrder Exception occured. key = {}.",
												e.getKey());
								LogUtil.getCoreLog().info(
										"GDMStock5PlannedOrder Exception:",
										exception);
							}
						});

		return resultList;
	}

	public boolean buildView(RawDataValue raw, RawDataBuilder builder,
			Map<String, RawDataBuilder> failMap, List<ViewResultItem> itemList) {

		Map map = raw.toMap();

		String localPlanningRelevant = null;
		String planOrdrTypeCd = String.valueOf(map.get("planOrdrTypeCd"));
		String sourceObjectAttribute2Value = null;
		String plngScnroCd = String.valueOf(map.get("plngScnroCd"));
		String primaryPlanningCode = null;
		String grDaysLeadQty = String.valueOf(map.get("grDaysLeadQty"));
		String mfgPlanOrdrDocId = String.valueOf(map.get("mfgPlanOrdrDocId"));
		String plntCd = String.valueOf(map.get("plntCd"));
		String planOrdrEndDt = String.valueOf(map.get("planOrdrEndDt"));
		String prdtnVersNum = String.valueOf(map.get("prdtnVersNum"));
		String srcSysCd = String.valueOf(map.get("srcSysCd"));
		String materialNumber = null;
		String resultStartDate = null;
		String matlNum = String.valueOf(map.get("matlNum"));
		String planOrdrQty = String.valueOf(map.get("planOrdrQty"));
		String noPlanRelevant = null;

		builder.put("active", "YES");
		builder.put("activeOPRERP", "YES");
		builder.put("activeSOPERP", "NO");
		builder.put("consignment", "NO");
		builder.put("batchId", "");
		builder.put("inventoryLinkGroupId", "");
		builder.put("vendorId", "");
		builder.put("certaintyId", "PA");
		builder.put("stockType", "movement");
		builder.put("blockedQuantity", "0.0");
		builder.put("qualityQuantity", "0.0");
		builder.put("restrictedQuantity", "0.0");
		builder.put("returnsQuantity", "0.0");
		builder.put("transferQuantity", "0.0");
		builder.put("unrestrictedQuantity", "0.0");
		builder.put("processTypeId", "Production");
		builder.put("transitDate", "1980/01/01 00:00:00");

		String stockId = null;

		Map map1 = joinMaterialGlobal(matlNum, srcSysCd);
		if (map1 != null) {
			primaryPlanningCode = String.valueOf(map1
					.get("primaryPlanningCode"));
			materialNumber = String.valueOf(map1.get("materialNumber"));
		}

		if (StringUtils.isNotEmpty(primaryPlanningCode)) {
			stockId = StringInner.join(primaryPlanningCode, "/", srcSysCd, "_",
					plntCd, "/", mfgPlanOrdrDocId);
		} else {
			stockId = StringInner.join(materialNumber, "/", srcSysCd, "_",
					plntCd, "/", mfgPlanOrdrDocId);
		}

		builder.put("stockId", stockId);

		String quantity = null;

		if ("000".equals(plngScnroCd)) {

			Map map2 = joinPlanObjectFilter(srcSysCd, plntCd, planOrdrTypeCd);
			if (map2 != null) {
				sourceObjectAttribute2Value = String.valueOf(map2
						.get("sourceObjectAttribute2Value"));
				if (StringUtils.isNotEmpty(sourceObjectAttribute2Value)) {
					quantity = planOrdrQty;
				}
			} else {
				return false;
			}
		}

		else {

			return false;
		}
		builder.put("quantity", quantity);

		String locationId = null;

		Map map3 = joinPlant(plntCd, srcSysCd);
		if (map3 != null) {
			localPlanningRelevant = String.valueOf(map3
					.get("localPlanningRelevant"));
		}

		if ("X".equals(localPlanningRelevant)) {
			locationId = StringInner.join(srcSysCd, "_", plntCd);
		} else {
			return false;
		}

		builder.put("locationId", locationId);

		String productId = null;

		Map map4 = joinMaterialPlanStatus(matlNum, plntCd, srcSysCd);
		if (map4 != null) {
			noPlanRelevant = String.valueOf(map4.get("noPlanRelevant"));
		}

		if ("X".equals(noPlanRelevant)) {
			Map map5 = joinMaterialGlobal(matlNum, srcSysCd);
			if (map5 != null) {
				primaryPlanningCode = String.valueOf(map5
						.get("primaryPlanningCode"));
				materialNumber = String.valueOf(map5.get("materialNumber"));
				if (StringUtils.isNotEmpty(primaryPlanningCode)) {
					productId = primaryPlanningCode;
				} else if (StringUtils.isNotEmpty(materialNumber)) {
					productId = materialNumber;
				} else {

					writeFailDataToRegion(failMap, "SP",
							"GDMStock_PlannedOrder", "PL09", "", "project_one",
							mfgPlanOrdrDocId, srcSysCd, "", "", "", "");
					return false;
				}
			}
		} else {
			return false;
		}

		builder.put("productId", productId);

		String processId = null;

		processId = StringInner.join(prdtnVersNum, "/", productId, "/",
				locationId);
		builder.put("processId", processId);

		String receiptDate = null;

		receiptDate = DateInner.dateToString(
				DateInner.stringToDate(planOrdrEndDt, "yyyyMMdd"),
				"yyyy/MM/dd HH:mm:ss");
		builder.put("receiptDate", receiptDate);

		String startDate = null;

		startDate = calcDate(planOrdrEndDt, grDaysLeadQty);

		builder.put("startDate", startDate);

		String erpOrderId = null;

		erpOrderId = mfgPlanOrdrDocId;
		builder.put("erpOrderId", erpOrderId);

		return true;
	}

	public Map joinMaterialGlobal(String matlNum, String srcSysCd) {

		String key = JsonObject.create().append("localMaterialNumber", matlNum)
				.append("sourceSystem", srcSysCd).toJson();
		Map map = AdfViewHelper.get("/edm/material_global_v1", key);
		if (map != null) {
			return map;
		}

		return null;
	}

	public Map joinPlant(String plntCd, String srcSysCd) {

		String key = JsonObject.create().append("localPlant", plntCd)
				.append("sourceSystem", srcSysCd).toJson();
		Map map = AdfViewHelper.get("/edm/plant_v1", key);
		if (map != null) {
			return map;
		}

		return null;
	}

	public Map joinMaterialPlanStatus(String matlNum, String plntCd,
			String srcSysCd) {

		String key = JsonObject.create().append("localMaterialNumber", matlNum)
				.append("localPlant", plntCd).append("sourceSystem", srcSysCd)
				.toJson();
		Map map = AdfViewHelper.get("/plan/cns_material_plan_status", key);
		if (map != null) {
			return map;
		}

		return null;
	}

	public Map joinPlanObjectFilter(String srcSysCd, String plntCd,
			String planOrdrTypeCd) {

		ADFCriteria adfCriteria8 = QueryHelper.buildCriteria(
				"sourceObjectTechName").is("planned_order");
		ADFCriteria adfCriteria9 = QueryHelper.buildCriteria("sourceSystem")
				.is(srcSysCd);
		ADFCriteria adfCriteria10 = QueryHelper.buildCriteria(
				"sourceObjectAttribute1").is("plntCd");
		ADFCriteria adfCriteria11 = QueryHelper.buildCriteria(
				"sourceObjectAttribute1Value").is(plntCd);
		ADFCriteria adfCriteria12 = QueryHelper.buildCriteria(
				"sourceObjectAttribute2").is("planOrdrTypeCd");
		ADFCriteria adfCriteria13 = QueryHelper.buildCriteria(
				"sourceObjectAttribute2Value").is(planOrdrTypeCd);
		ADFCriteria groupCriteria14 = adfCriteria13.and(adfCriteria12)
				.and(adfCriteria11).and(adfCriteria10).and(adfCriteria9)
				.and(adfCriteria8);

		ADFCriteria adfCriteria = groupCriteria14;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/plan/cns_plan_object_filter", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			Map.Entry<String, String> entry = retList.get(0);
			Map<String, Object> map = JsonObject.append(entry.getValue())
					.toMap();
			return map;
		}

		return null;

	}

	private final String calcDate(String planOrdrEndDt, String grDaysLeadQty) {
		Calendar startDate = Calendar.getInstance();

		startDate.setTime(DateInner.offsetDate(
				DateInner.stringToDate(planOrdrEndDt, "yyyyMMdd"),
				Integer.parseInt(grDaysLeadQty)));

		int dayOfWeek = startDate.get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek == 1) {
			startDate.add(Calendar.DATE, 1);
		} else if (dayOfWeek == 7) {
			startDate.add(Calendar.DATE, 2);
		}
		return DateInner.dateToString(startDate.getTime(),
				"yyyy/MM/dd HH:mm:ss");
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
