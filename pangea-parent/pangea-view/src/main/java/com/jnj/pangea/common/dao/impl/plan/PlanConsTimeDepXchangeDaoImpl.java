package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;

 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanParameterEntity;
import com.jnj.pangea.common.entity.plan.PlanConsTimeDepXchangeEntity;

import java.util.List;

public class PlanConsTimeDepXchangeDaoImpl extends CommonDaoImpl {

    public static final String PLAN_CONS_TIME_DEP_XCHANGE = "/plan/cons_time_dep_xchange";
    public static final String PLAN_CONS_TIME_DEP_XCHANGE_CLONE = "/plan/cons_time_dep_xchange_clone";

    public static final String UNIT_ID = "unitId";
    public static final String FROM_CURRENCY = "fromCurrency";
    public static final String TO_CURRENCY = "toCurrency";

    private static PlanConsTimeDepXchangeDaoImpl instance;

    public static PlanConsTimeDepXchangeDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanConsTimeDepXchangeDaoImpl();
        }
        return instance;
    }

    public List<PlanConsTimeDepXchangeEntity> getEntityList(String localCurrency) {
        String queryString = QueryHelper.buildCriteria(FROM_CURRENCY).is(localCurrency)
                .and(TO_CURRENCY).is("USD")
                .toQueryString();
        return queryForList(PLAN_CONS_TIME_DEP_XCHANGE, queryString, PlanConsTimeDepXchangeEntity.class);
    }

    public List<PlanConsTimeDepXchangeEntity> getEntityListWithUnitId(String localCurrency) {
        String queryString = QueryHelper.buildCriteria(UNIT_ID).is(localCurrency).toQueryString();
        return queryForList(PLAN_CONS_TIME_DEP_XCHANGE, queryString, PlanConsTimeDepXchangeEntity.class);
    }

    public List<PlanConsTimeDepXchangeEntity> getEntityListWithFromCurrency(String fromCurrency) {

        String queryString = QueryHelper.buildCriteria(FROM_CURRENCY).is(fromCurrency).toQueryString();
        return queryForList(PLAN_CONS_TIME_DEP_XCHANGE_CLONE, queryString, PlanConsTimeDepXchangeEntity.class);
    }
}
