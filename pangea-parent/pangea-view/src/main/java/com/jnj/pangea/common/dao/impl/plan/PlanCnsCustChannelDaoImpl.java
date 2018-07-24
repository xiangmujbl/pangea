package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsCustChannelEntity;

import java.util.List;

public class PlanCnsCustChannelDaoImpl extends CommonDaoImpl {

    public static final String PLAN_CNS_CUST_CHANNEL = "/plan/cns_cust_channel";

    public static final String CHANNEL = "channel";
    public static final String SALES_ORG = "salesOrg";
    public static final String SOURCE_SYSTEM = "sourceSystem";

    private static PlanCnsCustChannelDaoImpl instance;

    public static PlanCnsCustChannelDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsCustChannelDaoImpl();
        }
        return instance;
    }

    public List<PlanCnsCustChannelEntity> getEntitiesWithStartCharInSalesOrg(String startCharInSalesOrg) {

        String queryString = QueryHelper.buildCriteria(SALES_ORG).startsWith(startCharInSalesOrg).toQueryString();
        return queryForList(PLAN_CNS_CUST_CHANNEL, queryString, PlanCnsCustChannelEntity.class);
    }

    public List<PlanCnsCustChannelEntity> getEntitiesWithSourceSystemAndSalesOrgAndChannel(String sourceSystem, String salesOrg, String channel) {

        String queryString = QueryHelper.buildCriteria(SOURCE_SYSTEM).is(sourceSystem)
                .and(SALES_ORG).is(salesOrg)
                .and(CHANNEL).is(channel).toQueryString();
        return queryForList(PLAN_CNS_CUST_CHANNEL, queryString, PlanCnsCustChannelEntity.class);
    }

}
