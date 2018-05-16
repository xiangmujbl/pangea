package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanSplPlnLocEntity;

import java.util.List;

/**
 * @author: qzhan112
 * @date: 2018/5/3
 */
public class PlanSplPlnLocDaoImpl extends CommonDaoImpl {
    private static PlanSplPlnLocDaoImpl instance;

    public static PlanSplPlnLocDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanSplPlnLocDaoImpl();
        }
        return instance;
    }

    public List<PlanSplPlnLocEntity> getEntityListWithConditions(String sourceSystem, String localVendorAccountNumber) {

        String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_SPL_PLN_LOC.SOURCE_SYSTEM).is(sourceSystem)
                .and(IConstant.PLAN_CNS_SPL_PLN_LOC.LOCALNUMBER).is(localVendorAccountNumber)
                .and(IConstant.PLAN_CNS_SPL_PLN_LOC.VENDORORCUSTOMER).is("V")
                .toQueryString();

        return queryForList(IConstant.REGION.PLAN_CNS_SPL_PLN_LOC, queryString, PlanSplPlnLocEntity.class);
    }
}
