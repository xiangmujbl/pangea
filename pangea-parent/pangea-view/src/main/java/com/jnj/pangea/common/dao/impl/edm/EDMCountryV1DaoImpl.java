package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMCountryEntity;
import com.jnj.pangea.common.entity.edm.EDMCountryV1Entity;

public class EDMCountryV1DaoImpl extends CommonDaoImpl {

    private static EDMCountryV1DaoImpl instance;

    public static EDMCountryV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMCountryV1DaoImpl();
        }
        return instance;
    }


    public EDMCountryEntity getEntityWithLocalCountry(String land1) {
        if (null != land1 && !"".equals(land1)) {
            String localQueryString = QueryHelper.buildCriteria(IConstant.EDM_COUNTRY_V1.LOCAL_COUNTRY).is(land1).toQueryString();
            return queryForObject(IConstant.REGION.EDM_COUNTRY_V1, localQueryString, EDMCountryV1Entity.class);
        }
        return null;
    }
}
