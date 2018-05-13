package com.jnj.pangea.omp.gdm_conversion_storage.service;

import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMCountryV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMJNJCalendarV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialGlobalV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsCustChannelDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsDpPriceDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMCountryEntity;
import com.jnj.pangea.common.entity.edm.EDMJNJCalendarV1Entity;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.entity.plan.PlanCnsCustChannelEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsDpPriceEntity;
import com.jnj.pangea.omp.gdm_conversion_storage.bo.OMPGdmConversionStorageBo;
import com.jnj.pangea.util.DateUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormat;
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
    private EDMJNJCalendarV1DaoImpl edmJNJCalendarV1Dao = EDMJNJCalendarV1DaoImpl.getInstance();

    public List<ResultObject> buildView(String key, Object o, Object o2) {
        List<ResultObject> resultObjectList = new ArrayList<ResultObject>();
        PlanCnsDpPriceEntity cnsDpPriceEntity = (PlanCnsDpPriceEntity) o;
        EDMMaterialGlobalV1Entity edmMaterialGlobalV1Entity = materialGlobalV1Dao.getEntityWithLocalMaterialNumberAndSourceSystem(cnsDpPriceEntity.getLocalMaterialNumber(), cnsDpPriceEntity.getSourceSystem());
        List<EDMJNJCalendarV1Entity> edmJNJCalendarV1EntityList = edmJNJCalendarV1Dao.getEntityWithFiscalPeriod(cnsDpPriceEntity.getFromDate());
        if (edmJNJCalendarV1EntityList != null && edmJNJCalendarV1EntityList.size() > 0) {
            for (EDMJNJCalendarV1Entity edmjnjCalendarV1Entity : edmJNJCalendarV1EntityList) {
                List<EDMCountryEntity> edmCountryEntityList = countryV1Dao.getEntityWithLocalCountryList(cnsDpPriceEntity.getCountry());
                List<PlanCnsCustChannelEntity> planCnsCustChannelEntities = null;
                for (EDMCountryEntity edmCountryEntity : edmCountryEntityList) {
                    if (edmCountryEntity != null) {
                        planCnsCustChannelEntities = cnsCustChannelDao.getEntitiesWithStartCharInSalesOrg(edmCountryEntity.getCountryCode());
                        for (PlanCnsCustChannelEntity pccc : planCnsCustChannelEntities) {
                            //C1
                            OMPGdmConversionStorageBo gdmConversionStorageBo = new OMPGdmConversionStorageBo();
                            if (edmMaterialGlobalV1Entity != null) {
                                String aggregationId = IConstant.VALUE.LA_ + edmMaterialGlobalV1Entity.getLocalDpParentCode() + "-" + pccc.getChannel() + "-" + edmCountryEntity.getCountryCode();
                                gdmConversionStorageBo.setAggregationId(aggregationId);
                            }
                            gdmConversionStorageBo.setCurrencyId(cnsDpPriceEntity.getCurrency());
                            //C2
                            Date dueDate = DateUtils.stringToDate(edmjnjCalendarV1Entity.getWeekToDate(), DateUtils.DATE_FORMAT_1);
                            gdmConversionStorageBo.setDueDate(DateUtils.dateToString(dueDate, DateUtils.J_yyyyMMdd_HHmmss));
                            // C3
                            Date fromDueDate = DateUtils.stringToDate(edmjnjCalendarV1Entity.getWeekFromDate(), DateUtils.DATE_FORMAT_1);
                            gdmConversionStorageBo.setFromDueDate(DateUtils.dateToString(fromDueDate, DateUtils.J_yyyyMMdd_HHmmss));
                            //C5
                            String salesPrice = cnsDpPriceEntity.getSalesPrice();
                            if (StringUtils.isNotBlank(salesPrice) && !IConstant.VALUE.ZERO.equals(salesPrice)) {
                                gdmConversionStorageBo.setValue(getFieldWithC5(cnsDpPriceEntity.getLocalMaterialNumber()));
                            } else if (!isMathC5(salesPrice)) {
                                FailData failData = new FailData();
                                failData.setErrorCode(IConstant.FAILED.ERROR_CODE.C5);
                                failData.setErrorValue("Sales price is non-numeric");
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
                            //D1
                            gdmConversionStorageBo.setConversionFactor(IConstant.VALUE.SALESPRICE);
                            // D3
                            gdmConversionStorageBo.setUnitId("");
                            ResultObject resultObject = new ResultObject();
                            resultObject.setBaseBo(gdmConversionStorageBo);
                            resultObjectList.add(resultObject);
                        }
                    }
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
                List<Double> salesPrices = cnsDpPriceEntities.stream().map(entity -> {
                    if (StringUtils.isNotEmpty(entity.getSalesPrice())) {
                        return Double.parseDouble(entity.getSalesPrice());
                    }
                    return 0.0;
                }).collect(Collectors.toList());
                // calculate the avg
                Double avg = salesPrices.stream().filter(price -> price != 0).mapToDouble(price -> price).summaryStatistics().getAverage();
                DecimalFormat Format = new DecimalFormat("#.000");
                return Double.valueOf(Format.format(avg)) + "";
                //return avg +"";
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public static boolean isMathC5(String s) {
        if (StringUtils.isBlank(s)) {
            return true;
        } else {
            String regex = "^[1-9][0-9]*\\.[0-9]+$|^[1-9][0-9]*$|^0+\\.[0-9]+$";
            char c = s.charAt(0);
            boolean bool;
            if (c == '+' | c == '-') {
                bool = s.substring(1).matches(regex);
            } else {
                bool = s.matches(regex);
            }
            if (bool) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static void main(String[] args) {
        System.out.print(isMathC5(null));
    }

}