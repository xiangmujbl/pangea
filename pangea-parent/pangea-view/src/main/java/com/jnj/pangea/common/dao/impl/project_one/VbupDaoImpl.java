package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.VbupEntity;

public class VbupDaoImpl extends CommonDaoImpl {

    private static VbupDaoImpl instance;

    public static VbupDaoImpl getInstance() {
        if (instance == null) {
            instance = new VbupDaoImpl();
        }
        return instance;
    }

    public VbupEntity getEntityWithVbelnAndPosnrAndMandt(String vbeln, String posnr, String mandt) {
        String queryString = QueryHelper.buildCriteria(IConstant.PROJECT_ONE_VBUP.VBELN).is(vbeln).and(IConstant.PROJECT_ONE_VBUP.POSNR).is(posnr).and(IConstant.PROJECT_ONE_VBUP.MANDT).is(mandt).toQueryString();
        return queryForObject(IConstant.REGION.PROJECT_ONE_VBUP, queryString, VbupEntity.class);
    }
}
