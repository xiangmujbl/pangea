package com.jnj.pangea.omp.gdm_product.service;

import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.*;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsMaterialPlanStatusDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanUnitDaoImpl;
import com.jnj.pangea.common.entity.edm.*;
import com.jnj.pangea.common.entity.plan.CnsPlanUnitEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsMaterialPlanStatusEntity;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsMaterialPlanStatusDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMProductFamilyV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMProductFamilyV1DaoImpl;
import com.jnj.pangea.common.entity.edm.EDMFormV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMFormV1DaoImpl;
import com.jnj.pangea.common.entity.edm.EDMCategoryV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMCategoryV1DaoImpl;
import com.jnj.pangea.common.entity.edm.EDMSubBrandV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMSubBrandV1DaoImpl;
import com.jnj.pangea.common.entity.edm.EDMBrandV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMBrandV1DaoImpl;
import com.jnj.pangea.common.entity.edm.EDMFranchiseV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMFranchiseV1DaoImpl;
import com.jnj.pangea.common.entity.edm.EDMGlobalBaseUnitV1Entity;
import com.jnj.pangea.common.dao.impl.edm.EDMGlobalBaseUnitV1DaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanUnitDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanUnitEntity;
import com.jnj.pangea.omp.gdm_product.bo.OMPGdmProductBo;
import org.apache.commons.lang.StringUtils;

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

        PlanCnsMaterialPlanStatusEntity materialPlanStatusEntity = cnsMaterialPlanStatusDao.getEntityWithLocalMaterialNumberSourceSystemAndRelevant(materialGlobalV1Entity.getSourceSystem(), materialGlobalV1Entity.getLocalMaterialNumber());

        if (null != materialPlanStatusEntity && (IConstant.VALUE.X.equals(materialPlanStatusEntity.getDpRelevant()) || IConstant.VALUE.X.equals(materialPlanStatusEntity.getSpRelevant()) || IConstant.VALUE.X.equals(materialPlanStatusEntity.getNoPlanRelevant()))) {

            List<OMPGdmProductBo> productBos = new ArrayList<>();

            if (IConstant.VALUE.X.equals(materialPlanStatusEntity.getSpRelevant()) || IConstant.VALUE.X.equals(materialPlanStatusEntity.getNoPlanRelevant())) {
                if (null != primaryPlanningCode && !"".equals(primaryPlanningCode)) {
                    OMPGdmProductBo gdmProductBo = new OMPGdmProductBo();
                    gdmProductBo.setProductId(primaryPlanningCode);
                    productBos.add(gdmProductBo);
                }
            }
            if (IConstant.VALUE.X.equals(materialPlanStatusEntity.getDpRelevant())) {
                if (null != localDPParentCode && !"".equals(localDPParentCode)) {
                    OMPGdmProductBo gdmProductBo = new OMPGdmProductBo();
                    gdmProductBo.setProductId(sourceSystem + IConstant.VALUE.UNDERLINE + localDPParentCode);
                    productBos.add(gdmProductBo);
                }
            }

            for (OMPGdmProductBo productBo : productBos) {

                ResultObject resultObject = new ResultObject();

                productBo.setActive(IConstant.VALUE.NO);
                productBo.setActiveFCTERP(IConstant.VALUE.NO);
                productBo.setActiveOPRERP(IConstant.VALUE.NO);

                checkE1(productBo, materialPlanStatusEntity);

                productBo.setActiveSOPERP(IConstant.VALUE.NO);

                String refDescription = materialGlobalV1Entity.getRefDescription();

                productBo.setDescription(refDescription);
                productBo.setLabel(refDescription);
                productBo.setMatkl(materialGlobalV1Entity.getMaterialGroup());

                String productFamily = materialGlobalV1Entity.getProductFamily();
                productBo.setPlanningHierarchy1(productFamily);
                String form = materialGlobalV1Entity.getForm();
                productBo.setPlanningHierarchy2(form);
                String category = materialGlobalV1Entity.getCategory();
                productBo.setPlanningHierarchy3(category);
                String subBrand = materialGlobalV1Entity.getSubBrand();
                productBo.setPlanningHierarchy4(subBrand);
                String brand = materialGlobalV1Entity.getBrand();
                productBo.setPlanningHierarchy5(brand);
                String franchise = materialGlobalV1Entity.getFranchise();
                productBo.setPlanningHierarchy6(franchise);
                String globalBusinessUnit = materialGlobalV1Entity.getGlobalBusinessUnit();
                productBo.setPlanningHierarchy7(globalBusinessUnit);

                String dpRelevant = materialPlanStatusEntity.getDpRelevant();
                if (IConstant.VALUE.X.equals(dpRelevant)) {

                    productBo.setPlanningHierarchy1Desc(checkE2(productFamily));

                    if (StringUtils.isNotEmpty(form)) {
                        productBo.setPlanningHierarchy2Desc(checkE3(form));
                    } else {
                        FailData failData = writeFailDataToRegion(materialGlobalV1Entity, IConstant.FAILED.ERROR_CODE.E3, "There is no Form assigned for product");
                        resultObject.setFailData(failData);
                        resultObjects.add(resultObject);
                        return resultObjects;
                    }

                    if (StringUtils.isNotEmpty(category)) {
                        productBo.setPlanningHierarchy3Desc(checkE4(category));
                    } else {
                        FailData failData = writeFailDataToRegion(materialGlobalV1Entity, IConstant.FAILED.ERROR_CODE.E4, "There is no Category assigned for product");
                        resultObject.setFailData(failData);
                        resultObjects.add(resultObject);
                        return resultObjects;
                    }

                    if (StringUtils.isNotEmpty(subBrand)) {
                        productBo.setPlanningHierarchy4Desc(checkE5(subBrand));
                    } else {
                        FailData failData = writeFailDataToRegion(materialGlobalV1Entity, IConstant.FAILED.ERROR_CODE.E5, "There is no subBrand assigned for product");
                        resultObject.setFailData(failData);
                        resultObjects.add(resultObject);
                        return resultObjects;
                    }

                    if (StringUtils.isNotEmpty(brand)) {
                        productBo.setPlanningHierarchy5Desc(checkE6(brand));
                    } else {
                        FailData failData = writeFailDataToRegion(materialGlobalV1Entity, IConstant.FAILED.ERROR_CODE.E6, "There is no brand assigned for product");
                        resultObject.setFailData(failData);
                        resultObjects.add(resultObject);
                        return resultObjects;
                    }

                    if (StringUtils.isNotEmpty(franchise)) {
                        productBo.setPlanningHierarchy6Desc(checkE7(franchise));
                    } else {
                        FailData failData = writeFailDataToRegion(materialGlobalV1Entity, IConstant.FAILED.ERROR_CODE.E7, "There is no franchise assigned for product");
                        resultObject.setFailData(failData);
                        resultObjects.add(resultObject);
                        return resultObjects;
                    }

                    if (StringUtils.isNotEmpty(globalBusinessUnit)) {
                        productBo.setPlanningHierarchy7Desc(checkE8(globalBusinessUnit));
                    } else {
                        FailData failData = writeFailDataToRegion(materialGlobalV1Entity, IConstant.FAILED.ERROR_CODE.E8, "There is no globalBusinessUnit assigned for product");
                        resultObject.setFailData(failData);
                        resultObjects.add(resultObject);
                        return resultObjects;
                    }
                }

                productBo.setShortDescription(refDescription);
                productBo.setTechnology(materialGlobalV1Entity.getLocalManufacturingTechnology());

                String localBaseUom = materialGlobalV1Entity.getLocalBaseUom();
                if (null != localBaseUom && !"".equals(localBaseUom)) {
                    PlanCnsPlanUnitEntity planUnitEntity = checkE9(materialPlanStatusEntity, localBaseUom);
                    if (null != planUnitEntity) {
                        productBo.setUnitId(planUnitEntity.getUnit());
                    } else {
                        FailData failData = writeFailDataToRegion(materialGlobalV1Entity, IConstant.FAILED.ERROR_CODE.E9, "No Plannable Enterprise UOM has been assigned to the local Unit");
                        resultObject.setFailData(failData);
                        resultObjects.add(resultObject);
                        return resultObjects;
                    }
                } else {
                    FailData failData = writeFailDataToRegion(materialGlobalV1Entity, IConstant.FAILED.ERROR_CODE.E9, "No Plannable Enterprise UOM has been assigned to the local Unit");
                    resultObject.setFailData(failData);
                    resultObjects.add(resultObject);
                    return resultObjects;
                }
                resultObject.setBaseBo(productBo);
                resultObjects.add(resultObject);
            }
        } else {
            ResultObject resultObject = new ResultObject();
            FailData failData = writeFailDataToRegion(materialGlobalV1Entity, IConstant.FAILED.ERROR_CODE.J1, "Unable to find DPParentCode");
            resultObject.setFailData(failData);
            resultObjects.add(resultObject);
            return resultObjects;
        }
        return resultObjects;
    }

    private OMPGdmProductBo checkE1(OMPGdmProductBo productBo, PlanCnsMaterialPlanStatusEntity materialPlanStatusEntity) {
        if (IConstant.VALUE.X.equals(materialPlanStatusEntity.getDpRelevant()) || IConstant.VALUE.X.equals(materialPlanStatusEntity.getSpRelevant()) || IConstant.VALUE.X.equals(materialPlanStatusEntity.getNoPlanRelevant())) {
            productBo.setActive(IConstant.VALUE.YES);
            if (IConstant.VALUE.X.equals(materialPlanStatusEntity.getSpRelevant()) || IConstant.VALUE.X.equals(materialPlanStatusEntity.getNoPlanRelevant())) {
                productBo.setActiveOPRERP(IConstant.VALUE.YES);
            }
            if (IConstant.VALUE.X.equals(materialPlanStatusEntity.getDpRelevant())) {
                productBo.setActiveFCTERP(IConstant.VALUE.YES);
            }
        }
        return productBo;
    }

    private String checkE2(String productFamily) {
        if (null != productFamily && !"".equals(productFamily)) {
            EDMProductFamilyV1Entity productFamilyV1Entity = productFamilyV1Dao.getEntityWithProductFamily(productFamily);
            if (null != productFamilyV1Entity) {
                return productFamilyV1Entity.getProductFamilyName();
            }
        }
        return null;
    }

    private String checkE3(String form) {
        if (null != form && !"".equals(form)) {
            EDMFormV1Entity formV1Entity = formV1Dao.getEntityWithFormName(form);
            if (null != formV1Entity) {
                return formV1Entity.getFormDescription();
            }
        }
        return null;
    }

    private String checkE4(String category) {
        if (null != category && !"".equals(category)) {
            EDMCategoryV1Entity categoryV1Entity = categoryV1Dao.getEntityWithCategory(category);
            if (null != categoryV1Entity) {
                return categoryV1Entity.getCategoryName();
            }
        }
        return null;
    }

    private String checkE5(String subBrand) {
        if (null != subBrand && !"".equals(subBrand)) {
            EDMSubBrandV1Entity subBrandV1Entity = subBrandV1Dao.getEntityWithSubBrand(subBrand);
            if (null != subBrandV1Entity) {
                return subBrandV1Entity.getSubBrandDescription();
            }
        }
        return null;
    }

    private String checkE6(String brand) {
        if (null != brand && !"".equals(brand)) {
            EDMBrandV1Entity brandV1Entity = brandV1Dao.getEntityWithBrand(brand);
            if (null != brandV1Entity) {
                return brandV1Entity.getBrandDescription();
            }
        }
        return null;
    }

    private String checkE7(String franchise) {
        if (null != franchise && !"".equals(franchise)) {
            EDMFranchiseV1Entity franchiseV1Entity = franchiseV1Dao.getEntityWithFranchise(franchise);
            if (null != franchiseV1Entity) {
                return franchiseV1Entity.getFranchiseDescription();
            }
        }
        return null;
    }

    private String checkE8(String globalBusinessUnit) {
        if (null != globalBusinessUnit && !"".equals(globalBusinessUnit)) {
            EDMGlobalBaseUnitV1Entity globalBaseUnitV1Entity = globalBaseUnitV1Dao.getEntityWithGbu(globalBusinessUnit);
            if (null != globalBaseUnitV1Entity) {
                return globalBaseUnitV1Entity.getGbuName();
            }
        }
        return null;
    }

    private PlanCnsPlanUnitEntity checkE9(PlanCnsMaterialPlanStatusEntity materialPlanStatusEntity, String localBaseUom) {
        if (IConstant.VALUE.X.equals(materialPlanStatusEntity.getSpRelevant()) || IConstant.VALUE.X.equals(materialPlanStatusEntity.getDpRelevant()) || IConstant.VALUE.X.equals(materialPlanStatusEntity.getNoPlanRelevant())) {
            PlanCnsPlanUnitEntity planUnitEntity = cnsPlanUnitDao.getCnsPlanUnitEntityWithLocalUom(localBaseUom);
            return planUnitEntity;
        }
        return null;
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
