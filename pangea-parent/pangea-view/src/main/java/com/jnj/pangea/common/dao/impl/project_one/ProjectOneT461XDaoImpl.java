package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.Utils;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.T461XEntity;

public class ProjectOneT461XDaoImpl extends CommonDaoImpl {

    public static final String PROJECT_ONE_T461X = "/project_one/t461x";

    public static final String STRGR = "strgr";
    public static final String SPRAS = "spras";

    private static ProjectOneT461XDaoImpl instance;

    public static ProjectOneT461XDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOneT461XDaoImpl();
        }
        return instance;
    }

    public T461XEntity getEntityWithStrgrAndSpras(String strgr) {
        String queryString = QueryHelper.buildCriteria(STRGR).is(strgr)
                .and(SPRAS).is(Utils.EN).toQueryString();
        return queryForObject(PROJECT_ONE_T461X, queryString, T461XEntity.class);
    }
}
