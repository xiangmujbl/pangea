package com.jnj.pangea.edm.plant.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.ems.EMSFZEnterprisePlants;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.plant.service.EDMPlantServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class EDMPlantController extends CommonController {

    private ICommonService edmPlantService = EDMPlantServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return edmPlantService.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), EMSFZEnterprisePlants.class), null);
    }
}