package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;

 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanObjectFilterEntity;

import java.util.List;

public class PlanCnsPlanObjectFilterDaoImpl extends CommonDaoImpl {

    public static final String PLAN_CNS_PLAN_OBJECT_FILTER = "/plan/cns_plan_object_filter";

    public static final String SOURCE_SYSTEM = "sourceSystem";
    public static final String SOURCE_OBJECT_TECHNAME = "sourceObjectTechName";
    public static final String SOURCE_OBJECT_ATTRIBUTE1 = "sourceObjectAttribute1";
    public static final String SOURCE_OBJECT_ATTRIBUTE1_VALUE = "sourceObjectAttribute1Value";
    public static final String SOURCE_OBJECT_PLANT_ATTRIBUTE1 = "sourceObjectAttribute1";
    public static final String SOURCE_OBJECT_ATTRIBUTE2_VALUE = "sourceObjectAttribute2Value";
    public static final String SOURCE_OBJECT_PLANT_ATTRIBUTE2 = "sourceObjectAttribute2";
    public static final String SOURCE_FILTER_INCLUSIONEXCLUSION = "inclusionExclusion";


    private static PlanCnsPlanObjectFilterDaoImpl instance;

    public static PlanCnsPlanObjectFilterDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsPlanObjectFilterDaoImpl();
        }
        return instance;
    }

    public PlanCnsPlanObjectFilterEntity getEntityWithSourceObjectTechNameAndSourceSystemPrTypeCdAndPlntCdAndInclusion(String sourceObjectTechName, String sourceSystem, String prTypeCdValue, String plntCd, String plntCdValue, String prTypeCd, String incExcl) {
        String queryString = QueryHelper.buildCriteria(SOURCE_OBJECT_TECHNAME).is(sourceObjectTechName)
                .and(SOURCE_SYSTEM).is(sourceSystem)
                .and(SOURCE_OBJECT_ATTRIBUTE1_VALUE).is(plntCdValue)
                .and(SOURCE_OBJECT_PLANT_ATTRIBUTE1).is(plntCd)
                .and(SOURCE_OBJECT_ATTRIBUTE2_VALUE).is(prTypeCdValue)
                .and(SOURCE_OBJECT_PLANT_ATTRIBUTE2).is(prTypeCd)
                .and(SOURCE_FILTER_INCLUSIONEXCLUSION).is(incExcl).toQueryString();
        return queryForObject(PLAN_CNS_PLAN_OBJECT_FILTER,queryString,PlanCnsPlanObjectFilterEntity.class);
    }

    public PlanCnsPlanObjectFilterEntity getEntityWithSourceObjectTechNameAndSourceSystemAndParams(String sourceObjectTechName, String sourceSystem, String attribute1, String value1, String attribute2, String value2, String incExcl) {
        String queryString = QueryHelper.buildCriteria(SOURCE_OBJECT_TECHNAME).is(sourceObjectTechName)
                .and(SOURCE_SYSTEM).is(sourceSystem)
                .and(SOURCE_OBJECT_PLANT_ATTRIBUTE1).is(attribute1)
                .and(SOURCE_OBJECT_ATTRIBUTE1_VALUE).is(value1)
                .and(SOURCE_OBJECT_PLANT_ATTRIBUTE2).is(attribute2)
                .and(SOURCE_OBJECT_ATTRIBUTE2_VALUE).is(value2)
                .and(SOURCE_FILTER_INCLUSIONEXCLUSION).is(incExcl).toQueryString();
        return queryForObject(PLAN_CNS_PLAN_OBJECT_FILTER,queryString,PlanCnsPlanObjectFilterEntity.class);
    }

    public PlanCnsPlanObjectFilterEntity getEntityWithSourceObjectTechNameAndSourceSystemAndSourceObjectAttribute1AndValue1Attribute2Value2(String sourceObjectTechName, String sourceSystem, String attribute1, String attrValue1, String attribute2, String attrValue2) {
        String queryString = QueryHelper.buildCriteria(
                SOURCE_OBJECT_TECHNAME).is(sourceObjectTechName)
                .and(SOURCE_SYSTEM).is(sourceSystem)
                .and(SOURCE_OBJECT_PLANT_ATTRIBUTE1).is(attribute1)
                .and(SOURCE_OBJECT_ATTRIBUTE1_VALUE).is(attrValue1)
                .and(SOURCE_OBJECT_PLANT_ATTRIBUTE2).is(attribute2)
                .and(SOURCE_OBJECT_ATTRIBUTE2_VALUE).is(attrValue2)
                .toQueryString();
        return queryForObject(PLAN_CNS_PLAN_OBJECT_FILTER,queryString,PlanCnsPlanObjectFilterEntity.class);
    }

    public List<PlanCnsPlanObjectFilterEntity> getEntitiesWithSourceObjectTechNameAndSourceSystem(String sourceObjectTechName, String sourceSystem) {
        String queryString = QueryHelper.buildCriteria(SOURCE_OBJECT_TECHNAME).is(sourceObjectTechName)
                .and(SOURCE_SYSTEM).is(sourceSystem).toQueryString();
        return queryForList(PLAN_CNS_PLAN_OBJECT_FILTER,queryString,PlanCnsPlanObjectFilterEntity.class);
    }
}
