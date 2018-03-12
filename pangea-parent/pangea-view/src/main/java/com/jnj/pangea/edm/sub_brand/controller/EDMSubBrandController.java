package com.jnj.pangea.edm.sub_brand.controller;

import com.jnj.pangea.common.CommonController;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entry.ngems.SubBrandEntity;
import com.jnj.pangea.edm.sub_brand.bo.EDMSubBrandBo;
import com.jnj.pangea.util.BeanUtil;

import java.util.Map;

public class EDMSubBrandController extends CommonController{
    @Override
    public ResultObject process(Map<String, Object> rawMap) {
        ResultObject resultObject = new ResultObject();

        SubBrandEntity subBrandEntity = BeanUtil.mapToBean(rawMap,new SubBrandEntity());
        EDMSubBrandBo subBrandBo = new EDMSubBrandBo();
        subBrandBo.setSubBrand(subBrandEntity.getSubBrand());
        subBrandBo.setSubBrandDescription(subBrandEntity.getSubBrandDescription());

        resultObject.setBaseBo(subBrandBo);
        return resultObject;
    }
}
