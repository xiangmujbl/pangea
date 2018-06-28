
package com.jnj.pangea.omp.gdm_stock;

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
import com.jnj.pangea.hook.OMPGDMStockHook;
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
public class GdmStock implements IEventProcessor {

    private final String FAILREGION = "";


    @Override
    public List<ViewResultItem> process(List<RawDataEvent> list) {

        List<ViewResultItem> resultList = new ArrayList<>();

        list.stream().forEach(e -> {
            try {
                List<RawDataBuilder> rawDataBuilderList = new ArrayList<>();
                Map<String, RawDataBuilder> failMap = new HashMap<>();
                boolean isOk = buildView(e.getValue(), rawDataBuilderList, failMap);

                rawDataBuilderList.forEach(rawDataBuilder -> {
                    Map<String, Object> data = (Map<String, Object>) rawDataBuilder.toRawData();
                    String viewKey = JsonObject.create()
                            .append("stockId", data.get("stockId"))
                            .toJson();

                    ViewResultItem viewRaw = ViewResultBuilder.newResultItem(viewKey, data);
                    resultList.add(viewRaw);
                });

                failMap.forEach((key, value) -> {
                    ViewResultItem viewRawFail = ViewResultBuilder.newResultItem(FAILREGION, key, (Map<String, Object>) value.toRawData());
                    resultList.add(viewRawFail);
                });

            } catch (Exception exception) {
                LogUtil.getCoreLog().info("GdmStock Exception occured. key = {}.", e.getKey());
                LogUtil.getCoreLog().info("GdmStock Exception:", exception);
            }

        });

        return resultList;
    }

