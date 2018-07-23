package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;


import com.jnj.pangea.common.entity.edm.EDMSubBrandV1Entity;

public class EDMSubBrandV1DaoImpl extends CommonDaoImpl {

    public static final String EDM_SUB_BRAND_V1 = "/edm/sub_brand_v1";

    public static final String SUB_BRAND = "subBrand";

    private static EDMSubBrandV1DaoImpl instance;

    public static EDMSubBrandV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMSubBrandV1DaoImpl();
        }
        return instance;
    }

    public EDMSubBrandV1Entity getEntityWithSubBrand(String subBrand) {
        String queryString = QueryHelper.buildCriteria(SUB_BRAND).is(subBrand).toQueryString();
        return queryForObject(EDM_SUB_BRAND_V1, queryString, EDMSubBrandV1Entity.class);
    }
}
