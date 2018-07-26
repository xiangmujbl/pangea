package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;

 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.project_one.EineEntity;

import java.util.List;

public class ProjectOneEineDaoImpl extends CommonDaoImpl {

    public static final String PROJECT_ONE_EINE = "/project_one/eine";

    public static final String INFNR = "infnr";

    private static ProjectOneEineDaoImpl instance;

    public static ProjectOneEineDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOneEineDaoImpl();
        }
        return instance;
    }

    public List<EineEntity> getEntityWithInfnr(String infnr) {

        String queryString = QueryHelper.buildCriteria(INFNR).is(infnr).toQueryString();
        return queryForList(PROJECT_ONE_EINE, queryString, EineEntity.class);
    }
}
