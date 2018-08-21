package com.jnj.pangea.omp.step_resource.controller;

import com.jnj.adf.client.api.JsonObject;
import com.jnj.pangea.omp.step_resource.controller.StepResourceController;
import com.jnj.adf.client.api.ADFCriteria;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.grid.utils.DateUtil;
import com.jnj.adf.grid.utils.JsonUtils;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.adf.grid.view.common.AdfViewHelper;
import org.apache.commons.lang3.StringUtils;
import com.jnj.pangea.omp.step_resource.controller.StepResourceController;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class StepResourceHook {
    public static String formatNum(String charVal){
        String retStr = "0.000";
        DecimalFormat df = new DecimalFormat("0.000");
        if(StringUtils.isNotBlank(charVal)){
            try{        retStr = df.format(Double.parseDouble(charVal.replaceAll(",",
        ".")));
            }catch (NumberFormatException ne){
                LogUtil.getCoreLog().error(ne.getMessage());
            }
        }
        return retStr;
    }
    public static boolean filterraw(RawDataValue raw) {
        if (raw == null) {
            LogUtil.getCoreLog().info(" raw is null>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            return true;
        }
        Map<String, Object> map;
        try {
            map = (Map<String, Object>) raw.toMap();
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.getCoreLog().info("raw fail to change to map>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            return true;
        }
        try {
            ADFCriteria adfCriteria5 = QueryHelper.buildCriteria("parameterValue").is(String.valueOf(map.get("srcSysCd")));
            ADFCriteria adfCriteria = QueryHelper.buildCriteria("attribute").is("SEND_TO_OMP");
            ADFCriteria adfCriteria1 = QueryHelper.buildCriteria("dataObject").is("ALL");
            ADFCriteria adfCriteria2 = QueryHelper.buildCriteria("sourceSystem").is("CONS_LATAM");
            ADFCriteria adfCriteria3 = QueryHelper.buildCriteria("parameter").is("SYSTEMNAME");
            ADFCriteria adfCriteria4 = QueryHelper.buildCriteria("inclExcl").is("I");
            String str = adfCriteria5.and(adfCriteria4).and(adfCriteria3).and(adfCriteria2).and(adfCriteria1).and(adfCriteria).toQueryString();
            Map.Entry<String, Map<String, Object>> map2 = AdfViewHelper.queryForMap("/plan/cns_plan_parameter", str);
            if (map2 == null) {
                LogUtil.getCoreLog().info("map2===============================================================================null");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.getCoreLog().info("fail to get /plan/cns_plan_parameter map2");
            return true;
        }
        List<Map.Entry<String, String>> cnslist;
        try {
            ADFCriteria adfCriteriaAA = QueryHelper.buildCriteria("sourceSystem").is(String.valueOf(map.get("srcSysCd")));
            ADFCriteria adfCriteriaBB = QueryHelper.buildCriteria("dataObject").is("PP");
            ADFCriteria adfCriteriaCC = QueryHelper.buildCriteria("attribute").is("SEND_TO_OMP");
            ADFCriteria adfCriteriaDD = QueryHelper.buildCriteria("inclExcl").is("I");
            ADFCriteria adfCriteriaFF = QueryHelper.buildCriteria("parameter").is("PLANT");
            ADFCriteria adfCriteriaEE = QueryHelper.buildCriteria("parameterValue").in(String.valueOf(map.get("plntCd")));
            String cnsstr = adfCriteriaFF.and(adfCriteriaEE).and(adfCriteriaDD).and(adfCriteriaCC).and(adfCriteriaBB).and(adfCriteriaAA).toQueryString();
            cnslist = AdfViewHelper.queryForList("/plan/cns_plan_parameter", cnsstr);
            if (cnslist == null) {
                LogUtil.getCoreLog().info("cnslist========================================================================null");
                return true;
            }
        } catch (Exception e) {
            LogUtil.getCoreLog().info("fail to get /plan/cns_plan_parameter cnslist");
            return true;
        }

        try {
            ADFCriteria cns_plant_attr1 = QueryHelper.buildCriteria("steus").is(String.valueOf(map.get("operCd")));
            ADFCriteria cns_plant_attr2 = QueryHelper.buildCriteria("term").is("X");
            String qurtyt430 = cns_plant_attr2.and(cns_plant_attr1).toQueryString();
            Map.Entry<String, Map<String, Object>> mapattr = AdfViewHelper.queryForMap("/project_one/t430", qurtyt430);
            if (mapattr == null) {
                return true;
            }
        } catch (Exception e) {
            LogUtil.getCoreLog().info(" t430 " + e);
        }

      StepResourceController     sRC=new StepResourceController();
        List<Map.Entry<String, String>>   ListX  =   sRC.X1(String.valueOf(map.get("plntCd")),String.valueOf(map.get("rtngTypCd")),String.valueOf(map.get("rntgGrpCd")),String.valueOf(map.get("rntgGrpCntrNbr")));boolean flag=true;
        if (ListX == null || ListX.size() == 0) return true;
            for( int i=0;i<ListX.size();i++ ) {
                Map<String,Object>       mapX =     JsonUtils.jsonToObject(ListX.get(i).getKey(), Map.class);
         List<Map.Entry<String, String>> listS = sRC.cns_material_plan_status(String.valueOf(mapX.get("srcSysCd")), String.valueOf(mapX.get("matlNum")), String.valueOf(mapX.get("plntCd")));
         for(int j=0;j<listS.size();j++){
             Map<String,Object>       mapS =     JsonUtils.jsonToObject(listS.get(i).getKey(), Map.class);
            if("X".equals(String.valueOf( mapS.get("spRelevant")))|| "X".equals(String.valueOf(mapS.get("noPlanRelevant")))){
                flag=false;
            }
         }
     }
     return flag;
//        if (retList != null && retList.size() > 0) {
//            Map.Entry<String, String> entry = retList.get(0);
//            Map<String, Object> map = JsonObject.append(entry.getValue())
//                    .toMap();
//            if ("X".equals(String.valueOf(map.get("spRelevant"))) || "X".equals(String.valueOf(map.get("noPlanRelevant"))))
//                ;
//            {
//                return matlNum;
//            }
            //       <set name="ProductId" value="matlNum">
            //     </set>
            //</if>


//        try {
//            ADFCriteria matl_prod_versn1 = QueryHelper.buildCriteria("steus").is(String.valueOf(map.get("operCd")));
//
//
//        MATL_PROD_VERSN-srcSysCd = cns_material_plan_status-sourceSystem AND
//        MATL_PROD_VERSN-matlNum = cns_material_plan_status-localMaterialNumber AND
//        MATL_PROD_VERSN-plntCd = cns_material_plan_status-localPlant AND
          //      ( cns_material_plan_status-spRelevant = 'X' OR cns_material_plan_status-noPlanRelevant = 'X' )




     //   return false;
    }


    /**
     * rulet5
     *
     * @param X4Map
     * @return
     */
    public static String setResourceIdT5(Map X4Map) {

        String strreturn;
        if (X4Map == null) {
            return "";
        }

        if ("001".equals(String.valueOf(X4Map.get("wrkCtrUsgCd"))) == false & "009".equals(String.valueOf(X4Map.get("wrkCtrUsgCd"))) == false) {
            return "";

        }
        if ("X".equals(X4Map.get("delInd"))) {
            return "";

        }
        if (daysbetween(String.valueOf(X4Map.get("vldFromDt"))) < 30) {
            return "";
        }
        Map.Entry<String, Map<String, Object>> Entryctrcpy;
        try {
            if (String.valueOf(X4Map.get("srcSysCd")) != null && String.valueOf(X4Map.get("wrkCtrTypeCd")) != null && String.valueOf(X4Map.get("wrkCtrNum")) != null) {
                ADFCriteria adfCriteriawtA = QueryHelper.buildCriteria("srcSysCd").is(String.valueOf(X4Map.get("srcSysCd")));
                ADFCriteria adfCriteriawtB = QueryHelper.buildCriteria("wrkCtrTypeCd").is(String.valueOf(X4Map.get("wrkCtrTypeCd")));
                ADFCriteria adfCriteriawtC = QueryHelper.buildCriteria("wrkCtrNum").is(String.valueOf(X4Map.get("wrkCtrNum")));
                String strctrcapy = adfCriteriawtC.and(adfCriteriawtB).and(adfCriteriawtA).toQueryString();
                Entryctrcpy = AdfViewHelper.queryForMap("/edm/wrk_ctr_capy", strctrcapy);
            } else {
                return "";
            }
            LogUtil.getCoreLog().info("Entryctrcpy>>>>>>>>>>>>>>>>>>>>>" + Entryctrcpy);
            if (Entryctrcpy == null) {
                System.out.println("entry is null==============================t5===========================");
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.getCoreLog().info(" fail to get /edm/wrk_ctr_capy exception ");
            return "";
        }
        Map.Entry<String, Map<String, Object>> capyentry = null;
        try {
            if (String.valueOf(Entryctrcpy.getValue().get("srcSysCd")) != null && String.valueOf(Entryctrcpy.getValue().get("capyNum")) != null) {
                ADFCriteria adfCriteriawt3 = QueryHelper.buildCriteria("srcSysCd").is(String.valueOf(Entryctrcpy.getValue().get("srcSysCd")));
                ADFCriteria adfCriteriawt31 = QueryHelper.buildCriteria("capyCatCd").is("002");
                ADFCriteria adfCriteriawt32 = QueryHelper.buildCriteria("capyNum").is(String.valueOf(Entryctrcpy.getValue().get("capyNum")));
                LogUtil.getCoreLog().info("---------------third---------------");
                String str = adfCriteriawt32.and(adfCriteriawt31).and(adfCriteriawt3).toQueryString();
                capyentry = AdfViewHelper.queryForMap("/edm/capy_hdr", str);
            } else {
                LogUtil.getCoreLog().info("Entryctrcpy.getValue()" + Entryctrcpy.getValue());
                LogUtil.getCoreLog().info(" AdfViewHelper.queryForMap(\"/edm/capy_hdr\", str) capyentry=null=========================================================================");
                return "";
            }


            if (capyentry == null) {
                LogUtil.getCoreLog().debug("capyentry is null====t5===XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                return "";
            }

        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.getCoreLog().info(" fail to get /edm/capy_hdr exception ");
            return "";
        }
        String str1 = String.valueOf(capyentry.getValue().get("srcSysCd"));
        String str22 = String.valueOf(capyentry.getValue().get("plntCd"));
        String str3 = String.valueOf(capyentry.getValue().get("capyNm"));
        strreturn = StringUtils.join(str1, "_", str22, "/", str3);
        System.out.println("============strreturn================" + strreturn);
        return strreturn;
    }


    public static String getsteus(Map<String, Object> retList3Map) {
        String str = retList3Map.get("steus").toString();
        return str;
    }

    /**
     * rule t8
     *
     * @param map
     * @param retList1Map
     * @param retList3Map
     * @param X4Map
     * @return
     */
    public static String StepResourceId(Map map, Map retList1Map, Map retList3Map, Map X4Map, String resourceId) {
        String str = null;
        if (map != null && retList3Map != null) {
            String str3 = String.valueOf(X4Map.get("wrkCtrCd"));
            String str5 = String.valueOf(retList1Map.get("prdntVrsnNum"));
            String str6 = String.valueOf(Long.valueOf(String.valueOf(map.get("matlNum"))));
            String str7 = String.valueOf(retList3Map.get("operNum"));
            str = resourceId + '/' + str3 + '/' + str5 + '/' + str6 + '/' + str7;

        }
        return str;
    }

    public static String currentDate()
    {
        Calendar c = Calendar.getInstance();
        String monthS = "";String dayS = "";
        int year = c.get(1);
        int month = c.get(2) + 1;
        if (month < 10) {
            monthS = "0" + month;
        } else {
            monthS = String.valueOf(month);
        }
        int day = c.get(5);
        if (day < 10) {
            dayS = "0" + day;
        } else {
            dayS = String.valueOf(day);
        }
        String str = "" + year + monthS + dayS;
        return str;
    }


    public static String C1(Map map1, Map map, Map map3) {
        String str = null;
        String str1 = String.valueOf(map1.get("prdntVrsnNum"));
        String str2 = String.valueOf(Long.valueOf(String.valueOf(map1.get("matlNum"))));
        String str3 = String.valueOf(map.get("srcSysCd"));
        String str4 = String.valueOf(map.get("plntCd"));
        String str5 = String.valueOf(map3.get("operNum"));
        str = StringUtils.join(str1, "/", str2, "/", str3, "_", str4, "/", str5);
        return str;
    }

    public static int daysbetween(String o) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Long time1 = DateUtil.toLong(o);
        Long time2 = DateUtil.toLong(sdf.format(new Date()));
        Long days = (Math.abs(time2 - time1)) / 1000 / 24 / 3600;
        return days.intValue();
    }


    public static String setT3MachineId(Map<String, Object> X4Map) {
        String strreturn = null;
        if ("001".equals(String.valueOf(X4Map.get("wrkCtrUsgCd"))) == false & "009".equals(String.valueOf(X4Map.get("wrkCtrUsgCd"))) == false) {
            return "";
        }
        if ("X".equals(X4Map.get("delInd"))) {
            return "";
        }
        try {
            if (daysbetween(String.valueOf(X4Map.get("vldFromDt"))) < 30) {
                return "";
            }
            Map.Entry<String, Map<String, Object>> Entryctrcpy = null;
            if (String.valueOf(X4Map.get("srcSysCd")) != null && String.valueOf(X4Map.get("wrkCtrTypeCd")) != null && String.valueOf(X4Map.get("wrkCtrNum")) != null) {
                ADFCriteria adfCriteriawtA = QueryHelper.buildCriteria("srcSysCd").is(String.valueOf(X4Map.get("srcSysCd")));
                ADFCriteria adfCriteriawtB = QueryHelper.buildCriteria("wrkCtrTypeCd").is(String.valueOf(X4Map.get("wrkCtrTypeCd")));
                ADFCriteria adfCriteriawtC = QueryHelper.buildCriteria("wrkCtrNum").is(String.valueOf(X4Map.get("wrkCtrNum")));
                String strctrcapy = adfCriteriawtC.and(adfCriteriawtB).and(adfCriteriawtA).toQueryString();
                Entryctrcpy = AdfViewHelper.queryForMap("/edm/wrk_ctr_capy", strctrcapy);
            } else {
                return "";
            }
            if (Entryctrcpy == null) {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            LogUtil.getCoreLog().info("fail to get /edm/wrk_ctr_capy strctrcapyt3");
            return "";
        }
        Map.Entry<String, Map<String, Object>> Entry;
        if (String.valueOf(X4Map.get("srcSysCd")) != null && String.valueOf(X4Map.get("capyNum")) != null) {
            ADFCriteria adfCriteriawt3 = QueryHelper.buildCriteria("srcSysCd").is(String.valueOf(X4Map.get("srcSysCd")));
            ADFCriteria adfCriteriawt31 = QueryHelper.buildCriteria("capyCatCd").is("001");
            ADFCriteria adfCriteriawt32 = QueryHelper.buildCriteria("capyNum").is(String.valueOf(X4Map.get("capyNum")));
            String str = adfCriteriawt32.and(adfCriteriawt31).and(adfCriteriawt3).toQueryString();
            LogUtil.getCoreLog().info("T3>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + str);
            Entry = AdfViewHelper.queryForMap("/edm/capy_hdr", str);
        } else {
            return "";
        }
        if (Entry == null) return "";
        Map mapcapyhdr = Entry.getValue();//.toMap());
        if (mapcapyhdr.isEmpty()) return "";
        String str1 = String.valueOf(X4Map.get("srcSysCd"));
        String str22 = String.valueOf(X4Map.get("plntCd"));
        String str3 = String.valueOf(X4Map.get("wrkCtrCd"));
        strreturn = StringUtils.join(str1, "_", str22, "/", str3);
        return strreturn;
    }
}
