package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.PlafEntity;

public class ProjectOnePlafDaoImpl extends CommonDaoImpl {
    private static ProjectOnePlafDaoImpl instance;
    public static ProjectOnePlafDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOnePlafDaoImpl();
        }
        return instance;
    }

    public PlafEntity getEntityWithLocalPlant(String localPlant){
        String queryString = QueryHelper.buildCriteria(IConstant.PROJECT_ONE_PLAF.PLWRK).is(localPlant)
                .and(IConstant.PROJECT_ONE_PLAF.PLSCN).is(IConstant.VALUE.PLSCN).toQueryString();
        return queryForObject(IConstant.REGION.PROJECT_ONE_PLAF_CLONE, queryString, PlafEntity.class);
    }

    public PlafEntity getEntityWithPlscn(){
        String queryString = QueryHelper.buildCriteria(IConstant.PROJECT_ONE_PLAF.PLSCN).is(IConstant.VALUE.PLSCN).toQueryString();
        return queryForObject(IConstant.REGION.PROJECT_ONE_PLAF_CLONE, queryString, PlafEntity.class);
    }
}
