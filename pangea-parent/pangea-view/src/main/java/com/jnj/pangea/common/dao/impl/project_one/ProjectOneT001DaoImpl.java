package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.T001Entity;

public class ProjectOneT001DaoImpl extends CommonDaoImpl {

    private static ProjectOneT001DaoImpl instance;

    public static ProjectOneT001DaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOneT001DaoImpl();
        }
        return instance;
    }


    public T001Entity getEntityWithBukrs(String bukrs) {

        String queryString = QueryHelper.buildCriteria(IConstant.PROJECT_ONE_T001.BUKRS).is(bukrs).toQueryString();
        return queryForObject(IConstant.REGION.PROJECT_ONE_T001, queryString, T001Entity.class);
    }
}
