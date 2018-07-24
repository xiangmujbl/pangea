package com.jnj.pangea.omp.gdm_inventory_stocks;

import com.jnj.adf.client.api.JsonObject;
import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.curation.logic.IEventProcessor;
import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.adf.grid.data.raw.RawDataBuilder;
import com.jnj.adf.grid.indexer.lucene.AdfLuceneHelper;
import com.jnj.adf.grid.view.common.AdfViewHelper;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.hook.OMPGdmStockInventoryStocksHook;
import org.apache.commons.lang3.StringUtils;
import com.jnj.adf.client.api.ADFCriteria;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.inner.StringInner;

import java.util.*;

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

		String sourceSystem = StringInner.getString(map, "sourceSystem");

		String localPlant = StringInner.getString(map, "localPlant");

		String localBatchManagementRequirementIndicator = StringInner
				.getString(map, "localBatchManagementRequirementIndicator");

		String localMaterialNumber = StringInner.getString(map,
				"localMaterialNumber");

		String vendorOrCustomer = "V";

		String productId = "";

		String localNumber = "";

		String localPlanningRelevant = "";

		String triangulationDetail = "Yes";

		String trigSysTransaction = "Purchase Order";

		String localSourceSystem = "Project_One";

		String spRelevant = null;

		String noPlanRelevant = null;

		String primaryPlanningCode = null;

		String materialNumber = null;

		String localConsignmentSpecialStockIndicator = "";

		String localSpecialStockIndicator = "";

		String localMaterial = "";

		String localBatchId = "";

		String localVendorNumber = "";

		String localStorageLocation = "";

		String sequenceNumber = "";

		String tlaneName = "";

		List<Map.Entry<String, String>> inventoryStockList = null;
		if (StringInner.isStringNotEmpty(sourceSystem)
				&& StringInner.isStringNotEmpty(localPlant)
				&& StringInner.isStringNotEmpty(localMaterialNumber)) {
			inventoryStockList = getInventoryStock(sourceSystem, localPlant,
					localMaterialNumber);
			if (inventoryStockList != null && inventoryStockList.size() > 0) {
				for (Map.Entry<String, String> inventoryStockListEntry : inventoryStockList) {
					Map<String, Object> inventoryStockListMap = JsonObject
							.append(inventoryStockListEntry.getValue()).toMap();
					localConsignmentSpecialStockIndicator = StringInner
							.getString(inventoryStockListMap,
									"localConsignmentSpecialStockIndicator");
					localSpecialStockIndicator = StringInner
							.getString(inventoryStockListMap,
									"localSpecialStockIndicator");
					sourceSystem = StringInner.getString(inventoryStockListMap,
							"sourceSystem");
					localPlant = StringInner.getString(inventoryStockListMap,
							"localPlant");
					localMaterial = StringInner.getString(
							inventoryStockListMap, "localMaterial");
					localBatchId = StringInner.getString(inventoryStockListMap,
							"localBatchId");
					localVendorNumber = StringInner.getString(
							inventoryStockListMap, "localVendorNumber");
					localStorageLocation = StringInner.getString(
							inventoryStockListMap, "localStorageLocation");

					RawDataBuilder builder = new RawDataBuilder();

					builder.put("active", "YES");
					builder.put("activeOPRERP", "YES");
					builder.put("activeSOPERP", "NO");
					builder.put("certaintyId", "");
					builder.put("eRPOrderId", "");
					builder.put("inventoryLinkGroupId", "");
					builder.put("processTypeId", "");
					builder.put("stockType", "level");

					String locationId = null;

					Map map1 = getSourceSystemV1(sourceSystem, localSourceSystem);
					if (map1 != null) {

						if (StringUtils.isNotEmpty(localPlant)) {
							List<Map.Entry<String, String>> cnsTlaneControlList  = getCnsTlaneControl(sourceSystem,
									localPlant, triangulationDetail,
									trigSysTransaction);
							if (cnsTlaneControlList != null && cnsTlaneControlList.size() > 0) {
								for (Map.Entry<String, String> cnsTlaneControlListEntry : cnsTlaneControlList) {
									Map<String, Object> cnsTlaneControlListMap = JsonObject
											.append(cnsTlaneControlListEntry.getValue()).toMap();
									sequenceNumber = StringInner
											.getString(cnsTlaneControlListMap,
													"sequenceNumber");
									tlaneName = StringInner
											.getString(cnsTlaneControlListMap,
													"tlaneName");
									if (StringInner.isStringNotEmpty(sequenceNumber)
											&& StringInner.isStringNotEmpty(tlaneName)) {
										List<Map.Entry<String, String>> cnsTlaneControlTriangulationList = getCnsTlaneControlTriangulation(
												sequenceNumber, tlaneName);
										if (StringInner
												.isListNotNullWithSize(cnsTlaneControlTriangulationList)) {
											localPlant = OMPGdmStockInventoryStocksHook
													.getLocalPlantByTriangulation(cnsTlaneControlTriangulationList);
										}
									}
								}
							}
						}
					} else {
						return false;

					}

					if (StringInner.isStringEmpty(localSpecialStockIndicator)) {
						if (StringUtils.isNotEmpty(localPlant)) {
							Map map3 = getPlantV1(localPlant);
							if (map3 != null) {
								localPlanningRelevant = StringInner.getString(
										map3, "localPlanningRelevant");
								if (localPlanningRelevant.equals("X")) {
									locationId = StringInner.join(sourceSystem,
											"_", localPlant);
								} else {
									return false;
								}
							}
						} else {
							return false;
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
								Map map4 = getCnsSplPlnLocWithLocalPlant(
										sourceSystem, localVendorNumber,
										localPlant, vendorOrCustomer);
								if (map4 != null) {
									localNumber = StringInner.getString(map4,
											"localNumber");
									if (StringUtils.isNotEmpty(localNumber)) {
										locationId = sourceSystem + localPlant + localNumber.replaceFirst("^0*", "");
									} else {
										return false;
									}
								}
							} else {
								return false;
							}
						}
					}
					builder.put("locationId", locationId);


					String blockedQuantity = null;

					if (StringInner
							.isStringEmpty(localConsignmentSpecialStockIndicator)
							&& StringInner
							.isStringEmpty(localSpecialStockIndicator)) {

						blockedQuantity = OMPGdmStockInventoryStocksHook
								.getBlockedQuantity(
										localBatchManagementRequirementIndicator,
										inventoryStockList, localBatchId,
										localVendorNumber);
					}

					else {

						if (localConsignmentSpecialStockIndicator.equals("K")
								&& StringInner
								.isStringEmpty(localSpecialStockIndicator)) {
							blockedQuantity = OMPGdmStockInventoryStocksHook
									.getBlockedQuantity(
											localBatchManagementRequirementIndicator,
											inventoryStockList, localBatchId,
											localVendorNumber);
						} else {
							if (StringInner
									.isStringEmpty(localConsignmentSpecialStockIndicator)
									&& localSpecialStockIndicator.equals("O")) {
								blockedQuantity = OMPGdmStockInventoryStocksHook
										.getBlockedQuantity(
												localBatchManagementRequirementIndicator,
												inventoryStockList,
												localBatchId, localVendorNumber);
							}
						}
					}
					builder.put("blockedQuantity", blockedQuantity);

					String consignment = null;

					if (StringInner
							.isStringEmpty(localConsignmentSpecialStockIndicator)
							&& StringInner
							.isStringEmpty(localSpecialStockIndicator)) {

						consignment = "NO";
					}

					else {

						if (localConsignmentSpecialStockIndicator.equals("K")
								&& StringInner
								.isStringEmpty(localSpecialStockIndicator)) {
							consignment = "YES";
						} else {
							if (StringInner
									.isStringEmpty(localConsignmentSpecialStockIndicator)
									&& localSpecialStockIndicator.equals("O")) {
								consignment = "NO";
							}
						}
					}
					builder.put("consignment", consignment);

					String vendorId = null;

					if (StringInner
							.isStringEmpty(localConsignmentSpecialStockIndicator)
							&& StringInner
							.isStringEmpty(localSpecialStockIndicator)) {

						vendorId = "";
					}

					else {

						vendorId = localVendorNumber.replaceFirst("^0*", "");
					}
					builder.put("vendorId", vendorId);



					if (StringInner.isStringNotEmpty(localMaterial)
							&& StringInner.isStringNotEmpty(localPlant)
							&& StringInner.isStringNotEmpty(sourceSystem)) {

						Map map3 = getCnsMaterialPlanStatus(localMaterial,
								localPlant, sourceSystem);
						if (map3 != null) {
							spRelevant = StringInner.getString(map3,
									"spRelevant");
							noPlanRelevant = StringInner.getString(map3,
									"noPlanRelevant");
							if (spRelevant.equals("X")
									|| noPlanRelevant.equals("X")) {
								Map map4 = getMaterialGlobalV1(sourceSystem,
										localMaterial);
								if (map4 != null) {
									primaryPlanningCode = StringInner
											.getString(map4,
													"primaryPlanningCode");
									materialNumber = StringInner.getString(
											map4, "materialNumber");
									if (StringInner
											.isStringNotEmpty(primaryPlanningCode)) {
										productId = primaryPlanningCode;
									} else {
										if (StringInner
												.isStringNotEmpty(materialNumber)) {
											productId = materialNumber;
										} else {
											return false;
										}
									}
								}
							} else {
								return false;
							}
						}
					}

					else {
						return false;
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
										inventoryStockList, localBatchId,
										localVendorNumber);
					}

					else {

						if (localConsignmentSpecialStockIndicator.equals("K")
								&& StringInner
								.isStringEmpty(localSpecialStockIndicator)) {
							qualityQuantity = OMPGdmStockInventoryStocksHook
									.getQualityQuantity(
											localBatchManagementRequirementIndicator,
											inventoryStockList, localBatchId,
											localVendorNumber);
						} else {
							if (StringInner
									.isStringEmpty(localConsignmentSpecialStockIndicator)
									&& localSpecialStockIndicator.equals("O")) {
								qualityQuantity = OMPGdmStockInventoryStocksHook
										.getQualityQuantity(
												localBatchManagementRequirementIndicator,
												inventoryStockList,
												localBatchId, localVendorNumber);
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
								inventoryStockList, localBatchId,
								localVendorNumber);
					}

					else {

						if (localConsignmentSpecialStockIndicator.equals("K")
								&& StringInner
								.isStringEmpty(localSpecialStockIndicator)) {
							quantity = OMPGdmStockInventoryStocksHook
									.getQuantity(
											localBatchManagementRequirementIndicator,
											inventoryStockList, localBatchId,
											localVendorNumber);
						} else {
							if (StringInner
									.isStringEmpty(localConsignmentSpecialStockIndicator)
									&& localSpecialStockIndicator.equals("O")) {
								quantity = OMPGdmStockInventoryStocksHook
										.getQuantity(
												localBatchManagementRequirementIndicator,
												inventoryStockList,
												localBatchId, localVendorNumber);
							}
						}
					}
					builder.put("quantity", quantity);

					String receiptDate = null;

					receiptDate = OMPGdmStockInventoryStocksHook
							.stampToDate(String.valueOf(System
									.currentTimeMillis()));

					builder.put("receiptDate", receiptDate);

					String restrictedQuantity = null;

					if (StringInner
							.isStringEmpty(localConsignmentSpecialStockIndicator)
							&& StringInner
							.isStringEmpty(localSpecialStockIndicator)) {

						restrictedQuantity = OMPGdmStockInventoryStocksHook
								.getRestrictedQuantity(
										localBatchManagementRequirementIndicator,
										inventoryStockList, localBatchId,
										localVendorNumber);
					}

					else {

						if (localConsignmentSpecialStockIndicator.equals("K")
								&& StringInner
								.isStringEmpty(localSpecialStockIndicator)) {
							restrictedQuantity = OMPGdmStockInventoryStocksHook
									.getRestrictedQuantity(
											localBatchManagementRequirementIndicator,
											inventoryStockList, localBatchId,
											localVendorNumber);
						} else {
							if (StringInner
									.isStringEmpty(localConsignmentSpecialStockIndicator)
									&& localSpecialStockIndicator.equals("O")) {
								restrictedQuantity = OMPGdmStockInventoryStocksHook
										.getRestrictedQuantity(
												localBatchManagementRequirementIndicator,
												inventoryStockList,
												localBatchId, localVendorNumber);
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
												inventoryStockList,
												localBatchId);
							}
						}
					}
					builder.put("returnsQuantity", returnsQuantity);

					String startDate = null;

					if (StringInner.isStringNotEmpty(productId)
							&& StringInner.isStringNotEmpty(locationId)) {

						startDate = OMPGdmStockInventoryStocksHook
								.stampToDate(String.valueOf(System
										.currentTimeMillis()));
					}

					else {
						return false;
					}
					builder.put("startDate", startDate);

					String transferQuantity = null;

					if (StringInner
							.isStringEmpty(localConsignmentSpecialStockIndicator)
							&& StringInner
							.isStringEmpty(localSpecialStockIndicator)) {

						transferQuantity = OMPGdmStockInventoryStocksHook
								.getTransferQuantity(
										localBatchManagementRequirementIndicator,
										inventoryStockList, localBatchId,
										localVendorNumber);
					}

					else {

						if (localConsignmentSpecialStockIndicator.equals("K")
								&& StringInner
								.isStringEmpty(localSpecialStockIndicator)) {
							transferQuantity = OMPGdmStockInventoryStocksHook
									.getTransferQuantity(
											localBatchManagementRequirementIndicator,
											inventoryStockList, localBatchId,
											localVendorNumber);
						} else {
							if (StringInner
									.isStringEmpty(localConsignmentSpecialStockIndicator)
									&& localSpecialStockIndicator.equals("O")) {
								transferQuantity = OMPGdmStockInventoryStocksHook
										.getTransferQuantity(
												localBatchManagementRequirementIndicator,
												inventoryStockList,
												localBatchId, localVendorNumber);
							}
						}
					}
					builder.put("transferQuantity", transferQuantity);

					String transitDate = null;

					transitDate = OMPGdmStockInventoryStocksHook
							.stampToDate(String.valueOf(System
									.currentTimeMillis()));

					builder.put("transitDate", transitDate);

					String unrestrictedQuantity = null;

					unrestrictedQuantity = quantity;
					builder.put("unrestrictedQuantity", unrestrictedQuantity);

					String stockId = null;

					if (qualityQuantity.equals("0") && quantity.equals("0")
							&& restrictedQuantity.equals("0")
							&& returnsQuantity.equals("0")
							&& transferQuantity.equals("0")
							&& unrestrictedQuantity.equals("0")
							&& blockedQuantity.equals("0")) {

						return false;
					}

					else {

						if (StringInner.isStringNotEmpty(localMaterial)
								&& StringInner.isStringNotEmpty(sourceSystem)) {
							Map map5 = getMaterialGlobalV1(sourceSystem,
									localMaterial);
							if (map5 != null) {
								primaryPlanningCode = StringInner.getString(
										map5, "primaryPlanningCode");
								materialNumber = StringInner.getString(map5,
										"materialNumber");
								if (StringInner
										.isStringNotEmpty(primaryPlanningCode)) {
									productId = primaryPlanningCode;
								} else {
									if (StringInner
											.isStringNotEmpty(materialNumber)) {
										productId = materialNumber;
									} else {
										return false;
									}
								}
							}
							if (StringInner
									.isStringEmpty(localConsignmentSpecialStockIndicator)
									&& StringInner
									.isStringEmpty(localSpecialStockIndicator)) {
								if (StringInner.isStringNotEmpty(productId)
										&& StringInner
										.isStringNotEmpty(sourceSystem)
										&& StringInner
										.isStringNotEmpty(localPlant)
										&& StringInner
										.isStringNotEmpty(localBatchId)) {
									stockId = StringInner.join(productId, "/",
											sourceSystem, "_", localPlant, "/",
											localBatchId);
								} else {
									if (StringInner.isStringNotEmpty(productId)
											&& StringInner
											.isStringNotEmpty(sourceSystem)
											&& StringInner
											.isStringNotEmpty(localPlant)
											&& StringInner
											.isStringEmpty(localBatchId)) {
										stockId = StringInner.join(productId,
												"/", sourceSystem, "_",
												localPlant);
									}
								}
							} else {
								if (localConsignmentSpecialStockIndicator
										.equals("K")
										&& StringInner
										.isStringEmpty(localSpecialStockIndicator)) {
									if (StringInner.isStringNotEmpty(productId)
											&& StringInner
											.isStringNotEmpty(sourceSystem)
											&& StringInner
											.isStringNotEmpty(localPlant)
											&& StringInner
											.isStringNotEmpty(localBatchId)) {
										stockId = StringInner.join(productId,
												"/", sourceSystem, "_",
												localPlant, "/", localBatchId,
												"/", "K");
									} else {
										return false;
									}
								} else {
									if (StringInner
											.isStringEmpty(localConsignmentSpecialStockIndicator)
											&& localSpecialStockIndicator
											.equals("O")) {
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
												localNumber = StringInner
														.getString(map6,
																"localNumber").replaceFirst("^0*", "");
												if (StringInner
														.isStringNotEmpty(localNumber)) {
													if (StringInner
															.isStringNotEmpty(productId)
															&& StringInner
															.isStringNotEmpty(sourceSystem)
															&& StringInner
															.isStringNotEmpty(localPlant)
															&& StringInner
															.isStringNotEmpty(localBatchId)) {
														stockId = StringInner
																.join(productId,
																		"/",
																		sourceSystem,
																		"_",
																		localPlant,
																		"$",
																		localNumber,
																		"/",
																		localBatchId);
													} else {
														return false;
													}
												} else {
													return false;
												}
											}
										} else {
											return false;
										}
									}
								}
							}
						} else {
							return false;
						}
					}
					builder.put("stockId", stockId);


					String batchId = null;

                    if (StringInner.isStringNotEmpty(localMaterial)
                            && StringInner.isStringNotEmpty(sourceSystem)) {
                        Map map5 = getMaterialGlobalV1(sourceSystem,
                                localMaterial);
                        if (map5 != null) {
                            primaryPlanningCode = StringInner.getString(
                                    map5, "primaryPlanningCode");
                            materialNumber = StringInner.getString(map5,
                                    "materialNumber");
                            if (StringInner
                                    .isStringNotEmpty(primaryPlanningCode)) {
                                productId = primaryPlanningCode;
                            } else {
                                if (StringInner
                                        .isStringNotEmpty(materialNumber)) {
                                    productId = materialNumber;
                                } else {
                                    return false;
                                }
                            }
                        }
                        if (StringInner
                                .isStringEmpty(localConsignmentSpecialStockIndicator)
                                && StringInner
                                .isStringEmpty(localSpecialStockIndicator)) {
                            if (StringInner.isStringNotEmpty(productId)
                                    && StringInner
                                    .isStringNotEmpty(sourceSystem)
                                    && StringInner
                                    .isStringNotEmpty(localPlant)
                                    && StringInner
                                    .isStringNotEmpty(localBatchId)) {
                                batchId = StringInner.join(productId, "/",
                                        sourceSystem, "_", localPlant, "/",
                                        localBatchId);
                            } else {
                                if (StringInner.isStringNotEmpty(productId)
                                        && StringInner
                                        .isStringNotEmpty(sourceSystem)
                                        && StringInner
                                        .isStringNotEmpty(localPlant)
                                        && StringInner
                                        .isStringEmpty(localBatchId)) {
                                    batchId = StringInner.join(productId,
                                            "/", sourceSystem, "_",
                                            localPlant);
                                }
                            }
                        } else {
                            if (localConsignmentSpecialStockIndicator
                                    .equals("K")
                                    && StringInner
                                    .isStringEmpty(localSpecialStockIndicator)) {
                                if (StringInner.isStringNotEmpty(productId)
                                        && StringInner
                                        .isStringNotEmpty(sourceSystem)
                                        && StringInner
                                        .isStringNotEmpty(localPlant)
                                        && StringInner
                                        .isStringNotEmpty(localBatchId)) {
                                    batchId = StringInner.join(productId,
                                            "/", sourceSystem, "_",
                                            localPlant, "/", localBatchId);
                                } else {
                                    return false;
                                }
                            } else {
                                if (StringInner
                                        .isStringEmpty(localConsignmentSpecialStockIndicator)
                                        && localSpecialStockIndicator
                                        .equals("O")) {
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
                                            localNumber = StringInner
                                                    .getString(map6,
                                                            "localNumber").replaceFirst("^0*", "");
                                            if (StringInner
                                                    .isStringNotEmpty(localNumber)) {
                                                if (StringInner
                                                        .isStringNotEmpty(productId)
                                                        && StringInner
                                                        .isStringNotEmpty(sourceSystem)
                                                        && StringInner
                                                        .isStringNotEmpty(localPlant)
                                                        && StringInner
                                                        .isStringNotEmpty(localBatchId)) {
                                                    batchId = StringInner
                                                            .join(productId,
                                                                    "/",
                                                                    sourceSystem,
                                                                    "_",
                                                                    localPlant,
                                                                    "$",
                                                                    localNumber,
                                                                    "/",
                                                                    localBatchId);
                                                } else {
                                                    return false;
                                                }
                                            } else {
                                                return false;
                                            }
                                        }
                                    } else {
                                        return false;
                                    }
                                }
                            }
                        }
                    } else {
                        return false;
                    }

					builder.put("batchId", batchId);


					String processId = null;

					if (OMPGdmStockInventoryStocksHook.checkStockId(
							inventoryStockList, localMaterial, localBatchId,
							localVendorNumber, localStorageLocation)) {
						processId =  "";
						builder.put("processId", processId);
						rawDataBuilderList.add(builder);
					}
				}
			}
		}

		return true;
	}

	public List getInventoryStock(String sourceSystem, String localPlant,
								  String localMaterialNumber) {

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("sourceSystem").append(":").append(sourceSystem);
		stringBuilder.append(" AND ");
		stringBuilder.append("localPlant").append(":").append(localPlant);
		stringBuilder.append(" AND ");
		stringBuilder.append("localMaterial").append(":\"").append(AdfLuceneHelper.keyword(localMaterialNumber)).append("\"");

		String queryStr = stringBuilder.toString();
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

	public Map getSourceSystemV1(String sourceSystem, String localSourceSystem) {

		ADFCriteria adfCriteria17 = QueryHelper.buildCriteria("sourceSystem")
				.is(sourceSystem);
		ADFCriteria adfCriteria18 = QueryHelper.buildCriteria("localSourceSystem").is(
				localSourceSystem);
		ADFCriteria groupCriteria28 = adfCriteria17.and(adfCriteria18);

		ADFCriteria adfCriteria = groupCriteria28;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/edm/source_system_v1", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			Map.Entry<String, String> entry = retList.get(0);
			Map<String, Object> map = JsonObject.append(entry.getValue())
					.toMap();
			return map;
		}

		return null;

	}

	public List getCnsTlaneControl(String sourceSystem, String localPlant,
								   String triangulationDetail, String trigSysTransaction) {

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("sourceSystemCriticalParameters").append(":").append(sourceSystem);
		stringBuilder.append(" AND ");
		stringBuilder.append("trigSysPlant").append(":").append(localPlant);
		stringBuilder.append(" AND ");
		stringBuilder.append("triangulationDetail").append(":").append(triangulationDetail);
		stringBuilder.append(" AND ");
		stringBuilder.append("trigSysTransaction").append(":\"").append(AdfLuceneHelper.keyword(trigSysTransaction)).append("\"");

		String queryStr = stringBuilder.toString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/plan/cns_tlane_control", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			return retList;
		}

		return new ArrayList<>();

	}


	public List getCnsTlaneControlTriangulation(String sequenceNumber, String tlaneName) {

		ADFCriteria adfCriteria18 = QueryHelper.buildCriteria("sequenceNumber")
				.is(sequenceNumber);
		ADFCriteria adfCriteria19 = QueryHelper.buildCriteria("tlaneName")
				.is(tlaneName);
		ADFCriteria groupCriteria29 = adfCriteria18.and(adfCriteria19);

		ADFCriteria adfCriteria = groupCriteria29;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/plan/cns_tlane_control_triangulation", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			return retList;
		}

		return new ArrayList<>();

	}


}
