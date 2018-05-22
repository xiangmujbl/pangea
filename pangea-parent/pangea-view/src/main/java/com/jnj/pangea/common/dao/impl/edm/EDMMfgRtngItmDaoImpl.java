package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.edm.EDMMfgRtngItmEntity;

public class EDMMfgRtngItmDaoImpl extends CommonDaoImpl {

    private static EDMMfgRtngItmDaoImpl instance;

    public static EDMMfgRtngItmDaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMMfgRtngItmDaoImpl();
        }
        return instance;
    }

    public EDMMfgRtngItmEntity getEntityWithConditions(String param) {

        //String queryString = QueryHelper.buildCriteria(IConstant.EDM_COUNTRY_V1.LOCAL_COUNTRY).is(land1).toQueryString();
        //return queryForObject(IConstant.REGION.EDM_COUNTRY_V1, localQueryString, EDMCountryEntity.class);
        return null;
    }
}
