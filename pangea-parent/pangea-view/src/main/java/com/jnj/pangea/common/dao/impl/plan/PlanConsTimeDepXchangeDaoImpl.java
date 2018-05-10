package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanParameterEntity;
import com.jnj.pangea.common.entity.plan.PlanConsTimeDepXchangeEntity;

import java.util.List;

public class PlanConsTimeDepXchangeDaoImpl extends CommonDaoImpl {
    private static PlanCnsAbcIndDaoImpl instance;

    public static PlanCnsAbcIndDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsAbcIndDaoImpl();
        }
        return instance;
    }


    public List<PlanConsTimeDepXchangeEntity> getEntityListWithUnitId(String localCurrency) {
        String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CONS_TIME_DEP_XCHANGE.UNIT_ID).is(localCurrency).toQueryString();
        return queryForList(IConstant.REGION.PLAN_CONS_TIME_DEP_XCHANGE, queryString, PlanConsTimeDepXchangeEntity.class);
    }
}
