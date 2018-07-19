package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.edm.EDMSubBrandV1Entity;

public class EDMSubBrandV1DaoImpl extends CommonDaoImpl {

    public static final String SUB_BRAND = "subBrand";

    private static EDMSubBrandV1DaoImpl instance;

    public static EDMSubBrandV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMSubBrandV1DaoImpl();
        }
        return instance;
    }

    public EDMSubBrandV1Entity getEntityWithConditions(String param) {

        //String queryString = QueryHelper.buildCriteria(LOCAL_COUNTRY).is(land1).toQueryString();
        //return queryForObject(RegionsConstant.EDM_COUNTRY_V1, localQueryString, EDMCountryEntity.class);
        return null;
    }

    public EDMSubBrandV1Entity getEntityWithSubBrand(String subBrand) {
        String queryString = QueryHelper.buildCriteria(SUB_BRAND).is(subBrand).toQueryString();
        return queryForObject(RegionsConstant.EDM_SUB_BRAND_V1, queryString, EDMSubBrandV1Entity.class);
    }
}
