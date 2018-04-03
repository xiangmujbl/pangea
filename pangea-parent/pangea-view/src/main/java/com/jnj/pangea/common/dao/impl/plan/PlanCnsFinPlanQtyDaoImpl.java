package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.plan.PlanCnsFinPlanQtyEntity;

public class PlanCnsFinPlanQtyDaoImpl extends CommonDaoImpl {

    private static PlanCnsFinPlanQtyDaoImpl instance;

    public static PlanCnsFinPlanQtyDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsFinPlanQtyDaoImpl();
        }
        return instance;
    }

    public PlanCnsFinPlanQtyEntity getEntityWithConditions(String param) {

        //String queryString = QueryHelper.buildCriteria(IConstant.EDM_COUNTRY_V1.LOCAL_COUNTRY).is(land1).toQueryString();
        //return queryForObject(IConstant.REGION.EDM_COUNTRY_V1, localQueryString, EDMCountryEntity.class);
        return null;
    }

    public PlanCnsFinPlanQtyEntity getEntityWithLocalMaterialNumber(String localMaterialNumber) {
        String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_FIN_PLAN_QTY.LOCAL_MATERIAL_NUMBER).is(localMaterialNumber).toQueryString();
        return queryForObject(IConstant.REGION.PLAN_CNS_FIN_PLAN_QTY, queryString, PlanCnsFinPlanQtyEntity.class);
    }
}
