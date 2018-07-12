package com.jnj.pangea.plan.cns_prod_cty_affl.service;

import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsProdCtyAfflTempDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.entity.plan.PlanCnsMaterialPlanStatusEntity;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsMaterialPlanStatusDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.entity.edm.EDMPlantV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMPlantV1DaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsProdCtyAfflTempEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.plan.cns_prod_cty_affl.bo.PlanCnsProdCtyAfflBo;
import org.apache.commons.lang.StringUtils;

public class PlanCnsProdCtyAfflServiceImpl implements ICommonService {

    private static PlanCnsProdCtyAfflServiceImpl instance;

    public static PlanCnsProdCtyAfflServiceImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsProdCtyAfflServiceImpl();
        }
        return instance;
    }
    private PlanCnsProdCtyAfflTempDaoImpl planCnsProdCtyAfflTempDao = PlanCnsProdCtyAfflTempDaoImpl.getInstance();
    private PlanCnsMaterialPlanStatusDaoImpl cnsMaterialPlanStatusDao = PlanCnsMaterialPlanStatusDaoImpl.getInstance();
    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    private EDMPlantV1DaoImpl plantV1Dao = EDMPlantV1DaoImpl.getInstance();


    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        EDMMaterialGlobalV1Entity materialGlobalV1Entity = (EDMMaterialGlobalV1Entity) o;

        PlanCnsProdCtyAfflBo cnsProdCtyAfflBo = new PlanCnsProdCtyAfflBo();

        EDMSourceSystemV1Entity sourceSystemV1Entity = checkT1();
        if (null != sourceSystemV1Entity) {
            cnsProdCtyAfflBo.setSourceSystem(sourceSystemV1Entity.getSourceSystem());

            String localMaterialNumber = materialGlobalV1Entity.getLocalMaterialNumber();

            PlanCnsMaterialPlanStatusEntity materialPlanStatusEntityC1 = cnsMaterialPlanStatusDao.getEntityWithDpRelevantAndLocalMaterialnumber(localMaterialNumber);

            if (null != materialPlanStatusEntityC1) {
                String localParentCode = materialGlobalV1Entity.getLocalDpParentCode();

                if (StringUtils.isNotEmpty(localParentCode)) {
                    cnsProdCtyAfflBo.setDpParentCode(localParentCode);
                } else {
                    return null;
                }
            } else {
                return null;
            }

            PlanCnsMaterialPlanStatusEntity materialPlanStatusEntityT2 = cnsMaterialPlanStatusDao.getEntityWithConditions(localMaterialNumber);
            if (null != materialPlanStatusEntityT2) {
                String localPlant = materialPlanStatusEntityT2.getLocalPlant();
                EDMPlantV1Entity plantV1Entity = plantV1Dao.getPlantWithSourceSystemAndLocalPlant(IConstant.VALUE.PROJECT_ONE, localPlant);
                if (null != plantV1Entity) {
                    //G1
                    String country = plantV1Entity.getCountry();
                    cnsProdCtyAfflBo.setCountry(country);
                    String dpParentCode = cnsProdCtyAfflBo.getDpParentCode();
                    String sourceSystem = cnsProdCtyAfflBo.getSourceSystem();
                    PlanCnsProdCtyAfflTempEntity planCnsProdCtyAfflTempEntity =  planCnsProdCtyAfflTempDao.queryplanCnsProdCtyAfflTemp(country,dpParentCode,sourceSystem);
                    if(planCnsProdCtyAfflTempEntity!=null){
                        cnsProdCtyAfflBo.setOvrProdClass(planCnsProdCtyAfflTempEntity.getOvrProdClass());
                        cnsProdCtyAfflBo.setOvrProdStat(planCnsProdCtyAfflTempEntity.getOvrProdStat());
                        cnsProdCtyAfflBo.setDpSegmentation(planCnsProdCtyAfflTempEntity.getDpSegmentation());
                        cnsProdCtyAfflBo.setRootSize(planCnsProdCtyAfflTempEntity.getRootSize());
                        cnsProdCtyAfflBo.setCountryGrp(planCnsProdCtyAfflTempEntity.getCountryGrp());
                        cnsProdCtyAfflBo.setDpPlannerId(planCnsProdCtyAfflTempEntity.getDpPlannerId());

                    }
                }
            } else {
                return null;
            }

            String localMaterialType = materialGlobalV1Entity.getLocalMaterialType();
            if (IConstant.VALUE.SAPR.equals(localMaterialType)) {
                cnsProdCtyAfflBo.setProdClassification(IConstant.VALUE.SAMPLE);
            } else {
                cnsProdCtyAfflBo.setProdClassification(IConstant.VALUE.REGULAR);
            }

            if (null == cnsProdCtyAfflBo.getProdStatus()) {
                cnsProdCtyAfflBo.setProdStatus(IConstant.VALUE.ACTIVE);
            }

        } else {
            return null;
        }

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
