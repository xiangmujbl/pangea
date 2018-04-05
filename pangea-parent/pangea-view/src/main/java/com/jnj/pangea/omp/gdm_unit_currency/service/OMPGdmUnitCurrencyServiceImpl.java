package com.jnj.pangea.omp.gdm_unit_currency.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.edm.EDMCurrencyV1Entity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_unit_currency.bo.OMPGdmUnitCurrencyBo;

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
            gdmUnitCurrencyBo.setUnitId(currencyV1Entity.getCurrencyCode());
            gdmUnitCurrencyBo.setActive(IConstant.VALUE.YES);
            gdmUnitCurrencyBo.setActiveFCTERP(IConstant.VALUE.YES);
            gdmUnitCurrencyBo.setActiveOPRERP(IConstant.VALUE.YES);
            gdmUnitCurrencyBo.setActiveSOPERP(IConstant.VALUE.YES);
            gdmUnitCurrencyBo.setFactor("");
            gdmUnitCurrencyBo.setIsoCode(currencyV1Entity.getIsoNumeric());
            gdmUnitCurrencyBo.setMeasure("");
            gdmUnitCurrencyBo.setPrecision("");
            gdmUnitCurrencyBo.setLongDescription(currencyV1Entity.getCurrencyName());
            gdmUnitCurrencyBo.setShortDescription(currencyV1Entity.getCurrencyName());
            resultObject.setBaseBo(gdmUnitCurrencyBo);
        }

        resultObject.setBaseBo(gdmUnitCurrencyBo);
        return resultObject;
    }
}
