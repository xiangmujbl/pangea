package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.plan.PlanCnsProductCustomerEntity;

public class PlanCnsProductCustomerDaoImpl extends CommonDaoImpl {

    private static PlanCnsProductCustomerDaoImpl instance;

    public static PlanCnsProductCustomerDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsProductCustomerDaoImpl();
        }
        return instance;
    }

    public PlanCnsProductCustomerEntity getEntityWithDemandGroup(String demandGroup) {

        String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_PRODUCT_CUSTOMER.DEMAND_GROUP).is(demandGroup).toQueryString();
        return queryForObject(IConstant.REGION.PLAN_CNS_PRODUCT_CUSTOMER, queryString, PlanCnsProductCustomerEntity.class);
    }
}
