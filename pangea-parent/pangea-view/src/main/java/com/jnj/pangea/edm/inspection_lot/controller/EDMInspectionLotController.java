package com.jnj.pangea.edm.inspection_lot.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.pangea.common.BaseBo;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.project_one.ProjectOneQalsEntity;
import com.jnj.pangea.edm.inspection_lot.service.EDMInspectionLotServiceImpl;
import com.jnj.pangea.util.BeanUtil;
import java.util.LinkedList;
import java.util.List;

public class EDMInspectionLotController extends CommonController  {

    private EDMInspectionLotServiceImpl edmInspectionLotV1Service = EDMInspectionLotServiceImpl.getInstance();



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
                    result.add(ViewResultBuilder.newResultItem(IConstant.REGION.FAIL_DATA, failData.getKey(), failData.toMap()));
                }
            }


        });
        return result;
    }
    public ResultObject process(RawDataEvent raw )  {
        return edmInspectionLotV1Service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), ProjectOneQalsEntity.class), null);
    }


}
