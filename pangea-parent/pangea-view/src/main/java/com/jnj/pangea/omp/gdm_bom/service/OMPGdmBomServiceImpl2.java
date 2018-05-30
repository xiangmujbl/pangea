package com.jnj.pangea.omp.gdm_bom.service;

import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.edm.EDMMfgOrderEntity;
import com.jnj.pangea.common.entity.edm.EDMMfgOrderRtngEntity;
import com.jnj.pangea.common.dao.impl.edm.EDMMfgOrderRtngDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMfgOrderSeqEntity;
import com.jnj.pangea.common.dao.impl.edm.EDMMfgOrderSeqDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanParameterEntity;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanParameterDaoImpl;
import com.jnj.pangea.omp.gdm_bom.bo.OMPGdmBomBo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class OMPGdmBomServiceImpl2 {

    private static OMPGdmBomServiceImpl2 instance;

    public static OMPGdmBomServiceImpl2 getInstance() {
        if (instance == null) {
            instance = new OMPGdmBomServiceImpl2();
        }
        return instance;
    }

    private EDMMfgOrderRtngDaoImpl mfgOrderRtngDao = EDMMfgOrderRtngDaoImpl.getInstance();
    private EDMMfgOrderSeqDaoImpl mfgOrderSeqDao = EDMMfgOrderSeqDaoImpl.getInstance();
    private PlanCnsPlanParameterDaoImpl cnsPlanParameterDao = PlanCnsPlanParameterDaoImpl.getInstance();

    public List<ResultObject> buildView(String key, Object o, Object o2) {
        List<ResultObject> resultObjectList = new ArrayList<ResultObject>();

        EDMMfgOrderEntity mfgOrderEntity = (EDMMfgOrderEntity) o;
        List<PlanCnsPlanParameterEntity> planParameterEntityList= getPlanCnsPlanParameterEntityListWithSrcSysCdAndDataObject(mfgOrderEntity.getSourceSysCd());
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int  current_date = Integer.valueOf(dateFormat.format(calendar.getTime()));
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);
        int current_date_Minus_One = Integer.valueOf(dateFormat.format(calendar.getTime()));
        int actRlseDt = Integer.valueOf(mfgOrderEntity.getActRlseDt());
        planParameterEntityList.forEach(planCnsPlanParameterEntity -> {
            OMPGdmBomBo gdmBOMBo = new OMPGdmBomBo();
            String parameterValue = planCnsPlanParameterEntity.getParameterValue();
        if(mfgOrderEntity.getSourceSysCd() .equals(planCnsPlanParameterEntity.getAttribute()) && (current_date == actRlseDt || actRlseDt == current_date_Minus_One)){
//            if(mfgOrderEntity.getSourceSysCd() .equals(planCnsPlanParameterEntity.getAttribute())){
            List<EDMMfgOrderRtngEntity> edmMfgOrderRtngEntityList = joinMfgOrderRtngWithOrdrRtngNum(mfgOrderEntity.getOrdrRtngNum());
            edmMfgOrderRtngEntityList.forEach(edmMfgOrderRtngEntity -> {

                OMPGdmBomBo gdmBOMBo1 = null;
                try {
                    gdmBOMBo1 = (OMPGdmBomBo)gdmBOMBo.copy();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }

                List<EDMMfgOrderSeqEntity> edmMfgOrderSeqEntityList = joinMfgOrderSeqWithOrdrRtngNum(edmMfgOrderRtngEntity.getOrdrRtngNum() );
                OMPGdmBomBo finalGdmBOMBo = gdmBOMBo1;
                edmMfgOrderSeqEntityList.forEach(edmMfgOrderSeqEntity -> {
                    try {
                        OMPGdmBomBo gdmBOMBo2 = (OMPGdmBomBo)finalGdmBOMBo.copy();
                        String bomId = "PRO"+"/"+parameterValue+"_"+mfgOrderEntity.getMfgOrdrNum()+"/"+mfgOrderEntity.getOrdrRtngNum()+"/"+edmMfgOrderRtngEntity.getOperNum();
                        gdmBOMBo2.setBomId(bomId);
                        gdmBOMBo2.setActive("YES");
                        gdmBOMBo2.setActiveFCTERP("YES");
                        gdmBOMBo2.setActiveOPRERP("YES");
                        gdmBOMBo2.setActiveSOPERP("NO");
                        gdmBOMBo2.setComments("");
                        gdmBOMBo2.setStartEff("01/01/1980  00:00:00");
                        gdmBOMBo2.setEndEff("31/12/2298  23:59:59");


                        gdmBOMBo2.setLocationId(mfgOrderEntity.getSourceSysCd()+"_"+mfgOrderEntity.getPlntCd());
                        ResultObject resultObject = new ResultObject();
                        resultObject.setBaseBo(gdmBOMBo2);
                        resultObjectList.add(resultObject);
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }

                });

            });
        }
        });
        return resultObjectList;
    }

    public List<PlanCnsPlanParameterEntity> getPlanCnsPlanParameterEntityListWithSrcSysCdAndDataObject(String srcSysCd){
        return cnsPlanParameterDao.getPlanCnsPlanParameterEntityListWithSrcSysCdAndDataObject(srcSysCd,"SEND_TO_OMP");
    }

    public List<EDMMfgOrderRtngEntity> joinMfgOrderRtngWithOrdrRtngNum(String ordrRtngNum){
        return  mfgOrderRtngDao.joinMfgOrderRtngWithOrdrRtngNum(ordrRtngNum);
    }

    public List<EDMMfgOrderSeqEntity> joinMfgOrderSeqWithOrdrRtngNum(String ordrRtngNum){
        return  mfgOrderSeqDao.joinMfgOrderSeqWithOrdrRtngNum(ordrRtngNum);
    }
}
