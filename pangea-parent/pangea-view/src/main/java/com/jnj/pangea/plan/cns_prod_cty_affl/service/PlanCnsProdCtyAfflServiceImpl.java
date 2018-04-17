package com.jnj.pangea.plan.cns_prod_cty_affl.service;

import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.entity.plan.PlanCnsMaterialPlanStatusEntity;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsMaterialPlanStatusDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.entity.edm.EDMPlantV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMPlantV1DaoImpl;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.plan.cns_prod_cty_affl.bo.PlanCnsProdCtyAfflBo;

import java.util.Set;

public class PlanCnsProdCtyAfflServiceImpl implements ICommonService {

    private static PlanCnsProdCtyAfflServiceImpl instance;

    public static PlanCnsProdCtyAfflServiceImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsProdCtyAfflServiceImpl();
        }
        return instance;
    }

    private PlanCnsMaterialPlanStatusDaoImpl cnsMaterialPlanStatusDao = PlanCnsMaterialPlanStatusDaoImpl.getInstance();
    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    private EDMPlantV1DaoImpl plantV1Dao = EDMPlantV1DaoImpl.getInstance();


    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        EDMMaterialGlobalV1Entity materialGlobalV1Entity = (EDMMaterialGlobalV1Entity) o;
        Set<String> prodCtyAfflEntitySet  = (Set) o2;

        PlanCnsProdCtyAfflBo cnsProdCtyAfflBo = new PlanCnsProdCtyAfflBo();

        EDMSourceSystemV1Entity sourceSystemV1Entity = checkT1();
        if (null != sourceSystemV1Entity) {
            cnsProdCtyAfflBo.setSourceSystem(sourceSystemV1Entity.getSourceSystem());

            String localMaterialNumber = materialGlobalV1Entity.getLocalMaterialNumber();

            PlanCnsMaterialPlanStatusEntity materialPlanStatusEntityC1 = cnsMaterialPlanStatusDao.getEntityWithDpRelevantAndLocalMaterialnumber(localMaterialNumber);

            if (null != materialPlanStatusEntityC1) {
                String localParentCode = materialPlanStatusEntityC1.getLocalParentCode();

                if (!prodCtyAfflEntitySet.contains(localParentCode)) {
                    cnsProdCtyAfflBo.setDpParentCode(localParentCode);
                }else{
                    FailData failData = checkFailData(materialGlobalV1Entity);
                    failData.setErrorCode("C1");
                    resultObject.setFailData(failData);
                    return resultObject;
                }

            } else {
                FailData failData = checkFailData(materialGlobalV1Entity);
                failData.setErrorCode("C1");
                resultObject.setFailData(failData);
                return resultObject;
            }

            PlanCnsMaterialPlanStatusEntity materialPlanStatusEntityT2 = cnsMaterialPlanStatusDao.getEntityWithConditions(localMaterialNumber);
            if (null != materialPlanStatusEntityT2) {
                String localPlant = materialPlanStatusEntityT2.getLocalPlant();
                EDMPlantV1Entity plantV1Entity = plantV1Dao.getPlantWithSourceSystemAndLocalPlant(IConstant.VALUE.PROJECT_ONE, localPlant);
                if (null != plantV1Entity) {
                    String country = plantV1Entity.getCountry();
                    cnsProdCtyAfflBo.setCountry(country);
                }
            } else {
                FailData failData = checkFailData(materialGlobalV1Entity);
                failData.setErrorCode("T2");
                resultObject.setFailData(failData);
                return resultObject;
            }

            String localMaterialType = materialGlobalV1Entity.getLocalMaterialType();
            if (IConstant.VALUE.SAPR.equals(localMaterialType)) {
                cnsProdCtyAfflBo.setProdClassification(IConstant.VALUE.SAMPLE);
            } else {
                cnsProdCtyAfflBo.setProdClassification(IConstant.VALUE.REGULAR);
            }

            if (null == cnsProdCtyAfflBo.getOvrProdClass()) {
                cnsProdCtyAfflBo.setOvrProdClass(cnsProdCtyAfflBo.getProdClassification());
            }

            if (null == cnsProdCtyAfflBo.getProdStatus()) {
                cnsProdCtyAfflBo.setProdStatus(IConstant.VALUE.ACTIVE);
            }

            if (null == cnsProdCtyAfflBo.getOvrProdStat()) {
                cnsProdCtyAfflBo.setOvrProdStat(cnsProdCtyAfflBo.getProdStatus());
            }

        } else {
            FailData failData = checkFailData(materialGlobalV1Entity);
            failData.setErrorCode("T1");
            resultObject.setFailData(failData);
            return resultObject;
        }

        prodCtyAfflEntitySet.add(cnsProdCtyAfflBo.getDpParentCode());
        resultObject.setBaseBo(cnsProdCtyAfflBo);
        return resultObject;
    }

    private EDMSourceSystemV1Entity checkT1() {
        return sourceSystemV1Dao.getSourceSystemWithProjectOne();
    }

    private FailData checkFailData(EDMMaterialGlobalV1Entity materialGlobalV1Entity) {
        FailData failData = new FailData();
        failData.setFunctionalArea("DP");
        failData.setInterfaceID("PlanCnsProdCtyAffl");
        failData.setSourceSystem(IConstant.VALUE.PROJECT_ONE);
        failData.setKey1(materialGlobalV1Entity.getLocalMaterialNumber());
        failData.setKey2(materialGlobalV1Entity.getSourceSystem());
        failData.setKey3("");
        failData.setKey4("");
        failData.setKey5("");
        return failData;
    }
}
