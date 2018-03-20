package com.jnj.pangea.common.dao.impl;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;

public class EDMSourceSystemV1DaoImpl extends CommonDaoImpl {

    private static EDMSourceSystemV1DaoImpl instance;

    public static EDMSourceSystemV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMSourceSystemV1DaoImpl();
        }
        return instance;
    }

    public String getSourceSystemWithLocalSourceSystem(String localSourceSystem) {

        String queryString = QueryHelper.buildCriteria(IConstant.EDM_SOURCE_SYSTEM_V1.LOCAL_SOURCE_SYSTEM).is(localSourceSystem).toQueryString();

        EDMSourceSystemV1Entity sourceSystems = queryForEntity(IConstant.REGION.EDM_SOURCE_SYSTEM_V1, queryString, EDMSourceSystemV1Entity.class);
        if (null != sourceSystems) {
            return sourceSystems.getSourceSystem();
        }
        return "";
    }
}
