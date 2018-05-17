package com.jnj.omp.edm.country.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.omp.common.ResultObject;
import com.jnj.omp.common.controller.CommonController;
import com.jnj.omp.common.entity.ems.EMSFMdmCountriesEntity;
import com.jnj.omp.common.service.ICommonService;
import com.jnj.omp.edm.country.service.EDMCountryServiceImpl;
import com.jnj.omp.util.BeanUtil;

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
