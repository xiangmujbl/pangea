package com.jnj.pangea.plan.cns_material_plan_status.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.pangea.common.BaseBo;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.BaseController;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanParameterDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMaterialPlantV1Entity;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanParameterEntity;
import com.jnj.pangea.plan.cns_material_plan_status.service.PlanCnsMaterialPlanStatusServiceImpl1;
import com.jnj.pangea.util.BeanUtil;

import java.util.*;


public class PlanCnsMaterialPlanStatusController1 extends BaseController {

    private PlanCnsMaterialPlanStatusServiceImpl1 cnsMaterialPlanStatusService = PlanCnsMaterialPlanStatusServiceImpl1.getInstance();
    private PlanCnsPlanParameterDaoImpl planParameterDao = PlanCnsPlanParameterDaoImpl.getInstance();

    @Override
    public List<ViewResultItem> process(List<RawDataEvent> events) {
        List<ViewResultItem> result = new LinkedList<>();

        Set<String> f1LocalMaterialTypeSet = getF1Set(IConstant.VALUE.MATERIAL_TYPE,IConstant.VALUE.I);
        Set<String> f1LocalMaterialTypeNotSet = getF1Set(IConstant.VALUE.MATERIAL_TYPE,IConstant.VALUE.EN);
        Set<String> f1DivisionSet = getF1Set(IConstant.VALUE.DIVISION,IConstant.VALUE.I);
        Set<String> f1DivisionNotSet = getF1Set(IConstant.VALUE.DIVISION,IConstant.VALUE.EN);

        Set<String> f2LocalPlantSet = getF2Set(IConstant.VALUE.PLANT,IConstant.VALUE.I);
        Set<String> f2LocalPlantNotSet = getF2Set(IConstant.VALUE.PLANT,IConstant.VALUE.EN);
        Set<String> f2LocalMRPTypeNotSet = getF2Set(IConstant.VALUE.MRP_TYPE,IConstant.VALUE.EN);
        Set<String> f2LocalPlantStatusSet = getF2Set(IConstant.VALUE.PSMS_STATUS,IConstant.VALUE.I);
        Set<String> f2LocalPlantStatusNotSet = getF2Set(IConstant.VALUE.PSMS_STATUS,IConstant.VALUE.EN);
        Set<String> f2LocalMrpControllerSet = getF2Set(IConstant.VALUE.MRP_CONTROLLER,IConstant.VALUE.I);
        Set<String> f2LocalMrpControllerNotSet = getF2Set(IConstant.VALUE.MRP_CONTROLLER,IConstant.VALUE.EN);

        Set<String> f3LocalPlantSet = getF3Set(IConstant.VALUE.PLANT,IConstant.VALUE.I);
        Set<String> f3LocalPlantNotSet = getF3Set(IConstant.VALUE.PLANT,IConstant.VALUE.EN);
        Set<String> f3LocalMRPTypeSet = getF3Set(IConstant.VALUE.MRP_TYPE,IConstant.VALUE.I);
        Set<String> f3LocalPlantStatusSet = getF3Set(IConstant.VALUE.PSMS_STATUS,IConstant.VALUE.I);
        Set<String> f3LocalPlantStatusNotSet = getF3Set(IConstant.VALUE.PSMS_STATUS,IConstant.VALUE.EN);
        Set<String> f3LocalMrpControllerSet = getF3Set(IConstant.VALUE.MRP_CONTROLLER,IConstant.VALUE.I);
        Set<String> f3LocalMrpControllerNotSet = getF3Set(IConstant.VALUE.MRP_CONTROLLER,IConstant.VALUE.EN);

        events.forEach(raw -> {
            ResultObject resultObject = process(raw,f1LocalMaterialTypeSet,f1LocalMaterialTypeNotSet,f1DivisionSet,f1DivisionNotSet,
                    f2LocalPlantSet,f2LocalPlantNotSet,f2LocalMRPTypeNotSet,f2LocalPlantStatusSet,f2LocalPlantStatusNotSet,f2LocalMrpControllerSet,f2LocalMrpControllerNotSet,
                    f3LocalPlantSet,f3LocalPlantNotSet,f3LocalMRPTypeSet,f3LocalPlantStatusSet,f3LocalPlantStatusNotSet,f3LocalMrpControllerSet,f3LocalMrpControllerNotSet);

            if (null != resultObject){
                if (resultObject.isSuccess()) {
                    BaseBo baseBo = resultObject.getBaseBo();
                    result.add(ViewResultBuilder.newResultItem(baseBo.getKey(), baseBo.toMap()));
                } else {
                    if (null != resultObject.getFailData()) {
                        FailData failData = resultObject.getFailData();
                        result.add(ViewResultBuilder.newResultItem(failData.getFailRegion(), failData.getKey(), failData.toMap()));
                    }
                }
            }
        });

        return result;
    }

