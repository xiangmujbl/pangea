package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;


import com.jnj.pangea.common.entity.edm.EDMGlobalBusinessUnitV1Entity;

public class EDMGlobalBusinessUnitV1DaoImpl extends CommonDaoImpl {

    public static final String EDM_GLOBAL_BASE_UNIT_V1 = "/edm/global_business_unit_v1";

    public static final String GBU = "gbu";

    private static EDMGlobalBusinessUnitV1DaoImpl instance;

    public static EDMGlobalBusinessUnitV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMGlobalBusinessUnitV1DaoImpl();
        }
        return instance;
    }


    public EDMGlobalBusinessUnitV1Entity getEntityWithGbu(String globalBusinessUnit) {
        String queryString = QueryHelper.buildCriteria(GBU).is(globalBusinessUnit).toQueryString();
        return queryForObject(EDM_GLOBAL_BASE_UNIT_V1, queryString, EDMGlobalBusinessUnitV1Entity.class);
    }
}
