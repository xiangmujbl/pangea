package com.jnj.pangea.common.dao.impl;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.edm.EDMPlantV1Entity;

public class EDMPlantV1DaoImpl extends CommonDaoImpl{
    private static EDMPlantV1DaoImpl instance;

    public static EDMPlantV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMPlantV1DaoImpl();
        }
        return instance;
    }

    public String getPlantWithSourceSystemAndLocalPlant(String sourceSystem,String bwkey) {

        String queryString = QueryHelper.buildCriteria(IConstant.SOURCESYSTEM).is(sourceSystem).and(IConstant.LOCALPLANT).is(bwkey).toQueryString();
        EDMPlantV1Entity plantV1Enntity = queryForObject(IConstant.REGION.EDM_PLANT_V1,queryString,EDMPlantV1Entity.class);

        if (null != plantV1Enntity){
            return plantV1Enntity.getPlant();
        }
        return "";
    }

}
