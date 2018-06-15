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

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();
        EDMPurchaseOrderOAV1Entity purchaseOrderOAV1Entity = (EDMPurchaseOrderOAV1Entity) o;

        OMPGdmStockBo stockBo = new OMPGdmStockBo();

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

        //PO1
        String productId = "";
        String locationId = "";
        //A
        EDMMaterialGlobalV1Entity edmMaterialGlobalV1Entity = materialGlobalV1Dao.getEntityWithLocalMaterialNumberAndSourceSystem(purchaseOrderOAV1Entity.getMatlNum(), purchaseOrderOAV1Entity.getSourceSystem());
        if(edmMaterialGlobalV1Entity != null){
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

            //PO5
            if(purchaseOrderOAV1Entity.getPoCatTypeCd().equals(IConstant.VALUE.F)){
                stockBo.setCertaintyID(IConstant.VALUE.BE);
            } else if (purchaseOrderOAV1Entity.getPoCatTypeCd().equals(IConstant.VALUE.L)) {
                stockBo.setCertaintyID(IConstant.VALUE.LE);
            }

            //PO6
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

            if(cnsPlanObjectFilterEntity != null) {
                if((purchaseOrderOAV1Entity.getPoTypeCd().equals(cnsPlanObjectFilterEntity.getSourceObjectAttribute2Value())
                   || purchaseOrderOAV1Entity.getPoTypeCd().equals(cnsPlanObjectFilterEntity.getSourceObjectAttribute2()))
                   || purchaseOrderOAV1Entity.getPrchsngOrgNum().equals(cnsPlanObjectFilterEntity.getSourceObjectAttribute2Value())) {
                    if(purchaseOrderOAV1Entity.getDelInd().isEmpty()) {
                        stockBo.setErpOrderId(purchaseOrderOAV1Entity.getPoNum());

                        //PO7
                        EDMPlantV1Entity plantV1Entity = plantV1Dao.getPlantWithSourceSystemAndLocalPlant(purchaseOrderOAV1Entity.getSourceSystem(), purchaseOrderOAV1Entity.getPlntCd());
                        if(plantV1Entity != null) {
                            if(plantV1Entity.getLocalPlanningRelevant().equals(IConstant.VALUE.X)) {
                                stockBo.setLocationId(purchaseOrderOAV1Entity.getSourceSystem() + IConstant.VALUE.UNDERLINE + purchaseOrderOAV1Entity.getPlntCd());

                                //PO8
                                if(purchaseOrderOAV1Entity.getLineItemTypeCd().equals(IConstant.VALUE.TWO_NUM) || purchaseOrderOAV1Entity.getLineItemTypeCd().equals(IConstant.VALUE.K)) {
                                    stockBo.setConsignment(IConstant.VALUE.YES);
                                } else{
                                    stockBo.setConsignment(IConstant.VALUE.NO);
                                }

                                //PO9
                                PlanCnsMaterialPlanStatusEntity cnsMaterialPlanStatusEntity = cnsMaterialPlanStatusDao.getEntityWithLocalMaterialNumberAndsourceSystem(purchaseOrderOAV1Entity.getMatlNum(), purchaseOrderOAV1Entity.getSourceSystem());
                                if(cnsMaterialPlanStatusEntity != null) {
                                    if((cnsMaterialPlanStatusEntity.getSpRelevant() != null && cnsMaterialPlanStatusEntity.getSpRelevant().equals(IConstant.VALUE.X)) || (cnsMaterialPlanStatusEntity.getNoPlanRelevant() != null &&cnsMaterialPlanStatusEntity.getNoPlanRelevant().equals(IConstant.VALUE.X))) {
                                        if(edmMaterialGlobalV1Entity.getPrimaryPlanningCode().isEmpty()) {
                                            stockBo.setProductId(edmMaterialGlobalV1Entity.getMaterialNumber());
                                        } else {
                                            stockBo.setProductId(edmMaterialGlobalV1Entity.getPrimaryPlanningCode());
                                        }

                                        //PO10
                                        Float orderUnit = new Float(0.0);
                                        Float baseUnit = new Float(0.0);
                                        boolean getRecvEaQtySum = true;
                                        boolean bothZero = true;
                                        boolean orderUnitNotZero = true;
                                        try {
                                            if (!purchaseOrderOAV1Entity.getCnfrmQty().isEmpty()) {
                                                Float cnfrmQtySum = Float.parseFloat(purchaseOrderOAV1Entity.getCnfrmQty().trim());
                                                if (cnfrmQtySum > 0) {
                                                    getRecvEaQtySum = false;
                                                    bothZero = false;
                                                    if (!purchaseOrderOAV1Entity.getPoLineQty().isEmpty()) {
                                                        orderUnit = Float.parseFloat(purchaseOrderOAV1Entity.getPoLineQty().trim()) - cnfrmQtySum;
                                                        if(orderUnit == 0){
                                                            orderUnitNotZero = false;
                                                        }
                                                    }
                                                }
                                            }
                                            if (getRecvEaQtySum && purchaseOrderOAV1Entity.getEvTypeCd().trim().equals("1")) {
                                                if (!purchaseOrderOAV1Entity.getRecvEaQty().isEmpty()) {
                                                    Float recvEaQtySum = Float.parseFloat(purchaseOrderOAV1Entity.getRecvEaQty().trim());
                                                    if (recvEaQtySum > 0) {
                                                        bothZero = false;
                                                        if (!purchaseOrderOAV1Entity.getPoLineQty().isEmpty()) {
                                                            orderUnit = Float.parseFloat(purchaseOrderOAV1Entity.getPoLineQty().trim()) - recvEaQtySum;
                                                            if(orderUnit == 0){
                                                                orderUnitNotZero = false;
                                                            }
                                                        }
                                                    }
                                                }
                                            }

                                            if (bothZero) {
                                                if (!purchaseOrderOAV1Entity.getPoLineQty().isEmpty()) {
                                                    orderUnit = Float.parseFloat(purchaseOrderOAV1Entity.getPoLineQty().trim());
                                                }
                                            }
                                            if (!purchaseOrderOAV1Entity.getLocalNumerator().isEmpty() && !purchaseOrderOAV1Entity.getLocalDenominator().isEmpty()) {
                                                baseUnit = orderUnit * Integer.parseInt(purchaseOrderOAV1Entity.getLocalNumerator().trim()) / Integer.parseInt(purchaseOrderOAV1Entity.getLocalDenominator().trim());
                                            }
                                        } catch(NumberFormatException nfe){
                                            LogUtil.getCoreLog().error("PO10 float parse error OMPGdmStockPurchaseOrderServiceImpl");
                                            nfe.printStackTrace();
                                        }
                                        if(orderUnitNotZero) {
                                            stockBo.setQuantity(Float.toString(baseUnit));

                                            //PO11
                                            SimpleDateFormat sdfFrom = new SimpleDateFormat(IConstant.VALUE.YYYYMMDD);
                                            SimpleDateFormat sdfTo = new SimpleDateFormat(IConstant.VALUE.YYYYMMDDBS);
                                            String defaultTime = IConstant.VALUE.HH_NN_SS_ZERO;
                                            try {
                                                Date from = sdfFrom.parse(purchaseOrderOAV1Entity.getDelvDt().trim());
                                                String to = sdfTo.format(from);
                                                String newDate = to + defaultTime;
                                                stockBo.setReceiptDate(newDate);
                                            } catch (ParseException e) {
                                                stockBo.setReceiptDate(IConstant.VALUE.EMPTY);
                                                e.printStackTrace();
                                            }

                                            //PO12
                                            try {
                                                String dateToFormat = purchaseOrderOAV1Entity.getDelvDt().trim();
                                                Date dFrom = sdfFrom.parse(dateToFormat);

                                                String timeToMove = purchaseOrderOAV1Entity.getGrLeadTimeDays().trim();
                                                Calendar cal = Calendar.getInstance();
                                                cal.setTime(dFrom);
                                                if (!timeToMove.isEmpty()) {
                                                    cal.add(Calendar.DATE, Integer.parseInt(timeToMove));
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
                                                e.printStackTrace();
                                            }

                                            //PO17
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

                                            //PO18
                                            if (purchaseOrderOAV1Entity.getSuplPlntCd().isEmpty()) {
                                                stockBo.setProcessId(IConstant.VALUE.SU + IConstant.VALUE.BACK_SLANT + productId + IConstant.VALUE.BACK_SLANT + locationId + IConstant.VALUE.BACK_SLANT + purchaseOrderOAV1Entity.getSupNum() + IConstant.VALUE.BACK_SLANT + IConstant.VALUE.DEFAULTSM);
                                            } else {
                                                stockBo.setProcessId(IConstant.VALUE.TR + IConstant.VALUE.BACK_SLANT + productId + IConstant.VALUE.BACK_SLANT + locationId + IConstant.VALUE.BACK_SLANT + purchaseOrderOAV1Entity.getSourceSystem() + IConstant.VALUE.UNDERLINE + purchaseOrderOAV1Entity.getSuplPlntCd() + IConstant.VALUE.BACK_SLANT + IConstant.VALUE.DEFAULTSM);
                                            }

                                            //PO19
                                            if (purchaseOrderOAV1Entity.getPoTypeCd().equals(IConstant.VALUE.NB)) {
                                                stockBo.setProcessTypeId(IConstant.VALUE.VENDOR_TRANSPORT);
                                            } else if (purchaseOrderOAV1Entity.getPoTypeCd().equals(IConstant.VALUE.UB)) {
                                                stockBo.setProcessTypeId(IConstant.VALUE.INTERNAL_TRANSPORT);
                                            } else if (purchaseOrderOAV1Entity.getPoTypeCd().equals(IConstant.VALUE.ZLA) || purchaseOrderOAV1Entity.getPoTypeCd().equals(IConstant.VALUE.ZNB)) {
                                                stockBo.setProcessTypeId(IConstant.VALUE.EXTERNAL_TRANSPORT);
                                            } else {
                                                stockBo.setProcessTypeId(IConstant.VALUE.EMPTY);
                                            }

                                            resultObject.setBaseBo(stockBo); //Skipped if doesn't get to this point
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return resultObject;
    }
}
