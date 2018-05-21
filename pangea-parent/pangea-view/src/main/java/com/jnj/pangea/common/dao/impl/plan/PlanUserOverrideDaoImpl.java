package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.plan.PlanUserOverrideEntity;

public class PlanUserOverrideDaoImpl extends CommonDaoImpl {

    private static PlanUserOverrideDaoImpl instance;

    public static PlanUserOverrideDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanUserOverrideDaoImpl();
        }
        return instance;
    }

    public PlanUserOverrideEntity getEntityWithKey1AndKey2(String target, String sourceSystem, String key1, String key2) {

        String queryString = QueryHelper.buildCriteria(IConstant.PLAN_USER_OVERRIDE.TARGET).is(target)
                .and(IConstant.PLAN_USER_OVERRIDE.SOURCE_SYSTEM).is(sourceSystem)
                .and(IConstant.PLAN_USER_OVERRIDE.KEY1).is(key1)
                .and(IConstant.PLAN_USER_OVERRIDE.KEY2).is(key2).toQueryString();
        return queryForObject(IConstant.REGION.PLAN_USER_OVERRIDE, queryString, PlanUserOverrideEntity.class);
    }
}
