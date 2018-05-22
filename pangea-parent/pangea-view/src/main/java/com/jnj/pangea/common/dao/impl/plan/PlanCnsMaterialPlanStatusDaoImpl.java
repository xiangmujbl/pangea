package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsMaterialPlanStatusEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class PlanCnsMaterialPlanStatusDaoImpl extends CommonDaoImpl {

    private static PlanCnsMaterialPlanStatusDaoImpl instance;

    public static PlanCnsMaterialPlanStatusDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsMaterialPlanStatusDaoImpl();
        }
        return instance;
    }

    public PlanCnsMaterialPlanStatusEntity getEntityWithLocalMaterialNumberAndlLocalPlant(String localMaterialNumber, String localPlant) {
        if (StringUtils.isNotEmpty(localPlant) && StringUtils.isNotEmpty(localMaterialNumber)) {
            String queryString = QueryHelper.buildCriteria(IConstant.CNS_MATERIAL_PLAN_STATUS.LOCAL_MATERIAL_NUMBER).is(localMaterialNumber).and(IConstant.CNS_MATERIAL_PLAN_STATUS.LOCAL_PLANT).is(localPlant).toQueryString();
            return queryForObject(IConstant.REGION.CNS_MATERIAL_PLAN_STATUS, queryString, PlanCnsMaterialPlanStatusEntity.class);
        }
        return null;
    }

    public PlanCnsMaterialPlanStatusEntity getEntityWithConditions(String localMaterialnumber) {
        String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.DP_RELEVANT).is(IConstant.VALUE.X)
                .and(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.LOCAL_MATERIAL_NUMBER).is(localMaterialnumber)
                .and(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.SOURCE_SYSTEM).is(IConstant.VALUE.PROJECT_ONE).toQueryString();
        return queryForObject(IConstant.REGION.PLAN_CNS_MATERIAL_PLAN_STATUS, queryString, PlanCnsMaterialPlanStatusEntity.class);
    }

    public PlanCnsMaterialPlanStatusEntity getEntityWithLocalMaterialNumberAndlLocalPlantAndSourceSystem(String localMaterialNumber, String localPlant, String sourceSystem) {
        if (StringUtils.isNotEmpty(localPlant) && StringUtils.isNotEmpty(localMaterialNumber)) {
            String queryString = QueryHelper.buildCriteria(IConstant.CNS_MATERIAL_PLAN_STATUS.LOCAL_MATERIAL_NUMBER).is(localMaterialNumber)
                    .and(IConstant.CNS_MATERIAL_PLAN_STATUS.LOCAL_PLANT).is(localPlant)
                    .and(IConstant.CNS_MATERIAL_PLAN_STATUS.SOURCE_SYSTEM).is(sourceSystem).toQueryString();
            return queryForObject(IConstant.REGION.CNS_MATERIAL_PLAN_STATUS, queryString, PlanCnsMaterialPlanStatusEntity.class);
        }
        return null;
    }

    public PlanCnsMaterialPlanStatusEntity getEntityWithLocalMaterialNumber(String localMaterialNumber) {
        if (StringUtils.isNotEmpty(localMaterialNumber)) {
            String queryString = QueryHelper.buildCriteria(IConstant.CNS_MATERIAL_PLAN_STATUS.LOCAL_MATERIAL_NUMBER).is(localMaterialNumber).toQueryString();
            return queryForObject(IConstant.REGION.PLAN_CNS_MATERIAL_PLAN_STATUS, queryString, PlanCnsMaterialPlanStatusEntity.class);
        }
        return null;
    }

    public PlanCnsMaterialPlanStatusEntity getEntityWithDpRelevantAndLocalMaterialnumber(String localMaterialnumber) {
        String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.DP_RELEVANT).is(IConstant.VALUE.X)
                .and(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.LOCAL_MATERIAL_NUMBER).is(localMaterialnumber).toQueryString();
        LogUtil.getCoreLog().info("queryString:" + queryString);
        return queryForObject(IConstant.REGION.PLAN_CNS_MATERIAL_PLAN_STATUS, queryString, PlanCnsMaterialPlanStatusEntity.class);
    }

    public PlanCnsMaterialPlanStatusEntity getEntityWithLocalMaterialNumberAndsourceSystem(String localMaterialNumber, String sourceSystem) {
        if ("".equals(localMaterialNumber) || "".equals(sourceSystem)) {
            return null;
        }
        String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.LOCAL_MATERIAL_NUMBER).is(localMaterialNumber)
                .and(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.SOURCE_SYSTEM).is(sourceSystem).toQueryString();
        return queryForObject(IConstant.REGION.PLAN_CNS_MATERIAL_PLAN_STATUS, queryString, PlanCnsMaterialPlanStatusEntity.class);
    }

    public PlanCnsMaterialPlanStatusEntity getEntityWithThereConditions(String localMaterialNumber, String localPlant, String dpRelevant) {
        if (StringUtils.isNotEmpty(localMaterialNumber) && StringUtils.isNotEmpty(localPlant) && StringUtils.isNotEmpty(dpRelevant)) {
            String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.DP_RELEVANT).is(dpRelevant)
                    .and(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.LOCAL_MATERIAL_NUMBER).is(localMaterialNumber)
                    .and(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.LOCAL_PLANT).is(localPlant).toQueryString();
            return queryForObject(IConstant.REGION.PLAN_CNS_MATERIAL_PLAN_STATUS, queryString, PlanCnsMaterialPlanStatusEntity.class);
        }
        return null;
    }

    public List<PlanCnsMaterialPlanStatusEntity> getEntityWithMaterialNumber(String materialNumber) {
        if (StringUtils.isNotEmpty(materialNumber)) {
            String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.MATERIAL_NUMBER).is(materialNumber).toQueryString();
            return queryForList(IConstant.REGION.PLAN_CNS_MATERIAL_PLAN_STATUS, queryString, PlanCnsMaterialPlanStatusEntity.class);
        }
        return null;
    }

    public PlanCnsMaterialPlanStatusEntity getEntityWithLocalMaterialNumberSourceSystemAndRelevant(String sourceSystem, String localMaterialNumber) {
        if (StringUtils.isNotEmpty(sourceSystem) && StringUtils.isNotEmpty(localMaterialNumber)) {
            String queryString = QueryHelper.buildCriteria(IConstant.CNS_MATERIAL_PLAN_STATUS.SOURCE_SYSTEM).is(sourceSystem).and(IConstant.CNS_MATERIAL_PLAN_STATUS.LOCAL_MATERIAL_NUMBER).is(localMaterialNumber).toQueryString();
            PlanCnsMaterialPlanStatusEntity materialPlanStatusEntity = queryForObject(IConstant.REGION.PLAN_CNS_MATERIAL_PLAN_STATUS, queryString, PlanCnsMaterialPlanStatusEntity.class);

            if (null != materialPlanStatusEntity) {
                if (IConstant.VALUE.X.equals(materialPlanStatusEntity.getDpRelevant()) || IConstant.VALUE.X.equals(materialPlanStatusEntity.getSpRelevant()) || IConstant.VALUE.X.equals(materialPlanStatusEntity.getNoPlanRelevant())) {

                    return materialPlanStatusEntity;
                }
            }
        }
        return null;
    }

    public PlanCnsMaterialPlanStatusEntity getCnsMaterialPlanStatusDaoEntity(String sourceSystem, String localMaterialNumber, String localPlant) {
        if (StringUtils.isNotEmpty(sourceSystem) && StringUtils.isNotEmpty(localMaterialNumber) && StringUtils.isNotEmpty(localPlant)) {
            String queryString = QueryHelper.buildCriteria(IConstant.CNS_MATERIAL_PLAN_STATUS.SOURCE_SYSTEM).is(sourceSystem).and(IConstant.CNS_MATERIAL_PLAN_STATUS.LOCAL_MATERIAL_NUMBER).is(localMaterialNumber).and(IConstant.CNS_MATERIAL_PLAN_STATUS.LOCAL_PLANT).is(localPlant).toQueryString();
            return queryForObject(IConstant.REGION.PLAN_CNS_MATERIAL_PLAN_STATUS, queryString, PlanCnsMaterialPlanStatusEntity.class);
        }
        return null;
    }

    public PlanCnsMaterialPlanStatusEntity getEntityWithSourceSystemAndLocalMaterialNumberAndLocalPlant(String sourceSystem, String localMaterialNumber, String localPlant) {
        String queryString = QueryHelper.buildCriteria(IConstant.CNS_MATERIAL_PLAN_STATUS.SOURCE_SYSTEM).is(sourceSystem)
                .and(IConstant.CNS_MATERIAL_PLAN_STATUS.LOCAL_MATERIAL_NUMBER).is(localMaterialNumber)
                .and(IConstant.CNS_MATERIAL_PLAN_STATUS.LOCAL_PLANT).is(localPlant).toQueryString();
        return queryForObject(IConstant.REGION.CNS_MATERIAL_PLAN_STATUS, queryString, PlanCnsMaterialPlanStatusEntity.class);
    }

    public PlanCnsMaterialPlanStatusEntity getEntityWithLocalParentCode(String localParentCode) {
        if (StringUtils.isNotEmpty(localParentCode)) {
            String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.LOCAL_PARENT_CODE).is(localParentCode).toQueryString();
            PlanCnsMaterialPlanStatusEntity planCnsMaterialPlanStatusEntity = queryForObject(IConstant.REGION.PLAN_CNS_MATERIAL_PLAN_STATUS, queryString, PlanCnsMaterialPlanStatusEntity.class);
            if (planCnsMaterialPlanStatusEntity != null) {
                return planCnsMaterialPlanStatusEntity;
            }
        }
        return null;
    }
}