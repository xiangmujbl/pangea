package com.jnj.pangea.omp.gdm_supply.service;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialGlobalDaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialPlantV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMPlantV1DaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsMaterialPlanStatusDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlnSplLocDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsProcessTypeDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.entity.edm.EDMMaterialPlantV1Entity;
import com.jnj.pangea.common.entity.edm.EDMPlantV1Entity;
import com.jnj.pangea.common.entity.edm.EDMSourceListV1Entity;
import com.jnj.pangea.common.entity.plan.PlanCnsMaterialPlanStatusEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsPlnSplLocEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsProcessTypeEntity;
import com.jnj.pangea.omp.gdm_supply.bo.OMPGdmSupplyBo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OMPGdmSupplyServiceImpl {

    private static OMPGdmSupplyServiceImpl instance;

    public static OMPGdmSupplyServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmSupplyServiceImpl();
        }
        return instance;
    }

    private EDMMaterialGlobalDaoImpl materialGlobalDao = EDMMaterialGlobalDaoImpl.getInstance();
    private EDMPlantV1DaoImpl plantDao = EDMPlantV1DaoImpl.getInstance();
    private EDMMaterialPlantV1DaoImpl materialPlantDao = EDMMaterialPlantV1DaoImpl.getInstance();
    private PlanCnsProcessTypeDaoImpl cnsProcessTypeDao = PlanCnsProcessTypeDaoImpl.getInstance();
    private PlanCnsMaterialPlanStatusDaoImpl cnsMaterialPlanStatusDao = PlanCnsMaterialPlanStatusDaoImpl.getInstance();
    private PlanCnsPlnSplLocDaoImpl planCnsPlnSplLocDao = PlanCnsPlnSplLocDaoImpl.getInstance();


    public List<ResultObject> buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        OMPGdmSupplyBo gdmSupplyBo = new OMPGdmSupplyBo();
        List<ResultObject> resultObjects = new ArrayList<>();
        List<ResultObject> skipObjects = new ArrayList<>();

        EDMSourceListV1Entity edmSourceListV1Entity = (EDMSourceListV1Entity) o;

        // N1
        String partA;
        EDMMaterialGlobalV1Entity materialGlobalV1Entity = materialGlobalDao.getEntityWithLocalMaterialNumber(edmSourceListV1Entity.getLocalMaterialNumber());
        if(null != materialGlobalV1Entity && (!(materialGlobalV1Entity.getPrimaryPlanningCode().isEmpty() && materialGlobalV1Entity.getMaterialNumber().isEmpty()))) {
            if (materialGlobalV1Entity.getPrimaryPlanningCode() != null && (!(materialGlobalV1Entity.getPrimaryPlanningCode().isEmpty()))) {
                partA = materialGlobalV1Entity.getPrimaryPlanningCode();
            } else {
                partA = materialGlobalV1Entity.getMaterialNumber();
            }
            if (materialGlobalV1Entity.getPrimaryPlanningCode().equals(materialGlobalV1Entity.getMaterialNumber())) {

                if (edmSourceListV1Entity.getLocalPlant() != null && (!(edmSourceListV1Entity.getLocalPlant().isEmpty()))) {
                    if (edmSourceListV1Entity.getLocalVendorAccountNumber() != null && (!(edmSourceListV1Entity.getLocalVendorAccountNumber().isEmpty()))) {
                        if (edmSourceListV1Entity.getSourceSystem() != null && (!(edmSourceListV1Entity.getSourceSystem().isEmpty()))) {
                            String supplyId = partA + IConstant.VALUE.BACK_SLANT
                                    + edmSourceListV1Entity.getSourceSystem() + IConstant.VALUE.UNDERLINE + edmSourceListV1Entity.getLocalPlant() + IConstant.VALUE.BACK_SLANT
                                    + edmSourceListV1Entity.getLocalVendorAccountNumber();
                            gdmSupplyBo.setSupplyId(supplyId);

                            // N2
                            gdmSupplyBo.setActive(IConstant.VALUE.YES);
                            gdmSupplyBo.setActiveOPRERP(IConstant.VALUE.YES);

                            // N20
                            gdmSupplyBo.setActiveSOPERP(IConstant.VALUE.NO);

                            // N15 toDate
                            String dateToFormat_1 = edmSourceListV1Entity.getLocalSourceListRecordValidTo();
                            SimpleDateFormat sdfFrom_1 = new SimpleDateFormat(IConstant.VALUE.YYYYMMDD);
                            SimpleDateFormat sdfTo_1 = new SimpleDateFormat(IConstant.VALUE.YYYYMMDDHHMMSS);

                            Date dFrom_1 = null;
                            try {
                                dFrom_1 = sdfFrom_1.parse(dateToFormat_1);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            String toDate = sdfTo_1.format(dFrom_1);
                            gdmSupplyBo.setToDate(toDate);

                            // N3
                            EDMPlantV1Entity edmPlantV1Entity = plantDao.getPlantWithSourceSystemAndLocalPlant(edmSourceListV1Entity.getSourceSystem(), edmSourceListV1Entity.getLocalPlant());
                            if (edmPlantV1Entity != null) {
                                if (edmPlantV1Entity.getLocalPlanningRelevant() != null && edmPlantV1Entity.getLocalPlanningRelevant().equals(IConstant.VALUE.X)) {

                                    gdmSupplyBo.setLocationId(edmSourceListV1Entity.getSourceSystem() + IConstant.VALUE.UNDERLINE + edmSourceListV1Entity.getLocalPlant());

                                    // N6
                                    EDMMaterialPlantV1Entity edmMaterialPlantV1Entity = materialPlantDao.getPlantWithSourceSystemAndLocalPlantAndLocalMaterialNumber(edmSourceListV1Entity.getSourceSystem(), edmSourceListV1Entity.getLocalPlant(), edmSourceListV1Entity.getLocalMaterialNumber());

                                    if (edmMaterialPlantV1Entity != null) {
                                        gdmSupplyBo.setMaxQuantity(edmMaterialPlantV1Entity.getLocalMaximumLotSize());
                                        gdmSupplyBo.setMinQuantity(edmMaterialPlantV1Entity.getLocalMinimumLotSize());
                                    } else {
                                        gdmSupplyBo.setMaxQuantity("");
                                        gdmSupplyBo.setMinQuantity("");
                                    }

                                    // N10
                                    if (!(edmSourceListV1Entity.getLocalFixedvendor().isEmpty()) || (!(edmSourceListV1Entity.getLocalFixedOutlinePurchaseAgreementItem().isEmpty()))) {
                                        gdmSupplyBo.setPreference(IConstant.VALUE.ZERO);
                                    } else {
                                        gdmSupplyBo.setPreference(IConstant.VALUE.ONE);
                                    }

                                    // N11
                                    PlanCnsMaterialPlanStatusEntity planCnsMaterialPlanStatusEntity = cnsMaterialPlanStatusDao.getEntityWithSourceSystemAndLocalMaterialNumberAndLocalPlant(edmSourceListV1Entity.getSourceSystem(), edmSourceListV1Entity.getLocalMaterialNumber(), edmSourceListV1Entity.getLocalPlant());
                                    if (planCnsMaterialPlanStatusEntity != null) {
                                        if (planCnsMaterialPlanStatusEntity.getSpRelevant() != null && (!(planCnsMaterialPlanStatusEntity.getSpRelevant().isEmpty())) && planCnsMaterialPlanStatusEntity.getSpRelevant().equals(IConstant.VALUE.X)) {
                                            String primaryPlanningCode = materialGlobalV1Entity.getPrimaryPlanningCode();
                                            String materialNumber = materialGlobalV1Entity.getMaterialNumber();
                                            if (materialNumber != null && (!(materialNumber.isEmpty())) && primaryPlanningCode != null && (!(primaryPlanningCode.isEmpty()))) {

                                                if ((!(primaryPlanningCode.isEmpty()))) {
                                                    gdmSupplyBo.setProductId(materialGlobalV1Entity.getMaterialNumber());
                                                } else if (primaryPlanningCode.equals(materialNumber)) {
                                                    gdmSupplyBo.setProductId(materialGlobalV1Entity.getPrimaryPlanningCode());
                                                }

                                            } else {
                                                FailData failData = writeFailDataToRegion(edmSourceListV1Entity, "N11", "Material Global V1 Primary planning code and Material Number are blank");
                                                resultObject.setFailData(failData);
                                                resultObjects.add(resultObject);
                                                return resultObjects;
                                            }

                                            // N12
                                            if (edmMaterialPlantV1Entity != null) {
                                                if (edmMaterialPlantV1Entity.getLocalPurchasingGroup() != null && (!(edmMaterialPlantV1Entity.getLocalPurchasingGroup().isEmpty()))) {
                                                    gdmSupplyBo.setPURCHASINGGROUP(edmMaterialPlantV1Entity.getLocalPurchasingGroup());
                                                } else {
                                                    gdmSupplyBo.setPURCHASINGGROUP("");
                                                }
                                            }

                                            // N13
                                            if (edmSourceListV1Entity.getLocalPurchasingOrganization() != null && (!(edmSourceListV1Entity.getLocalPurchasingOrganization().isEmpty()))) {
                                                gdmSupplyBo.setPURCHASINGORGANIZATION(edmSourceListV1Entity.getLocalPurchasingOrganization());
                                            } else {
                                                gdmSupplyBo.setPURCHASINGORGANIZATION("");
                                            }

                                            // N14
                                            PlanCnsPlnSplLocEntity planCnsPlnSplLocEntity_1 = planCnsPlnSplLocDao.getEntityWithSourceSystemLocalNumberAndVendorOrCustomer(edmSourceListV1Entity.getSourceSystem(), edmSourceListV1Entity.getLocalVendorAccountNumber(), "V");
                                            if (planCnsPlnSplLocEntity_1 == null) {
                                                gdmSupplyBo.setSupplierId(edmSourceListV1Entity.getSourceSystem() + IConstant.VALUE.UNDERLINE + edmSourceListV1Entity.getLocalPlant());
                                            } else {
                                                gdmSupplyBo.setSupplierId(edmSourceListV1Entity.getSourceSystem()
                                                        + IConstant.VALUE.UNDERLINE + planCnsPlnSplLocEntity_1.getVendorOrCustomer()
                                                        + IConstant.VALUE.UNDERLINE + planCnsPlnSplLocEntity_1.getLocalNumber());
                                            }

                                            // N15
                                            String dateToFormat = edmSourceListV1Entity.getLocalSourceListRecordValidFrom();
                                            SimpleDateFormat sdfFrom = new SimpleDateFormat(IConstant.VALUE.YYYYMMDD);
                                            SimpleDateFormat sdfTo = new SimpleDateFormat(IConstant.VALUE.YYYYMMDDHHMMSS);

                                            Date dFrom = null;
                                            try {
                                                dFrom = sdfFrom.parse(dateToFormat);
                                            } catch (ParseException e) {
                                                e.printStackTrace();
                                            }

                                            String fromDate = sdfTo.format(dFrom);
                                            gdmSupplyBo.setFromDate(fromDate);

                                            // N17
                                            gdmSupplyBo.setTransportType(IConstant.VALUE.DEFAULT);

                                            // New Rule
                                            if (edmMaterialPlantV1Entity != null) {
                                                gdmSupplyBo.setINCQuantity(edmMaterialPlantV1Entity.getLocalRoundingValueForPoq());
                                            }

                                            // N18
                                            PlanCnsPlnSplLocEntity planCnsPlnSplLocEntity_2 = planCnsPlnSplLocDao.getEntityWithSourceSystemLocalNumberAndVendorOrCustomer(edmSourceListV1Entity.getSourceSystem(), edmSourceListV1Entity.getLocalVendorAccountNumber(), "V");
                                            if(null != planCnsPlnSplLocEntity_2) {
                                                if (planCnsPlnSplLocEntity_2.getLocalPlant().isEmpty()) {
                                                    // check if planCnsPlnSplLocEntity exists getLocalPlant if not then populate value as "VendorPurchase"
                                                    gdmSupplyBo.setPROCESSTYPEID(IConstant.PLAN_CNS_PLN_SPL_LOC.VENDOR_PURCHASE);
                                                } else {
                                                    return skipObjects;
                                                }
                                            } else {
                                                // if no value returned use ExternalPurchase
                                                gdmSupplyBo.setPROCESSTYPEID(IConstant.PLAN_CNS_PLN_SPL_LOC.EXTERNAL_PURCHASE);
                                            }

                                            // N16
                                            if(edmSourceListV1Entity.getLocalVendorAccountNumber() != null) {
                                                gdmSupplyBo.setVENDORID(edmSourceListV1Entity.getLocalVendorAccountNumber());
                                            }

                                            PlanCnsPlnSplLocEntity planCnsPlnSplLocEntity = planCnsPlnSplLocDao.getEntityWithSourceSystemLocalNumberAndVendorOrCustomer(edmSourceListV1Entity.getSourceSystem(), edmSourceListV1Entity.getLocalVendorAccountNumber(), "V");
                                            if (planCnsPlnSplLocEntity != null) {

                                                EDMMaterialPlantV1Entity edmMaterialPlantV1Entity_1 = materialPlantDao.getPlantWithSourceSystemAndLocalPlantAndLocalMaterialNumber(edmSourceListV1Entity.getSourceSystem(), edmSourceListV1Entity.getLocalPlant(), edmSourceListV1Entity.getLocalMaterialNumber());
                                                if (edmMaterialPlantV1Entity_1 != null) {

                                                    // skip if local Special Procurement Type == 30 as it is a sub contracting scenario
                                                    String localSpecialProcurementType = edmMaterialPlantV1Entity_1.getLocalSpecialProcurementType();
                                                    if (!(localSpecialProcurementType.equals("30"))) {

                                                        // skip if EDM Source List V1 Entity Local Plant from Which Material is Procured is not blank
                                                        if (edmSourceListV1Entity.getLocalPlantfromWhichMaterialisProcured().isEmpty()) {

                                                            // set Vendor to EDM Source List V1 local vendor account number
                                                            gdmSupplyBo.setVENDORID(edmSourceListV1Entity.getLocalVendorAccountNumber());
                                                            gdmSupplyBo.setLocationId(edmSourceListV1Entity.getSourceSystem()
                                                                    + IConstant.VALUE.UNDERLINE + edmSourceListV1Entity.getLocalPlant()
                                                                    + IConstant.VALUE.UNDERLINE + planCnsPlnSplLocEntity.getVendorOrCustomer()
                                                                    + IConstant.VALUE.UNDERLINE + planCnsPlnSplLocEntity.getLocalNumber());
                                                        }
                                                    }
                                                }
                                            }

                                            // N19
                                            PlanCnsProcessTypeEntity planCnsProcessTypeEntity = cnsProcessTypeDao.getEntityWithConditions(gdmSupplyBo.getPROCESSTYPEID());
                                            if (planCnsProcessTypeEntity != null) {
                                                gdmSupplyBo.setLABEL(planCnsProcessTypeEntity.getProcessTypeDesc());
                                            }
                                            resultObject.setBaseBo(gdmSupplyBo);
                                            resultObjects.add(resultObject);
                                        }
                                    }
                                }
                        }
                    }
                }
            }
        }
    } else {
        FailData failData = writeFailDataToRegion(edmSourceListV1Entity, "N1", "Material Global V1 Primary planning code and Material Number are blank");
        resultObject.setFailData(failData);
        resultObjects.add(resultObject);
        return resultObjects;
    }
    return resultObjects;
}

    private FailData writeFailDataToRegion(EDMSourceListV1Entity edmSourceListV1Entity, String ruleCode, String errorValue) {
        FailData failData = new FailData();
        failData.setFunctionalArea("SP");
        failData.setInterfaceID("OMPGdmSupply");
        failData.setErrorCode(ruleCode);
        failData.setSourceSystem("CONS_LATAM");
        failData.setKey1(edmSourceListV1Entity.getSourceSystem());
        failData.setKey2(edmSourceListV1Entity.getLocalMaterialNumber());
        failData.setKey3(edmSourceListV1Entity.getLocalPlant());
        failData.setKey4("");
        failData.setKey5("");
        failData.setErrorValue(errorValue);
        return failData;
    }
}
