package com.jnj.pangea.edm.currency.service;

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
import com.jnj.pangea.common.entry.ems.EMSFMdmCurrenciesEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.country.bo.EDMCountryBo;
import com.jnj.pangea.edm.currency.bo.EDMCurrencyBo;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by JGUO57 on 2018/3/2.
 */
public class EDMCurrencyServiceImpl implements ICommonService {

    private ICommonDao commonDao = CommonDaoImpl.getInstance();

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
            resultObject.setFailData(new FailData());
            return resultObject;
        }

        isOk = processSystem(mainData, edmCurrencyBo);
        if (!isOk) {
            LogUtil.getCoreLog().warn(">>>key:{},processSystem of flag:{}", key, isOk);
            resultObject.setFailData(new FailData());
            return resultObject;
        }

        isOk = processT1(mainData, edmCurrencyBo);
        if (!isOk) {
            LogUtil.getCoreLog().warn(">>>key:{},processSystem of flag:{}", key, isOk);
            resultObject.setFailData(new FailData());
            return resultObject;
        }

        isOk = processT2(key, mainData, edmCurrencyBo);
        if (!isOk) {
            LogUtil.getCoreLog().warn(">>>key:{},processMaterialNumber of flag:{}", key, isOk);
            resultObject.setFailData(new FailData());
            return resultObject;
        }

        //recycling object
        commonDao = null;

        return resultObject;
    }

    private final boolean processSystem(EMSFMdmCurrenciesEntity mainData, EDMCurrencyBo edmCurrencyBo) {
        edmCurrencyBo.setLocalCurrency(mainData.getzCode());
        edmCurrencyBo.setCurrencyCode(mainData.getzEntCodeIso4217Alpha());
        edmCurrencyBo.setIsoNumeric(mainData.getzIso4217Numeric());
        return true;
    }

    private final boolean processSourceSystem(String key, EMSFMdmCurrenciesEntity mainData, EDMCurrencyBo edmCurrencyBo) {

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

        edmCurrencyBo.setSourceSystem(sourceSystem);

        return true;
    }

    private final boolean processT1(EMSFMdmCurrenciesEntity mainData, EDMCurrencyBo edmCurrencyBo) {

        if (StringUtils.isEmpty(mainData.getzSourceSystem())) {
            return true;
        }
        String systemQueryString = QueryHelper.buildCriteria("localSourceSystem").is(mainData.getzSourceSystem()).toQueryString();
        Map.Entry<String, Map<String, Object>> systemResult = AdfViewHelper.queryForMap(CommonRegionPath.EDM_SOURCE_SYSTEM_V1, systemQueryString);
        if (null != systemResult) {
            String sourceSystem = systemResult.getValue().get("sourceSystem").toString().trim();
            edmCurrencyBo.setSourceSystem(sourceSystem);
        }
        return true;
    }

    private final boolean processT2(String key, EMSFMdmCurrenciesEntity mainData, EDMCurrencyBo edmCurrencyBo) {

        if (null == mainData.getzEntCodeIso4217Alpha() || mainData.getzEntCodeIso4217Alpha().isEmpty()) {
            return true;
        }
        String countryQueryString = QueryHelper.buildCriteria("zSourceSystem")
                .is("[EMS]").and("mdmCode").is(mainData.getzEntCodeIso4217Alpha()).toQueryString();
        List<Map.Entry<String, String>> items = AdfViewHelper.queryForList(CommonRegionPath.EMS_F_MDM_CURRENCIES_CLONE, countryQueryString);

        for (Map.Entry<String, String> item : items) {
            Map<String, Object> jsonObj = JsonObject.append(item.getValue()).toMap();
            String zName = jsonObj.get("zName").toString().trim();
            edmCurrencyBo.setCurrencyName(zName);
        }
        return true;
    }


}
