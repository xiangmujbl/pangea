package com.jnj.pangea.edm.country.service;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.dao.impl.EMSFMdmCountriesDaoImpl;
import com.jnj.pangea.common.entity.ems.EMSFMdmCountriesEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.edm.country.bo.EDMCountryBo;

import java.util.List;

/**
 * Created by JGUO57 on 2018/3/2.
 */
public class EDMCountryServiceImpl implements ICommonService {


    private static ICommonService instance;
    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    private EMSFMdmCountriesDaoImpl emsfMdmCountriesDao = EMSFMdmCountriesDaoImpl.getInstance();
    public static ICommonService getInstance() {
        if (instance == null) {
            instance = new EDMCountryServiceImpl();
        }
        return instance;
    }

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {
        ResultObject resultObject = new ResultObject();

        EMSFMdmCountriesEntity mainData = (EMSFMdmCountriesEntity) o;

        EDMCountryBo edmCountryBo = new EDMCountryBo();
        resultObject.setBaseBo(edmCountryBo);

        if (IConstant.VALUE.EMS.equals(mainData.getzSourceSystem())) {
            writeFailDataToRegion(mainData,  "z_source_system value is not [EMS] and rule T1", resultObject);
            return resultObject;
        }else{
            String sourceSystem = sourceSystemV1Dao.getSourceSystemWithLocalSourceSystem(mainData.getzSourceSystem());
            if(!sourceSystem.isEmpty()){
                edmCountryBo.setSourceSystem(sourceSystem);
            }else{
                writeFailDataToRegion(mainData,  "z_source_system value is not [EMS] and rule T1", resultObject);
                return resultObject;
            }
        }

        processSystem(mainData, edmCountryBo);
//        processT2(key, mainData, edmCountryBo);
        EMSFMdmCountriesEntity emsfMdmCountriesEntity = emsfMdmCountriesDao.getMdmNameWithzSourceSystemAndMdmCode(IConstant.VALUE.EMS,mainData.getzEntCodeIso3166Alpha2());
        if(emsfMdmCountriesEntity!=null){
            edmCountryBo.setCountryName(emsfMdmCountriesEntity.getMdmName());
        }
        return resultObject;
    }

    private boolean processSystem(EMSFMdmCountriesEntity mainData, EDMCountryBo edmCountryBo) {
        edmCountryBo.setLocalCountry(mainData.getMdmCode());
        edmCountryBo.setCountryCode(mainData.getzEntCodeIso3166Alpha2());
        return true;
    }

//    private boolean processT2(String key, EMSFMdmCountriesEntity mainData, EDMCountryBo edmCountryBo) {
//        if (null == mainData.getzEntCodeIso3166Alpha2() || mainData.getzEntCodeIso3166Alpha2().isEmpty()) {
//            return true;
//        }
//        String countryQueryString = QueryHelper.buildCriteria("zSourceSystem")
//                .is("[EMS]").and("mdmCode").is(mainData.getzEntCodeIso3166Alpha2()).toQueryString();
//        List<EMSFMdmCountriesEntity> sourceList = commonDao.queryForList(IConstant.REGION.EMS_F_MDM_COUNTRIES_CLONE, countryQueryString, EMSFMdmCountriesEntity.class);
//        String mdmName = null;
//        for (EMSFMdmCountriesEntity item : sourceList) {
//            mdmName = item.getMdmName();
//        }
//
//        edmCountryBo.setCountryName(mdmName);
//        return true;
//    }

    private void writeFailDataToRegion(EMSFMdmCountriesEntity mainData,  String ruleCode, ResultObject resultObject) {
        FailData failData = new FailData();
        failData.setFunctionalArea("DP");
        failData.setInterfaceID("EDMCountry");
        failData.setErrorCode(ruleCode);
        failData.setSourceSystem(mainData.getzSourceSystem());
        failData.setKey1(mainData.getzSourceSystem());
        failData.setKey2(mainData.getMdmCode());
        failData.setKey3("");
        failData.setKey4("");
        failData.setKey5("");
        failData.setBusinessArea("");
        resultObject.setFailData(failData);
    }

}
