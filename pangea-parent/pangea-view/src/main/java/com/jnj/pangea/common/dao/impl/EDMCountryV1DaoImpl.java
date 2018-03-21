package com.jnj.pangea.common.dao.impl;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.edm.EDMCountryEntity;

public class EDMCountryV1DaoImpl extends CommonDaoImpl {

    private static EDMCountryV1DaoImpl instance;

    public static EDMCountryV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMCountryV1DaoImpl();
        }
        return instance;
    }


    public EDMCountryEntity getEntityWithLand1(String land1) {

        String localQueryString = QueryHelper.buildCriteria(IConstant.EDM_COUNTRY_V1.LOCAL_COUNTRY).is(land1).toQueryString();
        return queryForObject(IConstant.REGION.EDM_COUNTRY_V1, localQueryString, EDMCountryEntity.class);
    }
}
