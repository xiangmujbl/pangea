package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;


import com.jnj.pangea.common.entity.edm.EDMFormV1Entity;

public class EDMFormV1DaoImpl extends CommonDaoImpl {

    public static final String EDM_FORM_V1 = "/edm/form_v1";

    public static final String FORM = "formName";

    private static EDMFormV1DaoImpl instance;

    public static EDMFormV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMFormV1DaoImpl();
        }
        return instance;
    }


    public EDMFormV1Entity getEntityWithFormName(String form) {
        String queryString = QueryHelper.buildCriteria(FORM).is(form).toQueryString();
        return queryForObject(EDM_FORM_V1, queryString, EDMFormV1Entity.class);
    }
}
