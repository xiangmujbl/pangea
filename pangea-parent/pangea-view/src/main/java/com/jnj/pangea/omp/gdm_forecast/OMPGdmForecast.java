package com.jnj.pangea.omp.gdm_forecast;

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
import com.jnj.pangea.hook.OMPGdmForecastHook;
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
public class OMPGdmForecast implements IEventProcessor {

    private final String FAILREGION = "/edm/fail_data";

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
                                            .append("productId",
                                                    data.get("productId"))
                                            .append("forecastId",
                                                    data.get("forecastId"))
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
                                        .info("OMPGdmForecast Exception occured. key = {}.",
                                                e.getKey());
                                LogUtil.getCoreLog().info(
                                        "OMPGdmForecast Exception:", exception);
                            }
                        });

        return resultList;
    }

    public boolean buildView(RawDataValue raw, RawDataBuilder builder,
                             Map<String, RawDataBuilder> failMap, List<ViewResultItem> itemList) {

        Map map = raw.toMap();

        String loadDate = StringInner.getString(map, "loadDate");
        String mthWeekInd = StringInner.getString(map, "mthWeekInd");
        String oorPeriod = StringInner.getString(map, "oorPeriod");
        String ppc = StringInner.getString(map, "ppc");
        String primaryPlanningCode = StringInner.getString(map,
                "primaryPlanningCode");
        String quantity = StringInner.getString(map, "quantity");
        String localUom = StringInner.getString(map, "localUom");
        String sourceSystem = StringInner.getString(map, "sourceSystem");
        String localPlant = StringInner.getString(map, "localPlant");
        String demandGrp = StringInner.getString(map, "demandGrp");

        builder.put("active", "YES");
        builder.put("activeFctErp", "NO");
        builder.put("activeOprErp", "YES");
        builder.put("activeSopErp", "NO");
        builder.put("certaintyId", "PP");
        //customerId
        String customerId = StringInner.getString(map, "demandGrp");
        builder.put("customerId", customerId);
        builder.put("planningStrategy", "ProductLocationBalanced");

        String cycleStartDate = null;

        cycleStartDate = OMPGdmForecastHook.CycleStartDate(loadDate);

        builder.put("cycleStartDate", cycleStartDate);

        String dueDate = null;

        if (!StringInner.equal(mthWeekInd, "M")
                && !StringInner.equal(mthWeekInd, "W")) {

            writeFailDataToRegion(failMap, "SP", "OMPGdmForecast", "FRC6",
                    "Missing oor period", sourceSystem, demandGrp, ppc,
                    localPlant, oorPeriod, mthWeekInd, "");
            return false;
        } else {

            if (StringInner.equal(mthWeekInd, "M")) {
                if (StringInner.isStringNotEmpty(oorPeriod)) {
                    List listJnjCalendar = getJnjCalendarFiscalPeriod(oorPeriod);
                    if (listJnjCalendar.size() >= 1) {
                        dueDate = OMPGdmForecastHook
                                .getDuDateFromSortOfCalWeek(listJnjCalendar);
                        if (StringInner.equal(dueDate, "")) {

                            writeFailDataToRegion(failMap, "SP",
                                    "OMPGdmForecast", "FRC6",
                                    "Missing oor period", sourceSystem,
                                    demandGrp, ppc, localPlant, oorPeriod,
                                    mthWeekInd, "");
                            return false;
                        }
                    } else {

                        writeFailDataToRegion(failMap, "SP", "OMPGdmForecast",
                                "FRC6", "Missing oor period", sourceSystem,
                                demandGrp, ppc, localPlant, oorPeriod,
                                mthWeekInd, "");
                        return false;
                    }
                }
            } else {
                if (StringInner.equal(mthWeekInd, "W")) {
                    if (StringInner.isStringNotEmpty(oorPeriod)) {
                        List listJnjCalendar = getJnjCalendarCalWeek(oorPeriod);
                        if (listJnjCalendar.size() >= 1) {
                            dueDate = OMPGdmForecastHook
                                    .getDuDateFromFirstRecord(listJnjCalendar);
                            if (StringInner.equal(dueDate, "")) {

                                writeFailDataToRegion(failMap, "SP",
                                        "OMPGdmForecast", "FRC6",
                                        "Missing oor period", sourceSystem,
                                        demandGrp, ppc, localPlant, oorPeriod,
                                        mthWeekInd, "");
                                return false;
                            }
                        } else {

                            writeFailDataToRegion(failMap, "SP",
                                    "OMPGdmForecast", "FRC6",
                                    "Missing oor period", sourceSystem,
                                    demandGrp, ppc, localPlant, oorPeriod,
                                    mthWeekInd, "");
                            return false;
                        }
                    }
                }
            }
        }
        builder.put("dueDate", dueDate);

        String fromDueDate = null;

        if (!StringInner.equal(mthWeekInd, "M")
                && !StringInner.equal(mthWeekInd, "W")) {

            writeFailDataToRegion(failMap, "SP", "OMPGdmForecast", "FRC8",
                    "Missing oor period", sourceSystem, demandGrp, ppc,
                    localPlant, oorPeriod, mthWeekInd, "");
            return false;
        } else {

            if (StringInner.equal(mthWeekInd, "M")) {
                if (StringInner.isStringNotEmpty(oorPeriod)) {
                    List listJnjCalendar = getJnjCalendarFiscalPeriod(oorPeriod);
                    if (listJnjCalendar.size() >= 1) {
                        fromDueDate = OMPGdmForecastHook
                                .getFromDueDateBysortOfCalWeek(listJnjCalendar);
                        if (StringInner.equal(fromDueDate, "")) {

                            writeFailDataToRegion(failMap, "SP",
                                    "OMPGdmForecast", "FRC8",
                                    "Missing oor period", sourceSystem,
                                    demandGrp, ppc, localPlant, oorPeriod,
                                    mthWeekInd, "");
                            return false;
                        }
                    } else {

                        writeFailDataToRegion(failMap, "SP", "OMPGdmForecast",
                                "FRC8", "Missing oor period", sourceSystem,
                                demandGrp, ppc, localPlant, oorPeriod,
                                mthWeekInd, "");
                        return false;
                    }
                }
            } else {
                if (StringInner.equal(mthWeekInd, "W")) {
                    if (StringInner.isStringNotEmpty(oorPeriod)) {
                        List listJnjCalendar = getJnjCalendarCalWeek(oorPeriod);
                        if (listJnjCalendar.size() >= 1) {
                            fromDueDate = OMPGdmForecastHook
                                    .getFromDueDateByFirstRecord(listJnjCalendar);
                            if (StringInner.equal(fromDueDate, "")) {

                                writeFailDataToRegion(failMap, "SP",
                                        "OMPGdmForecast", "FRC8",
                                        "Missing oor period", sourceSystem,
                                        demandGrp, ppc, localPlant, oorPeriod,
                                        mthWeekInd, "");
                                return false;
                            }
                        } else {

                            writeFailDataToRegion(failMap, "SP",
                                    "OMPGdmForecast", "FRC8",
                                    "Missing oor period", sourceSystem,
                                    demandGrp, ppc, localPlant, oorPeriod,
                                    mthWeekInd, "");
                            return false;
                        }
                    }
                }
            }
        }
        builder.put("fromDueDate", fromDueDate);

        String locationId = null;

        locationId = sourceSystem + "_" + localPlant;
        builder.put("locationId", locationId);

        String productId = null;

        if (StringInner.isStringNotEmpty(ppc)
                && StringInner.isStringNotEmpty(sourceSystem)) {

            List listMaterialGlobal = getMaterialGlobal(ppc, sourceSystem);
            if (listMaterialGlobal.size() >= 1) {
                productId = OMPGdmForecastHook.CheckAnyOne(listMaterialGlobal,
                        localPlant, failMap);
                if (StringInner.equal(productId, "")) {
                    return false;
                }
            } else {
                return false;
            }
        }

        builder.put("productId", productId);

        String publishDate = null;

        publishDate = OMPGdmForecastHook.stampToDate(String.valueOf(System
                .currentTimeMillis()));

        builder.put("publishDate", publishDate);

        if (StringInner.isStringNotEmpty(ppc)
                && StringInner.isStringNotEmpty(sourceSystem)) {

            List listMaterialGlobal = getMaterialGlobal(ppc, sourceSystem);
            if (listMaterialGlobal.size() >= 1) {
                quantity = OMPGdmForecastHook.getQuantity(listMaterialGlobal,
                        localUom, quantity, failMap);
                if (StringInner.equal(quantity, null)) {

                    writeFailDataToRegion(failMap, "SP", "OMPGdmForecast",
                            "FRC13", "Missing UOM Conversion", sourceSystem,
                            demandGrp, ppc, localPlant, oorPeriod, mthWeekInd,
                            "");
                    return false;
                }
            }
        }

        builder.put("quantity", quantity);

        String forecastId = null;

        forecastId = productId + "-" + locationId + "-" + customerId + "-"
                + fromDueDate;
        builder.put("forecastId", forecastId);

        return true;
    }

    public List getJnjCalendarFiscalPeriod(String oorPeriod) {

        ADFCriteria adfCriteria1 = QueryHelper.buildCriteria("fiscalPeriod")
                .is(oorPeriod);
        ADFCriteria groupCriteria11 = adfCriteria1;

        ADFCriteria adfCriteria = groupCriteria11;
        String queryStr = adfCriteria.toQueryString();
        List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
                "/edm/jnj_calendar_v1", queryStr, -1);
        if (retList != null && retList.size() > 0) {
            return retList;
        }

        return new ArrayList<>();

    }

    public List getJnjCalendarCalWeek(String oorPeriod) {

        ADFCriteria adfCriteria2 = QueryHelper.buildCriteria("calWeek").is(
                oorPeriod);
        ADFCriteria groupCriteria12 = adfCriteria2;

        ADFCriteria adfCriteria = groupCriteria12;
        String queryStr = adfCriteria.toQueryString();
        List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
                "/edm/jnj_calendar_v1", queryStr, -1);
        if (retList != null && retList.size() > 0) {
            return retList;
        }

        return new ArrayList<>();

    }

    public List getMaterialGlobal(String ppc, String sourceSystem) {

        ADFCriteria adfCriteria3 = QueryHelper.buildCriteria(
                "primaryPlanningCode").is(ppc);
        ADFCriteria adfCriteria4 = QueryHelper.buildCriteria("sourceSystem")
                .is(sourceSystem);
        ADFCriteria groupCriteria13 = adfCriteria4.and(adfCriteria3);

        ADFCriteria adfCriteria = groupCriteria13;
        String queryStr = adfCriteria.toQueryString();
        List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
                "/edm/material_global_v1", queryStr, -1);
        if (retList != null && retList.size() > 0) {
            return retList;
        }

        return new ArrayList<>();

    }

    public Map getCnsMaterial(String sourceSystem, String localPlant,
                              String localMaterialNumber) {

        ADFCriteria adfCriteria5 = QueryHelper.buildCriteria("sourceSystem")
                .is(sourceSystem);
        ADFCriteria adfCriteria6 = QueryHelper.buildCriteria("localPlant").is(
                localPlant);
        ADFCriteria adfCriteria7 = QueryHelper.buildCriteria(
                "localMaterialNumber").is(localMaterialNumber);
        ADFCriteria groupCriteria14 = adfCriteria7.and(adfCriteria6).and(
                adfCriteria5);

        ADFCriteria adfCriteria = groupCriteria14;
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

    public Map getMaterialAumo(String sourceSystem, String localMaterialNumber,
                               String localUom) {

        ADFCriteria adfCriteria8 = QueryHelper.buildCriteria("sourceSystem")
                .is(sourceSystem);
        ADFCriteria adfCriteria9 = QueryHelper.buildCriteria(
                "localMaterialNumber").is(localMaterialNumber);
        ADFCriteria adfCriteria10 = QueryHelper.buildCriteria("localAuom").is(
                localUom);
        ADFCriteria groupCriteria15 = adfCriteria10.and(adfCriteria9).and(
                adfCriteria8);

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
