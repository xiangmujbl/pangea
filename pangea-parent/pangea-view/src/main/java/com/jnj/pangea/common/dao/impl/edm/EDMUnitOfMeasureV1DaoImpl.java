package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;


import com.jnj.pangea.common.entity.edm.EDMUnitOfMeasureV1Entity;

public class EDMUnitOfMeasureV1DaoImpl extends CommonDaoImpl {

    public static final String EDM_UNIT_OF_MEASURE_V1 = "/edm/unit_of_measure_v1";

    public static final String UOM = "uom";
    public static final String SOURCESYSTEM = "sourceSystem";

    private static EDMUnitOfMeasureV1DaoImpl instance;

    public static EDMUnitOfMeasureV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMUnitOfMeasureV1DaoImpl();
        }
        return instance;
    }

    public EDMUnitOfMeasureV1Entity getEntityWithConditions(String unit) {

        String queryString = QueryHelper.buildCriteria(UOM).is(unit).toQueryString();
        return queryForObject(EDM_UNIT_OF_MEASURE_V1, queryString, EDMUnitOfMeasureV1Entity.class);
    }

    public EDMUnitOfMeasureV1Entity getEntityWithUnitAndSourceSystem(String unit,String sourceSystem) {

        String queryString = QueryHelper.buildCriteria(UOM).is(unit)
                .and(SOURCESYSTEM).is(sourceSystem)
                .toQueryString();
        return queryForObject(EDM_UNIT_OF_MEASURE_V1, queryString, EDMUnitOfMeasureV1Entity.class);
    }
}
