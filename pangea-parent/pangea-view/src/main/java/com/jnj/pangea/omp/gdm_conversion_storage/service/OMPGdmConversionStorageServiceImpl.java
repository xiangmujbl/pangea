package com.jnj.pangea.omp.gdm_conversion_storage.service;

import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMCountryV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMJNJCalendarV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialGlobalV1DaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsCustChannelDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsDpPriceDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanUnitDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMCountryEntity;
import com.jnj.pangea.common.entity.edm.EDMJNJCalendarV1Entity;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.entity.plan.PlanCnsCustChannelEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsDpPriceEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanUnitEntity;
import com.jnj.pangea.omp.gdm_conversion_storage.bo.OMPGdmConversionStorageBo;
import com.jnj.pangea.util.DateUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
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

    private EDMCountryV1DaoImpl countryV1Dao = EDMCountryV1DaoImpl.getInstance();
    private EDMMaterialGlobalV1DaoImpl materialGlobalV1Dao = EDMMaterialGlobalV1DaoImpl.getInstance();
    private PlanCnsCustChannelDaoImpl cnsCustChannelDao = PlanCnsCustChannelDaoImpl.getInstance();
    private PlanCnsDpPriceDaoImpl cnsDpPriceDao = PlanCnsDpPriceDaoImpl.getInstance();
    private EDMJNJCalendarV1DaoImpl edmJNJCalendarV1Dao = EDMJNJCalendarV1DaoImpl.getInstance();
    private PlanCnsPlanUnitDaoImpl planCnsPlanUnitDao = PlanCnsPlanUnitDaoImpl.getInstance();

    public List<ResultObject> buildView(String key, Object o, Object o2) {
        List<ResultObject> resultObjectList = new ArrayList<ResultObject>();
        PlanCnsDpPriceEntity cnsDpPriceEntity = (PlanCnsDpPriceEntity) o;
        EDMMaterialGlobalV1Entity edmMaterialGlobalV1Entity = materialGlobalV1Dao.getEntityWithLocalMaterialNumberAndSourceSystem(cnsDpPriceEntity.getLocalMaterialNumber(), cnsDpPriceEntity.getSourceSystem());
        //cns_dp_price-localMaterialNumber = material_global_v1-localMaterialNumber and
        // cns_dp_price-sourceSystem = material_global_v1-sourceSystem
        if (edmMaterialGlobalV1Entity == null) {
            String errorValue = "sourceSystem / dpParent code / channel / countryCode do not exist";
            ResultObject resultObject = getFailDate(IConstant.FAILED.ERROR_CODE.J1, errorValue, cnsDpPriceEntity);
            resultObjectList.add(resultObject);
            return resultObjectList;
        }
        if (StringUtils.isBlank(edmMaterialGlobalV1Entity.getLocalDpParentCode())) {
            String errorValue = "sourceSystem / dpParent code / channel / countryCode do not exist";
            ResultObject resultObject = getFailDate(IConstant.FAILED.ERROR_CODE.J1, errorValue, cnsDpPriceEntity);
            resultObjectList.add(resultObject);
            return resultObjectList;
        }

        List<EDMJNJCalendarV1Entity> edmJNJCalendarV1EntityList = edmJNJCalendarV1Dao.getEntityWithFiscalPeriod(cnsDpPriceEntity.getFromDate());
        if (edmJNJCalendarV1EntityList != null && edmJNJCalendarV1EntityList.size() > 0) {

            for (EDMJNJCalendarV1Entity edmjnjCalendarV1Entity : edmJNJCalendarV1EntityList) {
                //J1
                //source_system_v1 -localSourceSystem = material_global_v1-sourceSystem
                //cn_dp_price-country =  country_v1-localCountry
                List<EDMCountryEntity> edmCountryEntityList = countryV1Dao.getEntityWithLocalCountryList(cnsDpPriceEntity.getCountry());
                if (edmCountryEntityList == null || edmCountryEntityList.size() == 0) {
                    String errorValue = "sourceSystem / dpParent code / channel / countryCode do not exist";
                    ResultObject resultObject = getFailDate(IConstant.FAILED.ERROR_CODE.J1, errorValue, cnsDpPriceEntity);
                    resultObjectList.add(resultObject);
                    continue;
                }
                List<PlanCnsCustChannelEntity> planCnsCustChannelEntities = null;

                for (EDMCountryEntity edmCountryEntity : edmCountryEntityList) {
                    if (edmCountryEntity != null) {
                        String countryCode = edmCountryEntity.getCountryCode();
                        if (StringUtils.isEmpty(countryCode)) {
                            String errorValue = "sourceSystem / dpParent code / channel / countryCode do not exist";
                            ResultObject resultObject = getFailDate(IConstant.FAILED.ERROR_CODE.J1, errorValue, cnsDpPriceEntity);
                            resultObjectList.add(resultObject);
                            continue;
                        }
                        planCnsCustChannelEntities = cnsCustChannelDao.getEntitiesWithStartCharInSalesOrg(countryCode);
                        if (planCnsCustChannelEntities.isEmpty()) {
                            String errorValue = "sourceSystem / dpParent code / channel / countryCode do not exist";
                            ResultObject resultObject = getFailDate(IConstant.FAILED.ERROR_CODE.J1, errorValue, cnsDpPriceEntity);
                            resultObjectList.add(resultObject);
                            continue;
                        }

                        for (PlanCnsCustChannelEntity pccc : planCnsCustChannelEntities) {
                            //C1
                            if (StringUtils.isBlank(pccc.getChannel())) {
                                String errorValue = "sourceSystem / dpParent code / channel / countryCode do not exist";
                                ResultObject resultObject = getFailDate(IConstant.FAILED.ERROR_CODE.J1, errorValue, cnsDpPriceEntity);
                                resultObjectList.add(resultObject);
                                continue;
                            }
                            OMPGdmConversionStorageBo gdmConversionStorageBo = new OMPGdmConversionStorageBo();
                            if (edmMaterialGlobalV1Entity != null && pccc != null && edmCountryEntity != null) {
                                String aggregationId = IConstant.VALUE.LA_ + edmMaterialGlobalV1Entity.getLocalDpParentCode() + "-" + pccc.getChannel() + "-" + edmCountryEntity.getCountryCode();
                                gdmConversionStorageBo.setAggregationId(aggregationId);
                                gdmConversionStorageBo.setCurrencyId(cnsDpPriceEntity.getCurrency());
                            }
                            //C2
                            Date dueDate = DateUtils.stringToDate(edmjnjCalendarV1Entity.getWeekToDate(), DateUtils.DATE_FORMAT_1);
                            gdmConversionStorageBo.setDueDate(DateUtils.dateToString(dueDate, DateUtils.J_yyyy_MM_dd_HHmmss));
                            // C3
                            Date fromDueDate = DateUtils.stringToDate(edmjnjCalendarV1Entity.getWeekFromDate(), DateUtils.DATE_FORMAT_1);
                            gdmConversionStorageBo.setFromDueDate(DateUtils.dateToString(fromDueDate, DateUtils.J_yyyy_MM_dd_HHmmss));
                            //C5
                            String salesPrice = cnsDpPriceEntity.getSalesPrice();
                            if (StringUtils.isBlank(salesPrice) || IConstant.VALUE.ZERO.equals(salesPrice)) {
                                continue;
                            } else if (StringUtils.isNotBlank(salesPrice) && !IConstant.VALUE.ZERO.equals(salesPrice) && isMathC5(salesPrice)) {
                                gdmConversionStorageBo.setValue(getFieldWithC5(cnsDpPriceEntity.getLocalMaterialNumber()));
                            } else if (!isMathC5(salesPrice)) {
                                String errorValue = "Sales price is non-numeric";
                                ResultObject resultObject = getFailDate(IConstant.FAILED.ERROR_CODE.C5, errorValue, cnsDpPriceEntity);
                                resultObjectList.add(resultObject);
                                continue;
                            }
                            //D1
                            gdmConversionStorageBo.setConversionFactor(IConstant.VALUE.SALESPRICE);
                            // D3
                            gdmConversionStorageBo.setUnitId(getFieldWithC6(edmMaterialGlobalV1Entity.getLocalBaseUom()));
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
                List<Double> priceList = new ArrayList<>();
                for (PlanCnsDpPriceEntity planCnsDpPriceEntity : cnsDpPriceEntities) {
                    if (StringUtils.isNotBlank(planCnsDpPriceEntity.getSalesPrice())) {
                        priceList.add(Double.parseDouble(planCnsDpPriceEntity.getSalesPrice()));
                    }
                }
                // calculate the avg
                Double avg = priceList.stream().filter(price -> price != 0).mapToDouble(price -> price).summaryStatistics().getAverage();
                BigDecimal b = new BigDecimal(avg);
                double df = b.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
                return df + "";
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    private String getFieldWithC6(String localBaseUOM) {
        if (StringUtils.isNotEmpty(localBaseUOM)) {
            PlanCnsPlanUnitEntity planUnitEntity = planCnsPlanUnitDao.getCnsPlanUnitEntityWithLocalUom(localBaseUOM);
            if (null != planUnitEntity) {
                return planUnitEntity.getUnit();
            }
        }
        return "";
    }

    public static boolean isMathC5(String s) {
        if (IConstant.VALUE.ZERO.equals(s) || StringUtils.isBlank(s)) {
            return true;
        } else {
            String regex = "^[1-9][0-9]*\\.[0-9]+$|^[1-9][0-9]*$|^0+\\.[0-9]+$";
            char c = s.charAt(0);
            boolean bool;
            if (c == '+' || c == '-') {
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

    public ResultObject getFailDate(String errorCode, String errorValue, PlanCnsDpPriceEntity cnsDpPriceEntity) {
        ResultObject resultObject = new ResultObject();
        FailData failData = new FailData();
        failData.setErrorCode(errorCode);
        failData.setErrorValue(errorValue);
        failData.setFunctionalArea(IConstant.FAILED.FUNCTIONAL_AREA.DP);
        failData.setInterfaceID(IConstant.FAILED.INTERFACE_ID.OMP_GDM_CONVERSION_STORAGE);
        failData.setSourceSystem(IConstant.VALUE.OMP);
        failData.setKey1(cnsDpPriceEntity.getLocalMaterialNumber());
        failData.setKey2(cnsDpPriceEntity.getSourceSystem());
        failData.setKey3(cnsDpPriceEntity.getCurrency());
        failData.setKey4(cnsDpPriceEntity.getCountry());
        failData.setKey5(cnsDpPriceEntity.getFromDate());
        resultObject.setFailData(failData);
        return resultObject;
    }
}