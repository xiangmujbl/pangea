package com.jnj.pangea.omp.gdm_stock.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialGlobalV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMPlantV1DaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsMaterialPlanStatusDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanObjectFilterDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanSplPlnLocDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.entity.edm.EDMPlantV1Entity;
import com.jnj.pangea.common.entity.edm.EDMPurchaseRequisitionV1Entity;
import com.jnj.pangea.common.entity.plan.PlanCnsMaterialPlanStatusEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanObjectFilterEntity;
import com.jnj.pangea.common.entity.plan.PlanSplPlnLocEntity;
import com.jnj.pangea.common.exception.SkipRecordException;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_stock.bo.OMPGdmStockBo;
import com.jnj.pangea.util.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.Date;
import java.util.List;

public class OMPGdmStockPurchaseRequisitionServiceImpl implements ICommonService {

    private static final String ZERO_POINT_ZERO = "0.0";
    private static final String MOVEMENT = "movement";
    private static final String TRANSIT_DATE = "1980/01/01 00:00:00";

    private static OMPGdmStockPurchaseRequisitionServiceImpl instance;

    public static OMPGdmStockPurchaseRequisitionServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmStockPurchaseRequisitionServiceImpl();
        }
        return instance;
    }

    private EDMMaterialGlobalV1DaoImpl edmMaterialGlobalV1Dao = EDMMaterialGlobalV1DaoImpl.getInstance();
    private EDMPlantV1DaoImpl edmPlantV1Dao = EDMPlantV1DaoImpl.getInstance();
    private PlanCnsMaterialPlanStatusDaoImpl planCnsMaterialPlanStatusDao = PlanCnsMaterialPlanStatusDaoImpl.getInstance();
    private PlanSplPlnLocDaoImpl planSplPlnLocDao = PlanSplPlnLocDaoImpl.getInstance();
    private PlanCnsPlanObjectFilterDaoImpl planCnsPlanObjectFilterDao = PlanCnsPlanObjectFilterDaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {
        try {
            EDMPurchaseRequisitionV1Entity edmPurchaseRequisitionV1Entity = (EDMPurchaseRequisitionV1Entity) o;
            OMPGdmStockBo OMPGdmStockBo = new OMPGdmStockBo();
            LogUtil.getCoreLog().info("\n\nMCX; key: " + key + "\n\n");

            // PR5 - we are to skip this record if prCatCd in not "B"
            if (!"B".equals(edmPurchaseRequisitionV1Entity.getPrCatCd())) {
                throw new SkipRecordException("PR5");
            }

            // determine the productId, locationId and stockId
            String productId = getProductId(edmPurchaseRequisitionV1Entity);
            String locationId = edmPurchaseRequisitionV1Entity.getSourceSystem() + "_" +
                    edmPurchaseRequisitionV1Entity.getPlntCd();
            String stockId = productId + "/" + locationId + "/" + edmPurchaseRequisitionV1Entity.getPrNum() + "/" +
                    edmPurchaseRequisitionV1Entity.getPrLineNbr();

            OMPGdmStockBo.setStockId(stockId);
            OMPGdmStockBo.setConsignment(getConsignment(edmPurchaseRequisitionV1Entity));

            checkPlanObjectFilterValid(edmPurchaseRequisitionV1Entity);
            OMPGdmStockBo.seteRPOrderId(edmPurchaseRequisitionV1Entity.getPrNum());

            if (StringUtils.isNotBlank(edmPurchaseRequisitionV1Entity.getSuplPlntCd())) {
                OMPGdmStockBo.setInventoryLinkGroupId(stockId);
            }
            OMPGdmStockBo.setVendorId(edmPurchaseRequisitionV1Entity.getLocalSupNum());
            OMPGdmStockBo.setProcessId(getProcessId(productId, locationId, edmPurchaseRequisitionV1Entity));
            OMPGdmStockBo.setProcessTypeId(getProcessTypeId(edmPurchaseRequisitionV1Entity));
            OMPGdmStockBo.setLocationId(getLocationId(edmPurchaseRequisitionV1Entity));

            checkProductIdValid(edmPurchaseRequisitionV1Entity);
            OMPGdmStockBo.setProductId(productId);

            OMPGdmStockBo.setQuantity(String.valueOf(Long.parseLong(edmPurchaseRequisitionV1Entity.getPrLineQty()) -
                    Long.parseLong(edmPurchaseRequisitionV1Entity.getLocalPOQuantity())));

            // PR12
            Date needByDate = DateUtils.stringToDate(edmPurchaseRequisitionV1Entity.getNeedByDt(), DateUtils.MM_dd_yyyy);
            OMPGdmStockBo.setReceiptDate(DateUtils.dateToString(needByDate, DateUtils.yyyy_MM_dd_HHmmss));

            // PR13
            // TODO - check if weekend and move to Monday
            Date adjustedNeedByDate = DateUtils.offsetDate(needByDate, Integer.parseInt(edmPurchaseRequisitionV1Entity.getLocalPrGRLeadTimeDays()));
            OMPGdmStockBo.setStartDate(DateUtils.dateToString(adjustedNeedByDate, DateUtils.yyyy_MM_dd_HHmmss));

            OMPGdmStockBo.setActive(IConstant.VALUE.YES);
            OMPGdmStockBo.setActiveOPRERP(IConstant.VALUE.YES);
            OMPGdmStockBo.setActiveSOPERP(IConstant.VALUE.NO);
            OMPGdmStockBo.setBatchId(IConstant.VALUE.BLANK);
            OMPGdmStockBo.setCertaintyID(IConstant.VALUE.BA);
            OMPGdmStockBo.setBlockedQuantity(ZERO_POINT_ZERO);
            OMPGdmStockBo.setQualityQuantity(ZERO_POINT_ZERO);
            OMPGdmStockBo.setRestrictedQuantity(ZERO_POINT_ZERO);
            OMPGdmStockBo.setReturnsQuantity(ZERO_POINT_ZERO);
            OMPGdmStockBo.setStockType(MOVEMENT);
            OMPGdmStockBo.setTransferQuantity(ZERO_POINT_ZERO);
            OMPGdmStockBo.setTransitDate(TRANSIT_DATE);
            OMPGdmStockBo.setUnrestrictedQuantity(ZERO_POINT_ZERO);

            ResultObject resultObject = new ResultObject();
            resultObject.setBaseBo(OMPGdmStockBo);
            LogUtil.getCoreLog().info("OMPGdmStockBo: " + ToStringBuilder.reflectionToString(OMPGdmStockBo));
            return resultObject;
        } catch (SkipRecordException e) {
            // we're skipping this record
            LogUtil.getCoreLog().info("skipping this record: " + e.getMessage());
            return null;
        }
    }

    /**
     * getProductId - implement PR1
     *
     * @param edmPurchaseRequisitionV1Entity main data region
     * @return String productId
     * @throws SkipRecordException skip the record if no/bad match
     */
    private String getProductId(EDMPurchaseRequisitionV1Entity edmPurchaseRequisitionV1Entity) throws SkipRecordException {
        String productId;
        // read in the matching materialGlobalV1 region data
        List<EDMMaterialGlobalV1Entity> edmMaterialGlobalV1EntityList = edmMaterialGlobalV1Dao.getEntitiesWithMaterialNumberAndSourceSystem(
                edmPurchaseRequisitionV1Entity.getMatlNum(), edmPurchaseRequisitionV1Entity.getSourceSystem());
        if (edmMaterialGlobalV1EntityList == null || edmMaterialGlobalV1EntityList.size() != 1) {
            // if we don't find 1 match skip this record
            throw new SkipRecordException("PR1");
        }

        EDMMaterialGlobalV1Entity edmMaterialGlobalV1Entity = edmMaterialGlobalV1EntityList.get(0);
        if (StringUtils.isBlank(edmMaterialGlobalV1Entity.getPrimaryPlanningCode())) {
            productId = edmMaterialGlobalV1Entity.getMaterialNumber();
        } else {
            productId = edmMaterialGlobalV1Entity.getPrimaryPlanningCode();
        }
        return productId;
    }

    /**
     * getLocationId - implement PR8
     *
     * @param edmPurchaseRequisitionV1Entity main data region
     * @return String locationId
     * @throws SkipRecordException skip the record if no/bad match
     */
    private String getLocationId(EDMPurchaseRequisitionV1Entity edmPurchaseRequisitionV1Entity) throws SkipRecordException {
        EDMPlantV1Entity entityWithLocalPlant = edmPlantV1Dao.getEntityWithLocalPlant(edmPurchaseRequisitionV1Entity.getPlntCd());
        if (entityWithLocalPlant != null && "X".equals(entityWithLocalPlant.getLocalPlanningRelevant())) {
            return edmPurchaseRequisitionV1Entity.getSourceSystem() + "_" + edmPurchaseRequisitionV1Entity.getPlntCd();
        } else {
            // skip the record if we don't find a good match in plan_v1
            throw new SkipRecordException("PR8");
        }
    }

    /**
     * getProcessId - implement PR9
     *
     * @param productId                      determined by PR1
     * @param locationId                     determined by PR1
     * @param edmPurchaseRequisitionV1Entity main data region
     * @return String processId
     */
    private String getProcessId(String productId, String locationId,
                                EDMPurchaseRequisitionV1Entity edmPurchaseRequisitionV1Entity) {
        String processId;
        if ("7".equals(edmPurchaseRequisitionV1Entity.getPrLineCatCd())) {
            processId = "TR/" + productId + "/" + locationId + "/" + edmPurchaseRequisitionV1Entity.getSourceSystem() +
                    "_" + edmPurchaseRequisitionV1Entity.getSuplPlntCd() + "/Default";
        } else {
            processId = "SU/" + productId + "/" + locationId + "/" + edmPurchaseRequisitionV1Entity.getLocalSupNum() +
                    "/Default";
        }
        return processId;
    }

    /**
     * getConsignment - implement PR18
     *
     * @param edmPurchaseRequisitionV1Entity main data region
     * @return String consignment
     */
    private String getConsignment(EDMPurchaseRequisitionV1Entity edmPurchaseRequisitionV1Entity) {
        String consignment;
        if ("K".equals(edmPurchaseRequisitionV1Entity.getPrLineCatCd()) ||
                "2".equals(edmPurchaseRequisitionV1Entity.getPrLineCatCd())) {
            consignment = IConstant.VALUE.YES;
        } else {
            consignment = IConstant.VALUE.NO;
        }
        return consignment;
    }

    /**
     * getProcessTypeId - implement PR16
     *
     * @param edmPurchaseRequisitionV1Entity main data region
     * @return String processTypeId
     */
    private String getProcessTypeId(EDMPurchaseRequisitionV1Entity edmPurchaseRequisitionV1Entity) {
        String processTypeId;
        if ("3".equals(edmPurchaseRequisitionV1Entity.getPrLineCatCd())) {
            processTypeId = "Subcontracting";
        } else if ("7".equals(edmPurchaseRequisitionV1Entity.getPrLineCatCd())) {
            processTypeId = "InternalTransport";
        } else if (StringUtils.isBlank(edmPurchaseRequisitionV1Entity.getLocalSupNum())) {
            processTypeId = "TODO"; // TODO Q to Nitin
        } else {
            List<PlanSplPlnLocEntity> entityListWithConditions = planSplPlnLocDao.getEntityListWithConditions(edmPurchaseRequisitionV1Entity.getSourceSystem(),
                    edmPurchaseRequisitionV1Entity.getLocalSupNum());
            if (entityListWithConditions != null && !entityListWithConditions.isEmpty()) {
                processTypeId = "VendorTransport";
            } else {
                processTypeId = "ExternalPurchase";
            }
        }
        return processTypeId;
    }

    /**
     * checkProductIdValid - implement PR10
     *
     * @param edmPurchaseRequisitionV1Entity main data region
     * @throws SkipRecordException skip the record if no/bad match
     */
    private void checkProductIdValid(EDMPurchaseRequisitionV1Entity edmPurchaseRequisitionV1Entity) throws SkipRecordException {
        PlanCnsMaterialPlanStatusEntity entityWithLocalMaterialNumber = planCnsMaterialPlanStatusDao.getEntityWithLocalMaterialNumberAndlLocalPlantAndSourceSystem(
                edmPurchaseRequisitionV1Entity.getMatlNum(),
                edmPurchaseRequisitionV1Entity.getPlntCd(),
                edmPurchaseRequisitionV1Entity.getSourceSystem()
        );
        if (entityWithLocalMaterialNumber == null || !"X".equals(entityWithLocalMaterialNumber.getNoPlanRelevant())) {
            // skip the record if we don't find a good match in cns_material_plan_status_v1
            throw new SkipRecordException("PR10");
        }
    }

    /**
     * checkPlanObjectFilterValid - implement PR6
     *
     * @param edmPurchaseRequisitionV1Entity main data region
     * @throws SkipRecordException skip the record if no/bad match
     */
    private void checkPlanObjectFilterValid(EDMPurchaseRequisitionV1Entity edmPurchaseRequisitionV1Entity) throws SkipRecordException {
        PlanCnsPlanObjectFilterEntity planCnsPlanObjectFilterDaoEntity = planCnsPlanObjectFilterDao.getEntity(
                "purchase_requisition", edmPurchaseRequisitionV1Entity.getSourceSystem(),
                "plntCd", edmPurchaseRequisitionV1Entity.getPlntCd(),
                "prTypeCd", edmPurchaseRequisitionV1Entity.getPrTypeCd());
        if (planCnsPlanObjectFilterDaoEntity == null ||
                !"I".equals(planCnsPlanObjectFilterDaoEntity.getInclusion_Exclusion())) {
            throw new SkipRecordException("PR6");
        }
    }
}
