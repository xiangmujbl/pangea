package com.jnj.pangea.omp.product_type.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.edm.EDMMaterialTypeV1Entity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.product_type.bo.OMPProductTypeBo;

public class OMPProductTypeServiceImpl implements ICommonService {

    private static OMPProductTypeServiceImpl instance;

    public static OMPProductTypeServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPProductTypeServiceImpl();
        }
        return instance;
    }

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        EDMMaterialTypeV1Entity EDMMaterialTypeV1Entity = (EDMMaterialTypeV1Entity) o;

        OMPProductTypeBo productTypeBo = new OMPProductTypeBo();

        productTypeBo.setProductTypeId(EDMMaterialTypeV1Entity.getMaterialType());
        productTypeBo.setLabel(EDMMaterialTypeV1Entity.getMaterialTypeName());

        productTypeBo.setActiveFCTERP(IConstant.VALUE.YES);
        productTypeBo.setActiveOPRERP(IConstant.VALUE.YES);
        productTypeBo.setActiveSOPERP(IConstant.VALUE.NO);

        resultObject.setBaseBo(productTypeBo);
        return resultObject;
    }
}
