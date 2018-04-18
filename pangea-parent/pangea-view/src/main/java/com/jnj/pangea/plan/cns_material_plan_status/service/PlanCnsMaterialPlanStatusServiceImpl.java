package com.jnj.pangea.plan.cns_material_plan_status.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialGlobalDaoImpl;
import com.jnj.pangea.common.dao.impl.edm.EDMSourceSystemV1DaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.entity.edm.EDMMaterialPlantV1Entity;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import com.jnj.pangea.common.entity.plan.CnsMaterialInclEntity;
import com.jnj.pangea.plan.cns_material_plan_status.bo.PlanCnsMaterialPlanStatusBo;

import java.util.Set;

public class PlanCnsMaterialPlanStatusServiceImpl {

    private static PlanCnsMaterialPlanStatusServiceImpl instance;

    public static PlanCnsMaterialPlanStatusServiceImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsMaterialPlanStatusServiceImpl();
        }
        return instance;
    }

    private EDMSourceSystemV1DaoImpl sourceSystemV1Dao = EDMSourceSystemV1DaoImpl.getInstance();
    private EDMMaterialGlobalDaoImpl edmMaterialGlobalDao = EDMMaterialGlobalDaoImpl.getInstance();

    public ResultObject buildView(String key, Object o, Set<String> f1Set, Set<String> f2ASet,Set<String> f2BSet, Set<String> f3ASet,Set<String> f3BSet) {
        ResultObject resultObject = new ResultObject();

        EDMMaterialPlantV1Entity materialPlantV1Entity = (EDMMaterialPlantV1Entity) o;

        PlanCnsMaterialPlanStatusBo materialPlanStatusBo = new PlanCnsMaterialPlanStatusBo();

        String localPlant = materialPlantV1Entity.getLocalPlant();
        String localMrpType = materialPlantV1Entity.getLocalMrpType();
        LogUtil.getCoreLog().info("localPlant:"+ localPlant);
        LogUtil.getCoreLog().info("localMrpType:"+ localMrpType);


        boolean f2A = f2ASet.contains(localPlant);
        boolean f2B = f2BSet.contains(localMrpType);
        boolean f3A = f3ASet.contains(localPlant);
        boolean f3B = f3BSet.contains(localMrpType);
        boolean flag = (f2A && f2B) || (f3A && f3B);
        LogUtil.getCoreLog().info("flag:"+flag);
        if ((f2A && f2B) || (f3A && f3B)){
            // T1
            materialPlanStatusBo.setSourceSystem(getFieldWithT1());

            materialPlanStatusBo.setLocalMaterialNumber(materialPlantV1Entity.getLocalMaterialNumber());
            materialPlanStatusBo.setLocalPlant(materialPlantV1Entity.getLocalPlant());
            materialPlanStatusBo.setMaterialNumber(materialPlantV1Entity.getMaterialNumber());

            EDMMaterialGlobalV1Entity materialGlobalV1Entity = edmMaterialGlobalDao.getEntityWithLocalMaterialNumber(materialPlantV1Entity.getLocalMaterialNumber());
            if (null != materialGlobalV1Entity) {
                if (f1Set.contains(materialGlobalV1Entity.getMaterialType())){
                    String localParentCode = materialGlobalV1Entity.getLocalDpParentCode();
                    materialPlanStatusBo.setLocalParentCode(localParentCode);
                    materialPlanStatusBo.setPpc(materialGlobalV1Entity.getPrimaryPlanningCode());
                }
            }
            if (f2A && f2B){
                materialPlanStatusBo.setDpRelevant(IConstant.VALUE.X);
            }
            if (f3A && f3B) {
                materialPlanStatusBo.setSpRelevant(IConstant.VALUE.X);
            }

            if (null!=materialPlanStatusBo.getLocalParentCode() && !"".equals(materialPlanStatusBo.getLocalParentCode())){
                materialPlanStatusBo.setParentActive(IConstant.VALUE.X);
            }

            //T7
            getFieldWithT7(materialPlanStatusBo);

            LogUtil.getCoreLog().info("materialPlanStatusBo:"+materialPlanStatusBo);
            resultObject.setBaseBo(materialPlanStatusBo);
        }else{
            FailData failData = checkFailData(materialPlantV1Entity,IConstant.FAILED.ERROR_CODE.F2F3);
            resultObject.setFailData(failData);
            return resultObject;
        }

        return resultObject;
    }

    public ResultObject materialInclBuildView(CnsMaterialInclEntity materialInclEntity) {

        ResultObject resultObject = new ResultObject();

        PlanCnsMaterialPlanStatusBo materialPlanStatusBo = new PlanCnsMaterialPlanStatusBo();

        // T1
        materialPlanStatusBo.setSourceSystem(getFieldWithT1());

        materialPlanStatusBo.setLocalMaterialNumber(materialInclEntity.getLocalMaterialNumber());
        materialPlanStatusBo.setLocalPlant(materialInclEntity.getLocalPlant());

        if (IConstant.VALUE.NP.equals(materialInclEntity.getPlanningType())){
            materialPlanStatusBo.setNoPlanRelevant(IConstant.VALUE.X);
        }

        //T7
        getFieldWithT7(materialPlanStatusBo);

        LogUtil.getCoreLog().info("materialPlanStatusBo:"+materialPlanStatusBo);
        resultObject.setBaseBo(materialPlanStatusBo);
        return resultObject;
    }

    private String getFieldWithT1() {
        EDMSourceSystemV1Entity sourceSystemEntity = sourceSystemV1Dao.getEntityWithLocalSourceSystem(IConstant.VALUE.PROJECT_ONE);
        if (null != sourceSystemEntity) {
            return sourceSystemEntity.getSourceSystem();
        }
        return null;
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

    private FailData checkFailData(EDMMaterialPlantV1Entity materialPlantV1Entity,String ErrorCode) {
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
