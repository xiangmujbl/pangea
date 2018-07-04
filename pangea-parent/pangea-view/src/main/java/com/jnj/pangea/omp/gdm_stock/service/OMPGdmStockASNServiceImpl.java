package com.jnj.pangea.omp.gdm_stock.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialGlobalV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMPlantV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMPurchaseOrderOAV1DaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsMaterialPlanStatusDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanObjectFilterDaoImpl;
import com.jnj.pangea.common.entity.CommonEntity;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.entity.edm.EDMPlantV1Entity;
import com.jnj.pangea.common.entity.edm.EDMPurchaseOrderOAV1Entity;
import com.jnj.pangea.common.entity.plan.PlanCnsMaterialPlanStatusEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanObjectFilterEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_stock.bo.OMPGdmStockBo;
import com.jnj.pangea.common.entity.edm.EDMAdvancedShipNotificationV1Entity;

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
    private PlanCnsPlanObjectFilterDaoImpl cnsPlanObjectFilterDao = PlanCnsPlanObjectFilterDaoImpl.getInstance();
    private PlanCnsMaterialPlanStatusDaoImpl cnsMaterialPlanStatusDao = PlanCnsMaterialPlanStatusDaoImpl.getInstance();


    @Override
    public ResultObject buildView(String key, Object o, Object o2){
        ResultObject resultObject = new ResultObject();
        ResultObject resultObjectSkip = new ResultObject();
        OMPGdmStockBo stockBo = new OMPGdmStockBo();

        EDMAdvancedShipNotificationV1Entity shipNotifEntity = (EDMAdvancedShipNotificationV1Entity) o ;
        EDMMaterialGlobalV1Entity materialGlobalEntity = materialGlobalDao.getEntityWithLocalMaterialNumberAndSourceSystem(shipNotifEntity.getMatlNum(),shipNotifEntity.getSrcSysCd());
        EDMPlantV1Entity plantEntity = plantDao.getPlantWithSourceSystemAndLocalPlant(shipNotifEntity.getSrcSysCd(),shipNotifEntity.getLocalReceivingPlant());
        EDMPurchaseOrderOAV1Entity purchaseEntity = purchaseDao.getEntityByPoNumAndPoLineNumberAndSourceSystem(shipNotifEntity.getLocalRefDocNum(), shipNotifEntity.getLocRefDocLineNum(), shipNotifEntity.getSrcSysCd());
        PlanCnsMaterialPlanStatusEntity cnsMaterialPlanStatusEntity = cnsMaterialPlanStatusDao.getEntityWithLocalMaterialNumberAndSourceSystem(shipNotifEntity.getMatlNum(), shipNotifEntity.getSrcSysCd());
        //ASN rules

        stockBo.setVendorId(shipNotifEntity.getVendorID());

        //ASN 1
        String asn1_locationId = "";
        String asn1_productId = "";
        asn1_productId = materialGlobalEntity.getPrimaryPlanningCode();
        if(null == asn1_productId || asn1_productId.isEmpty()){
            asn1_productId = materialGlobalEntity.getMaterialNumber();
        }
        asn1_locationId = shipNotifEntity.getSrcSysCd() + IConstant.VALUE.UNDERLINE + shipNotifEntity.getLocalReceivingPlant();
        stockBo.setStockId(asn1_productId + IConstant.VALUE.BACK_SLANT + asn1_locationId + IConstant.VALUE.BACK_SLANT + shipNotifEntity.getDelvDocID() + IConstant.VALUE.BACK_SLANT + shipNotifEntity.getDelvLineNbr());

        //ASN 2
        stockBo.setActive(IConstant.VALUE.YES);
        stockBo.setActiveOPRERP(IConstant.VALUE.YES);

        //ASN 3
        stockBo.setBatchId(asn1_productId + IConstant.VALUE.BACK_SLANT + asn1_locationId + IConstant.VALUE.BACK_SLANT + shipNotifEntity.getLocalbatchNo());

        //ASN 4
        stockBo.setInventoryLinkGroupId("");

        //ASN 5
        stockBo.setCertaintyID(IConstant.VALUE.LA); //default

        //ASN 6
        if(shipNotifEntity.getLocaldeliveryCatg().equals(IConstant.VALUE.SEVEN) && shipNotifEntity.getActGRDt().isEmpty()){
            String erp = asn6Rule(shipNotifEntity);
            if(erp.isEmpty()){
                return resultObjectSkip;
            }
            stockBo.setErpOrderId(erp);
        }

        //ASN 7
        if(plantEntity.getLocalPlanningRelevant().equals(IConstant.VALUE.X)){
            stockBo.setLocationId(shipNotifEntity.getSrcSysCd() + IConstant.VALUE.UNDERLINE + shipNotifEntity.getLocalReceivingPlant());
        } else {
            return resultObjectSkip;
        }

        //ASN 8
        stockBo.setTransitDate(IConstant.VALUE.TRANSIT_DATE);

        //ASN 9
        if(cnsMaterialPlanStatusEntity.getNoPlanRelevant().equals(IConstant.VALUE.X) || cnsMaterialPlanStatusEntity.getSpRelevant().equals(IConstant.VALUE.X)){
            if(!materialGlobalEntity.getPrimaryPlanningCode().isEmpty()){
                stockBo.setProductId(materialGlobalEntity.getPrimaryPlanningCode());
            } else if(!materialGlobalEntity.getMaterialNumber().isEmpty()){
                stockBo.setProductId(materialGlobalEntity.getMaterialNumber());
            } else {
                //reject
                FailData failData = writeFailData(materialGlobalEntity, IConstant.FAILED.ERROR_CODE.ASN9, "Both Primary Planning Code and Material Number are missing");
                resultObject.setFailData(failData);
                return resultObject;
            }
        } else {
            return resultObjectSkip;
        }

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
                return resultObjectSkip;
            }

            //ASN 18
            if(purchaseEntity.getLineItemTypeCd().equals(IConstant.VALUE.TWO)){
                stockBo.setConsignment(IConstant.VALUE.YES);
            } else {
                stockBo.setConsignment("");
            }

        } else {
            return resultObjectSkip;
        }


        //ASN 19
        double delvQty = Double.parseDouble(shipNotifEntity.getActlSkuDelvQty());
        if(delvQty > 0.0){
            stockBo.setQuantity(String.format("%.2f",delvQty));
        } else {
            return resultObjectSkip;
        }


        //ASN rules end

        resultObject.setBaseBo(stockBo);
        return resultObject;
    }

    private String asn6Rule(EDMAdvancedShipNotificationV1Entity shipNotifEntity){
        ArrayList<PlanCnsPlanObjectFilterEntity> cnsPlanObjectFilterEntityList = new ArrayList<>(cnsPlanObjectFilterDao.getEntitiesWithSourceObjectTechNameAndSourceSystem(IConstant.VALUE.ADVANCE_SHIP_NOTIFICATIONS, shipNotifEntity.getSrcSysCd()));

        if(!cnsPlanObjectFilterEntityList.isEmpty()) {
            for(PlanCnsPlanObjectFilterEntity filterEntity : cnsPlanObjectFilterEntityList) {
                if(filterEntity.getSourceObjectAttribute1().equals(IConstant.VALUE.LOCAL_RECEIVING_PLANT) && filterEntity.getSourceObjectAttribute1Value().equals(shipNotifEntity.getLocalReceivingPlant())){
                    //stockBo.setErpOrderId(shipNotifEntity.getDelvDocID());
                    return shipNotifEntity.getDelvDocID();
                }
            }
        }
        return "";
    }

    private String asn10Rule(EDMAdvancedShipNotificationV1Entity shipNotifEntity, EDMPurchaseOrderOAV1Entity purchaseEntity){
        try {
            if(!shipNotifEntity.getLocaldeliveryDate().isEmpty()) {
                int gr = 0;
                if (!purchaseEntity.getGrLeadTimeDays().isEmpty()) {
                    gr = Integer.parseInt(purchaseEntity.getGrLeadTimeDays());
                }
                DateFormat df1 = new SimpleDateFormat("yyyyMMdd");
                SimpleDateFormat df2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Calendar cal = Calendar.getInstance();
                cal.setTime(df1.parse(shipNotifEntity.getLocaldeliveryDate()));

                cal.add(Calendar.DATE, gr);
                //determine weekday
                int dow = cal.get(Calendar.DAY_OF_WEEK);
                if (dow == 6) {
                    cal.add(Calendar.DATE, 2);
                } else if (dow == 7) {
                    cal.add(Calendar.DATE, 1);
                }
                cal.set(Calendar.HOUR, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);

                //stockBo.setStartDate(df2.format(cal.getTime()));
                return df2.format(cal.getTime());
            }
        } catch (Exception e){
            LogUtil.getCoreLog().catching(e);
        }
        return "";
    }

    private String asn13Rule(EDMAdvancedShipNotificationV1Entity shipNotifEntity){
        try{
            if(!shipNotifEntity.getLocaldeliveryDate().isEmpty()) {
                Calendar cal = Calendar.getInstance();
                DateFormat dt1 = new SimpleDateFormat("yyyyMMdd");
                SimpleDateFormat dt2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                cal.setTime(dt1.parse(shipNotifEntity.getLocaldeliveryDate()));
                cal.set(Calendar.HOUR, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                //stockBo.setReceiptDate(dt2.format(cal.getTime()));
                return dt2.format(cal.getTime());
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
                if(purchaseEntity.getLineItemTypeCd().isEmpty()){
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
                if(purchaseEntity.getLineItemTypeCd().isEmpty()){
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

    private FailData writeFailData(EDMMaterialGlobalV1Entity materialEntity, String errorCode, String errorValue) {
        FailData failData = new FailData();
        failData.setErrorCode(errorCode);
        failData.setErrorValue(errorValue);
        failData.setFunctionalArea(IConstant.FAILED.FUNCTIONAL_AREA.DP);
        failData.setInterfaceID(IConstant.FAILED.INTERFACE_ID.OMP_GDM_STOCK_ASN);
        failData.setSourceSystem(materialEntity.getSourceSystem());
        failData.setKey1(materialEntity.getLocalMaterialNumber());
        failData.setKey2(materialEntity.getSourceSystem());
        failData.setKey3("");
        failData.setKey4("");
        failData.setKey5("");

        return failData;
    }

}
