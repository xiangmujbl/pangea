package com.jnj.pangea.omp.gdm_unit_measurable.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.edm.EDMUnitOfMeasureV1Entity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_unit_measurable.service.OMPGdmUnitMeasurableServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class OMPGdmUnitMeasurableController extends CommonController {

    private ICommonService service = OMPGdmUnitMeasurableServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), EDMUnitOfMeasureV1Entity.class), null);
    }
}
