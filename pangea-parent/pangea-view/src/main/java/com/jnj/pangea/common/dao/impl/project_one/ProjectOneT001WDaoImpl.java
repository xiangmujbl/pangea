package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;

 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.T001WEntity;

public class ProjectOneT001WDaoImpl extends CommonDaoImpl {

    public static final String PROJECT_ONE_T001W = "/project_one/t001w";

    public static final String WERKS = "werks";
    public static final String LAND1 = "land1";

    private static ProjectOneT001WDaoImpl instance;

    public static ProjectOneT001WDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOneT001WDaoImpl();
        }
        return instance;
    }


    public T001WEntity getEntityWithZPlantAndLand1(String zPlant) {

        String name1QueryString = QueryHelper.buildCriteria(WERKS).is(zPlant)
                .and(LAND1).isNotNull().toQueryString();
        return queryForObject(PROJECT_ONE_T001W, name1QueryString, T001WEntity.class);
    }
}
