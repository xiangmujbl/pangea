package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.plan.PlanCnsAbcIndEntity;

public class PlanCnsAbcIndDaoImpl extends CommonDaoImpl {

    private static PlanCnsAbcIndDaoImpl instance;

    public static PlanCnsAbcIndDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsAbcIndDaoImpl();
        }
        return instance;
    }


    public PlanCnsAbcIndEntity getEntityWithLocalIndicatorAndsourceSystem(String localAbcIndicator, String sourceSystem) {
        String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_ABC_IND.LOCAL_INDICATOR).is(localAbcIndicator).and(IConstant.PLAN_CNS_ABC_IND.SOURCE_SYSTEM).is(sourceSystem).toQueryString();
        return queryForObject(IConstant.REGION.PLAN_CNS_ABC_IND, queryString, PlanCnsAbcIndEntity.class);
    }
}
