package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.T001WEntity;

public class ProjectOneT001WDaoImpl extends CommonDaoImpl {

    private static ProjectOneT001WDaoImpl instance;

    public static ProjectOneT001WDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOneT001WDaoImpl();
        }
        return instance;
    }


    public T001WEntity getEntityWithZPlantAndLand1(String zPlant) {

        String name1QueryString = QueryHelper.buildCriteria(IConstant.PROJECT_ONE_T001W.WERKS).is(zPlant)
                .and(IConstant.PROJECT_ONE_T001W.LAND1).isNotNull().toQueryString();
        return queryForObject(IConstant.REGION.PROJECT_ONE_T001W, name1QueryString, T001WEntity.class);
    }
}
