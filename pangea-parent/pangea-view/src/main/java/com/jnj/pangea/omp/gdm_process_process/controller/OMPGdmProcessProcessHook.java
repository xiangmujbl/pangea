package com.jnj.pangea.omp.gdm_process_process.controller;

import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.inner.DateInner;
import com.jnj.inner.StringInner;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanParameterDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanParameterEntity;
import com.jnj.pangea.util.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import static com.jnj.pangea.util.DateUtils.F_yyyyMMdd;
import static com.jnj.pangea.util.DateUtils.stringToDate;

/**
 * @author: qzhan112
 * @date: 2018/6/13
 */
public class OMPGdmProcessProcessHook {

	public static boolean check(RawDataValue rawDataValue){
		boolean rt = false;
		if(rawDataValue !=null){
			Map map=rawDataValue.toMap();
			String srcSysCd=(String)map.get("sourceSysCd");
			String actRlseDt=(String)map.get("actRlseDt");
			String mfgOrdrSttsCd=(String)map.get("mfgOrdrSttsCd");
			PlanCnsPlanParameterDaoImpl planCnsPlanParameterDao = PlanCnsPlanParameterDaoImpl.getInstance();
			if (StringInner.isStringNotEmpty(srcSysCd)&&StringInner.isStringNotEmpty(actRlseDt)&&StringInner.isStringNotEmpty(mfgOrdrSttsCd)){
				PlanCnsPlanParameterEntity paramterEntity = planCnsPlanParameterDao.getEntitiesWithConditions3(srcSysCd, "SEND_TO_OMP",srcSysCd);
				PlanCnsPlanParameterEntity paramterEntity2 = planCnsPlanParameterDao.getEntitiesWithConditions2(srcSysCd, "SEND_TO_OMP","GDMPROCESS");
				if(paramterEntity !=null ){
					if (paramterEntity2 !=null && checkDate(actRlseDt,paramterEntity2.getParameterValue()) && !mfgOrdrSttsCd.contains("I0012")){
						rt= false;
					}else{
						rt= true;
					}
				}else{
					rt= true;
				}
			}
		}
		return rt;
	}
	public static String getEndEff_T4_1(String rtgVld_ToDt, String valToDt) {
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
	public static String getEndEff_T4_2(String valFromDt_hdr, String valFromDt_versn) {
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

	public static String getProcessId(String srcSysCd,String mfgOrdrNum){
		String processId =StringInner.join("PRO","/",srcSysCd,"/",cleanZero(mfgOrdrNum));
		return processId;
	}
	public static String cleanZero(String mfgOrdrNum) {
		String newStr = mfgOrdrNum.replaceAll("^(0+)", "");
		return newStr;
	}
	public static Boolean checkDate(String actRlseDt, String parameterValue) {
		//act date
		Date act = stringToDate(actRlseDt, F_yyyyMMdd);
		//out date
		Date current = new Date();
		int parmValue =Integer.parseInt(parameterValue);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(current);
		calendar.add(Calendar.DATE, -parmValue);
		Date date1 = calendar.getTime();
		if (act.after(date1) && act.before(current)) {
			return true;
		}else{
			return false;
		}

	}
}
