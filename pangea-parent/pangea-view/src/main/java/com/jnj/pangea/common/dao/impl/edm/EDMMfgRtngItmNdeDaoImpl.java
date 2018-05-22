package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.edm.EDMMfgRtngItmNdeEntity;

public class EDMMfgRtngItmNdeDaoImpl extends CommonDaoImpl {

    private static EDMMfgRtngItmNdeDaoImpl instance;

    public static EDMMfgRtngItmNdeDaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMMfgRtngItmNdeDaoImpl();
        }
        return instance;
    }

    public EDMMfgRtngItmNdeEntity getEntityWithConditions(String param) {

        //String queryString = QueryHelper.buildCriteria(IConstant.EDM_COUNTRY_V1.LOCAL_COUNTRY).is(land1).toQueryString();
        //return queryForObject(IConstant.REGION.EDM_COUNTRY_V1, localQueryString, EDMCountryEntity.class);
        return null;
    }
}
