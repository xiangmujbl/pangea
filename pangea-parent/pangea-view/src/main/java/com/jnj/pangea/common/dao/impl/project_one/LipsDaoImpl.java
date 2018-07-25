package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.LipsEntity;

import java.util.List;

public class LipsDaoImpl extends CommonDaoImpl {

    public static final String PROJECT_ONE_LIPS = "/project_one/lips";

    public static final String VBELN = "vbeln";

    private static LipsDaoImpl instance;

    public static LipsDaoImpl getInstance() {
        if (instance == null) {
            instance = new LipsDaoImpl();
        }
        return instance;
    }

    public LipsEntity getLipsEntityWithVbeln(String likpVbeln) {
        if(likpVbeln != null && !(likpVbeln.isEmpty())) {
            String queryString = QueryHelper.buildCriteria(VBELN).is(likpVbeln).toQueryString();
            return (queryForObject(PROJECT_ONE_LIPS, queryString, LipsEntity.class));
        }
        return null;
    }

    public List<LipsEntity> getLipsEntitiesWithVbeln(String likpVbeln) {
        if(likpVbeln != null && !(likpVbeln.isEmpty())) {
            String queryString = QueryHelper.buildCriteria(VBELN).is(likpVbeln).toQueryString();
            return (queryForList(PROJECT_ONE_LIPS, queryString, LipsEntity.class));
        }
        return null;
    }
}
