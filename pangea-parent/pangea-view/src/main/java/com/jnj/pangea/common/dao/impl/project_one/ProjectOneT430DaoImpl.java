package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.T430Entity;

public class ProjectOneT430DaoImpl extends CommonDaoImpl {

    private static ProjectOneT430DaoImpl instance;

    public static ProjectOneT430DaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOneT430DaoImpl();
        }
        return instance;
    }

    public T430Entity getEntityWithPK(String steus,String term) {
        String queryString = QueryHelper.buildCriteria("steus").is(steus)
                .and("term").is(term).toQueryString();
//        LogUtil.getCoreLog().info("-----T430>>"+queryString);
        return queryForObject("/project_one/t430", queryString, T430Entity.class);
    }
}
