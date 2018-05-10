package com.jnj.omp.common.dao.impl.edm;

import com.jnj.omp.common.dao.impl.CommonDaoImpl;
import com.jnj.omp.common.entity.edm.EDMGlobalBaseUnitV1Entity;

public class EDMGlobalBaseUnitV1DaoImpl extends CommonDaoImpl {

    private static EDMGlobalBaseUnitV1DaoImpl instance;

    public static EDMGlobalBaseUnitV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMGlobalBaseUnitV1DaoImpl();
        }
        return instance;
    }

    public EDMGlobalBaseUnitV1Entity getEntityWithConditions(String param) {

        //String queryString = QueryHelper.buildCriteria(IConstant.EDM_COUNTRY_V1.LOCAL_COUNTRY).is(land1).toQueryString();
        //return queryForObject(IConstant.REGION.EDM_COUNTRY_V1, localQueryString, EDMCountryEntity.class);
        return null;
    }
}
