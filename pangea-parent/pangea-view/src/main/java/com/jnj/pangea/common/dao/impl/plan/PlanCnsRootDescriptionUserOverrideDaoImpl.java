package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;

 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.plan.PlanCnsRootDescriptionUserOverrideEntity;

public class PlanCnsRootDescriptionUserOverrideDaoImpl extends CommonDaoImpl {

    public static final String PLAN_CNS_ROOT_DESCRIPTION_USER_OVERRIDE = "/plan/cns_root_description_user_override";

    public static final String SOURCE_SYSTEM = "sourceSystem";
    public static final String LOCAL_DP_PARENT_CODE = "localDpParentCode";

    private static PlanCnsRootDescriptionUserOverrideDaoImpl instance;

    public static PlanCnsRootDescriptionUserOverrideDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsRootDescriptionUserOverrideDaoImpl();
        }
        return instance;
    }

    public PlanCnsRootDescriptionUserOverrideEntity getEntityWithSourceSystemAndLocalDpParentCode(String sourceSystem, String localDpParentCode) {

        String queryString = QueryHelper.buildCriteria(SOURCE_SYSTEM).is(sourceSystem)
                .and(LOCAL_DP_PARENT_CODE).is(localDpParentCode).toQueryString();
        return queryForObject(PLAN_CNS_ROOT_DESCRIPTION_USER_OVERRIDE, queryString, PlanCnsRootDescriptionUserOverrideEntity.class);
    }
}
