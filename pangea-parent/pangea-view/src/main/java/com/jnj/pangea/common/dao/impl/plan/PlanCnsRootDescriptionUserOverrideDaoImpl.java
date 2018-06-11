package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.plan.PlanCnsRootDescriptionUserOverrideEntity;

public class PlanCnsRootDescriptionUserOverrideDaoImpl extends CommonDaoImpl {

    private static PlanCnsRootDescriptionUserOverrideDaoImpl instance;

    public static PlanCnsRootDescriptionUserOverrideDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsRootDescriptionUserOverrideDaoImpl();
        }
        return instance;
    }

    public PlanCnsRootDescriptionUserOverrideEntity getEntityWithSourceSystemAndLocalDpParentCode(String sourceSystem, String localDpParentCode) {

        String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_ROOT_DESCRIPTION_USER_OVERRIDE.SOURCE_SYSTEM).is(sourceSystem)
                .and(IConstant.PLAN_CNS_ROOT_DESCRIPTION_USER_OVERRIDE.LOCAL_DP_PARENT_CODE).is(localDpParentCode).toQueryString();
        return queryForObject(IConstant.REGION.PLAN_CNS_ROOT_DESCRIPTION_USER_OVERRIDE, queryString, PlanCnsRootDescriptionUserOverrideEntity.class);
    }
}
