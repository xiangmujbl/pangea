package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.VbukEntity;

public class VbukDaoImpl extends CommonDaoImpl {

    private static VbukDaoImpl instance;

    public static VbukDaoImpl getInstance() {
        if (instance == null) {
            instance = new VbukDaoImpl();
        }
        return instance;
    }

    public VbukEntity getEntityWithVbelnAndMandt(String vbeln, String mandt) {
        String queryString = QueryHelper.buildCriteria(IConstant.PROJECT_ONE_VBUK.VBELN).is(vbeln).and(IConstant.PROJECT_ONE_VBUK.MANDT).is(mandt).toQueryString();
        return queryForObject(IConstant.REGION.PROJECT_ONE_VBUK, queryString, VbukEntity.class);
    }
}
