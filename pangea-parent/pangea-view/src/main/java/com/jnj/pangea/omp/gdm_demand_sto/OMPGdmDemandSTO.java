package com.jnj.pangea.omp.gdm_demand_sto;

import com.jnj.adf.client.api.JsonObject;
import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.curation.logic.IEventProcessor;
import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.adf.grid.data.raw.RawDataBuilder;
import com.jnj.adf.grid.view.common.AdfViewHelper;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.client.api.ADFCriteria;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.inner.DateInner;
import com.jnj.inner.StringInner;

import com.jnj.pangea.hook.OMPGdmDemandSTOHook;

import java.util.*;

@SuppressWarnings("unchecked")
public class OMPGdmDemandSTO implements IEventProcessor {

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
											.append("demandId",
													data.get("demandId"))
											.toJson();

									ViewResultItem viewRaw = ViewResultBuilder
											.newResultItem(viewKey, data);
									resultList.add(viewRaw);
								}
							} catch (Exception exception) {
								LogUtil.getCoreLog()
										.info("OMPGdmDemandSTO Exception occured. key = {}.",
												e.getKey());
								LogUtil.getCoreLog()
										.info("OMPGdmDemandSTO Exception:",
												exception);
							}
						});

		return resultList;
	}

	public boolean buildView(RawDataValue raw, RawDataBuilder builder,
			Map<String, RawDataBuilder> failMap, List<ViewResultItem> itemList) {

		Map map = raw.toMap();

		String custNum = StringInner.getString(map, "custNum");
		String sourceSystem = StringInner.getString(map, "sourceSystem");
		String plntCd = StringInner.getString(map, "plntCd");
		String localdelvDt = StringInner.getString(map, "localdelvDt");
		String localPDT = StringInner.getString(map, "localPDT");
		String matlNum = StringInner.getString(map, "matlNum");
		String locationId = "";
		String poNum = StringInner.getString(map, "poNum");
		String poLineNbr = StringInner.getString(map, "poLineNbr");
		String delvSchedCntNbr = StringInner.getString(map, "delvSchedCntNbr");
		String poTypeCd = StringInner.getString(map, "poTypeCd");
		String cnfrmQty = StringInner.getString(map, "cnfrmQty");
		String recvEaQty = StringInner.getString(map, "recvEaQty");
		String localNumerator = StringInner.getString(map, "localNumerator");
		String localDenominator = StringInner
				.getString(map, "localDenominator");
		String poLineQty = StringInner.getString(map, "poLineQty");
		String prchsngOrgNum = StringInner.getString(map, "prchsngOrgNum");
		String poUomCd = StringInner.getString(map, "poUomCd");
		String suplPlntCd = StringInner.getString(map, "suplPlntCd");
		String delInd = StringInner.getString(map, "delInd");
		String spRelevant = null;
		String noPlanRelevant = null;
		String primaryPlanningCode = null;
		String materialMumber = null;
		String minShelfLife = null;
		String minRemShelfLife = null;
		String unit = null;

		builder.put("active", "YES");
		builder.put("activeFCTERP", "NO");
		builder.put("activeOPRERP", "YES");
		builder.put("activeSOPERP", "NO");
		builder.put("batchId", " ");
		builder.put("certaintyId", "U1");
		builder.put("planningStrategy", "ProductLocationBalanced");
		//WRK02
		String WRK02 = StringInner.getString(map, "suplPlntCd");
		builder.put("WRK02", WRK02);

		String customerId = null;

		builder.put("customerId", custNum);

		if (StringInner.equal(sourceSystem, "CONS_LATAM")) {

			locationId = StringInner.join(sourceSystem, "_", suplPlntCd);
		}

		builder.put("locationId", locationId);

		String productId = null;

		Map map1 = ConCnsMaterialPlanStatus(matlNum, suplPlntCd, sourceSystem);
		if (map1 != null) {
			spRelevant = StringInner.getString(map1, "spRelevant");
			noPlanRelevant = StringInner.getString(map1, "noPlanRelevant");
		}
		if (StringInner.equal(spRelevant, "X")
				|| StringInner.equal(noPlanRelevant, "X")) {
			Map map2 = ConMaterialGlobalV1(matlNum, sourceSystem);
			if (map2 != null) {
				primaryPlanningCode = StringInner.getString(map2,
						"primaryPlanningCode");
				materialMumber = StringInner.getString(map2, "materialMumber");
				if (StringInner.isStringNotEmpty(primaryPlanningCode)) {
					productId = primaryPlanningCode;
				} else if (StringInner.isStringNotEmpty(materialMumber)) {
					productId = materialMumber;
				} else {
					productId = "";
				}
			}
		} else {
			return false;
		}

		builder.put("productId", productId);

		String demandId = null;

		Map cnsPlanFilterMapOne = ConCnsPlanObjectFilterOne(plntCd, poTypeCd,
				sourceSystem);
		if (cnsPlanFilterMapOne != null) {
		}
		if (StringInner.isMapNullWithSize(cnsPlanFilterMapOne)) {
			return false;
		} else {
			if (StringInner.isStringEmpty(delvSchedCntNbr)) {
				demandId = StringInner.join(productId, "/", locationId, "/",
						poNum, "/", poLineNbr);
			} else {
				demandId = StringInner.join(productId, "/", locationId, "/",
						poNum, "/", poLineNbr, "/", delvSchedCntNbr);
			}
		}

		if (StringInner.isStringNotEmpty(delInd)) {

			return false;
		}

		builder.put("demandId", demandId);

		String fromDueDate = null;

		if (StringInner.isStringNotEmpty(localdelvDt)) {

			if (StringInner.isStringEmpty(localPDT) || "0".equals(localPDT)) {
				fromDueDate = DateInner.dateToString(DateInner.stringToDate(
						localdelvDt, DateInner.F_yyyyMMdd),
						DateInner.yyyy_MM_dd_HHmmss_2);
			} else {
				fromDueDate = DateInner.dateToString(DateInner.offsetDay(
						DateInner.stringToDate(localdelvDt,
								DateInner.F_yyyyMMdd), Integer.parseInt("-"
								+ localPDT)), DateInner.yyyy_MM_dd_HHmmss_2);
			}
		}

		else {

			fromDueDate = "";
		}
		builder.put("fromDueDate", fromDueDate);

		String dueDate = null;

		if (StringInner.isStringEmpty(fromDueDate)) {

			dueDate = "";
		}

		else {

			dueDate = fromDueDate.substring(0, fromDueDate.length() - 1) + "1";
		}
		builder.put("dueDate", dueDate);

		String inventoryLinkGroupId = null;

		if (StringInner.isStringEmpty(delvSchedCntNbr)) {

			inventoryLinkGroupId = StringInner.join(productId, "/",
					sourceSystem, "_", plntCd, "/", poNum, "/", poLineNbr);
		}

		else {

			inventoryLinkGroupId = StringInner.join(productId, "/",
					sourceSystem, "_", plntCd, "/", poNum, "/", poLineNbr, "/",
					delvSchedCntNbr);
		}
		builder.put("inventoryLinkGroupId", inventoryLinkGroupId);

		String minRemainingShelfLife = null;

		Map mapThree = ConProdLocMinshelfThree(sourceSystem, plntCd, matlNum);
		if (mapThree != null) {
			minShelfLife = StringInner.getString(mapThree, "minShelfLife");
		}
		if (mapThree == null) {
			Map mapTwo = ConProdLocMinshelfTwo(plntCd, sourceSystem);
			if (mapTwo != null) {
				minShelfLife = StringInner.getString(mapTwo, "minShelfLife");
			}
			if (mapTwo == null) {
				Map mapOne = ConMaterialGlobalV1(matlNum, sourceSystem);
				if (mapOne != null) {
					minRemShelfLife = StringInner.getString(mapOne,
							"minRemShelfLife");
					minShelfLife = minRemShelfLife;
				}
				if (mapOne == null) {
					minShelfLife = "";
				}
			}
		}

		builder.put("minRemainingShelfLife", minShelfLife);

		String quantity = null;

		quantity = OMPGdmDemandSTOHook.calQuantity(poNum, poLineNbr, poLineQty,
				localNumerator, localDenominator);

		if (StringInner.equal("0", quantity)) {

			return false;
		}

		builder.put("quantity", quantity);

		String unitId = null;

		Map cnsPlanUnitMap = ConCnsPlanUnit(poUomCd, sourceSystem);
		if (cnsPlanUnitMap != null) {
			unit = StringInner.getString(cnsPlanUnitMap, "unit");
		}
		if (StringInner.isMapNull(cnsPlanUnitMap)) {
			return false;
		} else {
			unitId = unit;
		}

		builder.put("unitId", unitId);

		return true;
	}

	public Map ConCnsPlanObjectFilterOne(String plntCd, String poTypeCd,
			String sourceSystem) {

		ADFCriteria adfCriteria1 = QueryHelper.buildCriteria(
				"sourceObjectAttribute1").is("plntCd");
		ADFCriteria adfCriteria2 = QueryHelper.buildCriteria(
				"sourceObjectAttribute1Value").is(plntCd);
		ADFCriteria adfCriteria3 = QueryHelper.buildCriteria(
				"sourceObjectAttribute2Value").is(poTypeCd);
		ADFCriteria adfCriteria4 = QueryHelper.buildCriteria(
				"inclusionExclusion").is("I");
		ADFCriteria adfCriteria5 = QueryHelper.buildCriteria(
				"sourceObjectTechName").is("purchase_order_oa");
		ADFCriteria adfCriteria6 = QueryHelper.buildCriteria("sourceSystem")
				.is(sourceSystem);
		ADFCriteria groupCriteria20 = adfCriteria6.and(adfCriteria5)
				.and(adfCriteria4).and(adfCriteria3).and(adfCriteria2)
				.and(adfCriteria1);

		ADFCriteria adfCriteria = groupCriteria20;
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

	public Map ConProdLocMinshelfTwo(String plntCd, String sourceSystem) {

		ADFCriteria adfCriteria7 = QueryHelper.buildCriteria("localPlant").is(
				plntCd);
		ADFCriteria adfCriteria8 = QueryHelper.buildCriteria("sourceSystem")
				.is(sourceSystem);
		ADFCriteria adfCriteria9 = QueryHelper.buildCriteria(
				"localMaterialNumber").is("*");
		ADFCriteria groupCriteria21 = adfCriteria9.and(adfCriteria8).and(
				adfCriteria7);

		ADFCriteria adfCriteria = groupCriteria21;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/plan/prod_loc_min_shelf", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			Map.Entry<String, String> entry = retList.get(0);
			Map<String, Object> map = JsonObject.append(entry.getValue())
					.toMap();
			return map;
		}

		return null;

	}

	public Map ConProdLocMinshelfThree(String sourceSystem, String plntCd,
			String matlNum) {

		ADFCriteria adfCriteria10 = QueryHelper.buildCriteria("sourceSystem")
				.is(sourceSystem);
		ADFCriteria adfCriteria11 = QueryHelper.buildCriteria("localPlant").is(
				plntCd);
		ADFCriteria adfCriteria12 = QueryHelper.buildCriteria(
				"localMaterialNumber").is(matlNum);
		ADFCriteria groupCriteria22 = adfCriteria12.and(adfCriteria11).and(
				adfCriteria10);

		ADFCriteria adfCriteria = groupCriteria22;
		String queryStr = adfCriteria.toQueryString();
		List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
				"/plan/prod_loc_min_shelf", queryStr, -1);
		if (retList != null && retList.size() > 0) {
			Map.Entry<String, String> entry = retList.get(0);
			Map<String, Object> map = JsonObject.append(entry.getValue())
					.toMap();
			return map;
		}

		return null;

	}

	public Map ConCnsMaterialPlanStatus(String matlNum, String suplPlntCd,
			String sourceSystem) {

		ADFCriteria adfCriteria13 = QueryHelper.buildCriteria(
				"localMaterialNumber").is(matlNum);
		ADFCriteria adfCriteria14 = QueryHelper.buildCriteria("localPlant").is(
				suplPlntCd);
		ADFCriteria adfCriteria15 = QueryHelper.buildCriteria("sourceSystem")
				.is(sourceSystem);
		ADFCriteria groupCriteria23 = adfCriteria15.and(adfCriteria14).and(
				adfCriteria13);

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

	public Map ConMaterialGlobalV1(String matlNum, String sourceSystem) {

		ADFCriteria adfCriteria16 = QueryHelper.buildCriteria(
				"localMaterialNumber").is(matlNum);
		ADFCriteria adfCriteria17 = QueryHelper.buildCriteria("sourceSystem")
				.is(sourceSystem);
		ADFCriteria groupCriteria24 = adfCriteria17.and(adfCriteria16);

		ADFCriteria adfCriteria = groupCriteria24;
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

	public Map ConCnsPlanUnit(String poUomCd, String sourceSystem) {

		ADFCriteria adfCriteria18 = QueryHelper.buildCriteria("localUom").is(
				poUomCd);
		ADFCriteria adfCriteria19 = QueryHelper.buildCriteria("sourceSystem")
				.is(sourceSystem);
		ADFCriteria groupCriteria25 = adfCriteria19.and(adfCriteria18);

		ADFCriteria adfCriteria = groupCriteria25;
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
