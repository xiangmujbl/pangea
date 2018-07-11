package com.jnj.pangea.hook;

import com.alibaba.fastjson.JSONObject;
import com.jnj.adf.client.api.ADFCriteria;
import com.jnj.adf.client.api.JsonObject;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.adf.grid.view.common.AdfViewHelper;
import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class OMPGDMInTransitStockHook {

    public OMPGDMInTransitStockHook() {
    }
    public static String calculateQuantity(String localNumerator,String quantity,String localDenominator){
        if(StringUtils.isEmpty(localNumerator)||StringUtils.isEmpty(quantity)||StringUtils.isEmpty(localDenominator)){
            return null;
        }
        double v = Double.parseDouble(localNumerator);
        double v1 = Double.parseDouble(quantity);
        double v2 = Double.parseDouble(localDenominator);
        double value = v*v1/v2;
        DecimalFormat decimalFormat = new DecimalFormat("0.0000000");
        String format = decimalFormat.format(value);
        return format;
    }

   /* public static void main(String[] args) {
        String s = OMPGDMInTransitStockHook.calculateQuantity("3", "225", "10");
        System.out.println(s);
    }*/

    public static String QueryCnsIntransitstock(String productId,String sourceSystem,String demandGroup){
        if(!StringUtils.isEmpty(productId)||!StringUtils.isEmpty(sourceSystem)||!StringUtils.isEmpty(demandGroup)){
        int count = 0;
        ADFCriteria adfCriteria6 = QueryHelper.buildCriteria("productId").is(
                productId);
        ADFCriteria adfCriteria7 = QueryHelper.buildCriteria("sourceSystem").is(
                sourceSystem);
        ADFCriteria adfCriteria8 = QueryHelper.buildCriteria("demandGroup").is(
                demandGroup);
        ADFCriteria groupCriteria10 = adfCriteria8.and(adfCriteria7).and(
                adfCriteria6);
        ADFCriteria adfCriteria = groupCriteria10;
        String queryStr = adfCriteria.toQueryString();
        List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
                "/plan/cns_intransitstock_clone", queryStr, -1);
        if (retList != null && retList.size() > 0) {
            for (Map.Entry<String, String> stringStringEntry : retList) {
                JSONObject jsonObject = JSONObject.parseObject(stringStringEntry.getValue());
                String  productId1 = (String)jsonObject.get("productId");
                String  uom = (String)jsonObject.get("uom");
                String  quantity = (String)jsonObject.get("quantity");
                if(StringUtils.isNotEmpty(productId1)&&StringUtils.isNotEmpty(uom)){
                    String s1 = OMPGDMInTransitStockHook.localMaterialNumber(productId1);
                    Map map = OMPGDMInTransitStockHook.QueryAuom(uom, s1);
                    if (null!=map&&!map.isEmpty()) {
                        String localNumerator = (String)map.get("localNumerator");
                        String localDenominator = (String)map.get("localDenominator");
                        String s = OMPGDMInTransitStockHook.calculateQuantity(localNumerator, quantity, localDenominator);
                        if(null!=s){
                            count += Integer.parseInt(s);
                        }
                    }
                }
            }
        }
        if(count>0){
            return Integer.toString(count);
        }
        return null;
        }
        return null;
    }

    public static Map QueryAuom(String localAuom, String localMaterialNumber) {
        if(StringUtils.isEmpty(localAuom)||StringUtils.isEmpty(localMaterialNumber)){
            return null;
        }
        ADFCriteria adfCriteria3 = QueryHelper.buildCriteria("localAuom").is(localAuom);
        ADFCriteria adfCriteria4 = QueryHelper.buildCriteria("localMaterialNumber").is(localMaterialNumber);
        ADFCriteria groupCriteria9 = adfCriteria4.and(adfCriteria3);

        ADFCriteria adfCriteria = groupCriteria9;
        String queryStr = adfCriteria.toQueryString();
        List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList("/edm/material_auom_v1", queryStr, -1);
        if (retList != null && retList.size() > 0) {
            Map.Entry<String, String> entry = retList.get(0);
            Map<String, Object> map = JsonObject.append(entry.getValue()).toMap();
            return map;
        }

        return null;

    }



    public static  String localMaterialNumber(String productId){
        if(StringUtils.isNotEmpty(productId)){
            int a = 18-productId.length();
            StringBuilder stringBuilder = new StringBuilder();
            for(int x = 0;x<a;x++){
                stringBuilder.append("0");
            }
            stringBuilder.append(productId);
            return stringBuilder.toString();
        }
        return null;
    }

    public static String getproductId (String productId) {
        String newStr = productId.replaceAll("^(0+)", "");
        return newStr;
    }


    public static String getDemandGroup(String customerId,String sourceCountry,String hiytp,String date){
        Map queryKnvhentitymap   = OMPGDMInTransitStockHook.QueryKnvhEntity(customerId,sourceCountry,hiytp,date);

        if(queryKnvhentitymap==null){
            return null;
        }
        Map hkunnr = OMPGDMInTransitStockHook.QueryGrpAsgnWithKnvh((String) queryKnvhentitymap.get("hkunnr"));
        if(hkunnr!=null){
            if(StringUtils.isNotEmpty((String)hkunnr.get("demandGroup"))){
                return (String)hkunnr.get("demandGroup");
            }

        }
        Map queryKnvhentitymap2   = OMPGDMInTransitStockHook.QueryKnvhEntity((String)queryKnvhentitymap.get("hkunnr"),sourceCountry,hiytp,date);
        if(queryKnvhentitymap2==null){
            return null;
        }
        Map hkunnr2 = OMPGDMInTransitStockHook.QueryGrpAsgnWithKnvh((String) queryKnvhentitymap.get("hkunnr"));
        if(hkunnr2!=null){
            if(StringUtils.isNotEmpty((String)hkunnr2.get("demandGroup"))){
                return (String)hkunnr.get("demandGroup");
            }else {
                return null;
            }

        }else {
            return null;
        }


    }
    public static String getUUID(){
        return  UUID.randomUUID().toString().replaceAll("-", "");
    }

   /* public static void main(String[] args) {
        System.out.println(OMPGDMInTransitStockHook.getUUID());
    }*/

    public static Map QueryKnvhEntity(String kunnr, String vkorg, String hiytp,
                               String datbi) {
        if(StringUtils.isEmpty(kunnr)||StringUtils.isEmpty(vkorg)||StringUtils.isEmpty(hiytp)||StringUtils.isEmpty(kunnr)||StringUtils.isEmpty(datbi)){
            return null;
        }
        ADFCriteria adfCriteria10 = QueryHelper.buildCriteria("kunnr")
                .is(kunnr);
        ADFCriteria adfCriteria11 = QueryHelper.buildCriteria("vkorg")
                .startsWith(vkorg);
        ADFCriteria adfCriteria12 = QueryHelper.buildCriteria("hiytp")
                .is(hiytp);
        ADFCriteria adfCriteria13 = QueryHelper.buildCriteria("datbi")
                .greaterThanEqual(datbi);

        ADFCriteria groupCriteria18 = adfCriteria13.and(adfCriteria12)
                .and(adfCriteria11).and(adfCriteria10);

        ADFCriteria adfCriteria = groupCriteria18;
        String queryStr = adfCriteria.toQueryString();
        LogUtil.getCoreLog().info("QueryKnvhEntity    StringQuery"+queryStr);
        List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
                "/project_one/knvh", queryStr, -1);
        if (retList != null && retList.size() > 0) {
            Map.Entry<String, String> entry = retList.get(0);
            Map<String, Object> map = JsonObject.append(entry.getValue())
                    .toMap();
            return map;
        }

        return null;

    }
    public static Map QueryGrpAsgnWithKnvh(String customerId) {
        if(StringUtils.isEmpty(customerId)){
            return null;
        }

        ADFCriteria adfCriteria9 = QueryHelper.buildCriteria("customerId").is(
                customerId);
        ADFCriteria groupCriteria17 = adfCriteria9;

        ADFCriteria adfCriteria = groupCriteria17;
        String queryStr = adfCriteria.toQueryString();
        LogUtil.getCoreLog().info("QueryGrpAsgnWithKnvh    StringQuery"+queryStr);

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

 /*   public static void main(String[] args) {
        String s = OMPGDMInTransitStockHook.calculateQuantity("0.34", "1.00", "9.00");
        System.out.println(s);
    }*/
}
