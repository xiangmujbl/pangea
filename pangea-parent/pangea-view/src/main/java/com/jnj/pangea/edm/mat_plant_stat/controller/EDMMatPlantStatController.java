package com.jnj.pangea.edm.mat_plant_stat.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.project_one.T141Entity;
import com.jnj.pangea.edm.mat_plant_stat.service.EDMMatPlantStatServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class EDMMatPlantStatController extends CommonController {

    EDMMatPlantStatServiceImpl matPlantStatService = new EDMMatPlantStatServiceImpl();

    @Override
    public ResultObject process(RawDataEvent raw) {

        return matPlantStatService.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), T141Entity.class), null);
    }
}
