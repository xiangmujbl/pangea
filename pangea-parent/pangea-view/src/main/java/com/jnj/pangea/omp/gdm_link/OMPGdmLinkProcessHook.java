package com.jnj.pangea.omp.gdm_link;

import com.alibaba.fastjson.JSONObject;
import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.inner.StringInner;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanParameterDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanParameterEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

public class OMPGdmLinkProcessHook {


    private static PlanCnsPlanParameterDaoImpl planCnsPlanParameterDao= PlanCnsPlanParameterDaoImpl.getInstance();
    public static String getLinkId(String mfgOrdrNum,String operNum,String rtngSqncNum,List<Map.Entry<String, String>> orderRtngList,String ordrRtngCtrNum){
        String linkId="";
        String operNum_rtng=getPreviousOperNum(ordrRtngCtrNum,orderRtngList);
        linkId = StringInner.join("PRO/",mfgOrdrNum,"/",operNum_rtng,"/",operNum,"/",rtngSqncNum);
        return linkId;
    }
    public static String getPrevOperationId(String mfgOrdrNum,List<Map.Entry<String, String>> orderRtngList,String ordrRtngCtrNum){
        String prevOperationId="";
        String operNum_rtng=getPreviousOperNum(ordrRtngCtrNum,orderRtngList);
        if(StringUtils.isNotBlank(operNum_rtng)){
            prevOperationId = StringInner.join("PRO/",mfgOrdrNum,"/",operNum_rtng);
        }
        return prevOperationId;
    }
    public static boolean check(RawDataValue rawDataValue){
        boolean rt = false;
        if(rawDataValue !=null){
            Map map=rawDataValue.toMap();
            String srcSysCd=(String)map.get("sourceSysCd");
            String mfgOrdrSttsCd = (String)map.get("mfgOrdrSttsCd");
            boolean isCheck=checkMfgOrdrSttsCd(mfgOrdrSttsCd);
            if(StringUtils.isNotBlank(srcSysCd)){
                List<PlanCnsPlanParameterEntity> paramterEntityList = planCnsPlanParameterDao.getEntitiesWithConditions(srcSysCd, "SEND_TO_OMP",srcSysCd);
                if(paramterEntityList.size()>0 && isCheck){
                    rt= false;
                }else{
                    rt= true;
                }
            }
        }
        return rt;
    }

    public static String getPreviousOperNum(String currentOrdrRtngCtrNum,List<Map.Entry<String, String>> orderRtngList){
        String previousOperNum = "";
        JSONObject firstObject = JSONObject.parseObject(orderRtngList.get(0).getValue());
        String firstEntity = (String)firstObject.get("ordrRtngCtrNum");
        if(orderRtngList!=null && orderRtngList.size()>0){
            if(firstEntity.equals(currentOrdrRtngCtrNum)){
                previousOperNum = IConstant.VALUE.BLANK;
            }else{
                for(int i=1;i<orderRtngList.size();i++){
                    Map.Entry<String, String> entity = orderRtngList.get(i);
                    JSONObject jsonObject = JSONObject.parseObject(entity.getValue());
                    String ordrRtngCtrNum = (String)jsonObject.get("ordrRtngCtrNum");
                    if(ordrRtngCtrNum.equals(currentOrdrRtngCtrNum)){
                        previousOperNum = (String)JSONObject.parseObject(orderRtngList.get(i-1).getValue()).get("operNum");
                    }else{
                        continue;
                    }
                }
            }
        }
        return previousOperNum;
    }

    public static boolean checkMfgOrdrSttsCd(String mfgOrdrSttsCd ){
        boolean rt = false;
        if(StringUtils.isNotBlank(mfgOrdrSttsCd)){
            if((mfgOrdrSttsCd.contains("I0001")||mfgOrdrSttsCd.contains("I0002"))&& (!mfgOrdrSttsCd.contains("I0012"))){
                rt = true;
            }
        }

        return rt;
    }

}
