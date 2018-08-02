package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;


import com.jnj.pangea.common.entity.edm.EDMFranchiseV1Entity;

public class EDMFranchiseV1DaoImpl extends CommonDaoImpl {

    public static final String EDM_FRANCHISE_V1 = "/edm/franchise_v1";

    public static final String FRANCHISE = "franchise";

    private static EDMFranchiseV1DaoImpl instance;

    public static EDMFranchiseV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMFranchiseV1DaoImpl();
        }
        return instance;
    }

    public EDMFranchiseV1Entity getEntityWithFranchise(String franchise) {
        String queryString = QueryHelper.buildCriteria(FRANCHISE).is(franchise).toQueryString();
        return queryForObject(EDM_FRANCHISE_V1, queryString, EDMFranchiseV1Entity.class);
    }
}
