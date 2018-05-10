package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;

public class EkpvDaoImpl extends CommonDaoImpl {
    private static EkpvDaoImpl instance;

    public static EkpvDaoImpl getInstance() {
        if (instance == null) {
            instance = new EkpvDaoImpl();
        }
        return instance;
    }

     /*public SomeEntity getEntityWith(String entityVar) {

        String queryString = QueryHelper.buildCriteria(IConstant.PROJECT_ONE_EntityName.EntityVariable).is(entityVar).toQueryString();
        return queryForObject(IConstant.REGION.PROJECT_ONE_EntityName, queryString, SomeEntity.class);
    }*/
}
