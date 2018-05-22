package com.jnj.pangea.omp.gdm_product_type.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.edm.EDMMaterialTypeV1Entity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_product_type.bo.OMPGdmProductTypeBo;
import org.apache.commons.lang.StringUtils;

public class OMPGdmProductTypeServiceImpl implements ICommonService {

    private static OMPGdmProductTypeServiceImpl instance;

    public static OMPGdmProductTypeServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmProductTypeServiceImpl();
        }
        return instance;
    }

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {
        ResultObject resultObject = new ResultObject();
        EDMMaterialTypeV1Entity EDMMaterialTypeV1Entity = (EDMMaterialTypeV1Entity) o;
        OMPGdmProductTypeBo productTypeBo = new OMPGdmProductTypeBo();
        boolean empty = StringUtils.isEmpty(EDMMaterialTypeV1Entity.getMaterialType());
        if(empty){
            return null;
        }else{
        productTypeBo.setProductTypeId(EDMMaterialTypeV1Entity.getMaterialType());
        productTypeBo.setLabel(EDMMaterialTypeV1Entity.getMaterialTypeName());
        productTypeBo.setActiveFCTERP(IConstant.VALUE.YES);
        productTypeBo.setActiveOPRERP(IConstant.VALUE.YES);
        productTypeBo.setActiveSOPERP(IConstant.VALUE.NO);
        resultObject.setBaseBo(productTypeBo);
        return resultObject;
        }
    }
}
