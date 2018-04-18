package com.jnj.pangea.plan.cns_material_plan_status.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.BaseBo;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.BaseController;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsMaterialInclDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanParameterDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMaterialPlantV1Entity;
import com.jnj.pangea.common.entity.plan.CnsMaterialInclEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanParameterEntity;
import com.jnj.pangea.plan.cns_material_plan_status.service.PlanCnsMaterialPlanStatusServiceImpl;
import com.jnj.pangea.util.BeanUtil;

import java.util.*;


public class PlanCnsMaterialPlanStatusController extends BaseController {

    private PlanCnsMaterialPlanStatusServiceImpl cnsMaterialPlanStatusService = PlanCnsMaterialPlanStatusServiceImpl.getInstance();
    private PlanCnsPlanParameterDaoImpl planParameterDao = PlanCnsPlanParameterDaoImpl.getInstance();
    private PlanCnsMaterialInclDaoImpl materialInclDao = PlanCnsMaterialInclDaoImpl.getInstance();

    @Override
    public List<ViewResultItem> process(List<RawDataEvent> events) {
        List<ViewResultItem> result = new LinkedList<>();

        Set<String> f1Set = getF1Set();
        Set<String> f2ASet = getF2ASet();
        Set<String> f2BSet = getF2BSet();
        Set<String> f3ASet = getF3ASet();
        Set<String> f3BSet = getF3BSet();

        events.forEach(raw -> {
            ResultObject resultObject = process(raw,f1Set,f2ASet,f2BSet,f3ASet,f3BSet);
            if (resultObject.isSuccess()) {
                BaseBo baseBo = resultObject.getBaseBo();
                result.add(ViewResultBuilder.newResultItem(baseBo.getKey(), baseBo.toMap()));
            } else {
                if (null != resultObject.getFailData()) {
                    FailData failData = resultObject.getFailData();
                    result.add(ViewResultBuilder.newResultItem(IConstant.REGION.FAIL_DATA, failData.getKey(), failData.toMap()));
                }
            }
        });

        List<CnsMaterialInclEntity> materialInclEntityList = getMaterialInclEntity();

        for (CnsMaterialInclEntity materialInclEntity : materialInclEntityList) {
            ResultObject resultObject = materialInclProcess(materialInclEntity);
            if (resultObject.isSuccess()) {
                BaseBo baseBo = resultObject.getBaseBo();
                result.add(ViewResultBuilder.newResultItem(baseBo.getKey(), baseBo.toMap()));
            } else {
                if (null != resultObject.getFailData()) {
                    FailData failData = resultObject.getFailData();
                    result.add(ViewResultBuilder.newResultItem(IConstant.REGION.FAIL_DATA, failData.getKey(), failData.toMap()));
                }
            }
        }

        return result;
    }

    private ResultObject materialInclProcess(CnsMaterialInclEntity materialInclEntity) {
        return cnsMaterialPlanStatusService.materialInclBuildView(materialInclEntity);
    }

    public ResultObject process(RawDataEvent raw,Set<String> f1Set, Set<String> f2ASet,Set<String> f2BSet, Set<String> f3ASet,Set<String> f3BSet) {

        return cnsMaterialPlanStatusService.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), EDMMaterialPlantV1Entity.class), f1Set, f2ASet, f2BSet, f3ASet, f3BSet);
    }

    public List<CnsMaterialInclEntity> getMaterialInclEntity() {
        List<CnsMaterialInclEntity> materialInclEntityList = materialInclDao.getAllEntity();
        return materialInclEntityList;
    }

    private Set<String> getF1Set() {
        List<PlanCnsPlanParameterEntity> planParameterEntities = new ArrayList<>();
        List<PlanCnsPlanParameterEntity> t1DpList = planParameterDao.getEntitiesWithConditions(IConstant.VALUE.CONS_LATAM,
                IConstant.VALUE.CNS_MATERIAL_PLAN_STATUS, IConstant.VALUE.DP_RELEVANT, IConstant.VALUE.MATERIAL_TYPE);
        planParameterEntities.addAll(t1DpList);

        List<PlanCnsPlanParameterEntity> t1SpList = planParameterDao.getEntitiesWithConditions(IConstant.VALUE.CONS_LATAM,
                IConstant.VALUE.CNS_MATERIAL_PLAN_STATUS, IConstant.VALUE.SP_RELEVANT, IConstant.VALUE.MATERIAL_TYPE);
        planParameterEntities.addAll(t1SpList);

        Set<String> parameterValues = new HashSet<>();
        for (PlanCnsPlanParameterEntity planParameterEntity : planParameterEntities) {
            parameterValues.add(planParameterEntity.getParameterValue());
        }
        return parameterValues;
    }

    private Set<String> getF2ASet() {
        List<PlanCnsPlanParameterEntity> ldList = planParameterDao.getEntitiesWithConditions(IConstant.VALUE.CONS_LATAM,
                IConstant.VALUE.CNS_MATERIAL_PLAN_STATUS, IConstant.VALUE.DP_RELEVANT, IConstant.VALUE.PLANT);

        Set<String> ldSet = new HashSet<>();
        for (PlanCnsPlanParameterEntity planParameterEntity : ldList) {
            ldSet.add(planParameterEntity.getParameterValue());
        }
        return ldSet;
    }

    private Set<String> getF2BSet() {
        List<PlanCnsPlanParameterEntity> lmList = planParameterDao.getEntitiesWithConditions(IConstant.VALUE.CONS_LATAM,
                IConstant.VALUE.CNS_MATERIAL_PLAN_STATUS, IConstant.VALUE.DP_RELEVANT, IConstant.VALUE.MRP_TYPE, IConstant.VALUE.I);

        Set<String> lmSet = new HashSet<>();
        for (PlanCnsPlanParameterEntity planParameterEntity : lmList) {
            lmSet.add(planParameterEntity.getParameterValue());
        }
        return lmSet;
    }

    private Set<String> getF3ASet() {
        List<PlanCnsPlanParameterEntity> ldList = planParameterDao.getEntitiesWithConditions(IConstant.VALUE.CONS_LATAM,
                IConstant.VALUE.CNS_MATERIAL_PLAN_STATUS, IConstant.VALUE.SP_RELEVANT, IConstant.VALUE.PLANT);

        Set<String> ldSet = new HashSet<>();
        for (PlanCnsPlanParameterEntity planParameterEntity : ldList) {
            ldSet.add(planParameterEntity.getParameterValue());
        }
        return ldSet;
    }

    private Set<String> getF3BSet() {
        List<PlanCnsPlanParameterEntity> lmList = planParameterDao.getEntitiesWithConditions(IConstant.VALUE.CONS_LATAM,
                IConstant.VALUE.CNS_MATERIAL_PLAN_STATUS, IConstant.VALUE.SP_RELEVANT, IConstant.VALUE.MRP_TYPE, IConstant.VALUE.I);

        Set<String> lmSet = new HashSet<>();
        for (PlanCnsPlanParameterEntity planParameterEntity : lmList) {
            lmSet.add(planParameterEntity.getParameterValue());
        }
        return lmSet;
    }


}