package com.jnj.pangea.pangea.cns_material_plan_status.service;

import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.*;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.entity.edm.EDMMaterialPlantV1Entity;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.entity.ems.EMSFZEnterprisePlants;
import com.jnj.pangea.common.entity.plan.CnsMaterialInclEntity;
import com.jnj.pangea.common.entity.plan.CnsPlanParameterEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.pangea.cns_material_plan_status.bo.PangeaCnsMaterialPlanStatusBo;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class PangeaCnsMaterialPlanStatusServiceImpl implements ICommonService {

    private static PangeaCnsMaterialPlanStatusServiceImpl instance;

    public static PangeaCnsMaterialPlanStatusServiceImpl getInstance() {
        if (instance == null) {
            instance = new PangeaCnsMaterialPlanStatusServiceImpl();
        }
        return instance;
    }

    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    private PlanCnsPlanParameterDaoImpl planParameterDao = PlanCnsPlanParameterDaoImpl.getInstance();
    private EDMCountryV1DaoImpl countryV1Dao = EDMCountryV1DaoImpl.getInstance();
    private ProjectOneT001KDaoImpl t001KDao = ProjectOneT001KDaoImpl.getInstance();
    private ProjectOneT001DaoImpl t001Dao = ProjectOneT001DaoImpl.getInstance();
    private EDMMaterialGlobalDaoImpl edmMaterialGlobalDao = EDMMaterialGlobalDaoImpl.getInstance();
    private EDMMaterialGlobalDaoImpl materialGlobalDao = EDMMaterialGlobalDaoImpl.getInstance();
    private PlanCnsMaterialInclDaoImpl materialInclDao = PlanCnsMaterialInclDaoImpl.getInstance();

    @Override
    public ResultObject buildView(String key, Object o, Object o2) {

        ResultObject resultObject = new ResultObject();

        EDMMaterialPlantV1Entity materialPlantV1Entity = (EDMMaterialPlantV1Entity) o;

        PangeaCnsMaterialPlanStatusBo materialPlanStatusBo = new PangeaCnsMaterialPlanStatusBo();

        // T1
        materialPlanStatusBo.setSourceSystem(getFieldWithT1());

        //T2
        String localMaterialNumber = materialPlantV1Entity.getLocalMaterialNumber();
        //F1
        EDMMaterialGlobalV1Entity materialGlobalV1Entity = checkWithF1();
        if (null != materialGlobalV1Entity){
            //F2
            if (null != checkWithF2AndF3(materialPlantV1Entity,materialGlobalV1Entity,IConstant.VALUE.DP_RELEVANT)){
                materialPlanStatusBo.setLocalMaterialNumber(materialPlantV1Entity.getLocalMaterialNumber());
                materialPlanStatusBo.setDpRelevant(IConstant.VALUE.X);
                materialPlanStatusBo.setSpRelevant("");
            }else if (null != checkWithF2AndF3(materialPlantV1Entity,materialGlobalV1Entity,IConstant.VALUE.SP_RELEVANT)){
                materialPlanStatusBo.setLocalMaterialNumber(materialPlantV1Entity.getLocalMaterialNumber());
                materialPlanStatusBo.setDpRelevant("");
                materialPlanStatusBo.setSpRelevant(IConstant.VALUE.X);
            }
        }

        materialPlanStatusBo.setLocalPlant(materialPlantV1Entity.getLocalPlant());
        materialPlanStatusBo.setMaterialNumber(materialPlantV1Entity.getMaterialNumber());

        //T3 T4
        String localParentCode = "";
        EDMMaterialGlobalV1Entity entityWithLocalMaterialNumber = edmMaterialGlobalDao.getEntityWithLocalMaterialNumber(localMaterialNumber);
        if (null != entityWithLocalMaterialNumber) {
            localParentCode = entityWithLocalMaterialNumber.getLocalDpParentCode();
            materialPlanStatusBo.setLocalParentCode(localParentCode);
            materialPlanStatusBo.setPpc(entityWithLocalMaterialNumber.getPrimaryPlanningCode());
        }

        //T7
        getFieldWithT7(materialPlanStatusBo);

        //T5
        if (!"".equals(localParentCode)){
            materialPlanStatusBo.setParentActive(IConstant.VALUE.X);
        }

        //T6
        materialPlanStatusBo.setNoPlanRelevant(getFieldWithT6(localMaterialNumber));

        resultObject.setBaseBo(materialPlanStatusBo);
        return resultObject;
    }

    private String getFieldWithT1() {
        EDMSourceSystemV1Entity sourceSystemEntity = sourceSystemV1Dao.getEntityWithLocalSourceSystem(IConstant.VALUE.PROJECT_ONE);
        if (null != sourceSystemEntity) {
            return sourceSystemEntity.getSourceSystem();
        }
        return "";
    }

    private String getFieldWithT3(String localMaterialNumber) {
        EDMMaterialGlobalV1Entity entityWithLocalMaterialNumber = edmMaterialGlobalDao.getEntityWithLocalMaterialNumber(localMaterialNumber);
        if (null != entityWithLocalMaterialNumber) {
            return entityWithLocalMaterialNumber.getLocalDpParentCode();
        }
        return "";
    }

    private String getFieldWithT4(String localMaterialNumber) {
        EDMMaterialGlobalV1Entity materialGlobalV1Entity = materialGlobalDao.getEntityWithLocalMaterialNumber(localMaterialNumber);
        if (null != materialGlobalV1Entity){
            return materialGlobalV1Entity.getPrimaryPlanningCode();
        }
        return "";
    }

    private String getFieldWithT6(String localMaterialNumber) {
        CnsMaterialInclEntity materialInclEntity = materialInclDao.getCnsMaterialInclEntityWithLocalMaterialNumberAndPlanningType();
        if (null != materialInclEntity){
            return IConstant.VALUE.X;
        }
        return "";
    }


    private void getFieldWithT7(PangeaCnsMaterialPlanStatusBo materialPlanStatusBo) {
        if (null != materialPlanStatusBo) {
            if (IConstant.VALUE.X.equals(materialPlanStatusBo.getDpRelevant())) {
                materialPlanStatusBo.setActive(IConstant.VALUE.X);
            } else if (IConstant.VALUE.X.equals(materialPlanStatusBo.getNoPlanRelevant())) {
                materialPlanStatusBo.setActive(IConstant.VALUE.X);
            } else if (IConstant.VALUE.X.equals(materialPlanStatusBo.getSpRelevant())) {
                materialPlanStatusBo.setActive(IConstant.VALUE.X);
            } else {
                materialPlanStatusBo.setActive("");
            }
        } else {
            materialPlanStatusBo.setActive("");
        }
    }

    private EDMMaterialGlobalV1Entity checkWithF1(){
        List<CnsPlanParameterEntity> planParameterEntities = new ArrayList<CnsPlanParameterEntity>();
        List<CnsPlanParameterEntity> t1DpList = planParameterDao.getEntitiesWithConditions(IConstant.VALUE.CONS_LATAM,
                IConstant.VALUE.CNS_MATERIAL_PLAN_STATUS, IConstant.VALUE.DP_RELEVANT, IConstant.VALUE.MATERIAL_TYPE);
        planParameterEntities.addAll(t1DpList);

        List<CnsPlanParameterEntity> t1SpList = planParameterDao.getEntitiesWithConditions(IConstant.VALUE.CONS_LATAM,
                IConstant.VALUE.CNS_MATERIAL_PLAN_STATUS, IConstant.VALUE.SP_RELEVANT, IConstant.VALUE.MATERIAL_TYPE);
        planParameterEntities.addAll(t1SpList);

        for (CnsPlanParameterEntity planParameterEntity:planParameterEntities) {
            EDMMaterialGlobalV1Entity materialGlobalV1Entity = materialGlobalDao.getEntityWithLocalMaterialType(planParameterEntity.getParameterValue());
            if (null != materialGlobalV1Entity){
                return materialGlobalV1Entity;
            }
        }
        return null;
    }

    private EDMMaterialPlantV1Entity checkWithF2AndF3(EDMMaterialPlantV1Entity materialPlantV1Entity,EDMMaterialGlobalV1Entity materialGlobalV1Entity, String attribute) {
        if (null == materialPlantV1Entity) {
            return null;
        }
        if (null == materialGlobalV1Entity) {
            return null;
        }
        List<CnsPlanParameterEntity> cpp = planParameterDao.getEntitiesWithConditions(IConstant.VALUE.CONS_LATAM, IConstant.VALUE.CNS_MATERIAL_PLAN_STATUS, attribute, IConstant.VALUE.PLANT);
        if (null == cpp || cpp.size() == 0) {
            return null;
        }

        for (CnsPlanParameterEntity cnsPlanParameterEntity : cpp) {
            if (materialPlantV1Entity.getLocalDeletionFlagPlant().equals(cnsPlanParameterEntity.getParameterValue())) {
                break;
            } else {
                return null;
            }
        }
        List<CnsPlanParameterEntity> cpp2 = planParameterDao.getEntitiesWithConditions(IConstant.VALUE.CONS_LATAM, IConstant.VALUE.CNS_MATERIAL_PLAN_STATUS, attribute, IConstant.VALUE.PLANT, IConstant.VALUE.I);
        if (null == cpp2 || cpp2.size() == 0) {
            return null;
        }

        for (CnsPlanParameterEntity cnsPlanParameterEntity : cpp2) {
            if (materialPlantV1Entity.getLocalMrpType().equals(cnsPlanParameterEntity.getParameterValue())) {
                return materialPlantV1Entity;
            }
        }

        return null;
    }

    private FailData checkT1(EMSFZEnterprisePlants enterprisePlants, String sourceSystem) {
        FailData failData = null;
        if (StringUtils.isEmpty(sourceSystem) || IConstant.VALUE.EMS.equals(sourceSystem)) {
            failData = new FailData();
            failData.setErrorCode("T1");
            failData.setFunctionalArea("DP");
            failData.setInterfaceID("EDMPlant");
            failData.setSourceSystem("project_one");
            failData.setKey1(enterprisePlants.getzPlantSourceSystem());
            failData.setKey2(enterprisePlants.getzPlant());
            failData.setKey3("");
            failData.setKey4("");
            failData.setKey5("");
        }
        return failData;
    }

}
