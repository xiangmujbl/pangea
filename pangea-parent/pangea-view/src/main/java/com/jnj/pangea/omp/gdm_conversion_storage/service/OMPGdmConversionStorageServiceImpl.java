package com.jnj.pangea.omp.gdm_conversion_storage.service;

import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMCountryV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMCurrencyV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialGlobalV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsCustChannelDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsDpPriceDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMCountryEntity;
import com.jnj.pangea.common.entity.edm.EDMCurrencyV1Entity;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.entity.plan.PlanCnsCustChannelEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsDpPriceEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_conversion_storage.bo.OMPGdmConversionStorageBo;
import com.jnj.pangea.util.DateUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class OMPGdmConversionStorageServiceImpl implements ICommonService {

    private static OMPGdmConversionStorageServiceImpl instance;

    public static OMPGdmConversionStorageServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmConversionStorageServiceImpl();
        }
        return instance;
    }

    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    private EDMCurrencyV1DaoImpl currencyV1Dao = EDMCurrencyV1DaoImpl.getInstance();
    private EDMCountryV1DaoImpl countryV1Dao = EDMCountryV1DaoImpl.getInstance();
    private EDMMaterialGlobalV1DaoImpl materialGlobalV1Dao = EDMMaterialGlobalV1DaoImpl.getInstance();
    private PlanCnsCustChannelDaoImpl cnsCustChannelDao = PlanCnsCustChannelDaoImpl.getInstance();
    private PlanCnsDpPriceDaoImpl cnsDpPriceDao = PlanCnsDpPriceDaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        PlanCnsDpPriceEntity cnsDpPriceEntity = (PlanCnsDpPriceEntity) o;
        String aggregationId = (String) o2;

        OMPGdmConversionStorageBo gdmConversionStorageBo = new OMPGdmConversionStorageBo();

        // T1
        String sourceSystem = getFieldWithT1();
        if (null != sourceSystem) {
            gdmConversionStorageBo.setSourceSystem(sourceSystem);
        } else {
            resultObject.setFailData(new FailData());
            return resultObject;
        }

        // T2
        String currencyCode = getFieldWithT2(cnsDpPriceEntity.getCurrency());
        if (null != currencyCode) {
            gdmConversionStorageBo.setCurrencyId(currencyCode);
        } else {
            resultObject.setFailData(new FailData());
            return resultObject;
        }

        // C1
        gdmConversionStorageBo.setAggregationId(aggregationId);

        try {
            Date fromDate = DateUtils.stringToDate(cnsDpPriceEntity.getFromDate(), DateUtils.F_yyyyMM);
            // C2
            gdmConversionStorageBo.setDueDate(DateUtils.dateToString(DateUtils.endOfMonth(fromDate), DateUtils.dd_MM_yyyy_HHmmss));
            // C3
            gdmConversionStorageBo.setFromDueDate(DateUtils.dateToString(DateUtils.startOfMonth(fromDate), DateUtils.dd_MM_yyyy_HHmmss));
            // C4
            gdmConversionStorageBo.setForecastUploadId(aggregationId + "-" + gdmConversionStorageBo.getDueDate());
        } catch (Exception e) {
            resultObject.setFailData(new FailData());
            return resultObject;
        }

        // C5
        gdmConversionStorageBo.setSalesPrice(getFieldWithC5(cnsDpPriceEntity.getLocalMaterialNumber()));

        resultObject.setBaseBo(gdmConversionStorageBo);
        return resultObject;
    }

    private String getFieldWithT1() {

        EDMSourceSystemV1Entity sourceSystemEntity = sourceSystemV1Dao.getEntityWithLocalSourceSystem(IConstant.VALUE.PROJECT_ONE);
        if (null != sourceSystemEntity) {
            return sourceSystemEntity.getSourceSystem();
        }
        return null;
    }

    private String getFieldWithT2(String localCurrency) {

        EDMCurrencyV1Entity currencyV1Entity = currencyV1Dao.getEntityWithLocalCurrency(localCurrency);
        if (null != currencyV1Entity) {
            return currencyV1Entity.getCurrencyCode();
        }
        return null;
    }

    public List<String> getFieldWithC1(String localMaterialNumber, String localCountry) {

        List<String> result = new LinkedList<>();

        String dpParentCode = IConstant.VALUE.LA_;
        EDMMaterialGlobalV1Entity materialGlobalV1Entity = materialGlobalV1Dao.getEntityWithLocalMaterialNumber(localMaterialNumber);
        if (null != materialGlobalV1Entity) {
            dpParentCode += materialGlobalV1Entity.getLocalDpParentCode();
        } else {
            return Collections.emptyList();
        }

        String countryCode = null;
        EDMCountryEntity countryEntity = countryV1Dao.getEntityWithLocalCountry(localCountry);
        if (null != countryEntity) {
            countryCode = countryEntity.getCountryCode();

            List<PlanCnsCustChannelEntity> channelEntities = cnsCustChannelDao.getEntitiesWithStartCharInChannel(countryCode);
            if (!channelEntities.isEmpty()) {
                for (PlanCnsCustChannelEntity channelEntity : channelEntities) {
                    result.add(dpParentCode + "-" + channelEntity.getChannel() + "-" + countryCode);
                }
                return result;
            } else {
                return Collections.emptyList();
            }
        } else {
            return Collections.emptyList();
        }
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
                List<Long> salesPrices = cnsDpPriceEntities.stream().map(entity -> Long.parseLong(entity.getSalesPrice())).collect(Collectors.toList());
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
