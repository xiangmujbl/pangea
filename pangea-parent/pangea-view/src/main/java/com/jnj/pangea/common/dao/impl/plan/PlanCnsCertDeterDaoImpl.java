package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.plan.PlanCnsCertDeterEntity;

public class PlanCnsCertDeterDaoImpl extends CommonDaoImpl {

    private static PlanCnsCertDeterDaoImpl instance;

    public static PlanCnsCertDeterDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsCertDeterDaoImpl();
        }
        return instance;
    }

    public PlanCnsCertDeterEntity getEntityWithConditions(String param) {

        //String queryString = QueryHelper.buildCriteria(IConstant.EDM_COUNTRY_V1.LOCAL_COUNTRY).is(land1).toQueryString();
        //return queryForObject(IConstant.REGION.EDM_COUNTRY_V1, localQueryString, EDMCountryEntity.class);
        return null;
    }

    public PlanCnsCertDeterEntity getEntitiesWithConditions(String salesOrg, String orderType, String itemCategory) {
        if (null != salesOrg && !"".equals(salesOrg) && null!=orderType && !"".equals(orderType) && null!=itemCategory && !"".equals(itemCategory)){
            String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_CERT_DETER.SALES_ORG).is(salesOrg)
                    .and(IConstant.PLAN_CNS_CERT_DETER.ORDER_TYPE).is(orderType)
                    .and(IConstant.PLAN_CNS_CERT_DETER.ITEM_CATEGORY).is(itemCategory).toQueryString();
            return queryForObject(IConstant.REGION.PLAN_CNS_CERT_DETER, queryString, PlanCnsCertDeterEntity.class);
        }
        return null;
    }

    public PlanCnsCertDeterEntity getEntitiesWithSalesOrgAndItemCategory(String salesOrg, String itemCategory) {
        if (null != salesOrg && !"".equals(salesOrg) &&  null!=itemCategory && !"".equals(itemCategory)){
            String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_CERT_DETER.SALES_ORG).is(salesOrg)
                    .and(IConstant.PLAN_CNS_CERT_DETER.ITEM_CATEGORY).is(itemCategory).toQueryString();
            return queryForObject(IConstant.REGION.PLAN_CNS_CERT_DETER, queryString, PlanCnsCertDeterEntity.class);
        }
        return null;
    }

    public PlanCnsCertDeterEntity getEntitiesWithSalesOrgAndOrderType(String salesOrg, String orderType) {
        if (null != salesOrg && !"".equals(salesOrg) && null!=orderType && !"".equals(orderType)){
            String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_CERT_DETER.SALES_ORG).is(salesOrg)
                    .and(IConstant.PLAN_CNS_CERT_DETER.ORDER_TYPE).is(orderType).toQueryString();
            return queryForObject(IConstant.REGION.PLAN_CNS_CERT_DETER, queryString, PlanCnsCertDeterEntity.class);
        }
        return null;
    }
}
