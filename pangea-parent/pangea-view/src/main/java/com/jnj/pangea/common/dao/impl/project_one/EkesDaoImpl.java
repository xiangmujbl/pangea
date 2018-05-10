package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;

public class EkesDaoImpl extends CommonDaoImpl {
    private static EkesDaoImpl instance;

    public static EkesDaoImpl getInstance() {
        if (instance == null) {
            instance = new EkesDaoImpl();
        }
        return instance;
    }

     /*public SomeEntity getEntityWith(String entityVar) {

        String queryString = QueryHelper.buildCriteria(IConstant.PROJECT_ONE_EntityName.EntityVariable).is(entityVar).toQueryString();
        return queryForObject(IConstant.REGION.PROJECT_ONE_EntityName, queryString, SomeEntity.class);
    }*/
}
