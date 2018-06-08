package com.jnj.pangea.omp.gdm_fpb.service;

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
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_fpb.bo.OMPGdmFpbBo;
import com.jnj.pangea.util.DateUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class OMPGdmFpbServiceImpl implements ICommonService {

    private static OMPGdmFpbServiceImpl instance;

    public static OMPGdmFpbServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmFpbServiceImpl();
        }
        return instance;
    }

    private PlanCnsFinPlanQtyDaoImpl cnsFinPlanQtyDao = PlanCnsFinPlanQtyDaoImpl.getInstance();
    //private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    private PlanCnsFinPlanValDaoImpl cnsFinPlanValDao = PlanCnsFinPlanValDaoImpl.getInstance();
   // private EDMMaterialAuomV1DaoImpl materialAuomV1Dao = EDMMaterialAuomV1DaoImpl.getInstance();
    private EDMCountryV1DaoImpl countryV1Dao = EDMCountryV1DaoImpl.getInstance();
    private EDMCurrencyV1DaoImpl currencyV1Dao = EDMCurrencyV1DaoImpl.getInstance();
    private PlanCnsPlanParameterDaoImpl planCnsPlanParameterDao = PlanCnsPlanParameterDaoImpl.getInstance();
    private EDMJNJCalendarV1DaoImpl edmjnjCalendarV1Dao = EDMJNJCalendarV1DaoImpl.getInstance();
    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        EDMMaterialGlobalV1Entity materialGlobalV1Entity = (EDMMaterialGlobalV1Entity) o;

        OMPGdmFpbBo gdmFpbBo = new OMPGdmFpbBo();

//        String sourceSystem = materialGlobalV1Entity.getSourceSystem();
//        EDMSourceSystemV1Entity sourceSystemV1Entity = sourceSystemV1Dao.getEntityWithLocalSourceSystem(sourceSystem);
//
//        String localMaterialNumber = materialGlobalV1Entity.getLocalMaterialNumber();
//        PlanCnsFinPlanQtyEntity finPlanQtyEntity = cnsFinPlanQtyDao.getEntityWithConditions(localMaterialNumber,IConstant.VALUE.FPB);
//        PlanCnsFinPlanValEntity finPlanValEntity = cnsFinPlanValDao.getEntityWithConditions(localMaterialNumber,IConstant.VALUE.FPB);
//
//        String productId = sourceSystemV1Entity.getSourceSystem() + IConstant.VALUE.UNDERLINE + materialGlobalV1Entity.getLocalDpParentCode();
//        gdmFpbBo.setProductId(productId);
//
//        String sequeceNumber = UUID.randomUUID().toString();
//        String fpbId = productId +sequeceNumber;
//        gdmFpbBo.setFpbId(fpbId);

