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
public class OMPGdmStockInventoryStocks implements IEventProcessor {

	private final String FAILREGION = "";

	@Override
	public List<ViewResultItem> process(List<RawDataEvent> list) {

		List<ViewResultItem> resultList = new ArrayList<>();

		list.stream()
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
											.append("consignment",
													data.get("consignment"))
											.append("stockId",
													data.get("stockId"))
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
										.info("OMPGdmStockInventoryStocks Exception occured. key = {}.",
												e.getKey());
								LogUtil.getCoreLog()
										.info("OMPGdmStockInventoryStocks Exception:",
												exception);
							}

						});

		return resultList;
	}

	public boolean buildView(RawDataValue raw,
			List<RawDataBuilder> rawDataBuilderList,
			Map<String, RawDataBuilder> failMap) {

		Map map = raw.toMap();

		String localPlanningRelevant = "";

		String productId = "";

		String primaryPlanningCode = null;

		String sourceSystem = String.valueOf(map.get("sourceSystem"));

		String localSpecialStockIndicator = "";

		String localConsignmentSpecialStockIndicator = "";

		String localBatchId = "";

		String vendorOrCustomer = "V";

		String localBatchManagementRequirementIndicator = String.valueOf(map
				.get("localBatchManagementRequirementIndicator"));

		String localMaterial = "";

		String localMaterialNumber = String.valueOf(map
				.get("localMaterialNumber"));

		String localNumber = "";

		String spRelevant = null;

		String materialNumber = null;

		String localVendorNumber = "";

		String noPlanRelevant = null;

		String localPlant = String.valueOf(map.get("localPlant"));

		List<Map.Entry<String, String>> inventoryStockList = null;
		inventoryStockList = getInventoryStock(sourceSystem, localPlant,
				localMaterialNumber);
		if (inventoryStockList != null && inventoryStockList.size() > 0) {
			for (Map.Entry<String, String> inventoryStockListEntry : inventoryStockList) {
				Map<String, Object> inventoryStockListMap = JsonObject.append(
						inventoryStockListEntry.getValue()).toMap();
				localConsignmentSpecialStockIndicator = String
						.valueOf(inventoryStockListMap
								.get("localConsignmentSpecialStockIndicator"));
				localSpecialStockIndicator = String
						.valueOf(inventoryStockListMap
								.get("localSpecialStockIndicator"));
				sourceSystem = String.valueOf(inventoryStockListMap
						.get("sourceSystem"));
				localPlant = String.valueOf(inventoryStockListMap
						.get("localPlant"));
				localMaterial = String.valueOf(inventoryStockListMap
						.get("localMaterial"));
				localBatchId = String.valueOf(inventoryStockListMap
						.get("localBatchId"));
				localVendorNumber = String.valueOf(inventoryStockListMap
						.get("localVendorNumber"));

				RawDataBuilder builder = new RawDataBuilder();

				builder.put("active", "YES");
				builder.put("activeOPRERP", "YES");
				builder.put("activeSOPERP", "NO");
				builder.put("certaintyID", "");
				builder.put("eRPOrderId", "");
				builder.put("inventoryLinkGroupId", "");
				builder.put("processId", "");
				builder.put("processTypeId", "");
				builder.put("stockType", "level");

				String batchId = null;

				batchId = localBatchId;
				builder.put("batchId", batchId);

				String blockedQuantity = null;

				if (StringInner
						.isStringEmpty(localConsignmentSpecialStockIndicator)
						&& StringInner
								.isStringEmpty(localSpecialStockIndicator)) {

					blockedQuantity = OMPGdmStockInventoryStocksHook
							.getBlockedQuantity(
									localBatchManagementRequirementIndicator,
									inventoryStockList, localBatchId);
				}

				else {

					if (localConsignmentSpecialStockIndicator.equals("K")
							&& StringInner
									.isStringEmpty(localSpecialStockIndicator)) {
						blockedQuantity = OMPGdmStockInventoryStocksHook
								.getBlockedQuantity(
										localBatchManagementRequirementIndicator,
										inventoryStockList, localBatchId);
					} else {
						if (StringInner
								.isStringEmpty(localConsignmentSpecialStockIndicator)
								&& localSpecialStockIndicator.equals("O")) {
							blockedQuantity = OMPGdmStockInventoryStocksHook
									.getBlockedQuantity(
											localBatchManagementRequirementIndicator,
											inventoryStockList, localBatchId);
						}
					}
				}
				builder.put("blockedQuantity", blockedQuantity);

				String consignment = null;

				if (StringInner
						.isStringEmpty(localConsignmentSpecialStockIndicator)
						&& StringInner
								.isStringEmpty(localSpecialStockIndicator)) {

					consignment = "";
				}

				else {

					if (localConsignmentSpecialStockIndicator.equals("K")
							&& StringInner
									.isStringEmpty(localSpecialStockIndicator)) {
						consignment = "K";
					} else {
						if (StringInner
								.isStringEmpty(localConsignmentSpecialStockIndicator)
								&& localSpecialStockIndicator.equals("O")) {
							consignment = "";
						}
					}
				}
				builder.put("consignment", consignment);

				String lifnr = null;

				if (StringInner
						.isStringEmpty(localConsignmentSpecialStockIndicator)
						&& StringInner
								.isStringEmpty(localSpecialStockIndicator)) {

					lifnr = "";
				}

				else {

					if (localConsignmentSpecialStockIndicator.equals("K")
							&& StringInner
									.isStringEmpty(localSpecialStockIndicator)) {
						lifnr = "K";
					} else {
						if (StringInner
								.isStringEmpty(localConsignmentSpecialStockIndicator)
								&& localSpecialStockIndicator.equals("O")) {
							lifnr = "O";
						}
					}
				}
				builder.put("lifnr", lifnr);

				String locationId = null;

				if (StringInner.isStringEmpty(localSpecialStockIndicator)) {

					if (StringInner.isStringNotEmpty(localPlant)) {
						Map map1 = getPlantV1(localPlant);
						if (map1 != null) {
							localPlanningRelevant = String.valueOf(map1
									.get("localPlanningRelevant"));
							if (localPlanningRelevant.equals("X")) {
								locationId = StringInner.join(sourceSystem,
										"_", localPlant);
							}
						}
					}
				}

				else {

					if (localSpecialStockIndicator.equals("O")) {
						if (StringInner.isStringNotEmpty(sourceSystem)
								&& StringInner
										.isStringNotEmpty(localVendorNumber)
								&& StringInner.isStringNotEmpty(localPlant)
								&& StringInner
										.isStringNotEmpty(vendorOrCustomer)) {
							Map map2 = getCnsSplPlnLocWithLocalPlant(
									sourceSystem, localVendorNumber,
									localPlant, vendorOrCustomer);
							if (map2 != null) {
								localNumber = String.valueOf(map2
										.get("localNumber"));
								if (StringInner.isStringNotEmpty(localNumber)) {
									locationId = StringInner.join(sourceSystem,
											"_", localPlant, "$", localNumber);
								}
							}
						}
					}
				}
				builder.put("locationId", locationId);

				if (StringInner.isStringNotEmpty(localMaterial)
						&& StringInner.isStringNotEmpty(localPlant)
						&& StringInner.isStringNotEmpty(sourceSystem)) {

					Map map3 = getCnsMaterialPlanStatus(localMaterial,
							localPlant, sourceSystem);
					if (map3 != null) {
						spRelevant = String.valueOf(map3.get("spRelevant"));
						noPlanRelevant = String.valueOf(map3
								.get("noPlanRelevant"));
						if (spRelevant.equals("X")
								|| noPlanRelevant.equals("X")) {
							Map map4 = getMaterialGlobalV1(sourceSystem,
									localMaterial);
							if (map4 != null) {
								primaryPlanningCode = String.valueOf(map4
										.get("primaryPlanningCode"));
								materialNumber = String.valueOf(map4
										.get("materialNumber"));
								if (StringInner
										.isStringNotEmpty(primaryPlanningCode)) {
									productId = primaryPlanningCode;
								} else {
									productId = materialNumber;
								}
							}
						}
					}
				}

				builder.put("productId", productId);

				String qualityQuantity = null;

				if (StringInner
						.isStringEmpty(localConsignmentSpecialStockIndicator)
						&& StringInner
								.isStringEmpty(localSpecialStockIndicator)) {

					qualityQuantity = OMPGdmStockInventoryStocksHook
							.getQualityQuantity(
									localBatchManagementRequirementIndicator,
									inventoryStockList, localBatchId);
				}

				else {

					if (localConsignmentSpecialStockIndicator.equals("K")
							&& StringInner
									.isStringEmpty(localSpecialStockIndicator)) {
						qualityQuantity = OMPGdmStockInventoryStocksHook
								.getQualityQuantity(
										localBatchManagementRequirementIndicator,
										inventoryStockList, localBatchId);
					} else {
						if (StringInner
								.isStringEmpty(localConsignmentSpecialStockIndicator)
								&& localSpecialStockIndicator.equals("O")) {
							qualityQuantity = OMPGdmStockInventoryStocksHook
									.getQualityQuantity(
											localBatchManagementRequirementIndicator,
											inventoryStockList, localBatchId);
						}
					}
				}
				builder.put("qualityQuantity", qualityQuantity);

				String quantity = null;

				if (StringInner
						.isStringEmpty(localConsignmentSpecialStockIndicator)
						&& StringInner
								.isStringEmpty(localSpecialStockIndicator)) {

					quantity = OMPGdmStockInventoryStocksHook.getQuantity(
							localBatchManagementRequirementIndicator,
							inventoryStockList, localBatchId);
				}

				else {

					if (localConsignmentSpecialStockIndicator.equals("K")
							&& StringInner
									.isStringEmpty(localSpecialStockIndicator)) {
						quantity = OMPGdmStockInventoryStocksHook.getQuantity(
								localBatchManagementRequirementIndicator,
								inventoryStockList, localBatchId);
					} else {
						if (StringInner
								.isStringEmpty(localConsignmentSpecialStockIndicator)
								&& localSpecialStockIndicator.equals("O")) {
							quantity = OMPGdmStockInventoryStocksHook
									.getQuantity(
											localBatchManagementRequirementIndicator,
											inventoryStockList, localBatchId);
						}
					}
				}
				builder.put("quantity", quantity);

				String receiptDate = null;

				receiptDate = OMPGdmStockInventoryStocksHook.stampToDate(String
						.valueOf(System.currentTimeMillis()));

				builder.put("receiptDate", receiptDate);

				String restrictedQuantity = null;

				if (StringInner
						.isStringEmpty(localConsignmentSpecialStockIndicator)
						&& StringInner
								.isStringEmpty(localSpecialStockIndicator)) {

					restrictedQuantity = OMPGdmStockInventoryStocksHook
							.getRestrictedQuantity(
									localBatchManagementRequirementIndicator,
									inventoryStockList, localBatchId);
				}

				else {

					if (localConsignmentSpecialStockIndicator.equals("K")
							&& StringInner
									.isStringEmpty(localSpecialStockIndicator)) {
						restrictedQuantity = OMPGdmStockInventoryStocksHook
								.getRestrictedQuantity(
										localBatchManagementRequirementIndicator,
										inventoryStockList, localBatchId);
					} else {
						if (StringInner
								.isStringEmpty(localConsignmentSpecialStockIndicator)
								&& localSpecialStockIndicator.equals("O")) {
							restrictedQuantity = OMPGdmStockInventoryStocksHook
									.getRestrictedQuantity(
											localBatchManagementRequirementIndicator,
											inventoryStockList, localBatchId);
						}
					}
				}
				builder.put("restrictedQuantity", restrictedQuantity);

				String returnsQuantity = null;

				if (StringInner
						.isStringEmpty(localConsignmentSpecialStockIndicator)
						&& StringInner
								.isStringEmpty(localSpecialStockIndicator)) {

					returnsQuantity = OMPGdmStockInventoryStocksHook
							.getReturnsQuantity(
									localBatchManagementRequirementIndicator,
									inventoryStockList, localBatchId);
				}

				else {

					if (localConsignmentSpecialStockIndicator.equals("K")
							&& StringInner
									.isStringEmpty(localSpecialStockIndicator)) {
						returnsQuantity = OMPGdmStockInventoryStocksHook
								.getReturnsQuantity(
										localBatchManagementRequirementIndicator,
										inventoryStockList, localBatchId);
					} else {
						if (StringInner
								.isStringEmpty(localConsignmentSpecialStockIndicator)
								&& localSpecialStockIndicator.equals("O")) {
							returnsQuantity = OMPGdmStockInventoryStocksHook
									.getReturnsQuantity(
											localBatchManagementRequirementIndicator,
											inventoryStockList, localBatchId);
						}
					}
				}
				builder.put("returnsQuantity", returnsQuantity);

				String startDate = null;

				startDate = OMPGdmStockInventoryStocksHook.stampToDate(String
						.valueOf(System.currentTimeMillis()));

				builder.put("startDate", startDate);

				String transferQuantity = null;

				if (StringInner
						.isStringEmpty(localConsignmentSpecialStockIndicator)
						&& StringInner
								.isStringEmpty(localSpecialStockIndicator)) {

					transferQuantity = OMPGdmStockInventoryStocksHook
							.getTransferQuantity(
									localBatchManagementRequirementIndicator,
									inventoryStockList, localBatchId);
				}

				else {

					if (localConsignmentSpecialStockIndicator.equals("K")
							&& StringInner
									.isStringEmpty(localSpecialStockIndicator)) {
						transferQuantity = OMPGdmStockInventoryStocksHook
								.getTransferQuantity(
										localBatchManagementRequirementIndicator,
										inventoryStockList, localBatchId);
					} else {
						if (StringInner
								.isStringEmpty(localConsignmentSpecialStockIndicator)
								&& localSpecialStockIndicator.equals("O")) {
							transferQuantity = OMPGdmStockInventoryStocksHook
									.getTransferQuantity(
											localBatchManagementRequirementIndicator,
											inventoryStockList, localBatchId);
						}
					}
				}
				builder.put("transferQuantity", transferQuantity);

				String transitDate = null;

				transitDate = OMPGdmStockInventoryStocksHook.stampToDate(String
						.valueOf(System.currentTimeMillis()));

				builder.put("transitDate", transitDate);

				String unrestrictedQuantity = null;

				if (StringInner
						.isStringEmpty(localConsignmentSpecialStockIndicator)
						&& StringInner
								.isStringEmpty(localSpecialStockIndicator)) {

					unrestrictedQuantity = OMPGdmStockInventoryStocksHook
							.getQuantity(
									localBatchManagementRequirementIndicator,
									inventoryStockList, localBatchId);
				}

				else {

					if (localConsignmentSpecialStockIndicator.equals("K")
							&& StringInner
									.isStringEmpty(localSpecialStockIndicator)) {
						unrestrictedQuantity = OMPGdmStockInventoryStocksHook
								.getQuantity(
										localBatchManagementRequirementIndicator,
										inventoryStockList, localBatchId);
					} else {
						if (StringInner
								.isStringEmpty(localConsignmentSpecialStockIndicator)
								&& localSpecialStockIndicator.equals("O")) {
							unrestrictedQuantity = OMPGdmStockInventoryStocksHook
									.getQuantity(
											localBatchManagementRequirementIndicator,
											inventoryStockList, localBatchId);
						}
					}
				}
				builder.put("unrestrictedQuantity", unrestrictedQuantity);

				String stockId = null;

				if (qualityQuantity.equals("0.0") && quantity.equals("0.0")
						&& restrictedQuantity.equals("0.0")
						&& returnsQuantity.equals("0.0")
						&& transferQuantity.equals("0.0")
						&& unrestrictedQuantity.equals("0.0")
						&& blockedQuantity.equals("0.0")) {

					return false;
				}

				else {

					if (StringInner.isStringNotEmpty(localMaterial)
							&& StringInner.isStringNotEmpty(sourceSystem)) {
						Map map5 = getMaterialGlobalV1(sourceSystem,
								localMaterial);
						if (map5 != null) {
							primaryPlanningCode = String.valueOf(map5
									.get("primaryPlanningCode"));
							materialNumber = String.valueOf(map5
									.get("materialNumber"));
							if (StringInner
									.isStringNotEmpty(primaryPlanningCode)) {
								productId = primaryPlanningCode;
							} else {
								productId = materialNumber;
							}
						}
						if (StringInner
								.isStringEmpty(localConsignmentSpecialStockIndicator)
								&& StringInner
										.isStringEmpty(localSpecialStockIndicator)
								&& StringInner.isStringNotEmpty(productId)) {
							stockId = StringInner.join(productId, "/",
									sourceSystem, "_", localPlant, "/",
									localBatchId);
						} else {
							if (localConsignmentSpecialStockIndicator
									.equals("K")
									&& StringInner
											.isStringEmpty(localSpecialStockIndicator)
									&& StringInner.isStringNotEmpty(productId)) {
								stockId = StringInner.join(productId, "/",
										sourceSystem, "_", localPlant, "/",
										localBatchId, "/", "K");
							} else {
								if (StringInner
										.isStringEmpty(localConsignmentSpecialStockIndicator)
										&& localSpecialStockIndicator
												.equals("O")
										&& StringInner
												.isStringNotEmpty(productId)) {
									if (StringInner
											.isStringNotEmpty(sourceSystem)
											&& StringInner
													.isStringNotEmpty(localVendorNumber)
											&& StringInner
													.isStringNotEmpty(vendorOrCustomer)) {
										Map map6 = getCnsSplPlnLoc(
												sourceSystem,
												localVendorNumber,
												vendorOrCustomer);
										if (map6 != null) {
											localNumber = String.valueOf(map6
													.get("localNumber"));
											if (StringInner
													.isStringNotEmpty(localNumber)) {
												stockId = StringInner.join(
														productId, "/",
														sourceSystem, "_",
														localPlant, "$",
														localNumber, "/",
														localBatchId);
											}
										}
									}
								}
							}
						}
					}
				}
				builder.put("stockId", stockId);

				rawDataBuilderList.add(builder);
			}
		}

		return true;
	}

	public List getInventoryStock(String sourceSystem, String localPlant,
			String localMaterialNumber) {

		ADFCriteria adfCriteria1 = QueryHelper.buildCriteria("sourceSystem")
				.is(sourceSystem);
		ADFCriteria adfCriteria2 = QueryHelper.buildCriteria("localPlant").is(
				localPlant);
		ADFCriteria adfCriteria3 = QueryHelper.buildCriteria("localMaterial")
				.is(localMaterialNumber);
		ADFCriteria groupCriteria17 = adfCriteria3.and(adfCriteria2).and(
				adfCriteria1);

		ADFCriteria adfCriteria = groupCriteria17;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/edm/inventory_stock_v1", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			return retList;
		}

		return new ArrayList<>();

	}

	public Map getMaterialGlobalV1(String sourceSystem, String localMaterial) {

		ADFCriteria adfCriteria4 = QueryHelper.buildCriteria("sourceSystem")
				.is(sourceSystem);
		ADFCriteria adfCriteria5 = QueryHelper.buildCriteria(
				"localMaterialNumber").is(localMaterial);
		ADFCriteria groupCriteria18 = adfCriteria5.and(adfCriteria4);

		ADFCriteria adfCriteria = groupCriteria18;
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

	public Map getPlantV1(String localPlant) {

		ADFCriteria adfCriteria6 = QueryHelper.buildCriteria("localPlant").is(
				localPlant);
		ADFCriteria groupCriteria19 = adfCriteria6;

		ADFCriteria adfCriteria = groupCriteria19;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/edm/plant_v1", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			Map.Entry<String, String> entry = retList.get(0);
			Map<String, Object> map = JsonObject.append(entry.getValue())
					.toMap();
			return map;
		}

		return null;

	}

	public Map getCnsSplPlnLoc(String sourceSystem, String localVendorNumber,
			String vendorOrCustomer) {

		ADFCriteria adfCriteria7 = QueryHelper.buildCriteria("sourceSystem")
				.is(sourceSystem);
		ADFCriteria adfCriteria8 = QueryHelper.buildCriteria("localNumber").is(
				localVendorNumber);
		ADFCriteria adfCriteria9 = QueryHelper
				.buildCriteria("vendorOrCustomer").is(vendorOrCustomer);
		ADFCriteria groupCriteria20 = adfCriteria9.and(adfCriteria8).and(
				adfCriteria7);

		ADFCriteria adfCriteria = groupCriteria20;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/plan/cns_spl_pln_loc", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			Map.Entry<String, String> entry = retList.get(0);
			Map<String, Object> map = JsonObject.append(entry.getValue())
					.toMap();
			return map;
		}

		return null;

	}

	public Map getCnsSplPlnLocWithLocalPlant(String sourceSystem,
			String localVendorNumber, String localPlant, String vendorOrCustomer) {

		ADFCriteria adfCriteria10 = QueryHelper.buildCriteria("sourceSystem")
				.is(sourceSystem);
		ADFCriteria adfCriteria11 = QueryHelper.buildCriteria("localNumber")
				.is(localVendorNumber);
		ADFCriteria adfCriteria12 = QueryHelper.buildCriteria("localPlant").is(
				localPlant);
		ADFCriteria adfCriteria13 = QueryHelper.buildCriteria(
				"vendorOrCustomer").is(vendorOrCustomer);
		ADFCriteria groupCriteria21 = adfCriteria13.and(adfCriteria12)
				.and(adfCriteria11).and(adfCriteria10);

		ADFCriteria adfCriteria = groupCriteria21;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/plan/cns_spl_pln_loc", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			Map.Entry<String, String> entry = retList.get(0);
			Map<String, Object> map = JsonObject.append(entry.getValue())
					.toMap();
			return map;
		}

		return null;

	}

	public Map getCnsMaterialPlanStatus(String localMaterial,
			String localPlant, String sourceSystem) {

		ADFCriteria adfCriteria14 = QueryHelper.buildCriteria(
				"localMaterialNumber").is(localMaterial);
		ADFCriteria adfCriteria15 = QueryHelper.buildCriteria("localPlant").is(
				localPlant);
		ADFCriteria adfCriteria16 = QueryHelper.buildCriteria("sourceSystem")
				.is(sourceSystem);
		ADFCriteria groupCriteria22 = adfCriteria16.and(adfCriteria15).and(
				adfCriteria14);

		ADFCriteria adfCriteria = groupCriteria22;
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
