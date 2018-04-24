package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsMaterialPlanStatusEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsProcessTypeEntity;

public class PlanCnsProcessTypeDaoImpl extends CommonDaoImpl {

    private static PlanCnsProcessTypeDaoImpl instance;

    public static PlanCnsProcessTypeDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsProcessTypeDaoImpl();
        }
        return instance;
    }

    public PlanCnsProcessTypeEntity getEntityWithConditions(String param) {

        String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_PROCESS_TYPE.PROCESS_TYPE_DESC).is(param).toQueryString();
        return queryForObject(IConstant.REGION.PLAN_CNS_PROCESS_TYPE, queryString, PlanCnsProcessTypeEntity.class);
    }
}
