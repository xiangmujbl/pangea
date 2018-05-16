package com.jnj.omp.common.dao.impl.edm;

import com.jnj.omp.common.dao.impl.CommonDaoImpl;
import com.jnj.omp.common.entity.edm.EDMBatchMasterV1Entity;

public class BatchMasterV1DaoImpl extends CommonDaoImpl {

    private static BatchMasterV1DaoImpl instance;

    public static BatchMasterV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new BatchMasterV1DaoImpl();
        }
        return instance;
    }

    public EDMBatchMasterV1Entity getEntityWithConditions(String param) {

        //String queryString = QueryHelper.buildCriteria(IConstant.EDM_COUNTRY_V1.LOCAL_COUNTRY).is(land1).toQueryString();
        //return queryForObject(IConstant.REGION.EDM_COUNTRY_V1, localQueryString, EDMCountryEntity.class);
        return null;
    }


}
