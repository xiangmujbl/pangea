package com.jnj.pangea.omp.gdm_in_transit_stock;

import com.jnj.adf.client.api.ADFCriteria;
import com.jnj.adf.client.api.JsonObject;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.curation.logic.IEventProcessor;
import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.adf.grid.data.raw.RawDataBuilder;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.adf.grid.view.common.AdfViewHelper;
import com.jnj.inner.StringInner;
import com.jnj.pangea.hook.OMPGDMInTransitStockHook;
import com.jnj.pangea.util.DateUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class OMPGDMInTransitStock implements IEventProcessor {

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
											.append("customerId",
													data.get("customerId"))
											.append("productId",
													data.get("productId"))
											.append("dueDate",
													data.get("dueDate"))
											.append("uuid", data.get("uuid"))
											.append("quantity",
													data.get("quantity"))
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
										.info("OMPGDMInTransitStock Exception occured. key = {}.",
												e.getKey());
								LogUtil.getCoreLog().info(
										"OMPGDMInTransitStock Exception:",
										exception);
							}
						});

		return resultList;
	}

	public boolean buildView(RawDataValue raw, RawDataBuilder builder,
			Map<String, RawDataBuilder> failMap, List<ViewResultItem> itemList) {

		Map map = raw.toMap();

		String sourceSystem = StringInner.getString(map, "sourceSystem");
		String demandGroup = StringInner.getString(map, "customerId");
		String demandGroup2 = StringInner.getString(map, "customerId");
		String customerId = StringInner.getString(map, "customerId");
		String customerId2 = StringInner.getString(map, "customerId");
		String productId = StringInner.getString(map, "productId");
		String productId2 = StringInner.getString(map, "productId");
		String date = StringInner.getString(map, "date");
		String quantity = StringInner.getString(map, "quantity");
		String uom = StringInner.getString(map, "uom");
		String sourceCountry = StringInner.getString(map, "sourceCountry");
		String hiytp = "A";
		String localDpParentCode = null;
		String localAuom = null;
		String localDenominator = null;
		String localNumerator = null;

		if (StringInner.isStringEmpty(sourceSystem)
				|| StringInner.isStringEmpty(productId)
				|| StringInner.isStringEmpty(uom)
				|| StringInner.isStringEmpty(customerId)) {

			return false;
		}

		else {

			Map QueryGrpAsgnEntityMap = QueryGrpAsgnEntity(sourceSystem,
					customerId, sourceCountry);
			if (QueryGrpAsgnEntityMap != null) {
				demandGroup = String.valueOf(QueryGrpAsgnEntityMap
						.get("demandGroup"));
				if (StringInner.isStringEmpty(demandGroup)) {

					writeFailDataToRegion(failMap, "DP",
							"OMPGDMInTransitStock", "T4",
							"demandGroup is not defined for customerId",
							"sourceSystem", sourceSystem, customerId2,
							productId2, "", "", "");
					return false;
				}
			}
			if (QueryGrpAsgnEntityMap == null) {
				demandGroup = OMPGDMInTransitStockHook.getDemandGroup(
						customerId, sourceCountry.substring(0, 2), hiytp,
						DateUtils.currentDate(DateUtils.F_yyyyMMdd));
				if (StringInner.isStringEmpty((demandGroup))) {

					writeFailDataToRegion(failMap, "DP",
							"OMPGDMInTransitStock", "T4",
							"demandGroup is not defined for customerId",
							"sourceSystem", sourceSystem, customerId2,
							productId2, "", "", "");
					return false;
				}
			}
		}
		customerId = demandGroup;
		builder.put("customerId", demandGroup);

		Map QueryMaterialGlobalMap = QueryMaterialGlobal(sourceSystem,
				productId2);
		if (QueryMaterialGlobalMap != null) {
			localDpParentCode = String.valueOf(QueryMaterialGlobalMap
					.get("localDpParentCode"));
		}

		if (QueryMaterialGlobalMap == null) {

			productId = OMPGDMInTransitStockHook.getproductId(productId);

			writeFailDataToRegion(failMap, "DP", "OMPGDMInTransitStock", "J1",
					"productID do not exit in edm_material", "sourceSystem",
					sourceSystem, customerId2, productId, "", "", "");
			return false;
		}

		else if (StringInner.isStringEmpty(localDpParentCode)) {

			productId = OMPGDMInTransitStockHook.getproductId(productId);

			writeFailDataToRegion(failMap, "DP", "OMPGDMInTransitStock", "J1",
					"localDpParentCode do not exist in edm material",
					"sourceSystem", sourceSystem, customerId2, productId, "",
					"", "");
			return false;
		}

		else {

			localDpParentCode = StringInner.join("LA_", localDpParentCode);
		}
		productId = localDpParentCode;
		builder.put("productId", localDpParentCode);

		String dueDate = null;

		Map QueryAuomEntityMap = QueryAuomEntity(uom, productId2, sourceSystem);
		if (QueryAuomEntityMap != null) {
			localAuom = String.valueOf(QueryAuomEntityMap.get("localAuom"));
			localDenominator = String.valueOf(QueryAuomEntityMap
					.get("localDenominator"));
			localNumerator = String.valueOf(QueryAuomEntityMap
					.get("localNumerator"));
		}

		if (QueryAuomEntityMap == null) {

			productId = OMPGDMInTransitStockHook.getproductId(productId);

			writeFailDataToRegion(failMap, "DP", "OMPGDMInTransitStock", "J2",
					"unit do not exist in edm_Auom", "sourceSystem",
					sourceSystem, customerId2, productId2, "", "", "");
			return false;
		}

		else {

			dueDate = StringInner.join(date, " 00:00:00");
		}
		builder.put("dueDate", dueDate);

		String uuid = null;

		uuid = OMPGDMInTransitStockHook.getUUID();
		builder.put("uuid", uuid);

		quantity = OMPGDMInTransitStockHook.calculateQuantity(localNumerator,
				quantity, localDenominator);

		builder.put("quantity", quantity);

		return true;
	}

	public Map QueryMaterialGlobal(String sourceSystem,
			String localMaterialNumber) {

		ADFCriteria adfCriteria1 = QueryHelper.buildCriteria("sourceSystem")
				.is(sourceSystem);
		ADFCriteria adfCriteria2 = QueryHelper.buildCriteria(
				"localMaterialNumber").is(localMaterialNumber);
		ADFCriteria groupCriteria14 = adfCriteria2.and(adfCriteria1);

		ADFCriteria adfCriteria = groupCriteria14;
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

	public Map QueryAuomEntity(String localAuom, String localMaterialNumber,
			String sourceSystem) {

		ADFCriteria adfCriteria3 = QueryHelper.buildCriteria("localAuom").is(
				localAuom);
		ADFCriteria adfCriteria4 = QueryHelper.buildCriteria(
				"localMaterialNumber").is(localMaterialNumber);
		ADFCriteria adfCriteria5 = QueryHelper.buildCriteria("sourceSystem")
				.is(sourceSystem);
		ADFCriteria groupCriteria15 = adfCriteria5.and(adfCriteria4).and(
				adfCriteria3);

		ADFCriteria adfCriteria = groupCriteria15;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/edm/material_auom_v1", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			Map.Entry<String, String> entry = retList.get(0);
			Map<String, Object> map = JsonObject.append(entry.getValue())
					.toMap();
			return map;
		}

		return null;

	}

	public Map QueryGrpAsgnEntity(String sourceSystem, String customerId,
			String countryAffiliate) {

		ADFCriteria adfCriteria6 = QueryHelper.buildCriteria("sourceSystem")
				.is(sourceSystem);
		ADFCriteria adfCriteria7 = QueryHelper.buildCriteria("customerId").is(
				customerId);
		ADFCriteria adfCriteria8 = QueryHelper
				.buildCriteria("countryAffiliate").is(countryAffiliate);
		ADFCriteria groupCriteria16 = adfCriteria8.and(adfCriteria7).and(
				adfCriteria6);

		ADFCriteria adfCriteria = groupCriteria16;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/plan/cns_dem_grp_asgn", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			Map.Entry<String, String> entry = retList.get(0);
			Map<String, Object> map = JsonObject.append(entry.getValue())
					.toMap();
			return map;
		}

		return null;

	}

	public Map QueryGrpAsgnWithKnvh(String customerId) {

		ADFCriteria adfCriteria9 = QueryHelper.buildCriteria("customerId").is(
				customerId);
		ADFCriteria groupCriteria17 = adfCriteria9;

		ADFCriteria adfCriteria = groupCriteria17;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/plan/cns_dem_grp_asgn", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			Map.Entry<String, String> entry = retList.get(0);
			Map<String, Object> map = JsonObject.append(entry.getValue())
					.toMap();
			return map;
		}

		return null;

	}

	public Map QueryKnvhEntity(String kunnr, String vkorg, String hiytp,
			String datbi) {

		ADFCriteria adfCriteria10 = QueryHelper.buildCriteria("kunnr")
				.is(kunnr);
		ADFCriteria adfCriteria11 = QueryHelper.buildCriteria("vkorg")
				.startsWith(vkorg);
		ADFCriteria adfCriteria12 = QueryHelper.buildCriteria("hiytp")
				.is(hiytp);
		ADFCriteria adfCriteria13 = QueryHelper.buildCriteria("datbi")
				.greaterThan(datbi);
		ADFCriteria groupCriteria18 = adfCriteria13.and(adfCriteria12)
				.and(adfCriteria11).and(adfCriteria10);

		ADFCriteria adfCriteria = groupCriteria18;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/project_one/knvh", queryStr, -1);
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
				.append("key2", key2).append("key3", key3).toJson();

		RawDataBuilder failBuilder = new RawDataBuilder();
		failBuilder.put("functionalArea", functionalArea);
		failBuilder.put("interfaceID", interfaceID);
		failBuilder.put("errorCode", errorCode);
		failBuilder.put("sourceSystem", sourceSystem);
		failBuilder.put("key1", key1);
		failBuilder.put("key2", key2);
		failBuilder.put("key3", key3);

		failBuilder.put("businessArea", businessArea);
		failBuilder.put("errorValue", errorValue);

		failMap.put(keyJson, failBuilder);
	}

}
