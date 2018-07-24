package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanDemGrpEntity;
import org.apache.commons.lang.StringUtils;

public class PlanCnsPlanDemGrpDaoImpl extends CommonDaoImpl {

    public static final String PLAN_CNS_DEM_GRP = "/plan/cns_dem_grp";

    public static final String SOURCE_SYSTEM = "sourceSystem";
    public static final String DEMAND_GROUP_ID = "demandGroupId";
    public static final String DEMAND_GROUP_DESC = "demandGroupDesc";
    public static final String LOCAL_CURRENCY = "localCurrency";
    public static final String LOCATION_ID = "locationId";

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
        String queryString = QueryHelper.buildCriteria(SOURCE_SYSTEM).is(sourceSystem)
                .and(DEMAND_GROUP_ID).is(demandGroupId).toQueryString();
        PlanCnsPlanDemGrpEntity cnsPlanDemGrpEntity = queryForObject(PLAN_CNS_DEM_GRP, queryString, PlanCnsPlanDemGrpEntity.class);
        if(cnsPlanDemGrpEntity!=null&&StringUtils.isNotBlank(cnsPlanDemGrpEntity.getLocationId())){
            return cnsPlanDemGrpEntity.getLocationId();
        }
        return null;
    }
}
