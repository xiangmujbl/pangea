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

    public PlanCnsMaterialPlanStatusEntity getEntityWithLocalMaterialNumber(String localMaterialNumber) {
       String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.LOCAL_MATERIAL_NUMBER).is(localMaterialNumber).toQueryString();
       return queryForObject(IConstant.REGION.PLAN_CNS_MATERIAL_PLAN_STATUS, queryString, PlanCnsMaterialPlanStatusEntity.class);

    }

    public PlanCnsMaterialPlanStatusEntity getEntityWithLocalMaterialNumberAndsourceSystem(String localMaterialNumber, String sourceSystem) {
        String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.LOCAL_MATERIAL_NUMBER).is(localMaterialNumber).and(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.SOURCE_SYSTEM).is(sourceSystem).toQueryString();
        return queryForObject(IConstant.REGION.PLAN_CNS_MATERIAL_PLAN_STATUS, queryString, PlanCnsMaterialPlanStatusEntity.class);
    }
}
