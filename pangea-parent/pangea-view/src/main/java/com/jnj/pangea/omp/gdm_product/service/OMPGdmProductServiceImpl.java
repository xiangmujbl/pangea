package com.jnj.pangea.omp.gdm_product.service;

import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.*;
import com.jnj.pangea.common.entity.edm.*;
import com.jnj.pangea.common.entity.plan.CnsPlanUnitEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsMaterialPlanStatusEntity;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsMaterialPlanStatusDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanUnitDaoImpl;
import com.jnj.pangea.omp.gdm_product.bo.OMPGdmProductBo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class OMPGdmProductServiceImpl {

    private static OMPGdmProductServiceImpl instance;

    public static OMPGdmProductServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmProductServiceImpl();
        }
        return instance;
    }

    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    private PlanCnsMaterialPlanStatusDaoImpl cnsMaterialPlanStatusDao = PlanCnsMaterialPlanStatusDaoImpl.getInstance();
    private EDMProductFamilyV1DaoImpl productFamilyV1Dao = EDMProductFamilyV1DaoImpl.getInstance();
    private EDMFormV1DaoImpl formV1Dao = EDMFormV1DaoImpl.getInstance();
    private EDMCategoryV1DaoImpl categoryV1Dao = EDMCategoryV1DaoImpl.getInstance();
    private EDMSubBrandV1DaoImpl subBrandV1Dao = EDMSubBrandV1DaoImpl.getInstance();
    private EDMBrandV1DaoImpl brandV1Dao = EDMBrandV1DaoImpl.getInstance();
    private EDMFranchiseV1DaoImpl franchiseV1Dao = EDMFranchiseV1DaoImpl.getInstance();
    private EDMGlobalBaseUnitV1DaoImpl globalBaseUnitV1Dao = EDMGlobalBaseUnitV1DaoImpl.getInstance();
    private PlanCnsPlanUnitDaoImpl cnsPlanUnitDao = PlanCnsPlanUnitDaoImpl.getInstance();

    public List<ResultObject> buildView(String key, Object o, Object o2) {

        List<ResultObject> resultObjects = new LinkedList<>();
        EDMMaterialGlobalV1Entity materialGlobalV1Entity = (EDMMaterialGlobalV1Entity) o;

        String primaryPlanningCode = materialGlobalV1Entity.getPrimaryPlanningCode();
        String localDPParentCode = materialGlobalV1Entity.getLocalDpParentCode();
        String sourceSystem = materialGlobalV1Entity.getSourceSystem();

        EDMSourceSystemV1Entity sourceSystemV1Entity = sourceSystemV1Dao.getEntityWithLocalSourceSystem(sourceSystem);
        String sourceSystemSS = "";
        if (null != sourceSystemV1Entity) {
            sourceSystemSS = sourceSystemV1Entity.getSourceSystem();
        }

        PlanCnsMaterialPlanStatusEntity materialPlanStatusEntity = cnsMaterialPlanStatusDao.getEntityWithLocalMaterialNumber(materialGlobalV1Entity.getLocalMaterialNumber());

        if (null != materialPlanStatusEntity) {

            List<OMPGdmProductBo> productBos = new ArrayList<>();

            if ((IConstant.VALUE.X.equals(materialPlanStatusEntity.getSpRelevant()) || IConstant.VALUE.X.equals(materialPlanStatusEntity.getNoPlanRelevant())) &&
                    IConstant.VALUE.X.equals(materialPlanStatusEntity.getDpRelevant())) {

                if (null == primaryPlanningCode || null == localDPParentCode){
                    ResultObject resultObject = new ResultObject();
                    FailData failData = writeFailDataToRegion(materialGlobalV1Entity, IConstant.FAILED.ERROR_CODE.J1, "Unable to find DPParentCode or PrimaryPlanningCode");
                    resultObject.setFailData(failData);
                    resultObjects.add(resultObject);
                    return resultObjects;
                }

                for (int i=0;i<2;i++){
                    OMPGdmProductBo gdmProductBo = new OMPGdmProductBo();
                    if (i==0){
                        gdmProductBo.setProductId(primaryPlanningCode);
                    }else if(i==1){
                        gdmProductBo.setProductId(sourceSystemSS+IConstant.VALUE.UNDERLINE+localDPParentCode);
                    }
                    gdmProductBo.setActive(IConstant.VALUE.YES);
                    gdmProductBo.setActiveFcterp(IConstant.VALUE.YES);
                    gdmProductBo.setActiveOprerp(IConstant.VALUE.YES);
                    gdmProductBo.setActiveSoperp(IConstant.VALUE.YES);

                    productBos.add(gdmProductBo);
                }

            } else if (IConstant.VALUE.X.equals(materialPlanStatusEntity.getSpRelevant()) ||
                    IConstant.VALUE.X.equals(materialPlanStatusEntity.getNoPlanRelevant())) {
                if (null == primaryPlanningCode){
                    ResultObject resultObject = new ResultObject();
                    FailData failData = writeFailDataToRegion(materialGlobalV1Entity, IConstant.FAILED.ERROR_CODE.J1, "primaryPlanningCode is not available for SPRelevant mateial");
                    resultObject.setFailData(failData);
                    resultObjects.add(resultObject);
                    return resultObjects;
                }

                OMPGdmProductBo gdmProductBo = new OMPGdmProductBo();
                gdmProductBo.setProductId(primaryPlanningCode);
                gdmProductBo.setActive(IConstant.VALUE.YES);
                gdmProductBo.setActiveOprerp(IConstant.VALUE.YES);
                gdmProductBo.setActiveSoperp(IConstant.VALUE.YES);

                productBos.add(gdmProductBo);

            } else if (IConstant.VALUE.X.equals(materialPlanStatusEntity.getDpRelevant())) {
                if (null == localDPParentCode){
                    ResultObject resultObject = new ResultObject();
                    FailData failData = writeFailDataToRegion(materialGlobalV1Entity, IConstant.FAILED.ERROR_CODE.J1, "Unable to find DPParentCode");
                    resultObject.setFailData(failData);
                    resultObjects.add(resultObject);
                    return resultObjects;
                }

                OMPGdmProductBo gdmProductBo = new OMPGdmProductBo();
                gdmProductBo.setProductId(sourceSystemSS+IConstant.VALUE.UNDERLINE+localDPParentCode);
                gdmProductBo.setActive(IConstant.VALUE.YES);
                gdmProductBo.setActiveFcterp(IConstant.VALUE.YES);
                gdmProductBo.setActiveSoperp(IConstant.VALUE.YES);
                productBos.add(gdmProductBo);
            }

            for (OMPGdmProductBo productBo:productBos) {

                ResultObject resultObject = new ResultObject();

                String refDescription = materialGlobalV1Entity.getRefDescription();

                productBo.setDescription(refDescription);
                productBo.setLabel(refDescription);
                productBo.setMatkl(materialGlobalV1Entity.getMaterialGroup());

                String productFamily = materialGlobalV1Entity.getProductFamily();
                if (null != productFamily && !"".equals(productFamily)){
                    EDMProductFamilyV1Entity productFamilyV1Entity = productFamilyV1Dao.getEntityWithProductFamily(productFamily);
                    productBo.setPlanningHierarchy1(productFamily);
                    if (null != productFamilyV1Entity){
                        productBo.setPlanningHierarchy1Desc(productFamilyV1Entity.getProductFamilyName());
                    }
                }

                String form = materialGlobalV1Entity.getForm();
                if (null != form && !"".equals(form)){
                    EDMFormV1Entity formV1Entity = formV1Dao.getEntityWithFormName(form);
                    productBo.setPlanningHierarchy2(form);
                    if (null != formV1Entity){
                        productBo.setPlanningHierarchy2Desc(formV1Entity.getFormDescription());
                    }
                }else {
                    FailData failData = writeFailDataToRegion(materialGlobalV1Entity, IConstant.FAILED.ERROR_CODE.E3, "There is no Form assigned for product");
                    resultObject.setFailData(failData);
                    resultObjects.add(resultObject);
                    return resultObjects;
                }

                String category = materialGlobalV1Entity.getCategory();
                if (null != category && !"".equals(category)){
                    EDMCategoryV1Entity categoryV1Entity = categoryV1Dao.getEntityWithCategory(category);
                    productBo.setPlanningHierarchy3(category);
                    if (null != categoryV1Entity){
                        productBo.setPlanningHierarchy3Desc(categoryV1Entity.getCategoryName());
                    }
                }else {
                    FailData failData = writeFailDataToRegion(materialGlobalV1Entity, IConstant.FAILED.ERROR_CODE.E4, "There is no Category assigned for product");
                    resultObject.setFailData(failData);
                    resultObjects.add(resultObject);
                    return resultObjects;
                }

                String subBrand = materialGlobalV1Entity.getSubBrand();
                if (null != subBrand && !"".equals(subBrand)){
                    EDMSubBrandV1Entity subBrandV1Entity = subBrandV1Dao.getEntityWithSubBrand(subBrand);
                    productBo.setPlanningHierarchy4(subBrand);
                    if (null != subBrandV1Entity){
                        productBo.setPlanningHierarchy4Desc(subBrandV1Entity.getSubBrandDescription());
                    }
                }else {
                    FailData failData = writeFailDataToRegion(materialGlobalV1Entity, IConstant.FAILED.ERROR_CODE.E5, "There is no subBrand assigned for product");
                    resultObject.setFailData(failData);
                    resultObjects.add(resultObject);
                    return resultObjects;
                }

                String brand = materialGlobalV1Entity.getBrand();
                if (null != brand && !"".equals(brand)){
                    EDMBrandV1Entity brandV1Entity = brandV1Dao.getEntityWithBrand(brand);
                    productBo.setPlanningHierarchy5(brand);
                    if (null != brandV1Entity){
                        productBo.setPlanningHierarchy5Desc(brandV1Entity.getBrandDescription());
                    }
                }else {
                    FailData failData = writeFailDataToRegion(materialGlobalV1Entity, IConstant.FAILED.ERROR_CODE.E6, "There is no brand assigned for product");
                    resultObject.setFailData(failData);
                    resultObjects.add(resultObject);
                    return resultObjects;
                }

                String franchise = materialGlobalV1Entity.getFranchise();
                if (null != franchise && !"".equals(franchise)){
                    EDMFranchiseV1Entity franchiseV1Entity = franchiseV1Dao.getEntityWithFranchise(franchise);
                    productBo.setPlanningHierarchy6(franchise);
                    if (null != franchiseV1Entity){
                        productBo.setPlanningHierarchy6Desc(franchiseV1Entity.getFranchiseDescription());
                    }
                }else {
                    FailData failData = writeFailDataToRegion(materialGlobalV1Entity, IConstant.FAILED.ERROR_CODE.E7, "There is no franchise assigned for product");
                    resultObject.setFailData(failData);
                    resultObjects.add(resultObject);
                    return resultObjects;
                }

                String globalBusinessUnit = materialGlobalV1Entity.getGlobalBusinessUnit();
                if (null != globalBusinessUnit && !"".equals(globalBusinessUnit)){
                    EDMGlobalBaseUnitV1Entity globalBaseUnitV1Entity = globalBaseUnitV1Dao.getEntityWithGbu(globalBusinessUnit);
                    productBo.setPlanningHierarchy7(globalBusinessUnit);
                    if (null != globalBaseUnitV1Entity){
                        productBo.setPlanningHierarchy7Desc(globalBaseUnitV1Entity.getGbuName());
                    }
                }else {
                    FailData failData = writeFailDataToRegion(materialGlobalV1Entity, IConstant.FAILED.ERROR_CODE.E8, "There is no globalBusinessUnit assigned for product");
                    resultObject.setFailData(failData);
                    resultObjects.add(resultObject);
                    return resultObjects;
                }

                productBo.setShortDescription(refDescription);
                productBo.setTechnology(materialGlobalV1Entity.getLocalManufacturingTechnology());

                String localBaseUom = materialGlobalV1Entity.getLocalBaseUom();
                if (null != localBaseUom && !"".equals(localBaseUom)){
                    CnsPlanUnitEntity planUnitEntity = checkE9(materialPlanStatusEntity,localBaseUom);
                    if (null != planUnitEntity){
                        productBo.setUnitId(planUnitEntity.getUnit());
                    }else{
                        FailData failData = writeFailDataToRegion(materialGlobalV1Entity, IConstant.FAILED.ERROR_CODE.E9, "No Plannable Enterprise UOM has been assigned to the local Unit");
                        resultObject.setFailData(failData);
                        resultObjects.add(resultObject);
                        return resultObjects;
                    }
                }else{
                    FailData failData = writeFailDataToRegion(materialGlobalV1Entity, IConstant.FAILED.ERROR_CODE.E9, "No Plannable Enterprise UOM has been assigned to the local Unit");
                    resultObject.setFailData(failData);
                    resultObjects.add(resultObject);
                    return resultObjects;
                }

                resultObject.setBaseBo(productBo);
                resultObjects.add(resultObject);
            }
        }
        return resultObjects;
    }


    private CnsPlanUnitEntity checkE9(PlanCnsMaterialPlanStatusEntity materialPlanStatusEntity, String localBaseUom){

        CnsPlanUnitEntity planUnitEntity = null;

        if (IConstant.VALUE.X.equals(materialPlanStatusEntity.getSpRelevant()) && IConstant.VALUE.X.equals(materialPlanStatusEntity.getDpRelevant())){

            planUnitEntity = cnsPlanUnitDao.getEntityWithLocalUomAndPlanFlag(localBaseUom,IConstant.VALUE.DPSP);

        }else if(IConstant.VALUE.X.equals(materialPlanStatusEntity.getDpRelevant())){

            planUnitEntity = cnsPlanUnitDao.getEntityWithLocalUomAndPlanFlag(localBaseUom,IConstant.VALUE.DP);

        }else if (IConstant.VALUE.X.equals(materialPlanStatusEntity.getSpRelevant())){

            planUnitEntity = cnsPlanUnitDao.getEntityWithLocalUomAndPlanFlag(localBaseUom,IConstant.VALUE.SP1);

        }

        return planUnitEntity;
    }

    private List<OMPGdmProductBo> checkJ1AndE1(EDMMaterialGlobalV1Entity materialGlobalV1Entity,PlanCnsMaterialPlanStatusEntity materialPlanStatusEntity,String primaryPlanningCode,String localDPParentCode){
        List<OMPGdmProductBo> productBos = new ArrayList<>();

        if ((IConstant.VALUE.X.equals(materialPlanStatusEntity.getSpRelevant()) || IConstant.VALUE.X.equals(materialPlanStatusEntity.getNoPlanRelevant())) &&
                IConstant.VALUE.X.equals(materialPlanStatusEntity.getDpRelevant())) {

            if (null == materialGlobalV1Entity.getPrimaryPlanningCode() || null == materialGlobalV1Entity.getLocalDpParentCode()){

            }
            for (int i=0;i<2;i++){
                OMPGdmProductBo gdmProductBo = new OMPGdmProductBo();
                if (i==0){
                    gdmProductBo.setProductId(primaryPlanningCode);
                }else if(i==1){
                    gdmProductBo.setProductId(localDPParentCode);
                }
                gdmProductBo.setActive(IConstant.VALUE.YES);
                gdmProductBo.setActiveFcterp(IConstant.VALUE.YES);
                gdmProductBo.setActiveOprerp(IConstant.VALUE.YES);
                gdmProductBo.setActiveSoperp(IConstant.VALUE.YES);

                productBos.add(gdmProductBo);
            }

        } else if (IConstant.VALUE.X.equals(materialPlanStatusEntity.getSpRelevant()) ||
                IConstant.VALUE.X.equals(materialPlanStatusEntity.getNoPlanRelevant())) {

            OMPGdmProductBo gdmProductBo = new OMPGdmProductBo();
            gdmProductBo.setProductId(primaryPlanningCode);
            gdmProductBo.setActive(IConstant.VALUE.YES);
            gdmProductBo.setActiveOprerp(IConstant.VALUE.YES);
            gdmProductBo.setActiveSoperp(IConstant.VALUE.YES);

            productBos.add(gdmProductBo);

        } else if (IConstant.VALUE.X.equals(materialPlanStatusEntity.getDpRelevant())) {

            OMPGdmProductBo gdmProductBo = new OMPGdmProductBo();
            gdmProductBo.setProductId(localDPParentCode);
            gdmProductBo.setActive(IConstant.VALUE.YES);
            gdmProductBo.setActiveFcterp(IConstant.VALUE.YES);
            gdmProductBo.setActiveSoperp(IConstant.VALUE.YES);
            productBos.add(gdmProductBo);
        }

        return productBos;
    }

    private FailData writeFailDataToRegion(EDMMaterialGlobalV1Entity materialGlobalV1Entity, String ruleCode, String errorValue) {
        FailData failData = new FailData();
        failData.setFunctionalArea(IConstant.FAILED.FUNCTIONAL_AREA.SP);
        failData.setInterfaceID(IConstant.FAILED.INTERFACE_ID.OMP_GDM_PRODUCT);
        failData.setErrorCode(ruleCode);
        failData.setSourceSystem(materialGlobalV1Entity.getSourceSystem());
        failData.setKey1(materialGlobalV1Entity.getLocalMaterialNumber());
        failData.setKey2("");
        failData.setKey3("");
        failData.setKey4("");
        failData.setKey5("");
        failData.setErrorValue(errorValue);
        return failData;
    }

}
