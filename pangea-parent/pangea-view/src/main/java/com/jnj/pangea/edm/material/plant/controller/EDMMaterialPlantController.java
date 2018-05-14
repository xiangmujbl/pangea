package com.jnj.pangea.edm.material.plant.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.*;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.project_one.MarcEntity;
import com.jnj.pangea.edm.material.plant.service.EDMMaterialPlantServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class EDMMaterialPlantController extends CommonController {
    private EDMMaterialPlantServiceImpl materialPlantService = EDMMaterialPlantServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return materialPlantService.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), MarcEntity.class), null);

    }

}
