package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMatPlantStatV1Entity;

public class EDMMatPlantStatV1DaoImpl extends CommonDaoImpl {
    private static EDMMatPlantStatV1DaoImpl instance;

    public static EDMMatPlantStatV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMMatPlantStatV1DaoImpl();
        }
        return instance;
    }

    public EDMMatPlantStatV1Entity getPlantStatusWithLocalPlantStatusAndSourceSystem(String sourceSystem, String mmsta) {
        String queryString = QueryHelper.buildCriteria(IConstant.EDM_MAT_PLANT_STAT.SOURCE_SYSTEM).is(sourceSystem).and(IConstant.EDM_MAT_PLANT_STAT.LOCAL_PLANT_STATUS).is(mmsta).toQueryString();
        return queryForObject(IConstant.REGION.EDM_MAT_PLANT_STAT_V1, queryString, EDMMatPlantStatV1Entity.class);
    }

}
