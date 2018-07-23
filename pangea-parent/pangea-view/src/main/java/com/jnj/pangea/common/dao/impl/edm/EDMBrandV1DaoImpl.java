package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.query.QueryHelper;


import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMBrandV1Entity;

public class EDMBrandV1DaoImpl extends CommonDaoImpl {

    public static final String EDM_BRAND_V1 = "/edm/brand_v1";

    public static final String BRAND = "brand";


    private static EDMBrandV1DaoImpl instance;

    public static EDMBrandV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMBrandV1DaoImpl();
        }
        return instance;
    }

    public EDMBrandV1Entity getEntityWithBrand(String brand) {
        String queryString = QueryHelper.buildCriteria(BRAND).is(brand).toQueryString();
        return queryForObject(EDM_BRAND_V1, queryString, EDMBrandV1Entity.class);
    }
}
