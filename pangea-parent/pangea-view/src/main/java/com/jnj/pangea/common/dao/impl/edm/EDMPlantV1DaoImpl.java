package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMPlantV1Entity;
import org.apache.commons.lang3.StringUtils;

public class EDMPlantV1DaoImpl extends CommonDaoImpl {
    private static EDMPlantV1DaoImpl instance;

    public static EDMPlantV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMPlantV1DaoImpl();
        }
        return instance;
    }

    public EDMPlantV1Entity getPlantWithSourceSystemAndLocalPlant(String sourceSystem,String bwkey) {

        String queryString = QueryHelper.buildCriteria(IConstant.EDM_PLANT_V1.SOURCE_SYSTEM).is(sourceSystem).and(IConstant.EDM_PLANT_V1.LOCAL_PLANT).is(bwkey).toQueryString();
        return queryForObject(IConstant.REGION.EDM_PLANT_V1,queryString,EDMPlantV1Entity.class);
    }

    public EDMPlantV1Entity getEntityWithLocalPlant(String localPlant) {
        String queryString = QueryHelper.buildCriteria(IConstant.EDM_PLANT_V1.LOCAL_PLANT).is(localPlant).toQueryString();
        return queryForObject(IConstant.REGION.EDM_PLANT_V1,queryString,EDMPlantV1Entity.class);
    }
    public EDMPlantV1Entity getPlantWithLocalPlantAndCountry(String localPlant,String country) {
        if (StringUtils.isNotEmpty(localPlant) && StringUtils.isNotEmpty(country)){
            String queryString = QueryHelper.buildCriteria(IConstant.EDM_PLANT_V1.LOCAL_PLANT).is(localPlant).and(IConstant.EDM_PLANT_V1.COUNTRY).is(country).toQueryString();
            return queryForObject(IConstant.REGION.EDM_PLANT_V1,queryString,EDMPlantV1Entity.class);
        }
        return null;
    }
}
