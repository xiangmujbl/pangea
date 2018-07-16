package com.jnj.pangea.omp.gdm_process_master.controller;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.inner.DateInner;
import com.jnj.inner.StringInner;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.util.DateUtils;

import java.util.Date;

/**
 * @author: qzhan112
 * @date: 2018/6/13
 */
public class OMPGdmProcessMasterHook {
    public static String getEndEff_T5(String rtgVld_ToDt, String valToDt) {
        String endEff = "";
        Date endDate = DateInner.stringToDate(rtgVld_ToDt + IConstant.VALUE.HHMMSS, DateUtils.F_yyyy_MM_dd_HHmmss);
        Date toDate = DateInner.stringToDate(valToDt + IConstant.VALUE.HHMMSS, DateUtils.F_yyyy_MM_dd_HHmmss);
        Date compareDate=DateInner.stringToDate(IConstant.VALUE.ENDEFF,DateUtils.J_yyyyMMdd_HHmmss);
        if(endDate.after(toDate)){
            if(toDate.after(compareDate)){
                endEff = IConstant.VALUE.ENDEFF;
            }else{
                endEff = DateInner.dateToString(toDate,DateUtils.J_yyyyMMdd_HHmmss);
            }
        }else{
            if(endDate.after(compareDate)){
                endEff = IConstant.VALUE.ENDEFF;
            }else{
                endEff = DateInner.dateToString(endDate,DateUtils.J_yyyyMMdd_HHmmss);
            }
        }
        return endEff;
    }
    public static String getStartEff_T7(String valFromDt_hdr, String valFromDt_versn) {
        String startEff = "";
        Date startDate = DateInner.stringToDate(valFromDt_hdr + IConstant.VALUE.HHMMSSSTART, DateUtils.F_yyyy_MM_dd_HHmmss);
        Date fromDate = DateInner.stringToDate(valFromDt_versn + IConstant.VALUE.HHMMSSSTART, DateUtils.F_yyyy_MM_dd_HHmmss);
        if(startDate.after(fromDate)){
            startEff = DateInner.dateToString(startDate,DateUtils.J_yyyyMMdd_HHmmss);
        }else{
            startEff = DateInner.dateToString(fromDate,DateUtils.J_yyyyMMdd_HHmmss);
        }
        return startEff;
    }

    public static String getProcessId(String prdntVrsnNum,String matlNum,String srcSysCd,String plntCd){
        String processId =StringInner.join(prdntVrsnNum,"/",cleanZero(matlNum),"/",srcSysCd,"/",plntCd);
        return processId;
    }
    public static String getVendorId(String localvendor){
        String VendorId =StringInner.join(cleanZero(localvendor));
        return VendorId;
    }
    public static String cleanZero(String matlNum_rtng) {
        String newStr = matlNum_rtng.replaceAll("^(0+)", "");
        return newStr;

    }
}
