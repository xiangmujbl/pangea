package com.jnj.pangea.omp.gdm_location_edm.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.edm.EDMPlantV1Entity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_location_edm.service.OMPGdmLocationEdmServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class OMPGdmLocationEdmController extends CommonController {

    private ICommonService service = OMPGdmLocationEdmServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), EDMPlantV1Entity.class), null);
    }
}
