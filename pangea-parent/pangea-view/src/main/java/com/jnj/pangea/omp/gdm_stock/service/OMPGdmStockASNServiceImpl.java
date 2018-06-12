package com.jnj.pangea.omp.gdm_stock.service;

import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.edm.EDMAdvancedShipNotificationV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialGlobalV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMPlantV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMPurchaseOrderOAV1DaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsMaterialPlanStatusDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanObjectFilterDaoImpl;
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
import java.util.Calendar;
import java.util.Date;

public class OMPGdmStockASNServiceImpl implements ICommonService {

    public static OMPGdmStockASNServiceImpl instance;

    public static OMPGdmStockASNServiceImpl getInstance() {
        if(null == instance) {
            instance = new OMPGdmStockASNServiceImpl();
        }
        return instance;
    }

    private EDMMaterialGlobalV1DaoImpl materialGlobalDao = EDMMaterialGlobalV1DaoImpl.getInstance();
    private EDMAdvancedShipNotificationV1DaoImpl advanceShipNotificationDao = EDMAdvancedShipNotificationV1DaoImpl.getInstance();
    private EDMPlantV1DaoImpl plantDao = EDMPlantV1DaoImpl.getInstance();
    private EDMPurchaseOrderOAV1DaoImpl purchaseDao = EDMPurchaseOrderOAV1DaoImpl.getInstance();
    private PlanCnsPlanObjectFilterDaoImpl cnsPlanObjectFilterDao = PlanCnsPlanObjectFilterDaoImpl.getInstance();
    private PlanCnsMaterialPlanStatusDaoImpl cnsMaterialPlanStatusDao = PlanCnsMaterialPlanStatusDaoImpl.getInstance();
    //private PlanCnsCertKeyDaoImpl cnsCertKeyDao = PlanCnsCertKeyDaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2){
        ResultObject resultObject = new ResultObject();

        OMPGdmStockBo stockBo = new OMPGdmStockBo();

        EDMAdvancedShipNotificationV1Entity shipNotifEntity = (EDMAdvancedShipNotificationV1Entity) o ;
        EDMMaterialGlobalV1Entity materialGlobalEntity = materialGlobalDao.getEntityWithLocalMaterialNumberAndSourceSystem(shipNotifEntity.getMatlNum(),shipNotifEntity.getSrcSysCd());
        EDMPlantV1Entity plantEntity = plantDao.getPlantWithSourceSystemAndLocalPlant(shipNotifEntity.getSrcSysCd(),shipNotifEntity.getLocalReceivingPlant());
        EDMPurchaseOrderOAV1Entity purchaseEntity = purchaseDao.getEntityByPoNumAndPoLineNumberAndSourceSystem(shipNotifEntity.getLocalRefDocNum(), shipNotifEntity.getLocRefDocLineNum(), shipNotifEntity.getSrcSysCd());
        PlanCnsMaterialPlanStatusEntity cnsMaterialPlanStatusEntity = cnsMaterialPlanStatusDao.getEntityWithLocalMaterialNumberAndSourceSystem(shipNotifEntity.getMatlNum(), shipNotifEntity.getSrcSysCd());

        //ASN rules
        String locationId = "";
        String productId = "";
        boolean skip = false;
        stockBo.setVendorId(shipNotifEntity.getVendorID());

        //ASN 1
        productId = materialGlobalEntity.getPrimaryPlanningCode();
        if(null == productId || productId.isEmpty()){
            productId = materialGlobalEntity.getMaterialNumber();
        }
        locationId = shipNotifEntity.getSrcSysCd() + IConstant.VALUE.UNDERLINE + shipNotifEntity.getLocalReceivingPlant();
        stockBo.setStockId(productId + IConstant.VALUE.BACK_SLANT + locationId + IConstant.VALUE.BACK_SLANT + shipNotifEntity.getDelvDocID() + IConstant.VALUE.BACK_SLANT + shipNotifEntity.getDelvLineNbr());

        //ASN 2
        stockBo.setActive(IConstant.VALUE.YES);
        stockBo.setActiveOPRERP(IConstant.VALUE.YES);

        //ASN 3
        stockBo.setBatchId(locationId + IConstant.VALUE.BACK_SLANT + productId + IConstant.VALUE.BACK_SLANT + shipNotifEntity.getLocalbatchNo());

        //ASN 4
        stockBo.setInventoryLinkGroupId(IConstant.VALUE.SPACE);

        //ASN 5
        stockBo.setCertaintyId(IConstant.VALUE.LA); //default

        //ASN 6 //to be corrected
        if(shipNotifEntity.getLocaldeliveryCatg().equals(IConstant.VALUE.SEVEN) && shipNotifEntity.getActGRDt().isEmpty()){
            PlanCnsPlanObjectFilterEntity cnsPlanObjectFilterEntity = cnsPlanObjectFilterDao.getEntityWithSourceObjectTechNameAndSourceSystem(IConstant.VALUE.ADVANCE_SHIP_NOTIFICATIONS, shipNotifEntity.getSrcSysCd());
            if(cnsPlanObjectFilterEntity.getSourceObjectAttribute1().equals(IConstant.VALUE.LOCAL_RECEIVING_PLANT) && cnsPlanObjectFilterEntity.getSourceObjectAttribute1Value().equals(shipNotifEntity.getLocalReceivingPlant())) {
                stockBo.setErpOrderId(shipNotifEntity.getDelvDocID());
            } else {
                //skip record
                skip = true;
            }
        } else {
            //skip record
            skip = true;
        }

