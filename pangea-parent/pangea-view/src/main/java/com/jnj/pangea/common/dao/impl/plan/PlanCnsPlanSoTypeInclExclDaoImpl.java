package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanSoTypeInclExclEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class PlanCnsPlanSoTypeInclExclDaoImpl extends CommonDaoImpl{

    public static final String PLAN_CNS_SO_TYPE_INCL_EXCL = "/plan/cns_so_type_incl_excl";

    public static final String SOURCE_SYSTEM = "sourceSystem";
    public static final String SALES_ORG = "salesOrg";
    public static final String ORDER_TYPE = "orderType";
    public static final String PLANT = "plant";
    public static final String INCL_EXCL = "inclExcl";

    private static PlanCnsPlanSoTypeInclExclDaoImpl instance;

    public static PlanCnsPlanSoTypeInclExclDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsPlanSoTypeInclExclDaoImpl();
        }
        return instance;
    }

    public PlanCnsPlanSoTypeInclExclEntity getEntityWithParam(String salesOrg, String orderType, String plant, String sourceSystem, String inclExcl) {
        if (StringUtils.isNotEmpty(salesOrg) && StringUtils.isNotEmpty(plant)){
            String queryString = QueryHelper.buildCriteria(SALES_ORG).is(salesOrg)
                    .and(ORDER_TYPE).is(orderType)
                    .and(INCL_EXCL).is(inclExcl)
                    .and(PLANT).is(plant)
                    .and(SOURCE_SYSTEM).is(sourceSystem)
                    .toQueryString();
            return queryForObject(PLAN_CNS_SO_TYPE_INCL_EXCL, queryString, PlanCnsPlanSoTypeInclExclEntity.class);
        }
        return null;
    }

    public List<PlanCnsPlanSoTypeInclExclEntity> getEntitiesBySalesOrgAndSourceSystem(String salesOrg, String sourceSystem){
        if (StringUtils.isNotEmpty(salesOrg)) {
            String queryString = QueryHelper.buildCriteria(SALES_ORG).is(salesOrg)
                    .and(SOURCE_SYSTEM).is(sourceSystem)
                    .toQueryString();
            return queryForList(PLAN_CNS_SO_TYPE_INCL_EXCL, queryString, PlanCnsPlanSoTypeInclExclEntity.class);
        }
        return null;
    }
}
