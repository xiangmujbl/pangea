package com.jnj.pangea.omp.gdm_lfu.service;

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
import com.jnj.pangea.common.service.ICommonListService;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_lfu.bo.OMPGdmLfuBo;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import static com.jnj.pangea.util.DateUtils.*;

public class OMPGdmLfuServiceImpl implements ICommonListService {

    private static OMPGdmLfuServiceImpl instance;

    public static OMPGdmLfuServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmLfuServiceImpl();
        }
        return instance;
    }

    private PlanCnsFinPlanQtyDaoImpl cnsFinPlanQtyDao = PlanCnsFinPlanQtyDaoImpl.getInstance();
    private PlanCnsPlanParameterDaoImpl planCnsPlanParameterDao = PlanCnsPlanParameterDaoImpl.getInstance();
    private PlanCnsFinPlanValDaoImpl cnsFinPlanValDao = PlanCnsFinPlanValDaoImpl.getInstance();
    private EDMCountryV1DaoImpl countryV1Dao = EDMCountryV1DaoImpl.getInstance();
    private EDMCurrencyV1DaoImpl currencyV1Dao = EDMCurrencyV1DaoImpl.getInstance();
    private EDMJNJCalendarV1DaoImpl edmjnjCalendarV1Dao = EDMJNJCalendarV1DaoImpl.getInstance();


    @Override
    public List<ResultObject> buildView(String key, Object o, Object o2) {
        List<ResultObject> list=new ArrayList<>();
        EDMMaterialGlobalV1Entity materialGlobalV1Entity = (EDMMaterialGlobalV1Entity) o;
        if(StringUtils.isBlank(materialGlobalV1Entity.getLocalDpParentCode())){
            return list;
        }
        List<PlanCnsPlanParameterEntity> PlanCnsPlanParameterEntityList = planCnsPlanParameterDao.getEntityWithAttributeListForLFU(materialGlobalV1Entity.getSourceSystem());
        if(PlanCnsPlanParameterEntityList==null||PlanCnsPlanParameterEntityList.size()==0){
            return list;
        }
        OMPGdmLfuBo gdmLfuBo = new OMPGdmLfuBo();
        String localMaterialNumber = materialGlobalV1Entity.getLocalMaterialNumber();
        PlanCnsFinPlanQtyEntity finPlanQtyEntity = cnsFinPlanQtyDao.getEntityWithConditions(localMaterialNumber,IConstant.VALUE.LFU);
        PlanCnsFinPlanValEntity finPlanValEntity = cnsFinPlanValDao.getEntityWithConditions(localMaterialNumber,IConstant.VALUE.LFU);
        String country="";
        String currency="";
        String yearMonth="";
        if(finPlanValEntity!=null){
            country=finPlanValEntity.getCountry();
            currency=finPlanValEntity.getCurrency();
            gdmLfuBo.setValue(finPlanValEntity.getValue());
            yearMonth=finPlanValEntity.getYearMonth();
        }
        if(finPlanQtyEntity!=null){
            country=finPlanQtyEntity.getCountry();
            currency=finPlanQtyEntity.getCurrency();
            gdmLfuBo.setVolume(finPlanQtyEntity.getQuantity());
            yearMonth=finPlanQtyEntity.getYearMonth();
        }
        List<EDMJNJCalendarV1Entity>  EDMJNJCalendarV1EntityList= edmjnjCalendarV1Dao.getEntityWithFiscalPeriod(yearMonth);
        if(EDMJNJCalendarV1EntityList==null||EDMJNJCalendarV1EntityList.size()==0){
            return list;
        }
        Pattern pattern=Pattern.compile(IConstant.LFU.CHCEK_TIME);
        EDMCountryEntity edmCountryEntity=countryV1Dao.getEntityWithLocalCountry(country);
        EDMCurrencyV1Entity edmCurrencyV1Entity=currencyV1Dao.getEntityWithLocalCurrency(currency);
        if(edmCountryEntity!=null){
            gdmLfuBo.setCountryId(edmCountryEntity.getCountryCode());
        }
        if(edmCurrencyV1Entity!=null){
            gdmLfuBo.setCurrencyId(edmCurrencyV1Entity.getCurrencyCode());
        }

        for(PlanCnsPlanParameterEntity parameterEntity:PlanCnsPlanParameterEntityList){

            for(EDMJNJCalendarV1Entity edmjnjCalendarV1Entity:EDMJNJCalendarV1EntityList){
                String dateFrom=edmjnjCalendarV1Entity.getWeekFromDate();
                if(StringUtils.isNotBlank(dateFrom)&&pattern.matcher(dateFrom).matches()){
                    dateFrom=dateToString(stringToDate(dateFrom, DATE_FORMAT_1), yyyy_MM_dd_HHmmss_TRUE);
                    String dateTo=edmjnjCalendarV1Entity.getWeekToDate();;
                    if(StringUtils.isNotBlank(dateTo)&&pattern.matcher(dateTo).matches()){
                        LogUtil.getCoreLog().info(dateTo);
                        dateTo =dateToString(stringToDate(dateTo, DATE_FORMAT_1), yyyy_MM_dd_HHmmss_TRUE);
                        LogUtil.getCoreLog().info(dateTo);
                    }
                    ResultObject resultObject = new ResultObject();
                    OMPGdmLfuBo gdmLfuBoV1 = new OMPGdmLfuBo();
                    gdmLfuBoV1.setProductId(parameterEntity.getParameterValue()+IConstant.LFU.SPLIT+materialGlobalV1Entity.getLocalDpParentCode());
                    gdmLfuBoV1.setCurrencyId(gdmLfuBo.getCurrencyId());
                    gdmLfuBoV1.setCountryId(gdmLfuBo.getCountryId());
                    gdmLfuBoV1.setValue(gdmLfuBo.getValue());
                    gdmLfuBoV1.setVolume(gdmLfuBo.getVolume());
                    gdmLfuBoV1.setFromDueDate(dateFrom);
                    gdmLfuBoV1.setDueDate(dateTo);
                    gdmLfuBoV1.setLFUId(gdmLfuBoV1.getProductId()+IConstant.LFU.SPLIT+gdmLfuBoV1.getFromDueDate());
                    resultObject.setBaseBo(gdmLfuBoV1);
                    list.add(resultObject);
                }
            }
        }
        return list;
    }
}
