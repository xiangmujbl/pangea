package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.T001Entity;

public class ProjectOneT001DaoImpl extends CommonDaoImpl {

    public static final String PROJECT_ONE_T001 = "/project_one/t001";

    public static final String BUKRS = "bukrs";

    private static ProjectOneT001DaoImpl instance;

    public static ProjectOneT001DaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOneT001DaoImpl();
        }
        return instance;
    }


    public T001Entity getEntityWithBukrs(String bukrs) {

        String queryString = QueryHelper.buildCriteria(BUKRS).is(bukrs).toQueryString();
        return queryForObject(PROJECT_ONE_T001, queryString, T001Entity.class);
    }
}
