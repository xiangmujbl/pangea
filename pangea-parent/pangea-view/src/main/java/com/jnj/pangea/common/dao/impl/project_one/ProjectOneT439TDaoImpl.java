package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.Utils;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.T439TEntity;

public class ProjectOneT439TDaoImpl extends CommonDaoImpl {

    public static final String PROJECT_ONE_T439T = "/project_one/t439t";
    public static final String PROJECT_ONE_T439T_CLONE = "/project_one/t439t_clone";

    public static final String DISLS = "disls";
    public static final String SPRAS = "spras";

    private static ProjectOneT439TDaoImpl instance;

    public static ProjectOneT439TDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOneT439TDaoImpl();
        }
        return instance;
    }

    public T439TEntity getEntityWithDislsAndSpras(String disls, String spras) {
        String queryString = QueryHelper.buildCriteria(DISLS).is(disls)
                .and(SPRAS).is(spras).toQueryString();
        return queryForObject(PROJECT_ONE_T439T_CLONE, queryString, T439TEntity.class);
    }

    public T439TEntity getEntityWithSpras() {

        String queryString = QueryHelper.buildCriteria(SPRAS).is(Utils.SPRAS_EN).toQueryString();
        return queryForObject(PROJECT_ONE_T439T, queryString, T439TEntity.class);
    }
}
