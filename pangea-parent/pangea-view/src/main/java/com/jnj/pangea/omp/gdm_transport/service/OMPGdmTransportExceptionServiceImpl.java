package com.jnj.pangea.omp.gdm_transport.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialGlobalDaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialPlantV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMPlantV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceListV1DaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsMaterialPlanStatusDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlnSplLocDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsProcessTypeDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.entity.edm.EDMMaterialPlantV1Entity;
import com.jnj.pangea.common.entity.edm.EDMPlantV1Entity;
import com.jnj.pangea.common.entity.edm.EDMSourceListV1Entity;
import com.jnj.pangea.common.entity.plan.*;
import com.jnj.pangea.omp.gdm_transport.bo.OMPGdmTransportBo;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.Arrays;

public class OMPGdmTransportExceptionServiceImpl {

    private static OMPGdmTransportExceptionServiceImpl instance;

    public static OMPGdmTransportExceptionServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmTransportExceptionServiceImpl();
        }
        return instance;
    }

    private EDMMaterialGlobalDaoImpl materialGlobalDao = EDMMaterialGlobalDaoImpl.getInstance();
    private EDMMaterialPlantV1DaoImpl materialPlantDao = EDMMaterialPlantV1DaoImpl.getInstance();
    private EDMPlantV1DaoImpl plantDao = EDMPlantV1DaoImpl.getInstance();
    private EDMSourceListV1DaoImpl sourceListDao = EDMSourceListV1DaoImpl.getInstance();
    private PlanCnsPlnSplLocDaoImpl plnSplLocDao = PlanCnsPlnSplLocDaoImpl.getInstance();
    private PlanCnsMaterialPlanStatusDaoImpl materialPlanStatusDao = PlanCnsMaterialPlanStatusDaoImpl.getInstance();
    private PlanCnsProcessTypeDaoImpl processTypeDao = PlanCnsProcessTypeDaoImpl.getInstance();

    public ResultObject buildView(String key, Object o, Object o2) {

        //todo: check properly if entities are empty
        //todo: what to do if they come back with many records? is that possible?

        ResultObject resultObject = new ResultObject();
        CnsTlaneItemExceptionEntity tlaneItemExEntity = (CnsTlaneItemExceptionEntity) o;
        OMPGdmTransportBo gdmTransportBo = new OMPGdmTransportBo();
        boolean curationFail = false;
        String rule = null;
        String error = null;

        //N1
        if (tlaneItemExEntity.getDeletionIndicator() == null ||
                tlaneItemExEntity.getDeletionIndicator().equals("")) {
            gdmTransportBo.setProcessTypeId(tlaneItemExEntity.getProcessTypeId());
        } else {
            curationFail = true;
            rule = "N1";
            error = "Deletion Indicator not Blank";
        }

        if (tlaneItemExEntity.getMaterialNumber() == null) {
            curationFail = true;
            rule = "NA";
            error = "materialNumber null in CNS Tlane Item Exception";
        }

        EDMMaterialGlobalV1Entity materialGlobalV1Entity = null;

        if (!curationFail) {
            // set materials entity
            materialGlobalV1Entity = materialGlobalDao.getEntityWithPrimaryPlanningCode(tlaneItemExEntity.getMaterialNumber());

            if (materialGlobalV1Entity == null || materialGlobalV1Entity.getMaterialNumber() == null || materialGlobalV1Entity.getMaterialNumber().equals("")) {
                materialGlobalV1Entity = materialGlobalDao.getEntityWithLocalMaterialNumber(tlaneItemExEntity.getMaterialNumber());

                if (materialGlobalV1Entity == null || materialGlobalV1Entity.getMaterialNumber() == null || materialGlobalV1Entity.getMaterialNumber().equals("")) {
                    curationFail = true;
                    rule = "NA";
                    error = "Material Number doesn't exist in region Global Material V1";
                }
            }
        }


        String originLocalPlantNum = null;
        String originSourceSystem = null;
        String destLocalPlantNum = null;
        String destSourceSystem = null;
        String localMatNum = null;

        if (tlaneItemExEntity.getOriginLocation() == null) {
            curationFail = true;
            rule = "NA";
            error = "tlaneItemExEntity.getOriginLocation() is empty";
        }

        // set source systems and locations
        if (tlaneItemExEntity.getDestinationLocation() == null) {
            curationFail = true;
            rule = "NA";
            error = "tlaneItemExEntity.getDestinationLocation() is empty";
        }

        if (!curationFail) {
            originLocalPlantNum = this.getLocalPlantNum(tlaneItemExEntity.getOriginLocation());
            originSourceSystem = this.getSourceSystem(tlaneItemExEntity.getOriginLocation());
            destLocalPlantNum = this.getLocalPlantNum(tlaneItemExEntity.getDestinationLocation());
            destSourceSystem = this.getSourceSystem(tlaneItemExEntity.getDestinationLocation());

            // get the mat num if curation hasn't failed (will fail if entity not found)
            localMatNum = materialGlobalV1Entity.getMaterialNumber();

            //N1
            String transportId = tlaneItemExEntity.getMaterialNumber() + IConstant.VALUE.BACK_SLANT
                    + tlaneItemExEntity.getOriginLocation() + IConstant.VALUE.BACK_SLANT
                    + tlaneItemExEntity.getDestinationLocation();
            gdmTransportBo.setTransportId(transportId);

            //N2
            gdmTransportBo.setActive(IConstant.VALUE.YES);
            gdmTransportBo.setActiveOPRERP(IConstant.VALUE.YES);

            //N15
            gdmTransportBo.setActiveSOPERP(IConstant.VALUE.NO);

            //N3

            //N4
            gdmTransportBo.setMachineTypeId(IConstant.VALUE.TRANSPORT);

            //N5
            gdmTransportBo.setMinQuantity("");
            gdmTransportBo.setRequirementOffset("");

            //N6
            gdmTransportBo.setPlanLevelId(IConstant.VALUE.STAR);

            //N7
LogUtil.getCoreLog().info(localMatNum);
LogUtil.getCoreLog().info(destLocalPlantNum);
LogUtil.getCoreLog().info(destSourceSystem);
            //N8 & N9 & N10
            EDMMaterialPlantV1Entity materialPlantV1Entity = materialPlantDao.getEntityWithMaterialNumberPlantNumberSourceSystem(localMatNum, destLocalPlantNum, destSourceSystem);
            EDMSourceListV1Entity sourceListV1Entity = sourceListDao.getEntityWithMaterialNumberPlantNumberSourceSystemLocalDateAndBlankLocalBlockedSourceOfSupply(localMatNum, destLocalPlantNum, destSourceSystem);

            // if any records not found we cant continue, send fail data
            if (materialPlantV1Entity != null &&
                    sourceListV1Entity != null) {

                gdmTransportBo.setPurchasingGroup(materialPlantV1Entity.getLocalPurchasingGroup());
                gdmTransportBo.setPurchasingOrganization(sourceListV1Entity.getLocalPurchasingOrganization());
                gdmTransportBo.setVendorId(sourceListV1Entity.getLocalVendorAccountNumber());

            } else {
                curationFail = true;
                rule = "N8 & N9 & N10";
                error = "No Matching Record found in EDMMaterialPlantV1Entity or EDMSourceListV1Entity";
            }
        }

        //N11
        if (!curationFail) {

            if (this.locationExists(originSourceSystem, originLocalPlantNum)) {
                //N11
                String originLocation = originSourceSystem + "_V_" + originLocalPlantNum;
                gdmTransportBo.setFromLocationId(originLocation);
            } else {
                curationFail = true;
                rule = "N11";
                error = "Location Doesn't exist";
            }
        }

        //N13
        if (!curationFail) {

            if (this.locationExists(destSourceSystem, destLocalPlantNum)) {
                //N11
                String destLocation = destSourceSystem + "_V_" + destLocalPlantNum;
                gdmTransportBo.setToLocationId(destLocation);
            } else {
                curationFail = true;
                rule = "N13";
                error = "Location Doesn't exist";
            }
        }

        // N12 & N14
        if (!curationFail) {

            boolean entryExists = false;
            EDMPlantV1Entity plantV1Entity = plantDao.getPlantWithSourceSystemAndLocalPlant(originSourceSystem, originLocalPlantNum);

            //check if entry exists in EDMPlantV1Entity
            if (plantV1Entity != null) {
                entryExists = true;
            } else { //check if location is special, will be in PlanCnsPlnSplLocEntity

                PlanCnsPlnSplLocEntity plnSplLocEntity = plnSplLocDao.getEntityWithSourceSystemAndLocalNumber(originSourceSystem, originLocalPlantNum);

                if (plnSplLocEntity != null) {
                    entryExists = true;
                }
            }

            // if we have an entry for location
            if (entryExists) {

                PlanCnsMaterialPlanStatusEntity materialPlanStatusEntity = materialPlanStatusDao.getEntityWithLocalMaterialNumberAndlLocalPlantAndSourceSystem(localMatNum, originLocalPlantNum, originSourceSystem);

                //if material relevant
                if (materialPlanStatusEntity.getSpRelevant().equals("X")) {

                    String prodId = materialGlobalV1Entity.getPrimaryPlanningCode();

                    if (materialGlobalV1Entity.getPrimaryPlanningCode() == null ||
                            materialGlobalV1Entity.getPrimaryPlanningCode().equals("")) {
                        prodId = materialGlobalV1Entity.getMaterialNumber();
                    }
                    //N12 & N14 Finish
                    gdmTransportBo.setFromProductId(prodId);
                    gdmTransportBo.setToProductId(prodId);

                } else {
                    curationFail = true;
                    rule = "N12 & N14";
                    error = "Not SP Relevant";
                }
            } else {
                curationFail = true;
                rule = "N12 & N14";
                error = "Location Doesn't exist";
            }
        }

        //no rules
        if (!curationFail) {
            PlanCnsProcessTypeEntity processTypeEntity = processTypeDao.getCnsProcessTypeById(tlaneItemExEntity.getProcessTypeId());
            gdmTransportBo.setLabel(processTypeEntity.getProcessTypeDesc());
            gdmTransportBo.setEndEff(tlaneItemExEntity.getValidTo());
            gdmTransportBo.setStartEff(tlaneItemExEntity.getValidFrom());
            gdmTransportBo.setProcessTypeId(tlaneItemExEntity.getProcessTypeId());
            gdmTransportBo.setTransportOffset(tlaneItemExEntity.getLeadTime());
            gdmTransportBo.setTransportType(tlaneItemExEntity.getMode());
        }


        // set as a fail or as a success
        if (curationFail) {
            resultObject.setFailData(this.writeFailDataToRegion(tlaneItemExEntity, rule, error));
            LogUtil.getCoreLog().info("**FAILED**");
            LogUtil.getCoreLog().info("RULE: " + rule);
            LogUtil.getCoreLog().info("ERROR: " + error);
        } else {
            resultObject.setBaseBo(gdmTransportBo);
        }

        return resultObject;
    }

    private boolean locationExists (String sourceSystem, String localPlantNum) {
        PlanCnsPlnSplLocEntity plnSplLocEntity = plnSplLocDao.getEntityWithSourceSystemAndLocalNumber(sourceSystem,localPlantNum);

        boolean entryExists = false;

        if (plnSplLocEntity != null) {
            entryExists = true;
        }
        else {
            EDMPlantV1Entity plantV1Entity = plantDao.getPlantWithSourceSystemAndLocalPlant(sourceSystem,localPlantNum);
            if (plantV1Entity.getLocalPlanningRelevant().equals("X")) {
                entryExists = true;
            }
        }

        return entryExists;
    }

    private ArrayList<String> getLocationStringArray (String location) {
        String[] locArray = location.split("_");
        ArrayList<String> locArrayList = new ArrayList<>(Arrays.asList(locArray));
        return locArrayList;
    }
    private String getLocalPlantNum (String location) {
        ArrayList<String> locList = getLocationStringArray(location);
        return locList.get(locList.size() - 1);
    }
    private String getSourceSystem (String location) {
        ArrayList<String> locList = getLocationStringArray(location);
        return String.join("_", locList.remove(locList.size() - 1));
    }

    private FailData writeFailDataToRegion(CnsTlaneItemExceptionEntity cnsTlaneItemExceptionEntity, String ruleCode, String errorValue) {
        FailData failData = new FailData();
        failData.setFunctionalArea("SP");
        failData.setInterfaceID("OMPGdmTransport");
        failData.setErrorCode(ruleCode);
        failData.setSourceSystem(this.getSourceSystem(cnsTlaneItemExceptionEntity.getOriginLocation()));
        failData.setKey1(this.getSourceSystem(cnsTlaneItemExceptionEntity.getOriginLocation()));
        failData.setKey2(this.getLocalPlantNum(cnsTlaneItemExceptionEntity.getOriginLocation()));
        failData.setKey3(cnsTlaneItemExceptionEntity.getMaterialNumber());
        failData.setKey4("");
        failData.setKey5("");
        failData.setErrorValue(errorValue);
        return failData;
    }


}
