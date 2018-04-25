package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.plan.PlanCnsLotSizeKeyTransEntity;

public class PlanCnsLotSizeKeyTransDaoImpl extends CommonDaoImpl {

    private static PlanCnsLotSizeKeyTransDaoImpl instance;

    public static PlanCnsLotSizeKeyTransDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsLotSizeKeyTransDaoImpl();
        }
        return instance;
    }

    public PlanCnsLotSizeKeyTransEntity getEntityWithConditions(String param) {

        //String queryString = QueryHelper.buildCriteria(IConstant.EDM_COUNTRY_V1.LOCAL_COUNTRY).is(land1).toQueryString();
        //return queryForObject(IConstant.REGION.EDM_COUNTRY_V1, localQueryString, EDMCountryEntity.class);
        return null;
    }

    public PlanCnsLotSizeKeyTransEntity getEntityWithLocalLotSizeKeyAndSourceSystem(String localLotSize, String sourceSystem) {
        String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_LOT_SIZE_KEY_TRANS.LOCAL_LOT_SIZE_KEY).is(localLotSize).and(IConstant.PLAN_CNS_LOT_SIZE_KEY_TRANS.SOURCE_SYSTEM).is(sourceSystem).toQueryString();
        return queryForObject(IConstant.REGION.PLAN_CNS_LOT_SIZE_KEY_TRANS, queryString, PlanCnsLotSizeKeyTransEntity.class);
    }
}
