package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.TmabctEntity;

public class ProjectOneTmabctDaoImpl extends CommonDaoImpl {

    private static  ProjectOneTmabctDaoImpl instance;

    public static ProjectOneTmabctDaoImpl getInstance(){
        if (instance == null) {
            instance = new ProjectOneTmabctDaoImpl();
        }
        return instance;
    }

    public TmabctEntity getEntityWithMaabc(String maabc){
        String name1QueryString = QueryHelper.buildCriteria(IConstant.PROJECT_ONE_TMABCT.MAABC).is(maabc)
                .and(IConstant.PROJECT_ONE_TMABCT.SPARS).is(IConstant.VALUE.EN).toQueryString();
        return queryForObject(IConstant.REGION.PROJECT_ONE_TMABCT, name1QueryString, TmabctEntity.class);
    }
}
