package com.jnj.pangea.hook;

import com.jnj.adf.client.api.ADFCriteria;
import com.jnj.adf.client.api.JsonObject;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.data.raw.RawDataBuilder;
import com.jnj.adf.grid.view.common.AdfViewHelper;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/*
 * Create by IntelliJ IDEA
 * @author:zhiqiang.Tao
 * @date:2018/6/12 15:48
 * @describe:CustomMethod
 */
public class OMPGdmForecastHook {

    public static void main(String[] args) {
//        String Quantity = handleQuantity("1", "2", "0");
//        System.out.println("value of:" + Quantity);
//
//        String data = stampToDate(String.valueOf(System.currentTimeMillis()));
//        System.out.println(data);
        System.out.println(CycleStartDate("20180525"));
//
//
//        List<Map.Entry<String, String>> list = new ArrayList<>();
//        Map.Entry<String, String> map = (Map.Entry<String, String>) new HashMap<Object, Object>();
//        ((HashMap) map).put("calWeek", "201836");
//        ((HashMap) map).put("weekFromDate", "2018/9/6");
//        Map<String, Object> map1 = new HashMap<>();
//        map1.put("calWeek", "201832");
//        map1.put("weekFromDate", "2018-09-03");
//
//        list.add(map);
//        list.add(map1);
//        System.out.println(sortOfCalWeek(list));
//
//        System.out.println(getDueDate("2018/09/03 00:00:00"));

    }


    //format date
    public static String stampToDate(String s) {
        String res;
        String data;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        data = res + " " + "00:00:00";
        return data;
    }

    //exchange date
    public static String CycleStartDate(String s) {
        String data;
        String s1 = s.substring(0,4);
        String s2 = s.substring(4,8);
        String s3 = s2.substring(0,2);
        String s4 = s2.substring(2,4);

        String s5 = s1 + "/" + s3 + "/" +s4;
        data = s5 + " " + "00:00:00";
        return data;
    }


    //get duDate from sort by CalWeek from jnj
    public static String getDuDateFromSortOfCalWeek(List<Map.Entry<String, String>> list) {
        List<Map<String, Object>> list1 = new ArrayList<>();

        for (Map.Entry<String, String> map : list) {
            Map<String, Object> map1 = JsonObject.append(map.getValue()).toMap();

            list1.add(map1);
        }

        if (list != null) {
            Collections.sort(list1, (o1, o2) -> {
                Double name1 = Double.valueOf(Double.valueOf(o1.get("calWeek").toString()));
                Double name2 = Double.valueOf(Double.valueOf(o2.get("calWeek").toString()));
                return name1.compareTo(name2);
            });
        }

        Map map2 = list1.get(0);

        String weekToDate = String.valueOf(map2.get("weekToDate"));

        if (weekToDate.equals("")){
            return "";
        }

        String weekToDate1 = weekToDate.replaceAll("-", "/") + " " + "00:00:00";

        return weekToDate1;
    }


    //get duDate from jnj first record
    public static String getDuDateFromFirstRecord(List<Map.Entry<String, String>> list) {
        String weekToDate = "";
        List<Map<String, Object>> list1 = new ArrayList<>();

        for (Map.Entry<String, String> map : list) {
            Map<String, Object> map1 = JsonObject.append(map.getValue()).toMap();
            list1.add(map1);

        }

        if (list != null) {
            Map map1 = list1.get(0);
            String weekToDate1 = String.valueOf(map1.get("weekToDate"));
            if (weekToDate1.equals("")){
                return "";
            }
            weekToDate = weekToDate1.replaceAll("-", "/") + " " + "00:00:00";
        }

        return weekToDate;
    }


    //get fromDuDate from sort by CalWeek from jnj
    public static String getFromDueDateBysortOfCalWeek(List<Map.Entry<String, String>> list) {
        List<Map<String, Object>> list1 = new ArrayList<>();

        for (Map.Entry<String, String> map : list) {
            Map<String, Object> map1 = JsonObject.append(map.getValue()).toMap();

            list1.add(map1);
        }

        if (list != null) {
            Collections.sort(list1, (o1, o2) -> {
                Double name1 = Double.valueOf(Double.valueOf(o1.get("calWeek").toString()));
                Double name2 = Double.valueOf(Double.valueOf(o2.get("calWeek").toString()));
                return name1.compareTo(name2);
            });
        }

        Map map2 = list1.get(0);

        String weekFromDate = String.valueOf(map2.get("weekFromDate"));

        if (!StringUtils.isNotEmpty(weekFromDate)){
            return "";
        }

        String weekFromDate1 = weekFromDate.replaceAll("-", "/") + " " + "00:00:00";

        return weekFromDate1;
    }


