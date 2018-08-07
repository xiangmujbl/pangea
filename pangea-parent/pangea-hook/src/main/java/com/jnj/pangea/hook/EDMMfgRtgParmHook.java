package com.jnj.pangea.hook;
import com.jnj.adf.client.api.ADFCriteria;
import com.jnj.adf.client.api.JsonObject;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.grid.view.common.AdfViewHelper;

import java.util.List;
import java.util.Map;
public class EDMMfgRtgParmHook {

    public static   boolean processmfgrtgparm(RawDataValue e){
        Map<String, Object> mape = (Map<String, Object>) e.toMap();
        if (mape.get("loekz").toString().trim().equals("X")) {
            return  true;
        }
        String plnty = (String) mape.get("plnty");
        String plnnr = (String) mape.get("plnnr");
        String plnkn = (String) mape.get("plnkn");
        String plnph = (String) mape.get("plnph");
        List listJ12 = J1_2(plnty, plnnr, plnkn, plnph);
        if (listJ12 == null) return true;
            return false;
    }

    public static  List J1_2(String plnty, String plnnr, String plnkn, String plnph) {
        ADFCriteria adfCriteria4 = QueryHelper.buildCriteria("plnty").is(plnty);
        ADFCriteria adfCriteria5 = QueryHelper.buildCriteria("plnnr").is(plnnr);
        ADFCriteria adfCriteria6 = QueryHelper.buildCriteria("plnkn").is(plnkn);
        ADFCriteria adfCriteria7 = QueryHelper.buildCriteria("plnph").is(plnph);
        ADFCriteria adfCriteria8 = QueryHelper.buildCriteria("topnr").is(10);
        ADFCriteria groupCriteria10 = adfCriteria8.and(adfCriteria7).and(adfCriteria6).and(adfCriteria5).and(adfCriteria4);
        ADFCriteria adfCriteria = groupCriteria10;
        String queryStrx = adfCriteria.toQueryString();
        List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList("/project_one/plph", queryStrx, -1);
        if (retList != null && retList.size() > 0) {
            return retList;
        }
        return null;
    }
    }


