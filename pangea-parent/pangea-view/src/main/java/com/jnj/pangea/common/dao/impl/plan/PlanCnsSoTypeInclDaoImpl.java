package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.plan.PlanCnsSoTypeInclEntity;

public class PlanCnsSoTypeInclDaoImpl extends CommonDaoImpl {

    private static PlanCnsSoTypeInclDaoImpl instance;

    public static PlanCnsSoTypeInclDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsSoTypeInclDaoImpl();
        }
        return instance;
    }

    public PlanCnsSoTypeInclEntity getEntityWithConditions(String param) {

        //String queryString = QueryHelper.buildCriteria(IConstant.EDM_COUNTRY_V1.LOCAL_COUNTRY).is(land1).toQueryString();
        //return queryForObject(IConstant.REGION.EDM_COUNTRY_V1, localQueryString, EDMCountryEntity.class);
        return null;
    }
}
