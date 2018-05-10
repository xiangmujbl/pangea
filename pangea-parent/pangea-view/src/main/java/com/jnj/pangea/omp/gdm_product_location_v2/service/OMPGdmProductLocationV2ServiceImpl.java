package com.jnj.pangea.omp.gdm_product_location_v2.service;

import com.jnj.adf.grid.utils.DateUtil;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.*;
import com.jnj.pangea.common.dao.impl.plan.*;
import com.jnj.pangea.common.entity.edm.*;
import com.jnj.pangea.common.entity.plan.*;
import com.jnj.pangea.omp.gdm_product_location_v2.bo.OMPGdmProductLocationV2Bo;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: qzhan112
 * @date: 2018/5/2
 */
public class OMPGdmProductLocationV2ServiceImpl {

    private static OMPGdmProductLocationV2ServiceImpl instance;

    public static OMPGdmProductLocationV2ServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmProductLocationV2ServiceImpl();
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
    private EDMSourceListV1DaoImpl sourceListDao = EDMSourceListV1DaoImpl.getInstance();
    private PlanSplPlnLocDaoImpl splPlnLocDao = PlanSplPlnLocDaoImpl.getInstance();


    public List<ResultObject> buildView(String key, Object o, Object o2) {

        List<ResultObject> resultObjectList = new ArrayList<ResultObject>();
        EDMMaterialPlantV1Entity materialPlantV1Entity = (EDMMaterialPlantV1Entity) o;
        List<OMPGdmProductLocationV2Bo> boList = null;
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
//        String sourceSystem = edmMaterialGlobalV1Entity.getSourceSystem();
//        if ("".equals(sourceSystem)){
//            ResultObject resultObject = new ResultObject();
//            OMPGdmProductLocationV2Bo OMPGdmProductLocationV2Bo = new OMPGdmProductLocationV2Bo();
//            resultObject.setBaseBo(OMPGdmProductLocationV2Bo);
//            resultObjectList.add(resultObject);
//            return resultObjectList;
//        }
//        EDMSourceSystemV1Entity edmSourceSystemV1Entity = systemV1Dao.getEntityWithSourceSystem(sourceSystem);
//        if (edmSourceSystemV1Entity == null) {
//            return resultObjectList;
//        }
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

        //rules J2
        if (materialPlantV1Entity != null) {
            matPlantFiV1Entity = getFieldWithJ2(materialPlantV1Entity);
        }
        for (OMPGdmProductLocationV2Bo bo : boList) {
            if (matPlantFiV1Entity != null) {
                bo.setCost(matPlantFiV1Entity.getLocalStandardPrice());
                bo.setProductValue(matPlantFiV1Entity.getLocalStandardPrice());
                bo.setStprs(matPlantFiV1Entity.getLocalStandardPrice());
                bo.setVerpr(matPlantFiV1Entity.getLocalMovingAverage());
                bo.setVprsv(matPlantFiV1Entity.getLocalPriceControlIndicator());
                bo.setPeinh(matPlantFiV1Entity.getLocalPriceUnit());

            }
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
            //rules E4
            PlanCnsProdLocAttribEntity attribEntity = getFieldWithE4(materialPlantV1Entity.getLocalMaterialNumber(), materialPlantV1Entity.getLocalPlant());
            if (attribEntity != null) {
                bo.setMinmrsl(attribEntity.getMinMinShelfLife());
                bo.setSupplyGroup(attribEntity.getSupplyGroup());

                //rules E5
                if (attribEntity.getMinShelfLife() == null || "".equals(attribEntity.getMinShelfLife())) {
                    bo.setMinRemainingShelfLife(edmMaterialGlobalV1Entity.getMinRemShelfLife());
                } else {
                    bo.setMinRemainingShelfLife(attribEntity.getMinShelfLife());
                }

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
                bo.setProductTypeId(edmMaterialGlobalV1Entity.getLocalMaterialType());
            }

            bo.setBasmg(materialPlantV1Entity.getLocalBaseQuantity());
            bo.setBstfe(materialPlantV1Entity.getLocalFixedLotSize());
            bo.setBstma(materialPlantV1Entity.getLocalMaximumLotSize());
            bo.setBstmi(materialPlantV1Entity.getLocalMinimumLotSize());
            bo.setBstrf(materialPlantV1Entity.getLocalRoundingValueForPoq());
            bo.setDismm(materialPlantV1Entity.getLocalMrpType());
            bo.setDispo(materialPlantV1Entity.getLocalMrpController());
            bo.setDzeit(materialPlantV1Entity.getLocalInHouseProcessingTime());
            bo.setEisbe(materialPlantV1Entity.getLocalSafetyStock());
            bo.setEislo(materialPlantV1Entity.getLocalMinimumSafetyStock());
            bo.setFevor(materialPlantV1Entity.getLocalProductionSupervisor());
            bo.setFixedHorizon(materialPlantV1Entity.getLocalPlanningTimeFence());
            bo.setFrtme(materialPlantV1Entity.getLocalProductionUnit());
            bo.setInsmk(materialPlantV1Entity.getLocalPostToInspectionStock());
            bo.setKausf(materialPlantV1Entity.getLocalComponentScrapInPercent());
            bo.setKzkri(materialPlantV1Entity.getLocalCriticalPart());
            bo.setLeadTime(materialPlantV1Entity.getLocalPlannedDeliveryTimeInDays());
            bo.setMabst(materialPlantV1Entity.getLocalMaximumStockLevel());
            bo.setMmsta(materialPlantV1Entity.getLocalPlantStatus());
            bo.setMtvfp(materialPlantV1Entity.getLocalCheckingGroupForAvailabilityCheck());
            bo.setPlifz(materialPlantV1Entity.getLocalPlannedDeliveryTimeInDays());
            bo.setReplenishmentLotSize(materialPlantV1Entity.getLocalInstalledReplenishmentLotSize());
            bo.setSbdkz(materialPlantV1Entity.getLocalDependentRequirements());
            bo.setShflg(materialPlantV1Entity.getLocalSafetyTimeIndicator());
            bo.setShzet(materialPlantV1Entity.getLocalSafetyTime());
            bo.setVint1(materialPlantV1Entity.getLocalConsumptionPeriodBackward());
            bo.setVint2(materialPlantV1Entity.getLocalConsumptionPeriodForward());
            bo.setWebaz(materialPlantV1Entity.getLocalGoodsReceiptProcessingTimeInDays());
            bo.setXchpf(materialPlantV1Entity.getLocalBatchManagementRequirementIndicator());


            ResultObject resultObject = new ResultObject();
            resultObject.setBaseBo(bo);
            resultObjectList.add(resultObject);
        }

        return resultObjectList;
    }

