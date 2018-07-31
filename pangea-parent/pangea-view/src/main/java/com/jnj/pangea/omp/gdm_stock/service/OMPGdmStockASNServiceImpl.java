package com.jnj.pangea.omp.gdm_stock.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.Utils;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialGlobalV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMPlantV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMPurchaseOrderOAV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsMaterialPlanStatusDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanObjectFilterDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsTlaneControlDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsTlaneControlTriangulationDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.entity.edm.EDMPlantV1Entity;
import com.jnj.pangea.common.entity.edm.EDMPurchaseOrderOAV1Entity;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.entity.plan.PlanCnsMaterialPlanStatusEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanObjectFilterEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsTlaneControlEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsTlaneControlTriangulationEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_stock.bo.OMPGdmStockBo;
import com.jnj.pangea.common.entity.edm.EDMAdvancedShipNotificationV1Entity;
import org.apache.commons.lang.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class OMPGdmStockASNServiceImpl implements ICommonService {

    public static OMPGdmStockASNServiceImpl instance;

    public static OMPGdmStockASNServiceImpl getInstance() {
        if(null == instance) {
            instance = new OMPGdmStockASNServiceImpl();
        }
        return instance;
    }

    public static final String INTERFACE_ID = "OMPGdmStockASN";

    private EDMMaterialGlobalV1DaoImpl materialGlobalDao = EDMMaterialGlobalV1DaoImpl.getInstance();
    private EDMPlantV1DaoImpl plantDao = EDMPlantV1DaoImpl.getInstance();
    private EDMPurchaseOrderOAV1DaoImpl purchaseDao = EDMPurchaseOrderOAV1DaoImpl.getInstance();
    private EDMSourceSystemV1DaoImpl sourceDao = EDMSourceSystemV1DaoImpl.getInstance();
    private PlanCnsPlanObjectFilterDaoImpl cnsPlanObjectFilterDao = PlanCnsPlanObjectFilterDaoImpl.getInstance();
    private PlanCnsMaterialPlanStatusDaoImpl cnsMaterialPlanStatusDao = PlanCnsMaterialPlanStatusDaoImpl.getInstance();
    private PlanCnsTlaneControlDaoImpl cnsTlaneControlDao = PlanCnsTlaneControlDaoImpl.getInstance();
    private PlanCnsTlaneControlTriangulationDaoImpl cnsTlaneControlTriangulationDao = PlanCnsTlaneControlTriangulationDaoImpl.getInstance();

    private static final String ERROR = "ERROR";
    private static final String DEFAULT_CERTAINTY = "LA";
    private static final String DELV_CATG = "7";
    private static final String TRANSIT_DATE = "1980/01/01 00:00:00";
    private static final String MOVEMENT = "movement";
    private static final String LINE_ITEM_TYPE_CD = "2";
    private static final String ADVANCE_SHIP_NOTIFICATIONS = "advance_ship_notifications";
    private static final String LOCAL_RECEIVING_PLANT = "localReceivingPlant";
    private static final String LOCAL_DELIVERY_TYPE = "localdeliveryType";

    private static final String VENDOR_TRANSPORT = "VendorTransport";
    private static final String INTERNAL_TRANSPORT = "InternalTransport";
    private static final String EXTERNAL_TRANSPORT = "ExternalTransport";

    interface ERROR_CODE {
        String ASN9 = "ASN9";
        String ASN9a = "ASN9a";
        String ASN9b = "ASN9b";
        String ASN9c = "ASN9c";
    }


    @Override
    public ResultObject buildView(String key, Object o, Object o2){
        ResultObject resultObject = new ResultObject();
        ResultObject resultObjectSkip = new ResultObject();
        OMPGdmStockBo stockBo = new OMPGdmStockBo();

        EDMAdvancedShipNotificationV1Entity shipNotifEntity = (EDMAdvancedShipNotificationV1Entity) o ;
        EDMSourceSystemV1Entity sourceEntity = sourceDao.getSourceSystemWithProjectOne();
        EDMMaterialGlobalV1Entity materialGlobalEntity = materialGlobalDao.getEntityWithLocalMaterialNumberAndSourceSystem(shipNotifEntity.getMatlNum(),shipNotifEntity.getSrcSysCd());
        EDMPurchaseOrderOAV1Entity purchaseEntity = purchaseDao.getEntityByPoNumAndPoLineNumberAndSourceSystem(shipNotifEntity.getLocalRefDocNum(), shipNotifEntity.getLocRefDocLineNum(), shipNotifEntity.getSrcSysCd());

        //ASN rules
        String localPlant = "";
        if(shipNotifEntity == null)
        {
            FailData failData = writeFailData(shipNotifEntity, ERROR_CODE.ASN9c, "shipNotifEntity is null");
            resultObject.setFailData(failData);
            return resultObject;
        }
        if(null != sourceEntity && shipNotifEntity.getSrcSysCd().equals(sourceEntity.getSourceSystem())){
            localPlant = shipNotifEntity.getLocalReceivingPlant();
        } else {
            return resultObjectSkip;
        }
        //ASN 7 preparation
        localPlant = asn7Rule(shipNotifEntity, localPlant);
        if(localPlant.isEmpty()){
            return resultObjectSkip;
        } else {
            stockBo.setLocationId(shipNotifEntity.getSrcSysCd() + Utils.UNDERLINE + localPlant);
        }


        //ASN 1
        String asn1_locationId = "";
        String asn1_productId = "";
        if(materialGlobalEntity == null) {
            FailData failData = writeFailData(shipNotifEntity, ERROR_CODE.ASN9b, "Both (material_global_v1-primaryPlanningCode) and (material_global_v1-MaterialNumber) are Blank ");
            resultObject.setFailData(failData);
            return resultObject;
        }

        asn1_productId = materialGlobalEntity.getPrimaryPlanningCode();
        if(null == asn1_productId || asn1_productId.isEmpty()){
            asn1_productId = materialGlobalEntity.getMaterialNumber();
        }
        asn1_locationId = shipNotifEntity.getSrcSysCd() + Utils.UNDERLINE + localPlant;
        stockBo.setStockId(asn1_productId + Utils.BACK_SLANT + asn1_locationId + Utils.BACK_SLANT + shipNotifEntity.getDelvDocId() + Utils.BACK_SLANT + shipNotifEntity.getDelvLineNbr());

        //ASN 2
        stockBo.setActive(Utils.YES);
        stockBo.setActiveOPRERP(Utils.YES);

        //ASN 3
        stockBo.setBatchId(asn1_productId + Utils.BACK_SLANT + asn1_locationId + Utils.BACK_SLANT + shipNotifEntity.getLocalBatchNo());

        //ASN 4
        stockBo.setInventoryLinkGroupId("");

        //ASN 5
        stockBo.setCertaintyId(DEFAULT_CERTAINTY); //default

        //ASN 6
        if( shipNotifEntity.getLocalDeliveryCatg()!= null &&  shipNotifEntity.getLocalDeliveryCatg().equals(DELV_CATG) && StringUtils.isBlank(shipNotifEntity.getActGRDt()) ) {
            String erp = asn6Rule(shipNotifEntity, localPlant);
            if(StringUtils.isBlank(erp)){
                //reject
                return resultObjectSkip;
            }

            stockBo.setErpOrderId(erp);
        } else {
            return resultObjectSkip;
        }

        //ASN 7
        //moved to top

        //ASN 8
        stockBo.setTransitDate(TRANSIT_DATE);

        //ASN 9
        String asn9_productId = asn9Rule(shipNotifEntity, materialGlobalEntity, localPlant);
        if(ERROR.equals(asn9_productId)){
            FailData failData = writeFailData(shipNotifEntity, ERROR_CODE.ASN9, "Both Primary Planning Code and Material Number are missing");
            resultObject.setFailData(failData);
            return resultObject;
        }
        if(asn9_productId.isEmpty()){
            return resultObjectSkip;
        }
        stockBo.setProductId(asn9_productId);

        //ASN 10
        if(null != purchaseEntity){
            stockBo.setStartDate(asn10Rule(shipNotifEntity, purchaseEntity));
        }

        //ASN 11
        stockBo.setStockType(MOVEMENT);

        //ASN 12
        // unused

        //ASN 13
        stockBo.setReceiptDate(asn13Rule(shipNotifEntity));

        //ASN 14
        stockBo.setBlockedQuantity(Utils.ZEROZERO);
        stockBo.setQualityQuantity(Utils.ZEROZERO);
        stockBo.setRestrictedQuantity(Utils.ZEROZERO);
        stockBo.setReturnsQuantity(Utils.ZEROZERO);
        stockBo.setTransferQuantity(Utils.ZEROZERO);
        stockBo.setUnrestrictedQuantity(Utils.ZEROZERO);

        //ASN 15
        stockBo.setActiveSOPERP(Utils.NO);

        if(null != purchaseEntity){
            //ASN 16 17
            stockBo.setProcessId(asn16Rule(purchaseEntity, stockBo));
            stockBo.setProcessTypeId(asn17Rule(purchaseEntity));
            if(stockBo.getProcessId().isEmpty() || stockBo.getProcessTypeId().isEmpty()){

                //reject
                return resultObjectSkip;
            }

            //ASN 18
            if( (purchaseEntity.getLineItemTypeCd() != null &&  purchaseEntity.getLineItemTypeCd().equals(LINE_ITEM_TYPE_CD))){
                stockBo.setConsignment(Utils.YES);
            } else {
                stockBo.setConsignment(Utils.NO);
            }

        } else {

            //reject
            return resultObjectSkip;
        }


        //ASN 19
        double delvQty = Double.parseDouble(shipNotifEntity.getActlSkuDelvQty());
        if( delvQty > 0.0){
            //stockBo.setQuantity(String.format("%.2f",delvQty));
            stockBo.setQuantity(shipNotifEntity.getActlSkuDelvQty());
        } else {
            //reject
            return resultObjectSkip;
        }

        //ASN 20
        String vendorId = shipNotifEntity.getVendorId();
        try {
            //remove leading zeroes
            int id = Integer.parseInt(vendorId);
            stockBo.setVendorId(String.valueOf(id));
        } catch(Exception e){
            stockBo.setVendorId(shipNotifEntity.getVendorId());
        }

        //ASN rules end
        resultObject.setBaseBo(stockBo);
        return resultObject;
    }

    private String asn6Rule(EDMAdvancedShipNotificationV1Entity shipNotifEntity, String localPlant){
        ArrayList<PlanCnsPlanObjectFilterEntity> cnsPlanObjectFilterEntityList = new ArrayList<>(cnsPlanObjectFilterDao.getEntitiesWithSourceObjectTechNameAndSourceSystem(ADVANCE_SHIP_NOTIFICATIONS, shipNotifEntity.getSrcSysCd()));

        if( cnsPlanObjectFilterEntityList != null &&  !cnsPlanObjectFilterEntityList.isEmpty())

        {
            for(PlanCnsPlanObjectFilterEntity filterEntity : cnsPlanObjectFilterEntityList) {
                if( filterEntity.getSourceObjectAttribute1() != null &&  filterEntity.getSourceObjectAttribute1().equals(LOCAL_RECEIVING_PLANT)
                        && filterEntity.getSourceObjectAttribute1Value() != null && filterEntity.getSourceObjectAttribute1Value().equals(localPlant)

                        && filterEntity.getSourceObjectAttribute2Value()!= null
                        && shipNotifEntity.getLocalDeliveryType() != null
                        && filterEntity.getSourceObjectAttribute2() != null
                        && filterEntity.getSourceObjectAttribute2Value().equals(shipNotifEntity.getLocalDeliveryType())
                        && filterEntity.getSourceObjectAttribute2().equals((LOCAL_DELIVERY_TYPE))){

                    return shipNotifEntity.getDelvDocId();
                }
            }
        }
        return "";
    }

    private String asn7Rule(EDMAdvancedShipNotificationV1Entity shipNotifEntity, String localPlant){
        ArrayList<PlanCnsTlaneControlEntity> tlaneControlEntityList = new ArrayList(cnsTlaneControlDao.getEntityListWithTriangulationParamsAnySequence(shipNotifEntity.getSrcSysCd(),localPlant,"purchase_order",Utils.YES));

        if(null != tlaneControlEntityList && !tlaneControlEntityList.isEmpty()){

            //get any
            PlanCnsTlaneControlEntity tce = tlaneControlEntityList.get(0);

            ArrayList<PlanCnsTlaneControlTriangulationEntity> trigEntityList = new ArrayList(cnsTlaneControlTriangulationDao.getStepsForSequence(tce.getSequenceNumber(),tce.getTlaneName()));
            PlanCnsTlaneControlTriangulationEntity trigEntity = trigEntityList.get(0);
            //find highest step
            int s = 0;
            for(PlanCnsTlaneControlTriangulationEntity te : trigEntityList){
                int o = Integer.parseInt(te.getStepNumber());
                if(o > s) {
                    trigEntity = te;
                    s = o;
                }
            }

            //overwrite localPlant
            String destinationLocation = trigEntity.getDestinationLocation();
            int size = tce.getSourceSystemCriticalParameters().length();
            String newLocalPlant = destinationLocation.substring(size+1);
            localPlant = newLocalPlant;
        }

        //Planning relevancy check
        EDMPlantV1Entity plantEntity = plantDao.getPlantWithSourceSystemAndLocalPlant(shipNotifEntity.getSrcSysCd(), localPlant);
        if(null != plantEntity && Utils.X.equals(plantEntity.getLocalPlanningRelevant()) ){
            return localPlant;
        }
        return "";
    }

    private String asn9Rule(EDMAdvancedShipNotificationV1Entity shipNotifEntity, EDMMaterialGlobalV1Entity materialGlobalEntity, String localPlant){
        PlanCnsMaterialPlanStatusEntity cnsMaterialPlanStatusEntity = cnsMaterialPlanStatusDao.getCnsMaterialPlanStatusDaoEntity(shipNotifEntity.getSrcSysCd(),shipNotifEntity.getMatlNum(),localPlant);

        if((cnsMaterialPlanStatusEntity != null && cnsMaterialPlanStatusEntity.getNoPlanRelevant() != null && cnsMaterialPlanStatusEntity.getNoPlanRelevant().equals(Utils.X)) ||
                (cnsMaterialPlanStatusEntity != null &&  cnsMaterialPlanStatusEntity.getSpRelevant() != null && cnsMaterialPlanStatusEntity.getSpRelevant().equals(Utils.X))){
            if(!materialGlobalEntity.getPrimaryPlanningCode().isEmpty()){
                return materialGlobalEntity.getPrimaryPlanningCode();
            } else if(!materialGlobalEntity.getMaterialNumber().isEmpty()){
                return materialGlobalEntity.getMaterialNumber();
            }else {

                return ERROR;
            }
        }

        return "";
    }


    private String asn10Rule(EDMAdvancedShipNotificationV1Entity shipNotifEntity, EDMPurchaseOrderOAV1Entity purchaseEntity){
        try {
            if( shipNotifEntity.getLocalDeliveryDate() != null &&StringUtils.isNotBlank(shipNotifEntity.getLocalDeliveryDate()) && shipNotifEntity.getLocalDeliveryDate().length()>0) {
                int gr = 0;
                if ( StringUtils.isNotBlank(purchaseEntity.getGrLeadTimeDays())) {
                    gr = Integer.parseInt(purchaseEntity.getGrLeadTimeDays());
                }
                DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat df2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Calendar cal = Calendar.getInstance();
                cal.setTime(df1.parse(shipNotifEntity.getLocalDeliveryDate()));

                cal.add(Calendar.DATE, gr);

                cal.set(Calendar.HOUR, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);

                return df2.format(cal.getTime());
            }
        } catch (Exception e){
            LogUtil.getCoreLog().catching(e);
        }
        return "";
    }

    private String asn13Rule(EDMAdvancedShipNotificationV1Entity shipNotifEntity){
        try{
            if( StringUtils.isNotBlank(shipNotifEntity.getLocalDeliveryDate())) {
                Calendar cal = Calendar.getInstance();
                DateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat dt2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                if(StringUtils.isNotBlank(shipNotifEntity.getLocalDeliveryDate()) && shipNotifEntity.getLocalDeliveryDate().length()>0 ) {
                    cal.setTime(dt1.parse(shipNotifEntity.getLocalDeliveryDate()));
                    cal.set(Calendar.HOUR, 0);
                    cal.set(Calendar.MINUTE, 0);
                    cal.set(Calendar.SECOND, 0);
                    return dt2.format(cal.getTime());
                }
            }
        } catch (Exception e) {
            LogUtil.getCoreLog().catching(e);
        }
        return "";
    }

    private String asn16Rule(EDMPurchaseOrderOAV1Entity purchaseEntity, OMPGdmStockBo stockBo){
        String processId = "";
        String poTypeCd = purchaseEntity.getPoTypeCd();
        switch(poTypeCd){
            case "NB":
                if( StringUtils.isBlank(purchaseEntity.getLineItemTypeCd()) || purchaseEntity.getLineItemTypeCd().equals(Utils.ZERO)){
                    processId = "SU" + Utils.BACK_SLANT + stockBo.getProductId() + Utils.BACK_SLANT + stockBo.getLocationId() + Utils.BACK_SLANT + purchaseEntity.getSupNum() + Utils.BACK_SLANT + "Default";
                } else {
                    processId = "TR" + Utils.BACK_SLANT + stockBo.getProductId() + Utils.BACK_SLANT + stockBo.getLocationId() + Utils.BACK_SLANT + purchaseEntity.getSuplPlntCd() + Utils.BACK_SLANT + purchaseEntity.getSupNum() ;
                }
                break;
            case "UB":
                processId = "TR" + Utils.BACK_SLANT + stockBo.getProductId() + Utils.BACK_SLANT + stockBo.getLocationId() + Utils.BACK_SLANT + purchaseEntity.getSuplPlntCd() + Utils.BACK_SLANT + purchaseEntity.getSupNum() ;
                break;
            case "ZLA":
            case "ZNB":
                processId = "TR" + Utils.BACK_SLANT + stockBo.getProductId() + Utils.BACK_SLANT + stockBo.getLocationId() + Utils.BACK_SLANT + purchaseEntity.getSuplPlntCd() + Utils.BACK_SLANT + purchaseEntity.getSupNum() ;
                break;
            default:
                break;
        }
        return processId;
    }

    private String asn17Rule(EDMPurchaseOrderOAV1Entity purchaseEntity){
        String processTypeId = "";
        String poTypeCd = purchaseEntity.getPoTypeCd();

        switch(poTypeCd){
            case "NB":
                if( StringUtils.isBlank(purchaseEntity.getLineItemTypeCd()) || purchaseEntity.getLineItemTypeCd().equals(Utils.ZERO)){
                    processTypeId = VENDOR_TRANSPORT;
                }
                break;
            case "UB":
                processTypeId = INTERNAL_TRANSPORT;
                break;
            case "ZLA":
            case "ZNB":
                processTypeId = EXTERNAL_TRANSPORT;
                break;
            default:
                break;
        }
        return processTypeId;
    }

    private FailData writeFailData(EDMAdvancedShipNotificationV1Entity shipNotifEntity , String errorCode, String errorValue) {
        FailData failData = new FailData();
        failData.setErrorCode(errorCode);
        failData.setErrorValue(errorValue);
        failData.setFunctionalArea(Utils.DP);
        failData.setInterfaceID(INTERFACE_ID);
        failData.setKey1(shipNotifEntity.getDelvDocId());
        failData.setKey2(shipNotifEntity.getDelvLineNbr());
        failData.setKey3(shipNotifEntity.getMatlNum());
        failData.setKey4("");
        failData.setKey5("");

        return failData;
    }

}
