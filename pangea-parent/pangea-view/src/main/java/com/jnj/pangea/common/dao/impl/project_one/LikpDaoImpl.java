package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.ADFCriteria;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.LikpEntity;
import com.jnj.pangea.common.entity.project_one.VbapEntity;
import com.jnj.pangea.common.entity.project_one.VbpaEntity;

public class LikpDaoImpl extends CommonDaoImpl {

    private static LikpDaoImpl instance;

    public static LikpDaoImpl getInstance() {
        if (instance == null) {
            instance = new LikpDaoImpl();
        }
        return instance;
    }

    public LikpEntity getLikpEntityWithVbeln(String vbeln) {
        String queryString = QueryHelper.buildCriteria(IConstant.PROJECT_ONE_LIKP.VBELN).is(vbeln).toQueryString();
        return queryForObject(IConstant.REGION.PROJECT_ONE_LIKP, queryString, LikpEntity.class);
    }
}