    //rules J1
    public List<OMPGdmProductLocationV2Bo> getFieldWithJ1(EDMMaterialGlobalV1Entity edmMaterialGlobalV1Entity, PlanSplPlnLocEntity planSplPlnLocEntity, EDMMaterialPlantV1Entity materialPlantV1Entity, PlanCnsMaterialPlanStatusEntity planStatusEntity) {
        List<OMPGdmProductLocationV2Bo> boList = new ArrayList<OMPGdmProductLocationV2Bo>();
        OMPGdmProductLocationV2Bo ompGdmProductLocationV2Bo = new OMPGdmProductLocationV2Bo();
        if (edmMaterialGlobalV1Entity == null) {
            return new ArrayList<OMPGdmProductLocationV2Bo>();
        }

        if (IConstant.VALUE.X.equals(planStatusEntity.getSpRelevant()) || IConstant.VALUE.X.equals(planStatusEntity.getNoPlanRelevant())) {
            //rules T2
            ompGdmProductLocationV2Bo.setActiveOPRERP(IConstant.VALUE.YES);
            if (StringUtils.isEmpty(edmMaterialGlobalV1Entity.getPrimaryPlanningCode()) && StringUtils.isEmpty(edmMaterialGlobalV1Entity.getLocalMaterialNumber())) {
                return null;
            }
            if (StringUtils.isBlank(edmMaterialGlobalV1Entity.getPrimaryPlanningCode())) {
                ompGdmProductLocationV2Bo.setProductId(edmMaterialGlobalV1Entity.getSourceSystem() + IConstant.VALUE.UNDERLINE + edmMaterialGlobalV1Entity.getMaterialNumber());
            } else {
                OMPGdmProductLocationV2Bo OMPGdmProductLocationV2Bo = new OMPGdmProductLocationV2Bo();
                OMPGdmProductLocationV2Bo.setProductId(edmMaterialGlobalV1Entity.getSourceSystem() + "_" + edmMaterialGlobalV1Entity.getPrimaryPlanningCode());
            }
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
                            //rules C1
                            ompGdmProductLocationV2Bo.setLocationId(materialPlantV1Entity.getSourceSystem() + "_" + materialPlantV1Entity.getLocalPlant());
                        }
                    }
                    List<PlanSplPlnLocEntity> splPlnLocEntries = splPlnLocDao.getEntityListWithConditions(sourceListV1Entity.getSourceSystem(), sourceListV1Entity.getLocalVendorAccountNumber());
                    //LogUtil.getCoreLog().info("splPlnLocEntries{}",splPlnLocEntries);
                    if (splPlnLocEntries.size() > 0) {
                        //rules C1
                        ompGdmProductLocationV2Bo.setLocationId(materialPlantV1Entity.getSourceSystem() + "_" + planSplPlnLocEntity.getVendorOrCustomer() + "_" + planSplPlnLocEntity.getLocalNumber());
                    }
                }
            }
            boList.add(ompGdmProductLocationV2Bo);
            return boList;

        } else {
            return null;
        }

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
        if (edmPlantV1Entity == null) {
            return null;
        }
        if (IConstant.VALUE.V.equals(edmMaterialPlantFinV1Entity.getPriceControl())) {
            if (!IConstant.VALUE.USD.equals(edmPlantV1Entity.getLocalCurrency())) {
                String localCurrency = edmPlantV1Entity.getLocalCurrency();
                if (StringUtils.isEmpty(localCurrency)) {
                    return null;
                }
                List<PlanConsTimeDepXchangeEntity> entities = planConsTimeDepXchangeDao.getEntityListWithUnitId(localCurrency);
                if (entities == null || entities.size() == 0) {
                    return null;
                }
                for (PlanConsTimeDepXchangeEntity entity : entities) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                    try {
                        Date end = format.parse(entity.getEffectiveEndDate());
                        Date start = format.parse(entity.getEffectiveStartDate());
                        Date date = new Date();
                        if (date.compareTo(end) <= 0 && date.compareTo(start) >= 0) {
                            if (edmMaterialPlantFinV1Entity.getLocalMvp() != null || "".equals(edmMaterialPlantFinV1Entity.getLocalMvp())) {
                                edmMaterialPlantFinV1Entity.setLocalStandardPrice(new BigDecimal(edmMaterialPlantFinV1Entity.getLocalMvp()).multiply(new BigDecimal(entity.getExchangeRate())).toString());
                            } else {
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
                String localCurrency = edmPlantV1Entity.getLocalCurrency();
                if (StringUtils.isEmpty(localCurrency)) {
                    return null;
                }
                List<PlanConsTimeDepXchangeEntity> entities = planConsTimeDepXchangeDao.getEntityListWithUnitId(localCurrency);
                if (entities == null || entities.size() == 0) {
                    return null;
                }
                for (PlanConsTimeDepXchangeEntity entity : entities) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                    try {
                        Date end = format.parse(entity.getEffectiveEndDate());
                        Date start = format.parse(entity.getEffectiveStartDate());
                        Date date = new Date();
                        if (date.compareTo(end) <= 0 && date.compareTo(start) >= 0) {
                            if (edmMaterialPlantFinV1Entity.getLocalStandardPrice() != null || "".equals(edmMaterialPlantFinV1Entity.getLocalStandardPrice())) {
                                edmMaterialPlantFinV1Entity.setLocalStandardPrice(new BigDecimal(edmMaterialPlantFinV1Entity.getLocalStandardPrice()).multiply(new BigDecimal(entity.getExchangeRate())).toString());
                            } else {
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
    private PlanCnsProdLocAttribEntity getFieldWithE4(String localMaterialNumber, String localPlant) {
        if ("".equals(localMaterialNumber) || "".equals(localPlant)) {
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
