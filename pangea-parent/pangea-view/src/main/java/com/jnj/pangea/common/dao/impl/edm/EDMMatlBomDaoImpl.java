package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;


import com.jnj.pangea.common.entity.edm.EDMMatPlantFiV1Entity;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;

public class EDMMatlBomDaoImpl extends CommonDaoImpl {

    public static final String EDM_SOURCE_SYSTEM_V1 = "/edm/source_system_v1";

    public static final String LOCAL_SOURCE_SYSTEM = "localSourceSystem";
    public static final String SOURCE_SYSTEM = "sourceSystem";
    public static final String SOURCE_SYSTEM_TYPE = "sourceSystemType";

    private static final String PROJECT_ONE = "project_one";

    private static EDMMatPlantFiV1DaoImpl instance;

    public static EDMMatPlantFiV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMMatPlantFiV1DaoImpl();
        }
        return instance;
    }


    public EDMMatPlantFiV1Entity getSourceSystemWithProjectOne() {
        String queryString = QueryHelper.buildCriteria(LOCAL_SOURCE_SYSTEM).is(PROJECT_ONE).toQueryString();
        return queryForObject(EDM_SOURCE_SYSTEM_V1, queryString, EDMSourceSystemV1Entity.class);
    }
}
