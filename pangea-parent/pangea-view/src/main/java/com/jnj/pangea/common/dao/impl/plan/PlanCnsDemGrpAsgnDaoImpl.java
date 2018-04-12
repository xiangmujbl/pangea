package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.plan.PlanCnsDemGrpAsgnEntity;

public class PlanCnsDemGrpAsgnDaoImpl extends CommonDaoImpl {

    private static PlanCnsDemGrpAsgnDaoImpl instance;

    public static PlanCnsDemGrpAsgnDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsDemGrpAsgnDaoImpl();
        }
        return instance;
    }

    public PlanCnsDemGrpAsgnEntity getEntityWithConditions(String param) {

        //String queryString = QueryHelper.buildCriteria(IConstant.EDM_COUNTRY_V1.LOCAL_COUNTRY).is(land1).toQueryString();
        //return queryForObject(IConstant.REGION.EDM_COUNTRY_V1, localQueryString, EDMCountryEntity.class);
        return null;
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
