package com.jnj.pangea.plan.cns_material_plan_status.service;

import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialGlobalDaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsMaterialInclDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.entity.edm.EDMMaterialPlantV1Entity;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.entity.plan.CnsMaterialInclEntity;
import com.jnj.pangea.plan.cns_material_plan_status.bo.PlanCnsMaterialPlanStatusBo;
import org.apache.commons.lang.StringUtils;

import java.util.Set;

public class PlanCnsMaterialPlanStatusServiceImpl1 {

    private static PlanCnsMaterialPlanStatusServiceImpl1 instance;

    public static PlanCnsMaterialPlanStatusServiceImpl1 getInstance() {
        if (instance == null) {
            instance = new PlanCnsMaterialPlanStatusServiceImpl1();
        }
        return instance;
    }

    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    private EDMMaterialGlobalDaoImpl edmMaterialGlobalDao = EDMMaterialGlobalDaoImpl.getInstance();
    private PlanCnsMaterialInclDaoImpl materialInclDao = PlanCnsMaterialInclDaoImpl.getInstance();

    public ResultObject buildView(String key, Object o, Set<String> f1LocalMaterialTypeSet, Set<String> f1LocalMaterialTypeNotSet, Set<String> f1DivisionSet, Set<String> f1DivisionNotSet,
                                  Set<String> f2LocalPlantSet, Set<String> f2LocalPlantNotSet, Set<String> f2LocalMRPTypeNotSet, Set<String> f2LocalPlantStatusSet, Set<String> f2LocalPlantStatusNotSet, Set<String> f2LocalMrpControllerSet, Set<String> f2LocalMrpControllerNotSet,
                                  Set<String> f3LocalPlantSet, Set<String> f3LocalPlantNotSet, Set<String> f3LocalMRPTypeSet, Set<String> f3LocalPlantStatusSet, Set<String> f3LocalPlantStatusNotSet, Set<String> f3LocalMrpControllerSet, Set<String> f3LocalMrpControllerNotSet) {
        ResultObject resultObject = new ResultObject();

        EDMMaterialPlantV1Entity materialPlantV1Entity = (EDMMaterialPlantV1Entity) o;

        PlanCnsMaterialPlanStatusBo materialPlanStatusBo = new PlanCnsMaterialPlanStatusBo();

        boolean checkF1 = checkF1(materialPlantV1Entity, f1LocalMaterialTypeSet, f1LocalMaterialTypeNotSet, f1DivisionSet, f1DivisionNotSet);
        boolean checkF2 = checkF2(materialPlantV1Entity,f2LocalPlantSet,f2LocalPlantNotSet,f2LocalMRPTypeNotSet,f2LocalPlantStatusSet,f2LocalPlantStatusNotSet,f2LocalMrpControllerSet,f2LocalMrpControllerNotSet);
        boolean checkF3 = checkF3(materialPlantV1Entity,f3LocalPlantSet,f3LocalPlantNotSet,f3LocalMRPTypeSet,f3LocalPlantStatusSet,f3LocalPlantStatusNotSet,f3LocalMrpControllerSet,f3LocalMrpControllerNotSet);

        if (checkF1 && (checkF2 || checkF3)) {

            materialPlanStatusBo.setSourceSystem(getFieldWithT1());

            materialPlanStatusBo.setLocalMaterialNumber(materialPlantV1Entity.getLocalMaterialNumber());
            materialPlanStatusBo.setLocalPlant(materialPlantV1Entity.getLocalPlant());
            materialPlanStatusBo.setMaterialNumber(materialPlantV1Entity.getMaterialNumber());

            EDMMaterialGlobalV1Entity materialGlobalV1Entity = edmMaterialGlobalDao.getEntityWithLocalMaterialNumber(materialPlantV1Entity.getLocalMaterialNumber());
            if (null != materialGlobalV1Entity) {
                materialPlanStatusBo.setLocalParentCode(materialGlobalV1Entity.getLocalDpParentCode());
                materialPlanStatusBo.setPpc(StringUtils.trim(materialGlobalV1Entity.getPrimaryPlanningCode()));
            }

            if (checkF2) {
                materialPlanStatusBo.setDpRelevant(IConstant.VALUE.X);
            }
            if (checkF3) {
                materialPlanStatusBo.setSpRelevant(IConstant.VALUE.X);
            }

            if (StringUtils.isNotEmpty(materialPlanStatusBo.getLocalParentCode())) {
                materialPlanStatusBo.setParentActive(IConstant.VALUE.X);
            }

            CnsMaterialInclEntity materialInclEntity = materialInclDao.getEntityWithLocalMaterialNumberAndPlanningType(materialPlantV1Entity.getLocalMaterialNumber(), IConstant.VALUE.NP);
            if(null != materialInclEntity){
                materialPlanStatusBo.setNoPlanRelevant(IConstant.VALUE.X);
            }
            getFieldWithT6(materialPlanStatusBo);

            resultObject.setBaseBo(materialPlanStatusBo);
            return resultObject;
        }

        return null;
    }

