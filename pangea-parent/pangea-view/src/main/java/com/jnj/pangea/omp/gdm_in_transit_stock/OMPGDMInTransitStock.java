
package com.jnj.pangea.omp.gdm_in_transit_stock;

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
import com.jnj.pangea.hook.OMPGDMInTransitStockHook;
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
public class OMPGDMInTransitStock implements IEventProcessor {

    private final String FAILREGION = "/plan/edm_failed_data";

    @Override
    public List<ViewResultItem> process(List<RawDataEvent> list) {

        List<ViewResultItem> resultList = new ArrayList<>();

        list.stream().forEach(e -> {
            try {
                RawDataBuilder builder = new RawDataBuilder();

                Map<String, RawDataBuilder> failMap = new HashMap<>();

                List<ViewResultItem> itemList = new ArrayList<>();

                boolean isOk = buildView(e.getValue(), builder, failMap, itemList);
                if (itemList != null && itemList.size() > 0) {
                    resultList.addAll(itemList);
                }

                if (isOk) {
                    Map<String, Object> data = (Map<String, Object>) builder.toRawData();
                    String viewKey = JsonObject.create()
                            .append("customerId", data.get("customerId"))
                            .append("productId", data.get("productId"))
                            .append("quantity", data.get("quantity"))
                            .toJson();

                    ViewResultItem viewRaw = ViewResultBuilder.newResultItem(viewKey, data);
                    resultList.add(viewRaw);
                } else {
                    failMap.forEach((key, value) -> {
                        ViewResultItem viewRaw = ViewResultBuilder.newResultItem(FAILREGION, key, (Map<String, Object>) value.toRawData());
                        resultList.add(viewRaw);
                    });
                }
            } catch (Exception exception) {
                LogUtil.getCoreLog().info("OMPGDMInTransitStock Exception occured. key = {}.", e.getKey());
                LogUtil.getCoreLog().info("OMPGDMInTransitStock Exception:", exception);
            }
        });

        return resultList;
    }

    public boolean buildView(RawDataValue raw, RawDataBuilder builder, Map<String, RawDataBuilder> failMap, List<ViewResultItem> itemList) {

        Map map = raw.toMap();

        String sourceSystem = StringInner.getString(map, "sourceSystem");
        String demandGroup = StringInner.getString(map, "demandGroup");
        String productId = StringInner.getString(map, "productId");
        String date = StringInner.getString(map, "date");
        String quantity = StringInner.getString(map, "quantity");
        String uom = StringInner.getString(map, "uom");
        String localDpParentCode = null;
        String localAuom = null;
        String localDenominator = null;
        String localNumerator = null;


        String customerId = null;


        if (StringInner.isStringEmpty(sourceSystem) || StringInner.isStringEmpty(productId) || StringInner.isStringEmpty(uom)) {


            return false;
        } else {


            Map QueryGrpAsgnEntityMap = QueryGrpAsgnEntity(sourceSystem);
            if (QueryGrpAsgnEntityMap != null) {
            }
            if (QueryGrpAsgnEntityMap == null) {

                writeFailDataToRegion(failMap, "DP", "OMPGDMInTransitStock", "J1", "productId do not exist in edm_material", "sourceSystem", sourceSystem, demandGroup, productId, "", "", "");
                return false;
            }
        }
        customerId = demandGroup;
        builder.put("customerId", demandGroup);

        productId = OMPGDMInTransitStockHook.localMaterialNumber(productId);


        Map QueryMaterialGlobalMap = QueryMaterialGlobal(sourceSystem, productId);
        if (QueryMaterialGlobalMap != null) {
            localDpParentCode = String.valueOf(QueryMaterialGlobalMap.get("localDpParentCode"));
        }


        if (QueryMaterialGlobalMap == null || localDpParentCode == null) {


            productId = OMPGDMInTransitStockHook.getproductId(productId);

            writeFailDataToRegion(failMap, "DP", "OMPGDMInTransitStock", "J1", "localDpParentCode do not exist in edm material", "sourceSystem", sourceSystem, demandGroup, productId, "", "", "");
            return false;
        } else {


            localDpParentCode = StringInner.join("LA_", localDpParentCode);
        }
        productId = localDpParentCode;
        builder.put("productId", localDpParentCode);

        String dueDate = null;
        productId = OMPGDMInTransitStockHook.localMaterialNumber(productId);


        Map QueryAuomEntityMap = QueryAuomEntity(uom, productId);
        if (QueryAuomEntityMap != null) {
            localAuom = String.valueOf(QueryAuomEntityMap.get("localAuom"));
            localDenominator = String.valueOf(QueryAuomEntityMap.get("localDenominator"));
            localNumerator = String.valueOf(QueryAuomEntityMap.get("localNumerator"));
        }


        if (QueryAuomEntityMap == null) {


            productId = OMPGDMInTransitStockHook.getproductId(productId);

            writeFailDataToRegion(failMap, "DP", "OMPGDMInTransitStock", "J1", "unit do not exist in edm_Auom", "sourceSystem", sourceSystem, demandGroup, productId, "", "", "");
            return false;
        } else {


            dueDate = StringInner.join(date, " 00:00:00");
        }
        builder.put("dueDate", dueDate);

        productId = OMPGDMInTransitStockHook.getproductId(productId);


        quantity = OMPGDMInTransitStockHook.QueryCnsIntransitstock(productId, sourceSystem, demandGroup);


        builder.put("quantity", quantity);


        return true;
    }


