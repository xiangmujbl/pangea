package com.jnj.pangea.edm.country.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.ems.EMSFMdmCountriesEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.country.service.EDMCountryServiceImpl;
import com.jnj.pangea.util.BeanUtil;

/**
 * Created by XZhan290 on 2018/2/27.
 */
public class EDMCountryController extends CommonController {

    private ICommonService edmCountryService = EDMCountryServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return edmCountryService.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), EMSFMdmCountriesEntity.class), null);
    }

}
