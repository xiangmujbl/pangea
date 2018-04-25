package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsAbcIndEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsPlantAttrEntity;

public class PlanCnsPlantAttrDaoImpl extends CommonDaoImpl {

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
        String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_PLANT_ATTR.LOCAL_PLANT).is(localPlant).and(IConstant.PLAN_CNS_PLANT_ATTR.SOURCE_SYSTEM).is(sourceSystem).toQueryString();
        return queryForObject(IConstant.REGION.PLAN_CNS_PLANT_ATTR,queryString,PlanCnsPlantAttrEntity.class);
    }
}