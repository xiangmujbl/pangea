package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;

 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.CnsPlanRegionEntity;
import org.apache.commons.lang.StringUtils;

public class PlanCnsPlanRegionDaoImpl extends CommonDaoImpl {

    public static final String PLAN_CNS_PLAN_REGION = "/plan/cns_plan_region";

    public static final String PLANNING_REGION_ID = "planningRegionId";

    private static PlanCnsPlanRegionDaoImpl instance;

    public static PlanCnsPlanRegionDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsPlanRegionDaoImpl();
        }
        return instance;
    }

    public String getPlanningRegionDesc(String consumerPlanningRegion) {
        if(StringUtils.isEmpty(consumerPlanningRegion)){
            return null;
        }
        String queryString = QueryHelper.buildCriteria(PLANNING_REGION_ID).is(consumerPlanningRegion).toQueryString();
        CnsPlanRegionEntity cnsPlanRegionEntity = queryForObject(PLAN_CNS_PLAN_REGION, queryString, CnsPlanRegionEntity.class);
        if(cnsPlanRegionEntity!=null&&StringUtils.isNotBlank(cnsPlanRegionEntity.getPlanningRegionDesc())){

            return cnsPlanRegionEntity.getPlanningRegionDesc();
        }

        return null;
    }
}
