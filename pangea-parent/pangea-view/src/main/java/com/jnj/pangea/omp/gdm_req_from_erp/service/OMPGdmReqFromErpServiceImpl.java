package com.jnj.pangea.omp.gdm_req_from_erp.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialGlobalV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMPlantV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.dao.impl.plan.*;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.entity.edm.EDMPlantV1Entity;
import com.jnj.pangea.common.entity.edm.EDMPurchaseRequisitionV1Entity;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.entity.plan.*;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_req_from_erp.bo.OMPGdmReqFromErpBo;
import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    private PlanCnsTlaneControlDaoImpl tlaneControlDao = PlanCnsTlaneControlDaoImpl.getInstance();
    private PlanCnsTlaneControlTriangulationDaoImpl tlaneControlTriangulationDao = PlanCnsTlaneControlTriangulationDaoImpl.getInstance();
    private EDMPlantV1DaoImpl plantV1Dao = EDMPlantV1DaoImpl.getInstance();

    ThreadLocal<String> localPlant = new ThreadLocal<>();

    private static final String BACK_SLASH = "/";
    private static final String BA = "BA";
    private static final String NO = "NO";
    private static final String YES = "YES";
    private static final String UNDERLINE = "_";
    private static final String EMPTY = "";
    private static final String X = "X";
    private static final String ZERO = "0";
    private static final String INCLUSION = "I";
    private static final String YYYYMMDD = "yyyyMMdd";
    private static final String YYYYMMDDBS = "yyyy/MM/dd";
    private static final String HH_NN_SS_ZERO = " 00:00:00";
    private static final String PROJECT_ONE_DEV = "Project_One";
    private static final String CONS_LATAM = "CONS_LATAM";
    private static final String SOURCE_FILTER_SOURCE_OBJECT_TECHNAME_PURCHASE_REQUISITION = "purchase_requisition";

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {
        ResultObject resultObject = new ResultObject();
        ResultObject resultObjectSkip = new ResultObject();
        localPlant.set("");
        OMPGdmReqFromErpBo gdmReqFromErpBo = new OMPGdmReqFromErpBo();

        EDMPurchaseRequisitionV1Entity edmPurchaseRequisitionV1Entity = (EDMPurchaseRequisitionV1Entity) o;

        gdmReqFromErpBo.setBLCKD(edmPurchaseRequisitionV1Entity.getBlokInd());
        gdmReqFromErpBo.setBLCKT(edmPurchaseRequisitionV1Entity.getLocalBlockingText());
        gdmReqFromErpBo.setDELPS(edmPurchaseRequisitionV1Entity.getPrLineNbr());
        gdmReqFromErpBo.setPLIFZ(edmPurchaseRequisitionV1Entity.getLocalPDT());
        gdmReqFromErpBo.setREQType(edmPurchaseRequisitionV1Entity.getPrTypeCd());
        gdmReqFromErpBo.setTotalQuantity(edmPurchaseRequisitionV1Entity.getPrLineQty());
        gdmReqFromErpBo.setVERID(edmPurchaseRequisitionV1Entity.getLocalProdVersion());

        //N1
        gdmReqFromErpBo.setREQFromERPId(edmPurchaseRequisitionV1Entity.getSourceSystem() + BACK_SLASH + edmPurchaseRequisitionV1Entity.getPrNum() + BACK_SLASH + edmPurchaseRequisitionV1Entity.getPrLineNbr());

        //N2
        calcDateN2(edmPurchaseRequisitionV1Entity, gdmReqFromErpBo);

        //N3
        gdmReqFromErpBo.setDELKZ(BA);

        //N4
        if(!plantN4(edmPurchaseRequisitionV1Entity, gdmReqFromErpBo)){
            return resultObjectSkip;
        }

        //N5
        ruleN5(edmPurchaseRequisitionV1Entity, gdmReqFromErpBo);

        //N6
        //Step 1
        EDMMaterialGlobalV1Entity materialGlobalV1Entity = materialGlobalV1Dao.getEntityWithLocalMaterialNumberAndSourceSystem(edmPurchaseRequisitionV1Entity.getMatlNum(),edmPurchaseRequisitionV1Entity.getSourceSystem());
        if(materialGlobalV1Entity != null && !materialGlobalV1Entity.getLocalMaterialNumber().isEmpty()) {
            //Step 2
            PlanCnsMaterialPlanStatusEntity planCnsMaterialPlanStatusEntity = planCnsMaterialPlanStatusDao.getEntityWithLocalMaterialNumberAndlLocalPlantAndSourceSystem(materialGlobalV1Entity.getLocalMaterialNumber(), localPlant.get(), edmPurchaseRequisitionV1Entity.getSourceSystem());
            if(planCnsMaterialPlanStatusEntity != null) {
                if (!ruleN6(edmPurchaseRequisitionV1Entity, materialGlobalV1Entity, planCnsMaterialPlanStatusEntity, gdmReqFromErpBo)) {
                    return resultObjectSkip;
                }
            } else {
                return resultObjectSkip;
            }
        } else{
            resultObject.setFailData(writeFailDataToRegion(edmPurchaseRequisitionV1Entity, "N6", "Critical error - material_global_v1 null or localMaterialNumber not found"));
            return resultObject;
        }

        //N7
        if(!ruleN7(edmPurchaseRequisitionV1Entity, materialGlobalV1Entity, gdmReqFromErpBo)){
            resultObject.setFailData(writeFailDataToRegion(edmPurchaseRequisitionV1Entity, "N7", "Critical error - Cns Plan Unit - unit not found"));
            return resultObject;
        }

        //N8
        //version 2: set default as 'NO'
        gdmReqFromErpBo.setDELETED(NO);

        //N9
        if(!ruleN9(edmPurchaseRequisitionV1Entity, gdmReqFromErpBo)){
            return resultObjectSkip;
        }

        //N10
        gdmReqFromErpBo.setDELNR(edmPurchaseRequisitionV1Entity.getSourceSystem()
                + BACK_SLASH + edmPurchaseRequisitionV1Entity.getPrNum());

        //N11
        if(!edmPurchaseRequisitionV1Entity.getSuplPlntCd().isEmpty()) {
            gdmReqFromErpBo.setWRK02(edmPurchaseRequisitionV1Entity.getSourceSystem()
                    + UNDERLINE + edmPurchaseRequisitionV1Entity.getSuplPlntCd());
        } else{
            gdmReqFromErpBo.setWRK02("");
        }

        //N12
        gdmReqFromErpBo.setFLIEF(StringUtils.stripStart(edmPurchaseRequisitionV1Entity.getLocalFixedVendor(),"0"));

        resultObject.setBaseBo(gdmReqFromErpBo);
        return resultObject;
    }

    /**
     * set deliveryDate - implement N2 - Adding a number of days to the date
     *
     * @param edmPurchaseRequisitionV1Entity main data region
     */
    private void calcDateN2(EDMPurchaseRequisitionV1Entity edmPurchaseRequisitionV1Entity, OMPGdmReqFromErpBo gdmReqFromErpBo){
        try {
            String dateToFormat = edmPurchaseRequisitionV1Entity.getNeedByDt();
            SimpleDateFormat sdfFrom = new SimpleDateFormat(YYYYMMDD);
            SimpleDateFormat sdfTo = new SimpleDateFormat(YYYYMMDDBS);
            Date dFrom = sdfFrom.parse(dateToFormat);

            String timeToMove = edmPurchaseRequisitionV1Entity.getLocalPrGRLeadTimeDays();
            Calendar cal = Calendar.getInstance();
            cal.setTime(dFrom);
            cal.add(Calendar.DATE, Integer.parseInt(timeToMove));
            Date d2 = cal.getTime();
            String deliveryDate = sdfTo.format(d2) + HH_NN_SS_ZERO;
            gdmReqFromErpBo.setDeliveryDate(deliveryDate);
        } catch (ParseException e) {
            LogUtil.getCoreLog().error(e.getMessage());
        }
    }

    /**
     * set locationId - implement N4 - Triangulation logic, plant replacement
     *
     * @param edmPurchaseRequisitionV1Entity main data region
     * @return boolean for skip validation
     */
    private boolean plantN4(EDMPurchaseRequisitionV1Entity edmPurchaseRequisitionV1Entity, OMPGdmReqFromErpBo gdmReqFromErpBo){
        EDMSourceSystemV1Entity sourceSystemEntity = sourceSystemV1Dao.getEntityWithLocalSourceSystemAndSourceSystem(PROJECT_ONE_DEV, edmPurchaseRequisitionV1Entity.getSourceSystem());
        if(sourceSystemEntity == null){
            return false;
        }
        localPlant.set(edmPurchaseRequisitionV1Entity.getPlntCd());

        List<PlanCnsTlaneControlEntity> tlaneControlEntityList = tlaneControlDao.getEntityWithSourceSystemCriticalParameters(edmPurchaseRequisitionV1Entity.getSourceSystem());

        for(PlanCnsTlaneControlEntity tlaneControl : tlaneControlEntityList) {
            if(tlaneControl.getTrigSysPlant().equals(localPlant.get()) && tlaneControl.getTriangulationDetail().equalsIgnoreCase(YES) && tlaneControl.getTrigSysTransaction().equalsIgnoreCase("purchase_order")) {
                List<PlanCnsTlaneControlTriangulationEntity> triangulationEntities = tlaneControlTriangulationDao.getEntityWithSourceSystemCriticalParameters(tlaneControl.getSequenceNumber(), tlaneControl.getTlaneName());
                if(triangulationEntities != null) {
                    PlanCnsTlaneControlTriangulationEntity stepNumberEntity = findHighestStepNumber(triangulationEntities);
                    localPlant.set(stepNumberEntity.getDestinatonLocation().replace(tlaneControl.getSourceSystemCriticalParameters()+UNDERLINE,EMPTY));
                    break;
                }
            }
        }
        //Planning relevancy check
        EDMPlantV1Entity plantV1Entity = plantV1Dao.getPlantWithSourceSystemAndLocalPlant(edmPurchaseRequisitionV1Entity.getSourceSystem(), localPlant.get());
        if(plantV1Entity != null && plantV1Entity.getLocalPlanningRelevant().equalsIgnoreCase(X)){
            gdmReqFromErpBo.setLocationId(edmPurchaseRequisitionV1Entity.getSourceSystem()+UNDERLINE+localPlant.get());
            return true;
        }
        return false;
    }

    /**
     *  Finding the highest step number for triangulation logic
     *
     * @param triangulationEntities List of TlaneControlTriangulation entities
     * @return PlanCnsTlaneControlTriangulationEntity Returning the TlaneControlTriangulation entity with the highest step number
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
     * set manualOffset - set PRIO_URG - N5 implementation
     *
     * @param edmPurchaseRequisitionV1Entity main data region
     */
    private void ruleN5(EDMPurchaseRequisitionV1Entity edmPurchaseRequisitionV1Entity, OMPGdmReqFromErpBo gdmReqFromErpBo){
        if(CONS_LATAM.equalsIgnoreCase(edmPurchaseRequisitionV1Entity.getSourceSystem())) {
            gdmReqFromErpBo.setManualOffset(ZERO);
            gdmReqFromErpBo.setPRIO_URG(ZERO);
        }
    }

    /**
     * set productId - N6 implementation
     *
     * @param edmPurchaseRequisitionV1Entity main data region
     * @param materialGlobalV1Entity region lookup material global v1 entity
     * @param planCnsMaterialPlanStatusEntity region lookup material plan status entity
     * @return boolean for skip and or reject validation
     */
    private boolean ruleN6(EDMPurchaseRequisitionV1Entity edmPurchaseRequisitionV1Entity, EDMMaterialGlobalV1Entity materialGlobalV1Entity, PlanCnsMaterialPlanStatusEntity planCnsMaterialPlanStatusEntity, OMPGdmReqFromErpBo gdmReqFromErpBo){
        if ((planCnsMaterialPlanStatusEntity.getSpRelevant() != null && planCnsMaterialPlanStatusEntity.getSpRelevant().equals(X)) && (materialGlobalV1Entity.getLocalMaterialNumber() != null && materialGlobalV1Entity.getLocalMaterialNumber().equals(edmPurchaseRequisitionV1Entity.getMatlNum()))) {
            //Step 3
            if (materialGlobalV1Entity.getPrimaryPlanningCode() != null && materialGlobalV1Entity.getPrimaryPlanningCode().isEmpty()) {
                gdmReqFromErpBo.setProductId(materialGlobalV1Entity.getMaterialNumber());
            } else {
                gdmReqFromErpBo.setProductId(materialGlobalV1Entity.getPrimaryPlanningCode());
            }
            return true;
        }
        return false;
    }

    /**
     * set unitId - N7 implementation
     *
     * @param edmPurchaseRequisitionV1Entity main data region
     * @param materialGlobalV1Entity region lookup material global v1 entity
     * @return boolean for skip and or reject validation
     */
    private boolean ruleN7(EDMPurchaseRequisitionV1Entity edmPurchaseRequisitionV1Entity, EDMMaterialGlobalV1Entity materialGlobalV1Entity, OMPGdmReqFromErpBo gdmReqFromErpBo){
        if ((materialGlobalV1Entity.getLocalMaterialNumber() != null && materialGlobalV1Entity.getLocalMaterialNumber().equals(edmPurchaseRequisitionV1Entity.getMatlNum())) && (materialGlobalV1Entity.getSourceSystem() != null && materialGlobalV1Entity.getSourceSystem().equals(edmPurchaseRequisitionV1Entity.getSourceSystem()))) {
            PlanCnsPlanUnitEntity cnsPlanUnitEntity = planCnsPlanUnitDao.getCnsPlanUnitEntityWithLocalUom(materialGlobalV1Entity.getLocalBaseUom());
            if (cnsPlanUnitEntity != null && !cnsPlanUnitEntity.getUnit().isEmpty()) {
                gdmReqFromErpBo.setUnitId(cnsPlanUnitEntity.getUnit());
                return true;
            }
        }
        return false;
    }

    /**
     * set erpId - N9 implementation
     *
     * @param edmPurchaseRequisitionV1Entity main data region
     * @return boolean for skip and validation
     */
    private boolean ruleN9(EDMPurchaseRequisitionV1Entity edmPurchaseRequisitionV1Entity, OMPGdmReqFromErpBo gdmReqFromErpBo){
        if (edmPurchaseRequisitionV1Entity.getDelInd().isEmpty() && edmPurchaseRequisitionV1Entity.getPrClseInd().isEmpty()) {
            PlanCnsPlanObjectFilterEntity planObjectFilterEntity = planCnsPlanObjectFilterDao.getEntityWithSourceObjectTechNameAndSourceSystemPrTypeCdAndPlntCdAndInclusion(
                    SOURCE_FILTER_SOURCE_OBJECT_TECHNAME_PURCHASE_REQUISITION,edmPurchaseRequisitionV1Entity.getSourceSystem(),
                    edmPurchaseRequisitionV1Entity.getPrTypeCd(),"plntCd", localPlant.get(), "prTypeCd", INCLUSION);
            if (planObjectFilterEntity != null && edmPurchaseRequisitionV1Entity.getPrStsCd().equalsIgnoreCase("N")) {
                gdmReqFromErpBo.setERPId(edmPurchaseRequisitionV1Entity.getSourceSystem() + BACK_SLASH + edmPurchaseRequisitionV1Entity.getPrNum() + BACK_SLASH + edmPurchaseRequisitionV1Entity.getPrLineNbr());
                return true;
            }
        }
        return false;
    }

    /**
     * set failedData object for rejection
     *
     * @param edmPurchaseRequisitionV1Entity main data region
     * @param ruleCode rule number
     * @param errorValue error to write out
     * @return FailData object to write to the failed data region
     */
    private FailData writeFailDataToRegion(EDMPurchaseRequisitionV1Entity edmPurchaseRequisitionV1Entity, String ruleCode, String errorValue) {
        FailData failData = new FailData();
        failData.setFunctionalArea("SP");
        failData.setInterfaceID("OMPGdmReqFromErp");
        failData.setErrorCode(ruleCode);
        failData.setSourceSystem(CONS_LATAM);
        failData.setKey1(edmPurchaseRequisitionV1Entity.getSourceSystem());
        failData.setKey2(edmPurchaseRequisitionV1Entity.getMatlNum());
        failData.setKey3(edmPurchaseRequisitionV1Entity.getLocalProcuringPlant());
        failData.setKey4("");
        failData.setKey5("");
        failData.setErrorValue(errorValue);
        return failData;
    }
}