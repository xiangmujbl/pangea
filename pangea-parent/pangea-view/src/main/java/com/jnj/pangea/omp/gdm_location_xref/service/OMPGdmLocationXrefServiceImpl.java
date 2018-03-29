package com.jnj.pangea.omp.gdm_location_xref.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.edm.EDMPlantV1Entity;
import com.jnj.pangea.common.entity.plan.PlanCnsPlnSplLocEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanParameterEntity;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanParameterDaoImpl;
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

        if (cnsPlnSplLocEntity==null){
            return resultObject;
        }
        // rules C1
        gdmLocationXrefBo.setLocationId(cnsPlnSplLocEntity.getSourceSystem()+"_"+cnsPlnSplLocEntity.getVendorCustomer()+"_"+cnsPlnSplLocEntity.getLocalNumber());
        //rules C2
        if (getFieldWithC2(cnsPlnSplLocEntity)){
            gdmLocationXrefBo.setActiveFCTERP(IConstant.VALUE.YES);
        }

        //rules C3
        if (cnsPlnSplLocEntity.getVendorCustomer().equals("C")){
            gdmLocationXrefBo.setCustomerid(cnsPlnSplLocEntity.getLocalNumber());
        }

        //rules C4
        if (cnsPlnSplLocEntity.getVendorCustomer().equals("V")){
            gdmLocationXrefBo.setVendorid(cnsPlnSplLocEntity.getLocalNumber());
        }

        //rules D1
        gdmLocationXrefBo.setCurrencyId(IConstant.VALUE.USD);

        //rules D2
        gdmLocationXrefBo.setActive(IConstant.VALUE.YES);
        gdmLocationXrefBo.setActiveOPRERP(IConstant.VALUE.YES);

        gdmLocationXrefBo.setCountryId(cnsPlnSplLocEntity.getLocalCountry());
        gdmLocationXrefBo.setLabel(cnsPlnSplLocEntity.getLocalName());
        gdmLocationXrefBo.setLocationTypeId(cnsPlnSplLocEntity.getPlanLocTypeId());
        gdmLocationXrefBo.setRegionId(cnsPlnSplLocEntity.getLocalRegion());

        resultObject.setBaseBo(gdmLocationXrefBo);
        return resultObject;
    }

    /**
     * rules C2
     * @param cnsPlnSplLocEntity
     * @return
     */
    private boolean getFieldWithC2(PlanCnsPlnSplLocEntity cnsPlnSplLocEntity){
        List<PlanCnsPlanParameterEntity> entities = cnsPlanParameterDao.getEntitiesWithConditions(IConstant.VALUE.CONS_LATAM, IConstant.VALUE.CNS_MATERIAL_PLAN_STATUS, IConstant.VALUE.DP_RELEVANT, IConstant.VALUE.PLANT);
        if (!entities.isEmpty()){
            for (PlanCnsPlanParameterEntity cnsPlanParameterEntity:entities) {
                if (cnsPlanParameterEntity.getParameterValue().equals(cnsPlnSplLocEntity.getLocalNumber())){
                    return true;
                }
            }
        }
        return false;
    }
}
