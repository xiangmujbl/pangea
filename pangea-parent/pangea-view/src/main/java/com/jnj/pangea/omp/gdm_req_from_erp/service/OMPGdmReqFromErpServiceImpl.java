package com.jnj.pangea.omp.gdm_req_from_erp.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialGlobalV1DaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsMaterialPlanStatusDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanUnitDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.entity.edm.EDMPurchaseRequisitionV1Entity;
import com.jnj.pangea.common.entity.plan.CnsMaterialInclEntity;
import com.jnj.pangea.common.entity.plan.CnsPlanUnitEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsMaterialPlanStatusEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_req_from_erp.bo.OMPGdmReqFromErpBo;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class OMPGdmReqFromErpServiceImpl implements ICommonService {

    private static OMPGdmReqFromErpServiceImpl instance;

    public static OMPGdmReqFromErpServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmReqFromErpServiceImpl();
        }
        return instance;
    }

    private EDMMaterialGlobalV1DaoImpl materialGlobalV1Dao;
    private PlanCnsMaterialPlanStatusDaoImpl planCnsMaterialPlanStatusDao;
    private PlanCnsPlanUnitDaoImpl planCnsPlanUnitDao;

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {
        ResultObject resultObject = new ResultObject();
        EDMPurchaseRequisitionV1Entity edmPurchaseRequisitionV1Entity = (EDMPurchaseRequisitionV1Entity) o;

        OMPGdmReqFromErpBo gdmReqFromErpBo = new OMPGdmReqFromErpBo();

        gdmReqFromErpBo.setBlckd(edmPurchaseRequisitionV1Entity.getBlokInd());
        gdmReqFromErpBo.setBlckt(edmPurchaseRequisitionV1Entity.getLocalBlockingText());
        gdmReqFromErpBo.setDelnr(edmPurchaseRequisitionV1Entity.getPrNum());
        gdmReqFromErpBo.setDelps(edmPurchaseRequisitionV1Entity.getPrLineNbr());
        gdmReqFromErpBo.setFlief(edmPurchaseRequisitionV1Entity.getLocalFixedVendor());
        gdmReqFromErpBo.setPlifz(edmPurchaseRequisitionV1Entity.getLocalPDT());
        gdmReqFromErpBo.setReqType(edmPurchaseRequisitionV1Entity.getPrTypeCd());
        gdmReqFromErpBo.setTotalQuantity(edmPurchaseRequisitionV1Entity.getPrLineQty());
        gdmReqFromErpBo.setVerid(edmPurchaseRequisitionV1Entity.getLocalProdVersion());
        gdmReqFromErpBo.setWrk02(edmPurchaseRequisitionV1Entity.getSuplPlntCd());

        //N1
        //No pr_doc_ic?
        //gdmReqFromErpBo.setReqFromErpId(edmPurchaseRequisitionV1Entity.getSourceSystem() + IConstant.VALUE.BACK_SLANT + edmPurchaseRequisitionV1Entity);

        //N2
        try {
        String deliveryDate = edmPurchaseRequisitionV1Entity.getNeedByDt() + IConstant.VALUE.SPACE + edmPurchaseRequisitionV1Entity.getLocalPrGRLeadTimeDays();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/DD HH:NN:SS");
            Date date = sdf.parse(deliveryDate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                cal.add(Calendar.DATE, 2);
            } else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                cal.add(Calendar.DATE, 1);
            }
            deliveryDate = sdf.format(cal);
            gdmReqFromErpBo.setDeliveryDate(deliveryDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //N3
        gdmReqFromErpBo.setDelkz(IConstant.VALUE.BA);

        //N4
        if (!edmPurchaseRequisitionV1Entity.getSourceSystem().isEmpty() && edmPurchaseRequisitionV1Entity.getSourceSystem() != null
                && !edmPurchaseRequisitionV1Entity.getPlntCd().isEmpty() && edmPurchaseRequisitionV1Entity.getPlntCd() != null){
            gdmReqFromErpBo.setLocationId(edmPurchaseRequisitionV1Entity.getSourceSystem() + IConstant.VALUE.UNDERLINE + edmPurchaseRequisitionV1Entity.getPlntCd());
        } else {
            //Critical error?
        }
        //N5
        if(IConstant.VALUE.CONS_LATAM.equalsIgnoreCase(edmPurchaseRequisitionV1Entity.getSourceSystem())) {
            gdmReqFromErpBo.setManualOffset(" ");
            gdmReqFromErpBo.setPrio_urg(" ");
        }

        //N6
        //Step 1
        EDMMaterialGlobalV1Entity materialGlobalV1Entity = materialGlobalV1Dao.getEntityWithLocalMaterialNumberAndSourceSystem(edmPurchaseRequisitionV1Entity.getMatlNum(),edmPurchaseRequisitionV1Entity.getSourceSystem());
        //Step 2
         PlanCnsMaterialPlanStatusEntity planCnsMaterialPlanStatusEntity = planCnsMaterialPlanStatusDao.getEntityWithLocalMaterialNumberAndlLocalPlantAndSourceSystem(materialGlobalV1Entity.getLocalMaterialNumber(), edmPurchaseRequisitionV1Entity.getPlntCd(), edmPurchaseRequisitionV1Entity.getSourceSystem());
         if(planCnsMaterialPlanStatusEntity.getSpRelevant().equals(IConstant.VALUE.X)) {
            //Step 3
             if(materialGlobalV1Entity.getMaterialNumber().equals(edmPurchaseRequisitionV1Entity.getMatlNum())) {
                 if(materialGlobalV1Entity.getPrimaryPlanningCode().isEmpty()) {
                     gdmReqFromErpBo.setProductId(materialGlobalV1Entity.getMaterialNumber());
                 } else {
                     gdmReqFromErpBo.setProductId(materialGlobalV1Entity.getPrimaryPlanningCode());
                 }
             }
         }
        //N7
        CnsPlanUnitEntity cnsPlanUnitEntity = planCnsPlanUnitDao.getCnsPlanUnitEntityWithLocalUom(materialGlobalV1Entity.getLocalBaseUom());
        gdmReqFromErpBo.setUnitId(cnsPlanUnitEntity.getUnit());

        //N8
        gdmReqFromErpBo.setDeleted(IConstant.VALUE.FALSE);

        //N9


        return resultObject;
    }
}
