package com.jnj.pangea.edm.brand.controller;

import com.jnj.pangea.common.CommonController;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entry.ngems.BrandEntity;
import com.jnj.pangea.edm.brand.bo.EDMBrandBo;
import com.jnj.pangea.util.BeanUtil;

import java.util.Map;

public class EDMBrandController extends CommonController {
    @Override
    public ResultObject process(Map<String, Object> rawMap) {
        ResultObject resultObject = new ResultObject();

        BrandEntity brandEntity = BeanUtil.mapToBean(rawMap,new BrandEntity());
        EDMBrandBo brandBo = new EDMBrandBo();
        brandBo.setBrand(brandEntity.getBrand());
        brandBo.setBrandDescription(brandEntity.getBrandDescription());

        resultObject.setBaseBo(brandBo);
        return resultObject;
    }
}
