package com.jnj.pangea.edm.material.type.service;

import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.ems.EmsFMdmMaterialTypesEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.material.type.bo.EDMMaterialTypeBo;

public class EDMMaterialTypeServiceImpl implements ICommonService {
    private static EDMMaterialTypeServiceImpl instance;

    public static EDMMaterialTypeServiceImpl getInstance() {
        if (instance == null) {
            instance = new EDMMaterialTypeServiceImpl();
        }
        return instance;
    }

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {
        ResultObject resultObject = new ResultObject();
        EmsFMdmMaterialTypesEntity fMdmMaterialTypesEntity = (EmsFMdmMaterialTypesEntity) o;

        EDMMaterialTypeBo materialTypemBo = new EDMMaterialTypeBo();

        materialTypemBo.setMaterialType(fMdmMaterialTypesEntity.getMdmCode());
        materialTypemBo.setMaterialTypeName(fMdmMaterialTypesEntity.getMdmName());
        resultObject.setBaseBo(materialTypemBo);

        return resultObject;
    }
}
