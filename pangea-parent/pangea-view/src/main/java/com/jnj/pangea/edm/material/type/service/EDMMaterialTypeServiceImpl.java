package com.jnj.pangea.edm.material.type.service;

import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.ems.EmsFMdmMaterialTypesEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.material.type.bo.EDMMaterialTypeBo;
import org.apache.commons.lang3.StringUtils;

public class EDMMaterialTypeServiceImpl implements ICommonService {
    private static ICommonService instance;

    public static ICommonService getInstance() {
        if (instance == null) {
            instance = new EDMMaterialTypeServiceImpl();
        }
        return instance;
    }

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {
        ResultObject resultObject = new ResultObject();
        EmsFMdmMaterialTypesEntity mainData = (EmsFMdmMaterialTypesEntity) o;

        EDMMaterialTypeBo materialTypemBo = new EDMMaterialTypeBo();
        resultObject.setBaseBo(materialTypemBo);

        String zSourceSystem = mainData.getzSourceSystem();
        // z_source_system = [EMS]
        if (IConstant.VALUE.EMS.equals(zSourceSystem)) {
            String materialType = StringUtils.trim(mainData.getMdmCode());
            materialTypemBo.setMaterialType(materialType);
            String materialTypeName = StringUtils.trim(mainData.getMdmName());
            materialTypemBo.setMaterialTypeName(materialTypeName);
        } else {
            FailData failData = writeFailDataToRegion(mainData, "T1", "z_source_system value is not [EMS]");
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
