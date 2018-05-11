package com.jnj.pangea.plan.cns_material_plan_status_3.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.BaseBo;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.BaseController;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanParameterDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMSalesOrderV1Entity;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanParameterEntity;
import com.jnj.pangea.plan.cns_material_plan_status_3.service.PlanCnsMaterialPlanStatusServiceImpl;
import com.jnj.pangea.util.BeanUtil;

import java.util.*;


public class PlanCnsMaterialPlanStatusController extends BaseController {

    private PlanCnsMaterialPlanStatusServiceImpl cnsMaterialPlanStatusService = PlanCnsMaterialPlanStatusServiceImpl.getInstance();

    private PlanCnsPlanParameterDaoImpl planParameterDao = PlanCnsPlanParameterDaoImpl.getInstance();

    @Override
    public List<ViewResultItem> process(List<RawDataEvent> events) {
        List<ViewResultItem> result = new LinkedList<>();
        Set<String> f1Set = getF1Set();
        Set<String> f2Set = getF2Set();
        String time = getTime();
        events.forEach(raw -> {
            ResultObject resultObject = process(raw, f1Set,f2Set,time);
               if (resultObject!=null&&resultObject.isSuccess()) {
                   BaseBo baseBo = resultObject.getBaseBo();
                   result.add(ViewResultBuilder.newResultItem(baseBo.getKey(), baseBo.toMap()));
                   LogUtil.getLogger().info("-----------------BaseKey:"+baseBo.toMap());

               } else {
                   if (resultObject!=null&&null != resultObject.getFailData()) {
                       FailData failData = resultObject.getFailData();
                       result.add(ViewResultBuilder.newResultItem(failData.getFailRegion(), failData.getKey(), failData.toMap()));
                   }
               }


        });
        LogUtil.getLogger().info("-----------------result:"+result.size());
        return result;
    }

    private Set<String> getF1Set() {
        List<PlanCnsPlanParameterEntity> planParameterEntities = new ArrayList<>();
        List<PlanCnsPlanParameterEntity> t1SpList = planParameterDao.getEntitiesWithConditions(IConstant.VALUE.CONS_LATAM,
                IConstant.VALUE.CNS_MATERIAL_PLAN_STATUS, IConstant.VALUE.DP_RELEVANT, IConstant.VALUE.PLANT,IConstant.VALUE.I);
        planParameterEntities.addAll(t1SpList);
        Set<String> parameterValues = new HashSet<>();
        for (PlanCnsPlanParameterEntity planParameterEntity : planParameterEntities) {
            parameterValues.add(planParameterEntity.getParameterValue().trim());
        }

        return parameterValues;
    }

    private Set<String> getF2Set() {
        List<PlanCnsPlanParameterEntity> planParameterEntities = new ArrayList<>();
        List<PlanCnsPlanParameterEntity> t1SpList = planParameterDao.getEntitiesWithConditions(IConstant.VALUE.CONS_LATAM,
                IConstant.VALUE.CNS_PRODUCT_INCLUSION, IConstant.VALUE.LOCAL_MATERIAL_NUMBER, IConstant.VALUE.MRP_TYPE,IConstant.VALUE.I);
        planParameterEntities.addAll(t1SpList);
        Set<String> parameterValues = new HashSet<>();
        for (PlanCnsPlanParameterEntity planParameterEntity : planParameterEntities) {
            parameterValues.add(planParameterEntity.getParameterValue().trim());
        }
        return parameterValues;
    }


    public String getTime() {
        List<PlanCnsPlanParameterEntity> t1SpList = planParameterDao.getEntitiesWithConditions(IConstant.VALUE.CONS_LATAM,
                IConstant.VALUE.CNS_PRODUCT_INCLUSION, IConstant.VALUE.LESS_DAYS, IConstant.VALUE.LESS_DAYS,IConstant.VALUE.I);
       if(t1SpList.size()==1){
           PlanCnsPlanParameterEntity planCnsPlanParameterEntity = t1SpList.get(0);
           return planCnsPlanParameterEntity.getParameterValue();
       }
        return null;
    }

    public ResultObject process(RawDataEvent raw,Set<String> f1Set,Set<String> f2Set,String time) {

        return cnsMaterialPlanStatusService.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), EDMSalesOrderV1Entity.class), f1Set,f2Set,time);
    }


}