    public boolean buildView(RawDataValue raw, List<RawDataBuilder> rawDataBuilderList, Map<String, RawDataBuilder> failMap) {

        Map map = raw.toMap();

        String matlNum = StringInner.getString(map, "matlNum");
        ;

        String plntCd = StringInner.getString(map, "plntCd");
        ;

        String sourceSystem = StringInner.getString(map, "sourceSystem");
        ;

        String stockId = "";

        String delnr = StringInner.getString(map, "mfgOrdrNum");
        ;

        String delps = StringInner.getString(map, "mfgOrdrItemNum");
        ;

        String mfgOrdrNum = StringInner.getString(map, "mfgOrdrNum");
        ;

        String mfgOrdrItemNum = StringInner.getString(map, "mfgOrdrItemNum");
        ;

        String locationId = "";

        String prdntVrsnNum = StringInner.getString(map, "prdntVrsnNum");
        ;

        String delInd = StringInner.getString(map, "delInd");
        ;

        String ordrStts = StringInner.getString(map, "ordrStts");
        ;

        String ordrType = StringInner.getString(map, "ordrType");
        ;

        String erpOrderId = "";

        String prdtnSchdEndDt = StringInner.getString(map, "prdtnSchdEndDt");
        ;

        String ordrQty = StringInner.getString(map, "ordrQty");
        ;

        String rcvdQty = StringInner.getString(map, "rcvdQty");
        ;

        String fctrNmrtrMeas = StringInner.getString(map, "fctrNmrtrMeas");
        ;

        String fctrDnmntrMeas = StringInner.getString(map, "fctrDnmntrMeas");
        ;

        String quantity = "";

        String goodRcptLdDaysQty = StringInner.getString(map, "goodRcptLdDaysQty");
        ;

        String primaryPlanningCode = null;

        String materialNumber = null;

        String localPlanningRelevant = null;

        String noPlanRelevant = null;

        String sourceObjectAttribute1 = "";

        String sourceObjectAttribute2Value = "";


        List<Map.Entry<String, String>> listMultiply = null;
        listMultiply = queryCnsPlanObjectFilter(sourceSystem);
        if (listMultiply != null) {
            for (Map.Entry<String, String> entry : listMultiply) {
                Map<String, Object> rawMap = JsonObject.append(entry.getValue()).toMap();

                sourceObjectAttribute1 = StringInner.getString(rawMap, "sourceObjectAttribute1");
                sourceObjectAttribute2Value = StringInner.getString(rawMap, "sourceObjectAttribute2Value");


                RawDataBuilder builder = new RawDataBuilder();


                builder.put("active", "YES");
                builder.put("activeOPRERP", "YES");
                builder.put("activeSOPERP", "NO");
                builder.put("batchId", "");
                builder.put("blockedQuantity", "0.0");
                builder.put("consignment", "NO");
                builder.put("certaintyId", "FE");
                builder.put("inventoryLinkGroupId", "");
                builder.put("vendorId", "");
                builder.put("processTypeId", "Production");
                builder.put("qualityQuantity", "0.0");
                builder.put("restrictedQuantity", "0.0");
                builder.put("returnsQuantity", "0.0");
                builder.put("stockType", "movement");
                builder.put("transferQuantity", "0.0");
                builder.put("transitDate", "1980/01/01 00:00:00");
                builder.put("unrestrictedQuantity", "0.0");


                Map map1 = queryMaterialGlobalV1(matlNum, sourceSystem);
                if (map1 != null) {
                    primaryPlanningCode = String.valueOf(map1.get("primaryPlanningCode"));
                    materialNumber = String.valueOf(map1.get("materialNumber"));
                }
                if (StringInner.isStringEmpty(primaryPlanningCode)) {
                    stockId = StringInner.join(materialNumber, "/", StringInner.join(sourceSystem, "_", plntCd), "/", mfgOrdrNum);
                } else {
                    stockId = StringInner.join(primaryPlanningCode, "/", StringInner.join(sourceSystem, "_", plntCd), "/", mfgOrdrNum);
                }


                builder.put("stockId", stockId);


                if (("").equals(delInd)) {


                    if (ordrStts.indexOf("CRTD") != -1 || ordrStts.indexOf("REL") != -1 || ordrStts.indexOf("PCNF") != -1) {
                        if (("plntCd").equals(sourceObjectAttribute1)) {
                            if ((sourceObjectAttribute2Value).equals(ordrType)) {
                                erpOrderId = mfgOrdrNum;
                            } else {
                                continue;
                            }
                        } else {
                            continue;
                        }
                    } else {
                        continue;
                    }
                } else {


                    continue;
                }
                erpOrderId = erpOrderId;
                builder.put("erpOrderId", erpOrderId);


                localPlanningRelevant = queryPlantV1(plntCd, sourceSystem);

                if (("X").equals(localPlanningRelevant)) {


                    locationId = StringInner.join(sourceSystem, "_", plntCd);
                } else {


                    return false;
                }
                locationId = locationId;
                builder.put("locationId", locationId);

                String processId = null;


                Map map2 = queryMaterialGlobalV1(matlNum, sourceSystem);
                if (map2 != null) {
                    primaryPlanningCode = String.valueOf(map2.get("primaryPlanningCode"));
                    materialNumber = String.valueOf(map2.get("materialNumber"));
                }
                if (StringInner.isStringEmpty(primaryPlanningCode)) {
                    processId = StringInner.join(prdntVrsnNum, "/", materialNumber, "/", sourceSystem, "_", plntCd);
                } else {
                    processId = StringInner.join(prdntVrsnNum, "/", primaryPlanningCode, "/", sourceSystem, "_", plntCd);
                }


                builder.put("processId", processId);

                String productId = null;


                noPlanRelevant = queryCnsMaterialPlanStatus(matlNum, plntCd, sourceSystem);

                if (("X").equals(noPlanRelevant)) {


                    Map map3 = queryMaterialGlobalV1(matlNum, plntCd);
                    if (map3 != null) {
                        primaryPlanningCode = String.valueOf(map3.get("primaryPlanningCode"));
                        materialNumber = String.valueOf(map3.get("materialNumber"));
                    }
                    if (StringInner.isStringEmpty(primaryPlanningCode) && StringInner.isStringEmpty(materialNumber)) {
                        continue;
                    } else {
                        if (StringInner.isStringEmpty(primaryPlanningCode)) {
                            productId = materialNumber;
                        } else {
                            productId = primaryPlanningCode;
                        }
                    }
                } else {


                    return false;
                }
                productId = productId;
                builder.put("productId", productId);


                quantity = OMPGDMStockHook.setQuantityValue(ordrQty, rcvdQty, fctrNmrtrMeas, fctrDnmntrMeas);


                builder.put("quantity", quantity);

                String receiptDate = null;


                receiptDate = OMPGDMStockHook.setReceiptDateValue(prdtnSchdEndDt);


                builder.put("receiptDate", receiptDate);

                String startDate = null;


                startDate = OMPGDMStockHook.setStartDateValue(prdtnSchdEndDt, goodRcptLdDaysQty);


                builder.put("startDate", startDate);


                rawDataBuilderList.add(builder);
            }
        }

        return true;
    }


