package com.jnj.pangea.omp.gdm_product_location.service;

import com.jnj.adf.grid.utils.DateUtil;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.*;
import com.jnj.pangea.common.dao.impl.plan.*;
import com.jnj.pangea.common.entity.edm.EDMMatPlantFiV1Entity;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.entity.edm.EDMMaterialPlantV1Entity;
import com.jnj.pangea.common.entity.edm.EDMSourceListV1Entity;
import com.jnj.pangea.common.entity.plan.*;
import com.jnj.pangea.omp.gdm_product_location.bo.OMPGdmProductLocationBo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;


public class OMPGdmProductLocationServiceImpl {

    private static OMPGdmProductLocationServiceImpl instance;

    public static OMPGdmProductLocationServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmProductLocationServiceImpl();
        }
        return instance;
    }

    private EDMMaterialGlobalV1DaoImpl materialGlobalV1Dao = EDMMaterialGlobalV1DaoImpl.getInstance();
    private EDMSourceSystemV1DaoImpl systemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    private EDMMatPlantFiV1DaoImpl matPlantFinV1Dao = EDMMatPlantFiV1DaoImpl.getInstance();
    private PlanCnsSplProcTypDaoImpl cnsSplProcTypDao = PlanCnsSplProcTypDaoImpl.getInstance();
    private PlanCnsProdLocAttribDaoImpl cnsProdLocAttribDao = PlanCnsProdLocAttribDaoImpl.getInstance();
    private PlanLocMinShelfDaoImpl locMinShelfDao = PlanLocMinShelfDaoImpl.getInstance();
    private PlanCnsProcTypeDaoImpl cnsProcTypeDao = PlanCnsProcTypeDaoImpl.getInstance();
    private PlanCnsMaterialPlanStatusDaoImpl cnsMaterialPlanStatusDao = PlanCnsMaterialPlanStatusDaoImpl.getInstance();
    private PlanCnsPlngStratGrpDaoImpl cnsPlngStratGrpDao = PlanCnsPlngStratGrpDaoImpl.getInstance();
    private PlanCnsLotSizeKeyTransDaoImpl cnsLotSizeKeyTransDao = PlanCnsLotSizeKeyTransDaoImpl.getInstance();
    private PlanCnsConModeDaoImpl cnsConModeDao = PlanCnsConModeDaoImpl.getInstance();
    private PlanCnsAbcIndDaoImpl cnsAbcIndDao = PlanCnsAbcIndDaoImpl.getInstance();
    private EDMPlantV1DaoImpl plantV1Dao = new EDMPlantV1DaoImpl();
    private PlanConsTimeDepXchangeDaoImpl planConsTimeDepXchangeDao = new PlanConsTimeDepXchangeDaoImpl();
    private EDMSourceListV1DaoImpl sourceListDao = EDMSourceListV1DaoImpl.getInstance();
    private PlanSplPlnLocDaoImpl splPlnLocDao = PlanSplPlnLocDaoImpl.getInstance();

    public List<ResultObject> buildView(String key, Object o, Object o2) {
        List<ResultObject> resultObjectList = new ArrayList<ResultObject>();
        EDMMaterialPlantV1Entity materialPlantV1Entity = (EDMMaterialPlantV1Entity) o;
        List<OMPGdmProductLocationBo> boList = null;
        EDMMaterialGlobalV1Entity edmMaterialGlobalV1Entity = null;
        PlanCnsMaterialPlanStatusEntity planStatusEntity = null;
        EDMMatPlantFiV1Entity matPlantFiV1Entity = null;
        EDMSourceListV1Entity edmSourceListV1Entity = null;
        PlanSplPlnLocEntity planSplPlnLocEntity = null;
        //rules J1
        String localMaterialNumber = materialPlantV1Entity.getLocalMaterialNumber();
        if (!"".equals(localMaterialNumber)) {
            edmMaterialGlobalV1Entity = materialGlobalV1Dao.getEntityWithLocalMaterialNumber(localMaterialNumber);

        }
        String localPlant = materialPlantV1Entity.getLocalPlant();
        if (null != localPlant && !"".equals(localPlant)) {
            planStatusEntity = cnsMaterialPlanStatusDao.getEntityWithLocalMaterialNumberAndlLocalPlant(localMaterialNumber, localPlant);
        } else {
            FailData failData = new FailData();
            failData.setErrorCode("C1");
            failData.setErrorValue("Unable to find assigned plant");
            failData.setFunctionalArea("SP");
            failData.setInterfaceID("OMPGdmProductLocation");
            failData.setSourceSystem("omp");
            failData.setKey1(materialPlantV1Entity.getLocalPlant());
            failData.setKey2(materialPlantV1Entity.getLocalMaterialNumber());
            failData.setKey3(materialPlantV1Entity.getSourceSystem());
            failData.setKey4("");
            failData.setKey5("");
            ResultObject resultObject = new ResultObject();
            resultObject.setFailData(failData);
            resultObjectList.add(resultObject);
            return resultObjectList;
        }
        if (planStatusEntity != null && edmMaterialGlobalV1Entity != null) {
            boList = getFieldWithJ1(edmMaterialGlobalV1Entity, planSplPlnLocEntity, materialPlantV1Entity, planStatusEntity);
        }
        if (boList == null || boList.isEmpty()) {
            FailData failData = new FailData();
            failData.setErrorCode("J1");
            failData.setErrorValue("Unable to find DPParentCode");
            failData.setFunctionalArea("SP");
            failData.setInterfaceID("OMPGdmProductLocation");
            failData.setSourceSystem("omp");
            failData.setKey1(materialPlantV1Entity.getLocalPlant());
            failData.setKey2(materialPlantV1Entity.getLocalMaterialNumber());
            failData.setKey3(materialPlantV1Entity.getSourceSystem());
            failData.setKey4("");
            failData.setKey5("");
            ResultObject resultObject = new ResultObject();
            resultObject.setFailData(failData);
            resultObjectList.add(resultObject);
            return resultObjectList;
        }

        for (OMPGdmProductLocationBo bo : boList) {
            //rules D1
            bo.setActive(IConstant.VALUE.YES);

            //rule E10
            bo.setActiveSOPERP(IConstant.VALUE.NO);

            if (planStatusEntity != null) {
                //rules T1
                if (IConstant.VALUE.X.equals(planStatusEntity.getDpRelevant())) {
                    bo.setActiveFCTERP(IConstant.VALUE.YES);
                } else {
                    bo.setActiveFCTERP(IConstant.VALUE.NO);
                }

            }
            //rules E1
            PlanCnsProcTypeEntity planCnsProcTypeEntity = getFieldWithE1(materialPlantV1Entity.getLocalProcurementType(), materialPlantV1Entity.getSourceSystem());
            if (planCnsProcTypeEntity != null) {
                bo.setBeskz(planCnsProcTypeEntity.getProcurementType());
            }
            //rules E2
            PlanCnsAbcIndEntity abcIndEntity = getFieldWithE2(materialPlantV1Entity.getLocalAbcIndicator(), materialPlantV1Entity.getSourceSystem());
            if (abcIndEntity != null) {
                bo.setMaabc(abcIndEntity.getIndicator());
            }

            PlanLocMinShelfEnity planLocMinShelfEnity = getFieldWithE4(materialPlantV1Entity.getLocalMaterialNumber(), materialPlantV1Entity.getLocalPlant());
            PlanLocMinShelfEnity planLocMinShelfEnity1 = getFieldWithlocalPlant(materialPlantV1Entity.getLocalPlant());
            //rules E4   rules E5
            if (planLocMinShelfEnity != null) {
                bo.setMinmrsl(planLocMinShelfEnity.getMinMinShelfLife());
                bo.setMinRemainingShelfLife(planLocMinShelfEnity.getMinShelfLife());
            } else if (planLocMinShelfEnity1 != null) {
                bo.setMinmrsl(planLocMinShelfEnity1.getMinMinShelfLife());
                bo.setMinRemainingShelfLife(planLocMinShelfEnity1.getMinShelfLife());
            } else {
                bo.setMinmrsl("");
                bo.setMinRemainingShelfLife(edmMaterialGlobalV1Entity.getMinRemShelfLife());
            }
            //rules E12
            PlanCnsProdLocAttribEntity attribEntity = getFieldWithE12(materialPlantV1Entity.getLocalMaterialNumber(), materialPlantV1Entity.getLocalPlant(), materialPlantV1Entity.getSourceSystem());
            if (attribEntity != null) {
                bo.setSupplyGroup(attribEntity.getSupplyGroup());
            }

            //rules E6
            PlanCnsSplProcTypEntity planCnsSplProcTypEntity = getFieldWithE6(materialPlantV1Entity.getLocalSpecialProcurementType(), materialPlantV1Entity.getSourceSystem());
            if (planCnsSplProcTypEntity != null) {
                bo.setSobsl(planCnsSplProcTypEntity.getSpecialProcurementType());
            }
            //rules E7
            PlanCnsPlngStratGrpEntity stratGrpEntity = getFieldWithE7(materialPlantV1Entity.getLocalPlanningStrategyGroup(), materialPlantV1Entity.getSourceSystem());
            if (stratGrpEntity != null) {
                bo.setStrgr(stratGrpEntity.getPlanStratGrp());
            }
            //rules E8
            PlanCnsConModeEntity modeEntity = getFieldWithE8(materialPlantV1Entity.getLocalConsumptionMode(), materialPlantV1Entity.getSourceSystem());
            if (modeEntity != null) {
                bo.setVrmod(modeEntity.getLocalConsumptionMode());
            }
            //rules E9
            PlanCnsLotSizeKeyTransEntity planCnsLotSizeKeyTransEntity = getFieldWithE9(materialPlantV1Entity.getLocalLotSize(), materialPlantV1Entity.getSourceSystem());
            if (planCnsLotSizeKeyTransEntity != null) {
                bo.setDisls(planCnsLotSizeKeyTransEntity.getLotSizeKey());
            }

            //No rules
            if (edmMaterialGlobalV1Entity != null) {
                bo.setLabel(edmMaterialGlobalV1Entity.getLocalRefDescription());
                bo.setTotalShelfLife(edmMaterialGlobalV1Entity.getTotalShelfLife());
                bo.setProductTypeId(edmMaterialGlobalV1Entity.getMaterialType());
            }
            if (materialPlantV1Entity != null) {
                //rule E11
                if (materialPlantV1Entity.getLocalBatchManagementRequirementIndicator().equals(IConstant.VALUE.X)) {
                    bo.setBatchManaged(IConstant.VALUE.YES);
                } else {
                    bo.setBatchManaged(IConstant.VALUE.NO);
                }

                bo.setMinInventoryQuantity(materialPlantV1Entity.getLocalMinimumSafetyStock());
                bo.setMaxInventoryQuantity(materialPlantV1Entity.getLocalMaximumStockLevel());
                bo.setBstfe(materialPlantV1Entity.getLocalFixedLotSize());
                bo.setBstma(materialPlantV1Entity.getLocalMaximumLotSize());
                bo.setBstmi(materialPlantV1Entity.getLocalMinimumLotSize());
                bo.setBstrf(materialPlantV1Entity.getLocalRoundingValueForPoq());
                bo.setDismm(materialPlantV1Entity.getLocalMrpType());
                bo.setDispo(materialPlantV1Entity.getLocalMrpController());
                bo.setDzeit(materialPlantV1Entity.getLocalInHouseProcessingTime());
                bo.setEislo(materialPlantV1Entity.getLocalMinimumSafetyStock());
                bo.setFevor(materialPlantV1Entity.getLocalProductionSupervisor());
                bo.setFixedHorizon(materialPlantV1Entity.getLocalPlanningTimeFence());
                bo.setFrtme(materialPlantV1Entity.getLocalProductionUnit());
                bo.setInsmk(materialPlantV1Entity.getLocalPostToInspectionStock());
                bo.setKausf(materialPlantV1Entity.getLocalComponentScrapInPercent());
                bo.setKzkri(materialPlantV1Entity.getLocalCriticalPart());
                bo.setLeadTime(materialPlantV1Entity.getLocalPlannedDeliveryTimeInDays());
                bo.setMmsta(materialPlantV1Entity.getLocalPlantStatus());
                bo.setMtvfp(materialPlantV1Entity.getLocalCheckingGroupForAvailabilityCheck());
                bo.setReplenishmentLotSize(materialPlantV1Entity.getLocalInstalledReplenishmentLotSize());
                bo.setShflg(materialPlantV1Entity.getLocalSafetyTimeIndicator());
                bo.setVint1(materialPlantV1Entity.getLocalConsumptionPeriodBackward());
                bo.setVint2(materialPlantV1Entity.getLocalConsumptionPeriodForward());
                bo.setWebaz(materialPlantV1Entity.getLocalGoodsReceiptProcessingTimeInDays());

            }
            ResultObject resultObject = new ResultObject();
            resultObject.setBaseBo(bo);
            resultObjectList.add(resultObject);
        }
        return resultObjectList;
    }


    //rules J1
    public List<OMPGdmProductLocationBo> getFieldWithJ1(EDMMaterialGlobalV1Entity edmMaterialGlobalV1Entity, PlanSplPlnLocEntity planSplPlnLocEntity, EDMMaterialPlantV1Entity materialPlantV1Entity, PlanCnsMaterialPlanStatusEntity planStatusEntity) {
        List<OMPGdmProductLocationBo> boList = new ArrayList<OMPGdmProductLocationBo>();


        if (edmMaterialGlobalV1Entity == null) {
            return new ArrayList<OMPGdmProductLocationBo>();
        }

        if (IConstant.VALUE.X.equals(planStatusEntity.getSpRelevant()) || IConstant.VALUE.X.equals(planStatusEntity.getNoPlanRelevant())) {
            //rules T2
            if (StringUtils.isEmpty(edmMaterialGlobalV1Entity.getPrimaryPlanningCode()) && StringUtils.isEmpty(edmMaterialGlobalV1Entity.getLocalMaterialNumber())) {
                return null;
            }
            
            OMPGdmProductLocationBo OMPGdmProductLocationBo = new OMPGdmProductLocationBo();
            OMPGdmProductLocationBo.setActiveOPRERP(IConstant.VALUE.YES);
            if (StringUtils.isBlank(edmMaterialGlobalV1Entity.getPrimaryPlanningCode())) {
            	OMPGdmProductLocationBo.setProductId(materialPlantV1Entity.getSourceSystem() + IConstant.VALUE.UNDERLINE + edmMaterialGlobalV1Entity.getMaterialNumber());
            } else {
            	OMPGdmProductLocationBo.setProductId(materialPlantV1Entity.getSourceSystem() + IConstant.VALUE.UNDERLINE + edmMaterialGlobalV1Entity.getPrimaryPlanningCode());
            }
            OMPGdmProductLocationBo.setLocationId(materialPlantV1Entity.getSourceSystem() + "_" + materialPlantV1Entity.getLocalPlant());
            boList.add(OMPGdmProductLocationBo);

            List<EDMSourceListV1Entity> sourceListV1Entries = sourceListDao.getEntityListWithConditions(materialPlantV1Entity.getSourceSystem(), materialPlantV1Entity.getLocalMaterialNumber(), materialPlantV1Entity.getLocalPlant());
            if (null != sourceListV1Entries) {
                List<String> localvendorAccountNumberList = new ArrayList<>();
                for (EDMSourceListV1Entity sourceListV1Entity : sourceListV1Entries) {
                    Date date = new Date();
                    Date LocalSourceListRecordValidFrom = DateUtil.parse(sourceListV1Entity.getLocalSourceListRecordValidFrom());
                    Date LocalSourceListRecordValidTo = DateUtil.parse(sourceListV1Entity.getLocalSourceListRecordValidTo());
                    String localBlockedSourceofSupply = sourceListV1Entity.getLocalBlockedSourceofSupply();
                    if (StringUtils.isBlank(localBlockedSourceofSupply) && date.getTime() > LocalSourceListRecordValidFrom.getTime() && date.getTime() < LocalSourceListRecordValidTo.getTime()) {
                        String localvendorAccountNumber = sourceListV1Entity.getLocalVendorAccountNumber();
                        if (StringUtils.isNotEmpty(localvendorAccountNumber)) {
                            localvendorAccountNumberList.add(localvendorAccountNumber);
                            List<PlanSplPlnLocEntity> splPlnLocEntries = splPlnLocDao.getEntityListWithConditions(sourceListV1Entity.getSourceSystem(), sourceListV1Entity.getLocalVendorAccountNumber());
                            if (splPlnLocEntries.size() <= 0) {
                                return new ArrayList<>();
                            } else {
                                    for (PlanSplPlnLocEntity splPlnLocEntity : splPlnLocEntries) {
                                        if(splPlnLocEntity.getVendorOrCustomer().equals("V")){
                                        	OMPGdmProductLocationBo OMPGdmProductLocationBoVendor = new OMPGdmProductLocationBo();
                                            OMPGdmProductLocationBoVendor.setActiveOPRERP(OMPGdmProductLocationBo.getActiveOPRERP());
                                            OMPGdmProductLocationBoVendor.setProductId(OMPGdmProductLocationBo.getProductId());
                                            if ("".equals(splPlnLocEntity.getLocalPlant())) {
                                            	OMPGdmProductLocationBoVendor.setLocationId(materialPlantV1Entity.getSourceSystem() + "_" + splPlnLocEntity.getVendorOrCustomer() + "_" + splPlnLocEntity.getLocalNumber());
                                            } else {
                                            	OMPGdmProductLocationBoVendor.setLocationId(materialPlantV1Entity.getSourceSystem() + "_" + materialPlantV1Entity.getLocalPlant() + "$" + splPlnLocEntity.getLocalNumber());
                                            }
                                            boList.add(OMPGdmProductLocationBoVendor);
                                        }
                                    }
                            }
                        } else {
                            return new ArrayList<>();
                        }
                    }
                    break; //break for first record. 
                }
            }
            return boList;
        } else {
            return new ArrayList<>();
        }
    }


    /**
     * rules E1
     */
    public PlanCnsProcTypeEntity getFieldWithE1(String localProcurementType, String sourceSystem) {
        if ("".equals(localProcurementType) || "".equals(sourceSystem)) {
            return null;
        }
        PlanCnsProcTypeEntity entity = cnsProcTypeDao.getEntityWithLocalProcurementTypeAndsourceSystem(localProcurementType, sourceSystem);
        return entity;
    }

    /**
     * rules E2
     */
    private PlanCnsAbcIndEntity getFieldWithE2(String localAbcIndicator, String sourceSystem) {
        if ("".equals(localAbcIndicator) || "".equals(sourceSystem)) {
            return null;
        }
        PlanCnsAbcIndEntity abcIndEntity = cnsAbcIndDao.getEntityWithLocalIndicatorAndsourceSystem(localAbcIndicator, sourceSystem);
        return abcIndEntity;
    }

    /**
     * rules E4
     *
     * @param localMaterialNumber
     * @param localPlant
     */
    private PlanLocMinShelfEnity getFieldWithE4(String localMaterialNumber, String localPlant) {
        if ("".equals(localMaterialNumber) || "".equals(localPlant)) {
            return null;
        }
        PlanLocMinShelfEnity planLocMinShelfEnity = locMinShelfDao.getEntityWithLocalMaterialNumberAndLocalPlant(localMaterialNumber, localPlant);

        return planLocMinShelfEnity;
    }

    /**
     * rules E4
     *
     * @param localPlant
     */
    private PlanLocMinShelfEnity getFieldWithlocalPlant(String localPlant) {
        if ("".equals(localPlant)) {
            return null;
        }
        PlanLocMinShelfEnity planLocMinShelfEnity = locMinShelfDao.getEntityWithLocalPlant(localPlant);

        return planLocMinShelfEnity;
    }

    /**
     * rules E12
     *
     * @param localMaterialNumber
     * @param localPlant
     */
    private PlanCnsProdLocAttribEntity getFieldWithE12(String localMaterialNumber, String localPlant, String
            sourceSystem) {
        if ("".equals(localMaterialNumber) || "".equals(localPlant) || "".equals(sourceSystem)) {
            return null;
        }
        PlanCnsProdLocAttribEntity attribEntity = cnsProdLocAttribDao.getEntityWithLocalMaterialNumberAndLocalPlantAndSourceSystem(localMaterialNumber, localPlant, sourceSystem);
        return attribEntity;
    }

    /**
     * rules E6
     *
     * @param localSpecialProcurementType
     * @param sourceSystem
     */
    private PlanCnsSplProcTypEntity getFieldWithE6(String localSpecialProcurementType, String sourceSystem) {
        if ("".equals(localSpecialProcurementType) || "".equals(sourceSystem)) {
            return null;
        }
        PlanCnsSplProcTypEntity procTypEntity = cnsSplProcTypDao.getEntityWithLocalSpecialProcurementTypeAndSourceSystem(localSpecialProcurementType, sourceSystem);
        return procTypEntity;
    }

    /**
     * rules E7
     *
     * @param localPlanningStrategyGroup
     * @param sourceSystem
     */
    private PlanCnsPlngStratGrpEntity getFieldWithE7(String localPlanningStrategyGroup, String sourceSystem) {
        if ("".equals(localPlanningStrategyGroup) || "".equals(sourceSystem)) {
            return null;
        }
        PlanCnsPlngStratGrpEntity stratGrpEntity = cnsPlngStratGrpDao.getEntityWithLocalPlanStratGrpAndSourceSystem(localPlanningStrategyGroup, sourceSystem);
        return stratGrpEntity;
    }

    /**
     * rules E8
     *
     * @param localConsumptionMode
     * @param sourceSystem
     */
    private PlanCnsConModeEntity getFieldWithE8(String localConsumptionMode, String sourceSystem) {
        if ("".equals(localConsumptionMode) || "".equals(sourceSystem)) {
            return null;
        }
        PlanCnsConModeEntity modeEntity = cnsConModeDao.getEntityWithLocalConsumptionModeAndSourceSystem(localConsumptionMode, sourceSystem);
        return modeEntity;
    }

    /**
     * rules E9
     *
     * @param localLotSize
     * @param sourceSystem
     */
    private PlanCnsLotSizeKeyTransEntity getFieldWithE9(String localLotSize, String sourceSystem) {
        if ("".equals(localLotSize) || "".equals(sourceSystem)) {
            return null;
        }
        PlanCnsLotSizeKeyTransEntity planCnsLotSizeKeyTransEntity = cnsLotSizeKeyTransDao.getEntityWithLocalLotSizeKeyAndSourceSystem(localLotSize, sourceSystem);
        return planCnsLotSizeKeyTransEntity;
    }
}
