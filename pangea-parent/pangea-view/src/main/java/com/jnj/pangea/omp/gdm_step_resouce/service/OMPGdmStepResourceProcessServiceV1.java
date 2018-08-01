package com.jnj.pangea.omp.gdm_step_resouce.service;

import com.jnj.adf.client.api.ADFCriteria;
import com.jnj.adf.client.api.JsonObject;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.adf.grid.view.common.AdfViewHelper;
import com.jnj.inner.StringInner;
import com.jnj.pangea.util.DateUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class OMPGdmStepResourceProcessServiceV1 {

    private static OMPGdmStepResourceProcessServiceV1 instance;
    public static OMPGdmStepResourceProcessServiceV1 getInstance(){
        if(instance == null){
            instance = new OMPGdmStepResourceProcessServiceV1();
        }
        return  instance;
    }



    public Boolean checkCnsPlanParameter4J1_1(String sourceSysCdParam) {
        ADFCriteria adfCriteria1 = QueryHelper.buildCriteria("sourceSystem").is("CONS_LATAM")
                .and("dataObject").is("ALL")
                .and("attribute").is("SEND_TO_OMP")
                .and("parameter").is("SYSTEMNAME")
                .and("inclExcl").is("I")
                .and("parameterValue").is(sourceSysCdParam);

        String queryStr = adfCriteria1.toQueryString();

        LogUtil.getCoreLog().info("-------/plan/cns_plan_parameter-----query1>>" + queryStr);
        List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
                "/plan/cns_plan_parameter", queryStr, -1);
        if (retList != null && retList.size() > 0) {
            return true;
        }
        return false;
    }

    public Boolean checckCnsPlanParameter4J1_2(String sourceSysCdParam, String actRlseDt) {
        Boolean ret = false;
        ADFCriteria adfCriteria1 = QueryHelper.buildCriteria("sourceSystem").is(sourceSysCdParam)
                .and("dataObject").is("GDMSTEPRESOURCE")
                .and("attribute").is("SEND_TO_OMP")
                .and("parameter").is("DAYS")
                .and("inclExcl").is("I");

        String queryStr = adfCriteria1.toQueryString();

        LogUtil.getCoreLog().info("-------/plan/cns_plan_parameter-----query2>>" + queryStr);
        List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
                "/plan/cns_plan_parameter", queryStr, -1);
        if (retList != null && retList.size() > 0) {
            Map.Entry<String, String> entry = retList.get(0);
            Map<String, Object> map = JsonObject.append(entry.getValue())
                    .toMap();
            String paramValue = map.get("parameterValue").toString();
            if(checkDateBtwRegion(actRlseDt,paramValue)){
                ret = true;
            }
        }
        return ret;
    }

    public Boolean checkCnsPlanParameter4J1_3(String sourceSysCdParam,String mfgOrdrSttsCd) {
        ADFCriteria adfCriteria1 = QueryHelper.buildCriteria("sourceSystem").is(sourceSysCdParam)
                .and("dataObject").is("PROSTATUS")
                .and("attribute").is("SEND_TO_OMP")
                .and("parameter").is("ORDER")
                .and("inclExcl").is("I");

        String queryStr = adfCriteria1.toQueryString();

        LogUtil.getCoreLog().info("-------/plan/cns_plan_parameter-----query3>>" + queryStr);
        List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
                "/plan/cns_plan_parameter", queryStr, -1);
        if (retList != null && retList.size() > 0) {
            for( Map.Entry<String, String> entry:retList){
                Map<String, Object> map = JsonObject.append(entry.getValue())
                        .toMap();
                String paramValue = map.get("parameterValue").toString();
                if(StringInner.isStringNotEmpty(paramValue) && mfgOrdrSttsCd.contains(paramValue)){
                    return  true;
                }
            }
        }
        return false;
    }

    public Boolean checkCnsPlanParameter4J1_4(String sourceSysCdParam,String mfgOrdrSttsCd) {
        ADFCriteria adfCriteria1 = QueryHelper.buildCriteria("sourceSystem").is(sourceSysCdParam)
                .and("dataObject").is("PROSTATUS")
                .and("attribute").is("SEND_TO_OMP")
                .and("parameter").is("ORDER")
                .and("inclExcl").is("E");

        String queryStr = adfCriteria1.toQueryString();

        LogUtil.getCoreLog().info("-------/plan/cns_plan_parameter-----query4>>" + queryStr);
        List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
                "/plan/cns_plan_parameter", queryStr, -1);
        if (retList != null && retList.size() > 0) {
            for (Map.Entry<String, String> itm: retList) {
                Map<String, Object> map = JsonObject.append(itm.getValue()).toMap();

                String paramValue = map.get("parameterValue").toString();
                if(StringInner.isStringNotEmpty(paramValue) && mfgOrdrSttsCd.contains(paramValue)){
                    return  false;
                }
            }
        }
        return true;
    }

    public Boolean checkCnsPlanParameter4J1_5(String sourceSysCdParam, String parameterValue) {
        ADFCriteria adfCriteria1 = QueryHelper.buildCriteria("sourceSystem").is(sourceSysCdParam)
                .and("dataObject").is("GDMSTEPRESOURCE")
                .and("attribute").is("SEND_TO_OMP")
                .and("parameter").is("LOCATIONTYPE")
                .and("inclExcl").is("I")
                .and("parameterValue").is(parameterValue);

        String queryStr = adfCriteria1.toQueryString();

        LogUtil.getCoreLog().info("-------/plan/cns_plan_parameter-----query5>>" + queryStr);
        List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
                "/plan/cns_plan_parameter", queryStr, -1);
        if (retList != null && retList.size() > 0) {
            return  true;
        }
        return false;
    }

    public Boolean checkCnsPlantAttr(String sourceSysCd, String plntCd) {
        String queryStr = QueryHelper.buildCriteria("sourceSystem").is(sourceSysCd)
                .and("localPlanningRelevant").is("X")
//                .and("planLocTypeId").is("IM")
                .and("localPlant").is(plntCd)
                .toQueryString();

        LogUtil.getCoreLog().info("-------/plan/cns_plant_attr-----query>>" + queryStr);
        List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
                "/plan/cns_plant_attr", queryStr, -1);
        if (retList != null && retList.size() > 0) {
            for (Map.Entry<String,String> itm:
                    retList) {

                Map<String, Object> map = JsonObject.append(itm.getValue())
                        .toMap();
                String param = map.get("planLocTypeId").toString();

                if(StringInner.isStringNotEmpty(param) && checkCnsPlanParameter4J1_5(sourceSysCd,param)){
                    return true;
                }
            }
        }

        return false;

    }

    public Map joinMfgOrderItm(String sourceSysCdParam, String mfgOrdrNumParam, String plntCd) {
        String queryStr = QueryHelper.buildCriteria("srcSysCd").is(sourceSysCdParam)
                .and("mfgOrdrNum").is(mfgOrdrNumParam)
                .and("plntCd").is(plntCd).toQueryString();

        LogUtil.getCoreLog().info("-------/edm/mfg_order_itm-----query>>" + queryStr);
        List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
                "/edm/mfg_order_itm", queryStr, -1);
        if (retList != null && retList.size() > 0) {
            Map.Entry<String, String> entry = retList.get(0);
            Map<String, Object> map = JsonObject.append(entry.getValue())
                    .toMap();
            return map;
        }

        return null;
    }

    public Map joinMaltProdVersn(String srcSysCd, String matlNum,
                                 String plntCd, String prdntVrsnNum) {

        ADFCriteria adfCriteria = QueryHelper.buildCriteria("srcSysCd").is(srcSysCd)
                .and("matlNum").is(matlNum)
                .and("plntCd").is(plntCd)
                .and("prdntVrsnNum").is(prdntVrsnNum);

        String queryStr = adfCriteria.toQueryString();
        LogUtil.getCoreLog().info("-------/edm/matl_prod_versn-----query>>" + queryStr);
        List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
                "/edm/matl_prod_versn", queryStr, -1);
        if (retList != null && retList.size() > 0) {
            Map.Entry<String, String> entry = retList.get(0);
            Map<String, Object> map = JsonObject.append(entry.getValue())
                    .toMap();
            return map;
        }
        return null;
    }

    public Map joinMfgRtngItmNde(String srcSysCd, String rtngTypCd,
                                 String rntgGrpCd, String rtngGrpCntrNbr) {

        ADFCriteria adfCriteria = QueryHelper.buildCriteria("srcSysCd").is(srcSysCd)
                .and("rtngTypCd").is(rtngTypCd)
                .and("rtngGrpCd").is(rntgGrpCd)
                .and("rtngGrpCntrNbr").is(rtngGrpCntrNbr);

        String queryStr = adfCriteria.toQueryString();
        LogUtil.getCoreLog().info("-------/edm/mfg_rtng_itm_nde-----query>>" + queryStr);
        List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
                "/edm/mfg_rtng_itm_nde", queryStr, -1);
        if (retList != null && retList.size() > 0) {
            Map.Entry<String, String> entry = retList.get(0);
            Map<String, Object> map = JsonObject.append(entry.getValue())
                    .toMap();
            return map;
        }

        return null;
    }

    public Map joinMfgRtgParm(String srcSysCd, String rtgTypeCd,
                              String rtgGrpCd, String rtgNodeNum) {

        ADFCriteria adfCriteria17 = QueryHelper.buildCriteria("srcSysCd").is(
                srcSysCd);
        ADFCriteria adfCriteria18 = QueryHelper.buildCriteria("rtgTypeCd").is(
                rtgTypeCd);
        ADFCriteria adfCriteria19 = QueryHelper.buildCriteria("rtgGrpCd").is(
                rtgGrpCd);
        ADFCriteria adfCriteria20 = QueryHelper.buildCriteria("rtgNodeNum").is(
                rtgNodeNum);
        ADFCriteria adfCriteria21 = QueryHelper.buildCriteria("charCd").is(
                "EFICIENCIA");
        ADFCriteria groupCriteria37 = adfCriteria21.and(adfCriteria20)
                .and(adfCriteria19).and(adfCriteria18).and(adfCriteria17);

        ADFCriteria adfCriteria = groupCriteria37;
        String queryStr = adfCriteria.toQueryString();
        LogUtil.getCoreLog().info("-------/edm/mfg_rtg_parm-----query>>" + queryStr);
        List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
                "/edm/mfg_rtg_parm", queryStr, -1);
        if (retList != null && retList.size() > 0) {
            retList.sort((e1, e2) -> JsonObject
                    .append(e1.getValue())
                    .getValue("intrnlPrcsInstrNum")
                    .compareTo(
                            JsonObject.append(e2.getValue()).getValue(
                                    "intrnlPrcsInstrNum")));
            Map.Entry<String, String> entry = retList.get(retList.size() - 1);
            Map<String, Object> map = JsonObject.append(entry.getValue())
                    .toMap();
            return map;
        }

        return null;

    }

    public List joinMfgOrderRtng(String srcSysCdParam, String ordrRtngNumParam) {
        String queryStr = QueryHelper.buildCriteria("srcSysCd").is(srcSysCdParam)
                .and("ordrRtngNum").is(ordrRtngNumParam).toQueryString();

        LogUtil.getCoreLog().info("-------/edm/mfg_order_rtng-----query>>" + queryStr);
        List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
                "/edm/mfg_order_rtng", queryStr, -1);
        if (retList != null && retList.size() > 0) {
            return retList;
        }

        return new ArrayList<>();
    }

    public Boolean checkT430(String operCdParam) {
        ADFCriteria adfCriteria = QueryHelper.buildCriteria("steus").is(operCdParam)
                .and("term").is("X");

        String queryStr = adfCriteria.toQueryString();
        LogUtil.getCoreLog().info("-------/project_one/t430-----query>>" + queryStr);
        List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
                "/project_one/t430", queryStr, -1);
        if (retList != null && retList.size() > 0) {
            return true;
        }
        return false;
    }

    public Map joinWrkCtr(String srcSysCd, String wrkCtrNum) {

        ADFCriteria adfCriteria = QueryHelper.buildCriteria("srcSysCd").is(srcSysCd)
                .and("wrkCtrNum").is(wrkCtrNum)
                .and("wrkCtrUsgCd").in("001", "009");

        String queryStr = adfCriteria.toQueryString();
        LogUtil.getCoreLog().info("-------/edm/wrk_ctr-----query>>" + queryStr);
        List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
                "/edm/wrk_ctr", queryStr, -1);
        if (retList != null && retList.size() > 0) {
            Map.Entry<String, String> entry = retList.get(0);
            Map<String, Object> map = JsonObject.append(entry.getValue())
                    .toMap();
            return map;
        }

        return null;

    }

    public  List joinWrkCtrCapy(String srcSysCd, String wrkCtrNum, String wrkCtrTypeCd){
        String queryStr = QueryHelper.buildCriteria("srcSysCd").is(srcSysCd)
                .and("wrkCtrNum").is(wrkCtrNum)
                .and("wrkCtrTypeCd").is(wrkCtrTypeCd).toQueryString();

        LogUtil.getCoreLog().info("-------/edm/wrk_ctr_capy-----query>>" + queryStr);
        List<Map.Entry<String,String>> retLst = AdfViewHelper.queryForList("/edm/wrk_ctr_capy",queryStr,-1);
        if(retLst != null && retLst.size() > 0){
            return  retLst;
        }

        return  new ArrayList<>();
    }

    public Map joinCapyHdr4T3(String srcSysCd, String capyNum) {
        ADFCriteria adfCriteria = QueryHelper.buildCriteria("srcSysCd").is(srcSysCd)
                .and("capyNum").is(capyNum)
                .and("capyCatCd").is("001");

        String queryStr = adfCriteria.toQueryString();
        LogUtil.getCoreLog().info("-------/edm/capy_hdr-----queryt3>>" + queryStr);
        List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
                "/edm/capy_hdr", queryStr, -1);
        if (retList != null && retList.size() > 0) {
            Map.Entry<String, String> entry = retList.get(0);
            Map<String, Object> map = JsonObject.append(entry.getValue())
                    .toMap();
            return map;
        }

        return null;
    }

    public Map joinCapyHdr4T7(String srcSysCd, String capyNum) {
        ADFCriteria adfCriteria = QueryHelper.buildCriteria("srcSysCd").is(srcSysCd)
                .and("capyNum").is(capyNum)
                .and("capyCatCd").is("002");

        String queryStr = adfCriteria.toQueryString();
        LogUtil.getCoreLog().info("-------/edm/capy_hdr-----queryt7>>" + queryStr);
        List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
                "/edm/capy_hdr", queryStr, -1);
        if (retList != null && retList.size() > 0) {
            Map.Entry<String, String> entry = retList.get(0);
            Map<String, Object> map = JsonObject.append(entry.getValue())
                    .toMap();
            return map;
        }

        return null;

    }

    private Boolean checkDateBtwRegion(String actRlseDt, String parameterValue) {
        Boolean ret = true;
        String paramActRlseDt = checkStrNull4Num(actRlseDt);
        String paramValue = checkStrNull4Num(parameterValue);

        String currentDate = DateUtils.currentDate(DateUtils.F_yyyyMMdd);
        Date fromDa = DateUtils.stringToDate(currentDate, DateUtils.F_yyyyMMdd);
        Date paramDa = DateUtils.stringToDate(paramActRlseDt, DateUtils.F_yyyyMMdd);

        try {
            Integer offsetDay = Integer.parseInt(paramValue);
            Date toDa = DateUtils.offsetDate(fromDa, -offsetDay);
            if (toDa.compareTo(fromDa) > 0) {
                if (paramDa.getTime() < fromDa.getTime() || paramDa.getTime() > toDa.getTime()) {
                    ret = false;
                }
            } else {
                if (paramDa.getTime() > fromDa.getTime() || paramDa.getTime() < toDa.getTime()) {
                    ret = false;
                }
            }
        } catch (NumberFormatException ex) {
            LogUtil.getCoreLog().error(ex.getMessage());
            ret = false;
        }
        return ret;
    }

    public String checkStrNull4Num(String str) {
        return StringUtils.isEmpty(str) || StringUtils.equals("null", str)? "0": str;
    }

    public String checkNull4Str(String str){
        return StringUtils.isEmpty(str) || StringUtils.equals("null", str)? "": str;
    }
}
