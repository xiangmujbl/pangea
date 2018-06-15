package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.plan.PlanCnsSoTypeInclEntity;
import org.apache.commons.lang3.StringUtils;

public class PlanCnsSoTypeInclDaoImpl extends CommonDaoImpl {

    private static PlanCnsSoTypeInclDaoImpl instance;

    public static PlanCnsSoTypeInclDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsSoTypeInclDaoImpl();
        }
        return instance;
    }

    public PlanCnsSoTypeInclEntity getEntityWithConditions(String param) {

        //String queryString = QueryHelper.buildCriteria(IConstant.EDM_COUNTRY_V1.LOCAL_COUNTRY).is(land1).toQueryString();
        //return queryForObject(IConstant.REGION.EDM_COUNTRY_V1, localQueryString, EDMCountryEntity.class);
        return null;
    }

    public PlanCnsSoTypeInclEntity getEntityWithSalesOrgAndOrderType(String salesOrg, String orderType) {
        if (StringUtils.isNotEmpty(salesOrg) && StringUtils.isNotEmpty(orderType)){
            String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_SO_TYPE_INCL.SALES_ORG).is(salesOrg)
                    .and(IConstant.PLAN_CNS_SO_TYPE_INCL.ORDER_TYPE).is(orderType).toQueryString();
            return queryForObject(IConstant.REGION.PLAN_CNS_SO_TYPE_INCL, queryString, PlanCnsSoTypeInclEntity.class);
        }
        return null;
    }

    public PlanCnsSoTypeInclEntity getEntityWithSalesOrgAndOrderTypeAndInclExcl(String salesOrg, String orderType,String inclExcl) {
        if (StringUtils.isNotEmpty(salesOrg) && StringUtils.isNotEmpty(orderType) && StringUtils.isNotEmpty(inclExcl)){
            String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_SO_TYPE_INCL.SALES_ORG).is(salesOrg)
                    .and(IConstant.PLAN_CNS_SO_TYPE_INCL.ORDER_TYPE).is(orderType).and(IConstant.PLAN_CNS_SO_TYPE_INCL.INCL_EXCL).is(inclExcl).toQueryString();
            return queryForObject(IConstant.REGION.PLAN_CNS_SO_TYPE_INCL, queryString, PlanCnsSoTypeInclEntity.class);
        }
        return null;
    }

    public PlanCnsSoTypeInclEntity getEntityWithSalesOrgAndNotOrderTypeAndInclExcl(String salesOrg, String orderType,String inclExcl) {
        if (StringUtils.isNotEmpty(salesOrg) && StringUtils.isNotEmpty(orderType) && StringUtils.isNotEmpty(inclExcl)){
            String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_SO_TYPE_INCL.SALES_ORG).is(salesOrg)
                    .and(IConstant.PLAN_CNS_SO_TYPE_INCL.ORDER_TYPE).not().is(orderType).and(IConstant.PLAN_CNS_SO_TYPE_INCL.INCL_EXCL).is(inclExcl).toQueryString();
            return queryForObject(IConstant.REGION.PLAN_CNS_SO_TYPE_INCL, queryString, PlanCnsSoTypeInclEntity.class);
        }
        return null;
    }
}
