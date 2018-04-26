package com.jnj.pangea.omp.gdm_product_location_detail.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialGlobalDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsProdLocAttribDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.entity.plan.PlanCnsMaterialPlanStatusEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsProdLocAttribEntity;
import com.jnj.pangea.omp.gdm_product_location_detail.bo.OMPGdmProductLocationDetailBo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class OMPGdmProductLocationDetailServiceImpl {

    private static OMPGdmProductLocationDetailServiceImpl instance;

    public static OMPGdmProductLocationDetailServiceImpl getInstance() {
        if (instance == null) {
            instance = new OMPGdmProductLocationDetailServiceImpl();
        }
        return instance;
    }

    private PlanCnsProdLocAttribDaoImpl cnsProdLocAttribDao = PlanCnsProdLocAttribDaoImpl.getInstance();
    private EDMMaterialGlobalDaoImpl materialGlobalDao = EDMMaterialGlobalDaoImpl.getInstance();

    public List<ResultObject> buildView(String key, Object o, Object o2) {

        List<ResultObject> resultObjects = new LinkedList<>();

        PlanCnsMaterialPlanStatusEntity cnsMaterialPlanStatusEntity = (PlanCnsMaterialPlanStatusEntity) o;

        if (IConstant.VALUE.X.equals(cnsMaterialPlanStatusEntity.getSpRelevant()) || IConstant.VALUE.X.equals(cnsMaterialPlanStatusEntity.getNoPlanRelevant())) {

        String localMaterialNumber = cnsMaterialPlanStatusEntity.getLocalMaterialNumber();
        String localPlant = cnsMaterialPlanStatusEntity.getLocalPlant();
        String sourceSystem = cnsMaterialPlanStatusEntity.getSourceSystem();

        PlanCnsProdLocAttribEntity prodLocAttribEntity = cnsProdLocAttribDao.getEntityWithConditions(sourceSystem, localMaterialNumber, localPlant);
        LogUtil.getCoreLog().info("got cns_prod_loc_attr: " + "schdAttrbName1: " + prodLocAttribEntity.getSchdAttrbName1());

        if (null != prodLocAttribEntity) {
            String localMaterialNumberPr = prodLocAttribEntity.getLocalMaterialNumber();
            String sourceSystemPr = prodLocAttribEntity.getSourceSystem();
            EDMMaterialGlobalV1Entity materialGlobalV1Entity = materialGlobalDao.getMaterialNumberWithLocalMaterialNumberAndSourceSystem(sourceSystemPr, localMaterialNumberPr);
            LogUtil.getCoreLog().info("got material global entity: " + "sourceSystem: " + materialGlobalV1Entity.getSourceSystem());

            if (null != materialGlobalV1Entity) {
                String name = "";
                String value = "";
                LogUtil.getCoreLog().info("before loop");
                for (int i = 0; i < 3; i++) {
                    if (i == 0) {
                        name = prodLocAttribEntity.getSchdAttrbName1();
                        value = prodLocAttribEntity.getSchAttrbDesc1();
                    } else if (i == 1) {
                        name = prodLocAttribEntity.getSchdAttrbName2();
                        value = prodLocAttribEntity.getSchAttrbDesc2();
                    } else if (i == 2) {
                        name = prodLocAttribEntity.getSchdAttrbName3();
                        value = prodLocAttribEntity.getSchAttrbDesc3();
                    }

                    LogUtil.getCoreLog().info("in loop");
                    if (null != name && !"".equals(name)) {
                        LogUtil.getCoreLog().info("name checked");
                        OMPGdmProductLocationDetailBo gdmProductLocationDetailBo = new OMPGdmProductLocationDetailBo();
                        ResultObject resultObject = new ResultObject();
                        String CLASS = "";
                        if (IConstant.VALUE.CONS_LATAM.equals(materialGlobalV1Entity.getSourceSystem())) {
                            CLASS = IConstant.VALUE.PGA;
                            gdmProductLocationDetailBo.setCLASS(CLASS);
                            gdmProductLocationDetailBo.setDescription(IConstant.VALUE.PANGEA);
                        }

                        String productLocationId = materialGlobalV1Entity.getPrimaryPlanningCode() + IConstant.VALUE.LINE + sourceSystemPr + IConstant.VALUE.UNDERLINE + prodLocAttribEntity.getLocalPlant();
                        gdmProductLocationDetailBo.setProductLocationId(productLocationId);

                        String productLocationDetailId = productLocationId + IConstant.VALUE.BACK_SLANT + CLASS + IConstant.VALUE.BACK_SLANT + name;
                        gdmProductLocationDetailBo.setProductLocationDetailId(productLocationDetailId);

                        gdmProductLocationDetailBo.setName(name);
                        gdmProductLocationDetailBo.setValue(value);
                        //N2 N3
                        gdmProductLocationDetailBo.setActiveOprerp(IConstant.VALUE.YES);
                        gdmProductLocationDetailBo.setActiveSoperp(IConstant.VALUE.NO);
                        resultObject.setBaseBo(gdmProductLocationDetailBo);
                        resultObjects.add(resultObject);
                    }
                }
            }
        }

        }

        return resultObjects;
    }


    private FailData writeFailDataToRegion(PlanCnsMaterialPlanStatusEntity cnsMaterialPlanStatusEntity, String ruleCode, String errorValue) {
        FailData failData = new FailData();
        failData.setFunctionalArea(IConstant.FAILED.FUNCTIONAL_AREA.SP);
        failData.setInterfaceID(IConstant.FAILED.INTERFACE_ID.OMP_GDM_PRODUCT_LOCATION_DETAIL);
        failData.setErrorCode(ruleCode);
        failData.setSourceSystem("");
        failData.setKey1(cnsMaterialPlanStatusEntity.getSourceSystem());
        failData.setKey2(cnsMaterialPlanStatusEntity.getLocalMaterialNumber());
        failData.setKey3(cnsMaterialPlanStatusEntity.getLocalPlant());
        failData.setKey4("");
        failData.setKey5("");
        failData.setErrorValue(errorValue);
        return failData;
    }
}