package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.AfpoEntity;

public class ProjectOneAfpoDaoImpl extends CommonDaoImpl {

    public static final String PROJECT_ONE_AFPO = "/project_one/afpo";

    public static final String AUFNR = "aufnr";

    private static ProjectOneAfpoDaoImpl instance;

    public static ProjectOneAfpoDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOneAfpoDaoImpl();
        }
        return instance;
    }

    public AfpoEntity getEntityWithAufnr(String aufnr) {

        String queryString = QueryHelper.buildCriteria(AUFNR).is(aufnr).toQueryString();
        return queryForObject(PROJECT_ONE_AFPO, queryString, AfpoEntity.class);
    }
}
