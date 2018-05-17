package com.jnj.omp.common.dao.impl.plan;

import com.jnj.omp.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.omp.common.IConstant;
import com.jnj.omp.common.entity.plan.PlanCnsDemGrpAsgnEntity;

public class PlanCnsDemGrpAsgnDaoImpl extends CommonDaoImpl {

    private static PlanCnsDemGrpAsgnDaoImpl instance;

    public static PlanCnsDemGrpAsgnDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsDemGrpAsgnDaoImpl();
        }
        return instance;
    }

    public PlanCnsDemGrpAsgnEntity getEntityWithCustomerShipTo(String customerShipTo) {

        String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_DEM_GRP_ASGN.CUSTOMER_SHIP_TO).is(customerShipTo).toQueryString();
        return queryForObject(IConstant.REGION.PLAN_CNS_DEM_GRP_ASGN, queryString, PlanCnsDemGrpAsgnEntity.class);
    }

    public PlanCnsDemGrpAsgnEntity getEntitiesWithCustomerIdAndSalesOrganization(String customerId, String salesOrganization) {
        if (null != customerId && !"".equals(customerId) &&  null!=salesOrganization && !"".equals(salesOrganization)){
            String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_DEM_GRP_ASGN.CUSTOMER_ID).is(customerId)
                    .and(IConstant.PLAN_CNS_DEM_GRP_ASGN.SALES_ORGANIZATION).is(salesOrganization).toQueryString();
            return queryForObject(IConstant.REGION.PLAN_CNS_DEM_GRP_ASGN, queryString, PlanCnsDemGrpAsgnEntity.class);
        }
        return null;
    }

    public PlanCnsDemGrpAsgnEntity getEntityWithCustomerId(String customerId) {
        if (null != customerId && !"".equals(customerId)){
            String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_DEM_GRP_ASGN.CUSTOMER_ID).is(customerId).toQueryString();
            return queryForObject(IConstant.REGION.PLAN_CNS_DEM_GRP_ASGN, queryString, PlanCnsDemGrpAsgnEntity.class);
        }
        return null;
    }
}
