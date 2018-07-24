package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.plan.PlanCnsProdLocAttribEntity;

public class PlanCnsProdLocAttribDaoImpl extends CommonDaoImpl {

    public static final String PLAN_CNS_PROD_LOC_ATTRIB = "/plan/cns_prod_loc_attrib";

    public static final String SOURCE_SYSTEM = "sourceSystem";
    public static final String LOCAL_MATERIAL_NUMBER = "localMaterialNumber";
    public static final String LOCAL_PLANT = "localPlant";

    private static PlanCnsProdLocAttribDaoImpl instance;

    public static PlanCnsProdLocAttribDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsProdLocAttribDaoImpl();
        }
        return instance;
    }


    public PlanCnsProdLocAttribEntity getEntityWithConditions(String sourceSystem, String localMaterialNumber, String localPlant) {
        String queryString = QueryHelper.buildCriteria(SOURCE_SYSTEM).is(sourceSystem)
                .and(LOCAL_MATERIAL_NUMBER).is(localMaterialNumber)
                .and(LOCAL_PLANT).is(localPlant)
                .toQueryString();
        return queryForObject(PLAN_CNS_PROD_LOC_ATTRIB, queryString, PlanCnsProdLocAttribEntity.class);
    }

    public PlanCnsProdLocAttribEntity getEntityWithLocalMaterialNumberAndLocalPlant(String localMaterialNumber, String localPlant) {
        String queryString = QueryHelper.buildCriteria(LOCAL_MATERIAL_NUMBER).is(localMaterialNumber).and(LOCAL_PLANT).is(localPlant).toQueryString();
        return queryForObject(PLAN_CNS_PROD_LOC_ATTRIB, queryString, PlanCnsProdLocAttribEntity.class);
    }

    public PlanCnsProdLocAttribEntity getEntityWithLocalMaterialNumberAndLocalPlantAndSourceSystem(String localMaterialNumber, String localPlant ,String sourceSystem) {
        String queryString = QueryHelper.buildCriteria(LOCAL_MATERIAL_NUMBER).is(localMaterialNumber)
                .and(LOCAL_PLANT).is(localPlant)
                .and(SOURCE_SYSTEM).is(sourceSystem)
                .toQueryString();
        return queryForObject(PLAN_CNS_PROD_LOC_ATTRIB, queryString, PlanCnsProdLocAttribEntity.class);
    }
}
