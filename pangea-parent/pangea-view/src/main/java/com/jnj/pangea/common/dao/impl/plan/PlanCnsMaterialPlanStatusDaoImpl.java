package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.grid.utils.LogUtil;
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

    public PlanCnsMaterialPlanStatusEntity getEntityWithConditions(String localMaterialnumber) {
        String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.DP_RELEVANT).is(IConstant.VALUE.X)
                .and(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.LOCAL_MATERIAL_NUMBER).is(localMaterialnumber)
                .and(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.SOURCE_SYSTEM).is(IConstant.VALUE.PROJECT_ONE).toQueryString();
        return queryForObject(IConstant.REGION.PLAN_CNS_MATERIAL_PLAN_STATUS, queryString, PlanCnsMaterialPlanStatusEntity.class);
    }

    public PlanCnsMaterialPlanStatusEntity getEntityWithDpRelevantAndLocalMaterialnumber(String localMaterialnumber) {
        String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.DP_RELEVANT).is(IConstant.VALUE.X)
                .and(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.LOCAL_MATERIAL_NUMBER).is(localMaterialnumber).toQueryString();
        LogUtil.getCoreLog().info("queryString:"+queryString);
        return queryForObject(IConstant.REGION.PLAN_CNS_MATERIAL_PLAN_STATUS, queryString, PlanCnsMaterialPlanStatusEntity.class);
    }

    public PlanCnsMaterialPlanStatusEntity getEntityWithLocalMaterialNumberAndsourceSystem(String localMaterialNumber, String sourceSystem) {
        String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.LOCAL_MATERIAL_NUMBER).is(localMaterialNumber)
                .and(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.SOURCE_SYSTEM).is(sourceSystem).toQueryString();
        return queryForObject(IConstant.REGION.PLAN_CNS_MATERIAL_PLAN_STATUS, queryString, PlanCnsMaterialPlanStatusEntity.class);
    }
}
