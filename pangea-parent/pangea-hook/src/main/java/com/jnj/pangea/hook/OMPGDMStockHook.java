package com.jnj.pangea.hook;

import com.jnj.adf.client.api.IBiz;
import java.math.BigDecimal;
import com.jnj.adf.client.api.IRemoteService;
import com.jnj.adf.client.api.JsonObject;
import com.jnj.adf.grid.support.IGridService;
import com.jnj.adf.grid.utils.DateUtil;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.adf.grid.utils.Util;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class OMPGDMStockHook {

    public OMPGDMStockHook() {
    }


    public static String scaleDoubleValue(double value, int scale) {

        BigDecimal bg = new BigDecimal(value);
        return bg.setScale(scale, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString();
    }

    public static String calculateDivide(String factNumrtrMeas, String factDenomMeas) {
        Double factNum = Double.parseDouble(factNumrtrMeas);
        Double factDen = Double.parseDouble(factDenomMeas);
        Double value = factNum/factDen;

        return scaleDoubleValue(value,0);
    }




    public static String maskPassword(String password) {
        int lengthOfPassword = password.length();
        StringBuilder stringBuilder = new StringBuilder(lengthOfPassword);

        for (int i = 0; i < lengthOfPassword; ++i) {
            stringBuilder.append('*');
        }

        return stringBuilder.toString();
    }

    public static Map<String, String> parseList(List<List<String>> list, String[] keyFields) {
        Assert.assertNotNull(list);
        Assert.assertNotNull(keyFields);
        Assert.assertTrue(keyFields.length > 0);
        LinkedHashSet<String> columnSet = new LinkedHashSet();
        List<String> columns = (List) list.get(0);

        int i;
        for (i = 0; i < columns.size(); ++i) {
            columnSet.add(columns.get(i));
        }

        for (i = 0; i < keyFields.length; ++i) {
            Assert.assertTrue(columnSet.contains(keyFields[i]));
        }

        Map<String, String> map = new HashMap();

        for (i = 1; i < list.size(); ++i) {
            List<String> strs = (List) list.get(i);
            JsonObject value = JsonObject.create();
            Iterator<String> it = columnSet.iterator();

            int j;
            for (j = 0; it.hasNext(); ++j) {
                value.append((String) it.next(), (String) strs.get(j));
            }

            JsonObject key = JsonObject.create();

            for (j = 0; j < keyFields.length; ++j) {
                String columnName = keyFields[j];
                key.append(columnName, StringUtils.trim(value.getValue(columnName)));
            }

            map.put(key.toJson(), value.toJson());
        }

        return map;
    }

    public static Map<String, String> parseListNoKeyInValue(List<List<String>> list, String[] keyFields) {
        return parseList(list, keyFields, (String) null, (String) null);
    }

    public static Map<String, String> parseList(List<List<String>> list, String[] keyFields, String dynamicKeyField, String holdValue) {
        Assert.assertNotNull(list);
        Assert.assertNotNull(keyFields);
        Assert.assertTrue(keyFields.length > 0);
        LinkedHashSet<String> columnSet = new LinkedHashSet();
        List<String> columns = (List) list.get(0);

        for (int i = 0; i < columns.size(); ++i) {
            columnSet.add(columns.get(i));
        }

        SortedSet<String> keySet = new TreeSet();

        for (int i = 0; i < keyFields.length; ++i) {
            Assert.assertTrue(columnSet.contains(keyFields[i]));
            keySet.add(keyFields[i]);
        }

        Map<String, String> map = new HashMap();

        for (int i = 1; i < list.size(); ++i) {
            List<String> strs = (List) list.get(i);
            JsonObject fullValue = JsonObject.create();
            JsonObject value = JsonObject.create();
            Iterator<String> it = columnSet.iterator();

            for (int j = 0; it.hasNext(); ++j) {
                String columnName = (String) it.next();
                if (!keySet.contains(columnName)) {
                    value.append(columnName, (String) strs.get(j));
                }

                fullValue.append(columnName, (String) strs.get(j));
            }

            JsonObject key = JsonObject.create();
            keySet.forEach((columnNamex) -> {
                if (StringUtils.equals(dynamicKeyField, columnNamex)) {
                    key.append(columnNamex, holdValue);
                } else {
                    key.append(columnNamex, StringUtils.trim(fullValue.getValue(columnNamex)));
                }

            });
            map.put(key.toJson(), value.toJson());
        }

        return map;
    }



    public static void waitAqComplete(String fullPath) {
        IRemoteService<IGridService> srv = IBiz.remote(IGridService.class);
        long start = System.currentTimeMillis();
        int total = 0;

        int num;
        do {
            num = ((IGridService) srv.onServer(fullPath)).getQueueSize(fullPath);
            total += num;
            if (num > 0) {
                LogUtil.getCoreLog().info("Wati aq flush, size:{} ", new Object[]{num});
                Util.sleep(200L);
            }
        } while (num > 0);

        LogUtil.getCoreLog().info("AQ flushed. totalSize:{} cost:{}", new Object[]{total, System.currentTimeMillis() - start});
        Util.sleep(2000L);
    }

    public static String setQuantityValue(String ordrQty,String rcvdQty,String fctrNmrtrMeas,String fctrDnmntrMeas){
        int ordrQty_num = Integer.valueOf(ordrQty);
        int rcvdQty_num = Integer.valueOf(rcvdQty);
        int fctrNmrtrMeas_num = Integer.valueOf(fctrNmrtrMeas);
        int fctrDnmntrMeas_num = Integer.valueOf(fctrDnmntrMeas);
        int num_1 = ordrQty_num - rcvdQty_num;
        int num_2 = num_1 * fctrNmrtrMeas_num;
        String quantityValue = num_2 / fctrDnmntrMeas_num+"";

        return quantityValue;
    }

    public static String setStartDateValue(String prdtnSchdEndDt,String goodRcptLdDaysQty){
        Date date = DateUtil.stringToDate(prdtnSchdEndDt,"yyyyMMdd");
        prdtnSchdEndDt = DateUtil.dateToString(date,"yyyy/MM/dd HH:mm:ss");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            date = dateFormat.parse(prdtnSchdEndDt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, Integer.valueOf(goodRcptLdDaysQty));
        String startDateValue = dateFormat.format(calendar.getTime());
        if(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
            calendar.add(Calendar.DATE, 2);
            startDateValue = dateFormat.format(calendar.getTime());
        } else if(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
            calendar.add(Calendar.DATE, 1);
            startDateValue = dateFormat.format(calendar.getTime());
        }
        System.out.println(startDateValue);
        return startDateValue;
    }

    public static String setReceiptDateValue(String receiptDateValue){
        Date date = DateUtil.stringToDate(receiptDateValue,"yyyyMMdd");
        receiptDateValue = DateUtil.dateToString(date,"yyyy/MM/dd HH:mm:ss");
        return receiptDateValue;
    }

//    public static void main(String[] args){
//        setStartDateValue("20180531","10");
//        Date date = DateUtil.stringToDate("20180531","yyyyMMdd");
//        String st = DateUtil.dateToString(date,"yyyy/MM/dd HH:mm:ss");
//        System.out.println(st);
//        Date date = DateUtil.stringToDate("2018-06-10 15:11:00","yyyy-MM-dd HH:mm:ss");
//        t(date);
//        System.out.println(setReceiptDateValue("20170626"));
//    }

}
