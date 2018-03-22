package com.jnj.pangea.common.dao.impl;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.projectone.T461XEntity;

public class ProjectOneT461XDaoImpl extends CommonDaoImpl{

    private static ProjectOneT461XDaoImpl instance;

    public static ProjectOneT461XDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOneT461XDaoImpl();
        }
        return instance;
    }

    public T461XEntity getEntityWithStrgrAndSpras(String strgr) {
        String queryString = QueryHelper.buildCriteria(IConstant.PROJECT_ONE_T461X.STRGR).is(strgr)
                .and(IConstant.PROJECT_ONE_T461X.SPRAS).is(IConstant.VALUE.EN).toQueryString();
        return queryForObject(IConstant.REGION.PROJECT_ONE_T461X, queryString, T461XEntity.class);
    }
}
