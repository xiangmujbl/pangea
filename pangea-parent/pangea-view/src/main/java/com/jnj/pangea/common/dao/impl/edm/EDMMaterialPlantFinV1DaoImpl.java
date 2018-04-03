package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.edm.EDMMaterialPlantFinV1Entity;

public class EDMMaterialPlantFinV1DaoImpl extends CommonDaoImpl {

    private static EDMMaterialPlantFinV1DaoImpl instance;

    public static EDMMaterialPlantFinV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMMaterialPlantFinV1DaoImpl();
        }
        return instance;
    }

    public EDMMaterialPlantFinV1Entity getEntityWithConditions(String param) {

        //String queryString = QueryHelper.buildCriteria(IConstant.EDM_COUNTRY_V1.LOCAL_COUNTRY).is(land1).toQueryString();
        //return queryForObject(IConstant.REGION.EDM_COUNTRY_V1, localQueryString, EDMCountryEntity.class);
        return null;
    }

    public EDMMaterialPlantFinV1Entity getEntityWithLocalMaterialNumberAndLocalPlant(String localMaterialNumber, String localPlant) {
        String queryString = QueryHelper.buildCriteria(IConstant.EDM_MATERIAL_PLANT_FIN_V1.LOCAL_MATERIAL_NUMBER).is(localMaterialNumber).and(IConstant.EDM_MATERIAL_PLANT_FIN_V1.LOCAL_PLANT).is(localPlant).toQueryString();
        return queryForObject(IConstant.REGION.EDM_MATERIAL_PLANT_FIN_V1, queryString, EDMMaterialPlantFinV1Entity.class);
    }
}
