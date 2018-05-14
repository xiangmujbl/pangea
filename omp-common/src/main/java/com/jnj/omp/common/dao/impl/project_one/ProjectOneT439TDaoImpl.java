package com.jnj.omp.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.omp.common.IConstant;
import com.jnj.omp.common.dao.impl.CommonDaoImpl;
import com.jnj.omp.common.entity.project_one.T439TEntity;

public class ProjectOneT439TDaoImpl extends CommonDaoImpl {

    private static ProjectOneT439TDaoImpl instance;

    public static ProjectOneT439TDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOneT439TDaoImpl();
        }
        return instance;
    }

    public T439TEntity getEntityWithDislsAndSpras(String disls) {
        String queryString = QueryHelper.buildCriteria(IConstant.PROJECT_ONE_T439T.DISLS).is(disls)
                .and(IConstant.PROJECT_ONE_T439T.SPRAS).is(IConstant.VALUE.EN).toQueryString();
        return queryForObject(IConstant.REGION.PROJECT_ONE_T439T, queryString, T439TEntity.class);
    }
}
