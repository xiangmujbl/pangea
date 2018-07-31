package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;


import com.jnj.pangea.common.entity.edm.EDMProductFamilyV1Entity;

public class EDMProductFamilyV1DaoImpl extends CommonDaoImpl {

    public static final String EDM_PRODUCT_FAMILY_V1 = "/edm/product_family_v1";

    public static final String PRODUCT_FAMILY = "productFamily";

    private static EDMProductFamilyV1DaoImpl instance;

    public static EDMProductFamilyV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMProductFamilyV1DaoImpl();
        }
        return instance;
    }

    public EDMProductFamilyV1Entity getEntityWithProductFamily(String productFamily) {
        String queryString = QueryHelper.buildCriteria(PRODUCT_FAMILY).is(productFamily).toQueryString();
        return queryForObject(EDM_PRODUCT_FAMILY_V1, queryString, EDMProductFamilyV1Entity.class);
    }
}
