package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.project_one.VbepEntity;

public class VbepDaoImpl extends CommonDaoImpl {

    private static VbepDaoImpl instance;

    public static VbepDaoImpl getInstance() {
        if (instance == null) {
            instance = new VbepDaoImpl();
        }
        return instance;
    }

    public VbepEntity getEntityWithConditions(String param) {

        //String queryString = QueryHelper.buildCriteria(IConstant.EDM_COUNTRY_V1.LOCAL_COUNTRY).is(land1).toQueryString();
        //return queryForObject(IConstant.REGION.EDM_COUNTRY_V1, localQueryString, EDMCountryEntity.class);
        return null;
    }

    public VbepEntity getEntityWithVbelnAndPosnr(String vbeln, String posnr) {
        String queryString = QueryHelper.buildCriteria(IConstant.PROJECT_ONE_VBEP.VBELN).is(vbeln).and(IConstant.PROJECT_ONE_VBEP.POSNR).isNull().toQueryString();
        return queryForObject(IConstant.REGION.PROJECT_ONE_VBEP, queryString, VbepEntity.class);
    }
}