    //get fromDuDate from jnj first record
    public static String getFromDueDateByFirstRecord(List<Map.Entry<String, String>> list) {
        String weekFromDate = "";
        List<Map<String, Object>> list1 = new ArrayList<>();

        for (Map.Entry<String, String> map : list) {
            Map<String, Object> map1 = JsonObject.append(map.getValue()).toMap();
            list1.add(map1);

        }

        if (list != null) {
            Map map1 = list1.get(0);
            String weekFromDate1 = String.valueOf(map1.get("weekFromDate"));
            if (!StringUtils.isNotEmpty(weekFromDate1)){
                return "";
            }
            weekFromDate = weekFromDate1.replaceAll("-", "/") + " " + "00:00:00";
        }

        return weekFromDate;
    }



    //check any one
    public static String CheckAnyOne(List<Map.Entry<String, String>> list, String localPlant, Map<String, RawDataBuilder> failMap) {
        String productId = "";
        if (list != null) {
            for (Map.Entry<String, String> map : list) {
                Map<String, Object> mapMaterial = JsonObject.append(map.getValue()).toMap();
                String localMaterialNumber = String.valueOf(mapMaterial.get("localMaterialNumber"));
                String sourceSystem = String.valueOf(mapMaterial.get("sourceSystem"));

                if (StringUtils.isNotEmpty(sourceSystem) && StringUtils.isNotEmpty(localMaterialNumber) && StringUtils.isNotEmpty(localPlant)) {
                    Map<String, Object> mapCnsMaterial = getCnsMaterial(sourceSystem, localPlant, localMaterialNumber);
                    if (mapCnsMaterial != null) {
                        String spRelevant = String.valueOf(mapCnsMaterial.get("spRelevant"));
                        if (spRelevant.equals("X")) {
                            productId = String.valueOf(mapMaterial.get("primaryPlanningCode"));
                        } else {
                            return "";
                        }
                    } else {
                        return "";
                    }
                }
            }
        }
        return productId;
    }


    //deal quantity
    public static String getQuantity(List<Map.Entry<String, String>> list, String localUom, String quantity, Map<String, RawDataBuilder> failMap) {

        String Quantity = "";
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                //get first record
                Map<String, Object> mapMaterial = JsonObject.append(list.get(0).getValue()).toMap();
                String localMaterialNumber = String.valueOf(mapMaterial.get("localMaterialNumber"));
                String sourceSystem = String.valueOf(mapMaterial.get("sourceSystem"));
                String localBaseUom = String.valueOf(mapMaterial.get("localBaseUom"));

                if (localUom.equals(localBaseUom)) {
                    Quantity = quantity;
                } else {
                    if (StringUtils.isNotEmpty(sourceSystem) && StringUtils.isNotEmpty(localMaterialNumber) && StringUtils.isNotEmpty(localUom)) {

                        Map<String, Object> MaterialAumo = getMaterialAumo(sourceSystem, localMaterialNumber, localUom);

                        if (MaterialAumo != null) {
                            String localNumerator = String.valueOf(MaterialAumo.get("localNumerator"));
                            String localDenominator = String.valueOf(MaterialAumo.get("localDenominator"));
                            Quantity = handleQuantity(quantity, localNumerator, localDenominator);
                        } else {
                            return null;
                        }
                    }

                }
            }
        }
        return Quantity;
    }


    //get DueDate from FRC11
    public static String getDueDate(String FromDueDate) {
        String DueDate = null;
        String[] arr = FromDueDate.split(" ");
        for (int i = 0; i < arr.length - 1; i++) {
            DueDate = arr[0] + " " + "00:00:01";
        }
        return DueDate;
    }


    /*
     * Create by IntelliJ IDEA
     * @author:zhiqiang.Tao
     * @date:2018/6/12 15:50
     * @describe:process quantity
     */
    public static String handleQuantity(String quantity, String localNumerator, String localDenominator) {

        if (!localDenominator.equals("0")) {

            double Quantity1 = Double.valueOf(quantity) * Double.valueOf(localNumerator)
                    / Double.valueOf(localDenominator);

            Double Quantity2 = new Double(Quantity1);
            int Quantity3 = Quantity2.intValue();

            String Quantity = String.valueOf(Quantity3);

            return Quantity;
        }

        return "";

    }



    public static Map getCnsMaterial(String sourceSystem, String localPlant,
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



    public static Map getMaterialAumo(String sourceSystem, String localMaterialNumber,
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

}
