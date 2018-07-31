package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;

 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsProdLocAttribEntity;
import com.jnj.pangea.common.entity.plan.PlanLocMinShelfEnity;

public class PlanLocMinShelfDaoImpl extends CommonDaoImpl {

    public static final String PLAN_PROD_LOC_MIN_SHELF = "/plan/prod_loc_min_shelf";

    public static final String LOCAL_MATERIAL_NUMBER = "localMaterialNumber";
    public static final String LOCAL_PLANT = "localPlant";

    private static PlanLocMinShelfDaoImpl instance;

    public static PlanLocMinShelfDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanLocMinShelfDaoImpl();
        }
        return instance;
    }

    public PlanLocMinShelfEnity getEntityWithLocalMaterialNumberAndLocalPlant(String localMaterialNumber, String localPlant) {
        String queryString = QueryHelper.buildCriteria(LOCAL_MATERIAL_NUMBER).is(localMaterialNumber).and(LOCAL_PLANT).is(localPlant).toQueryString();
        return queryForObject(PLAN_PROD_LOC_MIN_SHELF, queryString, PlanLocMinShelfEnity.class);
    }

    public PlanLocMinShelfEnity getEntityWithLocalPlant(String localPlant) {
        String queryString = QueryHelper.buildCriteria(LOCAL_PLANT).is(localPlant)
                .and(LOCAL_MATERIAL_NUMBER).is("*")
                .toQueryString();
        return queryForObject(PLAN_PROD_LOC_MIN_SHELF, queryString, PlanLocMinShelfEnity.class);
    }
}
