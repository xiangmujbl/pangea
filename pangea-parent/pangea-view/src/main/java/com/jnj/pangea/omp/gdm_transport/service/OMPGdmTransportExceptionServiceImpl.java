package com.jnj.pangea.omp.gdm_transport.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.*;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsMaterialPlanStatusDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlnSplLocDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsProcessTypeDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.entity.edm.EDMMaterialPlantV1Entity;
import com.jnj.pangea.common.entity.edm.EDMPlantV1Entity;
import com.jnj.pangea.common.entity.edm.EDMSourceListV1Entity;
import com.jnj.pangea.common.entity.plan.*;
import com.jnj.pangea.omp.gdm_transport.bo.OMPGdmTransportBo;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.*;

public class OMPGdmTransportExceptionServiceImpl {

    private static OMPGdmTransportExceptionServiceImpl instance;

    public static OMPGdmTransportExceptionServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmTransportExceptionServiceImpl();
        }
        return instance;
    }

    private EDMMaterialGlobalV1DaoImpl materialGlobalV1Dao = EDMMaterialGlobalV1DaoImpl.getInstance();
    private EDMMaterialPlantV1DaoImpl materialPlantV1Dao = EDMMaterialPlantV1DaoImpl.getInstance();
    private EDMPlantV1DaoImpl plantV1Dao = EDMPlantV1DaoImpl.getInstance();
    private EDMSourceListV1DaoImpl sourceListV1Dao = EDMSourceListV1DaoImpl.getInstance();
    private PlanCnsPlnSplLocDaoImpl plnSplLocDao = PlanCnsPlnSplLocDaoImpl.getInstance();
    private PlanCnsMaterialPlanStatusDaoImpl materialPlanStatusDao = PlanCnsMaterialPlanStatusDaoImpl.getInstance();
    private PlanCnsProcessTypeDaoImpl processTypeDao = PlanCnsProcessTypeDaoImpl.getInstance();

    private boolean curationFail = false;
    private List<Map<String, String>> failedRules = new ArrayList<>();


    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        OMPGdmTransportBo gdmTransportBo = new OMPGdmTransportBo();
        CnsTlaneItemExceptionEntity tlaneItemExEntity = (CnsTlaneItemExceptionEntity) o;
        EDMSourceListV1Entity srcListV1Entity = null;
        String rule = "setup";

        // ensure material number is not empty
        if (tlaneItemExEntity.getMaterialNumber().isEmpty()) {
            this.setFailedRule(rule, "Material Number null in CNS Tlane Item Exception");
        }

        if (tlaneItemExEntity.getOriginLocation().isEmpty()) {
            this.setFailedRule(rule, "Origin Location is Empty. Can't generate source system or plant.");
        }

        if (tlaneItemExEntity.getDestinationLocation().isEmpty()) {
            this.setFailedRule(rule, "Destination Location is Empty. Can't generate source system or plant.");
        }

        if (!this.curationFail) {
            //N1
            gdmTransportBo.setTransportId(this.ruleN1(tlaneItemExEntity));
        }

        if (!this.curationFail) {
            //N2
            gdmTransportBo.setActive(IConstant.VALUE.YES);
            gdmTransportBo.setActiveOPRERP(IConstant.VALUE.YES);

            //N4
            gdmTransportBo.setMachineTypeId(IConstant.VALUE.TRANSPORT);

            //N5
            gdmTransportBo.setMinQuantity("");
            gdmTransportBo.setRequirementOffset("");

            //N6
            gdmTransportBo.setPlanLevelId(IConstant.VALUE.STAR);
        }

        if (!this.curationFail) {
            //N8
            gdmTransportBo.setPurchasingGroup(this.ruleN8(tlaneItemExEntity));
        }

        if (!this.curationFail) {
            srcListV1Entity = this.ruleN9N10(tlaneItemExEntity);
        }

        if (!this.curationFail && srcListV1Entity != null) {

            //N9
            gdmTransportBo.setPurchasingOrganization(srcListV1Entity.getLocalPurchasingOrganization());

            //N10
            gdmTransportBo.setVendorId(srcListV1Entity.getLocalVendorAccountNumber());
        }

        if (!this.curationFail) {
            //N11
            gdmTransportBo.setFromLocationId(this.ruleN11(tlaneItemExEntity));
        }

        if (!this.curationFail) {

            String prodId = this.ruleN12N14(tlaneItemExEntity);
            //N12
            gdmTransportBo.setFromProductId(prodId);
            //N14
            gdmTransportBo.setToProductId(prodId);

        }

        if (!this.curationFail) {
            //N13
            gdmTransportBo.setToLocationId(this.ruleN13(tlaneItemExEntity));
        }

        if (!this.curationFail) {
            //N15
            gdmTransportBo.setActiveSOPERP(IConstant.VALUE.NO);
        }

        //no rules
        if (!this.curationFail) {
            PlanCnsProcessTypeEntity processTypeEntity =
                    this.processTypeDao.getCnsProcessTypeById(tlaneItemExEntity.getProcessTypeId());
            gdmTransportBo.setLabel(processTypeEntity.getProcessTypeDesc());
            gdmTransportBo.setEndEff(tlaneItemExEntity.getValidTo());
            gdmTransportBo.setStartEff(tlaneItemExEntity.getValidFrom());
            gdmTransportBo.setProcessTypeId(tlaneItemExEntity.getProcessTypeId());
            gdmTransportBo.setTransportOffset(tlaneItemExEntity.getLeadTime());
            gdmTransportBo.setTransportType(tlaneItemExEntity.getMode());
        }

        // set as a fail or as a success
        if (this.curationFail) {
            // failedRules could have multiple records in future if curationFail condition above is
            // removed (should stop skipping on 1st fail). for now get 1st fail only
            resultObject.setFailData(new FailData("SP", "OMPGdmTransport",
                    this.failedRules.get(0).get("rule"), this.failedRules.get(0).get("error"),
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
     * Check if a plant relevant
     * @param sourceSystem
     * @param localPlantNum
     * @return
     */
    private boolean plantRelevant (String sourceSystem, String localPlantNum) {
        List<PlanCnsPlnSplLocEntity> plnSplLocEntityList = plnSplLocDao.getEntitiesWithSourceSystemAndLocalPlantNumber(sourceSystem,localPlantNum);

        boolean entryExists = false;

        if (!plnSplLocEntityList.isEmpty()) {
            entryExists = true;
        }
        else {
            List<EDMPlantV1Entity> plantV1EntityList = plantV1Dao.getEntitiesWithSourceSystemAndLocalPlant(sourceSystem,localPlantNum);

            if (!plantV1EntityList.isEmpty()) {
                for (EDMPlantV1Entity plantV1Entity : plantV1EntityList) {
                    if (plantV1Entity.getLocalPlanningRelevant().equals("X")) {
                        entryExists = true;
                        break;
                    }
                }
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
        String[] locArray = location.split("_V_");
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
        return locList.get(0);
    }

    /**
     * set the class's failed rule list and flag
     * @param rule
     * @param msg
     */
    private void setFailedRule (String rule, String msg) {
        this.curationFail = true;
        Map<String,String> error = new HashMap<>();
        error.put("rule", rule);
        error.put("error", msg);
        this.failedRules.add(error);
    }

    /**
     * 1) get mat_glob_v1.local_material_number where mat_glob_v1.ppcode = tlane.matnum
     * 2) else get mat_glob_v1.local_material_number where mat_glob_v1.material_num = tlane.matnum
     * @param tlaneItemExceptionEntity
     * @return
     */
    private String getMaterialNumber (CnsTlaneItemExceptionEntity tlaneItemExceptionEntity) {

        String locMatNum = null;
        List<EDMMaterialGlobalV1Entity> matGlobV1EntityList = this.materialGlobalV1Dao.getEntitiesWithPrimaryPlanningCode(tlaneItemExceptionEntity.getMaterialNumber());

        if (!matGlobV1EntityList.isEmpty()) {
            locMatNum = matGlobV1EntityList.get(0).getLocalMaterialNumber();
        }
        else {
            matGlobV1EntityList = this.materialGlobalV1Dao.getEntitiesWithMaterialNumber(tlaneItemExceptionEntity.getMaterialNumber());
            if (!matGlobV1EntityList.isEmpty()) {
                locMatNum = matGlobV1EntityList.get(0).getLocalMaterialNumber();
            }
        }

        return locMatNum;
    }

    /**
     * checks if the location is relevant in rules n12 & n14
     * @param srcSys
     * @param locPlant
     * @return
     */
    private boolean checkLocationRelevant (String srcSys, String locPlant) {
        boolean exists = false;
        List<EDMPlantV1Entity> plantV1Entities = plantV1Dao.getEntitiesWithSourceSystemAndLocalPlant(srcSys,locPlant);

        if (plantV1Entities.isEmpty()) {
            List<PlanCnsPlnSplLocEntity> plnSplLocEntityList = plnSplLocDao.getEntitiesWithSourceSystemAndLocalPlantNumber(srcSys,locPlant);

            if (!plnSplLocEntityList.isEmpty()) {
                exists = true;
            }
        }
        else {
            exists = true;
        }

        return exists;
    }

    /**
     * gets the material global v1 entity as part of n12 & n14
     * @param locMatNum
     * @return
     */
    private EDMMaterialGlobalV1Entity getGlobV1Entity (String locMatNum) {
        List<EDMMaterialGlobalV1Entity> matGlobV1EntityList = this.materialGlobalV1Dao.getEntitiesWithLocalMaterialNumber(locMatNum);

        if (!matGlobV1EntityList.isEmpty()) {
            return matGlobV1EntityList.get(0);
        }

        return null;
    }

    /**
     * checks if the material is relevant on the site for rules n12 & n14
     * @param locMatNum
     * @param locPlant
     * @param srcSys
     * @return
     */
    private boolean checkMaterialRelevant (String locMatNum, String locPlant, String srcSys) {
        boolean exists = false;
        List<PlanCnsMaterialPlanStatusEntity> materialPlanStatusEntityList = this.materialPlanStatusDao.getEntitiesWithLocalMaterialNumberLocalPlantSourceSystemAndRelevant(locMatNum, locPlant, srcSys);

        if (!materialPlanStatusEntityList.isEmpty()) {
            exists = true;
        }

        return exists;
    }

    /**
     * 1) check for deletion indicator blank
     * 2) skip if not.
     * 3) concat mat num, origin loc and dest loc as transport id
     *
     * @param tlaneItemExceptionEntity
     * @return
     */
    private String ruleN1 (CnsTlaneItemExceptionEntity tlaneItemExceptionEntity) {

        String transportId = null;
        String rule = "N1";

        if (tlaneItemExceptionEntity.getDeletionIndicator().isEmpty()) {
            transportId = tlaneItemExceptionEntity.getMaterialNumber() + IConstant.VALUE.BACK_SLANT
                    + tlaneItemExceptionEntity.getOriginLocation() + IConstant.VALUE.BACK_SLANT
                    + tlaneItemExceptionEntity.getDestinationLocation();
        }
        else {
            this.setFailedRule(rule, "Deletion Indicator not Blank");
            return null;
        }

        return transportId;
    }

    /**
     * 1) no matnum ? skip : continue
     * 2) get mat_plant_v1.localPurchaseGrp where mat_plant_v1.locMatNum = locMatNum and
     * mat_plant_v1.locPlantNum = locPlntNum and mat_plant_v1.srcSys = srcSys
     *
     * @param tlaneItemExceptionEntity
     * @return
     */
    private String ruleN8 (CnsTlaneItemExceptionEntity tlaneItemExceptionEntity) {

        String purchaseGroup = null;

        String rule = "N8";
        String locMatNum = null;

        String localPlantNum = this.getLocalPlantNum(tlaneItemExceptionEntity.getDestinationLocation());
        String sourceSystem = this.getSourceSystem(tlaneItemExceptionEntity.getDestinationLocation());

        locMatNum = this.getMaterialNumber(tlaneItemExceptionEntity);

        //skip rest if no mat num was found
        if (locMatNum == null) {
            this.setFailedRule(rule, "Material number not found in MaterialGlobalV1");
            return null;
        }

        List<EDMMaterialPlantV1Entity> materialPlantV1EntityList = this.materialPlantV1Dao.getEntitiesWithLocalMaterialNumberLocalPlantNumberSourceSystem(locMatNum,localPlantNum,sourceSystem);

        if (!materialPlantV1EntityList.isEmpty()) {
            purchaseGroup = materialPlantV1EntityList.get(0).getLocalPurchasingGroup();
        }
        else {
            this.setFailedRule(rule, "No Records found in MaterialGlobalV1 for local material, local plant and source system combination");
            return null;
        }

        return purchaseGroup;
    }

    /**
     * 1) no matnum ? skip : continue
     * 2) ...
     *
     * @param tlaneItemExceptionEntity
     * @return
     */
    private EDMSourceListV1Entity ruleN9N10 (CnsTlaneItemExceptionEntity tlaneItemExceptionEntity) {

        String rule = "N9_N10";

        String localPlantNum = this.getLocalPlantNum(tlaneItemExceptionEntity.getDestinationLocation());
        String sourceSystem = this.getSourceSystem(tlaneItemExceptionEntity.getDestinationLocation());
        String locMatNum = this.getMaterialNumber(tlaneItemExceptionEntity);

        //skip rest if no mat num was found
        if (locMatNum == null) {
            this.setFailedRule(rule, "Material number not found in MaterialGlobalV1");
            return null;
        }

        List<EDMSourceListV1Entity> sourceListV1EntityList = this.sourceListV1Dao.getEntitiesWithLocalMaterialNumberPlantNumberSourceSystemLocalDateAndBlankLocalBlockedSourceOfSupply(locMatNum,localPlantNum,sourceSystem);
        EDMSourceListV1Entity sourceListV1Entity;

        if (!sourceListV1EntityList.isEmpty()) {
            sourceListV1Entity = sourceListV1EntityList.get(0);
        }
        else {
            this.setFailedRule(rule,"No Matching Record found in EDMSourceListV1Entity");
            return null;
        }

        return sourceListV1Entity;
    }

    /**
     *
     *
     * @param tlaneItemExceptionEntity
     * @return
     */
    private String ruleN11 (CnsTlaneItemExceptionEntity tlaneItemExceptionEntity) {

        String rule = "N11";

        String srcSys = this.getSourceSystem(tlaneItemExceptionEntity.getOriginLocation());
        String locPlant = this.getLocalPlantNum(tlaneItemExceptionEntity.getOriginLocation());
        boolean planningRelevant = this.plantRelevant(srcSys, locPlant);

        if (planningRelevant) {
            return tlaneItemExceptionEntity.getOriginLocation();
        }

        this.setFailedRule(rule, "Planning not relevant");
        return null;
    }

    /**
     *
     *
     * @param tlaneItemExceptionEntity
     * @return
     */
    private String ruleN12N14 (CnsTlaneItemExceptionEntity tlaneItemExceptionEntity) {

        String rule = "N12_N14";
        String fromProdId = null;
        String locMatNum = this.getMaterialNumber(tlaneItemExceptionEntity);

        if (locMatNum == null) {
            this.setFailedRule(rule, "Material number not found in MaterialGlobalV1");
            return null;
        }

        String srcSys = this.getSourceSystem(tlaneItemExceptionEntity.getOriginLocation());
        String locPlant = this.getLocalPlantNum(tlaneItemExceptionEntity.getOriginLocation());

        if (!this.checkLocationRelevant(srcSys, locPlant)) {
            this.setFailedRule(rule, "Location not found in plant v1 or plan spl loc");
            return null;
        }

        if (!this.checkMaterialRelevant(locMatNum, locPlant, srcSys)) {
            this.setFailedRule(rule, "No Records relevant in materialPlanStatus");
            return null;
        }

        EDMMaterialGlobalV1Entity materialGlobalV1Entity = this.getGlobV1Entity(locMatNum);

        if (materialGlobalV1Entity == null) {
            this.setFailedRule(rule, "No record found for local material number in material global v1");
            return null;
        }

        fromProdId = materialGlobalV1Entity.getPrimaryPlanningCode();
        if (fromProdId.isEmpty()) {
            fromProdId = materialGlobalV1Entity.getMaterialNumber();
        }

        if (fromProdId.isEmpty()) {
            this.setFailedRule(rule, "Material Number empty for fromProdId");
        }

        return fromProdId;
    }

    /**
     *
     *
     * @param tlaneItemExceptionEntity
     * @return
     */
    private String ruleN13 (CnsTlaneItemExceptionEntity tlaneItemExceptionEntity) {

        String rule = "N13";

        String srcSys = this.getSourceSystem(tlaneItemExceptionEntity.getDestinationLocation());
        String locPlant = this.getLocalPlantNum(tlaneItemExceptionEntity.getDestinationLocation());
        boolean planningRelevant = this.plantRelevant(srcSys, locPlant);

        if (planningRelevant) {
            return tlaneItemExceptionEntity.getDestinationLocation();
        }

        this.setFailedRule(rule, "Planning not relevant");
        return null;
    }
}
