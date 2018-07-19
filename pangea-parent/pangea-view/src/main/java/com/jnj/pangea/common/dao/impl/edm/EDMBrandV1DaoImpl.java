package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMBrandV1Entity;

public class EDMBrandV1DaoImpl extends CommonDaoImpl {


    public static final String BRAND = "brand";


    private static EDMBrandV1DaoImpl instance;

    public static EDMBrandV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMBrandV1DaoImpl();
        }
        return instance;
    }

    public EDMBrandV1Entity getEntityWithConditions(String param) {

        //String queryString = QueryHelper.buildCriteria(IConstant.EDM_COUNTRY_V1.LOCAL_COUNTRY).is(land1).toQueryString();
        //return queryForObject(RegionsConstant.EDM_COUNTRY_V1, localQueryString, EDMCountryEntity.class);
        return null;
    }

    public EDMBrandV1Entity getEntityWithBrand(String brand) {
        String queryString = QueryHelper.buildCriteria(BRAND).is(brand).toQueryString();
        return queryForObject(RegionsConstant.EDM_BRAND_V1, queryString, EDMBrandV1Entity.class);
    }
}
