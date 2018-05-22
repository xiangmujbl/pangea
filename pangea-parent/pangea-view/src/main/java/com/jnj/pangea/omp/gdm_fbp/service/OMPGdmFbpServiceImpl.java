package com.jnj.pangea.omp.gdm_fbp.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.*;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanParameterDaoImpl;
import com.jnj.pangea.common.entity.edm.*;
import com.jnj.pangea.common.entity.plan.PlanCnsFinPlanQtyEntity;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsFinPlanQtyDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsFinPlanValEntity;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsFinPlanValDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanParameterEntity;
import com.jnj.pangea.omp.gdm_fbp.bo.OMPGdmFbpBo;
import com.jnj.pangea.util.DateUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.*;

public class OMPGdmFbpServiceImpl {

    private static OMPGdmFbpServiceImpl instance;

    public static OMPGdmFbpServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmFbpServiceImpl();
        }
        return instance;
    }

    private PlanCnsFinPlanQtyDaoImpl cnsFinPlanQtyDao = PlanCnsFinPlanQtyDaoImpl.getInstance();
    //private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    private PlanCnsFinPlanValDaoImpl cnsFinPlanValDao = PlanCnsFinPlanValDaoImpl.getInstance();
    private EDMMaterialAuomV1DaoImpl materialAuomV1Dao = EDMMaterialAuomV1DaoImpl.getInstance();
    private EDMCountryV1DaoImpl countryV1Dao = EDMCountryV1DaoImpl.getInstance();
    private EDMCurrencyV1DaoImpl currencyV1Dao = EDMCurrencyV1DaoImpl.getInstance();
    private PlanCnsPlanParameterDaoImpl planCnsPlanParameterDao = PlanCnsPlanParameterDaoImpl.getInstance();
    private EDMJNJCalendarV1DaoImpl edmjnjCalendarV1Dao = EDMJNJCalendarV1DaoImpl.getInstance();

    public List<ResultObject> buildView(String key, Object o, Object o2) {
        List<ResultObject> resultObjects = new LinkedList<>();

        EDMMaterialGlobalV1Entity materialGlobalV1Entity = (EDMMaterialGlobalV1Entity) o;
        if (null==materialGlobalV1Entity){
            return resultObjects;
        }
        List<OMPGdmFbpBo> gdmfbpBos = new ArrayList<>();

//        String sourceSystem = materialGlobalV1Entity.getSourceSystem();
//        EDMSourceSystemV1Entity sourceSystemV1Entity = sourceSystemV1Dao.getEntityWithLocalSourceSystem(sourceSystem);
//
//        String localMaterialNumber = materialGlobalV1Entity.getLocalMaterialNumber();
//        PlanCnsFinPlanQtyEntity finPlanQtyEntity = cnsFinPlanQtyDao.getEntityWithConditions(localMaterialNumber,IConstant.VALUE.fbp);
//        PlanCnsFinPlanValEntity finPlanValEntity = cnsFinPlanValDao.getEntityWithConditions(localMaterialNumber,IConstant.VALUE.fbp);
//
//        String productId = sourceSystemV1Entity.getSourceSystem() + IConstant.VALUE.UNDERLINE + materialGlobalV1Entity.getLocalDpParentCode();
//        gdmfbpBo.setProductId(productId);
//
//        String sequeceNumber = UUID.randomUUID().toString();
//        String fbpId = productId +sequeceNumber;
//        gdmfbpBo.setfbpId(fbpId);

//        if (null != finPlanValEntity) {
//            gdmfbpBo.setValue(finPlanValEntity.getValue());
//        }
//
//        if (null != finPlanQtyEntity) {
//            String country = finPlanQtyEntity.getCountry();
//            EDMCountryEntity countryV1Entity = countryV1Dao.getEntityWithLocalCountry(country);
//            if (null != countryV1Entity) {
//                gdmfbpBo.setCountryId(countryV1Entity.getCountryCode());
//            }
//
//            String currency = finPlanQtyEntity.getCurrency();
//            EDMCurrencyV1Entity currencyV1Entity = currencyV1Dao.getEntityWithLocalCurrency(currency);
//            if (null != currencyV1Entity) {
//                gdmfbpBo.setCurrencyId(currencyV1Entity.getCurrencyCode());
//            }
//
//            String unitId = finPlanQtyEntity.getUnitId();
//            if (null != unitId && unitId.equals(materialGlobalV1Entity.getLocalBaseUom())) {
//                EDMMaterialAuomV1Entity materialAuomV1Entity = materialAuomV1Dao.getEntityWithConditions(localMaterialNumber, unitId);
//                if (null != materialAuomV1Entity) {
//                    try {
//                        int quantity = Integer.parseInt(finPlanQtyEntity.getQuantity());
//                        int localNumerator = Integer.parseInt(materialAuomV1Entity.getLocalNumerator());
//                        int localDenominator = Integer.parseInt(materialAuomV1Entity.getLocalDenominator());
//                        if (0!=quantity && 0!=localNumerator && 0!=localDenominator){
//                            String volume = quantity*(localNumerator/localDenominator)+"";
//                            gdmfbpBo.setVolume(volume);
//                        }
//                    } catch (NumberFormatException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//
        String sourceSystem = materialGlobalV1Entity.getSourceSystem();
        PlanCnsPlanParameterEntity planCnsPlanParameterEntity = planCnsPlanParameterDao.getEntityWithSourceSystemAndDataObject(sourceSystem,IConstant.VALUE.SEND_TO_OMP);
        String localMaterialNumber = materialGlobalV1Entity.getLocalMaterialNumber();
        PlanCnsFinPlanQtyEntity finPlanQtyEntity = cnsFinPlanQtyDao.getEntityWithConditions(localMaterialNumber,IConstant.VALUE.FBP);
        PlanCnsFinPlanValEntity finPlanValEntity = cnsFinPlanValDao.getEntityWithConditions(localMaterialNumber,IConstant.VALUE.FBP);
        LogUtil.getCoreLog().info("=======111111111111111===="+sourceSystem );






        //rules  T1
        String localDpParentCode = materialGlobalV1Entity.getLocalDpParentCode();
        if (StringUtils.isEmpty(localDpParentCode)){
            return resultObjects;
        }
        String productId= null;
        if (null!=planCnsPlanParameterEntity && null!=materialGlobalV1Entity)
         productId =planCnsPlanParameterEntity.getParameterValue()+IConstant.VALUE.UNDERLINE+materialGlobalV1Entity.getLocalDpParentCode();
        LogUtil.getCoreLog().info("=======22222222222222===="+localMaterialNumber );
        String yearMonth =null;

        LogUtil.getCoreLog().info("6666666666666666666666"+localDpParentCode );

            LogUtil.getCoreLog().info("=======77777777777777===="+finPlanQtyEntity+"=======7777777777777======="+ finPlanValEntity+"=========7777777777777777========="+planCnsPlanParameterEntity);
            if (null!=finPlanQtyEntity && null!=planCnsPlanParameterEntity && null!=finPlanValEntity){
                //List<PlanCnsPlanParameterEntity> planCnsPlanParameterEntity1 = planCnsPlanParameterDao.getEntityWithAttributeList(sourceSystem);
                PlanCnsPlanParameterEntity planCnsPlanParameterEntity1 = planCnsPlanParameterDao.getEntityWithAttribute(sourceSystem);
                LogUtil.getCoreLog().info("=======@@@@@@@===="+planCnsPlanParameterEntity1 );
                if (null!=planCnsPlanParameterEntity1){
                    yearMonth=finPlanQtyEntity.getYearMonth();
                }else if (null!=finPlanQtyEntity && null!=planCnsPlanParameterEntity){
                    yearMonth=finPlanQtyEntity.getYearMonth();
                }else if (null!=planCnsPlanParameterEntity &&null!=finPlanValEntity){
                    yearMonth=finPlanValEntity.getYearMonth();
                }
            }
            LogUtil.getCoreLog().info("=======8888888888888888===="+yearMonth );

        LogUtil.getCoreLog().info("=======3333333333====" );
        //rules  T5
            if (null!=yearMonth){
                LogUtil.getCoreLog().info("=======4444444444====" +yearMonth);
                List<EDMJNJCalendarV1Entity> edmjnjCalendarV1Entities = edmjnjCalendarV1Dao.getEntityWithFiscalPeriod(yearMonth);
                if (null!=edmjnjCalendarV1Entities && edmjnjCalendarV1Entities.size()>0) {
                    for (EDMJNJCalendarV1Entity entity:edmjnjCalendarV1Entities) {
                        LogUtil.getCoreLog().info("++++++++++++++++" + entity);
                        OMPGdmFbpBo gdmfbpBo = new OMPGdmFbpBo();
                        Date dueDate = DateUtils.stringToDate(entity.getWeekToDate(), DateUtils.DATE_FORMAT_1);
                        gdmfbpBo.setDueDate(DateUtils.dateToString(dueDate, DateUtils.J_yyyyMMdd_HHmmss));

                        Date weekFromDate = DateUtils.stringToDate(entity.getWeekFromDate(), DateUtils.DATE_FORMAT_1);
                        gdmfbpBo.setFromDueDate(DateUtils.dateToString(weekFromDate, DateUtils.J_yyyyMMdd_HHmmss));

                        gdmfbpBo.setfbpId(productId + "-" + gdmfbpBo.getFromDueDate());

                        //rules T7,T8
                        gdmfbpBo.setValue(entity.getNoOfWeek());

                        if (null != finPlanQtyEntity) {
                            String localMaterialNumberQty = finPlanQtyEntity.getLocalMaterialNumber();
                            String unitIdQty = finPlanQtyEntity.getUnitId();
                            String sourceSystemQty = finPlanQtyEntity.getSourceSystem();
                            EDMMaterialAuomV1Entity edmMaterialAuomV1Entity = materialAuomV1Dao.getEntityWithLocalMaterialNumber(localMaterialNumberQty, unitIdQty, sourceSystemQty);

                        if (null != edmMaterialAuomV1Entity) {
                            LogUtil.getCoreLog().info("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                            //gdmfbpBo.setVolume(entity.getNoOfWeek());
                            try {
                                double quantity = Double.parseDouble(finPlanQtyEntity.getQuantity());
                                double localNumerator = Double.parseDouble(edmMaterialAuomV1Entity.getLocalNumerator());
                                double localDenominator = Double.parseDouble(edmMaterialAuomV1Entity.getLocalDenominator());
                                if (0.0 != quantity && 0 != localNumerator && 0 != localDenominator) {
                                    double result = quantity * (localNumerator / localDenominator);
                                    BigDecimal a = new BigDecimal(result);
                                    // BigDecimal b = new BigDecimal(localNumerator);
                                    // BigDecimal c = new BigDecimal(localDenominator);
                                    LogUtil.getCoreLog().info("CCCCCCCCCCCCCCCCCCCCCCC" + a);
//                                    BigDecimal v = b.divide(c);
//                                    LogUtil.getCoreLog().info("VVVVVVVVVVVVVVVVVVVV"+v);
//                                    BigDecimal n = a.multiply(v);
//                                    LogUtil.getCoreLog().info("NNNNNNNNNNNNNNNNNNNN"+n);
                                    // a.multiply(b.divide(c));
                                    double df = a.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
                                    String volume = df + "";
                                    gdmfbpBo.setVolume(volume);
                                    LogUtil.getCoreLog().info(quantity + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" + localNumerator + "BBBBBBBBB" + localDenominator);
                                }
                            } catch (NumberFormatException e) {
                                e.printStackTrace();

                            }
                        }
                    }
                        LogUtil.getCoreLog().info(entity.getNoOfWeek()+"!!!!!!!!!!!!!!!)))))))" +entity.getNoOfWeek());
                        gdmfbpBos.add(gdmfbpBo);
                    }
                }
            }

        for (OMPGdmFbpBo fbp:gdmfbpBos) {
            ResultObject resultObject = new ResultObject();
            //rules  T2,T3
            if (null != finPlanQtyEntity) {
                String country = finPlanQtyEntity.getCountry();
                EDMCountryEntity countryV1Entity = countryV1Dao.getEntityWithLocalCountry(country);
                if (null != countryV1Entity) {
                    fbp.setCountryId(countryV1Entity.getLocalCountry());
                }
            }
            if (null != finPlanValEntity) {
                String currency = finPlanValEntity.getCurrency();
                EDMCurrencyV1Entity currencyV1Entity = currencyV1Dao.getEntityWithLocalCurrency(currency);
                if (null != currencyV1Entity) {
                    fbp.setCurrencyId(currencyV1Entity.getLocalCurrency());
                }
            }
            if (null != planCnsPlanParameterEntity && null != materialGlobalV1Entity){
                fbp.setProductId(planCnsPlanParameterEntity.getParameterValue() + IConstant.VALUE.UNDERLINE + materialGlobalV1Entity.getLocalDpParentCode());
        }
            resultObject.setBaseBo(fbp);
           resultObjects.add(resultObject);
        }
        return resultObjects;
    }
}
