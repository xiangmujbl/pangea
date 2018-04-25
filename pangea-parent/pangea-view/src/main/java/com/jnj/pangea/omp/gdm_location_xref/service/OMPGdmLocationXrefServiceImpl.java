package com.jnj.pangea.omp.gdm_location_xref.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanParameterDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanParameterEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsPlnSplLocEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_location_xref.bo.OMPGdmLocationXrefBo;

import java.util.List;

public class OMPGdmLocationXrefServiceImpl implements ICommonService {

    private static OMPGdmLocationXrefServiceImpl instance;

    public static OMPGdmLocationXrefServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmLocationXrefServiceImpl();
        }
        return instance;
    }

    private PlanCnsPlanParameterDaoImpl cnsPlanParameterDao = PlanCnsPlanParameterDaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        PlanCnsPlnSplLocEntity cnsPlnSplLocEntity = (PlanCnsPlnSplLocEntity) o;

        OMPGdmLocationXrefBo gdmLocationXrefBo = new OMPGdmLocationXrefBo();

        if (cnsPlnSplLocEntity == null) {
            return resultObject;
        }
        // rules C1
        gdmLocationXrefBo.setLocationId(cnsPlnSplLocEntity.getSourceSystem() + "_" + cnsPlnSplLocEntity.getVendorOrCustomer() + "_" + cnsPlnSplLocEntity.getLocalNumber());

        //rules C3
        if (cnsPlnSplLocEntity.getVendorCustomer() != null && cnsPlnSplLocEntity.getVendorCustomer().equals("C")){
            gdmLocationXrefBo.setCustomerid(cnsPlnSplLocEntity.getLocalNumber());
        } else {
            gdmLocationXrefBo.setCustomerid("");
        }
        //rules C4
        if (cnsPlnSplLocEntity.getVendorCustomer() != null && cnsPlnSplLocEntity.getVendorCustomer().equals("V")){
            gdmLocationXrefBo.setVendorid(cnsPlnSplLocEntity.getLocalNumber());
        }
        else {
            gdmLocationXrefBo.setVendorid("");
        }

        //rules D1
        gdmLocationXrefBo.setCurrencyId(IConstant.VALUE.USD);

        //rules D2
        gdmLocationXrefBo.setActive(IConstant.VALUE.YES);
        gdmLocationXrefBo.setActiveOPRERP(IConstant.VALUE.YES);

        // rules D4
        gdmLocationXrefBo.setActiveFCTERP(IConstant.VALUE.NO);
        gdmLocationXrefBo.setActiveSOPERP(IConstant.VALUE.NO);

        gdmLocationXrefBo.setCountryId(cnsPlnSplLocEntity.getLocalCountry());
        gdmLocationXrefBo.setLabel(cnsPlnSplLocEntity.getLocalName());
        gdmLocationXrefBo.setLocationTypeId(cnsPlnSplLocEntity.getPlanLocTypeId());
        gdmLocationXrefBo.setRegionId(cnsPlnSplLocEntity.getLocalRegion());

        resultObject.setBaseBo(gdmLocationXrefBo);
        return resultObject;
    }
}
