package com.jnj.omp.common.dao.impl.edm;

import com.jnj.omp.common.dao.impl.CommonDaoImpl;
import com.jnj.omp.common.entity.edm.EDMBrandV1Entity;

public class EDMBrandV1DaoImpl extends CommonDaoImpl {

    private static EDMBrandV1DaoImpl instance;

    public static EDMBrandV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMBrandV1DaoImpl();
        }
        return instance;
    }

    public EDMBrandV1Entity getEntityWithConditions(String param) {

        //String queryString = QueryHelper.buildCriteria(IConstant.EDM_COUNTRY_V1.LOCAL_COUNTRY).is(land1).toQueryString();
        //return queryForObject(IConstant.REGION.EDM_COUNTRY_V1, localQueryString, EDMCountryEntity.class);
        return null;
    }
}
