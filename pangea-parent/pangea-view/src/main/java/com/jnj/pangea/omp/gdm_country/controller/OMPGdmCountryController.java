package com.jnj.pangea.omp.gdm_country.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.edm.EDMCountryV1Entity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_country.service.OMPGdmCountryServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class OMPGdmCountryController extends CommonController {

    private ICommonService service = OMPGdmCountryServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), EDMCountryV1Entity.class), null);
    }
}
