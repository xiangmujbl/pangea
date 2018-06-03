package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.ADFCriteria;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsMaterialPlanStatusEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class PlanCnsMaterialPlanStatusDaoImpl extends CommonDaoImpl {

    private static PlanCnsMaterialPlanStatusDaoImpl instance;

    public static PlanCnsMaterialPlanStatusDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsMaterialPlanStatusDaoImpl();
        }
        return instance;
    }

    public PlanCnsMaterialPlanStatusEntity getEntityWithLocalMaterialNumberAndlLocalPlant(String localMaterialNumber, String localPlant) {
        if (StringUtils.isNotEmpty(localPlant) && StringUtils.isNotEmpty(localMaterialNumber)) {
            String queryString = QueryHelper.buildCriteria(IConstant.CNS_MATERIAL_PLAN_STATUS.LOCAL_MATERIAL_NUMBER).is(localMaterialNumber)
                    .and(IConstant.CNS_MATERIAL_PLAN_STATUS.LOCAL_PLANT).is(localPlant).toQueryString();
            return queryForObject(IConstant.REGION.CNS_MATERIAL_PLAN_STATUS, queryString, PlanCnsMaterialPlanStatusEntity.class);
        }
        return null;
    }

    public PlanCnsMaterialPlanStatusEntity getEntityWithConditions(String localMaterialNumber) {
        String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.DP_RELEVANT).is(IConstant.VALUE.X)
                .and(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.LOCAL_MATERIAL_NUMBER).is(localMaterialNumber)
                .and(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.SOURCE_SYSTEM).is(IConstant.VALUE.PROJECT_ONE).toQueryString();
        return queryForObject(IConstant.REGION.PLAN_CNS_MATERIAL_PLAN_STATUS, queryString, PlanCnsMaterialPlanStatusEntity.class);
    }

    public List<PlanCnsMaterialPlanStatusEntity> getEntityListWithConditions(String sourceSystem, String localMaterialNumber, String localPlant, String spRelevant, String noPlanRelevant) {
        if (StringUtils.isNotEmpty(localMaterialNumber) && StringUtils.isNotEmpty(sourceSystem) && StringUtils.isNotEmpty(localPlant)) {

            ADFCriteria adfCriteria = QueryHelper.buildCriteria(IConstant.CNS_MATERIAL_PLAN_STATUS.SOURCE_SYSTEM).is(sourceSystem)
                    .and(IConstant.CNS_MATERIAL_PLAN_STATUS.LOCAL_MATERIAL_NUMBER).is(localMaterialNumber)
                    .and(IConstant.CNS_MATERIAL_PLAN_STATUS.LOCAL_PLANT).is(localPlant);

            if (StringUtils.isNotEmpty(spRelevant) && StringUtils.isNotEmpty(noPlanRelevant)) {
                adfCriteria.and(QueryHelper.buildCriteria(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.SP_RELEVANT).is(spRelevant)
                        .or(QueryHelper.buildCriteria(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.NO_PLAN_RELEVANT).is(noPlanRelevant)));
            } else {
                if (StringUtils.isNotEmpty(spRelevant)) {
                    adfCriteria.and(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.SP_RELEVANT).is(spRelevant);
                } else if (StringUtils.isNotEmpty(noPlanRelevant)) {
                    adfCriteria.and(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.NO_PLAN_RELEVANT).is(noPlanRelevant);
                }
            }

            String queryString = adfCriteria.toQueryString();
            return queryForList(IConstant.REGION.PLAN_CNS_MATERIAL_PLAN_STATUS, queryString, PlanCnsMaterialPlanStatusEntity.class);
        }
        return null;
    }

    public PlanCnsMaterialPlanStatusEntity getEntityWithLocalMaterialNumberAndlLocalPlantAndSourceSystem(String localMaterialNumber, String localPlant, String sourceSystem) {
        if (StringUtils.isNotEmpty(localPlant) && StringUtils.isNotEmpty(localMaterialNumber)) {
            String queryString = QueryHelper.buildCriteria(IConstant.CNS_MATERIAL_PLAN_STATUS.LOCAL_MATERIAL_NUMBER).is(localMaterialNumber)
                    .and(IConstant.CNS_MATERIAL_PLAN_STATUS.LOCAL_PLANT).is(localPlant)
                    .and(IConstant.CNS_MATERIAL_PLAN_STATUS.SOURCE_SYSTEM).is(sourceSystem).toQueryString();
            return queryForObject(IConstant.REGION.CNS_MATERIAL_PLAN_STATUS, queryString, PlanCnsMaterialPlanStatusEntity.class);
        }
        return null;
    }

    public PlanCnsMaterialPlanStatusEntity getEntityWithLocalMaterialNumber(String localMaterialNumber) {
        if (StringUtils.isNotEmpty(localMaterialNumber)) {
            String queryString = QueryHelper.buildCriteria(IConstant.CNS_MATERIAL_PLAN_STATUS.LOCAL_MATERIAL_NUMBER).is(localMaterialNumber).toQueryString();
            return queryForObject(IConstant.REGION.PLAN_CNS_MATERIAL_PLAN_STATUS, queryString, PlanCnsMaterialPlanStatusEntity.class);
        }
        return null;
    }

    public PlanCnsMaterialPlanStatusEntity getEntityWithDpRelevantAndLocalMaterialnumber(String localMaterialnumber) {
        String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.DP_RELEVANT).is(IConstant.VALUE.X)
                .and(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.LOCAL_MATERIAL_NUMBER).is(localMaterialnumber).toQueryString();
        return queryForObject(IConstant.REGION.PLAN_CNS_MATERIAL_PLAN_STATUS, queryString, PlanCnsMaterialPlanStatusEntity.class);
    }
    
    public PlanCnsMaterialPlanStatusEntity getEntityWithSPNoPlanRelevantAndLocalMaterialnumber(String localMaterialnumber) {
    	
    	 ADFCriteria adfCriteria = QueryHelper.buildCriteria(IConstant.CNS_MATERIAL_PLAN_STATUS.LOCAL_MATERIAL_NUMBER).is(localMaterialnumber);
    	 adfCriteria.and(QueryHelper.buildCriteria(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.SP_RELEVANT).is(IConstant.VALUE.X)
                 .or(QueryHelper.buildCriteria(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.NO_PLAN_RELEVANT).is(IConstant.VALUE.X)));
                 
        String queryString = adfCriteria.toQueryString();
        return queryForObject(IConstant.REGION.PLAN_CNS_MATERIAL_PLAN_STATUS, queryString, PlanCnsMaterialPlanStatusEntity.class);
    }


    public PlanCnsMaterialPlanStatusEntity getEntityWithLocalMaterialNumberAndsourceSystem(String localMaterialNumber, String sourceSystem) {
        if ("".equals(localMaterialNumber) || "".equals(sourceSystem)) {
            return null;
        }
        String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.LOCAL_MATERIAL_NUMBER).is(localMaterialNumber)
                .and(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.SOURCE_SYSTEM).is(sourceSystem).toQueryString();
        return queryForObject(IConstant.REGION.PLAN_CNS_MATERIAL_PLAN_STATUS, queryString, PlanCnsMaterialPlanStatusEntity.class);
    }

    public PlanCnsMaterialPlanStatusEntity getEntityWithThereConditions(String localMaterialNumber, String localPlant, String dpRelevant) {
        if (StringUtils.isNotEmpty(localMaterialNumber) && StringUtils.isNotEmpty(localPlant) && StringUtils.isNotEmpty(dpRelevant)) {
            String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.DP_RELEVANT).is(dpRelevant)
                    .and(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.LOCAL_MATERIAL_NUMBER).is(localMaterialNumber)
                    .and(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.LOCAL_PLANT).is(localPlant).toQueryString();
            return queryForObject(IConstant.REGION.PLAN_CNS_MATERIAL_PLAN_STATUS, queryString, PlanCnsMaterialPlanStatusEntity.class);
        }
        return null;
    }

    public List<PlanCnsMaterialPlanStatusEntity> getEntityWithMaterialNumber(String materialNumber) {
        if (StringUtils.isNotEmpty(materialNumber)) {
            String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.MATERIAL_NUMBER).is(materialNumber).toQueryString();
            return queryForList(IConstant.REGION.PLAN_CNS_MATERIAL_PLAN_STATUS, queryString, PlanCnsMaterialPlanStatusEntity.class);
        }
        return null;
    }

    public List<PlanCnsMaterialPlanStatusEntity> getEntityListWithLocalMaterialNumber(String localMaterialNumber) {
        if (StringUtils.isNotEmpty(localMaterialNumber)) {
            String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.LOCAL_MATERIAL_NUMBER).is(localMaterialNumber).toQueryString();
            return queryForList(IConstant.REGION.PLAN_CNS_MATERIAL_PLAN_STATUS, queryString, PlanCnsMaterialPlanStatusEntity.class);
        }
        return null;
    }

    public List<PlanCnsMaterialPlanStatusEntity> getEntityWithLocalMaterialNumberAndSourceSystem(String localMaterialNumber, String sourceSystem) {
        if (StringUtils.isNotBlank(localMaterialNumber) && StringUtils.isNotBlank(sourceSystem)) {
            String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.LOCAL_MATERIAL_NUMBER).is(localMaterialNumber)
                    .and(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.SOURCE_SYSTEM).is(sourceSystem).toQueryString();
            return queryForList(IConstant.REGION.PLAN_CNS_MATERIAL_PLAN_STATUS, queryString, PlanCnsMaterialPlanStatusEntity.class);
        }
        return null;
    }


    public PlanCnsMaterialPlanStatusEntity getEntityWithLocalMaterialNumberSourceSystemAndRelevant(String sourceSystem, String localMaterialNumber) {
        if (StringUtils.isNotEmpty(sourceSystem) && StringUtils.isNotEmpty(localMaterialNumber)) {
            String queryString = QueryHelper.buildCriteria(IConstant.CNS_MATERIAL_PLAN_STATUS.SOURCE_SYSTEM).is(sourceSystem).and(IConstant.CNS_MATERIAL_PLAN_STATUS.LOCAL_MATERIAL_NUMBER).is(localMaterialNumber).toQueryString();
            PlanCnsMaterialPlanStatusEntity materialPlanStatusEntity = queryForObject(IConstant.REGION.PLAN_CNS_MATERIAL_PLAN_STATUS, queryString, PlanCnsMaterialPlanStatusEntity.class);

            if (null != materialPlanStatusEntity) {
                if (IConstant.VALUE.X.equals(materialPlanStatusEntity.getDpRelevant()) || IConstant.VALUE.X.equals(materialPlanStatusEntity.getSpRelevant()) || IConstant.VALUE.X.equals(materialPlanStatusEntity.getNoPlanRelevant())) {

                    return materialPlanStatusEntity;
                }
            }
        }
        return null;
    }

    public PlanCnsMaterialPlanStatusEntity getCnsMaterialPlanStatusDaoEntity(String sourceSystem, String localMaterialNumber, String localPlant) {
        if (StringUtils.isNotEmpty(sourceSystem) && StringUtils.isNotEmpty(localMaterialNumber) && StringUtils.isNotEmpty(localPlant)) {
            String queryString = QueryHelper.buildCriteria(IConstant.CNS_MATERIAL_PLAN_STATUS.SOURCE_SYSTEM).is(sourceSystem).and(IConstant.CNS_MATERIAL_PLAN_STATUS.LOCAL_MATERIAL_NUMBER).is(localMaterialNumber).and(IConstant.CNS_MATERIAL_PLAN_STATUS.LOCAL_PLANT).is(localPlant).toQueryString();
            return queryForObject(IConstant.REGION.PLAN_CNS_MATERIAL_PLAN_STATUS, queryString, PlanCnsMaterialPlanStatusEntity.class);
        }
        return null;
    }

    public PlanCnsMaterialPlanStatusEntity getEntityWithSourceSystemAndLocalMaterialNumberAndLocalPlant(String sourceSystem, String localMaterialNumber, String localPlant) {
        String queryString = QueryHelper.buildCriteria(IConstant.CNS_MATERIAL_PLAN_STATUS.SOURCE_SYSTEM).is(sourceSystem)
                .and(IConstant.CNS_MATERIAL_PLAN_STATUS.LOCAL_MATERIAL_NUMBER).is(localMaterialNumber)
                .and(IConstant.CNS_MATERIAL_PLAN_STATUS.LOCAL_PLANT).is(localPlant).toQueryString();
        return queryForObject(IConstant.REGION.CNS_MATERIAL_PLAN_STATUS, queryString, PlanCnsMaterialPlanStatusEntity.class);
    }

    public PlanCnsMaterialPlanStatusEntity getEntityWithLocalParentCode(String localParentCode) {
        if (StringUtils.isNotEmpty(localParentCode)) {
            String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.LOCAL_PARENT_CODE).is(localParentCode).toQueryString();
            PlanCnsMaterialPlanStatusEntity planCnsMaterialPlanStatusEntity = queryForObject(IConstant.REGION.PLAN_CNS_MATERIAL_PLAN_STATUS, queryString, PlanCnsMaterialPlanStatusEntity.class);
            if (planCnsMaterialPlanStatusEntity != null) {
                return planCnsMaterialPlanStatusEntity;
            }
        }
        return null;
    }
    public PlanCnsMaterialPlanStatusEntity getEntityWithLocalParentCodeAndDp(String localParentCode) {
        if (StringUtils.isNotEmpty(localParentCode) ) {
            String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.LOCAL_PARENT_CODE).is(localParentCode)
            		.and(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.DP_RELEVANT).is(IConstant.VALUE.X)
            		.toQueryString();
            
            PlanCnsMaterialPlanStatusEntity planCnsMaterialPlanStatusEntity = queryForObject(IConstant.REGION.PLAN_CNS_MATERIAL_PLAN_STATUS, queryString, PlanCnsMaterialPlanStatusEntity.class);
            if (planCnsMaterialPlanStatusEntity != null) {
                return planCnsMaterialPlanStatusEntity;
            }
        }
        return null;
    }
    
    public PlanCnsMaterialPlanStatusEntity getPlanCnsMaterialPlanStatusEntity4251(String sourceSystem,String localMaterialNumber,String localPlant){
        ADFCriteria adfCriteria=QueryHelper.buildCriteria();
        if(StringUtils.isBlank(sourceSystem)){
            adfCriteria.and(QueryHelper.buildCriteria(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.SOURCE_SYSTEM).isNull());
        }else{
            adfCriteria.and(QueryHelper.buildCriteria(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.SOURCE_SYSTEM).is(sourceSystem.trim()));
        }
        if(StringUtils.isBlank(localMaterialNumber)){
            adfCriteria.and(QueryHelper.buildCriteria(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.LOCAL_MATERIAL_NUMBER).isNull());
        }else{
            adfCriteria.and(QueryHelper.buildCriteria(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.LOCAL_MATERIAL_NUMBER).is(localMaterialNumber.trim()));
        }
        if(StringUtils.isBlank(localPlant)){
            adfCriteria.and(QueryHelper.buildCriteria(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.LOCAL_PLANT).isNull());
        }else{
            adfCriteria.and(QueryHelper.buildCriteria(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.LOCAL_PLANT).is(localPlant.trim()));
        }
        adfCriteria.and(QueryHelper.buildCriteria(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.SP_RELEVANT).is(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.FIELD_X));
        return queryForObject(IConstant.REGION.PLAN_CNS_MATERIAL_PLAN_STATUS, adfCriteria.toQueryString(), PlanCnsMaterialPlanStatusEntity.class);
    }
}