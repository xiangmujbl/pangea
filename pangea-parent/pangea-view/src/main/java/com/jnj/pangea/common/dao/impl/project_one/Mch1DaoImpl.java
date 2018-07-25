package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.project_one.Mch1Entity;

public class Mch1DaoImpl extends CommonDaoImpl {

    private static Mch1DaoImpl instance;

    public static Mch1DaoImpl getInstance() {
        if (instance == null) {
            instance = new Mch1DaoImpl();
        }
        return instance;
    }

    public Mch1Entity getEntityWithConditions(String param) {

        //String queryString = QueryHelper.buildCriteria(LOCAL_COUNTRY).is(land1).toQueryString();
        //return queryForObject(EDM_COUNTRY_V1, localQueryString, EDMCountryEntity.class);
        return null;
    }
}
