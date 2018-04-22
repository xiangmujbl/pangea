package com.jnj.pangea.common.dao.impl.projectOne;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.projectOne.ProjectOneTvroEntity;

public class ProjectOneTvroDaoImpl extends CommonDaoImpl {

    private static ProjectOneTvroDaoImpl instance;

    public static ProjectOneTvroDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOneTvroDaoImpl();
        }
        return instance;
    }

    public ProjectOneTvroEntity getEntityWithConditions(String param) {

        //String queryString = QueryHelper.buildCriteria(IConstant.EDM_COUNTRY_V1.LOCAL_COUNTRY).is(land1).toQueryString();
        //return queryForObject(IConstant.REGION.EDM_COUNTRY_V1, localQueryString, EDMCountryEntity.class);
        return null;
    }

    public ProjectOneTvroEntity getEntityWithRoute(String route) {
        if (null != route && !"".equals(route)){
            String queryString = QueryHelper.buildCriteria(IConstant.PROJECT_ONE_TVRO.ROUTE).is(route).toQueryString();
            return queryForObject(IConstant.REGION.PROJECT_ONE_TVRO, queryString, ProjectOneTvroEntity.class);
        }
        return null;
    }
}
