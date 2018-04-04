package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsCustChannelEntity;

import java.util.List;

public class PlanCnsCustChannelDaoImpl extends CommonDaoImpl {

    private static PlanCnsCustChannelDaoImpl instance;

    public static PlanCnsCustChannelDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsCustChannelDaoImpl();
        }
        return instance;
    }

    public List<PlanCnsCustChannelEntity> getEntitiesWithStartCharInChannel(String startCharInChannel) {

        String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_CUST_CHANNEL.CHANNEL).startsWith(startCharInChannel).toQueryString();
        return queryForList(IConstant.REGION.PLAN_CNS_CUST_CHANNEL, queryString, PlanCnsCustChannelEntity.class);
    }
}
