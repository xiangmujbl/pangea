package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsProdLocAttribEntity;
import com.jnj.pangea.common.entity.plan.PlanLocMinShelfEnity;

public class PlanLocMinShelfDaoImpl extends CommonDaoImpl {
    private static PlanLocMinShelfDaoImpl instance;

    public static PlanLocMinShelfDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanLocMinShelfDaoImpl();
        }
        return instance;
    }

    public PlanLocMinShelfEnity getEntityWithLocalMaterialNumberAndLocalPlant(String localMaterialNumber, String localPlant) {
        String queryString = QueryHelper.buildCriteria(IConstant.PLAN_PROD_LOC_MIN_SHELF.LOCAL_MATERIAL_NUMBER).is(localMaterialNumber).and(IConstant.PLAN_PROD_LOC_MIN_SHELF.LOCAL_PLANT).is(localPlant).toQueryString();
        return queryForObject(IConstant.REGION.PLAN_PROD_LOC_MIN_SHELF, queryString, PlanLocMinShelfEnity.class);
    }

    public PlanLocMinShelfEnity getEntityWithLocalPlant(String localPlant) {
        String queryString = QueryHelper.buildCriteria(IConstant.PLAN_PROD_LOC_MIN_SHELF.LOCAL_PLANT).is(localPlant)
                .and(IConstant.PLAN_PROD_LOC_MIN_SHELF.LOCAL_MATERIAL_NUMBER).is("*")
                .toQueryString();
        return queryForObject(IConstant.REGION.PLAN_PROD_LOC_MIN_SHELF, queryString, PlanLocMinShelfEnity.class);
    }
}
