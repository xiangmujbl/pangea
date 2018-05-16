package com.jnj.omp.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.omp.common.IConstant;
import com.jnj.omp.common.dao.impl.CommonDaoImpl;
import com.jnj.omp.common.entity.project_one.T001KEntity;

public class ProjectOneT001KDaoImpl extends CommonDaoImpl {

    private static ProjectOneT001KDaoImpl instance;

    public static ProjectOneT001KDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOneT001KDaoImpl();
        }
        return instance;
    }


    public T001KEntity getEntityWithBwkey(String bwkey) {

        String QueryString = QueryHelper.buildCriteria(IConstant.PROJECT_ONE_T001K.BWKEY).is(bwkey).toQueryString();
        return queryForObject(IConstant.REGION.PROJECT_ONE_T001K, QueryString, T001KEntity.class);
    }
}
