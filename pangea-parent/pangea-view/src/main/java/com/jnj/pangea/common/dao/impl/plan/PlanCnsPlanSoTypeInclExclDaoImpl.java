package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanSoTypeInclExclEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class PlanCnsPlanSoTypeInclExclDaoImpl extends CommonDaoImpl{

    private static PlanCnsPlanSoTypeInclExclDaoImpl instance;

    public static PlanCnsPlanSoTypeInclExclDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsPlanSoTypeInclExclDaoImpl();
        }
        return instance;
    }

    public PlanCnsPlanSoTypeInclExclEntity getEntityWithParam(String salesOrg, String orderType, String plant, String sourceSystem, String inclExcl) {
        if (StringUtils.isNotEmpty(salesOrg) && StringUtils.isNotEmpty(plant)){
            String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_PLAN_SO_TYPE_INCL_EXCL.SALES_ORG).is(salesOrg)
                    .and(IConstant.PLAN_CNS_PLAN_SO_TYPE_INCL_EXCL.ORDER_TYPE).is(orderType)
                    .and(IConstant.PLAN_CNS_PLAN_SO_TYPE_INCL_EXCL.INCL_EXCL).is(inclExcl)
                    .and(IConstant.PLAN_CNS_PLAN_SO_TYPE_INCL_EXCL.PLANT).is(plant)
                    .and(IConstant.PLAN_CNS_PLAN_SO_TYPE_INCL_EXCL.SOURCE_SYSTEM).is(sourceSystem)
                    .toQueryString();
            return queryForObject(IConstant.REGION.PLAN_CNS_PLAN_SO_TYPE_INCL_EXCL, queryString, PlanCnsPlanSoTypeInclExclEntity.class);
        }
        return null;
    }

    public List<PlanCnsPlanSoTypeInclExclEntity> getEntitiesBySalesOrgAndSourceSystem(String salesOrg, String sourceSystem){
        if (StringUtils.isNotEmpty(salesOrg)) {
            String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_PLAN_SO_TYPE_INCL_EXCL.SALES_ORG).is(salesOrg)
                    .and(IConstant.PLAN_CNS_PLAN_SO_TYPE_INCL_EXCL.SOURCE_SYSTEM).is(sourceSystem)
                    .toQueryString();
            return queryForList(IConstant.REGION.PLAN_CNS_PLAN_SO_TYPE_INCL_EXCL, queryString, PlanCnsPlanSoTypeInclExclEntity.class);
        }
        return null;
    }
}
