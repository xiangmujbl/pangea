package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;


import com.jnj.pangea.common.entity.edm.EDMMatPlantFiV1Entity;

public class EDMMatPlantFiV1DaoImpl extends CommonDaoImpl {

    public static final String EDM_MAT_PLANT_FI_V1 = "/edm/mat_plant_fi_v1";

    public static final String LOCAL_MATERIAL_NUMBER = "localMaterialNumber";
    public static final String LOCAL_PLANT = "localPlant";

    private static EDMMatPlantFiV1DaoImpl instance;

    public static EDMMatPlantFiV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMMatPlantFiV1DaoImpl();
        }
        return instance;
    }


    public EDMMatPlantFiV1Entity getEntityWithLocalMaterialNumberAndLocalPlant(String localMaterialNumber, String localPlant) {
        String queryString = QueryHelper.buildCriteria(LOCAL_MATERIAL_NUMBER).is(localMaterialNumber).and(LOCAL_PLANT).is(localPlant).toQueryString();
        return queryForObject(EDM_MAT_PLANT_FI_V1, queryString, EDMMatPlantFiV1Entity.class);
    }
}
