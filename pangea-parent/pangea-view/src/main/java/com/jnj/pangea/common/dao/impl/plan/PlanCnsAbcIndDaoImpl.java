package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.plan.PlanCnsAbcIndEntity;

public class PlanCnsAbcIndDaoImpl extends CommonDaoImpl {

    public static final String PLAN_CNS_ABC_IND = "/plan/cns_abc_ind";

    public static final String SOURCE_SYSTEM = "sourceSystem";
    public static final String LOCAL_INDICATOR = "localIndicator";

    private static PlanCnsAbcIndDaoImpl instance;

    public static PlanCnsAbcIndDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsAbcIndDaoImpl();
        }
        return instance;
    }


    public PlanCnsAbcIndEntity getEntityWithLocalIndicatorAndsourceSystem(String localAbcIndicator, String sourceSystem) {
        String queryString = QueryHelper.buildCriteria(LOCAL_INDICATOR).is(localAbcIndicator).and(SOURCE_SYSTEM).is(sourceSystem).toQueryString();
        return queryForObject(PLAN_CNS_ABC_IND, queryString, PlanCnsAbcIndEntity.class);
    }
}
