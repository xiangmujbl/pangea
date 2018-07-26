package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;

 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.TvroEntity;

public class ProjectOneTvroDaoImpl extends CommonDaoImpl {

    public static final String PROJECT_ONE_TVRO = "/project_one/tvro";

    public static final String ROUTE = "route";

    private static ProjectOneTvroDaoImpl instance;

    public static ProjectOneTvroDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOneTvroDaoImpl();
        }
        return instance;
    }


    public TvroEntity getEntityWithRoute(String route) {
        if (null != route && !"".equals(route)){
            String queryString = QueryHelper.buildCriteria(ROUTE).is(route).toQueryString();
            return queryForObject(PROJECT_ONE_TVRO, queryString, TvroEntity.class);
        }
        return null;
    }
}
