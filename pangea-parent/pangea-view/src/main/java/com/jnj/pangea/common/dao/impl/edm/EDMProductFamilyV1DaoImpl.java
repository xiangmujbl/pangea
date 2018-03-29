package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.edm.EDMProductFamilyV1Entity;

public class EDMProductFamilyV1DaoImpl extends CommonDaoImpl {

    private static EDMProductFamilyV1DaoImpl instance;

    public static EDMProductFamilyV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMProductFamilyV1DaoImpl();
        }
        return instance;
    }

    public EDMProductFamilyV1Entity getEntityWithConditions(String param) {

        //String queryString = QueryHelper.buildCriteria(IConstant.EDM_COUNTRY_V1.LOCAL_COUNTRY).is(land1).toQueryString();
        //return queryForObject(IConstant.REGION.EDM_COUNTRY_V1, localQueryString, EDMCountryEntity.class);
        return null;
    }
}
