package com.jnj.pangea.edm.product_family.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.pangea.common.CommonController;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entry.ngems.ProductFamilyEntity;
import com.jnj.pangea.edm.product_family.bo.EDMProductFamilyBo;
import com.jnj.pangea.util.BeanUtil;

public class EDMProductFamilyController extends CommonController {
    @Override
    public ResultObject process(RawDataEvent raw) {
        ResultObject resultObject = new ResultObject();

        ProductFamilyEntity productFamilyEntity = BeanUtil.mapToBean(raw.getValue().toMap(), new ProductFamilyEntity());
        EDMProductFamilyBo productFamilyBo = new EDMProductFamilyBo();
        productFamilyBo.setProductFamily(productFamilyEntity.getProductFamily());
        productFamilyBo.setProductFamilyName(productFamilyEntity.getProductFamilyName());

        resultObject.setBaseBo(productFamilyBo);
        return resultObject;
    }
}
