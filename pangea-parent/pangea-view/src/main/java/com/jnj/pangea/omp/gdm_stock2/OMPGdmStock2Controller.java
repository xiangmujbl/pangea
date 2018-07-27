package com.jnj.pangea.omp.gdm_stock2;

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
public class OMPGdmStock2Controller implements IEventProcessor {

	private final String FAILREGION = "/plan/edm_failed_data";
	private static final String PROJECT_ONE_DEV = "Project_One";
	private static final String YES = "YES";
	private static final String UNDERLINE = "_";
	private static final String EMPTY = "";
	private static final String X = "X";
	private static final String PURCHASE_ORDER = "Purchase Order";
	ThreadLocal<String> localPlant = new ThreadLocal<>();

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
										.info("OMPGdmStock2Controller Exception occured. key = {}.",
												e.getKey());
								LogUtil.getCoreLog().info(
										"OMPGdmStock2Controller Exception:",
										exception);
							}
						});

		return resultList;
	}

	public boolean buildView(RawDataValue raw, RawDataBuilder builder,
			Map<String, RawDataBuilder> failMap, List<ViewResultItem> itemList) {

		Map map = raw.toMap();

		String srcSysCd = StringInner.getString(map, "srcSysCd");
		String lotNum = StringInner.getString(map, "lotNum");
		String plntCd = StringInner.getString(map, "plntCd");
		String matlId = StringInner.getString(map, "matlId");
		String btch_num = StringInner.getString(map, "btchNum");
		String poDocNum = StringInner.getString(map, "poDocNum");
		String poDocLineNbr = StringInner.getString(map, "poDocLineNbr");
		String toBePostedQty = StringInner.getString(map, "toBePostedQty");
		String qcStsCd = StringInner.getString(map, "qcStsCd");
		String mfgOrdrDoc = StringInner.getString(map, "mfgOrdrDoc");
		String inspEndDt = StringInner.getString(map, "inspEndDt");
		String vndrNum = StringInner.getString(map, "vndrNum");
		String materialNumber = "";
		String lineItemTypeCd = StringInner.getString(map, "lineItemTypeCd");
		String localPlanningrelevant = null;
		String primaryPlanningCode = null;
		String inventoryLinkGroupId = null;
		String prdntVrsnNum = null;
		String poTypeCd = null;
		String matlNum = null;
		String prchsngOrgNum = null;
		String supNum = null;
		String suplPlntCd = null;
		String localProductionVersion = null;

		builder.put("active", "YES");
		builder.put("activeOPRERP", "YES");
		builder.put("activeSOPERP", "NO");
		builder.put("blockedQuantity", "0.0");
		builder.put("certaintyId", "QM");
		builder.put("qualityQuantity", "0.0");
		//quantity
		String quantity = StringInner.getString(map, "toBePostedQty");
		builder.put("quantity", quantity);
		builder.put("restrictedQuantity", "0.0");
		builder.put("returnsQuantity", "0.0");
		builder.put("stockType", "movement");
		builder.put("transferQuantity", "0.0");
		builder.put("transitDate", "1980/01/01 00:00:00");
		builder.put("unrestrictedQuantity", "0.0");

		if (!plantN7(srcSysCd, plntCd)) {
			return false;
		} else {
			if (!StringInner.isStringEmpty(localPlant.get())) {
				plntCd = returnLocalPlant();
			}
		}
		builder.put("plntCd", plntCd);
		String productId = null;
		List loopList = method91(matlId, plntCd, srcSysCd);
		if (loopList == null) {
			return false;
		} else {
			productId = iLot91Udf(loopList, matlId, productId,
					primaryPlanningCode, materialNumber, srcSysCd, lotNum);
		}
		if (productId.equals("invalid")) {

			writeFailDataToRegion(failMap, "SP", "OMPGDMSTOCK2", "iLot9",
					"primaryPlanningCode and materialNumber are both blank",
					srcSysCd, lotNum, "", "", "", "", "");
			return false;
		}
		builder.put("productId", productId);
		String locationId = null;
		localPlanningrelevant = method7(plntCd, srcSysCd);
		if (0 == 0) {
			locationId = iLot7Udf(localPlanningrelevant, plntCd, srcSysCd);
			if (locationId == null) {
				return false;
			}
		}
		builder.put("locationId", locationId);
		String stockId = null;
		Map map1 = method1(matlId, srcSysCd);
		if (map1 != null) {
			primaryPlanningCode = StringInner.getString(map1,
					"primaryPlanningCode");
			materialNumber = StringInner.getString(map1, "materialNumber");
		}
		if (0 == 0) {
			stockId = iLot1Udf(locationId, stockId, productId, materialNumber,
					primaryPlanningCode, srcSysCd, plntCd, lotNum);
		}
		builder.put("stockId", stockId);
		String batchId = null;
		batchId = iLot3Udf(locationId, productId, btch_num);
		builder.put("batchId", batchId);
		String consignment = null;
		consignment = iLot18Udf(poDocNum, poDocLineNbr, lineItemTypeCd);
		builder.put("consignment", consignment);
		String erpOrderId = null;
		erpOrderId = iLot6Udf(toBePostedQty, lotNum, qcStsCd);
		if (StringInner.isStringEmpty(erpOrderId)
				|| erpOrderId.equals("invalid")) {
			return false;
		}
		builder.put("erpOrderId", erpOrderId);
		if (StringInner.isStringEmpty(poDocNum)) {
			inventoryLinkGroupId = "";
		} else {
			if (StringInner.isStringEmpty(poDocNum)) {
				lineItemTypeCd = null;
			} else {
				lineItemTypeCd = method16(poDocNum, srcSysCd);
			}
			inventoryLinkGroupId = iLot16Udf(stockId, lineItemTypeCd);
		}
		builder.put("inventoryLinkGroupId", inventoryLinkGroupId);
		String vendorId = null;
		vendorId = vendorIdFilter(vndrNum);
		builder.put("vendorId", vendorId);
		String processId = null;
		if (!StringInner.isStringEmpty(mfgOrdrDoc)) {
			prdntVrsnNum = method141(mfgOrdrDoc, srcSysCd);
			if (0 == 0) {
				processId = iLot141Udf(locationId, productId, prdntVrsnNum);
			}
		} else {
			if (!StringInner.isStringEmpty(poDocNum)
					&& !StringInner.isStringEmpty(poDocLineNbr)) {
				Map map2 = method14(poDocNum, poDocLineNbr);
				if (map2 != null) {
					poTypeCd = StringInner.getString(map2, "poTypeCd");
					matlNum = StringInner.getString(map2, "matlNum");
					plntCd = StringInner.getString(map2, "plntCd");
					prchsngOrgNum = StringInner
							.getString(map2, "prchsngOrgNum");
					lineItemTypeCd = StringInner.getString(map2,
							"lineItemTypeCd");
					supNum = StringInner.getString(map2, "supNum");
					suplPlntCd = StringInner.getString(map2, "suplPlntCd");
					if (poTypeCd.equals("NB")
							&& StringInner.isStringEmpty(lineItemTypeCd)) {
						processId = iLot142Udf(locationId, productId, vndrNum);
					} else if (poTypeCd.equals("NB")
							&& lineItemTypeCd.equals("3")) {
						localProductionVersion = method142(matlNum, plntCd,
								prchsngOrgNum, lineItemTypeCd, supNum);
						if (StringInner.isStringEmpty(localProductionVersion)) {
							processId = iLot1431Udf(locationId, productId);
						} else {
							processId = iLot1432Udf(locationId, productId,
									localProductionVersion);
						}
					} else if (poTypeCd.equals("UB") || poTypeCd.equals("ZLA")
							|| poTypeCd.equals("ZNB")) {
						processId = iLot144Udf(locationId, productId,
								suplPlntCd, vendorId);
					} else {
						processId = "";
					}
				}
			}
		}
		builder.put("processId", processId);
		String processTypeId = null;
		if (!StringInner.isStringEmpty(mfgOrdrDoc)) {
			processTypeId = "Production";
		} else {
			if (!StringInner.isStringEmpty(poDocNum)
					&& !StringInner.isStringEmpty(poDocLineNbr)) {
				Map map3 = method14(poDocNum, poDocLineNbr);
				if (map3 != null) {
					lineItemTypeCd = StringInner.getString(map3,
							"lineItemTypeCd");
					poTypeCd = StringInner.getString(map3, "poTypeCd");
					if (0 == 0) {
						processTypeId = iLot15Udf(lineItemTypeCd, poTypeCd);
					}
				}
			}
		}
		builder.put("processTypeId", processTypeId);
		String receiptDate = null;
		receiptDate = iLot10Udf(inspEndDt);
		builder.put("receiptDate", receiptDate);
		String startDate = null;
		startDate = iLot10Udf(inspEndDt);
		builder.put("startDate", startDate);

		return true;
	}

	public Map method1(String localMaterialNumber, String sourceSystem) {

		ADFCriteria adfCriteria1 = QueryHelper.buildCriteria(
				"localMaterialNumber").is(localMaterialNumber);

		ADFCriteria adfCriteria2 = QueryHelper.buildCriteria("sourceSystem")
				.is(sourceSystem);

		ADFCriteria groupCriteria27 = adfCriteria2.and(adfCriteria1);

		ADFCriteria adfCriteria = groupCriteria27;
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

	public String method7(String localPlant, String sourceSystem) {

		ADFCriteria adfCriteria3 = QueryHelper.buildCriteria("localPlant").is(
				localPlant);

		ADFCriteria adfCriteria4 = QueryHelper.buildCriteria("sourceSystem")
				.is(sourceSystem);

		ADFCriteria groupCriteria28 = adfCriteria4.and(adfCriteria3);

		ADFCriteria adfCriteria = groupCriteria28;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/edm/plant_v1", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			Map.Entry<String, String> entry = retList.get(0);
			Map<String, Object> map = JsonObject.append(entry.getValue())
					.toMap();
			Object o = map.get("localPlanningRelevant");
			return o != null ? o.toString().trim() : "";
		}

		return null;

	}

	public List method91(String localMaterialNumber, String localPlant,
			String sourceSystem) {

		ADFCriteria adfCriteria5 = QueryHelper.buildCriteria(
				"localMaterialNumber").is(localMaterialNumber);

		ADFCriteria adfCriteria6 = QueryHelper.buildCriteria("localPlant").is(
				localPlant);

		ADFCriteria adfCriteria7 = QueryHelper.buildCriteria("sourceSystem")
				.is(sourceSystem);

		ADFCriteria groupCriteria29 = adfCriteria7.and(adfCriteria6).and(
				adfCriteria5);

		ADFCriteria adfCriteria = groupCriteria29;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/plan/cns_material_plan_status", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			return retList;
		}

		return null;

	}

	public Map method92(String localMaterialNumber) {

		ADFCriteria adfCriteria8 = QueryHelper.buildCriteria(
				"localMaterialNumber").is(localMaterialNumber);

		ADFCriteria groupCriteria30 = adfCriteria8;

		ADFCriteria adfCriteria = groupCriteria30;
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

	public Map method14(String poNum, String poLineNbr) {

		ADFCriteria adfCriteria9 = QueryHelper.buildCriteria("poNum").is(poNum);

		ADFCriteria adfCriteria10 = QueryHelper.buildCriteria("poLineNbr").is(
				poLineNbr);

		ADFCriteria groupCriteria31 = adfCriteria10.and(adfCriteria9);

		ADFCriteria adfCriteria = groupCriteria31;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/edm/purchase_order_oa_v1", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			Map.Entry<String, String> entry = retList.get(0);
			Map<String, Object> map = JsonObject.append(entry.getValue())
					.toMap();
			return map;
		}

		return null;

	}

	public String method141(String mfgOrdrNum, String sourceSystem) {

		ADFCriteria adfCriteria11 = QueryHelper.buildCriteria("mfgOrdrNum").is(
				mfgOrdrNum);

		ADFCriteria adfCriteria12 = QueryHelper.buildCriteria("sourceSystem")
				.is(sourceSystem);

		ADFCriteria groupCriteria32 = adfCriteria12.and(adfCriteria11);

		ADFCriteria adfCriteria = groupCriteria32;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/edm/process_order_v1", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			Map.Entry<String, String> entry = retList.get(0);
			Map<String, Object> map = JsonObject.append(entry.getValue())
					.toMap();
			Object o = map.get("prdntVrsnNum");
			return o != null ? o.toString().trim() : "";
		}

		return null;

	}

	public String method142(String localMaterialNumber, String localPlanPlant,
			String localPurchasingOrg, String infotype, String localvendor) {

		ADFCriteria adfCriteria13 = QueryHelper.buildCriteria(
				"localMaterialNumber").is(localMaterialNumber);

		ADFCriteria adfCriteria14 = QueryHelper.buildCriteria("localPlanPlant")
				.is(localPlanPlant);

		ADFCriteria adfCriteria15 = QueryHelper.buildCriteria(
				"localPurchasingOrg").is(localPurchasingOrg);

		ADFCriteria adfCriteria16 = QueryHelper.buildCriteria("infotype").is(
				infotype);

		ADFCriteria adfCriteria17 = QueryHelper.buildCriteria("localvendor")
				.is(localvendor);

		ADFCriteria groupCriteria33 = adfCriteria17.and(adfCriteria16)
				.and(adfCriteria15).and(adfCriteria14).and(adfCriteria13);

		ADFCriteria adfCriteria = groupCriteria33;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/edm/purchasing_info_record_v1", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			Map.Entry<String, String> entry = retList.get(0);
			Map<String, Object> map = JsonObject.append(entry.getValue())
					.toMap();
			Object o = map.get("localProductionVersion");
			return o != null ? o.toString().trim() : "";
		}

		return null;

	}

	public String method16(String poNum, String sourceSystem) {

		ADFCriteria adfCriteria18 = QueryHelper.buildCriteria("poNum")
				.is(poNum);

		ADFCriteria adfCriteria19 = QueryHelper.buildCriteria("sourceSystem")
				.is(sourceSystem);

		ADFCriteria groupCriteria34 = adfCriteria19.and(adfCriteria18);

		ADFCriteria adfCriteria = groupCriteria34;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/edm/purchase_order_oa_v1", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			Map.Entry<String, String> entry = retList.get(0);
			Map<String, Object> map = JsonObject.append(entry.getValue())
					.toMap();
			Object o = map.get("lineItemTypeCd");
			return o != null ? o.toString().trim() : "";
		}

		return null;

	}

	public String getEntityWithLocalSourceSystemAndSourceSystem(
			String localSourceSystem, String sourceSystem) {

		ADFCriteria adfCriteria20 = QueryHelper.buildCriteria(
				"localSourceSystem").is(localSourceSystem);

		ADFCriteria adfCriteria21 = QueryHelper.buildCriteria("sourceSystem")
				.is(sourceSystem);

		ADFCriteria groupCriteria35 = adfCriteria21.and(adfCriteria20);

		ADFCriteria adfCriteria = groupCriteria35;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/edm/source_system_v1", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			Map.Entry<String, String> entry = retList.get(0);
			Map<String, Object> map = JsonObject.append(entry.getValue())
					.toMap();
			Object o = map.get("localSourceSystem");
			return o != null ? o.toString().trim() : "";
		}

		return null;

	}

	public List getEntityWithSourceSystemCriticalParameters(String sourceSystem) {

		ADFCriteria adfCriteria22 = QueryHelper.buildCriteria(
				"sourceSystemCriticalParameters").is(sourceSystem);

		ADFCriteria groupCriteria36 = adfCriteria22;

		ADFCriteria adfCriteria = groupCriteria36;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/plan/cns_tlane_control", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			return retList;
		}

		return null;

	}

	public List getTlaneWithSourceSystemCriticalParameters(
			String sequenceNumber, String tlaneName) {

		ADFCriteria adfCriteria23 = QueryHelper.buildCriteria("sequenceNumber")
				.is(sequenceNumber);

		ADFCriteria adfCriteria24 = QueryHelper.buildCriteria("tlaneName").is(
				tlaneName);

		ADFCriteria groupCriteria37 = adfCriteria24.and(adfCriteria23);

		ADFCriteria adfCriteria = groupCriteria37;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/plan/cns_tlane_control_triangulation", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			return retList;
		}

		return null;

	}

	public String getPlantWithSourceSystemAndLocalPlant(String sourceSystem,
			String localPlant) {

		ADFCriteria adfCriteria25 = QueryHelper.buildCriteria("sourceSystem")
				.is(sourceSystem);

		ADFCriteria adfCriteria26 = QueryHelper.buildCriteria("localPlant").is(
				localPlant);

		ADFCriteria groupCriteria38 = adfCriteria26.and(adfCriteria25);

		ADFCriteria adfCriteria = groupCriteria38;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/edm/plant_v1", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			Map.Entry<String, String> entry = retList.get(0);
			Map<String, Object> map = JsonObject.append(entry.getValue())
					.toMap();
			Object o = map.get("localPlanningRelevant");
			return o != null ? o.toString().trim() : "";
		}

		return null;

	}

	private final Map findHighestStepNumber(List list) {
		List<Map.Entry<String, String>> triangulationEntities = list;
		Map<String, Object> tempEntity = JsonObject.append(
				triangulationEntities.get(0).getValue()).toMap();
		for (Map.Entry<String, String> temp : triangulationEntities) {
			Map<String, Object> triangulationEntity = JsonObject.append(
					temp.getValue()).toMap();
			if (Integer.parseInt(String.valueOf(triangulationEntity
					.get("stepNumber"))) > Integer.parseInt(String
					.valueOf(tempEntity.get("stepNumber")))) {
				tempEntity = triangulationEntity;
			}
		}
		return tempEntity;
	}

	private final boolean plantN7(String srcSysCd, String plntCd) {
		String sourceSystemEntity = getEntityWithLocalSourceSystemAndSourceSystem(
				PROJECT_ONE_DEV, srcSysCd);
		if (sourceSystemEntity == null) {
			LogUtil.getCoreLog().info(
					"plantN7: No sourceSystemEntity by Project_One");
			return false;
		}
		localPlant.set(plntCd);

		List<Map.Entry<String, String>> tlaneControlEntityList = getEntityWithSourceSystemCriticalParameters(srcSysCd);

		for (Map.Entry<String, String> temp : tlaneControlEntityList) {
			Map<String, Object> tlaneControl = JsonObject.append(
					temp.getValue()).toMap();
			if (String.valueOf(tlaneControl.get("trigSysPlant")).equals(
					localPlant.get())
					&& String.valueOf(tlaneControl.get("triangulationDetail"))
							.equalsIgnoreCase(YES)
					&& String.valueOf(tlaneControl.get("trigSysTransaction"))
							.equalsIgnoreCase(PURCHASE_ORDER)) {
				List<Map.Entry<String, String>> triangulationEntities = getTlaneWithSourceSystemCriticalParameters(
						String.valueOf(tlaneControl.get("sequenceNumber")),
						String.valueOf(tlaneControl.get("tlaneName")));
				if (triangulationEntities != null) {
					Map<String, Object> stepNumberEntity = findHighestStepNumber(triangulationEntities);
					localPlant
							.set(String
									.valueOf(
											stepNumberEntity
													.get("destinatonLocation"))
									.replace(
											String.valueOf(tlaneControl
													.get("sourceSystemCriticalParameters"))
													+ UNDERLINE, EMPTY));
				}
			}
		}
		//Planning relevancy check
		String plantV1Entity = getPlantWithSourceSystemAndLocalPlant(srcSysCd,
				localPlant.get());
		if (plantV1Entity != null && plantV1Entity.equalsIgnoreCase(X)) {
			LogUtil.getCoreLog().info("srcSysCd: " + srcSysCd);
			LogUtil.getCoreLog().info("localPlant.get(): " + localPlant.get());
			return true;
		}

		LogUtil.getCoreLog().info("plantN7: Default false");
		return false;
	}

	private final String iLot1Udf(String locationId, String stockId,
			String productId, String materialNumber,
			String primaryPlanningCode, String srcSysCd, String plntCd,
			String lotNum) {
		StringBuffer sb = new StringBuffer();
		if (!StringInner.isStringEmpty(primaryPlanningCode)) {
			productId = primaryPlanningCode;
		} else if (!StringInner.isStringEmpty(materialNumber)) {
			productId = materialNumber;
		} else {
			productId = "";
		}
		sb.append(productId);
		sb.append("/");
		locationId = srcSysCd + "_" + localPlant.get();
		sb.append(locationId);
		sb.append("/");
		sb.append(lotNum);
		stockId = sb.toString();
		return stockId;
	}

	private final String iLot3Udf(String locationId, String productId,
			String btchNum) {
		StringBuffer sb = new StringBuffer();
		sb.append(productId);
		sb.append("/");
		sb.append(locationId);
		sb.append("/");
		sb.append(btchNum);
		return sb.toString();
	}

	private final String iLot18Udf(String poDocNum, String poDocLineNbr,
			String lineItemTypeCd) {
		if (!StringInner.isStringEmpty(poDocNum)
				&& !StringInner.isStringEmpty(poDocLineNbr)) {
			Map map2 = method14(poDocNum, poDocLineNbr);
			if (map2 != null) {
				lineItemTypeCd = String.valueOf(map2.get("lineItemTypeCd"));
			}
		}

		if (StringInner.isStringEmpty(poDocNum)) {
			return "NO";
		} else {
			if (null == lineItemTypeCd) {
				return "NO";
			}
			if (lineItemTypeCd.equals("2") || lineItemTypeCd.equals("K")) {
				return "YES";
			} else {
				return "NO";
			}
		}
	}

	private final String iLot6Udf(String toBePostedQty, String lotNum,
			String qcStsCd) {
		if (StringInner.isStringEmpty(toBePostedQty)
				|| toBePostedQty.equals("0.000")) {
			return "invalid";
		} else if (qcStsCd.contains("LTCA") || qcStsCd.contains("UD")) {
			return "invalid";
		} else {
			return lotNum;
		}
	}

	private final String iLot7Udf(String localPlanningrelevant, String plntCd,
			String srcSysCd) {
		if (null == localPlanningrelevant) {
			return "";
		} else if (localPlanningrelevant.equals("X")) {
			return srcSysCd + "_" + plntCd;
		} else {
			return "";
		}
	}

	private final String iLot15Udf(String lineItemTypeCd, String poTypeCd) {
		if (poTypeCd.equals("NB")) {
			if (StringInner.isStringEmpty(lineItemTypeCd)) {
				return "VendorTransport";
			} else if (lineItemTypeCd.equals("3")) {
				return "SubcontractingTransport";
			}
		} else if (poTypeCd.equals("UB")) {
			return "InternalTransport";
		} else if (poTypeCd.equals("ZLA") || poTypeCd.equals("ZNB")) {
			return "ExternalTransport";
		}
		return "";
	}

	private final String iLot16Udf(String stockId, String lineItemTypeCd) {
		if (lineItemTypeCd != null && lineItemTypeCd.equals("3")) {
			return stockId;
		} else {
			return "";
		}
	}

	private final String iLot9Udf(String primaryPlanningCode,
			String materialNumber) {
		if (!StringInner.isStringEmpty(primaryPlanningCode)) {
			return primaryPlanningCode;
		} else if (!StringInner.isStringEmpty(materialNumber)) {
			return materialNumber;
		} else {
			return "";
		}
	}

	private final String iLot10Udf(String inspEndDt) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",
				Locale.getDefault());
		Date s_date = null;
		try {
			s_date = (Date) sdf.parse(inspEndDt);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return sdf1.format(s_date);
	}

	private final String iLot141Udf(String locationId, String productId,
			String prdntVrsnNum) {
		return prdntVrsnNum + "/" + productId + "/" + locationId;
	}

	private final String iLot142Udf(String locationId, String productId,
			String vndrNum) {
		return "SU/" + productId + "/" + locationId + "/" + vndrNum
				+ "/Default";
	}

	private final String iLot1431Udf(String locationId, String productId) {
		return productId + "/" + locationId;
	}

	private final String iLot1432Udf(String locationId, String productId,
			String localProductionVersion) {
		return localProductionVersion + "/" + productId + "/" + locationId;
	}

	private final String iLot144Udf(String locationId, String productId,
			String suplPlntCd, String vndrNum) {
		return "TR" + "/" + productId + "/" + locationId + "/" + suplPlntCd
				+ "/" + vndrNum;
	}

	private final String vendorIdFilter(String vndrNum) {
		return StringUtils.stripStart(vndrNum, "0");
	}

	private final String iLot91Udf(List list, String matlId, String productId,
			String primaryPlanningCode, String materialNumber, String srcSysCd,
			String lotNum) {
		List<Map.Entry<String, String>> map91 = list;
		if (null != map91) {
			for (Map.Entry<String, String> map911 : map91) {
				Map<String, Object> map922 = JsonObject.append(
						map911.getValue()).toMap();
				String spRelevant = String.valueOf(map922.get("spRelevant"));
				String noPlanRelevant = String.valueOf(map922
						.get("noPlanRelevant"));
				if (spRelevant.equals("X") || noPlanRelevant.equals("X")) {

					Map map5 = method92(matlId);
					if (map5 != null) {
						primaryPlanningCode = String.valueOf(map5
								.get("primaryPlanningCode"));
						materialNumber = String.valueOf(map5
								.get("materialNumber"));
						productId = iLot9Udf(primaryPlanningCode,
								materialNumber);
						if (StringInner.isStringEmpty(productId)) {

							return "invalid";
						}
						break;
					}
				}
			}
		} else {
			return "invalid";
		}
		if (StringInner.isStringEmpty(productId)) {
			return "invalid";
		} else {
			return productId;
		}
	}

	private final String returnLocalPlant() {
		return localPlant.get();
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
