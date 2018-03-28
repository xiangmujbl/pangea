package com.jnj.pangea.omp.gdm_country.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.edm.EDMCountryV1Entity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_country.bo.OMPGdmCountryBo;

public class OMPGdmCountryServiceImpl implements ICommonService {

    private static OMPGdmCountryServiceImpl instance;

    public static OMPGdmCountryServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmCountryServiceImpl();
        }
        return instance;
    }


    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        EDMCountryV1Entity countryV1Entity = (EDMCountryV1Entity) o;

        OMPGdmCountryBo gdmCountryBo = new OMPGdmCountryBo();
        if (null !=countryV1Entity){
            gdmCountryBo.setCountryID(countryV1Entity.getCountryCode());
            gdmCountryBo.setCountryDescription(countryV1Entity.getCountryName());
        }
        gdmCountryBo.setActiveFCTERP(IConstant.VALUE.YES);
        gdmCountryBo.setActiveOPRERP(IConstant.VALUE.YES);
        gdmCountryBo.setActiveSOPERP(IConstant.VALUE.YES);
        gdmCountryBo.setMRC("");
        resultObject.setBaseBo(gdmCountryBo);
        return resultObject;
    }
}
