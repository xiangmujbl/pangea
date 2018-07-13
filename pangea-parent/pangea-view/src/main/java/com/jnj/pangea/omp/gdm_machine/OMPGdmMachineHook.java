package com.jnj.pangea.omp.gdm_machine;

import com.jnj.adf.client.api.ADFCriteria;
import com.jnj.adf.client.api.JsonObject;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.grid.view.common.AdfViewHelper;
import com.jnj.pangea.util.DateUtils;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class OMPGdmMachineHook {

    public static String getDescription(String desc){
        String result="";
        if(desc!=null){
            String[] descList = desc.split("/");
            if(descList.length>0){
                for(int i=0;i<descList.length;i++){
                    if(descList[i].equals("")){
                        continue;
                    }else{
                        result = descList[i];
                        break;
                    }
                }
            }
        }
        return result;
    }

    public static boolean check(RawDataValue rawDataValue){
        boolean rt = false;
        if(rawDataValue !=null){
            Map map=rawDataValue.toMap();
            String srcSysCd=(String)map.get("srcSysCd");
            String plant=(String)map.get("plntCd");
            String valToDt=(String)map.get("vldToDt");
            Map attrMap=getAttr(srcSysCd,plant);
            Map paramterMap=getParamter(srcSysCd);
            boolean bt = isOk(valToDt);
            if(attrMap != null && paramterMap != null && bt){
                rt= false;
            }else{
                rt= true;
            }
        }
        return rt;
    }

    public static Map getAttr(String sourceSystem,String localPlant) {
        ADFCriteria adfCriteria0 = QueryHelper.buildCriteria("sourceSystem").is(
                sourceSystem);
        ADFCriteria adfCriteria1 = QueryHelper.buildCriteria("localPlanningRelevant")
                .is("X");
        ADFCriteria adfCriteria2 = QueryHelper.buildCriteria("planLocTypeId")
                .is("IM");
        ADFCriteria adfCriteria3 = QueryHelper.buildCriteria("localPlant")
                .is(localPlant);
        ADFCriteria groupCriteria4 = adfCriteria3.and(adfCriteria2).and(adfCriteria1).and(adfCriteria0);

        ADFCriteria adfCriteria = groupCriteria4;
        String queryStr = adfCriteria.toQueryString();
        List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
                "/plan/cns_plant_attr", queryStr, -1);
        if (retList != null && retList.size() > 0) {
            Map.Entry<String, String> entry = retList.get(0);
            Map<String, Object> map = JsonObject.append(entry.getValue())
                    .toMap();
            return map;
        }

        return null;

    }


    public static Map getParamter(String sourceSystem) {

        ADFCriteria adfCriteria0 = QueryHelper.buildCriteria("sourceSystem").is(
                sourceSystem);
        ADFCriteria adfCriteria1 = QueryHelper.buildCriteria("dataObject")
                .is("SEND_TO_OMP");
        ADFCriteria adfCriteria2 = QueryHelper.buildCriteria("attribute")
                .is(sourceSystem);
        ADFCriteria groupCriteria3 = adfCriteria2.and(adfCriteria1).and(adfCriteria0);

        ADFCriteria adfCriteria = groupCriteria3;
        String queryStr = adfCriteria.toQueryString();
        List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
                "/plan/cns_plan_parameter", queryStr, -1);
        if (retList != null && retList.size() > 0) {
            Map.Entry<String, String> entry = retList.get(0);
            Map<String, Object> map = JsonObject.append(entry.getValue())
                    .toMap();
            return map;
        }

        return null;

    }

    public static boolean isOk(String valToDt){
        Date current =org.apache.commons.lang3.time.DateUtils.addDays(new Date(),-30);
        Date currentDate=DateUtils.stringToDate(current.toString(),DateUtils.F_yyyyMMdd);
        Date vldToDt=DateUtils.stringToDate(valToDt,DateUtils.F_yyyyMMdd);
        if(vldToDt.getTime()>=currentDate.getTime()){
            return true;
        }
        return false;
    }
}
