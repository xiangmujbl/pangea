package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.edm.EDMMatPlantFiV1Entity;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;

public class EDMMatlBomDaoImpl extends CommonDaoImpl {

    private static EDMMatPlantFiV1DaoImpl instance;

    public static EDMMatPlantFiV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMMatPlantFiV1DaoImpl();
        }
        return instance;
    }


    public EDMMatPlantFiV1Entity getSourceSystemWithProjectOne() {
        String queryString = QueryHelper.buildCriteria(IConstant.EDM_SOURCE_SYSTEM_V1.LOCAL_SOURCE_SYSTEM).is(IConstant.VALUE.PROJECT_ONE).toQueryString();
        return queryForObject(IConstant.REGION.EDM_SOURCE_SYSTEM_V1, queryString, EDMSourceSystemV1Entity.class);
    }
}
