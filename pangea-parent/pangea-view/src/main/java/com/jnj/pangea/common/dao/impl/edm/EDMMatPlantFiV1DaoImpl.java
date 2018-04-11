package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.edm.EDMMatPlantFiV1Entity;

public class EDMMatPlantFiV1DaoImpl extends CommonDaoImpl {

    private static EDMMatPlantFiV1DaoImpl instance;

    public static EDMMatPlantFiV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMMatPlantFiV1DaoImpl();
        }
        return instance;
    }


    public EDMMatPlantFiV1Entity getEntityWithLocalMaterialNumberAndLocalPlant(String localMaterialNumber, String localPlant) {
        String queryString = QueryHelper.buildCriteria(IConstant.EDM_MAT_PLANT_FI_V1.LOCAL_MATERIAL_NUMBER).is(localMaterialNumber).and(IConstant.EDM_MAT_PLANT_FI_V1.LOCAL_PLANT).is(localPlant).toQueryString();
        return queryForObject(IConstant.REGION.EDM_MAT_PLANT_FI_V1, queryString, EDMMatPlantFiV1Entity.class);
    }
}
