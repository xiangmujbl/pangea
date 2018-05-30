package com.jnj.pangea.omp.gdm_transport.service;

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

import java.util.*;

public class OMPGdmTransportExceptionServiceImpl extends OMPGdmTransportServiceParent {

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


    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        OMPGdmTransportBo gdmTransportBo = new OMPGdmTransportBo();
        CnsTlaneItemExceptionEntity tlaneItemExEntity = (CnsTlaneItemExceptionEntity) o;
        EDMSourceListV1Entity srcListV1Entity = null;
        String rule = "setup";
        this.curationFail = false;
        this.curationSkip = false;
        this.failedRules = new ArrayList<>();

        // check main data points are present
        if (tlaneItemExEntity.getMaterialNumber() == null || tlaneItemExEntity.getMaterialNumber().isEmpty()) {
            this.setFailedRule(rule, "Material Number null in CNS Tlane Item Exception");
        }

        if (!this.curationFail && (tlaneItemExEntity.getOriginLocation() == null || tlaneItemExEntity.getOriginLocation().isEmpty())) {
            this.setFailedRule(rule, "Origin Location is Empty. Can't generate source system or plant.");
        }

        if (!this.curationFail && (tlaneItemExEntity.getDestinationLocation() == null || tlaneItemExEntity.getDestinationLocation().isEmpty())) {
            this.setFailedRule(rule, "Destination Location is Empty. Can't generate source system or plant.");
        }

        //N1
        if (!this.curationSkip && !this.curationFail) {
            gdmTransportBo.setTransportId(this.ruleN1(tlaneItemExEntity));
        }

        if (!this.curationSkip && !this.curationFail) {
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

        //N8
        if (!this.curationSkip && !this.curationFail) {
            gdmTransportBo.setPurchasingGroup(this.ruleN8(tlaneItemExEntity));
        }

        //N9 & N10 Prep
        if (!this.curationSkip && !this.curationFail) {
            srcListV1Entity = this.ruleN9N10(tlaneItemExEntity);
        }

        //N9 & N10
        if (!this.curationSkip &&!this.curationFail && srcListV1Entity != null) {
            gdmTransportBo.setPurchasingOrganization(srcListV1Entity.getLocalPurchasingOrganization());
            gdmTransportBo.setVendorId(srcListV1Entity.getLocalVendorAccountNumber());
        }

        //N11
        if (!this.curationSkip && !this.curationFail) {
            gdmTransportBo.setFromLocationId(this.ruleN11(tlaneItemExEntity));
        }

        //N12
        if (!this.curationSkip && !this.curationFail) {
            gdmTransportBo.setFromProductId(this.ruleN12N14(tlaneItemExEntity, tlaneItemExEntity.getOriginLocation()));
        }

        //N14
        if (!this.curationSkip && !this.curationFail) {
            gdmTransportBo.setToProductId(this.ruleN12N14(tlaneItemExEntity, tlaneItemExEntity.getDestinationLocation()));
        }

        //N13
        if (!this.curationSkip && !this.curationFail) {
            gdmTransportBo.setToLocationId(this.ruleN13(tlaneItemExEntity));
        }

        //N15
        if (!this.curationSkip && !this.curationFail) {
            gdmTransportBo.setActiveSOPERP(IConstant.VALUE.NO);
        }

        //No Rules
        if (!this.curationSkip && !this.curationFail) {
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
        } else if (this.curationSkip) {
            resultObject = null;
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

        if (tlaneItemExceptionEntity.getDeletionIndicator() == null || tlaneItemExceptionEntity.getDeletionIndicator().isEmpty()) {
            transportId = tlaneItemExceptionEntity.getMaterialNumber() + IConstant.VALUE.BACK_SLANT
                    + tlaneItemExceptionEntity.getOriginLocation() + IConstant.VALUE.BACK_SLANT
                    + tlaneItemExceptionEntity.getDestinationLocation();
        } else {
            this.curationSkip = true;
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
            this.curationSkip = true;
            return null;
        }

        List<EDMMaterialPlantV1Entity> materialPlantV1EntityList = this.materialPlantV1Dao.getEntitiesWithLocalMaterialNumberLocalPlantNumberSourceSystem(locMatNum,localPlantNum,sourceSystem);

        if (!materialPlantV1EntityList.isEmpty()) {
            purchaseGroup = materialPlantV1EntityList.get(0).getPurchasingGrpCd();
        }
        else {
            this.curationSkip = true;
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
            this.curationSkip = true;
            return null;
        }

        List<EDMSourceListV1Entity> sourceListV1EntityList = this.sourceListV1Dao.getEntitiesWithLocalMaterialNumberPlantNumberSourceSystemLocalDateAndBlankLocalBlockedSourceOfSupply(locMatNum,localPlantNum,sourceSystem);
        EDMSourceListV1Entity sourceListV1Entity;

        if (!sourceListV1EntityList.isEmpty()) {
            sourceListV1Entity = sourceListV1EntityList.get(0);
        }
        else {
            this.curationSkip = true;
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

        this.curationSkip = true;
        return null;
    }

    /**
     *
     *
     * @param tlaneItemExceptionEntity
     * @return
     */
    private String ruleN12N14 (CnsTlaneItemExceptionEntity tlaneItemExceptionEntity, String location) {

        String rule = "N12_N14";
        String fromProdId = null;
        String srcSys = this.getSourceSystem(location);
        String locPlant = this.getLocalPlantNum(location);
        String locMatNum = this.getMaterialNumber(tlaneItemExceptionEntity);

        if (locMatNum == null) {
            this.curationSkip = true;
            return null;
        }

        if (!this.checkLocationRelevant(srcSys, locPlant)) {
            this.curationSkip = true;
            return null;
        }


        if (!this.checkMaterialRelevant(locMatNum, locPlant, srcSys)) {
            this.curationSkip = true;
            return null;
        }


        EDMMaterialGlobalV1Entity materialGlobalV1Entity = this.getGlobV1Entity(locMatNum);

        if (materialGlobalV1Entity == null) {
            this.curationSkip = true;
            return null;
        }


        fromProdId = materialGlobalV1Entity.getPrimaryPlanningCode();
        if (fromProdId == null || fromProdId.isEmpty()) {
            fromProdId = materialGlobalV1Entity.getMaterialNumber();
        }


        if (fromProdId == null || fromProdId.isEmpty()) {
            this.curationSkip = true;
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

        this.curationSkip = true;
        return null;
    }
}
