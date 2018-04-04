package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMCurrencyV1Entity;

public class EDMCurrencyV1DaoImpl extends CommonDaoImpl {

    private static EDMCurrencyV1DaoImpl instance;

    public static EDMCurrencyV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMCurrencyV1DaoImpl();
        }
        return instance;
    }

    public EDMCurrencyV1Entity getEntityWithLocalCurrency(String localCurrency) {

        String queryString = QueryHelper.buildCriteria(IConstant.EDM_CURRENCY.LOCAL_CURRENCY).is(localCurrency).toQueryString();
        return queryForObject(IConstant.REGION.EDM_CURRENCY_V1, queryString, EDMCurrencyV1Entity.class);
    }
}
