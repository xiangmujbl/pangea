package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsPlnSplLocEntity;


public class PlanCnsPlnSplLocDaoImpl extends CommonDaoImpl {
    private static PlanCnsPlnSplLocDaoImpl instance;

    public static PlanCnsPlnSplLocDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsPlnSplLocDaoImpl();
        }
        return instance;
    }

    public PlanCnsPlnSplLocEntity getEntityWithSourceSystemAndLocalNumber(String sourceSystem, String localNumber) {
        String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_PLN_SPL_LOC.SOURCE_SYSTEM).is(sourceSystem).and(IConstant.PLAN_CNS_PLN_SPL_LOC.LOCAL_NUMBER).is(localNumber).toQueryString();
        return queryForObject(IConstant.REGION.PLAN_CNS_PLN_SPL_LOC,queryString,PlanCnsPlnSplLocEntity.class);
    }


}
