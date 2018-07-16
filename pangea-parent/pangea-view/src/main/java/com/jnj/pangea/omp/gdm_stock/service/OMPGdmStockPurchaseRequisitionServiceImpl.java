package com.jnj.pangea.omp.gdm_stock.service;

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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OMPGdmStockPurchaseRequisitionServiceImpl implements ICommonService {

    private static final String ZERO_POINT_ZERO = "0.0";
    private static final String MOVEMENT = "movement";

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
            OMPGdmStockBo oMPGdmStockBo = new OMPGdmStockBo();

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

            oMPGdmStockBo.setStockId(stockId);
            oMPGdmStockBo.setConsignment(getConsignment(edmPurchaseRequisitionV1Entity));

            checkPlanObjectFilterValid(edmPurchaseRequisitionV1Entity);
            oMPGdmStockBo.setErpOrderId(edmPurchaseRequisitionV1Entity.getPrNum());

            if (StringUtils.isNotBlank(edmPurchaseRequisitionV1Entity.getSuplPlntCd())) {
                oMPGdmStockBo.setInventoryLinkGroupId(stockId);
            }
            oMPGdmStockBo.setVendorId(edmPurchaseRequisitionV1Entity.getLocalFixedVendor());
            oMPGdmStockBo.setProcessId(getProcessId(productId, locationId, edmPurchaseRequisitionV1Entity));
            oMPGdmStockBo.setProcessTypeId(getProcessTypeId(edmPurchaseRequisitionV1Entity));
            oMPGdmStockBo.setLocationId(getLocationId(edmPurchaseRequisitionV1Entity));

            checkProductIdValid(edmPurchaseRequisitionV1Entity);
            oMPGdmStockBo.setProductId(productId);

            try {
                oMPGdmStockBo.setQuantity(String.valueOf(Double.parseDouble(edmPurchaseRequisitionV1Entity.getPrLineQty()) -
                        Double.parseDouble(edmPurchaseRequisitionV1Entity.getLocalPOQuantity())));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

            // PR12 && PR13
            if (StringUtils.isNotBlank(edmPurchaseRequisitionV1Entity.getNeedByDt()) ||
                    (StringUtils.isNotBlank(edmPurchaseRequisitionV1Entity.getLocalPrGRLeadTimeDays()))) {
                try {
                    String validDateToFormat = edmPurchaseRequisitionV1Entity.getNeedByDt();
                    SimpleDateFormat sdfFrom = new SimpleDateFormat(IConstant.VALUE.YYYYMMDD);
                    SimpleDateFormat sdfTo = new SimpleDateFormat(IConstant.VALUE.YYYYMMDDBS);
                    Date dReceivedDate = sdfFrom.parse(validDateToFormat);
                    String receivedDateConverted = sdfTo.format(dReceivedDate) + IConstant.VALUE.HH_NN_SS_ZERO;
                    oMPGdmStockBo.setReceiptDate(receivedDateConverted);

                    Date adjustedNeedByDate = DateUtils.offsetDate(dReceivedDate, Integer.parseInt(edmPurchaseRequisitionV1Entity.getLocalPrGRLeadTimeDays()));
                    if (null != adjustedNeedByDate) {
                        adjustedNeedByDate = DateUtils.moveToNextWorkingDay(adjustedNeedByDate);
                        oMPGdmStockBo.setStartDate(DateUtils.dateToString(adjustedNeedByDate, DateUtils.yyyy_MM_dd_HHmmss_TRUE));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            oMPGdmStockBo.setActive(IConstant.VALUE.YES);
            oMPGdmStockBo.setActiveOPRERP(IConstant.VALUE.YES);
            oMPGdmStockBo.setActiveSOPERP(IConstant.VALUE.NO);
            oMPGdmStockBo.setBatchId(IConstant.VALUE.BLANK);
            oMPGdmStockBo.setCertaintyId(IConstant.VALUE.BA);
            oMPGdmStockBo.setBlockedQuantity(ZERO_POINT_ZERO);
            oMPGdmStockBo.setQualityQuantity(ZERO_POINT_ZERO);
            oMPGdmStockBo.setRestrictedQuantity(ZERO_POINT_ZERO);
            oMPGdmStockBo.setReturnsQuantity(ZERO_POINT_ZERO);
            oMPGdmStockBo.setStockType(MOVEMENT);
            oMPGdmStockBo.setTransferQuantity(ZERO_POINT_ZERO);
            oMPGdmStockBo.setTransitDate(IConstant.VALUE.START_EFF);
            oMPGdmStockBo.setUnrestrictedQuantity(ZERO_POINT_ZERO);

            ResultObject resultObject = new ResultObject();
            resultObject.setBaseBo(oMPGdmStockBo);
            return resultObject;
        } catch (SkipRecordException e) {
            // we're skipping this record
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
        EDMMaterialGlobalV1Entity edmMaterialGlobalV1Entity = edmMaterialGlobalV1Dao.getEntityWithLocalMaterialNumberAndSourceSystem(
                edmPurchaseRequisitionV1Entity.getMatlNum(), edmPurchaseRequisitionV1Entity.getSourceSystem());
        if (edmMaterialGlobalV1Entity == null) {
            // if we don't find 1 match skip this record
            throw new SkipRecordException("PR1");
        }

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
            processId = "TR/" + productId + "/" + locationId + "/";
            if (StringUtils.isNotBlank(edmPurchaseRequisitionV1Entity.getSuplPlntCd())) {
                // only include this section if suplPlntCd is set
                processId += edmPurchaseRequisitionV1Entity.getSourceSystem() +
                        "_" + edmPurchaseRequisitionV1Entity.getSuplPlntCd() + "/";
            }
            processId += IConstant.VALUE.DEFAULT;
        } else {
            processId = "SU/" + productId + "/" + locationId + "/" +
                    edmPurchaseRequisitionV1Entity.getLocalFixedVendor() + "/" + IConstant.VALUE.DEFAULT;
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
        } else {
            List<PlanSplPlnLocEntity> entityListWithConditions = planSplPlnLocDao.getEntityListWithConditions(edmPurchaseRequisitionV1Entity.getSourceSystem(),
                    edmPurchaseRequisitionV1Entity.getLocalFixedVendor());
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
        PlanCnsPlanObjectFilterEntity planObjectFilterEntity = planCnsPlanObjectFilterDao.getEntityWithSourceObjectTechNameAndSourceSystemPrTypeCdAndPlntCdAndInclusion(
                IConstant.PLAN_CNS_PLAN_OBJECT_FILTER.SOURCE_FILTER_SOURCE_OBJECT_TECHNAME_PURCHASE_REQUISITION,
                edmPurchaseRequisitionV1Entity.getSourceSystem(), edmPurchaseRequisitionV1Entity.getPrTypeCd(),
                "plntCd", edmPurchaseRequisitionV1Entity.getPlntCd(), "prTypeCd", IConstant.VALUE.INCLUSION);
        if (planObjectFilterEntity == null) {
            throw new SkipRecordException("PR6");
        }
    }
}
