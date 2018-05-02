package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.AfpoEntity;

public class ProjectOneAfpoDaoImpl extends CommonDaoImpl {

    private static ProjectOneAfpoDaoImpl instance;

    public static ProjectOneAfpoDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOneAfpoDaoImpl();
        }
        return instance;
    }

    public AfpoEntity getEntityWithAufnr(String aufnr) {

        String queryString = QueryHelper.buildCriteria(IConstant.PROJECT_ONE_AFPO.AUFNR).is(aufnr).toQueryString();
        return queryForObject(IConstant.REGION.PROJECT_ONE_AFPO, queryString, AfpoEntity.class);
    }
}
