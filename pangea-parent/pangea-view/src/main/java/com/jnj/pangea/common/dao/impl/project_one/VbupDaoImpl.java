package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.project_one.VbupEntity;

import java.util.List;

import java.util.List;

public class VbupDaoImpl extends CommonDaoImpl {

    public static final String PROJECT_ONE_VBUP = "/project_one/vbup";

    public static final String VBELN = "vbeln";
    public static final String POSNR = "posnr";
    public static final String MANDT = "mandt";

    private static VbupDaoImpl instance;

    public static VbupDaoImpl getInstance() {
        if (instance == null) {
            instance = new VbupDaoImpl();
        }
        return instance;
    }

    public VbupEntity getEntityWithVbelnAndPosnrAndMandt(String vbeln, String posnr, String mandt) {
        String queryString = QueryHelper.buildCriteria(VBELN).is(vbeln).and(POSNR).is(posnr).and(MANDT).is(mandt).toQueryString();
        return queryForObject(PROJECT_ONE_VBUP, queryString, VbupEntity.class);
    }

    public List<VbupEntity> getEntityWithMandtAndVbelnAndPosnr(String mandt, String vbeln, String posnr) {

        String queryString = QueryHelper.buildCriteria(MANDT).is(mandt)
                .and(POSNR).is(posnr)
                .and(VBELN).is(vbeln)
                .toQueryString();
        return queryForList(PROJECT_ONE_VBUP, queryString, VbupEntity.class);
    }
}