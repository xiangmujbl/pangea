package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanDemGrpEntity;
import org.apache.commons.lang.StringUtils;

public class PlanCnsPlanDemGrpDaoImpl extends CommonDaoImpl {
    private static PlanCnsPlanDemGrpDaoImpl instance;

    public static PlanCnsPlanDemGrpDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsPlanDemGrpDaoImpl();
        }
        return instance;
    }

    public String getLocationId(String sourceSystem, String demandGroupId) {
        if(StringUtils.isEmpty(sourceSystem) || StringUtils.isEmpty(demandGroupId)){
            return null;
        }
        String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_DEM_GRP.SOURCE_SYSTEM).is(sourceSystem)
                .and(IConstant.PLAN_CNS_DEM_GRP.DEMAND_GROUP_ID).is(demandGroupId).toQueryString();
        PlanCnsPlanDemGrpEntity cnsPlanDemGrpEntity = queryForObject(IConstant.REGION.PLAN_CNS_DEM_GRP, queryString, PlanCnsPlanDemGrpEntity.class);
        if(cnsPlanDemGrpEntity!=null&&StringUtils.isNotBlank(cnsPlanDemGrpEntity.getLocationId())){
            return cnsPlanDemGrpEntity.getLocationId();
        }
        return null;
    }
}
