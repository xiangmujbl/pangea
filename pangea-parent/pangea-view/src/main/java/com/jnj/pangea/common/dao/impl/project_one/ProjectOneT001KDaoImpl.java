package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.T001KEntity;

public class ProjectOneT001KDaoImpl extends CommonDaoImpl {

    public static final String PROJECT_ONE_T001K = "/project_one/t001k";

    public static final String BWKEY = "bwkey";

    private static ProjectOneT001KDaoImpl instance;

    public static ProjectOneT001KDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOneT001KDaoImpl();
        }
        return instance;
    }


    public T001KEntity getEntityWithBwkey(String bwkey) {

        String QueryString = QueryHelper.buildCriteria(BWKEY).is(bwkey).toQueryString();
        return queryForObject(PROJECT_ONE_T001K, QueryString, T001KEntity.class);
    }
}
