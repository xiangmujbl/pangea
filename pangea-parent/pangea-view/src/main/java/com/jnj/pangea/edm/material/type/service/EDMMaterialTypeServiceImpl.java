package com.jnj.pangea.edm.material.type.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.CommonRegionPath;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entry.ems.EmsFMdmMaterialTypesEntry;
import com.jnj.pangea.common.entry.projectone.MarmEntry;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.material.type.bo.EDMMaterialTypeBo;
import org.apache.commons.lang3.StringUtils;

public class EDMMaterialTypeServiceImpl  implements ICommonService {
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
        EmsFMdmMaterialTypesEntry mainData = (EmsFMdmMaterialTypesEntry) o;

        EDMMaterialTypeBo materialTypemBo = new EDMMaterialTypeBo();
        resultObject.setBaseBo(materialTypemBo);

        String zSourceSystem= mainData.getzSourceSystem();
        // z_source_system = [EMS]
        if (CommonRegionPath.ZSOURCESYSTEM_EMS.equals(zSourceSystem)) {
            String materialType=StringUtils.trim(mainData.getMdmCode());
            materialTypemBo.setMaterialType(materialType);
            String materialTypeName=StringUtils.trim(mainData.getMdmName());
            materialTypemBo.setMaterialTypeName(materialTypeName);
        }else
        {
            LogUtil.getCoreLog().warn(">>>key:{},z_source_system value is not '[EMS]' :{}", key, zSourceSystem);
            FailData failData = writeFailDataToRegion(mainData,"z_source_system value is not EMS");
            resultObject.setFailData(failData);
        }

        return resultObject;
    }

    private FailData writeFailDataToRegion(EmsFMdmMaterialTypesEntry mainData, String ruleCode){
        FailData failData = new FailData();
        failData.setFunctionalArea("DP");
        failData.setInterfaceID("EDMMaterialType");
        failData.setErrorCode(ruleCode);
        failData.setSourceSystem("EMS");
        failData.setKey1(mainData.getzSourceSystem());
        failData.setKey2(mainData.getMdmCode());
        failData.setKey3(mainData.getMdmName());
        return failData;
    }
}
