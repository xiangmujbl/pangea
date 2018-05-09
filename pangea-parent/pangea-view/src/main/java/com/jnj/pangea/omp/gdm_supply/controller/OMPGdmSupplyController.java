package com.jnj.pangea.omp.gdm_supply.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.edm.EDMSourceListV1Entity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_supply.service.OMPGdmSupplyServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class OMPGdmSupplyController extends CommonController {

    private ICommonService service = OMPGdmSupplyServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), EDMSourceListV1Entity.class), null);
    }
}
