package com.jnj.pangea.common.dao.impl;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.projectone.TmabctEntity;

public class ProjectOneTmabctDaoImpl extends CommonDaoImpl {

    private static  ProjectOneTmabctDaoImpl instance;

    public static ProjectOneTmabctDaoImpl getInstance(){
        if (instance == null) {
            instance = new ProjectOneTmabctDaoImpl();
        }
        return instance;
    }

    public TmabctEntity getEntityWithMaabc(String maabc){
        String name1QueryString = QueryHelper.buildCriteria(IConstant.project_one_tmabct.MAABC).is(maabc)
                .and(IConstant.project_one_tmabct.SPARS).is(IConstant.VALUE.EN).toQueryString();
        return queryForObject(IConstant.REGION.PROJECT_ONE_TMABCT, name1QueryString, TmabctEntity.class);
    }
}
