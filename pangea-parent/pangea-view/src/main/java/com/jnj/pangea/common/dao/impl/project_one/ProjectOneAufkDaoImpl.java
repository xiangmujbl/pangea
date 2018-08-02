package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;

 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.AufkEntity;

public class ProjectOneAufkDaoImpl extends CommonDaoImpl {

    public static final String AUFNR = "aufnr";

    public static final String PROJECT_ONE_AUFK = "/project_one/aufk";

    private static ProjectOneAufkDaoImpl instance;

    public static ProjectOneAufkDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOneAufkDaoImpl();
        }
        return instance;
    }

    public AufkEntity getEntityWithAufnr(String aufnr) {

        String queryString = QueryHelper.buildCriteria(AUFNR).is(aufnr).toQueryString();
        return queryForObject(PROJECT_ONE_AUFK, queryString, AufkEntity.class);
    }
}
