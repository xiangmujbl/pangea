package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.LipsEntity;

import java.util.List;

public class LipsDaoImpl extends CommonDaoImpl {

    private static LipsDaoImpl instance;

    public static LipsDaoImpl getInstance() {
        if (instance == null) {
            instance = new LipsDaoImpl();
        }
        return instance;
    }

    public LipsEntity getLipsEntityWithVbeln(String likpVbeln) {
        if(likpVbeln != null && !(likpVbeln.isEmpty())) {
            String queryString = QueryHelper.buildCriteria(IConstant.PROJECT_ONE_LIPS.VBELN).is(likpVbeln).toQueryString();
            return (queryForObject(IConstant.REGION.PROJECT_ONE_LIPS, queryString, LipsEntity.class));
        }
        return null;
    }

    public List<LipsEntity> getLipsEntitiesWithVbeln(String likpVbeln) {
        if(likpVbeln != null && !(likpVbeln.isEmpty())) {
            String queryString = QueryHelper.buildCriteria(IConstant.PROJECT_ONE_LIPS.VBELN).is(likpVbeln).toQueryString();
            return (queryForList(IConstant.REGION.PROJECT_ONE_LIPS, queryString, LipsEntity.class));
        }
        return null;
    }
}
