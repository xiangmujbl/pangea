package com.jnj.pangea.omp.gdm_stock.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.IConstant;
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

    private EDMMaterialGlobalV1DaoImpl materialGlobalDao = EDMMaterialGlobalV1DaoImpl.getInstance();
    private EDMPlantV1DaoImpl plantDao = EDMPlantV1DaoImpl.getInstance();
    private EDMPurchaseOrderOAV1DaoImpl purchaseDao = EDMPurchaseOrderOAV1DaoImpl.getInstance();
    private EDMSourceSystemV1DaoImpl sourceDao = EDMSourceSystemV1DaoImpl.getInstance();
    private PlanCnsPlanObjectFilterDaoImpl cnsPlanObjectFilterDao = PlanCnsPlanObjectFilterDaoImpl.getInstance();
    private PlanCnsMaterialPlanStatusDaoImpl cnsMaterialPlanStatusDao = PlanCnsMaterialPlanStatusDaoImpl.getInstance();
    private PlanCnsTlaneControlDaoImpl cnsTlaneControlDao = PlanCnsTlaneControlDaoImpl.getInstance();
    private PlanCnsTlaneControlTriangulationDaoImpl cnsTlaneControlTriangulationDao = PlanCnsTlaneControlTriangulationDaoImpl.getInstance();

    private static final String ERROR = "ERROR";


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
            FailData failData = writeFailData(shipNotifEntity, IConstant.FAILED.ERROR_CODE.ASN9c, "shipNotifEntity is null");
            resultObject.setFailData(failData);
            return resultObject;
        }
        if(null != sourceEntity && shipNotifEntity.getSrcSysCd().equals(sourceEntity.getSourceSystem())){
            localPlant = shipNotifEntity.getLocalReceivingPlant();
        } else {
            return resultObjectSkip;
        }


        //ASN 1
        String asn1_locationId = "";
        String asn1_productId = "";
        if(materialGlobalEntity == null) {
            FailData failData = writeFailData(shipNotifEntity, IConstant.FAILED.ERROR_CODE.ASN9b, "Both (material_global_v1-primaryPlanningCode) and (material_global_v1-MaterialNumber) are Blank ");
            resultObject.setFailData(failData);
            return resultObject;
        }

        asn1_productId = materialGlobalEntity.getPrimaryPlanningCode();
        if(null == asn1_productId || asn1_productId.isEmpty()){
            asn1_productId = materialGlobalEntity.getMaterialNumber();
        }
        asn1_locationId = shipNotifEntity.getSrcSysCd() + IConstant.VALUE.UNDERLINE + localPlant;
        stockBo.setStockId(asn1_productId + IConstant.VALUE.BACK_SLANT + asn1_locationId + IConstant.VALUE.BACK_SLANT + shipNotifEntity.getDelvDocId() + IConstant.VALUE.BACK_SLANT + shipNotifEntity.getDelvLineNbr());

        //ASN 2
        stockBo.setActive(IConstant.VALUE.YES);
        stockBo.setActiveOPRERP(IConstant.VALUE.YES);

        //ASN 3
        stockBo.setBatchId(asn1_productId + IConstant.VALUE.BACK_SLANT + asn1_locationId + IConstant.VALUE.BACK_SLANT + shipNotifEntity.getLocalBatchNo());

        //ASN 4
        stockBo.setInventoryLinkGroupId("");

        //ASN 5
        stockBo.setCertaintyId(IConstant.VALUE.LA); //default

        //ASN 6
        if( shipNotifEntity.getLocalDeliveryCatg()!= null &&  shipNotifEntity.getLocalDeliveryCatg().equals(IConstant.VALUE.SEVEN) && StringUtils.isBlank(shipNotifEntity.getActGRDt()) ) {
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
        localPlant = asn7Rule(shipNotifEntity, localPlant);
        if(localPlant.isEmpty()){
            return resultObjectSkip;
        } else {
            stockBo.setLocationId(shipNotifEntity.getSrcSysCd() + IConstant.VALUE.UNDERLINE + localPlant);
        }

        //ASN 8
        stockBo.setTransitDate(IConstant.VALUE.TRANSIT_DATE);

        //ASN 9
        String asn9_productId = asn9Rule(shipNotifEntity, materialGlobalEntity, localPlant);
        if(ERROR.equals(asn9_productId)){
            FailData failData = writeFailData(shipNotifEntity, IConstant.FAILED.ERROR_CODE.ASN9, "Both Primary Planning Code and Material Number are missing");
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
        stockBo.setStockType(IConstant.VALUE.MOVEMENT);

        //ASN 12
        // unused

        //ASN 13
        stockBo.setReceiptDate(asn13Rule(shipNotifEntity));

        //ASN 14
        stockBo.setBlockedQuantity(IConstant.VALUE.ZEROZERO);
        stockBo.setQualityQuantity(IConstant.VALUE.ZEROZERO);
        stockBo.setRestrictedQuantity(IConstant.VALUE.ZEROZERO);
        stockBo.setReturnsQuantity(IConstant.VALUE.ZEROZERO);
        stockBo.setTransferQuantity(IConstant.VALUE.ZEROZERO);
        stockBo.setUnrestrictedQuantity(IConstant.VALUE.ZEROZERO);

        //ASN 15
        stockBo.setActiveSOPERP(IConstant.VALUE.NO);

        if(null != purchaseEntity){
            //ASN 16 17
            stockBo.setProcessId(asn16Rule(purchaseEntity, stockBo));
            stockBo.setProcessTypeId(asn17Rule(purchaseEntity));
            if(stockBo.getProcessId().isEmpty() || stockBo.getProcessTypeId().isEmpty()){

                //reject
                return resultObjectSkip;
            }

            //ASN 18
            if( (purchaseEntity.getLineItemTypeCd() != null &&  purchaseEntity.getLineItemTypeCd().equals(IConstant.VALUE.TWO))){
                stockBo.setConsignment(IConstant.VALUE.YES);
            } else {
                stockBo.setConsignment(IConstant.VALUE.NO);
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
        ArrayList<PlanCnsPlanObjectFilterEntity> cnsPlanObjectFilterEntityList = new ArrayList<>(cnsPlanObjectFilterDao.getEntitiesWithSourceObjectTechNameAndSourceSystem(IConstant.VALUE.ADVANCE_SHIP_NOTIFICATIONS, shipNotifEntity.getSrcSysCd()));

        if( cnsPlanObjectFilterEntityList != null &&  !cnsPlanObjectFilterEntityList.isEmpty())

        {
            for(PlanCnsPlanObjectFilterEntity filterEntity : cnsPlanObjectFilterEntityList) {
                if( filterEntity.getSourceObjectAttribute1() != null &&  filterEntity.getSourceObjectAttribute1().equals(IConstant.VALUE.LOCAL_RECEIVING_PLANT)
                        && filterEntity.getSourceObjectAttribute1Value() != null && filterEntity.getSourceObjectAttribute1Value().equals(localPlant)

                        && filterEntity.getSourceObjectAttribute2Value()!= null
                        && shipNotifEntity.getLocalDeliveryType() != null
                        && filterEntity.getSourceObjectAttribute2() != null
                        && filterEntity.getSourceObjectAttribute2Value().equals(shipNotifEntity.getLocalDeliveryType())
                        && filterEntity.getSourceObjectAttribute2().equals((IConstant.VALUE.LOCAL_DELIVERY_TYPE))){

                    return shipNotifEntity.getDelvDocId();
                }
            }
        }
        return "";
    }

    private String asn7Rule(EDMAdvancedShipNotificationV1Entity shipNotifEntity, String localPlant){

        ArrayList<PlanCnsTlaneControlEntity> tlaneControlEntityList = new ArrayList(cnsTlaneControlDao.getEntityListWithTriangulationParams("*","*",
                shipNotifEntity.getSrcSysCd(),localPlant,"purchase_order",IConstant.VALUE.YES));

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
            String newLocalPlant = destinationLocation.substring(0,size+1);
            localPlant = newLocalPlant;
        }

        //Planning relevancy check
        EDMPlantV1Entity plantEntity = plantDao.getPlantWithSourceSystemAndLocalPlant(shipNotifEntity.getSrcSysCd(), localPlant);
        if(null != plantEntity && IConstant.VALUE.X.equals(plantEntity.getLocalPlanningRelevant()) ){
            return localPlant;
        }
        return "";
    }

    private String asn9Rule(EDMAdvancedShipNotificationV1Entity shipNotifEntity, EDMMaterialGlobalV1Entity materialGlobalEntity, String localPlant){
        PlanCnsMaterialPlanStatusEntity cnsMaterialPlanStatusEntity = cnsMaterialPlanStatusDao.getCnsMaterialPlanStatusDaoEntity(shipNotifEntity.getSrcSysCd(),shipNotifEntity.getMatlNum(),localPlant);

        if((cnsMaterialPlanStatusEntity != null && cnsMaterialPlanStatusEntity.getNoPlanRelevant() != null && cnsMaterialPlanStatusEntity.getNoPlanRelevant().equals(IConstant.VALUE.X)) ||
                (cnsMaterialPlanStatusEntity != null &&  cnsMaterialPlanStatusEntity.getSpRelevant() != null && cnsMaterialPlanStatusEntity.getSpRelevant().equals(IConstant.VALUE.X))){
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
                if( StringUtils.isBlank(purchaseEntity.getLineItemTypeCd()) || purchaseEntity.getLineItemTypeCd().equals(IConstant.VALUE.ZERO)){
                    processId = "SU" + IConstant.VALUE.BACK_SLANT + stockBo.getProductId() + IConstant.VALUE.BACK_SLANT + stockBo.getLocationId() + IConstant.VALUE.BACK_SLANT + purchaseEntity.getSupNum() + IConstant.VALUE.BACK_SLANT + "Default";
                } else {
                    processId = "TR" + IConstant.VALUE.BACK_SLANT + stockBo.getProductId() + IConstant.VALUE.BACK_SLANT + stockBo.getLocationId() + IConstant.VALUE.BACK_SLANT + purchaseEntity.getSuplPlntCd() + IConstant.VALUE.BACK_SLANT + purchaseEntity.getSupNum() ;
                }
                break;
            case "UB":
                processId = "TR" + IConstant.VALUE.BACK_SLANT + stockBo.getProductId() + IConstant.VALUE.BACK_SLANT + stockBo.getLocationId() + IConstant.VALUE.BACK_SLANT + purchaseEntity.getSuplPlntCd() + IConstant.VALUE.BACK_SLANT + purchaseEntity.getSupNum() ;
                break;
            case "ZLA":
            case "ZNB":
                processId = "TR" + IConstant.VALUE.BACK_SLANT + stockBo.getProductId() + IConstant.VALUE.BACK_SLANT + stockBo.getLocationId() + IConstant.VALUE.BACK_SLANT + purchaseEntity.getSuplPlntCd() + IConstant.VALUE.BACK_SLANT + purchaseEntity.getSupNum() ;
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
                if( StringUtils.isBlank(purchaseEntity.getLineItemTypeCd()) || purchaseEntity.getLineItemTypeCd().equals(IConstant.VALUE.ZERO)){
                    processTypeId = IConstant.VALUE.VENDOR_TRANSPORT;
                }
                break;
            case "UB":
                processTypeId = IConstant.VALUE.INTERNAL_TRANSPORT;
                break;
            case "ZLA":
            case "ZNB":
                processTypeId = IConstant.VALUE.EXTERNAL_TRANSPORT;
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
        failData.setFunctionalArea(IConstant.FAILED.FUNCTIONAL_AREA.DP);
        failData.setInterfaceID(IConstant.FAILED.INTERFACE_ID.OMP_GDM_STOCK_ASN);
        failData.setKey1(shipNotifEntity.getDelvDocId());
        failData.setKey2(shipNotifEntity.getDelvLineNbr());
        failData.setKey3(shipNotifEntity.getMatlNum());
        failData.setKey4("");
        failData.setKey5("");

        return failData;
    }

}
