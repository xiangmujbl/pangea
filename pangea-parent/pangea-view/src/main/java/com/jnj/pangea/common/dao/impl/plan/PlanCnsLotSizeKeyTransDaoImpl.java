package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.plan.PlanCnsLotSizeKeyTransEntity;

public class PlanCnsLotSizeKeyTransDaoImpl extends CommonDaoImpl {

    public static final String PLAN_CNS_LOT_SIZE_KEY_TRANS = "/plan/cns_lot_size_key_trans";

    public static final String SOURCE_SYSTEM = "sourceSystem";
    public static final String LOCAL_LOT_SIZE_KEY = "localLotSizeKey";

    private static PlanCnsLotSizeKeyTransDaoImpl instance;

    public static PlanCnsLotSizeKeyTransDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsLotSizeKeyTransDaoImpl();
        }
        return instance;
    }


    public PlanCnsLotSizeKeyTransEntity getEntityWithLocalLotSizeKeyAndSourceSystem(String localLotSize, String sourceSystem) {
        String queryString = QueryHelper.buildCriteria(LOCAL_LOT_SIZE_KEY).is(localLotSize).and(SOURCE_SYSTEM).is(sourceSystem).toQueryString();
        return queryForObject(PLAN_CNS_LOT_SIZE_KEY_TRANS, queryString, PlanCnsLotSizeKeyTransEntity.class);
    }
}
