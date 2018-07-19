package com.jnj.pangea.common.dao.impl.edm;


import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;

import com.jnj.pangea.common.entity.edm.EDMCurrencyV1Entity;
import org.eclipse.jetty.util.StringUtil;

public class EDMCurrencyV1DaoImpl extends CommonDaoImpl {

    public static final String SOURCE_SYSTEM = "sourceSystem";
    public static final String LOCAL_CURRENCY = "localCurrency";

    private static EDMCurrencyV1DaoImpl instance;

    public static EDMCurrencyV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMCurrencyV1DaoImpl();
        }
        return instance;
    }

    public EDMCurrencyV1Entity getEntityWithConditions(String param) {

        //String queryString = QueryHelper.buildCriteria(IConstant.EDM_COUNTRY_V1.LOCAL_COUNTRY).is(land1).toQueryString();
        //return queryForObject(RegionsConstant.EDM_COUNTRY_V1, localQueryString, EDMCountryEntity.class);
        return null;
    }


    public EDMCurrencyV1Entity getEntityWithLocalCurrency(String currency) {
        if (null != currency && !"".equals(currency)) {
            String localQueryString = QueryHelper.buildCriteria(LOCAL_CURRENCY).is(currency).toQueryString();
            return queryForObject(RegionsConstant.EDM_CURRENCY_V1, localQueryString, EDMCurrencyV1Entity.class);
        }
        return null;
    }

    public EDMCurrencyV1Entity getEntityWithLocalCurrencyAndSourceSystem(String localCurrency,String sourceSystem) {
        if (StringUtil.isNotBlank(localCurrency) && StringUtil.isNotBlank(sourceSystem)) {
            String localQueryString = QueryHelper.buildCriteria(
                    LOCAL_CURRENCY)
                    .is(localCurrency)
                    .and(SOURCE_SYSTEM)
                    .is(sourceSystem)
                    .toQueryString();
            return queryForObject(RegionsConstant.EDM_CURRENCY_V1, localQueryString, EDMCurrencyV1Entity.class);
        }
        return null;
    }
}