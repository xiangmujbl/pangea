package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.plan.PlanCnsConModeEntity;

public class PlanCnsConModeDaoImpl extends CommonDaoImpl {

    public static final String PLAN_CNS_CON_MODE = "/plan/cns_con_mode";

    public static final String SOURCE_SYSTEM = "sourceSystem";
    public static final String LOCAL_CONSUMPTION_MODE = "localConsumptionMode";

    private static PlanCnsConModeDaoImpl instance;

    public static PlanCnsConModeDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsConModeDaoImpl();
        }
        return instance;
    }

    public PlanCnsConModeEntity getEntityWithLocalConsumptionModeAndSourceSystem(String localConsumptionMode, String sourceSystem) {
        String queryString = QueryHelper.buildCriteria(LOCAL_CONSUMPTION_MODE).is(localConsumptionMode).and(SOURCE_SYSTEM).is(sourceSystem).toQueryString();
        return queryForObject(PLAN_CNS_CON_MODE, queryString, PlanCnsConModeEntity.class);
    }
}
