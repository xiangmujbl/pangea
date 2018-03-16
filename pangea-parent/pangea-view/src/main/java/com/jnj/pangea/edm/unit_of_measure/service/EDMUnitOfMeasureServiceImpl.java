package com.jnj.pangea.edm.unit_of_measure.service;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.CommonRegionPath;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.entity.ems.EMSFMdmUnitsEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.unit_of_measure.bo.EDMUnitOfMeasureBo;

import java.util.List;

/**
 * Created by JGUO57 on 2018/3/2.
 */
public class EDMUnitOfMeasureServiceImpl implements ICommonService {


    private static ICommonService instance;

    public static ICommonService getInstance() {
        if (instance == null) {
            instance = new EDMUnitOfMeasureServiceImpl();
        }
        return instance;
    }

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();

        EMSFMdmUnitsEntity mainData = (EMSFMdmUnitsEntity) o;

        EDMUnitOfMeasureBo edmUnitOfMeasureBo = new EDMUnitOfMeasureBo();
        resultObject.setBaseBo(edmUnitOfMeasureBo);

        boolean isOk = processSourceSystem(key, mainData,edmUnitOfMeasureBo);
        if (!isOk) {
            LogUtil.getCoreLog().warn(">>>key:{},processSourceSystem of flag:{}", key, isOk);
            writeFailDataToRegion(mainData,mainData.getzSourceSystem(),"z_source_system value is not [EMS] and rule T1",resultObject);
            return resultObject;
        }
        processSystem(mainData, edmUnitOfMeasureBo);
        processT2(key, mainData, edmUnitOfMeasureBo);
        return resultObject;
    }

    private final boolean processSystem(EMSFMdmUnitsEntity mainData, EDMUnitOfMeasureBo edmUnitOfMeasureBo) {
        edmUnitOfMeasureBo.setLocalUom(mainData.getMdmSapCode());
        edmUnitOfMeasureBo.setLocalUomName(mainData.getMdmName());
        edmUnitOfMeasureBo.setUom(mainData.getzEnterpriseCode());
        edmUnitOfMeasureBo.setIsocode(mainData.getMdmIsoCode());
        edmUnitOfMeasureBo.setMeasure(mainData.getzUnitsDimension());
        edmUnitOfMeasureBo.setFactor("");
        edmUnitOfMeasureBo.setRoundingDecimal("");
        return true;
    }

    private final boolean processSourceSystem(String key, EMSFMdmUnitsEntity mainData, EDMUnitOfMeasureBo edmUnitOfMeasureBo) {
        if (CommonRegionPath.ZSOURCESYSTEM_EMS.equals(mainData.getzSourceSystem())) {
            LogUtil.getCoreLog().info(">>>query {} data is null for project_one, query condition.", CommonRegionPath.EDM_SOURCE_SYSTEM_V1);
            //@TODO write fail data to region or file, T1
            return false;
        }
        if (null == mainData.getzSourceSystem() || mainData.getzSourceSystem().isEmpty()) {
            return false;
        }
        String queryString = QueryHelper.buildCriteria("localSourceSystem").is(mainData.getzSourceSystem()).toQueryString();

        List<EDMSourceSystemV1Entity> sourceList = commonDao.queryForList(CommonRegionPath.EDM_SOURCE_SYSTEM_V1, queryString, EDMSourceSystemV1Entity.class);

        String sourceSystem = null;
        for (Object entry : sourceList) {
            EDMSourceSystemV1Entity sourceSystemV1Entry = (EDMSourceSystemV1Entity) entry;
            sourceSystem = sourceSystemV1Entry.getSourceSystem();
        }
        if(null == sourceSystem || sourceSystem.isEmpty()){
            return false;
        }
        edmUnitOfMeasureBo.setSourceSystem(sourceSystem);

        return true;
    }


    private final boolean processT2(String key, EMSFMdmUnitsEntity mainData, EDMUnitOfMeasureBo edmUnitOfMeasureBo) {
        if (null == mainData.getzEnterpriseCode() || mainData.getzEnterpriseCode().isEmpty()) {
            return true;
        }
        String countryQueryString = QueryHelper.buildCriteria("zSourceSystem")
                .is("[EMS]").and("mdmSapCode").is(mainData.getzEnterpriseCode()).toQueryString();
//        List<Map.Entry<String, String>> items = AdfViewHelper.queryForList(CommonRegionPath.EMS_F_MDM_UNITS_CLONE, countryQueryString);
        List<EMSFMdmUnitsEntity> sourceList = commonDao.queryForList(CommonRegionPath.EMS_F_MDM_UNITS_CLONE, countryQueryString, EMSFMdmUnitsEntity.class);
        String mdmName = null;
        for (EMSFMdmUnitsEntity entry : sourceList) {
            mdmName = entry.getMdmName();
        }
        edmUnitOfMeasureBo.setUomName(mdmName);
        return true;
    }

    private void writeFailDataToRegion(EMSFMdmUnitsEntity mainData, String sourceSystem,String ruleCode,ResultObject resultObject){
        FailData failData = new FailData();
        failData.setFunctionalArea("DP");
        failData.setInterfaceID("EDMUnitOfMeasure");
        failData.setErrorCode(ruleCode);
        failData.setSourceSystem(sourceSystem);
        failData.setKey1(mainData.getzSourceSystem());
        failData.setKey2(mainData.getMdmSapCode());
        failData.setKey3("");
        failData.setKey4("");
        failData.setKey5("");
        failData.setBusinessArea("");
//        FailData failData = new FailData();
//        failData.setFunctionalArea("");
//        failData.setInterfaceID("");
//        failData.setErrorCode(ruleCode);
//        failData.setSourceSystem("");
//        failData.setKey1(mainData.getzSourceSystem());
//        failData.setKey2(mainData.getMdmSapCode());
//        failData.setKey3("");
//        failData.setKey4("");
//        failData.setKey5("");
//        failData.setBusinessArea("");
        resultObject.setFailData(failData);
    }


}
