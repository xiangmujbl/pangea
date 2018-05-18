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
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_supply.bo.OMPGdmSupplyBo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OMPGdmSupplyServiceImpl implements ICommonService {

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

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        EDMSourceListV1Entity edmSourceListV1Entity = (EDMSourceListV1Entity) o;

        OMPGdmSupplyBo gdmSupplyBo = new OMPGdmSupplyBo();

        // N1
        String partA = null;
        EDMMaterialGlobalV1Entity materialGlobalV1Entity = materialGlobalDao.getEntityWithLocalMaterialNumber(edmSourceListV1Entity.getLocalMaterialNumber());
        if(materialGlobalV1Entity == null)
        {
            FailData failData = writeFailDataToRegion(edmSourceListV1Entity, "N1", "Material Global V1 Entity is blank");
            resultObject.setFailData(failData);
            return resultObject;
        }
        else if(!(materialGlobalV1Entity.getPrimaryPlanningCode().isEmpty() && materialGlobalV1Entity.getMaterialNumber().isEmpty())) {
            if (materialGlobalV1Entity.getPrimaryPlanningCode() != null && (!(materialGlobalV1Entity.getPrimaryPlanningCode().isEmpty()))) {
                partA = materialGlobalV1Entity.getPrimaryPlanningCode();
            } else {
                partA = materialGlobalV1Entity.getMaterialNumber();
            }
            if (!(materialGlobalV1Entity.getPrimaryPlanningCode().isEmpty() && materialGlobalV1Entity.getMaterialNumber().isEmpty())
                    && (materialGlobalV1Entity.getPrimaryPlanningCode().equals(materialGlobalV1Entity.getMaterialNumber()))) {

                if(edmSourceListV1Entity.getLocalPlant() != null && (!(edmSourceListV1Entity.getLocalPlant().isEmpty()))) {
                    if(edmSourceListV1Entity.getLocalVendorAccountNumber() != null && (!(edmSourceListV1Entity.getLocalVendorAccountNumber().isEmpty()))) {
                        if(edmSourceListV1Entity.getSourceSystem() != null && (!(edmSourceListV1Entity.getSourceSystem().isEmpty()))) {
                            String supplyId = partA
                                    + edmSourceListV1Entity.getSourceSystem() + IConstant.VALUE.UNDERLINE + edmSourceListV1Entity.getLocalPlant() + IConstant.VALUE.UNDERLINE
                                    + edmSourceListV1Entity.getLocalVendorAccountNumber();
                            gdmSupplyBo.setSupplyId(supplyId);
                        } else {
                            return null;
                        }
                    } else {
                        return null;
                    }
                 } else {
                    return null;
                }

                // N2
                gdmSupplyBo.setActive(IConstant.VALUE.YES);
                gdmSupplyBo.setActiveOPRERP(IConstant.VALUE.YES);

                // N20
                gdmSupplyBo.setActiveSOPERP(IConstant.VALUE.NO);

                // N15
                String dateToFormat_1 = edmSourceListV1Entity.getLocalSourceListRecordValidTo();
                SimpleDateFormat sdfFrom_1 = new SimpleDateFormat(IConstant.VALUE.YYYYMMDD);
                SimpleDateFormat sdfTo_1 = new SimpleDateFormat(IConstant.VALUE.YYYYMMDDBS);

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
                if(edmPlantV1Entity == null || (!(edmPlantV1Entity.getLocalPlanningRelevant().equals(IConstant.VALUE.X)))) {
                    return null;
                }
                else if (edmPlantV1Entity.getLocalPlanningRelevant() != null && edmPlantV1Entity.getLocalPlanningRelevant().equals(IConstant.VALUE.X)) {

                        gdmSupplyBo.setLocationId(edmSourceListV1Entity.getSourceSystem() + IConstant.VALUE.UNDERLINE + edmSourceListV1Entity.getLocalPlant());

                        // N4
                        gdmSupplyBo.setMACHINECAPACITY(IConstant.VALUE.INFINITE);

                        // N5
                        gdmSupplyBo.setMACHINETYPEID(IConstant.VALUE.SUPPLY);

                        // N6
                        EDMMaterialPlantV1Entity edmMaterialPlantV1Entity = materialPlantDao.getPlantWithSourceSystemAndLocalPlantAndLocalMaterialNumber(edmSourceListV1Entity.getSourceSystem(), edmSourceListV1Entity.getLocalPlant(), edmSourceListV1Entity.getLocalMaterialNumber());

                        if (edmMaterialPlantV1Entity != null) {
                            gdmSupplyBo.setMaxQuantity(edmMaterialPlantV1Entity.getLocalMaximumLotSize());
                        } else {
                            gdmSupplyBo.setMaxQuantity("");
                        }

                        // N7
                        gdmSupplyBo.setMaxQuantityType("");

                        // N8
                        if (edmMaterialPlantV1Entity != null) {
                            gdmSupplyBo.setMinQuantity(edmMaterialPlantV1Entity.getLocalMinimumLotSize());
                        } else {
                            gdmSupplyBo.setMinQuantity("");
                        }

                        // N9
                        gdmSupplyBo.setPLANLEVELID(IConstant.VALUE.ASTERIX);

                        // N10
                        if (!(edmSourceListV1Entity.getLocalFixedvendor().isEmpty()) || (!(edmSourceListV1Entity.getLocalFixedOutlinePurchaseAgreementItem().isEmpty()))) {
                                if(edmSourceListV1Entity.getLocalFixedvendor().equals(IConstant.VALUE.ONE)
                                        || edmSourceListV1Entity.getLocalFixedOutlinePurchaseAgreementItem().equals(IConstant.VALUE.ONE)) {
                                    gdmSupplyBo.setPreference(IConstant.VALUE.ONE);
                                }
                        } else {
                            gdmSupplyBo.setPreference("");
                        }

                        // N18
                        gdmSupplyBo.setPROCESSTYPEID(IConstant.VALUE.VENDOR_TRANSPORT);

                        // N19
                        PlanCnsProcessTypeEntity planCnsProcessTypeEntity = cnsProcessTypeDao.getEntityWithConditions(gdmSupplyBo.getPROCESSTYPEID());
                        if(planCnsProcessTypeEntity != null) {
                            gdmSupplyBo.setLABEL(planCnsProcessTypeEntity.getProcessTypeDesc());
                        } else {
                            return null;
                        }

                        // N11
                        PlanCnsMaterialPlanStatusEntity planCnsMaterialPlanStatusEntity = cnsMaterialPlanStatusDao.getEntityWithSourceSystemAndLocalMaterialNumberAndLocalPlant(edmSourceListV1Entity.getSourceSystem(), edmSourceListV1Entity.getLocalMaterialNumber(), edmSourceListV1Entity.getLocalPlant());
                        if(planCnsMaterialPlanStatusEntity == null)
                        {
                            FailData failData = writeFailDataToRegion(edmSourceListV1Entity, "N11", "Plan Cns Material Plan Status Entity is blank");
                            resultObject.setFailData(failData);
                            return resultObject;
                        }
                        else {
                            if (planCnsMaterialPlanStatusEntity.getSpRelevant() != null && (!(planCnsMaterialPlanStatusEntity.getSpRelevant().isEmpty()))) {
                                String primaryPlanningCode = materialGlobalV1Entity.getPrimaryPlanningCode();
                                String materialNumber = materialGlobalV1Entity.getMaterialNumber();

                                if (primaryPlanningCode == null && primaryPlanningCode.isEmpty()) {
                                    gdmSupplyBo.setProductId(materialGlobalV1Entity.getMaterialNumber());
                                } else if (primaryPlanningCode.equals(materialNumber)) {
                                    gdmSupplyBo.setProductId(materialGlobalV1Entity.getPrimaryPlanningCode());
                                } else {
                                    FailData failData = writeFailDataToRegion(edmSourceListV1Entity, "N11", "Primary planning code is blank");
                                    resultObject.setFailData(failData);
                                    return resultObject;
                                }

                             // N12
                            if(edmMaterialPlantV1Entity != null) {
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
                                gdmSupplyBo.setSupplierId(edmSourceListV1Entity.getSourceSystem() +  IConstant.VALUE.UNDERLINE + edmSourceListV1Entity.getLocalPlant());
                            } else {
                                if (planCnsPlnSplLocEntity_1.getVendorOrCustomer() != null && (!(planCnsPlnSplLocEntity_1.getVendorOrCustomer().isEmpty()))) {
                                    if (planCnsPlnSplLocEntity_1.getLocalNumber() != null && (!(planCnsPlnSplLocEntity_1.getLocalNumber().isEmpty()))) {
                                        gdmSupplyBo.setSupplierId(edmSourceListV1Entity.getSourceSystem() +
                                                IConstant.VALUE.UNDERLINE + planCnsPlnSplLocEntity_1.getVendorOrCustomer()
                                                    + IConstant.VALUE.UNDERLINE + planCnsPlnSplLocEntity_1.getLocalNumber());
                                    } else {
                                        return null;
                                    }
                                } else {
                                    return null;
                                }
                            }

                            // N15
                            String dateToFormat = edmSourceListV1Entity.getLocalSourceListRecordValidFrom();
                            SimpleDateFormat sdfFrom = new SimpleDateFormat(IConstant.VALUE.YYYYMMDD);
                            SimpleDateFormat sdfTo = new SimpleDateFormat(IConstant.VALUE.YYYYMMDDBS);

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

                            // N16
                            PlanCnsPlnSplLocEntity planCnsPlnSplLocEntity = planCnsPlnSplLocDao.getEntityWithSourceSystemLocalNumberAndVendorOrCustomer(edmSourceListV1Entity.getSourceSystem(), edmSourceListV1Entity.getLocalVendorAccountNumber(), "V");
                            if (planCnsPlnSplLocEntity == null) {
                                gdmSupplyBo.setVENDOR(edmSourceListV1Entity.getLocalVendorAccountNumber());
                            } else {
                                    if (edmMaterialPlantV1Entity != null) {
                                    String localSpecialProcurementType = edmMaterialPlantV1Entity.getLocalSpecialProcurementType();
                                    if (localSpecialProcurementType.equals("30")) {
                                        // skip if local Special Procurement Type == 30
                                            return null;
                                    } else {
                                        // skip if EDM Source List V1 Entity Local Plant from Which Material is Procured is not blank
                                        if (!(edmSourceListV1Entity.getLocalPlantfromWhichMaterialisProcured().isEmpty()) && edmSourceListV1Entity.getLocalPlantfromWhichMaterialisProcured() != null) {
                                            return null;
                                        } else {
                                            // set Vendor to EDM Source List V1 local vendor account number
                                            gdmSupplyBo.setVENDOR(edmSourceListV1Entity.getLocalVendorAccountNumber());
                                        }
                                    }
                                }
                            }
                        } else {
                            FailData failData = writeFailDataToRegion(edmSourceListV1Entity, "N11", "Plan Cns Material Plan Status Entity SP relevant is blank");
                            resultObject.setFailData(failData);
                            return resultObject;
                        }
                    }
                } else {
                return null;
            }
        } else {
            FailData failData = writeFailDataToRegion(edmSourceListV1Entity, "N1", "Primary planning Code and Material Number do not match");
            resultObject.setFailData(failData);
            return resultObject;
        }
        resultObject.setBaseBo(gdmSupplyBo);
    }
    return resultObject;
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
