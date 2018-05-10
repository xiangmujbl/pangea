package com.jnj.pangea.plan.cns_material_plan_status.service;

import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialGlobalDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsMaterialInclDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.entity.plan.CnsMaterialInclEntity;
import com.jnj.pangea.plan.cns_material_plan_status.bo.PlanCnsMaterialPlanStatusBo;
import org.apache.commons.lang.StringUtils;

public class PlanCnsMaterialPlanStatus_2ServiceImpl {

    private static PlanCnsMaterialPlanStatus_2ServiceImpl instance;

    public static PlanCnsMaterialPlanStatus_2ServiceImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsMaterialPlanStatus_2ServiceImpl();
        }
        return instance;
    }

    private EDMMaterialGlobalDaoImpl edmMaterialGlobalDao = EDMMaterialGlobalDaoImpl.getInstance();
    private PlanCnsMaterialInclDaoImpl cnsMaterialInclDao = PlanCnsMaterialInclDaoImpl.getInstance();

    public ResultObject buildView(String key, Object o) {
        ResultObject resultObject = new ResultObject();
        CnsMaterialInclEntity materialInclEntity = (CnsMaterialInclEntity) o;
        PlanCnsMaterialPlanStatusBo materialPlanStatusBo = new PlanCnsMaterialPlanStatusBo();

        //J1
        String sourseSystem = getFieldWithJ1(materialInclEntity.getSourceSystem());
        if(sourseSystem != null){
            materialPlanStatusBo.setSourceSystem(sourseSystem);
        }else{
            return null;
        }

        //T1
        String localMaterialNumber = StringUtils.trim(materialInclEntity.getLocalMaterialNumber());
        String localPlant = StringUtils.trim(materialInclEntity.getLocalPlant());
        materialPlanStatusBo.setLocalMaterialNumber(localMaterialNumber);
        materialPlanStatusBo.setLocalPlant(localPlant);

        //J2  T2  T3 T4
        EDMMaterialGlobalV1Entity materialGlobalV1Entity = edmMaterialGlobalDao.getEntityWithLocalMaterialNumber(materialInclEntity.getLocalMaterialNumber());
        if (null != materialGlobalV1Entity) {
            //J2
            materialPlanStatusBo.setMaterialNumber(StringUtils.trim(materialGlobalV1Entity.getMaterialNumber()));

            String localParentCode = StringUtils.trim(materialGlobalV1Entity.getLocalDpParentCode());
            //T2
            materialPlanStatusBo.setLocalParentCode(localParentCode);
            //T4
            if(!localParentCode.isEmpty()){
                materialPlanStatusBo.setParentActive(IConstant.VALUE.X);
            }

            //T3
            materialPlanStatusBo.setPpc(StringUtils.trim(materialGlobalV1Entity.getPrimaryPlanningCode()));
        }else {
            return null;
        }

        //T5
        if (null != materialPlanStatusBo) {
            if(materialPlanStatusBo.getLocalMaterialNumber().equals(materialInclEntity.getLocalMaterialNumber())
                    && IConstant.VALUE.NP.equals(materialInclEntity.getPlanningType())){
                materialPlanStatusBo.setNoPlanRelevant(IConstant.VALUE.X);
            }else {
                materialPlanStatusBo.setNoPlanRelevant(IConstant.VALUE.BLANK);
            }
        }

        //D1
        materialPlanStatusBo.setDpRelevant(IConstant.VALUE.BLANK);
        materialPlanStatusBo.setSpRelevant(IConstant.VALUE.BLANK);

        //T6
        getFieldWithT6(materialPlanStatusBo);

        resultObject.setBaseBo(materialPlanStatusBo);
        return resultObject;
    }

    private String getFieldWithJ1(String sourceSystem) {
        CnsMaterialInclEntity cnsMaterialInclEntity = cnsMaterialInclDao.getEntityWithLocalSourceSystem(sourceSystem);
        if (null != cnsMaterialInclEntity) {
            return sourceSystem;
        }
        return null;
    }

    private void getFieldWithT6(PlanCnsMaterialPlanStatusBo materialPlanStatusBo) {
        if (null != materialPlanStatusBo) {
            if(IConstant.VALUE.X.equals(materialPlanStatusBo.getNoPlanRelevant())
                    || IConstant.VALUE.X.equals(materialPlanStatusBo.getDpRelevant())
                    || IConstant.VALUE.X.equals(materialPlanStatusBo.getSpRelevant()) ){
                materialPlanStatusBo.setActive(IConstant.VALUE.X);
            }
        }
    }
}
