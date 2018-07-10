package com.jnj.pangea.omp.gdm_stock2;

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
import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@SuppressWarnings("unchecked")
public class OMPGdmStock2Controller001 implements IEventProcessor {

    private final String FAILREGION = "/pangea/edm_failed_data";
//    String stockId;
//    String locationId;

    @Override
    public List<ViewResultItem> process(List<RawDataEvent> list) {

        List<ViewResultItem> resultList = new ArrayList<>();

        list.stream()
                .forEach(
                        e -> {
                            try {
                                RawDataBuilder builder = new RawDataBuilder();

                                Map<String, RawDataBuilder> failMap = new HashMap<>();

                                boolean isOk = buildView(e.getValue(), builder,
                                        failMap);

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
                                        .info("OMPGdmStock2Controller001 Exception occured. key = {}.",
                                                e.getKey());
                                LogUtil.getCoreLog().info(
                                        "OMPGdmStock2Controller001 Exception:",
                                        exception);
                            }
                        });

        return resultList;
    }

    public boolean buildView(RawDataValue raw, RawDataBuilder builder,
                             Map<String, RawDataBuilder> failMap) {

        Map map = raw.toMap();

        String primaryPlanningCode = null;

        String plntCd = String.valueOf(map.get("plntCd"));

        String matlId = String.valueOf(map.get("matlId"));

        String toBePostedQty = String.valueOf(map.get("toBePostedQty"));

        String result7 = null;

        String poTypeCd = null;

        String result6 = null;

        String poDoLinecNbr = String.valueOf(map.get("poDocLineNbr"));

        String suplPlntCd = null;

        String mfgOrdrDoc = String.valueOf(map.get("mfgOrdrDoc"));

        String materialNumber = null;

        String qcStsCd = String.valueOf(map.get("qcStsCd"));

        String poDocLineNbr = String.valueOf(map.get("poDocLineNbr"));

        String localProductionVersion = null;


        String prchsngOrgNum = null;

        String btch_num = String.valueOf(map.get("btchNum"));

        String poDocNum = String.valueOf(map.get("poDocNum"));

        String lineItemTypeCd = null;

        String lotNum = String.valueOf(map.get("lotNum"));

        String vndrNum = String.valueOf(map.get("vndrNum"));

        String srcSysCd = String.valueOf(map.get("srcSysCd"));

        String localPlanningrelevant = null;


        String inspEndDt = String.valueOf(map.get("inspEndDt"));

        String matlNum = null;

        String supNum = null;

        String materialMumber = null;

        String prdntVrsnNum = null;

        String productId = null;
        List<Map.Entry<String, String>> map91 = method91(matlId,plntCd,srcSysCd);
        if(null!=map91){
            for(Map.Entry<String, String> map911:map91){
                Map<String, Object> map922 = JsonObject.append(map911.getValue())
                        .toMap();
                String spRelevant = String.valueOf(map922.get("spRelevant"));
                String noPlanRelevant = String.valueOf(map922.get("noPlanRelevant"));
                if (spRelevant.equals("X") || noPlanRelevant.equals("X")) {

                    Map map5 = method92(matlId);
                    if (map5 != null) {
                        primaryPlanningCode = String.valueOf(map5
                                .get("primaryPlanningCode"));
                        materialNumber = String.valueOf(map5.get("materialNumber"));
                        productId = iLot9Udf(primaryPlanningCode, materialNumber);
                        if(null==productId||productId.isEmpty()){
                            return false;
                        }
                        break;
                    }
                }
            }
        }else{
            return false;
        }
        if(null==productId||productId.isEmpty()){
            return false;
        }else{
            builder.put("productId", productId);
        }



        String locationId = null;
        localPlanningrelevant = method7(plntCd,srcSysCd);
        if (0 == 0) {
            locationId = iLot7Udf(localPlanningrelevant, plntCd, srcSysCd);
        }
        if(null==locationId||locationId.isEmpty()){
            return false;
        }else{
            builder.put("locationId", locationId);
        }


        String stockId = null;

        Map map1 = method1(matlId, srcSysCd);
        if (map1 != null) {
            primaryPlanningCode = String.valueOf(map1
                    .get("primaryPlanningCode"));
            materialMumber = String.valueOf(map1.get("materialNumber"));
        }

        if (0 == 0) {

            stockId = iLot1Udf(locationId,stockId,productId,materialMumber, primaryPlanningCode, srcSysCd,
                    plntCd, lotNum);
        }

        builder.put("stockId", stockId);





        builder.put("active", "YES");
        builder.put("activeOPRERP", "YES");
        builder.put("activeSOPERP", "NO");
        builder.put("blockedQuantity", "0.0");
        builder.put("certaintyID", "QM");
        //vendorId
        String vendorId = String.valueOf(map.get("vndrNum"));
        builder.put("vendorId", vendorId);
        builder.put("qualityQuantity", "0.0");
        //quantity
        String quantity = String.valueOf(map.get("toBePostedQty")).trim();
        if(quantity.isEmpty()||quantity.equals("0.000")){
            return false;
        }else{
            builder.put("quantity", quantity);
        }
        builder.put("restrictedQuantity", "0.0");
        builder.put("returnsQuantity", "0.0");
        builder.put("stockType", "movement");
        builder.put("transferQuantity", "0.0");
        builder.put("transitDate", "1980/01/01 00:00:00");
        builder.put("unrestrictedQuantity", "0.0");



        String batchId = null;

        batchId = iLot3Udf(locationId,productId,btch_num);

        builder.put("batchId", batchId);

        String consignment = null;
        Map map2 = method14(poDocNum, poDoLinecNbr);
        if (map2 != null) {
            lineItemTypeCd = String.valueOf(map2.get("lineItemTypeCd"));
        }

        if (poDocNum.isEmpty()) {
            consignment = "NO";
        }
        else{
//            consignment ="";
            consignment = iLot18Udf(lineItemTypeCd);
        }
//        else {
//            if (0 == 0) {
//                consignment = iLot18Udf(lineItemTypeCd);
//            }
//        }
        builder.put("consignment", consignment);

        String erpOrderId = null;

        erpOrderId = iLot6Udf(toBePostedQty, lotNum, qcStsCd);
        if(erpOrderId.equals("invalid")){
            return false;
        }
        builder.put("erpOrderId", erpOrderId);

        String inventoryLinkGroupId = null;

        if (poDocNum.isEmpty()) {

            inventoryLinkGroupId = "";
        }

        else {

            lineItemTypeCd = method16(poDocNum, srcSysCd);
            if (0 == 0) {

                inventoryLinkGroupId = iLot16Udf(stockId,lineItemTypeCd);
            }
        }
        builder.put("inventoryLinkGroupId", inventoryLinkGroupId);



        String processId = null;

        if (!mfgOrdrDoc.isEmpty()) {

            prdntVrsnNum = method141(mfgOrdrDoc, srcSysCd);
            if (0 == 0) {

                processId = iLot141Udf(locationId,productId,prdntVrsnNum);
            }
        }

        else {

            Map map3 = method14(poDocNum, poDocLineNbr);
            if (map3 != null) {
                poTypeCd = String.valueOf(map3.get("poTypeCd"));
                matlNum = String.valueOf(map3.get("matlNum"));
                plntCd = String.valueOf(map3.get("plntCd"));
                prchsngOrgNum = String.valueOf(map3.get("prchsngOrgNum"));
                lineItemTypeCd = String.valueOf(map3.get("lineItemTypeCd"));
                supNum = String.valueOf(map3.get("supNum"));
                suplPlntCd = String.valueOf(map3.get("suplPlntCd"));

                if (poTypeCd.equals("NB") && lineItemTypeCd.isEmpty()) {

                    processId = iLot142Udf(locationId,productId,vndrNum);
                } else if (poTypeCd.equals("NB") && lineItemTypeCd.equals("3")) {
                    localProductionVersion = method142(matlNum, plntCd,
                            prchsngOrgNum, lineItemTypeCd, supNum);
                    if (localProductionVersion.isEmpty()) {

                        processId = iLot1431Udf(locationId,productId);
                    } else {

                        processId = iLot1432Udf(locationId,productId,localProductionVersion);
                    }
                } else if (poTypeCd.equals("UB") || poTypeCd.equals("ZLA")
                        || poTypeCd.equals("ZNB")) {

                    processId = iLot144Udf(locationId,productId,suplPlntCd, vndrNum);
                } else {
                }
            }
        }
        builder.put("processId", processId);

        String processTypeId = null;

        if (!mfgOrdrDoc.isEmpty()) {

            processTypeId = "Production";
        }


        else{

            Map map4 = method14(poDocNum, poDocLineNbr);
            if (map4 != null) {
                lineItemTypeCd = String.valueOf(map4.get("lineItemTypeCd"));
                poTypeCd = String.valueOf(map4.get("poTypeCd"));
                if (0 == 0) {

                    processTypeId = iLot15Udf(lineItemTypeCd, poTypeCd);
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

        ADFCriteria adfCriteria1 = QueryHelper.buildCriteria("localMaterialNumber").is(localMaterialNumber);
        ADFCriteria adfCriteria2 = QueryHelper.buildCriteria("sourceSystem")
                .is(sourceSystem);
        ADFCriteria groupCriteria19 = adfCriteria2.and(adfCriteria1);

        ADFCriteria adfCriteria = groupCriteria19;
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

    public String method7(String localPlant,String srcSysCd) {

        ADFCriteria adfCriteria3 = QueryHelper.buildCriteria("localPlant").is(
                localPlant);
        ADFCriteria adfCriteria2 = QueryHelper.buildCriteria("sourceSystem")
                .is(srcSysCd);
        ADFCriteria groupCriteria20 = adfCriteria3.and(adfCriteria2);

        ADFCriteria adfCriteria = groupCriteria20;
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
        ADFCriteria adfCriteria4 = QueryHelper.buildCriteria(
                "localMaterialNumber").is(localMaterialNumber);
        ADFCriteria adfCriteria5 = QueryHelper.buildCriteria("localPlant").is(
                localPlant);
        ADFCriteria adfCriteria6 = QueryHelper.buildCriteria("sourceSystem")
                .is(sourceSystem);
        ADFCriteria groupCriteria21 = adfCriteria6.and(adfCriteria5).and(
                adfCriteria4);

        ADFCriteria adfCriteria = groupCriteria21;
        String queryStr = adfCriteria.toQueryString();
        List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
                "/plan/cns_material_plan_status", queryStr, -1);
        if (retList != null && retList.size() > 0) {
            return retList;
        }

        return null;

    }

    public Map method92(String localMaterialNumber) {

        ADFCriteria adfCriteria7 = QueryHelper.buildCriteria(
                "localMaterialNumber").is(localMaterialNumber);
        ADFCriteria groupCriteria22 = adfCriteria7;

        ADFCriteria adfCriteria = groupCriteria22;
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

        if(StringUtils.isBlank(poNum) ||StringUtils.isBlank(poLineNbr))
            return  null;

            ADFCriteria adfCriteria8 = QueryHelper.buildCriteria("poNum").is(poNum);
            ADFCriteria adfCriteria9 = QueryHelper.buildCriteria("poLineNbr").is(
                    poLineNbr);
            ADFCriteria groupCriteria23 = adfCriteria9.and(adfCriteria8);

            ADFCriteria adfCriteria = groupCriteria23;
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

        ADFCriteria adfCriteria10 = QueryHelper.buildCriteria("mfgOrdrNum").is(
                mfgOrdrNum);
        ADFCriteria adfCriteria11 = QueryHelper.buildCriteria("sourceSystem")
                .is(sourceSystem);
        ADFCriteria groupCriteria24 = adfCriteria11.and(adfCriteria10);

        ADFCriteria adfCriteria = groupCriteria24;
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
        ADFCriteria adfCriteria12 = QueryHelper.buildCriteria(
                "localMaterialNumber").is(localMaterialNumber);
        ADFCriteria adfCriteria13 = QueryHelper.buildCriteria("localPlanPlant")
                .is(localPlanPlant);
        ADFCriteria adfCriteria14 = QueryHelper.buildCriteria(
                "localPurchasingOrg").is(localPurchasingOrg);
        ADFCriteria adfCriteria15 = QueryHelper.buildCriteria("infotype").is(
                infotype);
        ADFCriteria adfCriteria16 = QueryHelper.buildCriteria("localvendor")
                .is(localvendor);
        ADFCriteria groupCriteria25 = adfCriteria16.and(adfCriteria15)
                .and(adfCriteria14).and(adfCriteria13).and(adfCriteria12);

        ADFCriteria adfCriteria = groupCriteria25;
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


        if(StringUtils.isBlank(poNum))
            return  null;

        ADFCriteria adfCriteria17 = QueryHelper.buildCriteria("poNum")
                .is(poNum);
        ADFCriteria adfCriteria18 = QueryHelper.buildCriteria("sourceSystem")
                .is(sourceSystem);
        ADFCriteria groupCriteria26 = adfCriteria18.and(adfCriteria17);

        ADFCriteria adfCriteria = groupCriteria26;
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

    private final String iLot1Udf(String stockId,String locationId,String productId,String materialMumber,
                                  String primaryPlanningCode, String srcSysCd, String plntCd,
                                  String lotNum) {
        StringBuffer sb = new StringBuffer();
        if (!primaryPlanningCode.isEmpty()) {
            productId = primaryPlanningCode;
        } else if (!materialMumber.isEmpty()){
            productId = materialMumber;
        }else{
            productId = "";
        }
        sb.append(productId);
        sb.append("/");
        locationId = srcSysCd + "_" + plntCd;
        sb.append(locationId);
        sb.append("/");
        sb.append(lotNum);
        stockId = sb.toString();
        return stockId;
    }

    private final String iLot3Udf(String locationId,String productId,String btchNum) {
        StringBuffer sb = new StringBuffer();
        sb.append(productId);
        sb.append("/");
        sb.append(locationId);
        sb.append("/");
        sb.append(btchNum);
        return sb.toString();
    }

    private final String iLot18Udf(String lineItemTypeCd) {

        if (null == lineItemTypeCd) {
            return "NO";
        }
        if (lineItemTypeCd.equals("2") || lineItemTypeCd.equals("K")) {
            return "YES";
        } else {
            return "NO";
        }
    }

    private final String iLot6Udf(String toBePostedQty, String lotNum,
                                  String qcStsCd) {
        if (toBePostedQty.isEmpty()||toBePostedQty.equals("0.000")) {
            return "invalid";
        } else if (qcStsCd.contains("LTCA") || qcStsCd.contains("UD")) {
            return "invalid";
        } else {
            return lotNum;
        }
    }

    private final String iLot7Udf(String localPlanningrelevant, String plntCd,
                                  String srcSysCd) {

        if(null==localPlanningrelevant){
            return "";
        }
        else if ( localPlanningrelevant.equals("X")) {
            return srcSysCd+"_" + plntCd;
        } else {
            return "";
        }
    }

    private final String iLot15Udf(String lineItemTypeCd, String poTypeCd) {
        if (poTypeCd.equals("NB")) {
            if (lineItemTypeCd.isEmpty()) {
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

    private final String iLot16Udf(String stockId,String lineItemTypeCd) {
        if (lineItemTypeCd != null && lineItemTypeCd.equals("3")) {
            return stockId;
        } else {
            return "";
        }
    }

    private final String iLot9Udf(String primaryPlanningCode,
                                  String materialNumber) {
        if (!primaryPlanningCode.isEmpty()) {
            return primaryPlanningCode;
        } else if (!materialNumber.isEmpty()){
            return materialNumber;
        }else{
            return "";
        }
    }


    private final String iLot10Udf(String inspEndDt) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date s_date = null;
        try {
            s_date = (Date) sdf.parse(inspEndDt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return sdf1.format(s_date);
    }

    private final String iLot141Udf(String locationId,String productId,String prdntVrsnNum) {
        return prdntVrsnNum + "/" + productId + "/" + locationId;
    }

    private final String iLot142Udf(String locationId,String productId,String vndrNum) {
        return "SU/" + productId + "/" + locationId + "/" + vndrNum
                + "/Default";
    }

    private final String iLot1431Udf(String locationId,String productId) {
        return productId + "/" + locationId;
    }

    private final String iLot1432Udf(String locationId,String productId,String localProductionVersion) {
        return localProductionVersion + "/" + productId + "/" + locationId;
    }

    private final String iLot144Udf(String locationId,String productId,String suplPlntCd, String vndrNum) {
        return "TR/"  + productId + "/" + locationId + "/" + suplPlntCd
                + "/" + vndrNum;
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
