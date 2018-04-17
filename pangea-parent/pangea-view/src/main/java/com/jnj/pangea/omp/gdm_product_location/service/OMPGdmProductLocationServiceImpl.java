package com.jnj.pangea.omp.gdm_product_location.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMMatPlantFiV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialGlobalV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMPlantV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.dao.impl.plan.*;
import com.jnj.pangea.common.entity.edm.*;
import com.jnj.pangea.common.entity.plan.*;
import com.jnj.pangea.omp.gdm_product_location.bo.OMPGdmProductLocationBo;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private PlanCnsProcTypeDaoImpl cnsProcTypeDao = PlanCnsProcTypeDaoImpl.getInstance();
    private PlanCnsMaterialPlanStatusDaoImpl cnsMaterialPlanStatusDao = PlanCnsMaterialPlanStatusDaoImpl.getInstance();
    private PlanCnsPlngStratGrpDaoImpl cnsPlngStratGrpDao = PlanCnsPlngStratGrpDaoImpl.getInstance();
    private PlanCnsLotSizeKeyTransDaoImpl cnsLotSizeKeyTransDao = PlanCnsLotSizeKeyTransDaoImpl.getInstance();
    private PlanCnsConModeDaoImpl cnsConModeDao = PlanCnsConModeDaoImpl.getInstance();
    private PlanCnsAbcIndDaoImpl cnsAbcIndDao = PlanCnsAbcIndDaoImpl.getInstance();
    private EDMPlantV1DaoImpl plantV1Dao = new EDMPlantV1DaoImpl();
    private PlanConsTimeDepXchangeDaoImpl planConsTimeDepXchangeDao = new PlanConsTimeDepXchangeDaoImpl();

    public List<ResultObject> buildView(String key, Object o, Object o2) {

        List<ResultObject> resultObjectList = new ArrayList<ResultObject>();
        EDMMaterialPlantV1Entity materialPlantV1Entity = (EDMMaterialPlantV1Entity) o;
        //rules J1
        if (materialPlantV1Entity == null) {
            return resultObjectList;
        }
        String localMaterialNumber = materialPlantV1Entity.getLocalMaterialNumber();
        if ("".equals(localMaterialNumber)){
            return resultObjectList;
        }
        EDMMaterialGlobalV1Entity edmMaterialGlobalV1Entity = materialGlobalV1Dao.getEntityWithLocalMaterialNumber(localMaterialNumber);
        if (edmMaterialGlobalV1Entity == null) {
            return resultObjectList;
        }
        String sourceSystem = edmMaterialGlobalV1Entity.getSourceSystem();
        if ("".equals(sourceSystem)){
            return resultObjectList;
        }
//        EDMSourceSystemV1Entity edmSourceSystemV1Entity = systemV1Dao.getEntityWithSourceSystem(sourceSystem);
//        if (edmSourceSystemV1Entity == null) {
//            return resultObjectList;
//        }
        String localPlant = materialPlantV1Entity.getLocalPlant();
        if ("".equals(localPlant)){
            return resultObjectList;
        }
        PlanCnsMaterialPlanStatusEntity planStatusEntity = cnsMaterialPlanStatusDao.getEntityWithLocalMaterialNumberAndlLocalPlant(localMaterialNumber, localPlant);
        if (planStatusEntity == null) {
            return resultObjectList;
        }
        List<OMPGdmProductLocationBo> boList = getFieldWithJ1(edmMaterialGlobalV1Entity, planStatusEntity);
        if (boList == null||boList.size()==0){
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


        //rules J2
        EDMMatPlantFiV1Entity matPlantFiV1Entity = getFieldWithJ2(materialPlantV1Entity);
        if (matPlantFiV1Entity == null) {
            return resultObjectList;
        }
        for (OMPGdmProductLocationBo bo : boList) {
            bo.setCost(matPlantFiV1Entity.getLocalStandardPrice());
            bo.setProductValue(matPlantFiV1Entity.getLocalStandardPrice());
            bo.setStprs(matPlantFiV1Entity.getLocalStandardPrice());
            bo.setVerpr(matPlantFiV1Entity.getLocalMovingAverage());
            bo.setVprsv(matPlantFiV1Entity.getLocalPriceControlIndicator());
            bo.setPeinh(matPlantFiV1Entity.getLocalPriceUnit());

            //rules C1
            bo.setLocationId(materialPlantV1Entity.getSourceSystem() + "_" + materialPlantV1Entity.getLocalPlant());
            //rules D1
            bo.setActive(IConstant.VALUE.YES);
            //rules T1
            if (IConstant.VALUE.X.equals(planStatusEntity.getDpRelevant())) {
                bo.setActiveFCTERP(IConstant.VALUE.YES);
            }
            //rules T2
            if (IConstant.VALUE.X.equals(planStatusEntity.getSpRelevant()) || IConstant.VALUE.X.equals(planStatusEntity.getNoPlanRelevant())) {
                bo.setActiveOPRERP(IConstant.VALUE.YES);
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

            //rules E4
            PlanCnsProdLocAttribEntity attribEntity = getFieldWithE4(materialPlantV1Entity.getLocalMaterialNumber(), materialPlantV1Entity.getLocalPlant());
            if (attribEntity==null){
                return new ArrayList<ResultObject>();
            }
            if (attribEntity != null) {
                bo.setMinmrsl(attribEntity.getMinMinShelfLife());
                bo.setSupplyGroup(attribEntity.getSupplyGroup());
            }

            //rules E5
            if (attribEntity.getMinShelfLife() == null || "".equals(attribEntity.getMinShelfLife())) {
                bo.setMinRemainingShelfLife(edmMaterialGlobalV1Entity.getMinRemShelfLife());
            } else {
                bo.setMinRemainingShelfLife(attribEntity.getMinShelfLife());
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
            bo.setLabel(edmMaterialGlobalV1Entity.getLocalRefDescription());
            bo.setTotalShelfLife(edmMaterialGlobalV1Entity.getTotalShelfLife());
            bo.setBasmg (materialPlantV1Entity.getLocalBaseQuantity());
            bo.setBstfe (materialPlantV1Entity.getLocalFixedLotSize());
            bo.setBstma (materialPlantV1Entity.getLocalMaximumLotSize());
            bo.setBstmi (materialPlantV1Entity.getLocalMinimumLotSize());
            bo.setBstrf (materialPlantV1Entity.getLocalRoundingValueForPoq());
            bo.setDismm (materialPlantV1Entity.getLocalMrpType());
            bo.setDispo (materialPlantV1Entity.getLocalMRPController());
            bo.setDzeit (materialPlantV1Entity.getLocalInHouseProcessingTime());
            bo.setEisbe (materialPlantV1Entity.getLocalSafetyStock());
            bo.setEislo (materialPlantV1Entity.getLocalMinimumSafetyStock());
            bo.setFevor (materialPlantV1Entity.getLocalProductionSupervisor());
            bo.setFixedHorizon (materialPlantV1Entity.getLocalPlanningTimeFence());
            bo.setFrtme (materialPlantV1Entity.getLocalProductionUnit());
            bo.setInsmk (materialPlantV1Entity.getLocalPostToInspectionStock());
            bo.setKausf (materialPlantV1Entity.getLocalComponentScrapInPercent());
            bo.setKzkri (materialPlantV1Entity.getLocalCriticalPart());
            bo.setLeadTime (materialPlantV1Entity.getLocalPlannedDeliveryTimeInDays());
            bo.setMabst (materialPlantV1Entity.getLocalMaximumStockLevel());
            bo.setMmsta (materialPlantV1Entity.getLocalPlantStatus());
            bo.setMtvfp (materialPlantV1Entity.getLocalCheckingGroupForAvailabilityCheck());
            bo.setPlifz (materialPlantV1Entity.getLocalPlannedDeliveryTimeInDays());
            bo.setReplenishmentLotSize (materialPlantV1Entity.getLocalInstalledReplenishmentLotSize());
            bo.setSbdkz (materialPlantV1Entity.getLocalDependentRequirements());
            bo.setShflg (materialPlantV1Entity.getLocalSafetyTimeIndicator());
            bo.setShzet (materialPlantV1Entity.getLocalSafetyTime());
            bo.setVint1 (materialPlantV1Entity.getLocalConsumptionPeriodBackward());
            bo.setVint2 (materialPlantV1Entity.getLocalConsumptionPeriodForward());
            bo.setWebaz (materialPlantV1Entity.getLocalGoodsReceiptProcessingTimeInDays());
            bo.setXchpf (materialPlantV1Entity.getLocalBatchManagementRequirementIndicator());


            ResultObject resultObject = new ResultObject();
            resultObject.setBaseBo(bo);
            resultObjectList.add(resultObject);
        }
        return resultObjectList;
    }


    //rules J1
    public List<OMPGdmProductLocationBo> getFieldWithJ1( EDMMaterialGlobalV1Entity edmMaterialGlobalV1Entity, PlanCnsMaterialPlanStatusEntity planStatusEntity) {
        List<OMPGdmProductLocationBo> boList = new ArrayList<OMPGdmProductLocationBo>();
        if (edmMaterialGlobalV1Entity == null) {
            return new ArrayList<OMPGdmProductLocationBo>();
        }

        if (IConstant.VALUE.X.equals(planStatusEntity.getSpRelevant()) || IConstant.VALUE.X.equals(planStatusEntity.getNoPlanRelevant())) {
            if (edmMaterialGlobalV1Entity.getPrimaryPlanningCode() == null || "".equals(edmMaterialGlobalV1Entity.getPrimaryPlanningCode())) {
                return new ArrayList<OMPGdmProductLocationBo>();
            }
            OMPGdmProductLocationBo ompGdmProductLocationBo = new OMPGdmProductLocationBo();
            ompGdmProductLocationBo.setProductId(edmMaterialGlobalV1Entity.getPrimaryPlanningCode());
            boList.add(ompGdmProductLocationBo);

        }

        if (IConstant.VALUE.X.equals(planStatusEntity.getDpRelevant())) {
            if (edmMaterialGlobalV1Entity.getLocalDpParentCode() == null || "".equals(edmMaterialGlobalV1Entity.getLocalDpParentCode())) {
                return new ArrayList<OMPGdmProductLocationBo>();
            }
            OMPGdmProductLocationBo ompGdmProductLocationBo = new OMPGdmProductLocationBo();
            ompGdmProductLocationBo.setProductId(edmMaterialGlobalV1Entity.getSourceSystem() + "_" + edmMaterialGlobalV1Entity.getLocalDpParentCode());
            boList.add(ompGdmProductLocationBo);
        }
        return boList;
    }


    /**
     * J2
     *
     * @param materialPlantV1Entity
     * @return
     */
    public EDMMatPlantFiV1Entity getFieldWithJ2(EDMMaterialPlantV1Entity materialPlantV1Entity) {
        EDMMatPlantFiV1Entity edmMaterialPlantFinV1Entity = matPlantFinV1Dao.getEntityWithLocalMaterialNumberAndLocalPlant(materialPlantV1Entity.getLocalMaterialNumber(), materialPlantV1Entity.getLocalPlant());
        if (edmMaterialPlantFinV1Entity == null) {
            return null;
        }
        EDMPlantV1Entity edmPlantV1Entity = plantV1Dao.getEntityWithLocalPlant(materialPlantV1Entity.getLocalPlant());
        if (edmPlantV1Entity==null){
            return null;
        }
        if (IConstant.VALUE.V.equals(edmMaterialPlantFinV1Entity.getPriceControl())) {
            if (!IConstant.VALUE.USD.equals(edmPlantV1Entity.getLocalCurrency())) {
                List<PlanConsTimeDepXchangeEntity> entities = planConsTimeDepXchangeDao.getEntityListWithUnitId(edmPlantV1Entity.getLocalCurrency());
                if (entities==null||entities.size()==0){
                    return null;
                }
                for (PlanConsTimeDepXchangeEntity entity : entities) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                    try {
                        Date end = format.parse(entity.getEndEff());
                        Date start = format.parse(entity.getStartEff());
                        Date date = new Date();
                        if (date.compareTo(end) <= 0 && date.compareTo(start) >= 0) {
                            if (edmMaterialPlantFinV1Entity.getLocalMvp()!=null||"".equals(edmMaterialPlantFinV1Entity.getLocalMvp())){
                                edmMaterialPlantFinV1Entity.setLocalStandardPrice(new BigDecimal(edmMaterialPlantFinV1Entity.getLocalMvp()).multiply(new BigDecimal(entity.getExchangeRate())).toString());
                            }else{
                                edmMaterialPlantFinV1Entity.setLocalStandardPrice("0");
                            }
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        if (IConstant.VALUE.S.equals(edmMaterialPlantFinV1Entity.getPriceControl())) {
            if (!IConstant.VALUE.USD.equals(edmPlantV1Entity.getLocalCurrency())) {
                List<PlanConsTimeDepXchangeEntity> entities = planConsTimeDepXchangeDao.getEntityListWithUnitId(edmPlantV1Entity.getLocalCurrency());
                if (entities==null||entities.size()==0){
                    return null;
                }
                for (PlanConsTimeDepXchangeEntity entity : entities) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                    try {
                        Date end = format.parse(entity.getEndEff());
                        Date start = format.parse(entity.getStartEff());
                        Date date = new Date();
                        if (date.compareTo(end) <= 0 && date.compareTo(start) >= 0) {
                            if (edmMaterialPlantFinV1Entity.getLocalStandardPrice()!=null||"".equals(edmMaterialPlantFinV1Entity.getLocalStandardPrice())){
                                edmMaterialPlantFinV1Entity.setLocalStandardPrice(new BigDecimal(edmMaterialPlantFinV1Entity.getLocalStandardPrice()).multiply(new BigDecimal(entity.getExchangeRate())).toString());
                            }else{
                                edmMaterialPlantFinV1Entity.setLocalStandardPrice("0");
                            }
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return edmMaterialPlantFinV1Entity;
    }


    /**
     * rules E1
     */
    public PlanCnsProcTypeEntity getFieldWithE1(String localProcurementType, String sourceSystem) {
       if ("".equals(localProcurementType)||"".equals(sourceSystem)){
           return null;
       }
        PlanCnsProcTypeEntity entity = cnsProcTypeDao.getEntityWithLocalProcurementTypeAndsourceSystem(localProcurementType, sourceSystem);
        return entity;
    }

    /**
     * rules E2
     */
    private PlanCnsAbcIndEntity getFieldWithE2(String localAbcIndicator, String sourceSystem) {
        if ("".equals(localAbcIndicator)||"".equals(sourceSystem)){
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
    private PlanCnsProdLocAttribEntity getFieldWithE4(String localMaterialNumber, String localPlant) {
        if ("".equals(localMaterialNumber)||"".equals(localPlant)){
            return null;
        }
        PlanCnsProdLocAttribEntity attribEntity = cnsProdLocAttribDao.getEntityWithLocalMaterialNumberAndLocalPlant(localMaterialNumber, localPlant);
        return attribEntity;
    }

    /**
     * rules E6
     *
     * @param localSpecialProcurementType
     * @param sourceSystem
     */
    private PlanCnsSplProcTypEntity getFieldWithE6(String localSpecialProcurementType, String sourceSystem) {
        if ("".equals(localSpecialProcurementType)||"".equals(sourceSystem)){
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
        if ("".equals(localPlanningStrategyGroup)||"".equals(sourceSystem)){
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
        if ("".equals(localConsumptionMode)||"".equals(sourceSystem)){
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
        if ("".equals(localLotSize)||"".equals(sourceSystem)){
            return null;
        }
        PlanCnsLotSizeKeyTransEntity planCnsLotSizeKeyTransEntity = cnsLotSizeKeyTransDao.getEntityWithLocalLotSizeKeyAndSourceSystem(localLotSize, sourceSystem);
        return planCnsLotSizeKeyTransEntity;
    }
}
