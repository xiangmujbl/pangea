package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.plan.PlanCnsSoTypeInclEntity;
import org.apache.commons.lang3.StringUtils;

public class PlanCnsSoTypeInclDaoImpl extends CommonDaoImpl {

    public static final String PLAN_CNS_SO_TYPE_INCL = "/plan/cns_so_type_incl";

    public static final String SALES_ORG = "salesOrg";
    public static final String ORDER_TYPE = "orderType";
    public static final String INCL_EXCL = "inclExcl";
    public static final String COUNTRY = "country";

    private static PlanCnsSoTypeInclDaoImpl instance;

    public static PlanCnsSoTypeInclDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsSoTypeInclDaoImpl();
        }
        return instance;
    }

    public PlanCnsSoTypeInclEntity getEntityWithConditions(String salesOrg,String orderType,String country,String inclExcl) {

        if (StringUtils.isNotEmpty(salesOrg) && StringUtils.isNotEmpty(orderType) && StringUtils.isNotEmpty(country) && StringUtils.isNotEmpty(inclExcl)){
            String queryString = QueryHelper.buildCriteria(SALES_ORG).is(salesOrg)
                    .and(ORDER_TYPE).is(orderType)
                    .and(COUNTRY).is(country)
                    .and(INCL_EXCL).is(inclExcl).toQueryString();
            return queryForObject(PLAN_CNS_SO_TYPE_INCL, queryString, PlanCnsSoTypeInclEntity.class);
        }
        return null;
    }

    public PlanCnsSoTypeInclEntity getEntityWithConditionsNot(String salesOrg,String orderType,String country,String inclExcl) {

        if (StringUtils.isNotEmpty(salesOrg) && StringUtils.isNotEmpty(orderType) && StringUtils.isNotEmpty(country) && StringUtils.isNotEmpty(inclExcl)){
            String queryString = QueryHelper.buildCriteria(SALES_ORG).is(salesOrg)
                    .and(ORDER_TYPE).not().is(orderType)
                    .and(COUNTRY).is(country)
                    .and(INCL_EXCL).is(inclExcl).toQueryString();
            return queryForObject(PLAN_CNS_SO_TYPE_INCL, queryString, PlanCnsSoTypeInclEntity.class);
        }
        return null;
    }

    public PlanCnsSoTypeInclEntity getEntityWithSalesOrgAndOrderType(String salesOrg, String orderType) {
        if (StringUtils.isNotEmpty(salesOrg) && StringUtils.isNotEmpty(orderType)){
            String queryString = QueryHelper.buildCriteria(SALES_ORG).is(salesOrg)
                    .and(ORDER_TYPE).is(orderType).and(INCL_EXCL)
                    .is("I").toQueryString();
            return queryForObject(PLAN_CNS_SO_TYPE_INCL, queryString, PlanCnsSoTypeInclEntity.class);
        }
        return null;
    }
    public PlanCnsSoTypeInclEntity getEntityWithSalesOrgAndNotOrderType(String salesOrg, String orderType) {
        if (StringUtils.isNotEmpty(salesOrg) && StringUtils.isNotEmpty(orderType)){
            String queryString = QueryHelper.buildCriteria(SALES_ORG).is(salesOrg)
                    .and(ORDER_TYPE).not().is(orderType).and(INCL_EXCL)
                    .is("E").toQueryString();
            return queryForObject(PLAN_CNS_SO_TYPE_INCL, queryString, PlanCnsSoTypeInclEntity.class);
        }
        return null;
    }

    public PlanCnsSoTypeInclEntity getEntityWithSalesOrgAndOrderTypeAndInclExcl(String salesOrg, String orderType,String inclExcl) {
        if (StringUtils.isNotEmpty(salesOrg) && StringUtils.isNotEmpty(orderType) && StringUtils.isNotEmpty(inclExcl)){
            String queryString = QueryHelper.buildCriteria(SALES_ORG).is(salesOrg)
                    .and(ORDER_TYPE).is(orderType).and(INCL_EXCL).is(inclExcl).toQueryString();
            return queryForObject(PLAN_CNS_SO_TYPE_INCL, queryString, PlanCnsSoTypeInclEntity.class);
        }
        return null;
    }

    public PlanCnsSoTypeInclEntity getEntityWithSalesOrgAndNotOrderTypeAndInclExcl(String salesOrg, String orderType,String inclExcl) {
        if (StringUtils.isNotEmpty(salesOrg) && StringUtils.isNotEmpty(orderType) && StringUtils.isNotEmpty(inclExcl)){
            String queryString = QueryHelper.buildCriteria(SALES_ORG).is(salesOrg)
                    .and(ORDER_TYPE).not().is(orderType).and(INCL_EXCL).is(inclExcl).toQueryString();
            return queryForObject(PLAN_CNS_SO_TYPE_INCL, queryString, PlanCnsSoTypeInclEntity.class);
        }
        return null;
    }
}