        //ASN 7
        if(plantEntity.getLocalPlanningRelevant().equals(IConstant.VALUE.X)){
            stockBo.setLocationId(shipNotifEntity.getSrcSysCd() + IConstant.VALUE.UNDERLINE + shipNotifEntity.getLocalReceivingPlant());
        } else {
            skip = true;
        }

        //ASN 8
        stockBo.setTransitDate(IConstant.VALUE.TRANSIT_DATE);

        //ASN 9
        if(cnsMaterialPlanStatusEntity.getNoPlanRelevant().equals(IConstant.VALUE.X) || cnsMaterialPlanStatusEntity.getSpRelevant().equals(IConstant.VALUE.X)){
            if(materialGlobalEntity.getPrimaryPlanningCode().isEmpty()){
                stockBo.setProductId(materialGlobalEntity.getMaterialNumber());
            } else {
                stockBo.setProductId(materialGlobalEntity.getPrimaryPlanningCode());
            }

        } else {
            skip = true;
        }

        //ASN 10
        if(null != purchaseEntity){
            try {
                int gr = Integer.parseInt(purchaseEntity.getGrLeadTimeDays());
                String deliveryDateStr = purchaseEntity.getLocaldelvDt();
                //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");         // TO BE CHECKED
                Date d = dateFormat.parse(deliveryDateStr);
                Calendar cal = Calendar.getInstance();
                cal.setTime(d);

                cal.add(Calendar.DATE,gr);
                //determine weekday
                int dow = cal.get(Calendar.DAY_OF_WEEK);
                if(dow == 6){
                    cal.add(Calendar.DATE,2);
                } else if (dow == 7) {
                    cal.add(Calendar.DATE, 1);
                }

                //TODO: format date
                stockBo.setStartDate(cal.getTime().toString());

            } catch (Exception e){
                //TO LOG
            }
        }

        //ASN 11
        stockBo.setStockType(IConstant.VALUE.MOVEMENT);

        //ASN 12
        // unused

        //ASN 13
        stockBo.setReceiptDate(shipNotifEntity.getLocaldeliveryDate()); //may need formatting

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
            String processTypeId = "";
            String processId = "";

            //ASN 16 17
            String poTypeCd = purchaseEntity.getPoTypeCd();
            switch(poTypeCd){
                case "NB":
                    if(purchaseEntity.getLineItemTypeCd().isEmpty()){
                        processId = "SU" + IConstant.VALUE.BACK_SLANT + stockBo.getProductId() + IConstant.VALUE.BACK_SLANT + stockBo.getLocationId() + IConstant.VALUE.BACK_SLANT + purchaseEntity.getSupNum() + IConstant.VALUE.BACK_SLANT + "Default";
                        processTypeId = IConstant.VALUE.VENDOR_TRANSPORT;
                    } else {
                        processId = "TR" + IConstant.VALUE.BACK_SLANT + stockBo.getProductId() + IConstant.VALUE.BACK_SLANT + stockBo.getLocationId() + IConstant.VALUE.BACK_SLANT + purchaseEntity.getSuplPlntCd() + IConstant.VALUE.BACK_SLANT + purchaseEntity.getSupNum() ;
                    }
                    break;
                case "UB":
                    processId = "TR" + IConstant.VALUE.BACK_SLANT + stockBo.getProductId() + IConstant.VALUE.BACK_SLANT + stockBo.getLocationId() + IConstant.VALUE.BACK_SLANT + purchaseEntity.getSuplPlntCd() + IConstant.VALUE.BACK_SLANT + purchaseEntity.getSupNum() ;
                    processTypeId = IConstant.VALUE.INTERNAL_TRANSPORT;
                    break;
                case "ZLA":
                case "ZNB":
                    processId = "TR" + IConstant.VALUE.BACK_SLANT + stockBo.getProductId() + IConstant.VALUE.BACK_SLANT + stockBo.getLocationId() + IConstant.VALUE.BACK_SLANT + purchaseEntity.getSuplPlntCd() + IConstant.VALUE.BACK_SLANT + purchaseEntity.getSupNum() ;
                    processTypeId = IConstant.VALUE.EXTERNAL_TRANSPORT;
                    break;
                default:
                    skip = true;
            }
            stockBo.setProcessId(processId);
            stockBo.setProcessTypeId(processTypeId);

            //ASN 18
            if(purchaseEntity.getLineItemTypeCd().equals(IConstant.VALUE.TWO)){
                stockBo.setConsignment(IConstant.VALUE.YES);
            } else {
                stockBo.setConsignment(IConstant.VALUE.SPACE);
            }

        } else {
            skip = true;
        }


        //ASN 19
        double delvQty = Double.parseDouble(shipNotifEntity.getActlSkuDelvQty());
        if(delvQty > 0.0){
            stockBo.setQuantity(Double.toString(delvQty));
        } else {
            skip = true;
        }


        //ASN rules end

        if(skip){
            resultObject = null;
        } else {
            resultObject.setBaseBo(stockBo);
        }

        return resultObject;
    }



}
