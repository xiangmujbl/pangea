package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;

public class EkkoDaoImpl extends CommonDaoImpl {
    private static EkkoDaoImpl instance;

    public static EkkoDaoImpl getInstance() {
        if (instance == null) {
            instance = new EkkoDaoImpl();
        }
        return instance;
    }

     /*public SomeEntity getEntityWith(String entityVar) {

        String queryString = QueryHelper.buildCriteria(EntityVariable).is(entityVar).toQueryString();
        return queryForObject(PROJECT_ONE_EntityName, queryString, SomeEntity.class);
    }*/
}
