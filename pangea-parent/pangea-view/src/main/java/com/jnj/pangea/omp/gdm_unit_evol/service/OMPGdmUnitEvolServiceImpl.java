package com.jnj.pangea.omp.gdm_unit_evol.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.plan.PlanConsTimeDepXchangeDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanConsTimeDepXchangeEntity;
import com.jnj.pangea.omp.gdm_unit_evol.bo.OMPGdmUnitEvolBo;
import com.jnj.pangea.util.DateUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class OMPGdmUnitEvolServiceImpl {

    private static OMPGdmUnitEvolServiceImpl instance;

    public static OMPGdmUnitEvolServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmUnitEvolServiceImpl();
        }
        return instance;
    }

    private PlanConsTimeDepXchangeDaoImpl consTimeDepXchangeDao = PlanConsTimeDepXchangeDaoImpl.getInstance();

    public List<ResultObject> buildView(String key, Object o, Object o2) {

        List<ResultObject> list = new ArrayList<>();
        PlanConsTimeDepXchangeEntity consTimeDepXchangeEntity = (PlanConsTimeDepXchangeEntity) o;
        Map<String, Object> extraParam = (HashMap) o2;
        OMPGdmUnitEvolBo gdmUnitEvolBo = new OMPGdmUnitEvolBo();

        // rules T3
        if (StringUtils.isNotEmpty(consTimeDepXchangeEntity.getFromCurrency())) {
            List<PlanConsTimeDepXchangeEntity> consTimeDepXchangeEntityCloneList = consTimeDepXchangeDao.getEntityListWithFromCurrency(consTimeDepXchangeEntity.getFromCurrency());
            List<String> effectiveEndDateList = new ArrayList<String>();
            for (PlanConsTimeDepXchangeEntity consTimeDepXchangeEntityClone : consTimeDepXchangeEntityCloneList) {
                effectiveEndDateList.add(consTimeDepXchangeEntityClone.getEffectiveEndDate());
            }

            // currentTime
            Calendar current = Calendar.getInstance();
            current.set(Calendar.YEAR, current.get(Calendar.YEAR) + 4);
            int currentYear = current.get(Calendar.YEAR);

            // effectiveStartDate
            Calendar effectiveStartDate = Calendar.getInstance();
            Date startDate = DateUtils.stringToDate(consTimeDepXchangeEntity.getEffectiveStartDate(), IConstant.VALUE.YYYYMMDD);
            effectiveStartDate.setTime(startDate);

            // effectiveEndDate
            Calendar effectiveEndDate = Calendar.getInstance();
            Date latest = DateUtils.stringToDate(consTimeDepXchangeEntity.getEffectiveEndDate(), IConstant.VALUE.DDMMYYYY);
            effectiveEndDate.setTime(latest);
            int endYear = effectiveEndDate.get(Calendar.YEAR);

            if (Collections.max(effectiveEndDateList).equals(consTimeDepXchangeEntity.getEffectiveEndDate())) {

                for (; endYear <= currentYear; endYear++) {

                    if (endYear == currentYear) {
                        ResultObject resultObject = new ResultObject();
                        String extraParamKey = consTimeDepXchangeEntity.getFromCurrency();
                        if (extraParam.containsKey(extraParamKey)) {
                            String extraParamValue = String.format("%03d", Integer.parseInt(extraParam.get(extraParamKey).toString()) + 1);
                            extraParam.put(extraParamKey, extraParamValue);
                        } else {
                            extraParam.put(extraParamKey, IConstant.VALUE.STR_ONE);
                        }
                        // rules C1
                        gdmUnitEvolBo.setUniqueId(extraParamKey + extraParam.get(extraParamKey));
                        // rules D1
                        gdmUnitEvolBo.setActiveFCTERP(IConstant.VALUE.YES);
                        gdmUnitEvolBo.setUnitId(consTimeDepXchangeEntity.getFromCurrency());

                        gdmUnitEvolBo.setStartEff(DateUtils.dateToString(effectiveStartDate.getTime(), IConstant.VALUE.YYYYDDMMHHMMSS));
                        effectiveEndDate.set(Calendar.DATE, current.get(Calendar.DATE) + 1);
                        gdmUnitEvolBo.setEndEff(DateUtils.dateToString(effectiveEndDate.getTime(), IConstant.VALUE.YYYYDDMMHHMMSS));

                        gdmUnitEvolBo.setFactor(consTimeDepXchangeEntity.getExchangeRate());
                        gdmUnitEvolBo.setPreference(consTimeDepXchangeEntity.getPreference());
                        resultObject.setBaseBo(gdmUnitEvolBo);
                        list.add(resultObject);
                    } else {
                        ResultObject resultObject = new ResultObject();
                        LogUtil.getCoreLog().info("---------------------{}------------------", endYear);
                        String extraParamKey = consTimeDepXchangeEntity.getFromCurrency();
                        if (extraParam.containsKey(extraParamKey)) {
                            String extraParamValue = String.format("%03d", Integer.parseInt(extraParam.get(extraParamKey).toString()) + 1);
                            extraParam.put(extraParamKey, extraParamValue);
                        } else {
                            extraParam.put(extraParamKey, IConstant.VALUE.STR_ONE);
                        }
                        // rules C1
                        gdmUnitEvolBo.setUniqueId(extraParamKey + extraParam.get(extraParamKey));
                        // rules D1
                        gdmUnitEvolBo.setActiveFCTERP(IConstant.VALUE.YES);
                        gdmUnitEvolBo.setUnitId(consTimeDepXchangeEntity.getFromCurrency());

                        effectiveStartDate.set(Calendar.YEAR, current.get(Calendar.YEAR) + 1);
                        gdmUnitEvolBo.setStartEff(DateUtils.dateToString(effectiveStartDate.getTime(), IConstant.VALUE.YYYYDDMMHHMMSS));
                        effectiveEndDate.set(Calendar.YEAR, current.get(Calendar.YEAR) + 1);
                        effectiveEndDate.set(Calendar.DATE, current.get(Calendar.DATE) + 1);
                        gdmUnitEvolBo.setEndEff(DateUtils.dateToString(effectiveEndDate.getTime(), IConstant.VALUE.YYYYDDMMHHMMSS));

                        gdmUnitEvolBo.setFactor(consTimeDepXchangeEntity.getExchangeRate());
                        gdmUnitEvolBo.setPreference(consTimeDepXchangeEntity.getPreference());
                        resultObject.setBaseBo(gdmUnitEvolBo);
                        list.add(resultObject);
                    }
                }
            } else {
                ResultObject resultObject = new ResultObject();
                String extraParamKey = consTimeDepXchangeEntity.getFromCurrency();
                if (extraParam.containsKey(extraParamKey)) {
                    String extraParamValue = String.format("%03d", Integer.parseInt(extraParam.get(extraParamKey).toString()) + 1);
                    extraParam.put(extraParamKey, extraParamValue);
                } else {
                    extraParam.put(extraParamKey, IConstant.VALUE.STR_ONE);
                }
                // rules C1
                gdmUnitEvolBo.setUniqueId(extraParamKey + extraParam.get(extraParamKey));
                // rules D1
                gdmUnitEvolBo.setActiveFCTERP(IConstant.VALUE.YES);
                gdmUnitEvolBo.setUnitId(consTimeDepXchangeEntity.getFromCurrency());

                gdmUnitEvolBo.setStartEff(DateUtils.dateToString(effectiveStartDate.getTime(), IConstant.VALUE.YYYYDDMMHHMMSS));
                effectiveEndDate.set(Calendar.DATE, current.get(Calendar.DATE) + 1);
                gdmUnitEvolBo.setEndEff(DateUtils.dateToString(effectiveEndDate.getTime(), IConstant.VALUE.YYYYDDMMHHMMSS));

                gdmUnitEvolBo.setFactor(consTimeDepXchangeEntity.getExchangeRate());
                gdmUnitEvolBo.setPreference(consTimeDepXchangeEntity.getPreference());
                resultObject.setBaseBo(gdmUnitEvolBo);
                list.add(resultObject);
            }

        } else {
            ResultObject resultObject = new ResultObject();
            resultObject.setFailData(new FailData(IConstant.FAILED.FUNCTIONAL_AREA.DP, IConstant.FAILED.INTERFACE_ID.GDM_UNIT_EVOL, IConstant.FAILED.ERROR_CODE.C1,
                    "All Key fields not Exist", "", consTimeDepXchangeEntity.getSourceSystem(),
                    consTimeDepXchangeEntity.getFromCurrency(), consTimeDepXchangeEntity.getEffectiveStartDate(),
                    consTimeDepXchangeEntity.getEffectiveEndDate()));
            list.add(resultObject);
        }
        return list;
    }
}
