package com.jnj.pangea.omp.gdm_bom_element_Process.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.*;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsMaterialPlanStatusDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanParameterDaoImpl;
import com.jnj.pangea.common.entity.edm.*;
import com.jnj.pangea.common.entity.plan.PlanCnsMaterialPlanStatusEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanParameterEntity;
import com.jnj.pangea.common.service.ICommonListService;
import com.jnj.pangea.omp.gdm_bom_element_Process.bo.OMPGdmbomelementBo;
import com.jnj.pangea.util.DateUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class OMPGdmbomelementServiceImpl implements ICommonListService {

    private static OMPGdmbomelementServiceImpl instance;

    public static OMPGdmbomelementServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmbomelementServiceImpl();
        }
        return instance;
    }

    java.text.DecimalFormat df = new java.text.DecimalFormat(IConstant.OMP_GDMBOMELEMENT.PATTERN_DECIMAL_3);

    private EDMMfgOrderItmDaoImpl mfgOrderItmDao = EDMMfgOrderItmDaoImpl.getInstance();
    private EDMReservItmDaoImpl reservItmDao = EDMReservItmDaoImpl.getInstance();
    private EDMMfgOrderRtngDaoImpl mfgOrderRtngDao = EDMMfgOrderRtngDaoImpl.getInstance();
    private EDMMfgOrderSeqDaoImpl mfgOrderSeqDao = EDMMfgOrderSeqDaoImpl.getInstance();
    private EDMMatlProdVersnDaoImpl matlProdVersnDao = EDMMatlProdVersnDaoImpl.getInstance();
    private PlanCnsPlanParameterDaoImpl planCnsPlanParameterDao = PlanCnsPlanParameterDaoImpl.getInstance();
    private EDMBomItemDaoImpl edmBomItemDao = EDMBomItemDaoImpl.getInstance();
    private EDMMaterialGlobalV1DaoImpl edmMaterialGlobalV1Dao=EDMMaterialGlobalV1DaoImpl.getInstance();
    private PlanCnsMaterialPlanStatusDaoImpl planCnsMaterialPlanStatusDao= PlanCnsMaterialPlanStatusDaoImpl.getInstance();
    @Override
    public List<ResultObject> buildView(String key, Object o, Object o2) {

        List<ResultObject> result = new ArrayList<ResultObject>();
        EDMMfgOrderEntity mfgOrderEntity = (EDMMfgOrderEntity) o;
        Calendar calendar = Calendar.getInstance();
        String nowDate = DateUtils.dateToString(calendar.getTime(), DateUtils.F_yyyyMMdd);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        String actRlseDt = mfgOrderEntity.getActRlseDt();
        String beforeDate = DateUtils.dateToString(calendar.getTime(), DateUtils.F_yyyyMMdd);
        if (StringUtils.isBlank(actRlseDt) || (!actRlseDt.equals(nowDate) && !actRlseDt.equals(beforeDate))) {
            return null;
        }
        String sourceSysCd = mfgOrderEntity.getSourceSysCd();
        String mfgOrdrNum = mfgOrderEntity.getMfgOrdrNum();
        String rsrvtnNum = mfgOrderEntity.getRsrvtnNum();
        String ordrRtngNum = mfgOrderEntity.getOrdrRtngNum();

        PlanCnsPlanParameterEntity planCnsPlanParameterOne = planCnsPlanParameterDao.getEntityWithAttributeListForLFUOne(sourceSysCd);
        //get /plan/cns_plan_parameter
        PlanCnsPlanParameterEntity planCnsPlanParameterEntity = planCnsPlanParameterDao.getEntityWithAttribute(sourceSysCd, sourceSysCd);
        if (planCnsPlanParameterEntity == null) {
            return null;
        }
        //get MFG_ORDER_ITM
        List<EDMMfgOrderItmEntity> listMFG_ORDER_ITM = new ArrayList<>();
        List<EDMMfgOrderItmEntity> listMFG_ORDER_ITMTemp = mfgOrderItmDao.getEntityWithConditions(sourceSysCd, mfgOrdrNum);
        if (listMFG_ORDER_ITMTemp == null || listMFG_ORDER_ITMTemp.size() <= 0) {
            return null;
        }
        //get data MATL_PROD_VERSN
        //LogUtil.getCoreLog().info("listMFG_ORDER_ITMTemp" + listMFG_ORDER_ITMTemp.get(0));
        List<EDMMatlProdVersnEntity> listmatlProdVersn = new ArrayList<>();
        for (int i = 0; i < listMFG_ORDER_ITMTemp.size(); i++) {
            EDMMfgOrderItmEntity edmMfgOrderItmEntity = listMFG_ORDER_ITMTemp.get(i);
            List<EDMMatlProdVersnEntity> list = matlProdVersnDao.getEntityWithConditions(edmMfgOrderItmEntity.getSrcSysCd(), edmMfgOrderItmEntity.getMatlNum(), edmMfgOrderItmEntity.getPlntCd(), edmMfgOrderItmEntity.getPrdntVrsnNum());
            if (list != null && list.size() > 0) {
                listmatlProdVersn.addAll(list);
                listMFG_ORDER_ITM.add(edmMfgOrderItmEntity);
            }
        }
        if (listmatlProdVersn.size() == 0) {
            return null;
        }
       //LogUtil.getCoreLog().info("listMFG_ORDER_ITM" + listMFG_ORDER_ITM.get(0));

        //get data RESERV_ITM
        List<EDMReservItmEntity> listEDMReservItm = reservItmDao.getEntityWithConditions(sourceSysCd, rsrvtnNum);
        if (listEDMReservItm == null || listEDMReservItm.size() == 0) {
            return null;
        }
         //LogUtil.getCoreLog().info("listEDMReservItm" + listEDMReservItm.get(0));

        EDMBomItemEntity edmBomItemEntity = edmBomItemDao.getEntityWithConditions(sourceSysCd, mfgOrderEntity.getBomCatCd(), mfgOrderEntity.getBomNum());
//        if (edmBomItemEntity != null) {
//            LogUtil.getCoreLog().info("planCnsPlanParameterEntity" + edmBomItemEntity.toString());
//        }
//        // get data  from
        List<EDMMfgOrderRtngEntity> listEDMMfgOrderRtng = new ArrayList<>();
        List<EDMMfgOrderRtngEntity> listEDMMfgOrderRtngTemp = mfgOrderRtngDao.getEntityWithConditions(sourceSysCd, ordrRtngNum);
        if (listEDMMfgOrderRtngTemp == null || listEDMMfgOrderRtngTemp.size() == 0) {
            return null;
        }
        //LogUtil.getCoreLog().info("listEDMMfgOrderRtngTemp" + listEDMMfgOrderRtngTemp.toString());
        //he
        List<EDMMfgOrderSeqEntity> listEDMMfgOrderSeq = new ArrayList<>();
        for (int i = 0; i < listEDMMfgOrderRtngTemp.size(); i++) {
            EDMMfgOrderRtngEntity edmMfgOrderRtngEntity = listEDMMfgOrderRtngTemp.get(i);
            List<EDMMfgOrderSeqEntity> list = mfgOrderSeqDao.getEntityWithConditions(edmMfgOrderRtngEntity.getSrcSysCd(), edmMfgOrderRtngEntity.getOrdrRtngNum());
            if (list != null && list.size() > 0) {
                listEDMMfgOrderSeq.addAll(list);
                listEDMMfgOrderRtng.add(edmMfgOrderRtngEntity);
            }
        }
        if (listEDMMfgOrderRtng.size() == 0) {
            return null;
        }
        if (listEDMMfgOrderSeq.size() == 0) {
            return null;
        }
      //  LogUtil.getCoreLog().info("listEDMMfgOrderRtng" + listEDMMfgOrderRtng.toString());
        //  LogUtil.getCoreLog().info("listEDMMfgOrderSeq" + listEDMMfgOrderSeq.toString());

        //get data done
        result.addAll(setCommonRule(listmatlProdVersn,edmBomItemEntity, planCnsPlanParameterOne, mfgOrderEntity, listMFG_ORDER_ITM, listEDMReservItm, listEDMMfgOrderRtng, listEDMMfgOrderSeq));
        result.addAll(setFORPRODUCEDMATRule(listmatlProdVersn, planCnsPlanParameterOne, mfgOrderEntity, listMFG_ORDER_ITM, listEDMReservItm, listEDMMfgOrderRtng, listEDMMfgOrderSeq));
        return result;
    }

    public List<ResultObject> setCommonRule( List<EDMMatlProdVersnEntity> listmatlProdVersn,EDMBomItemEntity edmBomItemEntity, PlanCnsPlanParameterEntity planCnsPlanParameterOne, EDMMfgOrderEntity mfgOrderEntity, List<EDMMfgOrderItmEntity> listMFG_ORDER_ITM, List<EDMReservItmEntity> listEDMReservItm, List<EDMMfgOrderRtngEntity> listEDMMfgOrderRtng, List<EDMMfgOrderSeqEntity> listEDMMfgOrderSeq) {
        List<ResultObject> list = new ArrayList<>();
        EDMMfgOrderItmEntity edmMfgOrderItmEntity = listMFG_ORDER_ITM.get(listMFG_ORDER_ITM.size() - 1);
        String SumRcvdQty = getSumRcvdQty(listMFG_ORDER_ITM);
        final String key_split = IConstant.OMP_GDMBOMELEMENT.KEY_SPLIT;
        for (int i = 0; i < listEDMMfgOrderRtng.size(); i++) {
            EDMMfgOrderRtngEntity edmMfgOrderRtngEntity = listEDMMfgOrderRtng.get(i);

            for (int j = 0; j < listEDMMfgOrderSeq.size(); j++) {
                EDMMfgOrderSeqEntity edmMfgOrderSeqEntity = listEDMMfgOrderSeq.get(j);

                for (int k = 0; k < listEDMReservItm.size(); k++) {
                    EDMReservItmEntity edmReservItmEntity = listEDMReservItm.get(k);
                    OMPGdmbomelementBo ompGdmbomelementBo = new OMPGdmbomelementBo();
                    ResultObject resultObject = new ResultObject();
                    StringBuffer sb = new StringBuffer();
                    sb.append(IConstant.OMP_GDMBOMELEMENT.FRONT_BOMID_PRO_UP).append(key_split);
                    sb.append(checkNullDel0(mfgOrderEntity.getSourceSysCd()));
                    sb.append(checkNullDel0(mfgOrderEntity.getMfgOrdrNum()));
                    sb.append(checkNullDel0(edmMfgOrderSeqEntity.getRtngSqncNum()));
                    sb.append(checkNullDel0(edmMfgOrderRtngEntity.getOperNum()));
                    sb.append(checkNullDel0(edmReservItmEntity.getMatlNum()));
                    sb.append(checkNullDel0(edmReservItmEntity.getBomItmNum()));
                    sb.append(checkNullDel0End(edmReservItmEntity.getBtchNum()));
                    ompGdmbomelementBo.setBomElementId(sb.toString());
                    ompGdmbomelementBo.setActive(IConstant.OMP_GDMBOMELEMENT.DEFAULT_VALUE_YES);
                    ompGdmbomelementBo.setActiveFCTERP(IConstant.OMP_GDMBOMELEMENT.DEFAULT_VALUE_YES);
                    ompGdmbomelementBo.setActiveOPRERP(IConstant.OMP_GDMBOMELEMENT.DEFAULT_VALUE_YES);
                    ompGdmbomelementBo.setActiveSOPERP(IConstant.OMP_GDMBOMELEMENT.DEFAULT_VALUE_NO);
                    ompGdmbomelementBo.setBatchId(edmReservItmEntity.getBtchNum());
                    sb = new StringBuffer();
                    sb.append(IConstant.OMP_GDMBOMELEMENT.FRONT_BOMID_PRO_UP).append(key_split);
                    sb.append(checkNullDel0End(planCnsPlanParameterOne.getParameterValue())).append(IConstant.OMP_GDMBOMELEMENT.__SPLIT);
                    sb.append(checkNullDel0(mfgOrderEntity.getMfgOrdrNum()));
                    sb.append(checkNullDel0(mfgOrderEntity.getOrdrRtngNum()));
                    sb.append(checkNullDel0End(edmMfgOrderRtngEntity.getOperNum()));
                    ompGdmbomelementBo.setBOMId(sb.toString());
                    //BOMType
                    if (edmBomItemEntity != null) {
                        if (StringUtils.isBlank(edmBomItemEntity.getDstrbtnKeyCd())) {
                            ompGdmbomelementBo.setBOMType(IConstant.OMP_GDMBOMELEMENT.BOM_ITEM.FIELD_DSTRBTNKEYCD_BLACK_VALUE);
                        } else {
                            if (edmBomItemEntity.getDstrbtnKeyCd().equalsIgnoreCase(IConstant.OMP_GDMBOMELEMENT.BOM_ITEM.FIELD_DSTRBTNKEYCD_NAME)) {
                                ompGdmbomelementBo.setBOMType(IConstant.OMP_GDMBOMELEMENT.BOM_ITEM.FIELD_DSTRBTNKEYCD_VALUE);
                            } else {
                                ompGdmbomelementBo.setBOMType(IConstant.OMP_GDMBOMELEMENT.BOM_ITEM.FIELD_DSTRBTNKEYCD_ELSE_VALUE);
                            }
                        }
                    } else {
                        ompGdmbomelementBo.setBOMType(IConstant.OMP_GDMBOMELEMENT.BOM_ITEM.FIELD_DSTRBTNKEYCD_ELSE_VALUE);
                    }
                    //BOMUsage
                    String QtyFxInd = edmReservItmEntity.getQtyFxInd();
                    if (StringUtils.isBlank(QtyFxInd)) {
                        ompGdmbomelementBo.setBOMUsage(IConstant.OMP_GDMBOMELEMENT.QTYFXIND_BLACK);
                    } else if (QtyFxInd.equalsIgnoreCase(IConstant.OMP_GDMBOMELEMENT.QTYFXIND_X)) {
                        ompGdmbomelementBo.setBOMUsage(IConstant.OMP_GDMBOMELEMENT.QTYFXIND_X_VALUE);
                    } else {
                        ompGdmbomelementBo.setBOMUsage(IConstant.OMP_GDMBOMELEMENT.BOM_ITEM.FIELD_DSTRBTNKEYCD_ELSE_VALUE);
                    }
                    ompGdmbomelementBo.setComments(IConstant.OMP_GDMBOMELEMENT.LEAVE_BLACK);
                    ompGdmbomelementBo.setOffsetCalendarId(IConstant.OMP_GDMBOMELEMENT.LEAVE_BLACK);
                    ompGdmbomelementBo.setOffsetPercentage(IConstant.OMP_GDMBOMELEMENT.LEAVE_BLACK);
                    ompGdmbomelementBo.setOffsetPercType(IConstant.OMP_GDMBOMELEMENT.LEAVE_BLACK);
                    ompGdmbomelementBo.setEndEff(IConstant.OMP_GDMBOMELEMENT.ENDEFF);
                    ompGdmbomelementBo.setStartEff(IConstant.OMP_GDMBOMELEMENT.STARTEFF);
                    ompGdmbomelementBo.setERPFeedbackQuantity(SumRcvdQty);
                    //Concatenate MFG_ORDER_ITM-srcSysCd, "_", MFG_ORDER_ITM-plntCd

                    //                   RESERV_ITM- leadTimeOffset * 864000
                    String leadTimeOffset = edmReservItmEntity.getLeadTimeOffset();
                    StringToOther(ompGdmbomelementBo, leadTimeOffset, edmReservItmEntity.getRqmtQty(), edmReservItmEntity.getWthdrnQty());
                    String qtyFxInd = edmReservItmEntity.getQtyFxInd();


                    ompGdmbomelementBo.setLocationId(edmMfgOrderItmEntity.getSrcSysCd() + IConstant.OMP_GDMBOMELEMENT.__SPLIT + edmMfgOrderItmEntity.getPlntCd());
                    EDMMaterialGlobalV1Entity edmMaterialGlobalV1Entity= edmMaterialGlobalV1Dao.getEntityWithLocalMaterialNumberAndSourceSystem(edmReservItmEntity.getMatlNum(),mfgOrderEntity.getSourceSysCd());
                    if(edmMaterialGlobalV1Entity==null){
                        FailData failData=new FailData(IConstant.FAILDATA.NA,IConstant.OMP_GDMBOMELEMENT.INTERFACEID,IConstant.FAILDATA.ERRORCODE,IConstant.OMP_GDMBOMELEMENT.PRODUCTID_FAIL_MSG,mfgOrderEntity.getSourceSysCd(),mfgOrderEntity.getMfgOrdrNum(),edmReservItmEntity.getMatlNum());
                        resultObject.setFailData(failData);
                    }else{
                        PlanCnsMaterialPlanStatusEntity planCnsMaterialPlanStatusEntity=null;
                        for (EDMMatlProdVersnEntity edmMatlProdVersnEntity:listmatlProdVersn ){
                            planCnsMaterialPlanStatusEntity=  planCnsMaterialPlanStatusDao.getPlanCnsMaterialPlanStatusEntity4251(mfgOrderEntity.getSourceSysCd(),edmReservItmEntity.getMatlNum(),edmMatlProdVersnEntity.getPlntCd());
                            if(planCnsMaterialPlanStatusEntity!=null){
                                break;
                            }
                        }

                        if(planCnsMaterialPlanStatusEntity==null){
                            FailData failData=new FailData(IConstant.FAILDATA.NA,IConstant.OMP_GDMBOMELEMENT.INTERFACEID,IConstant.FAILDATA.ERRORCODE,IConstant.OMP_GDMBOMELEMENT.PRODUCTID_FAIL_MSG,mfgOrderEntity.getSourceSysCd(),mfgOrderEntity.getMfgOrdrNum(),edmReservItmEntity.getMatlNum());
                            resultObject.setFailData(failData);
                        }else{
                            String NoPlanRelevant=planCnsMaterialPlanStatusEntity.getNoPlanRelevant();
                            if(StringUtils.isNotBlank(NoPlanRelevant)&&NoPlanRelevant.trim().equals(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.FIELD_X)){
                                ompGdmbomelementBo.setProductId(edmMaterialGlobalV1Entity.getPrimaryPlanningCode());
                                resultObject.setBaseBo(ompGdmbomelementBo);
                            }else{
                                if(planCnsPlanParameterOne!=null){
                                    ompGdmbomelementBo.setProductId(planCnsPlanParameterOne.getParameterValue()+IConstant.OMP_GDMBOMELEMENT.__SPLIT+edmMaterialGlobalV1Entity.getLocalDpParentCode());
                                    resultObject.setBaseBo(ompGdmbomelementBo);
                                }else{
                                    FailData failData=new FailData(IConstant.FAILDATA.NA,IConstant.OMP_GDMBOMELEMENT.INTERFACEID,IConstant.FAILDATA.ERRORCODE,IConstant.OMP_GDMBOMELEMENT.PRODUCTID_FAIL_MSG,mfgOrderEntity.getSourceSysCd(),mfgOrderEntity.getMfgOrdrNum(),edmReservItmEntity.getMatlNum());
                                    resultObject.setFailData(failData);
                                }
                            }
                        }
                    }
                    if(resultObject.getFailData()==null){
                        if (StringUtils.isBlank(qtyFxInd)) {
                            ompGdmbomelementBo.setPlanLevelId(IConstant.OMP_GDMBOMELEMENT.DEFAULT_VALUE_PLANLEVELID_BLANK);
                        } else if (qtyFxInd.equalsIgnoreCase(IConstant.OMP_GDMBOMELEMENT.QTYFXIND_X)) {
                            ompGdmbomelementBo.setPlanLevelId(IConstant.OMP_GDMBOMELEMENT.DEFAULT_VALUE_PlanLevelId_X);
                            OMPGdmbomelementBo ompGdmbomelementBo1 = ompGdmbomelementBo.clone();
                            ResultObject resultObject1 = new ResultObject();
                            ompGdmbomelementBo1.setBomElementId(ompGdmbomelementBo1.getBomElementId() + IConstant.OMP_GDMBOMELEMENT.KEY_SPLIT + IConstant.OMP_GDMBOMELEMENT.CLONE_BOMEID);
                            ompGdmbomelementBo1.setPlanLevelId(IConstant.OMP_GDMBOMELEMENT.CLONE_PLANLEVELID);
                            resultObject1.setBaseBo(ompGdmbomelementBo1);
                            list.add(resultObject1);
                        }
                    }

                    list.add(resultObject);
                }
            }
        }


        return list;
    }

    public void StringToOther(OMPGdmbomelementBo ompGdmbomelementBo, String leadTimeOffset, String rqmtQty, String wthdrnQty) {
        try {
            double offset = Double.parseDouble(leadTimeOffset) * IConstant.OMP_GDMBOMELEMENT.DEFAULT_VALUE_LEADTIMEOFFSET;
            ompGdmbomelementBo.setOffset(formatNumber(df.format(offset)));
            double Quantity = Double.parseDouble(rqmtQty) - Double.parseDouble(wthdrnQty);
            ompGdmbomelementBo.setQuantity(formatNumber(df.format(Quantity)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ResultObject> setFORPRODUCEDMATRule(List<EDMMatlProdVersnEntity> listmatlProdVersn, PlanCnsPlanParameterEntity planCnsPlanParameterOne, EDMMfgOrderEntity mfgOrderEntity, List<EDMMfgOrderItmEntity> listMFG_ORDER_ITM, List<EDMReservItmEntity> listEDMReservItm, List<EDMMfgOrderRtngEntity> listEDMMfgOrderRtng, List<EDMMfgOrderSeqEntity> listEDMMfgOrderSeq) {
        List<ResultObject> list = new ArrayList<>();
        EDMReservItmEntity edmReservItmEntity = listEDMReservItm.get(listEDMReservItm.size() - 1);
        final String key_split = IConstant.OMP_GDMBOMELEMENT.KEY_SPLIT;
        for (int i = 0; i < listEDMMfgOrderRtng.size(); i++) {
            EDMMfgOrderRtngEntity edmMfgOrderRtngEntity = listEDMMfgOrderRtng.get(i);

            for (int j = 0; j < listEDMMfgOrderSeq.size(); j++) {
                EDMMfgOrderSeqEntity edmMfgOrderSeqEntity = listEDMMfgOrderSeq.get(j);

                for (int k = 0; k < listMFG_ORDER_ITM.size(); k++) {
                    EDMMfgOrderItmEntity orderItmEntity = listMFG_ORDER_ITM.get(k);
                    OMPGdmbomelementBo ompGdmbomelementBo = new OMPGdmbomelementBo();
                    ResultObject resultObject = new ResultObject();
                    StringBuffer sb = new StringBuffer();
                    sb.append(IConstant.OMP_GDMBOMELEMENT.FRONT_BOMID_PRO_UP).append(key_split);
                    sb.append(checkNullDel0(mfgOrderEntity.getSourceSysCd()));
                    sb.append(checkNullDel0(mfgOrderEntity.getMfgOrdrNum()));
                    sb.append(checkNullDel0(edmMfgOrderSeqEntity.getRtngSqncNum()));
                    sb.append(checkNullDel0(edmMfgOrderRtngEntity.getOperNum()));
                    sb.append(checkNullDel0(orderItmEntity.getMatlNum()));
                    sb.append(checkNullDel0(orderItmEntity.getLnItmNbr()));
                    sb.append(checkNullDel0End(orderItmEntity.getBtchNum()));
                    ompGdmbomelementBo.setBomElementId(sb.toString());
                    ompGdmbomelementBo.setActive(IConstant.OMP_GDMBOMELEMENT.DEFAULT_VALUE_YES);
                    ompGdmbomelementBo.setActiveFCTERP(IConstant.OMP_GDMBOMELEMENT.DEFAULT_VALUE_YES);
                    ompGdmbomelementBo.setActiveOPRERP(IConstant.OMP_GDMBOMELEMENT.DEFAULT_VALUE_YES);
                    ompGdmbomelementBo.setActiveSOPERP(IConstant.OMP_GDMBOMELEMENT.DEFAULT_VALUE_NO);
                    ompGdmbomelementBo.setBatchId(orderItmEntity.getBtchNum());
                    sb = new StringBuffer();
                    sb.append(IConstant.OMP_GDMBOMELEMENT.FRONT_BOMID_PRO_UP).append(key_split);
                    sb.append(checkNullDel0End(planCnsPlanParameterOne.getParameterValue())).append(IConstant.OMP_GDMBOMELEMENT.__SPLIT);
                    sb.append(checkNullDel0(mfgOrderEntity.getMfgOrdrNum()));
                    sb.append(checkNullDel0(mfgOrderEntity.getOrdrRtngNum()));
                    sb.append(checkNullDel0End(edmMfgOrderRtngEntity.getOperNum()));
                    ompGdmbomelementBo.setBOMId(sb.toString());
                    //BOMType

                    EDMMatlProdVersnEntity edmBomItemEntity = listmatlProdVersn.get(0);
                    if (edmBomItemEntity != null) {
                        if (StringUtils.isBlank(edmBomItemEntity.getDstrbtnKeyCd())) {
                            ompGdmbomelementBo.setBOMType(IConstant.OMP_GDMBOMELEMENT.BOM_ITEM.FIELD_DSTRBTNKEYCD_MAT_VALUE);
                        } else {
                            if (edmBomItemEntity.getDstrbtnKeyCd().equalsIgnoreCase(IConstant.OMP_GDMBOMELEMENT.BOM_ITEM.FIELD_DSTRBTNKEYCD_NAME)) {
                                ompGdmbomelementBo.setBOMType(IConstant.OMP_GDMBOMELEMENT.BOM_ITEM.FIELD_DSTRBTNKEYCD_VALUE);
                            } else {
                                ompGdmbomelementBo.setBOMType(IConstant.OMP_GDMBOMELEMENT.BOM_ITEM.FIELD_DSTRBTNKEYCD_ELSE_VALUE);
                            }
                        }
                    } else {
                        ompGdmbomelementBo.setBOMType(IConstant.OMP_GDMBOMELEMENT.BOM_ITEM.FIELD_DSTRBTNKEYCD_ELSE_VALUE);
                    }
                    //BOMUsage
                    ompGdmbomelementBo.setBOMUsage(IConstant.OMP_GDMBOMELEMENT.QTYFXIND_MAT);
                    ompGdmbomelementBo.setComments(IConstant.OMP_GDMBOMELEMENT.LEAVE_BLACK);
                    ompGdmbomelementBo.setOffsetCalendarId(IConstant.OMP_GDMBOMELEMENT.LEAVE_BLACK);
                    ompGdmbomelementBo.setOffsetPercentage(IConstant.OMP_GDMBOMELEMENT.LEAVE_BLACK);
                    ompGdmbomelementBo.setOffsetPercType(IConstant.OMP_GDMBOMELEMENT.LEAVE_BLACK);
                    ompGdmbomelementBo.setEndEff(IConstant.OMP_GDMBOMELEMENT.ENDEFF);
                    ompGdmbomelementBo.setStartEff(IConstant.OMP_GDMBOMELEMENT.STARTEFF);


                    ompGdmbomelementBo.setERPFeedbackQuantity(edmReservItmEntity.getWthdrnQty());
                    //Concatenate MFG_ORDER_ITM-srcSysCd, "_", MFG_ORDER_ITM-plntCd
                    ompGdmbomelementBo.setLocationId(orderItmEntity.getSrcSysCd() + IConstant.OMP_GDMBOMELEMENT.__SPLIT + orderItmEntity.getPlntCd());
//                   goodRcptLdDaysQty* 864000
                    String goodRcptLdDaysQty = orderItmEntity.getGoodRcptLdDaysQty();

                    StringToOther(ompGdmbomelementBo, goodRcptLdDaysQty, orderItmEntity.getItmQty(), "0");
//                   // cns_plan_parameter-parameterValue, '_'  and  RESERV_ITM-matlNum
                    if(StringUtils.isNotBlank(ompGdmbomelementBo.getQuantity())&&!ompGdmbomelementBo.getQuantity().equals(IConstant.OMP_GDMBOMELEMENT.PATTERN_0P000)){
                        ompGdmbomelementBo.setQuantity(IConstant.OMP_GDMBOMELEMENT.PATTERN_+ompGdmbomelementBo.getQuantity());
                    }
                    if (planCnsPlanParameterOne != null) {
                        ompGdmbomelementBo.setProductId(planCnsPlanParameterOne.getParameterValue() + IConstant.OMP_GDMBOMELEMENT.__SPLIT + orderItmEntity.getMatlNum());
                    }

                    ompGdmbomelementBo.setPlanLevelId(IConstant.OMP_GDMBOMELEMENT.DEFAULT_VALUE_PLANLEVELID_BLANK);
                    EDMMaterialGlobalV1Entity edmMaterialGlobalV1Entity= edmMaterialGlobalV1Dao.getEntityWithLocalMaterialNumberAndSourceSystem(orderItmEntity.getMatlNum(),mfgOrderEntity.getSourceSysCd());
                    if(edmMaterialGlobalV1Entity==null){
                        FailData failData=new FailData(IConstant.FAILDATA.NA,IConstant.OMP_GDMBOMELEMENT.INTERFACEID,IConstant.FAILDATA.ERRORCODE,IConstant.OMP_GDMBOMELEMENT.PRODUCTID_FAIL_MSG,mfgOrderEntity.getSourceSysCd(),mfgOrderEntity.getMfgOrdrNum(),edmReservItmEntity.getMatlNum());
                        resultObject.setFailData(failData);
                    }else{
                        PlanCnsMaterialPlanStatusEntity planCnsMaterialPlanStatusEntity=planCnsMaterialPlanStatusDao.getPlanCnsMaterialPlanStatusEntity4251(mfgOrderEntity.getSourceSysCd(),orderItmEntity.getMatlNum(),orderItmEntity.getPlntCd());;

                        if(planCnsMaterialPlanStatusEntity==null){
                            FailData failData=new FailData(IConstant.FAILDATA.NA,IConstant.OMP_GDMBOMELEMENT.INTERFACEID,IConstant.FAILDATA.ERRORCODE,IConstant.OMP_GDMBOMELEMENT.PRODUCTID_FAIL_MSG,mfgOrderEntity.getSourceSysCd(),mfgOrderEntity.getMfgOrdrNum(),edmReservItmEntity.getMatlNum());
                            resultObject.setFailData(failData);
                        }else{
                            String NoPlanRelevant=planCnsMaterialPlanStatusEntity.getNoPlanRelevant();
                            if(StringUtils.isNotBlank(NoPlanRelevant)&&NoPlanRelevant.trim().equals(IConstant.PLAN_CNS_MATERIAL_PLAN_STATUS.FIELD_X)){
                                ompGdmbomelementBo.setProductId(edmMaterialGlobalV1Entity.getPrimaryPlanningCode());
                                resultObject.setBaseBo(ompGdmbomelementBo);
                            }else{
                                if(planCnsPlanParameterOne!=null){
                                    ompGdmbomelementBo.setProductId(planCnsPlanParameterOne.getParameterValue()+IConstant.OMP_GDMBOMELEMENT.__SPLIT+edmMaterialGlobalV1Entity.getLocalDpParentCode());
                                    resultObject.setBaseBo(ompGdmbomelementBo);
                                }else{
                                    FailData failData=new FailData(IConstant.FAILDATA.NA,IConstant.OMP_GDMBOMELEMENT.INTERFACEID,IConstant.FAILDATA.ERRORCODE,IConstant.OMP_GDMBOMELEMENT.PRODUCTID_FAIL_MSG,mfgOrderEntity.getSourceSysCd(),mfgOrderEntity.getMfgOrdrNum(),edmReservItmEntity.getMatlNum());
                                    resultObject.setFailData(failData);
                                }
                            }
                        }
                    }
                    list.add(resultObject);
                }
            }
        }
        return list;
    }

    public String getSumRcvdQty(List<EDMMfgOrderItmEntity> listMFG_ORDER_ITM) {
        double sum = 0;
        for (int i = 0; i < listMFG_ORDER_ITM.size(); i++) {
            try {
                EDMMfgOrderItmEntity mfgOrderItmEntity = listMFG_ORDER_ITM.get(i);
                double qty = Double.parseDouble(mfgOrderItmEntity.getRcvdQty());
                sum += qty;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return formatNumber(df.format(sum));
    }


    public String formatNumber(String result) {
        if (StringUtils.startsWith(result, IConstant.OMP_GDMBOMELEMENT.PATTERN_POINTER)) {
            result = IConstant.OMP_GDMBOMELEMENT.DEFAULT_VALUE_0_BLANK + result;
        }
        return result;
    }

    public String checkNullDel0(String str) {
        final String key_split = IConstant.OMP_GDMBOMELEMENT.KEY_SPLIT;
        if (StringUtils.isNotBlank(str)) {
            return Del0(str)+ key_split;
        }
        return key_split;
    }
    public String Del0(String str){
        str=str.trim();
        char strs[] = str.trim().toCharArray();
        int index=strs.length;
        for(int i=0; i<strs.length; i++) {
            if (IConstant.OMP_GDMBOMELEMENT.DEL_0!=strs[i]) {
                index = i;
                break;
            }
        }
       return str.substring(index,str.length());
    }

    public String checkNullDel0End(String str) {
        if (StringUtils.isNotBlank(str)) {
            return Del0(str);
        }
        return IConstant.OMP_GDMBOMELEMENT.LEAVE_BLACK;
    }
}
