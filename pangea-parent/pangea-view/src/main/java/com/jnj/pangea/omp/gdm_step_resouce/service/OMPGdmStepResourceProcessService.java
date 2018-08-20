package com.jnj.pangea.omp.gdm_step_resouce.service;

import com.jnj.adf.client.api.remote.RawDataValue;
import com.jnj.adf.grid.data.raw.RawDataBuilder;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.inner.StringInner;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.*;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsMaterialPlanStatusDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanParameterDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlantAttrDaoImpl;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOneT430DaoImpl;
import com.jnj.pangea.common.entity.edm.*;
import com.jnj.pangea.common.entity.plan.PlanCnsMaterialPlanStatusEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanParameterEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsPlantAttrEntity;
import com.jnj.pangea.common.entity.project_one.T430Entity;
import com.jnj.pangea.util.DateUtils;
import org.apache.commons.lang3.StringUtils;
import sun.rmi.runtime.Log;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class OMPGdmStepResourceProcessService {

    private static OMPGdmStepResourceProcessService instance;

    public static OMPGdmStepResourceProcessService getInstance() {
        if (instance == null) {
            instance = new OMPGdmStepResourceProcessService();
        }
        return instance;
    }

    private PlanCnsPlanParameterDaoImpl planCnsPlanParameterDao = PlanCnsPlanParameterDaoImpl.getInstance();
    private EDMMfgOrderItmDaoImpl edmMfgOrderItmDao = EDMMfgOrderItmDaoImpl.getInstance();
    private EDMMatlProdVersnDaoImpl edmMatlProdVersnDao = EDMMatlProdVersnDaoImpl.getInstance();
    private EDMMfgRtngItmNdeDaoImpl edmMfgRtngItmNdeDao = EDMMfgRtngItmNdeDaoImpl.getInstance();
    private EDMMfgOrderRtngDaoImpl edmMfgOrderRtngDao = EDMMfgOrderRtngDaoImpl.getInstance();
    private EDMMfgRtgParmDaoImpl edmMfgRtgParmDao = EDMMfgRtgParmDaoImpl.getInstance();
    private ProjectOneT430DaoImpl projectOneT430Dao = ProjectOneT430DaoImpl.getInstance();
    private EDMWrkCtrDaoImpl edmWrkCtrDao = EDMWrkCtrDaoImpl.getInstance();
    private EDMWrkCtrCapyDaoImpl edmWrkCtrCapyDao = EDMWrkCtrCapyDaoImpl.getInstance();
    private EDMCapyHdrDaoImpl edmCapyHdrDao = EDMCapyHdrDaoImpl.getInstance();
    private PlanCnsMaterialPlanStatusDaoImpl materialPlanStatusDao = PlanCnsMaterialPlanStatusDaoImpl.getInstance();

    public boolean buildView(RawDataValue raw, List<RawDataBuilder> rawDataBuilderList, Map<String, RawDataBuilder> failMap) {
        Map map = raw.toMap();
        String sourceSysCd = map.get("sourceSysCd").toString();
        String mfgOrdrSttsCd = map.get("mfgOrdrSttsCd").toString();
        String plntCd = map.get("plntCd").toString();
        String mfgOrdrNum = map.get("mfgOrdrNum").toString();
        String ordrRtngNum = map.get("ordrRtngNum").toString();
        String mfgOrdrTypCd = map.get("mfgOrdrTypCd").toString();

        String yesDefault = "YES";
        String noDefault = "NO";
        String zeroDefault = "0.000";
        String prodStr = "production";

        if (StringInner.isStringNotEmpty(sourceSysCd) && StringInner.isStringNotEmpty(plntCd)
                 && StringInner.isStringNotEmpty(mfgOrdrSttsCd)
                && StringInner.isStringNotEmpty(mfgOrdrTypCd)) {

            if (checkCnsPlanParameter4J1_1(sourceSysCd)
                    && checkCnsPlanParameter4J1_2(sourceSysCd, plntCd)
                    && checkCnsPlanParameter4J1_3(sourceSysCd, mfgOrdrTypCd)
                    //&& checckCnsPlanParameter4J1_4(sourceSysCd, actRlseDt) //delete dt filter
                    //&& checkCnsPlanParameter4J1_51(sourceSysCd, mfgOrdrSttsCd)
                    && checkCnsPlanParameter4J1_52(sourceSysCd, mfgOrdrSttsCd)) {
                if (StringInner.isStringNotEmpty(mfgOrdrNum) && StringInner.isStringNotEmpty(ordrRtngNum)) {
                    EDMMfgOrderItmEntity mfgOrderItmEtt = joinMfgOrderItm(sourceSysCd, mfgOrdrNum, plntCd);
                    if (mfgOrderItmEtt != null) {
                        String srcSysCd = mfgOrderItmEtt.getSrcSysCd();
                        String prdntVrsnNum = mfgOrderItmEtt.getPrdntVrsnNum();
                        String matlNum = mfgOrderItmEtt.getMatlNum();
                        plntCd = mfgOrderItmEtt.getPlntCd();
                        if (StringInner.isStringNotEmpty(prdntVrsnNum) && StringInner.isStringNotEmpty(matlNum)
                                && StringUtils.isNotBlank(plntCd) && StringUtils.isNotBlank(srcSysCd)
                                && checkMaterialPlanStatus(srcSysCd,matlNum,plntCd)) {

                            EDMMatlProdVersnEntity matlProdVersnEtt = joinMaltProdVersn(srcSysCd, matlNum, plntCd, prdntVrsnNum);
                            if (matlProdVersnEtt != null) {
                                String minQuantity = "0.000";
                                srcSysCd = matlProdVersnEtt.getSrcSysCd();
                                String rtngTypCd = matlProdVersnEtt.getRtngTypCd();
                                String rtngGrpCd = matlProdVersnEtt.getRtngGrpCd();
                                String rtngGrpCntrNum = matlProdVersnEtt.getRtngGrpCntrNum();
                                if (StringInner.isStringNotEmpty(srcSysCd) && StringInner.isStringNotEmpty(rtngTypCd)
                                        && StringInner.isStringNotEmpty(rtngGrpCd) && StringInner.isStringNotEmpty(rtngGrpCntrNum)) {
                                    EDMMfgRtngItmNdeEntity mfgRtngItmNde = joinMfgRtngItmNde(srcSysCd, rtngTypCd, rtngGrpCd, rtngGrpCntrNum);
                                    if (mfgRtngItmNde != null) {
                                        String rtngNdeNum = mfgRtngItmNde.getRtngNdeNum();
                                        EDMMfgRtgParmEntity mfgRtgParmEtt = joinMfgRtgParm(srcSysCd, rtngTypCd, rtngGrpCd, rtngNdeNum);
                                        if (mfgRtgParmEtt != null) {
//                                            LogUtil.getCoreLog().info("----------mfgRtgParm>>" + mfgRtgParmEtt.toString());
                                            String charVal = mfgRtgParmEtt.getCharVal();
                                            if (StringInner.isStringNotEmpty(charVal)) {
                                                DecimalFormat df = new DecimalFormat("0.000");
                                                try {
                                                    minQuantity = df.format(Double.parseDouble(StringUtils.replace(charVal, ",", ".")));
                                                } catch (NumberFormatException ne) {
                                                    LogUtil.getCoreLog().error(ne.getMessage());
                                                }
                                            }
                                        }
                                    }
                                }

                                List<EDMMfgOrderRtngEntity> mfgOrderRtngMapmLst = joinMfgOrderRtng(sourceSysCd, ordrRtngNum);
                                if (mfgOrderRtngMapmLst != null && mfgOrderRtngMapmLst.size() > 0) {
                                    for (EDMMfgOrderRtngEntity item : mfgOrderRtngMapmLst) {
//                                        LogUtil.getCoreLog().info("---------------mfgOrderRtngMapmLst >>mfgOrderRtngMap:{}",item.toString());
                                        String operCd = item.getOperCd();
                                        String wrkCntrId = item.getWrkCntrId();
                                        String operNum = checkNull4Str(item.getOperNum());
                                        if (StringInner.isStringNotEmpty(operCd)) {
//                                            LogUtil.getCoreLog().info("-------------------mfgOrderRtng >>operCd:{}，wrkCntrId：{}", operCd, wrkCntrId);
                                            if (checkT430(operCd) && StringInner.isStringNotEmpty(wrkCntrId)) {
                                                EDMWrkCtrEntity wrkCtrMap = joinWrkCtr(srcSysCd, wrkCntrId);
                                                if (wrkCtrMap != null) {
//                                                    LogUtil.getCoreLog().info("-------------------WrkCtr >>wrkCtrMap:{}",wrkCtrMap.toString());
                                                    srcSysCd = wrkCtrMap.getSrcSysCd();
                                                    String wrkCtrNum = wrkCtrMap.getWrkCtrNum();
                                                    String wrkCtrTypeCd = wrkCtrMap.getWrkCtrTypeCd();
                                                    String wrkCtrCd = checkNull4Str(wrkCtrMap.getWrkCtrCd());
                                                    String plntCd4T3 = checkNull4Str(wrkCtrMap.getPlntCd());
                                                    if (StringInner.isStringNotEmpty(srcSysCd) && StringInner.isStringNotEmpty(wrkCtrNum)
                                                            && StringInner.isStringNotEmpty(wrkCtrTypeCd)) {
//                                                        LogUtil.getCoreLog().info("----------------wrkCtr >>wrkCtrNum:{},wrkCtrTypeCd{}", wrkCtrNum, wrkCtrTypeCd);
                                                        List<EDMWrkCtrCapyEntity> wrkCtrCapyMapLst = joinWrkCtrCapy(srcSysCd, wrkCtrNum, wrkCtrTypeCd);
                                                        if (wrkCtrCapyMapLst != null && wrkCtrCapyMapLst.size() > 0) {
                                                            EDMCapyHdrEntity capyHdr1 = null;
                                                            EDMCapyHdrEntity capyHdr2 = null;
                                                            for (EDMWrkCtrCapyEntity itm : wrkCtrCapyMapLst) {
                                                                String capyNum = itm.getCapyNum();
                                                                srcSysCd = itm.getSrcSysCd();
//                                                                LogUtil.getCoreLog().info("----------------EDMWrkCtrCapyEntity >>capyNum{}", capyNum);
                                                                if (StringInner.isStringNotEmpty(srcSysCd) && StringInner.isStringNotEmpty(capyNum)) {
                                                                    EDMCapyHdrEntity capyHdrMap = joinCapyHdr4T3(srcSysCd, capyNum);
                                                                    if (capyHdrMap != null) {
                                                                        capyHdr1 = capyHdrMap;
                                                                    } else {
                                                                        capyHdrMap = joinCapyHdr4T7(srcSysCd, capyNum);
                                                                        if (capyHdrMap != null) {
                                                                            capyHdr2 = capyHdrMap;
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                            if (capyHdr1 == null) {
//                                                                LogUtil.getCoreLog().info("----------------filter capyHdr1 null>>capyHdr1{}", capyHdr1);
                                                                FailData failData = new FailData();
                                                                failData.setErrorCode("T3");
                                                                failData.setErrorValue("Unable find the WC");
                                                                failData.setFunctionalArea("PP");
                                                                failData.setInterfaceID("OMPGdmStepResource");
                                                                failData.setSourceSystem("omp");
                                                                failData.setKey1(srcSysCd);
                                                                failData.setKey2(mfgOrdrNum);
                                                                failData.setKey3("");
                                                                failData.setKey4("");
                                                                failData.setKey5("");
                                                                ResultObject resultObj = new ResultObject();
                                                                resultObj.setFailData(failData);
                                                                RawDataBuilder rawDataBuilder = new RawDataBuilder();
                                                                rawDataBuilder.put(resultObj.getFailData().toMap());
                                                                failMap.put(failData.getKey(), rawDataBuilder);
                                                                LogUtil.getCoreLog().info("Unable find the WC");
                                                                continue;
                                                            }
                                                            if (capyHdr2 == null) {
                                                                continue;
                                                            } else {
                                                                String capyNm = checkNull4Str(capyHdr2.getCapyNm());
                                                                String plntCd4capyHdr = checkNull4Str(capyHdr2.getPlntCd());
                                                                RawDataBuilder dataRaw = new RawDataBuilder();
                                                                String machineId = StringInner.join(srcSysCd, "_", plntCd4T3, "/", wrkCtrCd);
                                                                String operationId = StringInner.join("PRO/", String.valueOf(Long.parseLong(mfgOrdrNum)), "/", operNum);
                                                                String resourceId = StringInner.join(srcSysCd, "_", plntCd4capyHdr, "/", capyNm);
                                                                String stepResourceId = StringInner.join("PRO/", resourceId, "/", wrkCtrCd, "/", String.valueOf(Long.parseLong(mfgOrdrNum)), "/", operNum);

                                                                dataRaw.put("stepResourceId", stepResourceId);
                                                                dataRaw.put("machineId", machineId);
                                                                dataRaw.put("minQuantity", minQuantity);
                                                                dataRaw.put("operationId", operationId);
                                                                dataRaw.put("resourceId", resourceId);
                                                                dataRaw.put("active", yesDefault);
                                                                dataRaw.put("activeOPRERP", yesDefault);
                                                                dataRaw.put("activeSOPERP", noDefault);
                                                                dataRaw.put("quantity", zeroDefault);
                                                                dataRaw.put("stepResourceType", prodStr);
                                                                rawDataBuilderList.add(dataRaw);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } else {
//                                    LogUtil.getCoreLog().info("-----------------------filter >>mfgOrderRtngMapmLst:{}", mfgOrderRtngMapmLst);
                                    return false;
                                }
                            } else {
//                                LogUtil.getCoreLog().info("--------------------filter >>matlProdVersnEtt:{}", matlProdVersnEtt);
                                return false;
                            }
                        } else {
//                            LogUtil.getCoreLog().info("-------------------filter >>prdntVrsnNum:{},matlNum{}", prdntVrsnNum, matlNum);
                            return false;
                        }
                    } else {
//                        LogUtil.getCoreLog().info("----------------------filter >>mfgOrderItmEtt:{}", mfgOrderItmEtt);
                        return false;
                    }
                } else {
//                    LogUtil.getCoreLog().info("---------------filter mainRegion>>mfgOrdrNum:{},ordrRtngNum:{}", mfgOrdrNum, ordrRtngNum);
                    return false;
                }
            } else {
//                LogUtil.getCoreLog().info("-------------filter CnsPlanParameter1-5>>sourceSysCd:{},plntCd:{},mfgOrdrTypCd:{},actRlseDt:{},mfgOrdrSttsCd:{}", sourceSysCd, plntCd, mfgOrdrTypCd, actRlseDt, mfgOrdrSttsCd);
                return false;
            }
        } else {
//            LogUtil.getCoreLog().info("-------------filter mainRegion field null>>sourceSysCd:{},plntCd:{},mfgOrdrTypCd:{},actRlseDt:{},mfgOrdrSttsCd:{}", sourceSysCd, plntCd, mfgOrdrTypCd, actRlseDt, mfgOrdrSttsCd);
            return false;
        }

        return true;
    }

    public Boolean checkCnsPlanParameter4J1_1(String sourceSysCdParam) {
        String sourceSystem = "CONS_LATAM";
        String dataObject = "ALL";
        String attribute = "SEND_TO_OMP";
        String parameter = "SYSTEMNAME";
        String inclExcl = "I";
        String parameterValue = checkStrNull4Num(sourceSysCdParam);
        List<PlanCnsPlanParameterEntity> retList = planCnsPlanParameterDao.getEntriessWithPKConditions(sourceSystem, dataObject, attribute,
                parameter, parameterValue, inclExcl);
        if (retList != null && retList.size() > 0) {
//            LogUtil.getCoreLog().info("------------->>checkCnsPlanParameter4J1_1");
            return true;
        }
        return false;
    }

    public Boolean checkCnsPlanParameter4J1_2(String sourceSysCdParam, String plntCd) {
        String sourceSystem = checkNull4Str(sourceSysCdParam);
        String dataObject = "pp";
        String attribute = "SEND_TO_OMP";
        String parameter = "PLANT";
        String inclExcl = "I";
        List<PlanCnsPlanParameterEntity> retList = planCnsPlanParameterDao.getEntriessWithPKConditions(sourceSystem, dataObject,
                attribute, parameter, checkStrNull4Num(plntCd), inclExcl);
        if (retList != null && retList.size() > 0) {
//            LogUtil.getCoreLog().info("------------->>checkCnsPlanParameter4J1_2");
            return true;
        }
        return false;
    }

    public Boolean checkCnsPlanParameter4J1_3(String sourceSysCdParam, String mfgOrdrTypCd) {
        String sourceSystem = checkNull4Str(sourceSysCdParam);
        String dataObject = "pp";
        String attribute = "SEND_TO_OMP";
        String parameter = "ORDERTYPE";
        String inclExcl = "I";
        List<PlanCnsPlanParameterEntity> retList = planCnsPlanParameterDao.getEntriessWithPKConditions(sourceSystem, dataObject,
                attribute, parameter, checkStrNull4Num(mfgOrdrTypCd), inclExcl);
        if (retList != null && retList.size() > 0) {
//            LogUtil.getCoreLog().info("------------->>checkCnsPlanParameter4J1_3");
            return true;
        }
        return false;
    }

    public Boolean checckCnsPlanParameter4J1_4(String sourceSysCdParam, String actRlseDt) {
        Boolean ret = false;
        String sourceSystem = checkNull4Str(sourceSysCdParam);
        String dataObject = "PP";
        String attribute = "SEND_TO_OMP";
        String parameter = "RELDAYS";
        String inclExcl = "I";
        PlanCnsPlanParameterEntity retEntity = planCnsPlanParameterDao.getEntityWithConditions(sourceSystem, dataObject, attribute, parameter, inclExcl);
        if (retEntity != null) {
            String paramValue = checkNull4Str(retEntity.getParameterValue());
            if (checkDateBtwRegion(actRlseDt, paramValue)) {
                ret = true;
//                LogUtil.getCoreLog().info("------------->>checkCnsPlanParameter4J1_4");
            }
        }
        return ret;
    }

    public Boolean checkCnsPlanParameter4J1_51(String sourceSysCdParam, String mfgOrdrSttsCd) {

        String sourceSystem = checkNull4Str(sourceSysCdParam);
        String dataObject = "PP";
        String attribute = "SEND_TO_OMP";
        String parameter = "ORDERSTAT";
        String inclExcl = "I";
        List<PlanCnsPlanParameterEntity> retEntityLst = planCnsPlanParameterDao.getEntitiesWithConditions(sourceSystem, dataObject, attribute, parameter, inclExcl);
        if (retEntityLst != null && retEntityLst.size() > 0) {
            for (PlanCnsPlanParameterEntity retEntity : retEntityLst) {
                String paramValue = retEntity.getParameterValue();
//                LogUtil.getCoreLog().info("----------------4 J1-51>>paramValue:{}", paramValue);
                if (StringInner.isStringNotEmpty(paramValue) && mfgOrdrSttsCd.contains(paramValue)) {
//                    LogUtil.getCoreLog().info("------------->>checkCnsPlanParameter4J1_51");
                    return true;
                }
            }
        }
        return false;
    }

    public Boolean checkCnsPlanParameter4J1_52(String sourceSysCdParam, String mfgOrdrSttsCd) {
        String sourceSystem = checkNull4Str(sourceSysCdParam);
        String dataObject = "PP";
        String attribute = "SEND_TO_OMP";
        String parameter = "ORDERSTAT";
        String inclExcl = "E";
        List<PlanCnsPlanParameterEntity> retEntityLst = planCnsPlanParameterDao.getEntitiesWithConditions(sourceSystem, dataObject, attribute, parameter, inclExcl);
        if (retEntityLst != null && retEntityLst.size() > 0) {
            for (PlanCnsPlanParameterEntity retEntity : retEntityLst) {
                String paramValue = retEntity.getParameterValue();
//                LogUtil.getCoreLog().info("----------------4 J1-52>>paramValue:{}", paramValue);
                if (StringInner.isStringNotEmpty(paramValue) && mfgOrdrSttsCd.contains(paramValue)) {
                    return false;
                }
            }
        }

//        LogUtil.getCoreLog().info("------------->>checkCnsPlanParameter4J1_52");
        return true;
    }

//    public Boolean checkCnsPlantAttr(String sourceSysCd, String plntCd) {
//        String localPlanningRelevant = "X";
//        String localPlant = checkNull4Str(plntCd);
//        String sourceSystem = checkNull4Str(sourceSysCd);
//        List<PlanCnsPlantAttrEntity> retList = planCnsPlantAttrDao.getEntitiesWithLocPltAndSrcSysAndlocPlnRlvnt(localPlant,sourceSystem,localPlanningRelevant);
//        if (retList != null && retList.size() > 0) {
//            for (PlanCnsPlantAttrEntity itm: retList) {
//                String param = itm.getPlanLocTypeId();
//                if(StringInner.isStringNotEmpty(param) && checkCnsPlanParameter4J1_6(sourceSysCd,param)){
////                    LogUtil.getCoreLog().info("------------->>checkCnsPlantAttr");
//                    return true;
//                }
//            }
//        }
//
//        return false;
//
//    }

    public EDMMfgOrderItmEntity joinMfgOrderItm(String sourceSysCdParam, String mfgOrdrNumParam, String plntCd) {
        EDMMfgOrderItmEntity edmMfgOrderItmEntity = edmMfgOrderItmDao.getEntityWithSrcSysAndOrdrNumAndPlntCd(checkNull4Str(sourceSysCdParam), checkNull4Str(mfgOrdrNumParam), checkNull4Str(plntCd));
        if (edmMfgOrderItmEntity != null) {
//            LogUtil.getCoreLog().info("------------->>joinMfgOrderItm");
            return edmMfgOrderItmEntity;
        }
        return null;
    }

    public Boolean checkMaterialPlanStatus(String srcSysCd, String maltNum, String pltCd){
        Boolean ret = true;

        PlanCnsMaterialPlanStatusEntity materialPlanStatusEtt = materialPlanStatusDao.getCnsMaterialPlanStatusDaoEntity(srcSysCd,maltNum,pltCd);
        if(materialPlanStatusEtt == null){
            ret = false;
        }

        return ret;
    }

    public EDMMatlProdVersnEntity joinMaltProdVersn(String srcSysCd, String matlNum,
                                                    String plntCd, String prdntVrsnNum) {

        List<EDMMatlProdVersnEntity> retList = edmMatlProdVersnDao.getEntityWithConditions(checkNull4Str(srcSysCd), checkNull4Str(matlNum), checkNull4Str(plntCd), checkStrNull4Num(prdntVrsnNum));
        if (retList != null && retList.size() > 0) {
//            LogUtil.getCoreLog().info("------------->>joinMaltProdVersn");
            EDMMatlProdVersnEntity entry = retList.get(0);
            return entry;
        }
        return null;
    }

    public EDMMfgRtngItmNdeEntity joinMfgRtngItmNde(String srcSysCd, String rtngTypCd,
                                                    String rntgGrpCd, String rtngGrpCntrNbr) {

        List<EDMMfgRtngItmNdeEntity> retList = edmMfgRtngItmNdeDao.getEntityWithConditions(checkNull4Str(srcSysCd),
                checkNull4Str(rtngTypCd), checkNull4Str(rntgGrpCd), checkNull4Str(rtngGrpCntrNbr));
        if (retList != null && retList.size() > 0) {
//            LogUtil.getCoreLog().info("------------->>joinMfgRtngItmNde");
            EDMMfgRtngItmNdeEntity entry = retList.get(0);
            return entry;
        }

        return null;
    }

    public EDMMfgRtgParmEntity joinMfgRtgParm(String srcSysCd, String rtgTypeCd,
                                              String rtgGrpCd, String rtgNodeNum) {

        String srcSysCdParm = checkNull4Str(srcSysCd);
        String rtgTypeCdParm = checkNull4Str(rtgTypeCd);
        String rtgGrpCdParm = checkNull4Str(rtgGrpCd);
        String rtgNodeNumParm = checkNull4Str(rtgNodeNum);
        String charCd = "EQUIPE";
        List<EDMMfgRtgParmEntity> edmMfgRtgParmEntities = edmMfgRtgParmDao.getEntityWithConditions(srcSysCdParm, rtgTypeCdParm, rtgGrpCdParm, rtgNodeNumParm, charCd);
        if (edmMfgRtgParmEntities != null && edmMfgRtgParmEntities.size() > 0) {
            edmMfgRtgParmEntities.sort((e1, e2) -> checkNull4Str(e1.getIntrnlPrcsInstrNum())
                    .compareTo(checkNull4Str(e2.getIntrnlPrcsInstrNum())));
//            LogUtil.getCoreLog().info("-------------MfgRtgParmLst>>"+edmMfgRtgParmEntities.size());
            Integer len = edmMfgRtgParmEntities.size();
            EDMMfgRtgParmEntity entry = edmMfgRtgParmEntities.get(len - 1);

//            LogUtil.getCoreLog().info("-------------MfgRtgParm>>getCharVal=" +entry.getCharVal());
            return entry;
        }

        return null;

    }

    public List<EDMMfgOrderRtngEntity> joinMfgOrderRtng(String srcSysCdParam, String ordrRtngNumParam) {
        List<EDMMfgOrderRtngEntity> retList = edmMfgOrderRtngDao.getEntityWithConditions(checkNull4Str(srcSysCdParam), checkNull4Str(ordrRtngNumParam));
        if (retList != null && retList.size() > 0) {
//            LogUtil.getCoreLog().info("------------->>joinMfgOrderRtng");
            return retList;
        }

        return new ArrayList<>();
    }

    public Boolean checkT430(String operCdParam) {
        T430Entity retEtt = projectOneT430Dao.getEntityWithPK(checkNull4Str(operCdParam), "X");
        if (retEtt != null) {

//            LogUtil.getCoreLog().info("------------->>checkT430");
            return true;
        }
        return false;
    }

    public EDMWrkCtrEntity joinWrkCtr(String srcSysCd, String wrkCtrNum) {
        List<EDMWrkCtrEntity> edmWrkCtrEntities = edmWrkCtrDao.getEntityWithwrkCtrUsgCd(checkNull4Str(srcSysCd), checkStrNull4Num(wrkCtrNum));
        if (edmWrkCtrEntities != null && edmWrkCtrEntities.size() > 0) {

//            LogUtil.getCoreLog().info("------------->>joinWrkCtr");
            return edmWrkCtrEntities.get(0);
        }
        return null;
    }

    public List<EDMWrkCtrCapyEntity> joinWrkCtrCapy(String srcSysCd, String wrkCtrNum, String wrkCtrTypeCd) {
        List<EDMWrkCtrCapyEntity> edmWrkCtrCapyEntities = edmWrkCtrCapyDao.getEntityWithwrkCtrUsgCd(checkNull4Str(srcSysCd), checkNull4Str(wrkCtrNum), checkNull4Str(wrkCtrTypeCd));
        if (edmWrkCtrCapyEntities != null && edmWrkCtrCapyEntities.size() > 0) {

//            LogUtil.getCoreLog().info("------------->>joinWrkCtrCapy");
            return edmWrkCtrCapyEntities;
        }

        return new ArrayList<>();
    }

    public EDMCapyHdrEntity joinCapyHdr4T3(String srcSysCd, String capyNum) {
        List<EDMCapyHdrEntity> edmCapyHdrEntities = edmCapyHdrDao.getEntityWithwrkCtrUsgCd(checkNull4Str(srcSysCd), checkNull4Str(capyNum), "001");
        if (edmCapyHdrEntities != null && edmCapyHdrEntities.size() > 0) {
            EDMCapyHdrEntity entry = edmCapyHdrEntities.get(0);
//            LogUtil.getCoreLog().info("------------->>joinCapyHdr4T3");
            return entry;
        }

        return null;
    }

    public EDMCapyHdrEntity joinCapyHdr4T7(String srcSysCd, String capyNum) {
        List<EDMCapyHdrEntity> edmCapyHdrEntities = edmCapyHdrDao.getEntityWithwrkCtrUsgCd(checkNull4Str(srcSysCd), checkNull4Str(capyNum), "002");
        if (edmCapyHdrEntities != null && edmCapyHdrEntities.size() > 0) {
            EDMCapyHdrEntity entry = edmCapyHdrEntities.get(0);
//            LogUtil.getCoreLog().info("------------->>joincapyhdr4t7");
            return entry;
        }

        return null;

    }

    private Boolean checkDateBtwRegion(String actRlseDt, String parameterValue) {
        Boolean ret = true;
        String paramActRlseDt = checkStrNull4Num(actRlseDt);
        String paramValue = checkStrNull4Num(parameterValue);

        String currentDate = DateUtils.currentDate(DateUtils.F_yyyyMMdd);
        Date fromDa = DateUtils.stringToDate(currentDate, DateUtils.F_yyyyMMdd);
        Date paramDa = DateUtils.stringToDate(paramActRlseDt, DateUtils.F_yyyyMMdd);

        try {
            Integer offsetDay = Integer.parseInt(paramValue);
            Date toDa = DateUtils.offsetDate(fromDa, -offsetDay);
            if (toDa.compareTo(fromDa) > 0) {
                if (paramDa.getTime() < fromDa.getTime() || paramDa.getTime() > toDa.getTime()) {
                    ret = false;
                }
            } else {
                if (paramDa.getTime() > fromDa.getTime() || paramDa.getTime() < toDa.getTime()) {
                    ret = false;
                }
            }
        } catch (NumberFormatException ex) {
            LogUtil.getCoreLog().error(ex.getMessage());
            ret = false;
        }
        return ret;
    }

    public String checkStrNull4Num(String str) {
        return StringUtils.isEmpty(str) || StringUtils.equals("null", str) ? "0" : str;
    }

    public String checkNull4Str(String str) {
        return StringUtils.isEmpty(str) || StringUtils.equals("null", str) ? "" : str;
    }
}
