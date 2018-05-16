package com.jnj.omp.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.omp.common.IConstant;
import com.jnj.omp.common.dao.impl.CommonDaoImpl;
import com.jnj.omp.common.entity.plan.PlanCnsCustChannelEntity;

import java.util.List;

public class PlanCnsCustChannelDaoImpl extends CommonDaoImpl {

    private static PlanCnsCustChannelDaoImpl instance;

    public static PlanCnsCustChannelDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsCustChannelDaoImpl();
        }
        return instance;
    }

    public List<PlanCnsCustChannelEntity> getEntitiesWithStartCharInSalesOrg(String startCharInSalesOrg) {

        String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_CUST_CHANNEL.SALES_ORG).startsWith(startCharInSalesOrg).toQueryString();
        return queryForList(IConstant.REGION.PLAN_CNS_CUST_CHANNEL, queryString, PlanCnsCustChannelEntity.class);
    }

}
