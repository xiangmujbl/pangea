package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.plan.PlanCnsMaterialPlanStatusEntity;

public class PlanCnsMaterialPlanStatusDaoImpl extends CommonDaoImpl {

    private static PlanCnsMaterialPlanStatusDaoImpl instance;

    public static PlanCnsMaterialPlanStatusDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsMaterialPlanStatusDaoImpl();
        }
        return instance;
    }

    public PlanCnsMaterialPlanStatusEntity getEntityWithLocalMaterialNumberAndlLocalPlant(String localMaterialNumber, String localPlant) {
        String queryString = QueryHelper.buildCriteria(IConstant.CNS_MATERIAL_PLAN_STATUS.LOCAL_MATERIAL_NUMBER).is(localMaterialNumber).and(IConstant.CNS_MATERIAL_PLAN_STATUS.LOCAL_PLANT).is(localPlant).toQueryString();
        return queryForObject(IConstant.REGION.CNS_MATERIAL_PLAN_STATUS, queryString, PlanCnsMaterialPlanStatusEntity.class);
    }

    public PlanCnsMaterialPlanStatusEntity getEntityWithLocalMaterialNumber(String localMaterialNumber) {
        String queryString = QueryHelper.buildCriteria(IConstant.CNS_MATERIAL_PLAN_STATUS.LOCAL_MATERIAL_NUMBER).is(localMaterialNumber).toQueryString();
        return queryForObject(IConstant.REGION.PLAN_CNS_MATERIAL_PLAN_STATUS, queryString, PlanCnsMaterialPlanStatusEntity.class);
    }

    public PlanCnsMaterialPlanStatusEntity getEntityWithLocalMaterialNumberSourceSystemAndRelevant(String sourceSystem, String localMaterialNumber) {
        String queryString = QueryHelper.buildCriteria(IConstant.CNS_MATERIAL_PLAN_STATUS.SOURCE_SYSTEM).is(sourceSystem).and(IConstant.CNS_MATERIAL_PLAN_STATUS.LOCAL_MATERIAL_NUMBER).is(localMaterialNumber).toQueryString();
        PlanCnsMaterialPlanStatusEntity materialPlanStatusEntity =  queryForObject(IConstant.REGION.PLAN_CNS_MATERIAL_PLAN_STATUS, queryString, PlanCnsMaterialPlanStatusEntity.class);

        if (null != materialPlanStatusEntity){
            if (IConstant.VALUE.X.equals(materialPlanStatusEntity.getDpRelevant()) || IConstant.VALUE.X.equals(materialPlanStatusEntity.getSpRelevant()) || IConstant.VALUE.X.equals(materialPlanStatusEntity.getNoPlanRelevant())){

                return materialPlanStatusEntity;
            }
        }
        return null;
    }
    public PlanCnsMaterialPlanStatusEntity getCnsMaterialPlanStatusDaoEntity(String sourceSystem,String localMaterialNumber,String localPlant) {
        String queryString = QueryHelper.buildCriteria(IConstant.CNS_MATERIAL_PLAN_STATUS.SOURCE_SYSTEM).is(sourceSystem).and(IConstant.CNS_MATERIAL_PLAN_STATUS.LOCAL_MATERIAL_NUMBER).is(localMaterialNumber).and(IConstant.CNS_MATERIAL_PLAN_STATUS.LOCAL_PLANT).is(localPlant).toQueryString();
        return queryForObject(IConstant.REGION.PLAN_CNS_MATERIAL_PLAN_STATUS, queryString, PlanCnsMaterialPlanStatusEntity.class);
    }
}