//        if (null != finPlanValEntity) {
//            gdmFpbBo.setValue(finPlanValEntity.getValue());
//        }
//
//        if (null != finPlanQtyEntity) {
//            String country = finPlanQtyEntity.getCountry();
//            EDMCountryEntity countryV1Entity = countryV1Dao.getEntityWithLocalCountry(country);
//            if (null != countryV1Entity) {
//                gdmFpbBo.setCountryId(countryV1Entity.getCountryCode());
//            }
//
//            String currency = finPlanQtyEntity.getCurrency();
//            EDMCurrencyV1Entity currencyV1Entity = currencyV1Dao.getEntityWithLocalCurrency(currency);
//            if (null != currencyV1Entity) {
//                gdmFpbBo.setCurrencyId(currencyV1Entity.getCurrencyCode());
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
//                            gdmFpbBo.setVolume(volume);
//                        }
//                    } catch (NumberFormatException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//

        String localMaterialNumber = materialGlobalV1Entity.getLocalMaterialNumber();
        PlanCnsFinPlanQtyEntity finPlanQtyEntity = cnsFinPlanQtyDao.getEntityWithConditions(localMaterialNumber,IConstant.VALUE.FPB);
        PlanCnsFinPlanValEntity finPlanValEntity = cnsFinPlanValDao.getEntityWithConditions(localMaterialNumber,IConstant.VALUE.FPB);

        //rules  T1
        String sourceSystem = materialGlobalV1Entity.getSourceSystem();
        PlanCnsPlanParameterEntity planCnsPlanParameterEntity = planCnsPlanParameterDao.getEntityWithConditions(sourceSystem,IConstant.VALUE.SEND_TO_OMP);
        if (null!= sourceSystem){
            if (null!=planCnsPlanParameterEntity){
                gdmFpbBo.setProductId(planCnsPlanParameterEntity.getParameterValue()+IConstant.VALUE.UNDERLINE+materialGlobalV1Entity.getLocalDpParentCode());
            }
        }
        //rules  T2,T3
        if (null!=finPlanQtyEntity){
            String country = finPlanQtyEntity.getCountry();
            EDMCountryEntity countryV1Entity = countryV1Dao.getEntityWithLocalCountry(country);
            if (null != countryV1Entity) {
                gdmFpbBo.setCountryId(countryV1Entity.getLocalCountry());
            }
        }
        if (null!=finPlanValEntity){
            String currency = finPlanValEntity.getCurrency();
            EDMCurrencyV1Entity currencyV1Entity = currencyV1Dao.getEntityWithLocalCurrency(currency);
            if (null != currencyV1Entity) {
                gdmFpbBo.setCountryId(currencyV1Entity.getLocalCurrency());
            }
        }
        //rules  T5
        String yearMonth = finPlanQtyEntity.getYearMonth();
        //String yearMonth2 = finPlanValEntity.getYearMonth();
        if (null!=yearMonth){
            List<EDMJNJCalendarV1Entity> edmjnjCalendarV1Entities = edmjnjCalendarV1Dao.getEntityWithFiscalPeriod(yearMonth);
            if (null!=edmjnjCalendarV1Entities && edmjnjCalendarV1Entities.size()>0) {
                for (EDMJNJCalendarV1Entity entity:edmjnjCalendarV1Entities) {
                    Date dueDate = DateUtils.stringToDate(entity.getWeekToDate(), DateUtils.DATE_FORMAT_1);
                    gdmFpbBo.setDueDate(DateUtils.dateToString(dueDate, DateUtils.J_yyyyMMdd_HHmmss));

                    Date weekFromDate = DateUtils.stringToDate(entity.getWeekFromDate(), DateUtils.DATE_FORMAT_1);
                    gdmFpbBo.setDueDate(DateUtils.dateToString(weekFromDate, DateUtils.J_yyyyMMdd_HHmmss));

                    //rules T7,T8
                    gdmFpbBo.setValue(entity.getNoOfweek());
                    gdmFpbBo.setVolume(entity.getNoOfweek());
                }
            }
        }
        //rules J1,T4
        String localDpParentCode = materialGlobalV1Entity.getLocalDpParentCode();
        if (null != localDpParentCode){
            if (null!=finPlanQtyEntity && null!=planCnsPlanParameterEntity){
                List<PlanCnsPlanParameterEntity> planCnsPlanParameterEntity1 = planCnsPlanParameterDao.getEntityWithAttributeList(sourceSystem);
                 if (null!= planCnsPlanParameterEntity1 && planCnsPlanParameterEntity1.size()>0){
                     for (PlanCnsPlanParameterEntity entity:planCnsPlanParameterEntity1) {
                         gdmFpbBo.setFpbId(gdmFpbBo.getProductId()+"-"+gdmFpbBo.getFromDueDate());
                     }
                 }
            }

            if (null!=finPlanValEntity && null!=planCnsPlanParameterEntity){
                //PlanCnsPlanParameterEntity planCnsPlanParameterEntity1 = planCnsPlanParameterDao.getEntityWithAttribute(sourceSystem);
                List<PlanCnsPlanParameterEntity> planCnsPlanParameterEntity2 = planCnsPlanParameterDao.getEntityWithAttributeList(sourceSystem);
                if (null!= planCnsPlanParameterEntity2 && planCnsPlanParameterEntity2.size()>0){
                    for (PlanCnsPlanParameterEntity entity2:planCnsPlanParameterEntity2) {

                    }
                    if (finPlanQtyEntity.getLocalMaterialNumber()==finPlanValEntity.getLocalMaterialNumber()){

                    }else{

                    }
                    gdmFpbBo.setFpbId(gdmFpbBo.getProductId()+"-"+gdmFpbBo.getFromDueDate());
                }
            }
        }else{
            return null;
        }
        resultObject.setBaseBo(gdmFpbBo);
        return resultObject;
    }
}
