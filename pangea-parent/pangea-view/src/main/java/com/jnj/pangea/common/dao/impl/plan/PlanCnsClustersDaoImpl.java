package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.plan.PlanCnsClustersEntity;

public class PlanCnsClustersDaoImpl extends CommonDaoImpl {

    private static PlanCnsClustersDaoImpl instance;

    public static PlanCnsClustersDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsClustersDaoImpl();
        }
        return instance;
    }

    public PlanCnsClustersEntity getEntityWithCountryIdAndSourceSystem(String countryId, String sourceSystem) {

        String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_CLUSTERS.COUNTRY_ID).is(countryId)
                .and(IConstant.PLAN_CNS_CLUSTERS.SOURCE_SYSTEM).is(sourceSystem).toQueryString();
        return queryForObject(IConstant.REGION.PLAN_CNS_CLUSTERS, queryString, PlanCnsClustersEntity.class);
    }
}
