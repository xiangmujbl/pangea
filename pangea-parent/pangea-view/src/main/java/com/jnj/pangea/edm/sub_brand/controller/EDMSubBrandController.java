package com.jnj.pangea.edm.sub_brand.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.CommonController;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.ngems.SubBrandEntity;
import com.jnj.pangea.edm.sub_brand.bo.EDMSubBrandBo;
import com.jnj.pangea.util.BeanUtil;

public class EDMSubBrandController extends CommonController {
    @Override
    public ResultObject process(RawDataEvent raw) {
        ResultObject resultObject = new ResultObject();

        SubBrandEntity subBrandEntity = BeanUtil.mapToBean(raw.getValue().toMap(), SubBrandEntity.class);
        EDMSubBrandBo subBrandBo = new EDMSubBrandBo();
        subBrandBo.setSubBrand(subBrandEntity.getSubBrand());
        subBrandBo.setSubBrandDescription(subBrandEntity.getSubBrandDescription());

        resultObject.setBaseBo(subBrandBo);
        return resultObject;
    }
}
