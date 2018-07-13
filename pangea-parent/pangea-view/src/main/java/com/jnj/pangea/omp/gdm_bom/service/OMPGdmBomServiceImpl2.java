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
import java.util.*;

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
        if ((mfgOrderEntity.getMfgOrdrSttsCd()).indexOf("I0012") == -1
                && (mfgOrderEntity.getMfgOrdrSttsCd()).indexOf("I0045") == -1
                && (mfgOrderEntity.getMfgOrdrSttsCd()).indexOf("I0046") == -1) {
            List<PlanCnsPlanParameterEntity> planParameterEntityList = getPlanCnsPlanParameterEntityListWithSrcSysCdAndDataObject(mfgOrderEntity.getSourceSysCd());
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int current_date = Integer.valueOf(dateFormat.format(calendar.getTime()));
            int actRlseDt = Integer.valueOf(mfgOrderEntity.getActRlseDt());
            planParameterEntityList.forEach(planCnsPlanParameterEntity -> {
                OMPGdmBomBo gdmBOMBo = new OMPGdmBomBo();
                String parameterValue = planCnsPlanParameterEntity.getParameterValue();
                calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - Integer.valueOf(parameterValue));
                int current_date_Minus_parametervalue = Integer.valueOf(dateFormat.format(calendar.getTime()));
                calendar.setTime(date);
                if (mfgOrderEntity.getSourceSysCd().equals(planCnsPlanParameterEntity.getAttribute())
                        && (current_date >= actRlseDt && actRlseDt >= current_date_Minus_parametervalue)) {
                    List<EDMMfgOrderRtngEntity> edmMfgOrderRtngEntityList = joinMfgOrderRtngWithOrdrRtngNumAndSrcSysCd(mfgOrderEntity.getOrdrRtngNum(),mfgOrderEntity.getSourceSysCd());
                    edmMfgOrderRtngEntityList.forEach(edmMfgOrderRtngEntity -> {

                        OMPGdmBomBo gdmBOMBo1 = null;
                        try {
                            gdmBOMBo1 = (OMPGdmBomBo) gdmBOMBo.copy();
                        } catch (CloneNotSupportedException e) {
                            e.printStackTrace();
                        }
                        List<EDMMfgOrderSeqEntity> edmMfgOrderSeqEntityList = joinMfgOrderSeqWithOrdrRtngNumAndSrcSysCd(edmMfgOrderRtngEntity.getOrdrRtngNum(),edmMfgOrderRtngEntity.getSrcSysCd());
                        OMPGdmBomBo finalGdmBOMBo = gdmBOMBo1;
                        edmMfgOrderSeqEntityList.forEach(edmMfgOrderSeqEntity -> {
                            try {
                                OMPGdmBomBo gdmBOMBo2 = (OMPGdmBomBo) finalGdmBOMBo.copy();
//                                String bomId = "PRO" + "/" + parameterValue + "_" + Integer.valueOf(mfgOrderEntity.getMfgOrdrNum()) + "/" + Integer.valueOf(mfgOrderEntity.getOrdrRtngNum()) + "/" + Integer.valueOf(edmMfgOrderRtngEntity.getOperNum());
                                String bomId = "PRO" + "/" + mfgOrderEntity.getSourceSysCd() + "/" + Integer.valueOf(mfgOrderEntity.getMfgOrdrNum()) + "/" + Integer.valueOf(mfgOrderEntity.getRtngSqncNum()) + "/" + Integer.valueOf(edmMfgOrderRtngEntity.getOperNum());
                                gdmBOMBo2.setBomId(bomId);
                                gdmBOMBo2.setActive("YES");
                                gdmBOMBo2.setActiveFCTERP("YES");
                                gdmBOMBo2.setActiveOPRERP("YES");
                                gdmBOMBo2.setActiveSOPERP("NO");
                                gdmBOMBo2.setComments("");
                                gdmBOMBo2.setStartEff("01/01/1980  00:00:00");
                                gdmBOMBo2.setEndEff("31/12/2298  23:59:59");


                                gdmBOMBo2.setLocationId(mfgOrderEntity.getSourceSysCd() + "_" + mfgOrderEntity.getPlntCd());
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
        return resultObjectList;
    }

    public List<PlanCnsPlanParameterEntity> getPlanCnsPlanParameterEntityListWithSrcSysCdAndDataObject(String srcSysCd){
        return cnsPlanParameterDao.getPlanCnsPlanParameterEntityListWithSrcSysCdAndDataObject(srcSysCd,"SEND_TO_OMP");
    }

    public List<EDMMfgOrderRtngEntity> joinMfgOrderRtngWithOrdrRtngNumAndSrcSysCd(String ordrRtngNum,String srcSysCd){
        return  mfgOrderRtngDao.joinMfgOrderRtngWithOrdrRtngNumAndSrcSysCd(ordrRtngNum,srcSysCd);
    }

    public List<EDMMfgOrderSeqEntity> joinMfgOrderSeqWithOrdrRtngNumAndSrcSysCd(String ordrRtngNum,String srcSysCd){
        return  mfgOrderSeqDao.joinMfgOrderSeqWithOrdrRtngNumAndSrcSysCd(ordrRtngNum,srcSysCd);
    }

    public String getParameterValue(String sourceSysCd){
        PlanCnsPlanParameterEntity planCnsPlanParameterEntity = cnsPlanParameterDao.getPlanCnsPlanParameterEntity(sourceSysCd);
        if(null != planCnsPlanParameterEntity){
            return planCnsPlanParameterEntity.getParameterValue();
        }
        return null;
    }
}
