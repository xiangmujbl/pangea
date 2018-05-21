package com.jnj.pangea.omp.gdm_product_type.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.BaseBo;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.edm.EDMMaterialTypeV1Entity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_product_type.service.OMPGdmProductTypeServiceImpl;
import com.jnj.pangea.util.BeanUtil;

import java.util.LinkedList;
import java.util.List;

public class OMPProductTypeController extends CommonController {

    private ICommonService service = OMPGdmProductTypeServiceImpl.getInstance();

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
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), EDMMaterialTypeV1Entity.class), null);
    }
}
