package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMCountryEntity;
import com.jnj.pangea.common.entity.edm.EDMCountryV1Entity;

import java.util.List;

public class EDMCountryV1DaoImpl extends CommonDaoImpl {

    private static EDMCountryV1DaoImpl instance;

    public static EDMCountryV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMCountryV1DaoImpl();
        }
        return instance;
    }


    public EDMCountryEntity getEntityWithLocalCountry(String localCountry) {
        if (null != localCountry && !"".equals(localCountry)) {
            String localQueryString = QueryHelper.buildCriteria(IConstant.EDM_COUNTRY_V1.LOCAL_COUNTRY).is(localCountry).toQueryString();
            return queryForObject(IConstant.REGION.EDM_COUNTRY_V1, localQueryString, EDMCountryEntity.class);
        }
        return null;
    }
    public List<EDMCountryEntity> getEntityWithLocalCountryList(String localCountry) {
        if (null != localCountry && !"".equals(localCountry)) {
            String localQueryString = QueryHelper.buildCriteria(IConstant.EDM_COUNTRY_V1.LOCAL_COUNTRY).is(localCountry).toQueryString();
            return queryForList(IConstant.REGION.EDM_COUNTRY_V1, localQueryString, EDMCountryEntity.class);
        }
        return null;
    }
}