    private boolean checkF1(EDMMaterialPlantV1Entity materialPlantV1Entity, Set<String> f1LocalMaterialTypeSet, Set<String> f1LocalMaterialTypeNotSet, Set<String> f1DivisionSet, Set<String> f1DivisionNotSet) {
        EDMMaterialGlobalV1Entity materialGlobalV1Entity = edmMaterialGlobalDao.getEntityWithLocalMaterialNumber(materialPlantV1Entity.getLocalMaterialNumber());
        if (null != materialGlobalV1Entity) {
            String flagForDeletion = StringUtils.trim(materialGlobalV1Entity.getFlagForDeletion());


         //   boolean f1LocalMaterialType = (f1LocalMaterialTypeSet.contains(StringUtils.trim(materialGlobalV1Entity.getLocalMaterialType()));
         //   boolean f1LocalMaterialTypeNot = !f1LocalMaterialTypeNotSet.contains(StringUtils.trim(materialGlobalV1Entity.getLocalMaterialType()));

            boolean f1LocalMaterialType = (f1LocalMaterialTypeSet.isEmpty() || ( f1LocalMaterialTypeSet.contains(StringUtils.trim(materialGlobalV1Entity.getLocalMaterialType()))));
            boolean f1LocalMaterialTypeNot =(f1LocalMaterialTypeNotSet.isEmpty() ||!f1LocalMaterialTypeNotSet.contains(StringUtils.trim(materialGlobalV1Entity.getLocalMaterialType())));
            boolean f1Division =   (f1DivisionSet.isEmpty()    || f1DivisionSet.contains(StringUtils.trim(materialGlobalV1Entity.getDivision())));
            boolean f1DivisionNot = (f1DivisionNotSet.isEmpty()    || !f1DivisionNotSet.contains(StringUtils.trim(materialGlobalV1Entity.getDivision())));



            if (f1LocalMaterialType && f1LocalMaterialTypeNot && f1Division && f1DivisionNot && !IConstant.VALUE.X.equals(flagForDeletion)) {
                return true;
            }


        }
        return false;
    }

    private boolean checkF2(EDMMaterialPlantV1Entity materialPlantV1Entity, Set<String> f2LocalPlantSet, Set<String> f2LocalPlantNotSet, Set<String> f2LocalMRPTypeNotSet, Set<String> f2LocalPlantStatusSet, Set<String> f2LocalPlantStatusNotSet, Set<String> f2LocalMrpControllerSet, Set<String> f2LocalMrpControllerNotSet) {

        String localPlant = StringUtils.trim(materialPlantV1Entity.getLocalPlant());
        String localMrpType = StringUtils.trim(materialPlantV1Entity.getLocalMrpType());
        String localPlantStatus = StringUtils.trim(materialPlantV1Entity.getLocalPlantStatus());
        String localMrpController = StringUtils.trim(materialPlantV1Entity.getLocalMrpController());

        boolean f2LocalPlant = (f2LocalPlantSet.isEmpty()    || f2LocalPlantSet.contains(localPlant));
        boolean f2LocalPlantNot = (f2LocalPlantNotSet.isEmpty()    ||!f2LocalPlantNotSet.contains(localPlant));
        boolean f2LocalMRPTypeNot = (f2LocalMRPTypeNotSet.isEmpty()    ||!f2LocalMRPTypeNotSet.contains(localMrpType));
        boolean f2LocalPlantStatus = (f2LocalPlantStatusSet.isEmpty()    ||f2LocalPlantStatusSet.contains(localPlantStatus));
        boolean f2LocalPlantStatusNot =(f2LocalPlantStatusNotSet.isEmpty()    || !f2LocalPlantStatusNotSet.contains(localPlantStatus));
        boolean f2LocalMrpController = (f2LocalMrpControllerSet.isEmpty()    ||f2LocalMrpControllerSet.contains(localMrpController));
        boolean f2LocalMrpControllerNot = (f2LocalMrpControllerNotSet.isEmpty()    ||!f2LocalMrpControllerNotSet.contains(localMrpController));




        if (f2LocalPlant && f2LocalPlantNot && f2LocalMRPTypeNot && f2LocalPlantStatus && f2LocalPlantStatusNot && f2LocalMrpController && f2LocalMrpControllerNot) {
            return true;
        }
        return false;
    }

