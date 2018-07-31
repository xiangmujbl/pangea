package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.ADFCriteria;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.Utils;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsMaterialPlanStatusEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class PlanCnsMaterialPlanStatusDaoImpl extends CommonDaoImpl {

    public static final String PLAN_CNS_MATERIAL_PLAN_STATUS = "/plan/cns_material_plan_status";

    public static final String SOURCE_SYSTEM = "sourceSystem";
    public static final String LOCAL_MATERIAL_NUMBER = "localMaterialNumber";
    public static final String LOCAL_PLANT = "localPlant";
    public static final String SP_RELEVANT = "spRelevant";
    public static final String NO_PLAN_RELEVANT = "noPlanRelevant";

    private static final String DP_RELEVANT = "DPRelevant";
    private static final String LOCAL_PARENT_CODE = "localParentCode";
    private static final String MATERIAL_NUMBER = "materialNumber";




    private static PlanCnsMaterialPlanStatusDaoImpl instance;

    public static PlanCnsMaterialPlanStatusDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsMaterialPlanStatusDaoImpl();
        }
        return instance;
    }

    public PlanCnsMaterialPlanStatusEntity getEntityWithLocalMaterialNumberAndlLocalPlant(String localMaterialNumber,
                                                                                          String localPlant) {
        if (StringUtils.isNotEmpty(localPlant) && StringUtils.isNotEmpty(localMaterialNumber)) {
            String queryString = QueryHelper.buildCriteria(LOCAL_MATERIAL_NUMBER)
                    .is(localMaterialNumber).and(LOCAL_PLANT).is(localPlant)
                    .toQueryString();
            return queryForObject(PLAN_CNS_MATERIAL_PLAN_STATUS, queryString,
                    PlanCnsMaterialPlanStatusEntity.class);
        }
        return null;
    }

    public PlanCnsMaterialPlanStatusEntity getEntityWithConditions(String localMaterialNumber) {
        String queryString = QueryHelper.buildCriteria(DP_RELEVANT)
                .is(Utils.X).and(LOCAL_MATERIAL_NUMBER)
                .is(localMaterialNumber).and(SOURCE_SYSTEM)
                .is(Utils.PROJECT_ONE).toQueryString();
        return queryForObject(PLAN_CNS_MATERIAL_PLAN_STATUS, queryString,
                PlanCnsMaterialPlanStatusEntity.class);
    }

    public List<PlanCnsMaterialPlanStatusEntity> getEntityListWithConditions(String sourceSystem,
                                                                             String localMaterialNumber, String localPlant, String spRelevant, String noPlanRelevant) {
        if (StringUtils.isNotEmpty(localMaterialNumber) && StringUtils.isNotEmpty(sourceSystem)
                && StringUtils.isNotEmpty(localPlant)) {

            ADFCriteria adfCriteria = QueryHelper.buildCriteria(SOURCE_SYSTEM)
                    .is(sourceSystem).and(LOCAL_MATERIAL_NUMBER)
                    .is(localMaterialNumber).and(LOCAL_PLANT).is(localPlant);

            if (StringUtils.isNotEmpty(spRelevant) && StringUtils.isNotEmpty(noPlanRelevant)) {
                adfCriteria.and(
                        QueryHelper.buildCriteria(SP_RELEVANT).is(spRelevant)
                                .or(QueryHelper.buildCriteria(NO_PLAN_RELEVANT)
                                        .is(noPlanRelevant)));
            } else {
                if (StringUtils.isNotEmpty(spRelevant)) {
                    adfCriteria.and(SP_RELEVANT).is(spRelevant);
                } else if (StringUtils.isNotEmpty(noPlanRelevant)) {
                    adfCriteria.and(NO_PLAN_RELEVANT).is(noPlanRelevant);
                }
            }

            String queryString = adfCriteria.toQueryString();
            return queryForList(PLAN_CNS_MATERIAL_PLAN_STATUS, queryString,
                    PlanCnsMaterialPlanStatusEntity.class);
        }
        return null;
    }

    public PlanCnsMaterialPlanStatusEntity getEntityWithLocalMaterialNumberAndlLocalPlantAndSourceSystem(
            String localMaterialNumber, String localPlant, String sourceSystem) {
        if (StringUtils.isNotEmpty(localPlant) && StringUtils.isNotEmpty(localMaterialNumber)) {
            String queryString = QueryHelper.buildCriteria(LOCAL_MATERIAL_NUMBER)
                    .is(localMaterialNumber).and(LOCAL_PLANT).is(localPlant)
                    .and(SOURCE_SYSTEM).is(sourceSystem).toQueryString();
            return queryForObject(PLAN_CNS_MATERIAL_PLAN_STATUS, queryString,
                    PlanCnsMaterialPlanStatusEntity.class);
        }
        return null;
    }

    public PlanCnsMaterialPlanStatusEntity getEntityWithLocalMaterialNumber(String localMaterialNumber) {
        if (StringUtils.isNotEmpty(localMaterialNumber)) {
            String queryString = QueryHelper.buildCriteria(LOCAL_MATERIAL_NUMBER)
                    .is(localMaterialNumber).toQueryString();
            return queryForObject(PLAN_CNS_MATERIAL_PLAN_STATUS, queryString,
                    PlanCnsMaterialPlanStatusEntity.class);
        }
        return null;
    }

    public PlanCnsMaterialPlanStatusEntity getEntityWithDpRelevantAndLocalMaterialnumber(String localMaterialnumber) {
        String queryString = QueryHelper.buildCriteria(DP_RELEVANT)
                .is(Utils.X).and(LOCAL_MATERIAL_NUMBER)
                .is(localMaterialnumber).toQueryString();
        return queryForObject(PLAN_CNS_MATERIAL_PLAN_STATUS, queryString,
                PlanCnsMaterialPlanStatusEntity.class);
    }

    public PlanCnsMaterialPlanStatusEntity getEntityWithSPNoPlanRelevantAndLocalMaterialnumber(
            String localMaterialnumber) {

        ADFCriteria adfCriteria = QueryHelper.buildCriteria(LOCAL_MATERIAL_NUMBER)
                .is(localMaterialnumber);
        adfCriteria.and(
                QueryHelper.buildCriteria(SP_RELEVANT).is(Utils.X)
                        .or(QueryHelper.buildCriteria(NO_PLAN_RELEVANT)
                                .is(Utils.X)));

        String queryString = adfCriteria.toQueryString();
        return queryForObject(PLAN_CNS_MATERIAL_PLAN_STATUS, queryString,
                PlanCnsMaterialPlanStatusEntity.class);
    }



    public PlanCnsMaterialPlanStatusEntity getEntityWithLocalMaterialNumberAndSourceSystem(String localMaterialNumber,
                                                                                           String sourceSystem) {
        if ("".equals(localMaterialNumber) || "".equals(sourceSystem)) {
            return null;
        }
        String queryString = QueryHelper.buildCriteria(LOCAL_MATERIAL_NUMBER)
                .is(localMaterialNumber).and(SOURCE_SYSTEM).is(sourceSystem)
                .toQueryString();
        return queryForObject(PLAN_CNS_MATERIAL_PLAN_STATUS, queryString,
                PlanCnsMaterialPlanStatusEntity.class);
    }

    public PlanCnsMaterialPlanStatusEntity getEntityWithThereConditions(String localMaterialNumber, String localPlant,
                                                                        String dpRelevant) {
        if (StringUtils.isNotEmpty(localMaterialNumber) && StringUtils.isNotEmpty(localPlant)
                && StringUtils.isNotEmpty(dpRelevant)) {
            String queryString = QueryHelper.buildCriteria(DP_RELEVANT)
                    .is(dpRelevant).and(LOCAL_MATERIAL_NUMBER)
                    .is(localMaterialNumber).and(LOCAL_PLANT).is(localPlant)
                    .toQueryString();
            return queryForObject(PLAN_CNS_MATERIAL_PLAN_STATUS, queryString,
                    PlanCnsMaterialPlanStatusEntity.class);
        }
        return null;
    }

    public List<PlanCnsMaterialPlanStatusEntity> getEntityWithMaterialNumber(String materialNumber) {
        if (StringUtils.isNotEmpty(materialNumber)) {
            String queryString = QueryHelper.buildCriteria(MATERIAL_NUMBER)
                    .is(materialNumber).toQueryString();
            return queryForList(PLAN_CNS_MATERIAL_PLAN_STATUS, queryString,
                    PlanCnsMaterialPlanStatusEntity.class);
        }
        return null;
    }

    public List<PlanCnsMaterialPlanStatusEntity> getEntityListWithLocalMaterialNumber(String localMaterialNumber) {
        if (StringUtils.isNotEmpty(localMaterialNumber)) {
            String queryString = QueryHelper
                    .buildCriteria(LOCAL_MATERIAL_NUMBER)
                    .is(localMaterialNumber).toQueryString();
            return queryForList(PLAN_CNS_MATERIAL_PLAN_STATUS, queryString,
                    PlanCnsMaterialPlanStatusEntity.class);
        }
        return null;
    }

    public List<PlanCnsMaterialPlanStatusEntity> getEntitiesWithLocalMaterialNumberAndSourceSystem(
            String localMaterialNumber, String sourceSystem) {
        if (StringUtils.isNotBlank(localMaterialNumber) && StringUtils.isNotBlank(sourceSystem)) {
            String queryString = QueryHelper
                    .buildCriteria(LOCAL_MATERIAL_NUMBER)
                    .is(localMaterialNumber).and(SOURCE_SYSTEM).is(sourceSystem)
                    .toQueryString();
            return queryForList(PLAN_CNS_MATERIAL_PLAN_STATUS, queryString,
                    PlanCnsMaterialPlanStatusEntity.class);
        }
        return null;
    }

    public PlanCnsMaterialPlanStatusEntity getEntityWithLocalMaterialNumberSourceSystemAndRelevant(String sourceSystem,
                                                                                                   String localMaterialNumber) {
        if (StringUtils.isNotEmpty(sourceSystem) && StringUtils.isNotEmpty(localMaterialNumber)) {
            String queryString = QueryHelper.buildCriteria(SOURCE_SYSTEM)
                    .is(sourceSystem).and(LOCAL_MATERIAL_NUMBER)
                    .is(localMaterialNumber).toQueryString();
            PlanCnsMaterialPlanStatusEntity materialPlanStatusEntity = queryForObject(
                    PLAN_CNS_MATERIAL_PLAN_STATUS, queryString, PlanCnsMaterialPlanStatusEntity.class);

            if (null != materialPlanStatusEntity) {
                if (Utils.X.equals(materialPlanStatusEntity.getDpRelevant())
                        || Utils.X.equals(materialPlanStatusEntity.getSpRelevant())
                        || Utils.X.equals(materialPlanStatusEntity.getNoPlanRelevant())) {

                    return materialPlanStatusEntity;
                }
            }
        }
        return null;
    }

    public PlanCnsMaterialPlanStatusEntity getCnsMaterialPlanStatusDaoEntity(String sourceSystem,
                                                                             String localMaterialNumber, String localPlant) {
        if (StringUtils.isNotEmpty(sourceSystem) && StringUtils.isNotEmpty(localMaterialNumber)
                && StringUtils.isNotEmpty(localPlant)) {
            String queryString = QueryHelper.buildCriteria(SOURCE_SYSTEM)
                    .is(sourceSystem).and(LOCAL_MATERIAL_NUMBER)
                    .is(localMaterialNumber).and(LOCAL_PLANT).is(localPlant)
                    .toQueryString();
            return queryForObject(PLAN_CNS_MATERIAL_PLAN_STATUS, queryString,
                    PlanCnsMaterialPlanStatusEntity.class);
        }
        return null;
    }

    public PlanCnsMaterialPlanStatusEntity getEntityWithSourceSystemAndLocalMaterialNumberAndLocalPlant(
            String sourceSystem, String localMaterialNumber, String localPlant) {
        String queryString = QueryHelper.buildCriteria(SOURCE_SYSTEM)
                .is(sourceSystem).and(LOCAL_MATERIAL_NUMBER).is(localMaterialNumber)
                .and(LOCAL_PLANT).is(localPlant).toQueryString();
        return queryForObject(PLAN_CNS_MATERIAL_PLAN_STATUS, queryString,
                PlanCnsMaterialPlanStatusEntity.class);
    }

    public PlanCnsMaterialPlanStatusEntity getEntityWithLocalParentCode(String localParentCode) {
        if (StringUtils.isNotEmpty(localParentCode)) {
            String queryString = QueryHelper.buildCriteria(LOCAL_PARENT_CODE)
                    .is(localParentCode).toQueryString();
            PlanCnsMaterialPlanStatusEntity planCnsMaterialPlanStatusEntity = queryForObject(
                    PLAN_CNS_MATERIAL_PLAN_STATUS, queryString, PlanCnsMaterialPlanStatusEntity.class);
            if (planCnsMaterialPlanStatusEntity != null) {
                return planCnsMaterialPlanStatusEntity;
            }
        }
        return null;
    }

    public PlanCnsMaterialPlanStatusEntity getEntityWithLocalParentCodeAndDp(String localParentCode) {
        if (StringUtils.isNotEmpty(localParentCode)) {
            String queryString = QueryHelper.buildCriteria(LOCAL_PARENT_CODE)
                    .is(localParentCode).and(DP_RELEVANT).is(Utils.X)
                    .toQueryString();

            PlanCnsMaterialPlanStatusEntity planCnsMaterialPlanStatusEntity = queryForObject(
                    PLAN_CNS_MATERIAL_PLAN_STATUS, queryString, PlanCnsMaterialPlanStatusEntity.class);
            if (planCnsMaterialPlanStatusEntity != null) {
                return planCnsMaterialPlanStatusEntity;
            }
        }
        return null;
    }

    public PlanCnsMaterialPlanStatusEntity getPlanCnsMaterialPlanStatusEntity4251(String sourceSystem,
                                                                                  String localMaterialNumber, String localPlant) {
        ADFCriteria adfCriteria = QueryHelper.buildCriteria();
        if (StringUtils.isBlank(sourceSystem)) {
            adfCriteria.and(QueryHelper.buildCriteria(SOURCE_SYSTEM).isNull());
        } else {
            adfCriteria.and(QueryHelper.buildCriteria(SOURCE_SYSTEM)
                    .is(sourceSystem.trim()));
        }
        if (StringUtils.isBlank(localMaterialNumber)) {
            adfCriteria.and(
                    QueryHelper.buildCriteria(LOCAL_MATERIAL_NUMBER).isNull());
        } else {
            adfCriteria.and(QueryHelper.buildCriteria(LOCAL_MATERIAL_NUMBER)
                    .is(localMaterialNumber.trim()));
        }
        if (StringUtils.isBlank(localPlant)) {
            adfCriteria.and(QueryHelper.buildCriteria(LOCAL_PLANT).isNull());
        } else {
            adfCriteria.and(QueryHelper.buildCriteria(LOCAL_PLANT)
                    .is(localPlant.trim()));
        }
        adfCriteria.and(QueryHelper.buildCriteria(SP_RELEVANT)
                .is(Utils.X));
        return queryForObject(PLAN_CNS_MATERIAL_PLAN_STATUS, adfCriteria.toQueryString(),
                PlanCnsMaterialPlanStatusEntity.class);
    }

    public List<PlanCnsMaterialPlanStatusEntity> getEntitiesWithLocalMaterialNumberLocalPlantSourceSystemAndRelevant(
            String localMaterialNumber, String localPlantNum, String sourceSystem) {
        String queryString = QueryHelper.buildCriteria(LOCAL_MATERIAL_NUMBER)
                .is(localMaterialNumber).and(LOCAL_PLANT).is(localPlantNum)
                .and(SOURCE_SYSTEM).is(sourceSystem)
                .and(SP_RELEVANT).is(Utils.X).toQueryString();
        return queryForList(PLAN_CNS_MATERIAL_PLAN_STATUS, queryString,
                PlanCnsMaterialPlanStatusEntity.class);
    }

    public PlanCnsMaterialPlanStatusEntity getEntityWithLocalMaterialNumberSourceSystemAndDpRelevant(
            String localMaterialNumber, String sourceSystem) {
        if (StringUtils.isNotEmpty(sourceSystem) && StringUtils.isNotEmpty(localMaterialNumber)) {
            String queryString = QueryHelper.buildCriteria(DP_RELEVANT)
                    .is(Utils.X).and(LOCAL_MATERIAL_NUMBER)
                    .is(localMaterialNumber).and(SOURCE_SYSTEM).is(sourceSystem)
                    .toQueryString();
            return queryForObject(PLAN_CNS_MATERIAL_PLAN_STATUS, queryString,
                    PlanCnsMaterialPlanStatusEntity.class);
        }
        return null;
    }
}