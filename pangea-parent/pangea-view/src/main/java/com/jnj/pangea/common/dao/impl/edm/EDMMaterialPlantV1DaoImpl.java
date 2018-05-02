package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMaterialPlantV1Entity;

public class EDMMaterialPlantV1DaoImpl extends CommonDaoImpl {
    private static EDMMaterialPlantV1DaoImpl instance;

    public static EDMMaterialPlantV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMMaterialPlantV1DaoImpl();
        }
        return instance;
    }

    public EDMMaterialPlantV1Entity getEntityWithMaterialNumberPlantNumberSourceSystem(String materialNumber, String localPlantNumber, String sourceSystem) {
        String queryString = QueryHelper.buildCriteria(IConstant.EDM_MATERIAL_PLANT_V1.SOURCE_SYSTEM).is(sourceSystem)
                .and(IConstant.EDM_MATERIAL_PLANT_V1.LOCAL_PLANT).is(localPlantNumber)
                .and(IConstant.EDM_MATERIAL_PLANT_V1.LOCAL_MATERIAL_NUMBER).is(materialNumber).toQueryString();
        return queryForObject(IConstant.REGION.EDM_MATERIAL_PLANT_V1,queryString,EDMMaterialPlantV1Entity.class);
    }
    public EDMMaterialPlantV1Entity getPlantWithSourceSystemAndLocalPlantAndLocalMaterialNumber(String sourceSystem, String localPlant, String localMaterialNumber) {

        String queryString = QueryHelper.buildCriteria(IConstant.EDM_MATERIAL_PLANT_V1.SOURCE_SYSTEM).is(sourceSystem)
                .and(IConstant.EDM_MATERIAL_PLANT_V1.LOCAL_PLANT).is(localPlant)
                .and(IConstant.EDM_MATERIAL_PLANT_V1.LOCAL_MATERIAL_NUMBER).is(localMaterialNumber).toQueryString();
        return queryForObject(IConstant.REGION.EDM_MATERIAL_PLANT_V1,queryString,EDMMaterialPlantV1Entity.class);
    }

}
