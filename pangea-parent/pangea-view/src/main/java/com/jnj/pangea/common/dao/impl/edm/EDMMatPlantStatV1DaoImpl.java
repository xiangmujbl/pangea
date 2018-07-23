package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.query.QueryHelper;


import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMatPlantStatV1Entity;

public class EDMMatPlantStatV1DaoImpl extends CommonDaoImpl {

    public static final String EDM_MAT_PLANT_STAT_V1 = "/edm/mat_plant_stat_v1";

    public static final String LOCAL_PLANT_STATUS = "localPlantStatus";
    public static final String SOURCE_SYSTEM = "sourceSystem";
    
    private static EDMMatPlantStatV1DaoImpl instance;

    public static EDMMatPlantStatV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMMatPlantStatV1DaoImpl();
        }
        return instance;
    }

    public EDMMatPlantStatV1Entity getPlantStatusWithLocalPlantStatusAndSourceSystem(String sourceSystem, String mmsta) {
        String queryString = QueryHelper.buildCriteria(SOURCE_SYSTEM).is(sourceSystem).and(LOCAL_PLANT_STATUS).is(mmsta).toQueryString();
        return queryForObject(EDM_MAT_PLANT_STAT_V1, queryString, EDMMatPlantStatV1Entity.class);
    }

}
