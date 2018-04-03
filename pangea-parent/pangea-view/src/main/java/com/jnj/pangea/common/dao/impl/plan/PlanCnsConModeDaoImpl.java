package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.plan.PlanCnsConModeEntity;

public class PlanCnsConModeDaoImpl extends CommonDaoImpl {

    private static PlanCnsConModeDaoImpl instance;

    public static PlanCnsConModeDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsConModeDaoImpl();
        }
        return instance;
    }

    public PlanCnsConModeEntity getEntityWithLocalConsumptionModeAndSourceSystem(String localConsumptionMode, String sourceSystem) {
        String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_CON_MODE.LOCAL_CONSUMPTION_MODE).is(localConsumptionMode).and(IConstant.PLAN_CNS_CON_MODE.SOURCE_SYSTEM).is(sourceSystem).toQueryString();
        return queryForObject(IConstant.REGION.PLAN_CNS_CON_MODE, queryString, PlanCnsConModeEntity.class);
    }
}
