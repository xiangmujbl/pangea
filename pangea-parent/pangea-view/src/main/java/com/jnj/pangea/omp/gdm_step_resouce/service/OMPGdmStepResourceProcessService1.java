package com.jnj.pangea.omp.gdm_step_resouce.service;

import com.jnj.adf.client.api.ADFCriteria;
import com.jnj.adf.client.api.JsonObject;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.view.common.AdfViewHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OMPGdmStepResourceProcessService1 {

    public Map joinCnsPlanParameter(String sourceSysCdParam) {

        ADFCriteria adfCriteria1 = QueryHelper.buildCriteria("sourceSystem")
                .is(sourceSysCdParam);
        ADFCriteria adfCriteria2 = QueryHelper.buildCriteria("dataObject").is(
                "SEND_TO_OMP");
        ADFCriteria adfCriteria3 = QueryHelper.buildCriteria("attribute").is(
                sourceSysCdParam);
        ADFCriteria groupCriteria41 = adfCriteria3.and(adfCriteria2).and(
                adfCriteria1);

        ADFCriteria adfCriteria = groupCriteria41;
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

    public Map joinCnsPlanParameterOther(String sourceSysCd) {

        ADFCriteria adfCriteria4 = QueryHelper.buildCriteria("sourceSystem")
                .is(sourceSysCd);
        ADFCriteria adfCriteria5 = QueryHelper.buildCriteria("dataObject").is(
                "SEND_TO_OMP");
        ADFCriteria adfCriteria6 = QueryHelper.buildCriteria("attribute").is(
                "GDMSTEPRESOURCE");
        ADFCriteria groupCriteria42 = adfCriteria6.and(adfCriteria5).and(
                adfCriteria4);

        ADFCriteria adfCriteria = groupCriteria42;
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

    public Map joinMfgOrderItm(String sourceSysCdParam, String mfgOrdrNumParam) {

        ADFCriteria adfCriteria7 = QueryHelper.buildCriteria("srcSysCd").is(
                sourceSysCdParam);
        ADFCriteria adfCriteria8 = QueryHelper.buildCriteria("mfgOrdrNum").is(
                mfgOrdrNumParam);
        ADFCriteria groupCriteria43 = adfCriteria8.and(adfCriteria7);

        ADFCriteria adfCriteria = groupCriteria43;
        String queryStr = adfCriteria.toQueryString();
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

        ADFCriteria adfCriteria9 = QueryHelper.buildCriteria("srcSysCd").is(
                srcSysCd);
        ADFCriteria adfCriteria10 = QueryHelper.buildCriteria("matlNum").is(
                matlNum);
        ADFCriteria adfCriteria11 = QueryHelper.buildCriteria("plntCd").is(
                plntCd);
        ADFCriteria adfCriteria12 = QueryHelper.buildCriteria("prdntVrsnNum")
                .is(prdntVrsnNum);
        ADFCriteria groupCriteria44 = adfCriteria12.and(adfCriteria11)
                .and(adfCriteria10).and(adfCriteria9);

        ADFCriteria adfCriteria = groupCriteria44;
        String queryStr = adfCriteria.toQueryString();
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

        ADFCriteria adfCriteria13 = QueryHelper.buildCriteria("srcSysCd").is(
                srcSysCd);
        ADFCriteria adfCriteria14 = QueryHelper.buildCriteria("rtngTypCd").is(
                rtngTypCd);
        ADFCriteria adfCriteria15 = QueryHelper.buildCriteria("rtngGrpCd").is(
                rntgGrpCd);
        ADFCriteria adfCriteria16 = QueryHelper.buildCriteria("rtngGrpCntrNbr")
                .is(rtngGrpCntrNbr);
        ADFCriteria groupCriteria45 = adfCriteria16.and(adfCriteria15)
                .and(adfCriteria14).and(adfCriteria13);

        ADFCriteria adfCriteria = groupCriteria45;
        String queryStr = adfCriteria.toQueryString();
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
        ADFCriteria groupCriteria46 = adfCriteria21.and(adfCriteria20)
                .and(adfCriteria19).and(adfCriteria18).and(adfCriteria17);

        ADFCriteria adfCriteria = groupCriteria46;
        String queryStr = adfCriteria.toQueryString();
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

    public Map joinMfgRtngItm(String srcSysCd, String rtngTypCd,
                              String rtngGrpCd, String rtngItmNum, String operNum) {

        ADFCriteria adfCriteria22 = QueryHelper.buildCriteria("srcSysCd").is(
                srcSysCd);
        ADFCriteria adfCriteria23 = QueryHelper.buildCriteria("rtngTypCd").is(
                rtngTypCd);
        ADFCriteria adfCriteria24 = QueryHelper.buildCriteria("rtngGrpCd").is(
                rtngGrpCd);
        ADFCriteria adfCriteria25 = QueryHelper.buildCriteria("rtngItmNum").is(
                rtngItmNum);
        ADFCriteria adfCriteria26 = QueryHelper.buildCriteria("operNum").is(
                operNum);
        ADFCriteria groupCriteria47 = adfCriteria26.and(adfCriteria25)
                .and(adfCriteria24).and(adfCriteria23).and(adfCriteria22);

        ADFCriteria adfCriteria = groupCriteria47;
        String queryStr = adfCriteria.toQueryString();
        List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
                "/edm/mfg_rtng_itm", queryStr, -1);
        if (retList != null && retList.size() > 0) {
            Map.Entry<String, String> entry = retList.get(0);
            Map<String, Object> map = JsonObject.append(entry.getValue())
                    .toMap();
            return map;
        }

        return null;

    }

    public Map joinMfgOrderClone(String srcSysCd, String rtngTypCd,
                                 String rtngGrpCd, String rtngGrpCntrNum) {

        ADFCriteria adfCriteria27 = QueryHelper.buildCriteria("sourceSysCd")
                .is(srcSysCd);
        ADFCriteria adfCriteria28 = QueryHelper.buildCriteria("rtngTypCd").is(
                rtngTypCd);
        ADFCriteria adfCriteria29 = QueryHelper.buildCriteria("rtngGrpCd").is(
                rtngGrpCd);
        ADFCriteria adfCriteria30 = QueryHelper.buildCriteria("rtngGrpCntrNum")
                .is(rtngGrpCntrNum);
        ADFCriteria groupCriteria48 = adfCriteria30.and(adfCriteria29)
                .and(adfCriteria28).and(adfCriteria27);

        ADFCriteria adfCriteria = groupCriteria48;
        String queryStr = adfCriteria.toQueryString();
        List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
                "/edm/mfg_order_clone", queryStr, -1);
        if (retList != null && retList.size() > 0) {
            Map.Entry<String, String> entry = retList.get(0);
            Map<String, Object> map = JsonObject.append(entry.getValue())
                    .toMap();
            return map;
        }

        return null;

    }

    public List joinMfgOrderRtng(String srcSysCdParam, String ordrRtngNumParam) {

        ADFCriteria adfCriteria31 = QueryHelper.buildCriteria("srcSysCd").is(
                srcSysCdParam);
        ADFCriteria adfCriteria32 = QueryHelper.buildCriteria("ordrRtngNum")
                .is(ordrRtngNumParam);
        ADFCriteria groupCriteria49 = adfCriteria32.and(adfCriteria31);

        ADFCriteria adfCriteria = groupCriteria49;
        String queryStr = adfCriteria.toQueryString();
        List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
                "/edm/mfg_order_rtng", queryStr, -1);
        if (retList != null && retList.size() > 0) {
            return retList;
        }

        return new ArrayList<>();

    }

    public Map joinT430(String operCdParam) {

        ADFCriteria adfCriteria33 = QueryHelper.buildCriteria("steus").is(
                operCdParam);
        ADFCriteria adfCriteria34 = QueryHelper.buildCriteria("term").is("X");
        ADFCriteria groupCriteria50 = adfCriteria34.and(adfCriteria33);

        ADFCriteria adfCriteria = groupCriteria50;
        String queryStr = adfCriteria.toQueryString();
        List<Map.Entry<String, String>> retList = AdfViewHelper.queryForList(
                "/project_one/t430", queryStr, -1);
        if (retList != null && retList.size() > 0) {
            Map.Entry<String, String> entry = retList.get(0);
            Map<String, Object> map = JsonObject.append(entry.getValue())
                    .toMap();
            return map;
        }

        return null;

    }

    public Map joinWrkCtr(String srcSysCd, String wrkCtrNum) {

        ADFCriteria adfCriteria35 = QueryHelper.buildCriteria("srcSysCd").is(
                srcSysCd);
        ADFCriteria adfCriteria36 = QueryHelper.buildCriteria("wrkCtrNum").is(
                wrkCtrNum);
        ADFCriteria adfCriteria37 = QueryHelper.buildCriteria("wrkCtrUsgCd")
                .in("001", "009");
        ADFCriteria groupCriteria51 = adfCriteria37.and(adfCriteria36).and(
                adfCriteria35);

        ADFCriteria adfCriteria = groupCriteria51;
        String queryStr = adfCriteria.toQueryString();
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

    public List joinWrkCtrCapy(String srcSysCd, String wrkCtrTypeCd, String wrkCtrNum) {
        String query = QueryHelper.buildCriteria("srcSysCd").is(srcSysCd)
                .and("wrkCtrTypeCd").is(wrkCtrTypeCd)
                .and("wrkCtrNum").is(wrkCtrNum).toQueryString();

        List<Map.Entry<String, String>> findLst = AdfViewHelper.queryForList("/edm/wrk_ctr_capy", query, -1);

        return new ArrayList<>();
    }

    public Map joinCapyHdr(String srcSysCd, String capyNum, String capyCatCd) {

        ADFCriteria adfCriteria38 = QueryHelper.buildCriteria("srcSysCd").is(
                srcSysCd);
        ADFCriteria adfCriteria39 = QueryHelper.buildCriteria("capyNum").is(
                capyNum);
        ADFCriteria adfCriteria40 = QueryHelper.buildCriteria("capyCatCd").is(
                capyCatCd);
        ADFCriteria groupCriteria52 = adfCriteria40.and(adfCriteria39).and(
                adfCriteria38);

        ADFCriteria adfCriteria = groupCriteria52;
        String queryStr = adfCriteria.toQueryString();
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

}
