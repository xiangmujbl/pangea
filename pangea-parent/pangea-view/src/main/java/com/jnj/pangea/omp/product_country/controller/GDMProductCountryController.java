package com.jnj.pangea.omp.product_country.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.plan.CnsProdCtyAffEntity;
import com.jnj.pangea.omp.product_country.service.GDMProductCountryServiceImpl;
import com.jnj.pangea.util.BeanUtil;

public class GDMProductCountryController extends CommonController {

    private GDMProductCountryServiceImpl productCountryService = GDMProductCountryServiceImpl.getInstance();

    @Override
    public ResultObject process(RawDataEvent raw) {
        return productCountryService.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), CnsProdCtyAffEntity.class), null);

    }
}
