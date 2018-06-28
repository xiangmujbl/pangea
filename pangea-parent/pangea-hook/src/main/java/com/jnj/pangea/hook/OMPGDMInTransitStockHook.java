package com.jnj.pangea.hook;

import com.alibaba.fastjson.JSONObject;
import com.jnj.adf.client.api.ADFCriteria;
import com.jnj.adf.client.api.JsonObject;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.view.common.AdfViewHelper;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

public class OMPGDMInTransitStockHook {

    public OMPGDMInTransitStockHook() {
    }
    public static String calculateQuantity(String localNumerator,String quantity,String localDenominator){
        if(StringUtils.isEmpty(localNumerator)||StringUtils.isEmpty(quantity)||StringUtils.isEmpty(localDenominator)){
            return null;
        }
        Integer v = Integer.parseInt(localNumerator);
        Integer v1 = Integer.parseInt(quantity);
        Integer v2 = Integer.parseInt(localDenominator);
        Integer value = v*v1/v2;
        return Integer.toString(value);
    }

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


//    public static void main(String[] args){
//        setStartDateValue("20180531","10");
//        Date date = DateUtil.stringToDate("20180531","yyyyMMdd");
//        String st = DateUtil.dateToString(date,"yyyy/MM/dd HH:mm:ss");
//        System.out.println(st);
//        Date date = DateUtil.stringToDate("2018-06-10 15:11:00","yyyy-MM-dd HH:mm:ss");
//        t(date);
//    }

    //public static void main(String[] args) {
//        String s = OMPGDMInTransitStockHook.localMaterialNumber("000000000000000021");
//        System.out.println(s);
//        System.out.println("000000000000095299".length());
//    }
}
