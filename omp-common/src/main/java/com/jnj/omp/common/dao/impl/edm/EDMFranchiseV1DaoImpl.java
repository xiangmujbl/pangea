package com.jnj.omp.common.dao.impl.edm;

import com.jnj.omp.common.dao.impl.CommonDaoImpl;
import com.jnj.omp.common.entity.edm.EDMFranchiseV1Entity;

public class EDMFranchiseV1DaoImpl extends CommonDaoImpl {

    private static EDMFranchiseV1DaoImpl instance;

    public static EDMFranchiseV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMFranchiseV1DaoImpl();
        }
        return instance;
    }

    public EDMFranchiseV1Entity getEntityWithConditions(String param) {

        //String queryString = QueryHelper.buildCriteria(IConstant.EDM_COUNTRY_V1.LOCAL_COUNTRY).is(land1).toQueryString();
        //return queryForObject(IConstant.REGION.EDM_COUNTRY_V1, localQueryString, EDMCountryEntity.class);
        return null;
    }
}
