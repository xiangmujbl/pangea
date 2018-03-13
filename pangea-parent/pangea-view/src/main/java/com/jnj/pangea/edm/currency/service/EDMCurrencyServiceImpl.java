package com.jnj.pangea.edm.currency.service;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.CommonRegionPath;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.entity.ems.EMSFMdmCurrenciesEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.currency.bo.EDMCurrencyBo;

import java.util.List;

/**
 * Created by JGUO57 on 2018/3/2.
 */
public class EDMCurrencyServiceImpl implements ICommonService {


    private static ICommonService instance;

    public static ICommonService getInstance() {
        if (instance == null) {
            instance = new EDMCurrencyServiceImpl();
        }
        return instance;
    }

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();

        EMSFMdmCurrenciesEntity mainData = (EMSFMdmCurrenciesEntity) o;

        EDMCurrencyBo edmCurrencyBo = new EDMCurrencyBo();
        resultObject.setBaseBo(edmCurrencyBo);

        boolean isOk = processSourceSystem(key, mainData,edmCurrencyBo);
        if (!isOk) {
            LogUtil.getCoreLog().warn(">>>key:{},processSourceSystem of flag:{}", key, isOk);
            writeFailDataToRegion(mainData,"z_source_system value is not [EMS] and rule T1",resultObject);
            return resultObject;
        }
        processSystem(mainData, edmCurrencyBo);
        processT2(key, mainData, edmCurrencyBo);
        return resultObject;
    }

    private final boolean processSystem(EMSFMdmCurrenciesEntity mainData, EDMCurrencyBo edmCurrencyBo) {
        edmCurrencyBo.setLocalCurrency(mainData.getzCode());
        edmCurrencyBo.setCurrencyCode(mainData.getzEntCodeIso4217Alpha());
        edmCurrencyBo.setIsoNumeric(mainData.getzIso4217Numeric());
        return true;
    }

    private  boolean processSourceSystem(String key, EMSFMdmCurrenciesEntity mainData, EDMCurrencyBo edmCurrencyBo) {
        LogUtil.getCoreLog().info(">>>>>>>>>>>processSourceSystem>>>>>>>>>mainData:{}", mainData.toString());
        if (CommonRegionPath.ZSOURCESYSTEM_EMS.equals( mainData.getzSourceSystem())) {
            LogUtil.getCoreLog().info(">>>query {} data is null for project_one, query condition.", CommonRegionPath.EDM_SOURCE_SYSTEM_V1);
            //@TODO write fail data to region or file, T1
            return false;
        }
        if (null == mainData.getzSourceSystem() || mainData.getzSourceSystem().isEmpty()) {
            return false;
        }
        String queryString = QueryHelper.buildCriteria("localSourceSystem").is(mainData.getzSourceSystem()).toQueryString();
        LogUtil.getCoreLog().info(">>>>>>>>>>>processSourceSystem>>>>>>>>>queryString:{}", queryString);
        List<EDMSourceSystemV1Entity> sourceList = commonDao.queryForList(CommonRegionPath.EDM_SOURCE_SYSTEM_V1, queryString, EDMSourceSystemV1Entity.class);
        String sourceSystem = null;
        for (Object entry : sourceList) {
            EDMSourceSystemV1Entity sourceSystemV1Entry = (EDMSourceSystemV1Entity) entry;
            LogUtil.getCoreLog().info(">>>>>>>>>>>sourceSystemV1Entry>>>>>>>>>sourceSystemV1Entry:{}", sourceSystemV1Entry.toString());
            sourceSystem = sourceSystemV1Entry.getSourceSystem();
        }
        if(null == sourceSystem || sourceSystem.isEmpty()){
            return false;
        }
        edmCurrencyBo.setSourceSystem(sourceSystem);

        return true;
    }


    private  boolean processT2(String key, EMSFMdmCurrenciesEntity mainData, EDMCurrencyBo edmCurrencyBo) {
        edmCurrencyBo.setCurrencyName("");
        if (null == mainData.getzEntCodeIso4217Alpha() || mainData.getzEntCodeIso4217Alpha().isEmpty()) {

            return true;
        }
        String countryQueryString = QueryHelper.buildCriteria("zSourceSystem")
                .is("[EMS]").and("zCode").is(mainData.getzEntCodeIso4217Alpha()).toQueryString();
//        List<Map.Entry<String, String>> items = AdfViewHelper.queryForList(CommonRegionPath.EMS_F_Z_CURRENCIES_CLONE, countryQueryString);

        List<EMSFMdmCurrenciesEntity> sourceList = commonDao.queryForList(CommonRegionPath.EMS_F_Z_CURRENCIES_CLONE, countryQueryString, EMSFMdmCurrenciesEntity.class);
        String zName = null;
        for (EMSFMdmCurrenciesEntity entry : sourceList) {
            LogUtil.getCoreLog().info(">>>>>>>>>>>zName>>>>>>>>>zName:{}", entry.toString());
            zName = entry.getzName();
        }
        edmCurrencyBo.setCurrencyName(zName);
        return true;
    }

    private void writeFailDataToRegion(EMSFMdmCurrenciesEntity mainData,String ruleCode,ResultObject resultObject){
        FailData failData = new FailData();
        failData.setFunctionalArea("DP");
        failData.setInterfaceID("EDMCurrency");
        failData.setErrorCode(ruleCode);
        failData.setSourceSystem(mainData.getzSourceSystem());
        failData.setKey1(mainData.getzSourceSystem());
        failData.setKey2(mainData.getzCode());
        failData.setKey3("");
        failData.setKey4("");
        failData.setKey5("");
        failData.setBusinessArea("");
        resultObject.setFailData(failData);
    }


}
