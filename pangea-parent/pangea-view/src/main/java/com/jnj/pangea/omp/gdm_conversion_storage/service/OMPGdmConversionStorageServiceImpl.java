package com.jnj.pangea.omp.gdm_conversion_storage.service;

import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMCountryV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialGlobalV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsCustChannelDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsDpPriceDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMCountryEntity;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.entity.plan.PlanCnsCustChannelEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsDpPriceEntity;
import com.jnj.pangea.omp.gdm_conversion_storage.bo.OMPGdmConversionStorageBo;
import com.jnj.pangea.util.DateUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class OMPGdmConversionStorageServiceImpl {

    private static OMPGdmConversionStorageServiceImpl instance;

    public static OMPGdmConversionStorageServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmConversionStorageServiceImpl();
        }
        return instance;
    }

    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    private EDMCountryV1DaoImpl countryV1Dao = EDMCountryV1DaoImpl.getInstance();
    private EDMMaterialGlobalV1DaoImpl materialGlobalV1Dao = EDMMaterialGlobalV1DaoImpl.getInstance();
    private PlanCnsCustChannelDaoImpl cnsCustChannelDao = PlanCnsCustChannelDaoImpl.getInstance();
    private PlanCnsDpPriceDaoImpl cnsDpPriceDao = PlanCnsDpPriceDaoImpl.getInstance();

    public List<ResultObject> buildView(String key, Object o, Object o2) {

        List<ResultObject> resultObjectList = new ArrayList<ResultObject>();
        PlanCnsDpPriceEntity cnsDpPriceEntity = (PlanCnsDpPriceEntity) o;

        // J1
        EDMMaterialGlobalV1Entity edmMaterialGlobalV1Entity = materialGlobalV1Dao.getEntityWithLocalMaterialNumberAndSourceSystem(cnsDpPriceEntity.getLocalMaterialNumber(), cnsDpPriceEntity.getSourceSystem());
        if (edmMaterialGlobalV1Entity == null) {
            FailData failData = new FailData();
            failData.setErrorCode(IConstant.FAILED.ERROR_CODE.J1);
            failData.setErrorValue("sourceSystem / dpParent code / channel / countryCode do not exist");
            failData.setFunctionalArea(IConstant.FAILED.FUNCTIONAL_AREA.DP);
            failData.setInterfaceID(IConstant.FAILED.INTERFACE_ID.OMP_GDM_CONVERSION_STORAGE);
            failData.setSourceSystem(IConstant.VALUE.OMP);
            failData.setKey1(cnsDpPriceEntity.getLocalMaterialNumber());
            failData.setKey2(cnsDpPriceEntity.getSourceSystem());
            failData.setKey3(cnsDpPriceEntity.getCurrency());
            failData.setKey4(cnsDpPriceEntity.getCountry());
            failData.setKey5(cnsDpPriceEntity.getFromDate());
            ResultObject resultObject = new ResultObject();
            resultObject.setFailData(failData);
            resultObjectList.add(resultObject);
            return resultObjectList;
        }
        EDMSourceSystemV1Entity sourceSystemV1Entity = sourceSystemV1Dao.getEntityWithSourceSystem(edmMaterialGlobalV1Entity.getSourceSystem());

        if (sourceSystemV1Entity == null) {
            FailData failData = new FailData();
            failData.setErrorCode(IConstant.FAILED.ERROR_CODE.J1);
            failData.setErrorValue("sourceSystem / dpParent code / channel / countryCode do not exist");
            failData.setFunctionalArea(IConstant.FAILED.FUNCTIONAL_AREA.DP);
            failData.setInterfaceID(IConstant.FAILED.INTERFACE_ID.OMP_GDM_CONVERSION_STORAGE);
            failData.setSourceSystem(IConstant.VALUE.OMP);
            failData.setKey1(cnsDpPriceEntity.getLocalMaterialNumber());
            failData.setKey2(cnsDpPriceEntity.getSourceSystem());
            failData.setKey3(cnsDpPriceEntity.getCurrency());
            failData.setKey4(cnsDpPriceEntity.getCountry());
            failData.setKey5(cnsDpPriceEntity.getFromDate());
            ResultObject resultObject = new ResultObject();
            resultObject.setFailData(failData);
            resultObjectList.add(resultObject);
            return resultObjectList;
        }
        List<EDMCountryEntity> edmCountryEntityList = countryV1Dao.getEntityWithLocalCountryList(cnsDpPriceEntity.getCountry());
        if (edmCountryEntityList == null || edmCountryEntityList.size() < 1) {
            FailData failData = new FailData();
            failData.setErrorCode(IConstant.FAILED.ERROR_CODE.J1);
            failData.setErrorValue("sourceSystem / dpParent code / channel / countryCode do not exist");
            failData.setFunctionalArea(IConstant.FAILED.FUNCTIONAL_AREA.DP);
            failData.setInterfaceID(IConstant.FAILED.INTERFACE_ID.OMP_GDM_CONVERSION_STORAGE);
            failData.setSourceSystem(IConstant.VALUE.OMP);
            failData.setKey1(cnsDpPriceEntity.getLocalMaterialNumber());
            failData.setKey2(cnsDpPriceEntity.getSourceSystem());
            failData.setKey3(cnsDpPriceEntity.getCurrency());
            failData.setKey4(cnsDpPriceEntity.getCountry());
            failData.setKey5(cnsDpPriceEntity.getFromDate());
            ResultObject resultObject = new ResultObject();
            resultObject.setFailData(failData);
            resultObjectList.add(resultObject);
            return resultObjectList;
        }
        List<PlanCnsCustChannelEntity> planCnsCustChannelEntities = null;
        for (EDMCountryEntity edmCountryEntity : edmCountryEntityList) {
            if (edmCountryEntity != null) {

                String countryCode = edmCountryEntity.getCountryCode();
                if (StringUtils.isEmpty(countryCode)) {
                    FailData failData = new FailData();
                    failData.setErrorCode(IConstant.FAILED.ERROR_CODE.J1);
                    failData.setErrorValue("sourceSystem / dpParent code / channel / countryCode do not exist");
                    failData.setFunctionalArea(IConstant.FAILED.FUNCTIONAL_AREA.DP);
                    failData.setInterfaceID(IConstant.FAILED.INTERFACE_ID.OMP_GDM_CONVERSION_STORAGE);
                    failData.setSourceSystem(IConstant.VALUE.OMP);
                    failData.setKey1(cnsDpPriceEntity.getLocalMaterialNumber());
                    failData.setKey2(cnsDpPriceEntity.getSourceSystem());
                    failData.setKey3(cnsDpPriceEntity.getCurrency());
                    failData.setKey4(cnsDpPriceEntity.getCountry());
                    failData.setKey5(cnsDpPriceEntity.getFromDate());
                    ResultObject resultObject = new ResultObject();
                    resultObject.setFailData(failData);
                    resultObjectList.add(resultObject);
                    return resultObjectList;
                }
                planCnsCustChannelEntities = cnsCustChannelDao.getEntitiesWithStartCharInSalesOrg(edmCountryEntity.getCountryCode());
                if (planCnsCustChannelEntities.isEmpty()) {
                    FailData failData = new FailData();
                    failData.setErrorCode(IConstant.FAILED.ERROR_CODE.J1);
                    failData.setErrorValue("sourceSystem / dpParent code / channel / countryCode do not exist");
                    failData.setFunctionalArea(IConstant.FAILED.FUNCTIONAL_AREA.DP);
                    failData.setInterfaceID(IConstant.FAILED.INTERFACE_ID.OMP_GDM_CONVERSION_STORAGE);
                    failData.setSourceSystem(IConstant.VALUE.OMP);
                    failData.setKey1(cnsDpPriceEntity.getLocalMaterialNumber());
                    failData.setKey2(cnsDpPriceEntity.getSourceSystem());
                    failData.setKey3(cnsDpPriceEntity.getCurrency());
                    failData.setKey4(cnsDpPriceEntity.getCountry());
                    failData.setKey5(cnsDpPriceEntity.getFromDate());
                    ResultObject resultObject = new ResultObject();
                    resultObject.setFailData(failData);
                    resultObjectList.add(resultObject);
                    return resultObjectList;
                }

                for (PlanCnsCustChannelEntity pccc : planCnsCustChannelEntities) {

                    //C1
                    String aggregationId = sourceSystemV1Entity.getSourceSystem() + "-" + edmMaterialGlobalV1Entity.getLocalDpParentCode() + "-" + pccc.getChannel() + "-" + edmCountryEntity.getCountryCode();

                    OMPGdmConversionStorageBo gdmConversionStorageBo = new OMPGdmConversionStorageBo();

                    gdmConversionStorageBo.setSourceSystem(sourceSystemV1Entity.getSourceSystem());

                    gdmConversionStorageBo.setAggregationId(aggregationId);

                    gdmConversionStorageBo.setCurrencyId(cnsDpPriceEntity.getCurrency());

                    //C2
                    Date startDate = DateUtils.stringToDate(cnsDpPriceEntity.getFromDate(), DateUtils.J_yyyyWW);
                    gdmConversionStorageBo.setDueDate(DateUtils.dateToString(DateUtils.offsetDate(startDate, 7), DateUtils.F_yyyyMMddHHmmss_));
                    // C3
                    gdmConversionStorageBo.setFromDueDate(DateUtils.dateToString(startDate, DateUtils.F_yyyyMMddHHmmss_));

                    // C4
                    gdmConversionStorageBo.setForecastUploadId(aggregationId + "-" + gdmConversionStorageBo.getDueDate());
                    //C5
                    gdmConversionStorageBo.setSalesPrice(getFieldWithC5(cnsDpPriceEntity.getLocalMaterialNumber()));
                    // D3
                    gdmConversionStorageBo.setUnitId("");

                    ResultObject resultObject = new ResultObject();
                    resultObject.setBaseBo(gdmConversionStorageBo);
                    resultObjectList.add(resultObject);
                }
            }
        }
        return resultObjectList;
    }


    private String getFieldWithC5(String localMaterialNumber) {

        String dpParentCode = null;
        // get corresponding dpParentCode
        EDMMaterialGlobalV1Entity materialGlobalV1Entity = materialGlobalV1Dao.getEntityWithLocalMaterialNumber(localMaterialNumber);
        if (null != materialGlobalV1Entity) {
            dpParentCode = materialGlobalV1Entity.getLocalDpParentCode();
            if (StringUtils.isNotEmpty(dpParentCode)) {
                // get all the corresponding localMaterialNumbers accroding to the dpParentCode
                List<EDMMaterialGlobalV1Entity> materialGlobalV1Entities = materialGlobalV1Dao.getEntitiesWithLocalDpParentCode(dpParentCode);
                List<String> localMaterialNumbers = materialGlobalV1Entities.stream().map(EDMMaterialGlobalV1Entity::getLocalMaterialNumber).collect(Collectors.toList());
                // get all the salesPrice
                List<PlanCnsDpPriceEntity> cnsDpPriceEntities = cnsDpPriceDao.getEntitiesWithLocalMaterialNumbers(localMaterialNumbers);
                List<Double> salesPrices = cnsDpPriceEntities.stream().map(entity -> Double.parseDouble(entity.getSalesPrice())).collect(Collectors.toList());
                // calculate the avg
                Double avg = salesPrices.stream().filter(price -> price != 0).mapToDouble(price -> price).summaryStatistics().getAverage();
                return avg + "";
            } else {
                return null;
            }
        } else {
            return null;
        }
    }


}