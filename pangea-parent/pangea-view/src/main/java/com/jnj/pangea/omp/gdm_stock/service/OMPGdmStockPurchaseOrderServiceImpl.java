package com.jnj.pangea.omp.gdm_stock.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.FailData;
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

    //DAO Impl classes for loading entities
    private EDMMaterialGlobalV1DaoImpl materialGlobalV1Dao = EDMMaterialGlobalV1DaoImpl.getInstance();
    private PlanCnsPlanObjectFilterDaoImpl cnsPlanObjectFilterDao = PlanCnsPlanObjectFilterDaoImpl.getInstance();
    private EDMPlantV1DaoImpl plantV1Dao = EDMPlantV1DaoImpl.getInstance();
    private PlanCnsMaterialPlanStatusDaoImpl cnsMaterialPlanStatusDao = PlanCnsMaterialPlanStatusDaoImpl.getInstance();
    private EDMPurchaseOrderOAV1DaoImpl purchaseOrderOAV1Dao = EDMPurchaseOrderOAV1DaoImpl.getInstance();
    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    private PlanCnsTlaneControlDaoImpl tlaneControlDao = PlanCnsTlaneControlDaoImpl.getInstance();
    private PlanCnsTlaneControlTriangulationDaoImpl tlaneControlTriangulationDao = PlanCnsTlaneControlTriangulationDaoImpl.getInstance();

    //Variables to be used across multiple rules
    private ThreadLocal<String> productId = new ThreadLocal<>();
    private ThreadLocal<String> locationId = new ThreadLocal<>();
    private ThreadLocal<String> localPlant = new ThreadLocal<>();

    //BO objects to be written out
    private OMPGdmStockBo stockBo = new OMPGdmStockBo();

    //String Constants for this service class
    private static final String ZERO_ZERO = "0.0";
    private static final String YES = "YES";
    private static final String NO = "NO";
    private static final String MOVEMENT = "movement";
    private static final String TWO_NUM = "2";
    private static final String K = "K";
    private static final String PROJECT_ONE_DEV = "Project_One";
    private static final String UNDERLINE = "_";
    private static final String BACKSLASH = "/";
    private static final String EMPTY = "";
    private static final String X = "X";
    private static final String DEFAULTSM = "Default";
    private static final String F = "F";
    private static final String BE = "BE";
    private static final String LE = "LE";
    private static final String L = "L";
    private static final String PURCHASE_ORDER_OA = "purchase_order_oa";
    private static final String PLNTCD = "plntCd";
    private static final String PO_TYPE_CD = "poTypeCd";
    private static final String PRCHSNG_ORG_NUM = "prchsngOrgNum";
    private static final String YYYYMMDD = "yyyyMMdd";
    private static final String YYYYMMDDBS = "yyyy/MM/dd";
    private static final String HH_NN_SS_ZERO = " 00:00:00";
    private static final String NB = "NB";
    private static final String THREE_NUM = "3";
    private static final String ZLA = "ZLA";
    private static final String ZNB = "ZNB";
    private static final String SU = "SU";
    private static final String TR = "TR";
    private static final String UB = "UB";
    private static final String VENDOR_TRANSPORT = "VendorTransport";
    private static final String INTERNAL_TRANSPORT = "InternalTransport";
    private static final String EXTERNAL_TRANSPORT = "ExternalTransport";

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
        stockBo.setActive(YES);
        stockBo.setActiveOPRERP(YES);
        //PO14
        stockBo.setActiveSOPERP(NO);
        //PO4
        stockBo.setBatchId("");
        //PO16
        stockBo.setBlockedQuantity(ZERO_ZERO);
        stockBo.setQualityQuantity(ZERO_ZERO);
        stockBo.setRestrictedQuantity(ZERO_ZERO);
        stockBo.setReturnsQuantity(ZERO_ZERO);
        stockBo.setTransferQuantity(ZERO_ZERO);
        stockBo.setUnrestrictedQuantity(ZERO_ZERO);
        //PO13
        stockBo.setStockType(MOVEMENT);
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

        if(!purchaseOrderOAV1Entity.getDelInd().isEmpty() || !purchaseOrderOAV1Entity.getDelvCmpltInd().isEmpty()) {
            return resultObjectSkip; //Skip if delInd value is empty
        }

        stockBo.setErpOrderId(purchaseOrderOAV1Entity.getPoNum());

        //PO8
        if(purchaseOrderOAV1Entity.getLineItemTypeCd().equals(TWO_NUM) || purchaseOrderOAV1Entity.getLineItemTypeCd().equals(K)) {
            stockBo.setConsignment(YES);
        } else{
            stockBo.setConsignment(NO);
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
     * set localPlant - implement PO7
     *
     * @param purchaseOrderOAV1Entity main data region
     * @return boolean for skip validation
     */
    private boolean po7Rule(EDMPurchaseOrderOAV1Entity purchaseOrderOAV1Entity){
        EDMSourceSystemV1Entity sourceSystemEntity = sourceSystemV1Dao.getEntityWithLocalSourceSystemAndSourceSystem(PROJECT_ONE_DEV, purchaseOrderOAV1Entity.getSourceSystem());
        if(sourceSystemEntity == null){
            return false;
        }
        localPlant.set(purchaseOrderOAV1Entity.getPlntCd());

        List<PlanCnsTlaneControlEntity> tlaneControlEntityList = tlaneControlDao.getEntityWithSourceSystemCriticalParameters(purchaseOrderOAV1Entity.getSourceSystem());
        for(PlanCnsTlaneControlEntity tlaneControl : tlaneControlEntityList) {
            if(tlaneControl.getTrigSysPlant().equals(localPlant.get()) && tlaneControl.getTriangulationDetail().equalsIgnoreCase(YES) && tlaneControl.getTrigSysTransaction().equalsIgnoreCase("Purchase Order")) {
                List<PlanCnsTlaneControlTriangulationEntity> triangulationEntities = tlaneControlTriangulationDao.getEntityWithSeqNumberTlaneName(tlaneControl.getSequenceNumber(), tlaneControl.getTlaneName());
                if(triangulationEntities != null) {
                    PlanCnsTlaneControlTriangulationEntity stepNumberEntity = findHighestStepNumber(triangulationEntities);
                    localPlant.set(stepNumberEntity.getDestinatonLocation().replace(tlaneControl.getSourceSystemCriticalParameters()+UNDERLINE,EMPTY));
                }
            }
        }
        //Planning relevancy check
        EDMPlantV1Entity plantV1Entity = plantV1Dao.getPlantWithSourceSystemAndLocalPlant(purchaseOrderOAV1Entity.getSourceSystem(), localPlant.get());
        if(plantV1Entity != null && plantV1Entity.getLocalPlanningRelevant().equalsIgnoreCase(X)){
            stockBo.setLocationId(purchaseOrderOAV1Entity.getSourceSystem()+UNDERLINE+localPlant.get());
            return true;
        }
        return false;
    }

    /**
     * Highest step number
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
        locationId.set(purchaseOrderOAV1Entity.getSourceSystem() + UNDERLINE + localPlant.get());


        //C,D
        if(purchaseOrderOAV1Entity.getDelvSchedCntNbr().isEmpty()) {
            stockBo.setStockId(productId.get() + BACKSLASH + locationId.get() + BACKSLASH + purchaseOrderOAV1Entity.getPoNum() + BACKSLASH + purchaseOrderOAV1Entity.getPoLineNbr());
        } else{
            stockBo.setStockId(productId.get() + BACKSLASH + locationId.get() + BACKSLASH + purchaseOrderOAV1Entity.getPoNum() + BACKSLASH + purchaseOrderOAV1Entity.getPoLineNbr() + BACKSLASH +purchaseOrderOAV1Entity.getDelvSchedCntNbr());
        }
    }

    /**
     * set certaintyId - implement PO5
     *
     * @param purchaseOrderOAV1Entity main data region
     */
    private void po5Rule(EDMPurchaseOrderOAV1Entity purchaseOrderOAV1Entity){
        if(purchaseOrderOAV1Entity.getPoCatTypeCd().equals(F)){
            stockBo.setCertaintyId(BE);
        } else if (purchaseOrderOAV1Entity.getPoCatTypeCd().equals(L)) {
            stockBo.setCertaintyId(LE);
        }
    }

    /**
     * getFilterObject based on two variants
     *
     * @param purchaseOrderOAV1Entity main data region
     * @return Return the planObjectFilter that was found based on the lookup
     */
    private PlanCnsPlanObjectFilterEntity getFilterObject(EDMPurchaseOrderOAV1Entity purchaseOrderOAV1Entity){
        PlanCnsPlanObjectFilterEntity cnsPlanObjectFilterEntity = cnsPlanObjectFilterDao.getEntityWithSourceObjectTechNameAndSourceSystemAndSourceObjectAttribute1AndValue1Attribute2Value2(
                PURCHASE_ORDER_OA,
                purchaseOrderOAV1Entity.getSourceSystem(),
                PLNTCD,
                localPlant.get(),
                PO_TYPE_CD,
                purchaseOrderOAV1Entity.getPoTypeCd());
        if(cnsPlanObjectFilterEntity != null) {
            return cnsPlanObjectFilterEntity;
        }
        cnsPlanObjectFilterEntity = cnsPlanObjectFilterDao.getEntityWithSourceObjectTechNameAndSourceSystemAndSourceObjectAttribute1AndValue1Attribute2Value2(
                PURCHASE_ORDER_OA,
                purchaseOrderOAV1Entity.getSourceSystem(),
                PRCHSNG_ORG_NUM,
                purchaseOrderOAV1Entity.getPrchsngOrgNum(),
                PO_TYPE_CD,
                purchaseOrderOAV1Entity.getPrchsngOrgNum());
        if(cnsPlanObjectFilterEntity != null) {
            return cnsPlanObjectFilterEntity;
        }

        cnsPlanObjectFilterEntity = cnsPlanObjectFilterDao.getEntityWithSourceObjectTechNameAndSourceSystemAndSourceObjectAttribute1AndValue1Attribute2Value2(
                PURCHASE_ORDER_OA,
                purchaseOrderOAV1Entity.getSourceSystem(),
                PLNTCD,
                localPlant.get(),
                PRCHSNG_ORG_NUM,
                purchaseOrderOAV1Entity.getPrchsngOrgNum());

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
        SimpleDateFormat sdfFrom = new SimpleDateFormat(YYYYMMDD);
        SimpleDateFormat sdfTo = new SimpleDateFormat(YYYYMMDDBS);
        String defaultTime = HH_NN_SS_ZERO;
        try {
            Date from = sdfFrom.parse(localDelvDate);
            String to = sdfTo.format(from);
            String newDate = to + defaultTime;
            stockBo.setReceiptDate(newDate);
        } catch (ParseException e) {
            stockBo.setReceiptDate(EMPTY);
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
        SimpleDateFormat sdfFrom = new SimpleDateFormat(YYYYMMDD);
        SimpleDateFormat sdfTo = new SimpleDateFormat(YYYYMMDDBS);
        String defaultTime = HH_NN_SS_ZERO;

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
            stockBo.setStartDate(EMPTY);
            LogUtil.getCoreLog().error("PO12 date parse error OMPGdmStockPurchaseOrderServiceImpl" + e.toString());
        }
    }

    /**
     * set inventoryLinkGroupId - implement PO17
     *
     * @param purchaseOrderOAV1Entity main data region
     */
    private void po17Rule(EDMPurchaseOrderOAV1Entity purchaseOrderOAV1Entity){
        if (purchaseOrderOAV1Entity.getPoTypeCd().equals(NB)) {
            if (purchaseOrderOAV1Entity.getLineItemTypeCd().equals(THREE_NUM) || purchaseOrderOAV1Entity.getLineItemTypeCd().equals(L)) {
                stockBo.setInventoryLinkGroupId(stockBo.getStockId());
            } else {
                stockBo.setInventoryLinkGroupId(EMPTY);
            }
        } else {
            if (purchaseOrderOAV1Entity.getPoTypeCd().equals(UB) || purchaseOrderOAV1Entity.getPoTypeCd().equals(ZLA) || purchaseOrderOAV1Entity.getPoTypeCd().equals(ZNB)) {
                stockBo.setInventoryLinkGroupId(stockBo.getStockId());
            } else {
                stockBo.setInventoryLinkGroupId(EMPTY);
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
            stockBo.setProcessId(SU + BACKSLASH + productId.get() + BACKSLASH + locationId.get() + BACKSLASH + purchaseOrderOAV1Entity.getSupNum() + BACKSLASH + DEFAULTSM);
        } else {
            stockBo.setProcessId(TR + BACKSLASH + productId.get() + BACKSLASH + locationId.get() + BACKSLASH + purchaseOrderOAV1Entity.getSourceSystem() + UNDERLINE + purchaseOrderOAV1Entity.getSuplPlntCd() + BACKSLASH + DEFAULTSM);
        }
    }

    /**
     * set processTypeId - implement PO19
     *
     * @param purchaseOrderOAV1Entity main data region
     */
    private void po19Rule(EDMPurchaseOrderOAV1Entity purchaseOrderOAV1Entity){
        if (purchaseOrderOAV1Entity.getPoTypeCd().equals(NB)) {
            stockBo.setProcessTypeId(VENDOR_TRANSPORT);
        } else if (purchaseOrderOAV1Entity.getPoTypeCd().equals(UB)) {
            stockBo.setProcessTypeId(INTERNAL_TRANSPORT);
        } else if (purchaseOrderOAV1Entity.getPoTypeCd().equals(ZLA) || purchaseOrderOAV1Entity.getPoTypeCd().equals(ZNB)) {
            stockBo.setProcessTypeId(EXTERNAL_TRANSPORT);
        } else {
            stockBo.setProcessTypeId(EMPTY);
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
            return (cnsMaterialPlanStatusEntity.getNoPlanRelevant() != null && cnsMaterialPlanStatusEntity.getNoPlanRelevant().equals(X)) || (cnsMaterialPlanStatusEntity.getSpRelevant() != null && cnsMaterialPlanStatusEntity.getSpRelevant().equals(X));
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
