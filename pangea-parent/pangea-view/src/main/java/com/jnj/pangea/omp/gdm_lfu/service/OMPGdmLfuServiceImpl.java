package com.jnj.pangea.omp.gdm_lfu.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.*;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanParameterDaoImpl;
import com.jnj.pangea.common.entity.edm.*;
import com.jnj.pangea.common.entity.plan.PlanCnsFinPlanQtyAndValEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsFinPlanQtyEntity;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsFinPlanQtyDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsFinPlanValEntity;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsFinPlanValDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanParameterEntity;
import com.jnj.pangea.common.service.ICommonListService;
import com.jnj.pangea.omp.gdm_lfu.bo.OMPGdmLfuBo;
import org.apache.commons.lang3.StringUtils;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.*;
import java.util.regex.Pattern;

import static com.jnj.pangea.util.DateUtils.*;

/**
 * @author WChen129
 */
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
    private EDMMaterialAuomV1DaoImpl edmMaterialAuomV1Dao = EDMMaterialAuomV1DaoImpl.getInstance();
    private EDMMaterialGlobalV1DaoImpl edmMaterialGlobalV1Dao=EDMMaterialGlobalV1DaoImpl.getInstance();

    final java.text.DecimalFormat df = new java.text.DecimalFormat(IConstant.LFU.PATTERN_DECIMAL_3);

    @Override
    public List<ResultObject> buildView(String key, Object o, Object o2) {

        List<ResultObject> list = new ArrayList<>();

        EDMMaterialGlobalV1Entity materialGlobalV1Entity = (EDMMaterialGlobalV1Entity) o;

        if (StringUtils.isBlank(materialGlobalV1Entity.getLocalDpParentCode())) {
            ResultObject resultObject=new ResultObject();
            resultObject.setFailData(setLFUFailData(materialGlobalV1Entity));
            list.add(resultObject);
            return list;
        }

        List<PlanCnsPlanParameterEntity> PlanCnsPlanParameterEntityList = planCnsPlanParameterDao.getEntityWithAttributeListForLFU(materialGlobalV1Entity.getSourceSystem());

        if (PlanCnsPlanParameterEntityList == null || PlanCnsPlanParameterEntityList.size() == 0) {

            return list;
        }

        List<EDMMaterialGlobalV1Entity> globalList= edmMaterialGlobalV1Dao.getEntitiesWithLocalDpParentCodeClone(materialGlobalV1Entity.getLocalDpParentCode());
        Map<String,Double> qtyYearMap=new HashMap<>();
        Map<String,Double> valYearMap=new HashMap<>();

        NumberFormat nf = NumberFormat.getInstance();
        nf.setRoundingMode(RoundingMode.HALF_UP);
        nf.setMinimumFractionDigits(IConstant.LFU.VALUE_DECIMAL_3);
        nf.setMaximumFractionDigits(IConstant.LFU.VALUE_DECIMAL_3);
        nf.setGroupingUsed(false);
        List<String> listMaterialNumber=new ArrayList<> ();
        for(int i=0;i<globalList.size();i++){
            listMaterialNumber.add(globalList.get(i).getLocalMaterialNumber());
        }


        List<PlanCnsFinPlanQtyEntity>  finPlanQtyEntityList = cnsFinPlanQtyDao.getListWithConditions(listMaterialNumber, IConstant.VALUE.LFU);
        List<PlanCnsFinPlanValEntity>  finPlanValEntityList = cnsFinPlanValDao.getListWithConditions(listMaterialNumber, IConstant.VALUE.LFU);
        List<PlanCnsFinPlanQtyAndValEntity> mergeQtyVal =mergeQtyVal(finPlanQtyEntityList,finPlanValEntityList);

        for(int j=0;j<mergeQtyVal.size();j++){
            PlanCnsFinPlanQtyAndValEntity planCnsFinPlanQtyAndValEntity=mergeQtyVal.get(j);
            String yearMonth=planCnsFinPlanQtyAndValEntity.getYearMonth();

            if(qtyYearMap.containsKey(yearMonth)){
                Double qty=  getQualityChangeFromAuom(planCnsFinPlanQtyAndValEntity);
                if(qty==null){

                    continue;
                }
                qtyYearMap.put(yearMonth,qtyYearMap.get(yearMonth)+qty);
            }else{
                Double qty=  getQualityChangeFromAuom(planCnsFinPlanQtyAndValEntity);
                if(qty==null){

                    continue;
                }
                qtyYearMap.put(yearMonth,qty);
            }

            if(valYearMap.containsKey(yearMonth)){
                Double value=valYearMap.get(yearMonth);
                try {
                    valYearMap.put(yearMonth,value+Double.parseDouble(planCnsFinPlanQtyAndValEntity.getValue()));
                }catch (Exception e){
                }
            }else{
                try {
                    valYearMap.put(yearMonth,Double.parseDouble(planCnsFinPlanQtyAndValEntity.getValue()));
                }catch (Exception e){
                }
            }
        }


        for(PlanCnsFinPlanQtyAndValEntity planCnsFinPlanQtyAndValEntity:mergeQtyVal){
            String country = IConstant.LFU.VALUE_BLACK;
            String yearMonth = planCnsFinPlanQtyAndValEntity.getYearMonth();
            String currency= IConstant.LFU.VALUE_BLACK;;
            List<EDMJNJCalendarV1Entity> EDMJNJCalendarV1EntityList = edmjnjCalendarV1Dao.getEntityWithFiscalPeriod(yearMonth);
            if (EDMJNJCalendarV1EntityList == null || EDMJNJCalendarV1EntityList.size() == 0) {

                continue;
            }
            Pattern pattern = Pattern.compile(IConstant.LFU.CHCEK_TIME);
            EDMCountryEntity edmCountryEntity = countryV1Dao.getEntityWithLocalCountry( planCnsFinPlanQtyAndValEntity.getCountry());
            EDMCurrencyV1Entity edmCurrencyV1Entity = currencyV1Dao.getEntityWithLocalCurrency(planCnsFinPlanQtyAndValEntity.getCurrency());
            if (edmCountryEntity != null) {
                country=edmCountryEntity.getCountryCode();
            }
            if (edmCurrencyV1Entity != null) {
                currency=edmCurrencyV1Entity.getCurrencyCode();
            }

            for (PlanCnsPlanParameterEntity parameterEntity : PlanCnsPlanParameterEntityList) {

                for (EDMJNJCalendarV1Entity edmjnjCalendarV1Entity : EDMJNJCalendarV1EntityList) {
                    int noOfWeek=1;

                    String dateFrom = edmjnjCalendarV1Entity.getWeekFromDate();
                    if (StringUtils.isNotBlank(dateFrom) && pattern.matcher(dateFrom).matches()) {
                        dateFrom = dateToString(stringToDate(dateFrom, DATE_FORMAT_1), yyyy_MM_dd_HHmmss_TRUE);
                        String dateTo = edmjnjCalendarV1Entity.getWeekToDate();
                        if (StringUtils.isNotBlank(dateTo) && pattern.matcher(dateTo).matches()) {
                            dateTo = dateToString(stringToDate(dateTo, DATE_FORMAT_1), yyyy_MM_dd_HHmmss_TRUE);
                        }
                        ResultObject resultObject = new ResultObject();
                        OMPGdmLfuBo gdmLfuBoV1 = new OMPGdmLfuBo();
                        gdmLfuBoV1.setProductId(parameterEntity.getParameterValue() + IConstant.LFU.SPLIT_ + materialGlobalV1Entity.getLocalDpParentCode());
                        gdmLfuBoV1.setCurrencyId(currency);
                        gdmLfuBoV1.setCountryId(country);
                        gdmLfuBoV1.setValue(IConstant.LFU.VALUE_BLACK);
                        gdmLfuBoV1.setVolume(IConstant.LFU.VALUE_BLACK);
                        try {
                            noOfWeek= Integer.parseInt( edmjnjCalendarV1Entity.getNoOfWeek()) ;
                        }catch (Exception e){
                            LogUtil.getCoreLog().error(e.getMessage());
                        }
                        try {
                            gdmLfuBoV1.setVolume(String.valueOf(Math.round(qtyYearMap.get(yearMonth)/noOfWeek)));
                        }catch (Exception e){
                            LogUtil.getCoreLog().error(e.getMessage());
                        }
                        try {
                            gdmLfuBoV1.setValue(String.valueOf( nf.format(valYearMap.get(yearMonth)/noOfWeek)));
                        }catch (Exception e){
                            LogUtil.getCoreLog().error(e.getMessage());
                        }
                        gdmLfuBoV1.setFromDueDate(dateFrom);
                        gdmLfuBoV1.setDueDate(dateTo);
                        gdmLfuBoV1.setLfuId(gdmLfuBoV1.getProductId() + IConstant.LFU.SPLIT + gdmLfuBoV1.getFromDueDate());
                        resultObject.setBaseBo(gdmLfuBoV1);
                        list.add(resultObject);
                    }
                }
            }
        }
        return list;
    }

    public List<PlanCnsFinPlanQtyAndValEntity> mergeQtyVal(List<PlanCnsFinPlanQtyEntity> qtyList, List<PlanCnsFinPlanValEntity> valList){
        List<PlanCnsFinPlanQtyAndValEntity> result=new  ArrayList<>();
        if(valList==null||valList.size()==0){
            return result;
        }
        if(qtyList==null||qtyList.size()==0){
            return result;
        }

        Map<String,PlanCnsFinPlanQtyAndValEntity> yearMap=new HashMap<>();
        for (PlanCnsFinPlanQtyEntity qtyEntity :qtyList){
            yearMap.put(qtyEntity.getLocalMaterialNumber().trim()+qtyEntity.getYearMonth().trim(),qtyEntity);
        }

        for (PlanCnsFinPlanValEntity valEntity :valList){
            if(yearMap.containsKey(valEntity.getLocalMaterialNumber().trim()+valEntity.getYearMonth().trim())){
                PlanCnsFinPlanQtyAndValEntity planCnsFinPlanQtyAndValEntity=yearMap.get(valEntity.getLocalMaterialNumber().trim()+valEntity.getYearMonth());
                planCnsFinPlanQtyAndValEntity.setCurrency(valEntity.getCurrency());
                planCnsFinPlanQtyAndValEntity.setValue(valEntity.getValue());
                result.add(planCnsFinPlanQtyAndValEntity);
            }
        }
        return result;
    }
    public Double getQualityChangeFromAuom(PlanCnsFinPlanQtyAndValEntity planCnsFinPlanQtyAndValEntity){
        EDMMaterialAuomV1Entity edmMaterialAuomV1Entity = edmMaterialAuomV1Dao.getEntityWithConditions(planCnsFinPlanQtyAndValEntity.getLocalMaterialNumber(), planCnsFinPlanQtyAndValEntity.getUnitId(), planCnsFinPlanQtyAndValEntity.getSourceSystem());
        if(edmMaterialAuomV1Entity!=null){
            try {
                double quantity=Double.parseDouble(planCnsFinPlanQtyAndValEntity.getQuantity());
                double localNumerator=Double.parseDouble(edmMaterialAuomV1Entity.getLocalNumerator());
                double localDenominator=Double.parseDouble(edmMaterialAuomV1Entity.getLocalDenominator());
                return quantity*localNumerator/localDenominator;
            }catch (Exception e){
                LogUtil.getCoreLog().error(e.getMessage());
                e.printStackTrace();
            }
        }
        return null;
    }
    public FailData setLFUFailData(EDMMaterialGlobalV1Entity materialGlobalV1Entity){
        return  new FailData(IConstant.FAILED.FUNCTIONAL_AREA.DP,
                IConstant.FAILED.INTERFACE_ID.OMP_GDM_LFU, IConstant.FAILED.ERROR_CODE.J1, IConstant.LFU.FAIL_MSG, materialGlobalV1Entity.getSourceSystem(), materialGlobalV1Entity.getLocalMaterialNumber(),
                materialGlobalV1Entity.getSourceSystem());
    }
}