    private boolean checkF3(EDMMaterialPlantV1Entity materialPlantV1Entity, Set<String> f3LocalPlantSet, Set<String> f3LocalPlantNotSet, Set<String> f3LocalMRPTypeSet, Set<String> f3LocalPlantStatusSet, Set<String> f3LocalPlantStatusNotSet, Set<String> f3LocalMrpControllerSet, Set<String> f3LocalMrpControllerNotSet) {
        String localPlant = StringUtils.trim(materialPlantV1Entity.getLocalPlant());
        String localMrpType = StringUtils.trim(materialPlantV1Entity.getLocalMrpType());
        String localPlantStatus = StringUtils.trim(materialPlantV1Entity.getLocalPlantStatus());
        String localMrpController = StringUtils.trim(materialPlantV1Entity.getLocalMrpController());

        CnsMaterialInclEntity cnsMaterialInclEntity = materialInclDao.getEntityWithConditions(materialPlantV1Entity.getLocalMaterialNumber(), localPlant, IConstant.VALUE.CRITICAL_ROH, IConstant.VALUE.SP1);

        boolean f3LocalPlant = (f3LocalPlantSet.isEmpty()    ||f3LocalPlantSet.contains(localPlant));
        boolean f3LocalPlantNot = (f3LocalPlantNotSet.isEmpty()    ||!f3LocalPlantNotSet.contains(localPlant));
        boolean f3LocalMRPType = (f3LocalMRPTypeSet.isEmpty()    || f3LocalMRPTypeSet.contains(localMrpType));
        boolean f3LocalPlantStatus = (f3LocalPlantStatusSet.isEmpty()    ||f3LocalPlantStatusSet.contains(localPlantStatus));
        boolean f3LocalPlantStatusNot = (f3LocalPlantStatusNotSet.isEmpty()    ||!f3LocalPlantStatusNotSet.contains(localPlantStatus));
        boolean f3LocalMrpController =(f3LocalMrpControllerSet.isEmpty()    || f3LocalMrpControllerSet.contains(localMrpController));
        boolean f3LocalMrpControllerNot =(f3LocalMrpControllerNotSet.isEmpty()    || !f3LocalMrpControllerNotSet.contains(localMrpController));
        
        if (null != cnsMaterialInclEntity && f3LocalPlant && f3LocalPlantNot && f3LocalMRPType && f3LocalPlantStatus && f3LocalPlantStatusNot && f3LocalMrpController && f3LocalMrpControllerNot) {
            return true;
        }
        return false;
    }

    private String getFieldWithT1() {
        EDMSourceSystemV1Entity sourceSystemEntity = sourceSystemV1Dao.getEntityWithLocalSourceSystem(IConstant.VALUE.PROJECT_ONE);
        if (null != sourceSystemEntity) {
            return sourceSystemEntity.getSourceSystem();
        }
        return null;
    }

    private void getFieldWithT6(PlanCnsMaterialPlanStatusBo materialPlanStatusBo) {
        if (null != materialPlanStatusBo) {
            if (IConstant.VALUE.X.equals(materialPlanStatusBo.getDpRelevant())) {
                materialPlanStatusBo.setActive(IConstant.VALUE.X);
            } else if (IConstant.VALUE.X.equals(materialPlanStatusBo.getNoPlanRelevant())) {
                materialPlanStatusBo.setActive(IConstant.VALUE.X);
            } else if (IConstant.VALUE.X.equals(materialPlanStatusBo.getSpRelevant())) {
                materialPlanStatusBo.setActive(IConstant.VALUE.X);
            }
        }
    }

    private FailData checkFailData(EDMMaterialPlantV1Entity materialPlantV1Entity, String ErrorCode) {
        FailData failData = new FailData();
        failData.setErrorCode(ErrorCode);
        failData.setFunctionalArea(IConstant.FAILED.FUNCTIONAL_AREA.DP);
        failData.setInterfaceID(IConstant.FAILED.INTERFACE_ID.PLAN_CNS_MATERIAL_PLAN_STATUS);
        failData.setSourceSystem(IConstant.VALUE.CONS_LATAM);
        failData.setKey1(materialPlantV1Entity.getLocalPlant());
        failData.setKey2(materialPlantV1Entity.getLocalMaterialNumber());
        failData.setKey3("");
        failData.setKey4("");
        failData.setKey5("");
        return failData;
    }

}
