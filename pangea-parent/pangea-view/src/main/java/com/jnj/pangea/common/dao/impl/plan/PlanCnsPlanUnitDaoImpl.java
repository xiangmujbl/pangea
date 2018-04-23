package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanUnitEntity;

public class PlanCnsPlanUnitDaoImpl extends CommonDaoImpl {

    private static PlanCnsPlanUnitDaoImpl instance;

    public static PlanCnsPlanUnitDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsPlanUnitDaoImpl();
        }
        return instance;
    }

    public PlanCnsPlanUnitEntity getEntityWithConditions(String param) {

        //String queryString = QueryHelper.buildCriteria(IConstant.EDM_COUNTRY_V1.LOCAL_COUNTRY).is(land1).toQueryString();
        //return queryForObject(IConstant.REGION.EDM_COUNTRY_V1, localQueryString, EDMCountryEntity.class);
        return null;
    }

    public PlanCnsPlanUnitEntity getCnsPlanUnitEntityWithLocalUom(String localUom) {
        String queryString = QueryHelper.buildCriteria(IConstant.CNS_PLAN_UNIT.LOCAL_UOM).is(localUom).toQueryString();
        return queryForObject(IConstant.REGION.CNS_PLAN_UNIT,queryString,PlanCnsPlanUnitEntity.class);
    }
}
