package com.jnj.omp.common.dao.impl.edm;

import com.jnj.omp.common.dao.impl.CommonDaoImpl;
import com.jnj.omp.common.entity.edm.EDMFormV1Entity;

public class EDMFormV1DaoImpl extends CommonDaoImpl {

    private static EDMFormV1DaoImpl instance;

    public static EDMFormV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMFormV1DaoImpl();
        }
        return instance;
    }

    public EDMFormV1Entity getEntityWithConditions(String param) {

        //String queryString = QueryHelper.buildCriteria(IConstant.EDM_COUNTRY_V1.LOCAL_COUNTRY).is(land1).toQueryString();
        //return queryForObject(IConstant.REGION.EDM_COUNTRY_V1, localQueryString, EDMCountryEntity.class);
        return null;
    }
}
