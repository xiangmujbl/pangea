package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.edm.EDMBomHdrEntity;

public class EDMBomHdrDaoImpl extends CommonDaoImpl {

    private static EDMBomHdrDaoImpl instance;

    public static EDMBomHdrDaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMBomHdrDaoImpl();
        }
        return instance;
    }

    public EDMBomHdrEntity getEntityWithConditions(String param) {

        //String queryString = QueryHelper.buildCriteria(IConstant.EDM_COUNTRY_V1.LOCAL_COUNTRY).is(land1).toQueryString();
        //return queryForObject(IConstant.REGION.EDM_COUNTRY_V1, localQueryString, EDMCountryEntity.class);
        return null;
    }
}
