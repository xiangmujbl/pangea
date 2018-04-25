package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.plan.PlanCnsPlngStratGrpEntity;

public class PlanCnsPlngStratGrpDaoImpl extends CommonDaoImpl {

    private static PlanCnsPlngStratGrpDaoImpl instance;

    public static PlanCnsPlngStratGrpDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsPlngStratGrpDaoImpl();
        }
        return instance;
    }

    public PlanCnsPlngStratGrpEntity getEntityWithConditions(String param) {

        //String queryString = QueryHelper.buildCriteria(IConstant.EDM_COUNTRY_V1.LOCAL_COUNTRY).is(land1).toQueryString();
        //return queryForObject(IConstant.REGION.EDM_COUNTRY_V1, localQueryString, EDMCountryEntity.class);
        return null;
    }

    public PlanCnsPlngStratGrpEntity getEntityWithLocalPlanStratGrpAndSourceSystem(String localPlanningStrategyGroup, String sourceSystem) {
        String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_PLNG_STRAT_GRP.LOCAL_PLAN_STRAT_GRP).is(localPlanningStrategyGroup).and(IConstant.PLAN_CNS_PLNG_STRAT_GRP.SOURCE_SYSTEM).is(sourceSystem).toQueryString();
        return queryForObject(IConstant.REGION.PLAN_CNS_PLNG_STRAT_GRP, queryString, PlanCnsPlngStratGrpEntity.class);
    }
}
