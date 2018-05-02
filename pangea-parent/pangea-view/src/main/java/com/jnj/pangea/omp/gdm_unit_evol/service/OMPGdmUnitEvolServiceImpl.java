package com.jnj.pangea.omp.gdm_unit_evol.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.plan.PlanConsTimeDepXchangeEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_unit_evol.bo.OMPGdmUnitEvolBo;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class OMPGdmUnitEvolServiceImpl implements ICommonService {

    private static OMPGdmUnitEvolServiceImpl instance;

    public static OMPGdmUnitEvolServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmUnitEvolServiceImpl();
        }
        return instance;
    }

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        PlanConsTimeDepXchangeEntity consTimeDepXchangeEntity = (PlanConsTimeDepXchangeEntity) o;
        Map<String, Object> extraParam = (HashMap) o2;

        OMPGdmUnitEvolBo gdmUnitEvolBo = new OMPGdmUnitEvolBo();
        LogUtil.getCoreLog().info("-----------" + consTimeDepXchangeEntity.getUniqueId());
//        if (null != consTimeDepXchangeEntity) {
        if (StringUtils.isNotEmpty(consTimeDepXchangeEntity.getSourceSystem()) && StringUtils.isNotEmpty(consTimeDepXchangeEntity.getFromCurrency())) {
            String extraParamKey = consTimeDepXchangeEntity.getSourceSystem() + consTimeDepXchangeEntity.getFromCurrency();
            if (extraParam.containsKey(extraParamKey)) {
                String extraParamValue = String.format("%03d", Integer.parseInt(extraParam.get(extraParamKey).toString()) + 1);
                extraParam.put(extraParamKey, extraParamValue);
            } else {
                extraParam.put(extraParamKey, IConstant.VALUE.STR_ONE);
            }
            gdmUnitEvolBo.setUniqueId(extraParamKey + extraParam.get(extraParamKey));
            gdmUnitEvolBo.setActiveFCTERP(IConstant.VALUE.YES);
            gdmUnitEvolBo.setUnitId(consTimeDepXchangeEntity.getFromCurrency());
            gdmUnitEvolBo.setStartEff(consTimeDepXchangeEntity.getEffectiveStartDate());
            gdmUnitEvolBo.setEndEff(consTimeDepXchangeEntity.getEffectiveEndDate());
            gdmUnitEvolBo.setFactor(consTimeDepXchangeEntity.getExchangeRate());
            gdmUnitEvolBo.setPreference(consTimeDepXchangeEntity.getPreference());
            resultObject.setBaseBo(gdmUnitEvolBo);
        } else {
            resultObject.setFailData(new FailData(IConstant.FAILED.FUNCTIONAL_AREA.DP, IConstant.FAILED.INTERFACE_ID.GDM_UNIT_EVOL, IConstant.FAILED.ERROR_CODE.C1,
                    "All Key fields not Exist", "", consTimeDepXchangeEntity.getSourceSystem(),
                    consTimeDepXchangeEntity.getFromCurrency(),consTimeDepXchangeEntity.getEffectiveStartDate(),
                    consTimeDepXchangeEntity.getEffectiveEndDate()));
        }
//        }
        return resultObject;
    }
}
