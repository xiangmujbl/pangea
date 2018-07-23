package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;


import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMCountryEntity;

import java.util.List;

public class EDMCountryV1DaoImpl extends CommonDaoImpl {

    public static final String EDM_COUNTRY_V1 = "/edm/country_v1";

    public static final String LOCAL_COUNTRY = "localCountry";
    public static final String SOURCE_SYSTEM = "sourceSystem";
    public static final String COUNTRY_CODE = "countryCode";

    private static EDMCountryV1DaoImpl instance;

    public static EDMCountryV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMCountryV1DaoImpl();
        }
        return instance;
    }


    public EDMCountryEntity getEntityWithLocalCountry(String localCountry) {
        if (null != localCountry && !"".equals(localCountry)) {
            String localQueryString = QueryHelper.buildCriteria(LOCAL_COUNTRY).is(localCountry).toQueryString();
            return queryForObject(EDM_COUNTRY_V1, localQueryString, EDMCountryEntity.class);
        }
        return null;
    }

    public List<EDMCountryEntity> getEntityWithLocalCountryList(String localCountry) {
        if (null != localCountry && !"".equals(localCountry)) {
            String localQueryString = QueryHelper.buildCriteria(LOCAL_COUNTRY).is(localCountry).toQueryString();
            return queryForList(EDM_COUNTRY_V1, localQueryString, EDMCountryEntity.class);
        }
        return null;
    }

    public EDMCountryEntity getEntityWithLocalCountryAndSourceSystem(String localCountry, String sourceSystem) {
        if ("".equals(localCountry) || "".equals(sourceSystem)) {
            return null;
        }
        String localQueryString = QueryHelper.buildCriteria(LOCAL_COUNTRY).is(localCountry).and(SOURCE_SYSTEM).is(sourceSystem).toQueryString();
        return queryForObject(EDM_COUNTRY_V1, localQueryString, EDMCountryEntity.class);
    }
}