    public Map queryMaterialGlobalV1(String matlNum, String sourceSystem) {

        ADFCriteria adfCriteria1 = QueryHelper.buildCriteria("localMaterialNumber").is(matlNum);
        ADFCriteria adfCriteria2 = QueryHelper.buildCriteria("sourceSystem").is(sourceSystem);
        ADFCriteria groupCriteria10 = adfCriteria2.and(adfCriteria1);

        ADFCriteria adfCriteria = groupCriteria10;
        String queryStr = adfCriteria.toQueryString();
        List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList("/edm/material_global_v1", queryStr, -1);
        if (retList != null && retList.size() > 0) {
            Map.Entry<String, String> entry = retList.get(0);
            Map<String, Object> map = JsonObject.append(entry.getValue()).toMap();
            return map;
        }

        return null;

    }

    public String queryCnsMaterialPlanStatus(String matlNum, String plntCd, String sourceSystem) {

        ADFCriteria adfCriteria3 = QueryHelper.buildCriteria("localMaterialNumber").is(matlNum);
        ADFCriteria adfCriteria4 = QueryHelper.buildCriteria("localPlant").is(plntCd);
        ADFCriteria adfCriteria5 = QueryHelper.buildCriteria("sourceSystem").is(sourceSystem);
        ADFCriteria groupCriteria11 = adfCriteria5.and(adfCriteria4).and(adfCriteria3);

        ADFCriteria adfCriteria = groupCriteria11;
        String queryStr = adfCriteria.toQueryString();
        List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList("/plan/cns_material_plan_status", queryStr, -1);
        if (retList != null && retList.size() > 0) {
            Map.Entry<String, String> entry = retList.get(0);
            Map<String, Object> map = JsonObject.append(entry.getValue()).toMap();
            Object o = map.get("noPlanRelevant");
            return o != null ? o.toString().trim() : "";
        }

        return null;

    }

    public String queryPlantV1(String plntCd, String sourceSystem) {

        ADFCriteria adfCriteria6 = QueryHelper.buildCriteria("localPlant").is(plntCd);
        ADFCriteria adfCriteria7 = QueryHelper.buildCriteria("sourceSystem").is(sourceSystem);
        ADFCriteria groupCriteria12 = adfCriteria7.and(adfCriteria6);

        ADFCriteria adfCriteria = groupCriteria12;
        String queryStr = adfCriteria.toQueryString();
        List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList("/edm/plant_v1", queryStr, -1);
        if (retList != null && retList.size() > 0) {
            Map.Entry<String, String> entry = retList.get(0);
            Map<String, Object> map = JsonObject.append(entry.getValue()).toMap();
            Object o = map.get("localPlanningRelevant");
            return o != null ? o.toString().trim() : "";
        }

        return null;

    }

    public List queryCnsPlanObjectFilter(String sourceSystem) {

        ADFCriteria adfCriteria8 = QueryHelper.buildCriteria("sourceObjectTechName").is("process_order");
        ADFCriteria adfCriteria9 = QueryHelper.buildCriteria("sourceSystem").is(sourceSystem);
        ADFCriteria groupCriteria13 = adfCriteria9.and(adfCriteria8);

        ADFCriteria adfCriteria = groupCriteria13;
        String queryStr = adfCriteria.toQueryString();
        List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList("/plan/cns_plan_object_filter", queryStr, -1);
        if (retList != null && retList.size() > 0) {
            return retList;
        }

        return new ArrayList<>();

    }


}