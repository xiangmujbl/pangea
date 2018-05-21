package com.jnj.pangea.omp.gdm_product_unit_conversion.service;

import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.dao.impl.edm.EDMMaterialAuomDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsMaterialPlanStatusDaoImpl;
import com.jnj.pangea.common.dao.impl.plan.PlanCnsPlanUnitDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMMaterialAuomV1Entity;
import com.jnj.pangea.common.entity.edm.EDMMaterialGlobalV1Entity;
import com.jnj.pangea.common.entity.plan.PlanCnsMaterialPlanStatusEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsPlanUnitEntity;
import com.jnj.pangea.omp.gdm_product_unit_conversion.bo.GDMProductUnitConversionBo;
import org.apache.commons.lang.StringUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class GDMProductUnitConversionServiceImpl {

    private static GDMProductUnitConversionServiceImpl instance;

    private final DecimalFormat df = new DecimalFormat("0.00");


    public static GDMProductUnitConversionServiceImpl getInstance() {
        if (instance == null) {
            instance = new GDMProductUnitConversionServiceImpl();
        }
        return instance;
    }

    private PlanCnsPlanUnitDaoImpl planCnsPlanUnitDao = PlanCnsPlanUnitDaoImpl.getInstance();
    private EDMMaterialAuomDaoImpl edmMaterialAuomDao = EDMMaterialAuomDaoImpl.getInstance();
    private PlanCnsMaterialPlanStatusDaoImpl planCnsMaterialPlanStatusDao = PlanCnsMaterialPlanStatusDaoImpl.getInstance();


    public List<ResultObject> buildView(String key, Object o, Object o2) {

        List<ResultObject> resultObjectList = new ArrayList<>();

        EDMMaterialGlobalV1Entity edmMaterialGlobalV1Entity = (EDMMaterialGlobalV1Entity) o;

        //J1
        List<GDMProductUnitConversionBo> gdmProductUnitConversionBoList = new ArrayList<>();
        List<PlanCnsMaterialPlanStatusEntity> planCnsMaterialPlanStatusEntityList = planCnsMaterialPlanStatusDao.getEntityWithMaterialNumber(edmMaterialGlobalV1Entity.getMaterialNumber());
        if (null == planCnsMaterialPlanStatusEntityList || planCnsMaterialPlanStatusEntityList.size() == 0) {
            return resultObjectList;
        }

        PlanCnsMaterialPlanStatusEntity T1Entity = null;
        PlanCnsMaterialPlanStatusEntity T2Entity = null;
        PlanCnsMaterialPlanStatusEntity T3Entity = null;
        for (PlanCnsMaterialPlanStatusEntity entity : planCnsMaterialPlanStatusEntityList) {
            if (IConstant.VALUE.X.equalsIgnoreCase(entity.getActive())) {
                T1Entity = entity;
            }
            if (IConstant.VALUE.X.equalsIgnoreCase(entity.getDpRelevant())) {
                T2Entity = entity;
            }

            if (IConstant.VALUE.X.equalsIgnoreCase(entity.getSpRelevant())) {
                T3Entity = entity;
            }
            LogUtil.getCoreLog().info("===========entity.getSpRelevant() =" + entity.getSpRelevant() + ",entity.getActive() = " + entity.getActive() + ",entity.getDpRelevant() = " + entity.getDpRelevant());
        }

        //T1
        if (null != T1Entity && IConstant.VALUE.X.equalsIgnoreCase(T1Entity.getActive())) {

            GDMProductUnitConversionBo gdmProductUnitConversionBo = new GDMProductUnitConversionBo();
            gdmProductUnitConversionBo.setProductId(edmMaterialGlobalV1Entity.getPrimaryPlanningCode());
            gdmProductUnitConversionBoList.add(gdmProductUnitConversionBo);

            if (StringUtils.isNotBlank(edmMaterialGlobalV1Entity.getLocalDpParentCode())) {
                GDMProductUnitConversionBo gdmProductUnitConversionBo1 = new GDMProductUnitConversionBo();
                gdmProductUnitConversionBo1.setProductId(edmMaterialGlobalV1Entity.getLocalDpParentCode());

                gdmProductUnitConversionBoList.add(gdmProductUnitConversionBo1);
            }
        }

        //J2
        String localMaterialNumber = edmMaterialGlobalV1Entity.getLocalMaterialNumber();
        String factor = "";
        if (StringUtils.isNotEmpty(localMaterialNumber)) {
            EDMMaterialAuomV1Entity edmMaterialAuomV1Entity = edmMaterialAuomDao.getEntityWithLocalMaterialNumAndLocalAuom(localMaterialNumber, localMaterialNumber);
            if (edmMaterialAuomV1Entity != null) {

                String localUomMaterialNumber = edmMaterialAuomV1Entity.getLocalMaterialNumber();
                String localUom = edmMaterialAuomV1Entity.getLocalAuom();
                if (localUom.equals(localMaterialNumber) && localUomMaterialNumber.equals(localUomMaterialNumber)) {

                    if (StringUtils.isNotEmpty(edmMaterialAuomV1Entity.getLocalDenominator())) {
                        Pattern pattern = Pattern.compile(IConstant.VALUE.PATTERN_DIGITAL);
                        if (pattern.matcher(edmMaterialAuomV1Entity.getLocalNumerator()).matches() && pattern.matcher(edmMaterialAuomV1Entity.getLocalDenominator()).matches()) {
                            int localNumerator = Integer.valueOf(edmMaterialAuomV1Entity.getLocalNumerator());
                            int localDenominator = Integer.valueOf(edmMaterialAuomV1Entity.getLocalDenominator());
                            factor = String.valueOf(df.format((float) localNumerator / localDenominator));
                        } else {
                            LogUtil.getCoreLog().info("==============localNumerator =" + edmMaterialAuomV1Entity.getLocalNumerator() +
                                    " , localDenominator =" + edmMaterialAuomV1Entity.getLocalDenominator());
                        }
                    }
                }
            }
        }

        //T2 ActiveFCTERP
        String ActiveFCTERP = "";
        String unit = "";
        LogUtil.getCoreLog().info("===============edmMaterialGlobalV1Entity.getLocalDpParentCode() = " + edmMaterialGlobalV1Entity.getLocalDpParentCode()
                + ",edmMaterialGlobalV1Entity materialNumber = " + edmMaterialGlobalV1Entity.getMaterialNumber());
        if (StringUtils.isNotBlank(edmMaterialGlobalV1Entity.getLocalDpParentCode())) {
            EDMMaterialAuomV1Entity edmMaterialAuomV1Entity = edmMaterialAuomDao.getEntityWithMaterialNum(edmMaterialGlobalV1Entity.getMaterialNumber());

            if (null != edmMaterialAuomV1Entity) {
                LogUtil.getCoreLog().info("=============edmMaterialAuomV1Entity.getLocalAuom() = " + edmMaterialAuomV1Entity.getLocalAuom());
                PlanCnsPlanUnitEntity planCnsPlanUnitEntity = planCnsPlanUnitDao.getCnsPlanUnitEntityWithLocalUom(edmMaterialAuomV1Entity.getLocalAuom());
                if (null == planCnsPlanUnitEntity) {
                    ResultObject resultObject = new ResultObject();
                    FailData failData = writeFailDataToRegion(edmMaterialGlobalV1Entity, "E1", "No Enterprise UOM is maintained");
                    resultObject.setFailData(failData);
                    resultObjectList.add(resultObject);
                } else {
                    LogUtil.getCoreLog().info("=============planCnsPlanUnitEntitygetPlantFlag = " + planCnsPlanUnitEntity.getPlanFlag());
                    unit = planCnsPlanUnitEntity.getUnit();
                    LogUtil.getCoreLog().info("=============== unit = " + unit);
                    if (IConstant.VALUE.DP.equals(planCnsPlanUnitEntity.getPlanFlag()) || IConstant.VALUE.DPSP.equals(planCnsPlanUnitEntity.getPlanFlag())) {
                        ActiveFCTERP = IConstant.VALUE.YES;
                    }
                }
            } else {
                LogUtil.getCoreLog().info("=============== not find edmMaterialAuomV1Entity ============ ");
            }
        } else {
            if (null != T2Entity && IConstant.VALUE.X.equals(T2Entity.getDpRelevant())) {
                EDMMaterialAuomV1Entity edmMaterialAuomV1Entity = edmMaterialAuomDao.getEntityWithMaterialNum(edmMaterialGlobalV1Entity.getMaterialNumber());
                if (null != edmMaterialAuomV1Entity) {
                    PlanCnsPlanUnitEntity planCnsPlanUnitEntity = planCnsPlanUnitDao.getCnsPlanUnitEntityWithLocalUom(edmMaterialAuomV1Entity.getLocalAuom());
                    if (null == planCnsPlanUnitEntity) {
                        ResultObject resultObject = new ResultObject();
                        FailData failData = writeFailDataToRegion(edmMaterialGlobalV1Entity, "E1", "No Enterprise UOM is maintained");
                        resultObject.setFailData(failData);
                        resultObjectList.add(resultObject);
                    } else {
                        unit = planCnsPlanUnitEntity.getUnit();
                        LogUtil.getCoreLog().info("=============planCnsPlanUnitEntitygetPlantFlag = " + planCnsPlanUnitEntity.getPlanFlag());
                        if (IConstant.VALUE.DP.equals(planCnsPlanUnitEntity.getPlanFlag()) || IConstant.VALUE.DPSP.equals(planCnsPlanUnitEntity.getPlanFlag())) {
                            ActiveFCTERP = IConstant.VALUE.YES;
                        }
                    }
                }
            }
        }

        //T3 ActiveOPRERP
        String ActiveOPRERP = "";
        if (null != T3Entity && IConstant.VALUE.X.equals(T3Entity.getSpRelevant())) {
            EDMMaterialAuomV1Entity edmMaterialAuomV1Entity = edmMaterialAuomDao.getEntityWithMaterialNum(edmMaterialGlobalV1Entity.getMaterialNumber());
            if (null != edmMaterialAuomV1Entity) {
                PlanCnsPlanUnitEntity planCnsPlanUnitEntity = planCnsPlanUnitDao.getCnsPlanUnitEntityWithLocalUom(edmMaterialAuomV1Entity.getLocalAuom());
                if (null != planCnsPlanUnitEntity && (IConstant.VALUE.DP.equals(planCnsPlanUnitEntity.getPlanFlag()) || IConstant.VALUE.DPSP.equals(planCnsPlanUnitEntity.getPlanFlag()))) {
                    ActiveOPRERP = IConstant.VALUE.YES;
                }
            }
        }

        //T4
        String Active = "";
        if (IConstant.VALUE.YES.equals(ActiveFCTERP) || IConstant.VALUE.YES.equals(ActiveOPRERP)) {
            Active = IConstant.VALUE.YES;
        }

        //E1


        //Set common field
        for (GDMProductUnitConversionBo gdmProductUnitConversionBo : gdmProductUnitConversionBoList) {
            ResultObject resultObject = new ResultObject();

            gdmProductUnitConversionBo.setUnitId(unit);
            //C1 CONCATENATE 'GDMProductUnitConversion-ProductID', 'GDMProductUnitConversion-UnitId'
            gdmProductUnitConversionBo.setGdmProductUnitConversionId(gdmProductUnitConversionBo.getProductId() + gdmProductUnitConversionBo.getUnitId());
            gdmProductUnitConversionBo.setFactor(factor);
            gdmProductUnitConversionBo.setActiveFCTERP(ActiveFCTERP);
            gdmProductUnitConversionBo.setActiveOPRERP(ActiveOPRERP);
            gdmProductUnitConversionBo.setActive(Active);
            //D1 Comments,leave Blank, example " "
            gdmProductUnitConversionBo.setComments("");
            //D2 ActiveSOPERP , default "NO"
            gdmProductUnitConversionBo.setActiveSOPERP(IConstant.VALUE.NO);

            resultObject.setBaseBo(gdmProductUnitConversionBo);
            resultObjectList.add(resultObject);
        }

        return resultObjectList;
    }

    private FailData writeFailDataToRegion(EDMMaterialGlobalV1Entity materialGlobalV1Entity, String ruleCode, String errorValue) {
        FailData failData = new FailData();
        failData.setFunctionalArea(IConstant.FAILED.FUNCTIONAL_AREA.SP);
        failData.setInterfaceID(IConstant.FAILED.INTERFACE_ID.OMP_GDM_PRODUCT_UNIT_CONVERSION);
        failData.setErrorCode(ruleCode);
        failData.setSourceSystem(IConstant.VALUE.OMP);
        failData.setKey1(materialGlobalV1Entity.getLocalMaterialNumber());
        failData.setKey2("");
        failData.setKey3("");
        failData.setKey4("");
        failData.setKey5("");
        failData.setErrorValue(errorValue);
        return failData;
    }
}