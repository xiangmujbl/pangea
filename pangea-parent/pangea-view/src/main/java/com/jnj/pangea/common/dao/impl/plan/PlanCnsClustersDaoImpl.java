package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;

 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.plan.PlanCnsClustersEntity;

public class PlanCnsClustersDaoImpl extends CommonDaoImpl {

    public static final String PLAN_CNS_CLUSTERS = "/plan/cns_clusters";

    public static final String COUNTRY_ID = "countryId";
    public static final String SOURCE_SYSTEM = "sourceSystem";

    private static PlanCnsClustersDaoImpl instance;

    public static PlanCnsClustersDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsClustersDaoImpl();
        }
        return instance;
    }

    public PlanCnsClustersEntity getEntityWithCountryIdAndSourceSystem(String countryId, String sourceSystem) {

        String queryString = QueryHelper.buildCriteria(COUNTRY_ID).is(countryId)
                .and(SOURCE_SYSTEM).is(sourceSystem).toQueryString();
        return queryForObject(PLAN_CNS_CLUSTERS, queryString, PlanCnsClustersEntity.class);
    }
}
