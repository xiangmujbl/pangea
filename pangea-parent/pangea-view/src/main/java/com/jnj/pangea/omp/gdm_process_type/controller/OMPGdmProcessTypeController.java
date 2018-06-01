package com.jnj.pangea.omp.gdm_process_type.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.pangea.common.BaseBo;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.plan.PlanCnsProcessTypeEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_process_type.service.OMPGdmProcessTypeServiceImpl;
import com.jnj.pangea.util.BeanUtil;

import java.util.LinkedList;
import java.util.List;

public class OMPGdmProcessTypeController extends CommonController {

    private ICommonService service = OMPGdmProcessTypeServiceImpl.getInstance();


    @Override
    public List<ViewResultItem> process(List<RawDataEvent> events){
        List<ViewResultItem> result = new LinkedList<>();
        events.forEach(raw -> {
            ResultObject resultObject = null;
            resultObject = process(raw);
            if (resultObject!=null&&resultObject.isSuccess()) {
                BaseBo baseBo = resultObject.getBaseBo();
                result.add(ViewResultBuilder.newResultItem(baseBo.getKey(), baseBo.toMap()));
            } else {
                if (resultObject!=null&&null != resultObject.getFailData()) {
                    FailData failData = resultObject.getFailData();
                    result.add(ViewResultBuilder.newResultItem(failData.getFailRegion(), failData.getKey(), failData.toMap()));
                }
            }


        });
        return result;
    }
    public ResultObject process(RawDataEvent raw )  {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), PlanCnsProcessTypeEntity.class), null);
    }

}
