package com.jnj.pangea.omp.gdm_machine;

import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanParameterDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanParameterEntity;

import java.util.Map;


public class OMPGdmMachineHook {

    private static PlanCnsPlanParameterDaoImpl planCnsPlanParameterDao = PlanCnsPlanParameterDaoImpl.getInstance();
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
            PlanCnsPlanParameterEntity paramterEntity = planCnsPlanParameterDao.getEntitiesWithConditions1(srcSysCd, "SEND_TO_OMP");
            if(paramterEntity !=null &&paramterEntity.getAttribute().equals(srcSysCd)){
                rt= false;
            }else{
                rt= true;
            }
        }
        return rt;
    }
}
