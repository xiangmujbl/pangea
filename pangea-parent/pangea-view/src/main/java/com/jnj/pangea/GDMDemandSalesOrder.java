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
public class GDMDemandSalesOrder implements IEventProcessor {

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
									String viewKey = JsonObject.create()
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
										.info("GDMDemandSalesOrder Exception occured. key = {}.",
												e.getKey());
								LogUtil.getCoreLog().info(
										"GDMDemandSalesOrder Exception:",
										exception);
							}
						});

		return resultList;
	}

	public boolean buildView(RawDataValue raw, RawDataBuilder builder,
			Map<String, RawDataBuilder> failMap, List<ViewResultItem> itemList) {

		Map map = raw.toMap();

		String localShipToParty = StringInner
				.getString(map, "localShipToParty");
		String localSalesOrg = StringInner.getString(map, "localSalesOrg");
		String localScheduleLineDate = StringInner.getString(map,
				"localScheduleLineDate");
		String sourceSystem = StringInner.getString(map, "sourceSystem");
		String localPlant = StringInner.getString(map, "localPlant");
		String localMaterialNumber = StringInner.getString(map,
				"localMaterialNumber");
		String allMaterialNumber = "*";
		String inclFlag = "I";
		String allShipTo = "ALL";
		String scheduleLineItem = StringInner
				.getString(map, "scheduleLineItem");
		String salesOrderQty = StringInner.getString(map, "salesOrderQty");
		String localRejReason = StringInner.getString(map, "localRejReason");
		String salesOrderNo = StringInner.getString(map, "salesOrderNo");
		String salesOrderItem = StringInner.getString(map, "salesOrderItem");
		String localRejStat = StringInner.getString(map, "localRejStat");
		String localDelvStat = StringInner.getString(map, "localDelvStat");
		String localNumtoBase = StringInner.getString(map, "localNumtoBase");
		String localDentoBase = StringInner.getString(map, "localDentoBase");
		String localSubDocCatg = "J";
		String localOrderType = StringInner.getString(map, "localOrderType");
		String localRequestedDate = StringInner.getString(map,
				"localRequestedDate");
		String localBaseUom = null;
		String unit = null;
		String inclExcl = null;
		String demandGroup = null;
		String vkorg = null;
		String datbi = null;
		String hkunnr = null;
		String resultStartDate = null;
		String resultDate = null;
		String minShelfLife = null;
		String minRemShelfLife = null;
		String spRelevant = null;
		String noPlanRelevant = null;
		String primaryPlanningCode = null;
		String materialNumber = null;
		String localBaseQuantity = null;

		builder.put("active", "YES");
		builder.put("activeFCTERP", "NO");
		builder.put("activeOPRERP", "YES");
		builder.put("activeSOPERP", "NO");
		builder.put("batchId", "");
		builder.put("certaintyId", "VC");
		builder.put("inventoryLinkGroupId", "");
		builder.put("wRK02", "");
		builder.put("planningStrategy", "ProductLocationBalanced");

		String unitId = null;

		Map map1 = joinMaterialGlobal(sourceSystem, localMaterialNumber);
		if (map1 != null) {
			localBaseUom = String.valueOf(map1.get("localBaseUom"));
		}

		Map map2 = joinCnsPlanUnit(sourceSystem, localBaseUom);
		if (map2 != null) {
			unit = String.valueOf(map2.get("unit"));
		}

		if (unit != null) {
			unitId = unit;
		} else {

			writeFailDataToRegion(failMap, "SP", "GDMDemandSalesOrder", "SO14",
					"Plan unit is null", sourceSystem, salesOrderNo,
					salesOrderItem, scheduleLineItem, localMaterialNumber, "",
					"");
			return false;
		}

		builder.put("unitId", unitId);

		String customerId = null;

		Map map3 = joinCnsCustExclIncl(sourceSystem, localSalesOrg, inclFlag,
				localShipToParty, allShipTo);
		if (map3 != null) {
			inclExcl = String.valueOf(map3.get("inclExcl"));
		}

		if (inclExcl != null) {
			Map map4 = joinCnsDemGrpAsgn(localShipToParty, localSalesOrg);
			if (map4 != null) {
				demandGroup = String.valueOf(map4.get("demandGroup"));
				if (StringInner.isStringNotEmpty(demandGroup)) {
					customerId = demandGroup;
				}
			}
			if (StringInner.isStringEmpty(customerId)) {
				Map map5 = joinKnvhOnKunnr(localShipToParty);
				if (map5 != null) {
					vkorg = String.valueOf(map5.get("vkorg"));
					datbi = String.valueOf(map5.get("datbi"));
					hkunnr = String.valueOf(map5.get("hkunnr"));
					if (vkorg.equals(localSalesOrg)
							&& Integer.parseInt(localRequestedDate) <= Integer
									.parseInt(datbi)) {
						Map map6 = joinCnsDemGrpAsgnCustId(hkunnr);
						if (map6 != null) {
							demandGroup = String.valueOf(map6
									.get("demandGroup"));
							if (StringInner.isStringNotEmpty(demandGroup)) {
								customerId = demandGroup;
							}
						}
					}
				}
				if (StringInner.isStringEmpty(customerId)) {
					Map map7 = joinKnvhOnHkunnr(localShipToParty);
					if (map7 != null) {
						vkorg = String.valueOf(map7.get("vkorg"));
						datbi = String.valueOf(map7.get("datbi"));
						hkunnr = String.valueOf(map7.get("hkunnr"));
						if (vkorg.equals(localSalesOrg)
								&& Integer.parseInt(localRequestedDate) <= Integer
										.parseInt(datbi)) {
							Map map8 = joinCnsDemGrpAsgnCustId(hkunnr);
							if (map8 != null) {
								demandGroup = String.valueOf(map8
										.get("demandGroup"));
								if (StringInner.isStringNotEmpty(demandGroup)) {
									customerId = demandGroup;
								}
							}
						}
					}
				}
			}
		} else {
			return false;
		}

		builder.put("customerId", customerId);

		String fromDueDate = null;

		fromDueDate = calcDate(localScheduleLineDate);

		builder.put("fromDueDate", fromDueDate);

		String dueDate = null;

		dueDate = addSecond(fromDueDate);

		builder.put("dueDate", dueDate);

		String locationId = null;

		locationId = StringInner.join(sourceSystem, "_", localPlant);
		builder.put("locationId", locationId);

		String minRemainingShelfLife = null;

		Map map9 = joinProdLocMinShelf(sourceSystem, localPlant,
				localMaterialNumber);
		if (map9 != null) {
			minShelfLife = String.valueOf(map9.get("minShelfLife"));
		}

		if (minShelfLife != null) {
			minRemainingShelfLife = minShelfLife;
		} else {
			Map map10 = joinProdLocMinShelf(sourceSystem, localPlant,
					allMaterialNumber);
			if (map10 != null) {
				minShelfLife = String.valueOf(map10.get("minShelfLife"));
				if (minShelfLife != null) {
					minRemainingShelfLife = minShelfLife;
				}
			}
		}

		if (minRemainingShelfLife == null) {

			Map map11 = joinMaterialGlobal(sourceSystem, localMaterialNumber);
			if (map11 != null) {
				minRemShelfLife = String.valueOf(map11.get("minRemShelfLife"));
				if (minRemShelfLife != null) {
					minRemainingShelfLife = minRemShelfLife;
				} else {
					minRemainingShelfLife = "";
				}
			}
		}

		builder.put("minRemainingShelfLife", minRemainingShelfLife);

		String productId = null;

		Map map12 = joinCnsMaterialPlanStatus(sourceSystem, localPlant,
				localMaterialNumber);
		if (map12 != null) {
			spRelevant = String.valueOf(map12.get("spRelevant"));
			noPlanRelevant = String.valueOf(map12.get("noPlanRelevant"));
		}

		if ("X".equals(spRelevant) || "X".equals(noPlanRelevant)) {
			Map map13 = joinMaterialGlobal(sourceSystem, localMaterialNumber);
			if (map13 != null) {
				primaryPlanningCode = String.valueOf(map13
						.get("primaryPlanningCode"));
				materialNumber = String.valueOf(map13.get("materialNumber"));
				if (StringInner.isStringEmpty(primaryPlanningCode)) {
					productId = materialNumber;
				} else {
					productId = primaryPlanningCode;
				}
			}
		} else {
			return false;
		}

		builder.put("productId", productId);

		String demandId = null;

		Map map14 = joinCnsSoTypeInclExcl(localOrderType, localPlant,
				localSalesOrg, sourceSystem, inclFlag);
		if (map14 != null) {
			inclExcl = String.valueOf(map14.get("inclExcl"));
		}

		if (inclExcl != null) {
			if (Integer.parseInt(scheduleLineItem) != 1
					|| Integer.parseInt(salesOrderQty) <= 0
					|| StringInner.isStringNotEmpty(localRejReason)
					|| "C".equals(localRejStat) || "C".equals(localDelvStat)) {
				return false;
			} else {
				demandId = StringInner.join(productId, "/", locationId, "/",
						salesOrderNo, "/", salesOrderItem);
			}
		} else {
			return false;
		}

		builder.put("demandId", demandId);

		String quantity = null;

		Map map15 = joinSalesHistoryAggr(salesOrderNo, salesOrderItem,
				localSubDocCatg);
		if (map15 != null) {
			localBaseQuantity = String.valueOf(map15.get("localBaseQuantity"));
		}

		if ((Double.parseDouble(salesOrderQty)
				* Integer.parseInt(localNumtoBase) / Integer
					.parseInt(localDentoBase)) > Double
				.parseDouble(localBaseQuantity)) {
			quantity = String.format(
					"%.2f",
					(Double.parseDouble(salesOrderQty)
							* Integer.parseInt(localNumtoBase) / Integer
								.parseInt(localDentoBase))
							- Double.parseDouble(localBaseQuantity));
		} else {
			return false;
		}

		builder.put("quantity", quantity);

		return true;
	}

	public Map joinCnsDemGrpAsgn(String localShipToParty, String localSalesOrg) {

		ADFCriteria adfCriteria1 = QueryHelper.buildCriteria("customerId").is(
				localShipToParty);
		ADFCriteria adfCriteria2 = QueryHelper.buildCriteria(
				"salesOrganization").is(localSalesOrg);
		ADFCriteria groupCriteria29 = adfCriteria2.and(adfCriteria1);

		ADFCriteria adfCriteria = groupCriteria29;
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

	public Map joinCnsDemGrpAsgnCustId(String customerId) {

		ADFCriteria adfCriteria3 = QueryHelper.buildCriteria("customerId").is(
				customerId);
		ADFCriteria groupCriteria30 = adfCriteria3;

		ADFCriteria adfCriteria = groupCriteria30;
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

	public Map joinProdLocMinShelf(String sourceSystem, String localPlant,
			String localMaterialNumber) {

		String key = JsonObject.create().append("sourceSystem", sourceSystem)
				.append("localPlant", localPlant)
				.append("localMaterialNumber", localMaterialNumber).toJson();
		Map map = AdfViewHelper.get("/plan/prod_loc_min_shelf", key);
		if (map != null) {
			return map;
		}

		return null;
	}

	public Map joinMaterialGlobal(String sourceSystem,
			String localMaterialNumber) {

		String key = JsonObject.create().append("sourceSystem", sourceSystem)
				.append("localMaterialNumber", localMaterialNumber).toJson();
		Map map = AdfViewHelper.get("/edm/material_global_v1", key);
		if (map != null) {
			return map;
		}

		return null;
	}

	public Map joinCnsMaterialPlanStatus(String sourceSystem,
			String localPlant, String localMaterialNumber) {

		String key = JsonObject.create().append("sourceSystem", sourceSystem)
				.append("localPlant", localPlant)
				.append("localMaterialNumber", localMaterialNumber).toJson();
		Map map = AdfViewHelper.get("/plan/cns_material_plan_status", key);
		if (map != null) {
			return map;
		}

		return null;
	}

	public Map joinCnsPlanUnit(String sourceSystem, String localBaseUom) {

		String key = JsonObject.create().append("sourceSystem", sourceSystem)
				.append("localUom", localBaseUom).toJson();
		Map map = AdfViewHelper.get("/plan/cns_plan_unit", key);
		if (map != null) {
			return map;
		}

		return null;
	}

	public Map joinSalesHistoryAggr(String salesOrderNo, String salesOrderItem,
			String localSubDocCatg) {

		String key = JsonObject.create().append("localPrecDocNo", salesOrderNo)
				.append("localSPrecDocLnNo", salesOrderItem)
				.append("localSubDocCatg", localSubDocCatg).toJson();
		Map map = AdfViewHelper.get("/edm/sales_history_v1_aggregation", key);
		if (map != null) {
			return map;
		}

		return null;
	}

	public Map joinCnsSoTypeInclExcl(String localOrderType, String localPlant,
			String localSalesOrg, String sourceSystem, String incl) {

		ADFCriteria adfCriteria17 = QueryHelper.buildCriteria("orderType").is(
				localOrderType);
		ADFCriteria adfCriteria18 = QueryHelper.buildCriteria("plant").is(
				localPlant);
		ADFCriteria adfCriteria19 = QueryHelper.buildCriteria("salesOrg").is(
				localSalesOrg);
		ADFCriteria adfCriteria20 = QueryHelper.buildCriteria("sourceSystem")
				.is(sourceSystem);
		ADFCriteria adfCriteria21 = QueryHelper.buildCriteria("inclExcl").is(
				incl);
		ADFCriteria groupCriteria31 = adfCriteria21.and(adfCriteria20)
				.and(adfCriteria19).and(adfCriteria18).and(adfCriteria17);

		ADFCriteria adfCriteria = groupCriteria31;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/plan/cns_so_type_incl_excl", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			Map.Entry<String, String> entry = retList.get(0);
			Map<String, Object> map = JsonObject.append(entry.getValue())
					.toMap();
			return map;
		}

		return null;

	}

	public Map joinCnsCustExclIncl(String sourceSystem, String localSalesOrg,
			String incl, String localShipToParty, String allShipTo) {

		ADFCriteria adfCriteria22 = QueryHelper.buildCriteria("sourceSystem")
				.is(sourceSystem);
		ADFCriteria adfCriteria23 = QueryHelper.buildCriteria("salesOrg").is(
				localSalesOrg);
		ADFCriteria adfCriteria24 = QueryHelper.buildCriteria("inclExcl").is(
				incl);
		ADFCriteria adfCriteria25 = QueryHelper.buildCriteria("customerShipTo")
				.is(localShipToParty);
		ADFCriteria adfCriteria26 = QueryHelper.buildCriteria("customerShipTo")
				.is(allShipTo);
		ADFCriteria groupCriteria32 = adfCriteria26.or(adfCriteria25)
				.and(adfCriteria24).and(adfCriteria23).and(adfCriteria22);

		ADFCriteria adfCriteria = groupCriteria32;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/plan/cns_cust_excl_incl", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			Map.Entry<String, String> entry = retList.get(0);
			Map<String, Object> map = JsonObject.append(entry.getValue())
					.toMap();
			return map;
		}

		return null;

	}

	public Map joinKnvhOnKunnr(String localShipToParty) {

		String key = JsonObject.create().append("kunnr", localShipToParty)
				.toJson();
		Map map = AdfViewHelper.get("/project_one/knvh", key);
		if (map != null) {
			return map;
		}

		return null;
	}

	public Map joinKnvhOnHkunnr(String localShipToParty) {

		String key = JsonObject.create().append("hkunnr", localShipToParty)
				.toJson();
		Map map = AdfViewHelper.get("/project_one/knvh", key);
		if (map != null) {
			return map;
		}

		return null;
	}

	private final String addSecond(String fromDueDate) {
		Calendar startDate = Calendar.getInstance();
		startDate.setTime(DateInner.offsetSecond(
				DateInner.stringToDate(fromDueDate, "yyyy/MM/dd HH:mm:ss"), 1));
		return DateInner.dateToString(startDate.getTime(),
				"yyyy/MM/dd HH:mm:ss");
	}

	private final String calcDate(String localScheduleLineDate) {
		Calendar startDate = Calendar.getInstance();
		startDate.setTime(DateInner.stringToDate(localScheduleLineDate,
				"yyyyMMdd"));
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
