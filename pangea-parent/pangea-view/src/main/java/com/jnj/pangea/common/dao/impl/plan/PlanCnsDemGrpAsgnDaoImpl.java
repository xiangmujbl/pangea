package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.plan.PlanCnsDemGrpAsgnEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class PlanCnsDemGrpAsgnDaoImpl extends CommonDaoImpl {

    public static final String PLAN_CNS_DEM_GRP_ASGN = "/plan/cns_dem_grp_asgn";

    public static final String CUSTOMER_ID = "customerId";
    public static final String SALES_ORG = "salesOrg";
    public static final String SALES_ORGANIZATION = "salesOrganization";
    public static final String CUSTOMER_SHIP_TO = "customerShipTo";
    public static final String SOURCE_SYSTEM = "sourceSystem";
    public static final String DEMAND_GROUP = "demandGroup";

    private static PlanCnsDemGrpAsgnDaoImpl instance;

    public static PlanCnsDemGrpAsgnDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsDemGrpAsgnDaoImpl();
        }
        return instance;
    }

    public PlanCnsDemGrpAsgnEntity getEntitiesWithCustomerIdAndSalesOrg(String customerId, String salesOrg) {
        if (null != customerId && !"".equals(customerId) &&  null!=salesOrg && !"".equals(salesOrg)){
            String queryString = QueryHelper.buildCriteria(CUSTOMER_ID).is(customerId)
                    .and(SALES_ORG).is(salesOrg).toQueryString();
            return queryForObject(PLAN_CNS_DEM_GRP_ASGN, queryString, PlanCnsDemGrpAsgnEntity.class);
        }
        return null;
    }

    public PlanCnsDemGrpAsgnEntity getEntityWithCustomerIdAndSalesOrganization(String customerId, String salesOrg) {
        if (null != customerId && !"".equals(customerId) &&  null!=salesOrg && !"".equals(salesOrg)){
            String queryString = QueryHelper.buildCriteria(CUSTOMER_ID).is(customerId)
                    .and(SALES_ORGANIZATION).is(salesOrg).toQueryString();
            return queryForObject(PLAN_CNS_DEM_GRP_ASGN, queryString, PlanCnsDemGrpAsgnEntity.class);
        }
        return null;
    }

    public PlanCnsDemGrpAsgnEntity getEntitiesWithCustomerIdAndSalesOrganization(String customerId, String salesOrg) {
        if (StringUtils.isNotEmpty(customerId) &&  StringUtils.isNotEmpty(salesOrg)){
            String queryString = QueryHelper.buildCriteria(CUSTOMER_ID).is(customerId)
                    .and(SALES_ORGANIZATION).is(salesOrg).toQueryString();
            return queryForObject(PLAN_CNS_DEM_GRP_ASGN, queryString, PlanCnsDemGrpAsgnEntity.class);
        }
        return null;
    }

    public PlanCnsDemGrpAsgnEntity getEntityWithCustomerId(String customerId) {
        if (StringUtils.isNotEmpty(customerId)){
            String queryString = QueryHelper.buildCriteria(CUSTOMER_ID).is(customerId).toQueryString();
            return queryForObject(PLAN_CNS_DEM_GRP_ASGN, queryString, PlanCnsDemGrpAsgnEntity.class);
        }
        return null;
    }

    public List<PlanCnsDemGrpAsgnEntity> getEntityWithDemandGroupAndSourceSystem(String demandGroup, String sourceSystem) {
        String queryString = QueryHelper.buildCriteria(DEMAND_GROUP).is(demandGroup)
                .and(SOURCE_SYSTEM).is(sourceSystem).toQueryString();
        return queryForList(PLAN_CNS_DEM_GRP_ASGN, queryString, PlanCnsDemGrpAsgnEntity.class);
    }
}
