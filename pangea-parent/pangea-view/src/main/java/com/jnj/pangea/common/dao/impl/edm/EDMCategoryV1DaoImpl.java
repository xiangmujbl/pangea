package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;


import com.jnj.pangea.common.entity.edm.EDMCategoryV1Entity;

public class EDMCategoryV1DaoImpl extends CommonDaoImpl {

    public static final String EDM_CATEGORY_V1 = "/edm/category_v1";

    public static final String CATEGORY = "category";

    private static EDMCategoryV1DaoImpl instance;

    public static EDMCategoryV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMCategoryV1DaoImpl();
        }
        return instance;
    }


    public EDMCategoryV1Entity getEntityWithCategory(String category) {
        String queryString = QueryHelper.buildCriteria(CATEGORY).is(category).toQueryString();
        return queryForObject(EDM_CATEGORY_V1, queryString, EDMCategoryV1Entity.class);
    }
}
