package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.project_one.MchaEntity;

public class ProjectOneMchaDaoImpl extends CommonDaoImpl {

    private static ProjectOneMchaDaoImpl instance;

    public static ProjectOneMchaDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOneMchaDaoImpl();
        }
        return instance;
    }

    public MchaEntity getEntityWithMatnrAndCharg(String matnr, String charg) {

        String queryString = QueryHelper.buildCriteria(IConstant.PROJECT_ONE_MCHA.MATNR).is(matnr)
                .and(IConstant.PROJECT_ONE_MCHA.CHARG).is(charg).toQueryString();
        return queryForObject(IConstant.REGION.PROJECT_ONE_MCHA, queryString, MchaEntity.class);
    }
}
