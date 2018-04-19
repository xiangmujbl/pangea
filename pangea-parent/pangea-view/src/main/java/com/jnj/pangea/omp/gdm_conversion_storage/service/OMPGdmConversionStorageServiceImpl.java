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

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class OMPGdmConversionStorageServiceImpl{

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

    public List<ResultObject> buildView(String key, Object o, Object o2) {

        List<ResultObject> resultObjectList = new ArrayList<ResultObject>();
        PlanCnsDpPriceEntity cnsDpPriceEntity = (PlanCnsDpPriceEntity) o;
        if (cnsDpPriceEntity==null){
            return  resultObjectList;
        }

        // J1
        EDMMaterialGlobalV1Entity edmMaterialGlobalV1Entity = materialGlobalV1Dao.getEntityWithLocalMaterialNumberAndSourceSystem(cnsDpPriceEntity.getLocalMaterialNumber(), cnsDpPriceEntity.getSourceSystem());
        if (edmMaterialGlobalV1Entity == null) {
            FailData failData = new FailData();
            failData.setErrorCode("J1");
            failData.setErrorValue("sourceSystem / dpParent code / channel / countryCode do not exist");
            failData.setFunctionalArea("DP");
            failData.setInterfaceID("OMPGdmConversionStorage");
            failData.setSourceSystem(IConstant.VALUE.OMP);
            failData.setKey1(cnsDpPriceEntity.getLocalMaterialNumber());
            failData.setKey2(cnsDpPriceEntity.getSourceSystem());
            failData.setKey3("");
            failData.setKey4("");
            failData.setKey5("");
            ResultObject resultObject=new ResultObject();
            resultObject.setFailData(failData);
            resultObjectList.add(resultObject);
            return resultObjectList;
        }
        EDMCountryEntity edmCountryEntity = countryV1Dao.getEntityWithLocalCountry(cnsDpPriceEntity.getCountry());
        if (edmCountryEntity == null) {
            FailData failData = new FailData();
            failData.setErrorCode("J1");
            failData.setErrorValue("sourceSystem / dpParent code / channel / countryCode do not exist");
            failData.setFunctionalArea("DP");
            failData.setInterfaceID("OMPGdmConversionStorage");
            failData.setSourceSystem(IConstant.VALUE.OMP);
            failData.setKey1(cnsDpPriceEntity.getLocalMaterialNumber());
            failData.setKey2(cnsDpPriceEntity.getSourceSystem());
            failData.setKey3("");
            failData.setKey4("");
            failData.setKey5("");
            ResultObject resultObject=new ResultObject();
            resultObject.setFailData(failData);
            resultObjectList.add(resultObject);
            return resultObjectList;
        }

        List<PlanCnsCustChannelEntity> planCnsCustChannelEntities = cnsCustChannelDao.getEntitiesWithStartCharInChannel(edmCountryEntity.getCountryCode());
        if (planCnsCustChannelEntities == null || planCnsCustChannelEntities.isEmpty()) {
            FailData failData = new FailData();
            failData.setErrorCode("J1");
            failData.setErrorValue("sourceSystem / dpParent code / channel / countryCode do not exist");
            failData.setFunctionalArea("DP");
            failData.setInterfaceID("OMPGdmConversionStorage");
            failData.setSourceSystem(IConstant.VALUE.OMP);
            failData.setKey1(cnsDpPriceEntity.getLocalMaterialNumber());
            failData.setKey2(cnsDpPriceEntity.getSourceSystem());
            failData.setKey3("");
            failData.setKey4("");
            failData.setKey5("");
            ResultObject resultObject=new ResultObject();
            resultObject.setFailData(failData);
            resultObjectList.add(resultObject);
            return resultObjectList;
        }
        EDMSourceSystemV1Entity sourceSystemV1Entity = sourceSystemV1Dao.getEntityWithLocalSourceSystem(edmMaterialGlobalV1Entity.getSourceSystem());
        if (sourceSystemV1Entity == null) {
            FailData failData = new FailData();
            failData.setErrorCode("J1");
            failData.setErrorValue("sourceSystem / dpParent code / channel / countryCode do not exist");
            failData.setFunctionalArea("DP");
            failData.setInterfaceID("OMPGdmConversionStorage");
            failData.setSourceSystem(IConstant.VALUE.OMP);
            failData.setKey1(cnsDpPriceEntity.getLocalMaterialNumber());
            failData.setKey2(cnsDpPriceEntity.getSourceSystem());
            failData.setKey3("");
            failData.setKey4("");
            failData.setKey5("");
            ResultObject resultObject=new ResultObject();
            resultObject.setFailData(failData);
            resultObjectList.add(resultObject);
            return resultObjectList;
        }


        for (PlanCnsCustChannelEntity pccc:planCnsCustChannelEntities) {
            //C1
            String aggregationId=sourceSystemV1Entity.getSourceSystem()+"-"+edmMaterialGlobalV1Entity.getLocalDpParentCode()+"-"+pccc.getChannel()+"-"+edmCountryEntity.getCountryCode();
            OMPGdmConversionStorageBo gdmConversionStorageBo = new OMPGdmConversionStorageBo();

            gdmConversionStorageBo.setSourceSystem(sourceSystemV1Entity.getLocalSourceSystem());
            gdmConversionStorageBo.setAggregationId(aggregationId);
            gdmConversionStorageBo.setCurrencyId(cnsDpPriceEntity.getCurrency());
            //C2
            Date fromDate = DateUtils.stringToDate(cnsDpPriceEntity.getFromDate(), DateUtils.F_yyyyMM);
            gdmConversionStorageBo.setDueDate(DateUtils.dateToString(DateUtils.endOfMonth(fromDate), DateUtils.dd_MM_yyyy_HHmmss));
            // C3
            gdmConversionStorageBo.setFromDueDate(DateUtils.dateToString(DateUtils.startOfMonth(fromDate), DateUtils.dd_MM_yyyy_HHmmss));
            // C4
            gdmConversionStorageBo.setForecastUploadId(aggregationId + "-" + gdmConversionStorageBo.getDueDate());
            //C5
            String cnsDpSalesPrice = cnsDpPriceEntity.getSalesPrice();
            String edmMaLocalDpParentCode = edmMaterialGlobalV1Entity.getLocalDpParentCode();
            if ("".equals(edmMaLocalDpParentCode)){
                return new ArrayList<ResultObject>();
            }
            if (cnsDpSalesPrice.isEmpty()){
                FailData failData = new FailData();
                failData.setErrorCode("C5");
                failData.setErrorValue("sales price do not exist");
                failData.setFunctionalArea("DP");
                failData.setInterfaceID("OMPGdmConversionStorage");
                failData.setSourceSystem(IConstant.VALUE.OMP);
                failData.setKey1(cnsDpPriceEntity.getLocalMaterialNumber());
                failData.setKey2(cnsDpPriceEntity.getSourceSystem());
                failData.setKey3("");
                failData.setKey4("");
                failData.setKey5("");
                ResultObject resultObject=new ResultObject();
                resultObject.setFailData(failData);
                resultObjectList.add(resultObject);
                return resultObjectList;
            }else if(cnsDpSalesPrice=="0"){
                continue;
            }else {
                BigDecimal bigCnsDpSalesPrice = new BigDecimal(cnsDpSalesPrice);
                BigDecimal bigEdmMaLocalDpParentCode = new BigDecimal(edmMaLocalDpParentCode);
                BigDecimal avgSalesPrice = bigCnsDpSalesPrice.add(bigEdmMaLocalDpParentCode).divide(new BigDecimal(2));
                gdmConversionStorageBo.setSalesPrice(avgSalesPrice.toString());
            }
            ResultObject resultObject=new ResultObject();
            resultObject.setBaseBo(gdmConversionStorageBo);
            resultObjectList.add(resultObject);
        }
        return resultObjectList;
    }


}
