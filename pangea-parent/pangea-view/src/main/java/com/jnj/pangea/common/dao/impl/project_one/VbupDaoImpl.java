package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.project_one.VbupEntity;

import java.util.List;

import java.util.List;

public class VbupDaoImpl extends CommonDaoImpl {

    private static VbupDaoImpl instance;

    public static VbupDaoImpl getInstance() {
        if (instance == null) {
            instance = new VbupDaoImpl();
        }
        return instance;
    }

    public List<VbupEntity> getEntityWithMandtAndVbelnAndPosnr(String mandt, String vbeln, String posnr) {

        String queryString = QueryHelper.buildCriteria(IConstant.PROJECT_ONE_VBUP.MANDT).is(mandt)
                .and(IConstant.PROJECT_ONE_VBUP.POSNR).is(posnr)
                .and(IConstant.PROJECT_ONE_VBUP.VBELN).is(vbeln)
                .toQueryString();
        return queryForList(IConstant.REGION.PROJECT_ONE_VBUP, queryString, VbupEntity.class);
    }
}