package com.jnj.pangea.omp.gdm_link;

import com.alibaba.fastjson.JSONObject;
import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.inner.DateInner;
import com.jnj.inner.StringInner;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.edm.EDMMfgRtngItmDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanParameterDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMfgRtngItmEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanParameterEntity;
import com.jnj.pangea.util.DateUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OMPGdmLinkMasterHook {


    private static PlanCnsPlanParameterDaoImpl planCnsPlanParameterDao = PlanCnsPlanParameterDaoImpl.getInstance();
    private static EDMMfgRtngItmDaoImpl mfgRtngItmDao = EDMMfgRtngItmDaoImpl.getInstance();

    public static String getLinkId(String prdntVrsnNum, String matlNum, String plntCd, List<Map.Entry<String, String>> mfgRtngItmNdeList,
                                   String operNum, String rtngSqncNum, String currentRtngNdeNum, List<Map.Entry<String, String>> mfgRtngItmList, String rtngSqncCtgryCd) {
        Map<String, String> operNumMap = getOperNum(operNum, currentRtngNdeNum, mfgRtngItmNdeList, mfgRtngItmList, rtngSqncCtgryCd);
        String linkId = StringInner.join(prdntVrsnNum, "/", matlNum, "/", plntCd, "/", operNumMap.get("previousOperNum"), "/", operNumMap.get("currentOperNum"), "/", rtngSqncNum);
        return linkId;
    }

    public static String getPrevOperationId(String prdntVrsnNum, String matlNum, String plntCd, List<Map.Entry<String, String>> mfgRtngItmNdeList, String currentRtngNdeNum,String operNum, List<Map.Entry<String, String>> mfgRtngItmList,String rtngSqncCtgryCd) {
        Map<String, String> operNumMap = getOperNum(operNum, currentRtngNdeNum, mfgRtngItmNdeList, mfgRtngItmList, rtngSqncCtgryCd);
        String prevOperationId = "";
        if(operNumMap!=null ){
            if(StringUtils.isNotBlank(operNumMap.get("previousOperNum"))){
                prevOperationId = StringInner.join(prdntVrsnNum, "/", matlNum, "/", plntCd, "/", operNumMap.get("previousOperNum"));
            }
        }
        return prevOperationId;
    }

    public static String getEndEff_T5(String rtgItemEndDate, String valToDt) {
        String endEff = "";
        if(StringUtils.isNotBlank(rtgItemEndDate)&&StringUtils.isNotBlank(valToDt)){
            Date endDate = DateInner.stringToDate(rtgItemEndDate + IConstant.VALUE.HHMMSS, DateUtils.J_yyyyMMdd_HHmmss);
            Date toDate = DateInner.stringToDate(valToDt + IConstant.VALUE.HHMMSS, DateUtils.F_yyyyMMddHHmmss_);
            Date compareDate = DateInner.stringToDate(IConstant.VALUE.ENDEFF, DateUtils.J_yyyyMMdd_HHmmss);
            if (endDate.after(toDate)) {
                if (toDate.after(compareDate)) {
                    endEff = IConstant.VALUE.ENDEFF;
                } else {
                    endEff = DateInner.dateToString(toDate, DateUtils.J_yyyyMMdd_HHmmss);
                }
            } else {
                if (endDate.after(compareDate)) {
                    endEff = IConstant.VALUE.ENDEFF;
                } else {
                    endEff = DateInner.dateToString(endDate, DateUtils.J_yyyyMMdd_HHmmss);
                }
            }
        }
        return endEff;
    }

    public static String getStartEff_T10(String valFromDt_itm, String valFrom) {
        String startEff = "";
        if(StringUtils.isNotBlank(valFromDt_itm) && StringUtils.isNotBlank(valFrom)){
            Date fromDt_itm = DateInner.stringToDate(valFromDt_itm + IConstant.VALUE.HH_NN_SS_ZERO, DateUtils.F_yyyyMMddHHmmss_);
            Date FromDt_versn = DateInner.stringToDate(valFrom + IConstant.VALUE.HH_NN_SS_ZERO, DateUtils.F_yyyyMMddHHmmss_);
            Date compareDate = DateInner.stringToDate(IConstant.VALUE.STARTEFF_HMS, DateUtils.J_yyyyMMdd_HHmmss);
            if (fromDt_itm.after(FromDt_versn)) {
                if (fromDt_itm.after(compareDate)) {
                    startEff = DateInner.dateToString(fromDt_itm, DateUtils.J_yyyyMMdd_HHmmss);
                } else {
                    startEff = IConstant.VALUE.STARTEFF_HMS;
                }
            } else {
                if (FromDt_versn.after(compareDate)) {
                    startEff = DateInner.dateToString(FromDt_versn, DateUtils.J_yyyyMMdd_HHmmss);
                } else {
                    startEff = IConstant.VALUE.STARTEFF_HMS;
                }
            }
        }
        return startEff;
    }

    public static boolean check(RawDataValue rawDataValue) {
        boolean rt = false;
        if (rawDataValue != null) {
            Map map = rawDataValue.toMap();
            String srcSysCd = (String) map.get("srcSysCd");
            List<PlanCnsPlanParameterEntity> paramterEntityList = planCnsPlanParameterDao.getEntitiesWithConditions(srcSysCd, "SEND_TO_OMP", srcSysCd);
            if (paramterEntityList.size() > 0) {
                rt = false;
            } else {
                rt = true;
            }
        }
        return rt;
    }

    public static Map<String, String> getOperNum(String operNum, String currentRtngNdeNum, List<Map.Entry<String, String>> mfgRtngItmNdeList,
                                                 List<Map.Entry<String, String>> mfgRtngItmList, String rtngSqncCtgryCd) {
        Map<String, String> operNumMap = new HashMap<String, String>();
        String currentOperNum = "";
        String previousOperNum = "";
        JSONObject firstObject = JSONObject.parseObject(mfgRtngItmNdeList.get(0).getValue());
        String firstEntity = (String) firstObject.get("rtngNdeNum");
        if (mfgRtngItmNdeList != null && mfgRtngItmNdeList.size() > 0) {
            if (firstEntity.equals(currentRtngNdeNum)) {
                previousOperNum = IConstant.VALUE.BLANK;
                currentOperNum = operNum;
            } else {
                if (rtngSqncCtgryCd.equals("0")) {
                    for (int i = 0; i < mfgRtngItmNdeList.size(); i++) {
                        Map.Entry<String, String> entity = mfgRtngItmNdeList.get(i);
                        JSONObject jsonObject = JSONObject.parseObject(entity.getValue());
                        String rtngNdeNum = (String) jsonObject.get("rtngNdeNum");
                        if (rtngNdeNum.equals(currentRtngNdeNum)) {
                            String srcSysCd = (String) JSONObject.parseObject(mfgRtngItmNdeList.get(i - 1).getValue()).get("srcSysCd");
                            String rtngTypCd = (String) JSONObject.parseObject(mfgRtngItmNdeList.get(i - 1).getValue()).get("rtngTypCd");
                            String preRtngNdeNum = (String) JSONObject.parseObject(mfgRtngItmNdeList.get(i - 1).getValue()).get("rtngNdeNum");
                            String rtngGrpCd = (String) JSONObject.parseObject(mfgRtngItmNdeList.get(i - 1).getValue()).get("rtngGrpCd");
                            EDMMfgRtngItmEntity mfgRtngItmEntity = mfgRtngItmDao.getEntityWithConditions(srcSysCd, rtngTypCd, preRtngNdeNum, rtngGrpCd);
                            if (mfgRtngItmEntity != null) {
                                previousOperNum = mfgRtngItmEntity.getOperNum();
                                currentOperNum = operNum;
                            }
                        } else {
                            continue;
                        }
                    }
                } else if (rtngSqncCtgryCd.equals("1")) {
                    List<Map.Entry<String, String>> itmLisy = reBuildList(currentRtngNdeNum, operNum, mfgRtngItmList);
                    for (int i = 0; i < itmLisy.size(); i++) {
                        Map.Entry<String, String> entity = itmLisy.get(i);
                        JSONObject jsonObject = JSONObject.parseObject(entity.getValue());
                        String rtngItmNum = (String) jsonObject.get("rtngItmNum");
                        String operNum_current = (String) jsonObject.get("operNum");
                        if (rtngItmNum.equals(currentRtngNdeNum)) {
                            String operNum_next = (String) JSONObject.parseObject(itmLisy.get(i + 1).getValue()).get("operNum");
                            String operNum_pro = (String) JSONObject.parseObject(itmLisy.get(i - 1).getValue()).get("operNum");
                            previousOperNum = operNum_pro;
                            currentOperNum = getCurrentOperNum(operNum_next,currentOperNum);
                        }else{
                            continue;
                        }
                    }
                }
            }
        }
        operNumMap.put("currentOperNum", currentOperNum);
        operNumMap.put("previousOperNum", previousOperNum);
        return operNumMap;
    }

    public static List<Map.Entry<String, String>> reBuildList(String currentRtngNdeNum, String currentOperNum, List<Map.Entry<String, String>> mfgRtngItmList) {
        for (int i = 0; i < mfgRtngItmList.size(); i++) {
            Map.Entry<String, String> entity = mfgRtngItmList.get(i);
            JSONObject jsonObject = JSONObject.parseObject(entity.getValue());
            String rtngItmNum = (String) jsonObject.get("rtngItmNum");
            String operNum = (String) jsonObject.get("operNum");
            if (currentOperNum.equals(operNum) && !rtngItmNum.equals(currentRtngNdeNum)) {
                mfgRtngItmList.remove(i);
            }
        }
        return mfgRtngItmList;
    }

    public static String getCurrentOperNum(String s1,String s2) {
        Integer result = ((Integer.parseInt(cleanZero(s1))+Integer.parseInt(cleanZero(s2)))/2);
        return "00"+result;
    }

    public static String cleanZero(String str) {
        String newStr = str.replaceAll("^(0+)", "");
        return newStr;
    }
}
