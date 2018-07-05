package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.plan.PlanCnsDemGrpAsgnEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class PlanCnsDemGrpAsgnDaoImpl extends CommonDaoImpl {

    private static PlanCnsDemGrpAsgnDaoImpl instance;

    public static PlanCnsDemGrpAsgnDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsDemGrpAsgnDaoImpl();
        }
        return instance;
    }

    public PlanCnsDemGrpAsgnEntity getEntitiesWithCustomerIdAndSalesOrg(String customerId, String salesOrg) {
        if (null != customerId && !"".equals(customerId) &&  null!=salesOrg && !"".equals(salesOrg)){
            String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_DEM_GRP_ASGN.CUSTOMER_ID).is(customerId)
                    .and(IConstant.PLAN_CNS_DEM_GRP_ASGN.SALES_ORG).is(salesOrg).toQueryString();
            return queryForObject(IConstant.REGION.PLAN_CNS_DEM_GRP_ASGN, queryString, PlanCnsDemGrpAsgnEntity.class);
        }
        return null;
    }

    public PlanCnsDemGrpAsgnEntity getEntitiesWithCustomerIdAndSalesOrganization(String customerId, String salesOrg) {
        if (StringUtils.isNotEmpty(customerId) &&  StringUtils.isNotEmpty(salesOrg)){
            String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_DEM_GRP_ASGN.CUSTOMER_ID).is(customerId)
                    .and(IConstant.PLAN_CNS_DEM_GRP_ASGN.SALES_ORGANIZATION).is(salesOrg).toQueryString();
            return queryForObject(IConstant.REGION.PLAN_CNS_DEM_GRP_ASGN, queryString, PlanCnsDemGrpAsgnEntity.class);
        }
        return null;
    }

    public PlanCnsDemGrpAsgnEntity getEntityWithCustomerId(String customerId) {
        if (StringUtils.isNotEmpty(customerId)){
            String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_DEM_GRP_ASGN.CUSTOMER_ID).is(customerId).toQueryString();
            return queryForObject(IConstant.REGION.PLAN_CNS_DEM_GRP_ASGN, queryString, PlanCnsDemGrpAsgnEntity.class);
        }
        return null;
    }

    public List<PlanCnsDemGrpAsgnEntity> getEntityWithDemandGroupAndSourceSystem(String demandGroup, String sourceSystem) {
        String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_DEM_GRP_ASGN.DEMAND_GROUP).is(demandGroup)
                .and(IConstant.PLAN_CNS_DEM_GRP_ASGN.SOURCE_SYSTEM).is(sourceSystem).toQueryString();
        return queryForList(IConstant.REGION.PLAN_CNS_DEM_GRP_ASGN, queryString, PlanCnsDemGrpAsgnEntity.class);
    }
}
