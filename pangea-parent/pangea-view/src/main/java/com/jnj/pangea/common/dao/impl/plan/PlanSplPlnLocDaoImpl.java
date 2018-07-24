package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;

import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanSplPlnLocEntity;

import java.util.List;

/**
 * @author: qzhan112
 * @date: 2018/5/3
 */
public class PlanSplPlnLocDaoImpl extends CommonDaoImpl {

    public static final String PLAN_CNS_SPL_PLN_LOC = "/plan/cns_spl_pln_loc";

    public static final String SOURCE_SYSTEM = "sourceSystem";
    public static final String LOCALNUMBER = "localNumber";
    public static final String VENDORORCUSTOMER = "vendorOrCustomer";
    public static final String LOCAL_PLANT = "localPlant";

    private static PlanSplPlnLocDaoImpl instance;

    public static PlanSplPlnLocDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanSplPlnLocDaoImpl();
        }
        return instance;
    }

    public List<PlanSplPlnLocEntity> getEntityListWithConditions(String sourceSystem, String localVendorAccountNumber) {

        String queryString = QueryHelper.buildCriteria(SOURCE_SYSTEM).is(sourceSystem)
                .and(LOCALNUMBER).is(localVendorAccountNumber)
                .toQueryString();
        return queryForList(PLAN_CNS_SPL_PLN_LOC, queryString, PlanSplPlnLocEntity.class);
    }

    public List<PlanSplPlnLocEntity> getEntityListWithConditions2(String sourceSystem, String localVendorAccountNumber) {

        String queryString = QueryHelper.buildCriteria(SOURCE_SYSTEM).is(sourceSystem)
                .and(LOCALNUMBER).is(localVendorAccountNumber)
                .and(VENDORORCUSTOMER).is("V")
                .toQueryString();
        return queryForList(PLAN_CNS_SPL_PLN_LOC, queryString, PlanSplPlnLocEntity.class);
    }
}
