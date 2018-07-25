package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.ADFCriteria;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.VbkdEntity;

public class VbkdDaoImpl extends CommonDaoImpl {

    public static final String PROJECT_ONE_VBKD = "/project_one/vbkd";

    public static final String VBELN = "vbeln";
    public static final String POSNR = "posnr";
    public static final String PARVW = "parvw";

    private static VbkdDaoImpl instance;

    public static VbkdDaoImpl getInstance() {
        if (instance == null) {
            instance = new VbkdDaoImpl();
        }
        return instance;
    }

    public VbkdEntity getEntityWithConditions(String param) {

        //String queryString = QueryHelper.buildCriteria(LOCAL_COUNTRY).is(land1).toQueryString();
        //return queryForObject(EDM_COUNTRY_V1, localQueryString, EDMCountryEntity.class);
        return null;
    }

    public VbkdEntity getEntityWithPosnrAndVbelAndPosnrIsNullOrBlankOr000000(String vbeln) {
        ADFCriteria adfCriteria = QueryHelper.buildCriteria(POSNR).is("000000");
        ADFCriteria adfCriteria1 = QueryHelper.buildCriteria(POSNR).isNull();
        ADFCriteria criteria = adfCriteria1.or(adfCriteria);
        String queryString = QueryHelper.buildCriteria(VBELN).is(vbeln).and(criteria).toQueryString();
        return queryForObject(PROJECT_ONE_VBKD, queryString, VbkdEntity.class);
    }
}
