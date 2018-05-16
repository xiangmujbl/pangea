package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.project_one.ProjectOnePlasEntity;

public class ProjectOnePlasDaoImpl extends CommonDaoImpl {

    private static ProjectOnePlasDaoImpl instance;

    public static ProjectOnePlasDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOnePlasDaoImpl();
        }
        return instance;
    }

    public ProjectOnePlasEntity getEntityWithConditions(String param) {

        //String queryString = QueryHelper.buildCriteria(IConstant.EDM_COUNTRY_V1.LOCAL_COUNTRY).is(land1).toQueryString();
        //return queryForObject(IConstant.REGION.EDM_COUNTRY_V1, localQueryString, EDMCountryEntity.class);
        return null;
    }
}
