package com.jnj.pangea.omp.gdm_demand.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.edm.EDMOutboundDeliveryHeaderV1Entity;
import com.jnj.pangea.common.entity.edm.EDMOutboundDeliveryLineV1Entity;
import com.jnj.pangea.common.entity.edm.EDMSalesHistoryV1Entity;
import com.jnj.pangea.common.entity.edm.EDMSalesOrderV1Entity;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.entity.edm.EDMPurchaseOrderOAV1Entity;
import com.jnj.pangea.common.entity.plan.PlanCnsMaterialPlanStatusEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanObjectFilterEntity;
import com.jnj.pangea.common.entity.plan.PlanLocMinShelfEnity;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanUnitEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsDemGrpAsgnEntity;
import com.jnj.pangea.common.entity.project_one.KnvhEntity;
import com.jnj.pangea.common.dao.impl.edm.EDMOutboundDeliveryLineV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMSalesHistoryV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMSalesOrderV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialGlobalV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMPurchaseOrderOAV1DaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsMaterialPlanStatusDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanObjectFilterDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanLocMinShelfDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanUnitDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsDemGrpAsgnDaoImpl;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOneKnvhDaoImpl;
import com.jnj.pangea.hook.OMPGdmDemandSalesOrderHook;
import com.jnj.pangea.omp.gdm_demand.bo.OMPGdmDemandBo;
import org.apache.commons.lang.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;

public class OMPGdmDemandOBDServiceImpl {

    private static OMPGdmDemandOBDServiceImpl instance;

    public static OMPGdmDemandOBDServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmDemandOBDServiceImpl();
        }
        return instance;
    }

    private EDMOutboundDeliveryLineV1DaoImpl deliverLineDao = EDMOutboundDeliveryLineV1DaoImpl.getInstance();
    private EDMSalesHistoryV1DaoImpl salesHistoryDao = EDMSalesHistoryV1DaoImpl.getInstance();
    private EDMSalesOrderV1DaoImpl salesOrderDao = EDMSalesOrderV1DaoImpl.getInstance();
    private EDMMaterialGlobalV1DaoImpl materialGlobalDao = EDMMaterialGlobalV1DaoImpl.getInstance();
    private EDMPurchaseOrderOAV1DaoImpl purchaseOrderDao = EDMPurchaseOrderOAV1DaoImpl.getInstance();
    private PlanCnsMaterialPlanStatusDaoImpl materialStatusDao = PlanCnsMaterialPlanStatusDaoImpl.getInstance();
    private PlanCnsPlanObjectFilterDaoImpl objectFilterDao = PlanCnsPlanObjectFilterDaoImpl.getInstance();
    private PlanLocMinShelfDaoImpl minShelfDao = PlanLocMinShelfDaoImpl.getInstance();
    private PlanCnsPlanUnitDaoImpl unitDao = PlanCnsPlanUnitDaoImpl.getInstance();
    private PlanCnsDemGrpAsgnDaoImpl demGrpAsgnDao = PlanCnsDemGrpAsgnDaoImpl.getInstance();
    private ProjectOneKnvhDaoImpl knvhDao = ProjectOneKnvhDaoImpl.getInstance();

    private static final String ERROR_REJECT = "ERROR";

    public List<ResultObject> buildView(String key, Object o, Object o2) {

        List<ResultObject> resultList = new ArrayList<ResultObject>();

        EDMOutboundDeliveryHeaderV1Entity obdHeaderEntity = (EDMOutboundDeliveryHeaderV1Entity) o;

        ArrayList<EDMOutboundDeliveryLineV1Entity> lineV1EntityList = new ArrayList(deliverLineDao.getOutboundDeliveryLinesByDeliveryDocId(obdHeaderEntity.getDelvDocId()));
        EDMPurchaseOrderOAV1Entity purchaseEntity;
        PlanCnsPlanUnitEntity unitEntity;
        EDMSalesOrderV1Entity salesOrderEntity;
        EDMSalesHistoryV1Entity salesHistoryEntity;
        ArrayList<PlanCnsPlanObjectFilterEntity> objectFilterEntityList = new ArrayList(objectFilterDao.getEntitiesWithSourceObjectTechNameAndSourceSystem( IConstant.PLAN_CNS_PLAN_OBJECT_FILTER.SOURCE_FILTER_SOURCE_OBJECT_TECHNAME_OUTBOUND_DELIVERY_HEADER, obdHeaderEntity.getSrcSysCd()));

        String locationId;
        String productId;
        boolean skip;
        boolean fail;

        SimpleDateFormat dtf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar obd7date = obd6and7Rule(obdHeaderEntity);
        Calendar obd6date = Calendar.getInstance();
        if(null != obd7date) {
            obd6date.setTime(obd7date.getTime());
            obd6date.add(Calendar.SECOND, 1);
        }

        OMPGdmDemandBo gdmDemandBo;
        ResultObject resultObject;
        FailData failData;

        for(EDMOutboundDeliveryLineV1Entity obdLineEntity : lineV1EntityList) {
            //LogUtil.getLogger().info("######################### docId "+obdLineEntity.getDelvDocId() + " ** ln: "+obdLineEntity.getDelvLineNbr() + " ** matl: "+obdLineEntity.getMatlNum());
            skip = false;
            fail = false;
            gdmDemandBo = new OMPGdmDemandBo();
            resultObject = new ResultObject();
            failData = new FailData();
            purchaseEntity = purchaseOrderDao.getEntityByPoNumAndPoLineNumberAndSourceSystem(obdLineEntity.getSlsOrdrNum(), obdLineEntity.getSlsOrdrLineNbr(), obdLineEntity.getSrcSysCd());
            salesHistoryEntity = salesHistoryDao.getFirstSalesHistoryForDeliveryDoc(obdHeaderEntity.getDelvDocId(), obdLineEntity.getDelvLineNbr(), obdHeaderEntity.getSrcSysCd(), IConstant.VALUE.J);
            if(null != salesHistoryEntity) {
                salesOrderEntity = salesOrderDao.getSalesOrderForHistoryDoc(salesHistoryEntity.getLocalPrecDocNo(), salesHistoryEntity.getLocalSPrecDocLnNo(), obdHeaderEntity.getSrcSysCd());
            } else {
                salesOrderEntity = null;
            }
            locationId = obdLineEntity.getSrcSysCd()+IConstant.VALUE.UNDERLINE+obdLineEntity.getShippingPlntCd();
            productId = obd12Rule(obdLineEntity);
            //

            //OBD 1
            gdmDemandBo.setActive(IConstant.VALUE.YES);
            gdmDemandBo.setActiveOPRERP(IConstant.VALUE.YES);

            //OBD 2
            gdmDemandBo.setActiveFCTERP(IConstant.VALUE.NO);
            gdmDemandBo.setActiveOBDPERP(IConstant.VALUE.NO);

            //OBD 3
            if (obdLineEntity.getBtchNum().isEmpty()) {
                gdmDemandBo.setBatchId("");
            } else {
                gdmDemandBo.setBatchId(productId + IConstant.VALUE.BACK_SLANT + locationId + IConstant.VALUE.BACK_SLANT + obdLineEntity.getBtchNum());
            }

            //OBD 4
            gdmDemandBo.setCertaintyId(IConstant.VALUE.CERTAINTY_VJ);

            //OBD 5
            boolean obd5 = true;
            if(null == purchaseEntity){
                //OBD is against STO
                obd5 = obd5Rule(obdHeaderEntity,salesOrderEntity);
            }
            //OBD is against SO
            if(obd5) {
                gdmDemandBo.setDemandId(productId + IConstant.VALUE.BACK_SLANT + locationId + IConstant.VALUE.BACK_SLANT + obdLineEntity.getDelvDocId() + IConstant.VALUE.BACK_SLANT + obdLineEntity.getDelvLineNbr());
            } else {
               skip = true;
            }

            if(null != obd7date) {
                //OBD 6
                gdmDemandBo.setDueDate(dtf.format(obd6date.getTime()));

                //OBD 7
                gdmDemandBo.setFromDueDate(dtf.format(obd7date.getTime()));
            }

            //OBD 8
            boolean empty = true;
            for(PlanCnsPlanObjectFilterEntity filterEntity : objectFilterEntityList){
                if(filterEntity.getSourceObjectAttribute1().equals(IConstant.VALUE.SHIPPING_PLANT_NUMBER) && filterEntity.getSourceObjectAttribute1Value().equals(obdLineEntity.getShippingPlntCd())){
                    if(filterEntity.getSourceObjectAttribute2Value().equals(obdHeaderEntity.getDelvTypeCd())){
                        gdmDemandBo.setInventoryLinkGroupId(gdmDemandBo.getDemandId());
                        empty = false;
                        break;
                    }

                }
            }
            if(empty){
              skip = true;
            }

            //OBD 9
            gdmDemandBo.setLocationId(locationId);

            //OBD 10
            gdmDemandBo.setMinRemainingShelfLife(obd10Rule(obdLineEntity));

            //OBD 11
            gdmDemandBo.setPlanningStrategy(IConstant.VALUE.PRODUCT_LOCATION_BALANCED);

            //OBD 12  (See top)
            if(productId.isEmpty()){
               skip = true;
            } else {
                gdmDemandBo.setProductId(productId);
            }

            //OBD 13
            unitEntity = unitDao.getCnsPlanUnitEntityWithSourceSystemAndLocalUom(obdLineEntity.getSrcSysCd(),obdLineEntity.getBaseUnitOfMeasureCd());
            if(null != unitEntity){
                gdmDemandBo.setUnitId(unitEntity.getUnit());
            } else {
                //reject record.
                failData = writeFailData(IConstant.FAILED.ERROR_CODE.OBD13, "Missing Enterprise Unit", obdHeaderEntity, obdLineEntity);
                fail = true;
            }

            //OBD 14
            gdmDemandBo.setWRK02("");

            //OBD 15
            if(obdLineEntity.getActlSkuDelvQty().isEmpty() || obdLineEntity.getActlSkuDelvQty().equals(IConstant.VALUE.ZEROZERO)){
              skip = true;
            }
            gdmDemandBo.setQuantity(obdLineEntity.getActlSkuDelvQty());

            //OBD 16
            if(null != purchaseEntity){
                //OBD against STO
                gdmDemandBo.setCustomerId(obdHeaderEntity.getShipToCustNum());
            } else {
                //OBD against SO
                String grp = obd16Rule(objectFilterEntityList, obdHeaderEntity, salesOrderEntity);
                if(grp.equals(ERROR_REJECT)){
                    //reject record.
                    failData = writeFailData(IConstant.FAILED.ERROR_CODE.OBD16, "Missing Demand Group", obdHeaderEntity, obdLineEntity);
                    fail = true;
                } else {
                    if (grp.isEmpty()) {
                        skip = true;

                    } else {
                        gdmDemandBo.setCustomerId(grp);
                    }
                }
            }

            if(fail){
                resultObject.setFailData(failData);
                resultList.add(resultObject);
            } else {
                if (!skip) {
                    resultObject.setBaseBo(gdmDemandBo);
                    resultList.add(resultObject);
                }
            }

        }
        return resultList;
    }

    private boolean obd5Rule(EDMOutboundDeliveryHeaderV1Entity obdHeaderEntity, EDMSalesOrderV1Entity salesOrderEntity){
        if(null != salesOrderEntity) {
            PlanCnsPlanObjectFilterEntity objectFilterEntity = objectFilterDao.getEntityWithSourceObjectTechNameAndSourceSystemAndParams
                    (IConstant.PLAN_CNS_PLAN_OBJECT_FILTER.SOURCE_FILTER_SOURCE_OBJECT_TECHNAME_SALES_ORDER,obdHeaderEntity.getSrcSysCd(),
                            IConstant.PLAN_CNS_PLAN_OBJECT_FILTER.SOURCE_FILTER_SOURCE_OBJECT_TECHNAME_LOCAL_PLANT,salesOrderEntity.getLocalPlant(),
                            IConstant.PLAN_CNS_PLAN_OBJECT_FILTER.SOURCE_FILTER_SOURCE_OBJECT_TECHNAME_LOCAL_ORDER_TYPE,salesOrderEntity.getLocalOrderType(),
                            IConstant.VALUE.I);

            if (null != objectFilterEntity) {
                return true;
            }
        }

        return false;
    }

    private Calendar obd6and7Rule(EDMOutboundDeliveryHeaderV1Entity obdHeaderEntity){
        try{
            if(!obdHeaderEntity.getPlanGiDt().isEmpty()) {
                Calendar cal = Calendar.getInstance();
                DateFormat dt1 = new SimpleDateFormat("yyyyMMdd");
                cal.setTime(dt1.parse(obdHeaderEntity.getPlanGiDt()));
                cal.set(Calendar.HOUR, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                return cal;
            }
        } catch (Exception e) {
            LogUtil.getCoreLog().error(e.getMessage());
        }
        return null;
    }

    private String obd10Rule(EDMOutboundDeliveryLineV1Entity obdLineEntity){
        PlanLocMinShelfEnity minShelfEntity = minShelfDao.getEntityWithLocalMaterialNumberAndLocalPlant(obdLineEntity.getMatlNum(),obdLineEntity.getShippingPlntCd());
        if(null != minShelfEntity){
            return minShelfEntity.getMinShelfLife();
        } else {
            minShelfEntity = minShelfDao.getEntityWithLocalPlant(obdLineEntity.getShippingPlntCd());
            if(null != minShelfEntity){
                return minShelfEntity.getMinShelfLife();
            } else {
                EDMMaterialGlobalV1Entity materialGlobalEntity = materialGlobalDao.getEntityWithLocalMaterialNumberAndSourceSystem(obdLineEntity.getMatlNum(),obdLineEntity.getSrcSysCd());
                if(null != materialGlobalEntity){
                    return materialGlobalEntity.getMinRemShelfLife();
                } else {
                    return "";
                }
            }
        }
    }

    private String obd12Rule(EDMOutboundDeliveryLineV1Entity obdLineEntity){
        PlanCnsMaterialPlanStatusEntity materialStatusEntity;
        EDMMaterialGlobalV1Entity materialGlobalEntity;
        materialStatusEntity = materialStatusDao.getEntityWithLocalMaterialNumberAndlLocalPlantAndSourceSystem(obdLineEntity.getMatlNum(),obdLineEntity.getShippingPlntCd(),obdLineEntity.getSrcSysCd());
        try {
            if(null != materialStatusEntity) {
                if (IConstant.VALUE.X.equals(materialStatusEntity.getSpRelevant()) || IConstant.VALUE.X.equals(materialStatusEntity.getNoPlanRelevant())) {

                    materialGlobalEntity = materialGlobalDao.getEntityWithLocalMaterialNumberAndSourceSystem(obdLineEntity.getMatlNum(), obdLineEntity.getSrcSysCd());
                    if(null != materialGlobalEntity) {
                        if (materialGlobalEntity.getPrimaryPlanningCode().isEmpty()) {
                            return materialGlobalEntity.getMaterialNumber();
                        } else {
                            return materialGlobalEntity.getPrimaryPlanningCode();
                        }
                    }

                }
            }

        } catch (Exception e){
            LogUtil.getLogger().catching(e);
        }

        return "";
    }

    private String obd16Rule(ArrayList<PlanCnsPlanObjectFilterEntity>  filterEntities, EDMOutboundDeliveryHeaderV1Entity obdHeaderEntity, EDMSalesOrderV1Entity salesOrderV1Entity){
        boolean customerFilter = false;
        if (filterEntities.isEmpty()) {
            return "";
        }

        for(PlanCnsPlanObjectFilterEntity filterEntity : filterEntities){
            if(filterEntity.getSourceObjectAttribute1().equals(IConstant.EDM_OUTBOUND_DELIVERY_HEADER_V1.LOCAL_SALESORG) &&
                    filterEntity.getSourceObjectAttribute1Value().equals(obdHeaderEntity.getLocalSalesOrg())) {
                if(filterEntity.getSourceObjectAttribute2().equals(IConstant.EDM_OUTBOUND_DELIVERY_HEADER_V1.SHIPTO_CUST_NUM)
                        && filterEntity.getSourceObjectAttribute2Value().equals(obdHeaderEntity.getShipToCustNum())
                        && filterEntity.getInclusionExclusion().equals(IConstant.VALUE.E)) {

                    return "";
                }


                if(filterEntity.getSourceObjectAttribute2().equals(IConstant.EDM_OUTBOUND_DELIVERY_HEADER_V1.SHIPTO_CUST_NUM)
                        && (filterEntity.getSourceObjectAttribute2Value().equals(obdHeaderEntity.getShipToCustNum()) ||
                        filterEntity.getSourceObjectAttribute2Value().equals(IConstant.VALUE.ALL))
                        && filterEntity.getInclusionExclusion().equals(IConstant.VALUE.I)) {
                    customerFilter = true;
                    break;
                }
            }
        }


        if (customerFilter) {
            String dgrp = getDemandGroup(obdHeaderEntity, salesOrderV1Entity);
            if (StringUtils.isEmpty(dgrp) && salesOrderV1Entity != null) {
                // if not found there, recursive find on the KNVH region
                dgrp = OMPGdmDemandSalesOrderHook.determineCustomerId(salesOrderV1Entity.getLocalShipToParty(),
                        salesOrderV1Entity.getLocalSalesOrg(), salesOrderV1Entity.getLocalOrderCreateDt());
            }
            if (StringUtils.isEmpty(dgrp)) {
                return ERROR_REJECT;
            } else {
                return dgrp;
            }
        }
        return "";
    }

    private String getDemandGroup(EDMOutboundDeliveryHeaderV1Entity obdHeaderEntity, EDMSalesOrderV1Entity salesOrderV1Entity) {
        PlanCnsDemGrpAsgnEntity grpAsgnEntity1, grpAsgnEntity2;
        if(null != salesOrderV1Entity){
            grpAsgnEntity1 = demGrpAsgnDao.getEntityWithCustomerIdAndSalesOrganization(salesOrderV1Entity.getLocalShipToParty(), salesOrderV1Entity.getLocalSalesOrg());
            if(null != grpAsgnEntity1) {
                if(!grpAsgnEntity1.getDemandGroup().isEmpty()){
                    return grpAsgnEntity1.getDemandGroup();
                }
            }
        }

        grpAsgnEntity2 = demGrpAsgnDao.getEntityWithCustomerIdAndSalesOrganization(obdHeaderEntity.getShipToCustNum(), obdHeaderEntity.getLocalSalesOrg());
        if(null != grpAsgnEntity2) {
            if(!grpAsgnEntity2.getDemandGroup().isEmpty()){
                return grpAsgnEntity2.getDemandGroup();
            }
        }

        return "";
    }

    private String queryKNVH(String custNum, String localSalesOrg, String localOrderCreateDt){
        PlanCnsDemGrpAsgnEntity grpAsgnEntity;
        KnvhEntity knvh = knvhDao.getEntityWithFourConditions(custNum, localSalesOrg, IConstant.VALUE.A, localOrderCreateDt);
        if (null != knvh) {
            grpAsgnEntity = demGrpAsgnDao.getEntityWithCustomerId(knvh.getHkunnr());
            if (null != grpAsgnEntity && !grpAsgnEntity.getDemandGroup().isEmpty()) {
                return grpAsgnEntity.getDemandGroup();
            }

            String tempId = knvh.getHkunnr();
            while(null != knvh){
                knvh = knvhDao.getEntityWithFourConditions(tempId, localSalesOrg, IConstant.VALUE.A, localOrderCreateDt);
                if (null != knvh) {
                    tempId = knvh.getHkunnr();
                    grpAsgnEntity = demGrpAsgnDao.getEntityWithCustomerId(tempId);
                    if (null != grpAsgnEntity && !grpAsgnEntity.getDemandGroup().isEmpty()) {
                        return grpAsgnEntity.getDemandGroup();
                    }
                }
            }

        }
        return "";
    }

    private FailData writeFailData(String errorCode, String errorValue, EDMOutboundDeliveryHeaderV1Entity obdHeaderEntity, EDMOutboundDeliveryLineV1Entity obdLineEntity ) {
        FailData failData = new FailData();
        failData.setErrorCode(errorCode);
        failData.setErrorValue(errorValue);
        failData.setFunctionalArea(IConstant.FAILED.FUNCTIONAL_AREA.SP);
        failData.setInterfaceID(IConstant.FAILED.INTERFACE_ID.OMP_GDM_DEMAND_OBD);
        failData.setSourceSystem(obdHeaderEntity.getSrcSysCd());
        failData.setKey1(obdHeaderEntity.getDelvDocId());
        failData.setKey2(obdLineEntity.getDelvLineNbr());
        failData.setKey3("");
        failData.setKey4("");
        failData.setKey5("");

        return failData;
    }


}
