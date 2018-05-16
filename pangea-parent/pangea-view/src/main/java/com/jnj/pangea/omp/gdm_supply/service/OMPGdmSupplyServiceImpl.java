package com.jnj.pangea.omp.gdm_supply.service;
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
            FailData failData = writeFailDataToRegion(edmSourceListV1Entity, "N16", "");
            resultObject.setFailData(failData);
        }
        else {
            if (materialGlobalV1Entity.getPrimaryPlanningCode() != null && !materialGlobalV1Entity.equals("")) {
                partA = materialGlobalV1Entity.getPrimaryPlanningCode();
            } else {
                partA = materialGlobalV1Entity.getMaterialNumber();
            }
            if (!(materialGlobalV1Entity.getPrimaryPlanningCode().equals("") && materialGlobalV1Entity.getMaterialNumber().equals(""))
                    && (materialGlobalV1Entity.getPrimaryPlanningCode().equals(materialGlobalV1Entity.getMaterialNumber()))) {
                String supplyId = partA
                        + edmSourceListV1Entity.getSourceSystem() + "_" + edmSourceListV1Entity.getLocalPlant() + "_"
                        + edmSourceListV1Entity.getLocalVendorAccountNumber();
                gdmSupplyBo.setSupplyId(supplyId);

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

                if(edmPlantV1Entity == null)
                {
                    FailData failData = writeFailDataToRegion(edmSourceListV1Entity, "N3", "");
                    resultObject.setFailData(failData);
                }
                else if (edmPlantV1Entity.getLocalPlanningRelevant() != null && edmPlantV1Entity.getLocalPlanningRelevant().equals(IConstant.VALUE.X)) {

                        gdmSupplyBo.setLocationId(edmSourceListV1Entity.getSourceSystem() + "_" + edmSourceListV1Entity.getLocalPlant());

                        // N4
                        gdmSupplyBo.setMACHINECAPACITY("INFINITE");

                        // N5
                        gdmSupplyBo.setMACHINETYPEID("SUPPLY");

                        // N6
                        EDMMaterialPlantV1Entity edmMaterialPlantV1Entity = materialPlantDao.getPlantWithSourceSystemAndLocalPlantAndLocalMaterialNumber(edmSourceListV1Entity.getSourceSystem(), edmSourceListV1Entity.getLocalPlant(), edmSourceListV1Entity.getLocalMaterialNumber());

                        if (edmMaterialPlantV1Entity != null) {
                            gdmSupplyBo.setMaxQuantity(edmMaterialPlantV1Entity.getLocalMaximumLotSize());
                        } else {
                            gdmSupplyBo.setMaxQuantity("");
                        }

                        // N7
                        gdmSupplyBo.setMaxQuantityType("");
                        gdmSupplyBo.setMaxQuantityType("");

                        // N8
                        if (edmMaterialPlantV1Entity != null) {
                            gdmSupplyBo.setMinQuantity(edmMaterialPlantV1Entity.getLocalMinimumLotSize());
                        } else {
                            gdmSupplyBo.setMinQuantity("");
                        }

                        // N9
                        gdmSupplyBo.setPLANLEVELID("*");

                        // N10
                        if (!edmSourceListV1Entity.getLocalFixedvendor().equals("") || edmSourceListV1Entity.getLocalFixedOutlinePurchaseAgreementItem().equals("")) {
                            gdmSupplyBo.setPreference("1");
                        } else {
                            gdmSupplyBo.setPreference("");
                        }

                        // N18
                        gdmSupplyBo.setPROCESSTYPEID("VendorTransport");

                        // N19
                        PlanCnsProcessTypeEntity planCnsProcessTypeEntity = cnsProcessTypeDao.getEntityWithConditions(gdmSupplyBo.getPROCESSTYPEID());
                        gdmSupplyBo.setLABEL(planCnsProcessTypeEntity.getProcessTypeDesc());

                        // N11
                        PlanCnsMaterialPlanStatusEntity planCnsMaterialPlanStatusEntity = cnsMaterialPlanStatusDao.getEntityWithSourceSystemAndLocalMaterialNumberAndLocalPlant(edmSourceListV1Entity.getSourceSystem(), edmSourceListV1Entity.getLocalMaterialNumber(), edmSourceListV1Entity.getLocalPlant());
                        if (planCnsMaterialPlanStatusEntity.getSpRelevant() != null && !planCnsMaterialPlanStatusEntity.getSpRelevant().equals("")) {
                            String primaryPlanningCode = materialGlobalV1Entity.getPrimaryPlanningCode();
                            String materialNumber = materialGlobalV1Entity.getMaterialNumber();


                            if (primaryPlanningCode == null || primaryPlanningCode.equals("")) {
                                gdmSupplyBo.setProductId(materialGlobalV1Entity.getMaterialNumber());
                            } else if (primaryPlanningCode.equals(materialNumber)) {
                                gdmSupplyBo.setProductId(materialGlobalV1Entity.getPrimaryPlanningCode());

                            } else {
                                FailData failData = writeFailDataToRegion(edmSourceListV1Entity, "N11", "");
                                resultObject.setFailData(failData);
                            }

                            // N12
                            gdmSupplyBo.setPURCHASINGGROUP(edmMaterialPlantV1Entity.getLocalPurchasingGroup());

                            // N13
                            gdmSupplyBo.setPURCHASINGORGANIZATION(edmSourceListV1Entity.getLocalPurchasingOrganization());

                            // N14
                            PlanCnsPlnSplLocEntity planCnsPlnSplLocEntity_1 = planCnsPlnSplLocDao.getEntityWithSourceSystemLocalNumberAndVendorOrCustomer(edmSourceListV1Entity.getSourceSystem(), edmSourceListV1Entity.getLocalVendorAccountNumber(), "V");
                            if (planCnsPlnSplLocEntity_1 == null)
                            {
                                gdmSupplyBo.setSupplierId(edmSourceListV1Entity.getSourceSystem());
                            }
                            else
                            {
                                gdmSupplyBo.setSupplierId(edmSourceListV1Entity.getSourceSystem() + "_" + planCnsPlnSplLocEntity_1.getVendorOrCustomer() + "_" + planCnsPlnSplLocEntity_1.getLocalNumber());

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
                            gdmSupplyBo.setTransportType("DEFAULT");

                            // N16
                            PlanCnsPlnSplLocEntity planCnsPlnSplLocEntity = planCnsPlnSplLocDao.getEntityWithSourceSystemLocalNumberAndVendorOrCustomer(edmSourceListV1Entity.getSourceSystem(), edmSourceListV1Entity.getLocalVendorAccountNumber(), "V");
                            if (planCnsPlnSplLocEntity == null) {
                                gdmSupplyBo.setVENDOR(edmSourceListV1Entity.getLocalVendorAccountNumber());

                            } else {
                                String localSpecialProcurementType = ""; // edmMaterialPlantV1Entity.getLocalSpecialProcurementType();
                                if (localSpecialProcurementType.equals("30")) {
                                    FailData failData = writeFailDataToRegion(edmSourceListV1Entity, "N11", "");
                                    resultObject.setFailData(failData);
                                } else {
                                    gdmSupplyBo.setVENDOR(edmSourceListV1Entity.getLocalVendorAccountNumber());
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
