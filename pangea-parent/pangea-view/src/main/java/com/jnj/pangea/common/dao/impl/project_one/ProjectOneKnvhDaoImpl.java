package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.KnvhEntity;

import java.util.Date;

public class ProjectOneKnvhDaoImpl extends CommonDaoImpl {

    private static ProjectOneKnvhDaoImpl instance;

    public static ProjectOneKnvhDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOneKnvhDaoImpl();
        }
        return instance;
    }

    public KnvhEntity getEntityWithCurrentDate() {

        String queryString = QueryHelper.buildCriteria(IConstant.EDM_COUNTRY_V1.LOCAL_COUNTRY).greaterThanEqual(new Date()).toQueryString();
        return queryForObject(IConstant.REGION.EDM_COUNTRY_V1, queryString, KnvhEntity.class);
    }
}
