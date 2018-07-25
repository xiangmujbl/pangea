package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.VbukEntity;

public class VbukDaoImpl extends CommonDaoImpl {

    public static final String PROJECT_ONE_VBUK = "/project_one/vbuk";

    public static final String GBSTK="gbstk";
    public static final String WBSTK="wbstk";
    public static final String KOSTK="kostk";
    public static final String LVSTK="lvstk";
    public static final String FKSTK="fkstk";
    public static final String MANDT="mandt";
    public static final String VBELN="vbeln";

    private static VbukDaoImpl instance;

    public static VbukDaoImpl getInstance() {
        if (instance == null) {
            instance = new VbukDaoImpl();
        }
        return instance;
    }

    public VbukEntity getVbukbyLikp(String vbeln, String mandt) {
        String queryString = QueryHelper.buildCriteria(VBELN).is(vbeln)
                .and(MANDT).is(mandt).toQueryString();
        return queryForObject(PROJECT_ONE_VBUK, queryString, VbukEntity.class);
    }

    public VbukEntity getEntityWithVbelnAndMandt(String vbeln, String mandt) {
        String queryString = QueryHelper.buildCriteria(VBELN).is(vbeln).and(MANDT).is(mandt).toQueryString();
        return queryForObject(PROJECT_ONE_VBUK, queryString, VbukEntity.class);
    }
}
