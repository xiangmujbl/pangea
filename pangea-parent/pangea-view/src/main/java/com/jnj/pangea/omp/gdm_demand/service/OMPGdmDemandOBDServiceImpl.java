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
import com.jnj.pangea.common.entity.plan.PlanCnsPlanSoTypeInclExclEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsMaterialPlanStatusEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanObjectFilterEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsCustExclInclEntity;
import com.jnj.pangea.common.entity.plan.PlanLocMinShelfEnity;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanUnitEntity;
import com.jnj.pangea.common.dao.impl.edm.EDMOutboundDeliveryLineV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMSalesHistoryV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMSalesOrderV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialGlobalV1DaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanSoTypeInclExclDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsMaterialPlanStatusDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanObjectFilterDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsCustExclInclDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanLocMinShelfDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanUnitDaoImpl;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_demand.bo.OMPGdmDemandBo;

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
    private PlanCnsPlanSoTypeInclExclDaoImpl inclExclDao = PlanCnsPlanSoTypeInclExclDaoImpl.getInstance();
    private PlanCnsMaterialPlanStatusDaoImpl materialStatusDao = PlanCnsMaterialPlanStatusDaoImpl.getInstance();
    private PlanCnsPlanObjectFilterDaoImpl objectFilterDao = PlanCnsPlanObjectFilterDaoImpl.getInstance();
    private PlanCnsCustExclInclDaoImpl custExclInclDao = PlanCnsCustExclInclDaoImpl.getInstance();
    private PlanLocMinShelfDaoImpl minShelfDao = PlanLocMinShelfDaoImpl.getInstance();
    private PlanCnsPlanUnitDaoImpl unitDao = PlanCnsPlanUnitDaoImpl.getInstance();

    public List<ResultObject> buildView(String key, Object o, Object o2) {

        List<ResultObject> resultList = new ArrayList<ResultObject>();

        EDMOutboundDeliveryHeaderV1Entity obdHeaderEntity = (EDMOutboundDeliveryHeaderV1Entity) o;

        ArrayList<EDMOutboundDeliveryLineV1Entity> lineV1EntityList = new ArrayList(deliverLineDao.getOutboundDeliveryLinesByDelivceryDocId(obdHeaderEntity.getDelvDocId()));
        PlanCnsPlanUnitEntity unitEntity;
        ArrayList<PlanCnsPlanObjectFilterEntity> objectFilterEntityList = new ArrayList(objectFilterDao.getEntitiesWithSourceObjectTechNameAndSourceSystem( "outbound_delivery_header", obdHeaderEntity.getSrcSysCd()));
        ArrayList<PlanCnsCustExclInclEntity>  custExclInclEntityList;
        if(obdHeaderEntity.getLocalSalesOrg().isEmpty()){
            custExclInclEntityList = new ArrayList<PlanCnsCustExclInclEntity>();
        } else {
            custExclInclEntityList = new ArrayList(custExclInclDao.getEntityListWithSalesOrgAndSourceSystem(obdHeaderEntity.getLocalSalesOrg(),obdHeaderEntity.getSrcSysCd()));
        }


        String locationId;
        String productId;
        String tmp="";
        boolean skip;

        SimpleDateFormat dtf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar asn7date = asn6and7Rule(obdHeaderEntity);
        Calendar asn6date = asn7date;
        asn6date.set(Calendar.SECOND, 1);

        OMPGdmDemandBo gdmDemandBo;
        ResultObject resultObject;

        for(EDMOutboundDeliveryLineV1Entity obdLineEntity : lineV1EntityList) {
            skip = false;
            gdmDemandBo = new OMPGdmDemandBo();
            resultObject = new ResultObject();
            locationId = obdLineEntity.getSrcSysCd()+"_"+obdLineEntity.getShippingPlntCd();
            productId = asn12Rule(obdLineEntity);

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
            if(asn5Rule(obdHeaderEntity,obdLineEntity)){
                gdmDemandBo.setDemandId(productId + IConstant.VALUE.BACK_SLANT + locationId + IConstant.VALUE.BACK_SLANT + obdLineEntity.getDelvDocId() + IConstant.VALUE.BACK_SLANT + obdLineEntity.getDelvLineNbr());
            } else {
                skip = true;
                tmp = "ASN 5 * "+obdHeaderEntity.getDelvDocId();
            }

            //OBD 6
            gdmDemandBo.setDueDate(dtf.format(asn6date.getTime()));

            //OBD 7
            gdmDemandBo.setFromDueDate(dtf.format(asn7date.getTime()));

            //OBD 8
            for(PlanCnsPlanObjectFilterEntity filterEntity : objectFilterEntityList){
                if(filterEntity.getSourceObjectAttribute1().equals("") && filterEntity.getSourceObjectAttribute1Value().equals(obdLineEntity.getShippingPlntCd())){
                    if(filterEntity.getSourceObjectAttribute2Value().equals(obdLineEntity.getDelvDocId())){
                        //to check
                        gdmDemandBo.setInventoryLinkGroupId(gdmDemandBo.getDemandId());
                        break;
                    }

                }
            }

            //OBD 9
            gdmDemandBo.setLocationId(locationId);

            //OBD 10
            gdmDemandBo.setMinRemainingShelfLife(asn10Rule(obdLineEntity));

            //OBD 11
            gdmDemandBo.setPlanningStrategy("ProductLocationBalanced");

            //OBD 12  (See top)
            if(productId.isEmpty()){
                skip = true;
                tmp = "ASN 12 * "+obdHeaderEntity.getDelvDocId();
            } else {
                //LogUtil.getLogger().info("product ID ++++++ "+productId);
                gdmDemandBo.setProductId(productId);
            }

            //OBD 13
            unitEntity = unitDao.getCnsPlanUnitEntityWithSourceSystemAndLocalUom(obdLineEntity.getSrcSysCd(),obdLineEntity.getBaseUnitOfMeasureCd());
            if(null != unitEntity){
                gdmDemandBo.setUnitId(unitEntity.getLocalUom());
            } else {
                //todo: reject record.
            }

            //OBD 14
            gdmDemandBo.setWRK02("");

            //OBD 15
            if(obdLineEntity.getShippedQty().isEmpty() || obdLineEntity.getShippedQty().equals("0")){
                skip = true;
                tmp = "ASN 15 * "+obdHeaderEntity.getDelvDocId() +" ship qty: "+obdLineEntity.getShippedQty();
            }
            gdmDemandBo.setQuantity(obdLineEntity.getShippedQty());

            //OBD 16
            if(asn16Rule(custExclInclEntityList,obdHeaderEntity)){
                gdmDemandBo.setCustomerId(obdHeaderEntity.getShipToCustNum());
            } else {
                skip = true;
                tmp = "ASN 16 * "+obdHeaderEntity.getDelvDocId();
            }

            //
            if(!skip) {
                LogUtil.getLogger().info("++++++++++++++++++++++++++ OK");
                resultObject.setBaseBo(gdmDemandBo);
                resultList.add(resultObject);
            } else {
                LogUtil.getLogger().info("++++++++++++++++++++++++++ "+tmp);
                //resultObject.setBaseBo(gdmDemandBo);
                //resultList.add(resultObject);
            }

        }

        return resultList;
    }

    private boolean asn5Rule(EDMOutboundDeliveryHeaderV1Entity obdHeaderEntity, EDMOutboundDeliveryLineV1Entity obdLineEntity){
        if(obdHeaderEntity.getActlGiDt().isEmpty()) {
            EDMSalesHistoryV1Entity salesHistoryEntity = salesHistoryDao.getFirstSalesHistoryForDeliveryDoc(obdHeaderEntity.getDelvDocId(), obdLineEntity.getDelvLineNbr(), obdHeaderEntity.getSrcSysCd(), IConstant.VALUE.J); //use first record of result to derive the value
            if(null != salesHistoryEntity){
                EDMSalesOrderV1Entity salesOrderEntity = salesOrderDao.getSalesOrderForHistoryDoc(salesHistoryEntity.getLocalPrecDocNo(), salesHistoryEntity.getLocalSPrecDocLnNo(), obdHeaderEntity.getSrcSysCd());
                PlanCnsPlanSoTypeInclExclEntity inclExclEntity = inclExclDao.getEntityWithParam(obdHeaderEntity.getLocalSalesOrg(), salesOrderEntity.getLocalOrderType(), salesOrderEntity.getLocalPlant(), obdHeaderEntity.getSrcSysCd(), IConstant.VALUE.I);

                if (null != inclExclEntity) {
                    //return (productId + IConstant.VALUE.BACK_SLANT + locationId + IConstant.VALUE.BACK_SLANT + obdLineEntity.getDelvDocId() + IConstant.VALUE.BACK_SLANT + obdLineEntity.getDelvLineNbr());
                    return true;
                } else {
                    LogUtil.getLogger().info("++++++ inclExclEntity EMPTY ");
                }
            } else {
                LogUtil.getLogger().info("++++++ salesHistoryEntity EMPTY "+obdLineEntity.getDelvDocId());
            }
        } else {
            LogUtil.getLogger().info("++++++ ActlGiDt : "+obdHeaderEntity.getActlGiDt());
        }
        return false;
    }

    private Calendar asn6and7Rule(EDMOutboundDeliveryHeaderV1Entity obdHeaderEntity){
        try{
            if(!obdHeaderEntity.getPlanGiDt().isEmpty()) {
                Calendar cal = Calendar.getInstance();
                DateFormat dt1 = new SimpleDateFormat("yyyyMMdd");
                cal.setTime(dt1.parse(obdHeaderEntity.getPlanGiDt()));
                cal.set(Calendar.HOUR, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                //return dt2.format(cal.getTime());
                return cal;
            }
        } catch (Exception e) {
            LogUtil.getCoreLog().catching(e);
        }
        return null;
    }

    private String asn10Rule(EDMOutboundDeliveryLineV1Entity obdLineEntity){
        PlanLocMinShelfEnity minShelfEntity = minShelfDao.getEntityWithLocalMaterialNumberAndLocalPlant(obdLineEntity.getMatlNum(),obdLineEntity.getShippingPlntCd());
        if(null != minShelfEntity){
            return minShelfEntity.getMinShelfLife();
        } else {
            minShelfEntity = minShelfDao.getEntityWithLocalMaterialNumberAndLocalPlant(IConstant.VALUE.STAR,obdLineEntity.getShippingPlntCd());
            if(null != minShelfEntity){
                minShelfEntity.getMinShelfLife();
            } else {
                EDMMaterialGlobalV1Entity materialGlobalEntity = materialGlobalDao.getEntityWithLocalMaterialNumberAndSourceSystem(obdLineEntity.getMatlNum(),obdLineEntity.getSrcSysCd());
                if(null != materialGlobalEntity){
                     return materialGlobalEntity.getMinRemShelfLife();
                } else {
                    return "";
                }
            }
        }
        return "";
    }

    private String asn12Rule(EDMOutboundDeliveryLineV1Entity obdLineEntity){
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
                    } else {
                        LogUtil.getLogger().info("Material Global not found ++++++ "+obdLineEntity.getMatlNum());
                    }

                } else {
                    LogUtil.getLogger().info("Relevant not found ++++++ "+materialStatusEntity.getSpRelevant()+" + "+materialStatusEntity.getNoPlanRelevant());
                }
            } else {
                LogUtil.getLogger().info("Material not found ++++++ "+obdLineEntity.getMatlNum()+" + "+obdLineEntity.getShippingPlntCd()+" + "+obdLineEntity.getSrcSysCd());
            }

        } catch (Exception e){
            //LogUtil.getLogger().info("Exception ++++++ "+materialStatusEntity.toString());
            LogUtil.getLogger().catching(e);
        }

        return "";
    }

    private boolean asn16Rule(ArrayList<PlanCnsCustExclInclEntity>  custExclInclEntities, EDMOutboundDeliveryHeaderV1Entity obdHeaderEntity){
        for(PlanCnsCustExclInclEntity entity : custExclInclEntities){
            if(entity.getInclExcl().equals(IConstant.VALUE.I)){
                if(entity.getCustomerShipTo().equals("ALL") || entity.getCustomerShipTo().equals(obdHeaderEntity.getShipToCustNum())){
                    return true;
                }
            }
        }

        return false;
    }

}
