package com.jnj.omp.common.dao.impl.plan;

import com.jnj.omp.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.omp.common.IConstant;
import com.jnj.omp.common.entity.plan.PlanCnsCustExclEntity;

public class PlanCnsCustExclDaoImpl extends CommonDaoImpl {

    private static PlanCnsCustExclDaoImpl instance;

    public static PlanCnsCustExclDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsCustExclDaoImpl();
        }
        return instance;
    }

    public PlanCnsCustExclEntity getEntityWithSalesOrgAndCustomerShipTo(String salesOrg, String customerShipTo){
        if (!salesOrg.isEmpty() && !customerShipTo.isEmpty()){
            String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_CUST_EXCL.SALES_ORG).is(salesOrg)
                    .and(IConstant.PLAN_CNS_CUST_EXCL.CUSTOMER_SHIP_TO).is(customerShipTo).toQueryString();
            return queryForObject(IConstant.REGION.PLAN_CNS_CUST_EXCL,queryString,PlanCnsCustExclEntity.class);
        }
        return null;
    }
}
