package com.jnj.pangea.edm.unit_of_measure.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.ems.EMSFMdmUnitOfMeasureEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.unit_of_measure.service.EDMUnitOfMeasureServiceImpl;
import com.jnj.pangea.util.BeanUtil;

/**
 * Created by XZhan290 on 2018/2/27.
 */
public class EDMUnitOfMeasureController extends CommonController {

    private ICommonService edmUnitOfMeasureService = EDMUnitOfMeasureServiceImpl.getInstance();


    @Override
    public ResultObject process(RawDataEvent raw) {
        return edmUnitOfMeasureService.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), EMSFMdmUnitOfMeasureEntity.class), null);
    }
}
