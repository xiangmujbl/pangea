package com.jnj.pangea.edm.country.service;

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
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.country.bo.EDMCountryBo;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by JGUO57 on 2018/3/2.
 */
public class EDMCountryServiceImpl implements ICommonService {


    private static ICommonService instance;

    public static ICommonService getInstance() {
        if (instance == null) {
            instance = new EDMCountryServiceImpl();
        }
        return instance;
    }

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {
        LogUtil.getCoreLog().info(">>>>>>>>>>>start>>>>>>>>>buildView key:{},o:{}", key,o);
        ResultObject resultObject = new ResultObject();

        EMSFMdmCountriesEntity mainData = (EMSFMdmCountriesEntity) o;

        EDMCountryBo edmCountryBo = new EDMCountryBo();
        resultObject.setBaseBo(edmCountryBo);

        boolean isOk = processSourceSystem(key, mainData,edmCountryBo);
        if (!isOk) {
            LogUtil.getCoreLog().warn(">>>key:{},processSourceSystem of flag:{}", key, isOk);
            resultObject.setFailData(new FailData());
            return resultObject;
        }

        isOk = processSystem(mainData, edmCountryBo);
        if (!isOk) {
            LogUtil.getCoreLog().warn(">>>key:{},processSystem of flag:{}", key, isOk);
            resultObject.setFailData(new FailData());
            return resultObject;
        }


        isOk = processT2(key, mainData, edmCountryBo);
        if (!isOk) {
            LogUtil.getCoreLog().warn(">>>key:{},processMaterialNumber of flag:{}", key, isOk);
            resultObject.setFailData(new FailData());
            return resultObject;
        }


        return resultObject;
    }

    private  boolean processSystem(EMSFMdmCountriesEntity mainData, EDMCountryBo edmCountryBo) {
        edmCountryBo.setLocalCountry(mainData.getMdmCode());
        edmCountryBo.setCountryCode(mainData.getzEntCodeIso3166Alpha2());
        return true;
    }

    private  boolean processSourceSystem(String key, EMSFMdmCountriesEntity mainData, EDMCountryBo edmCountryBo) {
        if (CommonRegionPath.ZSOURCESYSTEM_EMS.equals(mainData.getzSourceSystem())) {
            LogUtil.getCoreLog().info(">>>query {} data is null for project_one, query condition.", CommonRegionPath.EDM_SOURCE_SYSTEM_V1);
            //@TODO write fail data to region or file, T1
            return false;
        }
        if (null == mainData.getzSourceSystem() || mainData.getzSourceSystem().isEmpty()) {
            return true;
        }
        String queryString = QueryHelper.buildCriteria("localSourceSystem").is(mainData.getzSourceSystem()).toQueryString();
        List<EDMSourceSystemV1Entry> sourceList = commonDao.queryForList(CommonRegionPath.EDM_SOURCE_SYSTEM_V1, queryString, EDMSourceSystemV1Entry.class);
        String sourceSystem = null;
        for (Object entry : sourceList) {
            EDMSourceSystemV1Entry sourceSystemV1Entry = (EDMSourceSystemV1Entry) entry;
            sourceSystem = sourceSystemV1Entry.getSourceSystem();
        }
        edmCountryBo.setSourceSystem(sourceSystem);
        return true;
    }



    private  boolean processT2(String key, EMSFMdmCountriesEntity mainData, EDMCountryBo edmCountryBo) {
        LogUtil.getCoreLog().info("mainData:{}",mainData);
        if (null == mainData.getzEntCodeIso3166Alpha2() || mainData.getzEntCodeIso3166Alpha2().isEmpty()) {
            return true;
        }
        LogUtil.getCoreLog().info("mainData.getzEntCodeIso3166Alpha2():{}",mainData.getzEntCodeIso3166Alpha2());
        String countryQueryString = QueryHelper.buildCriteria("zSourceSystem")
                .is("[EMS]").and("mdmCode").is(mainData.getzEntCodeIso3166Alpha2()).toQueryString();
        List<EMSFMdmCountriesEntity> sourceList = commonDao.queryForList(CommonRegionPath.EMS_F_MDM_COUNTRIES_CLONE, countryQueryString, EMSFMdmCountriesEntity.class);
//        List<Map.Entry<String, String>> items = AdfViewHelper.queryForList(CommonRegionPath.EMS_F_MDM_COUNTRIES_CLONE, countryQueryString);
        String mdmName = null;
        for (EMSFMdmCountriesEntity item : sourceList) {
            mdmName = item.getMdmName();
        }
        edmCountryBo.setCountryName(mdmName);
        return true;
    }


}
