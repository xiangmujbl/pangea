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

    public EDMCurrencyV1Entity getEntityWithLocalCurrencyAndSourceSystem(String localCurrency, String sourceSystem) {
        if ("".equals(localCurrency) || "".equals(sourceSystem)) {
            return null;
        }
        String queryString = QueryHelper.buildCriteria(IConstant.EDM_CURRENCY_V1.LOCAL_CURRENCY).is(localCurrency).and(IConstant.EDM_CURRENCY_V1.SOURCE_SYSTEM).is(sourceSystem).toQueryString();
        return queryForObject(IConstant.REGION.EDM_CURRENCY_V1, queryString, EDMCurrencyV1Entity.class);
    }
}
