package com.jnj.pangea.omp.gdm_bom_element.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.*;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanParameterDaoImpl;
import com.jnj.pangea.common.entity.edm.*;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanParameterEntity;
import com.jnj.pangea.omp.gdm_bom_element.bo.OMPGdmBomElementBo;
import com.jnj.pangea.util.DateUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OMPGdmBomElementServiceImpl {

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
    List<ResultObject> resultObjectList  = new ArrayList<ResultObject>();

    public List<ResultObject> buildView(String key, Object o, Object o2) {
        resultObjectList = getCommentDate(o);
        ResultObject resultObject = getProductMat(o);
        resultObjectList.add(resultObject);
        return resultObjectList;
    }


    public List<ResultObject> getCommentDate(Object o) {
        List<ResultObject> resultObjectList = new ArrayList<ResultObject>();
        EDMMatlBomEntity matlBomEntity = (EDMMatlBomEntity) o;
        //J1
        List<EDMMatlProdVersnEntity> matlProdVersnList = matlProdVersnDao.getEntityWithFourConditions(matlBomEntity.getSrcSysCd(), matlBomEntity.getPlntCd(), matlBomEntity.getMatlNum(), matlBomEntity.getAltBomNum());
        List<EDMBomItemEntity> bomItemEntityList = bomItemDao.getEntityWithConditions(matlBomEntity.getBomNum(), matlBomEntity.getSrcSysCd());
        PlanCnsPlanParameterEntity planCnsPlanParameterEntity_system_object = cnsPlanParameterDao.getEntityWithSourceSystemAndDataObject(matlBomEntity.getSrcSysCd(), IConstant.VALUE.SEND_TO_OMP);
        if (matlProdVersnList != null && matlProdVersnList.size() > 0) {
            for (EDMMatlProdVersnEntity matlProdVersnEntity : matlProdVersnList) {
                List<EDMMatlMfgRtngEntity> mfgRtngEntityList = matlMfgRtngDao.getEntityWithFiveConditions(matlProdVersnEntity.getSrcSysCd(), matlProdVersnEntity.getMatlNum(), matlProdVersnEntity.getPlntCd(), matlProdVersnEntity.getRtngGrpCd(), matlProdVersnEntity.getRtngGrpCntrNum());
                if (mfgRtngEntityList != null && mfgRtngEntityList.size() > 0) {
                    for (EDMMatlMfgRtngEntity mfgRtngEntity : mfgRtngEntityList) {
                        List<EDMMfgRtngItmNdeEntity> rtngItmNdeEntityList = mfgRtngItmNdeDao.getEntityWithConditions(mfgRtngEntity.getSrcSysCd(), mfgRtngEntity.getRtngTypCd(), mfgRtngEntity.getRntgGrpCntrNbr(), mfgRtngEntity.getRtngGrpCd());
                        if (rtngItmNdeEntityList != null && rtngItmNdeEntityList.size() > 0) {
                            for (EDMMfgRtngItmNdeEntity rtngItmNdeEntity : rtngItmNdeEntityList) {
                                List<EDMMfgRtngItmEntity> mfgRtngItmEntityList = mfgRtngItmDao.getEntityWithConditions(rtngItmNdeEntity.getSrcSysCd(), rtngItmNdeEntity.getRtngTypCd(), rtngItmNdeEntity.getRtngNdeNum(), rtngItmNdeEntity.getRtngGrpCd());
                                if (mfgRtngItmEntityList != null && mfgRtngItmEntityList.size() > 0) {
                                    for (EDMMfgRtngItmEntity mfgRtngItmEntity : mfgRtngItmEntityList) {
                                        for (EDMBomItemEntity bomItemEntity : bomItemEntityList) {
                                            EDMBomHdrEntity bomHdrEntity = bomHdrDao.getEntityWithConditions(matlBomEntity.getSrcSysCd(), matlBomEntity.getBomNum(), matlBomEntity.getAltBomNum(), bomItemEntity.getBomCatCd());
                                            ResultObject resultObject = new ResultObject();
                                            OMPGdmBomElementBo gdmBomElementBo = new OMPGdmBomElementBo();
                                            //T1
                                            String bomElementId = getBomElementId_T1(matlProdVersnEntity, mfgRtngEntity, matlBomEntity, mfgRtngItmEntity, bomItemEntity);
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
                                                    bomHdrEntity.getBomNum(), bomHdrEntity.getAltBomNum(), bomHdrEntity.getBomUomCd(),
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
                                            //T11
                                            String planLevelId = getPlanLevelId_T11(bomItemEntity.getFxQtyInd(),gdmBomElementBo ,IConstant.VALUE.ZERO,planCnsPlanParameterEntity_system_object, bomItemEntity, matlProdVersnEntity);
                                            gdmBomElementBo.setPlanLevelId(planLevelId);
                                            //T12
                                            String productId = getProductId_T12(planCnsPlanParameterEntity_system_object.getParameterValue(), bomItemEntity.getCmpntNum());
                                            gdmBomElementBo.setProductId(productId);

                                            //T14
                                            String srateEff = getEndEff_T14(bomItemEntity.getBomItmVldFromDt(), matlProdVersnEntity.getValFromDt(), IConstant.VALUE.ZERO);
                                            gdmBomElementBo.setStartEff(srateEff);
                                            resultObject.setBaseBo(gdmBomElementBo);
                                            resultObjectList.add(resultObject);
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
                } else {
                    return resultObjectList;
                }
            }
        } else {
            return resultObjectList;
        }
        return resultObjectList;
    }


    public ResultObject getProductMat(Object o) {
        EDMMatlBomEntity matlBomEntity = (EDMMatlBomEntity) o;
        ResultObject resultObject = new ResultObject();
        OMPGdmBomElementBo gdmBomElementBo = new OMPGdmBomElementBo();
        List<EDMMatlProdVersnEntity> matlProdVersnList = matlProdVersnDao.getEntityWithFourConditions(matlBomEntity.getSrcSysCd(), matlBomEntity.getPlntCd(), matlBomEntity.getMatlNum(), matlBomEntity.getAltBomNum());
        List<EDMBomItemEntity> bomItemEntityList = bomItemDao.getEntityWithConditions(matlBomEntity.getBomNum(), matlBomEntity.getSrcSysCd());
        EDMMaterialPlantV1Entity materialPlantV1Entity = materialPlantV1Dao.getPlantWithSourceSystemAndLocalPlantAndLocalMaterialNumber(matlBomEntity.getSrcSysCd(), matlBomEntity.getPlntCd(), matlBomEntity.getMatlNum());
        PlanCnsPlanParameterEntity planCnsPlanParameterEntity_system_object = cnsPlanParameterDao.getEntityWithSourceSystemAndDataObject(matlBomEntity.getSrcSysCd(), IConstant.VALUE.SEND_TO_OMP);
        EDMMatlProdVersnEntity matlProdVersnEntity=matlProdVersnList.get(0);

        List<EDMMatlMfgRtngEntity> mfgRtngEntityList = matlMfgRtngDao.getEntityWithFiveConditions(matlProdVersnEntity.getSrcSysCd(), matlProdVersnEntity.getMatlNum(), matlProdVersnEntity.getPlntCd(), matlProdVersnEntity.getRtngGrpCd(), matlProdVersnEntity.getRtngGrpCntrNum());
        EDMMatlMfgRtngEntity mfgRtngEntity = mfgRtngEntityList.get(0);
        List<EDMMfgRtngItmNdeEntity> rtngItmNdeEntityList = mfgRtngItmNdeDao.getEntityWithConditions(mfgRtngEntity.getSrcSysCd(), mfgRtngEntity.getRtngTypCd(), mfgRtngEntity.getRntgGrpCntrNbr(), mfgRtngEntity.getRtngGrpCd());
        EDMMfgRtngItmNdeEntity rtngItmNdeEntity=rtngItmNdeEntityList.get(0);
        List<EDMMfgRtngItmEntity> mfgRtngItmEntityList = mfgRtngItmDao.getEntityWithConditions(rtngItmNdeEntity.getSrcSysCd(), rtngItmNdeEntity.getRtngTypCd(), rtngItmNdeEntity.getRtngNdeNum(), rtngItmNdeEntity.getRtngGrpCd());
        EDMMfgRtngItmEntity mfgRtngItmEntity =mfgRtngItmEntityList.get(0);
        EDMBomItemEntity bomItemEntity = bomItemEntityList.get(0);
        EDMBomHdrEntity bomHdrEntity = bomHdrDao.getEntityWithConditions(matlBomEntity.getSrcSysCd(), matlBomEntity.getBomNum(), matlBomEntity.getAltBomNum(), bomItemEntity.getBomCatCd());
        //T1
        String bomElementId = getBomElementId_T1(matlProdVersnEntity, mfgRtngEntity, matlBomEntity, mfgRtngItmEntity, bomItemEntity);
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
                bomHdrEntity.getBomNum(), bomHdrEntity.getAltBomNum(), bomHdrEntity.getBomUomCd(),
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
        String planLevelId = getPlanLevelId_T11(bomItemEntity.getFxQtyInd(),gdmBomElementBo, IConstant.VALUE.ONE, planCnsPlanParameterEntity_system_object, bomItemEntity, matlProdVersnEntity);
        gdmBomElementBo.setPlanLevelId(planLevelId);
        //T12
        String productId = getProductId_T12(planCnsPlanParameterEntity_system_object.getParameterValue(), bomItemEntity.getCmpntNum());
        gdmBomElementBo.setProductId(productId);

        //T14
        String srateEff = getEndEff_T14(bomItemEntity.getBomItmVldFromDt(), matlProdVersnEntity.getValFromDt(), IConstant.VALUE.ONE);
        gdmBomElementBo.setStartEff(srateEff);
        resultObject.setBaseBo(gdmBomElementBo);
        return resultObject;
    }

    public String getBomElementId_T1(EDMMatlProdVersnEntity matlProdVersnEntity, EDMMatlMfgRtngEntity mfgRtngEntity, EDMMatlBomEntity matlBomEntity, EDMMfgRtngItmEntity mfgRtngItmEntity, EDMBomItemEntity bomItemEntity) {
        String bomElementId = "";
        if (StringUtils.isNotBlank(matlProdVersnEntity.getPrdntVrsnNum())) {
            bomElementId = bomElementId + matlProdVersnEntity.getPrdntVrsnNum() + IConstant.VALUE.BACK_SLANT;
        }
        if (StringUtils.isNotBlank(mfgRtngEntity.getMatlNum())) {
            bomElementId = bomElementId + mfgRtngEntity.getMatlNum() + IConstant.VALUE.BACK_SLANT;
        }
        if (StringUtils.isNotBlank(mfgRtngEntity.getPlntCd())) {
            bomElementId = bomElementId + mfgRtngEntity.getPlntCd() + IConstant.VALUE.BACK_SLANT;
        }
        if (StringUtils.isNotBlank(matlBomEntity.getBomNum())) {
            bomElementId = bomElementId + matlBomEntity.getBomNum() + IConstant.VALUE.BACK_SLANT;
        }
        if (StringUtils.isNotBlank(matlBomEntity.getAltBomNum())) {
            bomElementId = bomElementId + matlBomEntity.getAltBomNum() + IConstant.VALUE.BACK_SLANT;
        }
        if (StringUtils.isNotBlank(matlBomEntity.getBomUsgCd())) {
            bomElementId = bomElementId + matlBomEntity.getBomUsgCd() + IConstant.VALUE.BACK_SLANT;
        }
        if (StringUtils.isNotBlank(mfgRtngItmEntity.getOperNum())) {
            bomElementId = bomElementId + mfgRtngItmEntity.getOperNum() + IConstant.VALUE.BACK_SLANT;
        }
        if (StringUtils.isNotBlank(bomItemEntity.getCmpntNum())) {
            bomElementId = bomElementId + bomItemEntity.getCmpntNum() + IConstant.VALUE.BACK_SLANT;
        }
        if (StringUtils.isNotBlank(bomItemEntity.getBomItmNum())) {
            bomElementId = bomElementId + bomItemEntity.getBomItmNum() + IConstant.VALUE.BACK_SLANT;
        }
        if (StringUtils.isNotBlank(bomItemEntity.getBomItmVldFromDt())) {
            Date fromDueDate = DateUtils.stringToDate(bomItemEntity.getBomItmVldFromDt(), DateUtils.DATE_FORMAT_1);
            String fromDate = DateUtils.dateToString(fromDueDate, DateUtils.F_yyyyMMdd);
            bomElementId = bomElementId + fromDate;
        }
        return bomElementId;
    }

    public String getBomId_T5(String prdntVrsnNum, String parameterValue, String matlNum, String plntCd,
                              String bomNum, String altBomNum, String bomUsgCd, String operNum) {
        String bomId = "";
        if (StringUtils.isNotBlank(prdntVrsnNum)) {
            bomId = bomId + prdntVrsnNum + IConstant.VALUE.BACK_SLANT;
        }
        if (StringUtils.isNotBlank(parameterValue)) {
            bomId = bomId + parameterValue + IConstant.VALUE.UNDERLINE;
        }
        if (StringUtils.isNotBlank(matlNum)) {
            bomId = bomId + matlNum + IConstant.VALUE.BACK_SLANT;
        }
        if (StringUtils.isNotBlank(plntCd)) {
            bomId = bomId + plntCd + IConstant.VALUE.BACK_SLANT;
        }
        if (StringUtils.isNotBlank(bomNum)) {
            bomId = bomId + bomNum + IConstant.VALUE.BACK_SLANT;
        }
        if (StringUtils.isNotBlank(altBomNum)) {
            bomId = bomId + altBomNum + IConstant.VALUE.BACK_SLANT;
        }
        if (StringUtils.isNotBlank(bomUsgCd)) {
            bomId = bomId + bomUsgCd + IConstant.VALUE.BACK_SLANT;
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
        Date valDt = DateUtils.stringToDate(valToDt, DateUtils.yyyy_MM_dd);
        Date endEff_default = DateUtils.stringToDate(IConstant.VALUE.ENDEFF, DateUtils.J_yyyy_MM_dd_HHmmss);
        if (type.equals(IConstant.VALUE.ZERO)) {
            if (bomDt.after(valDt)) {
                endEff = DateUtils.dateToString(valDt, DateUtils.J_yyyy_MM_dd_HHmmss);
            } else {
                endEff = DateUtils.dateToString(bomDt, DateUtils.J_yyyy_MM_dd_HHmmss);
            }
        } else {
            if (bomDt.after(valDt)) {
                if (valDt.after(endEff_default)) {
                    endEff = IConstant.VALUE.ENDEFF;
                } else {
                    endEff = DateUtils.dateToString(bomDt, DateUtils.J_yyyy_MM_dd_HHmmss);
                }
            } else {
                if (valDt.after(endEff_default)) {
                    endEff = IConstant.VALUE.ENDEFF;
                } else {
                    endEff = DateUtils.dateToString(valDt, DateUtils.J_yyyy_MM_dd_HHmmss);
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

    private String getPlanLevelId_T11(String fxQtyInd,OMPGdmBomElementBo gdmBomElementBo_T11, String type,PlanCnsPlanParameterEntity planCnsPlanParameterEntity,EDMBomItemEntity bomItemEntity,EDMMatlProdVersnEntity matlProdVersnEntity) {
        String planLevelId = "";
        if (type.equals(IConstant.VALUE.ZERO)) {
            if (StringUtils.isBlank(fxQtyInd)) {
                planLevelId = IConstant.VALUE.ASTERIX;
            } else if (fxQtyInd.equals(IConstant.VALUE.X)) {
                ResultObject resultObject_T11= new ResultObject();
                planLevelId = IConstant.VALUE.DETAILEDSCHEDULING;
                //create new row
                gdmBomElementBo_T11.setBomElementId(gdmBomElementBo_T11.getBomElementId()+IConstant.VALUE.PROPORTIONAL_BACK_SLANT);
                gdmBomElementBo_T11.setPlanLevelId(IConstant.VALUE.VOLUMEPLANNING);
                //T12
                String productId = getProductId_T12(planCnsPlanParameterEntity.getParameterValue(), bomItemEntity.getCmpntNum());
                gdmBomElementBo_T11.setProductId(productId);
                //T13
                gdmBomElementBo_T11.setQuantity("");
                //T14
                String srateEff = getEndEff_T14(bomItemEntity.getBomItmVldFromDt(), matlProdVersnEntity.getValFromDt(), IConstant.VALUE.ONE);
                gdmBomElementBo_T11.setStartEff(srateEff);
                resultObject_T11.setBaseBo(gdmBomElementBo_T11);
                resultObjectList.add(resultObject_T11);
            }
        } else {
            planLevelId = IConstant.VALUE.ASTERIX;
        }
        return planLevelId;
    }

    private String getProductId_T12(String parameterValue, String cmpntNum) {
        String productId = parameterValue + IConstant.VALUE.UNDERLINE + cmpntNum;
        return productId;
    }

    public String getEndEff_T14(String bomItmVldFromDt, String valFromDt, String type) {
        String StartEff = "";
        Date bomDt = DateUtils.stringToDate(bomItmVldFromDt, DateUtils.yyyy_MM_dd);
        Date valDt = DateUtils.stringToDate(valFromDt, DateUtils.yyyy_MM_dd);
        if (type.equals(IConstant.VALUE.ZERO)) {
            if (bomDt.after(valDt)) {
                StartEff = DateUtils.dateToString(bomDt, DateUtils.J_yyyy_MM_dd_HHmmss);
            } else {
                StartEff = DateUtils.dateToString(valDt, DateUtils.J_yyyy_MM_dd_HHmmss);
            }
        } else {
            if (bomDt.after(valDt)) {
                StartEff = DateUtils.dateToString(valDt, DateUtils.J_yyyy_MM_dd_HHmmss);
            } else {
                StartEff = DateUtils.dateToString(bomDt, DateUtils.J_yyyy_MM_dd_HHmmss);
            }
        }
        return StartEff;
    }
}
