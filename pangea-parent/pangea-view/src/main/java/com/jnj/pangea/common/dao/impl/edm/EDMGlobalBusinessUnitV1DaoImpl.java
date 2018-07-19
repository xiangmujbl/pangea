package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.edm.EDMGlobalBusinessUnitV1Entity;

public class EDMGlobalBusinessUnitV1DaoImpl extends CommonDaoImpl {

    public static final String GBU = "gbu";

    private static EDMGlobalBusinessUnitV1DaoImpl instance;

    public static EDMGlobalBusinessUnitV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMGlobalBusinessUnitV1DaoImpl();
        }
        return instance;
    }

    public EDMGlobalBusinessUnitV1Entity getEntityWithConditions(String param) {

        //String queryString = QueryHelper.buildCriteria(LOCAL_COUNTRY).is(land1).toQueryString();
        //return queryForObject(RegionsConstant.EDM_COUNTRY_V1, localQueryString, EDMCountryEntity.class);
        return null;
    }

    public EDMGlobalBusinessUnitV1Entity getEntityWithGbu(String globalBusinessUnit) {
        String queryString = QueryHelper.buildCriteria(GBU).is(globalBusinessUnit).toQueryString();
        return queryForObject(RegionsConstant.EDM_GLOBAL_BASE_UNIT_V1, queryString, EDMGlobalBusinessUnitV1Entity.class);
    }
}
