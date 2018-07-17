package com.jnj.pangea.omp.gdm_stock.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialGlobalV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMPlantV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMPurchaseOrderOAV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsMaterialPlanStatusDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanObjectFilterDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsTlaneControlDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsTlaneControlTriangulationDaoImpl;
import com.jnj.pangea.common.entity.edm.*;
import com.jnj.pangea.common.entity.plan.PlanCnsMaterialPlanStatusEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanObjectFilterEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsTlaneControlEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsTlaneControlTriangulationEntity;
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

    private EDMMaterialGlobalV1DaoImpl materialGlobalV1Dao = EDMMaterialGlobalV1DaoImpl.getInstance();
    private PlanCnsPlanObjectFilterDaoImpl cnsPlanObjectFilterDao = PlanCnsPlanObjectFilterDaoImpl.getInstance();
    private EDMPlantV1DaoImpl plantV1Dao = EDMPlantV1DaoImpl.getInstance();
    private PlanCnsMaterialPlanStatusDaoImpl cnsMaterialPlanStatusDao = PlanCnsMaterialPlanStatusDaoImpl.getInstance();
    private EDMPurchaseOrderOAV1DaoImpl purchaseOrderOAV1Dao = EDMPurchaseOrderOAV1DaoImpl.getInstance();
    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    private PlanCnsTlaneControlDaoImpl tlaneControlDao = PlanCnsTlaneControlDaoImpl.getInstance();
    private PlanCnsTlaneControlTriangulationDaoImpl tlaneControlTriangulationDao = PlanCnsTlaneControlTriangulationDaoImpl.getInstance();

    private ThreadLocal<String> productId = new ThreadLocal<>();

    private ThreadLocal<String> locationId = new ThreadLocal<>();

    private ThreadLocal<String> localPlant = new ThreadLocal<>();

    private OMPGdmStockBo stockBo = new OMPGdmStockBo();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {
        ResultObject resultObject = new ResultObject();
        ResultObject resultObjectSkip = new ResultObject();
        productId.set("");
        locationId.set("");
        localPlant.set("");

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
            return resultObjectSkip; //Skip this record when materialGlobal isn't found
        }

        //PO7
        if(!po7Rule(purchaseOrderOAV1Entity)){
            return resultObjectSkip;
        }

        //PO1
        po1Rule(edmMaterialGlobalV1Entity,purchaseOrderOAV1Entity);



        //PO5
        po5Rule(purchaseOrderOAV1Entity);


        //PO6
        PlanCnsPlanObjectFilterEntity cnsPlanObjectFilterEntity = getFilterObject(purchaseOrderOAV1Entity);

        if(cnsPlanObjectFilterEntity == null) {
            return resultObjectSkip; //Skip if planObjectFilter isn't found
        }

        if(!po6Rule(purchaseOrderOAV1Entity, cnsPlanObjectFilterEntity)){
            return resultObjectSkip; //Skip if PO6 fails
        }

        if(!purchaseOrderOAV1Entity.getDelInd().isEmpty() && !purchaseOrderOAV1Entity.getDelvCmpltInd().isEmpty()) {
            return resultObjectSkip; //Skip if delInd value is empty
        }

        stockBo.setErpOrderId(purchaseOrderOAV1Entity.getPoNum());

        //PO8
        if(purchaseOrderOAV1Entity.getLineItemTypeCd().equals(IConstant.VALUE.TWO_NUM) || purchaseOrderOAV1Entity.getLineItemTypeCd().equals(IConstant.VALUE.K)) {
            stockBo.setConsignment(IConstant.VALUE.YES);
        } else{
            stockBo.setConsignment(IConstant.VALUE.NO);
        }

        //PO9
        PlanCnsMaterialPlanStatusEntity cnsMaterialPlanStatusEntity = cnsMaterialPlanStatusDao.getCnsMaterialPlanStatusDaoEntity( purchaseOrderOAV1Entity.getSourceSystem(),purchaseOrderOAV1Entity.getMatlNum(),localPlant.get());
        if(cnsMaterialPlanStatusEntity == null) {
            return resultObjectSkip; //Skip when materialPlanStatus is not found
        }

        if(!po9RuleChecks(cnsMaterialPlanStatusEntity)){
            return resultObjectSkip; //Skip if validation of materialPlanStatus fails
        }

        po9Rule(edmMaterialGlobalV1Entity);

        //PO10

        if(!po10Rule(purchaseOrderOAV1Entity)) {
            return resultObjectSkip; //Skip when PO10 fails
        }

        EDMPurchaseOrderOAV1Entity purchDateEntity = getLocalDelvDate(purchaseOrderOAV1Entity);
        if(purchDateEntity == null){
            return resultObjectSkip; //Skip if a valid date isn't found for localDelvDate field
        }
        String localDelvDate = purchDateEntity.getLocaldelvDt().trim();
        String leadTime = purchDateEntity.getGrLeadTimeDays().trim();
        //PO11
        po11Rule(localDelvDate);

        //PO12
        po12Rule(localDelvDate, leadTime);
        //PO17
        po17Rule(purchaseOrderOAV1Entity);

        //PO18
        po18Rule(purchaseOrderOAV1Entity);

        //PO19
        po19Rule(purchaseOrderOAV1Entity);


        if(stockBo.getProductId().isEmpty()){
            resultObject.setFailData(writeFailDataToRegion(purchaseOrderOAV1Entity, "PO9", "Material Global V1 - materialNumber and primaryPlanningCode are empty"));
        } else {
            resultObject.setBaseBo(stockBo);
        }
        return resultObject;
    }

    /**
     * set stockId - implement PO1
     *
     * @param edmMaterialGlobalV1Entity called data region
     * @param purchaseOrderOAV1Entity main data region
     */
    private void po1Rule(EDMMaterialGlobalV1Entity edmMaterialGlobalV1Entity, EDMPurchaseOrderOAV1Entity purchaseOrderOAV1Entity){
        //A
        if(edmMaterialGlobalV1Entity.getPrimaryPlanningCode() != null && edmMaterialGlobalV1Entity.getPrimaryPlanningCode().isEmpty()) {
            productId.set(edmMaterialGlobalV1Entity.getMaterialNumber());
        } else{
            productId.set(edmMaterialGlobalV1Entity.getPrimaryPlanningCode());
        }

        //B
        locationId.set(purchaseOrderOAV1Entity.getSourceSystem() + IConstant.VALUE.UNDERLINE + localPlant.get());


        //C,D
        if(purchaseOrderOAV1Entity.getDelvSchedCntNbr().isEmpty()) {
            stockBo.setStockId(productId.get() + IConstant.VALUE.BACK_SLANT + locationId.get() + IConstant.VALUE.BACK_SLANT + purchaseOrderOAV1Entity.getPoNum() + IConstant.VALUE.BACK_SLANT + purchaseOrderOAV1Entity.getPoLineNbr());
        } else{
            stockBo.setStockId(productId.get() + IConstant.VALUE.BACK_SLANT + locationId.get() + IConstant.VALUE.BACK_SLANT + purchaseOrderOAV1Entity.getPoNum() + IConstant.VALUE.BACK_SLANT + purchaseOrderOAV1Entity.getPoLineNbr() + IConstant.VALUE.BACK_SLANT +purchaseOrderOAV1Entity.getDelvSchedCntNbr());
        }
    }

    /**
     * set certaintyId - implement PO5
     *
     * @param purchaseOrderOAV1Entity main data region
     */
    private void po5Rule(EDMPurchaseOrderOAV1Entity purchaseOrderOAV1Entity){
        if(purchaseOrderOAV1Entity.getPoCatTypeCd().equals(IConstant.VALUE.F)){
            stockBo.setCertaintyId(IConstant.VALUE.BE);
        } else if (purchaseOrderOAV1Entity.getPoCatTypeCd().equals(IConstant.VALUE.L)) {
            stockBo.setCertaintyId(IConstant.VALUE.LE);
        }
    }

    /**
     * set certaintyId - implement PO5
     *
     * @param purchaseOrderOAV1Entity main data region
     * @return boolean for skip validation
     */
    private boolean po7Rule(EDMPurchaseOrderOAV1Entity purchaseOrderOAV1Entity){
        EDMSourceSystemV1Entity sourceSystemEntity = sourceSystemV1Dao.getEntityWithLocalSourceSystemAndSourceSystem(IConstant.VALUE.PROJECT_ONE_DEV, purchaseOrderOAV1Entity.getSourceSystem());
        if(sourceSystemEntity == null){
            return false;
        }
        localPlant.set(purchaseOrderOAV1Entity.getPlntCd());

        List<PlanCnsTlaneControlEntity> tlaneControlEntityList = tlaneControlDao.getEntityWithSourceSystemCriticalParameters(purchaseOrderOAV1Entity.getSourceSystem());

        for(PlanCnsTlaneControlEntity tlaneControl : tlaneControlEntityList) {
            if(tlaneControl.getTrigSysPlant().equals(localPlant.get()) && tlaneControl.getTriangulationDetail().equalsIgnoreCase(IConstant.VALUE.YES) && tlaneControl.getTrigSysTransaction().equalsIgnoreCase("purchase_order")) {
                List<PlanCnsTlaneControlTriangulationEntity> triangulationEntities = tlaneControlTriangulationDao.getEntityWithSourceSystemCriticalParameters(tlaneControl.getSequenceNumber(), tlaneControl.getTlaneName());
                if(triangulationEntities != null) {
                    PlanCnsTlaneControlTriangulationEntity stepNumberEntity = findHighestStepNumber(triangulationEntities);
                    localPlant.set(stepNumberEntity.getDestinatonLocation().replace(tlaneControl.getSourceSystemCriticalParameters()+IConstant.VALUE.UNDERLINE,IConstant.VALUE.EMPTY));
                }
            }
        }
        //Planning relevancy check
        EDMPlantV1Entity plantV1Entity = plantV1Dao.getPlantWithSourceSystemAndLocalPlant(purchaseOrderOAV1Entity.getSourceSystem(), localPlant.get());
        if(plantV1Entity != null && plantV1Entity.getLocalPlanningRelevant().equalsIgnoreCase(IConstant.VALUE.X)){
            stockBo.setLocationId(purchaseOrderOAV1Entity.getSourceSystem()+IConstant.VALUE.UNDERLINE+localPlant.get());
            return true;
        }
        return false;
    }

    /**
     * set certaintyId - implement PO5
     *
     * @param triangulationEntities List of tlane_control_triangulation objects to find the highest step number
     * @return boolean for skip validation
     */
    private PlanCnsTlaneControlTriangulationEntity findHighestStepNumber(List<PlanCnsTlaneControlTriangulationEntity> triangulationEntities) {
        PlanCnsTlaneControlTriangulationEntity tempEntity = triangulationEntities.get(0);
        for (PlanCnsTlaneControlTriangulationEntity triangulationEntity : triangulationEntities) {
            if(Integer.parseInt(triangulationEntity.getStepNumber()) > Integer.parseInt(tempEntity.getStepNumber())){
                tempEntity = triangulationEntity;
            }
        }
        return tempEntity;
    }

    /**
     * getFilterObject based on two variants
     *
     * @param purchaseOrderOAV1Entity main data region
     * @return Return the planObjectFilter that was found based on the lookup
     */
    private PlanCnsPlanObjectFilterEntity getFilterObject(EDMPurchaseOrderOAV1Entity purchaseOrderOAV1Entity){
        PlanCnsPlanObjectFilterEntity cnsPlanObjectFilterEntity = cnsPlanObjectFilterDao.getEntityWithSourceObjectTechNameAndSourceSystemAndSourceObjectAttribute1AndValue1Attribute2Value2(
                IConstant.EDM_PURCHASE_ORDER_OA_V1.PURCHASE_ORDER_OA,
                purchaseOrderOAV1Entity.getSourceSystem(),
                IConstant.EDM_PURCHASE_ORDER_OA_V1.PLNTCD,
                localPlant.get(),
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

    /**
     * validate planObjectFilterEntity based on the main object - implement PO6
     *
     * @param cnsPlanObjectFilterEntity called data region
     * @param purchaseOrderOAV1Entity main data region
     * @return boolean for validation
     */
    private boolean po6Rule(EDMPurchaseOrderOAV1Entity purchaseOrderOAV1Entity, PlanCnsPlanObjectFilterEntity cnsPlanObjectFilterEntity) {
        return((purchaseOrderOAV1Entity.getPoTypeCd().equals(cnsPlanObjectFilterEntity.getSourceObjectAttribute2Value())
                || purchaseOrderOAV1Entity.getPoTypeCd().equals(cnsPlanObjectFilterEntity.getSourceObjectAttribute2()))
                || purchaseOrderOAV1Entity.getPrchsngOrgNum().equals(cnsPlanObjectFilterEntity.getSourceObjectAttribute2Value()));
    }

    /**
     * set productId - implement PO9
     *
     * @param edmMaterialGlobalV1Entity called data region
     */
    private void po9Rule(EDMMaterialGlobalV1Entity edmMaterialGlobalV1Entity){
        if(edmMaterialGlobalV1Entity.getPrimaryPlanningCode().isEmpty()) {
            stockBo.setProductId(edmMaterialGlobalV1Entity.getMaterialNumber());
        } else {
            stockBo.setProductId(edmMaterialGlobalV1Entity.getPrimaryPlanningCode());
        }
    }

    /**
     * calculate and set quantity - implement PO10
     *
     * @param purchaseOrderOAV1Entity main data region
     * @return return a boolean if quantity calculation was successful
     */
    private boolean po10Rule(EDMPurchaseOrderOAV1Entity purchaseOrderOAV1Entity){
        Float orderUnit = new Float(0.0);
        Float baseUnit = new Float(0.0);
        boolean getRecvEaQtySum = true;
        boolean bothZero = true;
        boolean orderUnitNotZero = true;
        Float cnfrmQtySum = Float.valueOf(0);
        Float recvEaQtySum = Float.valueOf(0);
        try {
            if(!purchaseOrderOAV1Entity.getCnfrmQty().isEmpty()) {
                cnfrmQtySum = Float.parseFloat(purchaseOrderOAV1Entity.getCnfrmQty().trim());
            }

            if (cnfrmQtySum > 0) {
                getRecvEaQtySum = false;
                bothZero = false;
                orderUnit = Float.parseFloat(purchaseOrderOAV1Entity.getPoLineQty().trim()) - cnfrmQtySum;
            }

            if (getRecvEaQtySum && purchaseOrderOAV1Entity.getEvTypeCd().trim().equals("1")) {
                if(!purchaseOrderOAV1Entity.getRecvEaQty().isEmpty()) {
                    recvEaQtySum = Float.parseFloat(purchaseOrderOAV1Entity.getRecvEaQty().trim());
                }
                if (recvEaQtySum > 0) {
                    bothZero = false;
                    orderUnit = Float.parseFloat(purchaseOrderOAV1Entity.getPoLineQty().trim()) - recvEaQtySum;
                }
            }

            if (orderUnit <= 0 && !bothZero) {
                orderUnitNotZero = false;
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

    /**
     * set receiptDate - implement PO11
     *
     * @param localDelvDate date String from purchaseOrder-localDelvDate
     */
    private void po11Rule(String localDelvDate){
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

    /**
     * calculate and set startDate - implement PO12
     *
     * @param localDelvDate date string from purchaseOrder-localDelvDate
     * @param leadTime number of days to add to the date from purchaseOrder-grLeadTimeDays
     */
    private void po12Rule(String localDelvDate, String leadTime){
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

    /**
     * set inventoryLinkGroupId - implement PO17
     *
     * @param purchaseOrderOAV1Entity main data region
     */
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

    /**
     * set processId - implement PO18
     *
     * @param purchaseOrderOAV1Entity main data region
     */
    private void po18Rule(EDMPurchaseOrderOAV1Entity purchaseOrderOAV1Entity){
        if (purchaseOrderOAV1Entity.getSuplPlntCd().isEmpty()) {
            stockBo.setProcessId(IConstant.VALUE.SU + IConstant.VALUE.BACK_SLANT + productId.get() + IConstant.VALUE.BACK_SLANT + locationId.get() + IConstant.VALUE.BACK_SLANT + purchaseOrderOAV1Entity.getSupNum() + IConstant.VALUE.BACK_SLANT + IConstant.VALUE.DEFAULTSM);
        } else {
            stockBo.setProcessId(IConstant.VALUE.TR + IConstant.VALUE.BACK_SLANT + productId.get() + IConstant.VALUE.BACK_SLANT + locationId.get() + IConstant.VALUE.BACK_SLANT + purchaseOrderOAV1Entity.getSourceSystem() + IConstant.VALUE.UNDERLINE + purchaseOrderOAV1Entity.getSuplPlntCd() + IConstant.VALUE.BACK_SLANT + IConstant.VALUE.DEFAULTSM);
        }
    }

    /**
     * set processTypeId - implement PO19
     *
     * @param purchaseOrderOAV1Entity main data region
     */
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

    /**
     * lookup a valid localDelvDate from purchaseOrder region
     *
     * @param purchaseOrderOAV1Entity main data region
     * @return Return the PurchaseOrder object where the date is valid
     */
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

    /**
     * validate materialPlanStatus - implement PO9
     *
     * @param cnsMaterialPlanStatusEntity called data region
     * @return boolean for validation
     */
    private boolean po9RuleChecks(PlanCnsMaterialPlanStatusEntity cnsMaterialPlanStatusEntity){

        if(cnsMaterialPlanStatusEntity.getSpRelevant() == null && cnsMaterialPlanStatusEntity.getNoPlanRelevant() == null ){
            return false;
        } else{
            return (cnsMaterialPlanStatusEntity.getNoPlanRelevant() != null && cnsMaterialPlanStatusEntity.getNoPlanRelevant().equals(IConstant.VALUE.X)) || (cnsMaterialPlanStatusEntity.getSpRelevant() != null && cnsMaterialPlanStatusEntity.getSpRelevant().equals(IConstant.VALUE.X));
        }
    }

    /**
     * Write to fail region method
     *
     * @param purchaseOrderOAV1Entity main data region
     * @param ruleCode rule number
     * @param errorValue The error to be written to the region
     * @return FailData object will be written to the fail region by setting the ResultsObject.setFailedData
     */
    private FailData writeFailDataToRegion(EDMPurchaseOrderOAV1Entity purchaseOrderOAV1Entity, String ruleCode, String errorValue) {
        FailData failData = new FailData();
        failData.setFunctionalArea("SP");
        failData.setInterfaceID("OMPGdmStock_PurchaseOrder");
        failData.setErrorCode(ruleCode);
        failData.setSourceSystem("CONS_LATAM");
        failData.setKey1(purchaseOrderOAV1Entity.getSourceSystem());
        failData.setKey2(purchaseOrderOAV1Entity.getPoNum());
        failData.setKey3(purchaseOrderOAV1Entity.getMatlNum());
        failData.setKey4("");
        failData.setKey5("");
        failData.setErrorValue(errorValue);
        return failData;
    }
}
