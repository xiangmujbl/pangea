package com.jnj.pangea.omp.gdm_supply.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialGlobalDaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialPlantV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMPlantV1DaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsMaterialInclDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsMaterialPlanStatusDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlnSplLocDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsProcessTypeDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.entity.edm.EDMMaterialPlantV1Entity;
import com.jnj.pangea.common.entity.edm.EDMPlantV1Entity;
import com.jnj.pangea.common.entity.edm.EDMSourceListV1Entity;
import com.jnj.pangea.common.entity.plan.*;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_supply.bo.OMPGdmSupplyBo;
import scala.concurrent.java8.FuturesConvertersImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
            LogUtil.getCoreLog().info("\n\n\n\n\nMaterial Global Entity is NULL:{}\n\n\n\n\n");
            FailData failData = writeFailDataToRegion(edmSourceListV1Entity, "N16", "");
            resultObject.setFailData(failData);
        }
        else {
            LogUtil.getCoreLog().info("\n\n\n\n\nmaterialGlobalV1Entity toString:{}\n\n\n\n\n", materialGlobalV1Entity.toString());
            LogUtil.getCoreLog().info("\n\n\n\n\nFIRST MATERIAL GLOBAL IF STATEMENT:{}\n\n\n\n\n");
            LogUtil.getCoreLog().info("\n\n\n\n\nmaterialGlobalV1Entity.getPrimaryPlanningCode():{}\n\n\n\n\n", materialGlobalV1Entity.getPrimaryPlanningCode());
            if (materialGlobalV1Entity.getPrimaryPlanningCode() != null && !materialGlobalV1Entity.equals("")) {
                partA = materialGlobalV1Entity.getPrimaryPlanningCode();
            } else {
                partA = materialGlobalV1Entity.getMaterialNumber();
            }
            LogUtil.getCoreLog().info("\n\n\n\n\nSECOND MATERIAL GLOBAL IF STATEMENT:{}\n\n\n\n\n");
            LogUtil.getCoreLog().info("\n\n\n\n\nmaterialGlobalV1Entity.getPrimaryPlanningCode():{}\n\n\n\n\n", materialGlobalV1Entity.getPrimaryPlanningCode());
            LogUtil.getCoreLog().info("\n\n\n\n\nmaterialGlobalV1Entity.getMaterialNumber():{}\n\n\n\n\n", materialGlobalV1Entity.getMaterialNumber());

            if (!(materialGlobalV1Entity.getPrimaryPlanningCode().equals("") && materialGlobalV1Entity.getMaterialNumber().equals(""))
                    && (materialGlobalV1Entity.getPrimaryPlanningCode().equals(materialGlobalV1Entity.getMaterialNumber()))) {
                LogUtil.getCoreLog().info("\n\n\n\n\nINSIDE MATERIAL GLOBAL IF STATEMENT:{}\n\n\n\n\n");
                String supplyId = partA
                        + edmSourceListV1Entity.getSourceSystem() + "_" + edmSourceListV1Entity.getLocalPlant()
                        + edmSourceListV1Entity.getLocalVendorAccountNumber();
                gdmSupplyBo.setSupplyId(supplyId);
                LogUtil.getCoreLog().info("\n\nN1 SUPPLY ID: {}\n\n", supplyId);

                // N2
                gdmSupplyBo.setActive(IConstant.VALUE.YES);
                gdmSupplyBo.setActiveOPRERP(IConstant.VALUE.YES);
                LogUtil.getCoreLog().info("\n\nN2 setActiveOPRERP: {}\n\n", IConstant.VALUE.YES);

                // N20
                gdmSupplyBo.setActiveSOPERP(IConstant.VALUE.NO);
                LogUtil.getCoreLog().info("\n\nN20 setActiveSOPERP: {}\n\n", IConstant.VALUE.NO);

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
                LogUtil.getCoreLog().info("\n\n\n\n\nN15 DATE :{}\n\n\n\n\n", fromDate);
                gdmSupplyBo.setFromDate(fromDate);

                // N19
                if (edmSourceListV1Entity.getLocalPlantfromWhichMaterialisProcured() == null || edmSourceListV1Entity.getLocalPlantfromWhichMaterialisProcured().equals("")) {
                    gdmSupplyBo.setLABEL("VENDOR Transport");
                    LogUtil.getCoreLog().info("\n\nN19 setLABEL: {}\n\n", "VENDOR Transport");
                } else {
                    gdmSupplyBo.setLABEL("INTERNAL Transport");
                    LogUtil.getCoreLog().info("\n\nN19 setLABEL: {}\n\n", "INTERNAL Transport");
                }

                // N3
                EDMPlantV1Entity edmPlantV1Entity = plantDao.getPlantWithSourceSystemAndLocalPlant(edmSourceListV1Entity.getSourceSystem(), edmSourceListV1Entity.getLocalPlant());
                LogUtil.getCoreLog().info("\n\nN3 edmPlantV1Entity: {}\n\n", edmPlantV1Entity.toString());

                if(edmPlantV1Entity == null)
                {
                    FailData failData = writeFailDataToRegion(edmSourceListV1Entity, "N3", "");
                    resultObject.setFailData(failData);
                    LogUtil.getCoreLog().info("\n\nN3 FAILED: {}\n\n");

                }
                if(edmPlantV1Entity.getLocalPlanningRelevant() == null)
                {
                    LogUtil.getCoreLog().info("\n\nN3 FAILED: edmPlantV1Entity.getLocalPlanningRelevant() == null{}\n\n");
                }
                else if (edmPlantV1Entity.getLocalPlanningRelevant() != null && edmPlantV1Entity.getLocalPlanningRelevant().equals(IConstant.VALUE.X)) {
                        gdmSupplyBo.setLocationId(edmSourceListV1Entity.getSourceSystem() + "_" + edmSourceListV1Entity.getLocalPlant());

                        // N4
                        gdmSupplyBo.setMACHINECAPACITY("infinite");
                        LogUtil.getCoreLog().info("\n\nN4 setMACHINECAPACITY: {}\n\n", "infinite");

                        // N5
                        gdmSupplyBo.setMACHINETYPEID("SUPPLY");
                        LogUtil.getCoreLog().info("\n\nN5 setMACHINETYPEID: {}\n\n", "SUPPLY");

                        // N6
                        EDMMaterialPlantV1Entity edmMaterialPlantV1Entity = materialPlantDao.getPlantWithSourceSystemAndLocalPlantAndLocalMaterialNumber(edmSourceListV1Entity.getSourceSystem(), edmSourceListV1Entity.getLocalPlant(), edmSourceListV1Entity.getLocalMaterialNumber());
                        LogUtil.getCoreLog().info("\n\nN6 edmMaterialPlantV1Entity: {}\n\n", edmMaterialPlantV1Entity.toString());

                        if (edmMaterialPlantV1Entity != null) {
                            gdmSupplyBo.setMaxQuantity(edmMaterialPlantV1Entity.getLocalMaximumLotSize());
                        } else {
                            gdmSupplyBo.setMaxQuantity("");
                        }

                        // N7
                        gdmSupplyBo.setMaxQuantityType("");
                        gdmSupplyBo.setMaxQuantityType("");
                        LogUtil.getCoreLog().info("\n\nN7 setMaxQuantityType: {}\n\n", "setMaxQuantityType = 0");

                        // N8
                        if (edmMaterialPlantV1Entity != null) {
                            gdmSupplyBo.setMinQuantity(edmMaterialPlantV1Entity.getLocalMinimumLotSize());
                            LogUtil.getCoreLog().info("\n\nN8 edmMaterialPlantV1Entity.getLocalMinimumLotSize(): {}\n\n", edmMaterialPlantV1Entity.getLocalMinimumLotSize());
                        } else {
                            gdmSupplyBo.setMinQuantity("");
                            LogUtil.getCoreLog().info("\n\nN8 setMinQuantity: {}\n\n", "setMinQuantity = 0");
                        }

                        // N9
                        gdmSupplyBo.setPLANLEVELID("*");
                        LogUtil.getCoreLog().info("\n\nN9 setPLANLEVELID: {}\n\n", "setPLANLEVELID = *");

                        // N10
                        if (!edmSourceListV1Entity.getLocalFixedvendor().equals("") || edmSourceListV1Entity.getLocalFixedOutlinePurchaseAgreementItem().equals("")) {
                            gdmSupplyBo.setPreference("1");
                            LogUtil.getCoreLog().info("\n\nN10 setPreference: {}\n\n", "setPreference = 1");
                        } else {
                            gdmSupplyBo.setPreference("");
                            LogUtil.getCoreLog().info("\n\nN10 setPreference: {}\n\n", "setPreference = 0");
                        }

                        // N18
                    LogUtil.getCoreLog().info("\n\nN18 edmSourceListV1Entity.getLocalPlantfromWhichMaterialisProcured(): {}\n\n", edmSourceListV1Entity.getLocalPlantfromWhichMaterialisProcured());
                    if (edmSourceListV1Entity.getLocalPlantfromWhichMaterialisProcured().isEmpty()) {
                        LogUtil.getCoreLog().info("\n\nN18 BLLLLLLLLLAAAAAAAAAAANNNNNNNNNNNKKKKKKKKKK: {}\n\n", edmSourceListV1Entity.getLocalPlantfromWhichMaterialisProcured());

                        PlanCnsProcessTypeEntity planCnsProcessTypeEntity = cnsProcessTypeDao.getEntityWithConditions("VENDOR Transport");
                            gdmSupplyBo.setPROCESSTYPEID(planCnsProcessTypeEntity.getProcessTypeId());

                        } else {
                            LogUtil.getCoreLog().info("\n\nN18 ELSE planCnsProcessTypeEntity.getProcessTypeId() {INTERNAL Transport}\n\n");
                            PlanCnsProcessTypeEntity planCnsProcessTypeEntity = cnsProcessTypeDao.getEntityWithConditions("INTERNAL Transport");
                            gdmSupplyBo.setPROCESSTYPEID(planCnsProcessTypeEntity.getProcessTypeId());
                            List<PlanCnsProcessTypeEntity> planCnsProcessTypeEntityList = cnsProcessTypeDao.getAllEntityTest();
                            LogUtil.getCoreLog().info("\n\nplanCnsProcessTypeEntityList " + planCnsProcessTypeEntityList.size());
                            LogUtil.getCoreLog().info("\n\ngetProcessTypeId " + planCnsProcessTypeEntityList.get(0).getProcessTypeId());
                            LogUtil.getCoreLog().info("\n\ngetProcessTypeDesc " + planCnsProcessTypeEntityList.get(0).getProcessTypeDesc());

                        }

                        // N11
                        PlanCnsMaterialPlanStatusEntity planCnsMaterialPlanStatusEntity = cnsMaterialPlanStatusDao.getEntityWithSourceSystemAndLocalMaterialNumberAndLocalPlant(edmSourceListV1Entity.getSourceSystem(), edmSourceListV1Entity.getLocalMaterialNumber(), edmSourceListV1Entity.getLocalPlant());
                        if (planCnsMaterialPlanStatusEntity.getSpRelevant() != null && !planCnsMaterialPlanStatusEntity.getSpRelevant().equals("")) {
                            LogUtil.getCoreLog().info("\n\nN11 materialGlobalV1Entity.getPrimaryPlanningCode(): {}\n\n", materialGlobalV1Entity.getPrimaryPlanningCode());
                            LogUtil.getCoreLog().info("\n\nN11  materialGlobalV1Entity.getMaterialNumber(): {}\n\n",  materialGlobalV1Entity.getMaterialNumber());
                            String primaryPlanningCode = materialGlobalV1Entity.getPrimaryPlanningCode();
                            String materialNumber = materialGlobalV1Entity.getMaterialNumber();


                            if (primaryPlanningCode == null || primaryPlanningCode.equals("")) {
                                gdmSupplyBo.setProductId(materialGlobalV1Entity.getMaterialNumber());
                                LogUtil.getCoreLog().info("\n\nN11 setProductId: {}\n\n", materialGlobalV1Entity.getMaterialNumber());
                            } else if (primaryPlanningCode.equals(materialNumber)) {
                                gdmSupplyBo.setProductId(materialGlobalV1Entity.getPrimaryPlanningCode());
                                LogUtil.getCoreLog().info("\n\nN11 setProductId: {}\n\n", materialGlobalV1Entity.getPrimaryPlanningCode());

                            } else {
                                FailData failData = writeFailDataToRegion(edmSourceListV1Entity, "N11", "");
                                resultObject.setFailData(failData);
                            }

                            // N12
                            LogUtil.getCoreLog().info("\n\nN12 edmMaterialPlantV1Entity: {}\n\n", edmMaterialPlantV1Entity.toString());
                            LogUtil.getCoreLog().info("\n\nN12 setPURCHASINGGROUP: {}\n\n", edmMaterialPlantV1Entity.getLocalPurchasingGroup());
                            gdmSupplyBo.setPURCHASINGGROUP(edmMaterialPlantV1Entity.getLocalPurchasingGroup());

                            // N13
                            gdmSupplyBo.setPURCHASINGORGANIZATION(edmSourceListV1Entity.getLocalPurchasingOrganization());
                            LogUtil.getCoreLog().info("\n\nN13 setPURCHASINGORGANIZATION: {}\n\n", edmSourceListV1Entity.getLocalPurchasingOrganization());

                            // N14
                            gdmSupplyBo.setSupplierId(edmSourceListV1Entity.getLocalPlantfromWhichMaterialisProcured());
                            LogUtil.getCoreLog().info("\n\nN14 setSupplierId: {}\n\n", edmSourceListV1Entity.getLocalPlantfromWhichMaterialisProcured());

                            // N15
                            String dateToFormat_1 = edmSourceListV1Entity.getLocalSourceListRecordValidTo();
                            SimpleDateFormat sdfFrom_1 = new SimpleDateFormat(IConstant.VALUE.YYYYMMDD);
                            SimpleDateFormat sdfTo_1 = new SimpleDateFormat(IConstant.VALUE.YYYYMMDDBS);

                            Date dFrom_1 = null;
                            try {
                                dFrom = sdfFrom.parse(dateToFormat);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            String toDate = sdfTo.format(dFrom);
                            gdmSupplyBo.setFromDate(toDate);
                            LogUtil.getCoreLog().info("\n\nN15 setFromDate: {}\n\n", toDate);

                            // N17
                            gdmSupplyBo.setTransportType("Default");
                            LogUtil.getCoreLog().info("\n\nN17 setTransportType: {}\n\n", "Default");

                            // N16
                            PlanCnsPlnSplLocEntity planCnsPlnSplLocEntity = planCnsPlnSplLocDao.getEntityWithLocalNumberAndVendorOrCustomer(edmSourceListV1Entity.getLocalVendorAccountNumber(), "V");
                            if (planCnsPlnSplLocEntity == null) {
                                gdmSupplyBo.setVENDOR(edmSourceListV1Entity.getLocalVendorAccountNumber());
                                LogUtil.getCoreLog().info("\n\nN16 setVENDOR: {}\n\n", edmSourceListV1Entity.getLocalVendorAccountNumber());

                            } else {
                                String localSpecialProcurementType = ""; // edmMaterialPlantV1Entity.getLocalSpecialProcurementType();
                                if (localSpecialProcurementType.equals("30")) {
                                    FailData failData = writeFailDataToRegion(edmSourceListV1Entity, "N11", "");
                                    resultObject.setFailData(failData);
                                } else {
                                    gdmSupplyBo.setVENDOR(edmSourceListV1Entity.getLocalVendorAccountNumber());
                                    LogUtil.getCoreLog().info("\n\nN16 setVENDOR: {}\n\n", edmSourceListV1Entity.getLocalVendorAccountNumber());

                                }
                            }

                        } else {
                            FailData failData = writeFailDataToRegion(edmSourceListV1Entity, "N16", "");
                            resultObject.setFailData(failData);
                        }
                    } else {
                        FailData failData = writeFailDataToRegion(edmSourceListV1Entity, "N3", "");
                        resultObject.setFailData(failData);
                    }

            } else {
                FailData failData = writeFailDataToRegion(edmSourceListV1Entity, "N1", "");
                resultObject.setFailData(failData);
            }
        }

        resultObject.setBaseBo(gdmSupplyBo);

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
