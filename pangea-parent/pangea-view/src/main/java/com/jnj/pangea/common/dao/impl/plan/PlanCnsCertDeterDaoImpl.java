package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.plan.PlanCnsCertDeterEntity;

public class PlanCnsCertDeterDaoImpl extends CommonDaoImpl {

    public static final String PLAN_CNS_CERT_DETER = "/plan/cns_cert_deter";

    public static final String SALES_ORG = "salesOrg";
    public static final String ORDER_TYPE = "orderType";
    public static final String ITEM_CATEGORY = "itemCategory";

    private static PlanCnsCertDeterDaoImpl instance;

    public static PlanCnsCertDeterDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsCertDeterDaoImpl();
        }
        return instance;
    }


    public PlanCnsCertDeterEntity getEntitiesWithConditions(String salesOrg, String orderType, String itemCategory) {
        if (null != salesOrg && !"".equals(salesOrg) && null!=orderType && !"".equals(orderType) && null!=itemCategory && !"".equals(itemCategory)){
            String queryString = QueryHelper.buildCriteria(SALES_ORG).is(salesOrg)
                    .and(ORDER_TYPE).is(orderType)
                    .and(ITEM_CATEGORY).is(itemCategory).toQueryString();
            return queryForObject(PLAN_CNS_CERT_DETER, queryString, PlanCnsCertDeterEntity.class);
        }
        return null;
    }

    public PlanCnsCertDeterEntity getEntitiesWithSalesOrgAndItemCategory(String salesOrg, String itemCategory) {
        if (null != salesOrg && !"".equals(salesOrg) &&  null!=itemCategory && !"".equals(itemCategory)){
            String queryString = QueryHelper.buildCriteria(SALES_ORG).is(salesOrg)
                    .and(ITEM_CATEGORY).is(itemCategory).toQueryString();
            return queryForObject(PLAN_CNS_CERT_DETER, queryString, PlanCnsCertDeterEntity.class);
        }
        return null;
    }

    public PlanCnsCertDeterEntity getEntitiesWithSalesOrgAndOrderType(String salesOrg, String orderType) {
        if (null != salesOrg && !"".equals(salesOrg) && null!=orderType && !"".equals(orderType)){
            String queryString = QueryHelper.buildCriteria(SALES_ORG).is(salesOrg)
                    .and(ORDER_TYPE).is(orderType).toQueryString();
            return queryForObject(PLAN_CNS_CERT_DETER, queryString, PlanCnsCertDeterEntity.class);
        }
        return null;
    }

    public PlanCnsCertDeterEntity getEntitiesWithSalesOrg(String salesOrg) {
        if (null != salesOrg && !"".equals(salesOrg) ){
            String queryString = QueryHelper.buildCriteria(SALES_ORG).is(salesOrg).toQueryString();
            return queryForObject(PLAN_CNS_CERT_DETER, queryString, PlanCnsCertDeterEntity.class);
        }
        return null;
    }
}
