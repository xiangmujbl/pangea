package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.project_one.VbapEntity;

import java.util.List;

public class VbapDaoImpl extends CommonDaoImpl {

    public static final String PROJECT_ONE_VBAP = "/project_one/vbap";

    public static final String VBELN = "vbeln";

    private static VbapDaoImpl instance;

    public static VbapDaoImpl getInstance() {
        if (instance == null) {
            instance = new VbapDaoImpl();
        }
        return instance;
    }

    public List<VbapEntity> getEntityWithVbeln(String vbeln){
        String queryString = QueryHelper.buildCriteria(VBELN).is(vbeln).toQueryString();
        return queryForList(PROJECT_ONE_VBAP, queryString, VbapEntity.class);
    }
}
