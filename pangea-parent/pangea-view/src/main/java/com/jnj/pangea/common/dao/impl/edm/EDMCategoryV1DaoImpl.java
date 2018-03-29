package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.edm.EDMCategoryV1Entity;

public class EDMCategoryV1DaoImpl extends CommonDaoImpl {

    private static EDMCategoryV1DaoImpl instance;

    public static EDMCategoryV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMCategoryV1DaoImpl();
        }
        return instance;
    }

    public EDMCategoryV1Entity getEntityWithConditions(String param) {

        //String queryString = QueryHelper.buildCriteria(IConstant.EDM_COUNTRY_V1.LOCAL_COUNTRY).is(land1).toQueryString();
        //return queryForObject(IConstant.REGION.EDM_COUNTRY_V1, localQueryString, EDMCountryEntity.class);
        return null;
    }
}
