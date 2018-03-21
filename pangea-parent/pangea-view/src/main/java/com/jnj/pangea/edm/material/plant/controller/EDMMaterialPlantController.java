package com.jnj.pangea.edm.material.plant.controller;

import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.curation.logic.IEventProcessor;
import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.*;
import com.jnj.pangea.common.controller.BaseController;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.projectone.MarcEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.material.plant.service.EDMMaterialPlantServiceImpl;
import com.jnj.pangea.util.BeanUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EDMMaterialPlantController extends CommonController {
    private EDMMaterialPlantServiceImpl materialPlantService = EDMMaterialPlantServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return materialPlantService.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), MarcEntity.class), null);

    }

}
