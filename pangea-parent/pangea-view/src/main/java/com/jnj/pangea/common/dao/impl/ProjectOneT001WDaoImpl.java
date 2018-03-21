package com.jnj.pangea.common.dao.impl;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.projectone.T001WEntity;

public class ProjectOneT001WDaoImpl extends CommonDaoImpl {

    private static ProjectOneT001WDaoImpl instance;

    public static ProjectOneT001WDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOneT001WDaoImpl();
        }
        return instance;
    }


    public T001WEntity getEntityWithZPlant(String zPlant) {

        String name1QueryString = QueryHelper.buildCriteria(IConstant.PROJECT_ONE_T001W.WERKS).is(zPlant).toQueryString();
        return queryForObject(IConstant.REGION.PROJECT_ONE_T001W, name1QueryString, T001WEntity.class);
    }
}
