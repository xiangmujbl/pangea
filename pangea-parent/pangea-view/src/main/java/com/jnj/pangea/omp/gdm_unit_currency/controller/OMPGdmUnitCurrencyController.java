package com.jnj.pangea.omp.gdm_unit_currency.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.edm.EDMCurrencyV1Entity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_unit_currency.service.OMPGdmUnitCurrencyServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class OMPGdmUnitCurrencyController extends CommonController {

    private ICommonService service = OMPGdmUnitCurrencyServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), EDMCurrencyV1Entity.class), null);
    }
}
