package com.jnj.pangea.omp.gdm_product_location_detail.service;

import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialGlobalDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsProdLocAttribDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.entity.plan.PlanCnsMaterialPlanStatusEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsProdLocAttribEntity;
import com.jnj.pangea.omp.gdm_product_location_detail.bo.OMPGdmProductLocationDetailBo;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedList;
import java.util.List;

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


        String localMaterialNumber = cnsMaterialPlanStatusEntity.getLocalMaterialNumber();
        String localPlant = cnsMaterialPlanStatusEntity.getLocalPlant();
        String sourceSystem = cnsMaterialPlanStatusEntity.getSourceSystem();

        PlanCnsProdLocAttribEntity prodLocAttribEntity = null;
        if (StringUtils.isNotEmpty(localMaterialNumber) && StringUtils.isNotEmpty(localPlant) && StringUtils.isNotEmpty(sourceSystem)) {
            prodLocAttribEntity = cnsProdLocAttribDao.getEntityWithConditions(sourceSystem, localMaterialNumber, localPlant);
        }

        if (null != prodLocAttribEntity) {

            if (IConstant.VALUE.X.equals(cnsMaterialPlanStatusEntity.getSpRelevant()) || IConstant.VALUE.X.equals(cnsMaterialPlanStatusEntity.getNoPlanRelevant())) {

                String localMaterialNumberPr = prodLocAttribEntity.getLocalMaterialNumber();
                String sourceSystemPr = prodLocAttribEntity.getSourceSystem();
                EDMMaterialGlobalV1Entity materialGlobalV1Entity = materialGlobalDao.getMaterialNumberWithLocalMaterialNumberAndSourceSystem(sourceSystemPr, localMaterialNumberPr);

                if (null != materialGlobalV1Entity) {

                    if (StringUtils.isEmpty(materialGlobalV1Entity.getPrimaryPlanningCode()) &&
                            StringUtils.isEmpty(materialGlobalV1Entity.getMaterialNumber())) {

                        // Reject record
                        ResultObject resultObjectE2 = new ResultObject();
                        resultObjectE2.setFailData(new FailData(IConstant.FAILED.FUNCTIONAL_AREA.SP, IConstant.FAILED.INTERFACE_ID.OMP_GDM_PRODUCT_LOCATION_DETAIL, IConstant.FAILED.ERROR_CODE.E2,
                                IConstant.FAILED.ERROR_VALUE.OMP_GDM_PRODUCT_LOCATION_DETAIL_E2, "omp", cnsMaterialPlanStatusEntity.getSourceSystem(),
                                cnsMaterialPlanStatusEntity.getLocalMaterialNumber(), cnsMaterialPlanStatusEntity.getLocalPlant()));
                        resultObjects.add(resultObjectE2);
                    } else {

                        String name = "";
                        String value = "";

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

                            if (null != name && !"".equals(name)) {
                                OMPGdmProductLocationDetailBo gdmProductLocationDetailBo = new OMPGdmProductLocationDetailBo();
                                ResultObject resultObject = new ResultObject();
                                String classStr = "";
                                if (IConstant.VALUE.CONS_LATAM.equals(materialGlobalV1Entity.getSourceSystem())) {
                                    classStr = IConstant.VALUE.PGA;
                                    gdmProductLocationDetailBo.setCLASS(classStr);
                                    gdmProductLocationDetailBo.setDescription(IConstant.VALUE.PANGEA);
                                }

                                // E2
                                String productLocationId = "";
                                if (StringUtils.isNotEmpty(materialGlobalV1Entity.getPrimaryPlanningCode())) {

                                    productLocationId = materialGlobalV1Entity.getPrimaryPlanningCode() + IConstant.VALUE.LINE + sourceSystemPr + IConstant.VALUE.UNDERLINE + prodLocAttribEntity.getLocalPlant();
                                    gdmProductLocationDetailBo.setProductLocationId(productLocationId);
                                } else {

                                    productLocationId = materialGlobalV1Entity.getMaterialNumber() + IConstant.VALUE.LINE + sourceSystemPr + IConstant.VALUE.UNDERLINE + prodLocAttribEntity.getLocalPlant();
                                    gdmProductLocationDetailBo.setProductLocationId(productLocationId);
                                }

                                String productLocationDetailId = productLocationId + IConstant.VALUE.BACK_SLANT + classStr + IConstant.VALUE.BACK_SLANT + name;
                                gdmProductLocationDetailBo.setProductLocationDetailId(productLocationDetailId);

                                gdmProductLocationDetailBo.setName(name);
                                gdmProductLocationDetailBo.setValue(value);
                                //N2 N3
                                gdmProductLocationDetailBo.setActiveOPRERP(IConstant.VALUE.YES);
                                gdmProductLocationDetailBo.setActiveSOPERP(IConstant.VALUE.NO);
                                resultObject.setBaseBo(gdmProductLocationDetailBo);
                                resultObjects.add(resultObject);
                            }
                        }
                    }
                }
            } else {
                // Reject record
                ResultObject resultObject = new ResultObject();
                resultObject.setFailData(new FailData(IConstant.FAILED.FUNCTIONAL_AREA.SP, IConstant.FAILED.INTERFACE_ID.OMP_GDM_PRODUCT_LOCATION_DETAIL, IConstant.FAILED.ERROR_CODE.N2,
                        IConstant.FAILED.ERROR_VALUE.OMP_GDM_PRODUCT_LOCATION_DETAIL_N2, "omp", cnsMaterialPlanStatusEntity.getSourceSystem(),
                        cnsMaterialPlanStatusEntity.getLocalMaterialNumber(), cnsMaterialPlanStatusEntity.getLocalPlant()));
                resultObjects.add(resultObject);
            }
        } else {
            // Reject record
            ResultObject resultObject = new ResultObject();
            resultObject.setFailData(new FailData(IConstant.FAILED.FUNCTIONAL_AREA.SP, IConstant.FAILED.INTERFACE_ID.OMP_GDM_PRODUCT_LOCATION_DETAIL, IConstant.FAILED.ERROR_CODE.N2,
                    IConstant.FAILED.ERROR_VALUE.OMP_GDM_PRODUCT_LOCATION_DETAIL_N2, "omp", cnsMaterialPlanStatusEntity.getSourceSystem(),
                    cnsMaterialPlanStatusEntity.getLocalMaterialNumber(), cnsMaterialPlanStatusEntity.getLocalPlant()));
            resultObjects.add(resultObject);
        }

        return resultObjects;
    }
}