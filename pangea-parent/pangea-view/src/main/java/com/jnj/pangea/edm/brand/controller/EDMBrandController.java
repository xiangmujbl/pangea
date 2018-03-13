package com.jnj.pangea.edm.brand.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.CommonController;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.ngems.BrandEntity;
import com.jnj.pangea.edm.brand.bo.EDMBrandBo;
import com.jnj.pangea.util.BeanUtil;

public class EDMBrandController extends CommonController {
    @Override
    public ResultObject process(RawDataEvent raw) {
        ResultObject resultObject = new ResultObject();

        BrandEntity brandEntity = BeanUtil.mapToBean(raw.getValue().toMap(), BrandEntity.class);
        EDMBrandBo brandBo = new EDMBrandBo();
        brandBo.setBrand(brandEntity.getBrand());
        brandBo.setBrandDescription(brandEntity.getBrandDescription());

        resultObject.setBaseBo(brandBo);
        return resultObject;
    }
}
