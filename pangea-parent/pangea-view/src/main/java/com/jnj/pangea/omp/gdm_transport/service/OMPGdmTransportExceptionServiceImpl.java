package com.jnj.pangea.omp.gdm_transport.service;

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

import java.util.*;

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

        ResultObject resultObject = new ResultObject();
        OMPGdmTransportBo gdmTransportBo = new OMPGdmTransportBo();

        CnsTlaneItemExceptionEntity tlaneItemExEntity = (CnsTlaneItemExceptionEntity) o;
        EDMMaterialGlobalV1Entity materialGlobalV1Entity = null;

        boolean curationFail = false;
        List<Map<String, String>> failedRules = new ArrayList<>();

        String originLocalPlantNum = null;
        String originSourceSystem = null;
        String destLocalPlantNum = null;
        String destSourceSystem = null;
        String localMatNum = null;

        //N1
        if (tlaneItemExEntity.getDeletionIndicator() == null ||
                tlaneItemExEntity.getDeletionIndicator().equals("")) {

            gdmTransportBo.setProcessTypeId(tlaneItemExEntity.getProcessTypeId());
        } else {
            curationFail = true;
            Map<String,String> error = new HashMap<>();
            error.put("rule", "N1");
            error.put("error", "Deletion Indicator not Blank");
            failedRules.add(error);
        }

        // ensure material number is not null
        if (tlaneItemExEntity.getMaterialNumber() == null || tlaneItemExEntity.getMaterialNumber().equals("")) {
            curationFail = true;
            Map<String,String> error = new HashMap<>();
            error.put("rule", "N/A");
            error.put("error", "Material Number null in CNS Tlane Item Exception");
            failedRules.add(error);
        }

        if (!curationFail) {

            // set materials entity
            materialGlobalV1Entity = this.materialGlobalDao.getEntityWithPrimaryPlanningCode(tlaneItemExEntity.getMaterialNumber());

            // check if materialGlobalV1Entity is empty
            if (materialGlobalV1Entity == null ||
                    materialGlobalV1Entity.getMaterialNumber() == null ||
                    materialGlobalV1Entity.getMaterialNumber().equals("")) {

                materialGlobalV1Entity = this.materialGlobalDao.getEntityWithLocalMaterialNumber(tlaneItemExEntity.getMaterialNumber());

                // if still empty, stop curation
                if (materialGlobalV1Entity == null ||
                        materialGlobalV1Entity.getMaterialNumber() == null ||
                        materialGlobalV1Entity.getMaterialNumber().equals("")) {

                    curationFail = true;
                    Map<String,String> error = new HashMap<>();
                    error.put("rule", "N/A");
                    error.put("error", "Material Number doesn't exist in region Global Material V1");
                    failedRules.add(error);
                }
            }
        }

        // ensure origin is set
        if (tlaneItemExEntity.getOriginLocation() == null || tlaneItemExEntity.getOriginLocation().equals("")) {
            curationFail = true;
            Map<String,String> error = new HashMap<>();
            error.put("rule", "N/A");
            error.put("error", "Origin Location null in CNS Tlane Item Exception");
            failedRules.add(error);
        }

        // ensure destination is set
        if (tlaneItemExEntity.getDestinationLocation() == null || tlaneItemExEntity.getDestinationLocation().equals("")) {
            curationFail = true;
            Map<String,String> error = new HashMap<>();
            error.put("rule", "N/A");
            error.put("error", "Destination Location null in CNS Tlane Item Exception");
            failedRules.add(error);
        }

        if (!curationFail) {
            originLocalPlantNum = this.getLocalPlantNum(tlaneItemExEntity.getOriginLocation());
            originSourceSystem = this.getSourceSystem(tlaneItemExEntity.getOriginLocation());
            destLocalPlantNum = this.getLocalPlantNum(tlaneItemExEntity.getDestinationLocation());
            destSourceSystem = this.getSourceSystem(tlaneItemExEntity.getDestinationLocation());

            // get the mat num if curation hasn't failed (will have failed if entity not found)
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

            //N4
            gdmTransportBo.setMachineTypeId(IConstant.VALUE.TRANSPORT);

            //N5
            gdmTransportBo.setMinQuantity("");
            gdmTransportBo.setRequirementOffset("");

            //N6
            gdmTransportBo.setPlanLevelId(IConstant.VALUE.STAR);

            //query entities
            EDMMaterialPlantV1Entity materialPlantV1Entity = this.materialPlantDao.getEntityWithMaterialNumberPlantNumberSourceSystem(localMatNum, destLocalPlantNum, destSourceSystem);
            EDMSourceListV1Entity sourceListV1Entity = this.sourceListDao.getEntityWithMaterialNumberPlantNumberSourceSystemLocalDateAndBlankLocalBlockedSourceOfSupply(localMatNum, destLocalPlantNum, destSourceSystem);

            //N8
            if (materialPlantV1Entity != null &&
                    materialPlantV1Entity.getLocalPurchasingGroup() != null &&
                    !materialPlantV1Entity.getLocalPurchasingGroup().equals("")) {

                gdmTransportBo.setPurchasingGroup(materialPlantV1Entity.getLocalPurchasingGroup());
            } else {
                curationFail = true;
                Map<String,String> error = new HashMap<>();
                error.put("rule", "N8");
                error.put("error", "No Matching Record found in EDMMaterialPlantV1Entity");
                failedRules.add(error);
            }

            // ensure we have sourceListV1Entity
            if (sourceListV1Entity != null &&
                    sourceListV1Entity.getLocalPurchasingOrganization() != null &&
                    !sourceListV1Entity.getLocalPurchasingOrganization().equals("")) {

                //N9
                gdmTransportBo.setPurchasingOrganization(sourceListV1Entity.getLocalPurchasingOrganization());
                //N10
                gdmTransportBo.setVendorId(sourceListV1Entity.getLocalVendorAccountNumber());
            } else {
                curationFail = true;
                Map<String,String> error = new HashMap<>();
                error.put("rule", "N9 & N10");
                error.put("error", "No Matching Record found in EDMSourceListV1Entity");
                failedRules.add(error);
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
                Map<String,String> error = new HashMap<>();
                error.put("rule", "N11");
                error.put("error", "Location Doesn't exist");
                failedRules.add(error);
            }
        }

        //N13
        if (!curationFail) {

            if (this.locationExists(destSourceSystem, destLocalPlantNum)) {

                //N13
                String destLocation = destSourceSystem + "_V_" + destLocalPlantNum;
                gdmTransportBo.setToLocationId(destLocation);
            } else {
                curationFail = true;
                Map<String,String> error = new HashMap<>();
                error.put("rule", "N13");
                error.put("error", "Location Doesn't exist");
                failedRules.add(error);
            }
        }

        // N12 & N14
        if (!curationFail) {

            boolean entryExists = false;
            EDMPlantV1Entity plantV1Entity = this.plantDao.getPlantWithSourceSystemAndLocalPlant(originSourceSystem, originLocalPlantNum);

            //check if entry exists in EDMPlantV1Entity
            if (plantV1Entity != null) {
                entryExists = true;
            } else { //check if location is special, will be in PlanCnsPlnSplLocEntity

                PlanCnsPlnSplLocEntity plnSplLocEntity = this.plnSplLocDao.getEntityWithSourceSystemAndLocalNumber(originSourceSystem, originLocalPlantNum);

                if (plnSplLocEntity != null) {
                    entryExists = true;
                }
            }

            // if we have an entry for location
            if (entryExists) {

                PlanCnsMaterialPlanStatusEntity materialPlanStatusEntity = this.materialPlanStatusDao.getEntityWithLocalMaterialNumberAndlLocalPlantAndSourceSystem(localMatNum, originLocalPlantNum, originSourceSystem);

                //if material relevant
                if (materialPlanStatusEntity != null &&
                        materialPlanStatusEntity.getSpRelevant() != null &&
                        materialPlanStatusEntity.getSpRelevant().equals("X")) {

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
                    Map<String,String> error = new HashMap<>();
                    error.put("rule", "N12 & N14");
                    error.put("error", "Not SP Relevant");
                    failedRules.add(error);
                }
            } else {
                curationFail = true;
                Map<String,String> error = new HashMap<>();
                error.put("rule", "N12 & N14");
                error.put("error", "Location Doesn't exist");
                failedRules.add(error);
            }
        }

        //no rules
        if (!curationFail) {
            PlanCnsProcessTypeEntity processTypeEntity = this.processTypeDao.getCnsProcessTypeById(tlaneItemExEntity.getProcessTypeId());
            gdmTransportBo.setLabel(processTypeEntity.getProcessTypeDesc());
            gdmTransportBo.setEndEff(tlaneItemExEntity.getValidTo());
            gdmTransportBo.setStartEff(tlaneItemExEntity.getValidFrom());
            gdmTransportBo.setProcessTypeId(tlaneItemExEntity.getProcessTypeId());
            gdmTransportBo.setTransportOffset(tlaneItemExEntity.getLeadTime());
            gdmTransportBo.setTransportType(tlaneItemExEntity.getMode());
        }

        // set as a fail or as a success
        if (curationFail) {
            resultObject.setFailData(new FailData("SP", "OMPGdmTransport", failedRules.get(0).get("rule"), failedRules.get(0).get("error"),
                    this.getSourceSystem(tlaneItemExEntity.getOriginLocation()),
                    this.getSourceSystem(tlaneItemExEntity.getOriginLocation()),
                    this.getLocalPlantNum(tlaneItemExEntity.getOriginLocation()),
                    tlaneItemExEntity.getMaterialNumber()
                    )
            );
        } else {
            resultObject.setBaseBo(gdmTransportBo);
        }

        return resultObject;
    }

    /**
     * Check if a location exists. Common code part of some rules.
     * @param sourceSystem
     * @param localPlantNum
     * @return
     */
    private boolean locationExists (String sourceSystem, String localPlantNum) {
        PlanCnsPlnSplLocEntity plnSplLocEntity = plnSplLocDao.getEntityWithSourceSystemAndLocalNumber(sourceSystem,localPlantNum);

        boolean entryExists = false;

        if (plnSplLocEntity != null) {
            entryExists = true;
        }
        else {
            EDMPlantV1Entity plantV1Entity = plantDao.getPlantWithSourceSystemAndLocalPlant(sourceSystem,localPlantNum);

            if (plantV1Entity != null &&
                    plantV1Entity.getLocalPlanningRelevant() != null &&
                    plantV1Entity.getLocalPlanningRelevant().equals("X")) {
                entryExists = true;
            }
        }

        return entryExists;
    }

    /**
     * Get location string as list
     * @param location
     * @return
     */
    private ArrayList<String> getLocationStringArray (String location) {
        String[] locArray = location.split("_");
        return new ArrayList<>(Arrays.asList(locArray));
    }

    /**
     * Take the plant number from location string list
     * @param location
     * @return
     */
    private String getLocalPlantNum (String location) {
        ArrayList<String> locList = getLocationStringArray(location);
        return locList.get(locList.size() - 1);
    }

    /**
     * Take the source system from the location string list
     * @param location
     * @return
     */
    private String getSourceSystem (String location) {
        ArrayList<String> locList = getLocationStringArray(location);
        locList.remove(locList.size() - 1);
        return String.join("_", locList);
    }
}
