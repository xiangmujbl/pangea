package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.T439AEntity;

public class ProjectOneT439ADaoImpl extends CommonDaoImpl {

    private static ProjectOneT439ADaoImpl instance;

    public static ProjectOneT439ADaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOneT439ADaoImpl();
        }
        return instance;
    }

    public T439AEntity getEntityWithDisls(String disls) {

        String queryString = QueryHelper.buildCriteria(IConstant.PROJECT_ONE_T439A.DISLS).is(disls).toQueryString();
        return queryForObject(IConstant.REGION.PROJECT_ONE_T439A, queryString, T439AEntity.class);
    }
}
