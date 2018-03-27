package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.project_one.VbpaEntity;

public class VbpaDaoImpl extends CommonDaoImpl {

    private static VbpaDaoImpl instance;

    public static VbpaDaoImpl getInstance() {
        if (instance == null) {
            instance = new VbpaDaoImpl();
        }
        return instance;
    }

    public VbpaEntity getEntityWithConditions(String param) {

        //String queryString = QueryHelper.buildCriteria(IConstant.EDM_COUNTRY_V1.LOCAL_COUNTRY).is(land1).toQueryString();
        //return queryForObject(IConstant.REGION.EDM_COUNTRY_V1, localQueryString, EDMCountryEntity.class);
        return null;
    }

    public VbpaEntity getEntityWithPosnrAndParvwAndVbeln(String vbeln,String posnr, String parvw) {
        String queryString = QueryHelper.buildCriteria(IConstant.PROJECT_ONE_VBPA.POSNR).isNull().and(IConstant.PROJECT_ONE_VBPA.PARVW).is(parvw).and(IConstant.PROJECT_ONE_VBPA.VBELN).is(vbeln).toQueryString();
        return queryForObject(IConstant.REGION.PROJECT_ONE_VBPA, queryString, VbpaEntity.class);
    }
}
