package com.jnj.omp.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.omp.common.IConstant;
import com.jnj.omp.common.dao.impl.CommonDaoImpl;
import com.jnj.omp.common.entity.project_one.TvroEntity;

public class ProjectOneTvroDaoImpl extends CommonDaoImpl {

    private static ProjectOneTvroDaoImpl instance;

    public static ProjectOneTvroDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOneTvroDaoImpl();
        }
        return instance;
    }

    public TvroEntity getEntityWithConditions(String param) {

        //String queryString = QueryHelper.buildCriteria(IConstant.EDM_COUNTRY_V1.LOCAL_COUNTRY).is(land1).toQueryString();
        //return queryForObject(IConstant.REGION.EDM_COUNTRY_V1, localQueryString, EDMCountryEntity.class);
        return null;
    }

    public TvroEntity getEntityWithRoute(String route) {
        if (null != route && !"".equals(route)){
            String queryString = QueryHelper.buildCriteria(IConstant.PROJECT_ONE_TVRO.ROUTE).is(route).toQueryString();
            return queryForObject(IConstant.REGION.PROJECT_ONE_TVRO, queryString, TvroEntity.class);
        }
        return null;
    }
}
