package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;

public class EkbeDaoImpl extends CommonDaoImpl {
    private static EkbeDaoImpl instance;

    public static EkbeDaoImpl getInstance() {
        if (instance == null) {
            instance = new EkbeDaoImpl();
        }
        return instance;
    }

    /*public SomeEntity getEntityWith(String entityVar) {

        String queryString = QueryHelper.buildCriteria(IConstant.PROJECT_ONE_EntityName.EntityVariable).is(entityVar).toQueryString();
        return queryForObject(IConstant.REGION.PROJECT_ONE_EntityName, queryString, SomeEntity.class);
    }*/
}
