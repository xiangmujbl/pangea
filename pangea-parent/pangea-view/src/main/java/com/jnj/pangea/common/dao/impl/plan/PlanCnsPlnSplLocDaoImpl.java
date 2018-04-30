package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.entity.plan.PlanCnsPlnSplLocEntity;

public class PlanCnsPlnSplLocDaoImpl extends CommonDaoImpl {
    private static PlanCnsPlnSplLocDaoImpl instance;
    public static PlanCnsPlnSplLocDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsPlnSplLocDaoImpl();
        }
        return instance;
    }

    public PlanCnsPlnSplLocEntity getEntityWithLocalNumberAndVendorOrCustomer(String localNumber, String vendorOrCustomer) {
        String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_PLN_SPL_LOC.LOCAL_NUMBER).is(localNumber)
                .and(IConstant.PLAN_CNS_PLN_SPL_LOC.VENDOR_OR_CUSTOMER).is(vendorOrCustomer).toQueryString();
        LogUtil.getCoreLog().info("queryString:{}",queryString);
        return queryForObject(IConstant.REGION.CNS_SPL_PLN_LOC,queryString,PlanCnsPlnSplLocEntity.class);
    }
}
