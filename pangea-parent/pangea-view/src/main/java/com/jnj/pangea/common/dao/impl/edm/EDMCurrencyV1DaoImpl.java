package com.jnj.pangea.common.dao.impl.edm;


import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.edm.EDMCurrencyV1Entity;

public class EDMCurrencyV1DaoImpl extends CommonDaoImpl {

    private static EDMCurrencyV1DaoImpl instance;

    public static EDMCurrencyV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMCurrencyV1DaoImpl();
        }
        return instance;
    }


    public EDMCurrencyV1Entity getEntityWithLocalCurrencyAndSourceSystem(String localCurrency, String sourceSystem) {
        if ("".equals(localCurrency) || "".equals(sourceSystem)) {
            return null;
        }
        String queryString = QueryHelper.buildCriteria(IConstant.EDM_CURRENCY_V1.LOCAL_CURRENCY).is(localCurrency).and(IConstant.EDM_CURRENCY_V1.SOURCE_SYSTEM).is(sourceSystem).toQueryString();
        return queryForObject(IConstant.REGION.EDM_CURRENCY_V1, queryString, EDMCurrencyV1Entity.class);
    }

    public EDMCurrencyV1Entity getEntityWithConditions(String param) {

        //String queryString = QueryHelper.buildCriteria(IConstant.EDM_COUNTRY_V1.LOCAL_COUNTRY).is(land1).toQueryString();
        //return queryForObject(IConstant.REGION.EDM_COUNTRY_V1, localQueryString, EDMCountryEntity.class);
        return null;
    }


    public EDMCurrencyV1Entity getEntityWithLocalCurrency(String currency) {
        if (null != currency && !"".equals(currency)) {
            String localQueryString = QueryHelper.buildCriteria(IConstant.EDM_CURRENCY_V1.LOCAL_CURRENCY).is(currency).toQueryString();
            return queryForObject(IConstant.REGION.EDM_CURRENCY_V1, localQueryString, EDMCurrencyV1Entity.class);
        }
        return null;
    }

}