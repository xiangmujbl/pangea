package com.jnj.pangea.edm.material.type.controller;

import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.curation.logic.IEventProcessor;
import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.*;
import com.jnj.pangea.common.controller.BaseController;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.ems.EmsFMdmMaterialTypesEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.material.type.service.EDMMaterialTypeServiceImpl;
import com.jnj.pangea.util.BeanUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EDMMaterialTypeController extends CommonController {
    private EDMMaterialTypeServiceImpl materialTypeService = EDMMaterialTypeServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return materialTypeService.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), EmsFMdmMaterialTypesEntity.class), null);

    }

}