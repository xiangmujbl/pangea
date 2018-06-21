package com.jnj.pangea.omp.gdm_stock.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialGlobalV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMPlantV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMPurchaseOrderOAV1DaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsMaterialPlanStatusDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanObjectFilterDaoImpl;
import com.jnj.pangea.common.entity.edm.*;
import com.jnj.pangea.common.entity.plan.PlanCnsMaterialPlanStatusEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanObjectFilterEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_stock.bo.OMPGdmStockBo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class OMPGdmStockPurchaseOrderServiceImpl implements ICommonService{

    private static OMPGdmStockPurchaseOrderServiceImpl instance;

    public static OMPGdmStockPurchaseOrderServiceImpl getInstance() {
        if(null == instance) {
            instance = new OMPGdmStockPurchaseOrderServiceImpl();
        }
        return instance;
    }

    EDMMaterialGlobalV1DaoImpl materialGlobalV1Dao = EDMMaterialGlobalV1DaoImpl.getInstance();
    PlanCnsPlanObjectFilterDaoImpl cnsPlanObjectFilterDao = PlanCnsPlanObjectFilterDaoImpl.getInstance();
    EDMPlantV1DaoImpl plantV1Dao = EDMPlantV1DaoImpl.getInstance();
    PlanCnsMaterialPlanStatusDaoImpl cnsMaterialPlanStatusDao = PlanCnsMaterialPlanStatusDaoImpl.getInstance();
    EDMPurchaseOrderOAV1DaoImpl purchaseOrderOAV1Dao = EDMPurchaseOrderOAV1DaoImpl.getInstance();

    String productId = "";
    String locationId = "";

    OMPGdmStockBo stockBo = new OMPGdmStockBo();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        ResultObject resultObjectSkip = new ResultObject();
        EDMPurchaseOrderOAV1Entity purchaseOrderOAV1Entity = (EDMPurchaseOrderOAV1Entity) o;


        stockBo.setVendorId(purchaseOrderOAV1Entity.getSupNum());
        //PO2
        stockBo.setActive(IConstant.VALUE.YES);
        stockBo.setActiveOPRERP(IConstant.VALUE.YES);
        //PO14
        stockBo.setActiveSOPERP(IConstant.VALUE.NO);
        //PO4
        stockBo.setBatchId("");
        //PO16
        stockBo.setBlockedQuantity(IConstant.VALUE.ZEROZERO);
        stockBo.setQualityQuantity(IConstant.VALUE.ZEROZERO);
        stockBo.setRestrictedQuantity(IConstant.VALUE.ZEROZERO);
        stockBo.setReturnsQuantity(IConstant.VALUE.ZEROZERO);
        stockBo.setTransferQuantity(IConstant.VALUE.ZEROZERO);
        stockBo.setUnrestrictedQuantity(IConstant.VALUE.ZEROZERO);
        //PO13
        stockBo.setStockType(IConstant.VALUE.MOVEMENT);
        //PO20
        stockBo.setTransitDate("1980/01/01 00:00:00");


        EDMMaterialGlobalV1Entity edmMaterialGlobalV1Entity = materialGlobalV1Dao.getEntityWithLocalMaterialNumberAndSourceSystem(purchaseOrderOAV1Entity.getMatlNum(), purchaseOrderOAV1Entity.getSourceSystem());
        if(edmMaterialGlobalV1Entity == null) {
            return resultObjectSkip;
        }
        //PO1
        po1Rule(edmMaterialGlobalV1Entity,purchaseOrderOAV1Entity);

        //PO5
        po5Rule(purchaseOrderOAV1Entity);

        //PO6
        PlanCnsPlanObjectFilterEntity cnsPlanObjectFilterEntity = getFilterObject(purchaseOrderOAV1Entity);

        if(cnsPlanObjectFilterEntity == null) {
            return resultObjectSkip;
        }

        if(!po6Rule(purchaseOrderOAV1Entity, cnsPlanObjectFilterEntity)){
            return resultObjectSkip;
        }

        if(!purchaseOrderOAV1Entity.getDelInd().isEmpty()) {
            return resultObjectSkip;
        }

        stockBo.setErpOrderId(purchaseOrderOAV1Entity.getPoNum());

        //PO7
        EDMPlantV1Entity plantV1Entity = plantV1Dao.getPlantWithSourceSystemAndLocalPlant(purchaseOrderOAV1Entity.getSourceSystem(), purchaseOrderOAV1Entity.getPlntCd());
        if(plantV1Entity == null) {
            return resultObjectSkip;
        }

        if(!plantV1Entity.getLocalPlanningRelevant().equals(IConstant.VALUE.X)) {
            return resultObjectSkip;
        }

        stockBo.setLocationId(purchaseOrderOAV1Entity.getSourceSystem() + IConstant.VALUE.UNDERLINE + purchaseOrderOAV1Entity.getPlntCd());

        //PO8
        if(purchaseOrderOAV1Entity.getLineItemTypeCd().equals(IConstant.VALUE.TWO_NUM) || purchaseOrderOAV1Entity.getLineItemTypeCd().equals(IConstant.VALUE.K)) {
            stockBo.setConsignment(IConstant.VALUE.YES);
        } else{
            stockBo.setConsignment(IConstant.VALUE.NO);
        }

        //PO9
        PlanCnsMaterialPlanStatusEntity cnsMaterialPlanStatusEntity = cnsMaterialPlanStatusDao.getEntityWithLocalMaterialNumberAndSourceSystem(purchaseOrderOAV1Entity.getMatlNum(), purchaseOrderOAV1Entity.getSourceSystem());
        if(cnsMaterialPlanStatusEntity == null) {
            return resultObjectSkip;
        }

        if((cnsMaterialPlanStatusEntity.getSpRelevant() == null && !cnsMaterialPlanStatusEntity.getSpRelevant().equals(IConstant.VALUE.X)) || (cnsMaterialPlanStatusEntity.getNoPlanRelevant() == null && !cnsMaterialPlanStatusEntity.getNoPlanRelevant().equals(IConstant.VALUE.X))) {
            return resultObjectSkip;
        }

        po9Rule(edmMaterialGlobalV1Entity);

        //PO10

        if(!po10Rule(purchaseOrderOAV1Entity)) {
            return resultObjectSkip;
        }
        EDMPurchaseOrderOAV1Entity purchDateEntity = getLocalDelvDate(purchaseOrderOAV1Entity);
        if(purchDateEntity == null){
            return resultObjectSkip;
        }
        String localDelvDate = purchDateEntity.getLocaldelvDt().trim();
        String leadTime = purchDateEntity.getGrLeadTimeDays().trim();
        //PO11
        po11Rule(purchaseOrderOAV1Entity, localDelvDate);

        //PO12
        po12Rule(purchaseOrderOAV1Entity, localDelvDate, leadTime);

        //PO17
        po17Rule(purchaseOrderOAV1Entity);

        //PO18
        po18Rule(purchaseOrderOAV1Entity);

        //PO19
        po19Rule(purchaseOrderOAV1Entity);

        resultObject.setBaseBo(stockBo);
        return resultObject;
    }

    private void po1Rule(EDMMaterialGlobalV1Entity edmMaterialGlobalV1Entity, EDMPurchaseOrderOAV1Entity purchaseOrderOAV1Entity){
        //A
        if(edmMaterialGlobalV1Entity.getPrimaryPlanningCode() != null && edmMaterialGlobalV1Entity.getPrimaryPlanningCode().isEmpty()) {
            productId = edmMaterialGlobalV1Entity.getMaterialNumber();
        } else{
            productId = edmMaterialGlobalV1Entity.getPrimaryPlanningCode();
        }

        //B
        locationId = purchaseOrderOAV1Entity.getSourceSystem() + IConstant.VALUE.UNDERLINE + purchaseOrderOAV1Entity.getPlntCd();


        //C,D
        if(purchaseOrderOAV1Entity.getDelvSchedCntNbr().isEmpty()) {
            stockBo.setStockId(productId + IConstant.VALUE.BACK_SLANT + locationId + IConstant.VALUE.BACK_SLANT + purchaseOrderOAV1Entity.getPoNum() + IConstant.VALUE.BACK_SLANT + purchaseOrderOAV1Entity.getPoLineNbr());
        } else{
            stockBo.setStockId(productId + IConstant.VALUE.BACK_SLANT + locationId + IConstant.VALUE.BACK_SLANT + purchaseOrderOAV1Entity.getPoNum() + IConstant.VALUE.BACK_SLANT + purchaseOrderOAV1Entity.getPoLineNbr() + IConstant.VALUE.BACK_SLANT +purchaseOrderOAV1Entity.getDelvSchedCntNbr());
        }
    }

    private void po5Rule(EDMPurchaseOrderOAV1Entity purchaseOrderOAV1Entity){
        if(purchaseOrderOAV1Entity.getPoCatTypeCd().equals(IConstant.VALUE.F)){
            stockBo.setCertaintyID(IConstant.VALUE.BE);
        } else if (purchaseOrderOAV1Entity.getPoCatTypeCd().equals(IConstant.VALUE.L)) {
            stockBo.setCertaintyID(IConstant.VALUE.LE);
        }
    }

    private PlanCnsPlanObjectFilterEntity getFilterObject(EDMPurchaseOrderOAV1Entity purchaseOrderOAV1Entity){
        PlanCnsPlanObjectFilterEntity cnsPlanObjectFilterEntity = cnsPlanObjectFilterDao.getEntityWithSourceObjectTechNameAndSourceSystemAndSourceObjectAttribute1AndValue1Attribute2Value2(
                IConstant.EDM_PURCHASE_ORDER_OA_V1.PURCHASE_ORDER_OA,
                purchaseOrderOAV1Entity.getSourceSystem(),
                IConstant.EDM_PURCHASE_ORDER_OA_V1.PLNTCD,
                purchaseOrderOAV1Entity.getPlntCd(),
                IConstant.EDM_PURCHASE_ORDER_OA_V1.PO_TYPE_CD,
                purchaseOrderOAV1Entity.getPoTypeCd());
        if(cnsPlanObjectFilterEntity == null) {
            cnsPlanObjectFilterEntity = cnsPlanObjectFilterDao.getEntityWithSourceObjectTechNameAndSourceSystemAndSourceObjectAttribute1AndValue1Attribute2Value2(
                    IConstant.EDM_PURCHASE_ORDER_OA_V1.PURCHASE_ORDER_OA,
                    purchaseOrderOAV1Entity.getSourceSystem(),
                    IConstant.EDM_PURCHASE_ORDER_OA_V1.PRCHSNG_ORG_NUM,
                    purchaseOrderOAV1Entity.getPrchsngOrgNum(),
                    IConstant.EDM_PURCHASE_ORDER_OA_V1.PO_TYPE_CD,
                    purchaseOrderOAV1Entity.getPoTypeCd());
        }
        return cnsPlanObjectFilterEntity;
    }

    private boolean po6Rule(EDMPurchaseOrderOAV1Entity purchaseOrderOAV1Entity, PlanCnsPlanObjectFilterEntity cnsPlanObjectFilterEntity) {
        return((purchaseOrderOAV1Entity.getPoTypeCd().equals(cnsPlanObjectFilterEntity.getSourceObjectAttribute2Value())
                || purchaseOrderOAV1Entity.getPoTypeCd().equals(cnsPlanObjectFilterEntity.getSourceObjectAttribute2()))
                || purchaseOrderOAV1Entity.getPrchsngOrgNum().equals(cnsPlanObjectFilterEntity.getSourceObjectAttribute2Value()));
    }

    private void po9Rule(EDMMaterialGlobalV1Entity edmMaterialGlobalV1Entity){
        if(edmMaterialGlobalV1Entity.getPrimaryPlanningCode().isEmpty()) {
            stockBo.setProductId(edmMaterialGlobalV1Entity.getMaterialNumber());
        } else {
            stockBo.setProductId(edmMaterialGlobalV1Entity.getPrimaryPlanningCode());
        }
    }

    private boolean po10Rule(EDMPurchaseOrderOAV1Entity purchaseOrderOAV1Entity){
        Float orderUnit = new Float(0.0);
        Float baseUnit = new Float(0.0);
        boolean getRecvEaQtySum = true;
        boolean bothZero = true;
        boolean orderUnitNotZero = true;
        try {
            Float cnfrmQtySum = Float.parseFloat(purchaseOrderOAV1Entity.getCnfrmQty().trim());
            if (cnfrmQtySum > 0) {
                getRecvEaQtySum = false;
                bothZero = false;
                orderUnit = Float.parseFloat(purchaseOrderOAV1Entity.getPoLineQty().trim()) - cnfrmQtySum;
                if(orderUnit == 0){
                    orderUnitNotZero = false;
                }
            }
            if (getRecvEaQtySum && purchaseOrderOAV1Entity.getEvTypeCd().trim().equals("1")) {
                Float recvEaQtySum = Float.parseFloat(purchaseOrderOAV1Entity.getRecvEaQty().trim());
                if (recvEaQtySum > 0) {
                    bothZero = false;
                    orderUnit = Float.parseFloat(purchaseOrderOAV1Entity.getPoLineQty().trim()) - recvEaQtySum;
                    if(orderUnit == 0){
                        orderUnitNotZero = false;
                    }
                }
            }

            if (bothZero) {
                orderUnit = Float.parseFloat(purchaseOrderOAV1Entity.getPoLineQty().trim());
            }
            if (!purchaseOrderOAV1Entity.getLocalNumerator().isEmpty() && !purchaseOrderOAV1Entity.getLocalDenominator().isEmpty()) {
                baseUnit = orderUnit * Integer.parseInt(purchaseOrderOAV1Entity.getLocalNumerator().trim()) / Integer.parseInt(purchaseOrderOAV1Entity.getLocalDenominator().trim());
            }
        } catch(NumberFormatException nfe){
            LogUtil.getCoreLog().error("PO10 float parse error OMPGdmStockPurchaseOrderServiceImpl" + nfe.toString());
        }
        stockBo.setQuantity(Float.toString(baseUnit));
        return orderUnitNotZero;
    }

    private void po11Rule(EDMPurchaseOrderOAV1Entity purchaseOrderOAV1Entity, String localDelvDate){
        SimpleDateFormat sdfFrom = new SimpleDateFormat(IConstant.VALUE.YYYYMMDD);
        SimpleDateFormat sdfTo = new SimpleDateFormat(IConstant.VALUE.YYYYMMDDBS);
        String defaultTime = IConstant.VALUE.HH_NN_SS_ZERO;
        try {
            Date from = sdfFrom.parse(localDelvDate);
            String to = sdfTo.format(from);
            String newDate = to + defaultTime;
            stockBo.setReceiptDate(newDate);
        } catch (ParseException e) {
            stockBo.setReceiptDate(IConstant.VALUE.EMPTY);
            LogUtil.getCoreLog().error("PO11 date parse error OMPGdmStockPurchaseOrderServiceImpl" + e.toString());
        }
    }

    private void po12Rule(EDMPurchaseOrderOAV1Entity purchaseOrderOAV1Entity, String localDelvDate, String leadTime){
        SimpleDateFormat sdfFrom = new SimpleDateFormat(IConstant.VALUE.YYYYMMDD);
        SimpleDateFormat sdfTo = new SimpleDateFormat(IConstant.VALUE.YYYYMMDDBS);
        String defaultTime = IConstant.VALUE.HH_NN_SS_ZERO;

        try {
            String dateToFormat = localDelvDate;
            Date dFrom = sdfFrom.parse(dateToFormat);

            Calendar cal = Calendar.getInstance();
            cal.setTime(dFrom);
            if (!leadTime.isEmpty()) {
                cal.add(Calendar.DATE, Integer.parseInt(leadTime));
            }
            if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                cal.add(Calendar.DATE, 2);
            } else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                cal.add(Calendar.DATE, 1);
            }
            Date d2 = cal.getTime();
            String deliveryDate = sdfTo.format(d2) + defaultTime;
            stockBo.setStartDate(deliveryDate);
        } catch (ParseException e) {
            stockBo.setStartDate(IConstant.VALUE.EMPTY);
            LogUtil.getCoreLog().error("PO12 date parse error OMPGdmStockPurchaseOrderServiceImpl" + e.toString());
        }
    }

    private void po17Rule(EDMPurchaseOrderOAV1Entity purchaseOrderOAV1Entity){
        if (purchaseOrderOAV1Entity.getPoTypeCd().equals(IConstant.VALUE.NB)) {
            if (purchaseOrderOAV1Entity.getLineItemTypeCd().equals(IConstant.VALUE.THREE_NUM) || purchaseOrderOAV1Entity.getLineItemTypeCd().equals(IConstant.VALUE.L)) {
                stockBo.setInventoryLinkGroupId(stockBo.getStockId());
            } else {
                stockBo.setInventoryLinkGroupId(IConstant.VALUE.EMPTY);
            }
        } else {
            if (purchaseOrderOAV1Entity.getPoTypeCd().equals(IConstant.VALUE.UB) || purchaseOrderOAV1Entity.getPoTypeCd().equals(IConstant.VALUE.ZLA) || purchaseOrderOAV1Entity.getPoTypeCd().equals(IConstant.VALUE.ZNB)) {
                stockBo.setInventoryLinkGroupId(stockBo.getStockId());
            } else {
                stockBo.setInventoryLinkGroupId(IConstant.VALUE.EMPTY);
            }
        }
    }

    private void po18Rule(EDMPurchaseOrderOAV1Entity purchaseOrderOAV1Entity){
        if (purchaseOrderOAV1Entity.getSuplPlntCd().isEmpty()) {
            stockBo.setProcessId(IConstant.VALUE.SU + IConstant.VALUE.BACK_SLANT + productId + IConstant.VALUE.BACK_SLANT + locationId + IConstant.VALUE.BACK_SLANT + purchaseOrderOAV1Entity.getSupNum() + IConstant.VALUE.BACK_SLANT + IConstant.VALUE.DEFAULTSM);
        } else {
            stockBo.setProcessId(IConstant.VALUE.TR + IConstant.VALUE.BACK_SLANT + productId + IConstant.VALUE.BACK_SLANT + locationId + IConstant.VALUE.BACK_SLANT + purchaseOrderOAV1Entity.getSourceSystem() + IConstant.VALUE.UNDERLINE + purchaseOrderOAV1Entity.getSuplPlntCd() + IConstant.VALUE.BACK_SLANT + IConstant.VALUE.DEFAULTSM);
        }
    }

    private void po19Rule(EDMPurchaseOrderOAV1Entity purchaseOrderOAV1Entity){
        if (purchaseOrderOAV1Entity.getPoTypeCd().equals(IConstant.VALUE.NB)) {
            stockBo.setProcessTypeId(IConstant.VALUE.VENDOR_TRANSPORT);
        } else if (purchaseOrderOAV1Entity.getPoTypeCd().equals(IConstant.VALUE.UB)) {
            stockBo.setProcessTypeId(IConstant.VALUE.INTERNAL_TRANSPORT);
        } else if (purchaseOrderOAV1Entity.getPoTypeCd().equals(IConstant.VALUE.ZLA) || purchaseOrderOAV1Entity.getPoTypeCd().equals(IConstant.VALUE.ZNB)) {
            stockBo.setProcessTypeId(IConstant.VALUE.EXTERNAL_TRANSPORT);
        } else {
            stockBo.setProcessTypeId(IConstant.VALUE.EMPTY);
        }
    }

    private EDMPurchaseOrderOAV1Entity getLocalDelvDate(EDMPurchaseOrderOAV1Entity purchaseOrderOAV1Entity){
        List<EDMPurchaseOrderOAV1Entity> porders = purchaseOrderOAV1Dao.getPurchaseOrderListByPoNumPoLineNbr(purchaseOrderOAV1Entity.getPoNum(), purchaseOrderOAV1Entity.getPoLineNbr());
        if(porders != null) {
            for(EDMPurchaseOrderOAV1Entity e : porders) {
                if(!e.getLocaldelvDt().trim().isEmpty()) {
                    return e;
                }
            }
        }
        return null;
    }
}
