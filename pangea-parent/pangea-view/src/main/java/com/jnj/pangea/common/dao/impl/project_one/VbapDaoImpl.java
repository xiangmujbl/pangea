package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.project_one.VbapEntity;

import java.util.List;

public class VbapDaoImpl extends CommonDaoImpl {

    private static VbapDaoImpl instance;

    public static VbapDaoImpl getInstance() {
        if (instance == null) {
            instance = new VbapDaoImpl();
        }
        return instance;
    }

    public VbapEntity getEntityWithConditions(String param) {

        //String queryString = QueryHelper.buildCriteria(IConstant.EDM_COUNTRY_V1.LOCAL_COUNTRY).is(land1).toQueryString();
        //return queryForObject(IConstant.REGION.EDM_COUNTRY_V1, localQueryString, EDMCountryEntity.class);
        return null;
    }
    public List<VbapEntity> getEntityWithVbeln(String vbeln){
        String queryString = QueryHelper.buildCriteria(IConstant.PROJECT_ONE_VBAP.VBELN).is(vbeln).toQueryString();
        return queryForObject(IConstant.REGION.PROJECT_ONE_VBAP, queryString, VbapEntity.class);
    }
}
