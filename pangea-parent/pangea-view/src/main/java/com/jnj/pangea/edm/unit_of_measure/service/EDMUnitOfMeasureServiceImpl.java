package com.jnj.pangea.edm.unit_of_measure.service;

import com.jnj.adf.client.api.JsonObject;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.adf.grid.view.common.AdfViewHelper;
import com.jnj.pangea.common.CommonRegionPath;
import com.jnj.pangea.common.Dao.ICommonDao;
import com.jnj.pangea.common.Dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entry.edm.EDMSourceSystemV1Entry;
import com.jnj.pangea.common.entry.ems.EMSFMdmCountriesEntity;
import com.jnj.pangea.common.entry.ems.EMSFMdmUnitsEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.country.bo.EDMCountryBo;
import com.jnj.pangea.edm.unit_of_measure.bo.EDMUnitOfMeasureBo;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

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
            resultObject.setFailData(new FailData());
            return resultObject;
        }

        isOk = processSystem(mainData, edmUnitOfMeasureBo);
        if (!isOk) {
            LogUtil.getCoreLog().warn(">>>key:{},processSystem of flag:{}", key, isOk);
            resultObject.setFailData(new FailData());
            return resultObject;
        }


        isOk = processT2(key, mainData, edmUnitOfMeasureBo);
        if (!isOk) {
            LogUtil.getCoreLog().warn(">>>key:{},processMaterialNumber of flag:{}", key, isOk);
            resultObject.setFailData(new FailData());
            return resultObject;
        }


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

        String queryString = QueryHelper.buildCriteria("localSourceSystem").is(mainData.getzSourceSystem()).toQueryString();

        List<EDMSourceSystemV1Entry> sourceList = commonDao.queryForList(CommonRegionPath.EDM_SOURCE_SYSTEM_V1, queryString, EDMSourceSystemV1Entry.class);

        String sourceSystem = null;
        for (Object entry : sourceList) {
            EDMSourceSystemV1Entry sourceSystemV1Entry = (EDMSourceSystemV1Entry) entry;
            sourceSystem = sourceSystemV1Entry.getSourceSystem();
        }

        if (StringUtils.isEmpty(sourceSystem)||CommonRegionPath.ZSOURCESYSTEM_EMS.equals(sourceSystem)) {
            LogUtil.getCoreLog().info(">>>query {} data is null for project_one, query condition.", CommonRegionPath.EDM_SOURCE_SYSTEM_V1);
            //@TODO write fail data to region or file, T1
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
        List<Map.Entry<String, String>> items = AdfViewHelper.queryForList(CommonRegionPath.EMS_F_MDM_UNITS_CLONE, countryQueryString);

        for (Map.Entry<String, String> item : items) {
            Map<String, Object> jsonObj = JsonObject.append(item.getValue()).toMap();
            String mdmName = jsonObj.get("mdmName").toString().trim();
            edmUnitOfMeasureBo.setUomName(mdmName);
        }
        return true;
    }


}
