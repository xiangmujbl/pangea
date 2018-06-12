package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.edm.EDMUnitOfMeasureV1Entity;

public class EDMUnitOfMeasureV1DaoImpl extends CommonDaoImpl {

    private static EDMUnitOfMeasureV1DaoImpl instance;

    public static EDMUnitOfMeasureV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMUnitOfMeasureV1DaoImpl();
        }
        return instance;
    }

    public EDMUnitOfMeasureV1Entity getEntityWithConditions(String unit) {

        String queryString = QueryHelper.buildCriteria(IConstant.EDM_UNIT_OF_MEASURE_V1.UOM).is(unit).toQueryString();
        return queryForObject(IConstant.REGION.EDM_UNIT_OF_MEASURE_V1, queryString, EDMUnitOfMeasureV1Entity.class);
    }

    public EDMUnitOfMeasureV1Entity getEntityWithUnitAndSourceSystem(String unit,String sourceSystem) {

        String queryString = QueryHelper.buildCriteria(IConstant.EDM_UNIT_OF_MEASURE_V1.UOM).is(unit)
                .and(IConstant.EDM_UNIT_OF_MEASURE_V1.SOURCESYSTEM).is(sourceSystem)
                .toQueryString();
        return queryForObject(IConstant.REGION.EDM_UNIT_OF_MEASURE_V1, queryString, EDMUnitOfMeasureV1Entity.class);
    }
}
