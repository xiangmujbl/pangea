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
		String exclFlag = "E";
		String allFlag = "ALL";
		String salesOrderFlag = "sales_order";
		String localPlantFlag = "localPlant";
		String localOrderTypeFlag = "localOrderType";
		String localSalesOrgFlag = "localSalesOrg";
		String localShipToPartyFlag = "localShipToParty";
		String scheduleLineItem = StringInner
				.getString(map, "scheduleLineItem");
		String salesOrderQty = StringInner.getString(map, "salesOrderQty");
		String localRejReason = StringInner.getString(map, "localRejReason");
		String salesOrderNo = StringInner.getString(map, "salesOrderNo");
		String salesOrderItem = StringInner.getString(map, "salesOrderItem");
		String lineRejStat = StringInner.getString(map, "lineRejStat");
		String localNumtoBase = StringInner.getString(map, "localNumtoBase");
		String localDentoBase = StringInner.getString(map, "localDentoBase");
		String localSubDocCatg = "J";
		String localOrderType = StringInner.getString(map, "localOrderType");
		String localOrderCreateDt = StringInner.getString(map,
				"localOrderCreateDt");
		String localBaseUom = null;
		String unit = null;
		String inclusionExclusion = null;
		String demandGroup = null;
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

		Map map2 = joinCnsPlanUnit(sourceSystem, StringInner.trim(localBaseUom));
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

		if (StringInner.isStringEmpty(localSalesOrg)
				|| StringInner.isStringEmpty(localShipToParty)) {

			return false;
		}

		else {

			Map map3 = joinCnsPlanObjectFilter(salesOrderFlag, sourceSystem,
					localSalesOrgFlag, localSalesOrg, localShipToPartyFlag,
					localShipToParty, exclFlag);
			if (map3 != null) {
				inclusionExclusion = String.valueOf(map3
						.get("inclusionExclusion"));
				if (inclusionExclusion != null) {
					return false;
				}
			}
			if (StringInner.isStringEmpty(customerId)) {
				Map map4 = joinCnsPlanObjectAllFilter(salesOrderFlag,
						sourceSystem, localSalesOrgFlag, localSalesOrg,
						inclFlag, localShipToPartyFlag, localShipToParty,
						allFlag);
				if (map4 != null) {
					inclusionExclusion = String.valueOf(map4
							.get("inclusionExclusion"));
					if (inclusionExclusion == null) {
						return false;
					}
				}
				if (StringInner.isStringEmpty(customerId)) {
					Map map5 = joinCnsDemGrpAsgn(localShipToParty,
							localSalesOrg);
					if (map5 != null) {
						demandGroup = String.valueOf(map5.get("demandGroup"));
						if (StringInner.isStringNotEmpty(demandGroup)) {
							customerId = demandGroup;
						}
					}
					if (StringInner.isStringEmpty(customerId)) {
						customerId = determineCustomerId(localShipToParty,
								localSalesOrg, localOrderCreateDt);
						if (StringInner.isStringEmpty(customerId)) {

							writeFailDataToRegion(failMap, "SP",
									"GDMDemandSalesOrder", "SO5",
									"Demand Group can not be determined",
									sourceSystem, salesOrderNo, salesOrderItem,
									scheduleLineItem, localMaterialNumber, "",
									"");
							return false;
						}
					}
				}
			}
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

		Map map6 = joinProdLocMinShelf(sourceSystem, localPlant,
				localMaterialNumber);
		if (map6 != null) {
			minShelfLife = String.valueOf(map6.get("minShelfLife"));
		}

		if (minShelfLife != null) {
			minRemainingShelfLife = minShelfLife;
		} else {
			Map map7 = joinProdLocMinShelf(sourceSystem, localPlant,
					allMaterialNumber);
			if (map7 != null) {
				minShelfLife = String.valueOf(map7.get("minShelfLife"));
				if (minShelfLife != null) {
					minRemainingShelfLife = minShelfLife;
				}
			}
		}

		if (minRemainingShelfLife == null) {

			Map map8 = joinMaterialGlobal(sourceSystem, localMaterialNumber);
			if (map8 != null) {
				minRemShelfLife = String.valueOf(map8.get("minRemShelfLife"));
				if (minRemShelfLife != null) {
					minRemainingShelfLife = minRemShelfLife;
				} else {
					minRemainingShelfLife = "";
				}
			}
		}

		builder.put("minRemainingShelfLife", minRemainingShelfLife);

		String productId = null;

		Map map9 = joinCnsMaterialPlanStatus(sourceSystem, localPlant,
				localMaterialNumber);
		if (map9 != null) {
			spRelevant = String.valueOf(map9.get("spRelevant"));
			noPlanRelevant = String.valueOf(map9.get("noPlanRelevant"));
		}

		if ("X".equals(spRelevant) || "X".equals(noPlanRelevant)) {
			Map map10 = joinMaterialGlobal(sourceSystem, localMaterialNumber);
			if (map10 != null) {
				primaryPlanningCode = String.valueOf(map10
						.get("primaryPlanningCode"));
				materialNumber = String.valueOf(map10.get("materialNumber"));
				if (StringInner.isStringEmpty(primaryPlanningCode)) {
					productId = materialNumber;
				} else {
					productId = primaryPlanningCode;
				}
			}
		} else {
			return false;
		}

		inclusionExclusion = null;
		builder.put("productId", productId);

		String demandId = null;

		Map map11 = joinCnsPlanObjectFilter(salesOrderFlag, sourceSystem,
				localPlantFlag, localPlant, localOrderTypeFlag, localOrderType,
				inclFlag);
		if (map11 != null) {
			inclusionExclusion = String
					.valueOf(map11.get("inclusionExclusion"));
		}

		if (inclusionExclusion != null) {
			if (Integer.parseInt(scheduleLineItem) != 1
					|| Double.parseDouble(salesOrderQty) <= 0
					|| StringInner.isStringNotEmpty(localRejReason)
					|| "C".equals(lineRejStat)) {
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

		Map map12 = joinSalesHistoryAggr(salesOrderNo, salesOrderItem,
				localSubDocCatg);
		if (map12 != null) {
			localBaseQuantity = String.valueOf(map12.get("localBaseQuantity"));
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
		ADFCriteria groupCriteria33 = adfCriteria2.and(adfCriteria1);

		ADFCriteria adfCriteria = groupCriteria33;
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
		ADFCriteria groupCriteria34 = adfCriteria3;

		ADFCriteria adfCriteria = groupCriteria34;
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

		ADFCriteria adfCriteria12 = QueryHelper.buildCriteria("sourceSystem")
				.is(sourceSystem);
		ADFCriteria adfCriteria13 = QueryHelper.buildCriteria("localUom").is(
				localBaseUom);
		ADFCriteria groupCriteria35 = adfCriteria13.and(adfCriteria12);

		ADFCriteria adfCriteria = groupCriteria35;
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

	public List joinKnvhOnKunnr(String localShipToParty) {

		ADFCriteria adfCriteria17 = QueryHelper.buildCriteria("kunnr").is(
				localShipToParty);
		ADFCriteria groupCriteria36 = adfCriteria17;

		ADFCriteria adfCriteria = groupCriteria36;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/project_one/knvh", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			return retList;
		}

		return new ArrayList<>();

	}

	public Map joinCnsPlanObjectFilter(String sourceObjName,
			String sourceSystem, String sourceObjAttr1,
			String sourceObjAttr1Val, String sourceObjAttr2,
			String sourceObjAttr2Val, String inclFlag) {

		ADFCriteria adfCriteria18 = QueryHelper.buildCriteria(
				"sourceObjectTechName").is(sourceObjName);
		ADFCriteria adfCriteria19 = QueryHelper.buildCriteria("sourceSystem")
				.is(sourceSystem);
		ADFCriteria adfCriteria20 = QueryHelper.buildCriteria(
				"sourceObjectAttribute1").is(sourceObjAttr1);
		ADFCriteria adfCriteria21 = QueryHelper.buildCriteria(
				"sourceObjectAttribute1Value").is(sourceObjAttr1Val);
		ADFCriteria adfCriteria22 = QueryHelper.buildCriteria(
				"sourceObjectAttribute2").is(sourceObjAttr2);
		ADFCriteria adfCriteria23 = QueryHelper.buildCriteria(
				"sourceObjectAttribute2Value").is(sourceObjAttr2Val);
		ADFCriteria adfCriteria24 = QueryHelper.buildCriteria(
				"inclusionExclusion").is(inclFlag);
		ADFCriteria groupCriteria37 = adfCriteria24.and(adfCriteria23)
				.and(adfCriteria22).and(adfCriteria21).and(adfCriteria20)
				.and(adfCriteria19).and(adfCriteria18);

		ADFCriteria adfCriteria = groupCriteria37;
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

	public Map joinCnsPlanObjectAllFilter(String sourceObjName,
			String sourceSystem, String sourceObjAttr1,
			String sourceObjAttr1Val, String inclFlag, String sourceObjAttr2,
			String sourceObjAttr2Val, String allFlag) {

		ADFCriteria adfCriteria25 = QueryHelper.buildCriteria(
				"sourceObjectTechName").is(sourceObjName);
		ADFCriteria adfCriteria26 = QueryHelper.buildCriteria("sourceSystem")
				.is(sourceSystem);
		ADFCriteria adfCriteria27 = QueryHelper.buildCriteria(
				"sourceObjectAttribute1").is(sourceObjAttr1);
		ADFCriteria adfCriteria28 = QueryHelper.buildCriteria(
				"sourceObjectAttribute1Value").is(sourceObjAttr1Val);
		ADFCriteria adfCriteria29 = QueryHelper.buildCriteria(
				"inclusionExclusion").is(inclFlag);
		ADFCriteria adfCriteria30 = QueryHelper.buildCriteria(
				"sourceObjectAttribute2").is(sourceObjAttr2);
		ADFCriteria adfCriteria31 = QueryHelper.buildCriteria(
				"sourceObjectAttribute2Value").is(sourceObjAttr2Val);
		ADFCriteria adfCriteria32 = QueryHelper.buildCriteria(
				"sourceObjectAttribute2Value").is(allFlag);
		ADFCriteria groupCriteria38 = adfCriteria32.or(adfCriteria31)
				.and(adfCriteria30).and(adfCriteria29).and(adfCriteria28)
				.and(adfCriteria27).and(adfCriteria26).and(adfCriteria25);

		ADFCriteria adfCriteria = groupCriteria38;
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

	private class KnvhResHolder {
		private String customerId;
		private String hkunnr;
	}

	private final String determineCustomerId(String kunnr, String localSalesOrg, String localOrderCreateDt) {
		LogUtil.getCoreLog().info("determineCustomerId - START: kunnr:{}, localSalesOrg:{}, localOrderCreateDt:{}", kunnr, localSalesOrg, localOrderCreateDt);
		// attempt to find a match at the lowest level record in KNVH
		KnvhResHolder results = findKnvhMatch(kunnr, localSalesOrg, localOrderCreateDt);

		// if no customer is set and there is a hkunnr set - try again at the parent level
		// we stop checking when a customer is found OR when none found and no parent found
		while (StringInner.isStringEmpty(results.customerId) && !StringInner.isStringEmpty(results.hkunnr)) {
			LogUtil.getCoreLog().info("determineCustomerId - In While, trying parent: results.hkunnr:{}", results.hkunnr);
			results = findKnvhMatch(results.hkunnr, localSalesOrg, localOrderCreateDt);
		}
		LogUtil.getCoreLog().info("determineCustomerId - END: customerId:{}", results.customerId);
		return results.customerId;
	}

	private final KnvhResHolder findKnvhMatch(String kunnr, String localSalesOrg, String localOrderCreateDt) {
		KnvhResHolder results = new KnvhResHolder();
		results.customerId = null;
		results.hkunnr = null;
		LogUtil.getCoreLog().info("findKnvhMatch - START: kunnr:{}, localSalesOrg:{}, localOrderCreateDt:{}", kunnr, localSalesOrg, localOrderCreateDt);

		// find all matching knvh records based on the kunnr passed in
		List<Map.Entry<String, String>> knvhList = joinKnvhOnKunnr(kunnr);
		if (knvhList != null && knvhList.size() > 0) {
			LogUtil.getCoreLog().info("findKnvhMatch: knvhList.size():{}", knvhList.size());

			// loop over all matching knvh records
			for (Map.Entry<String, String> entry : knvhList) {
				Map<String, Object> knvhRec = JsonObject.append(
						entry.getValue()).toMap();
				if (knvhRec != null) {
					results.hkunnr = String.valueOf(knvhRec.get("hkunnr"));
					String vkorg = String.valueOf(knvhRec.get("vkorg"));
					String datbi = String.valueOf(knvhRec.get("datbi"));
					String hityp = String.valueOf(knvhRec.get("hityp"));
					LogUtil.getCoreLog().info("findKnvhMatch: vkorg:{}, datbi:{}, hkunnr:{}, hityp:{}", vkorg, datbi, results.hkunnr, hityp);

					if (vkorg.equals(localSalesOrg)
							&& "A".equals(hityp)
							&& Integer.parseInt(localOrderCreateDt) <= Integer
							.parseInt(datbi)) {

						// if we have a match on the above criteria check the demGrpAsgn region
						Map demGrpAsgnRec = joinCnsDemGrpAsgnCustId(results.hkunnr);
						if (demGrpAsgnRec != null) {
							String demandGroup = String
									.valueOf(demGrpAsgnRec
											.get("demandGroup"));
							LogUtil.getCoreLog().info("findKnvhMatch: demandGroup:{}", demandGroup);
							if (StringInner.isStringNotEmpty(demandGroup)) {
								results.customerId = demandGroup;
							}
						}
						// we found a match on the above criteria so ignore any other matching records
						break;
					}
				}
			}
		}
		LogUtil.getCoreLog().info("findKnvhMatch - END: customerId:{}, hkunnr{}", results.customerId, results.hkunnr);
		return results;
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
