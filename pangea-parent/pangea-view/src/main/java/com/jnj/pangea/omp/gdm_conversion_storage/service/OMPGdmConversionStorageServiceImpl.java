package com.jnj.pangea.omp.gdm_conversion_storage.service;

import com.jnj.adf.grid.utils.LogUtil;
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
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.entity.plan.PlanCnsCustChannelEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsDpPriceEntity;
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

//    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    private EDMCountryV1DaoImpl countryV1Dao = EDMCountryV1DaoImpl.getInstance();
    private EDMMaterialGlobalV1DaoImpl materialGlobalV1Dao = EDMMaterialGlobalV1DaoImpl.getInstance();
    private PlanCnsCustChannelDaoImpl cnsCustChannelDao = PlanCnsCustChannelDaoImpl.getInstance();
    private PlanCnsDpPriceDaoImpl cnsDpPriceDao = PlanCnsDpPriceDaoImpl.getInstance();
    private EDMJNJCalendarV1DaoImpl edmJNJCalendarV1Dao = EDMJNJCalendarV1DaoImpl.getInstance();

    public List<ResultObject> buildView(String key, Object o, Object o2) {
        List<ResultObject> resultObjectList = new ArrayList<ResultObject>();
        PlanCnsDpPriceEntity cnsDpPriceEntity = (PlanCnsDpPriceEntity) o;
        EDMMaterialGlobalV1Entity edmMaterialGlobalV1Entity = materialGlobalV1Dao.getEntityWithLocalMaterialNumberAndSourceSystem(cnsDpPriceEntity.getLocalMaterialNumber(), cnsDpPriceEntity.getSourceSystem());
        LogUtil.getCoreLog().info("RRRRRRRRRRRRRRRRRRRRRRRRR1   edmMaterialGlobalV1Entity=" + edmMaterialGlobalV1Entity);
        //cns_dp_price-localMaterialNumber = material_global_v1-localMaterialNumber and
        // cns_dp_price-sourceSystem = material_global_v1-sourceSystem
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
        if(StringUtils.isBlank(edmMaterialGlobalV1Entity.getLocalDpParentCode())){
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
        List<EDMJNJCalendarV1Entity> edmJNJCalendarV1EntityList = edmJNJCalendarV1Dao.getEntityWithFiscalPeriod(cnsDpPriceEntity.getFromDate());
        LogUtil.getCoreLog().info("RRRRRRRRRRRRRRRRRRRRRRRRRedmJNJCalendarV1EntityList=" + edmJNJCalendarV1EntityList.size());
        if (edmJNJCalendarV1EntityList != null && edmJNJCalendarV1EntityList.size() > 0) {
            for (EDMJNJCalendarV1Entity edmjnjCalendarV1Entity : edmJNJCalendarV1EntityList) {
                //J1
                //source_system_v1 -localSourceSystem = material_global_v1-sourceSystem
                //cn_dp_price-country =  country_v1-localCountry
                List<EDMCountryEntity> edmCountryEntityList = countryV1Dao.getEntityWithLocalCountryList(cnsDpPriceEntity.getCountry());
                LogUtil.getCoreLog().info("RRRRRRRRRRRRRRRRRRRRRRRRRedmCountryEntity=" + edmCountryEntityList.size());
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
                        LogUtil.getCoreLog().info("RRRRRRRRRRRRRRRRRRRRRRRRRplanCnsCustChannelEntities=" + planCnsCustChannelEntities.size());
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
                            if(StringUtils.isBlank(pccc.getChannel())){
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
                            OMPGdmConversionStorageBo gdmConversionStorageBo = new OMPGdmConversionStorageBo();
                            if (edmMaterialGlobalV1Entity != null && pccc != null && edmCountryEntity != null) {
                                String aggregationId = IConstant.VALUE.LA_ + edmMaterialGlobalV1Entity.getLocalDpParentCode() + "-" + pccc.getChannel() + "-" + edmCountryEntity.getCountryCode();
                                gdmConversionStorageBo.setAggregationId(aggregationId);
                                gdmConversionStorageBo.setCurrencyId(cnsDpPriceEntity.getCurrency());
                            }
                            //C2
                            Date dueDate = DateUtils.stringToDate(edmjnjCalendarV1Entity.getWeekToDate(), DateUtils.DATE_FORMAT_1);
                            gdmConversionStorageBo.setDueDate(DateUtils.dateToString(dueDate, DateUtils.J_yyyyMMdd_HHmmss));
                            // C3
                            Date fromDueDate = DateUtils.stringToDate(edmjnjCalendarV1Entity.getWeekFromDate(), DateUtils.DATE_FORMAT_1);
                            gdmConversionStorageBo.setFromDueDate(DateUtils.dateToString(fromDueDate, DateUtils.J_yyyyMMdd_HHmmss));
                            //C5
                            String salesPrice = cnsDpPriceEntity.getSalesPrice();
                            LogUtil.getCoreLog().info("RRRRRRRRRRRRRRRRRRRR===salesPrice" + salesPrice);
                            if (StringUtils.isBlank(salesPrice) || IConstant.VALUE.ZERO.equals(salesPrice)) {
                                return resultObjectList;
                            } else if (StringUtils.isNotBlank(salesPrice) && !IConstant.VALUE.ZERO.equals(salesPrice) && isMathC5(salesPrice)) {
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

    public static boolean isMathC5(String s) {
        if (IConstant.VALUE.ZERO.equals(s) || StringUtils.isBlank(s)) {
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
        System.out.print(isMathC5("1265.234b"));
    }

}