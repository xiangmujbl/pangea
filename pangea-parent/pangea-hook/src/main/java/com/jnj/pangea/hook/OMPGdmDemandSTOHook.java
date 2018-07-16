package com.jnj.pangea.hook;

import com.jnj.adf.client.api.JsonObject;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.adf.grid.view.common.AdfViewHelper;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;
import java.util.Map;

public class OMPGdmDemandSTOHook {

    public static String calQuantity(String poNum, String poLineNbr, String poLineQty, String localNumerator, String localDenominator) {
        /*
        Using (purchase_order_oa_v1-poNum) and  (purchase_order_oa_v1-poLineNbr) Check if  records exists where (purchase_order_oa_v1-evTypeCd) = 8
        If yes then get Total of (purchase_order_oa_v1- recvEaQty)
        Check if  (purchase_order_oa_v1- recvEaQty) > 0
        If Yes then add values of field (purchase_order_oa_v1- recvEaQty)

        Then using (purchase_order_oa_v1- poLineQty), (purchase_order_oa_v1 -localNumerator ), (purchase_order_oa_v1 -localDenominator ) from 1st recotd of above fetched multiple record

        ""Open PO Quantity in order Unit"" = [(purchase_order_oa_v1- poLineQty) – Sum  of (purchase_order_oa_v1- recvEaQty)]
        if above statement value = 0 then Skip the Record
        if above statement value is greater than 0 then proceed.
        ""Open PO quantity in base Unit"" =  ""Open PO Quantity in order Unit"" *(purchase_order_oa_v1 -localNumerator ) / (purchase_order_oa_v1 -localDenominator )

        if Total of (purchase_order_oa_v1- recvEaQty) = 0
        Then ""Open PO Quantity in Order Unit"" = (purchase_order_oa_v1- poLineQty)
        ""Open PO Quantity in base unit"" = Open PO Quantity in Order Unit * (purchase_order_oa_v1 -localNumerator ) / (purchase_order_oa_v1 -localDenominator )"
        */

        String queryStr1 = QueryHelper.buildCriteria("poNum").is(poNum).and("poLineNbr").is(poLineNbr).and("evTypeCd").is("8").toQueryString();
        List<Map.Entry<String, String>> list1 = AdfViewHelper.queryForList("/edm/purchase_order_oa_v1_clone", queryStr1);
        Double lastResult = 0d;
        Double sumRecvEaQtyLong = 0d;
        if (list1 != null && list1.size() > 0) {
            for (Map.Entry<String, String> result1 : list1) {
                JsonObject jsonObjkeyMap = JsonObject.append(result1.getValue());
                Map<String, Object> jsonObj = jsonObjkeyMap.toMap();
                sumRecvEaQtyLong += NumberUtils.toDouble(getString(jsonObj, "recvEaQty"));
            }
        }
            // Check if  (purchase_order_oa_v1- recvEaQty) > 0
            // If Yes then add values of field (purchase_order_oa_v1- recvEaQty)

            // Then using (purchase_order_oa_v1- poLineQty), (purchase_order_oa_v1 -localNumerator ), (purchase_order_oa_v1 -localDenominator ) from 1st recotd of above fetched multiple record

            // "Open PO Quantity in order Unit" = [(purchase_order_oa_v1- poLineQty) – Sum  of (purchase_order_oa_v1- recvEaQty)]
            // if above statement value = 0 then Skip the Record
            // if above statement value is greater than 0 then proceed.
            // "Open PO quantity in base Unit" =  "Open PO Quantity in order Unit" *(purchase_order_oa_v1 -localNumerator ) / (purchase_order_oa_v1 -localDenominator )
           /* if (sumRecvEaQtyLong > 0) {
                String queryStr2 = QueryHelper.buildCriteria("poLineQty").is(poLineQty).and("localNumerator").is(localNumerator).and("localDenominator").is(localDenominator).toQueryString();
                List<Map.Entry<String, String>> list2 = AdfViewHelper.queryForList("/edm/purchase_order_oa_v1_clone", queryStr2);
                if (list2 != null && list2.size() > 0) {
                    Map resultMap2 = JsonObject.append(list2.get(0).getValue()).toMap();
                    Double orderUnit2 = NumberUtils.toDouble(getString(resultMap2, "poLineQty")) - sumRecvEaQtyLong;
                    if (orderUnit2 > 0) {
                        lastResult = orderUnit2 * NumberUtils.toDouble(getString(resultMap2, "localNumerator")) / NumberUtils.toDouble(getString(resultMap2, "localDenominator"));
                    } else if (orderUnit2 == 0) {
                        return String.valueOf(0);
                    }
                }
            }
            // if Total of (purchase_order_oa_v1- recvEaQty) = 0
            // Then "Open PO Quantity in Order Unit" = (purchase_order_oa_v1- poLineQty)
            // "Open PO Quantity in base unit" = Open PO Quantity in Order Unit * (purchase_order_oa_v1 -localNumerator ) / (purchase_order_oa_v1 -localDenominator )
            if (sumRecvEaQtyLong == 0) {
                Double orderUnit3 = NumberUtils.toDouble(poLineQty);

                lastResult = orderUnit3 * NumberUtils.toDouble(localNumerator) / NumberUtils.toDouble(localDenominator);*/
           // }

        Double orderUnit = NumberUtils.toDouble(poLineQty) - sumRecvEaQtyLong;
        if( orderUnit < 0 )
            orderUnit=0.0;
        lastResult = orderUnit * NumberUtils.toDouble(localNumerator) / NumberUtils.toDouble(localDenominator);

        return String.valueOf(lastResult.intValue());
    }

    private static String getString(Map<String, Object> m, String field) {
        if (m != null) {
            if (m.containsKey(field)) {
                Object o = m.get(field);
                return o != null ? o.toString().trim() : "";
            } else {
                return "";
            }
        }
        return "";
    }
}
