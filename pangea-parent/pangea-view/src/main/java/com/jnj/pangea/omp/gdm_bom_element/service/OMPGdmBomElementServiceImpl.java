package com.jnj.pangea.omp.gdm_bom_element.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.*;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsMaterialPlanStatusDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanParameterDaoImpl;
import com.jnj.pangea.common.entity.edm.*;
import com.jnj.pangea.common.entity.plan.PlanCnsMaterialPlanStatusEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanParameterEntity;
import com.jnj.pangea.common.service.ICommonListService;
import com.jnj.pangea.omp.gdm_bom_element.bo.OMPGdmBomElementBo;
import com.jnj.pangea.util.DateUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OMPGdmBomElementServiceImpl implements ICommonListService {

    private static OMPGdmBomElementServiceImpl instance;

    public static OMPGdmBomElementServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmBomElementServiceImpl();
        }
        return instance;
    }

    private EDMMaterialPlantV1DaoImpl materialPlantV1Dao = EDMMaterialPlantV1DaoImpl.getInstance();
    private EDMBomItemDaoImpl bomItemDao = EDMBomItemDaoImpl.getInstance();
    private EDMBomHdrDaoImpl bomHdrDao = EDMBomHdrDaoImpl.getInstance();
    private PlanCnsPlanParameterDaoImpl cnsPlanParameterDao = PlanCnsPlanParameterDaoImpl.getInstance();
    private EDMMatlProdVersnDaoImpl matlProdVersnDao = EDMMatlProdVersnDaoImpl.getInstance();
    private EDMMatlMfgRtngDaoImpl matlMfgRtngDao = EDMMatlMfgRtngDaoImpl.getInstance();
    private EDMMfgRtngItmNdeDaoImpl mfgRtngItmNdeDao = EDMMfgRtngItmNdeDaoImpl.getInstance();
    private EDMMfgRtngItmDaoImpl mfgRtngItmDao = EDMMfgRtngItmDaoImpl.getInstance();
    private EDMMaterialGlobalV1DaoImpl materialGlobalV1Dao = EDMMaterialGlobalV1DaoImpl.getInstance();
    private PlanCnsMaterialPlanStatusDaoImpl materialPlanStatusDao = PlanCnsMaterialPlanStatusDaoImpl.getInstance();
    List<ResultObject> resultObjectList = new ArrayList<ResultObject>();
    List<ResultObject> resultObjectList_1 = new ArrayList<ResultObject>();

    public List<ResultObject> buildView(String key, Object o, Object o2) {
        resultObjectList.clear();
        resultObjectList_1.clear();
        resultObjectList = getCommentDate(o);
        if (resultObjectList != null && resultObjectList.size() > 0) {
            ResultObject resultObject = getProductMat(o);
            resultObjectList.add(resultObject);
        }
        resultObjectList.addAll(resultObjectList_1);
        return resultObjectList;
    }


    public List<ResultObject> getCommentDate(Object o) {
        List<ResultObject> resultObjectList = new ArrayList<ResultObject>();
        EDMMatlBomEntity matlBomEntity = (EDMMatlBomEntity) o;
        //J1
        List<EDMMatlProdVersnEntity> matlProdVersnEntityList = matlProdVersnDao.getEntityWithFourConditions(matlBomEntity.getSrcSysCd(), matlBomEntity.getPlntCd(), matlBomEntity.getMatlNum(), matlBomEntity.getAltBomNum());
        EDMBomItemEntity bomItemEntity = bomItemDao.getEntityWithConditions(matlBomEntity.getBomNum(), matlBomEntity.getSrcSysCd());
        EDMMaterialPlantV1Entity materialPlantV1Entity = materialPlantV1Dao.getPlantWithSourceSystemAndLocalPlantAndLocalMaterialNumber(matlBomEntity.getSrcSysCd(), matlBomEntity.getPlntCd(), matlBomEntity.getMatlNum());
        PlanCnsPlanParameterEntity planCnsPlanParameterEntity = cnsPlanParameterDao.getEntitiesWithConditions1(matlBomEntity.getSrcSysCd(), IConstant.VALUE.SEND_TO_OMP, matlBomEntity.getSrcSysCd());
        if (matlProdVersnEntityList != null && matlProdVersnEntityList.size() > 0) {
            for (EDMMatlProdVersnEntity matlProdVersnEntity : matlProdVersnEntityList) {
                if (matlProdVersnEntity != null && bomItemEntity != null && materialPlantV1Entity != null && planCnsPlanParameterEntity != null) {
                    List<EDMMatlMfgRtngEntity> mfgRtngEntityList = matlMfgRtngDao.getEntityWithFiveConditions(matlProdVersnEntity.getSrcSysCd(), matlProdVersnEntity.getMatlNum(), matlProdVersnEntity.getPlntCd(), matlProdVersnEntity.getRtngGrpCd(), matlProdVersnEntity.getRtngGrpCntrNum());
                    if (mfgRtngEntityList != null && mfgRtngEntityList.size() > 0) {
                        for (EDMMatlMfgRtngEntity mfgRtngEntity : mfgRtngEntityList) {
                            List<EDMMfgRtngItmNdeEntity> rtngItmNdeEntityList = mfgRtngItmNdeDao.getEntityWithConditions(mfgRtngEntity.getSrcSysCd(), mfgRtngEntity.getRtngTypCd(), mfgRtngEntity.getRntgGrpCd(), mfgRtngEntity.getRntgGrpCntrNbr());
                            if (rtngItmNdeEntityList != null && rtngItmNdeEntityList.size() > 0) {
                                for (EDMMfgRtngItmNdeEntity rtngItmNdeEntity : rtngItmNdeEntityList) {
                                    EDMMfgRtngItmEntity mfgRtngItmEntity = mfgRtngItmDao.getEntityWithConditions(rtngItmNdeEntity.getSrcSysCd(), rtngItmNdeEntity.getRtngTypCd(), rtngItmNdeEntity.getRtngNdeNum(), rtngItmNdeEntity.getRtngGrpCd());
                                    if (mfgRtngItmEntity != null) {
                                        EDMBomHdrEntity bomHdrEntity = bomHdrDao.getEntityWithConditions(matlBomEntity.getSrcSysCd(), matlBomEntity.getBomNum(), matlBomEntity.getAltBomNum(), bomItemEntity.getBomCatCd());
                                        EDMMaterialGlobalV1Entity materialGlobalV1Entity = materialGlobalV1Dao.getEntityWithMaterialNumberAndSourceSystem(bomItemEntity.getCmpntNum(), matlBomEntity.getSrcSysCd());
                                        PlanCnsMaterialPlanStatusEntity cnsMaterialPlanStatusEntity = materialPlanStatusDao.getEntityWithLocalMaterialNumberAndlLocalPlantAndSourceSystem(bomItemEntity.getCmpntNum(), matlBomEntity.getPlntCd(), matlBomEntity.getSrcSysCd());
                                        if (bomHdrEntity != null && materialGlobalV1Entity != null && cnsMaterialPlanStatusEntity != null) {
                                            ResultObject resultObject = new ResultObject();
                                            OMPGdmBomElementBo gdmBomElementBo = new OMPGdmBomElementBo();
                                            //T1
                                            String bomElementId = getBomElementId_T1(IConstant.VALUE.ZERO, matlProdVersnEntity, mfgRtngEntity, matlBomEntity, mfgRtngItmEntity, bomItemEntity, bomHdrEntity);
                                            gdmBomElementBo.setBomElementId(bomElementId);
                                            //T2
                                            gdmBomElementBo.setActive(IConstant.VALUE.YES);
                                            gdmBomElementBo.setActiveFCTERP(IConstant.VALUE.YES);
                                            gdmBomElementBo.setActiveOPRERP(IConstant.VALUE.YES);
                                            //T3
                                            gdmBomElementBo.setActiveSOPERP(IConstant.VALUE.NO);
                                            //T4
                                            gdmBomElementBo.setBatchId(IConstant.VALUE.BLANK);
                                            gdmBomElementBo.setComment(IConstant.VALUE.BLANK);
                                            gdmBomElementBo.setErpFeedbackQuantity(IConstant.VALUE.BLANK);
                                            gdmBomElementBo.setOffsetCalendarId(IConstant.VALUE.BLANK);
                                            gdmBomElementBo.setOffsetPercentage(IConstant.VALUE.BLANK);
                                            gdmBomElementBo.setOffsetPercType(IConstant.VALUE.BLANK);
                                            //T5
                                            String bomId = getBomId_T5(matlProdVersnEntity.getPrdntVrsnNum(),
                                                    planCnsPlanParameterEntity.getParameterValue(),
                                                    mfgRtngEntity.getMatlNum(), mfgRtngEntity.getPlntCd(),
                                                    matlBomEntity.getBomNum(), matlBomEntity.getAltBomNum(), matlBomEntity.getBomUsgCd(),
                                                    mfgRtngItmEntity.getOperNum());

                                            gdmBomElementBo.setBomId(bomId);
                                            //T6
                                            String bomType = getBomtype_T6(bomItemEntity.getDstrbtnKeyCd(), IConstant.VALUE.ZERO);
                                            gdmBomElementBo.setBomType(bomType);
                                            //T7
                                            String bomUsage = getBomUsage_T7(bomItemEntity.getFxQtyInd(), IConstant.VALUE.ZERO);
                                            gdmBomElementBo.setBomUsage(bomUsage);
                                            //T8
                                            String endEff = getEndEff_T8(bomItemEntity.getBomItmVldToDt(), matlProdVersnEntity.getValToDt(), IConstant.VALUE.ZERO);
                                            gdmBomElementBo.setEndEff(endEff);
                                            //T9
                                            String locationId = getLocationId_T9(matlBomEntity.getSrcSysCd(), matlBomEntity.getPlntCd());
                                            gdmBomElementBo.setLocationId(locationId);
                                            //T10
                                            String offset = getOffset_T10(bomItemEntity.getLeadTimeOffst(), IConstant.VALUE.ZERO);
                                            gdmBomElementBo.setOffset(offset);
                                            //T12
                                            String productId = getProductId_T12(planCnsPlanParameterEntity, materialGlobalV1Entity, cnsMaterialPlanStatusEntity);
                                            gdmBomElementBo.setProductId(productId);
                                            //T13
                                            String quantity = getQuantity_T13(bomItemEntity.getCmpntQty(), bomItemEntity.getCmpntScrap_Pct(), IConstant.VALUE.ZERO);
                                            gdmBomElementBo.setQuantity(quantity);
                                            //T14
                                            String srateEff = getEndEff_T14(bomItemEntity.getBomItmVldFromDt(), matlProdVersnEntity.getValFromDt(), IConstant.VALUE.ZERO);
                                            gdmBomElementBo.setStartEff(srateEff);
                                            //T11
                                            String planLevelId = getPlanLevelId_T11(bomItemEntity.getFxQtyInd(), gdmBomElementBo, IConstant.VALUE.ZERO,
                                                    planCnsPlanParameterEntity, bomItemEntity, matlProdVersnEntity,
                                                    bomHdrEntity, materialPlantV1Entity, mfgRtngItmEntity,materialGlobalV1Entity,cnsMaterialPlanStatusEntity);
                                            gdmBomElementBo.setPlanLevelId(planLevelId);
                                            resultObject.setBaseBo(gdmBomElementBo);
                                            resultObjectList.add(resultObject);
                                        }
                                    } else {
                                        return resultObjectList;
                                    }
                                }
                            } else {
                                return resultObjectList;
                            }
                        }
                    } else {
                        return resultObjectList;
                    }
                }
            }
        }
        return resultObjectList;
    }



    public ResultObject getProductMat(Object o) {
        EDMMatlBomEntity matlBomEntity = (EDMMatlBomEntity) o;
        ResultObject resultObject = new ResultObject();
        OMPGdmBomElementBo gdmBomElementBo = new OMPGdmBomElementBo();
        List<EDMMatlProdVersnEntity> matlProdVersnEntityList = matlProdVersnDao.getEntityWithFourConditions(matlBomEntity.getSrcSysCd(), matlBomEntity.getPlntCd(), matlBomEntity.getMatlNum(), matlBomEntity.getAltBomNum());
        EDMBomItemEntity bomItemEntity = bomItemDao.getEntityWithConditions(matlBomEntity.getBomNum(), matlBomEntity.getSrcSysCd());
        EDMMaterialPlantV1Entity materialPlantV1Entity = materialPlantV1Dao.getPlantWithSourceSystemAndLocalPlantAndLocalMaterialNumber(matlBomEntity.getSrcSysCd(), matlBomEntity.getPlntCd(), matlBomEntity.getMatlNum());
        PlanCnsPlanParameterEntity planCnsPlanParameterEntity_system_object = cnsPlanParameterDao.getEntityWithSourceSystemAndDataObject(matlBomEntity.getSrcSysCd(), IConstant.VALUE.SEND_TO_OMP);
        EDMMatlProdVersnEntity matlProdVersnEntity = matlProdVersnEntityList.get(0);
        List<EDMMatlMfgRtngEntity> mfgRtngEntityList = matlMfgRtngDao.getEntityWithFiveConditions(matlProdVersnEntity.getSrcSysCd(), matlProdVersnEntity.getMatlNum(), matlProdVersnEntity.getPlntCd(), matlProdVersnEntity.getRtngGrpCd(), matlProdVersnEntity.getRtngGrpCntrNum());
        EDMMatlMfgRtngEntity mfgRtngEntity = mfgRtngEntityList.get(0);
        List<EDMMfgRtngItmNdeEntity> rtngItmNdeEntityList = mfgRtngItmNdeDao.getEntityWithConditions(mfgRtngEntity.getSrcSysCd(), mfgRtngEntity.getRtngTypCd(), mfgRtngEntity.getRntgGrpCntrNbr(), mfgRtngEntity.getRntgGrpCd());
        EDMMaterialGlobalV1Entity materialGlobalV1Entity = materialGlobalV1Dao.getEntityWithMaterialNumberAndSourceSystem(bomItemEntity.getCmpntNum(), matlBomEntity.getSrcSysCd());
        PlanCnsMaterialPlanStatusEntity cnsMaterialPlanStatusEntity = materialPlanStatusDao.getEntityWithLocalMaterialNumberAndlLocalPlantAndSourceSystem(matlBomEntity.getMatlNum(), matlBomEntity.getPlntCd(), matlBomEntity.getSrcSysCd());
        EDMMfgRtngItmEntity mfgRtngItmEntity = null;
        if (rtngItmNdeEntityList != null && rtngItmNdeEntityList.size() > 0) {
            EDMMfgRtngItmNdeEntity rtngItmNdeEntity = rtngItmNdeEntityList.get(0);
            mfgRtngItmEntity = mfgRtngItmDao.getEntityWithConditions(rtngItmNdeEntity.getSrcSysCd(), rtngItmNdeEntity.getRtngTypCd(), rtngItmNdeEntity.getRtngNdeNum(), rtngItmNdeEntity.getRtngGrpCd());
        }
        if (matlProdVersnEntity != null && bomItemEntity != null && materialPlantV1Entity != null && planCnsPlanParameterEntity_system_object != null && mfgRtngItmEntity != null) {
            EDMBomHdrEntity bomHdrEntity = bomHdrDao.getEntityWithConditions(matlBomEntity.getSrcSysCd(), matlBomEntity.getBomNum(), matlBomEntity.getAltBomNum(), bomItemEntity.getBomCatCd());
            //T1
            String bomElementId = getBomElementId_T1(IConstant.VALUE.ONE, matlProdVersnEntity, mfgRtngEntity, matlBomEntity, mfgRtngItmEntity, bomItemEntity, bomHdrEntity);
            gdmBomElementBo.setBomElementId(bomElementId);
            //T2
            gdmBomElementBo.setActive(IConstant.VALUE.YES);
            gdmBomElementBo.setActiveFCTERP(IConstant.VALUE.YES);
            gdmBomElementBo.setActiveOPRERP(IConstant.VALUE.YES);
            //T3
            gdmBomElementBo.setActiveSOPERP(IConstant.VALUE.NO);
            //T4
            gdmBomElementBo.setBatchId(IConstant.VALUE.BLANK);
            gdmBomElementBo.setComment(IConstant.VALUE.BLANK);
            gdmBomElementBo.setErpFeedbackQuantity(IConstant.VALUE.BLANK);
            gdmBomElementBo.setOffsetCalendarId(IConstant.VALUE.BLANK);
            gdmBomElementBo.setOffsetPercentage(IConstant.VALUE.BLANK);
            gdmBomElementBo.setOffsetPercType(IConstant.VALUE.BLANK);
            //T5
            String bomId = getBomId_T5(matlProdVersnEntity.getPrdntVrsnNum(),
                    planCnsPlanParameterEntity_system_object.getParameterValue(),
                    mfgRtngEntity.getMatlNum(), mfgRtngEntity.getPlntCd(),
                    matlBomEntity.getBomNum(), matlBomEntity.getAltBomNum(), matlBomEntity.getBomUsgCd(),
                    mfgRtngItmEntity.getOperNum());
            gdmBomElementBo.setBomId(bomId);
            //T6
            String bomType = getBomtype_T6(bomItemEntity.getDstrbtnKeyCd(), IConstant.VALUE.ONE);
            gdmBomElementBo.setBomType(bomType);
            //T7
            String bomUsage = getBomUsage_T7(bomItemEntity.getFxQtyInd(), IConstant.VALUE.ONE);
            gdmBomElementBo.setBomUsage(bomUsage);
            //T8
            String endEff = getEndEff_T8(bomItemEntity.getBomItmVldToDt(), matlProdVersnEntity.getValToDt(), IConstant.VALUE.ONE);
            gdmBomElementBo.setEndEff(endEff);
            //T9
            String locationId = getLocationId_T9(matlBomEntity.getSrcSysCd(), matlBomEntity.getPlntCd());
            gdmBomElementBo.setLocationId(locationId);
            //T10
            String offset = getOffset_T10(materialPlantV1Entity.getLocalGoodsReceiptProcessingTimeInDays(), IConstant.VALUE.ONE);
            gdmBomElementBo.setOffset(offset);
            //T11
            String planLevelId = getPlanLevelId_T11(bomItemEntity.getFxQtyInd(), gdmBomElementBo, IConstant.VALUE.ONE,
                    planCnsPlanParameterEntity_system_object, bomItemEntity, matlProdVersnEntity,
                    bomHdrEntity, materialPlantV1Entity, mfgRtngItmEntity,materialGlobalV1Entity, cnsMaterialPlanStatusEntity);
            gdmBomElementBo.setPlanLevelId(planLevelId);
            //T12
            String productId = getProductId_T12(planCnsPlanParameterEntity_system_object, materialGlobalV1Entity, cnsMaterialPlanStatusEntity);
            gdmBomElementBo.setProductId(productId);
            //T13
            String quantity = getQuantity_T13(bomHdrEntity.getBomBaseQty(), null, IConstant.VALUE.ONE);
            gdmBomElementBo.setQuantity(quantity);
            //T14
            String srateEff = getEndEff_T14(bomItemEntity.getBomItmVldFromDt(), matlProdVersnEntity.getValFromDt(), IConstant.VALUE.ONE);
            gdmBomElementBo.setStartEff(srateEff);
            resultObject.setBaseBo(gdmBomElementBo);
        }
        return resultObject;
    }

    public String getBomElementId_T1(String type, EDMMatlProdVersnEntity matlProdVersnEntity, EDMMatlMfgRtngEntity mfgRtngEntity, EDMMatlBomEntity matlBomEntity, EDMMfgRtngItmEntity mfgRtngItmEntity, EDMBomItemEntity bomItemEntity, EDMBomHdrEntity bomHdrEntity) {
        String bomElementId = "";

        if (matlProdVersnEntity != null && mfgRtngEntity != null && matlBomEntity != null
                && mfgRtngItmEntity != null && bomItemEntity != null && bomHdrEntity != null) {
            if (StringUtils.isNotBlank(matlProdVersnEntity.getPrdntVrsnNum())) {
                bomElementId = bomElementId + cleanZero(matlProdVersnEntity.getPrdntVrsnNum()) + IConstant.VALUE.BACK_SLANT;
            }
            if (StringUtils.isNotBlank(mfgRtngEntity.getMatlNum())) {
                bomElementId = bomElementId + cleanZero(mfgRtngEntity.getMatlNum()) + IConstant.VALUE.BACK_SLANT;
            }
            if (StringUtils.isNotBlank(mfgRtngEntity.getPlntCd())) {
                bomElementId = bomElementId + cleanZero(mfgRtngEntity.getPlntCd()) + IConstant.VALUE.BACK_SLANT;
            }
            if (StringUtils.isNotBlank(matlBomEntity.getBomNum())) {
                bomElementId = bomElementId + cleanZero(matlBomEntity.getBomNum()) + IConstant.VALUE.BACK_SLANT;
            }
            if (StringUtils.isNotBlank(matlBomEntity.getAltBomNum())) {
                bomElementId = bomElementId + cleanZero(matlBomEntity.getAltBomNum()) + IConstant.VALUE.BACK_SLANT;
            }
            if (StringUtils.isNotBlank(matlBomEntity.getBomUsgCd())) {
                bomElementId = bomElementId + cleanZero(matlBomEntity.getBomUsgCd()) + IConstant.VALUE.BACK_SLANT;
            }
            if (StringUtils.isNotBlank(mfgRtngItmEntity.getOperNum())) {
                bomElementId = bomElementId + cleanZero(mfgRtngItmEntity.getOperNum()) + IConstant.VALUE.BACK_SLANT;
            }
            if (type.equals(IConstant.VALUE.ZERO)) {
                if (StringUtils.isNotBlank(bomItemEntity.getCmpntNum())) {
                    bomElementId = bomElementId + cleanZero(bomItemEntity.getCmpntNum()) + IConstant.VALUE.BACK_SLANT;
                }
                if (StringUtils.isNotBlank(bomItemEntity.getBomItmNum())) {
                    bomElementId = bomElementId + cleanZero(bomItemEntity.getBomItmNum()) + IConstant.VALUE.BACK_SLANT;
                }
                if (StringUtils.isNotBlank(bomItemEntity.getBomItmVldFromDt())) {
                    Date fromDueDate = DateUtils.stringToDate(bomItemEntity.getBomItmVldFromDt(), DateUtils.F_yyyyMMdd);
                    String fromDate = DateUtils.dateToString(fromDueDate, DateUtils.F_yyyyMMdd);
                    bomElementId = bomElementId + fromDate;
                }
            } else {
                if (StringUtils.isNotBlank(bomHdrEntity.getBomVldFromDt())) {
                    Date fromDueDate = DateUtils.stringToDate(bomHdrEntity.getBomVldFromDt(), DateUtils.F_yyyyMMdd);
                    String fromDate = DateUtils.dateToString(fromDueDate, DateUtils.F_yyyyMMdd);
                    bomElementId = bomElementId + fromDate;
                }
            }
        }
        return bomElementId;
    }

    public String getBomId_T5(String prdntVrsnNum, String parameterValue, String matlNum, String plntCd,
                              String bomNum, String altBomNum, String bomUsgCd, String operNum) {
        String bomId = "";
        if (StringUtils.isNotBlank(prdntVrsnNum)) {
            bomId = bomId + cleanZero(prdntVrsnNum) + IConstant.VALUE.BACK_SLANT;
        }
        if (StringUtils.isNotBlank(parameterValue)) {
            bomId = bomId + cleanZero(parameterValue) + IConstant.VALUE.UNDERLINE;
        }
        if (StringUtils.isNotBlank(matlNum)) {
            bomId = bomId + cleanZero(matlNum) + IConstant.VALUE.BACK_SLANT;
        }
        if (StringUtils.isNotBlank(plntCd)) {
            bomId = bomId + cleanZero(plntCd) + IConstant.VALUE.BACK_SLANT;
        }
        if (StringUtils.isNotBlank(bomNum)) {
            bomId = bomId + cleanZero(bomNum) + IConstant.VALUE.BACK_SLANT;
        }
        if (StringUtils.isNotBlank(altBomNum)) {
            bomId = bomId + cleanZero(altBomNum) + IConstant.VALUE.BACK_SLANT;
        }
        if (StringUtils.isNotBlank(bomUsgCd)) {
            bomId = bomId + cleanZero(bomUsgCd) + IConstant.VALUE.BACK_SLANT;
        }
        if (StringUtils.isNotBlank(operNum)) {
            bomId = bomId + operNum;
        }
        return bomId;
    }

    public String getBomtype_T6(String dstrbtnKeyCd, String type) {
        String bomtype = "";
        if (type.equals(IConstant.VALUE.ZERO)) {
            if (StringUtils.isBlank(dstrbtnKeyCd)) {
                bomtype = IConstant.VALUE.BATCH_START;
            } else {
                bomtype = IConstant.VALUE.CONTINU;
            }
        } else {
            if (StringUtils.isBlank(dstrbtnKeyCd)) {
                bomtype = IConstant.VALUE.BATCH_END;
            } else {
                bomtype = IConstant.VALUE.CONTINU;
            }
        }
        return bomtype;
    }

    public String getBomUsage_T7(String fxQtyInd, String type) {
        String bomusage = "";
        if (type.equals(IConstant.VALUE.ZERO)) {
            if (StringUtils.isBlank(fxQtyInd)) {
                bomusage = IConstant.VALUE.PROPORTIONAL;
            } else if (fxQtyInd.equals(IConstant.VALUE.X)) {
                bomusage = IConstant.VALUE.FIXED;
            }
        } else {
            bomusage = IConstant.VALUE.PROPORTIONAL;
        }
        return bomusage;
    }

    public String getEndEff_T8(String bomItmVldToDt, String valToDt, String type) {
        String endEff = "";
        Date bomDt = DateUtils.stringToDate(bomItmVldToDt, DateUtils.yyyy_MM_dd);
        Date valDt = DateUtils.stringToDate(valToDt, DateUtils.F_yyyyMMdd);
        Date endEff_default = DateUtils.stringToDate(IConstant.VALUE.ENDEFF, DateUtils.J_yyyyMMdd_HHmmss);
        if (type.equals(IConstant.VALUE.ZERO)) {
            if (bomDt.after(valDt)) {
                endEff = DateUtils.dateToString(valDt, DateUtils.J_yyyyMMdd_HHmmss);
            } else {
                endEff = DateUtils.dateToString(bomDt, DateUtils.J_yyyyMMdd_HHmmss);
            }
        } else {
            if (bomDt.after(valDt)) {
                if (valDt.after(endEff_default)) {
                    endEff = IConstant.VALUE.ENDEFF;
                } else {
                    endEff = DateUtils.dateToString(bomDt, DateUtils.J_yyyyMMdd_HHmmss);
                }
            } else {
                if (valDt.after(endEff_default)) {
                    endEff = IConstant.VALUE.ENDEFF;
                } else {
                    endEff = DateUtils.dateToString(valDt, DateUtils.J_yyyyMMdd_HHmmss);
                }
            }
        }
        return endEff;
    }

    private String getLocationId_T9(String srcSysCd, String plntCd) {
        String locationId = srcSysCd + IConstant.VALUE.UNDERLINE + plntCd;
        return locationId;
    }

    private String getOffset_T10(String leadTimeOffst, String type) {
        String offset = "";
        if (type.equals(IConstant.VALUE.ZERO)) {
            offset = String.valueOf(Integer.parseInt(leadTimeOffst) * Integer.parseInt(IConstant.VALUE.BOM_86400));
        } else {
            if (StringUtils.isNotBlank(leadTimeOffst)) {
                offset = String.valueOf(Integer.parseInt(leadTimeOffst) * Integer.parseInt(IConstant.VALUE.BOM_86400));
            } else {
                offset = IConstant.VALUE.BLANK;
            }
        }
        return offset;
    }

    private String getPlanLevelId_T11(String fxQtyInd, OMPGdmBomElementBo gdmBomElementBo, String type,
                                      PlanCnsPlanParameterEntity planCnsPlanParameterEntity, EDMBomItemEntity bomItemEntity,
                                      EDMMatlProdVersnEntity matlProdVersnEntity, EDMBomHdrEntity bomHdrEntity,
                                      EDMMaterialPlantV1Entity materialPlantV1Entity, EDMMfgRtngItmEntity mfgRtngItmEntity,EDMMaterialGlobalV1Entity materialGlobalV1Entity, PlanCnsMaterialPlanStatusEntity cnsMaterialPlanStatusEntity) {
        String planLevelId = "";
        if (type.equals(IConstant.VALUE.ZERO)) {
            if (StringUtils.isBlank(fxQtyInd)) {
                planLevelId = IConstant.VALUE.ASTERIX;
            } else if (fxQtyInd.equals(IConstant.VALUE.X)) {
                ResultObject resultObject_T11 = new ResultObject();
                planLevelId = IConstant.VALUE.DETAILEDSCHEDULING;
                //create new row
                OMPGdmBomElementBo gdmBomElementBo_T11 = gdmBomElementBo.clone();
                gdmBomElementBo_T11.setBomElementId(gdmBomElementBo_T11.getBomElementId() + IConstant.VALUE.PROPORTIONAL_BACK_SLANT);
                gdmBomElementBo_T11.setPlanLevelId(IConstant.VALUE.VOLUMEPLANNING);
                //T12
                String productId = getProductId_T12(planCnsPlanParameterEntity,materialGlobalV1Entity,cnsMaterialPlanStatusEntity);
                gdmBomElementBo_T11.setProductId(productId);
                //T13 Quantity = BOM_ITEM-cmpntQty * average order qty / BOM_HDR-bomBaseQty
                String qty = "";
                if (StringUtils.isNotBlank(materialPlantV1Entity.getLocalFixedLotSize()) && !materialPlantV1Entity.getLocalFixedLotSize().equals(IConstant.VALUE.ZERO)) {
                    qty = materialPlantV1Entity.getLocalFixedLotSize();
                } else if (StringUtils.isNotBlank(materialPlantV1Entity.getLocalMaximumLotSize()) && !materialPlantV1Entity.getLocalMaximumLotSize().equals(IConstant.VALUE.ZERO)) {
                    qty = materialPlantV1Entity.getLocalFixedLotSize();
                } else if (StringUtils.isNotBlank(mfgRtngItmEntity.getBsQty()) && !mfgRtngItmEntity.getBsQty().equals(IConstant.VALUE.ZERO)) {
                    qty = mfgRtngItmEntity.getBsQty();
                }
                BigDecimal cmpntQty = new BigDecimal(bomItemEntity.getCmpntQty());
                BigDecimal qty_Decimal = new BigDecimal(qty);
                BigDecimal bomBaseQty = new BigDecimal(bomHdrEntity.getBomBaseQty());
                String quantity = cmpntQty.multiply(qty_Decimal).divide(bomBaseQty).setScale(3).toString();
                gdmBomElementBo_T11.setQuantity(quantity);
                resultObject_T11.setBaseBo(gdmBomElementBo_T11);
                resultObjectList_1.add(resultObject_T11);
            }
        } else {
            planLevelId = IConstant.VALUE.ASTERIX;
        }
        return planLevelId;
    }

    private String getProductId_T12(PlanCnsPlanParameterEntity planCnsPlanParameterEntity, EDMMaterialGlobalV1Entity materialGlobalV1Entity, PlanCnsMaterialPlanStatusEntity cnsMaterialPlanStatusEntity) {
        String spRelevant = cnsMaterialPlanStatusEntity.getSpRelevant();
        String noPlanRelevant = cnsMaterialPlanStatusEntity.getNoPlanRelevant();
        String dpRelevant = cnsMaterialPlanStatusEntity.getDpRelevant();
        String productId = "";
        if (spRelevant.equals(IConstant.VALUE.X) || noPlanRelevant.equals(IConstant.VALUE.X)) {
            productId = materialGlobalV1Entity.getPrimaryPlanningCode();
        } else if (dpRelevant.equals(IConstant.VALUE.X)) {
            productId = planCnsPlanParameterEntity.getParameterValue() + IConstant.VALUE.UNDERLINE + materialGlobalV1Entity.getLocalDpParentCode();
        }
        return productId;
    }


    public String getQuantity_T13(String cmpntQty, String cmpntScrap_Pct, String type) {
        String quantity = "";
        if (StringUtils.isNotBlank(cmpntQty) && StringUtils.isNotBlank(cmpntScrap_Pct)) {
            BigDecimal cmpntQty_Decimal = new BigDecimal(cmpntQty);
            BigDecimal cmpntScrap_Pct_Decimal = new BigDecimal(cmpntScrap_Pct);
            if (type.equals(IConstant.VALUE.ZERO)) {
                BigDecimal big100 = new BigDecimal(100);
                quantity = cmpntQty_Decimal.multiply(cmpntScrap_Pct_Decimal).divide(big100).add(big100).setScale(3).toString();
            } else {
                quantity = cmpntQty_Decimal.multiply(new BigDecimal(-1)).setScale(3).toString();
            }
        }
        return quantity;
    }

    public String getEndEff_T14(String bomItmVldFromDt, String valFromDt, String type) {
        String StartEff = "";
        if (StringUtils.isNotBlank(bomItmVldFromDt) && StringUtils.isNotBlank(valFromDt)) {
            Date bomDt = DateUtils.stringToDate(bomItmVldFromDt, DateUtils.F_yyyyMMdd);
            Date valDt = DateUtils.stringToDate(valFromDt, DateUtils.F_yyyyMMdd);
            if (type.equals(IConstant.VALUE.ZERO)) {
                if (bomDt.after(valDt)) {
                    StartEff = DateUtils.dateToString(bomDt, DateUtils.J_yyyyMMdd_HHmmss);
                } else {
                    StartEff = DateUtils.dateToString(valDt, DateUtils.J_yyyyMMdd_HHmmss);
                }
            } else {
                if (bomDt.after(valDt)) {
                    StartEff = DateUtils.dateToString(valDt, DateUtils.J_yyyyMMdd_HHmmss);
                } else {
                    StartEff = DateUtils.dateToString(bomDt, DateUtils.J_yyyyMMdd_HHmmss);
                }
            }
        }
        return StartEff;
    }

    public String cleanZero(String str) {
        String newStr = str.replaceAll("^(0+)", "");
        return newStr;
    }
}
