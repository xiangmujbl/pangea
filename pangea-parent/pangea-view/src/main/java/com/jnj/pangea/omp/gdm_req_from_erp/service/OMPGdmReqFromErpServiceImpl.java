package com.jnj.pangea.omp.gdm_req_from_erp.service;

import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialGlobalV1DaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsMaterialPlanStatusDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanObjectFilterDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanUnitDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.entity.edm.EDMPurchaseRequisitionV1Entity;
import com.jnj.pangea.common.entity.plan.PlanCnsMaterialPlanStatusEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanObjectFilterEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanUnitEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_req_from_erp.bo.OMPGdmReqFromErpBo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class OMPGdmReqFromErpServiceImpl implements ICommonService {

    private static OMPGdmReqFromErpServiceImpl instance;

    public static OMPGdmReqFromErpServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmReqFromErpServiceImpl();
        }
        return instance;
    }

    private EDMMaterialGlobalV1DaoImpl materialGlobalV1Dao = EDMMaterialGlobalV1DaoImpl.getInstance();
    private PlanCnsMaterialPlanStatusDaoImpl planCnsMaterialPlanStatusDao = PlanCnsMaterialPlanStatusDaoImpl.getInstance();
    private PlanCnsPlanUnitDaoImpl planCnsPlanUnitDao = PlanCnsPlanUnitDaoImpl.getInstance();
    private PlanCnsPlanObjectFilterDaoImpl planCnsPlanObjectFilterDao = PlanCnsPlanObjectFilterDaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {
        ResultObject resultObject = new ResultObject();

        EDMPurchaseRequisitionV1Entity edmPurchaseRequisitionV1Entity = (EDMPurchaseRequisitionV1Entity) o;

        OMPGdmReqFromErpBo gdmReqFromErpBo = new OMPGdmReqFromErpBo();

        gdmReqFromErpBo.setBLCKD(edmPurchaseRequisitionV1Entity.getBlokInd());
        gdmReqFromErpBo.setBLCKT(edmPurchaseRequisitionV1Entity.getLocalBlockingText());
        gdmReqFromErpBo.setDELNR(edmPurchaseRequisitionV1Entity.getPrNum());
        gdmReqFromErpBo.setDELPS(edmPurchaseRequisitionV1Entity.getPrLineNbr());
        gdmReqFromErpBo.setFLIEF(edmPurchaseRequisitionV1Entity.getLocalFixedVendor());
        gdmReqFromErpBo.setPLIFZ(edmPurchaseRequisitionV1Entity.getLocalPDT());
        gdmReqFromErpBo.setREQType(edmPurchaseRequisitionV1Entity.getPrTypeCd());
        gdmReqFromErpBo.setTotalQuantity(edmPurchaseRequisitionV1Entity.getPrLineQty());
        gdmReqFromErpBo.setVERID(edmPurchaseRequisitionV1Entity.getLocalProdVersion());
        gdmReqFromErpBo.setWRK02(edmPurchaseRequisitionV1Entity.getSuplPlntCd());

        //N1
        gdmReqFromErpBo.setREQFromERPId(edmPurchaseRequisitionV1Entity.getSourceSystem() + IConstant.VALUE.BACK_SLANT + edmPurchaseRequisitionV1Entity.getPrDocId());

        //N2
        try {
            String dateToFormat = edmPurchaseRequisitionV1Entity.getNeedByDt();
            SimpleDateFormat sdfFrom = new SimpleDateFormat(IConstant.VALUE.MMDYYYY);
            SimpleDateFormat sdfTo = new SimpleDateFormat(IConstant.VALUE.YYYYMMDDBS);
            Date dFrom = sdfFrom.parse(dateToFormat);

            String timeToMove = edmPurchaseRequisitionV1Entity.getLocalPrGRLeadTimeDays();
            String deliveryDate = sdfTo.format(dFrom);
            Date date = sdfTo.parse(deliveryDate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, Integer.parseInt(timeToMove));
            if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                cal.add(Calendar.DATE, 2);
            } else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                cal.add(Calendar.DATE, 1);
            }
            Date d2 = cal.getTime();
            deliveryDate = sdfTo.format(d2);
            gdmReqFromErpBo.setDeliveryDate(deliveryDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //N3
        gdmReqFromErpBo.setDELKZ(IConstant.VALUE.BA);

        //N4
        if (!edmPurchaseRequisitionV1Entity.getSourceSystem().isEmpty() && edmPurchaseRequisitionV1Entity.getSourceSystem() != null
                && !edmPurchaseRequisitionV1Entity.getPlntCd().isEmpty() && edmPurchaseRequisitionV1Entity.getPlntCd() != null){
            gdmReqFromErpBo.setLocationId(edmPurchaseRequisitionV1Entity.getSourceSystem() + IConstant.VALUE.UNDERLINE + edmPurchaseRequisitionV1Entity.getPlntCd());

            //N5
            if(IConstant.VALUE.CONS_LATAM.equalsIgnoreCase(edmPurchaseRequisitionV1Entity.getSourceSystem())) {
                gdmReqFromErpBo.setManualOffset("");
                gdmReqFromErpBo.setPRIO_URG("");
            }

            //N6
            //Step 1
            EDMMaterialGlobalV1Entity materialGlobalV1Entity = materialGlobalV1Dao.getEntityWithMaterialNumberAndSourceSystem(edmPurchaseRequisitionV1Entity.getMatlNum(),edmPurchaseRequisitionV1Entity.getSourceSystem());
            //Step 2
            if(materialGlobalV1Entity != null && !materialGlobalV1Entity.getLocalMaterialNumber().isEmpty()) {
                PlanCnsMaterialPlanStatusEntity planCnsMaterialPlanStatusEntity = planCnsMaterialPlanStatusDao.getEntityWithLocalMaterialNumberAndlLocalPlantAndSourceSystem(materialGlobalV1Entity.getLocalMaterialNumber(), edmPurchaseRequisitionV1Entity.getPlntCd(), edmPurchaseRequisitionV1Entity.getSourceSystem());
                if (planCnsMaterialPlanStatusEntity != null) {
                    if (planCnsMaterialPlanStatusEntity.getSpRelevant().equals(IConstant.VALUE.X)) {
                        //Step 3
                        if (materialGlobalV1Entity.getLocalMaterialNumber().equals(edmPurchaseRequisitionV1Entity.getMatlNum())) {
                            if (materialGlobalV1Entity.getPrimaryPlanningCode().isEmpty()) {
                                gdmReqFromErpBo.setProductId(materialGlobalV1Entity.getMaterialNumber());
                            } else {
                                gdmReqFromErpBo.setProductId(materialGlobalV1Entity.getPrimaryPlanningCode());
                            }
                        }
                    }
                    //N7
                    if (materialGlobalV1Entity.getLocalMaterialNumber().equals(edmPurchaseRequisitionV1Entity.getMatlNum()) && materialGlobalV1Entity.getSourceSystem().equals(edmPurchaseRequisitionV1Entity.getSourceSystem())) {
                        PlanCnsPlanUnitEntity cnsPlanUnitEntity = planCnsPlanUnitDao.getCnsPlanUnitEntityWithLocalUom(materialGlobalV1Entity.getLocalBaseUom());
                        if (cnsPlanUnitEntity != null && !cnsPlanUnitEntity.getUnit().isEmpty()) {
                            gdmReqFromErpBo.setUnitId(cnsPlanUnitEntity.getUnit());

                            //N8
                            gdmReqFromErpBo.setDELETED(IConstant.VALUE.FALSE);

                            //N9
                            if (edmPurchaseRequisitionV1Entity.getDelInd().isEmpty()) {
                                PlanCnsPlanObjectFilterEntity planObjectFilterEntity = planCnsPlanObjectFilterDao.getEntityWithSourceObjectAttribute1AndSourceObjectAttribute1ValueAndSourceSystem("plntCd", edmPurchaseRequisitionV1Entity.getPlntCd(), edmPurchaseRequisitionV1Entity.getSourceSystem());
                                if (planObjectFilterEntity.getSourceObjectAttribute1().equalsIgnoreCase("plntCd") &&
                                        planObjectFilterEntity.getSourceObjectAttribute1Value().equalsIgnoreCase(edmPurchaseRequisitionV1Entity.getPlntCd())) {
                                    if (planObjectFilterEntity.getSourceObjectAttribute2().equalsIgnoreCase("prTypeCd")
                                            && planObjectFilterEntity.getInclusion_Exclusion().equalsIgnoreCase("I") && !edmPurchaseRequisitionV1Entity.getPrTypeCd().isEmpty()) {
                                        if (edmPurchaseRequisitionV1Entity.getPrStsCd().equalsIgnoreCase("N")) {
                                            gdmReqFromErpBo.setERPId(edmPurchaseRequisitionV1Entity.getPrNum());
                                            resultObject.setBaseBo(gdmReqFromErpBo); //Skipped in doesn't get here
                                        }
                                    }
                                }
                            }
                        } else {
                            FailData failData = writeFailDataToRegion(edmPurchaseRequisitionV1Entity, "N7", "Critical error - Cns Plan Unit - unit not found");
                            resultObject.setFailData(failData);
                        }
                    }
                }
            } else {
                FailData failData = writeFailDataToRegion(edmPurchaseRequisitionV1Entity, "N6", "Critical error - material_global_v1 null or localMaterialNumber not found");
                resultObject.setFailData(failData);
            }
        } else {
            FailData failData = writeFailDataToRegion(edmPurchaseRequisitionV1Entity, "N4", "Critical error - Blank values of source system or plntCd in edmPurchaseRequisitionV1Entity");
            resultObject.setFailData(failData);
        }
        return resultObject;
    }

    private FailData writeFailDataToRegion(EDMPurchaseRequisitionV1Entity edmPurchaseRequisitionV1Entity, String ruleCode, String errorValue) {
        FailData failData = new FailData();
        failData.setFunctionalArea("SP");
        failData.setInterfaceID("OMPGdmReqFromErp");
        failData.setErrorCode(ruleCode);
        failData.setSourceSystem("CONS_LATAM");
        failData.setKey1(edmPurchaseRequisitionV1Entity.getSourceSystem());
        failData.setKey2(edmPurchaseRequisitionV1Entity.getMatlNum());
        failData.setKey3(edmPurchaseRequisitionV1Entity.getLocalProcuringPlant());
        failData.setKey4("");
        failData.setKey5("");
        failData.setErrorValue(errorValue);
        return failData;
    }
}
