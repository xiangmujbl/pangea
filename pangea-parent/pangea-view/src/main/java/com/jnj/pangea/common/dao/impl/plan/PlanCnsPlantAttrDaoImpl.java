package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;

 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsAbcIndEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsPlantAttrEntity;

import java.util.List;

public class PlanCnsPlantAttrDaoImpl extends CommonDaoImpl {

    public static final String PLAN_CNS_PLANT_ATTR = "/plan/cns_plant_attr";

    public static final String SOURCE_SYSTEM = "sourceSystem";
    public static final String LOCAL_PLANT = "localPlant";

    private static PlanCnsPlantAttrDaoImpl instance;

    public static PlanCnsPlantAttrDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsPlantAttrDaoImpl();
        }
        return instance;
    }

    public PlanCnsPlantAttrEntity getEntityWithLocalPlantAndSourceSystem(String localPlant, String sourceSystem) {
        if ("".equals(localPlant)||"".equals(sourceSystem)){
            return null;
        }
        String queryString = QueryHelper.buildCriteria(LOCAL_PLANT).is(localPlant).and(SOURCE_SYSTEM).is(sourceSystem).toQueryString();
        return queryForObject(PLAN_CNS_PLANT_ATTR,queryString,PlanCnsPlantAttrEntity.class);
    }

    public List<PlanCnsPlantAttrEntity> getEntitiesWithLocPltAndSrcSysAndlocPlnRlvnt(String localPlant, String sourceSystem, String localPlanningRelevant) {
        if ("".equals(localPlant)||"".equals(sourceSystem)){
            return null;
        }
        String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_PLANT_ATTR.LOCAL_PLANT).is(localPlant)
                .and(IConstant.PLAN_CNS_PLANT_ATTR.SOURCE_SYSTEM).is(sourceSystem)
                .and("localPlanningRelevant").is(localPlanningRelevant).toQueryString();
//        LogUtil.getCoreLog().info("------------PlanCnsPlantAttr query>>"+queryString);
        return queryForList(IConstant.REGION.PLAN_CNS_PLANT_ATTR,queryString,PlanCnsPlantAttrEntity.class);
    }

}
