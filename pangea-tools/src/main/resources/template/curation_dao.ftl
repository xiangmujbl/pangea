package com.jnj.pangea.common.dao.impl.${entity.system};

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.${entity.system}.${entity.fullName}Entity;

public class ${entity.fullName}DaoImpl extends CommonDaoImpl {

    private static ${entity.fullName}DaoImpl instance;

    public static ${entity.fullName}DaoImpl getInstance() {
        if (instance == null) {
            instance = new ${entity.fullName}DaoImpl();
        }
        return instance;
    }

    public ${entity.fullName}Entity getEntityWithConditions(String param) {

        //String queryString = QueryHelper.buildCriteria(IConstant.EDM_COUNTRY_V1.LOCAL_COUNTRY).is(land1).toQueryString();
        //return queryForObject(IConstant.REGION.EDM_COUNTRY_V1, localQueryString, EDMCountryEntity.class);
        return null;
    }
}
