package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;

 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.plan.PlanCnsPlngStratGrpEntity;

public class PlanCnsPlngStratGrpDaoImpl extends CommonDaoImpl {

    public static final String PLAN_CNS_PLNG_STRAT_GRP = "/plan/cns_plng_strat_grp";

    public static final String SOURCE_SYSTEM = "sourceSystem";
    public static final String LOCAL_PLAN_STRAT_GRP = "localPlanStratGrp";

    private static PlanCnsPlngStratGrpDaoImpl instance;

    public static PlanCnsPlngStratGrpDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsPlngStratGrpDaoImpl();
        }
        return instance;
    }


    public PlanCnsPlngStratGrpEntity getEntityWithLocalPlanStratGrpAndSourceSystem(String localPlanningStrategyGroup, String sourceSystem) {
        String queryString = QueryHelper.buildCriteria(LOCAL_PLAN_STRAT_GRP).is(localPlanningStrategyGroup).and(SOURCE_SYSTEM).is(sourceSystem).toQueryString();
        return queryForObject(PLAN_CNS_PLNG_STRAT_GRP, queryString, PlanCnsPlngStratGrpEntity.class);
    }
}
