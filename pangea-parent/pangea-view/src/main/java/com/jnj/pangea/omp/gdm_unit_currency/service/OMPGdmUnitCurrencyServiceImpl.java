package com.jnj.pangea.omp.gdm_unit_currency.service;

import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.edm.EDMCurrencyV1Entity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_unit_currency.bo.OMPGdmUnitCurrencyBo;
import org.apache.commons.lang.StringUtils;

public class OMPGdmUnitCurrencyServiceImpl implements ICommonService {

    private static OMPGdmUnitCurrencyServiceImpl instance;

    public static OMPGdmUnitCurrencyServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmUnitCurrencyServiceImpl();
        }
        return instance;
    }


    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        EDMCurrencyV1Entity currencyV1Entity = (EDMCurrencyV1Entity) o;

        OMPGdmUnitCurrencyBo gdmUnitCurrencyBo = new OMPGdmUnitCurrencyBo();
        if (null != currencyV1Entity){

            // rules E1
            if (StringUtils.isNotEmpty(currencyV1Entity.getCurrencyCode())) {

                gdmUnitCurrencyBo.setUnitId(currencyV1Entity.getCurrencyCode());
                // rules D1
                gdmUnitCurrencyBo.setActive(IConstant.VALUE.YES);
                gdmUnitCurrencyBo.setActiveFCTERP(IConstant.VALUE.YES);
                gdmUnitCurrencyBo.setActiveOPRERP(IConstant.VALUE.YES);
                // rules D3
                gdmUnitCurrencyBo.setActiveSOPERP(IConstant.VALUE.NO);
                // rules D2
                gdmUnitCurrencyBo.setFactor(IConstant.VALUE.CURRENCY_V);
                // rules D6
                gdmUnitCurrencyBo.setIsoCode(IConstant.VALUE.BLANK);
                // rules D4
                gdmUnitCurrencyBo.setMeasure(IConstant.VALUE.CURRENCY);
                // rules D5
                gdmUnitCurrencyBo.setPrecision(IConstant.VALUE.ZERO);
                gdmUnitCurrencyBo.setLongDescription(currencyV1Entity.getCurrencyName());
                gdmUnitCurrencyBo.setShortDescription(currencyV1Entity.getCurrencyName());
                resultObject.setBaseBo(gdmUnitCurrencyBo);
            }
        }

        return resultObject;
    }
}
