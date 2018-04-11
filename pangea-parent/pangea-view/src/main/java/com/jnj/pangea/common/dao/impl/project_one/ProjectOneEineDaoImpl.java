package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.project_one.EineEntity;

public class ProjectOneEineDaoImpl extends CommonDaoImpl {

    private static ProjectOneEineDaoImpl instance;

    public static ProjectOneEineDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOneEineDaoImpl();
        }
        return instance;
    }

    public EineEntity getEntityWithInfnr(String infnr) {

        String queryString = QueryHelper.buildCriteria(IConstant.PROJECT_ONE_EINE.INFNR).is(infnr).toQueryString();
        return queryForObject(IConstant.REGION.PROJECT_ONE_EINE, queryString, EineEntity.class);
    }
}
