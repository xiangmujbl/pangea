package com.jnj.pangea.omp.product_detail.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsMaterialPlanStatusDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanParameterDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.entity.plan.PlanCnsMaterialPlanStatusEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanParameterEntity;
import com.jnj.pangea.omp.product_detail.bo.OMPProductDetailBo;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class OMPProductDetailServiceImpl {

    private static OMPProductDetailServiceImpl instance;

    public static OMPProductDetailServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPProductDetailServiceImpl();
        }
        return instance;
    }

    private PlanCnsMaterialPlanStatusDaoImpl cnsMaterialPlanStatusDao = PlanCnsMaterialPlanStatusDaoImpl.getInstance();
    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    private PlanCnsPlanParameterDaoImpl cnsPlanParameterDao = PlanCnsPlanParameterDaoImpl.getInstance();

    public List<ResultObject> buildView(String key, Object o, Object o2) {

        List<ResultObject> resultObjectList = new ArrayList<ResultObject>();
        EDMMaterialGlobalV1Entity materialGlobalV1Entity = (EDMMaterialGlobalV1Entity) o;

        List<OMPProductDetailBo> BoList = new ArrayList<OMPProductDetailBo>();

        //rules J1
        String localMaterialNumber = materialGlobalV1Entity.getLocalMaterialNumber();

        PlanCnsMaterialPlanStatusEntity cnsMaterialPlanStatusEntity = cnsMaterialPlanStatusDao.getEntityWithLocalMaterialNumberAndsourceSystem(localMaterialNumber, IConstant.VALUE.CONS_LATAM);

        String sourceSystem = materialGlobalV1Entity.getSourceSystem();
        if (StringUtils.isNotEmpty(sourceSystem)) {
            EDMSourceSystemV1Entity edmSourceSystemV1Entity = sourceSystemV1Dao.getEntityWithLocalSourceSystemAndSourceSystem(IConstant.VALUE.PROJECT_ONE_DEV, sourceSystem);
            PlanCnsPlanParameterEntity cnsPlanParameterEntity = cnsPlanParameterDao.getEntityWithAttributeAndDataObject(IConstant.VALUE.CONS_LATAM, IConstant.VALUE.SEND_TO_OMP);

            //rules T1
            getFieldWithT1(materialGlobalV1Entity, cnsMaterialPlanStatusEntity, edmSourceSystemV1Entity, cnsPlanParameterEntity, BoList);
        }

        if (BoList == null || BoList.size() == 0) {
            return resultObjectList;
        }

        for (OMPProductDetailBo bo : BoList) {
            ResultObject resultObject = new ResultObject();

            //rules T3
            if (cnsMaterialPlanStatusEntity != null && (IConstant.VALUE.X.equals(cnsMaterialPlanStatusEntity.getSpRelevant()) || IConstant.VALUE.X.equals(cnsMaterialPlanStatusEntity.getNoPlanRelevant()))) {
                bo.setActiveOPRERP(IConstant.VALUE.YES);
            } else {
                bo.setActiveOPRERP(IConstant.VALUE.NO);
            }

            //rules T4 and T5
            if (IConstant.VALUE.CONS_LATAM.equals(materialGlobalV1Entity.getSourceSystem())) {
                bo.setCLASS(IConstant.VALUE.PGA);
                bo.setDescription(IConstant.VALUE.PANGEA);
            }

            //rules D1
            bo.setActiveSOPERP(IConstant.VALUE.NO);

            resultObject.setBaseBo(bo);
            resultObjectList.add(resultObject);
        }
        return resultObjectList;
    }

    /**
     * rules T1
     *
     * @param materialGlobalV1Entity
     * @param cnsMaterialPlanStatusEntity
     * @param edmSourceSystemV1Entity
     * @param cnsPlanParameterEntity
     * @param boList
     * @return
     */
    private void getFieldWithT1(EDMMaterialGlobalV1Entity materialGlobalV1Entity, PlanCnsMaterialPlanStatusEntity cnsMaterialPlanStatusEntity, EDMSourceSystemV1Entity edmSourceSystemV1Entity,PlanCnsPlanParameterEntity cnsPlanParameterEntity, List<OMPProductDetailBo> boList) {

        // one data
        OMPProductDetailBo productDetailBo1 = new OMPProductDetailBo();
        productDetailBo1.setProductDetailId(materialGlobalV1Entity.getPrimaryPlanningCode() + IConstant.VALUE.BACK_SLANT + IConstant.VALUE.PGA + IConstant.VALUE.BACK_SLANT + IConstant.VALUE.LATAM_SKU);
        productDetailBo1.setName(IConstant.VALUE.LATAM_SKU);
        productDetailBo1.setValue(materialGlobalV1Entity.getLocalMaterialNumber());
        productDetailBo1.setProductId(materialGlobalV1Entity.getPrimaryPlanningCode());
        productDetailBo1.setActiveFCTERP(IConstant.VALUE.NO);
        boList.add(productDetailBo1);

        // two data
        if (cnsMaterialPlanStatusEntity != null && IConstant.VALUE.X.equals(cnsMaterialPlanStatusEntity.getDpRelevant())
                && edmSourceSystemV1Entity != null && cnsPlanParameterEntity != null) {
            OMPProductDetailBo productDetailBo2 = new OMPProductDetailBo();
            productDetailBo2.setProductDetailId(edmSourceSystemV1Entity.getSourceSystem() + IConstant.VALUE.UNDERLINE + materialGlobalV1Entity.getLocalDpParentCode() + IConstant.VALUE.BACK_SLANT + IConstant.VALUE.PGA + IConstant.VALUE.BACK_SLANT + IConstant.VALUE.LATAM_SKU);
            productDetailBo2.setProductId(cnsPlanParameterEntity.getParameterValue() + IConstant.VALUE.UNDERLINE + materialGlobalV1Entity.getLocalDpParentCode());
            productDetailBo2.setName(IConstant.VALUE.LATAM_SKU);
            productDetailBo2.setValue(materialGlobalV1Entity.getLocalMaterialNumber());
            productDetailBo2.setActiveFCTERP(IConstant.VALUE.YES);
            boList.add(productDetailBo2);
        }

        // three and four data
        if (cnsMaterialPlanStatusEntity != null && IConstant.VALUE.X.equals(cnsMaterialPlanStatusEntity.getDpRelevant())
                && null != cnsPlanParameterEntity && null != edmSourceSystemV1Entity) {

            OMPProductDetailBo productDetailBo3 = new OMPProductDetailBo();
            productDetailBo3.setProductDetailId(materialGlobalV1Entity.getPrimaryPlanningCode() + IConstant.VALUE.BACK_SLANT + IConstant.VALUE.PGA + IConstant.VALUE.BACK_SLANT + IConstant.VALUE.LATAM_ROOT);
            productDetailBo3.setName(IConstant.VALUE.LATAM_ROOT);
            productDetailBo3.setValue(cnsPlanParameterEntity.getParameterValue() + IConstant.VALUE.UNDERLINE + materialGlobalV1Entity.getLocalDpParentCode());
            productDetailBo3.setProductId(materialGlobalV1Entity.getPrimaryPlanningCode());
            productDetailBo3.setActiveFCTERP(IConstant.VALUE.YES);
            boList.add(productDetailBo3);

            OMPProductDetailBo productDetailBo4 = new OMPProductDetailBo();
            productDetailBo4.setProductDetailId(edmSourceSystemV1Entity.getSourceSystem() + IConstant.VALUE.UNDERLINE + materialGlobalV1Entity.getLocalDpParentCode() + IConstant.VALUE.BACK_SLANT + IConstant.VALUE.PGA + IConstant.VALUE.BACK_SLANT + IConstant.VALUE.LATAM_ROOT);
            productDetailBo4.setValue(cnsPlanParameterEntity.getParameterValue() + IConstant.VALUE.UNDERLINE + materialGlobalV1Entity.getLocalDpParentCode());
            productDetailBo4.setProductId(cnsPlanParameterEntity.getParameterValue() + IConstant.VALUE.UNDERLINE + materialGlobalV1Entity.getLocalDpParentCode());
            productDetailBo4.setName(IConstant.VALUE.LATAM_ROOT);
            productDetailBo4.setActiveFCTERP(IConstant.VALUE.YES);
            boList.add(productDetailBo4);
        }

        // five data
        if (cnsMaterialPlanStatusEntity != null && IConstant.VALUE.CONS_LATAM.equals(materialGlobalV1Entity.getSourceSystem())
                && (IConstant.VALUE.X.equals(cnsMaterialPlanStatusEntity.getSpRelevant()) || IConstant.VALUE.X.equals(cnsMaterialPlanStatusEntity.getNoPlanRelevant()))
                && edmSourceSystemV1Entity != null && StringUtils.isNotEmpty(materialGlobalV1Entity.getLocalManufacturingTechnology())
                && null != cnsPlanParameterEntity) {
                OMPProductDetailBo productDetailBo5 = new OMPProductDetailBo();
                productDetailBo5.setProductDetailId(materialGlobalV1Entity.getPrimaryPlanningCode() + IConstant.VALUE.BACK_SLANT + IConstant.VALUE.PGA + IConstant.VALUE.BACK_SLANT + IConstant.VALUE.LATAM_TECH);
                productDetailBo5.setName(IConstant.VALUE.LATAM_TECH);
                productDetailBo5.setValue(materialGlobalV1Entity.getLocalManufacturingTechnology());
                productDetailBo5.setProductId(materialGlobalV1Entity.getPrimaryPlanningCode());
                productDetailBo5.setActiveFCTERP(IConstant.VALUE.NO);
                boList.add(productDetailBo5);
        }
    }

}
