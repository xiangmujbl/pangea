package com.jnj.pangea.edm.mat_plant_fi.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.project_one.MbewEntity;
import com.jnj.pangea.edm.mat_plant_fi.service.EDMMatPlantFiServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class EDMMatPlantFiController extends CommonController {

    EDMMatPlantFiServiceImpl matPlantFiService = EDMMatPlantFiServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return matPlantFiService.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), MbewEntity.class), null);

    }
}