package com.jnj.pangea.omp.gdm_location.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.plan.PlanCnsPlnSplLocEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_location.bo.OMPGdmLocationBo;
import org.apache.commons.lang.StringUtils;

public class OMPGdmLocationXrefServiceImpl implements ICommonService {

    private static OMPGdmLocationXrefServiceImpl instance;

    public static OMPGdmLocationXrefServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmLocationXrefServiceImpl();
        }
        return instance;
    }

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        PlanCnsPlnSplLocEntity cnsPlnSplLocEntity = (PlanCnsPlnSplLocEntity) o;

        OMPGdmLocationBo gdmLocationXrefBo = new OMPGdmLocationBo();

        if (cnsPlnSplLocEntity == null) {
            return resultObject;
        }
        // rules C1
        if (StringUtils.isBlank(cnsPlnSplLocEntity.getLocalPlant())) {
            gdmLocationXrefBo.setLocationId(cnsPlnSplLocEntity.getSourceSystem() + "_" + cnsPlnSplLocEntity.getVendorOrCustomer() + "_" +
                    cnsPlnSplLocEntity.getLocalNumber().replaceFirst("^0*", ""));
        } else {
            gdmLocationXrefBo.setLocationId(cnsPlnSplLocEntity.getSourceSystem() + "_" + cnsPlnSplLocEntity.getLocalPlant() + "$" +
                    cnsPlnSplLocEntity.getLocalNumber().replaceFirst("^0*", ""));
        }

        //rules C3
        if (IConstant.VALUE.C.equals(cnsPlnSplLocEntity.getVendorOrCustomer())) {
            gdmLocationXrefBo.setCustomerId(cnsPlnSplLocEntity.getLocalNumber().replaceFirst("^0*", ""));
        }

        //rules C4
        if (IConstant.VALUE.V.equals(cnsPlnSplLocEntity.getVendorOrCustomer())) {
            gdmLocationXrefBo.setVendorId(cnsPlnSplLocEntity.getLocalNumber().replaceFirst("^0*", ""));
        }

        //rules D1
        gdmLocationXrefBo.setCurrencyId(cnsPlnSplLocEntity.getLocalCurrency());

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
