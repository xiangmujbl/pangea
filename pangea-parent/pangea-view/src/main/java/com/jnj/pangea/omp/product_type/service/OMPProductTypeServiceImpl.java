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

        productTypeBo.setActiveFctErp(IConstant.VALUE.YES);
        productTypeBo.setActiveOprErp(IConstant.VALUE.YES);
        productTypeBo.setActiveSopErp(IConstant.VALUE.YES);

        resultObject.setBaseBo(productTypeBo);
        return resultObject;
    }
}
