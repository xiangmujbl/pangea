package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.VbkdEntity;

public class VbkdDaoImpl extends CommonDaoImpl {

    private static VbkdDaoImpl instance;

    public static VbkdDaoImpl getInstance() {
        if (instance == null) {
            instance = new VbkdDaoImpl();
        }
        return instance;
    }

    public VbkdEntity getEntityWithConditions(String param) {

        //String queryString = QueryHelper.buildCriteria(IConstant.EDM_COUNTRY_V1.LOCAL_COUNTRY).is(land1).toQueryString();
        //return queryForObject(IConstant.REGION.EDM_COUNTRY_V1, localQueryString, EDMCountryEntity.class);
        return null;
    }

    public VbkdEntity getEntityWithPosnrAndVbelAndPosnrIsNullOrBlankOr000000(String vbeln) {
        String queryString = QueryHelper.buildCriteria(IConstant.PROJECT_ONE_VBKD.VBELN).is(vbeln).and(IConstant.PROJECT_ONE_VBKD.POSNR).isNull().or(QueryHelper.buildCriteria(IConstant.PROJECT_ONE_VBKD.POSNR).is("000000")).toQueryString();
        return queryForObject(IConstant.REGION.PROJECT_ONE_VBKD, queryString, VbkdEntity.class);
    }
}
