package com.jnj.pangea.edm.material.type.service;

import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.dao.impl.EMSFMdmMaterialTypesDaoImpl;
import com.jnj.pangea.common.entity.ems.EmsFMdmMaterialTypesEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.material.type.bo.EDMMaterialTypeBo;
import org.apache.commons.lang3.StringUtils;

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

        String zSourceSystem = fMdmMaterialTypesEntity.getzSourceSystem();
        if (IConstant.VALUE.EMS.equals(zSourceSystem)){
            materialTypemBo.setMaterialType(fMdmMaterialTypesEntity.getMdmCode());
            materialTypemBo.setMaterialTypeName(fMdmMaterialTypesEntity.getMdmName());
            resultObject.setBaseBo(materialTypemBo);
        } else {
            FailData failData = writeFailDataToRegion(fMdmMaterialTypesEntity, "T1", "z_source_system value is not [EMS]");
            resultObject.setFailData(failData);
        }
        return resultObject;
    }

    private FailData writeFailDataToRegion(EmsFMdmMaterialTypesEntity mainData, String ruleCode, String errorValue) {
        FailData failData = new FailData();
        failData.setFunctionalArea("DP");
        failData.setInterfaceID("EDMMaterialType");
        failData.setErrorCode(ruleCode);
        failData.setSourceSystem("EMS");
        failData.setKey1(mainData.getzSourceSystem());
        failData.setKey2(mainData.getMdmCode());
        failData.setKey3(mainData.getMdmName());
        failData.setKey4("");
        failData.setKey5("");
        failData.setErrorValue(errorValue);
        return failData;
    }
}
