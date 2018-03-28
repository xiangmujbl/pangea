package com.jnj.pangea.plan.cns_material_plan_status.service;

import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMCountryV1DaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialGlobalDaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsMaterialInclDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanParameterDaoImpl;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOneT001DaoImpl;
import com.jnj.pangea.common.dao.impl.project_one.ProjectOneT001KDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.entity.edm.EDMMaterialPlantV1Entity;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.entity.plan.CnsMaterialInclEntity;
import com.jnj.pangea.common.entity.plan.CnsPlanParameterEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.plan.cns_material_plan_status.bo.PlanCnsMaterialPlanStatusBo;

import java.util.ArrayList;
import java.util.List;

public class PlanCnsMaterialPlanStatusServiceImpl implements ICommonService {

    private static PlanCnsMaterialPlanStatusServiceImpl instance;

    public static PlanCnsMaterialPlanStatusServiceImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsMaterialPlanStatusServiceImpl();
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

        PlanCnsMaterialPlanStatusBo materialPlanStatusBo = new PlanCnsMaterialPlanStatusBo();

        // T1
        materialPlanStatusBo.setSourceSystem(getFieldWithT1());

        //T2
        String localMaterialNumber = materialPlantV1Entity.getLocalMaterialNumber();
        //F1
        EDMMaterialGlobalV1Entity materialGlobalV1Entity = checkWithF1();
        if (null != materialGlobalV1Entity){
            //F2 F3
            if (checkWithF2AndF3(materialPlantV1Entity,materialGlobalV1Entity,IConstant.VALUE.DP_RELEVANT)){
                materialPlanStatusBo.setLocalMaterialNumber(materialPlantV1Entity.getLocalMaterialNumber());
                materialPlanStatusBo.setLocalPlant(materialPlantV1Entity.getLocalPlant());
                materialPlanStatusBo.setDpRelevant(IConstant.VALUE.X);
                materialPlanStatusBo.setSpRelevant("");
            }else if (checkWithF2AndF3(materialPlantV1Entity,materialGlobalV1Entity,IConstant.VALUE.SP_RELEVANT)){
                materialPlanStatusBo.setLocalMaterialNumber(materialPlantV1Entity.getLocalMaterialNumber());
                materialPlanStatusBo.setLocalPlant(materialPlantV1Entity.getLocalPlant());
                materialPlanStatusBo.setDpRelevant("");
                materialPlanStatusBo.setSpRelevant(IConstant.VALUE.X);
            }else {
                FailData failData = checkF2AndF3(materialPlantV1Entity);
                if (null != failData) {
                    resultObject.setFailData(failData);
                    return resultObject;
                }
            }
        }else{
            FailData failData = checkF1(materialPlantV1Entity);
            if (null != failData) {
                resultObject.setFailData(failData);
                return resultObject;
            }
        }
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

    private String getFieldWithT6(String localMaterialNumber) {
        CnsMaterialInclEntity materialInclEntity = materialInclDao.getCnsMaterialInclEntityWithLocalMaterialNumberAndPlanningType();
        if (null != materialInclEntity){
            return IConstant.VALUE.X;
        }
        return "";
    }

    private void getFieldWithT7(PlanCnsMaterialPlanStatusBo materialPlanStatusBo) {
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
        List<CnsPlanParameterEntity> planParameterEntities = new ArrayList<>();
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

    private Boolean checkWithF2AndF3(EDMMaterialPlantV1Entity materialPlantV1Entity,EDMMaterialGlobalV1Entity materialGlobalV1Entity,String attribute){

        if (null == materialPlantV1Entity) {
            return null;
        }
        if (null == materialGlobalV1Entity) {
            return null;
        }

        Boolean lpFlag = materialPlantV1Entity.getLocalPlant().equals(materialGlobalV1Entity.getLocalMaterialNumber());

        List<CnsPlanParameterEntity> ldList = planParameterDao.getEntitiesWithConditions(IConstant.VALUE.CONS_LATAM,
                IConstant.VALUE.CNS_MATERIAL_PLAN_STATUS, attribute, IConstant.VALUE.PLANT);

        Boolean ldFlag = false;
        for (CnsPlanParameterEntity planParameterEntity: ldList) {
            if (materialPlantV1Entity.getLocalPlant().equals(planParameterEntity.getParameterValue())){
                ldFlag = true;
            }
        }

        Boolean lmFlag = false;
        List<CnsPlanParameterEntity> lmList = planParameterDao.getEntitiesWithConditions(IConstant.VALUE.CONS_LATAM,
                IConstant.VALUE.CNS_MATERIAL_PLAN_STATUS, attribute, IConstant.VALUE.MRP_TYPE, IConstant.VALUE.I);

        for (CnsPlanParameterEntity planParameterEntity:lmList) {
            if (materialPlantV1Entity.getLocalMrpType().equals(planParameterEntity.getParameterValue())){
                lmFlag = true;
            }
        }

        if (lpFlag && ldFlag && lmFlag){
            return true;
        }
        return false;

    }

    private FailData checkF1(EDMMaterialPlantV1Entity materialPlantV1Entity) {
        FailData failData = new FailData();
        failData.setErrorCode("F1");
        failData.setFunctionalArea("DP");
        failData.setInterfaceID("PangeaCnsMaterialPlanStatus");
        failData.setSourceSystem(IConstant.VALUE.PROJECT_ONE);
        failData.setKey1(materialPlantV1Entity.getLocalPlant());
        failData.setKey2(materialPlantV1Entity.getLocalMaterialNumber());
        failData.setKey3("");
        failData.setKey4("");
        failData.setKey5("");
        return failData;
    }

    private FailData checkF2AndF3(EDMMaterialPlantV1Entity materialPlantV1Entity) {
        FailData failData = new FailData();
        failData.setErrorCode("F2,F3");
        failData.setFunctionalArea("DP");
        failData.setInterfaceID("PangeaCnsMaterialPlanStatus");
        failData.setSourceSystem(IConstant.VALUE.PROJECT_ONE);
        failData.setKey1(materialPlantV1Entity.getLocalPlant());
        failData.setKey2(materialPlantV1Entity.getLocalMaterialNumber());
        failData.setKey3("");
        failData.setKey4("");
        failData.setKey5("");
        return failData;
    }

    public List<PlanCnsMaterialPlanStatusBo> getMaterialInclBo() {
        List<PlanCnsMaterialPlanStatusBo> materialPlanStatusBoList = new ArrayList<>();

        List<CnsMaterialInclEntity> materialInclEntityList = materialInclDao.getAllEntity();

        for (CnsMaterialInclEntity materialInclEntity:materialInclEntityList) {

            PlanCnsMaterialPlanStatusBo materialPlanStatusBo = new PlanCnsMaterialPlanStatusBo();
            materialPlanStatusBo.setSourceSystem(materialInclEntity.getSourceSystem());
            materialPlanStatusBo.setLocalMaterialNumber(materialInclEntity.getLocalMaterialNumber());
            materialPlanStatusBo.setLocalPlant(materialInclEntity.getLocalPlant());
            if (IConstant.VALUE.NP.equals(materialInclEntity.getPlanningType())){
                materialPlanStatusBo.setNoPlanRelevant(IConstant.VALUE.X);
            }
            materialPlanStatusBoList.add(materialPlanStatusBo);
        }
        return materialPlanStatusBoList;
    }
}