    public ResultObject process(RawDataEvent raw,Set<String> f1LocalMaterialTypeSet,Set<String> f1LocalMaterialTypeNotSet,Set<String> f1DivisionSet,Set<String> f1DivisionNotSet,
                                Set<String> f2LocalPlantSet,Set<String> f2LocalPlantNotSet,Set<String> f2LocalMRPTypeNotSet,Set<String> f2LocalPlantStatusSet,Set<String> f2LocalPlantStatusNotSet,Set<String> f2LocalMrpControllerSet,Set<String> f2LocalMrpControllerNotSet,
                                Set<String> f3LocalPlantSet,Set<String> f3LocalPlantNotSet,Set<String> f3LocalMRPTypeSet,Set<String> f3LocalPlantStatusSet,Set<String> f3LocalPlantStatusNotSet,Set<String> f3LocalMrpControllerSet,Set<String> f3LocalMrpControllerNotSet) {

        return cnsMaterialPlanStatusService.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), EDMMaterialPlantV1Entity.class), f1LocalMaterialTypeSet,f1LocalMaterialTypeNotSet,f1DivisionSet,f1DivisionNotSet,
                f2LocalPlantSet,f2LocalPlantNotSet,f2LocalMRPTypeNotSet,f2LocalPlantStatusSet,f2LocalPlantStatusNotSet,f2LocalMrpControllerSet,f2LocalMrpControllerNotSet,
                f3LocalPlantSet,f3LocalPlantNotSet,f3LocalMRPTypeSet,f3LocalPlantStatusSet,f3LocalPlantStatusNotSet,f3LocalMrpControllerSet,f3LocalMrpControllerNotSet);
    }

    private Set<String> getF1Set(String parameter,String inclExcl) {
        List<PlanCnsPlanParameterEntity> planParameterEntities = new ArrayList<>();
        List<PlanCnsPlanParameterEntity> t1DpList = planParameterDao.getEntitiesWithConditions(IConstant.VALUE.CONS_LATAM,
                IConstant.VALUE.CNS_MATERIAL_PLAN_STATUS, IConstant.VALUE.DP_RELEVANT, parameter,inclExcl);
        planParameterEntities.addAll(t1DpList);

        List<PlanCnsPlanParameterEntity> t1SpList = planParameterDao.getEntitiesWithConditions(IConstant.VALUE.CONS_LATAM,
                IConstant.VALUE.CNS_MATERIAL_PLAN_STATUS, IConstant.VALUE.SP_RELEVANT, parameter,inclExcl);
        planParameterEntities.addAll(t1SpList);

        Set<String> parameterValues = new HashSet<>();
        for (PlanCnsPlanParameterEntity planParameterEntity : planParameterEntities) {
            parameterValues.add(planParameterEntity.getParameterValue().trim());
        }
        return parameterValues;
    }


    private Set<String> getF2Set(String parameter,String inclExcl) {
        List<PlanCnsPlanParameterEntity> lmList = planParameterDao.getEntitiesWithConditions(IConstant.VALUE.CONS_LATAM,
                IConstant.VALUE.CNS_MATERIAL_PLAN_STATUS, IConstant.VALUE.DP_RELEVANT,parameter, inclExcl);

        Set<String> lmSet = new HashSet<>();
        for (PlanCnsPlanParameterEntity planParameterEntity : lmList) {
            lmSet.add(planParameterEntity.getParameterValue().trim());
        }
        return lmSet;
    }

    private Set<String> getF3Set(String parameter,String inclExcl) {
        List<PlanCnsPlanParameterEntity> lmList = planParameterDao.getEntitiesWithConditions(IConstant.VALUE.CONS_LATAM,
                IConstant.VALUE.CNS_MATERIAL_PLAN_STATUS, IConstant.VALUE.SP_RELEVANT, parameter, inclExcl);

        Set<String> lmSet = new HashSet<>();
        for (PlanCnsPlanParameterEntity planParameterEntity : lmList) {
            lmSet.add(planParameterEntity.getParameterValue().trim());
        }
        return lmSet;
    }

}