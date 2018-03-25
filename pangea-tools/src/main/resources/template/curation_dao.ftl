package com.jnj.pangea.common.dao.impl.${entitySystem};

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.${entitySystem}.${entityName?cap_first}Entity;

public class ${entityName?cap_first}DaoImpl extends CommonDaoImpl {

    private static ${entityName?cap_first}DaoImpl instance;

    public static ${entityName?cap_first}DaoImpl getInstance() {
        if (instance == null) {
            instance = new ${entityName?cap_first}DaoImpl();
        }
        return instance;
    }

    public ${entityName?cap_first}Entity getEntityWithConditions(String param) {

        //String queryString = QueryHelper.buildCriteria(IConstant.EDM_COUNTRY_V1.LOCAL_COUNTRY).is(land1).toQueryString();
        //return queryForObject(IConstant.REGION.EDM_COUNTRY_V1, localQueryString, EDMCountryEntity.class);
        return null;
    }
}