    public Map QueryMaterialGlobal(String sourceSystem, String localMaterialNumber) {

        ADFCriteria adfCriteria1 = QueryHelper.buildCriteria("sourceSystem").is(sourceSystem);
        ADFCriteria adfCriteria2 = QueryHelper.buildCriteria("localMaterialNumber").is(localMaterialNumber);
        ADFCriteria groupCriteria6 = adfCriteria2.and(adfCriteria1);

        ADFCriteria adfCriteria = groupCriteria6;
        String queryStr = adfCriteria.toQueryString();
        List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList("/edm/material_global_v1", queryStr, -1);
        if (retList != null && retList.size() > 0) {
            Map.Entry<String, String> entry = retList.get(0);
            Map<String, Object> map = JsonObject.append(entry.getValue()).toMap();
            return map;
        }

        return null;

    }

    public Map QueryAuomEntity(String localAuom, String localMaterialNumber) {

        ADFCriteria adfCriteria3 = QueryHelper.buildCriteria("localAuom").is(localAuom);
        ADFCriteria adfCriteria4 = QueryHelper.buildCriteria("localMaterialNumber").is(localMaterialNumber);
        ADFCriteria groupCriteria7 = adfCriteria4.and(adfCriteria3);

        ADFCriteria adfCriteria = groupCriteria7;
        String queryStr = adfCriteria.toQueryString();
        List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList("/edm/material_auom_v1", queryStr, -1);
        if (retList != null && retList.size() > 0) {
            Map.Entry<String, String> entry = retList.get(0);
            Map<String, Object> map = JsonObject.append(entry.getValue()).toMap();
            return map;
        }

        return null;

    }

    public Map QueryGrpAsgnEntity(String sourceSystem) {

        ADFCriteria adfCriteria5 = QueryHelper.buildCriteria("sourceSystem").is(sourceSystem);
        ADFCriteria groupCriteria8 = adfCriteria5;

        ADFCriteria adfCriteria = groupCriteria8;
        String queryStr = adfCriteria.toQueryString();
        List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList("/plan/cns_dem_grp_asgn", queryStr, -1);
        if (retList != null && retList.size() > 0) {
            Map.Entry<String, String> entry = retList.get(0);
            Map<String, Object> map = JsonObject.append(entry.getValue()).toMap();
            return map;
        }

        return null;

    }


    private void writeFailDataToRegion(Map<String, RawDataBuilder> failMap, String functionalArea, String interfaceID, String errorCode, String errorValue, String sourceSystem, String key1, String key2, String key3, String key4, String key5, String businessArea) {
        String keyJson = JsonObject.create()
                .append("functionalArea", functionalArea)
                .append("interfaceID", interfaceID)
                .append("errorCode", errorCode)
                .append("sourceSystem", sourceSystem)
                .append("key1", key1)
                .append("key2", key2)
                .append("key3", key3)
                .append("key4", key4)
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