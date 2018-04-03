package com.jnj.pangea.omp.gdm_product_location.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialGlobalV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialPlantFinV1DaoImpl;
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
    private EDMMaterialPlantFinV1DaoImpl materialPlantFinV1Dao = EDMMaterialPlantFinV1DaoImpl.getInstance();
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
        EDMMaterialGlobalV1Entity edmMaterialGlobalV1Entity = materialGlobalV1Dao.getEntityWithLocalMaterialNumber(materialPlantV1Entity.getLocalMaterialNumber());
        if (edmMaterialGlobalV1Entity == null) {
            return resultObjectList;
        }
        EDMSourceSystemV1Entity edmSourceSystemV1Entity = systemV1Dao.getEntityWithLocalSourceSystem(edmMaterialGlobalV1Entity.getSourceSystem());
        if (edmSourceSystemV1Entity == null) {
            return resultObjectList;
        }
        PlanCnsMaterialPlanStatusEntity planStatusEntity = cnsMaterialPlanStatusDao.getEntityWithLocalMaterialNumberAndlLocalPlant(materialPlantV1Entity.getLocalMaterialNumber(), materialPlantV1Entity.getLocalPlant());
        if (planStatusEntity == null) {
            return resultObjectList;
        }
        List<OMPGdmProductLocationBo> boList = getFieldWithJ1(materialPlantV1Entity, edmMaterialGlobalV1Entity, planStatusEntity, edmSourceSystemV1Entity);
        if (boList == null) {
            FailData failData = new FailData();
            failData.setErrorCode("J1");
            failData.setFunctionalArea("SP");
            failData.setInterfaceID("OMPGdmProductLocation");
            failData.setSourceSystem("omp");
            failData.setKey1(edmMaterialGlobalV1Entity.getSourceSystem());
            failData.setKey2(edmMaterialGlobalV1Entity.getLocalMaterialNumber());
            failData.setKey3("");
            failData.setKey4("");
            failData.setKey5("");
            ResultObject resultObject = new ResultObject();
            resultObject.setFailData(failData);
            resultObjectList.add(resultObject);
            return resultObjectList;
        }


        //rules J2
        EDMMaterialPlantFinV1Entity edmMaterialPlantFinV1Entity = getFieldWithJ2(materialPlantV1Entity);
        if (edmMaterialPlantFinV1Entity == null) {
            return resultObjectList;
        }
        for (OMPGdmProductLocationBo bo : boList) {
            bo.setStprs(edmMaterialPlantFinV1Entity.getLocalStandardPrice());
            bo.setVerpr(edmMaterialPlantFinV1Entity.getLocalMovingAverage());
            bo.setVprsv(edmMaterialPlantFinV1Entity.getPriceControl());
            //rules C1
            bo.setLocationId(materialPlantV1Entity.getSourceSystem() + "_" + materialPlantV1Entity.getLocalPlant());
            //rules D1
            bo.setActive(IConstant.VALUE.YES);
            //rules T1
            if (IConstant.VALUE.X.equals(planStatusEntity.getDpRelevant())) {
                bo.setActiveFCTERP(planStatusEntity.getDpRelevant());
            }
            //rules T2
            if (IConstant.VALUE.X.equals(planStatusEntity.getSpRelevant()) || IConstant.VALUE.X.equals(planStatusEntity.getNoPlanRelevant())) {
                bo.setActiveOPRERP(planStatusEntity.getSpRelevant());
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
            if (attribEntity != null) {
                bo.setMinmrsl(attribEntity.getMinMinshelfLife());
                bo.setSupplyGroup(attribEntity.getSupplyGroup());
            }

            //rules E5
            if (attribEntity.getMinShelfLife() == null || "".equals(attribEntity.getMinShelfLife())) {
                bo.setMinremainingshelflife(edmMaterialGlobalV1Entity.getMinRemShelfLife());
            } else {
                bo.setMinremainingshelflife(attribEntity.getMinShelfLife());
            }

            //rules E6
            LogUtil.getLogger().info("11111111111"+materialPlantV1Entity.getLocalSpecialProcurementType());
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
            bo.setLABEL(edmMaterialGlobalV1Entity.getLocalRefDescription());
            bo.setProductId(edmMaterialGlobalV1Entity.getLocalMaterialType());
            bo.setTotalshelflife(edmMaterialGlobalV1Entity.getTotalShelfLife());
            bo.setBasmg (materialPlantV1Entity.getLocalBaseQuantity());
            bo.setBstfe (materialPlantV1Entity.getLocalFixedlotsize());
            bo.setBstma (materialPlantV1Entity.getLocalMaximumLotSize());
            bo.setBstmi (materialPlantV1Entity.getLocalMinimumLotSize());
            bo.setBstrf (materialPlantV1Entity.getLocalRoundingvalueforpurchaseorderquantity());
            bo.setDismm (materialPlantV1Entity.getLocalMrpType());
            bo.setDispo (materialPlantV1Entity.getLocalMRPController());
            bo.setDzeit (materialPlantV1Entity.getLocalInHouseProcessingTime());
            bo.setEisbe (materialPlantV1Entity.getLocalSafetyStock());
            bo.setEislo (materialPlantV1Entity.getLocalMinimumSafetyStock());
            bo.setFevor (materialPlantV1Entity.getLocalProductionSupervisor());
            bo.setFixedhorizon (materialPlantV1Entity.getLocalPlanningTimeFence());
            bo.setFrtme (materialPlantV1Entity.getLocalProductionUnit());
            bo.setInsmk (materialPlantV1Entity.getLocalPosttoInspectionStock());
            bo.setKausf (materialPlantV1Entity.getLocalComponentscrapinpercent());
            bo.setKzkri (materialPlantV1Entity.getLocalCriticalpart());
            bo.setLeadTime (materialPlantV1Entity.getLocalPlannedDeliveryTimeinDays());
            bo.setMabst (materialPlantV1Entity.getLocalMaximumstocklevel());
            bo.setMmsta (materialPlantV1Entity.getLocalPlantStatus());
            bo.setMtvfp (materialPlantV1Entity.getLocalCheckingGroupforAvailabilityCheck());
            bo.setPlifz (materialPlantV1Entity.getLocalPlannedDeliveryTimeinDays());
            bo.setReplenishmentLotsize (materialPlantV1Entity.getLocalInstalledReplenishmentLotSize());
            bo.setSbdkz (materialPlantV1Entity.getLocalDependentrequirements());
            bo.setShflg (materialPlantV1Entity.getLocalSafetytimeindicator());
            bo.setShzet (materialPlantV1Entity.getLocalSafetytime());
            bo.setVint1 (materialPlantV1Entity.getLocalConsumptionperiodBackward());
            bo.setVint2 (materialPlantV1Entity.getLocalConsumptionperiodForward());
            bo.setWebaz (materialPlantV1Entity.getLocalGoodsReceiptProcessingTimeinDays());
            bo.setXchpf (materialPlantV1Entity.getLocalBatchmanagementrequirmentindicator());


            ResultObject resultObject = new ResultObject();
            resultObject.setBaseBo(bo);
            resultObjectList.add(resultObject);
        }
        return resultObjectList;
    }


    //rules J1
    public List<OMPGdmProductLocationBo> getFieldWithJ1(EDMMaterialPlantV1Entity materialPlantV1Entity, EDMMaterialGlobalV1Entity edmMaterialGlobalV1Entity, PlanCnsMaterialPlanStatusEntity planStatusEntity, EDMSourceSystemV1Entity edmSourceSystemV1Entity) {
        List<OMPGdmProductLocationBo> boList = new ArrayList<OMPGdmProductLocationBo>();
        if (edmMaterialGlobalV1Entity == null) {
            return boList;
        }

        if (IConstant.VALUE.X.equals(planStatusEntity.getSpRelevant()) || IConstant.VALUE.X.equals(planStatusEntity.getNoPlanRelevant())) {
            if (edmMaterialGlobalV1Entity.getPrimaryPlanningCode() == null || "".equals(edmMaterialGlobalV1Entity.getPrimaryPlanningCode())) {
                return null;
            }
            OMPGdmProductLocationBo ompGdmProductLocationBo = new OMPGdmProductLocationBo();
            ompGdmProductLocationBo.setProductId(edmMaterialGlobalV1Entity.getPrimaryPlanningCode());
            boList.add(ompGdmProductLocationBo);

        }

        if (IConstant.VALUE.X.equals(planStatusEntity.getDpRelevant())) {
            if (edmMaterialGlobalV1Entity.getLocalDpParentCode() == null || "".equals(edmMaterialGlobalV1Entity.getLocalDpParentCode())) {
                return null;
            }
            OMPGdmProductLocationBo ompGdmProductLocationBo = new OMPGdmProductLocationBo();
            ompGdmProductLocationBo.setProductId(edmSourceSystemV1Entity.getSourceSystem() + "_" + edmMaterialGlobalV1Entity.getLocalDpParentCode());
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
    public EDMMaterialPlantFinV1Entity getFieldWithJ2(EDMMaterialPlantV1Entity materialPlantV1Entity) {
        EDMMaterialPlantFinV1Entity edmMaterialPlantFinV1Entity = materialPlantFinV1Dao.getEntityWithLocalMaterialNumberAndLocalPlant(materialPlantV1Entity.getLocalMaterialNumber(), materialPlantV1Entity.getLocalPlant());
        if (edmMaterialPlantFinV1Entity == null) {
            return null;
        }
        EDMPlantV1Entity edmPlantV1Entity = plantV1Dao.getEntityWithLocalPlant(materialPlantV1Entity.getLocalPlant());
        if (IConstant.VALUE.V.equals(edmMaterialPlantFinV1Entity.getPriceControl())) {
            if (!IConstant.VALUE.USD.equals(edmPlantV1Entity.getLocalCurrency())) {
                List<PlanConsTimeDepXchangeEntity> entities = planConsTimeDepXchangeDao.getEntityListWithUnitId(edmPlantV1Entity.getLocalCurrency());
                for (PlanConsTimeDepXchangeEntity entity : entities) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                    try {
                        Date end = format.parse(entity.getEndEff());
                        Date start = format.parse(entity.getStartEff());
                        Date date = new Date();
                        if (date.compareTo(end) <= 0 && date.compareTo(start) >= 0) {
                            BigDecimal bd1 = new BigDecimal(entity.getFactor());
                            BigDecimal bd2 = new BigDecimal(entity.getPreference());
                            BigDecimal proportion = bd1.divide(bd2);
                            edmMaterialPlantFinV1Entity.setLocalMovingAverage(new BigDecimal(edmMaterialPlantFinV1Entity.getLocalMvp()).multiply(proportion).toString());
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
                for (PlanConsTimeDepXchangeEntity entity : entities) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                    try {
                        Date end = format.parse(entity.getEndEff());
                        Date start = format.parse(entity.getStartEff());
                        Date date = new Date();
                        if (date.compareTo(end) <= 0 && date.compareTo(start) >= 0) {
                            BigDecimal bd1 = new BigDecimal(entity.getFactor());
                            BigDecimal bd2 = new BigDecimal(entity.getPreference());
                            BigDecimal proportion = bd1.divide(bd2);
                            edmMaterialPlantFinV1Entity.setLocalStandardPrice(new BigDecimal(edmMaterialPlantFinV1Entity.getPriceControl()).multiply(proportion).toString());
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
        PlanCnsProcTypeEntity entity = cnsProcTypeDao.getEntityWithLocalProcurementTypeAndsourceSystem(localProcurementType, sourceSystem);
        return entity;
    }

    /**
     * rules E2
     */
    private PlanCnsAbcIndEntity getFieldWithE2(String localAbcIndicator, String sourceSystem) {
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
        PlanCnsLotSizeKeyTransEntity planCnsLotSizeKeyTransEntity = cnsLotSizeKeyTransDao.getEntityWithLocalLotSizeKeyAndSourceSystem(localLotSize, sourceSystem);
        return planCnsLotSizeKeyTransEntity;
    }
}
